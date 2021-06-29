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
<title>Equipment Complaint Detail Desk</title>
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
<script language="Javascript" src="../js/CentralEquipEnquiryDetailDesk.js"></script>

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
<html:form action="/transactions/EquipInwardOutwardGatePassCNT"
	name="stEquipInstallDeskName"
	type="bmed.transactions.controller.fb.EquipInwardOutwardGatePassFB">

	<center>
	<div id="errMsg" class="errMsg"><bean:write
		name="EquipInwardOutwardGatePassFB" property="strErr" /></div>
	<div id="warningMsg" class="warningMsg"><bean:write
		name="EquipInwardOutwardGatePassFB" property="strWarning" /></div>
	<div id="normalMsg" class="normalMsg"><bean:write
		name="EquipInwardOutwardGatePassFB" property="strMsgString" /></div>


	<tag:tab tabLabel="Equipment Complaint detail Enquiry Desk" selectedTab="FIRST"
		align="center"  width="TABLEWIDTH">
	</tag:tab></center>

	<table class="TABLEWIDTH" align="center" width="100%">
		<tr class="HEADER">
			<td colspan="4">Request/Complaint Detail &gt;&gt;</td></tr>

	</table>
		<table border="0"  align="center" border='0'
		cellspacing='1px' cellpadding='1px' width="100%">

	
		<tr>
			<td colspan="1" class="LABEL" style="text-align:right;">Complaint Id</td>
			<td colspan="1" class="CONTROL"  style="text-align:left;">${EquipInwardOutwardGatePassFB.strComplaintId}
			</td>
			<td colspan="1" class="LABEL" style="text-align:right;">Complaint Date</td>
			<td colspan="1" class="CONTROL"  style="text-align:left;">${EquipInwardOutwardGatePassFB.strComplaintDate}
			</td>
		</tr>
		<tr>
			<td colspan="1" class="LABEL" style="text-align:right;">Complaint Description</td>
			<td colspan="3" class="CONTROL" style="text-align:left;">${EquipInwardOutwardGatePassFB.strComplaintDescription}
			</td>
		</tr>


	<tr>
	<td colspan="4" style="text-align:center;"><img src="../../../hisglobal/images/back_tab.png" onClick="javascript:window.history.back();" ></td>
	</tr>
		</table>
	<html:hidden property="hmode" styleId="hmode"/>
		</html:form>

</body>
</html>

