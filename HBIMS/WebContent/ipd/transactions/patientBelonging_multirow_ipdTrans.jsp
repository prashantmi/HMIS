<%@taglib uri="/WEB-INF/dateTag.tld" prefix="date"%>
<form name="multirow">
<div id="rowAdded1" style="display: none">
<table class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="1px">
	<tr>
		<td width="10%" class="multiControl"><input type='checkbox' onclick="checkBOrI(this)" class='txtFldNormal'
			name="strBelonging" id="strBelonging#delIndex#"></td>
		<td width="10%" class="multiControl"><input type='checkbox' onclick="checkBOrI(this)" class='txtFldNormal'
			name="strIssue" id="strIssue#delIndex#"></td>
		<td width="46%" class="multiControl"><input type='text' class='txtFldNormal'
			name="strItemName" id="strItemName#delIndex#" onkeypress="return validateData(event,9);" onkeyup="checkMultirow();" maxlength="50"></td>
		<td width="10%" class="multiControl"><input type='text' class='txtFldNormal'
			name="strItemQuantity" id="strItemQuantity#delIndex#" onkeypress="return validateData(event,5);" maxlength="3"></td>
		<td width="20%" class="multiControl"><input type='text' class='txtFldNormal'
			name="strRemarks" id="strRemarks#delIndex#" onkeypress="return validateData(event,9);" maxlength="100"></td>
		
	<!--<td width="24%" class="multiControl"><input type='text' class='txtFldNormal'
		    name="strItemReturnTo" id="strItemReturnTo#delIndex#"></td>  -->
		<td width="4%" class="multiControl"><img style="cursor: hand;cursor: pointer"
			style="cursor: pointer"
			src="../../hisglobal/images/minus.gif"
			onClick="deleteRow('#delIndex#','1','1');"></td>
	</tr>
</table>
</div>
<input type="hidden" name="rowIndex1" value="0"> <input
	type="hidden" name="rowLength1" value="0"></form>
