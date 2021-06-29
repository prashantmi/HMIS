
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
<his:javascript src="/registration/js/popup.js" />
<his:javascript src="/hisglobal/js/calendar.js"/>

<script type="text/javascript">

function callThisOnload()
{
	if(document.getElementsByName("patCrNo")[0]){
		document.getElementsByName("patCrNo")[0].focus();
	}
	DutyOfficerDetail();
}

function DutyOfficerDetail()
{
	if(document.getElementsByName("dutyOfficeFlag")[1].checked)
	{
		document.getElementById('dutyOfficerDetailId').style.display="block";
	}
	else
	{
		document.getElementById('dutyOfficerDetailId').style.display="none";
	}
}

function validateSave()
{
	var valid=true;
	
	if(validateObjects(document.forms[0].caseNo,"Case Number") &&
		validateObjects(document.forms[0].policeStation,"Police Station") &&
		validateObjects(document.forms[0].docketNo,"Docket No") &&
		validateObjects(document.forms[0].officerIncharge,"Investigating Ofiicer Name") &&
		validateObjects(document.forms[0].ioDesignation,"Investigating Ofiicer Designation") &&
		validateObjects(document.forms[0].ioBatchNo,"Investigating Ofiicer Badge No") &&
		validateDutyDetail()&& 
		validateObjects(document.forms[0].caseRemarks,"Case Remark"))
	{
		valid= true;
	}	
	else
	{
		valid= false;
	}
	return valid;
}


function validateDutyDetail()
{
	valid=true;
	if(document.getElementsByName("dutyOfficeFlag")[1].checked)
	{
		if(validateObjects(document.forms[0].dutyOffName,"Duty Officer Name") &&
			validateObjects(document.forms[0].dutyOffDesignation,"Duty Officer Designation") &&
			validateObjects(document.forms[0].dutyOffBatchNo,"Duty Officer Badge No"))
		{
			valid=true;
		}
		else
		{
			valid=false;
		}
	}
	else
	{
		valid=true;
	}
	return valid;
	
}

function clearForm()
{
	document.getElementsByName("caseNo")[0].value="";
	document.getElementsByName("policeStation")[0].value="";
	document.getElementsByName("docketNo")[0].value="";
	document.getElementsByName("officerIncharge")[0].value="";
	document.getElementsByName("ioDesignation")[0].value="";
	document.getElementsByName("ioBatchNo")[0].value="";
	document.getElementsByName("dutyOfficeFlag")[1].checked=true;
	document.getElementsByName("dutyOffName")[0].value="";
	document.getElementsByName("dutyOffDesignation")[0].value="";
	document.getElementsByName("dutyOffBatchNo")[0].value="";
	document.getElementsByName("caseRemarks")[0].value="";
	DutyOfficerDetail();
}

</script>

<body>
	<html:form action="/policeVerificationDtl">
		<his:TransactionContainer>
			<his:TitleTag name="Police Verification Detail">
			</his:TitleTag>
			
			<his:statusNew>	
				<logic:empty name="PoliceVerificationDtlFB" property="patCrNo" >
					<his:InputCrNoTag name="PoliceVerificationDtlFB"></his:InputCrNoTag>
				</logic:empty>
			</his:statusNew>
			<bean:define id="crNo" name="PoliceVerificationDtlFB" property="patCrNo" type="java.lang.String"/>

<%if(!crNo.trim().equals("")){%>
  <jsp:include page="/registration/patientDetail.cnt" flush="true" />  
  <%} %>
			<his:statusRecordFound>
				
 
  <his:ContentTag>
  <his:SubTitleTag name="Select MLC To Enter Police Verification">
 		</his:SubTitleTag>
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
		<html:radio name="PoliceVerificationDtlFB" property="selectedMlc" value="<%=arrayMLCVo.getMlcNo() %>" onclick="submitForm('GETPOLICEDETAIL')">
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
</his:ContentTag>
				
			</his:statusRecordFound>
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
									<bean:message key="policecaseno" />
								</font>
							</div>
							</td>
			
							<td width="25%" nowrap="nowrap" class="tdfont">
							<div align="left">
								<html:text name="PoliceVerificationDtlFB" tabindex="1" maxlength="50" styleClass="textboxBig" 
								property="caseNo" onkeypress="return validateAlphaNumericOnly(event,this)" />
							</div>
							</td>
			
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
								<html:text name="PoliceVerificationDtlFB" tabindex="1" property="policeStation" maxlength="100" 
								styleClass="textboxBig" onkeypress="return validateAlphaNumericOnly(event,this)" />
							</div>
							</td>
						</tr>
						<tr>
							<td class="tdfonthead" nowrap="nowrap" width="25%">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<font color="#FF0000">*</font>
									<bean:message key="docketNo" />
								</font>
							</div>
							</td>
							<td class="tdfont" nowrap="nowrap" width="25%">
							<div align="left">
								<html:text name="PoliceVerificationDtlFB" property="docketNo" maxlength="50" styleClass="textboxBig" onkeypress="return validateAlphaNumericOnly(event,this)" tabindex="1"></html:text>
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
								<html:text name="PoliceVerificationDtlFB" property="officerIncharge" maxlength="60" styleClass="textboxBig" onkeypress="return validateAlphabetsWithDotsOnly(event,this)" tabindex="1"></html:text>
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
								<html:text name="PoliceVerificationDtlFB" property="ioDesignation" maxlength="50" styleClass="textboxBig" onkeypress="return validateAlphabetsWithDotsOnly(event,this)" tabindex="1"></html:text>
							</div>
							</td>
							<td class="tdfonthead" nowrap="nowrap" width="25%">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<font color="#FF0000">*</font>
									<bean:message key="officerincharge" />
									<bean:message key="batchno" />
								</font>
							</div>
							</td>
							<td class="tdfont" nowrap="nowrap" width="25%">
							<div align="left">
								<html:text name="PoliceVerificationDtlFB" property="ioBatchNo" maxlength="50" styleClass="textboxBig" onkeypress="return validateAlphaNumericOnly(event,this)" tabindex="1"></html:text>
							</div>
							</td>
						</tr>
						
						<tr>
							<td class="tdfonthead" nowrap="nowrap" width="25%">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<bean:message key="dutyOfficer" />
									<bean:message key="detail" />
								</font>
							</div>
							</td>
							<td class="tdfont" nowrap="nowrap" width="25%" colspan="3">
							<div align="left">
								<html:radio name="PoliceVerificationDtlFB" property="dutyOfficeFlag" value="<%=RegistrationConfig.DUTY_OFFICER_IS_IO %>" onclick="DutyOfficerDetail()" tabindex="1"></html:radio>
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<bean:message key="io" />
								</font>		
								<html:radio name="PoliceVerificationDtlFB" property="dutyOfficeFlag" value="<%=RegistrationConfig.DUTY_OFFICER_IS_OTHER %>" onclick="DutyOfficerDetail()" tabindex="1"></html:radio>
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<bean:message key="other" />
								</font>
							</div>
							</td>
						</tr>
					</table>
						
					<div id="dutyOfficerDetailId">
						<table width="100%" cellspacing="1" cellpadding="0">
							<tr>
								<td class="tdfonthead" nowrap="nowrap" width="25%">
								<div align="right">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<font color="#FF0000">*</font>
										<bean:message key="dutyOfficer" />
										<bean:message key="name" />
									</font>
								</div>
								</td>
								<td class="tdfont" nowrap="nowrap" width="25%">
								<div align="left">
									<html:text name="PoliceVerificationDtlFB" property="dutyOffName" maxlength="60" styleClass="textboxBig" onkeypress="return validateAlphabetsWithDotsOnly(event,this)" tabindex="1"></html:text>
								</div>
								</td>
								<td class="tdfonthead" nowrap="nowrap" width="25%">
								<div align="right">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<font color="#FF0000">*</font>
										<bean:message key="dutyOfficer" />
										<bean:message key="designation" />
									</font>
								</div>
								</td>
								<td class="tdfont" nowrap="nowrap" width="25%">
								<div align="left">
									<html:text name="PoliceVerificationDtlFB" property="dutyOffDesignation" maxlength="50" styleClass="textboxBig" onkeypress="return validateAlphabetsWithDotsOnly(event,this)" tabindex="1"></html:text>
								</div>
								</td>
							</tr>
							<tr>
								<td class="tdfonthead" nowrap="nowrap" width="25%">
								<div align="right">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<font color="#FF0000">*</font>
										<bean:message key="dutyOfficer" />
										<bean:message key="batchno" />
									</font>
								</div>
								</td>
								<td class="tdfont" nowrap="nowrap" width="25%" colspan="3">
								<div align="left">
									<html:text name="PoliceVerificationDtlFB" property="dutyOffBatchNo" maxlength="50" styleClass="textboxBig" onkeypress="return validateAlphaNumericOnly(event,this)" tabindex="1"></html:text>
								</div>
								</td>
							
							</tr>
						</table>
					</div>
						
					<table width="100%" cellspacing="1" cellpadding="0">
						<tr>
							<td colspan="4">
								<table width="100%" cellpadding="0" cellspacing="1">
									<tr>
										<td class="tdfonthead" nowrap="nowrap" width="25%">
										<div align="right">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<bean:message key="isbroughtdead" />
											</font>
										</div>
										</td>
						
										<td class="tdfont" nowrap="nowrap" width="75%" >
										<div align="left">
											<html:checkbox name="PoliceVerificationDtlFB" tabindex="1" property="isBroughtDead" value="<%=RegistrationConfig.PATIENT_BROUGHT_DEAD_TRUE%>" tabindex="1"/>
										</div>
										</td>
									</tr>
									<tr>
										<td nowrap="nowrap" class="tdfonthead" width="25%">
										<div align="right">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<font color="#FF0000">*</font>
												<bean:message key="caseremarks" />
											</font>
										</div>
										</td>
						
										<td colspan="2" nowrap="nowrap" width="75%" class="tdfont">
										<div align="left">
											<html:textarea name="PoliceVerificationDtlFB" tabindex="1" property="caseRemarks" rows="1" cols="90" 
												onkeypress="return (validateTextArea(event,this,'500') && validateAlphaNumericOnly(event,this))" />
										</div>
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				</his:ContentTag>
				
			</his:statusTransactionInProcess>	
		
		
			<his:ButtonToolBarTag>
				<his:statusNew>		
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer tabindex="1" onclick =" submitForm('CANCEL')" onkeypress="if(event.keyCode==13)submitForm('CANCEL')">
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>'  style=cursor:pointer tabindex="1" onclick =" submitForm('NEW')" onkeypress="if(event.keyCode==13)submitForm('NEW')">
				</his:statusNew>
				
				<his:statusTransactionInProcess>
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>'  style=cursor:pointer tabindex="1" onclick ="if(validateSave()) submitForm('SAVE')" onkeypress="if(event.keyCode==13)if(validateSave()) submitForm('SAVE')">
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer tabindex="1" onclick =" submitForm('GETPATDTL')" onkeypress="if(event.keyCode==13)submitForm('GETPATDTL')">
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>'  style=cursor:pointer tabindex="1" onclick =" clearForm()" onkeypress="if(event.keyCode==13)clearForm()">
				</his:statusTransactionInProcess>
				
				<his:statusRecordFound>
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer tabindex="1" onclick =" submitForm('NEW')" onkeypress="if(event.keyCode==13)submitForm('NEW')">
				</his:statusRecordFound>	
				<his:statusUnsuccessfull>
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer tabindex="1" onclick =" submitForm('NEW')" onkeypress="if(event.keyCode==13)submitForm('NEW')">
				</his:statusUnsuccessfull>	
			</his:ButtonToolBarTag>
		</his:TransactionContainer>	
		<html:hidden name="PoliceVerificationDtlFB" property="patCrNo"/>
		<html:hidden name="PoliceVerificationDtlFB" property="hmode"/>
		<html:hidden name="PoliceVerificationDtlFB" property="episodeCode"/>
		<html:hidden name="PoliceVerificationDtlFB" property="episodeVisitNo"/>
		<html:hidden name="PoliceVerificationDtlFB" property="patMlcNo"/>
	
	</html:form>
	<his:status/>
</body>

</html>