<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/dateTag.tld" prefix="date"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>
<%@ taglib uri="/WEB-INF/comboPersistence.tld" prefix="cmbPers"%>


<head>
<meta charset=UTF-8">
<title>Indent View</title>

<link href="../css/master.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/calendar-tas.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/popup.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/buttons.css" rel="stylesheet" type="text/css">
<script type="text/javascript"src="../../hisglobal/js/calendar.js"></script>
<script language="Javascript" src="../../hisglobal/js/datepicker1.js"></script>
<script language="JavaScript" src="../../hisglobal/js/tab.js"></script>
<script language="JavaScript" src="../../hisglobal/masterutil/js/master.js"></script>
<script language="JavaScript" src="../../hisglobal/js/validation.js"></script>
<script language="Javascript" src="../../hisglobal/js/util.js"></script>
<script language="Javascript" src="../js/IndentApproval.js"></script>

<script language="JavaScript" src="../../masterutil/js/master.js"></script>
<script language="JavaScript" src="../../hisglobal/js/tab.js"></script>
<script language="JavaScript" src="../../hisglobal/js/calendar.js"></script>
<script language="JavaScript" src="../../hisglobal/js/multirow.js"></script>
<script language="Javascript" src="../../hisglobal/js/validation.js"></script>
<script language="Javascript" src="../../hisglobal/js/util.js"></script>
<script language="Javascript" src="../../hisglobal/js/popup.js"></script>
<script language="Javascript" src="../../hisglobal/js/dropdown.js"></script>
<script language="Javascript" src="../js/mms.js"></script>
	
</head>
<body>
<html:form name="indentApprovalTransBean" action="/transactions/IndentApprovalDeskCNT" type="mms.transactions.controller.fb.IndentApprovalDeskFB">
	
<center>
	 <div id="errMsg" class="errMsg"><bean:write name="indentApprovalTransBean" property="strErrMsg" /></div>
	 <div id="warningMsg" class="warningMsg"><bean:write name="indentApprovalTransBean" property="strWarning" /></div>
	 <div id="normalMsg" class="normalMsg"><bean:write name="indentApprovalTransBean" property="strMsg" /></div>
	
</center>
        
    <tag:tab tabLabel="Indent Approval Desk" selectedTab="FIRST" align="center" width="TABLEWIDTH"></tag:tab>
	<div  id ="Receving" style="display:none">
	    <table class="TABLEWIDTH" align="center" cellspacing="1" cellpadding="1">   
		   <tr class="HEADER">
			  <td colspan="4"></td>
		  </tr>
	   </table>
	</div>
	<div  id ="Raising" style="display:block">
       <table class="TABLEWIDTH" align="center" cellspacing="1" cellpadding="1">   
		 <tr class="HEADER">
			<td colspan="4"></td>
		 </tr>
		 </table>
	</div>	 
	<jsp:include page="discrepancyReportglobal.jsp"></jsp:include>
		 <table class="TABLEWIDTH" align="center" cellspacing="1" cellpadding="1">   
		      <bean:write name="indentApprovalTransBean" property="strIndentDetails" filter="false" />
		 </table>
		
		
		<table class="TABLEWIDTH" align="center" cellspacing="1" cellpadding="1">   
		      <bean:write name="indentApprovalTransBean" property="strLstApprovalDetails" filter="false" />
		 </table>
		
		<table class='TABLEWIDTH' align='center'  border='0'  cellspacing ='1px'>
		<tr>
		<td width='5%' class='TITLE' align='center'><input type='hidden' name='button1' value='0'>
		<img src='../../hisglobal/images/plus.gif'   id='plus2'  style='display:block;cursor:pointer' onClick='ftn22()'>
		<img src='../../hisglobal/images/minus.gif'  id='minus2' style='display:none;cursor:pointer' onClick='ftn22()'></td>
		<td colspan='3' class='TITLE' align='left'><b><div id='' style='color:blue;'>Non-Discrepancy Report</div></b></td>  
		</tr>
		</table>
		 
		 <div id='NonDiscrepancyRpt' style='display:none'>
		      <bean:write name="indentApprovalTransBean" property="strNonDiscrepancyRpt" filter="false" />
		      <div id="paginationNonDisId"></div>
		 </div>
		 <table class='TABLEWIDTH' align='center'  border='0'  cellspacing ='1px'>
		  <tr>
		    <td width='5%' class='TITLE' align='center'><input type='hidden' name='button1' value='0'>
		    <img src='../../hisglobal/images/plus.gif'   id='plus3'  style='display:block;cursor:pointer' onClick='ftn33()'>
		    <img src='../../hisglobal/images/minus.gif'  id='minus3' style='display:none;cursor:pointer' onClick='ftn33()'></td>
		    <td colspan='3' class='TITLE' align='left'><b><div id='' style='color:blue;'>Discrepancy Report</div></b></td>  
		  </tr>
		</table>
		<div id='DiscrepancyRpt' style='display:none'>
		   <table class="TABLEWIDTH" align="center" cellspacing="1" cellpadding="1">   
		      <bean:write name="indentApprovalTransBean" property="strDiscripancyRpt" filter="false" />
		   </table>
		</div>
	  		
	  <table class="TABLEWIDTH" align="center" cellspacing="1" cellpadding="1">   
		 <tr class="TITLE">
			<td colspan="5">Approval Details</td>
		 </tr>
		</table>
	    <bean:write name="indentApprovalTransBean" property="strSetApprovedDetails" filter="false" />
	  		
	  		
	   	
   <table  class="TABLEWIDTH" align="center">
	 <tr class="FOOTER">
               <td width="TABLEWIDTH" colspan="2" ></td>
               </tr>
		<tr>
	  <tr>
			<td align="center">
	               <!--  <img style="cursor:pointer;cursor:pointer"  title="Click to Save Record" src="../../hisglobal/images/btn-sv.png"  onClick=" return validate1();" />
	                <img style="cursor:pointer;cursor:pointer"  title="Click to Clear Page" src="../../hisglobal/images/btn-clr.png" name="clearImg" onclick="initPage();">
	                <img style="cursor: pointer; " src="../../hisglobal/images/back_tab.png" onClick="cancelToDesk();" title="Click Here Go Back To Main Desk"/> -->
	                <br>
	                <a href="#" class="button" id="" onclick=' return validate1();'><span class="save">Save</span></a>
					<a href="#" class="button"	onclick="initPage()"><span class="clear">Clear</span></a> 
					<a href="#" class="button" onclick="cancelToDesk();"><span class="cancel">Cancel</span></a>
			</td>
		</tr>
	</table>
	<input type="hidden" name="hmode"/>
	<input type="hidden" name="strStockNo"    value="${indentApprovalTransBean.strReqNo}">
	<input type="hidden" name="strStoreId"    value="${indentApprovalTransBean.strStoreId}">
	<input type="hidden" name="strLevelType"  value="${indentApprovalTransBean.strLevelType}">
	<input type="hidden" name="strReqTypeId"  value="${indentApprovalTransBean.strReqTypeId}">
	<input type="hidden" name="strPath"       value="${indentApprovalTransBean.strPath}">
	<input type="hidden" name="strChk"        value="${indentApprovalTransBean.strChk}">
	<cmbPers:cmbPers/>
	</html:form>
		<tag:autoIndex></tag:autoIndex>   
</body>
