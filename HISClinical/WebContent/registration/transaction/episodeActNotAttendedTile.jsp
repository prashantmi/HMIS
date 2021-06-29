<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%  
   System.out.println("inside not attended tile");
%>
  
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
  
  <his:SubTitleTag name="Patient Not Attended"> </his:SubTitleTag>
  <his:ContentTag>
    <table width="100%" cellpadding="1" cellspacing="1">   
     
      <tr> 
        <td class="tdfonthead" width="25%"><div align="right"><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">Remarks</font></div></td>
        <td class="tdfont" colspan="3" width="75%">        
        <html:textarea name="EpisodeNotAttendedFB" property="remarks" styleClass="textarea2" />                
		</td>
      </tr>
      
      
    </table>  
    </his:ContentTag>
    	  <html:hidden  name='EpisodeNotAttendedFB' property='hmode' value="unspecified"/>
  <div id="divDiagnosisList" style=display:none>              
 	Empty div
 </div>

  <html:hidden  name='EpisodeNotAttendedFB' property='removeRow' value=""/>