<%@taglib uri="/WEB-INF/dateTag.tld" prefix="dateTag"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>

<form name="multirow">
<div id="rowAdded1" style="display: none">
<table class="TABLEWIDTH" align="center" cellpadding="1px"
	cellspacing="1px">
	<tr>
		<td class="multiControl" width="20%"><select tabindex="2"
			name="strOnlinePaymentMode"  class='comboNormal'
			onChange="displayPayDetails(this,'strOnlinePaymentDtls#delIndex#','onLinePayDtlEdit#delIndex#','strOnlineAmount#delIndex#','0');"
			id="strOnlinePaymentMode#delIndex#">
			<bean:write name="cashCollectionTransBean" property="strPaymentModeContents" filter="false"/>
		</select></td>
		<td class="multiControl" width="40%"><input type="text" tabindex="2" autocomplete='off' 
			name="strOnlinePaymentDtls" id="strOnlinePaymentDtls#delIndex#"
			  class="txtFldBig" /> <input type="button"
			id="onLinePayDtlEdit#delIndex#" value="Edit" disabled="disabled"
			onClick="displayPayWithDataDetails(this,'strOnlinePaymentDtls#delIndex#','strOnlinePaymentMode#delIndex#');">
		</td>
		<td class="multiControl" width="20%"><input type="text" tabindex="1" autocomplete='off' 
			name="strOnlineAmount" id="strOnlineAmount#delIndex#"
			class="txtFldNormal" onkeypress="return validateData(event,7);"
			onkeyup="setTotalPaymentAmt('1');" /></td>

		<td width="3%" class="multiControl"><img style="cursor: hand; cursor: pointer"
			src="../../hisglobal/images/minus.gif"
			onClick="deleteRow('#delIndex#','1','1'),setTotalPaymentAmt('1');"></td>
	</tr>
</table>
</div>
<input type="hidden" name="rowIndex1" value="0"> <input
	type="hidden" name="rowLength1" value="0">

<div id="rowAdded2" style="display: none">
<table class="TABLEWIDTH" align="center" cellpadding="1px"
	cellspacing="1px">
	<tr>
		<td class="multiControl" width="20%"><input type="text" tabindex="1" autocomplete='off' 
			name="strOfflineTariffName" id="strOfflineTariffName#delIndex#"
			class="txtFldBig" onmouseover="Tip(this.value,SHADOW,true)" onmouseout="UnTip()"
			onkeypress="return setSelectValue1('strOfflineTariffName#delIndex#','1',event,'setDropDownSelectedTariff^tariffFullNameDiv','#delIndex#');"
			onkeyup="return showMultiRowAdder(event),showTariffSearchPopup(event,'strOfflineTariffName#delIndex#','#delIndex#'),searchSel(event,'strOfflineTariffName#delIndex#','1',this);" />
		<input type='hidden' name='strOfflineTariffDetailsHidden'
			id='strOfflineTariffDetailsHidden#delIndex#' value=''></td>
		<td class="multiControl" width="8%">
		<div id="strOfflineTariffRateUnitDivId#delIndex#" style="display: block;" >/</div>
		<div id="strOfflineTempTariffRateUnitDivId#delIndex#" style="display: none;">
		<input type="text" autocomplete='off'  tabindex="2" name="strOfflineTempTariffRateUnit" id="strOfflineTempTariffRateUnit#delIndex#" class="txtFldMin" value="0"  maxlength="8" onkeypress="return validateData(event,7);" onkeyup="setRateValue('#delIndex#');" > / </div>
		<input type="hidden" name="strOfflineTariffRateUnit"
			id="strOfflineTariffRateUnit#delIndex#" class="txtFldNormal" /></td>
		<td class="multiControl" width="7%"><input type="text"  autocomplete='off' 
			name="strOfflineTariffQty" id="strOfflineTariffQty#delIndex#" tabindex="2" maxlength="3" onkeypress="return validateData(event,5);"
			class="txtFldMin" value="0"
			onkeypress="return validateData(event,5);" maxlength="8"
			onkeyup="calcOffLineTariffNetAmount('#delIndex#');" /></td>
		<td class="multiControl" width="11%">
		<div id='offlineTariffUnitDivId#delIndex#'><select tabindex="2"
			name='strOfflineTariffUnit' id='strOfflineTariffUnit#delIndex#'
			class='comboMin'>
			<option value='0'>Select Value</option>
		</select></div>
		</td>
		<td class="multiControl" width="9%">
		<div id="strOfflineTariffServiceTaxDivId#delIndex#">0</div>
		<input type="hidden" name="strOfflineTariffServiceTax"
			id="strOfflineTariffServiceTax#delIndex#" class="txtFldMin" value="0" />
			<input type="hidden" name="strOfflineTariffServiceTaxAmtVal"
			id="strOfflineTariffServiceTaxAmtVal#delIndex#" class="txtFldMin" value="0" />
			</td>
		<td class="multiControl" width="10%">
		
		<table cellspacing="0" cellpadding="0" width="100%">
		<tr>
		<td align="center"><div id="strOfflineTariffDiscountDivId#delIndex#">0</div><input type="hidden" name="strOfflineTariffDiscount" id="strOfflineTariffDiscount#delIndex#" class="txtFldMin" value='0' />
		<input type="hidden" name="strOfflineTariffDiscountAmtVal" id="strOfflineTariffDiscountAmtVal#delIndex#" class="txtFldMin" value='0' />
		</td>
		<td align="right" width="5" valign="middle">
		<img name="tariffDiscountConf" id="tariffDiscountConf#delIndex#" style="cursor: pointer"
			src="../../hisglobal/images/plus.gif"
			onClick="getOffLineTariffDiscountDetails('#delIndex#',this);">
			<input type='hidden' name='strOfflineTariffDiscountConfigDetails' id='strOfflineTariffDiscountConfigDetails#delIndex#'>
		</td>
		</tr>
		</table>
		
		</td>
		<td class="multiControl" width="10%" style="font-weight: bold"> <input type="hidden" name="strOfflineTotalActualTariffAmtVal" id="strOfflineTotalActualTariffAmtVal#delIndex#" value="0.00" >
		<div id="strOfflineTariffNetAmountDivId#delIndex#">0.00</div>
		<input type="hidden" name="strOfflineTariffNetAmount"
			id="strOfflineTariffNetAmount#delIndex#" value="0.00"
			class="txtFldNormal" style="font-weight: bold" /></td>
		<td width="3%" class="multiControl"><img name="tariffMinus" style="cursor: pointer"
			src="../../hisglobal/images/minus.gif"
			onClick="removeTariffRow('#delIndex#');"></td>
	</tr>
</table>
</div>
<input type="hidden" name="rowIndex2" value="0"> <input
	type="hidden" name="rowLength2" value="0">

<div id="rowAdded3" style="display: none">
<table class="TABLEWIDTH" align="center" cellpadding="1px"
	cellspacing="1px">
	<tr>
		<td class="multiControl" width="20%"><select tabindex="2"
			name="strOfflinePaymentMode"  class='comboNormal'
			onChange="displayPayDetails(this,'strOfflinePaymentDtls#delIndex#','offLinePayDtlEdit#delIndex#','strOfflineAmount#delIndex#','1');"
			id="strOfflinePaymentMode#delIndex#">
			<bean:write name="cashCollectionTransBean" property="strPaymentModeContents" filter="false"/>
		</select></td> 
		<td class="multiControl" width="40%"><input type="text" tabindex="2" autocomplete='off' 
			name="strOfflinePaymentDtls" id="strOfflinePaymentDtls#delIndex#"
			class="txtFldBig"   /> <input type="button"
			id="offLinePayDtlEdit#delIndex#" value="Edit" disabled="disabled"
			onClick="displayPayWithDataDetails(this,'strOfflinePaymentDtls#delIndex#','strOfflinePaymentMode#delIndex#');">
		</td>
		<td class="multiControl" width="20%"><input type="text" tabindex="1" autocomplete='off' 
			name="strOfflineAmount" id="strOfflineAmount#delIndex#"
			class="txtFldNormal" onkeypress="return validateData(event,7);"
			onkeyup="setTotalPaymentAmt('0');" /></td>

		<td width="3%" class="multiControl"><img style="cursor: hand; cursor: pointer"
			src="../../hisglobal/images/minus.gif"
			onClick="deleteRow('#delIndex#','3','1'),setTotalPaymentAmt('0');"></td>
	</tr>
</table>
</div>
<input type="hidden" name="rowIndex3" value="0"> <input
	type="hidden" name="rowLength3" value="0">


<div id="rowAdded4" style="display: none">
<table class="TABLEWIDTH" align="center" cellpadding="1px"
	cellspacing="1px">
	<tr>
		<td class="multiControl" width="18%"><input type="text" autocomplete='off' 
			name="strOfflinePackageName" id="strOfflinePackageName#delIndex#"
			class="txtFldBig"
			onkeypress="return setSelectValue1('strOfflinePackageName#delIndex#','1',event,'','');"
			onkeyup="return searchSel(event,'strOfflinePackageName#delIndex#','1',this);" />
		</td>
		<td class="multiControl" width="8%"><input type="text" autocomplete='off' 
			name="strOfflinePackageRateUnit"
			id="strOfflinePackageRateUnit#delIndex#" class="txtFldMin"
			readonly="readonly" /></td>
		<td class="multiControl" width="8%"><input type="text" autocomplete='off' 
			name="strOfflinePackageReqQty" id="strOfflinePackageReqQty#delIndex#"
			class="txtFldMin" /></td>
		<td class="multiControl" width="8%"><input type="text" autocomplete='off' 
			name="strOfflinePackageUnit" id="strOfflinePackageUnit#delIndex#"
			class="txtFldMin" /></td>
		<td class="multiControl" width="8%"><input type="text" autocomplete='off' 
			name="strOfflinePackageServiceTax"
			id="strOfflinePackageServiceTax#delIndex#" class="txtFldMin"
			readonly="readonly" /></td>
		<td class="multiControl" width="10%"><input type="text" autocomplete='off' 
			name="strOfflinePackageDiscount"
			id="strOfflinePackageDiscount#delIndex#" class="txtFldMin"
			readonly="readonly" /> &nbsp;<img name="strOfflinePackageNetAmount"
			id="strOfflinePackageNetAmount#delIndex#" style="cursor: hand; cursor: pointer"
			src="../../hisglobal/images/plus.gif"
			onClick="getOffLinePkgDiscountDetails('#delIndex#',this);"></td>
		<td class="multiControl" width="10%"><input type="text" autocomplete='off' 
			value='0.00' name="strOfflinePackageNetAmount"
			id="strOfflinePackageNetAmount#delIndex#" class="txtFldNormal" /></td>
		<td width="8%" class="multiControl"><img style="cursor: hand; cursor: pointer"
			name="strOfflinePackageNetAmount"
			id="strOfflinePackageNetAmount#delIndex#"
			src="../../hisglobal/images/plus.gif"
			onClick="getOffLinePkgConfigDetails('#delIndex#');"><input
			type="hidden" name="flag" id="flag#delIndex#" value='0'></td>
		<td width="3%" class="multiControl"><img style="cursor: hand; cursor: pointer"
			src="../../hisglobal/images/minus.gif"
			onClick="deleteRow('#delIndex#','4','1');"></td>
	</tr>
	<tr>
		<td colspan='9'>
		<div id="pkgConfigId#delIndex#" style="display: none"></div>
		</td>
	</tr>
</table>
</div>
<input type="hidden" name="rowIndex4" value="0"> <input
	type="hidden" name="rowLength4" value="0"></form>