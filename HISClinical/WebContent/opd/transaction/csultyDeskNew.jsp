<!-- 
/**
 * @author CDAC
 */
-->

<%--##		Creation Date			: 	16-01-2015
	##		Reason	(CR/PRS)		: 	CR
	##		Created By				:	AKASH SINGH
--%>
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<%@page import="org.apache.struts.tiles.ComponentContext"%>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/master.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/>
<his:css src="/hisglobal/css/calendar-blue2.css"/>

<his:javascript src="/hisglobal/utility/dynamicdesk/js/deskNew.js" />
<his:javascript src="/hisglobal/js/calendar.js"/>
<his:javascript src="/hisglobal/js/dateFunctions.js"/> 
<his:javascript src="/hisglobal/js/commonFunctions.js"/> 
<his:javascript src="/hisglobal/js/validationCalls.js"/>
<his:javascript src="/hisglobal/js/validationCommon.js"/>

<his:javascript src="/registration/js/popup.js"/>
<his:javascript src="/registration/js/calendar-setup.js"/> 
<his:javascript src="/registration/js/registration.js"/>
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<his:javascript src="/opd/js/casulty_desk_new.js" />

<html>
	<head>
		<meta http-equiv="pragma" content="no-cache">
	</head>

	<body class="tundra">
	<%
		ComponentContext compContext = (ComponentContext)pageContext.getAttribute("org.apache.struts.taglib.tiles.CompContext", 2);
		String header=(String)compContext.getAttribute("header");
		String body=(String)compContext.getAttribute("body");
		String footer=(String)compContext.getAttribute("footer");
	%>
		<html:form action="/csultyDeskNew" styleId="commonTransactionLayoutFormId">
			<table width="100%" cellpadding="0" cellspacing="0">
				<tr><td width="100%" align="center">
					<jsp:include page="<%=header%>" flush="true"></jsp:include>
				</td></tr>

				<tr><td width="100%" align="center">
					<jsp:include page="<%=body%>" flush="true"></jsp:include>
				</td></tr>

				<tr><td width="100%" align="center">
					<jsp:include page="<%=footer%>" flush="true"></jsp:include>
				</td></tr>
			</table>
			
			<his:status />
			
			<html:hidden name="CsultyDeskFB" property="mode" />
			<html:hidden name="CsultyDeskFB" property="deskMenuId" />
			<html:hidden name="CsultyDeskFB" property="patCrNo" />
			<html:hidden name="CsultyDeskFB" property="crNoSelected" />
		</html:form>
	</body>
</html>