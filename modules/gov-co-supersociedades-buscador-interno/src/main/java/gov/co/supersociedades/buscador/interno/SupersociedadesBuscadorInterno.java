package gov.co.supersociedades.buscador.interno;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.PortletPreferences;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import gov.co.supersociedades.buscador.interno.constants.SupersociedadesBuscadorInternoPortletKeys;
import gov.co.supersociedades.buscador.interno.helpers.BuscadorHelper;
import gov.co.supersociedades.buscador.interno.models.ArticuloBusqueda;
import gov.co.supersociedades.buscador.interno.utils.BuscadorUtils;

/**
 * @author VictorAntunez
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=SuperSociedades Portlet",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=Buscador Interno",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/buscador/view.jsp",
		"javax.portlet.init-param.config-template=/buscador/edit.jsp",
		"javax.portlet.name=" + SupersociedadesBuscadorInternoPortletKeys.GOVCOSUPERSOCIEDADESBUSCADORINTERNO,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class SupersociedadesBuscadorInterno extends MVCPortlet {
	
	@Reference
	private BuscadorHelper _buscadorHelper;
	
	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		HttpServletRequest httpReq = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(renderRequest));
		
		PortletPreferences prefs = renderRequest.getPreferences();
		renderRequest.setAttribute("titulo", GetterUtil.getString(prefs.getValue(SupersociedadesBuscadorInternoPortletKeys.CONFIG_TITULO, StringPool.DASH)));
		renderRequest.setAttribute("bajada", GetterUtil.getString(prefs.getValue(SupersociedadesBuscadorInternoPortletKeys.CONFIG_BAJADA, StringPool.DASH)));
		
		String paginador = GetterUtil.getString(prefs.getValue(SupersociedadesBuscadorInternoPortletKeys.CONFIG_PAGINADOR, "10"));
		renderRequest.setAttribute("paginador", (Validator.isNotNull(paginador)) ? paginador : "10");
		
		String keyword = _buscadorUtils.getKeywordURL(httpReq);
		renderRequest.setAttribute("keyword", keyword);
		
		String category = _buscadorUtils.getCategoryURL(httpReq);
		renderRequest.setAttribute("category", category);
		
		String start = _buscadorUtils.getPaginator(httpReq, "start", paginador);
		renderRequest.setAttribute("start", Integer.parseInt(start));
		
		String end = _buscadorUtils.getPaginator(httpReq, "end", paginador);
		renderRequest.setAttribute("end", Integer.parseInt(end));
		
		long[] categoria = {_buscadorUtils.getCategorias(httpReq, prefs)};
		long categoryDefault = GetterUtil.getLong(prefs.getValue(SupersociedadesBuscadorInternoPortletKeys.CONFIG_ID_CATEGORY, "0"));
		
		boolean isDlFile = GetterUtil.getBoolean(prefs.getValue(SupersociedadesBuscadorInternoPortletKeys.CONFIG_DLFILE, StringPool.FALSE));
		boolean isJournalArticle = GetterUtil.getBoolean(prefs.getValue(SupersociedadesBuscadorInternoPortletKeys.CONFIG_JA, StringPool.FALSE));

		renderRequest.setAttribute("listaArticulos", _buscadorHelper.searchByCategory(renderRequest, keyword, categoria, isDlFile, isJournalArticle, start, end, true));
		renderRequest.setAttribute("totalArticulos", _buscadorHelper.getCountByCategory(renderRequest, keyword, categoria, isDlFile, isJournalArticle));
		renderRequest.setAttribute("listaCategorias", _buscadorHelper.getCountsByCategory(renderRequest, keyword, categoryDefault, isDlFile, isJournalArticle));
		
		super.doView(renderRequest, renderResponse);
	}

	@Reference
	private BuscadorUtils _buscadorUtils;
}