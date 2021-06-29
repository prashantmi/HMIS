<form name="multirow">
<div id="rowAdded1" style="display: none">
<table width="97%%" align="center" cellspacing="1px">
	<tr>
		<td width="90%" class="formbg">
			<div align="center">
			<input type="text" name="ipadd1" value="" id="ipadd1#delIndex#" maxlength="3" onkeypress="return validateData(event,5);" class="textbox_small" onblur="return checkIpSize(this);">.
			<input type="text" name="ipadd2" value="" id="ipadd2#delIndex#" maxlength="3" onkeypress="return validateData(event,5)" class="textbox_small" onblur="return checkIpSize(this);">.
			<input type="text" name="ipadd3" value="" id="ipadd3#delIndex#" maxlength="3" onkeypress="return validateData(event,5)" class="textbox_small" onblur="return checkIpSize(this);">.
			<input type="text" name="ipadd4" value="" id="ipadd4#delIndex#" maxlength="3" onkeypress="return validateData(event,5)" class="textbox_small" onblur="return checkIpSize(this);">
			</div>
		</td>
		<td width="10%" class="formbg"><img
			src="../../images/minus.gif" onClick="deleteRow('#delIndex#','1','1');"></td>
	</tr>
</table>
</div>
<input type="hidden" name="rowIndex1" value="0"> <input
	type="hidden" name="rowLength1" value="0"></form>