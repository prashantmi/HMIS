<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@page import="hisglobal.hisconfig.Config"%>
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
<his:css src="/hisglobal/css/calendar-blue2.css"/>

<his:javascript src="/registration/js/validationCommon.js"/>
<his:javascript src="/registration/js/validationCalls.js"/>
<his:javascript src="/registration/js/registration.js"/>
<his:javascript src="/registration/js/commonFunctions.js"/>
<his:javascript src="/registration/js/calendar.js"/>
<his:javascript src="/registration/js/dateFunctions.js"/>


<script>

function validateDepartmentType()
{ var valid=false;
	if(document.getElementsByName("departmentType")[0].value=="-1")
	{
	alert("Select the Type of department")
	valid=false
	}
	else
	{
		valid=true
	}
	
	return valid;
}

function validateSal()
{ 
	var valid=true;
	if(document.getElementsByName('salutationId')[0].value=="-1")
	{
		alert("Please Select Salutation !");
		document.getElementsByName('salutationId')[0].focus();
		valid= false;
	} 
	return valid; 
	
}

function validateCombo()
{ 
	var valid=true;
	  
	if(document.getElementsByName('genderCode')[0].value=="-1")
	{
		alert("Please Select Gender !");
		document.getElementsByName('genderCode')[0].focus();
		valid= false;
	} 
	else if(document.getElementsByName('birthDate')[0].value=="")
	{
		alert("Please Select Date Of Birth !");
		document.getElementsByName('birthDate')[0].focus();
		valid= false;
	}  
	/*else if(document.getElementsByName('countryCode')[0].value=="-1")
	{
		alert("Please Select Nationality !");
		document.getElementsByName('countryCode')[0].focus();
		valid= false;
	} */
	//else if(document.getElementsByName('deptCode')[0].value=="-1")
	//{
	//	alert("Please Select Department !");
	//	document.getElementsByName('deptCode')[0].focus();
	//	valid= false;
	//}
	else if(document.getElementsByName('designationCode')[0].value=="-1")
	{
		alert("Please Select Designation !");
		document.getElementsByName('designationCode')[0].focus();
		valid= false;
	}
		return valid; 
}


function validateEmployeeMaster()
{
	var valid=true;   
	//alert("SAVE");	
	if(validateSal()&& 
	   isEmpty(document.forms[0].firstName,"First Name")&&
	   isEmpty(document.forms[0].lastName,"Last Name")&&
	   validateCombo())		       
	    {
	  	valid=true;
	  	//alert("one");	  		
	  	submitTile('SAVE');
	  	}
	  	  
	else
	{
		valid=false;
		
	}
}
function compareDateCall(d1,d2,mode,l1,l2){
//alert("compare called    "+l1 +"      " +l2);
var valid=true;
if(isEmpty(d1,l1) && isEmpty(d2,l2)){
 if(compareDate(d1,d2, mode)){
		valid = true;
	}
 else {
	alert(l1+" cant be greater than "+l2);
	valid = false;
	}
}

else
valid=false;

return valid;
}

function submitTile(mode)
{
   document.getElementsByName("transactionMode")[0].value=mode;  
   document.forms[0].submit();
}

</script>

<body>
<html:form action="/master/addEmployeeMaster">
<his:TransactionContainer>

<%@ page import ="java.util.*,registration.*,hisglobal.presentation.*" %>

<%String sysDate=WebUTIL.getCustomisedSysDate((Date)session.getAttribute(RegistrationConfig.SYSADATEOBJECT), "dd-MMM-yyyy"); %>
<his:TitleTag name="Personal Details >>Add">			
</his:TitleTag> 


<his:ContentTag>
  <table width="100%" border="0" cellspacing="1" cellpadding="0">   
  
  <tr>
					
					<td width="17%" class="LABEL">
					</td>
					<td width="17%" class="LABEL">
					<div align="left"><font size="2" color="#000000"
						face="Verdana, Arial, Helvetica, sans-serif"
						style="display: block;">
					<bean:message key="mandatory1" /><bean:message key="salutation" /></font></div>	
					</td>
					<td width="17%" height="25" class="LABEL">
					<div align="left"><font size="2" color="#000000"
						face="Verdana, Arial, Helvetica, sans-serif"
						style="display: block;"><bean:message
						key="mandatory1" /> <bean:message key="first" /></font></div></td>
					<td width="17%" class="LABEL">
					<div align="left"><font size="2" color="#000000"
						face="Verdana, Arial, Helvetica, sans-serif"
						style="display: block;"> <bean:message key="middle" /> </font></div>
					</td>
					<td width="17%" class="LABEL">
					<div align="left"><font color="#000000" size="2"
						face="Verdana, Arial, Helvetica, sans-serif"><bean:message
						key="mandatory1" /><bean:message
						key="last" /> </font></div>
					
					</td>
					<td width="15%" class="LABEL">
					
					</td>
					
				</tr>
				<tr>
					<td width="17%" class="LABEL">
					<bean:message key="employee" /> <bean:message key="name" />				
					</td>
					<td class="tdfont" width="17%">
					<html:select name="EmployeeMasterFB" tabindex="1" property="salutationId" styleClass="regCbo">
					<html:option value="-1">Select Value</html:option>
					<html:options collection="<%=RegistrationConfig.ESSENTIALBO_OPTION_SALUTATION%>" property="value" labelProperty="label" />
					</html:select>
					</td>
					<td width="17%" class="tdfont" align="left"><html:text
						name="EmployeeMasterFB" property="firstName"
						styleClass="textbox" tabindex="1" maxlength="30"
						onchange="isAlpha(this,'First Name')"
						onkeypress="return validateAlphabetsWithDotsAndForwardSlashWithHypen(event,this)" /></td>
					<td width="17%" class="tdfont"><html:text
						name="EmployeeMasterFB" property="middleName"
						tabindex="2" styleClass="textbox"
						onchange="isAlpha(this,'Middle Name')" maxlength="20"
						onkeypress="return validateAlphabetsWithDotsOnly(event,this)"
						style="display:block;" /></td>
					<td width="17%" class="tdfont" align="left"><html:text
						name="EmployeeMasterFB" property="lastName"
						tabindex="2" styleClass="textbox" tabindex="1" maxlength="30"
						onchange="isAlpha(this,'Last Name')"
						onkeypress="return validateAlphabetsWithDotsOnly(event,this)" />
					</td>
					<td width="15%" class="tdfont"></td>
					
				</tr>
				
				<tr>
					<td width="17%" class="LABEL">
					<bean:message key="mandatory1" /><bean:message key="gender" />					
					</td>
					<td class="tdfont" width="17%">
					<html:select name="EmployeeMasterFB" tabindex="1" property="genderCode" styleClass="regCbo">
					<html:option value="-1">Select Value</html:option>										
					<html:options collection="<%=RegistrationConfig.ESSENTIALBO_OPTION_GENDER%>" property="value" labelProperty="label" />
					</html:select>
					</td>
					<td width="17%" class="LABEL">
					<bean:message key="mandatory1" /><bean:message key="dob" /></td>
					<td width="17%" class="tdfont">
					<his:date name='<%="birthDate"%>' dateFormate="%d-%b-%Y" />					
					</td>
					<td width="17%" class="LABEL">
					<bean:message key="mandatory1" /><bean:message key="designation" /></td>
					<td width="15%" class="tdfont">
					<html:select name="EmployeeMasterFB" tabindex="1" property="designationCode" styleClass="regCbo">
					<html:option value="-1">Select Value</html:option>										
					<html:options collection="<%=RegistrationConfig.ESSENTIALBO_OPTION_DESIGNATION%>" property="value" labelProperty="label" />
					</html:select></td>
					
				</tr>
				<tr>
					<td width="17%" class="LABEL">
					<bean:message key="department" /></td>
					<td width="17%" class="tdfont">
					<html:select name="EmployeeMasterFB" tabindex="1" property="deptCode" styleClass="regCbo">
					<html:option value="-1">Select Value</html:option>										
					<html:options collection="<%=RegistrationConfig.ESSENTIALBO_OPTION_ALL_DEPARTMENT%>" property="value" labelProperty="label" />
					</html:select></td>
					<td width="17%" class="LABEL">
					<bean:message key="contactNo" />					
					</td>
					<td class="tdfont" width="17%">
					<html:text name="EmployeeMasterFB"  maxlength="20" styleClass="textbox" property="contactNo" onkeypress="return validateNumeric(event)"/>	     
					</td>					
					<td width="17%" class="LABEL"></td>
					<td width="15%" class="tdfont">
					</td>
					
				</tr>
  
   </table>	
</his:ContentTag> 

<html:hidden name="EmployeeMasterFB" property="transactionMode" />
<his:ButtonToolBarTag>   
	<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>' style=cursor:pointer tabindex="1" onclick ="validateEmployeeMaster()" onkeypress="if(event.keyCode==13) validateEmployeeMaster();">
    <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style=cursor:pointer tabindex="1" onclick ="submitTile('CANCEL')" onkeypress="if(event.keyCode==13) submitTile('CANCEL')">
    <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style=cursor:pointer tabindex="1" onclick ="submitTile('CLEAR')" onkeypress="if(event.keyCode==13) submitTile('CLEAR');">          	
</his:ButtonToolBarTag>

</his:TransactionContainer>
 <his:status/> 
</html:form>
</body>
</html>