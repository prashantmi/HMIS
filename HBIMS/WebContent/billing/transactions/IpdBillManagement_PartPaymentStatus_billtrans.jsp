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
<script language="Javascript" src="../../billing/js/IpdBillMangTrans.js"></script>
</head>
<body onload='setInitValues();setPartPaymentAmount();desableCrNo();'>
<html:form action="transactions/IpdBillManagementTransCNT.cnt" method="post">
     
     <div id ="errMsg" class="errMsg"><bean:write name="ipdBillManagementTransBean" property="strErrMsg"/></div> 
     <div class="warningMsg"><bean:write name="ipdBillManagementTransBean" property="strWarning"/></div>
	 <div id="normalMsg" class="normalMsg"><bean:write  name="ipdBillManagementTransBean" property="strMsg"/></div>
 
 <tag:tab tabLabel="Part Payment Request" selectedTab="FIRST" align="center" width="TABLEWIDTH">
	</tag:tab>
 <table class="TABLEWIDTH" align="center"  border="0" cellspacing ="1px">
  <tr class="HEADER"> 
    <td colspan="4">IpdBillManagement&gt;&gt;PartPaymentRequest</td>
  </tr>
  
  <tr> 
    <td width="50%" colspan="2" class="LABEL">CR No.</td>
    <td colspan="2" width="50%" class="CONTROL"><bean:write name="ipdBillManagementTransBean" property="strCrNo" />
    </td>   
  </tr>
  </table>
 
  <jsp:include page="IpdBillMgmtHeader.jsp"/>
   
 <table class="TABLEWIDTH" align="center"  border="0" cellspacing ="1px">
  <tr> 
    <td width="5%" class="TITLE" align="center"><input type='hidden' name='button4' value="1">
    <img src="../../hisglobal/images/plus.gif" height="15" width="15"  id="plus4" style="display:block;cursor:pointer;cursor:hand" onClick="ftn44()">
    <img src="../../hisglobal/images/minus.gif"  id="minus4" style="display:none;cursor:pointer;cursor:hand" onClick="ftn44()"></td>
    <td colspan="3" class="TITLE"><b>Pending Request</b></td>   
  </tr>
 </table>
  <div id = "detailsdivid4" style="display:none;">
  <table class="TABLEWIDTH" align="center"  border="0" cellspacing ="1px">
  <tr>
            <bean:write name="ipdBillManagementTransBean" property="strPendingPartPaymentReq" filter="false"/>
  </tr>
 </table>
 </div>
 
 <table class="TABLEWIDTH" align="center" border="0" cellpadding="1px" cellspacing ="1px">
<tr>
<td colspan="4"  class="TITLE" >Part Payment Details</td>
</tr>
                  <tr>
                   <td class="LABEL" width="25%">Charge Type</td> 
                  <td class="CONTROL" width="25%"> <input type="hidden" name="strWardType"  value="${ipdBillManagementTransBean.strWardType }"> 
                     <bean:write  name="ipdBillManagementTransBean" property="strWardName" />             
                  
                  </td>
                                   
                  <td class="LABEL" width="25%"><font color="red">*</font>Treatment Category</td>
                  <td class="CONTROL" width="25%"><select class="comboNormal" name="strNewTreatmentCategory"  onchange="setPartPaymentAmount();" >
                  <bean:write name="ipdBillManagementTransBean" property="strTreatmentCategoryValues" filter="false"   />
                  </select> </td>
                  </tr>
                  </table>


 
 <table class="TABLEWIDTH" align="center"  border="0" cellspacing ="1px">
 	<tr> 
    <td width="25%" class="LABEL"><font color="red">*</font>Part Payment Amount:</td>
    <td width="25%" class="CONTROL"><input type="hidden" name="strdummypartpayment" value="${ipdBillManagementTransBean.strPartPaymentAmt}"><input name="strPartpayment" type="text"  class="txtFldMin"  value="${ipdBillManagementTransBean.strPartPaymentAmt}"  disabled="disabled" onkeypress="return validateData(event,7);">&nbsp;Rs</td>
    <td width="50%" class="LABEL" >
       <div id="id11" style="display:block">
       <table class="TABLEWIDTH" align="center">
       <tr> 
       <td width="25%" class="LABEL">Update:</td>
       <td width="25%" class="CONTROL"><input type='checkbox' name='strChk_value' onClick="ftnTick()"></td>
       </tr>
       </table>
      </div>
     </td> 
    </tr>
      
  </table>
 <div id="combo" style="display:none">	
 <table class="TABLEWIDTH" align="center"  border="0" cellspacing ="1px">
  <tr> 
    <td width="50%" class="LABEL"><font color="red">*</font>Approval By</td>
    <td width="50%" class="CONTROL">
       <select name="strApprovedByCombo" class="comboBig">
          <bean:write name="ipdBillManagementTransBean" property="strApprovedByCombo" filter="false"/>
       </select>
    </td> 
    
   </tr>
   <tr>
   <td  width="50%" class="LABEL">Remarks</td>
    
    <td  width="50%" class="CONTROL">
        <select name="dr" class="comboNormal" onChange="groupComboPartPayment();">
             <bean:write name="ipdBillManagementTransBean" property="strRemarksCombo" filter="false"/>
             <option value="0">Others</option>
        </select>
        <input name="strRemarksCombo2" type="text"  class="txtFldMax"  value="" disabled="disabled"></td> 
   </tr>
  </table>
 </div>
 
 <div id="a1" style="display:none">	
 <table class="TABLEWIDTH" border="0" align="center" >
   <tr class="FOOTER"> 
    <td colspan="4"><font size="2" color="red">*</font> Mandatory Fields</td>
  </tr>
  </table>
 </div>
 
 
 
  <table class="TABLEWIDTH" align="center"  border="0" cellspacing ="1px">
  <tr class="FOOTER">  
    <td colspan="4"><font size="2" color="red">*</font> Mandatory Fields</td>
  </tr>
  </table>	
  	
  <table border="0" class="TABLEWIDTH" align="center">
		<tr>
		<br>
     		<!-- <td align="right"><img style="cursor:pointer;cursor:hand"  src="../../hisglobal/images/btn-sv.png" onClick="return goFunctionPartPayment();"></td>
			<td align="left"><img style="cursor:pointer;cursor:hand" src="../../hisglobal/images/btn-ccl.png" onClick="cancel1('LIST');"></td>-->
			<td align="right"><a href="#" class="button" id="" onclick=' return goFunctionPartPayment();'><span class="save">Save</span></a></td>
			<td align="left"><a href="#" class="button" onclick="cancel1('LIST');"><span class="cancel">Cancel</span></a></td>
		</tr>
	</table>
	<input type="hidden" name="hmode">
	<input type="hidden" name="strChk" value="${ipdBillManagementTransBean.strChk}">
	<input type="hidden" name="strDeptId" value="${ipdBillManagementTransBean.strDeptId}">
	<input type="hidden" name="strDeptCode" value="${ipdBillManagementTransBean.strDeptCode}">
	<input type="hidden" name="strWardCode" value="${ipdBillManagementTransBean.strWardCode}">
	<input type="hidden" name="strSeatId" value="${ipdBillManagementTransBean.strSeatId}">
	
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