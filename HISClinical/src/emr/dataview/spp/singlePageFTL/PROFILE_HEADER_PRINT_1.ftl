<table width="100%" align="center" >
	<tr>
		<td align="center">
			<img src="/HISClinical/hisglobal/images/aiims_header.png" height="80px"/>
  		</td>
	</tr>
</table >
<table>
	<tr>
		<td height="10px">
		</td>
	</tr>
</table>
<table border="1" width="100%">
	<tr>
		<td>
		</td>
	</tr>
</table>
<table width="100%" align="center" >
	<tr>
		<td align="center">
			<font size="3">
			<#if voEHR ??>
        	<#list voEHR.listEpisodeVO as EHRSectionEpisodeVO><b>Department of ${((voEHR.getDepartment())!"")}</b>
			</#list>
			<#else>Department</font>
			</#if> 	
		</td>
	</tr>
</table >  
<table width="100%">
	<tr>
		<td align="center">
			<font size="3"><u><b>Discharge Summary</b></u></font>
		</td>
	</tr>
</table>
  