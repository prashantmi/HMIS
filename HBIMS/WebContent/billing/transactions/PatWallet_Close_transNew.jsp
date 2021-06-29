<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

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
<meta charset=utf-8>

<title>Patient Wallet Close </title>

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
<script language="Javascript" src="../../hisglobal/js/validation.js"></script>
<script language="Javascript" src="../../hisglobal/js/util.js"></script>
<script language="Javascript" src="../../hisglobal/js/popup.js"></script>
<script language="Javascript" src="../../hisglobal/js/newpopup.js"></script>
<script language="Javascript" src="../../hisglobal/js/dropdown.js"></script>
<script language="Javascript" src="../js/billing.js"></script>
<script language="Javascript" src="../js/tariffSearch.js"></script>
<script language="Javascript" src="../js/tariffCodeSearch.js"></script>
<script language="Javascript" src="../js/patientListing.js"></script>
<script language="Javascript" src="../../ipd/js/jquery.simplemodal.js"></script>
<script language="JavaScript" src="../../hisglobal/js/jquery-1.10.1.min.js"></script>
<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/jquery-2.0.3.min.js"></script>
<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/jquery.easyui.js"></script>
<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/jquery-ui.js"></script>
<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/jqueryExtValidation.js"></script>


<script language="Javascript" src="../js/PatWallet_offline_transBS.js"></script>
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
<body
	onLoad="onLoadLogics();addRows(new Array('strOfflinePaymentMode','strOfflinePaymentDtls','strOfflineAmount'),new Array('s','t','t'),'3','1','I');openPrintPopUp();"
	onfocus="checkPopUp();" onUnload="closePopUp();">
<script language="Javascript" src="../../hisglobal/js/wz_tooltip.js"></script>
<html:form 
	action="/transactions/PatWalletTransBSCNT" method="post" enctype="multipart/form-data">

<fieldset form="form1">
	
  <legend style="width:auto; margin-bottom: 0px;font-size: 1.5rem;font-weight: 400;margin-left:10px;color: #666;" id='nonPrintableLegend'>Cash Collection</legend>
  <div class="legend2" id='nonPrintableLegend2'>
	<button  type="button" class="float-right btn btn-outline-danger mt-1 btn-circle cancelbtn" onclick="cancelFunc();" style="background-color: #d9534f;">
		<i class="fas fa-ban iround"  title="Cancel"></i>
	</button>	
	<button  id="printbutton" type="button" class="float-right btn btn-outline-primary mt-1 btn-circle printbtn" data-toggle="modal" data-target="#printModal" onclick="" style="display: none;background-color: #007bff;">
		<i class="fas fa-print iround"  title="Print Last Slip"></i>
	</button>
	
    <button  type="button" id="savebutton" class="float-right btn btn-outline-success mt-1 btn-circle savebtn" tabindex='2' onclick='return validate1();' name="patientAdmissionModiTransBean"  data-toggle="modal" data-target="#validateModal" style="display: none;background-color: #5cb85c;">					
		<i class="fas fa-save iround"  title="Save" ></i>
	</button>	
	
							                 
  </div>
  <div  class="viewport" id="nonPrintable">
		<div class="container-fluid">
  <div class="col-md-12">	

						 <bean:write name="PatWalletTransBean" property="strPatientDetailsView" filter="false" />
									  			   
											
<div class="prescriptionTile" >

	<div id="blanket" style="display: none;"></div>
    <div id="nonPrintable">
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
						value="${PatWalletTransBean.strCurrentDate }" >
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
					<td> <input type="hidden" name="strConfirmationType" value="${PatWalletTransBean.strConfirmationType}">
					
					<logic:equal name="PatWalletTransBean"  property="strConfirmationType" value="0">
					<center> <input type="button" name="billConfirmCancel" tabindex="1" value="Cancel" class="btn"  onclick="return confirmCancel();" onblur="document.forms[0].billConfirmOk.focus();" > 
					<input type="button" name="billConfirmOk" class="btn" tabindex="1" value="Ok" onblur="document.forms[0].billConfirmCancel.focus();"   onclick="return confirmOk();">  
					</center>
					</logic:equal>
					<logic:equal name="PatWalletTransBean"  property="strConfirmationType" value="1">
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
					<td class="LABEL" id='totalCardAmtDivId1' colspan='4'><font color="red">#</font><font size="2">Final Transaction Amount(with Surcharge)(<img src='/HBIMS/hisglobal/images/INR.png'>)&nbsp;0.00</font></td>
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
						value="${PatWalletTransBean.strCurrentDate }" >
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
						src='../../hisglobal/images/btn-sv.png'  id= "savedivid" tabindex="1"
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
					<bean:write name="PatWalletTransBean" property="strClientNameContents" filter="false"/>
					</select></td>
				</tr>
				<tr>
					<td class='LABEL'><font color="red">*</font>Auth No.</td>
					<td class='CONTROL'><input type='text' name='strClientAuthNo' tabindex="1"
						class='txtFldNormal' onkeypress="return validateData(event,5);" /></td>
					<td class='LABEL'><font color="red">*</font>Auth Date</td>
					<td class='CONTROL'><input type="text" class="txtFldDate" tabindex="1"
						maxlength="11" name="strClientAuthDate"
						value="${PatWalletTransBean.strCurrentDate }" >
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
						title="_div_popup_cntl" class='comboNormal'>
						<option value='1'>Fixed</option>
						<option value='2'>Percentage</option>
					</select></td>
				</tr>
				<tr>
					<td class='LABEL'><font color="red">*</font>Discount By</td>
					<td class='CONTROL'><select title="_div_popup_cntl" 
						class='comboMax' name=''>
						<bean:write name="PatWalletTransBean"
							property="strOfflineDiscountApprovedByDetails" filter="false" />
					</select></td>
					<td class='LABEL'><font color="red">*</font>Discount Reason</td>
					<td class='CONTROL'><select title="_div_popup_cntl"
						class='comboNormal'>
						<bean:write name="PatWalletTransBean"
							property="strOfflineDiscountRemarksDetails" filter="false" />
					</select></td>
				</tr>
				<tr>
					<td colspan='2' class='LABEL'><font color="red">*</font>Discount
					Date</td>
					<td colspan='2' class='CONTROL'><date:date name=""
						value="${PatWalletTransBean.strCurrentDate}" ></date:date></td>
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
						<bean:write name="PatWalletTransBean"
							property="strOfflineDiscountApprovedByDetails" filter="false" />
					</select></td>
					<td class='LABEL'><font color="red">*</font>Discount Reason</td>
					<td class='CONTROL'><select title="_div_popup_cntl"
						class='comboNormal' name="strOffLineTariffDiscountReason"
						onchange="setReasonText();">
						<bean:write name="PatWalletTransBean"
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
						value="${PatWalletTransBean.strCurrentDate }" >
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
		name="PatWalletTransBean" property="strErrMsg" filter="false" /></div>
	<div id="normalMsg" class="normalMsg"><bean:write
		name="PatWalletTransBean" property="strNormalMsg" filter="false" /></div>
	<div id="warningMsg" class="warningMsg"><bean:write
		name="PatWalletTransBean" property="strWarningMsg" filter="false" /></div>
	<tag:tabNew1 tabList="${PatWalletTransBean.lhm}" selectedTab="PATWALLET" align="center" 
		width="TABLEWIDTH">
	</tag:tabNew1></center>


           <div class="row rowFlex reFlex">
			<div class="col-sm-12" align="right">
		    <html:radio name="PatWalletTransBean" property="strAccountStatus" value="1" onclick="setStrAccountStatus('1');">&nbsp;Wallet Open</html:radio>&nbsp;&nbsp;
			<html:radio name="PatWalletTransBean" property="strAccountStatus" value="2" onclick="setStrAccountStatus('2');">&nbsp;Wallet Close</html:radio>&nbsp;&nbsp;
			<html:radio name="PatWalletTransBean" property="strAccountStatus" value="3" onclick="setStrAccountStatus('3');">&nbsp;Recharge Wallet</html:radio> &nbsp;&nbsp;
		    <html:radio name="PatWalletTransBean" property="strAccountStatus" value="4" onclick="setStrAccountStatus('4');">&nbsp;Transaction History</html:radio>
		</div>
		</div>
	
		<div class="row rowFlex reFlex">
		<div class="col-sm-8"></div>
		<div class="col-sm-4">
		<html:radio property="strCRorWalletwise" name="PatWalletTransBean" value="1" onclick="IsCrOrWalletWise('1'); ">&nbsp;&nbsp;CR No. wise</html:radio>
		&nbsp;&nbsp;<html:radio property="strCRorWalletwise" name="PatWalletTransBean" value="2" onclick="IsCrOrWalletWise('2');">&nbsp;&nbsp;Mobile No. wise</html:radio>
		</div>
		</div>
								
										<div class="row rowFlex reFlex" id='goid'>
											<div class="col-sm-2" id="CrNOOrWallet" align="right">
												<label><font color="red">*</font>CR No.</label>
											</div>
											<div class="col-sm-2" id="CRWise">
												<crNo:crNo id="strCrNoId" name="strCrNo"
													value="${PatWalletTransBean.strCrNo}"
													js="onkeypress='return initGoFunc(event);'"
													className="form-control"></crNo:crNo>
											</div>
											<div class="col-sm-2" id="WalletWise" style="display: none">
												<input type="text" class="form-control" name="strWalletNo"
													value="${PatWalletTransBean.strWalletNo}"
													js=" onkeypress='return initGoFunc(event);'" />
											</div>

											<div class="col-sm-8" align="left">
												<a href="#" class="btn btn-sm btn-success" name="go"
													onClick="return goFunc();" onKeyPress="return goFunc();"
													style="font-size: 1rem;"> GO&nbsp;<i
													class="fas fa-angle-double-right"></i>
												</a>
											</div>
										</div>

	
	<logic:equal value="1" name="PatWalletTransBean" property="strIsWithoutCrNoRequired">
	<table class="TABLEWIDTH" align="center" cellpadding="1px" cellspacing="1px">
		<tr>

			<td class="LABEL" colspan="4">
			
			<html:radio
				name="PatWalletTransBean" property="strPatientMode" value="1"
				onclick="selectPatientMode(this);">With CR No.</html:radio> <html:radio
				name="PatWalletTransBean" property="strPatientMode" value="2"
				onclick="selectPatientMode(this);">Without CR No.</html:radio></td>
		</tr>
	</table>
	</logic:equal>
	
	<logic:notEqual value="1" name="PatWalletTransBean" property="strIsWithoutCrNoRequired">
	<div style="display: none">
	<table class="TABLEWIDTH" align="center" cellpadding="1px" cellspacing="1px">
		<tr>

			<td class="LABEL" colspan="4">
			
			<html:radio
				name="PatWalletTransBean" property="strPatientMode" value="1"
				onclick="selectPatientMode(this);">With CR No.</html:radio> <html:radio
				name="PatWalletTransBean" property="strPatientMode" value="2"
				onclick="selectPatientMode(this);">Without CR No.</html:radio></td>
		</tr>
	</table>
	</div>
	</logic:notEqual>
	
	
	 <%-- <tr>

			<td class="LABEL" colspan="8">
			
			<html:radio
				name="PatWalletTransBean" property="strAccountStatus" value="1"
				onclick="setStrAccountStatus('1');" >Wallet Open</html:radio> <html:radio
				name="PatWalletTransBean" property="strAccountStatus" value="2"
				onclick="setStrAccountStatus('2');">Wallet Close</html:radio>
		
			<html:radio
				name="PatWalletTransBean" property="strAccountStatus" value="3"
				onclick="setStrAccountStatus('3');">Recharge Wallet</html:radio> <html:radio
				name="PatWalletTransBean" property="strAccountStatus" value="4"
				onclick="setStrAccountStatus('4');">Transaction History</html:radio></td>
		</tr>  --%>
		
		<table class="TABLEWIDTH" align="center" cellpadding="1px" cellspacing="1px">
		<tr style="display: none;">
			<td colspan="1" class="LABEL" width="25%"><b><font color="red">*</font>Hospital Service</b></td>
			<td class="CONTROL" colspan="1" width="25%"><select class="comboNormal" name="strOffLineHospitalService" onchange="getBillService();">
				<bean:write name="PatWalletTransBean" property="strHospitalServiceDetails" filter="false" />
			</select>&nbsp;</td>
			<td width="25%" class="LABEL" colspan="1"><font color="red">*</font>Billing Services</td>
			<td width="25%" class="CONTROL" colspan="1">
			<div id='billServiceDivId'><select class="comboNormal" name="strOffLineBillingService" onchange="resetTotalAmount(this);">
				<bean:write name="PatWalletTransBean" property="strBillingServiceDetails" filter="false" />
			</select></div>
			</td>
		</tr>
		
		
		<tr>
			 <%-- <td width="50%" colspan="1" class="LABEL"><div align='Left'><font color="red">*</font>CR No.
			 	
				 	<crNo:crNo id="strCrNoId" name="strCrNo" value="${PatWalletTransBean.strCrNo}" js="onkeypress='return initGoFunc(event);'"></crNo:crNo>
				 	<img src="../../hisglobal/images/Go.png" style="cursor: hand; cursor: pointer" title="Get Bill Details" name="go" onClick="return goFunc();" onKeyPress="return goFunc();" align="middle"> &nbsp;&nbsp;
				 </div>
			</td>		 --%>






										<td  style="display: none;" width="25%" class="LABEL" colspan="1"><font color="red">*</font>Request Type</td>
			<td style="display: none;"  width="25%" class="CONTROL" colspan="1">			
				<logic:equal name="PatWalletTransBean" property="strIsRefundReq" value="1">
					<html:select name="PatWalletTransBean" property="strOffLineRequestType" styleClass="comboNormal"  onchange="getBillService();">
						<html:option value="1">Receipt</html:option>
						<html:option value="2">Refund</html:option>
					</html:select>
				</logic:equal>			
				<logic:equal name="PatWalletTransBean" property="strIsRefundReq" value="0">
					<html:select name="PatWalletTransBean" property="strOffLineRequestType" styleClass="comboNormal"  onchange="getBillService();">
						<html:option value="1">Receipt</html:option>
					</html:select>
				</logic:equal>			
			 </td>
		</tr>
		</table>
	
	 <div id="offlineDetailsDivId" style="display: none;">
	 
	<!-- off-line start -->
 
	<div id='offlineClientDivId'><bean:write
		name="PatWalletTransBean" property="strOfflineClientDetailsView"
		filter="false" /></div>
	
		
		<div id="offlineDepEpdTreatWardDivId" style="display: none">
		
		<table class="TABLEWIDTH" align="center" cellpadding="1px"
		cellspacing="1px" style="display: none;">
		<tr>
			<td width="25%" class="LABEL"><font color="red">*</font>Raising
			Department</td>
			<td width="25%" class="CONTROL">
			<div id='offlineRaisingDepartmentDivId'><select
				class="comboNormal" name="strOffLineRaisingDepartment"
				onchange="getEpisodeList(this);">
				<bean:write name="PatWalletTransBean"
					property="strRaisingDepartmentDetails" filter="false" />
			</select></div>
			</td>
			<td width="25%" class="LABEL">Episode</td>
			<td width="25%" class="CONTROL">
			<div id='episodeDivId'><select class="comboNormal"
				name="strOffLineEpisode">
				<bean:write name="PatWalletTransBean"
					property="strEpisodeDetails" filter="false" />
			</select></div>
			</td>
		</tr>
		<tr>
			<td width="25%" class="LABEL"><font color="red">*</font>Patient Category</td>
			<td width="25%" class="CONTROL">
			<div id='offlineTreatmentCategoryDivId'>
				<select class="comboNormal" name="strOffLineTreatmentCategory" onchange="poorFreeCheck('strOffLineTreatmentCategory'),getPartAccDtls(document.getElementsByName('strOffLineWard')[0]);">
					<bean:write name="PatWalletTransBean" property="strTreatmentCategoryDetails" filter="false" />
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
						<bean:write name="PatWalletTransBean"
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
						<bean:write name="PatWalletTransBean"
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
		name="PatWalletTransBean"
		property="strPartPayAdvanceAmountDetails" filter="false" /></div>

	<div id="otherRefundDetailsDivId" style="display: none">
	<table class='TABLEWIDTH' border="0" cellpadding="1px"
		cellspacing="1px" align="center">
		<tr>
			<td width='25%' class='LABEL'><font color="red">*</font>Refund
			By:</td>
			<td width="75%" class="CONTROL"><select
				name="strOffLineRefundBy">
				<bean:write name="PatWalletTransBean"
					property="strOffLineRefundByList" filter="false" />
			</select></td>
		</tr>
		<tr>
			<td width="25%" class="LABEL"><font color="red">*</font>Refund
			Reason:</td>
			<td width="75%" class="CONTROL"> <table><tr><td class="CONTROL"><select
				name="strOffLineRefundReason" onChange="setRefundRemarksText();">
				<bean:write name="PatWalletTransBean"
					property="strOffLineRefundReasonDetails" filter="false" />
			</select></td><td><input name="strOffLineRefundReasonText" type="text"
				class="txtFldMax" value="" disabled="disabled"></td></tr></table>  </td>
		</tr>
		<tr>
			<td class="LABEL" width="25%">Refund Date :</td>
			<td width="75%" class="CONTROL"> 
			<input type="hidden" name="strOfflineRefundDate"
				value="${PatWalletTransBean.strCurrentDate}">
			<bean:write name="PatWalletTransBean" property="strCurrentDate" />
			
			</td>
		</tr>
	</table>
	</div>
	 
	 <div id ="disBnR" style="display: block;">
	  
	  <p class="subHeaders">
	   <i class="fas fa-wallet" style="font-size: 26px;">&nbsp;</i>Close Wallet</p>
	  <div id="strWalletAccountSummary">
	<bean:write property="strWalletAccountSummary" name="PatWalletTransBean" filter="false"/>
	</div>
	  
	  
	  
	 
	   
			
			
			
		
		<%-- <tr> 
			<td width="25%" class='LABEL' colspan='1'><div align="right">Relation With Patient:</div></td>
			<td width="25%" class="CONTROL" colspan='1'>
		        <select name="strRelationId" class='comboNormal'><bean:write name="PatWalletTransBean" property="strRelatinDetails" filter="false"/></select>
			</td>
			<td width="25%" class='LABEL' colspan='1'><div align="right">Card Validity:</div></td>
			<td width="25%" class="CONTROL" colspan='1'>
		         <date:date name="strCardValidity" value=""/>   
		</td>
		</tr> --%>
<!-- 		 <tr>  -->
<!-- 			<td width="24%" class='LABEL' colspan='1'><div align="right">Waiver By:</div></td> -->
<!-- 			<td width="76%" class="CONTROL" colspan='3'> -->
<%-- 		        <select name="strRmk"><bean:write name="PatWalletTransBean" property="strRmk" filter="false"/></select>	         --%>
<!-- 		</td> -->
<!-- 		</tr> -->
		
	       
  	</div>
	
	
	<div id="id3" style="display: none;"></div>



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
		cellspacing='1px' style="display: none;">
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
		cellspacing="1px" style="display: none;">
		<tr>

			<td width="60%" class="LABEL">Net  Refundable Amount(cash)
			(<img src='/HBIMS/hisglobal/images/INR.png'>)</td>
			<td width="15%" class="CONTROL" style="font-weight: bold">

			<table>
				<tr>
					<td><input type='hidden' name='strOfflineTotalRecAmount'
						id='strOfflineTotalRecAmount' value='0.00'></td>
					<td class="CONTROL">
					<div id='totalRecAmtDivId'></div>${PatWalletTransBean.strWalletBalanceAmount}</div>
					</td>
				</tr>
			</table>


			</td>
		</tr>

	</table>

	<table class="TABLEWIDTH" align="center" cellpadding="1px"
		cellspacing="1px" style="display: none;">
		<tr>

			<td width="60%" class="LABEL">Net  Refund Amount (cash)(<img src='/HBIMS/hisglobal/images/INR.png'>)</td>
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
		cellspacing="1px" style="display: none;">
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

	
	

<div id="dialog-message" title="Save" style="display:none;">
  <p>
    <b>Are You Sure to Save it?</b>.
  </p>
</div>	

<!-- <div align="center" id="saveid">					 
							<a href="#" class="button" id="" onClick="closeFunc();" onkeypress="onKeyPressLogic('1',event);"><span class="save">Save</span></a>
							<a href="#" class="button"	onClick="clearFunc(document.forms[0].strCounterMode.value);"><span class="clear">Clear</span></a> 
							<a href="#" class="button" onClick="cancelFunc(); "onkeypress="onKeyPressLogic('3',event);"><span class="cancel">Cancel</span></a>
					</div> -->
					<hr>
					<div class="row rowFlex reFlex">
								<div class="col-sm-10"></div>
								<div class="col-sm-2" align="right">
									<i class="fas fa-asterisk" style="color: red; font-size: smaller;"></i>Fields Mandatory
								</div>
							</div>
	<input type="hidden" name="hmode" />
	<input type='hidden' name='strPkgServiceFlag' value='0' />
	<input type="hidden" name="strTempCtDate"
		value="${PatWalletTransBean.strCurrentDate}" />
	<input type="hidden" name="strIpdThirdPartyBenefit"
		value="${PatWalletTransBean.strIpdThirdPartyBenefit}" />
	<input type="hidden" name="strEmergencyThirdPartyBenefit"
		value="${PatWalletTransBean.strEmergencyThirdPartyBenefit}" />
	<input type="hidden" name="strOpdThirdPartyBenefit"
		value="${PatWalletTransBean.strOpdThirdPartyBenefit}" />

	<input type="hidden" name="strOfflineIpdPenaltyVal"
		value="${PatWalletTransBean.strOfflineIpdPenaltyVal}" />
	<input type="hidden" name="strOfflineOpdPenaltyVal"
		value="${PatWalletTransBean.strOfflineOpdPenaltyVal}" />
	<input type="hidden" name="strOfflineEmergencyPenaltyVal"
		value="${PatWalletTransBean.strOfflineEmergencyPenaltyVal}" />

	<input type='hidden' name='strOffLineRefundAdvanceAccountNo' value='0' />


	<%-- current state represents  1 - Online and 2 - Off-line--%>
	<input type="hidden" name="currentState"
		value="${PatWalletTransBean.currentState}" />
	<input type="hidden" name="currentBserviceId" value='1' />
	<input type="hidden" name="currentReceiptType" value='1' />
	<input type="hidden" name="currentHospService" value='1' />
	
	<input type="hidden" name="strTempBillNo" value="${PatWalletTransBean.strTempBillNo}" />
	<input type="hidden" name="strTempReceiptNo" value="${PatWalletTransBean.strTempReceiptNo}" />


<input type="hidden" name="strIsIpdDiscount" value="${PatWalletTransBean.strIsIpdDiscount}" />
<input type="hidden" name="strIsOpdDiscount" value="${PatWalletTransBean.strIsOpdDiscount}" />
<input type="hidden" name="strIsEmergencyDiscount" value="${PatWalletTransBean.strIsEmergencyDiscount}" />
	
<input type="hidden" name="strCounterMode" value="${PatWalletTransBean.strCounterMode}" />
<input type="hidden" name="strIsWithoutCrNoRequired" value="${PatWalletTransBean.strIsWithoutCrNoRequired}" />
<input type="hidden" name="strPrintMessageLimit" value="${PatWalletTransBean.strPrintMessageLimit}" />

<input type="hidden" name="strFreeCategory" value="${PatWalletTransBean.strFreeCategory}" />
<input type="hidden" name="strIsApprovalRequired" value="${PatWalletTransBean.strIsApprovalRequired}" />
<input type="hidden" name="strIsAdvanceRequired" value="${PatWalletTransBean.strIsAdvanceRequired}" />
<input type="hidden" name="strConsumableCharge" value="${PatWalletTransBean.strConsumableCharge}" />
<input type="hidden" name="strOfflineTotalPayAmountWithoutConsumable" value="${PatWalletTransBean.strOfflineTotalPayAmountWithoutConsumable}" />
<input type="hidden" name="strGroupIdForConsumableConcatenated" value="${PatWalletTransBean.strGroupIdForConsumableConcatenated}" />
<input type="hidden" name="strConsumableChargesGroupId" value="${PatWalletTransBean.strConsumableChargesGroupId}" />
<input type="hidden" name="strConsumableChargesTariffCode" value="${PatWalletTransBean.strConsumableChargesTariffCode}" />
<input type="hidden" name="strCreditLetterValidity" value="${PatWalletTransBean.strCreditLetterValidity}" />
<input type="hidden" name="isOpenPopUp" value="${PatWalletTransBean.isOpenPopUp}"/>
<input type="hidden" name="filePath" value="${PatWalletTransBean.filePath}"/>
<input type="hidden" name="printMode" value="${PatWalletTransBean.printMode}"/>
<input type="hidden" name="strWalletAccountNo" value="${PatWalletTransBean.strWalletAccountNo}" />
<input type="hidden" name="strWalletAccountOpenDate" value="${PatWalletTransBean.strWalletAccountOpenDate}" />
<input type="hidden" name="strWalletDepositedAmount" value="${PatWalletTransBean.strWalletDepositedAmount}"/>
<input type="hidden" name="strWalletBalanceAmount" value="${PatWalletTransBean.strWalletBalanceAmount}"/>
<input type="hidden" name="strWalletAccountStatus" value="${PatWalletTransBean.strWalletAccountStatus}"/>
<input type="hidden" name="strExpenseamt" value="${PatWalletTransBean.strExpenseamt}"/>
<input type="hidden" name="chkMobileNoCount" value="${PatWalletTransBean.chkMobileNoCount}"/>
<input type="hidden" name="strCRNum" value="${PatWalletTransBean.strCrNo}"/>
<input 	type="hidden" name="strSurCc" value="${PatWalletTransBean.strSurCc}">
<input 	type="hidden" name="strSurDc" value="${PatWalletTransBean.strSurDc}">
<input 	type="hidden" name="strSurIc" value="${PatWalletTransBean.strSurIc}">
<input 	type="hidden" name="strSurId" value="${PatWalletTransBean.strSurId}">
<input 	type="hidden" name="strSurCc1" value="${PatWalletTransBean.strSurCc1}">
<input 	type="hidden" name="strSurDc1" value="${PatWalletTransBean.strSurDc1}">
<input 	type="hidden" name="strSurIc1" value="${PatWalletTransBean.strSurIc1}">
<input 	type="hidden" name="strSurId1" value="${PatWalletTransBean.strSurId1}">
<input type="hidden" name="defsurlim" value="${PatWalletTransBean.defsurlim}" />
<input type="hidden" name="strMobileNo" value="${PatWalletTransBean.strMobileNo}" />
<input 	type="hidden" name="selectedTab" >
</div>	
</div>
</div>
</div>
</div>
</fieldset>
</html:form>
<jsp:include page="multirow_PatWallet_offline_billtrans.jsp"></jsp:include>
<jsp:include page="dropdown_PatWallet_trans.jsp"></jsp:include>

<div id='printableSlip'>
<logic:equal name="PatWalletTransBean"  property="isOpenPopUp" value="1">
<logic:equal name="PatWalletTransBean"  property="printMode" value="1">
<logic:present name="PatWalletTransBean"  property="filePath">
<jsp:include page="billing_receipt_printing_popup.jsp"></jsp:include>
</logic:present>
</logic:equal>
</logic:equal>
</div>

</body>
</html>