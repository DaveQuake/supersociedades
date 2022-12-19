package gov.co.supersociedades.buscador.interno;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.facet.faceted.searcher.FacetedSearcher;
import com.liferay.portal.kernel.search.facet.faceted.searcher.FacetedSearcherManagerUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

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
import gov.co.supersociedades.buscador.interno.helpers.SearchContextHelper;
import gov.co.supersociedades.buscador.interno.models.ArticuloBusqueda;
import gov.co.supersociedades.buscador.interno.models.ContadorCategorias;
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
		
		String category = _buscadorUtils.getCategoryURL(httpReq,prefs);
		renderRequest.setAttribute("category", category);
		
		String start = _buscadorUtils.getPaginator(httpReq, "start", paginador);
		renderRequest.setAttribute("start", Integer.parseInt(start));
		
		String end = _buscadorUtils.getPaginator(httpReq, "end", paginador);
		renderRequest.setAttribute("end", Integer.parseInt(end));
		
		long[] categoria = {_buscadorUtils.getCategorias(httpReq, prefs)};
		renderRequest.setAttribute("catBuscada", categoria[0]);
		long categoryDefault = GetterUtil.getLong(prefs.getValue(SupersociedadesBuscadorInternoPortletKeys.CONFIG_ID_CATEGORY, "0"));
		
		boolean isDlFile = GetterUtil.getBoolean(prefs.getValue(SupersociedadesBuscadorInternoPortletKeys.CONFIG_DLFILE, StringPool.FALSE));
		boolean isJournalArticle = GetterUtil.getBoolean(prefs.getValue(SupersociedadesBuscadorInternoPortletKeys.CONFIG_JA, StringPool.FALSE));

		
		_log.info("categoria a buscar"+categoria[0]);
		
		ThemeDisplay td = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		int tipo = _buscadorHelper.getTipo(isDlFile, isJournalArticle);
		SearchContext searchContext = _searchContextHelper.getSearchContext(td, keyword, categoria, tipo, "-1", "-1",
				true,category);
		
		try {
			FacetedSearcher facetedSearcher = FacetedSearcherManagerUtil.createFacetedSearcher();
			
			Hits hits = facetedSearcher.search(searchContext);
		
			
		
		
			long time = System.currentTimeMillis();
			renderRequest.setAttribute("listaArticulos", _buscadorHelper.searchByCategory(renderRequest, keyword, categoria, isDlFile, isJournalArticle, start, end, true,prefs, category, hits));
			_log.info("tiempo total en searchByCategory"+(System.currentTimeMillis()-time));
			
			
			
			
			
			time = System.currentTimeMillis();
			int totalArticulos = _buscadorHelper.getCountByCategory(renderRequest, keyword, categoria, isDlFile, isJournalArticle,"", hits);
			renderRequest.setAttribute("totalArticulos", totalArticulos);
			_log.info("tiempo total en getCountByCategory"+(System.currentTimeMillis()-time));
			
			
			
			time = System.currentTimeMillis();
			List<ContadorCategorias> listaCategorias = _buscadorHelper.getCountsByCategory(renderRequest, keyword, categoryDefault, isDlFile, isJournalArticle,prefs,category);
			renderRequest.setAttribute("listaCategorias", listaCategorias);
			_log.info("tiempo total en getCountsByCategory"+(System.currentTimeMillis()-time));
			
			if(listaCategorias.size() < 1) {
				int resultPag =Integer.parseInt(GetterUtil.getString(prefs.getValue(SupersociedadesBuscadorInternoPortletKeys.CONFIG_PAGINADOR, "20")));
				renderRequest.setAttribute("totalPag", _buscadorHelper.getCantPag(totalArticulos, resultPag));
			}
			
		
		
		
		}catch (Exception e) {
			_log.error(e);
		}
		super.doView(renderRequest, renderResponse);
	}

	@Reference
	private BuscadorUtils _buscadorUtils;
	
	@Reference
	private SearchContextHelper _searchContextHelper;

	
	private static final Log _log = LogFactoryUtil.getLog(SupersociedadesBuscadorInterno.class);
}