<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>

<form name="multirow">
<div id="rowAdded1" style="display: none">
<table width="100%">
	<tr>
		<td width="35%" class="multiControl"><select name="strGroupName" class='comboNormal'
			id="strGroupName#delIndex#">
			<bean:write name="clientApprovalDetailTransBean" property="strGroupNameValues" filter="false"/>
		</select>
		</td>
		<td width="35%" class="multiControl"><input type="text" name="strSancAmt" readonly="readonly" class='txtFldNormal' onkeypress='return validateData(event,7);' value='' id="strSancAmt#delIndex#">
		</td>
		<td width="20%" class="multiControl"><input type="button" name="strTariffWise" value="..." id="strTariffWise#delIndex#" onClick="return showTariffPopup(this,'#delIndex#');">
		<input type="hidden" name="strTariffDetails" id="strTariffDetails#delIndex#">
		</td>
		<td width="10%" class="multiControl"><img
			src="../../hisglobal/images/minus.gif"
			onClick="deleteRow('#delIndex#','1','0');"></td>
	</tr>
</table>
</div>
<input type="hidden" name="rowIndex1" value="0"> 
<input type="hidden" name="rowLength1" value="0">

<div id="rowAdded2" style="display: none">
<table width="400">
	<tr>
		<td width="236" class="multiControl">
		<div id="tariffOptionsId"></div>
		</td>
		<td width="170" class="multiControl"><input type="text" name="strTariffSancAmt" onkeypress='return validateData(event,7);' class='txtFldNormal' id="strTariffSancAmt#delIndex#">
		</td>
		
		<td width="23" class="multiControl"><img
			src="../../hisglobal/images/minus.gif"
			onClick="deleteRow('#delIndex#','2','0');"></td>
	</tr>
</table>
</div>
<input type="hidden" name="rowIndex2" value="0"> 
<input type="hidden" name="rowLength2" value="0">

</form>
