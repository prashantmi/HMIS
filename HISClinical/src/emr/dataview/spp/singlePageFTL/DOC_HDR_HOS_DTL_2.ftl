<table width="100%" align="center">
	<tr>
		<td width="20%" align="left">
			<img src="/HISClinical/hisglobal/images/logo_${voEHR.voHospital.getHospitalCode()!"___"}.jpg" alt="Hospital Logo" height="80px" />
		</td>
		<td width="80%" align="center">
			<font size="4" ><b> ${((voEHR.voHospital.getHospitalName())!"___________________________")?upper_case}</b></font>
			<br>
			<font size="3" > ${voEHR.voHospital.getCity()!"____________"},${voEHR.voHospital.getState()!"____________"} </font>
			<br>
			<font size="3" >CONTACT:$nbsp;${voEHR.voHospital.getPhone()!"___________"} (${voEHR.voHospital.getEmail()!"_____________"})</font>
		</td>
	</tr>
</table>
