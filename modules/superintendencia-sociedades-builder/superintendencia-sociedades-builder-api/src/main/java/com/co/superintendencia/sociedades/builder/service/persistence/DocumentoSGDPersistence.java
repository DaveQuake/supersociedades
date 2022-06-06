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

package com.co.superintendencia.sociedades.builder.service.persistence;

import com.co.superintendencia.sociedades.builder.exception.NoSuchDocumentoSGDException;
import com.co.superintendencia.sociedades.builder.model.DocumentoSGD;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the documento sgd service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see DocumentoSGDUtil
 * @generated
 */
@ProviderType
public interface DocumentoSGDPersistence extends BasePersistence<DocumentoSGD> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link DocumentoSGDUtil} to access the documento sgd persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Returns all the documento sgds where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching documento sgds
	 */
	public java.util.List<DocumentoSGD> findByUuid(String uuid);

	/**
	 * Returns a range of all the documento sgds where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DocumentoSGDModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of documento sgds
	 * @param end the upper bound of the range of documento sgds (not inclusive)
	 * @return the range of matching documento sgds
	 */
	public java.util.List<DocumentoSGD> findByUuid(
		String uuid, int start, int end);

	/**
	 * Returns an ordered range of all the documento sgds where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DocumentoSGDModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of documento sgds
	 * @param end the upper bound of the range of documento sgds (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching documento sgds
	 */
	public java.util.List<DocumentoSGD> findByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DocumentoSGD>
			orderByComparator);

	/**
	 * Returns an ordered range of all the documento sgds where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DocumentoSGDModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of documento sgds
	 * @param end the upper bound of the range of documento sgds (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching documento sgds
	 */
	public java.util.List<DocumentoSGD> findByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DocumentoSGD>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first documento sgd in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching documento sgd
	 * @throws NoSuchDocumentoSGDException if a matching documento sgd could not be found
	 */
	public DocumentoSGD findByUuid_First(
			String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<DocumentoSGD>
				orderByComparator)
		throws NoSuchDocumentoSGDException;

	/**
	 * Returns the first documento sgd in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching documento sgd, or <code>null</code> if a matching documento sgd could not be found
	 */
	public DocumentoSGD fetchByUuid_First(
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<DocumentoSGD>
			orderByComparator);

	/**
	 * Returns the last documento sgd in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching documento sgd
	 * @throws NoSuchDocumentoSGDException if a matching documento sgd could not be found
	 */
	public DocumentoSGD findByUuid_Last(
			String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<DocumentoSGD>
				orderByComparator)
		throws NoSuchDocumentoSGDException;

	/**
	 * Returns the last documento sgd in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching documento sgd, or <code>null</code> if a matching documento sgd could not be found
	 */
	public DocumentoSGD fetchByUuid_Last(
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<DocumentoSGD>
			orderByComparator);

	/**
	 * Returns the documento sgds before and after the current documento sgd in the ordered set where uuid = &#63;.
	 *
	 * @param documentoId the primary key of the current documento sgd
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next documento sgd
	 * @throws NoSuchDocumentoSGDException if a documento sgd with the primary key could not be found
	 */
	public DocumentoSGD[] findByUuid_PrevAndNext(
			long documentoId, String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<DocumentoSGD>
				orderByComparator)
		throws NoSuchDocumentoSGDException;

	/**
	 * Removes all the documento sgds where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	public void removeByUuid(String uuid);

	/**
	 * Returns the number of documento sgds where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching documento sgds
	 */
	public int countByUuid(String uuid);

	/**
	 * Returns all the documento sgds where urlPagina = &#63;.
	 *
	 * @param urlPagina the url pagina
	 * @return the matching documento sgds
	 */
	public java.util.List<DocumentoSGD> findByUrlPagina(String urlPagina);

	/**
	 * Returns a range of all the documento sgds where urlPagina = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DocumentoSGDModelImpl</code>.
	 * </p>
	 *
	 * @param urlPagina the url pagina
	 * @param start the lower bound of the range of documento sgds
	 * @param end the upper bound of the range of documento sgds (not inclusive)
	 * @return the range of matching documento sgds
	 */
	public java.util.List<DocumentoSGD> findByUrlPagina(
		String urlPagina, int start, int end);

	/**
	 * Returns an ordered range of all the documento sgds where urlPagina = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DocumentoSGDModelImpl</code>.
	 * </p>
	 *
	 * @param urlPagina the url pagina
	 * @param start the lower bound of the range of documento sgds
	 * @param end the upper bound of the range of documento sgds (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching documento sgds
	 */
	public java.util.List<DocumentoSGD> findByUrlPagina(
		String urlPagina, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DocumentoSGD>
			orderByComparator);

	/**
	 * Returns an ordered range of all the documento sgds where urlPagina = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DocumentoSGDModelImpl</code>.
	 * </p>
	 *
	 * @param urlPagina the url pagina
	 * @param start the lower bound of the range of documento sgds
	 * @param end the upper bound of the range of documento sgds (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching documento sgds
	 */
	public java.util.List<DocumentoSGD> findByUrlPagina(
		String urlPagina, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DocumentoSGD>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first documento sgd in the ordered set where urlPagina = &#63;.
	 *
	 * @param urlPagina the url pagina
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching documento sgd
	 * @throws NoSuchDocumentoSGDException if a matching documento sgd could not be found
	 */
	public DocumentoSGD findByUrlPagina_First(
			String urlPagina,
			com.liferay.portal.kernel.util.OrderByComparator<DocumentoSGD>
				orderByComparator)
		throws NoSuchDocumentoSGDException;

	/**
	 * Returns the first documento sgd in the ordered set where urlPagina = &#63;.
	 *
	 * @param urlPagina the url pagina
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching documento sgd, or <code>null</code> if a matching documento sgd could not be found
	 */
	public DocumentoSGD fetchByUrlPagina_First(
		String urlPagina,
		com.liferay.portal.kernel.util.OrderByComparator<DocumentoSGD>
			orderByComparator);

	/**
	 * Returns the last documento sgd in the ordered set where urlPagina = &#63;.
	 *
	 * @param urlPagina the url pagina
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching documento sgd
	 * @throws NoSuchDocumentoSGDException if a matching documento sgd could not be found
	 */
	public DocumentoSGD findByUrlPagina_Last(
			String urlPagina,
			com.liferay.portal.kernel.util.OrderByComparator<DocumentoSGD>
				orderByComparator)
		throws NoSuchDocumentoSGDException;

	/**
	 * Returns the last documento sgd in the ordered set where urlPagina = &#63;.
	 *
	 * @param urlPagina the url pagina
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching documento sgd, or <code>null</code> if a matching documento sgd could not be found
	 */
	public DocumentoSGD fetchByUrlPagina_Last(
		String urlPagina,
		com.liferay.portal.kernel.util.OrderByComparator<DocumentoSGD>
			orderByComparator);

	/**
	 * Returns the documento sgds before and after the current documento sgd in the ordered set where urlPagina = &#63;.
	 *
	 * @param documentoId the primary key of the current documento sgd
	 * @param urlPagina the url pagina
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next documento sgd
	 * @throws NoSuchDocumentoSGDException if a documento sgd with the primary key could not be found
	 */
	public DocumentoSGD[] findByUrlPagina_PrevAndNext(
			long documentoId, String urlPagina,
			com.liferay.portal.kernel.util.OrderByComparator<DocumentoSGD>
				orderByComparator)
		throws NoSuchDocumentoSGDException;

	/**
	 * Removes all the documento sgds where urlPagina = &#63; from the database.
	 *
	 * @param urlPagina the url pagina
	 */
	public void removeByUrlPagina(String urlPagina);

	/**
	 * Returns the number of documento sgds where urlPagina = &#63;.
	 *
	 * @param urlPagina the url pagina
	 * @return the number of matching documento sgds
	 */
	public int countByUrlPagina(String urlPagina);

	/**
	 * Caches the documento sgd in the entity cache if it is enabled.
	 *
	 * @param documentoSGD the documento sgd
	 */
	public void cacheResult(DocumentoSGD documentoSGD);

	/**
	 * Caches the documento sgds in the entity cache if it is enabled.
	 *
	 * @param documentoSGDs the documento sgds
	 */
	public void cacheResult(java.util.List<DocumentoSGD> documentoSGDs);

	/**
	 * Creates a new documento sgd with the primary key. Does not add the documento sgd to the database.
	 *
	 * @param documentoId the primary key for the new documento sgd
	 * @return the new documento sgd
	 */
	public DocumentoSGD create(long documentoId);

	/**
	 * Removes the documento sgd with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param documentoId the primary key of the documento sgd
	 * @return the documento sgd that was removed
	 * @throws NoSuchDocumentoSGDException if a documento sgd with the primary key could not be found
	 */
	public DocumentoSGD remove(long documentoId)
		throws NoSuchDocumentoSGDException;

	public DocumentoSGD updateImpl(DocumentoSGD documentoSGD);

	/**
	 * Returns the documento sgd with the primary key or throws a <code>NoSuchDocumentoSGDException</code> if it could not be found.
	 *
	 * @param documentoId the primary key of the documento sgd
	 * @return the documento sgd
	 * @throws NoSuchDocumentoSGDException if a documento sgd with the primary key could not be found
	 */
	public DocumentoSGD findByPrimaryKey(long documentoId)
		throws NoSuchDocumentoSGDException;

	/**
	 * Returns the documento sgd with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param documentoId the primary key of the documento sgd
	 * @return the documento sgd, or <code>null</code> if a documento sgd with the primary key could not be found
	 */
	public DocumentoSGD fetchByPrimaryKey(long documentoId);

	/**
	 * Returns all the documento sgds.
	 *
	 * @return the documento sgds
	 */
	public java.util.List<DocumentoSGD> findAll();

	/**
	 * Returns a range of all the documento sgds.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DocumentoSGDModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of documento sgds
	 * @param end the upper bound of the range of documento sgds (not inclusive)
	 * @return the range of documento sgds
	 */
	public java.util.List<DocumentoSGD> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the documento sgds.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DocumentoSGDModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of documento sgds
	 * @param end the upper bound of the range of documento sgds (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of documento sgds
	 */
	public java.util.List<DocumentoSGD> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DocumentoSGD>
			orderByComparator);

	/**
	 * Returns an ordered range of all the documento sgds.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DocumentoSGDModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of documento sgds
	 * @param end the upper bound of the range of documento sgds (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of documento sgds
	 */
	public java.util.List<DocumentoSGD> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DocumentoSGD>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the documento sgds from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of documento sgds.
	 *
	 * @return the number of documento sgds
	 */
	public int countAll();

}