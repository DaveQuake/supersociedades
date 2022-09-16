package gov.co.supersociedades.sgd.admin.resource;

import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCResourceCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.util.ParamUtil;

import javax.portlet.PortletRequestDispatcher;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import gov.co.supersociedades.sgd.admin.constants.SgdAdminPortletKeys;
import gov.co.supersociedades.sgd.sb.model.DocumentoSGD;
import gov.co.supersociedades.sgd.sb.service.DocumentoSGDLocalService;

@Component(
		immediate = true,
		property = {
			"javax.portlet.name="+SgdAdminPortletKeys.GOVCOSUPERSOCIEDADESSGDADMIN,
			"mvc.command.name=/obtenerDocumento"
		},
	    service = MVCResourceCommand.class
	)
public class ObtenerDocumento extends BaseMVCResourceCommand{

	@Reference
	private DocumentoSGDLocalService _documentoSGDLocalService;
	
	@Override
	protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
			throws Exception {
		String documentId = ParamUtil.getString(resourceRequest, "documentId", "");
		DocumentoSGD documentoSGD = _documentoSGDLocalService.getDocumentoSGD(Long.valueOf(documentId));
		resourceRequest.setAttribute("documento", documentoSGD);
		resourceRequest.setAttribute("actualizar", true);
		
		PortletRequestDispatcher dispatcher = getPortletRequestDispatcher(resourceRequest, "/agregarDocumento.jsp");
		dispatcher.include(resourceRequest, resourceResponse);
	}

}
