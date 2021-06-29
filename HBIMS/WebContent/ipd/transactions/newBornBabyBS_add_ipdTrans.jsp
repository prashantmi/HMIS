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
<%@ taglib uri="/WEB-INF/admissionDtl.tld" prefix="aDtl"%>
<%@ taglib uri="/WEB-INF/crNo.tld" prefix="crNo"%>


<meta name="viewport" content="width=device-width, initial-scale=1.0">


<link href="../../hisglobal/bootstrap4.0_newgui/css/bootstrap.css" rel="stylesheet" />
<link href="../../hisglobal/bootstrap4.0_newgui/css/bootstrap-theme.css" rel="stylesheet" />
<link href="../../hisglobal/bootstrap4.0_newgui/datatable/css/dataTables.bootstrap.css" rel="stylesheet" />
 <link href="../../hisglobal/bootstrap4.0_newgui/font-awesome/css/all.css" rel="stylesheet">
  <link href="../../hisglobal/bootstrap4.0_newgui/swAl/swal.css" rel="stylesheet">

 <script type="text/javascript" src="../../hisglobal/bootstrap4.0_newgui/swAl/swal.js"></script>
 <script type="text/javascript" src="../../hisglobal/bootstrap4.0_newgui/js/jquery-3.3.1.min.js"></script>
 <script type="text/javascript" src="../../hisglobal/bootstrap4.0_newgui/datatable/js/jquery.dataTables.js"></script>
 <script type="text/javascript" src="../../hisglobal/bootstrap4.0_newgui/js/bootstrap.min.js"></script>
 <script type="text/javascript" src="../../hisglobal/bootstrap4.0_newgui/datatable/js/dataTables.bootstrap4.js"></script>
 
  <script src="https://unpkg.com/gijgo@1.9.13/js/gijgo.min.js" type="text/javascript"></script>
    <link href="https://unpkg.com/gijgo@1.9.13/css/gijgo.min.css" rel="stylesheet" type="text/css" />


<%-- <link href="../css/transaction.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/calendar-tas.css" rel="stylesheet" type="text/css">--%>

<link href="../css/newlayout.css" rel="stylesheet" type="text/css">

<title>New Born Baby Admission</title>

<script language="JavaScript" src="../../masterutil/js/master.js"></script>
<script language="JavaScript" src="../../hisglobal/js/calendar.js"></script>
<script language="JavaScript" src="../../hisglobal/js/tab.js"></script>
<script language="JavaScript" src="../../hisglobal/js/util.js"></script>
<script language="JavaScript" src="../../hisglobal/js/validationBootstrap.js"></script>
<script language="JavaScript" src="../../hisglobal/js/multirow.js"></script>
<script language="JavaScript" src="../../ipd/js/newBornBaby_BS.js"></script>
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
 
</style>

<body onload="viewN_BS();checkBedIsSharable();removeSpace();openPrintPopUp();checkMsg();" onUnload="closePopUp();">
<html:form action="/transactions/NewBornBabyTransBSCNT.cnt" method="post">

<fieldset>
	
  <legend class='legendHeader' id='nonPrintableLegend'>New Born Baby Admission</legend>
  <div class="legend2" id='nonPrintableLegend2'>
	<!-- <button  type="button" class="btn btn-outline-danger mt-1 btn-circle cancelbtn" onclick="cancelFunc();"> -->
		<button  type="button" class="btn btn-outline-danger mt-1 btn-circle cancelbtn" onclick="openMenu('SIGLEMENUHOME','')">
		<i class="fa fa-ban iround"  title="Cancel"></i>
	</button>	
	<button  id="printbutton" type="button" class="btn btn-outline-primary mt-1 btn-circle printbtn" data-toggle="modal" data-target="#printModal" onclick="" style="display: none;">
		<i class="fa fa-print iround"  title="Print Last Admission Slip"></i>
	</button>
	<button  id="savebutton" type="button" class="btn btn-outline-success mt-1 btn-circle savebtn" data-toggle="modal" data-target="#validateModal" onClick="matchWardRoomCriteria_BS();" style="display: none;" >
		<i class="fa fa-save iround"  title="Save"></i>
	</button>
    												                 
  </div>
  <div class="legend3" id='nonPrintableLegend3' style='display:none;'>
  	<logic:equal name="newBornTransBean" property="strAdmissionCharge" value="1">	
		<font color='red'>Admission Fee: <i class="fa fa-rupee-sign"  title="admFee"></i>
			<label id='admissionChargeId'><bean:write property="strAdmissionChargeValue" name="newBornTransBean" filter="false" /></label>			
		</font>							
	</logic:equal>
	<input type='hidden' name='strAdmissionChargeValue' value="${newBornTransBean.strAdmissionChargeValue}">
	<label id="admissionId" style="display: none"></label>
	
	<logic:equal name="newBornTransBean" property="strNewBornRegistrationCharge" value="1">	
		<font color='red'>Registration Charges:<i class='fa fa-rupee-sign'  title='regFee'></i>
			<label id='RegistrationChargeId'><bean:write property="strNewBornRegistrationChargeVal" name="newBornTransBean" filter="false" /></label>
		</font>							
	</logic:equal>
 	<input type='hidden' name='strNewBornRegistrationChargeVal' value="${newBornTransBean.strNewBornRegistrationChargeVal}">
 	<label id="registrationId" style="display: none"></label>
 	
 	<logic:equal name="newBornTransBean" property="strAdmissionAdvance" value="1">	
		<font color='red'>Advance Amount:<i class='fa fa-rupee-sign'  title='advAmt'></i>
			<label id='advanceAdmissionChargeId'><bean:write property="strAdvanceAmount" name="newBornTransBean" filter="false" /></label>
		</font>							
	</logic:equal>
	<input type='hidden' name='strAdvanceAmount' value="${newBornTransBean.strAdvanceAmount}">
	<input type='hidden' name='strAdmissionAdvanceChargeValue' value="${newBornTransBean.strAdmissionAdvanceChargeValue}">
	
	<font color='red'>Total:<i class='fa fa-rupee-sign'  title='total'></i>
	<label id="TotalID"></label></font>
	<input type='hidden' name='strTotalAmount' value="0">	
	<input type="hidden" name="strTotalChargeVal">
  </div>
  
	
  
	<div class="container-fluid">		
		<div class="viewport" id="viewportDiv">			
			<div class="alert alert-danger" id="errID" style="display: none;"><bean:write name="newBornTransBean" property="strMsgString" /></div>
			<div class="alert alert-success" id="normalMsg" style="display: none;"><bean:write name="newBornTransBean" property="strMsg" filter="false" /></div>
			<div class="alert alert-warning" id="wrnID" style="display: none;"><bean:write name="newBornTransBean" property="strWarningMsg" /></div>
			<div class="prescriptionTile">				
				<div id="patientCrId" style="display: none;">
					<bean:write name="newBornTransBean" property="strCrNo"/>
				</div>
			    <div id="id4">                       
					<pDtl:patDtlNew crNo="${newBornTransBean.strCrNo}" address="false" motherCrFlag="true"></pDtl:patDtlNew>
					<aDtl:addDtlNew crNo="${newBornTransBean.strCrNo}" motherCrFlag="true"></aDtl:addDtlNew>
				</div>
			    <p id="newBabyLink">									           
				   <span class="spanlinkLeft">
				        <a id="motherDelDetUpdate" class="link" onclick="openUpdateMotherDelDet_BS();" href="#"><b>Mother Delivery Details Update</b></a>
				   </span>
				   <span class="spanlinkRight">
				        <a id="patAdmLink" class="link" onclick="openPatAdm();" href="#"><b>Switch To Patient Admission</b></a>				        
				   </span>
			   	</p>
				<div id='goBox'>				 
						<div class="row rowFlex reFlex">							
							<div class="col-sm-2" align="right"><font color="red" id="mandCRId" >*</font><label>Mother CR No.</label></div>
							<div class="col-sm-2">
								<div id="patientCrEdId" >
									<crNo:crNo value="${newBornTransBean.strCrNo}" js="onkeypress='return validateData(event,5);'  onkeyup='goRetFuncNB(event);'" id="strCrNoId" className='form-control'></crNo:crNo>
								</div>													
							</div>
							<div class="col-sm-2">
								<span class="fa fa-search" style="cursor: pointer; cursor: hand;" id="searchPatImg" title="Click here for Patient Search" name='searchPatient' onclick="showPatientListingWindow('6',document.forms[0].strCrNo,'setSelectedCrNo');"></span>
								<a href="#" class="btn btn-sm btn-success go" onclick="return goFuncNB1();"  data-toggle="modal" data-target="#validateModal" >
	                             	GO<i class="fas fa-angle-double-right" aria-hidden="true"></i>
	                             </a></div>
							<div class="col-sm-2"></div>
							<div class="col-sm-4"></div>												
							</div>
						
				</div>
				<div id="MotherOnlineId">
					<bean:write name="newBornTransBean" property="strOnlineBabyList" filter="false"></bean:write>
				</div>	
	
				<div id="newAddressModiId" style="display: none">
					<p class="subHeaders"><i class="fas fa-child" style="font-size: 26px"></i><span class="middle"><label>&nbsp;New Born Entry</label></span></p>
					<bean:write name="newBornTransBean" property="strAddressModi" filter="false" />
				</div>
				
				<div id="PatientOccDtl"><bean:write name="newBornTransBean" property="occupationDetailValues" filter="false" /></div>
				<div id="wardDivId" style="display: none">
					<div class="row rowheight">
				        	<div class="col-sm-10 col-md-10"><p class="subHeaders"><i class="fas fa-clinic-medical isubheaders"></i>&nbsp;Department Ward Details</p></div>			        
				</div>
				<div class='row rowFlex reFlex'  style="display:none">
				    	<div class='col-sm-6'>
				    		<label>Whether allot same bed as Mother</label>
				    	</div>
				    	<div class='col-sm-6'>
				    		<html:checkbox name ="newBornTransBean" property ="strSameBedAsMotherFlg"  value ="0" onchange="roomBedStatus();"/>
						</div>
					</div>
                 	<div class='row rowFlex reFlex'>
				    	<div class='col-sm-2'>
				    		<label>Department<font color="red">*</font></label>
				    	</div>
				    	<div class='col-sm-2'>
				    		<select name="strDeptNameNewBorn" onchange="getUnitComboNB();" class="browser-default custom-select">
								<bean:write property="strDeptValue" name="newBornTransBean" filter="false" />
							</select>
						</div>
						<div class='col-sm-2'>
				    		<label>Unit<font color="red">*</font></label>
				    	</div>
				    	<div class='col-sm-2'>
				    		<div id="unitDivId">
								<select name="strUnitNewBorn" class="browser-default custom-select" onchange="getCons();">
									<bean:write property="strUnitValue" name="newBornTransBean" filter="false" />
								</select>
							</div>
						</div>
						<div class='col-sm-2'>
				    		<label>Consultant<font color="red">*</font></label>
				    	</div>
				    	<div class='col-sm-2'>
				    		<div id="consDivId">
								<select name="strConsNewBorn" class="browser-default custom-select">
									<option value='0'>Select Value</option>
								</select>
							</div>
						</div>
					</div>
					<div class='row rowFlex reFlex'>
				    	<div class='col-sm-2'>
				    		<label>Mother's Ward</label>
				    	</div>
				    	<div class='col-sm-2'>
				    		<div id="motherwardNameId">
								<bean:write name="newBornTransBean" property="strMotherWardName" />
							</div>
						</div>
						<div class='col-sm-2'>
				    		<label>Mother's Room</label>
				    	</div>
				    	<div class='col-sm-2'>
				    		<div id="motherroomBedId">
								<bean:write name="newBornTransBean" property="strRoom" />
							</div>
						</div>
						<div class='col-sm-2'>
				    		<label></label>
				    	</div>
				    	<div class='col-sm-2'>				    		
						</div>
					</div>
					<div class='row rowFlex reFlex'>
				    	<div class='col-sm-2'>
				    		<label>Baby's Ward<font color="red">*</font></label>
				    	</div>
				    	<div class='col-sm-2'>
				    		<div id="wardNameId">						
							</div>
						</div>
						<div class='col-sm-2'>
				    		<label>Baby's Room<font color="red">*</font></label>
				    	</div>
				    	<div class='col-sm-2'>
				    		<div id="roomBedId">						
							</div>
						</div>
						<div class='col-sm-2'>
				    		<label>Case Sheet No.</label>
				    	</div>
				    	<div class='col-sm-2'>
				    		<input type="text" name="strCaseSheetNo" value="${newBornTransBean.strCaseSheetNo }" class="form-control" maxlength="15">				    		
						</div>
					</div>
					<div class='row rowFlex reFlex' style="display:none">
				    	<div class='col-sm-2'>
				    		<label>Admission Type<font color='red'>*</font></label>
				    	</div>
				    	<div class='col-sm-2'>
				    		<select name='strAdmissionType' class='browser-default custom-select' tabindex='1' onChange=''>
								<bean:write name="newBornTransBean" property="strAdmissionTypeValues" filter="false"/>
							</select>
						</div>
						<div class='col-sm-2'>
				    		<label>Relief Fund<font color='red'>*</font></label>
				    	</div>
				    	<div class='col-sm-2'>
				    		<select name='strReliefFund' class='browser-default custom-select' tabindex='1' onChange=''>
								<bean:write name="newBornTransBean" property="strReliefFundValues" filter="false"/>
							</select>
						</div>
						<div class='col-sm-2'>
				    		<label></label>
				    	</div>
				    	<div class='col-sm-2'>				    						    		
						</div>
					</div>
					<div class='row rowFlex reFlex'>
				    	<div class='col-sm-2'>
				    		<label>Remarks</label>
				    	</div>
				    	<div class='col-sm-10'>
				    		<textarea rows="3" cols="25" name="strRemarks" class='form-control' id='remarksId'></textarea>
				    		<div style="position: absolute;top: 0;right: 16px;">
							  <button type="button" onclick="$(this).parent().parent().find('#remarksId').val('');" class="btn btn-sm btn-default reasonOfVisitCleanBtn">
							  	<i class="fa fa-times" aria-hidden="true"></i></button>
							</div>
						</div>						
					</div>
				</div>
				<%-- <div id="chargesDtlId" class='row rowFlex reFlex'>
				    	
				    	<div class='col-sm-2' id="admissionId">
				    		<input type="text" name="strAdmissionChargeValue" value="${newBornTransBean.strAdmissionChargeValue}" class="form-control">
						</div>
						
				    	<div class='col-sm-2' id="admissionAdvanceId">
				    		<input type="text" name="strAdmissionAdvanceChargeValue" value="${newBornTransBean.strAdmissionAdvanceChargeValue}" class="form-control">
						</div>
						
				    	<div class='col-sm-2' id="registrationId">
				    		<input type="text" name="strNewBornRegistrationChargeVal" value="${newBornTransBean.strNewBornRegistrationChargeVal}" class="form-control">				    						    		
						</div>
				</div>
				
				<div class='row rowFlex reFlex' id="TotalID" >
				    	<div class='col-sm-2'>
				    		<label>Total Charges</label>
				    	</div>
				    	<div class='col-sm-10'>
				    		<input type="text" name="strTotalChargeVal" class="form-control">
						</div>						
				</div> --%>		
					<div id="chargesDtlId"></div>
		
	<hr>
	<div class="row rowFlex reFlex">
							<div class="col-sm-10">
							</div>
							<div class="col-sm-2" align="right">
							<i class="fa fa-asterisk"  style="color: red;font-size: smaller;"></i>Fields Mandatory
							</div>
							
							</div>
	</div>
	</div>
	</div>
				
<div>
<%--
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
			<td width="25%" class="LABEL">Case Sheet No.</td>
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
	</div> --%>
	
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
    <input type="hidden" name="strDobTime" value="${newBornTransBean.datetime}" />
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
	<input type="hidden" name="strDob"/>
	<input type="hidden" name="isSingleMenu" value="${newBornTransBean.isSingleMenu}" />
	
	
</div>
</fieldset>
</html:form>

<%-- <div id='printableSlip'>
<logic:equal name="newBornTransBean"  property="strSaveFlag" value="1">

<tiles:insert  page="/ipd/transactions/PatientAdmissionTransCNT.cnt?hmode=PRINTSLIP&strCrNo=${newBornTransBean.strPatientCrNo}&strAdmNo=${newBornTransBean.strAdmNo}&duplicateMode=0"/>

</logic:equal>
</div> --%>
<div class="modal fade" id="printModal" role="dialog" >
  <div class="modal-dialog modal-lg" role="document" style="">
    <div class="modal-content">
    <div class="modal-header"><button type="button" data-dismiss="modal" onclick="modalSlipPrint()" class="btn btn-primary" ><i class="fa fa-print" title="Print Last Slip"></i>&nbsp;Print</button></div>
    <div class="modal-body" id='printableSlip'>
	<logic:equal name="newBornTransBean"  property="strSaveFlag" value="1">
     <tiles:insert  page="/ipd/transactions/PatientAdmissionTransBSCNT.cnt?hmode=PRINTSLIP&strCrNo=${newBornTransBean.strPatientCrNo}&strAdmNo=${newBornTransBean.strAdmNo}&duplicateMode=0"/>
   </logic:equal>
	</div>
       
    </div>
  </div>
</div>
<script>
selectOnlineBabyList();



$('#datetimepicker1').datetimepicker({ footer: true, modal: true,format: 'dd-mmm-yyyy hh:MM TT' });
/* $('#timepicker').timepicker({
    uiLibrary: 'bootstrap4'
}); 
$('#datetimepicker1').datepicker({footer: true, modal: true});*/
</script>



<div id='mainDiv' style='display:none;'>
<div class="fade" id="validateModal"   tabindex="-1" role="dialog" aria-labelledby="validateModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-body"><h5 id='warn'></h5><p id='len'></p></div>
      <div class="modal-footer"><button type="button" class="btn btn-primary" data-dismiss="modal">OK</button>
      </div>
    </div>
  </div>
</div>
</div>

<div id='mainDiv2'>
<div class="modal fade" id="previewModal"    tabindex="-1" role="dialog" aria-labelledby="validateModalLabel" aria-hidden="true">
  <div class="modal-dialog "  role="document">
    <div class="modal-content">
    <div class="modal-header" >
	 <h4 class="modal-title" >Preview & Verify</h4>
		<button type="button" class="close" data-dismiss="modal">×</button></div>
      <div class="modal-body" >
		<div class="row"  id="setPrevModal">
		
		</div>
              
      </div>
      <div class="modal-footer" >
        <button  type="button" class="btn btn-success"  onclick="submit()">Ok&Save</button>

      </div>
    </div>
  </div>
</div>
</div>

</body>
</html>