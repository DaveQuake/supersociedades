<%@ include file="/init.jsp" %>

<div class="container">
	<div class="panel-group" id="accordionOther" role="tablist" aria-multiselectable="true">
		<c:forEach items="${documentos}" var="documento" varStatus="loop">
			<div class="panel panel-default">
				<div class="panel-heading" role="tab" id="headingsdg-${documento.documentoId}">
					<h4 class="panel-title">
						<a class="collapsed" title="${documento.nombre}" alt="${documento.nombre}" id="sdg-${documento.documentoId}" onclick="closeAccordionSgd(this.id);" role="button" data-toggle="collapse" style="text-decoration: none !important"
							data-parent="#accordionOther" href="#collapse_sdg-${documento.documentoId}" aria-expanded="false" aria-controls="collapse_sdg-${documento.documentoId}">
							<span class="mb-3 mt-5 titulo">
								<svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor" class="bi bi-file-earmark" viewBox="0 0 16 16">
	  								<path d="M14 4.5V14a2 2 0 0 1-2 2H4a2 2 0 0 1-2-2V2a2 2 0 0 1 2-2h5.5L14 4.5zm-3 0A1.5 1.5 0 0 1 9.5 3V1H4a1 1 0 0 0-1 1v12a1 1 0 0 0 1 1h8a1 1 0 0 0 1-1V4.5h-2z"/>
								</svg> ${documento.nombre} 
							</span>
						</a>
					</h4>
				</div>
				<div id="collapse_sdg-${documento.documentoId}" class="panel-collapse collapse ml-50" role="tabpanel" aria-labelledby="headingsdg-${documento.documentoId}" style="text-decoration: none !important">
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
						    <div class="col-6 col-md-6"></div>
					  	</div>
					</div>
				</div>
			</div>
		</c:forEach>
	</div>

</div>

<script>
function closeAccordionSgd(id) {
	if ($("#collapse_" + id).hasClass("show")) {
		setTimeout(function() {
			$("#collapse_" + id).removeClass("show");
			$("#" + id).attr("aria-expanded", "false");
		}, 400);
	}
}
</script>
