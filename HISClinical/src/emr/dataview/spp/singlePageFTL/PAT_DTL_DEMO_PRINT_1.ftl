<table width='100%'>
	<tr>
		<td width='28%'>
			<font size="1"><b>CR No.:</b>&nbsp;${((voEHR.voPatientDtl.getPatCrNo())!"")}</font>
		</td>
        <td width='44%'>
			<font size="1"><b>Name:</b>&nbsp;${((voEHR.voPatientDtl.getPatName())!"")!""}</font> 
		</td>					
		<td width='28%'>
			<font size="1"><b>Age/Gender:</b>&nbsp;${((voEHR.voPatientDtl.getPatAge())!"__")}/${((voEHR.voPatientDtl.getPatGender())!"__")}</font> 
	    </td>
	</tr>
	<tr>
		<td width='28%'>
			<font size="1"><b>Patient Category:</b>&nbsp;${((voEHR.voPatientDtl.getPatCat())!"")}</font>
		</td>
		<td width='44%'>
			<font size="1"><b>Father/Spouse Name:</b>&nbsp;${((voEHR.voPatientDtl.getPatFatherName())!"")}/${((voEHR.voPatientDtl.getPatSpouceName())!"-")}</font>
		</td>
		<td width='28%'>
			<font size="1"><b>Occupation:</b>&nbsp;${((voEHR.voPatientDtl.getPatOccupation())!"")}</font>
		</td>
	</tr>
</table>
<table width=100%>
	<tr>
		<td width='72%'>
			<font size="1"><b>Address:</b>&nbsp;${((voEHR.voPatientDtl.getPatCompleteAddressForDischargeSummary())!"")}</font>
		</td>
		<td width='28%'>
			<font size="1"><b>Contact No.:</b>&nbsp;${((voEHR.voPatientDtl.getPatAddContactNo())!"")}</font>
		</td>
	</tr>
</table > 
           
                           
		