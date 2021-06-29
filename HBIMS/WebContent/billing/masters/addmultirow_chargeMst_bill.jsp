
<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>

<html>
<head>
<meta charset=utf-8>
<title>Charge Master Multirow</title>
</head>
<body>
<form name="multirow">

<div id = "rowAdded1" style="display: none">
	<table class="TABLEWIDTH"  border="0" align="center" cellspacing="1px">
		<tr>			
			<td class="multiControl" width="20%"><div id="strPatientCategorydiv#delIndex#"><select class="comboNormal" name="strPatientCategory" id="strPatientCategory#delIndex#"> <bean:write name="chargeBean" property="patientCategory" filter="false"/></select></div></td>
			<td class="multiControl" width="14%" style="display: none"><input class="txtFldSmall" type="text" name="strProductCost" value="0" id="strProductCost#delIndex#" maxlength="9" onkeypress="return validateData(event,7);" onkeyup="calcTotalAmount('#delIndex#');"></td>
			<td class="multiControl" width="14%"><input class="txtFldSmall" type="text" name="strTariffCost" value="" id="strTariffCost#delIndex#" maxlength="9" onkeypress="return validateData(event,7);" onkeyup="calcTotalAmount('#delIndex#');" ></td>
			<td class="multiControl" width="14%"><input class="txtFldSmall" type="text" name="strTotalCost" value="" id="strTotalCost#delIndex#" maxlength="9" onkeypress="return validateData(event,7);" readonly="readonly"></td>
			<td class="multiControl" width="14%"><input type="checkbox" name="strIsAdvance" value="1" id="strIsAdvance#delIndex#" checked="checked"></td>
			<td class="multiControl" width="14%"><input type="checkbox" name="strIsRefundable" value="1" id="strIsRefundable#delIndex#" checked="checked"></td>
			<td class="multiControl" width="10%"><img src="../../hisglobal/images/minus.gif" name="minus" id="minus#delIndex#" onClick="deleteRow('#delIndex#','1','0');"></td>
		</tr>
	</table>
</div>
<input type="hidden" name="rowIndex1" value="0">
<input type="hidden" name="rowLength1" value="0">

</form>

</body>
</html>