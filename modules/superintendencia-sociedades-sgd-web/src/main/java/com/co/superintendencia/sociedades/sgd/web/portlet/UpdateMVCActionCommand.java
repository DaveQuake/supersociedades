package com.co.superintendencia.sociedades.sgd.web.portlet;

import com.co.superintendencia.sociedades.builder.model.DocumentoSGD;
import com.co.superintendencia.sociedades.builder.service.DocumentoSGDLocalServiceUtil;
import com.co.superintendencia.sociedades.sgd.web.constants.documentosResidenteSGDPortletKeys;
//import com.co.superintendencia.sociedades.model.Documentos;
//import com.co.superintendencia.sociedades.service.DocumentosLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.ParamUtil;
import com.co.cifrado.superIntendencia.api.cifrado;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.soap.SOAPFaultException;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferencePolicyOption;


@Component(
		immediate = true,
		property = {
			"javax.portlet.name=" + documentosResidenteSGDPortletKeys.DOCUMENTOSRESIDENTESGD,
			"mvc.command.name=/update/document"
		},
		service = MVCActionCommand.class
		)

public class UpdateMVCActionCommand implements MVCActionCommand {

	@Override
	public boolean processAction(ActionRequest actionRequest, ActionResponse actionResponse) throws PortletException {
		// TODO Auto-generated method stub
		Date dateActual = new Date();
		
		_cifradoActionCommand(actionRequest);
//		System.out.println("<--------------- updateMVCActionCommand entramos ----------------------->");
		String nombre=ParamUtil.getString(actionRequest, "nombre", ""); 	
		
		DateFormat fechaHora = new SimpleDateFormat("yyyy-MM-dd");
//		System.out.println("fechaHora: "+fechaHora);
		
		Date fechapub= ParamUtil.getDate(actionRequest, "fechapub", fechaHora);
		Date fechaFin = ParamUtil.getDate(actionRequest, "fechafin", fechaHora);
		String epigrafe=ParamUtil.getString(actionRequest, "epigrafe", "");
		String noRadicado=ParamUtil.getString(actionRequest, "NoRadicado", "");
		String documentId=ParamUtil.getString(actionRequest, "documentId", "");
		String urlPagina=ParamUtil.getString(actionRequest, "urlPagina", "");
		String categoria=ParamUtil.getString(actionRequest, "categoria", "");
		String tema=ParamUtil.getString(actionRequest, "tema", "");
		String etiqueta=ParamUtil.getString(actionRequest, "etiqueta", "");
		String palabraClave=ParamUtil.getString(actionRequest, "palabraClave", "");
		System.out.println("fechapub: "+fechapub);
		System.out.println("fechaFin: "+fechaFin);

		
		String UrlDocumento= "";
				
//		Documentos reg=null;
		DocumentoSGD reg=null;
		try {
//			reg = DocumentosLocalServiceUtil.getDocumentos(Long.valueOf(documentId));
			reg = DocumentoSGDLocalServiceUtil.getDocumentoSGD(Long.valueOf(documentId));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (PortalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Documentos antes de  "+ reg);
		try {
				
			  Boolean decifrar = false;
			  Boolean urlEncode = true;
				
			  String cifrado = _cifrado.cifrado(noRadicado, decifrar, urlEncode);
			  
			  UrlDocumento = cifrado+"_"+noRadicado;
			}
			catch (SOAPFaultException soapfe) {
				SessionErrors.add(actionRequest, soapfe.getClass(), soapfe);
			}
			catch (WebServiceException wse) {
				SessionErrors.add(actionRequest, wse.getClass(), wse);
			}
			catch (Exception e) {
				SessionErrors.add(
					actionRequest, "calculatorOperationError", e.getMessage());
			}
				
//		System.out.println("nombre: "+nombre);
//		System.out.println("fechapub: "+fechapub);
//		System.out.println("fechafin: "+fechaFin);
//		System.out.println("epigrafe: "+epigrafe);
//		System.out.println("NoRadicado: "+UrlDocumento);
//		System.out.println("documentId: "+documentId);
		
		
		reg.setNombre(nombre);
		reg.setEpigrafe(epigrafe);
		reg.setUrlDocumento(UrlDocumento);
		reg.setInicioPublicacion(fechapub);
		reg.setFinPublicacion(fechaFin);
		reg.setCreateDate(dateActual);
		reg.setUrlPagina(urlPagina);
		reg.setCategoria(categoria);
		reg.setTema(tema);
		reg.setEtiqueta(etiqueta);
		reg.setPalabraClave(palabraClave);

//		System.out.println("Documentos despues  de  "+ reg);
		
//		DocumentosLocalServiceUtil.updateDocumentos(reg);
		DocumentoSGDLocalServiceUtil.updateDocumentoSGD(reg);
		
//		System.out.println("documento actualziado revisar base de datos  ");
		
		return true;
	}

	private void _cifradoActionCommand(ActionRequest actionRequest) {
		// TODO Auto-generated method stub
		
	}
	@Reference(policyOption = ReferencePolicyOption.GREEDY)
	private cifrado _cifrado;
}
