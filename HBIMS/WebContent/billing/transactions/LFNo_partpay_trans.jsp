<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
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


<title>LF No. PartPay </title>
<link href="../css/transaction.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/calendar-tas.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/popup.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/newpopup.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/dropdown.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/cr-no-format.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/buttons.css" rel="stylesheet" type="text/css">

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


<script language="Javascript" src="../js/LFNo_offline_trans.js"></script>
</head>
<body
	onLoad="onLoadLogics();addRows(new Array('strOfflinePaymentMode','strOfflinePaymentDtls','strOfflineAmount'),new Array('s','t','t'),'3','1','I');openPrintPopUp();"
	onfocus="checkPopUp();" onUnload="closePopUp();">
<script language="Javascript" src="../../hisglobal/js/wz_tooltip.js"></script>
<html:form 
	action="/transactions/LFNoTransCNT" method="post" enctype="multipart/form-data">



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
						value="${LFNoTransBean.strCurrentDate }" >
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
					<td> <input type="hidden" name="strConfirmationType" value="${LFNoTransBean.strConfirmationType}">
					
					<logic:equal name="LFNoTransBean"  property="strConfirmationType" value="0">
					<center> <input type="button" name="billConfirmCancel" tabindex="1" value="Cancel" class="btn"  onclick="return confirmCancel();" onblur="document.forms[0].billConfirmOk.focus();" > 
					<input type="button" name="billConfirmOk" class="btn" tabindex="1" value="Ok" onblur="document.forms[0].billConfirmCancel.focus();"   onclick="return confirmOk();">  
					</center>
					</logic:equal>
					<logic:equal name="LFNoTransBean"  property="strConfirmationType" value="1">
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
						value="${LFNoTransBean.strCurrentDate }" >
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
					<bean:write name="LFNoTransBean" property="strClientNameContents" filter="false"/>
					</select></td>
				</tr>
				<tr>
					<td class='LABEL'><font color="red">*</font>Auth No.</td>
					<td class='CONTROL'><input type='text' name='strClientAuthNo' tabindex="1"
						class='txtFldNormal' onkeypress="return validateData(event,5);" /></td>
					<td class='LABEL'><font color="red">*</font>Auth Date</td>
					<td class='CONTROL'><input type="text" class="txtFldDate" tabindex="1"
						maxlength="11" name="strClientAuthDate"
						value="${LFNoTransBean.strCurrentDate }" >
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
						<bean:write name="LFNoTransBean"
							property="strOfflineDiscountApprovedByDetails" filter="false" />
					</select></td>
					<td class='LABEL'><font color="red">*</font>Discount Reason</td>
					<td class='CONTROL'><select title="_div_popup_cntl"
						class='comboNormal'>
						<bean:write name="LFNoTransBean"
							property="strOfflineDiscountRemarksDetails" filter="false" />
					</select></td>
				</tr>
				<tr>
					<td colspan='2' class='LABEL'><font color="red">*</font>Discount
					Date</td>
					<td colspan='2' class='CONTROL'><date:date name=""
						value="${LFNoTransBean.strCurrentDate}" ></date:date></td>
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
						<bean:write name="LFNoTransBean"
							property="strOfflineDiscountApprovedByDetails" filter="false" />
					</select></td>
					<td class='LABEL'><font color="red">*</font>Discount Reason</td>
					<td class='CONTROL'><select title="_div_popup_cntl"
						class='comboNormal' name="strOffLineTariffDiscountReason"
						onchange="setReasonText();">
						<bean:write name="LFNoTransBean"
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
						value="${LFNoTransBean.strCurrentDate }" >
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

			<td width="50%" class='LABEL'>Penalty Amount (<img src='/HBIMS/hisglobal/images/INR.png'>)</td>

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
		name="LFNoTransBean" property="strErrMsg" filter="false" /></div>
	<div id="normalMsg" class="normalMsg"><bean:write
		name="LFNoTransBean" property="strNormalMsg" filter="false" /></div>
	<div id="warningMsg" class="warningMsg"><bean:write
		name="LFNoTransBean" property="strWarningMsg" filter="false" /></div>
	<tag:tab tabLabel="LF Account Part Payment Cash Collection" selectedTab="FIRST" align="center" onlyTabIndexing="1"
		width="TABLEWIDTH">
	</tag:tab></center>

	<table class="TABLEWIDTH" align="center" cellspacing="1px"
		cellpadding="1px">
		<tr class="HEADER">
			<td>
			<table width="100%" cellpadding="0" cellspacing="0">
				<tr>
					<td colspan="3">
					<a  id="cashCollection" style="cursor: pointer;display: block;" onclick="openDependentPopup('BillingCNT.cnt?hmode=CASHCOLLECTIONPOPUP',event,250,600);" 
					title="Cash Collection Detail"><font color="white" style=""><u></u></font></a>
					</td>
					<td>
					<div align="right">
					<logic:equal value="2" name="LFNoTransBean" property="strCounterMode">
					<html:checkbox
						name="LFNoTransBean" property="strIsOnline" value="1"
						onclick="setOnlineOffLineMode(this);"></html:checkbox> Online&nbsp;
					</logic:equal>
					
					<logic:notEqual value="2" name="LFNoTransBean" property="strCounterMode">
					<div style="display: none">
					<html:checkbox
						name="LFNoTransBean" property="strIsOnline" value="1" 
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
	<logic:equal value="1" name="LFNoTransBean" property="strIsWithoutCrNoRequired">
	<table class="TABLEWIDTH" align="center" cellpadding="1px" cellspacing="1px">
		<tr>

			<td class="LABEL" colspan="4">
			
			<html:radio
				name="LFNoTransBean" property="strPatientMode" value="1"
				onclick="selectPatientMode(this);">With CR No.</html:radio> <html:radio
				name="LFNoTransBean" property="strPatientMode" value="2"
				onclick="selectPatientMode(this);">Without CR No.</html:radio></td>
		</tr>
	</table>
	</logic:equal>
	
	<logic:notEqual value="1" name="LFNoTransBean" property="strIsWithoutCrNoRequired">
	<div style="display: none">
	<table class="TABLEWIDTH" align="center" cellpadding="1px" cellspacing="1px">
		<tr>

			<td class="LABEL" colspan="4">
			
			<html:radio
				name="LFNoTransBean" property="strPatientMode" value="1"
				onclick="selectPatientMode(this);">With CR No.</html:radio> <html:radio
				name="LFNoTransBean" property="strPatientMode" value="2"
				onclick="selectPatientMode(this);">Without CR No.</html:radio></td>
		</tr>
	</table>
	</div>
	</logic:notEqual>
	
	<table class="TABLEWIDTH" align="center" cellpadding="1px" cellspacing="1px">
	<tr>

			<td class="LABEL" colspan="8">
			
			<html:radio
				name="LFNoTransBean" property="strAccountStatus" value="1"
				onclick="setStrAccountStatus('1');" >Account Open</html:radio> <html:radio
				name="LFNoTransBean" property="strAccountStatus" value="2"
				onclick="setStrAccountStatus('2');">Account Close</html:radio>
		
			<html:radio
				name="LFNoTransBean" property="strAccountStatus" value="3"
				onclick="setStrAccountStatus('3');">Part Payment</html:radio> <html:radio
				name="LFNoTransBean" property="strAccountStatus" value="4"
				onclick="setStrAccountStatus('4');">Account Summary</html:radio></td>
		</tr>
     <tr>
		<td class="LABEL" colspan="4" >
		<html:radio property="strCRorLFwise" name="LFNoTransBean" value="1" onclick="IsCrOrLFWise('1'); ">CR No. wise</html:radio>
		<html:radio property="strCRorLFwise" name="LFNoTransBean" value="2" onclick="IsCrOrLFWise('2');">LF No. wise</html:radio></td>
		</tr>
		

		<tr style="display: none;">
			<td colspan="1" class="LABEL" width="25%"><b><font color="red">*</font>Hospital Service</b></td>
			<td class="CONTROL" colspan="1" width="25%"><select class="comboNormal" name="strOffLineHospitalService" onchange="getBillService();">
				<bean:write name="LFNoTransBean" property="strHospitalServiceDetails" filter="false" />
			</select>&nbsp;</td>
			<td width="25%" class="LABEL" colspan="1"><font color="red">*</font>Billing Services</td>
			<td width="25%" class="CONTROL" colspan="1">
			<div id='billServiceDivId'><select class="comboNormal" name="strOffLineBillingService" onchange="resetTotalAmount(this);">
				<bean:write name="LFNoTransBean" property="strBillingServiceDetails" filter="false" />
			</select></div>
			</td>
		</tr>
		
		
		<tr>
			<%--  <td width="50%" colspan="1" class="LABEL"><div align='Left'><font color="red">*</font>CR No.
			 	
				 	<crNo:crNo id="strCrNoId" name="strCrNo" value="${LFNoTransBean.strCrNo}" js="onkeypress='return initGoFunc(event);'"></crNo:crNo>
				 	<img src="../../hisglobal/images/Go.png" style="cursor: hand; cursor: pointer" title="Get Bill Details" name="go" onClick="return goFunc();" onKeyPress="" align="middle"> &nbsp;&nbsp;
				 </div>
			</td>	 --%>
			
			
			
			 <td width="25%" class="LABEL" id="CrNOOrLf"><font color="red">*</font>CR No.</td>
    <td colspan="4" class="CONTROL" style="display:block"><div id="CRWise">
<crNo:crNo id="strCrNoId" name="strCrNo" value="${LFNoTransBean.strCrNo}" js="onkeypress='return initGoFunc(event);'"></crNo:crNo>
    <input type="image" style="cursor:pointer;cursor:pointer" title="Issue Process" 
		    align="top" src ="../../hisglobal/images/Go.png" name="go" 
		    onclick="return goFunc();" onkeyup="" align="middle"> 
	
		</div>
		
		<div id="LFWise" style="display:none"> 
    <input type="text" name="strLFNo" value ="${LFNoTransBean.strLFNo}" js=" onkeypress='return initGoFunc(event);'"/>
    <input type="image" style="cursor:pointer;cursor:pointer" title="Issue Process" 
		    align="top" src ="../../hisglobal/images/Go.png" name="go" value="Go" 
		    onclick="return goFunc();" onkeyup="">  
		    </div>   
  </td>
  
					
			<td  style="display: none;" width="25%" class="LABEL" colspan="1"><font color="red">*</font>Request Type</td>
			<td style="display: none;"  width="25%" class="CONTROL" colspan="1">			
				<logic:equal name="LFNoTransBean" property="strIsRefundReq" value="1">
					<html:select name="LFNoTransBean" property="strOffLineRequestType" styleClass="comboNormal"  onchange="getBillService();">
						<html:option value="1">Receipt</html:option>
						<html:option value="2">Refund</html:option>
					</html:select>
				</logic:equal>			
				<logic:equal name="LFNoTransBean" property="strIsRefundReq" value="0">
					<html:select name="LFNoTransBean" property="strOffLineRequestType" styleClass="comboNormal"  onchange="getBillService();">
						<html:option value="1">Receipt</html:option>
					</html:select>
				</logic:equal>			
			 </td>
		</tr>
		</table>
	
	 <div id="offlineDetailsDivId" style="display: none;">
	 
	 <bean:write name="LFNoTransBean" property="strPatientDetailsView" filter="false" />
	<!-- off-line start -->
 
	<div id='offlineClientDivId'><bean:write
		name="LFNoTransBean" property="strOfflineClientDetailsView"
		filter="false" /></div>
	
		
		<div id="offlineDepEpdTreatWardDivId" style="display: block">
		
	
	
	 <table class="TABLEWIDTH" align="center" border="0" cellspacing ="1px">
	 <tr>
			<td colspan="4" width='100%'><div class='line'><label class='DIVLABEL'>LF Account Details</label></div></td>
		</tr>
	 		<tr>
		<td width="100%" colspan='8'>
			
		<div id='LFAccounDivId'><bean:write name="LFNoTransBean" property="strAccountDetailsView"
		filter="false" /></div>
		
		</td></tr>
	 </table>
		
		<table class="TABLEWIDTH" align="center" cellpadding="1px"
		cellspacing="1px">

		<tr>
			<td colspan="4" width='100%'><div class='line'><label class='DIVLABEL'>Patient Details</label></div></td>
		</tr>
		<tr>
			<td width="25%" class="LABEL"><font color="red">*</font>Raising
			Department</td>
			<td width="25%" class="CONTROL">
			<div id='offlineRaisingDepartmentDivId'><select
				class="comboNormal" name="strOffLineRaisingDepartment"
				onchange="" disabled="disabled">
				<bean:write name="LFNoTransBean"
					property="strRaisingDepartmentDetails" filter="false" />
			</select></div>
			</td>
			<td width="25%" class="LABEL"  style="display: none;">Episode</td>
			<td width="25%" class="CONTROL">
			<div id='episodeDivId'  style="display: none;"><select class="comboNormal"
				name="strOffLineEpisode">
				<bean:write name="LFNoTransBean"
					property="strEpisodeDetails" filter="false" />
			</select></div>
			</td>
		</tr>
		<tr>
			<td width="25%" class="LABEL"><font color="red">*</font>Patient Category</td>
			<td width="25%" class="CONTROL">
			<div id='offlineTreatmentCategoryDivId'>
				<select class="comboNormal" name="strOffLineTreatmentCategory" onchange="poorFreeCheck('strOffLineTreatmentCategory'),getPartAccDtls(document.getElementsByName('strOffLineWard')[0]);" disabled="disabled" >
					<bean:write name="LFNoTransBean" property="strTreatmentCategoryDetails" filter="false" />
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
						<bean:write name="LFNoTransBean"
							property="strWardDetails" filter="false" />
					</select></div>
					</td>

				</tr>

			</table>			
			</div>
			</td>

		</tr>

	</table>
	
	<div id="specialWardDivId" style="display: none;">
	<table class="TABLEWIDTH" align="center" cellpadding="1px"
		cellspacing="1px">
		<tr>
			<td width="25%" class="LABEL">Ward Name</td>
			<td width="75%" class="CONTROL" colspan="3">
			
			<div id='offlineSpecialWardDivId' ><select class="comboNormal"
						name="strOffLineSpecialWard"
						onchange="changeWard();">
						<bean:write name="LFNoTransBean"
							property="strSpecialWardDetails" filter="false" />
					</select></div>
			</td>
			</tr>
			</table>
			
			</div>
	
	
	</div>
	
	<div id='offlineBillDetailsDivId' style="display: none"></div>
	<div id='offlineBillTariffDivId' style="display: none"></div>

	<div id='offlineAdmissionCancellationDivId' style="display: none">
	</div>

	<div id='partPayAdvanceDivId'><bean:write
		name="LFNoTransBean"
		property="strPartPayAdvanceAmountDetails" filter="false" /></div>

	<div id="otherRefundDetailsDivId" style="display: none">
	<table class='TABLEWIDTH' border="0" cellpadding="1px"
		cellspacing="1px" align="center">
		<tr>
			<td width='25%' class='LABEL'><font color="red">*</font>Refund
			By:</td>
			<td width="75%" class="CONTROL"><select
				name="strOffLineRefundBy">
				<bean:write name="LFNoTransBean"
					property="strOffLineRefundByList" filter="false" />
			</select></td>
		</tr>
		<tr>
			<td width="25%" class="LABEL"><font color="red">*</font>Refund
			Reason:</td>
			<td width="75%" class="CONTROL"> <table><tr><td class="CONTROL"><select
				name="strOffLineRefundReason" onChange="setRefundRemarksText();">
				<bean:write name="LFNoTransBean"
					property="strOffLineRefundReasonDetails" filter="false" />
			</select></td><td><input name="strOffLineRefundReasonText" type="text"
				class="txtFldMax" value="" disabled="disabled"></td></tr></table>  </td>
		</tr>
		<tr>
			<td class="LABEL" width="25%">Refund Date :</td>
			<td width="75%" class="CONTROL"> 
			<input type="hidden" name="strOfflineRefundDate"
				value="${LFNoTransBean.strCurrentDate}">
			<bean:write name="LFNoTransBean" property="strCurrentDate" />
			
			</td>
		</tr>
	</table>
	</div>

	
	 
	 
	 <div id ="disBnR" style="display: block;">
	  
	 
	 
	  <table class="TABLEWIDTH" align="center" cellpadding="0" cellspacing="">
		<tr class="FOOTER">
			<td colspan="4" width='100%' align="left">Advance Details</label></td>
		</tr>
	  </table>
	  
	  
	  
	  
	  
	 
	   <table class="TABLEWIDTH" align="center" border="0" cellspacing ="1px">
		<tr> 
			<td width="25%" class='LABEL' colspan='1'><div align="left">Advance Amount
		        <input name="strAdvacnceAmount" type="text" maxlength="5" class="txtFldMax" value="" onkeypress="return validateData(event,8);" onblur="getAdvanceAmount();">Rs</div>
			</td>
			
			
		</tr>
		<%-- <tr> 
			<td width="25%" class='LABEL' colspan='1'><div align="right">Relation With Patient:</div></td>
			<td width="25%" class="CONTROL" colspan='1'>
		        <select name="strRelationId" class='comboNormal'><bean:write name="LFNoTransBean" property="strRelatinDetails" filter="false"/></select>
			</td>
			<td width="25%" class='LABEL' colspan='1'><div align="right">Card Validity:</div></td>
			<td width="25%" class="CONTROL" colspan='1'>
		         <date:date name="strCardValidity" value=""/>   
		</td>
		</tr> --%>
<!-- 		 <tr>  -->
<!-- 			<td width="24%" class='LABEL' colspan='1'><div align="right">Waiver By:</div></td> -->
<!-- 			<td width="76%" class="CONTROL" colspan='3'> -->
<%-- 		        <select name="strRmk"><bean:write name="LFNoTransBean" property="strRmk" filter="false"/></select>	         --%>
<!-- 		</td> -->
<!-- 		</tr> -->
		
	  </table>     
  	</div>
	
	<table class="TABLEWIDTH" align="center" cellpadding="1px" cellspacing="1px">
		<tr>
			<td colspan="4" width='100%'><div class='line'><label class='DIVLABEL'>Payment</label></div></td>
		</tr>
	</table>
	<table class="TABLEWIDTH" align="center" cellpadding="1px"
		cellspacing="1px">
		<tr>
			<td class="multiLabel" width="20%"><font color="red">*</font>Payment
			Mode</td>
			<td class="multiLabel" width="45%">Payment
			Details</td>
			<td class="multiLabel" width="30%"><div style="cursor: pointer;" onclick="openDependentPopup('/HBIMS/billing/transactions/cashDenomination_billTrans.jsp',event,400,350);"><font color="red">*</font>Amount
			(<img src='/HBIMS/hisglobal/images/INR.png'>)</div></td>
			<td class="multiLabel" width="3%" style="display: none;"><img
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
			<td width='60%' class='LABEL'>Grand Total (<img src='/HBIMS/hisglobal/images/INR.png'>)</td>
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
			<td width='60%' class='LABEL'>Max Benefit From Client (<img src='/HBIMS/hisglobal/images/INR.png'>)</td>
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
			(<img src='/HBIMS/hisglobal/images/INR.png'>)</td>
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
			(<img src='/HBIMS/hisglobal/images/INR.png'>)</td>
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

			<td width="60%" class="LABEL">Net Payment / Refund Amount (<img src='/HBIMS/hisglobal/images/INR.png'>)</td>
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

			<td width="60%" class="LABEL">Change (<img src='/HBIMS/hisglobal/images/INR.png'>)</td>
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
			<table width='100%' align="center" cellpadding="1px" cellspacing="1px">				
				<tr>
					<td class="CONTROL" colspan="2" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Patient Category Combo Rules:Admitted Patient Then Default Selected Category Admitted Treatment Category. 
					OPD Patient Then Episode Treatment Category. If No Episode Treatment Category Then Primary Category. Department/Episode Is Last Visted Department/Episode
					</td>
				</tr>
				<tr class=FOOTER>
					<td colspan="2"></td>
				</tr>
			</table>
			</div>
			</td>
		</tr>
	</table>
<!-- 	<div id="saveid">
	<table border="0" class="TABLEWIDTH" align="center" cellpadding="1px"
		cellspacing="1px">
		<tr>

			<td align="center" ><img  style="cursor: pointer" name="saveButton" id= "savedivid" tabindex="1"
				src="../../hisglobal/images/btn-sv.png" 
				onClick="return LFAccountServices();" onkeypress="onKeyPressLogic('1',event);"   title="Save Record" />
			<img style="cursor: pointer"  tabindex="1"
				src="../../hisglobal/images/btn-clr.png" onClick="clearFunc(document.forms[0].strCounterMode.value);"
				  onkeypress="onKeyPressLogic('2',event);"  title="Clear Content">
			<img style="cursor: pointer"  tabindex="1"
				src="../../hisglobal/images/btn-ccl.png" onClick="cancelFunc();"
				 onkeypress="onKeyPressLogic('3',event);"  title="Cancel Process"></td>
		</tr>
	</table>
	</div>-->
	<br>
<div align="center" id="saveid">					 
							<a href="#" class="button" id="" onClick="return LFAccountServices();" onkeypress="onKeyPressLogic('1',event);"><span class="save">Save</span></a>
							<a href="#" class="button"	onClick="clearFunc(document.forms[0].strCounterMode.value);"><span class="clear">Clear</span></a> 
							<a href="#" class="button" onClick="cancelFunc(); "onkeypress="onKeyPressLogic('3',event);"><span class="cancel">Cancel</span></a>
					</div>
	
	
	<input type="hidden" name="hmode" />
	<input type='hidden' name='strPkgServiceFlag' value='0' />
	<input type="hidden" name="strTempCtDate"
		value="${LFNoTransBean.strCurrentDate}" />
	<input type="hidden" name="strIpdThirdPartyBenefit"
		value="${LFNoTransBean.strIpdThirdPartyBenefit}" />
	<input type="hidden" name="strEmergencyThirdPartyBenefit"
		value="${LFNoTransBean.strEmergencyThirdPartyBenefit}" />
	<input type="hidden" name="strOpdThirdPartyBenefit"
		value="${LFNoTransBean.strOpdThirdPartyBenefit}" />

	<input type="hidden" name="strOfflineIpdPenaltyVal"
		value="${LFNoTransBean.strOfflineIpdPenaltyVal}" />
	<input type="hidden" name="strOfflineOpdPenaltyVal"
		value="${LFNoTransBean.strOfflineOpdPenaltyVal}" />
	<input type="hidden" name="strOfflineEmergencyPenaltyVal"
		value="${LFNoTransBean.strOfflineEmergencyPenaltyVal}" />

	<input type='hidden' name='strOffLineRefundAdvanceAccountNo' value='0' />


	<%-- current state represents  1 - Online and 2 - Off-line--%>
	<input type="hidden" name="currentState"
		value="${LFNoTransBean.currentState}" />
	<input type="hidden" name="currentBserviceId" value='1' />
	<input type="hidden" name="currentReceiptType" value='1' />
	<input type="hidden" name="currentHospService" value='1' />
	
	<input type="hidden" name="strTempBillNo" value="${LFNoTransBean.strTempBillNo}" />
	<input type="hidden" name="strTempReceiptNo" value="${LFNoTransBean.strTempReceiptNo}" />


<input type="hidden" name="strIsIpdDiscount" value="${LFNoTransBean.strIsIpdDiscount}" />
<input type="hidden" name="strIsOpdDiscount" value="${LFNoTransBean.strIsOpdDiscount}" />
<input type="hidden" name="strIsEmergencyDiscount" value="${LFNoTransBean.strIsEmergencyDiscount}" />
	
<input type="hidden" name="strCounterMode" value="${LFNoTransBean.strCounterMode}" />
<input type="hidden" name="strIsWithoutCrNoRequired" value="${LFNoTransBean.strIsWithoutCrNoRequired}" />
<input type="hidden" name="strPrintMessageLimit" value="${LFNoTransBean.strPrintMessageLimit}" />

<input type="hidden" name="strFreeCategory" value="${LFNoTransBean.strFreeCategory}" />
<input type="hidden" name="strIsApprovalRequired" value="${LFNoTransBean.strIsApprovalRequired}" />
<input type="hidden" name="strIsAdvanceRequired" value="${LFNoTransBean.strIsAdvanceRequired}" />
<input type="hidden" name="strConsumableCharge" value="${LFNoTransBean.strConsumableCharge}" />
<input type="hidden" name="strOfflineTotalPayAmountWithoutConsumable" value="${LFNoTransBean.strOfflineTotalPayAmountWithoutConsumable}" />
<input type="hidden" name="strGroupIdForConsumableConcatenated" value="${LFNoTransBean.strGroupIdForConsumableConcatenated}" />
<input type="hidden" name="strConsumableChargesGroupId" value="${LFNoTransBean.strConsumableChargesGroupId}" />
<input type="hidden" name="strConsumableChargesTariffCode" value="${LFNoTransBean.strConsumableChargesTariffCode}" />
<input type="hidden" name="strCreditLetterValidity" value="${LFNoTransBean.strCreditLetterValidity}" />
<input type="hidden" name="isOpenPopUp" value="${LFNoTransBean.isOpenPopUp}"/>
<input type="hidden" name="filePath" value="${LFNoTransBean.filePath}"/>
<input type="hidden" name="printMode" value="${LFNoTransBean.printMode}"/>
<input type="hidden" name="strLFAccountNo" value="${LFNoTransBean.strLFAccountNo}" />
<input type="hidden" name="strLFAccountOpenDate" value="${LFNoTransBean.strLFAccountOpenDate}" />
<input type="hidden" name="strLFDepositedAmount" value="${LFNoTransBean.strLFDepositedAmount}"/>
<input type="hidden" name="strLFBalanceAmount" value="${LFNoTransBean.strLFBalanceAmount}"/>
<input type="hidden" name="strLFAccountStatus" value="${LFNoTransBean.strLFAccountStatus}"/>
<input type="hidden" name="strLFAccountSummary" value="${LFNoTransBean.strLFAccountSummary}"/>
	
</html:form>
<jsp:include page="multirow_LFNo_offline_billtrans.jsp"></jsp:include>
<jsp:include page="dropdown_LFNo_trans.jsp"></jsp:include>

</body>
</html>