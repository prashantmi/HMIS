

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>
<%@taglib uri="/WEB-INF/dateTag.tld" prefix="date"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset=utf-8><title>Bill Cancellation</title>

<link href="../css/transaction.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/calendar-tas.css" rel="stylesheet"type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/popup.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/buttons.css" rel="stylesheet" type="text/css">
<script language="JavaScript" src="../../hisglobal/js/tab.js"></script>
<script language="Javascript" src="../../hisglobal/js/validation.js"></script>
<script language="Javascript" src="../../hisglobal/js/util.js"></script>
<script language="Javascript" src="../../billing/js/BillCancellation_Trans.js"></script>
<script language="JavaScript" src="../../hisglobal/js/calendar.js"></script>
<script language="Javascript" src="../js/patientListing.js"></script>
<script language="Javascript" src="../../hisglobal/js/popup.js"></script>


</head>
<body onload="showPatientDetails('document.forms[0].strGuarantorName');" onFocus="checkPopUp();" onUnload="closePopUp();">

<html:form action="transactions/BillingCancellationTransCNT.cnt"  method="post" onsubmit="GO2();">
 	
<div id='normalMsg' class="normalMsg"><bean:write name="BillingCancellationTransBean" property="strMsg" filter="false"/></div>
<div id='errMsg' class="errMsg"><bean:write name="BillingCancellationTransBean" property="strErrMsg" filter="false"/></div>
<div id="warningMsg" class="warningMsg"><bean:write
		name="BillingCancellationTransBean" property="strWarningMsg" filter="false" /></div>
 	


<tag:tab tabLabel="Receipt Cancellation " selectedTab="FIRST" align="center" width="TABLEWIDTH">
	</tag:tab> 	
 	
<table class='TABLEWIDTH' align="center" cellpadding="1px" cellspacing="1px">
  <tr class="HEADER" > 
    <td ><b>Billing Cancellation &gt;&gt;</b>
    </td>
    </tr>
    <tr class="HEADER" > <td>
     <html:radio property="strCriteria" name="BillingCancellationTransBean" value="1" onclick="chkCriteria();"><b>Criteria 1: </b></html:radio>
     </td>
    </tr>
</table>
<div id="criteria1" style="display: block">
	<table class='TABLEWIDTH' align="center" cellpadding="1px" cellspacing="1px">
    <tr >
    <td class="LABEL">
    <div align="right">
    	<html:radio property="strCase" name="BillingCancellationTransBean" value="1" onclick="chkCase(this);">With CR No.</html:radio>
   		<html:radio property="strCase" name="BillingCancellationTransBean" value="2" onclick="chkCase(this);">Without CR No.</html:radio>
	</div>
    </td>
 	 </tr>
 	</table>
 	<table class='TABLEWIDTH' align="center" cellpadding="1px" cellspacing="1px">
     <tr> 
    <td width="25%" class="LABEL"><font color="red">*</font><B>Guarantor Name: </B></td>
    <td class="CONTROL">
   <input type="text" class="txtFldMax" name="strGuarantorName" value="${BillingCancellationTransBean.strGuarantorName}" maxlength="100" 
   onkeypress="return validateData(event,11);" ><img style="cursor:pointer;cursor:hand" src="../../hisglobal/images/viewDetails.gif" 
   title="click here for patient search" name='searchPatient' 
   onclick="showPatientListingWindow('7',document.forms[0].strGuarantorName,'setSelectedGrntrName');"/>
    <input type="image"  src="../../hisglobal/images/Go.png" name="go" value="Go" >
    </td>   
  </tr>
</table> 
</div>

<div id="criteria2" style="display: block">
 	<div id="c2heading" style="display: block">
 		<table class='TABLEWIDTH' align="center" cellpadding="1px" cellspacing="1px">
 		 <tr class="HEADER" > 
   		 <td > <html:radio  property="strCriteria" name="BillingCancellationTransBean" value="2" onclick="chkCriteria('2');"><b>Criteria 2: </b></html:radio>
   		  </td>
    	</tr>
   		 </table>
 	</div>
 	<div id="c2body" style="display: none">
 		<table class='TABLEWIDTH' align="center" cellpadding="1px" cellspacing="1px">
 			 <tr> 
    		<td width="25%" class="LABEL"><font color="red">*</font><B>Receipt No.</B></td>
   			 <td colspan="3" class="CONTROL">
   			<input type="text" class="txtFldBig" name="strTransNo" value="${BillingCancellationTransBean.strTransNo}" maxlength="15" onkeypress="return validateData(event,5);" onkeyup="focusNext(this);" >
  			/ <input type="text" class="txtFldMin" name="strRcptNo"  value="${BillingCancellationTransBean.strRcptNo}" maxlength="2" onkeypress="return validateData(event,5);" >
  			
  		    </td>   
  			</tr>
   		 </table>
 	</div>
 	
 
 </div>
		<div id="fetchRecordDivId"></div>
					
			<div id="billdtls" style="display:none">
		<bean:write name="BillingCancellationTransBean" property="strBillDtl" filter="false"/>
</div>
 
<div id="combo" style="display:block">
 
 <table class='TABLEWIDTH' align="center" cellpadding="1px" cellspacing="1px">
 <tr> 
    <td width="25%" class="LABEL"><font color="red">*</font>Cancelled By</td>
    <td colspan="3" class="CONTROL"><select name="strCancelledBy"  class="comboBig" >
    										<bean:write name="BillingCancellationTransBean" property="strCancelledBy" filter="false"/>
    							 </select></td> 
 
  
   
  </tr>
  <tr> 
    <td width="25%" class="LABEL"><font color="red">*</font>Cancellation Reason</td>
    <td  class="CONTROL" colspan="3"><select name="strCancelReason" class="comboNormal" onchange="showText();"> 
    									<bean:write name="BillingCancellationTransBean" property="strCancelReason" filter="false"/>
    								</select>            
    							 <input type="text" class="txtFldMax" name="strOtherReason" value="" maxlength="50" onkeypress="" ></td> 
    
   
    
   </table>
</div>  		
 

	
<table class='TABLEWIDTH' align="center" cellpadding="1px" cellspacing="1px">
<tr class="FOOTER"> 
    <td colspan="4"><font size="2" color="red">*</font> Mandatory Fields</td>
</tr>
</table>

<div id="lastbuttons" style="display:block">	
<table border="0" class='TABLEWIDTH' align="center">
		<tr>
			<td align="center">
			<!-- <img style="cursor:pointer;cursor:hand" title="click here to save the selected data" src="../../hisglobal/images/btn-sv.png" onclick="return validate1();">
			<img style="cursor:pointer;cursor:hand" title="click here to clear the contents"  src="../../hisglobal/images/btn-clr.png" onclick="showFirstPage2();" >
			<img style="cursor:pointer;cursor:hand" title="click here to cancel the process" src="../../hisglobal/images/btn-ccl.png" onclick="initPage();" >
			 -->
			<br>
			<a href="#" class="button" id="" onclick=' return validate1();'><span class="save">Save</span></a>
							<a href="#" class="button"	onclick="showFirstPage2();"><span class="clear">Clear</span></a> 
							<a href="#" class="button" onclick="initPage();"><span class="cancel">Cancel</span></a>
			</td>
		</tr>
</table>
</div>

<div id="onlyClearbutton" style="display:none">	
<table border="0" class=' TABLEWIDTH' align="center" cellpadding="1px" cellspacing="1px">
		<tr>
			<td align="center">
			<!-- <img style="cursor:pointer;cursor:hand" title="click here to clear the contents"  src="../../hisglobal/images/btn-clr.png" onclick="showFirstPage();" >
			<img style="cursor:pointer;cursor:hand" title="click here to cancel the process" src="../../hisglobal/images/btn-ccl.png" onclick="initPage();" >
			 -->
			 <br>
			<a href="#" class="button"	onclick="showFirstPage();"><span class="clear">Clear</span></a> 
							<a href="#" class="button" onclick="initPage();"><span class="cancel">Cancel</span></a>
			</td>
		</tr>
</table>
</div>
		
		<input type="hidden" name="hmode"/>
		<input type="hidden" name="GrntrName" value="${BillingCancellationTransBean.strGuarantorName }"/>	
		<input type="hidden" name="userJsFunctionName" value="${BillingCancellationTransBean.strBillUsrFuncName }" />
		<input type="hidden" name="chkValue"/>
		<input type="hidden" name="strCRNoSatus" value="${BillingCancellationTransBean.strCRNoSatus }"/>
		
		</html:form>
		
		<div class="popup" id="popup" style="display: none">  

</div>
<tag:autoIndex></tag:autoIndex> 
</body>
</html>