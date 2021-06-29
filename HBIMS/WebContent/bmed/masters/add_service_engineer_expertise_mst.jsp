<%-- 
author 				: Aritra Kumar Dhawa 
Date of Creation 	: 13-Jan-2011
Process 			: Service Engineer Expertise Mst
Module 				: BMED
TL 					: Mr. A. S. Cheema
Description 		: This Process is used to add the Service Engineer Expertise Details.
 --%>

<%-- <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%> --%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/dateTag.tld" prefix="dateTag"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>
<%@ taglib uri="/WEB-INF/comboPersistence.tld" prefix="cmbPers"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
<title>Add Service Engineer Expertise Master...</title>

<!-- CSS Start  -->
<link href="../css/master.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet"
	type="text/css">
<link href="../../hisglobal/css/calendar-tas.css" rel="stylesheet"
	type="text/css">
<!-- CSS End  -->

<!-- JavaScript Start -->
<script language="JavaScript" src="../../hisglobal/js/tab.js"></script>
<script language="JavaScript" src="../../hisglobal/js/calendar.js"></script>
<script language="Javascript" src="../../hisglobal/js/validation.js"></script>
<script language="JavaScript" src="../../hisglobal/js/util.js"></script>
<script language="JavaScript"
	src="../../hisglobal/masterutil/js/master.js"></script>
<script language="JavaScript" src="../js/serviceEngineerExpertiseMst.js"></script>
<!-- JavaScript Start -->
</head>
<body>
<html:form action="/masters/ServiceEngineerExpertiseMstCNT"
	name="serviceEngineerExpertiseMstFB"
	type="bmed.masters.controller.fb.ServiceEngineerExpertiseMstFB">

	<center>
	<div class="errMsg" id="errMsg"><bean:write
		name="serviceEngineerExpertiseMstFB" property="strErrMsg" /></div>
	<div class="warningMsg" id="warningMsg"><bean:write
		name="serviceEngineerExpertiseMstFB" property="strWarningMsg" /></div>
	<div class="normalMsg" id="normalMsg"><bean:write
		name="serviceEngineerExpertiseMstFB" property="strNormalMsg" /></div>

	</center>


	<table class="TABLEWIDTH" align="center" cellspacing="1px"
		cellpadding="1px">
		<tr class="HEADER">
			<td colspan="2">&nbsp;Service Engineer Expertise Mst &gt;&gt;
			Add</td>
		</tr>
		<tr>
			<td class="LABEL" width="50%">Service Engineer Name</td>
			<td class="CONTROL">${serviceEngineerExpertiseMstFB.strServiceEngineerName}</td>
		</tr>


	</table>

	<table class="TABLEWIDTH" align="center" cellspacing="1px"
		cellpadding="1px">
		<tr class="TITLE">
			<td colspan="3">&nbsp;<font color="red">*</font>Expertise Name</td>
		</tr>
		<tr>
			<td class="LABEL" style="text-align: center;">Available
			Expertise</td>
			<td class="LABEL">&nbsp;</td>
			<td class="LABEL" style="text-align: center;">Selected Expertise</td>
		</tr>
		<tr>
			<td class="CONTROL">
			<div id="LeftExpertiseDivId" align="center"><select
				name="strAvailableExpertiseId" size="8" multiple
				style="width: 250px">
				<bean:write name="serviceEngineerExpertiseMstFB"
					property="strAvailableExpertiseOptions" filter="false" />
			</select></div>
			</td>
			<td class="LABEL">
			<center><img src="../../hisglobal/images/forward3.gif"
				onclick='shiftToRight		("strAvailableExpertiseId","arrStrSelectedExpertiseId",1);'></center>
			<center><img src="../../hisglobal/images/forwardward.gif"
				onClick='shiftAllToRight	("strAvailableExpertiseId","arrStrSelectedExpertiseId",1);'></center>
			<center><img src="../../hisglobal/images/backward.gif"
				onClick='shiftAllToLeft		("strAvailableExpertiseId","arrStrSelectedExpertiseId",1);'></center>
			<center><img src="../../hisglobal/images/back3.gif"
				onClick='shiftToLeft		("strAvailableExpertiseId","arrStrSelectedExpertiseId",1);'></center>
			</td>
			<td class="CONTROL">
			<div id="RightExpertiseDivId" align="center"><select
				name="arrStrSelectedExpertiseId" size="8" multiple
				style="width: 250px"></select></div>
			</td>
		</tr>


	</table>

	<table class="TABLEWIDTH" align="center" cellspacing="1px"
		cellpadding="1px">
		<tr class="TITLE">
			<td colspan="4">&nbsp;</td>
		</tr>
		<tr>
			<td class="LABEL"><font color="red">*</font>Effective From Date</td>
			<td class="CONTROL"><dateTag:date name="strEffectiveFrom"
				value="${serviceEngineerExpertiseMstFB.strCurrentDate}"></dateTag:date></td>
			
			<td class="CONTROL" colspan="2"></td>
		</tr>

	</table>

	<table cellspacing="1px" cellpadding="1px" class="TABLEWIDTH"
		align="center">
		<tr class="FOOTER">
			<td><font size="2" color="red">*</font> Mandatory Fields</td>
		</tr>
	</table>

	<table class="TABLEWIDTH" align="center" cellspacing="1px"
		cellpadding="1px">
		<tr>
			<td align="center" colspan="2" width="25%"><img
				src="../../hisglobal/images/btn-sv.png" title="Save Record"
				onClick="return validate('insert');" style="cursor: pointer;" /> <img
				src="../../hisglobal/images/btn-clr.png"
				onClick="resetForm('initializeAdd');" style="cursor: pointer;"
				title="Clear Content" /> <img
				src="../../hisglobal/images/btn-ccl.png" onClick="cancelMasterPage();"
				style="cursor: pointer;" title="Cancel Process"></td>
		</tr>
	</table>
	<input type="hidden" name="hmode" />
	<input type="hidden"
		value="${serviceEngineerExpertiseMstFB.strCurrentDate}"
		name="strCurrentDate" />
	<input type="hidden"
		value="${serviceEngineerExpertiseMstFB.strServiceEngineerEmployeeId}"
		name="strServiceEngineerEmployeeId" />
	<input type="hidden"
		value="${serviceEngineerExpertiseMstFB.strServiceEngineerName}"
		name="strServiceEngineerName" />
	<cmbPers:cmbPers />

</html:form>

</body>
</html>