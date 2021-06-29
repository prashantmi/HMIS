<table width="100%">
	<tr>
		<td>
			<font size=1><b><u>Investigation Details</u>:&nbsp;&nbsp;</b></font>
		</td>
	</tr>
</table>

<table width="100%">
	<tr>
		<td width="5%"></td>
		<td width="15%" ><font color="#000000" size="1" ><b>Visit Date</b></font></td>
		<td width="30%"><font color="#000000" size="1" ><b>Test Name</b></font></td>
		<td width="25%"><font color="#000000" size="1" ><b>Result</b></font></td>
		<td width="20%"><font color="#000000" size="1" ><b>Unit</b></font></td>			
	</tr>
</table>
	
<#list voEHR.listInvestigationResultsVO as EHRSectionInvestigationResultsVO></#list>

<#if voEHR.listInvestigationResultsVO?size &gt; 0>
	<table width="100%">
 	<#list voEHR.listInvestigationResultsVO as EHRSectionInvestigationResultsVO>
		<tr>
			<td width="5%"align="left"><font color="#000000" size="1" ></font></td> 
			<td width="15%"align="left"><font color="#000000" size="1" >${EHRSectionInvestigationResultsVO.getPatvisitdate()!""}</font></td>
			<td width="30%"align="left"><font color="#000000" size="1" >${EHRSectionInvestigationResultsVO.getTestName()!""}</font></td>
			<td width="25%"align="left"><font color="#000000" size="1" >${EHRSectionInvestigationResultsVO.getTestValue()!""}</font></td>
			<td width="20%"align="left"><font color="#000000" size="1" >${EHRSectionInvestigationResultsVO.getTestUnit()!""}</font></td>
		</tr>
	</#list>
	</table>
<#else>
	<table>     
		<tr>
			<td height='50px'></td>
		</tr>
	</table>
</#if>