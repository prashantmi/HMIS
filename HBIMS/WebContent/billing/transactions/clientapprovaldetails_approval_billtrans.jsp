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
<meta charset=utf-8>
<title>Client Approval Details</title>

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

<body onload="onLoadLogics();">
<html:form action="/transactions/ClientApprovalDetailsTransCNT.cnt"  method="post">

<div id ="errMsg" class="errMsg">    <bean:write name="clientApprovalDetailTransBean" property="strErrMsg"/></div>
<div id ="warningMsg" class="warningMsg"><bean:write name="clientApprovalDetailTransBean" property="strWarning"/></div>
<div id ="normalMsg" class="normalMsg"> <bean:write name="clientApprovalDetailTransBean" property="strMsg"/></div>


<tag:tab tabLabel="Approve Client Approval Details" selectedTab="FIRST" align="center" width="TABLEWIDTH"></tag:tab>
	

 <table class="TABLEWIDTH" align="center">
  <tr class="HEADER"> 
    <td colspan="4">Client Approval Details &gt;&gt;Approval</td>
  </tr>
 
  <tr> 
    <td width="25%" class="LABEL"><font color="red">*</font><b>CR No.</b></td>
    <td colspan="3" class="CONTROL">
    <crNo:crNo
						id="strCrNo2Id" name="strCrNo"
						value="${clientApprovalDetailTransBean.strCrNo}"
						js="onkeypress='return initGoFunc(event);'"></crNo:crNo>
    <input type="image" title="Click to Get Details" src="../../hisglobal/images/Go.png" name="go" value="Go" onClick="return goFuncClientApproval();" onKeyPress="return goFuncClientApproval();">
    
   </td>   
  </tr>
</table> 
  <div id = "approvalid" style="display:none">
  <div id="patDtl">
      <table class="TABLEWIDTH" align="center" cellspacing ="1px">
      <tr> 
      
      <td colspan="3" class="TITLE"><b>Patient Detail</b></td> 
   </tr>
 </table>
 </div>
 <div id = "detailsdivid3" >
 <table class="TABLEWIDTH" align="center" cellspacing ="1px">
  <tr>
           <bean:write name="clientApprovalDetailTransBean" property="strPatientDetailsView" filter="false"/>
  </tr>  
 </table>
 </div>
 
 <div id="divFromClientDetails" >
 <table class="TABLEWIDTH" align="center" cellspacing ="1px">
 <tr> 
    <td colspan="4" class="TITLE">Client Details</td>
  </tr>
   <tr> 
    <td width="25%" class="LABEL"><font color="red">*</font>Client Name</td>
    <td width="25%" class="CONTROL">
         <select name="strClientName" class="comboNormal" onChange="getClientDtl(this);">
                <bean:write name="clientApprovalDetailTransBean" property="strClientName" filter="false"/>
         </select>
    </td>   
    <td width="25%" class="LABEL"></td>
    <td width="25%" class="LABEL"></td>  
   </tr>
   </table>
   
   <div id = "cltdtlid" style="display:none;">
     <table class="TABLEWIDTH" align="center" cellspacing ="1px">
   <tr> 
    
    <td width="25%" class="LABEL">Registration No</td>
    <td width="25%" class="CONTROL"><div id='strRegistrationNo'></div></td>   
    <td width="25%" class="LABEL">Client Type</td>
    <td width="25%" class="CONTROL"><div id='strClientType'></div></td>   
   </tr>
   <tr> 
    <td width="25%" class="LABEL">Address</td>
    <td width="25%" class="CONTROL" nowrap="nowrap"><div id='strClientAddress'></div></td>      
    <td width="25%" class="LABEL">Contact Person</td>
    <td width="25%" class="CONTROL"><div id='strContactPerson'></div></td>   
   </tr>
   <tr> 
    <td width="25%" class="LABEL">Email</td>
    <td width="25%" class="CONTROL"><div id='strEmailId'></div></td>      
    <td width="25%" class="LABEL">Contact No</td>
    <td width="25%" class="CONTROL"><div id='strContactNo'></div></td>   
   </tr>
   </table>
   </div>
   
    <table class="TABLEWIDTH" align="center" cellspacing ="1px">
    <tr> 
    <td colspan="4" class="TITLE">Card Details</td>
  </tr>
  <tr> 
    <td width="25%" class="LABEL"><font color="red">*</font>Card No. </td>
    <td width="25%" class="CONTROL"> <input type="text" name="strCardNumber" class="txtFldMax" maxlength="20" onkeypress="return validateData(event,8);"> </td>   
    <td width="25%" class="LABEL"><font color="red">*</font>Card Holder Name </td>
    <td width="25%" class="CONTROL"><input type="text" name="strCardHolderName" maxlength="100" class="txtFldMax" onkeypress="return validateData(event,4);"></td>   
   </tr>
    <tr> 
    <td width="25%" class="LABEL">Card Expiry Date </td>
    <td width="25%" class="CONTROL"><date:date name="strCardExpiryDate" value="${clientApprovalDetailTransBean.strCtDate}"></date:date></td>   
    <td width="25%" class="LABEL" colspan="2"> </td>
    
   </tr>
   <tr> 
    <td colspan="4" class="TITLE">Approval Details</td>
  </tr>
  
   <tr> 
    <td width="25%" class="LABEL"><font color="red">*</font>Approval For</td>
    
	 
	    <td width="25%" class="LABEL" align="center" >
	    <html:checkbox name="clientApprovalDetailTransBean" property="OPD" value="1"  />OPD 
	    </td>
       <td width="25%" class="LABEL" align="center" >
	    <html:checkbox name="clientApprovalDetailTransBean" property="IPD" value="1" />IPD 
	    </td>
        <td width="25%" class="LABEL" align="center" >
        <html:checkbox name="clientApprovalDetailTransBean" property="EME" value="1" />Emergency 
       </td>
      
	 </tr>
   </table>
   
   <table class="TABLEWIDTH" align="center" cellspacing ="1px">
     
	  <tr> 
	  
    <td width="25%" class="LABEL"><font color="red">*</font>Auth No</td>
    <td width="25%" class="CONTROL"><input type="text" name="strAuthenticationNumber" class="txtFldMax" maxlength="20" onkeypress="return validateData(event,8);"> </td>   
    <td width="25%" class="LABEL"><font color="red">*</font>Auth Date</td>
    <td width="25%" class="CONTROL"><date:date name="strAuthenticationDate" value="${clientApprovalDetailTransBean.strCtDate}"></date:date></td>
   </tr>
    <tr> 
    <td width="25%" class="LABEL">Expiry Date </td>
    <td width="25%" class="CONTROL"><date:date name="strExpiryDate" value="${clientApprovalDetailTransBean.strCtDate}"></date:date></td>   
    <td width="25%" class="LABEL"><font color="red">*</font>Sanction Amt</td>
    <td width="25%" class="CONTROL"><input type="text" name="strSanctionAmount" maxlength="10" class="txtFldMax" onkeypress="return validateData(event,7);"></td>   

   </tr>
    <tr> 
    <td width="25%" colspan="1" class="LABEL">Approval Only For One Time</td>
     <td width="25%" colspan="3" class="CONTROL" align="center" >
	    <html:checkbox name="clientApprovalDetailTransBean" property="strOneTimeService" value="1"/>
	    </td>
	 </tr>
   </table>
   
      
   
  <div id="Show" style="display:none;">
                 
                </div>
   
   </div>
   
      
   
</div>
 <table class="TABLEWIDTH" align="center" cellspacing ="1px">     
    <tr class="FOOTER"> 
    <td ><font size="2" color="red">*</font> Mandatory Fields</td>
  </tr>
 </table>
 
 <div id="buttons1" align="center" style="display:block">
   <table border="0" class="TABLEWIDTH" align="center" cellspacing ="1px">
		<tr>
			<td align="center">
			<img style="cursor:pointer;cursor:hand;" title="Click to Save Data" src="../../hisglobal/images/btn-sv.png" onClick="return ClientApprovalSave();">
			<img style="cursor:pointer;cursor:hand;" title="Click to Clear Data" src="../../hisglobal/images/btn-clr.png" onclick="ClearPage();" >
			 <img style="cursor:pointer;cursor:hand;" title="Click to Go back List Page" src="../../hisglobal/images/btn-ccl.png" onClick="cancel('LIST');" >
			 
			 </td>
			
		</tr>
   </table>	
   </div>
   
   <div id="buttons2" align="center" style="display:none">
	<table border="0" class="TABLEWIDTH" align="center" cellspacing ="1px">
		<tr>
			<td align="center">
			 <img style="cursor:pointer;cursor:hand;" title="Click to Go back List Page" src="../../hisglobal/images/btn-ccl.png" onClick="cancel('LIST');" >
			
			 </td>
			
		</tr>
   </table>	
	</div>
	
	

 <input type="hidden" name="hmode">
 <input type="hidden" name="strChk" value="${clientApprovalDetailTransBean.strChk}">
 <input type="hidden" name="strClientPatNo" value="${clientApprovalDetailTransBean.strClientPatNo}">
 <input type="hidden" name="strMsgType" value="${clientApprovalDetailTransBean.strMsgType}">
 <input type="hidden" name="strApprovalStatus" value="${clientApprovalDetailTransBean.strApprovalStatus}">
 <input type="hidden" name="strCrNoStatus"  value="${clientApprovalDetailTransBean.strCrNoStatus}"/>  
 
 <input type="hidden" name="strCltNo" id="strCltNo" value=""/>
 <input type="hidden" name="strCrNo2"  value="${clientApprovalDetailTransBean.strCrNo}"/>  
  <input type="hidden" name="strCtDate"  value="${clientApprovalDetailTransBean.strCtDate}"/>
 
 
 
 
   <cmbPers:cmbPers/>
</html:form>
  <tag:autoIndex></tag:autoIndex> 
</body>
</html>