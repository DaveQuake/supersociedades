<%@ include file="/init.jsp" %>

<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%-- <%@ page import= "com.co.superindentendencia.sociedades.sgd.model.DocumentosSGD" %>
<%@ page import= "com.co.superindentendencia.sociedades.sgd.service.DocumentosSGDLocalServiceUtil" %> --%>
<%@ page import= "com.co.superintendencia.sociedades.builder.model.DocumentoSGD" %>
<%@ page import= "com.co.superintendencia.sociedades.builder.service.DocumentoSGDLocalServiceUtil" %>
<%@ page import="com.liferay.portal.kernel.util.ParamUtil"%>
<%@ page import="com.liferay.portal.kernel.util.PortalUtil"%>
<%@ page import="java.util.Date"%>

<%@ page import="java.time.LocalDateTime"%>

<%@ page import="java.time.ZoneId"%>

  <!-- link para el icono -->
<!--   <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.0/css/font-awesome.min.css" rel="stylesheet"> -->
  
<!--    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous"> -->

   <link rel="stylesheet" href="https://cdn.datatables.net/1.11.3/css/jquery.dataTables.min.css">
   
  
<!--   <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script> -->
  
 <!--  <script type="text/javascript" src="http://cdn.datatables.net/1.10.2/js/jquery.dataTables.min.js"></script>
   -->
  <script type="text/javascript" src="http://cdn.datatables.net/1.11.3/js/jquery.dataTables.min.js"></script>
  
 <portlet:actionURL name="/save/document" var="saveURL"/>
 <portlet:actionURL name="/update/document" var="updateURL"/> 
 <portlet:actionURL name="/delete/document" var="deleteURL"/> 

<% 


List<DocumentoSGD> docum= (List<DocumentoSGD>) request.getAttribute("documentos");	

List<String> nombres = new ArrayList<String>();
List<LocalDateTime> fechasinicial = new ArrayList<LocalDateTime>();
List<LocalDateTime> fechasFinal = new ArrayList<LocalDateTime>();
List<LocalDateTime> fechasCarga = new ArrayList<LocalDateTime>();
List<String> urlDocumento = new ArrayList<String>();
List<String> epigrafe = new ArrayList<String>();
List<Long> documentId = new ArrayList<Long>();
List<String> categoria = new ArrayList<String>();
List<String> tema = new ArrayList<String>();
List<String> etiqueta = new ArrayList<String>();
List<String> palabraClave = new ArrayList<String>();

for (DocumentoSGD d:docum) {
	nombres.add(d.getNombre());
	fechasinicial.add(d.getInicioPublicacion().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
	fechasFinal.add(d.getFinPublicacion().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
	urlDocumento.add(d.getUrlDocumento());
	epigrafe.add(d.getEpigrafe());
	documentId.add(d.getDocumentoId());
	fechasCarga.add(d.getCreateDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
	categoria.add(d.getCategoria());
	tema.add(d.getTema());
	etiqueta.add(d.getEtiqueta());
	palabraClave.add(d.getPalabraClave());
		}


String urlPagina = PortalUtil.getCurrentURL(request);

System.out.println("fechasinicial : " + fechasinicial);
System.out.println("fechasFinal : " + fechasFinal);
System.out.println("Esta es la request URL actual: desde el jsp " + PortalUtil.getCurrentURL(request));


%>

<div class="container-portlet">

  <button type="button" class="btn btn-primary btn-md" id="myBtnAdd">+</button>
             
  <br>
  <br>
											<div class="">
											<table id="tabla_factura" class="display table-stile" cellspacing="0" width="100%">
												<thead>
													<tr>
														<th class="fuente-title">Título del Documento</th>
														<th class="fuente-title">Fecha Carga documento</th>
														<th class="fuente-title">Fecha Inicio Publicación</th>
														<th class="fuente-title">Número Radicado</th>
														<th class="fuente-title">Fecha Fin Publicación</th>
														<th class="fuente-title">Acción</th>
														<td  style="display: none;">Epi</td>
														<td  style="display: none;">Id</td>
														<td  style="display: none;">urlPagina</td>
														<td  style="display: none;">Categoria</td>
														<td  style="display: none;">Tema</td>
														<td  style="display: none;">Etiqueta</td>
														<td  style="display: none;">PalabraClave</td>
												   </tr>
												</thead>
												<tbody id="content_table">
													<tr>
														<td></td>
														<td></td>
														<td></td>
														<td></td>
														<td></td>
														<td></td>
														<td style="display: none;"></td>
														<td style="display: none;"></td>
														<td style="display: none;"></td>
														<td style="display: none;"></td>
														<td style="display: none;"></td>
														<td style="display: none;"></td>
														<td style="display: none;"></td>
													</tr>
												</tbody>
											</table>
										</div>
 
 </div>
 
 <!--   modal para agregar datos -->
<div id="modalAdd" class="modal fade" role="dialog">
  <div class="modal-dialog">
    
    <div class="modal-content">
      <div class="modal-body">
		  <form class="my-form" method="post" action="${saveURL}">
		  <!-- Nombre input -->
		  <div class="form-outline mb-4">
		    <label class="form-label" for="form3Example3">Nombre</label>
		    <input type="text" id="addnombre" class="form-control" placeholder="Nombre" name="<portlet:namespace />nombre" required />
		    <input id="addurlPagina" type="hidden" placeholder="id" name="<portlet:namespace />urlPagina">
		  </div>		
		  <!-- Epigrafe input -->
		  <div class="form-outline mb-4">
		    <label class="form-label" for="form3Example4">Epígrafe</label>
		    <textarea class="form-control" maxlength="200" cols="20" rows="3" id="addEpigrafe" placeholder="" name="<portlet:namespace />epigrafe" ></textarea>  
		  </div>		  
		    <!-- Numero Radicado input -->
		  <div class="form-outline mb-4">
		    <label class="form-label" for="form3Example3">Número de Radicado</label>
		    <input type="text" id="addnradicado" class="form-control" placeholder="No Radicado" name="<portlet:namespace />NoRadicado" required>
		  </div>
		      <!-- Categoria input -->
		  <div class="form-outline mb-4">
		    <label class="form-label" for="form3Example3">Categoría</label>
		    <input type="text" id="addCategoria" class="form-control" name="<portlet:namespace />categoria" required />
		  </div>	  
		      <!-- Tema input -->
		  <div class="form-outline mb-4">
		    <label class="form-label" for="form3Example3">Tema</label>
		    <input type="text" id="addTema" class="form-control" name="<portlet:namespace />tema" required  />
		  </div>	  
		      <!-- Etiqueta input -->
		  <div class="form-outline mb-4">
		    <label class="form-label" for="form3Example3">Etiqueta</label>
		    <input type="text" id="addEtiqueta" class="form-control" name="<portlet:namespace />etiqueta" required  />
		  </div>
		      <!-- Palabra clave input -->
		  <div class="form-outline mb-4">
		    <label class="form-label" for="form3Example3">Palabra clave</label>
		    <input type="text" id="addPalabraClave" class="form-control" name="<portlet:namespace />palabraClave" required/>
		  </div>
		  <br>
		  <!-- 2 column grid layout with text inputs for the first and last names -->
		  <div class="row mb-4">
		    <div class="col">
		      <div class="form-outline">
		        <label class="form-label" for="form3Example1">Fecha de Publicación</label>
		        <input type="date" id="addfechapub" class="form-control" placeholder=" "  name="<portlet:namespace />fechapub" required>
		      </div>
		    </div>
		    <div class="col">
		      <div class="form-outline">
		        <label class="form-label" for="form3Example2">Fecha fin de publicación</label>
		        <input type="date" id="addfechafin" class="form-control" placeholder=" " name="<portlet:namespace />fechafin" required>
		      </div>
		    </div>
		  </div>
		    <br>
		  <!-- Submit button -->
		    <div class="row mb-4">
		    <div class="col">
		      <div class="form-outline">
		       <button type="reset" class="btn btn-danger btn-block mb-4" data-dismiss="modal">CANCELAR</button>
		      </div>
		    </div>
		    <div class="col">
		      <div class="form-outline">
		      <button type="submit" class="btn btn-primary btn-block mb-4">PUBLICAR</button>
		      </div>
		    </div>
		  </div>
		</form>
      </div>    
    </div>
  </div>
</div>

 
<!-- modal para editar -->
 <div id="modalEdit" class="modal fade" role="dialog">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-body">
           <form class="my-form" method="post" action="${updateURL}">
		<!-- Nombre input -->
		  <div class="form-outline mb-4">
		    <input id="editDocumetId" type="hidden" placeholder="id" name="<portlet:namespace />documentId">
		    <input id="editurlPagina" type="hidden" placeholder="id" name="<portlet:namespace />urlPagina">
		    <label class="form-label" for="form3Example1">Nombre</label>
		    <input type="text" id="editNombre" class="form-control" placeholder="Nombre" name="<portlet:namespace />nombre" required />  
		  </div>
		  <!-- Epigrafe input -->
		  <div class="form-outline mb-4">
		    <label class="form-label" for="form3Example2">Epígrafe</label>
		    <textarea class="form-control" maxlength="200" cols="20" rows="3" id="editEpigrafe" placeholder="" name="<portlet:namespace />epigrafe" ></textarea> 
		  </div>
		    <!-- Numero Radicado input -->
		  <div class="form-outline mb-4">
		    <label class="form-label" for="form3Example3">Número de Radicado</label>
		    <input type="text" id="editRadicado" class="form-control" placeholder="No Radicado" name="<portlet:namespace />NoRadicado" required>
		  </div>
		      <!-- Categoria input -->
		  <div class="form-outline mb-4">
		    <label class="form-label" for="form3Example4">Categoría</label>
		    <input type="text" id="editCategoria" class="form-control" name="<portlet:namespace />categoria" required />
		  </div>		  
		      <!-- Tema input -->
		  <div class="form-outline mb-4">
		    <label class="form-label" for="form3Example5">Tema</label>
		    <input type="text" id="editTema" class="form-control" name="<portlet:namespace />tema" required  />
		  </div>		  
		      <!-- Etiqueta input -->
		  <div class="form-outline mb-4">
		    <label class="form-label" for="form3Example6">Etiqueta</label>
		    <input type="text" id="editEtiqueta" class="form-control" name="<portlet:namespace />etiqueta" required  />
		  </div>	  
		      <!-- Palabra clave input -->
		  <div class="form-outline mb-4">
		    <label class="form-label" for="form3Example7">Palabra clave</label>
		    <input type="text" id="editPalabraClave" class="form-control" name="<portlet:namespace />palabraClave" required/>
		  </div>
		  <br>
		  <!-- 2 column grid layout with text inputs for the first and last names -->
		  <div class="row mb-4">
		    <div class="col">
		      <div class="form-outline">
		        <label class="form-label" for="form3Example8">Fecha de Publicación</label>
		        <input type="date" id="editInicio" class="form-control" placeholder=" "  name="<portlet:namespace />fechapub" required>		        
		      </div>
		    </div>
		    <div class="col">
		      <div class="form-outline">
		        <label class="form-label" for="form3Example9">Fecha fin de publicación</label>
		        <input type="date" id="editiFinal" class="form-control" placeholder=" " name="<portlet:namespace />fechafin" required>
		      </div>
		    </div>
		  </div>
		    <br>
		  <!-- Submit button -->
		    <div class="row mb-4">
		    <div class="col">
		      <div class="form-outline">
		       <button type="reset" class="btn btn-danger btn-block mb-4" data-dismiss="modal">CANCELAR</button>
		      </div>
		    </div>
		    <div class="col">
		      <div class="form-outline">
		      <button type="submit" class="btn btn-primary btn-block mb-4">PUBLICAR</button>
		      </div>
		    </div>
		  </div>
		  </form> 
      </div>
    </div>
  </div>
</div> 
  

<%--  <!--   modal para Eliminar datos -->
 <div class="modal fade" id="myModalDelete" role="dialog">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-body">
		  <form class="my-form" method="post" action="${deleteURL}">
		  <!-- Nombre input -->
		  <div class="form-outline mb-4">
		  	<input id="eliminarDocumetId" type="hidden" placeholder="id" name="<portlet:namespace />documentId">
		  	<input id="eliminarurlPagina" type="hidden" placeholder="id" name="<portlet:namespace />urlPagina">
		    <h3 class="mensajeElim"> ¿Esta seguro que desea eliminar el archivo  <scan id="NombreEliminar"> </scan >  de la lista? </h3>
		  </div>				  
		    <br>
		  <!-- Submit button -->
		    <div class="row mb-4">
		    <div class="col">
		      <div class="form-outline">
		       <button type="reset" class="btn btn-danger btn-block mb-4" data-dismiss="modal">NO</button>
		      </div>
		    </div>
		    <div class="col">
		      <div class="form-outline">
		      <button type="submit" class="btn btn-primary btn-block mb-4">SI</button>
		      </div>
		    </div>
		  </div>
		</form>
    </div>
   </div>
      
 </div>
</div>  --%>

 <div id="myModalDelete" class="modal fade" role="dialog">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-body">
		  <form class="my-form" method="post" action="${deleteURL}">
		  <!-- Nombre input -->
		  <div class="form-outline mb-4">
		  	<input id="eliminarDocumetId" type="hidden" placeholder="id" name="<portlet:namespace />documentId">
		  	<input id="eliminarurlPagina" type="hidden" placeholder="id" name="<portlet:namespace />urlPagina">
		    <h3 class="mensajeElim"> ¿Esta seguro que desea eliminar el archivo  <scan id="NombreEliminar"> </scan >  de la lista? </h3>
		  </div>				  
		    <br>
		  <!-- Submit button -->
		    <div class="row mb-4">
		    <div class="col">
		      <div class="form-outline">
		       <button type="reset" class="btn btn-danger btn-block mb-4" data-dismiss="modal">NO</button>
		      </div>
		    </div>
		    <div class="col">
		      <div class="form-outline">
		      <button type="submit" class="btn btn-primary btn-block mb-4">SI</button>
		      </div>
		    </div>
		  </div>
		</form>
      </div>
    </div>
  </div>
</div>   
    
 <script> 
 $(document).ready(function(){
     console.log("probando responsive 8"); 
	 $(".modal").css("display", "none");
      $("#modalAdd").modal('hide');
	  $("#modalEdit").modal('hide');
	  $("#myModalDelete").modal('hide');
	  $('body').removeClass('modal-open');
	  $('.modal-backdrop').remove(); 
	 
	 var array_fila;
	 var editar = 0;
	 var eliminarData=0;

	 $("#myBtnAdd").click(function(){
	     $("#modalAdd").modal({show: true});
	   });
	  
		 
	 var nombres =  '<%=nombres%>'.slice(1, -1).split(',');
 	 var fechaInicial = '<%=fechasinicial%>'.slice(1, -1).split(',');
	 var fechaFinal = '<%=fechasFinal%>'.slice(1, -1).split(',');
	 var urlDocumento = '<%=urlDocumento%>'.slice(1, -1).split(',');
	 var epigrafe = '<%=epigrafe%>'.slice(1, -1).split(',');
	 var documentoId = '<%=documentId%>'.slice(1, -1).split(',');
	 var fechaCarga = '<%=fechasCarga%>'.slice(1, -1).split(',');
	 var urlPagina = '<%=urlPagina%>';
	 var categoria = '<%=categoria%>'.slice(1, -1).split(',');
	 var tema = '<%=tema%>'.slice(1, -1).split(',');
	 var etiqueta = '<%=etiqueta%>'.slice(1, -1).split(',');
	 var palabraClave = '<%=palabraClave%>'.slice(1, -1).split(',');
	 	 
	 console.log("nombres: " + nombres);
	 console.log("fechaInicial: " + fechaInicial);
	 
	 
	 for(i=0; i < nombres.length; i++){
		 newRowTableGestion(nombres[i].trim(),fechaInicial[i].trim(),fechaFinal[i].trim(),urlDocumento[i].trim(),epigrafe[i].trim(),documentoId[i].trim(),fechaCarga[i].trim(),urlPagina,categoria[i].trim(),tema[i].trim(),etiqueta[i].trim(),palabraClave[i].trim());
	 }
	 	 	 
	 	$("loans_table").on('click','.fa-eraser',deleteProduct);
	 	$("loans_table").on('click','.dataFila',editProduct);

	 	$("body").on('click',".fa-eraser",deleteProduct);
	 	$("body").on('click',".dataFila",editProduct);
	 	
	 	$(".previ").on('click',previsu);
	 	$(".edit").on('click',editDocument);
	 	$(".elimin").on('click',eliminDocument);

	 	$('#tabla_factura').DataTable( {
	 	    responsive: true
	 	} );
	 	
		 $('#tabla_factura').dataTable({
		        "language": {
		            "lengthMenu": "Datos _MENU_  por página",
		            "zeroRecords": "!Lo sentimos - búsqueda no encontrada",
		            "info": "páginas _PAGE_ de _PAGES_",
		            "infoEmpty": "No hay registros disponibles",
		            "infoFiltered": "(filtrado de registros totales _MAX_ )",
		            "search":         "Buscar:",
		            "paginate": {
		                "first":      "primero",
		                "last":       "último",
		                "next":       "siquiente",
		                "previous":   "anterior"
		            }
		        }
		    } );
	 	
	 	
	 function funcEliminarProductosso(){
	 	//Obteniendo la fila que se esta eliminando
	 	var a=this.parentNode.parentNode;
	 	//Obteniendo el array de todos loe elementos columna en esa fila
	 	//var b=a.getElementsByTagName("td");
	 	var cantidad=a.getElementsByTagName("td")
	 	console.log(a);

	 	$(this).parent().parent().fadeOut("slow",function(){$(this).remove();});
	 }


	 function deleteProduct(){
	 	//Guardando la referencia del objeto presionado
	 	var _this = this;
	 	//Obtener las filas los datos de la fila que se va a elimnar
	 	//var array_fila=getRowSelected(_this);

	 	//Restar esos datos a los totales mostrados al finales
	 	//calculateTotals(cantidad, precio, subtotal, impuesto, totalneto, accioneliminar)
	 	calculateTotals(array_fila[3],array_fila[4],array_fila[5],array_fila[6],array_fila[7],2)

	 	$(this).parent().parent().fadeOut("slow",function(){$(this).remove();});
	 }


	 function editProduct(){
		 console.log("entramos a la funcion editProduct");
		var _this = this;
	 	array_fila=getRowSelected(_this);
	 	
	 	// editar codigo
	 		if(editar > 0 ){
	 			editar = 0;
		        var fechainit;
			 	var fechaFinal
				console.log("****"+ array_fila[0]+" - "+array_fila[1]+" - "+array_fila[2]+" - "+array_fila[3]+" - "+array_fila[4]+" - "+array_fila[5]+" - "+array_fila[6]+" - "+array_fila[7]+" - "+array_fila[8]+" - "+array_fila[9]+" - "+array_fila[10]+" - "+array_fila[11]); 
			 
			 	var  fechainicio = array_fila[2];
			 	var fechafin = array_fila[4];
			 	
			 	console.log("fechainicio: " + fechainicio);
			 	console.log("fechafin: " + fechafin);
			     
			 	if(fechainicio.includes("Enero")){ 
			 		fechainicio = fechainicio.replace('Enero', 'january');
			 	}else if(fechainicio.includes("Abril")){
			 		fechainicio =  fechainicio.replace('Abril', 'april');
			 	}else if(fechainicio.includes("Agosto")){
			 		fechainicio = fechainicio.replace('agosto', 'august');
			 	}else if(fechainicio.includes("Diciembre")){
			 		fechainicio =  fechainicio.replace('Diciembre', 'december');
			 	}
			 	
			 	if(fechafin.includes("Enero")){ 
			 		fechafin = fechafin.replace('Enero', 'january');
			 	}else if(fechafin.includes("Abril")){
			 		fechafin =  fechafin.replace('abril', 'april');
			 	}else if(fechafin.includes("Agosto")){
			 		fechafin = fechafin.replace('Agosto', 'august');
			 	}else if(fechafin.includes("Diciembre")){
			 		fechafin =  fechafin.replace('Diciembre', 'december');
			 	}			 	
			 
			
			 	console.log("fechainicio2: " + fechainicio);
			 	console.log("fechafin2: " + fechafin);	
			 	
			 	if(!(fechainicio.includes('null'))){
			 		var date = new Date(fechainicio);
				 	var result = date.toISOString();
				    fechainit = result.slice(0,10)
			 	}
				
			 	if(!(fechafin.includes('null'))){
				 	var dateFinal = new Date(fechafin);
				 	var resultFinal = dateFinal.toISOString();
				 	fechaFinal = resultFinal.slice(0,10)		 		
			 	}
			 	
			 	$("#editNombre").val(array_fila[0]);
			 	$("#editInicio").val(fechainit);
			 	$("#editiFinal").val(fechaFinal);
			 	$("#editRadicado").val(array_fila[3]);
			 	$("#editEpigrafe").val(array_fila[5]);
			 	$("#editDocumetId").val(array_fila[6]);
			 	$("#editurlPagina").val(array_fila[7]);
			 	$("#editCategoria").val(array_fila[8]);
			 	$("#editTema").val(array_fila[9]);
			 	$("#editEtiqueta").val(array_fila[10]);
			 	$("#editPalabraClave").val(array_fila[11]);
			 			 	
//			 	$("#myModal3").modal({show: true});
			 	$("#modalEdit").modal({show: true});
	 		}else if (eliminarData > 0){
			 	$("#deleteNombre").val(array_fila[0]);
			 	document.getElementById("NombreEliminar").innerHTML = array_fila[0];
			 	$("#eliminarDocumetId").val(array_fila[6]);
	 			eliminarData=0;
	 			$("#myModalDelete").modal({show: true});
	 		}
	 	
	 	
	 	
	 }

	 function previsu(){
		 	console.log("previsualizar --------->");
/* 		 	var fechainit;
		 	var fechaFinal
		 	console.log("****"+ array_fila[0]+" - "+array_fila[1]+" - "+array_fila[2]+" - "+array_fila[3]+" - "+array_fila[4]+" - "+array_fila[5]); 
		 	
		 	
		 	console.log("array_fila[2]"+array_fila[2]);
		 	
		 	console.log("array_fila[2] include "+array_fila[2].includes('null'));
		 	
		 	if(!(array_fila[2].includes('null'))){
			 	var date = new Date(array_fila[2]);
			 	var result = date.toISOString();
			    fechainit = result.slice(0,10)		 		
		 	}

		 	console.log("array_fila[4]"+array_fila[4].includes('null'));			
		 	if(!(array_fila[4].includes('null'))){
			 	var dateFinal = new Date(array_fila[4]);
			 	var resultFinal = dateFinal.toISOString();
			 	fechaFinal = resultFinal.slice(0,10)		 		
		 	}

		 	
		 	$("#previNombre").val(array_fila[0]);
		 	$("#previInicio").val(fechainit);
		 	$("#previFinal").val(fechaFinal);
		 	$("#previRadicado").val(array_fila[3]);
		 	$("#previEpigrafe").val(array_fila[5]);
		 			 	
		 	$("#myModal2").modal({show: true}); */
		 	//console.log("--------->");
		 }

	 
/* 	 editamos la fila selecionada */
	 function editDocument(){
	        editar++;
	        console.log("valor de editar: " + editar);
	        
		 }	 
	 
	 
/* 	 eliminamos la fila selecionada */
	 
	 function eliminDocument(){
		 console.log("entramos a la funcion eliminar"); 
		 console.log("****"+ array_fila[0]+" - "+array_fila[1]+" - "+array_fila[2]+" - "+array_fila[3]+" - "+array_fila[4]+" - "+array_fila[5]+" - "+array_fila[6]+" - "+array_fila[7]+" - "+array_fila[8]+" - "+array_fila[9]+" - "+array_fila[10]+" - "+array_fila[11]); 
		 eliminarData++;
	     console.log("valor de eliminarData: " + eliminarData);
		 }		 
	 
	 
	 function getRowSelected(objectPressed){	 	
	 	//Obteniendo la linea que se esta eliminando
	 	var a=objectPressed.parentNode.parentNode;
	 	//b=(fila).(obtener elementos de clase columna y traer la posicion 0).(obtener los elementos de tipo parrafo y traer la posicion0).(contenido en el nodo)
	 	var nombre=a.getElementsByTagName("td")[0].getElementsByTagName("p")[0].innerHTML;
	 	var fechaCarga=a.getElementsByTagName("td")[1].getElementsByTagName("p")[0].innerHTML;
 	 	var fechaInicio=a.getElementsByTagName("td")[2].getElementsByTagName("p")[0].innerHTML;
	 	var documento=a.getElementsByTagName("td")[3].getElementsByTagName("p")[0].innerHTML;
	 	var fechaFinal=a.getElementsByTagName("td")[4].getElementsByTagName("p")[0].innerHTML;
	 	var epigrafe=a.getElementsByTagName("td")[6].getElementsByTagName("p")[0].innerHTML;
	 	var documentID=a.getElementsByTagName("td")[7].getElementsByTagName("p")[0].innerHTML;
	 	var urlPagina=a.getElementsByTagName("td")[8].getElementsByTagName("p")[0].innerHTML;
	 	var categoria=a.getElementsByTagName("td")[9].getElementsByTagName("p")[0].innerHTML;
	 	var tema=a.getElementsByTagName("td")[10].getElementsByTagName("p")[0].innerHTML;
	 	var etiqueta=a.getElementsByTagName("td")[11].getElementsByTagName("p")[0].innerHTML;
	 	var palabraClave=a.getElementsByTagName("td")[12].getElementsByTagName("p")[0].innerHTML;

 	 	var array_fila = [nombre, fechaCarga, fechaInicio, documento, fechaFinal,epigrafe,documentID,urlPagina,categoria,tema,etiqueta,palabraClave];
 	 	console.log("--"+array_fila);
	 	
	 	return array_fila;
	 }

	 function newRowTableGestion(nombre,fechaInicial,fechaFinal,urlDocumento,epigrafe,documentoId,fechaCarga,urlPagina,categoria,tema,etiqueta,palabraClave) {		
		var meses = ["Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"]; 	
		var nombreColum=nombre;
	
		var urlPaginaTd = urlPagina.split("?");
		console.log("*urlPagina*: " + urlPaginaTd[0]);
	 	$("#addurlPagina").val(urlPaginaTd[0]);
	 	$("#eliminarurlPagina").val(urlPaginaTd[0]);
		
	     console.log("nombre: " + nombre);
	     console.log("fechaInicial: " + fechaInicial);
	     console.log("fechaFinal: " + fechaFinal);
	     console.log("fechaCarga: " + fechaCarga);
	     	     
	 	
	 	var documento=urlDocumento;
	 	var epigrafeData=epigrafe;
	 	var documentoIdData=documentoId;
	 	var categoriaData=categoria;
	 	var temaData=tema;
	 	var etiquetaData=etiqueta;
	 	var palabraClaveData=palabraClave;
	 	var urlArray = documento.split("_");
	 	
	 	var dateFechaCargaColum = new Date(fechaCarga);
	 	var dateFechaInicialColum = new Date(fechaInicial);
	 	var dateFechaFinalColum = new Date(fechaFinal);
	 	
	     console.log("dateFechaInicialColum: " + dateFechaInicialColum);
	     console.log("dateFechaFinalColum: " + dateFechaFinalColum);
	     console.log("dateFechaCargaColum: " + dateFechaCargaColum);
	     	     
	 	var fechacargaColum = " " + meses[dateFechaCargaColum.getMonth()]+ " " + dateFechaCargaColum.getDate() + " "+ dateFechaCargaColum.getFullYear();
	 	var fechaInicialColum=" " + meses[dateFechaInicialColum.getMonth()]+ " " + dateFechaInicialColum.getDate() + " "+ dateFechaInicialColum.getFullYear();
	 	var fechaFinalColum=" " + meses[dateFechaFinalColum.getMonth()]+ " " + dateFechaFinalColum.getDate() + " "+ dateFechaFinalColum.getFullYear();
	 
	 	
	     console.log("fechaInicialColum: " + fechaInicialColum);
	     console.log("fechaFinalColum: " + fechaFinalColum);
	     console.log("fechacargaColum: " + fechacargaColum);
	     
	     console.log("-------------------------------------");
	 	
	 	var archivo  = "https://servicios.supersociedades.gov.co/bpmformularios/VisualizarDocumentos.aspx?Radicado="+urlArray[0].trim();
	 	
	 	var name_table=document.getElementById("tabla_factura");

	     var row = name_table.insertRow(0+1);
	     var cell1 = row.insertCell(0);
	     var cell2 = row.insertCell(1);
	     var cell3 = row.insertCell(2);
	     var cell4 = row.insertCell(3);
	     var cell5 = row.insertCell(4);
	     var cell6 = row.insertCell(5);
	     var cell7 = row.insertCell(6);
	     var cell8 = row.insertCell(7);
	     
	     var cell9 = row.insertCell(8);//urlPagina
	     var cell10 = row.insertCell(9);//categoria
	     var cell11 = row.insertCell(10);//tema
	     var cell12 = row.insertCell(11);//etiqueta
	     var cell13 = row.insertCell(12);//palabraclave

	     cell1.className = "rowcenter";
	     cell2.className = "rowcenter";
	     cell3.className = "rowcenter";
	     cell4.className = "rowcenter";
	     cell5.className = "rowcenter";
	     cell6.className = "rowcenter";
	     
	     cell7.className = "rowhidden";
	     cell8.className = "rowhidden";
	     cell9.className = "rowhidden";
	     cell10.className = "rowhidden";
	     cell11.className = "rowhidden";
	     cell12.className = "rowhidden";
	     cell13.className = "rowhidden";
	     
	     cell1.innerHTML = '<p name="numero_f[]" class="non-margin">'+nombreColum+'</p>';
	     cell2.innerHTML = '<p name="codigo_p[]" class="non-margin">'+fechacargaColum+'</p>';
	     cell3.innerHTML = '<p name="descuento_p[]" class="non-margin">'+fechaInicialColum+'</p>';
	     cell4.innerHTML = '<p name="cantidad_p[]" class="non-margin">'+urlArray[1]+'</p>';
	     cell5.innerHTML = '<p name="precio_p[]" class="non-margin">'+fechaFinalColum+'</p>';
	     cell6.innerHTML = '<div class="dataFila centericon dropleft"><i class="icon-dots-three-vertical fa fa-ellipsis-v dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"></i> <div class="dropdown-menu" aria-labelledby="dropdownMenuLink"> <span class="dropdown-item edit">Editar</span><span class="dropdown-item elimin">Eliminar</span> <a href='+ archivo + ' target="_blank" class="dropdown-item previ">Previsualizar</a></div></div>';
	     cell7.innerHTML = '<p  style="display: none;" name="epigrafe_p[]" class="non-margin">'+epigrafeData+'</p>';
	     cell8.innerHTML = '<p  style="display: none;" name="documenId_p[]" class="non-margin">'+documentoIdData+'</p>';
	     cell9.innerHTML = '<p  style="display: none;" name="urlPagina_p[]" class="non-margin">'+urlPaginaTd[0]+'</p>';
	     cell10.innerHTML = '<p  style="display: none;" name="categoria_p[]" class="non-margin">'+categoriaData+'</p>';
	     cell11.innerHTML = '<p  style="display: none;" name="tema_p[]" class="non-margin">'+temaData+'</p>';
	     cell12.innerHTML = '<p  style="display: none;" name="etiqueta_p[]" class="non-margin">'+etiquetaData+'</p>';
	     cell13.innerHTML = '<p  style="display: none;" name="palabrasClave_p[]" class="non-margin">'+palabraClaveData+'</p>';
	     //cell6.innerHTML = '<span class="icon fa-edit">actualizar</span> <span class="icon fa-eraser">borrar</span>';  
	 }
	 	 
	 }); 
 
</script> 