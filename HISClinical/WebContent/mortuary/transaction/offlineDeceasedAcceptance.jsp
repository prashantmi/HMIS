<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>


<%@page import="registration.RegistrationConfig"%>
<%@page import="mortuary.MortuaryConfig"%>
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
<his:javascript src="/registration/js/calendar.js"/>

<his:javascript src="/hisglobal/js/utilityFunctions.js" />
<script type="text/javascript">

window.onload=function()
{
	showHandoverDtl();
}

function submitPage(mode)
{

	
	document.getElementsByName("hmode")[0].value=mode;
	document.forms[0].submit();
}

function getRack()
{
	var chamberId=document.getElementsByName("chamberId")[0].value;
	if(chamberId!="-1")
		submitForm('GETRACK');
}


function validateSave()
{
	var valid=false;
	if(validateStorageRelative()
		
		&& comboValidation(document.forms[0].chamberId,"Chamber")
		&& comboValidation(document.forms[0].chamberRackId,"Rack")
		&& comboValidation(document.forms[0].bodyPutBy,"Body Put By")
		&& validateStorageUpto()
	//	&& validateStorageRelative()
	)
	{
		valid=true;
	}
	else
	{
		valid=false;
	}
	return valid;
	
}

function validateStorageRelative()
{
	var valid=true;

	if(document.getElementsByName("handoverFlag")[1].checked)
	{
		if(isEmpty(document.forms[0].storageRelativeName,"Relative Name")
			&& comboValidation(document.forms[0].storageRelativeCode,"Relationship")
			&& isEmpty(document.forms[0].storageRelativeAddress,"Address of Relative")
			&& isEmpty(document.forms[0].storageRelativeContactNo,"Relative Contact No")
		)
		{
			valid=true;
		}
		else
		{
			valid=false;
		}
	}
	else
		valid=true;
	
	return valid;
}

function validateStorageUpto()
{
	var valid=true;
	var maxUpto="<%=MortuaryConfig.DECEASED_STORAGE_UPTO_HOURS%>"
	
	if(document.getElementsByName("storageUpto")[0].value=="" || document.getElementsByName("storageUpto")[0].value=="0" || document.getElementsByName("storageUpto")[0].value=="00")
	{
		alert("Please Enter The Storage Duration");
		document.getElementsByName("storageUpto")[0].focus();
		valid=false;
	}
	else
	{
		if(parseInt(document.getElementsByName("storageUpto")[0].value)>parseInt(maxUpto))
		{
			alert("Entered Storage Duration Cannot be Greater Than "+ maxUpto + " Hours");
			document.getElementsByName("storageUpto")[0].focus();
			valid=false;
		}
		else
		{
			valid=true;
		}
	}
	
	return valid;
}

function showHandoverDtl()
{
	if(document.getElementsByName("bodyHandoverTo")[0].value=="<%=RegistrationConfig.DEAD_BODY_HANDOVER_TO_RELATIVES %>" )
	{
		if(document.getElementsByName("handoverFlag")[0].checked)
		{
			document.getElementById("divExistingHandoverDetailId").style.display="block";
			document.getElementById("divNewHandoverDetailId").style.display="none";
		}	
		else
		{
			document.getElementById("divExistingHandoverDetailId").style.display="none";
			document.getElementById("divNewHandoverDetailId").style.display="block";
		}
	}	
	if(document.getElementsByName("bodyHandoverTo")[0].value=="<%=RegistrationConfig.DEAD_BODY_HANDOVER_TO_POLICE %>" )
	{
		document.getElementById("divExistingHandoverDetailId").style.display="block";
			document.getElementById("divNewHandoverDetailId").style.display="none";
	}
	
}

function clearForm()
{
	document.getElementsByName("chamberId")[0].value="-1";
	document.getElementsByName("chamberRackId")[0].value="-1";
	document.getElementsByName("bodyPutBy")[0].value="-1";
	document.getElementsByName("storageReason")[0].value="";
	document.getElementsByName("storageUpto")[0].value="<%=MortuaryConfig.DECEASED_STORAGE_UPTO_HOURS%>";
	document.getElementsByName("storageRelativeName")[0].value="";
	document.getElementsByName("storageRelativeAddress")[0].value="";
	document.getElementsByName("storageRelativeContactNo")[0].value="";
	document.getElementsByName("storageRelativeCode")[0].value="-1";
}

</script>

<body>
	<html:form action="/offlineDeceasedAcceptance">
		<his:TransactionContainer>
			<his:TitleTag name="Offline Deceased Acceptance">
			</his:TitleTag>
			
			<his:statusNew>
				<logic:empty name="OfflineDeceasedAcceptanceFB" property="patCrNo" >
					<his:InputCrNoTag name="OfflineDeceasedAcceptanceFB"></his:InputCrNoTag>
				</logic:empty>
			</his:statusNew>
			
			<his:statusUnsuccessfull>
				<jsp:include page="/inpatient/inpatientDetail.cnt" flush="true" />
			</his:statusUnsuccessfull>
			
			<his:statusTransactionInProcess>
				<jsp:include page="/inpatient/inpatientDetail.cnt" flush="true" />
				
				<his:SubTitleTag name="Body Brought By Detail">
					<logic:equal name="OfflineDeceasedAcceptanceFB" property="bodyHandoverTo" value="<%=RegistrationConfig.DEAD_BODY_HANDOVER_TO_RELATIVES %>">
						<table width="100%" border="0"  cellspacing="1" cellpadding="0">
							<tr>
								<td width="100%" >
									<div align="right">
										<font color="#005B88" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<b>
												<bean:message key="exist"/>
											</b>	
										</font>
										<html:radio name="OfflineDeceasedAcceptanceFB" property="handoverFlag" value="<%=MortuaryConfig.DEAD_BODY_HANDOVER_EXISTING%>" onclick="showHandoverDtl()" tabindex="1" ></html:radio>
										<font color="#005B88" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<b>
												<bean:message key="new"/>
											</b>
										</font>	
										<html:radio name="OfflineDeceasedAcceptanceFB" property="handoverFlag" value="<%=MortuaryConfig.DEAD_BODY_HANDOVER_NEW%>" onclick="showHandoverDtl()" tabindex="1"  ></html:radio>
									</div>
								</td>
							</tr>
						</table>
					</logic:equal>
					<logic:equal name="OfflineDeceasedAcceptanceFB" property="bodyHandoverTo" value="<%=RegistrationConfig.DEAD_BODY_HANDOVER_TO_POLICE %>">
						<html:hidden name="OfflineDeceasedAcceptanceFB" property="handoverFlag" value="<%=MortuaryConfig.DEAD_BODY_HANDOVER_EXISTING%>"/> 
						<html:hidden name="OfflineDeceasedAcceptanceFB" property="handoverFlag" value="<%=MortuaryConfig.DEAD_BODY_HANDOVER_NEW%>"/>
					</logic:equal>
				</his:SubTitleTag>
				
				<div id="divExistingHandoverDetailId" >
				
					<his:ContentTag>
						<table width="100%" border="0"  cellspacing="1" cellpadding="0">
							<tr>
								<td width="25%" class="tdfonthead">
									<div align="right">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<bean:message key="deathdate"/>
										</font>	
									</div>
								</td>
								<td width="25%" class="tdfont">
									<div align="left">
										&nbsp;<bean:write name="OfflineDeceasedAcceptanceFB" property="deathDateTime"/>
									</div>
								</td>
								<td width="25%" class="tdfonthead">
									<div align="right">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<bean:message key="handoverdate"/>
										</font>	
									</div>
								</td>
								<td width="25%" class="tdfont">
									<div align="left">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											&nbsp;<bean:write name="OfflineDeceasedAcceptanceFB" property="bodyHandoverDateTime"/>
										</font>
									</div>
								</td>
							</tr>
							<tr>
								<td width="25%" class="tdfonthead">
									<div align="right">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<bean:message key="broughtBy"/>
										</font>	
									</div>
								</td>
								<td width="25%" class="tdfont">
									<div align="left">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<logic:equal name="OfflineDeceasedAcceptanceFB" property="bodyHandoverTo" value="<%=RegistrationConfig.DEAD_BODY_HANDOVER_TO_RELATIVES %>">
												&nbsp;Relatives
											</logic:equal>
											<logic:equal name="OfflineDeceasedAcceptanceFB" property="bodyHandoverTo" value="<%=RegistrationConfig.DEAD_BODY_HANDOVER_TO_POLICE %>">
												&nbsp;Police
											</logic:equal>
										</font>	
									</div>
								</td>
								<td width="25%" class="tdfonthead"></td>
								<td width="25%" class="tdfont"></td>
							</tr>
							<logic:equal name="OfflineDeceasedAcceptanceFB" property="bodyHandoverTo" value="<%=RegistrationConfig.DEAD_BODY_HANDOVER_TO_RELATIVES %>">
								<tr>
									<td width="25%" class="tdfonthead">
										<div align="right">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<bean:message key="relativename"/>
											</font>	
										</div>
									</td>
									<td width="25%" class="tdfont">
										<div align="left">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<html:text name="OfflineDeceasedAcceptanceFB" property="relativeName" readonly="true"></html:text>
											</font>	
										</div>
									</td>
									<td width="25%" class="tdfonthead">
										<div align="right">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<bean:message key="realtionship"/>
											</font>	
										</div>
									</td>
									<td width="25%" class="tdfont">
										<div align="left">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<html:select name="OfflineDeceasedAcceptanceFB" property="relativeCode" disabled="true">
													<html:option value="-1">Select Value</html:option>
													<logic:present name="<%=MortuaryConfig.ESENTIAL_ALL_PATIENT_RELATION%>">
														<html:options collection="<%=MortuaryConfig.ESENTIAL_ALL_PATIENT_RELATION %>" property = "value" labelProperty = "label"/>
													</logic:present>
												</html:select>
											</font>
										</div>
									</td>
								</tr>
								<tr>
									<td width="25%" class="tdfonthead">
										<div align="right">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<bean:message key="relativeaddress"/>
											</font>	
										</div>
									</td>
									<td width="25%" class="tdfont">
										<div align="left">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<html:textarea name="OfflineDeceasedAcceptanceFB" property="relativeAddress" rows="1" cols="30"  readonly="true"></html:textarea>
											</font>	
										</div>
									</td>
									<td width="25%" class="tdfonthead"></td>
									<td width="25%" class="tdfont"></td>
								</tr>
							</logic:equal>
							
							<logic:equal name="OfflineDeceasedAcceptanceFB" property="bodyHandoverTo" value="<%=RegistrationConfig.DEAD_BODY_HANDOVER_TO_POLICE %>">
								<tr>
									<td width="25%" class="tdfonthead">
										<div align="right">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<bean:message key="officer"/>
												<bean:message key="name"/>
											</font>	
										</div>
									</td>
									<td width="25%" class="tdfont">
										<div align="left">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<html:text name="OfflineDeceasedAcceptanceFB" property="officerName" readonly="true"></html:text>
											</font>	
										</div>
									</td>
									<td width="25%" class="tdfonthead">
										<div align="right">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<bean:message key="officer"/>
												<bean:message key="designation"/>
											</font>	
										</div>
									</td>
									<td width="25%" class="tdfont">
										<div align="left">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<html:text name="OfflineDeceasedAcceptanceFB" property="officerDesignation" readonly="true"></html:text>
											</font>
										</div>
									</td>
								</tr>
								<tr>
									<td width="25%" class="tdfonthead">
										<div align="right">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<bean:message key="officer"/>
												<bean:message key="batchno"/>
											</font>	
										</div>
									</td>
									<td width="25%" class="tdfont">
										<div align="left">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<html:text name="OfflineDeceasedAcceptanceFB" property="officerBadgeNo" readonly="true"></html:text>
											</font>	
										</div>
									</td>
									<td width="25%" class="tdfonthead"></td>
									<td width="25%" class="tdfont"></td>
								</tr>
							</logic:equal>		
						</table>				
					</his:ContentTag>
				</div>
				
				<div id="divNewHandoverDetailId" style="display: none;">
					<his:ContentTag>
						<table width="100%" border="0"  cellspacing="1" cellpadding="0">
							<tr>
								<td width="25%" class="tdfonthead">
									<div align="right">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<bean:message key="deathdate"/>
										</font>	
									</div>
								</td>
								<td width="25%" class="tdfont">
									<div align="left">
										&nbsp;<bean:write name="OfflineDeceasedAcceptanceFB" property="deathDateTime"/>
									</div>
								</td>
								<td width="25%" class="tdfonthead">
									<div align="right">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<bean:message key="handoverdate"/>
										</font>	
									</div>
								</td>
								<td width="25%" class="tdfont">
									<div align="left">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											&nbsp;<bean:write name="OfflineDeceasedAcceptanceFB" property="bodyHandoverDateTime"/>
										</font>
									</div>
								</td>
							</tr>
							<tr>
								<td width="25%" class="tdfonthead">
									<div align="right">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<bean:message key="broughtBy"/>
										</font>	
									</div>
								</td>
								<td width="25%" class="tdfont">
									<div align="left">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<logic:equal name="OfflineDeceasedAcceptanceFB" property="bodyHandoverTo" value="<%=RegistrationConfig.DEAD_BODY_HANDOVER_TO_RELATIVES %>">
												&nbsp;Relatives
											</logic:equal>
											<logic:equal name="OfflineDeceasedAcceptanceFB" property="bodyHandoverTo" value="<%=RegistrationConfig.DEAD_BODY_HANDOVER_TO_POLICE %>">
												&nbsp;Police
											</logic:equal>
										</font>	
									</div>
								</td>
								<td width="25%" class="tdfonthead"></td>
								<td width="25%" class="tdfont"></td>
							</tr>
							<logic:equal name="OfflineDeceasedAcceptanceFB" property="bodyHandoverTo" value="<%=RegistrationConfig.DEAD_BODY_HANDOVER_TO_RELATIVES %>">
								<tr>
									<td width="25%" class="tdfonthead">
										<div align="right">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<font color="#FF0000">*</font>
												<bean:message key="relativename"/>
											</font>	
										</div>
									</td>
									<td width="25%" class="tdfont">
										<div align="left">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<html:text name="OfflineDeceasedAcceptanceFB" property="storageRelativeName" maxlength="60" onkeypress="return validateAlphabetsWithDotsOnly(event,this)" tabindex="1"></html:text>
											</font>	
										</div>
									</td>
									<td width="25%" class="tdfonthead">
										<div align="right">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<font color="#FF0000">*</font>
												<bean:message key="realtionship"/>
											</font>	
										</div>
									</td>
									<td width="25%" class="tdfont">
										<div align="left">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<html:select name="OfflineDeceasedAcceptanceFB" property="storageRelativeCode" tabindex="1">
												<html:option value="-1">Select Value</html:option>
												<logic:present name="<%=MortuaryConfig.ESENTIAL_ALL_PATIENT_RELATION%>">
													<html:options collection="<%=MortuaryConfig.ESENTIAL_ALL_PATIENT_RELATION %>" property = "value" labelProperty = "label"/>
												</logic:present>
											</html:select>
											</font>
										</div>
									</td>
								</tr>
								<tr>
									<td width="25%" class="tdfonthead">
										<div align="right">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<font color="#FF0000">*</font>
												<bean:message key="relativeaddress"/>
											</font>	
										</div>
									</td>
									<td width="25%" class="tdfont">
										<div align="left">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<html:textarea name="OfflineDeceasedAcceptanceFB" property="storageRelativeAddress" rows="1" cols="30" onkeypress="return (validateTextArea(event,this,'100') && validateAlphaNumericOnly(event,this))" tabindex="1"></html:textarea>
											</font>	
										</div>
									</td>
									<td width="25%" class="tdfonthead">
										<div align="right">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<font color="#FF0000">*</font>
												<bean:message key="relativeContactNo"/>
											</font>
										</div>
									</td>
									<td width="25%" class="tdfont">
										<div align="left">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<html:text name="OfflineDeceasedAcceptanceFB" property="storageRelativeContactNo" maxlength="30" onkeypress="return validateNumeric(event)" tabindex="1"></html:text>
											</font>	
										</div>
									</td>
								</tr>
							</logic:equal>	
						</table>
					</his:ContentTag>		
				</div>
				
				<his:SubTitleTag name="Storage Detail">
				</his:SubTitleTag>
				<his:ContentTag>
					<table width="100%" border="0"  cellspacing="1" cellpadding="0">
						<tr>
							<td width="25%" class="tdfonthead">
								<div align="right">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<font color="#FF0000">*</font>
										<bean:message key="chamber"/>
									</font>
								</div>
							</td>
							<td width="25%" class="tdfont">
								<div align="left">
									<html:select name="OfflineDeceasedAcceptanceFB" property="chamberId" onchange="getRack()" tabindex="1" >
										<html:option value="-1">Select Value</html:option>
										<logic:present name="<%=MortuaryConfig.ESENTIAL_ALL_CHAMBER %>">
											<html:options collection="<%=MortuaryConfig.ESENTIAL_ALL_CHAMBER %>" property = "value" labelProperty = "label"/>
										</logic:present>
									</html:select>
									
								</div>
							</td>
							<td width="25%" class="tdfonthead">
								<div align="right">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<font color="#FF0000">*</font>
											<bean:message key="rack"/>
										</font>
									</div>
								</td>
								<td width="25%" class="tdfont">
									<div align="left">
										<html:select name="OfflineDeceasedAcceptanceFB" property="chamberRackId" tabindex="1" >
											<html:option value="-1">Select Value</html:option>
											<logic:present name="<%=MortuaryConfig.ESSENTIAL_ALL_CHAMBER_RACK_LIST %>">
												<html:options collection="<%=MortuaryConfig.ESSENTIAL_ALL_CHAMBER_RACK_LIST %>" property = "value" labelProperty = "label"/>
											</logic:present>
										</html:select>
										
									</div>
								</td>
							</tr>
							<tr>
								<td width="25%" class="tdfonthead">
									<div align="right">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<font color="#FF0000">*</font>
												<bean:message key="bodyPutBy"/>
										</font>
									</div>
								</td>
								<td width="25%" class="tdfont">
									<div align="left">
										<html:select name="OfflineDeceasedAcceptanceFB" property="bodyPutBy" tabindex="1" >
											<html:option value="-1">Select Value</html:option>
											<logic:present name="<%=MortuaryConfig.ESSENTIAL_ALL_MORTUARY_EMPLOYEE_LIST %>">
												<html:options collection="<%=MortuaryConfig.ESSENTIAL_ALL_MORTUARY_EMPLOYEE_LIST %>" property = "value" labelProperty = "label"/>
											</logic:present>
										</html:select>
										
									</div>
								</td>
								<td width="25%" class="tdfonthead">
									<div align="right">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<font color="#FF0000">*</font>
											<bean:message key="storageUpto"/>
										</font>
									</div>
								</td>
								<td width="25%" class="tdfont">
									<div align="left">
										<html:text name="OfflineDeceasedAcceptanceFB" property="storageUpto" value="<%=MortuaryConfig.DECEASED_STORAGE_UPTO_HOURS %>" maxlength="2" size="5" onkeypress="return validateNumeric(event)" tabindex="1" ></html:text>
										<bean:message key="hrs"/>
									</div>
								</td>
							</tr>
							<tr>	
								<td width="25%" class="tdfonthead">
									<div align="right">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<bean:message key="reason"/>
										</font>
									</div>
								</td>
								<td width="25%" class="tdfont">
									<div align="left">
										<html:text name="OfflineDeceasedAcceptanceFB" property="storageReason" maxlength="100" onkeypress="return validateAlphabetsWithDotsOnly(event,this)" tabindex="1" ></html:text>
									</div>
								</td>
								<td width="25%" class="tdfonthead">
								</td>
								<td width="25%" class="tdfont">
								</td>
							</tr>
						</table>	
				</his:ContentTag>
				
			</his:statusTransactionInProcess>
			
			
			<his:ButtonToolBarTag>
				<his:statusNew>
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer tabindex="1" onclick =" cancelFunc()" onkeypress="if(event.keyCode==13)cancelFunc()">
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>'  style=cursor:pointer tabindex="1" onclick =" submitForm('NEW')" onkeypress="if(event.keyCode==13)submitForm('NEW')">
				</his:statusNew>
				
				<his:statusUnsuccessfull>
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer tabindex="1" onclick =" submitPage('CANCEL')" onkeypress="if(event.keyCode==13)submitPage('CANCEL')">
				</his:statusUnsuccessfull>
				
				<his:statusTransactionInProcess>
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>'  style=cursor:pointer tabindex="1" onclick ="if(validateSave()) submitPage('SAVE')" onkeypress="if(event.keyCode==13)if(validateSave())submitPage('SAVE')">
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer tabindex="1" onclick =" submitPage('CANCEL')" onkeypress="if(event.keyCode==13)submitPage('CANCEL')">
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>'  style=cursor:pointer tabindex="1" onclick =" clearForm()" onkeypress="if(event.keyCode==13)clearForm()">	
				</his:statusTransactionInProcess>
			</his:ButtonToolBarTag>
			
		</his:TransactionContainer>
		
		<html:hidden name="OfflineDeceasedAcceptanceFB" property="hmode"/>
		<html:hidden name="OfflineDeceasedAcceptanceFB" property="patCrNo"/>
		<html:hidden name="OfflineDeceasedAcceptanceFB" property="deathDateTime"/>
		<html:hidden name="OfflineDeceasedAcceptanceFB" property="bodyHandoverDateTime"/>
		<html:hidden name="OfflineDeceasedAcceptanceFB" property="bodyHandoverTo"/>
		<html:hidden name="OfflineDeceasedAcceptanceFB" property="relativeCode"/>
		
	</html:form>
	<his:status/>
</body>
</html>