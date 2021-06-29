<%--##		Modification Date		: 	14-01-2015
	##		Reason	(CR/PRS)		: 	CR
	##		Modified By				:	AKASH SINGH
--%>
<%@page import="hisglobal.vo.ProfileChartViewDtlVO"%>
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

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
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<%
	String deskMenuId = request.getParameter("DeskMenuID");
	GenericPatientProfileUTIL.ProfileProforma proforma = (GenericPatientProfileUTIL.ProfileProforma) session.getAttribute(OpdConfig.PATIENT_PROFILE_PROFORMA_OBJECT);
	if (proforma != null)
	{
		Map mpCharts = (Map)proforma.mapProfileOptions.get(deskMenuId);
		List lstCharts = (List) mpCharts.get("lstEpisodeChartViewDtlVO");
%>

<%@page import="java.util.Iterator"%>

<table width="100%" border="1" RULES=NONE FRAME=BOX cellpadding="0" cellspacing="0">
		<%
		Iterator lstItr = lstCharts.iterator();
			while(lstItr.hasNext())
			{
				ProfileChartViewDtlVO vo = (ProfileChartViewDtlVO)lstItr.next();
		%>
		<tr>
			<td width="100%">
				<div align="center">
					<%=vo.getChartHtml()%>			
				</div>
			</td>
		</tr>
			<%
				}
			%>
</table>
			
<%
	}
%>