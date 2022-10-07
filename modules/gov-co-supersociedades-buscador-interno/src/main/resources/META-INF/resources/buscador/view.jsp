<%@ include file="../init.jsp" %>
<div class="container container-tag">
	<div id="cargando" style="display: none;">
		<div class="overley"></div>
		<div class="loader"></div>
	</div>
	<table class="wd-100">
		<tbody>
			<tr>
				<td class="col-5">
					<h1>${titulo}</h1>
				</td>
				<td class="col-6">
					<div class="input-group mb-3">
						<div>
							<input type="text" class="form-control" value="${keyword}" placeholder="Buscar..." aria-label="Buscar..." id="searchKeyword">
						</div>
						<div style="cursor: pointer;" onclick="buscar();">
							<span class="input-group-text boton-lupa">
								<svg xmlns="http://www.w3.org/2000/svg" width="100%" height="100%" fill="currentColor" class="bi bi-search" viewBox="0 0 16 16">
									<path d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001c.03.04.062.078.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1.007 1.007 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0z" fill="#fff"/>
								</svg>
							</span>
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<p class="col-12">${bajada}</p>
				</td>
			</tr>
		</tbody>
	</table>
	<br>
	<div class="container">
		<div class="row">
			<c:forEach items="${listaCategorias}" var="categoria">
				<div class="col-sd-4 mb-2 " style="cursor: pointer; max-width: 157.78px; min-width: 157.78px; margin-left: 10px; ">
					<span style="display: block;" id="cat_${categoria.category.categoryId}" class="boton-categorias" onclick="findByCategory('${categoria.category.categoryId}');" title="${categoria.category.name}" alt="${categoria.category.name}">${categoria.category.name} (${categoria.contador})</span>
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
									<img class="img-modal" src="<%=request.getContextPath()%>/imagenes/pdf-svgrepo-com.svg" align="center">
								</c:if>
								<c:if test="${articulo.extension eq 'xlsx' or articulo.extension eq 'csv' or articulo.extension eq 'xls' or fn:contains(tipo, '.xls')}">
									<img class="img-modal" src="<%=request.getContextPath()%>/imagenes/microsoft-excel-2013-logo-svgrepo-com.svg" align="center">
									<c:set var="office" value="true"/>
								</c:if>
								<c:if test="${articulo.extension eq 'doc' or articulo.extension eq 'docx'}">
									<img class="img-modal" src="<%=request.getContextPath()%>/imagenes/word-svgrepo-com.svg" align="center">
									<c:set var="office" value="true"/>
								</c:if>
								<c:if test="${articulo.extension eq 'ppt'}">
									<img class="img-modal" src="<%=request.getContextPath()%>/imagenes/microsoft-powerpoint-2013-logo-svgrepo-com.svg" align="center">
									<c:set var="office" value="true"/>
								</c:if>
								<c:if test="${articulo.extension eq 'vsd'}">
									<img class="img-modal" src="<%=request.getContextPath()%>/imagenes/visio.png" align="center">
								</c:if>
								<c:if test="${articulo.extension eq 'web'}">
									<img class="img-modal" src="<%=request.getContextPath()%>/imagenes/contenido_web.png" align="center">
								</c:if>
								<c:if test="${articulo.extension eq 'img' or articulo.extension eq 'jpeg' or articulo.extension eq 'png' or articulo.extension eq 'jpg'}">
									<img class="img-modal" src="<%=request.getContextPath()%>/imagenes/imagen.png" align="center">
								</c:if>
								<c:if test="${not empty articulo.peso}">
									<span class="text-info-data">${articulo.peso} Kb</span>
								</c:if>
							</td>
							<td class="pd-left wd-100">
								<table class="wd-100">
									<tbody>
										<c:if test="${not empty articulo.categoriaPadre}">
											<tr>
												<td><span class="texto-categoria" id="txtCat">${articulo.categoriaPadre}</span></td>
											</tr>
										</c:if>
										<c:if test="${not empty articulo.tituloArticulo}">
											<tr class="txt-justificado">
												<td>
													<div class="w-40">
														<a href="${articulo.ulrArticulo}" style="display: flex;">
														<span title="${articulo.tituloArticulo}" class="titulo-articulo">${articulo.tituloArticulo}</span>
														</a>
													</div>
												</td>
											</tr>
										</c:if>
										<c:if test="${not empty articulo.descripcion}">
											<tr class="">
												<td style="padding-top: 1%; font-size: 14px;">
													<a href="${articulo.ulrArticulo}" style="display: flex;">
														<p>${fn:substring(articulo.descripcion, 0, 220)}</p>
													</a>
												</td>
											</tr>
										</c:if>
										<c:if test="${not empty articulo.fechaActualizacion}">
											<tr>
												<td>
													<span class="text-info-data" title="${articulo.tituloArticulo}" alt="${articulo.tituloArticulo}">
														Publicaci&oacute;n: ${articulo.fechaActualizacion} 
														<c:if test="${articulo.fechaExtencion != null}">| Expedici&oacute;n ${articulo.fechaExtencion}</c:if>
													</span>
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
			<div class="paginationGroup">
				<ul class="pagination row p-0 m-0">
					<c:if test="${start > 0}">
						<li class="page-item col-1 p-0 m-0" style="max-width: max-content;"><span style="cursor: pointer;" class="action-paginador" onclick="anterior();">Anterior</span></li>
					</c:if>
					<div id="paginationNum" class="col p-0 m-0 paginationNum">
						<c:forEach begin="1" step="1" end="${totalPag}" var="pag">
							<li class="page-item"><button class=" numPagination mostrar" id="pag${pag}" onclick="num_Pagination(${pag})">${pag}</button></li>
						</c:forEach>
					</div>
					<c:if test="${end < totalArticulos}">
						<li class="page-item col-1 p-0 m-0" style="max-width: max-content;"><span style="cursor: pointer;" class="action-paginador" onclick="siguiente();">Siguiente</span></li>
					</c:if>
				</ul>
			</div>
		</c:when>
		<c:otherwise>
			<span class="barra mb-2 mt-2">Sin Documentos</span>
		</c:otherwise>
	</c:choose>
</div>
<style>
	
</style>

<script type="text/javascript">
	var keyword = '${keyword}';
	var category = '${category}';
	var catBuscada = '${catBuscada}';
	var start = '${start}';
	var end = '${end}';
	var paginador = '${paginador}';
	
	function num_Pagination(pag){
		$("#cargando").show();
		var startTemp = (pag * 20) - 20;
		if(pag == 1 ){
			startTemp = 0;
		}
		var finTemp = pag * 20;
		if(keyword == '' && category == ''){
			
			window.location.href = window.location.origin+window.location.pathname+"?start="+startTemp+"&end="+finTemp;
		}else{
			window.location.href = window.location.origin+window.location.pathname+"?keyword="+keyword+"&id="+category+"&start="+startTemp+"&end="+finTemp;
		}
	}
	
	function ocultarPag(id,scrollIf,animateIf){
        var idTemp = parseInt(id);
        var anterior = parseInt(id) - 1;
        var conut = parseInt(id) + 4;
        $(".numPagination").removeClass("mostrar");
        $("#pag"+idTemp).addClass("mostrar");
        $("#pag"+idTemp).addClass("activoPag");

        if(screen.width < 768){
            $("#pag"+idTemp).addClass("mostrar");
            $("#pag"+(idTemp +1)).addClass("mostrar");
        }else{
            while (++idTemp < conut) {
                $("#pag"+idTemp).addClass("mostrar");
            } 
        }

        if (scrollIf) {
            $("#pag"+anterior).addClass("mostrar");
            $("html, body").animate({ scrollTop: 0 }, "slow");
        }

        if (animateIf) {
            $("#pag"+anterior).addClass("mostrar");
            $("html, body").animate({ scrollTop: 0 }, "0s");
        }
    }
	
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
		$("#cargando").show();
		
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
		$("#cargando").show();
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
		if(id == null){
			id = catBuscada;
		}
		$(".boton-categorias").removeClass("cat-select");
		
		$("#cat_"+id).addClass("cat-select")
		$(".texto-categoria").text($("#cat_"+id).text().split(" ")[0]);
		if(start == '' || start == '0'){
			ocultarPag(1,false,false);
		}else{
			var pagactual = end / 20;
			ocultarPag(pagactual,false,false);
		}
		
	});
	
</script>