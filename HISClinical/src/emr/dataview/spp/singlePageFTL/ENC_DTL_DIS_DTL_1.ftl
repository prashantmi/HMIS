<table width=100%>
	<tr>
		<td width='28%'>
			<font size="1"><b>Discharge Type:</b>&nbsp;${((voEHR.voPatientDtl.getDischargeType())!"")}</font>
		</td>
		<td width='44%'>
			<font size="1"><b>Printed on:</b>&nbsp;<#assign sysDate= .now> ${(sysDate)}</font>
		</td>
		<td width='28%'>
			<font size="1"><b>Discharge on:-</b>&nbsp;${((voEHR.voPatientDtl.getDischargeOn())!"__-__-____ __:__")}</font>
		</td>
	</tr>
</table>
