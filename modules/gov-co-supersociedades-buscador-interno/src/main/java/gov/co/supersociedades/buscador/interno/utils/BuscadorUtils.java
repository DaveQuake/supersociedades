package gov.co.supersociedades.buscador.interno.utils;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.service.AssetCategoryLocalService;
import com.liferay.document.library.kernel.model.DLFileEntryMetadata;
import com.liferay.document.library.kernel.service.DLAppLocalService;
import com.liferay.document.library.kernel.service.DLFileEntryMetadataLocalServiceUtil;
import com.liferay.document.library.kernel.util.DLUtil;
import com.liferay.dynamic.data.mapping.kernel.DDMFormFieldValue;
import com.liferay.dynamic.data.mapping.kernel.DDMFormValues;
import com.liferay.dynamic.data.mapping.kernel.StorageEngineManagerUtil;
import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.service.JournalArticleLocalService;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.FileVersion;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.search.Summary;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.portlet.PortletPreferences;
import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import gov.co.supersociedades.buscador.interno.constants.SupersociedadesBuscadorInternoPortletKeys;
import gov.co.supersociedades.buscador.interno.models.ArticuloBusqueda;

@Component(immediate=true, service=BuscadorUtils.class)
public class BuscadorUtils {
	
	public ArticuloBusqueda getInfoArticulo(ThemeDisplay td, Document doc) {
		ArticuloBusqueda articulo = new ArticuloBusqueda();
		
		try {
			String idArticle = doc.get(Field.ARTICLE_ID);
			JournalArticle journalArticle = _journalArticleLocalService.getLatestArticle(td.getScopeGroupId(), idArticle);
			if(Validator.isNotNull(journalArticle) && journalArticle.getIndexable()){
				articulo.setTituloArticulo(journalArticle.getTitle());
				articulo.setIdArticulo(journalArticle.getArticleId());
				articulo.setUlrArticulo(_querysUtils.getArticlePageURL(journalArticle, td));
				articulo.setDescripcion(journalArticle.getDescription());
				if(Validator.isNotNull(journalArticle.getModifiedDate())) {
					articulo.setFechaActualizacion(formatter.format(journalArticle.getModifiedDate()));
					articulo.setDateModificate(journalArticle.getModifiedDate());
				}
				articulo.setExtension("web");
			}
		} catch (Exception e) {
			_log.debug(e);
		}
		
		return articulo;
	}
	
	public ArticuloBusqueda getInfoDocumento(ThemeDisplay td, Document doc) {
		
		try {
			ArticuloBusqueda articulo = new ArticuloBusqueda();
			String entryClassName = GetterUtil.getString(doc.get(Field.ENTRY_CLASS_NAME));
			long entryClassPK = GetterUtil.getLong(doc.get(Field.ENTRY_CLASS_PK));
			
			FileEntry fileEntry = _dlAppLocalService.getFileEntry(entryClassPK);
			FileVersion fileVersion = fileEntry.getLatestFileVersion();
			
			articulo.setUlrArticulo(DLUtil.getPreviewURL(fileEntry, fileVersion, td, StringPool.BLANK, true, true));
			articulo.setDescripcion(fileEntry.getDescription());
			Indexer<?> indexer = IndexerRegistryUtil.getIndexer(entryClassName);
			if (indexer != null) {
				String snippet = doc.get(Field.SNIPPET);
				Summary summary = indexer.getSummary(doc, snippet, null, null);
				if (summary != null) {
					articulo.setTituloArticulo(summary.getTitle());
					articulo.setDescripcion(fileEntry.getDescription());
					articulo.setExtension(fileEntry.getExtension());
					articulo.setFechaExtencion(getFechaMetaData(fileEntry, td));
					articulo.setPeso(String.valueOf(fileEntry.getSize()/1000));
					if(Validator.isNotNull(fileEntry.getModifiedDate())) {
						articulo.setFechaActualizacion(formatter.format(fileEntry.getModifiedDate()));
					}
					if(Validator.isNotNull(fileEntry.getModifiedDate())) {
						articulo.setDateModificate(fileEntry.getModifiedDate());
					}
				}
			}
			
			return articulo;
		} catch (Exception e) {
			_log.debug(e);
			return null;
		}
	}
	
	public String getFechaMetaData(FileEntry file, ThemeDisplay td) {
		String fecha = "";
		try {
			List<DLFileEntryMetadata> dlFileEntryMetadata = DLFileEntryMetadataLocalServiceUtil
					.getFileVersionFileEntryMetadatas(file.getFileVersion().getFileVersionId());
			for (DLFileEntryMetadata dlFileEntryMetadata2 : dlFileEntryMetadata) {

				DDMFormValues ddmFormValues = StorageEngineManagerUtil
						.getDDMFormValues(dlFileEntryMetadata2.getDDMStorageId());
				List<DDMFormFieldValue> ddmFormFieldValues = ddmFormValues.getDDMFormFieldValues();
				if (Validator.isNotNull(ddmFormFieldValues) && !ddmFormFieldValues.isEmpty()) {
					for (DDMFormFieldValue formfieldValue : ddmFormFieldValues) {
						if (formfieldValue.getName().equalsIgnoreCase("Fecha1g84")) {
							fecha = formfieldValue.getValue().getString(td.getLocale());

						}
					}
				}

			}

			return formatter.format(fecha);
		} catch (Exception e) {
			return null;
		}
	}
	
	public long getCategorias(HttpServletRequest httpReq, PortletPreferences prefs) {
		String paramCategory = getCategoryURL(httpReq);
		return (paramCategory.isEmpty()) 
				? GetterUtil.getLong(prefs.getValue(SupersociedadesBuscadorInternoPortletKeys.CONFIG_ID_CATEGORY, "0"))
				: Long.parseLong(paramCategory);
	}
	
	public AssetCategory getCategoria(long categoryId) {
		try {
			return _assetCategoryLocalServiceUtil.getCategory(categoryId);
		} catch (Exception e) {
			_log.debug(e);
			return null;
		}
	}
	
	public AssetCategory getCategoriaPadre(AssetCategory categoria) throws PortalException{
		return (categoria.getParentCategoryId() > 0) ? _assetCategoryLocalServiceUtil.getCategory(categoria.getParentCategoryId()) : categoria;
	}
	
	public String getKeywordURL(HttpServletRequest httpReq) {
		return ParamUtil.getString(httpReq, SupersociedadesBuscadorInternoPortletKeys.KEYWORD, StringPool.BLANK);
	}
	
	public String getCategoryURL(HttpServletRequest httpReq) {
		return ParamUtil.getString(httpReq, SupersociedadesBuscadorInternoPortletKeys.ID, StringPool.BLANK);
	}
	
	public String getPaginator(HttpServletRequest httpReq, String paginator, String paginador) {
		if(paginator.equalsIgnoreCase("end")) {
			return ParamUtil.getString(httpReq, paginator, paginador);
		}else {
			return ParamUtil.getString(httpReq, paginator, "0");
		}
	}
	
	private static SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yyyy");
	private static final Log _log = LogFactoryUtil.getLog(BuscadorUtils.class);
	
	@Reference
	private AssetCategoryLocalService _assetCategoryLocalServiceUtil;
	
	@Reference
	private JournalArticleLocalService _journalArticleLocalService;
	
	@Reference
	private DLAppLocalService _dlAppLocalService;
	
	@Reference
	private QuerysUtils _querysUtils;

}
