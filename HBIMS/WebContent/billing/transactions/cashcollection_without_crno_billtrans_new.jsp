<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>
<%@ taglib uri="/WEB-INF/onLineReqDiscountDtl.tld" prefix="onLineDtl"%>
<%@ taglib uri="/WEB-INF/patientDtl.tld" prefix="pDtl"%>
<%@ taglib uri="/WEB-INF/dateTag.tld" prefix="date"%>

<html>
<head><meta charset=utf-8>
<title>Cash Collection Without CR No</title>
<link href="../css/transaction.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet"
	type="text/css">
<link href="../../hisglobal/css/calendar-tas.css" rel="stylesheet"
	type="text/css">

<link href="../../hisglobal/css/popup.css" rel="stylesheet"
	type="text/css">

<link href="../../hisglobal/css/newpopup.css" rel="stylesheet"
	type="text/css">

<link href="../../hisglobal/css/dropdown.css" rel="stylesheet"
	type="text/css">
<link href="../../hisglobal/css/buttons.css" rel="stylesheet" type="text/css">
<script language="JavaScript" src="../../masterutil/js/master.js"></script>
<script language="JavaScript" src="../../hisglobal/js/tab.js"></script>
<script language="JavaScript" src="../../hisglobal/js/calendar.js"></script>
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

<script language="Javascript" src="../js/cashcollection_withoutcr_trans.js"></script>
</head>
<body
	onload="withoutCrNoOnLoadLogics(),loadIntExtPatLogics(),addRows(new Array('strOfflineTariffName','strOfflineTariffDetailsHidden','strOfflineTariffRateUnit','strOfflineTariffQty','strOfflineTariffUnit','strOfflineTariffServiceTax','strOfflineTariffDiscount','strOfflineTariffNetAmount'),new Array('t','t','t','t','s','t','t','t'),'2','1','I'),addRows(new Array('strOfflinePaymentMode','strOfflinePaymentDtls','strOfflineAmount'),new Array('s','t','t'),'3','1','I');openPrintPopUp();"
	onfocus="checkPopUp();" onUnload="closePopUp();">
	
	<script language="Javascript" src="../../hisglobal/js/wz_tooltip.js"></script>
	
	
<html:form action="/transactions/CashCollectionWithoutCrTransCNT" method="post">

	<center>
		<div id="errMsg" class="errMsg"><bean:write name="cashCollectionWithoutCrTransBean" property="strErrMsg" filter="false" /></div>
		<div id="normalMsg" class="normalMsg"><bean:write name="cashCollectionWithoutCrTransBean" property="strNormalMsg" filter="false" /></div>
		<div id="warningMsg" class="warningMsg"><bean:write name="cashCollectionWithoutCrTransBean" property="strWarningMsg" filter="false" /></div>
		<tag:tab tabList="${cashCollectionWithoutCrTransBean.lhm}" selectedTab="WITHOUTCRNO" align="center" width="TABLEWIDTH"></tag:tab>
	</center>
	
	<div id="blanket" style="display: none;"></div>
	

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
					<td> <input type="hidden" name="strConfirmationType" value="${cashCollectionWithoutCrTransBean.strConfirmationType}">
					
					<logic:equal name="cashCollectionWithoutCrTransBean"  property="strConfirmationType" value="0">
					<center> <input type="button" name="billConfirmCancel" tabindex="1" value="Cancel" class="btn"  onblur="document.forms[0].billConfirmOk.focus();"   onclick="return confirmCancel();"> 
					<input type="button" name="billConfirmOk" class="btn" tabindex="1" value="Ok" onblur="document.forms[0].billConfirmCancel.focus();"   onclick="return confirmOk();">  
					</center>
					</logic:equal>
					<logic:equal name="cashCollectionWithoutCrTransBean"  property="strConfirmationType" value="1">
					<center> <input type="button" name="billConfirmOk" class="btn" tabindex="1" value="Ok" onblur="document.forms[0].billConfirmCancel.focus();"   onclick="return confirmOk();">
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

	<div class="popUpDiv" id="payDtlCDDMenu" style="display: none;">
	<table bgcolor="white">
		<tr>
			<td>
			<div class='popup' id='payDtlCDDMenuInner' style="display: block">
			<table width='400'>

				<tr class='HEADER'>
					<th colspan='4' align='left'>&nbsp;Payment Details &gt;&gt;
					Check / DD</th>
				</tr>
				<tr>
					<td class='LABEL'><font color="red">*</font>Bank Name</td>
					<td class='CONTROL'><input type='text'
						name='strPayCDDBankName' class='txtFldMax' tabindex="1"
						onkeypress='return validateData(event,4);' /></td>
					<td class='LABEL'><font color="red">*</font>Cheque / DD No.</td>
					<td class='CONTROL'><input type='text' name='strChequeDDNo' tabindex="1"
						class='txtFldDate' onkeypress="return validateData(event,5);" /></td>
				</tr>
				<tr>
					<td colspan='2' class='LABEL'><font color="red">*</font>Cheque
					/ DD Issue Date</td>
					<td colspan='2' class='CONTROL'><input type="text" tabindex="1"
						class="txtFldDate" maxlength="11" name="strChequeDDDate" tabindex="1"
						value="${cashCollectionWithoutCrTransBean.strCurrentDate }">
					(dd-Mon-yyyy)</td>

				</tr>
				<tr class='FOOTER'>
					<td colspan='4'>&nbsp;</td>
				</tr>
			</table>
			<center>
			<table width='300' border='0' cellpadding='0' cellspacing='0'>
				<tr>
					<td>
					<center><img style="cursor: hand; cursor: pointer"
						src='../../hisglobal/images/btn-sv.png' tabindex="1"
						onClick='return validateCheckDD();'> <img
						style="cursor: hand; cursor: pointer"
						src='../../hisglobal/images/btn-ccl.png' tabindex="1"
						onClick="hidePayDetails('payDtlCDDMenu');"></center>
					</td>
				</tr>
			</table>
			</center>
			</div>

			</td>
		</tr>
	</table>
	</div>


	<div class="popUpDiv" id="payDtlCDMenu" style="display: none;">
	<table bgcolor="white">
		<tr>
			<td>
			<div class='popup' id='payDtlCDMenuInner' style="display: block">
			<table width='400'>
				<tr class='HEADER'>
					<th colspan='4' align='left'>&nbsp;Payment Details &gt;&gt;
					Credit / Debit Card</th>
				</tr>
				<tr>
					<td class='LABEL'><font color="red">*</font>Bank Name</td>
					<td class='CONTROL'><input type='text' name='strPayCDBankName' tabindex="1"
						class='txtFldMax' onkeypress="return validateData(event,4);" /></td>
					<td class='LABEL'><font color="red">*</font>Card No. (Last 4 Digits)</td>
					<td class='CONTROL'><input type='password' name='strCardNo' tabindex="1"
						class='txtFldNormal' maxlength="4" onkeypress="return validateData(event,5);" /></td>
				</tr>
				<tr>
					<td class='LABEL'><font color="red">*</font>Auth No.</td>
					<td class='CONTROL'><input type='text' name='strAuthNo' tabindex="1"
						class='txtFldNormal' onkeypress="return validateData(event,5);" /></td>
					<td class='LABEL'><font color="red">*</font>Auth Date</td>
					<td class='CONTROL'><input type="text" class="txtFldDate" tabindex="1"
						maxlength="11" name="strAuthDate"
						value="${cashCollectionWithoutCrTransBean.strCurrentDate}">
					(dd-Mon-yyyy)</td>
				</tr>
				<tr>
					<td colspan='2' class='LABEL'><font color="red">*</font>Card
					Type</td>
					<td colspan='2' class='CONTROL'><select class='comboNormal' tabindex="1"
						name='strCardType'>
						<option value='0'>Select Value</option>
						<option value='1'>Master</option>
						<option value='2'>Visa</option>
						<option value='3'>Rupay</option>
					</select></td>
				</tr>

				<tr class='FOOTER'>
					<td colspan='4'>&nbsp;</td>
				</tr>
			</table>
			<center>
			<table width='300' border='0' cellpadding='0' cellspacing='0'>
				<tr>
					<td>
					<center><img style="cursor: hand; cursor: pointer"
						src='../../hisglobal/images/btn-sv.png' tabindex="1"
						onClick=' return validateCreditDebit();'> <img
						style="cursor: hand; cursor: pointer"
						src='../../hisglobal/images/btn-ccl.png' tabindex="1"
						onClick="hidePayDetails('payDtlCDMenu')"></center>
					</td>
				</tr>
			</table>
			</center>
			</div>
			</td>
		</tr>
	</table>
	</div>


<div class="popUpDiv" id="payDtlCLTMenu" style="display: none;">
	<table bgcolor="white">
		<tr>
			<td>
			<div class='popup' id='payDtlCLTMenuInner' style="display: block">
			<table width='450'>
				<tr class='HEADER'>
					<th colspan='4' align='left'>&nbsp;Payment Details &gt;&gt;
					Client</th>
				</tr>
				<tr>
					<td class='LABEL'><font color="red">*</font>Client Name</td>
					<td class='CONTROL' colspan="3">
					<select name="strPayCNTClientName" class="comboMax" tabindex="1" >
					<bean:write name="cashCollectionWithoutCrTransBean" property="strClientNameContents" filter="false"/>
					</select></td>
				</tr>
				<tr>
					<td class='LABEL'><font color="red">*</font>Auth No.</td>
					<td class='CONTROL'><input type='text' name='strClientAuthNo' tabindex="1"
						class='txtFldNormal' onkeypress="return validateData(event,5);" /></td>
					<td class='LABEL'><font color="red">*</font>Auth Date</td>
					<td class='CONTROL'><input type="text" class="txtFldDate" tabindex="1"
						maxlength="11" name="strClientAuthDate"
						value="${cashCollectionWithoutCrTransBean.strCurrentDate }" >
					(dd-Mon-yyyy)</td>
				</tr>
				
				<tr class='FOOTER'>
					<td colspan='4'>&nbsp;</td>
				</tr>
			</table>
			<center>
			<table width='300' border='0' cellpadding='0' cellspacing='0'>
				<tr>
					<td>
					<center><img style="cursor: hand; cursor: pointer"
						src='../../hisglobal/images/btn-sv.png' tabindex="1"
						onClick=' return validateClientDetails();'> <img
						style="cursor: hand; cursor: pointer"
						src='../../hisglobal/images/btn-ccl.png' tabindex="1"
						onClick="hidePayDetails('payDtlCLTMenu')"></center>
					</td>
				</tr>
			</table>
			</center>
			</div>
			</td>
		</tr>
	</table>
	</div>


	<div class="popUpDiv" id="tariffDiscountDtls" style="display: none;">
	<table bgcolor="white">
		<tr>
			<td>
			<div class='popup' id='tariffDiscountDtlsInner'
				style="display: block">
			<table width='400'>
				<tr class='HEADER'>
					<th colspan='4' align='left'>&nbsp;Tariff Discount Details</th>
				</tr>
				<tr>
					<td class='LABEL'><font color="red">*</font>Discount / Unit</td>
					<td class='CONTROL'><input type='text'
						name='strOffLineTariffDiscountUnit' class='txtFldMax'
						onkeypress="return validateData(event,7);" /></td>
					<td class='LABEL'><font color="red">*</font>Discount Type</td>
					<td class='CONTROL'><select title="_div_popup_cntl"
						name='strOffLineTariffDiscountType' class='comboNormal'>
						<option value='1'>Fixed</option>
						<option value='2'>Percentage</option>
					</select></td>
				</tr>
				<tr>
					<td class='LABEL'><font color="red">*</font>Discount By</td>
					<td class='CONTROL'><select title="_div_popup_cntl"
						class='comboMax' name="strOffLineTariffDiscountBy">
						<bean:write name="cashCollectionWithoutCrTransBean"
							property="strOfflineDiscountApprovedByDetails" filter="false" />
					</select></td>
					<td class='LABEL'><font color="red">*</font>Discount Reason</td>
					<td class='CONTROL'><select title="_div_popup_cntl"
						class='comboNormal' name="strOffLineTariffDiscountReason"
						onchange="setReasonText();">
						<bean:write name="cashCollectionWithoutCrTransBean"
							property="strOfflineDiscountRemarksDetails" filter="false" />
					</select> <input type="text" name="strOffLineTariffDiscountReasonText"
						class='txtFldNormal' disabled="disabled" /></td>
				</tr>
				<tr>
					<td colspan='2' class='LABEL'><font color="red">*</font>Discount
					Date</td>
					<td colspan='2' class='CONTROL'><input type="text"
						class="txtFldDate" maxlength="11"
						name="strOffLineTariffDiscountDate"
						value="${cashCollectionWithoutCrTransBean.strCurrentDate }">
					(dd-Mon-yyyy)</td>
				</tr>

				<tr class='FOOTER'>
					<td colspan='4'>&nbsp;</td>
				</tr>
			</table>
			<center>
			<table width='300' border='0' cellpadding='0' cellspacing='0'>
				<tr>
					<td>
					<center><img style="cursor: hand; cursor: pointer"
						src='../../hisglobal/images/btn-sv.png'
						onClick='validateWithoutCrNoTariffDiscountDetails();'> <img
						style="cursor: hand; cursor: pointer"
						src='../../hisglobal/images/btn-ccl.png'
						onClick="hideWithoutCrNoTariffDiscountDetails('tariffDiscountDtls');"></center>
					</td>
				</tr>
			</table>
			</center>
			</div>
			</td>
		</tr>
	</table>
	</div>



	<div class="popUpDiv" id="multiRowAdderDivId" style="display: none;">
	<table bgcolor="white">
		<tr>
			<td>

			<div class='popup' id='multiRowAdderDivIdInner'
				style="display: block">
			<table width='400' cellpadding="0" cellspacing="0"
				background="../../hisglobal/images/blank.gif">
				<tr class='HEADER'>
					<th colspan='2' align="left">&nbsp;Add Rows</th>
					<th align="right"><img
						src="../../hisglobal/images/stop.png" align="middle"
						onclick="hideMultiRowAdder('multiRowAdderDivId');"></th>
				</tr>

				<tr>
					<td class='LABEL'><font color="red">*</font>Enter The No. of
					Rows to be Added</td>
					<td width="1">&nbsp;</td>
					<td class='CONTROL'><input type='text'
						name='strOffLineTariffNoOfRows' class='txtFldMin' maxlength="2"
						onkeypress="return validateData(event,5),addTariffRows(this,event,'multiRowAdderDivId');" /></td>

				</tr>

				<tr class='FOOTER'>
					<td colspan='3'>&nbsp;</td>
				</tr>
			</table>
			</div>

			</td>
		</tr>
	</table>
	</div>


	<div id='offlineBillTariffPopupDetailsDivId' style="display: none"
		class='popup'></div>

	<table class="TABLEWIDTH" align="center" cellpadding="1px"
		cellspacing="1px">
		<tr class="HEADER">
			<td colspan="4">
			<a id="cashCollection" style="cursor: pointer;" onclick="openDependentPopup('BillingCNT.cnt?hmode=CASHCOLLECTIONPOPUP',event,250,600);" 
			title="Cash Collection Detail"><font color="white" style=""><u>Total Cash</u></font></a>
			</td>
		</tr>
	</table>
	<logic:equal value="1" name="cashCollectionWithoutCrTransBean" property="strIsWithoutCrNoRequired">
	<table class="TABLEWIDTH" align="center" cellpadding="1px" cellspacing="1px">
		<tr>

			<td class="LABEL" colspan="4">
			
			<html:radio
				name="cashCollectionWithoutCrTransBean" property="strPatientMode" value="1"
				onclick="selectPatientMode(this);">With CR No.</html:radio> <html:radio
				name="cashCollectionWithoutCrTransBean" property="strPatientMode" value="2"
				onclick="selectPatientMode(this);">Without CR No.</html:radio></td>
		</tr>
	</table>
	</logic:equal>
	
	<logic:notEqual value="1" name="cashCollectionWithoutCrTransBean" property="strIsWithoutCrNoRequired">
	<div style="display: none">
	<table class="TABLEWIDTH" align="center" cellpadding="1px" cellspacing="1px">
		<tr>

			<td class="LABEL" colspan="4">
			
			<html:radio
				name="cashCollectionWithoutCrTransBean" property="strPatientMode" value="1"
				onclick="selectPatientMode(this);">With CR No.</html:radio> <html:radio
				name="cashCollectionWithoutCrTransBean" property="strPatientMode" value="2"
				onclick="selectPatientMode(this);">Without CR No.</html:radio></td>
		</tr>
	</table>
	</div>
	</logic:notEqual>
	
	<logic:equal value="1" name="cashCollectionWithoutCrTransBean" property="strPatTypeBoth">
	<table class="TABLEWIDTH" align="center" cellpadding="1px" cellspacing="1px">
		<tr>

			<td class="LABEL" colspan="4">			

			<html:radio
				name="cashCollectionWithoutCrTransBean" property="strPatientType" value="2"
				onclick="selPatTypeLogic(this);" >External</html:radio></td>
		</tr>
	</table>
	</logic:equal>
	
	
	
	
<div id="onlineRequestComboDivId">
<table class="TABLEWIDTH" align="center" cellpadding="1px"
		cellspacing="1px">
		<tr>
		<td width="50%" class="LABEL">
		Online Request No.
		</td>
		
		<td width="50%" class="CONTROL"><select class="comboMax"
				name="strWithoutCrNoOnlineRequestNo"
				onchange="return getWithoutCrNoOnlineRequests(this);">
				<bean:write name="cashCollectionWithoutCrTransBean"
					property="strRequestNoValues" filter="false" />
			</select></td>
		</tr>
		</table>
</div>
	<table class="TABLEWIDTH" align="center" cellpadding="1px"
		cellspacing="1px">
		<tr>
			<td width="25%" class="LABEL"><font color="red">*</font>Request
			Type</td>
			<td width="25%" class="CONTROL"><select class="comboNormal"
				name="strOffLineRequestType"
				onchange="getwithoutCrNoBillService(),changeWithoutCrNoRequestMode(this);">
				<option value='1'>Receipt</option>
				<option value='2'>Refund</option>
			</select></td>
			<td width="25%" class="LABEL"><font color="red">*</font>Billing
			Services</td>
			<td width="25%" class="CONTROL">
			<div id='billServiceDivId'><select class="comboNormal"
				name="strOffLineBillingService" >
				<bean:write name="cashCollectionWithoutCrTransBean"
					property="strBillingServiceDetails" filter="false" />
			</select></div>
			</td>
		</tr>
		
	</table>
	<div id="refundDtlsDivId" style="display: none">
	<table class="TABLEWIDTH" align="center" cellpadding="1px"
		cellspacing="1px">
		<tr class='TITLE'>
			<td>&nbsp;&nbsp;Receipt No.<input type='text' name='strBillNo'
				maxlength="10" class='txtFldMax'
				onkeypress='return validateData(event,5);'
				onkeyup="return initGoBillFunc(event);" /> <img
				style="cursor: hand; cursor: pointer"
				src="../../hisglobal/images/viewDetails.gif"
				title="Click here for Bill Search" align="middle"
				name='searchPatient'
				onclick="showBillListingWindow('setSelectedBillNo');" /> <img
				src="../../hisglobal/images/Go.png"
				style="cursor: hand; cursor: pointer" title="Get Bill Details"
				name="go" onClick="return goBillFunc();"
				onKeyPress="return goBillFunc();" align="middle"></td>

		</tr>
	</table>
	</div>

	<div id="receiptDtlsDivId">
	<table class="TABLEWIDTH" align="center" cellpadding="1px"
		cellspacing="1px">
		<tr>
			<td colspan="4" class="TITLE">Patient Details</td>
		</tr>
		<tr>
		<td class="LABEL">
		<div id="previousCrNoNonMandatoryDivId" >Unique Id</div>
		<div id="previousCrNoMandatoryDivId" style="display: none;" ><font color="red">*</font>Unique Id</div>
		<input type="hidden" name="strIsPreviousCrNoReq" value="${cashCollectionWithoutCrTransBean.strIsPreviousCrNoReq}">
		</td>
		<td colspan="3" class="CONTROL" >
		
		<logic:equal name="cashCollectionWithoutCrTransBean" property="strPreviousCrNoSearch" value="1">
			
			<input type="text" name="strPreviousCrNo" maxlength="10" value="${cashCollectionWithoutCrTransBean.strPreviousCrNo}"  tabindex="${cashCollectionWithoutCrTransBean.strIsPreviousCrNoReq}"  class="txtFldMax"  onkeypress="return validateData(event,8),initWithoutCrNoGoFunc(event);">
		<img src="../../hisglobal/images/Go.png" style="cursor: hand; cursor: pointer" title="Get Previous CR No. Details"
						name="go" onClick="return goWithoutCrNoFunc();" onKeyPress="return goWithoutCrNoFunc();" align="top" > 
		
		</logic:equal>
		
		<logic:equal name="cashCollectionWithoutCrTransBean" property="strPreviousCrNoSearch" value="0">
		<input type="text" name="strPreviousCrNo" maxlength="10" value="${cashCollectionWithoutCrTransBean.strPreviousCrNo}"  tabindex="${cashCollectionWithoutCrTransBean.strIsPreviousCrNoReq}"  class="txtFldMax"  onkeypress="return validateData(event,8);">
		</logic:equal>
	
		</td>
		</tr>
		<tr>
			<td width="25%" class="LABEL" ><font color="red">*</font>Patient
			Name</td>
			<td width="25%" class="CONTROL"><input type='text'  tabindex="1"
				name="strGuarantorName" maxlength="100"
				onkeypress="return validateData(event,4);" onkeyup="javascript:this.value=this.value.toUpperCase();"  class='txtFldMax' disabled="disabled" >
				<input type="hidden" name="strIsDetailsReceived" value="0">
			</td>

		<td width="25%" class="LABEL"><font color="red">*</font>Raising
			Department</td>
			<td width="25%" class="CONTROL"><select class="comboNormal" 
				name="strOffLineRaisingDepartment" disabled="disabled">
				<bean:write name="cashCollectionWithoutCrTransBean"
					property="strRaisingDepartmentDetails" filter="false" />
			</select></td>
			

		</tr>
	 
	</table>
	
	<table class="TABLEWIDTH" cellspacing="0px" align="center">
				<tr class="TITLE">
					<td width='3%'>
					<div id="plusotherDtlsDivId" align="center" style="display: block;"><img
						style="cursor: hand; cursor: pointer;"
						src="../../hisglobal/images/plus.gif" name="plusHelp"
						align="middle" onclick="showCltDetails('otherDtlsDivId');" /></div>
					<div id="minusotherDtlsDivId" style="display: none" align="center"><img
						style="cursor: hand; cursor: pointer"
						src="../../hisglobal/images/minus.gif" name="minusHelp"
						onclick="hideCltDetails('otherDtlsDivId');"></div>
					</td>
					<td>
					<div align="left">Other Details</div>
					</td>
					 
				</tr>
			</table>
	<div id="otherDtlsDivId" style="display: none;">
	
	<table class="TABLEWIDTH" align="center" cellpadding="1px"
		cellspacing="1px">
	
	<tr>
			<td width="25%" class="LABEL">Patient
			Address</td>
			<td width="25%" class="CONTROL"><textarea tabindex="2" name="strGuartorAddress" cols="20" rows="2" ></textarea> 
			</td>
				<td width="25%" class="LABEL">Contact
			No.</td>
			<td width="25%" class="CONTROL"><input type="text" tabindex="2" name="strGuarantorContactNo" maxlength="50" onkeypress="return validateData(event,2);" class="txtFldMax" />
			</td>

		</tr>
		<tr>
			<td width="25%" class="LABEL">
			
			<div id="referringPhysicianNonMandatoryDivId">Referring Physician</div>
		<div id="referringPhysicianMandatoryDivId" style="display: none;"><font color="red">*</font>Referring Physician</div>
		<input type="hidden" name="strIsRefPhysicanReq" value="${cashCollectionWithoutCrTransBean.strIsRefPhysicanReq}"/>
			
			</td><td width="25%" class="CONTROL"><input type="text" class="txtFldMax" name="strReferringPhysician"  tabindex="${cashCollectionWithoutCrTransBean.strIsRefPhysicanReq}" onkeypress="return validateData(event,4);"/> </td>
			<td width="25%" class="LABEL">Referring Physician Contact No.</td>
			<td width="25%" class="CONTROL"><input type="text" class="txtFldMax" name="strReferringPhysicianContactNo"  tabindex="2" onkeypress="return validateData(event,2);"/> </td>

		</tr>
		<tr>
			<td width="25%" class="LABEL">Age</td>
			<td width="25%" class="CONTROL"><input type="text" tabindex="1" name="strAge" maxlength="3" onkeypress="return validateData(event,5);" class="txtFldMin" onkeyup="ageValidation();" />
				<select name="strAgeUnit" class="comboMin" onchange="ageValidation();" tabindex="1" >
				<option value="0">Select Value</option>
				<option value="1">Year(s)</option>
				<option value="2">Month(s)</option>
				<option value="3">Day(s)</option>
								
				</select>
			</td>

		
			<td width="25%" class="LABEL">Gender</td>
			<td width="25%" class="CONTROL"><select name="strGender" class="comboMin" tabindex="1" >
			<bean:write name="cashCollectionWithoutCrTransBean" property="strGenderList" filter="false"/>
			</select>
			</td>

		</tr>
	
	</table>
	
	</div>
	
	
	</div>
	<div id="onlineRequestTariffDivId" style="display: none">
	</div>
	<div id='receiptTariffDivId'>
	<table class="TABLEWIDTH" align="center" cellpadding="1px"
		cellspacing="1px">
		<tr>
			<td colspan='8'>
			<table cellpadding="0" cellspacing="0" width="100%">
				<tr>
					<td class="TITLE" width='50%'><a onclick="showTariffSearchPopupOnClick();"><font color="white" style="cursor: pointer;" ><u>Tariff Detail</u></font></a></td>
					<td width='25%' class='TITLE'>
					<div id='trfCodeDivId' align="right"> Tariff Code <input type="text" class="txtFldMin" maxlength="6" name="strTariffCode" id="strTariffCode" tabindex="1"    onkeypress="return validateData(event,8);" onkeyup="getTariffByCode(this , event),showTariffCodeSearchPopup(event,'strTariffCode','0');" >
					</div>
					</td>
					<td width='50%' class='TITLE'>
					<div id='groupDivId' align="right"><b>Group</b> <select tabindex="2"
						name="strOffLineGroup" class="comboNormal"
						>
						<bean:write name="cashCollectionWithoutCrTransBean"
							property="strOfflineGroupDetails" filter="false" />
					</select></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>

			<td class="multiLabel" width="19%"><font color="red">*</font>Tariff
			Name</td>
			<td class="multiLabel" width="8%">Rate / Unit</td>
			<td class="multiLabel" width="7%"><font color="red">*</font>Qty.</td>
			<td class="multiLabel" width="10%"><font color="red">*</font>Unit</td>
			<td style="display:none" class="multiLabel" width="8%">S. Tax (%)<input type="hidden"
				name="strOfflineTotalServiceTaxAmount" value="0"></td>
			<td class="multiLabel" width="8%">Dis. / Unit<input
				type="hidden" name="strOfflineTotalDiscountAmount" value="0"></td>
			<td class="multiLabel" width="9%">Net Amt. (<img src='../../hisglobal/images/INR.png'>)<input
				type="hidden" name="strOfflineTotalActualTariffAmount" value="0">
			</td>
			<td class="multiLabel" width="3%"><img
				style="cursor: hand; cursor: pointer"
				src="../../hisglobal/images/plus.gif"
				onclick="addRows(new Array('strOfflineTariffName','strOfflineTariffDetailsHidden','strOfflineTariffRateUnit','strOfflineTariffQty','strOfflineTariffUnit','strOfflineTariffServiceTax', 'strOfflineTariffServiceTaxAmtVal','strOfflineTariffDiscount','strOfflineTariffDiscountAmtVal' ,'strOfflineTariffNetAmount','strOfflineTotalActualTariffAmtVal'),new Array('t','t','t','t','s','t','t','t','t','t','t'),'2','1','R');"></td>
		</tr>
	</table>
	<div id='id2'></div>

	</div>
	<div id="consumableChargeDiv">
	</div>
	 <div id="chargeDtlDiv" style="display: none;" class="popUpDiv">
	 <table bgcolor="white">
		<tr>
		<td>
		 	<table width='200' cellpadding="1px" cellspacing="1px"> 
			<tr class="HEADER">
			<th colspan='4' align='left' width='95%'>&nbsp;Charge Details</th>
			<th width="5%"><img	src="../../hisglobal/images/stop.png" align="middle" onclick="hideChargeDtl();"></th>
			</tr>
			<tr>
					<td colspan='3' class="LABEL">Tariff Charges</td>
					<td colspan='3' class="CONTROL"><div id="tariffCost"></div></td>
			</tr>	
			<tr>
				<td colspan='3' class="LABEL">Consumable Charges </td>
				<td colspan="3" class="CONTROL"><div id="consuambleCost"></div></td>
			</tr>
			</table>
		</td>
		</tr>
	 </table>
	 </div>

	<div id='withoutCrNoRefundTariffDivId' style="display: none"></div>

	<div id="otherRefundDetailsDivId" style="display: none">
	<table class='TABLEWIDTH' border="0" cellpadding="1px"
		cellspacing="1px" align="center">
		<tr>
			<td width='24%' class='LABEL'><font color="red">*</font>Refund
			By:</td>
			<td width="76%" class="CONTROL"><select tabindex="1"
				name="strOffLineRefundBy">
				<bean:write name="cashCollectionWithoutCrTransBean"
					property="strOfflineDiscountApprovedByDetails" filter="false" />
			</select></td>
		</tr>
		<tr>
			<td width="24%" class="LABEL"><font color="red">*</font>Refund
			Reason:</td>
			<td width="76%" class="CONTROL"><select tabindex="1"
				name="strOffLineRefundReason" onChange="setRefundRemarksText();">
				<bean:write name="cashCollectionWithoutCrTransBean"
					property="strOfflineDiscountRemarksDetails" filter="false" />
			</select>others specify: <input name="strOffLineRefundReasonText" type="text" tabindex="1"
				class="txtFldMax" value="" disabled="disabled"></td>
		</tr>
		<tr>
		<td class="LABEL" width="25%"> 
		Refund Date :
		</td>
		<td width="75%" class="CONTROL">
		<bean:write   property="strCurrentDate" name="cashCollectionWithoutCrTransBean" />
		
		<input type="hidden" name="strOfflineRefundDate"   value="${cashCollectionWithoutCrTransBean.strCurrentDate}">
				  
		</td>
		</tr>
	</table>
	</div>


	<div id="paymentModeDtlsDivId">
	<table class="TABLEWIDTH" align="center" cellpadding="1px"
		cellspacing="1px">
		<tr>
			<td colspan="4" class="TITLE">Payment Mode</td>
		</tr>
	</table>

	<table class="TABLEWIDTH" align="center" cellspacing="1px">
		<tr>
			<td class="multiLabel" width="20%">Payment
			Mode</td>
			<td class="multiLabel" width="40%">Payment
			Details</td>
			<td class="multiLabel" width="20%"><font color="red">*</font>Amount</td>
			<td class="multiLabel" width="3%"><img
				style='cursor: hand; cursor: pointer'
				src="../../hisglobal/images/plus.gif"
				onclick="checkForOffLinePaymentLimit('0');"></td>
		</tr>
	</table>
	<div id="id3"></div>

	</div>

	<table class="TABLEWIDTH" align="center" cellpadding="1px"
		cellspacing="1px">
		<tr>

			<td width="60%" class="LABEL">Total Amount (<img src='../../hisglobal/images/INR.png'>)</td>
			<td width="15%" class="CONTROL" style="font-weight: bold">
			<div id='totalRecAmtDivId'>0.00</div>
			<input
				type='hidden' name='strOfflineTotalRecAmount'
				id='strOfflineTotalRecAmount' value='0.00'>
			</td>
		</tr>

	</table>

	<table class="TABLEWIDTH" align="center" cellpadding="1px"
		cellspacing="1px">
		<tr>

			<td width="60%" class="LABEL">Net Payment / Refund Amount (<img src='../../hisglobal/images/INR.png'>)</td>
			<td width="15%" class="CONTROL" style="font-weight: bold">
			<div id='offlineTotalPayAmtDivId'>0.00</div>
			<input
				type='hidden' name='strOfflineTotalPayAmount'
				id='strOfflineTotalPayAmount' value='0.00'>
			</td>
		</tr>
	</table>

	<table class="TABLEWIDTH" align="center" cellpadding="1px"
		cellspacing="1px">
		<tr>

			<td width="60%" class="LABEL">Change (<img src='../../hisglobal/images/INR.png'>)</td>
			<td width="15%" class="CONTROL" style="color: red; font-weight: bold">
			<div id='offlineRefundAmtDivId'>0.00</div>
			<input
				type='hidden' name='strOfflineRefundAmount'
				id='strOfflineRefundAmountDivId' value='0.00'>
			</td>
		</tr>
	</table>

	<div id="tariffNamePartDivId">
	<table class="TABLEWIDTH" align="center" cellpadding="1px"
		cellspacing="1px">
		<tr>

			<td width="15%" class="CONTROL"
				style="color: blue; font-weight: bold">
			<div id="tariffFullNameDiv"></div>
			</td>
		</tr>

	</table>

	</div>
	<table class="TABLEWIDTH" align="center" cellpadding="1px"
		cellspacing="1px">
		<tr>
			<td>
			<table width='100%' cellspacing="0px">
				<tr class="FOOTER">
					<td width='3%'>
					<div id="plusHelpId" align="center" style="display: block;"><img
						style="cursor: hand; cursor: pointer;"
						src="../../hisglobal/images/plus.gif" name="plusHelp"
						align="middle" onclick="showCltDetails('HelpId');" /></div>
					<div id="minusHelpId" style="display: none;" align="center"><img
						style="cursor: hand; cursor: pointer"
						src="../../hisglobal/images/minus.gif" name="minusHelp"
						onclick="hideCltDetails('HelpId');"></div>
					</td>
					<td>
					<div align="left">Help</div>
					</td>
					<td colspan="2"><font size="2" color="red">*</font> Mandatory
					Fields</td>
				</tr>
			</table>
			<div id="HelpId" style="display: none;">
			<table width='100%' align="center" cellpadding="1px"
				cellspacing="1px">
				<tr>
					<td class="CONTROL">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Press <b>Alt+Ctrl+S</b>
					in Off-Line Data's Tariff Name Text Box To Search Tariff</td>
				</tr>
				<tr>
					<td class="CONTROL">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Press <b>Alt+Ctrl+R</b>
					in Off-Line Data's Tariff Name Text Box To Add More than One Row</td>
				</tr>
				<tr class=FOOTER>
					<td></td>
				</tr>
			</table>
			</div>
			</td>
		</tr>
	</table>
	<!-- 
<div id="saveid">
	<table border="0" class="TABLEWIDTH" align="center" cellpadding="1px"
		cellspacing="1px">
		<tr>

			<td align="center"><img style="cursor: hand; cursor: pointer" tabindex="1"
				src="../../hisglobal/images/btn-sv.png" onkeypress="onKeyPressLogic1('1',event);" 
				onClick="return saveWithouCrNoDtls();" title="Save Record" />
			<img style="cursor: hand; cursor: pointer" onkeypress="onKeyPressLogic1('2',event);" 
				src="../../hisglobal/images/btn-clr.png" tabindex="1"
				onClick="clearBillFunc();" title="Clear Content" />
					<img style="cursor: hand; cursor: pointer" tabindex="1"
				src="../../hisglobal/images/btn-ccl.png" onkeypress="onKeyPressLogic1('3',event);" 
				onClick="cancelFunc();" title="Cancel Process" /></td>
		</tr>
	</table>
</div>

 -->
<br>
<div align="center" id="saveid">					 
							<a href="#" class="button" id="" onkeypress="onKeyPressLogic1('1',event);" 
							onClick="return saveWithouCrNoDtls();"><span class="save">Save</span></a>
							<a href="#" class="button" onkeypress="onKeyPressLogic1('2',event);" 
							onClick=onClick="clearBillFunc();"><span class="clear">Clear</span></a> 
							<a href="#" class="button" onkeypress="onKeyPressLogic1('3',event);" 
							onClick="cancelFunc();"><span class="cancel">Cancel</span></a>
					</div>
	<input type="hidden" name="hmode" />
	<input type="hidden" name="strOffLineHospitalService"
		value="${cashCollectionWithoutCrTransBean.strOffLineHospitalService}" />
	<input type="hidden" name="strOffLineTreatmentCategory"
		value="${cashCollectionWithoutCrTransBean.strOffLineTreatmentCategory}" />
	<input type="hidden" name="strTempCtDate"
		value="${cashCollectionWithoutCrTransBean.strCurrentDate}" />

	<input type="hidden" name="currentBserviceId" value='1' />
	<input type="hidden" name="currentReceiptType" value='1' />
	<input type="hidden" name="currentMode" value='0' />
	

	<input type="hidden" name="strTempBillNo" value="${cashCollectionWithoutCrTransBean.strTempBillNo}" />
	<input type="hidden" name="strTempReceiptNo" value="${cashCollectionWithoutCrTransBean.strTempReceiptNo}" />

<input type="hidden" name="strIsOpdDiscount" value="${cashCollectionWithoutCrTransBean.strIsOpdDiscount}" />

<input type="hidden" name="strCounterMode" value="${cashCollectionWithoutCrTransBean.strCounterMode}" />
<input type="hidden" name="strIsWithoutCrNoRequired" value="${cashCollectionWithoutCrTransBean.strIsWithoutCrNoRequired}" />

<input type="hidden" name="strIsOnline" value="${cashCollectionWithoutCrTransBean.strIsOnline}" />

<input type="hidden" name="strPreviousCrNoDtlFlag" value="0" />

<input type="hidden" name="strTempPreviousCrNo" value="0" />


<input type="hidden" name="strPrintMessageLimit" value="${cashCollectionWithoutCrTransBean.strPrintMessageLimit}" />

<input type="hidden" name="strPatTypeBoth" value="${cashCollectionWithoutCrTransBean.strPatTypeBoth}" />

<input type="hidden" name="strIntExtPat" value="${cashCollectionWithoutCrTransBean.strIntExtPat}" />

<input type="hidden" name="strGeneralChargeTypeId" value="${cashCollectionWithoutCrTransBean.strGeneralChargeTypeId}" />
<input type="hidden" name="strWardTypeId" value="${cashCollectionWithoutCrTransBean.strWardTypeId}" />
<input type="hidden" name="strConsumableCharge" value="${cashCollectionWithoutCrTransBean.strConsumableCharge}" />
<input type="hidden" name="strOfflineTotalPayAmountWithoutConsumable" value="${cashCollectionWithoutCrTransBean.strOfflineTotalPayAmountWithoutConsumable}" />
<input type="hidden" name="strGroupIdForConsumableConcatenated" value="${cashCollectionWithoutCrTransBean.strGroupIdForConsumableConcatenated}" />
<input type="hidden" name="strConsumableChargesGroupId" value="${cashCollectionWithoutCrTransBean.strConsumableChargesGroupId}" />
<input type="hidden" name="strConsumableChargesTariffCode" value="${cashCollectionWithoutCrTransBean.strConsumableChargesTariffCode}" />
<input type="hidden" name="isOpenPopUp" value="${cashCollectionWithoutCrTransBean.isOpenPopUp}"/>
<input type="hidden" name="filePath" value="${cashCollectionWithoutCrTransBean.filePath}"/>
<input 	type="hidden" name="selectedTab" >

</html:form>

<jsp:include page="multirow_cashcollection_withoutcr_billtrans.jsp"></jsp:include>
<jsp:include page="dropdown_cashcollection_withoutcr_trans.jsp"></jsp:include>

</body>
</html>