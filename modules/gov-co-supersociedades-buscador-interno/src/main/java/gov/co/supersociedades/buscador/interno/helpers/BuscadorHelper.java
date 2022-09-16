package gov.co.supersociedades.buscador.interno.helpers;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.service.AssetCategoryLocalService;
import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.journal.model.JournalArticle;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.OrderFactoryUtil;
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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.portlet.RenderRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import gov.co.supersociedades.buscador.interno.models.ArticuloBusqueda;
import gov.co.supersociedades.buscador.interno.models.ContadorCategorias;
import gov.co.supersociedades.buscador.interno.utils.BuscadorUtils;

@Component(immediate=true, service=BuscadorHelper.class)
public class BuscadorHelper {

	private Comparator<ArticuloBusqueda> orderByFecha = new Comparator<ArticuloBusqueda>() {
        @Override
        public int compare(ArticuloBusqueda articuloUno, ArticuloBusqueda articuloDos) {
			return articuloUno.getDateModificate().compareTo(articuloDos.getDateModificate());
        }
    };
	
	public List<ArticuloBusqueda> searchByCategory(RenderRequest renderRequest, String keyword, long[] categoria, boolean isDlFile, 
			boolean isJournalArticle, String start, String end, boolean pagination) {
		List<ArticuloBusqueda> listaArticulos = new ArrayList<ArticuloBusqueda>();
		
		ThemeDisplay td = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		int tipo = getTipo(isDlFile, isJournalArticle);
		SearchContext searchContext =  _searchContextHelper.getSearchContext(td, keyword, categoria, tipo, start, end, pagination);
		
		try {
			FacetedSearcher facetedSearcher = FacetedSearcherManagerUtil.createFacetedSearcher();
			
			
			
			Hits hits = facetedSearcher.search(searchContext);
			List<Document> docs = hits.toList();
			Collections.sort(docs,sortDocument);
			
			if(Validator.isNotNull(docs)){
				AssetCategory categoriaPadre = _buscadorUtils.getCategoriaPadre(_buscadorUtils.getCategoria(categoria[0]));
				Set<String> setArticles = new HashSet<>();

				for (Document doc : docs.subList(Integer.parseInt(start),Integer.parseInt(end))) {
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
	
	public static Comparator<Document> sortDocument = new Comparator<Document>() {
		public int compare(Document doc1, Document doc2) {
			try {
				SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
				Date dateUno = formatter.parse(doc1.get(Field.MODIFIED_DATE));
				Date dateDos = formatter.parse(doc2.get(Field.MODIFIED_DATE));
				int compareValue = dateUno.compareTo(dateDos);
				if (compareValue == 0) {
					return 0;
				}
				if (compareValue < 0) {
					return -1;
				} else {
					return 1;
				}
			} catch (Exception e) {
				return 0;
			}
			
			
			
		}
	};
	
	private int getTipo(boolean isDlFile, boolean isJournalArticle) {
		if(isDlFile && !isJournalArticle) {
			return 1;
		}else if(!isDlFile && isJournalArticle) {
			return 2;
		}else if(isDlFile && isJournalArticle){
			return 3;
		}else {
			return 3;
		}
	}

	public List<ContadorCategorias> getCountsByCategory(RenderRequest renderRequest, String keyword, long categoryDefault, boolean isDlFile, boolean isJournalArticle) {
		List<ContadorCategorias> listaCategorias = new ArrayList<ContadorCategorias>();
		ThemeDisplay td = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		
		List<AssetCategory> childCategories = _assetCategoryLocalService.getChildCategories(categoryDefault);
		if(!childCategories.isEmpty()) {
			try {
				for (AssetCategory childCategory : childCategories) {
					long[] childCategoryId = {childCategory.getCategoryId()};
					
					try {
						SearchContext searchContext =  _searchContextHelper.getSearchContextCount(td, keyword, childCategoryId, getTipo(isDlFile, isJournalArticle));
						FacetedSearcher facetedSearcher = FacetedSearcherManagerUtil.createFacetedSearcher();
						Hits hits = facetedSearcher.search(searchContext);
						
						if(hits.getLength() > 0) {
							ContadorCategorias cont = new ContadorCategorias();
							cont.setCategory(childCategory);
							cont.setContador(hits.getLength());
							listaCategorias.add(cont);
						}
						
					} catch (Exception e) {
						_log.error(e);
					}
				}
			} catch (Exception e) {
				_log.debug(e);
			}
		}
		return listaCategorias;
	}

	public int getCountByCategory(RenderRequest renderRequest, String keyword, long[] categoria, boolean isDlFile, boolean isJournalArticle) {
		ThemeDisplay td = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		int tipo = getTipo(isDlFile, isJournalArticle);
		SearchContext searchContext =  _searchContextHelper.getSearchContext(td, keyword, categoria, tipo, "", "", false);
		
		int contador = 0;
		try {
			FacetedSearcher facetedSearcher = FacetedSearcherManagerUtil.createFacetedSearcher();
			Hits hits = facetedSearcher.search(searchContext);
			contador = hits.getLength();
		}catch (Exception e) {
			_log.error(e);
		}
		
		return contador;
	}
	
	
	
	
	private static final Log _log = LogFactoryUtil.getLog(BuscadorHelper.class);
	
	@Reference
	private BuscadorUtils _buscadorUtils;
	
	@Reference
	private SearchContextHelper _searchContextHelper;
	
	@Reference
	private AssetCategoryLocalService _assetCategoryLocalService;
}
