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
<%@page import="hisglobal.vo.DoctorRoundDtlVO"%>
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
		Map mpProgress = (Map)proforma.mapProfileOptions.get(deskMenuId);
		List lstProgress = (List) mpProgress.get("lstProgressNotesVO");
%>
<%@page import="java.util.Iterator"%>

<table width="100%" border="1" cellpadding="0" cellspacing="0">
	<tr>
	  <td width="100%">
			<table width='100%'>
				<tr>
					<td width="100%" align="left">
						<b>
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="progressNotes"/>
							</font>
						</b>
					</td>
				</tr>
			</table>
			
			<table width='100%'>
				<tr>
					<td width='10%' align='center'>
						<b>
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="entryDate"/>
							</font>
						</b>
					</td>
					<td width='10%' align='center'>
						<b>
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="progNote"/>
							</font>
						</b>
					</td>
					
					
					
				</tr>
				<%
					Iterator lstProgressItr = lstProgress.iterator();
					while(lstProgressItr.hasNext())
					{
						DoctorRoundDtlVO vo = (DoctorRoundDtlVO)lstProgressItr.next();
				%>
				<tr>
					<td width='10%' align='center'>
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<%=vo.getRoundTime()%>
						</font>
					</td>
					<td width='10%' align='center'>
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<%=vo.getProgressNote()%>
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