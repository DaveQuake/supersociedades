<%@ include file="init.jsp"%>

<div class="container">
	<h2 class="titulo-general mb-5">${titulo}</h2>
	<div class="panel-group" id="accordion${idPadre}" role="tablist" aria-multiselectable="true">
		<c:choose>
			<c:when test="${not empty listadoDoc}">
				<c:forEach items="${listadoDoc}" var="listado" varStatus="loop">
					<c:if test="${not empty listado.documentos}">
						<div class="panel panel-default">
							<div class="panel-heading" role="tab" id="heading${listado.id}">
								<h4 class="panel-title">
									<a class="collapsed" id="${listado.id}" onclick="closeAccordion(this.id);" role="button" data-toggle="collapse" 
										data-parent="#accordion${idPadre}" href="#collapse${listado.id}" aria-expanded="false" aria-controls="collapse${listado.id}">
										<span class="mb-3 mt-5 titulo"> ${listado.nombreListado} </span>
									</a>
								</h4>
							</div>
							<div id="collapse${listado.id}" class="panel-collapse collapse ml-50" role="tabpanel" aria-labelledby="heading${listado.id}">
								<div class="panel-body">
									<c:forEach items="${listado.documentos}" var="documento">
										<div class="mb-2"></div>
										<table>
											<tbody>
												<tr>
													<td>
														<table>
															<tbody>
																<tr>
																	<td>
																		<c:set var="tipo" value="${documento.nombreDocumento}"/>
																		<c:set var="office" value="false"/>
																		
																		<c:if test="${documento.extencion eq 'pdf'}">
																			<img class="img-modal" src="<%=request.getContextPath()%>/imagenes/pdf-svgrepo-com.svg" align="center">
																		</c:if>
																		<c:if test="${documento.extencion eq 'doc' or documento.extencion eq 'docx'}">
																			<img class="img-modal" src="<%=request.getContextPath()%>/imagenes/word-svgrepo-com.svg" align="center">
																			<c:set var="office" value="true"/>
																		</c:if>
																		<c:if test="${documento.extencion eq 'xlsx' or articulo.extension eq 'csv' or articulo.extension eq 'xls' or fn:contains(tipo, '.xls')}">
																			<img class="img-modal" src="<%=request.getContextPath()%>/imagenes/microsoft-excel-2013-logo-svgrepo-com.svg" align="center">
																			<c:set var="office" value="true"/>
																		</c:if>
																		<c:if test="${documento.extencion eq 'ppt'}">
																			<img class="img-modal" src="<%=request.getContextPath()%>/imagenes/microsoft-powerpoint-2013-logo-svgrepo-com.svg" align="center">
																			<c:set var="office" value="true"/>
																		</c:if>
																		<c:if test="${documento.extencion eq 'vsd'}">
																			<img class="img-modal" src="<%=request.getContextPath()%>/imagenes/visio.png" align="center">
																		</c:if>
																		<c:if test="${documento.extencion eq 'web'}">
																			<img class="img-modal" src="<%=request.getContextPath()%>/imagenes/contenido_web.png" align="center">
																		</c:if>
																		<c:if test="${documento.extencion eq 'img' or documento.extencion eq 'jpeg' or documento.extencion eq 'png' or documento.extencion eq 'jpg'}">
																			<img class="img-modal" src="<%=request.getContextPath()%>/imagenes/imagen.png" align="center">
																		</c:if>
																	</td>
																</tr>
															</tbody>
														</table>
													</td>
													<td class="pd-left ${empty documento.descripcion ? 'wd-100' : ''}">
														<table class="${empty documento.descripcion ? 'wd-100' : ''}">
															<tbody>
																<tr>
																	<td class="titulo">
																		<c:choose>
																			<c:when test="${not empty documento.urlDocumento and office}">
																				<a onclick="window.open('https://view.officeapps.live.com/op/view.aspx?src=${documento.urlDocumento}', '_blank')">
																					<span class="titulo_doc">${documento.nombreDocumento}</span>
																				</a>
																			</c:when>
																			<c:otherwise>
																				<a onclick="window.open('${documento.urlDocumento}', '_blank')"><span class="titulo_doc">${documento.nombreDocumento}</span></a>
																			</c:otherwise>
																		</c:choose>
																	</td>
																	<td class="fecha-modal">${documento.fecha}</td>
																</tr>
																<tr class="txt-justificado">
																	<td colspan="2">${documento.descripcion}</td>
																</tr>
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