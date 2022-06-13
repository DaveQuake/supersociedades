create table gov_documento_sgd (
	uuid_ VARCHAR(75) null,
	documentoId LONG not null primary key,
	nombre VARCHAR(75) null,
	createDate DATE null,
	inicioPublicacion DATE null,
	finPublicacion DATE null,
	epigrafe VARCHAR(75) null,
	urlDocumento VARCHAR(75) null,
	urlPagina VARCHAR(75) null,
	categoria VARCHAR(75) null,
	tema VARCHAR(75) null,
	etiqueta VARCHAR(75) null,
	palabraClave VARCHAR(75) null
);