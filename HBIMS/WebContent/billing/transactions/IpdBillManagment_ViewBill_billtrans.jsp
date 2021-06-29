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
<%@ taglib uri="/WEB-INF/onlineClientDtl.tld" prefix="bDtl"%>

<%@ taglib uri="/WEB-INF/comboPersistence.tld" prefix="cmbPers"%>
<html>
<head><meta charset=utf-8>
<title>IPD Bill Management</title>
<link href="../css/transaction.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/calendar-tas.css" rel="stylesheet"	type="text/css">
<link href="../../hisglobal/css/popup.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/buttons.css" rel="stylesheet" type="text/css">
<script language="Javascript" src="../../hisglobal/js/validation.js"></script>
<script language="JavaScript" src="../../hisglobal/masterutil/js/master.js"></script>
<script language="JavaScript" src="../../hisglobal/js/tab.js"></script>
<script language="JavaScript" src="../../hisglobal/js/calendar.js"></script>
<script language="Javascript" src="../../hisglobal/js/multirow.js"></script>
<script language="Javascript" src="../../hisglobal/js/datepicker1.js"></script>
 <script language="Javascript" src="../../hisglobal/js/util.js"></script>
<script language="Javascript" src="../../hisglobal/js/popup.js"></script>
<script language="Javascript" src="../../billing/js/IpdBillMangTrans.js"></script> 
</head>
<body onload="resetParticulatDtlsView(),desableCrNo();">
<html:form action="transactions/IpdBillManagementTransCNT.cnt" method="post">
 
     <div id="errMsg" class="errMsg"><bean:write name="ipdBillManagementTransBean" property="strErrMsg"/></div> 
   	 <div id="warningMsg" class="warningMsg"><bean:write name="ipdBillManagementTransBean" property="strWarning"/></div>
	 <div id="normalMsg" class="normalMsg"><bean:write  name="ipdBillManagementTransBean" property="strMsg"/></div>
 
 <div class='popup' id='tariffDtl' style="display:none">
	<table width='400' border="0" cellpadding="1px" cellspacing ="1px">
		<tr class="HEADER"> 
			<th colspan='3' align='left'>&nbsp;Tariff Details</th>
			<th align='right'><img  style='cursor:pointer;cursor:hand' src='../../hisglobal/images/stop.png'
				onClick="hidePayDetails('tariffDtl');"></th>
	    </tr>
	 </table>   
	 <table  width='400' border="0" cellpadding="1px" cellspacing ="1px">
		<tr>
			<td width="25%" class="LABEL">Rate/Unit</td>
			<td width="25%" class="CONTROL"><div id ='1'></div></td>
			<td width="25%" class="LABEL">Total Discount</td>
			<td width="25%" class="CONTROL"><div id ='2'></div></td>
		</tr>
		<tr>
			<td width="25%" class="LABEL">Total Service Tax</td>
		   	<td width="25%" class="CONTROL"><div id ='3'></div></td>
		  	<td width="25%" class="LABEL">Total Penalty</td>
			<td width="25%" class="CONTROL"><div id ='4'></div></td>
		</tr>
        </table>
	</div>
  <div id="ipdhide"> 
 <!--<tag:tab tabLabel="View Bill" selectedTab="FIRST" align="center" width="TABLEWIDTH">
	</tag:tab>-->
	<tag:tab tabList="${ipdBillManagementTransBean.lhm}" selectedTab="VIEWBILL" align="center"
	width="TABLEWIDTH"></tag:tab>
	</div>
 <table class="TABLEWIDTH" align="center" border="0" cellpadding="1px" cellspacing ="1px">
  <tr class="HEADER">  
    <td colspan="4">View&gt;&gt;Bill</td>
  </tr>
  
 <tr> 
    <td width="50%" colspan="2" class="LABEL">CR No.</td>
    <td colspan="2" width="50%" class="CONTROL"><bean:write name="ipdBillManagementTransBean" property="strCrNo" />
    </td>   
  
  </tr>
  </table>
 
  <jsp:include page="IpdBillMgmtHeaderNew.jsp"/>
   
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
	
  <table class="TABLEWIDTH" align="center"  border="0" cellpadding="1px" cellspacing ="1px">
  <tr>
            <bean:write name="ipdBillManagementTransBean" property="strParticulaDtl" filter="false"/>
  </tr>
 </table>
 <div id="detailhide">
  <table class="TABLEWIDTH" align="center" border="0" cellpadding="1px" cellspacing ="1px">
  	<tr> 
    <td width="50%" class="LABEL"><font color="red">*</font>Bill Type:</td>
    <td width="50%" class="CONTROL">
         <select name="strBillTypeCombo" class='comboNormal' id="strBillTypeCombo"><option value="0">Select Value</option><option value="1">Consolidated</option><option selected="selected"  value="2">Detailed</option></select>
     </td>    
    </table>
  <table class="TABLEWIDTH" border="0" align="center"  border="0" cellspacing ="1px">
 <tr class="HEADER"> 
    <td colspan="4"><font size="2" color="red">*</font> Mandatory Fields</td>
  </tr>
  </table>	
   	
  <table border="0" class="TABLEWIDTH" align="center">
		<tr>
			<!-- <td align="right"><img style="cursor:hand;cursor:pointer;cursor:hand" name="save"  src="../../hisglobal/images/btn-view.png" onClick="validate();"></td>
			<td align="left"><img  style="cursor:hand;cursor:pointer;cursor:hand" src="../../hisglobal/images/btn-ccl.png" onClick="cancel1('LIST');"></td> -->
			<br>
			 <td align="right"><a href="#" class="button"	onclick="validate();"><span class="view">View</span></a></td> 
			<td align="left"><a href="#" class="button" onclick="cancel1('LIST')"><span class="cancel">Cancel</span></a></td>
		</tr>
	</table>
</div>
	<input type="hidden" name="hmode">
	<input type="hidden" name="strChk" value="${ipdBillManagementTransBean.strChk}">
	<input type="hidden" name="strSeatId" value="${ipdBillManagementTransBean.strSeatId}">
	<input type="hidden" name="strVal" value="${ipdBillManagementTransBean.strVal}">
	
		<input type="hidden" name="searchColumn" value='<%=request.getParameter("searchColumn") %>' />
	<input type="hidden" name="search" value='<%=request.getParameter("search") %>' />
	<input type="hidden" name="blockNo" value='<%=request.getParameter("blockNo") %>' />
	<input type="hidden" name="prevNext" value='<%=request.getParameter("prevNext") %>' />
	<input type="hidden" name="rowNum" value='<%=request.getParameter("rowNum") %>'/>
	<input type="hidden" name="divisionId" value='<%=request.getParameter("divisionId") %>' />
	<input type="hidden" name="isDesk" value='<%=request.getParameter("isDesk") %>' />
	<input type="hidden" name="selectedTab">
	<input type="hidden" name="strIsCalledFromIpdBillNew" value="${ipdBillManagementTransBean.strIsCalledFromIpdBillNew}" />
    <input type="hidden" name="strAcctStatMode" value="${ipdBillManagementTransBean.strAcctStatMode}" />
	
   <cmbPers:cmbPers/>
</html:form>
<tag:autoIndex></tag:autoIndex>  
</body>
</html>