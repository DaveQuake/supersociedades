package com.aria.liferay.documentos.util;

import com.liferay.calendar.model.Calendar;
import com.liferay.document.library.kernel.model.DLFileEntryMetadata;
import com.liferay.document.library.kernel.model.DLFolder;
import com.liferay.document.library.kernel.model.DLVersionNumberIncrease;
import com.liferay.document.library.kernel.service.DLAppServiceUtil;
import com.liferay.document.library.kernel.service.DLFileEntryLocalServiceUtil;
import com.liferay.document.library.kernel.service.DLFileEntryMetadataLocalServiceUtil;
import com.liferay.document.library.kernel.service.DLFolderLocalServiceUtil;
import com.liferay.document.library.kernel.service.persistence.DLFileEntryMetadataUtil;
import com.liferay.dynamic.data.mapping.kernel.DDMFormFieldValue;
import com.liferay.dynamic.data.mapping.kernel.DDMFormValues;
import com.liferay.dynamic.data.mapping.kernel.StorageEngineManagerUtil;
import com.liferay.dynamic.data.mapping.kernel.Value;
import com.liferay.dynamic.data.mapping.model.DDMContent;
import com.liferay.dynamic.data.mapping.service.DDMContentLocalServiceUtil;
import com.liferay.dynamic.data.mapping.service.DDMStructureLocalServiceUtil;
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
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.exceptions.CsvException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.nio.file.StandardCopyOption;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
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
			"osgi.command.function=listaEstructuras",
			"osgi.command.scope=custom"
	},
	service = Object.class
)
public class ActualizarDocumentos {
	
	//Long sitio = 20121L;
	//Long tipoDocumental=37931L;
	//Long companyId=20097L;
	//Long structureId= 38008L;

	@SuppressWarnings("static-access")
	public void actualizarDocumentos(String sitio) {

		User user = authentication();
		_log.info("sitio recibido "+sitio);
		
		FileEntry metadatos = getArchivoMetadatos(Long.valueOf(sitio));
		
		List<ConceptosJuridicos> arrayMetadatos = getContentFile(metadatos);
		
		revisarArchivoss(user, Long.valueOf(sitio), arrayMetadatos);
	
	}
	
	
	@SuppressWarnings("static-access")
	private void revisarArchivoss(User user, Long sitio, List<ConceptosJuridicos> arrayMetadatos) {
		
		//listaEstructuras();
		
		List<FileEntry> archivosPortal = new ArrayList<>();
		
		_log.info("cantidad registros de metadatos "+arrayMetadatos.size());
		
		List<DLFolder> dlFolders = DLFolderLocalServiceUtil.getDLFolders(0, DLFolderLocalServiceUtil.getDLFoldersCount());
		_log.info("cantidad de carpetas "+dlFolders.size());
		
		try {
			for (DLFolder folder : dlFolders) {
				_log.info("carpeta "+folder.getName());
				List<FileEntry> newFiles = DLAppServiceUtil.getFileEntries(sitio, folder.getFolderId()); // local
				_log.info("cantidad archivos en la carpeta "+newFiles.size());
				_log.info("--------------------------------------------------------------------------------");
				for (FileEntry file : newFiles) {
					_log.info("lectura de archivo portal "+file.getFileEntryId() +","+file.getFileName()+","+file.getDescription()+","+file.getTitle()+","+file.getFileVersion());
					archivosPortal.add(file);
				}
			}
			_log.info("cantidad archivos en el portal "+archivosPortal.size());
		}catch (Exception e) {
			_log.error(e);
		}
		
				
		try {		
			ServiceContext serviceContext = new ServiceContext();
			serviceContext.setUserId(user.getUserId());
			int i=1;
			for (ConceptosJuridicos registroMetadato : arrayMetadatos) {
				_log.info("validacion registro "+i+" de "+arrayMetadatos.size());
				for (FileEntry file : archivosPortal) {
					
					if(file.getFileName().equalsIgnoreCase(registroMetadato.getNombre()) || file.getTitle().equalsIgnoreCase(registroMetadato.getTitulo())) {
						_log.info("encontro el archivo "+file.getFileEntryId()+","+file.getFileName());
						try {
							List<DLFileEntryMetadata> ListFileEntryMeta = DLFileEntryMetadataLocalServiceUtil.getFileVersionFileEntryMetadatas(file.getFileVersion().getFileVersionId());
							for (DLFileEntryMetadata metadato : ListFileEntryMeta) {
								Long storageid = metadato.getDDMStorageId();
								DDMContent contenido = DDMContentLocalServiceUtil.getContent(storageid);
								_log.info("Contenido metadata " +contenido.getData());
								
								DDMFormValues ddmFormValues = StorageEngineManagerUtil.getDDMFormValues(metadato.getDDMStorageId());
								
								List<DDMFormFieldValue> ddmFormFieldValues = ddmFormValues.getDDMFormFieldValues();
								
								if (Validator.isNotNull(ddmFormFieldValues) && !ddmFormFieldValues.isEmpty()) {
									for (DDMFormFieldValue formfieldValue : ddmFormFieldValues) {
										
										if (formfieldValue.getName().equalsIgnoreCase("DescripcionMetaMetadatoSEO")) {
											_log.info("valor de DescripcionMetaMetadatoSEO " +formfieldValue.getValue().getString(LocaleUtil.SPAIN));
											Value valor = formfieldValue.getValue();
											valor.addString(LocaleUtil.SPAIN, registroMetadato.getEpigrafe());
											formfieldValue.setValue(valor);
											_log.info("nuevo valor de DescripcionMetaMetadatoSEO " +formfieldValue.getValue().getString(LocaleUtil.SPAIN));
											
										}
										if (formfieldValue.getName().equalsIgnoreCase("FechaExpedicionGlobal")) {
											_log.info("valor de FechaExpedicionGlobal " +formfieldValue.getValue().getString(LocaleUtil.SPAIN));
											Value valor = formfieldValue.getValue();
											String patternLiferay = "yyyy-MM-dd HH:mm";
											String patternArchivo = "dd/MM/yyyy HH:mm";
											SimpleDateFormat simpleDateFormatLiferay = new SimpleDateFormat(patternLiferay);
											SimpleDateFormat simpleDateFormatArchivo = new SimpleDateFormat(patternArchivo);
											Date date = simpleDateFormatArchivo.parse(registroMetadato.getFechaExpedicion());
											_log.info("fecha parseada " +date);
											valor.addString(LocaleUtil.SPAIN, simpleDateFormatLiferay.format(date));
											formfieldValue.setValue(valor);
											_log.info("nuevo valor de FechaExpedicionGlobal " +formfieldValue.getValue().getString(LocaleUtil.SPAIN));
										}
										if (formfieldValue.getName().equalsIgnoreCase("Anno")) {
											_log.info("valor de Anno " +formfieldValue.getValue().getString(LocaleUtil.SPAIN));
											Value valor = formfieldValue.getValue();
											valor.addString(LocaleUtil.SPAIN, registroMetadato.getAnioDocumento());
											formfieldValue.setValue(valor);
											_log.info("nuevo valor de Anno " +formfieldValue.getValue().getString(LocaleUtil.SPAIN));
											
										}
										if (formfieldValue.getName().equalsIgnoreCase("PalabrasClaveMetadatoSEO")) {
											_log.info("valor de PalabrasClaveMetadatoSEO " +formfieldValue.getValue().getString(LocaleUtil.SPAIN));
											Value valor = formfieldValue.getValue();
											valor.addString(LocaleUtil.SPAIN, registroMetadato.getPalabrasClave());
											formfieldValue.setValue(valor);
											_log.info("nuevo valor de PalabrasClaveMetadatoSEO " +formfieldValue.getValue().getString(LocaleUtil.SPAIN));
											
										}
										if (formfieldValue.getName().equalsIgnoreCase("FechaDePublicacion")) {
											_log.info("valor de FechaDePublicacion " +formfieldValue.getValue().getString(LocaleUtil.SPAIN));
											Value valor = formfieldValue.getValue();
											String patternLiferay = "yyyy-MM-dd HH:mm";
											String patternArchivo = "dd/MM/yyyy HH:mm";
											SimpleDateFormat simpleDateFormatLiferay = new SimpleDateFormat(patternLiferay);
											SimpleDateFormat simpleDateFormatArchivo = new SimpleDateFormat(patternArchivo);
											Date date = simpleDateFormatArchivo.parse(registroMetadato.getFechaPublicacion());
											_log.info("fecha parseada " +date);
											valor.addString(LocaleUtil.SPAIN, simpleDateFormatLiferay.format(date));
											formfieldValue.setValue(valor);
											_log.info("nuevo valor de FechaDePublicacion " +formfieldValue.getValue().getString(LocaleUtil.SPAIN));
										}
										if (formfieldValue.getName().equalsIgnoreCase("TituloExploradorMetadatoSEO")) {
											_log.info("valor de TituloExploradorMetadatoSEO " +formfieldValue.getValue().getString(LocaleUtil.SPAIN));
											Value valor = formfieldValue.getValue();
											valor.addString(LocaleUtil.SPAIN, registroMetadato.getTitulo());
											formfieldValue.setValue(valor);
											_log.info("nuevo valor de TituloExploradorMetadatoSEO " +formfieldValue.getValue().getString(LocaleUtil.SPAIN));
											
										}
									}
								}
								StorageEngineManagerUtil.update(metadato.getDDMStorageId(), ddmFormValues, serviceContext);
								
								contenido = DDMContentLocalServiceUtil.getContent(storageid);
								_log.info("Content despues de cambio " +contenido.getData());
								
							}
							
						}catch (Exception e) {
							_log.error(e);
						}
						
						//byte[] bytes = IOUtils.toByteArray(file.getContentStream());
						//DLAppServiceUtil.updateFileEntry(file.getFileEntryId(), file.getFileName(),file.getMimeType(),registroMetadato.getTitulo(),
						//		registroMetadato.getEpigrafe(), "", DLVersionNumberIncrease.NONE, bytes , serviceContext);

					}
					
				}
				i++;
			}

			
		}catch (Exception e) {
			_log.error(e.getMessage());
		}
		
	}
	
	public String getMetaData(FileEntry file, String nombreCampo, ServiceContext serviceContext) {
		String metadata = "";
		
		try {
			List<DLFileEntryMetadata> dlFileEntryMetadata = DLFileEntryMetadataLocalServiceUtil.getFileVersionFileEntryMetadatas(file.getFileVersion().getFileVersionId());
			for (DLFileEntryMetadata dlFileEntryMetadata2 : dlFileEntryMetadata) {

				DDMFormValues ddmFormValues = StorageEngineManagerUtil.getDDMFormValues(dlFileEntryMetadata2.getDDMStorageId());
				List<DDMFormFieldValue> ddmFormFieldValues = ddmFormValues.getDDMFormFieldValues();
				if (Validator.isNotNull(ddmFormFieldValues) && !ddmFormFieldValues.isEmpty()) {
					for (DDMFormFieldValue formfieldValue : ddmFormFieldValues) {
						if (formfieldValue.getName().equalsIgnoreCase(nombreCampo)) {
//							Value valor = formfieldValue.getValue();
//							valor.addString(LocaleUtil.SPAIN, "Nyevo valor de epigrafe");
							metadata = formfieldValue.getValue().getString(LocaleUtil.SPAIN);
							
//							formfieldValue.setValue(valor);
							
							
						}
					}
				}
				
//				StorageEngineManagerUtil.update(structureId, ddmFormValues, serviceContext);
			}
			return metadata;
			
		} catch (Exception e) {
			return "";
		}
	}
	
	private void listaEstructuras() {
		List<com.liferay.dynamic.data.mapping.model.DDMStructure>estructura = DDMStructureLocalServiceUtil.getStructures();
		_log.info("estrucutras "+estructura.toString());
		
		_log.info("------");
		for (com.liferay.dynamic.data.mapping.model.DDMStructure seri : estructura) {
			_log.info(seri.toString());
		}
		_log.info("------");
		
	}
	
 	public List<ConceptosJuridicos> getContentFile(FileEntry fileEntry) {
		
		List<ConceptosJuridicos> beans = new ArrayList<ConceptosJuridicos>();
		
		InputStream initialStream;
		try {
			initialStream = fileEntry.getContentStream();
		
			File targetFile = new File("C:\\metadatos\\archivo.csv");
	
			copyInputStreamToFile(fileEntry.getContentStream(), targetFile);
			 
			 _log.info("Tamanio de archivo "+targetFile.length());
	
	 	     beans = new CsvToBeanBuilder(new FileReader(targetFile)).withType(ConceptosJuridicos.class).withSeparator(';').build().parse();
	 	     
	 	     _log.info("Lineas de archivo "+beans.size());
	 	     for(ConceptosJuridicos nextLine : beans) {
	 	       
	 	        _log.info(nextLine.toString());
	 	     }
	 	    
		} catch (IOException | PortalException e1) {
			e1.printStackTrace();
		}
		
		return beans;
	}
	
	private FileEntry getArchivoMetadatos(Long sitio) {
		
		Folder folder;
		try {
			folder = DLAppServiceUtil.getFolder(sitio, 0L, "metadatos");
			_log.info("folder del archivo de metadatos "+folder.getFolderId());
		
			FileEntry metadatos = DLAppServiceUtil.getFileEntry(sitio, folder.getFolderId(), "conceptosJur.csv");
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
			long userId = 37003;
	
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
	
	private void copyInputStreamToFile(InputStream inputStream, File file)
	            throws IOException {

	        // append = false
	        try (FileOutputStream outputStream = new FileOutputStream(file, false)) {
	            int read;
	            byte[] bytes = new byte[4000];
	            while ((read = inputStream.read(bytes)) != -1) {
	                outputStream.write(bytes, 0, read);
	            }
	        }

	    }
	
	private static final Log _log = LogFactoryUtil.getLog(ActualizarDocumentos.class);
}