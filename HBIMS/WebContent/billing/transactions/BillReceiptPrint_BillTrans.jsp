<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<html>
<head>
<meta charset=utf-8><title>Bill Re-Print</title>

<script language="JavaScript" src="../../hisglobal/js/tab.js"></script>
<link href="../css/transaction.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/popup.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/buttons.css" rel="stylesheet" type="text/css">

<script language="JavaScript" src="../../hisglobal/js/validation.js"></script>
<script language="Javascript" src="../js/BillDupPrint_Trans.js"></script>
<script language="Javascript" src="../../hisglobal/js/util.js"></script>
<script language="Javascript" src="../../hisglobal/js/popup.js"></script>
<script language="Javascript" src="../../hisglobal/js/newpopup.js"></script>

<script type="text/javascript">
var beforePrint = function() 
{  
	showPrintableSlip();
};
var afterPrint = function() 
{               
	hidePrintableSlip();
};
/*if (window.matchMedia) 
{
	var mediaQueryList = window.matchMedia('print');
	mediaQueryList.addListener
	(
		function(mql) 
		{
		    if (mql.matches) 
		    {
		    	beforePrint();
		    } 
		    else 
		    {
		    	afterPrint();
		    }
		}
	);
 }*/

window.onbeforeprint = beforePrint;
window.onafterprint = afterPrint;
</script>

<style type="text/css">

@media print 
{ 
		#nonPrintable 
		{
		 display: none; 
		}		
}
</style>

</head>
<body onload="showBillDetails();openPrintPopUp();" onfocus="checkPopUp();" onUnload="closePopUp();">

<html:form action="transactions/BillDupPrintTransCNT.cnt" method="post">

<div id="nonPrintable">
     <div id="errMsg" class="errMsg"><bean:write     name="BillDupPrintTransBean" property="strErrMsg"/></div>   	 
	 <div id="normalMsg" class="normalMsg"><bean:write  name="BillDupPrintTransBean" property="strMsg"/></div>  
  
<tag:tab tabLabel="Receipt Duplicate-Print" selectedTab="FIRST" align="center" width="TABLEWIDTH"></tag:tab>

<table class="TABLEWIDTH" align="center" border='0' cellpadding="1px" cellspacing ="1px">
  <tr class="HEADER">  
    <td colspan="2">Receipt Duplicate-Print &gt;&gt;</td>
    <td>
		<div align="right">
			<img style="cursor: pointer" name="printLastButton" src="../../hisglobal/images/print_on.gif" onclick="printLastBill();" title="Print Last Bill">
		</div>
	</td>
  </tr>
 
  <tr> 
    <td width="25%" class="LABEL"><font color="red">*</font><B>Receipt No.</B></td>
    <td colspan="3" class="CONTROL">
    
      <input type="text" class="txtFldMax" name="strTransNo" value="${BillDupPrintTransBean.strTransNo}" maxlength="15" onkeypress="return goRetFunc(event);" onkeyup="focusNext(this);" >
    / <input type="text" class="txtFldMin" name="strRcptNo"  value="${BillDupPrintTransBean.strRcptNo}" maxlength="2" onkeypress="return goRetFunc(event);">
      
     
      <html:select name="BillDupPrintTransBean" property="strReceiptType"  styleClass="comboNormal" onchange="changeReceiptTypelabel(this);">
          <bean:write     name="BillDupPrintTransBean" property="strReceiptTypevalues" filter="false"/>
      </html:select>
      
      <img style="cursor:pointer;cursor:hand"  src="../../hisglobal/images/viewDetails.gif" title="Bill Search"  name='searchBillDtls' onclick="showBillSearchPopup();"/>
      <img style="cursor:pointer;cursor:hand" title="Get Bill Details" src="../../hisglobal/images/Go.png" align="top" name="go" onclick="goFunc();" onKeyPress="goFunc();"/>
    
    
    </td>   
  </tr>
 </table> 
 <div id='billDtl' style='display:none'>
   	<table class="TABLEWIDTH" align="center" border="0" cellpadding="1px" cellspacing ="1px">
  	 
							     <bean:write     name="BillDupPrintTransBean" property="strBillDetl"/>
    </table>						     
 </div>
 
 <div id="BillType" style="display:none">	
  	<table class="TABLEWIDTH" align="center" border="0" cellpadding="1px" cellspacing ="1px">
  	<tr> 
    <td width="50%" class="LABEL"><font color="red">*</font>Receipt Type:</td>
     <td width="50%" class="CONTROL">
         <select name="strBillTypeCombo" class='comboNormal' id="strBillTypeCombo">         
	         <option value="2">Detailed</option>
	         <option value="1">Consolidated</option>
         </select>
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
			<!-- <img style="cursor:pointer;cursor:hand" src="../../hisglobal/images/btn-clr.png" onclick="clearBillRePrint();" >
			<img style="cursor:pointer;cursor:hand" src="../../hisglobal/images/btn-ccl.png" onclick="cancelFunc();" >-->
			<br><a href="#" class="button"	onclick="clearBillRePrint();"><span class="clear">Clear</span></a> 
			<a href="#" class="button" onclick="cancelFunc();"><span class="cancel">Cancel</span></a>
			</td>
			
		</tr>
</table>
</div>

<div id="withPrint" style="display:none">	
<table border="0" class="TABLEWIDTH" align="center">
		<tr>
			<td align="center">
			<!-- <img style="cursor:pointer;cursor:hand" src="../../hisglobal/images/print_tab.gif" onClick="return PRINT();">
			<img style="cursor:pointer;cursor:hand" src="../../hisglobal/images/btn-clr.png" onclick="clearWithOutBillRePrint();" >
			<img style="cursor:pointer;cursor:hand" src="../../hisglobal/images/btn-ccl.png" onclick="initPage();" >-->
			<br>
			<a href="#" class="button" id="" onclick=' return PRINT();'><span class="save">Save</span></a>
							<a href="#" class="button"	onclick="clearWithOutBillRePrint();"><span class="clear">Clear</span></a> 
							<a href="#" class="button" onclick="initPage();"><span class="cancel">Cancel</span></a>
			
			</td>
			
		</tr>
</table>
</div>
	<input type="hidden" name="hmode"/>
	<input type="hidden" name="strTempBillNo" value="${BillDupPrintTransBean.strTempBillNo}">
	<input type="hidden" name="strTempRcptNo" value="${BillDupPrintTransBean.strTempRcptNo}">
	<input type="hidden" name="strPrintFlag" value="${BillDupPrintTransBean.strPrintFlag}">
	<input type="hidden" name="strPrintMessageLimit" value="${BillDupPrintTransBean.strPrintMessageLimit}"/>  
	<input type="hidden" name="strModeVal" value="2"/>
	<input type="hidden" name="isOpenPopUp" value="${BillDupPrintTransBean.isOpenPopUp}"/>
	<input type="hidden" name="filePath" value="${BillDupPrintTransBean.filePath}"/>
	<input type="hidden" name="strReceiptTypeLabel" value="${BillDupPrintTransBean.strReceiptTypeLabel}"/>
   
</div>

<div id='printableSlip'>
<logic:equal name="BillDupPrintTransBean"  property="isOpenPopUp" value="1">
<logic:present name="BillDupPrintTransBean"  property="filePath">
<jsp:include page="billing_receipt_printing_popup.jsp"></jsp:include>
</logic:present>
</logic:equal>
</div>

 </html:form>
<div class="popup" id="popup" style="display: none">  

</div>
<tag:autoIndex></tag:autoIndex> 
</body>
</html>