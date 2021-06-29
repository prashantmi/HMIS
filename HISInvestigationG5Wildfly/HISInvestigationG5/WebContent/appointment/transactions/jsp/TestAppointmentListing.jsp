<%System.out.println("m here"); %>
<%@page import="hisglobal.hisconfig.Config,hisglobal.presentation.Status"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<%@ taglib uri="/WEB-INF/AppointmentTools.tld" prefix="appt"%>

<%@page %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">



<link href="../../hisglobal/css/buttons.css" rel="stylesheet" type="text/css">
<link href="../../appointment/transactions/css/appointment.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/layout.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/jquery-ui.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="../../hisglobal/css/jqueryExtValidationToolTip.css">
<link rel="stylesheet" href="../../hisglobal/css/easyui.css">
<link href="/HISRegistration/hisglobal/css/jquery.timeentry.css" rel="stylesheet">
<link href="/HIS/hisglobal/css/basic.css" rel="stylesheet" type="text/css">
<script language="JavaScript" type="text/javascript" src="../../hisglobal/masterutil/js/jquery/jquery-2.0.3.min.js"></script>
<script type="text/javascript" src="../../hisglobal/masterutil/js/jquery/jquery.easyui.js"></script>
<script type="text/javascript" src="../../hisglobal/masterutil/js/jquery/jquery-ui.js"></script>
<script type="text/javascript" src="../../hisglobal/masterutil/js/jquery/jqueryExtValidation.js"></script>
<script type='text/javascript' src='../../hisglobal/masterutil/js/jquery/jquery.simplemodal.js'></script>
<script type="text/javascript" src="./../../appointment/transactions/js/NewAppointment.js"></script>
<script type="text/javascript" src="./../../registration/masters/js/registration.js"></script>
<script type="text/javascript" src="./../../registration/transactions/js/popup.js"></script>


<script src="../../hisglobal/js/jquery.plugin.js"></script>
<script src="/HISRegistration/hisglobal/js/jquery.timeentry.js"></script>
<script type="text/javascript" src="/HISInvestigationG5/new_investigation/js/appointment.js"></script>



<title>Confirm Appointment</title>
<script>



$(document).ready(function() {

	
	
	//validateFields();
	$('#submitId').click(function(){
		var isValid = $('#NewAppointment').form('validate');
		//alert(isValid);
	    if(isValid==false)
	      return false;
	    document.forms[0].action =  "ConfirmNewAppointment.action";		
		document.forms[0].submit();
	    
	});
	
	return false;
});

	
</script>

</head>

<body>
<center>
<s:actionerror/>

<s:form action="NewAppointment">
	
	<br>
        	<appt:AppointmentListingTag  controllerName="NewAppointment"  listingMode="REG"  aptId="1" scriptCallBackFunctionName="getAppointmentShifts"/>
			
			<div class="div-table-button">
				<div class="div-table-row footerBar">
						<div class="div-table-col"> </div>
				</div>
				<div class="div-table-row emptyBar">
					<div class="div-table-col"> </div>
				</div>
				<div class="div-table-row" align="center">
					<a href="#" class="button" id="submitId" title="confirm appointment"><span class="save"><s:text name="confirm"/></span></a>					
				</div>				
			</div>
		
		 

</s:form>

</center>
<div id='loadingmessage' style='display:none'>
       <img src='/HISRegistration/hisglobal/images/ajax-loader.gif'/>
</div>
</body>
</html>