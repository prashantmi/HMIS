<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/dateTag.tld" prefix="date"%>
<%@taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>

<html>
<head><meta charset=utf-8>
<title>Client Verification Modify Page</title>
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
function ftn1()
{     
   document.getElementById("id1").style.display="block";
}
  
</script>

</head>
<html:form action="billing/transactions/ClientVerificTransCNT.cnt"  method="post">
 <tag:tab tabLabel="Client Verification" selectedTab="FIRST" align="center" width="75%">
	</tag:tab>


 <table class="TABLEWIDTH" align="center">
  <tr class="HEADER"> 
    <td colspan="4">Client Verification &gt;&gt;Modify</td>
  </tr>
  <tr> 
    <td width="25%" class="LABEL">Client Name:</td>
    <td width="25%" class="CONTROL"><bean:write name="clientverificTransBean" property="strClientType"/></td>   
    <td width="25%" class="LABEL">To Get Other Details:</td>
    <td width="25%" class="LABEL"><input type="button" name="OtherDtlBtn" style="color:red" value="Go" onClick="ftn1()"></td>   
   </tr>
  <tr> 
    <td width="25%" class="LABEL">Client Type:</td>
    <td width="25%" class="CONTROL"><bean:write name="clientverificTransBean" property="strClientType"/></td>   
    <td width="25%" class="LABEL">Payment Type:</td>
    <td width="25%" class="CONTROL"><bean:write name="clientverificTransBean" property="strPaymentType"/></td>   
   </tr>
   <tr>
    <td width="25%" class="LABEL">Reg No:</td>
    <td width="25%" class="CONTROL"><bean:write name="clientverificTransBean" property="strRegNo"/></td> 
    <td width="25%" class="LABEL"><font color="red">*</font>Address:</td>
    <td width="25%" class="CONTROL"> 
    <bean:write name="clientverificTransBean" property="strAddress"/></td>   
  </tr>
  <tr>
    <td width="25%" class="LABEL">Contact Person:</td>
    <td width="25%" class="CONTROL"><bean:write name="clientverificTransBean" property="strRegNo"/></td> 
    <td width="25%" class="LABEL">Contact No:</td>
    <td width="25%" class="CONTROL"><font color="red">
    <bean:write name="clientverificTransBean" property="strAddress"/></font> </td>   
  </tr>
  
  <tr> 
    <td colspan="4" class="TITLE">Client Covers</td>
   </tr>
    </table>
       
    
    
  <table class= "TABLEWIDTH"  align="center">
	 <tr>
		<td width="32%" class="LABEL" ><input type="checkbox" name="isOPD" value="1" >OPD Covers</td>
		<td width="32%" class="LABEL" ><input type="checkbox" name="isIPD" value="1" >IPD Covers</td>
		<td width="32%" class="LABEL" ><input type="checkbox" name="isEME" value="1" >Emergency Covers</td>
	 </tr>
  </table>
  
  
   <table class= "TABLEWIDTH"  align="center">
    <tr>
    <td width="25%" class="LABEL">Total Deposit Amt:</td>
    <td width="25%" class="CONTROL"><bean:write name="clientverificTransBean" property="strRegNo"/></td> 
    <td width="25%" class="LABEL">Total Expense:</td>
    <td width="25%" class="CONTROL"><bean:write name="clientverificTransBean" property="strAddress"/></td>   
  </tr>
    <tr>
    <td width="25%" class="LABEL">Remained Amt:</td>
    <td width="25%" class="CONTROL"><bean:write name="clientverificTransBean" property="strRegNo"/></td> 
    <td width="25%" class="LABEL"></td>
    <td width="25%" class="CONTROL"></td>   
  </tr>
   </table> 
  
  <div id = "id1" style="display:none;">
  <table class="TABLEWIDTH" align="center">
  <tr> 
    <td colspan="4" class="TITLE">Other Details</td>
   </tr>
    <tr> 
    <td colspan="4" class="TITLE"><font color="red">In Case of Pre-Paid</font></td>
   </tr>
   <tr> 
    <td width="25%" class="LABEL">Deposit Amount:</td>
    <td width="25%" class="CONTROL"><bean:write name="clientverificTransBean" property="strBgAmount"/></td>   
    <td width="25%" class="LABEL">Payment Mode:</td>
    <td width="25%" class="CONTROL"><bean:write name="clientverificTransBean" property="strRenewalDate"/></td>   
   </tr>
  <tr> 
    <td width="25%" class="LABEL">PyamentMode Details:</td>
    <td width="25%" class="CONTROL"><bean:write name="clientverificTransBean" property="strBankName"/></td>   
    <td width="25%" class="LABEL">Re-Order Level:</td>
    <td width="25%" class="CONTROL"><bean:write name="clientverificTransBean" property="strBankAddress"/></td>   
   </tr>
   <tr> 
    <td width="25%" class="LABEL">CR Billing Allow(Y/N):</td>
    <td width="25%" class="CONTROL"><select name="strWard"><bean:write name="clientverificTransBean" property="strWardValues" filter="false"/></select></td>   
    <td width="25%" class="LABEL"></td>
    <td width="25%" class="CONTROL"></td>   
   </tr>
   
   </table>
  </div>
  <table class="TABLEWIDTH" align="center">
  <tr> 
    <td colspan="4" class="TITLE">Bank Guarntee Details</td>
   
  </tr>
  <tr> 
    <td width="25%" class="LABEL">BG Amount</td>
    <td width="25%" class="CONTROL"><bean:write name="clientverificTransBean" property="strBgAmount"/></td>   
    <td width="25%" class="LABEL">Renewal Date</td>
    <td width="25%" class="CONTROL"><bean:write name="clientverificTransBean" property="strRenewalDate"/></td>   
   </tr>
  <tr> 
    <td width="25%" class="LABEL">Bank Name</td>
    <td width="25%" class="CONTROL"><bean:write name="clientverificTransBean" property="strBankName"/></td>   
    <td width="25%" class="LABEL">Bank Address</td>
    <td width="25%" class="CONTROL"><bean:write name="clientverificTransBean" property="strBankAddress"/></td>   
   </tr>
   
   </table>
   
    <table class="TABLEWIDTH" border="0" align="center" >
      <tr> 
        <td colspan="3" class="TITLE">Discount By Hospital(%)  
       </td>
     </tr>
	 <tr>
		<td width="33%" class="LABEL" align=left>OPD</td>
		<td width="33%" class="LABEL" align="center">IPD</td>
		<td width="33%" class="LABEL" align="center">Emergency Covers</td>
	 </tr>
	 <tr>
		<td width="33%" class="LABEL" align="center"><input type="text" style="color:red" name="strOPDDiscount" value="1" ></td>
		<td width="33%" class="LABEL" align="center"><input type="text" name="strOPDDiscount" value="" ></td>
		<td width="33%" class="LABEL" align="center"><input type="text" name="strOPDDiscount" value="" ></td>
	 </tr>
    </table>
   
   <table class="TABLEWIDTH" border="0" align="center" >
   <tr> 
    <td width="25%" class="LABEL"><font color="red">*</font>Effective From Date</td>
    <td class="CONTROL"><date:date name="strEffectiveFrom"
				value="${clientverificTransBean.strEffectiveFrom }"></date:date></td> 
    <td width="25%" class="LABEL"><font color="red">*</font>Expiry Date</td>
    <td class="CONTROL"><date:date name="strEffectiveFrom" value=""></date:date></td>   
  </tr>
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
<tag:autoIndex></tag:autoIndex> 
</html>