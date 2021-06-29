<%--
/*
 * @ author Pragya Sharma
 * Created at 04-Aug-2011
 */
--%>

<%
try
{
%>

<%@page autoFlush="true"%>
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<%@page import="hisglobal.hisconfig.Config"%>
<%@page import="registration.controller.fb.PatientModificationFB"%>
<%@page import="hisglobal.Status"%>


<%@page import="java.util.Collection"%>
<%@page import="java.util.HashSet"%>
<%@page import="registration.RegistrationConfig"%>
<%@page import="java.util.Date"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<his:css src="/hisglobal/css/tab.css" />
<his:css src="/hisglobal/css/Color.css" />
<his:css src="/hisglobal/css/master.css" />
<his:css src="/hisglobal/css/hisStyle.css" />
<his:css src="/hisglobal/css/calendar-blue2.css" />
<his:css src="/hisglobal/css/hisStyleExt.css" />

<script language="JavaScript" src="/HISClinical/registration/js/popup.js"></script>
<script language="JavaScript" src="/HISClinical/registration/js/registration.js"></script>
<script language="JavaScript" src="/HISClinical/registration/js/validationCalls.js"></script>
<script language="JavaScript" src="/HISClinical/registration/js/validationCommon.js"></script>
<script language="JavaScript" src="/HISClinical/registration/js/stateChangeAjax.js"></script>
<script language="JavaScript" src="/HISClinical/registration/js/cityLocationAjax.js"></script>
<script language="JavaScript" src="/HISClinical/registration/js/commonFunctions.js"></script>


<script language="JavaScript" src="/HISClinical/hisglobal/js/utilityFunctions.js"></script>
<script language="JavaScript" src="/HISClinical/hisglobal/utility/generictemplate/js/validationFunctions.js"></script>
<script language="JavaScript" src="/HISClinical/hisglobal/utility/generictemplate/js/date_validator.js"></script>

<script language="JavaScript" src="/HISClinical/registration/js/patientModification.js"></script>

<script type="text/javascript">


//moved from JS file to jsp for appplying validation
function requestByValidation()
{
	  var valid=true
  var ageBeforeModification=parseInt(document.getElementsByName('beforeModificationAge')[0].value)
  var validge=<%=Integer.parseInt(RegistrationConfig.VALID_AGE_FOR_REQUESTING_MODIFICATION)%>
  var requestBy=document.getElementsByName('requestBy')[0].value
  var requestBySelf=<%=RegistrationConfig.MODIFICATION_REQUESTED_BY_SELF%>
  var requestByRelative=<%=RegistrationConfig.MODIFICATION_REQUESTED_BY_RELATIVE%>
  var requestByPolice=<%=RegistrationConfig.MODIFICATION_REQUESTED_BY_POLICE%>
  var mlcNo=document.getElementsByName('mlcNo')[0].value
  var patientIsMlc=document.getElementsByName('isMLC')[0].value
  var mlcTrue='<%=RegistrationConfig.IS_MLC_TRUE%>'
  var patientStatus=document.getElementsByName('patStatusCode')[0].value
  var patStatusDead=<%=RegistrationConfig.PATIENT_STATUS_CODE_DEAD%>
  
  if(patientStatus==patStatusDead && requestBy==requestBySelf)
  {
  	alert("Dead Patient Cannot Request For Modification");
  	return false
  }
  
  else if(ageBeforeModification<validge && requestBy==requestBySelf)
  {
  	alert("Patient Less Than "+validge+" Years Cannot Ask For Modification")
  	return false
  }
  else
  {
  	return true
  }
}
</script>

<%
	Collection addressType = new HashSet();
	addressType = (HashSet) session.getAttribute(RegistrationConfig.ESSENTIALBO_OPTION_EDIT_ADDRESS_TYPE_FOR_VALIDATION);
	String systemDate = hisglobal.presentation.WebUTIL.getCustomisedSysDate((Date) session.getAttribute(Config.SYSDATEOBJECT),"dd-MMM-yyyy HH:mm");
	String currentDate = hisglobal.presentation.WebUTIL.getCustomisedSysDate((Date) session.getAttribute(Config.SYSDATEOBJECT),"dd-MMM-yyyy");
	String strdivage = "\"\"";
	String strdivdob = "\"\"";
%>

<script>

var isAddressAdded  = <%=RegistrationConfig.IS_ADDRESS_ADDED%>;
var isAddressModified = <%=RegistrationConfig.IS_ADDRESS_MODIFIED%>;
var primaryCatPoorFreeCode = '<%=Config.PRIMARY_CATEGORY_POOR_FREE_CODE%>';
var registrationDeskDefaultStateCode = '<%=RegistrationConfig.REGISTRATIONDESK_DEFAULT_STATE_CODE %>';
var registrationDeskDefaultCountryCode = '<%=RegistrationConfig.REGISTRATIONDESK_DEFAULT_COUNTRY_CODE%>';
var validAgeForRequestingModification = <%=Integer.parseInt(RegistrationConfig.VALID_AGE_FOR_REQUESTING_MODIFICATION)%>;
var isAddressDelhi = '<bean:write name="PatientModificationFB" property="isAddressDelhi"/>';
var primaryCategoryEmployeeCode = '<%=Config.PRIMARY_CATEGORY_EMPLOYEE_CODE%>';
var isAddressCurrentTrue = <%=RegistrationConfig.IS_ADDRESS_CURRENT_TRUE%>;
var modificationRequestedBySelf = <%=RegistrationConfig.MODIFICATION_REQUESTED_BY_SELF%>;
var modificationRequestedByRelative = <%=RegistrationConfig.MODIFICATION_REQUESTED_BY_RELATIVE%>;
var modificationRequestedByPolice = <%=RegistrationConfig.MODIFICATION_REQUESTED_BY_POLICE%>;
var patientStatusCodeDead = <%=RegistrationConfig.PATIENT_STATUS_CODE_DEAD%>;
var configIsMlcTrue = '<%=RegistrationConfig.IS_MLC_TRUE%>';
var clientPGIMER='<%=Config.CLIENT_PGIMER%>';
var fieldEditableValue="<%=Config.PATCAT_EMPLOYEE_FIELD_VALUE%>";
var fieldEditableFalse="<%=Config.PATCAT_EMPLOYEE_FIELD_EDITABLE_FALSE%>";
var dataFromTableValue="<%=Config.PATCAT_EMPLOYEE_DATA_FROM_TABLE_VALUE%>";
var dataFromTableTrue="<%=Config.PATCAT_EMPLOYEE_DATA_FROM_TABLE_TRUE%>";
var registrationDeskDefaultAreaCatCode = "<%=RegistrationConfig.REGISTRATIONDESK_DEFAULT_AREA_CATEGORY_CODE%>";
var locationRequired="<%=Config.IS_LOCATION_COMBO_REQ%>";
var locationRequiredYes="<%=Config.IS_LOCATION_COMBO_REQ_YES%>";
var seniorCitizenCode = '<%=Config.PRIMARY_CATEGORY_SENIOR_CITIZEN%>';
var seniorCitizenAge = '<%=Config.SENIOR_CITIZEN_AGE%>';


var childwindow=null;

//Submit Tile by setting hmode
function submitTile(mode)
{
	elem = document.getElementsByName('hmode')[0];
	elem.value = mode;
	if(mode=="SAVE")
	{
		if(document.getElementsByName("arrSelectedVerifyDocs").length!=0)
			document.forms[0].submit();
		else
			alert("Verification for the addresses Not Specified !! ");
	}
	else if(mode=="ADDADDRESS")	
	{
		<%if(addressType!=null && addressType.size()==0){ %>
			alert("All Address Types have been Added Please Select from the list to Modify")
		<% }else{ %>
			document.forms[0].submit();
		<% } %>
	}	
	else
		document.forms[0].submit();
}

 
</script>


</head>

<body>

<!--<html:form action="/patientModification">-->

	<bean:define id="objStatus" name="<%=Config.STATUS_OBJECT%>" type="hisglobal.presentation.Status" />

	<his:TitleTag>
		<his:name>
			<bean:message key="patientdetail" />
			<bean:message key="modification" />
		</his:name>
	</his:TitleTag>

	<his:InputCrNoTag name="PatientModificationFB">
	</his:InputCrNoTag>
	
	
	<his:statusNew>
	<input type='hidden' name='crNoToRetrieve' />
	<his:ContentTag>

		<table width="100%" border="0" cellspacing="1" cellpadding="0">
			<logic:notEmpty name="<%=RegistrationConfig.PATIENT_VO%>">
				<logic:iterate id="PATIENT_VO"
					name="<%=RegistrationConfig.PATIENT_VO%>">

					<tr>
						<td width="5%" class="tdfont"><input type="radio"
							name="crNoToModify" onclick="submitForm('GETPATDTL');"
							value='<bean:write name="PATIENT_VO" property="patCrNo" />' tabindex="1" /></td>
						<td width="20%" class="tdfont"><bean:write name="PATIENT_VO"
							property="patCrNo" /></td>
						<td width="25%" class="tdfont"><bean:write name="PATIENT_VO"
							property="patFirstName" /> <bean:write name="PATIENT_VO"
							property="patMiddleName" /> <bean:write name="PATIENT_VO"
							property="patLastName" /></td>
						<td width="25%" class="tdfont"></td>
						<td width="25%" class="tdfont"></td>
						<td width="25%" class="tdfont"></td>
					</tr>
				</logic:iterate>
			</logic:notEmpty>
		</table>
	</his:ContentTag>
</his:statusNew>
	

	<his:statusTransactionInProcess>
		<his:ContentTag>
			<table width="100%" border="0" cellspacing="1" cellpadding="0">
				<tr style="display:none">
					<td width="15%" nowrap class="tdfonthead">
						<div align="right">
							<font color="#FF0000">*</font>
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="patient" /> <bean:message key="category" />
							</font>
						</div>
					</td>
					<td width="17%" class="tdfont">
						<div align="left">
							<html:text name="PatientModificationFB" property="patPrimaryCat" tabindex="1" styleClass="regcbo" readonly="true"></html:text>
						</div>
					</td>
					<td width="15%" nowrap class="tdfonthead"></td>
					<td width="15%" nowrap class="tdfont"></td>
					<td width="15%" nowrap class="tdfonthead"></td>
					<td width="15%" nowrap class="tdfont"></td>
				</tr>
				<tr id="poorPatTR" style="display: none;">
					<td width="19%" class="tdfonthead">
						<div align="right" id="mmjrkDiv" style="display: none;">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="mmjrkNo" />
							</font>
						</div>
					</td>
<!--					<td class="tdfont" colspan="1">-->
<!--						<div align="left" id="mmjrkTextDiv" style="display: none;">-->
<!--							<html:text name="PatientModificationFB" property="patCardNo" styleClass="textbox" maxlength="30" tabindex="1" onkeypress="return validateAlphaNumericWithDotsOnly(event,this)" />-->
<!--							</div>-->
<!--					</td>-->
					<td width="14%" height="25" nowrap class="tdfonthead">
						<div align="right" id="familyHeadDiv" style="display: none;">
							<font size="2" color="#000000" style="display: block" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="familyHeadName" />
							</font>
						</div>
					</td>
					<td width="17%" class="tdfont">
						<div style="display: none" id="familyHeadTextDiv">
							<html:text name="PatientModificationFB" property="patNickName" tabindex="1" styleClass="textbox" maxlength="30" 
								onchange="isAlpha(this,'Nick Name')" onkeypress="return validateAlphabetsWithDotsOnly(event,this)" />
						</div>
					</td>
					<td width="17%" class="tdfonthead" nowrap="nowrap">
						<div align="right" id="relationDiv" style="display: none;">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="relation" />
							</font>
						</div>
					</td>
					<td width="17%" class="tdfont" nowrap="nowrap">
						<div id="relationComboDiv" style="display: none;">
							<html:select name="PatientModificationFB" tabindex="2" property="patHusbandOccupation" styleClass="regcbo">
								<html:option value="">Select Value</html:option>
								<html:option value="28">Aunt</html:option>
								<html:option value="16">Brother</html:option>
								<html:option value="23">Brother In-Law</html:option>
								<html:option value="19">Cousin</html:option>
								<html:option value="18">Daughter</html:option>
								<html:option value="24">Daughter In-Law</html:option>
								<html:option value="11">Father</html:option>
		
								<html:option value="20">Father In-Law</html:option>
								<html:option value="30">Granddaughter</html:option>
								<html:option value="31">Grandfather</html:option>
								<html:option value="32">Grandson</html:option>
								<html:option value="13">Husband</html:option>
								<html:option value="12">Mother</html:option>
								<html:option value="22">Mother In-Law</html:option>
								<html:option value="26">Nephew</html:option>
								<html:option value="27">Niece</html:option>
		
								<html:option value="15">Sister</html:option>
								<html:option value="21">Sister In-Law</html:option>
								<html:option value="17">Son</html:option>
								<html:option value="25">Son In-Law</html:option>
								<html:option value="29">Uncle</html:option>
								<html:option value="14">Wife</html:option>
							</html:select>
						</div>
					</td>
				</tr>

				<tr>
					<td width="14%" height="25" nowrap class="tdfonthead">
						<div align="right"><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:message key="patient" /> <bean:message key="name" />
						</font></div>
					</td>
					<td width="17%" class="tdfonthead">
						<div align="left">
							<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font>
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="first" /> 
							</font>
						</div>
					</td>
					<td width="18%" class="tdfonthead">
						<div align="left"><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif" style="display: block">
							<bean:message key="middle" />
						</font></div>
					</td>

					<td width="19%" class="tdfonthead">
						<div align="left"><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:message key="last" /> </font></div>
					</td>

					<td class="tdfonthead" colspan="1">
						<div align="right" style="display:none">
							<img class="button" style="cursor: pointer" src='<his:path src="/hisglobal/images/camera.png"/>' 
								alt="Capture Photo" title="Capture Photo" onkeypress="if(event.keyCode==13) captureImage(event)" 
								onclick="captureImage(event)">
						</div>
					</td>
					<td class="tdfont" width="18">
						<div align="left" style="display:none">
							<img class="button" style="cursor: pointer" src='<his:path src="/hisglobal/images/view.psd.gif"/>'
								alt="View Photo" title="View Photo"
								onkeypress="if(event.keyCode==13) openPopup('<his:path src="/registration/enlargedImage.cnt"/>',event,300,600)"
								onclick="openPopup('<his:path src="/registration/enlargedImage.cnt"/>',event,300,600)">
						</div>
					</td>
				</tr>
				
				<tr>
					<td width="14%" class="tdfonthead">&nbsp;</td>
					<td width="17%" class="tdfont">
						<html:text name="PatientModificationFB" property="patFirstName" styleClass="textbox" tabindex="1"
						maxlength="30" onblur="(this,'First Name')" onkeypress="return validateAlphabetsWithDotsAndForwardSlashWithHypen(event,this)" />
					</td>
					<td width="18%" class="tdfonthead">
						<div align="left">
							<html:text name="PatientModificationFB" property="patMiddleName" tabindex="1" styleClass="textbox"
							onblur="isAlpha(this,'Middle Name')" maxlength="20" onkeypress="return validateAlphabetsWithDotsOnly(event,this)" 
							style="display:block" />
						</div>
					</td>
					<td class="tdfont">
						<html:text name="PatientModificationFB" property="patLastName" tabindex="1" styleClass="textbox"
						onblur="isAlpha(this,'Last Name')" maxlength="30" onkeypress="return validateAlphabetsWithDotsOnly(event,this)" />
					</td>
					<!-- Patient Category Id -->
					<td width="14%" class="tdfonthead">
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="patCaste" />
							</font>
						</div>
					</td>
					<td class="tdfont">
						<html:select name="PatientModificationFB" property="patCasteCode" tabindex="1" styleClass="regcbo">
							<html:option value="-1">Select Value</html:option>
							<html:options collection="<%=RegistrationConfig.ESSENTIALBO_OPTION_CASTE %>" property="value" labelProperty="label" />
						</html:select>
					</td>
				</tr>
				<tr>
					<td width="17%" nowrap class="tdfonthead">
						<div align="center">
							<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font>
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="age" />
							</font>
							<html:radio name="PatientModificationFB" property="isActualDob" tabindex="1" value="0" onclick="ageModificationSelection()" />
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="dob" />
							</font>
							<html:radio name="PatientModificationFB" property="isActualDob" tabindex="1" value="1" onclick="ageModificationSelection()" />
						</div>
					</td>
					
					<logic:empty name="PatientModificationFB" property="isActualDob">
						<%
								strdivage = "none";
								strdivdob = "none";
						%>
					</logic:empty>
					<logic:equal name="PatientModificationFB" property="isActualDob" value="1">
						<%
								strdivage = "none";
								strdivdob = "block";
						%>
					</logic:equal>
					<logic:equal name="PatientModificationFB" property="isActualDob" value="0">
						<%
								strdivage = "block";
								strdivdob = "none";
						%>
					</logic:equal>

					<td width="18%" nowrap="nowrap" class="tdfont">
						<div id="divAge" style='display: <%=strdivage%>'>
							<html:text name="PatientModificationFB" size="4" tabindex="1" property="patAge" maxlength="3" onkeypress="return validateNumeric(event)" />
							<html:select name="PatientModificationFB" property="patAgeUnit" tabindex="1" styleClass="smallcombo">
								<html:option value="-1">Select Value</html:option>
								<html:options collection="<%=RegistrationConfig.ESSENTIALBO_OPTION_AGE_TYPE %>" property="value" labelProperty="label" />
							</html:select>
						</div>
						<div id="divDob" style='display: <%=strdivdob%>'>
							<bean:define name="PatientModificationFB" property="patDOB" id="dob" type="java.lang.String" />
							<div id="divPatDOB"></div>
							<script type="text/javascript">DateValidator.setup("divPatDOB","patDOB","<%=dob%>","dd-MM-yyyy","textbox");</script>
						</div>
					</td>
					<td width="14%" class="tdfonthead">
						<div align="right">
							<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font>
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="gender" />
							</font>
						</div>
					</td>
					<td width="17%" class="tdfont">
						<html:select name="PatientModificationFB" property="patGenderCode" tabindex="1" styleClass="regcbo">
							<html:option value="-1">Select Value</html:option>
							<html:options collection="<%=RegistrationConfig.ESSENTIALBO_OPTION_GENDER %>" property="value" labelProperty="label" />
						</html:select>
					</td>
					<td width="17%" class="tdfonthead">
						<div align="right"><font color="#000000" size="2"
						face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
						key="maritalStatus" /> </font></div>
					</td>
					<td width="17%" class="tdfont"><html:select
						name="PatientModificationFB" property="patMaritalStatusCode" 
						tabindex="2" styleClass="regcbo" onchange="changeSpouseField(this)">
					<html:option value="-1">Select Value</html:option>
					<html:options
						collection="<%=RegistrationConfig.ESSENTIALBO_OPTION_MARITAL_STATUS %>"
						property="value" labelProperty="label" />
					</html:select></td>
				</tr>

				<tr>
					<td width="14%" height="25" nowrap="nowrap" class="tdfonthead">
						<div align="right">
							<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font>
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="fatherName" />
							</font>
						</div>
					</td>
					<td width="17%" class="tdfont">
						<html:text name="PatientModificationFB" property="patGuardianName" styleClass="textbox" tabindex="1"
							maxlength="60" onblur="isAlpha(this,'Father Name')" onkeypress="return validateAlphabetsWithDotsOnly(event,this)" />
					</td>
					<td width="14%" height="25" nowrap class="tdfonthead">
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="spouseName" />
							</font>
						</div>
					</td>
					<td class="tdfont" width="17%">
						<html:text name="PatientModificationFB" property="patHusbandName" tabindex="1" styleClass="textbox"
							maxlength="60" onkeypress="return validateAlphabetsWithDotsOnly(event,this)" />
					</td>
					<td width="18%" height="25" nowrap class="tdfonthead">
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="motherName" />
							</font>
						</div>
					</td>
					<td class="tdfont" width="17%">
						<html:text name="PatientModificationFB" property="patMotherName" tabindex="1" styleClass="textbox"
							onblur="isAlpha(this,'Mother Name')" maxlength="60" onkeypress="return validateAlphabetsWithDotsOnly(event,this)" />
					</td>
				</tr>

				<tr>
					<td class="tdfonthead" width="18%">
						<div align="right">
							<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font>
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="nationality" />
							</font>
						</div>
					</td>
					<td class="tdfont" width="17%">
						<div id="patCountryCombo"><html:select name="PatientModificationFB" property="patNationalityCode" tabindex="1" styleClass="regcbo">
							<html:option value="-1">Select Value</html:option>
							<html:options collection="<%=RegistrationConfig.ESSENTIALBO_OPTION_NATIONALITY %>" property="value" labelProperty="label" />
						</html:select></div>
					</td>
					<td width="14%" class="tdfonthead">
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="religion" /></font>
							</div>
					</td>
					<td width="17%" class="tdfont">
						<html:select name="PatientModificationFB" property="patReligionCode" tabindex="1" styleClass="regcbo">
							<html:option value="">Select Value</html:option>
							<html:options collection="<%=RegistrationConfig.ESSENTIALBO_OPTION_RELIGION %>" property="value" labelProperty="label" />
						</html:select>
					</td>
					<td width="17%" class="LABEL"><bean:message key="birplace" />
					</td>
					<td width="17%" class="tdfont"><html:text
						name="PatientModificationFB" property="patBirthPlace"
						styleClass="textbox" tabindex="2" maxlength="60"
						onchange="isAlpha(this,'Guardian Name')"
						onkeypress="return validateAlphabetsWithDotsOnly(this,event)" /></td>
				</tr>
				<tr>
					<td width="14%" class="tdfonthead">
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="adhar" />
							</font>
						</div>
					</td>
					<td width="17%" class="tdfont">
						<html:text name="PatientModificationFB" property="patNationalId" styleClass="textbox" maxlength="16"
							tabindex="1" onkeypress="return validateAlphaNumericWithSpecialCharacterOnly(this,event)" styleClass="textbox" />
					</td>
					<td width="17%" class="LABEL"><bean:message key="otherId" /></td>
					<td width="17%" class="tdfont"><html:select
						name="PatientModificationFB" property="patDocType"
						tabindex="2" styleClass="regcbo" onchange="changeCardNField(this)">
						<html:option value="-1">Select Value</html:option>
						<html:options
							collection="<%=RegistrationConfig.ESSENTIALBO_OPTION_VERIFICATION_DOCUMENTS %>"
							property="value" labelProperty="label" />
					</html:select></td>
					<td width="17%" class="LABEL"><bean:message key="patCardNo" /></td>
					<td width="17%" class="tdfont"><html:text
						name="PatientModificationFB" property="patCardNo" styleClass="textbox" maxlength="11" onkeypress="return validateAlphaNumericWithSpecialCharacterOnly(this,event)"/></td>
				</tr>
				<tr>
					<td width="17%" class="LABEL"><bean:message key="patOccupation" /></td>
					<td width="17%" class="tdfont"><html:select
						name="PatientModificationFB" property="patOccupation"
						tabindex="2" styleClass="regcbo">
						<html:option value="-1">Select Value</html:option>
						<html:options
							collection="<%=RegistrationConfig.ESSENTIALBO_OPTION_OCCUPATION_DTL %>"
							property="value" labelProperty="label" />
					</html:select></td>
					<td class="LABEL" width="17%"><bean:message key="monthlyIncome" />
					</td>
					<td width="17%" class="tdfont"><html:text
						name="PatientModificationFB" property="patMonthlyIncome"
						styleClass="textbox" maxlength="16" tabindex="2"
						onkeypress="return validateNumericOnly(this,event)" />
					</td>
					<td width="17%" class="LABEL"></td>
					<td width="17%" class="tdfont"></td>		
				</tr>
			</table>
		</his:ContentTag>

		<his:SubTitleTagBroad>
			<his:name>
				<bean:message key="address" />
				<bean:message key="detail" />
			</his:name>
			<img class="button" src='<his:path src="/hisglobal/images/plus.png"/>' style="cursor: pointer" 
				onclick="submitTile('ADDADDRESS');" onkeypress="if(event.keyCode==13) submitTile('ADDADDRESS');" size='7' tabindex="1">
		</his:SubTitleTagBroad>

		<his:ContentTag>
			<logic:present name="<%=RegistrationConfig.REGDESK_ADDRESSVO_ARR%>">
				<table width="100%" cellspacing="1" cellpadding="0">
					<tr>
						<td class="tdfont" width="15%">
							<div align="left">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<bean:message key="addressType" />
								</font>
							</div>
						</td>
						<td class="tdfont" width="85%">
							<div align="left">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<bean:message key="address" />
								</font>
							</div>
						</td>
					</tr>
					<logic:iterate id="address" name="<%=RegistrationConfig.REGDESK_ADDRESSVO_ARR%>" type="hisglobal.vo.AddressVO">
						<tr>
							<td width="15%" Class="tdfonthead">
								<div align="left">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<html:radio name="PatientModificationFB" property='patFBAddTypeCode' 
											value="<%=address.getPatAddTypeCode()%>" onclick="getAddressDetailForModification()" />
										<bean:write name="address" property="patAddType" />
									</font>
								</div>
							</td>
							<td width="85%" Class="tdfonthead">
								<div align="left">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<bean:write name="address" property="patAddHNo" />
										<logic:notEqual name="address" property="patAddHNo" value="">,</logic:notEqual>
										<bean:write name="address" property="patAddStreet" />
										<logic:notEqual name="address" property="patAddStreet" value="">,</logic:notEqual>
										<bean:write name="address" property="patAddCityLoc" />
										<logic:notEqual name="address" property="patAddCityLoc" value="">,</logic:notEqual>
										<bean:write name="address" property="patAddCityLocMstValue" />
										<logic:notEqual name="address" property="patAddCityLocMstValue" value="">,</logic:notEqual>
										<bean:write name="address" property="patAddCity" />
										<logic:notEqual name="address" property="patAddCity" value="">,</logic:notEqual>
										<bean:write name="address" property="strPatAddressTehsil" />
										<logic:notEqual name="address" property="strPatAddressTehsil" value="">,</logic:notEqual>
										<bean:write name="address" property="patAddDistrictMstValue" />
										<logic:notEqual name="address" property="patAddDistrictMstValue" value="">,</logic:notEqual>
										<bean:write name="address" property="patAddState" />
										<logic:notEqual name="address" property="patAddState" value="">,</logic:notEqual>
										<bean:write name="address" property="patAddCountry" />
										<logic:notEqual name="address" property="patAddPIN" value=""><bean:message key="line" /></logic:notEqual>
										<bean:write name="address" property="patAddPIN" />
										<logic:notEqual name="address" property="patAddContactNo" value="">
											<bean:message key="addressContactNo" />
										</logic:notEqual> <bean:write name="address" property="patAddContactNo" />
									</font>
								</div>
							</td>
						</tr>
					</logic:iterate>
				</table>
			</logic:present>
		</his:ContentTag>

		<logic:notEmpty name="PatientModificationFB" property="addModify">
		
			<bean:define name="PatientModificationFB" property="patFBAddTypeCode" id="typeCode" />
			<bean:define name="PatientModificationFB" property="hmode" id="hmode" />
		
			<%
						if (!typeCode.equals("-1") || hmode.equals("ADDADDRESS"))
						{
							String divdisplay = "\"\"";
							String divnddisplay = "\"\"";
							String divisrefdisplay = "\"\"";
							String divRefGnctdHospitalCode = "\"\"";
							String divRefHospname = "\"\"";
							String RefGnctdHosNameradio = "";
							String RefGnctdHoscoderadio = "";
			%>
			<his:SubTitleTag name=" ">
				<div align="left">
					<bean:message key="address" />
					<% if (request.getParameter("hmode").equals("MODIFYADDRESS")) { %>
						<bean:message key="modification" />
					<% } else { %> <bean:message key="addition" /> <% } %>
					<bean:message key="detail" />
				</div>
			</his:SubTitleTag>
			
			<his:ContentTag>
				<table width="100%" cellpadding="0" cellspacing="1" border="0">
					<logic:equal name="PatientModificationFB" property="isAddressDelhi" value="0">
						<%
								divdisplay = "none";
								divnddisplay = "";
								//System.out.println("divdisplay" + divdisplay);
						%>
					</logic:equal>
			
					<logic:equal name="PatientModificationFB" property="isAddressDelhi" value="1">
						<%
								divdisplay = "";
								divnddisplay = "none";
								//System.out.println("divnddisplay" + divnddisplay);
						%>
					</logic:equal>
					<tr>
						<td class="tdfonthead">
							<div align="right">
								<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font>
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<bean:message key="address" /> <bean:message key="type" />
								</font>
							</div>
						</td>
						<td id="addTypeCombo" class="tdfont">
							<html:select tabindex="1" name="PatientModificationFB" property="patAddTypeCode" styleClass="regcbo">
								<html:option value="-1">Select Value</html:option>
								<html:options collection="<%=RegistrationConfig.ESSENTIALBO_OPTION_EDIT_ADDRESS_TYPE%>" property="value" labelProperty="label" />
							</html:select>
						</td>
						<td id="addTypeText" class="tdfont">
							<html:text tabindex="1" name="PatientModificationFB" property="patAddTypeLable" maxlength="50" readonly="true" styleClass="textbox" />
						</td>
						<td width="25%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<bean:message key="tehsil" />
								</font>
							</div>
						</td>
						<td width="25%" class="tdfont">
							<html:text name="PatientModificationFB" tabindex="1" property="strPatAddressTehsil" maxlength="30"
								styleClass="textbox" onkeypress="return validateAlphabetsOnly(event,this)" />
						</td>
					</tr>
					<tr>
						<td class="tdfonthead" width="25%">
							<div align="right">
								<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font>
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<bean:message key="country" />
								</font>
							</div>
						</td>
						<td class="tdfont" width="25%">
							<div id="patCountryCombo">
								<html:select tabindex="1" name="PatientModificationFB" property="patAddCountryCode" onchange="showState();" styleClass="regcbo">
									<html:option value="-1">Select Value</html:option>
									<html:options collection="<%=RegistrationConfig.ESSENTIALBO_OPTION_COUNTRY %>" property="value" labelProperty="label" />
								</html:select>
							</div>
						</td>
			
						<td class="tdfonthead" width="25%">
							<div id="stateMandotary" style="" align="right">
								<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font>
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<bean:message key="state" />
								</font>
							</div>
							<div id="stateNotMandotary" style="" align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<bean:message key="state" />
								</font>
							</div>
						</td>
			
						<td class="tdfont" width="25%">
							<div id="divpatAddStateCodeCombo" align="left">
								<html:select tabindex="1" name="PatientModificationFB" property="patAddStateCode"
									onchange="if(this.value!='-1') sendDataForStateChange(this)" styleClass="regcbo">
									<html:option value="-1">Select Value</html:option>
									<html:options collection="<%=RegistrationConfig.ESSENTIALBO_OPTION_STATE %>" property="value" labelProperty="label" />
								</html:select>
							</div>
							<div id="divpatAddStateCodeText" style="" align="left">
								<html:text name="PatientModificationFB" tabindex="1" property="patAddStateName"
									styleClass="regcbo" maxlength="50" onkeypress="return validateAlphabetsWithDotsOnly(event,this);">
							</html:text></div>
						</td>
					</tr>
					<tr>
						<td class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<bean:message key="location" />
								</font>
							</div>
						</td>
						<td class="tdfont">
							<div id="divpatAddCityLocCode">
								<html:select tabindex="1" name="PatientModificationFB" property="patAddCityLocCode" styleClass="regcbo"
									onchange="if(this.value!='-1') sendDataForCityLocation(this)">
									<html:option value="-1">Select Value</html:option>
									<html:options collection="<%=RegistrationConfig.ESSENTIALBO_OPTION_LOCATION%>" property="value" labelProperty="label" />
								</html:select>
							</div>
							<div id="divpatAddCityLocation">
								<html:text tabindex="2" name="PatientModificationFB" property="patAddCityLoc"
									onkeypress="return validateAlphabetsWithDotsOnly(event,this);" maxlength="50" styleClass="textbox" />
							</div>
						</td>
						<td class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<bean:message key="hno" />
								</font>
							</div>
						</td>
						<td class="tdfont">
							<html:text tabindex="1" onkeypress="return validateAlphaNumericWithDotsOnly(event,this);" maxlength="15"
								name="PatientModificationFB" property="patAddHNo" styleClass="textbox" />
						</td>
					</tr>
					
					<tr>
						<td class="tdfonthead">
							<div align="right"><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="street" />
							</font></div>
						</td>
						<td class="tdfont">
							<html:text tabindex="1" name="PatientModificationFB" property="patAddStreet"
								onkeypress="return validateAlphaNumericWithDotsOnly(event,this);" maxlength="30" styleClass="textbox" />
						</td>
						<td class="tdfonthead">
							<div align="right"><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="district" />
							</font></div>
						</td>
						<td class="tdfont">
							<div id="districtCombo" style='display: <%=divdisplay%>'>
								<html:select tabindex="1" name="PatientModificationFB" property="patAddDistrictCode" styleClass="regcbo">
									<html:option value="-1">Select Value</html:option>
									<html:options collection="<%=RegistrationConfig.ESSENTIALBO_OPTION_DISTRICT_ON_DEFAULT_STATE%>" property="value" labelProperty="label" />
								</html:select>
							</div>
							<div id="districtTextBox" style='display: <%=divnddisplay%>'>
								<html:text tabindex="1" name="PatientModificationFB" property="patAddDistrict"
									onkeypress="return validateAlphabetsWithDotsOnly(event,this);" maxlength="30" styleClass="textbox" />
							</div>
						</td>
					</tr>
			
					<tr>
						<td class="tdfonthead">
							<div align="right">
								<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font>
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<bean:message key="city" /> <bean:message key="slash" /> <bean:message key="village" />
								</font>
							</div>
						</td>
						<td class="tdfont">
							<html:text tabindex="1" name="PatientModificationFB" property="patAddCity" onblur="isAlpha(this,'City');"
								onkeypress="return validateAlphabetsWithDotsOnly(event,this);" maxlength="30" styleClass="textbox" />
						</td>
						<td class="tdfonthead">
							<div align="right"><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="pin" />
							</font></div>
						</td>
						<td class="tdfont">
							<html:text tabindex="1" name="PatientModificationFB" property="patAddPIN" maxlength="6" styleClass="textbox"
								onkeypress="return validateNumeric(event)" />
						</td>
					</tr>
			
					<tr>
						<td class="tdfonthead">
							<div align="right">
								<font color="#FF0000" size="2" face="Verdana, Arial, Helvetica, sans-serif">*</font>
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<bean:message key="areaCategory" />
								</font>
							</div>
						</td>
						<td class="tdfont">
							<html:select tabindex="1" name="PatientModificationFB" property="patIsUrban" styleClass="regcbo">
								<html:option value="-1">Select Value</html:option>
								<html:options collection="<%=RegistrationConfig.ESSENTIALBO_OPTION_AREA_CATEGORY%>" property="value" labelProperty="label" />
							</html:select>
						</td>
						<td class="tdfonthead">
							<div align="right">
								
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<bean:message key="contactNo" />
								</font>
							</div>
						</td>
						<td class="tdfont">
							<html:text tabindex="1" name="PatientModificationFB" property="patAddContactNo" onkeypress="return validateNumeric(event);"
								maxlength="30" styleClass="textbox" />
						</td>
					</tr>
			
					<tr>
						<td class="tdfonthead">
							<div align="right"><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="mobileNo" />
							</font></div>
						</td>
						<td class="tdfont">
							<html:text tabindex="1" name="PatientModificationFB" property="patAddMobileNo" onkeypress="return validateNumeric(event);"
								maxlength="15" styleClass="textbox" />
						</td>
						<td class="tdfonthead">
							<div align="right"><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="faxNo" />
							</font></div>
						</td>
						<td class="tdfont">
							<html:text tabindex="1" name="PatientModificationFB" property="patAddFaxNo" onkeypress="return validateNumeric(event);"
								maxlength="30" styleClass="textbox" />
						</td>
					</tr>
					<tr>
						<td class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<bean:message key="email" /> 
								</font>
							</div>
						</td>
						<td class="tdfont">
							<html:text tabindex="1" name="PatientModificationFB" property="patAddEmailId" maxlength="50" styleClass="textbox"
								onkeypress="return CheckMaxLength(event,this,50,3)" />
						</td>
						<%
								String modeOnOK = "ADD";
								if (request.getParameter("hmode").equals( "MODIFYADDRESS"))
								{
									modeOnOK = "MODIFY";
								}
						%>
						<td class="tdfonthead">
						</td>
						<td class="tdfont">
						</td>
					</tr>
				</table>
			</his:ContentTag>
		<%
					}
		%>
		</logic:notEmpty>
	
		<bean:define name="PatientModificationFB" property="arrSelectedVerifyDocs" id="arrVerificationDocs" type="java.lang.String[]" />

		<table width="100%" cellspacing="0" cellpadding="0" bgcolor="#EBEBEB" class="applicationBackgroundColor" style="clear: both; border-left: 1px solid #003366;display: none;">
			<tr>
				<td width="30%" nowrap="nowrap" valign="middle" class="ShadedSubTitleTagImageBroad">
					<div align="left" class="TitleTagFontStyle" valign="middle">
						<font color="#FF0000">*</font>
						<bean:message key="verificationDocument" />
						<img class="button" src='<his:path src="/hisglobal/images/icon-vrf.png"/>' style="cursor: pointer;vertical-align: middle;"
							alt="Verification Documents" title="Verification Documents"
							onclick="openPopup('<his:path src="/registration/verificationDocumentPopup.cnt"/>',event,300,600);"
							onkeypress="if(event.keyCode==13) {openPopup('<his:path src="/registration/verificationDocumentPopup.cnt"/>',event,300,600)}"
							size='7' tabindex="1">
					</div>
				</td>
				<td width="70%" nowrap="nowrap" class="ShadedSubTitleTagImageBroad">
					<div align="left" class="TitleTagFontStyle" id="hiddenDivVerification">
						:: &nbsp;
						<%if(arrVerificationDocs==null || arrVerificationDocs.length==0 || arrVerificationDocs[0]==null || arrVerificationDocs[0].equals("")) {%>
							<font color="red"><bean:message key="reqdVerificationDocument" /></font>
							<input type="hidden" name="arrSelectedVerifyDocs" value="" />
						<%	} else {	%>
							<logic:iterate name="PatientModificationFB" property="arrSelectedVerifyDocs" id="selectedVerifyDocs" type="java.lang.String">
								<bean:define id="selectedVerifyDocs" name="selectedVerifyDocs" type="java.lang.String" />
								<%String[] arrayOfDocsData = selectedVerifyDocs.split("\\|");	%>
								<%=arrayOfDocsData[1]%>&nbsp; &nbsp;
								<input type="hidden" name="arrSelectedVerifyDocs" value="<%=selectedVerifyDocs%>" />
							</logic:iterate>
						<%	}	%>
					</div>
				</td>
			</tr>
		</table>

		<his:SubTitleTag name="Modification Requested By">
		</his:SubTitleTag>
	
		<his:ContentTag>
			<table width="100%" cellspacing="1" cellpadding="0" style="display: none;">
				<tr>
					<td width="25%" class="tdfonthead">
						<div align="right">
							<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font>
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="requestBy" />
							</font>
						</div>
					</td>
					<td width="25%" class="tdfont">
						<div align="left">
							<html:select name="PatientModificationFB" property="requestBy" styleClass="regcbo" onchange="enableRelation()" tabindex="1">
								<html:option value="-1">Select Value</html:option>
								<html:option value="<%=RegistrationConfig.MODIFICATION_REQUESTED_BY_SELF %>">Self</html:option>
								<html:option value="<%=RegistrationConfig.MODIFICATION_REQUESTED_BY_RELATIVE %>">Relative</html:option>
								<%
									String mlcNo = ((PatientModificationFB) pageContext .findAttribute("PatientModificationFB")) .getMlcNo();
									if (mlcNo != null && !mlcNo.equals(""))
									{
								%>
									<html:option value="<%=RegistrationConfig.MODIFICATION_REQUESTED_BY_POLICE%>">Police</html:option>
								<%
									}
								%>
							</html:select>
						</div>
					</td>
					<td width="25%" class="tdfonthead">
						<div align="right" id="relationshipSelfId">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="realtionship" />
							</font>
						</div>
						<div align="right" id="relationshipRelativeId">
							<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font>
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="realtionship" />
							</font>
						</div>
					</td>
					<td width="25%" class="tdfont">
						<html:select name="PatientModificationFB" property="requestRelation" tabindex="1" styleClass="regcbo" tabindex="1">
							<html:option value="-1">Select Value</html:option>
							<html:options collection="<%=RegistrationConfig.ESSENTIALBO_OPTION_RELATION_DTL %>" property="value" labelProperty="label" />
						</html:select>
					</td>
				</tr>
			</table>
			
			<div id="requesterDIV" style="display: none">
				<table width="100%" cellspacing="1" cellpadding="0">
					<tr>
						<td width="25%" class="tdfonthead">
							<!-- 16 -->
							<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font>
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="requestName" />
							</font>
						</td>
						<td width="25%" class="tdfont">
							<!-- 18 -->
							<html:text name="PatientModificationFB" tabindex="1" property="requestByName" tabindex="1"
								onkeypress="return validateAlphabetsWithDotsOnly(event,this)" tabindex="1" styleClass="textbox" 
								onblur="isAlpha(this,'Middle Name')" maxlength="60" />
						</td>
						<td width="25%" class="tdfonthead">
							<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font>
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="requestByAddress" />
							</font>
						</td>
						<td width="25%" class="tdfont">
							<!-- 3 -->
							<html:textarea name="PatientModificationFB" property="requestByAddress" tabindex="1"
								onkeypress="return CheckMaxLength(event,this,100,3)" tabindex="1"
								style="width: 180px;font-family: Verdana, Arial, Helvetica, sans-serif;font-size: 12;height:40px;color: #000000;" />
						</td>
					</tr>
				</table>
			</div>
			<div id="policeDetailDiv" style="display: none">
				<table width="100%" cellspacing="1" cellpadding="0">
					<tr>
						<td width="25%" class="tdfonthead">
							<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font>
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="designation" />
							</font>
						</td>
						<td width="25%" class="tdfont">
							<html:text name="PatientModificationFB" property="constableDesig" styleClass="textbox" tabindex="1"
								maxlength="50" onkeypress="return validateAlphaNumericWithDotsOnly(event,this)"></html:text>
						</td>
						<td width="25%" class="tdfonthead">
							<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font>
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="batchNo" />
							</font>
						</td>
						<td width="25%" class="tdfont">
							<html:text name="PatientModificationFB" property="constableBadgeNo" styleClass="textbox" tabindex="1"
								maxlength="50" onkeypress="return validateAlphaNumericWithDotsOnly(event,this)"></html:text>
						</td>
					</tr>
				</table>
			</div>
		</his:ContentTag>

	</his:statusTransactionInProcess>

	<his:ButtonToolBarTag>
		<his:statusTransactionInProcess>
			<%
				String hmd = request.getParameter("hmode");
				String display = "none";
				if (hmd.equals("ADDADDRESS"))	hmd = "ADD";
				if (hmd.equals("MODIFYADDRESS")) hmd = "MODIFY";
			%>
			<table width="100%">
				<tr>
					<td width='45%' align='right'>
						<div id="saveButton">
							<img class="button" style='cursor: pointer' src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>'
								onkeypress="if(event.keyCode==13) submitFormOnValidate(ValidatePatientDtlModification(),'SAVE');" tabindex="1"
								onclick="submitFormOnValidate(ValidatePatientDtlModification(),'SAVE');">
						</div>
					</td>
					<td width='55%' align='left'>
						<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style="cursor: pointer"
							onclick="clearForm()" onkeypress="if(event.keyCode==13) clearForm();" tabindex="1"> 
						<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style="cursor: pointer"
							onkeypress="if(event.keyCode==13) submitForm('NEW')" onclick="submitForm('NEW')" tabindex="1" />
					</td>
				</tr>
			</table>
		</his:statusTransactionInProcess>
		<his:statusNew>
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style="cursor: pointer;"
				onclick="submitForm('NEW')" onkeypress="if(event.keyCode==13) submitForm('NEW');" tabindex="1">
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style="cursor: pointer" 
				onkeypress="if(event.keyCode==13) submitPage('CANCEL')" onclick="submitPage('CANCEL')" tabindex="1">
		</his:statusNew>
	</his:ButtonToolBarTag>

	<html:hidden name="PatientModificationFB" property="hmode" value="unspecified" />
	<html:hidden name="PatientModificationFB" property="patCrNo" />
	<html:hidden name="PatientModificationFB" property="addModify" />
	<html:hidden name="PatientModificationFB" property="patAddTypeCode" />
	<html:hidden name="PatientModificationFB" property="patPrimaryCatCode" />
	<html:hidden name="PatientModificationFB" property="isMLC" />
	<html:hidden name="PatientModificationFB" property="mlcNo" />
	<html:hidden name="PatientModificationFB" property="verificationDocumentAdded" />
	<html:hidden name="PatientModificationFB" property="patStatusCode" />
	<html:hidden name="PatientModificationFB" property="isNewImageUploaded"/>
	<html:hidden name="PatientModificationFB" property="sysDate" value="<%=currentDate%>" />
	<html:hidden name="PatientModificationFB" property="beforeModificationAge"/>
	
	<his:status />
<!--</html:form>-->
</body>
</html>
<%
}
catch (Exception e)
{
	e.printStackTrace();
}
%>