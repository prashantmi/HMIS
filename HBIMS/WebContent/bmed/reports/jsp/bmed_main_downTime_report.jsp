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
 * @author Anshul Vaid
 * Date of Creation : 06-July-2012
 * Date of Modification :  
 * Version : 
 * Module  : BMED
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
<his:javascript src="/hisglobal/js/time.js" />
<his:javascript src="/hisglobal/js/util.js" />
<his:javascript src="/hisglobal/js/calendar.js" />
<his:javascript src="/hisglobal/js/validation.js" />
<his:javascript src="/bmed/reports/js/bmed_downTime_report.js" />
</head>
<body marginheight="0" marginwidth="0" onload="onLoadPage();">

<html:form action="/reports/DownTimeRptACTION" method="post">
	<his:TransactionContainer>
		<his:TitleTag name="Down Time Report">
		</his:TitleTag>

		<his:ContentTag>
			<table width="100%" border="0" cellspacing="1" cellpadding="0">
				<tr>
					<td class="LABEL_TD" colspan="4"><html:radio
						property="issueChkDetail" name="downTimeRpt" value="1"
						onclick="setValueChk();">Item Wise</html:radio> <html:radio
						property="issueChkDetail" name="downTimeRpt" value="2"
						onclick="setValueChk();">Date Wise</html:radio> <html:radio
						property="issueChkDetail" name="downTimeRpt" value="3"
						onclick="setValueChk();">Detailed</html:radio></td>
				</tr>
			</table>


			<div id="trItemId">
			<table width="100%" border="0" cellspacing="1" cellpadding="0">
				<tr>

					<td width="50%" colspan="2" class="LABEL_TD">
					<div align="right"><font color="red">*</font>Item Name</div>
					</td>
					<td width="50%" colspan="2" class="CONTROL_TD">
					<div id="itemCmb" style="display: block;" align="left"><select
						name="strItemId" class="combo1">
						<bean:write name="downTimeRpt" property="strItemCmb"
							filter="false" />
					</select></div>
					</td>
				</tr>
			</table>
			</div>
			<div id="trComplaint">
			<table width="100%" border="0" cellspacing="1" cellpadding="0">
				<tr>

					<td width="50%" colspan="2" class="LABEL_TD">
					<div align="right"><font color="red">*</font>Complaint ID</div>
					</td>
					<td width="50%" colspan="2" class="CONTROL_TD">
					<div align="left"><html:text name="downTimeRpt"
						property="strComplaintId" maxlength="10">
					</html:text></div>
					</td>
				</tr>
			</table>
			</div>


			<table width="100%" border="0" cellspacing="1" cellpadding="0">
				<tr>
					<td width="50%" colspan="2" class="LABEL_TD"><font color="red">*</font>From
					Date</td>
					<td width="50%" colspan="2" class="CONTROL_TD"><dateTag:date
						name="strFromDate" value="${downTimeRpt.strCurrentDate}" /></td>

				</tr>
				<tr>
					<td width="50%" colspan="2" class="LABEL_TD"><font color="red">*</font>To
					Date</td>
					<td width="50%" colspan="2" class="CONTROL_TD"><dateTag:date
						name="strToDate" value="${downTimeRpt.strCurrentDate}" /></td>

				</tr>
				<tr>
					<td width="50%" colspan="2" class="LABEL_TD">Report Format</td>
					<td width="50%" colspan="2" class="CONTROL_TD"><select
						name="strReportFormat" onchange="">
						<option value="html">Html</option>
						<option value="pdf">Pdf</option>
					</select></td>

				</tr>
				<tr>
					<td width="50%" colspan="2" class="LABEL_TD">Footer Required</td>
					<td width="50%" colspan="2" class="CONTROL_TD"><html:checkbox
						property="strIsFooter" name="downTimeRpt" value="1"></html:checkbox>
					</td>

				</tr>
				<tr>
					<td width="50%" colspan="2" class="LABEL_TD">User Remarks</td>
					<td width="50%" colspan="2" class="CONTROL_TD"><input
						class="txtFldMax" type="text" name="strUserRemarks"
						maxlength="100"></td>

				</tr>

			</table>
		</his:ContentTag>



		<his:ContentTag>
			<table class="TABLE_STYLE">

				<tr class="FOOTER_TR">
					<td colspan="4"><font color='red'>*</font>Mandatory Field</td>
				</tr>
			</table>
		</his:ContentTag>


		<his:ButtonToolBarTag>

			<img style="cursor: pointer"
				src="/AHIMS/hisglobal/images/btn-generate.png"
				onClick="return validate();" />
			<img style="cursor: pointer"
				src="/AHIMS/hisglobal/images/btn-clr.png"
				onClick="document.forms[0].reset();">
			<img style="cursor: pointer"
				src="/AHIMS/hisglobal/images/btn-ccl.png" onClick="cancelPage();">
		</his:ButtonToolBarTag>

		<!-- Error Messages 	-->
		<br />
		<div id="strErrMsg" align="center" class="ERR_DIV"><bean:write 
			name="downTimeRpt" property="strErrMsg" /></div>
		<div id="strWarningMsg" align="center" class="WARNING_DIV"><bean:write
			name="downTimeRpt" property="strWarningMsg" /></div>
		<div id="normalMsg" align="center" class="NORMAL_DIV"><bean:write
			name="downTimeRpt" property="strNormalMsg" /></div>
		<input type="hidden" name="hmode" />
		<input type="hidden" name="strCurrentDate"
			value="${downTimeRpt.strCurrentDate}" />
		<input type="hidden" name="strUniqId" />


	</his:TransactionContainer>
	<cmbPers:cmbPers />
</html:form>
</body>
</html>