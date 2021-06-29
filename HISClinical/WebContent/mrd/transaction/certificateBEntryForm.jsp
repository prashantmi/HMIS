<!-- 
## Copyright Information		: 	C-DAC, Noida  
## Project Name					: 	HIS-NIMS
## Name of Developer		 	: 	Manisha Gangwar
## Module Name					: 	MRD
## Process/Database Object Name	:	Certificate B Entry Form Request
## Purpose						:	To raise a request for issueing Certificate B by MRD department
## Date of Creation				: 	14-October-2015
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

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<his:css src="/hisglobal/css/calendar-blue2.css" />
<his:javascript src="/registration/js/registration.js" />
<his:javascript src="/registration/js/calendar.js" />
<his:javascript src="/registration/js/validationCommon.js" />
<his:javascript src="/registration/js/validationCalls.js" />
<his:javascript src="/hisglobal/js/commonFunctions.js" />
<his:javascript src="/registration/js/popup.js" />
<his:javascript src="/registration/js/dateFunctions.js" />
<his:javascript src="/registration/js/commonFunctions.js" />

<his:javascript src="/hisglobal/utility/generictemplate/js/date_validator.js"/>
<his:css src="/hisglobal/css/Color.css" />
<his:css src="/hisglobal/css/master.css" />
<his:css src="/hisglobal/css/hisStyle.css" />
<his:css src="/hisglobal/css/hisStyleExt.css" />
<his:css src="/hisglobal/css/tab.css"/>
<his:javascript src="/hisglobal/js/utilityFunctions.js" />
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<script type="text/javascript">

<%-- window.onload=function()
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
} --%>
function submitPage(mode)
{   //alert("Hi I am in submit");
	document.getElementsByName("hmode")[0].value=mode;
	document.forms[0].submit();
}

function validateSave()
{
	
	var approved=document.getElementsByName("empNo")[0].value;
	
	
		if(approved=="-1")
		{
			document.getElementsByName("empNo")[0].focus();
			//document.forms[0].relationCode[0].focus();
			alert("Please Select Approved By");
			return false;
		}
		else
		{
			return true;			
		}
	}
	



/* function validateAgeOrDob()
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
} */

/* function validateWithoutCrNo()
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
		&& isEmpty(document.forms[0].empNo,"Approved By")
		
	)
	{
		valid=true;
	}
	else
	{
		valid=false;
	}
	return valid;	
} */
/* function validateWithCrNo()
{
	var valid=false;
	if(  isEmpty(document.forms[0].recordType,"Record Type") 
		&& isEmpty(document.forms[0].requestBy,"Requested By")
		&& isEmpty(document.forms[0].empNo,"Approved By")
		
	)
	{
		valid=true;
	}
	else
	{
		valid=false;
	}
	return valid;	
} */

/* function validatePatientDOBAgainstSysDate()
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
} */
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
	document.getElementsByName("empNo")[0].value="-1";
	ageSelection();
	
}
/* function clearWithoutCrForm()
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
	document.getElementsByName("empNo")[0].value="-1";
	ageSelection();
	
} */
</script>
<style>
textarea {
    resize: none;
}
</style>

<body >
		<html:form action="/certificateBEntryForm">
		
				<his:TitleTag name="Certificate B Entry Form Request">	</his:TitleTag>
						<jsp:include page="/inpatient/inpatientDetail.cnt" flush="true"></jsp:include>
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
										<html:select name="CertificateBEntryFormFB" property="empNo" tabindex="1" >
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
								<html:textarea name="CertificateBEntryFormFB" property="remarks" onkeypress="return (validateTextArea(event,this,'100') && validateAlphaNumericOnly(event,this))"></html:textarea>
								</div>
							</td>
						</tr>
						</table>
						
					<his:ButtonToolBarTag>
					<logic:equal name="CertificateBEntryFormFB" property="reqType" value="0">
						<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>'  style=cursor:pointer  onclick ="if(validateSave()) submitPage('SAVE')" onkeypress="if(event.keyCode==13) if(validateSave()) submitPage('SAVE')">
									
						<%-- <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>'  style=cursor:pointer  onclick ="clearWithCrForm()" onkeypress="if(event.keyCode==13) clearWithCrForm()">
				 --%>	</logic:equal>
					<%-- 
						<logic:equal name="CertificateBEntryFormFB" property="reqType" value="0">
						<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>'  style=cursor:pointer  onclick ="if(validateWithCrNo()) submitPage('SAVE')" onkeypress="if(event.keyCode==13)if(validateWithCrNo())submitPage('SAVE')">
									
						<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>'  style=cursor:pointer  onclick =" clearWithCrForm()" onkeypress="if(event.keyCode==13)clearWithCrForm()">
					</logic:equal> --%>
				
						<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer  onclick ="submitPage('ADD')" onkeypress="if(event.keyCode==13)submitPage('ADD')">
			 		</his:ButtonToolBarTag>
			 		
		        <html:hidden name="CertificateBEntryFormFB" property="patCrNo"/>
		         <html:hidden name="CertificateBEntryFormFB" property="patDOB"/>
		          <html:hidden name="CertificateBEntryFormFB" property="hmode"/>
		          <html:hidden name="CertificateBEntryFormFB" property="mode"/>		          
   					<html:hidden name="CertificateBEntryFormFB" property="reqType"/>
				</html:form>

</body>


