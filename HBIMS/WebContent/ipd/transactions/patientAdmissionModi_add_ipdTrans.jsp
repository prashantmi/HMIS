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
<title>Patient Admission Modification</title>

<link href="../css/transaction.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/calendar-tas.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/dropdown.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/buttons.css" rel="stylesheet" type="text/css">
<link href="../css/basic.css" rel="stylesheet" type="text/css">


<script language="JavaScript" src="../../masterutil/js/master.js"></script>
<script language="JavaScript" src="../../hisglobal/js/tab.js"></script>
<script language="JavaScript" src="../../hisglobal/js/calendar.js"></script>
<script language="JavaScript" src="../../hisglobal/js/util.js"></script>
<script language="JavaScript" src="../../hisglobal/js/validation.js"></script>
<script language="JavaScript" src="../../hisglobal/js/multirow.js"></script>
<script language="JavaScript" src="../../ipd/js/patientAdmissionModi.js"></script>
<script language="Javascript" src="../js/patientListing.js"></script>
<script language="JavaScript" src="../../ipd/js/patientOccupationDetail.js"></script>
<script language="Javascript" src="../../ipd/js/jquery.js"></script>
<script language="Javascript" src="../../ipd/js/jquery.simplemodal.js"></script>
<!-- <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script> -->
<script type="text/javascript" src="../js/bootstrap.min.js"></script>


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
<body onload="view();openPrintPopUp();showPatientListingWindowforPage('8',document.forms[0].strCrNo);onchangeCountry();" onUnload="closePopUp();">
<html:form action="/transactions/PatientAdmissionModificationTransCNT" method="post">
<div>
<div id="nonPrintable">
	<div class="errMsg" id="errMsg"><bean:write name="patientAdmissionModiTransBean" property="strMsgString"/></div>
	<div class="normalMsg" id="normalMsg"><bean:write name="patientAdmissionModiTransBean" property="strMsg"/></div>
	<div class="warningMsg"><bean:write name="patientAdmissionModiTransBean" property="strWarningMsg"/></div>
	<%--<tag:tab tabLabel="Patient Admission Modification" selectedTab="FIRST" align="center" width="TABLEWIDTH"></tag:tab>--%>

	<table class="TABLEWIDTH" align="center" cellspacing="0">
		<tr><td colspan="4"></td></tr>
		<tr class="HEADER">
			<td ><div align="left">Patient Admission Modification</div></td>
			<td>
						<div align="right">
							<img style="cursor: pointer" name="printLastButton" src="../../hisglobal/images/print_on.gif" onclick="printLastBill();" title="Print Last Bill">
						</div>
					</td>
		</tr>
	</table>
	
	
   <div id="fetchRecordDivId"></div>
		
	
	<table class="TABLEWIDTH" align="center" cellspacing="0" id='searchdata'>
		<tr align="right">
			<td width="59%" class="LABEL" ></td>
			<td class='LABEL' width="85%" align="left"><div align="left"><b>Find By&nbsp;</b>
			<select name='strSearchType' class='comboNormal'>
				<option value='1'>CR No.</option>
				<option value='2'>Patient Name</option>
			</select> 
			<input type='text' name='strSearchString' class="txtFldMax"
					style="" onkeypress="return validateSearchText(event);">
			<img style="cursor: pointer;position: absolute;" src="../../hisglobal/images/btn-search.png"
					title="Search Record" name="Search" onClick="getSearchPatListBySearchforpatientmodification();" 
					onKeyPress="getSearchPatListBySearchforpatientmodification();" width="70"> &nbsp;</div></td>
		</tr>
	</table>
	
	<table class="TABLEWIDTH" align="center"  cellpadding="0">
	
		<tr align="left">
		 	<td width="5%" class="LABEL" ><div id="patientCrLabelId" style="display: block;" align="left">
			<font color="red" id="mandCRId" tabindex='1'>*</font>CR No.</div></td>
			<td class="CONTROL" nowrap="nowrap" width="75%">
			<div id="patientCrEdId" style="display: block;" align="left">
			<crNo:crNo id="strCrNoId" value="${patientAdmissionModiTransBean.strCrNo}" js="onkeypress='return goRetFunc(event);return validateData(event,5);'"></crNo:crNo> 
			<!--  <input name="strCrNo" id=""  class="txtCrNoFormat" maxlength="13" onkeypress="return goRetFunc(event);return validateData(event,5);" align="middle" type="text" value=${patientAdmissionModiTransBean.strCrNo} >--> 
			<!--  <img style="cursor: pointer;" id="searhPatientImageId" src="../../hisglobal/images/viewDetails.gif"
				title="Click here for Patient Search" align="middle" name='searchPatient' 
				onclick="showPatientListingWindow('8',document.forms[0].strCrNo,'setSelectedCrNo');" /> --> 
			<img src="../../hisglobal/images/Go.png" onClick="return goFunc();" align="top"
				style="cursor: pointer; cursor: hand"></div>
			<div id="patientCrId" style="display: none;">
			<!--<bean:write name="patientAdmissionModiTransBean" property="strCrNo"/>--></div>
			</td>
		</tr>
	</table>	
	<table class="TABLEWIDTH" align="center" cellspacing="0" cellpadding="0">	
		<tr id="patInformationIdPlus" style="display: none;" class="HEADER">
			<td width="100%" ><div id="plusImageId">
			<img src="../../hisglobal/images/plus.gif" onClick="viewX();" style="cursor: pointer;"/>
			&nbsp;&nbsp;
			CR No:&nbsp;<bean:write name="patientAdmissionModiTransBean" property="strCrNo"/>
			&nbsp;&nbsp;&nbsp;&nbsp;Patient Name&nbsp;:&nbsp;<label id="patName1"></label></div></td>
		</tr>
		<tr id="patInformationIdMinus" style="display: none;" class="HEADER">
			<td width="100%" >
			<div id="minusImageId">
			<img src="../../hisglobal/images/minus.gif" onClick="viewY();" style="cursor: pointer;"/>&nbsp;&nbsp;
			CR No:&nbsp;<bean:write name="patientAdmissionModiTransBean" property="strCrNo"/>
			&nbsp;&nbsp;&nbsp;&nbsp;Patient Name&nbsp;:&nbsp;<label id="patName2"></label></div></td>
		</tr>
	</table>
	<div id="id4" style="display: none;">
	
	<table class="TABLEWIDTH" align="center" cellspacing="1px">
		<pDtl:patDtl crNo="${patientAdmissionModiTransBean.strCrNo}" address="false"></pDtl:patDtl>
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
	<bean:write name="patientAdmissionModiTransBean" property="strAddressModi" filter="false" /></div>
	<div id="PatientOccId" >
	<table class="TABLEWIDTH" align="center" cellspacing="0">
		<!--<tr>
			<td colspan="2" class="TITLE">
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
			<td colspan="2" class="TITLE">
			<div align="right">Same As Address<input type='checkbox' name='strSameAsAdd'  onClick='sameAsAddress();' >
			</div>
			</td>
		</tr>
	
	-->
	
	</table>
	</div>
	<div id="PatientOccDtl" >
	<table align="center" cellspacing="0px" cellpadding="0px" width="100%">
	<tr>
	<td colspan="4">
	<bean:write name="patientAdmissionModiTransBean" property="occupationDetailValues" filter="false"/>
	</td></tr></table>
	</div>	
	<div id="deptWardId" style="display: none">
	<table class="TABLEWIDTH" align="center" cellspacing="1px">
		<tr>
					<td colspan="3" class="TITLE">Department Ward Details</td>
					<td  class="LABEL" width="5%" style="display:none;">
						<!-- <img src="../../hisglobal/images/Bed_.gif" onClick="bedDetails();" align="middle" 
							 onmouseover="" style="cursor: pointer; cursor: hand"/>-->
							 	<img src='../../hisglobal/images/Bed_.gif' ' style='cursor:hand;cursor:pointer;'  title ='Check Bed Status'  data-toggle='modal'  data-target='#myModal' id='modellink'  onClick ='bedDetails();'>
					</td>
					<td class="LABEL" width="10%" colspan="1" style="display:none;">Bed Status</td>
		</tr>
	</table>
	</div>
	<div id="wardDivId" style="display: none">
	<bean:write property="strDeptUnitWardRoomCatConsulatant" name="patientAdmissionModiTransBean" filter="false" />	
	</div>
	<div id='DeparmentUnitModiId' style="display: none">	
	</div>
	<div id='DeparmentUnitId' style="display: none">	
	</div>
	<div id="remarksId" style="display: none">
	<!--  <table class="TABLEWIDTH" align="center" cellspacing="1px">		
		<tr>
			<td width="25%"  class="LABEL">Remarks</td>
			<td width="75%" colspan="6" class="CONTROL">
			<textarea rows="3" cols="25" name="strRemarks"></textarea></td>
		</tr>
	</table>-->
	</div>
	<div id="csno" style="display: none">
    <table class="TABLEWIDTH" align="center" cellspacing="1px">
		<tr>
			<td width="25%" class="LABEL"><font size="2" color="red" tabindex='1'>*</font>Gate Pass No.</td>
			<td width="25%" class="CONTROL"><input type="text" 
			    name="strCaseSheetNo" value="${patientAdmissionModiTransBean.strCaseSheetNo }" class="txtFldMax" maxlength="15"></td>
			
			<td width="25%" class="LABEL"><div align="right">Admission Type</div></td>
    	   <td width="25%" class="CONTROL">
		   <select name="strAdmissionType" tabindex='0'class="comboNormal" >
 				<bean:write name="patientAdmissionModiTransBean" property="strAdmissionTypeValues" filter="false"/>
		   </select>
		   </td>
		</tr>
	</table>
	</div>
		 <div id="emrgencyDivId" style="display:none">	
			 <table class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="0px">
  			<tr>
    		<td colspan="4" class="TITLE" width="100%"><img name="minus"  src="../../hisglobal/images/minus.gif" 
       		onClick="viewhide('DiagPlusId','DiagMinusId','emgAddressDiv'); " 
       		style="cursor: pointer;display:none;" id="DiagMinusId">
       		<img name="plus" src="../../hisglobal/images/plus.gif" 
       		onClick="viewshow('DiagPlusId','DiagMinusId','emgAddressDiv');" 
       		style="cursor: pointer;display:" id="DiagPlusId">&nbsp;&nbsp;
       		<a style="cursor: pointer; color: " title="Show Previous Diagnosis" 
    		onclick="viewshow('DiagPlusId','DiagMinusId','emgAddressDiv');">
    		Emergency Contact Details</a></td>
  			</tr>
  			</table>
			<!--  <table class="TABLEWIDTH" align="center" cellspacing="0">
				<tr>
					<td colspan="1" class="TITLE" width="100%" >Emergency Contact Details</td>
			    </tr>
			</table>-->
		<div id="emgAddressDiv" style="display: none;">					
				<bean:write name="patientAdmissionModiTransBean" property="strEmgAddress" filter="false"/>
		</div>
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
	<table border="0" class="TABLEWIDTH" align="center" cellspacing="0">
		<tr class="FOOTER">
		    <td><div align='left'><font size="2" color="red" tabindex='1'>**</font>Fields are mandatory if I/II Person Name is entered</div></td>
			<td><font size="2" color="red" tabindex='1'>*</font>Mandatory Fields</td>
		</tr>
		<tr>
			<td align="center" colspan="2">
				<logic:notEmpty name="patientAdmissionModiTransBean" property="strCrNo">
					<!-- <img src="../../hisglobal/images/btn-sv.png" onClick="return validate1();" style="cursor: pointer; cursor: hand" /> -->
					<a href="#" class="button" id="" onClick="return validate1();" ><span class="save">Save</span></a>
				</logic:notEmpty>
				<!--   <img src="../../hisglobal/images/btn-clr.png" onClick="clearRecord();" style="cursor: pointer; cursor: hand" />-->
				<a href="#" class="button" onclick="cancelFunc();"><span class="cancel">Cancel</span></a>	
				<!-- <img src="../../hisglobal/images/btn-ccl.png" onClick=" cancelFunc();" style="cursor: pointer; cursor: hand" /> -->
			</td>
		</tr>
	</table>
	
<%-- <div class="container ">--%>
<div class="modal-container" style="display: none;">
	<div id="myModal" class="modal fade" role="dialog">
	  <div class="modal-dialog modal-lg">
	
	    <!-- Modal content-->
	    <div class="modal-content">
	      <div class="modal-header">
	        <h4 class="modal-title">Bed Dashboard Status</h4>
	        <button type="button" onclick="unloadBootstrap();" class="close" data-dismiss="modal">&times;</button>
	      </div>
	      <div class="modal-body" id="modalSpaceId">
	        
	      </div>
	    </div>
	
	  </div>
	</div>
</div>

	<input type="hidden" name="hmode" />
	<input type="hidden" name="strWardTypeCode" value="${patientAdmissionModiTransBean.strWardTypeCode}" />
	<input type="hidden" name="strBedTypeCode" value="${patientAdmissionModiTransBean.strBedTypeCode}" />
	<input type="hidden" name="strRoomTypeCode" value="${patientAdmissionModiTransBean.strRoomTypeCode}" />
	<input type="hidden" name="strEpisodeCode" value="${patientAdmissionModiTransBean.strEpisodeCode}" />
	<input type="hidden" name="strVisitNo" value="${patientAdmissionModiTransBean.strVisitNo}" />
	<input type="hidden" name="strMlcNo" value="${patientAdmissionModiTransBean.strMlcNo}" />
	<input type="hidden" name="strAdviceAdmNo" value="${patientAdmissionModiTransBean.strAdviceAdmNo}" />
	<input type="hidden" name="strIsUrban" value="${patientAdmissionModiTransBean.strIsUrban}" />
	<input type="hidden" name="strBedCode" value="${patientAdmissionModiTransBean.strBedCode}" />
	<input type="hidden" name="strBookingDate" value="${patientAdmissionModiTransBean.strBookingDate}" />
	<input type="hidden" name="strFlag" value="1" />
	<input type="hidden" name="strMsApprovalFlag" value="${patientAdmissionModiTransBean.strMsApprovalFlag}" />
	<input type="hidden" name="strMsApprovalStatus" value="${patientAdmissionModiTransBean.strMsApprovalStatus}" />
	<input type="hidden" name="strNewBorn" value="${patientAdmissionModiTransBean.strNewBorn}" />
	<input type="hidden" name="strDeptName" value="${patientAdmissionModiTransBean.strDeptName}" />
	<input type="hidden" name="strUnitName" value="${patientAdmissionModiTransBean.strUnitName}" />
	<input type="hidden" name="strAdmNo" value="${patientAdmissionModiTransBean.strAdmNo}" />
	<input type="hidden" name="strBookingDate" value="${patientAdmissionModiTransBean.strBookingDate}" />
	<input type="hidden" name="strDeptUnitName" value="${patientAdmissionModiTransBean.strDeptUnitName}" />
	<input type="hidden" name="strWardName" value="${patientAdmissionModiTransBean.strWardName}" />
	<input type="hidden" name="strRoom" value="${patientAdmissionModiTransBean.strRoom}" />
	<input type="hidden" name="strConsultantName" value="${patientAdmissionModiTransBean.strConsultantName}">
	<input type="hidden" name="strAgeUnit" value="">
	<input type="hidden" name="strSexCode" value="">
	<input type="hidden" name="strAge" value="">
	
	<input type="hidden" name="strAdmDateTime" value="${patientAdmissionModiTransBean.strAdmDateTime}" />
	<input type="hidden" name=strIsIntegratedWithBilling value="${patientAdmissionModiTransBean.strIsIntegratedWithBilling}"/>
	<input type="hidden" name=strIsAdvanceAmountAtAdmission value="${patientAdmissionModiTransBean.strIsAdvanceAmountAtAdmission}"/>
	<input type="hidden" name=strIsAdvanceAmountAtAdmissionTaken value="${patientAdmissionModiTransBean.strIsAdvanceAmountAtAdmissionTaken}"/>
	<input type="hidden" name="strAdvanceAmountDate" value="${patientAdmissionModiTransBean.strAdvanceAmountDate}">
	<input type="hidden" name="strAdvanceAmountReceiptNo" value="${patientAdmissionModiTransBean.strAdvanceAmountReceiptNo}">
	<input type="hidden" name="strAdvanceAmount" value="${patientAdmissionModiTransBean.strAdvanceAmount}">
	<input type="hidden" name="strUnitReq" value="${patientAdmissionModiTransBean.strUnitReq}">
	<input type="hidden" name="strRoomReq" value="${patientAdmissionModiTransBean.strRoomReq}">
	<input type="hidden" name="strSaveFlag" value="${patientAdmissionModiTransBean.strSaveFlag}">
	<input type="hidden" name="strPatientCrNo" value="${patientAdmissionModiTransBean.strPatientCrNo}">
	<input type="hidden" name="strFlagForCheck" value="${patientAdmissionModiTransBean.strFlagForCheck}">
	<input type="hidden" name="strAdmissionChargeAtCounter" value="${patientAdmissionModiTransBean.strAdmissionChargeAtCounter} ">
	<input type="hidden" name="strPatIsUnknown" value="${patientAdmissionModiTransBean.strPatIsUnknown} ">
	
	</div>
</div>
</html:form>
<%--<tag:autoIndex></tag:autoIndex>--%>
<div id='printableSlip'>
<logic:equal name="patientAdmissionModiTransBean"  property="strSaveFlag" value="1">

<tiles:insert  page="/ipd/transactions/PatientAdmissionTransCNT.cnt?hmode=PRINTSLIP&strCrNo=${patientAdmissionModiTransBean.strPatientCrNo}&strAdmNo=${patientAdmissionModiTransBean.strAdmNo}&duplicateMode=1"/>

</logic:equal>
</div>
<script>
function unloadBootstrap(){
	var styleSheet1=document.getElementById('style1');
	styleSheet1.disabled= true ;
}
window.onclick = function(event) {
	var styleSheet1=document.getElementById('style1');
    if (event.target == myModal) {
    	styleSheet1.disabled= true ;
    }
}
</script>

</body>
</html>