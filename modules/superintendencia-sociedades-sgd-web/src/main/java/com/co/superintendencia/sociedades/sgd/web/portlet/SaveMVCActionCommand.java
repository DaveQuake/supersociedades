package com.co.superintendencia.sociedades.sgd.web.portlet;

import com.co.superintendencia.sociedades.builder.model.DocumentoSGD;
import com.co.superintendencia.sociedades.builder.service.DocumentoSGDLocalServiceUtil;
import com.co.superintendencia.sociedades.sgd.web.constants.documentosResidenteSGDPortletKeys;
//import com.co.superintendencia.sociedades.model.Documentos;
//import com.co.superintendencia.sociedades.service.DocumentosLocalServiceUtil;
import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
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
			"mvc.command.name=/save/document"
		},
		service = MVCActionCommand.class
		)

public class SaveMVCActionCommand implements MVCActionCommand {

	@Override
	public boolean processAction(ActionRequest actionRequest, ActionResponse actionResponse) throws PortletException {
		//System.out.println("<--------------- savemvcActionCommand entramos a save llegamos aqui ----------------------->");		
		//DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date dateActual = new Date();		
		String nombre=ParamUtil.getString(actionRequest, "nombre", ""); 
		
		DateFormat fechaHora = new SimpleDateFormat("yyyy-MM-dd");
		//System.out.println("fechaHora: "+fechaHora);
		
		Date fechapub= ParamUtil.getDate(actionRequest, "fechapub", fechaHora);
		Date fechaFin = ParamUtil.getDate(actionRequest, "fechafin", fechaHora);
		String epigrafe=ParamUtil.getString(actionRequest, "epigrafe", "");
		String noRadicado=ParamUtil.getString(actionRequest, "NoRadicado", "");
		String urlPagina=ParamUtil.getString(actionRequest, "urlPagina", "");
		String categoria=ParamUtil.getString(actionRequest, "categoria", "");
		String tema=ParamUtil.getString(actionRequest, "tema", "");
		String etiqueta=ParamUtil.getString(actionRequest, "etiqueta", "");
		String palabraClave=ParamUtil.getString(actionRequest, "palabraClave", "");
		System.out.println("urlPagina: "+urlPagina);
		System.out.println("categoria: "+categoria);
		System.out.println("tema: "+tema);
		System.out.println("etiqueta: "+etiqueta);
		System.out.println("palabraClave: "+palabraClave);
		
		String UrlDocumento= "";
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
//		System.out.println("urldocumento: "+UrlDocumento);
		
//		Documentos reg = DocumentosLocalServiceUtil.createDocumentos(
//				CounterLocalServiceUtil.increment( Documentos.class.getName()));
		
		DocumentoSGD reg = DocumentoSGDLocalServiceUtil.createDocumentoSGD(
				CounterLocalServiceUtil.increment( DocumentoSGD.class.getName())); 

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
		
//		DocumentosLocalServiceUtil.addDocumentos(reg);
		DocumentoSGDLocalServiceUtil.addDocumentoSGD(reg);
		
		
		return true;
	}


	@Reference(policyOption = ReferencePolicyOption.GREEDY)
	private cifrado _cifrado;
		
	}