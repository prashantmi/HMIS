<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/dateTag.tld" prefix="date"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>
<%@ taglib uri="/WEB-INF/patientDtl.tld" prefix="pDtl"%>
<%@ taglib uri="/WEB-INF/onLineReqDiscountDtl.tld" prefix="DisDtl"%>
<%@ taglib uri="/WEB-INF/crNo.tld" prefix="crNo"%>
<html>
<head><meta charset=utf-8>
<title>Discount Approval</title>
<link href="../css/transaction.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/calendar-tas.css" rel="stylesheet" type="text/css">
	<link href="../../hisglobal/css/popup.css" rel="stylesheet"
	type="text/css">
	
<script language="Javascript" src="../../hisglobal/js/datepicker1.js"></script>
<script language="Javascript" src="../../hisglobal/js/validation.js"></script>
<script language="JavaScript" src="../../transUtil/js/master.js"></script>
<script language="JavaScript" src="../../hisglobal/js/tab.js"></script>
<script language="JavaScript" src="../../hisglobal/js/calendar.js"></script>
<script language="Javascript" src="../../hisglobal/js/multirow.js"></script>
<script language="Javascript" src="../../hisglobal/js/datepicker1.js"></script>
<script language="Javascript" src="../../hisglobal/js/validation.js"></script>
<script language="Javascript" src="../../hisglobal/js/util.js"></script>
<script language="Javascript" src="../../hisglobal/js/popup.js"></script>
<script language="Javascript" src="../../billing/js/billing.js"></script>
<script language="Javascript" src="../../billing/js/BillPrintTrans.js"></script>
<script language="Javascript" src="../js/patientListing.js"></script>
</head>
<body onLoad="document.forms[0].strCrNo.focus(),hlpOnLoad();" >
<html:form action="transactions/BillPrintTransCNT.cnt" method="post">

<div id ="errMsg" class="errMsg"><bean:write name="billPrintTransBean" property="strErrMsg"/></div> 
<div id ="normalMsg" class="normalMsg"><bean:write  name="billPrintTransBean" property="strMsg"/></div>

 <tag:tab tabLabel="Receipt Print" selectedTab="FIRST" align="center" width="TABLEWIDTH">
	</tag:tab>
 <table class="TABLEWIDTH" align="center" border="0" cellspacing ="1px">
  <tr class="HEADER"> 
    <td colspan="4">Receipt &gt;&gt;Print</td>
  </tr>
  
  <tr> 
    <td width="25%" class="LABEL"><font color="red">*</font>CR No.</td>
    <td colspan="3" class="CONTROL">
   <crNo:crNo
						 id="strCrNoId"
						value="${billPrintTransBean.strCrNo}"
						js="onkeypress='return initGoFunc(event);'"></crNo:crNo> 
   <input type="image" style="cursor:pointer;cursor:hand" title="Discount Details" align="top" src ="../../hisglobal/images/Go.png" name="go" value="Go" onclick="return goFunc();" onkeyup="goFuncOnEnter(event);">
  </td>   
  </tr>
  </table>
 
       <bean:write name="billPrintTransBean" property="strPatientDetailsView" filter="false"/>

 	<bean:write name="billPrintTransBean" property="strOnlineRequestDtls" filter="false"/>  
 
   <div id="billTypeDivId" style="display: none">
    <table class="TABLEWIDTH" align="center" border="0" cellspacing ="1px">
  <tr> 
    <td colspan="2" class="LABEL">Receipt Type</td>
    <td colspan="2" class="CONTROL"><select name="strBillType" class="comboNormal"><option value='1'>Consolidate</option><option value='2'>Detail</option> </select> </td>
  </tr>
  </table>	
   </div>
   
   <table class="TABLEWIDTH" align="center" border="0" cellspacing ="1px">
  <tr class="FOOTER"> 
    <td colspan="4"><font size="2" color="red">*</font> Mandatory Fields</td>
  </tr>
  </table>	

  <table border="0" class="TABLEWIDTH" align="center">
		<tr>

			
			<td align="center">
			<img src="../../hisglobal/images/print_tab.gif" style="cursor: pointer;cursor:hand" title="Print Record"   onclick="printBill();">
			 <img style="cursor: pointer;cursor:hand" src="../../hisglobal/images/btn-clr.png" title="click here to clear the contents"   
				name="clearImg" onclick="clearData();"/> <img style="cursor: pointer;cursor:hand" title="click here to cancel the process" 
				src="../../hisglobal/images/btn-ccl.png" onclick="cancel();"/></td>
			
		</tr>
	</table>
	
		<input type="hidden" name="hmode" value="">
		<input type="hidden" name="strRequestValue" value="0">
	
</html:form>
<tag:autoIndex></tag:autoIndex> 
</body>
</html>