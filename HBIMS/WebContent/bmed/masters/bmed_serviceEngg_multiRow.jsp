<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<form name="multirow">
<div id="rowAdded1" style="display: none">
<table width="100%" bgcolor='#CC9966' align="center" cellpadding="1px"
	cellspacing="1px">
	<tr>



		<td width="15%" class="multiLabel"><img name=""
			onkeypress="onPressingEnter(this,event)"
			src="/HBIMS/hisglobal/images/minus.gif" style="cursor: pointer;"
			title="Delete Row" onclick="deleteRow('#delIndex#',1,0);"></td>

		<td class="multiControl" width="85%"><select
			name="strServiceEnggNameId" id="strServiceEnggName#delIndex#"
			class="comboMax" onChange="chequeDuplicacy(this,'#delIndex#');">
			<bean:write name="serviceEnggMstBean"
				property="strServiceEnggNameCmb" filter="false" />
		</select></td>



	</tr>
</table>
</div>
<input type="hidden" name="rowIndex1" value="0"> <input
	type="hidden" name="rowLength1" value="0"></form>