<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- 
 Ipd Bill Re-Open Transaction JSP
  
 author : Debashis Sardar
 
 date: 10-Dec-2011
 -->
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>
<html>
<head><meta charset=utf-8>
<title>IPD Bill Re-Open</title>
<!-- <link href="../billing/css/transaction.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/calendar-tas.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/popup.css" rel="stylesheet" type="text/css">
 -->
<link href="../css/transaction.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/calendar-tas.css" rel="stylesheet"	type="text/css">
<link href="../../hisglobal/css/buttons.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/popup.css" rel="stylesheet" type="text/css">
<style type='text/css'>

</style>

<script language="Javascript" src="../../hisglobal/js/validation.js"></script>
<script language="JavaScript" src="../../hisglobal/transactionutil/js/master.js"></script>
<script language="JavaScript" src="../../hisglobal/js/tab.js"></script>
<script language="JavaScript" src="../../hisglobal/js/calendar.js"></script>
<script language="Javascript" src="../../hisglobal/js/multirow.js"></script>
<script language="Javascript" src="../../hisglobal/js/util.js"></script>
<script language="Javascript" src="../../hisglobal/js/popup.js"></script>
<script language="Javascript" src="/HBIMS/billing/js/IpdBillReOpenTrans.js"></script>
<script language="Javascript" src="/HBIMS/billing/js/billing.js"></script>
<script language="Javascript" src="/HBIMS/billing/js/SearchReceiptDetails.js"></script>
<script type="text/javascript">
	 function viewBill(event)
 {
  var wardCode=1;
  var deptCode=1;
  var accountno=document.forms[0].strPatAccNo.value;
  var billNo=document.forms[0].strRcptNo.value;
  var chk=accountno+"@"+"";
  var reportid='1';
  var reportFormat='pdf';
  
  var url="/HBIMS/billing/transactions/IpdBillManagementTransCNT.cnt?hmode=VIEWRPT&deptCode="+deptCode+"&wardCode="+wardCode+"&chk="+chk+"&billNo="+billNo+"&rptMode=1";
  
  openDependentPopup(url,event,500,800)
 }
	
</script>
</head>
<body onLoad="document.forms[0].strRcptNo.focus(),showPatientDetails();">
<html:form action="transactions/IpdBillReOpenCNT.cnt"
	 method="post">
	   
    <div id='errMsg' class="errMsg"><bean:write name="IpdBillReOpenTransBean" property="strErrMsg" filter="false"/></div>
    <div id='normalMsg' class="normalMsg"><bean:write name="IpdBillReOpenTransBean" property="strMsg" filter="false"/></div>
   <div id="warningMsg" class="warningMsg"><bean:write
		name="IpdBillReOpenTransBean" property="strWarningMsg" filter="false" /></div>
    
		<tag:tab tabList="${IpdBillReOpenTransBean.lhm}" selectedTab="IPDBILLREOPEN" align="center"
			width="TABLEWIDTH"></tag:tab>
    <table class="TABLEWIDTH" cellspacing="1px" align="center">
		<tr class="HEADER" bgcolor="">
			<td colspan="6"> 
		  IPD Bill Re-Open</td>
		  	  
		</tr>
		<tr class="TITLE" bgcolor="">
			
		  <td colspan="6"> <div  id="viewBil" style="display: none">
		  <a id="viewBill" style="cursor: pointer;" onclick="viewBill(event)" 
					title="View Bill"><font color="blue" style=""><u>View Bill</u></font></a>
			</div></td>
			
		</tr>
		<tr>
			
			<td class="LABEL" ><font color="red">*</font>IPD Bill No.</td>
			<td class="CONTROL" colspan="4">
			<input type="text" class="txtFldMax" name="strRcptNo"  value="${IpdBillReOpenTransBean.strRcptNo}" maxlength="15" onkeypress="return initGoFunc(event);">
 				<img style="cursor:pointer;cursor:hand" src="../../hisglobal/images/viewDetails.gif" title="click here for patient search"  name='searchPatient' onclick="checkForError();"/>
				<img title="click here for Bill Details" src="../../hisglobal/images/Go.png" name="go"  onclick="return goFunc();" onkeypress="return goFunc();"/>
				 
			</td>
		</tr>
		
	</table>
	<div  id="details" style="display: none">
			<table class="TABLEWIDTH" align="center">
  	 			 <tr class="TITLE"> 
  	 			 <td width="25" class="TITLE">
     										<div id="plus" style="display:none">
   											<img style="cursor:pointer;cursor:hand" title="click here to show Patient Details" src="../../hisglobal/images/plus.gif"  onclick="showinfo();">
   											</div><div id="minus" style="cursor:pointer;cursor:hand" title="click here to hide Patient Details" style="display:block"><img src="../../hisglobal/images/minus.gif"  onclick="hideinfo();"></div>
  										 </td>
  	 			  <td colspan="4"><b> Patient Details</b></td>
    								
 				 </tr>
 				 </table>	
 				<div id='patdtltld'>
 				 <bean:write name="IpdBillReOpenTransBean" property="strPatientDtls" filter="false"/>
 				 </div>
 			 
 </div>
<div  id="billDetails" style="display: none">
			<table class="TABLEWIDTH" align="center">
		<tr class="TITLE"> 
  	 			
  	 			  <td colspan="4"><b>Bill Details</b></td>
    								
 				 </tr>	
  	<tr>
      <td class="LABEL" width="25%"><div align="right">Bill Date</div></td>
      <td class="CONTROL" width="25%"><div align="left">
      <bean:write name="IpdBillReOpenTransBean" property="strBillDate" filter="false"/>
      </div></td>
      <td class="LABEL" width="25%"><div align="right">Approved By</div></td>
      <td class="CONTROL" width="25%"><div align="left">
       <bean:write name="IpdBillReOpenTransBean" property="strApprovedBy" filter="false"/>
      </div></td>
    </tr>
  	<tr>
      <td class="LABEL" width="25%"><div align="right">Expense Amount</div></td>
      <td class="CONTROL" width="25%"><div align="left">
       <bean:write name="IpdBillReOpenTransBean" property="strExpenseAmt" filter="false"/>
      </div></td>
      <td class="LABEL" width="25%"><div align="right">Received Amount</div></td>
      <td class="CONTROL" width="25%"><div align="left">
     <bean:write name="IpdBillReOpenTransBean" property="strReceiveAmt" filter="false"/>
      </div></td>
    </tr> 			 
 	</table>		 
 </div>
	
	<table class="TABLEWIDTH" border="0" cellspacing="1px" align="center">
		<tr class="FOOTER">
			<td colspan="4"><font size="2" color="red">*</font> Mandatory
			Fields</td>
		</tr>
	</table>
	<div id="lastbuttons" style="display: none">
	<table border="0" class="TABLEWIDTH" cellspacing="1px" align="center">
		<tr>
			<td align="center">
			<!-- <img style="cursor:pointer;cursor:hand" title="click here to save the selected data" src="../../hisglobal/images/btn-sv.png" onClick="return validate1();">
			<img style="cursor:pointer;cursor:hand" title="click here to reset the CR No." src="../../hisglobal/images/btn-clr.png" onclick="showFirstPage();" >
			<img  style="cursor:pointer;cursor:hand" title="click here to forward the control to main page" src="../../hisglobal/images/btn-ccl.png"	onclick="initPage();"></td>
			 -->
			 <a href="#" class="button"	onClick="return validate1();"><span class="save">Save</span></a>
			 <a href="#" class="button"	onClick="showFirstPage();"><span class="clear">Clear</span></a> 
			<a href="#" class="button"	onClick="initPage();"><span class="cancel">Cancel</span></a> 
		</tr>
	</table>
	</div>
	
<div id="onlyClearbutton" style="display:block">	
<table border="0" class=' TABLEWIDTH' align="center" cellpadding="1px" cellspacing="1px">
		<tr>
			<td align="center">
			
			<a href="#" class="button"	onClick="showFirstPage();"><span class="clear">Clear</span></a> 
			<a href="#" class="button"	onClick="initPage();"><span class="cancel">Cancel</span></a> 
			<!-- <img style="cursor:pointer;cursor:hand" title="click here to reset the CR No." src="../../hisglobal/images/btn-clr.png"  onclick="showFirstPage();" >
			<img  style="cursor:pointer;cursor:hand" title="click here to forward the control to main page" src="../../hisglobal/images/btn-ccl.png" onclick="initPage();">
			 -->
			</td>
		</tr>
</table>
</div>
	
	<input type="hidden" name="hmode"/>
	<input type="hidden" name="strCrNo" value="${IpdBillReOpenTransBean.strCrNo }"/>
	<input type="hidden" name="chkValue"/>
	<input type="hidden" name="RcptNo" value="${IpdBillReOpenTransBean.strRcptNo }"/>
	<input type="hidden" name="strPatAccNo" value="${IpdBillReOpenTransBean.strPatAccNo }"/>
	<input type="hidden" value="0" name="popId">
	<input type="hidden" name="selectedTab">
    <input type="hidden" name="strBillDate" value="${IpdBillReOpenTransBean.strBillDate }"/>
    <input type="hidden" name="strApprovedBy" value="${IpdBillReOpenTransBean.strApprovedBy }"/>
    <input type="hidden" name="strExpenseAmt" value="${IpdBillReOpenTransBean.strExpenseAmt }"/>
    <input type="hidden" name="strReceiveAmt" value="${IpdBillReOpenTransBean.strReceiveAmt }"/>
    <input type="hidden" name="strBillCatGrp" value="${IpdBillReOpenTransBean.strBillCatGrp }"/>
    <input type="hidden" name="strBillCatCode" value="${IpdBillReOpenTransBean.strBillCatCode }"/>
	
	</html:form>
	<tag:autoIndex></tag:autoIndex>  
</body>
</html>