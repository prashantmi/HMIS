<table width=100%>
 <tr>
   <td style="width:30%">
      <font size="1"><b>CR No. :-</b>&nbsp;&nbsp;${((voEHR.voHospital.patCrNo)!"10109000000000")}
      </font>
   </td>
   <td style="width:40%">
     <font size="1"><b>NAME :-</b>&nbsp;&nbsp; ${((voEHR.voHospital.patFirstName)!"Fname")?upper_case} 
	 </font>
   </td>					
   <td style="width:30%">
     <font size="1"><b> AGE/GENDER  :-</b>&nbsp;&nbsp;${((voEHR.voHospital.patAge)!"00")}/${((voEHR.voHospital.patGender)!"f")} 
	 </font>
   </td>
 </tr>
 <tr>
  <td style="width:30%">
     <font size="1"><b>FATHER/SPOUSE NAME :-</b>&nbsp;&nbsp;${((voEHR.voHospital.patFatherName)!"FatherName")}/${((voEHR.voHospital.patSpouceName)!"")}
	 </font>
   </td>
   <td style="width:40%">
      <font size="1"><b>ADDRESS :-</b>&nbsp;&nbsp;${((voEHR.voHospital.patCompleteAddress)!"complete Address")?upper_case}
      </font>
   </td>
   <td style="width:30%">
      <font size="1"><b>MOBILE :-</b>&nbsp;&nbsp;1134567880
      </font>
   </td>
 </tr>
</table>      
                           
							