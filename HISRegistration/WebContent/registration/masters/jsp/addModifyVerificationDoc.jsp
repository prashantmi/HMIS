<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<!-- Created By 	: s.singaravelan
 	 Date			: 20-Dec-2013 		-->

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

<script language="JavaScript" type="text/javascript" src="./../hisglobal/masterutil/js/jquery/jquery-2.0.3.min.js"></script>
<script type="text/javascript" src="./../hisglobal/masterutil/js/jquery/jquery.easyui.js"></script>
<script type="text/javascript" src="./../hisglobal/masterutil/js/jquery/jquery-ui.js"></script>



<script type="text/javascript" src="./../hisglobal/masterutil/js/jquery/jqueryExtValidation.js"></script>
<script language="JavaScript" type="text/javascript" src="/HISRegistration/hisglobal/masterutil/js/jquery/security.js" ></script>

<title>Verification Document</title>


</head>


<body>


<s:form  action="VerificationDoc" namespace="/">

<div class="wrapper rounded">
<div class="div-table">
			<div class="div-table-row ">
				<div class="div-table-col title width100 ">
				<s:text name="global.verificationDoc"/>&nbsp;<s:text name="global.detail"/>
				</div>
			</div>
			<div class="div-table-row ">
				<div class="div-table-col label  width50"><font color="#FF0000">*</font>&nbsp;<s:text name="global.document"/>&nbsp;<s:text name="global.name"/></div>	
				<div class="div-table-col column  width45"><s:textfield key="strDocName" id="strDocNameId" name="veriDocModel.strDocName" maxlength="30" size="28" > </s:textfield>
				</div>
			</div>
			<div class="div-table-row ">
				<div class="div-table-col label  width50"><font color="#FF0000">*</font>&nbsp;<s:text name="verificationDoc.alternate"/></div>
				<div class="div-table-col column  width45"><s:select key="strIsAlternate" headerKey="-1" headerValue="Select Value" 
				 				 list="#{'1':'Yes','0':'No'}"  name="veriDocModel.strIsAlternate" cssStyle="width:186px"> </s:select>
				</div>
			</div>
			<div class="div-table-row ">
				<div class="div-table-col label  width50"><font color="#FF0000">*</font>&nbsp;<s:text name="verificationDoc.idsize"/></div>
				<div class="div-table-col column  width45"><s:textfield key="strIdSize" name="veriDocModel.strIdSize" maxlength="2" cssStyle="width:184px" > </s:textfield>
				</div>
			</div>
			<div class="div-table-row ">
				<div class="div-table-col label  width50"><font color="#FF0000">*</font>&nbsp;<s:text name="verificationDoc.validationtype"/></div>
				<div class="div-table-col column  width45"><s:select key="strIdValidationType" headerKey="-1" headerValue="Select Value" 
				 				 list="#{'0':'Any Data','1':'Numeric','2':'Alphanumeric','3':'Characters'}"  name="veriDocModel.strIdValidationType" cssStyle="width:186px"> </s:select>
				</div>
			</div>
			<div class="div-table-row ">
				<div class="div-table-col label  width50">&nbsp;<s:text name="verificationDoc.isauth"/></div>
				<div class="div-table-col column  width45"><s:textfield key="strIsAuth" id="strIsAuthID" name="veriDocModel.strIsAuth" maxlength="50" size="28" > </s:textfield>
				</div>
			</div>
</div>
<div class="div-table">
			<div class="div-table-row ">
				<div class="div-table-col  width100 height20 ">
				</div>
			</div>
			
		
</div>

<div class="div-table-button">
<div class="div-table-row">
				<div class="div-table-col footerBar">
				</div>
		</div>
		<div class="div-table-row">
				<div class="div-table-col emptyBar">
				</div>
		</div>
	<div class="div-table-row" align="center">
		<s:if test="flagAddMod=='ADD'">
    		<a href="#" class="button" id="submitId"><span class="save"><s:text name="save"/></span></a>
			<a href="#" class="button" id="cancelId"><span class="cancel"><s:text name="cancel"/></span></a>
			<a href="#" class="button" id="clearId"><span class="clear"><s:text name="clear"/></span></a>
		</s:if>
		
	</div>
</div>
	
	<s:hidden name="flagAddMod" value="%{flagAddMod}"></s:hidden>
	<s:hidden name="veriDocModel.strDocCode" value="%{veriDocModel.strDocCode}"></s:hidden>
</div>
<cmbPers:cmbPers></cmbPers:cmbPers>
<s:token></s:token>
</s:form>
 <s:actionerror />
 <div class="div-table">
<div class="div-table-row   fontError">
<s:fielderror ></s:fielderror>
</div>
</div>
 <div  class="div-table-col alignLeft fontError" style="width: 100%"><s:property value="%{veriDocModel.StrWarning}"/></div>
 <div  class="div-table-col alignLeft fontNormalMessage" style="width: 100%"><s:set name="messageVar" value="message"></s:set></div>


<h3><s:property value="message"/></h3>
<%-- <h4><font color="#FF0000"><s:property value="%{veriDocModel.strErrorMsg}"/></font></h4> --%>


<script type="text/javascript" >
//$("#strDocNameId").validatebox({required: true,	validType: 'alpha'});
$('[name="veriDocModel.strDocName"]').validatebox({required: true,	validType: 'alphaWithSpace'});
$('[name="veriDocModel.strIsAlternate"]').validatebox({
	required: true,
	validType: ['selectCombo[-1]']
});
$('[name="veriDocModel.strIdSize"]').validatebox({required: true,	validType: 'numberRangeOneToEighteen'});
$('[name="veriDocModel.strIdValidationType"]').validatebox({
	required: true,
	validType: ['selectCombo[-1]']
}); 
$('[name="veriDocModel.strIsAuth"]').validatebox({validType: 'alphaNumSpecialChar'});

$('#submitId').click(function(e)
{
		$("#VerificationDoc").attr('action',"/HISRegistration/registration/saveVerificationDoc.action");
		if($('#VerificationDoc').form('validate'))
				{
			      sortandEncodebase64($("#VerificationDoc"));
			      $('#VerificationDoc').submit();
				}
}); 
	
$('#cancelId').click(function(e)
{
	$("#VerificationDoc").attr('action',"/HISRegistration/registration/cancelVerificationDoc.action");
	$('#VerificationDoc').submit();
}); 

$('#clearId').click(function(e){	
	$('[name="veriDocModel.strDocName"]').val('');	
	$('[name="veriDocModel.strIdSize"]').val('');
	$('[name="veriDocModel.strIsAlternate"]').val('-1');
	$('[name="veriDocModel.strIdValidationType"]').val('-1');
	$('[name="veriDocModel.strIsAuth"]').val('');
});
</script>

</body>
</html>