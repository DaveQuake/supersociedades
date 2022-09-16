/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package gov.co.supersociedades.sgd.sb.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author Brian Wing Shun Chan
 * @deprecated As of Athanasius (7.3.x), with no direct replacement
 * @generated
 */
@Deprecated
public class DocumentoSGDSoap implements Serializable {

	public static DocumentoSGDSoap toSoapModel(DocumentoSGD model) {
		DocumentoSGDSoap soapModel = new DocumentoSGDSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setDocumentoId(model.getDocumentoId());
		soapModel.setNombre(model.getNombre());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setInicioPublicacion(model.getInicioPublicacion());
		soapModel.setFinPublicacion(model.getFinPublicacion());
		soapModel.setEpigrafe(model.getEpigrafe());
		soapModel.setUrlDocumento(model.getUrlDocumento());
		soapModel.setUrlPagina(model.getUrlPagina());
		soapModel.setCategoria(model.getCategoria());
		soapModel.setTema(model.getTema());
		soapModel.setEtiqueta(model.getEtiqueta());
		soapModel.setPalabraClave(model.getPalabraClave());
		soapModel.setNumRadicado(model.getNumRadicado());

		return soapModel;
	}

	public static DocumentoSGDSoap[] toSoapModels(DocumentoSGD[] models) {
		DocumentoSGDSoap[] soapModels = new DocumentoSGDSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static DocumentoSGDSoap[][] toSoapModels(DocumentoSGD[][] models) {
		DocumentoSGDSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new DocumentoSGDSoap[models.length][models[0].length];
		}
		else {
			soapModels = new DocumentoSGDSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static DocumentoSGDSoap[] toSoapModels(List<DocumentoSGD> models) {
		List<DocumentoSGDSoap> soapModels = new ArrayList<DocumentoSGDSoap>(
			models.size());

		for (DocumentoSGD model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new DocumentoSGDSoap[soapModels.size()]);
	}

	public DocumentoSGDSoap() {
	}

	public long getPrimaryKey() {
		return _documentoId;
	}

	public void setPrimaryKey(long pk) {
		setDocumentoId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getDocumentoId() {
		return _documentoId;
	}

	public void setDocumentoId(long documentoId) {
		_documentoId = documentoId;
	}

	public String getNombre() {
		return _nombre;
	}

	public void setNombre(String nombre) {
		_nombre = nombre;
	}

	public Date getCreateDate() {
		return _createDate;
	}

	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	public Date getInicioPublicacion() {
		return _inicioPublicacion;
	}

	public void setInicioPublicacion(Date inicioPublicacion) {
		_inicioPublicacion = inicioPublicacion;
	}

	public Date getFinPublicacion() {
		return _finPublicacion;
	}

	public void setFinPublicacion(Date finPublicacion) {
		_finPublicacion = finPublicacion;
	}

	public String getEpigrafe() {
		return _epigrafe;
	}

	public void setEpigrafe(String epigrafe) {
		_epigrafe = epigrafe;
	}

	public String getUrlDocumento() {
		return _urlDocumento;
	}

	public void setUrlDocumento(String urlDocumento) {
		_urlDocumento = urlDocumento;
	}

	public String getUrlPagina() {
		return _urlPagina;
	}

	public void setUrlPagina(String urlPagina) {
		_urlPagina = urlPagina;
	}

	public String getCategoria() {
		return _categoria;
	}

	public void setCategoria(String categoria) {
		_categoria = categoria;
	}

	public String getTema() {
		return _tema;
	}

	public void setTema(String tema) {
		_tema = tema;
	}

	public String getEtiqueta() {
		return _etiqueta;
	}

	public void setEtiqueta(String etiqueta) {
		_etiqueta = etiqueta;
	}

	public String getPalabraClave() {
		return _palabraClave;
	}

	public void setPalabraClave(String palabraClave) {
		_palabraClave = palabraClave;
	}

	public String getNumRadicado() {
		return _numRadicado;
	}

	public void setNumRadicado(String numRadicado) {
		_numRadicado = numRadicado;
	}

	private String _uuid;
	private long _documentoId;
	private String _nombre;
	private Date _createDate;
	private Date _inicioPublicacion;
	private Date _finPublicacion;
	private String _epigrafe;
	private String _urlDocumento;
	private String _urlPagina;
	private String _categoria;
	private String _tema;
	private String _etiqueta;
	private String _palabraClave;
	private String _numRadicado;

}