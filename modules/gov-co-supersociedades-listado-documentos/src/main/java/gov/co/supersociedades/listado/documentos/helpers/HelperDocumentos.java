package gov.co.supersociedades.listado.documentos.helpers;

import com.liferay.document.library.kernel.service.DLAppServiceUtil;
import com.liferay.document.library.kernel.util.DLUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Validator;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.portlet.PortletPreferences;
import javax.portlet.PortletRequest;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;

import gov.co.supersociedades.listado.documentos.configuration.ListadoConfiguration;
import gov.co.supersociedades.listado.documentos.constants.Constantes;
import gov.co.supersociedades.listado.documentos.models.Documento;
import gov.co.supersociedades.listado.documentos.models.ListadoDocumentos;

@Component(immediate = true, configurationPid = "gov_co_supersociedades_ListarDocumentosConfiguration", service = HelperDocumentos.class)
public class HelperDocumentos {
	
	private static final Log _log = LogFactoryUtil.getLog(HelperDocumentos.class);
	private volatile ListadoConfiguration _listadoConfig;
	private String PATRON = "dd-MM-yyyy";
	
	@Activate
    @Modified
    protected void activate(Map<Object, Object> properties) {
		_listadoConfig = ConfigurableUtil.createConfigurable(ListadoConfiguration.class, properties);
    }
	
	public List<ListadoDocumentos> generarListado(ThemeDisplay td, PortletPreferences prefs) {
		List<ListadoDocumentos> listadoDoc = new ArrayList<ListadoDocumentos>();
		
		if(Validator.isNotNull(prefs.getValue(Constantes.ID_CARPETA, StringPool.BLANK))) {
			try {
				Folder folderPadre = DLAppServiceUtil.getFolder(Long.parseLong(prefs.getValue(Constantes.ID_CARPETA, StringPool.BLANK)));
				List<Folder> carpetasInternas = DLAppServiceUtil.getFolders(folderPadre.getRepositoryId(), folderPadre.getFolderId());
				if(!carpetasInternas.isEmpty()) {
					for (Folder folder : carpetasInternas) {
						List<FileEntry> listFile = DLAppServiceUtil.getFileEntries(folder.getGroupId(),folder.getFolderId());
						if(!listFile.isEmpty()) {
							listadoDoc.add(getListadoDocumentos(listFile, folder, td));
						}
					}
				}else {
					List<FileEntry> listFile = DLAppServiceUtil.getFileEntries(folderPadre.getGroupId(),folderPadre.getFolderId());
					if(!listFile.isEmpty()) {
						listadoDoc.add(getListadoDocumentos(listFile, folderPadre, td));
					}
				}
			} catch (Exception e) {
				_log.error("Error en el proceso de busqueda documentos: ", e);
			}
		}		
		
		return listadoDoc;
	}
	
	private ListadoDocumentos getListadoDocumentos(List<FileEntry> listFile, Folder folder, ThemeDisplay td) {
		ListadoDocumentos listado = new ListadoDocumentos();
		listado.setNombreListado(folder.getName());
		listado.setId(String.valueOf(folder.getFolderId()));
		if (listFile.size() > 0) {
			List<Documento> listDoc = new ArrayList<Documento>();
			for (FileEntry files : listFile) {
				try {
					Documento doc = new Documento();
					String url = DLUtil.getPreviewURL(files, files.getLatestFileVersion(), td, StringPool.BLANK);
					doc.setNombreDocumento(files.getTitle());
					doc.setDescripcion(files.getDescription());
					SimpleDateFormat simpleDateFormat = new SimpleDateFormat(PATRON);
					doc.setExtencion(files.getExtension());
					String date = simpleDateFormat.format(files.getModifiedDate());
					doc.setFecha(generarFecha(date));
					doc.setUrlDocumento(url);
					listDoc.add(doc);
				} catch (Exception e) {
					continue;
				}
			}
			listado.setDocumentos(listDoc);
		}
		
		return listado;
	}

	public void generarTitulo(PortletRequest request) {
		request.setAttribute("titulo", _listadoConfig.titulo());
	}
	
	private String generarFecha(String fecha) {
		String[] fechaSplit = fecha.split("-");
		String mes = generarMes(fechaSplit[1]);
		
		fecha = mes + ". " + fechaSplit[0] + ", " +fechaSplit[2];
		return fecha;
	}	
	
	
	private String generarMes(String mes) {
		String mesString = "";
		
		if(mes.equalsIgnoreCase("01") || mes.equalsIgnoreCase("1")) {
			mesString = "Ene";
		}
		
		if(mes.equalsIgnoreCase("02") || mes.equalsIgnoreCase("2")) {
			mesString = "Feb";
		}
		

		if(mes.equalsIgnoreCase("03") || mes.equalsIgnoreCase("3")) {
			mesString = "Mar";
		}
		
		if(mes.equalsIgnoreCase("04") || mes.equalsIgnoreCase("4")) {
			mesString = "Abr";
		}
		
		if(mes.equalsIgnoreCase("05") || mes.equalsIgnoreCase("5")) {
			mesString = "May";
		}
		
		if(mes.equalsIgnoreCase("06") || mes.equalsIgnoreCase("6")) {
			mesString = "Jun";
		}
		
		if(mes.equalsIgnoreCase("07") || mes.equalsIgnoreCase("7")) {
			mesString = "Jul";
		}
		
		if(mes.equalsIgnoreCase("08") || mes.equalsIgnoreCase("8")) {
			mesString = "Ago";
		}
		
		if(mes.equalsIgnoreCase("09") || mes.equalsIgnoreCase("9")) {
			mesString = "Sep";
		}
		
		if(mes.equalsIgnoreCase("10")) {
			mesString = "Oct";
		}
		
		if(mes.equalsIgnoreCase("11")) {
			mesString = "Nov";
		}
		
		if(mes.equalsIgnoreCase("12")) {
			mesString = "Dic";
		}
		
		return mesString;
		
	}
}
