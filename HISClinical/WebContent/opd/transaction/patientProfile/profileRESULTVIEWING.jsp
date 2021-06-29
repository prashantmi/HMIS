<%@page import="com.sun.xml.internal.ws.policy.privateutil.PolicyUtils.Collections"%>
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
	
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<%@ taglib uri="/WEB-INF/HISInvestigationTools.tld" prefix="inv"%>

<%@page import="opd.OpdConfig"%>
<%@page import="opd.transaction.controller.util.GenericPatientProfileUTIL"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@page import="hisglobal.vo.ProfileInvestigationVO"%>
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<%@page import="java.util.Iterator"%>

<bean:define id="patCrNo" name="GenericPatientProfileFB" property="patCrNo" type="java.lang.String" >
</bean:define>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style>
table {
    
     border-collapse: separate;
    empty-cells: hide;
}

</style>
</head>
<!-- <div class="div-table-row" style="height: 100px;"><div class="div-table-col" id="divOPDCardLogo" style="width: 20%" align="left"><img src="/HISRegistration/hisglobal/images/nims-tr-logo.gif"></div><div class="div-table-col" id="divOPDCardHeader" style="width: 60%" align="center"><br><font style="font-size: 16px; font-weight:bold;">GANDHI HOSPITAL TELANGANA</font><br><font style="font-size: 14px;">civil lines</font><br><font style="font-size: 14px;">station road</font><br> Nizamabad, Nizamabad -201301, Telangana, (INDIA)<br><font style="font-size: 14px;">Phone :123456789, Fax :22113366, Email :cdac@cdac.in</font><br></div></div> -->
 <%-- <%
	String deskMenuId = request.getParameter("DeskMenuID");
	GenericPatientProfileUTIL.ProfileProforma proforma = (GenericPatientProfileUTIL.ProfileProforma) session
			.getAttribute(OpdConfig.PATIENT_PROFILE_PROFORMA_OBJECT);
	if (proforma != null)
	{
		Map mpInv = (Map)proforma.mapProfileOptions.get(deskMenuId);
		List lstInv = (List) mpInv.get("lstEpisodeInvestigationVO");
%> --%>
<%@page import="java.util.Iterator"%>

<%--  <bean:define id="patCrN" name="GenericPatientProfileFB" property="patCrNo" type="java.lang.String" >
</bean:define> 
 --%>
<table width="100%" border="1" cellpadding="0" cellspacing="0">
	<tr>
	  <td width="100%">
			<table width='100%'>
				<tr>
					<td width="100%" align="left">
						<b>
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="investigationDetail"/>
							</font>
						</b>
					</td>
				</tr>
			</table>
			
			<table width='100%' border="1" cellpadding="0" cellspacing="0">
				<tr>
					<td width='20%' align='center' >
						<b>
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="visitDate"/>
							</font>
						</b>
					</td>
					<!-- <td width='10%' align='center'> -->
					<td width='40%' align='center'>
						<b>
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="test"/>
							</font>
						</b>
					</td>
					<td width='40%' align='center'>
						<b>
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="result"/>
							</font>
					</b></td>
					
				</tr>
				<%
				try
				{
					ProfileInvestigationVO[] arrVo=(ProfileInvestigationVO[])session.getAttribute(OpdConfig.PATIENT_INVESTIGATION_PROFILE);
					int i=0;
					//for (int i=0;i<arrVo.length;i++)
					while(i<arrVo.length)
					{
						ProfileInvestigationVO vo = arrVo[i];	
						String visitDate=arrVo[i].getVisitDate();
				%>
				<%
						do
						{
							String testName = arrVo[i].getTestName();
				%>
						<tr>
							<td width='20%' align='left'>
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<%=visitDate%>	
								</font>
							</td>
							<td width='40%' align='left'>
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<%=testName%>	
								</font>
							</td>
							<td width='40%' align='left'>
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	
								</font>
							</td>
						</tr>
				<%
							visitDate = "";
							do
							{
				%>
						<tr>
							<td width='20%' align='left'>
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"></font>
							</td>
							<td width='40%' align='left'>
								<i><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<%=arrVo[i].getParameterName()%>
								</font></i>
							</td>
							<td width='40%' align='left'>
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<%=arrVo[i].getTestValue()%>
								</font>
							</td>
						</tr>
				<%
								i++;
							} while(arrVo[i-1].getVisitDate().equals(arrVo[i].getVisitDate()) && arrVo[i-1].getTestName().equals(arrVo[i].getTestName()));
						} while(arrVo[i-1].getVisitDate().equals(arrVo[i].getVisitDate()));
					}
					
				}
				catch(Exception e){
					System.out.print("no investigation report found");
				}%>
			</table> 
		</td>
	</tr>
</table>			
