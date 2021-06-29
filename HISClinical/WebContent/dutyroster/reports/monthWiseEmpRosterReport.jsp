<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@	taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="java.util.*"%>
<%@page import="hisglobal.hisconfig.Config"%>
<%@page import="dutyroster.DutyRosterConfig"%>
<%@page import="hisglobal.vo.HospitalMstVO" %>
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
@import url(../css/calendar-blue2.css);
</style>
<his:javascript src="/registration/js/registration.js" />
<his:javascript src="/dutyroster/js/monthWiseEmpRosterReport.js" />


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

<%
	String systemDate=hisglobal.presentation.WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy HH:mm");
	HospitalMstVO HVO = (HospitalMstVO)session.getAttribute(DutyRosterConfig.HOSPITAL_MST_VO);
%>

<html:form action="/reports/MonthWiseEmpRosterReport">






	<logic:empty
		name="<%=DutyRosterConfig.MAP_FOR_MONTHWISE_EMP_ROSTER_REPORT_DETAILS %>">

		<his:TransactionContainer>

			<his:css src="/hisglobal/css/Color.css" />

			<his:TitleTag name="MonthWise Employee Roster Report">
				
			</his:TitleTag>






			<his:ContentTag>
				<table width="100%" border="0" cellspacing="1" cellpadding="0">
					<div align="right" style="background-color: #FFEBD5"><b>Printing
					Format:</b>&nbsp;Combined<html:radio property="printingFormat"
						value="C" name="MonthWiseEmpRosterReportFB" />Seperate<html:radio
						property="printingFormat" value="S"
						name="MonthWiseEmpRosterReportFB" /></div>
					<tr>
						<td width="25%" class="tdfonthead">
						<div align="right"><font color="RED" size="2"
							face="Verdana, Arial, Helvetica, sans-serif"> * </font> <font
							color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
							key="year" />&nbsp; </font></div>
						</td>


						<td width="25%" class="tdfont">
						<div align="left"><html:select property="year"
							name="MonthWiseEmpRosterReportFB" tabindex="1"
							styleClass="textbox" >
							<html:option value="-1">Select Value</html:option>
							<logic:present
								name="<%=DutyRosterConfig.LIST_OF_YEARS_EMPLOYEE_DUTY_ROSTER_MASTER%>">
								<html:options
									collection="<%=DutyRosterConfig.LIST_OF_YEARS_EMPLOYEE_DUTY_ROSTER_MASTER%>"
									property="value" labelProperty="label" />
							</logic:present>
						</html:select></div>
						</td>



						<td width="25%" class="tdfonthead">
						<div align="right"><font color="RED" size="2"
							face="Verdana, Arial, Helvetica, sans-serif"> * </font> <font
							color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
							key="month" /> </font></div>
						</td>


						<td width="25%" class="tdfont">
						<div align="left"><html:select property="month"
							name="MonthWiseEmpRosterReportFB" tabindex="1"
							styleClass="textbox" >
							<html:option value="-1">Select Value</html:option>
							<logic:present
								name="<%=DutyRosterConfig.LIST_OF_MONTHS_EMPLOYEE_DUTY_ROSTER_MASTER%>">
								<html:options
									collection="<%=DutyRosterConfig.LIST_OF_MONTHS_EMPLOYEE_DUTY_ROSTER_MASTER%>"
									property="value" labelProperty="label" />
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
						<div align="left"><html:select property="rosterCategory"
							name="MonthWiseEmpRosterReportFB" tabindex="1"
							styleClass="textbox" onchange="submitPage('GET_ROSTERS')">
							<html:option value="-1">Select Value</html:option>
							<logic:present
								name="<%=DutyRosterConfig.LIST_OF_ROSTER_CATEGORY%>">
								<html:options
									collection="<%=DutyRosterConfig.LIST_OF_ROSTER_CATEGORY%>"
									property="value" labelProperty="label" />
							</logic:present>
						</html:select></div>
						</td>


						<td width="25%" class="tdfonthead">
						<div align="right"><font color="RED" size="2"
							face="Verdana, Arial, Helvetica, sans-serif"> * </font> <font
							color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
							key="rosterName" /> </font></div>
						</td>


						<td width="25%" class="tdfont">
						<div align="left"><html:select property="rosterId"
							name="MonthWiseEmpRosterReportFB" tabindex="1"
							styleClass="textbox" onchange="submitPage('GET_DUTY_AREA')">
							<html:option value="-1">Select Value</html:option>
							<logic:present
								name="<%=DutyRosterConfig.LIST_OF_ROSTERS_ON_THE_BASIS_OF_ROSTER_CATEGORY%>">
								<html:options
									collection="<%=DutyRosterConfig.LIST_OF_ROSTERS_ON_THE_BASIS_OF_ROSTER_CATEGORY%>"
									property="value" labelProperty="label" />
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
							key="dutyArea" /> </font></div>
						</td>
						<%
							List areaList = new ArrayList();
											areaList = (ArrayList) session
													.getAttribute(DutyRosterConfig.DUTY_ROSTER_MASTER_LIST_OF_AREA);
						%>

						<td width="25%" class="tdfont">
						<div align="left"><html:select property="areaCode"
							name="MonthWiseEmpRosterReportFB" tabindex="1"
							styleClass="textbox" onchange="">
							<html:option value="-1">Select Value</html:option>
							<logic:present 	name="<%=DutyRosterConfig.DUTY_ROSTER_MASTER_LIST_OF_AREA%>">
							<% if (areaList != null && areaList.size() > 1) { %>
								<html:option value="ALL">ALL</html:option>
							<% } %>
			<html:options 	collection="<%=DutyRosterConfig.DUTY_ROSTER_MASTER_LIST_OF_AREA%>" 	property="value" labelProperty="label" />
							</logic:present>
						</html:select></div>
						</td>


						<td width="25%" class="tdfonthead">
						<div align="right"><font color="RED" size="2"
							face="Verdana, Arial, Helvetica, sans-serif"> * </font> <font
							color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
							key="designation" /> </font></div>
						</td>


						<td width="25%" class="tdfont">

						<div align="left"><html:select property="designationId"
							name="MonthWiseEmpRosterReportFB" tabindex="1"
							styleClass="textbox" onchange="" disabled="true">
							<html:option value="-1">Select Value</html:option>
							<logic:present
								name="<%=DutyRosterConfig.LIST_OF_DESIGNATION_ON_THE_BASIS_OF_ROSTER_TYPE%>">
								<html:option value="ALL">ALL</html:option>
								<html:options
									collection="<%=DutyRosterConfig.LIST_OF_DESIGNATION_ON_THE_BASIS_OF_ROSTER_TYPE%>"
									property="value" labelProperty="label" />
							</logic:present>
						</html:select></div>
						</td>


					</tr>

					<tr>

						<td width="25%" class="tdfonthead">
						<div align="right"><font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
							key="reportPrinting" /> <bean:message key="fromDate" /> </font></div>
						</td>


						<td width="25%" class="tdfont">
						<div align="left"><bean:define id="startDateId"
							name="MonthWiseEmpRosterReportFB" property="startDate"></bean:define>
						<his:date name="startDate" dateFormate="%d-%b-%Y"
							value="<%=(String)startDateId%>" /></div>
						</td>


						<td width="25%" class="tdfonthead">
						<div align="right"><font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
							key="reportPrinting" /> <bean:message key="toDate" /> </font></div>
						</td>


						<td width="25%" class="tdfont">
						<div align="left"><bean:define id="endDateId"
							name="MonthWiseEmpRosterReportFB" property="endDate"></bean:define>
						<his:date name="endDate" dateFormate="%d-%b-%Y"
							value="<%=(String)endDateId%>" /></div>
						</td>


					</tr>
				</table>




			</his:ContentTag>
		</his:TransactionContainer>

	</logic:empty>




	<%
		String printingDateAndTime = "Report Printing Date and Time :"
					+ systemDate;

			String width = "";
	%>

	<his:ContentTag>



		<logic:notEmpty 	name="<%=DutyRosterConfig.MAP_FOR_MONTHWISE_EMP_ROSTER_REPORT_DETAILS %>">

			<div id="noPrint" align="right"><img class="button"
				src='<his:path src="/../HIS/hisglobal/images/buttons/btn-pnt.png"/>' tabindex="1"
				style="cursor: pointer" onclick="printPage()"
				onkeypress="if(event.keyCode==13) printPage()"> <img
				class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'
				tabindex="1" style="cursor: pointer" onclick="backButton()"
				onkeypress="if(event.keyCode==13) backButton()"></div>

			<br>
			<br>
<table  width="100%" cellspacing="1" cellpadding="0">
		<tr>
		<td width="100%"  style="font-size:10px; font:Arial;" align="right">
		
		<%=printingDateAndTime%>
		</td>
		</tr>
		<tr >
		 <td class="" width="50%" colspan="2">
	 	 	<div align="center" width="100%">
		   <img style="width: auto; height: 100px; align:center" src='<his:path src="/hisglobal/images/NIMSLOGO.png"/>'>
			</div>			
		  </td>
		 </tr>
		<tr >
		 <td class="" width="50%" colspan="2">
	 	 	<div align="center" width="100%">
		    <%=HVO.getHospitalName() %>
			</div>			
		  </td>
		 </tr>
	
		 <tr>
		 <td class="" width="50%" colspan="2">
		 	<div align="center" width="100%">	
		 	  <%=HVO.getAddress1() %>
			</div> 
		  </td>
		 </tr> 
		 
		 
		 <tr>
		 <td class="" width="50%" colspan="2">
		 	<div align="center" width="100%">	
		 	  <%=HVO.getAddress2() %>,<%=HVO.getCity() %> - <%=HVO.getPinCode() %>,
							<%=HVO.getStateName() %>, INDIA	
			</div> 
		  </td>
		 </tr> 
		 
		<tr>
		<td class="" width="50%" colspan="2">
		<div align="center" width="100%">
							<b>Duty Roster for
		<bean:write name="MonthWiseEmpRosterReportFB" property="areaName"/></b>
		</div> 
	  	</td>
		</tr>
		<tr>
		<td class="" width="50%" colspan="2">
		<div align="center" width="100%">
						<b>For the Month of 
		<bean:write name="MonthWiseEmpRosterReportFB" property="monthChar"/></b>
		</div> 
	  	</td>
		</tr>
		</table>
	<!--
		</table>
			<table width="100%" cellpadding="1" cellspacing="1"
				bordercolor="black" style="color: black;">
				<tr>
					<td width="100%" class="reportNameHeader"><bean:write
						name="MonthWiseEmpRosterReportFB" property="headerMsg" /></td>

				</tr>


				<br>
				<br>

				<tr>
					<td class="reportDate"><%=printingDateAndTime%></td>
				</tr>
			</table>
			--><br>
			<br>

<logic:notEqual value="ALL" name="MonthWiseEmpRosterReportFB"
					property="areaCode">
			<table width="100%" cellpadding="1" cellspacing="1" border="1"
				bordercolor="black" style="color: black;">


				



					<tr>
						<td width="1%" class="reportColumnHeader">Sl.No.</td>

						<td width="25%" class="reportColumnHeader">
						Employees(Designation)</td>
						<td width="15%" class="reportColumnHeader">
						Contact No.</td>

		<logic:iterate id="headerShiftId" 	name="<%=DutyRosterConfig.HEADER_LIST_OF_SHIFTS_FOR_REPORT%>" 	type="java.lang.String">

							<td class="reportColumnHeader"><%=headerShiftId%></td>

						</logic:iterate>
					</tr>

		<logic:iterate id="empWiseEntry" 	name="<%=DutyRosterConfig.MAP_FOR_MONTHWISE_EMP_ROSTER_REPORT_DETAILS %>" 	type="java.util.Map.Entry" indexId="index">


						<bean:define id="empWiseKey" name="empWiseEntry" property="key"
							type="java.lang.String"></bean:define>
						<bean:define id="shiftMap" name="empWiseEntry" property="value"
							type="java.util.LinkedHashMap"></bean:define>
						<%
							String[] empDetails = null;
														empDetails = empWiseKey.split("@");
						%>
						<tr >

							<td width="1%" class="reportData"><%=index + 1%></td>

							<td width="5%" class="reportData"><%=empDetails[1].split("#")[0]%></td>
							<td width="5%" class="reportData"><%=empDetails[1].split("#")[1]%></td>

							<logic:iterate id="shiftWiseEntry" name="shiftMap"
								type="java.util.Map.Entry">

								<bean:define id="shiftWiseKey" name="shiftWiseEntry"
									property="key"></bean:define>
								<bean:define id="daysList" name="shiftWiseEntry"
									property="value" type="java.lang.String"></bean:define>


								<td class="reportData"><%=daysList%></td>


							</logic:iterate>



						</tr>


					</logic:iterate>




			


			</table>
<br/><br/>
<table width="100%" cellpadding="1" cellspacing="1"
				style="color: black;">
<tr>
<td align="left" style="font-size:15px; font:Arial; font:bold;">
Instructions
</td>
<td align="right" style="font-size:15px; font:Arial; font:bold;">
Incharge
</td>
</tr>
<tr>
<td colspan="2" align="right"  style="font-size:10px; font:Arial;">
<bean:write name="MonthWiseEmpRosterReportFB" property="incharge" />
</td>
</tr>
</table>
	</logic:notEqual>





			<logic:equal value="ALL" name="MonthWiseEmpRosterReportFB"
				property="areaCode">

				<table width="100%" cellpadding="1" cellspacing="1" border="1"
					bordercolor="black" style="color: black;">

					<tr>
						<td width="1%" class="reportColumnHeader">S.No.</td>
						<td width="15%" class="reportColumnHeader">Area Name</td>
						<td width="15%" class="reportColumnHeader">Employee Name</td>
						<logic:iterate id="shiftNameId"
							name="<%=DutyRosterConfig.HEADER_LIST_OF_SHIFTS_FOR_REPORT %>"
							type="java.lang.String">
							<td class="reportColumnHeader"><%=shiftNameId%></td>
						</logic:iterate>
					</tr>


					<%
						int counter = 0;
					%>
					<logic:iterate id="areaMap"
						name="<%=DutyRosterConfig.MAP_FOR_MONTHWISE_EMP_ROSTER_REPORT_DETAILS %>"
						type="java.util.Map.Entry" indexId="idx">

						<bean:define id="areaName" name="areaMap" property="key"
							type="java.lang.String"></bean:define>
						<bean:define id="empMap" name="areaMap" property="value"
							type="java.util.LinkedHashMap"></bean:define>


						<logic:iterate id="empMapEntry" name="empMap"
							type="java.util.Map.Entry">
							<bean:define id="empName" name="empMapEntry" property="key"
								type="java.lang.String"></bean:define>
							<bean:define id="shiftMap" name="empMapEntry" property="value"
								type="java.util.LinkedHashMap"></bean:define>


							<%
								String[] areaNameArray = areaName
																			.split("@");
							%>
							<%
								String[] empNameArray = empName
																			.split("@");
							%>

							<tr>


								<td class="reportData"><%=++counter%></td>
								<td class="reportData"><%=areaNameArray[1]%></td>
								<td class="reportData"><%=empNameArray[1]%></td>

								<logic:iterate id="shiftMapEntry" name="shiftMap"
									type="java.util.Map.Entry">
									<bean:define id="shiftId" name="shiftMapEntry" property="key"></bean:define>
									<bean:define id="days" name="shiftMapEntry" property="value"
										type="java.lang.String"></bean:define>


									<td class="reportData"><%=days%></td>




								</logic:iterate>
							</tr>
						</logic:iterate>


					</logic:iterate>


				</table>


			</logic:equal>


		</logic:notEmpty>



		<br>

		<logic:notEmpty
			name="<%=DutyRosterConfig.MAP_FOR_MONTHWISE_EMP_ROSTER_REPORT_DETAILS %>">

			<logic:notEmpty
				name="<%=DutyRosterConfig.MAP_OF_ROSTER_PRINT_DETAILS %>">


				<logic:iterate id="rosterPrintMapId"
					name="<%=DutyRosterConfig.MAP_OF_ROSTER_PRINT_DETAILS %>"
					type="java.util.Map.Entry">

					<bean:define id="rosterPrintMapKey" name="rosterPrintMapId"
						property="key" type="java.lang.String"></bean:define>
					<bean:define id="rosterOrderMap" name="rosterPrintMapId"
						property="value" type="java.util.LinkedHashMap"></bean:define>

					<%
						String[] propertyTypeConcate = rosterPrintMapKey
														.split("@");
												String alignMent = "";

												if (propertyTypeConcate[1].equals("1"))
													alignMent = "left";
												else if (propertyTypeConcate[1].equals("2"))
													alignMent = "right";
												else if (propertyTypeConcate[1].equals("3"))
													alignMent = "center";

												String displayTitle = "";

												if (propertyTypeConcate[0].equals("1"))
													displayTitle = "Instructions";
												else if (propertyTypeConcate[0].equals("2"))
													displayTitle = "Roster By";
												else if (propertyTypeConcate[0].equals("3"))
													displayTitle = "Copy To";
					%>






					<his:ContentTag>




						<div align="<%=alignMent%>"><b><%=displayTitle%></b></div>

						<table width="100%" border="0" cellspacing="0" cellpadding="0">

							<logic:iterate id="rosterOrderMapId" name="rosterOrderMap"
								type="java.util.Map.Entry">

								<bean:define id="rosterOrderMapKey" name="rosterOrderMapId"
									property="key" type="java.lang.String"></bean:define>
								<bean:define id="displayValue" name="rosterOrderMapId"
									property="value" type="java.lang.String"></bean:define>


								<tr>
									<td width="100%">

									<div align="<%=alignMent%>"><%=displayValue%></div>

									</td>


								</tr>

							</logic:iterate>
						</table>
					</his:ContentTag>

				</logic:iterate>






			</logic:notEmpty>

		</logic:notEmpty>

		<logic:equal name="MonthWiseEmpRosterReportFB" property="hmode"
			value="GET_REPORT">
			<logic:empty
				name="<%=DutyRosterConfig.MAP_FOR_MONTHWISE_EMP_ROSTER_REPORT_DETAILS %>">
				<div align="left"
					style="color: red; font-family: arial; font-size: 20px; font-weight: bold;">
				No Roster Found</div>

			</logic:empty>
		</logic:equal>



	</his:ContentTag>

	<logic:empty
		name="<%=DutyRosterConfig.MAP_FOR_MONTHWISE_EMP_ROSTER_REPORT_DETAILS %>">
		<his:ButtonToolBarTag>
			<span id="saveDiv"> <img class="button"
				src='<his:path src="/../HIS/hisglobal/images/buttons/btn-view.png"/>' tabindex="1"
				style="cursor: pointer" onclick="submitPage('GET_REPORT')"
				onkeypress="if(event.keyCode==13) submitPage('GET_REPORT')"> <img
				class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>'
				tabindex="1" style="cursor: pointer" onclick="clearForm()"
				onkeypress="if(event.keyCode==13) clearForm()"> <img
				class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'
				tabindex="1" style="cursor: pointer" onclick="cancelFunc()"
				onkeypress="if(event.keyCode==13) cancelFunc()"> </span>
		</his:ButtonToolBarTag>
	</logic:empty>


	<his:status />

	<html:hidden name="MonthWiseEmpRosterReportFB" property="hmode" />
	<html:hidden name="MonthWiseEmpRosterReportFB" property="headerMsg" />


</html:form>
</body>
</html>


