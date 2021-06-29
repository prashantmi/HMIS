<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<meta charset="utf-8" /></head>


<link href="../../hisglobal/bootstrap4.0_newgui/css/bootstrap.css" rel="stylesheet" />
<link href="../../hisglobal/bootstrap4.0_newgui/css/bootstrap-theme.css" rel="stylesheet" />
<link href="../../hisglobal/bootstrap4.0_newgui/datatable/css/dataTables.bootstrap.css" rel="stylesheet" />
 <link href="../../hisglobal/bootstrap4.0_newgui/font-awesome/css/all.css" rel="stylesheet">
 
 

 <script type="text/javascript" src="../../hisglobal/bootstrap4.0_newgui/js/jquery-3.3.1.min.js"></script>
 <script type="text/javascript" src="../../hisglobal/bootstrap4.0_newgui/datatable/js/jquery.dataTables.js"></script>
 <script type="text/javascript" src="../../hisglobal/bootstrap4.0_newgui/js/bootstrap.min.js"></script>
 <script type="text/javascript" src="../../hisglobal/bootstrap4.0_newgui/datatable/js/dataTables.bootstrap4.js"></script>

<link href="../css/newlayout.css" rel="stylesheet" type="text/css">


<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>
<%@ taglib uri="/WEB-INF/patientDtl.tld" prefix="pDtl"%>
<%@ taglib uri="/WEB-INF/crNo.tld" prefix="crNo"%>

<link href="../css/transaction.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/calendar-tas.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
<link href="../../hisglobal/css/popup.css" rel="stylesheet"	type="text/css">
<link href="../../hisglobal/css/newpopup.css" rel="stylesheet"	type="text/css">

<title>New Born Baby Admission</title>

<script language="JavaScript" src="../../masterutil/js/master.js"></script>
<script language="JavaScript" src="../../hisglobal/js/calendar.js"></script>
<script language="JavaScript" src="../../hisglobal/js/tab.js"></script>
<script language="JavaScript" src="../../hisglobal/js/util.js"></script>
<script language="JavaScript" src="../../hisglobal/js/validation.js"></script>
<script language="JavaScript" src="../../hisglobal/js/multirow.js"></script>
<script language="JavaScript" src="../../ipd/js/newBornBabyBS.js"></script>
<script language="JavaScript" src="../../ipd/js/patientOccupationDetail.js"></script>
<script language="Javascript" src="../js/patientListing.js"></script>
<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
<script language="Javascript" src="../../hisglobal/js/popup.js"></script>
<script language="Javascript" src="../../hisglobal/js/newpopup.js"></script>


<script type="text/javascript">
var child = null;
var popIndex = 0;
var gblCntrlObj = null;
window.history.forward();

function save()
{
if(checkNumOfBabies())
{
	document.forms[0].hmode.value = "UPDATE";// to be changed..
	document.forms[0].submit();
}
else
{
	// alert already shown..form should not be submitted..
}

}

function validateAndSubmit(){
	
	var hisValidator = new HISValidator("newBornTransBean");  
	hisValidator.addValidation("strCrNo", "req", "CR No. is a Mandatory Field" );
	//hisValidator.addValidation("strCrNo", "minlen=13", "CR No. must be 13 Digits" );
	hisValidator.addValidation("strCrNo","minlen="+document.forms[0].strCrNo.maxLength,"CR No. must be "+document.forms[0].strCrNo.maxLength+" Digits" );
	var retVal = hisValidator.validate(); 
//	document.forms[0].strSaveFlag.value="";
	document.forms[0].strPatientCrNo.value="";
	if(retVal){

		//document.getElementById("savebutton").style.display="";
				document.forms[0].hmode.value="GOUPDATE";
				
				document.forms[0].submit();
				
	}else{
			return false;
		}
		
}

function viewJsp()
{
	if(document.forms[0].strOnlineOrNot.value==2)//Offline 1st tym
	{
		document.getElementById("searchPatImg").style.display="none";
	}
	if(document.forms[0].strOnlineOrNot.value==1)//Online
	{
		document.getElementById("searchPatImg").style.display="";
	}
	
	if(document.getElementsByName("strPatStatusCode")[0].value==12)
		{
			document.forms[0].strCrNo.value="";
			document.getElementById("newModificationId").style.display="none";
			document.getElementById("id5").style.display="none";
			document.getElementById("patientCrEdId").style.display="";
			document.getElementById("patientCrId").style.display="none";
			document.forms[0].strCrNo.focus();
		}
	if(checkCrdef(document.getElementById("strCrNoId"))==false && document.forms[0].strCrNo.value!="")
	{
		
		document.getElementById("patientCrId").style.display="block";
		document.getElementById("plusMomId").style.display="block";
		document.getElementById("patientCrEdId").style.display="none";
		document.getElementById("plusAddModiId").style.display="none";
		document.getElementById("newModificationId").style.display="block";
		document.getElementById("minusAddModiId").style.display="block";
		document.getElementById("newAddressModiId").style.display="";
		document.getElementById("id5").style.display="block";
		document.getElementById("mandCRId").style.display="none";
		document.getElementById("savebutton").style.display="block";
			 
				
	}
	else
	{
		
		SetCursorToTextEnd('strCrNoId');
		document.getElementById("newModificationId").style.display="none";
		document.getElementById("id5").style.display="none";
		document.getElementById("patientCrEdId").style.display="";
		document.getElementById("patientCrId").style.display="none";
		document.getElementById("plusMomId").style.display="none";
		//document.forms[0].strCrNo.focus();
		document.getElementById("mandCRId").style.display="";
	}
}

function showAdmittedBabyDetails()
{
	//document.getElementById("admittedBabyDetails").style.display="";
	//$( "#admittedBabyDetails" ).dialog();
	popup('admittedBabyDetails' , '250','250');
	
}

function cancelPage()
{
hide_popup("admittedBabyDetails");
}

function checkNumOfBabies()
{
	
	//alert("formbean val::"+document.getElementById("strNumberOfChildrenBornFB").value);
	//alert("sel val of combo::"+document.getElementById("strNumberOfChildrenBornCmb").value);
	var flag = true;
	var fbVal =document.getElementById("strNumberOfChildrenBornFB").value;
	if(fbVal != 0)
	{
		var selVal=document.getElementById("strNumberOfChildrenBornCmb").value;
		if(selVal < fbVal)
		{
			alert("Number of children cannot be less than earlier selected value ("+fbVal+").");
			flag=false;
		}
	}
	
	
	
	return flag;
}
</script>
<style>
.alert {
    padding: 0.25rem 1.25rem;
}
</style>
<body onload="viewJsp();checkMsg();">
<html:form action="/transactions/NewBornBabyTransBSCNT.cnt" method="post">

<fieldset>
	
  <legend class='legendHeader' id='nonPrintableLegend'>Mother's delivery details update</legend>
  <div class="legend2" id='nonPrintableLegend2'>
	<button  type="button" class="btn btn-outline-danger mt-1 btn-circle cancelbtn" onclick="cancelFunc();">
		<i class="fa fa-ban iround"  title="Cancel"></i>
	</button>	
	<button  id="printbutton" type="button" class="btn btn-outline-primary mt-1 btn-circle printbtn" data-toggle="modal" data-target="#printModal" onclick="" style="display: none;">
		<i class="fa fa-print iround"  title="Print Last Admission Slip"></i>
	</button>
	<button  id="savebutton" type="button" class="btn btn-outline-success mt-1 btn-circle savebtn" data-toggle="modal" data-target="#validateModal" onClick="save();">
		<i class="fa fa-save iround"  title="Save"></i>
	</button>
    												                 
  </div>
  

<%-- <tag:tab tabLabel="Mother's delivery details update" selectedTab="FIRST" align="center" width="TABLEWIDTH"></tag:tab> --%>


<div class="container-fluid">
   <div class="viewport" id="viewportDiv">
	
	<!--	used for making modality false-->
<!--	<div id="blanket" style="display: none;"></div>

	 <table class="TABLEWIDTH" align="center" cellspacing="1px">
		<tr class="HEADER">
			<td colspan="4">Mother's delivery details update</td>
			
		</tr>
	</table> -->
	 <div class="prescriptionTile">
	            <div class="alert alert-danger" id="errID" style="display: none;"><bean:write name="newBornTransBean" property="strMsgString" /></div>
               	<div class="alert alert-success" id="normalMsg" style="display: none;"></div>
	            <div class="alert alert-success" id="normalMsg" style="display: none;"><bean:write name="newBornTransBean" property="strMsg" /></div>
	            <div class="alert alert-warning" id="wrnID" style="display: none;"><bean:write name="newBornTransBean" property="strWarningMsg" /></div>	
	
				 
						<div class="row rowFlex reFlex" id="patientCrEdId">
							
							<div class="col-sm-2" align="right"><font color="red" id="mandCRId" >*</font><label >Mother CR No.</label></div>
							<div class="col-sm-2" >
								<div >
			                         <crNo:crNo value="${newBornTransBean.strCrNo}" id="strCrNoId" className='form-control'></crNo:crNo>
								</div>													
							</div>
							
							<div class="col-sm-2" id="patientCrEdId" >
								<span class="fa fa-search" style="cursor: pointer; cursor: hand;" id="searchPatImg" title="Click here for Patient Search" name='searchPatient' onclick="showPatientListingWindow('6',document.forms[0].strCrNo,'setSelectedCrNo');"></span>
								<a href="#" class="btn btn-sm btn-success" onClick="return validateAndSubmit();" style="font-size: 1rem;">
	                             	Go&nbsp;<i class="fas fa-angle-double-right"></i>
	                             </a></div>
							<div class="col-sm-2"></div>
							<div class="col-sm-4"></div>										
							</div>
		                   
		                  <div id="patientCrId" style="display: none"><label><font color="red" id="mandCRId">*</font>Mother CR No.</label><bean:write name="newBornTransBean" property="strCrNo"/></div>
		                   
<%-- <div id="patientCrId" style="display: none">	                    
	  <table class="table">
		<tr>
			<td><font color="red" id="mandCRId">*</font>Mother CR No.</td>
			<td><bean:write name="newBornTransBean" property="strCrNo"/></td>
			
		</tr>
	</table> 
	</div> --%>
	<DIV ID="ID4" STYLE="DISPLAY: NONE">
		<TABLE CLASS="TABLEWIDTH" ALIGN="CENTER" CELLSPACING="1PX" CELLPADDING="0PX">
			<TR><TD>
				<LOGIC:NOTEMPTY NAME="NEWBORNTRANSBEAN" PROPERTY="STRCRNO">   	
					<JSP:INCLUDE PAGE="/INPATIENT/INPATIENTDETAIL.CNT?PATCRNO=${NEWBORNTRANSBEAN.STRCRNO}"/>
				</LOGIC:NOTEMPTY>
			</TD></TR>
		</TABLE>
<!--	 <TABLE CLASS="TABLEWIDTH" ALIGN="CENTER" CELLSPACING="1PX">-->
<!--		<PDTL:PATDTL CRNO="${NEWBORNTRANSBEAN.STRCRNO}" ADDRESS="FALSE"></PDTL:PATDTL>-->
<!--	</TABLE> -->
	</DIV>
	<div id="id5" style="display: ">
	
			<div class="row rowheight" id="plusMomId" align="left" style="display: block;">
			<div class="col-sm-10 col-md-10"><p class="subHeaders"><i class="fas fa-plus-circle" onClick="viewX();"></i>&nbsp;&nbsp;Mother Details</p></div>
			<!-- <img src="../../hisglobal/images/plus.gif" onClick="viewX();"
				style="cursor: pointer; cursor: hand" />&nbsp;&nbsp;Mother Details --></div>
			<div class="row rowheight" id="minusMomId" style="display: none;" align="left">
				<div class="col-sm-10 col-md-10"><p class="subHeaders"><i class="fas fa-minus-circle" onClick="viewY();"></i>&nbsp;&nbsp;Mother Details</p></div>
			<!-- <img src="../../hisglobal/images/minus.gif" onClick="viewY();"
				style="cursor: pointer; cursor: hand" />&nbsp;&nbsp;Mother Details --></div>
	</div>
	<div id="momDtlId" style="display:none ">
	<%-- <table class="TABLEWIDTH" align="center" cellspacing="1px">
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
		<tr>
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
	</table> --%>
	
	<div class="row rowFlex reFlex">
	   
	    <div class="col-sm-2" align="Right"><b><label>Mother Name</label></b></div>
		<div class="col-sm-2"><bean:write name="newBornTransBean" property="strMotherName" filter="false"/></div>
		 <div class="col-sm-2" align="Right"><b><label>Department/Unit</label></b></div>
		 <div class="col-sm-4"><bean:write name="newBornTransBean" property="strDeptUnitName"/></div>
		 <div class="col-sm-2"></div>
	</div>
	<div class="row rowFlex reFlex">
		   
		    <div class="col-sm-2" align="Right"><b><label></label>Mother Admission No.</label></b></div>
		    <div class="col-sm-2"><bean:write name="newBornTransBean" property="strMotherAdmissionNo" filter="false" /></div>
	       	<div class="col-sm-2" align="Right"><b><label>Mother Nationality</label></b></div>
	       	<div class="col-sm-2"><bean:write name="newBornTransBean" property="strMotherNationality" filter="false"/></div>
	       	<div class="col-sm-4"></div>
	       
	</div>
	<div class="row rowFlex reFlex">
		   
		    <div class="col-sm-2" align="Right"><b><label></label>Mother Religion</label></b></div>
		    <div class="col-sm-2"><bean:write name="newBornTransBean" property="strMotherReligion" filter="false" /></div>
	       	<div class="col-sm-2" align="Right"><b><label>Treatment Category</label></b></div>
	       	<div class="col-sm-2"><bean:write name="newBornTransBean" property="strTreatmentCategoryName" filter="false" /></div>
	       	<div class="col-sm-4"></div>
	       
	</div>
	</div>	
	
	<div class="" style="color: #666;font-size:1rem;font-weight:600;background-color: transparent;">
	<div class="row admTileHeader">
	    <div class="col-xs-2"></div>
		<div class="col-xs-8"></div>
		<div class="col-xs-2"></div>
	</div>
	</div>
<!--	<div id="MotherOnlineId">-->
<!--	<bean:write name="newBornTransBean" property="strOnlineBabyList" filter="false"></bean:write>-->
<!--	</div>	-->
	<div id="newModificationId" style="display: none">
	<!-- <table class="TABLEWIDTH" align="center" cellspacing="1px">
		<tr>
			<td colspan="4" class="TITLE">
			<div id="plusAddModiId" align="left" style="display: block;">
			<img src="../../hisglobal/images/plus.gif" onClick="view3();"
				style="cursor: pointer;" />&nbsp;&nbsp;New Born Baby Form</div>
			<div id="minusAddModiId" style="display: none;" align="left">
			<img src="../../hisglobal/images/minus.gif" onClick="view4_1();"
				style="cursor: pointer;" />&nbsp;&nbsp;New Born Baby Form</div>
			</td>
		</tr>
	</table> -->
	       <div class="row rowheight" id="plusAddModiId" align="left" style="display: block;">
			<div class="col-sm-10 col-md-10"><p class="subHeaders"><i class="fas fa-plus-circle" onClick="view3();"></i>&nbsp;&nbsp;New Born Baby Form</p></div>
			</div>
			<div class="row rowheight" id="minusAddModiId" align="left" style="display: block;">
			<div class="col-sm-10 col-md-10"><p class="subHeaders"><i class="fas fa-minus-circle" onClick="view4_1();"></i>&nbsp;&nbsp;New Born Baby Form</p></div>
			</div>
	</div>
	<div id="newAddressModiId" style="display: none">
	<bean:write name="newBornTransBean" property="strAddressModi" filter="false" /></div>
	
	<div id="admittedBabyDetails" class="popUpDiv"  style="display: none;" >
 	<bean:write name="newBornTransBean" property="strAdmittedBabyDetails" filter="false" /></div>
 	
 	
                      <div class="row rowFlex reFlex">
                      <hr>
							<div class="col-sm-10">
							</div>
							<div class="col-sm-2" align="right">
							<i class="fa fa-asterisk"  style="color: red;font-size: smaller;"></i>Fields Mandatory
							</div>
							
							</div>

	<%-- <table border="0" class="TABLEWIDTH" align="center" cellspacing="1px">
		<tr class="FOOTER">
			<td colspan="4"><font size="2" color="red">*</font> Mandatory Fields</td>
		</tr>
		<tr>
			<td align="center" colspan="2">
			<logic:notEmpty name="newBornTransBean" property="strCrNo">
				<img src="../../hisglobal/images/btn-sv.png" onClick="save();" style="cursor: pointer; cursor: hand" />
			</logic:notEmpty>
			<img src="../../hisglobal/images/btn-clr.png" onClick="clearRecordNB();" style="cursor: pointer;"/>
			<img src="../../hisglobal/images/btn-ccl.png" onClick="cancelFunc();" style="cursor: pointer;"/></td>
		</tr>
	</table> --%>
	<input type="hidden" name="hmode" />
	<input type="hidden" id="strNumberOfChildrenBornFB" name="strNumberOfChildrenBorn" value="${newBornTransBean.strNumberOfChildrenBorn}">
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
	<input type="hidden" name="strWardName" value="${newBornTransBean.strWardName}" />
	<input type="hidden" name="strRoomTypeCode" value="${newBornTransBean.strRoomTypeCode}" />
	<input type="hidden" name="strDeptCode" value="${newBornTransBean.strDeptCode}" />
	<input type="hidden" name="strConsultantCode" value="${newBornTransBean.strConsultantCode}" />
	<input type="hidden" name="strEpisodeCode" value="${newBornTransBean.strEpisodeCode}" />
	<input type="hidden" name="strVisitNo" value="${newBornTransBean.strVisitNo}" />
	<input type="hidden" name="strMlcNo" value="${newBornTransBean.strMlcNo}" />
	<input type="hidden" name="strAdviceAdmNo" value="${newBornTransBean.strAdviceAdmNo}" />
	<input type="hidden" name="strIsUrban" value="${newBornTransBean.strIsUrban}" />
	<input type="hidden" name="strBedCode" value="${newBornTransBean.strBedCode}" />
	<input type="hidden" name="strRoomCode" value="${newBornTransBean.strRoomCode}" />
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
	<input type="hidden" name="strIsWardRoomCriteriaMatch" value="${newBornTransBean.strIsWardRoomCriteriaMatch}" />
	<input type="hidden" name="strSlNo" />
	<input type="hidden" name="strGravidaNo" />
	<input type="hidden" name="strSaveFlag" value="${newBornTransBean.strSaveFlag}">
	<input type="hidden" name="strPatientCrNo" value="${newBornTransBean.strPatientCrNo}">
	<input type="hidden" name="strPrintingMode" value="${newBornTransBean.strPrintingMode}">
	<input type="hidden" name="strIsGivenBirth" value="${newBornTransBean.strIsGivenBirth}">	
	<input type="hidden" name="strMaxBabyAllowed" value="${newBornTransBean.strMaxBabyAllowed}">
	<input type="hidden" name="strWardBedStatusFlag" value="${newBornTransBean.strWardBedStatusFlag}">
	</div>
	</div>
	</div>
	</fieldset>
</html:form>
<script>
//selectOnlineBabyList();
</script>
<%-- <tag:autoIndex></tag:autoIndex> --%>
</body>
</html>