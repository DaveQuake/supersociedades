<%@ include file="init.jsp" %>

<div class="form" id="formDocumento">
	<h1>Ingresar Documento</h1>
	
	<div class="form-outline mb-2">
		<label class="form-label" for="form3Example3">Nombre *</label>
		<input type="text" id="nombre" maxlength="200" class="form-control" placeholder="Nombre" name="nombre" value="${documento.nombre}"/> 
	</div>
	
	<div class="form-outline mb-2">
		<label class="form-label" for="epigrafe">Epígrafe *</label>
		<textarea class="form-control" maxlength="200" cols="20" rows="3" id="epigrafe" placeholder="" name="epigrafe" value="${documento.epigrafe}">
			${documento.epigrafe}
		</textarea>
	</div>

	<div class="form-outline mb-2">
		<label class="form-label" for="noRadicado">Número de Radicado *</label> 
		<input type="text" id="noRadicado" maxlength="200" class="form-control" placeholder="No Radicado" name="noRadicado" value="${documento.numRadicado}"/>
	</div>

	<div class="form-outline mb-2">
		<label class="form-label" for="categoria">Categoría *</label>
		<input type="text" id="categoria" maxlength="200" class="form-control" name="categoria" value="${documento.categoria}"/>
	</div>

	<div class="form-outline mb-2">
		<label class="form-label" for="tema">Tema*</label>
		<input type="text" id="tema" maxlength="200" class="form-control" name="tema" value="${documento.tema}"/>
	</div>

	<div class="form-outline mb-2">
		<label class="form-label" for="etiqueta">Etiqueta*</label> 
		<input type="text" id="etiqueta" maxlength="200" class="form-control" name="etiqueta" value="${documento.etiqueta}"/>
	</div>

	<div class="form-outline mb-2">
		<label class="form-label" for="palabraClave">Palabra clave*</label>
		<input type="text" id="palabraClave" maxlength="200" class="form-control" name="palabraClave" value="${documento.palabraClave}"/>
	</div>
	<br>

	<h3>Fechas</h3> 
	<div class="row">
		<div class="col-6">
			<div class="form-outline">
				<label class="form-label" for="fechapub">Inicio Publicación*</label> 
				<input type="date" id="fechapub" class="form-control" placeholder="${documento.inicioPublicacion}" name="fechapub" value="${documento.inicioPublicacion}"/>
			</div>
		</div>
		<div class="col-6">
			<div class="form-outline">
				<label class="form-label" for="fechafin">Fin Publicación*</label>
				<input type="date" id="fechafin" class="form-control" placeholder="${documento.finPublicacion}" name="fechafin" value="${documento.finPublicacion}"/>
			</div>
		</div>
	</div>
	
	<br>
	
	<div class="alert alert-danger d-none" id="errorFormulario">
  		Ingrese los datos del formulario correctamente...
	</div>
	
	<div class="row mb-2">
		<div class="col">
			<div class="form-outline">
				<button class="btn btn-danger btn-block mb-2" onclick="cancelar();" id="btnCancelar">CANCELAR</button>
			</div>
		</div>
		<div class="col">
			<div class="form-outline">
				<c:choose>
					<c:when test="${actualizar}">
						<button class="btn btn-primary btn-block mb-2" onclick="actualizar('${documento.documentoId}');" id="btnActualizar">ACTUALIZAR</button>
					</c:when>
					<c:otherwise>
						<button class="btn btn-primary btn-block mb-2" onclick="enviarFormulario();" id="btnPublicar">PUBLICAR</button>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
	</div>
</div>