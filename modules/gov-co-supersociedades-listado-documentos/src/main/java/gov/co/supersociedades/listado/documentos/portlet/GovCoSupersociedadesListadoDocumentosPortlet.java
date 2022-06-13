package gov.co.supersociedades.listado.documentos.portlet;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;

import java.io.IOException;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.PortletPreferences;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import gov.co.supersociedades.listado.documentos.constants.Constantes;
import gov.co.supersociedades.listado.documentos.helpers.HelperDocumentos;
import gov.co.supersociedades.listado.documentos.helpers.HelperPreferences;
import gov.co.supersociedades.listado.documentos.models.DocumentosPrefs;
import gov.co.supersociedades.listado.documentos.models.ListadoDocumentos;



/**
 * @author azure
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=SuperSociedades Portlet",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=Listado Documentos",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.init-param.config-template=/edit.jsp",
		"javax.portlet.name=" + Constantes.GOVCOSUPERSOCIEDADESLISTADODOCUMENTOS,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class GovCoSupersociedadesListadoDocumentosPortlet extends MVCPortlet {

	@Reference
	HelperDocumentos _helperDocumentos;

	@Override
	public void doEdit(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		DocumentosPrefs prefs = HelperPreferences.loadPreferences(renderRequest);
		renderRequest.setAttribute(Constantes.PORTLET_PREFERENCIAS, prefs);
		super.doEdit(renderRequest, renderResponse);
	}

	public void savePrefs(ActionRequest actionRequest, ActionResponse actionResponse)
			throws IOException, PortletException, Exception {

		HelperPreferences prefs = new HelperPreferences();
		prefs.savePreferences(actionRequest, actionResponse);
	}

	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		ThemeDisplay td = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		PortletPreferences pref = renderRequest.getPreferences();
		List<ListadoDocumentos> listadoDoc = _helperDocumentos.generarListado(td,pref);
		renderRequest.setAttribute("listadoDoc", listadoDoc);
		renderRequest.setAttribute("titulo", pref.getValue(Constantes.TITULO, StringPool.BLANK));
		renderRequest.setAttribute("idPadre", pref.getValue(Constantes.ID_CARPETA, StringPool.BLANK));
		super.doView(renderRequest, renderResponse);
	}

}