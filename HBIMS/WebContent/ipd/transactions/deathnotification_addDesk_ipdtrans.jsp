
<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib  uri="/WEB-INF/comboPersistence.tld" prefix="cmbPers" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>
<%@taglib uri="/WEB-INF/dateTag.tld" prefix="date"%>
<%@taglib uri="/WEB-INF/patientDtl.tld" prefix="pDtl"%>
<%@ taglib uri="/WEB-INF/admissionDtl.tld" prefix="aDtl"%>
<%@ page import="ipd.transactions.*" %>

<html>
<head>
<meta charset=utf-8>
<title>Insert title here</title>
<link href="../css/transaction.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/calendar-tas.css" rel="stylesheet" type="text/css">
<script language="JavaScript" src="../..hisglobal/masterutil/js/master.js"></script>
<script language="JavaScript" src="../../hisglobal/js/tab.js"></script>
<script language="JavaScript" src="../../hisglobal/js/calendar.js"></script>
<script language="JavaScript" src="../../hisglobal/js/util.js"></script>
<script language="JavaScript" src="../../hisglobal/js/validation1.js"></script>
<script language="JavaScript" src="../../hisglobal/js/multirow.js"></script>
<script language="JavaScript" src="../../ipd/js/deathnotiftrans.js"></script>
<script language="JavaScript" src="../../ipd/js/nursingDesk.js"></script>
<script>
function onstart(){
	var flag=document.forms[0].strFGnctdFlag.value;
	if(flag==1){
		document.getElementById("onset1").className="CONTROL";
		document.getElementById("onset1").innerHTML="";
		document.getElementById("onset2").innerHTML="";
		document.getElementById("onset3").className="CONTROL";
		document.getElementById("onset3").innerHTML="";
		document.getElementById("onset4").innerHTML="";	
		document.getElementById("manner1").innerHTML="Mode of Death";
		document.getElementById("manner2").options[0].text="Natural";
	}
}

</script>
</head>
<body onload="view()">
<html:form action="/transactions/DeathNotificationTransCNT.cnt" 
				method="post" >
				
		<div class="normalMsg" id="normalMsg"><bean:write name="deathNotificationTransBean" property="strNormalMsg"/></div>
		<div class="errMsg" id="errMsg"><bean:write name="deathNotificationTransBean" property="strErrMsg"/></div>
		
		
		<tag:tab tabLabel="Death Notification" selectedTab="FIRST"
		align="center" width="TABLEWIDTH"></tag:tab>
	<table class="TABLEWIDTH" align="center" cellspacing="1px">
		<tr class="HEADER">
			<td colspan="4">Death Notification</td>
		</tr>
		
	</table>
	<table class="TABLEWIDTH" align="center" cellspacing="1px">
	<tr>
			<td width="25%" class="LABEL"><font color="red">*</font>CR No.</td>
			<td class="CONTROL" nowrap="nowrap">
			<div id="patientCrEdId" style="display: block;">
			<input type="hidden" name="strCrNo" value="${deathNotificationTransBean.strCrNo}">
			<bean:write name="deathNotificationTransBean" property="strCrNo"></bean:write> 
			</div>
			<div id="patientCrId" style="display: none;"></div>
		</td>
			
		</tr>
	</table>
	<div id="patientDemographicId" style="display: none">
		<table class="TABLEWIDTH" align="center" cellspacing="1px">
			<tr>
				<td colspan="4" class="TITLE" width="93%">Patient Demographic Details </td> 
			</tr>
			<pDtl:patDtl crNo="${deathNotificationTransBean.strCrNo}" address="true"></pDtl:patDtl>
	</table>
	</div>
	<div id="patientAdmissionId" style="display: none">
	<table class="TABLEWIDTH" align="center" cellspacing="1px">
			 <tr>
				<td colspan="4" class="TITLE" width="93%">Patient Admission Details </td> 
			</tr>
	 <aDtl:addDtl crNo="${deathNotificationTransBean.strCrNo}"></aDtl:addDtl>
	</table>
	</div>
	<div id="deathNotiFicationId1" style="display:none;">
	<table class="TABLEWIDTH" align="center" cellspacing="1px">
		<tr>
			<td colspan="4" class="TITLE" width="93%">Death Notification Form</td> 
		</tr>
		<tr>
			<td class="LABEL" width="25%"><font color="red">*</font>Date Of Death</td>
			<td class="CONTROL" width="25%">
				<date:date name="strDeathDate" value =""></date:date>
			</td>
			<td class="LABEL" id="onset1" width="25%"><font color="red">*</font>Onset Date Of Death</td>
			<td class="CONTROL" id="onset2" width="25%">
				<date:date name="strOnsetDeathDate" value =""></date:date>
			</td>
		</tr>
		<tr>
	<td class="LABEL" width="23%"><font color="red">*</font>Time Of Death</td>
	<td class="CONTROL" width="27%" nowrap="nowrap">
	<input type="text" class="txtFldSmall" name="strDeathHour" maxlength="2" onkeypress="return validateData(event,5);" onkeyup="hour(this,event),focusDeathMin(this)"><b>:</b>
	<input type="text" class="txtFldSmall" name="strDeathMin" maxlength="2" onkeypress="return validateData(event,5);" onkeyup="min(this,event),focusDeathSec(this)"><b>:</b>
	<input type="text" class="txtFldSmall" name="strDeathSec" maxlength="2" onkeypress="return validateData(event,5);" onkeyup="sec(this,event)">
	<select class="comboSmall" name="strDeathAmPm">
		<option value="1">AM</option>
		<option value="2">PM</option>
	</select>
	</td>
	<td class="LABEL" width="15%" id="onset3"><font color="red">*</font>Onset Time Of Death</td>
	<td class="CONTROL" id="onset4" width="35%" nowrap="nowrap">
		<input type="text" class="txtFldSmall" name="strOnsetDeathHour" maxlength="2" onkeypress="return validateData(event,5);" onkeyup="hour(this,event),focusOnsetDeathMin(this)"><b>:</b>
		<input type="text" class="txtFldSmall" name="strOnsetDeathMin" maxlength="2" onkeypress="return validateData(event,5);" onkeyup="min(this,event),focusOnsetDeathSec(this)"><b>:</b>
		<input type="text" class="txtFldSmall" name="strOnsetDeathSec" maxlength="2" onkeypress="return validateData(event,5);" onkeyup="sec(this,event)">
		<select class="comboSmall" name="strOnsetDeathAmPm">
		<option value="1">AM</option>
		<option value="2">PM</option>
	</select>
	</td>
</tr>
		<tr>
			<td class="LABEL" width="25%"><font color="red">*</font>Immediate Cause Of Death</td>
			<td class="CONTROL" width="25%">
				<select name="strCauseDeath" class="comboNormal">
					<bean:write property="strDeathCauseValue" name="deathNotificationTransBean" filter="false"/>
				</select>
			</td>
			<td class="LABEL" width="25%" id="manner1"><font color="red">*</font>Manner Of Death</td>
			<td class="CONTROL" width="25%">
				<select name="strDeathManner" id="manner2" class="comboNormal">
					<bean:write property="strDeathMannerValue" name="deathNotificationTransBean" filter="false"/>
				</select>
			</td>
		</tr>
		<tr>
			<td class="LABEL" width="25%">Description Of Immediate Cause Of Death</td>
			<td class="CONTROL" width="25%">
				<textarea rows="2" name="strDescpCauseDeath" ></textarea>
			</td>
			<td class="LABEL" width="25%">Antecedent Cause Of Death</td>
			<td class="CONTROL" width="25%">
				<textarea rows="2" name="strAntecedentCauseDeath"></textarea>
			</td>
			
		</tr>
		<tr>
			<td class="LABEL" width="25%">Injury Details</td>
			<td class="CONTROL" width="25%">
				<textarea rows="2" name="strInjuryDetail"></textarea>
			</td>
			
			<td class="LABEL" width="25%">Description Of Other Significant Conditions Contributing To Death</td>
			<td class="CONTROL" width="25%">
				<textarea rows="2" name="strInjuryDetail"></textarea>
			</td>
		</tr>
		</table>
	
		<div id = "isPregnantId" <%if(((DeathNotificationTransFB)request.getAttribute("deathNotificationTransBean")).isFFemale()){
	 %>style="display:block"<%}else{ %>style="display:none"<%} %>>
		<table class="TABLEWIDTH" align="center" cellspacing="1px">
		<tr>
			<td class="LABEL" width="25%">Is Pregnant</td>
			<td class="CONTROL" colspan="3">
				 <html:radio name="deathNotificationTransBean" property="strIsPregnant" value="1" onclick="isPregnantClick(this);">Yes</html:radio>
    			<html:radio name="deathNotificationTransBean" property="strIsPregnant" value="0" onclick="isPregnantClick(this);">No</html:radio>
    		</td>
		</tr>
		</table>
		</div>
	</div>
	<div id="isDeliveryID" style="display: none">
	<table class="TABLEWIDTH" align="center" cellspacing="1px">
		<tr>
			<td class="LABEL" width="25%">Is there Delivery</td>
			<td class="CONTROL" colspan="3">
				 <html:radio name="deathNotificationTransBean" property="strIsDelivery" value="1" >Yes</html:radio>
    			<html:radio name="deathNotificationTransBean" property="strIsDelivery" value="0">No</html:radio>
			</td>
		</tr>
	
	</table>
	</div>
	
	<div id="deathNotiFicationId2" style="display: none">
	<table class="TABLEWIDTH" align="center" cellspacing="1px">
		<tr>
			<td class="LABEL" width="25%"><font color="red">*</font>Verified By</td>
			<td class="CONTROL" colspan="3">
				<select name="strConsultant" class="comboMax">
					<bean:write name="deathNotificationTransBean" property="strConsultantValue" filter="false"/>
				</select>
			</td>
		</tr>
		
<tr>
			<td class="LABEL" width="25%"><font color="red">*</font>Verify Date</td>
			<td class="CONTROL" width="25%">
				<date:date name="strVerifyDate" value =""></date:date>
			</td>
			<td class="LABEL" width="23%"><font color="red">*</font>Verify Time</td>
				<td class="CONTROL" width="27%" nowrap="nowrap">
				<input type="text" class="txtFldSmall" name="strVerifyHour" maxlength="2" onkeypress="return validateData(event,5);" onkeyup="hour(this,event),verifyTimeMin(this)"><b>:</b>
				<input type="text" class="txtFldSmall" name="strVerifyMin" maxlength="2" onkeypress="return validateData(event,5);" onkeyup="min(this,event),verifyTimeSec(this)"><b>:</b>
				<input type="text" class="txtFldSmall" name="strVerifySec" maxlength="2" onkeypress="return validateData(event,5);" onkeyup="sec(this,event)">
				<select class="comboSmall" name="strVerifyAmPm">
					<option value="1">AM</option>
					<option value="2">PM</option>
				</select>
	</td>
</tr>
		<tr>
			<td class="LABEL" width="25%">Is Shift To Mortuary</td>
			<td class="CONTROL" colspan="3">
				 <html:radio name="deathNotificationTransBean" property="strIsSiftToMortuary" value="1" onclick="shiftMortuaryClk(this)">Yes</html:radio>
    			<html:radio name="deathNotificationTransBean" property="strIsSiftToMortuary" value="0" onclick="shiftMortuaryClk(this)">No</html:radio>
			</td>
		</tr> 
		
	</table>
	</div>
	
	<div id="isSiftMortID" style="display: none">
	<table class="TABLEWIDTH" align="center" cellspacing="1px">
		<tr>
			<td class="LABEL" width="25%"><font color="red">*</font>Shift date</td>
			<td class="CONTROL">
				<date:date name="strShiftDate" value =""></date:date>
			</td>
			<td class="LABEL" width="25%"><font color="red">*</font>Shift Time</td>
			<td class="CONTROL" width="25%" nowrap="nowrap">
				<input type="text" class="txtFldSmall" name="strShiftHour" maxlength="2" onkeypress="return validateData(event,5);" onkeyup="hour(this,event),focusShiftMin(this)"><b>:</b>
			<input type="text" class="txtFldSmall" name="strShiftMin" maxlength="2" onkeypress="return validateData(event,5);" onkeyup="min(this,event),focusShiftSec(this)"><b>:</b>
			<input type="text" class="txtFldSmall" name="strShiftSec" maxlength="2" onkeypress="return validateData(event,5);" onkeyup="sec(this,event)">
			<select class="comboSmall" name="strShiftAmPm">
					<option value="1">AM</option>
					<option value="2">PM</option>
				</select>
		</td>
		</tr>
	</table>
	</div>
	
	<div id="isHandOverID" style="display: none">
	<table class="TABLEWIDTH" align="center" cellspacing="1px">
		<tr><td class="LABEL" ><font color="red">*</font>HandOver To</td>
		<td class="CONTROL" nowrap="nowrap">
		<input type="text" class="txtFldNormal" name="strHandOverTo" maxlength="100">
		
		</td>
		
			<td class="LABEL" ><font color="red">*</font>HandOver Date</td>
			<td class="CONTROL">
				<date:date name="strHandOverDate" value =""></date:date>
			</td>
			<td class="LABEL" ><font color="red">*</font>HandOver Time</td>
			<td class="CONTROL"  nowrap="nowrap">
				<input type="text" class="txtFldSmall" name="strHandOverHour" maxlength="2" onkeypress="return validateData(event,5);" onkeyup="hour(this,event),focusHandOverMin(this)"><b>:</b>
			<input type="text" class="txtFldSmall" name="strHandOverMin" maxlength="2" onkeypress="return validateData(event,5);" onkeyup="min(this,event),focusHandOverSec(this)"><b>:</b>
			<input type="text" class="txtFldSmall" name="strHandOverSec" maxlength="2" onkeypress="return validateData(event,5);" onkeyup="sec(this,event)">
			<select class="comboSmall" name="strHandOverAmPm">
					<option value="1">AM</option>
					<option value="2">PM</option>
				</select>
		</td>
		</tr>
	</table>
	</div>
	
<table border="0" class="TABLEWIDTH" align="center" cellspacing="1px"> 
		<tr class="FOOTER">
			<td colspan="4"><font size="2" color="red">*</font> Mandatory
			Fields		
			</td>
		</tr>
		<tr>
			<td align="center" colspan="2"><img style="cursor: hand;cursor: pointer" src="../../hisglobal/images/btn-sv.png"
				onClick="return validate1();">
			<img style="cursor: hand;cursor: pointer" src="../../hisglobal/images/btn-ccl.png"
				onClick="cancelToDesk();">
			</td>
		</tr>
	</table>
	<input type="hidden" name="hmode" value=""/>
	<input type="hidden" name="strAdmno" value="${deathNotificationTransBean.strAdmno}"/>
	<input type="hidden" name="strEpisodeCode" value="${deathNotificationTransBean.strEpisodeCode}"/>
	<input type="hidden" name="strVistNo" value="${deathNotificationTransBean.strVistNo}"/>
	<input type="hidden" name="strAdmDate" value="${deathNotificationTransBean.strAdmDate}"/>
	<input type="hidden" name="strDeptCode" value="${deathNotificationTransBean.strDeptCode}"/>
	<input type="hidden" name="strDeptUnitCode" value="${deathNotificationTransBean.strDeptUnitCode}"/>
	<input type="hidden" name="strWardCode" value="${deathNotificationTransBean.strWardCode}"/>
	<input type="hidden" name="strRoomeCode" value="${deathNotificationTransBean.strRoomeCode}"/>
	<input type="hidden" name="strBedCode" value="${deathNotificationTransBean.strBedCode}"/>
	<input type="hidden" name="strIsNewBorn" value="${deathNotificationTransBean.strIsNewBorn}"/>
	<input type="hidden" name="strMlcNo" value="${deathNotificationTransBean.strMlcNo}"/>
	<input type="hidden" name="strFGnctdFlag" value="${deathNotificationTransBean.strFGnctdFlag}"/>
	<input type="hidden" name="strDeathDateTime" value=""/>
	<input type="hidden" name="strOnsetDeathDateTime" value=""/>
	<input type="hidden" name="strShiftDateTime" value=""/>
	<input type="hidden" name="strVerifyDateTime" value=""/>
	<input type="hidden" name="strHandOverDateTime" value=""/>
	<cmbPers:cmbPers/>
</html:form>
<script>
onstart();
</script>
<tag:autoIndex></tag:autoIndex>
</body>
</html>