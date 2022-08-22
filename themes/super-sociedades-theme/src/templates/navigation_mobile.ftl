<div class="d-lg-none d-md-none d-sm-none container-supersoc">
    <#if has_navigation && is_setup_complete>
            <nav>
                <ul aria-label="<@liferay.language key="site-pages" />" class="menu-super-mobile" role="menubar">
                        <#list nav_items as nav_item>
                            <#assign
                                nav_item_attr_has_popup = ""
                                nav_item_css_class = "nav-item"
                                nav_item_layout = nav_item.getLayout()
                                nav_item_caret = ""
                                nav_item_caret_up = ""
                                nav_item_css_color = ""
                                page_name = ""
                                dropdown_css_class = ""
                            />
                
                            <#if nav_item.isSelected()>
                                <#assign
                                    nav_item_attr_has_popup = "aria-haspopup='true'"
                                    nav_item_css_class = "${nav_item_css_class} selected"
                                />
                            </#if>
                            
                            <#if nav_item.hasChildren()>
                                <#assign
                                    nav_item_css_class = "${nav_item_css_class} dropdown"
                                    nav_item_caret = '<svg class="lexicon-icon icon-sub hide-down show-caret"><use xlink:href="${images_folder}/lexicon/icons.svg#angle-down" /></svg>'
                                    nav_item_caret_up ='<svg class="lexicon-icon icon-sub hide-up hide-caret"><use xlink:href="${images_folder}/lexicon/icons.svg#angle-up" /></svg>'
                                />
                                <#if nav_item?is_last>
                                   <#assign 
                                       page_name = "Otras secciones"
                                       dropdown_css_class = "dropdown-css-class"
                                   />
                                </#if>
                            </#if>
                
                            <li class="item nav-item" id="layout_${nav_item.getLayoutId()}" role="presentation">
                                <a aria-labelledby="layout_${nav_item.getLayoutId()}" class="nav-link main-item" ${nav_item_attr_has_popup} href="${nav_item.getURL()}" ${nav_item.getTarget()} role="menuitem"><span class="${nav_item_css_color} ${dropdown_css_class}"><@liferay_theme["layout-icon"] layout=nav_item_layout /> ${nav_item.getName()} ${page_name}</span> ${nav_item_caret} ${nav_item_caret_up}</a>
                
                                <#if nav_item.hasChildren()>
                                
                                    <ul class="menu-child-mobile " role="menu">
                                        <div class="main-submenu">
                                            <div class="mx-lg-3 mx-md-3 mx-sm-3 mx-3 mt-lg-2 mt-md-2 mt-sm-2 mt-2">
                                                
                                            </div>								
                                        
                                        <div>
                                        <#list nav_item.getChildren() as nav_child>
                                            <#assign
                                                nav_child_css_class = "nav-item"
                                            />
                
                                            <#if nav_item.isSelected()>
                                                <#assign
                                                    nav_child_css_class = "nav-item selected"
                                                />
                                            </#if>
                                        
                                            <li class="${nav_child_css_class}" id="layout_${nav_child.getLayoutId()}" role="presentation">
                                                <a aria-labelledby="layout_${nav_child.getLayoutId()}" class="nav-link" href="${nav_child.getURL()}" ${nav_child.getTarget()} role="menuitem"><span>${nav_child.getName()}</span></a>
                                            </li>
                                        
                                        </#list>
                                    </div>
                                    </div>
                                    </ul>
                                </#if>
                            </li>
                        </#list>
                    </ul>
            </nav>
    </#if>
    </div>
    
    <script>
        $(document).ready(function(){
            $('.header-wrapper .header .container-fluid div .container-supersoc').hide();
            $('.container-logos div div #closebtn').hide();
            $(".container-logos div div #checkbtn").on( "click", function() {
               $('.header-wrapper .header .container-fluid div .container-supersoc').slideDown(400); 
               $('.container-logos div div #checkbtn').hide();
               $('.container-logos div div #closebtn').show();
             });
            $(".menu-super-mobile .encabezado-menu #btnclose").on( "click", function() {
                $('.header-wrapper .header .container-fluid div .container-supersoc').hide();
            });

            $('.container-logos div div #closebtn').on('click', function(){
               $('.header-wrapper .header .container-fluid div .container-supersoc').slideUp(400);
               $('.container-logos div div #closebtn').hide();
               $('.container-logos div div #checkbtn').show();
            })
        });

        /*Muestra los submenú de cada item seleccionado*/
        $(".menu-super-mobile li:has(ul)").on("click", function(ev) {
            ev.preventDefault();
            $('.hide-down').toggleClass('show-caret');
            $('.hide-down').toggleClass('hide');
            $('.hide-up').toggleClass('hide-caret');
            $(this).children("ul").slideToggle(300);
        });
    </script>