
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<%@page import="opd.OpdConfig"%>

<%@page import="hisglobal.utility.servlets.ServletsUtilityConfig"%>
<%@page import="hisglobal.hisconfig.Config"%>
<his:javascript src="/opd/js/generic_patient_profile.js"/>
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<his:TransactionContainer> 

<his:TitleTag key="patientProfile">
</his:TitleTag>

<jsp:include page="/inpatient/inpatientDetail.cnt" flush="true"></jsp:include>

<his:statusNew>
<logic:notEmpty name="<%=OpdConfig.PATIENT_PROFILES_FOR_ALL_LIST%>">
	<his:SubTitleTag key="previousProfileDtl">
	</his:SubTitleTag>
	
	<his:ContentTag>
		<table width="100%" cellpadding="0" cellspacing="1">
			<tr>
				<td width="10%" class="tdfonthead">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b><bean:message key="SNo" /></b>
						</font>
					</div>
				</td>

				<td width="90%" class="tdfonthead">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b><bean:message key="profileHeader" /></b>
						</font>
					</div>
				</td>

			</tr>
			<logic:iterate id="patProfileVO" name="<%=OpdConfig.PATIENT_PROFILES_FOR_ALL_LIST%>" indexId="idx" type="hisglobal.vo.PatientProfileDetailVO">
			<tr>
				<td width="10%" class="tdfont">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b><%=(idx.intValue()+1)%>.</b>
						</font>
					</div>
				</td>
				<%
					String displayFileServletURL = "/HISClinical/DisplayFileServlet?" + ServletsUtilityConfig.FILE_PATH_WINDOWS + "=" + Config.PATIENT_PROFILE_STORAGE_PATH_WINDOWS 
							+ "&" + ServletsUtilityConfig.FILE_PATH_LINUX + "=" + Config.PATIENT_PROFILE_STORAGE_PATH_LINUX 
							+ "&" + ServletsUtilityConfig.FILE_NAME + "=" + patProfileVO.getProfileId()+Config.PATIENT_PROFILE_FILE_STORAGE_EXT;
				 %>
				<td width="90%" class="tdfont">
					<div align="center">
						<font size="2" face="Verdana, Arial, Helvetica, sans-serif"><b>
							<a style="cursor: pointer;" onclick="displayProfileFile('<%=displayFileServletURL%>');">
								<bean:write name="patProfileVO" property="profileHeader"/>
							</a>
						</b></font>
					</div>
				</td>

			</tr>
			</logic:iterate>
		</table>
	</his:ContentTag>
</logic:notEmpty>	
</his:statusNew>

<his:ButtonToolBarTag>	
	<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' tabindex="1" style="cursor: pointer" onclick="submitToDesk('NEW','NEW')" onkeypress="if(event.keyCode==13) submitToDesk('NEW','NEW')">
</his:ButtonToolBarTag>

</his:TransactionContainer>

<html:hidden name="GenericPatientProfileFB" property="hmode" />

<html:hidden name="GenericPatientProfileFB" property="patCrNo" />
<html:hidden name="GenericPatientProfileFB" property="departmentUnitCode" />
