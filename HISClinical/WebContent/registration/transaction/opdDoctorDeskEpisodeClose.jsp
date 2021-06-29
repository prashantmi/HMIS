
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<his:css src="/hisglobal/css/calendar-blue2.css"/>
<his:javascript src="/registration/js/calendar.js"/>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>

<script>
 
</script>
 
 <table width="100%" cellpadding="1" cellspacing="1">
      <tr> 
         <td colspan ="6" class ="header">
           <bean:message key="confirmVisit"/>
         </td>
       </tr>
     <tr>     
      <td width="17%" height="22" class="tdfonthead">      
		<div align="right">
		<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		 <bean:message key="date"/>
		</font></div>
		</td>
        <td width="17%" class="tdfont">        
        <his:date name='<%="episodeActionDate"%>' dateFormate="%d-%b-%Y" />
         </td>               
        <td width="17%" class="tdfonthead"><div align="right"><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
        <bean:message key="time"/></font></div></td>
        <td width="17%" class="tdfont">
         <html:text name="OpdDoctorDeskEpisodeCloseFB" property="episodeActionTime" />
        </td>
        <td width="16%" class="tdfonthead"></td>
        <td width="16%" class="tdfont"></td>
      </tr>     
    <tr>
	 
      <td class="tdfonthead" width = "17%"><div align="right"><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
        <bean:message key="remarks"/></font></div></td>
        <td class="tdfont" 17%>
        <html:textarea name="OpdDoctorDeskEpisodeCloseFB" property="remarks" />
        
        </td>
        <td class="tdfonthead" width = "16%"><div align="right"></div></td>
        <td class="tdfont" width = "16%">&nbsp;</td>
      </tr>
    
  <tr> 
         <td colspan ="6" class ="header">          
         </td>
  </tr>
 </table>