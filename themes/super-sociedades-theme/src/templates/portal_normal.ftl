<#--original portal normal-->

<!DOCTYPE html>

<#include init />

<html class="${root_css_class}" dir="<@liferay.language key="lang.dir" />" lang="${w3c_language_id}">

<head>
	<title>${html_title}</title>

	<meta content="initial-scale=1.0, width=device-width" name="viewport" />

	<@liferay_util["include"] page=top_head_include />
	<script src="https://kit.fontawesome.com/b2e5483959.js" crossorigin="anonymous"></script>
	<link rel="preconnect" href="https://fonts.googleapis.com">
	<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
	<link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@600;700;800&family=Work+Sans:wght@400;500;600;700&display=swap" rel="stylesheet">
</head>

<body class="${css_class}">

<@liferay_ui["quick-access"] contentId="#main-content" />

<@liferay_util["include"] page=body_top_include />

<@liferay.control_menu />

<#assign 
   icon_go_up = '<svg class="lexicon-icon"><use xlink:href="${images_folder}/lexicon/icons.svg#angle-up" /></svg>'
/>
<#assign 
   session_up = "Iniciar sesión"
   session_down = "Cerrar sesión"
/>
<div class="container-fluid container-supersoc" id="wrapper">
	<#include "${full_templates_path}/header.ftl" />
	<section id="content">
		<h2 class="hide-accessible" role="heading" aria-level="1">${the_title}</h2>

		<#if selectable>
			<@liferay_util["include"] page=content_include />
		<#else>
			${portletDisplay.recycle()}

			${portletDisplay.setTitle(the_title)}

			<@liferay_theme["wrap-portlet"] page="portlet.ftl">
				<@liferay_util["include"] page=content_include />
			</@>
		</#if>
	</section>

	<div id="cont_acc">
	<!--<div class="container-accesibility d-lg-inline-block d-md-inline-block d-sm-inline-block">-->
		<div class="container-accesibility">
			<div class="container-buttons py-2 px-2 " id="buttons_accesibility">
				<a href="#" class="contraste" id="btnContrast">
					<img src="${urlImageContast}" class="my-1 d-inline-block" alt="Contraste" title="Contraste"/>
					<span class="label-contraste">Contraste</span>
				</a>
				<a href="#" class="text-down" id="button_text_down">
					<img src="${urlImageTextDown}" class="my-1 d-inline-block" alt="Reducir letra" title="Reducir letra"/>
					<span class="label-text-down">Reducir letra</span>
				</a>
				<a href="#" class="text-up" id="button_text_up">
					<img src="${urlImageTextUp}" class="my-1 d-inline-block" alt="Aumentar letra" title="Aumentar letra"/>
					<span class="label-text-up">Aumentar letra</span>
				</a>
				<a href="${urlRelief}" class="text-up" id="button_text_up" target="_blank">
					<img src="${urlImageRelief}" class="my-1 d-inline-block" alt="Centro de relevo" title="Centro de relevo"/>
					<span class="label-text-up">Centro de relevo</span>
				</a>
				<a href="${urlConvertic}" class="text-up" id="button_text_up" target="_blank">
					<img src="${urlImageConvertic}" class="my-1 d-inline-block" alt="Convertic" title="Convertic"/>
					<span class="label-text-up">Convertic</span>
				</a>
			</div>
			<!--<div>
				<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-eye" viewBox="0 0 16 16">
					<path d="M16 8s-3-5.5-8-5.5S0 8 0 8s3 5.5 8 5.5S16 8 16 8zM1.173 8a13.133 13.133 0 0 1 1.66-2.043C4.12 4.668 5.88 3.5 8 3.5c2.12 0 3.879 1.168 5.168 2.457A13.133 13.133 0 0 1 14.828 8c-.058.087-.122.183-.195.288-.335.48-.83 1.12-1.465 1.755C11.879 11.332 10.119 12.5 8 12.5c-2.12 0-3.879-1.168-5.168-2.457A13.134 13.134 0 0 1 1.172 8z"/>
					<path d="M8 5.5a2.5 2.5 0 1 0 0 5 2.5 2.5 0 0 0 0-5zM4.5 8a3.5 3.5 0 1 1 7 0 3.5 3.5 0 0 1-7 0z"/>
				</svg>
			</div>-->

		</div>
		
		<br/>
		<!--Botón accesibilidad tamaño mobile-->
		<!--<div class="resp-accesibility p-lg-0 p-md-0 p-sm-0 p-2 d-lg-none d-md-none d-sm-none d-inline-block" id="respAccesibility">-->
		<div class="resp-accesibility" id="respAccesibility" alt="Accesibilidad" title="Accesibilidad">

			<span class="show_acc">
				<svg xmlns="http://www.w3.org/2000/svg" width="15" height="15" fill="white" class=" bi bi-chevron-left" viewBox="0 0 16 16" alt="Accesibilidad" title="Accesibilidad">
					<path fill-rule="evenodd" d="M11.354 1.646a.5.5 0 0 1 0 .708L5.707 8l5.647 5.646a.5.5 0 0 1-.708.708l-6-6a.5.5 0 0 1 0-.708l6-6a.5.5 0 0 1 .708 0z"/>
				</svg>
				<i class='fas fa-wheelchair back_wheelchair' style='font-size:18px'></i>
			</span>

			<span class="hide_acc">
				<i class='fas fa-wheelchair wheelchair' style='font-size:18px'></i>
				<svg xmlns="http://www.w3.org/2000/svg" width="15" height="15" fill="white" class="hide_acc bi bi-chevron-right" viewBox="0 0 16 16" alt="Accesibilidad" title="Accesibilidad">
					<path fill-rule="evenodd" d="M4.646 1.646a.5.5 0 0 1 .708 0l6 6a.5.5 0 0 1 0 .708l-6 6a.5.5 0 0 1-.708-.708L10.293 8 4.646 2.354a.5.5 0 0 1 0-.708z"/>
				</svg>
			</span>

			<!--
			<img src="${images_folder}/svg/down-line.svg" class="down-line " id="downLine" />
			<img src="${images_folder}/svg/up-line.svg" class="up-line " id="upLine"/>
			-->
		</div>
	</div>
	


	<div class="top_help">
		<div class="main-goup d-lg-flex d-md-flex d-sm-flex d-none">
				<span class="goup-text text-center">Volver a arriba</span>
				<a href="#" class="go-up py-1 px-1" id="goUp">
					<img src="${images_folder}/svg/go-up.svg" class="my-1 d-inline-block" alt="Volver a arriba" title="Volver a arriba"/>	
				</a>
		</div>

		<!--Botón ir arriba responsive-->
		<div class="resp-maingoup">
			<div class="go-up py-1 px-1" id="goUp">
				<a href="#" class="p-2 text-white">${icon_go_up}</a>
			</div>
		</div>

		<div class="chatbot" id="chatBot">
			<div class="ayuda p-3" id="ayuda">
				<div class="rounded-circle">
					<img src="${urlImageChat}" class="img-fluid" alt="Menú de soporte" title="Menú de soporte">
				</div>
				<!-- 
				<div class="">
					<div class="" id="cicle-blanco"><i class="fal fa-times fa-2x icon-error"></i></div>
				</div>
				-->
			</div>

			<div class="menu_bg " id="support"></div>
			<div class="menu_list">
				<div class="menu_item" id="case">
					<p class="text-icon">Resuelva su caso </p>
					<div class="ico-item">
					<a href="${urlSolveCase}" target="_blank">
						<img class="" src="${images_folder}/soporte/caso.svg" alt="Resuelva su caso" title="Resuelva su caso">
					</a>
					</div>
				</div>
				<div class="menu_item" id="close">
					<p class="text-icon text-whatsapp"></p>
					<div class="ico-item ico-close">
						<img class="" src="${images_folder}/soporte/close.svg" alt="Cerrar menú soporte" title="Cerrar menú soporte">
					</div>
				</div>
				<div class="menu_item" id="participation">
					<p class="text-icon">Participación </p>
					<div class="ico-item">
					<a href="${urlParticipation}" target="_blank">
						<img class="" src="${images_folder}/soporte/users.svg" alt="Participación" title="Participación">
					</a>
					</div>
				</div>
				<div class="menu_item" id="complaints">
					<p class="text-icon">Denuncias </p>
					<div class="ico-item">
					<a href="${urlComplaints}" target="_blank">
						<img class="" src="${images_folder}/soporte/denuncias.svg" alt="Denuncias" title="Denuncias">
					</a>
					</div>
				</div>
				<div class="menu_item" id="pqrsd">
					<p class="text-icon">PQRSD </p>
					<div class="ico-item">
					<a href="${urlPqrsd}" target="_blank">
						<img class="" src="${images_folder}/soporte/pqrsd.svg" alt="PQRSD" title="PQRSD">
					</a>
					</div>
				</div>
				<div class="menu_item" id="whatsapp">
					<p class="text-icon">Chat de atención al ciudadano</p>
					<div class="ico-item">
					<a href="${urlAttentionChat}" target="_blank">
						<img class="" src="${images_folder}/soporte/whatsapp.svg" alt="Chat de atención al ciudadano" title="Chat de atención al ciudadano">
					</a>
					</div>
				</div>
			</div>
		</div>

	</div>


	<#include "${full_templates_path}/footer.ftl" />
</div>

<@liferay_util["include"] page=body_bottom_include />

<@liferay_util["include"] page=bottom_include />

<#--<!--<script type="text/javascript" src="${javascript_folder}/main.js"></script>-->

</body>

</html>

