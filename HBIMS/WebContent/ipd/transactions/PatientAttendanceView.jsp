<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<%@ taglib uri="/WEB-INF/dateTag.tld" prefix="date"%>
<!-- 
Patient Attendance VIEW JSP

author: Debashis Sardar

dated: 18/02/2012
-->
<html>
<head><meta charset="utf-8" />
<link href="../css/transaction.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/buttons.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/calendar-tas.css" rel="stylesheet" type="text/css">

<script language="Javascript" src="../../hisglobal/js/validation2.js"></script>
<script language="Javascript" src="../../hisglobal/js/validation.js"></script>
<script language="JavaScript" src="../../hisglobal/js/tab.js"></script>
<script language="JavaScript" src="../../hisglobal/js/calendar.js"></script>
<script language="Javascript" src="../../hisglobal/js/datepicker1.js"></script>
<script language="Javascript" src="../../hisglobal/js/util.js"></script>
<script language="JavaScript" src="../../hisglobal/js/commonFunctions.js"/> </script>

<script language="JavaScript" src="../../ipd/js/PatientAttendanceView.js"></script>
<title>Patient Attendance View</title>

<script type="text/javascript">
</script>
</head>
<body>
<html:form action="/transactions/PatientAttendanceCNT">

<table class="TABLEWIDTH" align="center" cellspacing="0" cellpadding="0" width="100%">
	<tr class="HEADER">
		<td>Patient Attendance View</td></tr>
</table>		
<div class="normalMsg" id="normalMsg"></div>
							<table width="100%" align="center">
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
					<td width="25%" class="LABEL">
						<div align="right" id="divFromDate"><font color="red">*</font>From Date</div>
					</td>
					<td width="25%" class="CONTROL"><div id="divFromDateControl">
						<date:date name="strEffectiveFrom" value="${PatientAttendanceFB.strCtDate}"></date:date></div>
					</td>
					<td width="25%" class="LABEL">
						<div align="right" id="divToDate"><font color="red">*</font>To Date
						</div>
					</td>
					<td width="25%" class="CONTROL"><div id="divToDateControl">
						<date:date name="strEffectiveTo" value="${PatientAttendanceFB.strCtDate}"></date:date>				
						<img title="view patients status" src="../../hisglobal/images/Go.png"  onclick="return GOFunc();" style="cursor: pointer"  tabindex="1">
					
						</div>
					</td>
				</tr>
																					
						</table>
						
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
<div id="ID_HEADER" style="">
						<table class="TABLEWIDTH" align="center" cellspacing="0" cellpadding="0" width="100%">
	<tr class="HEADER">
		<td> </td></tr>
</table>	
</div>	
<div id="ID_CAN_CLR" style="">
<table class="TABLEWIDTH" width="100%" cellpadding="0" cellspacing="1">
<tr><td align="center">				

		<br>
				<a href="#" class="button"	onClick="formClear();"><span class="clear">Clear</span></a> 
							<a href="#" class="button" onclick="cancel();"><span class="cancel">Cancel</span></a>
</td></tr>
</table>
</div>	
	
		
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
<html:hidden name="PatientAttendanceFB" property="strDefaultWardFlag" value="0"/>  
<html:hidden name="PatientAttendanceFB" property="strIsVacant" value=""/>  
<html:hidden name="PatientAttendanceFB" property="strChk" value=""/>  
<input type="hidden" name="cnt" value="">
<input type="hidden" name="strCtDate" value="${PatientAttendanceFB.strCtDate}">
</html:form>
		
</body>
</html>