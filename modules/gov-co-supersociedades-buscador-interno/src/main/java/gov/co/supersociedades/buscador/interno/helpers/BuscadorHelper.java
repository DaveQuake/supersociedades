package gov.co.supersociedades.buscador.interno.helpers;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.service.AssetCategoryLocalService;
import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.journal.model.JournalArticle;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.facet.faceted.searcher.FacetedSearcher;
import com.liferay.portal.kernel.search.facet.faceted.searcher.FacetedSearcherManagerUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.portlet.PortletPreferences;
import javax.portlet.RenderRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import gov.co.supersociedades.buscador.interno.constants.SupersociedadesBuscadorInternoPortletKeys;
import gov.co.supersociedades.buscador.interno.models.ArticuloBusqueda;
import gov.co.supersociedades.buscador.interno.models.ContadorCategorias;
import gov.co.supersociedades.buscador.interno.utils.BuscadorUtils;

@Component(immediate = true, service = BuscadorHelper.class)
public class BuscadorHelper {

	private Comparator<ArticuloBusqueda> orderByFecha = new Comparator<ArticuloBusqueda>() {
		@Override
		public int compare(ArticuloBusqueda articuloUno, ArticuloBusqueda articuloDos) {
			return articuloUno.getDateModificate().compareTo(articuloDos.getDateModificate());
		}
	};

	public List<ArticuloBusqueda> searchByCategory(RenderRequest renderRequest, String keyword, long[] categoria,
			boolean isDlFile, boolean isJournalArticle, String start, String end, boolean pagination,
			PortletPreferences prefs, String categoriaFiltro) {
		long inicio = System.currentTimeMillis();
		ArrayList<ArticuloBusqueda> listaArticulos = new ArrayList<ArticuloBusqueda>();
		ThemeDisplay td = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		int tipo = getTipo(isDlFile, isJournalArticle);
		long inicio2 = System.currentTimeMillis();
		SearchContext searchContext = _searchContextHelper.getSearchContext(td, keyword, categoria, tipo, "-1", "-1",
				pagination,categoriaFiltro);
		long fin2 = System.currentTimeMillis();
		_log.info("Tiempo de creacion del searchContext: " + (fin2 - inicio2));

		try {
			FacetedSearcher facetedSearcher = FacetedSearcherManagerUtil.createFacetedSearcher();

			Hits hits = facetedSearcher.search(searchContext);

			List<Document> docs = hits.toList();
			
			if (Validator.isNotNull(docs)) {
				
				if(Integer.parseInt(end) > docs.size()) {
					end = String.valueOf(docs.size());
				}
			
				long inicio3 = System.currentTimeMillis();
				AssetCategory categoriaPadre = _buscadorUtils.getCategoriaPadre(_buscadorUtils.getCategoria(categoria[0]));
				long fin3 = System.currentTimeMillis();
				_log.info("Tiempo de obtener la categoria padre: " + (fin3 - inicio3));
				long inicio4 = System.currentTimeMillis();
				Set<String> setArticles = new HashSet<>();
				long fin4 = System.currentTimeMillis();
				_log.info("Tiempo de crear el set: " + (fin4 - inicio4));

				long inicio5 = System.currentTimeMillis();
				for (Document doc : docs) {
					String entryClassName = doc.get(Field.ENTRY_CLASS_NAME);

					if (entryClassName.equalsIgnoreCase(DLFileEntry.class.getName()) && isDlFile) {
						String idArticle = doc.get(Field.ENTRY_CLASS_PK);
						if (setArticles.contains(idArticle))
							continue;
						else
							setArticles.add(idArticle);

						ArticuloBusqueda articulo = _buscadorUtils.getInfoDocumento(td, doc);
						articulo.setCategoriaPadre(categoriaPadre.getName());
						listaArticulos.add(articulo);
					}

					if (entryClassName.equalsIgnoreCase(JournalArticle.class.getName()) && isJournalArticle) {
						String idArticle = doc.get(Field.ARTICLE_ID);
						if (setArticles.contains(idArticle))
							continue;
						else
							setArticles.add(idArticle);

						ArticuloBusqueda articulo = _buscadorUtils.getInfoArticulo(td, doc);
						articulo.setCategoriaPadre(categoriaPadre.getName());
						listaArticulos.add(articulo);

					}
				}
				long fin5 = System.currentTimeMillis();
				_log.info("Tiempo de recorrer los documentos: " + (fin5 - inicio5));
			}
		} catch (Exception e) {
			_log.debug(e);
		}
//		renderRequest.setAttribute("orden", categoriaFiltro.equals("1256470"));
		long inicio6 = System.currentTimeMillis();
		Collections.sort(listaArticulos, new ArticuloBusqueda());
		long fin6 = System.currentTimeMillis();
		_log.info("Tiempo de ordenar los articulos: " + (fin6 - inicio6));
		//Collections.sort(listaArticulos, compareByFechaCreacion);
		long fin = System.currentTimeMillis();
		_log.info("Tiempo de busqueda: " + (fin - inicio));
		return listaArticulos.subList(Integer.parseInt(start), Integer.parseInt(end));
	}
	
	
	
	
	public Comparator<ArticuloBusqueda> compareByFechaCreacion = new Comparator<ArticuloBusqueda>() {
		public int compare(ArticuloBusqueda doc1, ArticuloBusqueda doc2) {
			try {
				Date dateUno = doc1.getDateCompare();
				Date dateDos = doc2.getDateCompare();
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
		if (isDlFile && !isJournalArticle) {
			return 1;
		} else if (!isDlFile && isJournalArticle) {
			return 2;
		} else if (isDlFile && isJournalArticle) {
			return 3;
		} else {
			return 3;
		}
	}

	public List<ContadorCategorias> getCountsByCategory(RenderRequest renderRequest, String keyword,
			long categoryDefault, boolean isDlFile, boolean isJournalArticle, PortletPreferences prefs, String categori) {
		List<ContadorCategorias> listaCategorias = new ArrayList<ContadorCategorias>();
		ThemeDisplay td = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);

		List<AssetCategory> childCategories = _assetCategoryLocalService.getChildCategories(categoryDefault);
		if (!childCategories.isEmpty()) {
			try {
				for (AssetCategory childCategory : childCategories) {
					long[] childCategoryId = { childCategory.getCategoryId() };

					try {
						SearchContext searchContext = _searchContextHelper.getSearchContextCount(td, keyword,
								childCategoryId, getTipo(isDlFile, isJournalArticle));
						FacetedSearcher facetedSearcher = FacetedSearcherManagerUtil.createFacetedSearcher();
						Hits hits = facetedSearcher.search(searchContext);

						if (hits.getLength() > 0) {
							ContadorCategorias cont = new ContadorCategorias();
							cont.setCategory(childCategory);
							cont.setContador(hits.getLength());
							listaCategorias.add(cont);
							Long categoriLong = Long.parseLong(categori);
							if(categoriLong == childCategory.getCategoryId()) {
								int resultPag =Integer.parseInt(GetterUtil.getString(prefs.getValue(SupersociedadesBuscadorInternoPortletKeys.CONFIG_PAGINADOR, "20")));
								renderRequest.setAttribute("totalPag", getCantPag(hits.getLength(), resultPag));
							}
							
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

	public int getCountByCategory(RenderRequest renderRequest, String keyword, long[] categoria, boolean isDlFile,
			boolean isJournalArticle, String categoriaFiltro) {
		ThemeDisplay td = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		int tipo = getTipo(isDlFile, isJournalArticle);
		SearchContext searchContext = _searchContextHelper.getSearchContext(td, keyword, categoria, tipo, "", "",
				false,categoriaFiltro);

		int contador = 0;
		try {
			FacetedSearcher facetedSearcher = FacetedSearcherManagerUtil.createFacetedSearcher();
			Hits hits = facetedSearcher.search(searchContext);
			contador = hits.getLength();
		} catch (Exception e) {
			_log.error(e);
		}

		return contador;
	}
	
	public long getCantPag(int totalRegistros, int registrosPagina) {
		long cantPag = 1;
		
		if(totalRegistros > registrosPagina) {
			double result = totalRegistros / registrosPagina;
			cantPag = Math.round(result);
			
			if(totalRegistros % registrosPagina == 1) {
				cantPag = cantPag +1; 
			}
			
		}
		
		return cantPag;
	}
	
	
	
	
	
	private static final Log _log = LogFactoryUtil.getLog(BuscadorHelper.class);

	@Reference
	private BuscadorUtils _buscadorUtils;

	@Reference
	private SearchContextHelper _searchContextHelper;

	@Reference
	private AssetCategoryLocalService _assetCategoryLocalService;
}
