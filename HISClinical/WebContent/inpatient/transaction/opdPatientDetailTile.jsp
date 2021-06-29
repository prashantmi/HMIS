<%--
##		Modify Date				: 18-02-2015
##		Reason	(CR/PRS)		: Update "isDead" checking condition, because it null pointer exception was comming
##		Modify By				: Akash Singh
--%>
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<%@page import="hisglobal.vo.PatientDetailVO"%>
<%@page import="registration.RegistrationConfig"%>
<%@page import="inpatient.InpatientConfig"%>
<%@page import="opd.OpdConfig"%>

<%@page import="hisglobal.utility.dynamicdesk.DynamicDeskConfig;"%>
<his:css src="/hisglobal/css/Color.css"/>
<%-- <his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/> --%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<his:statusInProcessWithJsp>

	<his:SubTitleTag>
	<his:name>
		<bean:message key="patient"/>
		<bean:message key="detail"/>
	 </his:name>
<%-- 	 <b>
		<bean:message key="registrationDate" />
		<bean:write name="InpatientDetailFB" property="registerDate" />
	</b> --%>
	</his:SubTitleTag>
	
	<his:ContentTag>
		<%
		PatientDetailVO patVO ;
		patVO = (PatientDetailVO)session.getAttribute(DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO);
		if(patVO == null || patVO.equals(""))
		{ patVO = (PatientDetailVO)session.getAttribute(InpatientConfig.INPATIENT_ADMISSION_VO);}
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
		<%  //<html:hidden type="hidden" name="InpatientDetailFB" property="patCatcode" value="<%=patVO.getPatCatcode()" /> %>
		<table width="100%">
			<tr>
				<td class="tdfonthead" width="25%">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:message key="name" />
						</font>
					</div>
				</td>
				<td width="25%" class="tdfont">
					<b>
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:write	name="InpatientDetailFB" property="patFirstName" />
							<bean:write	name="InpatientDetailFB" property="patMiddleName" />
							<bean:write	name="InpatientDetailFB" property="patLastName" />
						</font>
					</b>
				</td>
				<td width="25%" class="tdfonthead">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:message key="crNo" />
						</font>
					</div>
				</td>
				<td class="tdfont">
					<div align="left">
						<b>
							<font size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:write name="InpatientDetailFB" property="patCrNo" />
							</font>
						</b>
					</div>
				</td>
			
			</tr>
			<tr>
				<td width="25%" class="tdfonthead">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:message key="age" />
							<bean:message key="slash" />
							<bean:message key="gender" />
						</font>
					</div>
				</td>
				<td width="25%" nowrap class="tdfont">
					<b>
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:write	name="InpatientDetailFB" property="patAge" /> 
							<logic:notEqual	name="InpatientDetailFB" property="patGender" value="">/</logic:notEqual>
							<bean:write name="InpatientDetailFB" property="patGender" />
						</font>
					</b>
				</td>
				<td width="25%" class="tdfonthead">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:message key="primCat" />
						</font>
					</div>
				</td>
				<td width="25%" class="tdfont" nowrap>
					<b>
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:write	name="InpatientDetailFB" property="patPrimaryCat" />
						</font>
					</b>
				</td>
			</tr>
			<tr>
				<td class="tdfonthead" width="25%" nowrap>
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:message key="fatherName" />
						</font>
					</div>
				</td>			
				<td width="25%" class="tdfont">
					<b>
						<font color="#000000" size="2"face="Verdana, Arial, Helvetica, sans-serif">
							<bean:write name="InpatientDetailFB" property="patGuardianName" />
						</font>
					</b>
				</td>
				<td class="tdfonthead" width="25%" nowrap>
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:message key="husbandName" />
						</font>
					</div>
				</td>			
				<td width="25%" class="tdfont">
					<b>
						<font color="#000000" size="2"face="Verdana, Arial, Helvetica, sans-serif">
							<bean:write name="InpatientDetailFB" property="patSpouceName" />
						</font>
					</b>
				</td>
			</tr>
			
			<% // Changes By Pragya dated 2015.01.16 Now Dead Status will be checked from IsDead flag not Patient Status
			PatientDetailVO patDtlVO=(PatientDetailVO)session.getAttribute(InpatientConfig.INPATIENT_ADMISSION_VO);  
				if((patDtlVO.getMlcNo() != null && !patDtlVO.getMlcNo().equals("")) || ((patDtlVO.getIsDead()!=null) && (patDtlVO.getIsDead().equals(RegistrationConfig.PATIENT_STATUS_CODE_DEAD))))
				{
			%>
			<tr>
				<logic:notEqual name="InpatientDetailFB" property="mlcNo" value="">
					<td class="tdfonthead" width="25%" nowrap>
						<div align="right">
							<font color="#FF0000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								 <bean:message	key="mlcNo" />
						     </font>
				    	 </div>
					</td>
					<td width="25%" class="tdfont">
						<b>
							<font color="#FF0000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:write	name="InpatientDetailFB" property="mlcNo" />
							</font>
						</b>
					</td>
				</logic:notEqual>
				<logic:equal name="InpatientDetailFB" property="mlcNo" value="">
					<td class="tdfonthead" width="25%" nowrap></td>
					<td width="25%" class="tdfont"></td>
				</logic:equal>
				
				<logic:equal name="InpatientDetailFB" property="isDead" value="<%=RegistrationConfig.PATIENT_STATUS_CODE_DEAD %>">
					<td class="tdfonthead" width="25%" nowrap></td>
					<td width="25%" class="tdfont">
						<div align="left">
							<b>
								<font color="#FF0000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<bean:message key="patIsDead" />
							    </font>
							</b>
						</div>
					</td>
				</logic:equal>
				<logic:notEqual name="InpatientDetailFB" property="isDead" value="<%=RegistrationConfig.PATIENT_STATUS_CODE_DEAD %>">
					<td class="tdfonthead" width="25%" nowrap></td>
					<td width="25%" class="tdfont"></td>
				</logic:notEqual>
				
			</tr>
			<%} %>
			<% 
				if((patDtlVO.getPatIsPregnant() != null && !patDtlVO.getPatIsPregnant().equals("") && patDtlVO.getPatIsPregnant().equals(OpdConfig.YES))
						&& (patDtlVO.getPatGestationWeek() != null && !patDtlVO.getPatGestationWeek().equals("") ))
				{
			%>
			<tr>
				<td class="tdfonthead" width="25%" nowrap>
					<div align="right">
						<font color="#FF0000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							 <bean:message	key="gestationweek" />
					     </font>
			    	 </div>
				</td>
				<td width="25%" class="tdfont">
					<b>
						<font color="#FF0000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:write	name="InpatientDetailFB" property="patGestationWeek" />
						</font>
					</b>
				</td>
				<td class="tdfonthead" width="25%" nowrap></td>
				<td width="25%" class="tdfont"></td>
			</tr>
			<%} %>
		</table>
	</his:ContentTag>

</his:statusInProcessWithJsp>
