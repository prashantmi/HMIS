<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>

<form name="multirow">
<div id="rowAdded1" style="display: none">
<table class="TABLEWIDTH" align="center" cellpadding="1px" cellspacing="0px">
	<tr>
		<td width="50%" class="multiControl"><select name="strDepartment" class='comboMax'
			id="strDepartment#delIndex#" onChange="myFunc('1',this,'#delIndex#');">
			<bean:write name="nurseCheckListBean" property="strDeptmentValues" filter="false"/>
		</select></td>
		<td width="44%" class="multiControl">
		<div id="unitId#delIndex#" >
		 <select name="strUnit" class='comboMax' id="strUnit#delIndex#">
		 	<bean:write name="nurseCheckListBean" property="strUnitValues" filter="false"/>
		</select> </div>
		<td width="6%" class="multiControl"><img
			src="../../hisglobal/images/minus.gif"  style="cursor:hand;pointer:hand"
			onClick="deleteRow('#delIndex#','1','1');"></td>
	</tr>
</table>
</div>
<input type="hidden" name="rowIndex1" value="0"> <input
	type="hidden" name="rowLength1" value="0"></form>