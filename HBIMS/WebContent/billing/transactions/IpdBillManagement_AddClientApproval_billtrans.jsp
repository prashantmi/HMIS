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
<script language="Javascript" src="../../hisglobal/js/validation.js"></script>
<script language="Javascript" src="../../hisglobal/js/util.js"></script>
<script language="Javascript" src="../../hisglobal/transactionutil/js/IpdBillMangTrans.js"></script>
<script language="Javascript" src="../../billing/js/IpdBillMangTrans.js"></script>
</head>
<body onload="desableCrNo();">
<html:form action="transactions/IpdBillManagementTransCNT.cnt" method="post">
   
     <div id="errMsg" class="errMsg"><bean:write     name="ipdBillManagementTransBean" property="strErrMsg"/></div> 
   	 <div id="warningMsg" class="warningMsg"><bean:write name="ipdBillManagementTransBean" property="strWarning"/></div>
	 <div id="normalMsg" class="normalMsg"><bean:write  name="ipdBillManagementTransBean" property="strMsg"/></div>
      
 
 <tag:tab tabLabel="Add Client Approval" selectedTab="FIRST" align="center" width="TABLEWIDTH">
	</tag:tab>
 <table class="TABLEWIDTH" align="center">
  <tr class="HEADER"> 
    <td colspan="4">IpdBillManagement&gt;&gt;Add Client Approval</td>
  </tr>
  
  <tr> 
    <td width="25%" class="LABEL"><font color="red">*</font>CR No.</td>
    <td colspan="3" class="CONTROL">
      <crNo:crNo
						id="strCrNoId" name="strCrNo"
						value="${ipdBillManagementTransBean.strCrNo}"
						js="onkeypress='return initGoFunc(event);'"></crNo:crNo>
   
    </td>   
  </tr>
  </table>
 
  <jsp:include page="IpdBillMgmtHeaderAddCltApproval.jsp"/>
    	
 <table border="0" class="TABLEWIDTH" align="center">
		<tr>
	<br>
			<!-- <td align="right"><img style="cursor:pointer;cursor:hand"  src="../../hisglobal/images/btn-sv.png" onClick="return goFunctionAddClientApproval();"></td>
			<td align="left"><img style="cursor:pointer;cursor:hand" src="../../hisglobal/images/btn-ccl.png" onClick="cancel1('LIST');"></td>-->
			
			<td align="right"><a href="#" class="button" id="" onclick=' return goFunctionAddClientApproval();'><span class="save">Save</span></a></td>
			<td align="left"><a href="#" class="button" onclick="cancel1('LIST');"><span class="cancel">Cancel</span></a></td>
			
		</tr>
	</table>
	   <input type="hidden" name="chk">
	   <input type="hidden" name="hmode">
	   	<input type="hidden" name="searchColumn" value='<%=request.getParameter("searchColumn") %>' />
	<input type="hidden" name="search" value='<%=request.getParameter("search") %>' />
	<input type="hidden" name="blockNo" value='<%=request.getParameter("blockNo") %>' />
	<input type="hidden" name="prevNext" value='<%=request.getParameter("prevNext") %>' />
	<input type="hidden" name="rowNum" value='<%=request.getParameter("rowNum") %>'/>
	<input type="hidden" name="divisionId" value='<%=request.getParameter("divisionId") %>' />
  <cmbPers:cmbPers/>	   
</html:form>
<tag:autoIndex></tag:autoIndex>  
</body>
</html>