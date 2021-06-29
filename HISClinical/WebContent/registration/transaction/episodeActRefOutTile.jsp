<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ page import ="java.util.*,registration.*,hisglobal.vo.*" %>
	<his:css src="/hisglobal/css/calendar-blue2.css"/>
	<his:javascript src="/registration/js/calendar.js"/>
	<his:javascript src="/registration/js/registration.js"/>
	<his:javascript src="/registration/js/popup.js"/>
	<his:javascript src="/registration/js/time.js"/>
	<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
  <script>
  function showdivhosname()
 {
    //alert("showdivhosname");
    document.getElementById("divRefHosCode").style.display="none";  
	document.getElementById("divRefHosname").style.display="";  
	document.getElementsByName("patRefGnctdHospitalCode")[0].selectedIndex=0;
 }
 
 function showdivhoscode() 
 {
    //alert("showdivhoscode");
    document.getElementById("divRefHosCode").style.display="";  
	document.getElementById("divRefHosname").style.display="none"; 
	document.getElementsByName("patRefHospitalName")[0].value="";
 } 
     </script>
     
     <his:SubTitleTag name="Patient Referred Out"> </his:SubTitleTag>
     
     <his:ContentTag>
	  <table width="100%" border="0" cellspacing="1" cellpadding="1">
   <tr>     
	      <td width="15%" class="tdfonthead">      
			<div align="right">
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			<font color="#FF0000">*</font>
			<bean:message key="date"/>
			</font></div>
			</td>
			
	        <td width="35%" class="tdfont">        
	        <bean:define name ="EpisodeAttendedRefOutFB" property="episodeActionDate" id="date" type="java.lang.String"/>	        
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
	           <html:text name="EpisodeAttendedRefOutFB" styleClass="textboxSmall" property="episodeActionTime" onkeypress="checkTime(event,this);" onblur="if(this.value.length!=0 && this.value.length!=5) {alert('Use Time Format HH:MM 24Hr');this.focus();}" maxlength="5" tabindex="1" /><font color="#FF0000"> Time Format HH:MM 24Hr</font>
	        </td>
    </tr>
  <tr> 
		     <td class="tdfonthead" width = "15%">
		     <div align="right">
		     <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			 <font color="#FF0000">*</font>
			 <bean:message key="remarks"/>
			 </font>
			 </div>
			 </td>
			 
		     <td class="tdfont" width="35%">
		     <html:textarea styleClass="textarea2" tabindex="1" name="EpisodeAttendedRefOutFB" property="remarks" onkeypress="return CheckMaxLength(event,this,300)"/>
		     </td>            
				<td class="tdfonthead" width = "15%">
				<div align="right"><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			        <bean:message key="complaintdtl"/>
			        </font>
			        </div>
			        </td>
			        
			   <td class="tdfont" width="35%">
			        <html:textarea styleClass="textarea2" tabindex="1" name="EpisodeAttendedRefOutFB" property="complainDtl" onkeypress="return CheckMaxLength(event,this,500)"  />
                </td>
  </tr> 
       </table>
      </his:ContentTag>  
   <his:SubTitleTag name="Refer Details">
   </his:SubTitleTag>
    <his:ContentTag>
   <table width="100%" cellpadding="1" cellspacing="1">   
         <tr>     
	      <td width="15%"  class="tdfonthead" nowrap>      
			<div align="right">
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		     <bean:message key="referredBy"/>
			</font>
			</div>
		  </td>
	      <td width="35%" class="tdfont">        
		  <html:text name="EpisodeAttendedRefOutFB" property="patRefDoctor" styleClass="textboxBig" maxlength="60" tabindex="1" onblur="isAlpha(this,'First Name')" onkeypress="return validateAlphabetsOnly(event)" />
          
          </td>               
          
	      <td width="15%" class="tdfonthead">
	        <div align="right">
	        <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	        <bean:message key="referedByDoctorCode"/>
	        </font>
	        </div>
	      </td>
	      	      
	      <td width="35%" class="tdfont">
	           <html:text name="EpisodeAttendedRefOutFB" property="patRefDoctorCode" styleClass="textboxBig"  maxlength="10" tabindex="1" />
	      </td>
    </tr>
         
      <tr>
       <td width="17%" nowrap class="tdfonthead">
           <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
               <bean:message key="referredto"/>
            </font>
           </td>
    
       <td class="tdfont" nowrap width="20%">           
	     <div id="divIsRefRadioGroup">          
	       <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		    <font color="#FF0000">*</font>
		    <bean:message key="gnctd"/>
		 </font>

	  <html:radio name="EpisodeAttendedRefOutFB" property="referringInstType"  value="G" onclick="showdivhoscode(this)"/> 
		      <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		      <bean:message key="other"/>
		     </font> 
		     <html:radio name="EpisodeAttendedRefOutFB" property="referringInstType" value="O" onclick="showdivhosname(this)"/>
	         </div>
         </td>       
        
        <td width="18%" class="tdfont">
	         <div id='divRefHosCode' style='display:""'>          	         
                  <html:select name="EpisodeAttendedRefOutFB" tabindex="1" property="patRefGnctdHospitalCode" styleClass="registrationCmb">
		          <html:option value="-1">Select Value</html:option>
				 <html:options collection = "<%=RegistrationConfig.ESSENTIALBO_OPTION_REF_HOSPITAL%>" property = "value" labelProperty = "label"/>
	          </html:select>
	         </div>      
			<div id='divRefHosname' style='display:none'>	    
			   <html:text name="EpisodeAttendedRefOutFB" tabindex="1" property="patRefHospitalName" styleClass="textboxBig" size="20"/>
			 </div>        
        </td>
      <td width="18%" class="tdfont"></td>
      </tr>
      
                    
 </table>
 </his:ContentTag>

  <html:hidden  name='EpisodeDeathFB' property='hmode' value="unspecified"/>
  <div id="divDiagnosisList" style=display:none>              
 	Empty div
 </div>

  <html:hidden  name='EpisodeDeathFB' property='removeRow' value=""/>
