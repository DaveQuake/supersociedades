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

package com.co.superintendencia.sociedades.builder.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link DocumentoSGDLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see DocumentoSGDLocalService
 * @generated
 */
public class DocumentoSGDLocalServiceWrapper
	implements DocumentoSGDLocalService,
			   ServiceWrapper<DocumentoSGDLocalService> {

	public DocumentoSGDLocalServiceWrapper(
		DocumentoSGDLocalService documentoSGDLocalService) {

		_documentoSGDLocalService = documentoSGDLocalService;
	}

	/**
	 * Adds the documento sgd to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect DocumentoSGDLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param documentoSGD the documento sgd
	 * @return the documento sgd that was added
	 */
	@Override
	public com.co.superintendencia.sociedades.builder.model.DocumentoSGD
		addDocumentoSGD(
			com.co.superintendencia.sociedades.builder.model.DocumentoSGD
				documentoSGD) {

		return _documentoSGDLocalService.addDocumentoSGD(documentoSGD);
	}

	/**
	 * Creates a new documento sgd with the primary key. Does not add the documento sgd to the database.
	 *
	 * @param documentoId the primary key for the new documento sgd
	 * @return the new documento sgd
	 */
	@Override
	public com.co.superintendencia.sociedades.builder.model.DocumentoSGD
		createDocumentoSGD(long documentoId) {

		return _documentoSGDLocalService.createDocumentoSGD(documentoId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _documentoSGDLocalService.createPersistedModel(primaryKeyObj);
	}

	/**
	 * Deletes the documento sgd from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect DocumentoSGDLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param documentoSGD the documento sgd
	 * @return the documento sgd that was removed
	 */
	@Override
	public com.co.superintendencia.sociedades.builder.model.DocumentoSGD
		deleteDocumentoSGD(
			com.co.superintendencia.sociedades.builder.model.DocumentoSGD
				documentoSGD) {

		return _documentoSGDLocalService.deleteDocumentoSGD(documentoSGD);
	}

	/**
	 * Deletes the documento sgd with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect DocumentoSGDLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param documentoId the primary key of the documento sgd
	 * @return the documento sgd that was removed
	 * @throws PortalException if a documento sgd with the primary key could not be found
	 */
	@Override
	public com.co.superintendencia.sociedades.builder.model.DocumentoSGD
			deleteDocumentoSGD(long documentoId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _documentoSGDLocalService.deleteDocumentoSGD(documentoId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _documentoSGDLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _documentoSGDLocalService.dynamicQuery();
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return _documentoSGDLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.co.superintendencia.sociedades.builder.model.impl.DocumentoSGDModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {

		return _documentoSGDLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.co.superintendencia.sociedades.builder.model.impl.DocumentoSGDModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {

		return _documentoSGDLocalService.dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return _documentoSGDLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {

		return _documentoSGDLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.co.superintendencia.sociedades.builder.model.DocumentoSGD
		fetchDocumentoSGD(long documentoId) {

		return _documentoSGDLocalService.fetchDocumentoSGD(documentoId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _documentoSGDLocalService.getActionableDynamicQuery();
	}

	/**
	 * Returns the documento sgd with the primary key.
	 *
	 * @param documentoId the primary key of the documento sgd
	 * @return the documento sgd
	 * @throws PortalException if a documento sgd with the primary key could not be found
	 */
	@Override
	public com.co.superintendencia.sociedades.builder.model.DocumentoSGD
			getDocumentoSGD(long documentoId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _documentoSGDLocalService.getDocumentoSGD(documentoId);
	}

	/**
	 * Returns a range of all the documento sgds.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.co.superintendencia.sociedades.builder.model.impl.DocumentoSGDModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of documento sgds
	 * @param end the upper bound of the range of documento sgds (not inclusive)
	 * @return the range of documento sgds
	 */
	@Override
	public java.util.List
		<com.co.superintendencia.sociedades.builder.model.DocumentoSGD>
			getDocumentoSGDs(int start, int end) {

		return _documentoSGDLocalService.getDocumentoSGDs(start, end);
	}

	/**
	 * Returns the number of documento sgds.
	 *
	 * @return the number of documento sgds
	 */
	@Override
	public int getDocumentoSGDsCount() {
		return _documentoSGDLocalService.getDocumentoSGDsCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _documentoSGDLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _documentoSGDLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _documentoSGDLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Updates the documento sgd in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect DocumentoSGDLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param documentoSGD the documento sgd
	 * @return the documento sgd that was updated
	 */
	@Override
	public com.co.superintendencia.sociedades.builder.model.DocumentoSGD
		updateDocumentoSGD(
			com.co.superintendencia.sociedades.builder.model.DocumentoSGD
				documentoSGD) {

		return _documentoSGDLocalService.updateDocumentoSGD(documentoSGD);
	}

	@Override
	public DocumentoSGDLocalService getWrappedService() {
		return _documentoSGDLocalService;
	}

	@Override
	public void setWrappedService(
		DocumentoSGDLocalService documentoSGDLocalService) {

		_documentoSGDLocalService = documentoSGDLocalService;
	}

	private DocumentoSGDLocalService _documentoSGDLocalService;

}