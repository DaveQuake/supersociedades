package gov.co.supersociedades.buscador.interno.models;

import com.liferay.asset.kernel.model.AssetCategory;

public class ContadorCategorias {

	private AssetCategory category;
	private int contador;
	
	public AssetCategory getCategory() {
		return category;
	}
	public void setCategory(AssetCategory category) {
		this.category = category;
	}
	public int getContador() {
		return contador;
	}
	public void setContador(int contador) {
		this.contador = contador;
	}
}
