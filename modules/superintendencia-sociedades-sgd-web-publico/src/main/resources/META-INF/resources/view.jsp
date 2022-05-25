<%@ include file="/init.jsp" %>

<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import= "com.co.superintendencia.sociedades.builder.model.DocumentoSGD" %>
<%@ page import= "com.co.superintendencia.sociedades.builder.service.DocumentoSGDLocalServiceUtil" %>
<%@ page import="com.liferay.portal.kernel.util.ParamUtil"%>
<%@ page import="com.liferay.portal.kernel.util.PortalUtil"%>
<%@ page import="java.util.Date"%>

<%@ page import="java.time.LocalDateTime"%>
<%@ page import="java.time.ZoneId"%>

<!--   <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.0/css/font-awesome.min.css" rel="stylesheet"> -->  
<!--   <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css"> -->
<!--   <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script> -->
   
	  <!--  tabla -->
	  <link rel="stylesheet" href="https://cdn.datatables.net/1.11.3/css/jquery.dataTables.min.css">
	  <script type="text/javascript" src="http://cdn.datatables.net/1.11.3/js/jquery.dataTables.min.js"></script>   
	
	  <!--   modal -->
<!-- 	  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-modal/0.9.1/jquery.modal.min.css">
	  <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-modal/0.9.1/jquery.modal.min.js"></script> --> 
  
  
<% 
List<DocumentoSGD> docum= (List<DocumentoSGD>) request.getAttribute("documentos");	

List<String> nombres = new ArrayList<String>();
List<LocalDateTime> fechasinicial = new ArrayList<LocalDateTime>();
List<LocalDateTime> fechasFinal = new ArrayList<LocalDateTime>();
List<LocalDateTime> fechasCarga = new ArrayList<LocalDateTime>();
List<String> urlDocumento = new ArrayList<String>();
List<String> epigrafe = new ArrayList<String>();
List<Long> documentId = new ArrayList<Long>();

Date hoy = new Date(); 

for (DocumentoSGD d:docum) {
	nombres.add(d.getNombre());
	fechasinicial.add(d.getInicioPublicacion().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
	fechasFinal.add(d.getFinPublicacion().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
	urlDocumento.add(d.getUrlDocumento());
	epigrafe.add(d.getEpigrafe());
	fechasCarga.add(d.getCreateDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
		}

System.out.println("fechasinicial : " + fechasinicial);
System.out.println("fechasFinal : " + fechasFinal);
System.out.println("fechasCarga : " + fechasCarga);

System.out.println("hoy : " + hoy);
LocalDateTime hoytime = hoy.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime() ;



System.out.println("hoytime : " + hoytime);

String urlPagina = PortalUtil.getCurrentURL(request);
%>
											<div class="">
											<table id="tabla_factura_publica" class="display table-stile" cellspacing="0" width="100%">
												<thead>
													<tr>
														<th class="fuente-title">Título del Documento</th>
														<th class="fuente-title">Descripción</th>
														<th class="fuente-title">Fecha Inicio Publicación</th>
														<th class="fuente-title">Fecha Fin Publicación</th>
														<th class="fuente-title">Fecha de Modificación</th>
														<td  style="display: none;">urlDocumento</td>
												   </tr>
												</thead>
												<tbody id="content_table">
													<tr>
														<td></td>
														<td></td>
														<td></td>
														<td></td>
														<td></td>
														<td style="display: none;"></td>
													</tr>
												</tbody>
											</table>
										</div>


<!-- modal para previsualizarurldocumento -->
 <!-- <div class="container">  
  <div class="modal fade" id="modalUrlDocumento" role="dialog">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-body">
           <iframe id="urlData" src="" width="700" height="450" frameborder="0" scrolling="no"></iframe>
        </div>
      </div>
      
    </div>
  </div>
</div>  --> 

<div id="urlDocumentoPublico" class="modal fade" role="dialog">
  <div class="modal-dialog">
    <!-- Contenido del modal -->
    <div class="modal-content">
       
      <div class="modal-body">
          <form > 
	       <iframe id="urlData" src="" width="650" height="450" frameborder="0" scrolling="yes"></iframe>
       </form>
      </div>
       <div class="modal-footer">
        <button type="button" class="btn btn-danger" data-dismiss="modal">Cerrar</button>
      </div> 
    </div>
  </div>
</div>

<!-- <div id="urlDocumentoPublico" class="modal">
   <form > 
	   <iframe id="urlData" src="" width="650" height="450" frameborder="0" scrolling="yes"></iframe>
   </form>
</div> -->

 <script> 
 $(document).ready(function(){

     $(".modal").css("display", "none");
     $("#urlDocumentoPublico").modal('hide');
	 $('body').removeClass('modal-open');
	 $('.modal-backdrop').remove(); 
	  
/* 	  $("#modalUrlDocumento").modal('hide');
	  $('body').removeClass('modal-open');
	  $('.modal-backdrop').remove(); */
	 
	 var array_fila_public;
	 var editar_publico = 0;
	   
	 var nombres =  '<%=nombres%>'.slice(1, -1).split(',');
 	 var fechaInicial = '<%=fechasinicial%>'.slice(1, -1).split(',');
	 var fechaFinal = '<%=fechasFinal%>'.slice(1, -1).split(',');
	 var urlDocumento = '<%=urlDocumento%>'.slice(1, -1).split(',');
	 var epigrafe = '<%=epigrafe%>'.slice(1, -1).split(',');
	 var fechaCarga = '<%=fechasCarga%>'.slice(1, -1).split(',');
	 var urlPagina = '<%=urlPagina%>';
	 
	 var hoy = '<%=hoytime%>';
	 
	 

	 for(i=0; i < nombres.length; i++){
		 newRowTableGestion(nombres[i].trim(),fechaInicial[i].trim(),fechaFinal[i].trim(),urlDocumento[i].trim(),fechaCarga[i].trim(),urlPagina,epigrafe[i].trim(),hoy);
	 }
	 	 	 
	 	$("loans_table").on('click','.dataFila_public',editProduct_public);

	 	$("body").on('click',".dataFila_public",editProduct_public);
	 	
	 	$(".edit_public").on('click',editDocument_public);
	 	
		 $('#tabla_factura_publica').dataTable({
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
	 			 
	 	
	 function editProduct_public(){
		 console.log("entramos a la funcion ");
		var _this = this;
		array_fila_public=getRowSelected(_this);
	 	
	 	// editar codigo
	 		if(editar_publico > 0 ){
	 			editar_publico = 0;
	 			console.log(array_fila_public[5])
	 			var urlDocument = array_fila_public[5];
	 			document.getElementById('urlData').src = urlDocument;	 			
	 			 $("#urlDocumentoPublico").modal({show: true});
	 			// $('#urlDocumentoPublico').modal();
	 		}
	
	 }
	 
/* 	 editamos la fila selecionada */
	 function editDocument_public(){
		    editar_publico++;
	        console.log("valor de editar: " + editar_publico);
	        
		 }	 
	 	 	 
	 function getRowSelected(objectPressed){	 	
	 	//Obteniendo la linea que se esta eliminando
	 	var a=objectPressed.parentNode.parentNode;
	 	//b=(fila).(obtener elementos de clase columna y traer la posicion 0).(obtener los elementos de tipo parrafo y traer la posicion0).(contenido en el nodo)
	 	var nombre=a.getElementsByTagName("td")[0].getElementsByTagName("p")[0].innerHTML;
	 	var fechaCarga=a.getElementsByTagName("td")[1].getElementsByTagName("p")[0].innerHTML;
 	 	var fechaInicio=a.getElementsByTagName("td")[2].getElementsByTagName("p")[0].innerHTML;
 	 	var fechaFinal=a.getElementsByTagName("td")[3].getElementsByTagName("p")[0].innerHTML;
	 	var fechaModificacion=a.getElementsByTagName("td")[4].getElementsByTagName("p")[0].innerHTML;	 	
	 	var urlDocumento=a.getElementsByTagName("td")[5].getElementsByTagName("p")[0].innerHTML;

 	 	var array_fila_public = [nombre, fechaCarga, fechaInicio,fechaFinal,fechaModificacion,urlDocumento];
 	 	console.log("--"+ array_fila_public);
	 	
	 	return array_fila_public;
	 }

	 function newRowTableGestion(nombre,fechaInicial,fechaFinal,urlDocumento,fechaCarga,urlPagina,epigrafe,hoy) {		
		var meses = ["Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"]; 	
		var nombreColum=nombre;
	 	var documento=urlDocumento;
	 	var epigrafeData=epigrafe;
		var urlPaginaTd = urlPagina.split("?");		
	 	var urlArray = documento.split("_");
	 	
	 	var fechaModificacion = "ahora";
	 	console.log("entramos al publico portlet");
	 	console.log("fechaCarga: " + fechaCarga);	 	
	 	console.log("hoy: " + hoy);
	 		 	
	 	var hoy = new Date(hoy);
	 	var fechaCargDocumento = new Date(fechaCarga);
	 		 	 	
	 	var tiempoPasado= hoy - fechaCargDocumento;
	 	console.log("tiempoPasado: " + tiempoPasado);
	 	//calculo segundos 
	 	var segundos = Math.floor(tiempoPasado / 1000);
	 	var minutos = Math.floor(segundos / 60);
	 	tiempoPasado= tiempoPasado- (minutos - segundos);
	 	
	 	var horas = Math.floor(minutos / 60);
	 	tiempoPasado= tiempoPasado- ( horas - minutos);
	 	
	 	var dias = Math.floor(horas / 24);
	 	tiempoPasado= tiempoPasado- (dias - horas);
	 	
	 	var mesesMod = Math.floor(dias / 30.416666666666668);
	 	tiempoPasado= tiempoPasado- (meses - dias);
	 	
	 	var anos = Math.floor(mesesMod / 12);
	 	
/* 	 	console.log(`Han pasado ${anos} años, ${meses} meses,  ${dias} dias, ${horas} horas, y ${minutos} minutos desde que naciste. Ya chocheas...!!`) */
	    console.log("Han pasado "+  anos +" años, "+ mesesMod + " meses, " + dias + " dias, "+ horas + " horas, y "+ minutos + " minutos");
	 	 	
	 	if(anos !== 0 ){
	 		fechaModificacion="hace " + anos +" años ";
	 	}else if(mesesMod !== 0){
	 		fechaModificacion ="hace " + mesesMod +" meses ";;
	 	}else if(dias !== 0){
	 		fechaModificacion="hace " + dias +" dias ";;
	 	}else if(horas !== 0){
	 		fechaModificacion="hace " + horas +" horas ";;
	 	}else if(minutos !== 0){
	 		fechaModificacion="hace " + minutos +" minutos ";;
	 	}
	 	
	 	console.log("fechaModificacion: " +fechaModificacion);
		console.log("*urlPagina*: " + urlPaginaTd[0]);
 	
	 	var dateFechaCargaColum = new Date(fechaCarga);
	 	var dateFechaInicialColum = new Date(fechaInicial);
	 	var dateFechaFinalColum = new Date(fechaFinal);
	 		 		 	
	 	var fechacargaColum = " " + meses[dateFechaCargaColum.getMonth()]+ " " + dateFechaCargaColum.getDate() + " "+ dateFechaCargaColum.getFullYear();
	 	var fechaInicialColum=" " + meses[dateFechaInicialColum.getMonth()]+ " " + dateFechaInicialColum.getDate() + " "+ dateFechaInicialColum.getFullYear();
	 	var fechaFinalColum=" " + meses[dateFechaFinalColum.getMonth()]+ " " + dateFechaFinalColum.getDate() + " "+ dateFechaFinalColum.getFullYear();
	 		 	 	
	 	var archivo  = "https://servicios.supersociedades.gov.co/bpmformularios/VisualizarDocumentos.aspx?Radicado="+urlArray[0].trim();
	 	
	 	var name_table=document.getElementById("tabla_factura_publica");

	     var row = name_table.insertRow(0+1);
	     var cell1 = row.insertCell(0);//nombre
	     var cell2 = row.insertCell(1);//fecha de creacion
	     var cell3 = row.insertCell(2);//fecha de publicacion
	     var cell4 = row.insertCell(3);//fecha fin de publicacion
	     var cell5 = row.insertCell(4);// fecha modificacion
	     var cell6 = row.insertCell(5);//urlDocumento

	     cell1.className = "rowcenter";
	     cell2.className = "rowcenter";
	     cell3.className = "rowcenter";
	     cell4.className = "rowcenter";
	     cell5.className = "rowcenter";
	     
	     cell6.className = "rowhidden";
	     
	     cell1.innerHTML = '<p name="numero_f[]" class="dataFila_public non-margin ">' +' <a class="edit_public">'+nombreColum+ '</a></p>';
	     cell2.innerHTML = '<p name="codigo_p[]" class="non-margin">'+epigrafeData+'</p>';
	     cell3.innerHTML = '<p name="descuento_p[]" class="non-margin">'+fechaInicialColum+'</p>';
	     cell4.innerHTML = '<p name="cantidad_p[]" class="non-margin">'+fechaFinalColum+'</p>';
	     cell5.innerHTML = '<p name="precio_p[]" class="non-margin">'+fechaModificacion+'</p>';
	     cell6.innerHTML = '<p  style="display: none;" name="epigrafe_p[]" class="non-margin">'+archivo+'</p>';

	 }
	 	 
	 }); 
 
</script> 