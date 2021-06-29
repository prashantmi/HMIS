	<table width="100%">
	<tr>
		<td>
			<font size=1><b><u>Advice On Discharge</u>:&nbsp;&nbsp;</b></font>
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
   <#list voEHR.listDrugAdviceVO as EHRSectionAdviceOnDischargeVO></#list> 
   <#if voEHR.listDrugAdviceVO?size &gt; 0>
   <table width=100%>
     <#list voEHR.listDrugAdviceVO as EHRSectionAdviceOnDischargeVO>
	   <tr>  
			<td width="5%"align="left"><font color="#000000" size="1" face="Verdana, Arial, Helvetica, sans-serif"></font></td> 
			<td width="30%"align="left"><font color="#000000" size="1" face="Verdana, Arial, Helvetica, sans-serif"> ${EHRSectionAdviceOnDischargeVO.getDrugName()!""}(${EHRSectionAdviceOnDischargeVO.getDrugAdminName()!""})</font></td>
			<td width="10%"align="left"><font color="#000000" size="1" face="Verdana, Arial, Helvetica, sans-serif">${EHRSectionAdviceOnDischargeVO.getDoseName()!""}</font></td>
			<td width="15%"align="left"><font color="#000000" size="1" face="Verdana, Arial, Helvetica, sans-serif">${EHRSectionAdviceOnDischargeVO.getFrequencyName()!""}</font></td>
			<td width="10%"align="left"><font color="#000000" size="1" face="Verdana, Arial, Helvetica, sans-serif">${EHRSectionAdviceOnDischargeVO.getDays()!""}</font></td>
			<td width="30%"align="left"><font color="#000000" size="1" face="Verdana, Arial, Helvetica, sans-serif">${EHRSectionAdviceOnDischargeVO.getRemarks()!""}</font></td>   
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
