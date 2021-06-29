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
<title>Equipment Test Parameter Master Modify Page</title>

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
<script language="Javascript" src="../../bmed/js/equipmentTestParameterMst.js"></script>
</head>

<body>
<html:form name="equipmentTestParameterMstFB" action="/masters/EquipmentTestParameterMstCNT"
	type="bmed.masters.controller.fb.EquipmentTestParameterMstFB">

	<div id="strErrMsg" class="errMsg"><bean:write name="equipmentTestParameterMstFB"
		property="strErrMsg" /></div>
	<div class="warningMsg"><bean:write name="equipmentTestParameterMstFB"
		property="strWarningMsg" /></div>
	<div id="normalMsg" class="normalMsg"><bean:write name="equipmentTestParameterMstFB"
		property="strNormalMsg" /></div>

	<tag:tab tabLabel="Equipment Test Parameter Master" selectedTab="FIRST" align="center"
		width="TABLEWIDTH" onlyTabIndexing="1">
	</tag:tab>

	<table class="TABLEWIDTH" align="center" cellpadding="1px"
		cellspacing="1px">
		<tr class="HEADER">
			<td colspan="3">Equipment Test Parameter Master&gt;&gt;Modify</td>
		</tr>

		<tr>
			<td class="LABEL"><font color="red">*</font>Test Name</td>
			<td width="33%" class="CONTROL">
			<div id="testIdDiv" align="left"><select
				class='comboNormal' name="strTestId" onchange="getParamters();" disabled="disabled">
				
				<bean:write name="equipmentTestParameterMstFB"
					property="strTestNameCmb" filter="false" />
			</select>
			<html:hidden name="equipmentTestParameterMstFB" property="strTestId"/>
			</div>
			
			</td>
			<td class="LABEL"></td>	
		</tr>


		
		<tr>
			<td class="LABEL"><font color="red">*</font>Parameter Name</td>
			<td width="33%" class="LABEL"></td>
			<td class="LABEL"></td>	
		</tr>


<tr>
			<td class="LABEL" style="text-align: center;">Available Parameter</td>
			<td class="LABEL">&nbsp;</td>
			<td class="LABEL" style="text-align: center;">Selected Parameter</td>
		</tr>
		<tr>
			<td class="CONTROL">
			<div id="LeftParaDivId" align="center"><select
				name="strAvailableTestId" size="8" multiple
				style="width: 250px">
				<bean:write name="equipmentTestParameterMstFB"
					property="strAvailableTestOptions" filter="false" />
			</select></div>
			</td>
			<td class="LABEL">
			<center><img src="../../hisglobal/images/forward3.gif"
				onclick='shiftToRight		("strAvailableTestId","arrStrSelectedTestId",1);'></center>
			<center><img src="../../hisglobal/images/forwardward.gif"
				onClick='shiftAllToRight	("strAvailableTestId","arrStrSelectedTestId",1);'></center>
			<center><img src="../../hisglobal/images/backward.gif"
				onClick='shiftAllToLeft		("strAvailableTestId","arrStrSelectedTestId",1);'></center>
			<center><img src="../../hisglobal/images/back3.gif"
				onClick='shiftToLeft		("strAvailableTestId","arrStrSelectedTestId",1);'></center>
			</td>
			<td class="CONTROL">
			<div id="RightParaDivId" align="center"><select
				name="arrStrSelectedTestId" size="8" multiple
				style="width: 250px">
				<bean:write name="equipmentTestParameterMstFB"
					property="strSelectedTestOptions" filter="false" />
			</select>
				</div>
			</td>
		</tr>






		<tr>
			<td class="LABEL" width="33%"><font color="red">*</font>Effective From Date</td>
			<td class="CONTROL"><dateTag:date name="strEffectiveFrom"
				value="${equipmentTestParameterMstFB.strEffectiveFrom}"></dateTag:date></td>
				<td class="LABEL"></td>
		</tr>

		<tr>
			<td class="LABEL">
			<div align="right">Remarks</div>
			</td>
			<td class="CONTROL">
			<div align="left"><html:textarea property="strRemarks" name="equipmentTestParameterMstFB"></html:textarea>
			</div>
			</td>
			<td class="LABEL"></td>
		</tr>



		<tr class="FOOTER">
			<td colspan="3"><font size="2" color="red">*</font> Mandatory Fields</td>
		</tr>
	</table>

	<table CLASS="TABLEWIDTH" align="center" cellpadding="1px"
		cellspacing="1px">
		<tr>
			<td align="center"><img src="../../hisglobal/images/btn-sv.png"
				style="cursor: pointer;" title="Save Record" onClick="validate2();" />
			<img src="../../hisglobal/images/btn-clr.png"
				style="cursor: pointer;" title="Reset Content"
				onClick="document.forms[0].reset(),clearMsg('ADD')" ;/> <img
				src="../../hisglobal/images/btn-ccl.png" style="cursor: pointer;"
				title="Cancel Process" onClick="cancel('LIST');" /></td>
		</tr>
	</table>
	<input type="hidden" name="hmode" />
	<html:hidden name="equipmentTestParameterMstFB" property="strTestId"/>
	
	<html:hidden name="equipmentTestParameterMstFB" property="chk"/>

	<cmbPers:cmbPers />
</html:form>
<tag:autoIndex></tag:autoIndex>
</body>
</html>
