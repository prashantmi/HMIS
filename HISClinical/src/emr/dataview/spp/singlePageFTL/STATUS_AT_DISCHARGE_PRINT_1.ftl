<table width=100%>
	<#if voEHR.hospitalCourseVO.getStatusAtDischarge()?has_content>
	<tr>
		<td>
			<font size="1"><b><u>Hospital Course</u>:&nbsp;</b></font>
			<font size="1">
					${((voEHR.hospitalCourseVO.getStatusAtDischarge())!"")} &nbsp;&nbsp;
			</font>
		</td>
	</tr>
	<#else>
	<tr>
		<td>
			<font size="1"><b><u>Hospital Course</u>:&nbsp;</b></font>
		</td>
	</tr>
	<tr>
		<td height='50px'>
		</td>
	</tr>
	</#if> 
</table>