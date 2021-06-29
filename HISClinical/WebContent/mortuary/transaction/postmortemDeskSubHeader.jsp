<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>


<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<%@page import="hisglobal.utility.dynamicdesk.DynamicDeskConfig"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/master.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/>
<his:css src="/hisglobal/css/calendar-blue2.css"/>

<his:javascript src="/hisglobal/js/commonFunctions.js" />
<his:javascript src="/registration/js/registration.js"/>
</head>

<body>
<form id="postmortemDeskSubheaderId" >
<his:TitleTag name="POSTMORTEM DESK">
	<div align="left">
		<%=(String)session.getAttribute(DynamicDeskConfig.DYNAMIC_DESK_DESK_NAME) %>
	</div>	
</his:TitleTag>
</form>
</body>
</html>