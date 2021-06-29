<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@	taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="java.util.*" %>
<%@page import="hisglobal.hisconfig.Config"%>
<%@page import="dutyroster.DutyRosterConfig;"%>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>	
<style type="text/css">
@import url(../css/calendar-blue2.css);
</style>
<his:css src="/hisglobal/css/calendar-blue2.css"/>
<his:css src="/hisglobal/css/Color.css" />
<his:css src="/hisglobal/css/master.css" />
<his:css src="/hisglobal/css/hisStyle.css" />
<his:css src="/hisglobal/css/hisStyleExt.css" />
<his:css src="/css/calendar-blue2.css" />
<style type="text/css">
	@import url(../css/calendar-blue2.css);
</style>
<his:javascript src="/registration/js/registration.js" />
<his:javascript src="/dutyroster/js/empRosterGeneration.js" />
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/masterutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<his:javascript src="/registration/js/calendar.js" />
<his:javascript src="/hisglobal/js/dateFunctions.js" />
<his:javascript src="/registration/js/popup.js" />
<his:javascript src="/hisglobal/js/validationCommon.js" />
<his:javascript src="/hisglobal/js/validationCalls.js" />
<his:javascript src="/hisglobal/js/utilityFunctions.js" /> 

<script language="javaScript"  src="/HISClinical/hisglobal/js/commonFunctions.js"></script>

 <body onload="checkDateOnLoad()">

  <%
 String systemDate=hisglobal.presentation.WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy HH:mm");

  %>
    
  <html:form action="/transaction/EmpRosterGeneration" > 
    
   
    
     <his:TransactionContainer>
     

       <his:TitleTag name="Employee Roster Generation">
     	 
       </his:TitleTag>

   
		
	  
	
		
	  <his:ContentTag>
		<table width="100%" border="0" cellspacing="1" cellpadding="0">
		
		  <tr>
		      <td width="25%" class="tdfonthead">
			     <div align="right">
					<font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								*
					</font> 
				    <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="year"/>&nbsp;
					</font>
				  </div>
			    </td>
			    
			    
			    <td width="25%" class="tdfont">
			      <div align="left">			       		
		   <html:select property="year" name="EmpRosterGenerationFB" tabindex="1" styleClass="textbox" onchange="submitFormData()">
			       <logic:present  name="<%=DutyRosterConfig.LIST_OF_YEARS_EMPLOYEE_DUTY_ROSTER_MASTER%>" >
			          <html:options collection="<%=DutyRosterConfig.LIST_OF_YEARS_EMPLOYEE_DUTY_ROSTER_MASTER%>" property="value"  labelProperty="label"/>
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
								<bean:message key="month"/>
					</font>
				  </div>
			    </td>
			    
			    
			    <td width="25%" class="tdfont">
			      <div align="left">	    	   
		  <html:select property="month" name="EmpRosterGenerationFB" tabindex="1" styleClass="textbox" onchange="submitFormData()">
			      <logic:present  name="<%=DutyRosterConfig.LIST_OF_MONTHS_EMPLOYEE_DUTY_ROSTER_MASTER%>" >
			          <html:options collection="<%=DutyRosterConfig.LIST_OF_MONTHS_EMPLOYEE_DUTY_ROSTER_MASTER%>" property="value"  labelProperty="label"/>
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
								<bean:message key="rosterCategory"/>
					</font>
				  </div>
			    </td>
			    
			    
			    <td width="25%" class="tdfont">
			      <div align="left">        
	    	   
		  <html:select property="rosterCategory" name="EmpRosterGenerationFB" tabindex="1" styleClass="textbox" onchange="submitPage('GET_ROSTERS')">
			    <html:option value="-1">Select Value</html:option>
			       <logic:present  name="<%=DutyRosterConfig.LIST_OF_ROSTER_CATEGORY%>" >
			          <html:options collection="<%=DutyRosterConfig.LIST_OF_ROSTER_CATEGORY%>" property="value"  labelProperty="label"/>
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
								<bean:message key="rosterName"/>
					</font>
				  </div>
			    </td>
			    
			    
			    <td width="25%" class="tdfont">
			      <div align="left">		          
	    	   
		  <html:select property="rosterId" name="EmpRosterGenerationFB" tabindex="1" styleClass="textbox" onchange="submitPage('GET_DUTY_AREA')">
			    <html:option value="-1@-1@-1">Select Value</html:option>
			       <logic:present  name="<%=DutyRosterConfig.LIST_OF_ROSTERS_ON_THE_BASIS_OF_ROSTER_CATEGORY%>" >
			          <html:options collection="<%=DutyRosterConfig.LIST_OF_ROSTERS_ON_THE_BASIS_OF_ROSTER_CATEGORY%>" property="value"  labelProperty="label"/>
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
								<bean:message key="dutyArea"/>
					</font>
				  </div>
			    </td>
			    
			    
			    <td width="25%" class="tdfont">
			      <div align="left">        
	    	   
		   <html:select property="areaCode" name="EmpRosterGenerationFB" tabindex="1" styleClass="textbox" onchange="submitPage('GET_EMPLOYEES_CALENDAR')">
			    <html:option value="-1">Select Value</html:option>
			       <logic:present  name="<%=DutyRosterConfig.DUTY_ROSTER_MASTER_LIST_OF_AREA%>" >
			    		<html:options collection="<%=DutyRosterConfig.DUTY_ROSTER_MASTER_LIST_OF_AREA%>" property="value"  labelProperty="label"/>
			       
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
								<bean:message key="designation"/>
					</font>
				  </div>
			    </td>
			    
			    
			    <td width="25%" class="tdfont">
			     
			     <div align="left">        
	    	   
		  <html:select property="designationId" name="EmpRosterGenerationFB" tabindex="1" styleClass="textbox" onchange="submitPage('GET_EMPLOYEES_CALENDAR')" disabled="true">
			    <html:option value="-1">Select Value</html:option>
			       <logic:present  name="<%=DutyRosterConfig.LIST_OF_DESIGNATION_ON_THE_BASIS_OF_ROSTER_TYPE%>" >
			    	<html:option value="ALL" >ALL</html:option>	
			          <html:options collection="<%=DutyRosterConfig.LIST_OF_DESIGNATION_ON_THE_BASIS_OF_ROSTER_TYPE%>" property="value"  labelProperty="label"/>
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
								<bean:message key="generation"/> <bean:message key="fromDate"/>
					</font>
				  </div>
			    </td>
			    
			    
			    <td width="25%" class="tdfont">
			      <div align="left">        
	    	   
	    	   <bean:define id="startDateId" name="EmpRosterGenerationFB" property="startDate"></bean:define>
	    	   		<his:date name="startDate" dateFormate="%d-%b-%Y" value="<%=(String)startDateId%>" />	
	    	   			    	   				
				   </div>
			     </td>  
			     
			     
			      <td width="25%" class="tdfonthead">
			     <div align="right">
					<font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								*
					</font> 
				    <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="generation"/> <bean:message key="toDate"/>
					</font>
				  </div>
			    </td>
			    
			    
			    <td width="25%" class="tdfont">
			      <div align="left">		          
	    	     <bean:define id="endDateId" name="EmpRosterGenerationFB" property="endDate" ></bean:define>
				 <his:date name="endDate" dateFormate="%d-%b-%Y"  value="<%=(String)endDateId%>" />	   
							
				
				   </div>
			     </td>  
			    
			 
		 </tr>  
		
	</table>
		
		
		
		
      </his:ContentTag>
        
      <his:ContentTag>
      <bean:write name="EmpRosterGenerationFB" property="calendar" filter="false"/>
      </his:ContentTag>
      
      <his:SubTitleTag name="Legends">
<div align="right"><b>


		<table width="100%" cellspacing="0" cellpadding="0">
		<tr>
		<td width="70%"> </td>
			<td width="30%">
			<div align="right">
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">Show </font>
				<img src='/HIS/hisglobal/images/avai/arrow_down.gif' onclick="showLegends();">	
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">Hide	</font>
			<img src='/HIS/hisglobal/images/avai/arrow_up.gif' onclick="hideLegends();">

			</div>
			</td>			
		</tr>
		</table>
	
</b></div>

</his:SubTitleTag>	

<div id="legendId">
 

<logic:notEmpty name="<%=DutyRosterConfig.MAP_OF_LEGENDS_SHIFT %>">


	<table width="100%" cellpadding="0" cellspacing="1">

	
	
	
<tr><td class="tdfonthead"><input type="button" style="background-color: <%=DutyRosterConfig.ROSTER_GENERATED_COLOR %>;" value=""></td><td class="tdfont">Roster is Verified and Generated,Hence Duty Cannot be Assigned/Changed</td></tr>	
<tr><td class="tdfonthead"><input type="button" style="background-color: <%=DutyRosterConfig.EMP_LEAVE_COLOR %>;" value=""></td><td class="tdfont">Employee is on Leave,Hence Duty Cannot be Assigned</td></tr>			
<tr><td align="left" width="10%" class="tdfonthead"><input type="button" style="background-color: <%=DutyRosterConfig.HOLIDAY_COLOR %>;" value="" ></td><td align="left" class="tdfont">Sunday/Gazetted Holiday</td></tr>

<tr><td align="left" width="10%" class="tdfonthead"><b><a>EmployeeName</a></b></td><td align="left" class="tdfont">Employee Duty in Other Rosters Can be Viewed</td></tr>
<tr><td align="left" width="10%" class="tdfonthead">NA</td><td align="left" class="tdfont">Not Assigned</td></tr>
<tr><td align="left" width="10%" class="tdfonthead">REL</td><td align="left" class="tdfont">Employee Can be/Is Reliver</td></tr>

	
	<logic:iterate id="shiftLegendEntry" name="<%=DutyRosterConfig.MAP_OF_LEGENDS_SHIFT %>" type="java.util.Map.Entry">
	
		<bean:define id="shiftShortName" name="shiftLegendEntry" property="key"></bean:define>
		<bean:define id="shiftFullName" name="shiftLegendEntry" property="value"></bean:define>
	
		<tr><td align="left" width="10%" class="tdfonthead"><%=shiftShortName %></td><td align="left" class="tdfont"><%=shiftFullName %></td></tr>
	</logic:iterate>	
	
	
	
	</table>
	
</logic:notEmpty>

<table width="100%" cellpadding="0" cellspacing="1">

<tr ><td class="tdfont" colspan="7" >The Below Color Code,Signifies that the Employee is Already Assigned that Shift Somewhere</td></tr>

<tr >

<td class="tdfonthead"><input type="button" style="background-color: <%=DutyRosterConfig.DAY_OFF_COLOR %>;" value="">&nbsp;Day Off</td> 
<td class="tdfonthead">	<input type="button" style="background-color: <%=DutyRosterConfig.MORNING_SHIFT_TYPE_COLOR %>;" value="">&nbsp;Morning</td>
<td class="tdfonthead">	<input type="button" style="background-color: <%=DutyRosterConfig.EVENING_SHIFT_TYPE_COLOR %>;" value="" >&nbsp;Evening</td> 
<td class="tdfonthead">	<input type="button" style="background-color: <%=DutyRosterConfig.NIGHT_SHIFT_TYPE_COLOR %>;" value="" >&nbsp;Night</td>
<td class="tdfonthead"><input type="button" style="background-color: <%=DutyRosterConfig.DAY_SHIFT_TYPE_COLOR %>;" value="" >&nbsp;Day</td>
<td class="tdfonthead"><input type="button" style="background-color: <%=DutyRosterConfig.EARLY_MORNING_SHIFT_TYPE_COLOR %>;" value="" >&nbsp;Early Morning</td>
<td class="tdfonthead"><input type="button" style="background-color: <%=DutyRosterConfig.MULTIPLE_SHIFT_TYPE_COLOR %>;" value="" >&nbsp;Multiple Shifts</td>

</tr>
	
</table>

 </div> 
 
       <his:ButtonToolBarTag>
			<span id="saveDiv">
			      <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>' id="saveButton"   tabindex="1" style="cursor: pointer" onkeypress="if(event.keyCode==13) finalSubmit('SAVE')" onclick="finalSubmit('SAVE')">
				  <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' tabindex="1"  style="cursor: pointer" onclick="clearForm()" onkeypress="if(event.keyCode==13) clearForm()">
				  <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' tabindex="1" style="cursor: pointer" onclick="cancelFunc()" onkeypress="if(event.keyCode==13) cancelFunc()">
				    <img class="button" src='<his:path src="/hisglobal/images/RepeatPattern1.png"/>' tabindex="1" style="cursor: pointer" onclick="enableNewBlanket(event)" onkeypress="if(event.keyCode==13)enableNewBlanket(event)">
				  <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-generate.png"/>' tabindex="1" style="cursor: pointer" onclick="generateRoster()" onkeypress="if(event.keyCode==13) generateRoster()">
				  <img class="button" src='<his:path src="/hisglobal/images/Drop1.png"/>' tabindex="1" style="cursor: pointer" onclick="dropRoster()" onkeypress="if(event.keyCode==13) dropRoster()">
			      	
			</span>
		</his:ButtonToolBarTag>



    <his:status/>
    
    
	
	<div id="blanket" style="height: 580;width: 1024;display: none;"></div>
	<div id="newPatternDivId" style="display: none; height: 125px;width: 310px;position:absolute; top: 20%;left: 30%;z-index: 9100;background-color: #FFEBD5;">
				<div align="right">
					<img class="button" tabindex="1" src="/HISClinical/hisglobal/images/popUp_cancel.JPG" style="cursor: pointer; " onkeypress="disableNewBlanket(event)" onclick="disableNewBlanket(event)">
				</div>	
				<table width="100%">
					<tr>
						<td width="80%" class="tdfonthead">
							<div align="right">
								<font color="#FF0000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> * </font>
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								Repeat Pattern From (Date)
								</font>
							</div>
						</td>
						<td width="20%" class="tdfonthead">
							<div align="left">
								<input type="text" name="newPatternRepeatFrom" maxlength="2" size="3" tabindex="1" onkeypress="return validatePositiveIntegerOnly(this,event)" >
							</div>
						</td>
					</tr>
					
					<tr>
						<td width="80%" class="tdfonthead">
							<div align="right">
								<font color="#FF0000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> * </font>
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								Repeat Pattern To (Date)
								</font>
							</div>
						</td>
						<td width="20%" class="tdfonthead">
							<div align="left">
								<input type="text" name="newPatternRepeatTo" maxlength="2" size="3" tabindex="1" onkeypress="return validatePositiveIntegerOnly(this,event)" >
							</div>
						</td>
					</tr>
					
					<tr>
						<td width="80%" class="tdfonthead">
							<div align="right">
								<font color="#FF0000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> * </font>
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								Repeat Pattern After(Days)
								</font>
							</div>
						</td>
						<td width="20%" class="tdfonthead">
							<div align="left">
								<input type="text" name="newPatternRepeatAfter" maxlength="2" size="3" tabindex="1" onkeypress="return validatePositiveIntegerOnly(this,event)" >
							</div>
						</td>
					</tr>
					
					<tr>
						<td width="50%" class="tdfonthead"></td>
						<td width="50%" class="tdfonthead">
							<div align="left">
								<img class="button" tabindex="1" src="/../HIS/hisglobal/images/buttons/GoNew.png" style="cursor: pointer; position: absolute;" onkeypress="repeatNewPattern(event)" onclick="repeatNewPattern(event)">		
							</div> 
						</td>
					</tr>
					
				</table>
				<br>
				
			</div>
	
	<html:hidden name="EmpRosterGenerationFB"   property="hmode" />
	<html:hidden name="EmpRosterGenerationFB"   property="serialNo"/>
    <html:hidden name="EmpRosterGenerationFB"   property="concatedValueToBeSaved"/>
	<html:hidden name="EmpRosterGenerationFB"   property="dutyType"/>
	<html:hidden name="EmpRosterGenerationFB"   property="currentYear"/>
	<html:hidden name="EmpRosterGenerationFB"   property="currentMonth"/>
	<html:hidden name="EmpRosterGenerationFB"   property="currentDate"/>
	<html:hidden name="EmpRosterGenerationFB"   property="empListToBeUpdated"/>
 	<html:hidden name="EmpRosterGenerationFB"   property="generatedRosterId"/>
	<html:hidden name="EmpRosterGenerationFB"   property="reliverEmpList"/>
	
	<input type="hidden" name="morningShiftId" value="<%=DutyRosterConfig.MORNING_SHIFT_TYPE_ID %>">
	<input type="hidden" name="eveningShiftId" value="<%=DutyRosterConfig.EVENING_SHIFT_TYPE_ID %>">
	<input type="hidden" name="nightShiftId" value="<%=DutyRosterConfig.NIGHT_SHIFT_TYPE_ID %>">
	<input type="hidden" name="dayShiftId" value="<%=DutyRosterConfig.DAY_SHIFT_TYPE_ID%>">
	<input type="hidden" name="expandRoster" value="1" id="expandRoster">
	
		</his:TransactionContainer>
				
   </html:form>
  </body>
</html>
		     
		   
		  