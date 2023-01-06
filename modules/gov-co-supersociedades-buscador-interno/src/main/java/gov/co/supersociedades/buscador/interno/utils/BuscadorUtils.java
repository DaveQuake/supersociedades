package gov.co.supersociedades.buscador.interno.utils;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.service.AssetCategoryLocalService;
import com.liferay.document.library.kernel.model.DLFileEntryMetadata;
import com.liferay.document.library.kernel.service.DLAppLocalService;
import com.liferay.document.library.kernel.service.DLFileEntryMetadataLocalServiceUtil;
import com.liferay.document.library.kernel.util.DLUtil;
import com.liferay.dynamic.data.mapping.kernel.DDMFormValues;
import com.liferay.dynamic.data.mapping.kernel.StorageEngineManagerUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.FileVersion;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.portlet.PortletPreferences;
import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import gov.co.supersociedades.buscador.interno.constants.SupersociedadesBuscadorInternoPortletKeys;
import gov.co.supersociedades.buscador.interno.models.ArticuloBusqueda;

@Component(immediate=true, service=BuscadorUtils.class)
public class BuscadorUtils {
	
	public ArticuloBusqueda getInfoDocumento(ThemeDisplay td, String doc) {
	//public ArticuloBusqueda getInfoDocumento(ThemeDisplay td, Document doc) {		
		try {
			ArticuloBusqueda articulo = new ArticuloBusqueda();

			long entryClassPK = GetterUtil.getLong(doc);
			long idStructureUrl=2851561;
			long idStructurePublicacion=2221997;
			long idStructureExpedicion=2695274;
			
			long idStructureDiario=4404273;
			long idStructureDiarioLink=4404282;
			long idStructureSuin=4404287;
			long idStructureSuinLink=4404293;
			
//			long timegetFileEntry = System.currentTimeMillis();
			FileEntry fileEntry = _dlAppLocalService.getFileEntry(entryClassPK);
//			_log.info("tiempo metadato getFileEntry "+(System.currentTimeMillis()- timegetFileEntry));
			
//			long timegetLatestFileVersion = System.currentTimeMillis();
			FileVersion fileVersion = fileEntry.getFileVersion();
			long fileVersionId=fileVersion.getFileVersionId();
//			_log.info("tiempo metadato getLatestFileVersion "+(System.currentTimeMillis()- timegetLatestFileVersion));
			
			
			articulo.setUlrArticulo(DLUtil.getPreviewURL(fileEntry, fileVersion, td, StringPool.BLANK, true, true));
			articulo.setDescripcion(fileEntry.getDescription());
			articulo.setTituloArticulo(fileEntry.getTitle());
			articulo.setDescripcion(fileEntry.getDescription());
			articulo.setExtension(fileEntry.getExtension());

//			long timeFechaExp = System.currentTimeMillis();
			Date fechaExpedicionCompare =getFechaMetaDataCompare(fileEntry, td, idStructureExpedicion);
			if(Validator.isNull(fechaExpedicionCompare)) {
				fechaExpedicionCompare = fileEntry.getCreateDate();
				_log.debug("Entro a asignar la fecha de creacion del documento por no tener fecha de expedicion, para el documento "+fileEntry.getFileEntryId()+" "+articulo.getTituloArticulo());
			}
//			_log.info("tiempo metadato fechaexp "+(System.currentTimeMillis()-timeFechaExp));

			articulo.setDateCompare(fechaExpedicionCompare);
			_log.debug("fecha compare sin formatterDos.format "+fechaExpedicionCompare+" "+fileEntry.getFileEntryId()+" "+articulo.getTituloArticulo());
			_log.debug("fecha compare para enviar a generarFecha "+formatterDos.format(fechaExpedicionCompare)+" "+fileEntry.getFileEntryId()+" "+articulo.getTituloArticulo());
			articulo.setFechaExtencion(generarFecha(formatterDos.format(fechaExpedicionCompare)));
			
//			long timeFechaPub = System.currentTimeMillis();
			String fechaPublicacion =getFechaStringMetaData(fileVersionId, td, idStructurePublicacion);
			if(Validator.isNull(fechaPublicacion)) {
				if(Validator.isNotNull(fileEntry.getModifiedDate())) {
					fechaPublicacion = (formatter.format(fileEntry.getModifiedDate()));
					//_log.debug("Entro a asignar la fecha de modificacion del documento por no tener fecha de publicacion, para el documento "+fileEntry.getFileEntryId()+" "+articulo.getTituloArticulo());
				}
			}
//			_log.info("tiempo metadato fechaPub "+(System.currentTimeMillis()-timeFechaPub));
			
//			long timeUrl = System.currentTimeMillis();
			String urlExterna =getStringMetaData(fileVersionId, td, idStructureUrl);
//			_log.info("tiempo metadato url "+(System.currentTimeMillis()-timeUrl));
			
			articulo.setUrlExterna(urlExterna);
			articulo.setPeso(String.valueOf(fileEntry.getSize()/1000));
			articulo.setFechaActualizacion(fechaPublicacion);
			articulo.setDateModificate(fileEntry.getCreateDate());
			
			articulo.setDiarioDescripcion(getStringMetaData(fileVersionId, td, idStructureDiario));
			articulo.setDiarioLink(getStringMetaData(fileVersionId, td, idStructureDiarioLink));
			articulo.setSuinDescripcion(getStringMetaData(fileVersionId, td, idStructureSuin));
			articulo.setSuinLink(getStringMetaData(fileVersionId, td, idStructureSuinLink));
			
			//_log.debug("Objeto articulo para retornar al front "+articulo.toString());
			
			return articulo;
		} catch (Exception e) {
			_log.error(e);
			return null;
		}
	}

	public Date getFechaMetaDataCompare(FileEntry file, ThemeDisplay td, long idEstructura) throws ParseException, ParseException {
		String fecha = "";
		try {
			DLFileEntryMetadata dlFileEntryMetadata = DLFileEntryMetadataLocalServiceUtil.getFileEntryMetadata(idEstructura, file.getFileVersion().getFileVersionId());
			DDMFormValues ddmFormValues = StorageEngineManagerUtil.getDDMFormValues(dlFileEntryMetadata.getDDMStorageId());
			fecha=ddmFormValues.getDDMFormFieldValues().get(0).getValue().getString(td.getLocale());
			_log.debug("fecha leida del metadato sin parse "+fecha+" "+file.getFileEntryId()+" "+file.getTitle());
			return formatterDos.parse(fecha);
		}catch (Exception e) {
			if(Validator.isNotNull(file.getCreateDate())) {
				_log.debug("Entro a asignar la fecha de creacion del documento por no tener fecha de expedicion, para el documento "+file.getFileEntryId()+" "+file.getTitle());
				return file.getCreateDate();
				
			}else {
				//_log.debug("Entro a asignar la fecha de modificacion del documento por no tener fecha de publicacion, para el documento "+file.getFileEntryId()+" "+file.getTitle());
				return file.getModifiedDate();
			}
		}
	}
	
	public String getFechaStringMetaData(long file, ThemeDisplay td, long idEstructura) throws PortalException {
		String fecha = "";
		try{
			DLFileEntryMetadata dlFileEntryMetadata = DLFileEntryMetadataLocalServiceUtil.getFileEntryMetadata(idEstructura, file);
			DDMFormValues ddmFormValues = StorageEngineManagerUtil.getDDMFormValues(dlFileEntryMetadata.getDDMStorageId());
			fecha=ddmFormValues.getDDMFormFieldValues().get(0).getValue().getString(td.getLocale());
			//_log.debug("fecha stringMetadata para enviar a generarFecha "+fecha+" "+file);
			return generarFecha(fecha);
		}catch (Exception e) {
			return "";
		}
	}
	
	public String getStringMetaData(long file, ThemeDisplay td, long idEstructura) throws PortalException {
		try{
			DLFileEntryMetadata dlFileEntryMetadata = DLFileEntryMetadataLocalServiceUtil.getFileEntryMetadata(idEstructura, file);

			DDMFormValues ddmFormValues = StorageEngineManagerUtil.getDDMFormValues(dlFileEntryMetadata.getDDMStorageId());
			return ddmFormValues.getDDMFormFieldValues().get(0).getValue().getString(td.getLocale());
		}catch (Exception e) {
			return "";
		}
	}
		
	public long getCategorias(HttpServletRequest httpReq, PortletPreferences prefs) {
		String paramCategory = getCategoryURL(httpReq,prefs);
		if(Validator.isNotNull(paramCategory)) {
			return Long.parseLong(paramCategory);
		}else {
			if(Validator.isNotNull(prefs.getValue(SupersociedadesBuscadorInternoPortletKeys.CONFIG_ID_CATEGORY_INICIAL, ""))) {
				return GetterUtil.getLong(prefs.getValue(SupersociedadesBuscadorInternoPortletKeys.CONFIG_ID_CATEGORY_INICIAL, "0"));
			}else {
				return  GetterUtil.getLong(prefs.getValue(SupersociedadesBuscadorInternoPortletKeys.CONFIG_ID_CATEGORY, "0"));
			}
			
		}
		
	}
	
	public AssetCategory getCategoria(long categoryId) {
		try {
			return _assetCategoryLocalServiceUtil.getCategory(categoryId);
		} catch (Exception e) {
			_log.error(e);
			return null;
		}
	}
	
	public AssetCategory getCategoriaPadre(AssetCategory categoria) throws PortalException{
		return (categoria.getParentCategoryId() > 0) ? _assetCategoryLocalServiceUtil.getCategory(categoria.getParentCategoryId()) : categoria;
	}
	
	public String getKeywordURL(HttpServletRequest httpReq) {
		return ParamUtil.getString(httpReq, SupersociedadesBuscadorInternoPortletKeys.KEYWORD, StringPool.BLANK);
	}
	
	public String getCategoryURL(HttpServletRequest httpReq, PortletPreferences prefs) {
		return ParamUtil.getString(httpReq, SupersociedadesBuscadorInternoPortletKeys.ID, GetterUtil.getString(prefs.getValue(SupersociedadesBuscadorInternoPortletKeys.CONFIG_ID_CATEGORY_INICIAL, "0")));
	}
	
	public String getPaginator(HttpServletRequest httpReq, String paginator, String paginador) {
		if(paginator.equalsIgnoreCase("end")) {
			return ParamUtil.getString(httpReq, paginator, paginador);
		}else {
			return ParamUtil.getString(httpReq, paginator, "0");
		}
	}
	
	private String generarFecha(String fecha) {
		String[] fechaSplit = fecha.split("-");
		String mes = generarMes(fechaSplit[1]);

		fecha = fechaSplit[2] + " " + mes + " " + fechaSplit[0];
		return fecha;
	}

	private String generarMes(String mes) {
		String mesString = "";

		if (mes.equalsIgnoreCase("01") || mes.equalsIgnoreCase("1")) {
			mesString = "Ene";
		}

		if (mes.equalsIgnoreCase("02") || mes.equalsIgnoreCase("2")) {
			mesString = "Feb";
		}

		if (mes.equalsIgnoreCase("03") || mes.equalsIgnoreCase("3")) {
			mesString = "Mar";
		}

		if (mes.equalsIgnoreCase("04") || mes.equalsIgnoreCase("4")) {
			mesString = "Abr";
		}

		if (mes.equalsIgnoreCase("05") || mes.equalsIgnoreCase("5")) {
			mesString = "May";
		}

		if (mes.equalsIgnoreCase("06") || mes.equalsIgnoreCase("6")) {
			mesString = "Jun";
		}

		if (mes.equalsIgnoreCase("07") || mes.equalsIgnoreCase("7")) {
			mesString = "Jul";
		}

		if (mes.equalsIgnoreCase("08") || mes.equalsIgnoreCase("8")) {
			mesString = "Ago";
		}

		if (mes.equalsIgnoreCase("09") || mes.equalsIgnoreCase("9")) {
			mesString = "Sep";
		}

		if (mes.equalsIgnoreCase("10")) {
			mesString = "Oct";
		}

		if (mes.equalsIgnoreCase("11")) {
			mesString = "Nov";
		}

		if (mes.equalsIgnoreCase("12")) {
			mesString = "Dic";
		}

		return mesString;

	}
	
	private static SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yyyy");
	private static SimpleDateFormat formatterDos = new SimpleDateFormat("yyyy-MM-dd");
	private static final Log _log = LogFactoryUtil.getLog(BuscadorUtils.class);
	
	@Reference
	private AssetCategoryLocalService _assetCategoryLocalServiceUtil;
		
	@Reference
	private DLAppLocalService _dlAppLocalService;

}
