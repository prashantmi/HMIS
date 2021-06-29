<table width=100%>
	<tr>
		<td width='28%'>
			<font size="1"><b>Admission No.:</b>&nbsp;${((voEHR.voPatientDtl.getPatAdmNo())!"")}</font>
		</td>
		<td width='44%'>
			<font size="1"><b>Department Unit:</b>&nbsp;${((voEHR.voPatientDtl.getDepartmentUnitName())!"") }</font>
		</td>
		<td width='28%'>
			<font size="1"><b>Admitted On:</b>&nbsp;${((voEHR.voPatientDtl.getAdmDateTime())!"__-__-____ __:__")}</font>
	</tr>
	<tr>
		<td width='28%'>
			<font size="1"><b>Ward:</b>&nbsp;${((voEHR.voPatientDtl.getWardName())!"-")}</font>
		</td>
		<td width='44%'>
			<font size="1"><b>Room/Bed:</b>&nbsp;${((voEHR.voPatientDtl.getIpdRoomName())!"-")}/${((voEHR.voPatientDtl.getBedName())!"-")}</font>
		</td>
		<td width='28%'>
			<font size="1"><b>Consultant:</b>&nbsp; ${((voEHR.voPatientDtl.getConsultantName())!"-")}</font>
		</td>						 
	</tr>
</table>
<table width=100%>
	<tr>
		<td width='28%'>
			<font size="1"><b>Discharge Type:</b>&nbsp;${((voEHR.followUpVO.getDischargeStatusName())!"")}</font>
		</td>
		<td width='44%'>
			<font size="1"><b>Printed On:</b>&nbsp;${((voEHR.getCurrDateTime())!"")}</font>
		</td>
		<td width='28%'>
			<font size="1"><b>Discharged On:</b>&nbsp;${((voEHR.voPatientDtl.getDischargeOn())!"__-___-____ __:__")}</font>
		</td>
	</tr>
</table>
<br>
<table border="1" width="100%">
	<tr>
		<td>
		</td>
	</tr>
</table>
                           