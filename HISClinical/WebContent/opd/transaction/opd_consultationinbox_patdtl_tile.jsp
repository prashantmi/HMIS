<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<%@page import="hisglobal.vo.PatientDetailVO"%>
<%@page import="opd.OpdConfig"%>
<%@page import="inpatient.InpatientConfig"%>
<%@page import="registration.RegistrationConfig"%>
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
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

<%
PatientDetailVO patDtlVO = (PatientDetailVO) session.getAttribute(OpdConfig.OPD_CONSULTATION_INBOX_MAIL_PATIENT_DETAIL_VO);
if(patDtlVO!=null){  
%>

<%	if(!patDtlVO.getIsIpd().equals(InpatientConfig.DESK_IPD_TILE))	{%>
<his:SubTitleTag>
		<his:name>
			<bean:message key="patient"/>
			<bean:message key="detail"/>
		 </his:name>
		 <b>
			<bean:message key="registrationDate" />
			<%=(patDtlVO.getRegisterDate()!=null)?patDtlVO.getRegisterDate():""%>
		</b>
	</his:SubTitleTag>
<%	}else{ %>
	<his:SubTitleTag>
	<his:name>
		<bean:message key="patient"/>
		<bean:message key="detail"/>
	 </his:name>
	 <b>
	 	<bean:message key="admFrmDays" />
		<%=(patDtlVO.getPatAdmittedDays()!=null)?patDtlVO.getPatAdmittedDays():""%>
	 	(
		<bean:message key="admDate" />
		<%=(patDtlVO.getAdmDateTime()!=null)?patDtlVO.getAdmDateTime():""%>
		)
	</b>
	<img id="imgPatientDtl" tabindex="1" style="cursor: pointer; vertical-align: middle;" src="/HIS/hisglobal/images/avai/arrow-up.png"; onclick="onclickImage(this)"/>
	</his:SubTitleTag>
<%	} %>
	<his:ContentTag>
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
							<%=(patDtlVO.getPatFirstName()!=null)?patDtlVO.getPatFirstName():""%>
							<%=(patDtlVO.getPatMiddleName()!=null)?patDtlVO.getPatMiddleName():""%>
							<%=(patDtlVO.getPatLastName()!=null)?patDtlVO.getPatLastName():""%>
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
							<%=(patDtlVO.getPatCrNo()!=null)?patDtlVO.getPatCrNo():""%>
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
							<%=(patDtlVO.getPatAge()!=null)?patDtlVO.getPatAge():""%>
							<%if(patDtlVO.getPatGender()!=null && !patDtlVO.getPatGender().trim().equals("")) {%>
								<%=patDtlVO.getPatGender()%>
							<%} %>
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
							<%=(patDtlVO.getPatPrimaryCat()!=null)?patDtlVO.getPatPrimaryCat():""%>
						</font>
					</b>
				</td>
			</tr>			
		<%	if(patDtlVO.getIsIpd().equals(InpatientConfig.DESK_IPD_TILE))	{%>
		</table>
		<div id="divPatientDtl" style="display: block;">
		<table width="100%" cellpadding="0" cellspacing="1">
		<%	} %>	
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
							<%=(patDtlVO.getPatGuardianName()!=null)?patDtlVO.getPatGuardianName():""%>
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
							<%=(patDtlVO.getPatSpouceName()!=null)?patDtlVO.getPatSpouceName():""%>
						</font>
					</b>
				</td>
			</tr>
			<%	if(patDtlVO.getIsIpd().equals(InpatientConfig.DESK_IPD_TILE))	{%>
			<tr>
				<td class="tdfonthead" width="25%" nowrap>
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:message key="dept/unit" />
						</font>
					</div>
				</td>			
				<td width="25%" class="tdfont">
					<b>
						<font color="#000000" size="2"face="Verdana, Arial, Helvetica, sans-serif">
							<%=(patDtlVO.getDepartmentName()!=null)?patDtlVO.getDepartmentName():""%>
							<%=(patDtlVO.getDepartmentUnitName()!=null)?patDtlVO.getDepartmentUnitName():""%>
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
						<%=(patDtlVO.getPatAdmNo()!=null)?patDtlVO.getPatAdmNo():""%>
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
							<%=(patDtlVO.getWardName()!=null)?patDtlVO.getWardName():""%>
							<%=(patDtlVO.getIpdRoomName()!=null)?patDtlVO.getIpdRoomName():""%>
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
							<%=(patDtlVO.getBedName()!=null)?patDtlVO.getBedName():""%>
						</font>
					</b>
				</td>
			</tr>
			<%	} %>	

			<%  
				if((patDtlVO.getMlcNo() != null && !patDtlVO.getMlcNo().equals("")) || (patDtlVO.getPatStatusCode().equals(RegistrationConfig.PATIENT_STATUS_CODE_DEAD)))
				{
			%>
			<tr>
				<%  
					if(patDtlVO.getMlcNo() != null && !patDtlVO.getMlcNo().equals(""))
					{
				%>
					<td class="tdfonthead" width="25%" nowrap>
						<div align="right">
							<font color="FF0000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								 <bean:message	key="mlcNo" />
						     </font>
				    	 </div>
					</td>
					<td width="25%" class="tdfont">
						<b>
							<font color="#FF0000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<%=(patDtlVO.getMlcNo()!=null)?patDtlVO.getMlcNo():""%>
							</font>
						</b>
					</td>
				<%	}	else	{	%>
					<td class="tdfonthead" width="25%" nowrap></td>
					<td width="25%" class="tdfont"></td>
				<%	
					}
					if(patDtlVO.getPatStatusCode().equals(RegistrationConfig.PATIENT_STATUS_CODE_DEAD))
					{
				%>
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
				<%	}	else	{ %>
					<td class="tdfonthead" width="25%" nowrap></td>
					<td width="25%" class="tdfont"></td>
				<%	} %>
			</tr>
			<%	
				}
				if((patDtlVO.getPatIsPregnant() != null && !patDtlVO.getPatIsPregnant().equals("") && patDtlVO.getPatIsPregnant().equals(OpdConfig.YES))
						&& (patDtlVO.getPatGestationWeek() != null && !patDtlVO.getPatGestationWeek().equals("") ))
				{
			%>
			<tr>
				<td class="tdfonthead" width="25%" nowrap>
					<div align="right">
						<font color="FF0000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							 <bean:message	key="gestationweek" />
					     </font>
			    	 </div>
				</td>
				<td width="25%" class="tdfont">
					<b>
						<font color="#FF0000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<%=(patDtlVO.getPatGestationWeek()!=null)?patDtlVO.getPatGestationWeek():""%>
						</font>
					</b>
				</td>
				<td class="tdfonthead" width="25%" nowrap></td>
				<td width="25%" class="tdfont"></td>
			</tr>
			<%} %>
		</table>
		<%	if(patDtlVO.getIsIpd().equals(InpatientConfig.DESK_IPD_TILE))	{%>
		</div>
		<%	} %>
	</his:ContentTag>
<%}%>