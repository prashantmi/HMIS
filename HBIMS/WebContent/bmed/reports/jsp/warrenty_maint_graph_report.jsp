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
 * @author Anshuman Singh
 * Date of Creation : 01-Jul-2014
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
<his:javascript	src="/bmed/reports/js/warrenty_maint_graph_report.js" />
</head>
<body marginheight="0" marginwidth="0" class="background">

<html:form  action="/reports/WarrentyMaintGraphReportCNT"  method="post" styleClass="formbg">
	
		
		<div id="trItemId">
		<table class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="1px">
		<tr class="HEADER">
			<td colspan="4">Warranty & Maintenance Graph Report</td>		</tr>
	    	
	    <tr>
			<td class="LABEL" colspan="4">
				
				<html:radio property="strReportType" name="warrentyMaintGraphReportFB" value="1" onclick="">Group Wise</html:radio>
		    	<html:radio style="display:none;" property="strReportType" name="warrentyMaintGraphReportFB" value="2" onclick=""></html:radio>
		    	<html:radio style="display:none;" property="strReportType" name="warrentyMaintGraphReportFB" value="3" onclick=""></html:radio>
			
			</td>
		</tr>	
	   	
		<tr>
			<td width="50%" colspan="2" class="LABEL"><font color="red">*</font>Hospital Name</td>
			<td width="50%" colspan="2" class="CONTROL">
				<div id="hospitalNameId">
					<select name="strDeptCode"  class="comboNormal" onchange="getStoreName()">
					<bean:write name="warrentyMaintGraphReportFB" property="strHospitalCombo" filter="false" />
					</select>
				</div>
			</td>
		</tr>
		<tr>
			<td width="50%" colspan="2" class="LABEL"><font color="red">*</font>Lab Name</td>
			<td width="50%" colspan="2" class="CONTROL">
				<div id="labNameId">
					<select name="strLabCode" class="comboNormal">
					<option value="0">All</option>
					<bean:write name="warrentyMaintGraphReportFB" property="strLabCombo" filter="false" />
					</select>
				</div>
			</td>
		</tr>
		<tr style="display:none;">
			<td width="50%" colspan="2" class="LABEL" >From Date</td>
			<td width="50%" colspan="2" class="CONTROL" >
				<dateTag:date name="strFromDate" value=""></dateTag:date>
			</td>
		</tr>
		<tr style="display:none;">
			<td width="50%" colspan="2" class="LABEL" >To Date</td>
			<td width="50%" colspan="2" class="CONTROL" >
				<dateTag:date name="strToDate" value=""></dateTag:date>
			</td>
		</tr>
		<tr style="display:none;">
			<td width="50%" colspan="2" class="LABEL"><font color="red">*</font>Equipment Status</td>
			<td width="50%" colspan="2" class="CONTROL">
				<div id="equipmentStatusId">
					<select name="strEquipmentStatusCode"  class="comboNormal" onchange="">
					<bean:write name="warrentyMaintGraphReportFB" property="strEquipmentStatusCombo" filter="false" />
					</select>
				</div>
			</td>
		</tr>
		<tr style="display:none;">
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
		
		<!--<table class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="1px">
		<tr>
			<td width="50%" colspan="2" class="LABEL">
			Footer Required
			</td>
			<td width="50%" colspan="2" class="CONTROL">
			<html:checkbox property="strIsFooter" name="warrentyMaintGraphReportFB" value="1"></html:checkbox>
			</td>
			
		</tr>
		</table>
		--></div>
		
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
			name="warrentyMaintGraphReportFB" property="strErrMsg" /></div>
		<div id="strWarningMsg" align="center" class="WARNING_DIV"><bean:write
			name="warrentyMaintGraphReportFB" property="strWarningMsg" /></div>
		<div id="normalMsg" align="center" class="NORMAL_DIV"><bean:write
			name="warrentyMaintGraphReportFB" property="strNormalMsg" /></div>
		<input type="hidden" name="hmode"/>
        	
		<input type="hidden" name="strStoreName"/>
		<input type="hidden" name="strLabName"/>
				
	
	<cmbPers:cmbPers />
</html:form>
</body>
</html>