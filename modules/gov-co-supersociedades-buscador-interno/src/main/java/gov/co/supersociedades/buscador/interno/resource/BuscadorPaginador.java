package gov.co.supersociedades.buscador.interno.resource;

import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCResourceCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;

import gov.co.supersociedades.buscador.interno.constants.SupersociedadesBuscadorInternoPortletKeys;

@Component(
	immediate = true,
	property = {
		"javax.portlet.name="+SupersociedadesBuscadorInternoPortletKeys.GOVCOSUPERSOCIEDADESBUSCADORINTERNO,
		"mvc.command.name=/actualizarDocumento"
	},
    service = MVCResourceCommand.class
)
public class BuscadorPaginador extends BaseMVCResourceCommand{

	@Override
	protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

}
