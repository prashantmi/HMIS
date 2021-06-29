<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@taglib uri="/WEB-INF/dateTag.tld" prefix="dateTag"%>

<form name="multirow">
<div id="rowAdded1" style="display: none">
<table class="TABLEWIDTH" cellspacing="1px" cellpadding="1px" align="center">
	<tr>
		<td width="94%" class="multiControl"><select name="strGenWardApprover" class='comboBig' onChange='showName(this)'
			id="strGenWardApprover#delIndex#" >
			<bean:write name="inPatientConfigBean" property="strGenWardApproverValues" filter="false"/>
		</select></td>
		
		<td width="6%" class="multiControl"><img
			src="../../hisglobal/images/minus.gif"  style="cursor:hand;pointer:hand"
			onClick="deleteRow('#delIndex#','1','1');"></td>
	</tr>
</table>
</div>
<input type="hidden" name="rowIndex1" value="0"> <input
	type="hidden" name="rowLength1" value="0">
	
	<div id="rowAdded2" style="display: none">
<table class="TABLEWIDTH" cellspacing="1px" cellpadding="1px" align="center">
	<tr>
		<td width="94%" class="multiControl"><select name="strPrivateWardApprover" class='comboBig' onChange='showName(this)'
			id="strPrivateWardApprover#delIndex#" >
			<bean:write name="inPatientConfigBean" property="strPrivateWardApproverValues" filter="false"/>
		</select></td>
		
		<td width="6%" class="multiControl"><img
			src="../../hisglobal/images/minus.gif"  style="cursor:hand;pointer:hand"
			onClick="deleteRow('#delIndex#','2','1');"></td>
	</tr>
</table>
</div>
<input type="hidden" name="rowIndex2" value="0"> <input
	type="hidden" name="rowLength2" value="0">
	
	
	</form>