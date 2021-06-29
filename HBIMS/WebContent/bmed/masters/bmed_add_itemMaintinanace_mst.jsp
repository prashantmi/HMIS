<%-- <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%> --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/dateTag.tld" prefix="dateTag"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>
<%@ taglib uri="/WEB-INF/comboPersistence.tld" prefix="cmbPers"%>


<html>
<head>
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
<title>Item Maintenance</title>

<link href="../css/master.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/calendar-tas.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/newpopup.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/popup.css" rel="stylesheet" type="text/css">
	
	<style type="text/css">
            .pg-normal 
            {
                color: blue;
                font-weight: normal;
                text-decoration: none;
                cursor: pointer;
                
                
            }
            .pg-selected 
            {
                color: red;
                font-weight: bold;
                text-decoration: underline;
                cursor: pointer;
                
            }
            .pg-qualified 
            {
                color: green;
                font-weight: bold;
                text-decoration: underline;
                cursor: pointer;
                
            }
            </style>

<script language="JavaScript"
	src="../../hisglobal/masterutil/js/master.js"></script>
<script language="JavaScript" src="../../hisglobal/js/tab.js"></script>
<script language="JavaScript" src="../../hisglobal/js/calendar.js"></script>
<script language="Javascript" src="../../hisglobal/js/validation.js"></script>
<script language="Javascript" src="../../hisglobal/js/util.js"></script>
<script language="Javascript" src="../../hisglobal/js/newpopup.js"></script>
<script language="Javascript" src="../../hisglobal/js/popup.js"></script>
<script language="Javascript" src="../../bmed/js/itemMaintenance_mst.js"></script>

</head>
<body onload="onLoadFunction();">
<html:form action="/masters/ItemMaintenanceMstCNT"
	name="itemMaintenanceMstBean"
	type="bmed.masters.controller.fb.ItemMaintenanceMstFB">

	<center>
	<div id="errMsg" class="errMsg"><bean:write
		name="itemMaintenanceMstBean" property="strErr" /></div>
	<div id="warningMsg" class="warningMsg"><bean:write
		name="itemMaintenanceMstBean" property="strWarning" /></div>
	<div id="normalMsg" class="normalMsg"><bean:write
		name="itemMaintenanceMstBean" property="strMsgString" /></div>


	</center>

	<table class="TABLEWIDTH" align="center" border="0" cellspacing="1px"
		cellpadding="1px">
		<tr class="HEADER">
			<td colspan="4">Item Maintenance&gt;&gt; Add</td>
		</tr>
		<tr>
		<tr>
			<td colspan="1" class="LABEL">Department / Store</td>
			<td colspan="1" class="CONTROL"><bean:write
				name="itemMaintenanceMstBean" property="strStoreName" filter="false" />

			</td>
			<td colspan="1" class="LABEL"><font color="red">*</font>Item
			Category</td>
			<td colspan="1" class="CONTROL"><select name="strItemCatgId"
				class='comboNormal' onChange="getItemNameCombo();">
				<bean:write name="itemMaintenanceMstBean"
					property="strItemCategoryCmb" filter="false" />
			</select></td>
		</tr>

		<tr>
			<td colspan="1" class="LABEL"><font color="red">*</font>Engineering
			Item Type</td>
			<td colspan="1" class="CONTROL">
			<div id="engineeringItemTypeId"><select
				name="strEnggItemTypeId" class='comboNormal'
				onchange="getEnggItemSubTypeCombo();">
				<bean:write name="itemMaintenanceMstBean"
					property="strEnggItemTypeCmb" filter="false" />
			</select></div>
			</td>
			<td colspan="1" class="LABEL"><font color="red">*</font>Engineering
			Item Sub-Type</td>
			<td colspan="1" class="CONTROL">
			<div id="engineeringItemSubTypeId"><select
				name="strEnggItemSubTypeId" class='comboNormal'>
				<bean:write name="itemMaintenanceMstBean"
					property="strEnggItemSubTypeCmb" filter="false" />
				<option value="0">Select Value</option>
			</select></div>
			</td>
		</tr>

		<tr>
			<td colspan="2" class="LABEL"><font color="red">*</font>Complaint Type</td>
			<td colspan="2" class="CONTROL">
			
			  <input type="radio" name="strIsInternal" value="0"  onClick="changeViewMode(this);" /> Internal
			  <input type="radio" name="strIsInternal" value="1" onClick="changeViewMode(this);" /> External
			  
			</td>
			
		</tr>
		<tr>
		
		   <td colspan="1" class="LABEL"><font color="red">*</font>Item
			Name</td>
			<td colspan="1" class="CONTROL">
			<div id="itemNameId"><select name="strItemid"
				class='comboNormal' onchange="getStockDtl();">
				<bean:write name="itemMaintenanceMstBean" property="strItemNameCmb"
					filter="false" />
				<option value="0">Select Value</option>
			</select></div>
			</td>
		
			<td colspan="1" class="LABEL"><font color="red">*</font>Maintenance
			Name</td>
			
			
			<td colspan="1" class="CONTROL">
			<div id="maintenanceId"><select name="strMaintenanceId"
				class='comboNormal' onchange="getLeftItemList();">
				<bean:write name = "itemMaintenanceMstBean" property = "strMaintenanceCmb" filter = "false" />
				 <option value="0">Select Value</option>
			</select></div>
			</td>
			
		</tr>
		<tr>
			<td width="25%" class="LABEL" valign="top"><font color="red">*</font>Maintenance Desc.</td>
			<td width="25%" class="CONTROL"><textarea
				name="strMaintenanceDesc" cols="25" rows="2"
				onkeypress="return validateData(event,22);"></textarea></td>

			<td width="25%" class="CONTROL"></td>
			<td width="25%" class="CONTROL"></td>


		</tr>

	</table>
	<div id="stockValueId"></div>
	
	<%-- Warranty Details Start--%>
	<DIV ID="warrantyDTL" style="display:none;"></DIV>
	<%-- Warranty Details End--%>

	<%-- Maintenance Contract Details Start--%>
	<DIV ID="maintenanceDTL" style="display:none;"></DIV>
	<%-- Maintenance Contract Details End--%>


	<table class="TABLEWIDTH" align="center" border="0" cellspacing="1px"
		cellpadding="1px">
		<tr class="TITLE">
			<td colspan="7" align='left'><font color="red">*</font>Task Name</td>
		</tr>
		<tr>
		<tr class="FOOTER">
			<td class="LABEL" colspan="3">
			<div id='' align='center'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Available Task</div>
			</td>
			<td class="LABEL" colspan="4">
			<div id='' align='center'>Selected Task&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>
			</td>
		</tr>
		<tr>
			<td class="CONTROL" colspan="2">

			<div id="LeftItemIds" align="right"><select
				name="strLeftItemIds" size="8" multiple style="width: 280px">
				<bean:write name="itemMaintenanceMstBean" property="strLeftItemList"
					filter="false" />
			</select></div>
			</td>
			<td width="6%" class="CONTROL" colspan="2">

			<center><img src="../../hisglobal/images/forward3.gif"
				width="35" height="31" onclick="LeftListTransfer();"></center>

			<center><img src="../../hisglobal/images/forwardward.gif"
				width="35" height="31" align="middle"
				onClick="shiftAllToRight('strLeftItemIds','strRightItemIds',1);" /></center>
			<br />
			<center><img src="../../hisglobal/images/backward.gif"
				width="35" height="31"
				onClick="shiftAllToLeft('strLeftItemIds','strRightItemIds',1);">
			</center>

			<center><img src="../../hisglobal/images/back3.gif"
				width="35" height="31"
				onclick="shiftToLeft('strLeftItemIds','strRightItemIds',1);" /></center>
			</td>

			<td colspan="3" class="CONTROL">
			<div id="RightItemIds" align="left"><select
				name="strRightItemIds" size="8" multiple style="width: 280px">
				<bean:write name="itemMaintenanceMstBean"
					property="strRightItemList" filter="false" />
			</select></div>
			</td>
		</tr>
		<tr class="TITLE">
			<td colspan="7" align='left'></td>
		</tr>
		<tr>
	</table>

	<table border="0" class="TABLEWIDTH" align="center" cellspacing="1px"
		cellpadding="1px">
		<tr>
			<td colspan="1" class="LABEL"><font color="red">*</font>Preferred
			Time From</td>
			<td colspan="1" class="CONTROL"><input type="text" size='2'
				onblur="twoDig(this)" onkeyup="lessThen24(this)" name="strFromHour"
				value="--" dir="rtl" maxlength="2"
				onkeypress="return validateData(event,5);"> : <input
				type="text" size="2" name="strFromMin" value="--" dir="rtl"
				maxlength="2" onkeyup="lessThen60(this)" onblur="twoDig(this)"
				onkeypress="return validateData(event,5);"></td>
			<td colspan="1" class="LABEL"><font color="red">*</font>Preferred
			Time To</td>
			<td colspan="1" class="CONTROL"><input type="text" size='2'
				onblur="twoDig(this)" onkeyup="lessThen24(this)" name="strToHour"
				value="--" dir="rtl" maxlength="2"
				onkeypress="return validateData(event,5);"> : <input
				type="text" size="2" name="strToMin" value="--" dir="rtl"
				maxlength="2" onkeyup="lessThen60(this)" onblur="twoDig(this)"
				onkeypress="return validateData(event,5);"></td>
		</tr>
		<tr>
			<td colspan="1" class="LABEL"><font color="red">*</font>Maintenance
			Period</td>
			<td colspan="1" class="CONTROL"><input type="text"
				class="txtFldNormal" name="strMaintenancePeriod" maxlength="3"
				value="" onkeypress="return validateData(event,5);"></td>
			<td colspan="1" class="LABEL"><font color="red">*</font>Unit
			Name</td>
			<td colspan="1" class="CONTROL"><select name="strUnitId"
				class='comboNormal'>
				<bean:write name="itemMaintenanceMstBean" property="strUnitIdCmb"
					filter="false" />
			</select></td>
		</tr>

		<tr>
			<td colspan="1" class="LABEL"><font color="red">*</font>Duration
			of alert before the Scheduled Days</td>
			<td colspan="1" class="CONTROL"><input type="text"
				class="txtFldNormal" name="strMaintenancePeriodInDays" maxlength="3"
				value="" onkeypress="return validateData(event,5);">Day's</td>
			<td colspan="1" class="CONTROL"></td>
			<td colspan="1" class="CONTROL"></td>
		</tr>


		<tr>
			<td class="LABEL" width="25%">
			<div align="right"><font color="red">*</font> Effective From</div>
			</td>
			<td class="CONTROL"><dateTag:date name="strEffectiveFrom"
				value="${itemMaintenanceMstBean.strCtDate}"></dateTag:date></td>

			<td width="25%" class="LABEL">Remarks</td>
			<td width="25%" class="CONTROL"><textarea name="strRemarks"
				cols="25" rows="2" onkeypress="return validateData(event,22);"></textarea></td>
		</tr>

	</table>

	<table border="0" class="TABLEWIDTH" align="center" border="0"
		cellspacing="1px" cellpadding="1px">
		<tr class="FOOTER">
			<td colspan="2">[<font size="2" color="blue">*</font>Branded/Reserved Item]<font size="2" color="red">*</font> Mandatory
			Fields</td>
		</tr>
	</table>

	<table border="0" class="TABLEWIDTH" align="center">
		<tr>

			<td align="center"><img style="cursor: pointer;"
				src="../../hisglobal/images/btn-sv.png"
				onClick="return validate1();" /> <img style="cursor: pointer;"
				src="../../hisglobal/images/btn-clr.png" onClick="ClearPage();">
			<img style="cursor: pointer;"
				src="../../hisglobal/images/btn-ccl.png" onClick="cancel('LIST');">
			</td>
		</tr>
	</table>
	<input type="hidden" name="hmode" />
	<input type="hidden" name="comboValue"
		value="${itemMaintenanceMstBean.comboValue}">
	<input type="hidden" name="strStoreId"
		value="${itemMaintenanceMstBean.strStoreId}">
	<input type="hidden" name="strStoreIdValue"
		value="${itemMaintenanceMstBean.strStoreIdValue}">
	<input type="hidden" name="strStockInfoVal"
		value="">
	<input type="hidden" name="strComplaintFlg" 	value="">	
	<input type="hidden" name="strUploadFileId" 	value="">
		

 <div id="blanket" style="display:none;"></div>
   <div class="popUpDiv" id="popUpDiv" style="display:none;">
   <table bgcolor="white">
     <tr>
      <td>
     
         <div id="stockDtlsDivId" style="display:block;"></div>
       </td>
     </tr>
    </table>
   </div>



	<cmbPers:cmbPers />
</html:form>

</body>
</html>
