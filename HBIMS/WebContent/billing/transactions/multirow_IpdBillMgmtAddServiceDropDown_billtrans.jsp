<%@ taglib uri="/WEB-INF/dateTag.tld" prefix="date"%>

<form name="multirow">

<div id="rowAdded1" style="display:none">

<table class="TABLEWIDTH" align="center" cellpadding="1px"
	cellspacing="1px">
	<tr>

		<td class="multiControl" width="14%"><input type="text" tabindex="1" autocomplete="off"
			name="strOfflineTariffName" id="strOfflineTariffName#delIndex#"
			class="txtFldBig" onmouseover="Tip(this.value,SHADOW,true)" onmouseout="UnTip()"
			 onkeypress="return setSelectValue1('strOfflineTariffName#delIndex#','1',event,'setDropDownSelectedTariff^tariffFullNameDiv','#delIndex#');"
			onkeyup="return showMultiRowAdder(event),showTariffSearchPopup(event,'strOfflineTariffName#delIndex#','#delIndex#'),getTariffDtls(this , event , '#delIndex#' );"  />
		<input type='hidden' name='strOfflineTariffDetailsHidden'
			id='strOfflineTariffDetailsHidden#delIndex#' value=''></td>
		<td class="multiControl" width="8%">
		<div id="strOfflineTariffRateUnitDivId#delIndex#">/</div>
		<div id="strOfflineTempTariffRateUnitDivId#delIndex#" style="display: none;"><input type="text" name="strOfflineTempTariffRateUnit" id="strOfflineTempTariffRateUnit#delIndex#" tabindex="1" class="txtFldMin" value="0"  maxlength="8" onkeypress="return validateData(event,7);" onkeyup="setRateValue('#delIndex#');" > / </div>
		<input type="hidden" name="strOfflineTariffRateUnit"
			id="strOfflineTariffRateUnit#delIndex#" class="txtFldNormal" /></td>
		<td class="multiControl" width="8%"><input type="text" 
			name="strOfflineTariffQty" id="strOfflineTariffQty#delIndex#" maxlength="3" class="txtFldMin" value="0" tabindex="1"
			onkeypress="return validateData(event,5);" 
			onkeydown="setFocusToTariffCode(event);"
			onkeyup="calcOffLineTariffNetAmount('#delIndex#');" /></td>
		<td class="multiControl" width="8%">
		<div id='offlineTariffUnitDivId#delIndex#'><select tabindex="2"
			name='strOfflineTariffUnit' id='strOfflineTariffUnit#delIndex#'
			class='comboMin'>
			<option value='0'>Select Value</option>
		</select></div>
		</td>
		<td class="multiControl" width="12%">
			<input size="7%" type="text" name="strOfflineTariffDate" readonly='readonly' id="strOfflineTariffDate#delIndex#" class='txtFldDate'/>
			 <img src="/HBIMS/hisglobal/images/imgDatepicker.png"  style="cursor: pointer; border: 1px solid red; width: 10px;" 
			 	  title="Date selector" onmouseover="datePickerMultiRow1(event,'strOfflineTariffDate',this);this.style.background='red';" 
			 	  onmouseout="this.style.background=''" id="strOfflineTariffDate1#delIndex#"  name="strOfflineTariffDate1"/>
		</td>
		<td class="multiControl" width="9%">
		<div id='priorityDivId#delIndex#'><select tabindex="2"
			name='strPriority' id='strPriority#delIndex#'
			class='comboMin' onchange="calcOffLineTariffNetAmount('#delIndex#');onEntDiscount('#delIndex#');">
			<option value='1'>Normal</option>
			<option value='2'>Urgent</option>
		</select></div>
		</td>	
		<td class="multiControl" width="8%">
		<input type="text" 
			name="strDiscount" id="strDiscount#delIndex#" maxlength="10" class="txtFldMin" value="0" tabindex="1"
			onkeypress="return validateData(event,7);" onkeyup="onEntDiscount('#delIndex#');" autocomplete="off" /></td>
		<td class="multiControl" width="9%">
		<div id="strDiscountTypeDivId#delIndex#"><select tabindex="2"
			name="strDiscountType" id="strDiscountType#delIndex#"
			class="comboMin" onchange="onEntDiscount('#delIndex#');">
			<option value='1'>Fixed</option>
			<option value='2'>Percentage</option>
		</select></div>
		</td>
		<td class="multiControl" width="8%">
		<div id="strDiscountAmtDivId#delIndex#">0.00</div>
			<input type="hidden" name="strDiscountAmt"
			id="strDiscountAmt#delIndex#" class="txtFldNormal" /></td>
		<td class="multiControl" width="4%">
		<div id="strOfflineTariffServiceTaxDivId#delIndex#">0</div>
		<input type="hidden" name="strOfflineTariffServiceTax"
			id="strOfflineTariffServiceTax#delIndex#" class="txtFldMin" value="0" />
			<input type="hidden" name="strOfflineTariffServiceTaxAmtVal"
			id="strOfflineTariffServiceTaxAmtVal#delIndex#" class="txtFldMin" value="0" />
			</td>
		<td class="multiControl" width="9%" style="font-weight: bold"> <input type="hidden" name="strOfflineActualTariffAmtVal" id="strOfflineActualTariffAmtVal#delIndex#" value="0.00" >
		<div id="strOfflineTariffNetAmountDivId#delIndex#">0.00</div>
		<input type="hidden" name="strOfflineTariffNetAmount"
			id="strOfflineTariffNetAmount#delIndex#" value="0.00"
			class="txtFldNormal" style="font-weight: bold" /></td>
		<td width="3%" class="multiControl"><img style="cursor: hand; cursor: pointer" tabindex='2'
			src="../../hisglobal/images/minus.gif" name="tariffMinus"
			onClick="removeTariffRow('#delIndex#');"></td>
	</tr>
</table>
</div>
<input type="hidden" name="rowIndex1" value="0"> 
<input type="hidden" name="rowLength1" value="0">


</form>