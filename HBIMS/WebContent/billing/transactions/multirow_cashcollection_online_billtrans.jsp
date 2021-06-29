<%@taglib uri="/WEB-INF/dateTag.tld" prefix="dateTag"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>

<form name="multirow">
<div id="rowAdded1" style="display: none">
<table class="TABLEWIDTH" align="center" cellpadding="1px"
	cellspacing="1px">
	<tr>
		<%-- <td class="multiControl" width="8%">			
			<div align='left'><img id="imgPatientWallet#delIndex#"  name="imgPatientWallet" onclick="popup('payDtlWalletMenu' , '250','250');"  style="cursor: hand; cursor: pointer;display: none;" class="icon-imageVerySmallIcon" src="../../hisglobal/images/patientwallet.png" title='Patient Wallet'></div>
		</td>--%>
		<td class="multiControl" width="28%">
			<div align='left'>
				<select tabindex="2" name="strOnlinePaymentMode"  class='comboNormal'  id="strOnlinePaymentMode#delIndex#"
			onChange="displayPayDetails(this,'strOnlinePaymentDtls#delIndex#','onLinePayDtlEdit#delIndex#','strOnlineAmount#delIndex#','0');">   <!--  validatePayMode(); -->
				<bean:write name="cashCollectionOnlineTransBean" property="strPaymentModeContents" filter="false"/>
				</select>   
			</div>				
		</td>		
		<td class="multiControl" width="32%"><input type="text" tabindex="2" autocomplete='off' 
			name="strOnlinePaymentDtls" id="strOnlinePaymentDtls#delIndex#"
			  class="txtFldBig" /> <input type="button"
			id="onLinePayDtlEdit#delIndex#" value="Edit" disabled="disabled"
			onClick="displayPayWithDataDetails(this,'strOnlinePaymentDtls#delIndex#','strOnlinePaymentMode#delIndex#');">
		</td>
		<td class="multiControl" width="20%"><input type="text" tabindex="1" autocomplete='off' 
			name="strOnlineAmount" id="strOnlineAmount#delIndex#"
			class="txtFldNormal" onkeypress="return validateData(event,7);"
			onkeyup="setTotalPaymentAmt('1');" /></td>

		<td width="3%" class="multiControl">
		<!-- 	<img style="cursor: hand; cursor: pointer" src="../../hisglobal/images/minus.gif" onClick="deleteRow('#delIndex#','1','1'),setTotalPaymentAmt('1');"> -->
		</td>
	</tr>
</table>
</div>
<input type="hidden" name="rowIndex1" value="0"> <input
	type="hidden" name="rowLength1" value="0">
