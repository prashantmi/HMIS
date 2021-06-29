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
<%@page import="registration.master.controller.util.DeptUnitRoomDayWiseCapacityMstUTIL"%>

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
<his:javascript src="/registration/js/deptUnitRoomDayWiseCapacityMaster.js"/>
<his:javascript src="/registration/js/calendar.js"/>


<%@ page import ="registration.*" %>

<script type="text/javascript">

	
</script>

<his:TransactionContainer>	
<html:form action="/master/deptUnitRoomDayWiseCapacityMaster">
	<his:TitleTag name="Department Unit Room Capacity Master">
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
	
	<% int noOfShifts = DeptUnitRoomDayWiseCapacityMstUTIL.getNumberOfShifts(request);
	
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
	<bean:define name="deptUnitRoomDayWiseCapacityMstFB" id="currForm" type="registration.master.controller.fb.DeptUnitRoomDayWiseCapacityMstFB"/>
	<%for(int di=1; di<= 7; di++){ 
		//for each Day of Week	
		int noOfRows = DeptUnitRoomDayWiseCapacityMstUTIL.getNumOfRows4aDOfWinJSP(currForm, request, di);

		 
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
					
					
			   		 
				</tr>
			</table>
		</td>
		
	    <td Class="tdfont">
	    	<div align="center"><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	    	
	    	
	    	<bean:define id="listFor1stWeekOfMonthId" name="deptUnitRoomDayWiseCapacityMstFB" property="listFor1stWeekOfMonth" type="java.util.List"/>
	    	<bean:define id="listFor2ndWeekOfMonthId" name="deptUnitRoomDayWiseCapacityMstFB" property="listFor2ndWeekOfMonth" type="java.util.List"/>
	    	<bean:define id="listFor3rdWeekOfMonthId" name="deptUnitRoomDayWiseCapacityMstFB" property="listFor3rdWeekOfMonth" type="java.util.List"/>
	    	<bean:define id="listFor4thWeekOfMonthId" name="deptUnitRoomDayWiseCapacityMstFB" property="listFor4thWeekOfMonth" type="java.util.List"/>
	    	<bean:define id="listFor5thWeekOfMonthId" name="deptUnitRoomDayWiseCapacityMstFB" property="listFor5thWeekOfMonth" type="java.util.List"/>
	    	<bean:define id="shiftDoubleArrayId" name="deptUnitRoomDayWiseCapacityMstFB" property="shift" type="java.lang.String[][]"/>
	    	
	    	<bean:define id="roomCapacityFor1stWeekId" name="deptUnitRoomDayWiseCapacityMstFB" property="roomCapacityFor1stWeek" type="java.lang.String[]"/>
	    	<bean:define id="roomCapacityFor2ndWeekId" name="deptUnitRoomDayWiseCapacityMstFB" property="roomCapacityFor2ndWeek" type="java.lang.String[]"/>
	    	<bean:define id="roomCapacityFor3rdWeekId" name="deptUnitRoomDayWiseCapacityMstFB" property="roomCapacityFor3rdWeek" type="java.lang.String[]"/>
	    	<bean:define id="roomCapacityFor4thWeekId" name="deptUnitRoomDayWiseCapacityMstFB" property="roomCapacityFor4thWeek" type="java.lang.String[]"/>
	    	<bean:define id="roomCapacityFor5thWeekId" name="deptUnitRoomDayWiseCapacityMstFB" property="roomCapacityFor5thWeek" type="java.lang.String[]"/>
	    	
	    
	    	<bean:define id="updateDataFor1stWeekId" name="deptUnitRoomDayWiseCapacityMstFB" property="updateDataFor1stWeek" type="java.lang.String[]"/>
	    	<bean:define id="updateDataFor2ndWeekId" name="deptUnitRoomDayWiseCapacityMstFB" property="updateDataFor2ndWeek" type="java.lang.String[]"/>
	    	<bean:define id="updateDataFor3rdWeekId" name="deptUnitRoomDayWiseCapacityMstFB" property="updateDataFor3rdWeek" type="java.lang.String[]"/>
	    	<bean:define id="updateDataFor4thWeekId" name="deptUnitRoomDayWiseCapacityMstFB" property="updateDataFor4thWeek" type="java.lang.String[]"/>
	    	<bean:define id="updateDataFor5thWeekId" name="deptUnitRoomDayWiseCapacityMstFB" property="updateDataFor5thWeek" type="java.lang.String[]"/>
	    
	    		<% 
	    			if(listFor1stWeekOfMonthId.contains(di+"_"+ri)){
	    		%>
	    			
	    			
	    			<html:text property="roomCapacityFor1stWeek" name="deptUnitRoomDayWiseCapacityMstFB" value="<%=roomCapacityFor1stWeekId[indexWeek1]%>" tabindex="1" size="3" maxlength="2"/>
	    			
	    			<html:hidden name="deptUnitRoomDayWiseCapacityMstFB" property="updateDataFor1stWeek" value="<%=updateDataFor1stWeekId[indexWeek1]%>"/>
      		  <%
      				indexWeek1++;
      		     }
	    	  %>	
      		
	          		
      		
      		</font></div>
        </td>
        <td Class="tdfont">
	    	<div align="center"><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	      		
	      		<%
	    		if(listFor2ndWeekOfMonthId.contains(di+"_"+ri)){
	    		%>
	    			
	    			
	    			<html:text property="roomCapacityFor2ndWeek" name="deptUnitRoomDayWiseCapacityMstFB" value="<%=roomCapacityFor2ndWeekId[indexWeek2]%>" tabindex="1" size="3" maxlength="2"/>
	    			
	    			<html:hidden name="deptUnitRoomDayWiseCapacityMstFB" property="updateDataFor2ndWeek" value="<%=updateDataFor2ndWeekId[indexWeek2]%>"/>
	    			
      		  <%
      		indexWeek2++;
      		  } 
      		  %>	
	      		
	      		
      	
      	
      		</font></div>
        </td>
        <td Class="tdfont">
	    	<div align="center"><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	      		
	      		<%
	    		if(listFor3rdWeekOfMonthId.contains(di+"_"+ri)){
	    		%>
	    			
	    			
	    			<html:text property="roomCapacityFor3rdWeek" name="deptUnitRoomDayWiseCapacityMstFB" value="<%=roomCapacityFor3rdWeekId[indexWeek3]%>" tabindex="1" size="3" maxlength="2"/>
	    			
	    			<html:hidden name="deptUnitRoomDayWiseCapacityMstFB" property="updateDataFor3rdWeek" value="<%=updateDataFor3rdWeekId[indexWeek3]%>"/>
	    			
      		 <%
      		indexWeek3++;
      		  } 
      		  %>	
	      		
	      		
      		</font></div>
        </td>
	    <td Class="tdfont">
	    	<div align="center"><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	      		
	      		<%
	    		if(listFor4thWeekOfMonthId.contains(di+"_"+ri)){
	    		%>
	    			
	    			
	    			<html:text property="roomCapacityFor4thWeek" name="deptUnitRoomDayWiseCapacityMstFB" value="<%=roomCapacityFor4thWeekId[indexWeek4]%>" tabindex="1" size="3" maxlength="2"/>
	    			
	    			<html:hidden name="deptUnitRoomDayWiseCapacityMstFB" property="updateDataFor4thWeek" value="<%=updateDataFor4thWeekId[indexWeek4]%>"/>
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
	    		if(listFor5thWeekOfMonthId.contains(di+"_"+ri)){
	    		%>
	    			
	    			
	    			<html:text property="roomCapacityFor5thWeek" name="deptUnitRoomDayWiseCapacityMstFB" value="<%=roomCapacityFor5thWeekId[indexWeek5]%>" tabindex="1" size="3" maxlength="2"/>
	    			
	    			<html:hidden name="deptUnitRoomDayWiseCapacityMstFB" property="updateDataFor5thWeek" value="<%=updateDataFor5thWeekId[indexWeek5]%>"/>
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
	 
	 
	      
	     		 
	    	<his:checkbox name="deptUnitRoomDayWiseCapacityMstFB" onClick="checkShiftTimeClash(this);"  property='<%="shift["+si+"]"%>' value='<%=di+"_"+ri%>' disabled="true" /> 		
	    			
	      
        
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
		
		
		
	</tr>
			
	<%} } %>    

</table>
</his:ContentTag>
</his:statusTransactionInProcess>

<input type="hidden" name="addRow"/>
<input type="hidden" name="removeRow"/>
<input type="hidden" name="hmode"/>
<html:hidden name="deptUnitRoomDayWiseCapacityMstFB" property="checkdate" />

<his:ButtonToolBarTag>
		 <his:statusTransactionInProcess>	
		  <img class='button' src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>'  style="cursor:pointer;"  tabindex='1' onclick =  "submitFormOnValidate(validateIt(),'SAVE');" onkeypress="if(event.keyCode==13)submitFormOnValidate(validateIt(),'SAVE');")>
          <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>'  style="cursor:pointer;" tabindex="1" onclick ="clearForm()" onkeypress="if(event.keyCode==13) clearForm();">
		  <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style="cursor:pointer;" tabindex="1" onclick ="submitForm('NEW')" onkeypress="if(event.keyCode==13) submitForm('NEW')">
     	</his:statusTransactionInProcess>
     	
     	<his:statusNew>
	         <img class="button" src='/../HIS/hisglobal/images/buttons/btn-clr.png'  style="cursor:pointer;" tabindex="1" onclick ="submitForm('NEW')" onkeypress="if(event.keyCode==13) submitForm('NEW');">
     	</his:statusNew>
     	
     	<his:statusUnsuccessfull>
     	     <img class="button" src='/../HIS/hisglobal/images/buttons/btn-clr.png'  style="cursor:pointer;" tabindex="1" onclick ="submitForm('NEW')" onkeypress="if(event.keyCode==13) submitForm('NEW');">	
     	</his:statusUnsuccessfull>
     	
</his:ButtonToolBarTag>

</html:form>

<his:status/>
</his:TransactionContainer>
<%
}catch(Exception e){e.printStackTrace();}

%>