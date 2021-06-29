<%@page autoFlush="true" %>
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>

<%@page import="org.apache.struts.tiles.ComponentContext,hisglobal.hisconfig.Config"%>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/master.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/>
<his:css src="/hisglobal/css/calendar-blue2.css"/>

<his:javascript src="/registration/js/registration.js"/>
<his:javascript src="/registration/js/validationCalls.js"/>
<his:javascript src="/registration/js/validationCommon.js"/>
<his:javascript src="/registration/js/commonFunctions.js"/>
<his:javascript src="/registration/js/popup.js"/>

<%@ page import ="registration.*" %>

<html:form action="/master/deptRosterUnitSequencing">
<his:TransactionContainer>
	<his:TitleTag>
		<his:name><bean:message key="titleDeptRosterUnitSequencing"/></his:name>
	</his:TitleTag>
<his:ContentTag>
<table width="100%" cellpadding="1" cellspacing="1">
	<tr>
		<td width="17%" Class="tdfonthead">
			<bean:message key="department"/>
		</td>
			
		<td width="17%" Class="tdfont">
			<html:select property="departmentCode" tabindex="1" styleClass="regCbo" onchange="if(this.value!='-1') submitForm('GET_ROSTER_4_DEPT');">
				<html:option value="-1">Select Value</html:option>
				<html:options collection = "<%=Config.ESSENTIALBO_OPTION_DEPARTMENT%>" property = "value" labelProperty = "label"/>
			</html:select>
		</td>
</table>
</his:ContentTag>

<%ComponentContext compContext = (ComponentContext)pageContext.getAttribute("org.apache.struts.taglib.tiles.CompContext", 2);
String body=(String)compContext.getAttribute("body");
String footer=(String)compContext.getAttribute("footer");

%>
<jsp:include page="<%=body %>" flush="true"></jsp:include>
<jsp:include page="<%=footer %>" flush="true"></jsp:include>

<input type="hidden" name="hmode"/>
</his:TransactionContainer>
<his:status/>
</html:form>