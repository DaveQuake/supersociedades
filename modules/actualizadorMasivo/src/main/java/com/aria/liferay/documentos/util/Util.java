package com.aria.liferay.documentos.util;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.service.AssetCategoryLocalServiceUtil;
import com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil;
import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.model.DLFileEntryMetadata;
import com.liferay.document.library.kernel.service.DLAppServiceUtil;
import com.liferay.document.library.kernel.service.DLFileEntryMetadataLocalServiceUtil;
import com.liferay.dynamic.data.mapping.kernel.DDMFormFieldValue;
import com.liferay.dynamic.data.mapping.kernel.DDMFormValues;
import com.liferay.dynamic.data.mapping.kernel.StorageEngineManagerUtil;
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

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.osgi.service.component.annotations.Component;

@Component(
		immediate = true,
		property = {
				"osgi.command.function=listaEstructuras",
				"osgi.command.function=listaCategorias",
				"osgi.command.scope=custom"
		},
		service = Object.class
	)
public class Util {
	
	public void listaCategorias() {
		for (AssetCategory categoria : AssetCategoryLocalServiceUtil.getCategories()) {
			_log.info("categorias "+categoria.toString());
		}
	}
	
	public void listaAsset() {
		for (AssetCategory categoria : AssetCategoryLocalServiceUtil.getCategories()) {
			_log.info("categorias "+categoria.toString());
		}
	}
	
	public void listaEstructuras() {
		List<com.liferay.dynamic.data.mapping.model.DDMStructure>estructura = DDMStructureLocalServiceUtil.getStructures();
		_log.info("estrucutras "+estructura.toString());
		
		_log.info("------");
		for (com.liferay.dynamic.data.mapping.model.DDMStructure seri : estructura) {
			_log.info(seri.toString());
		}
		_log.info("------");
	}
	
	public void listaMetadata(String fileEntryId) {
		
		FileEntry file;
		try {
			file = DLAppServiceUtil.getFileEntry(Long.valueOf(fileEntryId));
			List<DLFileEntryMetadata> ListFileEntryMeta = DLFileEntryMetadataLocalServiceUtil.getFileVersionFileEntryMetadatas(file.getFileVersion().getFileVersionId());
			for (DLFileEntryMetadata metadato : ListFileEntryMeta) {
				Long storageid = metadato.getDDMStorageId();
				DDMContent contenido = DDMContentLocalServiceUtil.getContent(storageid);
				_log.info("Contenido metadata " +contenido.getData());
				System.out.println("Contenido metadata " +contenido.getData());
			}
		} catch (NumberFormatException | PortalException e) {
			e.printStackTrace();
		}
		
	}
	
	public User authentication() {
		
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
	
	public void copyInputStreamToFile(InputStream inputStream, File file)
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
	
	public FileEntry getArchivoMetadatos(Long sitio, String nombre) {
		
		Folder folder;
		try {
			folder = DLAppServiceUtil.getFolder(sitio, 0L, "metadatos");
			_log.info("folder del archivo de metadatos "+folder.getFolderId());
		
			FileEntry metadatos = DLAppServiceUtil.getFileEntry(sitio, folder.getFolderId(), nombre);
			_log.info("codigo del archivo de metadatos "+metadatos.getFileEntryId());
			
			return metadatos;
			
		} catch (PortalException e) {
			_log.error(e);
			return null;
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

	
	private static final Log _log = LogFactoryUtil.getLog(Util.class);
}
