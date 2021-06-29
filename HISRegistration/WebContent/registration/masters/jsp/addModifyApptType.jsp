<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="/WEB-INF/comboPersistence.tld" prefix="cmbPers"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<META Http-Equiv="Cache-Control" Content="no-cache" />
<META Http-Equiv="Cache-Control" Content="no-store" />
<META Http-Equiv="Pragma" Content="no-cache" />
<META Http-Equiv="Expires" Content="0" />

<%
	response.setHeader("Cache-Control", "no-cache");
	response.setHeader("Pragma", "no-cache");
	response.setDateHeader("Expires", 0);
	response.setHeader("Cache-Control", "no-store");
%>
<link href="./../hisglobal/css/buttons.css" rel="stylesheet" type="text/css">
<link href="./../hisglobal/css/layout.css" rel="stylesheet" type="text/css">
<link href="./../hisglobal/css/jquery-ui.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="./../hisglobal/css/jqueryExtValidationToolTip.css">

<script language="JavaScript" type="text/javascript" src="./../hisglobal/masterutil/js/jquery/jquery-2.0.3.min.js"></script>
<script type="text/javascript" src="./../hisglobal/masterutil/js/jquery/jquery.easyui.js"></script>
<script type="text/javascript" src="./../hisglobal/masterutil/js/jquery/jquery-ui.js"></script>
<script type="text/javascript" src="./../hisglobal/masterutil/js/jquery/jqueryExtValidation.js"></script>
<script language="JavaScript" src="../hisglobal/masterutil/js/master.js"></script>
<script language="JavaScript" src="./../registration/masters/js/registration.js"></script>

<script language="JavaScript" type="text/javascript" src="/HISRegistration/hisglobal/masterutil/js/jquery/security.js" ></script>

</head>
<body>

<s:form action="AppointmentType">
<div class="wrapper rounded">
<div class="div-table">
			<div class="div-table-row ">
				<div class="div-table-col title width100 ">
						<s:text name="Appointment"/>&nbsp;<s:text name="global.type"/>&nbsp;<s:text name="global.detail"/>
						<s:if test="flagAddMod=='ADD'">>><s:text name="global.add"/></s:if>
						<s:else >>><s:text name="global.modify"/></s:else >
				</div>
			</div>
			<div class="div-table-row ">
				<div class="div-table-col label  width50"><font color="#FF0000">*</font>&nbsp;<s:text name="Appointment"/>&nbsp;<s:text name="global.type"/>&nbsp;<s:text name="global.name"/></div>
				<div class="div-table-col column  width45">
					<s:textfield key="strApptTypeName" value="%{apptTypeModel.strApptTypeName}" name="apptTypeModel.strApptTypeName" maxlength="20" size="30" ></s:textfield>
					
				</div>
			</div>
			<div class="div-table-row ">
				<div class="div-table-col label  width50"><font color="#FF0000">*</font>&nbsp;<s:text name="Is"/>&nbsp;<s:text name="global.default"/></div>
				<div class="div-table-col column  width45">
					<s:select key="strApptIsDefault" name="apptTypeModel.strApptIsDefault" value="%{apptTypeModel.strApptIsDefault}" headerKey="-1" headerValue="Select Value" list="#{'1':'Yes','0':'No'}" >
				 	</s:select>
				 </div>
			</div>			
						
</div>

<div class="div-table-button">
<div class="div-table-row">
					<div class="div-table-col footerBar"></div>
					<div class="div-table-col emptyBar"></div>
				</div>
	<div class="div-table-row" align="center">
			<s:if test="flagAddMod=='ADD'">
    		<a href="#" class="button" id="submitId"><span class="save"><s:text name="save"/></span></a>
			<a href="#" class="button" id="cancelId"><span class="cancel"><s:text name="cancel"/></span></a>
			<a href="#" class="button" id="clearlId"><span class="clear"><s:text name="clear"/></span></a>				
			</s:if>
			<s:else >
			<a href="#" class="button" id="updateId"><span class="save"><s:text name="save"/></span></a>
			<a href="#" class="button" id="cancelId"><span class="cancel"><s:text name="cancel"/></span></a>	
			<a href="#" class="button" id="reloadlId"><span class="clear"><s:text name="clear"/></span></a>			
			</s:else>
	</div>
</div>
	
	<s:hidden name="flagAddMod" value="%{flagAddMod}"></s:hidden>
	<s:hidden key="strApptTypeId" name="apptTypeModel.strApptTypeId" value="%{apptTypeModel.strApptTypeId}"></s:hidden>
	
</div>
<cmbPers:cmbPers></cmbPers:cmbPers>
<s:token></s:token>
</s:form>
<h4><font color="#FF0000"><s:property value="%{apptTypeModel.StrWarning}"/></font></h4>
<%-- <h4><font color="#FF0000"><s:property value="%{apptTypeModel.strErrorMsg}"/></font></h4> --%>
<h3><s:property value="message"/></h3>
<s:actionerror/>
<div class="div-table">
<div class="div-table-row   fontError">
<s:fielderror ></s:fielderror>
</div>
</div>
<script type="text/javascript">
$('[name="apptTypeModel.strApptTypeName"]').validatebox({required: true, validType: 'alphaNumericWithSpaces'});
$('[name="apptTypeModel.strApptIsDefault"]').validatebox({
	required: true,
	validType: ['selectCombo[-1]']

});

$('#submitId').click(function(e){
	$("#AppointmentType").attr('action',"/HISRegistration/registration/saveAppointmentType.action");
	if($('#AppointmentType').form('validate'))
		{
		sortandEncodebase64($("#AppointmentType"));
		$('#AppointmentType').submit();
		}
			});

$('#cancelId').click(function(e){	
	$("#AppointmentType").attr('action',"/HISRegistration/registration/cancelAppointmentType.action");
		$('#AppointmentType').submit();			
});		

$('#clearlId').click(function(e){
	$('[name="apptTypeModel.strApptTypeName"]').val('');
	$('[name="apptTypeModel.strApptIsDefault"]').val('-1');
});


$('#updateId').click(function(e){	
	$("#AppointmentType").attr('action',"/HISRegistration/registration/updateAppointmentType.action");	
	if($('#AppointmentType').form('validate'))
		{
		sortandEncodebase64($("#AppointmentType"));
		$('#AppointmentType').submit();			
		}
});

$('#reloadlId').click(function(e){
	isappointmentdefault.reload(true);
});

</script>
</body>
</html>