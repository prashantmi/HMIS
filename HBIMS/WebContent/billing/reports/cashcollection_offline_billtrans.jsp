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

<script language="Javascript" src="../js/cashcollection_offline_trans.js"></script>


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
.no-close .ui-dialog-titlebar-close {display: none }
</style>
</head>
<body
	onLoad="onLoadLogics();addRows(new Array('strOfflinePaymentMode','strOfflinePaymentDtls','strOfflineAmount'),new Array('s','t','t'),'3','1','I');openPrintPopUp();"
	onfocus="checkPopUp();" onUnload="closePopUp();">   <!-- changePayMode(); -->  
<script language="Javascript" src="../../hisglobal/js/wz_tooltip.js"></script>
<html:form action="/transactions/CashCollectionOfflineTransCNT" method="post" enctype="multipart/form-data">



	<div id="blanket" style="display: none;"></div>
	<div id="nonPrintable">
	
	<center>
		<div id="errMsg" class="errMsg"><bean:write name="cashCollectionOfflineTransBean" property="strErrMsg" filter="false" /></div>
		<div id="normalMsg" class="normalMsg"><bean:write name="cashCollectionOfflineTransBean" property="strNormalMsg" filter="false" /></div>
		<div id="warningMsg" class="warningMsg"><bean:write name="cashCollectionOfflineTransBean" property="strWarningMsg" filter="false" /></div>		
		<tag:tab tabList="${cashCollectionOfflineTransBean.lhm}" selectedTab="OFFLINE" align="center" width="TABLEWIDTH"></tag:tab>
	</center>

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
						onkeypress='return validateData(event,11);' maxlength="50"/></td>
					<td class='LABEL'><font color="red">*</font>Cheque / DD No.(Max : 15 Digits)</td>
					<td class='CONTROL'><input type='text' name='strChequeDDNo' tabindex="1"
						class='txtFldDate' onkeypress="return validateData(event,5);" maxlength="15"/></td>
				</tr>
				<tr>
					<td colspan='2' class='LABEL'><font color="red">*</font>Cheque
					/ DD Issue Date</td>
					<td colspan='2' class='CONTROL'><input type="text" tabindex="1"
						class="txtFldDate" maxlength="11" name="strChequeDDDate"
						value="${cashCollectionOfflineTransBean.strCurrentDate }" >
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
	
	<div class="popUpDiv" id="payDtlWalletMenu" style="display: none;">
		<table bgcolor="white">
		 <tr>
			<td>
			<div class='popup' id='payDtlWalletMenuInner' style="display: block">
				<table width='400'>
					<tr class='WALLETHEADER'>
						<th colspan='4' align='left'>&nbsp;Payment Details &gt;&gt;Patient Wallet</th>
					</tr>
					<tr>
						<td class='WALLETLABEL'>Wallet No.:&nbsp;</td>
						<td class='WALLETCONTROL'>
							<bean:write name="cashCollectionOfflineTransBean" property="strWalletNoMasked" filter="false"/>
							<input type="hidden" name="strWalletNo" value="${cashCollectionOfflineTransBean.strWalletNo}" />
							<input type="hidden" name="strWalletNoMasked" value="${cashCollectionOfflineTransBean.strWalletNoMasked}" />
							<input type="hidden" name="strMobileNo" value="${cashCollectionOfflineTransBean.strMobileNo}" />
						</td>
						<td class='WALLETLABEL'>Patient Wallet Balance:&nbsp;</td>
						<td class='WALLETCONTROL'>
							<bean:write name="cashCollectionOfflineTransBean" property="strAvlWalletMoney" filter="false"/>&nbsp;<img src='/HBIMS/hisglobal/images/INR.png'>
							<input type="hidden" name="strAvlWalletMoney" value="${cashCollectionOfflineTransBean.strAvlWalletMoney}" />
						</td>
					</tr>					
					<tr class='FOOTER'>
						<td colspan='4'>&nbsp;</td>
					</tr>
				</table>
	
				<center>
				<table width='300' border='0' cellpadding='0' cellspacing='0'>
					<tr>
						<td>
							<center>
							<img style="cursor: hand; cursor: pointer" src='../../hisglobal/images/btn-ok.png' tabindex="1" onClick='return validateWallet();'> 
							<img style="cursor: hand; cursor: pointer" src='../../hisglobal/images/btn-ccl.png' tabindex="1" onClick="hidePayDetails('payDtlWalletMenu');"></center>
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
					<td> <input type="hidden" name="strConfirmationType" value="${cashCollectionOfflineTransBean.strConfirmationType}">
					
					<logic:equal name="cashCollectionOfflineTransBean"  property="strConfirmationType" value="0">
					<center> <input type="button" name="billConfirmCancel" tabindex="1" value="Cancel" class="btn"  onclick="return confirmCancel();" onblur="document.forms[0].billConfirmOk.focus();" > 
					<input type="button" name="billConfirmOk" class="btn" tabindex="1" value="Ok" onblur="document.forms[0].billConfirmCancel.focus();"   onclick="return confirmOk();">  
					</center>
					</logic:equal>
					<logic:equal name="cashCollectionOfflineTransBean"  property="strConfirmationType" value="1">
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
				<%-- <tr>
					<td class="LABEL" id='totalCardAmtDivId1' colspan='4'><font color="red">#</font><font size="2">Final Transaction Amount(with Surcharge)(<img src='/HBIMS/hisglobal/images/INR.png'>)&nbsp;0.00</font></td>
				</tr>--%>
				<tr>
					<td class='LABEL'><font color="red">*</font>Bank Name</td>
					<td class='CONTROL'><input type='text' name='strPayCDBankName' tabindex="1"
						class='txtFldMax' onkeypress="return validateData(event,11);" maxlength="50"/></td>
					<td class='LABEL'><font color="red">*</font>Card No. (Last 4 Digits)</td>
					<td class='CONTROL'><input type='password' name='strCardNo' tabindex="1"
						class='txtFldNormal' maxlength="4" onkeypress="return validateData(event,5);" /></td>
				</tr>
				<tr>
					<td class='LABEL'><font color="red">*</font>Transaction No.(Max : 15 Digits)</td>
					<td class='CONTROL'><input type='text' name='strAuthNo' tabindex="1"
						class='txtFldNormal' onkeypress="return validateData(event,5);" maxlength="15"/></td>
					<td class='LABEL'><font color="red">*</font>Transaction Date</td>
					<td class='CONTROL'><input type="text" class="txtFldDate" tabindex="1"
						maxlength="11" name="strAuthDate"
						value="${cashCollectionOfflineTransBean.strCurrentDate }" >
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
						<option value='4'>Others</option>
					</select></td>
				</tr>

				<tr class='FOOTER'>
					<td colspan='4' align='left'><font color="red">#</font>Ensure that the Transaction/Card Swap is Successful</td>
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
					<bean:write name="cashCollectionOfflineTransBean" property="strClientNameContents" filter="false"/>
					</select></td>
				</tr>
				<tr>
					<td class='LABEL'><font color="red">*</font>Auth No.(Max : 15 Digits)</td>
					<td class='CONTROL'><input type='text' name='strClientAuthNo' tabindex="1"
						class='txtFldNormal' onkeypress="return validateData(event,5);" maxlength="15"/></td>
					<td class='LABEL'><font color="red">*</font>Auth Date</td>
					<td class='CONTROL'><input type="text" class="txtFldDate" tabindex="1"
						maxlength="11" name="strClientAuthDate"
						value="${cashCollectionOfflineTransBean.strCurrentDate }" >
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
	
	<div class="popUpDiv" id="cardAmtDiv" style="display: none;">
	<table bgcolor="white">
				<tr>
				<td>
				<div class='popup' id='cardAmtMenuInner' style="display: block">
			    <table width='400'>
			    <tr class='HEADER'>
					<th colspan='2' align='left'>&nbsp;Transaction Alert</th>
				</tr>
				<tr>
					<td class="LABEL" id='totalBillAmtDivId'>Billed Amount(<img src='/HBIMS/hisglobal/images/INR.png'>)&nbsp;0.00</td>
					<td class="LABEL" id='totalSurAmtDivId'>Surcharge Amount(<img src='/HBIMS/hisglobal/images/INR.png'>)&nbsp;0.00</td>
				</tr>
			    <tr>
					<td class="LABEL" id='totalCardAmtDivId' colspan='2'><font color="red">#</font><font size="2">Final Transaction Amount(with Surcharge)(<img src='/HBIMS/hisglobal/images/INR.png'>)&nbsp;0.00</font></td>
				</tr>
				<tr class='FOOTER'>
					<td colspan='2'><font color="red">#</font>Please Initiate the Card Transaction/Swipe Card as follows:
					</td>
				</tr>
				</table>
			<center>
			<table width='300' border='0' cellpadding='0' cellspacing='0'>
				<tr>
					<td>
					<center><img style="cursor: hand; cursor: pointer"
						src='../../hisglobal/images/btn-ok.png' tabindex="1"
						onClick=' return okcd();'> <img
						style="cursor: hand; cursor: pointer"
						src='../../hisglobal/images/btn-ccl.png' tabindex="1"
						onClick="return ccd();"></center>
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
						title="_div_popup_cntl" class='comboNormal' >
						<option value='1'>Fixed</option>
						<option value='2' selected>Percentage</option>
					</select></td>
				</tr>
				<tr>
					<td class='LABEL'><font color="red">*</font>Discount By</td>
					<td class='CONTROL'><select title="_div_popup_cntl" 
						class='comboMax' name=''>
						<bean:write name="cashCollectionOfflineTransBean"
							property="strOfflineDiscountApprovedByDetails" filter="false" />
					</select></td>
					<td class='LABEL'><font color="red">*</font>Discount Reason</td>
					<td class='CONTROL'><select title="_div_popup_cntl"
						class='comboNormal'>
						<bean:write name="cashCollectionOfflineTransBean"
							property="strOfflineDiscountRemarksDetails" filter="false" />
					</select></td>
				</tr>
				<tr>
					<td colspan='2' class='LABEL'><font color="red">*</font>Discount
					Date</td>
					<td colspan='2' class='CONTROL'><date:date name=""
						value="${cashCollectionOfflineTransBean.strCurrentDate}" ></date:date></td>
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
						<option value='2' selected>Percentage</option>
					</select></td>
				</tr>
				<tr>
					<td class='LABEL'><font color="red">*</font>Discount By</td>
					<td class='CONTROL'><select title="_div_popup_cntl"
						class='comboMax' name="strOffLineTariffDiscountBy">
						<bean:write name="cashCollectionOfflineTransBean"
							property="strOfflineDiscountApprovedByDetails" filter="false" />
					</select></td>
					<td class='LABEL'><font color="red">*</font>Discount Reason</td>
					<td class='CONTROL'><select title="_div_popup_cntl"
						class='comboNormal' name="strOffLineTariffDiscountReason"
						onchange="setReasonText();">
						<bean:write name="cashCollectionOfflineTransBean"
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
						value="${cashCollectionOfflineTransBean.strCurrentDate }" >
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


	<div id='refundTariffPopupDetailsDivId' class='popup' style="display: none"></div>

	<div id='reconcilTariffPopupDetailsDivId' class='popup' style="display: none"></div>


	<table class="TABLEWIDTH" align="center" cellspacing="1px"
		cellpadding="1px">
		<tr class="HEADER">
			<td>
			<table width="100%" cellpadding="0" cellspacing="0">
				<tr>
					<td colspan="2">
					<div style="display:none">
					<a  id="cashCollection" style="cursor: pointer;display: block;" onclick="openDependentPopup('BillingCNT.cnt?hmode=CASHCOLLECTIONPOPUP',event,250,600);" 
					title="Cash Collection Detail"><font color="white" style=""><u>Total Cash</u></font></a>
					</div>
					</td>
					<td>
					<div align="right">
					<logic:equal value="2" name="cashCollectionOfflineTransBean" property="strCounterMode">
					<html:checkbox
						name="cashCollectionOfflineTransBean" property="strIsOnline" value="1"
						onclick="setOnlineOffLineMode(this);"></html:checkbox> Online&nbsp;
					</logic:equal>
					
					<logic:notEqual value="2" name="cashCollectionOfflineTransBean" property="strCounterMode">
					<div style="display: none">
					<html:checkbox
						name="cashCollectionOfflineTransBean" property="strIsOnline" value="1" 
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
	<logic:equal value="1" name="cashCollectionOfflineTransBean" property="strIsWithoutCrNoRequired">
	<table class="TABLEWIDTH" align="center" cellpadding="1px" cellspacing="1px">
		<tr>

			<td class="LABEL" colspan="4">
			
			<html:radio
				name="cashCollectionOfflineTransBean" property="strPatientMode" value="1"
				onclick="selectPatientMode(this);">With CR No.</html:radio> <html:radio
				name="cashCollectionOfflineTransBean" property="strPatientMode" value="2"
				onclick="selectPatientMode(this);">Without CR No.</html:radio></td>
		</tr>
	</table>
	</logic:equal>
	
	<logic:notEqual value="1" name="cashCollectionOfflineTransBean" property="strIsWithoutCrNoRequired">
	<div style="display: none">
	<table class="TABLEWIDTH" align="center" cellpadding="1px" cellspacing="1px">
		<tr>

			<td class="LABEL" colspan="4">
			
			<html:radio
				name="cashCollectionOfflineTransBean" property="strPatientMode" value="1"
				onclick="selectPatientMode(this);">With CR No.</html:radio> <html:radio
				name="cashCollectionOfflineTransBean" property="strPatientMode" value="2"
				onclick="selectPatientMode(this);">Without CR No.</html:radio></td>
		</tr>
	</table>
	</div>
	</logic:notEqual>
	
	<table class="TABLEWIDTH" align="center" cellpadding="1px" cellspacing="1px">
		<tr>
			<td colspan="1" class="LABEL" width="25%"><b><font color="red">*</font>Hospital Service</b></td>
			<td class="CONTROL" colspan="1" width="25%"><select class="comboNormal" name="strOffLineHospitalService" onchange="getBillService();changeComboClass(this);">
				<bean:write name="cashCollectionOfflineTransBean" property="strHospitalServiceDetails" filter="false" />
			</select>&nbsp;</td>
			<td width="25%" class="LABEL" colspan="1"><font color="red">*</font>Billing Services</td>
			<td width="25%" class="CONTROL" colspan="1">
			<div id='billServiceDivId'><select class="comboNormal" name="strOffLineBillingService" onchange="resetTotalAmount(this);">
				<bean:write name="cashCollectionOfflineTransBean" property="strBillingServiceDetails" filter="false" />
			</select></div>
			</td>
		</tr>
		<tr>
			 <td width="25%" colspan="1" class="LABEL"><div align='right'>&nbsp;<font color="red">*</font>CR No.</div></td>
			 <td width="25%" colspan="1" class="CONTROL">
			 	<div align='left'>
				 	<crNo:crNo id="strCrNoId" name="strCrNo" value="${cashCollectionOfflineTransBean.strCrNo}" js="onkeypress='return initGoFunc(event);'"></crNo:crNo>
				 	<img src="../../hisglobal/images/Go.png" style="cursor: hand; cursor: pointer" title="Get Bill Details" name="go" onClick="return goFunc();" onKeyPress="return goFunc();" align="middle"> &nbsp;&nbsp;
				 </div>
			</td>			
			<td width="25%" class="LABEL" colspan="1"><font color="red">*</font>Request Type</td>
			<td width="25%" class="CONTROL" colspan="1">			
				<logic:equal name="cashCollectionOfflineTransBean" property="strIsRefundReq" value="1">
					<html:select name="cashCollectionOfflineTransBean" property="strOffLineRequestType" styleClass="comboNormal"  onchange="getBillService();">
						<html:option value="1">Receipt</html:option>
						<html:option value="2">Refund</html:option>
						<html:option value="3">Estimation</html:option>
					</html:select>
				</logic:equal>			
				<logic:equal name="cashCollectionOfflineTransBean" property="strIsRefundReq" value="0">
					<html:select name="cashCollectionOfflineTransBean" property="strOffLineRequestType" styleClass="comboNormal"  onchange="getBillService();">
						<html:option value="1">Receipt</html:option>
						\<html:option value="3">Estimation</html:option>
					</html:select>
				</logic:equal>			
			 </td>
		</tr>
		</table>
	
	 <div id="offlineDetailsDivId" style="display: none;">
	 
	 <bean:write name="cashCollectionOfflineTransBean" property="strPatientDetailsView" filter="false" />
	<!-- off-line start -->
 
	<div id='offlineClientDivId'><bean:write
		name="cashCollectionOfflineTransBean" property="strOfflineClientDetailsView"
		filter="false" /></div>
	
		
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
				<bean:write name="cashCollectionOfflineTransBean"
					property="strRaisingDepartmentDetails" filter="false" />
			</select></div>
			</td>
			<td width="25%" class="LABEL">Episode</td>
			<td width="25%" class="CONTROL">
			<div id='episodeDivId'><select class="comboNormal"
				name="strOffLineEpisode" onchange='changeEpisodeClass(this)'>
				<bean:write name="cashCollectionOfflineTransBean"
					property="strEpisodeDetails" filter="false" />
			</select></div>
			</td>
		</tr>
		<tr>
			<td width="25%" class="LABEL"><font color="red">*</font>Patient Category</td>
			<td width="25%" class="CONTROL">
			<div id='offlineTreatmentCategoryDivId'>
				<select class="comboNormal"  name="strOffLineTreatmentCategory" onchange="poorFreeCheck('strOffLineTreatmentCategory'),getPartAccDtls(document.getElementsByName('strOffLineWard')[0]),chkPaidCredit();resetAmt();getPaymentModeByPatCat();">
					<bean:write name="cashCollectionOfflineTransBean" property="strTreatmentCategoryDetails" filter="false" />
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
						<bean:write name="cashCollectionOfflineTransBean"
							property="strWardDetails" filter="false" />
					</select></div>
					</td>

				</tr>

			</table>			
			</div>
			</td>

		</tr>

	</table>
	
	<div id="specialWardDivId">
	<table class="TABLEWIDTH" align="center" cellpadding="1px"
		cellspacing="1px">
		<tr>
			<td width="25%" class="LABEL">Ward Name</td>
			<td width="75%" class="CONTROL" colspan="3">
			
			<div id='offlineSpecialWardDivId'><select class="comboNormal"
						name="strOffLineSpecialWard"
						onchange="changeWard();">
						<bean:write name="cashCollectionOfflineTransBean"
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
	
	
	
	
	
	<div id="advanceDtl" style="display: none">
	</div>

	<div id='partPayAdvanceDivId'><bean:write
		name="cashCollectionOfflineTransBean"
		property="strPartPayAdvanceAmountDetails" filter="false" /></div>
		
    <div id='packageDivId'><bean:write
		name="cashCollectionOfflineTransBean"
		property="strPackageDetails" filter="false" /></div>
		
		
	<div id="RefundadvanceDtlsDiv" style="display: none">
		<table class="TABLEWIDTH" align="center" cellpadding="1px" cellspacing="1px">
			<tr>
	
				<td class="TITLE" colspan="4">
				
					<html:radio
						name="cashCollectionOfflineTransBean" property="strAdvCancelRefund" value="1"
						onclick="advanceRefundCancel(this);" style="display:none;"><!-- Advance Cancel --> 
					</html:radio>
					 <html:radio
						name="cashCollectionOfflineTransBean" property="strAdvCancelRefund"  value="2"
						onclick="advanceRefundCancel(this);">Advance Refund</html:radio>
				</td>
			</tr>
		</table>
	
	</div>
	
	
	
	
	
	
	

	<div id="otherRefundDetailsDivId" style="display: none">
	

	<div id='advPayDtl'><bean:write
		name="cashCollectionOfflineTransBean"
		property="advPayDtl" filter="false" /></div>
	
	<table class='TABLEWIDTH' border="0" cellpadding="1px"
		cellspacing="1px" align="center">
		<tr>
			<td width='25%' class='LABEL'><font color="red">*</font>Refund
			By:</td>
			<td width="75%" class="CONTROL"><select
				name="strOffLineRefundBy">
				<bean:write name="cashCollectionOfflineTransBean"
					property="strOffLineRefundByList" filter="false" />
			</select></td>
		</tr>
		<tr>
			<td width="25%" class="LABEL"><font color="red">*</font>Refund
			Reason:</td>
			<td width="75%" class="CONTROL"> <table><tr><td class="CONTROL"><select
				name="strOffLineRefundReason" onChange="setRefundRemarksText();">
				<bean:write name="cashCollectionOfflineTransBean"
					property="strOffLineRefundReasonDetails" filter="false" />
			</select></td><td><input name="strOffLineRefundReasonText" type="text"
				class="txtFldMax" value="" disabled="disabled"></td></tr></table>  </td>
		</tr>
		<tr>
			<td class="LABEL" width="25%">Refund Date :</td>
			<td width="75%" class="CONTROL"> 
			<input type="hidden" name="strOfflineRefundDate"
				value="${cashCollectionOfflineTransBean.strCurrentDate}">
			<bean:write name="cashCollectionOfflineTransBean" property="strCurrentDate" />
			
			</td>
		</tr>
	</table>
	</div>
	
	
	
<div id="accountCatDiv" style="display: none">
		<table class="TABLEWIDTH" align="center" cellpadding="1px" cellspacing="1px">
			<tr>
	
						<td class="TITLE" colspan="4">   <html:radio
						name="cashCollectionOfflineTransBean" property="accountCatDivCheked"  value="1"
						onclick="changeAccCat(this)">Change Account Category</html:radio></td>
						
						<td  class="TITLE" style="display:none;" > <html:radio
						name="cashCollectionOfflineTransBean"  property="accountCatDivCheked"  value="2"
						onclick="changeAccCat(this)">Change Account Category</html:radio></td>
				</tr> <tr>
						<%-- 	<div id="changeAccCat" style="display: none"> --%>
								<table id="changeAccCat" style="display: none" class='TABLEWIDTH' border="0" cellpadding="1px" cellspacing="1px" align="center">
									<tr>
										<td width='25%' class='LABEL'><font color="red">*</font>Account Category:</td>
										<td width="75%" class="CONTROL"><select
											name="accountCategory">
												<bean:write name="cashCollectionOfflineTransBean"
													property="accountCategoryList" filter="false" />
										</select></td>
									</tr>
								
								</table>
						<!-- 	</div>  -->
				
				
			</tr>
		</table>
	
	</div>


	<logic:equal name="cashCollectionOfflineTransBean"  property="currentBserviceId" value="13">
	<div id="apppack">
	<table class="TABLEWIDTH" align="center" cellspacing="1px">
	<tr>
	       <td width="25" class="LABEL"><font color="red" id="mandCRId">*</font>Apply OT Package</td>
    	   <td width="25%" class="LABEL"><div id="pack" align="left">
	    	   <select name="strPackageApply" class="comboNormal" onChange="setAdvAmt();">
			   <bean:write name="cashCollectionOfflineTransBean" property="strPackageComboValues" filter="false"/>
			   </select></div>
    	   </td>
       	   <td width="25%" class="LABEL"><font color="red" id="mandCRId">*</font>Date of Applying Package</td>
    	   <td width="25%" class="CONTROL">
    	       <date:date name="strPackAppDate" value="${cashCollectionOfflineTransBean.strCurrentDate}"></date:date>
    	   </td>
    </tr>
    </table>
    </div>
    </logic:equal>



	<div id='offlineTariffDivId'>
	<table class="TABLEWIDTH" align="center" cellpadding="0" cellspacing="0">
		<tr>
			<td colspan='12'>
			<table cellpadding="0" cellspacing="0" width="100%">
				<tr>
					<td class="TITLE" width='20%'><a style="display: none;" onclick="showTariffSearchPopupOnClick();"><font color="blue" style="cursor: pointer;"><u>Tariff Detail</u></font></a></td>
					<td width='20%' class='TITLE'>
					<div id='trfCodeDivId' align="right">
					<label class='blink'>Enter Tariff Code</label>
					<input type="text" class="txtFldBig" autocomplete='off' name="strTariffCode" id="strTariffCode" tabindex="1"  maxlength="15"  onkeypress="return validateData(event,8);" onkeyup="getTariffByCode(this , event),showTariffCodeSearchPopup(event,'strTariffCode','0'),chkPaidCredit();">
					</div>
					</td>
					<td class="TITLE" width='25%' style="display:none;"><div align="right">
					<!--<font color="red">*</font>Client Name-->
					Add New Credit Letter
					</div></td>
					<td width='25%' class='TITLE'>
					<img onkeypress='onPressingEnter(this,event)' id='addCreditLetter' style='cursor: pointer; display:none;' tabindex="2" onclick='addNewCreditLetter(this,1)' src='../../hisglobal/images/plus.gif' align='middle'>
					<!--  <select name="strPayClientName" class="comboMax" tabindex="1" >
					<bean:write name="cashCollectionOfflineTransBean" property="strClientNameContents" filter="false"/>
					</select>-->
					</td>
					<td width='10%' class='TITLE'>
					<div id='groupDivId' align="right">
					<table border='0' cellpadding='0' cellspacing='0'>
					<tr><td class="TITLE">Group</td>
					<td class="TITLE">
					<select tabindex="2" name="strOffLineGroup" class="comboMax">
						<bean:write name="cashCollectionOfflineTransBean" property="strOfflineGroupDetails" filter="false" />
					</select></td></tr></table></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		</table>
		<table class="TABLEWIDTH" align="center" border="1px" style=" border-collapse: collapse;" align="center" bordercolor="#0E507C" cellpadding="1px" cellspacing="0">
		<tr>
			<td class="multiLabelNew" width="18%"><font color="red">*</font>Tariff	Name</td>
			<td class="multiLabelNew" width="7%">Rate / Unit</td>
			<td class="multiLabelNew" width="5%"><font color="red">*</font>Qty.</td>
			<td class="multiLabelNew" width="7%" style='display:none;'><font color="red">*</font>Unit</td>			
			<td class="multiLabelNew" width="7%" style="display:none"  >S. Tax (%)<input type="hidden" name="strOfflineTotalServiceTaxAmount" value="0"></td>
			<td class="multiLabelNew" width="6%">Dis. / Unit<input type="hidden" name="strOfflineTotalDiscountAmount" value="0"></td>
			<td class="multiLabelNew" width="7%">Payment Type</td>
			<td class="multiLabelNew" width="19%">Credit Letter No./Date/Org/Limit<!--  <input type='checkbox' name='strSameCreditDtlsChk' onclick='applySameCreditDtls(this)'>Apply All--></td>
			<!--<td class="multiLabel" width="12%">Credit Letter Date<input type='checkbox' name='strSameCreditDtlsChk' onclick='applySameCreditDtls(this)'>Apply All</td>-->			
			<td class="multiLabelNew" width="13%">Upload Letter[<font color='red'>JP(E)G/PDF</font>]</td>
			<td class="multiLabelNew" width="8%">Net Amt.(<font color='red'>*</font>)<input type="hidden" name="strOfflineTotalActualTariffAmount" value="0"></td>
		 	
			
<!-- 			<td> -->
<!--  		  	<input type="file" name="uploadedFile2[0]" accept="image/*,application/pdf" id="uploadedFile#delIndex#"/> -->
<!--  		  	<input type="hidden" name="MAX_FILE_SIZE" value="2000000" />   -->
<!--  		 	</td>  -->
 		 
<!--  		 	<td> -->
<!--  		  	<input type="file" name="uploadedFile2[1]" accept="image/*,application/pdf" id="uploadedFile#delIndex#"/> -->
<!--  		  	<input type="hidden" name="MAX_FILE_SIZE" value="2000000" />   -->
<!--  		 	</td>  -->
			
			
			
			<td class="multiLabel" width="1%"><img style="cursor: hand; cursor: pointer" tabindex="2" src="/HBIMS/hisglobal/images/plus.gif" onclick="generateRows()"></td>
		<!--	
		</tr>
		<tr>
			<td colspan="12" width='100%'><div class='lineContinue'>&nbsp;</div></td></tr>	-->		
	</table>
	<div id="id2"></div>
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
	 
	 <div id ="creditDtlsDivId" style="display: block;"><bDtl:onlnCltDtl crNo="${cashCollectionOfflineTransBean.strCrNo}" ></bDtl:onlnCltDtl></div>
	 
	 <%--  
	 <div id ="disBnR" style="display: none;">
	  <table class="NEWTABLEWIDTH" align="center" cellpadding="0" cellspacing="">
		<tr>
			<td colspan="4" width='100%'><div class='line'><label class='DIVLABEL'>Other Credit Details</label></div></td>
		</tr>
	  </table>
	  
	  
	  
	  
	  
	 
	   <table class="TABLEWIDTH" align="center" border="0" cellspacing ="1px" style="display: none;">
		<tr> 
			<td width="25%" class='LABEL' colspan='1'><div align="right">Employee ID:()Max:20 Digits)</div></td>
			<td width="25%" class="CONTROL" colspan='1'>
		        <input name="strEmployeeId" type="text"  class="txtFldMax" value="" onkeypress="return validateData(event,8);" maxlength="20">
			</td>
			<td width="25%" class='LABEL' colspan='1'><div align="right">Employee Name:</div></td>
			<td width="25%" class="CONTROL" colspan='1'>
		        <input name="strEmployeeName" type="text"  class="txtFldMax" value="" onkeypress="return validateData(event,11);" maxlength="50">
			</td>
		</tr>
		<tr> 
			<td width="25%" class='LABEL' colspan='1'><div align="right">Relation With Patient:</div></td>
			<td width="25%" class="CONTROL" colspan='1'>
		        <select name="strRelationId" class='comboNormal'><bean:write name="cashCollectionOfflineTransBean" property="strRelatinDetails" filter="false"/></select>
			</td>
			<td width="25%" class='LABEL' colspan='1'><div align="right">Card Validity:</div></td>
			<td width="25%" class="CONTROL" colspan='1'>
		         <date:date name="strCardValidity" value=""/>   
		</td>
		</tr>--%>
<!-- 		 <tr>  -->
<!-- 			<td width="24%" class='LABEL' colspan='1'><div align="right">Waiver By:</div></td> -->
<!-- 			<td width="76%" class="CONTROL" colspan='3'> -->
<%-- 		        <select name="strRmk"><bean:write name="cashCollectionOfflineTransBean" property="strRmk" filter="false"/></select>	         --%>
<!-- 		</td> -->
<!-- 		</tr> -->
<!--  		
	  </table>     
  	</div>
  	-->
	
	<table class="TABLEWIDTH" align="center" cellpadding="1px" cellspacing="1px">
		<tr>
			<td colspan="4" width='100%'><div class='line'><label class='DIVLABEL'>Payment</label></div></td>
		</tr>
	</table>
	<table class="TABLEWIDTH" align="center" cellpadding="1px"
		cellspacing="1px">
		<tr>
		    <%-- <td class="multiLabel" width="8%"><div align='left'></div></td>--%>
			<td class="multiLabel" width="28%"><font color="red">*</font>Payment
			Mode</td>
			<td class="multiLabel" width="32%">Payment
			Details</td>
			<td class="multiLabel" width="20%"><font color="red">*</font>Amount(<img src='/HBIMS/hisglobal/images/INR.png'>)</td>
			<td class="multiLabel" width="3%"><!-- <img
				style="cursor: hand; cursor: pointer"
				src="../../hisglobal/images/plus.gif"
				onclick="checkForOffLinePaymentLimit('0');"> --></td>  <!-- commented by manisha -->
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

	<table class="TABLEWIDTH" align="center" cellpadding="1px" cellspacing="1px" style='display:none;'>
		<tr>
			<td width="60%" class="LABEL">Change (<img src='/HBIMS/hisglobal/images/INR.png'>)</td>
			<td width="15%" class="CONTROL">
			<table>
				<tr>
					<td><input type='hidden' name='strOfflineRefundAmount' id='strOfflineRefundAmountDivId' value='0.00'></td>
					<td class="CONTROL" style="color: red; font-weight: bold">
					<div id='offlineRefundAmtDivId'>0.00</div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
	</table>	 

	<div id="tariffNamePartDivId">
	<table class="TABLEWIDTH" align="center" cellpadding="1px" cellspacing="1px">
		<tr>
			<td width="15%" class="CONTROL" style="color: blue; font-weight: bold">
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
				<tr>
					<td class="CONTROL" colspan="2" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Patient Category Combo Rules:Admitted Patient Then Default Selected Category Admitted Treatment Category. 
					OPD Patient Then Episode Treatment Category/Episode Category. If No Episode Treatment Category Then Primary Category. Category Combo is Always Lower to Higher Not Higher To Lower.
					Higher To Lower Category Offline Billing Not Allowed.Department/Episode Is Last Visted Department/Episode
					</td>
				</tr>
				<tr>
				<td class="CONTROL" colspan="2" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Credit Letter Format: Credit Letter Reference No./Credit Letter Date/Company(Client) Name/Balance Credit Limit/Card No./Card Holder Name
	            </td>
				</tr>
				<tr>
				     <td class="CONTROL" colspan="2" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; (<font color='red'>*</font>): Rounded Off Value
	                 </td>
				</tr>
				<tr>
				<td class="CONTROL" colspan="2"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>Drug Code can only be entered when Drugs & Disposables Group is selected</b></td>
			    </tr>
				<tr class=FOOTER>
					<td colspan="2"></td>
				</tr>
			</table>
			</div>
			</td>
		</tr>
	</table>
	<!-- <div id="saveid">
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
	</div>-->
	<div id="dialog-message" title="Save" style="display:none;">
  <p>
    <b>Are You Sure to Save it?</b>.
  </p>
</div>
	<br>
<div align="center" id="saveid">					 
							<a href="#" class="button" id="btn-sv" tabindex="1" onClick="return findBillServices();" onkeypress="onKeyPressLogic('1',event);"><span class="save" tabindex="1">Save</span></a>
							<a href="#" class="button" id="btn-clr"	onClick="clearFunc(document.forms[0].strCounterMode.value);" onkeypress="onKeyPressLogic('2',event);"><span class="clear">Clear</span></a> 
							<a href="#" class="button" id="btn-ccl" onClick="cancelFunc();" onkeypress="onKeyPressLogic('3',event);" ><span class="cancel">Cancel</span></a>
					</div>
	
	<input type="hidden" name="hmode" />
	<input type='hidden' name='strPkgServiceFlag' value='0' />
	<input type="hidden" name="strTempCtDate"
		value="${cashCollectionOfflineTransBean.strCurrentDate}" />
	<input type="hidden" name="strIpdThirdPartyBenefit"
		value="${cashCollectionOfflineTransBean.strIpdThirdPartyBenefit}" />
	<input type="hidden" name="strEmergencyThirdPartyBenefit"
		value="${cashCollectionOfflineTransBean.strEmergencyThirdPartyBenefit}" />
	<input type="hidden" name="strOpdThirdPartyBenefit"
		value="${cashCollectionOfflineTransBean.strOpdThirdPartyBenefit}" />

	<input type="hidden" name="strOfflineIpdPenaltyVal"
		value="${cashCollectionOfflineTransBean.strOfflineIpdPenaltyVal}" />
	<input type="hidden" name="strOfflineOpdPenaltyVal"
		value="${cashCollectionOfflineTransBean.strOfflineOpdPenaltyVal}" />
	<input type="hidden" name="strOfflineEmergencyPenaltyVal"
		value="${cashCollectionOfflineTransBean.strOfflineEmergencyPenaltyVal}" />

	<input type='hidden' name='strOffLineRefundAdvanceAccountNo' value='0' />


	<%-- current state represents  1 - Online and 2 - Off-line--%>
	<input type="hidden" name="currentState"
		value="${cashCollectionOfflineTransBean.currentState}" />
	<input type="hidden" name="currentBserviceId" value="${cashCollectionOfflineTransBean.currentBserviceId}" />
	<input type="hidden" name="currentReceiptType" value='1' />
	<input type="hidden" name="currentHospService" value='1' />
	
	<input type="hidden" name="strTempBillNo" value="${cashCollectionOfflineTransBean.strTempBillNo}" />
	<input type="hidden" name="strTempReceiptNo" value="${cashCollectionOfflineTransBean.strTempReceiptNo}" />


<input type="hidden" name="strIsIpdDiscount" value="${cashCollectionOfflineTransBean.strIsIpdDiscount}" />
<input type="hidden" name="strIsOpdDiscount" value="${cashCollectionOfflineTransBean.strIsOpdDiscount}" />
<input type="hidden" name="strIsEmergencyDiscount" value="${cashCollectionOfflineTransBean.strIsEmergencyDiscount}" />
	
<input type="hidden" name="strCounterMode" value="${cashCollectionOfflineTransBean.strCounterMode}" />
<input type="hidden" name="strIsWithoutCrNoRequired" value="${cashCollectionOfflineTransBean.strIsWithoutCrNoRequired}" />
<input type="hidden" name="strPrintMessageLimit" value="${cashCollectionOfflineTransBean.strPrintMessageLimit}" />

<input type="hidden" name="strFreeCategory" value="${cashCollectionOfflineTransBean.strFreeCategory}" />
<input type="hidden" name="strIsApprovalRequired" value="${cashCollectionOfflineTransBean.strIsApprovalRequired}" />
<input type="hidden" name="strIsAdvanceRequired" value="${cashCollectionOfflineTransBean.strIsAdvanceRequired}" />
<input type="hidden" name="strConsumableCharge" value="${cashCollectionOfflineTransBean.strConsumableCharge}" />
<input type="hidden" name="strOfflineTotalPayAmountWithoutConsumable" value="${cashCollectionOfflineTransBean.strOfflineTotalPayAmountWithoutConsumable}" />
<input type="hidden" name="strGroupIdForConsumableConcatenated" value="${cashCollectionOfflineTransBean.strGroupIdForConsumableConcatenated}" />
<input type="hidden" name="strConsumableChargesGroupId" value="${cashCollectionOfflineTransBean.strConsumableChargesGroupId}" />
<input type="hidden" name="strConsumableChargesTariffCode" value="${cashCollectionOfflineTransBean.strConsumableChargesTariffCode}" />
<input type="hidden" name="strCreditLetterValidity" value="${cashCollectionOfflineTransBean.strCreditLetterValidity}" />
<input type="hidden" name="isOpenPopUp" value="${cashCollectionOfflineTransBean.isOpenPopUp}"/>
<input type="hidden" name="filePath" value="${cashCollectionOfflineTransBean.filePath}"/>
<input type="hidden" name="printMode" value="${cashCollectionOfflineTransBean.printMode}"/>
<input 	type="hidden" name="selectedTab" >
<input type ="hidden" name="strVisitType" value="${cashCollectionOfflineTransBean.strVisitType}" />	
<input 	type="hidden" name="strNewCreditLetterAddedFlag" >
<input 	type="hidden" name="strArogyaCatId" value="${cashCollectionOfflineTransBean.strArogyaCatId}">
<input 	type="hidden" name="strArogyaTSCatId" value="${cashCollectionOfflineTransBean.strArogyaTSCatId}">
<input 	type="hidden" name="strWchCatId" value="${cashCollectionOfflineTransBean.strWchCatId}">
<input 	type="hidden" name="strArogyaTSCatCreditLimit" value="${cashCollectionOfflineTransBean.strArogyaTSCatCreditLimit}">
<input 	type="hidden" name="strUrgSur" value="${cashCollectionOfflineTransBean.strUrgSur}">
<input 	type="hidden" name="strSurCc" value="${cashCollectionOfflineTransBean.strSurCc}">
<input 	type="hidden" name="strSurDc" value="${cashCollectionOfflineTransBean.strSurDc}">
<input 	type="hidden" name="strSurIc" value="${cashCollectionOfflineTransBean.strSurIc}">
<input 	type="hidden" name="strSurId" value="${cashCollectionOfflineTransBean.strSurId}">
<input 	type="hidden" name="strSurCc1" value="${cashCollectionOfflineTransBean.strSurCc1}">
<input 	type="hidden" name="strSurDc1" value="${cashCollectionOfflineTransBean.strSurDc1}">
<input 	type="hidden" name="strSurIc1" value="${cashCollectionOfflineTransBean.strSurIc1}">
<input 	type="hidden" name="strSurId1" value="${cashCollectionOfflineTransBean.strSurId1}">
<input 	type="hidden" name="strEpisodeFound" value="${cashCollectionOfflineTransBean.strEpisodeFound}"/>
<input type="hidden" name="grpid" value="${cashCollectionOfflineTransBean.grpid}" />
<input type="hidden" name="defsurlim" value="${cashCollectionOfflineTransBean.defsurlim}" />
<input type="hidden" name="onlyCatCahnge" value="0" />

<input 	type="hidden" name="strAlertPromptConfirmFlag" value="0"/>
</div>
</html:form>
<jsp:include page="multirow_cashcollection_offline_billtrans.jsp"></jsp:include>
<jsp:include page="dropdown_cashcollection_offline_trans.jsp"></jsp:include>


<div id='printableSlip'>
<logic:equal name="cashCollectionOfflineTransBean"  property="isOpenPopUp" value="1">
<logic:equal name="cashCollectionOfflineTransBean"  property="printMode" value="1">
<logic:present name="cashCollectionOfflineTransBean"  property="filePath">
<jsp:include page="billing_receipt_printing_popup.jsp"></jsp:include>
</logic:present>
</logic:equal>
</logic:equal>
</div>				

</body>
</html>