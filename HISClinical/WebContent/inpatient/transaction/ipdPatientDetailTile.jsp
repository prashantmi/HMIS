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

<his:css src="/hisglobal/css/Color.css"/>

<his:javascript src="/hisglobal/transactionutil/js/master.js"/>

<script>


function onclickImage(imgObj)
{
	var divObj=document.getElementById("div"+imgObj.id.substr(3));
	if(divObj.style.display=="none")
	{
		divObj.style.display="block";
		imgObj.src = "/HIS/hisglobal/images/avai/arrow-up.png";
	}
	else if(divObj.style.display=="block")
	{
		divObj.style.display="none";
		imgObj.src = "/HIS/hisglobal/images/avai/arrow-down.png";
	}
}



</script>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<his:statusInProcessWithJsp>

	<his:SubTitleTag>
	<his:name>
		<bean:message key="patient"/>
		<bean:message key="detail"/>
	 </his:name>
	 <b>
	 	<bean:message key="admFrmDays" />
	 	<bean:write name="InpatientDetailFB" property="patAdmittedDays" />
	 	(
		<bean:message key="admDate" />
		<bean:write name="InpatientDetailFB" property="admDateTime" />
		)
	</b>
	<img id="imgPatientDtl" tabindex="1" style="cursor: pointer; vertical-align: middle;" src="/HIS/hisglobal/images/avai/arrow-up.png"; onclick="onclickImage(this)"/>
	</his:SubTitleTag>
	<his:ContentTag>
		<%
		PatientDetailVO patVO = (PatientDetailVO)session.getAttribute(InpatientConfig.INPATIENT_ADMISSION_VO);
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
		
		StringBuffer strDtUtWrRmBd = new StringBuffer("");
		strDtUtWrRmBd.append((patVO.getDepartmentName()!=null && !patVO.getDepartmentName().trim().equals(""))?patVO.getDepartmentName():"");
		strDtUtWrRmBd.append("^");
		strDtUtWrRmBd.append((patVO.getDepartmentUnitName()!=null && !patVO.getDepartmentUnitName().trim().equals(""))?patVO.getDepartmentUnitName():"");
		strDtUtWrRmBd.append("^");
		strDtUtWrRmBd.append((patVO.getWardName()!=null && !patVO.getWardName().trim().equals(""))?patVO.getWardName():"");
		strDtUtWrRmBd.append("^");
		strDtUtWrRmBd.append((patVO.getIpdRoomName()!=null && !patVO.getIpdRoomName().trim().equals(""))?patVO.getIpdRoomName():"");
		strDtUtWrRmBd.append("^");
		strDtUtWrRmBd.append((patVO.getBedName()!=null && !patVO.getBedName().trim().equals(""))?patVO.getBedName():"");
		
		StringBuffer strWrdBedCode = new StringBuffer("");
        strWrdBedCode.append((patVO.getWardCode()!=null && !patVO.getWardCode().trim().equals(""))?patVO.getWardCode():"");
        strWrdBedCode.append("^");
        strWrdBedCode.append((patVO.getBedCode()!=null && !patVO.getBedCode().trim().equals(""))?patVO.getBedCode():"");
        strWrdBedCode.append("^");
        strWrdBedCode.append((patVO.getIsBedSharable()!=null && !patVO.getIsBedSharable().trim().equals(""))?patVO.getIsBedSharable():"");

		StringBuffer strDeptUntRomCode = new StringBuffer("");
		strDeptUntRomCode.append((patVO.getDepartmentCode()!=null && !patVO.getDepartmentCode().trim().equals(""))?patVO.getDepartmentCode():"");
		strDeptUntRomCode.append("^");
		strDeptUntRomCode.append((patVO.getDepartmentUnitCode()!=null && !patVO.getDepartmentUnitCode().trim().equals(""))?patVO.getDepartmentUnitCode():"");
		strDeptUntRomCode.append("^");
		strDeptUntRomCode.append((patVO.getIpdRoomCode()!=null && !patVO.getIpdRoomCode().trim().equals(""))?patVO.getIpdRoomCode():"");
		if(patVO.getMlcNo()!= null && !patVO.getMlcNo().trim().equals("")) patVO.setIsMLC("1");	else patVO.setIsMLC("0");
		%>
		<input type="hidden" name="strHiddenPatDtl" value="<%=strHiddenPatDtl.toString()%>" />
		<input type="hidden" name="strPatName" value="<%=patVO.getPatName()%>" />
		<input type="hidden" name="strCategoryCode" value="<%=patVO.getPatPrimaryCatCode()%>" />
		<input type="hidden" name="strIsMLC" value="<%=patVO.getIsMLC()%>" />
		<input type="hidden" name="strMLCNo" value='<%=(patVO.getMlcNo()!=null && !patVO.getMlcNo().trim().equals(""))?patVO.getMlcNo():""%>' />
		<input type="hidden" name="strIsNewBorn" value="<%=patVO.getIsNewBorn()%>" />
		<input type="hidden" name="strMotherDtl" value="<%=strMotherDtl.toString()%>" />
		
		<input type="hidden" name="curDtUtWrRmBd" value="<%=strDtUtWrRmBd%>">
		<input type="hidden" name="curWrdBedCode" value="<%=strWrdBedCode%>">
		<input type="hidden" name="curDept_Unt_RomCode" value="<%=strDeptUntRomCode%>">
		<input type="hidden" name="curAdmAdvNo" value='<%=(patVO.getAdmAdvNo()!=null && !patVO.getAdmAdvNo().trim().equals(""))?patVO.getAdmAdvNo():""%>'>
		<input type="hidden" name="curAdmNo" value='<%=(patVO.getPatAdmNo()!=null && !patVO.getPatAdmNo().trim().equals(""))?patVO.getPatAdmNo():""%>'>
		<input type="hidden" name="strWardTypeCode" value='<%=(patVO.getWardTypeCode()!=null && !patVO.getWardTypeCode().trim().equals(""))?patVO.getWardTypeCode():""%>'>
		<input type="hidden" name="strWardCode" value='<%=(patVO.getWardCode()!=null && !patVO.getWardCode().trim().equals(""))?patVO.getWardCode():""%>'>
		<input type="hidden" name="strRoomTypeCode" value='<%=(patVO.getIpdRoomTypeCode()!=null && !patVO.getIpdRoomTypeCode().trim().equals(""))?patVO.getIpdRoomTypeCode():""%>'>
		<input type="hidden" name="strBedTypeCode" value='<%=(patVO.getBedTypeCode()!=null && !patVO.getBedTypeCode().trim().equals(""))?patVO.getBedTypeCode():""%>'>
		<input type="hidden" name="strAdmDateAndTime" value='<%=(patVO.getAdmDateTime()!=null && !patVO.getAdmDateTime().trim().equals(""))?patVO.getAdmDateTime():""%>'>
		<input type="hidden" name="strConsultantId" value='<%=(patVO.getConsultantID()!=null && !patVO.getConsultantID().trim().equals(""))?patVO.getConsultantID():""%>'>
		<input type="hidden" name="patBalance" value="<%=patVO.getPatBalance()%>" />   <!-- // added by manisha gangwar for alert on treatment advice detail dashboard for zero balance -->
		

		<table width="100%" cellpadding="0" cellspacing="1">
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
		</table>
		<div id="divPatientDtl" style="display: block;">
		<table width="100%" cellpadding="0" cellspacing="1">
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
			<!--<logic:equal name="InpatientDetailFB" property="isNewBorn" value="1">
				<td class="tdfonthead" width="25%" nowrap>
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:message key="motherCrNo" />
						</font>
					</div>
				</td>			
				<td width="25%" class="tdfont">
					<b>
						<bean:write name="InpatientDetailFB" property="patMotherCrNo" />
					</b>
				</td>
			</logic:equal>-->
			</tr>
			<tr>
				<td class="tdfonthead" width="25%" nowrap>
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:message key="department" />(<bean:message key="unit" />)
						</font>
					</div>
				</td>			
				<td width="25%" class="tdfont">
					<b>
						<font color="#000000" size="2"face="Verdana, Arial, Helvetica, sans-serif">
							<bean:write name="InpatientDetailFB" property="departmentUnitName" />
						</font>
					</b>
				</td>
				<td class="tdfonthead" width="25%" nowrap>
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:message key="admNo" />
						</font>
					</div>
				</td>			
				<td width="25%" class="tdfont">
					<b>
						<font size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:write name="InpatientDetailFB" property="patAdmNo" />
						</font>
					</b>
				</td>
			</tr>
			<tr>
				<td class="tdfonthead" width="25%" nowrap>
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:message key="ward" />/
							<bean:message key="roomNo" />
						</font>
					</div>
				</td>			
				<td width="25%" class="tdfont">
					<b>
						<font color="#000000" size="2"face="Verdana, Arial, Helvetica, sans-serif">
							<bean:write name="InpatientDetailFB" property="wardName" />/<bean:write name="InpatientDetailFB" property="ipdRoomName" />
						</font>
					</b>
				</td>
				<td class="tdfonthead" width="25%" nowrap>
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:message key="bedNo" />
						</font>
					</div>
				</td>			
				<td width="25%" class="tdfont">
					<b>
						<font color="#000000" size="2"face="Verdana, Arial, Helvetica, sans-serif">
							<bean:write name="InpatientDetailFB" property="bedName" />
						</font>
					</b>
				</td>
			</tr>
			<% // Changes By Pragya dated 2015.01.16 Now Dead Status will be checked from IsDead flag not Patient Status
			PatientDetailVO patDtlVO=(PatientDetailVO)session.getAttribute(InpatientConfig.INPATIENT_ADMISSION_VO);  
				if((patDtlVO.getMlcNo() != null && !patDtlVO.getMlcNo().equals("")) || (patDtlVO.getPatStatusCode() !=null && (patDtlVO.getIsDead()!=null) && patDtlVO.getIsDead().equals(RegistrationConfig.PATIENT_STATUS_CODE_DEAD)))
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
				
				<logic:equal name="InpatientDetailFB" property="isDead" value="<%=RegistrationConfig.PATIENT_STATUS_CODE_DEAD  %>">
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
				<logic:notEqual name="InpatientDetailFB" property="isDead" value="<%=RegistrationConfig.PATIENT_STATUS_CODE_DEAD  %>">
					<td class="tdfonthead" width="25%" nowrap></td>
					<td width="25%" class="tdfont"></td>
				</logic:notEqual>
				
			</tr>
			<%} %>
			<% 
			if(((patDtlVO.getPatIsPregnant() != null && !patDtlVO.getPatIsPregnant().equals("") && patDtlVO.getPatIsPregnant().equals(OpdConfig.YES))
					&& (patDtlVO.getPatGestationWeek() != null && !patDtlVO.getPatGestationWeek().equals("") )) ||
					(patDtlVO.getConsultantName() != null && !patDtlVO.getConsultantName().equals("")))	
			{
			%>
			<tr>
			<%	
				if((patDtlVO.getPatIsPregnant() != null && !patDtlVO.getPatIsPregnant().equals("") && patDtlVO.getPatIsPregnant().equals(OpdConfig.YES))
						&& (patDtlVO.getPatGestationWeek() != null && !patDtlVO.getPatGestationWeek().equals("") ))
				{
			%>
				<td class="tdfonthead" width="25%" nowrap>
					<div align="right">
						<font size="2" face="Verdana, Arial, Helvetica, sans-serif">
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
			<%}else{ %>
				<td class="tdfonthead" width="25%" nowrap></td>
				<td width="25%" class="tdfont"></td>
			<%	}
				if((patDtlVO.getConsultantName() != null && !patDtlVO.getConsultantName().equals("")))
				{
			%>
				<td class="tdfonthead" width="25%" nowrap>
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							 <bean:message	key="consultant" /> <bean:message	key="name" />
					     </font>
			    	 </div>
				</td>
				<td width="25%" class="tdfont">
					<b>
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:write	name="InpatientDetailFB" property="consultantName" />
						</font>
					</b>
				</td>
			<%}else{ %>
				<td class="tdfonthead" width="25%" nowrap></td>
				<td width="25%" class="tdfont"></td>
			<%} %>
			</tr>
			<%} %>
		</table>
		</div>
	</his:ContentTag>

</his:statusInProcessWithJsp>
