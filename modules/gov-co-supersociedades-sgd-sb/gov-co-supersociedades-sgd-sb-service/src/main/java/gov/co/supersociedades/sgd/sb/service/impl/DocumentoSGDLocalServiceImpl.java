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

package gov.co.supersociedades.sgd.sb.service.impl;

import com.liferay.portal.aop.AopService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.osgi.service.component.annotations.Component;

import gov.co.supersociedades.sgd.sb.model.DocumentoSGD;
import gov.co.supersociedades.sgd.sb.service.base.DocumentoSGDLocalServiceBaseImpl;

/**
 * The implementation of the documento sgd local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the <code>gov.co.supersociedades.sgd.sb.service.DocumentoSGDLocalService</code> interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see DocumentoSGDLocalServiceBaseImpl
 */
@Component(
	property = "model.class.name=gov.co.supersociedades.sgd.sb.model.DocumentoSGD",
	service = AopService.class
)
public class DocumentoSGDLocalServiceImpl
	extends DocumentoSGDLocalServiceBaseImpl {

	public List<DocumentoSGD> findByUrlPaginaAndDate(String urlPagina, Date fechaActual) {
		List<DocumentoSGD> documentos = this.documentoSGDPersistence.findByUrlPagina(urlPagina);
		List<DocumentoSGD> newList = new ArrayList<DocumentoSGD>();
		for(DocumentoSGD doc : documentos){
		    if(fechaActual.after(doc.getInicioPublicacion()) && doc.getFinPublicacion().after(fechaActual)) {
		    	newList.add(doc);
		    }		    
		}
		return newList;
	}

	public List<DocumentoSGD> findByUrlPagina(String urlPagina) {
		return this.documentoSGDPersistence.findByUrlPagina(urlPagina);
	}
	
	public List<DocumentoSGD> findByNombre(String nombre) {
		return this.documentoSGDPersistence.findByNombre(nombre);
	}
}