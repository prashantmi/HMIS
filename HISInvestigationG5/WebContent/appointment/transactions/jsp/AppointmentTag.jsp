<%System.out.println("m here"); %>
<%@page import="hisglobal.hisconfig.Config,hisglobal.presentation.Status"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1"   pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<%-- <%@ taglib uri="/WEB-INF/AppointmentTools.tld" prefix="appt"%> --%>

<%@page %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">



<link href="/HIS/hisglobal/css/buttons.css" rel="stylesheet" type="text/css">
<link href="/HIS/appointment/css/appointment.css" rel="stylesheet" type="text/css">
<link href="/HIS/hisglobal/css/layout.css" rel="stylesheet" type="text/css">
<link href="/HIS/hisglobal/css/jquery-ui.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="/HIS/hisglobal/css/jqueryExtValidationToolTip.css">
<link rel="stylesheet" href="/HIS/hisglobal/css/easyui.css">
<!-- <link href="/HISRegistration/hisglobal/css/jquery.timeentry.css" rel="stylesheet"> -->
<link href="/HIS/hisglobal/css/basic.css" rel="stylesheet" type="text/css">
<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/masterutil/js/jquery/jquery-2.0.3.min.js"></script>
<script type="text/javascript" src="/HIS/hisglobal/masterutil/js/jquery/jquery.easyui.js"></script>
<script type="text/javascript" src="/HIS/hisglobal/masterutil/js/jquery/jquery-ui.js"></script>
<script type="text/javascript" src="/HIS/hisglobal/masterutil/js/jquery/jqueryExtValidation.js"></script>
<script type='text/javascript' src='/HIS/hisglobal/masterutil/js/jquery/jquery.simplemodal.js'></script>
<%-- <script type="text/javascript" src="./../../appointment/transactions/js/NewAppointment.js"></script> --%>
<%-- <script type="text/javascript" src="./../../registration/masters/js/registration.js" /></script> --%>
<%-- <script type="text/javascript" src="./../../registration/transactions/js/popup.js"></script> --%>


<script src="/HIS/hisglobal/js/jquery.plugin.js"></script>
<%-- <script src="/HISRegistration/hisglobal/js/jquery.timeentry.js"></script> --%>
<script type="text/javascript" src="/HISInvestigationG5/new_investigation/js/appointment.js"></script>


<title>Confirm Appointment</title>
<script>


	
</script>

</head>

<body>
<center>
<s:actionerror/>

<s:form action="AppointmentTags">
	<s:property value="aptTagHTML" escape=""/>
</s:form>

</center>
</body>
</html>