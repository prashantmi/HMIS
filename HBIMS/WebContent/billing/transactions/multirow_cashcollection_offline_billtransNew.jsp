<%@taglib uri="/WEB-INF/dateTag.tld" prefix="dateTag"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<script language="Javascript" src="../../hisglobal/js/validation.js"></script>
<script language="JavaScript" src="../../masterutil/js/master.js"></script>
<script language="JavaScript" src="../../transUtil/js/master.js"></script>
<script language="Javascript" src="../../hisglobal/js/util.js"></script>
<form name="multirow" enctype="multipart/form-data" method="post">
 
<div id="rowAdded2" style="display: none">
<table class="table">
	<tr role="row">	
		<td>
		<div id="strOfflineTariffNameDivId#delIndex#" style="display: block;" >
			<input type="text" tabindex="1" autocomplete='off' name="strOfflineTariffName" id="strOfflineTariffName#delIndex#" autocomplete='off' class="form-control" onmouseover="Tip(this.value,SHADOW,true)" onmouseout="UnTip()"
				 onkeypress="return setSelectValue1('strOfflineTariffName#delIndex#','1',event,'setDropDownSelectedTariff^tariffFullNameDiv','#delIndex#');"
				onkeyup="return showMultiRowAdder(event),showTariffSearchPopup(event,'strOfflineTariffName#delIndex#','#delIndex#'),getTariffDtls(this , event , '#delIndex#' );" />
		</div>	
		<!--  <div id="strOfflineTempTariffNameDivId#delIndex#" style="display: none;">
		    <input type="text" tabindex="1" autocomplete='off' name="strOfflineTempTariffName" id="strOfflineTempTariffName#delIndex#" autocomplete='off' class="txtFldTrfName" onmouseover="Tip(this.value,SHADOW,true)" onmouseout="UnTip()" />
		</div>-->
			<input type='hidden' name='strOfflineTariffDetailsHidden'	id='strOfflineTariffDetailsHidden#delIndex#' value=''>
		</td>			
		<td>
			<div id="strOfflineTariffRateUnitDivId#delIndex#" style="display: block;" >/</div>
			<div id="strOfflineTempTariffRateUnitDivId#delIndex#" style="display: none;">		
				<input type="text" autocomplete='off' tabindex="2" autocomplete='off'  name="strOfflineTempTariffRateUnit"  id="strOfflineTempTariffRateUnit#delIndex#" class="form-control" value="0"  maxlength="8" onkeypress="return validateData(event,7);" onkeyup="setRateValue('#delIndex#');" > / 
			</div>	
			<input type="hidden" name="strOfflineTariffRateUnit" id="strOfflineTariffRateUnit#delIndex#" class="txtFldNormal" />
		</td>
		
		<td>
			<input type="text" autocomplete='off' name="strOfflineTariffQty" id="strOfflineTariffQty#delIndex#" tabindex="1" maxlength="3" onkeypress="return validateData(event,5);"
				class="form-control" value="0" onkeypress="return validateData(event,5);" maxlength="8" onkeyup="calcOffLineTariffNetAmount('#delIndex#');"  />
		</td>		
		<td style='display:none;'>
		<div id='offlineTariffUnitDivId#delIndex#'>
			<select tabindex="2" name='strOfflineTariffUnit' id='strOfflineTariffUnit#delIndex#' class='browser-default custom-select'>
				<option value='0'>Select Value</option>				
			</select>
		</div>
		</td>		
		<td style="display:none">
			<div id="strOfflineTariffServiceTaxDivId#delIndex#">0</div>
			<input type="hidden" name="strOfflineTariffServiceTax" id="strOfflineTariffServiceTax#delIndex#" class="form-control" value="0" />
			<input type="hidden" name="strOfflineTariffServiceTaxAmtVal" id="strOfflineTariffServiceTaxAmtVal#delIndex#" class="form-control" value="0" />
		</td>			
		<td>
			<div id="strOfflineTariffDiscountDivId#delIndex#">0</div>
			<input type="hidden" name="strOfflineTariffDiscount" id="strOfflineTariffDiscount#delIndex#" class="form-control" value='0' />
			<input type="hidden" name="strOfflineTariffDiscountAmtVal" id="strOfflineTariffDiscountAmtVal#delIndex#" class="form-control" value='0' />
		</td>		
		<td>
			<img name="tariffDiscountConf" data-toggle="modal" data-target="#payModeModal" id="tariffDiscountConf#delIndex#" style="cursor: pointer" src="/HBIMS/hisglobal/images/plus.gif" onClick="getOffLineTariffDiscountDetails('#delIndex#',this);">
			<input type='hidden' name='strOfflineTariffDiscountConfigDetails' id='strOfflineTariffDiscountConfigDetails#delIndex#'>
		</td>
		
		<!-- added by vikrant -->	
		<td>
		<select class='browser-default custom-select' name='strCreditPaymentType' tabindex="1" id="strCreditPaymentType#delIndex#" onchange="calcCreditAmount(this,'#delIndex#');"> 
			<option value='10'>Paid</option>
			<option value='11'>Credit</option>
			<option value='12'>Paid(Urgent)</option>
			<option value='13'>Credit(Urgent)</option>			
		</select>
		</td>						
		<td id="TDCreditLetter#delIndex#">
		 	<select class='browser-default custom-select' name='strCreditLetterNo' id="strCreditLetterNo#delIndex#" tabindex="1" onchange="checkPaymentType(this,'#delIndex#');" tabindex="1"> 
				<bean:write name="cashCollectionOfflineTransBean" property="strCreditLetterNoList" filter="false" />
			</select>		
		</td>		
		<!--<td class="multiControl" width="12%">
			<input size="9%" type="text" name="strCreditRefDate1" readonly='readonly' id="strCreditRefDate1#delIndex#" class='txtFldDate'/>			
			 <img src="/HBIMS/hisglobal/images/imgDatepicker.png"  style="cursor: pointer; border: 1px solid red; width: 12px;" 
			 	  title="Date selector" onmouseover="datePickerMultiRow(event,'strCreditRefDate1',this);this.style.background='red';" 
			 	  onmouseout="this.style.background=''" id="strCreditRefDate2#delIndex#"  name="strCreditRefDate2"/>
		 </td> -->		 
  		 <td>
  		 <div class="fileUpload"><span><img name="tariffMinus" style="cursor: pointer" src="/HBIMS/hisglobal/images/upload.png">Upload</span>
  		 	<input type="file" class="upload" name="uploadedFile2" accept="image/*,application/pdf" id="uploadedFile#delIndex#" onclick='uploadSet(this)'/></div>
  		 	<div id="uploadedFilePlace#delIndex#"></div>
  		  <!--  <input type="file" name="uploadedFile2" accept="image/*,application/pdf" id="uploadedFile#delIndex#"/>--> 
  		  <input type="hidden" name="MAX_FILE_SIZE" value="2000000" />   
  		 </td>  
		 
		 <td style="font-weight: bold"> 
			<input type="hidden" name="strOfflineTotalActualTariffAmtVal" id="strOfflineTotalActualTariffAmtVal#delIndex#" value="0.00" >
			<div id="strOfflineTariffNetAmountDivId#delIndex#">0.00</div>
				<input type="hidden" name="strOfflineTariffNetAmount" id="strOfflineTariffNetAmount#delIndex#" value="0.00"/>
				<input type="hidden" name="strOfflineAmtPaidByClient" id="strOfflineAmtPaidByClient#delIndex#" value="0.00"/>
		</td>		
						
		<td>
			<img name="tariffMinus" style="cursor: pointer" src="/HBIMS/hisglobal/images/minus.gif" onClick="removeTariffRow('#delIndex#');"></td>
	</tr>
</table>
</div>
<input type="hidden" name="rowIndex2" value="0"> 
<input type="hidden" name="rowLength2" value="0">

<div id="rowAdded3" style="display: none">

<div class="row rowFlex reFlex">
	<div class="col-sm-2"><label>Payment Mode<font color="red">*</font></label></div>
	<div class="col-sm-2"><select tabindex="2"
			name="strOfflinePaymentMode"  class='browser-default custom-select'
			onChange="displayPayDetails(this,'strOfflinePaymentDtls#delIndex#','offLinePayDtlEdit#delIndex#','strOfflineAmount#delIndex#','1'); "   
			id="strOfflinePaymentMode#delIndex#">    <!-- validatePayMode(); -->
			<bean:write name="cashCollectionOfflineTransBean" property="strPaymentModeContents" filter="false"/>
		</select></div>
	<div class="col-sm-2"><label>Payment Details<font color="red">*</font></label></div>
	<div class="col-sm-2"><input type="text" tabindex="2" autocomplete='off' 
			name="strOfflinePaymentDtls" id="strOfflinePaymentDtls#delIndex#"
			class="form-control"   /> <!-- <input type="button"
			id="offLinePayDtlEdit#delIndex#" value="Edit" disabled="disabled"
			onClick="displayPayWithDataDetails(this,'strOfflinePaymentDtls#delIndex#','strOfflinePaymentMode#delIndex#');"> --></div>
	<div class="col-sm-2"><label>Amount(<img src='/HBIMS/hisglobal/images/INR.png'>)<font color="red">*</font></label></div>
	<div class="col-sm-2"><input type="text" tabindex="1" autocomplete='off' 
			name="strOfflineAmount" id="strOfflineAmount#delIndex#"
			class="form-control" onkeypress="return validateData(event,7);"
			onkeyup="setTotalPaymentAmt('0');" /></div>
	</div>

<%-- <table class="TABLEWIDTH" align="center" cellpadding="1px"	cellspacing="1px">
	<tr>
	    <td class="multiControl" width="8%">			
			<div align='left'><img id="imgPatientWallet#delIndex#"  name="imgPatientWallet" onclick="popup('payDtlWalletMenu' , '250','250');"  style="cursor: hand; cursor: pointer;display: none;" class="icon-imageVerySmallIcon" src="../../hisglobal/images/patientwallet.png" title='Patient Wallet'></div>
		</td>
		<td class="multiControl" width="28%"><select tabindex="2"
			name="strOfflinePaymentMode"  class='comboNormal'
			onChange="displayPayDetails(this,'strOfflinePaymentDtls#delIndex#','offLinePayDtlEdit#delIndex#','strOfflineAmount#delIndex#','1'); "   
			id="strOfflinePaymentMode#delIndex#">    <!-- validatePayMode(); -->
			<bean:write name="cashCollectionOfflineTransBean" property="strPaymentModeContents" filter="false"/>
		</select></td> 
		<td class="multiControl" width="32%"><input type="text" tabindex="2" autocomplete='off' 
			name="strOfflinePaymentDtls" id="strOfflinePaymentDtls#delIndex#"
			class="txtFldBig"   /> <input type="button"
			id="offLinePayDtlEdit#delIndex#" value="Edit" disabled="disabled"
			onClick="displayPayWithDataDetails(this,'strOfflinePaymentDtls#delIndex#','strOfflinePaymentMode#delIndex#');">
		</td>
		<td class="multiControl" width="20%"><input type="text" tabindex="1" autocomplete='off' 
			name="strOfflineAmount" id="strOfflineAmount#delIndex#"
			class="txtFldNormal" onkeypress="return validateData(event,7);"
			onkeyup="setTotalPaymentAmt('0');" /></td>

		<td width="3%" class="multiControl"><!-- <img style="cursor: hand; cursor: pointer"
			src="../../hisglobal/images/minus.gif"
			onClick="deleteRow('#delIndex#','3','1'),setTotalPaymentAmt('0');"> --></td>  <!-- commented by manisha -->
	</tr>
</table> --%>
</div>
<input type="hidden" name="rowIndex3" value="0"> <input
	type="hidden" name="rowLength3" value="0">
</form>
 