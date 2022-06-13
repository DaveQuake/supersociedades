package gov.co.supersociedades.listado.documentos.models;

import java.util.List;

public class ListadoDocumentos {
	private String nombreListado;
	private List<Documento> documentos;
	private String id;
	
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNombreListado() {
		return nombreListado;
	}

	public void setNombreListado(String nombreListado) {
		this.nombreListado = nombreListado;
	}

	public List<Documento> getDocumentos() {
		return documentos;
	}

	public void setDocumentos(List<Documento> documentos) {
		this.documentos = documentos;
	}

	@Override
	public String toString() {
		return "ListadoDocumentos [nombreListado=" + nombreListado + ", documentos=" + documentos + ", id=" + id + "]";
	}

}
