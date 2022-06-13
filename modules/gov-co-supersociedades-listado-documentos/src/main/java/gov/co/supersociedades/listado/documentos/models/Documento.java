package gov.co.supersociedades.listado.documentos.models;

public class Documento {

	private String nombreDocumento;
	private String urlDocumento;
	private String descripcion;
	private String fecha;
	private String extencion;
	
	

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

	@Override
	public String toString() {
		return "Documento [nombreDocumento=" + nombreDocumento + ", urlDocumento=" + urlDocumento + ", descripcion="
				+ descripcion + ", fecha=" + fecha + ", extencion=" + extencion + "]";
	}

}
