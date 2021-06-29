<%-- 
 Global Tariff Master ADD jsp
 author: Debashis Sardar
  Created on : 14-Sep-2011
  --%>
<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>

<%@ taglib uri="/WEB-INF/comboPersistence.tld" prefix="cmbPers" %>

<html>
<head>
<meta charset=utf-8>
<title>Global Tariff Master</title>
<link href="../css/master.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/buttons.css" rel="stylesheet" type="text/css">
<script language="JavaScript" src="../../hisglobal/js/tab.js"></script>
<script language="JavaScript" src="../../hisglobal/masterutil/js/master.js"></script>
<script language="JavaScript" src="../../hisglobal/js/util.js"></script>
<script language="JavaScript" src="../../hisglobal/js/validation.js"></script>
<script language="JavaScript" src="../../billing/js/GlobalTariffMst.js"></script>
</head>

<body onload="document.forms[0].strgrpId.focus();">
<html:form name="tariffBean" action="/masters/CNTTariffMst.cnt" type="billing.masters.controller.fb.GlobalTariffMstFB">

	<div id = "errMsg" class="errMsg"><bean:write name="tariffBean" property="errMsg"/></div>
	<div id = "normalMsg" class="normalMsg"><bean:write name="tariffBean" property="normalMsg"/></div>
	<tag:tab tabLabel="Add Tariff Master" width="TABLEWIDTH" align="center"></tag:tab>

	<table class="TABLEWIDTH" border="0" align="center" cellpadding="1px" cellspacing="1px">
		<tr class="HEADER">
			<td colspan="2" WIDTH="50%">
			<table width="100%" border="0" align="center">
				<tr class="HEADER">
					<td width="50%">
					<div align="left">Global Tariff Master&gt;&gt; Add</div>
					</td>					
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td class="LABEL" width="50%">Group Name</td>
			<td class="CONTROL" width="50%">
			<div id="GroupId">
			<bean:write name="tariffBean" property="strgroupName" filter="false" /></div>
			</td>
		</tr>
		<tr>
			<td class="LABEL" width="50%">Sub Group Name</td>
			<td class="CONTROL" width="50%">
			<div id="SubGroupId"><html:select styleClass='comboNormal' name="tariffBean" property="strsubgroupName"  >
			<bean:write name="tariffBean" property="addsubgroupCombo" filter="false"/>
			</html:select></div>
			</td>
		</tr>
		<tr>
			<td class="LABEL"><font color="red">*</font>Tariff Name</td>
			<td class="CONTROL">
			<input type="text" class="txtFldBig" name="strtrfPkgName" onkeypress="return validateData(event,16);" maxlength="100"></td>
		</tr>		
	</table>
	
	<table class="TABLEWIDTH" border="0" align="center" cellpadding="1px" cellspacing="1px">	
	<tr class="FOOTER">
			<td colspan="2"><font color="red">*</font>Mandatory Fields</td>
		</tr>
	</table>
	
	<div align="center">
	<!-- 	<img name="save" src="../../hisglobal/images/btn-sv.png" title="Save Record" style="cursor:pointer;cursor:hand"
			onClick=" return submitDataAdd('SAVEADD'); document.forms[0].reset();" /> 
		<img style="cursor:pointer;cursor:hand"  title="Reset Content" src="../../hisglobal/images/btn-clr.png" onClick="document.forms[0].reset(),document.forms[0].addGroupCombo.focus();" />
		<img title="Cancel Process" style="cursor:pointer;cursor:hand" src="../../hisglobal/images/btn-ccl.png" onClick="cancel('LIST');" />
	 -->
	<br><a href="#" class="button" id="" onClick=" return submitDataAdd('SAVEADD'); document.forms[0].reset();" ><span class="save">Save</span></a>
							<a href="#" class="button"	onClick="document.forms[0].reset(),document.forms[0].strgroupName.focus();"><span class="clear">Clear</span></a> 
							<a href="#" class="button" onclick="cancel('LIST');"><span class="cancel">Cancel</span></a>	
	</div>
	
	<input type="hidden" name="hmode" value="" />
    <input type="hidden" name="strHospitalCode" value ="${tariffBean.strHospitalCode}" />
    <input type="hidden" name="strgroupName" value ="${tariffBean.strgroupName}" />
    <input type="hidden" name="strgrpId" value ="${tariffBean.strgrpId}" />
    
    <cmbPers:cmbPers/>    
</html:form>
</body>
</html>