<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>

<%@page import="mrd.MrdConfig"%>
<%@page import="hisglobal.vo.PatMedicalDtlVO"%>
<%@page import="hisglobal.hisconfig.Config"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/master.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/>
<his:css src="/hisglobal/css/calendar-blue2.css"/>

<his:javascript src="/registration/js/registration.js" />
<his:javascript src="/registration/js/validationCalls.js" />
<his:javascript src="/registration/js/validationCommon.js" />
<his:javascript src="/registration/js/commonFunctions.js" />
<his:javascript src="/registration/js/popup.js" />
<his:javascript src="/hisglobal/js/calendar.js"/>
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>

<script type="text/javascript">

function submitPage(mode)
{
	document.getElementsByName("hmode")[0].value=mode;
	document.forms[0].submit();
}

</script>

	<body>
		<html:form action="/fitnessCertificate">
			<his:TransactionContainer>
		
			<his:TitleTag name="Fitness Certificate">
			</his:TitleTag>
			
			<his:InputCrNoTag name="FitnessCertificateFB"></his:InputCrNoTag>
			
				<his:statusList>
					<jsp:include page="/registration/patientDetail.cnt" flush="true" />
					
					<his:SubTitleTag name="Patient All Episodes">
					</his:SubTitleTag>
					
					<his:ContentTag>
						<table width="100%" border="0"  cellspacing="1" cellpadding="0">
							<tr>
								<td width="10%" class="tdfonthead">
									<div align="center">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<b>
												<bean:message key="select"/>
											</b>	
										</font>
									</div>
								</td>
								<td width="30%" class="tdfonthead">
									<div align="center">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<b>
												<bean:message key="deptName"/>
											</b>	
										</font>
									</div>
								</td>
								<td width="30%" class="tdfonthead">
									<div align="center">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<b>
												<bean:message key="unit"/>
											</b>	
										</font>
									</div>
								</td>
								<td width="30%" class="tdfonthead">
									<div align="center">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<b>
												<bean:message key="epiStartDate"/>
											</b>	
										</font>
									</div>
								</td>
							</tr>
							<logic:iterate id="arrEpisodeVO" name="<%=MrdConfig.MC_PATIENT_ALL_EPISODE_VO_ARR %>" type="hisglobal.vo.EpisodeVO">
								<tr>
									<td class="tdfont" width="10%" >
										<div align="center">
											<html:radio name="FitnessCertificateFB" property="selectedEpiCode" value="<%=arrEpisodeVO.getEpisodeCode() %>" onclick="submitPage('GETMCDTL')"></html:radio>
										</div>
									</td>
									<td class="tdfont" width="30%" >
										<div align="center">
											<bean:write name="arrEpisodeVO" property="department"/>
										</div>
									</td>
									<td class="tdfont" width="30%" >
										<div align="center">
											<bean:write name="arrEpisodeVO" property="departmentUnit"/>
										</div>
									</td>
									<td class="tdfont" width="30%" >
										<div align="center">
											<bean:write name="arrEpisodeVO" property="episodeDate"/>
										</div>
									</td>
								</tr>	
							</logic:iterate>
						</table>	
					</his:ContentTag>
				</his:statusList>
				
				<his:statusTransactionInProcess>
				<jsp:include page="/registration/patientDetail.cnt" flush="true" />
				<%PatMedicalDtlVO[] issuePatMCVO=(PatMedicalDtlVO[])session.getAttribute(MrdConfig.FC_PATIENT_MEDICAL_CERTIFICATE_LIST_BY_EPISODE );
					if(issuePatMCVO.length>0){
				 %>
				 <logic:equal name="FitnessCertificateFB" property="hmode" value="GETMCDTL">
					<his:SubTitleTag name="Issued Medical Certificate">
					</his:SubTitleTag>
						
						<his:ContentTag>
							<table width="100%" border="0"  cellspacing="1" cellpadding="0">
								<tr>
									<td width="8%" class="tdfonthead">
										<div align="center">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<b>
													<bean:message key="select"/>
												</b>	
											</font>
										</div>
									</td>
									<td width="23%" class="tdfonthead">
										<div align="center">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<b>
													<bean:message key="sufferingFrom"/>
												</b>	
											</font>
										</div>
									</td>
									<td width="23%" class="tdfonthead">
										<div align="center">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<b>
													<bean:message key="advice"/>
													<bean:message key="day"/>
												</b>	
											</font>
										</div>
									</td>
									<td width="23%" class="tdfonthead">
										<div align="center">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<b>
													<bean:message key="startday"/>
												</b>	
											</font>
										</div>
									</td>
									<td width="23%" class="tdfonthead">
										<div align="center">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<b>
													<bean:message key="endday"/>
												</b>	
											</font>
										</div>
									</td>
								</tr>
								<logic:iterate id="issueMCVO" name="<%=MrdConfig.FC_PATIENT_MEDICAL_CERTIFICATE_LIST_BY_EPISODE %>" type="hisglobal.vo.PatMedicalDtlVO" indexId="idx">
									<tr>
										<td class="tdfont" width="8%" >
											<div align="center">
												<html:radio name="FitnessCertificateFB" property="selectedMC" value="<%=idx.toString() %>" onclick="submitPage('GETFITNESSDATE')"></html:radio>
											</div>
										</td>
										<td class="tdfont" width="23%" >
											<div align="center">
												<bean:write name="issueMCVO" property="sufferingFrom"/>
											</div>
										</td>
										<td class="tdfont" width="23%" >
											<div align="center">
												<bean:write name="issueMCVO" property="adviceDays"/>
											</div>
										</td>
										<td class="tdfont" width="23%" >
											<div align="center">
												<bean:write name="issueMCVO" property="fromDate"/>
											</div>
										</td>
										<td class="tdfont" width="23%" >
											<div align="center">
												<bean:write name="issueMCVO" property="toDate"/>
											</div>
										</td>
									</tr>
								</logic:iterate>
							</table>
						</his:ContentTag>	
					</logic:equal>	
				<%} %>	
				
					 <logic:equal name="FitnessCertificateFB" property="hmode" value="GETFITNESSDATE">
					 	<his:SubTitleTag name="Fitness Details">
					 	</his:SubTitleTag>
					 	
					 	<his:ContentTag>
					 		<table width="100%" border="0"  cellspacing="1" cellpadding="0">
					 			<logic:equal name="FitnessCertificateFB" property="generationMode" value="<%=Config.MEDICAL_CERTIFICATE_GENERATION_MANUAL %>">
					 				<tr>
										<td width="25%" class="tdfonthead">
											<div align="right">
												<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
													<bean:message key="fitCertificateNo"/>
												</font>
											</div>
										</td>
										<td width="25%" class="tdfont" colspan="3">
											<div align="left">
												<html:text name="FitnessCertificateFB" property="fitnessCertificateId" maxlength="14" size="25"></html:text>
											</div>
										</td>
									</tr>
					 			</logic:equal>
					 			<tr>
					 				<td width="25%" class="tdfonthead">
					 					<div align="right">
					 						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					 							<bean:message key="sufferingFrom"/>
					 						</font>	
					 					</div>
					 				</td>
					 				<td width="25%" class="tdfont">
					 					<div align="left">
					 						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					 							&nbsp;<bean:write name="FitnessCertificateFB" property="sufferingFrom"/>
					 						</font>	
					 					</div>
					 				</td>
					 				<td width="25%" class="tdfonthead">
					 					<div align="right">
					 						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					 							<bean:message key="advice"/>
												<bean:message key="day"/>
					 						</font>	
					 					</div>
					 				</td>
					 				<td width="25%" class="tdfont">
					 					<div align="left">
					 						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					 							&nbsp;<bean:write name="FitnessCertificateFB" property="adviceDays"/>
					 						</font>	
					 					</div>
					 				</td>
					 			</tr>
					 			<tr>
					 				<td width="25%" class="tdfonthead">
					 					<div align="right">
					 						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					 							<bean:message key="startday"/>
					 						</font>	
					 					</div>
					 				</td>
					 				<td width="25%" class="tdfont">
					 					<div align="left">
					 						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					 							&nbsp;<bean:write name="FitnessCertificateFB" property="fromDate"/>
					 						</font>	
					 					</div>
					 				</td>
					 				<td width="25%" class="tdfonthead">
					 					<div align="right">
					 						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					 							<bean:message key="endday"/>
					 						</font>	
					 					</div>
					 				</td>
					 				<td width="25%" class="tdfont">
					 					<div align="left">
					 						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					 							&nbsp;<bean:write name="FitnessCertificateFB" property="toDate"/>
					 						</font>	
					 					</div>
					 				</td>
					 			</tr>
					 			<tr>
					 				<td width="25%" class="tdfonthead">
					 					<div align="right">
					 						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					 							<bean:message key="fitnessDate"/>
					 						</font>	
					 					</div>
					 				</td>
					 				<td width="25%" class="tdfont">
					 					<div align="left">
					 						&nbsp;<bean:write name="FitnessCertificateFB" property="fitnessDate"/>
					 						<html:hidden name="FitnessCertificateFB" property="fitnessDate"/>
					 					</div>
					 				</td>
					 				<td width="25%" class="tdfonthead"></td>
					 				<td width="25%" class="tdfont"></td>
					 			</tr>
					 		</table>
					 	</his:ContentTag>
					 </logic:equal>
				</his:statusTransactionInProcess>
				
				<his:ButtonToolBarTag>
					<logic:equal name="FitnessCertificateFB" property="hmode" value="GETFITNESSDATE">
						<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>'  style=cursor:pointer tabindex="1" onclick =" submitPage('SAVE')" onkeypress="if(event.keyCode==13) submitPage('SAVE')">
					</logic:equal>
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer tabindex="1" onclick =" submitForm('NEW')" onkeypress="if(event.keyCode==13)submitForm('NEW')">
				</his:ButtonToolBarTag>
			
			</his:TransactionContainer>
			
			<html:hidden name="FitnessCertificateFB" property="hmode" />
			<html:hidden name="FitnessCertificateFB" property="patCrNo"/>
			<html:hidden name="FitnessCertificateFB" property="selectedEpiCode"/>
			<html:hidden name="FitnessCertificateFB" property="selectedMC"/>
			<html:hidden name="FitnessCertificateFB" property="generationMode"/>
			<html:hidden name="FitnessCertificateFB" property="deptUnitCode"/>
		</html:form>
		<his:status/>
	</body>
</html>