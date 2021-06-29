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
<%@page import="hisglobal.utility.dynamicdesk.DynamicDeskConfig"%>
<%@page import="opd.transaction.controller.util.GenericPatientProfileUTIL"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@page import="hisglobal.vo.EpisodeDiagnosisVO"%>
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
		Map mpDiag = (Map)proforma.mapProfileOptions.get(deskMenuId);
		List lstDiag = (List) mpDiag.get("lstEpisodeDiagnosisVO");
%>

<%@page import="java.util.Iterator"%>

<table width="100%" border="1" RULES=NONE FRAME=BOX cellpadding="0" cellspacing="0">
		<tr>
			<td width="100%">
				<div align="left">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<b><bean:message key="patdiagnosisDetail"/>:</b>
					</font>
				</div>
			</td>
		</tr>
				<%
				Iterator lstDiagItr = lstDiag.iterator();
					while(lstDiagItr.hasNext())
					{
						EpisodeDiagnosisVO vo = (EpisodeDiagnosisVO)lstDiagItr.next();
				%>
		<tr>
			<td width="100%">
				<div align="left">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								- &nbsp;<%-- <%=vo.getDiagnosticCode()%>[<%=vo.getDignosisName()%>]; (<%=vo.getDiagnosticTypeName()%>);	 --%>
								<%-- [<%=vo.getDignosisName()%>]; (<%=vo.getDiagnosticTypeName()%>);	 --%>
								<%=vo.getDignosisName()%> (<%=vo.getDiagnosticTypeName()%>)			
					</font>
				</div>
			</td>
		</tr>
			<%
				}
			%>
</table>

<%-- <table width="100%" border="1" cellpadding="0" cellspacing="0">
	<tr>
	  <td width="100%">
			<table width='100%'>
				<tr>
					<td width="100%" align="left">
						<b>
							<font color="#000000" size="3" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="diagnosisDetail"/>
							</font>
						</b>
					</td>
				</tr>
			</table>
			
			<table width='100%'>
				<tr>
				<logic:notEqual name="GenericPatientProfileFB" property="deskType" value="<%=DynamicDeskConfig.DESK_TYPE_IPD_DOCTOR_DESK %>">
					<td width='15%' align='center'>
						<b>
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="visitDate"/>
							</font>
						</b>
					</td>
				</logic:notEqual>	
					<td width='15%' align='center'>
						<b>
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="diagnosisCode"/>
							</font>
						</b>
					</td>
					<td width='35%' align='center'>
						<b>
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="diagnosisName"/>
							</font>
						</b>
					</td>
					<td width='20%' align='center'>
						<b>
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="dignosisTypeType"/>
							</font>
						</b>
					</td>
					<!-- <td width='20%' align='center'>
						<b>
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="remarks"/>
							</font>
						</b>
					</td> -->
				</tr>
				<%
					Iterator lstDiagItr = lstDiag.iterator();
					while(lstDiagItr.hasNext())
					{
						EpisodeDiagnosisVO vo = (EpisodeDiagnosisVO)lstDiagItr.next();
				%>
				<tr>
				<logic:notEqual name="GenericPatientProfileFB" property="deskType" value="<%=DynamicDeskConfig.DESK_TYPE_IPD_DOCTOR_DESK %>">
					<td align='center'>
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<%=vo.getEpisodeDate()%>
						</font>
					</td>
				</logic:notEqual>	
					<td align='center'>
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<%=vo.getDiagnosticCode()%>
						</font>
					</td>
					<td align='center'>
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<%=vo.getDignosisName()%>
						</font>
					</td>
					<td align='center'>
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<%=vo.getDiagnosticTypeName()%>
						</font>
					</td>
					<!-- <td width='20%' align='center'>
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
</table> --%>			
<%
	}
%>