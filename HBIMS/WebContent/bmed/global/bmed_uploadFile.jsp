<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/dateTag.tld" prefix="dateTag"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
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
		<his:ContentTag>
	<table class="TABLEWIDTH" align="center" border="0" cellpadding="1px" cellspacing="1px">
				    <tr>
				    <td align='center' style='width: 5%'><img style='cursor: pointer;' id='imgStockDetails' src='/HBIMS/hisglobal/images/minus.gif' onclick='showOrHideFileUpLoadDetails(this)' title='Hide'/>
				    </td><td colspan='5' style='width: 95%'><div id='Id1' style='text-align:left'>File Upload Details</div></td>
				    </tr>
					</table>
		</his:ContentTag>			
	

<div id='docUpLoadId' style="display: block;">



<div id='fileNoId' style="display: block;">
<table class="TABLEWIDTH" align="center" border="0" cellpadding="1px" cellspacing="1px">
	<tr>
		<td class="LABEL" width="50%"><font color="red">*</font>Ref. No./Ref Date</td>
		<td class="CONTROL" width="50%"><input type="text"
			class="txtFldMax" name="strDocRefNo"
			 onkeypress="return validateDataWithSpecialChars(event,9 ,'\')" maxlength="50">/<dateTag:date
						name="strDocRefDate"
						value=""></dateTag:date></td>

	</tr>
</table>
</div>
<table class="TABLEWIDTH" align="center" border="0" cellpadding="1px" cellspacing="1px">
	<tr>
		<td class="LABEL" width="50%">Attach Document</td>
		<td class="CONTROL" width="50%"><html:file property="strLocation" />

		</td>

	</tr>

</table>
</div>