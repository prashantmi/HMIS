<!-- 
	# Project Name : NIMS
	# Process Name : Appointment Desk Calendar
	# Developer    : Singaravelan 
	# Date 		   : 24-Apr-2015
 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<%@ taglib uri="/WEB-INF/AppointmentTools.tld" prefix="appt"%>
<%@ taglib uri="/WEB-INF/comboPersistence.tld" prefix="cmbPers"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="../../hisglobal/css/buttons.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/layout.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/jquery-ui.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="../../hisglobal/css/jqueryExtValidationToolTip.css">
<link rel="stylesheet" href="../../hisglobal/css/easyui.css">
<link rel="stylesheet" href="../../hisglobal/css/basic.css">
<link href='/HIS/hisglobal/fullcalendar/css/fullcalendar.css' rel='stylesheet' />
<link href='/HIS/hisglobal/fullcalendar/css/fullcalendar.print.css' rel='stylesheet' media='print' />
<link href="/HIS/appointment/css/appointment.css" rel="stylesheet" >
<link href='/HIS/hisglobal/css/jquery.qtip.min.css' rel='stylesheet' />

<script language="JavaScript" type="text/javascript" src="../../hisglobal/masterutil/js/jquery/jquery-2.0.3.min.js"></script>
<script type="text/javascript" src="../../hisglobal/masterutil/js/jquery/jquery.easyui.js"></script>
<script type="text/javascript" src="../../hisglobal/masterutil/js/jquery/jquery-ui.js"></script>
<script src='/HIS/hisglobal/fullcalendar/js/fullcalendar.min.js'></script>
<script src='/HIS/hisglobal/js/jquery/jquery.qtip.min.js'></script>

<script type="text/javascript" src="./../../appointment/transactions/js/aptDskCalendar.js"></script>
<script language="JavaScript" type="text/javascript" src="/HISRegistration/hisglobal/masterutil/js/jquery/security.js" ></script>
<title>Appointment Desk</title>
<style>
	#calendar {
		width: 900px;
		margin: 0 auto;
	}
	.fc-event-time{
		display: none;
	}
	.tipCustomStyle{
		font-family: Verdana,Arial,sans-serif;
		font-size: 0.75em;
		line-height: 150%;
		
	}
	.custbtncolor .ui-button-text{
		background: #086FA6;
		color: #FFFFFF;
		font-weight: bold
	}
	.custoverlay{
	   		opacity: 0.9;   		  
	}
</style>

<script>

	$(document).ready(function() {	

	
	});
	
</script>	
</head>
<body>
<s:form action="AptDskCalendar">
	
    <div class="wrapper rounded" style="width:95%" >
    <div class="div-table">
			<div class="div-table-row ">
				<div class="div-table-col title width100 ">
						<s:text name="Apt_appointment"/>&nbsp;<s:text name="Desk"/>  
				</div>
			</div>
			<div class="div-table-row ">
				<appt:AppointmentParameterComboTag  tagView="TRANSACTION" controllerName="NewAppointment"   scriptCallBackFunctionName="getActualParaIdWiseEssensials"/>
			</div>
			<div class="div-table-row ">
				<div id='calendar'></div>
			</div>
	</div>
    </div>
    <s:hidden name="allParaId"></s:hidden>
    <cmbPers:cmbPers></cmbPers:cmbPers>
    <s:token></s:token>
</s:form>
<div id="fade"></div>
<div id="modal">
     <img id="loader" src="/HIS/hisglobal/images/loading.gif" />
</div>
</body>
</html>