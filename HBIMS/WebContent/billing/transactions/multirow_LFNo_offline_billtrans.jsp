<%@taglib uri="/WEB-INF/dateTag.tld" prefix="dateTag"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>

<form name="multirow" enctype="multipart/form-data" method="post">
 

<div id="rowAdded2" style="display: none">
<table class="TABLEWIDTH" align="center" cellpadding="0" cellspacing="1px">
	<tr>	
		<td class="multiControl" width="12%">		
			<input type="text" tabindex="1" name="strOfflineTariffName" id="strOfflineTariffName#delIndex#" autocomplete='off' class="txtFldBig" onmouseover="Tip(this.value,SHADOW,true)" onmouseout="UnTip()"
				 onkeypress="return setSelectValue1('strOfflineTariffName#delIndex#','1',event,'setDropDownSelectedTariff^tariffFullNameDiv','#delIndex#');"
				onkeyup="return showMultiRowAdder(event),showTariffSearchPopup(event,'strOfflineTariffName#delIndex#','#delIndex#'),getTariffDtls(this , event , '#delIndex#' );" />
			
			<input type='hidden' name='strOfflineTariffDetailsHidden'	id='strOfflineTariffDetailsHidden#delIndex#' value=''>
		</td>			
		<td class="multiControl" width="7%">
			<div id="strOfflineTariffRateUnitDivId#delIndex#" style="display: block;" >/</div>
			<div id="strOfflineTempTariffRateUnitDivId#delIndex#" style="display: none;">		
				<input type="text" tabindex="2" autocomplete='off'  name="strOfflineTempTariffRateUnit"  id="strOfflineTempTariffRateUnit#delIndex#" class="txtFldMin" value="0"  maxlength="8" onkeypress="return validateData(event,7);" onkeyup="setRateValue('#delIndex#');" > / 
			</div>	
			<input type="hidden" name="strOfflineTariffRateUnit" id="strOfflineTariffRateUnit#delIndex#" class="txtFldNormal" />
		</td>
		
		<td class="multiControl" width="6%">
			<input type="text" autocomplete='off'	name="strOfflineTariffQty" id="strOfflineTariffQty#delIndex#" tabindex="2" maxlength="3" onkeypress="return validateData(event,5);"
				class="txtFldMin" value="0" onkeypress="return validateData(event,5);" maxlength="8" onkeyup="calcOffLineTariffNetAmount('#delIndex#');"  />
		</td>		
		<td class="multiControl" width="8%">
		<div id='offlineTariffUnitDivId#delIndex#'>
			<select tabindex="2" name='strOfflineTariffUnit' id='strOfflineTariffUnit#delIndex#' class='comboMin'>
				<option value='0'>Select Value</option>
			</select>
		</div>
		</td>		
		<td style="display:none" class="multiControl" width="7%">
			<div id="strOfflineTariffServiceTaxDivId#delIndex#">0</div>
			<input type="hidden" name="strOfflineTariffServiceTax" id="strOfflineTariffServiceTax#delIndex#" class="txtFldMin" value="0" />
			<input type="hidden" name="strOfflineTariffServiceTaxAmtVal" id="strOfflineTariffServiceTaxAmtVal#delIndex#" class="txtFldMin" value="0" />
		</td>			
		<td class="multiControl" width="4%">
			<div id="strOfflineTariffDiscountDivId#delIndex#">0</div>
			<input type="hidden" name="strOfflineTariffDiscount" id="strOfflineTariffDiscount#delIndex#" class="txtFldMin" value='0' />
			<input type="hidden" name="strOfflineTariffDiscountAmtVal" id="strOfflineTariffDiscountAmtVal#delIndex#" class="txtFldMin" value='0' />
		</td>		
		<td class="multiControl" width="2%" >
			<img name="tariffDiscountConf" id="tariffDiscountConf#delIndex#" style="cursor: pointer" src="/AHIMS/hisglobal/images/plus.gif" onClick="getOffLineTariffDiscountDetails('#delIndex#',this);">
			<input type='hidden' name='strOfflineTariffDiscountConfigDetails' id='strOfflineTariffDiscountConfigDetails#delIndex#'>
		</td>
		
		<!-- added by vikrant -->	
		<td class="multiLabel" width="7%">
		<select class='comboMin' name='strCreditPaymentType' id="strCreditPaymentType#delIndex#" onchange="calcCreditAmount(this,'#delIndex#');"> 
			<option value='10'>Paid</option>
			<option value='11'>Credit</option>			
		</select>
		</td>
						
		 <td class="multiControl" width="10%">
		 	<input type="text" autocomplete='off' readonly='readonly' name="strCreditLetterNo" id="strCreditLetterNo#delIndex#"  maxlength="50" onkeypress='return validateData(event,3);' class="txtFldMin" value="" onkeyup="" />
		 </td>		
		<td class="multiControl" width="12%">
			<input size="9%" type="text" name="strCreditRefDate1" readonly='readonly' id="strCreditRefDate1#delIndex#" class='txtFldDate'/>
			 <img src="/HBIMS/hisglobal/images/imgDatepicker.png"  style="cursor: pointer; border: 1px solid red; width: 12px;" 
			 	  title="Date selector" onmouseover="datePickerMultiRow(event,'strCreditRefDate1',this);this.style.background='red';" 
			 	  onmouseout="this.style.background=''" id="strCreditRefDate2#delIndex#"  name="strCreditRefDate2"/>
		 </td>		 
  		 <td class="multiControl" width="14%">
  		 <div class="fileUpload"><span><img name="tariffMinus" style="cursor: pointer" src="/AHIMS/hisglobal/images/upload.png">Upload</span>
  		 	<input type="file" class="upload" name="uploadedFile2" accept="image/*,application/pdf" id="uploadedFile#delIndex#" onclick='uploadSet(this)'/></div>
  		 	<div id="uploadedFilePlace#delIndex#"></div>
  		  <!--  <input type="file" name="uploadedFile2" accept="image/*,application/pdf" id="uploadedFile#delIndex#"/>--> 
  		  <input type="hidden" name="MAX_FILE_SIZE" value="2000000" />   
  		 </td>  
		 
		 <td class="multiControl" width="8%" style="font-weight: bold"> 
			<input type="hidden" name="strOfflineTotalActualTariffAmtVal" id="strOfflineTotalActualTariffAmtVal#delIndex#" value="0.00" >
			<div id="strOfflineTariffNetAmountDivId#delIndex#">0.00</div>
				<input type="hidden" name="strOfflineTariffNetAmount" id="strOfflineTariffNetAmount#delIndex#" value="0.00" class="txtFldNormal" style="font-weight: bold" />
		</td>		
						
		<td width="3%" class="multiControl" style="display: none;">
			<!-- <img name="tariffMinus" style="cursor: pointer" src="/AHIMS/hisglobal/images/minus.gif" onClick="removeTariffRow('#delIndex#');"> --></td>
	</tr>
</table>
</div>
<input type="hidden" name="rowIndex2" value="0"> 
<input type="hidden" name="rowLength2" value="0">

<div id="rowAdded3" style="display: none">
<table class="TABLEWIDTH" align="center" cellpadding="1px"	cellspacing="1px">
	<tr>
		<td class="multiControl" width="20%"><select tabindex="2"
			name="strOfflinePaymentMode"  class='comboNormal'
			onChange="displayPayDetails(this,'strOfflinePaymentDtls#delIndex#','offLinePayDtlEdit#delIndex#','strOfflineAmount#delIndex#','1');"
			id="strOfflinePaymentMode#delIndex#" readonly/>
			<bean:write name="LFNoTransBean" property="strPaymentModeContents" filter="false"/>
		</select></td> 
		<td class="multiControl" width="40%"><input type="text" tabindex="1" autocomplete='off' 
			name="strOfflinePaymentDtls" id="strOfflinePaymentDtls#delIndex#"
			class="txtFldBig"   /> <input type="button"
			id="offLinePayDtlEdit#delIndex#" value="Edit" disabled="disabled"
			onClick="displayPayWithDataDetails(this,'strOfflinePaymentDtls#delIndex#','strOfflinePaymentMode#delIndex#');">
		</td>
		<td class="multiControl" width="20%"><input type="text" tabindex="1" autocomplete='off' 
			name="strOfflineAmount" id="strOfflineAmount#delIndex#"
			class="txtFldNormal" readonly/></td>

		<td width="3%" class="multiControl"><img style="cursor: hand; cursor: pointer"
			src="../../hisglobal/images/minus.gif"
			onClick="deleteRow('#delIndex#','3','1'),setTotalPaymentAmt('0');"></td>
	</tr>
</table>
</div>
<input type="hidden" name="rowIndex3" value="0"> <input
	type="hidden" name="rowLength3" value="0">
</form>
 