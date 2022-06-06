package com.co.superintendencia.sociedades.sgd.web.publico.portlet;

import com.co.superintendencia.sociedades.builder.service.DocumentoSGDLocalService;
import com.co.superintendencia.sociedades.sgd.web.publico.constants.documentoResidenteSGDPublicoPortletKeys;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;

import java.io.IOException;
import java.util.Date;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author LENOVO
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=documentoResidenteSGDPublico",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + documentoResidenteSGDPublicoPortletKeys.DOCUMENTORESIDENTESGDPUBLICO,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class documentoResidenteSGDPublicoPortlet extends MVCPortlet {
	
	@Reference
	private DocumentoSGDLocalService _documentoSGDLocalService;
	
	@Override
	public void render(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {

		String urlPagina = ParamUtil.getString(renderRequest, "urlPagina", "");
		
		if(urlPagina == "") {	
			String urlPaginaActual = PortalUtil.getCurrentURL(renderRequest);
			String[] urlPaginaArray = urlPaginaActual.split("\\?"); 
			urlPagina = urlPaginaArray[0];
		}

		renderRequest.setAttribute("documentos", _documentoSGDLocalService.findByUrlPaginaAndDate(urlPagina, new Date()));
		super.render(renderRequest, renderResponse);
	}
}