



<script language="Javascript" src="../../ipd/js/ipd.js"></script>



<form name="multirow">
<div id="rowAdded1" style="display: none">
<table class="TABLEWIDTH"   cellspacing="1px">
	<tr>
		
		<td width="60%" class="multiControl"><input type="text" 
			name="strArticleName" id="strArticleName#delIndex#" maxlength="50" >
		
		</td>
		<td width="10%" class="multiControl"><input type="text" class ="txtFldSmall"
			name="strQuantity" id="strQuantity#delIndex#"  maxlength = "10" onkeypress ="return validateData(event,5);">
		</td>
		<td width="50%" class="multiControl"><input type="text" 
			name="strBelongingRemark" id="strBelongingRemark#delIndex#"  maxlength ="100" >
		</td>
		<td width="2%" class="multiControl"><img
			src="../../hisglobal/images/minus.gif"
			onClick="deleteRow('#delIndex#','1','0');"></td>
			
		<TD><input type="hidden" name="strMultiRowMode" id="strMultiRowMode#delIndex#" ></TD>
	</tr>
</table>
</div>
<input type="hidden" name="rowIndex1" value="0">
 <input type="hidden" name="rowLength1" value="0">
	<input type="hidden" id="savedRowId" value="0">
	</form>