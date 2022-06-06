package gov.co.supersociedades.listado.documentos.configuration;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.ConfigurationAction;
import com.liferay.portal.kernel.portlet.DefaultConfigurationAction;
import com.liferay.portal.kernel.util.ParamUtil;

import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
import javax.portlet.PortletPreferences;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;

import aQute.bnd.annotation.metatype.Configurable;
import gov.co.supersociedades.listado.documentos.constants.Constantes;

@Component(
    configurationPid = Constantes.CONFIGURATION_ID,
    immediate = true,
    property = {
        "javax.portlet.name="+Constantes.GOVCOSUPERSOCIEDADESLISTADODOCUMENTOS
    },
    service = ConfigurationAction.class
)
@SuppressWarnings( { "java:S110" })
public class ConfigListado extends DefaultConfigurationAction {

	private static final Log _log = LogFactoryUtil.getLog(ConfigListado.class);
	private ListadoConfiguration _configListado;
	
	@Override
    public void processAction(PortletConfig portletConfig, ActionRequest actionRequest, ActionResponse actionResponse)
            throws Exception {
		
		try {
			String idCarpeta = ParamUtil.getString(actionRequest, Constantes.ID_CARPETA);
			String titulo = ParamUtil.getString(actionRequest, Constantes.TITULO);
			
			PortletPreferences prefs = actionRequest.getPreferences();
			prefs.setValue(Constantes.ID_CARPETA, idCarpeta);			
			prefs.setValue(Constantes.TITULO, titulo);
			prefs.store();

			super.processAction(portletConfig, actionRequest, actionResponse);
		} catch (Exception e) {
			_log.error(e);
		}
    }
	
	@Override
    public void include(PortletConfig portletConfig, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) throws Exception {
 
        httpServletRequest.setAttribute(ListadoConfiguration.class.getName(), _configListado);
        super.include(portletConfig, httpServletRequest, httpServletResponse);
    }
	
	@Activate
    @Modified
    protected void activate(Map<Object, Object> properties) {
		_configListado = Configurable.createConfigurable(ListadoConfiguration.class, properties);
    }
}
