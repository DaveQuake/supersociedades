package gov.co.supersociedades.buscador.interno.helpers;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.service.AssetCategoryLocalService;
import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.journal.model.JournalArticle;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.facet.faceted.searcher.FacetedSearcher;
import com.liferay.portal.kernel.search.facet.faceted.searcher.FacetedSearcherManagerUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.portlet.RenderRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import gov.co.supersociedades.buscador.interno.models.ArticuloBusqueda;
import gov.co.supersociedades.buscador.interno.models.ContadorCategorias;
import gov.co.supersociedades.buscador.interno.utils.BuscadorUtils;

@Component(immediate=true, service=BuscadorHelper.class)
public class BuscadorHelper {

	public List<ArticuloBusqueda> searchByCategory(RenderRequest renderRequest, String keyword, long[] categoria, 
			List<ArticuloBusqueda> listaArticulos, boolean isDlFile, boolean isJournalArticle) {
		
		ThemeDisplay td = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		SearchContext searchContext =  _searchContextHelper.getSearchContext(td, keyword, categoria, isDlFile, isJournalArticle);
		
		try {
			FacetedSearcher facetedSearcher = FacetedSearcherManagerUtil.createFacetedSearcher();
			Hits hits = facetedSearcher.search(searchContext);
			List<Document> docs = hits.toList();
			
			if(Validator.isNotNull(docs)){
				AssetCategory categoriaPadre = _buscadorUtils.getCategoriaPadre(_buscadorUtils.getCategoria(categoria[0]));
				Set<String> setArticles = new HashSet<>();

				for (Document doc : docs) {
					String entryClassName = doc.get(Field.ENTRY_CLASS_NAME);
					
					if(entryClassName.equalsIgnoreCase(DLFileEntry.class.getName()) && isDlFile){
						String idArticle = doc.get(Field.ENTRY_CLASS_PK);
						if(setArticles.contains(idArticle)) continue;
						else setArticles.add(idArticle);
						
						ArticuloBusqueda articulo = _buscadorUtils.getInfoDocumento(td, doc);
						articulo.setCategoriaPadre(categoriaPadre.getName());
						listaArticulos.add(articulo);
					}
					
					if(entryClassName.equalsIgnoreCase(JournalArticle.class.getName()) && isJournalArticle){
						String idArticle = doc.get(Field.ARTICLE_ID);
						if(setArticles.contains(idArticle)) continue;
						else setArticles.add(idArticle);
						
						ArticuloBusqueda articulo = _buscadorUtils.getInfoArticulo(td, doc);
						articulo.setCategoriaPadre(categoriaPadre.getName());
						listaArticulos.add(articulo);
					}
				}
			}
			
		}catch (Exception e) {
			_log.debug(e);
		}
		
		return listaArticulos;
	}
	
	public List<ContadorCategorias> getCountsByCategory(RenderRequest renderRequest, String keyword, long categoryDefault, boolean isDlFile, boolean isJournalArticle) {
		List<ContadorCategorias> listaCategorias = new ArrayList<ContadorCategorias>();
		ThemeDisplay td = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		
		List<AssetCategory> childCategories = _assetCategoryLocalService.getChildCategories(categoryDefault);
		if(!childCategories.isEmpty()) {
			try {
				for (AssetCategory childCategory : childCategories) {
					long[] childCategoryId = {childCategory.getCategoryId()};
					int contador = 0;
					
					if(isDlFile) {
						SearchContext searchContext =  _searchContextHelper.getSearchContext(td, keyword, childCategoryId, true, false);
						try {
							FacetedSearcher facetedSearcher = FacetedSearcherManagerUtil.createFacetedSearcher();
							Hits hits = facetedSearcher.search(searchContext);
							contador = contador + hits.getLength();
						}catch (Exception e) {
							_log.debug(e);
						}
					}
					
					if(isJournalArticle) {
						SearchContext searchContext =  _searchContextHelper.getSearchContext(td, keyword, childCategoryId, false, true);
						try {
							FacetedSearcher facetedSearcher = FacetedSearcherManagerUtil.createFacetedSearcher();
							Hits hits = facetedSearcher.search(searchContext);
							contador = contador + hits.getLength();
						}catch (Exception e) {
							_log.debug(e);
						}
					}
					
					if(contador > 0) {
						ContadorCategorias cont = new ContadorCategorias();
						cont.setCategory(childCategory);
						cont.setContador(contador);
						listaCategorias.add(cont);
					}
				}
			} catch (Exception e) {
				_log.debug(e);
			}
		}
		return listaCategorias;
	}

	
	private static final Log _log = LogFactoryUtil.getLog(BuscadorHelper.class);
	
	@Reference
	private BuscadorUtils _buscadorUtils;
	
	@Reference
	private SearchContextHelper _searchContextHelper;
	
	@Reference
	private AssetCategoryLocalService _assetCategoryLocalService;
}
