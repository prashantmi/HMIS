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
 * @author   Adil Wasi
 * Creation Date:- 29-Aug-2012 
 * Modifying Date:- 
 * Used For:-
 * Module:- BMED(HIS Project) Service Engineer Wise Job Status Reports
 * 
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
<his:javascript	src="/bmed/reports/js/bmed_ServiceEngineerWiseJobStatus_report.js" />
</head>
<body marginheight="0" marginwidth="0" onload="onLoadPage();" >
<html:form  action="/reports/ServiceEngineerWiseJobStatusReportACTION"  method="post">
	<his:TransactionContainer>
		<his:TitleTag name="Service Engineer Wise Job Status">
		</his:TitleTag>	

		<his:ContentTag>
			<table class="TABLE_STYLE">
				
	    
		<tr	id="comboId">
		 		
				<td width="50%" colspan="2" class="LABEL_TD">
					<div id="id1" style="dispaly:block;">
						<font color="red">*</font>Service Engineer Name
					</div>
				</td>
				<td width="50%" colspan="2" class="CONTROL_TD">
					<div id="serviceEngineerCmb" style="display:block;">
					 <select name="strServiceEngineerId"   class="comboNormal">
					   <bean:write name="serviceEngineerWiseJobStatusReportFB" property="strServiceEngineerCmb" filter="false" />
					 </select>
					 </div>
				</td>
		</tr> 
		
		
		<tr>
			<td width="50%" colspan="2" class="LABEL_TD">
			<font color="red">*</font>From Date
			</td>
			<td width="50%" colspan="2" class="CONTROL_TD">
				<dateTag:date name="strFromDate" 
				 value="${serviceEngineerWiseJobStatusReportFB.strCurrentDate}"/>
			</td>
			
		</tr> 
		<tr>
			<td width="50%" colspan="2" class="LABEL_TD">
			<font color="red">*</font>To Date
			</td>
			<td width="50%" colspan="2" class="CONTROL_TD">
				<dateTag:date name="strToDate" 
				 value="${serviceEngineerWiseJobStatusReportFB.strCurrentDate}"/>
			</td>
			
		</tr> 
		<tr>
			<td width="50%" colspan="2" class="LABEL_TD">
			Report Format
			</td>
			<td width="50%" colspan="2" class="CONTROL_TD">
			<select name="strReportFormat"  onchange="">
			<option value="html">Html</option>
			<option value="pdf">Pdf</option></select>
			</td>
			
		</tr> 
		<tr>
			<td width="50%" colspan="2" class="LABEL_TD">
			Footer Required
			</td>
			<td width="50%" colspan="2" class="CONTROL_TD">
			<html:checkbox property="strIsFooter" name="serviceEngineerWiseJobStatusReportFB" value="1"></html:checkbox>
			</td>
			
		</tr>
		<tr>
			<td width="50%" colspan="2" class="LABEL_TD">
			User Remarks
			</td>
			<td width="50%" colspan="2" class="CONTROL_TD">
			<input class="txtFldMax" type="text" name="strUserRemarks" maxlength="100">
			</td>
			
		</tr>
	
			</table>
		</his:ContentTag>
		
		
		
		<his:ContentTag>
			<table class="TABLE_STYLE">

				<tr class="FOOTER_TR">
					 <td colspan="4"><font color='red'>*</font>Mandatory Field </td>
				</tr>
			</table>
		</his:ContentTag>


		<his:ButtonToolBarTag>
			
			<img style="cursor: pointer" src="/HBIMS/hisglobal/images/btn-generate.png" onClick="return validate();" />
			<img style="cursor: pointer" src="/HBIMS/hisglobal/images/btn-clr.png" onClick="document.forms[0].reset();" >
			<img style="cursor: pointer" src="/HBIMS/hisglobal/images/btn-ccl.png" onClick="cancelPage();" >
		</his:ButtonToolBarTag>

		<!-- Error Messages 	-->
		<br />
		<div id="strErrMsg" align="center" class="ERR_DIV"><bean:write
			name="serviceEngineerWiseJobStatusReportFB" property="strErrMsg" /></div>
		<div id="strWarningMsg" align="center" class="WARNING_DIV"><bean:write
			name="serviceEngineerWiseJobStatusReportFB" property="strWarningMsg" /></div>
		<div id="normalMsg" align="center" class="NORMAL_DIV"><bean:write
			name="serviceEngineerWiseJobStatusReportFB" property="strNormalMsg" /></div>
		<input type="hidden" name="hmode"/>
        <input type="hidden" name="strCurrentDate" value="${serviceEngineerWiseJobStatusReportFB.strCurrentDate}" />
        <!--<input type="hidden" name="strUniqId" />-->
		</his:TransactionContainer>
	<cmbPers:cmbPers />
</html:form>
</body>
</html>