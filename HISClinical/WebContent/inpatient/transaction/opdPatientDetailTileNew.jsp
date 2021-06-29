<%--##		Modify Date				: 	20-01-2015
	##		Reason	(CR/PRS)		: 	added Summary Link on page
	##		Modify By				:	Akash Singh
--%>
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<%@page import="hisglobal.vo.PatientDetailVO"%>
<%@page import="inpatient.InpatientConfig"%>
<%@page import="hisglobal.utility.dynamicdesk.DynamicDeskConfig;"%>
<his:css src="/hisglobal/css/Color.css"/>
<his:javascript src="/opd/js/opd_desk_new.js"/>
<%-- <his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/> --%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script>
function patientOPPTab()
{
	//alert("summary tab opening....");
	var patCrNo=document.getElementsByName("patCrNo")[0].value;
	var episodeCode=document.getElementsByName("episodeCode")[0].value;
	var episodeVisitNo=document.getElementsByName("episodeVisitNo")[0].value;
	var departmentUnitCode=document.getElementsByName("departmentUnitCode")[0].value;
	var roomCode=document.getElementsByName("roomCode")[0].value;

	parent.addTab('Single Page Prescription','UNIPAGEPRESCRIPTION','14','1',false,"&patCrNo="+patCrNo+"&episodeCode="+episodeCode+"&episodeVisitNo="+episodeVisitNo+"&departmentUnitCode="+departmentUnitCode+"&roomCode="+roomCode,'1');

}

</script>
</head>
<his:statusInProcessWithJsp>
	

		<%
	//	PatientDetailVO patVO = (PatientDetailVO)session.getAttribute(InpatientConfig.INPATIENT_ADMISSION_VO);
		PatientDetailVO patVO = (PatientDetailVO)session.getAttribute(DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO);
		patVO.setPatientName();
		patVO.setPatientCompleteAddress();
		StringBuffer strHiddenPatDtl = new StringBuffer("");
		strHiddenPatDtl.append((patVO.getPatName()!=null && !patVO.getPatName().trim().equals(""))?patVO.getPatName():"");	
		strHiddenPatDtl.append("^");
		strHiddenPatDtl.append((patVO.getPatGuardianName()!=null && !patVO.getPatGuardianName().trim().equals(""))?patVO.getPatGuardianName():"");
		strHiddenPatDtl.append("/");
		strHiddenPatDtl.append((patVO.getPatSpouceName()!=null && !patVO.getPatSpouceName().trim().equals(""))?patVO.getPatSpouceName():"");
		strHiddenPatDtl.append("^");
		strHiddenPatDtl.append((patVO.getPatPrimaryCat()!=null && !patVO.getPatPrimaryCat().trim().equals(""))?patVO.getPatPrimaryCat():"");
		strHiddenPatDtl.append("^");
		strHiddenPatDtl.append((patVO.getPatAge()!=null && !patVO.getPatAge().trim().equals(""))?patVO.getPatAge():"");
		strHiddenPatDtl.append("/");
		strHiddenPatDtl.append((patVO.getPatGender()!=null && !patVO.getPatGender().trim().equals(""))?patVO.getPatGender():"");
		strHiddenPatDtl.append("^");
		strHiddenPatDtl.append((patVO.getPatCompleteAddress()!=null && !patVO.getPatCompleteAddress().trim().equals(""))?patVO.getPatCompleteAddress():"");
		
		StringBuffer strMotherDtl = new StringBuffer("");
		strMotherDtl.append((patVO.getPatMotherName()!=null && !patVO.getPatMotherName().trim().equals(""))?patVO.getPatMotherName():"");
		strMotherDtl.append("^");
		strMotherDtl.append((patVO.getPatMotherCrNo()!=null && !patVO.getPatMotherCrNo().trim().equals(""))?patVO.getPatMotherCrNo():"");
		if(patVO.getMlcNo()!= null && !patVO.getMlcNo().trim().equals("")) patVO.setIsMLC("1");	else patVO.setIsMLC("0");
		%>
		<input type="hidden" name="strHiddenPatDtl" value="<%=strHiddenPatDtl.toString()%>" />
		<input type="hidden" name="strPatName" value="<%=patVO.getPatName()%>" />
		<input type="hidden" name="strCategoryCode" value="<%=patVO.getPatPrimaryCatCode()%>" />
		<input type="hidden" name="strIsMLC" value="<%=patVO.getIsMLC()%>" />
		<input type="hidden" name="strMLCNo" value="<%=patVO.getMlcNo()%>" />
		<input type="hidden" name="strIsNewBorn" value="<%=patVO.getIsNewBorn()%>" />
		<input type="hidden" name="strMotherDtl" value="<%=strMotherDtl%>" />
		
		
		<table width="100%">
			<tr>
				<td  width="20%" valign="top" class="deskTile">
					<div align="left">
						<font color="#126295">
							<bean:message key="name" />:
							<font color="#000000" > <bean:write	name="InpatientDetailFB" property="patFirstName" />
							<bean:write	name="InpatientDetailFB" property="patMiddleName" />
							<bean:write	name="InpatientDetailFB" property="patLastName" /></font>
							<br>
							<bean:message key="crNo" />:
								<font color="#000000" style="font-size: 12px;"><bean:write name="InpatientDetailFB" property="patCrNo" /></font>
							<br>							
							<bean:message key="age" />
							<bean:message key="slash" />
							<bean:message key="gender" />:
							<font color="#000000"><bean:write	name="InpatientDetailFB" property="patAge" />
							<logic:notEqual	name="InpatientDetailFB" property="patGender" value="">/</logic:notEqual>
							<bean:write name="InpatientDetailFB" property="patGender" /></font>
						</font>
					</div>
				</td>
				<td width="20%" valign="top" class="deskTile">
					<div align="left">
						<font color="#126295">
						
						<logic:present name="InpatientDetailFB" property="patCat">
							<bean:message key="patCat" />:
							<font color="#000000">
								<bean:write name="InpatientDetailFB" property="patCat" />
							</font>
						</logic:present>
						
						<logic:present name="InpatientDetailFB" property="patFatherName">
							<br>
							<bean:message key="fatherName" />:
							<font color="#000000">
								<bean:write name="InpatientDetailFB" property="patFatherName" />
							</font>							
						</logic:present>
						
						<logic:present name="InpatientDetailFB" property="patMotherName">
							<br>
							<bean:message key="motherName" />:
							<font color="#000000">
								<bean:write name="InpatientDetailFB" property="patMotherName" />
							</font>
						</logic:present>
							
						</font>
					</div>
				</td>
				<td width="20%" valign="top" class="deskTile">
					<div align="left">
						<font color="#126295">
						
							<logic:present name="InpatientDetailFB" property="departmentUnitName">
							<bean:message key="deptUnitName" />:
							
							<font color="#000000">
							<bean:write name="InpatientDetailFB" property="departmentName" />
							(<bean:write name="InpatientDetailFB" property="departmentUnitName" />)
							</font>
							</logic:present>
							<!-- Added By Nilesh on 1 Jan 2018 -->
							<logic:present name="InpatientDetailFB" property="patFatherName">
							<br>
							<bean:message key="deptName" />:
							<font color="#000000">
								<bean:write name="InpatientDetailFB" property="departmentName" />
							</font>							
						</logic:present>
						
							<logic:present name="InpatientDetailFB" property="room">
							<br>
							<bean:message key="room" />:
							<font color="#000000">
								<bean:write name="InpatientDetailFB" property="room" />
							</font>
							</logic:present>
							
							<br>
						</font>
					</div>
				</td>
				<td width="20%" valign="top" class="deskTile">
					<div align="left">
						<font color="#126295">
							<bean:message key="district" />:
							<font color="#000000">
								<bean:write	name="InpatientDetailFB" property="patAddDistrict" />
							</font>
							<logic:equal name="InpatientDetailFB" value="1" property="isDead">
							<br>
							<font color="#FF0000">
								<bean:message key="patIsDead" />
							</font>
							</logic:equal>
							
							<logic:present name="InpatientDetailFB" property="patAdmittedDays">
							<br>
							<bean:message key="admFrmDays" />:
							<font color="#000000">
								<bean:write name="InpatientDetailFB" property="patAdmittedDays" />
							</font>
							</logic:present>
							
							<logic:present name="InpatientDetailFB" property="patGestationWeek">
							<br>
							<bean:message key="gestationweek" />:
							<font color="#000000">
								<bean:write name="InpatientDetailFB" property="patGestationWeek" />
							</font>
							</logic:present>
						</font>
					</div>
				</td>
				<td width="15%" valign="top">
					<div align="center">						
							<logic:equal name="InpatientDetailFB" property="patGender" value="Male">
							<img src="/HISClinical/hisglobal/images/Patient-Male-icon.png" />
							</logic:equal>							
							<logic:equal name="InpatientDetailFB" property="patGender" value="Female">
							<img src="/HISClinical/hisglobal/images/Patient-Female-icon.png" />
							</logic:equal>
					</div>
				</td>
				<td width="5%" valign="top">
					<div align="right">						
						<img src="/HISClinical/hisglobal/images/icon_refresh.gif" onClick="refreshDashBoard();" 
				style="cursor: pointer; cursor: hand"/><br>
				<% String deskType = (String)request.getSession().getAttribute(DynamicDeskConfig.DYNAMIC_DESK_TYPE);  //Added by Prachi to remove spp link for nursing desk on 26-06-2019
						if (deskType.equalsIgnoreCase("1"))
				{%>
				<a onclick="patientOPPTab();" href="#" style="font-size: xx-small; font: bold">Single Page Prescription</a>
				<%} %>
				
					</div>
				</td>
			</tr>
		</table>
		
		
		


</his:statusInProcessWithJsp>


