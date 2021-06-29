<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>

<%@taglib uri="/WEB-INF/onLineReqDiscountDtl.tld" prefix="onLineDtl"%>
<%@taglib uri="/WEB-INF/patientDtl.tld" prefix="pDtl"%>
<%@taglib uri="/WEB-INF/dateTag.tld" prefix="date"%>

<%@ taglib uri="/WEB-INF/crNo.tld" prefix="crNo"%>

<html>
<head>
<title>Cash Collection</title>
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

<link href="../../hisglobal/css/cr-no-format.css" rel="stylesheet"
	type="text/css">

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
<script language="Javascript" src="../js/patientListing.js"></script>


<script language="Javascript" src="../js/cashcollection_trans.js"></script>
</head>
<body
	onLoad="onLoadLogics();addRows(new Array('strOnlinePaymentMode','strOnlinePaymentDtls','strOnlineAmount'),new Array('s','t','t'),'1','1','I');addRows(new Array('strOfflinePaymentMode','strOfflinePaymentDtls','strOfflineAmount'),new Array('s','t','t'),'3','1','I');"
	onfocus="checkPopUp();" onUnload="closePopUp();">
<script language="Javascript" src="../../hisglobal/js/wz_tooltip.js"></script>
<html:form 
	action="/transactions/CashCollectionTransCNT" method="post" >


	<div id="blanket" style="display: none;"></div>

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
					<td class='CONTROL'><input type='text' tabindex="1"
						name='strPayCDDBankName' class='txtFldMax'
						onkeypress='return validateData(event,4);' /></td>
					<td class='LABEL'><font color="red">*</font>Cheque / DD No.</td>
					<td class='CONTROL'><input type='text' name='strChequeDDNo' tabindex="1"
						class='txtFldDate' onkeypress="return validateData(event,5);" /></td>
				</tr>
				<tr>
					<td colspan='2' class='LABEL'><font color="red">*</font>Cheque
					/ DD Issue Date</td>
					<td colspan='2' class='CONTROL'><input type="text" tabindex="1"
						class="txtFldDate" maxlength="11" name="strChequeDDDate"
						value="${cashCollectionTransBean.strCurrentDate }" >
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
					<td> <input type="hidden" name="strConfirmationType" value="${cashCollectionTransBean.strConfirmationType}">
					
					<logic:equal name="cashCollectionTransBean"  property="strConfirmationType" value="0">
					<center> <input type="button" name="billConfirmCancel" tabindex="1" value="Cancel" class="btn"  onclick="return confirmCancel();" onblur="document.forms[0].billConfirmOk.focus();" > 
					<input type="button" name="billConfirmOk" class="btn" tabindex="1" value="Ok" onblur="document.forms[0].billConfirmCancel.focus();"   onclick="return confirmOk();">  
					</center>
					</logic:equal>
					<logic:equal name="cashCollectionTransBean"  property="strConfirmationType" value="1">
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
						value="${cashCollectionTransBean.strCurrentDate }" >
					(dd-Mon-yyyy)</td>
				</tr>
				<tr>
					<td colspan='2' class='LABEL'><font color="red">*</font>Card
					Type</td>
					<td colspan='2' class='CONTROL'><select class='comboNormal'
						name='strCardType' tabindex="1" >
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
					<bean:write name="cashCollectionTransBean" property="strClientNameContents" filter="false"/>
					</select></td>
				</tr>
				<tr>
					<td class='LABEL'><font color="red">*</font>Auth No.</td>
					<td class='CONTROL'><input type='text' name='strClientAuthNo' tabindex="1"
						class='txtFldNormal' onkeypress="return validateData(event,5);" /></td>
					<td class='LABEL'><font color="red">*</font>Auth Date</td>
					<td class='CONTROL'><input type="text" class="txtFldDate" tabindex="1"
						maxlength="11" name="strClientAuthDate"
						value="${cashCollectionTransBean.strCurrentDate }" >
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


	<div class="popUpDiv" id="pkgDiscountDtls" style="display: none;">
	<table bgcolor="white">
		<tr>
			<td>

			<div class='popup' id='pkgDiscountDtlsInner' style="display: block">
			<table width='400' background="../../hisglobal/images/blank.gif">
				<tr class='HEADER'>
					<th colspan='4' align='left'>&nbsp;Package Discount Details</th>
				</tr>
				<tr>
					<td class='LABEL'><font color="red">*</font>Discount / Unit</td>
					<td class='CONTROL'><input type='text' tabindex="1"
						name='strPkgDiscountUnit' class='txtFldMax'
						onkeypress="return validateData(event,7);" /></td>
					<td class='LABEL'><font color="red">*</font>discount Type</td>
					<td class='CONTROL'><select name='strPkgDiscountType' tabindex="1"
						title="_div_popup_cntl" class='comboNormal'>
						<option value='1'>Fixed</option>
						<option value='2'>Percentage</option>
					</select></td>
				</tr>
				<tr>
					<td class='LABEL'><font color="red">*</font>Discount By</td>
					<td class='CONTROL'><select title="_div_popup_cntl" 
						class='comboMax' name=''>
						<bean:write name="cashCollectionTransBean"
							property="strOfflineDiscountApprovedByDetails" filter="false" />
					</select></td>
					<td class='LABEL'><font color="red">*</font>Discount Reason</td>
					<td class='CONTROL'><select title="_div_popup_cntl"
						class='comboNormal'>
						<bean:write name="cashCollectionTransBean"
							property="strOfflineDiscountRemarksDetails" filter="false" />
					</select></td>
				</tr>
				<tr>
					<td colspan='2' class='LABEL'><font color="red">*</font>Discount
					Date</td>
					<td colspan='2' class='CONTROL'><date:date name=""
						value="${cashCollectionTransBean.strCurrentDate}" ></date:date></td>
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
						src='../../hisglobal/images/btn-sv.png' onClick=''> <img
						style="cursor: hand; cursor: pointer"
						src='../../hisglobal/images/btn-ccl.png'
						onClick="hideOffLinePkgDiscountDetails('pkgDiscountDtls');"></center>
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
						<bean:write name="cashCollectionTransBean"
							property="strOfflineDiscountApprovedByDetails" filter="false" />
					</select></td>
					<td class='LABEL'><font color="red">*</font>Discount Reason</td>
					<td class='CONTROL'><select title="_div_popup_cntl"
						class='comboNormal' name="strOffLineTariffDiscountReason"
						onchange="setReasonText();">
						<bean:write name="cashCollectionTransBean"
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
						value="${cashCollectionTransBean.strCurrentDate }" >
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
						onClick='validateTariffDiscountDetails();'> <img
						style="cursor: hand; cursor: pointer"
						src='../../hisglobal/images/btn-ccl.png'
						onClick="hideOffLineTariffDiscountDetails('tariffDiscountDtls');"></center>
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

	<div class='popup' id='tariffDtl' style="display: none">

	<table width='400' cellspacing="0"
		background="../../hisglobal/images/blank.gif">

		<tr class='HEADER'>

			<th colspan='3' align='left'>&nbsp;Tariff Details</th>

			<th align='right'><img
				src='../../hisglobal/images/stop.png'
				onClick="hide_popup_menu('tariffDtl');"></th>


		</tr>
	</table>
	<table width='400'>
		<tr>

			<td width="25%" class='LABEL'>Rate/Unit</td>

			<td width="25%" class='CONTROL'>
			<div id='tariffId1'></div>
			</td>

			<td width="25%" class='LABEL'>Total Discount</td>

			<td width="25%" class='CONTROL'>
			<div id='tariffId2'></div>
			</td>

		</tr>

		<tr>

			<td width="25%" class='LABEL'>Total Service Tax</td>

			<td width="25%" class='CONTROL'>
			<div id='tariffId3'></div>
			</td>

			<td width="25%" class='LABEL'>Total Penalty</td>

			<td width="25%" class='CONTROL'>
			<div id='tariffId4'></div>
			</td>

		</tr>

	</table>

	</div>


	<div class='popup' id='refundAdvanceDtl' style="display: none">

	<table width='400' cellspacing="0">

		<tr class='HEADER'>

			<th colspan='3' align='left'>&nbsp;Refund Advance Details</th>

			<th align='right'><img
				src='../../hisglobal/images/stop.png'
				onClick="hide_popup_menu('refundAdvanceDtl');"></th>

		</tr>
	</table>
	<table width='400'>
		<tr>
			<td width="50%" class='LABEL'>Penalty (%)</td>

			<td width="50%" class='CONTROL'>
			<div id='refAdvancePenalty'></div>
			</td>

		</tr>

		<tr>

			<td width="50%" class='LABEL'>Penalty Amount (in <img src='/AHIMS/hisglobal/images/INR.png'>)</td>

			<td width="50%" class='CONTROL'>
			<div id='refAdvancePenaltyAmt'></div>
			</td>

		</tr>

	</table>
	<table width='400' cellspacing="0">

		<tr class='FOOTER'>
			<td colspan="2"></td>
		</tr>
	</table>
	</div>



	<div class='popup' id='finalAdjustmentNetAmountDtls'
		style="display: none">

	<table width='400' cellspacing="0">

		<tr class='HEADER'>

			<th colspan='3' align='left'>&nbsp;Final Adjustment Net Amount
			Details</th>

			<th align='right'><img
				src='../../hisglobal/images/stop.png'
				onClick="hide_popup_menu('finalAdjustmentNetAmountDtls');"></th>

		</tr>
	</table>
	<table width='400'>
		<tr>
			<td width="35%" class='LABEL'>Expense Amount</td>

			<td width="15%" class='CONTROL'>
			<div id='finalAdjustExpenseAmount'></div>
			</td>
			<td width="35%" class='LABEL'>Client Amount</td>
			<td width="15%" class='CONTROL'>
			<div id='finalAdjustClientAmount'></div>
			</td>
		</tr>

		<tr>
			<td width="35%" class='LABEL'>Discount Amount</td>
			<td width="15%" class='CONTROL'>
			<div id='finalAdjustDiscountAmount'></div>
			</td>
			<td width="35%" class='LABEL'>Service Tax Amount</td>
			<td width="15%" class='CONTROL'>
			<div id='finalAdjustServiceAmount'></div>
			</td>
		</tr>

	</table>
	<table width='400' cellspacing="0">

		<tr class='FOOTER'>
			<td colspan="2"></td>
		</tr>
	</table>
	</div>


	<div id='refundTariffPopupDetailsDivId' class='popup'
		style="display: none"></div>

	<div id='reconcilTariffPopupDetailsDivId' class='popup'
		style="display: none"></div>



	<center>
	<div id="errMsg" class="errMsg"><bean:write
		name="cashCollectionTransBean" property="strErrMsg" filter="false" /></div>
	<div id="normalMsg" class="normalMsg"><bean:write
		name="cashCollectionTransBean" property="strNormalMsg" filter="false" /></div>
	<div id="warningMsg" class="warningMsg"><bean:write
		name="cashCollectionTransBean" property="strWarningMsg" filter="false" /></div>
	<tag:tab tabLabel="Cash Collection" selectedTab="FIRST" align="center" onlyTabIndexing="1"
		width="TABLEWIDTH">
	</tag:tab></center>

	<table class="TABLEWIDTH" align="center" cellspacing="1px"
		cellpadding="1px">
		<tr class="HEADER">
			<td>
			<table width="100%" cellpadding="0" cellspacing="0">
				<tr>
					<td colspan="3">Cash Collection</td>
					<td>
					<div align="right">
					<logic:equal value="2" name="cashCollectionTransBean" property="strCounterMode">
					<html:checkbox
						name="cashCollectionTransBean" property="strIsOnline" value="1"
						onclick="setOnlineOffLineMode(this);"></html:checkbox> Online&nbsp;
					</logic:equal>
					
					<logic:notEqual value="2" name="cashCollectionTransBean" property="strCounterMode">
					<div style="display: none">
					<html:checkbox
						name="cashCollectionTransBean" property="strIsOnline" value="1" 
						onclick="setOnlineOffLineMode(this);"></html:checkbox> Online&nbsp;
						</div>
					</logic:notEqual>
					</div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
	<logic:equal value="1" name="cashCollectionTransBean" property="strIsWithoutCrNoRequired">
	<table class="TABLEWIDTH" align="center" cellpadding="1px" cellspacing="1px">
		<tr>

			<td class="LABEL" colspan="4">
			
			<html:radio
				name="cashCollectionTransBean" property="strPatientMode" value="1"
				onclick="selectPatientMode(this);">With CR No.</html:radio> <html:radio
				name="cashCollectionTransBean" property="strPatientMode" value="2"
				onclick="selectPatientMode(this);">Without CR No.</html:radio></td>
		</tr>
	</table>
	</logic:equal>
	
	<logic:notEqual value="1" name="cashCollectionTransBean" property="strIsWithoutCrNoRequired">
	<div style="display: none">
	<table class="TABLEWIDTH" align="center" cellpadding="1px" cellspacing="1px">
		<tr>

			<td class="LABEL" colspan="4">
			
			<html:radio
				name="cashCollectionTransBean" property="strPatientMode" value="1"
				onclick="selectPatientMode(this);">With CR No.</html:radio> <html:radio
				name="cashCollectionTransBean" property="strPatientMode" value="2"
				onclick="selectPatientMode(this);">Without CR No.</html:radio></td>
		</tr>
	</table>
	</div>
	</logic:notEqual>
	<table class="TABLEWIDTH" align="center" cellspacing="1px"
		cellpadding="1px">
		<tr>
			<td class="TITLE">


			<table width="100%" cellpadding="0" cellspacing="0" >
				<tr>
					<td width="200">&nbsp;<font color="red">*</font>CR No. <crNo:crNo
						id="strCrNoId" name="strCrNo"
						value="${cashCollectionTransBean.strCrNo}"
						js="onkeypress='return initGoFunc(event);'"></crNo:crNo>
					</td>
					<td width="15">
					<div id="searchIconDivId"><img 
						style="cursor: pointer; cursor: hand;"
						src="../../hisglobal/images/viewDetails.gif"
						title="Click here for Patient Search" align="middle"
						name='searchPatient'
						onclick="showPatientListingWindow('1',document.forms[0].strCrNo,'setSelectedCrNo');" />
					</div>
					</td>
					<td><img src="../../hisglobal/images/Go.png" 						style="cursor: hand; cursor: pointer" title="Get Bill Details"
						name="go" onClick="return goFunc();" onKeyPress="return goFunc();"
						align="middle"> &nbsp;&nbsp;</td>


				</tr>
			</table>



			</td>
		</tr>
	</table>
	<div id="gblDivId" style="display: none">
	<div id="onLinePatId" style="display: block"><bean:write
		name="cashCollectionTransBean" property="strPatientDetailsView"
		filter="false" /></div>
	 <!-- online start -->
	<div id="onLineId">
	<table class="TABLEWIDTH" align="center" cellpadding="1px"
		cellspacing="1px">
		<tr class="HEADER">
			<td colspan="6">&nbsp;Online Request(s)</td>
		</tr>
		<tr>
			<td class="multiLabel" width="6%"></td>
			<td class="multiLabel" width="20%">Request No.</td>
			<td class="multiLabel" width="20%">Request Date</td>
			<td class="multiLabel" width="20%">Department/Ward</td>
			<td class="multiLabel" width="15%">Hospital Service</td>
			<td class="multiLabel" width="25%">Request For</td>
		</tr>
	</table>

	<bean:write name="cashCollectionTransBean"
		property="strOnlineDetailsView" filter="false" />
	<div id='clientDetailsDivId'><bean:write
		name="cashCollectionTransBean" property="strOnlineClientDetailsView"
		filter="false" /></div>
	<table class="TABLEWIDTH" align="center" cellpadding="1px"
		cellspacing="1px">
		<tr>

			<td colspan="3" class="TITLE">Payment Details</td>
		</tr>
	</table>
	<table class="TABLEWIDTH" align="center" cellspacing="1px">
		<tr>
			<td class="multiLabel" width="20%"><font color="red">*</font>Payment
			Mode</td>
			<td class="multiLabel" width="40%">Payment
			Details</td>
			<td class="multiLabel" width="20%"><font color="red">*</font>Amount</td>

			<td class="multiLabel" width="3%"><img
				style="cursor: hand; cursor: pointer"
				src="../../hisglobal/images/plus.gif"
				onclick="checkForOnLinePaymentLimit('0');"></td>
		</tr>
	</table>
	<div id="id1"></div>


	<div id="onlineGrandTotalDivPartId" style="display: none">
	<table class='TABLEWIDTH' align='center' cellpadding='1px'
		cellspacing='1px'>
		<tr>
			<td width='60%' class='LABEL'>Grand Total (<img src='/AHIMS/hisglobal/images/INR.png'>)</td>
			<td width='15%' class='CONTROL'>
			<table>
				<tr>
					<td><input type='hidden' name='strOnlineGrandTotalAmount'
						id='strOnlineGrandTotalAmount' value='0.00'></td>
					<td class='CONTROL' style="color: red; font-weight: bold">
					<div id='onlineGrandTotalDivId'>0.00</div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
	</div>

	<div id="onlineMaxClientBenefitDivPartId" style="display: none">
	<table class='TABLEWIDTH' align='center' cellpadding='1px'
		cellspacing='1px'>
		<tr>
			<td width='60%' class='LABEL'>Max Benefit From Client (<img src='/AHIMS/hisglobal/images/INR.png'>)</td>
			<td width='15%' class='CONTROL'>
			<table>
				<tr>
					<td><input type='hidden'
						name='strOnlineMaxClientBenefitAmount'
						id='strOnlineMaxClientBenefitAmount' value='0.00'></td>
					<td class='CONTROL' style='font-weight: bold'>
					<div id='onlineMaxClientBenefitDivId'>0.00</div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
	</div>

	<div id="onlinePatientPayableDivPartId" style="display: none">
	<table class='TABLEWIDTH' align='center' cellpadding='1px'
		cellspacing='1px'>
		<tr>
			<td width='60%' class='LABEL'>Net Payable Amount By Patient
			(<img src='/AHIMS/hisglobal/images/INR.png'>)</td>
			<td width='15%' class='CONTROL' style='font-weight: bold'>
			<table>
				<tr>
					<td><input type='hidden' name='strOnlinePatNetPayAmount'
						id='strOnlinePatNetPayAmount' value='0.00'></td>
					<td class='CONTROL' style='font-weight: bold'>
					<div id='onlinePatNetPayDivId'>0.00</div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
	</div>

	<table class="TABLEWIDTH" align="center" cellpadding="1px"
		cellspacing="1px">
		<tr>

			<td width="60%" class="LABEL">Net Payable / Refundable Amount
			(<img src='/AHIMS/hisglobal/images/INR.png'>)</td>
			<td width="15%" class="CONTROL">
			<table>
				<tr>
					<td><input type='hidden' name='strOnlineTotalRecAmount'
						value='0.00'></td>
					<td class="CONTROL" style="color: red; font-weight: bold">
					<div id='onlineTotalRecAmtDivId'>0.00</div>
					</td>
					<td>
					<div id="onlineNetAmountDetailsDivId" style="display: none">
					<img style="cursor: hand; cursor: pointer"
						src="../../hisglobal/images/plus.gif"
						onclick="displayOnlineNetAmountDetails(this , 'finalAdjustmentNetAmountDtls');">
					</div>
					</td>
				</tr>
			</table>

			</td>
		</tr>
	</table>

	<table class="TABLEWIDTH" align="center" cellpadding="1px"
		cellspacing="1px">
		<tr>

			<td width="60%" class="LABEL">Net Payment / Refund Amount (<img src='/AHIMS/hisglobal/images/INR.png'>)</td>
			<td width="15%" class="CONTROL" style="font-weight: bold">

			<table>
				<tr>
					<td><input type='hidden' name='strOnlineTotalPayAmount'
						id='strOnlineTotalPayAmount' value='0.00'></td>
					<td class="CONTROL">
					<div id='onlineTotalPayAmtDivId'>0.00</div>
					</td>
				</tr>
			</table>

			</td>
		</tr>
	</table>
	<table class="TABLEWIDTH" align="center" cellpadding="1px"
		cellspacing="1px">
		<tr>

			<td width="60%" class="LABEL">Change (<img src='/AHIMS/hisglobal/images/INR.png'>)</td>
			<td width="15%" class="CONTROL" style="font-weight: bold">


			<table>
				<tr>
					<td><input type='hidden' name='strOnlineRefundAmount'
						id='strOnlineRefundAmount' value='0.00'></td>
					<td class="CONTROL">
					<div id='onlineRefundAmtDivId'>0.00</div>
					</td>
				</tr>
			</table>

			</td>
		</tr>
	</table>
	</div>
	<!-- online end --> 
	
	<!-- off-line start -->
	<div id="offLineId" style="display: none">

	<div id='offlineClientDivId'><bean:write
		name="cashCollectionTransBean" property="strOfflineClientDetailsView"
		filter="false" /></div>
	<table class="TABLEWIDTH" align="center" cellpadding="1px"
		cellspacing="1px">
		<tr>
			<td colspan="3" class="LABEL"><b><font color="red">*</font>Hospital
			Service</b></td>
			<td class="CONTROL"><select class="comboNormal"
				name="strOffLineHospitalService"
				onchange="initOfflineRaisingDepartment(),wardDisplayStatus(this),resetTariffRows();">
				<bean:write name="cashCollectionTransBean"
					property="strHospitalServiceDetails" filter="false" />
			</select>&nbsp;</td>
		</tr>
		<tr>
			<td width="25%" class="LABEL"><font color="red">*</font>Request
			Type</td>
			<td width="25%" class="CONTROL"><select class="comboNormal"
				name="strOffLineRequestType"
				onchange="getBillService(),resetTotalAmount(this);">
				<option value='1'>Receipt</option>
				<option value='2'>Refund</option>
			</select></td>
			<td width="25%" class="LABEL"><font color="red">*</font>Billing
			Services</td>
			<td width="25%" class="CONTROL">
			<div id='billServiceDivId'><select class="comboNormal"
				name="strOffLineBillingService"
				onchange="getPartAccDtls(this),resetTotalAmount(this);">
				<bean:write name="cashCollectionTransBean"
					property="strBillingServiceDetails" filter="false" />
			</select></div>
			</td>
		</tr>
		</table>
		
		<div id="offlineDepEpdTreatWardDivId" style="display: block">
		
		<table class="TABLEWIDTH" align="center" cellpadding="1px"
		cellspacing="1px">
		<tr>
			<td width="25%" class="LABEL"><font color="red">*</font>Raising
			Department</td>
			<td width="25%" class="CONTROL">
			<div id='offlineRaisingDepartmentDivId'><select
				class="comboNormal" name="strOffLineRaisingDepartment"
				onchange="getEpisodeList(this);">
				<bean:write name="cashCollectionTransBean"
					property="strRaisingDepartmentDetails" filter="false" />
			</select></div>
			</td>
			<td width="25%" class="LABEL">Episode</td>
			<td width="25%" class="CONTROL">
			<div id='episodeDivId'><select class="comboNormal"
				name="strOffLineEpisode">
				<bean:write name="cashCollectionTransBean"
					property="strEpisodeDetails" filter="false" />
			</select></div>
			</td>
		</tr>
		<tr>
			<td width="25%" class="LABEL"><font color="red">*</font>Patient
			Category</td>
			<td width="25%" class="CONTROL">
			<div id='offlineTreatmentCategoryDivId'><select
				class="comboNormal" name="strOffLineTreatmentCategory"
				onclick='showAlert()'
				onchange="getTariffDtls(),resetTariffRows();">
				<bean:write name="cashCollectionTransBean"
					property="strTreatmentCategoryDetails" filter="false" />
			</select></div>
			</td>
			<td colspan='2' class="CONTROL">
			<div id='wardDivId' style="display: none">
			<table width="100%" align="center" cellpadding="0" cellspacing="0">
				<tr>
					<td width="50%" class="LABEL"><font color="red">*</font>Ward
					Name&nbsp;</td>
					<td width="50%" class="CONTROL">
					<div id='offlineWardDivId'><select class="comboNormal"
						name="strOffLineWard"
						onchange="getTariffDtls(),getPartAccDtls(this);">
						<bean:write name="cashCollectionTransBean"
							property="strWardDetails" filter="false" />
					</select></div>
					</td>

				</tr>

			</table>			
			</div>
			</td>

		</tr>

	</table>
	</div>
	
	<div id='offlineBillDetailsDivId' style="display: none"></div>
	<div id='offlineBillTariffDivId' style="display: none"></div>

	<div id='offlineAdmissionCancellationDivId' style="display: none">
	</div>

	<div id='partPayAdvanceDivId'><bean:write
		name="cashCollectionTransBean"
		property="strPartPayAdvanceAmountDetails" filter="false" /></div>

	<div id="otherRefundDetailsDivId" style="display: none">
	<table class='TABLEWIDTH' border="0" cellpadding="1px"
		cellspacing="1px" align="center">
		<tr>
			<td width='25%' class='LABEL'><font color="red">*</font>Refund
			By:</td>
			<td width="75%" class="CONTROL"><select
				name="strOffLineRefundBy">
				<bean:write name="cashCollectionTransBean"
					property="strOfflineDiscountApprovedByDetails" filter="false" />
			</select></td>
		</tr>
		<tr>
			<td width="25%" class="LABEL"><font color="red">*</font>Refund
			Reason:</td>
			<td width="75%" class="CONTROL"> <table><tr><td class="CONTROL"><select
				name="strOffLineRefundReason" onChange="setRefundRemarksText();">
				<bean:write name="cashCollectionTransBean"
					property="strOffLineRefundReasonDetails" filter="false" />
			</select></td><td><input name="strOffLineRefundReasonText" type="text"
				class="txtFldMax" value="" disabled="disabled"></td></tr></table>  </td>
		</tr>
		<tr>
			<td class="LABEL" width="25%">Refund Date :</td>
			<td width="75%" class="CONTROL"><date:date
				name="strOfflineRefundDate"
				value="${cashCollectionTransBean.strCurrentDate}">
			</date:date></td>
		</tr>
	</table>
	</div>

	<div id='offlineTariffDivId'>
	<table class="TABLEWIDTH" align="center" cellpadding="1px"
		cellspacing="1px">
		<tr>
			<td colspan='8'>
			<table cellpadding="0" cellspacing="0" width="100%">
				<tr>
					<td class="TITLE" width='25%'>Tariff Detail</td>
					<td width='25%' class='TITLE'>
					<div id='trfCodeDivId' align="right"> Tariff Code <input type="text" class="txtFldMin" name="strTariffCode" tabindex="1"   onkeypress="return validateData(event,8);" onkeyup="getTariffByCode(this , event);" >
					</div>
					</td>
					<td width='50%' class='TITLE'>
					<div id='groupDivId' align="right"><table border='0' cellpadding='0'   cellspacing='0'><tr><td class="TITLE">Group </td><td class="TITLE"><select tabindex="2"
						name="strOffLineGroup" class="comboMax"
						onchange="getTariffDtls();">
						<bean:write name="cashCollectionTransBean"
							property="strOfflineGroupDetails" filter="false" />
					</select></td></tr></table></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>

			<td class="multiLabel" width="16%"><font color="red">*</font>Tariff
			Name</td>
			<td class="multiLabel" width="7%">Rate / Unit</td>
			<td class="multiLabel" width="6%"><font color="red">*</font>Qty.</td>
			<td class="multiLabel" width="9%"><font color="red">*</font>Unit</td>
			<td class="multiLabel" width="7%">S. Tax (%)<input type="hidden"
				name="strOfflineTotalServiceTaxAmount" value="0"></td>
			<td class="multiLabel" width="8%">Dis. / Unit<input
				type="hidden" name="strOfflineTotalDiscountAmount" value="0"></td>
			<td class="multiLabel" width="8%">Net Amt.<input
				type="hidden" name="strOfflineTotalActualTariffAmount" value="0">
			</td>
			<td class="multiLabel" width="3%"><img
				style="cursor: hand; cursor: pointer"
				src="../../hisglobal/images/plus.gif"
				onclick="generateRows()"></td>
		</tr>
	</table>
	<div id="id2"></div>
	</div>

	<div id='offlinePkgsDivId' style="display: none">
	<table class="TABLEWIDTH" align="center" cellspacing="1px">
		<tr>
			<td colspan='9'>
			<table cellpadding="0" cellspacing="0" width="100%">
				<tr>
					<td class="TITLE" width='50%'>Package Detail</td>
					<td width='50%' class='TITLE'>
					<div id='packageGroupDivId' align="right">
					
					<table border='0' cellpadding='0' cellspacing='0'><tr><td class="TITLE">Group </td><td class="TITLE"><select
						name="strOffLinePackageGroup" class="comboMax"
						onchange="getPackageDtls(this);">
						<bean:write name="cashCollectionTransBean"
							property="strOfflinePkgsGroupDetails" filter="false" />
					</select></td></tr></table>
					
					
					</div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>

			<td class="multiLabel" width="18%">Package Name</td>
			<td class="multiLabel" width="8%">Rate/Unit</td>
			<td class="multiLabel" width="8%">Quantity</td>
			<td class="multiLabel" width="10%">Unit</td>
			<td class="multiLabel" width="8%">Service Tax</td>
			<td class="multiLabel" width="10%">Discount / Unit</td>
			<td class="multiLabel" width="10%">Net Amount (<img src='/AHIMS/hisglobal/images/INR.png'>)</td>
			<td class="multiLabel" width="8%">Config Services</td>
			<td class="multiLabel" width="3%"><img
				style="cursor: hand; cursor: pointer"
				src="../../hisglobal/images/plus.gif"
				onclick="addRows(new Array('strOfflinePackageName','strOfflinePackageRateUnit','strOfflinePackageReqQty','strOfflinePackageUnit','strOfflinePackageServiceTax','strOfflinePackageDiscount','strOfflinePackageNetAmount','flag'),new Array('t','t','t','t','t','t','t','t'),'4','1','R');"></td>
		</tr>
	</table>
	<div id="id4"></div>
	</div>

	<table class="TABLEWIDTH" align="center" cellpadding="1px"
		cellspacing="1px">
		<tr>
			<td colspan="4" class="TITLE">Payment Mode</td>
		</tr>
	</table>
	<table class="TABLEWIDTH" align="center" cellpadding="1px"
		cellspacing="1px">
		<tr>
			<td class="multiLabel" width="20%"><font color="red">*</font>Payment
			Mode</td>
			<td class="multiLabel" width="40%">Payment
			Details</td>
			<td class="multiLabel" width="20%"><font color="red">*</font>Amount
			(<img src='/AHIMS/hisglobal/images/INR.png'>)</td>
			<td class="multiLabel" width="3%"><img
				style="cursor: hand; cursor: pointer"
				src="../../hisglobal/images/plus.gif"
				onclick="checkForOffLinePaymentLimit('0');"></td>
		</tr>
	</table>
	<div id="id3"></div>



	<div id="offlineGrandTotalDivPartId" style="display: none">
	<table class='TABLEWIDTH' align='center' cellpadding='1px'
		cellspacing='1px'>
		<tr>
			<td width='60%' class='LABEL'>Grand Total (<img src='/AHIMS/hisglobal/images/INR.png'>)</td>
			<td width='15%' class='CONTROL'>
			<table>
				<tr>
					<td><input type='hidden' name='strOfflineGrandTotalAmount'
						id='strOfflineGrandTotalAmount' value='0.00'></td>
					<td class='CONTROL' style='color: red; font-weight: bold'>
					<div id='offlineMaxGrandTotalDivId'>0.00</div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
	</div>

	<div id="offlineMaxClientBenefitDivPartId" style="display: none">
	<table class='TABLEWIDTH' align='center' cellpadding='1px'
		cellspacing='1px'>
		<tr>
			<td width='60%' class='LABEL'>Max Benefit From Client (<img src='/AHIMS/hisglobal/images/INR.png'>)</td>
			<td width='15%' class='CONTROL'>
			<table>
				<tr>
					<td><input type='hidden'
						name='strOfflineMaxClientBenefitAmount'
						id='strOfflineMaxClientBenefitAmount' value='0.00'></td>
					<td class='CONTROL' style='font-weight: bold'>
					<div id='offlineMaxClientBenefitDivId'>0.00</div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
	</div>

	<div id="offlinePatientPayableDivPartId" style="display: none">
	<table class='TABLEWIDTH' align='center' cellpadding='1px'
		cellspacing='1px'>
		<tr>
			<td width='60%' class='LABEL'>Net Payable Amount By Patient
			(<img src='/AHIMS/hisglobal/images/INR.png'>)</td>
			<td width='15%' class='CONTROL' style='font-weight: bold'>
			<table>
				<tr>
					<td><input type='hidden' name='strOfflinePatNetPayAmount'
						id='strOfflinePatNetPayAmount' value='0.00'></td>
					<td class='CONTROL' style='font-weight: bold'>
					<div id='offlinePatNetPayDivId'>0.00</div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
	</div>
	<table class="TABLEWIDTH" align="center" cellpadding="1px"
		cellspacing="1px">
		<tr>

			<td width="60%" class="LABEL">Net Payable / Refundable Amount
			(<img src='/AHIMS/hisglobal/images/INR.png'>)</td>
			<td width="15%" class="CONTROL" style="font-weight: bold">

			<table>
				<tr>
					<td><input type='hidden' name='strOfflineTotalRecAmount'
						id='strOfflineTotalRecAmount' value='0.00'></td>
					<td class="CONTROL">
					<div id='totalRecAmtDivId'>0.00</div>
					</td>
				</tr>
			</table>


			</td>
		</tr>

	</table>

	<table class="TABLEWIDTH" align="center" cellpadding="1px"
		cellspacing="1px">
		<tr>

			<td width="60%" class="LABEL">Net Payment / Refund Amount (<img src='/AHIMS/hisglobal/images/INR.png'>)</td>
			<td width="15%" class="CONTROL" style="font-weight: bold">


			<table>
				<tr>
					<td><input type='hidden' name='strOfflineTotalPayAmount'
						id='strOfflineTotalPayAmount' value='0.00'></td>
					<td class="CONTROL">
					<div id='offlineTotalPayAmtDivId'>0.00</div>
					</td>
				</tr>
			</table>

			</td>
		</tr>
	</table>

	<table class="TABLEWIDTH" align="center" cellpadding="1px"
		cellspacing="1px">
		<tr>

			<td width="60%" class="LABEL">Change (<img src='/AHIMS/hisglobal/images/INR.png'>)</td>
			<td width="15%" class="CONTROL">

			<table>
				<tr>
					<td><input type='hidden' name='strOfflineRefundAmount'
						id='strOfflineRefundAmountDivId' value='0.00'></td>
					<td class="CONTROL" style="color: red; font-weight: bold">
					<div id='offlineRefundAmtDivId'>0.00</div>
					</td>
				</tr>
			</table>

			</td>
		</tr>
	</table>

	</div>

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

	<!--  off-line end --></div>
	<table class="TABLEWIDTH" align="center" cellpadding="1px"
		cellspacing="1px">
		<tr>
			<td>
			<table width='100%' cellspacing="0px">
				<tr class="FOOTER">
					<td width='3%'>
					<div id="plusHelpId" align="center" style="display: none;" ><img
						style="cursor: hand; cursor: pointer;"
						src="../../hisglobal/images/plus.gif" name="plusHelp"
						align="middle" onclick="showCltDetails('HelpId');" /></div>
					<div id="minusHelpId" style="display: block" align="center"><img
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
			<div id="HelpId" style="display: block">
			<table width='100%' align="center" cellpadding="1px"
				cellspacing="1px">
				<tr>
					<td class="CONTROL" colspan="2" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Press <b>Alt+Ctrl+S</b>
					in Off-Line Data's Tariff Name Text Box To Search Tariff</td>
				</tr>
				<tr>
					<td class="CONTROL" colspan="2" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Press <b>Alt+Ctrl+R</b>
					in Off-Line Data's Tariff Name Text Box To Add More than One Row</td>
				</tr>
					<tr>
					<td class="multiControl" width="20" ><table width="60%" bgcolor="red" align="center"><tr><td>&nbsp;&nbsp;</td></tr></table> 
					</td  ><td class='CONTROL' >Rejected Record(s)</td>
				</tr>
				<tr class=FOOTER>
					<td colspan="2"></td>
				</tr>
			</table>
			</div>
			</td>
		</tr>
	</table>
<div id="saveid">
	<table border="0" class="TABLEWIDTH" align="center" cellpadding="1px"
		cellspacing="1px">
		<tr>

			<td align="center"><img style="cursor: pointer" name="saveButton" tabindex="1"
				src="../../hisglobal/images/btn-sv.png" 
				onClick="return findBillServices();" onkeypress="onKeyPressLogic('1',event);"   title="Save Record" />
			<img style="cursor: pointer"  tabindex="1"
				src="../../hisglobal/images/btn-clr.png" onClick="clearFunc(document.forms[0].strCounterMode.value);"
				  onkeypress="onKeyPressLogic('2',event);"  title="Clear Content">
			<img style="cursor: pointer"  tabindex="1"
				src="../../hisglobal/images/btn-ccl.png" onClick="cancelFunc();"
				 onkeypress="onKeyPressLogic('3',event);"  title="Cancel Process"></td>
		</tr>
	</table>
</div>
	<input type="hidden" name="hmode" />
	<input type='hidden' name='strPkgServiceFlag' value='0' />
	<input type="hidden" name="strTempCtDate"
		value="${cashCollectionTransBean.strCurrentDate}" />
	<input type="hidden" name="strIpdThirdPartyBenefit"
		value="${cashCollectionTransBean.strIpdThirdPartyBenefit}" />
	<input type="hidden" name="strEmergencyThirdPartyBenefit"
		value="${cashCollectionTransBean.strEmergencyThirdPartyBenefit}" />
	<input type="hidden" name="strOpdThirdPartyBenefit"
		value="${cashCollectionTransBean.strOpdThirdPartyBenefit}" />

	<input type="hidden" name="strOfflineIpdPenaltyVal"
		value="${cashCollectionTransBean.strOfflineIpdPenaltyVal}" />
	<input type="hidden" name="strOfflineOpdPenaltyVal"
		value="${cashCollectionTransBean.strOfflineOpdPenaltyVal}" />
	<input type="hidden" name="strOfflineEmergencyPenaltyVal"
		value="${cashCollectionTransBean.strOfflineEmergencyPenaltyVal}" />

	<input type='hidden' name='strOffLineRefundAdvanceAccountNo' value='0' />


	<%-- current state represents  1 - Online and 2 - Off-line--%>
	<input type="hidden" name="currentState"
		value="${cashCollectionTransBean.currentState}" />
	<input type="hidden" name="currentBserviceId" value='1' />
	<input type="hidden" name="currentReceiptType" value='1' />
	<input type="hidden" name="currentHospService" value='1' />
	
	<input type="hidden" name="strTempBillNo" value="${cashCollectionTransBean.strTempBillNo}" />
	<input type="hidden" name="strTempReceiptNo" value="${cashCollectionTransBean.strTempReceiptNo}" />


<input type="hidden" name="strIsIpdDiscount" value="${cashCollectionTransBean.strIsIpdDiscount}" />
<input type="hidden" name="strIsOpdDiscount" value="${cashCollectionTransBean.strIsOpdDiscount}" />
<input type="hidden" name="strIsEmergencyDiscount" value="${cashCollectionTransBean.strIsEmergencyDiscount}" />
	
<input type="hidden" name="strCounterMode" value="${cashCollectionTransBean.strCounterMode}" />
<input type="hidden" name="strIsWithoutCrNoRequired" value="${cashCollectionTransBean.strIsWithoutCrNoRequired}" />


</html:form>
<jsp:include page="multirow_cashcollection_billtrans.jsp"></jsp:include>
<jsp:include page="dropdown_cashcollection_trans.jsp"></jsp:include>

</body>
</html>