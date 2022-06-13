<%@ include file="init.jsp" %>

<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
	<c:forEach items="${documentos}" var="documento" varStatus="loop">
		<div class="panel panel-default">
			<div class="panel-heading" role="tab" id="heading${documento.documentoId}">
				<h4 class="panel-title">
					<a class="collapsed" id="${documento.documentoId}" onclick="closeAccordion(this.id);" role="button" data-toggle="collapse" 
						data-parent="#accordion" href="#collapse${documento.documentoId}" aria-expanded="false" aria-controls="collapse${documento.documentoId}">
						<span class="mb-3 mt-5 titulo"> ${documento.nombre} </span>
					</a>
				</h4>
			</div>
			<div id="collapse${documento.documentoId}" class="panel-collapse collapse ml-50" role="tabpanel" aria-labelledby="heading${documento.documentoId}">
				<table>
					<tbody>
						<tr>
							<td>Titulo del documento: ${documento.nombre}</td>
							<td>Fecha de carga documento: ${documento.createDate}</td>
						</tr>
						<hr>
						<tr>
							<td>Fecha inicio publicación: ${documento.inicioPublicacion}</td>
							<td>Número de radicado: ${documento.etiqueta}</td>
						</tr>
						<hr>
						<tr>
							<td>Fecha fin publicación: ${documento.finPublicacion}</td>
							<td>&nbsp;</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</c:forEach>
</div>