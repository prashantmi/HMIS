<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>
<%@taglib uri="/WEB-INF/dateTag.tld" prefix="date"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Bill Details</title>

<link href="../css/transaction.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/calendar-tas.css" rel="stylesheet"type="text/css">
<link href="../../hisglobal/css/buttons.css" rel="stylesheet" type="text/css">

<script language="Javascript" src="../../hisglobal/js/validation.js"></script>
<script language="Javascript" src="../../hisglobal/js/util.js"></script>
<script language="Javascript" src="../js/BillDupPrint_Trans.js"></script>
<script language="JavaScript" src="../../hisglobal/js/calendar.js"></script>


<tag:tab onlyTabIndexing="1"></tag:tab>



</head>
<body onload="fetchBillList('1','1');" >

<html:form action="transactions/BillDupPrintTransCNT.cnt" 
	
 	method="post" >
 	<div id = "errMsg" class="errMsg"><bean:write name="BillDupPrintTransBean" property="strErrMsg"/></div>
 	<div id = "normalMsg" class="normalMsg"></div>
<table width="100%" cellspacing="1px">
	<tr>
	  <td> 
		 <table width='100%' align ="center" cellspacing="1px">
			<tr class="HEADER">
				<td colspan="4">Bill Details</td>
			</tr>
		    <tr class="HEADER" style="display: none;"> 
 			  <td colspan="4">
    			<html:radio property="strCase" name="BillDupPrintTransBean" value="1" onclick="chkCase(this);">With CR No.</html:radio>
   				 <html:radio property="strCase" name="BillDupPrintTransBean" value="2" onclick="chkCase(this);">Without CR No.</html:radio>
   			 </td>
  			</tr>
		 <tr >
  <td width="25%" class="LABEL"><font color="red">*</font>Search Type</td>
   <td colspan="3" class="CONTROL"> 
   
   	<select name="strSearchType" class="comboNormal" >
   	<option value="1">Previous CR. No.</option>
   	<option value="2" selected="selected">Patient Name</option>
   	</select>
	&nbsp; <input type="text" name="strSearchString" class="txtFldMax" maxlength="100" onkeypress="return validateDataWithSpecialChars(event,9,'%.');">
   </td>   
    <tr>
		<tr>
  		<td class="LABEL"><b>
  		<font color="red">*</font>From Date: </b></td>
  		<td class="CONTROL"> <date:date name="strFromDate" id="fromDateId"  value="${BillDupPrintTransBean.strCtDate}"/> </td> 
  		<td class="LABEL"> <b><font color="red">*</font>To Date: </b></td>
 		<td class="CONTROL">  <date:date name="strToDate" id="toDateId"  value="${BillDupPrintTransBean.strCtDate}"/>   
 		 &nbsp;
 		 <img  src="../../hisglobal/images/Go.png"  name="go" onclick="getSearchBillListBySearch('strGuarantorName');"
 		 onKeyPress="getSearchBillListBySearch('strGuarantorName');"/> </td>
  		 </tr>
		</table>
		
		<div id="fetchRecordDivId"></div>
				
			
			<table width='100%'align='center' cellspacing='1px'>
                  <tr class="FOOTER"> 

				<td width='3%'><div id="plusHelpId" align="center"><img
						src="../../hisglobal/images/plus.gif" name="plusHelp"
						align="middle" 
						onclick="showHelpDetails('HelpId');" /></div>
					<div id="minusHelpId" style="display: none" align="center"><img
						src="../../hisglobal/images/minus.gif" name="minusHelp"
						
						onclick="hideHelpDetails('HelpId');"> </div></td>
			    		<td><div align="left" id="help">Help</div></td>
				</tr>
			</table>
			<div id="HelpId" style="display:none">
			<table class='TABLEWIDTH' align="center" cellspacing="1px">
			<tr >
			<td colspan="" class="CONTROL"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			 Use <b>%</b> to Find Any Data of Any Length (Including zero length)
			</td>
			</tr>
			<tr >
			<td class="CONTROL"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			Use <b>_</b> to Find Data on a Single Character Exclusion
			</td>
			</tr>
			<tr class=FOOTER>
			<td ></td>
			</tr>
			</table>
			</div>
			
		
		<table border="0" class='TABLEWIDTH' align="center">
				<tr>
<td align="center">
<!-- <img style=cursor:hand title="Go to Main Page"
						src="../../hisglobal/images/ok.gif" onClick="validate('document.forms[0].strGuarantorName')">
						
<img style=cursor:hand	title="To Clear All Fields" src="../../hisglobal/images/btn-clr.png" onClick="clearGrntrNo();">			
					
<img style=cursor:hand	title="To Close Window" src="../../hisglobal/images/btn-ccl.png" onClick="window.opener.closePopUp();">
 -->

<br><a href="#" class="button" id="" onClick="validate('document.forms[0].strGuarantorName')"><span class="ok">Ok</span></a>
							<a href="#" class="button"	 onClick="clearGrntrNo();"><span class="clear">Clear</span></a> 
							<a href="#" class="button" onClick="window.opener.closePopUp();"><span class="cancel">Cancel</span></a>
</td>
				</tr>
			</table>
					
		</td>
		</tr>
		</table>
		
		<input type="hidden" name="hmode"/>
		
		<input type="hidden" name="ctDate" value="${BillDupPrintTransBean.strCtDate }"/>
		<input type="hidden" name="userJsFunctionName" value="${BillDupPrintTransBean.strBillUsrFuncName }" />		
		</html:form>
		<tag:autoIndex></tag:autoIndex>  
</body>
</html>