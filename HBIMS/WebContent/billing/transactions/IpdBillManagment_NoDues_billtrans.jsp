<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/dateTag.tld" prefix="date"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>
<%@ taglib uri="/WEB-INF/patientDtl.tld" prefix="pDtl"%>
<%@ taglib uri="/WEB-INF/accountDtl.tld" prefix="acDtl"%>
<%@ taglib uri="/WEB-INF/onLineReqDiscountDtl.tld" prefix="DisDtl"%>
<%@ taglib uri="/WEB-INF/crNo.tld" prefix="crNo"%>
<%@ taglib uri="/WEB-INF/comboPersistence.tld" prefix="cmbPers"%>
<%@ taglib uri="/WEB-INF/onlineClientDtl.tld" prefix="bDtl"%>
<html>
<head><meta charset=utf-8>
<title>IPD Bill Management</title>
<link href="../css/transaction.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/calendar-tas.css" rel="stylesheet"	type="text/css">
<link href="../../hisglobal/css/buttons.css" rel="stylesheet" type="text/css">
<script language="Javascript" src="../../hisglobal/js/datepicker1.js"></script>
<script language="Javascript" src="../../hisglobal/js/validation.js"></script>
<script language="JavaScript" src="../../masterutil/js/master.js"></script>
<script language="JavaScript" src="../../hisglobal/js/tab.js"></script>
<script language="JavaScript" src="../../hisglobal/js/calendar.js"></script>
<script language="Javascript" src="../../hisglobal/js/multirow.js"></script>
<script language="Javascript" src="../../hisglobal/js/datepicker1.js"></script>
<script language="Javascript" src="../../hisglobal/js/util.js"></script>
<script language="Javascript" src="../../billing/js/IpdBillMangTrans.js"></script> 
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
.no-close .ui-dialog-titlebar-close {display: none }
</style>
</head>
<body onload="openPrintPopUp();getgoDetails(this);">
<html:form action="transactions/IpdBillManagementTransCNT.cnt" method="post">
 <div id="nonPrintable">
      <div id="errMsg" class="errMsg"><bean:write     name="ipdBillManagementTransBean" property="strErrMsg"/></div> 
   	 <div id="warningMsg" class="warningMsg"><bean:write name="ipdBillManagementTransBean" property="strWarning"/></div>
	 <div id="normalMsg" class="normalMsg"><bean:write  name="ipdBillManagementTransBean" property="strMsg"/></div>
 
 
 
	<tag:tab tabList="${ipdBillManagementTransBean.lhm}" selectedTab="NODUESPRINT" align="center"
	width="TABLEWIDTH"></tag:tab>
 <table class="TABLEWIDTH" align="center">
  <tr class="HEADER"> 
    <td colspan="4">No Dues</td>
  </tr>
  <tr> 
    <td width="50%" colspan="2" class="LABEL">CR No.</td>
    <td colspan="2" width="50%" class="CONTROL"><bean:write name="ipdBillManagementTransBean" property="strCrNo" />
    </td>   
  
  </tr>
  </table>
 
  <jsp:include page="IpdBillMgmtHeaderNew.jsp"/>
	
	
	<div id="movementDtlId">
	<bean:write name="ipdBillManagementTransBean" property="strPatientAdmndtl" filter="false"/>
	</div>
 
  <table class="TABLEWIDTH" border="0" align="center" >
  <tr class="FOOTER"> 
    <td colspan="4"><font size="2" color="red">*</font> Mandatory Fields</td>
  </tr>
  </table>	
 
  <table border="0" class="TABLEWIDTH" align="center">
		<tr>
			<br>
			<td align="right"><!-- <img style="cursor:pointer;cursor:hand"  src="../../hisglobal/images/btn-sv.png" onclick=" return goFuncUpdateAcctStatus();"> -->
			<a href="#" class="button" id="print_btn"	onclick="noduesprint1();"><span class="print">Print</span></a> </td>
			<td align="left">
			
			<!-- <img  style="cursor:pointer;cursor:hand" src="../../hisglobal/images/btn-ccl.png" onClick="cancel1('LIST');"> -->
			<a href="#" class="button"	onClick="cancel1('LIST');"><span class="cancel">Cancel</span></a> 
			</td>
		</tr>
	</table>

	<input type="hidden" name="hmode">
	<input type="hidden" name="strChk" value="${ipdBillManagementTransBean.strChk}">
	<input type="hidden" name="strSeatId" value="${ipdBillManagementTransBean.strSeatId}">
	<input type="hidden" name="strPresentAcctStatusCode" value="${ipdBillManagementTransBean.strPresentAcctStatusCode}">
	
		<input type="hidden" name="searchColumn" value='<%=request.getParameter("searchColumn") %>' />
	<input type="hidden" name="search" value='<%=request.getParameter("search") %>' />
	<input type="hidden" name="blockNo" value='<%=request.getParameter("blockNo") %>' />
	<input type="hidden" name="prevNext" value='<%=request.getParameter("prevNext") %>' />
	<input type="hidden" name="rowNum" value='<%=request.getParameter("rowNum") %>'/>
	<input type="hidden" name="divisionId" value='<%=request.getParameter("divisionId") %>' />
	<input type="hidden" name="selectedTab">
	<input type="hidden" name="strIsCalledFromIpdBillNew" value="${ipdBillManagementTransBean.strIsCalledFromIpdBillNew}" />
    <input type="hidden" name="strAcctStatMode" value="${ipdBillManagementTransBean.strAcctStatMode}" />
    <input type="hidden" name="printFlag" value="${ipdBillManagementTransBean.printFlag}" />
    <input type="hidden" name="strAddmissionNo" value="${ipdBillManagementTransBean.strAddmissionNo}" />
    <input type="hidden" name="strAdmissionDate" value="${ipdBillManagementTransBean.strAdmissionDate}" />
    <input type="hidden" name="strDischargeDate" value="${ipdBillManagementTransBean.strDischargeDate}" />
    <input type="hidden" name="isOpenPopUp" value="${ipdBillManagementTransBean.isOpenPopUp}"/>
	<input type="hidden" name="filePath" value="${ipdBillManagementTransBean.filePath}"/>
	<input type="hidden" name="printMode" value="${ipdBillManagementTransBean.printMode}"/>
	<input type="hidden" name="strAccountNo" value="${ipdBillManagementTransBean.strAccountNo}" />
	<input type="hidden" name="strCrNo" value="${ipdBillManagementTransBean.strCrNo}" />
	<input type="hidden" name="strDeptId" value="${ipdBillManagementTransBean.strDeptId}" />
	<input type="hidden" name="strWardCode" value="${ipdBillManagementTransBean.strWardCode}" />
	<input type="hidden" name="strTreatmentCategory" value="${ipdBillManagementTransBean.strTreatmentCategory}" />
	<input type="hidden" name="strBillNo" value="${ipdBillManagementTransBean.strBillNo}" />
	<input type="hidden" name="strAccType" value="${ipdBillManagementTransBean.accType}" />
	<input type="hidden" name="strAccStatus" value="${ipdBillManagementTransBean.strAccStatus}" />
	<input type="hidden" name="finalBillFlag" value="${ipdBillManagementTransBean.finalBillFlag}" />
	
   <cmbPers:cmbPers/>
</div>   
</html:form>
<div id='printableSlip'>
<logic:equal name="ipdBillManagementTransBean"  property="isOpenPopUp" value="1">
<logic:equal name="ipdBillManagementTransBean"  property="printMode" value="1">
<logic:present name="ipdBillManagementTransBean"  property="filePath">
<jsp:include page="billing_receipt_printing_popup.jsp"></jsp:include>
</logic:present>
</logic:equal>
</logic:equal>
</div>				

</body>
</html>