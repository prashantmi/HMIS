<%@taglib uri="/WEB-INF/dateTag.tld" prefix="dateTag"%>

<form name="multirow">
 <div id="rowAdded1" style="display:none">
 <table class="TABLEWIDTH" align="center">	
	<tr>
		<td  class="multiControl" width="10%"><input type="text" name="strCat"    id="strCat#delIndex#" class="txtFldNormal" onkeypress="return validateData(event,9);" /></td>
		<td  class="multiControl" width="10%"><input type="text" name="strPkg"    id="strPkg#delIndex#" class="txtFldMin" onkeypress="return validateData(event,17);" /></td>
		<td  class="multiControl" width="8%"><input type="text" name="strBatch"  id="strBatch#delIndex#" class="txtFldMin" value=""  onkeypress="return validateData(event,17);"/></td>  
		<td  class="multiControl" width="8%"><input type="text" name="strExpiry" id="strExpiry#delIndex#" class="txtFldMin" value="" onkeypress="return validateData(event,5);" /></td>
		<td  class="multiControl" width="8%"><input type="text" name="strQty"    id="strQty#delIndex#" class="txtFldMin" value="" onkeypress="return validateData(event,5);"/></td>
		<td  class="multiControl" width="8%"><input type="text" name="strQtyFinal" id="strQtyFinal#delIndex#" class="txtFldMin" value=''onkeypress="return validateData(event,5);"/></td>
		<td  class="multiControl" width="8%"><input type="text" name="strPTW"    id="strPTW#delIndex#" value="" class="txtFldMin" onkeypress="return validateData(event,7);" /></td>
		<td  class="multiControl" width="8%"><input type="text" name="strPTR"    id="strPTR#delIndex#" value="" class="txtFldMin" onkeypress="return validateData(event,7);" /></td>
		<td  class="multiControl" width="8%"><input type="text" name="strMrp"     id="strMrp#delIndex#" class="txtFldMin" value="" onkeypress="return validateData(event,7);" /></td>
		<td  class="multiControl" width="8%">
		     <input type="text" name="strAmt" id="strAmt#delIndex#" value="" onKeyUp="Calculation('#delIndex#');" class="txtFldMin" onkeypress="return validateData(event,7);" />
		</td>
		<td  class="multiControl" width="8%">
		     <input type="text" name="strFinalMul" id="strFinalMul#delIndex#" value="0" class="txtFldMin" disabled />
		</td>
		<td  class="multiControl" width="3%">
		
		<img src ="../../hisglobal/images/minus.gif"  
			 style ="cursor:hand;cursor:pointer"  
			 title ="Remove Tariff"
			 onClick ="deleteRow('#delIndex#','1','0'),CalAmt('#delIndex#');"></td>
	</tr>
</table>
</div>
<input type="hidden" name="rowIndex1" value="0"> 
<input type="hidden" name="rowLength1" value="0">
</form>