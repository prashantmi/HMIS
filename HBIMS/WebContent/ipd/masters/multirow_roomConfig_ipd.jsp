<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<script language="Javascript" src="../../ipd/js/ipd.js"></script>
<form name="multirow">
<div id="rowAdded1" style="display: none">
<table width="300"  cellspacing="1px">
	<tr>
		<td width="98%" class="multiControl"><select 
			name="strPropertyId" id="strPropertyId#delIndex#" >
			<bean:write name="roomconfigBean" property="strPropertyComboValues" filter="false"/>
			</select>
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