<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>
<%@taglib uri="/WEB-INF/dateTag.tld" prefix="date"%>
<%@taglib uri="/WEB-INF/patientDtl.tld" prefix="pDtl"%>

<html>
<head>
<meta charset=utf-8><title>Client Approval Details</title>

<link href="../css/transaction.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/calendar-tas.css" rel="stylesheet" type="text/css">


<script language="JavaScript" src="../../hisglobal/transactionutil/js/master.js"></script>
<script language="JavaScript" src="../../hisglobal/js/tab.js"></script>
<script language="JavaScript" src="../../hisglobal/js/calendar.js"></script>
<script language="JavaScript" src="../../hisglobal/js/util.js"></script>
<script language="JavaScript" src="../../hisglobal/js/validation.js"></script>
<script language="JavaScript" src="../../hisglobal/js/multirow.js"></script>

<script language="Javascript" src="../js/clientapproval_trans.js"></script>

</head>

<body>
<html:form action="/transactions/ClientApprovalDetailsTransCNT.cnt"  method="post">
<tag:tab tabLabel="Re-Approve Client Approval Details" selectedTab="FIRST" align="center" width="TABLEWIDTH"></tag:tab>
	
		<table class="TABLEWIDTH" align="center">
  <tr class="HEADER"> 
    <td colspan="4">Client Approval Details &gt;&gt;Re-Fund</td>
  </tr>
 
  <tr> 
    <td width="25%" class="LABEL">CR No.</td>
    <td colspan="3" class="CONTROL"><bean:write name="clientApprovalDetailTransBean" property="strCrNo"/> 
    </td>   
  </tr>
 
 <pDtl:patDtl crNo="${clientApprovalDetailTransBean.strCrNo}" address="false"></pDtl:patDtl>
 
 <tr> 
    <td width="25%" class="LABEL">Client Name</td>
    <td width="25%" class="CONTROL"><bean:write name="clientApprovalDetailTransBean" property="strClientName"/></td>   
    <td width="25%" class="LABEL">Address </td>
    <td width="25%" class="CONTROL"> <bean:write name="clientApprovalDetailTransBean" property="strClientAddress"/></td>   
   </tr>
  <tr> 
    <td width="25%" class="LABEL">Authentication  No. </td>
    <td width="25%" class="CONTROL"><bean:write name="clientApprovalDetailTransBean" property="strAuthenticationNumber"/></td>   
    <td width="25%" class="LABEL">Authentication  Date</td>
    <td width="25%" class="CONTROL"> <bean:write name="clientApprovalDetailTransBean" property="strAuthenticationDate"/> </td>   
   </tr>
   <tr> 
    <td width="25%" class="LABEL">Total Sanction Amount</td>
    <td width="25%" class="CONTROL"><input type="text" name="strSanctionAmount" value="${clientApprovalDetailTransBean.strSanctionAmount }" style="background-color: #EBEFFA; border-right-style: none; border-bottom-style: none; border-left-style: none; border-top-style: none"></td>   
    <td width="25%" class="LABEL">Expense Amount</td>
    <td width="25%" class="CONTROL"><input type="text" name="strClientExpenseAmount" value="${clientApprovalDetailTransBean.strClientExpenseAmount }" style="background-color: #EBEFFA; border-right-style: none; border-bottom-style: none; border-left-style: none; border-top-style: none"></td>   
   </tr>
   <tr> 
    <td width="25%" class="LABEL">Received Amount from Client</td>
    <td width="25%" class="CONTROL"><input type="text" name="strAmountReceivedFromClient" value="${clientApprovalDetailTransBean.strAmountReceivedFromClient }" style="background-color: #EBEFFA; border-right-style: none; border-bottom-style: none; border-left-style: none; border-top-style: none"></td>   
    <td width="25%" class="LABEL">Received Amount from Patient</td>
    <td width="25%" class="CONTROL"><input type="text" name="strAmountReceivedFromPatient" value="${clientApprovalDetailTransBean.strAmountReceivedFromPatient }" style="background-color: #EBEFFA; border-right-style: none; border-bottom-style: none; border-left-style: none; border-top-style: none"></td>   
   </tr>
    <tr> 
    <td width="25%" class="LABEL">Refund Amount</td>
    <td width="25%" class="CONTROL"><input type="text" name="strRefundAmount" value="${clientApprovalDetailTransBean.strRefundAmount}" style="background-color: #EBEFFA; border-right-style: none; border-bottom-style: none; border-left-style: none; border-top-style: none"></td>   
    <td width="25%" class="LABEL">Payment Mode</td>
    <td width="25%" class="CONTROL"><select name="strPaymentMode" class="comboNormal" onChange="getPaymentModeDtls(this);">
    <option value="0">Select Value</option>
    <option value="1">Cash</option>
    <option value="2">Cheque</option>
    <option value="3">Credit Card</option>
    <option value="4">Debit Card</option>
    <option value="5">DD</option>
    </select> </td>  
   </tr>
  <tr>
  <td colspan="4">
  <div id="paymentModeId"></div>
  </td>
  </tr>
   <tr> 
    <td colspan="2" width="50%" class="LABEL">Remarks</td>
    <td colspan="2" width="50%" class="CONTROL"><textarea name="strRemarks" rows="2" cols="25"></textarea>  
       
     </td>   
   
   </tr>
    <tr class="FOOTER"> 
    <td colspan="4"><font size="2" color="red">*</font> Mandatory Fields</td>
  </tr>
 </table>
 <table border="0" class="TABLEWIDTH" align="center">
		<tr>

			<td align="right"><img  src="../../hisglobal/images/btn-sv.png" onClick=""></td>
			<td align="left"><img  src="../../hisglobal/images/btn-ccl.png" onClick="add('LIST');"></td>
		</tr>
	</table>
 <input type="hidden" name="hmode">
</html:form>
<tag:autoIndex></tag:autoIndex> 
</body>
</html>