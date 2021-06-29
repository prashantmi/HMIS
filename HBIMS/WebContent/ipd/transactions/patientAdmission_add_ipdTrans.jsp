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
<title>Patient Admission</title>
<meta charset=utf-8>


<link href="../css/transaction.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/buttons.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/calendar-tas.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/dropdown.css" rel="stylesheet" type="text/css">
<link href="../css/basic.css" rel="stylesheet" type="text/css">

<script language="JavaScript" src="../../masterutil/js/master.js"></script>
<script language="JavaScript" src="../../hisglobal/js/tab.js"></script>
<script language="JavaScript" src="../../hisglobal/js/calendar.js"></script>
<script language="JavaScript" src="../../hisglobal/js/validation.js"></script>
<script language="JavaScript" src="../../hisglobal/js/multirow.js"></script>
<script language="JavaScript" src="../../ipd/js/patientAdmission.js"></script>
<script language="JavaScript" src="../../hisglobal/js/util.js"></script>
<script language="JavaScript"
	src="../../ipd/js/patientOccupationDetail.js"></script>
<script language="Javascript" src="../js/patientListing.js"></script>
<script language="Javascript" src="../../ipd/js/jquery.js"></script> 
<script language="Javascript" src="../../ipd/js/jquery.simplemodal.js"></script>
<!-- <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script> -->
<script type="text/javascript" src="../js/bootstrap.min.js"></script>
</script>


<script language="Javascript">
//window.history.forward();

function test()
{
	document.getElementsByName("strStreet")[0].focus();
}


function view1(obj1,obj2,obj3)
{
		document.getElementById(obj1).style.display="none";
		document.getElementById(obj2).style.display="";
		document.getElementById(obj3).style.display="";
}
function view2(obj1,obj2,obj3)
{
		document.getElementById(obj1).style.display="";
		document.getElementById(obj2).style.display="none";
		document.getElementById(obj3).style.display="none";
}

function getAdmissionCharges() 
{
	if(document.getElementsByName("strAdmissionCharge")[0].value=="1" || document.getElementsByName("strAdvanceDepsoitAtAdmissionCounter")[0].value=="1")
	{
		var hmode = "CHARGEVALUE";
		var url = 'PatientAdmissionTransCNT.cnt?hmode=' + hmode + '&wardCode='+ document.forms[0].strWardCode.value + '&treatmentCategCode='+ document.forms[0].strTreatmentCategoryCode.value;
		ajaxFunction(url, "6");
	}		
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
/* .modal-backdrop{
	 display:none; 
} */


</style>

</head>
<body onload="viewA();openPrintPopUp();onchangeCountry();" onfocus="checkPopUpAndSetDefaultCrNo();" onUnload="closePopUp();">
<html:form action="/transactions/PatientAdmissionTransCNT" method="post">
<div>
<div id="nonPrintable">
	<div class="errMsg" id="errID"><bean:write
		name="paientAdmissionTransBean" property="strMsgString" /></div>
	<div class="normalMsg" id="normalMsg"><bean:write
		name="paientAdmissionTransBean" property="strMsg" filter="false" /></div>
	<div class="warningMsg" id="wrnID"><bean:write
		name="paientAdmissionTransBean" property="strWarningMsg" /></div>
	<%--<tag:tab tabLabel="Patient Admission " selectedTab="FIRST" align ="center" width ="TABLEWIDTH" onlyTabIndexing="0"></tag:tab>--%>
	<table class="TABLEWIDTH" align="center" cellspacing="0px">
		<tr class="HEADER">
			<td><div align="left">Patient Admission</div></td>
			<td>
						<div align="right">
							<img style="cursor: pointer" name="printLastButton" src="../../hisglobal/images/print_on.gif" onclick="printLastBill();" title="Print Last Bill">
						</div>
			</td>
		</tr>
	</table>
	<div id="patDtlID" style="display: none;">
	<table class="TABLEWIDTH" align="center" cellspacing="1px">
		<tr class="HEADER">
			<td colspan="4">
			<div id="plusPatDtl" align="left"><img
				src="../../hisglobal/images/plus.gif" onClick="viewX();"
				style="cursor: pointer;" />&nbsp;&nbsp; CR No:&nbsp;<a
				id="patientCrId1"></a> &nbsp;&nbsp;&nbsp;&nbsp;Patient
			Name&nbsp;:&nbsp;<label id="patName1"></label></div>
			<div id="minusPatDtl" style="display: none;" align="left"><img
				src="../../hisglobal/images/minus.gif" onClick="viewY();"
				style="cursor: pointer;" />&nbsp;&nbsp; CR No:&nbsp;<a
				id="patientCrId2" style="display: none;"></a>
			&nbsp;&nbsp;&nbsp;&nbsp;Patient Name&nbsp;:&nbsp;<label id="patName2"></label></div>
			</td>
		</tr>
	</table>
	</div>
	<div id="crNoId">
	<table class="TABLEWIDTH" align="center" cellspacing="1px">
		<tr>
			<td width="25%" class="LABEL"><font color="red" id="mandCRId">*</font>CR
			No.</td>
			<td class="CONTROL" nowrap="nowrap">
			<div id="patientCrEdId"><crNo:crNo id="strCrNoId"
				value="${paientAdmissionTransBean.strCrNo}"
				js="	
				onkeypress='return goRetFuncNA(event);return validateData(event,5);'"></crNo:crNo>
			<img style="cursor: pointer; cursor: hand;" id="searhPatientImageId"
				src="../../hisglobal/images/viewDetails.gif"
				title="Click here for Patient Search" align="middle"
				name='searchPatient'
				onclick="showPatientListingWindow('4',document.forms[0].strCrNo,'setSelectedCrNo');" />
			<img src="../../hisglobal/images/Go.png"
				onClick="return goFuncNA();" align="top" style="cursor: pointer;">
			</div>
			</td>
		</tr>
	</table>
	</div>
	<div id="id4" style="display: none;">
	<table class="TABLEWIDTH" align="center" cellspacing="1px">
		<pDtl:patDtl crNo="${paientAdmissionTransBean.strCrNo}"
			address="false"></pDtl:patDtl>
	</table>

	</div>
	<div id="newModificationId" style="display: none">
	<table class="TABLEWIDTH" align="center" cellspacing="1px">
		<tr>
			<td colspan="4" class="TITLE"><!-- <div id="plusAddModiId" align="left">
					<img src="../../hisglobal/images/plus.gif" 
									onClick="view3();" style="cursor: pointer; cursor: hand"/>
						New Modification
					</div>
					<div id="minusAddModiId" style="display:none;" align="left">
						<img src="../../hisglobal/images/minus.gif" 
								onClick="view4();" style="cursor: pointer; cursor: hand"/>
								New Modification
					</div> --> Patient Modification Details</td>
		</tr>
	</table>
	</div>
	<div id="newAddressModiId"><bean:write
		name="paientAdmissionTransBean" property="strAddressModi"
		filter="false" /></div>
	<div id="id5" style="display: none">
	<table class="TABLEWIDTH" align="center" cellspacing="1px">
		<tr>
			<td colspan="4" class="TITLE">New Born Baby Status</td>
		</tr>
		<tr>
			<td width="25%" class="LABEL" colspan="1">Mother Name</td>
			<td width="25%" class="CONTROL" colspan="3"><bean:write
				name="paientAdmissionTransBean" property="strMotherName"
				filter="false" /></td>
		</tr>
		<tr>
			<td width="25%" class="LABEL">Mother CR No.</td>
			<td width="25%" class="CONTROL"><bean:write
				name="paientAdmissionTransBean" property="strMotherCrNo"
				filter="false" /></td>
			<td width="25%" class="LABEL">Mother Admission No.</td>
			<td width="25%" class="CONTROL"><bean:write
				name="paientAdmissionTransBean" property="strMotherAdmissionNo"
				filter="false" /></td>
		</tr>
		<tr>
			<td width="25%" class="LABEL">Mother Nationality</td>
			<td width="25%" class="CONTROL"><bean:write
				name="paientAdmissionTransBean" property="strMotherNationality"
				filter="false" /></td>
			<td width="25%" class="LABEL">Mother Religion</td>
			<td width="25%" class="CONTROL"><bean:write
				name="paientAdmissionTransBean" property="strMotherReligion"
				filter="false" /></td>
		</tr>
	</table>
	</div>
	<div id="PatientOccId">
	<table class="TABLEWIDTH" align="center" cellspacing="1px">
		<!--  <tr>
					<td  class="TITLE" width="50%">
					<div id="id1" align="left" style="display:none;">
					<img src="../../hisglobal/images/plus.gif" 
									onClick="view1();" style="cursor: pointer;"/>
						Patient Occupation Details
					</div>
					<div id="id2"  align="left">
						<img src="../../hisglobal/images/minus.gif" 
								onClick="view2();" style="cursor: pointer;"/>
								Patient Occupation Details
					</div>
					</td>
					<td  class="TITLE" width="50%">
					<div align="right">Same As Address
					<input type='checkbox' name='strSameAsAdd'  onClick='sameAsAddress()' >
			</div>
			</td>
				</tr> -->
	</table>
	</div>
	<div id="PatientOccDtl">
	<table align="center" cellspacing="0px" cellpadding="0px" width="100%">
		<tr>
			<td colspan="4"><bean:write name="paientAdmissionTransBean"
				property="occupationDetailValues" filter="false" /></td>
		</tr>
	</table>
	</div>

	<div id="wardDivId" style="display: none">
	<table class="TABLEWIDTH" align="center" cellspacing="0">
		<tr>
			<td colspan="1" class="TITLE" width="70%">Department Ward Details
					<div id='strDeptWardChangeChkId' style='display: none;'>
			        	Change Department & Ward<input type="checkbox" name='strDeptWardChangeChk' onchange="enableDeptWard(this);" value="${paientAdmissionTransBean.strDeptWardChangeChk}"/>
					</div>
			</td>
			<td class="LABEL" width="2%" style="display:none;">
				<img src='../../hisglobal/images/Bed_.gif' ' style='cursor:hand;cursor:pointer;'  title ='Check Bed Status' data-toggle="modal" data-target="#myModal" id='modellink' onClick ='bedDetails();'>
			</td>
			<td class="LABEL" width="10%" id='bedStatusTD' style="display:none;">
			<div id='bedStatusDiv'>Bed Status</div>
			</td>
		</tr>
	</table>
	<div id="DeptUnitDivId"><bean:write
		name="paientAdmissionTransBean" property="strWardBedModi"
		filter="false" /></div>

	<table class="TABLEWIDTH" align="center" cellspacing="1px">
		<tr style="display: none;">
			<td width="25%" colspan="1" class="LABEL">Remarks</td>
			<td width="25%" colspan="3" class="CONTROL"><textarea rows="3"
				cols="25" name="strRemarks"></textarea></td>
		</tr>
	</table>
	</div>
    <div id="csno" style="display: none">
    <table class="TABLEWIDTH" align="center" cellspacing="1px">
		<tr>
			<td width="25%" class="LABEL"><font size="2" color="red" tabindex='1'>*</font>Gate Pass No.</td>
			<td width="25%" class="CONTROL"><input type="text" 
			    name="strCaseSheetNo" value="${paientAdmissionTransBean.strCaseSheetNo }" class="txtFldMax" maxlength="15"></td>

			    <td width="25%" class="LABEL"><div align="right">Admission Type/Status</div></td>
    	   <td width="25%" class="CONTROL">
    	   <select name="strAdmissionType" tabindex='0' class="comboNormal" >
		     <bean:write name="paientAdmissionTransBean" property="strAdmissionTypeValues" filter="false"/>
		   </select>
		   </td>
		</tr>
	</table>
	</div>
	<div id="emrgencyDivId" style="display: none">
	<table class="TABLEWIDTH" align="center" cellspacing="1px"
		cellpadding="0px">
		<tr>
			<td colspan="4" class="TITLE" width="100%"><img name="minus"
				src="../../hisglobal/images/minus.gif"
				onClick="view2('DiagPlusId','DiagMinusId','emgAddressDiv'); "
				style="cursor: pointer; display: none;" id="DiagMinusId"> <img
				name="plus" src="../../hisglobal/images/plus.gif"
				onClick="view1('DiagPlusId','DiagMinusId','emgAddressDiv');"
				style="cursor: pointer; display: " id="DiagPlusId">&nbsp;&nbsp;
			<a style="cursor: pointer; color: " title="Show Previous Diagnosis"
				onclick="view1('DiagPlusId','DiagMinusId','emgAddressDiv');">
			Emergency Contact Details</a></td>
		</tr>
	</table>
	<!--  <table class="TABLEWIDTH" align="center" cellspacing="0">
				<tr>
					<td colspan="1" class="TITLE" width="100%" >Emergency Contact Details</td>
			    </tr>
			</table>-->
	<div id="emgAddressDiv" style="display: none;"><bean:write
		name="paientAdmissionTransBean" property="strEmgAddress"
		filter="false" /></div>
	</div>


	<!--  <div id="admissionId" style="display: none">
	<table class="TABLEWIDTH" align="center" cellspacing="1px">
		<tr>
			<td  class="LABEL" width="25%">Admission Charges</td>
				<td  class="CONTROL" width="25%">
				<input type="text" name="strAdmissionChargeValue" 
				value="${paientAdmissionTransBean.strAdmissionChargeValue }" class="txtFldMin"><img src='/AHIMS/hisglobal/images/INR.png'>
				</td>
				<td  class="LABEL" width="25%"></td>
				<td  class="LABEL" width="25%"></td>
		</tr>
	</table>
	</div>-->
	<div id="saveid">
	<table border="0" class="TABLEWIDTH" align="center" cellspacing="0">
		<tr class="FOOTER">
		<td><div align='left'><font size="2" color="red">**</font> Fields are mandatory if I/II Person Name is entered</div></td>
		<td><font size="2" color="red">*</font> Mandatory
			Fields</td>
		</tr>
		<tr>		
			<td align="center" colspan="2">
			<logic:notEmpty name="paientAdmissionTransBean" property="strCrNo">
				<a href="#" class="button" id="savebutton" onClick="return validate1NA();"  tabindex="1">
					<span class="save">Save</span>
				</a>
				<!-- <img src="../../hisglobal/images/btn-sv.png" onClick="return validate1NA();" style="cursor: pointer; cursor: hand" />-->
			</logic:notEmpty>
			<!--  <img src="../../hisglobal/images/btn-clr.png" onClick="clearRecord();" style="cursor: pointer; cursor: hand" /> 
			<img src="../../hisglobal/images/btn-ccl.png" onClick="cancelFunc();" style="cursor: pointer; cursor: hand;" class="image" />  -->
				<a href="#" class="button"	onClick="clearRecord();"><span class="clear">Clear</span></a> 
				<a href="#" class="button" onclick="cancelFunc();"><span class="cancel">Cancel</span></a>				
			</td>
		</tr>
	</table>
	</div>
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
</div>

	<input type="hidden" name="hmode" />
	<input type="hidden" name="strWardTypeCode" value="${paientAdmissionTransBean.strWardTypeCode}" />
	<input type="hidden" name="strBedTypeCode" value="${paientAdmissionTransBean.strBedTypeCode}" />
	<input type="hidden" name="strWardName" value="${paientAdmissionTransBean.strWardName}" />
	<input type="hidden" name="strRoomTypeCode" value="${paientAdmissionTransBean.strRoomTypeCode}" />
	<input type="hidden" name="strEpisodeCode" value="${paientAdmissionTransBean.strEpisodeCode}" />
	<input type="hidden" name="strVisitNo" value="${paientAdmissionTransBean.strVisitNo}" />
	<input type="hidden" name="strMlcNo" value="${paientAdmissionTransBean.strMlcNo}" />
	<input type="hidden" name="strAdviceAdmNo" value="${paientAdmissionTransBean.strAdviceAdmNo}" />
	<input type="hidden" name="strIsUrban" value="${paientAdmissionTransBean.strIsUrban}" />
	<input type="hidden" name="strBedCode" value="${paientAdmissionTransBean.strBedCode}" />
	<input type="hidden" name="strWard" value="${paientAdmissionTransBean.strWard}" />
	<input type="hidden" name="strRoom" value="${paientAdmissionTransBean.strRoom}" />
	<input type="hidden" name="strBookingDate" value="${paientAdmissionTransBean.strBookingDate}" />
	<input type="hidden" name="strFlag" value="1" />
	<input type="hidden" name="strMsApprovalFlag" value="${paientAdmissionTransBean.strMsApprovalFlag}" />
	<input type="hidden" name="strMsApprovalStatus" value="${paientAdmissionTransBean.strMsApprovalStatus}" />
	<input type="hidden" name="strNewBorn" value="${paientAdmissionTransBean.strNewBorn}" />
	<input type="hidden" name="strMotherCrNo" value="${paientAdmissionTransBean.strMotherCrNo}" />
	<input type="hidden" name="strMotherAdmissionNo" value="${paientAdmissionTransBean.strMotherAdmissionNo}" />
	<input type="hidden" name="strMotherNationalityCode" value="${paientAdmissionTransBean.strMotherNationalityCode}" />
	<input type="hidden" name="strMotherReligionCode" value="${paientAdmissionTransBean.strMotherReligionCode}" />
	<input type="hidden" name="strMotherName" value="${paientAdmissionTransBean.strMotherName}" />
	<input type="hidden" name="strAdmissionCharge" value="${paientAdmissionTransBean.strAdmissionCharge}" />
	<input type="hidden" name="strPatStatusCode" value="${paientAdmissionTransBean.strPatStatusCode}" />
	<input type="hidden" name="strAdviceStatus" value="${paientAdmissionTransBean.strAdviceStatus}" />
	<input type="hidden" name="strNoOfFreePass" value="${paientAdmissionTransBean.strNoOfFreePass}" />
	<input type="hidden" name="strFreePassValid" value="${paientAdmissionTransBean.strFreePassValid}" />
	<input type="hidden" name=strDepartmentName value="${paientAdmissionTransBean.strDepartmentName}" />
	<input type="hidden" name=strIsAdmissionOnline value="${paientAdmissionTransBean.strIsAdmissionOnline}" />
	<input type="hidden" name=strPrimaryCategoryCode value="${paientAdmissionTransBean.strPrimaryCategoryCode}" />
	<input type="hidden" name="strAgeUnit" value="${paientAdmissionTransBean.strAgeUnit}">
	<input type="hidden" name="strSexCode" value="${paientAdmissionTransBean.strSexCode}">
	<input type="hidden" name="strAge" value="${paientAdmissionTransBean.strAge}">
	<input type="hidden" name="strFatherNameMandatoryFlag" value="${paientAdmissionTransBean.strFatherNameMandatoryFlag}">
	<input type="hidden" name="strAdmissionMode" value="${paientAdmissionTransBean.strAdmissionMode}">
	<input type="hidden" name="strConsultantName" value="${paientAdmissionTransBean.strConsultantName}">
	<input type="hidden" name="strDeptUnitName" value="${paientAdmissionTransBean.strDeptUnitName}">
	<input type="hidden" name="strHiddenUnit" value="${paientAdmissionTransBean.strHiddenUnit}">
	<input type="hidden" name="strHiddenRoom" value="${paientAdmissionTransBean.strHiddenRoom}">
	<input type="hidden" name=strIsIntegratedWithBilling value="${paientAdmissionTransBean.strIsIntegratedWithBilling}" />
	<input type="hidden" name=strIsAdvanceAmountAtAdmission value="${paientAdmissionTransBean.strIsAdvanceAmountAtAdmission}" />
	<input type="hidden" name=strIsAdvanceAmountAtAdmissionTaken value="${paientAdmissionTransBean.strIsAdvanceAmountAtAdmissionTaken}" />
	<input type="hidden" name="strAdvanceAmountDate" value="${paientAdmissionTransBean.strAdvanceAmountDate}">
	<input type="hidden" name="strAdvanceAmountReceiptNo" value="${paientAdmissionTransBean.strAdvanceAmountReceiptNo}">
	<input type="hidden" name="strAdvanceAmount" value="${paientAdmissionTransBean.strAdvanceAmount}">
	<input type="hidden" name="strSaveFlag" value="${paientAdmissionTransBean.strSaveFlag}">
	<input type="hidden" name="strPatientCrNo" value="${paientAdmissionTransBean.strPatientCrNo}">
			
	<input type="hidden" name="strAdmNo" value="${paientAdmissionTransBean.strAdmNo}">
	<input type="hidden" name="gblCRValue"/>
	<input type="hidden" name="strPatIsUnknown" value="${paientAdmissionTransBean.strPatIsUnknown}">
	<input type="hidden" name="strMsAppStatus" value="${paientAdmissionTransBean.strMsAppStatus}">
	<input type="hidden" name="strIcuWardType" value="${paientAdmissionTransBean.strIcuWardType}">
	<input type="hidden" name="strPvtWardType" value="${paientAdmissionTransBean.strPvtWardType}">
	<input type="hidden" name="billcount" value="${paientAdmissionTransBean.billcount}">
	<input type="hidden" name="strAdvanceDepsoitAtAdmissionCounter" value="${paientAdmissionTransBean.strAdvanceDepsoitAtAdmissionCounter}">
</div>
<!-- </div> -->
</html:form>
<script>
	try
	{
	if(document.forms[0].strFatherNameMandatoryFlag.value==0)
		document.getElementById("fontIdForFatherName").style.display="none";
	}
	catch(_err)
	{}
	if(document.forms[0].strAdmissionMode.value==2)
		document.getElementById("searhPatientImageId").style.display="none";
</script>
<div id='printableSlip'>
<logic:equal name="paientAdmissionTransBean"  property="strSaveFlag" value="1">


<tiles:insert  page="/ipd/transactions/PatientAdmissionTransCNT.cnt?hmode=PRINTSLIP&strCrNo=${paientAdmissionTransBean.strPatientCrNo}&strAdmNo=${paientAdmissionTransBean.strAdmNo}&duplicateMode=0"/>



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