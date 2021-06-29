<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>
<html>
<head>
<title>Untitled Document</title>
<meta charset=utf-8>
</head>

<body>
 
   <table width="90%" border="0">
  <tr>
    <td><u><strong>Pre-Paid</strong></u></td>
  </tr>
</table>
<table width="90%" border="0">
  <tr>
    <td width="19%">&nbsp;</td>
    <td width="56%">&nbsp;</td>
    <td width="14%"><strong>Invoice No:</strong></td>
    <td width="11%"><bean:write name="clientverificTransBean" property="strInvoiceNo" filter="false"/></td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td><strong>Invoice Date:</strong></td>
    <td><bean:write name="clientverificTransBean" property="strInvoiceDate" filter="false"/></td>
  </tr>
  <tr>
    <td>To,</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td><strong><bean:write name="clientverificTransBean" property="strClientName" filter="false"/></strong></td>
     <td></td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td><strong><bean:write name="clientverificTransBean" property="strClientAdd" filter="false"/></strong></td>
    <td></td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  
</table>
<p></p>
<table width="90%" border="0">
  <tr>
    <td><div align="center"><strong><u>Subject: Invoice Letter</u></strong></div></td>
  </tr>
</table>
<p></p>
<table width="90%" border="0">
  <tr>
    <td width="347">Sir,</td>
    <td width="335">&nbsp;</td>
  </tr>
  <tr>
    <td>Please find the expense details for the following patient (s)</td>
    <td style="width: 51px"><bean:write name="clientverificTransBean" property="strCrNo" filter="false"/></td>
  </tr>
</table>
<p></p>
<table width="90%" border="0">
  <tr>
    <td><strong><u>Expense Details</u></strong></td>
  </tr>
</table><table width="90%" border="0">
  <tr>
    <td width="10%"><div align="center"><strong>CR No:</strong></div></td>
    <td width="21%"><div align="center"><strong>Patient Name:</strong></div></td>
    <td width="14%"><div align="center"><strong>Sanc Amt:</strong></div></td>
    <td width="19%"><div align="center"><strong>Expense Amt:</strong></div></td>
    <td width="16%"><div align="center"><strong>Recrd Amt:</strong></div></td>
    <td width="20%"><div align="center"><strong>Balance Amt:</strong></div></td>
  </tr>
  <tr>
    <td><div align="center">
      <bean:write name="clientverificTransBean" property="strCrNo" filter="false"/></div></td>
    <td><div align="center">
      <bean:write name="clientverificTransBean" property="strClientName" filter="false"/></div></td>
    <td><div align="center">
      <bean:write name="clientverificTransBean" property="strSancAmt" filter="false"/></div></td>
    <td><div align="center">
      <bean:write name="clientverificTransBean" property="strExpenseAmt" filter="false"/></div></td>
     <td><div align="center">
      <bean:write name="clientverificTransBean" property="strRecAmt" filter="false"/></div></td>
     <td><div align="center">
      <bean:write name="clientverificTransBean" property="strRecAmt" filter="false"/></div></td>
  </tr>
</table>
<p></p>
<table width="90%" border="1">
  <tr>
    <td width="77%"><strong><u>Total Invoice Amount:</u></strong></td>
    <td width="23%"><bean:write name="clientverificTransBean" property="strInvoiceAmt" filter="false"/></td>
  </tr>
</table>

<tag:autoIndex></tag:autoIndex>
</body>
</html>
