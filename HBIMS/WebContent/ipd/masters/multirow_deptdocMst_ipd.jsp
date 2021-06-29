<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>

<form name="multirow">
<div id="rowAdded1" style="display: none">
<table class="TABLEWIDTH" align="center" cellspacing="1px">
	<tr>
		<td width="30%" class="multiControl"><select name="strComponentCode"  class='comboNormal'
			id="strComponentCode#delIndex#">
			<bean:write name="deptdocBean" property="componentAdd" filter="false"/>
		</select></td>
		<td width="30%" class="multiControl">
		<input type ="text" onkeyup="lTrim(this);" onblur="Trim(this);" name="strComponentFileName"  class="txtFldNormal"
			 id="strComponentFileName#delIndex#" maxlength="40" onkeypress="return validateData(event,9);" ></td>
		<td width="30%" class="multiControl">
		<input type ="text" onkeyup="lTrim(this);" onblur="Trim(this);" name="strComponentRemark"  class="txtFldNormal"
			 id="strComponentRemark#delIndex#" onkeypress="return validateData(event,9);"></td>
		
		<td width="10%" class="multiControl"><img
			src="../../hisglobal/images/minus.gif"
			onClick="deleteRow('#delIndex#','1','1');"></td>
	</tr>
</table>
</div>
<input type="hidden" name="rowIndex1" value="0"> 
<input type="hidden" name="rowLength1" value="0"></form>