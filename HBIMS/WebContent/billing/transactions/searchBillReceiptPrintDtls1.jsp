<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>
<%@taglib uri="/WEB-INF/dateTag.tld" prefix="date"%>
<%@taglib uri="/WEB-INF/patientDtl.tld" prefix="pDtl"%>
<%@ taglib uri="/WEB-INF/crNo.tld" prefix="crNo"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Bill Details-2</title>

<link href="../css/transaction.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/buttons.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/calendar-tas.css" rel="stylesheet"type="text/css">


<script language="JavaScript" src="../../hisglobal/js/validation.js"></script>
<script language="Javascript" src="../js/BillDupPrint_Trans.js"></script>
<script language="Javascript" src="../../hisglobal/js/util.js"></script>
<script language="JavaScript" src="../../hisglobal/js/calendar.js"></script>

<tag:tab onlyTabIndexing="1"></tag:tab>

</head>
<body onload="fetchBillList2('1','1');" > 

<html:form action="transactions/BillDupPrintTransCNT.cnt" method="post" onsubmit="return popUpGo();">

    <div id='errMsg' class="errMsg"><bean:write name="BillDupPrintTransBean" property="strErrMsg" filter="false"/></div>
    <div id='normalMsg' class="normalMsg"><bean:write name="BillDupPrintTransBean" property="strMsg" filter="false"/></div>



 <table width='100%'align='center' cellspacing='1px'>
   <tr class="HEADER"> 
    <td colspan="4">Bill Search &gt;&gt; </td>
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
   	<option value="1">CR. No.</option>
   	<option value="2">Patient Name</option>
   	</select>
	&nbsp; <input type="text" name="strSearchString" class="txtFldMax" maxlength="100" onkeypress="return validateDataWithSpecialChars(event,9,'%.');">
   </td>   
    <tr>
  <td class="LABEL"> <font color="red">*</font><B>From Date </B></td>
  <td class="CONTROL"> <date:date name="strFromDate" id="fromDateId"  value="${BillDupPrintTransBean.strCtDate}"/> </td> 
  <td class="LABEL"> <font color="red">*</font><B>To Date </B></td>
  <td class="CONTROL">  <date:date name="strToDate" id="toDateId"  value="${BillDupPrintTransBean.strCtDate}"/> 
   <img  style="cursor:pointer;cursor:hand" title="Get Bill Dtl with CR No" src="../../hisglobal/images/Go.png" align="top"
 		 name="go" onclick="getSearchBillListBySearch('strCrNo');"
 		 onKeyPress="getSearchBillListBySearch('strCrNo');"/> </td>
  
  </tr>
</table> 
 
 <div id="billdtls" >
	
</div>

<table width='100%'align='center' cellspacing='1px'>
<tr class="FOOTER"> 
    <td colspan="4"><font size="2" color="red">*</font> Mandatory Fields</td>
</tr>
</table>

<div id="lastbuttons" style="display:block">	
<table border="0" class="TABLEWIDTH" align="center">
		<tr align='center'>
			<td>
			<!-- <img  style="cursor:pointer;cursor:hand" title="Go to Main Page" src="../../hisglobal/images/ok.gif" onClick="validate('document.forms[0].strCrNo');">
			<img style="cursor:pointer;cursor:hand" title="To Clear Field's" src="../../hisglobal/images/btn-clr.png" onclick="clearCrNo();" >
			<img style="cursor:pointer;cursor:hand" title="Cancel All Opn" src="../../hisglobal/images/btn-ccl.png" onclick="window.opener.closePopUp();" >
			 -->
			<br><a href="#" class="button" id="" onClick="validate('document.forms[0].strCrNo');" ><span class="ok">Ok</span></a>
							<a href="#" class="button"	onclick="clearCrNo();"><span class="clear">Clear</span></a> 
							<a href="#" class="button" onclick="window.opener.closePopUp();"><span class="cancel">Cancel</span></a>
			</td>
			
		</tr>
</table>
</div>

<input type="hidden" name="hmode"/>
<input type="hidden" name="ctDate" value="${BillDupPrintTransBean.strCtDate }"/>


</html:form>
<tag:autoIndex></tag:autoIndex>  
</body>
</html>