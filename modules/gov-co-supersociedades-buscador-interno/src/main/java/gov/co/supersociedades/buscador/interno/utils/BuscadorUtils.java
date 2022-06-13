package gov.co.supersociedades.buscador.interno.utils;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.service.AssetCategoryLocalService;
import com.liferay.document.library.kernel.service.DLAppLocalService;
import com.liferay.document.library.kernel.util.DLUtil;
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
				articulo.setTituloArticulo(journalArticle.getTitleCurrentValue());
				articulo.setIdArticulo(journalArticle.getArticleId());
				articulo.setUlrArticulo(_querysUtils.getArticlePageURL(journalArticle, td));
				articulo.setDescripcion(journalArticle.getDescription());
				articulo.setFechaActualizacion(formatter.format(journalArticle.getModifiedDate()));
				articulo.setDateModificate(journalArticle.getModifiedDate());
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
					articulo.setFechaActualizacion(formatter.format(fileEntry.getModifiedDate()));
					articulo.setDateModificate(fileEntry.getModifiedDate());
				}
			}
			
			return articulo;
		} catch (Exception e) {
			_log.debug(e);
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
