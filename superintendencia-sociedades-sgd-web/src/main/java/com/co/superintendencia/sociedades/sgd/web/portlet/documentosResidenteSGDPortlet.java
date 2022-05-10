package com.co.superintendencia.sociedades.sgd.web.portlet;

import com.co.superintendencia.sociedades.builder.model.DocumentoSGD;
import com.co.superintendencia.sociedades.builder.service.DocumentoSGDLocalServiceUtil;
import com.co.superintendencia.sociedades.sgd.web.constants.documentosResidenteSGDPortletKeys;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

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
		"javax.portlet.name=" + documentosResidenteSGDPortletKeys.DOCUMENTOSRESIDENTESGD,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class documentosResidenteSGDPortlet extends MVCPortlet {
	
	@Override
	public void render(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		
		System.out.println("pasamos por el render --->");
		String urlPagina=ParamUtil.getString(renderRequest, "urlPagina", "");
		String[] urlPaginaArray = null;
		System.out.println("urlPagina  --->" + urlPagina);
				
		if(urlPagina == "") {
			System.out.println("******Esta es la request URL actual******: " + PortalUtil.getCurrentURL(renderRequest));	
			String urlPaginaActual = PortalUtil.getCurrentURL(renderRequest);
			urlPaginaArray = urlPaginaActual.split("\\?"); 
			System.out.println("urlPaginaArray: " + urlPaginaArray[0]);
			urlPagina = urlPaginaArray[0];
		}


		List<DocumentoSGD> documentos=DocumentoSGDLocalServiceUtil.getDocumentoSGDs(-1, -1);
		
		ArrayList<DocumentoSGD> documentosPagina = new ArrayList<DocumentoSGD>();
		
		for(DocumentoSGD str : documentos){
		    //imprimimos el objeto pivote
			
		    if(str.getUrlPagina().equals(urlPagina)) {
		    	documentosPagina.add(str);
		    }		    
		}
		
		System.out.println("----documentosPagina " + documentosPagina);
		
		renderRequest.setAttribute("documentos", documentosPagina );
		super.render(renderRequest, renderResponse);
	}
}