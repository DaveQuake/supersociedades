<%@ include file="init.jsp"%>

<%
String titulo = GetterUtil.getString(portletPreferences.getValue(Constantes.TITULO, StringPool.BLANK));
String idCarpeta = GetterUtil.getString(portletPreferences.getValue(Constantes.ID_CARPETA, StringPool.BLANK));
%>

<liferay-ui:error key="internal-error" message="internal-error" />
<liferay-portlet:actionURL portletConfiguration="true" var="configurationURL" />

<aui:form name="fm" action="<%=configurationURL%>" method="post">
	<aui:fieldset>
		<aui:input maxlength="20" name="idCarpeta" value="<%=idCarpeta%>" label="Id de la carpeta padre:"/>			
	</aui:fieldset>
	<aui:fieldset>
		<aui:input maxlength="500" name="titulo" value="<%=titulo%>" label="Titulo de las seccion: "/>			
	</aui:fieldset>
	<aui:button-row>
		<aui:button value="save" type="submit"></aui:button>
	</aui:button-row>
</aui:form>