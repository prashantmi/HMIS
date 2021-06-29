<%--##		Modification Date		: 	20-01-2015
	##		Reason	(CR/PRS)		: 	CR
	##		Modified By				:	AKASH SINGH
--%><%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<%@page import="opd.OpdConfig"%>
<%@page import="opd.transaction.controller.util.GenericPatientProfileUTIL"%>
<%@page import="hisglobal.vo.PatientDetailVO"%>
<%@page import="hisglobal.utility.dynamicdesk.DynamicDeskConfig"%>
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<script>
function win_print()
{
window.print();	
}

</script>
<%
	GenericPatientProfileUTIL.ProfileProforma proforma = (GenericPatientProfileUTIL.ProfileProforma)session.getAttribute(OpdConfig.PATIENT_PROFILE_PROFORMA_OBJECT);
	if(proforma!=null)
	{
		String header= (String)proforma.mapProfileHeader.get("Header");
		PatientDetailVO patDtlVO = (PatientDetailVO)proforma.mapProfileHeader.get("PatientDetailVO");
		//pageContext.setAttribute("patDtlVO",patDtlVO);
		ControllerUTIL.getHospitalVO(request);
		HospitalMstVO hospitalMasterVO=(HospitalMstVO)session.getAttribute(Config.HOSPITAL_VO);
%>

<%@page import="hisglobal.vo.HospitalMstVO"%>
<%@page import="hisglobal.hisconfig.Config"%>
<%@page import="hisglobal.presentation.ControllerUTIL"%>
<table width='100%'>
	<tr>
		<td width="95%">
			<div align="center">
				<b>
					<font color="#000000" size="3" face="Verdana, Arial, Helvetica, sans-serif">
						<%=header%>
					</font>
				</b>
			</div>
		</td>
		
		<!-- <td width="5%">
			<div align="center">
				<b>
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<a onclick="win_print()">Print</a>	
					</font>
				</b>
			</div>
		</td> -->
	</tr>
</table>
<br>
<table width='100%' border="1" cellpadding="0" cellspacing="0"><tr><td width="100%">
<table width='100%' cellpadding="0" cellspacing="0">
	<tr>
		<td width="25%" style="empty-cells: show">
		<div align="right">
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<bean:message key="name" />&nbsp;
				</font>
			</div>
		</td>
		<td width="25%" >
			<div align="left">
				<b>
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						&nbsp;<%=patDtlVO.getPatName().toUpperCase()%>
					</font>
				</b>
			</div>
		</td>
		<td width="25%" style="empty-cells: show">
			<div align="right">
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<bean:message key="crNo" />&nbsp;
				</font>
			</div>
		</td>
		<td width="25%" nowrap style="empty-cells: show">
			<div align="left">
				<b>
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						&nbsp;<%=patDtlVO.getPatCrNo()%>
					</font>
				</b>
			</div>
		</td>
	</tr>
	<tr>
		<td width="25%" nowrap style="empty-cells: show">
			<div align="right">
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<bean:message key="age" /> / <bean:message key="gender" />&nbsp;
				</font>
			</div>
		</td>
		<td width="25%" nowrap style="empty-cells: show">
			<b><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				&nbsp;<%=patDtlVO.getPatAge()%> / <%=patDtlVO.getPatGender()%>
			</font></b>
		</td>
		<td width="25%" nowrap style="empty-cells: show">
			<div align="right">
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<bean:message key="primCat" />&nbsp;
				</font>
			</div>
		</td>
		<td width="25%" nowrap style="empty-cells: show">
			<b><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				&nbsp;<%=patDtlVO.getPatPrimaryCat()%>
			</font></b>
		</td>
	</tr>
	<tr>
		<td width="25%" nowrap style="empty-cells: show">
			<div align="right">
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<bean:message key="fatherName" />&nbsp;
				</font>
			</div>
		</td>
		<td width="25%" nowrap style="empty-cells: show">
			<b><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				&nbsp;<%=patDtlVO.getPatGuardianName()%>
			</font></b>
		</td>
		<td width="25%" nowrap style="empty-cells: show">
			<div align="right">
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<bean:message key="husbandName" />&nbsp;
				</font>
			</div>
		</td>
		<td width="25%" nowrap style="empty-cells: show">
			<b><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				&nbsp;<%=patDtlVO.getPatSpouceName()%>
			</font></b>
		</td>
	</tr>	
	<logic:equal name="GenericPatientProfileFB" property="deskType" value="<%=DynamicDeskConfig.DESK_TYPE_IPD_DOCTOR_DESK %>">
	<tr>
			<td width="25%" nowrap style="empty-cells: show">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:message key="dept/unit" />&nbsp;
						</font>
					</div>
				</td>			
				<td width="25%" nowrap style="empty-cells: show">
					<b>
						<font color="#000000" size="2"face="Verdana, Arial, Helvetica, sans-serif">
						&nbsp;<%=patDtlVO.getDepartmentName()%>/
						<%=patDtlVO.getDepartmentUnitName()%>
						</font>
					</b>
				</td>
				<td width="25%" nowrap style="empty-cells: show">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:message key="admNo" />&nbsp;
						</font>
					</div>
				</td>			
				<td width="25%" style="empty-cells: show">
					<b>
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						&nbsp;<%=patDtlVO.getPatAdmNo()%>
					</font>
					</b>
				</td>
				
	</tr>			
	<tr>
				<td width="25%" nowrap style="empty-cells: show">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:message key="ward" />/
							<bean:message key="roomNo" />&nbsp;
						</font>
					</div>
				</td>			
				<td width="25%" style="empty-cells: show">
					<b>
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						&nbsp;<%=patDtlVO.getWardName()%>/
						<%=patDtlVO.getIpdRoomName()%>
						</font>
					</b>
				</td>
				<td width="25%" nowrap style="empty-cells: show">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:message key="bedNo" />&nbsp;
						</font>
					</div>
				</td>			
				<td width="25%" style="empty-cells: show">
					<b>
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						&nbsp;<%=patDtlVO.getBedName()%>
						</font>
					</b>
				</td>
	</tr>
	<tr>
			<td width="25%" nowrap style="empty-cells: show">
			<div align="right">
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<bean:message key="admissionDate" />&nbsp;
				</font>
			</div>
			</td>
			<td width="25%" nowrap style="empty-cells: show">
				<b><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					&nbsp;<%=patDtlVO.getAdmDateTime()%>
				</font></b>
			</td>
			<td width="25%" style="empty-cells: show">
			</td>
			<td width="25%" style="empty-cells: show">
			</td>
	</tr>
	</logic:equal>
</table>
</td></tr></table>
<%
	}
%>

<html:hidden name="GenericPatientProfileFB" property="deskType" />
<html:hidden name="GenericPatientProfileFB" property="profileType" />

