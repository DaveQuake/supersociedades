<div class="form">
	<h1>Ingresar Documento</h1>
	
	<div class="form-outline mb-2">
		<label class="form-label" for="form3Example3">Nombre *</label>
		<input type="text" id="nombre" class="form-control" placeholder="Nombre" name="nombre"/> 
		<input id="urlPagina" type="hidden" name="urlPagina" value="${urlPagina}">
	</div>
	
	<div class="form-outline mb-2">
		<label class="form-label" for="epigrafe">Epígrafe *</label>
		<textarea class="form-control" maxlength="200" cols="20" rows="3" id="epigrafe" placeholder="" name="epigrafe"></textarea>
	</div>

	<div class="form-outline mb-2">
		<label class="form-label" for="noRadicado">Número de Radicado *</label> 
		<input type="text" id="noRadicado" class="form-control" placeholder="No Radicado" name="noRadicado"/>
	</div>

	<div class="form-outline mb-2">
		<label class="form-label" for="categoria">Categoría *</label>
		<input type="text" id="categoria" class="form-control" name="categoria"/>
	</div>

	<div class="form-outline mb-2">
		<label class="form-label" for="tema">Tema*</label>
		<input type="text" id="tema" class="form-control" name="tema"/>
	</div>

	<div class="form-outline mb-2">
		<label class="form-label" for="etiqueta">Etiqueta*</label> 
		<input type="text" id="etiqueta" class="form-control" name="etiqueta"/>
	</div>

	<div class="form-outline mb-2">
		<label class="form-label" for="palabraClave">Palabra clave*</label>
		<input type="text" id="palabraClave" class="form-control" name="palabraClave"/>
	</div>
	<br>

	<h3>Fechas</h3> 
	<div class="row">
		<div class="col-6">
			<div class="form-outline">
				<label class="form-label" for="fechapub">Inicio Publicación*</label> 
				<input type="date" id="fechapub" class="form-control" placeholder=" " name="fechapub"/>
			</div>
		</div>
		<div class="col-6">
			<div class="form-outline">
				<label class="form-label" for="fechafin">Fin Publicación*</label>
				<input type="date" id="fechafin" class="form-control" placeholder=" " name="fechafin"/>
			</div>
		</div>
	</div>
	
	<br>
	
	<div class="alert alert-danger d-none" id="errorFormulario">
  		Ingrese los datos del fromulario correctamente...
	</div>
	
	<div class="row mb-2">
		<div class="col">
			<div class="form-outline">
				<button class="btn btn-danger btn-block mb-2" onclick="cancelar();" id="btnCancelar">CANCELAR</button>
			</div>
		</div>
		<div class="col">
			<div class="form-outline">
				<button class="btn btn-primary btn-block mb-2" onclick="publicar();" id="btnPublicar">PUBLICAR</button>
			</div>
		</div>
	</div>
</div>