<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<!-- Created By 	: s.singaravelan
 	 Date			: 26-Feb-2014 		-->

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

<script language="JavaScript" src="../hisglobal/masterutil/js/master.js"></script>
<script language="JavaScript" src="./../registration/masters/js/registration.js"></script>
<script language="JavaScript" type="text/javascript" src="./../hisglobal/masterutil/js/jquery/jquery-2.0.3.min.js"></script>
<script type="text/javascript" src="./../hisglobal/masterutil/js/jquery/jquery.easyui.js"></script>
<script type="text/javascript" src="./../hisglobal/masterutil/js/jquery/jquery-ui.js"></script>
<script type="text/javascript" src="./../hisglobal/masterutil/js/jquery/jqueryExtValidation.js"></script>
<script>

/* function saveUOM()
{
	if(validateIt())
	{
		document.forms[0].action="saveUOMMst.action";
		document.forms[0].submit();			
	}
}

function updateUOM()
{
	if(validateIt())
	{
		document.forms[0].action="updateUOMMst.action";
		document.forms[0].submit();			
	}
}

function cancelUOM()
{
		document.forms[0].action="cancelUOMMst.action";
		document.forms[0].submit();			
}


function clearUOM()
{
	document.getElementsByName("uomModel.strUOMName")[0].value="";
	document.getElementsByName("uomModel.strUOMShortName")[0].value="";
}

function validateIt()
{
	var isValid=true;
	if(document.getElementsByName("uomModel.strUOMName")[0].value==""){
		alert("Please Enter Unit of Measurement Name..!");document.getElementsByName("uomModel.strUOMName")[0].focus();
		isValid=false;		
	}
	else if(document.getElementsByName("uomModel.strUOMShortName")[0].value==""){
		alert("Please Enter Unit of Measurement Short Name..!");document.getElementsByName("uomModel.strUOMShortName")[0].focus();
		isValid=false;		
	}
	return isValid;
	
}*/

</script>
</head>
<body onload="">
<s:form action="UOMMst">
<div class="wrapper rounded">
<div class="div-table">
			<div class="div-table-row ">
				<div class="div-table-col title width100 ">
						<s:text name="uom.title"/>&nbsp;<s:text name="global.master"/>						
						<s:if test="flagAddMod=='ADD'">>><s:text name="global.add"/></s:if>
						<s:else >>><s:text name="global.modify"/></s:else >
				</div>
			</div>
			<div class="div-table-row ">
				<div class="div-table-col label  width50"><font color="#FF0000">*</font>&nbsp;<s:text name="uom.title"/>&nbsp;<s:text name="global.name"/></div>	
				<div class="div-table-col column  width50"><s:textfield key="strUOMName" name="uomModel.strUOMName" maxlength="60" size="30" cssStyle="width:197" ></s:textfield>
				<s:hidden key="strOldUOMName" name="uomModel.strOldUOMName" value="%{uomModel.strUOMName}"></s:hidden>
				</div>
			</div>
			<div class="div-table-row ">
				<div class="div-table-col label  width50"><font color="#FF0000">*</font>&nbsp;<s:text name="uom.title"/>&nbsp;<s:text name="global.short"/>&nbsp;<s:text name="global.name"/></div>	
				<div class="div-table-col column  width50"><s:textfield key="strUOMShortName" name="uomModel.strUOMShortName" maxlength="20" size="30"  cssStyle="width:197" ></s:textfield>
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
    		<a href="#" class="button" id="submitId"><span class="save"><s:text name="save"/></span></a>
			<a href="#" class="button" id="cancelId"><span class="cancel"><s:text name="cancel"/></span></a>
			<a href="#" class="button" id="clearId" ><span class="clear"><s:text name="clear"/></span></a>
			</s:if>
			<s:else >
			<a href="#" class="button" id="modifyId"><span class="save"><s:text name="save"/></span></a>
			<a href="#" class="button" id="cancelId"><span class="cancel"><s:text name="cancel"/></span></a>
			<a href="#" class="button" id="reloadId" ><span class="clear"><s:text name="clear"/></span></a>
			</s:else>
			
	</div>
</div>
</div>
	
	<s:hidden name="flagAddMod" value="%{flagAddMod}"></s:hidden>
	<s:hidden key="strUOMId" name="uomModel.strUOMId" value="%{uomModel.strUOMId}"></s:hidden>
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
<h4><font color="#FF0000"><s:property value="%{uomModel.StrWarning}"/></font></h4>
<%-- <h4><font color="#FF0000"><s:property value="%{uomModel.strErrorMsg}"/></font></h4> --%>
<script type="text/javascript" >

$('[name="uomModel.strUOMName"]') .validatebox({required: true,	validType: 'alphanumericWithSomeSymobols'});
$('[name="uomModel.strUOMShortName"]') .validatebox({required: true,	validType: 'alphanumericWithSomeSymobols'});
$('#submitId').click(function(e){
	$("#UOMMst").attr('action',"/HISRegistration/registration/saveUOMMst.action");
	if($('#UOMMst').form('validate'))
		$('#UOMMst').submit();			
			});

$('#cancelId').click(function(e){	
	$("#UOMMst").attr('action',"/HISRegistration/registration/cancelUOMMst.action");	
		$('#UOMMst').submit();			
});

$('#clearId').click(function(e){
	$('[name="uomModel.strUOMName"]').val('');
	$('[name="uomModel.strUOMShortName"]').val('');

});
$('#modifyId').click(function(e){	
	$("#UOMMst").attr('action',"/HISRegistration/registration/updateUOMMst.action");	
	if($('#UOMMst').form('validate'))
		$('#UOMMst').submit();			
});
$('#reloadId').click(function(e){
	location.reload("true");
});

</script>
</body>
</html>