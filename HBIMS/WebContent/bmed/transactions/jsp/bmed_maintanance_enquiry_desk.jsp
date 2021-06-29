<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/dateTag.tld" prefix="dateTag"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<%@ taglib uri="/WEB-INF/comboPersistence.tld" prefix="cmbPers"%>


<html>
<head>
<title>Equipment Maintanance Enquiry Desk</title>
<his:css src="/hisglobal/css/Color.css" />
<his:css src="/hisglobal/css/master.css" />
<his:css src="/hisglobal/css/hisStyle.css" />
<his:css src="/hisglobal/css/hisStyleExt.css" />
<his:css src="/hisglobal/css/calendar-tas.css" />
<his:javascript src="/hisglobal/js/time.js" />
<his:javascript src="/hisglobal/js/util.js" />
<his:javascript src="/hisglobal/js/calendar.js" />
<his:javascript src="/hisglobal/js/validation.js" />
<his:javascript	src="/bmed/transactions/js/bmed_HemDesk_trans.js" />

<script language="JavaScript"src="../../hisglobal/transactionutil/js/master.js"></script>
<script language="JavaScript" src="../../hisglobal/js/tab.js"></script>
<script language="JavaScript" src="../../hisglobal/js/calendar.js"></script>
<script language="Javascript" src="../../hisglobal/js/validation.js"></script>
<script language="Javascript" src="../../hisglobal/js/util.js"></script>
<!-- <script language="Javascript"src="../../bmed/js/complaintHierarchyMst.js"></script> -->
<script language="Javascript" src="../../hisglobal/js/newpopup.js"></script>
<script language="Javascript" src="../../hisglobal/js/popup.js"></script>
<script language="Javascript" src="../../bmed/js/CentralEquipEnquiryDesk.js"></script>

<!-- 
/**
 * BY shefali
 * Date of Creation : 16/08/2013
 * Date of Modification :  /  / 
 * Version : 1.0
 * Module  : HEMM Product
 * Product For : Rajasthan
 */
 -->
</head>
<body>
<html:form action="/transactions/CentralEquipEnquiryDeskCNT"
	name="stEquipInstallDeskName"
	type="bmed.transactions.controller.fb.CentralEquipEnquiryDeskFB">

	<center>
	<div id="errMsg" class="errMsg"><bean:write
		name="CentralEquipEnquiryDeskFB" property="strErr" /></div>
	<div id="warningMsg" class="warningMsg"><bean:write
		name="CentralEquipEnquiryDeskFB" property="strWarning" /></div>
	<div id="normalMsg" class="normalMsg"><bean:write
		name="CentralEquipEnquiryDeskFB" property="strMsgString" /></div>


	<tag:tab tabLabel="Equipment Maintenance Enquiry Desk" selectedTab="FIRST"
		align="center"  width="TABLEWIDTH">
	</tag:tab></center>

	<table class="TABLEWIDTH" align="center" width="100%">
		<tr class="HEADER">
			<td colspan="4">Equipment Detail &gt;&gt;</td></tr>

	</table>
		<table border="0"  align="center" border='0'
		cellspacing='1px' cellpadding='1px' width="100%">

		
		<tr>
			<td colspan="1" width="25%" class="LABEL" style="text-align:left;">Hospital Name</td>
			<td colspan="1" width="75%" class="CONTROL" style="text-align:left;">${CentralEquipEnquiryDeskFB.strStoreId}
			</td>
			
		</tr>
		<tr>
			<td colspan="1" width="25%" class="LABEL" style="text-align:left;">Equipment Name</td>
			<td colspan="1" width="75%" class="CONTROL" style="text-align:left;">${CentralEquipEnquiryDeskFB.strEquipName}
			</td>
		</tr>
		<tr>	
			<td width="25%" colspan="" class="LABEL" style="text-align:left;">Equipment Model</td>
			<td width="75%" colspan="1" class="CONTROL" style="text-align:left;">${CentralEquipEnquiryDeskFB.strEquipModel}</td>
		</tr>
		<tr>	
			<td width="25%" colspan="" class="LABEL" style="text-align:left;">Group Name</td>
			<td width="75%" colspan="1" class="CONTROL" style="text-align:left;">${CentralEquipEnquiryDeskFB.strEquipGroup}</td>
		</tr>
		<tr>	
			<td width="25%" colspan="" class="LABEL" style="text-align:left;">Sub Group Name</td>
			<td width="75%" colspan="1" class="CONTROL" style="text-align:left;">${CentralEquipEnquiryDeskFB.strEquipSubGroup}</td>
		</tr>
			
		</table>
	<table class="TABLEWIDTH" align="center" width="100%">
		<tr class="HEADER">
			<td colspan="4">Inventory Detail &gt;&gt;</td></tr>
		<tr>
			<td colspan="1" width="25%" class="LABEL" style="text-align:left;">Serial No.</td>
			<td colspan="1" width="75%" class="CONTROL" style="text-align:left;">${CentralEquipEnquiryDeskFB.strBatchNo}
			</td>
		</tr>
		<tr>	
			<td width="25%" colspan="" class="LABEL" style="text-align:left;">UIN</td>
			<td width="75%" colspan="1" class="CONTROL" style="text-align:left;">${CentralEquipEnquiryDeskFB.strUidNo}</td>
		</tr>

	</table>
		
		<table class="TABLEWIDTH" align="center" width="100%">
		<tr class="HEADER">
	    <td colspan="10">Equipment Maintenance Detail &gt;&gt;</td></tr>
	     <tr>	
			<td colspan="1" class="LABEL" style="text-align:center;">MC Name</td>
		    <td colspan="1" class="LABEL" style="text-align:center;">AMC Type</td>
			<td colspan="1" class="LABEL" style="text-align:center;">Start Date</td>
			<td colspan="1" class="LABEL" style="text-align:center;">End Date</td>
			<td colspan="1" class="LABEL" style="text-align:center;">PMF</td>
			<td colspan="1" class="LABEL" style="text-align:center;">Response Time</td>
			<td colspan="1" class="LABEL" style="text-align:center;">Contact No.</td>
			<td colspan="1" class="LABEL" style="text-align:center;">Document No.</td>
			<td colspan="1" class="LABEL" style="text-align:center;">Document Date</td>
			<td colspan="1" class="LABEL" style="text-align:center;">Document Ref.</td>
		</tr>
		<tr>
			<td colspan="1" class="CONTROL" style="text-align:center;">${CentralEquipEnquiryDeskFB.strManufacturerName}</td>
		    <td colspan="1" class="CONTROL" style="text-align:center;">${CentralEquipEnquiryDeskFB.strAMCType}</td>
			<td colspan="1" class="CONTROL" style="text-align:center;">${CentralEquipEnquiryDeskFB.strStartDate}</td>
			<td colspan="1" class="CONTROL" style="text-align:center;">${CentralEquipEnquiryDeskFB.strEndDate}</td>
			<td colspan="1" class="CONTROL" style="text-align:center;">${CentralEquipEnquiryDeskFB.strPMF}</td>
			<td colspan="1" class="CONTROL" style="text-align:center;">${CentralEquipEnquiryDeskFB.strReponse}</td>
			<td colspan="1" class="CONTROL" style="text-align:center;">${CentralEquipEnquiryDeskFB.strCompanyContectNo}</td>
			<td colspan="1" class="CONTROL" style="text-align:center;">${CentralEquipEnquiryDeskFB.strDocRefNo}</td>
			<td colspan="1" class="CONTROL" style="text-align:center;">${CentralEquipEnquiryDeskFB.strDocRefDate}</td>
			<td colspan="1" class="CONTROL" style="text-align:center;">N/A</td>
		
		</tr>
	</table>
		<font>Note: PMF = Preventive Maintenance Frequency </font>		
		
		
	
	<html:hidden property="hmode" styleId="hmode"/>
		</html:form>

</body>
</html>

