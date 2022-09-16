<%@ include file="init.jsp" %>

<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
	<c:forEach items="${documentos}" var="documento" varStatus="loop">
		<div class="panel panel-default">
			<div class="panel-heading" role="tab" id="heading${documento.documentoId}">
				<h4 class="panel-title">
					<a class="collapsed" id="${documento.documentoId}" onclick="closeAccordion(this.id);" role="button" data-toggle="collapse" style="text-decoration: none !important"
						data-parent="#accordion" href="#collapse${documento.documentoId}" aria-expanded="false" aria-controls="collapse${documento.documentoId}">
						<span class="mb-3 mt-5 titulo">
							<svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor" class="bi bi-file-earmark" viewBox="0 0 16 16">
  								<path d="M14 4.5V14a2 2 0 0 1-2 2H4a2 2 0 0 1-2-2V2a2 2 0 0 1 2-2h5.5L14 4.5zm-3 0A1.5 1.5 0 0 1 9.5 3V1H4a1 1 0 0 0-1 1v12a1 1 0 0 0 1 1h8a1 1 0 0 0 1-1V4.5h-2z"/>
							</svg> ${documento.nombre} 
						</span>
					</a>
				</h4>
			</div>
			<div id="collapse${documento.documentoId}" class="panel-collapse collapse ml-50" role="tabpanel" aria-labelledby="heading${documento.documentoId}" style="text-decoration: none !important">
				<div class="contendor-collapsable">
					<div class="row">
				    	<div class="col-6 col-md-6">
					      	<span class="titulo-info">Título del documento:</span> ${documento.nombre}
					    </div>
					    <div class="col-6 col-md-6">
					    	<span class="titulo-info">Fecha de carga documento:</span> 
					      	<fmt:formatDate pattern="dd MMM YYYY" value="${documento.createDate}" />
					    </div>
				  	</div>
				  	<hr>
				  	<div class="row">
				    	<div class="col-6 col-md-6">
					      	<span class="titulo-info">Fecha inicio de publicación:</span> <fmt:formatDate pattern="dd MMM YYYY" value="${documento.inicioPublicacion}" />
					    </div>
					    <div class="col-6 col-md-6">
					      	<c:set var="cifrado" value="${fn:split(documento.urlDocumento, '_')}"/>
					      	<span class="titulo-info">Número de radicado:</span> <a href="${urlExterna}${cifrado[0]}" target="blank_">${not empty documento.numRadicado ? documento.numRadicado : documento.nombre}</a>
					    </div>
				  	</div>
				  	<hr>
				  	<div class="row">
				    	<div class="col-6 col-md-6">
					      	<span class="titulo-info">Fecha fin publicación:</span> 
					      	<fmt:formatDate pattern="dd MMM YYYY" value="${documento.finPublicacion}" />
					    </div>
					    <div class="col-6 col-md-6 row">
					      	<div class="col-md-6">
					      		<button type="button" class="btn btn-primary" onclick="obtenerDocumento('${documento.documentoId}');">
	                				<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-plus-circle" viewBox="0 0 16 16">
								  		<path d="M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z"></path>
								  		<path d="M8 4a.5.5 0 0 1 .5.5v3h3a.5.5 0 0 1 0 1h-3v3a.5.5 0 0 1-1 0v-3h-3a.5.5 0 0 1 0-1h3v-3A.5.5 0 0 1 8 4z"></path>
									</svg>
									Actualizar
	              				</button>
					      	</div>
					      	<div class="col-md-6">
						      	<button type="button" class="btn btn-outline-danger" onclick="abrirModal('${documento.documentoId}');">
	                				<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-dash-circle" viewBox="0 0 16 16">
	  									<path d="M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z"></path>
	  									<path d="M4 8a.5.5 0 0 1 .5-.5h7a.5.5 0 0 1 0 1h-7A.5.5 0 0 1 4 8z"></path>
									</svg>
									Eliminar
	              				</button>
              				</div>
					    </div>
				  	</div>
				</div>
			</div>
		</div>
	</c:forEach>
</div>