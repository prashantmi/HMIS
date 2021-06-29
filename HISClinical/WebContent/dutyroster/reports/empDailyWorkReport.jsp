<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@	taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="java.util.*"%>
<%@page import="hisglobal.hisconfig.Config"%>
<%@page import="dutyroster.DutyRosterConfig;"%>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>	
<style type="text/css">
@import url(../css/calendar-blue2.css);
</style>
<his:css src="/hisglobal/css/calendar-blue2.css" />
<his:css src="/hisglobal/css/master.css" />
<his:css src="/hisglobal/css/hisStyle.css" />
<his:css src="/hisglobal/css/hisStyleExt.css" />
<style type="text/css"> 
</style>
<his:javascript src="/registration/js/registration.js" />
<his:javascript src="/dutyroster/js/empDailyWorkReport.js" />


<his:javascript src="/hisglobal/js/dateFunctions.js" />
<his:javascript src="/registration/js/calendar.js" />
<his:javascript src="/hisglobal/js/validationCommon.js" />
<his:javascript src="/hisglobal/js/validationCalls.js" />
<his:javascript src="/hisglobal/js/utilityFunctions.js" />
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/masterutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<his:css src="/css/calendar-blue2.css" />

<body onload="focusOnLoad()">



<html:form action="/reports/EmpDailyWorkReport">




	<logic:empty
		name="<%=DutyRosterConfig.MAP_FOR_MONTHWISE_EMP_ROSTER_REPORT_DETAILS %>">

		<his:TransactionContainer>

			<his:css src="/hisglobal/css/Color.css" />

			<his:TitleTag name="Employee Daily Work Report">

			</his:TitleTag>






			<his:ContentTag>
				<table width="100%" border="0" cellspacing="1" cellpadding="0">


					<tr>

					<td width="25%" class="tdfonthead">
						<div align="right">
							<font color="#000000" size="2" 	face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message 	key="workingDate" /> 
							</font>
						</div>
					</td>
<bean:define id="workingDateId" name="EmpDailyWorkReportFB" property="workingDate" type="java.lang.String"></bean:define>

						<td width="25%" class="tdfont">
						<div align="left"><his:date name="workingDate"
							dateFormate="%d-%b-%Y" value="<%=workingDateId %>"/></div>
						</td>


						<td width="25%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" 	face="Verdana, Arial, Helvetica, sans-serif"> 
							   		<bean:message key="rosterMainCategory" />
							    </font>
						    </div>
						</td>


						<td width="25%" class="tdfont">
						<div align="left">
						<html:select property="rosterMainCategory"  name="EmpDailyWorkReportFB" tabindex="1" styleClass="textbox" 	onchange="submitPage('GET_ROSTER_CATEGORY')"> 
							<html:option value="-1">Select Value</html:option>
							<logic:present 	name="<%=DutyRosterConfig.LIST_OF_ROSTER_MAIN_CATEGORY%>">
								<html:options 	collection="<%=DutyRosterConfig.LIST_OF_ROSTER_MAIN_CATEGORY%>" property="value" labelProperty="label" /> 
							</logic:present>
						</html:select></div>
						</td>


					</tr>


					<tr>

						<td width="25%" class="tdfonthead">
						<div align="right"><font color="RED" size="2"
							face="Verdana, Arial, Helvetica, sans-serif"> * </font> <font
							color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
							key="rosterCategory" /> </font></div>
						</td>


						<td width="25%" class="tdfont">
							<div align="left">
								<html:select property="rosterCategory" 	name="EmpDailyWorkReportFB" tabindex="1" styleClass="textbox"  onchange="submitPage('GET_ROSTERS')"> 
								  <html:option value="-1">Select Value</html:option>
									<logic:present  name="<%=DutyRosterConfig.LIST_OF_ROSTER_CATEGORY%>"> 
									 	<html:options 	collection="<%=DutyRosterConfig.LIST_OF_ROSTER_CATEGORY%>" 	property="value" labelProperty="label" /> 
									</logic:present>
								</html:select>
							 </div>
						</td>


						<td width="25%" class="tdfonthead">
						<div align="right"><font color="RED" size="2"
							face="Verdana, Arial, Helvetica, sans-serif"> * </font> <font
							color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
							key="rosterName" /> </font></div>
						</td>


		<td width="25%" class="tdfont">
				<div align="left">
					<html:select property="rosterId" 	name="EmpDailyWorkReportFB" tabindex="1" styleClass="textbox"  onchange="submitPage('GET_DUTY_AREA')"> 
						<html:option value="-1">Select Value</html:option>
							<logic:present 	name="<%=DutyRosterConfig.LIST_OF_ROSTERS_ON_THE_BASIS_OF_ROSTER_CATEGORY%>"> 
								<html:options 	collection="<%=DutyRosterConfig.LIST_OF_ROSTERS_ON_THE_BASIS_OF_ROSTER_CATEGORY%>" 	property="value" labelProperty="label" />  
							</logic:present>
						</html:select>
				</div>
		</td>


					</tr>



					<tr>

						<td width="25%" class="tdfonthead">
						<div align="right"><font color="RED" size="2"
							face="Verdana, Arial, Helvetica, sans-serif"> * </font> <font
							color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
							key="dutyArea" /> </font></div>
						</td>
						<%
							List areaList = new ArrayList();
											areaList = (ArrayList) session
													.getAttribute(DutyRosterConfig.DUTY_ROSTER_MASTER_LIST_OF_AREA);
						%>

		<td width="25%" class="tdfont">
			<div align="left">
				<html:select property="areaCode" name="EmpDailyWorkReportFB" tabindex="1" styleClass="textbox"  onchange="submitPage('GET_EMP')">
							<html:option value="-1">Select Value</html:option>
							<logic:present 	name="<%=DutyRosterConfig.DUTY_ROSTER_MASTER_LIST_OF_AREA%>"> 
								<%
									if (areaList != null && areaList.size() > 1) {
								%>
								<html:option value="%">ALL</html:option>
								<%
									}
								%>
							  <html:options collection="<%=DutyRosterConfig.DUTY_ROSTER_MASTER_LIST_OF_AREA%>" 	property="value" labelProperty="label" /> 
						</logic:present>
					</html:select>
				</div>
		</td>


						<td width="25%" class="tdfonthead">
						<div align="right"><font color="RED" size="2"
							face="Verdana, Arial, Helvetica, sans-serif"> * </font> <font
							color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
							key="employee" /> </font></div>
						</td>


		<td width="25%" class="tdfont">
				<div align="left">
					<html:select property="empId" 	name="EmpDailyWorkReportFB" tabindex="1" styleClass="textbox"  onchange="submitPage('GET_REPORT')"> 
						<html:option value="-1">Select Value</html:option>
							<logic:present 	name="<%=DutyRosterConfig.LIST_OF_EMP_BASED_ON_AREA_TYPE_AND_AREA_CODE%>"> 
								<html:options 	collection="<%=DutyRosterConfig.LIST_OF_EMP_BASED_ON_AREA_TYPE_AND_AREA_CODE%>" 	property="value" labelProperty="label" />  
							</logic:present>
						</html:select>
				</div>
		</td>


					</tr>


				</table>




			</his:ContentTag>
		</his:TransactionContainer>

	</logic:empty>


<logic:notEmpty name="<%=DutyRosterConfig.EMP_WORK_REPORT_MAP %>" > 

<his:SubTitleTag name="Employee Details">
</his:SubTitleTag>

<table width="100%" border="0" cellspacing="1" cellpadding="0">
		
		
		   <tr>
				<td width="25%" class="tdfonthead">
						<div align="right">	
							<font	color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								Employee Name
							</font>	
						</div>
				</td>
				
				<td width="25%" class="tdfont">
							<div align="left">	
								<bean:write name="EmpDailyWorkReportFB" property="empName"/>
							</div>
				</td>
				
				
				<td width="25%" class="tdfonthead">
						<div align="right">	
							<font	color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							Designation
							</font>	
						</div>
				</td>
				
				<td width="25%" class="tdfont">
							<div align="left">	
								<bean:write name="EmpDailyWorkReportFB" property="designationName"/>
							</div>
				</td>
				
				
				
		   
		   </tr>			
			
					
		</table>		
		

<his:SubTitleTag name="Employee Assign Roles">
</his:SubTitleTag>				

<table width="100%" border="0" cellspacing="1" cellpadding="0">						

<tr>
    			<td width="33%" class="tdfonthead">
    					<div align="center">
    							 <font	color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
    							 		<bean:message 	key="areaName" />
    							  </font>
    					</div>
    			</td>

				<td width="33%" class="tdfonthead">
    					<div align="center">
    							 <font	color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
    							 		<bean:message 	key="shiftName" />
    							  </font>
    					</div>
    			</td>
    			
    			
    			<td width="33%" class="tdfonthead">
    					<div align="center">
    							 <font	color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
    							 		<bean:message 	key="roleName" />
    							  </font>
    					</div>
    			</td>
    			
    			
				
			</tr>	
						
<logic:iterate id="areaEntry" name="<%=DutyRosterConfig.EMP_WORK_REPORT_MAP %>" type="java.util.Map.Entry">


<bean:define id="areaId" name="areaEntry" property="key" type="java.lang.String"></bean:define>

<bean:define id="shiftMap" name="areaEntry" property="value" type="java.util.Map"></bean:define>



	<logic:iterate id="shiftMapEntry" name="shiftMap" type="java.util.Map.Entry">

<bean:define id="shiftId" name="shiftMapEntry" property="key" type="java.lang.String"></bean:define>

<bean:define id="roleList" name="shiftMapEntry" property="value" type="java.util.List"></bean:define>

<%
String[] areaArray=null;
String[] shiftArray=null;

if(areaId!=null)
	areaArray=areaId.split("@");

if(shiftId!=null)
	shiftArray=shiftId.split("@");

%>

			<logic:iterate id="roleId" name="roleList" type="java.lang.String">
			
			<tr>

				<td class="tdfont"><div align="center"><%if(areaArray!=null){%><%=areaArray[2]%><%}%></td>

				<td class="tdfont"><div align="center"><%if(shiftArray!=null){%><%=shiftArray[1]%><%}%></td>

				<td class="tdfont"><div align="center"><%=roleId%></td>

					</tr>
								
			</logic:iterate>



	</logic:iterate>








</logic:iterate>


</table>

<his:SubTitleTag name="Employee Work Performed">
</his:SubTitleTag>		


</logic:notEmpty>



	<his:ContentTag>
		<logic:equal name="EmpDailyWorkReportFB" property="hmode" 	value="GET_REPORT">
			<logic:empty 	name="<%=DutyRosterConfig.EMP_WORK_REPORT_MAP %>">
				<div align="left" 	style="color: red; font-family: arial; font-size: 20px; font-weight: bold;">
						No Role Assigned
				</div>
			</logic:empty>
		</logic:equal>
	</his:ContentTag>

		<his:ButtonToolBarTag>
			<span id="saveDiv">
			 <img class="button" src='<his:path src="/hisglobal/images/btn-view.png"/>' tabindex="1" style="cursor: pointer" onclick="submitPage('GET_REPORT')"  onkeypress="if(event.keyCode==13) submitPage('GET_REPORT')">
			 <img  class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' tabindex="1" style="cursor: pointer" onclick="clearForm()" 	onkeypress="if(event.keyCode==13) clearForm()"> 
			 <img  class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' tabindex="1" style="cursor: pointer" onclick="submitPage('CANCEL')"  onkeypress="if(event.keyCode==13) submitPage('CANCEL')">
		    </span>  
		</his:ButtonToolBarTag>


	<his:status />

	<html:hidden name="EmpDailyWorkReportFB" property="hmode" />
	


</html:form>
</body>
</html>


