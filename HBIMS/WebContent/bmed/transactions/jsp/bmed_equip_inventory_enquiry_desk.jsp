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
<title>Equipment Installation Master</title>
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
 * Date of Creation : 30/07/2013
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


	<tag:tab tabLabel="Equipment Inventory Enquiry" selectedTab="FIRST"
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
			<td colspan="1" width="25%" class="LABEL" style="text-align:left;"> Equipment Name</td>
			<td colspan="1" width="75%" class="CONTROL" style="text-align:left;">${CentralEquipEnquiryDeskFB.strEquipName}
			</td>
		</tr>
		<tr>	
			<td colspan="" width="25%" class="LABEL" style="text-align:left;">Equipment Model</td>
			<td colspan="1" width="75%" class="CONTROL" style="text-align:left;">${CentralEquipEnquiryDeskFB.strEquipModel}</td>
		</tr>
			<tr>
			<td colspan="1" width="25%" class="LABEL" style="text-align:left;">Group Name</td>
			<td colspan="1" width="75%" class="CONTROL" style="text-align:left;">${CentralEquipEnquiryDeskFB.strEquipGroup}
			</td>
		</tr>
		<tr>	
			<td colspan="1" width="25%" class="LABEL" style="text-align:left;">Sub Group Name</td>
			<td colspan="1" width="75%" class="CONTROL" style="text-align:left;">${CentralEquipEnquiryDeskFB.strEquipSubGroup}</td>
		</tr>
		<tr class="HEADER">
			<td colspan="4">Inventory Detail &gt;&gt;</td></tr>


		<tr>
			<td colspan="1" width="25%" class="LABEL" style="text-align:left;">Serial No</td>
			<td colspan="1" width="75%" class="CONTROL" style="text-align:left;">${CentralEquipEnquiryDeskFB.strBatchNo}
			</td>
		</tr>
		<tr>	
			<td colspan="1" width="25%" class="LABEL" style="text-align:left;">UIN</td>
			<td colspan="1" width="75%" class="CONTROL" style="text-align:left;">${CentralEquipEnquiryDeskFB.strUidNo}</td>
		</tr>
	
	    <tr>
			<td colspan="1" width="25%" class="LABEL" style="text-align:left;">Manufacturer Name</td>
			<td colspan="1" width="75%" class="CONTROL" style="text-align:left;">${CentralEquipEnquiryDeskFB.strManufacturerName}
			</td>
					
		</tr>
	    <tr>
			<td colspan="1" width="25%" class="LABEL" style="text-align:left;">Supplier Name</td>
			<td colspan="1" width="75%" class="CONTROL" style="text-align:left;">${CentralEquipEnquiryDeskFB.strSupplierName}
			</td>
					
		</tr>
		
	    <tr>
			<td colspan="1" width="25%" class="LABEL" style="text-align:left;">Status</td>
			<td colspan="1" width="75%" class="CONTROL" style="text-align:left;">${CentralEquipEnquiryDeskFB.strStatus}
			</td>
					
		</tr>
	 
				
		</table>
		
	
	<html:hidden property="hmode" styleId="hmode"/>
		</html:form>

</body>
</html>

