<!DOCTYPE html>
        <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/dateTag.tld" prefix="date"%>
<head><meta charset=UTF-8></head>
<form name="multirow">

<div id="rowAdded1" style="display:none">

<table class="TABLEWIDTH" align="center" cellpadding="1px"
	cellspacing="1px">
	<tr>

		<td class="multiControl" width="20%"><input type="text"
			name="strOfflineTariffName" id="strOfflineTariffName#delIndex#"
			class="txtFldBig" onmouseover="Tip(this.value,SHADOW,true)" onmouseout="UnTip()"
			onblur="enableDisableMinusAndSaveButton(this,'#delIndex#');"
			onkeypress="return setSelectValue1('strOfflineTariffName#delIndex#','1',event,'setDropDownSelectedTariff^tariffFullNameDiv','#delIndex#');"
			onkeyup="return showMultiRowAdder(event),showTariffSearchPopup(event,'strOfflineTariffName#delIndex#','#delIndex#'),searchSel(event,'strOfflineTariffName#delIndex#','1',this);" />
		<input type='hidden' name='strOfflineTariffDetailsHidden'
			id='strOfflineTariffDetailsHidden#delIndex#' value=''>
			<input type='hidden' name='strCompChargeCheck' id='strCompChargeCheck#delIndex#' value='1'>
			</td>
		<td class="multiControl" width="8%">
		<div id="strOfflineTariffRateUnitDivId#delIndex#">/</div>
		<div id="strOfflineTempTariffRateUnitDivId#delIndex#" style="display: none;"><input type="text" name="strOfflineTempTariffRateUnit" id="strOfflineTempTariffRateUnit#delIndex#" class="txtFldMin" value="0"  maxlength="8" onkeypress="return validateData(event,7);" onkeyup="setRateValue('#delIndex#');" > / </div>
		<input type="hidden" name="strOfflineTariffRateUnit"
			id="strOfflineTariffRateUnit#delIndex#" class="txtFldNormal" /></td>
		<td class="multiControl" width="8%"><input type="text" 
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
		<td class="multiControl" width="8%">
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
		<td width="3%" class="multiControl"><img style="cursor: hand; cursor: pointer"
			src="../../hisglobal/images/minus.gif" name="tariffMinus"
			onClick="removeTariffRow('#delIndex#');"></td>
	</tr>
</table>
</div>
<input type="hidden" name="rowIndex1" value="0"> 
<input type="hidden" name="rowLength1" value="0">


<div id="rowAdded2" style="display:none">

<table class="TABLEWIDTH" align="center" cellpadding="1px"
	cellspacing="1px">
	<tr>

		<td class="multiControl" width="20%"><input type="text"
			name="strOfflineTariffName" id="strOfflineTariffName#delIndex#"
			class="txtFldBig" onmouseover="Tip(this.value,SHADOW,true)" onmouseout="UnTip()"
			onblur="enableDisableMinusAndSaveButton(this,'#delIndex#');"
			onkeypress="return setSelectValue1('strOfflineTariffName#delIndex#','2',event,'setDropDownSelectedTariff^tariffFullNameDiv','#delIndex#');"
			onkeyup="return showMultiRowAdder(event),showTariffSearchPopup(event,'strOfflineTariffName#delIndex#','#delIndex#'),searchSel(event,'strOfflineTariffName#delIndex#','2',this);" />
		<input type='hidden' name='strOfflineTariffDetailsHidden'
			id='strOfflineTariffDetailsHidden#delIndex#' value=''>
			<input type='hidden' name='strCompChargeCheck' id='strCompChargeCheck#delIndex#' value='2'>
			</td>
		<td class="multiControl" width="8%">
		<div id="strOfflineTariffRateUnitDivId#delIndex#">/</div>
		<div id="strOfflineTempTariffRateUnitDivId#delIndex#" style="display: none;"><input type="text" name="strOfflineTempTariffRateUnit" id="strOfflineTempTariffRateUnit#delIndex#" class="txtFldMin" value="0"  maxlength="8" onkeypress="return validateData(event,7);" onkeyup="setRateValue('#delIndex#');" > / </div>
		<input type="hidden" name="strOfflineTariffRateUnit"
			id="strOfflineTariffRateUnit#delIndex#" class="txtFldNormal" /></td>
		<td class="multiControl" width="8%"><input type="text" 
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
		<td class="multiControl" width="8%">
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
		<td width="3%" class="multiControl"><img style="cursor: hand; cursor: pointer"
			src="../../hisglobal/images/minus.gif" name="tariffMinus"
			onClick="removeTariffRow('#delIndex#');"></td>
	</tr>
</table>
</div>
<input type="hidden" name="rowIndex2" value="0"> 
<input type="hidden" name="rowLength2" value="0">

<div id="rowAdded3" style="display:none">

<table width='500' cellpadding="1px" cellspacing="1px" >

		<tr> 
			<td class="multiControl" width="25%" ><input type="text" name="strFromDate" id='strFromDate#delIndex#' onkeypress="return validateDataWithSpecialChars(event,8 , '-');"  style="width:120px;height: 14px;"  maxlength="11"></td>
			<td class="multiControl" width="20%" >
			<input type="text" name="strFromHr" id='strFromHr#delIndex#' style="width:30px;height: 14px;" onkeypress="return validateData(event,5);"  maxlength="2" value="00"> :
			<input type="text" name="strFromMm" id='strFromMm#delIndex#' style="width:30px;height: 14px;" onkeypress="return validateData(event,5);"  maxlength="2" value="00">
			</td>
			<td class="multiControl"  width="25%" ><input type="text" name="strToDate" id='strToDate#delIndex#' onkeypress="return validateDataWithSpecialChars(event,8 , '-');" style="width:120px;height: 14px;" maxlength="11"></td>
			<td class="multiControl" width="20%" >
			<input type="text" name="strToHr" id='strToHr#delIndex#' style="width:30px;height: 14px;" onkeypress="return validateData(event,5);"  maxlength="2" value="00"> :
			<input type="text" name="strToMm" id='strToMm#delIndex#' style="width:30px;height: 14px;" onkeypress="return validateData(event,5);"  maxlength="2" value="00">
			</td>			 
			<td class="multiControl" width="5%" >
			<img style='cursor:pointer' src="../../hisglobal/images/minus.gif" onclick="deleteRow('#delIndex#' , '3' , '1');" >
			</td>		 
		</tr>
</table>
</div>
<input type="hidden" name="rowIndex3" value="0"> 
<input type="hidden" name="rowLength3" value="0">


<div id="rowAdded4" style="display:none">

<table width='500' cellpadding="1px" cellspacing="1px" >

		<tr> 
			<td class="multiControl" width="25%" ><input type="text" name="strPrivateFromDate" id='strPrivateFromDate#delIndex#' onkeypress="return validateData(event,8);"  style="width:120px;height: 14px;"  maxlength="10"></td>
			<td class="multiControl" width="20%" >
			<input type="text" name="strPrivateFromHr" id='strPrivateFromHr#delIndex#' style="width:30px;height: 14px;" onkeypress="return validateData(event,5);"  maxlength="2" value="00"> :
			<input type="text" name="strPrivateFromMm" id='strPrivateFromMm#delIndex#' style="width:30px;height: 14px;" onkeypress="return validateData(event,5);"  maxlength="2" value="00">
			</td>
			<td class="multiControl"  width="25%" ><input type="text" name="strPrivateToDate" id='strPrivateToDate#delIndex#' onkeypress="return validateData(event,8);" style="width:120px;height: 14px;" maxlength="10"></td>
			<td class="multiControl" width="20%" >
			<input type="text" name="strPrivateToHr" id='strPrivateToHr#delIndex#' style="width:30px;height: 14px;" onkeypress="return validateData(event,5);"  maxlength="2" value="00"> :
			<input type="text" name="strPrivateToMm" id='strPrivateToMm#delIndex#' style="width:30px;height: 14px;" onkeypress="return validateData(event,5);"  maxlength="2" value="00">
			</td>			 
			<td class="multiControl" width="5%" >
			<img style='cursor:pointer' src="../../hisglobal/images/minus.gif" onclick="deleteRow('#delIndex#' , '3' , '1');" >
			</td>		 
		</tr>
</table>
</div>
<input type="hidden" name="rowIndex4" value="0"> 
<input type="hidden" name="rowLength4" value="0">


</form>