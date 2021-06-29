<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


  <%@taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>
  <%@taglib uri="/WEB-INF/dateTag.tld" prefix="date"%>
  <%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
  <%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
 <%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
  <%@ taglib uri="/WEB-INF/patientDtl.tld" prefix="pDtl"%>
<%@ taglib uri="/WEB-INF/comboPersistence.tld" prefix="cmbPers"%>

<html>
<head>
<meta charset=utf-8>
<title>Cancel</title>
<link href="../css/transaction.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/buttons.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/calendar-tas.css" rel="stylesheet" type="text/css">

<script language="JavaScript" src="../../hisglobal/js/validation.js"></script>
<script language="JavaScript" src="../../hisglobal/js/util.js"></script>
<script language="JavaScript" src="../../hisglobal/js/calendar.js"></script>
<script language="JavaScript" src="../../hisglobal/masterutil/js/master.js"></script>
<script language ="javascript">
function validate1(){

var retVal = false;
var hisValidator = new HISValidator("msApprovalTransBean");
hisValidator.addValidation("strCancelReason", "req","Cancel Reason is mandatory field");
hisValidator.addValidation("strCancelBy", "dontselect=0","Cancel By is mandatory field");
if(document.msApprovalTransBean.strCancelReason.value != ""){
		hisValidator.addValidation("strCancelReason", "maxlen=100","Reason Cannot be More than 100 Characters");
		}
		retVal = hisValidator.validate(); 
		if(retVal){
document.forms[0].hmode.value = "CANCELAPPROVAL";

document.forms[0].submit();
}
else{
	hisValidator.clearAllValidations();
	return false;
	}
}
</script>
</head>
<body>
<html:form action="/transactions/MsApprovalTransCNT" method="post">
	<div class="normalMsg" id="normalMsg"></div>
<tag:tab tabLabel="Cancel Page" selectedTab="FIRST"
				width="TABLEWIDTH">
			</tag:tab>


<table class='TABLEWIDTH' align='center' cellspacing='0px' cellpadding="1px">
		<tr class="HEADER">
			<td colspan="2">Patient Demographic Details</td>
			<td colspan="2" align="right">Date :<bean:write name="msApprovalTransBean" property ="strCtDate"/>&nbsp;&nbsp;</td>
		</tr>
		</table>
		<table class='TABLEWIDTH' align='center' cellspacing='1px' cellpadding="0px">
	
 <tr>
 <td width="25%" class="LABEL" >CR No</td>
 <td width="75%" class="CONTROL" colspan ="3"><bean:write name = "msApprovalTransBean" property = "strCrNo" />
  <html:hidden  name  = "msApprovalTransBean" property  = "strCrNo"/>
  <html:hidden  name  = "msApprovalTransBean" property  = "strAdviceNo"/>
  <html:hidden  name  = "msApprovalTransBean" property  = "strApprovedStatus"/>
  <html:hidden  name  = "msApprovalTransBean" property  = "strPrefAdmDate"/>
 </td>
 </tr>
</table>                                             
<div>
		<table class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="0px">
			<tr>
			<td width="45%" class="TITLE">Patient Details</td>
			</tr>
		</table>
  <pDtl:patDtl address="true" crNo="${msApprovalTransBean.strCrNo}" ></pDtl:patDtl>
 </div>
 <table border="0" class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="0px">
 <tr>
 <td width="25%"  class="LABEL">File No/Episode No </td>
 <td width="25%" class="CONTROL"><bean:write name = "msApprovalTransBean" property = "strEpisodeNumber" /></td>
 <td width="25%" class="LABEL" >Dept/Unit</td>
 <td width="25%" class="CONTROL" colspan=""><bean:write name = "msApprovalTransBean" property = "strDeptUnit" /></td>
 </tr>
 <tr>
 <td width="25%" class="LABEL" >Prefer Adm Date</td>
 <td width="25%" class="CONTROL"><bean:write name = "msApprovalTransBean" property = "strPrefAdmDate" filter="false"/></td>
 <td width="25%" class="LABEL" >Adm Date and Time</td>
 <td width="25%" class="CONTROL"><bean:write name = "msApprovalTransBean" property = "strAdmDate" filter="false"/></td>
 
 
 </tr>
 <tr>
 <td width="25%"  class="LABEL">Ward</td>
 <td width="25%" class="CONTROL"><bean:write name = "msApprovalTransBean" property = "strWardName" /></td>
 <td width="25%" class="LABEL" >Room/Bed</td>
 <td width="25%" class="CONTROL"><bean:write name = "msApprovalTransBean" property = "strRoomBedNo" />
    <html:hidden name="msApprovalTransBean" property="strWardType"/>
    <html:hidden name="msApprovalTransBean" property="strWardCode"/>
    <html:hidden name="msApprovalTransBean" property="strBedType"/>
    <html:hidden name="msApprovalTransBean" property="strRoomType"/>
    <html:hidden name="msApprovalTransBean" property="strBedCode"/>
    <html:hidden name="msApprovalTransBean" property="strRoomNo"/>
 </td>
 </tr>
 <tr>
 <td width="25%" class="LABEL"><font color="red">*</font>Cancel By</td>
 <td width="25%" class="CONTROL">
 <SELECT name="strCancelBy"
				class="comboNormal">
				<bean:write name="msApprovalTransBean"
					property="strEmployeeComboValues" filter="false" />
			</SELECT></td>
<td width="25%" class="LABEL">Cancel Date Time </td>
<td width="25%" class="CONTROL"><bean:write property="strCtDate" name="msApprovalTransBean"/>
<html:hidden property="strCancelDate" value="${msApprovalTransBean.strCtDate}"/></td>
</tr>
<tr>
 <td width="25%" class="LABEL"><font color="red">*</font>Reason for Cancel </td>
 <td width="75%" class="CONTROL" colspan="3"><html:textarea name="msApprovalTransBean" property="strCancelReason" value="" /></td>
</tr>

<tr class="HEADER" ><td colspan="4">&nbsp;</td></tr>
</table>
<table border="0" class="TABLEWIDTH" align="center" cellspacing='0px' cellpadding="1px">
  <tr>
    <td align="center">
    <!-- <img style="cursor:hand;cursor:pointer" src="../../hisglobal/images/btn-sv.png" onClick ='validate1();'/>
   <img style="cursor:hand;cursor:pointer" src="../../hisglobal/images/btn-ccl.png" onclick="cancel('LIST');"/>
    -->
   <br>
			<a href="#" class="button"	onClick="return validate1();"><span class="generate">Generate</span></a> 
							<a href="#" class="button" onclick="cancel('LIST');"><span class="cancel">Cancel</span></a>
     </td>
 
  </tr>
</table>
<input type="hidden" name="hmode">
  <cmbPers:cmbPers></cmbPers:cmbPers>
</html:form>
<tag:autoIndex></tag:autoIndex>
</body>
</html>
