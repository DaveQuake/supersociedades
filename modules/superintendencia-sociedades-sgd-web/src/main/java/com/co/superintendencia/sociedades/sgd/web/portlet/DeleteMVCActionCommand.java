package com.co.superintendencia.sociedades.sgd.web.portlet;

import com.co.superintendencia.sociedades.builder.service.DocumentoSGDLocalService;
import com.co.superintendencia.sociedades.sgd.web.constants.Constantes;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.util.ParamUtil;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + Constantes.DOCUMENTOSRESIDENTESGD,
		"mvc.command.name=/delete/document"
	},
	service = MVCActionCommand.class
)
public class DeleteMVCActionCommand implements MVCActionCommand {

	@Reference
	private DocumentoSGDLocalService _documentoSGDLocalService;
	
	@Override
	public boolean processAction(ActionRequest actionRequest, ActionResponse actionResponse) throws PortletException {
		String documentId = ParamUtil.getString(actionRequest, "documentId", "");
		
		try {
			_documentoSGDLocalService.deleteDocumentoSGD(Long.valueOf(documentId));
		} catch (Exception e) {
			e.printStackTrace();
		}
				
		return true;
	}
}