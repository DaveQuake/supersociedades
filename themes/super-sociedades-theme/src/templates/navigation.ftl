<#--<#assign 
    icon_toggle = '<img class="lexicon-icon" src="${images_folder}/icon-menu.svg" />'
/>
-->
<@liferay_portlet["runtime"]
                        portletName = "com_liferay_site_navigation_menu_web_portlet_SiteNavigationMenuPortlet"
                />

<#--
<div class="">
	<#if has_navigation>
	   <#include "${full_templates_path}/navigation_mobile.ftl" />
	</#if>
<nav class="${nav_css_class} navigation-supersoc d-lg-flex d-md-flex d-sm-flex d-none" id="navigation" role="navigation">
	<ul aria-label="<@liferay.language key="site-pages" />" role="menubar" class="menu-supersoc">
		<#list nav_items as nav_item>
			<#assign
				nav_item_attr_has_popup = ""
				nav_item_css_class = ""
				nav_item_caret = ""
				css_show_caret = ""
				nav_item_layout = nav_item.getLayout()
			/>

			<#if nav_item.isSelected()>
				<#assign
					nav_item_attr_has_popup = "aria-haspopup='true'"
					nav_item_css_class = "selected"
				/>
			</#if>

			<#if nav_item.hasChildren()>
				<#assign
					css_caret = '<img class="lexicon-icon" src="${images_folder}/svg/icon-subright.svg" />'
				/>
			</#if>

			<li class="${nav_item_css_class} py-3 text-wrap text-center first-child" id="layout_${nav_item.getLayoutId()}" role="presentation">
				<a class="item-first-child" aria-labelledby="layout_${nav_item.getLayoutId()}" ${nav_item_attr_has_popup} href="${nav_item.getURL()}" ${nav_item.getTarget()} role="menuitem"><span><@liferay_theme["layout-icon"] layout=nav_item_layout /> ${nav_item.getName()}</span></a>
				
				<#if nav_item.hasChildren()>
					<ul aria-label="<@liferay.language key="site-pages" />" role="menubar" class="child-menu my-3 px-4" >
						<#list nav_item.getChildren() as nav_child>
							<#assign
								nav_child_attr_has_popup = ""
								nav_child_css_class = ""
								css_caret = ""
								nav_child_layout = nav_child.getLayout()
							/>
							
							<#if nav_item.isSelected()>
								<#assign
									nav_child_attr_has_popup = "aria-haspopup='true'"
									nav_child_css_class = "selected"
								/>
							</#if>

							<#if nav_child.hasChildren()>
								<#assign
									css_caret = '<img class="lexicon-icon" src="${images_folder}/svg/icon-subright.svg" />'
								/>
							</#if>

							<li class="${nav_child_css_class} second-child" id="layout_${nav_child.getLayoutId()}" role="presentation">
								<a class="ajst_txt_sec_lvl" aria-labelledby="layout_${nav_child.getLayoutId()}" ${nav_child_attr_has_popup} href="${nav_child.getURL()}" ${nav_child.getTarget()} role="menuitem"><span><@liferay_theme["layout-icon"] layout=nav_child_layout />${nav_child.getName()} </span></a><span class="">${css_caret}</span>

								<#if nav_child.hasChildren()>
									<ul class="child-third-menu px-4" role="menu">
										<#list nav_child.getChildren() as nav_third_child>
											<#assign
												nav_item_caret_sub = ""
											/>

											<#if nav_third_child.hasChildren()>
												<#assign
												nav_item_caret_sub = '<img class="lexicon-icon" src="${images_folder}/svg/icon-subright.svg" />'
												/>
											</#if>

											<li class="${nav_child_css_class} " id="layout_${nav_third_child.getLayoutId()}" role="presentation">
												<a class="ajst_txt_sec_lvl" aria-labelledby="layout_${nav_third_child.getLayoutId()}" href="${nav_third_child.getURL()}" ${nav_third_child.getTarget()} role="menuitem">${nav_third_child.getName()} </a>
												<span class="">${nav_item_caret_sub}</span>

												<!--Cuarto nivel de menú-->     <#--
												<#if nav_third_child.hasChildren()>
												<ul class="child-fourth-menu px-4" role="menu">
													<#list nav_third_child.getChildren() as nav_fourth_child>
														<li class="${nav_child_css_class} " id="layout_${nav_fourth_child.getLayoutId()}" role="presentation">
															<a aria-labelledby="layout_${nav_fourth_child.getLayoutId()}" href="${nav_fourth_child.getURL()}" ${nav_fourth_child.getTarget()} role="menuitem">${nav_fourth_child.getName()} </a>
														</li>
													</#list>
												</ul>
												</#if>
											</li>
										</#list>
									</ul>
									</#if>
							</li>
						</#list>
					</ul>
					</#if>
			</li>
		</#list>
	</ul>
</nav>
</div>


-->