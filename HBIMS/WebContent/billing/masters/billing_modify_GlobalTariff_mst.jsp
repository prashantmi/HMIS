<%-- 
 Global Tariff Master Modify jsp
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
<link href="../../hisglobal/css/buttons.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet"type="text/css">

<script language="JavaScript" src="../../hisglobal/js/tab.js"></script>
<script language="JavaScript" src="../../hisglobal/masterutil/js/master.js"></script>
<script language="JavaScript" src="../../hisglobal/js/util.js"></script>
<script language="JavaScript" src="../../hisglobal/js/validation.js"></script>
<script language="JavaScript" src="../../billing/js/GlobalTariffMst.js"></script>
</head>

<body >
<html:form name="tariffBean" action="masters/CNTTariffMst.cnt" type="billing.masters.controller.fb.GlobalTariffMstFB">

	<div class="errMsg"><bean:write name="tariffBean" property="errMsg"/></div>
	<div class="normalMsg" id='normalMsg'><bean:write name="tariffBean" property="normalMsg"/></div>
	<tag:tab tabLabel="Modify Tariff Master" width="TABLEWIDTH" align="center"></tag:tab>

	<table class="TABLEWIDTH" border="0" align="center" cellpadding="1px" cellspacing="1px">
		<tr class="HEADER">
			<td colspan="2" WIDTH="50%">
			<table width="100%" border="0" align="center">
				<tr class="HEADER">
					<td width="50%">
					<div align="left">Global Tariff Master&gt;&gt; Modify</div>
					</td>					
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td class="LABEL" width="50%">Group Name</td>
			<td class="CONTROL" width="50%">
			<div id="GroupId">
				<bean:write name="tariffBean" property="strgroupName" filter="false" />
			</div>
			</td>
		</tr>
		<tr>
			<td class="LABEL" width="50%">Sub Group Name</td>
			<td class="CONTROL" width="50%">
			<div id="SubGroupId"><html:select styleClass='comboNormal' name="tariffBean" property="strsubgroupName"  >
			<bean:write name="tariffBean" property="modifysubgroupCombo" filter="false"/>
			</html:select></div>
			</td>
		</tr>
		<tr>
			<td class="LABEL"><font color="red">*</font>Tariff Name</td>
			<td class="CONTROL">
			<input type="text" name="strtrfPkgName" class="txtFldBig" onkeypress="return validateData(event,16);" value="${tariffBean.strtrfPkgName}" maxlength="100"></td>
		</tr>
		<tr>
    <td width ="45%" class ="LABEL"><font color="red">*</font>Record Status </td>
    <td width ="45%" class ="CONTROL" >
	    <html:radio name="tariffBean" property="strisValid" value="1" />Active<span class="LABEL">
	    <html:radio name="tariffBean" property="strisValid" value="2" />InActive
    </span></td>
    </table>
	
	<table class="TABLEWIDTH" border="0" align="center" cellpadding="1px" cellspacing="1px">	
	<tr class="FOOTER">
			<td colspan="2"><font color="red">*</font>Mandatory Fields</td>
		</tr>
	</table>
	<div align="center">
		<!-- <img title="Save Record" src="../../hisglobal/images/btn-sv.png" style="cursor:pointer;cursor:hand"
			onClick=" return submitDataModify('SAVEMODIFY'); " /> 
		<img title="Cancel Process" style="cursor:pointer;cursor:hand" src="../../hisglobal/images/btn-ccl.png" onClick="cancel('LIST');" />
		 -->
		
				<br><a href="#" class="button" id="" onClick=" return submitDataModify('SAVEMODIFY'); "><span class="save">Save</span></a>
				<a href="#" class="button" onclick="cancel('LIST');"><span class="cancel">Cancel</span></a>
	</div>
	
	<input type="hidden" name="hmode" value="" />
	<input type="hidden" name="chk" value="${tariffBean.chk[0]}">
	<input type="hidden" name="strGroupId" value="${tariffBean.strGroupId}" />
	<input type="hidden" name="strHospitalCode" value ="${tariffBean.strHospitalCode}" />
  
    <cmbPers:cmbPers></cmbPers:cmbPers>
    
</html:form>
</body>
</html>