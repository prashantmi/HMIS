
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>

<form name="multirow">
<div id="rowAdded1" style="display: none">
<table class="TABLEWIDTH" align="center" cellpadding="1px" cellspacing="1px">
	<tr>
		<td width="45%" class="multiControl"><select name="strBedName" class='comboNormal'
			id="strBedName#delIndex#">
			<bean:write name="wardroombedBean" property="strBedNameValues" filter="false"/>
		</select>
		</td>
		<td width="45%" class="multiControl"><select name="strBedType" class='comboNormal' id="strBedType#delIndex#">
		   <bean:write name="wardroombedBean" property="strBedTypeVlaues" filter="false"/>
		</select>
		</td>
		<td width="10%" class="multiControl"><img
			src="../../hisglobal/images/minus.gif"  style="cursor:hand;pointer:hand"
			onClick="deleteRow('#delIndex#','1','0');"></td>
	</tr>
</table>
</div>
<input type="hidden" name="rowIndex1" value="0"> 
<input type="hidden" name="rowLength1" value="0"></form>
