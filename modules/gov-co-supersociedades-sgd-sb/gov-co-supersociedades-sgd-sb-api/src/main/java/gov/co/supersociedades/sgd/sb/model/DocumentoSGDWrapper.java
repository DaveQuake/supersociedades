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

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link DocumentoSGD}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see DocumentoSGD
 * @generated
 */
public class DocumentoSGDWrapper
	extends BaseModelWrapper<DocumentoSGD>
	implements DocumentoSGD, ModelWrapper<DocumentoSGD> {

	public DocumentoSGDWrapper(DocumentoSGD documentoSGD) {
		super(documentoSGD);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("documentoId", getDocumentoId());
		attributes.put("nombre", getNombre());
		attributes.put("createDate", getCreateDate());
		attributes.put("inicioPublicacion", getInicioPublicacion());
		attributes.put("finPublicacion", getFinPublicacion());
		attributes.put("epigrafe", getEpigrafe());
		attributes.put("urlDocumento", getUrlDocumento());
		attributes.put("urlPagina", getUrlPagina());
		attributes.put("categoria", getCategoria());
		attributes.put("tema", getTema());
		attributes.put("etiqueta", getEtiqueta());
		attributes.put("palabraClave", getPalabraClave());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long documentoId = (Long)attributes.get("documentoId");

		if (documentoId != null) {
			setDocumentoId(documentoId);
		}

		String nombre = (String)attributes.get("nombre");

		if (nombre != null) {
			setNombre(nombre);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date inicioPublicacion = (Date)attributes.get("inicioPublicacion");

		if (inicioPublicacion != null) {
			setInicioPublicacion(inicioPublicacion);
		}

		Date finPublicacion = (Date)attributes.get("finPublicacion");

		if (finPublicacion != null) {
			setFinPublicacion(finPublicacion);
		}

		String epigrafe = (String)attributes.get("epigrafe");

		if (epigrafe != null) {
			setEpigrafe(epigrafe);
		}

		String urlDocumento = (String)attributes.get("urlDocumento");

		if (urlDocumento != null) {
			setUrlDocumento(urlDocumento);
		}

		String urlPagina = (String)attributes.get("urlPagina");

		if (urlPagina != null) {
			setUrlPagina(urlPagina);
		}

		String categoria = (String)attributes.get("categoria");

		if (categoria != null) {
			setCategoria(categoria);
		}

		String tema = (String)attributes.get("tema");

		if (tema != null) {
			setTema(tema);
		}

		String etiqueta = (String)attributes.get("etiqueta");

		if (etiqueta != null) {
			setEtiqueta(etiqueta);
		}

		String palabraClave = (String)attributes.get("palabraClave");

		if (palabraClave != null) {
			setPalabraClave(palabraClave);
		}
	}

	/**
	 * Returns the categoria of this documento sgd.
	 *
	 * @return the categoria of this documento sgd
	 */
	@Override
	public String getCategoria() {
		return model.getCategoria();
	}

	/**
	 * Returns the create date of this documento sgd.
	 *
	 * @return the create date of this documento sgd
	 */
	@Override
	public Date getCreateDate() {
		return model.getCreateDate();
	}

	/**
	 * Returns the documento ID of this documento sgd.
	 *
	 * @return the documento ID of this documento sgd
	 */
	@Override
	public long getDocumentoId() {
		return model.getDocumentoId();
	}

	/**
	 * Returns the epigrafe of this documento sgd.
	 *
	 * @return the epigrafe of this documento sgd
	 */
	@Override
	public String getEpigrafe() {
		return model.getEpigrafe();
	}

	/**
	 * Returns the etiqueta of this documento sgd.
	 *
	 * @return the etiqueta of this documento sgd
	 */
	@Override
	public String getEtiqueta() {
		return model.getEtiqueta();
	}

	/**
	 * Returns the fin publicacion of this documento sgd.
	 *
	 * @return the fin publicacion of this documento sgd
	 */
	@Override
	public Date getFinPublicacion() {
		return model.getFinPublicacion();
	}

	/**
	 * Returns the inicio publicacion of this documento sgd.
	 *
	 * @return the inicio publicacion of this documento sgd
	 */
	@Override
	public Date getInicioPublicacion() {
		return model.getInicioPublicacion();
	}

	/**
	 * Returns the nombre of this documento sgd.
	 *
	 * @return the nombre of this documento sgd
	 */
	@Override
	public String getNombre() {
		return model.getNombre();
	}

	/**
	 * Returns the palabra clave of this documento sgd.
	 *
	 * @return the palabra clave of this documento sgd
	 */
	@Override
	public String getPalabraClave() {
		return model.getPalabraClave();
	}

	/**
	 * Returns the primary key of this documento sgd.
	 *
	 * @return the primary key of this documento sgd
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the tema of this documento sgd.
	 *
	 * @return the tema of this documento sgd
	 */
	@Override
	public String getTema() {
		return model.getTema();
	}

	/**
	 * Returns the url documento of this documento sgd.
	 *
	 * @return the url documento of this documento sgd
	 */
	@Override
	public String getUrlDocumento() {
		return model.getUrlDocumento();
	}

	/**
	 * Returns the url pagina of this documento sgd.
	 *
	 * @return the url pagina of this documento sgd
	 */
	@Override
	public String getUrlPagina() {
		return model.getUrlPagina();
	}

	/**
	 * Returns the uuid of this documento sgd.
	 *
	 * @return the uuid of this documento sgd
	 */
	@Override
	public String getUuid() {
		return model.getUuid();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the categoria of this documento sgd.
	 *
	 * @param categoria the categoria of this documento sgd
	 */
	@Override
	public void setCategoria(String categoria) {
		model.setCategoria(categoria);
	}

	/**
	 * Sets the create date of this documento sgd.
	 *
	 * @param createDate the create date of this documento sgd
	 */
	@Override
	public void setCreateDate(Date createDate) {
		model.setCreateDate(createDate);
	}

	/**
	 * Sets the documento ID of this documento sgd.
	 *
	 * @param documentoId the documento ID of this documento sgd
	 */
	@Override
	public void setDocumentoId(long documentoId) {
		model.setDocumentoId(documentoId);
	}

	/**
	 * Sets the epigrafe of this documento sgd.
	 *
	 * @param epigrafe the epigrafe of this documento sgd
	 */
	@Override
	public void setEpigrafe(String epigrafe) {
		model.setEpigrafe(epigrafe);
	}

	/**
	 * Sets the etiqueta of this documento sgd.
	 *
	 * @param etiqueta the etiqueta of this documento sgd
	 */
	@Override
	public void setEtiqueta(String etiqueta) {
		model.setEtiqueta(etiqueta);
	}

	/**
	 * Sets the fin publicacion of this documento sgd.
	 *
	 * @param finPublicacion the fin publicacion of this documento sgd
	 */
	@Override
	public void setFinPublicacion(Date finPublicacion) {
		model.setFinPublicacion(finPublicacion);
	}

	/**
	 * Sets the inicio publicacion of this documento sgd.
	 *
	 * @param inicioPublicacion the inicio publicacion of this documento sgd
	 */
	@Override
	public void setInicioPublicacion(Date inicioPublicacion) {
		model.setInicioPublicacion(inicioPublicacion);
	}

	/**
	 * Sets the nombre of this documento sgd.
	 *
	 * @param nombre the nombre of this documento sgd
	 */
	@Override
	public void setNombre(String nombre) {
		model.setNombre(nombre);
	}

	/**
	 * Sets the palabra clave of this documento sgd.
	 *
	 * @param palabraClave the palabra clave of this documento sgd
	 */
	@Override
	public void setPalabraClave(String palabraClave) {
		model.setPalabraClave(palabraClave);
	}

	/**
	 * Sets the primary key of this documento sgd.
	 *
	 * @param primaryKey the primary key of this documento sgd
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the tema of this documento sgd.
	 *
	 * @param tema the tema of this documento sgd
	 */
	@Override
	public void setTema(String tema) {
		model.setTema(tema);
	}

	/**
	 * Sets the url documento of this documento sgd.
	 *
	 * @param urlDocumento the url documento of this documento sgd
	 */
	@Override
	public void setUrlDocumento(String urlDocumento) {
		model.setUrlDocumento(urlDocumento);
	}

	/**
	 * Sets the url pagina of this documento sgd.
	 *
	 * @param urlPagina the url pagina of this documento sgd
	 */
	@Override
	public void setUrlPagina(String urlPagina) {
		model.setUrlPagina(urlPagina);
	}

	/**
	 * Sets the uuid of this documento sgd.
	 *
	 * @param uuid the uuid of this documento sgd
	 */
	@Override
	public void setUuid(String uuid) {
		model.setUuid(uuid);
	}

	@Override
	protected DocumentoSGDWrapper wrap(DocumentoSGD documentoSGD) {
		return new DocumentoSGDWrapper(documentoSGD);
	}

}