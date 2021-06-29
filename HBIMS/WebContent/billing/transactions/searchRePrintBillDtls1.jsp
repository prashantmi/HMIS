<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>
<%@taglib uri="/WEB-INF/dateTag.tld" prefix="date"%>
<%@taglib uri="/WEB-INF/patientDtl.tld" prefix="pDtl"%>
<%@ taglib uri="/WEB-INF/crNo.tld" prefix="crNo"%>

<html>
<head>
<meta charset=utf-8>
<title>Bill Details</title>

<link href="../css/transaction.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/calendar-tas.css" rel="stylesheet"type="text/css">


<script language="JavaScript" src="../../hisglobal/js/validation.js"></script>
<script language="Javascript" src="../js/BillRePrint_Trans.js"></script>
<script language="Javascript" src="../../hisglobal/js/util.js"></script>
<script language="JavaScript" src="../../hisglobal/js/calendar.js"></script>


</head>
<body onload="resetFormValues(),fetchBillList2('1','1');" > 

<html:form action="transactions/BillRePrintTransCNT.cnt" method="post" onsubmit="return popUpGo();">

    <div id='errMsg' class="errMsg"><bean:write name="BillRePrintTransBean" property="strErrMsg" filter="false"/></div>
    <div id='normalMsg' class="normalMsg"><bean:write name="BillRePrintTransBean" property="strMsg" filter="false"/></div>



 <table width='100%'align='center' cellspacing='1px'>
   <tr class="HEADER"> 
    <td colspan="4">Bill Search &gt;&gt; </td>
   </tr>
  <tr class="HEADER" style="display: none;"> 
  <td colspan="4">
    <html:radio property="strCase" name="BillRePrintTransBean" value="1" onclick="chkCase(this);">With CR No.</html:radio>
    <html:radio property="strCase" name="BillRePrintTransBean" value="2" onclick="chkCase(this);">Without CR No.</html:radio>
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
  <td class="CONTROL"> <date:date name="strFromDate" id="fromDateId"  value="${BillRePrintTransBean.strCtDate}"/> </td> 
  <td class="LABEL"> <font color="red">*</font><B>To Date </B></td>
  <td class="CONTROL">  <date:date name="strToDate" id="toDateId"  value="${BillRePrintTransBean.strCtDate}"/> 
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
<table width='100%'align='center' cellspacing='1px'>
		<tr>
			<td align="center"><img  style="cursor:pointer;cursor:hand" title="Go to Main Page" src="../../hisglobal/images/ok.gif" onClick="validate('document.forms[0].strCrNo');"><img style="cursor:pointer;cursor:hand" title="To Clear Field's" src="../../hisglobal/images/btn-clr.png" onclick="clearCrNo();" ><img style="cursor:pointer;cursor:hand" title="Cancel All Opn" src="../../hisglobal/images/btn-ccl.png" onclick="window.opener.closePopUp();" ></td>
			
		</tr>
</table>
</div>

<input type="hidden" name="hmode"/>
<input type="hidden" name="ctDate" value="${BillRePrintTransBean.strCtDate }"/>


</html:form>

</body>
</html>