package gov.co.supersociedades.buscador.interno.helpers;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.theme.ThemeDisplay;

import java.util.ArrayList;
import java.util.List;

import gov.co.supersociedades.buscador.interno.models.ArticuloBusqueda;
import gov.co.supersociedades.buscador.interno.utils.BuscadorUtils;

public class Hilos extends Thread{
    private AssetCategory categoriaPadre;
    private List<Document> docs;
    private BuscadorUtils _buscadorUtils;
    private ArrayList<ArticuloBusqueda> listaArticulos;
    private boolean isDlFile;
    private ThemeDisplay td;


    public Hilos(AssetCategory categoriaPadre, List<Document> docs, BuscadorUtils _buscadorUtils, ArrayList<ArticuloBusqueda> listaArticulos,
    		boolean isDlFile, ThemeDisplay td) {
		super();
		this.categoriaPadre = categoriaPadre;
		this.docs = docs;
		this._buscadorUtils = _buscadorUtils;
		this.listaArticulos = listaArticulos;
		this.isDlFile = isDlFile;
		this.td = td;
	}
	//TODO devolver una lista de 2000 elementos
    public void hiloTandaRticulos(AssetCategory categoriaPadre, List<Document> docs,  BuscadorUtils _buscadorUtils, ArrayList<ArticuloBusqueda> listaArticulos, boolean isDlFile, ThemeDisplay td) {
        
        for (Document doc : docs) {
			String entryClassName = doc.get(Field.ENTRY_CLASS_NAME);

			if (entryClassName.equalsIgnoreCase(DLFileEntry.class.getName()) && isDlFile) {
				String idFileEntry = doc.get(Field.ENTRY_CLASS_PK);
				
				//long timegetInfoDocumento = System.currentTimeMillis();
				ArticuloBusqueda articulo = _buscadorUtils.getInfoDocumento(td, idFileEntry);
				//_log.info("tiempo metadato getInfoDocumento "+(System.currentTimeMillis()- timegetInfoDocumento)+" hilo "+this.getId());
				
				
				articulo.setCategoriaPadre(categoriaPadre.getName());
				listaArticulos.add(articulo);
			}
		}
        
        
        
    }
    @Override
    public void run() {

        hiloTandaRticulos(categoriaPadre, docs, _buscadorUtils, listaArticulos, isDlFile, td);

    }
    
    private static final Log _log = LogFactoryUtil.getLog(Hilos.class);
}
