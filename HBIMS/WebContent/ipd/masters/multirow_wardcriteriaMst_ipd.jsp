
<jsp:useBean id="beans" class="ipd.masters.vo.WardCriteriaMstVO" scope="request" />
<%if (!(request.getAttribute("wardBean") == null))
				beans = (ipd.masters.vo.WardCriteriaMstVO) request.getAttribute("wardcriteria");

%>


<script language="Javascript" src="../../ipd/js/ipd.js"></script>



<form name="multirow">
<div id="rowAdded1" style="display: none">
<table CLASS="TABLEWIDTH" align="center" cellpadding="1px" cellspacing="1px">
	<tr>
		<td width="35%" class="multiControl"><select name="strGender" class='comboNormal'
			id="strGender#delIndex#">
			<%=beans.getComboGender() %>
		</select></td>
		<td width="15%" class="multiControl"><input onkeyup="lTrim(this);" onblur="Trim(this);" type="text" class ="txtFldSmall"
			name="strFromAge" id="strFromAge#delIndex#" maxlength="3" onkeypress ="return validateData(event,5);">
		
		</td>
		<td width="15%" class="multiControl"><input onkeyup="lTrim(this);" onblur="Trim(this);" type="text" class ="txtFldSmall"
			name="strToAge" id="strToAge#delIndex#" maxlength ="3" onkeypress ="return validateData(event,5);">
		</td>
		<td width="25%" class="multiControl" >
		<select name ="strFunit" class='comboNormal' id = "strFunit#delIndex#" onChange = "ageValidation(this,'#delIndex#');">
		<option value ="0">Select Unit</option>
		<option value ="1">Days</option>
		<option value ="2">Months</option>
		<option value ="3">Year</option>
		</select>	
		</td>
		<td width="10%" class="multiControl"><img
			src="../../hisglobal/images/minus.gif"  style="cursor:hand;pointer:hand"
			onClick="deleteRow('#delIndex#','1','1');"></td>
	</tr>
</table>
</div>
<input type="hidden" name="rowIndex1" value="0"> <input type="hidden"
	name="rowLength1" value="0"></form>
