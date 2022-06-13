function abrirFormulario(){
	$('#formularioAgregarDocumento').removeClass('d-none');
	$('#divPrincipal').addClass('d-none');
}

function cancelar(){
	$('#formularioAgregarDocumento').addClass('d-none');
	$('#divPrincipal').removeClass('d-none');
	limpiarFormulario();
}

function publicar(){
	$('#btnCancelar').addClass('disabled');
	$('#btnPublicar').addClass('disabled');
	
	if(validarFormulario()){
		$('#errorFormulario').addClass('d-none');
		enviarFormulario();
	}else{
		$('#errorFormulario').removeClass('d-none');
		$('#btnCancelar').removeClass('disabled');
		$('#btnPublicar').removeClass('disabled');
	}
}

function limpiarFormulario(){
	$('#nombre').val("");
	$('#epigrafe').val("");
	$('#noRadicado').val("");
	$('#categoria').val("");
	$('#tema').val("");
	$('#etiqueta').val("");
	$('#palabraClave').val("");
	$('#fechapub').val("");
	$('#fechafin').val("");
}

function validarFormulario(){
	if($('#nombre').val() == ''){
		return false;
	}else if($('#epigrafe').val() == ''){
		return false;
	}else if($('#noRadicado').val() == ''){
		return false;
	}else if($('#categoria').val() == ''){
		return false;
	}else if($('#tema').val() == ''){
		return false;
	}else if($('#etiqueta').val() == ''){
		return false;
	}else if($('#palabraClave').val() == ''){
		return false;
	}else if($('#fechapub').val() == ''){
		return false;
	}else if($('#fechafin').val() == ''){
		return false;
	}else{
		return true;
	}
}