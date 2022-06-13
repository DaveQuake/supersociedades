package gov.co.supersociedades.listado.documentos.models;

public class FolderList {

	private long folderID;
	private long groupID;
	private boolean revisado;

	public long getFolderID() {
		return folderID;
	}

	public void setFolderID(long folderID) {
		this.folderID = folderID;
	}

	public long getGroupID() {
		return groupID;
	}

	public void setGroupID(long groupID) {
		this.groupID = groupID;
	}

	public boolean isRevisado() {
		return revisado;
	}

	public void setRevisado(boolean revisado) {
		this.revisado = revisado;
	}

	@Override
	public String toString() {
		return "FolderList [folderID=" + folderID + ", groupID=" + groupID + ", revisado=" + revisado + "]";
	}

}
