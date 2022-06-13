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

package gov.co.supersociedades.sgd.sb.service;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for DocumentoSGD. This utility wraps
 * <code>gov.co.supersociedades.sgd.sb.service.impl.DocumentoSGDLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see DocumentoSGDLocalService
 * @generated
 */
public class DocumentoSGDLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>gov.co.supersociedades.sgd.sb.service.impl.DocumentoSGDLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

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
	public static gov.co.supersociedades.sgd.sb.model.DocumentoSGD
		addDocumentoSGD(
			gov.co.supersociedades.sgd.sb.model.DocumentoSGD documentoSGD) {

		return getService().addDocumentoSGD(documentoSGD);
	}

	/**
	 * Creates a new documento sgd with the primary key. Does not add the documento sgd to the database.
	 *
	 * @param documentoId the primary key for the new documento sgd
	 * @return the new documento sgd
	 */
	public static gov.co.supersociedades.sgd.sb.model.DocumentoSGD
		createDocumentoSGD(long documentoId) {

		return getService().createDocumentoSGD(documentoId);
	}

	/**
	 * @throws PortalException
	 */
	public static com.liferay.portal.kernel.model.PersistedModel
			createPersistedModel(java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().createPersistedModel(primaryKeyObj);
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
	public static gov.co.supersociedades.sgd.sb.model.DocumentoSGD
		deleteDocumentoSGD(
			gov.co.supersociedades.sgd.sb.model.DocumentoSGD documentoSGD) {

		return getService().deleteDocumentoSGD(documentoSGD);
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
	public static gov.co.supersociedades.sgd.sb.model.DocumentoSGD
			deleteDocumentoSGD(long documentoId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deleteDocumentoSGD(documentoId);
	}

	/**
	 * @throws PortalException
	 */
	public static com.liferay.portal.kernel.model.PersistedModel
			deletePersistedModel(
				com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deletePersistedModel(persistedModel);
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery
		dynamicQuery() {

		return getService().dynamicQuery();
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return getService().dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>gov.co.supersociedades.sgd.sb.model.impl.DocumentoSGDModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {

		return getService().dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>gov.co.supersociedades.sgd.sb.model.impl.DocumentoSGDModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {

		return getService().dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {

		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	public static gov.co.supersociedades.sgd.sb.model.DocumentoSGD
		fetchDocumentoSGD(long documentoId) {

		return getService().fetchDocumentoSGD(documentoId);
	}

	public static java.util.List
		<gov.co.supersociedades.sgd.sb.model.DocumentoSGD> findByNombre(
			String nombre) {

		return getService().findByNombre(nombre);
	}

	public static java.util.List
		<gov.co.supersociedades.sgd.sb.model.DocumentoSGD> findByUrlPagina(
			String urlPagina) {

		return getService().findByUrlPagina(urlPagina);
	}

	public static java.util.List
		<gov.co.supersociedades.sgd.sb.model.DocumentoSGD>
			findByUrlPaginaAndDate(
				String urlPagina, java.util.Date fechaActual) {

		return getService().findByUrlPaginaAndDate(urlPagina, fechaActual);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return getService().getActionableDynamicQuery();
	}

	/**
	 * Returns the documento sgd with the primary key.
	 *
	 * @param documentoId the primary key of the documento sgd
	 * @return the documento sgd
	 * @throws PortalException if a documento sgd with the primary key could not be found
	 */
	public static gov.co.supersociedades.sgd.sb.model.DocumentoSGD
			getDocumentoSGD(long documentoId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getDocumentoSGD(documentoId);
	}

	/**
	 * Returns a range of all the documento sgds.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>gov.co.supersociedades.sgd.sb.model.impl.DocumentoSGDModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of documento sgds
	 * @param end the upper bound of the range of documento sgds (not inclusive)
	 * @return the range of documento sgds
	 */
	public static java.util.List
		<gov.co.supersociedades.sgd.sb.model.DocumentoSGD> getDocumentoSGDs(
			int start, int end) {

		return getService().getDocumentoSGDs(start, end);
	}

	/**
	 * Returns the number of documento sgds.
	 *
	 * @return the number of documento sgds
	 */
	public static int getDocumentoSGDsCount() {
		return getService().getDocumentoSGDsCount();
	}

	public static
		com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
			getIndexableActionableDynamicQuery() {

		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	public static com.liferay.portal.kernel.model.PersistedModel
			getPersistedModel(java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getPersistedModel(primaryKeyObj);
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
	public static gov.co.supersociedades.sgd.sb.model.DocumentoSGD
		updateDocumentoSGD(
			gov.co.supersociedades.sgd.sb.model.DocumentoSGD documentoSGD) {

		return getService().updateDocumentoSGD(documentoSGD);
	}

	public static DocumentoSGDLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker
		<DocumentoSGDLocalService, DocumentoSGDLocalService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(DocumentoSGDLocalService.class);

		ServiceTracker<DocumentoSGDLocalService, DocumentoSGDLocalService>
			serviceTracker =
				new ServiceTracker
					<DocumentoSGDLocalService, DocumentoSGDLocalService>(
						bundle.getBundleContext(),
						DocumentoSGDLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}