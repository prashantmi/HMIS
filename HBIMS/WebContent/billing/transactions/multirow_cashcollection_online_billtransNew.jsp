<%@taglib uri="/WEB-INF/dateTag.tld" prefix="dateTag"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>

<form name="multirow">
<div id="rowAdded1" style="display: none">

<div class="row rowFlex reFlex">
		<div class="col-sm-2"><label><font color="red">*</font>Payment Mode</label></div>
		<div class="col-sm-2">
		<select tabindex="2" name="strOnlinePaymentMode"  class='browser-default custom-select'  id="strOnlinePaymentMode#delIndex#"
			onChange="displayPayDetails(this,'strOnlinePaymentDtls#delIndex#','onLinePayDtlEdit#delIndex#','strOnlineAmount#delIndex#','0');">   <!--  validatePayMode(); -->
				<bean:write name="cashCollectionOnlineTransBean" property="strPaymentModeContents" filter="false"/>
				</select> 
		</div>
		<div class="col-sm-2"><label>Payment Details</label></div>
		<div class="col-sm-2">
		<input type="text" tabindex="2" autocomplete='off' 
			name="strOnlinePaymentDtls" id="strOnlinePaymentDtls#delIndex#"
			  class="form-control" /> 
		</div>
		<div class="col-sm-1" style="padding-left: 0;" align="left"><button type="button" class="btn btn-info" name="strOnlinePaymentDtls" id="strOnlinePaymentDtls#delIndex#" onClick="displayPayWithDataDetails(this,'strOnlinePaymentDtls#delIndex#','strOnlinePaymentMode#delIndex#');" style="padding: 0.215rem 0.75rem">Edit</button></div>
		<div class="col-sm-2" align="right"><label><font color="red">*</font>Amount(<img src='/HBIMS/hisglobal/images/INR.png'>)</label></div>
		<div class="col-sm-1">
		<input type="text" tabindex="1" autocomplete='off' 
			name="strOnlineAmount" id="strOnlineAmount#delIndex#"
			class="form-control" onkeypress="return validateData(event,7);"
			onkeyup="setTotalPaymentAmt('1');" />
		</div>
		</div>
		
		

</div>
<input type="hidden" name="rowIndex1" value="0"> <input
	type="hidden" name="rowLength1" value="0">
