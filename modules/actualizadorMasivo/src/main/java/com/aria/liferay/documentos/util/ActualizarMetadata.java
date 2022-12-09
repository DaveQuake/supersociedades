package com.aria.liferay.documentos.util;

import org.osgi.service.component.annotations.Component;

import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.model.DLFolder;
import com.liferay.document.library.kernel.service.DLAppLocalService;
import com.liferay.document.library.kernel.service.DLAppServiceUtil;

import com.liferay.document.library.kernel.service.DLFileEntryLocalServiceUtil;
import com.liferay.document.library.kernel.service.DLFolderLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.security.auth.PrincipalThreadLocal;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.PermissionCheckerFactoryUtil;
import com.liferay.portal.kernel.security.permission.PermissionThreadLocal;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.PortalUtil;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


/**
 * @author GobiernoDatos
 */
@Component(
	immediate = true,
	property = {
			"osgi.command.function=setDate",
			"osgi.command.function=getRoleIdByName",
			"osgi.command.scope=custom"

	},
	service = Object.class
	)
public class ActualizarMetadata  {

	public void setDate() {

		
		Role adminRole = null;

		try {
			adminRole = RoleLocalServiceUtil.getRole(PortalUtil.getDefaultCompanyId(), "Administrator");

			List<User> adminUsers = UserLocalServiceUtil.getRoleUsers(adminRole.getRoleId());
			long userId = 37003;

			if (adminUsers != null && !adminUsers.isEmpty()) {
				userId = adminUsers.get(0).getUserId();
			}

			User user = UserLocalServiceUtil.getUser(userId);
			_log.info("valor de userId "+userId);
			
			_log.info("valor de user Group ID "+user.getGroupId());
			
			PrincipalThreadLocal.setName(userId);
            PermissionChecker checker = PermissionCheckerFactoryUtil.create(user);
			PermissionThreadLocal.setPermissionChecker(checker);
			
		} catch (PortalException e3) {
			_log.error("Error en revisiond e usuario adminsitrator");
			_log.error(e3);
		}


		
		List<FileEntry> documentosLiferay = new ArrayList<>(); 
		try {
			//Carga en memoria la lista de archivos de liferay
			List<DLFolder> dlFolders = DLFolderLocalServiceUtil.getDLFolders(0, DLFolderLocalServiceUtil.getDLFoldersCount());
			_log.info("recorrido de todos los archivos");
			for (DLFolder folder : dlFolders) {
				_log.info("folderid "+folder.getFolderId());
				try {
					List<FileEntry> ArchivosLiferay = DLAppServiceUtil.getFileEntries(39286 , folder.getFolderId());
	
					for(FileEntry file: ArchivosLiferay) {
						documentosLiferay.add(file); 
						_log.info("archivoLiferay "+file.getFileEntryId());
					}
				}
				catch (Exception e) {
					_log.error(e.getMessage());
				}
			}
			
			
			
			//busqueda del archivo de cambios
			
			List<FileEntry> newFiles2  = DLAppServiceUtil.getFileEntries(39286 , 0);
			for(FileEntry file: newFiles2) {
				documentosLiferay.add(file); 
				_log.info("files "+file.getFileEntryId());
			}

			for (FileEntry archivoLiferay : documentosLiferay) {
				_log.info("archivo en lista "+archivoLiferay.getFileName());
			}
			
			
			FileEntry fileEntry = null; 
			fileEntry = DLAppServiceUtil.getFileEntry(39286, 0,	"DLFILEENTRY");
			

			if(fileEntry != null) {
				
				List<String >textElements = getContentFile(fileEntry); 

				List<String> nameFiles = new ArrayList<>(); 
				List<Date> dateFiles = new ArrayList<>(); 
				List<String> dateStringFiles = new ArrayList<>(); 
				int cont = 0; 

				for (String string : textElements) {
					if(cont%2 == 0) {
						//System.out.print(cont+" "+string + " ");
						nameFiles.add(string); 
					}else {

						SimpleDateFormat oldFomart = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss"); 

						Date newDate =  oldFomart.parse(string); 
						newDate.setYear(newDate.getYear() + 2000);

						dateFiles.add(newDate);  
						dateStringFiles.add(string); 
						//System.out.println(newDate);
					}
					cont++; 

				}
				_log.info("Cantidad de nombres y de fechas "+nameFiles.size() +" "+ dateFiles.size());

				int cantidad = 0;
				for (FileEntry archivoLiferay : documentosLiferay) {
					
					int contador = 0; 
					
					for (int i=0; i < nameFiles.size(); i++) {
						String name = nameFiles.get(i); 
						if(archivoLiferay.getFileName().equalsIgnoreCase(name)) {
							
							DLFileEntry fileNew =  DLFileEntryLocalServiceUtil.getDLFileEntry(archivoLiferay.getFileEntryId()); 
							
							
							
							fileNew.setCreateDate(dateFiles.get(contador));
							fileNew.setModifiedDate(dateFiles.get(contador));
							fileNew.setLastPublishDate(dateFiles.get(contador));
							
							fileNew.persist();
							
							cantidad++; 
							
							_log.info("Se ajusto el archivo "+archivoLiferay.getFileName());
							_log.info("Archivos ajustados "+cantidad);
						}
						contador++; 
					}
					
				}
				_log.info("Cantidad de archivos recorridos "+documentosLiferay.size());
				_log.info("Cantidad de fechas ajustadas Total "+cantidad);
			}
		} catch(Exception e) {
			_log.error("Error: " + e.getMessage());
			_log.error(e);
		}

	}



	@SuppressWarnings("finally")
	public List<String> getContentFile(FileEntry fileEntry) {
		String[] textElements = null; 
		List<String> arreglo = new ArrayList<String>(); 
		try {

			if(fileEntry != null) {
				InputStream result = null;
				result = DLFileEntryLocalServiceUtil.getFileAsStream(
						fileEntry.getFileEntryId(),
						fileEntry.getVersion()
						);
				String text = new BufferedReader(
						new InputStreamReader(result, StandardCharsets.UTF_8))
						.lines()
						.collect(Collectors.joining("\n")); 
				textElements = text.split("¬");
				for (String string : textElements) {
					string = string.replace("\n", ""); 
					arreglo.add(string); 
				}
				_log.info("Total en el portal: " + arreglo.size());
			}


		} catch (Exception e) {
			_log.error(e); 
		} 
		finally { 
			return arreglo;
		} 

	}
	
	
	public long getRoleIdByName()  {
		String roleName = "Account Administrator"; 
		if (roleName != null && !roleName.isEmpty()) {
			for (Role role : RoleLocalServiceUtil.getRoles(0, RoleLocalServiceUtil.getRolesCount())) {

				if (role.getName().equals(roleName)) {
					return role.getRoleId();
				}
			}
		}
		return -1;
	}
	
	private static final Log _log = LogFactoryUtil.getLog(ActualizarMetadata.class);

}