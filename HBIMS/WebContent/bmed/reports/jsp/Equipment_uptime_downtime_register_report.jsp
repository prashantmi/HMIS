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
 * @author Paras Jain
 * Date of Creation : 18-June-2014
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
<his:javascript	src="/bmed/reports/js/bmed_equip_updown_time_manpower.js" />
</head>
<body marginheight="0" marginwidth="0" class="background">

<html:form  action="/reports/ManpowerAssignmentDeskCNT"  method="post" styleClass="formbg">
	
			
		<div id="trItemId">
		<table class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="1px">
		<tr class="HEADER">
			<td colspan="4">Equipment Uptime/Downtime Register Report</td>		</tr>
	    	
	    <tr>
			<td class="LABEL" colspan="4">
				
				<html:radio property="strReportType" name="ManpowerAssignmentDeskRptFB" value="1" onclick="">Equipment Downtime/Uptime </html:radio>
		    	<html:radio property="strReportType" name="ManpowerAssignmentDeskRptFB" value="2" onclick="">Equipment No. of times Up/Down </html:radio>
			
			</td>
		</tr>	
	   	
		<tr>
			<td width="50%" colspan="2" class="LABEL"><font color="red">*</font>Hospital Name</td>
			<td width="50%" colspan="2" class="CONTROL">
				<div id="hospitalNameId">
					<select name="strDeptCode"  class="comboNormal" onchange="getStoreName()">
					<bean:write name="ManpowerAssignmentDeskRptFB" property="strHospitalCombo" filter="false" />
					</select>
				</div>
			</td>
		</tr>
		<tr>
			<td width="50%" colspan="2" class="LABEL"><font color="red">*</font>Lab Name</td>
			<td width="50%" colspan="2" class="CONTROL">
				<div id="labNameId">
					<select name="strLabCode" class="comboNormal">
						<option value="0">---</option>
					<bean:write name="ManpowerAssignmentDeskRptFB" property="strLabCombo" filter="false" />
					</select>
				</div>
			</td>
		</tr>
		
		<tr>
			<td width="50%" colspan="2" class="LABEL">
			Report Format
			</td>
			<td width="50%" colspan="2" class="CONTROL">
			<select name="strReportFormat"  onchange="">
			<option value="html">HTML</option>
			<option value="pdf">PDF</option></select>
			</td>
			
		</tr>
		</table>
		</div>
		
			<table class='TABLEWIDTH' align="center" cellspacing="1px" cellpadding="1px">
			<tr class="FOOTER">
			   <td colspan="4" ></td>
		    </tr>
				
			</table>
			
			<div align="center">
<img class="button" onClick="return validate();" tabindex="1" style="cursor:pointer" src="/AHIMS/hisglobal/images/btn-view.png">
<img class="button" onkeypress="if(event.keyCode==13) cancelReport()" onClick="cancelPage();" tabindex="1" style="cursor:pointer" src="/AHIMS/hisglobal/images/btn-ccl.png">
<img class="button" onkeypress="if(event.keyCode==13) clearReport();" onClick="document.forms[0].reset();" tabindex="1" style="cursor:pointer" src="/AHIMS/hisglobal/images/btn-clr.png">
</div>

			<!--
		


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

		--><!-- Error Messages 	-->
		<br />
		<div id="strErrMsg" align="center" class="ERR_DIV"><bean:write
			name="ManpowerAssignmentDeskRptFB" property="strErrMsg" /></div>
		<div id="strWarningMsg" align="center" class="WARNING_DIV"><bean:write
			name="ManpowerAssignmentDeskRptFB" property="strWarningMsg" /></div>
		<div id="normalMsg" align="center" class="NORMAL_DIV"><bean:write
			name="ManpowerAssignmentDeskRptFB" property="strNormalMsg" /></div>
		<input type="hidden" name="hmode"/>
        	
		<input type="hidden" name="strStoreName"/>	
		<input type="hidden" name="strLabName;"/>	
	
	<cmbPers:cmbPers />
</html:form>
</body>
</html>