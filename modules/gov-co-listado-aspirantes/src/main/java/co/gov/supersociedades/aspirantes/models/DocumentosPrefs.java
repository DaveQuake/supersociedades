package co.gov.supersociedades.aspirantes.models;

public class DocumentosPrefs {

	private String idCarpeta;
	private String titulo;

	public String getIdCarpeta() {
		return idCarpeta;
	}

	public void setIdCarpeta(String idCarpeta) {
		this.idCarpeta = idCarpeta;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	@Override
	public String toString() {
		return "registroUsuarioPreferencias [idCarpeta=" + idCarpeta + ", titulo=" + titulo + "]";
	}

}
