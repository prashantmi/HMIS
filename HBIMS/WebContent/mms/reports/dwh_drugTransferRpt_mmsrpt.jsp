<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/dateTag.tld" prefix="dateTag"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>


<html>
<head>
<title>Drug Transfer Report</title>
<link href="../css/master.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet"
	type="text/css">
<link href="../../hisglobal/css/calendar-tas.css" rel="stylesheet"
	type="text/css">

<link href="../../hisglobal/css/popup.css" rel="stylesheet"
	type="text/css">

<script language="JavaScript" src="../../masterutil/js/master.js"></script>
<script language="JavaScript" src="../../hisglobal/js/tab.js"></script>
<script language="JavaScript" src="../../hisglobal/js/calendar.js"></script>
<script language="Javascript" src="../../hisglobal/js/validation.js"></script>
<script language="Javascript" src="../../hisglobal/js/util.js"></script>
<script language="Javascript" src="../../mms/js/dwh_drugTransferRpt_mmsrpt.js"></script>

</head>
<body onload="onBodyDisplay();">
<html:form action="/reports/DrugTransferRptCNT" method="post">

	<div class="errMsg" id="errMsg">
		<bean:write name="drugTransferRptFB" property="strErrMsg" />
	</div>
	<div class="normalMsg" id="normalMsg">
		<bean:write name="drugTransferRptFB" property="strNormalMsg" />
	</div>
	<div class="warningMsg" id="warningMsg">
		<bean:write name="drugTransferRptFB" property="strWarningMsg" />
	</div>


	<tag:tab tabLabel="Drug Transfer Report" selectedTab="FIRST" align="center" width="TABLEWIDTH">
	</tag:tab>

	<table class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="1px">
		<tr class="HEADER">
			<td colspan="4"></td>
		</tr>
	
		

	<tr>
		
	
			<td colspan="2" class="LABEL" width="50%" align="right">
				<html:checkbox name="drugTransferRptFB" property="strDateWise" value="1" />
				
			</td>
		
		
			<td colspan="2" class="CONTROL" width="50%" align="right">
				Whether Date Wise
			</td>			
	</tr>

	
		<%-- District Drug warehouse Name	 --%>	
			
		<tr>
			<td width="50%" colspan="2" class="LABEL"><font color="red">*</font>Item Category</td>
			<td width="50%" colspan="2" class="CONTROL">
			<div id="itemCatDivId">
			<select name="strItemCatNo" class="comboNormal" onchange="getStoreCmb();">
					<bean:write name="drugTransferRptFB" property="strItemCategoryValues" filter="false" />
			</select>
			</div>
			</td>
		</tr>
						
		<tr	>
			<td width="50%" colspan="2" class="LABEL"><font color="red">*</font>From DDW Name</td>
			<td width="50%" colspan="2" class="CONTROL">
			<div id="drugWarehouseDivId">
				<select name="strStoreId" class="comboNormal" onchange="getItemCatCmb();">				
					<bean:write name="drugTransferRptFB" property="strStoreValues" filter="false" />
				</select>
			</div>	
			</td>
		</tr>
		
		
		
		<tr>
			<td width="50%" colspan="2" class="LABEL">To DDW Name</td>
			<td width="50%" colspan="2" class="CONTROL">
			<div id="toStoreDivId">
				<select name="strToStoreId" class="comboNormal" onchange='getToStoreCmb();'>
					<bean:write name="drugTransferRptFB" property="strToStoreValues" filter="false" />
				</select>
			</div>
			</td>
		</tr>
		<tr>
			<td width="50%" colspan="2" class="LABEL">Batch No.</td>
			<td width="50%" colspan="2" class="CONTROL">
				<html:checkbox property="strBatchNo" name="drugTransferRptFB" value="1" onclick="onClickBatch();">
				</html:checkbox>
			</td>

		</tr>
		
		  <tr>
			<td class="LABEL" colspan="2"><font color="red">*</font>From Date</td>
			<td class="CONTROL" colspan="2"><dateTag:date name="strFromDate" value="${drugTransferRptFB.strCurrentDate}" /></td>
		</tr>
		<tr>
			<td class="LABEL" colspan="2"><font color="red">*</font>To Date</td>
			<td class="CONTROL" colspan="2"><dateTag:date name="strToDate" value="${drugTransferRptFB.strCurrentDate}" /></td>
		</tr>
	</table>

	<div id="dateDivId" style="display: none;">
	<table class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="1px">
		<tr>
			<td class="LABEL" width="50%" colspan="2"><font color="red">*</font>Date</td>
			<td class="CONTROL" width="50%" colspan="2">
				<dateTag:date name="strDate" value="${drugTransferRptFB.strCurrentDate}" /></td>
		</tr>
	</table>
	</div>

	<table class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="1px">
		<tr>
			<td width="50%" colspan="2" class="LABEL">Report Format</td>
			<td width="50%" colspan="2" class="CONTROL">
			<select name="strReportFormat" onchange="">
					<option value="html">Html</option>
					<option value="pdf">Pdf</option>
				</select>
			</td>

		</tr>

		<tr>
			<td width="50%" colspan="2" class="LABEL">Footer Required</td>
			<td width="50%" colspan="2" class="CONTROL">
			<html:checkbox property="strIsFooter" name="drugTransferRptFB" value="1"></html:checkbox>
			</td>

		</tr>
		<tr>
			<td width="50%" colspan="2" class="LABEL">User Remarks</td>
			<td width="50%" colspan="2" class="CONTROL">
				<input class="txtFldMax" type="text" name="strUserRemarks">
			</td>

		</tr>
		<tr class="FOOTER">
			<td colspan="4"><font color='red'>*</font>Mandatory Field</td>
		</tr>
	</table>
	<table border="0" class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="1px">
		<tr>

			<td align="center">
				<img style=" cursor: pointer" src="../../hisglobal/images/btn-generate.png" onClick="return validate1();" /> 
				<img style=" cursor: pointer" src="../../hisglobal/images/btn-clr.png" onClick="onClickClear();">
				<img style=" cursor: pointer" src="../../hisglobal/images/btn-ccl.png" onClick="cancelFunc();">
			</td>

		</tr>
	</table>
	<input type="hidden" name="hmode" />
	<input type="hidden" name="strStoreName" value="${drugTransferRptFB.strStoreName}" />
	<input type="hidden" name="strToStoreName" value="${drugTransferRptFB.strToStoreName}" />
	<input type="hidden" name="strCurrentDate" value="${drugTransferRptFB.strCurrentDate}" />
	<input type="hidden" name="strCurrentStock" value="${drugTransferRptFB.strCurrentStock}" />



</html:form>
<tag:autoIndex></tag:autoIndex>
</body>
</html>
