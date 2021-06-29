<!DOCTYPE HTML>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>
<%@ taglib uri="/WEB-INF/dateTag.tld" prefix="date"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/patientDtl.tld" prefix="pDtl"%>
<%@ taglib uri="/WEB-INF/comboPersistence.tld" prefix="cmbPers"%>
<html>
<head>
<meta charset=utf-8>
<title>MS Approval View page</title>
<link href="../../hisglobal/css/master.css" rel="stylesheet" type="text/css">
<link href="../css/transaction.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet"
	type="text/css">
<link href="../../hisglobal/css/calendar-tas.css" rel="stylesheet"
	type="text/css">


<script language="JavaScript" src="../../hisglobal/masterutil/js/master.js"></script>
<script language="JavaScript" src="../../hisglobal/js/tab.js"></script>
<script language="JavaScript" src="../../hisglobal/js/calendar.js"></script>
<script language="JavaScript" src="../../hisglobal/js/util.js"></script>
<script type="text/javascript">
function funOccupationAdd()
{
 	document.getElementById("divoccupation").style.display="block";
 	document.getElementById("plusOcc").style.display="none";
 	document.getElementById("minusOcc").style.display="";
}
function funOccupationRemove()
{
 document.getElementById("divoccupation").style.display="none";
 document.getElementById("plusOcc").style.display="";
 document.getElementById("minusOcc").style.display="none";
}
function funFormAdd()
{
 document.getElementById("divform").style.display="block";
 document.getElementById("plusForm").style.display="none";
 document.getElementById("minusForm").style.display="";
}
function funFormRemove()
{
 document.getElementById("divform").style.display="none";
 document.getElementById("plusForm").style.display="";
 document.getElementById("minusForm").style.display="none";
}
</script>
</head>
<body>
<html:form action="/transactions/MsApprovalTransCNT" method="post">
	<div class="normalMsg" id="normalMsg"></div>
	<tag:tab tabLabel="MS Approval View Page" selectedTab="FIRST" width="TABLEWIDTH"></tag:tab>
<table class='TABLEWIDTH' align='center' cellspacing='0' cellpadding="0">
		<tr class="HEADER">
			<td colspan="2">Patient Demographic Details</td>
			<td colspan="2" align="right">Date&nbsp;:&nbsp;
			<bean:write name="msApprovalTransBean" property ="strCtDate"/>&nbsp;&nbsp;</td>
		</tr>
		</table>
		<table class='TABLEWIDTH' align='center' cellspacing='1px' cellpadding="1px">	
		<tr>
			<td class="LABEL" nowrap width="25%">CR No</td>
			<td class="CONTROL"  width="25%" nowrap colspan ="1">
			<bean:write name = "msApprovalTransBean" property="strCrNo" /></td>
			<td class="LABEL"  width="25%" nowrap colspan ="1"></td>
			<td class="CONTROL"  width="25%" nowrap colspan ="1"></td>			
		</tr>
</table>
 		
    <table class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="0px">
	 <tr>
		<td width="45%" class="TITLE">Patient Details</td>
	 </tr>
   </table>
  <pDtl:patDtl crNo="${msApprovalTransBean.strCrNo}" address="true"></pDtl:patDtl>
<table class='TABLEWIDTH' align='center' cellspacing='1px' cellpadding="1px">		      
 <tr  class ="TITLE"><td colspan ="4">Clinical Detail</td></tr>         	
		<tr>
			<td width="25%" class="LABEL">Department/Unit</td>
			<td width="25%" colspan="1" class="CONTROL">
			<bean:write name="msApprovalTransBean" property="strDeptUnit"/></td>
			<td width="25%"  class="LABEL">File No/Episode No </td>
 			<td width="25%" colspan="1" class="CONTROL">
 			<bean:write name = "msApprovalTransBean" property = "strEpisodeNumber"/></td>
 		</tr>   
		<tr>
			<td width="25%" class="LABEL">Ward</td>
			<td width="25%" class="CONTROL"><bean:write name = "msApprovalTransBean" property ="strWardName"/></td>
			<td width="25%" class="LABEL">Room/Bed</td>
			<td width="25%" class="CONTROL"><bean:write name = "msApprovalTransBean" property ="strRoomBedNo"/></td>
		</tr>		
		<tr>
			<td width="25%" class="LABEL">Consultant</td>
			<td width="25%" class="CONTROL" colspan="1">
			<bean:write name = "msApprovalTransBean" property ="strVerifyByName"/></td>
			<td width="25%" class="LABEL" nowrap>Patient PGI Employee</td>
			<td width="25%" class="CONTROL" COLSPAN="1">
				<bean:write name="msApprovalTransBean"  property="strIsPgiEmp"/>
			</td>
		</tr>
		<tr>
			<td width="25%" class="LABEL">Provisional Diagnosis</td>
			<td width="25%" class="CONTROL" colspan="3">
			<bean:write name = "msApprovalTransBean" property ="strProvisionDiagnosis"/></td>
		</tr>
		<logic:notEmpty name="msApprovalTransBean"  property="strWaitPeriod">
		<tr>
			<td width="50%" class="LABEL" nowrap colspan="2">Waiting period for Planned Surgery(Days)</td>
			<td width="50%" class="CONTROL"  colspan="2">
			<bean:write name="msApprovalTransBean"  property="strWaitPeriod"/>
			</td>
		</tr>
		</logic:notEmpty>
		</table>
		<tr>
        <td colspan="5">
	   <table class='TABLEWIDTH' align='center' cellspacing='1px' cellpadding="1px">
		<tr class="TITLE">
		<td colspan="5">
	    <img style="cursor:hand;cursor:pointer" src="../../hisglobal/images/plus.gif" id="plusOcc" 
	    onClick ="funOccupationAdd();"/>
		<img style="cursor:hand;cursor:pointer;display: none" src="../../hisglobal/images/minus.gif" 
		id="minusOcc" onClick ="funOccupationRemove();"/>&nbsp;&nbsp;Occupation Details
		</td>
	    </tr>
	    </table>
	    </td>
	    </tr>
	    <tr>
	  <td colspan="5">
	  <div id ="divoccupation" style="display:none">
		  <bean:write name ="msApprovalTransBean" property ="strOccupation" filter = "false"/>
       </div>
       </td>
       </tr>      
      <tr>
      <td colspan="5">
    <table class='TABLEWIDTH' align='center' cellspacing='1px' cellpadding="1px">
        <tr class="TITLE"><td colspan="5">
		<img style="cursor:hand;cursor:pointer" id="plusForm" src="../../hisglobal/images/plus.gif" 
		onClick ="funFormAdd();"/>
		<img style="cursor:hand;cursor:pointer;display: none" id="minusForm"  
		src="../../hisglobal/images/minus.gif" onClick ="funFormRemove();"/>&nbsp;&nbsp;Form Details</td></tr>
	  </table>
	  </td>
	 </tr>	  
	  <tr>
	 <td colspan ="5">
	  <div id ="divform" style="display:none">	    
      <table class="TABLEWIDTH" align="center">    
		<tr>
			<td width="25%" class="LABEL">Form No</td>
			<td width="25%"  class="CONTROL">
			<bean:write name="msApprovalTransBean" property="strFormNo" /></td>			
			<td width="25%" class="LABEL">Submit Date</td>
			<td width="25%" class="CONTROL"><font color="blue">
			<bean:write name="msApprovalTransBean" property="strRequestDate"/></font>
			</td>
		</tr>
		<tr>
			<td width="25%" class="LABEL">Verified By</td>
			<td width="25%" class="CONTROL">
			<bean:write name ="msApprovalTransBean" property ="strVerifyByName" />
			</td>
			<td width="25%" class="LABEL">Verified Date</td>
			<td width="25%" class="CONTROL"><font color="blue">
			<bean:write name="msApprovalTransBean" property="strVerifiedDate"/></font>
			</td>
		</tr>
		<tr>
			<td width="25%" class="LABEL">Remark</td>
			<td width="75%" colspan="4" class="CONTROL">
			<bean:write name="msApprovalTransBean" property="strVerifyRemark" />
			</td>
		</tr>
	</table>
</div>
</td>
</tr>
<table class='TABLEWIDTH' align='center' cellspacing='1px' cellpadding="1px">   
    <tr CLASS="TITLE"><td colspan="4">Approval</td></tr>
	<tr>
		<td width="25%" CLASS="LABEL">Approved Status</td>
		<td width="25%" class="CONTROL"><b>
		<logic:equal name ="msApprovalTransBean" property = "appStatus" value="1">
		<font color="green"><bean:write name ="msApprovalTransBean" property = "strApprovedStatus"/></font></logic:equal>
		<logic:equal name ="msApprovalTransBean" property = "appStatus" value="2">
		<font color="green"><bean:write name ="msApprovalTransBean" property = "strApprovedStatus"/></font></logic:equal>
		<logic:equal name ="msApprovalTransBean" property = "appStatus" value="3">
		<font color="red"><bean:write name ="msApprovalTransBean" property = "strApprovedStatus"/></font></logic:equal>
		<logic:equal name ="msApprovalTransBean" property = "appStatus" value="4">
		<font color="red"><bean:write name ="msApprovalTransBean" property = "strApprovedStatus"/></font></logic:equal>
		<logic:equal name ="msApprovalTransBean" property="appStatus" value="5">
		<font color="red"><bean:write name ="msApprovalTransBean" property = "strApprovedStatus"/></font></logic:equal>
		<logic:equal name ="msApprovalTransBean" property = "appStatus" value="6">
		<font color="blue"><bean:write name ="msApprovalTransBean" property = "strApprovedStatus"/></font></logic:equal>
		<logic:equal name ="msApprovalTransBean" property = "appStatus" value="7">
		<font color="red"><bean:write name ="msApprovalTransBean" property = "strApprovedStatus"/></font></logic:equal>
		</b></td>
		<td width="25%" class="LABEL">Approval/Rejection Date</td>
		<td width="25%" class="CONTROL"><font color="blue">
		<bean:write name="msApprovalTransBean" property="strApproveDate"/></font>
		</td>
	</tr>
	<tr>
		<td width="25%" class="LABEL">Approved/Rejected By</td>
		<td width="25%" class="CONTROL">
		<bean:write name ="msApprovalTransBean" property ="strApprovedBY"/>
		</td>
		<td width="25%" class="LABEL">Remark</td>
		<td width="25%" class="CONTROL">
		<bean:write name="msApprovalTransBean" property="strRemark" />
		</td>
	</tr>
	<tr class="HEADER">
		<td colspan="4">&nbsp;</td>
	</tr>   
   </table>
	<table border="0" class="TABLEWIDTH" align="center">
		<tr>
		<td align ="left"><div align ='center'>
		<!-- <img style="cursor:hand;cursor:pointer" src="../../hisglobal/images/btn-ccl.png" onclick="cancel('LIST');"/> -->
		<br>
		<a href="#" class="button" onclick="cancel('LIST');"><span class="cancel">Cancel</span></a>
		</div></td>		
	  </tr>
	</table>	
	<input type="hidden" name="hmode">
		<html:hidden name="msApprovalTransBean" property="strAdviceNo"/>
	    <html:hidden name="msApprovalTransBean" property="strBooking_date"/>
	    <html:hidden name="msApprovalTransBean" property="strBookingQno"/>
	    <html:hidden name="msApprovalTransBean" property="strWardype"/>
	    <html:hidden name="msApprovalTransBean" property="strPrefAdmDate"/>	
	   	  <cmbPers:cmbPers></cmbPers:cmbPers>
</html:form>
<tag:autoIndex></tag:autoIndex>
</body>
</html>
