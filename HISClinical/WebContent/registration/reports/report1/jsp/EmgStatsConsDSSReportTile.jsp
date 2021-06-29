<%
	try
	{
%>
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@page import="registration.RegistrationConfig"%>
<%@page import="java.util.Map"%>
<%@page import="registration.reports.report1.controller.util.DSSReportDataCollectorUTIL"%>
<%@page import="hisglobal.hisconfig.Config"%>
<%@page import="hisglobal.presentation.WebUTIL"%>
<%@page import="java.util.Date"%>
<%@page import="registration.vo.DSSEpisodeStatisticVO.GenderCount"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<his:css src="/hisglobal/css/Color.css" />
<his:css src="/hisglobal/css/master.css" />
<his:css src="/hisglobal/css/hisStyle.css" />
<his:css src="/hisglobal/css/hisStyleExt.css" />
<his:css src="/hisglobal/css/calendar-blue2.css" />
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<his:javascript src="/registration/js/calendar.js" />
<his:javascript src="/registration/js/registration.js" />
<style type="text/css">
@media print { 
div 
	{ 
	display: none; 
	}
} </style>
<script Language=javascript>
function printPage() 
{
var frameElement = parent.document.getElementById("frmMain");
var win = frameElement.contentWindow ;
win.print(); 
} 

function backButton() 
{ 
  history.back(); 
 } 
</script>
<body>
<html:form action="/reports/report1/dssParameterConfigurator">
	<his:TitleTag name="OPD Statistics">

	</his:TitleTag>
	

	<his:statusRecordFound>
	<%
		Map mpReportData = (Map) session.getAttribute(RegistrationConfig.DSS_REPORTS_DATA_MAP);
		Map mpSortedDates = (Map) session.getAttribute(RegistrationConfig.DSS_REPORTS_LIST_HOS_WISE_SORTED_DATES);
		if(mpReportData!=null && mpReportData.keySet().size()>0)
		{
	%>
		<table width="100%"><tr><td>
			<div id="noPrint" align="right">
			<img class="button" src='/HIS/hisglobal/images/buttons/btn-pnt.png' tabindex="1" style=cursor:pointer onclick="printPage()" onkeypress="if(event.keyCode==13)  printPage()">
			<img class="button" src='/HIS/hisglobal/images/buttons/btn-ccl.png' tabindex="1" style=cursor:pointer onclick="backButton()" onkeypress="if(event.keyCode==13) backButton()">
			</div>
		</td></tr></table>
		
		<table width="70%" align="center">
			<tr>
				<th align="right" colspan="4">
					<font size="1">Report Date : <%=WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy")%></font>
					<br>
					<font size="1">Report Time : <%=WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "HH:mm")%></font>
				</th>
			</tr>
			<tr>
				<th align="center" colspan="4">
					<font size="2">No. of Patients Registered in Emergency</font>
				</th>
			</tr>
			<logic:equal name="DSSParameterConfiguratorFB" property="strDurationMode" value="<%=RegistrationConfig.DSS_REPORTS_DURATION_MODE_DATEWISE%>">
			<tr>
				<th align="right" colspan="2">From Date: <bean:write name="DSSParameterConfiguratorFB" property="strFromDate"/></th>
				<th align="left" colspan="2">&nbsp;&nbsp;&nbsp;To Date: <bean:write name="DSSParameterConfiguratorFB" property="strToDate"/></th>
			</tr>
			</logic:equal>
			<logic:equal name="DSSParameterConfiguratorFB" property="strDurationMode" value="<%=RegistrationConfig.DSS_REPORTS_DURATION_MODE_MONTHLY%>">
			<tr>
				<bean:define id="fromMon" name="DSSParameterConfiguratorFB" property="strFromMonth"  type="java.lang.String"></bean:define>
				<bean:define id="toMon" name="DSSParameterConfiguratorFB" property="strToMonth"  type="java.lang.String"></bean:define>
				<th align="right" colspan="2">From Month: <%=DSSReportDataCollectorUTIL.arrMonths[Integer.parseInt(fromMon)] %>,  <bean:write name="DSSParameterConfiguratorFB" property="strFromYear"/></th>
				<th align="left" colspan="2">&nbsp;&nbsp;&nbsp;To Month: <%=DSSReportDataCollectorUTIL.arrMonths[Integer.parseInt(toMon)] %>, <bean:write name="DSSParameterConfiguratorFB" property="strToYear"/></th>
			</tr>
			</logic:equal>
			<logic:equal name="DSSParameterConfiguratorFB" property="strDurationMode" value="<%=RegistrationConfig.DSS_REPORTS_DURATION_MODE_YEARLY%>">
			<tr>
				<th align="right" colspan="2">From Year: <bean:write name="DSSParameterConfiguratorFB" property="strFromYear"/></th>
				<th align="left" colspan="2">&nbsp;&nbsp;&nbsp;To Year: <bean:write name="DSSParameterConfiguratorFB" property="strToYear"/></th>
			</tr>
			</logic:equal>
		</table>
		<%
			GenderCount objGenCntGrandTot = (GenderCount) ((Map) (mpReportData.get("GT"))).get("GT");
		%>
		<table width="100%" cellpadding="0" cellspacing="0" border="0">
		<%
			if(mpReportData.get("0")!=null)
			{
				Map mpHosData = (Map) mpReportData.get("0");
				GenderCount genCountHosTot = (GenderCount) mpHosData.get("HT"); 
		%>
				<tr>
					<th colspan="7" align="center">
						<b><font size="2"> Consolidated Hospital Statistics </font></b>
					</th>
				</tr>
				<tr><td colspan="7" align="center" height="18px"></td></tr>
				<tr>
					<td align="center" style="border: 1px solid;" rowspan="2" valign="top"><b>S. No.</b></td>
					<td align="center" style="border: 1px solid;" rowspan="2" valign="top"><b>
						<logic:equal name="DSSParameterConfiguratorFB" property="strDurationMode" value="<%=RegistrationConfig.DSS_REPORTS_DURATION_MODE_DATEWISE%>">
							Date
						</logic:equal>
						<logic:equal name="DSSParameterConfiguratorFB" property="strDurationMode" value="<%=RegistrationConfig.DSS_REPORTS_DURATION_MODE_MONTHLY%>">
							Month
						</logic:equal>
						<logic:equal name="DSSParameterConfiguratorFB" property="strDurationMode" value="<%=RegistrationConfig.DSS_REPORTS_DURATION_MODE_YEARLY%>">
							Year
						</logic:equal>
					</b></td>
					<td align="center" style="border: 1px solid;" rowspan="2" valign="bottom"><b>Male</b></td>
					<td align="center" style="border: 1px solid;" rowspan="2" valign="bottom"><b>Female</b></td>
					<td align="center" style="border: 1px solid;" colspan="2"><b>Children</b></td>
					<td align="center" style="border: 1px solid;" rowspan="2" valign="top"><b>Total</b></td>
				</tr>
				<tr>
					<td align="center" style="border: 1px solid;"><b>Male</b></td>
					<td align="center" style="border: 1px solid;"><b>Female</b></td>
				</tr>
				<tr><td colspan="7" align="center" height="18px"></td></tr>
				<tr>
					<td colspan="7" align="left">
						<b><font size="1">New Patients</font></b>
					</td>
				</tr>
				<%
					List sortedDates = (List) mpSortedDates.get("0");
					Iterator itrDate = sortedDates.iterator();
					int dateSNo = 1;
					while(itrDate.hasNext())
					{
						String strDate = (String) itrDate.next();
						if(!strDate.equals("HT"))
						{
						GenderCount genCount = (GenderCount) mpHosData.get(strDate);
				%>
					<tr>
						<td style="border: 1px solid;"><b><%=dateSNo%>.</b></td>
						<td style="border: 1px solid;"><b><%=strDate %></b></td>
						<td align="center" style="border: 1px solid;"><b><%=genCount.getMaleNew()%></b></td>
						<td align="center" style="border: 1px solid;"><b><%=genCount.getFemaleNew()%></b></td>
						<td align="center" style="border: 1px solid;"><b><%=genCount.getChildMaleNew()%></b></td>
						<td align="center" style="border: 1px solid;"><b><%=genCount.getChildFemaleNew()%></b></td>
						<%	
							int nGrpTot = 0;
							nGrpTot += genCount.maleNew;
							nGrpTot += genCount.femaleNew;
							nGrpTot += genCount.childMaleNew;
							nGrpTot += genCount.childFemaleNew;
						%>
						<td align="center" style="border: 1px solid;"><b><%=nGrpTot%></b></td>
					</tr>
				<%
						dateSNo++;
						}
					}
				%>
					<tr>
						<td colspan="2" style="border: 1px solid;"><b>Total:</b></td>
						<td align="center" style="border: 1px solid;"><b><%=genCountHosTot.getMaleNew()%></b></td>
						<td align="center" style="border: 1px solid;"><b><%=genCountHosTot.getFemaleNew()%></b></td>
						<td align="center" style="border: 1px solid;"><b><%=genCountHosTot.getChildMaleNew()%></b></td>
						<td align="center" style="border: 1px solid;"><b><%=genCountHosTot.getChildFemaleNew()%></b></td>
						<%	
							int nHosTot = 0;
							nHosTot += genCountHosTot.maleNew;
							nHosTot += genCountHosTot.femaleNew;
							nHosTot += genCountHosTot.childMaleNew;
							nHosTot += genCountHosTot.childFemaleNew;
						%>
						<td align="center" style="border: 1px solid;"><b><%=nHosTot%></b></td>
					</tr>
				<tr><td colspan="7" align="center" height="18px"></td></tr>
				<tr>
					<td colspan="7" align="left">
						<b><font size="1">Old Patients</font></b>
					</td>
				</tr>
				<%
					itrDate = sortedDates.iterator();
					dateSNo = 1;
					while(itrDate.hasNext())
					{
						String strDate = (String) itrDate.next();
						if(!strDate.equals("HT"))
						{
						GenderCount genCount = (GenderCount) mpHosData.get(strDate);
				%>
					<tr>
						<td style="border: 1px solid;"><b><%=dateSNo%>.</b></td>
						<td style="border: 1px solid;"><b><%=strDate %></b></td>
						<td align="center" style="border: 1px solid;"><b><%=genCount.getMaleOld()%></b></td>
						<td align="center" style="border: 1px solid;"><b><%=genCount.getFemaleOld()%></b></td>
						<td align="center" style="border: 1px solid;"><b><%=genCount.getChildMaleOld()%></b></td>
						<td align="center" style="border: 1px solid;"><b><%=genCount.getChildFemaleOld()%></b></td>
						<%	
							int nGrpTot = 0;
							nGrpTot += genCount.maleOld;
							nGrpTot += genCount.femaleOld;
							nGrpTot += genCount.childMaleOld;
							nGrpTot += genCount.childFemaleOld;
						%>
						<td align="center" style="border: 1px solid;"><b><%=nGrpTot%></b></td>
					</tr>
				<%
						dateSNo++;
						}
					}
				%>
					<tr>
						<td colspan="2" style="border: 1px solid;"><b>Total:</b></td>
						<td align="center" style="border: 1px solid;"><b><%=genCountHosTot.getMaleOld()%></b></td>
						<td align="center" style="border: 1px solid;"><b><%=genCountHosTot.getFemaleOld()%></b></td>
						<td align="center" style="border: 1px solid;"><b><%=genCountHosTot.getChildMaleOld()%></b></td>
						<td align="center" style="border: 1px solid;"><b><%=genCountHosTot.getChildFemaleOld()%></b></td>
						<%	
							nHosTot = 0;
							nHosTot += genCountHosTot.maleOld;
							nHosTot += genCountHosTot.femaleOld;
							nHosTot += genCountHosTot.childMaleOld;
							nHosTot += genCountHosTot.childFemaleOld;
						%>
						<td align="center" style="border: 1px solid;"><b><%=nHosTot%></b></td>
					</tr>
					<tr><td colspan="7" align="center" height="18px"></td></tr>
			<%
				}
			%>

				<tr>
					<td colspan="2" style="border: 1px solid;"><b>Grand Total:</b></td>
					<td align="center" style="border: 1px solid;"><b><%=objGenCntGrandTot.getMaleNew()+objGenCntGrandTot.getMaleOld()%></b></td>
					<td align="center" style="border: 1px solid;"><b><%=objGenCntGrandTot.getFemaleNew()+objGenCntGrandTot.getFemaleOld()%></b></td>
					<td align="center" style="border: 1px solid;"><b><%=objGenCntGrandTot.getChildMaleNew()+objGenCntGrandTot.getChildMaleOld()%></b></td>
					<td align="center" style="border: 1px solid;"><b><%=objGenCntGrandTot.getChildFemaleNew()+objGenCntGrandTot.getChildFemaleOld()%></b></td>
					<%	
						int nGrandTot = 0;
						nGrandTot += objGenCntGrandTot.maleNew + objGenCntGrandTot.maleOld;
						nGrandTot += objGenCntGrandTot.femaleNew + objGenCntGrandTot.femaleOld;
						nGrandTot += objGenCntGrandTot.childMaleNew + objGenCntGrandTot.childMaleOld;
						nGrandTot += objGenCntGrandTot.childFemaleNew + objGenCntGrandTot.childFemaleOld;
					%>
					<td align="center" style="border: 1px solid;"><b><%=nGrandTot%></b></td>
				</tr>
			</table>
		<%
			}
			else
			{
		%>
		<div align="center" style="vertical-align: middle;">
			<b><font color="#FF0000" size="3"> No Data Found for the Report</font></b> &nbsp;&nbsp;&nbsp;&nbsp;
			<img class="button" src='/HIS/hisglobal/images/buttons/btn-ccl.png' tabindex="1" style="cursor:pointer" onclick="backButton()" onkeypress="if(event.keyCode==13) backButton()">
		</div>
		<%
			}
		%>
	</his:statusRecordFound>
	<his:statusUnsuccessfull>
		<div align="center" style="vertical-align: middle;">
			<b><font color="#FF0000" size="3"> No Data Found for the Report</font></b> &nbsp;&nbsp;&nbsp;&nbsp;
			<img class="button" src='/HIS/hisglobal/images/buttons/btn-ccl.png' tabindex="1" style="cursor:pointer" onclick="backButton()" onkeypress="if(event.keyCode==13) backButton()">
		</div>
	</his:statusUnsuccessfull>
	<html:hidden name="DSSParameterConfiguratorFB" property="strMode" />
	<html:hidden name="DSSParameterConfiguratorFB" property="strIsConsolidated" />


</html:form>
</body>
<%
	}
	catch (Exception e)
	{
		e.printStackTrace();
	}
%>