package gov.co.supersociedades.buscador.interno.config;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.ConfigurationAction;
import com.liferay.portal.kernel.portlet.DefaultConfigurationAction;
import com.liferay.portal.kernel.util.ParamUtil;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
import javax.portlet.PortletPreferences;

import org.osgi.service.component.annotations.Component;

import gov.co.supersociedades.buscador.interno.constants.SupersociedadesBuscadorInternoPortletKeys;

@Component(
    configurationPid = SupersociedadesBuscadorInternoPortletKeys.GOVCOSUPERSOCIEDADESBUSCADORINTERNOCONFIGURATION,
    immediate = true,
    property = {
        "javax.portlet.name="+SupersociedadesBuscadorInternoPortletKeys.GOVCOSUPERSOCIEDADESBUSCADORINTERNO
    },
    service = ConfigurationAction.class
)
public class ConfigBuscadorAction extends DefaultConfigurationAction {

	private static final Log _log = LogFactoryUtil.getLog(ConfigBuscadorAction.class);
	
	@Override
    public void processAction(PortletConfig portletConfig, ActionRequest actionRequest, ActionResponse actionResponse)
            throws Exception {
		
		try {
			String idCategory = ParamUtil.getString(actionRequest, SupersociedadesBuscadorInternoPortletKeys.CONFIG_ID_CATEGORY);
			String idCategoryInicial = ParamUtil.getString(actionRequest, SupersociedadesBuscadorInternoPortletKeys.CONFIG_ID_CATEGORY_INICIAL);
			String titulo = ParamUtil.getString(actionRequest, SupersociedadesBuscadorInternoPortletKeys.CONFIG_TITULO);
			String bajada = ParamUtil.getString(actionRequest, SupersociedadesBuscadorInternoPortletKeys.CONFIG_BAJADA);
			String dlfile = ParamUtil.getString(actionRequest, SupersociedadesBuscadorInternoPortletKeys.CONFIG_DLFILE);
			String journalArticle = ParamUtil.getString(actionRequest, SupersociedadesBuscadorInternoPortletKeys.CONFIG_JA);
			String paginador = ParamUtil.getString(actionRequest, SupersociedadesBuscadorInternoPortletKeys.CONFIG_PAGINADOR);

			PortletPreferences prefs = actionRequest.getPreferences();
			prefs.setValue("idCategory", idCategory);
			prefs.setValue("idCategoryInicial", idCategoryInicial);
			prefs.setValue("titulo", titulo);
			prefs.setValue("bajada", bajada);
			prefs.setValue("dlfile", dlfile);
			prefs.setValue("journalArticle", journalArticle);
			prefs.setValue("paginador", paginador);
			prefs.store();

			super.processAction(portletConfig, actionRequest, actionResponse);
		} catch (Exception e) {
			_log.error(e);
		}
    }
}
