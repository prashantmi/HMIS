<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<!-- 
Patient Attendance JSP

author: Debashis Sardar

dated: 18/02/2012
-->
<html>
<head><meta http-equiv="Content-Type" content="text/html; charset="utf-8" >
<link href="../css/transaction.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/calendar-tas.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/buttons.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/popup.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/cr-no-format.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/dropdown.css" rel="stylesheet" type="text/css">

<style type='text/css'>
.dragme {
	cursor: move;
	color: #FFFFFF;
	background-color: #005680;
}
</style>
<script language="Javascript" src="../../hisglobal/js/validation.js"></script>
<script language="JavaScript" src="../../transUtil/js/master.js"></script>
<script language="JavaScript" src="../../hisglobal/js/tab.js"></script>
<script language="JavaScript" src="../../hisglobal/js/calendar.js"></script>
<script language="Javascript" src="../../hisglobal/js/datepicker1.js"></script>
<script language="Javascript" src="../../hisglobal/js/util.js"></script>
<script language="JavaScript" src="../../hisglobal/js/newMultiRow.js"></script>
<script language="JavaScript" src="../../hisglobal/js/multirow.js"></script>
<script language="JavaScript" src="../../hisglobal/js/dropdown.js"></script>
<script language="JavaScript" src="../../hisglobal/js/common.js"></script>

<script language="JavaScript" src="../../ipd/js/PatientAttendance.js"></script>

<title>Patient Attendance</title>

<script type="text/javascript">

</script>
</head>
<body onLoad="">
<html:form action="/transactions/PatientAttendanceCNT" >
<div align="center" style="display:none" id="blkHrsId"></div>
<table class="TABLEWIDTH" align="center" cellspacing="0" cellpadding="0" width="100%">
	<tr class="HEADER">
		<td width="75%">Patient Attendance</td>
              <td colspan="1" class="" width="25%" align="right">
              <input type="checkbox" name="strIsView" value="" onClick="view();"> View</td>
		
		</tr>
</table>		
<div class="normalMsg" id="normalMsg"></div>
							<table class="TABLEWIDTH" width="100%" align="center">
								<tr>
									
									<td class='LABEL' width="25%" height="10%">
									<div align="right"><font color="red">*</font>Service Type</div>
									</td>
									<td class="CONTROL" width="25%"><select name="strServiceType"
										class='comboNormal' onChange="getServiceName(this)" >
										<bean:write name="PatientAttendanceFB"
											property="strServiceType" filter='false' />
									</select></td>
									
									<td class='LABEL' width="25%" >
									<div align="right" id="SERVICE_NAME">
									<font color="red">*</font>Service Name</div>
									</td>
									<td class="CONTROL" width="25%"><select name="strServiceName"
										class='comboNormal' onChange='serviceNameChange(this)'>
										<option value='0'>Select Value</option>
									</select></td>
								</tr>
								<tr>
									 
									<td class='LABEL' width="25%" height="10%">
									<div align="right"><font color="red">*</font>Status</div>
									</td>        
									<td class="CONTROL" width="25%"><select name="strStatusCombo"
										class='comboNormal' onChange="return getPatList(this)">
										<option value='0'>Select Value</option>
									</select></td>
									
									<td class='LABEL' width="25%" >
									</td>
									<td class='CONTROL' width="25%" >
									</td>	
								</tr>
																					
						</table>
						<div id="ID_HEADER" style="">
						<table class="TABLEWIDTH" align="center" cellspacing="0" cellpadding="0" width="100%">
	<tr class="HEADER">
		<td> </td></tr>
</table>	
</div>	
<div id="ID_PAT_LIST_HEADER" style="display: none">
<table class="TABLEWIDTH" cellpadding="0" cellspacing="1px" width="100%" align="center">
			<tr class="TITLE">
				<td colspan="3">Patient List</td>				
			</tr>
			<tr>
				<td width="100%" height="100%">
						<div id="ID_PAT_LIST" >	 
							</div>					
				</td>
		 </tr>	
		</table>	
	 </div>	
<div id="ID_CAN_CLR" style="">
<table  class="TABLEWIDTH" width="100%" cellpadding="0" cellspacing="1">
<tr><td align="center">			
				
				 <br>
				<a href="#" class="button"	onClick="formClear();"><span class="clear">Clear</span></a> 
							<a href="#" class="button" onclick="cancel();"><span class="cancel">Cancel</span></a>
</td></tr> 
</table>
</div>	
	<div id="ID_MSG" align="center">
	<logic:notEmpty name="PatientAttendanceFB" property="strErrMsgString">
			<font color="red" size="2"
				face="Verdana, Arial, Helvetica, sans-serif"> <b><bean:write
				name="PatientAttendanceFB" property="strErrMsgString" /> </b></font>
		</logic:notEmpty>	</div>
<html:hidden name="PatientAttendanceFB" property="strStatus"/> 
<html:hidden name="PatientAttendanceFB" property="strControl" value="-1"/>
<html:hidden name="PatientAttendanceFB" property="strAcceptedFlag" value="0"/>	
<html:hidden name="PatientAttendanceFB" property="hmode"/>
<html:hidden name="PatientAttendanceFB" property="strAge" value="0"/> 
<html:hidden name="PatientAttendanceFB" property="strGenderCode" value="0"/> 
<html:hidden name="PatientAttendanceFB" property="strAgeCode" value="0"/> 
<html:hidden name="PatientAttendanceFB" property="strTreatmentCat" value="0"/> 
<html:hidden name="PatientAttendanceFB" property="strCrNo"/>  
<html:hidden name="PatientAttendanceFB" property="strDefaultUnitFlag" value="0"/>  
<html:hidden name="PatientAttendanceFB" property="strDefaultUnit" value=""/>
<html:hidden name="PatientAttendanceFB" property="strDefaultWard" value=""/>
<html:hidden name="PatientAttendanceFB" property="strDefaultRoom" value=""/>
<html:hidden name="PatientAttendanceFB" property="strDefaultWardFlag" value="0"/>  
<html:hidden name="PatientAttendanceFB" property="strDefaultRoomFlag" value="0"/>  
<html:hidden name="PatientAttendanceFB" property="strIsVacant" value=""/>  
<html:hidden name="PatientAttendanceFB" property="strChk" value=""/>  
<html:hidden name="PatientAttendanceFB" property="strBed" />
<input type="hidden" name="cnt" value="">
</html:form>
		
</body>
</html>