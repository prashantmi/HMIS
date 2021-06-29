<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<form name="multirow">
<div id="rowAdded1" style="display: none">
<table width="100%">
	<tr>
		<td width="9%" class="multiControl">
		<bean:write name="ipdBillManagementTransBean" property="strCtDate"/>
		</td>
		<td width="12%" class="multiControl">
		<input type="text" name="strTariffName" size="13" id="strTariffName#delIndex#" 
  onkeypress="return setSelectValue1('strTariffName#delIndex#','1',event,'getTariffRate','#delIndex#');"  
  onkeyup="return searchSel(event,'strTariffName#delIndex#','1',this)">
		</td>
		<td width="10.5%" class="multiControl">
		<input name="strTariffRate" size="6" id="strTariffRate#delIndex#" class="txtFldNormal" readonly="readonly">
		</td>
		<td width="9.5%" class="multiControl">
		<input name="strQuantity" size="7" id="strQuantity#delIndex#" class="txtFldNormal" onKeyUp="enterQty(this,document.getElementById('strTariffRate#delIndex#'),document.getElementById('strCost#delIndex#'));" onkeypress="return validateData(event,7);">
		</td>
		<td width="10%" class="multiControl">
		<input name="strCost" class="txtFldNormal" size="12" id="strCost#delIndex#" readonly="readonly">
		</td>
		<td width="7.0%" class="multiControl"><img
			src="../../hisglobal/images/minus.gif"
			onClick="deleteRow('#delIndex#','1','0');"></td>
	</tr>
</table>
</div>
<input type="hidden" name="rowIndex1" value="0"> 
<input type="hidden" name="rowLength1" value="0"></form>
