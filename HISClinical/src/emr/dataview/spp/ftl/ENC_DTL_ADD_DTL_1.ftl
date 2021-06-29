<table width=100%>
  <tr>
   <td style="width:30%">
    <font size="1"><b>DEPARTMENT UNIT :-</b>&nbsp;&nbsp;${((voEHR.voPatientDtl.departmentUnitName)!"Department unit") }
	</font>
   </td>
   <td style="width:40%">
    <font size="1"><b>WARD No. :-</b>&nbsp;&nbsp;${((voEHR.voPatientDtl.wardName)!"ward No.")}
	</font>
   </td>
  <td style="width:30%">
    <font size="1"><b>BED No. :-</b>&nbsp;&nbsp;${((voEHR.voHospital. bedName)!"bed No.")}
	</font>
  </td>
 </tr>
 <tr>
  <td style="width:30%">
     <font size="1"><b>ADMISSION NO. :-</b>&nbsp;&nbsp;${((voEHR.voPatientDtl.patAdmNo)!"admission No.")}
	 </font>					
  </td>
 </tr>
 <tr>						 
  <td style="width:40%">
    <font size="1"><b>DATE OF ADMISSION :-</b>&nbsp;&nbsp;${((voEHR.voPatientDtl.admDateTime)!"dd/mm/yyyy")}
	</font>
  </td>
  <td style="width:30%">
    <font size="1"><b>CONSULTANT NAME :-</b>&nbsp;&nbsp; ${((voEHR.voPatientDtl.consultantName)!"Dr.vinay chauhan")?upper_case }
	</font>
  </td>
 </tr>
</table>
                           