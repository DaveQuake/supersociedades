package gov.co.supersociedades.sgd;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.WebKeys;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import gov.co.supersociedades.sgd.constants.GovCoSupersociedadesSgdPortletKeys;
import gov.co.supersociedades.sgd.sb.model.DocumentoSGD;
import gov.co.supersociedades.sgd.sb.service.DocumentoSGDLocalService;

/**
 * @author VictorAntunez
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=SuperSociedades Portlet",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.instanceable=false",
		"javax.portlet.display-name=Documentos SGD",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + GovCoSupersociedadesSgdPortletKeys.GOVCOSUPERSOCIEDADESSGD,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class GovCoSupersociedadesSgdPortlet extends MVCPortlet {

	private static final Log _log = LogFactoryUtil.getLog(GovCoSupersociedadesSgdPortlet.class);
			
	@Reference
	private DocumentoSGDLocalService _documentoSGDLocalService;
	
	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		
		ThemeDisplay theme = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		Group sitio = theme.getSiteGroup();
		String UrlVisorDocumentoSGD = (String) sitio.getExpandoBridge().getAttribute("UrlVisorDocumentoSGD");
		renderRequest.setAttribute("urlExterna", UrlVisorDocumentoSGD);
		
		String urlPagina = PortalUtil.getCurrentURL(renderRequest);
		
		List<DocumentoSGD> listaDocumentos = _documentoSGDLocalService.findByUrlPagina(urlPagina);
		List<DocumentoSGD> listaFiltrada = new ArrayList<DocumentoSGD>();
		for (int i = 0; i < listaDocumentos.size(); i++) {
			Date fechaActual = new Date();
			
			if(fechaActual.getTime() > listaDocumentos.get(i).getInicioPublicacion().getTime() && fechaActual.getTime() < listaDocumentos.get(i).getFinPublicacion().getTime()) {
				listaFiltrada.add(listaDocumentos.get(i));
			}
		}
		
		renderRequest.setAttribute("documentos", listaFiltrada);
		renderRequest.setAttribute("urlPagina", urlPagina);
		
		super.doView(renderRequest, renderResponse);
	}
	
}