
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>

<%@taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<%@page import="registration.RegistrationConfig"%>
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
<his:javascript src="/registration/js/policeExaminationReqtDtl.js" />
<his:javascript src="/registration/js/popup.js" />
<his:javascript src="/hisglobal/js/calendar.js"/>

<body>
	<% String strCancelFuncMethod = "NEW"; %>  
	<logic:equal name="policeExaminationReqtDtlFB" property="isDirectCall" value="DIRECT">
		<form action="/HISClinical/registration/policeExaminationReqtDtl.cnt" >
		<% strCancelFuncMethod = "GETCRNO"; %>  
	</logic:equal>
	<%--<html:form action="/policeExaminationReqtDtl">--%>
		<his:TransactionContainer>
			<his:TitleTag name="Police Examination Request Detail">
			</his:TitleTag>
			
			<his:statusNew>	
				<logic:empty name="policeExaminationReqtDtlFB" property="patCrNo" >
					<his:InputCrNoTag name="policeExaminationReqtDtlFB"></his:InputCrNoTag>
				</logic:empty>
			</his:statusNew>
			<bean:define id="crNo" name="policeExaminationReqtDtlFB" property="patCrNo" type="java.lang.String"/>

<%if(!crNo.trim().equals("")){%>
  <jsp:include page="/registration/patientDetail.cnt" flush="true" />  
  <%} %>
				
 
  <his:ContentTag>
  <his:statusList>
  <his:SubTitleTag name="Patient Episode Detail For Police Examination">
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
								<b>No Episode Found For This Patient</b>
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
									<html:radio name="policeExaminationReqtDtlFB" property="episodeChk" value='<%=patientList.getPatCrNo()+"^"+ patientList.getEpisodeCode()+"^"+ patientList.getEpisodeVisitNo()%>' onclick="getPoliceExamReqtPage(this);" onkeypress="getPoliceExamReqtPage(this);" />
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
	</his:statusList>
	
 <%--
  <table width="100%">
  
  
  <tr>
  <td width="5%" class="tdfonthead">
					<div align="center">				
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
  					 <b><bean:message key="select" /></b>
					</font>
					</div>					
   </td>
   <td width="15%" class="tdfonthead">
					<div align="center">				
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
  					 <b><bean:message key="mlcNo" /></b>
					</font>
					</div>					
   </td>

  <td width="15%" class="tdfonthead">
					<div align="center">				
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<b><bean:message key="mlcDate" /></b>
					</font>
					</div>					
  </td>
  
  </tr>
  <logic:iterate id="arrayMLCVo" name="<%=RegistrationConfig.ARRAY_MLC_VO_POLICE_VERIFICATION %>" type="hisglobal.vo.MlcVO">
  <tr>
  
  		<td width="5%" class="tdfont">	  
		<div align="center">				
		<html:radio name="policeExaminationReqtDtlFB" property="selectedMlc" value="<%=arrayMLCVo.getMlcNo() %>" onclick="submitForm('GETPOLICEDETAIL')">
		</html:radio>
					</div>
		</td>	
        <td width="15%" class="tdfont">	  
		<div align="center">				
		<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<bean:write	name="arrayMLCVo" property="mlcNo" />
					</font>
					</div>
		</td>		
	
		<td width="15%" class="tdfont">
					<div align="center">				
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<bean:write	name="arrayMLCVo" property="mlcDate" />
					</font>
					</div>
		</td>			
		
  </tr>
  </logic:iterate>
 </table>
--%>
</his:ContentTag>
				
			<his:statusTransactionInProcess>
			
				
				<his:SubTitleTag name="Police Verification Details">
				</his:SubTitleTag>
				<his:ContentTag>
					<table width="100%" cellspacing="1" cellpadding="0">
						<tr>
							<td width="25%" nowrap="nowrap" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<font color="#FF0000">*</font>
									Examination Type
								</font>
							</div>
							</td>
							<td width="25%" nowrap="nowrap" class="tdfont">
							<div align="left">
								<html:select name="policeExaminationReqtDtlFB"
										property="policeExaminationType" tabindex="1" styleClass="regCbo">
									<html:option value="-1">Select Value</html:option>
									<html:options property="value" labelProperty="label"
										collection="<%=RegistrationConfig.CASUALTYDESK_OPTION_EXAMINATION_TYPE%>"/>
								</html:select>
							</div>
							</td>
							<td width="25%" nowrap="nowrap" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<!--<font color="#FF0000">*</font>-->
									<bean:message key="policecaseno" />
								</font>
							</div>
							</td>
			
							<td width="25%" nowrap="nowrap" class="tdfont">
							<div align="left">
								<html:text name="policeExaminationReqtDtlFB" tabindex="1" maxlength="50" styleClass="textboxBig" 
								property="caseNo" onkeypress="return validateAlphaNumericOnly(event,this)" />
							</div>
							</td>
						</tr>
						<tr>
							<td width="25%" nowrap="nowrap" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<font color="#FF0000">*</font>
									<bean:message key="policestation" />
								</font>
							</div>
							</td>
			
							<td width="25%" nowrap="nowrap" class="tdfont">
							<div align="left">
								<html:select name="policeExaminationReqtDtlFB"
										property="policeStation" tabindex="1" styleClass="regCbo">
									<html:option value="-1">Select Value</html:option>
									<html:options property="value" labelProperty="label"
										collection="<%=RegistrationConfig.CASUALTYDESK_OPTION_POLICESTATION%>"/>
								</html:select>
								<!--<html:text name="policeExaminationReqtDtlFB" tabindex="1" property="policeStation" maxlength="100" 
								styleClass="textboxBig" onkeypress="return validateAlphaNumericOnly(event,this)" />
								-->
							</div>
							</td>
							<td class="tdfonthead" nowrap="nowrap" width="25%">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<!--<font color="#FF0000">*</font>-->
									<bean:message key="docketNo" />
								</font>
							</div>
							</td>
							<td class="tdfont" nowrap="nowrap" width="25%">
							<div align="left">
								<html:text name="policeExaminationReqtDtlFB" property="docketNo" maxlength="50" styleClass="textboxBig" onkeypress="return validateAlphaNumericOnly(event,this)" tabindex="1"></html:text>
							</div>
							</td>
						</tr>
						<tr>
							<td class="tdfonthead" nowrap="nowrap" width="25%">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<font color="#FF0000">*</font>
									Letter No
								</font>
							</div>
							</td>
							<td class="tdfont" nowrap="nowrap" width="25%">
							<div align="left">
								<html:text name="policeExaminationReqtDtlFB" property="letterNo" maxlength="50" styleClass="textboxBig" onkeypress="return validateAlphaNumericOnly(event,this)" tabindex="1"></html:text>
							</div>
							</td>
							<td class="tdfonthead" nowrap="nowrap" width="25%">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<font color="#FF0000">*</font>
									<bean:message key="officerincharge" />
									<bean:message key="name" />
								</font>
							</div>
							</td>
							<td class="tdfont" nowrap="nowrap" width="25%">
							<div align="left">
								<html:text name="policeExaminationReqtDtlFB" property="officerIncharge" maxlength="60" styleClass="textboxBig" onkeypress="return validateAlphabetsWithDotsOnly(event,this)" tabindex="1"></html:text>
							</div>
							</td>
						</tr>
						<tr>
							<td class="tdfonthead" nowrap="nowrap" width="25%">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<font color="#FF0000">*</font>
									<bean:message key="officerincharge" />
									<bean:message key="designation" />
								</font>
							</div>
							</td>
							<td class="tdfont" nowrap="nowrap" width="25%">
							<div align="left">
								<html:text name="policeExaminationReqtDtlFB" property="ioDesignation" maxlength="50" styleClass="textboxBig" onkeypress="return validateAlphabetsWithDotsOnly(event,this)" tabindex="1"></html:text>
							</div>
							</td>
							<td class="tdfonthead" nowrap="nowrap" width="25%">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<!--<font color="#FF0000">*</font>-->
									<bean:message key="officerincharge" />
									Batch No
								</font>
							</div>
							</td>
							<td class="tdfont" nowrap="nowrap" width="25%">
							<div align="left">
								<html:text name="policeExaminationReqtDtlFB" property="ioBatchNo" maxlength="50" styleClass="textboxBig" onkeypress="return validateAlphaNumericOnly(event,this)" tabindex="1"></html:text>
							</div>
							</td>
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
											<html:textarea name="policeExaminationReqtDtlFB" tabindex="1" property="caseRemarks" rows="1" cols="90" 
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
				<% String strCancelPrintFlag="0"; %>
				<his:statusTransactionInProcess>
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>'  style="cursor: pointer;" tabindex="1" onclick ="if(validateSave()) submitForm('SAVE')" onkeypress="if(event.keyCode==13)if(validateSave()) submitForm('SAVE')">
					<logic:equal name="policeExaminationReqtDtlFB" property="isDirectCall" value="DIRECT">
						<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style="cursor: pointer;" tabindex="1" onclick =" submitForm('GETPATDTL')" onkeypress="if(event.keyCode==13)submitForm('GETPATDTL')">
					</logic:equal>
					<logic:notEqual name="policeExaminationReqtDtlFB" property="isDirectCall" value="DIRECT">
						<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style="cursor: pointer;" tabindex="1" onclick =" submitToDesk('NEW','NEW')" onkeypress="if(event.keyCode==13) submitToDesk('NEW','NEW')">
					</logic:notEqual>
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>'  style="cursor: pointer;" tabindex="1" onclick =" clearForm()" onkeypress="if(event.keyCode==13)clearForm()">
					<% strCancelPrintFlag="1"; %>
					
					
				</his:statusTransactionInProcess>
				
				<% if(strCancelPrintFlag.equals("0")){ %>
					<his:statusNew>		
						<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style="cursor: pointer;" tabindex="1" onclick =" submitForm('CANCEL')" onkeypress="if(event.keyCode==13)submitForm('CANCEL')">
						
						<logic:equal name="policeExaminationReqtDtlFB" property="isDirectCall" value="DIRECT">
							<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>'  style="cursor: pointer;" tabindex="1" onclick =" submitForm('NEW')" onkeypress="if(event.keyCode==13)submitForm('NEW')">
						</logic:equal>
						<logic:notEqual name="policeExaminationReqtDtlFB" property="isDirectCall" value="DIRECT">
							<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style="cursor: pointer;" tabindex="1" onclick =" submitToDesk('NEW','NEW')" onkeypress="if(event.keyCode==13) submitToDesk('NEW','NEW')">
						</logic:notEqual>
					</his:statusNew>
				<%} %>
				
				<his:statusList>
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style="cursor: pointer;" tabindex="1" onclick =" submitForm('GETCRNO')" onkeypress="if(event.keyCode==13)submitForm('GETCRNO')">
				</his:statusList>	
				<his:statusUnsuccessfull>
					<logic:equal name="policeExaminationReqtDtlFB" property="isDirectCall" value="DIRECT">
						<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style="cursor: pointer;" tabindex="1" onclick =" submitForm('<%=strCancelFuncMethod %>')" onkeypress="if(event.keyCode==13)submitForm('GETCRNO')">
					</logic:equal>
				</his:statusUnsuccessfull>	
				<his:statusRecordFound>
					<logic:equal name="policeExaminationReqtDtlFB" property="isDirectCall" value="DIRECT">
						<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style="cursor: pointer;" tabindex="1" onclick =" submitForm('GETCRNO')" onkeypress="if(event.keyCode==13)submitForm('GETCRNO')">
					</logic:equal>
					<logic:notEqual name="policeExaminationReqtDtlFB" property="isDirectCall" value="DIRECT">
						<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style="cursor: pointer;" tabindex="1" onclick =" submitToDesk('NEW','NEW')" onkeypress="if(event.keyCode==13) submitToDesk('NEW','NEW')">
					</logic:notEqual>
					
				</his:statusRecordFound>
			</his:ButtonToolBarTag>
		</his:TransactionContainer>	
		<html:hidden name="policeExaminationReqtDtlFB" property="patCrNo"/>
		<html:hidden name="policeExaminationReqtDtlFB" property="hmode"/>
		<html:hidden name="policeExaminationReqtDtlFB" property="isDirectCall"/>
		<html:hidden name="policeExaminationReqtDtlFB" property="episodeCode"/>
		<html:hidden name="policeExaminationReqtDtlFB" property="episodeVisitNo"/>
		<html:hidden name="policeExaminationReqtDtlFB" property="patMlcNo"/>
	
	<logic:equal name="policeExaminationReqtDtlFB" property="isDirectCall" value="DIRECT">
	</form>
	<his:status/>
	</logic:equal>
</body>

</html>