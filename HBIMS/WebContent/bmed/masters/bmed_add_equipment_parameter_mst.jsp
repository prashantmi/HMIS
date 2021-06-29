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
 * @author Arun VR%
 * Date of Creation : 06-Aug-2012
 * Date of Modification :  06-Aug-2012 
 * Version : 
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
<title>Equipment Parameter Master Add Page</title>

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
<script language="Javascript" src="../../bmed/js/equipmentParameterMst.js"></script>
</head>

<body>
<html:form name="equipmentParameterMstFB" action="/masters/EquipmentParameterMstCNT"
	type="bmed.masters.controller.fb.EquipmentParameterMstFB">

	<div id="strErrMsg" class="errMsg"><bean:write name="equipmentParameterMstFB"
		property="strErrMsg" /></div>
	<div class="warningMsg"><bean:write name="equipmentParameterMstFB"
		property="strWarningMsg" /></div>
	<div id="normalMsg" class="normalMsg"><bean:write name="equipmentParameterMstFB"
		property="strNormalMsg" /></div>

	
	<table class="TABLEWIDTH" align="center" cellpadding="1px"
		cellspacing="1px">
		<tr class="HEADER">
			<td colspan="2">Equipment Parameter Master&gt;&gt;Add</td>
		</tr>

		
		<tr>
			<td class="LABEL"><font color="red">*</font>Parameter Name</td>
			<td width="50%" class="CONTROL"><input type="text"
				name="strParameterName" value="" maxlength="250"
				onkeypress="return validateData(event,18);"></td>
		</tr>


		<tr>
			<td class="LABEL" width="50%"><font color="red">*</font>Effective From Date</td>
			<td class="CONTROL"><dateTag:date name="strEffectiveFrom"
				value="${equipmentParameterMstFB.strCtDate}"></dateTag:date></td>
		</tr>

		<tr>
			<td class="LABEL">
			<div align="right">Remarks</div>
			</td>
			<td class="CONTROL">
			<div align="left"><textarea name="strRemarks" rows="2"></textarea>
			</div>
			</td>
		</tr>



		<tr class="FOOTER">
			<td colspan="2"><font size="2" color="red">*</font> Mandatory Fields</td>
		</tr>
	</table>

	<table CLASS="TABLEWIDTH" align="center" cellpadding="1px"
		cellspacing="1px">
		<tr>
			<td align="center"><img src="../../hisglobal/images/btn-sv.png"
				style="cursor: pointer;" title="Save Record" onClick="validate1();" />
			<img src="../../hisglobal/images/btn-clr.png"
				style="cursor: pointer;" title="Reset Content"
				onClick="document.forms[0].reset(),clearMsg('ADD')" ;/> <img
				src="../../hisglobal/images/btn-ccl.png" style="cursor: pointer;"
				title="Cancel Process" onClick="cancel('LIST');" /></td>
		</tr>
	</table>
	<input type="hidden" name="hmode" />
	<input type="hidden" name="strCurrentDate" value="${equipmentParameterMstFB.strCtDate}">		



	<cmbPers:cmbPers />
</html:form>

</body>
</html>
