
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
"http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<%@taglib uri="/WEB-INF/dateTag.tld" prefix="dateTag"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>
<%@ taglib uri="/WEB-INF/comboPersistence.tld" prefix="cmb"%>

<%--
/**
 * Developer Name : Vivek Aggarwal
 * Process Name : List Of Equipment Under Amc Report
 * Date : 21-June-2011
 * Module  : BMED
 * Modify By/Date : 
 */ 
 --%>

<html>
<head>

 <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<his:css src="/hisglobal/css/tab.css" />
<his:css src="/hisglobal/css/Color.css" />
<his:css src="/hisglobal/css/master.css" />
<his:css src="/hisglobal/css/hisStyle.css" />
<his:css src="/hisglobal/css/hisStyleExt.css" />
<his:css src="/hisglobal/css/calendar-tas.css" />

<his:javascript  src="/hisglobal/masterutil/js/master.js" />
<his:javascript  src="/hisglobal/js/util.js" />
<his:javascript  src="/hisglobal/js/tab.js" />
<his:javascript  src="/hisglobal/js/calendar.js" />
<his:javascript  src="/hisglobal/js/validation.js" />
<his:javascript  src="/hisglobal/js/time.js" />
<his:javascript  src="/hisglobal/js/commonFunctions.js" />


<his:javascript	src="/bmed/reports/js/bmed_listOfPendingComplaints_report.js" />

</head>
<body marginheight="0" marginwidth="0"  onload="onLoadPage();">

<html:form name="listOfPendingComplaintsReportFB" action="/reports/ListOfPendingComplaintsReportACTION"
	type="bmed.reports.controller.fb.ListOfPendingComplaintsReportFB">
	

	<his:TransactionContainer>
		<his:TitleTag name="List Of Pending Complaints">
		</his:TitleTag>	

		<his:ContentTag>
			<table class="TABLE_STYLE">
				<tr>
			<td class="LABEL_TD" colspan="4">
				
				<html:radio property="strIssueChkDetail" name="listOfPendingComplaintsReportFB" value="1" onclick="setValueChk();">Department</html:radio>
		    	<html:radio property="strIssueChkDetail" name="listOfPendingComplaintsReportFB" value="2" onclick="setValueChk();">Item</html:radio>
		    	<html:radio property="strIssueChkDetail" name="listOfPendingComplaintsReportFB" value="3" onclick="setValueChk();">Date</html:radio>
			
			</td>
		</tr>
	    
	    <logic:notEqual name="listOfPendingComplaintsReportFB"  property="strIssueChkDetail" value="3">
		<tr>
		 		
		 	<logic:equal name="listOfPendingComplaintsReportFB"  property="strIssueChkDetail" value="1">
				<td width="50%" colspan="2" class="LABEL_TD">
					<div id="id1" style="display:block;"><font color="red">*</font>Department Name</div>
				</td>
			</logic:equal>
			
			<logic:equal name="listOfPendingComplaintsReportFB"  property="strIssueChkDetail" value="2">
				<td width="50%" colspan="2" class="LABEL_TD">
					<div id="id1" style="display:block;"><font color="red">*</font>Item</div>
				</td>
			</logic:equal>
		 	
		 	
		 	<td width="50%" colspan="2" class="CONTROL_TD">
				<select name="strUniqId"   class="COMBO_NORMAL">
					<bean:write name="listOfPendingComplaintsReportFB" property="strUniqValCmb" filter="false" />
				</select>
			</td>	
		 		
		</tr>
		 
		</logic:notEqual>
		
		<tr>
			<td width="50%" colspan="2" class="LABEL_TD">
				<font color="red">*</font>From Date
			</td>
			<td width="50%" colspan="2" class="CONTROL_TD">
				<dateTag:date name="strFromDate" value="${listOfPendingComplaintsReportFB.strCurrentDate}"/>
			</td>
			
		</tr> 
		<tr>
			<td width="50%" colspan="2" class="LABEL_TD">
				<font color="red">*</font>To Date
			</td>
			<td width="50%" colspan="2" class="CONTROL_TD">
				<dateTag:date name="strToDate" value="${listOfPendingComplaintsReportFB.strCurrentDate}"/>
			</td>
			
		</tr> 
		<tr>
			<td width="50%" colspan="2" class="LABEL_TD">Report Format
			</td>
			
			<td width="50%" colspan="2" class="CONTROL_TD">
				<select name="strReportFormat"  onchange="">
					<option value="html">Html</option>
					<option value="pdf">Pdf</option>
				</select>
			</td>
			
		</tr> 
		
		<tr>
			<td width="50%" colspan="2" class="LABEL_TD">Footer Required
			</td>
			<td width="50%" colspan="2" class="CONTROL_TD">
				<html:checkbox property="strIsFooter" name="listOfPendingComplaintsReportFB" value="1"></html:checkbox>
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
		<div id="strErrMsg" align="center" class="ERR_DIV"><bean:write	name="listOfPendingComplaintsReportFB" property="strErrMsg" /></div>
		<div id="strWarningMsg" align="center" class="WARNING_DIV"><bean:write	name="listOfPendingComplaintsReportFB" property="strWarningMsg" /></div>
		<div id="normalMsg" align="center" class="NORMAL_DIV"><bean:write name="listOfPendingComplaintsReportFB" property="strNormalMsg" /></div>
			
		<input type="hidden" name="hmode"/>
        <input type="hidden" name="strCurrentDate" value="${listOfPendingComplaintsReportFB.strCurrentDate}" />
			
				
	</his:TransactionContainer>
	<cmb:cmbPers />
</html:form>
</body>
</html>