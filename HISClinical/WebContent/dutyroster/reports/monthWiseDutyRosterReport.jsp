<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@	taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="java.util.*"%>
<%@page import="java.text.DateFormatSymbols" %>
<%@page import="hisglobal.hisconfig.Config"%>
<%@page import="hisglobal.utility.Entry"%>
<%@page import="hisglobal.vo.RosterReliverDtlVO" %>
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
@import url(../css/calendar-blue2.css);
</style>
<his:javascript src="/registration/js/registration.js" />
<his:javascript src="/dutyroster/js/monthWiseDutyRosterReport.js" />


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
%>

<html:form action="/reports/monthWiseDutyRosterReport">

	<logic:empty name="<%=DutyRosterConfig.MAP_FOR_MONTHWISE_DUTY_ROSTER_REPORT_DETAILS %>">

		<his:TransactionContainer>

			<his:css src="/hisglobal/css/Color.css" />

			<his:TitleTag name="MonthWise Duty Roster Report">
			</his:TitleTag>

			<his:ContentTag>
				<table width="100%" border="0" cellspacing="1" cellpadding="0">
					<!--<div align="right" style="background-color: #FFEBD5"><b>Printing
					Format:</b>&nbsp;Combined<html:radio property="printingFormat"
						value="C" name="monthWiseDutyRosterReportFB" />Seperate<html:radio
						property="printingFormat" value="S"
						name="monthWiseDutyRosterReportFB" /></div>
					-->
					<html:hidden name="monthWiseDutyRosterReportFB" property="printingFormat" value="C" />	
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
							name="monthWiseDutyRosterReportFB" tabindex="1"
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
							name="monthWiseDutyRosterReportFB" tabindex="1"
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
							name="monthWiseDutyRosterReportFB" tabindex="1"
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
							name="monthWiseDutyRosterReportFB" tabindex="1"
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
						<div id="divAreaCodeId" align="left"><html:select property="areaCode"
							name="monthWiseDutyRosterReportFB" tabindex="1"
							styleClass="textbox" onchange="">
							<html:option value="-1">Select Value</html:option>
							<logic:present 	name="<%=DutyRosterConfig.DUTY_ROSTER_MASTER_LIST_OF_AREA%>">
							<%--
							<% if (areaList != null && areaList.size() > 1) { %>
								<html:option value="ALL">ALL</html:option>
							<% } %>--%>
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
							name="monthWiseDutyRosterReportFB" tabindex="1"
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
	
	<logic:notEmpty name="<%=DutyRosterConfig.MAP_FOR_MONTHWISE_DUTY_ROSTER_REPORT_DETAILS %>">

		<div id="noPrint" align="right"><img class="button"
			src='<his:path src="/hisglobal/images/btn-pnt.png"/>' tabindex="1"
			style="cursor: pointer" onclick="printPage()"
			onkeypress="if(event.keyCode==13) printPage()"> <img
			class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'
			tabindex="1" style="cursor: pointer" onclick="backButton()"
			onkeypress="if(event.keyCode==13) backButton()"></div>

		<table  width="100%" cellspacing="1" cellpadding="0">
				<tr>
					<td width="100%"  style="font-size:10px; font:Arial;" align="right">
						<%=printingDateAndTime%>
					</td>
				</tr>
				<tr >
		 			<td class="" width="50%" colspan="2"><b>
				 	 	<div align="center" style="font-size:17px;font:Arial">
							Guru Gobind Singh Govt. Hospital
						</div>	</b>		
				  	</td>
				 </tr>
				 <tr>
					 <td class="" width="50%" colspan="2"><b>
					 	<div align="center" style="font-size:15px; font:Arial; font:bold;">
							Raghubir Nagar, New Delhi-110027- (INDIA)	
						</div> </b>	
					  </td>
				 </tr> 
				<tr>
					 <td class="" width="50%" colspan="2">
					 	<div align="center" style="font-size:15px; font:Arial; font:bold;"><b>
						Department of <bean:write name="monthWiseDutyRosterReportFB" property="areaName"/>	
						</div> </b>
					  </td>
				</tr> 
				<tr>
					<td class="" width="50%" colspan="2"><b>
					<div align="center" style="font-size:15px; font:Arial; font:bold;">
							Duty Roster of <bean:write name="monthWiseDutyRosterReportFB" property="rosterName"/>,
							For the Month of <bean:write name="monthWiseDutyRosterReportFB" property="monthChar"/>
					</div> </b>
				  	</td>
				</tr>
		</table>
	
		<logic:notEqual value="ALL" name="monthWiseDutyRosterReportFB" property="areaCode">
				
			<table width="100%" cellpadding="1" cellspacing="1" border="1" bordercolor="black" style="color: black;">
	
				<tr>
					<td width="15%" style="font-size:16px; font:Arial; font:bold;"><b>Date</b></td>
					<td width="15%" style="font-size:16px; font:Arial; font:bold;"><b>Day</b></td>
	
					<logic:iterate id="headerShiftId" 	name="<%=DutyRosterConfig.HEADER_LIST_OF_SHIFTS_FOR_REPORT%>" 	type="java.lang.String">
						<td style="font-size:16px; font:Arial; font:bold;"><b><%=headerShiftId%></b></td>
					</logic:iterate>
				</tr>
				<%
				DateFormatSymbols dfs = new DateFormatSymbols(Locale.ENGLISH);
				String weekdays[] = dfs.getWeekdays();
				int dayCode;
				%>	
				<logic:iterate id="dateWiseEntry" 	name="<%=DutyRosterConfig.MAP_FOR_MONTHWISE_DUTY_ROSTER_REPORT_DETAILS %>" 	type="java.util.Map.Entry" indexId="index">
	
						<bean:define id="dateKey" name="dateWiseEntry" property="key" type="java.lang.Integer"></bean:define>
						<bean:define id="shiftMap" name="dateWiseEntry" property="value" type="java.util.LinkedHashMap"></bean:define>
						<bean:define id="strMonthId" name="monthWiseDutyRosterReportFB" property="month" type="java.lang.String"></bean:define>
						<bean:define id="strYearId" name="monthWiseDutyRosterReportFB" property="year" type="java.lang.String"></bean:define>
						<% 
							Calendar dailyCalendar =new GregorianCalendar();	
							dailyCalendar.set(Integer.parseInt(strYearId),Integer.parseInt(strMonthId)-1, dateKey);
							
							dayCode=dailyCalendar.get(Calendar.DAY_OF_WEEK);
							%>
						<tr >
							
							
							<td width="15%" style="font-size:15px; font:Arial; font:bold;"><%=dateKey%>-<%=strMonthId%>-<%=strYearId %></td>
							<td width="15%" style="font-size:15px; font:Arial; font:bold;"><%=weekdays[dayCode]%></td>
	
							<logic:iterate id="shiftWiseEntry" name="shiftMap" type="java.util.Map.Entry">
	
								<bean:define id="shiftWiseKey" name="shiftWiseEntry" property="key"></bean:define>
								<bean:define id="empShortList" name="shiftWiseEntry" property="value" type="java.lang.String"></bean:define>
	
								<td style="font-size:15px; font:Arial; font:bold;"><%=empShortList%></td>
	
							</logic:iterate>
						</tr>
	
					</logic:iterate>
	
			</table>
			<table width="100%" cellpadding="1" cellspacing="1" style="color: black;">
				<tr>
				<% 
				Map mapEmpSeq= (Map)request.getSession().getAttribute(DutyRosterConfig.DUTY_ROSTER_EMP_SEQ_MAP);
				int nCounterTd=0;
				Iterator itr=mapEmpSeq.values().iterator();
				while(itr.hasNext()){
					Entry empSeqEntObj=(Entry)itr.next();
					nCounterTd++; %>
					
					<td align="left" style="font-size:15px; font:Arial; font:bold;">
						<%=empSeqEntObj.getLabel() %> - <%=empSeqEntObj.getValue().split("\\#")[0] %>[<%=empSeqEntObj.getValue().split("\\#")[1] %>]
					</td>
					<%if((nCounterTd % 2)==0){%>
						</tr><tr>
					<%} 
				}
				if((nCounterTd % 2)!=0){%>
					<td></td><%
				} 
				%>
				</tr>
			</table>
			<table width="100%" cellpadding="1" cellspacing="1" style="color: black;">
				
				<%
					List lstRosterReliverDtlVO= (List)request.getSession().getAttribute(DutyRosterConfig.EMP_RELIVER_LIST);
				
					if(lstRosterReliverDtlVO!=null && lstRosterReliverDtlVO.size()>0){
						for(int j=0; j<lstRosterReliverDtlVO.size(); j++){
							RosterReliverDtlVO voRosterReliverDtl= (RosterReliverDtlVO)lstRosterReliverDtlVO.get(j);%>
							<tr>
								<td width="10%" style="font-size:15px; font:Arial; font:bold;">Reliver <%=(j+1) %></td>
								<td width="90%" style="font-size:15px; font:Arial; font:bold;">
								<%=voRosterReliverDtl.getReliverEmp().split("\\#")[0]%>&nbsp;
								(<%=voRosterReliverDtl.getReliverEmp().split("\\#")[1]%>)
								</td>
							</tr>	
							<%
						}
					}else{%>
						<tr>
							<td colspan="2" style="font-size:15px; font:Arial; font:bold;"><div align="center">No Reliver Found.</div></td>
						</tr>
						<%
					}
				%>
			</table>
		<!-- <table width="100%" cellpadding="1" cellspacing="1" style="color: black;">
				<tr>
					<td colspan="2" align="left" style="font-size:15px; font:Arial; font:bold;"><b>
						1. EXCHANGE OF DUTIES ONLY PERMITTED ONLY IN EXTRAORDINARY CASES WITH PRIO APPROVAL OF 
						UNDESIGNED, IF A JR DOESNOT REPORT FOR SUCH DUTY BOTH THE JRs SHALL BE MARKED ABSENT.<br>
						2. JRs CHANGING DUTIES WITHOUT PRIOR APPROVAL WILL BE MARKED ABSENT.<br>
						3. JRs WILL NOT BE ALLOWED TO GO OFF FROM DUTY BEFORE THEY ARE RELIVED BTY THE NEXT JR<BR>
						4. NO LEAVE WILL BE GRANTED IF 2 JRs OF CASUALTY ARE ALREADY ON LEAVE.<BR>
						5. COMPENSATORY OFF SHALL BE GRANTED FOR DUTIES DONE ON NATIONAL HOLIDAYS.<BR>
						6. JRs ON RELIEVER DUTIES SHALL REPORT TO DMS FOR ASSIGNMENT OF DUTIES ON THE DAYS 
						THEY ARE NOT PERFORMING ED DUTIES AGAINST THE 'ON LEAVE' ED JRs<BR>
						8. THIS ISSUE WITH THE APPROVAL OF MS FOR STRICT COMPLIANCE. </b>
						
					</td>
					
				</tr>
				<tr>
					<td align="left"  style="font-size:15px; font:Arial; font:bold;">
						No.F.7/10/3/Med./2013/ED Duty Roster/GGSGH/<br>
						Copy to<br>
						Concerned JRs<br>
						Casualty Notice Board (NS I/C Casualty)<br>
						Nodal Officer HIMS<br>
						Copy to DMS for information<br>
						Copy to MS for information<br>
					</td>
					<td align="right"  style="font-size:15px; font:Arial; font:bold;">
						<bean:write name="monthWiseDutyRosterReportFB" property="incharge" /><br>
						I/C Ed
					</td>
				</tr>
			</table> -->	
		</logic:notEqual>

	</logic:notEmpty>
	<br>
		
		<logic:notEmpty
			name="<%=DutyRosterConfig.MAP_FOR_MONTHWISE_DUTY_ROSTER_REPORT_DETAILS %>">

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

		<logic:equal name="monthWiseDutyRosterReportFB" property="hmode"
			value="GET_REPORT">
			<logic:empty
				name="<%=DutyRosterConfig.MAP_FOR_MONTHWISE_DUTY_ROSTER_REPORT_DETAILS %>">
				<div align="left"
					style="color: red; font-family: arial; font-size: 20px; font-weight: bold;">
				No Roster Found</div>

			</logic:empty>
		</logic:equal>



	</his:ContentTag>

	<logic:empty
		name="<%=DutyRosterConfig.MAP_FOR_MONTHWISE_DUTY_ROSTER_REPORT_DETAILS %>">
		<his:ButtonToolBarTag>
			<span id="saveDiv"> <img class="button"
				src='<his:path src="/hisglobal/images/btn-view.png"/>' tabindex="1"
				style="cursor: pointer" onclick="submitPage('GET_REPORT')"
				onkeypress="if(event.keyCode==13) submitPage('GET_REPORT')"> <img
				class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>'
				tabindex="1" style="cursor: pointer" onclick="clearForm()"
				onkeypress="if(event.keyCode==13) clearForm()"> <img
				class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'
				tabindex="1" style="cursor: pointer" onclick="submitPage('CANCEL')"
				onkeypress="if(event.keyCode==13) submitPage('CANCEL')"> </span>
		</his:ButtonToolBarTag>
	</logic:empty>


	<his:status />

	<html:hidden name="monthWiseDutyRosterReportFB" property="hmode" />
	<html:hidden name="monthWiseDutyRosterReportFB" property="headerMsg" />
	<html:hidden name="monthWiseDutyRosterReportFB" property="startDate" />
	<html:hidden name="monthWiseDutyRosterReportFB" property="endDate" />
	<html:hidden name="monthWiseDutyRosterReportFB" property="rosterName" />


</html:form>
</body>
</html>


