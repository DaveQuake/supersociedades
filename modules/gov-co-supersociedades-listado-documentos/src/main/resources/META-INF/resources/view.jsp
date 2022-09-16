<%@ include file="init.jsp"%>

<div class="container">
	<div class="panel-group" id="accordion${idPadre}" role="tablist" aria-multiselectable="true">
		<c:choose>
			<c:when test="${not empty listadoDoc}">
				<c:forEach items="${listadoDoc}" var="listado" varStatus="loop">
					<c:if test="${not empty listado.documentos}">
						<div class="panel panel-default">
							<div class="panel-heading" role="tab" id="heading${listado.id}">
								<h4 class="panel-title">
									<a class="collapsed" title="${listado.nombreListado}" alt="${listado.nombreListado}" id="${listado.id}" onclick="closeAccordion(this.id);" role="button" data-toggle="collapse" 
										data-parent="#accordion${idPadre}" href="#collapse${listado.id}" aria-expanded="false" aria-controls="collapse${listado.id}">
										<span class="mb-3 mt-5 titulo"> ${listado.nombreListado} </span>
									</a>
								</h4>
							</div>
							<div id="collapse${listado.id}"  class="panel-collapse collapse ml-50" role="tabpanel" aria-labelledby="heading${listado.id}">
								<div class="panel-body">
									<c:forEach items="${listado.documentos}" var="documento">
										<table style="margin-bottom: 30px;">
											<tbody >
												<tr>
													<td>
														<table>
															<tbody>
																<tr>
																	<td>
																		<c:set var="tipo" value="${documento.nombreDocumento}"/>
																		<c:set var="office" value="false"/>
																		
																		<c:if test="${documento.extencion eq 'pdf'}">
																			<img class="img-modal" title="${documento.nombreDocumento}" alt="${documento.nombreDocumento}" src="<%=request.getContextPath()%>/imagenes/pdf-svgrepo-com.svg" align="center">
																		</c:if>
																		<c:if test="${documento.extencion eq 'doc' or documento.extencion eq 'docx'}">
																			<img class="img-modal" title="${documento.nombreDocumento}" alt="${documento.nombreDocumento}" src="<%=request.getContextPath()%>/imagenes/word-svgrepo-com.svg" align="center">
																			<c:set var="office" value="true"/>
																		</c:if>
																		<c:if test="${documento.extencion eq 'xlsx' or articulo.extension eq 'csv' or articulo.extension eq 'xls' or fn:contains(tipo, '.xls')}">
																			<img class="img-modal" title="${documento.nombreDocumento}" alt="${documento.nombreDocumento}" src="<%=request.getContextPath()%>/imagenes/microsoft-excel-2013-logo-svgrepo-com.svg" align="center">
																			<c:set var="office" value="true"/>
																		</c:if>
																		<c:if test="${documento.extencion eq 'ppt'}">
																			<img class="img-modal" title="${documento.nombreDocumento}" alt="${documento.nombreDocumento}" src="<%=request.getContextPath()%>/imagenes/microsoft-powerpoint-2013-logo-svgrepo-com.svg" align="center">
																			<c:set var="office" value="true"/>
																		</c:if>
																		<c:if test="${documento.extencion eq 'vsd'}">
																			<img class="img-modal" title="${documento.nombreDocumento}" alt="${documento.nombreDocumento}" src="<%=request.getContextPath()%>/imagenes/visio.png" align="center">
																		</c:if>
																		<c:if test="${documento.extencion eq 'web'}">
																			<img class="img-modal" title="${documento.nombreDocumento}" alt="${documento.nombreDocumento}" src="<%=request.getContextPath()%>/imagenes/contenido_web.png" align="center">
																		</c:if>
																		<c:if test="${documento.extencion eq 'img' or documento.extencion eq 'jpeg' or documento.extencion eq 'png' or documento.extencion eq 'jpg'}">
																			<img class="img-modal" title="${documento.nombreDocumento}" alt="${documento.nombreDocumento}" src="<%=request.getContextPath()%>/imagenes/imagen.png" align="center">
																		</c:if>
																		<span class="text-info">${documento.peso} Kb</span>
																	</td>
																</tr>
															</tbody>
														</table>
													</td>
													<td class="pd-left wd-100">
														<table class="wd-100">
															<tbody>
																<tr>
																	<td class="titulo">
																		<c:choose>
																			<c:when test="${not empty documento.urlDocumento and office}">
																				<a href="${documento.urlDocumento}" title="${documento.nombreDocumento}" alt="${documento.nombreDocumento}">
																					<span class="titulo_doc">${documento.nombreDocumento}</span>
																				</a>
																			</c:when>
																			<c:otherwise>
																				<a href="${documento.urlDocumento}" title="${documento.nombreDocumento}" alt="${documento.nombreDocumento}"><span class="titulo_doc">${documento.nombreDocumento}</span></a>
																			</c:otherwise>
																		</c:choose>
																	</td>
																	<%-- <td class="fecha-modal">${documento.fecha}</td> --%>
																</tr>
																<tr>
																	<td>
																		<span class="text-info" title="${documento.nombreDocumento}" alt="${documento.nombreDocumento}">Publicaci&oacute;n: ${documento.fecha} <c:if test="${documento.fechaExpedicion != null}">| Expedici&oacute;n ${documento.fechaExpedicion}</c:if></span>
																	</td>
																	<%-- <td colspan="2">${documento.descripcion}</td> --%>
																</tr>
																<%-- <tr class="txt-justificado">
																	
																	<td colspan="2">${documento.descripcion}</td>
																</tr> --%>
															</tbody>
														</table>
													</td>
												</tr>
											</tbody>
										</table>
									</c:forEach>
								</div>
							</div>
						</div>
					</c:if>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<span class="barra mb-2 mt-2">Sin Documentos</span>
			</c:otherwise>
		</c:choose>
	</div>
</div>

<style>
.text-info{
	color: #2A477A !important;
    font-family: "Work Sans";
    font-weight: normal;
    font-size: 12px;
    float: left;
    padding-top: 0,5rem;
    transition: all 100ms ease;
    text-decoration: none !important;

}
</style>

<script>
function closeAccordion(id) {
	if ($("#collapse" + id).hasClass("show")) {
		setTimeout(function() {
			$("#collapse" + id).removeClass("show");
			$("#" + id).attr("aria-expanded", "false");
		}, 400);
	}
}
</script>