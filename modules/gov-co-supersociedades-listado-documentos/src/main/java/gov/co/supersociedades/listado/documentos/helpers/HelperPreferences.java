package gov.co.supersociedades.listado.documentos.helpers;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;

import java.io.IOException;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.PortletPreferences;
import javax.portlet.PortletRequest;

import org.osgi.service.component.annotations.Component;

import gov.co.supersociedades.listado.documentos.constants.Constantes;
import gov.co.supersociedades.listado.documentos.models.DocumentosPrefs;

@Component(immediate = true, service = HelperPreferences.class)

public class HelperPreferences {

	
	
	public void savePreferences(ActionRequest req, ActionResponse res) throws IOException, PortletException {
		PortletPreferences portletPrefs = req.getPreferences();
		String idCarpeta = ParamUtil.getString(req, Constantes.ID_CARPETA);
		savePrefs(portletPrefs, idCarpeta, Constantes.ID_CARPETA);
		String titulo = ParamUtil.getString(req, Constantes.TITULO);
		savePrefs(portletPrefs, titulo, Constantes.TITULO);
	}

	private void savePrefs(PortletPreferences portletPrefs, String variable, String constante)
			throws IOException, PortletException {
		if (Validator.isBlank(variable)) {
			portletPrefs.setValue(constante, StringPool.BLANK);
			portletPrefs.store();
		} else {
			portletPrefs.setValue(constante, variable);
			portletPrefs.store();
		}
	}

	public static DocumentosPrefs loadPreferences(PortletRequest portletRequest) {
		PortletPreferences portletPrefs = portletRequest.getPreferences();
		DocumentosPrefs prefs = new DocumentosPrefs();
		prefs.setIdCarpeta(portletPrefs.getValue(Constantes.ID_CARPETA, StringPool.BLANK));
		prefs.setTitulo(portletPrefs.getValue(Constantes.TITULO, StringPool.BLANK));
		return prefs;
	}

}
