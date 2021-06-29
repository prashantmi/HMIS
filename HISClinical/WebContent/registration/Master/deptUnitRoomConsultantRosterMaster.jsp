<%
try{
%>	  
<%@page autoFlush="true" %>
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>

<%@page import="hisglobal.hisconfig.Config"%>
<%@page import="org.apache.struts.tiles.ComponentContext"%>
<%@page import="hisglobal.presentation.WebUTIL"%>
<%@page import="java.util.Date"%>
<%@page import="registration.master.controller.util.DeptUnitRoomConsultantRosterMstUTIL"%>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/master.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/>
<his:css src="/hisglobal/css/calendar-blue2.css"/>

<his:javascript src="/registration/js/registration.js"/>
<his:javascript src="/registration/js/validationCalls.js"/>
<his:javascript src="/registration/js/validationCommon.js"/>
<his:javascript src="/registration/js/commonFunctions.js"/>
<his:javascript src="/registration/js/dateFunctions.js" />
<his:javascript src="/registration/js/popup.js"/>
<his:javascript src="/hisglobal/js/DateValidation.js"/>
<his:javascript src="/registration/js/deptUnitRoomConsultantRosterMaster.js"/>
<his:javascript src="/registration/js/calendar.js"/>

			<bean:define id="shiftDoubleArrayId" name="deptUnitRoomConsultantRosterMstFB" property="shift" type="java.lang.String[][]"/>
	
			<bean:define id="listFor1stWeekOfMonthId" name="deptUnitRoomConsultantRosterMstFB" property="listFor1stWeekOfMonth" type="java.util.List"/>
	    	<bean:define id="listFor2ndWeekOfMonthId" name="deptUnitRoomConsultantRosterMstFB" property="listFor2ndWeekOfMonth" type="java.util.List"/>
	    	<bean:define id="listFor3rdWeekOfMonthId" name="deptUnitRoomConsultantRosterMstFB" property="listFor3rdWeekOfMonth" type="java.util.List"/>
	    	<bean:define id="listFor4thWeekOfMonthId" name="deptUnitRoomConsultantRosterMstFB" property="listFor4thWeekOfMonth" type="java.util.List"/>
	    	<bean:define id="listFor5thWeekOfMonthId" name="deptUnitRoomConsultantRosterMstFB" property="listFor5thWeekOfMonth" type="java.util.List"/>
	    
	   
	   
	   		<bean:define id="consultantRosterFor1stWeekId" name="deptUnitRoomConsultantRosterMstFB" property="consultantRosterFor1stWeek" type="java.lang.String[]"/>
	    	<bean:define id="consultantRosterFor2ndWeekId" name="deptUnitRoomConsultantRosterMstFB" property="consultantRosterFor2ndWeek" type="java.lang.String[]"/>
	    	<bean:define id="consultantRosterFor3rdWeekId" name="deptUnitRoomConsultantRosterMstFB" property="consultantRosterFor3rdWeek" type="java.lang.String[]"/>
	    	<bean:define id="consultantRosterFor4thWeekId" name="deptUnitRoomConsultantRosterMstFB" property="consultantRosterFor4thWeek" type="java.lang.String[]"/>
	    	<bean:define id="consultantRosterFor5thWeekId" name="deptUnitRoomConsultantRosterMstFB" property="consultantRosterFor5thWeek" type="java.lang.String[]"/>
	    	
	    	
	    	
	    	<bean:define id="listOfAllExceptSelectedUnit1stWeekOfMonthId" name="deptUnitRoomConsultantRosterMstFB" property="listOfAllExceptSelectedUnit1stWeekOfMonth" type="java.util.List"/>
	    	<bean:define id="listOfAllExceptSelectedUnit2ndWeekOfMonthId" name="deptUnitRoomConsultantRosterMstFB" property="listOfAllExceptSelectedUnit2ndWeekOfMonth" type="java.util.List"/>
	    	<bean:define id="listOfAllExceptSelectedUnit3rdWeekOfMonthId" name="deptUnitRoomConsultantRosterMstFB" property="listOfAllExceptSelectedUnit3rdWeekOfMonth" type="java.util.List"/>
	    	<bean:define id="listOfAllExceptSelectedUnit4thWeekOfMonthId" name="deptUnitRoomConsultantRosterMstFB" property="listOfAllExceptSelectedUnit4thWeekOfMonth" type="java.util.List"/>
	    	<bean:define id="listOfAllExceptSelectedUnit5thWeekOfMonthId" name="deptUnitRoomConsultantRosterMstFB" property="listOfAllExceptSelectedUnit5thWeekOfMonth" type="java.util.List"/>
	    
	   
	   
	
	    	<bean:define id="updateDataFor1stWeekId" name="deptUnitRoomConsultantRosterMstFB" property="updateDataFor1stWeek" type="java.lang.String[]"/>
	    	<bean:define id="updateDataFor2ndWeekId" name="deptUnitRoomConsultantRosterMstFB" property="updateDataFor2ndWeek" type="java.lang.String[]"/>
	    	<bean:define id="updateDataFor3rdWeekId" name="deptUnitRoomConsultantRosterMstFB" property="updateDataFor3rdWeek" type="java.lang.String[]"/>
	    	<bean:define id="updateDataFor4thWeekId" name="deptUnitRoomConsultantRosterMstFB" property="updateDataFor4thWeek" type="java.lang.String[]"/>
	    	<bean:define id="updateDataFor5thWeekId" name="deptUnitRoomConsultantRosterMstFB" property="updateDataFor5thWeek" type="java.lang.String[]"/>
 

<%@ page import ="registration.*" %>

<script type="text/javascript">

	
</script>
<html:form action="/master/deptUnitRoomConsultantRosterMaster">
<his:TransactionContainer>	

	<his:TitleTag name="Department Unit Room Consultant Roster Master">
	</his:TitleTag>
<his:ContentTag>

			
	  
	  

<table width="100%" cellpadding="1" cellspacing="1">
	<tr>
		<td width="25%" class="tdfonthead">
		<div align="right">
		<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		<bean:message key="department"/>
		</font>
		</div>
		</td>
		
		<td width="25%" class="tdfont">
		<html:select property="departmentCode" tabindex="1" styleClass="regCbo" onchange="if(this.value!='-1') submitForm('GET_DEPT_UNITS');">		   		  
		<html:option value="-1">Select Value</html:option>
		<html:options collection = "<%=Config.ESSENTIALBO_OPTION_DEPARTMENT%>" property = "value" labelProperty = "label"/>
		</html:select>
		</td>
		
		<td width="25%" class="tdfonthead">
		<div align="right">
		<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		<bean:message key="unit"/>
		</font>
		</div>
		</td>
		
		<td width="25%" class="tdfont">
		<html:select property="departmentUnitCode" tabindex="1" styleClass="regCbo" onchange="if(this.value!='-1') submitForm('GET_ROOM_AND_CONSULTANTS');"> 		  
		<html:option value="-1">Select Value</html:option>
		<html:options collection = "<%=Config.ESSENTIALBO_OPTION_DEPT_UNIT %>" property = "value" labelProperty = "label"/>
		</html:select>
		</td>

	</tr>
		
	<tr>
	
		
		<td width="25%" class="tdfonthead">
		<div align="right">
		<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		<bean:message key="roomName"/>
		</font>
		</div>
		</td>
		
		<td width="25%" class="tdfont">
		<html:select property="roomCode" tabindex="1" styleClass="regCbo" onchange="if(this.value!='-1') changeRoom();"> 		  
		<html:option value="-1">Select Value</html:option>
		<logic:present name="<%=RegistrationConfig.ESSENTIAL_OPTION_ROOM_LIST %>">
		<html:options collection = "<%=RegistrationConfig.ESSENTIAL_OPTION_ROOM_LIST%>" property = "value" labelProperty = "label"/>
		</logic:present>
		</html:select>
		</td>
		
		<td width="25%" class="tdfonthead">
		<div align="right">
		<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		<bean:message key="consultant"/>
		</font>
		</div>
		</td>
		
		<td width="25%" class="tdfont">
		<html:select property="consultantEmpId" tabindex="1" styleClass="regCbo" onchange="if(this.value!='-1') checkRoomSelected('GET_UNIT_CONSULTANT_ROSTER');"> 		  
		<html:option value="-1">Select Value</html:option>
		<html:option value="0">SR/JR</html:option>
		<logic:present name="<%=RegistrationConfig.ESSENTIAL_OPTION_CONSTULTANT_HOU_1_2_LIST %>">
		<html:options collection = "<%=RegistrationConfig.ESSENTIAL_OPTION_CONSTULTANT_HOU_1_2_LIST%>" property = "value" labelProperty = "label"/>
		</logic:present>
		</html:select>
		</td>
		
</tr>
</table>
</his:ContentTag>

<his:statusTransactionInProcess>
<his:SubTitleTag>
	<his:name>
	<bean:message key="titleRosterDetails"/>
	</his:name>	
	<%-- 
	
	<table width="100%">
	  <tr>
	  	<td width="50%">
	  	</td>
		<td width="20%">
			<div align="right">
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			<b>
			  <bean:message key="effectiveDate"/>
			</b>
			</font>
			</div>
		</td>
		<td width="30%">
			<div align="left">
			<logic:empty name="deptUnitRoomConsultantRosterMstFB" property="effectDate">
				<bean:define name="deptUnitRoomConsultantRosterMstFB" property="effectDate" id="effectiveDate" type="java.lang.String"/>
				<%
					effectiveDate = WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy");
				
				%>				
				
				<his:date name='<%="effectDate"%>' dateFormate="%d-%b-%Y" value='<%=effectiveDate%>'/>
			</logic:empty>			
			
			
			<logic:notEmpty name="deptUnitRoomConsultantRosterMstFB" property="effectDate">
				<bean:define name="deptUnitRoomConsultantRosterMstFB" property="effectDate" id="effectiveDate" type="java.lang.String"/>
 	      		<his:date name='<%="effectDate"%>' dateFormate="%d-%b-%Y" value='<%=effectiveDate%>'/>
       		</logic:notEmpty>
			</div>
		</td>
	  </tr>
	</table>
--%>	
	
</his:SubTitleTag>

<his:ContentTag>
<table width="100%" cellspacing="1" cellpading="1">
		<tr>
		<td colspan="1" width="17%" Class="tdfonthead">
		<b>
		<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		<div align="center">
		<bean:message key="daysOfWeek"/>
		</div>
		</font>
		</b>
		</td>
		
		<td colspan="5" Class="tdfonthead">
		<b>
		<div align="center">
		<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		<bean:message key="weekOfMonth"/>
		</font>
		</div>
		</b>
		</td>
		
		<td colspan="4" Class="tdfonthead">
		<b>
		<center>
		<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		<bean:message key="shift"/>
		</font>
		</center>
		</b>
		</td>
		</tr>
	
	<% int noOfShifts = DeptUnitRoomConsultantRosterMstUTIL.getNumberOfShifts(request);
	
	 int indexWeek1=0;
	 int indexWeek2=0;
	 int indexWeek3=0;
	 int indexWeek4=0;
	 int indexWeek5=0;
	%>
	<tr>
	  <td class="tdfont" style="border-top:outset black 2px; border-bottom:inset black 2px;">&nbsp;</td>
	  <td class="tdfont" style="border-top:outset black 2px; border-bottom:inset black 2px;"><div align="center"><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">1st <bean:message key="week"/></font></div></td>
	  <td class="tdfont" style="border-top:outset black 2px; border-bottom:inset black 2px;"><div align="center"><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">2nd <bean:message key="week"/></font></div></td>
	  <td class="tdfont" style="border-top:outset black 2px; border-bottom:inset black 2px;"><div align="center"><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">3rd <bean:message key="week"/></font></div></td>
	  <td class="tdfont" style="border-top:outset black 2px; border-bottom:inset black 2px;"><div align="center"><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">4th <bean:message key="week"/></font></div></td>
	  <td class="tdfont" style="border-top:outset black 2px; border-bottom:inset black 2px;"><div align="center"><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">5th <bean:message key="week"/></font></div></td>
	  
	  
	   	<logic:iterate id="list" name="<%=RegistrationConfig.ESSENTIALBO_OPTION_SHIFT%>" type="hisglobal.vo.ShiftMasterVO">	
		  	<td Class="tdfont" style="border-top:outset black 2px; border-bottom:inset black 2px;">
		  		<div align="center">
		  		<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"><bean:write name='list' property='shiftDescription'/></font></div>
		  		<input type="hidden" name="startTime" value="<bean:write name='list' property='shiftStartTime'/>">
		  		<input type="hidden" name="endTime" value="<bean:write name='list' property='shiftEndTime'/>">
		  	</td>
	  	</logic:iterate>
	  
	  
    </tr>
	<bean:define name="deptUnitRoomConsultantRosterMstFB" id="currForm" type="registration.master.controller.fb.DeptUnitRoomConsultantRosterMstFB"/>
	<%for(int di=1; di<= 7; di++){ 
		//for each Day of Week	
		int noOfRows = DeptUnitRoomConsultantRosterMstUTIL.getNumOfRows4aDOfWinJSP(currForm, request, di);

		 
		for(int ri=0; ri<noOfRows; ri++){
			//for each row in a Day
		
	%>
	<tr>
		<td class="tdfont">
			<table width="100%">
				<tr>
					<td>
					<div align="left">
						<%if(ri==0){  String tmpRowDay = "dOw"+di; %>
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:message key="<%=tmpRowDay%>"/>
							</font>
						<%}%>
					</div>
					</td>
					
					
					<td Class="tdfont" width="20%">
				    	<div align="left">
				    		<input type="checkbox" name="selectAll<%=di%>" value="<%=di+"_"+ri%>" onclick="selectAllWeek(this)">
				    	</div>
			   		 </td>	
			   		
			   		 
				</tr>
			</table>
		</td>
		
	    <td Class="tdfont">
	    	<div align="center"><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	    	
	    	
	    	
	    		
      		
      		
      	<% 
	    		if(listFor1stWeekOfMonthId.contains(di+"_"+ri) && listOfAllExceptSelectedUnit1stWeekOfMonthId.contains(di+"_"+ri))
	    		{
	    		%>
	    		<%--<html:hidden name="deptUnitRoomConsultantRosterMstFB" property="updateDataFor1stWeek" value="<%=updateDataFor1stWeekId[indexWeek1]%>"/>
	    	 	<font color="red">NA</font> 
      		  --%>
      		  <his:checkbox name="deptUnitRoomConsultantRosterMstFB"  property="consultantRosterFor1stWeek" value='<%=di+"_"+ri %>'  /> 
	    		<html:hidden name="deptUnitRoomConsultantRosterMstFB" property="updateDataFor1stWeek" value="<%=updateDataFor1stWeekId[indexWeek1]%>"/>
      		  
      		  <%
      				indexWeek1++;
      		     }
	    		else 
	    		if(listFor1stWeekOfMonthId.contains(di+"_"+ri) && !listOfAllExceptSelectedUnit1stWeekOfMonthId.contains(di+"_"+ri))
	    		{
	    		%>
	    		  
	    		<his:checkbox name="deptUnitRoomConsultantRosterMstFB"  property="consultantRosterFor1stWeek" value='<%=di+"_"+ri %>'  /> 
	    		<html:hidden name="deptUnitRoomConsultantRosterMstFB" property="updateDataFor1stWeek" value="<%=updateDataFor1stWeekId[indexWeek1]%>"/>

      		  <%
      				indexWeek1++;
      		     }
      	%>
      		
      		
      		
      		
	          		
      		
      		</font></div>
        </td>
        <td Class="tdfont">
	    	<div align="center"><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	      		
	      		
      		  
      		  
      		  <% 
	    		if(listFor2ndWeekOfMonthId.contains(di+"_"+ri) && listOfAllExceptSelectedUnit2ndWeekOfMonthId.contains(di+"_"+ri))
	    		{
	    		%><%--
	    		 <html:hidden name="deptUnitRoomConsultantRosterMstFB" property="updateDataFor2ndWeek" value="<%=updateDataFor2ndWeekId[indexWeek2]%>"/>
	    	 	<font color="red">NA</font> 
      		  --%>
      		  <his:checkbox name="deptUnitRoomConsultantRosterMstFB"  property="consultantRosterFor2ndWeek" value='<%=di+"_"+ri %>'  /> 
	       	    			
	    		 <html:hidden name="deptUnitRoomConsultantRosterMstFB" property="updateDataFor2ndWeek" value="<%=updateDataFor2ndWeekId[indexWeek2]%>"/>
      		  <%
      		  
      				indexWeek2++;
      		     }
	    			else
	    		if(listFor2ndWeekOfMonthId.contains(di+"_"+ri) && !listOfAllExceptSelectedUnit2ndWeekOfMonthId.contains(di+"_"+ri))
	    		{		
	    			
	    	  %>
	    	  
      			<his:checkbox name="deptUnitRoomConsultantRosterMstFB"  property="consultantRosterFor2ndWeek" value='<%=di+"_"+ri %>'  /> 
	       	    			
	    		 <html:hidden name="deptUnitRoomConsultantRosterMstFB" property="updateDataFor2ndWeek" value="<%=updateDataFor2ndWeekId[indexWeek2]%>"/> 
      		  <%
      				indexWeek2++;
      		     }
      		  %>
      		  
	      		
	      	
      	
      	
      		</font></div>
        </td>
        <td Class="tdfont">
	    	<div align="center"><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	      		
	      	<% 
	    		if(listFor3rdWeekOfMonthId.contains(di+"_"+ri) && listOfAllExceptSelectedUnit3rdWeekOfMonthId.contains(di+"_"+ri))
	    		{
	    		%>
	    		<%--<html:hidden name="deptUnitRoomConsultantRosterMstFB" property="updateDataFor3rdWeek" value="<%=updateDataFor3rdWeekId[indexWeek3]%>"/>
	    	 	<font color="red">NA</font> 
      		  --%>
      		  <his:checkbox name="deptUnitRoomConsultantRosterMstFB"  property="consultantRosterFor3rdWeek" value='<%=di+"_"+ri %>'  /> 
	       	    			
	    		<html:hidden name="deptUnitRoomConsultantRosterMstFB" property="updateDataFor3rdWeek" value="<%=updateDataFor3rdWeekId[indexWeek3]%>"/>
      		  <%
      				indexWeek3++;
      		     }
	    			else
	    		if(listFor3rdWeekOfMonthId.contains(di+"_"+ri) && !listOfAllExceptSelectedUnit3rdWeekOfMonthId.contains(di+"_"+ri))
	    		{		
	    		 		
	    	  %>	
	    	    
      			<his:checkbox name="deptUnitRoomConsultantRosterMstFB"  property="consultantRosterFor3rdWeek" value='<%=di+"_"+ri %>'  /> 
	       	    			
	    		<html:hidden name="deptUnitRoomConsultantRosterMstFB" property="updateDataFor3rdWeek" value="<%=updateDataFor3rdWeekId[indexWeek3]%>"/> 
      		  <%
      				indexWeek3++;
      		     }
      		  %>
	      		
	      		
	      		
      		</font></div>
        </td>
	    <td Class="tdfont">
	    	<div align="center"><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	      		
	      	  <% 
	    		if(listFor4thWeekOfMonthId.contains(di+"_"+ri) && listOfAllExceptSelectedUnit4thWeekOfMonthId.contains(di+"_"+ri))
	    		{
	    		%>
	    		<%--<html:hidden name="deptUnitRoomConsultantRosterMstFB" property="updateDataFor4thWeek" value="<%=updateDataFor4thWeekId[indexWeek4]%>"/>
	    	 	<font color="red">NA</font>
      		  --%>
      		  <his:checkbox name="deptUnitRoomConsultantRosterMstFB"  property="consultantRosterFor4thWeek" value='<%=di+"_"+ri %>'  /> 
	       	    			
	    		<html:hidden name="deptUnitRoomConsultantRosterMstFB" property="updateDataFor4thWeek" value="<%=updateDataFor4thWeekId[indexWeek4]%>"/>
      		  <%
      			indexWeek4++;
      		     }
	    			else
	    		if(listFor4thWeekOfMonthId.contains(di+"_"+ri) && !listOfAllExceptSelectedUnit4thWeekOfMonthId.contains(di+"_"+ri))
	    		{		
	    		 		
	    	  %>	
	    	    
      			<his:checkbox name="deptUnitRoomConsultantRosterMstFB"  property="consultantRosterFor4thWeek" value='<%=di+"_"+ri %>'  /> 
	       	    			
	    		<html:hidden name="deptUnitRoomConsultantRosterMstFB" property="updateDataFor4thWeek" value="<%=updateDataFor4thWeekId[indexWeek4]%>"/> 
      		  <%
      			indexWeek4++;
      		     }
      		  %>
	      		
	      	
      		  
      		  
	      		
      		</font></div>
        </td>
	    <td Class="tdfont">
	    	<div align="center">
	    	<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	    	
	    	  <% 
	    		if(listFor5thWeekOfMonthId.contains(di+"_"+ri) && listOfAllExceptSelectedUnit5thWeekOfMonthId.contains(di+"_"+ri))
	    		{
	    		%>
	    		<%--<html:hidden name="deptUnitRoomConsultantRosterMstFB" property="updateDataFor5thWeek" value="<%=updateDataFor5thWeekId[indexWeek5]%>"/>
	    	 	<font color="red">NA</font>
      		  --%>
      		  <his:checkbox name="deptUnitRoomConsultantRosterMstFB"  property="consultantRosterFor5thWeek" value='<%=di+"_"+ri %>'  /> 
	       	    			
	    		<html:hidden name="deptUnitRoomConsultantRosterMstFB" property="updateDataFor5thWeek" value="<%=updateDataFor5thWeekId[indexWeek5]%>"/>
      		  <%
      			  indexWeek5++;
      		     }
	    			else
	    		if(listFor5thWeekOfMonthId.contains(di+"_"+ri) && !listOfAllExceptSelectedUnit5thWeekOfMonthId.contains(di+"_"+ri))
	    		{		
	    				
	    	  %>	
	    	    <his:checkbox name="deptUnitRoomConsultantRosterMstFB"  property="consultantRosterFor5thWeek" value='<%=di+"_"+ri %>'  /> 
	       	    			
	    		<html:hidden name="deptUnitRoomConsultantRosterMstFB" property="updateDataFor5thWeek" value="<%=updateDataFor5thWeekId[indexWeek5]%>"/> 
      		  <%
      			indexWeek5++;
      		     }
      		  %>
	    	
      		  	
      		
      		  
	      		
      		</font>
      		</div>
        </td>
     
		<%for(int si=0; si<noOfShifts; si++){ %>
		
		<td Class="tdfont" >
	      <div align="center">
	    
	
	<%		
		String[] shiftSingleArrayId=shiftDoubleArrayId[si];
		if(shiftSingleArrayId!=null && shiftSingleArrayId.length!=0){
		
			for(int k=0;k < shiftSingleArrayId.length ; k++){
			
				if(shiftSingleArrayId[k].equals(di+"_"+ri)){
		%>
	 
	 
	      
	     		 
	    	<his:checkbox name="deptUnitRoomConsultantRosterMstFB" onClick="checkShiftTimeClash(this);"  property='<%="shift["+si+"]"%>' value='<%=di+"_"+ri%>' disabled="true" /> 		
	    			
	      
        
        <%			
					}//if(shiftSingleArrayId[k].equals(di+"_"+ri)) is cloesd 
				
				}//for of shiftSingleArrayId is closed
			
			}//if of shiftSingleArrayId!=null is closed 
		%>
		
		</div>
        </td>
			
		<%
		}//for of no of shifts is closed	
		%>
		
		<%-- 
		
		<td border="0">
			<div align="center">
				<img class="button" src='<his:path src="/hisglobal/images/icn-min.png"/>' tabindex="1" border="0" style="cursor:pointer" onClick='deleteRowData("<%=di+"_"+ri%>")' onkeypress='if(event.keyCode==13) deleteRowData("<%=di+"_"+ri%>")'>
			</div>
		</td>
        <td border="0">
			<div align="center">
				<%if(ri==0){%>
					<img class="button" src='<his:path src="/hisglobal/images/icn-add.png"/>' tabindex="1" border="0" style="cursor:pointer" onClick='addRowData("<%=di%>");' onkeypress="if(event.keyCode==13)addRowData('<%=di%>');">
				<%} %>
			</div>
		</td>
		--%>
		
	</tr>
			
	<%     }//FOR LOOP OF No. of Rows 
		} //for loop of 1-7 days
		%>    

</table>


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

<div id="legendId" style="display: none;">
 



<table width="100%" cellpadding="0" cellspacing="1">

<tr>

<td class="tdfont" width="5%"><font color="red">NA</font></td>
<td class="tdfonthead"><div align="left">
<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
Not Available,due to Duty Already Assigned  in Some Roster
</font>
</div></td> 
</tr>

	
</table>

 </div> 
 
 
</his:ContentTag>
</his:statusTransactionInProcess>

<input type="hidden" name="addRow"/>
<input type="hidden" name="removeRow"/>
<input type="hidden" name="hmode"/>
<html:hidden name="deptUnitRoomConsultantRosterMstFB" property="checkdate" />

<his:ButtonToolBarTag>
		 <his:statusTransactionInProcess>	
		  <img class='button' src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>'  style="cursor:pointer;"  tabindex='1' onclick =  "submitFormOnValidate(validateIt(),'SAVE');" onkeypress="if(event.keyCode==13)submitFormOnValidate(validateIt(),'SAVE');")>
          <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>'  style="cursor:pointer;" tabindex="1" onclick ="clearForm()" onkeypress="if(event.keyCode==13) clearForm();">
		  <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style="cursor:pointer;" tabindex="1" onclick ="submitForm('NEW')" onkeypress="if(event.keyCode==13) submitForm('NEW')">
     	</his:statusTransactionInProcess>
     	
     	<his:statusNew>
	         <img class="button" src='/../HIS/hisglobal/images/buttons/btn-clr.png'  style="cursor:pointer;" tabindex="1" onclick ="submitForm('NEW')" onkeypress="if(event.keyCode==13) submitForm('NEW');">
     	</his:statusNew>
</his:ButtonToolBarTag>


</his:TransactionContainer>
</html:form>
<his:status/>

<%//try closed
}catch(Exception e){e.printStackTrace();}

%>