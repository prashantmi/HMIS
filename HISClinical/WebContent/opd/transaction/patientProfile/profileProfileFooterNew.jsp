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
		DisclaimerMstVO disclaimerMstVO = (DisclaimerMstVO) mpFooter.get("Disclaimer");
		String preparedBy = (String) mpFooter.get("DichargeAdvisedBy");
%>


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
					<%=(preparedBy==null?"":preparedBy)%>
				</font>
			</b>
		
		</td>
	</tr>
	
	<%} %>
	
	
	  <%-- <%} %>  --%>
</table>
<%-- <%
	}
%> --%>

<html:hidden name="GenericPatientProfileFB"	property="profileType" />
<html:hidden name="GenericPatientProfileFB"	property="deskType" />