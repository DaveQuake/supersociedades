package com.aria.liferay.documentos.util;

import com.aria.liferay.documentos.model.Insolvencia;
import com.liferay.asset.entry.rel.service.AssetEntryAssetCategoryRelLocalServiceUtil;
import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.service.AssetCategoryLocalServiceUtil;
import com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil;
import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.model.DLFileEntryMetadata;
import com.liferay.document.library.kernel.model.DLFolder;
import com.liferay.document.library.kernel.model.DLVersionNumberIncrease;
import com.liferay.document.library.kernel.service.DLAppServiceUtil;
import com.liferay.document.library.kernel.service.DLFileEntryLocalServiceUtil;
import com.liferay.document.library.kernel.service.DLFileEntryMetadataLocalServiceUtil;
import com.liferay.document.library.kernel.service.DLFolderLocalServiceUtil;
import com.liferay.dynamic.data.mapping.kernel.DDMFormFieldValue;
import com.liferay.dynamic.data.mapping.kernel.DDMFormValues;
import com.liferay.dynamic.data.mapping.kernel.LocalizedValue;
import com.liferay.dynamic.data.mapping.kernel.StorageEngineManagerUtil;
import com.liferay.dynamic.data.mapping.kernel.Value;
import com.liferay.dynamic.data.mapping.model.DDMContent;
import com.liferay.dynamic.data.mapping.service.DDMContentLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.Validator;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.osgi.service.component.annotations.Component;

/**
 * @author Asus
 */
@Component(
	immediate = true,
	property = {
			"osgi.command.function=actualizarInsolvencia",
			"osgi.command.function=actualizarCategorias",
			"osgi.command.scope=custom"
	},
	service = Object.class
)
public class ActualizarInsolvencia {
	
	//Long sitio = 20121L;

	public void actualizarInsolvencia(String companyId, String sitio) {
		
		Util util = new Util();
		User user = util.authentication();
		_log.info("sitio recibido "+sitio);
		
		FileEntry metadatos = util.getArchivoMetadatos(Long.valueOf(sitio), "insolvencia.csv");
		
		List<Insolvencia> arrayMetadatos = getContentFile(metadatos);
		
		actualizarDocumentos(user, Long.valueOf(companyId), Long.valueOf(sitio), arrayMetadatos);
	}
	
	public void actualizarCategorias(String companyId, String sitio) {
		
		Util util = new Util();
		User user = util.authentication();
		_log.info("sitio recibido "+sitio);
		
		FileEntry metadatos = util.getArchivoMetadatos(Long.valueOf(sitio), "insolvencia.csv");
		
		List<Insolvencia> arrayMetadatos = getContentFile(metadatos);
		
		actualizarCateg(user, Long.valueOf(companyId), Long.valueOf(sitio), arrayMetadatos);
	}
	
	private void actualizarDocumentos(User user, Long companyId, Long sitio, List<Insolvencia> arrayMetadatos) {
		
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
			for (Insolvencia registroMetadato : arrayMetadatos) {
				_log.info("validacion registro "+i+" de "+arrayMetadatos.size());
				
				Boolean encontroArchivo = Boolean.FALSE;
				
				for (FileEntry file : archivosPortal) {
					
					if(file.getFileName().equalsIgnoreCase(registroMetadato.getNombre()) || file.getTitle().equalsIgnoreCase(registroMetadato.getTitulo())) {
						encontroArchivo=Boolean.TRUE;
						_log.info("encontro el archivo "+file.getFileEntryId()+","+file.getFileName());
						try {
							
							Boolean tramite = Boolean.TRUE;
							Boolean FechaExpedicionGlobal = Boolean.TRUE;
							Boolean Anno = Boolean.TRUE;
							Boolean dependenciaOrigen = Boolean.TRUE;
							Boolean FechaDePublicacion = Boolean.TRUE;
							Boolean TituloExploradorMetadatoSEO = Boolean.TRUE;
							
							List<DLFileEntryMetadata> ListFileEntryMeta = DLFileEntryMetadataLocalServiceUtil.getFileVersionFileEntryMetadatas(file.getFileVersion().getFileVersionId());
							for (DLFileEntryMetadata metadato : ListFileEntryMeta) {
								Long storageid = metadato.getDDMStorageId();
								DDMContent contenido = DDMContentLocalServiceUtil.getContent(storageid);
								_log.info("Contenido metadata " +contenido.getData());
								
								DDMFormValues ddmFormValues = StorageEngineManagerUtil.getDDMFormValues(metadato.getDDMStorageId());
								ddmFormValues.setDefaultLocale(LocaleUtil.SPAIN);
								List<DDMFormFieldValue> ddmFormFieldValues = ddmFormValues.getDDMFormFieldValues();
								
								if (Validator.isNotNull(ddmFormFieldValues) && !ddmFormFieldValues.isEmpty()) {

									
// 										int j=0;
									for (DDMFormFieldValue formfieldValue : ddmFormFieldValues) {
// 											_log.info("vuelta del for " + ++j);
										
										if (formfieldValue.getName().equalsIgnoreCase("Tramite")) {
											_log.info("valor de tramite " +formfieldValue.getValue().getString(LocaleUtil.SPAIN));
											Value valor = formfieldValue.getValue();
											valor.addString(LocaleUtil.SPAIN, registroMetadato.getTramite());
											formfieldValue.setValue(valor);
											_log.info("nuevo valor de tramite " +formfieldValue.getValue().getString(LocaleUtil.SPAIN));
											tramite = Boolean.FALSE;
										}
										if (formfieldValue.getName().equalsIgnoreCase("FechaExpedicionGlobal")) {
											_log.info("valor de FechaExpedicionGlobal " +formfieldValue.getValue().getString(LocaleUtil.SPAIN));
											Value valor = formfieldValue.getValue();
											String patternLiferay = "yyyy-MM-dd";
											String patternArchivo = "dd/MM/yyyy";
											SimpleDateFormat simpleDateFormatLiferay = new SimpleDateFormat(patternLiferay);
											SimpleDateFormat simpleDateFormatArchivo = new SimpleDateFormat(patternArchivo);
											Date date = simpleDateFormatArchivo.parse(registroMetadato.getFechaExpedicion());
											_log.info("fecha parseada " +date);
											valor.addString(LocaleUtil.SPAIN, simpleDateFormatLiferay.format(date));
											formfieldValue.setValue(valor);
											_log.info("nuevo valor de FechaExpedicionGlobal " +formfieldValue.getValue().getString(LocaleUtil.SPAIN));
											FechaExpedicionGlobal = Boolean.FALSE;
										}
										if (formfieldValue.getName().equalsIgnoreCase("Anno")) {
											_log.info("valor de Anno " +formfieldValue.getValue().getString(LocaleUtil.SPAIN));
											Value valor = formfieldValue.getValue();
											valor.addString(LocaleUtil.SPAIN, registroMetadato.getAnioDocumento());
											formfieldValue.setValue(valor);
											_log.info("nuevo valor de Anno " +formfieldValue.getValue().getString(LocaleUtil.SPAIN));
											Anno = Boolean.FALSE;
										}
										if (formfieldValue.getName().equalsIgnoreCase("DependenciaOrigen")) {
											_log.info("valor de dependenciaOrigen " +formfieldValue.getValue().getString(LocaleUtil.SPAIN));
											Value valor = formfieldValue.getValue();
											valor.addString(LocaleUtil.SPAIN, registroMetadato.getDependenciaOrigen());
											formfieldValue.setValue(valor);
											_log.info("nuevo valor de dependenciaOrigen " +formfieldValue.getValue().getString(LocaleUtil.SPAIN));
											dependenciaOrigen = Boolean.FALSE;
										}
										if (formfieldValue.getName().equalsIgnoreCase("Fecha1g84")) {
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
										
											FechaDePublicacion = Boolean.FALSE;
										}
										if (formfieldValue.getName().equalsIgnoreCase("TituloExploradorMetadatoSEO")) {
											_log.info("valor de TituloExploradorMetadatoSEO " +formfieldValue.getValue().getString(LocaleUtil.SPAIN));
											Value valor = formfieldValue.getValue();
											valor.addString(LocaleUtil.SPAIN, registroMetadato.getTitulo());
											formfieldValue.setValue(valor);
											_log.info("nuevo valor de TituloExploradorMetadatoSEO " +formfieldValue.getValue().getString(LocaleUtil.SPAIN));
											TituloExploradorMetadatoSEO = Boolean.FALSE;
										}
									}
									
									if(FechaDePublicacion) {
										DDMFormFieldValue nuevoFFV = new DDMFormFieldValue();
										_log.info("creara el metadato FechaDePublicacion");
										Value valor = new LocalizedValue(LocaleUtil.SPAIN);
										String patternLiferay = "yyyy-MM-dd HH:mm";
										String patternArchivo = "dd/MM/yyyy HH:mm";
										SimpleDateFormat simpleDateFormatLiferay = new SimpleDateFormat(patternLiferay);
										SimpleDateFormat simpleDateFormatArchivo = new SimpleDateFormat(patternArchivo);
										Date date = simpleDateFormatArchivo.parse(registroMetadato.getFechaPublicacion());
										_log.info("fecha parseada " +date);
										valor.addString(LocaleUtil.SPAIN, simpleDateFormatLiferay.format(date));
										nuevoFFV.setName("Fecha1g84");
										nuevoFFV.setValue(valor);
										ddmFormValues.addDDMFormFieldValue(nuevoFFV);
									}
									if(FechaExpedicionGlobal) {
										DDMFormFieldValue nuevoFFV = new DDMFormFieldValue();
										_log.info("creara el metadato FechaExpedicionGlobal");
										Value valor = new LocalizedValue(LocaleUtil.SPAIN);
										String patternLiferay = "yyyy-MM-dd";
										String patternArchivo = "dd/MM/yyyy";
										SimpleDateFormat simpleDateFormatLiferay = new SimpleDateFormat(patternLiferay);
										SimpleDateFormat simpleDateFormatArchivo = new SimpleDateFormat(patternArchivo);
										Date date = simpleDateFormatArchivo.parse(registroMetadato.getFechaExpedicion());
										_log.info("fecha parseada " +date);
										valor.addString(LocaleUtil.SPAIN, simpleDateFormatLiferay.format(date));
										nuevoFFV.setName("FechaExpedicionGlobal");
										nuevoFFV.setValue(valor);
										ddmFormValues.addDDMFormFieldValue(nuevoFFV);
									}
									if(tramite) {
										DDMFormFieldValue nuevoFFV = new DDMFormFieldValue();
										_log.info("creara el metadato tramite");
										Value valor = new LocalizedValue(LocaleUtil.SPAIN);
										valor.addString(LocaleUtil.SPAIN, registroMetadato.getTramite());
										nuevoFFV.setName("Tramite");
										nuevoFFV.setValue(valor);
										ddmFormValues.addDDMFormFieldValue(nuevoFFV);
									}
									if(Anno) {
										DDMFormFieldValue nuevoFFV = new DDMFormFieldValue();
										_log.info("creara el metadato Anno");
										Value valor = new LocalizedValue(LocaleUtil.SPAIN);
										valor.addString(LocaleUtil.SPAIN, registroMetadato.getAnioDocumento());
										nuevoFFV.setName("Anno");
										nuevoFFV.setValue(valor);
										ddmFormValues.addDDMFormFieldValue(nuevoFFV);
									}
									if(dependenciaOrigen) {
										DDMFormFieldValue nuevoFFV = new DDMFormFieldValue();
										_log.info("creara el metadato dependenciaOrigen");
										Value valor = new LocalizedValue(LocaleUtil.SPAIN);
										valor.addString(LocaleUtil.SPAIN, registroMetadato.getDependenciaOrigen());
										nuevoFFV.setName("DependenciaOrigen");
										nuevoFFV.setValue(valor);
										ddmFormValues.addDDMFormFieldValue(nuevoFFV);
									}
									if(TituloExploradorMetadatoSEO) {
										DDMFormFieldValue nuevoFFV = new DDMFormFieldValue();
										_log.info("creara el metadato TituloExploradorMetadatoSEO");
										Value valor = new LocalizedValue(LocaleUtil.SPAIN);
										valor.addString(LocaleUtil.SPAIN, registroMetadato.getTitulo());
										nuevoFFV.setName("TituloExploradorMetadatoSEO");
										nuevoFFV.setValue(valor);
										ddmFormValues.addDDMFormFieldValue(nuevoFFV);
									}
									
									StorageEngineManagerUtil.update(metadato.getDDMStorageId(), ddmFormValues, serviceContext);
									
								}
								
								contenido = DDMContentLocalServiceUtil.getContent(storageid);
								_log.info("Content despues de cambio " +contenido.getData());
								
							}
							
						}catch (Exception e) {
							_log.error(e);
						}
						
						try {
							byte[] bytes = IOUtils.toByteArray(file.getContentStream());
							DLAppServiceUtil.updateFileEntry(file.getFileEntryId(), file.getFileName(),file.getMimeType(),registroMetadato.getTitulo(),
									"", "", DLVersionNumberIncrease.NONE, bytes , serviceContext);
						}catch (Exception e) {
							_log.error(e.getMessage());
						}
						
						
					}
				}
				if (!encontroArchivo) {
					_log.info("No se encontro el archivo "+registroMetadato.getTitulo());
				}
				i++;
			}

			
		}catch (Exception e) {
			_log.error(e.getMessage());
		}
		
	}

	public List<Insolvencia> getContentFile(FileEntry fileEntry) {
		
 		Util util = new Util();
		List<Insolvencia> beans = new ArrayList<Insolvencia>();
		
		try {
		
			File targetFile = new File("C:\\metadatos\\archivoInsolvencia.csv");
	
			util.copyInputStreamToFile(fileEntry.getContentStream(), targetFile);
			 
			 _log.info("Tamanio de archivo "+targetFile.length());
	
	 	     beans = new CsvToBeanBuilder(new FileReader(targetFile)).withType(Insolvencia.class).withSeparator(';').build().parse();
	 	     
	 	     _log.info("Lineas de archivo "+beans.size());
	 	     for(Insolvencia nextLine : beans) {
	 	       
	 	        _log.info(nextLine.toString());
	 	     }
	 	    
		} catch (IOException | PortalException e1) {
			e1.printStackTrace();
		}
		
		return beans;
	}

	public void actualizarCateg(User user, Long companyId, Long sitio, List<Insolvencia> arrayMetadatos) {
		
		
//		long assetEntryId = CounterLocalServiceUtil.increment(AssetEntry.class.getName());
//		AssetEntry ae = AssetEntryLocalServiceUtil.createAssetEntry(assetEntryId);
//		ae.setCompanyId(Long.valueOf(companyId));
//		ae.setClassPK(38013L);
//		ae.setClassName(DLFileEntry.class.getName());
//		AssetEntryLocalServiceUtil.addAssetEntry(ae);
//		
		
		
		
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
			for (Insolvencia registroMetadato : arrayMetadatos) {
				_log.info("validacion registro "+i+" de "+arrayMetadatos.size());
				
				Boolean encontroArchivo = Boolean.FALSE;
				
				for (FileEntry file : archivosPortal) {
					
					if(file.getFileName().equalsIgnoreCase(registroMetadato.getNombre()) || file.getTitle().equalsIgnoreCase(registroMetadato.getTitulo())) {
						encontroArchivo=Boolean.TRUE;
						_log.info("encontro el archivo "+file.getFileEntryId()+","+file.getFileName());
						try {
							AssetEntry ae=AssetEntryLocalServiceUtil.getEntry(DLFileEntry.class.getName(), file.getFileEntryId());
							_log.info("AssetEntry "+ae);
														
							AssetCategoryLocalServiceUtil.addAssetEntryAssetCategory(ae.getEntryId(), Long.valueOf(registroMetadato.getCategoria()));
							_log.info("actualizo la categoria");
						}catch (PortalException e) {
							_log.error(e);
						}
						
						
						
						
					}
				}
				if (!encontroArchivo) {
					_log.info("No se encontro el archivo "+registroMetadato.getTitulo());
				}
				i++;
			}

			
		}catch (Exception e) {
			_log.error(e.getMessage());
		}
		
		
	}
	
 	private static final Log _log = LogFactoryUtil.getLog(ActualizarInsolvencia.class);
}