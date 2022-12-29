package com.aria.liferay.documentos.model;

import com.opencsv.bean.CsvBindByName;

public class Insolvencia {
	
	@CsvBindByName
    private String nombre;
    @CsvBindByName
    private String titulo;
    @CsvBindByName
    private String fechaExpedicion;
    @CsvBindByName
    private String anioDocumento;
    @CsvBindByName
    private String tipoDocumento;
    @CsvBindByName
    private String fechaPublicacion;
    @CsvBindByName
    private String dependenciaOrigen;
    @CsvBindByName
    private String tramite;
    @CsvBindByName
    private String categoria;
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
	public String getTipoDocumento() {
		return tipoDocumento;
	}
	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
	public String getFechaPublicacion() {
		return fechaPublicacion;
	}
	public void setFechaPublicacion(String fechaPublicacion) {
		this.fechaPublicacion = fechaPublicacion;
	}
	public String getDependenciaOrigen() {
		return dependenciaOrigen;
	}
	public void setDependenciaOrigen(String dependenciaOrigen) {
		this.dependenciaOrigen = dependenciaOrigen;
	}
	public String getTramite() {
		return tramite;
	}
	public void setTramite(String tramite) {
		this.tramite = tramite;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	@Override
	public String toString() {
		return "Insolvencia [nombre=" + nombre + ", titulo=" + titulo + ", fechaExpedicion=" + fechaExpedicion
				+ ", anioDocumento=" + anioDocumento + ", tipoDocumento=" + tipoDocumento + ", fechaPublicacion="
				+ fechaPublicacion + ", dependenciaOrigen=" + dependenciaOrigen + ", tramite=" + tramite
				+ ", categoria=" + categoria + "]";
	}
    
    
    
}
