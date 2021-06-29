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
<head>
<meta charset="utf-8" /> 
<style type="text/css">
            .pg-normal 
            {
                color: blue;
                font-weight: normal;
                text-decoration: none;
                cursor: pointer;
                
                
            }
            .pg-selected 
            {
                color: red;
                font-weight: bold;
                text-decoration: underline;
                cursor: pointer;
                
            }
            .pg-qualified 
            {
                color: green;
                font-weight: bold;
                text-decoration: underline;
                cursor: pointer;
                
            }
            </style>
<title>On-Line Refund Approval</title>
<link href="../css/transaction.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/calendar-tas.css" rel="stylesheet" type="text/css">
	<link href="../../hisglobal/css/popup.css" rel="stylesheet"
	type="text/css">
<link href="../../hisglobal/css/buttons.css" rel="stylesheet" type="text/css">
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
<script language="Javascript" src="../../billing/js/OnLineRefundApprovalTrans.js"></script>

</head>
<body onLoad="checkRadio();" >

<html:form action="transactions/OnLineRefundApprovalCNT.cnt" method="post">

<div id ="errMsg" class="errMsg"><bean:write name="onLineRefundApprovalTransBean" property="strErrMsg" filter="false"/></div> 
<div id ="normalMsg" class="normalMsg"></div>
<div  class="normalMsg"><bean:write name="onLineRefundApprovalTransBean" property="strMsg" filter="false"/></div>


 <tag:tab tabLabel="On-Line Refund Approval" selectedTab="FIRST" align="center" width="TABLEWIDTH">
	</tag:tab>
 <table class="TABLEWIDTH" align="center" border="0" cellspacing ="1px">
  <tr class="HEADER"> 
    <td colspan="4">On-Line&gt;&gt;Refund Approval</td>
  </tr>
  </table>  
  
    <bean:write name="onLineRefundApprovalTransBean" property="strTodayApprovalListDtls" filter="false"/>

   <div id="refundCRNODivId" style="display: block">
 	<bean:write name="onLineRefundApprovalTransBean" property="strRefundRequestDtls" filter="false"/>  
   </div>
   
   <table class="TABLEWIDTH" align="center" border="0" cellspacing ="1px">
  <tr class="TITLE"> 
    <td colspan="4"></td>
  </tr>
  </table>	
  <table class="TABLEWIDTH" align="center" border="0" cellspacing ="1px">
  
  <tr> 
      <td width="50%"  align="right" class='LABEL'><select name='strSearchMode' class='comboMin'><option value='1'>CR No</option><option value='2'>Patient Name</option></select>
      <input type="text" name="strSearchValue" class="txtFldMax" maxlength="15" onkeypress="return validateData(event,9);">
       <img style="cursor:pointer;cursor:hand"  src="/HBIMS/hisglobal/transactionutil/images/search_icon1.gif" name='searchBillDtls' onclick="goFunc();"  onKeyPress="goFunc();"/>
  	<!-- <a href="#" class="button" id="" onclick="goFunc();"  onKeyPress="goFunc();"><span class="search">Search</span></a> -->
  </td>   
  </tr>
  
  </table>
  <table class="TABLEWIDTH" align="center" border="0" cellspacing ="1px">
  <tr class="TITLE"> 
    <td colspan="4"></td>
  </tr>
  </table>	
  <table class="TABLEWIDTH" align="center" border="0" cellspacing ="1px">
  <tr>
 
     
  
  </tr>
  </table>
 
      <div id="tariffDivId" style="display: none"></div>
 
   
   
   <table class="TABLEWIDTH" align="center" cellpadding="1px"
		cellspacing="1px">
		
		<tr>
			<td width='50%' class='LABEL'>
			  <div align="right"><font color="red">*</font>Refund By:</div>
			</td>
			<td width="50%" colspan="2" class="CONTROL"><select name="strRefundedByComboVal">
				<bean:write name="onLineRefundApprovalTransBean"
					property="strRefendedBy" filter="false" />
			   </select>
			</td>
		</tr>
		<tr>

			<td width="50%" class="LABEL"><font color="red">*</font>Remarks</td>
			<td width="50%" colspan="2" class="CONTROL"><textarea rows="2"
				cols="20" name="strRemarks"></textarea></td>
		</tr>
	</table>
   
   <table class="TABLEWIDTH" align="center" border="0" cellspacing ="1px">
  
    <tr class="FOOTER"> 
    <td colspan="2"><div align="left">If Tariff/Request Not Shown then Service Is Already Consumed(Sample Collected,Service Taken etc.)</div></td>
  	<td colspan="2"><div align="right"><font size="2" color="red">*</font> Mandatory Fields</div></td>
  </tr>
  </table>	

  <table border="0" class="TABLEWIDTH" align="center">
		<tr>

			
			<td align="center">
			<!-- <img src="../../hisglobal/images/btn-sv.png" style="cursor: pointer;cursor:hand" title="Print Record"   onclick="save();">
			 <img style="cursor: pointer;cursor:hand" src="../../hisglobal/images/btn-clr.png" title="click here to clear the contents"   
				name="clearImg" onclick="clearData();"/> <img style="cursor: pointer;cursor:hand" title="click here to cancel the process" 
				src="../../hisglobal/images/btn-ccl.png" onclick="cancelFunc();"/>
				-->
						<br><a href="#" class="button" id="" onclick='save();'><span class="save">Save</span></a>
							<a href="#" class="button"	onclick="clearData();"><span class="clear">Clear</span></a> 
							<a href="#" class="button" onclick="cancelFunc();"><span class="cancel">Cancel</span></a>
				</td>
			
		</tr>
	</table>
	
		<input type="hidden" name="hmode" value="">
		<input type="hidden" name="strRequestValue" value="0">
	
</html:form>
<tag:autoIndex></tag:autoIndex> 
</body>
</html>