package com.co.superintendencia.sociedades.sgd.web.portlet;

import com.co.cifrado.superIntendencia.api.cifrado;
import com.co.superintendencia.sociedades.builder.model.DocumentoSGD;
import com.co.superintendencia.sociedades.builder.service.DocumentoSGDLocalService;
import com.co.superintendencia.sociedades.sgd.web.constants.Constantes;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.util.ParamUtil;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferencePolicyOption;

@Component(immediate = true, property = { "javax.portlet.name=" + Constantes.DOCUMENTOSRESIDENTESGD,
		"mvc.command.name=/update/document" }, service = MVCActionCommand.class)
public class UpdateMVCActionCommand implements MVCActionCommand {

	@Reference
	private DocumentoSGDLocalService _documentoSGDLocalService;

	@Override
	public boolean processAction(ActionRequest actionRequest, ActionResponse actionResponse) throws PortletException {
		Date fechapub = ParamUtil.getDate(actionRequest, "fechapub", Constantes.fechaHora);
		Date fechaFin = ParamUtil.getDate(actionRequest, "fechafin", Constantes.fechaHora);

		String noRadicado = ParamUtil.getString(actionRequest, "NoRadicado", "");
		String documentId = ParamUtil.getString(actionRequest, "documentId", "");

		try {
			DocumentoSGD reg = _documentoSGDLocalService.getDocumentoSGD(Long.valueOf(documentId));
			String cifrado = _cifrado.cifrado(noRadicado, false, true);
			String urlDocumento = cifrado + "_" + noRadicado;

			reg.setNombre(ParamUtil.getString(actionRequest, "nombre", ""));
			reg.setEpigrafe(ParamUtil.getString(actionRequest, "epigrafe", ""));
			reg.setUrlDocumento(urlDocumento);
			reg.setInicioPublicacion(fechapub);
			reg.setFinPublicacion(fechaFin);
			reg.setCreateDate(new Date());
			reg.setUrlPagina(ParamUtil.getString(actionRequest, "urlPagina", ""));
			reg.setCategoria(ParamUtil.getString(actionRequest, "categoria", ""));
			reg.setTema(ParamUtil.getString(actionRequest, "tema", ""));
			reg.setEtiqueta(ParamUtil.getString(actionRequest, "etiqueta", ""));
			reg.setPalabraClave(ParamUtil.getString(actionRequest, "palabraClave", ""));

			_documentoSGDLocalService.updateDocumentoSGD(reg);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return true;
	}

	@Reference(policyOption = ReferencePolicyOption.GREEDY)
	private cifrado _cifrado;
}
