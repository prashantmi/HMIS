<!-- 
## Copyright Information		: 	C-DAC, Noida  
## Project Name					: 	HIS-NIMS
## Name of Developer		 	: 	Anant Patel
## Module Name					: 	MRD
## Process/Database Object Name	:	Duplicate Record Printing Request
## Purpose						:	To raise a request for issueing duplicate Copy of Certificate issued by MRD department
## Date of Creation				: 	13-January-2015
## Modification Log				:					
##		Modify Date				: 	
##		Reason	(CR/PRS)		: 
##		Modify By				: 

 -->
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>
<%@page import="mrd.MrdConfig"%>
<his:css src="/hisglobal/css/calendar-blue2.css" />
<his:javascript src="/registration/js/registration.js" />
<his:javascript src="/registration/js/calendar.js" />
<his:javascript src="/registration/js/validationCommon.js" />
<his:javascript src="/registration/js/validationCalls.js" />
<his:javascript src="/hisglobal/js/commonFunctions.js" />
<his:javascript src="/registration/js/popup.js" />
<his:javascript src="/registration/js/dateFunctions.js" />
<his:javascript src="/registration/js/commonFunctions.js" />
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<his:javascript src="/hisglobal/utility/generictemplate/js/date_validator.js"/>
<his:css src="/hisglobal/css/Color.css" />
<his:css src="/hisglobal/css/master.css" />
<his:css src="/hisglobal/css/hisStyle.css" />
<his:css src="/hisglobal/css/hisStyleExt.css" />
<his:css src="/hisglobal/css/tab.css"/>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<script type="text/javascript">

window.onload=function()
{
ageSelection();
}

function showRequestedBy()
{
	if(document.getElementsByName("requestBy")[0].value=="-1")
	{
		document.getElementById("divRlativeId").style.display="none";
	}
	if(document.getElementsByName("requestBy")[0].value=="<%=MrdConfig.CERTIFICATE_REQ_BY_PATIENT%>")
	{
		document.getElementById("divRlativeId").style.display="none";
	}
	if(document.getElementsByName("requestBy")[0].value=="<%=MrdConfig.CERTIFICATE_REQ_BY_RELATIVE%>")
	{
		document.getElementById("divRlativeId").style.display="block";
	}
}
function submitPage(mode)
{
	document.getElementsByName("hmode")[0].value=mode;
	document.forms[0].submit();
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

function validateWithoutCrNo()
{
	//alert("validateWithoutCrNo");
	var valid=false;
	if( isEmpty(document.forms[0].patName,"Patient Name")
	    && isEmpty(document.forms[0].patAge,"Age")
		&& comboValidation(document.forms[0].patGenderCode,"Gender")
		&& isEmpty(document.forms[0].patientAddress,"Address")
		&& isEmpty(document.forms[0].patContactNo,"Contact No")
		&& isEmpty(document.forms[0].recordType,"Record Type") 
		&& isEmpty(document.forms[0].requestBy,"Requested By")
		&& isEmpty(document.forms[0].approvedBy,"Approved By")
		
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
function validateWithCrNo()
{
	var valid=false;
	if(  isEmpty(document.forms[0].recordType,"Record Type") 
		&& isEmpty(document.forms[0].requestBy,"Requested By")
		&& isEmpty(document.forms[0].approvedBy,"Approved By")
		
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

function validatePatientDOBAgainstSysDate()
{
	if(document.getElementsByName('isActualDob')[1].checked)
	{
		var ctrlObjPatDOB = document.forms[0].patDOB.Control_Object;
		var elemCurrentDate = document.getElementsByName('sysDate')[0];		
		var flag = ctrlObjPatDOB.validateAgainstDateOnMode(elemCurrentDate.value,"dd-Mon-yyyy",DateValidator.COMPARE_MODES["LessNEqual"],"Date of Birth","Current Date");
		if(flag==true)
		{
			var dateOfBirth = convertStrToDate(document.forms[0].patDOB.value,"dd-MM-yyyy");
			var currentDate = convertStrToDate(elemCurrentDate.value,"dd-Mon-yyyy");
			var ageInYears = dateDifference(currentDate,dateOfBirth,"Y");

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
function clearWithCrForm()
{
	document.getElementsByName("recordType")[0].value="-1";
	document.getElementsByName("recordDesc")[0].value="";
	document.getElementsByName("reqReason")[0].value="";
	document.getElementsByName("requestBy")[0].value="-1";
	document.getElementsByName("requestByName")[0].value="";
	document.getElementsByName("relationship")[0].value="";
	document.getElementsByName("requestedByAddress")[0].value="";
	document.getElementsByName("requestedByContact")[0].value="";
	document.getElementsByName("remarks")[0].value="";
	document.getElementsByName("approvedBy")[0].value="-1";
	ageSelection();
	
}
function clearWithoutCrForm()
{
	document.getElementsByName("patName")[0].value="";
	document.getElementsByName("patAge")[0].value="";
	document.getElementsByName("patAgeUnit")[0].value="Y";
	document.getElementsByName("patientAddress")[0].value="";
	document.getElementsByName("patGenderCode")[0].value="-1";
	document.getElementsByName("patContactNo")[0].value="";
	document.getElementsByName("recordType")[0].value="-1";
	document.getElementsByName("recordDesc")[0].value="";
	document.getElementsByName("reqReason")[0].value="";
	document.getElementsByName("requestBy")[0].value="-1";
	document.getElementsByName("requestByName")[0].value="";
	document.getElementsByName("relationship")[0].value="";
	document.getElementsByName("requestedByAddress")[0].value="";
	document.getElementsByName("requestedByContact")[0].value="";
	document.getElementsByName("remarks")[0].value="";
	document.getElementsByName("approvedBy")[0].value="-1";
	ageSelection();
	
}
</script>
<style>
textarea {
    resize: none;
}
</style>

<body >
		<html:form action="/duplicateRecordPrintReq">
		
				<his:TitleTag name="Duplicate Record Printing Request">	</his:TitleTag>
	<logic:equal name="duplicateRecordPrintReqFB" property="reqType" value="1">
				<his:SubTitleTag name="Patient Details">
				</his:SubTitleTag>
				<table width="100%" border="0"  cellspacing="1" cellpadding="0">
				<tr>
					<td width="25%" class="tdfonthead">
								<div align="right">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<font color="#FF0000">*</font>
								    <bean:message key="patientName" />
									</font>
								</div>
							</td>
							<td width="25%" class="tdfont">
								<div align="left">
								<html:text name="duplicateRecordPrintReqFB" property="patName" onkeypress="return (validateTextArea(event,this,'60') && validateAlphaNumericOnly(event,this))"></html:text>
								</div>
							</td>
							<td width="25%" class="tdfonthead">
									<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font> 
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<bean:message key="age" />
									</font>
							</td>
							<td width="25%" class="tdfont">
									<html:text name="duplicateRecordPrintReqFB" size="4" property="patAge" tabindex="1" maxlength="3" onkeypress="return validateNumeric(event)" /> 
									<html:select name="duplicateRecordPrintReqFB" property="patAgeUnit" tabindex="1" styleClass="smallcombo">
										<logic:present name="<%=MrdConfig.ALL_AGE_TYPE_LIST %>">
											<html:options collection="<%=MrdConfig.ALL_AGE_TYPE_LIST %>" property="value" labelProperty="label" />
										</logic:present>	
									</html:select>
								</td>
						</tr>
						<tr>
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
									<html:select name="duplicateRecordPrintReqFB" property="patGenderCode" tabindex="1" styleClass="regcbo">
										<html:option value="-1">Select Value</html:option>
										<logic:present name="<%=MrdConfig.ESSENTIAL_GENDER_LIST %>">
											<html:options collection="<%=MrdConfig.ESSENTIAL_GENDER_LIST %>" property="value" labelProperty="label" />
										</logic:present>	
									</html:select>
								</div>	
							</td>
							<td width="25%" class="tdfonthead">
								<div align="right">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<font color="#FF0000">*</font>
										<bean:message key="address"/>
									</font>
								</div>
							</td>
							<td width="25%" class="tdfont">
								<div align="left">
								<html:textarea name="duplicateRecordPrintReqFB" property="patientAddress" onkeypress="return (validateTextArea(event,this,'100') && validateAlphaNumericOnly(event,this))"></html:textarea>
								</div>
							</td>
						</tr>
						<tr>
						
						
								<td width="25%" class="tdfonthead">
									<div align="right">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<font color="#FF0000">*</font>
											<bean:message key="contactNo"/>
										</font>
									</div>
								</td>
								<td width="25%" class="tdfont">
									<div align="left">
								<html:text name="duplicateRecordPrintReqFB" property="patContactNo" tabindex="1" maxlength="10" onkeypress="return validateNumeric(event)" /> 
								</div>
								</td>
						</tr>
						</table>
                      </logic:equal>
                      <logic:equal name="duplicateRecordPrintReqFB" property="reqType" value="0">
						<jsp:include page="/inpatient/inpatientDetail.cnt" flush="true"></jsp:include>
				</logic:equal>
						<his:SubTitleTag name="Record Details">
				</his:SubTitleTag>	
					<table width="100%" border="0"  cellspacing="1" cellpadding="0">
						<tr>
							<td width="25%" class="tdfonthead">
								<div align="right">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<font color="#FF0000">*</font>
										<bean:message key="recordType"/> 
									</font>
								</div>
							</td>
							<td width="25%" colspan="1" class="tdfont">
								<div align="left">
									<html:select property="recordType" styleClass="regcbo" tabindex="1" >
										<html:option value="-1">Select Value</html:option>
										<logic:present name="<%=MrdConfig.RECORD_TYPE %>">
										<html:options collection="<%=MrdConfig.RECORD_TYPE %>" property="value" labelProperty="label"/>
										</logic:present>
									</html:select>
								</div>
							</td>
							
							<td width="25%" class="tdfonthead">
								<div align="right">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<font color="#FF0000"></font>
										<bean:message key="recDetail"/>
									</font>
								</div>
							</td>
							<td width="25%" class="tdfont">
								<div align="left">
								<html:textarea name="duplicateRecordPrintReqFB" property="recordDesc" onkeypress="return (validateTextArea(event,this,'100') && validateAlphaNumericOnly(event,this))"></html:textarea>
								</div>
							</td>
							</tr>
							<tr>
							<td width="25%" class="tdfonthead">
								<div align="right">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<font color="#FF0000"></font>
										<bean:message key="reqReason"/>
									</font>
								</div>
							</td>
							<td width="25%" class="tdfont">
								<div align="left">
								<html:textarea name="duplicateRecordPrintReqFB" property="reqReason" onkeypress="return (validateTextArea(event,this,'50') && validateAlphaNumericOnly(event,this))"></html:textarea>
								</div>
							</td>
							
						</tr>
						</table >
						
						<his:SubTitleTag name="Requested By Details">
				        </his:SubTitleTag>
				        <table>
				        <tr>
				        <td width="25%" class="tdfonthead">
									<div align="right">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<font color="#FF0000">*</font>
											<bean:message key="reqBy"/>
										</font>
									</div>
								</td>
					    <td  class="tdfont">
									<div align="left">
											<html:select name="duplicateRecordPrintReqFB" property="requestBy" onchange="showRequestedBy();" tabindex="1" >
												<html:option value="-1">Select Value</html:option>
												<html:option value="<%=MrdConfig.CERTIFICATE_REQ_BY_PATIENT%>">Patient</html:option>
												<html:option value="<%=MrdConfig.CERTIFICATE_REQ_BY_RELATIVE %>">Relative</html:option>
											</html:select>
									</div>
								</td>
								</tr>
								</table>
						<table  id="divRlativeId" width="100%" border="0"  cellspacing="1" cellpadding="0" style="display:none">
							<tr>
								<td width="29%" class="tdfonthead">
									<div align="right">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<font color="#FF0000">*</font>
											<bean:message key="relativename"/>
										</font>
									</div>
								</td>
								<td width="29%" class="tdfont">
									<div align="left">
										<html:text name="duplicateRecordPrintReqFB" property="requestByName" maxlength="60"  tabindex="1" onkeypress="return validateAlphabetsWithDotsOnly(event,this)" ></html:text>
									</div>
								</td>
								<td width="29%" class="tdfonthead">
									<div align="right">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<font color="#FF0000">*</font>
											<bean:message key="realtionship"/>
										</font>
									</div>
								</td>
								<td width="29%" class="tdfont">
									<div align="left">
										<html:select name="duplicateRecordPrintReqFB" property="relationship" tabindex="1" >
											<html:option value="-1">Select Value</html:option>
											<logic:present name="<%=MrdConfig.ESSENTIAL_ALL_RELATION_LIST%>">
												<html:options collection="<%=MrdConfig.ESSENTIAL_ALL_RELATION_LIST %>" property = "value" labelProperty = "label"/>
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
										<html:textarea name="duplicateRecordPrintReqFB" property="requestedByAddress" rows="1" cols="30"  tabindex="1" onkeypress="return (validateTextArea(event,this,'100') && validateAlphaNumericOnly(event,this))" ></html:textarea>
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
										<html:text name="duplicateRecordPrintReqFB" property="requestedByContact" maxlength="30" onkeypress="return validateNumeric(event)" tabindex="1"></html:text>
									</div>
								</td>
							</tr>
						</table>
						
						<his:SubTitleTag name="Request Approval"> </his:SubTitleTag>
						<table width="100%" border="0"  cellspacing="1" cellpadding="0">
						
						<tr>
						<td width="25%" class="tdfonthead">
									<div align="right">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<font color="#FF0000">*</font>
												<bean:message key="approvedBy"/>
										</font>
									</div>
								</td>
								<td width="25%" class="tdfont">
									<div align="left">
										<html:select name="duplicateRecordPrintReqFB" property="approvedBy" tabindex="1" >
											<html:option value="-1">Select Value</html:option>
											<logic:present name="<%=MrdConfig.ESSENTIAL_ALL_MRD_EMP_LIST %>">
												<html:options collection="<%=MrdConfig.ESSENTIAL_ALL_MRD_EMP_LIST %>" property = "value" labelProperty = "label"/>
											</logic:present>
										</html:select>
									</div>
								</td>
								<td width="25%" class="tdfonthead">
								<div align="right">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<font color="#FF0000"></font>
										<bean:message key="remarks"/>
									</font>
								</div>
							</td>
							<td width="25%" class="tdfont">
								<div align="left">
								<html:textarea name="duplicateRecordPrintReqFB" property="remarks" onkeypress="return (validateTextArea(event,this,'100') && validateAlphaNumericOnly(event,this))"></html:textarea>
								</div>
							</td>
						</tr>
						</table>
						
					<his:ButtonToolBarTag>
					<logic:equal name="duplicateRecordPrintReqFB" property="reqType" value="0">
						<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>'  style=cursor:pointer  onclick ="if(validateWithCrNo()) submitPage('SAVE')" onkeypress="if(event.keyCode==13)if(validateWithCrNo())submitPage('SAVE')">
						<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>'  style=cursor:pointer  onclick =" clearWithCrForm()" onkeypress="if(event.keyCode==13)clearWithCrForm()">
					</logic:equal>
					<logic:equal name="duplicateRecordPrintReqFB" property="reqType" value="1">
						<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>'  style=cursor:pointer  onclick ="if(validateWithoutCrNo()) submitPage('SAVE')" onkeypress="if(event.keyCode==13)if(validateWithoutCrNo())submitPage('SAVE')">
						<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>'  style=cursor:pointer  onclick =" clearWithoutCrForm()" onkeypress="if(event.keyCode==13)clearWithoutCrForm()">
					</logic:equal>
						<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer  onclick =" submitPage('CANCEL')" onkeypress="if(event.keyCode==13)submitPage('CANCEL')">
			 		</his:ButtonToolBarTag>
			 		
		        <html:hidden name="duplicateRecordPrintReqFB" property="patCrNo"/>
		         <html:hidden name="duplicateRecordPrintReqFB" property="patDOB"/>
		          <html:hidden name="duplicateRecordPrintReqFB" property="hmode"/>
		               
		          
		          <html:hidden name="duplicateRecordPrintReqFB" property="mode"/>		          
   					<html:hidden name="duplicateRecordPrintReqFB" property="reqType"/>
				</html:form>

</body>


