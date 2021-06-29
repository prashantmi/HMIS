<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<%@ taglib uri="/WEB-INF/HISInvestigationTools.tld" prefix="inv"%>

<%@page import="opd.OpdConfig"%>
<%@page import="hisglobal.utility.generictemplate.GenericTemplateConfig"%>
<%@page import="opd.transaction.controller.util.GenericPatientProfileUTIL"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@page import="hisglobal.vo.ProfileOTDetailVO"%>
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
		Map mpOperation = (Map)proforma.mapProfileOptions.get(deskMenuId);
		List lstOT = (List) mpOperation.get("lstOperationDetailVO");
%>
<%@page import="java.util.Iterator"%>


<bean:define id="patCrNo" name="GenericPatientProfileFB" property="patCrNo" type="java.lang.String" >
</bean:define>
<table width="100%" border="1" cellpadding="0" cellspacing="0">
	<tr>
	  <td width="100%">
			<table width='100%'>
				<tr>
					<td width="100%" align="left">
						<b>
							<font color="#000000" size="3" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="operationDetail"/>
							</font>
						</b>
					</td>
				</tr>
			</table>
							
				
				<tr>
					<td colspan="2">
						<%
								List mapAllList=(List)session.getAttribute(OpdConfig.OPERATION_TEMPLATE_MAP_ALL_LIST);
								List templateIdAllList=(List) request.getSession().getAttribute(OpdConfig.OPERATION_TEMPLATE_LIST_ALL);
								int j=0;
									Iterator mapAllListItr=mapAllList.iterator();
									Iterator templateIdAllListItr= templateIdAllList.iterator();
									while((templateIdAllListItr.hasNext()) && (mapAllListItr.hasNext()))
									{
										
									List templateId = (List)templateIdAllListItr.next();
									Map mapAll=(Map)mapAllListItr.next();
									session.setAttribute(GenericTemplateConfig.GENERIC_TEMPLATE_PARAMETER_VALUES_MAP_TEMPLATE_MAP, mapAll);
									%>
									
									<%if(j<lstOT.size()){
										ProfileOTDetailVO pv=(ProfileOTDetailVO) lstOT.get(j);%>
									<table width='100%'>
										<tr>
											<td width='5%' align='center'>
												<b>
													<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
														<bean:message key="operationDate"/>
													</font>
												</b>
											</td>
											<td width='10%' align='center'>
												<b>
													<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
														<bean:message key="operationName"/>
													</font>
												</b>
											</td>
											
											
											
										</tr>
										<tr>
				
											<td width='5%' align='center'>
												<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
													
													<%=pv.getOpdate() %>
													
												</font>
											</td>
											<td width='10%' align='center'>
												<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
													<%=pv.getOperationName()%>
												</font>
											</td>
										</tr>
								</table>
									<%j++; 
									}%>
									<%
									if (templateId != null && templateId.size() > 0) {
									
									for (int i = 0; i < templateId.size(); i++) {
									
									%>
									
									<table width="100%" cellspacing="1" cellpadding="0">
									
										<tr>
									
										<td width="100%"><his:GenericTemplateTag
									
											templateId="<%=(String)templateId.get(i)%>" mode="<%=GenericTemplateConfig.GENERIC_TEMPLATE_MODE_REPORT%>"></his:GenericTemplateTag>
									
										</td>
									
										</tr>
									
									</table>
									
									 
									
									<%
							
							}
							
							}
									}		
							%>
							</td>
							
				</tr>
			</table>
		
<%
	}
%>