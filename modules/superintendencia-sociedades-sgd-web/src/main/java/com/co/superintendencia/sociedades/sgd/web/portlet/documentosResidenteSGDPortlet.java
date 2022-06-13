package com.co.superintendencia.sociedades.sgd.web.portlet;

import com.co.superintendencia.sociedades.builder.service.DocumentoSGDLocalService;
import com.co.superintendencia.sociedades.sgd.web.constants.Constantes;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;

import java.io.IOException;

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
		"javax.portlet.display-name=documentosResidenteSGD",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + Constantes.DOCUMENTOSRESIDENTESGD,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class documentosResidenteSGDPortlet extends MVCPortlet {
	
	@Reference
	private DocumentoSGDLocalService _documentoSGDLocalService;
	
	@Override
	public void render(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {

		String urlPagina=ParamUtil.getString(renderRequest, "urlPagina", "");
		if(urlPagina == "") {
			String urlPaginaActual = PortalUtil.getCurrentURL(renderRequest);
			String[] urlPaginaArray = urlPaginaActual.split("\\?"); 
			urlPagina = urlPaginaArray[0];
		}

		renderRequest.setAttribute("documentos", _documentoSGDLocalService.findByUrlPagina(urlPagina));
		super.render(renderRequest, renderResponse);
	}
}