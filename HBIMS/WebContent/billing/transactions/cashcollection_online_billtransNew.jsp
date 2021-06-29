<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>
<%@ taglib uri="/WEB-INF/onLineReqDiscountDtl.tld" prefix="onLineDtl"%>
<%@ taglib uri="/WEB-INF/patientDtl.tld" prefix="pDtl"%>
<%@ taglib uri="/WEB-INF/dateTag.tld" prefix="date"%>
<%@ taglib uri="/WEB-INF/onlineClientDtl.tld" prefix="bDtl"%>
<%@ taglib uri="/WEB-INF/crNo.tld" prefix="crNo"%>

<html>
<head>
<title>Cash Collection Online</title>

<meta name="viewport" content="width=device-width, initial-scale=1.0">

<link href="../../hisglobal/bootstrap4.0_newgui/css/bootstrap.css" rel="stylesheet" />
<link href="../../hisglobal/bootstrap4.0_newgui/css/bootstrap-theme.css" rel="stylesheet" />
<link href="../../hisglobal/bootstrap4.0_newgui/datatable/css/dataTables.bootstrap.css" rel="stylesheet" />
 <link href="../../hisglobal/bootstrap4.0_newgui/font-awesome/css/all.css" rel="stylesheet">
 <link href="../../hisglobal/bootstrap4.0_newgui/swAl/swal.css" rel="stylesheet">
<link href="../../ipd/css/newlayout.css" rel="stylesheet" type="text/css"> 

 <script type="text/javascript" src="../../hisglobal/bootstrap4.0_newgui/swAl/swal.js"></script>
 <script type="text/javascript" src="../../hisglobal/bootstrap4.0_newgui/js/jquery-3.3.1.min.js"></script>
 <script type="text/javascript" src="../../hisglobal/bootstrap4.0_newgui/datatable/js/jquery.dataTables.js"></script>
 <script type="text/javascript" src="../../hisglobal/bootstrap4.0_newgui/js/bootstrap.min.js"></script>
 <script type="text/javascript" src="../../hisglobal/bootstrap4.0_newgui/datatable/js/dataTables.bootstrap4.js"></script>
 
 
 
 <script src="https://unpkg.com/gijgo@1.9.13/js/gijgo.min.js" type="text/javascript"></script>
<link href="https://unpkg.com/gijgo@1.9.13/css/gijgo.min.css" rel="stylesheet" type="text/css" />



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
<script language="Javascript" src="../../hisglobal/js/validationBootstrap.js"></script>
<script language="Javascript" src="../../hisglobal/js/util.js"></script>
<script language="Javascript" src="../../hisglobal/js/popup.js"></script>
<script language="Javascript" src="../../hisglobal/js/newpopup.js"></script>
<script language="Javascript" src="../js/billing.js"></script>
<script language="Javascript" src="../js/tariffSearch.js"></script>
<script language="Javascript" src="../js/patientListing.js"></script>
<script language="Javascript" src="../js/cashcollection_online_transBS.js"></script>


<script type="text/javascript">
var beforePrint = function() 
{  
	showPrintableSlip();
};
var afterPrint = function() 
{               
	hidePrintableSlip();
};

window.onbeforeprint = beforePrint;
window.onafterprint = afterPrint;

modalDataTableOnlineReq();
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

.dataTables_wrapper {
    font-family: tahoma;
   
}

</style>

</head>
<body
	onLoad="onLoadLogics();addRows(new Array('strOnlinePaymentMode','strOnlinePaymentDtls','strOnlineAmount'),new Array('s','t','t'),'1','1','I');openPrintPopUp();"
	onfocus="checkPopUpAndSetDefaultCrNo();" onUnload="closePopUp();" > <!--  changePayMode(); -->

<script language="Javascript" src="../../hisglobal/js/wz_tooltip.js"></script>
<html:form  action="/transactions/CashCollectionOnlineTransBSCNT" method="post">

<fieldset form="form1">
	
  <legend style="width:auto; margin-bottom: 0px;font-size: 1.5rem;font-weight: 400;margin-left:10px;color: #666;" id='nonPrintableLegend'>Cash Collection</legend>
  <div class="legend2" id='nonPrintableLegend2'>
	<button  type="button" class="float-right btn btn-outline-danger mt-1 btn-circle cancelbtn" onclick="cancelFunc();" style="background-color: #d9534f;">
		<i class="fas fa-ban iround"  title="Cancel"></i>
	</button>	
	<button  id="printbutton" type="button" class="float-right btn btn-outline-primary mt-1 btn-circle printbtn" data-toggle="modal" data-target="#printModal" onclick="" style="display: none;background-color: #007bff;">
		<i class="fas fa-print iround"  title="Print Last Slip"></i>
	</button>
	
    <button  type="button" id="savebutton" class="float-right btn btn-outline-success mt-1 btn-circle savebtn" tabindex='2' onclick='return findBillServices();' name="patientAdmissionModiTransBean"  data-toggle="modal" data-target="#validateModal" style="display: none;background-color: #5cb85c;">					
		<i class="fas fa-save iround"  title="Save" ></i>
	</button>	
	
							                 
  </div>
  <div  class="viewport" id="nonPrintable">
		<div class="container-fluid">
  <div class="col-md-12">	

				<div id="onLinePatId" style="display: block">
		                           <bean:write name="cashCollectionOnlineTransBean" property="strPatientDetailsView" filter="false" />
	                           </div>					  			   
											
<div class="prescriptionTile">

<div id="blanket" style="display: none;"></div>
<div id="nonPrintable">
	<center>
		<div id="errMsg" class="errMsg"><bean:write name="cashCollectionOnlineTransBean" property="strErrMsg" filter="false" /></div>
		<div id="normalMsg" class="normalMsg"><bean:write name="cashCollectionOnlineTransBean" property="strNormalMsg" filter="false" /></div>
		<div id="warningMsg" class="warningMsg"><bean:write name="cashCollectionOnlineTransBean" property="strWarningMsg" filter="false" /></div>		
		<tag:tabNew1 tabList="${cashCollectionOnlineTransBean.lhm}" selectedTab="ONLINE" align="center" width="TABLEWIDTH"></tag:tabNew1>		
	</center>

	<div class="popUpDiv" id="payDtlCDDMenu" style="display: none;">
		<table bgcolor="white">
		 <tr>
			<td>
			<div class='popup' id='payDtlCDDMenuInner' style="display: block">
				<table width='400'>
					<tr class='HEADER'>
						<th colspan='4' align='left'>&nbsp;Payment Details &gt;&gt;Check / DD</th>
					</tr>
					<tr>
						<td class='LABEL'><font color="red">*Bank Name</td>
						<td class='CONTROL'>
						<input type='text' tabindex="1" name='strPayCDDBankName' class='txtFldMax' onkeypress='return validateData(event,11);' maxlength="50"/></td>
						<td class='LABEL'><font color="red">*Cheque / DD No.(Max : 15 Digits)</td>
						<td class='CONTROL'>
						<input type='text' name='strChequeDDNo' tabindex="1" class='txtFldDate' onkeypress="return validateData(event,5);" maxlength="15"/></td>
					</tr>
					<tr>
						<td colspan='2' class='LABEL'><font color="red">*Cheque / DD Issue Date</td>
						<td colspan='2' class='CONTROL'>
						<input type="text" tabindex="1" class="txtFldDate" maxlength="11" name="strChequeDDDate" value="${cashCollectionOnlineTransBean.strCurrentDate }" >(DD-Mon-YYYY)</td>
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
							<img style="cursor: hand; cursor: pointer" src='../../hisglobal/images/btn-sv.png' tabindex="1" onClick='return validateCheckDD();'> 
							<img style="cursor: hand; cursor: pointer" src='../../hisglobal/images/btn-ccl.png' tabindex="1" onClick="hidePayDetails('payDtlCDDMenu');"></center>
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
							<bean:write name="cashCollectionOnlineTransBean" property="strWalletNoMasked" filter="false"/>
							<input type="hidden" name="strWalletNo" value="${cashCollectionOnlineTransBean.strWalletNo}" />
							<input type="hidden" name="strWalletNoMasked" value="${cashCollectionOnlineTransBean.strWalletNoMasked}" />
							<input type="hidden" name="strMobileNo" value="${cashCollectionOnlineTransBean.strMobileNo}" />
						</td>
						<td class='WALLETLABEL'>Patient Wallet Balance:&nbsp;</td>
						<td class='WALLETCONTROL'>
							<bean:write name="cashCollectionOnlineTransBean" property="strAvlWalletMoney" filter="false"/>&nbsp;<img src='/HBIMS/hisglobal/images/INR.png'>
							<input type="hidden" name="strAvlWalletMoney" value="${cashCollectionOnlineTransBean.strAvlWalletMoney}" />
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
						<td> <input type="hidden" name="strConfirmationType" value="${cashCollectionOnlineTransBean.strConfirmationType}">
						
						<logic:equal name="cashCollectionOnlineTransBean"  property="strConfirmationType" value="0">
							<center> 
								<input type="button" name="billConfirmCancel" tabindex="1" value="Cancel" class="btn"  onclick="return confirmCancel();" onblur="document.forms[0].billConfirmOk.focus();" > 
								<input type="button" name="billConfirmOk" class="btn" tabindex="1" value="Ok" onblur="document.forms[0].billConfirmCancel.focus();"   onclick="return confirmOk();">  
							</center>
						</logic:equal>
						<logic:equal name="cashCollectionOnlineTransBean"  property="strConfirmationType" value="1">
							<center> 
								<input type="button" name="billConfirmOk" class="btn" tabindex="1" value="Ok" onblur="document.forms[0].billConfirmCancel.focus();"  onclick="return confirmOk();">
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
						<th colspan='4' align='left'>&nbsp;Payment Details &gt;&gt;Credit / Debit Card</th>
					</tr>
					<%-- <tr>
					<td class="LABEL" id='totalCardAmtDivId3' colspan='4'><font color="red">#<font size="2">Final Transaction Amount(with Surcharge)(<img src='/HBIMS/hisglobal/images/INR.png'>)&nbsp;0.00</td>
				    </tr>--%>
					<tr>
						<td class='LABEL'><font color="red">*Bank Name</td>
						<td class='CONTROL'>
							<input type='text' name='strPayCDBankName' tabindex="1" class='txtFldMax' onkeypress="return validateData(event,11);" maxlength="50"/></td>
						<td class='LABEL'><font color="red">*Card No. (Last 4 Digits)</td>
						<td class='CONTROL'>
							<input type='password' name='strCardNo' tabindex="1" class='txtFldNormal' maxlength="4" onkeypress="return validateData(event,5);" /></td>
					</tr>
					<tr>
						<td class='LABEL'><font color="red">*Transaction No.(Max : 15 Digits)</td>
						<td class='CONTROL'>
							<input type='text' name='strAuthNo' tabindex="1" class='txtFldNormal' onkeypress="return validateData(event,5);" maxlength="15"/></td>
						<td class='LABEL'><font color="red">*Transaction Date</td>
						<td class='CONTROL'>
							<input type="text" class="txtFldDate" tabindex="1" maxlength="11" name="strAuthDate" value="${cashCollectionOnlineTransBean.strCurrentDate }" >(DD-Mon-YYYY)</td>
					</tr>
					<tr>
						<td colspan='2' class='LABEL'><font color="red">*Card Type</td>
						<td colspan='2' class='CONTROL'>
							<select class='comboNormal' name='strCardType' tabindex="1" >
								<option value='0'>Select Value</option>
								<option value='1'>Master</option>
								<option value='2'>Visa</option>
								<option value='3'>Rupay</option>
								<option value='4'>Others</option>
						</select></td>
					</tr>	
					<tr class='FOOTER'>
						<td colspan='4' align='left'><font color="red">#Ensure that the Transaction/Card Swap is Successful</td>
					</tr>
				</table>
				<center>
				<table width='300' border='0' cellpadding='0' cellspacing='0'>
					<tr>
						<td>
						<center>
							<img style="cursor: hand; cursor: pointer" src='../../hisglobal/images/btn-sv.png' tabindex="1" onClick=' return validateCreditDebit();'> 
							<img style="cursor: hand; cursor: pointer" src='../../hisglobal/images/btn-ccl.png' tabindex="1" onClick="hidePayDetails('payDtlCDMenu')"></center>
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
					<td class="LABEL" id='totalBillAmtDivId1'>Billed Amount(<img src='/HBIMS/hisglobal/images/INR.png'>)&nbsp;0.00</td>
					<td class="LABEL" id='totalSurAmtDivId1'>Surcharge Amount(<img src='/HBIMS/hisglobal/images/INR.png'>)&nbsp;0.00</td>
				</tr>
			    <tr>
					<td class="LABEL" id='totalCardAmtDivId2' colspan='2'><font color="red">#<font size="2">Final Transaction Amount(with Surcharge)(<img src='/HBIMS/hisglobal/images/INR.png'>)&nbsp;0.00</td>
				</tr>
				<tr class='FOOTER'>
					<td colspan='2'><font color="red">#Please Initiate the Card Transaction/Swipe Card as follows:
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
	


	<div class="popUpDiv" id="payDtlCLTMenu" style="display: none;">
		<table bgcolor="white">
			<tr>
				<td>
				<div class='popup' id='payDtlCLTMenuInner' style="display: block">
					<table width='450'>
						<tr class='HEADER'>
							<th colspan='4' align='left'>&nbsp;Payment Details &gt;&gt;Client</th>
						</tr>
						<tr>
							<td class='LABEL'><font color="red">*Client Name</td>
							<td class='CONTROL' colspan="3">
							<select name="strPayCNTClientName" class="comboMax" tabindex="1" >
								<bean:write name="cashCollectionOnlineTransBean" property="strClientNameContents" filter="false"/>
							</select></td>
						</tr>
						<tr>
							<td class='LABEL'><font color="red">*Auth No.</td>
							<td class='CONTROL'>
								<input type='text' name='strClientAuthNo' tabindex="1" class='txtFldNormal' onkeypress="return validateData(event,5);" /></td>
							<td class='LABEL'><font color="red">*Auth Date</td>
							<td class='CONTROL'>
								<input type="text" class="txtFldDate" tabindex="1" maxlength="11" name="strClientAuthDate" value="${cashCollectionOnlineTransBean.strCurrentDate }" >(DD-Mon-YYYY)</td>
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
								<img style="cursor: hand; cursor: pointer" src='../../hisglobal/images/btn-sv.png' tabindex="1" onClick=' return validateClientDetails();'> 
								<img style="cursor: hand; cursor: pointer" src='../../hisglobal/images/btn-ccl.png' tabindex="1" onClick="hidePayDetails('payDtlCLTMenu')"></center>
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
				<div class='popup' id='multiRowAdderDivIdInner' style="display: block">
					<table width='400' cellpadding="0" cellspacing="0" background="../../hisglobal/images/blank.gif">
						<tr class='HEADER'>
							<th colspan='2' align="left">&nbsp;Add Rows</th>
							<th align="right">
							<img src="../../hisglobal/images/stop.png" align="middle" onclick="hideMultiRowAdder('multiRowAdderDivId');"></th>
						</tr>
						<tr>
							<td class='LABEL'><font color="red">*Enter The No. of Rows to be Added</td>
							<td width="1">&nbsp;</td>
							<td class='CONTROL'>
							<input type='text' name='strOffLineTariffNoOfRows' class='txtFldMin' maxlength="2" onkeypress="return validateData(event,5),addTariffRows(this,event,'multiRowAdderDivId');" /></td>	
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
	
	<div class='popup' id='tariffDtl' style="display: none">
		<table width='400' cellspacing="0" background="../../hisglobal/images/blank.gif">	
			<tr class='HEADER'>	
				<th colspan='3' align='left'>&nbsp;Tariff Details</th>	
				<th align='right'>
				<img src='../../hisglobal/images/stop.png' onClick="hide_popup_menu('tariffDtl');"></th>	
			</tr>
		</table>
		<table width='400'>
			<tr>
				<td width="25%" class='LABEL'>Rate/Unit(<img src='/HBIMS/hisglobal/images/INR.png'>)</td>
				<td width="25%" class='CONTROL'>
					<div id='tariffId1'></div>
				</td>
				<td width="25%" class='LABEL'>Total Discount(<img src='/HBIMS/hisglobal/images/INR.png'>)</td>
				<td width="25%" class='CONTROL'>
					<div id='tariffId2'></div>
				</td>
			</tr>
			<tr>
				<td width="25%" class='LABEL'>Total Service Tax(<img src='/HBIMS/hisglobal/images/INR.png'>)</td>
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
				<th align='right'>
				<img src='../../hisglobal/images/stop.png' onClick="hide_popup_menu('refundAdvanceDtl');"></th>
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
				<td width="50%" class='LABEL'>Penalty Amount (in <img src='/HBIMS/hisglobal/images/INR.png'>)</td>
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

	<div class='popup' id='finalAdjustmentNetAmountDtls' style="display: none">
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

	<div id="consumableChargeDiv"></div>
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

	

	<%-- <table class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="1px">
		<tr class="HEADER">
			<td>
			<table width="100%" cellpadding="0" cellspacing="0">
				<tr>
					<td colspan="2">
					<div style="display:none">
					<a id="cashCollection" style="cursor: pointer;color: white" onclick="openDependentPopup('BillingCNT.cnt?hmode=CASHCOLLECTIONPOPUP',event,250,600);" 
					title="Cash Collection Detail"><font color="white" style=""><u>Total Cash</u></a>
					</div>
					</td>
					<td>
					<div align="right">
					<logic:equal value="2" name="cashCollectionOnlineTransBean" property="strCounterMode">
					<html:checkbox
						name="cashCollectionOnlineTransBean" property="strIsOnline" value="1"
						onclick="setOnlineOffLineMode(this);"></html:checkbox> Online&nbsp;
					</logic:equal>
					
					<logic:notEqual value="2" name="cashCollectionOnlineTransBean" property="strCounterMode">
					<div style="display: none">
					<html:checkbox
						name="cashCollectionOnlineTransBean" property="strIsOnline" value="1" 
						onclick="setOnlineOffLineMode(this);"></html:checkbox> Online&nbsp;
						</div>
					</logic:notEqual>
					</div>
					</td>
					<td>
						<div align="right">
							<img style="cursor: pointer" name="printLastButton" src="../../hisglobal/images/print_on.gif" onclick="printLastBill();" title="Print Last Bill">
						</div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
	</table> --%>
	<logic:equal value="1" name="cashCollectionOnlineTransBean" property="strIsWithoutCrNoRequired">
	<table class="TABLEWIDTH" align="center" cellpadding="1px" cellspacing="1px">
		<tr>

			<td class="LABEL" colspan="4">
			
			<html:radio
				name="cashCollectionOnlineTransBean" property="strPatientMode" value="1"
				onclick="selectPatientMode(this);">With CR No.</html:radio> <html:radio
				name="cashCollectionOnlineTransBean" property="strPatientMode" value="2"
				onclick="selectPatientMode(this);">Without CR No.</html:radio></td>
		</tr>
	</table>
	</logic:equal>
	
	<logic:notEqual value="1" name="cashCollectionOnlineTransBean" property="strIsWithoutCrNoRequired">
	<div style="display: none">
	<table class="TABLEWIDTH" align="center" cellpadding="1px" cellspacing="1px">
		<tr>

			<td class="LABEL" colspan="4">
			
			<html:radio
				name="cashCollectionOnlineTransBean" property="strPatientMode" value="1"
				onclick="selectPatientMode(this);">With CR No.</html:radio> <html:radio
				name="cashCollectionOnlineTransBean" property="strPatientMode" value="2"
				onclick="selectPatientMode(this);">Without CR No.</html:radio></td>
		</tr>
	</table>
	</div>
	</logic:notEqual>
	
	<%-- <table class="TABLEWIDTH" align="center" cellspacing="1px"
		cellpadding="1px">
		<tr>
			<td class="LABEL">
				<table width="100%" cellpadding="0" cellspacing="0" >
					<tr>
						<td width="200">&nbsp;<div align="left"><font color="red">*CR No. 
							<crNo:crNo id="strCrNoId" name="strCrNo" value="${cashCollectionOnlineTransBean.strCrNo}" js="onkeypress='return initGoFunc(event);'"></crNo:crNo></div>
						</td>
						<td width="15">
							<div id="searchIconDivId" align="left">
								<img style="cursor: pointer; cursor: hand;" src="../../hisglobal/images/viewDetails.gif"
									title="Click here for Patient Search" align="middle" name='searchPatient'
									onclick="showPatientListingWindow('1',document.forms[0].strCrNo,'setSelectedCrNo');" />
							</div>
						</td>
						<td><div align="left">
							<img src="../../hisglobal/images/Go.png" 	style="cursor: hand; cursor: pointer" title="Get Bill Details"
								name="go" onClick="return goFunc();" onKeyPress="return goFunc();"
								align="middle"> &nbsp;&nbsp;</div>
						</td>	
					</tr>
				</table>
			</td>
		</tr>
	</table> --%>
								<div class="row rowFlex reFlex" id="goid">
									<div class="col-sm-2" align="right">
										<label><font color="red">*</font>CR No.</label>
									</div>
									<div class="col-sm-2">
										<crNo:crNo id="strCrNoId" name="strCrNo"
											value="${cashCollectionOnlineTransBean.strCrNo}"
											js="onkeypress='return initGoFunc(event);'" className="form-control"></crNo:crNo>
									</div>
									<div class="col-sm-2" style="padding-left: 0">
										<span class="fas fa-search"
											style="cursor: pointer; cursor: hand;" 
											title="Click here for Patient Search" name='searchPatient'
											data-toggle="modal" data-target="#searchModel"
											onclick="fetchPatientList_BS(1,1)"></span>
										<a href="#" class="btn btn-sm btn-success" name="go"
											onClick="return goFunc();" onKeyPress="return goFunc();"
											style="font-size: 1rem;"> GO&nbsp;<i
											class="fas fa-angle-double-right"></i>
										</a>
									</div>
									<div class="col-sm-6"></div>									
								</div>
								
								<div id="gblDivId" style="display: none">
								
	                            <div id="onLineId">
	                               <p class="subHeader">Online Request(s)</p>
										<table class="table" ><!--id="datatable"  -->
											<thead>
												<tr>
													<th>#</th>
													<th>Request No.</th>
													<th>Request Date</th>
													<th>Department/Ward</th>
													<th>Hospital Service</th>
													<th>Request For</th>
													<th>Penelty</th>
												</tr>
											</thead>
											<tbody>
												<bean:write name="cashCollectionOnlineTransBean"
													property="strOnlineDetailsView" filter="false" />
											</tbody>
										</table>
										<div id='clientDetailsDivId' >
		<bean:write name="cashCollectionOnlineTransBean" property="strOnlineClientDetailsView" filter="false" />
	</div>
	
	
	<div id ="otherCreditDetailsId" style="display: none;">
	  <table class="NEWTABLEWIDTH" align="center" cellpadding="0" cellspacing="">
	  
	  <tr> 
		  <td width="2%" colspan='1'> 
		  		<div id="plusCreditDtlsId" style="display: block;" class="lineContinue2"> 
					<img src="/HBIMS/hisglobal/images/plus.gif" name="plusonLine" style="cursor:hand;cursor:pointer" onclick="displayCreditDetails('creditDtlsDivId');" align="middle">					
				</div>
				<div id="minusCreditDtlsId" style="display: none;" class="lineContinue2">
					<img src="/HBIMS/hisglobal/images/minus.gif" style="cursor:hand;cursor:pointer" name="minusonLine" onclick="hideCreditDetails('creditDtlsDivId');">
				</div>
		  </td>
		  <td colspan="3" width='98%'><div class="lineContinue"><label class='DIVLABEL'>Other Credit Details</label></div> </td>
	  </tr>		
	  </table>
	</div>
	
	
	<div id ="creditDtlsDivId" style="display: block;">
		
				<bDtl:onlnCltDtl crNo="${cashCollectionOnlineTransBean.strCrNo}" ></bDtl:onlnCltDtl>					
	</div>
	        
  	</div>
  	<div class="subHeaders"><i class="fas fa-university" style="font-size:26px;"></i>Payment Details</div>
		
		<br>
		<%-- <table class="TABLEWIDTH" align="center" cellpadding="1px" cellspacing="1px">
			<tr>	
				<td colspan="3"><div class="line"><label class="DIVLABEL">Payment Details</label></div></td>
			</tr>
		</table>
		<table class="TABLEWIDTH" align="center" cellspacing="1px">
			<tr>
				<td class="multiLabel" width="8%"><div align='left'></div></td>
				<td class="multiLabel" width="28%"><div align='left'><font color="red">*</font>Payment Mode</div></td>
				<td class="multiLabel" width="32%">Payment Details</td>
				<td class="multiLabel" width="20%"><font color="red">*</font>Amount(<img src='/HBIMS/hisglobal/images/INR.png'>)</td>	
				<td class="multiLabel" width="3%">
					<!-- <img style="cursor: hand; cursor: pointer" src="../../hisglobal/images/plus.gif" onclick="checkForOnLinePaymentLimit('0');"> --></td>
			</tr>
		</table> --%>
		
		<div id="id1"></div>
		
		<div id="onlineGrandTotalDivPartId" style="display: none">
			<table class='TABLEWIDTH' align='center' cellpadding='1px'
				cellspacing='1px'>
				<tr>
					<td width='60%' class='LABEL'>Grand Total (<img src='/HBIMS/hisglobal/images/INR.png'>)</td>
					<td width='15%' class='CONTROL'>
					<table>
						<tr>
							<td>
								<input type='hidden' name='strOnlineGrandTotalAmount' id='strOnlineGrandTotalAmount' value='0.00'></td>
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
					<td width='60%' class='LABEL'>Max Benefit From Client (<img src='/HBIMS/hisglobal/images/INR.png'>)</td>
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
		<table class='TABLEWIDTH' align='center' cellpadding='1px' cellspacing='1px'>
			<tr>
				<td width='60%' class='LABEL'>Net Payable Amount By Patient(<img src='/HBIMS/hisglobal/images/INR.png'>)</td>
				<td width='15%' class='CONTROL' style='font-weight: bold'>
				<table>
					<tr>
						<td>
							<input type='hidden' name='strOnlinePatNetPayAmount' id='strOnlinePatNetPayAmount' value='0.00'></td>
						<td class='CONTROL' style='font-weight: bold'>
							<div id='onlinePatNetPayDivId'>0.00</div>
						</td>
					</tr>
				</table>
				</td>
			</tr>
		</table>
	</div>

       <div class="row rowFlex reFlex">
		<div class="col-sm-6"></div>
		<div class="col-sm-4" align="right"><label>Net Payable / Refundable Amount(<img src='/HBIMS/hisglobal/images/INR.png'>)</label></div>
		<div class="col-sm-2" style="color: red; font-weight: bold"><input type='hidden' name='strOnlineTotalRecAmount' value='0.00'><div id='onlineTotalRecAmtDivId'>0.00</div></div>
		</div>
		
		 <div class="row rowFlex reFlex">
		<div class="col-sm-6"></div>
		<div class="col-sm-4" align="right"><label>Net Payment / Refund Amount (<img src='/HBIMS/hisglobal/images/INR.png'>)</label></div>
		<div class="col-sm-2"><input type='hidden' name='strOnlineTotalPayAmount' id='strOnlineTotalPayAmount' value='0.00'><div id='onlineTotalPayAmtDivId'>0.00</div></div>
		</div>
		 <div class="row rowFlex reFlex">
		<div class="col-sm-6"></div>
		<div class="col-sm-4" align="right"><label>Change(<img src='/HBIMS/hisglobal/images/INR.png'>)</label></div>
		<div class="col-sm-2" ><input type='hidden' name='strOnlineRefundAmount' id='strOnlineRefundAmount' value='0.00'><div id='onlineRefundAmtDivId'>0.00</div></div>
		</div>
		 <div class="row rowFlex reFlex">
		              <div id="onlineNetAmountDetailsDivId" style="display: none">
								<img style="cursor: hand; cursor: pointer" src="../../hisglobal/images/plus.gif"
								onclick="displayOnlineNetAmountDetails(this , 'finalAdjustmentNetAmountDtls');">
							</div></div>
<!-- 	<table class="TABLEWIDTH" align="center" cellpadding="1px" cellspacing="1px">
		<tr>
			<td width="60%" class="LABEL">Net Payable / Refundable Amount(<img src='/HBIMS/hisglobal/images/INR.png'>)</td>
			<td width="15%" class="CONTROL">
				<table>
					<tr>
						<td>
							<input type='hidden' name='strOnlineTotalRecAmount' value='0.00'></td>
						<td class="CONTROL" style="color: red; font-weight: bold">
							<div id='onlineTotalRecAmtDivId'>0.00</div>
						</td>
						<td>
							<div id="onlineNetAmountDetailsDivId" style="display: none">
								<img style="cursor: hand; cursor: pointer" src="../../hisglobal/images/plus.gif"
								onclick="displayOnlineNetAmountDetails(this , 'finalAdjustmentNetAmountDtls');">
							</div>
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table> -->

	<!-- <table class="TABLEWIDTH" align="center" cellpadding="1px" cellspacing="1px">
		<tr>
			<td width="60%" class="LABEL">Net Payment / Refund Amount (<img src='/HBIMS/hisglobal/images/INR.png'>)</td>
			<td width="15%" class="CONTROL" style="font-weight: bold">
				<table>
					<tr>
						<td>
							<input type='hidden' name='strOnlineTotalPayAmount' id='strOnlineTotalPayAmount' value='0.00'></td>
						<td class="CONTROL">
							<div id='onlineTotalPayAmtDivId'>0.00</div>
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table> -->
	
	<!-- <table class="TABLEWIDTH" align="center" cellpadding="1px" cellspacing="1px">
		<tr>
			<td width="60%" class="LABEL">Change (<img src='/HBIMS/hisglobal/images/INR.png'>)</td>
			<td width="15%" class="CONTROL" style="font-weight: bold">
				<table>
					<tr>
						<td>
							<input type='hidden' name='strOnlineRefundAmount' id='strOnlineRefundAmount' value='0.00'></td>
						<td class="CONTROL">
							<div id='onlineRefundAmtDivId'>0.00</div>
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
	</div> -->
	
	<!-- online end --> 
	
	</div>
<div id="dialog-message" title="Save" style="display:none;">
  <p>
    <b>Are You Sure to Save it?</b>.
  </p>
</div>
<!-- <br>
<div align="center" id="saveid">					 
							<a href="#" id="btn-sv" class="button" onclick="return findBillServices();" onkeypress="onKeyPressLogic('1',event);"><span class="save" tabindex="1">Save</span></a>
							<a href="#" id="btn-clr" class="button"	onclick="clearFunc(document.forms[0].strCounterMode.value);"><span class="clear">Clear</span></a> 
							<a href="#" id="btn-ccl" class="button" onclick="cancelFunc();" onkeypress="onKeyPressLogic('3',event);"><span class="cancel">Cancel</span></a>
</div> -->

 <hr>
							<div class="row rowFlex reFlex">
								<div class="col-sm-10"></div>
								<div class="col-sm-2" align="right">
									<i class="fas fa-asterisk"
										style="color: red; font-size: smaller;"></i>Fields Mandatory
								</div>
							</div>

	<input type="hidden" name="hmode" />
	<input type='hidden' name='strPkgServiceFlag' value='0' />
	<input type="hidden" name="strTempCtDate" value="${cashCollectionOnlineTransBean.strCurrentDate}" />
	<input type="hidden" name="strIpdThirdPartyBenefit" value="${cashCollectionOnlineTransBean.strIpdThirdPartyBenefit}" />
	<input type="hidden" name="strEmergencyThirdPartyBenefit" value="${cashCollectionOnlineTransBean.strEmergencyThirdPartyBenefit}" />
	<input type="hidden" name="strOpdThirdPartyBenefit" value="${cashCollectionOnlineTransBean.strOpdThirdPartyBenefit}" />
	<%-- current state represents  1 - Online and 2 - Off-line--%>
	<input type="hidden" name="currentState" value="${cashCollectionOnlineTransBean.currentState}" />
	<input type="hidden" name="currentBserviceId" value='1' />
	<input type="hidden" name="currentReceiptType" value='1' />
	<input type="hidden" name="currentHospService" value='1' />	
	<input type="hidden" name="strTempBillNo" value="${cashCollectionOnlineTransBean.strTempBillNo}" />
	<input type="hidden" name="strTempReceiptNo" value="${cashCollectionOnlineTransBean.strTempReceiptNo}" />
	<input type="hidden" name="strIsIpdDiscount" value="${cashCollectionOnlineTransBean.strIsIpdDiscount}" />
	<input type="hidden" name="strIsOpdDiscount" value="${cashCollectionOnlineTransBean.strIsOpdDiscount}" />
	<input type="hidden" name="strIsEmergencyDiscount" value="${cashCollectionOnlineTransBean.strIsEmergencyDiscount}" />	
	<input type="hidden" name="strCounterMode" value="${cashCollectionOnlineTransBean.strCounterMode}" />
	<input type="hidden" name="strPrintMessageLimit" value="${cashCollectionOnlineTransBean.strPrintMessageLimit}" />
	<input type="hidden" name="strConsumableCharge" value="${cashCollectionOnlineTransBean.strConsumableCharge}" />
	<input type="hidden" name="strOfflineTotalPayAmountWithoutConsumable" value="${cashCollectionOnlineTransBean.strOfflineTotalPayAmountWithoutConsumable}" />
	<input type="hidden" name="strGroupIdForConsumableConcatenated" value="${cashCollectionOnlineTransBean.strGroupIdForConsumableConcatenated}" />
	<input type="hidden" name="strConsumableChargesGroupId" value="${cashCollectionOnlineTransBean.strConsumableChargesGroupId}" />
	<input type="hidden" name="strConsumableChargesTariffCode" value="${cashCollectionOnlineTransBean.strConsumableChargesTariffCode}" />
	<input type="hidden" name="isOpenPopUp" value="${cashCollectionOnlineTransBean.isOpenPopUp}"/>
	<input type="hidden" name="filePath" value="${cashCollectionOnlineTransBean.filePath}"/>
	<input type="hidden" name="printMode" value="${cashCollectionOnlineTransBean.printMode}"/>
	<input type="hidden" name="strCreditApprovalRequired" value="${cashCollectionOnlineTransBean.strCreditApprovalRequired}"/>
	<input type="hidden" name="strCreditLetterValidity" value="${cashCollectionOnlineTransBean.strCreditLetterValidity}" />
	<input 	type="hidden" name="strUrgTrfSur" value="${cashCollectionOnlineTransBean.strUrgTrfSur}">
	<input 	type="hidden" name="strSurCc" value="${cashCollectionOnlineTransBean.strSurCc}">
	<input 	type="hidden" name="strSurDc" value="${cashCollectionOnlineTransBean.strSurDc}">
	<input 	type="hidden" name="strSurIc" value="${cashCollectionOnlineTransBean.strSurIc}">
	<input 	type="hidden" name="strSurId" value="${cashCollectionOnlineTransBean.strSurId}">
	<input 	type="hidden" name="strSurCc1" value="${cashCollectionOnlineTransBean.strSurCc1}">
	<input 	type="hidden" name="strSurDc1" value="${cashCollectionOnlineTransBean.strSurDc1}">
	<input 	type="hidden" name="strSurIc1" value="${cashCollectionOnlineTransBean.strSurIc1}">
	<input 	type="hidden" name="strSurId1" value="${cashCollectionOnlineTransBean.strSurId1}">
	<input type="hidden" name="defsurlim" value="${cashCollectionOnlineTransBean.defsurlim}" />
	
	
	<input type="hidden" name="currentReceiptPatCat" value='0' />
	<input type="hidden" name="currentReceiptPayMode" value='0' />


	<input 	type="hidden" name="selectedTab" >
	<input 	type="hidden" name="strNewCreditLetterAddedFlag" >
	<input type="hidden" name="gblCRValue"/>

</div>
</div>
</div>
</div>
</div>
</fieldset>
</html:form>

<jsp:include page="multirow_cashcollection_online_billtransNew.jsp"></jsp:include>


<div id='printableSlip'>
<logic:equal name="cashCollectionOnlineTransBean"  property="isOpenPopUp" value="1">
<logic:equal name="cashCollectionOnlineTransBean"  property="printMode" value="1">
<logic:present name="cashCollectionOnlineTransBean"  property="filePath">
<jsp:include page="billing_receipt_printing_popup.jsp"></jsp:include>
</logic:present>
</logic:equal>
</logic:equal>
</div>			
	
<div class="modal fade " id="searchModel" tabindex="-1" role="dialog"
		aria-labelledby="validateModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered  modal-lg " role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title">Paitent Listing</h4>
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>

				<div class="modal-body" id="fetchRecordDivId"></div>

			</div>
		</div>
	</div>

</body>
</html>