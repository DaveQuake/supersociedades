package co.gov.supersociedades.aspirantes.helpers;

import com.liferay.document.library.kernel.model.DLFileEntryMetadata;
import com.liferay.document.library.kernel.service.DLAppServiceUtil;
import com.liferay.document.library.kernel.service.DLFileEntryMetadataLocalServiceUtil;
import com.liferay.document.library.kernel.util.DLUtil;
import com.liferay.dynamic.data.mapping.kernel.DDMFormFieldValue;
import com.liferay.dynamic.data.mapping.kernel.DDMFormValues;
import com.liferay.dynamic.data.mapping.kernel.StorageEngineManagerUtil;
import com.liferay.dynamic.data.mapping.model.DDMContent;
import com.liferay.dynamic.data.mapping.model.DDMFormFieldOptions;
import com.liferay.dynamic.data.mapping.model.DDMStructure;
import com.liferay.dynamic.data.mapping.service.DDMContentLocalServiceUtil;
import com.liferay.dynamic.data.mapping.service.DDMStructureLocalServiceUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.Validator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.PortletPreferences;
import javax.portlet.PortletRequest;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;

import co.gov.supersociedades.aspirantes.configuration.ListadoConfiguration;
import co.gov.supersociedades.aspirantes.constants.Constantes;
import co.gov.supersociedades.aspirantes.models.Documento;
import co.gov.supersociedades.aspirantes.models.ListadoDocumentos;



@Component(immediate = true, configurationPid = "gov_co_supersociedades_ListarAspirantesConfiguration", service = HelperDocumentos.class)
public class HelperDocumentos {

	private static final Log _log = LogFactoryUtil.getLog(HelperDocumentos.class);
	private volatile ListadoConfiguration _listadoConfig;
	private static SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yyyy");

	@Activate
	@Modified
	protected void activate(Map<Object, Object> properties) {
		_listadoConfig = ConfigurableUtil.createConfigurable(ListadoConfiguration.class, properties);
	}

	public List<ListadoDocumentos> generarListado(ThemeDisplay td, PortletPreferences prefs) {
		List<ListadoDocumentos> listadoDoc = new ArrayList<ListadoDocumentos>();

		if (Validator.isNotNull(prefs.getValue(Constantes.ID_CARPETA, StringPool.BLANK))) {
			try {
				Folder folderPadre = DLAppServiceUtil
						.getFolder(Long.parseLong(prefs.getValue(Constantes.ID_CARPETA, StringPool.BLANK)));
				List<Folder> carpetasInternas = DLAppServiceUtil.getFolders(folderPadre.getRepositoryId(),
						folderPadre.getFolderId());
				if (!carpetasInternas.isEmpty()) {
					for (Folder folder : carpetasInternas) {
						List<FileEntry> listFile = DLAppServiceUtil.getFileEntries(folder.getGroupId(),
								folder.getFolderId());
						if (!listFile.isEmpty()) {
							listadoDoc.add(getListadoDocumentos(listFile, folder, td));
						}
					}
				} else {
					List<FileEntry> listFile = DLAppServiceUtil.getFileEntries(folderPadre.getGroupId(),
							folderPadre.getFolderId());
					if (!listFile.isEmpty()) {
						listadoDoc.add(getListadoDocumentos(listFile, folderPadre, td));
					}
				}
			} catch (Exception e) {
				_log.error("Error en el proceso de busqueda documentos: ", e);
			}
		}
//		List<ListadoDocumentos> listaOrdenada = new ArrayList(listadoDoc);
//		return listaOrdenada;
		if(listadoDoc.size() > 0) {
//			for (ListadoDocumentos documento : listaOrdenada) {
//				_log.info("generarListado antes de ordenar "+documento.getNombreListado());
//			}
//			Collections.sort(listaOrdenada, compareByFechaCreacion);
//			return listaOrdenada;
			if (listadoDoc.get(0).getNombreListado().contains("20")) {

				Collections.sort(listadoDoc, compareByFechaCreacion);
//				for (ListadoDocumentos documento : listaOrdenada) {
//					_log.info("generarListado ordenado por compareByFechaCreacion despues de ordenar "+documento.getNombreListado());
//				}
				return listadoDoc;
			} else {
				Collections.sort(listadoDoc, compareName);
//				for (ListadoDocumentos documento : listaOrdenada) {
//					_log.info("generarListado ordenado por compareName despues de ordenar "+documento.getNombreListado());
//				}
				return listadoDoc;
			}
		}else {
			return listadoDoc;
		}
		

	}

	private ListadoDocumentos getListadoDocumentos(List<FileEntry> listFile, Folder folder, ThemeDisplay td) {
		ListadoDocumentos listado = new ListadoDocumentos();
		listado.setNombreListado(folder.getName());
		listado.setId(String.valueOf(folder.getFolderId()));
		if (listFile.size() > 0) {
			
			Map<String, String> tiposVinculacion = new HashMap<String, String>();
			//structureKey =	AUTO_B10AEDAA-4CBD-4A1F-C2FE-D3A3B5ACD50F
			DDMStructure estructura = (DDMStructure) DDMStructureLocalServiceUtil.fetchDDMStructure(41033L);
			try {
				DDMFormFieldOptions opciones = estructura.getDDMFormField("Seleccionar1wy4").getDDMFormFieldOptions();
				//_log.info("opciones "+opciones.getOptions().toString());
				tiposVinculacion.put("[\"value 1\"]", opciones.getOptionLabels("value 1").getString(LocaleUtil.SPAIN));
				tiposVinculacion.put("[\"value 2\"]", opciones.getOptionLabels("value 2").getString(LocaleUtil.SPAIN));
				tiposVinculacion.put("[\"value 3\"]", opciones.getOptionLabels("value 3").getString(LocaleUtil.SPAIN));
//				_log.info("tipos "+tiposVinculacion.get("[\"value 1\"]"));
//				_log.info("tipos "+tiposVinculacion.get("[\"value 2\"]"));
//				_log.info("tipos "+tiposVinculacion.get("[\"value 3\"]"));
			} catch (PortalException e1) {
				_log.error(e1);
			}
					
			List<Documento> listDoc = new ArrayList<Documento>();
			for (FileEntry files : listFile) {
				try {
					Documento doc = new Documento();
					String url = DLUtil.getPreviewURL(files, files.getLatestFileVersion(), td, StringPool.BLANK);
					doc.setNombreDocumento(files.getTitle());
					doc.setDescripcion(files.getDescription());
					String extencion = files.getExtension();
					if(extencion.contains(".")) {
						extencion= files.getExtension().split(".")[0];
					}
					doc.setExtencion(extencion);
					String date = getFechaMetaData(files, td, Constantes.FECHA_PUBLICACION);
					if(Validator.isNull(date)) {
						if(Validator.isNotNull(files.getCreateDate())) {
							date = (formatter.format(files.getCreateDate()));
						}
					}
					
					doc.setFecha(date);
					doc.setUrlDocumento(url);
					String fechaExpedicion =getFechaMetaData(files, td, Constantes.FECHA_EXPEDICION);
					String urlExterna = getFechaMetaData(files, td, Constantes.URL_ENLACE_OTRA_PAG);
					if(Validator.isNull(fechaExpedicion)) {
						if(Validator.isNotNull(files.getModifiedDate())) {
							fechaExpedicion = (formatter.format(files.getModifiedDate()));
						}
					}
					doc.setUrlExterna(urlExterna);
					doc.setFechaExpedicion(fechaExpedicion);
					doc.setPeso(String.valueOf(files.getSize() / 1000));
					doc.setNombrefuncionario(getStringMetaData(files, td, Constantes.NOMBRE_FUNCIONARIO));
					doc.setCargo(getStringMetaData(files, td, Constantes.CARGO));
					//_log.info("metadato tipo "+getStringMetaData(files, td, Constantes.TIPO_VINCULACION));
					doc.setTipoVinculacion(tiposVinculacion.get(getStringMetaData(files, td, Constantes.TIPO_VINCULACION)));
					
					listDoc.add(doc);
				} catch (Exception e) {
					continue;
				}
			}
//			for (Documento documento : listDoc) {
//				_log.info("antes de ordenar "+documento.getFechaExpedicion()+" "+documento.getNombreDocumento());
//			}
			Collections.sort(listDoc ,compareByFechaCreacionDocumento);
			listado.setDocumentos(listDoc);
//			for (Documento documento : listDoc) {
//				_log.info("despues de ordenar "+documento.getFechaExpedicion()+" "+documento.getNombreDocumento());
//			}
			
		}
		
		
		return listado;
	}

	public Comparator<Documento> compareByFechaCreacionDocumento = new Comparator<Documento>() {

		@Override
		public int compare(Documento documentoUno, Documento documentoDos) {
			try {
				Date dateUno = formatter.parse(documentoUno.getFechaExpedicion());
				Date dateDos = formatter.parse(documentoDos.getFechaExpedicion());
				
				String tituloUno = documentoUno.getFechaExpedicion();
				String tituloDos = documentoDos.getFechaExpedicion();
				
				int compareValue=0;
				if(dateUno == dateDos) {
					tituloUno = documentoUno.getNombreDocumento();
					tituloDos = documentoDos.getNombreDocumento();
					compareValue = tituloUno.compareTo(tituloDos);
				}else {
					compareValue = dateUno.compareTo(dateDos);
				}
	
				if (compareValue == 0) {
					return 0;
				} else if (compareValue > 0) {
					return -1;
				} else {
					return 1;
				}
			
			} catch (ParseException e) {
				_log.error(e);
				return 0;
			}
		}

	};

	
	public String getFechaMetaData(FileEntry file, ThemeDisplay td, String nombreCampo) {
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
						if (formfieldValue.getName().equalsIgnoreCase(nombreCampo)) {
							fecha = formfieldValue.getValue().getString(td.getLocale());
						}
					}
				}
			}
			return generarFechaMetadata(fecha);
		} catch (Exception e) {
			return "";
		}
	}
	
	public String getStringMetaData(FileEntry file, ThemeDisplay td, String nombreCampo) {
		String data = "";
		try {
			List<DLFileEntryMetadata> dlFileEntryMetadata = DLFileEntryMetadataLocalServiceUtil
					.getFileVersionFileEntryMetadatas(file.getFileVersion().getFileVersionId());
			for (DLFileEntryMetadata dlFileEntryMetadata2 : dlFileEntryMetadata) {

//				DDMContent contenido = DDMContentLocalServiceUtil.getContent(dlFileEntryMetadata2.getDDMStorageId());
				//_log.info("Contenido metadata " +contenido.getData());
				
				DDMFormValues ddmFormValues = StorageEngineManagerUtil
						.getDDMFormValues(dlFileEntryMetadata2.getDDMStorageId());
				List<DDMFormFieldValue> ddmFormFieldValues = ddmFormValues.getDDMFormFieldValues();
				if (Validator.isNotNull(ddmFormFieldValues) && !ddmFormFieldValues.isEmpty()) {
					for (DDMFormFieldValue formfieldValue : ddmFormFieldValues) {
						if (formfieldValue.getName().equalsIgnoreCase(nombreCampo)) {
							data = formfieldValue.getValue().getString(td.getLocale());
						}
					}
				}

			}
			return data;
			
		} catch (Exception e) {
			return "";
		}
	}
	
	public void generarTitulo(PortletRequest request) {
		request.setAttribute("titulo", _listadoConfig.titulo());
	}

	private String generarFechaMetadata(String fecha) {
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

	public Comparator<ListadoDocumentos> compareByFechaCreacion = new Comparator<ListadoDocumentos>() {

		@Override
		public int compare(ListadoDocumentos documentoUno, ListadoDocumentos documentoDos) {

			Long tituloUno = Long.parseLong(documentoUno.getNombreListado().replaceAll("\\D+",""));
			Long tituloDos = Long.parseLong(documentoDos.getNombreListado().replaceAll("\\D+",""));

			int compareValue = tituloUno.compareTo(tituloDos);

			if (compareValue == 0) {
				return 0;
			} else if (compareValue > 0) {
				return -1;
			} else {
				return 1;
			}
		}

	};

	public Comparator<ListadoDocumentos> compareName = new Comparator<ListadoDocumentos>() {

		@Override
		public int compare(ListadoDocumentos documentoUno, ListadoDocumentos documentoDos) {

			String tituloUno = documentoUno.getNombreListado();
			String tituloDos = documentoDos.getNombreListado();

			int compareValue = tituloUno.compareTo(tituloDos);

			if (compareValue == 0) {
				return 0;
			} else if (compareValue > 0) {
				return -1;
			} else {
				return 1;
			}
		}

	};

}
