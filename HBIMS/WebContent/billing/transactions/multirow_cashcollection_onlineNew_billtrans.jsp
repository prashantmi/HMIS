<%@taglib uri="/WEB-INF/dateTag.tld" prefix="dateTag"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>

<form name="multirow">
<div id="rowAdded1" style="display: none">
	<div class='row'>
		<div class="col-sm-2" align="left">
		<div align='left'>
			<select tabindex="2" name="strOnlinePaymentMode"  class='form-control'  id="strOnlinePaymentMode#delIndex#"
				onChange="displayPayDetails(this,'strOnlinePaymentDtls#delIndex#','onLinePayDtlEdit#delIndex#','strOnlineAmount#delIndex#','0');">   <!--  validatePayMode(); -->
					<bean:write name="cashCollectionOnlineTransBean" property="strPaymentModeContents" filter="false"/>
			</select>   
		</div>				
		</div>
		<div class="col-sm-8" align="left">			
			<div class="input-group">
  				<div class="input-group-append">
					<button class="btn btn-outline-secondary btn-sm" type="button" id="onLinePayDtlEdit#delIndex#" value="Edit" disabled="disabled" onClick="displayPayWithDataDetails(this,'strOnlinePaymentDtls#delIndex#','strOnlinePaymentMode#delIndex#');">Edit</button>
				</div>
  				<input type="text" tabindex="2" autocomplete='off' name="strOnlinePaymentDtls" id="strOnlinePaymentDtls#delIndex#" class="form-control col-xs3" />				
			</div>
		</div>
		<div class="col-sm-2" align="left">
			<input type="text" tabindex="1" autocomplete='off' name="strOnlineAmount" id="strOnlineAmount#delIndex#" class="form-control col-xs3 priceDiv" onkeypress="return validateData(event,7);" onkeyup="setTotalPaymentAmt('1');" />
		</div>
	</div>
</div>
<input type="hidden" name="rowIndex1" value="0">
<input type="hidden" name="rowLength1" value="0">