<table width=100%>
 <tr>
   <td style="width:30%">
      <font size="1"><b>CR No. :-</b>&nbsp;&nbsp;${((voEHR.voPatientDtl.getPatCrNo())!"10109000000000")}
      
      
      </font>
   </td>
   <td style="width:40%">
     <font size="1"><b>NAME :-</b>&nbsp;&nbsp; ${((voEHR.voPatientDtl.getPatName())!"Fname")?upper_case} 
	 </font>
   </td>					
   <td style="width:30%">
     <font size="1"><b> AGE/GENDER  :-</b>&nbsp;&nbsp;${((voEHR.voPatientDtl.getPatAge())!"00")}/${((voEHR.voPatientDtl.getPatGender())!"f")} 
	 </font>
   </td>
 </tr>
 
 <tr>
  <td style="width:30%">
     <font size="1"><b>FATHER/SPOUSE NAME :-</b>&nbsp;&nbsp;${((voEHR.voPatientDtl.getPatFatherName())!"FatherName")}/${((voEHR.voPatientDtl.getPatSpouceName())!"")}
	 </font>
   </td>
   <td style="width:40%">
      <font size="1"><b>ADDRESS :-</b>&nbsp;&nbsp;${((voEHR.voPatientDtl.getPatCompleteAddress())!"complete Address")?upper_case}
      </font>
   </td>
   <td style="width:30%">
      <font size="1"><b>MOBILE :-</b>&nbsp;&nbsp;1134567880
      </font>
   </td>
 </tr>
 <tr>
  <td style="width:30%">
     <font size="1"><b>DEPARTMENT :-</b>&nbsp;&nbsp;${((voEHR.voPatientDtl.getDepartmentName())!"department name")}
	 </font>
   </td>
   <td style="width:40%">
      <font size="1"><b>VISIT DATE :-</b>&nbsp;&nbsp;${((voEHR.voPatientDtl.getEntryDate())!"00/00/0000")}
      </font>
   </td>
   <td style="width:30%">
      <font size="1"><b>CONSULTANT NAME :-</b>&nbsp;&nbsp; ${((voEHR.voPatientDtl.getConsultantName())!"Dr.vinay chauhan")?upper_case }
      </font>
   </td>
 </tr>
</table>      
                           
							