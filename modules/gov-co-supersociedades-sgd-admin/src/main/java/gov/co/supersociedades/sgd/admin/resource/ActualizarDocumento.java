package gov.co.supersociedades.sgd.admin.resource;

import com.co.cifrado.superIntendencia.api.cifrado;
import com.liferay.petra.string.StringPool;
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
import java.util.Date;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferencePolicyOption;

import gov.co.supersociedades.sgd.admin.constants.SgdAdminPortletKeys;
import gov.co.supersociedades.sgd.sb.model.DocumentoSGD;
import gov.co.supersociedades.sgd.sb.service.DocumentoSGDLocalService;

@Component(
	immediate = true,
	property = {
		"javax.portlet.name="+SgdAdminPortletKeys.GOVCOSUPERSOCIEDADESSGDADMIN,
		"mvc.command.name=/actualizarDocumento"
	},
    service = MVCResourceCommand.class
)
public class ActualizarDocumento extends BaseMVCResourceCommand{
	
	private static final Log _log = LogFactoryUtil.getLog(ActualizarDocumento.class);
	
	@Reference(policyOption = ReferencePolicyOption.GREEDY)
	private cifrado _cifrado;
	
	@Reference
	private DocumentoSGDLocalService _documentoSGDLocalService;
	
	@Override
	protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
			throws Exception {
		
		JSONArray result = JSONFactoryUtil.createJSONArray();
		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
		
		try {
			String documentId = ParamUtil.getString(resourceRequest, "documentId", "");
			
			Date fechapub = ParamUtil.getDate(resourceRequest, "fechapub", SgdAdminPortletKeys.fechaHora);
			Date fechaFin = ParamUtil.getDate(resourceRequest, "fechafin", SgdAdminPortletKeys.fechaHora);
			String noRadicado = ParamUtil.getString(resourceRequest, "noRadicado", "");
			
			String cifrado = _cifrado.cifrado(noRadicado, false, true);
			String urlDocumento = cifrado+StringPool.UNDERLINE+noRadicado;

			DocumentoSGD documentoSGD = _documentoSGDLocalService.getDocumentoSGD(Long.valueOf(documentId));
			documentoSGD.setNumRadicado(noRadicado);
			documentoSGD.setNombre(ParamUtil.getString(resourceRequest, "nombre", ""));
			documentoSGD.setEpigrafe(ParamUtil.getString(resourceRequest, "epigrafe", ""));
			documentoSGD.setUrlDocumento(urlDocumento);
			documentoSGD.setInicioPublicacion(fechapub);
			documentoSGD.setFinPublicacion(fechaFin);
			documentoSGD.setCreateDate(new Date());
			documentoSGD.setUrlPagina(ParamUtil.getString(resourceRequest, "urlPagina", ""));
			documentoSGD.setCategoria(ParamUtil.getString(resourceRequest, "categoria", ""));
			documentoSGD.setTema(ParamUtil.getString(resourceRequest, "tema", ""));
			documentoSGD.setEtiqueta(ParamUtil.getString(resourceRequest, "etiqueta", ""));
			documentoSGD.setPalabraClave(ParamUtil.getString(resourceRequest, "palabraClave", ""));

			_documentoSGDLocalService.updateDocumentoSGD(documentoSGD);

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
