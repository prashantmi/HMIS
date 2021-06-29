<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>

<%@ page import ="registration.*" %>
   	<his:css src="/hisglobal/css/calendar-blue2.css"/>
	<his:javascript src="/registration/js/calendar.js"/>
	<his:javascript src="/registration/js/registration.js"/>
	<his:javascript src="/registration/js/popup.js"/>
	<his:javascript src="/registration/js/time.js"/>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<his:SubTitleTag name="Patient Referred in Casualty">
</his:SubTitleTag>
<his:ContentTag>
   <table width="100%" cellpadding="1" cellspacing="1">
   
     <tr>     
	        <td width="15%" nowrap  class="tdfonthead">      
			<div align="right">
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			<font color="#FF0000">*</font>
			<bean:message key="date"/>
			</font></div>
			</td> 
	        <td width="35%" class="tdfont">        
	        <bean:define name ="EpisodeAttendedRefInCasualtyFB" property="episodeActionDate" id="date" type="java.lang.String"/>	        
	        <his:date name='episodeActionDate'dateFormate="%d-%b-%Y" value="<%=date%>"/>
	        </td>
	                 
	        <td width="15%" class="tdfonthead">
	        <div align="right">
	        <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	        <font color="#FF0000">*</font>
	        <bean:message key="time"/>
	        </font>
	        </div>
	        </td>
	        
	        <td width="35%" class="tdfont">
	           <html:text name="EpisodeAttendedRefInCasualtyFB" styleClass="textboxSmall" property="episodeActionTime" onkeypress="checkTime(event,this);" onblur="if(this.value.length!=0 && this.value.length!=5) {alert('Use Time Format HH:MM 24Hr');this.focus();}" maxlength="5" tabindex="1" /><font color="#FF0000"> Time Format HH:MM 24Hr</font>
	        </td>
    </tr>
  <tr> 
		     <td class="tdfonthead" nowrap width = "15%">
		     <div align="right">
		     <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			 <font color="#FF0000">*</font>
			 <bean:message key="remarks"/>
			 </font>
			 </div>
			 </td>
		     
		     <td class="tdfont" width="35%">
		        <html:textarea styleClass="textarea2" tabindex="1" name="EpisodeAttendedRefInCasualtyFB" property="remarks" onkeypress="return CheckMaxLength(event,this,300)"  />
		     </td>            
			 
			 <td class="tdfonthead" nowrap width = "15%">
			 <div align="right">
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			    <bean:message key="complaintdtl"/>
			    </font>
			    </div>
			    </td>
			    
			    <td class="tdfont" width="35%">
			    <html:textarea styleClass="textarea2" tabindex="1" name="EpisodeAttendedRefInCasualtyFB" property="complainDtl" onkeypress="return CheckMaxLength(event,this,500)"  />
                </td>   
  </tr>            
      <tr> 
      
        <td class="tdfonthead" nowrap width="25%">
          <div align="right">
          <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
          <font color="#FF0000">*</font>
          <bean:message key="department"/>
          </font>
          </div>
        </td>        
      
         <td width="75%" colspan="3" class ="tdfont">       
	        <html:select name="EpisodeAttendedRefInCasualtyFB" property="departmentCode" tabindex="1" >
	           <html:option value="-1">Select Value</html:option>
               <html:options collection = "<%=RegistrationConfig.ESSENTIALBO_OPTION_DEPARTMENT_WITH_CASUALITY%>" property = "value" labelProperty = "label" />
	        </html:select>
        </td>             
      </tr>     

</table>
</his:ContentTag>

  <html:hidden  name='EpisodeAttendedRefInCasualtyFB' property='hmode' value="unspecified"/>
  <div id="divDiagnosisList" style=display:none>              
 	Empty div
 </div>

  <html:hidden  name='EpisodeAttendedRefInCasualtyFB' property='removeRow' value=""/>