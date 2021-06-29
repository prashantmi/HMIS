<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
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
<!-- <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1"> -->
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
<title>Non-Item Master &gt;&gt; Modify Page</title>
<link href="../css/master.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet"
	type="text/css">
<link href="../../hisglobal/css/calendar-tas.css" rel="stylesheet"
	type="text/css">

<link href="../../hisglobal/css/popup.css" rel="stylesheet"
	type="text/css">

<script language="JavaScript"
	src="../../hisglobal/masterutil/js/master.js"></script>
<script language="JavaScript" src="../../hisglobal/js/tab.js"></script>
<script language="JavaScript" src="../../hisglobal/js/calendar.js"></script>
<script language="Javascript" src="../../hisglobal/js/validation.js"></script>
<script language="Javascript" src="../../bmed/js/nonItemMst.js"></script>

<script language="javaScript">

</script>


</head>

<body>
<html:form name="nonItemBean" action="/masters/NonItemMstCNT"
	type="bmed.masters.controller.fb.NonItemMstFB">


	<div id="strErrMsg" class="errMsg">
		<bean:write name="nonItemBean" property="strErrMsg" />
	</div>
	
	<div class="warningMsg">	
		<bean:write name="nonItemBean" property="strWarningMsg" />
	</div>
	
	<div id="normalMsg" class="normalMsg">
		<bean:write name="nonItemBean" property="strNormalMsg" />
	</div>

	<table class="TABLEWIDTH" align="center" cellpadding="1px"	cellspacing="1px">
		<tr class="HEADER">
			<td colspan="2">Non-Item Master&gt;&gt;Modify</td>
		</tr>

		<tr>
			<td class="LABEL">Engineering Item Type</td>
			<td class="CONTROL" colspan="2">${nonItemBean.strEngineeringItemTypeName}</td>
		</tr>


		<tr>
			<td class="LABEL">Engineering Item Sub Type</td>
			<td class="CONTROL" colspan="2">${nonItemBean.strEngineeringItemSubTypeName}</td>
		</tr>



		<tr>
			<td class="LABEL"><font color="red">*</font>Non-Item Name</td>
			<td width="50%" class="CONTROL"><input type="text"
				name="strNonItemName" maxlength="250" value="${nonItemBean.strNonItemName}" tabindex="1"
				onkeypress="return validateData(event,18);"></td>
		</tr>

		<tr>
			<td class="LABEL" width="50%">
				<font color="red">*</font>Effective From Date
			</td>
			<td class="CONTROL">
				<dateTag:date name="strEffectiveFrom" value="${nonItemBean.strEffectiveFrom}">
				</dateTag:date>
			</td>
		</tr>

		<tr>
			<td class="LABEL">
			<div align="right">Remarks</div>
			</td>
			<td class="CONTROL">
				<div align="left"><textarea name="strRemarks" tabindex="1" rows="2"><bean:write	name="nonItemBean" property="strRemarks" /></textarea>
				</div>
			</td>
		</tr>

		<tr>
			<td class="LABEL"><font color="red">*</font>Record Status</td>
			<td width="45%" class="CONTROL">
				<html:radio name="nonItemBean" property="strIsValid" tabindex="1" value="1">Active</html:radio> 
				<html:radio	name="nonItemBean" property="strIsValid" tabindex="1" value="2">Inactive</html:radio>
			</td>
		</tr>



		<tr class="FOOTER">
			<td colspan="2"><font size="2" color="red">*</font> Mandatory Fields</td>
		</tr>
	</table>

	<table border="0" class="TABLEWIDTH" align="center" cellpadding="1px"
		cellspacing="1px">
		<tr>

			<td align="center">
				<img src="../../hisglobal/images/btn-sv.png" style="cursor: pointer;" title="Save Record" tabindex="1" onClick="validate2();" onkeypress="if(event.keyCode==13) validate2();" />
				
				<img src="../../hisglobal/images/btn-clr.png" style="cursor: pointer;" title="Reset Content" tabindex="1" onClick="document.forms[0].reset(), clearMsg('MODIFY');" onkeypress="if(event.keyCode==13) document.forms[0].reset(), clearMsg('MODIFY');"/>
				
				<img src="../../hisglobal/images/btn-ccl.png" style="cursor: pointer;" title="Cancel Process" tabindex="1" onClick="cancel('LIST');" onkeypress="if(event.keyCode==13) cancel('LIST');"/>
			</td>
		</tr>
	</table>

	<input type="hidden" name="chk" value="${nonItemBean.chk[0] }">
	<input type="hidden" name="comboValue" value="${nonItemBean.strNonItemName}">
	<input type="hidden" name="hmode">
	<html:hidden name="nonItemBean" property="strEngineeringItemTypeId"/>
	<html:hidden name="nonItemBean" property="strEngineeringItemSubTypeId"/>

	<cmbPers:cmbPers />
</html:form>

</body>
</html>

