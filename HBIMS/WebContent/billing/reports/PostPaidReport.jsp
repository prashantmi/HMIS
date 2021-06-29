<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<html>
<head>
<title>Post-Paid Account Report</title>
<meta charset=utf-8>
</head>

<body>

   <table width="90%" border="0">
  <tr>
    <td><u><strong>Post-Paid</strong></u></td>
  </tr>
</table>
<table width="90%" border="0">
  <tr>
    <td width="19%">&nbsp;</td>
    <td width="64%">&nbsp;</td>
    <td width="12%"><strong>Invoice No:</strong></td>
    <td width="5%"> <bean:write name="clientverificTransBean" property="strInvoiceNo" filter="false"/></td>
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
    <td width="34%">Sir,</td>
    <td width="66%">&nbsp;</td>
  </tr>
  <tr>
    <td>Please find the expense details for the patient:</td>
    <td>&nbsp;</td>
  </tr>
</table>
<p></p>

<table width="90%" border="0">
  <tr>
    <td><strong><u>Patient Details</u></strong></td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td width="16%"><strong>CR No:</strong></td>
     <td width="84%"><bean:write name="clientverificTransBean" property="strCrNo" filter="false"/></td>
  </tr>
</table>

<table width="90%" border="0">
  <tr>
    <td width="16%"><strong>Card No:</strong></td>
    <td width="17%"><bean:write name="clientverificTransBean" property="strCardNo" filter="false"/></td>
    <td width="21%"><strong>Card Holder Name:</strong></td>
     <td width="17%"><bean:write name="clientverificTransBean" property="strCardHolderName" filter="false"/></td>
    <td width="11%">&nbsp;</td>
    <td width="18%">&nbsp;</td>
  </tr>
  <tr>
    <td><strong>Approved For:</strong></td> 
    <td><bean:write name="clientverificTransBean" property="strApprovedFor" filter="false"/></td>
    <td><strong>Auth No:</strong></td>
    <td><bean:write name="clientverificTransBean" property="strAutNo" filter="false"/></td>
    <td><strong>Auth Date:</strong></td>
    <td><bean:write name="clientverificTransBean" property="strAuthDate" filter="false"/></td>
  </tr>
  <tr>
    <td><strong>Sac Amt:</strong></td>
    <td><bean:write name="clientverificTransBean" property="strSancAmt" filter="false"/></td>
    <td><strong>Received Amt:</strong></td>
    <td><bean:write name="clientverificTransBean" property="strRecAmt" filter="false"/></td>
    <td><strong>Expense Amt:</strong></td>
    <td><bean:write name="clientverificTransBean" property="strExpenseAmt" filter="false"/></td>
  </tr>
</table>
<p></p>
<table width="90%" border="0">
  <tr>
    <td><strong><u>Expense Details</u></strong></td>
  </tr>
</table>
<p></p>
<table width="90%" border="0">
  <tr>
    <td><div align="center"><strong>Bill No</strong></div></td>
    <td><div align="center"><strong>Bill Date</strong></div></td>
    <td><div align="center"><strong>Service</strong></div></td>
    <td><div align="center"><strong>Bill Amount</strong></div></td>
  </tr>
  <tr>
    <td><div align="center"><bean:write name="clientverificTransBean" property="strBillNo" filter="false"/></div></td>
    <td><div align="center"><bean:write name="clientverificTransBean" property="strBillDate" filter="false"/></div></td>
    <td><div align="center"><bean:write name="clientverificTransBean" property="strService" filter="false"/></div></td>
    <td><div align="center"><bean:write name="clientverificTransBean" property="strBillAmt" filter="false"/></div></td>
  </tr>
</table>
<p></p>
<table width="90%" border="1">
  <tr>
    <td width=77%><strong><u>Total Invoice Amount:</u></strong></td>
    <td width="23%"><bean:write name="clientverificTransBean" property="strInvoiceAmt" filter="false"/></td>
  </tr>
</table>


</body>
</html>
