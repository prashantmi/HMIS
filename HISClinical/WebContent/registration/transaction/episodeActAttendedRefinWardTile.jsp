<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<%@page import="hisglobal.hisconfig.Config"%>
<his:css src="/hisglobal/css/calendar-blue2.css"/>
<his:javascript src="/registration/js/calendar.js"/>
<his:javascript src="/registration/js/registration.js"/>
<his:javascript src="/registration/js/popup.js"/>
<his:javascript src="/registration/js/time.js"/>

<%@ page import ="registration.RegistrationConfig" %>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<script>
function submitTile(mode){
	 var elmt=document.getElementsByName("departmentCode")[0];
	if(elmt.selectedIndex !=0){
	 document.getElementsByName("hmode")[0].value=mode
	 //alert("hmode"+document.getElementsByName("hmode")[0].value)	 
	 document.forms[0].submit();
	 } 
} 
  </script>
  <his:SubTitleTag name="Patient Referred in Ward">
  </his:SubTitleTag>

<his:ContentTag>
<table width="100%" border="0" cellspacing="1" cellpadding="1">  
    <tr>                    
   <tr>     
	      <td width="15%"  class="tdfonthead">      
			<div align="right">
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			<font color="#FF0000">*</font> <bean:message key="date"/>
			</font></div>
			</td>
	        <td width="35%" class="tdfont">        
	        <bean:define name ="EpisodeAttendedRefInWardFB" property="episodeActionDate" id="date" type="java.lang.String"/>	        
	        <his:date name='episodeActionDate'dateFormate="%d-%b-%Y" value="<%=date%>"/>
	        </td>               
	        <td width="15%" class="tdfonthead"><div align="right"><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	        <font color="#FF0000">*</font> <bean:message key="time"/></font></div></td>
	        <td width="35%" class="tdfont">
	           <html:text name="EpisodeAttendedRefInWardFB" styleClass="textboxSmall" property="episodeActionTime" onkeypress="checkTime(event,this);" onblur="if(this.value.length!=0 && this.value.length!=5) {alert('Use Time Format HH:MM 24Hr');this.focus();}" maxlength="5" tabindex="1" /><font color="#FF0000"> Time Format HH:MM 24Hr</font>
	        </td>
    </tr>
  <tr> 
		     <td class="tdfonthead" width = "15%"><div align="right"><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			 <font color="#FF0000">*</font> <bean:message key="remarks"/></font></div></td>
		        <td class="tdfont" width="35%">
		        <html:textarea styleClass="textarea2" tabindex="1" name="EpisodeAttendedRefInWardFB" property="remarks" onkeypress="return CheckMaxLength(event,this,300)"  />
		        
             </td>            
				<td class="tdfonthead" width = "15%"><div align="right"><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			        <bean:message key="complaintdtl"/></font></div></td>
			        <td class="tdfont" width="35%">
			        <html:textarea styleClass="textarea2" tabindex="1" name="EpisodeAttendedRefInWardFB" property="complainDtl" onkeypress="return CheckMaxLength(event,this,500)"  />		        
                </td>   
  </tr>      
           
   <tr>
   <td width="17%" class="tdfonthead">
           <div align="right">
           <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
           <font color="#FF0000">*</font>
               <bean:message key="department"/>
            </font>
            </div>
           </td>
   
          <td class="tdfont"width="20%">           	          
		      <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
                <html:select name="EpisodeAttendedRefInWardFB" tabindex="1" property="departmentCode" styleClass="registrationCmb" onchange="submitTile('GETWARD')">
                <html:option value="-1">Select Value</html:option>
                <logic:present name="<%=Config.ESSENTIALBO_OPTION_DEPARTMENT%>">
				 <html:options collection = "<%=Config.ESSENTIALBO_OPTION_DEPARTMENT%>" property = "value" labelProperty = "label"/>
				 </logic:present>
				 </html:select>
		      </font> 		      
          </td> 
            <td width="17%" class="tdfonthead"><div align="right">
           <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"><font color="#FF0000">*</font>
               <bean:message key="ward"/>
            </font></div>
           </td>      
        <td width="18%"  class="tdfont" >
         <html:select name="EpisodeAttendedRefInWardFB" tabindex="1" property="wardCode" styleClass="registrationCmb">
		          <html:option value="-1">Select Value</html:option>
				 <html:options collection = "<%=RegistrationConfig.ESSENTIALBO_WARD_CODE%>" property = "value" labelProperty = "label"/>
	          </html:select>
		  </td>
      </tr>
                  
 </table>
 </his:ContentTag>
 	  <html:hidden  name='EpisodeAttendedRefInWardFB' property='hmode' value="unspecified"/>
  <div id="divDiagnosisList" style=display:none>              
 	Empty div
 </div>

  <html:hidden  name='EpisodeAttendedRefInWardFB' property='removeRow' value=""/>