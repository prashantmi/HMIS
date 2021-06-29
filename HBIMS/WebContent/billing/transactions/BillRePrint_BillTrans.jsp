<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>

<html>
<head>
<meta charset=utf-8><title>Bill Re-Print</title>

<script language="JavaScript" src="../../hisglobal/js/tab.js"></script>
<link href="../css/transaction.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/popup.css" rel="stylesheet" type="text/css">



<script language="JavaScript" src="../../hisglobal/js/validation.js"></script>
<script language="Javascript" src="../js/BillRePrint_Trans.js"></script>
<script language="Javascript" src="../../hisglobal/js/util.js"></script>




</head>
<body onload="withoutCrNoOnLoadLogics();showBillDetails();" onfocus="checkPopUp();" onUnload="closePopUp();">

<html:form action="transactions/BillRePrintTransCNT.cnt" method="post" onsubmit="">

     <div id="errMsg" class="errMsg"><bean:write     name="BillRePrintTransBean" property="strErrMsg"/></div> 
   	 
	 <div id="normalMsg" class="normalMsg"><bean:write  name="BillRePrintTransBean" property="strMsg"/></div>
  
  
<tag:tab tabLabel="Receipt Re-Print" selectedTab="FIRST" align="center" width="TABLEWIDTH">
	</tag:tab>

<table class="TABLEWIDTH" align="center" border='0' cellpadding="1px" cellspacing ="1px">
  <tr class="HEADER"> 
    <td colspan="5">Receipt Re-Print &gt;&gt;</td>
  </tr>
 
  <tr> 
    <td width="25%" class="LABEL"><font color="red">*</font><B>Receipt No.</B></td>
    <td colspan="3" class="CONTROL">
    
      <input type="text" class="txtFldMax" name="strTransNo" value="${BillRePrintTransBean.strTransNo}" maxlength="10" onkeypress="return goRetFunc(event);" onkeyup="focusNext(this);" >
    / <input type="text" class="txtFldMin" name="strRcptNo"  value="${BillRePrintTransBean.strRcptNo}" maxlength="2" onkeypress="return goRetFunc(event);">
      
      <img style="cursor:pointer;cursor:hand"  src="../../hisglobal/images/viewDetails.gif" title="Bill Search"  name='searchBillDtls' onclick="showBillSearchPopup();"/>
      <img style="cursor:pointer;cursor:hand" title="Get Bill Details" src="../../hisglobal/images/Go.png" align="top" name="go" onclick="goFunc();" onKeyPress="goFunc();"/>
    
    
    </td>   
  </tr>
 </table> 
 <div id='billDtl' style='display:none'>
   	<table class="TABLEWIDTH" align="center" border="0" cellpadding="1px" cellspacing ="1px">
  	 
							     <bean:write     name="BillRePrintTransBean" property="strBillDetl"/>
    </table>						     
 </div>
 <div id="BillType" style="display:none">	
  	<table class="TABLEWIDTH" align="center" border="0" cellpadding="1px" cellspacing ="1px">
  	<tr> 
    <td width="50%" class="LABEL"><font color="red">*</font>Receipt Type:</td>
     <td width="50%" class="CONTROL">
         <select name="strBillTypeCombo" class='comboNormal' id="strBillTypeCombo"><option value="0">Select Value</option><option value="1">Consolidated</option><option value="2">Detailed</option></select>
     </td>    
    </table>
 </div>
<table class="TABLEWIDTH" align="center" border='0' cellpadding="1px" cellspacing ="1px">
<tr class="FOOTER"> 
    <td colspan="4"><font size="2" color="red">*</font> Mandatory Fields</td>
</tr>
</table>

<div id="withoutPrint" style="display:block">	
<table border="0" class="TABLEWIDTH" align="center">
		<tr>
			<td align="center">
			<img style="cursor:pointer;cursor:hand" src="../../hisglobal/images/btn-clr.png" onclick="clearBillRePrint();" >
			<img style="cursor:pointer;cursor:hand" src="../../hisglobal/images/btn-ccl.png" onclick="initPage();" ></td>
			
		</tr>
</table>
</div>

<div id="withPrint" style="display:none">	
<table border="0" class="TABLEWIDTH" align="center">
		<tr>
			<td align="center">
			<img style="cursor:pointer;cursor:hand" src="../../hisglobal/images/print_tab.gif" onClick="return PRINT();">
			<img style="cursor:pointer;cursor:hand" src="../../hisglobal/images/btn-clr.png" onclick="clearWithOutBillRePrint();" >
			<img style="cursor:pointer;cursor:hand" src="../../hisglobal/images/btn-ccl.png" onclick="initPage();" ></td>
			
		</tr>
</table>
</div>
       <input type="hidden" name="hmode"/>
       <input type="hidden" name="strTempBillNo" value="${BillRePrintTransBean.strTempBillNo}">
	   <input type="hidden" name="strTempReceiptNo" value="${BillRePrintTransBean.strTempReceiptNo}">
	   <input type="hidden" name="strPrintFlag" value="${BillRePrintTransBean.strPrintFlag}">
      <input type="hidden" name="strPrintMessageLimit" value="${BillRePrintTransBean.strPrintMessageLimit}"/>
   
 </html:form>
<div class="popup" id="popup" style="display: none">  

</div>
<tag:autoIndex></tag:autoIndex> 
</body>
</html>