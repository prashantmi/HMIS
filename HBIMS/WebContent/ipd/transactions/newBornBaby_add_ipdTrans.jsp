<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<meta charset="utf-8" /></head>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>
<%@ taglib uri="/WEB-INF/patientDtl.tld" prefix="pDtl"%>
<%@ taglib uri="/WEB-INF/crNo.tld" prefix="crNo"%>

<link href="../css/transaction.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/calendar-tas.css" rel="stylesheet" type="text/css">

<title>New Born Baby Admission</title>

<script language="JavaScript" src="../../masterutil/js/master.js"></script>
<script language="JavaScript" src="../../hisglobal/js/calendar.js"></script>
<script language="JavaScript" src="../../hisglobal/js/tab.js"></script>
<script language="JavaScript" src="../../hisglobal/js/util.js"></script>
<script language="JavaScript" src="../../hisglobal/js/validation.js"></script>
<script language="JavaScript" src="../../hisglobal/js/multirow.js"></script>
<script language="JavaScript" src="../../ipd/js/newBornBaby.js"></script>
<script language="JavaScript" src="../../ipd/js/patientOccupationDetail.js"></script>
<script language="Javascript" src="../js/patientListing.js"></script>

<script type="text/javascript">



function openPrintPopUp()
{
	if(document.forms[0].strSaveFlag.value=='1' && document.forms[0].strPatientCrNo.value!='' )
	{
		printSlip();
		//document.forms[0].hmode.value="PRINTSLIP";
		//alert(document.forms[0].strPatientCrNo.value);
		//document.forms[0].strCrNo.value=document.forms[0].strPatientCrNo.value;
		window.print();
		//document.forms[0].submit();
		//alert(document.forms[0].strCrNo.value);
		//alert(document.forms[0].hmode.value);
		
			//window.close();
		
	
	 }
	
	//alert(window.matchMedia('print'));
	 document.forms[0].strSaveFlag.value=0;
	 //window.onbeforeprint = beforePrint;
	 //window.onafterprint = hidePrintableSlip();
	 //setTimeout("hidePrintableSlip()",2000);
	 //document.getElementById("printableSlip").style.display="none"; 
}
function hidePrintableSlip()
{
	//alert("hide");
	document.getElementById("printableSlip").style.display="none"; 
}
function printLastBill()
{
	//alert("show");
	//document.getElementById("printableSlip").style.display="";
	if(document.getElementById("printableSlip").style.display=="")
	{
		// alert("showsdsdsdsdsd");
		alert("No Bill Generated Yet.");
		return;
	}
	else
	    window.print();
}
function showPrintableSlip()
{
	//alert("show");
	document.getElementById("printableSlip").style.display=""; 
}
var globalCnt=0;
String.prototype.replaceAll=function(target, replacement) 
{
	  return this.split(target).join(replacement);
}

var child = null;
var popIndex = 0;
var gblCntrlObj = null;
window.history.forward();

//function to open 'updateMotherDeliveryDetails.jsp'
function openUpdateMotherDelDet()
{
	document.forms[0].strCrNo.value = '';
	document.forms[0].hmode.value="UPDATEMOTHERDETAILS";
	document.forms[0].submit();			
}



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

<body onload="viewN();checkBedIsSharable();removeSpace();openPrintPopUp();" onUnload="closePopUp();">
<html:form action="/transactions/NewBornBabyTransCNT.cnt" method="post">
<div>
<div id="nonPrintable">
<tag:tab tabLabel="New Born Baby Admission" selectedTab="FIRST" align="center" width="TABLEWIDTH"></tag:tab>
	<div class="errMsg" id="errId"><bean:write name="newBornTransBean" property="strMsgString" /></div>
	<div class="normalMsg" id="normalMsg"></div>
	<div class="normalMsg" id="nrmId"><bean:write name="newBornTransBean" property="strMsg" /></div>
	<div class="warningMsg"><bean:write name="newBornTransBean" property="strWarningMsg" /></div>	

	<table class="TABLEWIDTH" align="center" cellspacing="1px">
		<tr class="HEADER">
			<td colspan="3">New Born Baby Admission</td>
			<td colspan="1" align="right">
			<a id="motherDelDetUpdate" onclick="openUpdateMotherDelDet();" style="cursor: pointer;color: white"><b><u>Mother Delivery Details Update</u></b></a>
			</td>
		</tr>
	</table>
	<table class="TABLEWIDTH" align="center" cellspacing="1px">
		<tr>
			<td width="25%" class="LABEL"><font color="red" id="mandCRId">*</font>Mother CR No.</td>
			<td class="CONTROL" nowrap="nowrap">
			<div id="patientCrEdId" style="display: block;">
			<crNo:crNo value="${newBornTransBean.strCrNo}"
				js="onkeypress='return validateData(event,5);'  onkeyup='goRetFuncNB(event);'" id="strCrNoId"></crNo:crNo>
			<img style="cursor: pointer;" src="../../hisglobal/images/viewDetails.gif"
				title="Click here for Patient Search" align="middle" name='searchPatient' id="searchPatImg"
				onclick="showPatientListingWindow('6',document.forms[0].strCrNo,'setSelectedCrNo');" />
			<img src="../../hisglobal/images/Go.png" onClick="return goFuncNB1();" align="top"
				style="cursor: pointer;"></div>
			<div id="patientCrId" style="display: none;">
			<bean:write name="newBornTransBean" property="strCrNo"/></div>
			</td>
		</tr>
	</table>
	<div id="id4" style="display: none;">		
	 <table class="TABLEWIDTH" align="center" cellspacing="1px">
		<pDtl:patDtl crNo="${newBornTransBean.strCrNo}" address="false"></pDtl:patDtl>
	</table> 
	</div>
	<div id="id5" style="display: none">
	<table class="TABLEWIDTH" align="center" cellspacing="1px">
		<tr>
			<td colspan="4" class="TITLE">
			<div id="plusMomId" align="left" style="display: block;">
			<img src="../../hisglobal/images/plus.gif" onClick="viewX();"
				style="cursor: pointer; cursor: hand" />&nbsp;&nbsp;Mother Details</div>
			<div id="minusMomId" style="display: none;" align="left">
			<img src="../../hisglobal/images/minus.gif" onClick="viewY();"
				style="cursor: pointer; cursor: hand" />&nbsp;&nbsp;Mother Details</div></td>
		</tr>
	</table>
	</div>
	<div id="momDtlId" style="display: none">
	<table class="TABLEWIDTH" align="center" cellspacing="1px">
		<tr>
			<td width="25%" class="LABEL" colspan="1">Mother Name</td>
			<td width="25%" class="CONTROL" colspan="3">
			<bean:write name="newBornTransBean" property="strMotherName" filter="false"/></td>
		</tr>
		<tr>
			<td width="25%" class="LABEL">Mother Admission No.</td>
			<td width="25%" class="CONTROL">
			<bean:write name="newBornTransBean" property="strMotherAdmissionNo" filter="false" /></td>
			<td width="25%" class="LABEL">Department/Unit</td>
			<td width="25%" class="CONTROL">
			<bean:write name="newBornTransBean" property="strDeptUnitName"/></td>
		</tr>
		<tr style="display:none;">
			<td width="25%" class="LABEL">Mother Nationality</td>
			<td width="25%" class="CONTROL">
			<bean:write name="newBornTransBean" property="strMotherNationality" filter="false"/></td>
			<td width="25%" class="LABEL">Mother Religion</td>
			<td width="25%" class="CONTROL">
			<bean:write name="newBornTransBean" property="strMotherReligion" filter="false" />
			</td>
		</tr>
		<tr>
			<td width="25%" class="LABEL">Treatment Category</td>
			<td width="75%" colspan="3" class="CONTROL">
			<bean:write name="newBornTransBean" property="strTreatmentCategoryName" filter="false" /></td>
		</tr>
	</table>
	</div>	
	<div id="MotherOnlineId">
	<bean:write name="newBornTransBean" property="strOnlineBabyList" filter="false"></bean:write>
	</div>	
	<div id="newModificationId" style="display: none">
	<table class="TABLEWIDTH" align="center" cellspacing="1px">
		<tr>
			<td colspan="4" class="TITLE">
			<div id="plusAddModiId" align="left" style="display: block;">
			<img src="../../hisglobal/images/plus.gif" onClick="view3();"
				style="cursor: pointer;" />&nbsp;&nbsp;New Born Baby Form</div>
			<div id="minusAddModiId" style="display: none;" align="left">
			<img src="../../hisglobal/images/minus.gif" onClick="view4();"
				style="cursor: pointer;" />&nbsp;&nbsp;New Born Baby Form</div>
			</td>
		</tr>
	</table>
	</div>
	<div id="newAddressModiId" style="display: none">
	<bean:write name="newBornTransBean" property="strAddressModi" filter="false" /></div>
	<div id="PatientOccId" style="display: none">
	<table class="TABLEWIDTH" align="center" cellspacing="1px">
		<!--<tr>
			<td colspan="4" class="TITLE">
			<div id="id1" align="left" style="display: block;">
			<img src="../../hisglobal/images/plus.gif" onClick="view1();"
				style="cursor: pointer;" />&nbsp;&nbsp;Patient Occupation Details
			</div>
			<div id="id2" style="display: none;" align="left">
			<img src="../../hisglobal/images/minus.gif" onClick="view2();"
				style="cursor: pointer;" />&nbsp;&nbsp;Patient Occupation Details
			</div>
			</td>
		</tr>
	-->
	</table>
	</div>
	<div id="PatientOccDtl">
	<bean:write name="newBornTransBean" property="occupationDetailValues" filter="false" /></div>
	<div id="wardDivId" style="display: none">
	<table class="TABLEWIDTH" align="center" cellspacing="1px">
		<tr>
			<td colspan="4" class="TITLE">Ward Bed Status</td>
		</tr>
		
		<tr style="display:none">
		<td class ="LABEL" width="25%">Whether allot same bed as Mother</td>
		<td width="25%" class="CONTROL">
		 <html:checkbox name ="newBornTransBean" property ="strSameBedAsMotherFlg"  value ="0" onchange="roomBedStatus();"/>
		 </td>
		 <td class ="LABEL" width="25%"></td>
		  <td class ="LABEL" width="25%"></td>
		</tr>
		
		<tr>
			<td width="25%" class="LABEL"><font color="red">*</font>Department</td>
			<td width="25%" class="CONTROL">
			<select name="strDeptNameNewBorn" onchange="getUnitComboNB();" class="comboMax">
				<bean:write property="strDeptValue" name="newBornTransBean" filter="false" />
			</select></td>
			<td width="25%" class="LABEL"><font color="red">*</font>Unit</td>
			<td width="25%" class="CONTROL">
			<div id="unitDivId">
			<select name="strUnitNewBorn" class="comboMax" onchange="getCons();">
				<bean:write property="strUnitValue" name="newBornTransBean" filter="false" />
			</select></div>
			</td>
		</tr>
		<tr>
			<td class="LABEL" width="25%" style="display:none;">Bed Status</td>
			<td class="multiControl" width="25%" style="display:none;">
			<img src="../../hisglobal/images/Bed_.gif" onClick="checkFlag();"
				align="middle" onmouseover="" style="cursor: pointer;" />
			</td>
			<td class="LABEL" width="25%"><font color="red">*</font>Consultant</td>
			<td width="25%" class="CONTROL">
			<div id="consDivId">
			<select name="strConsNewBorn" class="comboBig">
				<!--<bean:write property="strConsValue" name="newBornTransBean" filter="false" />-->
				<option value='0'>Select Value</option>
			</select></div>
			</td>
		</tr>
		<tr>
			<td width="25%" class="LABEL">Mother's Ward</td>
			<td width="25%" class="CONTROL">
			<div id="motherwardNameId">
			<bean:write name="newBornTransBean" property="strMotherWardName" />
			</div>
			</td>
			<td width="25%" class="LABEL">Mother's Room</td>
			<td width="25%" class="CONTROL">
			<div id="motherroomBedId">
			<bean:write name="newBornTransBean" property="strRoom" />
			</div>
			</td>
		</tr>
		<tr>
			<td width="25%" class="LABEL"><font color="red">*</font>Baby's Ward</td>
			<td width="25%" class="CONTROL">
			<div id="wardNameId">
			<!--  <select name="strWard" onChange="getRoomNo();" class="comboMax"> -->
    		<!--<bean:write name="newBornTransBean" property="strWard" filter="false"/>-->
    		
    	    <!-- </select> -->
			<!--<bean:write name="newBornTransBean" property="strMotherWardName" />-->
			</div>
			</td>
			<td width="25%" class="LABEL"><font color="red">*</font>Baby's Room</td>
			<td width="25%" class="CONTROL">
			<div id="roomBedId">
			<!-- <select name="strRoomCode" onChange="" class="comboMax"> -->
    		<!--<bean:write name="newBornTransBean" property="strRoomCode" filter="false"/>-->
    		
    	   <!--  </select> -->
			<!--<bean:write name="newBornTransBean" property="strRoom" />-->
			</div>
			</td>
		</tr>
		<tr>
			<td width="25%" class="LABEL"><font size="2" color="red" tabindex='1'>*</font>Gate Pass No.</td>
			<td width="25%" class="CONTROL"><input type="text" 
			    name="strCaseSheetNo" value="${newBornTransBean.strCaseSheetNo }" class="txtFldMax" maxlength="15"></td>
			<td width="25%"></td>
			<td width="25%"></td>
		</tr>
		<tr style="display: none">
			<td width='25%' class='LABEL'><font color='red'>*</font>Admission Type</td>
			<td width='25%' class='CONTROL'>
				<select name='strAdmissionType' class='comboNormal' tabindex='1' onChange=''>
					<bean:write name="newBornTransBean" property="strAdmissionTypeValues" filter="false"/>
				</select>
			</td>
						
			<td width='25%' class='LABEL'><font color='red'>*</font>Relief Fund</td>
			<td width='25%' class='CONTROL'>
				<select name='strReliefFund' class='comboNormal' tabindex='1' onChange=''>
					<bean:write name="newBornTransBean" property="strReliefFundValues" filter="false"/>
				</select>
			</td>
		</tr>
		<tr>
			<td width="25%" colspan="1" class="LABEL">Remarks</td>
			<td width="25%" colspan="3" class="CONTROL">
			<textarea rows="3" cols="25" name="strRemarks"></textarea></td>
		</tr>
	</table>
	</div>
	<div style="display: none" id="chargesDtlId">
	<table class="TABLEWIDTH" align="center" cellspacing="1px">
		<tr>
			<td colspan="4" class="TITLE">Admission Charges Details</td>
		</tr>
	</table>
	</div>
	<div id="admissionId" style="display: none">
	<table class="TABLEWIDTH" align="center" cellspacing="1px">
		<tr>
			<td class="LABEL" width="25%">Admission Charges</td>
			<td class="CONTROL" width="25%">
			<input type="text" name="strAdmissionChargeValue" value="${newBornTransBean.strAdmissionChargeValue}"
				class="txtFldMin"></td>
			<td class="LABEL" width="25%"></td>
			<td class="LABEL" width="25%"></td>
		</tr>
	</table>
	</div>
	<div id="admissionAdvanceId" style="display: none">
	<table class="TABLEWIDTH" align="center" cellspacing="1px">
		<tr>
			<td class="LABEL" width="25%">Advance Admission Charges</td>
			<td class="CONTROL" width="25%">
			<input type="text" name="strAdmissionAdvanceChargeValue"
				value="${newBornTransBean.strAdmissionAdvanceChargeValue}"
				class="txtFldMin"></td>
			<td class="LABEL" width="25%"></td>
			<td class="LABEL" width="25%"></td>
		</tr>
	</table>
	</div>
	<div id="registrationId" style="display: none">
	<table class="TABLEWIDTH" align="center" cellspacing="1px">
		<tr>
			<td class="LABEL" width="25%">Registration Charges</td>
			<td class="CONTROL" width="25%">
			<input type="text" name="strNewBornRegistrationChargeVal"
				value="${newBornTransBean.strNewBornRegistrationChargeVal}"
				class="txtFldMin"></td>
			<td class="LABEL" width="25%"></td>
			<td class="LABEL" width="25%"></td>
		</tr>
	</table>
	</div>
	<div id="TotalID" style="display: none">
	<table class="TABLEWIDTH" align="center" cellspacing="1px">
		<tr>
			<td class="LABEL" width="25%">Total Charges</td>
			<td class="CONTROL" width="25%">
			<input type="text" name="strTotalChargeVal" class="txtFldMin"></td>
			<td class="LABEL" width="25%"></td>
			<td class="LABEL" width="25%"></td>
		</tr>
	</table>
	</div>
	<table border="0" class="TABLEWIDTH" align="center" cellspacing="1px">
		<tr class="FOOTER">
			<td colspan="4"><font size="2" color="red">*</font> Mandatory Fields</td>
		</tr>
		<tr>
			<td align="center" colspan="2">
			<logic:notEmpty name="newBornTransBean" property="strCrNo">
				<img src="../../hisglobal/images/btn-sv.png" onClick="matchWardRoomCriteria();" style="cursor: pointer; cursor: hand" />
			</logic:notEmpty>
			<img src="../../hisglobal/images/btn-clr.png" onClick="clearRecordNB();" style="cursor: pointer;"/>
			<img src="../../hisglobal/images/btn-ccl.png" onClick="cancelFunc();" style="cursor: pointer;"/></td>
		</tr>
	</table>
	<input type="hidden" name="hmode" />
	<input type="hidden" name="strAdmissionChargeHidden" value="${newBornTransBean.strAdmissionChargeHidden}" />
	<input type="hidden" name="strRegistrationChargeHidden" value="${newBornTransBean.strRegistrationChargeHidden}" />
	<input type="hidden" name="strMotherDeptCode" value="${newBornTransBean.strMotherDeptCode}" />
	<input type="hidden" name="strMotherWardCode" value="${newBornTransBean.strMotherWardCode}" />
	<input type="hidden" name="strMotherWardTypeCode" value="${newBornTransBean.strMotherWardTypeCode}" />
	<input type="hidden" name="strMotherRoomTypeCode" value="${newBornTransBean.strMotherRoomTypeCode}" />
	<input type="hidden" name="strMotherBedTypeTypeCode" value="${newBornTransBean.strMotherRoomTypeCode}" />
	<input type="hidden" name="strWardTypeCode" value="${newBornTransBean.strWardTypeCode}" />
	<input type="hidden" name="strDeptUnitCode" value="${newBornTransBean.strDeptUnitCode}" />
	<input type="hidden" name="strTreatmentCategoryCode" value="${newBornTransBean.strTreatmentCategoryCode}" />
	<input type="hidden" name="strWardCode" value="${newBornTransBean.strMotherWardCode}" />
	<input type="hidden" name="strBedTypeCode" value="${newBornTransBean.strBedTypeCode}" />
	<input type="hidden" name="strRoomTypeCode" value="${newBornTransBean.strRoomTypeCode}" />
	<input type="hidden" name="strDeptCode" value="${newBornTransBean.strDeptCode}" />
	<input type="hidden" name="strConsultantCode" value="${newBornTransBean.strConsultantCode}" />
	<input type="hidden" name="strEpisodeCode" value="${newBornTransBean.strEpisodeCode}" />
	<input type="hidden" name="strVisitNo" value="${newBornTransBean.strVisitNo}" />
	<input type="hidden" name="strMlcNo" value="${newBornTransBean.strMlcNo}" />
	<input type="hidden" name="strAdviceAdmNo" value="${newBornTransBean.strAdviceAdmNo}" />
	<input type="hidden" name="strIsUrban" value="${newBornTransBean.strIsUrban}" />
	<input type="hidden" name="strBedCode" value="${newBornTransBean.strBedCode}" />
	<!--  <input type="hidden" name="strRoomCode" value="${newBornTransBean.strRoomCode}" />-->
	<input type="hidden" name="strBookingDate" value="${newBornTransBean.strBookingDate}" />
	<input type="hidden" name="strFlag" value="1" />
	<input type="hidden" name="strMsApprovalFlag" value="${newBornTransBean.strMsApprovalFlag}" />
	<input type="hidden" name="strMsApprovalStatus" value="${newBornTransBean.strMsApprovalStatus}" />
	<input type="hidden" name="strNewBorn" value="${newBornTransBean.strNewBorn}" />
	<input type="hidden" name="strMotherCrNo" value="${newBornTransBean.strMotherCrNo}" />
	<input type="hidden" name="strMotherAdmissionNo" value="${newBornTransBean.strMotherAdmissionNo}" />
	<input type="hidden" name="strMotherNationalityCode" value="${newBornTransBean.strMotherNationalityCode}" />
	<input type="hidden" name="strMotherReligionCode" value="${newBornTransBean.strMotherReligionCode}" />
	<input type="hidden" name="strMotherName" value="${newBornTransBean.strMotherName}" />
	<input type="hidden" name="strAdmissionCharge" value="${newBornTransBean.strAdmissionCharge}" />
	<input type="hidden" name="strNewBornRegistrationCharge" value="${newBornTransBean.strNewBornRegistrationCharge}" />
	<input type="hidden" name="strPatStatusCode" value="${newBornTransBean.strPatStatusCode}" />
	<input type="hidden" name="strDobTime" value="${newBornTransBean.strPatStatusCode}" />
	<input type="hidden" name="strMotherUnitCode" value="${newBornTransBean.strMotherUnitCode}" />
	<input type="hidden" name="strMotherNationalityCode" value="${newBornTransBean.strMotherNationalityCode}" />
	<input type="hidden" name="strAdmissionAdvance" value="${newBornTransBean.strAdmissionAdvance}" />
	<input type="hidden" name="strConsultantCode" value="${newBornTransBean.strConsultantCode}" />
	<input type="hidden" name="strPatCatCode" value="${newBornTransBean.strPatCatCode}" />
	<input type="hidden" name="strPatStatusCode" value="${newBornTransBean.strPatStatusCode}" />
	<input type="hidden" name="strDeptUnitName" value="${newBornTransBean.strDeptUnitName}" />
	<input type="hidden" name="strWardName" value="${newBornTransBean.strMotherWardName}" />
	<input type="hidden" name="strRoom" value="${newBornTransBean.strRoom}" />
	<input type="hidden" name="strBabyDeptUntName" value="" />
	<input type="hidden" name="strGenderName" value="" />
	<input type="hidden" name="strMotherDeptName" value="${newBornTransBean.strMotherDeptName}" />
	<input type="hidden" name="strBillFlag" value="${newBornTransBean.strBillFlag}" />
	<input type="hidden" name="strAdvanceAmount" value="${newBornTransBean.strAdvanceAmount}" />
	<input type="hidden" name="strAmountChargeFinal" value="" />
	<input type="hidden" name="strTotalAmount" value="" />
	<input type="hidden" name="strAge" value="" />
	<input type="hidden" name="strAgeUnit" value="" />
	<input type="hidden" name="strSexCode" value="" />
	<input type="hidden" name="strOnlineOrNot" value="${newBornTransBean.strOnlineOrNot}" />
	<input type="hidden" name="strConsultantName" value="${newBornTransBean.strConsultantName}">
	<input type="hidden" name="sameBedAsMother" value="1" />
	<input type="hidden" name="strIsBedSharable" value="${newBornTransBean.strIsBedSharable}" />
	<input type="hidden" name="strSaveFlag" value="${newBornTransBean.strSaveFlag}" />
	<input type="hidden" name="strSlNo" />
	<input type="hidden" name="strGravidaNo" />
	<input type="hidden" name="printMode" value="${newBornTransBean.printMode}">
	<input type="hidden" name="isOpenPopUp" value="${newBornTransBean.isOpenPopUp}">
	<input type="hidden" name="strPatientCrNo" value="${newBornTransBean.strPatientCrNo}">
	<input type="hidden" name="strPrintingMode" value="${newBornTransBean.strPrintingMode}">
	<input type="hidden" name="strIsGivenBirth" value="${newBornTransBean.strIsGivenBirth}">	
	<input type="hidden" name="strMaxBabyAllowed" value="${newBornTransBean.strMaxBabyAllowed}">
	<input type="hidden" name="strWardBedStatusFlag" value="${newBornTransBean.strWardBedStatusFlag}">
	<input type="hidden" name="filePath" value="${newBornTransBean.filePath}">
	<input type="hidden" name="setStrMotherCatgrp" value="${newBornTransBean.setStrMotherCatgrp}">
	<input type="hidden" name="strMotherRoomCode" value="${newBornTransBean.strMotherRoomCode}" />
	<input type="hidden" name="strRoomType" value="${newBornTransBean.strRoomType}" />
	<input type="hidden" name="strBedType" value="${newBornTransBean.strBedType}" />
	<input type="hidden" name="strwardType" value="${newBornTransBean.strwardType}" />
	<input type="hidden" name="strCtDate" value="${newBornTransBean.strCtDate}" />
	<input type="hidden" name="strMothAdmDate" value="${newBornTransBean.strMothAdmDate}" />
	
	</div>
</div>	
</html:form>
<script>
selectOnlineBabyList();
</script>
<div id='printableSlip'>
<logic:equal name="newBornTransBean"  property="strSaveFlag" value="1">


<tiles:insert  page="/ipd/transactions/PatientAdmissionTransCNT.cnt?hmode=PRINTSLIP&strCrNo=${newBornTransBean.strPatientCrNo}&strAdmNo=${newBornTransBean.strAdmNo}&duplicateMode=0"/>



</logic:equal>
</div>
</body>
</html>