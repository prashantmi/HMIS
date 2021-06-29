
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>

<%@taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<%@page import="registration.RegistrationConfig"%>
<%@page import="java.util.Date"%>
<%@page import="hisglobal.presentation.WebUTIL"%>
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
<his:javascript src="/registration/js/dateFunctions.js" />
<his:javascript src="/registration/js/policeExaminationReportGenerationDtl.js" />
<his:javascript src="/registration/js/popup.js" />
<his:javascript src="/hisglobal/js/calendar.js"/>

<body>
<his:TransactionContainer>
	<logic:equal name="policeExaminationReportGenerationDtlFB" property="isDirectCall" value="DIRECT">
		<form action="/HISClinical/registration/policeExaminationReportGenerationDtl.cnt" >
	</logic:equal>
			<his:TitleTag name="Police Examination Report Generation Detail">
			</his:TitleTag>
			
			<his:statusNew>	
				<logic:empty name="policeExaminationReportGenerationDtlFB" property="patCrNo" >
					<his:InputCrNoTag name="policeExaminationReportGenerationDtlFB"></his:InputCrNoTag>
				</logic:empty>
			</his:statusNew>
			<bean:define id="crNo" name="policeExaminationReportGenerationDtlFB" property="patCrNo" type="java.lang.String"/>

<%if(!crNo.trim().equals("")){%>
  <jsp:include page="/registration/patientDetail.cnt" flush="true" />  
  <%} %>
				
 
  <his:ContentTag>
  <his:statusList>
  <logic:empty name="policeExaminationReportGenerationDtlFB" property="episodeCode">
  
  <his:SubTitleTag name="Patients Those Who Requested For Police Examination">
  </his:SubTitleTag>
  
  <div id="divLstPatForPoliceExamReqtId" >
		<table width="100%" cellpadding="0" cellspacing="1">
			<tr>
				<td width="5%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;" nowrap valign="top">
					<div align="center"><b>Select</b></div>
				</td>
				<td width="10%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;" nowrap valign="top">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b><bean:message key="qNo" /></b>
						</font>
					</div>
				</td>
				<td width="19%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;" nowrap>
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b><bean:message key="crNo" /></b>
						</font>
					</div>
				</td>
				<td width="20%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;" nowrap valign="top">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b><bean:message key="name" /></b>
						</font>
					</div>
				</td>
				<td width="10%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;" nowrap>
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b><bean:message key="gender/age" /></b>
						</font>
					</div>
				</td>
				<td width="19%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;" nowrap="nowrap" align="right">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b><bean:message key="primCat" /></b>
						</font>
					</div>
				</td>
			</tr>

			<logic:empty name="<%=RegistrationConfig.CASUALTY_DESK_PATIENT_LIST_PATIENTDTL_VO%>">
				<tr>
					<td width="100%" class="tdfont" nowrap valign="top" colspan="6">
						<div align="left">
							<font size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>No Request Raised For This Patient</b>
							</font>
						</div>		
					</td>
				</tr>
			</logic:empty>

			<logic:notEmpty name="<%=RegistrationConfig.CASUALTY_DESK_PATIENT_LIST_PATIENTDTL_VO%>">
			<logic:iterate id="patientList" name="<%=RegistrationConfig.CASUALTY_DESK_PATIENT_LIST_PATIENTDTL_VO%>" type="hisglobal.vo.PatientDetailVO">
				
				<tr>
					<td width="5%" class="tdfont" nowrap="nowrap" valign="top" >						
						<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<html:radio name="policeExaminationReportGenerationDtlFB" property="episodeChk" value='<%=patientList.getPatCrNo()+"^"+ patientList.getEpisodeCode()+"^"+ patientList.getEpisodeVisitNo()%>' onclick="getPoliceExamReportGenerationPage(this);" onkeypress="getPoliceExamReportGenerationPage(this);" />
									<input type="hidden" name="<%=patientList.getPatCrNo()%>" value="<%=false%>"/>
								</font>
						</div>						
					</td>
					<td width="10%" class="tdfont" nowrap="nowrap" valign="top" >
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								&nbsp;<% if(patientList.getQueNo()!=null) { %> <%=patientList.getQueNo()%> <% } %>
							</font>
						</div>
					</td>
					<td width="18%" class="tdfont" nowrap="nowrap" valign="top" >
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<%=patientList.getPatCrNo()%>
							</font>
						</div>
					</td>
					<td width="20%" class="tdfont" nowrap="nowrap" valign="top" >
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<%=patientList.getPatFirstName()%>
								<% if(patientList.getPatMiddleName()!=null) { %> <%=patientList.getPatMiddleName()%> <% } %>
								<% if(patientList.getPatLastName()!=null) { %> <%=patientList.getPatLastName()%> <% } %>
							</font>
						</div>
					</td>
					<td width="10%" class="tdfont" nowrap="nowrap" valign="top" >
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<%=patientList.getPatGenderAge()%>
							</font>
						</div>
					</td>
					<td width="18%" class="tdfont" nowrap="nowrap" valign="top" >
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<%=patientList.getPatPrimaryCat()%>
							</font>
						</div>
					</td>
				</tr>
			</logic:iterate>
			</logic:notEmpty>
		</table>
	</div>
	</logic:empty>
	
  <logic:notEmpty name="policeExaminationReportGenerationDtlFB" property="episodeCode">
  
  <his:SubTitleTag name="Police Examination Request Detail">
  </his:SubTitleTag>
  
  <div id="divLstPoliceExamReqtDtlId" >
		<table width="100%" cellpadding="0" cellspacing="1">
			<tr>
				<td width="5%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;" nowrap valign="top">
					<div align="center"><b>Select</b></div>
				</td>
				<td width="24%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;" nowrap valign="top">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							Examination Type
						</font>
					</div>
				</td>
				<td width="24%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;" nowrap>
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							Requested Officer
						</font>
					</div>
				</td>
				<td width="24%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;" nowrap valign="top">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							Request Status
						</font>
					</div>
				</td>
				<td width="23%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;" nowrap>
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							Request Date
						</font>
					</div>
				</td>
				
			</tr>

			<logic:empty name="<%=RegistrationConfig.CASUALTY_DESK_LIST_OF_POLICE_EXAM_REQT_DTL_VO%>">
				<tr>
					<td width="100%" class="tdfont" nowrap valign="top" colspan="6">
						<div align="center">
							<font size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>No Request Raised For This Patient</b>
							</font>
						</div>		
					</td>
				</tr>
			</logic:empty>

			<logic:notEmpty name="<%=RegistrationConfig.CASUALTY_DESK_LIST_OF_POLICE_EXAM_REQT_DTL_VO%>">
			<logic:iterate id="patExamReqtVO" indexId="episodeIndexId" name="<%=RegistrationConfig.CASUALTY_DESK_LIST_OF_POLICE_EXAM_REQT_DTL_VO%>" type="hisglobal.vo.PoliceExaminationReqtDtlVO">
				
				<tr>
					<td width="5%" class="tdfont" nowrap="nowrap" valign="top" >						
						<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<%	String strRradioDisabled=""; 
										if("4".equals(patExamReqtVO.getReqStatus())){
											strRradioDisabled="disabled='disabled''";
										}
									%>
									<input type="radio" name="policeExamReportGnRadio" value="<%=episodeIndexId%>" onclick="getHandOverDtl(this);" <%=strRradioDisabled %>  >
								</font>
						</div>						
					</td>
					<td width="24%" class="tdfont" nowrap="nowrap" valign="top" >
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">&nbsp;
								 <a href="#" onclick="openPrintPopup(event,<%=patExamReqtVO.getTemplateId()%>);">
								 	<%= patExamReqtVO.getPoliceExaminationTypeDesc() %>
								 </a>
							</font>
						</div>
					</td>
					<td width="24%" class="tdfont" nowrap="nowrap" valign="top" >
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<%=patExamReqtVO.getOfficerIncharge()%>
							</font>
						</div>
					</td>
					<td width="24%" class="tdfont" nowrap="nowrap" valign="top" >
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<%=patExamReqtVO.getReqStatusDesc()%>
							</font>
						</div>
					</td>
					<td width="23%" class="tdfont" nowrap="nowrap" valign="top" >
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<%=patExamReqtVO.getRequestDate()%>
							</font>
						</div>
					</td>
					
				</tr>
			</logic:iterate>
			</logic:notEmpty>
		</table>
	</div>
	</logic:notEmpty>
	</his:statusList>
	
</his:ContentTag>
				
			<his:statusTransactionInProcess>
			
				
				<his:SubTitleTag name="Police Examination Report Generation Details">
				</his:SubTitleTag>
				<his:ContentTag>
					<table width="100%" cellspacing="1" cellpadding="0">
						
						<tr>
							<td class="tdfonthead" nowrap="nowrap" width="25%">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<font color="#FF0000">*</font>Handover To Officer Name
								</font>
							</div>
							</td>
							<td class="tdfont" nowrap="nowrap" width="25%">
							<div align="left">
								<html:text name="policeExaminationReportGenerationDtlFB" property="handOverToOffIncharge" maxlength="60" styleClass="textboxBig" onkeypress="return validateAlphabetsWithDotsOnly(event,this)" tabindex="1"></html:text>
							</div>
							</td>
							
							<td class="tdfonthead" nowrap="nowrap" width="25%">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<font color="#FF0000">*</font>Handover To Officer Designation
								</font>
							</div>
							</td>
							<td class="tdfont" nowrap="nowrap" width="25%">
							<div align="left">
								<html:text name="policeExaminationReportGenerationDtlFB" property="handOverToOffDesignation" maxlength="50" styleClass="textboxBig" onkeypress="return validateAlphabetsWithDotsOnly(event,this)" tabindex="1"></html:text>
							</div>
							</td>
						</tr>
						<tr>
							<td class="tdfonthead" nowrap="nowrap" width="25%">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<!--<font color="#FF0000">*</font>-->
									Handover To Officer Batch No
								</font>
							</div>
							</td>
							<td class="tdfont" nowrap="nowrap" width="25%">
							<div align="left">
								<html:text name="policeExaminationReportGenerationDtlFB" property="handOverToOffBatchNo" maxlength="50" styleClass="textboxBig" onkeypress="return validateAlphaNumericOnly(event,this)" tabindex="1"></html:text>
							</div>
							</td>
							
							<td class="tdfonthead" nowrap="nowrap" width="25%">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<font color="#FF0000">*</font>Handover Date
								</font>
							</div>
							</td>
							<td class="tdfont" nowrap="nowrap" width="25%">
							<div align="left">
								<bean:define id="ctDate" name="policeExaminationReportGenerationDtlFB" property="handOverDate" type="java.lang.String"></bean:define>
								<% if("".equals(ctDate)){
									ctDate = WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy"); 
								}%>
								
								<his:date name='handOverDate' dateFormate="%d-%b-%Y" value="<%=ctDate%>" />
							</div>
							</td>
						</tr>
						<tr>
							<td class="tdfonthead" nowrap="nowrap" width="25%">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									Is Final
								</font>
							</div>
							</td>
							<td class="tdfont" nowrap="nowrap" width="25%">
							<div align="left">
								<html:checkbox name="policeExaminationReportGenerationDtlFB" property="isFinal" tabindex="1"></html:checkbox>
							</div>
							</td>
							<td class="tdfonthead" width="75%" colspan="3"></td>
						</tr>
					</table>
						
					
						
					<table width="100%" cellspacing="1" cellpadding="0">
						<tr>
							<td colspan="4">
								<table width="100%" cellpadding="0" cellspacing="1">
									<tr>
										<td nowrap="nowrap" class="tdfonthead" width="25%">
										<div align="right">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<!--<font color="#FF0000">*</font>-->Remarks
												<!--<bean:message key="caseremarks" />-->
											</font>
										</div>
										</td>
						
										<td colspan="2" nowrap="nowrap" width="75%" class="tdfont">
										<div align="left">
											<html:textarea name="policeExaminationReportGenerationDtlFB" tabindex="1" property="finalRemarks" rows="1" cols="90" 
												onkeypress="return (validateTextArea(event,this,'500') && validateAlphaNumericOnly(event,this))" />
										</div>
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				</his:ContentTag>
				<!--<his:statusRecordFound>
				</his:statusRecordFound>-->
			</his:statusTransactionInProcess>	
			
			
			<his:ButtonToolBarTag>
				<his:statusNew>		
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style="cursor: pointer;" tabindex="1" onclick =" submitForm('CANCEL')" onkeypress="if(event.keyCode==13)submitForm('CANCEL')">
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>'  style="cursor: pointer;" tabindex="1" onclick =" submitForm('NEW')" onkeypress="if(event.keyCode==13)submitForm('NEW')">
				</his:statusNew>
				
				<his:statusTransactionInProcess>
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>'  style="cursor: pointer;" tabindex="1" onclick ="if(validateSave()) submitForm('SAVE')" onkeypress="if(event.keyCode==13)if(validateSave()) submitForm('SAVE')">
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style="cursor: pointer;" tabindex="1" onclick =" submitForm('GETPATDTL')" onkeypress="if(event.keyCode==13)submitForm('GETPATDTL')">
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>'  style="cursor: pointer;" tabindex="1" onclick =" clearForm()" onkeypress="if(event.keyCode==13)clearForm()">
				</his:statusTransactionInProcess>
				
				<his:statusList>
					<logic:equal name="policeExaminationReportGenerationDtlFB" property="isDirectCall" value="DIRECT">
						<logic:empty name="policeExaminationReportGenerationDtlFB" property="episodeCode">
							<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style="cursor: pointer;" tabindex="1" onclick =" submitForm('NEW')" onkeypress="if(event.keyCode==13)submitForm('NEW')">
						</logic:empty>
						<logic:notEmpty name="policeExaminationReportGenerationDtlFB" property="episodeCode">
							<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style="cursor: pointer;" tabindex="1" onclick =" submitForm('GETPATDTL')" onkeypress="if(event.keyCode==13)submitForm('GETPATDTL')">
						</logic:notEmpty>
					</logic:equal>
					<!-- isDirectCall!='DIRECT' means  isDirectCall='DESK' -->
					<logic:notEqual name="policeExaminationReportGenerationDtlFB" property="isDirectCall" value="DIRECT">
						<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style="cursor: pointer;" tabindex="1" onclick =" submitToDesk('NEW','NEW')" onkeypress="if(event.keyCode==13) submitToDesk('NEW','NEW')">
					</logic:notEqual>
				</his:statusList>	
				<his:statusUnsuccessfull>
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style="cursor: pointer;" tabindex="1" onclick =" submitForm('NEW')" onkeypress="if(event.keyCode==13)submitForm('NEW')">
				</his:statusUnsuccessfull>	
			</his:ButtonToolBarTag>
		<html:hidden name="policeExaminationReportGenerationDtlFB" property="patCrNo"/>
		<html:hidden name="policeExaminationReportGenerationDtlFB" property="hmode"/>
		<html:hidden name="policeExaminationReportGenerationDtlFB" property="isDirectCall"/>
		<html:hidden name="policeExaminationReportGenerationDtlFB" property="episodeCode"/>
		<html:hidden name="policeExaminationReportGenerationDtlFB" property="episodeVisitNo"/>
		<html:hidden name="policeExaminationReportGenerationDtlFB" property="selectedEpisodeIndex"/>
		<html:hidden name="policeExaminationReportGenerationDtlFB" property="deskType"/>
		<html:hidden name="policeExaminationReportGenerationDtlFB" property="templateHtmlCode"/>
	
	<logic:equal name="policeExaminationReportGenerationDtlFB" property="isDirectCall" value="DIRECT">
		</form>
		<his:status/>
	</logic:equal>
	</his:TransactionContainer>	
</body>

</html>