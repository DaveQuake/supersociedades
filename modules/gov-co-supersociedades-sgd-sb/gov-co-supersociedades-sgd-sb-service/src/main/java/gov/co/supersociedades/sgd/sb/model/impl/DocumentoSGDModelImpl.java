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

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.ProxyUtil;

import gov.co.supersociedades.sgd.sb.model.DocumentoSGD;
import gov.co.supersociedades.sgd.sb.model.DocumentoSGDModel;

import java.io.Serializable;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;

import java.sql.Types;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * The base model implementation for the DocumentoSGD service. Represents a row in the &quot;gov_documento_sgd&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>DocumentoSGDModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link DocumentoSGDImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see DocumentoSGDImpl
 * @generated
 */
public class DocumentoSGDModelImpl
	extends BaseModelImpl<DocumentoSGD> implements DocumentoSGDModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a documento sgd model instance should use the <code>DocumentoSGD</code> interface instead.
	 */
	public static final String TABLE_NAME = "gov_documento_sgd";

	public static final Object[][] TABLE_COLUMNS = {
		{"uuid_", Types.VARCHAR}, {"documentoId", Types.BIGINT},
		{"nombre", Types.VARCHAR}, {"createDate", Types.TIMESTAMP},
		{"inicioPublicacion", Types.TIMESTAMP},
		{"finPublicacion", Types.TIMESTAMP}, {"epigrafe", Types.VARCHAR},
		{"urlDocumento", Types.VARCHAR}, {"urlPagina", Types.VARCHAR},
		{"categoria", Types.VARCHAR}, {"tema", Types.VARCHAR},
		{"etiqueta", Types.VARCHAR}, {"palabraClave", Types.VARCHAR},
		{"numRadicado", Types.VARCHAR}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("uuid_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("documentoId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("nombre", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("inicioPublicacion", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("finPublicacion", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("epigrafe", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("urlDocumento", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("urlPagina", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("categoria", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("tema", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("etiqueta", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("palabraClave", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("numRadicado", Types.VARCHAR);
	}

	public static final String TABLE_SQL_CREATE =
		"create table gov_documento_sgd (uuid_ VARCHAR(75) null,documentoId LONG not null primary key,nombre VARCHAR(75) null,createDate DATE null,inicioPublicacion DATE null,finPublicacion DATE null,epigrafe VARCHAR(75) null,urlDocumento VARCHAR(75) null,urlPagina VARCHAR(75) null,categoria VARCHAR(75) null,tema VARCHAR(75) null,etiqueta VARCHAR(75) null,palabraClave VARCHAR(75) null,numRadicado VARCHAR(75) null)";

	public static final String TABLE_SQL_DROP = "drop table gov_documento_sgd";

	public static final String ORDER_BY_JPQL =
		" ORDER BY documentoSGD.createDate DESC";

	public static final String ORDER_BY_SQL =
		" ORDER BY gov_documento_sgd.createDate DESC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)
	 */
	@Deprecated
	public static final long NOMBRE_COLUMN_BITMASK = 1L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)
	 */
	@Deprecated
	public static final long URLPAGINA_COLUMN_BITMASK = 2L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)
	 */
	@Deprecated
	public static final long UUID_COLUMN_BITMASK = 4L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *		#getColumnBitmask(String)
	 */
	@Deprecated
	public static final long CREATEDATE_COLUMN_BITMASK = 8L;

	/**
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	public static void setEntityCacheEnabled(boolean entityCacheEnabled) {
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	public static void setFinderCacheEnabled(boolean finderCacheEnabled) {
	}

	public DocumentoSGDModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _documentoId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setDocumentoId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _documentoId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return DocumentoSGD.class;
	}

	@Override
	public String getModelClassName() {
		return DocumentoSGD.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<DocumentoSGD, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		for (Map.Entry<String, Function<DocumentoSGD, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<DocumentoSGD, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((DocumentoSGD)this));
		}

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<DocumentoSGD, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<DocumentoSGD, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(DocumentoSGD)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<DocumentoSGD, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<DocumentoSGD, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function<InvocationHandler, DocumentoSGD>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			DocumentoSGD.class.getClassLoader(), DocumentoSGD.class,
			ModelWrapper.class);

		try {
			Constructor<DocumentoSGD> constructor =
				(Constructor<DocumentoSGD>)proxyClass.getConstructor(
					InvocationHandler.class);

			return invocationHandler -> {
				try {
					return constructor.newInstance(invocationHandler);
				}
				catch (ReflectiveOperationException
							reflectiveOperationException) {

					throw new InternalError(reflectiveOperationException);
				}
			};
		}
		catch (NoSuchMethodException noSuchMethodException) {
			throw new InternalError(noSuchMethodException);
		}
	}

	private static final Map<String, Function<DocumentoSGD, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<DocumentoSGD, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<DocumentoSGD, Object>> attributeGetterFunctions =
			new LinkedHashMap<String, Function<DocumentoSGD, Object>>();
		Map<String, BiConsumer<DocumentoSGD, ?>> attributeSetterBiConsumers =
			new LinkedHashMap<String, BiConsumer<DocumentoSGD, ?>>();

		attributeGetterFunctions.put("uuid", DocumentoSGD::getUuid);
		attributeSetterBiConsumers.put(
			"uuid", (BiConsumer<DocumentoSGD, String>)DocumentoSGD::setUuid);
		attributeGetterFunctions.put(
			"documentoId", DocumentoSGD::getDocumentoId);
		attributeSetterBiConsumers.put(
			"documentoId",
			(BiConsumer<DocumentoSGD, Long>)DocumentoSGD::setDocumentoId);
		attributeGetterFunctions.put("nombre", DocumentoSGD::getNombre);
		attributeSetterBiConsumers.put(
			"nombre",
			(BiConsumer<DocumentoSGD, String>)DocumentoSGD::setNombre);
		attributeGetterFunctions.put("createDate", DocumentoSGD::getCreateDate);
		attributeSetterBiConsumers.put(
			"createDate",
			(BiConsumer<DocumentoSGD, Date>)DocumentoSGD::setCreateDate);
		attributeGetterFunctions.put(
			"inicioPublicacion", DocumentoSGD::getInicioPublicacion);
		attributeSetterBiConsumers.put(
			"inicioPublicacion",
			(BiConsumer<DocumentoSGD, Date>)DocumentoSGD::setInicioPublicacion);
		attributeGetterFunctions.put(
			"finPublicacion", DocumentoSGD::getFinPublicacion);
		attributeSetterBiConsumers.put(
			"finPublicacion",
			(BiConsumer<DocumentoSGD, Date>)DocumentoSGD::setFinPublicacion);
		attributeGetterFunctions.put("epigrafe", DocumentoSGD::getEpigrafe);
		attributeSetterBiConsumers.put(
			"epigrafe",
			(BiConsumer<DocumentoSGD, String>)DocumentoSGD::setEpigrafe);
		attributeGetterFunctions.put(
			"urlDocumento", DocumentoSGD::getUrlDocumento);
		attributeSetterBiConsumers.put(
			"urlDocumento",
			(BiConsumer<DocumentoSGD, String>)DocumentoSGD::setUrlDocumento);
		attributeGetterFunctions.put("urlPagina", DocumentoSGD::getUrlPagina);
		attributeSetterBiConsumers.put(
			"urlPagina",
			(BiConsumer<DocumentoSGD, String>)DocumentoSGD::setUrlPagina);
		attributeGetterFunctions.put("categoria", DocumentoSGD::getCategoria);
		attributeSetterBiConsumers.put(
			"categoria",
			(BiConsumer<DocumentoSGD, String>)DocumentoSGD::setCategoria);
		attributeGetterFunctions.put("tema", DocumentoSGD::getTema);
		attributeSetterBiConsumers.put(
			"tema", (BiConsumer<DocumentoSGD, String>)DocumentoSGD::setTema);
		attributeGetterFunctions.put("etiqueta", DocumentoSGD::getEtiqueta);
		attributeSetterBiConsumers.put(
			"etiqueta",
			(BiConsumer<DocumentoSGD, String>)DocumentoSGD::setEtiqueta);
		attributeGetterFunctions.put(
			"palabraClave", DocumentoSGD::getPalabraClave);
		attributeSetterBiConsumers.put(
			"palabraClave",
			(BiConsumer<DocumentoSGD, String>)DocumentoSGD::setPalabraClave);
		attributeGetterFunctions.put(
			"numRadicado", DocumentoSGD::getNumRadicado);
		attributeSetterBiConsumers.put(
			"numRadicado",
			(BiConsumer<DocumentoSGD, String>)DocumentoSGD::setNumRadicado);

		_attributeGetterFunctions = Collections.unmodifiableMap(
			attributeGetterFunctions);
		_attributeSetterBiConsumers = Collections.unmodifiableMap(
			(Map)attributeSetterBiConsumers);
	}

	@Override
	public String getUuid() {
		if (_uuid == null) {
			return "";
		}
		else {
			return _uuid;
		}
	}

	@Override
	public void setUuid(String uuid) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_uuid = uuid;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public String getOriginalUuid() {
		return getColumnOriginalValue("uuid_");
	}

	@Override
	public long getDocumentoId() {
		return _documentoId;
	}

	@Override
	public void setDocumentoId(long documentoId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_documentoId = documentoId;
	}

	@Override
	public String getNombre() {
		if (_nombre == null) {
			return "";
		}
		else {
			return _nombre;
		}
	}

	@Override
	public void setNombre(String nombre) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_nombre = nombre;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public String getOriginalNombre() {
		return getColumnOriginalValue("nombre");
	}

	@Override
	public Date getCreateDate() {
		return _createDate;
	}

	@Override
	public void setCreateDate(Date createDate) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_createDate = createDate;
	}

	@Override
	public Date getInicioPublicacion() {
		return _inicioPublicacion;
	}

	@Override
	public void setInicioPublicacion(Date inicioPublicacion) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_inicioPublicacion = inicioPublicacion;
	}

	@Override
	public Date getFinPublicacion() {
		return _finPublicacion;
	}

	@Override
	public void setFinPublicacion(Date finPublicacion) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_finPublicacion = finPublicacion;
	}

	@Override
	public String getEpigrafe() {
		if (_epigrafe == null) {
			return "";
		}
		else {
			return _epigrafe;
		}
	}

	@Override
	public void setEpigrafe(String epigrafe) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_epigrafe = epigrafe;
	}

	@Override
	public String getUrlDocumento() {
		if (_urlDocumento == null) {
			return "";
		}
		else {
			return _urlDocumento;
		}
	}

	@Override
	public void setUrlDocumento(String urlDocumento) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_urlDocumento = urlDocumento;
	}

	@Override
	public String getUrlPagina() {
		if (_urlPagina == null) {
			return "";
		}
		else {
			return _urlPagina;
		}
	}

	@Override
	public void setUrlPagina(String urlPagina) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_urlPagina = urlPagina;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public String getOriginalUrlPagina() {
		return getColumnOriginalValue("urlPagina");
	}

	@Override
	public String getCategoria() {
		if (_categoria == null) {
			return "";
		}
		else {
			return _categoria;
		}
	}

	@Override
	public void setCategoria(String categoria) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_categoria = categoria;
	}

	@Override
	public String getTema() {
		if (_tema == null) {
			return "";
		}
		else {
			return _tema;
		}
	}

	@Override
	public void setTema(String tema) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_tema = tema;
	}

	@Override
	public String getEtiqueta() {
		if (_etiqueta == null) {
			return "";
		}
		else {
			return _etiqueta;
		}
	}

	@Override
	public void setEtiqueta(String etiqueta) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_etiqueta = etiqueta;
	}

	@Override
	public String getPalabraClave() {
		if (_palabraClave == null) {
			return "";
		}
		else {
			return _palabraClave;
		}
	}

	@Override
	public void setPalabraClave(String palabraClave) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_palabraClave = palabraClave;
	}

	@Override
	public String getNumRadicado() {
		if (_numRadicado == null) {
			return "";
		}
		else {
			return _numRadicado;
		}
	}

	@Override
	public void setNumRadicado(String numRadicado) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_numRadicado = numRadicado;
	}

	public long getColumnBitmask() {
		if (_columnBitmask > 0) {
			return _columnBitmask;
		}

		if ((_columnOriginalValues == null) ||
			(_columnOriginalValues == Collections.EMPTY_MAP)) {

			return 0;
		}

		for (Map.Entry<String, Object> entry :
				_columnOriginalValues.entrySet()) {

			if (entry.getValue() != getColumnValue(entry.getKey())) {
				_columnBitmask |= _columnBitmasks.get(entry.getKey());
			}
		}

		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(
			0, DocumentoSGD.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public DocumentoSGD toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, DocumentoSGD>
				escapedModelProxyProviderFunction =
					EscapedModelProxyProviderFunctionHolder.
						_escapedModelProxyProviderFunction;

			_escapedModel = escapedModelProxyProviderFunction.apply(
				new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		DocumentoSGDImpl documentoSGDImpl = new DocumentoSGDImpl();

		documentoSGDImpl.setUuid(getUuid());
		documentoSGDImpl.setDocumentoId(getDocumentoId());
		documentoSGDImpl.setNombre(getNombre());
		documentoSGDImpl.setCreateDate(getCreateDate());
		documentoSGDImpl.setInicioPublicacion(getInicioPublicacion());
		documentoSGDImpl.setFinPublicacion(getFinPublicacion());
		documentoSGDImpl.setEpigrafe(getEpigrafe());
		documentoSGDImpl.setUrlDocumento(getUrlDocumento());
		documentoSGDImpl.setUrlPagina(getUrlPagina());
		documentoSGDImpl.setCategoria(getCategoria());
		documentoSGDImpl.setTema(getTema());
		documentoSGDImpl.setEtiqueta(getEtiqueta());
		documentoSGDImpl.setPalabraClave(getPalabraClave());
		documentoSGDImpl.setNumRadicado(getNumRadicado());

		documentoSGDImpl.resetOriginalValues();

		return documentoSGDImpl;
	}

	@Override
	public int compareTo(DocumentoSGD documentoSGD) {
		int value = 0;

		value = DateUtil.compareTo(
			getCreateDate(), documentoSGD.getCreateDate());

		value = value * -1;

		if (value != 0) {
			return value;
		}

		return 0;
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof DocumentoSGD)) {
			return false;
		}

		DocumentoSGD documentoSGD = (DocumentoSGD)object;

		long primaryKey = documentoSGD.getPrimaryKey();

		if (getPrimaryKey() == primaryKey) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return (int)getPrimaryKey();
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	@Override
	public boolean isEntityCacheEnabled() {
		return true;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	@Override
	public boolean isFinderCacheEnabled() {
		return true;
	}

	@Override
	public void resetOriginalValues() {
		_columnOriginalValues = Collections.emptyMap();

		_columnBitmask = 0;
	}

	@Override
	public CacheModel<DocumentoSGD> toCacheModel() {
		DocumentoSGDCacheModel documentoSGDCacheModel =
			new DocumentoSGDCacheModel();

		documentoSGDCacheModel.uuid = getUuid();

		String uuid = documentoSGDCacheModel.uuid;

		if ((uuid != null) && (uuid.length() == 0)) {
			documentoSGDCacheModel.uuid = null;
		}

		documentoSGDCacheModel.documentoId = getDocumentoId();

		documentoSGDCacheModel.nombre = getNombre();

		String nombre = documentoSGDCacheModel.nombre;

		if ((nombre != null) && (nombre.length() == 0)) {
			documentoSGDCacheModel.nombre = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			documentoSGDCacheModel.createDate = createDate.getTime();
		}
		else {
			documentoSGDCacheModel.createDate = Long.MIN_VALUE;
		}

		Date inicioPublicacion = getInicioPublicacion();

		if (inicioPublicacion != null) {
			documentoSGDCacheModel.inicioPublicacion =
				inicioPublicacion.getTime();
		}
		else {
			documentoSGDCacheModel.inicioPublicacion = Long.MIN_VALUE;
		}

		Date finPublicacion = getFinPublicacion();

		if (finPublicacion != null) {
			documentoSGDCacheModel.finPublicacion = finPublicacion.getTime();
		}
		else {
			documentoSGDCacheModel.finPublicacion = Long.MIN_VALUE;
		}

		documentoSGDCacheModel.epigrafe = getEpigrafe();

		String epigrafe = documentoSGDCacheModel.epigrafe;

		if ((epigrafe != null) && (epigrafe.length() == 0)) {
			documentoSGDCacheModel.epigrafe = null;
		}

		documentoSGDCacheModel.urlDocumento = getUrlDocumento();

		String urlDocumento = documentoSGDCacheModel.urlDocumento;

		if ((urlDocumento != null) && (urlDocumento.length() == 0)) {
			documentoSGDCacheModel.urlDocumento = null;
		}

		documentoSGDCacheModel.urlPagina = getUrlPagina();

		String urlPagina = documentoSGDCacheModel.urlPagina;

		if ((urlPagina != null) && (urlPagina.length() == 0)) {
			documentoSGDCacheModel.urlPagina = null;
		}

		documentoSGDCacheModel.categoria = getCategoria();

		String categoria = documentoSGDCacheModel.categoria;

		if ((categoria != null) && (categoria.length() == 0)) {
			documentoSGDCacheModel.categoria = null;
		}

		documentoSGDCacheModel.tema = getTema();

		String tema = documentoSGDCacheModel.tema;

		if ((tema != null) && (tema.length() == 0)) {
			documentoSGDCacheModel.tema = null;
		}

		documentoSGDCacheModel.etiqueta = getEtiqueta();

		String etiqueta = documentoSGDCacheModel.etiqueta;

		if ((etiqueta != null) && (etiqueta.length() == 0)) {
			documentoSGDCacheModel.etiqueta = null;
		}

		documentoSGDCacheModel.palabraClave = getPalabraClave();

		String palabraClave = documentoSGDCacheModel.palabraClave;

		if ((palabraClave != null) && (palabraClave.length() == 0)) {
			documentoSGDCacheModel.palabraClave = null;
		}

		documentoSGDCacheModel.numRadicado = getNumRadicado();

		String numRadicado = documentoSGDCacheModel.numRadicado;

		if ((numRadicado != null) && (numRadicado.length() == 0)) {
			documentoSGDCacheModel.numRadicado = null;
		}

		return documentoSGDCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<DocumentoSGD, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(4 * attributeGetterFunctions.size()) + 2);

		sb.append("{");

		for (Map.Entry<String, Function<DocumentoSGD, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<DocumentoSGD, Object> attributeGetterFunction =
				entry.getValue();

			sb.append(attributeName);
			sb.append("=");
			sb.append(attributeGetterFunction.apply((DocumentoSGD)this));
			sb.append(", ");
		}

		if (sb.index() > 1) {
			sb.setIndex(sb.index() - 1);
		}

		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		Map<String, Function<DocumentoSGD, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<DocumentoSGD, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<DocumentoSGD, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(attributeGetterFunction.apply((DocumentoSGD)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function<InvocationHandler, DocumentoSGD>
			_escapedModelProxyProviderFunction = _getProxyProviderFunction();

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

	public <T> T getColumnValue(String columnName) {
		columnName = _attributeNames.getOrDefault(columnName, columnName);

		Function<DocumentoSGD, Object> function = _attributeGetterFunctions.get(
			columnName);

		if (function == null) {
			throw new IllegalArgumentException(
				"No attribute getter function found for " + columnName);
		}

		return (T)function.apply((DocumentoSGD)this);
	}

	public <T> T getColumnOriginalValue(String columnName) {
		if (_columnOriginalValues == null) {
			return null;
		}

		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		return (T)_columnOriginalValues.get(columnName);
	}

	private void _setColumnOriginalValues() {
		_columnOriginalValues = new HashMap<String, Object>();

		_columnOriginalValues.put("uuid_", _uuid);
		_columnOriginalValues.put("documentoId", _documentoId);
		_columnOriginalValues.put("nombre", _nombre);
		_columnOriginalValues.put("createDate", _createDate);
		_columnOriginalValues.put("inicioPublicacion", _inicioPublicacion);
		_columnOriginalValues.put("finPublicacion", _finPublicacion);
		_columnOriginalValues.put("epigrafe", _epigrafe);
		_columnOriginalValues.put("urlDocumento", _urlDocumento);
		_columnOriginalValues.put("urlPagina", _urlPagina);
		_columnOriginalValues.put("categoria", _categoria);
		_columnOriginalValues.put("tema", _tema);
		_columnOriginalValues.put("etiqueta", _etiqueta);
		_columnOriginalValues.put("palabraClave", _palabraClave);
		_columnOriginalValues.put("numRadicado", _numRadicado);
	}

	private static final Map<String, String> _attributeNames;

	static {
		Map<String, String> attributeNames = new HashMap<>();

		attributeNames.put("uuid_", "uuid");

		_attributeNames = Collections.unmodifiableMap(attributeNames);
	}

	private transient Map<String, Object> _columnOriginalValues;

	public static long getColumnBitmask(String columnName) {
		return _columnBitmasks.get(columnName);
	}

	private static final Map<String, Long> _columnBitmasks;

	static {
		Map<String, Long> columnBitmasks = new HashMap<>();

		columnBitmasks.put("uuid_", 1L);

		columnBitmasks.put("documentoId", 2L);

		columnBitmasks.put("nombre", 4L);

		columnBitmasks.put("createDate", 8L);

		columnBitmasks.put("inicioPublicacion", 16L);

		columnBitmasks.put("finPublicacion", 32L);

		columnBitmasks.put("epigrafe", 64L);

		columnBitmasks.put("urlDocumento", 128L);

		columnBitmasks.put("urlPagina", 256L);

		columnBitmasks.put("categoria", 512L);

		columnBitmasks.put("tema", 1024L);

		columnBitmasks.put("etiqueta", 2048L);

		columnBitmasks.put("palabraClave", 4096L);

		columnBitmasks.put("numRadicado", 8192L);

		_columnBitmasks = Collections.unmodifiableMap(columnBitmasks);
	}

	private long _columnBitmask;
	private DocumentoSGD _escapedModel;

}