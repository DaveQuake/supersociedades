package gov.co.supersociedades.buscador.interno.helpers;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.service.AssetCategoryLocalService;
import com.liferay.document.library.kernel.model.DLFileEntry;
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
import java.util.List;

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


	public List<ArticuloBusqueda> searchByCategory(RenderRequest renderRequest, String keyword, long[] categoria,
			boolean isDlFile, boolean isJournalArticle, String start, String end, boolean pagination,
			PortletPreferences prefs, String categoriaFiltro, Hits hits) {
		
		long inicio = System.currentTimeMillis();
		ArrayList<ArticuloBusqueda> listaArtHilo1 = new ArrayList<ArticuloBusqueda>();
		ArrayList<ArticuloBusqueda> listaArtHilo2 = new ArrayList<ArticuloBusqueda>();
		ArrayList<ArticuloBusqueda> listaArtHilo3 = new ArrayList<ArticuloBusqueda>();
		ArrayList<ArticuloBusqueda> listaArtHilo4 = new ArrayList<ArticuloBusqueda>();
		ArrayList<ArticuloBusqueda> listaArtHilo5 = new ArrayList<ArticuloBusqueda>();
		ArrayList<ArticuloBusqueda> listaArtHilo6 = new ArrayList<ArticuloBusqueda>();
		ArrayList<ArticuloBusqueda> listaArtHilo7 = new ArrayList<ArticuloBusqueda>();
		ArrayList<ArticuloBusqueda> listaArtHilo8 = new ArrayList<ArticuloBusqueda>();
		
		ThemeDisplay td = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		//int tipo = getTipo(isDlFile, isJournalArticle);
		//long inicio2 = System.currentTimeMillis();
		//SearchContext searchContext = _searchContextHelper.getSearchContext(td, keyword, categoria, tipo, "-1", "-1",
			//	pagination,categoriaFiltro);
		//long fin2 = System.currentTimeMillis();
		//_log.info("Tiempo de creacion del searchContext: " + (fin2 - inicio2));

		try {
			//FacetedSearcher facetedSearcher = FacetedSearcherManagerUtil.createFacetedSearcher();

			//Hits hits = facetedSearcher.search(searchContext);

			List<Document> docs = hits.toList();
			
			
			_log.info("tamaño lista de docs antes de hilo " + docs.size());
			
			if (Validator.isNotNull(docs)) {
				
				if(Integer.parseInt(end) > docs.size()) {
					end = String.valueOf(docs.size());
				}
			
				long inicio3 = System.currentTimeMillis();
				AssetCategory categoriaPadre = _buscadorUtils.getCategoriaPadre(_buscadorUtils.getCategoria(categoria[0]));
				long fin3 = System.currentTimeMillis();
				_log.info("Tiempo de obtener la categoria padre: " + (fin3 - inicio3));

				long inicio5 = System.currentTimeMillis();
				
				if(docs.size()>100) {
				
					int lote = Math.round(docs.size()/8);
					_log.info("Tamaño lote: " + lote);
					
					List<Document> docsHilo1 = docs.subList(0, lote);
					List<Document> docsHilo2 = docs.subList(lote, lote*2);
					List<Document> docsHilo3 = docs.subList(lote*2, lote*3);
					List<Document> docsHilo4 = docs.subList(lote*3, lote*4);
					List<Document> docsHilo5 = docs.subList(lote*4, lote*5);
					List<Document> docsHilo6 = docs.subList(lote*5, lote*6);
					List<Document> docsHilo7 = docs.subList(lote*6, lote*7);
					List<Document> docsHilo8 = docs.subList(lote*7, docs.size());
					
					_log.info("Tamaño lote hilo 1: " + docsHilo1.size());
					_log.info("Tamaño lote hilo 2: " + docsHilo2.size());
					_log.info("Tamaño lote hilo 3: " + docsHilo3.size());
					_log.info("Tamaño lote hilo 4: " + docsHilo4.size());
					_log.info("Tamaño lote hilo 5: " + docsHilo5.size());
					_log.info("Tamaño lote hilo 6: " + docsHilo6.size());
					_log.info("Tamaño lote hilo 7: " + docsHilo7.size());
					_log.info("Tamaño lote hilo 8: " + docsHilo8.size());
					
					
					Hilos hilo1 = new Hilos(categoriaPadre, docsHilo1, _buscadorUtils, listaArtHilo1, isDlFile, td);
					hilo1.start();
					Hilos hilo2 = new Hilos(categoriaPadre, docsHilo2, _buscadorUtils, listaArtHilo2, isDlFile, td);
					hilo2.start();
					Hilos hilo3 = new Hilos(categoriaPadre, docsHilo3, _buscadorUtils, listaArtHilo3, isDlFile, td);
					hilo3.start();
					Hilos hilo4 = new Hilos(categoriaPadre, docsHilo4, _buscadorUtils, listaArtHilo4, isDlFile, td);
					hilo4.start();
					Hilos hilo5 = new Hilos(categoriaPadre, docsHilo5, _buscadorUtils, listaArtHilo5, isDlFile, td);
					hilo5.start();
					Hilos hilo6 = new Hilos(categoriaPadre, docsHilo6, _buscadorUtils, listaArtHilo6, isDlFile, td);
					hilo6.start();
					Hilos hilo7 = new Hilos(categoriaPadre, docsHilo7, _buscadorUtils, listaArtHilo7, isDlFile, td);
					hilo7.start();
					Hilos hilo8 = new Hilos(categoriaPadre, docsHilo8, _buscadorUtils, listaArtHilo8, isDlFile, td);
					hilo8.start();
	
					do {
						_log.info(".");
			             try{
			                 Thread.sleep(100);
			             }catch (InterruptedException exc){
			                 _log.error("Hilo principal interrumpido.");
			             }
			         } while (hilo1.isAlive() || hilo2.isAlive() || hilo3.isAlive() || hilo4.isAlive() || hilo5.isAlive()
			        		 || hilo6.isAlive() || hilo7.isAlive() || hilo8.isAlive());
			        _log.info("Hilo Principal finalizado.");
			        
			        _log.info("tamaño lista de articulos despues de hilo1 " + listaArtHilo1.size());
					_log.info("tamaño lista de articulos despues de hilo2 " + listaArtHilo2.size());
					_log.info("tamaño lista de articulos despues de hilo3 " + listaArtHilo3.size());
					_log.info("tamaño lista de articulos despues de hilo4 " + listaArtHilo4.size());
					_log.info("tamaño lista de articulos despues de hilo5 " + listaArtHilo5.size());
					_log.info("tamaño lista de articulos despues de hilo6 " + listaArtHilo6.size());
					_log.info("tamaño lista de articulos despues de hilo7 " + listaArtHilo7.size());
					_log.info("tamaño lista de articulos despues de hilo8 " + listaArtHilo8.size());
					
					listaArtHilo1.addAll(listaArtHilo2);
					listaArtHilo1.addAll(listaArtHilo3);
					listaArtHilo1.addAll(listaArtHilo4);
					listaArtHilo1.addAll(listaArtHilo5);
					listaArtHilo1.addAll(listaArtHilo6);
					listaArtHilo1.addAll(listaArtHilo7);
					listaArtHilo1.addAll(listaArtHilo8);
				
				}else {
		        
					for (Document doc : docs) {
					String entryClassName = doc.get(Field.ENTRY_CLASS_NAME);
	
						if (entryClassName.equalsIgnoreCase(DLFileEntry.class.getName()) && isDlFile) {
							String idFileEntry = doc.get(Field.ENTRY_CLASS_PK);
							
							long timegetInfoDocumento = System.currentTimeMillis();
							ArticuloBusqueda articulo = _buscadorUtils.getInfoDocumento(td, idFileEntry);
							_log.debug("tiempo metadato getInfoDocumento "+(System.currentTimeMillis()- timegetInfoDocumento));
							
							
							articulo.setCategoriaPadre(categoriaPadre.getName());
							listaArtHilo1.add(articulo);
						}
					}
				}
		        long fin5 = System.currentTimeMillis();
				_log.info("Tiempo de recorrer los documentos: " + (fin5 - inicio5));
			}
			
		} catch (Exception e) {
			_log.error(e);
		}

		_log.info("tamaño lista de articulos despues de hilos " + listaArtHilo1.size());

//		renderRequest.setAttribute("orden", categoriaFiltro.equals("1256470"));
		long inicio6 = System.currentTimeMillis();
		Collections.sort(listaArtHilo1, new ArticuloBusqueda());
		long fin6 = System.currentTimeMillis();
		_log.info("Tiempo de ordenar los articulos: " + (fin6 - inicio6));
		//Collections.sort(listaArticulos, compareByFechaCreacion);
		long fin = System.currentTimeMillis();
		_log.info("Tiempo de busqueda: " + (fin - inicio));
		return listaArtHilo1.subList(Integer.parseInt(start), Integer.parseInt(end));
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

	public int getTipo(boolean isDlFile, boolean isJournalArticle) {
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
					_log.info("categoria hija "+childCategory.getName());
					long[] childCategoryId = { childCategory.getCategoryId() };

					try {
						SearchContext searchContext = _searchContextHelper.getSearchContextCount(td, keyword,
								childCategoryId, getTipo(isDlFile, isJournalArticle));
						FacetedSearcher facetedSearcher = FacetedSearcherManagerUtil.createFacetedSearcher();
						Hits hits = facetedSearcher.search(searchContext);
						_log.info("cantidad hits "+hits.getLength());
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
			boolean isJournalArticle, String categoriaFiltro, Hits hits) {
		//ThemeDisplay td = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		//int tipo = getTipo(isDlFile, isJournalArticle);
		//SearchContext searchContext = _searchContextHelper.getSearchContext(td, keyword, categoria, tipo, "", "",
			//	false,categoriaFiltro);

		int contador = 0;
		try {
			//FacetedSearcher facetedSearcher = FacetedSearcherManagerUtil.createFacetedSearcher();
			//Hits hits = facetedSearcher.search(searchContext);
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
