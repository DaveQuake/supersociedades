package com.aria.liferay.documentos.model;

import com.opencsv.bean.CsvBindByName;

public class ConceptosJuridicos {

     @CsvBindByName
     private String nombre;
     @CsvBindByName
     private String titulo;
     @CsvBindByName
     private String epigrafe;
     @CsvBindByName
     private String fechaExpedicion;
     @CsvBindByName
     private String anioDocumento;
     @CsvBindByName
     private String palabrasClave;
     @CsvBindByName
     private String tipoDocumental;
     @CsvBindByName
     private String fechaPublicacion;
	@Override
	public String toString() {
		return "ConceptosJuridicos [nombre=" + nombre + ", titulo=" + titulo + ", epigrafe=" + epigrafe
				+ ", fechaExpedicion=" + fechaExpedicion + ", anioDocumento=" + anioDocumento + ", palabrasClave="
				+ palabrasClave + ", tipoDocumental=" + tipoDocumental + ", fechaPublicacion=" + fechaPublicacion + "]";
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getEpigrafe() {
		return epigrafe;
	}
	public void setEpigrafe(String epigrafe) {
		this.epigrafe = epigrafe;
	}
	public String getFechaExpedicion() {
		return fechaExpedicion;
	}
	public void setFechaExpedicion(String fechaExpedicion) {
		this.fechaExpedicion = fechaExpedicion;
	}
	public String getAnioDocumento() {
		return anioDocumento;
	}
	public void setAnioDocumento(String anioDocumento) {
		this.anioDocumento = anioDocumento;
	}
	public String getPalabrasClave() {
		return palabrasClave;
	}
	public void setPalabrasClave(String palabrasClave) {
		this.palabrasClave = palabrasClave;
	}
	public String getTipoDocumental() {
		return tipoDocumental;
	}
	public void setTipoDocumental(String tipoDocumental) {
		this.tipoDocumental = tipoDocumental;
	}
	public String getFechaPublicacion() {
		return fechaPublicacion;
	}
	public void setFechaPublicacion(String fechaPublicacion) {
		this.fechaPublicacion = fechaPublicacion;
	}

     
    
}
