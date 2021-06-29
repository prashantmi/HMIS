<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%try{ %>
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
<his:css src="/hisglobal/css/Color.css" />
<his:css src="/hisglobal/css/master.css" />
<his:css src="/hisglobal/css/hisStyle.css" />
<his:css src="/hisglobal/css/hisStyleExt.css" />
<his:css src="/hisglobal/css/calendar-blue2.css"/>
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/masterutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<his:javascript src="/registration/js/registration.js" />
<his:javascript src="/dutyroster/js/dateWiseEmpDutyRoster.js" />
<his:javascript src="/registration/js/calendar.js" />
<his:javascript src="/registration/js/dateFunctions.js" />
<his:javascript src="/hisglobal/js/validationCommon.js" />
<his:javascript src="/hisglobal/js/validationCalls.js" />
<his:javascript src="/hisglobal/js/utilityFunctions.js" />


 <body onload="focusOnLoad()">

  <%
 //String systemDate=hisglobal.presentation.WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy HH:mm");
  String sysDate=hisglobal.presentation.WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy");
  
  
  %>
    
  <html:form action="/transaction/DateWiseEmpDutyRoster" > 
    
   
    
     <his:TransactionContainer>
     

       <his:TitleTag name="DateWise Employee Duty Roster">
       
     	 
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
								<bean:message key="rosterCategory"/>
					</font>
				  </div>
			    </td>
			    
			    
			    <td width="25%" class="tdfont">
			      <div align="left">        
	    	   
		  <html:select property="rosterCategory" name="DateWiseEmpRosterFB" tabindex="1" styleClass="textbox" onchange="submitPage('GET_ROSTERS')">
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
	    	   
		  <html:select property="rosterId" name="DateWiseEmpRosterFB" tabindex="1" styleClass="textbox" onchange="submitPage('GET_DUTY_AREA')">
			    <html:option value="-1">Select Value</html:option>
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
	    	   
		   <html:select property="areaCode" name="DateWiseEmpRosterFB" tabindex="1" styleClass="textbox" onchange="submitPage('GET_EMPLOYEES_CALENDAR')">
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
	    	   
		  <html:select property="designationId" name="DateWiseEmpRosterFB" tabindex="1" styleClass="textbox" onchange="submitPage('GET_EMPLOYEES_CALENDAR')" disabled="true">
			    <html:option value="-1">Select Value</html:option>
			       <logic:present  name="<%=DutyRosterConfig.LIST_OF_DESIGNATION_ON_THE_BASIS_OF_ROSTER_TYPE%>" >
			    	<html:option value="ALL">ALL</html:option>	
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
								<bean:message key="fromDate"/>&nbsp;
					</font>
				  </div>
			    </td>
			    
			    
			    <td width="25%" class="tdfont">
			      <div align="left">		
			      	       		
		   		<bean:define id="startDateId" name="DateWiseEmpRosterFB" property="startDate" type="java.lang.String"/>
	    	   	<his:date name="startDate" dateFormate="%d-%b-%Y" value="<%=startDateId%>" />
			    <html:hidden name="DateWiseEmpRosterFB" property="startDateOld" value="<%=startDateId%>"/>
			    		   
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
			          	   
		  		 <bean:define id="endDateId" name="DateWiseEmpRosterFB" property="endDate" type="java.lang.String"/>
				 <his:date name="endDate" dateFormate="%d-%b-%Y"  value="<%=endDateId%>" />	   
				<html:hidden name="DateWiseEmpRosterFB" property="endDateOld" value="<%=endDateId%>"/>		   
				
				   </div>
			     </td>  
			     
		</tr>
		
	</table>
		
		
		
		
      </his:ContentTag>
       
      <his:ContentTag>
      
     
<bean:define id="modeOfRosterId" name="DateWiseEmpRosterFB"  property="modeOfRoster"/>     

<logic:equal value="GET_EMPLOYEES_CALENDAR" name="DateWiseEmpRosterFB"  property="hmode">
<logic:equal value="NEW" name="DateWiseEmpRosterFB"  property="modeOfRoster">


<logic:present name="<%=DutyRosterConfig.MAP_OF_EMPLOYEE_DETAILS_FOR_DATEWISE_DUTY_ROSTER %>">

<table width="100%">
<tr>
<td class="tdfonthead"><div align="center"><input type="checkbox" onclick="selectAllEmp(this)"></div></td>
<td class="tdfonthead"><div align="center"><b>Employees</b></div></td>
<td class="tdfont"><div align="center">Shifts</div></td>

</tr>

<logic:iterate id="empDetailsId" name="<%=DutyRosterConfig.MAP_OF_EMPLOYEE_DETAILS_FOR_DATEWISE_DUTY_ROSTER %>" type="java.util.Map.Entry"  indexId="idx">

<tr>

<bean:define id="empDetailsKey" name="empDetailsId" property="key"></bean:define>
<bean:define id="empDetailsValue" name="empDetailsId" property="value"></bean:define>
<%
String[] empDetailsArray=((String)empDetailsKey).split("@");
String checkedFlag="";
String disabledFlag=" disabled='disabled' ";

if(!empDetailsArray[2].equals("-1"))
	{
	checkedFlag=" checked='checked' " ;
	disabledFlag="";
	}
	
%>

<td class="tdfont" width="25%"><div align="center"><input type="checkbox"  name="selectEmp"  value="<%=empDetailsArray[0]%>" id="selectEmp_<%=idx+1%>" onclick="selectEmployee(this)" <%=checkedFlag %> ></div></td>

<td class="tdfonthead" width="25%"><div align="center"><%=empDetailsArray[1]%></div></td>

<td class="tdfont" width="50%"><div align="center"><input type="button" name="<%=empDetailsKey%>" value="<%=empDetailsValue%>" id="<%=idx+1%>" onclick="changeShiftType(this)" onmouseover="showShiftName(this)" onmousemove="showShiftName(this)" <%=disabledFlag%> ></div></td>



</tr>



  </logic:iterate>






</table>


</logic:present>
</logic:equal> 
</logic:equal> 





<logic:equal value="MODIFY" name="DateWiseEmpRosterFB"  property="modeOfRoster">
<logic:equal value="MODIFY" name="DateWiseEmpRosterFB"  property="hmode">

<logic:present name="<%=DutyRosterConfig.MAP_OF_EMPLOYEE_DETAILS_FOR_DATEWISE_DUTY_ROSTER %>">
<table width="100%">
<tr>
<td class="tdfonthead"><div align="center"><input type="checkbox" onclick="selectAllEmp(this)"></div></td>
<td class="tdfonthead"><div align="center"><b>Employees</b></div></td>
<td class="tdfont"><div align="center">Shifts</div></td>

</tr>

<logic:iterate id="empDetailsId" name="<%=DutyRosterConfig.MAP_OF_EMPLOYEE_DETAILS_FOR_DATEWISE_DUTY_ROSTER %>" type="java.util.Map.Entry"  indexId="idx">

<tr>

<bean:define id="empDetailsKey" name="empDetailsId" property="key"></bean:define>
<bean:define id="empDetailsValue" name="empDetailsId" property="value"></bean:define>
<%
String[] empDetailsArray=((String)empDetailsKey).split("@");
String checkedFlag="";
String disabledFlag=" disabled='disabled' ";

if(!empDetailsArray[2].equals("-1"))
	{
	checkedFlag=" checked='checked' " ;
	disabledFlag="";
	}
	
%>

<td class="tdfont" width="25%"><div align="center"><input type="checkbox"  name="selectEmp"  value="<%=empDetailsArray[0]%>" id="selectEmp_<%=idx+1%>" onclick="selectEmployee(this)" <%=checkedFlag %> ></div></td>

<td class="tdfonthead" width="25%"><div align="center"><%=empDetailsArray[1]%></div></td>

<td class="tdfont" width="50%"><div align="center"><input type="button" name="<%=empDetailsKey%>" value="<%=empDetailsValue%>" id="<%=idx+1%>" onclick="changeShiftType(this)" onmouseover="showShiftName(this)" onmousemove="showShiftName(this)" <%=disabledFlag%> ></div></td>



</tr>



  </logic:iterate>






</table>


</logic:present>
</logic:equal>    
</logic:equal>   
      
      
<logic:equal value="REPORT" name="DateWiseEmpRosterFB"  property="hmode">
<logic:present name="<%=DutyRosterConfig.MAP_OF_EMPLOYEE_DETAILS_FOR_DATEWISE_DUTY_ROSTER %>">
<table width="100%">
<tr>


<td class="tdfonthead"><div align="center"><b>Employees</b></div></td>
<td class="tdfont"><div align="center">Shifts</div></td>

</tr>

<logic:iterate id="empDetailsId" name="<%=DutyRosterConfig.MAP_OF_EMPLOYEE_DETAILS_FOR_DATEWISE_DUTY_ROSTER %>" type="java.util.Map.Entry"  indexId="idx">

<tr>

<bean:define id="empDetailsKey" name="empDetailsId" property="key"></bean:define>
<bean:define id="empDetailsValue" name="empDetailsId" property="value"></bean:define>
<%
String[] empDetailsArray=((String)empDetailsKey).split("@");

	
%>



<td class="tdfonthead" width="25%"><div align="center"><%=empDetailsArray[1]%></div></td>

<td class="tdfont" width="50%"><div align="center"><%=empDetailsArray[5]%></div></td>



</tr>



  </logic:iterate>






</table>

 		<his:ButtonToolBarTag>
			<span id="saveDiv">
			      <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' tabindex="1" style="cursor: pointer" onclick="submitPage('GET_EMPLOYEES_CALENDAR')" onkeypress="if(event.keyCode==13) submitPage('GET_EMPLOYEES_CALENDAR')">
			</span>
		</his:ButtonToolBarTag>
		

</logic:present>
</logic:equal>        
 
 <bean:write name="DateWiseEmpRosterFB" property="calendar" filter="false"/>   
   
   <logic:notEmpty name="<%=DutyRosterConfig.LIST_OF_EMPLOYEES_WITH_ROSTER %>">
   
   <bean:write name="DateWiseEmpRosterFB" property="distinctRosterList" filter="false"/>
   
   </logic:notEmpty>
   
 
   
   
      </his:ContentTag>
      
     <bean:define id="modeOfRoster1" name="DateWiseEmpRosterFB"  property="modeOfRoster"/>     
    
      <%
      List listOfEmpWithReoster=(ArrayList)session.getAttribute(DutyRosterConfig.LIST_OF_EMPLOYEES_WITH_ROSTER);
      if(modeOfRoster1.equals("NEW") || (listOfEmpWithReoster!=null && listOfEmpWithReoster.size()==0)){ %>
      
        
       <his:ButtonToolBarTag>
			<span id="saveDiv">
			      <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>'  tabindex="1" style="cursor: pointer" onkeypress="if(event.keyCode==13) finalSubmit('SAVE')" onclick="finalSubmit('SAVE')">
				  <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' tabindex="1"  style="cursor: pointer" onclick="clearForm()" onkeypress="if(event.keyCode==13) clearForm()">
				  <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' tabindex="1" style="cursor: pointer" onclick="cancelFunc()" onkeypress="if(event.keyCode==13) cancelFunc()">
			</span>
		</his:ButtonToolBarTag>
		
		
		<%} %>
		
		<logic:equal name="DateWiseEmpRosterFB" property="modeOfRoster" value="MODIFY">
        
       <his:ButtonToolBarTag>
			<span id="saveDiv">
			      <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>'  tabindex="1" style="cursor: pointer" onkeypress="if(event.keyCode==13) finalSubmit('MODIFY_SAVE')" onclick="finalSubmit('MODIFY_SAVE')">
				  <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' tabindex="1"  style="cursor: pointer" onclick="clearForm()" onkeypress="if(event.keyCode==13) clearForm()">
				<%--   <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' tabindex="1" style="cursor: pointer" onclick="submitPage('CANCEL')" onkeypress="if(event.keyCode==13) submitPage('CANCEL')">
			    --%>    <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' tabindex="1" style="cursor: pointer" onclick="cancelFunc()" onkeypress="if(event.keyCode==13) cancelFunc()">
		
			</span>
			
		</his:ButtonToolBarTag>
		
			
		
		
      </logic:equal>
      
  
    <his:status/>
    
    <html:hidden name="DateWiseEmpRosterFB" property="hmode" />
	<html:hidden name="DateWiseEmpRosterFB" property="serialNo"/>
    <html:hidden name="DateWiseEmpRosterFB"   property="concatedValueToBeSaved"/>
	 <html:hidden name="DateWiseEmpRosterFB"   property="modeOfRoster"/>   
	 <html:hidden name="DateWiseEmpRosterFB"   property="generatedRosterId"/>
	 <html:hidden name="DateWiseEmpRosterFB"   property="sysDate" value="<%=sysDate %>" />
	 <html:hidden name="DateWiseEmpRosterFB"   property="isGenerated"  />

<input type="hidden" name="morningShiftId" value="<%=DutyRosterConfig.MORNING_SHIFT_TYPE_ID %>">
<input type="hidden" name="eveningShiftId" value="<%=DutyRosterConfig.EVENING_SHIFT_TYPE_ID %>">
<input type="hidden" name="nightShiftId" value="<%=DutyRosterConfig.NIGHT_SHIFT_TYPE_ID %>">
<input type="hidden" name="dayShiftId" value="<%=DutyRosterConfig.DAY_SHIFT_TYPE_ID%>">
	 </his:TransactionContainer>	
   </html:form>
  </body>
</html>
<%}catch(Exception e){e.printStackTrace();} %>		     
		   
		  