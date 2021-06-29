<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/dateTag.tld" prefix="dateTag"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>

<html>

<head>
<meta charset=utf-8>
<title>Tariff Master</title>
<link href="../css/master.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet"
	type="text/css">
<link href="../../hisglobal/css/buttons.css" rel="stylesheet" type="text/css">
<style type="text/css">@import url(../../hisglobal/css/calendar-tas.css);</style>
<script type="text/javascript" src="../../hisglobal/js/calendar.js"></script>
<script language="Javascript" src="../../hisglobal/js/datepicker1.js"></script>
<script language="JavaScript" src="../../hisglobal/js/tab.js"></script>
<script language="JavaScript" src="../../hisglobal/masterutil/js/master.js"></script>
<script language="JavaScript" src="../../hisglobal/js/multirow.js"></script>
<script language="JavaScript" src="../../hisglobal/js/util.js"></script>

<script type="text/javascript">

function tariffCombo()
{	
    var objVal1 = document.getElementById("tariffType");
	if(document.forms[0].strCostType.value=='0')
	{
	  objVal1.innerHTML = "Select"; 
	}
	else
	{
	  if(document.forms[0].strCostType.value=='1')
	  {
	     objVal1.innerHTML = "Hospital Cost"; 
	  }
	  else
	  {
	     objVal1.innerHTML = "Package Cost"; 
	  }
	}
	var temp = document.forms[0].strSId.value;
	var tariffId = document.forms[0].strModTariffId.value ;
	var mode="POPULATEDATA";
	var url="CNTTariffMst.cnt?hmode="+mode+"&service="+temp+"&tariffId="+tariffId;
	
	ajaxFunction(url,"1");
}

function getAjaxResponse(res,mode)
{

	if(mode==1){
		var objVal = document.getElementById("tId");
		
	if(res.length > 0){

	objVal.innerHTML = "<select name = 'tariffName' disabled='disabled' value='${localTariffBean.tariffId}' >" + res + "</select>";
	
	}else{
	
	objVal.innerHTML = "<select name = 'tariffName' disabled='disabled'><option value='0'>Select Value</option></select>";
	
	}


	}
		
}

</script>
</head>

<body onload="tariffCombo();" >
<html:form name="localTariffBean" action="/masters/CNTTariffMst.cnt"
	type="billing.masters.controller.fb.LocalTariffMstFB">

	<div class="errMsg"><bean:write name="localTariffBean" property="errMsg"/></div>
	<div class="normalMsg" id="normalMsg"><bean:write name="localTariffBean" property="normalMsg"/></div>
	<tag:tab tabLabel="View Tariff Master" width="TABLEWIDTH" align="center"></tag:tab>

	<table class="TABLEWIDTH" border="0" align="center">
		<tr class="HEADER">
			<td colspan="2" WIDTH="50%">
			<table width="100%" border="0" align="center">
				<tr class="HEADER">
					<td width="50%">
					<div align="left">Tariff Master&gt;&gt; View</div>
					</td>
					<td width="50%">
					<div align="right"><html:checkbox name="localTariffBean" property="isPackage" value="1" disabled="true"
						  />Package</div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td class="LABEL" width="50%">Group Name</td>
			<td class="CONTROL" width="50%">
			<div id="grpId"><html:select name="localTariffBean" property="groupId" disabled="true" >
			<bean:write name="localTariffBean" property="addGroupCombo" filter="false"/>
			</html:select></div>
			</td>
		</tr>
		<tr>
			<td class="LABEL">Tariff/Package Name</td>
			<td class="CONTROL"><input type="text" name="trfPkgName"  value="${localTariffBean.trfPkgName}"
				maxlength="50" readonly="readonly"></td>
		</tr>
		<tr>
			<td class="LABEL">Default Unit</td>
			<td class="CONTROL"><html:select name="localTariffBean" property="defaultUnit" disabled="true">
			   <bean:write name="localTariffBean" property="addDefaultUnitCombo" filter="false" />
			</html:select></td>
		</tr>
		<tr>  
			<td class ="LABEL" width ="50%">Un-Defined Charges</td>
			<td class="CONTROL" width="30%"> <html:checkbox property="strUndefinedCharges" name="localTariffBean" value="1"></html:checkbox> </td>
	    </tr>
	</table>
	<div id="divId1" style="display:none">
	<table class="TABLEWIDTH" border="0" align="center">
		<tr>
			<td class="LABEL" width="50%">Length of Stay</td>
			<td class="CONTROL" width="50%"><input type="text"
				name="lengthOfStay" maxlength="2"
				 readonly="readonly"></td>
		</tr>
	</table>
	</div>
	<table class="TABLEWIDTH" border="0" align="center">
		<tr class="TITLE">
			<td colspan="2">Associated with</td>
		</tr>
		<tr>
			<td class="LABEL" width="50%"><font color="red">*</font>Tariff Code</td>
			<td class="CONTROL" width="50%"><input type="text" class="txtFldMax"
				name="tariffCode" maxlength="15"  value="${localTariffBean.tariffCode}"
				onkeypress="return validateData(event,8);" readonly="readonly"></td>
		</tr>
		<tr>
			<td class="LABEL" width="50%">Service Name</td>
			<td class="CONTROL" width="50%">
			<html:select name="localTariffBean" property="strServiceId"  disabled="true" >
			<bean:write name="localTariffBean" property="addServiceCombo" filter="false" />
			</html:select></td>
		</tr>
		<tr>
			<td class="LABEL" width="50%">Tariff Name</td>
			<td class="CONTROL" width="50%">
			<div id="tId">
			<select style="comboNormal" disabled="disabled" name="tariffName">
			<option value="0">Select Value</option>
			</select>
			</div></td>
		</tr>
		<!--<tr>
			<td class="LABEL">Tariff Name</td>
			<td class="CONTROL">
			<div id="tId"><select name="tariffName">
				<option>Select Value</option>
			</select></div>
			</td>
		</tr> -->
	</table>
	<table class="TABLEWIDTH" border="0" align="center" cellpadding="1px" cellspacing="1px">
		<tr class="HEADER">
			<td colspan="2">Discount Rule</td>
		</tr>
		<tr>
			<td class="LABEL" width="50%">Discount Limit</td>
			<td class="CONTROL" width="50%">
			<input type="text" class="txtFldMin" maxlength="3" name="strDiscountLimit" disabled="disabled" onkeypress="return validateData(event,5);" value="${localTariffBean.strDiscountLimit}"> %</td>
		</tr>
		<tr>
			<td class="LABEL" width="50%">Discount Privilege</td>
			<td class="CONTROL" width="50%">
			<input type="text" class="txtFldMin" maxlength="3" name="strDiscountPrivilege" disabled="disabled" onkeypress="return validateData(event,5);" value="${localTariffBean.strDiscountPrivilege}"> %</td>
		</tr>
		
	</table>
	<table class="TABLEWIDTH" border="0" align="center">
		<tr class="TITLE">
			<td colspan="5">Default Value</td>
		</tr>
		<tr>
			
			<td class="multiLabel" width="20%">Hospital Service</td>
			<td class="multiLabel" width="20%">Default Rate</td>
			<td class="multiLabel" width="20%">Actual Cost</td>
			<td class="multiLabel" width="20%">Service tax(%)</td>
			<td class="multiLabel" width="20%">Visibility</td>
			
		</tr>
	</table>
	<div id="divId2" style="display:block">
<!--	<table class="TABLEWIDTH" border="0" align="center">-->
	  ${localTariffBean.toDefault}
<!--		</table>-->
	</div>
	<table class="TABLEWIDTH" border="0" align="center">
		<tr class="HEADER">
			<td colspan="2" width="50%">
			<table width="100%" border="0" align="center">
				<tr class="TITLE">
					<td width="50%">Contribution Details</td>
					<td width="50%">
					
					</td>
				</tr>
			</table>
			</td> 
		</tr>
	</table>
	<table class="TABLEWIDTH" border="0" align="center">
		<tr>
			<td class="multiLabel" width="55%">Department</td>
			<td class="multiLabel" width="35%">Share(%)</td>
			
		</tr>
	</table>
	<div id="mrow" align="center"> <bean:write name="localTariffBean" property="strMultiRow" filter="false"/> </div>
	<table class="TABLEWIDTH" border="0" align="center">
	<tr>  
	<td class ="LABEL" width ="50%">Tariff Type</td>
	<td width="30%" class="CONTROL"><div id="tariffType"></div></td>
	</tr>
	
	<tr>  
	<td class ="LABEL" width ="50%">Is Default (for IPD)</td>
	<td class="CONTROL" width="30%">

<html:select property="strIsDefaultForIpd" name="localTariffBean" styleClass="comboNormal" disabled="true" >
	<html:option value="0">Select Value</html:option>
	<html:option value='1'>Except New Born Baby</html:option>
		<html:option value='2'>New Born Baby</html:option>
		<html:option value='3'>All</html:option>
	</html:select>

 </td>
	</tr>
	<tr>  
	<td class ="LABEL" width ="50%"><span class="style1"></span> Effective From</td>
	<td class="CONTROL" width="30%">${localTariffBean.strEffectiveFrom} </td>
		<tr>
			<td class="LABEL" width="50%">Remarks</td>
			<td class="CONTROL" width="50%">
			<bean:write name="localTariffBean" property="remarks" filter="false"/></td>
		</tr>
		<tr >
    <td width ="45%" class ="LABEL">Record Status </td>
    <td width ="45%" class ="CONTROL" >
    <logic:equal name="localTariffBean" property="isValid" value="1">Active</logic:equal>
    <logic:equal name="localTariffBean" property="isValid" value="2">Inactive</logic:equal>
   </td>
    </tr>
		<tr class="FOOTER">
			<td colspan="2"></td>
		</tr>
	</table>
	<div align="center"><!-- <img	name="cancel"   style="cursor:pointer;cursor:hand"
		src="../../hisglobal/images/btn-ccl.png" onClick="window.close();" /> -->
		<br><a href="#" class="button" onclick="window.close()()"><span class="cancel">Cancel</span></a>
	</div>
	
	<input type="hidden" name="strSId" value="${localTariffBean.serviceName}^${localTariffBean.strHospitalCode}" />
	<input type="hidden" name="strModTariffId" value="${localTariffBean.tariffId}" />
	<input type="hidden" name="strCostType" value="${localTariffBean.strCostType}" />
	
</html:form>


</body>
</html>


