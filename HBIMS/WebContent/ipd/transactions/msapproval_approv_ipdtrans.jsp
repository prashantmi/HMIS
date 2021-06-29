<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>



<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>
<%@ taglib uri="/WEB-INF/dateTag.tld" prefix="date"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/comboPersistence.tld" prefix="cmbPers"%>


<html>
<head>
<meta charset=utf-8>
<title>MS Approval Page</title>
<link href="../css/transaction.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/buttons.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/calendar-tas.css" rel="stylesheet" type="text/css">

<script language="JavaScript" src="../../hisglobal/js/validation.js"></script>
<script language="JavaScript" src="../../hisglobal/js/util.js"></script>
<script language="JavaScript" src="../../hisglobal/masterutil/js/master.js"></script>
<script language="JavaScript" src="../../hisglobal/js/calendar.js"></script>

<script type="text/javascript">

function validate1()
{

var retVal = false;
var hisValidator = new HISValidator("msApprovalTransBean");
hisValidator.addValidation("strApprovedBY", "dontselect=0","Approved by is mandatory field");
hisValidator.addValidation("strRemark", "req","Remark  is mandatory field");
if(document.msApprovalTransBean.strRemark.value != ""){
		hisValidator.addValidation("strRemark", "maxlen=100","Remark Cannot be More than 100 Characters");
		}
				
		retVal = hisValidator.validate(); 
		if(retVal){
document.forms[0].hmode.value = "SAVEAPPROVE";
document.forms[0].submit();
}
else{
	hisValidator.clearAllValidations();
	return false;
	}
}

/*
function validate1()
{ 
document.forms[0].hmode.value = "SAVEAPPROVE";
alert("mode=="+document.forms[0].hmode.value);
document.forms[0].submit();
}
*/
function funPatientListAdd(){
 document.getElementById("divapprovallist").style.display="block";
 document.getElementById("plus").style.display="none";
 document.getElementById("minus").style.display="";
 }
function funPatientListRemove(){
 document.getElementById("divapprovallist").style.display="none";
 document.getElementById("plus").style.display="";
 document.getElementById("minus").style.display="none";
 }
 function clear1(){
	document.forms[0].strApprovedBY.value="0";
	document.forms[0].strRemark.value="";
	document.forms[0].strApprovedStatus[0].checked=true;
}
function show()
{
document.getElementById("plus").style.display="none";
document.getElementById("minus").style.display="";
document.getElementById("divapprovallist").style.display="";
document.getElementById("minusPatEp").style.display="none";
}

function showPatEpDtl(flag)
{
	if(flag=='1')//show
	{
		document.getElementById("patEpDtl").style.display="";
		document.getElementById("minusPatEp").style.display="";
		document.getElementById("plusPatEp").style.display="none";
	}
	else
	{
		document.getElementById("patEpDtl").style.display="none";
		document.getElementById("minusPatEp").style.display="none";
		document.getElementById("plusPatEp").style.display="";
	}
}


</script>
</head>

<body onload="show()">
<html:form action="/transactions/MsApprovalTransCNT" method="post">
<fmt:setBundle basename="/ipd/ADT_Resource_Bundle" var="lang"/>  
<fmt:setLocale value="en"/>  

<div class="normalMsg" id="normalMsg"></div>	
	<tag:tab tabLabel="Approval Page" selectedTab="FIRST" width="TABLEWIDTH"></tag:tab>
	<table class='TABLEWIDTH' align='center' cellspacing='1px' cellpadding="1px">
		<tr class="HEADER">
			<td colspan="6" width="100%">
			<img style="cursor: pointer" src="../../hisglobal/images/plus.gif" onClick="funPatientListAdd();" id="plus"/>
			<img style="cursor: pointer" src="../../hisglobal/images/minus.gif" onClick="funPatientListRemove();" id="minus"/>
			<a style="cursor: pointer; color: " title="Show List" onclick="funPatientListAdd();"><fmt:message key="patientList" bundle="${lang}"/></a></td>			
		</tr>
		<tr>
			<td class="multiLabel" width='20%' colspan="1">CR No</td>
			<td CLASS="multiLabel" width='20%' colspan="1">Name</td>
			<td CLASS="multiLabel" width='10%' colspan="1">Age/Sex</td>
			<td CLASS="multiLabel" width='20%' colspan="1">Dept/Unit/Ward</td>
			<td CLASS="multiLabel" width='20%' colspan="1">Priority Type</td>
			<td CLASS="multiLabel" width='20%' colspan="1">Adm Date/Time</td>
			
		</tr>
		</table>	
		<tr>
			<td colspan='6' width="100%">
			<div id="divapprovallist" style="display: block">
			<bean:write name='msApprovalTransBean' property='approvallist' filter="false" /></div>
			</td>
		</tr>
	
	<div id="patEp">
	<table class='TABLEWIDTH' align='center' cellspacing='1px' cellpadding="1px">
		<tr class="TITLE">
			<td colspan="4" width="100%">
			<div id="plusPatEp">
			<img alt="Show Patient Episode Details" src="../../hisglobal/images/plus.gif" style="cursor: pointer;"
			onclick="showPatEpDtl('1')">&nbsp;&nbsp;Episode Details</div>
			<div id="minusPatEp">
			<img alt="Hide Patient Episode Details" src="../../hisglobal/images/minus.gif" style="cursor: pointer;"
			onclick="showPatEpDtl('2')">&nbsp;&nbsp;Episode Details</div>
			</td>
		</tr>
	</table>
	</div>
	<div id="patEpDtl" style="display: none;">
	<table class='TABLEWIDTH' align='center' cellspacing='1px'
		cellpadding="1px">
		<tr>
			<td class="LABEL" width="25%">Episode No</td>
			<td class="CONTROL"  width="25%"><bean:write
				name="msApprovalTransBean" property="strEpisodeNumber" /></td>
				
			<td class="LABEL" width="25%">Consultant</td>
			<td class="CONTROL"  width="25%">
			<div id="divconsultant"><bean:write name="msApprovalTransBean"
				property="strConsultantName" /></div>
			</td>	
		</tr>
		<tr>
			<td class="LABEL" width="25%">Proposed Department/Unit</td>
			<td class="CONTROL" width="25%"><bean:write
				name="msApprovalTransBean" property="strDeptUnitCode" /></td>
			<td class="LABEL" width="25%">Proposed Ward</td>
			<td class="CONTROL" width="25%"><bean:write
				name="msApprovalTransBean" property="strWardName" /></td>	
		</tr>
		
		<tr>
			<td class="LABEL" width="25%"><bean:write
				name="msApprovalTransBean" property="strdateLable" /></td>
			<td width="25%" class="CONTROL"><font color="blue"><bean:write
				name="msApprovalTransBean" property="strPropAdminssionDate" /></font></td>
				
			<td class="LABEL" width="25%">Proposed Surgery Date</td>
			<td width="25%" class="CONTROL"><font color="blue"><bean:write
				name="msApprovalTransBean" property="strSurgeryDate" /></font></td>	
	   </tr>
	   
	   <tr>
			<td class="LABEL" width="25%">Admission Type</td>
			<td width="25%" class="CONTROL"><bean:write
				name="msApprovalTransBean" property="strAdmissionType" /></td>
				
			<td class="LABEL" width="25%">Approximately Length Of Stay</td>
			<td width="25%" class="CONTROL"><bean:write
				name="msApprovalTransBean" property="strLengthOfStay" /></td>	
	   </tr>
		
		<!--  <tr>
			<td class="LABEL" width="25%">Provisional Diagnosis</td>
			<td class="CONTROL" colspan="3" width="75%">
			<div id="divdiagnosis">
				<bean:write
				name="msApprovalTransBean" property="strProvisionDiagnosis" filter="false"/>
			</div>
			</td>
		</tr>
		<tr>
			<td class="LABEL" width="25%">Consultant</td>
			<td class="CONTROL" colspan="3" width="25%">
			<div id="divconsultant"><bean:write name="msApprovalTransBean"
				property="strConsultantName" /></div>
			</td>
		</tr>-->
	</table>
	</div>
	
	<table class='TABLEWIDTH' align='center' cellspacing='1px'
		cellpadding="1px">
		<tr CLASS="TITLE">
			<td colspan="5">Approval</td>
		</tr>
		<tr>
			<td width="25%" CLASS="LABEL"><font color="red">*</font>Approved</td>
			<td width="25%" class="CONTROL"><html:radio
				name="msApprovalTransBean" property="strApprovedStatus" value="1" />Yes
			<html:radio name="msApprovalTransBean" property="strApprovedStatus"
				value="5" />No</td>
			<td width="25%" class="LABEL">Approval Date</td>
			<td width="25%" colspan="2" class="CONTROL"><INPUT type="hidden"
				name="strApproveDate" value="${msApprovalTransBean.strCtDate}" />
				<font color="blue">
				<bean:write name="msApprovalTransBean" property="strCtDate" /></font></td>
		</tr>
		<tr>
			<td width="25%" class="LABEL"><font color="red">*</font>Approved By</td>
			<td width="25%" class="CONTROL"><html:select name="msApprovalTransBean" property="strApprovedBY"
				styleClass="comboNormal">
				<bean:write name="msApprovalTransBean"
					property="strEmployeeComboValues" filter="false" />
			</html:select></td>
			<td width="25%" class="LABEL"><font color="red">*</font>Remarks</td>
			<td width="25%" colspan="2" class="CONTROL"><html:textarea
				name="msApprovalTransBean" property="strRemark" value="${msApprovalTransBean.strRemark}" /></td>
		</tr>
		<tr class="HEADER">
			<td colspan="5">&nbsp;</td>

		</tr>
	</table>

	<table border="0" class="TABLEWIDTH" align="center">
		<tr>
			<td align="center">
			<!-- <img style="cursor: hand; cursor: pointer"
				src="../../hisglobal/images/btn-sv.png"
				onClick="return validate1();" /><img
				style="cursor: hand; cursor: pointer"
				src="../../hisglobal/images/btn-clr.png" onclick="clear1();" /><img
				style="cursor: hand; cursor: pointer"
				src="../../hisglobal/images/btn-ccl.png"
				onclick="cancel('LIST');" />
				 -->
				<br><a href="#" class="button" id="" onClick="return validate1();" ><span class="save">Save</span></a>
							<a href="#" class="button"	onClick="clear1;"><span class="clear">Clear</span></a> 
							<a href="#" class="button" onclick="cancel('LIST');"><span class="cancel">Cancel</span></a>
				</td>

		</tr>
	</table>

	<input type="hidden" name="hmode">
	<html:hidden name="msApprovalTransBean" property="hrowlengthApproval"
		value="${msApprovalTransBean.hrowlengthApproval}" />
	<cmbPers:cmbPers></cmbPers:cmbPers>

</html:form>
<tag:autoIndex></tag:autoIndex>
</body>
</html>