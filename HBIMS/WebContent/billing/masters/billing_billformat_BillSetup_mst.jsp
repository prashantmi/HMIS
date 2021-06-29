<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>



<!--  
 
 										Developer : PAWAN KUMAR B N
 									    Created On: 19-09-2011
 										Module : Billing
 										Process: BILL SETUP Master
 										JSP URL : billing/masters/billing_billformat_BillSetup_mst.jsp

-->	





<html>
<link href="../css/master.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/buttons.css" rel="stylesheet" type="text/css">
	
<script language="JavaScript" src="../../masterutil/js/master.js"></script>
<script language="JavaScript" src="../../hisglobal/js/validation.js"></script>
<script language="JavaScript" src="../../hisglobal/js/tab.js"></script>
<script language="JavaScript" src="/HBIMS/hisglobal/js/util.js"></script>

	
	<head><meta charset=utf-8>
	<title>Bill Format</title>
	
	<script type="text/javascript"><!--
	
	function validate1(){	
	
		/*	var hisValidator = new HISValidator("billsetupbean");  
	 	hisValidator.addValidation("billFormatHeader1", "req", "Header1 is a Mandatory Field" );
	    var retVal = hisValidator.validate(); 
	    if(retVal){
	*/
				document.forms[0].selectedTab.value = "BILLFORMATINSERT";
				document.forms[0].submit();
	/*	}else{
		
				return false;
		}
	*/
	}

function initPage(){
	 	document.forms[0].selectedTab.value="INITIALPAGE";
		document.forms[0].submit();
	 }


	 
	/**
	 * showDetails
	 * @param {String} divId 
	 */
	 function showDetails(divId) {
	 	
	 	document.getElementById(divId).style.display="block";
	 	document.getElementById('minus'+divId).style.display="block";
		document.getElementById('plus'+divId).style.display="none";
	 	
	 }
	
	/**
	 * hideDetails
	 * @param {String} divId 
	 */
	 function hideDetails(divId) {
	 	
	 	document.getElementById(divId).style.display="none";
	 	document.getElementById('minus'+divId).style.display="none";
		document.getElementById('plus'+divId).style.display="block";
	 	
	 }
	
	 

	</script>
	
	</head>
	
	<body>
<html:form action="/masters/CNTBillSetup.cnt" method="post" >
	
	<div class="errMsg"><bean:write name="billsetupbean" property="strErr"/></div>
	<div class="normalMsg" id='normalMsg'><bean:write name="billsetupbean" property="strMsg"/></div>
	
	<tag:tab tabList="${billsetupbean.lhm}" selectedTab="billFormat" align="center" width="TABLEWIDTH"></tag:tab>
		
	<table align="center" class="TABLEWIDTH" cellpadding="1px" cellspacing="1px">
		<tr class="HEADER"><td  colspan="2" height="22"> Bill Format Screen </td></tr>
		
		<tr><td width="50%" class="LABEL">Header1</td>
			<td width="50%" class="CONTROL">
			<input type="text" name="billFormatHeader1" maxlength="100" class="txtFldBig" value="${billsetupbean.billFormatHeader1}">
				
			</td>
		</tr>
		<tr><td width="50%" class="LABEL">Header2</td>
			<td width="50%" class="CONTROL">
			<input type="text" name="billFormatHeader2" maxlength="100" class="txtFldBig" value="${billsetupbean.billFormatHeader2}">
				
			</td>
		</tr>
		<tr><td width="50%" class="LABEL">Header3</td>
			<td width="50%" class="CONTROL">
			<input type="text" name="billFormatHeader3" maxlength="100" class="txtFldBig" value="${billsetupbean.billFormatHeader3}">
				
			</td>
		</tr>
		<tr><td width="50%" class="LABEL">Header4</td>
			<td width="50%" class="CONTROL">
			<input type="text" name="billFormatHeader4" maxlength="100" class="txtFldBig" value="${billsetupbean.billFormatHeader4}">
				
			</td>
		</tr>
				
		<tr><td class="LABEL">Global Footer</td>
			<td class="CONTROL">
			<input type="text" name="billFormatFooter" maxlength="100" class="txtFldBig" value="${billsetupbean.billFormatFooter}">
			</td>
		</tr>
		
		<tr><td class="LABEL">Disclaimer (Without CR No.)</td>
			<td class="CONTROL">
			<input type="text" name="billDisclaimerWithoutCrNo" maxlength="100" class="txtFldBig" value="${billsetupbean.billDisclaimerWithoutCrNo}">
			</td>
		</tr>
		<tr><td class="LABEL">Disclaimer (Services or Tests)</td>
			<td class="CONTROL">
			<input type="text" name="billDisclaimerServices" maxlength="100" class="txtFldBig" value="${billsetupbean.billDisclaimerServices}">
			</td>
		</tr>
		<tr><td class="LABEL">Disclaimer (Refund)</td>
			<td class="CONTROL">
			<input type="text" name="billDisclaimerRefund" maxlength="100" class="txtFldBig" value="${billsetupbean.billDisclaimerRefund}">
			</td>
		</tr>
		<tr><td class="LABEL">Disclaimer (Advance)</td>
			<td class="CONTROL">
			<input type="text" name="billDisclaimerAdvance" maxlength="100" class="txtFldBig" value="${billsetupbean.billDisclaimerAdvance}">
			</td>
		</tr>
		<tr><td class="LABEL">Disclaimer (Part Payment)</td>
			<td class="CONTROL">
			<input type="text" name="billDisclaimerPartPayment" maxlength="100" class="txtFldBig" value="${billsetupbean.billDisclaimerPartPayment}">
			</td>
		</tr>
		<tr><td class="LABEL">Disclaimer (Final Bill)</td>
			<td class="CONTROL">
			<input type="text" name="billDisclaimerFinalBill" maxlength="100" class="txtFldBig" value="${billsetupbean.billDisclaimerFinalBill}">
			</td>
		</tr>
		<tr><td class="LABEL">Is Disclaimer (Duplicate Print) Required</td>
			<td class="CONTROL">
			
			<html:radio name="billsetupbean" property="billDisclaimerDuplicatePrintRequired" value="1">Yes</html:radio>
     <html:radio name="billsetupbean" property="billDisclaimerDuplicatePrintRequired" value="0">No</html:radio>
			</td>
		</tr>
		 
		  <tr><td class="LABEL">Day End Report (No. of Lines)</td>
			<td class="CONTROL">
			<input type="text" name="billLineOpdServices" maxlength="2" class="txtFldMin" value="${billsetupbean.billLineOpdServices}">
			</td>
		</tr>
    
		
		</table>
		
		   
	<table border="0" class="TABLEWIDTH" align="center" cellpadding="1px" cellspacing="1px">
		<tr class="FOOTER"><td colspan="2" height="22"></td></tr>
	</table>
		
		<table border="0" class="TABLEWIDTH" align="center" cellpadding="1px" cellspacing="1px"> 
		<tr>
             
			<!-- <td align="right">
			<img
				src="../../hisglobal/images/btn-sv.png" style="cursor:pointer;cursor:hand;" title="Save Record" onClick="validate1();" />
			
			</td>
			<td align="left">
			<img src="../../hisglobal/images/btn-ccl.png" style="cursor:pointer;cursor:hand;" title="Cancel the Process"
				onClick="cancelFunc();" />
			</td>
			-->
			<br>
			<td align="right">
				<a href="#" class="button" id="" onclick=' return validate1();'><span class="save">Save</span></a>		
			</td>
			<td align="left">
			<a href="#" class="button"	onclick="cancelFunc();"><span class="cancel">Cancel</span></a> 
		</tr>
	</table>

	<input 	type="hidden" name="selectedTab">
	
	</html:form>
	<tag:autoIndex></tag:autoIndex>
	</body>
	
	</html>