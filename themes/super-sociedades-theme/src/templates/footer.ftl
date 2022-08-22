    <footer id="footer" role="contentinfo">
                <#--
                <#if footerArticleIdSuper?has_content>
                   <@liferay_journal["journal-article"]
                       articleId=footerArticleIdSuper
                        groupId=group_id
                    />
                </#if>
                -->

                <@liferay_portlet["runtime"]
                        portletName = "com_liferay_journal_content_web_portlet_JournalContentPortlet"
                />
<#--          <div class="container">
            <div class="row mx-0 bg-footer-bottom">
                <div class="col-12 text-md-left text-sm-center text-center py-md-4 py-sm-4 py-3">
                    <div class="marca-col-footer text-md-right text-sm-center text-right pr-md-3 pr-sm-3 pr-3 mr-md-3 mr-sm-3 mr-2">
                        <a href="${linkMarcaCol}" target="_blank">
                        <img class="img-fluid img-marca-col" alt="Logo marca Colombia" title="Logo marca Colombia" src="${urlImageMarcaPais}" /></a>
                    </div>
                    <a href="${linkGovNal}" target="_blank">
                    <img alt="Logo gobierno" title="Logo gobierno" class="img-fluid img-gov-footer align-middle py-2" src="${urlImageGobierno}" ></a>
                </div>
            </div>
        </div>  -->
        <div class="bg-primary">
            <div class="container">
                <div class="d-flex justify-content-between align-items-center flex-column flex-sm-row p-3 p-sm-0">
                    <ul class="d-flex m-0 py-3 list-unstyled">
                        <li>
                            <a href="${linkMarcaCol}" target="_blank">
                                <img src="${urlImageMarcaPais}" alt="Marca Colombia" width="60px" class="img-fluid">
                                <span class="sr-only">Logo marca Colombia</span>
                            </a>
                        </li>
                        <li class="border-left border-white pl-4 ml-4">
                        </li>
                        <li class="align-self-center">
                            <a href="${linkGovNal}" target="_blank">
                                <img src="${urlImageGobierno}" alt="Logo Gobierno de Colombia" width="130px" class="img-fluid">
                                <span class="sr-only">Logo Gobierno de Colombia</span>
                            </a>
                        </li>
                    </ul>                    
                </div>
            </div>
        <div>

    </footer>