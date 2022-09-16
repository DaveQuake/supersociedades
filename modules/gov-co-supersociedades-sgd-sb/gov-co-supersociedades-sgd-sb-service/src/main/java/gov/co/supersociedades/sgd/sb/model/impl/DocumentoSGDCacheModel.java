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

package gov.co.supersociedades.sgd.sb.model.impl;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;

import gov.co.supersociedades.sgd.sb.model.DocumentoSGD;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing DocumentoSGD in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class DocumentoSGDCacheModel
	implements CacheModel<DocumentoSGD>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof DocumentoSGDCacheModel)) {
			return false;
		}

		DocumentoSGDCacheModel documentoSGDCacheModel =
			(DocumentoSGDCacheModel)object;

		if (documentoId == documentoSGDCacheModel.documentoId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, documentoId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(29);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", documentoId=");
		sb.append(documentoId);
		sb.append(", nombre=");
		sb.append(nombre);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", inicioPublicacion=");
		sb.append(inicioPublicacion);
		sb.append(", finPublicacion=");
		sb.append(finPublicacion);
		sb.append(", epigrafe=");
		sb.append(epigrafe);
		sb.append(", urlDocumento=");
		sb.append(urlDocumento);
		sb.append(", urlPagina=");
		sb.append(urlPagina);
		sb.append(", categoria=");
		sb.append(categoria);
		sb.append(", tema=");
		sb.append(tema);
		sb.append(", etiqueta=");
		sb.append(etiqueta);
		sb.append(", palabraClave=");
		sb.append(palabraClave);
		sb.append(", numRadicado=");
		sb.append(numRadicado);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public DocumentoSGD toEntityModel() {
		DocumentoSGDImpl documentoSGDImpl = new DocumentoSGDImpl();

		if (uuid == null) {
			documentoSGDImpl.setUuid("");
		}
		else {
			documentoSGDImpl.setUuid(uuid);
		}

		documentoSGDImpl.setDocumentoId(documentoId);

		if (nombre == null) {
			documentoSGDImpl.setNombre("");
		}
		else {
			documentoSGDImpl.setNombre(nombre);
		}

		if (createDate == Long.MIN_VALUE) {
			documentoSGDImpl.setCreateDate(null);
		}
		else {
			documentoSGDImpl.setCreateDate(new Date(createDate));
		}

		if (inicioPublicacion == Long.MIN_VALUE) {
			documentoSGDImpl.setInicioPublicacion(null);
		}
		else {
			documentoSGDImpl.setInicioPublicacion(new Date(inicioPublicacion));
		}

		if (finPublicacion == Long.MIN_VALUE) {
			documentoSGDImpl.setFinPublicacion(null);
		}
		else {
			documentoSGDImpl.setFinPublicacion(new Date(finPublicacion));
		}

		if (epigrafe == null) {
			documentoSGDImpl.setEpigrafe("");
		}
		else {
			documentoSGDImpl.setEpigrafe(epigrafe);
		}

		if (urlDocumento == null) {
			documentoSGDImpl.setUrlDocumento("");
		}
		else {
			documentoSGDImpl.setUrlDocumento(urlDocumento);
		}

		if (urlPagina == null) {
			documentoSGDImpl.setUrlPagina("");
		}
		else {
			documentoSGDImpl.setUrlPagina(urlPagina);
		}

		if (categoria == null) {
			documentoSGDImpl.setCategoria("");
		}
		else {
			documentoSGDImpl.setCategoria(categoria);
		}

		if (tema == null) {
			documentoSGDImpl.setTema("");
		}
		else {
			documentoSGDImpl.setTema(tema);
		}

		if (etiqueta == null) {
			documentoSGDImpl.setEtiqueta("");
		}
		else {
			documentoSGDImpl.setEtiqueta(etiqueta);
		}

		if (palabraClave == null) {
			documentoSGDImpl.setPalabraClave("");
		}
		else {
			documentoSGDImpl.setPalabraClave(palabraClave);
		}

		if (numRadicado == null) {
			documentoSGDImpl.setNumRadicado("");
		}
		else {
			documentoSGDImpl.setNumRadicado(numRadicado);
		}

		documentoSGDImpl.resetOriginalValues();

		return documentoSGDImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		documentoId = objectInput.readLong();
		nombre = objectInput.readUTF();
		createDate = objectInput.readLong();
		inicioPublicacion = objectInput.readLong();
		finPublicacion = objectInput.readLong();
		epigrafe = objectInput.readUTF();
		urlDocumento = objectInput.readUTF();
		urlPagina = objectInput.readUTF();
		categoria = objectInput.readUTF();
		tema = objectInput.readUTF();
		etiqueta = objectInput.readUTF();
		palabraClave = objectInput.readUTF();
		numRadicado = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		if (uuid == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(uuid);
		}

		objectOutput.writeLong(documentoId);

		if (nombre == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(nombre);
		}

		objectOutput.writeLong(createDate);
		objectOutput.writeLong(inicioPublicacion);
		objectOutput.writeLong(finPublicacion);

		if (epigrafe == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(epigrafe);
		}

		if (urlDocumento == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(urlDocumento);
		}

		if (urlPagina == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(urlPagina);
		}

		if (categoria == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(categoria);
		}

		if (tema == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(tema);
		}

		if (etiqueta == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(etiqueta);
		}

		if (palabraClave == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(palabraClave);
		}

		if (numRadicado == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(numRadicado);
		}
	}

	public String uuid;
	public long documentoId;
	public String nombre;
	public long createDate;
	public long inicioPublicacion;
	public long finPublicacion;
	public String epigrafe;
	public String urlDocumento;
	public String urlPagina;
	public String categoria;
	public String tema;
	public String etiqueta;
	public String palabraClave;
	public String numRadicado;

}