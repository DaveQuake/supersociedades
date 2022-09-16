package gov.co.supersociedades.buscador.interno.models;

import java.util.Date;

public class ArticuloBusqueda{

	private String idArticulo;
	private String tituloArticulo;
	private String ulrArticulo;
	private String descripcion;
	private String fechaActualizacion;
	private String extension;
	private String categoriaPadre;
	private Date dateModificate;
	private String fechaExtencion;
	private String peso;
	
	
	
	@Override
	public String toString() {
		return "ArticuloBusqueda [idArticulo=" + idArticulo + ", tituloArticulo=" + tituloArticulo + ", ulrArticulo="
				+ ulrArticulo + ", descripcion=" + descripcion + ", fechaActualizacion=" + fechaActualizacion
				+ ", extension=" + extension + ", categoriaPadre=" + categoriaPadre + ", dateModificate="
				+ dateModificate + ", fechaExtencion=" + fechaExtencion + "]";
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