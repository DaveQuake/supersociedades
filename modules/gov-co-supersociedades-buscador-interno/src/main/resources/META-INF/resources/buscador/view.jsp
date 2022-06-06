<%@ include file="../init.jsp" %>

<div class="container">
	<table class="wd-100">
		<tbody>
			<tr>
		    	<td class="col-8"><h1 class="titulo">${titulo}</h1></td>
		    	<td class="col-4">
    				<div class="input-group mb-3">
				  		<input type="text" class="form-control" placeholder="Buscar..." aria-label="Buscar..." id="searchKeyword">
					  	<div style="cursor: pointer;" onclick="buscar();">
							<span class="input-group-text">
								<svg class="lexicon-icon lexicon-icon-search" role="presentation" viewBox="0 0 512 512">
									<use xlink:href="http://localhost:8080/o/super-sociedades-theme/images/clay/icons.svg#search"/>
								</svg>
							</span>
					  	</div>
					</div>
		    	</td>
	  		</tr>
	  		<tr>
		    	<td colspan="2"><p class="bajada col-12">${bajada}</p></td>
	  		</tr>
		</tbody>
	</table>

	<br>

	<table>
		<tbody>
	  		<tr>
		    	<c:forEach items="${listaCategorias}" var="categoria">
					<td style="cursor: pointer;"><span class="boton-categorias" onclick="findByCategory('${categoria.category.categoryId}');">${categoria.category.name} (${categoria.contador})</span></td>
				</c:forEach>
	  		</tr>
		</tbody>
	</table>
	
	<br>
	
	<c:choose>
		<c:when test="${not empty listaArticulos && fn:length(listaArticulos) > 0}">
			<c:forEach items="${listaArticulos}" var="articulo">
				<div class="mb-2"></div>
				<table>
					<tbody>
						<c:set var="tipo" value="${articulo.tituloArticulo}"/>
						<c:set var="office" value="false"/>
								
						<tr>
							<td>
								<c:if test="${articulo.extension eq 'pdf'}">
									<img class="img-modal" src="<%=request.getContextPath()%>/img/pdf.png" align="center">
								</c:if>
								<c:if test="${articulo.extension eq 'xlsx' or articulo.extension eq 'csv' or articulo.extension eq 'xls' or fn:contains(tipo, '.xls')}">
									<img class="img-modal" src="<%=request.getContextPath()%>/img/excel.png" align="center">
									<c:set var="office" value="true"/>
								</c:if>
								<c:if test="${articulo.extension eq 'doc' or articulo.extension eq 'docx'}">
									<img class="img-modal" src="<%=request.getContextPath()%>/img/word.png" align="center">
									<c:set var="office" value="true"/>
								</c:if>
								<c:if test="${articulo.extension eq 'ppt'}">
									<img class="img-modal" src="<%=request.getContextPath()%>/img/powerpoint.png" align="center">
									<c:set var="office" value="true"/>
								</c:if>
								<c:if test="${articulo.extension eq 'vsd'}">
									<img class="img-modal" src="<%=request.getContextPath()%>/img/visio.png" align="center">
								</c:if>
								<c:if test="${articulo.extension eq 'web'}">
									<img class="img-modal" src="<%=request.getContextPath()%>/img/contenido_web.png" align="center">
								</c:if>
								<c:if test="${articulo.extension eq 'img' or articulo.extension eq 'jpeg' or articulo.extension eq 'png' or articulo.extension eq 'jpg'}">
									<img class="img-modal" src="<%=request.getContextPath()%>/img/imagen.png" align="center">
								</c:if>
							</td>
							<td class="pd-left ${empty articulo.descripcion ? 'wd-100' : ''}">
								<table class="${empty articulo.descripcion ? 'wd-100' : ''}">
									<tbody>
										<c:if test="${not empty articulo.categoriaPadre}">
											<tr><td><span class="texto-categoria">${articulo.categoriaPadre}</span></td></tr>
										</c:if>
										
										<c:if test="${not empty articulo.tituloArticulo}">
											<tr class="txt-justificado">
												<td>
													<a href="${articulo.ulrArticulo}" target="_blank" style="display: flex;">
														<c:choose>
															<c:when test="${not empty articulo.descripcion}">
																<span class="titulo-articulo">${articulo.descripcion}</span>
															</c:when>
															<c:otherwise>
																<span class="titulo-articulo">${articulo.tituloArticulo}</span> 
															</c:otherwise>
														</c:choose>
													</a>
												</td>
											</tr>
										</c:if>
										
										<c:if test="${not empty articulo.fechaActualizacion}">
											<tr><td><span class="texto-fecha">Modificaci&oacute;n ${articulo.fechaActualizacion}</span></td></tr>
										</c:if>
									<tbody>
								</table>
							</td>
						</tr>
					<tbody>
				</table>
				<hr>
			</c:forEach>
		</c:when>
		<c:otherwise>
			<span class="barra mb-2 mt-2">Sin Documentos</span>
		</c:otherwise>
	</c:choose>
</div>
<script type="text/javascript">
var keyword = '${keyword}';
var category = '${category}';

function buscar(){
	if($('#searchKeyword').val() != ""){		
		if(category == ''){
			window.location.href = window.location.origin+window.location.pathname+"?keyword="+$('#searchKeyword').val();
		}else{
			window.location.href = window.location.origin+window.location.pathname+"?keyword="+$('#searchKeyword').val()+"&id="+category;
		}
	}else{
		window.location.href = window.location.origin+window.location.pathname;
	}
}

function findByCategory(category){
	if(keyword == ''){
		window.location.href = window.location.origin+window.location.pathname+"?id="+category;
	}else{
		window.location.href = window.location.origin+window.location.pathname+"?keyword="+keyword+"&id="+category;
	}
}
</script>