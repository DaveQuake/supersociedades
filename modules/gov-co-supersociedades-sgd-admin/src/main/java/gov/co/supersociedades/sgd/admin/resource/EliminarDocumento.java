package gov.co.supersociedades.sgd.admin.resource;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCResourceCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.ParamUtil;

import java.io.IOException;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import gov.co.supersociedades.sgd.admin.constants.SgdAdminPortletKeys;
import gov.co.supersociedades.sgd.sb.service.DocumentoSGDLocalService;

@Component(
	immediate = true,
	property = {
		"javax.portlet.name="+SgdAdminPortletKeys.GOVCOSUPERSOCIEDADESSGDADMIN,
		"mvc.command.name=/eliminarDocumento"
	},
    service = MVCResourceCommand.class
)
public class EliminarDocumento extends BaseMVCResourceCommand{

	private static final Log _log = LogFactoryUtil.getLog(EliminarDocumento.class);
	
	@Reference
	private DocumentoSGDLocalService _documentoSGDLocalService;
	
	@Override
	protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
			throws Exception {
		JSONArray result = JSONFactoryUtil.createJSONArray();
		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
		
		try {
			String documentId = ParamUtil.getString(resourceRequest, "documentId", "");
			_documentoSGDLocalService.deleteDocumentoSGD(Long.valueOf(documentId));
		
			jsonObject.put("status", "OK");
			result.put(jsonObject);
		} catch (Exception e) {
			jsonObject.put("status", "NOK");
			result.put(jsonObject);
			_log.error(e);
		}
		
		returnJsonArray(result, resourceRequest, resourceResponse);
	}

	private void returnJsonArray(JSONArray result, ResourceRequest request, ResourceResponse response) throws IOException {
		response.setContentType(ContentTypes.APPLICATION_JSON);
        response.flushBuffer();
        response.getWriter().print(result);
    }
}
