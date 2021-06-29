<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<%@page import="opd.OpdConfig"%>
<%@page import="opd.transaction.controller.util.GenericPatientProfileUTIL"%>
<%@page import="java.util.Map"%>
<%@page import="hisglobal.vo.EpisodeVO"%>
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
		Map mpNextVisit = (Map)proforma.mapProfileOptions.get(deskMenuId);
		EpisodeVO episodeVO = (EpisodeVO) mpNextVisit.get("EpisodeVO");
%>

<table width='100%'>
	<tr>
		<td width="100%" align="left">
			<b>
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<bean:message key="nextvisitdetail"/>
				</font>
			</b>
		</td>
	</tr>
</table>

<table width='100%'>
	<%
		if (!episodeVO.getEpisodeNextVisitDate().equals(""))
		{
	%>
	<tr>
		<td width='20%' align='left'>
			<b>
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<bean:message key="nextVisitDate"/>
				</font>
			</b>
		</td>
		<td width='80%' align='left'>
			<b>
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<%=episodeVO.getEpisodeNextVisitDate()%>
				</font>
			</b>
		</td>
	</tr>
	<%
		}
		if (!episodeVO.getComplainDetail().equals(""))
		{
	%>
	<tr>
		<td width='30%' align='left'>
			<b>
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<bean:message key="visit"/> <bean:message key="summary"/>
				</font>
			</b>
		</td>
		<td width='80%' align='left'>
			<b>
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<%=episodeVO.getComplainDetail()%>
				</font>
			</b>
		</td>
	</tr>
	<%
		}
	%>
</table>
<%
	}
%>