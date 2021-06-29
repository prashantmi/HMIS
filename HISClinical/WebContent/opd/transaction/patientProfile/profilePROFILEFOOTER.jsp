<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<%@page import="opd.OpdConfig"%>
<%@page import="opd.transaction.controller.util.GenericPatientProfileUTIL"%>
<%@page import="java.util.Map"%>
<%@page import="hisglobal.vo.DisclaimerMstVO"%>
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
		Map mpFooter = (Map)proforma.mapProfileOptions.get(deskMenuId);
		String remarks = (String) mpFooter.get("Remarks");
		String reviewDate = (String) mpFooter.get("ReviewOn");
		String profileType = (String) mpFooter.get("ProfileType");
		String prescribedBy = (String) mpFooter.get("DichargeAdvisedBy");
		DisclaimerMstVO disclaimerMstVO = (DisclaimerMstVO) mpFooter.get("Disclaimer");
%>

<table width='100%'>
	<tr>
		<td width="100%" nowrap="nowrap">
			<table width='100%' border="1" cellpadding="0" cellspacing="0" >
				<tr>
					<td width="100%" nowrap="nowrap" valign="top" >
						<b>
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif" >
									<bean:message key="summary"/>
									<bean:message key="colon"/>
						</font>
						</b>
					</td>
				</tr>
				<tr style="height:140">
				<td width="100%" valign="top" >
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<%-- <html:textarea property="remark" value="<%=remarks %>" rows="10" cols="130"></html:textarea>
						--%>
						<%=remarks%>
					</font>
				</td>
			</tr>
			</table>	
		</td>
	</tr>
</table>
<table width='100%'>
	 <%-- <%if(profileType.equals(OpdConfig.PATIENT_PROFILE_TYPE_DISCHARGE_SUMMARY)){ %>  --%>
	
	<tr>
	   <td width="25%">
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>Prescribed By:</b>
				</font>
					
				</td>
		<td width="75%" align="left">
		
			<b>
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<%=(prescribedBy==null?"":prescribedBy)%>
				</font>
			</b>
		
		</td>
	</tr>
	</table>
<table width='100%'>
	 <%if(profileType.equals(OpdConfig.PATIENT_PROFILE_TYPE_DISCHARGE_SUMMARY)){ %> 
	
	<tr>
	   <td width="25%">
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b><bean:message key="reviewOn"/></b>
				</font>
					
				</td>
		<td width="75%" align="left">
		
			<b>
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<%=(reviewDate==null?"":reviewDate)%>
				</font>
			</b>
		
		</td>
	</tr>
	<tr>
	   <td width="25%">
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b><bean:message key="signature"/></b>
				</font>
	   </td>
	</tr>	
	<tr>
	   <td width="25%">
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b><bean:message key="seniorResident"/></b>
				</font>
	   </td>
	</tr>	
	<tr>
	   <td width="25%">
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b><bean:message key="date1"/></b>
				</font>
	   </td>
	</tr>	
	<%if(disclaimerMstVO.getDisclaimerDesc1()!=null){%>
	<tr>
	   <td width="100%" colspan="2" align="center">
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>	
							<%=disclaimerMstVO.getDisclaimerDesc1()%>
							</b>
				</font>
	   </td>
	</tr>
	<%} %>
	<%if(disclaimerMstVO.getDisclaimerDesc2()!=null){%>
		<tr>
		   <td width="100%" colspan="2" align="center">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>	
								<%=disclaimerMstVO.getDisclaimerDesc2()%>
								</b>
					</font>
		   </td>
		</tr>
	<%} %>
	<%if(disclaimerMstVO.getDisclaimerDesc3()!=null){%>
		<tr>
		   <td width="100%" colspan="2" align="center">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>	
								<%=disclaimerMstVO.getDisclaimerDesc3()%>
								</b>
					</font>
		   </td>
		</tr>
	<%} %>
	
	
	  <%} %> 
</table>
<%
	}
%>

<html:hidden name="GenericPatientProfileFB"	property="profileType" />
<html:hidden name="GenericPatientProfileFB"	property="deskType" />