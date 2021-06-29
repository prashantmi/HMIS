<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ page import="java.io.*" %>
<%@ page import ="java.util.*,java.text.*" %> 

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>

<%@ taglib uri="/WEB-INF/onLineReqDiscountDtl.tld" prefix="onLineDtl"%>
<%@ taglib uri="/WEB-INF/onlineClientDtl.tld" prefix="bDtl"%>
<%@ taglib uri="/WEB-INF/patientDtl.tld" prefix="pDtl"%>
<%@ taglib uri="/WEB-INF/dateTag.tld" prefix="date"%>


<%@ taglib uri="/WEB-INF/crNo.tld" prefix="crNo"%>

<html>
<head>

<meta charset=utf-8>
<title>Cash Collection Offline</title>
<link href="../css/transaction.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/calendar-tas.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/popup.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/newpopup.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/dropdown.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/cr-no-format.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/buttons.css" rel="stylesheet" type="text/css">
<link href="/HIS/hisglobal/css/jquery-ui.css" rel="stylesheet" type="text/css">
<link href="/HIS/hisglobal/css/jqueryExtValidationToolTip.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="../../hisglobal/css/bootstrap.min.css">



 <style>
.creditData{
font-weight:bold;
font-family:monospace;
font-size:15px;

}
th{
font-size:1vw;
font-family:Arial;
color:tomato;
}

#tableBody tr:nth-child(odd) td{
background-color :lightblue;
}
.creditData:hover
{
background-color :#66CCFF !important;
font-family:Arial;
font-size:17px;
transition-duration: 0.5s;

}
#addCreditLetter1:hover
{

width :30px;
height :30px
}

#addCreditLetter1
{
width :20px;
height :20px;
}

#ui-datepicker-div{
width:300px !important;
}
#checkBoxDP
{
 position: absolute;
  left: -9999px;
  top: -9999px;
}
#dpImg
{
cursor:pointer;
}



</style>

</head>
<body onLoad="onLoadLogics();" onfocus="checkPopUp();" onUnload="closePopUp();">
<script language="Javascript" src="../../hisglobal/js/wz_tooltip.js"></script>
<html:form action="/transactions/CreditLetterHandlingCNT" method="post" enctype="multipart/form-data">



	<div id="blanket" style="display: none;"></div>
	<div id="nonPrintable">
	
	<center>
		<div id="errMsg" class="errMsg"><bean:write name="CreditLetterTransBean" property="strErrMsg" filter="false" /></div>
		<div id="normalMsg" class="normalMsg"><bean:write name="CreditLetterTransBean" property="strNormalMsg" filter="false" /></div>
		<div id="warningMsg" class="warningMsg"><bean:write name="CreditLetterTransBean" property="strWarningMsg" filter="false" /></div>		
	</center>

	
	

	<div class="popUpDiv" id="billPrintConfirm" style="display: none;">
	<table bgcolor="white">
		<tr>
			<td>
			<div class='popup' id='billPrintConfirmInner' style="display: block">
			<table width='400'>

				<tr class='HEADER'>
					<th colspan='4' align='left'>&nbsp;Confirmation</th>
				</tr>
				<tr>
					<td class='multiControl'> Are Your Sure to Save it?</td>
					
				</tr>
				
				<tr class='FOOTER'>
					<td colspan='4'>&nbsp;</td>
				</tr>
			</table>
			<center>
			<table width='300' border='0' cellpadding='0' cellspacing='0'>
				<tr>
					<td> <input type="hidden" name="strConfirmationType" value="${CreditLetterTransBean.strConfirmationType}">
					
					<logic:equal name="CreditLetterTransBean"  property="strConfirmationType" value="0">
					<center> <input type="button" name="billConfirmCancel" tabindex="1" value="Cancel" class="btn"  onclick="return confirmCancel();" onblur="document.forms[0].billConfirmOk.focus();" > 
					<input type="button" name="billConfirmOk" class="btn" tabindex="1" value="Ok" onblur="document.forms[0].billConfirmCancel.focus();"   onclick="return confirmOk();">  
					</center>
					</logic:equal>
					<logic:equal name="CreditLetterTransBean"  property="strConfirmationType" value="1">
					<center> <input type="button" name="billConfirmOk" class="btn" tabindex="1" value="Ok" onblur="document.forms[0].billConfirmCancel.focus();"  onclick="return confirmOk();">
					<input type="button" name="billConfirmCancel" tabindex="1" value="Cancel" class="btn" onblur="document.forms[0].billConfirmOk.focus();"   onclick="return confirmCancel();">  
					</center>
					</logic:equal>					
					</td>
				</tr>
			</table>
			</center>
			</div>

			</td>
		</tr>
	</table>
	</div>



<table class="TABLEWIDTH" align="center" cellspacing="1px"
		cellpadding="1px" style="display: none;">
		<tr class="HEADER">
			<td>
			<table width="100%" cellpadding="0" cellspacing="0">
				<tr>
					<td colspan="2">
					<a  id="cashCollection" style="cursor: pointer;display: block;" onclick="openDependentPopup('BillingCNT.cnt?hmode=CASHCOLLECTIONPOPUP',event,250,600);" 
					title="Cash Collection Detail"><font color="white" style=""><u>Total Cash</u></font></a>
					</td>
					<td>
					<div align="right">
					<logic:equal value="2" name="CreditLetterTransBean" property="strCounterMode">
					<html:checkbox
						name="CreditLetterTransBean" property="strIsOnline" value="1"
						onclick="setOnlineOffLineMode(this);"></html:checkbox> Online&nbsp;
					</logic:equal>
					
					<logic:notEqual value="2" name="CreditLetterTransBean" property="strCounterMode">
					<div style="display: none">
					<html:checkbox
						name="CreditLetterTransBean" property="strIsOnline" value="1" 
						onclick="setOnlineOffLineMode(this);"></html:checkbox> Online&nbsp;
						</div>
					</logic:notEqual>
					</div>
					</td>
					<td>
					<div align="right">
	                     Client Amount				
					     (<img src='/HBIMS/hisglobal/images/INR.png'>)
								  <label id='totalCltAmtLabId'>0.00</label>&nbsp;&nbsp;&nbsp;&nbsp;
					     Net Amount
			             (<img src='/HBIMS/hisglobal/images/INR.png'>)
								  <label id='totalRecAmtLabId'>0.00</label>&nbsp;&nbsp;&nbsp;&nbsp;
							<img style="cursor: pointer" name="printLastButton" src="../../hisglobal/images/print_on.gif" onclick="printLastBill();" title="Print Last Bill">
						</div>
					</td>			
				</tr>
			</table>
			</td>
		</tr>
	</table>

	

	<table class="TABLEWIDTH" align="center" cellpadding="1px" cellspacing="1px" >
		<tr style="display: none;">
			<td colspan="1" class="LABEL" width="25%"><b><font color="red">*</font>Hospital Service</b></td>
			<td class="CONTROL" colspan="1" width="25%"><select class="comboNormal" name="strOffLineHospitalService" onchange="getBillService();changeComboClass(this);">
				<bean:write name="CreditLetterTransBean" property="strHospitalServiceDetails" filter="false" />
			</select>&nbsp;</td>
			<td width="25%" class="LABEL" colspan="1"><font color="red">*</font>Billing Services</td>
			<td width="25%" class="CONTROL" colspan="1">
			<div id='billServiceDivId'><select class="comboNormal" name="strOffLineBillingService" onchange="resetTotalAmount(this);">
				<bean:write name="CreditLetterTransBean" property="strBillingServiceDetails" filter="false" />
			</select></div>
			</td>
		</tr>
		<tr>
		<tr class="HEADER"> 
        <td colspan="4">CM Fund Flow</td>
        </tr>
			 <td width="25%" colspan="1" class="LABEL"><div align='right'>&nbsp;<font color="red">*</font>CR No.</div></td>
			 <td width="25%" colspan="1" class="CONTROL">
			 	<div align='left'>
				 	<crNo:crNo id="strCrNoId" name="strCrNo" value="${CreditLetterTransBean.strCrNo}" js="onkeypress='return initGoFunc(event);'"></crNo:crNo>
				 	<img src="../../hisglobal/images/Go.png" style="cursor: hand; cursor: pointer" title="Get Bill Details" name="go" onClick="return goFunc();" onKeyPress="return goFunc();" align="middle"> &nbsp;&nbsp;
				 </div>
			</td>			
			<td style="display:none;" width="25%" class="LABEL" colspan="1"><font color="red">*</font>Request Type</td>
			<td style="display:none;" width="25%" class="CONTROL" colspan="1">			
				<logic:equal name="CreditLetterTransBean" property="strIsRefundReq" value="1">
					<html:select name="CreditLetterTransBean" property="strOffLineRequestType" styleClass="comboNormal"  onchange="getBillService();">
						<html:option value="1">Receipt</html:option>
						<html:option value="2">Refund</html:option>
						<html:option value="3">Estimation</html:option>
					</html:select>
				</logic:equal>			
				<logic:equal name="CreditLetterTransBean" property="strIsRefundReq" value="0">
					<html:select name="CreditLetterTransBean" property="strOffLineRequestType" styleClass="comboNormal"  onchange="getBillService();">
						<html:option value="1">Receipt</html:option>
						<html:option value="3">Estimation</html:option>
					</html:select>
				</logic:equal>			
			 </td>
		</tr>
		</table>
	
	 <div id="offlineDetailsDivId" style="display: none;">
	 
	 <bean:write name="CreditLetterTransBean" property="strPatientDetailsView" filter="false" />
	<!-- off-line start -->
 
	<div id='offlineClientDivId'><bean:write name="CreditLetterTransBean" property="strOfflineClientDetailsView" filter="false" /></div>
	
		
		<div id="offlineDepEpdTreatWardDivId" style="display: none;">
		
		<table class="TABLEWIDTH" align="center" cellpadding="1px"
		cellspacing="1px">
		<tr>
			<td width="25%" class="LABEL"><font color="red">*</font>Raising
			Department</td>
			<td width="25%" class="CONTROL">
			<div id='offlineRaisingDepartmentDivId'><select
				class="comboNormal" name="strOffLineRaisingDepartment"
				onchange="getEpisodeList(this);">
				<bean:write name="CreditLetterTransBean"
					property="strRaisingDepartmentDetails" filter="false" />
			</select></div>
			</td>
			<td width="25%" class="LABEL">Episode</td>
			<td width="25%" class="CONTROL">
			<div id='episodeDivId'><select class="comboNormal"
				name="strOffLineEpisode" onchange='changeEpisodeClass(this)'>
				<bean:write name="CreditLetterTransBean"
					property="strEpisodeDetails" filter="false" />
			</select></div>
			</td>
		</tr>
		<tr>
			<td width="25%" class="LABEL"><font color="red">*</font>Patient Category</td>
			<td width="25%" class="CONTROL">
			<div id='offlineTreatmentCategoryDivId'>
				<select class="comboNormal"  name="strOffLineTreatmentCategory" onchange="poorFreeCheck('strOffLineTreatmentCategory'),getPartAccDtls(document.getElementsByName('strOffLineWard')[0]),chkPaidCredit();resetAmt();">
					<bean:write name="CreditLetterTransBean" property="strTreatmentCategoryDetails" filter="false" />
				</select></div>
			</td>
			<td colspan='2' class="CONTROL">
			<div id='wardDivId' style="display: none">
			<table width="100%" align="center" cellpadding="0" cellspacing="0">
				<tr>
					<td width="50%" class="LABEL"><font color="red">*</font>Charge Type&nbsp;</td>
					<td width="50%" class="CONTROL">
					<div id='offlineWardDivId'><select class="comboNormal"
						name="strOffLineWard"
						onchange="changeWard();">
						<bean:write name="CreditLetterTransBean"
							property="strWardDetails" filter="false" />
					</select></div>
					</td>

				</tr>

			</table>			
			</div>
			</td>

		</tr>

	</table>
	<%-- <div class="popUpDiv" id="modifyLimitDiv" style="display: none;">
	<table bgcolor="white">
		<tr>
			<td>
			<div class='popup' id='modifyLimitDivInner' style="display: block">
			<table width='400'>
				<tr class='HEADER'>
					<th colspan='4' align='left'>&nbsp;Edit Letter Limit</th>
				</tr>
				<tr>
				<td colspan='2' class='LABEL'><font color="red">*</font>Modified Sanctioned Limit</td>
				<td colspan='2' class='CONTROL'><input type="text" id="strmodCreditLetterLimitId" name="strmodCreditLetterLimitId"  onkeypress="return validateData(event,5);" class="txtFldNormal" maxlength="10">
				</td></tr>
				<tr class='FOOTER'>
				<td><img onkeypress="onPressingEnter(this,event)" id="oklimit" style="cursor: pointer;" onclick="" src="../../hisglobal/images/ok.gif"></td>	
				</tr>
				</table>
				</div>
			</td>
		</tr>
	</table>
	</div>--%>
				<%-- <tr>
					<td class="LABEL" id='totalCardAmtDivId1' colspan='4'><font color="red">#</font><font size="2">Final Transaction Amount(with Surcharge)(<img src='/HBIMS/hisglobal/images/INR.png'>)&nbsp;0.00</font></td>
				    </tr>--%>
				<tr>
	
	<div id="specialWardDivId">
	<table class="TABLEWIDTH" align="center" cellpadding="1px"
		cellspacing="1px">
		<tr>
			<td width="25%" class="LABEL">Ward Name</td>
			<td width="75%" class="CONTROL" colspan="3">
			
			<div id='offlineSpecialWardDivId'><select class="comboNormal"
						name="strOffLineSpecialWard"
						onchange="changeWard();">
						<bean:write name="CreditLetterTransBean"
							property="strSpecialWardDetails" filter="false" />
					</select></div>
			</td>
			</tr>
			</table>
			
			</div>
	
	
	</div>
	
	<div id='creditLetterList'>
		<bean:write name="CreditLetterTransBean" property="creditLetterList" filter="false" />
	
	</div>
	
	<div id='offlineBillDetailsDivId' style="display: none"></div>
	<div id='offlineBillTariffDivId' style="display: none"></div>

	<div id='offlineAdmissionCancellationDivId' style="display: none">
	</div>

	<div id='partPayAdvanceDivId'><bean:write
		name="CreditLetterTransBean"
		property="strPartPayAdvanceAmountDetails" filter="false" /></div>
		
    <div id='packageDivId'><bean:write
		name="CreditLetterTransBean"
		property="strPackageDetails" filter="false" /></div>

	
	

	 
	 <div id ="creditDtlsDivId" style="display: block;"><bDtl:onlnCltDtl crNo="${CreditLetterTransBean.strCrNo}" ></bDtl:onlnCltDtl></div>
	 
	 
	<div id="id3" style="display: none"></div>



	

	
 


</div>

	<!--  off-line end --> 
	<table class="TABLEWIDTH" align="center" cellpadding="1px" cellspacing="1px">
		<tr>
			<td>
			<table width='100%' cellspacing="0px">
				<tr class="FOOTER">
					<td width='3%'>
					<div id="plusHelpId" align="center" style="display: block"><img
						style="cursor: hand; cursor: pointer;"
						src="../../hisglobal/images/plus.gif" name="plusHelp"
						align="middle" onclick="showCltDetails('HelpId');" /></div>
					<div id="minusHelpId" style="display: none;"   align="center"><img
						style="cursor: hand; cursor: pointer"
						src="../../hisglobal/images/minus.gif" name="minusHelp"
						onclick="hideCltDetails('HelpId');"></div>
					</td>
					<td>
					<div align="left">Help</div>
					</td>
					<td colspan="2"><font size="2" color="red">*</font> Mandatory Fields</td>
				</tr>
			</table>
			<div id="HelpId" style="display: none;">
			<table width='100%' align="center" cellpadding="1px"
				cellspacing="1px">
				<tr>
					<td class="CONTROL" colspan="2" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; <b><font color="red">RED</font></b>
					Coloured Letters(Letter Numbers) are Expired or InActive</td>
				</tr>
				<tr>
					<td class="CONTROL" colspan="2" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; Kindly don't enter ^ and # in Letter Number Field </td>
				</tr>
				<tr class=FOOTER>
					<td colspan="2"></td>
				</tr>
			</table>
			</div>
			</td>
		</tr>
	</table>
	
	<div id="dialog-message" title="Save" style="display:none;">
  <p>
    <b>Are You Sure to Save it?</b>.
  </p>
</div>
	<br>
<div align="center" id="saveid">					 
							<a href="#" class="button" id="btn-sv" tabindex="1" onClick="return validateSaveLetter();" onkeypress="onKeyPressLogic('1',event);"><span class="save" tabindex="1">Save</span></a>
							<a href="#" class="button" id="btn-clr"	onClick="clearFunc(document.forms[0].strCounterMode.value);" onkeypress="onKeyPressLogic('2',event);"><span class="clear">Clear</span></a> 
							<a href="#" class="button" id="btn-ccl" onClick="cancelFunc();" onkeypress="onKeyPressLogic('3',event);" ><span class="cancel">Cancel</span></a>
					</div>
	
	<input type="hidden" name="hmode" />
	<input type='hidden' name='strPkgServiceFlag' value='0' />
	<input type="hidden" name="strTempCtDate"
		value="${CreditLetterTransBean.strCurrentDate}" />
	<input type="hidden" name="strIpdThirdPartyBenefit"
		value="${CreditLetterTransBean.strIpdThirdPartyBenefit}" />
	<input type="hidden" name="strEmergencyThirdPartyBenefit"
		value="${CreditLetterTransBean.strEmergencyThirdPartyBenefit}" />
	<input type="hidden" name="strOpdThirdPartyBenefit"
		value="${CreditLetterTransBean.strOpdThirdPartyBenefit}" />

	<input type="hidden" name="strOfflineIpdPenaltyVal"
		value="${CreditLetterTransBean.strOfflineIpdPenaltyVal}" />
	<input type="hidden" name="strOfflineOpdPenaltyVal"
		value="${CreditLetterTransBean.strOfflineOpdPenaltyVal}" />
	<input type="hidden" name="strOfflineEmergencyPenaltyVal"
		value="${CreditLetterTransBean.strOfflineEmergencyPenaltyVal}" />

	<input type='hidden' name='strOffLineRefundAdvanceAccountNo' value='0' />


	<%-- current state represents  1 - Online and 2 - Off-line--%>
	<input type="hidden" name="currentState"
		value="${CreditLetterTransBean.currentState}" />
	<input type="hidden" name="currentBserviceId" value="${CreditLetterTransBean.currentBserviceId}" />
	<input type="hidden" name="currentReceiptType" value='1' />
	<input type="hidden" name="currentHospService" value='1' />
	
	<input type="hidden" name="strTempBillNo" value="${CreditLetterTransBean.strTempBillNo}" />
	<input type="hidden" name="strTempReceiptNo" value="${CreditLetterTransBean.strTempReceiptNo}" />


<input type="hidden" name="strIsIpdDiscount" value="${CreditLetterTransBean.strIsIpdDiscount}" />
<input type="hidden" name="strIsOpdDiscount" value="${CreditLetterTransBean.strIsOpdDiscount}" />
<input type="hidden" name="strIsEmergencyDiscount" value="${CreditLetterTransBean.strIsEmergencyDiscount}" />
	
<input type="hidden" name="strCounterMode" value="${CreditLetterTransBean.strCounterMode}" />
<input type="hidden" name="strIsWithoutCrNoRequired" value="${CreditLetterTransBean.strIsWithoutCrNoRequired}" />
<input type="hidden" name="strPrintMessageLimit" value="${CreditLetterTransBean.strPrintMessageLimit}" />

<input type="hidden" name="strFreeCategory" value="${CreditLetterTransBean.strFreeCategory}" />
<input type="hidden" name="strIsApprovalRequired" value="${CreditLetterTransBean.strIsApprovalRequired}" />
<input type="hidden" name="strIsAdvanceRequired" value="${CreditLetterTransBean.strIsAdvanceRequired}" />
<input type="hidden" name="strConsumableCharge" value="${CreditLetterTransBean.strConsumableCharge}" />
<input type="hidden" name="strOfflineTotalPayAmountWithoutConsumable" value="${CreditLetterTransBean.strOfflineTotalPayAmountWithoutConsumable}" />
<input type="hidden" name="strGroupIdForConsumableConcatenated" value="${CreditLetterTransBean.strGroupIdForConsumableConcatenated}" />
<input type="hidden" name="strConsumableChargesGroupId" value="${CreditLetterTransBean.strConsumableChargesGroupId}" />
<input type="hidden" name="strConsumableChargesTariffCode" value="${CreditLetterTransBean.strConsumableChargesTariffCode}" />
<input type="hidden" name="strCreditLetterValidity" value="${CreditLetterTransBean.strCreditLetterValidity}" />
<input type="hidden" name="isOpenPopUp" value="${CreditLetterTransBean.isOpenPopUp}"/>
<input type="hidden" name="filePath" value="${CreditLetterTransBean.filePath}"/>
<input type="hidden" name="printMode" value="${CreditLetterTransBean.printMode}"/>
<input 	type="hidden" name="selectedTab" >
<input type ="hidden" name="strVisitType" value="${CreditLetterTransBean.strVisitType}" />	
<input 	type="hidden" name="strNewCreditLetterAddedFlag" >
<input 	type="hidden" name="strArogyaCatId" value="${CreditLetterTransBean.strArogyaCatId}">
<input 	type="hidden" name="strArogyaTSCatId" value="${CreditLetterTransBean.strArogyaTSCatId}">
<input 	type="hidden" name="strWchCatId" value="${CreditLetterTransBean.strWchCatId}">
<input 	type="hidden" name="strArogyaTSCatCreditLimit" value="${CreditLetterTransBean.strArogyaTSCatCreditLimit}">
<input 	type="hidden" name="strUrgSur" value="${CreditLetterTransBean.strUrgSur}">
<input 	type="hidden" name="strSurCc" value="${CreditLetterTransBean.strSurCc}">
<input 	type="hidden" name="strSurDc" value="${CreditLetterTransBean.strSurDc}">
<input 	type="hidden" name="strSurIc" value="${CreditLetterTransBean.strSurIc}">
<input 	type="hidden" name="strSurId" value="${CreditLetterTransBean.strSurId}">
<input 	type="hidden" name="strSurCc1" value="${CreditLetterTransBean.strSurCc1}">
<input 	type="hidden" name="strSurDc1" value="${CreditLetterTransBean.strSurDc1}">
<input 	type="hidden" name="strSurIc1" value="${CreditLetterTransBean.strSurIc1}">
<input 	type="hidden" name="strSurId1" value="${CreditLetterTransBean.strSurId1}">
<input 	type="hidden" name="strEpisodeFound" value="${CreditLetterTransBean.strEpisodeFound}"/>
<input type="hidden" name="grpid" value="${CreditLetterTransBean.grpid}" />
<input type="hidden" name="defsurlim" value="${CreditLetterTransBean.defsurlim}" />
<input type="hidden" name="strCreditLetterNo" value="${CreditLetterTransBean.strCreditLetterNo}" />
<input type="hidden" name="deleteFlg" value="">
<input type="hidden" name="expireFlg" value="">
<input type="hidden" name="letterDelete" value="">
<input type="hidden" name="modifyFlg" value="">
<input type="hidden" name="strCreditPaymentType" value="">


 

<input 	type="hidden" name="strAlertPromptConfirmFlag" value="0"/>
</div>
</html:form>
  
 


<div id='printableSlip'>
<logic:equal name="CreditLetterTransBean"  property="isOpenPopUp" value="1">
<logic:equal name="CreditLetterTransBean"  property="printMode" value="1">
<logic:present name="CreditLetterTransBean"  property="filePath">
<jsp:include page="billing_receipt_printing_popup.jsp"></jsp:include>
</logic:present>
</logic:equal>
</logic:equal>
</div>				

<script src="/HIS/hisglobal/js/js/jquery.min.js"></script>
<script src=../../hisglobal/js/bootstrap.min.js"></script>
  
<script language="JavaScript" src="../../masterutil/js/master.js"></script>
<script language="JavaScript" src="../../hisglobal/js/tab.js"></script>
<script language="JavaScript" src="../../hisglobal/js/calendar.js"></script>
<script language="JavaScript" src="../../hisglobal/js/multiRowTLD.js"></script>
<script language="JavaScript" src="../../hisglobal/js/multirow.js"></script>
<script language="Javascript" src="../../hisglobal/js/validation.js"></script>
<script language="Javascript" src="../../hisglobal/js/util.js"></script>
<script language="Javascript" src="../../hisglobal/js/popup.js"></script>
<script language="Javascript" src="../../hisglobal/js/newpopup.js"></script>
<script language="Javascript" src="../../hisglobal/js/dropdown.js"></script>
<script language="Javascript" src="../js/billing.js"></script>
<script language="Javascript" src="../js/tariffSearch.js"></script>
<script language="Javascript" src="../js/tariffCodeSearch.js"></script>
<script language="Javascript" src="../js/patientListing.js"></script>
<script language="Javascript" src="../../ipd/js/jquery.js"></script>
<script language="Javascript" src="../../ipd/js/jquery.simplemodal.js"></script>
<script language="JavaScript" src="../../hisglobal/js/jquery-1.10.1.min.js"></script>
<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/jquery-2.0.3.min.js"></script>
<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/jquery.easyui.js"></script>
<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/jquery-ui.js"></script>
<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/jqueryExtValidation.js"></script>
<script src="../js/CreditLetterHandler.js"></script>
</body>
</html>