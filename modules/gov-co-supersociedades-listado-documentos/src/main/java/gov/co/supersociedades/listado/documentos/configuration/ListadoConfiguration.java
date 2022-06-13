package gov.co.supersociedades.listado.documentos.configuration;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

import org.osgi.annotation.versioning.ProviderType;

import aQute.bnd.annotation.metatype.Meta;
import gov.co.supersociedades.listado.documentos.constants.Constantes;



@Meta.OCD(id = Constantes.CONFIGURATION_ID)

public interface ListadoConfiguration {
	
	static final String ID_CARPETA_PADRE = "nombreCarpeta";
	static final String TITULO = "titulo";
	static final String BAJADA = "bajada";
	
	@Meta.AD(deflt = "", name = ID_CARPETA_PADRE, description = "id de la carpeta padre", required = false)
	public String idCarpeta();
	@Meta.AD(deflt = "", name = TITULO, description = "Titulo de la seccion", required = false)
	public String titulo();
	@Meta.AD(deflt = "", name = BAJADA, description = "Bajada", required = false)
	public String bajada();
	


}
