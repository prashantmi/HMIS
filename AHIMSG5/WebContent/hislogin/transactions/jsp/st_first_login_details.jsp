<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="/struts-tags" prefix="s" %>

<%@page import="hissso.config.HISSSOServerConfig"%>
<%@page import="hislogin.config.HISLoginConfig"%>
<%@page import="hisglobal.config.HISConfig"%>

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

<link href="/HIS/hisglobal/css/buttons.css" rel="stylesheet" type="text/css">
<link href="/HIS/hisglobal/css/layout.css" rel="stylesheet" type="text/css">
<link href="/HIS/hisglobal/css/jquery-ui.css" rel="stylesheet" type="text/css">
<link href="/HIS/hisglobal/css/jqueryExtValidationToolTip.css" rel="stylesheet" type="text/css">
<link href="/HIS/hisglobal/css/easyui.css" rel="stylesheet" type="text/css">

<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/jquery-2.0.3.min.js"></script>
<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/jquery.easyui.js"></script>
<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/jquery-ui.js"></script>
<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/jqueryExtValidation.js"></script>
<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/hashFunctions.js"></script>
<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/commonFunctions.js"></script>

</head>
<body onload="pageOnLoad()">
<center>

<div class="wrapper rounded">

<s:form action="LgnFtr">

<s:if test="%{varStatus != 'DONE'}">
	<div class="div-table" >
		<div class="div-table-row " >
			<div class="div-table-col title width100 " >
					User Login Details
			</div>
		</div>
		<div class="div-table-row " >
			<div class="div-table-col subtitle width100 " >
					Hello !!! <s:property value="varUsrName" />, Please Change your Password Details On First Login
			</div>
		</div>
		<div class="div-table-row ">
			<div class="div-table-col width40 label" >
				<font color="red">*</font>Hint Question 
			</div>
			<div class="div-table-col width60 control" >
				<s:if test="%{#session.keyQuestionList != null}">
					<s:select name="varQuestionId" tabindex="1" cssStyle="width:150px" headerKey="-1" headerValue="Select Question" list="#session.keyQuestionList" listKey="value" listValue="label" />
				</s:if>
			</div>
		</div>
		<div class="div-table-row ">
			<div class="div-table-col width40 label" >
				<font color="red">*</font>Answer 
			</div>
			<div class="div-table-col width60 control" >
				<input type="password" name="varHintAnswer" tabindex="1" maxlength="10" onkeydown="if(event.keyCode==13) finalSubmit(event)" autocomplete="off">
			</div>
		</div>
		<div class="div-table-row ">
			<div class="div-table-col width40 label" >
				<font color="red">*</font>New Password 
			</div>
			<div class="div-table-col width60 control" >
				<input type="password" name="varPassword" tabindex="1" value="" maxlength="15" id="varPassword" autocomplete="off">
			</div>
		</div>
		<div class="div-table-row ">
			<div class="div-table-col width40 label" >
				<font color="red">*</font>Confirm Password 
			</div>
			<div class="div-table-col width60 control" >
				<input type="password" name="varConfirmPassword" tabindex="1" value="" maxlength="15" id="varConfirmPassword" autocomplete="off">
			</div>
		</div>
		<div class="div-table-row ">
		<div class="div-table-col width40 label" >
			<%if(HISConfig.CAPTCHA_IMPLEMENTATION.equals("ON")) {%>
	 <img id="captchaImg"  src="/AHIMSG5/hislogin/captchaLgnFtr" style="width:30%" alt="Captcha Image" height="30">
	
	</div>
	<div class="div-table-col width60 control" >
	<input type="text" tabindex="1" class="captcha-Text" style="width:34%"  maxlength="6" name="captchaResponse" placeholder="Enter Captcha" autocomplete="off"/>
	 <img src="/HIS/hisglobal/images/buttons/refresh.png" alt="Reload"  onclick="document.forms[0].captchaImg.src='/AHIMSG5/hislogin/captchaLgnFtr'+'?id='+Math.random();" style="cursor:pointer"/>
	 </div>
	  <%}%>
	  </div>
	  <div class="div-table-row ">
			<div class="div-table-col width100 label" >
				<font color='red'>
					<br>
		     		The Password is case sensitive. 
		     	</font>
		     </div>
		</div>
	</div>
	
	<div class="div-table-button">
		<div class="div-table-row footerBar">
			<div class="div-table-col"> </div>
		</div>
		<div class="div-table-row emptyBar">
			<div class="div-table-col"> </div>
		</div>
		<div class="div-table-row" align="center">
			<a href="#" class="button" tabindex="1" id="idSave"><span class="save">Save</span></a>
			<a href="#" class="button" tabindex="1" id="idClear"><span class="clear">Clear</span></a>
			<s:if test="%{varStatus == 'DONE'}">
				<a href="#" class="button" tabindex="1" id="idCancel"><span class="cancel">Cancel</span></a>
			</s:if>
		</div>
	</div>
	<s:hidden name="varUserName"></s:hidden>
	<s:hidden name="varUsrName"></s:hidden>
</s:if>

	<s:hidden name="varStatus"></s:hidden>

<div id="divElementErrorsId"><s:actionerror/></div>
<s:token/>
</s:form>

<script type="text/javascript">
var passwordStrength = "<%=HISLoginConfig.PASSWORD_STRENGTH%>";

function setPasswordStrength()
{
	$('[name="varPassword"]').validatebox({
		required: true,
		validType : ['alphaSpecialChar','minLength[8]', 'maxLength[15]', 'passwordStrength['+passwordStrength+']']
	
	});
}

function pageOnLoad()
{
	if(document.getElementsByName('varStatus')[0].value=="DONE")
	{
		window.setTimeout(function(){parent.closeModal()},2000);
	}

	if(document.getElementsByName('varQuestionId')[0])	document.getElementsByName('varQuestionId')[0].focus();
	setPasswordStrength();
}


var firstLoginUserDetail = {
		clearForm : function()
		{
			document.getElementsByName("varQuestionId")[0].value = "-1";
			document.getElementsByName("varHintAnswer")[0].value = "";
			document.getElementsByName("varPassword")[0].value = "";
			document.getElementsByName("varConfirmPassword")[0].value = "";
		},
		submitOnSave : function()
		{
			if(!firstLoginUserDetail.secureAnswer() || !firstLoginUserDetail.securePassword())
			{
				document.getElementById("divElementErrorsId").innerHTML = "Faced Some Unknown Problem. Please try Again!";
				document.getElementsByName("varQuestionId")[0].value = "-1";
				document.getElementsByName("varHintAnswer")[0].value = "";
				document.getElementsByName("varPassword")[0].value = "";
				document.getElementsByName("varConfirmPassword")[0].value = "";
				return;
			}
			else
			{
				//For Submission
			  	submitForm("saveFirstLoginLgnFtr");
			}
		},
		secureAnswer : function()
		{
			var hashValue = "";
			var objPassHash = new jsSHA(document.getElementsByName("varHintAnswer")[0].value+document.getElementsByName("varQuestionId")[0].value, "ASCII");
			try 
			{
				hashValue = objPassHash.getHash("SHA-1", "HEX");
			} 
			catch(e)
			{
				return false;
			}

			/*objPassHash = new jsSHA(hashValue + sessionToken, "ASCII");
			try
			{
				hashValue = objPassHash.getHash("SHA-1", "HEX");
			}
			catch(e)
			{
				return false;
			}*/

			document.getElementsByName("varHintAnswer")[0].value = hashValue;
			return true;
		},
		securePassword : function()
		{
			var hashValue = "";
			var objPassHash = new jsSHA(document.getElementsByName("varPassword")[0].value+document.getElementsByName("varUserName")[0].value, "ASCII");
			try 
			{
				hashValue = objPassHash.getHash("SHA-1", "HEX");
			} 
			catch(e)
			{
				return false;
			}

			/*objPassHash = new jsSHA(hashValue + sessionToken, "ASCII");
			try
			{
				hashValue = objPassHash.getHash("SHA-1", "HEX");
			}
			catch(e)
			{
				return false;
			}*/

			document.getElementsByName("varPassword")[0].value = hashValue;
			document.getElementsByName("varConfirmPassword")[0].value = hashValue;
			return true;
		}


	};

	$('[name="varQuestionId"]').validatebox({
		required: true,
		validType : [ 'selectCombo[-1]' ]
	});
	$('[name="varHintAnswer"]').validatebox({
		required: true,
		validType : [ 'alphaSpecialChar', 'maxLength[10]' ]
	});
	$('[name="varPassword"]').validatebox({
		required: true,
		validType : [ 'alphaSpecialChar', 'minLength[8]', 'maxLength[15]' ]
	});
	$('[name="varConfirmPassword"]').validatebox({
		required: true,
		validType : [ 'alphaSpecialChar', 'minLength[8]', 'maxLength[15]', 'equalTo["#varPassword","New Password","Confirm Password"]' ]
	});
	$('[name="captchaResponse"]').validatebox({
		required: true
		
	});
	// On Click of Clear
	$('#idClear').click(function(e){
		firstLoginUserDetail.clearForm();
	});

	// On Click of Cancel
	$('#idCancel').click(function(e){
		parent.closeModal();
		e.preventDefault();
	});

	// On Click of Save
	$('#idSave').click(function(e){
		if($('#LgnFtr').form('validate')){
			firstLoginUserDetail.submitOnSave();
		}else{
			return false;
		}
		e.preventDefault();
	});


</script>

</div>
</center>
</body>
</html>