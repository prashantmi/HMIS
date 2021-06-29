<table width=100%>
 <tr>
  <td height=>
   <font size=1><b><u>DIAGNOSIS :-&nbsp;&nbsp;</u></b>
   <#if EHRVO ??>
        <#list EHRVO.listDiagnosisVO as EHRSectionDiagnosisVO>
         <#if EHRVO.listDiagnosisVO.diagnosisName ??>
           ${EHRSectionDiagnosisVO.diagnosisName}
           <#else>NAD 
          </#if>
        </#list>
   <#else>NAD
      <br>
   </font>
  </#if> 
  </td>
 </tr>
 <tr>
  <td height="300">
  </td>
 </tr>
</table>


