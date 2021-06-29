<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
	<his:css src="/hisglobal/css/Color.css"/>
	<his:css src="/hisglobal/css/master.css"/>
	<his:css src="/hisglobal/css/hisStyle.css"/>
	<his:css src="/hisglobal/css/hisStyleExt.css"/>    

<his:javascript src="/registration/js/validationCommon.js" />
<his:javascript src="/registration/js/validationCalls.js" />
<his:javascript src="/registration/js/registration.js" />
<his:javascript src="/registration/js/commonFunctions.js" />
<his:javascript src="/registration/js/calendar.js" />
<his:javascript src="/registration/js/dateFunctions.js" />
<his:css src="/css/calendar-blue2.css"/>

<script>
window.history.forward()

function submitTile(mode)
{
	document.getElementsByName("hmode")[0].value=mode;  
//	alert(">>>>>>"+mode)
   	document.forms[0].submit();
}

function validateExtInstMst()
{
	if(document.forms[0].instituteName.value==""){
		alert("Institute Name:::Can not be left blank");
		document.forms[0].instituteName.focus()
		return false;
	}
	if(document.forms[0].instituteShortName.value==""){
		alert("Institute Short name :::Can not be blank");
		document.forms[0].instituteShortName.focus()
		return false;
	}
	if(document.forms[0].instituteType.value=="-1"){
		alert("Institute Type ::::Please select valid value");
		document.forms[0].instituteType.focus()
		return false;
	}
	if(document.forms[0].city.value==""){
		alert("City :::: can not be left blank");
		document.forms[0].city.focus()
		return false;
	}
	if(document.forms[0].stateCode.value=="-1"){
		alert("State::: Please select valid value");
		document.forms[0].stateCode.focus()
		return false;
	}
	if(document.forms[0].isValid.value=="-1"){
		alert("Is Active Status ::::Please select valid value");
		document.forms[0].isValid.focus()
		return false;
	}
	if(!validateEmail()){
		alert("Enter valid email");
		document.forms[0].email.focus()
		return false;
	}
	
	submitTile("MODIFYSAVE");	
	
}

function validateEmail(){
	var valid=true
	var email=document.getElementsByName("email")[0].value;
	var array=email.split("@");
	//alert(email.lastIndexOf("."))
	if(email.length>0){
		if(email.charAt(0)==" "){
			//alert("space")
			valid= false
		}
		if((email.length - email.lastIndexOf("."))>4 || (email.length - email.lastIndexOf("."))==2){
			valid= false
		}
		if(array.length!=2){
			valid=false
		}
		if(email.lastIndexOf(".")- email.indexOf("@")< 2){
			valid=false
		}
	}
	return valid
}


</script>

	<body>
		<html:form action="/master/modifyExternalInstituteMaster">
			<his:TransactionContainer>
				<%@ page import="registration.*"%>
				<%boolean varReadOnly=false; %>
				<logic:equal name="modifyExternalInstituteMasterFB" property="hmode" value="MODIFY">
					<his:TitleTag name="External Institute Master>> Modify">
					</his:TitleTag>
				</logic:equal>
				
				<logic:equal name="modifyExternalInstituteMasterFB" property="hmode" value="VIEW">
					<his:TitleTag name="External Institute Master>> View">
					</his:TitleTag>
					<%varReadOnly=true; %>
				</logic:equal>
				
				<his:ContentTag>
					<logic:equal name="modifyExternalInstituteMasterFB" property="hmode" value="MODIFY">
					<table width="100%" border="0" cellspacing="1" cellpadding="0">
						<tr>
							<td class="tdfonthead" align="left" width="25%">
								<font color="#000000" size="2"face="Verdana, Arial, Helvetica, sans-serif"> 
									<b>
										<bean:message key="updateMode" /> 
									</b> 
								</font>
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
									<bean:message key="correction" /> 
								</font> 
								<html:radio name="modifyExternalInstituteMasterFB" property="choice" tabindex="1" value="<%=RegistrationConfig.CHOICE_MISTAKE%>" onclick="submitTile('CHOICE')" />
						
						 		<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
						 			<bean:message key="modification" /> 
						 		</font> 
						 		<html:radio name="modifyExternalInstituteMasterFB" property="choice" tabindex="1" value="<%=RegistrationConfig.CHOICE_ADDITION%>" onclick="submitTile('CHOICE')" />
						 	</td>
				
						</tr>
					</table>
					</logic:equal>
					<table width="100%" border="0" cellspacing="1" cellpadding="0">
						<tr>
							<td width="50%" class="tdfonthead">
								<div align="right">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<font color="#FF0000">*
	  									</font>
										<b>
											<bean:message key="hospitalType"/>
										</b>
									</font>
								</div>
							</td>
							<td width="50%" class="tdfont">
								<div align="left">
									<bean:message key="notAssociated"/>
									<html:radio name="modifyExternalInstituteMasterFB" property="isAssociated" value="0" disabled="<%=varReadOnly %>" tabindex="1"></html:radio>
									<bean:message key="associated"/>
									<html:radio name="modifyExternalInstituteMasterFB" property="isAssociated" value="1" disabled="<%=varReadOnly %>" tabindex="1"></html:radio>
								
								</div>
						</tr>
						<tr>
							<td width="50%" class="tdfonthead">
								<div align="right">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<font color="#FF0000">*
	  									</font>
										<b>
											<bean:message key="institute"/>
											<bean:message key="name"/>
										</b>
									</font>
								</div>
							</td>
							<td width="50%" class="tdfont">
								<div align="left">
									<html:text name="modifyExternalInstituteMasterFB" property="instituteName" maxlength="100" size="35" readonly="<%=varReadOnly %>" tabindex="1" onkeypress="return validateAlphabetsOnly(event,this)">
									</html:text>
								</div>
							</td>	
						</tr>
						<tr>
							<td width="50%" class="tdfonthead">
								<div align="right">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<font color="#FF0000">*
	  									</font>
										<b>
											<bean:message key="institute"/>
											<bean:message key="short"/>
											<bean:message key="name"/>
										</b>
									</font>
								</div>
							</td>
							<td width="50%" class="tdfont">
								<div align="left">
									<html:text name="modifyExternalInstituteMasterFB" property="instituteShortName" maxlength="10" size="15" readonly="<%=varReadOnly %>" tabindex="1" onkeypress="return validateAlphabetsOnly(event,this)">
									</html:text>
								</div>
							</td>	
						</tr>
						<tr>
							<td width="50%" class="tdfonthead">
								<div align="right">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<font color="#FF0000">*
	  									</font>
										<b>
											<bean:message key="institute"/>
											<bean:message key="type"/>
										</b>
									</font>
								</div>
							</td>
							<td width="50%" class="tdfont">
								<div align="left">
									<html:select name="modifyExternalInstituteMasterFB" tabindex="1" property="instituteType" disabled="<%=varReadOnly %>" >
										<html:option value="-1">Select Value</html:option>
										<html:option value="1">Govt Hospital</html:option>
										<html:option value="2">Dispensary</html:option>
										<html:option value="3">Private Hospital</html:option>
										<html:option value="4">Nursing Home</html:option>
									</html:select>
								</div>
							</td>	
						</tr>
						<tr>
							<td width="50%" class="tdfonthead">
								<div align="right">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<b>
											<bean:message key="address1"/>
										</b>
									</font>
								</div>
							</td>
							<td width="50%" class="tdfont">
								<div align="left">
									<html:textarea name="modifyExternalInstituteMasterFB" property="instituteAddress1" onkeypress="return CheckMaxLength(event,this,40,3);" cols="32" rows="1" readonly="<%=varReadOnly %>" tabindex="1">
									</html:textarea>
								</div>
							</td>
						</tr>
						<tr>
							<td width="50%" class="tdfonthead">
								<div align="right">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<b>
											<bean:message key="address2"/>
										</b>
									</font>
								</div>
							</td>
							<td width="50%" class="tdfont">
								<div align="left">
									<html:textarea name="modifyExternalInstituteMasterFB" property="instituteAddress2" onkeypress="return CheckMaxLength(event,this,40,3)" cols="32" rows="1" readonly="<%=varReadOnly %>" tabindex="1">
									</html:textarea>
								</div>
							</td>
						</tr>
						<tr>
							<td width="50%" class="tdfonthead">
								<div align="right">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<font color="#FF0000">*
	  									</font>
										<b>
											<bean:message key="city"/>
										</b>
									</font>
								</div>
							</td>
							<td width="50%" class="tdfont">
								<div align="left">
									<html:text name="modifyExternalInstituteMasterFB" property="city" maxlength="40" size="20" onkeypress="return validateAlphabetsOnly(event,this)" readonly="<%=varReadOnly %>" tabindex="1">
									</html:text>
								</div>
							</td>
						</tr>
						<tr>
							<td width="50%" class="tdfonthead">
								<div align="right">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<font color="#FF0000">*
	  									</font>
										<b>
											<bean:message key="state"/>
										</b>
									</font>
								</div>
							</td>
							<td width="50%" class="tdfont">
								<div align="left">
									<html:select name="modifyExternalInstituteMasterFB" property="stateCode" disabled="<%=varReadOnly %>" tabindex="1">
										<html:option value="-1">Select Value</html:option>
										<html:options collection="<%=RegistrationConfig.ESSENTIALBO_OPTION_ALL_STATE %>" property="value" labelProperty="label" />
									</html:select>
								</div>
							</td>	
						</tr>
						<tr>
							<td width="50%" class="tdfonthead">
								<div align="right">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<b>
											<bean:message key="contactPerson"/>
										</b>
									</font>
								</div>
							</td>
							<td width="50%" class="tdfont">
								<div align="left">	
									<html:text name="modifyExternalInstituteMasterFB" property="contactPerson" maxlength="50" size="20" readonly="<%=varReadOnly %>" tabindex="1" onkeypress="return validateAlphabetsOnly(event,this)">
									</html:text>
								</div>
							</td>		
						</tr>
						<tr>
							<td width="50%" class="tdfonthead">
								<div align="right">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<b>
											<bean:message key="phone"/>
										</b>
									</font>
								</div>
							</td>
							<td width="50%" class="tdfont">
								<div align="left">	
									<html:text name="modifyExternalInstituteMasterFB" property="phone" onkeypress="return validateNumeric(event)" maxlength="40" size="20" readonly="<%=varReadOnly %>" tabindex="1">
									</html:text>
								</div>
							</td>	
						</tr>
						<tr>
							<td width="50%" class="tdfonthead">
								<div align="right">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<b>
											<bean:message key="fax"/>
										</b>
									</font>
								</div>
							</td>
							<td width="50%" class="tdfont">
								<div align="left">	
									<html:text name="modifyExternalInstituteMasterFB" property="fax" maxlength="20" size="20" onkeypress="return validateNumeric(event)" readonly="<%=varReadOnly %>" tabindex="1">
									</html:text>
								</div>
							</td>	
						</tr>
						<tr>
							<td width="50%" class="tdfonthead">
								<div align="right">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<b>
											<bean:message key="email"/>
										</b>
									</font>
								</div>
							</td>
							<td width="50%" class="tdfont">
								<div align="left">	
									<html:text name="modifyExternalInstituteMasterFB" property="email" maxlength="50" size="30" readonly="<%=varReadOnly %>" tabindex="1" onkeypress="return CheckMaxLength(event,this,50,3)">
									</html:text>
								</div>
							</td>	
						</tr>
						<tr>
							<td width="50%" class="tdfonthead">
								<div align="right">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<font color="#FF0000">*
	  									</font>
										<b>
											<bean:message key="isActive"/>
										</b>
									</font>
								</div>
							</td>
							<td width="50%" class="tdfont">
								<div align="left">
									<html:select name="modifyExternalInstituteMasterFB" tabindex="1" property="isValid" disabled="<%=varReadOnly %>">
										<html:option value="-1">Select Value</html:option>
										<html:option value="1">Active</html:option>
										<html:option value="2">In Active</html:option>
									</html:select>
								</div>
							</td>	
						</tr>
						
						<tr>
							<td width="50%" class="tdfonthead">
								<div align="right">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<b>
											<bean:message key="remarks"/>
										</b>
									</font>
								</div>
							</td>
							<td width="50%" class="tdfont">
								<div align="left">
									<html:textarea name="modifyExternalInstituteMasterFB" property="remarks" onkeypress="return CheckMaxLength(event,this,50,3)" cols="32" rows="1" readonly="<%=varReadOnly %>" tabindex="1">
									</html:textarea>
								</div>
							</td>
						</tr>
					</table>
				</his:ContentTag>
				<his:ButtonToolBarTag>
					<logic:equal name="modifyExternalInstituteMasterFB" property="hmode" value="MODIFY">
						<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>' style=cursor:pointer tabindex="1" onclick="validateExtInstMst() && submitTile('MODIFYSAVE')" onkeypress="if(event.keyCode==13) validateExtInstMst() && submitTile('MODIFYSAVE')">
						<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style=cursor:pointer tabindex="1" onclick="submitTile('CLEAR')" onkeypress="if(event.keyCode==13) submitTile('CLEAR');">
					</logic:equal>
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style=cursor:pointer tabindex="1" onclick="submitTile('CANCEL')" onkeypress="if(event.keyCode==13) submitTile('CANCEL')">
				</his:ButtonToolBarTag>
				
				<html:hidden name="modifyExternalInstituteMasterFB" property="hmode"/>
				<html:hidden name="modifyExternalInstituteMasterFB" property="tempMode"/>
				<html:hidden name="modifyExternalInstituteMasterFB" property="chk"/>
				<html:hidden name="modifyExternalInstituteMasterFB" property="instituteCode"/>
				<html:hidden name="modifyExternalInstituteMasterFB" property="instituteSlNo"/>
			</his:TransactionContainer>
			<his:status/>
		</html:form>
	</body>
</html>