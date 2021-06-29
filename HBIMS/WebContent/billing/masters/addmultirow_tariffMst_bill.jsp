<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<form name="multirow">

<div id = "rowAdded1" style="display: none">
	<table class="TABLEWIDTH"  border="0" align="center">
		<tr>			
			<td class="multiControl" width="55%">
			<select name="department" id="department#delIndex#" >
		<bean:write name="localTariffBean" property="addDepartmentCombo" filter="false"/>
			</select>
			</td>
			<td class="multiControl" width="35%"><input class="txtFldSmall" type="text" name="share" value="" id="share#delIndex#" maxlength="4" onkeypress="return validateData(event,5);"></td>
						
			<td class="multiControl" width="10%"><img src="../../hisglobal/images/minus.gif" name="minus" id="minus#delIndex#" onClick="deleteRow('#delIndex#','1','0');"></td>
		</tr>
	</table>
</div>
<input type="hidden" name="rowIndex1" value="0">
<input type="hidden" name="rowLength1" value="0">

</form>
