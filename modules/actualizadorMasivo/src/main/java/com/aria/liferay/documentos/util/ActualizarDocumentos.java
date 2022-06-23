package com.aria.liferay.documentos.util;

import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.document.library.content.model.DLContent;
import com.liferay.document.library.content.service.DLContentLocalServiceUtil;
import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.model.DLFileEntryMetadata;
import com.liferay.document.library.kernel.model.DLFileEntryType;
import com.liferay.document.library.kernel.model.DLFolder;
import com.liferay.document.library.kernel.model.DLVersionNumberIncrease;
import com.liferay.document.library.kernel.service.DLAppLocalServiceUtil;
import com.liferay.document.library.kernel.service.DLAppServiceUtil;
import com.liferay.document.library.kernel.service.DLFileEntryLocalServiceUtil;
import com.liferay.document.library.kernel.service.DLFileEntryMetadataLocalServiceUtil;
import com.liferay.document.library.kernel.service.DLFileEntryTypeLocalServiceUtil;
import com.liferay.document.library.kernel.service.DLFolderLocalServiceUtil;
import com.liferay.document.library.kernel.service.persistence.DLFileEntryMetadataUtil;
import com.liferay.dynamic.data.mapping.kernel.DDMFormFieldValue;
import com.liferay.dynamic.data.mapping.kernel.DDMFormValues;
import com.liferay.dynamic.data.mapping.kernel.DDMStructure;
import com.liferay.dynamic.data.mapping.kernel.LocalizedValue;
import com.liferay.dynamic.data.mapping.kernel.StorageEngineManagerUtil;
import com.liferay.dynamic.data.mapping.kernel.Value;
import com.liferay.dynamic.data.mapping.model.DDMStorageLink;
import com.liferay.dynamic.data.mapping.service.DDMStorageLinkLocalServiceUtil;
import com.liferay.dynamic.data.mapping.service.DDMStructureLocalServiceUtil;
import com.liferay.dynamic.data.mapping.storage.Field;
import com.liferay.dynamic.data.mapping.storage.Fields;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.security.auth.PrincipalThreadLocal;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.PermissionCheckerFactoryUtil;
import com.liferay.portal.kernel.security.permission.PermissionThreadLocal;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.io.IOUtils;
import org.osgi.service.component.annotations.Component;

/**
 * @author Asus
 */
@Component(
	immediate = true,
	property = {
			"osgi.command.function=actualizarDocumentos",
			"osgi.command.scope=custom"
	},
	service = Object.class
)
public class ActualizarDocumentos {
	
	Long sitio = 20121L;
	Long tipoDocumental=37931L;
	Long companyId=20097L;

	@SuppressWarnings("static-access")
	public void actualizarDocumentos() {

		User user = authentication();
		
		FileEntry metadatos = getArchivoMetadatos();
		
		List<String> arrayfilas = getContentFile(metadatos);
		
		for (String string : arrayfilas) {
			_log.info("Linea de archivo "+string);
		}
		
		modificarArchivos(user, arrayfilas);
		
	
	}
	
	@SuppressWarnings("static-access")
	private void modificarArchivos(User user, List<String> arrayfilas) {
		
		int totalarchivos = arrayfilas.size();
		try {
			List<FileEntry> arrayArchivosPortal = new ArrayList<FileEntry>();
			
			List<DLFolder> dlFolders = DLFolderLocalServiceUtil.getDLFolders(0, DLFolderLocalServiceUtil.getDLFoldersCount());
			for (DLFolder folder : dlFolders) {
				List<FileEntry> newFiles = DLAppServiceUtil.getFileEntries(sitio, folder.getFolderId()); // local
				for (FileEntry file : newFiles) {
					
					if(arrayfilas.size()<1) {
						break;
					}
					_log.info("--------------------------------------------------------------------------------");
					_log.info(" archivos pendientes para modificar "+arrayfilas.size()+" de "+totalarchivos);
					_log.info("lectura de archivo portal "+file.getFileEntryId() +","+file.getFileName()+","+file.getDescription()+","+file.getTitle());
	//				arrayArchivosPortal.add(file);
					
					int indice=0;
					for (String string : arrayfilas) {
						String[] valor = string.split(";");
						String tipoDocumento = valor[0].replace("\"", "");
						String titulo = valor[2].replace("\"", "");
						String fileName = valor[1].replace("\"", "");
						String descripcion = valor[5].replace("\"", "");
						
						_log.info("informacion de la fila " + tipoDocumento+","+ fileName+","+ descripcion);
						
						if(file.getFileName().equals(fileName)) {
							_log.info("se encontro el archivo,"+file.getFileName()+","+file.getFileEntryId());
							
							ServiceContext serviceContext = new ServiceContext();
							serviceContext.setUserId(user.getUserId());
							
							
							//Actualiza documento
							byte[] bytes = IOUtils.toByteArray(file.getContentStream());
							DLAppServiceUtil.updateFileEntry(file.getFileEntryId(), file.getFileName(),file.getMimeType(),titulo,descripcion, null, DLVersionNumberIncrease.NONE, bytes , serviceContext);
							
							
							arrayfilas.remove(indice);
							break;
						}
						indice++;
					}
					
				}
			}
			
	//		for (FileEntry arch : arrayArchivosPortal) {
	//			_log.info("archivo "+arch.getFileEntryId() +" "+arch.getFileName()+" "+arch.getDescription());
	//		}
	//		
		}catch (Exception e) {
			_log.error(e);
		}
		
		
	}
	
 	public List<String> getContentFile2(FileEntry fileEntry) {
		String SEPARATOR=";";
		BufferedReader br = null;
		List<String> lista=new ArrayList<String>();
	      try {
	    	  InputStream result = DLFileEntryLocalServiceUtil.getFileAsStream(fileEntry.getFileEntryId(),
						fileEntry.getVersion());
	         br =new BufferedReader(new InputStreamReader(result, StandardCharsets.UTF_8));
	         String line = br.readLine();
	         while (null!=line) {
	            String [] fields = line.split(SEPARATOR);
	            System.out.println(Arrays.toString(fields));
	            
	            fields = removeTrailingQuotes(fields);
	            System.out.println(Arrays.toString(fields));
	            
	            line = br.readLine();
	            lista.add(Arrays.toString(fields));
	         
	         
	      }
	      }catch (Exception e) {
	    	  _log.error(e);
	    	  
	      } finally {
	        
	            try {
					br.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        
	      }
	      return lista;    
	}
	
	private String[] removeTrailingQuotes(String[] fields) {
			String QUOTE="\"";
	      String result[] = new String[fields.length];

	      for (int i=0;i<result.length;i++){
	         result[i] = fields[i].replaceAll("^"+QUOTE, "").replaceAll(QUOTE+"$", "");
	      }
	      return result;
	   }
	
	public List<String> getContentFile(FileEntry fileEntry) {
		String[] textElements = null;
		InputStream result = null;
		List<String> arreglo = new ArrayList<String>();
		try {
			if (fileEntry != null) {
				result = DLFileEntryLocalServiceUtil.getFileAsStream(fileEntry.getFileEntryId(),
						fileEntry.getVersion());
				String text = new BufferedReader(new InputStreamReader(result, StandardCharsets.UTF_8)).lines().collect(Collectors.joining("\n"));
				textElements = text.split("\n");
				for (String string : textElements) {
					arreglo.add(string);
				}
			}
		} catch (Exception e) {
			_log.error("Error getContentFile(): " + e.getMessage());
		}
		return arreglo;

	}
	
	private FileEntry getArchivoMetadatos() {
		
		Folder folder;
		try {
			folder = DLAppServiceUtil.getFolder(sitio, 0L, "metadatos");
			_log.info("folder del archivo de metadatos "+folder.getFolderId());
		
			FileEntry metadatos = DLAppServiceUtil.getFileEntry(sitio, folder.getFolderId(), "metadatos.csv");
			_log.info("codigo del archivo de metadatos "+metadatos.getFileEntryId());
			
			return metadatos;
			
		} catch (PortalException e) {
			_log.error(e);
			return null;
		}
		
	}
	
	private User authentication() {
		
		_log.info("se habilitan permisos admin");
		
		Role adminRole = null;
	
		try {
			adminRole = RoleLocalServiceUtil.getRole(PortalUtil.getDefaultCompanyId(), "Administrator");
	
			List<User> adminUsers = UserLocalServiceUtil.getRoleUsers(adminRole.getRoleId());
			long userId = 20129;
	
			if (adminUsers != null && !adminUsers.isEmpty()) {
				userId = adminUsers.get(0).getUserId();
			}
	
			User user = UserLocalServiceUtil.getUser(userId);
			_log.info("valor de userId "+userId);
			
			PrincipalThreadLocal.setName(userId);
			PermissionChecker permissionChecker = PermissionCheckerFactoryUtil.create(UserLocalServiceUtil.getUser(userId));
			PermissionThreadLocal.setPermissionChecker(permissionChecker);

			return user;
			
		} catch (PortalException e3) {
			_log.error("Error en revision de usuario adminsitrator");
			_log.error(e3);
			return null;
		}
	
	}
	
	private static final Log _log = LogFactoryUtil.getLog(ActualizarDocumentos.class);
}