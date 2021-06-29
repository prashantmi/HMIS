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

<%@page import="hisglobal.presentation.WebUTIL"%>
<%@page import="dutyroster.transaction.controller.fb.DutyRoleAssignmentFB"%>

<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>	
<his:css src="/hisglobal/css/Color.css" />
<his:css src="/hisglobal/css/master.css" />
<his:css src="/hisglobal/css/hisStyle.css" />
<his:css src="/hisglobal/css/hisStyleExt.css" />
<his:css src="/hisglobal/css/calendar-blue2.css" />
<his:javascript src="/registration/js/popup.js" />
<his:javascript src="/registration/js/registration.js" />
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/masterutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<his:javascript src="/registration/js/dateFunctions.js"/>
<his:javascript src="/registration/js/validationCommon.js"/>
<his:javascript src="/registration/js/validationCalls.js"/>
<his:javascript src="/registration/js/commonFunctions.js"/>
<his:javascript src="/registration/js/calendar.js"/>
<his:javascript src="/hisglobal/js/utilityFunctions.js" />
<his:javascript src="/dutyroster/js/dutyRoleAssignmentTile.js" />


<script type="text/javascript">


</script>
<%String sysDate=WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy"); %>
<body>
<html:form action="/transaction/dutyRoleAssignment">
<his:TransactionContainer>
	<his:TitleTag name="Duty Role Assignment">
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
								<bean:message key="rosterName"/>
					</font>
				  </div>
			    </td>
			    
			    
			    <td width="25%" class="tdfont">
			      <div align="left">        
	    	   
	    	   
<logic:equal value="NEW" name="DutyRoleAssignmentFB" property="mode">  
	    	   
		  <html:select property="rosterTypeID" name="DutyRoleAssignmentFB" tabindex="1" styleClass="textbox" onchange="if (this.value!='-1') submitPage('GET_SHIFTS')">
			    <html:option value="-1">Select Value</html:option>
			       <logic:present  name="<%=DutyRosterConfig.ESSENTIAL_ROSTER_TYPE_FOR_ROLE_ASSIGNMENT%>" >
			          <html:options collection="<%=DutyRosterConfig.ESSENTIAL_ROSTER_TYPE_FOR_ROLE_ASSIGNMENT%>" property="value"  labelProperty="label"/>
			       </logic:present>
		   </html:select>		   
</logic:equal>				


<logic:equal value="" name="DutyRoleAssignmentFB" property="mode">  
	    	   
		  <html:select property="rosterTypeID" name="DutyRoleAssignmentFB" tabindex="1" styleClass="textbox" onchange="if (this.value!='-1') submitPage('GET_SHIFTS')">
			    <html:option value="-1">Select Value</html:option>
			       <logic:present  name="<%=DutyRosterConfig.ESSENTIAL_ROSTER_TYPE_FOR_ROLE_ASSIGNMENT%>" >
			          <html:options collection="<%=DutyRosterConfig.ESSENTIAL_ROSTER_TYPE_FOR_ROLE_ASSIGNMENT%>" property="value"  labelProperty="label"/>
			       </logic:present>
		   </html:select>		   
</logic:equal>				


<logic:equal value="MODIFY" name="DutyRoleAssignmentFB" property="mode">  
	    	<html:hidden property="rosterTypeID" name="DutyRoleAssignmentFB"/>   
	    	<bean:write name="DutyRoleAssignmentFB" property="rosterName"/>		  	   
</logic:equal>				

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

<logic:equal value="NEW" name="DutyRoleAssignmentFB" property="mode">  	    	   
		  <html:select property="dutyAreaId" name="DutyRoleAssignmentFB" tabindex="1" styleClass="textbox" onchange="if( this.value !='-1') getEmployeeList()">
			    <html:option value="-1">Select Value</html:option>
			       <logic:present  name="<%=DutyRosterConfig.DUTY_ROSTER_MASTER_LIST_OF_AREA%>" >
			          <html:options collection="<%=DutyRosterConfig.DUTY_ROSTER_MASTER_LIST_OF_AREA%>" property="value"  labelProperty="label"/>
			       </logic:present>
		   </html:select>	
</logic:equal>		   
		   		

<logic:equal value="" name="DutyRoleAssignmentFB" property="mode">  	    	   
		  <html:select property="dutyAreaId" name="DutyRoleAssignmentFB" tabindex="1" styleClass="textbox" onchange="if( this.value !='-1') getEmployeeList()">
			    <html:option value="-1">Select Value</html:option>
			       <logic:present  name="<%=DutyRosterConfig.DUTY_ROSTER_MASTER_LIST_OF_AREA%>" >
			          <html:options collection="<%=DutyRosterConfig.DUTY_ROSTER_MASTER_LIST_OF_AREA%>" property="value"  labelProperty="label"/>
			       </logic:present>
		   </html:select>	
</logic:equal>		   


<logic:equal value="MODIFY" name="DutyRoleAssignmentFB" property="mode">  	    	   
		  	<html:hidden property="dutyAreaId" name="DutyRoleAssignmentFB"/>   
	    	<bean:write name="DutyRoleAssignmentFB" property="areaName"/>		  
</logic:equal>		   



				   </div>
			     </td>  
			    
			     
		 </tr>
		     
		 
		 
		  <tr>     
		      	     
		      <td width="10%" class="tdfonthead">
			     <div align="right">
			     <font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								*
					</font> 
					   <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="shift"/>
					</font>
				  </div>
			    </td>
			    
			    
			    <td width="30%" class="tdfont">
			      <div align="left">        
	    	   
			   
			   
	<logic:equal value="NEW" name="DutyRoleAssignmentFB" property="mode">  
		   <html:select property="shiftID" name="DutyRoleAssignmentFB" tabindex="1" styleClass="textbox" onchange="submitFormData()">
			    <html:option value="%">ALL</html:option>
			       <logic:present  name="<%=DutyRosterConfig.ESSENTIAL_SHIFT_TYPE_BASEDON_ROSTER%>" >
			    		<html:options collection="<%=DutyRosterConfig.ESSENTIAL_SHIFT_TYPE_BASEDON_ROSTER%>" property="value"  labelProperty="label"/>
			       
			       </logic:present>
		   </html:select>		   
	</logic:equal>
				


	<logic:equal value="" name="DutyRoleAssignmentFB" property="mode">  
		   <html:select property="shiftID" name="DutyRoleAssignmentFB" tabindex="1" styleClass="textbox" onchange="submitFormData()">
			    <html:option value="%">ALL</html:option>
			       <logic:present  name="<%=DutyRosterConfig.ESSENTIAL_SHIFT_TYPE_BASEDON_ROSTER%>" >
			    		<html:options collection="<%=DutyRosterConfig.ESSENTIAL_SHIFT_TYPE_BASEDON_ROSTER%>" property="value"  labelProperty="label"/>
			       
			       </logic:present>
		   </html:select>		   
	</logic:equal>



	<logic:equal value="MODIFY" name="DutyRoleAssignmentFB" property="mode">  
		<html:hidden name="DutyRoleAssignmentFB" property="shiftID"/>	
		   <bean:write name="DutyRoleAssignmentFB" property="shiftName"/>	   
	</logic:equal>



				   </div>
			     </td>  
			     
			     
			      <td width="30%" class="tdfonthead">
			   <div align="right">
			   
				  </div>
			    </td>
			    
			    
			    <td width="30%" class="tdfont">
			     
			     <div align="left">        
	    	   
		 
				
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
	    	   
	    	  		<bean:define id="idFromDate" name="DutyRoleAssignmentFB" property="fromDate" type="java.lang.String"></bean:define>
	    	   	
	    	   	<logic:equal value="NEW"  property="mode" name="DutyRoleAssignmentFB">	
	    	   		<his:date name="fromDate" dateFormate="%d-%b-%Y" value="<%=idFromDate%>" />	
				</logic:equal>
				
				
				<logic:equal value=""  property="mode" name="DutyRoleAssignmentFB">	
	    	   		<his:date name="fromDate" dateFormate="%d-%b-%Y" value="<%=idFromDate%>" />	
				</logic:equal>
				
				
				<logic:equal value="MODIFY"  property="mode" name="DutyRoleAssignmentFB">	
				<html:hidden name="DutyRoleAssignmentFB"  property="fromDate"/>
	    	   		<bean:write name="DutyRoleAssignmentFB" property="fromDate"/>	
				</logic:equal>
				
					    	   			    	   				
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
	    	    <bean:define id="idToDate" name="DutyRoleAssignmentFB" property="toDate" type="java.lang.String"></bean:define>
			
			
				 	   
							
				
				<logic:equal value="NEW"  property="mode" name="DutyRoleAssignmentFB">	
	    	   		<his:date name="toDate" dateFormate="%d-%b-%Y" value="<%=idToDate %>"/>	
				</logic:equal>
				
				
				<logic:equal value=""  property="mode" name="DutyRoleAssignmentFB">	
	    	   		<his:date name="toDate" dateFormate="%d-%b-%Y" value="<%=idToDate%>" />	
				</logic:equal>
				
				
				<logic:equal value="MODIFY"  property="mode" name="DutyRoleAssignmentFB">	
					<html:hidden name="DutyRoleAssignmentFB"  property="toDate"/>
	    	   		<bean:write name="DutyRoleAssignmentFB" property="toDate"/>	
				</logic:equal>
				
				
				   </div>
			     </td>  
			    
			 
		 </tr> 
		
	</table>
		
<logic:notEqual value="NEW" name="DutyRoleAssignmentFB" property="mode">	
<logic:notEqual value="MODIFY" name="DutyRoleAssignmentFB" property="mode">		
<logic:present name="<%=DutyRosterConfig.LIST_OF_ROLE_ASSIGNED_DATE_RANGE_WISE %>">
	
	<table width="100%" border="0" cellspacing="1" cellpadding="0">
		<tr>
			<td width="25%" class="tdfonthead">
						<div align="center">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
							<bean:message key="select"/>
					</font>
						</div>
			</td>
	
			<td width="25%" class="tdfonthead">
						<div align="center">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
							<bean:message key="fromDate"/>
					</font>
						</div>	
			</td>
	
			<td width="25%" class="tdfonthead">
						<div align="center">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
							<bean:message key="toDate"/>
					</font>
						</div>	
			</td>
	
			<td width="25%" class="tdfonthead">
						<div align="center"> 
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:message key="shiftName"/>
					</font>
						</div>	
			</td>
	
		</tr>
	<logic:iterate id="RosterRoleDateRangeId" name="<%=DutyRosterConfig.LIST_OF_ROLE_ASSIGNED_DATE_RANGE_WISE %>" type="java.lang.String">
	<%
	String[] roleDetailsArray=RosterRoleDateRangeId.split("@");
	%>
	
		<tr>
		
		<td class="tdfont">
			<div align="center">
				<html:radio property="roleDateRangeId" name="DutyRoleAssignmentFB" value="<%=RosterRoleDateRangeId %>"/>
				<html:hidden property="fromDateOld" name="DutyRoleAssignmentFB" value="<%=roleDetailsArray[0] %>"/>
				<html:hidden property="toDateOld" name="DutyRoleAssignmentFB" value="<%=roleDetailsArray[1] %>"/>
			</div>			
		</td>
	
		
		<td class="tdfont">
			<div align="center">
				<%=roleDetailsArray[0] %>
			</div>
		</td>
	
	
	<td class="tdfont">
			<div align="center">
				<%=roleDetailsArray[1] %>
			</div>
	</td>
	
	
	<td class="tdfont">
		<div align="center">
			<%=roleDetailsArray[4] %>
		</div>	
	</td>
	
	
	
	
		</tr>
	</logic:iterate>
	
	</table>
	
<table width="100%" border="0" cellspacing="1" cellpadding="0">
	<tr>
		<td width="100%">
		   <div align="center">
		   <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/New.png"/>' tabindex="1" style="cursor: pointer" onkeypress="if(event.keyCode==13) getNewRole()" onclick="getNewRole()">
			     <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-mo.png"/>' tabindex="1" style="cursor: pointer" onclick="modifyOldRoster()" onkeypress="if(event.keyCode==13) modifyOldRoster()">
				 
				
		   </div>
		</td>
	</tr>
</table>
	
	
	
</logic:present>	
</logic:notEqual>	
</logic:notEqual>		
		
		
</his:ContentTag>


      <his:statusTransactionInProcess>
<%
List dateRangeList=new ArrayList();

dateRangeList=(ArrayList)session.getAttribute(DutyRosterConfig.LIST_OF_ROLE_ASSIGNED_DATE_RANGE_WISE);

//if((dateRangeList!=null && dateRangeList > 0) || (DutyRoleAssignmentFB)(pageContext.findAttribute("DutyRoleAssignmentFB")))

	//out.println("mode----"+((DutyRoleAssignmentFB)(pageContext.findAttribute("DutyRoleAssignmentFB"))).getMode());

	//out.println("dateRangeList----"+dateRangeList);
	
if((dateRangeList==null) || ((DutyRoleAssignmentFB)(pageContext.findAttribute("DutyRoleAssignmentFB"))).getMode().equals("NEW") ||  ((DutyRoleAssignmentFB)(pageContext.findAttribute("DutyRoleAssignmentFB"))).getMode().equals("MODIFY"))	
{
//out.println("hiiii");
%>
      

	
 
      <his:SubTitleTag key="employeeList">
      </his:SubTitleTag>
		<his:ContentTag>
			<table width="100%" border="0" cellspacing="1" cellpadding="0">
			<tr>
		      			<td width="25%" class="tdfont">
			     		<div align="center">
			     		  
			     		 <b>
								<bean:message key="employeename"/>
							</b>
							
				  		</div>
			   			 </td>
			    		<td width="75%" class="tdfont">
			     			<div align="center">
			     		    
			     		   <b>
								<bean:message key="dutyRole"/>
							</b>
							
				  			</div>
			  			  </td>  
			     		</tr>
			   </table>
			<bean:define id="shiftMap" name="<%=DutyRosterConfig.EMPLOYEE_LIST_ROSTER_AREA_AND_SHIFT_WISE %>" type="java.util.Map"></bean:define>
			<logic:iterate id="shiftMapEntry" name="shiftMap" type="java.util.Map.Entry">
				<bean:define id="employeeList" name="shiftMapEntry" property="value" type="java.util.List"></bean:define>
				<bean:define id="shiftNameKey" name="shiftMapEntry" property="key" type="java.lang.String" ></bean:define>
				<%
					String[] shiftArray=shiftNameKey.split("#");
					String shiftName=shiftArray[0];
					String shiftID=shiftArray[1];
				%>
				<his:SubTitleTag name="<%=shiftName %>">
				</his:SubTitleTag>
				<his:ContentTag>
					<table width="100%" border="0" cellspacing="1" cellpadding="0">
						
						<logic:iterate id="employee" name="employeeList" type="hisglobal.utility.Entry">
						<tr>
		      			<td width="25%" class="tdfonthead">
			     			<div align="center">
			     			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			     				<bean:write name="employee" property="label" />
			     			</font>
							</div>
			    		</td>
			    		<logic:present name="<%=DutyRosterConfig.ESSENTIAL_DUTY_ROLE_FOR_ROSTER %>">
			    		<%
			    		List roleList=(ArrayList)session.getAttribute(DutyRosterConfig.ESSENTIAL_DUTY_ROLE_FOR_ROSTER);
			    		String tdwidth=Integer.toString((70/roleList.size()));
			    		%>
			    		<bean:define id="dutyRoleList" name="<%= DutyRosterConfig.ESSENTIAL_DUTY_ROLE_FOR_ROSTER %>" ></bean:define>
			    		<logic:iterate id="dutyRoleEntry" name="dutyRoleList" type="hisglobal.utility.Entry">
			    		<bean:define id="roleDesc" name="dutyRoleEntry" property="label" type="java.lang.String"></bean:define>
			    		<%
			    		String[] descArray=roleDesc.split("#");
			    		String roleName=descArray[0];
			    		String shortName=descArray[1];
			    		String roleId=dutyRoleEntry.getValue();
			    		String[] employeeIdAndRole=(employee.getValue()).split("@");
			    		String employeeId=employeeIdAndRole[0];
			    		String concatedRoleID=employeeIdAndRole[1]; 
			    		String[] empRoleId= concatedRoleID.split("#");
			    		String isChecked="";
			    		
			    		for(int i=0; i<empRoleId.length;i++)
			    		{
			    			
			    			if(empRoleId[i].equals(roleId))
			    			{
			    				isChecked="checked";
			    			
			    				break;
			    			}
			    		}
			    		
			    		String checkBoxValue=employeeId +"#"+shiftID+"#"+roleId; 
			   			%>
			    		<td width="<%=tdwidth%>%" class="tdfonthead">
			    		
			      			<div align="center">	
			      			<font color="#000000"  face="Verdana, Arial, Helvetica, sans-serif">
			      			<input type="checkbox" name="roleID" <%=isChecked %> 
			      			value="<%=checkBoxValue %>" tabindex="1" />
			      			<a title="<%=roleName %>" style="cursor:pointer"><%=shortName  %></a>
			      			
			      			</font>		       		
		   				   </div>
			    		 </td>  
			    		 </logic:iterate>
			    		 </logic:present>
			     		</tr>
			     		</logic:iterate>
			     	</table>	
			     
				</his:ContentTag>
				
			</logic:iterate>
			
		</his:ContentTag>
	

		
		
		
	

<%} %>		
		
	</his:statusTransactionInProcess>
	
<%
List dateRangeList=new ArrayList();

dateRangeList=(ArrayList)session.getAttribute(DutyRosterConfig.LIST_OF_ROLE_ASSIGNED_DATE_RANGE_WISE);
if((dateRangeList==null) || ((DutyRoleAssignmentFB)(pageContext.findAttribute("DutyRoleAssignmentFB"))).getMode().equals("NEW") ||  ((DutyRoleAssignmentFB)(pageContext.findAttribute("DutyRoleAssignmentFB"))).getMode().equals("MODIFY"))
{
%>	
	<his:ButtonToolBarTag>
		<his:statusTransactionInProcess>
		<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>' style='cursor:pointer' tabindex="1" onclick ="validateSave('SAVE')" onkeypress="if(event.keyCode==13) validateSave('SAVE')">
		</his:statusTransactionInProcess>
    		<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style='cursor:pointer' tabindex="1" onclick ="submitPage('NEW')" onkeypress="if(event.keyCode==13) submitPage('NEW');">          	
   		 	<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style='cursor:pointer' tabindex="1" onclick ="cancelFunc()" onkeypress="if(event.keyCode==13) cancelFunc()">
		
	</his:ButtonToolBarTag>
<%} %>

</his:TransactionContainer>

<html:hidden name="DutyRoleAssignmentFB" property="mode"/>
<html:hidden name="DutyRoleAssignmentFB" property="hmode"/>
<html:hidden name="DutyRoleAssignmentFB" property="generatedRosterId"/>
<html:hidden name="DutyRoleAssignmentFB" property="currentDate" value="<%=sysDate %>"/>

<his:status/>


</html:form>
</body>
</html>