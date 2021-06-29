<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>

<form name="multirow">
<div id="rowAdded1" style="display: none">
<table class="TABLEWIDTH" align="center">
	<tr>
		<td width="10%" class="multiControl"><input name="strCatProd" size="6" class="txtFldNormal"></td>
		<td width="10%" class="multiControl"><input name="strPack" size="3" class="txtFldMin"></td>
		
		<td width="10%" class="multiControl"><input name="strBatch" size="3" class="txtFldMin"></td>
		<td width="10%" class="multiControl"><input name="strExp" size="3" class="txtFldMin"></td>
		
		<td width="10%" class="multiControl"><input name="strQty" size="3" class="txtFldMin"></td>
		<td width="10%" class="multiControl"><input name="strQtyFinal" size="3" class="txtFldMin"></td>
		
		<td width="10%" class="multiControl"><input name="strPTW" size="3" class="txtFldMin"></td>
		<td width="10%" class="multiControl"><input name="strPIR" size="3" class="txtFldMin"></td>
		
		<td width="10%" class="multiControl"><input name="strMRP" size="3" class="txtFldMin"></td>
		<td width="10%" class="multiControl"><input name="strAMT" size="3" class="txtFldMin"></td>
		
		
		<td width="10%" class="multiControl"><img
			src="../../hisglobal/images/minus.gif"
			onClick="deleteRow('#delIndex#','1','0');"></td>
	</tr>
</table>
</div>
<input type="hidden" name="rowIndex1" value="0"> 
<input type="hidden" name="rowLength1" value="0"></form>
