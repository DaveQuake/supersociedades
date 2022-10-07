<%@ include file="../init.jsp"%>

<%
String idCategory = GetterUtil.getString(portletPreferences.getValue(SupersociedadesBuscadorInternoPortletKeys.CONFIG_ID_CATEGORY, StringPool.BLANK));
String idCategoryInicial = GetterUtil.getString(portletPreferences.getValue(SupersociedadesBuscadorInternoPortletKeys.CONFIG_ID_CATEGORY_INICIAL, StringPool.BLANK));
String titulo = GetterUtil.getString(portletPreferences.getValue(SupersociedadesBuscadorInternoPortletKeys.CONFIG_TITULO, StringPool.BLANK));
String bajada = GetterUtil.getString(portletPreferences.getValue(SupersociedadesBuscadorInternoPortletKeys.CONFIG_BAJADA, StringPool.BLANK));
String dlfile = GetterUtil.getString(portletPreferences.getValue(SupersociedadesBuscadorInternoPortletKeys.CONFIG_DLFILE, StringPool.BLANK));
String journalArticle = GetterUtil.getString(portletPreferences.getValue(SupersociedadesBuscadorInternoPortletKeys.CONFIG_JA, StringPool.BLANK));
String paginador = GetterUtil.getString(portletPreferences.getValue(SupersociedadesBuscadorInternoPortletKeys.CONFIG_PAGINADOR, StringPool.BLANK));
%>

<liferay-ui:error key="internal-error" message="internal-error" />
<liferay-portlet:actionURL portletConfiguration="true" var="configurationURL" />

<aui:form name="fm" action="<%=configurationURL%>" method="post">
	<aui:fieldset>
		<aui:input maxlength="100" name="titulo" value="<%=titulo%>" label="TITULO CATEGORIA"/>			
	</aui:fieldset>
	<aui:fieldset>
		<aui:input maxlength="200" name="bajada" value="<%=bajada%>" label="BAJADA BUSCADOR"/>
	</aui:fieldset>
	
	<aui:fieldset>
		<aui:input type="10" name="paginador" value="<%=paginador%>" label="PAGINADOR"/>
	</aui:fieldset>
	
	<aui:fieldset>
		<aui:input maxlength="10" name="idCategory" value="<%=idCategory%>" label="CATEGORIA PADRE"/>			
	</aui:fieldset>

	<aui:fieldset>
		<aui:input maxlength="10" name="idCategoryInicial" value="<%=idCategoryInicial%>" label="CATEGORIA INICIAL"/>			
	</aui:fieldset>

	<aui:fieldset>
		<aui:input type="checkbox" name="dlfile" value="<%=dlfile%>" label="DLFILE"/>
	</aui:fieldset>
	
	<aui:fieldset>
		<aui:input type="checkbox" name="journalArticle" value="<%=journalArticle%>" label="JOURNAL ARTICLE"/>			
	</aui:fieldset>
	
	<aui:button-row>
		<aui:button value="save" type="submit"></aui:button>
	</aui:button-row>
</aui:form>
