<!-- Header -->
<script type="text/javascript">
    ${scriptG}
</script>

<#assign preferences = freeMarkerPortletPreferences.getPreferences({
    "portletSetupPortletDecoratorId", "barebone"
}) />
<header class="header-wrapper" id="header" role="banner">
    <div class="header">
        <div class="container-fluid px-lg-0 px-md-0 px-sm-0 px-0">


<#--              <div class="row py-2 bg-footer-bottom mx-0">
                <div class="col-md-3 col-sm-3 col-6 text-md-center text-sm-center text-lef">
                    <a href="${linkGovNal}" target="_blank">
                    <img alt="Logo Gobierno" title="Logo Gobierno" src="${urlImageGobierno}" class="img-fluid logo-gov-header" />
                    </a>
                </div>
                <div class="col-md-7 col-sm-7 col-0 d-lg-block d-md-block d-sm-block d-none"></div>
                <div class="col-md-2 col-sm-2 col-6  text-md-center text-sm-center text-right">
                    
                    <#if theme_display.isSignedIn()>
                            <a href="${urlLogin}" class="pr-4 text-white d-md-none d-sm-none d-inline-block" target="_self">${session_down}</a>
                        <#else>
                            <a href="${urlLogin}" class="pr-4 text-white d-md-none d-sm-none d-inline-block" target="_self">${session_up}</a>
                    </#if>
                    <a href="${urlLanguage}" target="_self">
                        <img alt="Idioma" src="${images_folder}/idioma.svg" class="img-fluid idioma-header" />
                    </a>
                </div>
            </div>    -->


        <nav class="navbar bg-primary">
            <div class="container">

                <a href="${linkGovNal}" target="_blank">
                    <img alt="Logo Gobierno" title="Logo Gobierno" src="${urlImageGobierno}" class="img-fluid logo-gov-header" />
                </a>

                <span class="d-flex" role="search">

                    <a href="${urlLanguage}" target="_self">
                        <img alt="Idioma" src="${images_folder}/idioma.svg" class="img-fluid language_head" />
                    </a>

                </span>
            </div>
        </nav>





<#--              <div class="navbar navbar-expand-lg navbar-govco navbar-expanded">
                <div class="navbar-container container">
                    <div class="navbar-logo float-left">    
                        <a href="${linkGovNal}" target="_blank">      
                            <img src="${urlImageGobierno}" height="30" alt="Logo Gov.co">          
                        </a>
                    </div>
                    <div class="nav-item-primary ml-auto mr-2">
                        <#if theme_display.isSignedIn()>
                                <a href="${urlLogin}" class="pr-4 text-white d-md-none d-sm-none d-inline-block" target="_self">${session_down}</a>
                        <#else>
                                <a href="${urlLogin}" class="pr-4 text-white d-md-none d-sm-none d-inline-block" target="_self">${session_up}</a>
                        </#if>
                        <a href="${urlLanguage}" target="_self">
                            <img alt="Idioma" src="${images_folder}/idioma.svg" class="img-fluid idioma-header" />
                        </a>
                    </div>
                </div>
            </div>  -->




  <#--            <div class="row mx-0 container-logos">

                <div class="col-lg-6 col-12 pl-md-7 pl-sm-7 pl-4 pt-4">
                    <div class="pr-lg-3 pr-md-3 pr-sm-3 pr-3 my-2">
                        <a href="${urlHome}" target="_self">
                            <img class="img-fluid" alt="Logo Super sociedades" title="Logo Super sociedades" src="${urlImageLogo}" />
                        </a>
                        <span class="men_mob">
                            <svg xmlns="http://www.w3.org/2000/svg" width="30" height="30" fill="#2a4779" class="bi bi-list" viewBox="0 0 16 16">
                                <path fill-rule="evenodd" d="M2.5 12a.5.5 0 0 1 .5-.5h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5zm0-4a.5.5 0 0 1 .5-.5h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5zm0-4a.5.5 0 0 1 .5-.5h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5z"/>
                            </svg>
                        </span>
                    </div>                    

                </div>
    
                <div class="col-lg-6 col-12 text-md-right text-sm-right text-center pt-md-4">
                    <span class="text-username d-flex flex-row justify-content-end"> ${user_name} 
                        <#if theme_display.isSignedIn()>
                        <a href="/c/portal/logout" target="_self" class="d-md-block dsm-block d-none text-prisma pl-3">${session_down}</a>
                        <#else>
                        <a href="${urlLogin}" target="_self" class="d-md-block dsm-block d-none text-prisma pl-3">${session_up}</a>
                    </#if>
                    </span>
                   
                    <div class="bar-search">
                        <@liferay_portlet["runtime"]
                            defaultPreferences="${preferences}"
                            portletName="com_liferay_portal_search_web_search_bar_portlet_SearchBarPortlet"
                        />
                    </div>
                </div>
            </div>
  -->

            <nav class="navbar pt-3">
                <div class="container">

                    <a href="${urlHome}" target="_self">
                            <img class="img-fluid" alt="Logo Super sociedades" title="Logo Super sociedades" src="${urlImageLogo}" style="width: 257px"/>
                    </a>
                    <span class="men_mob">
                        <svg xmlns="http://www.w3.org/2000/svg" width="30" height="30" fill="#2a4779" class="bi bi-list" viewBox="0 0 16 16">
                            <path fill-rule="evenodd" d="M2.5 12a.5.5 0 0 1 .5-.5h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5zm0-4a.5.5 0 0 1 .5-.5h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5zm0-4a.5.5 0 0 1 .5-.5h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5z"/>
                        </svg>
                    </span>

                    <span class="" role="search">

                        <div class="text-username d-flex flex-row justify-content-end"> ${user_name} 
                            <#if theme_display.isSignedIn()>
                            <a href="/c/portal/logout" target="_self" class="d-md-block dsm-block d-none text-prisma pl-3">${session_down}</a>
                            <#else>
                            <a href="${urlLogin}" target="_self" class="d-md-block dsm-block d-none text-prisma pl-3">${session_up}</a>
                        </#if>
                        </div>
                    
                        <div class="bar-search">
                            <@liferay_portlet["runtime"]
                                defaultPreferences="${preferences}"
                                portletName="com_liferay_portal_search_web_search_bar_portlet_SearchBarPortlet"
                            />
                        </div>

                    </span>
                </div>
            </nav>





            
            <#if has_navigation>
                <#include "${full_templates_path}/navigation.ftl" />
            </#if>
        </div>
    </div>
</header>