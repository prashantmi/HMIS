
<%@taglib uri="/WEB-INF/dateTag.tld" prefix="date"%>
<form name="multirow">
<div id="rowAdded1" style="display: none">
<table class="TABLEWIDTH" align="center" cellspacing="1px">
	<tr>
		
	
		<td width="19%" class="multiControl"><select name="strPassType"
			class='comboNormal' id="strPassType#delIndex#">
			<option value="2">Paid Pass</option>
			<option value="1">Free Pass</option>
			
		</select></td>
		<td width="19%" class="multiControl"><input type='text' disabled='disabled' class='txtFldNormal'
			name="strIssueRenewDt" id="strIssueRenewDt#delIndex#" 
			value="${visitorpassTransBean.strCtDate}"></td>
		<td width="19%" class="multiControl"><date:date id="strValidFrom#delIndex#" dateFormate="dd-Mon-yyyy"
			name="strValidFrom" value="${visitorpassTransBean.strCtDate}"></date:date></td>
		<td width="19%" class="multiControl" ><input type='text' disabled='disabled' class='txtFldNormal'
			name="strValidUpto" id="strValidUpto#delIndex#" value="${visitorpassTransBean.strCtDate}"></td>
		
		<td width="19%" class="multiControl"><input type="text"
			class="txtFldNormal" name="strPaidAmount" id="strPaidAmount#delIndex#"
			maxlength="4" onkeypress="return validateData(event,5);" value="${visitorpassTransBean.strpaidAmount}"></td>
		<td width="5%" class="multiControl"><img
			src="../../hisglobal/images/minus.gif"
			onClick="deleteRow('#delIndex#','1','1');"></td>
	</tr>
</table>
</div>
<input type="hidden" name="rowIndex1" value="0"> <input
	type="hidden" name="rowLength1" value="0"></form>
