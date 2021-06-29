<table width=100%>
   <#list voEHR.listDiagnosisVO as EHRSectionDiagnosisVO></#list>
   <#if voEHR.listDiagnosisVO?size &gt; 0> 
	   <tr>
		    <td>
			    <font size=1><b><u>Diagnosis</u>:&nbsp;&nbsp;</b></font>			   
			    <font size="1">
			       <#list voEHR.listDiagnosisVO as EHRSectionDiagnosisVO>
			       ${EHRSectionDiagnosisVO.getDignosisName()}&nbsp;(${EHRSectionDiagnosisVO.getDiagnosticTypeName()});nbsp;
                  </#list>
                  </font>
             </td>
        </tr>          
        <#else>
	<tr>
		<td>
			<font size="1"><b><u>Diagnosis</u>:&nbsp;</b> </font>
		</td>
	</tr>
	<tr>
		<td height='50px'>
		</td>
	</tr>
	</#if> 
</table>

