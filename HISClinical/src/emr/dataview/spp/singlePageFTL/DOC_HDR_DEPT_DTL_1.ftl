<table width="100%" align="center" >
	<tr>
		<td align="center">
			<font size="3">
			<#if voEHR ??>
        	<#list voEHR.listEpisodeVO as EHRSectionEpisodeVO><b>Department Of ${EHRSectionEpisodeVO.getDepartment()}</b>
			</#list>
			<#else></font>
			</#if> 	
		</td>
	</tr>
</table >              