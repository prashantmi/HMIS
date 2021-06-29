<%-- <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%> --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
"http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/dateTag.tld" prefix="dateTag"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>
<%@ taglib uri="/WEB-INF/comboPersistence.tld" prefix="cmbPers"%>

<!-- 
/**
 * @author Vivek Aggarwal
 * Date of Creation : 11-April-2011
 * Date of Modification :  12-April-2011 
 * Module  : BMED
 */
 -->
<html>
<head>
<!-- <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"> -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<META Http-Equiv="Cache-Control" Content="no-cache" />
<META Http-Equiv="Cache-Control" Content="no-store" />
<META Http-Equiv="Pragma" Content="no-cache" />
<META Http-Equiv="Expires" Content="0" />
 <%
	response.setHeader("Cache-Control", "no-cache");
	response.setHeader("Pragma", "no-cache");
	response.setDateHeader("Expires", 0);
	response.setHeader("Cache-Control", "no-store");
%>
<title>HEM Config Master</title>

<link href="../css/master.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet"
	type="text/css">
<link href="../../hisglobal/css/calendar-tas.css" rel="stylesheet"
	type="text/css">

<script language="JavaScript"
	src="../../hisglobal/masterutil/js/master.js"></script>
<script language="Javascript" src="../../hisglobal/js/util.js"></script>
<script language="JavaScript" src="../../hisglobal/js/tab.js"></script>
<script language="JavaScript" src="../../hisglobal/js/calendar.js"></script>
<script language="Javascript" src="../../hisglobal/js/validation.js"></script>
<script language="Javascript" src="../../bmed/js/hemConfigMst.js"></script>
</head>

<body>
<html:form name="hemConfigBean" action="/masters/HemConfigMstCNT"
	type="bmed.masters.controller.fb.HemConfigMstFB">

	<div id="strErrMsg" class="errMsg">
		<bean:write name="hemConfigBean" property="strErrMsg" />
	</div>
	
	<div class="warningMsg">
		<bean:write name="hemConfigBean" property="strWarningMsg" />
	</div>
	
	<div id="normalMsg" class="normalMsg">
		<bean:write name="hemConfigBean" property="strNormalMsg" />
	</div>

	<table class="TABLEWIDTH" align="center" cellpadding="1px" cellspacing="1px">
		
		<tr class="HEADER">
			<td colspan="2">HEM Config Master</td>
		</tr>

		
		<tr>
			<td class="LABEL"><font color="red">*</font>Show Expiry Maintenance Contract Details Upto</td>
			<td width="50%" class="CONTROL">
				<input type="text" name="strShowMcDetailsUpto" value="${hemConfigBean.strShowMcDetailsUpto}" maxlength="3"tabindex="1" onkeypress="return validateData(event,5);" onclick="clearNormalMessage();"> (0-365)
			</td>
		</tr>
		
		<tr>
			<td class="LABEL"><font color="red">*</font>Show Expiry Warranty Contract Details Upto</td>
			<td width="50%" class="CONTROL">
				<input type="text" name="strShowWarrantyDetailsUpto" value="${hemConfigBean.strShowWarrantyDetailsUpto}" maxlength="3"tabindex="1" onkeypress="return validateData(event,5);" onclick="clearNormalMessage();"> (0-365)
			</td>
		</tr>
		
		<tr>
			<td class="LABEL" width="50%"><font color="red">*</font>Financial Start Date</td>
			<td class="CONTROL">
				<dateTag:date name="strFinancialStartDate" value="${hemConfigBean.strFinancialStartDate}">
				</dateTag:date>
			</td>
		</tr>
		
		<tr>
			<td class="LABEL" width="50%"><font color="red">*</font>Financial End Date</td>
			<td class="CONTROL">
				<dateTag:date name="strFinancialEndDate" value="${hemConfigBean.strFinancialEndDate}" >
				</dateTag:date>
			</td>
		</tr>

		<tr>
			<td class="LABEL"><font color="red">*</font>Whether HEM Department Maintain Item Under AMC/Warranty</td>
			<td width="45%" class="CONTROL">
			<html:radio name="hemConfigBean" property="strItemUnderAmcOrWarranty" value="1" onclick="clearNormalMessage();">Yes</html:radio>
			<html:radio name="hemConfigBean" property="strItemUnderAmcOrWarranty" value="0" onclick="clearNormalMessage();">No</html:radio>

			</td>
		</tr>

		<tr>
			<td class="LABEL"><font color="red">*</font>Folder Name to save Module Related File</td>
			<td width="50%" class="CONTROL">
				<input type="text" name="strFolderName" value="${hemConfigBean.strFolderName}" maxlength="50" 
					   tabindex="1" onkeypress="return validateData(event,20);" onclick="clearNormalMessage();">
			</td>
		</tr>

		<tr class="FOOTER">
			<td colspan="2"><font size="2" color="red">*</font> Mandatory Fields</td>
		</tr>
	</table>

	<table CLASS="TABLEWIDTH" align="center" cellpadding="1px" cellspacing="1px">
		<tr>
			<td align="center">
				<img src="../../hisglobal/images/btn-sv.png" style="cursor: pointer;" title="Save Record"  onClick="validateAdd();" onkeypress="if(event.keyCode==13) validateAdd();" />
				<img src="../../hisglobal/images/btn-clr.png" style="cursor: pointer;" title="Clear Process"  onClick="document.forms[0].reset();" onkeypress="" />
			</td>
		</tr>
	</table>
	
	<input type="hidden" name="hmode" />

	
	
	

	<cmbPers:cmbPers />
</html:form>

</body>
</html>
