package gov.co.supersociedades.buscador.interno.helpers;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import gov.co.supersociedades.buscador.interno.models.ArticuloBusqueda;
import gov.co.supersociedades.buscador.interno.utils.BuscadorUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Hilos  implements Runnable{
    AssetCategory categoriaPadre;
    List<Document> docs;
    ArticuloBusqueda articuloBusqueda;
    Set<String> setArticles;
    BuscadorUtils _buscadorUtils;
    ArrayList<ArticuloBusqueda> listaArticulos;
    boolean isDlFile;
    ThemeDisplay td;
//    public Hilos(AssetCategory categoriaPadre, List<Document> docs, ArticuloBusqueda articuloBusqueda, Set<String> setArticles, BuscadorUtils _buscadorUtils, ArrayList<ArticuloBusqueda> listaArticulos, boolean isDlFile, ThemeDisplay td) {
//        this.categoriaPadre = categoriaPadre;
//        this.docs = docs;
//        this.articuloBusqueda = articuloBusqueda;
//        this.setArticles = setArticles;
//        this._buscadorUtils = _buscadorUtils;
//        this.listaArticulos = listaArticulos;
//        this.isDlFile = isDlFile;
//        this.td = td;
//
//    }

    //TODO devolver una lista de 2000 elementos
    public void hiloTandaRticulos(AssetCategory categoriaPadre, List<Document> docs, ArticuloBusqueda articuloBusqueda,  BuscadorUtils _buscadorUtils, ArrayList<ArticuloBusqueda> listaArticulos, boolean isDlFile, ThemeDisplay td) {
        for (Document doc : docs) {

            String entryClassName = doc.get(Field.ENTRY_CLASS_NAME);

            if (entryClassName.equalsIgnoreCase(DLFileEntry.class.getName()) && isDlFile) {
                String idArticle = doc.get(Field.ENTRY_CLASS_PK);


                ArticuloBusqueda articulo = _buscadorUtils.getInfoDocumento(td, doc);
                articulo.setCategoriaPadre(categoriaPadre.getName());
                 listaArticulos.add(articulo);
            }
        }
    }
    @Override
    public void run() {

        this.hiloTandaRticulos(categoriaPadre, docs, articuloBusqueda, _buscadorUtils, listaArticulos, isDlFile, td);

    }
}
