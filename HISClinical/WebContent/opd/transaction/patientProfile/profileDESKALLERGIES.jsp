<%--##		Modification Date		: 	14-01-2015
	##		Reason	(CR/PRS)		: 	CR
	##		Modified By				:	AKASH SINGH
--%>
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
<%@page import="hisglobal.vo.PatAllergyDtlVO"%>
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
		Map mpAllerg = (Map)proforma.mapProfileOptions.get(deskMenuId);
		List lstAllerg = (List) mpAllerg.get("lstEpisodeAllergiesVO");
%>
<%@page import="java.util.Iterator"%>

<table width="100%" border="1" cellpadding="0" cellspacing="0">
	<tr>
		<td width="100%">
			<div align="left">
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<b><bean:message key="allergiesDetail"/>:</b>
				<%
				Iterator lstAllergItr = lstAllerg.iterator();
					while(lstAllergItr.hasNext())
					{
						PatAllergyDtlVO vo = (PatAllergyDtlVO)lstAllergItr.next();
				%>
							&nbsp;<%=vo.getAllergyName()%><%=vo.getAllergyTypeName()%> (<%=vo.getDurationDays()%>);
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
								<bean:message key="allergiesDetail"/>
							</font>
						</b>
					</td>
				</tr>
			</table>
			
			<table width='100%'>
				<tr>
					<td width='40%' align='center'>
						<b>
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="allergyName"/>
							</font>
						</b>
					</td>
					<td width='35%' align='center'>
						<b>
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="allergyType"/>
							</font>
						</b>
					</td>
					<td width='25%' align='center'>
						<b>
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="duration"/>
							</font>
						</b>
					</td>
					
				</tr>
				<%
					Iterator lstAllergItr = lstAllerg.iterator();
					while(lstAllergItr.hasNext())
					{
						PatAllergyDtlVO vo = (PatAllergyDtlVO)lstAllergItr.next();
				%>
				<tr>
					<td align='center'>
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<%=vo.getAllergyName()%>
						</font>
					</td>
					<td align='center'>
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<%=vo.getAllergyTypeName()%>
						</font>
					</td>
					<td align='center'>
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<%=vo.getDurationDays()%>
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
--%>			
<%
	}
%>