<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>

<%@page import="mortuary.MortuaryConfig"%>
<%@page import="hisglobal.vo.DeceasedIdentityDtlVO"%>
<%@page import="registration.RegistrationConfig"%>
<%@page import="hisglobal.presentation.WebUTIL"%>
<%@page import="java.util.Date"%>
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
<his:javascript src="/registration/js/calendar.js"/>

<his:javascript src="/hisglobal/js/utilityFunctions.js" />
<his:javascript src="/hisglobal/utility/generictemplate/js/validationFunctions.js"/>
<his:javascript src="/hisglobal/utility/generictemplate/js/date_validator.js"/>
<his:javascript src="/hisglobal/js/utilityFunctions.js" />
<script type="text/javascript">

window.onload=function()
{
	ageSelection();
	enableSpouseField();
}

function ageSelection()
{
	if(document.getElementsByName("isActualDob")[0].checked)
	{
		 document.getElementById("divAge").style.display="";
		 document.getElementById("divDob").style.display="none";
	 	// document.getElementsByName("patDOB")[0].value="";
	}
	else
	{
		//  document.getElementsByName("patAge")[0].value="";
		  document.getElementsByName("patAgeUnit")[0].selectedIndex=0;
		  document.getElementById("divAge").style.display="none";
		  document.getElementById("divDob").style.display="block";
	} 
}
function enableSpouseField()
{
	var maritalStatus=document.getElementsByName("patMaritalStatusCode")[0].value;
	var maritalStatusSingle="<%=RegistrationConfig.REGISTRATIONDESK_MARITAL_STATUS_SINGLE%>";
	
	if( (maritalStatus=="-1") || (maritalStatus==maritalStatusSingle))
	{
		document.getElementsByName("patHusbandName")[0].readOnly=true;
		document.getElementsByName("patHusbandName")[0].value="";
	}
	else
	{
		document.getElementsByName("patHusbandName")[0].readOnly=false;
		
	}
}

function submitPage(mode)
{
	document.getElementsByName("hmode")[0].value=mode;
	document.forms[0].submit();
}

function showIdentifyByDiv()
{
	if(document.getElementsByName("isIdentifiyBy")[0].value=="-1")
	{
		document.getElementById("divRlativeId").style.display="none";
		document.getElementById("divPoliceId").style.display="none";
	}
	if(document.getElementsByName("isIdentifiyBy")[0].value=="<%=MortuaryConfig.BODY_IDENTIFIED_BY_POLICE%>")
	{
		document.getElementById("divRlativeId").style.display="none";
		document.getElementById("divPoliceId").style.display="block";
	}
	if(document.getElementsByName("isIdentifiyBy")[0].value=="<%=MortuaryConfig.BODY_IDENTIFIED_BY_RELATIVE%>")
	{
		document.getElementById("divRlativeId").style.display="block";
		document.getElementById("divPoliceId").style.display="none";
	}
}
function getDeceasedDetail(obj)
{
	document.getElementsByName("deceasedNo")[0].value=obj.value;;
	submitForm('DECEASEDDTL')
}


function validateSave()
{
	var valid=false;
	if(validateUnknownDetail()
		&& comboValidation(document.forms[0].isIdentifiyBy,"Identify By")
		&& validatePolice()
		&& validateRelative()
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

function clearForm()
{
	document.getElementsByName("isIdentifiyBy")[0].value="-1";
	document.getElementsByName("identifiyByName")[0].value="";
	document.getElementsByName("identifiyByAddress")[0].value="";
	document.getElementsByName("identifiyByPhone")[0].value="";
	document.getElementsByName("relativeCode")[0].value="-1";
	document.getElementsByName("officerName")[0].value="";
	document.getElementsByName("officerDesignation")[0].value="";
	document.getElementsByName("officerBadgeNo")[0].value="";
	document.getElementsByName("identityRemarks")[0].value="";
	showIdentifyByDiv();
}

function validateClaimedSave()
{
	var valid=false;
	if(comboValidation(document.forms[0].isIdentifiyBy,"Claimed By")
		&& validatePolice()
		&& validateRelative()
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

function validatePolice()
{
	var valid=false;
	if(document.getElementsByName("isIdentifiyBy")[0].value=="<%=MortuaryConfig.BODY_IDENTIFIED_BY_POLICE%>")
	{
		if(isEmpty(document.forms[0].officerName,"Officer Name")
			&& isEmpty(document.forms[0].officerDesignation,"Officer Designation")
			&& isEmpty(document.forms[0].officerBadgeNo,"Officer Badge No")
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

function validateRelative()
{
	var valid=false;
	if(document.getElementsByName("isIdentifiyBy")[0].value=="<%=MortuaryConfig.BODY_IDENTIFIED_BY_RELATIVE%>")
	{
	
		if(isEmpty(document.forms[0].identifiyByName,"Relative Name")
			&& comboValidation(document.forms[0].relativeCode,"Relationship")
			&& isEmpty(document.forms[0].identifiyByAddress,"Address of Relative")
			&& isEmpty(document.forms[0].identifiyByPhone,"Relative Contact No")
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

function validateUnknownDetail()
{
	var valid=false;
	if(validateFirstName()
	//isEmpty(document.forms[0].patFirstName,"Deceased First Name")
		&& validateAgeOrDob()
		&& validatePatientDOBAgainstSysDate()
		&& comboValidation(document.forms[0].patGenderCode,"Gender")
	)
	{
		if(document.forms[0].patDOB.value!="")
		{
			var dateOfBirth = convertStrToDate(document.forms[0].patDOB.value,"dd-MM-yyyy");
			document.forms[0].patDOB.maxLength = 11;
			document.forms[0].patDOB.value = convertDateToStr(dateOfBirth,"dd-Mon-yyyy");
		}
		valid=true;
	}
	else
	{
		valid=false;
	}
	
	return valid;
}

function validateFirstName()
{
	var valid=false;
	if(document.getElementsByName("patFirstName")[0].value=="")
	{
		alert("Please Enter Deceased First Name");
		document.getElementsByName("patFirstName")[0].focus();
		valid=false;
	}
	else
	{
		if(document.getElementsByName("patFirstName")[0].value=="Unknown")
		{
			alert("Please Enter Deceased First Name");
			document.getElementsByName("patFirstName")[0].focus();
			document.getElementsByName("patFirstName")[0].value="";
			valid=false;
		}
		else
			valid=true;
	}
	
	return valid;
}
function validateAgeOrDob()
{
	var valid=true;
	if(document.getElementsByName("isActualDob")[0].checked)
	{
		valid=isEmpty(document.forms[0].patAge,"Age"); 
	}
	else
	{
		valid=isEmpty(document.forms[0].patDOB,"Date of Birth");
		if(!DateValidator.validate(document.forms[0].patDOB,"Date of Birth"))
			valid = false;
	}
	
	return valid;	
}
function clearUnknownDetail()
{
	document.getElementsByName("patFirstName")[0].value="";
	document.getElementsByName("patMiddleName")[0].value="";
	document.getElementsByName("patLastName")[0].value="";
//	document.getElementsByName("patDOB")[0].value="";
	document.getElementsByName("patGenderCode")[0].value="-1";
//	document.getElementsByName("patAge")[0].value="";
//	document.getElementsByName("patAgeUnit")[0].value="Y";
	document.getElementsByName("patMotherName")[0].value="";
	document.getElementsByName("patHusbandName")[0].value="";
	document.getElementsByName("patGuardianName")[0].value="";
	document.getElementsByName("patMaritalStatusCode")[0].value="-1";
	document.getElementsByName("add1")[0].value="";
	document.getElementsByName("add2")[0].value="";
	document.getElementsByName("contactNo")[0].value="";
	document.getElementsByName("isActualDob")[0].checked=true;
	enableSpouseField();
	ageSelection();
	clearForm();
}


function validatePatientDOBAgainstSysDate()
{
	if(document.getElementsByName('isActualDob')[1].checked)
	{
		//DateValidator.prototype.validateAgainstDateOnMode = function(_givenDate, _givenFormat, _mode, _dateName, _givenDateName)
		var ctrlObjPatDOB = document.forms[0].patDOB.Control_Object;
	//	alert(ctrlObjPatDOB);
		var elemCurrentDate = document.getElementsByName('sysDate')[0];		
		var flag = ctrlObjPatDOB.validateAgainstDateOnMode(elemCurrentDate.value,"dd-Mon-yyyy",DateValidator.COMPARE_MODES["LessNEqual"],"Date of Birth","Current Date");
	//	alert(flag);
		if(flag==true)
		{
			var dateOfBirth = convertStrToDate(document.forms[0].patDOB.value,"dd-MM-yyyy");
			var currentDate = convertStrToDate(elemCurrentDate.value,"dd-Mon-yyyy");
		//	alert(dateOfBirth);
			//alert(currentDate);
			
			// utilityFunctions.js :: function dateDifference(_bigDt,_smallDt,_format{"Y","M","D","H","MI","S"})
			var ageInYears = dateDifference(currentDate,dateOfBirth,"Y");
			//alert("Age : " + ageInYears +" Years");
			if(parseInt(ageInYears)>125)
			{
				alert("Age ("+ageInYears+" Years) cannot be greater than 125 Years");
				flag=false;
			}
		}		
		return flag;
	}
	else
		return true;
}
</script>


<% 
	String strdivage="\"\"";	
	String strdivdob="\"\""; 
%>

<body>
	<html:form action="/unknownBodyIdenttification">
		<his:TransactionContainer>
			<his:TitleTag name="Unknown/Unclaimed Body Identification">
			</his:TitleTag>
			<his:statusList>
				<his:ContentTag>
					<table width="100%" border="0"  cellspacing="1" cellpadding="0">
						<tr>
							<td width="5%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<b>
											<bean:message key="select"/>
										</b>	
									</font>
								</div>
							</td>
							<td width="15%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<b>
											<bean:message key="deceasedNo"/>
										</b>	
									</font>
								</div>
							</td>
							<td width="30%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<b>
											<bean:message key="name"/>
										</b>	
									</font>
								</div>
							</td>
							<td width="25%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<b>
											<bean:message key="deathdate"/>
										</b>	
									</font>
								</div>
							</td>
							<td width="25%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<b>
											<bean:message key="receiveDate"/>
										</b>	
									</font>
								</div>
							</td>
							
						</tr>
						<logic:iterate id="arrDeceasedListVO" name="<%=MortuaryConfig.UNKNOWN_N_UNCLAIMED_BODY_LIST %>" type="hisglobal.vo.DeceasedDetailVO" indexId="idx">
							<tr>
								<td width="5%" class="tdfont">
									<div align="center">
										<html:radio name="UnknownBodyIdentificationFB" property="selectedDeceased" value="<%=arrDeceasedListVO.getDeceasedNo()%>" onclick="getDeceasedDetail(this)" tabindex="1" ></html:radio>
									
									</div>
								</td>
								<td width="15%" class="tdfont">
									<div align="center">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<%=arrDeceasedListVO.getDeceasedNo() %>
										</font>
									</div>
								</td>
								<td width="30%" class="tdfont">
									<div align="center">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<%=arrDeceasedListVO.getPatFirstName()==null?"":arrDeceasedListVO.getPatFirstName() %>
											<%=arrDeceasedListVO.getPatMiddleName()==null?"":arrDeceasedListVO.getPatMiddleName() %>
											<%=arrDeceasedListVO.getPatLastName()==null?"":arrDeceasedListVO.getPatLastName() %>
										</font>	
									</div>
								</td>
								<td width="25%" class="tdfont">
									<div align="center">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<%=arrDeceasedListVO.getDeathDate() %>
										</font>	
									</div>
								</td>
								
								<td width="25%" class="tdfont">
									<div align="center">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<%=arrDeceasedListVO.getReceivedDateTime() %>
										</font>	
									</div>
								</td>
							</tr>
						</logic:iterate>
					</table>		
				</his:ContentTag>
			</his:statusList>
			
			<his:statusTransactionInProcess>
			
				<jsp:include page="/mortuary/deceasedTile.cnt" flush="true" />
					
				<logic:equal name="UnknownBodyIdentificationFB" property="isClaimedFlag" value="<%=MortuaryConfig.NO %>">		
					<his:SubTitleTag name="Deceased Detail">
					</his:SubTitleTag>
					<his:ContentTag>
						<table width="100%" border="0"  cellspacing="1" cellpadding="0">
							<tr>
								<td width="25%" class="tdfonthead">
								</td>
								<td width="25%" class="tdfonthead">
									<div align="left">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<bean:message key="firstName"/>
										</font>
									</div>
								</td>
								<td width="25%" class="tdfonthead">
									<div align="left">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<bean:message key="middleName"/>
										</font>
									</div>
								</td>
								<td width="25%" class="tdfonthead">
									<div align="left">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<bean:message key="lastName"/>
										</font>
									</div>
								</td>
							</tr>
							<tr>
								<td width="25%" class="tdfonthead">
									<div align="right">
										<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font>
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<bean:message key="deceased"/>
											<bean:message key="name"/>
										</font>
									</div>
								</td>
								<td width="25%" class="tdfont">
									<div align="left">
										<html:text name="UnknownBodyIdentificationFB" property="patFirstName" maxlength="30" onkeypress="return validateAlphaNumOnly(this,event)" tabindex="1" ></html:text>
									</div>
								</td>
								<td width="25%" class="tdfont">
									<div align="left">
										<html:text name="UnknownBodyIdentificationFB" property="patMiddleName" maxlength="30" onkeypress="return validateAlphaNumOnly(this,event)" tabindex="1" ></html:text>
									</div>
								</td>
								<td width="25%" class="tdfont">
									<div align="left">
										<html:text name="UnknownBodyIdentificationFB" property="patLastName" maxlength="30" onkeypress="return validateAlphaNumOnly(this,event)" tabindex="1" ></html:text>
									</div>
								</td>
							</tr>
							<tr>
								<td width="25%" class="tdfonthead">
									<div align="center">
										<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font> 
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<bean:message key="age" />
										</font>
										<html:radio name="UnknownBodyIdentificationFB" property="isActualDob" tabindex="1" value="0" onclick="ageSelection()" />
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<bean:message key="dob" />
										</font>
										<html:radio name="UnknownBodyIdentificationFB" property="isActualDob" tabindex="1" value="1" onclick="ageSelection()" />
									</div>
								</td>
								<logic:equal name="UnknownBodyIdentificationFB" property="isActualDob" value="1">
									<%
										strdivage = "none";
										strdivdob = "";
									%>
								</logic:equal>
								<logic:equal name="UnknownBodyIdentificationFB" property="isActualDob"
									value="0">
									<%
										strdivage = "";
										strdivdob = "none";
									%>
								</logic:equal>
								<td width="25%" class="tdfont">
									<div id="divAge" style='display:<%=strdivage%>'>
										<html:text name="UnknownBodyIdentificationFB" size="4" property="patAge" tabindex="1" maxlength="3" onkeypress="return validateNumeric(event)" /> 
										<html:select name="UnknownBodyIdentificationFB" property="patAgeUnit" tabindex="1" styleClass="smallcombo">
											<logic:present name="<%=RegistrationConfig.ESSENTIALBO_OPTION_AGE_TYPE %>">
												<html:options collection="<%=RegistrationConfig.ESSENTIALBO_OPTION_AGE_TYPE %>" property="value" labelProperty="label" />
											</logic:present>	
										</html:select>
									</div>
									<div id="divDob" style='display:<%=strdivdob%>'>
										<bean:define name="UnknownBodyIdentificationFB" property="patDOB" id="dob" type="java.lang.String" />
										<div id="divPatDOB"></div>
										<script type="text/javascript">DateValidator.setup("divPatDOB","patDOB","<%=dob%>","dd-Mon-yyyy","textbox");</script>
									</div>
								</td>
								<td width="25%" class="tdfonthead" >
									<div align="right">
										<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font>
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<bean:message key="gender" />
										</font>
									</div>
								</td>
								<td width="25%" class="tdfont" >
									<div align="left">
										<html:select name="UnknownBodyIdentificationFB" property="patGenderCode" tabindex="1" styleClass="regcbo">
											<html:option value="-1">Select Value</html:option>
											<logic:present name="<%=MortuaryConfig.ESS_ALL_GENDER_LIST %>">
												<html:options collection="<%=MortuaryConfig.ESS_ALL_GENDER_LIST %>" property="value" labelProperty="label" />
											</logic:present>	
										</html:select>
									</div>	
								</td>
							</tr>
							<tr>
								<td width="25%" class="tdfonthead" >
									<div align="right">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<bean:message key="fathersName" />
										</font>
									</div>
								</td>
								<td width="25%" class="tdfont" >
									<div align="left">
										<html:text name="UnknownBodyIdentificationFB" property="patGuardianName" maxlength="60" onkeypress="return validateAlphaNumOnly(this,event)" tabindex="1" ></html:text>
									</div>
								</td>
								<td width="25%" class="tdfonthead" >
									<div align="right">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<bean:message key="motherName" />
										</font>
									</div>
								</td>
								<td width="25%" class="tdfont" >
									<div align="left">
										<html:text name="UnknownBodyIdentificationFB" property="patMotherName" maxlength="60" onkeypress="return validateAlphaNumOnly(this,event)" tabindex="1" ></html:text>
									</div>
								</td>
							</tr>
							<tr>
								<td width="25%" class="tdfonthead" >
									<div align="right">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<bean:message key="maritalStatus" />
										</font>
									</div>
								</td>
								<td width="25%" class="tdfont" >
									<div align="left">
										<html:select name="UnknownBodyIdentificationFB" property="patMaritalStatusCode" tabindex="1" styleClass="regcbo" onchange="enableSpouseField()">
											<html:option value="-1">Select Value</html:option>
											<logic:present name="<%=MortuaryConfig.ESS_ALL_MARITIAL_STATUS_LIST %>">
												<html:options collection="<%=MortuaryConfig.ESS_ALL_MARITIAL_STATUS_LIST %>" property="value" labelProperty="label" />
											</logic:present>	
										</html:select>
									</div>
								</td>
								<td width="25%" class="tdfonthead" >
									<div align="right">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<bean:message key="husbandName" />
										</font>
									</div>
								</td>
								<td width="25%" class="tdfont" >
									<div align="left">
										<html:text name="UnknownBodyIdentificationFB" property="patHusbandName" maxlength="60" onkeypress="return validateAlphaNumOnly(this,event)" readonly="true" tabindex="1" ></html:text>
									</div>
								</td>
							</tr>
							<tr>
								<td width="25%" class="tdfonthead" >
									<div align="right">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<bean:message key="address1" />
										</font>
									</div>
								</td>
								<td width="25%" class="tdfont" colspan="3">
									<div align="left">
										<html:text name="UnknownBodyIdentificationFB" property="add1" size="94" maxlength="100" onkeypress="return validateAlphabetsWithDotsOnly(event)" tabindex="1" ></html:text>
									</div>
								</td>
							</tr>
							<tr>	
								<td width="25%" class="tdfonthead" >
									<div align="right">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<bean:message key="address2" />
										</font>
									</div>
								</td>
								<td width="25%" class="tdfont" colspan="3">
									<div align="left">
										<html:text name="UnknownBodyIdentificationFB" property="add2" size="94" maxlength="100" onkeypress="return validateAlphabetsWithDotsOnly(event)" tabindex="1" ></html:text>
									</div>
								</td>
							</tr>
							<tr>
								<td width="25%" class="tdfonthead" >
									<div align="right">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<bean:message key="contactNo" />
										</font>
									</div>
								</td>
								<td width="25%" class="tdfont" >
									<div align="left">
										<html:text name="UnknownBodyIdentificationFB" property="contactNo" maxlength="30" onkeypress="return validateNumeric(event)" tabindex="1" ></html:text>
									</div>
								</td>
								<td width="25%" class="tdfonthead" ></td>
								<td width="25%" class="tdfont" ></td>
							</tr>	
						</table>	
					</his:ContentTag>
				</logic:equal>	
				
				<logic:equal name="UnknownBodyIdentificationFB" property="isClaimedFlag" value="<%=MortuaryConfig.NO %>">		
					<his:SubTitleTag name="Body Identification">
					</his:SubTitleTag>
				</logic:equal>
				<logic:equal name="UnknownBodyIdentificationFB" property="isClaimedFlag" value="<%=MortuaryConfig.YES %>">
					<his:SubTitleTag name="Deceased Claimant Detail">
					</his:SubTitleTag>	
				</logic:equal>	
					
				<his:ContentTag>
					<logic:equal name="UnknownBodyIdentificationFB" property="isClaimedFlag" value="<%=MortuaryConfig.NO %>">
						<table width="100%" border="0"  cellspacing="1" cellpadding="0">
							<tr>
								<td width="25%" class="tdfonthead">
									<div align="right">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<font color="#FF0000">*</font>
											<bean:message key="identifyBy"/>
										</font>
									</div>
								</td>
								<td width="25%" class="tdfont">
									<div align="left">
										<html:select name="UnknownBodyIdentificationFB" property="isIdentifiyBy" onchange="showIdentifyByDiv()"  tabindex="1">
											<html:option value="-1">Select value</html:option>
											<html:option value="<%=MortuaryConfig.BODY_IDENTIFIED_BY_RELATIVE %>">Relative</html:option>
											<html:option value="<%=MortuaryConfig.BODY_IDENTIFIED_BY_POLICE %>">Police</html:option>
										</html:select>
									</div>
								</td>
								<td width="25%" class="tdfonthead">
									<div align="right">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<bean:message key="isClaimed"/>
										</font>
									</div>
								</td>
								<td width="25%" class="tdfont">
									<div align="left">
										<html:checkbox name="UnknownBodyIdentificationFB" property="isClaimed" tabindex="1" onclick="relativeDetails()" ></html:checkbox>
									</div>
								</td>
							</tr>
						</table>
					</logic:equal>			
					
					<logic:equal name="UnknownBodyIdentificationFB" property="isClaimedFlag" value="<%=MortuaryConfig.YES %>">
						<table width="100%" border="0"  cellspacing="1" cellpadding="0">
							<tr>
								<td width="25%" class="tdfonthead">
									<div align="right">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<font color="#FF0000">*</font>
											<bean:message key="claimedBy"/>
										</font>
									</div>
								</td>
								<td width="25%" class="tdfont">
									<div align="left">
										<html:select name="UnknownBodyIdentificationFB" property="isIdentifiyBy" onchange="showIdentifyByDiv()"  tabindex="1">
											<html:option value="-1">Select value</html:option>
											<html:option value="<%=MortuaryConfig.BODY_IDENTIFIED_BY_RELATIVE %>">Relative</html:option>
											<html:option value="<%=MortuaryConfig.BODY_IDENTIFIED_BY_POLICE %>">Police</html:option>
										</html:select>
									</div>
								</td>
								<td width="25%" class="tdfonthead">
								</td>
								<td width="25%" class="tdfont">
								</td>
							</tr>
						</table>
					</logic:equal>
					
					<div id="divRlativeId" style="display: none;">
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
										<html:text name="UnknownBodyIdentificationFB" property="identifiyByName" maxlength="60"  tabindex="1" onkeypress="return validateAlphabetsWithDotsOnly(event,this)" ></html:text>
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
										<html:select name="UnknownBodyIdentificationFB" property="relativeCode" tabindex="1" >
											<html:option value="-1">Select Value</html:option>
											<logic:present name="<%=MortuaryConfig.ESENTIAL_ALL_PATIENT_RELATION%>">
												<html:options collection="<%=MortuaryConfig.ESENTIAL_ALL_PATIENT_RELATION %>" property = "value" labelProperty = "label"/>
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
										<html:textarea name="UnknownBodyIdentificationFB" property="identifiyByAddress" rows="1" cols="30"  tabindex="1" onkeypress="return (validateTextArea(event,this,'100') && validateAlphaNumericOnly(event,this))" ></html:textarea>
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
										<html:text name="UnknownBodyIdentificationFB" property="identifiyByPhone" maxlength="30" onkeypress="return validateNumeric(event)" tabindex="1"></html:text>
									</div>
								</td>
							</tr>
						</table>
					</div>
							
					<div id="divPoliceId" style="display: none;">
						<table width="100%" border="0"  cellspacing="1" cellpadding="0">
							<tr>
								<td width="25%" class="tdfonthead">
									<div align="right">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<font color="#FF0000">*</font>
											<bean:message key="officer"/>
											<bean:message key="name"/>
										</font>
									</div>
								</td>
								<td width="25%" class="tdfont">
									<div align="left">
										<html:text name="UnknownBodyIdentificationFB" property="officerName" maxlength="60" onkeypress="return validateAlphabetsWithDotsOnly(event,this)" tabindex="1" ></html:text>
									</div>
								</td>
								<td width="25%" class="tdfonthead">
									<div align="right">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<font color="#FF0000">*</font>
											<bean:message key="officer"/>
											<bean:message key="designation"/>
										</font>
									</div>
								</td>
								<td width="25%" class="tdfont">
									<div align="left">
										<html:text name="UnknownBodyIdentificationFB" property="officerDesignation" maxlength="50" onkeypress="return validateAlphabetsWithDotsOnly(event,this)" tabindex="1" ></html:text>
									</div>
								</td>
							</tr>
							<tr>
								<td width="25%" class="tdfonthead">
									<div align="right">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<font color="#FF0000">*</font>
											<bean:message key="officer"/>
											<bean:message key="batchno"/>
										</font>
									</div>
								</td>
								<td width="25%" class="tdfont">
									<div align="left">
										<html:text name="UnknownBodyIdentificationFB" property="officerBadgeNo" maxlength="50" onkeypress="return validateAlphaNumOnly(this,event)" tabindex="1" ></html:text>
									</div>
								</td>
								<td width="25%" class="tdfonthead"></td>
								<td width="25%" class="tdfont"></td>
							</tr>
						</table>
					</div>
				
					<table width="100%" border="0"  cellspacing="1" cellpadding="0">
						<tr>
							<td width="25%" class="tdfonthead">
								<div align="right">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<logic:equal name="UnknownBodyIdentificationFB" property="isClaimedFlag" value="<%=MortuaryConfig.NO %>">
											<bean:message key="identification"/>
											<bean:message key="remarks"/>
										</logic:equal>
										<logic:equal name="UnknownBodyIdentificationFB" property="isClaimedFlag" value="<%=MortuaryConfig.YES %>">
											<bean:message key="claimant"/>
											<bean:message key="remarks"/>
										</logic:equal>
									</font>
								</div>
							</td>
							<td width="75%" class="tdfont" >
								<div align="left">
									<html:textarea name="UnknownBodyIdentificationFB" property="identityRemarks" rows="1" cols="70"  tabindex="1" onkeypress="return (validateTextArea(event,this,'100') && validateAlphaNumericOnly(event,this))"></html:textarea>
								</div>
							</td>
						</tr>
					</table>		
				</his:ContentTag>
				<bean:define name="UnknownBodyIdentificationFB" property="sysDate" id="sysDate" type="java.lang.String" />
					<%
						if(sysDate==null||sysDate.equalsIgnoreCase(""))
						{
							sysDate = WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy");
						}
					%>
				<html:hidden name="UnknownBodyIdentificationFB" property="sysDate" value="<%=sysDate %>"/>
			
			</his:statusTransactionInProcess>
			
			<his:ButtonToolBarTag>
			<his:statusList>
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer tabindex="1" onclick =" cancelFunc()" onkeypress="if(event.keyCode==13)cancelFunc()">
			</his:statusList>
				
			<his:statusUnsuccessfull>
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer tabindex="1" onclick =" submitPage('FINALCANCEL')" onkeypress="if(event.keyCode==13)submitPage('CANCEL')">
			</his:statusUnsuccessfull>
			
			<his:statusTransactionInProcess>
				<logic:equal name="UnknownBodyIdentificationFB" property="isClaimedFlag" value="<%=MortuaryConfig.NO %>">
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>'  style=cursor:pointer tabindex="1" onclick ="if(validateSave()) submitPage('SAVE')" onkeypress="if(event.keyCode==13)if(validateSave())submitPage('SAVE')">				
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer tabindex="1" onclick =" submitPage('CANCEL')" onkeypress="if(event.keyCode==13)submitPage('CANCEL')">
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>'  style=cursor:pointer tabindex="1" onclick =" clearUnknownDetail()" onkeypress="if(event.keyCode==13)clearUnknownDetail()">
				</logic:equal>
				
				
				
				<logic:equal name="UnknownBodyIdentificationFB" property="isClaimedFlag" value="<%=MortuaryConfig.YES %>">
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>'  style=cursor:pointer tabindex="1" onclick ="if(validateClaimedSave()) submitPage('SAVECLAIM')" onkeypress="if(event.keyCode==13)if(validateClaimedSave())submitPage('SAVECLAIM')">
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer tabindex="1" onclick =" submitPage('CANCEL')" onkeypress="if(event.keyCode==13)submitPage('CANCEL')">
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>'  style=cursor:pointer tabindex="1" onclick =" clearForm()" onkeypress="if(event.keyCode==13)clearForm()">	
				</logic:equal>
			</his:statusTransactionInProcess>
			
		</his:ButtonToolBarTag>
			
		</his:TransactionContainer>
		
			<html:hidden name="UnknownBodyIdentificationFB" property="hmode"/>
			<html:hidden name="UnknownBodyIdentificationFB" property="deceasedNo" />
			<html:hidden name="UnknownBodyIdentificationFB" property="isClaimedFlag" />
			
	</html:form>
	<his:status/>
</body>
</html>			