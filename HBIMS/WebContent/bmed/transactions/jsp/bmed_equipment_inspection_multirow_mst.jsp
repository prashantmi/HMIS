<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<form name="multirow">
<div id="rowAdded1" style="display: none">
<table class="TABLE_STYLE" >
	
	<tr>
		
		
		<td width="33%" class="CONTROL_TD" >
		<div align="right">
			<select name="strTestParaId" class="comboNormal" id="strTestParaId#delIndex#" onchange="getRelationshipCombo(this);">
				<bean:write name="equipmentInspectionTestDtlsFB" property="strTestParaNameCombo" filter="false"/>
			</select>
			
		</div>	
		</td>
		
		<td width="33%" class="CONTROL_TD">
		<div align="right">
			<input type="text" name="stroutPut" id="stroutPut#delIndex#"  class="txtFldMax" maxlength="100" onkeypress="return validateData(event,2);" >
		</div>
		</td>
				
			<td width="33%" class="CONTROL_TD">
			
			<img name=""
			onkeypress="onPressingEnter(this,event)"
			src="../../hisglobal/images/minus.gif" style="cursor: pointer;"
			title="Delete Row" onclick="deleteRow('#delIndex#',1,0);">
			</td>
		
			
			
		
	</tr>
</table>
</div>
<input type="hidden" name="rowIndex1" value="0"> 
<input type="hidden" name="rowLength1" value="0">


</form>


