
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<%@page import="opd.OpdConfig"%>
<%@page import="opd.transaction.controller.util.GenericPatientProfileUTIL"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@page import="hisglobal.vo.PatDrugTreatmentDetailVO"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<%
	String deskMenuId = request.getParameter("DeskMenuID");
	GenericPatientProfileUTIL.ProfileProforma proforma = (GenericPatientProfileUTIL.ProfileProforma) session
	.getAttribute(OpdConfig.PATIENT_PROFILE_PROFORMA_OBJECT);
	if (proforma != null)
	{
		Map mpTreat = (Map)proforma.mapProfileOptions.get(deskMenuId);
		List lstTreat = (List) mpTreat.get("lstEpisodeTreatmentVO");
%>

<%@page import="java.util.Iterator"%>
<%-- <table width="100%" border="1" RULES=NONE FRAME=BOX cellpadding="0" cellspacing="0">
		<tr>
			<td width="100%">
				<div align="left">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<b><bean:message key="pattreatmentDetail"/>:</b>
					</font>
				</div>
			</td>
		</tr>
				<%
				Iterator lstTreatItr = lstTreat.iterator();
					while(lstTreatItr.hasNext())
					{
						PatDrugTreatmentDetailVO vo = (PatDrugTreatmentDetailVO)lstTreatItr.next();
				%>
		<tr>
			<td width="100%">
				<div align="left">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								- &nbsp;<%=vo.getDrugName()%>[<%=vo.getDoseName()%>]; <%=vo.getFrequencyName()%>;	 [<%=vo.getStartDate()%> - <%=vo.getEndDate()%>];			
					</font>
				</div>
			</td>
		</tr>
			<%
				}
			%>
</table>--%>
<table width="100%" border="1" cellpadding="0" cellspacing="0">
	<tr>
	  <td width="100%">
			<table width='100%'>
				<tr>
					<td width="100%" align="left">
						<b>
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="pattreatmentDetail"/>
							</font>
						</b>
					</td>
				</tr>
			</table>

			<table width='100%'>
				<tr>
				
				
				<td width='15%' align='center'>
						<b>
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="medicationType"/>
							</font>
						</b>
					</td>
				
					<td width='20%' align='center'>
						<b>
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="medicationName"/>
							</font>
						</b>
					</td>
					<td width='5%' align='center'>
						<b>
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="dosage"/>
							</font>
						</b>
					</td>
					<td width='20%' align='center'>
						<b>
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="frequency"/>
							</font>
						</b>
					</td>
					<td width='20%' align='center'>
						<b>
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="startDate"/>
							</font>
						</b>
					</td>
					<td width='20%' align='center'>
						<b>
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="endDate"/>
							</font>
						</b>
					</td>
				</tr>
				<%
					Iterator lstTreatItr = lstTreat.iterator();
					while(lstTreatItr.hasNext())
					{
						PatDrugTreatmentDetailVO vo = (PatDrugTreatmentDetailVO)lstTreatItr.next();
				%>
				<tr>
					<td align='center'>
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<%=vo.getMedicationType()%>
						</font>
					</td>
					<td align='center'>
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<%=vo.getMedicationName()%>
						</font>
					</td>
					<td align='center'>
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<%=vo.getDoseName()%>
						</font>
					</td>
					
					
					<td align='center'>
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<%=vo.getFrequencyName()%>
						</font>
					</td>
					
					<td align='center'>
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<%=vo.getStartDate()%>
						</font>
					</td>
					<td align='center'>
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<%=vo.getEndDate()%>
						</font>
					</td>
				</tr>
				<%
					}
				%>
			
			</table>

		</td>
	</tr>
</table>
<%
	}
%>