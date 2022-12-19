package gov.co.supersociedades.buscador.interno.models;

import java.util.Comparator;
import java.util.Date;

public class ArticuloBusqueda implements Comparator<ArticuloBusqueda>{

	private String idArticulo;
	private String tituloArticulo;
	private String ulrArticulo;
	private String descripcion;
	private String fechaActualizacion;
	private String extension;
	private Date dateCompare;
	private String categoriaPadre;
	private Date dateModificate;
	private String fechaExtencion;
	private String peso;
	private String urlExterna;
	private String diarioDescripcion;
	private String diarioLink;
	private String suinDescripcion;
	private String suinLink;
	
	@Override
	public int compare(ArticuloBusqueda o1, ArticuloBusqueda o2) {
		return o2.getDateCompare().compareTo(o1.getDateCompare());
	}
	
	
	

	@Override
	public String toString() {
		return "ArticuloBusqueda [idArticulo=" + idArticulo + ", tituloArticulo=" + tituloArticulo + ", ulrArticulo="
				+ ulrArticulo + ", descripcion=" + descripcion + ", fechaActualizacion=" + fechaActualizacion
				+ ", extension=" + extension + ", dateCompare=" + dateCompare + ", categoriaPadre=" + categoriaPadre
				+ ", dateModificate=" + dateModificate + ", fechaExtencion=" + fechaExtencion + ", peso=" + peso
				+ ", urlExterna=" + urlExterna + ", diarioDescripcion=" + diarioDescripcion + ", diarioLink="
				+ diarioLink + ", suinDescripcion=" + suinDescripcion + ", suinLink=" + suinLink + "]";
	}




	public String getDiarioDescripcion() {
		return diarioDescripcion;
	}




	public void setDiarioDescripcion(String diarioDescripcion) {
		this.diarioDescripcion = diarioDescripcion;
	}




	public String getDiarioLink() {
		return diarioLink;
	}




	public void setDiarioLink(String diarioLink) {
		this.diarioLink = diarioLink;
	}




	public String getSuinDescripcion() {
		return suinDescripcion;
	}




	public void setSuinDescripcion(String suinDescripcion) {
		this.suinDescripcion = suinDescripcion;
	}




	public String getSuinLink() {
		return suinLink;
	}




	public void setSuinLink(String suinLink) {
		this.suinLink = suinLink;
	}




	public String getUrlExterna() {
		return urlExterna;
	}


	public void setUrlExterna(String urlExterna) {
		this.urlExterna = urlExterna;
	}


	public Date getDateCompare() {
		return dateCompare;
	}



	public void setDateCompare(Date dateCompare) {
		this.dateCompare = dateCompare;
	}



	public String getPeso() {
		return peso;
	}



	public void setPeso(String peso) {
		this.peso = peso;
	}



	public String getFechaExtencion() {
		return fechaExtencion;
	}

	public void setFechaExtencion(String fechaExtencion) {
		this.fechaExtencion = fechaExtencion;
	}

	public String getCategoriaPadre() {
		return categoriaPadre;
	}

	public void setCategoriaPadre(String categoriaPadre) {
		this.categoriaPadre = categoriaPadre;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getFechaActualizacion() {
		return fechaActualizacion;
	}

	public void setFechaActualizacion(String fechaActualizacion) {
		this.fechaActualizacion = fechaActualizacion;
	}

	public String getIdArticulo() {
		return idArticulo;
	}

	public void setIdArticulo(String idArticulo) {
		this.idArticulo = idArticulo;
	}

	public String getTituloArticulo() {
		return tituloArticulo;
	}

	public void setTituloArticulo(String tituloArticulo) {
		this.tituloArticulo = tituloArticulo;
	}

	public String getUlrArticulo() {
		return ulrArticulo;
	}

	public void setUlrArticulo(String ulrArticulo) {
		this.ulrArticulo = ulrArticulo;
	}

	public Date getDateModificate() {
		return dateModificate;
	}

	public void setDateModificate(Date dateModificate) {
		this.dateModificate = dateModificate;
	}
}