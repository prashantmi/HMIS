<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="/WEB-INF/dateTag.tld" prefix="date"%>
<html>
<head>
<meta charset=utf-8>
<title>Report InterFace</title>
<link href="../css/transaction.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/cr-no-format.css" rel="stylesheet"
	type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet"
	type="text/css">
<link href="../../hisglobal/css/calendar-tas.css" rel="stylesheet"
	type="text/css">
<link href="../../hisglobal/css/popup.css" rel="stylesheet"
	type="text/css">
<script language="JavaScript" src="../../hisglobal/js/calendar.js"></script>
<script language="Javascript" src="../../hisglobal/js/datepicker1.js"></script>
</head>
<body>
<table class="TABLEWIDTH" align="center" cellspacing="0px">
<tr class="HEADER">
<td colspan="4"><b><u>Report Interface</u></b>
</td>
</tr>
</table>
<table class="TABLEWIDTH" align="center" cellspacing="0px">
<tr>
<td>
<table width="100%" align="center">
  <tr> 
    <td class="LABEL" colspan="1">From Date</td>
    <td class="CONTROL" colspan="1"><date:date name="fromdate" value="${visitorpassTransBean.strCtDate}"></date:date></td>
    <td class="LABEL" colspan="1">To Date</td>
    <td class="CONTROL" colspan="1"><date:date name="todate" value="${visitorpassTransBean.strCtDate}"></date:date></td>
  </tr>
  <tr>
      <td class="LABEL" colspan="1">Admission Status</td>
      <td class="CONTROL" colspan="1">
          <select name="admn">
		<option value="0">Select Value</option>
		</select>
      </td>
      <td class="LABEL" colspan="1">Discharge Status</td>
      <td class="CONTROL" colspan="1">
          <select name="dis">
		<option value="0">Select Value</option>
		</select>
      </td>
  </tr>
  <tr>
  <td class="LABEL" colspan="1">Department</td>
  <td class="CONTROL" colspan="1"><select name="deparment">
		<option value="0">Select Value</option>
		</select>
   </td>
   <td class="LABEL" colspan="1">Unit</td>
  <td class="CONTROL" colspan="1"><select name="unit">
		<option value="0">Select Value</option>
		</select>
   </td>
  </tr> 
  <tr>
      <td class="LABEL" colspan="1">Ward</td>
      <td class="CONTROL" colspan="3">
          <select name="ward">
		<option value="0">Select Value</option>
		</select>
     
      </td>
  </tr>
  
  </table></td></tr></table>
  <table class="TABLEWIDTH" align="center" cellspacing="0px">
	<tr class="FOOTER"> 
    <td colspan="4"><font size="2" color="red">*</font> Mandatory Fields</td>
  </tr>
  </table>
  <table class="TABLEWIDTH" align="center" cellspacing="0px">
	<tr>
		<td align="center">
		<img src="../../hisglobal/images/btn-generate.png">
		</td>
	</tr>
</table>
</body>
</html>