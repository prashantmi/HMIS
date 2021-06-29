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
<%@page import="registration.master.controller.util.DeptUnitRoomUsablityMstUTIL"%>
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
<his:javascript src="/registration/js/deptUnitRoomUsablityMaster.js"/>
<his:javascript src="/registration/js/calendar.js"/>


<%@ page import ="registration.*" %>

<script type="text/javascript">

	
</script>

<his:TransactionContainer>	
<html:form action="/master/deptUnitRoomUsablityMaster">
	<his:TitleTag name="Department Unit Room Usablity Master">
	</his:TitleTag>
<his:ContentTag>
<table width="100%" cellpadding="1" cellspacing="1">
	<tr>
		<td width="17%" class="tdfonthead">
		<div align="right">
		<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		<bean:message key="department"/>
		</font>
		</div>
		</td>
		
		<td width="17%" class="tdfont">
		<html:select property="departmentCode" tabindex="1" styleClass="regCbo" onchange="if(this.value!='-1') submitForm('GET_DEPT_UNITS');"> 		  
		<html:option value="-1">Select Value</html:option>
		<html:options collection = "<%=Config.ESSENTIALBO_OPTION_DEPARTMENT%>" property = "value" labelProperty = "label"/>
		</html:select>
		</td>
		
		<td width="17%" class="tdfonthead">
		<div align="right">
		<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		<bean:message key="unit"/>
		</font>
		</div>
		</td>
		
		<td width="16%" class="tdfont">
		<html:select property="departmentUnitCode" tabindex="1" styleClass="regCbo" onchange="if(this.value!='-1') submitForm('GETROOM');"> 		  
		<html:option value="-1">Select Value</html:option>
		<html:options collection = "<%=Config.ESSENTIALBO_OPTION_DEPT_UNIT %>" property = "value" labelProperty = "label"/>
		</html:select>
		</td>
		
		<td width="16%" class="tdfonthead">
		<div align="right">
		<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		<bean:message key="roomName"/>
		</font>
		</div>
		</td>
		
		<td width="16%" class="tdfont">
		<html:select property="roomCode" tabindex="1" styleClass="regCbo" onchange="if(this.value!='-1') submitForm('GET_UNIT_ROSTER');"> 		  
		<html:option value="-1">Select Value</html:option>
		<logic:present name="<%=RegistrationConfig.ESSENTIAL_OPTION_ROOM_LIST %>">
		<html:options collection = "<%=RegistrationConfig.ESSENTIAL_OPTION_ROOM_LIST%>" property = "value" labelProperty = "label"/>
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
			<logic:empty name="deptUnitRoomUsablityMstFB" property="effectDate">
				<bean:define name="deptUnitRoomUsablityMstFB" property="effectDate" id="effectiveDate" type="java.lang.String"/>
				<%
					effectiveDate = WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy");
				
				%>				
				
				<his:date name='<%="effectDate"%>' dateFormate="%d-%b-%Y" value='<%=effectiveDate%>'/>
			</logic:empty>			
			
			
			<logic:notEmpty name="deptUnitRoomUsablityMstFB" property="effectDate">
				<bean:define name="deptUnitRoomUsablityMstFB" property="effectDate" id="effectiveDate" type="java.lang.String"/>
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
	
	<% int noOfShifts = DeptUnitRoomUsablityMstUTIL.getNumberOfShifts(request);
	
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
	<bean:define name="deptUnitRoomUsablityMstFB" id="currForm" type="registration.master.controller.fb.DeptUnitRoomUsablityMstFB"/>
	<%for(int di=1; di<= 7; di++){ 
		//for each Day of Week	
		int noOfRows = DeptUnitRoomUsablityMstUTIL.getNumOfRows4aDOfWinJSP(currForm, request, di);

		 
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
					
					<%-- 
					<td Class="tdfont" width="20%">
				    	<div align="left">
				    		<input type="checkbox" name="selectAll<%=di%>" value="<%=di+"_"+ri%>" onclick="selectAllWeek(this)">
				    	</div>
			   		 </td>	
			   		--%>
			   		 
				</tr>
			</table>
		</td>
		
	    <td Class="tdfont">
	    	<div align="center"><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	    	
	    	
	    	<bean:define id="listFor1stWeekOfMonthId" name="deptUnitRoomUsablityMstFB" property="listFor1stWeekOfMonth" type="java.util.List"/>
	    	<bean:define id="listFor2ndWeekOfMonthId" name="deptUnitRoomUsablityMstFB" property="listFor2ndWeekOfMonth" type="java.util.List"/>
	    	<bean:define id="listFor3rdWeekOfMonthId" name="deptUnitRoomUsablityMstFB" property="listFor3rdWeekOfMonth" type="java.util.List"/>
	    	<bean:define id="listFor4thWeekOfMonthId" name="deptUnitRoomUsablityMstFB" property="listFor4thWeekOfMonth" type="java.util.List"/>
	    	<bean:define id="listFor5thWeekOfMonthId" name="deptUnitRoomUsablityMstFB" property="listFor5thWeekOfMonth" type="java.util.List"/>
	    	<bean:define id="shiftDoubleArrayId" name="deptUnitRoomUsablityMstFB" property="shift" type="java.lang.String[][]"/>
	    	
	    	<bean:define id="roomUsabilityFor1stWeekId" name="deptUnitRoomUsablityMstFB" property="roomUsabilityFor1stWeek" type="java.lang.String[]"/>
	    	<bean:define id="roomUsabilityFor2ndWeekId" name="deptUnitRoomUsablityMstFB" property="roomUsabilityFor2ndWeek" type="java.lang.String[]"/>
	    	<bean:define id="roomUsabilityFor3rdWeekId" name="deptUnitRoomUsablityMstFB" property="roomUsabilityFor3rdWeek" type="java.lang.String[]"/>
	    	<bean:define id="roomUsabilityFor4thWeekId" name="deptUnitRoomUsablityMstFB" property="roomUsabilityFor4thWeek" type="java.lang.String[]"/>
	    	<bean:define id="roomUsabilityFor5thWeekId" name="deptUnitRoomUsablityMstFB" property="roomUsabilityFor5thWeek" type="java.lang.String[]"/>
	    	
	    
	    	<bean:define id="updateDataFor1stWeekId" name="deptUnitRoomUsablityMstFB" property="updateDataFor1stWeek" type="java.lang.String[]"/>
	    	<bean:define id="updateDataFor2ndWeekId" name="deptUnitRoomUsablityMstFB" property="updateDataFor2ndWeek" type="java.lang.String[]"/>
	    	<bean:define id="updateDataFor3rdWeekId" name="deptUnitRoomUsablityMstFB" property="updateDataFor3rdWeek" type="java.lang.String[]"/>
	    	<bean:define id="updateDataFor4thWeekId" name="deptUnitRoomUsablityMstFB" property="updateDataFor4thWeek" type="java.lang.String[]"/>
	    	<bean:define id="updateDataFor5thWeekId" name="deptUnitRoomUsablityMstFB" property="updateDataFor5thWeek" type="java.lang.String[]"/>
	    
	    		<% 
	    			if(listFor1stWeekOfMonthId.contains(di+"_"+ri)){
	    		%>
	    			<html:select property="roomUsabilityFor1stWeek" name="deptUnitRoomUsablityMstFB"  tabindex="1" value="<%=roomUsabilityFor1stWeekId[indexWeek1]%>">
	    				<html:option value="<%=RegistrationConfig.ROOM_USABILITY_NO_BOUND %>">No Bound</html:option>
	    				<html:option value="<%=RegistrationConfig.ROOM_USABILITY_OLD_PATIENT %>">Old Patient</html:option>
	    				<html:option value="<%=RegistrationConfig.ROOM_USABILITY_EXTERNAL_REFERRAL %>">External Referral</html:option>
	    				<html:option value="<%=RegistrationConfig.ROOM_USABILITY_INTERNAL_REFERRAL %>">Internal Referral</html:option>
	    				<html:option value="<%=RegistrationConfig.ROOM_USABILITY_FORCEFUL %>">Forceful Room</html:option>
	    				<html:option value="<%=RegistrationConfig.ROOM_USABILITY_NEW_OLD %>">New And Old</html:option>
	    				<html:option value="<%=RegistrationConfig.ROOM_USABILITY_NEW_OLD_FORCEFUL %>">New, Old And Forceful</html:option>
	    				<html:option value="<%=RegistrationConfig.ROOM_USABILITY_NOT_REQUIRED %>">Consultant Room</html:option>
	    			</html:select>
	    			
	    			<html:hidden name="deptUnitRoomUsablityMstFB" property="updateDataFor1stWeek" value="<%=updateDataFor1stWeekId[indexWeek1]%>"/>
      		  <%
      				indexWeek1++;
      		     }
	    	  %>	
      		
	          		<%--<his:checkbox onClick="shiftselect(this)" name="deptUnitRoomUsablityMstFB" property="week1stOfMonth" value='<%=di+"_"+ri%>'/>--%>
      		
      		</font></div>
        </td>
        <td Class="tdfont">
	    	<div align="center"><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	      		
	      		<%
	    		if(listFor2ndWeekOfMonthId.contains(di+"_"+ri)){
	    		%>
	    			<html:select property="roomUsabilityFor2ndWeek" name="deptUnitRoomUsablityMstFB"  tabindex="1" value="<%=roomUsabilityFor2ndWeekId[indexWeek2]%>">
	    				<html:option value="<%=RegistrationConfig.ROOM_USABILITY_NO_BOUND %>">No Bound</html:option>
	    				<html:option value="<%=RegistrationConfig.ROOM_USABILITY_OLD_PATIENT %>">Old Patient</html:option>
	    				<html:option value="<%=RegistrationConfig.ROOM_USABILITY_EXTERNAL_REFERRAL %>">External Referral</html:option>
	    				<html:option value="<%=RegistrationConfig.ROOM_USABILITY_INTERNAL_REFERRAL %>">Internal Referral</html:option>
	    				<html:option value="<%=RegistrationConfig.ROOM_USABILITY_FORCEFUL %>">Forceful Room</html:option>
	    				<html:option value="<%=RegistrationConfig.ROOM_USABILITY_NEW_OLD %>">New And Old</html:option>
	    				<html:option value="<%=RegistrationConfig.ROOM_USABILITY_NEW_OLD_FORCEFUL %>">New, Old And Forceful</html:option>
	    				<html:option value="<%=RegistrationConfig.ROOM_USABILITY_NOT_REQUIRED %>">Consultant Room</html:option>
	    			</html:select>
	    			
	    			<html:hidden name="deptUnitRoomUsablityMstFB" property="updateDataFor2ndWeek" value="<%=updateDataFor2ndWeekId[indexWeek2]%>"/>
	    			
      		  <%
      		indexWeek2++;
      		  } 
      		  %>	
	      		
	      	<%--<his:checkbox onClick="shiftselect(this)" name="deptUnitRoomUsablityMstFB" property="week2ndOfMonth" value='<%=di+"_"+ri%>'/>--%>	
      	
      	
      		</font></div>
        </td>
        <td Class="tdfont">
	    	<div align="center"><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	      		
	      		<%
	    		if(listFor3rdWeekOfMonthId.contains(di+"_"+ri)){
	    		%>
	    			<html:select property="roomUsabilityFor3rdWeek" name="deptUnitRoomUsablityMstFB"  tabindex="1" value="<%=roomUsabilityFor3rdWeekId[indexWeek3]%>">
	    				<html:option value="<%=RegistrationConfig.ROOM_USABILITY_NO_BOUND %>">No Bound</html:option>
	    				<html:option value="<%=RegistrationConfig.ROOM_USABILITY_OLD_PATIENT %>">Old Patient</html:option>
	    				<html:option value="<%=RegistrationConfig.ROOM_USABILITY_EXTERNAL_REFERRAL %>">External Referral</html:option>
	    				<html:option value="<%=RegistrationConfig.ROOM_USABILITY_INTERNAL_REFERRAL %>">Internal Referral</html:option>
	    				<html:option value="<%=RegistrationConfig.ROOM_USABILITY_FORCEFUL %>">Forceful Room</html:option>
	    				<html:option value="<%=RegistrationConfig.ROOM_USABILITY_NEW_OLD %>">New And Old</html:option>
	    				<html:option value="<%=RegistrationConfig.ROOM_USABILITY_NEW_OLD_FORCEFUL %>">New, Old And Forceful</html:option>
	    				<html:option value="<%=RegistrationConfig.ROOM_USABILITY_NOT_REQUIRED %>">Consultant Room</html:option>
	    			</html:select>
	    			
	    			<html:hidden name="deptUnitRoomUsablityMstFB" property="updateDataFor3rdWeek" value="<%=updateDataFor3rdWeekId[indexWeek3]%>"/>
	    			
      		 <%
      		indexWeek3++;
      		  } 
      		  %>	
	      		<%--<his:checkbox onClick="shiftselect(this)" name="deptUnitRoomUsablityMstFB" property="week3rdOfMonth" value='<%=di+"_"+ri%>'/> --%>
	      		
      		</font></div>
        </td>
	    <td Class="tdfont">
	    	<div align="center"><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	      		
	      		<%
	    		if(listFor4thWeekOfMonthId.contains(di+"_"+ri)){
	    		%>
	    			<html:select property="roomUsabilityFor4thWeek" name="deptUnitRoomUsablityMstFB"  tabindex="1" value="<%=roomUsabilityFor4thWeekId[indexWeek4]%>">
	    				<html:option value="<%=RegistrationConfig.ROOM_USABILITY_NO_BOUND %>">No Bound</html:option>
	    				<html:option value="<%=RegistrationConfig.ROOM_USABILITY_OLD_PATIENT %>">Old Patient</html:option>
	    				<html:option value="<%=RegistrationConfig.ROOM_USABILITY_EXTERNAL_REFERRAL %>">External Referral</html:option>
	    				<html:option value="<%=RegistrationConfig.ROOM_USABILITY_INTERNAL_REFERRAL %>">Internal Referral</html:option>
	    				<html:option value="<%=RegistrationConfig.ROOM_USABILITY_FORCEFUL %>">Forceful Room</html:option>
	    				<html:option value="<%=RegistrationConfig.ROOM_USABILITY_NEW_OLD %>">New And Old</html:option>
	    				<html:option value="<%=RegistrationConfig.ROOM_USABILITY_NEW_OLD_FORCEFUL %>">New, Old And Forceful</html:option>
	    				<html:option value="<%=RegistrationConfig.ROOM_USABILITY_NOT_REQUIRED %>">Consultant Room</html:option>
	    			</html:select>
	    			
	    			<html:hidden name="deptUnitRoomUsablityMstFB" property="updateDataFor4thWeek" value="<%=updateDataFor4thWeekId[indexWeek4]%>"/>
      		 <%
      		indexWeek4++;
      		  } 
      		  %>	
      		  
      		  <%--<his:checkbox onClick="shiftselect(this)" name="deptUnitRoomUsablityMstFB" property="week4thOfMonth" value='<%=di+"_"+ri%>'/> --%>
	      		
      		</font></div>
        </td>
	    <td Class="tdfont">
	    	<div align="center">
	    	<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	    	
	    	
	    	<%
	    		if(listFor5thWeekOfMonthId.contains(di+"_"+ri)){
	    		%>
	    			<html:select property="roomUsabilityFor5thWeek" name="deptUnitRoomUsablityMstFB"  tabindex="1" value="<%=roomUsabilityFor5thWeekId[indexWeek5]%>">
	    				<html:option value="<%=RegistrationConfig.ROOM_USABILITY_NO_BOUND %>">No Bound</html:option>
	    				<html:option value="<%=RegistrationConfig.ROOM_USABILITY_OLD_PATIENT %>">Old Patient</html:option>
	    				<html:option value="<%=RegistrationConfig.ROOM_USABILITY_EXTERNAL_REFERRAL %>">External Referral</html:option>
	    				<html:option value="<%=RegistrationConfig.ROOM_USABILITY_INTERNAL_REFERRAL %>">Internal Referral</html:option>
	    				<html:option value="<%=RegistrationConfig.ROOM_USABILITY_FORCEFUL %>">Forceful Room</html:option>
	    				<html:option value="<%=RegistrationConfig.ROOM_USABILITY_NEW_OLD %>">New And Old</html:option>
	    				<html:option value="<%=RegistrationConfig.ROOM_USABILITY_NEW_OLD_FORCEFUL %>">New, Old And Forceful</html:option>
	    				<html:option value="<%=RegistrationConfig.ROOM_USABILITY_NOT_REQUIRED %>">Consultant Room</html:option>
	    			</html:select>
	    			
	    			<html:hidden name="deptUnitRoomUsablityMstFB" property="updateDataFor5thWeek" value="<%=updateDataFor5thWeekId[indexWeek5]%>"/>
      		  <%
      		indexWeek5++;
      		  } 
      		  %>
      		  	
      		  <%--<his:checkbox onClick="shiftselect(this)" name="deptUnitRoomUsablityMstFB" property="week5thOfMonth" value='<%=di+"_"+ri%>'/> --%>
      		  
	      		
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
	 
	 
	      
	     		 
	    	<his:checkbox name="deptUnitRoomUsablityMstFB" onClick="checkShiftTimeClash(this);"  property='<%="shift["+si+"]"%>' value='<%=di+"_"+ri%>' disabled="true" /> 		
	    			
	      
        
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
			
	<%} } %>    

</table>
</his:ContentTag>
</his:statusTransactionInProcess>

<input type="hidden" name="addRow"/>
<input type="hidden" name="removeRow"/>
<input type="hidden" name="hmode"/>
<html:hidden name="deptUnitRoomUsablityMstFB" property="checkdate" />

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

</html:form>

<his:status/>
</his:TransactionContainer>
<%
}catch(Exception e){e.printStackTrace();}

%>