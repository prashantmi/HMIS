
<form name="multirow">
<div id="rowAdded1" style="display:none">
<table bgcolor='#6097BC' class="TABLEWIDTH" align="center" cellpadding="1px" cellspacing="1px" >
 <tr>
    <td class="multiControl"  width="30%">
		<input type="hidden" name="itemParamValue" id="itemParamValue#delIndex#">
		<input type="hidden" name="itemCalcValue" id="itemCalcValue#delIndex#">
		<input type="hidden" name="itemUserValue" id="itemUserValue#delIndex#">

		<div id="itemParaId1#delIndex#">
		</div>
	</td>
	

	<td class="multiControl" width="20%">
		<div id="itemParaId11#delIndex#"></div>
	</td>
	
	<td class="multiControl"  width="10%">
			<div id="itemParaId4#delIndex#"></div>
	</td>
	
	<td class="multiControl"  width="10%">
			<input type="text" name="strReqQty" id="strReqQty#delIndex#" class='txtFldMin' onkeyup="return checkQty('#delIndex#','strReqQty','strUnitName');" onkeypress="return validateData(event,7);" >
	</td>
	
	<td class="multiControl"  width="15%">
			<div id="itemParaId0#delIndex#"></div>
	</td>
	<td class="multiControl" width="15%">
	<div id='strCostFinalDivId#delIndex#'>0.00</div>
	<input type="hidden"  name="strCostFinal" value="0.00" id="strCostFinal#delIndex#" class="txtFldMin" maxlength ="5" onkeypress="return validateData(event,7);">
	</td>
  </tr>
</table>
</div>
	<input type="hidden" name="rowIndex1" value="0"> 
	<input type="hidden" name="rowLength1" value="0">


</form>