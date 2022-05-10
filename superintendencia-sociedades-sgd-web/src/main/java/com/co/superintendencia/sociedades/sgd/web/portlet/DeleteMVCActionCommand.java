package com.co.superintendencia.sociedades.sgd.web.portlet;

import com.co.superintendencia.sociedades.builder.service.DocumentoSGDLocalServiceUtil;
import com.co.superintendencia.sociedades.sgd.web.constants.documentosResidenteSGDPortletKeys;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.util.ParamUtil;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;


import org.osgi.service.component.annotations.Component;



@Component(
		immediate = true,
		property = {
			"javax.portlet.name=" + documentosResidenteSGDPortletKeys.DOCUMENTOSRESIDENTESGD,
			"mvc.command.name=/delete/document"
		},
		service = MVCActionCommand.class
		)

public class DeleteMVCActionCommand implements MVCActionCommand {

	@Override
	public boolean processAction(ActionRequest actionRequest, ActionResponse actionResponse) throws PortletException {
		// TODO Auto-generated method stub
		System.out.println("<--------------- deleteMVCActionCommand entramos ----------------------->");
		
		String documentId=ParamUtil.getString(actionRequest, "documentId", "");
		
		System.out.println("documentid para eliminar: " + documentId);
		
		//eliminar de la base de datos 
		try {
			
			DocumentoSGDLocalServiceUtil.deleteDocumentoSGD(Long.valueOf(documentId));
			
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (PortalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		return true;
	}


}
