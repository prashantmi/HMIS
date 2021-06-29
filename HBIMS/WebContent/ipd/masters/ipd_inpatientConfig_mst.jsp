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
<title>In Patient Configuration/General</title>

<script type="text/javascript">
/*

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
}*/

function validate1()
{	
	retVal = false;
	var hisValidator = new HISValidator("inPatientConfigBean");
	/* hisValidator.addValidation("strPurgingRecordCurrentlyAdmissionDetails","req","Days For Purging from Admission Details is a Mandatory Field");
	if(document.forms[0].strPurgingRecordCurrentlyAdmissionDetails.value>60)
	{
		alert("Days Should not be greater than 60 days");
		return false;
	}*/
	hisValidator.addValidation("strPurgeTimeNotReportedPatient","req","Purge Time For Not Reported Patient is a Mandatory Field"); 
	hisValidator.addValidation("strNotReportedTimeLimit","req","Not Reported Time Limit is a Mandatory Field"); 
	
	
	hisValidator.addValidation("strPrivateWardType", "dontselect=0","Please Select A Private Ward Type");
	hisValidator.addValidation("strIcuWardType", "dontselect=0","Please Select A ICU Ward Type");
	hisValidator.addValidation("strGovtEmpPayLimit", "req","Government Employee Basic Pay Limit is a Mandatory Field");
  	hisValidator.addValidation("strGovtEmpPayLimit", "amount=7,2","Government Employee Basic Pay Limit value must be valid Amount");
   	hisValidator.addValidation("strPvtEmpIncomeLimit", "req","Private Employee Monthly Income Limit is a Mandatory Field");
  	hisValidator.addValidation("strPvtEmpIncomeLimit", "amount=7,2","Private Employee Monthly Income Limit value must be valid Amount");
	hisValidator.addValidation("strBlockedExpiryTime", "req","Blocked Bed Expiry Time Validity  is a Mandatory Field");
	hisValidator.addValidation("strBedLimit","req","Bed Limit is a Mandatory Field");
	hisValidator.addValidation("strBedLimit","numgtet=1","Bed Limit must be greater than or equal to 1.");
	hisValidator.addValidation("strBabyDept", "dontselect=0","Please Select a New Born Baby Default Department 	");
	retVal = hisValidator.validate(); 
	
	if(retVal)
	{
	
		destroyMultiRow("1");
		destroyMultiRow("2");
	
		hisValidator.clearAllValidations();
		document.forms[0].selectedTab.value = "GENERALSAVE";
		document.forms[0].submit();
	}
	else
	{
		hisValidator.clearAllValidations();
		return false;
	}
}
	
<!--	function genAdmissionBlock(){-->
<!--		if(document.inPatientConfigBean.strGenWardAdmission[0].checked)-->
<!--			document.getElementById("genWardApproverId").style.display="none";-->


<!--	}-->
		
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
<html:form action="/masters/CNTInPatientConfigMst.cnt" method="post">

<div class="normalMsg"><bean:write name="inPatientConfigBean" property="strMsg" /></div>

<tag:tab tabLabel="In Patient Configuration" selectedTab="FIRST" align="center" width="TABLEWIDTH"></tag:tab> 
<tag:tab tabList="${inPatientConfigBean.lhm}" selectedTab="ipdgeneral" align="center" width="TABLEWIDTH"></tag:tab>

<table align="center" class="TABLEWIDTH" cellpadding="1px" cellspacing="1px">
	<tr class="HEADER">
		<td colspan="2" height="22">In Patient Configuration/General</td>
	<tr style="display: none;">
		<td class="LABEL" width="50%"><font color="red">*</font>Days For Purging from currently Admission Details</td>
		<td class="CONTROL" width="50%">
		<input type="text" name="strPurgingRecordCurrentlyAdmissionDetails" class="txtFldMin"
			maxlength="2" onkeypress="return validateData(event,5);" value="${inPatientConfigBean.strPurgingRecordCurrentlyAdmissionDetails }">
		</td>
	</tr>
	<tr>
		<td class="LABEL" width="50%"><font color="red">*</font>Purge Time For Not Reported Patient(Days)</td>
		<td class="CONTROL" width="50%">
		<input type="text" name="strPurgeTimeNotReportedPatient" class="txtFldMin" maxlength="2"
			onkeypress="return validateData(event,5);" value="${inPatientConfigBean.strPurgeTimeNotReportedPatient }">
		</td>
	</tr>
	<tr>
		<td class="LABEL" width="50%"><font color="red">*</font>Not Reported Time Limit(Hours)</td>
		<td class="CONTROL" width="50%">
		<input type="text" name="strNotReportedTimeLimit" class="txtFldMin" maxlength="2"
			onkeypress="return validateData(event,5);" value="${inPatientConfigBean.strNotReportedTimeLimit }">
		</td>
	</tr>
	<tr style="display:none;">
		<td class="LABEL" width="50%">MS Approval Form No. Online</td>
		<td class="CONTROL" width="50%">
		<html:checkbox property="strMsApprovalOffline" name="inPatientConfigBean" value="1"></html:checkbox>
		</td>
	</tr>
	<tr>
		<td class="LABEL" width="50%"><font color="red">*</font>Private Ward Type</td>
		<td class="CONTROL" width="50%">
		<select name="strPrivateWardType" class="comboNormal"> 
		<bean:write name="inPatientConfigBean" property="strPrivateWardTypeValues" filter="false" />
		</select></td>
	</tr>
	<tr>
		<td class="LABEL" width="50%"><font color="red">*</font>ICU Ward Type</td>
		<td class="CONTROL" width="50%">
		<select name="strIcuWardType" class="comboNormal"> 
		<bean:write name="inPatientConfigBean" property="strIcuWardTypeValues" filter="false" />
		</select></td>
	</tr>
	<tr>
		<td width="50%" class="LABEL"><font color="red">*</font>New Born Baby Process</td>

		<td width="50%" class="CONTROL">
		<html:radio name="inPatientConfigBean" property="strNewBornBabyProcessType" value="1">Online</html:radio> 
		<html:radio name="inPatientConfigBean" property="strNewBornBabyProcessType" value="2">Offline</html:radio></td>
	</tr>
	<tr >
		<td width="50%" class="LABEL"><font color="red">*</font>Minimum Age to be a Mother (Years)</td>
		<td width="50%" class="CONTROL">
		<html:text styleClass="txtFldMin" name="inPatientConfigBean" property="strMinAgeToBeMother"
			onkeypress="return validateData(event,5);" maxlength="2"></html:text>
		</td>
	</tr>
	<tr>
		<td width="50%" class="LABEL"><font color="red">*</font>Max. No. of babies a Mother can give Born</td>
		<td width="50%" class="CONTROL"><html:text styleClass="txtFldMin"
			name="inPatientConfigBean" property="strMaxNoOfBabyMotherCanBorn"
			onkeypress="return validateData(event,5);" maxlength="1"></html:text>
		</td>
	</tr>
</table>

<table align="center" class="TABLEWIDTH" cellpadding="1px" cellspacing="1px">
	<tr>
		<td class="LABEL" width="50%"><font color="red">*</font>New Born Baby Default Department</td>
		<td class="CONTROL" width="50%"><select name="strBabyDept" class="comboNormal">
			<bean:write name="inPatientConfigBean" property="strBabyDeptValues" filter="false" />
		</select></td>
	</tr>
	<tr>
		<td class="LABEL" width="50%"><font color="red">*</font>Patient Category (Staff)</td>
		<td class="CONTROL" width="50%"><select name="strPatientCategory" class="comboNormal">
			<bean:write name="inPatientConfigBean" property="strPatientCategoryValues" filter="false" />
		</select></td>
	</tr>
	<tr>
		<td class="LABEL" width="50%"><font color="red">*</font>Diagnosis Type</td>
		<td class="CONTROL" width="50%">
		<html:select name="inPatientConfigBean" property="strDiagType" styleClass="comboNormal">
			<html:option value="1">ICD Diagnosis</html:option>
			<html:option value="2">Hospital Diagnosis</html:option>
			<html:option value="0">Both</html:option>
		</html:select></td>
	</tr>
	<tr>
		<td class="LABEL" width="50%"><font color="red">*</font>Government Employee Basic Pay Limit(<img src='../../hisglobal/images/INR.png'>)</td>
		<td class="CONTROL" width="50%">
		<input type="text" name="strGovtEmpPayLimit" value="${inPatientConfigBean.strGovtEmpPayLimit }"
			class="txtFldNormal" maxlength="9" onkeypress="return validateData(event,7);"></td>
	</tr>
	<tr>
		<td class="LABEL" width="50%"><font color="red">*</font>Private Employee Monthly Income Limit(<img src='../../hisglobal/images/INR.png'>)</td>
		<td class="CONTROL" width="50%">
		<input type="text" name="strPvtEmpIncomeLimit" value="${inPatientConfigBean.strPvtEmpIncomeLimit }"
			class="txtFldNormal" maxlength="9" onkeypress="return validateData(event,7);"></td>
	</tr>	
	<tr>
		<td class="LABEL" width="50%"><font color="red">*</font>Blocked Bed Expiry Time</td>
		<td class="CONTROL" width="50%"><input type="text"
			name="strBlockedExpiryTime"
			value="${inPatientConfigBean.strBlockedExpiryTime }"
			class="txtFldMin" maxlength="3"
			onkeypress="return validateData(event,5);"> (in Hours)</td>
	</tr>
	<tr>
		<td class="LABEL" width="50%"><font color="red">*</font>Belonging Required</td>
		<td class="CONTROL" width="50%">
		<html:select name="inPatientConfigBean" property="strBelongingRequired" styleClass="comboNormal">
			<html:option value="0">No</html:option>
			<html:option value="1">Yes</html:option>
		</html:select></td>
	</tr>
	<tr>
		<td class="LABEL" width="50%"><font color="red">*</font>Issue Item Required</td>
		<td class="CONTROL" width="50%">
		<html:select name="inPatientConfigBean" property="strIssueItemRequired" styleClass="comboNormal">
			<html:option value="0">No</html:option>
			<html:option value="1">Yes</html:option>
		</html:select></td>
	</tr>
	<tr>
		<td class="LABEL" width="50%"><font color="red">*</font>Nurse CheckList Mandatory</td>
		<td class="CONTROL" width="50%">
		<html:select name="inPatientConfigBean" property="strNurseChecklistMandatory" styleClass="comboNormal">
			<html:option value="0">No</html:option>
			<html:option value="1">Yes</html:option>
		</html:select></td>
	</tr>
	<tr>
		<td width="50%" class="LABEL"><font color="red">*</font>Bed Limit</td>	
		<td width="50%" class="CONTROL"><html:text styleClass="txtFldMin"
			name="inPatientConfigBean"
			property="strBedLimit" onkeypress="return validateData(event,5);" maxlength="3"></html:text>
		</td>
	</tr>
	<tr>
		<td class="LABEL" width="50%"><font color="red">*</font>Leave Request Type</td>
		<td class="CONTROL" width="50%"> 
		<html:select name="inPatientConfigBean" property="strLeaveReqType" styleClass="comboNormal">
		<html:option value="2">Both</html:option>
		<html:option value="1">Offline</html:option>
		<html:option value="0">Online</html:option>				
		</html:select>
	 </td>
	 </tr>
	 <tr>
		<td width="50%" class="LABEL"><font color="red">*</font>Discharge Slip Required</td>

		<td width="50%" class="CONTROL">
		<html:radio name="inPatientConfigBean" property="strDischargeSlipReq" value="1">Yes</html:radio> 
		<html:radio name="inPatientConfigBean" property="strDischargeSlipReq" value="2">No</html:radio></td>
	</tr>
	<tr class="FOOTER">
		<td colspan="2"><font size="2" color="red">*</font> Mandatory Fields</td>
	</tr>
</table>

<table border="0" class="TABLEWIDTH" align="center">
	<tr>
		<td align="right">
		<!-- <img src="../../hisglobal/images/btn-sv.png" style="cursor: pointer" onClick=" return validate1();" /> -->
		<br><a href="#" class="button" id="" onClick="return validate1();" ><span class="save">Save</span></a>
		</td>
		<td align="left">
		<!-- <img src="../../hisglobal/images/btn-ccl.png" style="cursor: pointer" onClick="cancelFunc();" />-->
		<br><a href="#" class="button" onclick="cancelFunc();"><span class="cancel">Cancel</span></a>
		</td>
	</tr>
</table>

<input type="hidden" name="selectedTab"> 
<input type="hidden" name="hmode"></html:form>

<jsp:include page="multirow_inpatientconfig_ipd.jsp"></jsp:include>

<tag:autoIndex></tag:autoIndex>
</body>

</html>