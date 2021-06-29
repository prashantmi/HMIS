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
<head>
<meta charset=utf-8>
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
<script language="Javascript" src="../../hisglobal/js/validation.js"></script>
<script language="Javascript" src="../../hisglobal/js/util.js"></script>
<script language="Javascript" src="../../billing/js/IpdBillMangTrans.js"></script> 
</head>
<body onload="groupComboPartPayment();desableCrNo();">
<html:form action="transactions/IpdBillManagementTransCNT.cnt" method="post">
 
      <div id="errMsg" class="errMsg"><bean:write     name="ipdBillManagementTransBean" property="strErrMsg"/></div> 
   	 <div id="warningMsg" class="warningMsg"><bean:write name="ipdBillManagementTransBean" property="strWarning"/></div>
	 <div id="normalMsg" class="normalMsg"><bean:write  name="ipdBillManagementTransBean" property="strMsg"/></div>
 
 
 <!--<tag:tab tabLabel="Update Account" selectedTab="FIRST" align="center" width="TABLEWIDTH">
	</tag:tab>-->
	<tag:tab tabList="${ipdBillManagementTransBean.lhm}" selectedTab="UPDATEACCOUNTSTATUS" align="center"
	width="TABLEWIDTH"></tag:tab>
 <table class="TABLEWIDTH" align="center">
  <tr class="HEADER"> 
    <td colspan="4">Update Account&gt;&gt;Status</td>
  </tr>
  <tr> 
    <td width="50%" colspan="2" class="LABEL">CR No.</td>
    <td colspan="2" width="50%" class="CONTROL"><bean:write name="ipdBillManagementTransBean" property="strCrNo" />
    </td>   
  
  </tr>
  </table>
 
  <jsp:include page="IpdBillMgmtHeader.jsp"/>
  <div id ="otherCreditDetailsId" style="display: none;">
	  <table class="NEWTABLEWIDTH" align="center" cellpadding="0" cellspacing="">
	  
	  <tr> 
		  <td width="2%" colspan='1'> 
		  		<div id="plusCreditDtlsId" style="display: block;" class="lineContinue2"> 
					<img src="/HBIMS/hisglobal/images/plus.gif" name="plusonLine" style="cursor:hand;cursor:pointer" onclick="displayCreditDetails('creditDtlsDivId');" align="middle">					
				</div>
				<div id="minusCreditDtlsId" style="display: none;" class="lineContinue2">
					<img src="/HBIMS/hisglobal/images/minus.gif" style="cursor:hand;cursor:pointer" name="minusonLine" onclick="hideCreditDetails('creditDtlsDivId');">
				</div>
		  </td>
		  <td colspan="3" width='98%'><div class="lineContinue"><label class='DIVLABEL'>Client Details</label></div> </td>
	  </tr>		
	  </table>
	</div>
	
	
	<div id ="creditDtlsDivId" style="display: none;">	
			  		<table class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="0px">
			<tr><td>
				<bDtl:onlnCltDtl crNo="${ipdBillManagementTransBean.strCrNo}" ></bDtl:onlnCltDtl>
			</td></tr>
		</table>
					
	</div>

 <table class="TABLEWIDTH" border="0" align="center" >
    <tr> 
    
    <td width="25%" class="LABEL">Present Account Status: </td>
    <td width="25%" class="CONTROL"><bean:write name="ipdBillManagementTransBean" property="strPresentAcctStatus"/>
    <input type="hidden" name="strPresentAcctStatus" value="${ipdBillManagementTransBean.strPresentAcctStatus}" class="txtFldMax" maxlength="20" onkeypress="return validateData(event,5);" >
    		
     </td>   
    
    <td width="25%" class="LABEL"><font color="red">*</font>New Account Status:</td>
    <td width="25%" class="CONTROL">   
    <select name="strNewAcctStatus" class="comboNormal" >
         <bean:write name="ipdBillManagementTransBean" property="strAccStatusCombo" filter="false"/>
    </select>
    
    </td> 
       </tr>
 </table>
 
  <table class="TABLEWIDTH" border="0" align="center" >
  <tr> 
    <td width="25%" class="LABEL"><font color="red">*</font>Approval By</td>
    <td width="25%" class="CONTROL">
       <select name="strApprovedByCombo" class="comboBig">
          <bean:write name="ipdBillManagementTransBean" property="strApprovedByCombo" filter="false"/>
       </select>
    </td> 
    </tr>
    <tr>
    <td  width="25%" class="LABEL"><font color="red">*</font>Remarks</td>
    
    <td  width="25%" class="CONTROL">
        <select name="dr" class="comboNormal" onChange="groupComboPartPayment();">
             <bean:write name="ipdBillManagementTransBean" property="strRemarksCombo" filter="false"/>
             <option value="0">Others</option>
        </select>
        <input name="strRemarksCombo2" type="text"  class="txtFldMax"  value="" >
        <div id="dummy" style="display: none;">
         <input name="str" type="text"  class="txtFldMax"  value="" >
         </div>
        </td> 
   </tr>
  </table>
 
  <table class="TABLEWIDTH" border="0" align="center" >
  <tr class="FOOTER"> 
    <td colspan="4"><font size="2" color="red">*</font> Mandatory Fields</td>
  </tr>
  </table>	
  	
  <table border="0" class="TABLEWIDTH" align="center">
		<tr>
			<br>
			<td align="right"><!-- <img style="cursor:pointer;cursor:hand"  src="../../hisglobal/images/btn-sv.png" onclick=" return goFuncUpdateAcctStatus();"> -->
			<a href="#" class="button"	onclick="goFuncUpdateAcctStatus();"><span class="save">Save</span></a> </td>
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
	
   <cmbPers:cmbPers/>
   
</html:form>
<tag:autoIndex></tag:autoIndex> 
</body>
</html>