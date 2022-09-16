<%@ include file="init.jsp" %>

<portlet:resourceURL var="agregarDocumento" id="/agregarDocumento"/>
<portlet:resourceURL var="eliminarDocumento" id="/eliminarDocumento"/>
<portlet:resourceURL var="obtenerDocumento" id="/obtenerDocumento"/>
<portlet:resourceURL var="actualizarDocumento" id="/actualizarDocumento"/>

<c:if test="${not isDefaultUser}">
	<div class="container">
		<div id="divPrincipal">
			<div class="row">
				<div class="col-1" style="padding: 20px;">
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
	
	<div class="modal" id="deleteModal" tabindex="-1" data-backdrop="static" data-keyboard="false" aria-labelledby="successModal" aria-hidden="true" style="display: none;">
		<div class="modal-dialog modal-dialog-centered modal-lg">
	        <div class="modal-content">
				<div class="modal-body modal_contenido">
	                <div id="closeModalSucesss" style="position:absolute;right:20px">
	                    <img src="<%=request.getContextPath()%>/img/closeModal.svg" alt="close" role="button" data-dismiss="modal">
	                </div>
	                <div class="text-center position-relative">
	                    <div class="modal_asset">
	                        <img src="<%=request.getContextPath()%>/img/basurero.svg" alt="basurero">
	                    </div>
	                   
	                    <p class="modal_description mb-0">Esta seguro que desea eliminar el archivo documento 1 de la lista</p>
	                    <button class="btn modal_button_yes" onclick="confirmarEliminar();">Si</button>
	   		            <button class="btn modal_button_no" onclick="noEliminar();">No</button>
	                </div>
	            </div>
	        </div>
	    </div>
	</div>
</c:if>

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
	formData.append('<portlet:namespace/>urlPagina', window.location.pathname);
	
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

function obtenerDocumento(documentId){
	var formData = new FormData();
	formData.append('<portlet:namespace/>documentId', documentId);
	
	$.ajax({
		url : '${obtenerDocumento}',
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
			var jsonData = data[0];
			if(jsonData.status == 'NOK'){
				$('#errorAgregarFormulario').removeClass('d-none');
			}else{
				$("#formDocumento").replaceWith(data);
				$('#formularioAgregarDocumento').removeClass('d-none');
				$('#divPrincipal').addClass('d-none');
			}
		}
	});
}

function actualizar(documentId){
	var formData = new FormData();
	formData.append('<portlet:namespace/>documentId', documentId);
	formData.append('<portlet:namespace/>nombre', $('#nombre').val());
	formData.append('<portlet:namespace/>epigrafe', $('#epigrafe').val());
	formData.append('<portlet:namespace/>noRadicado', $('#noRadicado').val());
	formData.append('<portlet:namespace/>categoria', $('#categoria').val());
	formData.append('<portlet:namespace/>tema', $('#tema').val());
	formData.append('<portlet:namespace/>etiqueta', $('#etiqueta').val());
	formData.append('<portlet:namespace/>palabraClave', $('#palabraClave').val());
	formData.append('<portlet:namespace/>fechapub', $('#fechapub').val());
	formData.append('<portlet:namespace/>fechafin', $('#fechafin').val());
	formData.append('<portlet:namespace/>urlPagina', window.location.pathname);
	
	$.ajax({
		url : '${actualizarDocumento}',
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

var deleteId = "";
function abrirModal(documentId){
	$("#deleteModal").show();
	deleteId = documentId;
}
function noEliminar(){
	$("#deleteModal").hide();
	deleteId = "";
}

function confirmarEliminar(){
	$('#btnCancelar').addClass('disabled');
	$('#btnPublicar').addClass('disabled');
	$('#btnActualizar').addClass('disabled');
	$("#deleteModal").hide();
	
	var formData = new FormData();
	formData.append('<portlet:namespace/>documentId', deleteId);
	
	$.ajax({
		url : '${eliminarDocumento}',
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