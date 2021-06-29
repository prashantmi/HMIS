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
<meta charset=utf-8><title>Client Re-Approval Details</title>

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
<script type="text/javascript">  
function ClientReApprovalSave() 
	{  
	
	if(document.forms[0].strOPD.checked==true)
	{
	// alert("opd checked"); 
		document.forms[0].OPD.value="1"; 
		
	}
	if(document.forms[0].strIPD.checked==true)
	{
		document.forms[0].IPD.value="1";
	}
	if(document.forms[0].strEME.checked==true)
	{
		document.forms[0].EME.value="1";
	}
	   //  alert("Date is::"+document.forms[0].strNewAuthenticationDate.value);
	    document.forms[0].strCrNo.disabled = false;
	   //  alert("document.forms[0].strCardNumber.value"+document.forms[0].strCardNumber.value);  
	    var hisValidator = new HISValidator("clientApprovalDetailTransBean");  
	    hisValidator.addValidation("strNewAuthenticationNumber", "req", "Approval Authentication No is a Mandatory Field" );
	    hisValidator.addValidation("strNewAuthenticationDate", "req", "Approval Authentication Date is a Mandatory Field" );
     	hisValidator.addValidation("strNewAuthenticationDate", "dtltet=${clientApprovalDetailTransBean.strCtDate}" ,"Authentication Date Should be less than or Equal to Current Date");
     	 
     	
     	if(document.forms[0].strNewExpiryDate.value == ""  )  
		{
		 // alert("exp blank"); 
		}
		else  
		{
		 hisValidator.addValidation("strNewExpiryDate", "dtgtet=${clientApprovalDetailTransBean.strCtDate}" ,"Expiry Date Should be Greater than or Equal to Current Date");
		}
		
	    hisValidator.addValidation("strNewSanctionAmount", "req", "Sanction Amt is a Mandatory Field" );
		hisValidator.addValidation("strNewSanctionAmount", "amount=9,2", "Sanction Amt should be in format 0000000.00" );   
	    var retVal = hisValidator.validate();
		 // alert("retVal::"+retVal);
		  if(parseInt(document.forms[0].strNewSanctionAmount.value) == "0")
		{
			alert("Sanction amount cannot be zero");
			retVal = false;
		}
		
     	if(document.forms[0].strNewAuthenticationDate.value < document.forms[0].strOldAuthenticationDate.value )
     	{
     		alert("New Authentication Date cannot less than Old Authentication Date");
     		retVal = false; 
     	} 
     	
     	if(document.forms[0].strNewAuthenticationNumber.value == document.forms[0].strOldAuthenticationNumber.value )
     	{
     		alert("New Authentication Number cannot be equal to Old Authentication Number"); 
     		retVal = false; 
     	}
		 if(retVal)
		 {
		
		  document.forms[0].hmode.value="REAPPROVALINSERT"; 
	      document.forms[0].submit();
		 }
		 else
		 {
		  return false;
		 }
	 
	  
	}
	
</script>
</head>

<body onload="onLoadLogicsReapproval();"> 
<html:form action="/transactions/ClientApprovalDetailsTransCNT.cnt" method="post">

<div id ="errMsg" class="errMsg"><bean:write name="clientApprovalDetailTransBean" property="strErrMsg"/></div>
<div id ="warningMsg" class="warningMsg"><bean:write name="clientApprovalDetailTransBean" property="strWarning"/></div>
<div id ="normalMsg" class="normalMsg"> <bean:write name="clientApprovalDetailTransBean" property="strMsg"/></div>


 <tag:tab tabLabel="Client Re-Approval Details" selectedTab="FIRST" align="center" width="TABLEWIDTH"></tag:tab>
  <table class="TABLEWIDTH" align="center">
    <tr class="HEADER"> 
      <td colspan="4">Client Approval Details &gt;&gt;Re-Approval</td>
    </tr>
    <tr> 
      <td width="25%" class="LABEL"><font color="red">*</font><b>CR No.</b></td>
      
      <td colspan="3" class="CONTROL">
      
      <crNo:crNo
						id="strCrNoId"
						value="${clientApprovalDetailTransBean.strCrNo}"
						></crNo:crNo>
      
        
   </td>   
  </tr>
</table> 
  <div id = "approvalid" style="display:none">
      <table class="TABLEWIDTH" align="center" cellspacing ="1px">
      <tr> 
       <td colspan="3" class="TITLE"><b>Patient Detail</b></td> 
   </tr>
 </table>
 </div>
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
    <td width="25%" class="LABEL"><font color="red">*</font>Client Name</td>
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
    <td width="25%" class="LABEL">Contact No.</td>
    <td width="25%" class="CONTROL"><bean:write name="clientApprovalDetailTransBean" property="strContactNo" filter="false"/></td>   
   </tr>
  
    <tr> 
    <td colspan="4" class="TITLE">Card Details</td>
  </tr>
  <tr> 
    <td width="25%" class="LABEL"><font color="red">*</font>Card No. </td>
    <td width="25%" class="CONTROL"><bean:write name="clientApprovalDetailTransBean" property="strCardNumber" filter="false"/></td>
  
    <td width="25%" class="LABEL"><font color="red">*</font>Card Holder Name </td>
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
    <td width="25%" class="LABEL"><font color="red">*</font>Approval For</td>
      <td width="25%" class="LABEL" align="center" >
	    <input type="checkbox"  name ="strOPD" > OPD
	    </td>
        <td width="25%" class="LABEL" align="center" >
         <input type="checkbox"  name ="strIPD" >IPD 
        </td>
        <td width="25%" class="LABEL" align="center" >
         <input type="checkbox"  name ="strEME" >Emergency  
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
	 
   </table>
   
   <table class="TABLEWIDTH" align="center" cellspacing ="1px">
    <tr> 
      <td colspan="4" class="TITLE">New Approval Details</td>
    </tr>
      <tr> 
	  
    <td width="25%" class="LABEL"><font color="red">*</font>Auth No</td>
    <td width="25%" class="CONTROL"><input type="text" name="strNewAuthenticationNumber" class="txtFldMax" maxlength="20" onkeypress="return validateData(event,8);"> </td>   
    <td width="25%" class="LABEL"><font color="red">*</font>Auth Date</td>
    <td width="25%" class="CONTROL"><date:date name="strNewAuthenticationDate" value="${clientApprovalDetailTransBean.strCtDate}"></date:date></td>
   </tr>
    <tr> 
    <td width="25%" class="LABEL">Expiry Date </td>
    <td width="25%" class="CONTROL"><date:date name="strNewExpiryDate" value="${clientApprovalDetailTransBean.strCtDate}"></date:date></td>   
    <td width="25%" class="LABEL"><font color="red">*</font>Sanction Amt</td>
    <td width="25%" class="CONTROL"><input type="text" name="strNewSanctionAmount" maxlength="10" class="txtFldMax" onkeypress="return validateData(event,7);"></td>   

    
   </tr>
	
    <tr> 
    <td width="25%" colspan="1" class="LABEL">Approval Only For One Time</td>
     <td width="25%" colspan="3" class="CONTROL" align="center" >
	    <html:checkbox name="clientApprovalDetailTransBean" property="strOneTimeService" value="1" disabled="true"/>    
	    </td>
	 </tr>
    </table>
  
 <table class="TABLEWIDTH" align="center" cellspacing ="1px">     
    <tr class="FOOTER"> 
    <td colspan="4"><font size="2" color="red">*</font> Mandatory Fields</td>
  </tr>
 </table>

   <table border="0" width=100% align="center">
		<tr>
			 <td align="center"><img style="cursor:pointer;cursor:hand;" title="Click to Save Data" src="../../hisglobal/images/btn-sv.png" onClick="return ClientReApprovalSave();">
			  <img style="cursor:pointer;cursor:hand;" title="Click to Go back List Page" src="../../hisglobal/images/btn-ccl.png" onClick="cancel('LIST');" >
			</td>
		</tr>
   </table>	
 
	
	

 <input type="hidden" name="hmode">
 <input type="hidden" name="strChk" value="${clientApprovalDetailTransBean.strChk}">
 <input type="hidden" name="strMsgType" value="${clientApprovalDetailTransBean.strMsgType}"/>
 <input type="hidden" name="strApprovalStatus" value="${clientApprovalDetailTransBean.strApprovalStatus}"/>
   <input type="hidden" name="strCardNumber" value="${clientApprovalDetailTransBean.strCardNumber}"/>  
   <input type="hidden" name="strCardHolderName" value="${clientApprovalDetailTransBean.strCardHolderName}"/>
   <input type="hidden" name="strCardExpiryDate" value="${clientApprovalDetailTransBean.strCardExpiryDate}"/> 
    <input type="hidden" name="strClientNo" value="${clientApprovalDetailTransBean.strClientNo}"/>
    <input type="hidden" name="OPD" value="${clientApprovalDetailTransBean.OPD}"/>   
    <input type="hidden" name="IPD" value="${clientApprovalDetailTransBean.IPD}"/>  
    <input type="hidden" name="EME" value="${clientApprovalDetailTransBean.EME}"/>  
    <input type="hidden" name="strOldAuthenticationDate" value="${clientApprovalDetailTransBean.strAuthenticationDate}"/>    
       <input type="hidden" name="strOldAuthenticationNumber" value="${clientApprovalDetailTransBean.strAuthenticationNumber}"/>
 
   <cmbPers:cmbPers/>
</html:form>
  <tag:autoIndex></tag:autoIndex> 
</body>
</html>