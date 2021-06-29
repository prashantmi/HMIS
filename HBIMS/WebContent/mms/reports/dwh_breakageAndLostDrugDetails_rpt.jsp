<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/dateTag.tld" prefix="dateTag"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>


<html>
<head>
<title>Breakage/Lost Detail</title>
<link href="../css/master.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/calendar-tas.css" rel="stylesheet" type="text/css">

<link href="../../hisglobal/css/popup.css" rel="stylesheet" type="text/css">	
	
<script language="JavaScript" src="../../masterutil/js/master.js"></script>
<script language="JavaScript" src="../../hisglobal/js/tab.js"></script>
<script language="JavaScript" src="../../hisglobal/js/calendar.js"></script>
<script language="Javascript" src="../../hisglobal/js/validation.js"></script>
<script language="Javascript" src="../../hisglobal/js/util.js"></script>
<script language="Javascript" src="../../hisglobal/js/commonFunctions.js"></script>
<script language="Javascript" src="../../hisglobal/js/time.js"></script>

<script language="Javascript" src="../../mms/js/dwh_breakageAndLostDrugDetails_rpt.js"></script>


</head>
<body onload="onLoadPage();">

<html:form action="/reports/BreakageAndLostDrugDetailsRptCNT" method="post">
	
<div class="errMsg" id="errMsg"><bean:write name="breakageAndLostDrugDetailsRptFB" property="strErrMsg"/></div>
<div class="normalMsg" id="normalMsg"><bean:write name="breakageAndLostDrugDetailsRptFB" property="strNormalMsg"/></div>
<div class="warningMsg" id="warningMsg"><bean:write name="breakageAndLostDrugDetailsRptFB" property="strWarningMsg"/></div>


	<tag:tab tabLabel="Breakage/Lost Detail" selectedTab="FIRST" align="center" width="TABLEWIDTH">
                  </tag:tab>

	<table class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="1px" >   
	<tr class="HEADER">
			<td colspan="4"></td>
			
	</tr>
	</table>
			
	<table class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="1px" > 	
	
		<tr>
		
		<td width="50%" colspan="2" class="LABEL"></td>
		
			
			
			<td width="25%"  class="CONTROL">Breakage				
				<input type="radio" name="strBreakageOrLost" checked="checked" value="1" onclick="" />
			</td>
			
						
			<td width="25%"  class="CONTROL">Lost
				<input type="radio" name="strBreakageOrLost"  value="2" onclick="" />
			</td>
			
			
		</tr>
		
		
	
		<tr>
			<td width="50%" colspan="2" class="LABEL">
			
				<html:checkbox property="strWhetherConsolidatedDataForAllInstitutesOfDistrictDrugWarehouseCheckBox" name="breakageAndLostDrugDetailsRptFB" value="1" onclick="" onclick="setDistrictDrugWarehouseCombo();"/>
			
			</td>
			
			<td width="50%" colspan="2" class="CONTROL">Whether consolidated data for all Institutes of a District Drug Warehouse</td>
		</tr>
	

	<%-- District Drug warehouse Name	 --%>	
			
		<tr id="districtDrugWarehouseDivId" style="display: none;">
			<td width="50%" colspan="2" class="LABEL"><font color="red">*</font>District Drug Warehouse Name</td>
			<td width="50%" colspan="2" class="CONTROL">
			<div id="strStoreDivId" >
				<select name="strDistrictStoreId" class="comboNormal" onchange="getItemCatCmb();">
					<bean:write name="breakageAndLostDrugDetailsRptFB" property="strDistrictStoreValues" filter="false"/>
				</select>
			</div>				
			</td>
		</tr>		
				
				
		<tr	id="drugWarehouseDivId">
			<td width="50%" colspan="2" class="LABEL"><font color="red">*</font>Drug Warehouse/Sub-Store Name</td>
			<td width="50%" colspan="2" class="CONTROL">
				<select name="strStoreId" class="comboNormal" onchange="getItemCatCmb();">
					<bean:write name="breakageAndLostDrugDetailsRptFB" property="strStoreValues" filter="false" />
				</select>
			</td>
		</tr>
		
		
		<tr>
			<td width="50%" colspan="2" class="LABEL"><font color="red">*</font>Category</td>
			<td width="50%" colspan="2" class="CONTROL">
			<div id="itemCatDivId"><select name="strItemCatNo" class="comboNormal">
				<option value="0">Select Value</option>
				<option value="10">Drug</option>
			</select></div>
			</td>
		</tr>
<!--	-->
	
		
	
		<tr>
			<td class="LABEL" colspan="2"><font color="red">*</font>From Date</td>
			<td class="CONTROL" colspan="2"><dateTag:date name="strFromDate" value="${breakageAndLostDrugDetailsRptFB.strCurrentDate}" /></td>
		</tr>
		<tr>
			<td class="LABEL" colspan="2"><font color="red">*</font>To Date</td>
			<td class="CONTROL" colspan="2"><dateTag:date name="strToDate" value="${breakageAndLostDrugDetailsRptFB.strCurrentDate}" /></td>
		</tr>
		
		
		
	
		<tr>
			<td width="50%" colspan="2" class="LABEL">Report Format</td>
			<td width="50%" colspan="2" class="CONTROL">
				<select name="strReportFormat"  onchange="">
					<option value="html">Html</option>
					<option value="pdf">Pdf</option>
				</select>
			</td>
			
		</tr> 
		
		<tr>
			<td width="50%" colspan="2" class="LABEL">Footer Required</td>
			<td width="50%" colspan="2" class="CONTROL">
				<html:checkbox property="strIsFooter" name="breakageAndLostDrugDetailsRptFB" value="1"></html:checkbox>
			</td>
			
		</tr>
		<tr>
			<td width="50%" colspan="2" class="LABEL">User Remarks</td>
			<td width="50%" colspan="2" class="CONTROL">
				<input class="txtFldMax" type="text" name="strUserRemarks" >
			</td>
			
		</tr>
		<tr class="FOOTER">
			 <td colspan="4"><font color='red'>*</font>Mandatory Field </td>
		</tr>
		
	</table>
	<table border="0" class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="1px">
		<tr>

			<td align="center">
			<img style="cursor: pointer" src="../../hisglobal/images/btn-generate.png" onClick="return validate();" />
			<img style="cursor: pointer" src="../../hisglobal/images/btn-clr.png" onClick="document.forms[0].reset();" >
			<img style="cursor: pointer" src="../../hisglobal/images/btn-ccl.png" onClick="cancelFunc();" >
			</td>
			
		</tr>
	</table>
<input type="hidden" name="hmode"/>
<input type="hidden" name="strEndFinancialYearTemp" value="${breakageAndLostDrugDetailsRptFB.strEndFinancialYearTemp}"/>
<input type="hidden" name="strCurrentDate" value="${breakageAndLostDrugDetailsRptFB.strCurrentDate}"/>

<input type="hidden" name="strStoreName" value="${breakageAndLostDrugDetailsRptFB.strStoreName}"/>


</html:form>
<tag:autoIndex></tag:autoIndex>
</body>
</html>