package co.gov.supersociedades.aspirantes.models;

public class Documento {

	private String nombreDocumento;
	private String urlDocumento;
	private String descripcion;
	private String fecha;
	private String extencion;
	private String fechaExpedicion;
	private String peso;
	private String urlExterna;
	private String cargo;
	private String nombrefuncionario;
	private String tipoVinculacion;
	
	
	
	public String getUrlExterna() {
		return urlExterna;
	}

	public void setUrlExterna(String urlExterna) {
		this.urlExterna = urlExterna;
	}

	public String getPeso() {
		return peso;
	}

	public void setPeso(String peso) {
		this.peso = peso;
	}

	public String getFechaExpedicion() {
		return fechaExpedicion;
	}

	public void setFechaExpedicion(String fechaExpedicion) {
		this.fechaExpedicion = fechaExpedicion;
	}

	public String getExtencion() {
		return extencion;
	}

	public void setExtencion(String extencion) {
		this.extencion = extencion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getNombreDocumento() {
		return nombreDocumento;
	}

	public void setNombreDocumento(String nombreDocumento) {
		this.nombreDocumento = nombreDocumento;
	}

	public String getUrlDocumento() {
		return urlDocumento;
	}

	public void setUrlDocumento(String urlDocumento) {
		this.urlDocumento = urlDocumento;
	}
	
	

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public String getNombrefuncionario() {
		return nombrefuncionario;
	}

	public void setNombrefuncionario(String nombrefuncionario) {
		this.nombrefuncionario = nombrefuncionario;
	}

	public String getTipoVinculacion() {
		return tipoVinculacion;
	}

	public void setTipoVinculacion(String tipoVinculacion) {
		this.tipoVinculacion = tipoVinculacion;
	}

	@Override
	public String toString() {
		return "Documento [nombreDocumento=" + nombreDocumento + ", urlDocumento=" + urlDocumento + ", descripcion="
				+ descripcion + ", fecha=" + fecha + ", extencion=" + extencion + ", fechaExpedicion=" + fechaExpedicion
				+ ", peso=" + peso + ", urlExterna=" + urlExterna + ", cargo=" + cargo + ", nombrefuncionario="
				+ nombrefuncionario + ", tipoVinculacion=" + tipoVinculacion + "]";
	}

	

}
