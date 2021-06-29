<table width=100%>
	<tr>
		<td width='28%'>
			<font size="1"><b>Admission No.:</b>&nbsp;${((voEHR.voPatientDtl.getPatAdmNo())!"____________")}</font>
		</td>
		<td width='44%'>
			<font size="1"><b>Department Unit:</b>&nbsp;${((voEHR.voPatientDtl.getDepartmentUnitName())!"__________") }</font>
		</td>
		<td width='28%'>
			<font size="1"><b>Admitted On:</b>&nbsp;${((voEHR.voPatientDtl.getadmDateTime())!"___________")}</font>
	</tr>
	<tr>
		<td width='28%'>
			<font size="1"><b>Ward No.:</b>&nbsp;${((voEHR.voPatientDtl.getWardName())!"__________")}</font>
		</td>
		<td width='44%'>
			<font size="1"><b>Consultant Name:</b>&nbsp; ${((voEHR.voPatientDtl.getConsultantName())!"___________")?upper_case }</font>
		</td>						 
		<td width='28%'>
			<font size="1"><b>Bed No.:</b>&nbsp;${((voEHR.voHospital.getBedName())!"__________")}</font>
		</td>
	</tr>
</table>