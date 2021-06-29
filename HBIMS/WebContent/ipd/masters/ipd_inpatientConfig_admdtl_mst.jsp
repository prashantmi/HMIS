<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<html>

<link href="../css/master.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/calendar-tas.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/buttons.css" rel="stylesheet" type="text/css">

<script language="JavaScript" src="../../hisglobal/masterutil/js/master.js"></script>
<script language="JavaScript" src="../../hisglobal/js/tab.js"></script>
<script language="JavaScript" src="../../hisglobal/js/calendar.js"></script>
<script language="Javascript" src="../../hisglobal/js/validation.js"></script>
<script language="Javascript" src="../../hisglobal/js/multirow.js"></script>
<script language="JavaScript" src="../../hisglobal/js/util.js"></script>
<head><meta charset="utf-8" />
<title>In Patient Configuration/Admission Details</title>

<script type="text/javascript"><!--
	
function setMultiRow()
{
		<logic:equal name="inPatientConfigBean" property="strTotalRows" value="${inPatientConfigBean.strGenMultiRow }">
		</logic:equal>
		var rowLength = ${inPatientConfigBean.strTotalRows}
		document.multirow.rowLength1.value = rowLength;
		document.multirow.rowIndex1.value = rowLength;
		
	<logic:equal name="inPatientConfigBean" property="strTotalRows" value="${inPatientConfigBean.strPrivateMultiRow }">
	</logic:equal>
		rowLength = ${inPatientConfigBean.strTotalRows}
		document.multirow.rowLength2.value = rowLength;
		document.multirow.rowIndex2.value = rowLength;
}
function getWardType()
{
	var hmode = "GETPRIVATEWARDTYPE"; 
	var url = "CNTInPatientConfigMst.cnt?hmode="+hmode;
	ajaxFunction(url,"1");
}
function validate1()
{	
	retVal = false;
	var hisValidator = new HISValidator("inPatientConfigBean");
<!--	if(document.inPatientConfigBean.strGenWardAdmission[0].checked){-->
<!--		hisValidator.addValidation("strGenWardApprover", "dontselect=0","Please Select A General Ward Approving Authority Id");-->
<!--	}-->
	
<!--	if(document.inPatientConfigBean.strPrivateWardAdmission[0].checked){-->
<!--		hisValidator.addValidation("strPrivateWardApprover", "dontselect=0","Please Select A Private Ward Approving Authority Id");-->
<!--	} 	-->
		hisValidator.addValidation("strAdmissionAdviceValidityFrom", "req","Admission Advice Validity from is a Mandatory Field");
		//hisValidator.addValidation("strAdmissionAdviceValidityTo", "req","Admission Advice Validity to is a Mandatory Field");
		hisValidator.addValidation("strModificationTimeValidity", "req","Modification Time Validity  is a Mandatory Field");
		hisValidator.addValidation("strAdmissionReprintCharge","req","Admission Re-Print Charge is a Mandatory Field");
		hisValidator.addValidation("strAdmissionReprintCharge","amount=5,2","Please Enter a Valid Admission Re-Print Charge");
		retVal = hisValidator.validate(); 
	
		if(retVal)
		{
				destroyMultiRow("1");
				destroyMultiRow("2");
				hisValidator.clearAllValidations();
				document.forms[0].selectedTab.value = "ADMISSIONSAVE";
				document.forms[0].submit();
		}
		else
		{
			hisValidator.clearAllValidations();
			return false;
		}
}
function genAdmissionBlock()
{
		if(document.inPatientConfigBean.strGenWardAdmission[0].checked)
			document.getElementById("genWardApproverId").style.display="none";
		else 
			document.getElementById("genWardApproverId").style.display="none";
}
function privateAdmissionBlock()
{
		if(document.inPatientConfigBean.strPrivateWardAdmission[0].checked)
			document.getElementById("privateWardApproverId").style.display="none";
		else 
			document.getElementById("privateWardApproverId").style.display="none";
}		
function showName(obj)
{
			var val = obj.options[obj.selectedIndex].text;
			val = val.split('-')[1];
			if(obj.value != 0)
			{
				document.getElementById("nameDiv").innerHTML = val;
			}
			else
			{
				document.getElementById("nameDiv").innerHTML = "";
			}
}
function cancelFunction()
{
	document.forms[0].selectedTab.value = "INITIALPAGE";
	document.forms[0].submit();		
}
	--></script>

</head>
<div class="normalMsg" id="normalMsg"></div>
<body onLoad="">
<html:form action="/masters/CNTInPatientConfigMst.cnt" method="post" >

<div class="normalMsg"><bean:write name="inPatientConfigBean" property="strMsg" /></div>

<tag:tab tabLabel="In Patient Configuration" selectedTab="SECOND" align="center" width="TABLEWIDTH"></tag:tab> 
<tag:tab tabList="${inPatientConfigBean.lhm}" selectedTab="ipdadmdtls" align="center" width="TABLEWIDTH"></tag:tab>

<table align="center" class="TABLEWIDTH" cellpadding="1px" cellspacing="1px">
	<tr class="HEADER">
		<td colspan="2" height="22">In Patient Configuration/Admission Details</td>			
	<tr>
		<td width="50%" class="LABEL"><font color="red">*</font>Admission Advice Mode</td>
		<td width="50%" class="CONTROL">
		<html:radio name="inPatientConfigBean" property="strAdmissionAdviceMode" value="0">Online(OPD Desk)</html:radio>
		<html:radio name="inPatientConfigBean" property="strAdmissionAdviceMode" value="1">Offline</html:radio>
		</td>
	</tr>		
	<tr>
		<td width="50%" class="LABEL"><font color="red">*</font>Admission Process </td>	
		<td width="50%" class="CONTROL">
		 <html:radio name="inPatientConfigBean" property="strAdmissionOnline" value="1">Online</html:radio>
		 <html:radio name="inPatientConfigBean" property="strAdmissionOnline" value="2">Offline</html:radio>
		</td>
	</tr>	
</table>

<div id="id2" style="display: none">${inPatientConfigBean.strPrivateMultiRow }</div>
	<table align="center" class="TABLEWIDTH" cellpadding="1px" cellspacing="1px">	
	<tr>
		<td class="LABEL" width="50%"><font color="red">*</font>Admission Advice Validity +</td>
		<td class="CONTROL" width="50%">
		<input type="text" 	name="strAdmissionAdviceValidityFrom" value="${inPatientConfigBean.strAdmissionAdviceValidityFrom }"
			class="txtFldMin" maxlength="3" onkeypress="return validateData(event,5);"> (in Days)</td>
	</tr>
	<tr style="display: none;">
		<td class="LABEL" width="50%"><font color="red">*</font>Admission Advice Validity -</td>
		<td class="CONTROL" width="50%">
		<input type="text" name="strAdmissionAdviceValidityTo" value="${inPatientConfigBean.strAdmissionAdviceValidityTo }"
			class="txtFldMin" maxlength="3" onkeypress="return validateData(event,5);"> (in Days)</td>
	</tr>
	<tr>
		<td class="LABEL" width="50%"><font color="red">*</font>Admission Modification Validity</td>
		<td class="CONTROL" width="50%">
		<input type="text" name="strModificationTimeValidity" value="${inPatientConfigBean.strModificationTimeValidity }"
			class="txtFldMin" maxlength="3" onkeypress="return validateData(event,5);"> (in minutes)</td>
	</tr>
	<tr>
		<td class="LABEL" width="50%"><font color="red">*</font>Whether Unit Name Required(Admission/Transfer)</td>
		<td class="CONTROL" width="50%">
			<html:select name="inPatientConfigBean" property="strUnitReq">
			<html:option value="0">No</html:option>
			<html:option value="1">Yes</html:option>
			</html:select>
		</td>
	</tr>
	<tr>
		<td class="LABEL" width="50%"><font color="red">*</font>Whether Room No. Required(Admission/Transfer)</td>
		<td class="CONTROL" width="50%">
			<html:select name="inPatientConfigBean" property="strRoomReq">
			<html:option value="0">No</html:option>
			<html:option value="1">Yes</html:option>
			</html:select>
		</td>
	</tr>
	<%--  
	<tr>
		<td width="50%" class="LABEL"><font color="red">*</font>Admission Charge Taken At Admission Counter</td>
		<td width="50%" class="CONTROL">
			<html:radio name="inPatientConfigBean" property="strAdmissionChargeTakenAtCounter" value="1">Yes</html:radio>
			<html:radio name="inPatientConfigBean" property="strAdmissionChargeTakenAtCounter" value="0">No</html:radio>
		</td>
	</tr>	
	--%>
	<tr>
		<td class="LABEL" width="50%"><!-- <font color="red">*</font> -->New Born Baby Registration Charge</td>
		<td class="CONTROL" width="50%">
		<html:checkbox property="strNewBornBabyRegistrationCharge" name="inPatientConfigBean" value="1"></html:checkbox></td>
	</tr>
	<tr>
		<td class="LABEL" width="50%"><!-- <font color="red">*</font> -->New Born Baby Admission Charge</td>
		<td class="CONTROL" width="50%">
		<html:checkbox property="strNewBornBabyAdmissionCharge" name="inPatientConfigBean" value="1"></html:checkbox></td>
	</tr>
	<tr style="display: none;">
		<td class="LABEL" width="50%"><font color="red">*</font>Advance Amount Taken At Admission Counter during New Born Baby Case</td>
		<td class="CONTROL" width="50%">
		<html:radio name="inPatientConfigBean" property="strAdvanceAmountNewBornBaby" value="1">Yes</html:radio>
		<html:radio name="inPatientConfigBean" property="strAdvanceAmountNewBornBaby" value="0">No</html:radio></td>
	</tr>
	<tr>
		<td class="LABEL" width="50%"><font color="red">*</font>Admission Re-Print Charge(<img src='../../hisglobal/images/INR.png'>)</td>
		<td class="CONTROL" width="50%">
		<input type="text" name="strAdmissionReprintCharge" value="${inPatientConfigBean.strAdmissionReprintCharge }"
			class="txtFldMin" maxlength="6" onkeypress="return validateData(event,7);"></td>
	</tr>
	<tr>
		<td width="50%" class="LABEL"><font color="red">*</font>Virtual Bed Allotment At the Time of Admission</td>
		<td width="50%" class="CONTROL">
			<html:radio name="inPatientConfigBean" property="strBedAllotmentAtAdmission" value="1">Yes</html:radio>
			<html:radio name="inPatientConfigBean" property="strBedAllotmentAtAdmission" value="0">No</html:radio>			
		</td>
	</tr>
	<tr class="FOOTER">
		<td colspan="2"><font size="2" color="red">*</font>Mandatory Fields</td>
	</tr>
</table>

<table border="0" class="TABLEWIDTH" align="center" >
	<tr> 
		<!-- <td align="right">
		<img src="../../hisglobal/images/btn-sv.png" style="cursor: pointer" onClick=" return validate1();" /></td>
		<td align="left">
		<img src="../../hisglobal/images/btn-ccl.png" style="cursor: pointer" onClick="cancelFunc();" /></td>
		 -->
			<td align="right">
		<br><a href="#" class="button" id="" onClick="return validate1();" ><span class="save">Save</span></a>
		</td>
		<td align="left">
		<br><a href="#" class="button" onclick="cancelFunc();"><span class="cancel">Cancel</span></a>
		</td>
	</tr>
</table>

<input type="hidden" name="selectedTab" /> 
<input type="hidden" name="hmode" />
</html:form>
<jsp:include page="multirow_inpatientconfig_ipd.jsp"></jsp:include>

<tag:autoIndex></tag:autoIndex></body>

</html>