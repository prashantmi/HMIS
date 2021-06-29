<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>

<%@page import="mrd.MrdConfig"%>
<%@page import="registration.RegistrationConfig"%>
<html>
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
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<script type="text/javascript">


function getDetail(obj)
{	
	document.getElementsByName("statusCode")[0].value=obj.value.split("@")[1];
	document.getElementsByName("patCrNo")[0].value=obj.value.split("@")[0];	

	if(document.getElementsByName("statusCode")[0].value==0)
		submitForm('GETDTL');
	else
		submitForm('GETHANDOVERDTL')	
}


function showHandoverToDiv(obj)
{
	if(obj.checked==true)
		document.getElementById("divHandoverDetail").style.display="block";
	else
		document.getElementById("divHandoverDetail").style.display="none";
}

function validateSave()
{
	var valid=false;
	if(isEmpty(document.forms[0].registrtionNo,"Registration No")
	&& validateHandover()
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

function validateHandover()
{
	var valid=false;
	if(document.getElementsByName("isHandoverTo")[0].checked)
	{
		if(isEmpty(document.forms[0].relativeName,"Relative Name")
			&& comboValidation(document.forms[0].relativeCode,"Relationship")
			&& isEmpty(document.forms[0].relativeAddress,"Address of Relative")
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

function validateHandoverSave()
{
	var valid=false;
	if(isEmpty(document.forms[0].relativeName,"Relative Name")
		&& comboValidation(document.forms[0].relativeCode,"Relationship")
		&& isEmpty(document.forms[0].relativeAddress,"Address of Relative")
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

</script>

<body>
	<his:TransactionContainer>
		<his:SubTitleTag name="Death Registration Upload">
		<table  width="100%" border="0" cellspacing="1" cellpadding="0" >
			<tr>
				<td>
					<div align="right">
						<img class="button" style="cursor: pointer" alt="Search" title="Search"	src='<his:path src="/hisglobal/images/btn-search.png"/>' onclick="openPopup('/HISClinical/mrd/deathRegistrationUpload.cnt?hmode=SEARCH',event,400,700);">
					</div>
				</td>
			</tr>
		</table>
		</his:SubTitleTag>
		<his:statusList>
		<his:ContentTag>
			<table width="100%" border="0"  cellspacing="1" cellpadding="0">
				<tr>
					<td width="5%" class="addtoolbar" style="border-top:outset black 2px; border-bottom:inset black 2px;">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<bean:message key="select"/>
								</b>	
							</font>
						</div>
					</td>
					<td width="19%" class="addtoolbar" style="border-top:outset black 2px; border-bottom:inset black 2px;">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<bean:message key="crNo"/>
								</b>	
							</font>
						</div>
					</td>
					<td width="19%" class="addtoolbar" style="border-top:outset black 2px; border-bottom:inset black 2px;">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<bean:message key="status"/>
								</b>	
							</font>
						</div>
					</td>
					<td width="19%" class="addtoolbar" style="border-top:outset black 2px; border-bottom:inset black 2px;">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<bean:message key="gender"/>/
									<bean:message key="age"/>
								</b>	
							</font>
						</div>
					</td>
					<td width="19%" class="addtoolbar" style="border-top:outset black 2px; border-bottom:inset black 2px;">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<bean:message key="deathtime"/>
								</b>	
							</font>
						</div>
					</td>
					<td width="19%" class="addtoolbar" style="border-top:outset black 2px; border-bottom:inset black 2px;">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<bean:message key="status"/>
								</b>	
							</font>
						</div>
					</td>
				</tr>
				<logic:iterate id="arrDeathListVO" name="<%=MrdConfig.ARR_DEATH_REGISTRATION_UPLOAD_LIST_VO %>" type="hisglobal.vo.PatientVO" indexId="idx">
					<tr>
						<td width="4%" class="tdfonthead">
							<div align="center">
								<html:radio name="DeathRegistrationUploadFB" property="selectedChild" value='<%=arrDeathListVO.getPatCrNo()+"@"+arrDeathListVO.getPatStatusCode()%>' onclick="getDetail(this)" tabindex="1" ></html:radio>
							</div>
						</td>
						<td width="19%" class="tdfonthead">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<%=arrDeathListVO.getPatCrNo() %>
								</font>
							</div>
						</td>
						<td width="19%" class="tdfonthead">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<%=arrDeathListVO.getPatFirstName() %>
								</font>
							</div>
						</td>
						<td width="19%" class="tdfonthead">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<%=arrDeathListVO.getPatGender() %>/
									<%=arrDeathListVO.getPatAge() %>
								</font>
							</div>
						</td>
						<td width="19%" class="tdfonthead">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<%=arrDeathListVO.getDeathDateTime() %>
								</font>
							</div>
						</td>
						<td width="19%" class="tdfonthead">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<%=arrDeathListVO.getPatStatus() %>
								</font>
							</div>
						</td>
					</tr>
				</logic:iterate>
			</table>
		</his:ContentTag>
		</his:statusList>
		
		<his:statusTransactionInProcess>
			<jsp:include page="/inpatient/inpatientDetail.cnt" flush="true" />
			<%boolean readonly=false; %>
		<logic:equal name="DeathRegistrationUploadFB" property="statusCode" value="0">
			<%readonly=false; %>
		</logic:equal>
		
		<logic:notEqual name="DeathRegistrationUploadFB" property="statusCode" value="0">
			<%readonly=true; %>
		</logic:notEqual>
			<his:ContentTag>
				<table width="100%" border="0"  cellspacing="1" cellpadding="0">
					<tr>
						<td width="25%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<font color="#FF0000">*</font>
									<bean:message key="registration"/>
									<bean:message key="number"/>
								</font>
							</div>
						</td>
						<td width="25%" class="tdfont">
							<div align="left" >
								<html:text name="DeathRegistrationUploadFB" property="registrtionNo" readonly="<%=readonly %>"></html:text>
							</div>
						</td>
						<td width="25%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<bean:message key="remarks"/>
								</font>
							</div>
						</td>
						<td width="25%" class="tdfont">
							<div align="left" >
								<html:textarea name="DeathRegistrationUploadFB" property="remarks" rows="1" cols="33" readonly="<%=readonly %>"></html:textarea>
							</div>
						</td>
					</tr>
				</table>
			</his:ContentTag>
			<logic:equal name="DeathRegistrationUploadFB" property="statusCode" value="0">
				<his:SubTitleTag name="">
					<div align="left">
						<html:checkbox name="DeathRegistrationUploadFB" tabindex="1" value="<%=RegistrationConfig.IS_HANDOVER_TRUE%>" property="isHandoverTo" onclick="showHandoverToDiv(this)" />
						Handover To Detail
					</div>
				</his:SubTitleTag>
				<div id="divHandoverDetail" style="display: none;">
					<his:ContentTag>
						<table width="100%" border="0"  cellspacing="1" cellpadding="0">
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
										<html:text name="DeathRegistrationUploadFB" property="relativeName" maxlength="60" onkeypress="return validateAlphabetsWithDotsOnly(event)"></html:text>
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
										<html:select name="DeathRegistrationUploadFB" property="relativeCode">
											<html:option value="-1">Select Value</html:option>
											<logic:present name="<%=MrdConfig.ESSENTIAL_ALL_RELATION_LIST%>">
												<html:options collection="<%=MrdConfig.ESSENTIAL_ALL_RELATION_LIST%>" property="value" labelProperty="label" />
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
											<bean:message key="relativeaddress"/>
										</font>
									</div>
								</td>
								<td width="25%" class="tdfont">
									<div align="left">
										<html:textarea name="DeathRegistrationUploadFB" property="relativeAddress" rows="1" cols="30" onkeypress="return (validateTextArea(event,this,'100') && validateAlphaNumericOnly(event,this))"></html:textarea>
									</div>
								</td>
								<td width="25%" class="tdfonthead"></td>
								<td width="25%" class="tdfont"></td>
							</tr>
						</table>
					</his:ContentTag>	
				</div>	
			</logic:equal>	
			
			<logic:notEqual name="DeathRegistrationUploadFB" property="statusCode" value="0">
				<his:SubTitleTag name="Handover To Detail">
				</his:SubTitleTag>
				<his:ContentTag>
					<table width="100%" border="0"  cellspacing="1" cellpadding="0">
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
									<html:text name="DeathRegistrationUploadFB" property="relativeName" maxlength="60" onkeypress="return validateAlphabetsWithDotsOnly(event)"></html:text>
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
									<html:select name="DeathRegistrationUploadFB" property="relativeCode">
										<html:option value="-1">Select Value</html:option>
										<logic:present name="<%=MrdConfig.ESSENTIAL_ALL_RELATION_LIST%>">
											<html:options collection="<%=MrdConfig.ESSENTIAL_ALL_RELATION_LIST%>" property="value" labelProperty="label" />
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
										<bean:message key="relativeaddress"/>
									</font>
								</div>
							</td>
							<td width="25%" class="tdfont">
								<div align="left">
									<html:textarea name="DeathRegistrationUploadFB" property="relativeAddress" rows="1" cols="30" onkeypress="return (validateTextArea(event,this,'100') && validateAlphaNumericOnly(event,this))"></html:textarea>
								</div>
							</td>
							<td width="25%" class="tdfonthead"></td>
							<td width="25%" class="tdfont"></td>
						</tr>
					</table>
				</his:ContentTag>	
			</logic:notEqual>
		</his:statusTransactionInProcess>
		
		
		<his:ButtonToolBarTag>
			<his:statusList>
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer tabindex="1" onclick =" submitPage('CANCEL')" onkeypress="if(event.keyCode==13)submitPage('CANCEL')">
			</his:statusList>
			
			<his:statusUnsuccessfull>
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer tabindex="1" onclick =" submitPage('CANCEL')" onkeypress="if(event.keyCode==13)submitPage('CANCEL')">
			</his:statusUnsuccessfull>
			
			<his:statusTransactionInProcess>
				<logic:equal name="DeathRegistrationUploadFB" property="statusCode" value="0">
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>'  style=cursor:pointer tabindex="1" onclick ="if(validateSave()) submitForm('SAVE')" onkeypress="if(event.keyCode==13)if(validateSave())submitForm('SAVE')">
				</logic:equal>
				<logic:equal name="DeathRegistrationUploadFB" property="statusCode" value="1">
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>'  style=cursor:pointer tabindex="1" onclick ="if(validateHandoverSave()) submitForm('HANDOVERSAVE')" onkeypress="if(event.keyCode==13)if(validateHandoverSave())submitForm('HANDOVERSAVE')">
				</logic:equal>	
				<logic:equal name="DeathRegistrationUploadFB" property="statusCode" value="2">
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>'  style=cursor:pointer tabindex="1" onclick ="if(validateHandoverSave()) submitForm('DUPLICATEHANDOVERSAVE')" onkeypress="if(event.keyCode==13)if(validateHandoverSave())submitForm('DUPLICATEHANDOVERSAVE')">
				</logic:equal>	
				<logic:equal name="DeathRegistrationUploadFB" property="statusCode" value="3">
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>'  style=cursor:pointer tabindex="1" onclick ="if(validateHandoverSave()) submitForm('DUPLICATEHANDOVERSAVE')" onkeypress="if(event.keyCode==13)if(validateHandoverSave())submitForm('DUPLICATEHANDOVERSAVE')">
				</logic:equal>	
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer tabindex="1" onclick =" submitForm('CANCEL')" onkeypress="if(event.keyCode==13)submitForm('CANCEL')">
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>'  style=cursor:pointer tabindex="1" onclick =" clearForm()" onkeypress="if(event.keyCode==13)clearForm()">
			</his:statusTransactionInProcess>
		</his:ButtonToolBarTag>
		
		
		<html:hidden name="DeathRegistrationUploadFB" property="hmode" value="unspecified"/>
		<html:hidden name="DeathRegistrationUploadFB" property="statusCode"/>
		<html:hidden name="DeathRegistrationUploadFB" property="isPrint"/>
		<html:hidden name="DeathRegistrationUploadFB" property="patCrNo"/>
		<html:hidden name="DeathRegistrationUploadFB" property="isFromSearch"/>
		
	</his:TransactionContainer>
</body>	

</html>