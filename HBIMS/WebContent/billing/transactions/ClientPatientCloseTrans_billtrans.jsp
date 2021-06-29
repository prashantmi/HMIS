
<!DOCTYPE HTML>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/dateTag.tld" prefix="date"%>
<%@taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>

<html>
<head>
<meta charset=utf-8>
<title>Client Close Page</title>
<link href="../css/master.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet"
	type="text/css">
<link href="../../hisglobal/css/calendar-tas.css" rel="stylesheet"
	type="text/css">
<script language="Javascript" src="../../hisglobal/js/datepicker1.js"></script>
<script language="Javascript" src="../../hisglobal/js/validation.js"></script>
<script language="JavaScript" src="../../masterutil/js/master.js"></script>
<script language="JavaScript" src="../../hisglobal/js/tab.js"></script>
<script language="JavaScript" src="../../hisglobal/js/calendar.js"></script>
<script language="Javascript" src="../../hisglobal/js/multirow.js"></script>
<script language="Javascript" src="../../hisglobal/js/datepicker1.js"></script>
<script language="Javascript" src="../../hisglobal/js/validation.js"></script>
<script language="Javascript" src="../../hisglobal/js/util.js"></script>
<script type="text/javascript">
 
</script>

</head>
<html:form action="billing/transactions/ClientVerificTransCNT.cnt"  method="post">
 <tag:tab tabLabel="Client Verification" selectedTab="FIRST" align="center" width="TABLEWIDTH">
	</tag:tab>


 <table class="TABLEWIDTH" align="center">
  <tr class="HEADER"> 
    <td colspan="4">Client\Patient&gt;&gt;Close</td>
  </tr>
  <tr> 
    <td width="25%" class="LABEL">CR No:</td>
    <td width="25%" class="CONTROL"><input name="strCrNo"></td>   
    <td width="25%" class="LABEL">To Get Other Details:</td>
    <td width="25%" class="LABEL"><input type="button" name="OtherDtlBtn" style="color:red" value="Go" onClick="ftn1()"></td>   
   </tr>
  <tr> 
    <td width="25%" class="LABEL">Patient Name:</td>
    <td width="25%" class="CONTROL"><bean:write name="clientverificTransBean" property="strClientType"/></td>   
    <td width="25%" class="LABEL">Father\Husband Name:</td>
    <td width="25%" class="CONTROL"><bean:write name="clientverificTransBean" property="strPaymentType"/></td>   
   </tr>
   <tr>
    <td width="25%" class="LABEL">Age\Sex:</td>
    <td width="25%" class="CONTROL"><bean:write name="clientverificTransBean" property="strRegNo"/></td> 
    <td width="25%" class="LABEL"><font color="red">*</font>Primary Category:</td>
    <td width="25%" class="CONTROL"> 
    <bean:write name="clientverificTransBean" property="strAddress"/></td>   
  </tr>
 
  <tr>
    <td width="25%" class="LABEL">Addm No:</td>
    <td width="25%" class="CONTROL"><bean:write name="clientverificTransBean" property="strAddmNo"/></td> 
    <td width="25%" class="LABEL">Addm Date:</td>
    <td width="25%" class="CONTROL"><font color="red">
    <bean:write name="clientverificTransBean" property="strAddmDate"/></font> </td>   
  </tr>

  <tr>
    <td width="25%" class="LABEL">Client Name:</td>
    <td width="25%" class="CONTROL"><bean:write name="clientverificTransBean" property="strAddress"/></td> 
    <td width="25%" class="LABEL">Address:</td>
    <td width="25%" class="CONTROL"><font color="red">
    <bean:write name="clientverificTransBean" property="strAddress"/></font> </td>   
  </tr>
  <tr> 
    <td width="25%" class="LABEL">Auth.No:</td>
    <td width="25%" class="CONTROL"><input name="strAuthNo"></td>   
    <td width="25%" class="LABEL">Auth Date:</td>
    <td width="25%" class="CONTROL"><input name="strAuthDate"></td>   
   </tr>
   <tr> 
    <td width="25%" class="LABEL">Total Sanction Amt:</td>
    <td width="25%" class="CONTROL"><input name="strSanctionAmt"></td>   
    <td width="25%" class="LABEL">Expense Amt</td>
    <td width="25%" class="CONTROL"><input name="strExpenseAmt"></td>   
   </tr>
   <tr> 
    <td width="25%" class="LABEL">Recd Amt from Client:</td>
    <td width="25%" class="CONTROL"><input name="strRecAmtfrmClient"></td>   
    <td width="25%" class="LABEL">Recd Amt from Patient:</td>
    <td width="25%" class="CONTROL"><input name="strRecAmtfrmPatient"></td>   
   </tr>
  </table>
  <table class="TABLEWIDTH" border="0" align="center" >
   <tr>
     <td colspan="2" width="50%" class="LABEL">Remarks</td>
    <td colspan="2" width="50%" class="CONTROL"> <textarea rows="2" cols="20" name="strRemarks"></textarea></td> 
  </tr>
   <tr class="FOOTER"> 
   <td colspan="4"><font size="2" color="red">*</font> Mandatory Fields</td>
  </tr>
</table>
		
		<table border="0" class="TABLEWIDTH" align="center">
		<tr>

			<td align="right"><img name="save"   src="../../hisglobal/images/btn-sv.png" onclick=" return validate1();"></td>
			<td align="left"><img name="cancel"   src="../../hisglobal/images/btn-ccl.png" onclick="cancel('LIST');"></td>
		</tr>
	</table>

	<input type="hidden" name="hmode">
</html:form>
<jsp:include page="multirow_ClientApprovalTrans_billtrans.jsp"></jsp:include>
<tag:autoIndex></tag:autoIndex>
</html>