<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/dateTag.tld" prefix="dateTag"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>


<html>
<head>
<title>Application Error Log Detail</title>
<link href="../css/master.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/calendar-tas.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/buttons.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/popup.css" rel="stylesheet" type="text/css">	
	
<script language="JavaScript" src="../../masterutil/js/master.js"></script>
<script language="JavaScript" src="../../hisglobal/js/tab.js"></script>
<script language="JavaScript" src="../../hisglobal/js/calendar.js"></script>
<script language="Javascript" src="../../hisglobal/js/validation.js"></script>
<script language="Javascript" src="../../hisglobal/js/util.js"></script>
<script language="JavaScript" src="../js/dwh_applicationErrorLogDtl_rpt.js"></script>
<script language="Javascript" src="../../hisglobal/js/time.js" /></script>

</head>
<body onload="onLoadPage();">
<html:form action="/reports/ApplicationErrorLogDetailRptCNT" method="post">
	
<div class="errMsg" id="errMsg"><bean:write name="applicationErrorLogDetailRptFB" property="strErrMsg"/></div>
<div class="normalMsg" id="normalMsg"><bean:write name="applicationErrorLogDetailRptFB" property="strNormalMsg"/></div>
<div class="warningMsg" id="warningMsg"><bean:write name="applicationErrorLogDetailRptFB" property="strWarningMsg"/></div>


	<tag:tab tabLabel="Application Error Log Detail" selectedTab="FIRST" align="center" width="TABLEWIDTH"></tag:tab>

	<table class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="1px" >   
		
		<tr class="HEADER">
			<td colspan="3">Application Error Log Detail</td>
			 <td align="right" >
		     	<span>Whether Consolidated<input type="checkbox" name="strWhetherConsolidated" value="1" /></span>
		     </td>
		</tr>
		
		<tr>	
			
				<td class="LABEL" colspan="2">Error Id</td>
				<td class="CONTROL" colspan="2">				
					<input type="text" name="strErrorId" maxlength="15" onkeypress="return validateData(event,5);">
				</td>
		</tr>
		<tr>
			<td class="LABEL" colspan="2"><font color="red">*</font>From Date</td>
			<td class="CONTROL" colspan="2"><dateTag:date name="strFromDate" value="${applicationErrorLogDetailRptFB.strCurrentDate}" />
				<input type="text" class="TEXT_FIELD_MIN" size="5" maxlength="5" name="strPreferredFromTime" value="${applicationErrorLogDetailRptFB.strPreferredFromTime}" onkeypress='return checkTime(event,this);' /> (HH:MM 24Hr)
			</td>
		</tr>
		<tr>
			<td class="LABEL" colspan="2"><font color="red">*</font>To Date</td>
			<td class="CONTROL" colspan="2">
				<dateTag:date name="strToDate" value="${applicationErrorLogDetailRptFB.strCurrentDate}" />
					<input type="text" class="TEXT_FIELD_MIN" size="5" maxlength="5" name="strPreferredToTime" value="${applicationErrorLogDetailRptFB.strPreferredToTime}" onkeypress='return checkTime(event,this);' /> (HH:MM 24Hr)
			</td>
		</tr>
		
		<tr>
			<td width="50%" colspan="2" class="LABEL">Report Format</td>
			<td width="50%" colspan="2" class="CONTROL">
				<select name="strReportFormat"  onchange="">
					<option value="html">Html</option>
					<option value="pdf">Pdf</option>
					<option value="xls">Excel</option>
				</select>
			</td>
		</tr> 
		
		<tr>
			<td width="50%" colspan="2" class="LABEL">Footer Required</td>
			<td width="50%" colspan="2" class="CONTROL">
				<html:checkbox property="strIsFooter" name="applicationErrorLogDetailRptFB" value="1"></html:checkbox>
			</td>
		</tr>
		
		<tr>
			<td width="50%" colspan="2" class="LABEL">User Remarks</td>
			<td width="50%" colspan="2" class="CONTROL">
				<input class="txtFldMax" type="text" name="strUserRemarks" >
			</td>
		</tr>
		
		<tr class="FOOTER">
			 <td colspan="4"><font size="2" color="red">*</font> Mandatory Fields  </td>
		</tr>
	</table>
	
<!--	<table border="0" class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="1px">
		<tr>

			<td align="center">
			<img style="cursor: pointer" src="../../hisglobal/images/btn-generate.png" onClick="return validate();" />
			<img style="cursor: pointer" src="../../hisglobal/images/btn-clr.png" onClick="onClearPage();" >
			<img style="cursor: pointer" src="../../hisglobal/images/btn-ccl.png" onClick="cancelFunc();" >
			</td>
			
		</tr>
	</table>-->
	<br>
<div align="center" id="">					 
					 	<a href="#" class="button" id="" onclick=' return validate();'><span class="generate">Generate</span></a>
						<a href="#" class="button"	onclick="onClearPage"><span class="clear">Clear</span></a> 
						<a href="#" class="button" onclick="cancelFunc();"><span class="cancel">Cancel</span></a>
				</div>
	
<input type="hidden" name="hmode"/>
<input type="hidden" name="strCurrentDate" value="${applicationErrorLogDetailRptFB.strCurrentDate}"/>


</html:form>
<tag:autoIndex></tag:autoIndex>
</body>
</html>