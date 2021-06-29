<%System.out.println("m here"); %>
<%@page import="hisglobal.hisconfig.Config,hisglobal.presentation.Status"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<%@ taglib uri="/WEB-INF/AppointmentTools.tld" prefix="appt"%>
<%@ taglib uri="/WEB-INF/comboPersistence.tld" prefix="cmbPers"%>
<%@page %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

 
<link href="../../hisglobal/css/buttons.css" rel="stylesheet" type="text/css">
<link href="../../appointment/transactions/css/appointment.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/layout.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/jquery-ui.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="../../hisglobal/css/jqueryExtValidationToolTip.css">
<link rel="stylesheet" href="../../hisglobal/css/easyui.css">
<link href="/HISRegistration/hisglobal/css/jquery.timeentry.css" rel="stylesheet">
<link href="/HIS/hisglobal/css/basic.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="/HIS/hisglobal/datepicker/css/datepicker.css">

<script language="JavaScript" type="text/javascript" src="../../hisglobal/masterutil/js/jquery/jquery-2.0.3.min.js"></script>
<script type="text/javascript" src="../../hisglobal/masterutil/js/jquery/jquery.easyui.js"></script>
<script type="text/javascript" src="../../hisglobal/masterutil/js/jquery/jquery-ui.js"></script>
<script type="text/javascript" src="../../hisglobal/masterutil/js/jquery/jqueryExtValidation.js"></script>
<script type='text/javascript' src='../../hisglobal/masterutil/js/jquery/jquery.simplemodal.js'></script>
<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/jquery/datePickerDefaultSetting.js"></script>

<script type="text/javascript" src="./../../appointment/transactions/js/NewAppointment.js"></script>
<script type="text/javascript" src="./../../registration/masters/js/registration.js" /></script>
<script type="text/javascript" src="./../../registration/transactions/js/popup.js"></script>
<script language="Javascript" type="text/javascript" src="/HIS/hisglobal/js/moment.min.js"></script>



<script src="../../hisglobal/js/jquery.plugin.js"></script>
<script src="/HISRegistration/hisglobal/js/jquery.timeentry.js"></script>
<script type="text/javascript" src="./../../appointment/transactions/js/appointment.js"></script>
<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/datepicker/js/datepicker.js"></script>

<%-- <script language="JavaScript" type="text/javascript" src="/HISRegistration/hisglobal/masterutil/js/jquery/security.js" ></script> --%>

<title>Confirm Appointment</title>
<script>
var HOLIDAY_DATES					=	[<%=request.getSession().getAttribute("HOLIDAY_DATES")%>];
$(document).ready(function() {
	var today=moment().format('DD-MMM-YYYY');//new Date().toLocaleFormat('%d-%b-%Y');
	var selectedDate=$('[name="aptForDate"]').val();
	//alert("HOLIDAY_DATES "+HOLIDAY_DATES);
	$('#appointmentDatePopup').DatePicker({
		format: 'd-M-Y',default_position :'below',
		//disabled_dates:['1 11 2015','11 11 2015'],
		disabled_dates:HOLIDAY_DATES,
		start_date:today,	onSelect: function(d,i){if(d !== i.lastVal){$(this).change();}}
	}).val(selectedDate);
	//alert(parent.document.getElementById("framePopup").src);
	
	
	
});


	
</script>

</head>

<body>
<center>
<s:actionerror/>

<s:form action="AppointmentTags">
	<s:property value="aptTagHTML" escape="false"/>
	 	  <s:hidden name="appointmentDatePopup"></s:hidden> 
	 	  <%-- <cmbPers:cmbPers></cmbPers:cmbPers> --%>
	 <s:token></s:token>
</s:form>

</center>
</body>
</html>