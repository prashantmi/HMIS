<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/dateTag.tld" prefix="dateTag"%>

<script type="text/javascript">
	function view1(obj1, obj2, obj3) 
	{
		document.getElementById(obj1).style.display = "none";
		document.getElementById(obj2).style.display = "block";
		document.getElementById(obj3).style.display = "block";
	}
	function view2(obj1, obj2, obj3) 
	{
		document.getElementById(obj1).style.display = "block";
		document.getElementById(obj2).style.display = "none";
		document.getElementById(obj3).style.display = "none";
	}
</script>

	<table class='TABLE_STYLE'>
				    <tr class='FOOTER_TR'>
				    <td align='center' style='width: 5%'><img style='cursor: pointer;' id='imgStockDetails' src='../../hisglobal/images/minus.gif' onclick='showOrHideFileUpLoadDetails(this)' title='Hide'/>
				    </td><td colspan='5' style='width: 95%'><div id='Id1' style='text-align:left'>File Up-Load Details</div></td>
				    </tr>
					</table>
	


<div id='docUpLoadId' style="display: block;">



<div id='fileNoId' style="display: block;">

<table class="TABLE_STYLE">
	<tr>
		<td class="LABEL_TD" width="50%"><font color="red">*</font>Ref. No./Ref Date</td>
		<td class="CONTROL_TD" width="50%"><input type="text"
			class="txtFldMax" name="strDocRefNo"
			onkeypress="return validateData(event,5);" maxlength="10">/<dateTag:date
						name="strDocRefDate"
						value=""></dateTag:date></td>

	</tr>
</table>

</div>

<table class="TABLE_STYLE">
	<tr>
		<td class="LABEL_TD" width="50%">Attach</td>
		<td class="CONTROL_TD" width="50%"><html:file property="strLocation" />

		</td>

	</tr>

</table>

</div>