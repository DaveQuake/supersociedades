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

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.model.BaseModel;

import java.util.Date;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The base model interface for the DocumentoSGD service. Represents a row in the &quot;gov_documento_sgd&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>gov.co.supersociedades.sgd.sb.model.impl.DocumentoSGDModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>gov.co.supersociedades.sgd.sb.model.impl.DocumentoSGDImpl</code>.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see DocumentoSGD
 * @generated
 */
@ProviderType
public interface DocumentoSGDModel extends BaseModel<DocumentoSGD> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a documento sgd model instance should use the {@link DocumentoSGD} interface instead.
	 */

	/**
	 * Returns the primary key of this documento sgd.
	 *
	 * @return the primary key of this documento sgd
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this documento sgd.
	 *
	 * @param primaryKey the primary key of this documento sgd
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the uuid of this documento sgd.
	 *
	 * @return the uuid of this documento sgd
	 */
	@AutoEscape
	public String getUuid();

	/**
	 * Sets the uuid of this documento sgd.
	 *
	 * @param uuid the uuid of this documento sgd
	 */
	public void setUuid(String uuid);

	/**
	 * Returns the documento ID of this documento sgd.
	 *
	 * @return the documento ID of this documento sgd
	 */
	public long getDocumentoId();

	/**
	 * Sets the documento ID of this documento sgd.
	 *
	 * @param documentoId the documento ID of this documento sgd
	 */
	public void setDocumentoId(long documentoId);

	/**
	 * Returns the nombre of this documento sgd.
	 *
	 * @return the nombre of this documento sgd
	 */
	@AutoEscape
	public String getNombre();

	/**
	 * Sets the nombre of this documento sgd.
	 *
	 * @param nombre the nombre of this documento sgd
	 */
	public void setNombre(String nombre);

	/**
	 * Returns the create date of this documento sgd.
	 *
	 * @return the create date of this documento sgd
	 */
	public Date getCreateDate();

	/**
	 * Sets the create date of this documento sgd.
	 *
	 * @param createDate the create date of this documento sgd
	 */
	public void setCreateDate(Date createDate);

	/**
	 * Returns the inicio publicacion of this documento sgd.
	 *
	 * @return the inicio publicacion of this documento sgd
	 */
	public Date getInicioPublicacion();

	/**
	 * Sets the inicio publicacion of this documento sgd.
	 *
	 * @param inicioPublicacion the inicio publicacion of this documento sgd
	 */
	public void setInicioPublicacion(Date inicioPublicacion);

	/**
	 * Returns the fin publicacion of this documento sgd.
	 *
	 * @return the fin publicacion of this documento sgd
	 */
	public Date getFinPublicacion();

	/**
	 * Sets the fin publicacion of this documento sgd.
	 *
	 * @param finPublicacion the fin publicacion of this documento sgd
	 */
	public void setFinPublicacion(Date finPublicacion);

	/**
	 * Returns the epigrafe of this documento sgd.
	 *
	 * @return the epigrafe of this documento sgd
	 */
	@AutoEscape
	public String getEpigrafe();

	/**
	 * Sets the epigrafe of this documento sgd.
	 *
	 * @param epigrafe the epigrafe of this documento sgd
	 */
	public void setEpigrafe(String epigrafe);

	/**
	 * Returns the url documento of this documento sgd.
	 *
	 * @return the url documento of this documento sgd
	 */
	@AutoEscape
	public String getUrlDocumento();

	/**
	 * Sets the url documento of this documento sgd.
	 *
	 * @param urlDocumento the url documento of this documento sgd
	 */
	public void setUrlDocumento(String urlDocumento);

	/**
	 * Returns the url pagina of this documento sgd.
	 *
	 * @return the url pagina of this documento sgd
	 */
	@AutoEscape
	public String getUrlPagina();

	/**
	 * Sets the url pagina of this documento sgd.
	 *
	 * @param urlPagina the url pagina of this documento sgd
	 */
	public void setUrlPagina(String urlPagina);

	/**
	 * Returns the categoria of this documento sgd.
	 *
	 * @return the categoria of this documento sgd
	 */
	@AutoEscape
	public String getCategoria();

	/**
	 * Sets the categoria of this documento sgd.
	 *
	 * @param categoria the categoria of this documento sgd
	 */
	public void setCategoria(String categoria);

	/**
	 * Returns the tema of this documento sgd.
	 *
	 * @return the tema of this documento sgd
	 */
	@AutoEscape
	public String getTema();

	/**
	 * Sets the tema of this documento sgd.
	 *
	 * @param tema the tema of this documento sgd
	 */
	public void setTema(String tema);

	/**
	 * Returns the etiqueta of this documento sgd.
	 *
	 * @return the etiqueta of this documento sgd
	 */
	@AutoEscape
	public String getEtiqueta();

	/**
	 * Sets the etiqueta of this documento sgd.
	 *
	 * @param etiqueta the etiqueta of this documento sgd
	 */
	public void setEtiqueta(String etiqueta);

	/**
	 * Returns the palabra clave of this documento sgd.
	 *
	 * @return the palabra clave of this documento sgd
	 */
	@AutoEscape
	public String getPalabraClave();

	/**
	 * Sets the palabra clave of this documento sgd.
	 *
	 * @param palabraClave the palabra clave of this documento sgd
	 */
	public void setPalabraClave(String palabraClave);

	/**
	 * Returns the num radicado of this documento sgd.
	 *
	 * @return the num radicado of this documento sgd
	 */
	@AutoEscape
	public String getNumRadicado();

	/**
	 * Sets the num radicado of this documento sgd.
	 *
	 * @param numRadicado the num radicado of this documento sgd
	 */
	public void setNumRadicado(String numRadicado);

}