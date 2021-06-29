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
		List lstDiag = (List) mpDiag.get("lstPatientExtInvDtlVO");
%>

<%@page import="java.util.Iterator"%>

<%@page import="hisglobal.vo.EpisodeExtInvDtlVO"%>
<%--  <table width="100%" border="1" RULES=NONE FRAME=BOX cellpadding="0" cellspacing="0">
		<tr>
			<td width="100%">
				<div align="left">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<b><bean:message key="extInvDetail"/>:</b>
					</font>
				</div>
			</td>
		</tr>
				<%
				Iterator lstDiagItr = lstDiag.iterator();
					while(lstDiagItr.hasNext())
					{
						EpisodeExtInvDtlVO vo = (EpisodeExtInvDtlVO)lstDiagItr.next();
				%>
		<tr>
			<td width="100%">
				<div align="left">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								- &nbsp;<%=vo.getParaName()%>[<%=vo.getParaValue()%>]; (<%=vo.getRecordDate()%>);				
					</font>
				</div>
			</td>
		</tr>
			<%
				}
			%>
</table>	--%>	

<table width="100%" border="1" cellpadding="0" cellspacing="0">
	<tr>
	  <td width="100%">
			<table width='100%'>
				<tr>
					<td width="100%" align="left">
						<b>
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="extInvDetail"/>
							</font>
						</b>
					</td>
				</tr>
			</table>
			
			<table width='100%'>
				<tr>
					<td width='25%' align='center'>
						<b>
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="recordDate"/> &amp; <bean:message key="time"/>
							</font>
						</b>
					</td>
					<td width='30%' align='center'>
						<b>
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="paraName"/>
							</font>
						</b>
					</td>
					<td width='35%' align='center'>
						<b>
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="value"/>
							</font>
						</b>
					</td>
				</tr>
				<%
					Iterator lstDiagItr = lstDiag.iterator();
					while(lstDiagItr.hasNext())
					{
						EpisodeExtInvDtlVO vo = (EpisodeExtInvDtlVO)lstDiagItr.next();
				%>
				<tr>
					<td align='center'>
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<%=vo.getRecordDate()%>
						</font>
					</td>
					<td align='center'>
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<%=vo.getParaName()%>
						</font>
					</td>
					<td align='center'>
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<%=vo.getParaValue()%>
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