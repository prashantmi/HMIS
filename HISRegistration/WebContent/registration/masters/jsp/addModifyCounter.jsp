<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<!-- Created By 	: s.singaravelan
 	 Date			: 18-Feb-2014 		-->

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
<!-- <link rel="stylesheet" href="/HISRegistration/struts/xhtml/styles.css" type="text/css"/> -->

<script language="JavaScript" src="../hisglobal/masterutil/js/master.js"></script>
<script language="JavaScript" src="./../registration/masters/js/registration.js"></script>
<script language="JavaScript" type="text/javascript" src="./../hisglobal/masterutil/js/jquery/jquery-2.0.3.min.js"></script>
<script type="text/javascript" src="./../hisglobal/masterutil/js/jquery/jquery.easyui.js"></script>
<script type="text/javascript" src="./../hisglobal/masterutil/js/jquery/jquery-ui.js"></script>
<script type="text/javascript" src="./../hisglobal/masterutil/js/jquery/jqueryExtValidation.js"></script>
<script>

/*function saveCounter()
{
	if(validateIt())
	{
		document.forms[0].action="saveCounterMst.action";
		document.forms[0].submit();			
	}
}

function updateCounter()
{
	if(validateIt())
	{
		document.forms[0].action="updateCounterMst.action";
		document.forms[0].submit();			
	}
}

function cancelCounter()
{
		document.forms[0].action="cancelCounterMst.action";
		document.forms[0].submit();			
}


function clearCounter()
{
	document.getElementsByName("counterModel.strCounterName")[0].value="";
	document.getElementsByName("counterModel.strCounterType")[0].value="-1";
	document.getElementsByName("counterModel.strLocationCode")[0].value="-1";
	document.getElementsByName("counterModel.strIPAddress")[0].value="";
	document.getElementsByName("counterModel.strRemarks")[0].value="";
}

function validateIt()
{
	var isValid=true;
	if(document.getElementsByName("counterModel.strCounterName")[0].value==""){
		alert("Please Enter Counter Name..!");document.getElementsByName("counterModel.strCounterName")[0].focus();
		isValid=false;		
	}
	else if(document.getElementsByName("counterModel.strCounterType")[0].value=="-1"){
		alert("Please Select Counter Type..!");document.getElementsByName("counterModel.strCounterType")[0].focus();
		isValid=false;		
	}
	else if(document.getElementsByName("counterModel.strLocationCode")[0].value=="-1"){
		alert("Please Select Location..!");document.getElementsByName("counterModel.strLocationCode")[0].focus();
		isValid=false;		
	}
	else if(document.getElementsByName("counterModel.strIPAddress")[0].value==""){
		alert("Please Enter IP Address..!");document.getElementsByName("counterModel.strIPAddress")[0].focus();
		isValid=false;		
	}
	return isValid;
	
}*/

</script>
</head>
<body onload="">
<s:form action="CounterMst">
<div class="wrapper rounded">
<div class="div-table">
			<div class="div-table-row ">
				<div class="div-table-col title width100 ">
						<s:text name="global.counter"/>&nbsp;<s:text name="global.master"/>						
						<s:if test="flagAddMod=='ADD'">>><s:text name="global.add"/></s:if>
						<s:else >>><s:text name="global.modify"/></s:else >
				</div>
			</div>
			<div class="div-table-row ">
				<div class="div-table-col label  width50"><font color="#FF0000">*</font>&nbsp;<s:text name="global.counter"/>&nbsp;<s:text name="global.name"/></div>	
				<div class="div-table-col column  width50"><s:textfield key="strCounterName" name="counterModel.strCounterName" maxlength="35"  cssStyle="width:197px" > </s:textfield>
				<s:hidden key="strOldCounterName" name="counterModel.strOldCounterName" value="%{counterModel.strCounterName}"></s:hidden>
				</div>
			</div>
			<div class="div-table-row ">
				<div class="div-table-col label  width50"><font color="#FF0000">*</font>&nbsp;<s:text name="global.counter"/>&nbsp;<s:text name="global.type"/></div>
				<div class="div-table-col column  width50"><s:select key="strCounterType" value="%{counterModel.strCounterType}" headerKey="-1" headerValue="Select Value" 
				 				list="%{#session.counterTypeList}" listKey="value" listValue="label" name="counterModel.strCounterType"  cssStyle="width:198px" ></s:select>
				</div>
			</div>
			<div class="div-table-row ">
				<div class="div-table-col label  width50"><font color="#FF0000">*</font>&nbsp;<s:text name="global.location"/></div>
				<div class="div-table-col column  width50"><s:select key="strLocationCode" value="%{counterModel.strLocationCode}" headerKey="-1" headerValue="Select Value" 
				 				list="%{#session.locationList}" listKey="value" listValue="label" name="counterModel.strLocationCode"  cssStyle="width:198px" ></s:select>
				</div>
			</div>
			<div class="div-table-row ">
				<div class="div-table-col label  width50"><font color="#FF0000">*</font>&nbsp;<s:text name="global.ipaddress"/>&nbsp;<s:text name="global.name"/></div>	
				<div class="div-table-col column  width50"><s:textfield key="strIPAddress" name="counterModel.strIPAddress" maxlength="15"  cssStyle="width:197px"  > </s:textfield>
				</div>
			</div>
			<div class="div-table-row ">
				<div class="div-table-col label  width50"><s:text name="global.remarks"/></div>		
				<div class="div-table-col column  width50"><s:textfield key="strRemarks" name="counterModel.strRemarks" maxlength="50"  cssStyle="width:197px" > </s:textfield>
				</div>
			</div>			
			
</div>

<div class="div-table-button">
<div class="div-table-row">
				<div class="div-table-col footerBar"></div>
				</div>
				<div class="div-table-row">
					<div class="div-table-col emptyBar"></div>
				</div>
			<div class="div-table-row" align="center">
			<s:if test="flagAddMod=='ADD'">
    		<a href="#" class="button" id="submitId" ><span class="save"><s:text name="save"/></span></a>
			<a href="#" class="button" id="cancelId" ><span class="cancel"><s:text name="cancel"/></span></a>
			<a href="#" class="button" id="clearId" ><span class="clear"><s:text name="clear"/></span></a>
			</s:if>
			<s:else >
			<a href="#" class="button" id="modifyId" ><span class="save"><s:text name="save"/></span></a>
			<a href="#" class="button" id="cancelId" ><span class="cancel"><s:text name="cancel"/></span></a>
			<a href="#" class="button" id="reloadId" ><span class="clear"><s:text name="clear"/></span></a>
			</s:else>
			
	</div>
</div>
</div>
	
	<s:hidden name="flagAddMod" value="%{flagAddMod}"></s:hidden>
	<s:hidden key="strCounterCode" name="counterModel.strCounterCode" value="%{counterModel.strCounterCode}"></s:hidden>
	<cmbPers:cmbPers></cmbPers:cmbPers>
<s:token></s:token>
</s:form>
<div class="div-table">
<div class="div-table-row   fontError">
<s:fielderror ></s:fielderror>
</div>
</div>
<s:actionerror/>

<div  class="div-table-col alignLeft fontNormalMessage" style="width: 100%"><s:property value="message" /></div>
<h4><font color="#FF0000"><s:property value="%{counterModel.StrWarning}"/></font></h4>
<%-- <h4><font color="#FF0000"><s:property value="%{counterModel.strErrorMsg}"/></font></h4> --%>

<script type="text/javascript" >
$('[name="counterModel.strCounterName"]') .validatebox({required: true,	validType: 'alphaNumeric'});
$('[name="counterModel.strCounterType"]').validatebox({
	required: true,validType: ['selectCombo[-1]']
	});
$('[name="counterModel.strLocationCode"]').validatebox({
	required: true,validType: ['selectCombo[-1]']
	});
$('[name="counterModel.strIPAddress"]') .validatebox({required: true,	validType: 'ipAddress'});
$('[name="counterModel.strRemarks"]') .validatebox({required: true,	validType: 'alphaSpecialChar1'});
$('#submitId').click(function(e){
	$("#CounterMst").attr('action',"/HISRegistration/registration/saveCounterMst.action");
	if($('#CounterMst').form('validate'))
		{
		sortandEncodebase64($("#CounterMst"));
		$('#CounterMst').submit();	
		}
			});
$('#cancelId').click(function(e){	
	$("#CounterMst").attr('action',"/HISRegistration/registration/cancelCounterMst.action");	
		$('#CounterMst').submit();			
});

$('#clearId').click(function(e){
	$('[name="counterModel.strCounterName"]').val('');
	$('[name="counterModel.strCounterType"]').val('-1');
	$('[name="counterModel.strLocationCode"]').val('-1');
	$('[name="counterModel.strIPAddress"]').val('');
	$('[name="counterModel.strRemarks"]').val('');
});

$('#modifyId').click(function(e){	
	$("#CounterMst").attr('action',"/HISRegistration/registration/updateCounterMst.action");	
	if($('#CounterMst').form('validate'))
		{
		sortandEncodebase64($("#CounterMst"));
		$('#CounterMst').submit();		
		}
			
});

$('#reloadId').click(function(e){
	location.reload("true");
});




</script>
</body>
</html>