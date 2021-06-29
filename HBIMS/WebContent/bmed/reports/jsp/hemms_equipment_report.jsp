<%@page import="hisglobal.hisconfig.Config"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
"http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<%@taglib uri="/WEB-INF/dateTag.tld" prefix="dateTag"%>
<%@ taglib uri="/WEB-INF/comboPersistence.tld" prefix="cmbPers"%>


<!-- 
/**
 * @author Partha P Chattaraj and T. Saratkumar
 * Date of Creation : 19-Aug-2013
 * Date of Modification :  
 * Version : 
 * Module  : HEMMS Product
 */
 -->
<html>
<head>

 <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="../../hisglobal/css/control.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/emms.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/calendar-tas.css" rel="stylesheet" type="text/css">
<his:javascript  src="/hisglobal/js/time.js" />
<his:javascript src="/hisglobal/js/util.js" />
<his:javascript src="/hisglobal/js/calendar.js" />
<his:javascript src="/hisglobal/js/validation.js" />
<his:javascript	src="/bmed/reports/js/hemms_equipment_report.js" />
</head>
<body marginheight="0" marginwidth="0" class="background">

<html:form  action="/reports/EquipmentReportCNT"  method="post" styleClass="formbg">
	
			
		<div id="trItemId">
		<table class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="1px">
		<tr class="HEADER">
			<td colspan="4">Equipment Maintenance Report</td>		</tr>
	    	
	    <tr>
			<td width="50%" colspan="2" class="LABEL">
			Report Format
			</td>
			<td width="50%" colspan="2" class="CONTROL">
			<select name="strReportFormat"  onchange="">
			<option value="html">Html</option>
			<option value="pdf">Pdf</option></select>
			</td>
			
		</tr> 	
		<tr>
			<td width="50%" colspan="2" class="LABEL"><font color="red">*</font>Hospital Name</td>
			<td width="50%" colspan="2" class="CONTROL">
				<div id="hospitalNameId">
					<select name="strDeptCode"  class="combo1" onchange="getStoreName()">
					<bean:write name="equipmentReportFB" property="strHospitalCombo" filter="false" />
					</select>
				</div>
			</td>
		</tr>
		<tr>
			<td width="50%" colspan="2" class="LABEL"><font color="red">*</font>Lab Name</td>
			<td width="50%" colspan="2" class="CONTROL">
				<div id="labNameId">
					<select name="strLabCode" class="combo1">
					<option value="0">Select Value</option>
					<bean:write name="equipmentReportFB" property="strLabCombo" filter="false" />
					</select>
				</div>
			</td>
		</tr>
		<tr>
			<td width="50%" colspan="2" class="LABEL">
			Report Detail Type
			</td>
			<td width="50%" colspan="2" class="CONTROL">
			<select name="strReportDetailType" class="combo1"  onchange="changeVisibility(this);">
			<option value="-1">Select Value</option>
			<option value="Textual">Textual Information</option>
			<option value="Statistical">Statistical Information</option></select>
			</td>
			
		</tr> 
		<tr><td colspan="4" width="100%">
		<table id="complaintTableId" style="display: none;">
		<tr>
			<td width="50%" colspan="2" class="LABEL">
			Complaint Number
			</td>
			<td width="50%" colspan="2" class="CONTROL">
			<div id="complaintNoId">
					<select name="strComplaintNo" class="combo1">
					<option value="0">Select Value</option>
					</select>
				</div>
			</td>
		</tr> 
		<tr>
			<td width="50%" colspan="2" class="LABEL">
			Report Generation on the basis of
			</td>
			<td width="50%" colspan="2" class="CONTROL">
			<div align="center" style="display: block;"><html:checkbox property="strIsRaisingChecked" name="equipmentReportFB" value="1">Raising</html:checkbox></div>
			<div align="center" style="display: none;"><html:checkbox property="strIsAckByApprovalChecked" name="equipmentReportFB" value="1">Acknowledged By Approving Authority</html:checkbox></div>
			<div align="center" style="display: block;"><html:checkbox property="strIsApprovalChecked" name="equipmentReportFB" value="1">Approved</html:checkbox></div>
			<div align="center" style="display: none;"><html:checkbox property="strIsAckByHEMMChecked" name="equipmentReportFB" value="1">Acknowledged By HEMM</html:checkbox></div>
			<div align="center" style="display: block;"><html:checkbox property="strIsSchedulingChecked" name="equipmentReportFB" value="1">Scheduling</html:checkbox></div>
			<div align="center" style="display: block;"><html:checkbox property="strIsAttendChecked" name="equipmentReportFB" value="1">Attend</html:checkbox></div>
			<div align="center" style="display: none;"><html:checkbox property="strIsAckByEngineerChecked" name="equipmentReportFB" value="1">Acknowledged By Engineer</html:checkbox></div>
			<div align="center" style="display: block;"><html:checkbox property="strIsServiceActionChecked" name="equipmentReportFB" value="1">Service Action</html:checkbox></div>
			<div align="center" style="display: block;"><html:checkbox property="strIsClosingChecked" name="equipmentReportFB" value="1">Closing</html:checkbox></div>
			</td>
			
		</tr>
		</table>
		</td>
		</tr>
		<tr><td >
		<table id="dateTableId" style="display: none;">
		<tr>
			<td width="50%" colspan="2" class="LABEL">
			<font color="red">*</font>From Date
			</td>
			<td width="50%" colspan="2" class="CONTROL">
			<dateTag:date name="strFromDate" 
				 value="${equipmentReportFB.strCurrentDate}"/>
			</td>
		</tr> 
		<tr>
			<td width="50%" colspan="2" class="LABEL">
			<font color="red">*</font>To Date
			</td>
			<td width="50%" colspan="2" class="CONTROL">
				<dateTag:date name="strToDate" value="${equipmentReportFB.strCurrentDate}"/>
			</td>
			
		</tr>
		</table>
		</td>
		</tr>
		
		<tr>
			<td width="50%" colspan="2" class="LABEL">
			Footer Required
			</td>
			<td width="50%" colspan="2" class="CONTROL">
			<html:checkbox property="strIsFooter" name="equipmentReportFB" value="1"></html:checkbox>
			</td>
			
		</tr>
		</table>
		</div>
		
			<table class='TABLEWIDTH' align="center" cellspacing="1px" cellpadding="1px">
			<tr class="FOOTER">
			   <td colspan="4" ></td>
		    </tr>
				
			</table>
		


		<div id="showButtonID">
	<div><div class="legends"><font size="2" color="red">*</font> Mandatory Fields</div>
	<div class="control_button">
	<table  class="TABLEWIDTH" align="center">
	<tr>
	<td align="center"><div>
				<a href="#" class="button" onClick="return validate();"><span class="generate">Generate</span></a>
				<a href="#" class="button" onClick="document.forms[0].reset();" ><span class="clear">Clear</span></a>
				<a href="#" class="button" onClick="cancelPage();"><span class="cancel">Cancel</span></a>				 
				</div></td>
	</tr>	  
	</table>
	</div></div>	
		</div>

		<!-- Error Messages 	-->
		<br />
		<div id="strErrMsg" align="center" class="ERR_DIV"><bean:write
			name="equipmentReportFB" property="strErrMsg" /></div>
		<div id="strWarningMsg" align="center" class="WARNING_DIV"><bean:write
			name="equipmentReportFB" property="strWarningMsg" /></div>
		<div id="normalMsg" align="center" class="NORMAL_DIV"><bean:write
			name="equipmentReportFB" property="strNormalMsg" /></div>
		<input type="hidden" name="hmode"/>
        	
				
	
	<cmbPers:cmbPers />
</html:form>
</body>
</html>