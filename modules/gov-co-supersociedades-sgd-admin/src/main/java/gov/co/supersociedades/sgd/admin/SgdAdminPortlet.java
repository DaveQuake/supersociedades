package gov.co.supersociedades.sgd.admin;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.WebKeys;

import java.io.IOException;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.PortletPreferences;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import gov.co.supersociedades.sgd.admin.constants.SgdAdminPortletKeys;
import gov.co.supersociedades.sgd.sb.service.DocumentoSGDLocalService;

/**
 * @author VictorAntunez
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=SuperSociedades Portlet",
		"com.liferay.portlet.footer-portlet-javascript=/js/main.js",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.instanceable=false",
		"javax.portlet.display-name=Documentos SGD Admin",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + SgdAdminPortletKeys.GOVCOSUPERSOCIEDADESSGDADMIN,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,Administrator"
	},
	service = Portlet.class
)
public class SgdAdminPortlet extends MVCPortlet {

	@Reference
	private DocumentoSGDLocalService _documentoSGDLocalService;
	
	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		ThemeDisplay td = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		User user = td.getUser();
		renderRequest.setAttribute("isDefaultUser", user.getDefaultUser());
		
		ThemeDisplay theme = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		Group sitio = theme.getSiteGroup();
		String UrlVisorDocumentoSGD = (String) sitio.getExpandoBridge().getAttribute("UrlVisorDocumentoSGD");
		renderRequest.setAttribute("urlExterna", UrlVisorDocumentoSGD);
		
		String urlPagina = PortalUtil.getCurrentURL(renderRequest);
		renderRequest.setAttribute("documentos", _documentoSGDLocalService.findByUrlPagina(urlPagina));
		renderRequest.setAttribute("actualizar", false);
		
		super.doView(renderRequest, renderResponse);
	}
	
}