<%--##		Modification Date		: 	14-01-2015
	##		Reason	(CR/PRS)		: 	CR
	##		Modified By				:	AKASH SINGH
--%><%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<%@page import="opd.OpdConfig"%>
<%@page import="opd.transaction.controller.util.GenericPatientProfileUTIL"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@page import="hisglobal.vo.PatientAlertsDetailVO"%>
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<%
	String deskMenuId = request.getParameter("DeskMenuID");
	GenericPatientProfileUTIL.ProfileProforma proforma = (GenericPatientProfileUTIL.ProfileProforma) session
			.getAttribute(OpdConfig.PATIENT_PROFILE_PROFORMA_OBJECT);
	if (proforma != null)
	{
		Map mpAlerts = (Map)proforma.mapProfileOptions.get(deskMenuId);
		List lstAlerts = (List) mpAlerts.get("lstPatientAlertsVO");
%>
<%@page import="java.util.Iterator"%>

<table width="100%" border="1" cellpadding="0" cellspacing="0">
	<tr>
		<td width="100%">
			<div align="left">
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<b><bean:message key="alertsDetail"/>:</b>
				<%
					Iterator lstAlertsItr = lstAlerts.iterator();
					while(lstAlertsItr.hasNext())
					{
						PatientAlertsDetailVO vo = (PatientAlertsDetailVO)lstAlertsItr.next();
				%>
							&nbsp;<%=vo.getAlertName()%> (<%=vo.getDurationDays()%>);
				<%
					}
				%>
				</font>
			</div>
		</td>
	</tr>
</table>			

<%--<table width="100%" border="1" cellpadding="0" cellspacing="0">
	<tr>
	  <td width="100%">
			<table width='100%'>
				<tr>
					<td width="100%" align="left">
						<b>
							<font color="#000000" size="3" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="alertsDetail"/>
							</font>
						</b>
					</td>
				</tr>
			</table>
			
			<table width='100%'>
				<tr>
					<td width='70%' align='center'>
						<b>
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="chronicDisease"/>
							</font>
						</b>
					</td>
					<td width='30%' align='center'>
						<b>
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="duration"/>
							</font>
						</b>
					</td>
					<!-- <td width='10%' align='center'>
						<b>
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="remarks"/>
							</font>
						</b>
					</td> -->
					
					
				</tr>
				<%
					Iterator lstAlertsItr = lstAlerts.iterator();
					while(lstAlertsItr.hasNext())
					{
						PatientAlertsDetailVO vo = (PatientAlertsDetailVO)lstAlertsItr.next();
				%>
				<tr>
					<td align='center'>
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<%=vo.getAlertName()%>
						</font>
					</td>
					<td align='center'>
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<%=vo.getDurationDays()%>
						</font>
					</td>
					<!-- <td align='center'>
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<%=vo.getRemarks()%>
						</font>
					</td> -->
				</tr>
				<%
					}
				%>
			</table>
		</td>
	</tr>
</table>	--%>		
<%
	}
%>