<%@ include file="init.jsp" %>

<portlet:resourceURL var="agregarDocumento" id="/agregarDocumento"/>
<portlet:resourceURL var="eliminarDocumento" id="/eliminarDocumento"/>
<portlet:resourceURL var="actualizarDocumento" id="/actualizarDocumento"/>

<div class="container">
	<div id="divPrincipal">
		<div class="row">
			<div class="col-1">
				<button type="button" class="btn btn-primary" onclick="abrirFormulario();">+</button>
			</div>
			<div class="col-11">
				<h1>Mis Documentos</h1>
			</div>
		</div>
	
		<div class="row">
			<%@ include file="listadoDocumentos.jsp" %>
		</div>
	</div>
	
	<div class="row d-none" id="formularioAgregarDocumento">
		<%@ include file="agregarDocumento.jsp" %>
	</div>
	
	<div class="alert alert-danger d-none" id="errorAgregarFormulario">
  		Ocurrió un problema el agregar el documento, intente nuevamente....
	</div>
</div>

<script>
function enviarFormulario(){
	
	var formData = new FormData();
	formData.append('<portlet:namespace/>nombre', $('#nombre').val());
	formData.append('<portlet:namespace/>epigrafe', $('#epigrafe').val());
	formData.append('<portlet:namespace/>noRadicado', $('#noRadicado').val());
	formData.append('<portlet:namespace/>categoria', $('#categoria').val());
	formData.append('<portlet:namespace/>tema', $('#tema').val());
	formData.append('<portlet:namespace/>etiqueta', $('#etiqueta').val());
	formData.append('<portlet:namespace/>palabraClave', $('#palabraClave').val());
	formData.append('<portlet:namespace/>fechapub', $('#fechapub').val());
	formData.append('<portlet:namespace/>fechafin', $('#fechafin').val());
	formData.append('<portlet:namespace/>urlPagina', $('#urlPagina').val());
	
	$.ajax({
		url : '${agregarDocumento}',
		type : 'POST',
		datatype : 'json',
		data: formData,
		cache: false,
	    contentType: false,
	    processData: false,
		error : function(xhr, status, error) {
			console.log("hay un error");
		},
		success : function(data) {
			$('#btnCancelar').removeClass('disabled');
			$('#btnPublicar').removeClass('disabled');
			
			var jsonData = data[0];
			if(jsonData.status == 'OK'){
				window.location.reload();
			}else if(jsonData.status == 'NOK'){
				$('#errorAgregarFormulario').removeClass('d-none');
			}
		}
	});
}
</script>