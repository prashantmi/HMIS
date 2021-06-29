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
<his:css src="/hisglobal/css/Color.css" />
<his:css src="/hisglobal/css/master.css" />
<his:css src="/hisglobal/css/hisStyle.css" />
<his:css src="/hisglobal/css/hisStyleExt.css" />
<his:css src="/hisglobal/css/calendar-tas.css" />
<his:javascript  src="/hisglobal/js/time.js" />
<his:javascript src="/hisglobal/js/util.js" />
<his:javascript src="/hisglobal/js/calendar.js" />
<his:javascript src="/hisglobal/js/validation.js" />
<his:javascript	src="/bmed/reports/js/hemms_complaint_status_report.js" />
</head>
<body marginheight="0" marginwidth="0">

<html:form  action="/reports/ComplaintStatusReportCNT"  method="post">
	<his:TransactionContainer>
		<his:TitleTag name="Complaint Status Report">
		</his:TitleTag>	

		<his:ContentTag>
				
		<div id="trItemId">
	    	<table width="100%" border="0" cellspacing="1" cellpadding="0">
	    <tr>
			<td width="20%" colspan="2" class="LABEL_TD">
			Report Format
			</td>
			<td width="50%" colspan="2" class="CONTROL_TD">
			<select name="strReportFormat"  onchange="">
			<option value="html">Html</option>
			<option value="pdf">Pdf</option></select>
			</td>
			
		</tr> 	
		<tr>
			<td width="20%" colspan="2" class="LABEL_TD"><font color="red">*</font>Hospital Name</td>
			<td width="50%" colspan="2" class="CONTROL_TD">
				<div id="hospitalNameId">
					<select name="strDeptCode"   class="combo1" onchange="getStoreName()">
					<bean:write name="complaintStatusReportFB" property="strHospitalCombo" filter="false" />
					</select>
				</div>
			</td>
		</tr>
		<tr>
			<td width="20%" colspan="2" class="LABEL_TD"><font color="red">*</font>Lab Name</td>
			<td width="50%" colspan="2" class="CONTROL_TD">
				<div id="labNameId">
					<select name="strLabCode" class="combo1">
					<option value="0">Select Value</option>
					<bean:write name="complaintStatusReportFB" property="strLabCombo" filter="false" />
					</select>
				</div>
			</td>
		</tr>
		<tr>
			<td width="20%" colspan="2" class="LABEL_TD">
			Report Detail Type
			</td>
			<td width="50%" colspan="2" class="CONTROL_TD">
			<select name="strReportDetailType" class="combo1"  onchange="getComplaintNumber();">
			<option value="-1">Select Value</option>
			<option value="Textual">Textual Information</option>
			</td>
			
		</tr> 
		<tr>
			<td width="20%" colspan="2" class="LABEL_TD">
			Complaint Number
			</td>
			<td width="50%" colspan="2" class="CONTROL_TD">
			<div id="complaintNoId">
					<select name="strComplaintNo" class="combo1">
					<option value="0">Select Value</option>
					</select>
				</div>
			</td>
		</tr> 
		
		<tr>
			<td width="20%" colspan="2" class="LABEL_TD">
			Footer Required
			</td>
			<td width="50%" colspan="2" class="CONTROL_TD">
			<html:checkbox property="strIsFooter" name="complaintStatusReportFB" value="1"></html:checkbox>
			</td>
			
		</tr>
		</table>
		</div>
		</his:ContentTag>
		
		
		
		<his:ContentTag>
			<table class="TABLE_STYLE">

				<tr class="FOOTER_TR">
					 <td colspan="4"><font color='red'>*</font>Mandatory Field </td>
				</tr>
			</table>
		</his:ContentTag>


		<his:ButtonToolBarTag>
			
			<img style="cursor: pointer" src="/HEMMS_ODISHA/hisglobal/images/btn-generate.png" onClick="return validate();" />
			<img style="cursor: pointer" src="/HEMMS_ODISHA/hisglobal/images/btn-clr.png" onClick="document.forms[0].reset();" >
			<img style="cursor: pointer" src="/HEMMS_ODISHA/hisglobal/images/btn-ccl.png" onClick="cancelPage();" >
		</his:ButtonToolBarTag>

		<!-- Error Messages 	-->
		<br />
		<div id="strErrMsg" align="center" class="ERR_DIV"><bean:write
			name="complaintStatusReportFB" property="strErrMsg" /></div>
		<div id="strWarningMsg" align="center" class="WARNING_DIV"><bean:write
			name="complaintStatusReportFB" property="strWarningMsg" /></div>
		<div id="normalMsg" align="center" class="NORMAL_DIV"><bean:write
			name="complaintStatusReportFB" property="strNormalMsg" /></div>
		<input type="hidden" name="hmode"/>
        	
				
	</his:TransactionContainer>
	<cmbPers:cmbPers />
</html:form>
</body>
</html>