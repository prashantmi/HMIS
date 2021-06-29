<table width=100%>
	<#if voEHR.cheifComplaintsVO.getEhrVisitReason()?has_content>
	<tr>
		<td>
			<font size="1"><b><u>Cheif Complaints</u>:&nbsp;</b></font>
			<font size="1">
					${((voEHR.cheifComplaintsVO.getEhrVisitReason())!"")} &nbsp;&nbsp;
			</font>
		</td>
	</tr>
	<#else>
	<tr>
		<td>
			<font size="1"><b><u>Cheif Complaints</u>:&nbsp;</b></font>
		</td>
	</tr>
	<tr>
		<td height='10px'>
		</td>
	</tr>
	</#if> 
</table>