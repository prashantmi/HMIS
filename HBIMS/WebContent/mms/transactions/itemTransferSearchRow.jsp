<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<form name="multirow">
<logic:equal value="1" name="itemTransferTransBean" property="strCostRequired">
<div id="rowAdded1" style="display:none">
<table class="TABLEWIDTH" align="center" bgcolor='#CC9966' cellpadding="1px" cellspacing="1px" id="td#delIndex#" >
<tr>
<td class="multiControl" width="30%">
<input type="hidden" name="itemParamValue" id="itemParamValue#delIndex#">
<input type="hidden" name="itemCalcValue" id="itemCalcValue#delIndex#">
<input type="hidden" name="itemUserValue" id="itemUserValue#delIndex#">
<div id="itemParaId1#delIndex#"></div>
</td>

<td class="multiControl" width="14%">
<div id="itemParaId11#delIndex#">
</div>
</td>

<td class="multiControl" width="14%">
<div id="itemParaId4#delIndex#">
</div>
</td>

<td class="multiControl"  width="12%">
<input type="text" name="strTransferQty" id="strTransferQty#delIndex#" class="txtFldNormal" maxlength ="7" onkeypress="return validateData(event,5);" onkeyup="return issueQtyValidation('#delIndex#');">
</td>

<td class="multiControl"  width="20%">
<div id="itemParaId0#delIndex#" >
</div>
</td>

<td class="multiControl"  width="10%">
 <input type="text" class="txtFldMin" name="strTransferCostDivId" disabled id="strTransferCostDivId#delIndex#" value="0.00" >
 <input type="hidden" name="strTransferCost" id="strTransferCost#delIndex#" value="0.00" >
</td>
</tr>
</table>
</div>
</logic:equal>
<logic:equal value="0" name="itemTransferTransBean" property="strCostRequired">
<div id="rowAdded1" style="display:none">
<table class="TABLEWIDTH" align="center" cellpadding="1px" cellspacing="1px" id="td#delIndex#" >
<tr>
<td class="multiControl" width="25%">
<input type="hidden" name="itemParamValue" id="itemParamValue#delIndex#">
<input type="hidden" name="itemCalcValue" id="itemCalcValue#delIndex#">
<input type="hidden" name="itemUserValue" id="itemUserValue#delIndex#">
<div id="strTransferCostDivId#delIndex#" style="display: none">0.00</div>
 <input type="hidden" name="strTransferCost" id="strTransferCost#delIndex#" value="0.00" >
<div id="itemParaId1#delIndex#"></div>
</td>

<td class="multiControl" width="19%">
<div id="itemParaId11#delIndex#">
</div>
</td>

<td class="multiControl" width="19%">
<div id="itemParaId4#delIndex#">
</div>
</td>

<td class="multiControl"  width="17%">
<input type="text" name="strTransferQty" id="strTransferQty#delIndex#" class="txtFldMin" maxlength ="5" onkeypress="return validateData(event,5);" onkeyup="return validateQtyFor_decNcost(this,'3','#delIndex#','strTransferQty','strUnitName','strTransferCost','strTotalTransferCost');">
</td>

<td class="multiControl"  width="20%">
<div id="itemParaId0#delIndex#" >
</div>
</td>


</tr>
</table>
</div>
</logic:equal>
<input type="hidden" name="rowIndex1" value="0"> 
<input type="hidden" name="rowLength1" value="0">

</form>