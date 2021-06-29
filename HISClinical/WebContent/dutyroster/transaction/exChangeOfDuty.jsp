<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@	taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="java.util.*" %>
<%@page import="hisglobal.hisconfig.Config"%>
<%@page import="dutyroster.DutyRosterConfig"%>
<%@page import="hisglobal.vo.RosterDtlVO"%>
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
<style type="text/css">
	@import url(../css/calendar-blue2.css);
</style>
<his:javascript src="/registration/js/popup.js" />
<his:javascript src="/registration/js/registration.js" />
<his:javascript src="/registration/js/calendar.js"/>
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/masterutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<his:javascript src="/hisglobal/js/dateFunctions.js" />
<his:javascript src="/hisglobal/js/validationCommon.js" />
<his:javascript src="/hisglobal/js/validationCalls.js" />
<his:javascript src="/registration/js/commonFunctions.js" />
<his:javascript src="/hisglobal/js/utilityFunctions.js" />
<his:javascript src="/dutyroster/js/exChangeOfDuty.js" />


<his:css src="/css/calendar-blue2.css" />

<body onload="focusOnLoad()">

  <%
  String sysDate=hisglobal.presentation.WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy");

  %>
    
  <html:form action="/transaction/ExChangeOfDuty" > 
    
   
    
     <his:TransactionContainer>
     

       <his:TitleTag name="Exchange of Duty">
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
		   <html:select property="year" name="ExChangeOfDutyFB" tabindex="1" styleClass="textbox">
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
		  <html:select property="month" name="ExChangeOfDutyFB" tabindex="1" styleClass="textbox">
			      <logic:present  name="<%=DutyRosterConfig.LIST_OF_MONTHS_EMPLOYEE_DUTY_ROSTER_MASTER%>" >
			          <html:options collection="<%=DutyRosterConfig.LIST_OF_MONTHS_EMPLOYEE_DUTY_ROSTER_MASTER%>" property="value"  labelProperty="label"/>
			       </logic:present>
		   </html:select>		   
				
				   </div>
			     </td>  
			     
		</tr>
				      
		      <tr>    
		      
						      
		      <tr>     
		      	     
		      <td width="25%" class="tdfonthead">
			     <div align="right">
			     <font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								*
					</font> 
					   <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="rosterMainCategory"/>
					</font>
				  </div>
			    </td>
			    
			    
			    <td width="25%" class="tdfont">
			      <div align="left">        
	    	   
		  <html:select property="rosterMainCatg" name="ExChangeOfDutyFB" tabindex="1" styleClass="textbox" onchange="submitPage('GET_ROSTER_CATEGORY')">
			    <html:option value="-1">Select Value</html:option>
			       <logic:present  name="<%=DutyRosterConfig.LIST_OF_ROSTER_MAIN_CATEGORY%>" >
			          <html:options collection="<%=DutyRosterConfig.LIST_OF_ROSTER_MAIN_CATEGORY%>" property="value"  labelProperty="label"/>
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
								<bean:message key="rosterCategory"/>
					</font>
				  </div>
			    </td>
			    
			    
			    <td width="25%" class="tdfont">
			      <div align="left">		          
	    	   
		  <html:select property="rosterCatg" name="ExChangeOfDutyFB" tabindex="1" styleClass="textbox" onchange="submitPage('GET_EMP')">
			    <html:option value="-1">Select Value</html:option>
			       <logic:present  name="<%=DutyRosterConfig.LIST_OF_ROSTER_CATEGORY%>" >
			          <html:options collection="<%=DutyRosterConfig.LIST_OF_ROSTER_CATEGORY%>" property="value"  labelProperty="label"/>
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
								<bean:message key="exchangeOfRequestBy"/>
					</font>
				  </div>
			    </td>
			    
			    
			    <td width="25%" class="tdfont">
			      <div align="left">        
	    	   
		  <html:select property="requestedEmp" name="ExChangeOfDutyFB" tabindex="1" styleClass="textbox" onchange="submitPage('GET_DUTY_LIST_REQUEST_BY_EMP')">
			    <html:option value="-1">Select Value</html:option>
			       <logic:present  name="<%=DutyRosterConfig.LIST_OF_REQUEST_BY_EMP_TO_BE_EXCHANGED%>" >
			          <html:options collection="<%=DutyRosterConfig.LIST_OF_REQUEST_BY_EMP_TO_BE_EXCHANGED%>" property="value"  labelProperty="label"/> 
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
								<bean:message key="mode"/>
					</font>
				  </div>
			    </td>
			    
			    
			    <td width="25%" class="tdfont">
			      <div align="left">		          
	    	   
		<html:radio property="mode" name="ExChangeOfDutyFB" value="1" onclick="checkMode()" tabindex="1"></html:radio>
		   		ExChange
		   		
		<html:radio property="mode" name="ExChangeOfDutyFB" value="2" onclick="checkMode()" tabindex="1"></html:radio>
		   		Change
		
				   </div>
			     </td>  
			    
			     
		 </tr>
		
		 
		
	</table>

	
	<logic:present name="<%=DutyRosterConfig.LIST_OF_REQUESTED_BY_EMP_DUTY_LIST_TO_BE_EXCHANGED %>"  >
	
	<table width="100%" border="0" cellspacing="1" cellpadding="0">
	
	
	<tr>
	
		<td class="tdfonthead" width="10%" >
			<div align="center" >
			 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
			    <bean:message key="select"/>
			    </font>
			</div>
		</td>
		
	<td class="tdfonthead" width="30%" >
		<div align="center">
			 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
				<bean:message key="day"/> 
			 </font>
		</div>
	</td>
	
	
	<td class="tdfonthead" width="30%" >
		<div align="center">
			 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
				<bean:message key="shiftName"/>
			 </font>	
		 </div>
	</td>
	
	
	
	<td class="tdfonthead" width="30%" >
	 	<div align="center">
	 		 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
	 			 <bean:message key="areaName"/>
	 		 </font>	  
	 	</div>
	 </td>

</tr>
	
	<logic:iterate id="rosterDtlVOId" name="<%=DutyRosterConfig.LIST_OF_REQUESTED_BY_EMP_DUTY_LIST_TO_BE_EXCHANGED %>" type="hisglobal.vo.RosterDtlVO" >
	
	<tr>
	<bean:define id="generatedRosterIdValue" name="rosterDtlVOId" property="generatedRosterId" type="java.lang.String"></bean:define>
	<bean:define id="endDateTimeIdValue" name="rosterDtlVOId" property="endDateTime" type="java.lang.String"></bean:define>
	<bean:define id="shiftIdValue" name="rosterDtlVOId" property="shiftId" type="java.lang.String"></bean:define>
	<bean:define id="shiftStartTimeId" name="rosterDtlVOId" property="shiftStartTime" type="java.lang.String"></bean:define>
	<bean:define id="shiftEndTimeId" name="rosterDtlVOId" property="shiftEndTime" type="java.lang.String"></bean:define>
	
	<bean:define id="rosterIdValue" name="rosterDtlVOId" property="rosterId" type="java.lang.String"></bean:define>
	<bean:define id="areaCodeId" name="rosterDtlVOId" property="areaCode" type="java.lang.String"></bean:define>
	<bean:define id="areaTypeCodeId" name="rosterDtlVOId" property="areaTypeCode" type="java.lang.String"></bean:define>
	<bean:define id="dutyTypeId" name="rosterDtlVOId" property="dutyType" type="java.lang.String"></bean:define>
	
	<%
			//////////generatedRosterIdValue  +       dateofDuty    +     shiftId     +  shiftStartTime    +   shiftEndTime   +  rosterId       +  areaCode    +  areaTypeCode	
	String radioValue=generatedRosterIdValue+"@"+endDateTimeIdValue+"@"+shiftIdValue+"@"+shiftStartTimeId+"@"+shiftEndTimeId+"@"+rosterIdValue+"@"+areaCodeId+"@"+areaTypeCodeId;
	String colorCode="";
	if(dutyTypeId.equals("3"))
		colorCode=DutyRosterConfig.COLOR_CODE_OF_EXCHANGED_DUTY;
	
	%>
	<td class="tdfont">
	  <div align="center" style="color: <%=colorCode %>">
	  <html:radio property="selectRequestedEmp" value="<%=radioValue%>" name="ExChangeOfDutyFB" onclick="submitRequestedEmp()"></html:radio>
	     
	  </div>
	</td>
	
	
	<td class="tdfont">
	    <div align="center" style="color: <%=colorCode %>">
	<bean:write name="rosterDtlVOId" property="startDateTime"/>
		</div>
	</td>
	
	
	<td class="tdfont">
		<div align="center" style="color: <%=colorCode %>">
	<bean:write name="rosterDtlVOId" property="shiftName"/>
		</div>
	</td>
	
	
	<td class="tdfont">
		<div align="center" style="color: <%=colorCode %>">
	<bean:write name="rosterDtlVOId" property="areaName"/>
		</div>
	</td>
	
	</tr>
	
	</logic:iterate>
	
	
	
	</table>

<table width="100%" border="0" cellspacing="1" cellpadding="0">

<tr>


<td width="25%" class="tdfonthead">
			     <div align="right">
			     <font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								*
					</font> 
					   <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="exchangeWith"/>
					</font>
				  </div>
 </td>
			    
			    
			    
 <td width="25%" class="tdfonthead">
			      <div align="left">        
	    	   
		  <html:select property="exchangedEmp" name="ExChangeOfDutyFB" tabindex="1" styleClass="textbox" onchange="submitPage('GET_DUTY_LIST_EXCHANGE_WITH_EMP')">
			    <html:option value="-1">Select Value</html:option>
			       <logic:present  name="<%=DutyRosterConfig.LIST_OF_REQUEST_WITH_EMP_TO_BE_EXCHANGED%>" >
			          <html:options collection="<%=DutyRosterConfig.LIST_OF_REQUEST_WITH_EMP_TO_BE_EXCHANGED%>" property="value"  labelProperty="label"/>
			       </logic:present>
		   </html:select>		   
				
				   </div>
</td>  
			     
			       <td width="10%" class="tdfonthead">
			     <div align="right">
					<font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								*
					</font> 
				    <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="month"/>
					</font>
				  </div>
			    </td>
			    
			    
			    <td width="40%" class="tdfont">
			      <div align="left">		          
	    	   
		<html:radio property="exchangeWithMonth" name="ExChangeOfDutyFB" value="C" onclick="submitFormData()" tabindex="1"></html:radio>
		   		 	<bean:message key="selectedMonth"/>
		<html:radio property="exchangeWithMonth" name="ExChangeOfDutyFB" value="N" onclick="submitFormData()" tabindex="1"></html:radio>
		   			<bean:message key="nextToSelectedMonth"/>
		
				   </div>
			     </td>  
			     
			    

</tr>

</table>
	
	</logic:present>
	
	
	
	
	
	
<logic:present name="<%=DutyRosterConfig.LIST_OF_REQUESTED_WITH_EMP_DUTY_LIST_TO_BE_EXCHANGED %>"  >
	
	<table width="100%" border="0" cellspacing="1" cellpadding="0">
	
	
	<tr>
	
		<td class="tdfonthead" width="10%" >
			<div align="center">
				 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
			    	<bean:message key="select"/>
				</font>
			</div>
		</td>
		
	<td class="tdfonthead" width="30%" >
		<div align="center">
			 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
				<bean:message key="day"/>
			 </font>	 
		</div>
	</td>
	
	
	<td class="tdfonthead" width="30%" >
		<div align="center">
			 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
				<bean:message key="shiftName"/>
			 </font>	
		 </div>
	</td>
	
	
	
	<td class="tdfonthead" width="30%" >
	 	<div align="center">
	 		 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
	 			<bean:message key="areaName"/>
	 		 </font>	 
	 	</div>
	 </td>

</tr>
	
	<logic:iterate id="rosterDtlVOId" name="<%=DutyRosterConfig.LIST_OF_REQUESTED_WITH_EMP_DUTY_LIST_TO_BE_EXCHANGED %>" type="hisglobal.vo.RosterDtlVO" >
	
	<tr>
	<bean:define id="generatedRosterIdValue" name="rosterDtlVOId" property="generatedRosterId" type="java.lang.String"></bean:define>
	<bean:define id="endDateTimeIdValue" name="rosterDtlVOId" property="endDateTime" type="java.lang.String"></bean:define>
	<bean:define id="shiftIdValue" name="rosterDtlVOId" property="shiftId" type="java.lang.String"></bean:define>
	<bean:define id="shiftStartTimeId" name="rosterDtlVOId" property="shiftStartTime" type="java.lang.String"></bean:define>
	<bean:define id="shiftEndTimeId" name="rosterDtlVOId" property="shiftEndTime" type="java.lang.String"></bean:define>
	
	<bean:define id="rosterIdValue" name="rosterDtlVOId" property="rosterId" type="java.lang.String"></bean:define>
	<bean:define id="areaCodeId" name="rosterDtlVOId" property="areaCode" type="java.lang.String"></bean:define>
	<bean:define id="areaTypeCodeId" name="rosterDtlVOId" property="areaTypeCode" type="java.lang.String"></bean:define>
	<bean:define id="dutyTypeId" name="rosterDtlVOId" property="dutyType" type="java.lang.String"></bean:define>
	
	<%
			//////////generatedRosterIdValue  +       dateofDuty    +     shiftId     +  shiftStartTime    +   shiftEndTime   +  rosterId       +  areaCode    +  areaTypeCode	
	String radioValue=generatedRosterIdValue+"@"+endDateTimeIdValue+"@"+shiftIdValue+"@"+shiftStartTimeId+"@"+shiftEndTimeId+"@"+rosterIdValue+"@"+areaCodeId+"@"+areaTypeCodeId;
	String colorCode="";
	if(dutyTypeId.equals("3"))
		colorCode=DutyRosterConfig.COLOR_CODE_OF_EXCHANGED_DUTY;
	
	%>
	

	

	<td class="tdfont">
	 <div align="center" style="color: <%=colorCode %>">
	  <html:radio property="selectExchangedEmp" value="<%=radioValue%>" name="ExChangeOfDutyFB"></html:radio>
	     
	  </div>
	</td>
	
	
	<td class="tdfont">
	  <div align="center" style="color: <%=colorCode %>">
	<bean:write name="rosterDtlVOId" property="startDateTime"/>
		</div>
	</td>
	
	
	<td class="tdfont">
		 <div align="center" style="color: <%=colorCode %>">
	<bean:write name="rosterDtlVOId" property="shiftName"/>
		</div>
	</td>
	
	
	<td class="tdfont">
		 <div align="center" style="color: <%=colorCode %>">
	<bean:write name="rosterDtlVOId" property="areaName"/>
		</div>
	</td>
	
	</tr>
	
	</logic:iterate>
	
	
	<tr>
	
	<td class="tdfonthead">
	  <div align="right">
	     
	  </div>
	</td>
	
	<td class="tdfonthead">
	  <div align="right">
	  <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
	 <bean:message key="reason"/>&nbsp;
	  </font>   
	  </div>
	</td>
	
	
	<td class="tdfont">
	    <div align="left">
	<html:textarea property="reason" name="ExChangeOfDutyFB" cols="40" tabindex="1" onkeypress="return CheckMaxLength(event,this,50,3)"></html:textarea>
		</div>
	</td>
	
	<td class="tdfont">
	    <div align="left">
	
		</div>
	</td>
	
	</tr>
	
	
	
	
	
	</table>


	
	</logic:present>
	
	
		
	
	
<logic:present name="<%=DutyRosterConfig.LIST_OF_REQUESTED_WITH_EMP_DUTY_LIST_TO_BE_CHANGED %>"  >
	
	<table width="100%" border="0" cellspacing="1" cellpadding="0">
	
	
	<tr>
	
		<td class="tdfonthead" width="10%" >
			<div align="center">
				 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
			    	<bean:message key="select"/>
				</font>
			</div>
		</td>
		
	<td class="tdfonthead" width="30%" >
		<div align="center">
			 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
				<bean:message key="day"/>
			 </font>	 
		</div>
	</td>
	
	
	<td class="tdfonthead" width="30%" >
		<div align="center">
			 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
				<bean:message key="shiftName"/>
			 </font>	
		 </div>
	</td>
	
	
	
	<td class="tdfonthead" width="30%" >
	 	<div align="center">
	 		 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
	 			<bean:message key="areaName"/>
	 		 </font>	 
	 	</div>
	 </td>

</tr>
	
	<logic:iterate id="rosterDtlVOId" name="<%=DutyRosterConfig.LIST_OF_REQUESTED_WITH_EMP_DUTY_LIST_TO_BE_CHANGED %>" type="hisglobal.vo.RosterDtlVO" >
	
	<tr>
	<bean:define id="generatedRosterIdValue" name="rosterDtlVOId" property="generatedRosterId" type="java.lang.String"></bean:define>
	<bean:define id="endDateTimeIdValue" name="rosterDtlVOId" property="endDateTime" type="java.lang.String"></bean:define>
	<bean:define id="shiftIdValue" name="rosterDtlVOId" property="shiftId" type="java.lang.String"></bean:define>
	<bean:define id="shiftStartTimeId" name="rosterDtlVOId" property="shiftStartTime" type="java.lang.String"></bean:define>
	<bean:define id="shiftEndTimeId" name="rosterDtlVOId" property="shiftEndTime" type="java.lang.String"></bean:define>
	
	<bean:define id="rosterIdValue" name="rosterDtlVOId" property="rosterId" type="java.lang.String"></bean:define>
	<bean:define id="areaCodeId" name="rosterDtlVOId" property="areaCode" type="java.lang.String"></bean:define>
	<bean:define id="areaTypeCodeId" name="rosterDtlVOId" property="areaTypeCode" type="java.lang.String"></bean:define>
	
	<%
//////////	      generatedRosterIdValue  +       dateofDuty    +     shiftId     +  shiftStartTime    +   shiftEndTime   +  rosterId       +  areaCode    +  areaTypeCode              	
String radioValue=generatedRosterIdValue+"@"+endDateTimeIdValue+"@"+shiftIdValue+"@"+shiftStartTimeId+"@"+shiftEndTimeId+"@"+rosterIdValue+"@"+areaCodeId+"@"+areaTypeCodeId;
	
	%>
	

	<td class="tdfont">
	  <div align="center">
	  <html:radio property="selectExchangedEmp" value="<%=radioValue%>" name="ExChangeOfDutyFB" disabled="true"></html:radio>
	     
	  </div>
	</td>
	
	
	<td class="tdfont">
	    <div align="center">
	<bean:write name="rosterDtlVOId" property="startDateTime"/>
		</div>
	</td>
	
	
	<td class="tdfont">
		<div align="center">
	<bean:write name="rosterDtlVOId" property="shiftName"/>
		</div>
	</td>
	
	
	<td class="tdfont">
		<div align="center">
	<bean:write name="rosterDtlVOId" property="areaName"/>
		</div>
	</td>
	
	</tr>
	
	</logic:iterate>
	
	
	
	
	
	
	
	
	</table>


	
	</logic:present>
		
<logic:equal value="2" name="ExChangeOfDutyFB" property="mode">
	
<table width="100%" border="0" cellspacing="1" cellpadding="0">
	
	<tr>
	
	<td class="tdfonthead" width="10%">
	  <div align="right">
	     
	  </div>
	</td>
	
	<td class="tdfonthead" width="30%">
	  <div align="right">
	  <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
	 <bean:message key="reason"/>&nbsp;
	  </font>   
	  </div>
	</td>
	
	
	<td class="tdfont" width="30%">
	    <div align="left">
	<html:textarea property="reason"  name="ExChangeOfDutyFB" cols="40" tabindex="1"  onkeypress="return CheckMaxLength(event,this,50,3)"></html:textarea>
		</div>
	</td>
	
	<td class="tdfont" width="30%">
	    <div align="left">
	
		</div>
	</td>
	
	</tr>
</table>	

</logic:equal>
	
      </his:ContentTag>
       <his:ButtonToolBarTag>
			<span id="saveDiv">
			      <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>' id="saveButton"   tabindex="1" style="cursor: pointer" onkeypress="if(event.keyCode==13) finalSubmit('SAVE')" onclick="finalSubmit('SAVE')">
				  <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' tabindex="1"  style="cursor: pointer" onclick="clearForm()" onkeypress="if(event.keyCode==13) clearForm()">
				  <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' tabindex="1" style="cursor: pointer" onclick="cancelFunc()" onkeypress="if(event.keyCode==13) cancelFunc()">
			</span>
		</his:ButtonToolBarTag>
		    <his:status/>
        </his:TransactionContainer>
      
    
    
    <html:hidden name="ExChangeOfDutyFB"   property="hmode" />
    <html:hidden name="ExChangeOfDutyFB"   property="concatedData" />
	<html:hidden name="ExChangeOfDutyFB"   property="sysDate" value="<%=sysDate %>" />
	<html:hidden name="ExChangeOfDutyFB"   property="selectRequestedEmp" />
    		
   </html:form>
  </body>
</html>
		     
		   
		  