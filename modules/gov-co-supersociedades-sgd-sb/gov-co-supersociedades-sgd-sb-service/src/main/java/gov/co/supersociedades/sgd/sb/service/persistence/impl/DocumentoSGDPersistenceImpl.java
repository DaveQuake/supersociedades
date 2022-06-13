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

package gov.co.supersociedades.sgd.sb.service.persistence.impl;

import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.configuration.Configuration;
import com.liferay.portal.kernel.dao.orm.ArgumentsResolver;
import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.FinderCache;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.SessionFactory;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.MapUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;

import gov.co.supersociedades.sgd.sb.exception.NoSuchDocumentoSGDException;
import gov.co.supersociedades.sgd.sb.model.DocumentoSGD;
import gov.co.supersociedades.sgd.sb.model.impl.DocumentoSGDImpl;
import gov.co.supersociedades.sgd.sb.model.impl.DocumentoSGDModelImpl;
import gov.co.supersociedades.sgd.sb.service.persistence.DocumentoSGDPersistence;
import gov.co.supersociedades.sgd.sb.service.persistence.impl.constants.GestionesSGDPersistenceConstants;

import java.io.Serializable;

import java.lang.reflect.InvocationHandler;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.sql.DataSource;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * The persistence implementation for the documento sgd service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = DocumentoSGDPersistence.class)
public class DocumentoSGDPersistenceImpl
	extends BasePersistenceImpl<DocumentoSGD>
	implements DocumentoSGDPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>DocumentoSGDUtil</code> to access the documento sgd persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		DocumentoSGDImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;
	private FinderPath _finderPathWithPaginationFindByUuid;
	private FinderPath _finderPathWithoutPaginationFindByUuid;
	private FinderPath _finderPathCountByUuid;

	/**
	 * Returns all the documento sgds where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching documento sgds
	 */
	@Override
	public List<DocumentoSGD> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

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
	@Override
	public List<DocumentoSGD> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

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
	@Override
	public List<DocumentoSGD> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<DocumentoSGD> orderByComparator) {

		return findByUuid(uuid, start, end, orderByComparator, true);
	}

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
	@Override
	public List<DocumentoSGD> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<DocumentoSGD> orderByComparator,
		boolean useFinderCache) {

		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByUuid;
				finderArgs = new Object[] {uuid};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByUuid;
			finderArgs = new Object[] {uuid, start, end, orderByComparator};
		}

		List<DocumentoSGD> list = null;

		if (useFinderCache) {
			list = (List<DocumentoSGD>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (DocumentoSGD documentoSGD : list) {
					if (!uuid.equals(documentoSGD.getUuid())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler sb = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					3 + (orderByComparator.getOrderByFields().length * 2));
			}
			else {
				sb = new StringBundler(3);
			}

			sb.append(_SQL_SELECT_DOCUMENTOSGD_WHERE);

			boolean bindUuid = false;

			if (uuid.isEmpty()) {
				sb.append(_FINDER_COLUMN_UUID_UUID_3);
			}
			else {
				bindUuid = true;

				sb.append(_FINDER_COLUMN_UUID_UUID_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(DocumentoSGDModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindUuid) {
					queryPos.add(uuid);
				}

				list = (List<DocumentoSGD>)QueryUtil.list(
					query, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					finderCache.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first documento sgd in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching documento sgd
	 * @throws NoSuchDocumentoSGDException if a matching documento sgd could not be found
	 */
	@Override
	public DocumentoSGD findByUuid_First(
			String uuid, OrderByComparator<DocumentoSGD> orderByComparator)
		throws NoSuchDocumentoSGDException {

		DocumentoSGD documentoSGD = fetchByUuid_First(uuid, orderByComparator);

		if (documentoSGD != null) {
			return documentoSGD;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append("}");

		throw new NoSuchDocumentoSGDException(sb.toString());
	}

	/**
	 * Returns the first documento sgd in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching documento sgd, or <code>null</code> if a matching documento sgd could not be found
	 */
	@Override
	public DocumentoSGD fetchByUuid_First(
		String uuid, OrderByComparator<DocumentoSGD> orderByComparator) {

		List<DocumentoSGD> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last documento sgd in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching documento sgd
	 * @throws NoSuchDocumentoSGDException if a matching documento sgd could not be found
	 */
	@Override
	public DocumentoSGD findByUuid_Last(
			String uuid, OrderByComparator<DocumentoSGD> orderByComparator)
		throws NoSuchDocumentoSGDException {

		DocumentoSGD documentoSGD = fetchByUuid_Last(uuid, orderByComparator);

		if (documentoSGD != null) {
			return documentoSGD;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append("}");

		throw new NoSuchDocumentoSGDException(sb.toString());
	}

	/**
	 * Returns the last documento sgd in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching documento sgd, or <code>null</code> if a matching documento sgd could not be found
	 */
	@Override
	public DocumentoSGD fetchByUuid_Last(
		String uuid, OrderByComparator<DocumentoSGD> orderByComparator) {

		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<DocumentoSGD> list = findByUuid(
			uuid, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the documento sgds before and after the current documento sgd in the ordered set where uuid = &#63;.
	 *
	 * @param documentoId the primary key of the current documento sgd
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next documento sgd
	 * @throws NoSuchDocumentoSGDException if a documento sgd with the primary key could not be found
	 */
	@Override
	public DocumentoSGD[] findByUuid_PrevAndNext(
			long documentoId, String uuid,
			OrderByComparator<DocumentoSGD> orderByComparator)
		throws NoSuchDocumentoSGDException {

		uuid = Objects.toString(uuid, "");

		DocumentoSGD documentoSGD = findByPrimaryKey(documentoId);

		Session session = null;

		try {
			session = openSession();

			DocumentoSGD[] array = new DocumentoSGDImpl[3];

			array[0] = getByUuid_PrevAndNext(
				session, documentoSGD, uuid, orderByComparator, true);

			array[1] = documentoSGD;

			array[2] = getByUuid_PrevAndNext(
				session, documentoSGD, uuid, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected DocumentoSGD getByUuid_PrevAndNext(
		Session session, DocumentoSGD documentoSGD, String uuid,
		OrderByComparator<DocumentoSGD> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_DOCUMENTOSGD_WHERE);

		boolean bindUuid = false;

		if (uuid.isEmpty()) {
			sb.append(_FINDER_COLUMN_UUID_UUID_3);
		}
		else {
			bindUuid = true;

			sb.append(_FINDER_COLUMN_UUID_UUID_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields =
				orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				sb.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						sb.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN);
					}
					else {
						sb.append(WHERE_LESSER_THAN);
					}
				}
			}

			sb.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						sb.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC);
					}
					else {
						sb.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			sb.append(DocumentoSGDModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		if (bindUuid) {
			queryPos.add(uuid);
		}

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(documentoSGD)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<DocumentoSGD> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the documento sgds where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (DocumentoSGD documentoSGD :
				findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(documentoSGD);
		}
	}

	/**
	 * Returns the number of documento sgds where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching documento sgds
	 */
	@Override
	public int countByUuid(String uuid) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUuid;

		Object[] finderArgs = new Object[] {uuid};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_DOCUMENTOSGD_WHERE);

			boolean bindUuid = false;

			if (uuid.isEmpty()) {
				sb.append(_FINDER_COLUMN_UUID_UUID_3);
			}
			else {
				bindUuid = true;

				sb.append(_FINDER_COLUMN_UUID_UUID_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindUuid) {
					queryPos.add(uuid);
				}

				count = (Long)query.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_UUID_UUID_2 =
		"documentoSGD.uuid = ?";

	private static final String _FINDER_COLUMN_UUID_UUID_3 =
		"(documentoSGD.uuid IS NULL OR documentoSGD.uuid = '')";

	private FinderPath _finderPathWithPaginationFindByNombre;
	private FinderPath _finderPathWithoutPaginationFindByNombre;
	private FinderPath _finderPathCountByNombre;

	/**
	 * Returns all the documento sgds where nombre = &#63;.
	 *
	 * @param nombre the nombre
	 * @return the matching documento sgds
	 */
	@Override
	public List<DocumentoSGD> findByNombre(String nombre) {
		return findByNombre(nombre, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the documento sgds where nombre = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DocumentoSGDModelImpl</code>.
	 * </p>
	 *
	 * @param nombre the nombre
	 * @param start the lower bound of the range of documento sgds
	 * @param end the upper bound of the range of documento sgds (not inclusive)
	 * @return the range of matching documento sgds
	 */
	@Override
	public List<DocumentoSGD> findByNombre(String nombre, int start, int end) {
		return findByNombre(nombre, start, end, null);
	}

	/**
	 * Returns an ordered range of all the documento sgds where nombre = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DocumentoSGDModelImpl</code>.
	 * </p>
	 *
	 * @param nombre the nombre
	 * @param start the lower bound of the range of documento sgds
	 * @param end the upper bound of the range of documento sgds (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching documento sgds
	 */
	@Override
	public List<DocumentoSGD> findByNombre(
		String nombre, int start, int end,
		OrderByComparator<DocumentoSGD> orderByComparator) {

		return findByNombre(nombre, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the documento sgds where nombre = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DocumentoSGDModelImpl</code>.
	 * </p>
	 *
	 * @param nombre the nombre
	 * @param start the lower bound of the range of documento sgds
	 * @param end the upper bound of the range of documento sgds (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching documento sgds
	 */
	@Override
	public List<DocumentoSGD> findByNombre(
		String nombre, int start, int end,
		OrderByComparator<DocumentoSGD> orderByComparator,
		boolean useFinderCache) {

		nombre = Objects.toString(nombre, "");

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByNombre;
				finderArgs = new Object[] {nombre};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByNombre;
			finderArgs = new Object[] {nombre, start, end, orderByComparator};
		}

		List<DocumentoSGD> list = null;

		if (useFinderCache) {
			list = (List<DocumentoSGD>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (DocumentoSGD documentoSGD : list) {
					if (!nombre.equals(documentoSGD.getNombre())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler sb = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					3 + (orderByComparator.getOrderByFields().length * 2));
			}
			else {
				sb = new StringBundler(3);
			}

			sb.append(_SQL_SELECT_DOCUMENTOSGD_WHERE);

			boolean bindNombre = false;

			if (nombre.isEmpty()) {
				sb.append(_FINDER_COLUMN_NOMBRE_NOMBRE_3);
			}
			else {
				bindNombre = true;

				sb.append(_FINDER_COLUMN_NOMBRE_NOMBRE_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(DocumentoSGDModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindNombre) {
					queryPos.add(nombre);
				}

				list = (List<DocumentoSGD>)QueryUtil.list(
					query, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					finderCache.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first documento sgd in the ordered set where nombre = &#63;.
	 *
	 * @param nombre the nombre
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching documento sgd
	 * @throws NoSuchDocumentoSGDException if a matching documento sgd could not be found
	 */
	@Override
	public DocumentoSGD findByNombre_First(
			String nombre, OrderByComparator<DocumentoSGD> orderByComparator)
		throws NoSuchDocumentoSGDException {

		DocumentoSGD documentoSGD = fetchByNombre_First(
			nombre, orderByComparator);

		if (documentoSGD != null) {
			return documentoSGD;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("nombre=");
		sb.append(nombre);

		sb.append("}");

		throw new NoSuchDocumentoSGDException(sb.toString());
	}

	/**
	 * Returns the first documento sgd in the ordered set where nombre = &#63;.
	 *
	 * @param nombre the nombre
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching documento sgd, or <code>null</code> if a matching documento sgd could not be found
	 */
	@Override
	public DocumentoSGD fetchByNombre_First(
		String nombre, OrderByComparator<DocumentoSGD> orderByComparator) {

		List<DocumentoSGD> list = findByNombre(nombre, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last documento sgd in the ordered set where nombre = &#63;.
	 *
	 * @param nombre the nombre
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching documento sgd
	 * @throws NoSuchDocumentoSGDException if a matching documento sgd could not be found
	 */
	@Override
	public DocumentoSGD findByNombre_Last(
			String nombre, OrderByComparator<DocumentoSGD> orderByComparator)
		throws NoSuchDocumentoSGDException {

		DocumentoSGD documentoSGD = fetchByNombre_Last(
			nombre, orderByComparator);

		if (documentoSGD != null) {
			return documentoSGD;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("nombre=");
		sb.append(nombre);

		sb.append("}");

		throw new NoSuchDocumentoSGDException(sb.toString());
	}

	/**
	 * Returns the last documento sgd in the ordered set where nombre = &#63;.
	 *
	 * @param nombre the nombre
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching documento sgd, or <code>null</code> if a matching documento sgd could not be found
	 */
	@Override
	public DocumentoSGD fetchByNombre_Last(
		String nombre, OrderByComparator<DocumentoSGD> orderByComparator) {

		int count = countByNombre(nombre);

		if (count == 0) {
			return null;
		}

		List<DocumentoSGD> list = findByNombre(
			nombre, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the documento sgds before and after the current documento sgd in the ordered set where nombre = &#63;.
	 *
	 * @param documentoId the primary key of the current documento sgd
	 * @param nombre the nombre
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next documento sgd
	 * @throws NoSuchDocumentoSGDException if a documento sgd with the primary key could not be found
	 */
	@Override
	public DocumentoSGD[] findByNombre_PrevAndNext(
			long documentoId, String nombre,
			OrderByComparator<DocumentoSGD> orderByComparator)
		throws NoSuchDocumentoSGDException {

		nombre = Objects.toString(nombre, "");

		DocumentoSGD documentoSGD = findByPrimaryKey(documentoId);

		Session session = null;

		try {
			session = openSession();

			DocumentoSGD[] array = new DocumentoSGDImpl[3];

			array[0] = getByNombre_PrevAndNext(
				session, documentoSGD, nombre, orderByComparator, true);

			array[1] = documentoSGD;

			array[2] = getByNombre_PrevAndNext(
				session, documentoSGD, nombre, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected DocumentoSGD getByNombre_PrevAndNext(
		Session session, DocumentoSGD documentoSGD, String nombre,
		OrderByComparator<DocumentoSGD> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_DOCUMENTOSGD_WHERE);

		boolean bindNombre = false;

		if (nombre.isEmpty()) {
			sb.append(_FINDER_COLUMN_NOMBRE_NOMBRE_3);
		}
		else {
			bindNombre = true;

			sb.append(_FINDER_COLUMN_NOMBRE_NOMBRE_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields =
				orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				sb.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						sb.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN);
					}
					else {
						sb.append(WHERE_LESSER_THAN);
					}
				}
			}

			sb.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						sb.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC);
					}
					else {
						sb.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			sb.append(DocumentoSGDModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		if (bindNombre) {
			queryPos.add(nombre);
		}

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(documentoSGD)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<DocumentoSGD> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the documento sgds where nombre = &#63; from the database.
	 *
	 * @param nombre the nombre
	 */
	@Override
	public void removeByNombre(String nombre) {
		for (DocumentoSGD documentoSGD :
				findByNombre(
					nombre, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(documentoSGD);
		}
	}

	/**
	 * Returns the number of documento sgds where nombre = &#63;.
	 *
	 * @param nombre the nombre
	 * @return the number of matching documento sgds
	 */
	@Override
	public int countByNombre(String nombre) {
		nombre = Objects.toString(nombre, "");

		FinderPath finderPath = _finderPathCountByNombre;

		Object[] finderArgs = new Object[] {nombre};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_DOCUMENTOSGD_WHERE);

			boolean bindNombre = false;

			if (nombre.isEmpty()) {
				sb.append(_FINDER_COLUMN_NOMBRE_NOMBRE_3);
			}
			else {
				bindNombre = true;

				sb.append(_FINDER_COLUMN_NOMBRE_NOMBRE_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindNombre) {
					queryPos.add(nombre);
				}

				count = (Long)query.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_NOMBRE_NOMBRE_2 =
		"documentoSGD.nombre = ?";

	private static final String _FINDER_COLUMN_NOMBRE_NOMBRE_3 =
		"(documentoSGD.nombre IS NULL OR documentoSGD.nombre = '')";

	private FinderPath _finderPathWithPaginationFindByUrlPagina;
	private FinderPath _finderPathWithoutPaginationFindByUrlPagina;
	private FinderPath _finderPathCountByUrlPagina;

	/**
	 * Returns all the documento sgds where urlPagina = &#63;.
	 *
	 * @param urlPagina the url pagina
	 * @return the matching documento sgds
	 */
	@Override
	public List<DocumentoSGD> findByUrlPagina(String urlPagina) {
		return findByUrlPagina(
			urlPagina, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

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
	@Override
	public List<DocumentoSGD> findByUrlPagina(
		String urlPagina, int start, int end) {

		return findByUrlPagina(urlPagina, start, end, null);
	}

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
	@Override
	public List<DocumentoSGD> findByUrlPagina(
		String urlPagina, int start, int end,
		OrderByComparator<DocumentoSGD> orderByComparator) {

		return findByUrlPagina(urlPagina, start, end, orderByComparator, true);
	}

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
	@Override
	public List<DocumentoSGD> findByUrlPagina(
		String urlPagina, int start, int end,
		OrderByComparator<DocumentoSGD> orderByComparator,
		boolean useFinderCache) {

		urlPagina = Objects.toString(urlPagina, "");

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByUrlPagina;
				finderArgs = new Object[] {urlPagina};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByUrlPagina;
			finderArgs = new Object[] {
				urlPagina, start, end, orderByComparator
			};
		}

		List<DocumentoSGD> list = null;

		if (useFinderCache) {
			list = (List<DocumentoSGD>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (DocumentoSGD documentoSGD : list) {
					if (!urlPagina.equals(documentoSGD.getUrlPagina())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler sb = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					3 + (orderByComparator.getOrderByFields().length * 2));
			}
			else {
				sb = new StringBundler(3);
			}

			sb.append(_SQL_SELECT_DOCUMENTOSGD_WHERE);

			boolean bindUrlPagina = false;

			if (urlPagina.isEmpty()) {
				sb.append(_FINDER_COLUMN_URLPAGINA_URLPAGINA_3);
			}
			else {
				bindUrlPagina = true;

				sb.append(_FINDER_COLUMN_URLPAGINA_URLPAGINA_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(DocumentoSGDModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindUrlPagina) {
					queryPos.add(urlPagina);
				}

				list = (List<DocumentoSGD>)QueryUtil.list(
					query, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					finderCache.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first documento sgd in the ordered set where urlPagina = &#63;.
	 *
	 * @param urlPagina the url pagina
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching documento sgd
	 * @throws NoSuchDocumentoSGDException if a matching documento sgd could not be found
	 */
	@Override
	public DocumentoSGD findByUrlPagina_First(
			String urlPagina, OrderByComparator<DocumentoSGD> orderByComparator)
		throws NoSuchDocumentoSGDException {

		DocumentoSGD documentoSGD = fetchByUrlPagina_First(
			urlPagina, orderByComparator);

		if (documentoSGD != null) {
			return documentoSGD;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("urlPagina=");
		sb.append(urlPagina);

		sb.append("}");

		throw new NoSuchDocumentoSGDException(sb.toString());
	}

	/**
	 * Returns the first documento sgd in the ordered set where urlPagina = &#63;.
	 *
	 * @param urlPagina the url pagina
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching documento sgd, or <code>null</code> if a matching documento sgd could not be found
	 */
	@Override
	public DocumentoSGD fetchByUrlPagina_First(
		String urlPagina, OrderByComparator<DocumentoSGD> orderByComparator) {

		List<DocumentoSGD> list = findByUrlPagina(
			urlPagina, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last documento sgd in the ordered set where urlPagina = &#63;.
	 *
	 * @param urlPagina the url pagina
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching documento sgd
	 * @throws NoSuchDocumentoSGDException if a matching documento sgd could not be found
	 */
	@Override
	public DocumentoSGD findByUrlPagina_Last(
			String urlPagina, OrderByComparator<DocumentoSGD> orderByComparator)
		throws NoSuchDocumentoSGDException {

		DocumentoSGD documentoSGD = fetchByUrlPagina_Last(
			urlPagina, orderByComparator);

		if (documentoSGD != null) {
			return documentoSGD;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("urlPagina=");
		sb.append(urlPagina);

		sb.append("}");

		throw new NoSuchDocumentoSGDException(sb.toString());
	}

	/**
	 * Returns the last documento sgd in the ordered set where urlPagina = &#63;.
	 *
	 * @param urlPagina the url pagina
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching documento sgd, or <code>null</code> if a matching documento sgd could not be found
	 */
	@Override
	public DocumentoSGD fetchByUrlPagina_Last(
		String urlPagina, OrderByComparator<DocumentoSGD> orderByComparator) {

		int count = countByUrlPagina(urlPagina);

		if (count == 0) {
			return null;
		}

		List<DocumentoSGD> list = findByUrlPagina(
			urlPagina, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the documento sgds before and after the current documento sgd in the ordered set where urlPagina = &#63;.
	 *
	 * @param documentoId the primary key of the current documento sgd
	 * @param urlPagina the url pagina
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next documento sgd
	 * @throws NoSuchDocumentoSGDException if a documento sgd with the primary key could not be found
	 */
	@Override
	public DocumentoSGD[] findByUrlPagina_PrevAndNext(
			long documentoId, String urlPagina,
			OrderByComparator<DocumentoSGD> orderByComparator)
		throws NoSuchDocumentoSGDException {

		urlPagina = Objects.toString(urlPagina, "");

		DocumentoSGD documentoSGD = findByPrimaryKey(documentoId);

		Session session = null;

		try {
			session = openSession();

			DocumentoSGD[] array = new DocumentoSGDImpl[3];

			array[0] = getByUrlPagina_PrevAndNext(
				session, documentoSGD, urlPagina, orderByComparator, true);

			array[1] = documentoSGD;

			array[2] = getByUrlPagina_PrevAndNext(
				session, documentoSGD, urlPagina, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected DocumentoSGD getByUrlPagina_PrevAndNext(
		Session session, DocumentoSGD documentoSGD, String urlPagina,
		OrderByComparator<DocumentoSGD> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_DOCUMENTOSGD_WHERE);

		boolean bindUrlPagina = false;

		if (urlPagina.isEmpty()) {
			sb.append(_FINDER_COLUMN_URLPAGINA_URLPAGINA_3);
		}
		else {
			bindUrlPagina = true;

			sb.append(_FINDER_COLUMN_URLPAGINA_URLPAGINA_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields =
				orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				sb.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						sb.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN);
					}
					else {
						sb.append(WHERE_LESSER_THAN);
					}
				}
			}

			sb.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						sb.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC);
					}
					else {
						sb.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			sb.append(DocumentoSGDModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		if (bindUrlPagina) {
			queryPos.add(urlPagina);
		}

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(documentoSGD)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<DocumentoSGD> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the documento sgds where urlPagina = &#63; from the database.
	 *
	 * @param urlPagina the url pagina
	 */
	@Override
	public void removeByUrlPagina(String urlPagina) {
		for (DocumentoSGD documentoSGD :
				findByUrlPagina(
					urlPagina, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(documentoSGD);
		}
	}

	/**
	 * Returns the number of documento sgds where urlPagina = &#63;.
	 *
	 * @param urlPagina the url pagina
	 * @return the number of matching documento sgds
	 */
	@Override
	public int countByUrlPagina(String urlPagina) {
		urlPagina = Objects.toString(urlPagina, "");

		FinderPath finderPath = _finderPathCountByUrlPagina;

		Object[] finderArgs = new Object[] {urlPagina};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_DOCUMENTOSGD_WHERE);

			boolean bindUrlPagina = false;

			if (urlPagina.isEmpty()) {
				sb.append(_FINDER_COLUMN_URLPAGINA_URLPAGINA_3);
			}
			else {
				bindUrlPagina = true;

				sb.append(_FINDER_COLUMN_URLPAGINA_URLPAGINA_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindUrlPagina) {
					queryPos.add(urlPagina);
				}

				count = (Long)query.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_URLPAGINA_URLPAGINA_2 =
		"documentoSGD.urlPagina = ?";

	private static final String _FINDER_COLUMN_URLPAGINA_URLPAGINA_3 =
		"(documentoSGD.urlPagina IS NULL OR documentoSGD.urlPagina = '')";

	public DocumentoSGDPersistenceImpl() {
		Map<String, String> dbColumnNames = new HashMap<String, String>();

		dbColumnNames.put("uuid", "uuid_");

		setDBColumnNames(dbColumnNames);

		setModelClass(DocumentoSGD.class);

		setModelImplClass(DocumentoSGDImpl.class);
		setModelPKClass(long.class);
	}

	/**
	 * Caches the documento sgd in the entity cache if it is enabled.
	 *
	 * @param documentoSGD the documento sgd
	 */
	@Override
	public void cacheResult(DocumentoSGD documentoSGD) {
		entityCache.putResult(
			DocumentoSGDImpl.class, documentoSGD.getPrimaryKey(), documentoSGD);
	}

	/**
	 * Caches the documento sgds in the entity cache if it is enabled.
	 *
	 * @param documentoSGDs the documento sgds
	 */
	@Override
	public void cacheResult(List<DocumentoSGD> documentoSGDs) {
		for (DocumentoSGD documentoSGD : documentoSGDs) {
			if (entityCache.getResult(
					DocumentoSGDImpl.class, documentoSGD.getPrimaryKey()) ==
						null) {

				cacheResult(documentoSGD);
			}
		}
	}

	/**
	 * Clears the cache for all documento sgds.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(DocumentoSGDImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the documento sgd.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(DocumentoSGD documentoSGD) {
		entityCache.removeResult(DocumentoSGDImpl.class, documentoSGD);
	}

	@Override
	public void clearCache(List<DocumentoSGD> documentoSGDs) {
		for (DocumentoSGD documentoSGD : documentoSGDs) {
			entityCache.removeResult(DocumentoSGDImpl.class, documentoSGD);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(DocumentoSGDImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new documento sgd with the primary key. Does not add the documento sgd to the database.
	 *
	 * @param documentoId the primary key for the new documento sgd
	 * @return the new documento sgd
	 */
	@Override
	public DocumentoSGD create(long documentoId) {
		DocumentoSGD documentoSGD = new DocumentoSGDImpl();

		documentoSGD.setNew(true);
		documentoSGD.setPrimaryKey(documentoId);

		String uuid = PortalUUIDUtil.generate();

		documentoSGD.setUuid(uuid);

		return documentoSGD;
	}

	/**
	 * Removes the documento sgd with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param documentoId the primary key of the documento sgd
	 * @return the documento sgd that was removed
	 * @throws NoSuchDocumentoSGDException if a documento sgd with the primary key could not be found
	 */
	@Override
	public DocumentoSGD remove(long documentoId)
		throws NoSuchDocumentoSGDException {

		return remove((Serializable)documentoId);
	}

	/**
	 * Removes the documento sgd with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the documento sgd
	 * @return the documento sgd that was removed
	 * @throws NoSuchDocumentoSGDException if a documento sgd with the primary key could not be found
	 */
	@Override
	public DocumentoSGD remove(Serializable primaryKey)
		throws NoSuchDocumentoSGDException {

		Session session = null;

		try {
			session = openSession();

			DocumentoSGD documentoSGD = (DocumentoSGD)session.get(
				DocumentoSGDImpl.class, primaryKey);

			if (documentoSGD == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchDocumentoSGDException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(documentoSGD);
		}
		catch (NoSuchDocumentoSGDException noSuchEntityException) {
			throw noSuchEntityException;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	protected DocumentoSGD removeImpl(DocumentoSGD documentoSGD) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(documentoSGD)) {
				documentoSGD = (DocumentoSGD)session.get(
					DocumentoSGDImpl.class, documentoSGD.getPrimaryKeyObj());
			}

			if (documentoSGD != null) {
				session.delete(documentoSGD);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (documentoSGD != null) {
			clearCache(documentoSGD);
		}

		return documentoSGD;
	}

	@Override
	public DocumentoSGD updateImpl(DocumentoSGD documentoSGD) {
		boolean isNew = documentoSGD.isNew();

		if (!(documentoSGD instanceof DocumentoSGDModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(documentoSGD.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					documentoSGD);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in documentoSGD proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom DocumentoSGD implementation " +
					documentoSGD.getClass());
		}

		DocumentoSGDModelImpl documentoSGDModelImpl =
			(DocumentoSGDModelImpl)documentoSGD;

		if (Validator.isNull(documentoSGD.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			documentoSGD.setUuid(uuid);
		}

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(documentoSGD);
			}
			else {
				documentoSGD = (DocumentoSGD)session.merge(documentoSGD);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			DocumentoSGDImpl.class, documentoSGDModelImpl, false, true);

		if (isNew) {
			documentoSGD.setNew(false);
		}

		documentoSGD.resetOriginalValues();

		return documentoSGD;
	}

	/**
	 * Returns the documento sgd with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the documento sgd
	 * @return the documento sgd
	 * @throws NoSuchDocumentoSGDException if a documento sgd with the primary key could not be found
	 */
	@Override
	public DocumentoSGD findByPrimaryKey(Serializable primaryKey)
		throws NoSuchDocumentoSGDException {

		DocumentoSGD documentoSGD = fetchByPrimaryKey(primaryKey);

		if (documentoSGD == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchDocumentoSGDException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return documentoSGD;
	}

	/**
	 * Returns the documento sgd with the primary key or throws a <code>NoSuchDocumentoSGDException</code> if it could not be found.
	 *
	 * @param documentoId the primary key of the documento sgd
	 * @return the documento sgd
	 * @throws NoSuchDocumentoSGDException if a documento sgd with the primary key could not be found
	 */
	@Override
	public DocumentoSGD findByPrimaryKey(long documentoId)
		throws NoSuchDocumentoSGDException {

		return findByPrimaryKey((Serializable)documentoId);
	}

	/**
	 * Returns the documento sgd with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param documentoId the primary key of the documento sgd
	 * @return the documento sgd, or <code>null</code> if a documento sgd with the primary key could not be found
	 */
	@Override
	public DocumentoSGD fetchByPrimaryKey(long documentoId) {
		return fetchByPrimaryKey((Serializable)documentoId);
	}

	/**
	 * Returns all the documento sgds.
	 *
	 * @return the documento sgds
	 */
	@Override
	public List<DocumentoSGD> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

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
	@Override
	public List<DocumentoSGD> findAll(int start, int end) {
		return findAll(start, end, null);
	}

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
	@Override
	public List<DocumentoSGD> findAll(
		int start, int end, OrderByComparator<DocumentoSGD> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

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
	@Override
	public List<DocumentoSGD> findAll(
		int start, int end, OrderByComparator<DocumentoSGD> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindAll;
				finderArgs = FINDER_ARGS_EMPTY;
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindAll;
			finderArgs = new Object[] {start, end, orderByComparator};
		}

		List<DocumentoSGD> list = null;

		if (useFinderCache) {
			list = (List<DocumentoSGD>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_DOCUMENTOSGD);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_DOCUMENTOSGD;

				sql = sql.concat(DocumentoSGDModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<DocumentoSGD>)QueryUtil.list(
					query, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					finderCache.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the documento sgds from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (DocumentoSGD documentoSGD : findAll()) {
			remove(documentoSGD);
		}
	}

	/**
	 * Returns the number of documento sgds.
	 *
	 * @return the number of documento sgds
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_DOCUMENTOSGD);

				count = (Long)query.uniqueResult();

				finderCache.putResult(
					_finderPathCountAll, FINDER_ARGS_EMPTY, count);
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	@Override
	public Set<String> getBadColumnNames() {
		return _badColumnNames;
	}

	@Override
	protected EntityCache getEntityCache() {
		return entityCache;
	}

	@Override
	protected String getPKDBName() {
		return "documentoId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_DOCUMENTOSGD;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return DocumentoSGDModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the documento sgd persistence.
	 */
	@Activate
	public void activate(BundleContext bundleContext) {
		_bundleContext = bundleContext;

		_argumentsResolverServiceRegistration = _bundleContext.registerService(
			ArgumentsResolver.class, new DocumentoSGDModelArgumentsResolver(),
			MapUtil.singletonDictionary(
				"model.class.name", DocumentoSGD.class.getName()));

		_finderPathWithPaginationFindAll = _createFinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0],
			new String[0], true);

		_finderPathWithoutPaginationFindAll = _createFinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0],
			new String[0], true);

		_finderPathCountAll = _createFinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0], new String[0], false);

		_finderPathWithPaginationFindByUuid = _createFinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			},
			new String[] {"uuid_"}, true);

		_finderPathWithoutPaginationFindByUuid = _createFinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] {String.class.getName()}, new String[] {"uuid_"},
			true);

		_finderPathCountByUuid = _createFinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] {String.class.getName()}, new String[] {"uuid_"},
			false);

		_finderPathWithPaginationFindByNombre = _createFinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByNombre",
			new String[] {
				String.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			},
			new String[] {"nombre"}, true);

		_finderPathWithoutPaginationFindByNombre = _createFinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByNombre",
			new String[] {String.class.getName()}, new String[] {"nombre"},
			true);

		_finderPathCountByNombre = _createFinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByNombre",
			new String[] {String.class.getName()}, new String[] {"nombre"},
			false);

		_finderPathWithPaginationFindByUrlPagina = _createFinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUrlPagina",
			new String[] {
				String.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			},
			new String[] {"urlPagina"}, true);

		_finderPathWithoutPaginationFindByUrlPagina = _createFinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUrlPagina",
			new String[] {String.class.getName()}, new String[] {"urlPagina"},
			true);

		_finderPathCountByUrlPagina = _createFinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUrlPagina",
			new String[] {String.class.getName()}, new String[] {"urlPagina"},
			false);
	}

	@Deactivate
	public void deactivate() {
		entityCache.removeCache(DocumentoSGDImpl.class.getName());

		_argumentsResolverServiceRegistration.unregister();

		for (ServiceRegistration<FinderPath> serviceRegistration :
				_serviceRegistrations) {

			serviceRegistration.unregister();
		}
	}

	@Override
	@Reference(
		target = GestionesSGDPersistenceConstants.SERVICE_CONFIGURATION_FILTER,
		unbind = "-"
	)
	public void setConfiguration(Configuration configuration) {
	}

	@Override
	@Reference(
		target = GestionesSGDPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setDataSource(DataSource dataSource) {
		super.setDataSource(dataSource);
	}

	@Override
	@Reference(
		target = GestionesSGDPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	private BundleContext _bundleContext;

	@Reference
	protected EntityCache entityCache;

	@Reference
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_DOCUMENTOSGD =
		"SELECT documentoSGD FROM DocumentoSGD documentoSGD";

	private static final String _SQL_SELECT_DOCUMENTOSGD_WHERE =
		"SELECT documentoSGD FROM DocumentoSGD documentoSGD WHERE ";

	private static final String _SQL_COUNT_DOCUMENTOSGD =
		"SELECT COUNT(documentoSGD) FROM DocumentoSGD documentoSGD";

	private static final String _SQL_COUNT_DOCUMENTOSGD_WHERE =
		"SELECT COUNT(documentoSGD) FROM DocumentoSGD documentoSGD WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "documentoSGD.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No DocumentoSGD exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No DocumentoSGD exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		DocumentoSGDPersistenceImpl.class);

	private static final Set<String> _badColumnNames = SetUtil.fromArray(
		new String[] {"uuid"});

	static {
		try {
			Class.forName(GestionesSGDPersistenceConstants.class.getName());
		}
		catch (ClassNotFoundException classNotFoundException) {
			throw new ExceptionInInitializerError(classNotFoundException);
		}
	}

	private FinderPath _createFinderPath(
		String cacheName, String methodName, String[] params,
		String[] columnNames, boolean baseModelResult) {

		FinderPath finderPath = new FinderPath(
			cacheName, methodName, params, columnNames, baseModelResult);

		if (!cacheName.equals(FINDER_CLASS_NAME_LIST_WITH_PAGINATION)) {
			_serviceRegistrations.add(
				_bundleContext.registerService(
					FinderPath.class, finderPath,
					MapUtil.singletonDictionary("cache.name", cacheName)));
		}

		return finderPath;
	}

	private Set<ServiceRegistration<FinderPath>> _serviceRegistrations =
		new HashSet<>();
	private ServiceRegistration<ArgumentsResolver>
		_argumentsResolverServiceRegistration;

	private static class DocumentoSGDModelArgumentsResolver
		implements ArgumentsResolver {

		@Override
		public Object[] getArguments(
			FinderPath finderPath, BaseModel<?> baseModel, boolean checkColumn,
			boolean original) {

			String[] columnNames = finderPath.getColumnNames();

			if ((columnNames == null) || (columnNames.length == 0)) {
				if (baseModel.isNew()) {
					return FINDER_ARGS_EMPTY;
				}

				return null;
			}

			DocumentoSGDModelImpl documentoSGDModelImpl =
				(DocumentoSGDModelImpl)baseModel;

			long columnBitmask = documentoSGDModelImpl.getColumnBitmask();

			if (!checkColumn || (columnBitmask == 0)) {
				return _getValue(documentoSGDModelImpl, columnNames, original);
			}

			Long finderPathColumnBitmask = _finderPathColumnBitmasksCache.get(
				finderPath);

			if (finderPathColumnBitmask == null) {
				finderPathColumnBitmask = 0L;

				for (String columnName : columnNames) {
					finderPathColumnBitmask |=
						documentoSGDModelImpl.getColumnBitmask(columnName);
				}

				_finderPathColumnBitmasksCache.put(
					finderPath, finderPathColumnBitmask);
			}

			if ((columnBitmask & finderPathColumnBitmask) != 0) {
				return _getValue(documentoSGDModelImpl, columnNames, original);
			}

			return null;
		}

		private Object[] _getValue(
			DocumentoSGDModelImpl documentoSGDModelImpl, String[] columnNames,
			boolean original) {

			Object[] arguments = new Object[columnNames.length];

			for (int i = 0; i < arguments.length; i++) {
				String columnName = columnNames[i];

				if (original) {
					arguments[i] = documentoSGDModelImpl.getColumnOriginalValue(
						columnName);
				}
				else {
					arguments[i] = documentoSGDModelImpl.getColumnValue(
						columnName);
				}
			}

			return arguments;
		}

		private static Map<FinderPath, Long> _finderPathColumnBitmasksCache =
			new ConcurrentHashMap<>();

	}

}