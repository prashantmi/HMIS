<table width=100%>
	<tr>
		<td>
			<font size="1"><b><u>Follow Up</u>:&nbsp;</b></font>			
		</td>
	</tr>
	<tr>
		<td height='10px'>			
		</td>
	</tr>
	<tr>
		<td width='25%'>
			<font size="1"><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Discharge Type:</b>&nbsp;${((voEHR.followUpVO.getDischargeStatusName())!"")}</font>
		</td>
	</tr>
	<tr>
		<td width='25%'>
			<font size="1"><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Next Visit Date:</b>&nbsp;${((voEHR.followUpVO.getNextVisitDate())!"")}</font>
		</td>
	</tr>
	<tr>
		<td width='25%'>
			<font size="1"><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Discharge Remarks:</b>&nbsp;${((voEHR.followUpVO.getDischargeRemarks())!"")}</font>
		</td>
	</tr>
	<tr>
		<td height='20px'>
		</td>
	</tr>
</table>
