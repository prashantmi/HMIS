<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>
<%@taglib uri="/WEB-INF/dateTag.tld" prefix="date"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/patientDtl.tld" prefix="pDtl"%>
<%@ taglib uri="/WEB-INF/crNo.tld" prefix="crNo"%>

<html>
<head>
<meta charset=utf-8><title>Online Bill Cancellation</title>

<link href="../css/transaction.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet"
	type="text/css">
<link href="../../hisglobal/css/popup.css" rel="stylesheet"
	type="text/css">
<link href="../../hisglobal/css/buttons.css" rel="stylesheet" type="text/css">
<link href="/HIS/hisglobal/css/jquery-ui.css" rel="stylesheet" type="text/css">
<link href="/HIS/hisglobal/css/jqueryExtValidationToolTip.css" rel="stylesheet" type="text/css">

<script language="JavaScript" src="../../hisglobal/js/tab.js"></script>
<script language="JavaScript" src="../../hisglobal/js/validation.js"></script>
<script language="Javascript" src="../../hisglobal/js/popup.js"></script>
<script language="Javascript" src="../../billing/js/BillCancellation_Trans.js"></script>
<script language="Javascript" src="../../hisglobal/js/util.js"></script>
<script language="Javascript" src="../js/patientListing.js"></script>
<script language="Javascript" src="../../billing/js/billing.js"></script>
<script language="Javascript" src="../../ipd/js/jquery.simplemodal.js"></script>
<script language="JavaScript" src="../../hisglobal/js/jquery-1.10.1.min.js"></script>
<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/jquery-2.0.3.min.js"></script>
<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/jquery.easyui.js"></script>
<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/jquery-ui.js"></script>
<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/jqueryExtValidation.js"></script>
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
<body onload="showPatientDetails('document.forms[0].strCrNo');openPrintPopUp();" onfocus="checkPopUpAndSetDefaultCrNo();" onUnload="closePopUp();">

<html:form action="transactions/BillingCancellationTransCNT.cnt" method="post"
	onsubmit="GO();">
<div id="nonPrintable">	
	<div id='normalMsg' class="normalMsg"><bean:write
		name="BillingCancellationTransBean" property="strMsg" filter="false" /></div>
	<div id='errMsg' class="errMsg"><bean:write
		name="BillingCancellationTransBean" property="strErrMsg"
		filter="false" /></div>
	<div id="warningMsg" class="warningMsg"><bean:write
		name="BillingCancellationTransBean" property="strWarningMsg" filter="false" /></div>


	<tag:tab tabLabel="Receipt Cancellation " selectedTab="FIRST" align="center" width="TABLEWIDTH">
	</tag:tab>

	<table class='TABLEWIDTH' align="center" cellpadding="1px"
		cellspacing="0px">
		<tr class="HEADER">
		<td colspan="4"><b>Receipt Cancellation &gt;&gt;</b></td>
		</tr>
		<tr class="TITLE">
			<td colspan="3"><html:radio property="strCriteria"
				name="BillingCancellationTransBean" value="1"
				onclick="chkCriteria();">
				<b>Criteria 1: </b>
			</html:radio></td><td colspan="1"><input type="checkbox" align="right" onclick="chkcredit();" name="crbill" id="crbill" value="${BillingCancellationTransBean.crbill}">Credit Bill</td>
		</tr>
	</table>
	<div id="criteria1" style="display: block;">
	<table class='TABLEWIDTH' align="center" cellpadding="1px" cellspacing="1px" style="display: none;">
		<tr>
			<td class="LABEL">
			<div align="right"><html:radio property="strCase"
				name="BillingCancellationTransBean" value="1"
				onclick="chkCase(this);">With CR No.</html:radio> <html:radio
				property="strCase" name="BillingCancellationTransBean" value="2"
				onclick="chkCase(this);">Without CR No.</html:radio></div>
			</td>
		</tr>
	</table>
	<table class='TABLEWIDTH' align="center" cellpadding="1px" cellspacing="1px">
		<tr>
			<td width="25%" class="LABEL"><font color="red">*</font><B>CR
			No.</B></td>
			<td colspan="3" class="CONTROL"><crNo:crNo name="strCrNo"
				id="strCrNoId" value="${BillingCancellationTransBean.strCrNo}"
				js="onkeypress='return initGoFunc(event);'"></crNo:crNo>
			<img style="cursor: pointer; cursor: hand"
				src="../../hisglobal/images/viewDetails.gif"
				title="click here for patient search" name='searchPatient'
				onclick="showPatientListingWindow('6',document.forms[0].strCrNo,'setSelectedCrNo');" />
			<img src="../../hisglobal/images/Go.png" name="go"
				onclick="return GO();" onkeypress="return initGoFunc(event);">
			</td>
		</tr>
	</table>
	</div>
	<div id="criteria2" style="display: block">
	<div id="c2heading" style="display: block">
	<table class='TABLEWIDTH' align="center" cellpadding="1px"
		cellspacing="1px">
		<tr class="HEADER">
			<td><html:radio property="strCriteria"
				name="BillingCancellationTransBean" value="2"
				onclick="chkCriteria('1');">
				<b>Criteria 2: </b>
			</html:radio></td>
		</tr>
	</table>
	</div>
	<div id="c2body" style="display: none">
	<table class='TABLEWIDTH' align="center" cellpadding="1px"
		cellspacing="1px">
		<tr>
			<td width="25%" class="LABEL"><font color="red">*</font><B>Receipt
			No.</B></td>
			<td colspan="3" class="CONTROL"><input type="text"
				class="txtFldBig" name="strTransNo"
				value="${BillingCancellationTransBean.strTransNo}" maxlength="15"
				onkeypress="return validateData(event,5);"
				onkeyup="focusNext(this);"> / <input type="text"
				class="txtFldMin" name="strRcptNo"
				value="${BillingCancellationTransBean.strRcptNo}" maxlength="2"
				onkeypress="return validateData(event,5);"></td>
		</tr>
	</table>
	</div>


	</div>
	<div id="details" style="display: none">
	<table class='TABLEWIDTH' align="center" cellpadding="1px"
		cellspacing="1px">
		<tr class="HEADER">
			<td width="25" class="TITLE">
			<div id="plus" style="display: none"><img
				src="../../hisglobal/images/plus.gif" onclick="showinfo();"></div>
			<div id="minus" style="display: block"><img
				src="../../hisglobal/images/minus.gif" onclick="hideinfo();"></div>
			</td>
			<td colspan="4">Patient Demographic Information</td>

		</tr>
	</table>
	</div>

	<div id="demographicInfo" style="display:none" align="center"><bean:write
		name="BillingCancellationTransBean" property="strPatientDetailsView"
		filter="false" /></div>
	<div id="billdtls" style="display:none"><bean:write
		name="BillingCancellationTransBean" property="strBillDtl"
		filter="false"/></div>
	<div id="trfDtls"> </div>	
	<table class='TABLEWIDTH' align="center" cellpadding="1px"
		cellspacing="1px">
		<tr>
			<td width="25%" class="LABEL"><font color="red">*</font>Cancelled
			By</td>
			<td class="CONTROL" colspan="3"><select name="strCancelledBy"
				class="comboBig">
				<bean:write name="BillingCancellationTransBean"
					property="strCancelledBy" filter="false" />
			</select></td>



		</tr>
		<tr>
			<td width="25%" class="LABEL"><font color="red">*</font>Cancellation
			Reason</td>
			<td class="CONTROL" colspan="3"><select name="strCancelReason"
				class="comboNormal" onchange="showText();">
				<bean:write name="BillingCancellationTransBean"
					property="strCancelReason" filter="false" />
			</select> <input type="text" class="txtFldMax" name="strOtherReason" value=""
				maxlength="50" onkeypress=""></td>
	</table>
	



	<table class='TABLEWIDTH' align="center" cellpadding="1px"
		cellspacing="1px">
		<tr class="FOOTER">
			<td colspan="4"><font size="2" color="red">*</font> Mandatory
			Fields</td>
		</tr>
	</table>
<div id="dialog-message" title="Save" style="display:none;">
  <p>
    <b>Are You Sure to Save it?</b>.
  </p>
</div>
<br>
	<div id="lastbuttons" style="display: block">
	<table border="0" class='TABLEWIDTH' align="center" cellpadding="1px"
		cellspacing="1px">
		<tr>
			<td align="center"><!-- <img style="cursor: pointer; cursor: hand"
				src="../../hisglobal/images/btn-sv.png" title="click here to save the selected data"
				onClick="return validate1();"> <img
				style="cursor: pointer; cursor: hand" title="click here to clear the contents"  
				src="../../hisglobal/images/btn-clr.png"
				onclick="showFirstPage();"> <img
				style="cursor: pointer; cursor: hand" title="click here to cancel the process"
				src="../../hisglobal/images/btn-ccl.png" onclick="cancelFunc();">
				
				-->
				<br>
				<a href="#" class="button" id="" onclick=' return validate1();'><span class="save">Save</span></a>
							<a href="#" class="button"	onclick="showFirstPage();"><span class="clear">Clear</span></a> 
							<a href="#" class="button" onclick="cancelFunc();"><span class="cancel">Cancel</span></a>
			</td>
		</tr>
	</table>
	</div>

	<div id="onlyClearbutton" style="display: none">
	<table border="0" class=' TABLEWIDTH' align="center" cellpadding="1px"
		cellspacing="1px">
		<tr>
			<td align="center">
			<!-- <img style="cursor: pointer; cursor: hand" title="click here to clear the contents"  
				src="../../hisglobal/images/btn-clr.png"
				onclick="showFirstPage();"> <img
				style="cursor: pointer; cursor: hand" title="click here to cancel the process"
				src="../../hisglobal/images/btn-ccl.png" onclick="cancelFunc();">
				 -->
				 <br>
				<a href="#" class="button"	onclick="showFirstPage();"><span class="clear">Clear</span></a> 
							<a href="#" class="button" onclick="cancelFunc();"><span class="cancel">Cancel</span></a>
			</td>
		</tr>
	</table>
	</div>

	<input type="hidden" name="hmode" />
	<input type="hidden" name="CrNo"
		value="${BillingCancellationTransBean.strCrNo }" />
	<input type="hidden" name="chkValue" />
	<input type="hidden" name="strCRNoSatus"
		value="${BillingCancellationTransBean.strCRNoSatus }" />
	<input type="hidden" name="gblCRValue"/>
	<input type="hidden" name="billcancelflag"
		value="${BillingCancellationTransBean.billcancelflag }" />
	<input type="hidden" name="isOpenPopUp" value="${BillingCancellationTransBean.isOpenPopUp}"/>
	<input type="hidden" name="filePath" value="${BillingCancellationTransBean.filePath}"/>
	<input type="hidden" name="printMode" value="${BillingCancellationTransBean.printMode}"/>
	</div>
</html:form>

<div class="popup" id="popup" style="display: none"></div>
<tag:autoIndex></tag:autoIndex> 
<div id='printableSlip'>
<logic:equal name="BillingCancellationTransBean"  property="isOpenPopUp" value="1">
<logic:equal name="BillingCancellationTransBean"  property="printMode" value="1">
<logic:present name="BillingCancellationTransBean"  property="filePath">
<jsp:include page="billing_receipt_printing_popup.jsp"></jsp:include>
</logic:present>
</logic:equal>
</logic:equal>
</div>	
</body>
</html>