<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>
<%@ taglib uri="/WEB-INF/dateTag.tld" prefix="date"%>
<%@ taglib uri="/WEB-INF/patientDtl.tld" prefix="pDtl"%>
<%@ taglib uri="/WEB-INF/crNo.tld" prefix="crNo"%>

<html>
<head>
<meta charset=utf-8>
<title>Slip Reprint</title>

<link href="../css/transaction.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/calendar-tas.css" rel="stylesheet" type="text/css">

<script language="JavaScript" src="../../masterutil/js/master.js"></script>
<script language="JavaScript" src="../../hisglobal/js/tab.js"></script>
<script language="JavaScript" src="../../hisglobal/js/calendar.js"></script>
<script language="JavaScript" src="../../hisglobal/js/util.js"></script>
<script language="JavaScript" src="../../hisglobal/js/validation.js"></script>
<script language="JavaScript" src="../../hisglobal/js/multirow.js"></script>
<script language="JavaScript" src="../../ipd/js/slipReprint.js"></script>
<script language="Javascript" src="../js/patientListing.js"></script>
<script language="JavaScript" src="../../ipd/js/patientOccupationDetail.js"></script>
<script type="text/javascript">
var beforePrint = function() 
{  
	showPrintableSlip();
};
var afterPrint = function() 
{               
	hidePrintableSlip();
};
/*if (window.matchMedia) 
{
	var mediaQueryList = window.matchMedia('print');
	mediaQueryList.addListener
	(
		function(mql) 
		{
		    if (mql.matches) 
		    {
		    	beforePrint();
		    } 
		    else 
		    {
		    	afterPrint();
		    }
		}
	);
 }*/

window.onbeforeprint = beforePrint;
window.onafterprint = afterPrint;
</script>

<style type="text/css">

@media print 
{ 
		#nonPrintable 
		{
		 display: none; 
		}		
}
</style>
</head>
<body onload="view();;openPrintPopUp();" onUnload="closePopUp();">
<html:form action="/transactions/SlipReprintTransCNT" method="post">
<div>
<div id="nonPrintable">
	<div class="errMsg" id="errMsg">
	<bean:write name="slipReprintTransBean" property="strMsgString"/></div>
	<div class="normalMsg" id="normalMsg">
	<bean:write name="slipReprintTransBean" property="strMsg"/>
	<div class="warningMsg">
	<bean:write name="slipReprintTransBean" property="strWarningMsg"/></div>
	</div>
	<tag:tab tabLabel="Slip Reprint" selectedTab="FIRST" align="center" width="TABLEWIDTH"></tag:tab>

	<table class="TABLEWIDTH" align="center" cellspacing="1px">
		<tr><td colspan="4"></td></tr>
		<tr class="HEADER">
			<td colspan="4">Slip Reprint</td>
		</tr>
	</table>
	<table class="TABLEWIDTH" align="center" cellspacing="1px">
		<tr>
			<td width="25%" class="LABEL"><font color="red" id="mandCRId">*</font>CR No.</td>
			<td class="CONTROL" nowrap="nowrap">
			<div id="patientCrEdId" style="display: block;">
			<crNo:crNo value="${slipReprintTransBean.strCrNo}" js="onkeypress='goRetFunc(event);'" id="strCrNoId"></crNo:crNo>
			<img style="cursor: pointer;display: none;" id="searhPatientImageId" src="../../hisglobal/images/viewDetails.gif"
				title="Click here for Patient Search" align="middle" name='searchPatient' 
				onclick="showPatientListingWindow('8',document.forms[0].strCrNo,'setSelectedCrNo');" />
			<img src="../../hisglobal/images/Go.png" onClick="return goFunc();" align="top"
				style="cursor: pointer; cursor: hand"></div>
			<div id="patientCrId" style="display: none;">
			<bean:write name="slipReprintTransBean" property="strCrNo"/></div>
			</td>
		</tr>
	</table>
	<div id="id4" style="display: none;">
	<table class="TABLEWIDTH" align="center" cellspacing="1px">
		<pDtl:patDtl crNo="${slipReprintTransBean.strCrNo}" address="false"></pDtl:patDtl>
	</table>
	</div>
	<div id="newModificationFormId" style="display: none">
	<table class="TABLEWIDTH" align="center" cellspacing="1px">
		<tr>
			<td colspan="4" class="TITLE">New Modification Form</td>
		</tr>
	</table>
	</div>
	<div id="newAddressModiId">
	<bean:write name="slipReprintTransBean" property="strAddressModi" filter="false" /></div>
	<div id='DeparmentUnitModiId' style="display: none">
	<table class="TABLEWIDTH" align="center" cellspacing="1px">
		<tr>
			<td width="25%" class="LABEL">Department</td>
			<td width="25%" class="CONTROL">
			<select name="strDeptNameNewBorn" onchange="getUnitCombo();" class="comboMax">
				<bean:write property="strDeptValue" name="slipReprintTransBean" filter="false" />
			</select></td>
			<td width="25%" class="LABEL">Unit</td>
			<td width="25%" class="CONTROL">
			<div id="unitDivId">
			<select name="strUnitNewBorn" class="comboNormal" onchange="funUnit()">
				<bean:write property="strUnitValue" name="slipReprintTransBean" filter="false" />
			</select></div>
			</td>
		</tr>
	</table>
	</div>
	<div id='DeparmentUnitId' style="display: none">
	<table class="TABLEWIDTH" align="center" cellspacing="1px">
		<tr>
			<td width="25%" class="LABEL">Department</td>
			<td width="25%" class="CONTROL">
			<bean:write property="strDeptName" name="slipReprintTransBean" filter="false" /></td>
			<td width="25%" class="LABEL">Unit</td>
			<td width="25%" class="CONTROL">
			<bean:write property="strUnitName" name="slipReprintTransBean" filter="false" /></td>
		</tr>
		<tr>
			<td width='25%' class='LABEL'><font color='red'>*</font>Consultant</td>
			<td width='25%' class='CONTROL'>	
				<div id='consId'>
					<select name='strConsultantCode' tabindex='1' disabled="disabled" class='comboMax' onchange="if(this.value!=0) document.getElementsByName('strConsultantName')[0].value=this.options[this.selectedIndex].text; else document.getElementsByName('strConsultantName')[0].value='';">
						<bean:write property="strConsultantValues" name="slipReprintTransBean" filter="false" />
					</select>
				</div>
			</td>
			<td width="25%" class="LABEL"></td>
			<td width="25%" class="CONTROL"></td>
		</tr>
	</table>
	</div>

	<div id="PatientOccId" style="display: none">
	<table class="TABLEWIDTH" align="center" cellspacing="1px">
		<tr>
			<td colspan="4" class="TITLE">
			<div id="id1" align="left" style="display: block;">
			<img src="../../hisglobal/images/plus.gif" onClick="view1();"
				style="cursor: pointer; cursor: hand" />
				Patient Occupation Details
			</div>
			<div id="id2" style="display: none;" align="left">
			<img src="../../hisglobal/images/minus.gif" onClick="view2();"
				style="cursor: pointer; cursor: hand" />
				Patient Occupation Details
			</div>
			</td>
		</tr>
	</table>
	</div>
	<div id="PatientOccDtl" style="display: none;">
	<table align="center" cellspacing="0px" cellpadding="0px" width="100%">
	<tr>
	<td colspan="4">
	<bean:write name="slipReprintTransBean" property="occupationDetailValues" filter="false"/>
	</td></tr></table>
	</div>
	<div id="wardDivId" style="display: none">
	<table class="TABLEWIDTH" align="center" cellspacing="1px">
		<tr>
			<td colspan="4" class="TITLE">Ward Bed Status</td>
		</tr>
		<tr style="display: none;">
			<td class="LABEL" width="25%">Bed Status</td>
			<td class="multiControl" width="25%">
			<img src="../../hisglobal/images/Bed_.gif" onClick="bedDetails();"
				align="middle" onmouseover="" style="cursor: pointer; cursor: hand"/>
			</td>
			<td class="LABEL" width="25%" colspan="2"></td>
		</tr>
		<tr>
			<td width="25%" class="LABEL">Ward</td>
			<td width="25%" class="CONTROL">
			<div id="wardNameId">
			<bean:write name="slipReprintTransBean" property="strWardName"/></div>
			</td>
			<td width="25%" class="LABEL">Room</td>
			<td width="25%" class="CONTROL">
			<div id="roomBedId">
			<bean:write name="slipReprintTransBean" property="strRoom"/></div>
			</td>
		</tr>
		<tr style="display:none;">
			<td width="25%" colspan="1" class="LABEL">Remarks</td>
			<td width="25%" colspan="3" class="CONTROL">
			<textarea rows="3" cols="25" name="strRemarks"></textarea></td>
		</tr>

	</table>
	</div>
	<div id="admissionId" style="display: none">
	<table class="TABLEWIDTH" align="center" cellspacing="1px">
		<tr>
			<td class="LABEL" width="25%">Admission Charges</td>
			<td class="CONTROL" width="50%"><input type="text" 
				name="strAdmissionCharge" value="" class="txtFldMin"></td>
			<td class="CONTROL" width="50%"></td>
		</tr>
	</table>
	</div>
	<table border="0" class="TABLEWIDTH" align="center" cellspacing="1px">
		<tr class="FOOTER">
			<td colspan="4"></td>
		</tr>
		<tr>
			<td align="center" colspan="2">
				<logic:notEmpty name="slipReprintTransBean" property="strCrNo">
					<img src="../../hisglobal/images/btn-pnt.png" onClick="return validate1();" style="cursor: pointer; cursor: hand" />
				</logic:notEmpty>
				<img src="../../hisglobal/images/btn-clr.png" onClick="clearRecord();" style="cursor: pointer;" />
				<img src="../../hisglobal/images/btn-ccl.png" onClick=" cancelFunc();" style="cursor: pointer;" />
			</td>
		</tr>
	</table>

	<input type="hidden" name="hmode" />
	<input type="hidden" name="strWardTypeCode" value="${slipReprintTransBean.strWardTypeCode}" />
	<input type="hidden" name="strDeptUnitCode" value="${slipReprintTransBean.strDeptUnitCode}" />
	<input type="hidden" name="strTreatmentCategoryCode" value="${slipReprintTransBean.strTreatmentCategoryCode}" />
	<input type="hidden" name="strWardCode" value="${slipReprintTransBean.strWardCode}" />
	<input type="hidden" name="strBedTypeCode" value="${slipReprintTransBean.strBedTypeCode}" />
	<input type="hidden" name="strRoomTypeCode" value="${slipReprintTransBean.strRoomTypeCode}" />
	<input type="hidden" name="strEpisodeCode" value="${slipReprintTransBean.strEpisodeCode}" />
	<input type="hidden" name="strVisitNo" value="${slipReprintTransBean.strVisitNo}" />
	<input type="hidden" name="strMlcNo" value="${slipReprintTransBean.strMlcNo}" />
	<input type="hidden" name="strAdviceAdmNo" value="${slipReprintTransBean.strAdviceAdmNo}" />
	<input type="hidden" name="strIsUrban" value="${slipReprintTransBean.strIsUrban}" />
	<input type="hidden" name="strBedCode" value="${slipReprintTransBean.strBedCode}" />
	<input type="hidden" name="strRoomCode" value="${slipReprintTransBean.strRoomCode}" />
	<input type="hidden" name="strBookingDate" value="${slipReprintTransBean.strBookingDate}" />
	<input type="hidden" name="strFlag" value="1" />
	<input type="hidden" name="strMsApprovalFlag" value="${slipReprintTransBean.strMsApprovalFlag}" />
	<input type="hidden" name="strMsApprovalStatus" value="${slipReprintTransBean.strMsApprovalStatus}" />
	<input type="hidden" name="strNewBorn" value="${slipReprintTransBean.strNewBorn}" />
	<input type="hidden" name="strDeptName" value="${slipReprintTransBean.strDeptName}" />
	<input type="hidden" name="strUnitName" value="${slipReprintTransBean.strUnitName}" />
	<input type="hidden" name="strDeptCode" value="${slipReprintTransBean.strDeptCode}" />
	<input type="hidden" name="strAdmNo" value="${slipReprintTransBean.strAdmNo}" />
	<input type="hidden" name="strBookingDate" value="${slipReprintTransBean.strBookingDate}" />
	<input type="hidden" name="strDeptUnitName" value="${slipReprintTransBean.strDeptUnitName}" />
	<input type="hidden" name="strWardName" value="${slipReprintTransBean.strWardName}" />
	<input type="hidden" name="strRoom" value="${slipReprintTransBean.strRoom}" />
	<input type="hidden" name="strConsultantName" value="${slipReprintTransBean.strConsultantName}">
	<input type="hidden" name="strAgeUnit" value="">
	<input type="hidden" name="strSexCode" value="">
	<input type="hidden" name="strAge" value="">
	<input type="hidden" name="strAdmDateTime" value="${slipReprintTransBean.strAdmDateTime}" />
	<input type="hidden" name=strIsIntegratedWithBilling value="${slipReprintTransBean.strIsIntegratedWithBilling}"/>
	<input type="hidden" name=strIsAdvanceAmountAtAdmission value="${slipReprintTransBean.strIsAdvanceAmountAtAdmission}"/>
	<input type="hidden" name=strIsAdvanceAmountAtAdmissionTaken value="${slipReprintTransBean.strIsAdvanceAmountAtAdmissionTaken}"/>
	<input type="hidden" name="strAdvanceAmountDate" value="${slipReprintTransBean.strAdvanceAmountDate}">
	<input type="hidden" name="strAdvanceAmountReceiptNo" value="${slipReprintTransBean.strAdvanceAmountReceiptNo}">
	<input type="hidden" name="strAdvanceAmount" value="${slipReprintTransBean.strAdvanceAmount}">
	<input type="hidden" name="strSaveFlag" value="${slipReprintTransBean.strSaveFlag}">
	<input type="hidden" name="strPatientCrNo" value="${slipReprintTransBean.strPatientCrNo}">
</div>
</div>
</html:form>
<tag:autoIndex></tag:autoIndex>
<div id='printableSlip'>
<logic:equal name="slipReprintTransBean"  property="strSaveFlag" value="1">


<tiles:insert  page="/ipd/transactions/PatientAdmissionTransCNT.cnt?hmode=PRINTSLIP&strCrNo=${slipReprintTransBean.strPatientCrNo}&strAdmNo=${slipReprintTransBean.strAdmNo}&duplicateMode=1"/>



</logic:equal>
</div>
</body>
</html>