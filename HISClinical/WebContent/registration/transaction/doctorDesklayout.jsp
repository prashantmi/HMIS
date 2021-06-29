<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ page import="org.apache.struts.tiles.ComponentContext,registration.*"%>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>
<%@ page import ="java.util.*,registration.*,hisglobal.vo.*" %>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<%	

System.out.println("inside doctorregisterlayout"); %>

<%
	ComponentContext compContext = (ComponentContext)pageContext.getAttribute("org.apache.struts.taglib.tiles.CompContext", 2);
	Object value = compContext.getAttribute("patientDtl");
	String valBody=value.toString();
	//String str = request.getContextPath();
%>
<%="VALUE BODY:"+valBody %>
<%@page import="hisglobal.hisconfig.Config"%>
<his:javascript src="/registration/js/jsRegistration.js"/>
<his:javascript src="/registration/js/commonvalidation.js"/>
<his:javascript src="/registration/js/registration.js"/>
<script type="text/javascript">

//Submit Register by setting registermode
function submitRegister(registerMode){
	elem = document.getElementsByName('registermode')[0];
	elem.value = registerMode;
	//alert(elem.value);
	document.forms[0].submit();
}
</script>

	<!-- Code to fetch CRNo -->

<table width="100%" colspacing="0" colpadding="0">	
	<tr> 
      <td Class="header" colspan="4" width="68%"><font size="2" face="Verdana, Arial, Helvetica, sans-serif">
         <bean:message key="cmoregister"/></font>
      </td>
      <td Class = "header" colspan="2" width="32%">
         <div align="left">
         <bean:message key="date"/><bean:message key="and"/><bean:message key="time"/>          
		   <bean:write name="<%=Config.SYSDATE%>"/>		 
         </div>
      </td>
     <%System.out.println("printing header");%>      
  </tr>    
  <tr> 
    <td width="17%" nowrap Class="tdfonthead">
        <div align="right"><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
           <bean:message key="crNo"/>
           <font color="#FF0000">*</font>
        </div>
    </td>    
    <td width="17%"  Class = "tdfont" >
       <font color="#000000" size="1" face="Verdana, Arial, Helvetica, sans-serif"> 
           <html:text  name="OpdDoctorDeskFB"  property="crNo1" maxlength="3" size ="3" onkeydown="return validateNumeric(event,this,false)" onkeypress="setPrevValue(this, event);"  onkeyup="moveToRight(this, event);" tabindex="1"/>
		   <html:text  name="OpdDoctorDeskFB"  property="crNo2" maxlength="2" size ='2' onkeydown="return validateNumeric(event,this,false)" onkeypress="setPrevValue(this, event);" onkeyup="moveToRight(this, event);" onblur="padWithZeros(this)" tabindex="1"  />
	       <html:text  name="OpdDoctorDeskFB"  property="crNo3" maxlength="7" size ='7' onkeydown="return validateNumeric(event,this,false)" onkeypress="setPrevValue(this, event);" onkeyup="moveToRight(this, event);" onblur="padWithZeros(this)" tabindex="1"  /> 	           
       </font>
    </td>
        
    <td class="tdfont" colspan="4" width="66%">
        <img class="button" src='<his:path src="/hisglobal/images/GO_NEW.gif"/>' onclick="submitRegister('GETPATDTL');" onkeypress="if(event.keyCode==13) submitRegister('GETPATDTL');" size = '7' tabindex="1">
    </td>    
  </tr>
  <his:statusNew>
   <tr>
         <td colspan="6" class="header">        
       </td>
      </tr>
  </his:statusNew>
</table>
     <tiles:insert attribute='patientDtl'/>		
<%
	if(valBody!=null && !valBody.equals("")){
%>
	<!-- Code to fetch Options -->	
	<his:statusInProcess>
	<his:patientStatus emergency="false" opd="true">
<table width="100%" colspacing="0" colpadding="0">
	<tr> 
      <td Class="header" colspan="5" >
      </td>     
 </tr> 
 <his:openEpisodes/>
 <logic:notEmpty name="<%=RegistrationConfig.REGISTRATIONDESK_EPISODEVO_ARR%>">					
		<logic:iterate id="episode" name="<%=RegistrationConfig.REGISTRATIONDESK_EPISODEVO_ARR%>">
				<tr>
					<td width="5%" class="tdfont" >
						<bean:define name="episode" property="episodeCode" id="epcode" type="java.lang.String"/>
						<bean:define name="episode" property="episodeVisitNo" id="epvisitno" type="java.lang.String"/>
						<html:radio name="OpdDoctorDeskFB" property="episodeCode" value='<%=epcode%>'/>								
					</td>
					<td width="35%" class="tdfont"><bean:write name="episode" property="department"/></td>
		  		    <td width="30%" class="tdfont" ><bean:write name="episode" property="departmentUnit"/></td>
					<td width="30%" class="tdfont" ><bean:write name="episode" property="room"/></td>									
					<html:hidden name="OpdDoctorDeskFB" property="episodeVisitNo" value="<%=epvisitno%>" />					
			</tr>			
		</logic:iterate>								
	</logic:notEmpty> 
  <tr>         
       <td width="14%"  class="tdfonthead">
       <div align="right">
       <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
       <bean:message key="selectoption"/>
       </font></div>
       </td>
       <td width="17%"  class ="tdfont">       
        <html:select name="OpdDoctorDeskFB" property="episodeActionCode" tabindex="0" onchange="submitRegister('DTL4STATUS');">
           <html:option value="-1">Select Value</html:option>
        <html:options collection = "<%=RegistrationConfig.ESSENTIALBO_OPTION_DOCTOR_DESK %>" property = "value" labelProperty = "label" />
        </html:select>
      </td> 
  </tr>

</table>
</his:patientStatus>
</his:statusInProcess>
<%
	}
%>
<table width="100%">	
<tiles:insert attribute='body'/> 

<bean:define name="OpdDoctorDeskFB" property="episodeActionCode" id="actionCode" type="java.lang.String"/>
<%
System.out.println("actionCode:::::::::"+actionCode);
if((actionCode!=null && !(actionCode.equalsIgnoreCase("-1"))) && !(actionCode.trim().equalsIgnoreCase("")))
		{
	System.out.println("print buttons:::::::::");
	
	%>	
<tr>
	  <td colspan="6" class="addtoolbar"  style="border-top:outset black 2px; border-bottom:inset black 2px;"><div align="center" >		  
	    <img class='button' src='<his:path src="/hisglobal/images/save_tab.gif"/>' onclick="submitRegister('SAVE');"  tabindex='1'>
	      <img class='button' src='<his:path src="/hisglobal/images/cancel_tab.gif"/>' tabindex='1'>
		  </div>
		  </td>
</tr>
<%
}
else
{
%>
<tr>
<td Class="header" colspan="4" width="68%">
      </td>
      </tr>
<%
}
%>
</table>
	<html:hidden  name='OpdDoctorDeskFB' property='registermode'/>	
	
<his:status/>