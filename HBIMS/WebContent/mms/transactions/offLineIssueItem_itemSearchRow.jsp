<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<form name="multirow">
<logic:equal value="0" name="offlineIssueIndentBean" property="strBudgetFlg">
		<div id="rowAdded1" style="display:none">
		<table class="TABLEWIDTH" align="center" bgcolor='#6097BC' cellpadding="1px" cellspacing="1px" id="td#delIndex#" >
		<tr>
		<td class="multiPOControl" width="25.66%"  style="text-align:left;">
			<input type="hidden" name="itemParamValue" id="itemParamValue#delIndex#">
			<input type="hidden" name="itemCalcValue" id="itemCalcValue#delIndex#">
			<input type="hidden" name="itemUserValue" id="itemUserValue#delIndex#">
			<div id="itemParaId1#delIndex#"></div>
		</td>
		
		<td class="multiPOControl" width="18.66%" >
			<div id="itemParaId11#delIndex#"></div>
		</td>
		
		<td class="multiPOControl"  width="10%"  >
			<div id="itemParaId4#delIndex#"></div>
		</td>
		
		
		<td class="multiPOControl"  width="10%"  >
			<input type="text" name="strReqQty" id="strReqQty#delIndex#" class="txtFldNormal" maxlength ="8"  onkeyup="return reqQtyValidation('#delIndex#');" onkeypress="return validateData(event,7);" onblur="setDefaultValue(this);">
		</td>
		
		
		<td class="multiPOControl"  width="12%" >
			<input type="text" name="strIssueQty" id="strIssueQty#delIndex#"  class="txtFldMin" maxlength ="8"  onkeyup="return issueQtyValidation('#delIndex#');" onkeypress="return validateData(event,7);" onblur="setDefaultValue(this);" >
		</td>
		
		<td class="multiPOControl" width="15%"  >
				<div id="itemParaId0#delIndex#" ></div>
		</td>
		</tr>
		</table>
		</div>
</logic:equal>


<logic:equal value="1" name="offlineIssueIndentBean" property="strBudgetFlg">
		<div id="rowAdded1" style="display:none">
		<table class="TABLEWIDTH" align="center" bgcolor='#6097BC' cellpadding="1px" cellspacing="1px" id="td#delIndex#" >
		<tr>
		<td class="multiPOControl" width="27%" style="text-align:left;">
			<input type="hidden" name="itemParamValue" id="itemParamValue#delIndex#">
			<input type="hidden" name="itemCalcValue" id="itemCalcValue#delIndex#">
			<input type="hidden" name="itemUserValue" id="itemUserValue#delIndex#">
			<div id="itemParaId1#delIndex#"></div>
		</td>
		
		<td class="multiPOControl" width="13%" >
			<div id="itemParaId11#delIndex#"></div>
		</td>
		
		<td class="multiPOControl"  width="10%"  >
			<div id="itemParaId4#delIndex#"></div>
		</td>
		
		<td class="multiPOControl"  width="10%"  >
			<input type="text" name="strReqStoreAvlQty" id="strReqStoreAvlQty#delIndex#" class="txtFldMin" maxlength ="8"   onkeypress="return validateData(event,7);" onblur="setDefaultValue(this);">
		</td>
		
		<td class="multiPOControl"  width="10%"  >
			<input type="text" name="strReqQty" id="strReqQty#delIndex#" class="txtFldMin" maxlength ="8"  onkeyup="return reqQtyValidation('#delIndex#');" onkeypress="return validateData(event,5);" onblur="setDefaultValue(this);">
		</td>
		
		
		<td class="multiPOControl"  width="10%" >
			<input type="text" name="strIssueQty" id="strIssueQty#delIndex#"  class="txtFldMin" maxlength ="8"  onblur="return issueQtyValidation('#delIndex#');" onkeypress="return validateData(event,5);" onblur="setDefaultValue(this);" >
		</td>
		
		<td class="multiPOControl" width="10%"  >
				<div id="itemParaId0#delIndex#" ></div>
		</td>
		
		<td class="multiPOControl"  width="10%" >
			<input type="text" name="strCost"  disabled='disabled' id="strCost#delIndex#" class="txtFldNormal" maxlength ="10"  onkeypress="return validateData(event,7);" value="0.00" onblur="setDefaultValue(this);" >
		</td>
		</tr>
		</table>
		</div>
</logic:equal>


<input type="hidden" name="rowIndex1" value="0"> 
<input type="hidden" name="rowLength1" value="0">


</form>
