<%@taglib uri="/WEB-INF/dateTag.tld" prefix="dateTag"%>

<form name="multirow">

<div id="rowAdded1" style="display:none">

<table class="TABLEWIDTH" align="center" cellpadding="1px"
	cellspacing="1px">
	<tr>
		<td class="multiControl" width="25%"><input type="text"
			name="strOfflineTariffName" id="strOfflineTariffName#delIndex#"
			class="txtFldBig" onmouseover="Tip(this.value,SHADOW,true)" onmouseout="UnTip()"
			onblur="enableDisableMinusAndSaveButton(this,'#delIndex#');"
			onkeypress="return setSelectValue1('strOfflineTariffName#delIndex#','1',event,'setDropDownSelectedTariff^tariffFullNameDiv','#delIndex#');"
			onkeyup="return showMultiRowAdder(event),showTariffSearchPopup(event,'strOfflineTariffName#delIndex#','#delIndex#'),searchSel(event,'strOfflineTariffName#delIndex#','1',this);" />
		<input type='hidden' name='strOfflineTariffDetailsHidden'
			id='strOfflineTariffDetailsHidden#delIndex#' value=''></td>
		<td class="multiControl" width="11%">
		<div id="strOfflineTariffRateUnitDivId#delIndex#">/</div>
		<div id="strOfflineTempTariffRateUnitDivId#delIndex#" style="display: none;"><input type="text" name="strOfflineTempTariffRateUnit" id="strOfflineTempTariffRateUnit#delIndex#" class="txtFldMin" value="0"  maxlength="8" onkeypress="return validateData(event,7);" onkeyup="setRateValue('#delIndex#');" > / </div>
		<input type="hidden" name="strOfflineTariffRateUnit"
			id="strOfflineTariffRateUnit#delIndex#" class="txtFldNormal" /></td>
		<td class="multiControl" width="10%"><input type="text" 
			name="strOfflineTariffQty" id="strOfflineTariffQty#delIndex#" maxlength="3" onkeypress="return validateData(event,5);"
			class="txtFldMin" value="0"
			onkeypress="return validateData(event,5);" maxlength="8"
			onkeyup="calcOffLineTariffNetAmount('#delIndex#');" /></td>
		<td class="multiControl" width="10%">
		<div id='offlineTariffUnitDivId#delIndex#'><select
			name='strOfflineTariffUnit' id='strOfflineTariffUnit#delIndex#'
			class='comboMin'>
			<option value='0'>Select Value</option>
		</select></div>
		</td>
		<td class="multiControl" width="10%">
		<div id="strOfflineTariffServiceTaxDivId#delIndex#">0</div>
		<input type="hidden" name="strOfflineTariffServiceTax"
			id="strOfflineTariffServiceTax#delIndex#" class="txtFldMin" value="0" />
			<input type="hidden" name="strOfflineTariffServiceTaxAmtVal"
			id="strOfflineTariffServiceTaxAmtVal#delIndex#" class="txtFldMin" value="0" />
			</td>
		<td class="multiControl" width="12%" style="font-weight: bold"> <input type="hidden" name="strOfflineActualTariffAmtVal" id="strOfflineActualTariffAmtVal#delIndex#" value="0.00" >
		<div id="strOfflineTariffNetAmountDivId#delIndex#">0.00</div>
		<input type="hidden" name="strOfflineTariffNetAmount"
			id="strOfflineTariffNetAmount#delIndex#" value="0.00"
			class="txtFldNormal" style="font-weight: bold" /></td>
		<td width="5%" class="multiControl"><img style="cursor: hand; cursor: pointer"
			src="../../hisglobal/images/minus.gif" name="tariffMinus"
			onClick="removeTariffRow('#delIndex#');"></td>
	</tr>
</table>
</div>
<input type="hidden" name="rowIndex1" value="0"> 
<input type="hidden" name="rowLength1" value="0">


</form>