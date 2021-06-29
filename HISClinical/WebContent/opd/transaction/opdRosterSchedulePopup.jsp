<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>

<%@page import="java.util.List"%>
<%@page import="hisglobal.utility.Entry"%>
<%@page import="hisglobal.presentation.Status"%>
<%@page import="hisglobal.hisconfig.Config"%>
<%@page import="opd.OpdConfig"%>

<%@page import="hisglobal.tools.tag.PaginationFB"%>
<%@page import="opd.transaction.controller.fb.OpdRosterSchedulePopupFB"%>
<%@page import="hisglobal.tools.tag.PaginationTag"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/master.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/>
<his:css src="/hisglobal/css/calendar-blue2.css"/>
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<his:javascript src="/hisglobal/js/utilityFunctions.js"/>

<script type="text/javascript">
function submitPage(mode)
{
	document.getElementsByName("hmode")[0].value = mode;
	document.forms[0].submit();
}

function closeForm()
{
	self.close();
}

function isFormClose()
{
	var formclose=true;
	<%
		Status objStatus=(Status)request.getAttribute(Config.STATUS_OBJECT);
		if(objStatus.contains(Status.NEW))
		{
	%>
		formclose=false;
	<%
		}
	%>
	
	if(formclose)
	{
		if(!window.opener.closed)
		{
			self.close();
		}
	}
}

function setSchedule()
{
	var date="";
	for(var i=0;i<document.getElementsByName("scheduleDate").length;i++)
	{
		if(document.getElementsByName("scheduleDate")[i].checked==true)
			date=document.getElementsByName("scheduleDate")[i].value;
	}
	if(date!="")
	{
		var dateObj = convertStrToDate(date,"dd-MM-yyyy");
		date = convertDateToStr(dateObj,"dd-Mon-yyyy");
		if(!window.opener.closed)
			opener.<bean:write name="OpdRosterSchedulePopupFB" property="targetFunction" filter="false"/>date);
        /* Added by SingaraVelan for Apt Tag Integration */
		if(window.opener.document.getElementsByName("appointmentDate") && window.opener.document.getElementsByName("appointmentDate")[0] && window.opener.document.getElementsByName("appointmentDate")[0].value)
			window.opener.document.getElementsByName("appointmentDate")[0].value=date;		
		closeForm();
		/* Added by SingaraVelan for Apt Tag Integration */
		window.opener.setAppointmentTag();//getViewSummaryAptSlotDetails(); /* Commented and Changed By Pragya as per Non Appointment Based Unit Check 2015.12.01*/
	}
}

function doPagination(e,p)
{
	document.getElementsByName('currentPage')[0].value=p;
	submitPage("PAGINATION");
}

</script>
</head>

<body onload="isFormClose()">

<html:form action="/opdRosterSchedulePopup">
	<his:statusNew>
		<%
			PaginationFB fbPage= new PaginationFB();
			pageContext.setAttribute("fbPagination", fbPage);
			fbPage.setCurrentPage(((OpdRosterSchedulePopupFB)request.getAttribute("OpdRosterSchedulePopupFB")).getCurrentPage());
			fbPage.setObjArrName(OpdConfig.OPD_ROSTER_SCHEDULE_POPUP_LIST_OF_SCHEDULE_DATES);
			fbPage.setAppendInTitle("Schedule Dates");
			fbPage.setMaxRecords(15);
			
			List lstScheduleDates = (List)session.getAttribute(OpdConfig.OPD_ROSTER_SCHEDULE_POPUP_LIST_OF_SCHEDULE_DATES); 
			if(lstScheduleDates.size()>0)
			{
		%>
		<his:PaginationTag name="fbPagination"></his:PaginationTag>
		<his:ContentTag>
			<table width="100%" cellpadding="0" cellspacing="1">
				<tr>
					<td width="10%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="select"/>
							</font>
						</div>
					</td>
					<td width="90%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="scheduleDate"/>
							</font>
						</div>
					</td>
				</tr>
				<%
					int startIndex = ((Integer)request.getAttribute(PaginationTag.PAGINATION_START_INDEX)).intValue();
					int endIndex = ((Integer)request.getAttribute(PaginationTag.PAGINATION_END_INDEX)).intValue();
					
				%>
				<logic:iterate id="entObj" name="<%=OpdConfig.OPD_ROSTER_SCHEDULE_POPUP_LIST_OF_SCHEDULE_DATES%>" offset="<%=Integer.toString(startIndex)%>" length="<%=Integer.toString(endIndex-startIndex+1)%>" type="hisglobal.utility.Entry">
					<tr>
						<td class="tdfont">
							<div align="center">
								<html:radio name="OpdRosterSchedulePopupFB" property="scheduleDate" value="<%=entObj.getLabel() %>" onclick="setSchedule()"></html:radio>
							</div>
						</td>
						<td class="tdfont">
							<div align="left">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<bean:write name="entObj" property="value"/>
								</font>
							</div>
						</td>
					</tr>
				</logic:iterate>
			</table>
		</his:ContentTag>
		<%	} %>
	</his:statusNew>
	
		<his:ButtonToolBarTag>
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style="cursor:pointer" tabindex="1" onclick ="closeForm()" onkeypress="if(event.keyCode==13) closeForm()">
		</his:ButtonToolBarTag>

		<html:hidden name="OpdRosterSchedulePopupFB" property="hmode"/>
		<html:hidden name="OpdRosterSchedulePopupFB" property="departmentUnitCode"/>
		<html:hidden name="OpdRosterSchedulePopupFB" property="userId"/>
		<html:hidden name="OpdRosterSchedulePopupFB" property="entryDate"/>
		<html:hidden name="OpdRosterSchedulePopupFB" property="targetFunction"/>
		
		<html:hidden name="OpdRosterSchedulePopupFB" property="currentPage"/>
		
<his:status/>

</html:form>
</body>
</html>