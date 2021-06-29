<!DOCTYPE html> 
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/dateTag.tld" prefix="date"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>
<%@ taglib uri="/WEB-INF/patientDtl.tld" prefix="pDtl"%>
<%@ taglib uri="/WEB-INF/crNo.tld" prefix="crNo"%>
<html>
<head>
<meta charset="utf-8" /> 
<title>Refund Approval</title>
<link href="../css/transaction.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/calendar-tas.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/popup.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/buttons.css" rel="stylesheet" type="text/css">
<link href="/HIS/hisglobal/css/jquery-ui.css" rel="stylesheet" type="text/css">
<link href="/HIS/hisglobal/css/jqueryExtValidationToolTip.css" rel="stylesheet" type="text/css">

<script language="Javascript" src="../../hisglobal/js/validation.js"></script>
<script language="JavaScript" src="../../hisglobal/transactionutil/js/master.js"></script>
<script language="JavaScript" src="../../hisglobal/js/tab.js"></script>
<script language="JavaScript" src="../../hisglobal/js/calendar.js"></script>
<script language="Javascript" src="../../hisglobal/js/multirow.js"></script>
<script language="Javascript" src="../../hisglobal/js/util.js"></script>
<script language="Javascript" src="../../hisglobal/js/popup.js"></script>
<script language="Javascript" src="../../billing/js/RefundApproval_Trans.js"></script>
<script language="Javascript" src="../../billing/js/billing.js"></script>
<script language="Javascript" src="../js/patientListing.js"></script>
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
<body onLoad="document.forms[0].strCrNo.focus(),showPatientDetails();openPrintPopUp();" onfocus="checkPopUpAndSetDefaultCrNo();">
<html:form action="transactions/RefundApprovalTransCNT.cnt"
	 method="post">
<div id="nonPrintable">	   
    <div id='errMsg' class="errMsg"><bean:write name="refundApprovalTransBean" property="strErrMsg" filter="false"/></div>
    
    
    
    <div id='normalMsg' class="normalMsg"><bean:write name="refundApprovalTransBean" property="strMsg" filter="false"/></div>
   <div id="warningMsg" class="warningMsg"><bean:write
		name="refundApprovalTransBean" property="strWarningMsg" filter="false" /></div>
    
    <div id="menu1" class=popup style="display:none"></div>

	<logic:equal  name="refundApprovalTransBean" property="strRefundMode"  value="1">

	<tag:tab tabLabel="Refund Request" selectedTab="FIRST" align="center"
		width="TABLEWIDTH">
	</tag:tab>
</logic:equal>

<logic:notEqual name="refundApprovalTransBean" property="strRefundMode"  value="1">

 
	<tag:tab tabLabel="Refund Approval" selectedTab="FIRST" align="center"
		width="TABLEWIDTH">
	</tag:tab>
 </logic:notEqual>

	<table class="TABLEWIDTH" cellspacing="1px" align="center">
		<tr class="HEADER" bgcolor="">
			<td colspan="6">
			 
		<logic:equal  name="refundApprovalTransBean" property="strRefundMode"  value="1">  Refund&gt;&gt;Request </logic:equal>

<logic:notEqual name="refundApprovalTransBean" property="strRefundMode"  value="1">Refund&gt;&gt;Approval </logic:notEqual>
			
			
			
			
			
			</td>
		</tr>
		<tr>
			
			<td class="LABEL" ><font color="red">*</font>CR No.</td>
			<td class="CONTROL" colspan="4">
                <crNo:crNo id="strCrNoId" value="${refundApprovalTransBean.strCrNo}" js="onkeypress='return initGoFunc(event);'"></crNo:crNo>
				<img style="cursor:pointer;cursor:hand" src="../../hisglobal/images/viewDetails.gif" title="click here for patient search"  name='searchPatient' onclick="checkForError();"/>
				<img title="click here for Bill Details" src="../../hisglobal/images/Go.png" name="go"  onclick="return goFunc();" onkeypress="return goFunc();"/>
				 
			</td>
		</tr>
	</table>
	<div  id="details" style="display: none">
			<table class="TABLEWIDTH" align="center">
  	 			 <tr class="TITLE"> 
  	 			 <td width="25" class="TITLE">
     										<div id="plus" style="display:none">
   											<img style="cursor:pointer;cursor:hand" title="click here to show Patient Details" src="../../hisglobal/images/plus.gif"  onclick="showinfo();">
   											</div><div id="minus" style="cursor:pointer;cursor:hand" title="click here to hide Patient Details" style="display:block"><img src="../../hisglobal/images/minus.gif"  onclick="hideinfo();"></div>
  										 </td>
  	 			  <td colspan="4"><b> Patient Demographic Information </b></td>
    								
 				 </tr>
 				 </table>	
 				<div id='patdtltld'>
 				 <bean:write name="refundApprovalTransBean" property="strPatientDtls" filter="false"/>
 				 </div>
 			 
 </div>
	
	
	<div id="billdtls" style="display:none">
	<bean:write name="refundApprovalTransBean" property="strBill" filter="false"/>	
</div>
<div id="trfDtls"> </div>
	<div id="combo" style="display: none">
	<logic:notEqual name="refundApprovalTransBean" property="strRefundMode"  value="1">
	<table class="TABLEWIDTH" border="0" cellspacing="1px" align="center">
		<tr>
			<td width='24%' class='LABEL'>
			<div align="right"><font color="red">*</font>Refund By:</div>
			</td>
			<td width="76%" class="CONTROL"><select name="strRefBy">
				<bean:write name="refundApprovalTransBean"
					property="strRmk" filter="false" />
			</select></td>
		</tr>
		</table>
	</logic:notEqual>	
		<table class="TABLEWIDTH" border="0" cellspacing="1px" align="center">
		<tr>
			<td width="10%" class="LABEL">
			<div align="right"><font color="red">*</font>Refund Reason:</div>
			</td>
			<td width="40%" class="CONTROL"><select name="refRsn"
				onChange="showText();">
				<bean:write name="refundApprovalTransBean"
					property="strRsn" filter="false" />
				<option value="0">others</option>
			</select>others specify:<input name="strOtherReason" type="text"
				class="txtFldMax" value="" readonly></td>
				
				
				<td width="25%" class="LABEL" id="td1" style="display: none;">
			<div align="right">
			Processing Fee Penalty:</div>
			</td>
			<td width="25%" class="CONTROL" id="td2" style="display: none;">
			<input name="strProcessFeePenalty" type="text"
			onkeypress="return initGoFunc(event);" tabindex="1" onkeyup="lTrim(this)" onblur="Trim(this)" value="0"	class="txtFldMin" value="" >
			</div>
			</td>
		</tr>
	</table>
	</div>
	
	
	<table class="TABLEWIDTH" align="center" border="0" cellspacing ="1px">
  
    <tr class="FOOTER"> 
    <td colspan="2"><div align="left">If Tariff/Request Not Shown then Service Is Already Consumed(Sample Collected,Service Taken etc.)</div></td>
  	<td colspan="2"><div align="right"><font size="2" color="red">*</font> Mandatory Fields</div></td>
  </tr>
  </table>
	<div id="dialog-message" title="Save" style="display:none;">
  <p>
    <b>Are You Sure to Save it?</b>.
  </p>
</div>
<br>
	<div id="lastbuttons" style="display: none">
	<table border="0" class="TABLEWIDTH" cellspacing="1px" align="center">
		<tr>
			<td align="center">
			<!-- <img style="cursor:pointer;cursor:hand"
				 title="click here to save the selected data" src="../../hisglobal/images/btn-sv.png" onClick="return validate1();">
				<img style="cursor:pointer;cursor:hand" title="click here to reset the CR No." src="../../hisglobal/images/btn-clr.png" 
				onclick="showFirstPage();" >
			<img  style="cursor:pointer;cursor:hand" title="click here to forward the control to main page"
				 src="../../hisglobal/images/btn-ccl.png"
				onclick="initPage();"> -->
				
				
							<br><a href="#" class="button" id="" onclick=' return validate1();'><span class="save">Save</span></a>
							<a href="#" class="button"	onclick="showFirstPage();"><span class="clear">Clear</span></a> 
							<a href="#" class="button" onclick="initPage();"><span class="cancel">Cancel</span></a>
				
				
			</td>
		</tr>
	</table>
	</div>
	
<div id="onlyClearbutton" style="display:block">	
<table border="0" class=' TABLEWIDTH' align="center" cellpadding="1px" cellspacing="1px">
		<tr>
			<td align="center">
			<!-- <img style="cursor:pointer;cursor:hand" title="click here to reset the CR No." src="../../hisglobal/images/btn-clr.png" 
				onclick="showFirstPage();" >
			<img  style="cursor:pointer;cursor:hand" title="click here to forward the control to main page"
				 src="../../hisglobal/images/btn-ccl.png"
				onclick="cancelFunc();">
				-->
				<br><a href="#" class="button"	onclick="showFirstPage();"><span class="clear">Clear</span></a> 
							<a href="#" class="button" onclick="cancelFunc();"><span class="cancel">Cancel</span></a>
				</td>
		</tr>
</table>
</div>
	
	<input type="hidden" name="hmode"/>
	<input type="hidden" name="CrNo" value="${refundApprovalTransBean.strCrNo }"/>
	<input type="hidden" name="chkValue"/>
	
	<input type="hidden" name="strIpdRefundPenaltyAmt" value="${refundApprovalTransBean.strIpdRefundPenaltyAmt }"/>
	<input type="hidden" name="strOpdRefundPenaltyAmt" value="${refundApprovalTransBean.strOpdRefundPenaltyAmt }"/>
	<input type="hidden" name="strEmergencyRefundPenaltyAmt" value="${refundApprovalTransBean.strEmergencyRefundPenaltyAmt }"/>
	<input type="hidden" name="strRefundMode" value="${refundApprovalTransBean.strRefundMode }"/>
	<input type="hidden" name="isOpenPopUp" value="${refundApprovalTransBean.isOpenPopUp}"/>
	<input type="hidden" name="filePath" value="${refundApprovalTransBean.filePath}"/>
	<input type="hidden" name="printMode" value="${refundApprovalTransBean.printMode}"/>
	<input type="hidden" name="strBillNo" value="${refundApprovalTransBean.strBillNo}"/>
	<input type="hidden" name="strBillDate" value="${refundApprovalTransBean.strBillDate}"/>
	<input type="hidden" name="trf_grossRefund" value="${refundApprovalTransBean.trf_grossRefund}"/>
	
	<input type="hidden" value="0" name="popId">
    <input type="hidden" name="gblCRValue"/>

</div>
	
	</html:form>
	<tag:autoIndex></tag:autoIndex>  
<div id='printableSlip'>
<logic:equal name="refundApprovalTransBean"  property="isOpenPopUp" value="1">
<logic:equal name="refundApprovalTransBean"  property="printMode" value="1">
<logic:present name="refundApprovalTransBean"  property="filePath">
<jsp:include page="billing_receipt_printing_popup.jsp"></jsp:include>
</logic:present>
</logic:equal>
</logic:equal>
</div>		
</body>
</html>