<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@page import="hisglobal.hisconfig.Config"%>
<%@page import="registration.controller.fb.EmgPatDetailModificationFB"%>
<%@page import ="java.util.*,registration.*" %>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>
<html>

<head> 
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="pragma" content="no-cache">
<title>Emergency Patient Detail Modification</title>


<link href="../hisglobal/css/Color.css" rel="stylesheet" type="text/css">
<link href="../hisglobal/css/master.css" rel="stylesheet" type="text/css">
<link href="../hisglobal/css/hisStyle.css" rel="stylesheet" type="text/css">
<link href="../hisglobal/css/calendar-blue2.css" rel="stylesheet" type="text/css">

<his:javascript src="/registration/js/registration.js"/>
<his:javascript src="/registration/js/validationCalls.js"/>
<his:javascript src="/registration/js/validationCommon.js"/>
<his:javascript src="/registration/js/commonFunctions.js"/>
<his:javascript src="/registration/js/calendar.js"/>
<his:javascript src="/registration/js/stateChangeAjax.js" />
<his:javascript src="/registration/js/cityLocationAjax.js" />
<his:javascript src="/registration/js/popup.js"/>
<his:css src="/hisglobal/css/calendar-blue2.css"/>

<script language="JavaScript" src="/HISClinical/registration/js/registration.js"></script>
<script language="JavaScript" src="/HISClinical/registration/js/validationCalls.js"></script>
<script language="JavaScript" src="/HISClinical/registration/js/validationCommon.js"></script>
<script language="JavaScript" src="/HISClinical/registration/js/commonFunctions.js"></script>
<script language="JavaScript" src="/HISClinical/registration/js/dateFunctions.js"></script>
<script language="JavaScript" src="/HISClinical/registration/js/calendar.js"></script>
<script language="JavaScript" src="/HISClinical/registration/js/popup.js"></script>
<script language="JavaScript" src="/HISClinical/hisglobal/js/utilityFunctions.js"></script>
<script language="JavaScript" src="/HISClinical/hisglobal/utility/generictemplate/js/validationFunctions.js"></script>
<script language="JavaScript" src="/HISClinical/hisglobal/utility/generictemplate/js/date_validator.js"></script>

<script language="JavaScript" src="/HISClinical/hisglobal/js/multilingualSupport/pramukhime.js"></script>
<script language="JavaScript" src="/HISClinical/hisglobal/js/multilingualSupport/pramukhindic.js"></script>
<script language="JavaScript" src="/HISClinical/hisglobal/js/multilingualSupport/pramukhime-common.js"></script>
<script language="JavaScript" src="/HISClinical/hisglobal/js/multilingualSupport/multilingualSupport.js"></script>
 
<%!
   String divdisplay="\"\"";
   String divnddisplay="\"\"";
   String divisrefdisplay="\"\"";
   String divRefGnctdHospitalCode="\"\"";
   String divRefHospname="\"\"";
   String strdivage="\"\"";
   String divref="\"\"";
   String strdivdob="\"\"";
   String RefGnctdHosNameradio=""; 
   String RefGnctdHoscoderadio=""; 
   String divDocname="\"\"";
   String divDocTitle="\"\"";
   String divBroughtByDisplay="\"\"";
   String gnctdrefDetails = "\"\"";
  String divRefInstitute ="\"\"";
  
%>

<script>
function Isreferred(elem)
 {
  	if(elem.checked){
//alert("isref true");
// 	 	document.getElementById('divReferred').style.display="";
		document.getElementsByName("patRefGnctdHospitalCode")[0].value="-1";
		document.getElementsByName("patRefHospitalName")[0].value="";
 	if(document.getElementsByName('referringInstType')[0].checked){
// alert("gnctd true checking");
        //document.getElementsByName('referringInstType')[0].checked="true";    
		document.getElementById('divRefHosname').style.display="none";
		document.getElementById('divRefHosCode').style.display="";
		document.getElementById('divReferredInstitute').style.display="";
		document.getElementById('divReferred').style.display="";
		document.getElementsByName("patRefDoctor")[0].value="";
		document.getElementsByName("patRefGnctdHospitalDept")[0].value="";
		document.getElementsByName("patRefGnctdHospitalDeptUnit")[0].value="";
		document.getElementsByName("patRefGnctdHospitalCrno")[0].value="";
		//alert("ref doct value"+document.getElementsByName("patRefDoctor")[0].value);
		}
		else{
	//	alert("other");
		document.getElementById('divRefHosname').style.display="";
		document.getElementById('divRefHosCode').style.display="none";
		document.getElementById('divReferredInstitute').style.display="";

		document.getElementById('divReferred').style.display="none";
		document.getElementsByName("patRefDoctor")[0].value="";
		//alert("ref doct value"+document.getElementsByName("patRefDoctor")[0].value);

		//document.getElementById('divReferred').style.display="none";
		document.getElementById('divReferred').style.display="";
		document.getElementsByName("patRefGnctdHospitalDept")[0].value="";
		document.getElementsByName("patRefGnctdHospitalDeptUnit")[0].value="";
		document.getElementsByName("patRefGnctdHospitalCrno")[0].value="";
		}
		var client= "<%=Config.CLIENT%>";
 		//alert(client)
 		//if(client=="<%=Config.CLIENT_PGIMER%>"){
 			document.getElementsByName("patRefGnctdHospitalDept")[0].value=""; 		
 		//}
		document.getElementById('divDocTitle').style.display="";
		document.getElementById('divDocName').style.display="";
  	}
 	
 	else
 	{
	 	document.getElementById('divRefHosname').style.display="none";
		document.getElementById('divRefHosCode').style.display="none";
		document.getElementById('divReferredInstitute').style.display="none";
		//document.getElementById('divReferredByDoc').style.display="none";
		//document.getElementById('divReferredByDocText').style.display="none";
		document.getElementById('divReferred').style.display="none";
		document.getElementById('divDocTitle').style.display="none";
		document.getElementById('divDocName').style.display="none";
	 }
 }




function checkReferDepartment()
{
	//if(<%=Config.CLIENT%>==<%=Config.CLIENT_PGIMER%>)
	if(document.getElementsByName('patRefGnctdHospitalDept')[0])
	{
		if(document.getElementsByName('patRefGnctdHospitalDept')[0].value=='0')
		{
			document.getElementsByName('patRefHospitalDeptOther')[0].disabled=false
			//document.getElementsByName('patRefHospitalDeptOther')[0].value=""
		}
		else
		{
			document.getElementsByName('patRefHospitalDeptOther')[0].value=""
			document.getElementsByName('patRefHospitalDeptOther')[0].disabled=true
		}
	}	
}

function broughtBy(obj)
{
// alert(document.getElementsByName("isRelative")[0].value)
	if(obj.checked==true)
	{
	// alert("inside brought by if")
		document.getElementById("broughtById").style.display="";
	}
	else
	{
	// 	alert("inside brought by else")
		document.getElementById("broughtById").style.display="none";
	}
}

function checkEmployee()
{
	if(document.forms[0].patPrimaryCatCode)
	{
 			//var patIdNo=document.getElementsByName("patIdNo")[0].value;
			var patCategoryCode=document.forms[0].patPrimaryCatCode.value
			var patEmployeeCode="<%=Config.PRIMARY_CATEGORY_EMPLOYEE_CODE %>";
			// alert(patIdNo)
			var clientValue="<%=Config.CLIENT%>"; 
	 		var clientPGIMER="<%=Config.CLIENT_PGIMER%>";
	 		var fieldEditableValue="<%=Config.PATCAT_EMPLOYEE_FIELD_VALUE%>";
	 		var fieldEditableFalse="<%=Config.PATCAT_EMPLOYEE_FIELD_EDITABLE_FALSE%>";
	 		var dataFromTableValue="<%=Config.PATCAT_EMPLOYEE_DATA_FROM_TABLE_VALUE%>";
	 		var dataFromTableTrue="<%=Config.PATCAT_EMPLOYEE_DATA_FROM_TABLE_TRUE%>";
		 if( (patCategoryCode==patEmployeeCode) && ((fieldEditableValue==fieldEditableFalse) || (dataFromTableValue==dataFromTableTrue) ) )
		 {
				  document.getElementsByName("patFirstName")[0].disabled=true;
				  document.getElementsByName("patMiddleName")[0].disabled=true;
				  document.getElementsByName("patLastName")[0].disabled=true;
				  document.getElementsByName("isActualDob")[0].disabled=true;
				  document.getElementsByName("patAge")[0].disabled=true;
				  document.getElementsByName("patAgeUnit")[0].disabled=true;
				  document.getElementsByName("patDOB")[0].value="";
				  document.getElementsByName("patDOB")[0].disabled=true;
				  document.getElementsByName("patGenderCode")[0].disabled=true;
				  document.getElementsByName("patGuardianName")[0].disabled=true;
				  document.getElementsByName("patHusbandName")[0].disabled=true;
				  document.getElementsByName("patMotherName")[0].disabled=true;
				  //document.getElementsByName("patMaritalStatusCode")[0].disabled=true;
				  document.getElementsByName("patCasteCode")[0].disabled=true;
				  document.getElementsByName("patNationalityCode")[0].disabled=true;
				  document.getElementsByName("patReligionCode")[0].disabled=true;
				  document.getElementsByName("patIsUrban")[0].disabled=true;
				  document.getElementsByName("patMonthlyIncome")[0].disabled=true;
				  //if(clientValue==clientPGIMER)
				  {
				  	  document.getElementsByName("patNickName")[0].disabled=true;
					  //document.getElementsByName("patOccupation")[0].disabled=true;
					  //document.getElementsByName("patFatherOccupation")[0].disabled=true;
					  //document.getElementsByName("patHusbandOccupation")[0].disabled=true;
					  //document.getElementsByName("patCardNo")[0].disabled=true;
				  }
				  //else{}
				  document.getElementsByName("patAddCountryCode")[0].disabled=true;
				  document.getElementsByName("patAddStateCode")[0].disabled=true;
				  document.getElementsByName("patAddStateName")[0].disabled=true;
				//  document.getElementsByName("patAddCityLocCode")[0].disabled=true;
				//  document.getElementsByName("patAddCityLoc")[0].disabled=true;
				  document.getElementsByName("patAddHNo")[0].disabled=true;
				  document.getElementsByName("patAddStreet")[0].disabled=true;
				  document.getElementsByName("patAddDistrict")[0].disabled=true;
				  document.getElementsByName("patAddDistrictCode")[0].disabled=true;
				  document.getElementsByName("patAddCity")[0].disabled=true;
				  document.getElementsByName("patAddPIN")[0].disabled=true;
				  document.getElementsByName("patAddContactNo")[0].disabled=true;
				  //document.getElementsByName("patAddCountryCode")[0].disabled=true;
			}
	}
}



function enableRelation(obj)
{	
	 
	
	if(obj.value=="1")
	{
		document.getElementById("nameBroughtBy").innerHTML="<font color='#FF0000' size='2' >*</font><font color='#000000' size='2' face='Verdana, Arial, Helvetica, sans-serif'>  Name";
		document.getElementsByName("broughtByRelationCode")[0].disabled=false;
		document.getElementById("phoneBroughtByDiv").innerHTML=" <font color='#000000' size='2' face='Verdana, Arial, Helvetica, sans-serif'> Phone No.";			
	}
	else 
	{
		document.getElementById("nameBroughtBy").innerHTML="<font color='#FF0000' size='2' >*</font><font color='#000000' size='2' face='Verdana, Arial, Helvetica, sans-serif'>  Name";
		document.getElementsByName("broughtByRelationCode")[0].disabled=true;
		document.getElementById("phoneBroughtByDiv").innerHTML="<font color='#000000' size='2' face='Verdana, Arial, Helvetica, sans-serif'> Phone No.";			
		
	}
	if(obj.value=="2"){
		document.getElementById("nameBroughtBy").innerHTML="<font color='#FF0000' size='2' >*</font> <font color='#000000' size='2' face='Verdana, Arial, Helvetica, sans-serif'> Name";
		document.getElementById("policeDetailDiv").style.display="block";
		document.getElementById("phoneBroughtByDiv").innerHTML="<font color='#000000' size='2' face='Verdana, Arial, Helvetica, sans-serif'> Phone No.";	
	}
	else{
		document.getElementById("nameBroughtBy").innerHTML="<font color='#FF0000' size='2' >*</font> <font color='#000000' size='2' face='Verdana, Arial, Helvetica, sans-serif'> Name";
		document.getElementById("policeDetailDiv").style.display="none";
		document.getElementById("phoneBroughtByDiv").innerHTML="<font color='#000000' size='2' face='Verdana, Arial, Helvetica, sans-serif'> Phone No.";
	}
	// in order to change the label "name" of brought by to EMT name & to diplay the combo for vehicle in case of choice by 108
	if(obj.value=="3"){
		document.getElementById("nameBroughtBy").innerHTML="<font color='#FF0000' size='2' >*</font> <font color='#000000' size='2' face='Verdana, Arial, Helvetica, sans-serif'> EMT Name</font>";
		document.getElementById("ambulanceDtlDiv").style.display="block";
		document.getElementById("phoneBroughtByDiv").innerHTML="<font color='#FF0000' size='2' >*</font><font color='#000000' size='2' face='Verdana, Arial, Helvetica, sans-serif'> EMT Phone No.";
	}
	else{
		document.getElementById("ambulanceDtlDiv").style.display="none";

	}
	
}
function enableSpouseField()
{
 //alert("sdsdsdsdsdsds")
	var maritalStatus=document.getElementsByName("patMaritalStatusCode")[0].value;
	
	var maritalStatusSingle="<%=RegistrationConfig.REGISTRATIONDESK_MARITAL_STATUS_SINGLE%>";
	var clientValue="<%=Config.CLIENT%>";
	 var clientPGIMER="<%=Config.CLIENT_PGIMER%>";
	// alert("maritalStatus "+maritalStatus+" maritalStatusSingle  "+maritalStatusSingle)
	if( (maritalStatus=="-1") || (maritalStatus==maritalStatusSingle) || (document.getElementsByName("isUnknown")[0].checked==true) )
	{
		document.getElementsByName("patHusbandName")[0].disabled=true;
		if(clientValue==clientPGIMER)
		{
		//alert("enableSpouseField")
		document.getElementsByName("patHusbandOccupation")[0].disabled=true;
		}
		
	}
	else
	{
	// alert("elsse")
		document.getElementsByName("patHusbandName")[0].disabled=false;
		if(clientValue==clientPGIMER)
		{
		document.getElementsByName("patHusbandOccupation")[0].disabled=false;
		}
		// alert(document.getElementsByName("patHusbandName")[0].disabled)
	}
}
   

function checkIsrefer(){
	var flag=false;
	
	if(document.getElementsByName("isReferred")[0].checked){
	//	alert("inst value="+document.getElementsByName("referringInstType")[0].value); 
		if(document.getElementsByName("referringInstType")[0].checked){ 
				flag=isSelected(document.forms[0].patRefGnctdHospitalCode,"Referring institution");
			}
		else{
			flag=isEmpty(document.forms[0].isReferred,"Referring institution");
		}
	}
	else{ 
		flag=true;
	}
	return flag;
}

 function validateBroughtByPolice()
 {
 	valid=true
 	if(document.forms[0].isRelative.value== <%=RegistrationConfig.PATIENT_BROUGHT_BY_POLICE %>)
 	{
 		if(isEmpty(document.getElementsByName('constableDesig')[0],'Designation' ) 
 		//&& isEmpty(document.getElementsByName('constableBadgeNo')[0],'Batch No.')
 		)
 		{
 			valid=true
 		}
 		else
 		{
 			valid=false
 		}
 	}
 	return valid
 }
 
 function validateBroughtBy()
 {
 	if( document.forms[0].isBroughtBy.checked)
 	{
	 	if(comboValidation(document.forms[0].isRelative,"Brought By") &&
	 	isEmpty(document.getElementsByName('broughtByName')[0],'Brought By Name') &&
	 	isEmpty(document.getElementsByName('broughtByLocation')[0],'Brought From Location') && 
	 	validateBroughtByPolice())
	 	{
	 		return true
	 	}
	 	else
	 	{
	 		return false
	 	}
	 }
	 else
	 {
	 	return true
	 }
 }


// Changed on 30-Oct-2009
function ValidateEmgReg(){
	// alert("emg valid");
	if(document.getElementsByName("isBroughtDead")[0].checked==true && document.forms[0].isBroughtBy.checked==false){
		alert("Please Select Is Brought By");
		return false;
		
	}
	
	
 if(isSelected(document.forms[0].departmentUnitCode,"Unit")
 	&& isSelected(document.forms[0].patRegCatCode,"Emergency Type")
 	&& isSelected(document.forms[0].patPrimaryCatCode,"Patient Category")	
 	&& isEmpty(document.forms[0].patFirstName,"First Name") 
 	&& validateDot(document.forms[0].patFirstName,"First Name")
 	&& validateSpecialCharacter('/',document.forms[0].patFirstName,"First Name")
	&& validateSpecialCharacter('-',document.forms[0].patFirstName,"First Name")
	//&& validateDot(document.forms[0].patMiddleName,"Middle Name")
	&& isEmpty(document.forms[0].patLastName,"Last Name")
	&& validateDot(document.forms[0].patLastName,"Last Name")
 	&& checkAgeOrDob()
 	&& isValid(document.forms[0].patAge,"Age",125) 
 	&& isEmpty(document.forms[0].patAgeUnit,"Age Unit")

	//&& validateDateAgainstSysDate(document.forms[0].patDOB,"Date of Birth")
	&& validatePatientDOBAgainstSysDate()

    && isSelected(document.forms[0].patGenderCode,"Gender")
    //&& isEmpty(document.forms[0].patGuardianName,"Father's Name")
    && validateFatherNameSpouseName()
    && validateDot(document.forms[0].patGuardianName,"Father Name")
  	&& isEmpty(document.forms[0].patNationalityCode,"Nationality ")
  	&& validateMonthlyIncome(document.getElementsByName("patMonthlyIncome")[0],'<%=RegistrationConfig.BPL_MONTHLY_INCOME%>','<%=Config.PRIMARY_CATEGORY_POOR_FREE_CODE%>')
  	&& isEmpty(document.forms[0].patIdMark1,"Id Mark 1")
  	&& isEmpty(document.forms[0].patIdMark2,"Id Mark 2")
  	&& isSelected(document.forms[0].patAddCountryCode,"Country")
	&& chackAdhaarID()	
	&& checkAnotherID()	 
	&& checkState() 	
	// && validateLocation()
	 && validateDot(document.forms[0].patAddCityLoc,"Location")
	&& validateDot(document.forms[0].patAddHNo,"House Number")
	&& validateDot(document.forms[0].patAddStreet,"Street")
	&& validateDot(document.forms[0].patAddDistrict,"District")
	&& isEmpty(document.forms[0].patAddCity,"City / Village")
	&& validateDot(document.forms[0].patAddCity,"City / Village")
	&& validateMinimumLength(document.forms[0].patAddPIN,'Pin Number','6')
	&& validatePinNumber(document.forms[0].patAddPIN)
	//&& isEmpty(document.forms[0].patAddContactNo,"Contact No")
	&& validateBroughtBy()
	&& checkIsrefer()
	&& isEmpty(document.forms[0].patRefDoctor,"Reffered By Doctor Name")
	)
    {
		// Setting Date from format 'dd-mm-yyyy' format to 'dd-Mon-yyyy'
		var patPriCode = document.getElementsByName("patPrimaryCatCode")[0].value;
		var patEmployeeCode = "<%=Config.PRIMARY_CATEGORY_EMPLOYEE_CODE %>";
		//if(valid==true) 
		if(patPriCode!=patEmployeeCode)
		{
			//alert(document.forms[0].patDOB.value);
			if(document.forms[0].patDOB.value!="")
			{
				var dateOfBirth = convertStrToDate(document.forms[0].patDOB.value,"dd-MM-yyyy");
				document.forms[0].patDOB.maxLength = 11;
				document.forms[0].patDOB.value = convertDateToStr(dateOfBirth,"dd-Mon-yyyy");
			}	
		}
		//alert(document.forms[0].patDOB.value+"   "+valid);
	      return true;
    }
  else
  return false;     
}

function checkAnotherID()
{
	var valid=true;
	if(document.getElementsByName("patDocType")[0].value!="-1")
	{
		if (document.getElementsByName("patCardNo")[0].value=="")
		{
			alert("Plaese Enter the Card No..!");
			document.getElementsByName("patCardNo")[0].focus();
			valid=false;
		}
		
	}
	return valid;
}

function chackAdhaarID()
{
	var valid=true;
	if(document.getElementsByName("patNationalId")[0].value!="")
	{
		//alert(document.getElementsByName("patNationalId")[0].value.length);
		if (document.getElementsByName("patNationalId")[0].value.length!=12)
		{
			alert("Plaese Enter exact 12 Characters in Adhaar No..!");
			document.getElementsByName("patNationalId")[0].focus();
			valid=false;
		}
		
	}
	return valid;
}

// Added on 30-Oct-2009
function validatePatientDOBAgainstSysDate()
{
	if(document.getElementsByName('isActualDob')[1].checked)
	{
		//DateValidator.prototype.validateAgainstDateOnMode = function(_givenDate, _givenFormat, _mode, _dateName, _givenDateName)
		var ctrlObjPatDOB = document.forms[0].patDOB.Control_Object;
		//alert(ctrlObjPatDOB);
		var elemCurrentDate = document.getElementsByName('sysDate')[0];		
		var flag = ctrlObjPatDOB.validateAgainstDateOnMode(elemCurrentDate.value,"dd-Mon-yyyy",DateValidator.COMPARE_MODES["LessNEqual"],"Date of Birth","Current Date");
		//alert(flag);
		if(flag==true)
		{
			var dateOfBirth = convertStrToDate(document.forms[0].patDOB.value,"dd-MM-yyyy");
			var currentDate = convertStrToDate(elemCurrentDate.value,"dd-Mon-yyyy");
			//alert(dateOfBirth);
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

function dateDifference(_bigDt,_smallDt,_format)
{
	var diff = null;
	if(_bigDt < _smallDt)	return diff;
	
	if(_format=="Y")	// Years
	{
		diff = _bigDt.getFullYear() - _smallDt.getFullYear();
		_smallDt.setYear(_smallDt.getFullYear()+diff);
		if(_smallDt>_bigDt)	diff--;
	}
	else if(_format=="M")	// Months
	{
		var diffYears = _bigDt.getFullYear() - _smallDt.getFullYear();
		_smallDt.setYear(_smallDt.getFullYear()+diffYears);
		if(_smallDt>_bigDt)
		{	
			diffYears--;
			_smallDt.setYear(_smallDt.getFullYear()-1);
			//alert(_smallDt>_bigDt);
			var diffMonths = _bigDt.getMonth() + 12 - _smallDt.getMonth();
		}
		else
		{
			var diffMonths = _bigDt.getMonth() - _smallDt.getMonth();
		}
		_smallDt.setMonth(_smallDt.getMonth()+diffMonths);
		if(_smallDt>_bigDt)	diffMonths--;
		
		diff = (diffYears*12) + diffMonths;
	}
	else if(_format=="D")	// Days
	{
		diff = Math.ceil((_bigDt-_smallDt)/(1000*60*60*24));
	}
	else if(_format=="H")	// Hours
	{
		diff = Math.ceil((_bigDt-_smallDt)/(1000*60*60));
	}
	else if(_format=="MI")	// Minutes
	{
		diff = Math.ceil((_bigDt-_smallDt)/(1000*60));
	}
	else if(_format=="S")	// Seconds
	{
		diff = Math.ceil((_bigDt-_smallDt)/(1000));
	}
	return diff;
}

// Changed on 30-Oct-2009
function checkAgeOrDob()
{
	var valid=true;
	//alert(document.getElementsByName("isActualDob")[0].value);
	if(document.getElementsByName("isActualDob")[0].checked)
	{
		valid=isEmpty(document.forms[0].patAge,"Age");
	}
	else
	{
		valid=isEmpty(document.forms[0].patDOB,"Date of Birth");

		// Validate Date of Birth
		if(!DateValidator.validate(document.forms[0].patDOB,"Date of Birth"))
			valid = false;
	}
	if(!valid)	return valid;

	// Senior Citizen Check
	var seniorCitizenCode = '<%=Config.PRIMARY_CATEGORY_SENIOR_CITIZEN%>';
	var seniorCitizenAge = '<%=Config.SENIOR_CITIZEN_AGE%>';
	//alert(seniorCitizenCode);
	if(document.forms[0].patPrimaryCatCode.value==seniorCitizenCode)
	{
		//alert("validation for senior citizen");
		if(!validateForSeniorCitizen(parseInt(seniorCitizenAge)))	valid=false;
	}
	return valid;
}

function submitTile(mode){
doHomeWork();                // in case of unknown remove the disabled field
elem = document.getElementsByName('hmode')[0];
	elem.value = mode;
	//alert(elem.value);
	//alert("unknown on submit"+document.getElementsByName("isUnknown")[0].value);
	document.forms[0].submit();
}

function changeToUnknown(elem1)
{ //alert("script unknown");
	/*if(elem1.checked)
	{
		document.getElementsByName("patFirstName")[0].value="Unknown";

 	    if( document.getElementsByName('isAddressDelhi')[0].checked){
 	       document.getElementsByName("patAddCityLocCode")[0].value="<%=RegistrationConfig.REGISTRATIONDESK_UNKNOWN_LOCATION_CODE%>";
 	       document.getElementsByName("patPrimaryCatCode")[0].value="<%=RegistrationConfig.REGISTRATIONDESK_UNKNOWN_PRIMARY_CATEGORY%>";
           }  
	}	
	else {*/
		document.getElementsByName("patFirstName")[0].value="";
		document.getElementsByName("patFirstName")[0].disabled=false;
		if( document.getElementsByName('isAddressDelhi')[0].checked)
 	    {
		document.getElementsByName("patAddCityLocCode")[0].value="-1";
		document.getElementsByName("patAddCityLocCode")[0].disabled=false;
		document.getElementsByName("patAddStateCode")[0].value="<%=RegistrationConfig.REGISTRATIONDESK_DEFAULT_STATE_CODE %>";   
        }
        else{
        document.getElementsByName("patAddCityLoc")[0].value="";
   		document.getElementsByName("patAddCityLocCode")[0].disabled=false;
        }
	 
	
}


function submit4image(){
	//elem = document.getElementsByName('hmode')[0];
	//elem.value = "REFRESHFORIMAGE"; 
	//document.forms[0].submit();
}


function  showdelhidivOnLoad()
  {

      /* <logic:equal name="EmgPatDetailModificationFB" property="isAddressDelhi" value="1">
	   document.getElementById("divpatAddCityLocCode").style.display="";   	  
	   document.getElementById("divpatAddCityLocation").style.display="none";
	    document.getElementsByName("patAddStateCode")[0].disabled=true;   
       document.getElementsByName("patAddCountryCode")[0].disabled=true;
	   document.getElementsByName("patAddStateCode")[0].value="<%=RegistrationConfig.REGISTRATIONDESK_DEFAULT_STATE_CODE %>";   
       document.getElementsByName("patAddCountryCode")[0].value="<%=RegistrationConfig.REGISTRATIONDESK_DEFAULT_COUNTRY_CODE%>"; 
       </logic:equal> */
       <his:statusTransactionInProcess>
 	  <logic:equal name="EmgPatDetailModificationFB" property="isAddressDelhi" value="1">
	   document.getElementById("divpatAddCityLocCode").style.display="";   	  
	   document.getElementById("divpatAddCityLocation").style.display="none";
	  
	   document.getElementsByName("patAddStateCode")[0].value="<%=RegistrationConfig.REGISTRATIONDESK_DEFAULT_STATE_CODE %>";   
       document.getElementsByName("patAddCountryCode")[0].value="<%=RegistrationConfig.REGISTRATIONDESK_DEFAULT_COUNTRY_CODE%>"; 
	 </logic:equal>	
     <logic:equal name="EmgPatDetailModificationFB" property="isReferred" value="1">
       IsreferredOnLoad(document.getElementsByName("isReferred")[0]);
	 </logic:equal>	
	 
	 </his:statusTransactionInProcess>

 }
 function  showdelhidiv()
  {

       <logic:equal name="EmgPatDetailModificationFB" property="isAddressDelhi" value="1">
	  // alert("showdelhidiv222222222");
    
	   document.getElementById("divpatAddCityLocCode").style.display="";   	  
       
	   document.getElementById("divpatAddCityLocation").style.display="none";
	  
	   document.getElementsByName("patAddStateCode")[0].value="<%=RegistrationConfig.REGISTRATIONDESK_DEFAULT_STATE_CODE %>";   
       document.getElementsByName("patAddCountryCode")[0].value="<%=RegistrationConfig.REGISTRATIONDESK_DEFAULT_COUNTRY_CODE%>";  
       </logic:equal>
     Isreferred(document.getElementsByName("isReferred")[0]);
 }
 function callThisOnload(){
 	var LOCAL_LANGUAGE = "<%= RegistrationConfig.LOCAL_LANGUAGE %>";
	initMultilingual(LOCAL_LANGUAGE);	
	
	 focusCrNo();
	showdelhidivOnLoad();
	showState();
	showLocation();
	checkEmployee();
	checkReferDepartment();
	//enableSpouseField();
	/// checkForUnknownOnLoad();
	if(document.getElementsByName("isBroughtBy")[0]){
		setBroughtByVehicleOnLoad();
		broughtBy(document.getElementsByName("isBroughtBy")[0]);
	}
	if(document.getElementsByName("isRelative")[0])	enableRelation(document.getElementsByName("isRelative")[0]);
	if(document.getElementsByName("isBroughtBy")[0])	broughtBy(document.getElementsByName("isBroughtBy")[0]);
	if(document.getElementsByName("patRegCatCode")[0])	document.getElementsByName("patRegCatCode")[0].focus();
	//showdelhidiv();
	 
}

function doHomeWork()
{
/*<logic:equal name="EmgPatDetailModificationFB" property="isUnknown" value="1">
alert("homework");
document.getElementsByName("isUnknown")[0].checked.disabled=false;
document.getElementsByName("isUnknown")[0].value="<%=RegistrationConfig.ISUNKNOWN%>";
alert(document.getElementsByName("isUnknown")[0].value);
</logic:equal>*/
}

function populate(e){ 


document.getElementsByName('crNoToRetrieve')[0].value=e;

submitForm("DGNDETAIL"); 

} 


	
function checkState(){
 	var flag=false;
 	var contryCode=document.getElementsByName("patAddCountryCode")[0].value;
	var defaltcontryCode="<%=RegistrationConfig.REGISTRATIONDESK_DEFAULT_COUNTRY_CODE%>";  
	if(contryCode==defaltcontryCode){
		flag=isSelected(document.forms[0].patAddStateCode,"State");
		// alert("flag "+flag)
	}
	else{
	 //    flag=isEmpty(document.forms[0].patAddStateName,"State Name") ;
		// alert("flag "+flag)
		flag=true;
	}
	
	return flag;
 }
 
 function checkDistrict(){
 	var flag=false;
 	var contryCode=document.getElementsByName("patAddCountryCode")[0].value;
	var defaltcontryCode="<%=RegistrationConfig.REGISTRATIONDESK_DEFAULT_COUNTRY_CODE%>";  
	if(contryCode==defaltcontryCode){
		flag=isSelected(document.forms[0].patAddDistrictCode,"District");
		// alert("flag "+flag)
	}
	else{
	     flag=isEmpty(document.forms[0].patAddDistrict,"District") ;
		// alert("flag "+flag)
		
	}
	
	return flag;
 }
 
 
 
function showState()
{ 
 if(document.getElementsByName("patAddCountryCode")[0])
 {
	var contryCode=document.getElementsByName("patAddCountryCode")[0].value;
	
// alert(<%=RegistrationConfig.REGISTRATIONDESK_DEFAULT_COUNTRY_CODE%>)
	var defaltcontryCode="<%=RegistrationConfig.REGISTRATIONDESK_DEFAULT_COUNTRY_CODE%>";  
	 //alert("chcking conuntry code "+contryCode+"  "+defaltcontryCode);
	var stateCode= document.getElementsByName("patAddStateCode")[0];
	if(contryCode==defaltcontryCode)
	{
	// alert("default country")
		document.getElementById("stateMandotary").style.display="";
		document.getElementById("divpatAddStateCodeCombo").style.display="";
		document.getElementById("divpatAddStateCodeText").style.display="none";
		document.getElementById("stateNotMandotary").style.display="none";
		document.getElementById("districtCombo").style.display="";
		document.getElementById("districtTextBox").style.display="none";
		if(stateCode.options[stateCode.selectedIndex].value=="<%=RegistrationConfig.REGISTRATIONDESK_DEFAULT_STATE_CODE%>")
		{
		document.getElementById("divpatAddCityLocCode").style.display=""; 
	  	document.getElementById("divpatAddCityLocation").style.display="none"; 
	  	}
	// 	document.getElementsByName("patAddStateCode")[0].value="<%=RegistrationConfig.REGISTRATIONDESK_DEFAULT_STATE_CODE%>";
		
	}
	else{
	// alert("not default country")
		document.getElementById("stateMandotary").style.display="none";
		document.getElementById("divpatAddStateCodeCombo").style.display="none";
		document.getElementById("divpatAddStateCodeText").style.display="";
		document.getElementById("stateNotMandotary").style.display="";
		document.getElementById("divpatAddCityLocCode").style.display="none"; 
	  	document.getElementById("divpatAddCityLocation").style.display="";
	  	document.getElementById("districtCombo").style.display="none";
		document.getElementById("districtTextBox").style.display="";
		 
	  	//document.getElementsByName('patAddCity')[0].value=""
	  	document.getElementsByName('patIsUrban')[0].value="<%=RegistrationConfig.REGISTRATIONDESK_DEFAULT_AREA_CATEGORY_CODE%>";
	  	//document.getElementsByName('patAddPIN')[0].value=""
	  	document.getElementsByName('patAddCityLocCode')[0].value="-1"
	  	document.getElementsByName('patAddDistrictCode')[0].value="-1"
	  	
	}
	//  showLocation();
 }	
}


function showLocation()
{
 if(document.getElementsByName("patAddCountryCode")[0])
 {
	var contryCode=document.getElementsByName("patAddCountryCode")[0].value;
	var defaltcontryCode="<%=RegistrationConfig.REGISTRATIONDESK_DEFAULT_COUNTRY_CODE%>";  
  var elmt= document.getElementsByName("patAddStateCode")[0];
  var locationRequired="<%=Config.IS_LOCATION_COMBO_REQ%>"
  var locationRequiredYes="<%=Config.IS_LOCATION_COMBO_REQ_YES%>"
  if(locationRequired==locationRequiredYes)
  { 
 	  if((contryCode==defaltcontryCode) 
	  && (elmt.options[elmt.selectedIndex].value==<%=RegistrationConfig.REGISTRATIONDESK_DEFAULT_STATE_CODE%>))
	  	  {  
	  	   
		  document.getElementById("divpatAddCityLocCode").style.display=""; 
		  document.getElementById("divpatAddCityLocation").style.display="none"; 
		  }
	  else{
		 //alert("inside else hide divpatAddCityLocation") 
	  	  document.getElementById("divpatAddCityLocCode").style.display="none"; 
		  document.getElementById("divpatAddCityLocation").style.display="";   
		   //document.getElementsByName("patAddCityLoc")[0].value="";
		    // elmt.options[elmt.selectedIndex].value="<%=RegistrationConfig.REGISTRATIONDESK_DEFAULT_STATE_CODE%>";
		 }
	}
	else{
		  document.getElementById("divpatAddCityLocCode").style.display="none"; 
		  document.getElementById("divpatAddCityLocation").style.display="";   
		    // elmt.options[elmt.selectedIndex].value="<%=RegistrationConfig.REGISTRATIONDESK_DEFAULT_STATE_CODE%>";
		 }
 }	
}

function validateLocation(){
		  var contryCode=document.getElementsByName("patAddCountryCode")[0].value;
	      var elmt= document.getElementsByName("patAddStateCode")[0];    
	      var locationRequired="<%=Config.IS_LOCATION_COMBO_REQ%>"
  			var locationRequiredYes="<%=Config.IS_LOCATION_COMBO_REQ_YES%>"   
		 //  alert("selected index value state combo"+elmt.options[elmt.selectedIndex].value);
		 //   alert("display style "+document.getElementsByName("patAddStateCode")[0].style.display)
		  //  alert("display style state name"+document.getElementsByName("patAddStateName")[0].style.display)
	if(locationRequired==locationRequiredYes)
  {
 	   if(contryCode==<%=RegistrationConfig.REGISTRATIONDESK_DEFAULT_COUNTRY_CODE%> && elmt.options[elmt.selectedIndex].value==<%=RegistrationConfig.REGISTRATIONDESK_DEFAULT_STATE_CODE%>){ 	        
		    value=document.getElementsByName('patAddCityLocCode')[0].value;  				    				     			
		   // alert("selected index value location combo"+value);
		  	if(value=="-1"){
		  	 alert("Please select Location");
       	     document.getElementsByName('patAddCityLocCode')[0].focus();	
		  	  return false;
		  	  }
		  	  else
		  	  return true;		  	
		   }
		   else{
		   // alert("inside else of validateLocation");
        	    value=document.getElementsByName('patAddCityLoc')[0].value;  				    				     					   
	       	    if (value==""){	       	     
	       	     alert("Please Enter The Location");
	       	     document.getElementsByName('patAddCityLoc')[0].focus();	       	     
	       	     return false;
	       	     }	       	     
	       	     else	       	     
	       	     return true;	       	    
	       	     }
	 }
	 else
	 {
	 	 value=document.getElementsByName('patAddCityLoc')[0].value;  				    				     					   
	       	    if (value==""){	       	     
	       	     alert("Please Enter The Location");
	       	     document.getElementsByName('patAddCityLoc')[0].focus();	       	     
	       	     return false;
	       	     }	       	     
	       	     else	       	     
	       	     return true;	
	 }
}

function getVehicleHosCode(obj)
{
		document.forms[0].patVehicleNo.value=	document.forms[0].broughtByVehicleCode.options[document.forms[0].broughtByVehicleCode.selectedIndex].text;
        
		var broughtByVehicleCode=document.getElementsByName("broughtByVehicleCode")[0].value;
		if(broughtByVehicleCode=="-1")
			document.forms[0].vehicleHosCode.value="";
		else
			document.forms[0].vehicleHosCode.value=broughtByVehicleCode;
		
}

function setBroughtByVehicleOnLoad(){
	if(document.getElementsByName("isRelative")[0].value=="3"){
		var patVehicleNo = document.forms[0].patVehicleNo.value;
		var objBroughtByVehicleCode = document.getElementsByName("broughtByVehicleCode")[0];
		for(var i=0; i<objBroughtByVehicleCode.length; i++){
		//console.log("objBroughtByVehicleCode.text :"+objBroughtByVehicleCode.options[i].text+",objBroughtByVehicleCode.value :"+objBroughtByVehicleCode.options[i].value);
			if(objBroughtByVehicleCode.options[i].text==patVehicleNo){
				objBroughtByVehicleCode.selectedIndex=i;
				document.getElementsByName("vehicleHosCode")[0].value=objBroughtByVehicleCode.options[i].value;
				break;
			}
		}
	}
}
function changeCardNField(obj)
{
	if(obj.value==-1)
	 	document.getElementsByName("patCardNo")[0].disabled=true;
	else
		document.getElementsByName("patCardNo")[0].disabled=false;
}




</script>

</head>

<body>

<html:form action="/emgPatDtlModification">

<%
String st= (String)session.getAttribute("SYSDATE");
System.out.println("date in jsp"+st);
%>
<%
String varStatus="";
%>

 <bean:define id="clientFlag" name="<%=Config.CLIENT_NAME%>"></bean:define>
<his:TitleTag name="Emergency Patient Detail Modification">

<!-- <b>
<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
<a onclick="openPopup('registration/searchByNamePopup.cnt',event)" style=cursor:pointer>
<bean:message key="search"/>
<bean:message key="by"/>
<bean:message key="name"/>
</a>
</font>
</b>
-->
<b><font  size="2" face="Verdana, Arial, Helvetica, sans-serif">
 
</font></b>
</his:TitleTag>
<his:statusNew>
<%varStatus="New";%>	

</his:statusNew>

<his:InputCrNoTag name="EmgPatDetailModificationFB"> </his:InputCrNoTag>	


<his:statusNew>
<input type='hidden' name='crNoToRetrieve'/>
<his:ContentTag>

<table width="100%" border="0"  cellspacing="1" cellpadding="0">
<logic:notEmpty name="<%=RegistrationConfig.PATIENT_VO%>">
                     <logic:iterate id="PATIENT_VO" name="<%=RegistrationConfig.PATIENT_VO%>">                     
 
							<tr>
								<td width="5%" class="tdfont" ><input  type="radio" name="crNoToModify" onclick="submitForm('GETPATDTL');" value='<bean:write name="PATIENT_VO" property="patCrNo" />'/></td>
								<td width="20%" class="tdfont"><bean:write name="PATIENT_VO" property="patCrNo"/> </td>
								<td width="25%" class="tdfont" ><bean:write name="PATIENT_VO" property="patFirstName"/> <bean:write name="PATIENT_VO" property="patMiddleName"/> <bean:write name="PATIENT_VO" property="patLastName"/> </td>
								<td width="25%" class="tdfont" > </td>
								<td width="25%" class="tdfont" > </td>
								<td width="25%" class="tdfont" > </td>
							</tr>			
						</logic:iterate>
						</logic:notEmpty>
		 </table></his:ContentTag> 		
</his:statusNew>
 <his:statusTransactionInProcess>
 <input type='hidden' name='crNoToRetrieve'/> 
  <his:SubTitleTag name="Patient Details">  
  <td width="45%" ></td> 
    <td>
		  <div align="right">
		  		<font color="#ffffff" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		  <bean:message key="isBroughtDead1"/></font></div>
	  </td>
	  
	   <td>
	    <div align="left">
	      <html:checkbox  name="EmgPatDetailModificationFB" property="isBroughtDead" value="1" tabindex="1" />
	      </div>	      
	   </td>
        <td ><div align="right">
        <font color="#ffffff" size="2" face="Verdana, Arial, Helvetica, sans-serif">
        <bean:message key="mlc"/></font> 
        </div>
       </td>
	      <td ><html:checkbox name="EmgPatDetailModificationFB" property="isMLC" value="<%=RegistrationConfig.IS_MLC_TRUE%>" tabindex="1"/>
        </td>	 
  </his:SubTitleTag>
  <%varStatus="InProcess";%>
 
<his:ContentTag>
	<table border="0" cellpadding="0" cellspacing="1" width = "100%">
		<tr>
		 <td width="14%" nowrap class="tdfonthead">
        <div align="right">
        <font color="#FF0000">*</font> 
        <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
        <bean:message key="primary"/><bean:message key="category"/>
        </font>
        </div>
        </td>
        
        
        <td width="17%"  class = "tdfont">
        	   <font color="#000000" size="1" face="Verdana, Arial, Helvetica, sans-serif"> 
        		<html:text name="EmgPatDetailModificationFB" property="patPrimaryCat" tabindex="1"  styleClass ="regcbo" readonly="true"></html:text>
        		<html:hidden name="EmgPatDetailModificationFB" property="patPrimaryCatCode" />
        </font>
        </td>
        <td class="tdfonthead">
	  	</td>	  
	   	<td class="tdfont">
	   	</td>
        <td class="tdfonthead">
        <img class="button" style="cursor:pointer" src='<his:path src="/hisglobal/images/camera.png"/>' alt="Capture Photo" title="Capture Photo" onkeypress="if(event.keyCode==13) openPopup('<his:path src="/registration/uploadFile.cnt"/>',event,300,600)" onclick="openPopup('<his:path src="/registration/uploadFile.cnt"/>',event,300,600)">	  
       	</td>
        <td class="tdfont">
        <img class="button" style="cursor:pointer" src='<his:path src="/hisglobal/images/view.psd.gif"/>' alt="View Photo" title="View Photo" onkeypress="if(event.keyCode==13) openPopup('<his:path src="/registration/enlargedImage.cnt"/>',event,300,600)" onclick="openPopup('<his:path src="/registration/enlargedImage.cnt"/>',event,300,600)">   
	    </td>
           
		</tr>
	    <tr> 
        <td width="14%" height="25" nowrap  class="tdfonthead">
        <div align="right">
        <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
        <bean:message key="patient"/>
        <bean:message key="name"/>
        </font>
        </div>
        </td>
        
        <td width="17%" class="tdfont">
        <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
        <font color="#FF0000">*</font>
        <bean:message key="first"/>
        </font>
        </td>
        
        <td width = "18%" class="tdfonthead">
       	 <div align="left">
        <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
        	<b><bean:message key="middle"/></b>
        </font>
        </div>
        </td>
        
        <td width="17%"  class="tdfont" >
        <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
        <font color="#FF0000">*</font>        
        <bean:message key="last"/>
        </font>
        </td>
        
     
                  
    	 <td width="14%" class="tdfonthead" colspan="1">
	    </td>
		
		<td class="tdfont" width="18">
		</td>  
		
		</tr>
		
		 <tr>
        <td class ="tdfonthead" nowrap>
        <div align="right">
        </div>
        </td>
        
        <td width="17%"	 class="tdfont">
        <html:text name="EmgPatDetailModificationFB" property="patFirstName"  tabindex="1" styleClass="textbox"  maxlength ="30" onblur="isAlpha(this,'First Name')" 
        onkeypress="return validateAlphabetsWithDotsAndForwardSlashWithHypen(event,this)"
        onkeyup="multilingualConversion(this,document.getElementsByName('patFirstNameInMultiLang')[0]);"/> </td>
        <td width="18%"  class="tdfonthead">
        <div align="left">
        <html:text  name="EmgPatDetailModificationFB" property="patMiddleName"  tabindex="2" styleClass="textbox" onblur="isAlpha(this,'Middle Name')" maxlength ="20" 
        onkeypress="return validateAlphabetsWithDotsOnly(event,this)"
        onkeyup="multilingualConversion(this,document.getElementsByName('patMiddleNameInMultiLang')[0]);"/></div>
        </td>
        <td class="tdfont">
        <html:text  name="EmgPatDetailModificationFB" property="patLastName" tabindex="2" styleClass="textbox" maxlength ="20" onblur="isAlpha(this,'Last Name')" 
        onkeypress="return validateAlphabetsWithDotsOnly(event,this)"
        onkeyup="multilingualConversion(this,document.getElementsByName('patLastNameInMultiLang')[0]);"/>
        </td>
       
        <logic:equal name="clientFlag"  value="<%=Config.CLIENT_PGIMER%>">	
				<td width="14%" class="tdfonthead">
					 <%-- 
					<div id="nicklable" align="right"><font size="2" color="#000000"
						face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
						key="nickName" /> </font></div>
					 --%>  
					 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">	
					<bean:message key="patCaste"/></font>
      			</td>
				
				
				<td width="17%" class="tdfont">
				 	<%-- 
					<div id="nickNameControl">
					<html:text name="EmgPatDetailModificationFB"
						property="patNickName" tabindex="1" styleClass="textbox"
						maxlength="30" onchange="isAlpha(this,'Nick Name')"
						onkeypress="return validateAlphabetsWithDotsOnly(event,this)" />
					</div>
					--%>
					<html:select name="EmgPatDetailModificationFB" property="patCasteCode" tabindex="2" styleClass ="regcbo"> <!--  onchange="enableSpouseField()" > -->		  
      				<html:option value="">Select Value</html:option>
	  				<html:options collection = "<%=RegistrationConfig.ESSENTIALBO_OPTION_CASTE %>" property = "value" labelProperty = "label"/>
      				</html:select>			
				</td>
		</logic:equal>
		 <logic:notEqual name="clientFlag"  value="<%=Config.CLIENT_PGIMER%>">	
				<td width="14%" class="tdfonthead"><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<bean:message key="patCaste"/></font>
				</td>				
				
				<td width="17%" class="tdfont">
				<html:select name="EmgPatDetailModificationFB" property="patCasteCode" tabindex="2" styleClass ="regcbo"> <!--  onchange="enableSpouseField()" > -->		  
      				<html:option value="">Select Value</html:option>
	  				<html:options collection = "<%=RegistrationConfig.ESSENTIALBO_OPTION_CASTE %>" property = "value" labelProperty = "label"/>
      				</html:select>	
				
				</td>
		</logic:notEqual>
		
		</tr>
        <tr style="display: none">
	        <td class ="tdfonthead" nowrap><div align="right"></div></td>
	        
	        <td width="17%" class="tdfont">
				<input type="text" name="patFirstNameInMultiLang" value="${EmgPatDetailModificationFB.patFirstNameInMultiLang}" class="textbox" tabindex="1"
				onblur="callOnBlur();" onfocus="callOnClick();"
				maxlength="30" onkeypress="return validateAlphabetsWithDotsAndForwardSlashWithHypen(event,this)" >
			</td>
			<td width="18%" class="tdfonthead">
				<div align="left">
					<input type="text" name="patMiddleNameInMultiLang" value="${EmgPatDetailModificationFB.patMiddleNameInMultiLang}" tabindex="1" class="textbox"
					onblur="callOnBlur();" onfocus="callOnClick();"
					maxlength="20" onkeypress="return validateAlphabetsWithDotsOnly(event,this)" 
					style="display:block" />
					
				</div>
			</td>
			<td class="tdfont">
				<input type="text" name="patLastNameInMultiLang" value="${EmgPatDetailModificationFB.patLastNameInMultiLang}" tabindex="1" class="textbox"
				onblur="callOnBlur();" onfocus="callOnClick();"
				maxlength="30" onkeypress="return validateAlphabetsWithDotsOnly(event,this)" />
			</td>  
	       
			
			<td width="14%" class="tdfonthead"></td>
			<td width="17%" class="tdfont"></td>
        </tr>   
		
		
       
           		
        <tr> 
  <td width="17%"  nowrap  class="tdfonthead" > 
	  <div align="center">
      <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
      <bean:message key="age"/><font color="#FF0000">*</font></font>
      <html:radio name="EmgPatDetailModificationFB" property="isActualDob" value="0" tabindex="1" onclick="ageSelection()"/>
   
     <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
     <bean:message key="dob"/></font>
     <html:radio name="EmgPatDetailModificationFB" property="isActualDob" value="1" tabindex="1" onclick="ageSelection()"/>
     </div>
     </td>
     
     <logic:empty name="EmgPatDetailModificationFB" property="isActualDob">
     <%
               strdivage="none";
               strdivdob="none";                
     %>
     </logic:empty>
          
      <logic:equal name="EmgPatDetailModificationFB" property="isActualDob" value="1">    
            <%
     		 
             strdivage="none";
             strdivdob="";           
          %>               
      </logic:equal> 
      <logic:equal name="EmgPatDetailModificationFB" property="isActualDob" value="0">
         <%
           System.out.println("inside case 0isActualDob:::::::::");	 
           strdivage="";
           strdivdob="none";           
          %>               
      </logic:equal>                    
     
     <td width="18%"  nowrap class="tdfont" >
       <div id="divAge" style='display:<%=strdivage%>'>
        <html:text  name="EmgPatDetailModificationFB" size="7" property="patAge" tabindex="1" maxlength ="3" onkeypress="return validateNumeric(event)"/> 
        <html:select name="EmgPatDetailModificationFB" property="patAgeUnit" tabindex="1" styleClass="smallcombo">
       <html:options collection = "<%=RegistrationConfig.ESSENTIALBO_OPTION_AGE_TYPE %>" property = "value" labelProperty = "label"/>
        </html:select>
        </div>         
       <%//String strEmpdivdob="none"; 
		 //if(((EmgPatDetailModificationFB)pageContext.findAttribute
		//		 ("EmgPatDetailModificationFB")).getPatPrimaryCatCode().equals(Config.PRIMARY_CATEGORY_EMPLOYEE_CODE)){
			//	 strEmpdivdob="";
			//	 strdivdob="none";
	 	//}%>        
      <div id="divDob" style='display:<%=strdivdob%>'>         
        <bean:define name="EmgPatDetailModificationFB" property="patDOB" id="dob" type="java.lang.String"/>               

		<div id="divPatDOB"></div>
		<script type="text/javascript">DateValidator.setup("divPatDOB","patDOB","<%=dob%>","dd-Mon-yyyy","textbox");</script>
		
		<!--<his:date name='<%="patDOB"%>' dateFormate="%d-%b-%Y" value='<%=dob%>' />-->
		
        </div>
        <%--
        <div id="divEmpDob" style='display:<%=strEmpdivdob%>'>
			<input type="text" value='<%=dob%>' readonly="readonly" size="12"/>
		</div>	
      --%></td>  
     
      <td width="14%" class="tdfonthead" >
      <div align="right">
      <font color="#FF0000">*</font>
      <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
      <bean:message key="gender"/>
      </font>
      </div>
      </td>
     
      <td width="17%" class="tdfont" >
      <html:select name="EmgPatDetailModificationFB"  property="patGenderCode" tabindex="1" styleClass="regcbo">
      <html:option value="-1">Select Value</html:option>
	  <html:options collection = "<%=RegistrationConfig.ESSENTIALBO_OPTION_GENDER %>" property  = "value" labelProperty = "label"/>
      </html:select>
      </td>
      <td width="18%" class="tdfonthead" >  
      <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">   
      <bean:message key="maritalStatus"/></font>
      </td>     
      <td class="tdfont">
      <html:select
					name="EmgPatDetailModificationFB" property="patMaritalStatusCode" 
					tabindex="2" styleClass="regcbo" onchange="changeSpouseField(this)">
					<html:option value="-1">Select Value</html:option>
					<html:options
						collection="<%=RegistrationConfig.ESSENTIALBO_OPTION_MARITAL_STATUS %>"
						property="value" labelProperty="label" />
				</html:select>
      </td>
      <tr>
       <td width="19%" class="tdfonthead">
        <div align="right">
        <font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font>
        <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
        <bean:message key="fathersName"/></font>
        </div>
        </td>
       
        <td width="19%" class="tdfont">
        <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
        <html:text name="EmgPatDetailModificationFB" property="patGuardianName" tabindex="1" styleClass="textbox" onblur="isAlpha(this,'Middle Name')" maxlength ="60" onkeypress="return validateAlphabetsWithDotsOnly(event,this)"/>
        </font>
        </td>
        
         <td class="tdfonthead" width="18%">
        		<div align="right">
		<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
        <bean:message key="husbandName"/>
        </font>
        </div>
        </td>
        <td class="tdfont" width="18%">
         <html:text name="EmgPatDetailModificationFB" property="patHusbandName" styleClass="textbox" tabindex="2" maxlength="60" onkeypress="return validateAlphabetsWithDotsOnly(event,this)"/>
        </td> 
        
         <td width="17%" class="tdfonthead" nowrap>
				<div align="right"><font color="#000000" size="2"
					face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
					key="motherName" /></font></div>
				</td>
				<td width="17%" class="tdfont"><html:text name="EmgPatDetailModificationFB"
					property="patMotherName" styleClass="textbox" tabindex="2"
					maxlength="60" onchange="isAlpha(this,'Mother Name')"
					onkeypress="return validateAlphabetsWithDotsOnly(event,this)" /></td>
       
         
     
      </tr>        
      
    
   <tr>
        
       <td class="tdfonthead" width="18%">
        <div align="right">
       
        <font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font>
         <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
        <bean:message key="nationality"/>
        
        </font>
        </div>
        </td>
        
        <td class="tdfont" width="17%">   
 		<div id="patCountryCombo">
        <html:select name="EmgPatDetailModificationFB" property="patNationalityCode" tabindex="1" styleClass="regcbo" >
	    <html:option value="-1">Select Value</html:option>
		<html:options collection ="<%=RegistrationConfig.ESSENTIALBO_OPTION_NATIONALITY %>"property = "value" labelProperty = "label"/>
        </html:select>	
        </div>
        </td>
        
       
        <td width="14%"  class="tdfonthead">
        <div align="right">
        <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
        <bean:message key="religion"/>
        </font>
        </div>
        </td>
      
        
         
        
        <td width="17%"  class ="tdfont">
        <html:select name="EmgPatDetailModificationFB" property="patReligionCode" tabindex="2" styleClass="regcbo">
        <html:option value="">Select Value</html:option>
        <html:options collection = "<%=RegistrationConfig.ESSENTIALBO_OPTION_RELIGION %>" property = "value" labelProperty = "label"/>
        </html:select>
        </td>
        
        <td width="18%" class="tdfonthead">
        <div align="right">
        <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
        <bean:message key="birplace"/></font>
        </div>
        </td>
        
        <td class="tdfont" width="18%">
        <html:text name="EmgPatDetailModificationFB" property="patBirthPlace"
						styleClass="textbox" tabindex="2" maxlength="60"
						onchange="isAlpha(this,'Guardian Name')"
						onkeypress="return validateAlphabetsWithDotsOnly(event,this)" />
        </td>
        </tr>

        <tr>  
        <td width="18%" class="tdfonthead" >
        <div align="right">
        <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	    <bean:message key="idMark1"/>
	    <logic:equal name="EmgPatDetailModificationFB" property="isUnknown" value="1">
	    <font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font>
	    </logic:equal>
	    </font>
	    </div>
	    </td>

         <td width="17%" colspan="2"  class="tdfont" > 
         <html:text  name="EmgPatDetailModificationFB"  size="40" maxlength="100"  property="patIdMark1" tabindex="2" onkeypress="return validateAlphabetsOnly(event,this)"/>
		 </td>
		 
		 <td width="18%" class="tdfonthead" >
        <div align="right">
        <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	    <bean:message key="idMark2"/>
	    <logic:equal name="EmgPatDetailModificationFB" property="isUnknown" value="1">
	    <font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font>
	    </logic:equal>
	    </font>
	    </div>
	    </td>

         <td width="17%" colspan="2"  class="tdfont" > 
         <html:text  name="EmgPatDetailModificationFB"  size="40" maxlength="100" property="patIdMark2" tabindex="2" onkeypress="return validateAlphabetsOnly(event,this)" />
		 </td>
		 </tr>      
		 
		 <tr>
				<td width="17%" class="LABEL"><bean:message key="patOccupation" /></td>
				<td width="17%" class="tdfont"><html:select
					name="EmgPatDetailModificationFB" property="patOccupation"
					tabindex="2" styleClass="regcbo">
					<html:option value="-1">Select Value</html:option>
					<html:options
						collection="<%=RegistrationConfig.ESSENTIALBO_OPTION_OCCUPATION_DTL %>"
						property="value" labelProperty="label" />
				</html:select></td>
				<td class="LABEL" width="17%"><bean:message key="monthlyIncome" />
				</td>
				<td width="17%" class="tdfont">     
 				<html:text name="EmgPatDetailModificationFB" property="patMonthlyIncome" styleClass="textbox" tabindex="2" maxlength="11" onkeypress="return(currencyFormat(this,',','.',event))"/>
				</td>

	       	
	     	   <td width="17%" class="LABEL" >
<!--	       <bean:message key="amountCollected"/> -->
	    </td>
	       <td width ="17%" class="CONTROL" >
<!--	       <html:text name="EmgPatDetailModificationFB" property="patAmountCollected"   maxlength="10,2" readonly="true" styleClass="textbox" />-->
	       </td>	      
		</tr>
        
        
                
        
        <tr>
			
				<td width="17%" class="tdfonthead">
					<div align="right"><font color="#000000" size="2"
					face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
					key="adhar" /> </font></div>
				</td>
				<td width="17%" class="tdfont" nowrap="nowrap"><html:text
					name="EmgPatDetailModificationFB" property="patNationalId"
					styleClass="textbox" maxlength="16" tabindex="2"
					onkeypress="return validateAlphaNumericWithSpecialCharacterOnly(event,this)"
					styleClass="textbox" /></td>       
				
				<td width="17%" class="tdfonthead" nowrap="nowrap" >
					<div align="right"><font color="#000000" size="2"
					face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
					key="otherId" /> </font></div>
				</td>
				<td width="17%" class="tdfont" nowrap="nowrap">
				<html:select name="EmgPatDetailModificationFB" property="patDocType"
						tabindex="2" styleClass="regcbo" onchange="changeCardNField(this)">
						<html:option value="-1">Select Value</html:option>
						<html:options
							collection="<%=RegistrationConfig.ESSENTIALBO_OPTION_VERIFICATION_DOCUMENTS %>"
							property="value" labelProperty="label" /></html:select></td>
        
          <td width="17%" class="tdfonthead" nowrap="nowrap" ><div align="right"><font color="#000000" size="2"
					face="Verdana, Arial, Helvetica, sans-serif"><bean:message key="patCardNo" /> </font></div>
          </td>
          	<td width="17%" class="tdfont">
          	<html:text
						name="EmgPatDetailModificationFB" property="patCardNo" disabled="true"
						styleClass="textbox" maxlength="11"/>
          	</td>
		</tr>
       
	 
     
	 
   
   </table>
	 </his:ContentTag>
       
       
         <his:SubTitleTagBroad name="Address Detail">
		   <table width="100%">
		   <tr>
             <td>	  
             		  <font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font>
                      <font color="#ffffff" size="2" face="Verdana, Arial, Helvetica, sans-serif">
                      <bean:message key="country"/></font>
             </td>       
 			 <td>
 			 	       <html:select name="EmgPatDetailModificationFB" tabindex="1" property="patAddCountryCode"  styleClass="regcbo" onchange="if(this.value!='-1') showState()">
		                  	<html:option value="">Select Value</html:option>
				  		   <html:options collection ="<%=RegistrationConfig.ESSENTIALBO_OPTION_COUNTRY %>"property = "value" labelProperty = "label"/>
                       </html:select>	

             </td>
            <td>
             
             <div id="stateMandotary" style="" >
             		  <font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font>
             		  <font color="#ffffff" size="2" face="Verdana, Arial, Helvetica, sans-serif">
             		  		<bean:message key="state"/>
             		  </font>
              </div>
              <div id="stateNotMandotary" style="" >
             		  <font color="#ffffff" size="2" face="Verdana, Arial, Helvetica, sans-serif">
             		  		<bean:message key="state"/>
             		  </font>
              </div>
             </td>
             <td>     
             		 <div id="divpatAddStateCodeCombo" style="">                 
                     <html:select name="EmgPatDetailModificationFB" tabindex="1" property="patAddStateCode" styleClass="regcbo" onchange="if(this.value!='-1') sendDataForStateChange(this)" >
                       <html:option value="-1">Select Value</html:option>
                       <html:options collection = "<%=RegistrationConfig.ESSENTIALBO_OPTION_STATE %>" property = "value" labelProperty = "label"/>
                     </html:select>    
                     </div> 
                     <div id="divpatAddStateCodeText" style="" >
                     <html:text name="EmgPatDetailModificationFB" tabindex="1" property="patAddStateName" styleClass="regcbo" maxlength="50" onkeypress="return validateAlphaNumericWithDotsOnly(event,this)">
                     </html:text>
                     </div>                 
             </td>
             </tr>
             </table>
             </his:SubTitleTagBroad>   
             
 <his:ContentTag>
 
 <table width="100%"  cellspacing="1" cellpadding="0">                    
 <tr>
             <td  class="tdfonthead" width="17%">
	            <div align="right">
	            
	                      
	            <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	            <bean:message key="location"/>
	            </font>
	            </div>
             </td>
 
 
       <td  class="tdfont" width="17%">
       
          
                <div id="divpatAddCityLocCode" >                
	            <html:select name="EmgPatDetailModificationFB" tabindex="2" styleClass="regcbo" property="patAddCityLocCode" onchange="if(this.value!='-1') sendDataForCityLocation(this)">
				<html:option value="-1">Select Value</html:option>
				<html:options collection ="<%=RegistrationConfig.ESSENTIALBO_OPTION_LOCATION%>" property = "value" labelProperty = "label"/>
	            </html:select>
	            </div>	                    	                    	                    	                    	                    	                    	                    
	      
	            <div id="divpatAddCityLocation" >	                     
				<html:text  name="EmgPatDetailModificationFB" tabindex="2"  maxlength="50" onkeypress="return validateAlphaNumericWithDotsOnly(event,this);" styleClass="textbox" property="patAddCityLoc"/>
				</div>
				
		</td>									         

                  
 
                    <td  class="tdfonthead" width="14%" >
                    <div align="right">
	                     <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	                     <bean:message key="hno"/>
	                     </font> 
                    </div>                    
                    </td>        
                                
                <td  class="tdfont" width="17%">
                <html:text  name="EmgPatDetailModificationFB" onkeypress="return validateAlphaNumericWithDotsOnly(event,this);" property="patAddHNo" tabindex="2" maxlength="15" styleClass="textbox"/>
                </td>
               
	            <td  class="tdfonthead" width="18%">                    
	            <div align="right">
	            
	            <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	            <bean:message key="street"/>  
	            </font>
	            </div>                    
	            </td>
	                     
	            <td class="tdfont" width="17%">
	            <html:text  name="EmgPatDetailModificationFB" property="patAddStreet" onkeypress="return validateAlphaNumericWithDotsOnly(event,this);" tabindex="2" maxlength="30" styleClass ="textbox"/> 
	            </td>
                  
                
                </tr>
                 
                <tr>
                <td class="tdfonthead" width="14%">
                <div align="right">
                <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	            <bean:message key="district"/> 
	            </font>
	            </div>
                </td>                     
                
                <td class="tdfont" width="17%">
                <div id="districtCombo">
                <html:select name="EmgPatDetailModificationFB" tabindex="2" styleClass="regcbo" property="patAddDistrictCode">
				<html:option value="-1">Select Value</html:option>
				<html:options collection ="<%=RegistrationConfig.ESSENTIAL_BO_OPTION_DISTRICT_LIST_STATEWISE%>" property = "value" labelProperty = "label"/>
	            </html:select>
                </div>
                <div id="districtTextBox" style="display:none">
                <html:text  name="EmgPatDetailModificationFB" tabindex="2" property="patAddDistrict" onchange="isAlpha(this,'District')"  onkeypress="return validateAlphabetsWithDotsOnly(event,this)" maxlength="30" styleClass="textbox"/>
                </div>					
                </td>
                  
                <td  class="tdfonthead" width="18%" ><div align="right">
	            	<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font>
	            <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		            <bean:message key="city"/>
		            <bean:message key="slash"/>
		            <bean:message key="village"/>
		         </font>   
	            </div>
	            </td>
	            
	            <td class="tdfont" width="17%">
	            <html:text name="EmgPatDetailModificationFB" tabindex="1" property="patAddCity" onchange="isAlpha(this,'City')" onkeypress="return validateAlphabetsWithDotsOnly(event,this);" maxlength="30" styleClass="textbox"/> 
                </td>
                
                <td class="tdfonthead" width="14%">
	            <div align="right">
	            <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	            <bean:message key="pin"/>
	            </font> 
	            </div>
                </td>       
                  <% 
                String pinCode=((EmgPatDetailModificationFB)pageContext.findAttribute("EmgPatDetailModificationFB")).getPatAddPIN();
               if(pinCode==null)
            	   {
            	   pinCode="";
            	   }%>   
                <td  class="tdfont" width="17%">
                <input type="text" id="pintext"  name="patAddPIN" tabindex="2"
                 size="17"  maxlength ="6"  onkeypress="return validateNumeric(event)" value="<%=pinCode %>"  /> 
                  </td>
                                                    
                    
              </tr>
                    
              <tr>
                      
                    <td  class="tdfonthead"  width="17%">
                    <div align="right">
                    
                    <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
                    <bean:message key="tehsil"/>
                    </font>
                    </div>
                    </td>
             		
                    <td  class="tdfont" width="17%">   
 		            <div >
         		    <html:text name="EmgPatDetailModificationFB" tabindex="2" property="strPatAddressTehsil"  maxlength ="30" styleClass="textbox" onkeypress="return validateNumeric(event)"/> 
                    </div>
                    </td>
                    
             <td width="18%" class="tdfonthead">
				<div align="right"><font color="#000000" size="2"
					face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
					key="areaCategory" /> </font></div>
				</td>

				<td width="18%" class="tdfont"><html:select
					name="EmgPatDetailModificationFB" property="patIsUrban" tabindex="2"
					styleClass="regcbo">
					<html:option value="">Select Value</html:option>
					<html:options
						collection="<%= RegistrationConfig.ESSENTIALBO_OPTION_AREA_CATEGORY %>"
						property="value" labelProperty="label" />
				</html:select></td> 
             <td  class="tdfonthead"  width="17%">
                    <div align="right">
                    <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
                    <bean:message key="contactNo"/>
                    </font>
                    </div>
                    </td>
             		
                    <td  class="tdfont" width="17%">   
 		            <div >
         		    <html:text name="EmgPatDetailModificationFB" tabindex="2" property="patAddContactNo"  maxlength ="30" styleClass="textbox" onkeypress="return validateNumeric(event)"/> 
                    </div>
                    </td>
                  </tr>		
                </table>
   
 </his:ContentTag>
		   <bean:define name="EmgPatDetailModificationFB" property="isBroughtBy" id="br"></bean:define>
		   <logic:equal name="EmgPatDetailModificationFB" property="isBroughtBy" value="1">
		 <%  divBroughtByDisplay=""; %>
		   </logic:equal>
		   
		   <logic:notEqual name="EmgPatDetailModificationFB" property="isBroughtBy" value="1">
		 <%  divBroughtByDisplay="none"; %>
		   </logic:notEqual>
		
<bean:define id="confFlagId" name="<%=Config.EMG_BROUGHT_BY_DETAIL_FLAG%>" ></bean:define>
	 <logic:equal name="confFlagId" value="<%=Config.EMG_BROUGHT_BY_DETAIL_FLAG_VALUE_TRUE%>">
	<his:SubTitleTag name="Brought By Details"> 
	  <table width="100%" cellspacing="1" cellpadding="0">
	   <tr>
	  	<td width="90%">
		  <div align="right">
			  <font color="#ffffff" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			          <bean:message key="isBroughtBy"/>      
			  </font>
		  </div>
	 	</td>
		<td>
		<div align="left">
			<html:checkbox  name="EmgPatDetailModificationFB" tabindex="1" property="isBroughtBy" onclick="broughtBy(this)" value="1" />
	  	</div>
  		</td>
  	 </tr>
  	</table>
 	</his:SubTitleTag>
	
   <div id="broughtById" >     
       <his:ContentTag>
       <table width="100%" cellspacing="1" cellpadding="0">
       <tr>       
        <td width="25%" class="tdfonthead"><!-- 16 -->
      	 <div align="right" >
      	  <font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font>
	        <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	          <bean:message key="broughtBy"/>      
	        </font>
	       </div>
        </td>        
        <td width="25%" class="tdfont" > <!-- 18 -->
        <div align="left" >
        	<html:select name="EmgPatDetailModificationFB" property="isRelative" styleClass="regcbo" onchange="enableRelation(this)" tabindex="1">
        		<html:option value="-1">Select Value</html:option>
        		<html:option value="<%=RegistrationConfig.PATIENT_BROUGHT_BY_RELATIVE %>">Relative</html:option>
        		<html:option value="<%=RegistrationConfig.PATIENT_BROUGHT_BY_POLICE %>">Police</html:option>
        		<html:option value="<%=RegistrationConfig.PATIENT_BROUGHT_BY_OTHER%>">Other</html:option>
        		<html:option value="<%=RegistrationConfig.PATIENT_BROUGHT_BY_108%>">108</html:option>
        		       		
        	</html:select>
<!--             <html:checkbox name="EmgPatDetailModificationFB" tabindex="1" property="isRelative" onclick="enableRelation(this)" />-->
        </div>
        </td>
       <td width="25%"  class="tdfonthead" >
       <div align="right">
       <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
       <bean:message key="realtionship"/>
       </font>
       </div></td>
       
       <td width="25%" class="tdfont"  >
       <html:select name="EmgPatDetailModificationFB" property="broughtByRelationCode" tabindex="1" styleClass="regcbo">
       <html:option value="-1">Select Value</html:option>
	   <html:options collection = "<%=RegistrationConfig.ESSENTIALBO_OPTION_RELATION_DTL %>" property  = "value" labelProperty = "label"/>
	   </html:select>
       </td>
        <%--<td width="25%" class="tdfonthead"><!-- 16 -->
	        <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	          <bean:message key="gender"/>      
	        </font>
        </td>        
        <td width="25%" class="tdfont">
       <html:select name="EmgPatDetailModificationFB" property="broughtByGenderCode" tabindex="1" styleClass="regcbo">
       <html:option value="-1">Select Value</html:option>
         <html:option value="1">Male</html:option>
           <html:option value="2">Female</html:option>
             <html:option value="3">Other</html:option>
	   </html:select>
       </td>--%>
      </tr>
     
      <tr>
       <td width="25%" class="tdfonthead"><!-- 16 -->
		<div align="right" id="nameBroughtBy">       
        <font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font>
	        <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	          <bean:message key="name"/>      
	        </font>
	        </div>
        </td>        
        <td width="25%" class="tdfont" > <!-- 18 -->
             <html:text name="EmgPatDetailModificationFB" tabindex="1" property="broughtByName" onkeypress="return validateAlphabetsWithDotsOnly(event,this)" tabindex="1" styleClass="textbox" onblur="isAlpha(this,'Middle Name')" maxlength="60"/>
        </td>
         <td width="25%" class="tdfonthead">
         <div align="right">
       <font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font>
      <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	          <bean:message key="broughtFromLoacation"/>         
	        </font>
       </div>
        </td>        
        <td width="25%" class="tdfont" > <!-- 18 -->
             <html:text name="EmgPatDetailModificationFB" tabindex="1" property="broughtByLocation"  maxlength="30" styleClass="textbox" onkeypress="return validateAlphabetsWithDotsOnly(event,this)"/>
        </td>
      	
      	 
      </tr>
      </table>
      <div id="policeDetailDiv" style="display: none">
	      <table width="100%" cellspacing="1" cellpadding="0">
		      <tr>
		      	<td width="25%" class="tdfonthead">
		      	<div align="right">
		      	<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font>
		      		 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				       <bean:message key="designation"/>
				      </font>
				     </div> 
		      	</td>
		      	<td width="25%" class="tdfont">
		      		<html:text name="EmgPatDetailModificationFB" property="constableDesig" styleClass="textbox" maxlength="50"></html:text>
		      	</td>
		      	<td width="25%" class="tdfonthead">
		      	<div align="right">
<!--		      	<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font>-->
		      		 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				       <bean:message key="batchNo"/>
				      </font>
				      </div>
		      	</td>
		      	<td width="25%" class="tdfont">
		      		<html:text name="EmgPatDetailModificationFB" property="constableBadgeNo" styleClass="textbox" maxlength="50"></html:text>
		      	</td>
		      </tr>
	      </table>
      </div>
      <div id="ambulanceDtlDiv" style="display: none">
	      <table width="100%" cellspacing="1" cellpadding="0">
		      <tr>
		      	<td width="25%" class="LABEL">		      	 
		      		 <bean:message key="vehicle"/>  <bean:message key="number"/>
		      	</td>
		     
		      	 <td width="25%" class="CONTROL"  >
     			 <html:select name="EmgPatDetailModificationFB" property="broughtByVehicleCode" tabindex="1" styleClass="regcbo" onchange="  getVehicleHosCode(this)">
      			 <html:option value="-1">Select Value</html:option>
	  			 <html:options collection = "<%=RegistrationConfig.ESSENTIALBO_OPTION_VEHICLE %>" property  = "value" labelProperty = "label"/>
	 	    	 </html:select>
      			 </td>
		      	
		      	<td width="25%" class="LABEL">
				    <bean:message key="vehicle"/>  <bean:message key="location"/>    
		      	</td>
		      	<td width="25%" class="CONTROL">
		      	 <div align="left">
		      	
		      		<html:text name="EmgPatDetailModificationFB" property="vehicleHosCode" styleClass="textbox11" disabled="true" value ="" tabindex="1"></html:text>
		      	</div>	
		      	</td>
		      </tr>
	      </table>
      </div>
      <table width="100%" cellspacing="1" cellpadding="0">
      <tr> 
      	<td width="50%" height="100%">
       <table width="100%" cellspacing="1" cellpadding="0">
       <tr>
       <!--<td width="50%"  style="background-color:#FFEBD5;height:45;font-family: 'MS Sans Serif';	font-size:18px; color:#653232;text-align:right;">
      --><td width="50%" class="LABEL">
      <div id="phoneBroughtByDiv">  
      <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
      
	          <bean:message key="phone"/>  <bean:message key="no"/>    </font></div>
	        
       </td>
     
       
       <!--<td width="50%"  style="background-Color:#F1ECE2;height:45;color:#990000;text-align:left;text-transform: capitalize;font-family:'Trebuchet MS';	font-size: 10px;font-weight: bold;font-variant: normal;">
         --><td width="50%" class="CONTROL">
         <html:text name="EmgPatDetailModificationFB" tabindex="1" property="broughtByPhone"  maxlength="30" styleClass="textbox" onkeypress="return validateNumeric(event)"/>
       </td>
       </tr>
       </table>
       </td>
       <td width="50%">
        <table width="100%" cellspacing="1" cellpadding="0">
        <tr>
       <td width="25%" valign="top" rowspan="2" class="tdfonthead"><!-- 18 --> 
          <div align="right">
          	<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
             <bean:message key="broughtbyaddress"/>
       		 </font>
       		</div> 
       	</td>
      
       	<td colspan="2" width="35%" rowspan="2" class="tdfont" valign="top" ><!-- 3 -->
            <html:textarea styleClass="textarea2" onkeypress="return CheckMaxLength(event,this,100,1)"  tabindex="1" name="EmgPatDetailModificationFB" property="broughtByAddress"/>
        </td>
        </tr>
        </table>
        </td>
      </tr>
</table>

</his:ContentTag>
</div>
</logic:equal>
	 			<!--................................... code for referred details........... -->
	<bean:define name="EmgPatDetailModificationFB" property="isReferred"
			id="isref" type="java.lang.String" />

		<logic:equal name="isref" value="1">
			<%
			divisrefdisplay = "";
			%>
		</logic:equal>
		<%
		System.out.println("inside isref 0" + isref);
		%>
		<logic:equal name="isref" value="">
			<%
				//System.out.println("inside isref 0");
				divisrefdisplay = "none";
				divRefGnctdHospitalCode = "none";
				divRefHospname = "none";
				//System.out.println("inside isref 0");
			%>
		</logic:equal>
		<logic:equal name="isref" value="0">
			<%
				//System.out.println("inside isref 0");
				divisrefdisplay = "none";
				divRefGnctdHospitalCode = "none";
				divRefHospname = "none";
				gnctdrefDetails = "none";
				divRefInstitute = "none";
				divDocTitle = "none";
				divDocname = "none";
				//System.out.println("inside isref 0");
			%>
		</logic:equal>

		<logic:equal name="isref" value="1">
			<logic:equal name="EmgPatDetailModificationFB" property="referringInstType"
				value="O">
				<%
					
					divRefGnctdHospitalCode = "none";
					divRefHospname = "";
					gnctdrefDetails = "none";
					divDocTitle = "";
					divDocname = "";
					gnctdrefDetails = "";
				%>
			</logic:equal>
			<logic:equal name="EmgPatDetailModificationFB" property="referringInstType"
				value="G">
				<%
					System.out.println("patRefHospitalName:::::::::empty");
					divRefGnctdHospitalCode = "";
					divRefHospname = "none";
					gnctdrefDetails = "";
					divDocTitle = "";
					divDocname = "";
					gnctdrefDetails = "";
				%>
			</logic:equal>
		</logic:equal>
  <his:SubTitleTag name="Refer Details">
 
   <table width="100%" cellspacing="1" cellpadding="0">
	  <tr>
	  <td width="90%">
	  <div align="right">
	  <font color="#ffffff" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	          <bean:message key="isreferred"/>      
	        </font>
	        </div>
	  </td>
	<td   >
	<div align="left" >
	
        	  <html:checkbox name="EmgPatDetailModificationFB" tabindex="1" property="isReferred"  onclick="Isreferred(this)" value="1"/>
      
  </div>
  </td>
  </tr>
  </table>
  </his:SubTitleTag>
			
  <his:ContentTag>
  <table width="100%">
  <tr>
  		
         <td  width="50%"  class="tdfonthead" colspan="2">  
		 <div id="divReferredInstitute" style="display:<%=divRefInstitute%>">
              <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		         <bean:message key="gnctd"/></font> 
		         <html:radio name="EmgPatDetailModificationFB" property="referringInstType" tabindex="1" value="G" onclick="showdivhoscode(this)"/> 
		         <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		         <bean:message key="other"/></font> 
		         &nbsp;&nbsp;&nbsp;<html:radio name="EmgPatDetailModificationFB" property="referringInstType" value="O" tabindex="1" onclick="showdivhosname(this)"/>
   		</div> 
   		</td>       
        <td  width="25%" nowrap class="tdfont">
	         
           <div id='divRefHosCode' style='display:<%=divRefGnctdHospitalCode%>'>	          
	         <font
						color="#FF0000" size="1"
						face="Verdana, Arial, Helvetica, sans-serif">*</font> <font
						color="#000000" size="2"
						face="Verdana, Arial, Helvetica, sans-serif">
			   <bean:message key="institute"/>
			   <bean:message key="name"/></font>
                  <html:select name="EmgPatDetailModificationFB" tabindex="1" property="patRefGnctdHospitalCode" styleClass="registrationCmb">
		          <html:option value="-1">Select Value</html:option>
				  <html:options collection = "<%=RegistrationConfig.ESSENTIALBO_OPTION_REF_HOSPITAL%>" property = "value" labelProperty = "label"/>		  
	          </html:select>
	         </div>	        
			<div id='divRefHosname' style='display:<%=divRefHospname%>'>
			<font
						color="#FF0000" size="1"
						face="Verdana, Arial, Helvetica, sans-serif">*</font> <font
						color="#000000" size="2"
						face="Verdana, Arial, Helvetica, sans-serif">
			   <bean:message key="hospital"/>
			   <bean:message key="name"/></font>
			   <html:text  name="EmgPatDetailModificationFB" tabindex="1" property="patRefHospitalName" maxlength="100" styleClass="textboxBig" size="20" onkeypress="return validateAlphabetsOnly(event,this)"/>
	        </div>
        </td>  
  
  </tr>
  </table>
  </his:ContentTag>
  

			
 <div id="divReferred" style="display:none">	
 <his:ContentTag>

   <table width="100%" cellpadding="1" cellspacing="1">
   
   <tr>
		<td width="25%"    class="tdfonthead"> 
	    <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
        <bean:message key="previous"/> <bean:message key="hospital"/> <bean:message key="crNo"/></font></td>
        
        <td width="25"% nowrap class="tdfont">
        <html:text name="EmgPatDetailModificationFB" tabindex="1" property="patRefGnctdHospitalCrno" maxlength="13" tabindex="1"  onkeydown="setPrevValue(this, event);" onkeyup="moveToRight(this, event);" onkeypress="return validateNumeric(event)" styleClass="textbox"/>
        </td>

		<td width="15%" class="tdfonthead" nowrap>
			<div id="divDocTitle" style="display:<%=divDocTitle%>">
				<div align="right"><font color="#FF0000" size="1"
					face="Verdana, Arial, Helvetica, sans-serif">*</font> <font
					color="#000000" size="2"
					face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
					key="referredBy" /></font>
				</div></div>
				</td>

				<td width="15%" colspan="2" class="tdfont">
				<div id="divDocName" style="display:<%=divDocname%>">
				<div align="left"><html:text name="EmgPatDetailModificationFB"
					 maxlength="60" property="patRefDoctor" 
					onchange="isAlpha(this,'Referred By Doctor')" tabindex="1" styleClass="textbox"
					onkeypress="return validateAlphabetsOnly(event,this)" />
				</div>
				</div>
				</td>
			</tr>
				
        <!-- <td width="25%" class="tdfonthead"> 
        <div align="right">
        <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
        <bean:message key="referredBy"/></font> 
        </div>
        </td>
         
        <td width="25%"  colspan="2"  class="tdfont"> 
        <div align="left">
        <html:text  name="EmgPatDetailModificationFB"  styleClass="textboxBig" maxlength="100" property="patRefDoctor" onblur="isAlpha(this,'Referred By Doctor')" tabindex="1" onkeypress="return validateAlphabetsOnly(event)" />
        </div>
  		</td>   
        </tr>
        -->        
        <tr>
	    <td width="25%" class="tdfonthead" nowrap>
        <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
        <bean:message key="referring"/>
        <bean:message key="hospital"/>
        <bean:message key="department"/>
        </font>
        </td>
    	<td width="25%" class="tdfont">
        
			<html:select name="EmgPatDetailModificationFB" property="patRefGnctdHospitalDept" tabindex="1" styleClass="regCbo" onchange="checkReferDepartment(this)">
				<html:option value="">Select Value</html:option>
				<html:option value="0">Other</html:option>
				<html:options collection="<%=RegistrationConfig.ESSENTIALBO_OPTION_REFERAL_DEPARTMENT_DTL%>" property="value" labelProperty="label" /> 
			</html:select> 
	        </td>
        	
   			 <td  class="tdfonthead" >
   			 	<div  align="right">
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<bean:message key="other" />
				<bean:message key="department" /> </font>
				</div>
			</td>
			<td class="tdfont">
			<html:text name="EmgPatDetailModificationFB"  property="patRefHospitalDeptOther" maxlength="20"  tabindex="1" styleClass="textbox" disabled="true" onkeypress="return validateAlphabetsOnly(event,this)"/>
			</td>
			
			
        </tr>
        
        
        <tr>
        <td width="25%" class="tdfonthead">
        <div align="right">
        <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
     	<bean:message key="referring"/>
        <bean:message key="hospital"/>
        <bean:message key="unit"/>
        </font>
        </div>
        </td>
        
        <td width="25%" class="tdfont">
        <html:text name="EmgPatDetailModificationFB" property="patRefGnctdHospitalDeptUnit" tabindex="1" maxlength="15" styleClass="textbox" onkeypress="return validateAlphaNumericOnly(event,this)"/>
        </td>
        <td width="25%" class="tdfonthead"></td>
        <td width="25%" class="tdfont"></td>
        </tr>
        
      </table>
 </his:ContentTag>
 
 </div>
 
 			<!--.......................... code for referred details......... ends here.......... -->
       
       
    </his:statusTransactionInProcess>


    <his:ButtonToolBarTag>

     <%if(varStatus.equals("InProcess")){%>
          <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>' style=cursor:pointer onkeypress="if(event.keyCode==13) submitFormOnValidate(ValidateEmgReg(),'SAVE');"  tabindex="1" onclick ="submitFormOnValidate(ValidateEmgReg(),'SAVE');" >
          <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' tabindex="1"  style=cursor:pointer onkeypress="if(event.keyCode==13) submitForm('CANCEL');" tabindex="1" onclick ="submitForm('CANCEL');">
          <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style=cursor:pointer tabindex="1" onclick ="submitForm('NEW')" onkeypress="if(event.keyCode==13) submitForm('NEW');">
       <%} else{ %>

          <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' tabindex="1"  style=cursor:pointer onkeypress="if(event.keyCode==13) submitForm('CANCEL');" tabindex="1" onclick ="submitForm('CANCEL');">
          <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style=cursor:pointer tabindex="1" onclick ="submitForm('NEW')" onkeypress="if(event.keyCode==13) submitForm('NEW');">

	  <%} %>
</his:ButtonToolBarTag>
<%
 
        	Date dt=(Date)session.getAttribute(Config.SYSDATEOBJECT);
             String 	sysDate = hisglobal.presentation.WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy");
%>
<input type="hidden" name="sysDate" value="<%=sysDate%>"/>
<his:status/>
<input type= "hidden" name="hmode" value="unspecified"/>
<html:hidden name="EmgPatDetailModificationFB" property="isUnknown"/>
<html:hidden name="EmgPatDetailModificationFB" property="patAddDistrictCodeHidden"/>
<html:hidden name="EmgPatDetailModificationFB" property="patAddPINHidden"/>
<html:hidden name="EmgPatDetailModificationFB" property="patAddCityHidden"/>
<html:hidden name="EmgPatDetailModificationFB" property="patIsUrbanHidden"/>
<html:hidden name="EmgPatDetailModificationFB" property="patVehicleNo" />


</html:form>
</body>
</html>