<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>
<%@ taglib uri="/WEB-INF/patientDtl.tld" prefix="pDtl"%>
<%@ taglib uri="/WEB-INF/crNo.tld" prefix="crNo"%>

<html>
<head>
<meta charset=utf-8>
<title>Patient Admission Cancellation</title>

<link href="../css/transaction.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/buttons.css" rel="stylesheet" type="text/css">

<script language="JavaScript" src="../../masterutil/js/master.js"></script>
<script language="JavaScript" src="../../hisglobal/js/tab.js"></script>
<script language="JavaScript" src="../../hisglobal/js/util.js"></script>
<script language="JavaScript" src="../../hisglobal/js/validation.js"></script>
<script language="JavaScript" src="../../hisglobal/js/multirow.js"></script>
<script language="Javascript" src="../js/patientListing.js"></script>
<script language="Javascript" src="../js/admCancel.js"></script>
<script type="text/javascript">

 
</script>
</head>
<body onload="view();" onFocus="checkPopUpAndSetDefaultCrNo();">
<html:form action="/transactions/PatientAdmissionCancellationTransCNT" method="post">

	<div class="errMsg" id="errMsg"><bean:write name="patientAdmissionCanceTransBean" property="strMsgString" /></div>
	<div class="normalMsg" id="normalMsg"><bean:write name="patientAdmissionCanceTransBean" property="strMsg" /></div>
	<div class="warningMsg" id="warningMsg"><bean:write name="patientAdmissionCanceTransBean" property="strWarningMsg" /></div>
	

	<tag:tab tabLabel="Patient Admission Cancellation" selectedTab="FIRST" align="center" width="TABLEWIDTH"></tag:tab>
	<table class="TABLEWIDTH" align="center" cellspacing="1px">
		<tr class="HEADER">
			<td colspan="4">Patient Admission Cancellation</td>
		</tr>
	</table>
	<table class="TABLEWIDTH" align="center" cellspacing="1px">
		<tr>
			<td width="25%" class="LABEL"><font color="red" id="mandCRId">*</font>CR No.</td>
			<td class="CONTROL" nowrap="nowrap">
			<div id="patientCrEdId" style="display: block;"><crNo:crNo id="strCrNoId"
				value="${patientAdmissionCanceTransBean.strCrNo}"
				js=" onkeypress='return goRetFunc(event);return validateData(event,5);'"></crNo:crNo> <img
				style="cursor: pointer;" id="searhPatientImageId"
				src="../../hisglobal/images/viewDetails.gif"
				title="Click here for Patient Search" align="middle"
				name='searchPatient'
				onclick="showPatientListingWindow('8',document.forms[0].strCrNo,'setSelectedCrNo');" />
			<img src="../../hisglobal/images/Go.png"
				onClick="return goFunc();" align="top"
				style="cursor: pointer; cursor: hand"></div>
			<div id="patientCrId" style="display: none;"><bean:write
				name="patientAdmissionCanceTransBean" property="strCrNo" /></div>
			</td>
		</tr>
	</table>
	<div id="id4" style="display: none;">		
	 <table class="TABLEWIDTH" align="center" cellspacing="1px">
		<pDtl:patDtl crNo="${patientAdmissionCanceTransBean.strCrNo}"
			address="false"></pDtl:patDtl>
	</table> 
	</div>
	<div id="newModificationFormId" style="display: none">
	<table class="TABLEWIDTH" align="center" cellspacing="1px">
		<tr>
			<td colspan="4" class="TITLE">Address Details</td>
		</tr>
	</table>
	</div>
	<div id="newAddressModiId"><bean:write
		name="patientAdmissionCanceTransBean" property="strAddressModi"
		filter="false" /></div>
	<div id='DeparmentUnitModiId' style="display: none">
	<table class="TABLEWIDTH" align="center" cellspacing="1px">
		<tr>
			<td width="25%" class="LABEL">Department</td>
			<td width="25%" class="CONTROL"><select
				name="StrDeptNameNewBorn" onchange="getUnitCombo();"
				class="comboMax">
				<bean:write property="strDeptValue"
					name="patientAdmissionCanceTransBean" filter="false" />
			</select></td>
			<td width="25%" class="LABEL">Unit</td>
			<td width="25%" class="CONTROL">
			<div id="unitDivId"><select name="strUnitNewBorn"
				class="comboNormal" onchange="funUnit()">
				<bean:write property="strUnitValue"
					name="patientAdmissionCanceTransBean" filter="false" />
			</select></div>
			</td>
		</tr>
	</table>
	</div>
	<div id='DeparmentUnitId' style="display: none">
	<table class="TABLEWIDTH" align="center" cellspacing="1px">
		<tr>
			<td width="25%" class="LABEL">Department</td>
			<td width="25%" class="CONTROL"><bean:write
				property="strDeptName" name="patientAdmissionCanceTransBean"
				filter="false" /></td>
			<td width="25%" class="LABEL">Unit</td>
			<td width="25%" class="CONTROL"><bean:write
				property="strUnitName" name="patientAdmissionCanceTransBean"
				filter="false" /></td>
		</tr>
	</table>
	</div>


	<div id="wardDivId" style="display: none">
	<table class="TABLEWIDTH" align="center" cellspacing="1px">
		<tr>
			<td colspan="4" class="TITLE">Ward Bed Status</td>
		</tr>


		<tr>
			<td width="25%" class="LABEL">Ward</td>
			<td width="25%" class="CONTROL">
			<div id="wardNameId"><bean:write
				name="patientAdmissionCanceTransBean" property="strWardName" /></div>
			</td>
			<td width="25%" class="LABEL">Room</td>
			<td width="25%" class="CONTROL">
			<div id="roomBedId"><bean:write name="patientAdmissionCanceTransBean" property="strRoom" /></div>
			</td>
		</tr>
		<tr>
			<td class="LABEL" width="25%"><font color="red">*</font>Approved
			By</td>
			<td class="CONTROL" width="25%"><select name="strConsultantName"
				class='comboNormal'>
				<bean:write name="patientAdmissionCanceTransBean"
					property="strConsultantValues" filter='false' />
			</select></td>
			<td class="LABEL" width="25%" colspan="2"><div align="left">
	    <html:radio property="status" name="patientAdmissionCanceTransBean" value="1" >Wrong Admission</html:radio>
	    <html:radio property="status" name="patientAdmissionCanceTransBean" value="3" >Patient Denies Treatment</html:radio>
	   
	    
	    </div></td>
		</tr>
		<tr>
			<td width="25%" colspan="1" class="LABEL"><font color="red">*</font>Cancel Reason</td>
			<td width="25%" class="CONTROL"><textarea rows="3" cols="16" name="strRemarks"></textarea></td>
			<td class="CONTROL" width="25%" colspan="2"></td>
		</tr>
		<logic:greaterThan value="0" name="patientAdmissionCanceTransBean" property="strAdmissionChargeValue">
		<tr>
			<td width="25%" colspan="1" class="LABEL"><font color="red">Refund Amount(Admission Charges)</font></td>
			<td width="25%" class="CONTROL"><img src="/HBIMS/hisglobal/images/INR.png"/><font color="red"><bean:write name="patientAdmissionCanceTransBean" property="strAdmissionChargeValue" /></font></td>
			<td class="CONTROL" width="25%" colspan="2"></td>
		</tr>
		</logic:greaterThan>
	</table>
	</div>

	<table border="0" class="TABLEWIDTH" align="center" cellspacing="1px">
		<tr class="FOOTER">
			<td colspan="4"><font size="2" color="red">*</font> Mandatory
			Fields</td>
		</tr>
		<tr>
			<td align="center" colspan="2">
				<logic:notEmpty name="patientAdmissionCanceTransBean" property="strCrNo">
				<a href="#" class="button" id="" onClick="return validate1();" ><span class="save">Save</span></a>
					<!-- <img src="../../hisglobal/images/btn-sv.png" onClick="return validate1();" style="cursor: pointer; cursor: hand" /> -->
				</logic:notEmpty>
			<!-- <img src="../../hisglobal/images/btn-clr.png" onClick="clearRecord();" style="cursor: pointer; cursor: hand" />
			<img src="../../hisglobal/images/btn-ccl.png" onClick="cancelFunc();" style="cursor: pointer; cursor: hand" /> -->
			<a href="#" class="button"	onClick="clearRecord();"><span class="clear">Clear</span></a> 
			<a href="#" class="button" onclick="cancelFunc();"><span class="cancel">Cancel</span></a>	
		</td>
		</tr>
	</table>

	<input type="hidden" name="hmode" />
	<input type="hidden" name="deskmode" />
	<input type="hidden" name="strWardTypeCode" value="${patientAdmissionCanceTransBean.strWardTypeCode}" />
	<input type="hidden" name="strDeptUnitCode" value="${patientAdmissionCanceTransBean.strDeptUnitCode}" />
	<input type="hidden" name="strTreatmentCategoryCode" value="${patientAdmissionCanceTransBean.strTreatmentCategoryCode}" />
	<input type="hidden" name="strWardCode" value="${patientAdmissionCanceTransBean.strWardCode}" />
	<input type="hidden" name="strBedTypeCode" value="${patientAdmissionCanceTransBean.strBedTypeCode}" />
	<input type="hidden" name="strWardName" value="${patientAdmissionCanceTransBean.strWardName}" />
	<input type="hidden" name="strRoomTypeCode" value="${patientAdmissionCanceTransBean.strRoomTypeCode}" />
	<input type="hidden" name="strDeptCode" value="${patientAdmissionCanceTransBean.strDeptCode}" />
	<input type="hidden" name="strConsultantCode" value="${patientAdmissionCanceTransBean.strConsultantCode}" />
	<input type="hidden" name="strEpisodeCode" value="${patientAdmissionCanceTransBean.strEpisodeCode}" />
	<input type="hidden" name="strVisitNo" value="${patientAdmissionCanceTransBean.strVisitNo}" />
	<input type="hidden" name="strMlcNo" value="${patientAdmissionCanceTransBean.strMlcNo}" />
	<input type="hidden" name="strAdviceAdmNo" value="${patientAdmissionCanceTransBean.strAdviceAdmNo}" />
	<input type="hidden" name="strIsUrban" value="${patientAdmissionCanceTransBean.strIsUrban}" />
	<input type="hidden" name="strBedCode" value="${patientAdmissionCanceTransBean.strBedCode}" />
	<input type="hidden" name="strRoomCode" value="${patientAdmissionCanceTransBean.strRoomCode}" />
	<input type="hidden" name="strBookingDate" value="${patientAdmissionCanceTransBean.strBookingDate}" />
	<input type="hidden" name="strFlag" value="1" />
	<input type="hidden" name="strMsApprovalFlag" value="${patientAdmissionCanceTransBean.strMsApprovalFlag}" />
	<input type="hidden" name="strMsApprovalStatus" value="${patientAdmissionCanceTransBean.strMsApprovalStatus}" />
	<input type="hidden" name="strNewBorn" value="${patientAdmissionCanceTransBean.strNewBorn}" />
	<input type="hidden" name="strDeptName" value="${patientAdmissionCanceTransBean.strDeptName}" />
	<input type="hidden" name="strUnitName" value="${patientAdmissionCanceTransBean.strUnitName}" />
	<input type="hidden" name="strAdmNo" value="${patientAdmissionCanceTransBean.strAdmNo}" />
	<input type="hidden" name="gblCRValue"/>
	<input type="hidden" name="strAdmissionChargeValue" value="${patientAdmissionCanceTransBean.strAdmissionChargeValue}" />
	
</html:form>
<tag:autoIndex></tag:autoIndex>
</body>
</html>