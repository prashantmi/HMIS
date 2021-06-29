<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/dateTag.tld" prefix="date"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>
<%@ taglib uri="/WEB-INF/comboPersistence.tld" prefix="cmbPers"%>

<%-- 
author 	: Arun V.R
Date of Creation : 13-Jan-11
Process : Engineering Item Sub-Type Master
Module 	: BMED
TL 		: Mr. 
Description :   
 --%>
<html>
<head>
<!-- <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1"> -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
<title>Engineering Item Sub Type Master</title>
<link href="../../hisglobal/css/emms.css" rel="stylesheet"
	type="text/css">
<link href="../../hisglobal/css/control.css" rel="stylesheet"
	type="text/css">
<link href="../../hisglobal/css/calendar-tas.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/popup.css" rel="stylesheet" type="text/css">	

<script language="JavaScript" src="../../hisglobal/masterutil/js/master.js"></script>
<script language="JavaScript" src="../../hisglobal/js/tab.js"></script>
<script language="JavaScript" src="../../hisglobal/js/calendar.js"></script>
<script language="Javascript" src="../../hisglobal/js/datepicker1.js"></script>
<script language="Javascript" src="../../hisglobal/js/validation.js"></script>
<script language="Javascript" src="../../hisglobal/js/newpopup.js"></script>
<script language="Javascript" src="../../hisglobal/js/popup.js"></script>
<script language="Javascript" src="../../bmed/js/engItemSubTypeMst.js"></script>
<script language="JavaScript">

function validate1(){   
     
      var hisValidator = new HISValidator("engineeringItemSubTypeBean");
      
     
      
        hisValidator.addValidation("strEngItemSubTypeName","req","Item Sub Type Name is a Mandatory Field" );
        hisValidator.addValidation("strEngItemSubTypeName","maxlen=100","Item Sub Type Name should not contain more than 100 characters" );
       // hisValidator.addValidation("strParamLength","maxlen=3","Parameter Length should not contain greater than 3 characters" );
        hisValidator.addValidation("strEffectiveFrom","date","Effective Date is a mandatory field");
        //hisValidator.addValidation("strEffectiveFrom", "dtgtet=${itemParameterBean.strCtDate}" , "Effective Date should be Greater than or Equal to Current Date");         
         hisValidator.addValidation("strRemarks","maxlen=100","Remarks should not contain more than 100 characters" );
        
  var retVal = hisValidator.validate(); 
      		hisValidator.clearAllValidations();
          if(retVal){
                       document.forms[0].hmode.value = "SAVE";
                       document.forms[0].submit();
            }else{

           return false;
}	
}

function parentDisable(chkObj){
	
	if(chkObj.checked == true){
		document.getElementById("parentParamDivId").style.display = "block";
	}else{
		document.getElementById("parentParamDivId").style.display = "none";
	}
}


	
	
</script>
</head>

<body class="background">
<html:form name="engineeringItemSubTypeBean"
	action="/masters/EngineeringItemSubTypeMstCNT"
	type="bmed.masters.controller.fb.EngineeringItemSubTypeMstFB" styleClass="formbg">
	<div class="errMsg"><bean:write name="engineeringItemSubTypeBean"
		property="strErr" /></div>
	<div class="warningMsg"><bean:write
		name="engineeringItemSubTypeBean" property="strWarning" /></div>
	<div class="normalMsg"><bean:write
		name="engineeringItemSubTypeBean" property="strMsg" /></div>
	

	<table class="TABLEWIDTH" border="0" align="center" cellpadding="1px"
		cellspacing="1px">
		<tr class="HEADER">
			<td colspan="2">Engineering Item Sub Type Master&gt;&gt;Modify</td>
		</tr>
		<tr>
			<td class="LABEL" width="50%"><font color="red">*</font>Engineering
			Item Type</td>
			<td width="50%" class="CONTROL"><bean:write
				name="engineeringItemSubTypeBean" property="strEnggItemTypeCmb"
				filter="false" /></td>
		</tr>
		<tr>
			<td class="LABEL"><font color="red">*</font> Engineering Item
			Sub Type Name</td>
			<td class="CONTROL"><html:text name="engineeringItemSubTypeBean"
				property="strEngItemSubTypeName"
				onkeypress="return validateData(event,11)" maxlength="100"></html:text>
			</td>
		</tr>



	</table>

	<table class="TABLEWIDTH" border="0" align="center" cellpadding="1px"
		cellspacing="1px">

		<tr>
			<td class="LABEL"><font color="red">*</font> Effective From Date</td>
			<td class="CONTROL"><date:date name="strEffectiveFrom"
				value="${engineeringItemSubTypeBean.strEffectiveFrom}"></date:date></td>
		</tr>
		<tr>
			<td class="LABEL">Remarks</td>
			<td width="50%" class="CONTROL"><html:textarea
				name="engineeringItemSubTypeBean" property="strRemarks" rows="2"
				onkeypress="return validateDataWithSpecialChars(event,9,'.');"></html:textarea>
			</td>
		</tr>
		<tr>
			<td class="LABEL">Record Status</td>
			<td width="50%" class="CONTROL"><html:radio
				name="engineeringItemSubTypeBean" property="strIsValid" value="1">Active</html:radio>
			<html:radio name="engineeringItemSubTypeBean" property="strIsValid"
				value="2">Inactive</html:radio></td>
		</tr>

	</table>
	<table class="TABLEWIDTH" align="center" border="0" cellpadding="1px"
			cellspacing="1px">

			<!--	<table cellspacing="1px" class="TABLEWIDTH" align="center">-->
			<tr class="FOOTER">
				<td></td>
			</tr>
		</table>
		
		<div class="legends"><font size="2" color="red">*</font>
		Mandatory Fields</div>
		
		<div class="control_button">
		<table class="TABLEWIDTH" align="center">
			<tr>
				<td align="center">
				<div><a href="#" class="button" onClick=" return validateUpdate();"><span
					class="save">Save</span></a> <a href="#" class="button"
					onClick="document.forms[0].reset(),clearMsg('MODIFY');"><span class="clear">Clear</span></a>
				<a href="#" class="button" onClick="cancel('LIST');"><span
					class="back">Back</span></a></div>
				</td>
			</tr>
		</table>
		</div>
		
			
	<input type="hidden" name="hmode" value="" />
	<input type="hidden" name="strEnggItemTypeCmb"
		value="${engineeringItemSubTypeBean.combo[0]}" />
	<input type="hidden" name="strEngItemTypeId"
		value="${engineeringItemSubTypeBean.strEngItemTypeId}" />
	<input type="hidden" name="strHospitalCode"
		value="${engineeringItemSubTypeBean.strHospitalCode}" />
	<input type="hidden" name="comboValue"
		value="${engineeringItemSubTypeBean.comboValue}" />

	<input type="hidden" name="strChk"
		value="${engineeringItemSubTypeBean.strChk}" />
	<input type="hidden" name="strEngItemSubTypeName"
		value="${engineeringItemSubTypeBean.strEngItemSubTypeName}" />
	<input type="hidden" name="strRemarks"
		value="${engineeringItemSubTypeBean.strRemarks}" />
	<cmbPers:cmbPers />
</html:form>

</body>
</html>


