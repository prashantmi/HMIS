<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>
<%@taglib uri="/WEB-INF/dateTag.tld" prefix="date"%>
<%@taglib uri="/WEB-INF/patientDtl.tld" prefix="pDtl"%>
<%@ taglib uri="/WEB-INF/crNo.tld" prefix="crNo"%>
<%@ taglib uri="/WEB-INF/comboPersistence.tld" prefix="cmbPers"%>

<html>
<head>
<meta charset=utf-8><title>Client Approval Details</title>

<link href="../css/transaction.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/calendar-tas.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/popup.css" rel="stylesheet" type="text/css">

<script language="JavaScript" src="../../hisglobal/transactionutil/js/master.js"></script>
<script language="JavaScript" src="../../hisglobal/js/tab.js"></script>
<script language="JavaScript" src="../../hisglobal/js/calendar.js"></script>
<script language="JavaScript" src="../../hisglobal/js/util.js"></script>
<script language="JavaScript" src="../../hisglobal/js/validation.js"></script>
<script language="JavaScript" src="../../hisglobal/js/multirow.js"></script>
<script language="Javascript" src="../../hisglobal/js/popup.js"></script>
<script language="Javascript" src="../../billing/js/clientapproval_trans.js"></script>
<script language="Javascript" src="../../hisglobal/js/util.js"></script>
<script language="Javascript" src="../../hisglobal/js/dropdown.js"></script>
<script language="Javascript" src="../../billing/js/tariffSearch.js"></script>
<script language="Javascript" src="../../billing/js/billing.js"></script>



</head>

<body onload="onLoadLogicsReapproval();"> 
<html:form action="/transactions/ClientApprovalDetailsTransCNT.cnt"  method="post">

<div id ="errMsg" class="errMsg">    <bean:write name="clientApprovalDetailTransBean" property="strErrMsg"/></div>
<div id ="warningMsg" class="warningMsg"><bean:write name="clientApprovalDetailTransBean" property="strWarning"/></div>
<div id ="normalMsg" class="normalMsg"> <bean:write name="clientApprovalDetailTransBean" property="strMsg"/></div>


<tag:tab tabLabel="Client Approval Details View" selectedTab="FIRST" align="center" width="TABLEWIDTH"></tag:tab>
	

 <table class="TABLEWIDTH" align="center">
  <tr class="HEADER"> 
    <td colspan="4">Client Approval Details &gt;&gt;View</td>
  </tr>
 
  <tr> 
    <td width="25%" class="LABEL"><b>CR No.</b></td>
    <td colspan="3" class="CONTROL">
    <crNo:crNo
						id="strCrNoId"
						value="${clientApprovalDetailTransBean.strCrNo}"
						></crNo:crNo>   
   </td>   
  </tr>
</table> 
  
      <table class="TABLEWIDTH" align="center" cellspacing ="1px">
      <tr> 
      <td colspan="3" class="TITLE"><b>Patient Detail</b></td> 
   </tr>
 </table>
 <div id = "detailsdivid3" style="display:block;">
 <table class="TABLEWIDTH" align="center" cellspacing ="1px">
  <tr>
           <bean:write name="clientApprovalDetailTransBean" property="strPatientDetailsView" filter="false"/>
  </tr>  
 </table>
 </div>
 
 <table class="TABLEWIDTH" align="center" cellspacing ="1px">
 <tr> 
    <td colspan="4" class="TITLE">Client Details</td>
  </tr>
   <tr> 
    <td width="25%" class="LABEL">Client Name</td>
    <td width="25%" class="CONTROL">       
              
       <bean:write name="clientApprovalDetailTransBean" property="strClientName" filter="false"/>
       <input type="hidden" class="txtFldMax" name="strClientPatNo" value="${clientApprovalDetailTransBean.strClientPatNo}">
    </td>   
    <td width="25%" class="LABEL"></td>
    <td width="25%" class="LABEL"></td>  
   </tr>
   
   
   
     
   <tr> 
    <td width="25%" class="LABEL">Registration No</td>
    <td width="25%" class="CONTROL"><bean:write name="clientApprovalDetailTransBean" property="strRegistrationNo" filter="false"/></td>   
    <td width="25%" class="LABEL">Client Type</td>
    <td width="25%" class="CONTROL"><bean:write name="clientApprovalDetailTransBean" property="strClientType" filter="false"/></td>   
   </tr>
   <tr> 
    <td width="25%" class="LABEL">Address</td>
    <td width="25%" class="CONTROL"><bean:write name="clientApprovalDetailTransBean" property="strClientAddress" filter="false"/></td>
    <td width="25%" class="LABEL">Contact Person</td>
    <td width="25%" class="CONTROL"><bean:write name="clientApprovalDetailTransBean" property="strContactPerson" filter="false"/></td>  
   </tr>
   <tr> 
    <td width="25%" class="LABEL">Email</td>
    <td width="25%" class="CONTROL"><bean:write name="clientApprovalDetailTransBean" property="strEmailId" filter="false"/></td>        
    <td width="25%" class="LABEL">Contact No</td>
    <td width="25%" class="CONTROL"><bean:write name="clientApprovalDetailTransBean" property="strContactNo" filter="false"/></td>   
   </tr>
  
    <tr> 
    <td colspan="4" class="TITLE">Card Details</td>
  </tr>
  <tr> 
    <td width="25%" class="LABEL">Card No. </td>
    <td width="25%" class="CONTROL"><bean:write name="clientApprovalDetailTransBean" property="strCardNumber" filter="false"/></td> 
    <td width="25%" class="LABEL">Card Holder Name </td>
    <td width="25%" class="CONTROL"><bean:write name="clientApprovalDetailTransBean" property="strCardHolderName" filter="false"/></td>
  </tr>
  <tr> 
    <td width="25%" class="LABEL">Card Expiry Date </td>
    <td width="25%" class="CONTROL"><bean:write name="clientApprovalDetailTransBean" property="strCardExpiryDate" filter="false"/></td>
    <td width="25%" class="LABEL" colspan="2"> </td>
  </tr>
  <tr> 
      <td colspan="4" class="TITLE">Approval Details</td>
   </tr>
  
   <tr> 
  <td width="25%" class="LABEL">Approval For</td>
      <td width="25%" class="LABEL" align="center" >
	    <input type="checkbox"  name ="strOPD" disabled="disabled" > OPD
	    </td>
        <td width="25%" class="LABEL" align="center" >
         <input type="checkbox"  name ="strIPD" disabled="disabled">IPD 
        </td>
        <td width="25%" class="LABEL" align="center" >
         <input type="checkbox"  name ="strEME" disabled="disabled">Emergency  
       </td> 
	 </tr>
	
   <tr> 
	 <td width="25%" class="LABEL">Auth No</td>
     <td width="25%" class="CONTROL"><bean:write name="clientApprovalDetailTransBean" property="strAuthenticationNumber" filter="false"/></td>   
     <td width="25%" class="LABEL">Auth Date</td>
     <td width="25%" class="CONTROL"><bean:write name="clientApprovalDetailTransBean" property="strAuthenticationDate" filter="false"/></td>
   </tr>
   <tr> 
     <td width="25%" class="LABEL">Expiry Date </td>
     <td width="25%" class="CONTROL"><bean:write name="clientApprovalDetailTransBean" property="strExpiryDate" filter="false"/></td>   
     <td width="25%" class="LABEL">Total Sanction Amt</td>
     <td width="25%" class="CONTROL"><bean:write name="clientApprovalDetailTransBean" property="strSanctionAmount" filter="false"/></td>   
   </tr>
     <tr> 
    <td width="25%" colspan="1" class="LABEL">Approval Only For One Time</td>
     <td width="25%" colspan="3" class="CONTROL" align="center" >
	    <html:checkbox name="clientApprovalDetailTransBean" property="strOneTimeService" value="1" disabled="true"/>    
	    </td>
	 </tr>
	 <tr> 
      <td colspan="4" class="TITLE"></td>
   </tr>
  </table>
  <table class="TABLEWIDTH" align="center"  border="0" cellspacing ="1px">
  <tr>
            <bean:write name="clientApprovalDetailTransBean" property="strViewDtl" filter="false"/>
  </tr>
 </table>
  
 <table class="TABLEWIDTH" align="center" cellspacing ="1px">     
    <tr class="FOOTER"> 
    <td colspan="4"><font size="2" color="red">*</font> Mandatory Fields</td>
  </tr>
 </table>
 
   <table border="0" width=100% align="center">
		<tr>
			 <td align="center">
			  <img style="cursor:pointer;cursor:hand;" title="Click to Go back List Page" src="../../hisglobal/images/btn-ccl.png" onClick="cancel('LIST');" >
			</td>
		</tr>
   </table>	
	
	

 <input type="hidden" name="hmode">
 <input type="hidden" name="strChk" value="${clientApprovalDetailTransBean.strChk}">
 <input type="hidden" name="strMsgType" value="${clientApprovalDetailTransBean.strMsgType}">
 <input type="hidden" name="strApprovalStatus" value="${clientApprovalDetailTransBean.strApprovalStatus}">
 
 <input type="hidden" name="OPD" value="${clientApprovalDetailTransBean.OPD}"/>   
    <input type="hidden" name="IPD" value="${clientApprovalDetailTransBean.IPD}"/>  
    <input type="hidden" name="EME" value="${clientApprovalDetailTransBean.EME}"/>    
   <cmbPers:cmbPers/>
</html:form>
  <tag:autoIndex></tag:autoIndex> 
</body>
</html>