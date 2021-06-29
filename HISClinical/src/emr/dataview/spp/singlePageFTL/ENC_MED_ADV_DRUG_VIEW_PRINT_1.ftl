<table width="100%">
	<tr>
		<td>
			<font size=1><b><u>Treatment Given</u>:&nbsp;&nbsp;</b></font>
		</td>
	</tr>
</table>	
			<table width="100%">
			<tr>
			<td width="5%"></td>
			<td width="30%" ><font color="#000000" size="1" face="Verdana, Arial, Helvetica, sans-serif"><b>Medicine</b></font></td>
			<td width="10%"><font color="#000000" size="1" face="Verdana, Arial, Helvetica, sans-serif"><b>Dose</b></font></td>
			<td width="15%"><font color="#000000" size="1" face="Verdana, Arial, Helvetica, sans-serif"><b>Frequency</b></font></td>
			<td width="10%"><font color="#000000" size="1" face="Verdana, Arial, Helvetica, sans-serif"><b>Days</b></font></td>
			<td width="30%"><font color="#000000" size="1" face="Verdana, Arial, Helvetica, sans-serif"><b>Instructions</b></font></td>
			</tr>
			</table>
   <#list voEHR.listTreatmentVO as EHRSectionTreatmentGivenVO></#list>  
   <#if voEHR.listTreatmentVO?size &gt; 0> 
   <table width=100%>
     <#list voEHR.listTreatmentVO as EHRSectionTreatmentGivenVO>
	   <tr>
           <td>    
			<td width="5%"align="left"><font color="#000000" size="1" face="Verdana, Arial, Helvetica, sans-serif"></font></td> 
			<td width="30%"align="left"><font color="#000000" size="1" face="Verdana, Arial, Helvetica, sans-serif"> ${EHRSectionTreatmentGivenVO.getDrugName()!""}(${EHRSectionTreatmentGivenVO.getDrugAdminName()!""})</font></td>
			<td width="10%"align="left"><font color="#000000" size="1" face="Verdana, Arial, Helvetica, sans-serif">${EHRSectionTreatmentGivenVO.getDoseName()!""}</font></td>
			<td width="15%"align="left"><font color="#000000" size="1" face="Verdana, Arial, Helvetica, sans-serif">${EHRSectionTreatmentGivenVO.getFrequencyName()!""}</font></td>
			<td width="10%"align="left"><font color="#000000" size="1" face="Verdana, Arial, Helvetica, sans-serif">${EHRSectionTreatmentGivenVO.getDays()!""}</font></td>
			<td width="30%"align="left"><font color="#000000" size="1" face="Verdana, Arial, Helvetica, sans-serif">${EHRSectionTreatmentGivenVO.getRemarks()!""}</font></td>
           </td>    
        </tr>
        </#list>
      </table>         
        <#else>
   <table>     
	<tr>
		<td height='50px'>
		</td>
	</tr>	
</table>
</#if> 
