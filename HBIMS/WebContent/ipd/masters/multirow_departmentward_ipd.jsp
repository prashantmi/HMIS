<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@taglib uri="/WEB-INF/dateTag.tld" prefix="dateTag"%>

<form name="multirow">
<div id="rowAdded1" style="display: none">
<table class="TABLEWIDTH" cellspacing="1px" cellpadding="1px" align="center">
	<tr>
		<td width="32%" class="multiControl"><select name="strDepartment"  class='comboMax'
			id="strDepartment#delIndex#" onChange="myFunc('1',this,'#delIndex#');">
			<bean:write name="deptWardBean" property="strDeptmentValues" filter="false"/>
		</select></td>
		<td width="30%" class="multiControl">
		<div id="unitId#delIndex#" >
		 <select name="strUnit" class='comboMax' id="strUnit#delIndex#">
		 <bean:write name="deptWardBean" property="strUnitValues" filter="false"/>
		</select> </div>
		</td>
		<td width="32%" class="multiControl">
		<dateTag:date name="strEffectiveFromDate" id="strEffectiveFromDate#delIndex#"
				 value="${deptWardBean.strEffTempDate}"/>
		</td>
		<td width="6%" class="multiControl"><img
			src="../../hisglobal/images/minus.gif"  style="cursor:hand;pointer:hand"
			onClick="deleteRow('#delIndex#','1','1');"></td>
	</tr>
</table>
</div>
<input type="hidden" name="rowIndex1" value="0"> <input
	type="hidden" name="rowLength1" value="0"></form>