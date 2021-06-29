<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>

<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/master.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/>    
<his:css src="/hisglobal/css/calendar-blue2.css"/>

<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/masterutil/js/master.js"/> 
<his:javascript src="/hisglobal/js/util.js"/>
<his:javascript src="/registration/js/registration.js" />
<his:javascript src="/dutyroster/js/locationDutyRoster.js" />
<his:javascript src="/registration/js/calendar.js"/>
<his:javascript src="/hisglobal/js/dateFunctions.js" />
<his:javascript src="/hisglobal/js/validationCommon.js" />
<his:javascript src="/hisglobal/js/validationCalls.js" />
<his:javascript src="/hisglobal/js/utilityFunctions.js" /> 



<%@page import="hisglobal.hisconfig.Config"%>
<%@page import="dutyroster.DutyRosterConfig"%>
<%@page import="java.util.*" %>


<%@page import="dutyroster.transaction.controller.fb.DateWiseEmployeeDutyRosterFB;"%>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>	
<style type="text/css">
@import url(../css/calendar-blue2.css);
</style>
<his:css src="/hisglobal/css/Color.css" />
<his:css src="/hisglobal/css/master.css" />
<his:css src="/hisglobal/css/hisStyle.css" />
<his:css src="/hisglobal/css/hisStyleExt.css" />
<style type="text/css">
	@import url(../css/calendar-blue2.css);
</style>



<his:css src="/css/calendar-blue2.css" />

 <body onload="focusOnLoad()">

  <html:form action="/transaction/LocationDutyRoster" > 
    <%
 	//String systemDate=hisglobal.presentation.WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy HH:mm");
    
    String sysDate=hisglobal.presentation.WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy");   
    %>
   
    
     <his:TransactionContainer>
     
     <logic:notEqual value="REPORT" name="LocationRosterFB" property="hmode">

       <his:TitleTag name="Location Capacity Duty Roster">
     	 
       </his:TitleTag>

   
		</logic:notEqual>
	  
	
		
	  <his:ContentTag>
	  
	  <logic:notEqual value="REPORT" name="LocationRosterFB" property="hmode">
	  
	  
		<table width="100%" border="0" cellspacing="1" cellpadding="0">
		
		   <tr>     
		      	     
		      <td width="25%" class="tdfonthead">
			     <div align="right">
			     <font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								*
					</font> 
					   <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="rosterName"/>
					</font>
				  </div>
			    </td>
			    
			    
			    <td width="25%" class="tdfont">
			      <div align="left">        
	    	   
		  <html:select property="rosterId" name="LocationRosterFB" tabindex="1" styleClass="textbox" onchange="submitRosterType('GET_DUTY_AREA')">
			    <html:option value="-1^-1">Select Value</html:option>
			       <logic:present  name="<%=DutyRosterConfig.LIST_OF_ROSTERS_AND_AREA_TYPE_HAVING_ROSTER_MODE_LOCATION%>" >
			          <html:options collection="<%=DutyRosterConfig.LIST_OF_ROSTERS_AND_AREA_TYPE_HAVING_ROSTER_MODE_LOCATION%>" property="value"  labelProperty="label"/>
			       </logic:present>
		   </html:select>		   
				
				   </div>
			     </td>  
			     
			     
			      <td width="25%" class="tdfonthead">
			     <div align="right">
					<font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								*
					</font> 
				    <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="dutyArea"/>
					</font>
				  </div>
			    </td>
			    
			   
			   
			    
			    <td width="25%" class="tdfont">
			      <div align="left">		          
	    	   
		  <html:select property="areaCode" name="LocationRosterFB" tabindex="1" styleClass="textbox" onchange="submitAreaCode('GET_LOCATION_WISE_ROSTER')">
			    <html:option value="-1">Select Value</html:option>
			       <logic:present  name="<%=DutyRosterConfig.DUTY_ROSTER_MASTER_LIST_OF_AREA%>" >
			          <html:options collection="<%=DutyRosterConfig.DUTY_ROSTER_MASTER_LIST_OF_AREA%>" property="value"  labelProperty="label"/>
			       </logic:present>
		   </html:select>		   
				
				   </div>
			     </td>  
			    
			     
		 </tr>
		 <tr>     
		      	     
		      <td width="25%" class="tdfonthead">
			     <div align="right">
			     <font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								*
					</font> 
					   <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="fromDate"/>
					</font>
				  </div>
			    </td>
			    
			    
			    <td width="25%" class="tdfont">
			      <div align="left">        
	    	   
	    	   <bean:define id="fromDateId" name="LocationRosterFB" property="fromDate"></bean:define>
	    	   		<his:date name="fromDate" dateFormate="%d-%b-%Y" value="<%=(String)fromDateId%>" />	
	    	   		<html:hidden name="LocationRosterFB" property="startDateTimeOld" value="<%=(String)fromDateId%>"/>
	    	   				
				   </div>
			     </td>  
			     
			     
			      <td width="25%" class="tdfonthead">
			     <div align="right">
					<font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								*
					</font> 
				    <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="toDate"/>
					</font>
				  </div>
			    </td>
			    
			    
			    <td width="25%" class="tdfont">
			      <div align="left">		          
	    	     <bean:define id="toDateId" name="LocationRosterFB" property="toDate" ></bean:define>
				 <his:date name="toDate" dateFormate="%d-%b-%Y"  value="<%=(String)toDateId%>" />	   
				
				 			
			<html:hidden name="LocationRosterFB" property="endDateTimeOld" value="<%=(String)toDateId%>"/>	
				   </div>
			     </td>  
			    
			 
		 </tr>   
		     
	</table>
	
</logic:notEqual>		
		
		<bean:write name="LocationRosterFB" property="distinctRosterList" filter="false"/>
		
		<bean:write name="LocationRosterFB" property="locationWiseRoster" filter="false"/>
		
      </his:ContentTag>
      
      
<logic:equal value="0" name="<%=DutyRosterConfig.TOTAL_LOCATION_WISE_ROSTERS%>">
       	 <his:ButtonToolBarTag>
			<span id="saveDiv">
			      <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>'  tabindex="1" style="cursor: pointer" onkeypress="if(event.keyCode==13) finalSubmit('SAVE')" onclick="finalSubmit('SAVE')">
				  <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' tabindex="1"  style="cursor: pointer" onclick="clearForm()" onkeypress="if(event.keyCode==13) clearForm()">
				  <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' tabindex="1" style="cursor: pointer" onclick="cancelFunc()" onkeypress="if(event.keyCode==13) cancelFunc()">
			</span>
		</his:ButtonToolBarTag>	
</logic:equal>
		
		
<logic:equal value="NEW" name="LocationRosterFB" property="modeOfRoster">
       	 <his:ButtonToolBarTag>
			<span id="saveDiv">
			      <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>'  tabindex="1" style="cursor: pointer" onkeypress="if(event.keyCode==13) finalSubmit('SAVE')" onclick="finalSubmit('SAVE')">
				  <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' tabindex="1"  style="cursor: pointer" onclick="clearForm()" onkeypress="if(event.keyCode==13) clearForm()">
				  <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' tabindex="1" style="cursor: pointer" onclick="cancelFunc()" onkeypress="if(event.keyCode==13) cancelFunc()">
			</span>
		</his:ButtonToolBarTag>	
</logic:equal>
		
		<logic:equal value="MODIFY" name="LocationRosterFB" property="modeOfRoster">
       	 <his:ButtonToolBarTag>
			<span id="saveDiv">
			      <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>'  tabindex="1" style="cursor: pointer" onkeypress="if(event.keyCode==13) finalSubmit('SAVE')" onclick="finalSubmit('SAVE')">
				  <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' tabindex="1"  style="cursor: pointer" onclick="clearForm()" onkeypress="if(event.keyCode==13) clearForm()">
				  <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' tabindex="1" style="cursor: pointer" onclick="cancelFunc()" onkeypress="if(event.keyCode==13) cancelFunc()">
			</span>
		</his:ButtonToolBarTag>	
</logic:equal>
      
    </his:TransactionContainer>
    <his:status/>
    
    <html:hidden name="LocationRosterFB" property="hmode"/>
	<html:hidden name="LocationRosterFB"   property="concatedValueToBeSaved"/>
	<html:hidden name="LocationRosterFB"   property="modeOfRoster"/>
	<html:hidden name="LocationRosterFB"   property="sysDate" value="<%=sysDate %>" />
	<html:hidden name="LocationRosterFB"   property="fromDateCheck"/>
	<html:hidden name="LocationRosterFB"   property="toDateCheck"/>
	
<input type="hidden" name="morningShiftId" value="<%=DutyRosterConfig.MORNING_SHIFT_TYPE_ID %>">
<input type="hidden" name="eveningShiftId" value="<%=DutyRosterConfig.EVENING_SHIFT_TYPE_ID %>">
<input type="hidden" name="nightShiftId" value="<%=DutyRosterConfig.NIGHT_SHIFT_TYPE_ID %>">
<input type="hidden" name="dayShiftId" value="<%=DutyRosterConfig.DAY_SHIFT_TYPE_ID%>">	
		
   </html:form>
  </body>
</html>
		     
		   
		  