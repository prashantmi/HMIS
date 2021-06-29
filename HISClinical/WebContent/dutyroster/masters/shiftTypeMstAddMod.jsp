<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>


<%@page import="hisglobal.hisconfig.Config"%>
<%@page import="dutyroster.DutyRosterConfig;"%>
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

<his:javascript src="/dutyroster/js/shiftTypeMstAddMod.js" />
<his:javascript src="/hisglobal/js/commonFunctions.js" />
<his:javascript src="/registration/js/registration.js" />
<his:javascript src="/registration/js/commonFunctions.js" />
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/masterutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<his:css src="/css/calendar-blue2.css" />

 <body onload="checkDayWiseOnLoad()">

  <html:form action="/masters/ShiftTypeMstAddModACT" > 
    
     <%!boolean readOnly;
     	boolean readOnlyDayWise;
     	boolean readOnlyShiftType;
     %>
   <%
   this.readOnly=false;
   this.readOnlyDayWise=true;
   this.readOnlyShiftType=false;
   %>
   
   <logic:equal name="ShiftMstFB" property="hmode" value="VIEW">
   		<% this.readOnly=true; %>
   		<% this.readOnlyShiftType=true; %>
   </logic:equal>
   
     <logic:equal name="ShiftMstFB" property="hmode" value="MODIFY">
   		<% this.readOnlyShiftType=true; %>
   </logic:equal> 
    
     <his:TransactionContainer>
     
       <logic:equal name="ShiftMstFB" property="hmode" value="ADD">
       <his:TitleTag name="Shift Master >> Add">
       </his:TitleTag>
       </logic:equal>
		
	  <logic:equal name="ShiftMstFB" property="hmode" value="MODIFY">
       <his:TitleTag name="Shift Master >> Modify">
       </his:TitleTag>
       </logic:equal>
	
	  <logic:equal name="ShiftMstFB" property="hmode" value="VIEW">
       <his:TitleTag name="Shift Master >> View">
       </his:TitleTag>
       </logic:equal>
	
		
	  <his:ContentTag>
		<table width="100%" border="0" cellspacing="1" cellpadding="0">
		   <tr>
		      <td width="50%" class="tdfonthead">
			     <div align="right">
					<font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								*
					</font> 
				    <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="shift"/> <bean:message key="description"/>&nbsp;
					</font>
				  </div>
			    </td>
			    <td width="50%" class="tdfont">
			      <div align="left">
			        
			          &nbsp;<html:text name="ShiftMstFB" property="shiftDescription" tabindex="1"  maxlength="50" size="30" onkeypress="return validateAlphabetsWithBrackets(event)" disabled="<%=this.readOnly %>">
							   </html:text>
					       	   
				    </div>
			     </td>  
		      </tr>
		       <tr>
		      <td width="50%" class="tdfonthead">
			     <div align="right">
					<font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								*
					</font> 
				    <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="shift"/> <bean:message key="short"/> <bean:message key="name"/>&nbsp;
					</font>
				  </div>
			    </td>
			    <td width="50%" class="tdfont">
			      <div align="left">
			       
			          &nbsp;<html:text name="ShiftMstFB" property="shiftShortName"  tabindex="1"  maxlength="3" size="4" onkeypress="return validateAlphabetsOnly(event,this)" disabled="<%=this.readOnly %>" onblur="validateShiftShortName()">
							   </html:text>
							   
				    </div>
			     </td>  
		      </tr>
		      
		      
		        <tr>
		      <td width="50%" class="tdfonthead">
			     <div align="right">
					<font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								*
					</font> 
				    <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="shift"/> <bean:message key="type"/>&nbsp;
					</font>
				  </div>
			    </td>
			    <td width="50%" class="tdfont">
			      <div align="left">
	     
			        
			    &nbsp;<html:select property="shiftTypeCode" name="ShiftMstFB" tabindex="1" onchange="getShiftTypeAndTime(this)" styleClass="regcbo" disabled="<%=this.readOnlyShiftType %>">
			    <html:option value="-1">Select Value</html:option>
			       <logic:present  name="<%=DutyRosterConfig.DUTY_ROSTER_MASTER_LIST_OF_SHIFT_TYPE%>" >
			          <html:options collection="<%=DutyRosterConfig.DUTY_ROSTER_MASTER_LIST_OF_SHIFT_TYPE%>" property="value"  labelProperty="label"/>
			       </logic:present>
			    </html:select>
			          
	     		   
			   
				   </div>
			     </td>  
		      </tr>
		      
		      
        <tr>
		      <td width="50%" class="tdfonthead">
			     <div align="right">
					<font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								*
					</font> 
				    <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="dayWiseShiftTimings"/> &nbsp;
					</font>
				  </div>
			    </td>
			    <td width="50%" class="tdfont">
			      <div align="left">
	     
			        <%-- 
			    &nbsp; <html:select   property="isDayWiseShift"   name="ShiftMstFB"   tabindex="1"   onchange="showDayWiseShift(this)"   disabled="true"   styleClass="regcbo"   disabled="<%=this.readOnlyDayWise%> "> --%>
			      &nbsp; <html:select   property="isDayWiseShift"   name="ShiftMstFB"   tabindex="1"   onchange="showDayWiseShift(this)"     styleClass="regcbo"   disabled="<%=this.readOnlyDayWise%>">
			    <html:option value="-1"> Select Value </html:option>
				<html:option value="0"> No </html:option>
				<html:option value="1"> Yes </html:option>								       
			       
			       
			    </html:select>
			          
	     		   
				  	   
	   </div>
   </td>  
</tr>
		      
</table>
<%
//try{
%>
		

  
<div id="sameTimingsTable" >		  
		  <table width="100%" border="0" cellspacing="1" cellpadding="0">    
		    <tr>
		      <td width="50%" class="tdfonthead">
			     <div align="right">
					<font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								*
					</font> 
				    <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="startTime"/><bean:message key="timeFormat"/>&nbsp;
					</font>
				  </div>
			    </td>
			   
			  
			 
		  
		  <bean:define name="ShiftMstFB"  id="startTimeHrArray" property="startTimeHrUser" type="java.lang.String[]"></bean:define>
		  <bean:define name="ShiftMstFB"  id="startTimeMinArray" property="startTimeMinUser" type="java.lang.String[]"></bean:define>
		  
		  
			    <td width="10%" class="tdfont">
			      <div align="left">
			       
			          &nbsp;<html:text name="ShiftMstFB" property="startTimeHrUser" tabindex="1"  maxlength="2" size="3" onkeydown="setPreviosValue(this, event)"  onkeyup="moveToRightBox(this,event)" onkeypress="return validatePositiveIntegerOnly(this,event)" onblur="validateHr(this)" value="<%=startTimeHrArray[0]%>" disabled="<%=this.readOnly%>"></html:text>
							   <bean:message key="colon"/>
							   <html:text name="ShiftMstFB" property="startTimeMinUser" tabindex="1"  maxlength="2" size="3" onkeydown="setPreviosValue(this, event)"  onkeyup="moveToRightBox(this,event)" onkeypress="return validatePositiveIntegerOnly(this,event)" onblur="validateMin(this)" value="<%=startTimeMinArray[0]%>" disabled="<%=this.readOnly %>"></html:text>
					        
					        
				 	   
				   </div>
				   </td>
			     
			     
			       <td width="40%" class="tdfont">
				   <div id="startTime"><b><bean:write name="ShiftMstFB" property="startTimeTable"/></b> </div>
				 
			     </td>  
		      </tr>
		      
		        <tr>
		      <td width="50%" class="tdfonthead">
			     <div align="right">
					<font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								*
					</font> 
				    <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="endTime"/> <bean:message key="timeFormat"/>
					</font>
				  </div>
			    </td>
		
		
			
		
		

 <bean:define name="ShiftMstFB"  id="endTimeHrArray" property="endTimeHrUser" type="java.lang.String[]"></bean:define>
 <bean:define name="ShiftMstFB"  id="endTimeMinArray" property="endTimeMinUser" type="java.lang.String[]"></bean:define>
		
			    
			    <td width="10%" class="tdfont">
			      <div align="left">
			        
			          &nbsp;<html:text name="ShiftMstFB" property="endTimeHrUser" tabindex="1"  maxlength="2" size="3" onkeydown="setPreviosValue(this, event)"  onkeyup="moveToRightBox(this,event)" onkeypress="return validatePositiveIntegerOnly(this,event)"  onblur="validateHr(this)" value="<%=endTimeHrArray[0] %>" disabled="<%=this.readOnly %>"></html:text>
							 <bean:message key="colon"/> 
							   <html:text name="ShiftMstFB" property="endTimeMinUser" tabindex="1"  maxlength="2" size="3"  onkeydown="setPreviosValue(this, event)" onkeyup="moveToRightBox(this,event)" onkeypress="return validatePositiveIntegerOnly(this,event)" onblur="validateMin(this)" value="<%=endTimeMinArray[0] %>" disabled="<%=this.readOnly %>"> </html:text>
					      
				    </div>
				    
			     </td>
		   	
			      
			       <td width="40%" class="tdfont">
			   <div id="endTime" align="left"><b><bean:write name="ShiftMstFB" property="endTimeTable"/></b></div>
			     </td>		  
		      </tr>
		      
		     </table>
</div>		  



<div id="differentTimingsTable"  style="display: none;">		  

 <table width="100%" border="0" cellspacing="1" cellpadding="0">    
 
 
		    <tr>
		    
		    <td class="tdfonthead" width="20%">
		    
		    <div align="center">
					<font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								*
					</font> 
				    <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="weekday"/>&nbsp;
					</font>
				  </div>
				  
		    </td>
		    
		    
		      <td width="40%" class="tdfonthead">
			     <div align="center">
					<font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								*
					</font> 
				    <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="startTime"/><bean:message key="timeFormat"/>&nbsp;
					</font>
					
					 <div id="startTimeDifferent" style=" color:#990000;font-size: 10px;font-weight: bold;font-variant: normal;text-transform: capitalize;"><b><bean:write name="ShiftMstFB" property="startTimeTable"/></b> </div>
				  </div>
				  
				 
				   
			    </td>
			    		   
				 		     
			     
			    
		    
		      <td width="40%" class="tdfonthead">
			     <div align="center">
					<font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								*
					</font> 
				    <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="endTime"/> <bean:message key="timeFormat"/>
					</font>
					
					<div id="endTimeDifferent" style=" color:#990000;font-size: 10px;font-weight: bold;font-variant: normal;text-transform: capitalize;"><b><bean:write name="ShiftMstFB" property="endTimeTable"/></b></div>
					
				  </div>
				   
			    </td>
			    	
			      
			       	  
		  </tr>
		      
<%
for(int i=1;i <=7 ; i++ )
{
String day="dOw"+i;
%>


		      
		   <tr>
		      <td width="20%" class="tdfont">
			     <div align="center">
					
				    <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="<%=day%>" />&nbsp;
					</font>
					
				  </div>
			    </td>
			    
			     <td width="40%" class="tdfont">
			     
 
				
				
			     

  <bean:define name="ShiftMstFB"  id="startTimeHrArray" property="startTimeHrUser" type="java.lang.String[]"></bean:define>
  <bean:define name="ShiftMstFB"  id="startTimeMinArray" property="startTimeMinUser" type="java.lang.String[]"></bean:define>
 
			   
			      <div align="center">
			       
			          &nbsp;<html:text name="ShiftMstFB" property="startTimeHrUser" tabindex="1"  maxlength="2" size="3" onkeydown="setPreviosValue(this, event)"  onkeyup="moveToRightBox(this,event)" onkeypress="return validatePositiveIntegerOnly(this,event)" onblur="validateHr(this)" value="<%=startTimeHrArray[i-1]%>" disabled="<%=this.readOnly %>"></html:text>
							   <bean:message key="colon"/>
							   <html:text name="ShiftMstFB" property="startTimeMinUser" tabindex="1"  maxlength="2" size="3" onkeydown="setPreviosValue(this, event)"  onkeyup="moveToRightBox(this,event)" onkeypress="return validatePositiveIntegerOnly(this,event)" onblur="validateMin(this)" value="<%=startTimeMinArray[i-1]%>" disabled="<%=this.readOnly %>"></html:text>
					        
					        
				 	   
				   </div>
				  

			     
			      </td> 	 
			     
			  		      
		  <td width="40%" class="tdfont">
		         
	
			       	
			 
			      <div align="center">
 <bean:define name="ShiftMstFB"  id="endTimeHrArray" property="endTimeHrUser" type="java.lang.String[]"></bean:define>
 <bean:define name="ShiftMstFB"  id="endTimeMinArray" property="endTimeMinUser" type="java.lang.String[]"></bean:define>
 			        
			          &nbsp;<html:text name="ShiftMstFB" property="endTimeHrUser" tabindex="1"  maxlength="2" size="3" onkeydown="setPreviosValue(this, event)"  onkeyup="moveToRightBox(this,event)" onkeypress="return validatePositiveIntegerOnly(this,event)"  onblur="validateHr(this)" value="<%=endTimeHrArray[i-1]%>" disabled="<%=this.readOnly %>"></html:text>
							 <bean:message key="colon"/> 
							   <html:text name="ShiftMstFB" property="endTimeMinUser" tabindex="1"  maxlength="2" size="3"  onkeydown="setPreviosValue(this, event)" onkeyup="moveToRightBox(this,event)" onkeypress="return validatePositiveIntegerOnly(this,event)" onblur="validateMin(this)" value="<%=endTimeMinArray[i-1]%>" disabled="<%=this.readOnly %>"> </html:text>
					      
				    </div>
				    
			   

			     
           </td>
      	  
      </tr>     
   <%
}
   %>   
      
    
  </table>
		     
</div>

   <%
//}catch(Exception e){e.printStackTrace();}
   %>
		      <table width="100%" border="0" cellspacing="1" cellpadding="0">
			  
			 
			 
			 <logic:equal name="ShiftMstFB" property="hmode" value="MODIFY">
			        <tr>
			         <td width="50%" class="tdfonthead">
			          <div align="right">
					   <font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								*
					   </font> 
				       <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="isActive"/>&nbsp;
					   </font>
				     </div>
			        </td>
			        <td width="50%" class="tdfont">
			         <div align="left">
				     &nbsp;<html:select name="ShiftMstFB" property="isValid" tabindex="1" styleClass="regcbo">
						    <html:option value="-1">Select Value</html:option>
							<html:option value="1">Active</html:option>
							<html:option value="2">InActive</html:option>
						 </html:select>
				     </div>
			       </td>  
			     </tr>
			   </logic:equal>
			  
		      
	    </table>
      </his:ContentTag>
      
       <his:ButtonToolBarTag>
			<span id="saveDiv">
			    <logic:notEqual name="ShiftMstFB" property="hmode" value="MODIFY">
			    <logic:notEqual name="ShiftMstFB" property="hmode" value="VIEW">
			      <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>' tabindex="1" style="cursor: pointer" onkeypress="if(event.keyCode==13) finalSubmit('SAVE')" onclick="finalSubmit('SAVE')">
				  <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' tabindex="1"  style="cursor: pointer" onclick="clearForm()" onkeypress="if(event.keyCode==13) clearForm()">
				</logic:notEqual>
				</logic:notEqual>
				
			    <logic:equal name="ShiftMstFB" property="hmode" value="MODIFY">
				   <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>' tabindex="1"  style="cursor: pointer" onkeypress="if(event.keyCode==13) finalSubmit('MODIFYSAVE')" onclick="finalSubmit('MODIFYSAVE')">
				   <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' tabindex="1"  style="cursor: pointer" onclick="clearForm()" onkeypress="if(event.keyCode==13) clearForm()">
				</logic:equal> 
				
	               <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' tabindex="1"  style="cursor: pointer" onclick="submitPage('CANCEL')" onkeypress="if(event.keyCode==13) submitPage('CANCEL')">
			</span>
		</his:ButtonToolBarTag>
		
      
    </his:TransactionContainer>
    <his:status/>
    
    <html:hidden name="ShiftMstFB" property="hmode"/>
	<html:hidden name="ShiftMstFB" property="shiftCode"/>
	<html:hidden name="ShiftMstFB" property="serialNo"/>
    <html:hidden name="ShiftMstFB" property="chk"/>
    <html:hidden name="ShiftMstFB" property="startTimeTable"/>
    <html:hidden name="ShiftMstFB" property="endTimeTable"/>
   <html:hidden name="ShiftMstFB" property="shiftTypeName"/>
   </html:form>
  </body>
</html>
		     
		   
		  