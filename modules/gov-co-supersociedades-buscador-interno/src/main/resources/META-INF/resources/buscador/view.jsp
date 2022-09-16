<%@ include file="../init.jsp" %>

<div class="container">
	<table class="wd-100">
		<tbody>
			<tr>
		    	<td class="col-8"><h1>${titulo}</h1></td>
		    	<td class="col-4">
    				<div class="input-group mb-3">
				  		<input type="text" class="form-control" value="${keyword}" placeholder="Buscar..." aria-label="Buscar..." id="searchKeyword">
					  	<div style="cursor: pointer;" onclick="buscar();">
							<span class="input-group-text boton-categorias">
								<svg xmlns="http://www.w3.org/2000/svg" width="100%" height="100%" fill="currentColor" class="bi bi-search" viewBox="0 0 16 16">  <path d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001c.03.04.062.078.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1.007 1.007 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0z" fill="#fff"/></svg>
							</span>
					  	</div>
					</div>
		    	</td>
	  		</tr>
	  		<tr>
		    	<td colspan="2"><p class="col-12">${bajada}</p></td>
	  		</tr>
		</tbody>
	</table>
	<br>
	<div class="container">
        <div class="row">
            <c:forEach items="${listaCategorias}" var="categoria">
                <div class="col-sd 4 mb-5 " style="cursor: pointer; max-width: 157.78px; max-height: 30.51px; ">
                    <span style="display: block;" id="cat_${categoria.category.categoryId}" class="boton-categorias" onclick="findByCategory('${categoria.category.categoryId}');">${categoria.category.name} (${categoria.contador})</span>
                </div>
            </c:forEach>
        </div>
    </div>
	<br>
	<c:choose>
		<c:when test="${not empty listaArticulos && fn:length(listaArticulos) > 0}">
			<c:forEach items="${listaArticulos}" var="articulo">
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
								<c:if test="${not empty articulo.peso}">
									<span class="text-info-data">${articulo.peso} Kb</span>
								</c:if>
								
							</td>
							<td class="pd-left wd-100">
								<table class="wd-100">
									<tbody>
										<c:if test="${not empty articulo.categoriaPadre}">
											<tr><td><span class="texto-categoria">${articulo.categoriaPadre}</span></td></tr>
										</c:if>
										
										<c:if test="${not empty articulo.tituloArticulo}">
											<tr class="txt-justificado">
												<td>
													<a href="${articulo.ulrArticulo}" style="display: flex;">
														<span title="${articulo.tituloArticulo}" class="titulo-articulo">${articulo.tituloArticulo}</span>
													</a>
												</td>
											</tr>
										</c:if>
										
										<%-- <c:if test="${not empty articulo.descripcion}">
											<tr class="txt-justificado">
												<td style="padding-top: 1%;"><a href="${articulo.descripcion}" style="display: flex;"><p>${articulo.descripcion}</p></a></td>
											</tr>
										</c:if> --%>
										
										<c:if test="${not empty articulo.fechaActualizacion}">
											<tr>
												<td>
													<span class="text-info-data" title="${articulo.tituloArticulo}" alt="${articulo.tituloArticulo}">Publicaci&oacute;n: ${articulo.fechaActualizacion} <c:if test="${articulo.fechaExtencion != null}">| Expedici&oacute;n ${articulo.fechaExtencion}</c:if></span>
												</td>
											</tr>
										</c:if>
									<tbody>
								</table>
							</td>
						</tr>
					<tbody>
				</table>
				<hr>
			</c:forEach>
			<div style="float: right;">
				<div class="row">
	                <c:if test="${start > 0}">
	                	<div class="col m-1" style="cursor: pointer; max-width: max-content;cursor: pointer;height: max-content;max-height: max-content;max-width: max-content;min-width: max-content;">
		                    <span style="display: block;" class="boton-categorias" onclick="anterior();">Anterior</span>
		                </div>
	                </c:if>
	                <c:if test="${end < totalArticulos}">
	                	<div class="col m-1" style="cursor: pointer; max-width: max-content;cursor: pointer;height: max-content;max-height: max-content;max-width: max-content;min-width: max-content;">
	                    	<span style="display: block;" class="boton-categorias" onclick="siguiente();">Siguiente</span>
		                </div>
	                </c:if>
		        </div>
			</div>
		</c:when>
		<c:otherwise>
			<span class="barra mb-2 mt-2">Sin Documentos</span>
		</c:otherwise>
	</c:choose>
</div>

<style>
.text-info-data{
	color: #2A477A !important;
    font-family: "Work Sans";
    font-weight: normal;
    font-size: 12px;
    float: left;
    padding-top: 0,5rem;
    transition: all 100ms ease;
    text-decoration: none !important;

}

.cat-select{

    background: #2a477a !important;
    color: white !important;
    border: 1px solid #2a477a !important;
    border-top-color: #2a477a !important;
    border-right-color: #2a477a !important;
    border-bottom-color: #2a477a !important;
    border-left-color: #2a477a !important;

}

</style>
<script type="text/javascript">
	var keyword = '${keyword}';
	var category = '${category}';
	var start = '${start}';
	var end = '${end}';
	var paginador = '${paginador}'
	
	function siguiente(){
		start = Number(start) + Number(paginador);
		end = Number(end) + Number(paginador);
		if(keyword == '' && category == ''){
			window.location.href = window.location.origin+window.location.pathname+"?start="+start+"&end="+end;
		}else{
			window.location.href = window.location.origin+window.location.pathname+"?keyword="+keyword+"&id="+category+"&start="+start+"&end="+end;
		}
	}
	
	function anterior(){
		start = Number(start) - Number(paginador);
		end = Number(end) - Number(paginador);
		if(keyword == '' && category == ''){
			window.location.href = window.location.origin+window.location.pathname+"?start="+start+"&end="+end;
		}else{
			window.location.href = window.location.origin+window.location.pathname+"?keyword="+keyword+"&id="+category+"&start="+start+"&end="+end;
		}
	}
	
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
	
	$( document ).ready(function() {
		const valores = window.location.search;
		const urlParams = new URLSearchParams(valores);
		var id = urlParams.get('id');
		$(".boton-categorias").removeClass("cat-select");
		$("#cat_"+id).addClass("cat-select")
		
	});
	
</script>