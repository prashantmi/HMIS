<%@taglib uri="/WEB-INF/dateTag.tld" prefix="dateTag"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>

<form name="multirow">
<div id="rowAdded1" style="display:none">
<table class="TABLEWIDTH" cellspacing="1px" cellpadding="1px" align="center">
	<tr>
		<td width="47%" class="multiControl"><select name="strDisease" class='comboMax'
			id="strDisease#delIndex#" >
		<bean:write name="deptWardDiseaseBean" property="strDiseaseValues" filter="false"/>   
		</select></td>
		<td width="47%" class="multiControl">
		<dateTag:date name="strEffectiveFromDate" id="strEffectiveFromDate#delIndex#"
				 value="${deptWardDiseaseBean.strEffTempDate}" />
		</td>
		<td width="6%" class="multiControl"><img  style="cursor:hand;pointer:hand"
			src="../../hisglobal/images/minus.gif"
			onClick="deleteRow('#delIndex#','1','1');"></td>
	</tr>
</table>
</div>
<input type="hidden" name="rowIndex1" value="0"> <input
	type="hidden" name="rowLength1" value="0"></form>