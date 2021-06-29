<table width=100%>
 <tr>
  <td height="100">
   <font size="1"><b><u>DIAGNOSIS :-&nbsp;&nbsp;</u></b> </font>
  </td>
 </tr>
 
   <#if voEHR ??>
   
   		<#list voEHR.listDiagnosisVO as EHRSectionDiagnosisVO>
   		<tr><td>
   			<font size="1">
   			Diagnosis : -${((EHRSectionDiagnosisVO.getDignosisName())!"Diagnosis Not Available")}
   			Type : -${((EHRSectionDiagnosisVO.getDiagnosticTypeName())!"Type not Found")}
   			</font>
  		</tr><td>
  		</#list>
  	
   	<#else>
   	<tr>
   		<td>  NAD  </td>
   	</tr>
   	</#if>
 
 <tr>
  <td height="300">
  </td>
 </tr>
</table>


