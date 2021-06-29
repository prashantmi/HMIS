<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<form name="multirow">

<div id = "rowAdded1" style="display: none">
	<table class="TABLEWIDTH"  border="0" align="center" cellpadding="1px" cellspacing="1px">
		<tr>			
			<td class="multiControl" width="40%"><div id="trfcmb#delIndex#">
			<select name="strMultiTariffId" id="tariffname#delIndex#" class="comboMax">
		      <option value='0'>Select Value</option>
			</select></div>
			</td>
			<td class="multiControl" width="18%"><input type="text" class="txtFldSmall" 
			    name="strMultiQty" value="" maxlength="2" id="quantity#delIndex#" 
			    onkeypress="return validateData(event,5);"></td>
			<td class="multiControl" width="37%">
			<div id="trfUnitDivId#delIndex#">
			<select name="strMultiUnitId" id="unitname#delIndex#"  class="comboNormal" >
		      <option value='0'>Select Value</option>
			</select>
			</div>
			</td>			
			<td class="multiControl" width="5%">
			  <img src="../../hisglobal/images/minus.gif" name="minus" id="minus#delIndex#" 
			  onClick="deleteRow('#delIndex#','1','0');"></td>
		</tr>
	</table>
</div>
<input type="hidden" name="rowIndex1" value="0">
<input type="hidden" name="rowLength1" value="0">

</form>