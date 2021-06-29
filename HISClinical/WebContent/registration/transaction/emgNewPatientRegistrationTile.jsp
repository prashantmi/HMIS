	<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
	
	<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>

<%@page import="hisglobal.hisconfig.Config"%>
<%@page import="registration.controller.fb.EmgNewPatientRegFB"%>

<head> 

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>EMERGENCY PATIENT REGISTRATION</title> 
	<his:css src="/hisglobal/css/Color.css"/>
	<his:css src="/hisglobal/css/master.css"/>
	<his:css src="/hisglobal/css/hisStyle.css"/>
	<his:css src="/hisglobal/css/hisStyleExt.css"/>
</head>
<his:css src="/hisglobal/css/calendar-blue2.css"/>
<his:css src="/hisglobal/css/tab.css"/>

<his:javascript src="/registration/js/calendar.js"/>
<his:javascript src="/registration/js/popup.js"/>
<%@ page import ="java.util.*,registration.*" %>
<his:javascript src="/registration/js/registration.js"/>
<his:javascript src="/registration/js/validationCalls.js"/>
<his:javascript src="/registration/js/validationCommon.js"/>
<his:javascript src="/registration/js/stateChangeAjax.js" />
<his:javascript src="/registration/js/cityLocationAjax.js" />
<his:javascript src="/registration/js/commonFunctions.js"/>
<his:javascript src="/registration/js/dateFunctions.js"/>
<his:javascript src="/opd/opdJs/opdAjax.js"/>

<his:javascript src="/hisglobal/js/utilityFunctions.js" />
<his:javascript src="/hisglobal/utility/generictemplate/js/validationFunctions.js"/>
<his:javascript src="/hisglobal/utility/generictemplate/js/date_validator.js"/>

<script>
var queryString;

function spouseNameMandatory()
{
//alert("spouseNameMandatory")
	var maritalStatus=document.getElementsByName("patMaritalStatusCode")[0].value;
	
	var maritalStatusMarried="<%=RegistrationConfig.REGISTRATIONDESK_MARITAL_STATUS_MARRIED%>";
	
	var gender=document.getElementsByName("patGenderCode")[0].value;
	
	var genderFemale="<%=Config.GENDER_TYPE_FEMALE%>";
	//alert("maritalStatus="+maritalStatus+":maritalStatusMarried="+maritalStatusMarried+": genderFemale= "+genderFemale+": gender="+gender)
	//alert(maritalStatus==maritalStatusMarried)
	//alert(gender==genderFemale)
	if((maritalStatus==maritalStatusMarried) && (gender==genderFemale))
	{
	
		if(document.getElementsByName("patHusbandName")[0].value=="")
		{
	
			alert("Spouse Name Is Mandatory For Married Female")
			document.getElementsByName("patHusbandName")[0].focus()
			return false
		}
	}
	return true
}

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


function checkReferDepartment(obj)
{
	if(document.getElementsByName('patRefGnctdHospitalDept')[0].value=='0')
	{
		document.getElementsByName('patRefHospitalDeptOther')[0].disabled=false
		document.getElementsByName('patRefHospitalDeptOther')[0].value=""
	}
	else
	{
		document.getElementsByName('patRefHospitalDeptOther')[0].disabled=true
		document.getElementsByName('patRefHospitalDeptOther')[0].value=""
	}
}

function validateCategoryIDRequired()
{
	var valid=true
	var patPriCode=document.getElementsByName("patPrimaryCatCode")[0].value;
	var patEmployeeCode="<%=Config.PRIMARY_CATEGORY_EMPLOYEE_CODE %>";
	if(document.getElementsByName("isIdRequired")[0].value==<%=RegistrationConfig.PAT_CAT_ID_REQUIRED_YES%>)
	{
	//alert("id Required")
		if(patPriCode!=patEmployeeCode)
		{
	
		valid=isEmpty(document.getElementsByName("patCatIdNo")[0],"Patient Category Id")
		
	
		}
	}
	
	return valid
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
		document.getElementsByName("isRelative")[0].value="-1"
		document.getElementsByName("broughtByRelationCode")[0].value="-1"
		document.getElementsByName("broughtByName")[0].value=""
		document.getElementsByName("broughtByLocation")[0].value=""
		document.getElementsByName("constableDesig")[0].value=""
		document.getElementsByName("constableBadgeNo")[0].value=""
		document.getElementsByName("broughtByPhone")[0].value=""
		document.getElementsByName("broughtByAddress")[0].value=""
		enableRelation(document.getElementsByName("isRelative")[0])
	}
}

function onBroughtDead(obj)
{
	if( (obj.checked==true) || (document.getElementsByName("isUnknown")[0].checked==true) )
	{
		//document.getElementsByName("isMLC")[0].checked=true;
		//document.getElementsByName("isMLC")[0].disabled=true;
	}
	else
	{
		//document.getElementsByName("isMLC")[0].checked=false;
		//document.getElementsByName("isMLC")[0].disabled=false;
	}
	
	if(obj.checked==true){
		document.getElementsByName("isBroughtBy")[0].checked=true;
		broughtBy(document.getElementsByName("isBroughtBy")[0]);
	}
	else{
		document.getElementsByName("isBroughtBy")[0].checked=false;
		broughtBy(document.getElementsByName("isBroughtBy")[0]);
	}
}


function enableRelation(obj)
{	
	 
	
	if(obj.value=="1")
	{
			document.getElementsByName("broughtByRelationCode")[0].disabled=false;
			
	}
	else 
	{
			document.getElementsByName("broughtByRelationCode")[0].disabled=true;
	}
	if(obj.value=="2"){
		document.getElementById("policeDetailDiv").style.display="block";
	}
	else{
		document.getElementById("policeDetailDiv").style.display="none";
	}
	
}
function enableSpouseField()
{
// alert("sdsdsdsdsdsds")
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
   

function checkPatientCategory() 
{

 var patPriCode=document.getElementsByName("patPrimaryCatCode")[0].value;
	// alert("cxcxc")
	 var patEmployeeCode="<%=Config.PRIMARY_CATEGORY_EMPLOYEE_CODE %>";
	 var patStaffCode="<%=Config.PRIMARY_CATEGORY_STAFF_CODE %>";
	 var dataFromTabletrue="<%=Config.PATCAT_EMPLOYEE_DATA_FROM_TABLE_TRUE%>";
	 var dataFromTableStatus=document.getElementsByName("patDataFromEmployeeTable")[0].value;
	var dataFromTableStatusObject=document.getElementsByName("patDataFromEmployeeTable")[0];
	 var clientValue="<%=Config.CLIENT%>";
	 var clientPGIMER="<%=Config.CLIENT_PGIMER%>";
	 var patCatEmpFieldValue="<%=Config.PATCAT_EMPLOYEE_FIELD_VALUE%>";
	 var patCatEmpFieldEditFalse="<%=Config.PATCAT_EMPLOYEE_FIELD_EDITABLE_FALSE%>";
	 
	 if(patPriCode!=patEmployeeCode)
	 {
	 	
	 enableForNonEmployee(dataFromTableStatusObject,dataFromTabletrue,clientValue,clientPGIMER,patCatEmpFieldValue,patCatEmpFieldEditFalse);
	 document.getElementsByName("patAddCountryCode")[0].value='<%= RegistrationConfig.REGISTRATIONDESK_DEFAULT_COUNTRY_CODE%>';
	document.getElementsByName("patAddStateCode")[0].value='<%=RegistrationConfig.REGISTRATIONDESK_DEFAULT_STATE_CODE%>';
	document.getElementsByName("patNationalityCode")[0].value='<%=RegistrationConfig.REGISTRATIONDESK_DEFAULT_COUNTRY_CODE%>';
	showState();
	showLocation();
	 }
	 
	
 	 if(patPriCode==patEmployeeCode)
 	 {	
 	 	document.getElementById("divempIdLabel").style.display=""
 	 	document.getElementById("divempIdControl").style.display=""
 	 	document.getElementById("divstaffIdLabel").style.display="none"
 	 	document.getElementById("divstaffIdControl").style.display="none"
 	 	document.getElementById("divIdTextLabel").style.display="none"
 	 	document.getElementById("divIdTextBox").style.display="none"
 	 	document.getElementById("blankCategoryHead").style.display="none"
 	 	document.getElementById("blankCategoryData").style.display="none"
 	 		
 	 	disableForEmployee(dataFromTableStatusObject,dataFromTabletrue,clientValue,clientPGIMER,patCatEmpFieldValue,patCatEmpFieldEditFalse);
 	 }
 	else{
 		
 	 if(patPriCode==patStaffCode)
	 	 {
	 	 	document.getElementById("divempIdLabel").style.display="none"
	 	 	document.getElementById("divempIdControl").style.display="none"
	 	 	document.getElementById("divstaffIdLabel").style.display=""
	 	 	document.getElementById("divstaffIdControl").style.display=""
	 	 	document.getElementById("divIdTextLabel").style.display="none"
 	 		document.getElementById("divIdTextBox").style.display="none"
 	 		document.getElementById("blankCategoryHead").style.display="none"
 	 		document.getElementById("blankCategoryData").style.display="none"
	 	 	
	 	 }
	 else
	 {	
	 //alert("id required "+document.getElementsByName("isIdRequired")[0].value)
	// alert("sasassas")
	// alert("id req "+<%//=RegistrationConfig.PAT_CAT_ID_REQUIRED_YES%>)
	 if(document.getElementsByName("isIdRequired")[0].value==<%=RegistrationConfig.PAT_CAT_ID_REQUIRED_YES%>)
	 	 	{ 
	 	 	document.getElementById("divempIdLabel").style.display="none"
	 	 	document.getElementById("divempIdControl").style.display="none"
	 	 	document.getElementById("divstaffIdLabel").style.display="none"
	 	 	document.getElementById("divstaffIdControl").style.display="none"
	 	 	document.getElementById("divIdTextLabel").style.display=""
 	 		document.getElementById("divIdTextBox").style.display=""
 	 		document.getElementById("blankCategoryHead").style.display="none"
 	 		document.getElementById("blankCategoryData").style.display="none"
 	 		
 	 		//document.getElementById("divEmpDob").style.display="none"
 	 		ageSelection()
	 	 	}
	 	 	else{
	 	 	document.getElementById("divempIdLabel").style.display="none"
	 	 	document.getElementById("divempIdControl").style.display="none"
	 	 	document.getElementById("divstaffIdLabel").style.display="none"
	 	 	document.getElementById("divstaffIdControl").style.display="none"
	 	 	document.getElementById("divIdTextLabel").style.display="none"
 	 		document.getElementById("divIdTextBox").style.display="none"
 	 		document.getElementById("blankCategoryHead").style.display=""
 	 		document.getElementById("blankCategoryData").style.display=""
 	 		
 	 		//document.getElementById("divEmpDob").style.display="none"
 	 		ageSelection()
	 	 	}
	 }	 
	}
	
 	if(patPriCode=='<%=Config.PRIMARY_CATEGORY_POOR_FREE_CODE%>'){
 		document.getElementById("poorPatTR").style.display="table-row";
	}
	else{
		document.getElementById("poorPatTR").style.display="none";
	} 	

}

function handlePostResponse(){ 
 
 if(request.readyState == 4){
 if(request.status == 200){
 var reply=request.responseText
 if(document.getElementsByName("patAmountCollected")[0].disabled!=true)
 	{
 	//alert("reply"+reply)	
	 document.getElementsByName("patAmountCollected")[0].value=reply.substring(0,reply.indexOf('^'));
	 document.getElementsByName("isIdRequired")[0].value=reply.substring(reply.indexOf('^')+1);
	// alert("isIdRequired"+document.getElementsByName("isIdRequired")[0].value)

 		
 	}
 	checkPatientCategory()
 } else {
 alert("A problem occurred with communicating between "+
 "the XMLHttpRequest object and the server program.");
 }
 }//end outer if
}

function setQueryString(){
 var patientCat=document.getElementsByName("patPrimaryCatCode")[0].value;
 var tariffIdValue='<%=RegistrationConfig.EMERGENCY_REGISTRATION_TARIFF_ID%>';
 var tariffIdLable='<%=RegistrationConfig.TARIFF_ID%>';
 var patientCatLable='<%=RegistrationConfig.PATIENT_CAT%>';
 
 queryString=tariffIdLable+'='+tariffIdValue+'&'+patientCatLable+'='+patientCat;
}

function sendData(){

	setQueryString();
 //alert("queryString"+queryString);
	// var url='http://localhost:8080/HISClinical/AmountByPatientCat';
	var patPriCode=document.getElementsByName("patPrimaryCatCode")[0].value;
	//alert("patPriCode"+patPriCode)
	if(patPriCode =='-1'){
	  	document.getElementsByName("isIdRequired")[0].value="";
	  	checkPatientCategory();
    }
	 else{
	 //alert("patPriCode"+patPriCode)
	 	if(patPriCode!=""){ // && patPriCode!='-1'){
	 		var url='<%=RegistrationConfig.REG_URL_FOR_AMOUNT_BY_CAT%>';
			httpRequest("POST",url,true);
		}
	 }	
	// alert("end send data")
}

function httpRequest(reqType,url,asynch){

	 //Mozilla-based browsers
	//alert("inside httpRequest")
	 if(window.XMLHttpRequest){
	
	 request = new XMLHttpRequest();
	 //alert("url in http"+url);
	 initReq(reqType,url,asynch);
	
	 } else if (window.ActiveXObject){
	//alert("For Internet Explorer")
	 request=new ActiveXObject("Msxml2.XMLHTTP");
		//alert("request object:Mscml2"+request)
	 if (! request){
	
	 request=new ActiveXObject("Microsoft.XMLHTTP");
	//alert("request object:Microsoft"+request)
	 }
	
	 if(request){
		//	alert("request branched here")
	 	initReq(reqType,url,asynch);
	
		 /* Unlikely to branch here, as IE uses will be able to use either one of the constructors*/
	
	 } else {
	
		 alert("Your browser does not permit the use of all "+
		 "of this application's features!");}
	 } else {
	
	 alert("Your browser does not permit the use of all "+
	 "of this application's features!");}

}

function initReq(reqType,url,isAsynch){
	// alert("inside initReq()")
	
	 /* Specify the function that will handle the HTTP response */
	 request.onreadystatechange=handleResponse;
	//alert("url in initreq "+url);
	 request.open(reqType,url,isAsynch);
	
	 /* set the Content-Type header for a POST request */
	 request.setRequestHeader("Content-Type","application/x-www-form-urlencoded; charset=UTF-8");
	//alert("query String"+queryString)
	 request.send(queryString);

}

function handleResponse(){
	 if(request.readyState == 4){
		 if(request.status == 200){
			 // alert("xcxcxcxcxcxc")
			 var reply=request.responseText
			 document.getElementsByName("patAmountCollected")[0].value=reply.substring(0,reply.indexOf('^'));
			 document.getElementsByName("isIdRequired")[0].value=reply.substring(reply.indexOf('^')+1);
			 checkPatientCategory();
	 	
		 } else {
		 alert("A problem occurred with communicating between "+
		 "the XMLHttpRequest object and the server program.");
		 }
	 }//end outer if
}

function checkIsrefer(){
	var flag=false;
	
	if(document.getElementsByName("isReferred")[0].checked){
		// alert("inst value="+document.getElementsByName("referringInstType")[0].value); 
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
function primCatHandler(mode){
// 	alert("fggfgf");
	submitForm(mode);		
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

 function submit4image(){
	//elem = document.getElementsByName('hmode')[0];
	//elem.value = "REFRESHFORIMAGE"; 
	//document.forms[0].submit();
 }

 
 function validateBroughtByPolice()
 {
 	valid=true
 	if(document.forms[0].isRelative.value== <%=RegistrationConfig.PATIENT_BROUGHT_BY_POLICE %>)
 	{
 		if(isEmpty(document.getElementsByName('constableDesig')[0],'Designation' ) &&
 		isEmpty(document.getElementsByName('constableBadgeNo')[0],'Badge No.'))
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
 	var client= "<%=Config.CLIENT%>";
 		
 	if((document.forms[0].isBroughtBy.checked) || ((document.forms[0].isUnknown.checked) && (client=="<%=Config.CLIENT_PGIMER%>") ) || (document.forms[0].isBroughtDead.checked))
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
 
 function validateEmg(){
 	var valid=false;
 	var broughtDetailsRequired="<%=Config.EMG_BROUGHT_BY_DETAIL_FLAG_VALUE%>"
 	var broughtByDetailsRequiredTrue="<%=Config.EMG_BROUGHT_BY_DETAIL_FLAG_VALUE_TRUE%>"
 //	alert("broughtDetailsRequired"+broughtDetailsRequired)
 //	alert("broughtByDetailsRequiredTrue"+broughtByDetailsRequiredTrue)
 	if(broughtDetailsRequired==broughtByDetailsRequiredTrue){
	 	if(document.getElementsByName("isBroughtBy")[0].checked==true)
	 	{
	 		document.getElementsByName("isBroughtBy")[0].value="1";
	 	}
	 	else
	 	{
	 		document.getElementsByName("isBroughtBy")[0].value="0";
	 	}
	 	/*
	 	if(document.getElementsByName("isRelative")[0].checked==true)
	 	{
	 		document.getElementsByName("isRelative")[0].value="1";
	 	}
	 	else
	 	{
	 		document.getElementsByName("isRelative")[0].value="0";
	 	}
	 	*/
 	}
 	////in case of unknown brought by detail s are mandatory for PGIMER
 
 if(document.forms[0].isUnknown.checked){
 	 if(comboValidation(document.forms[0].departmentUnitCode,"Unit")
		 && checkAgeOrDob()
 		 && isValid(document.forms[0].patAge,"Age",125) 
		 && isEmpty(document.forms[0].patAgeUnit,"Age Unit")

		//&& validateDateAgainstSysDate(document.forms[0].patDOB,"Date of Birth")
		&& validatePatientDOBAgainstSysDate()

 		 && isSelected(document.forms[0].patGenderCode,"Gender")
 		// && isEmpty(document.forms[0].patIdMark1,"Id Mark 1")
 		 //&& isEmpty(document.forms[0].patIdMark2,"Id Mark 2")
 		 && checkIsrefer()
	 	 && isEmpty(document.forms[0].patRefDoctor,"Referred By Doctor") &&
	 	 validateBroughtBy()){
	 	 valid=true;
	 	 }
 }
 else{
 	valid=ValidateEmgReg();
 }
	// Setting Date from format 'dd-mm-yyyy' format to 'dd-Mon-yyyy'
	var patPriCode = document.getElementsByName("patPrimaryCatCode")[0].value;
	var patEmployeeCode = "<%=Config.PRIMARY_CATEGORY_EMPLOYEE_CODE %>";
	if(valid==true && patPriCode!=patEmployeeCode)
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

 function checkDisabled()
 {
 // alert("checkDisabled");
 obj=document.forms[0].isUnknown;
 if(obj.type=="checkbox" && obj.checked)
 {
//  alert("checked");
 	return true;
 }else{
 return (isSelected(document.forms[0].patPrimaryCatCode,"Primary Category") &&   validateLocation(document.forms[0].patAddCityLocCode,"Location"));
 }
 }
 
 function ValidateEmgReg()
 { 
	
	var valid=true;	
	var patCatEmpFieldValue="<%=Config.PATCAT_EMPLOYEE_FIELD_VALUE%>";
	var patCatEmpFieldEditfalse="<%=Config.PATCAT_EMPLOYEE_FIELD_EDITABLE_FALSE%>";
	var dataFromTableStatus=document.getElementsByName("patDataFromEmployeeTable")[0].value;
	//var bb=isEmpty(document.forms[0].patAge,"Age");
	var patPriCode=document.getElementsByName("patPrimaryCatCode")[0].value;
 	var dataFromTableValue="<%=Config.PATCAT_EMPLOYEE_DATA_FROM_TABLE_VALUE%>";
 	var dataFromTableTrue="<%=Config.PATCAT_EMPLOYEE_DATA_FROM_TABLE_TRUE%>";
	var patEmployeeCode="<%=Config.PRIMARY_CATEGORY_EMPLOYEE_CODE %>";
	var client="<%=Config.CLIENT%>";
	var clientSms="<%=Config.CLIENT_SMS%>";
//	alert("dfgdsfhsgffgjgf"+document.getElementsByName("isReferred")[0].value);
if((patPriCode==patEmployeeCode) && (patCatEmpFieldEditfalse==patCatEmpFieldValue))
{ 
	if(dataFromTableStatus==dataFromTableTrue) 
	 valid=true;
	 else
	 {
	 	alert("Please Fetch Data For Employee")
	 	valid=false; 
	 	return valid
	 }
}

	if((patPriCode==patEmployeeCode) && (dataFromTableStatus==dataFromTableTrue) )
	{
		 if(comboValidation(document.forms[0].departmentUnitCode,"Unit")
		 &&  comboValidation(document.forms[0].patPrimaryCatCode,"Patient Category")
		  	&& validateLocation()	
			 && validateDot(document.forms[0].patAddCityLoc,"Location")
			  && checkIsrefer()
		 	  && isEmpty(document.forms[0].patRefDoctor,"Referred By Doctor") &&
		 	  validateBroughtBy() 
		 	)
		 	 {
			  valid=true;
			 }
			       
			 else
			 valid=false;     
			 
		 	
	}
	else
	{ 
	if(	 comboValidation(document.forms[0].departmentUnitCode,"Unit") 
	&& comboValidation(document.forms[0].patPrimaryCatCode,"Patient Category")
	 && validateCategoryIDRequired()	
	 //&& validateForPoorPatient(document.forms[0].patPrimaryCatCode,'<%//=Config.PRIMARY_CATEGORY_POOR_FREE_CODE%>')
	 && isEmpty(document.forms[0].patFirstName,"First Name") 
	 && validateDot(document.forms[0].patFirstName,"First Name")
	 && validateSpecialCharacter('/',document.forms[0].patFirstName,"First Name")
	 && validateSpecialCharacter('-',document.forms[0].patFirstName,"First Name")
	 && validateDot(document.forms[0].patMiddleName,"Middle Name")
	 //&& (client==clientSms ?(isEmpty(document.forms[0].patLastName,"Last Name")):true )
	 //&& isEmpty(document.forms[0].patLastName,"Last Name")
	 && validateDot(document.forms[0].patLastName,"Last Name")
	 && checkAgeOrDob()
	 && isValid(document.forms[0].patAge,"Age",125) 
	 && isEmpty(document.forms[0].patAgeUnit,"Age Unit")

	//&& validateDateAgainstSysDate(document.forms[0].patDOB,"Date of Birth")
	&& validatePatientDOBAgainstSysDate()
	//&& validateAgeForDeptReg('<%//=RegistrationConfig.MAX_AGE_TO_REGISTER_IN_CHILD_DEPT%>','<%//=RegistrationConfig.CHILD_DEPT_CODE%>',document.getElementsByName("departmentUnitCode")[0])
	 && comboValidation(document.forms[0].patGenderCode,"Gender")
	 //&& isEmpty(document.forms[0].patGuardianName,"Father's Name")
	 && validateDot(document.forms[0].patGuardianName,"Father Name")
	 //&& spouseNameMandatory()
	 && validateFatherNameSpouseName()
	 && validateDot(document.forms[0].patHusbandName,"Husband Name")
	 && validateDot(document.forms[0].patMotherName,"Mother Name")
	 && comboValidation(document.forms[0].patNationalityCode,"Nationality")
	 && comboValidation(document.forms[0].patReligionCode,"Religion")
	 && validateMonthlyIncome(document.getElementsByName("patMonthlyIncome")[0],'<%=RegistrationConfig.BPL_MONTHLY_INCOME%>','<%=Config.PRIMARY_CATEGORY_POOR_FREE_CODE%>')
	 && comboValidation(document.forms[0].patAddCountryCode,"Country")
	 && checkState()
	 //&& validateLocation()
	 && isEmpty(document.forms[0].patAddHNo,"Address")
	 && validateDot(document.forms[0].patAddCityLoc,"Location")
	 //&& validateDot(document.forms[0].patAddHNo,"House Number")
	 //&& validateDot(document.forms[0].patAddStreet,"Street")
	 && validateDot(document.forms[0].patAddDistrict,"District")
	 && isEmpty(document.forms[0].patAddCity,"City / Village")
	 && validateDot(document.forms[0].patAddCity,"City / Village")
	 && validateMinimumLength(document.getElementById("pintext"),'Pin Number','6') 
	 && validatePinNumber(document.getElementById("pintext"))
	 //&& isEmpty(document.forms[0].patAddContactNo,"Contact No")
	 && checkIsrefer()
	 && isEmpty(document.forms[0].patRefDoctor,"Referred By Doctor") 
	 && validateBroughtBy()
	  <his:madatory property="departmentCode" /> 
	  ) 
	 
	 {
	  valid=true;
	 }
	       
	 else
	 valid=false;     
	 }

	return valid;    
	}
function submitTile(mode){
//alert ("inside submitTile");
	elem = document.getElementsByName('hmode')[0];
	elem.value = mode;
	//alert(elem.value);
	document.forms[0].submit();
}


function checkForUnknownOnLoad()
{

	 var client="<%=Config.CLIENT%>";
	 var clientPGI="<%=Config.CLIENT_PGIMER%>";
//alert("checkForUnknownOnLoad");

	 <logic:equal name="EmgNewRegFB" property="isUnknown" value="1">
//alert("unknown true");
		//document.getElementsByName("patFirstName")[0].value="<%=RegistrationConfig.PATIENT_UNKNOWN%>";
		document.getElementsByName("patFirstName")[0].readOnly=true;
		
		document.getElementsByName("patPrimaryCatCode")[0].value="<%=RegistrationConfig.REGISTRATIONDESK_UNKNOWN_PRIMARY_CATEGORY%>";
	   	checkPatientCategory()

		document.getElementsByName("patPrimaryCatCode")[0].disabled=true;
		
 	    document.getElementsByName("patAddHNo")[0].disabled=true;
	    //document.getElementsByName("patAddStreet")[0].disabled=true;
	   
    	//document.getElementsByName("patAddCity")[0].disabled=true;
    	document.getElementsByName("patAddContactNo")[0].disabled=true;
	    document.getElementsByName("patAddPIN")[0].disabled=true;
	    document.getElementsByName("patMiddleName")[0].disabled=true;
	    document.getElementsByName("patLastName")[0].disabled=true;
	    document.getElementsByName("patGuardianName")[0].disabled=true;
	    document.getElementsByName("patHusbandName")[0].disabled=true;
    	//document.getElementsByName("prevCrNo")[0].disabled=true;
    	document.getElementsByName("patReligionCode")[0].disabled=true;
    	document.getElementsByName("patNationalityCode")[0].disabled=true;
    	document.getElementsByName("patIsUrban")[0].disabled=true;
    	document.getElementsByName("patMaritalStatusCode")[0].disabled=true;
    	document.getElementsByName("patNationalId")[0].disabled=true;
    	document.getElementsByName("patAmountCollected")[0].disabled=true;
    	document.getElementsByName("patMonthlyIncome")[0].disabled=true;
    	
    	
    	 
    	//document.getElementsByName("patAddCityLocCode")[0].disabled=true;
    	document.getElementsByName("patAddCityLoc")[0].disabled=true;
    	document.getElementsByName("patAddCity")[0].disabled=true;
    	document.getElementsByName("patMotherName")[0].disabled=true;
    	
    	if(client==clientPGI)
    	{
   		//document.getElementsByName("patNickName")[0].disabled=true;
   		document.getElementsByName("patOccupation")[0].disabled=true;
   		document.getElementsByName("patFatherOccupation")[0].disabled=true;
   		document.getElementsByName("patHusbandOccupation")[0].disabled=true;
   		document.getElementsByName("patCardNo")[0].disabled=true;
   		///enable brought by for unknown
   		//document.getElementsByName("isBroughtBy")[0].checked=true
   			
    	}
    	var contryCode=document.getElementsByName("patAddCountryCode")[0].value;
	var defaltcontryCode="<%=RegistrationConfig.REGISTRATIONDESK_DEFAULT_COUNTRY_CODE%>";  
    	if(contryCode==defaltcontryCode)
    { 
    //alert("country default"); 
    document.getElementsByName("patAddCountryCode")[0].disabled=true;
	document.getElementsByName("patAddStateCode")[0].disabled=true;
	document.getElementsByName("patAddDistrictCode")[0].disabled=true;
	}
	else{
	//alert("country not default");
	document.getElementsByName("patAddCountryCode")[0].disabled=true;
	document.getElementsByName("patAddStateName")[0].disabled=true;
	document.getElementsByName("patAddDistrict")[0].disabled=true;
	
	}
	    		
	</logic:equal>
	
}

function callThisOnload(){
// alert("on load call");
	if(document.getElementsByName("isUnknown")[0].checked==true || (document.getElementsByName("isBroughtDead")[0].checked==true)){
		document.getElementsByName("isBroughtBy")[0].checked=true;
	}
	else{
		document.getElementsByName("isBroughtBy")[0].checked=false;
	}
	document.getElementsByName("isUnknown")[0].focus()
	checkForUnknownOnLoad();
		
	showState();
	showLocation();
	enableRelation(document.getElementsByName("isRelative")[0])
	broughtBy(document.getElementsByName("isBroughtBy")[0]);
	<logic:notEqual name="EmgNewRegFB" property="isUnknown" value="1">
		sendData();
		checkPatientCategory();
	</logic:notEqual>
	//enableSpouseField();
	
	//if( (document.getElementsByName("isUnknown")[0].checked==true) || (document.getElementsByName("isBroughtDead")[0].checked==true))
	if(document.getElementsByName("isUnknown")[0].checked==true)
	{
		document.getElementsByName("isMLC")[0].checked=true;
		document.getElementsByName("isMLC")[0].disabled=true;
	}
	else
	{
		document.getElementsByName("isMLC")[0].checked=false;
		document.getElementsByName("isMLC")[0].disabled=false;
	}
	if(document.getElementsByName("isUnknown")[0].checked==true)
	{
		document.getElementsByName("isFree")[0].checked=true;
		document.getElementsByName("isFree")[0].disabled=true;
	}
	else
	{
		document.getElementsByName("isFree")[0].checked=false;
		document.getElementsByName("isFree")[0].disbaled=false;
	}
	
	
	
}

function showEmployeepopup(e){
    // if(!document.getElementsByName("patIdNo")[0].value=="")
    //	  openPopupWide(document.getElementsByName("patIdNo")[0].value,'<his:path src="/registration/employeeDtlWithDependents.cnt"/>',e);  
    //	  else
    //	  alert("Please enter Employee ID");
    openPopup('<his:path src="/registration/employeeDtlWithDependents.cnt"/>',e,300,600);
  }
function poulateFieldsWithEmpDtl(selectedElem){
//   alert("sdsd"+selectedElem);
	document.getElementsByName("empIdChk")[0].value=selectedElem;		
	submitForm("GETEMPDEPENDENTDTL");
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
       	     document.getElementsByName('patAddCityLocCode')[0].focus;	
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
	       	     document.getElementsByName('patAddCityLoc')[0].focus;	       	     
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

function validateCategoryIDRequired()
{
	var valid=true
	var patPriCode=document.getElementsByName("patPrimaryCatCode")[0].value;
	var patEmployeeCode="<%=Config.PRIMARY_CATEGORY_EMPLOYEE_CODE %>";
	if(document.getElementsByName("isIdRequired")[0].value==<%=RegistrationConfig.PAT_CAT_ID_REQUIRED_YES%>)
	{
	//alert("id Required")
		if(patPriCode!=patEmployeeCode)
		{
	
		valid=isEmpty(document.getElementsByName("patCatIdNo")[0],"Patient Category Id")
		
	
		}
	}
	
	return valid
}


function doHomeWork(){
//	alert("home work karo");
	document.getElementsByName("patPrimaryCatCode")[0].disabled=false;
    document.getElementsByName("patAddCityLocCode")[0].disabled=false;
    document.getElementsByName("patAddHNo")[0].disabled=false;
    document.getElementsByName("patAddStreet")[0].disabled=false;
    document.getElementsByName("patAddDistrict")[0].disabled=false;
   	document.getElementsByName("patAddCity")[0].disabled=false;
   	document.getElementsByName("patAddContactNo")[0].disabled=false;
    document.getElementsByName("patAddPIN")[0].disabled=false;
	
}

function  showdelhidiv()
  {
// alert("delhidiv..........");
       <logic:equal name="EmgNewRegFB" property="isAddressDelhi" value="1">
	  // alert("showdelhidiv222222222");    
		   document.getElementById("divpatAddCityLocCode").style.display="";   	  
	       document.getElementsByName("isAddressDelhi")[0].checked="true";
		   document.getElementById("divpatAddCityLocation").style.display="none";
		   document.getElementsByName("patAddStateCode")[0].disabled=true;   
	       document.getElementsByName("patAddCountryCode")[0].disabled=true;
		   document.getElementsByName("patAddStateCode")[0].value="<%=RegistrationConfig.REGISTRATIONDESK_DEFAULT_STATE_CODE %>";   
	       document.getElementsByName("patAddCountryCode")[0].value="<%=RegistrationConfig.REGISTRATIONDESK_DEFAULT_COUNTRY_CODE%>";  
       </logic:equal>
//       alert("before is ref.........");
     Isreferred(document.getElementsByName("isReferred")[0]);
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
	//alert("country code ="+contryCode+ " " +defaltcontryCode)
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
 
 
 
function showState(){ 
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
		 
	  	document.getElementsByName('patAddCity')[0].value=""
	  	document.getElementsByName('patIsUrban')[0].value="<%=RegistrationConfig.REGISTRATIONDESK_DEFAULT_AREA_CATEGORY_CODE%>";
	  	document.getElementsByName('patAddPIN')[0].value=""
	  	document.getElementsByName('patAddCityLocCode')[0].value="-1"
	  	document.getElementsByName('patAddDistrictCode')[0].value="-1"
	  	
	}
	//  showLocation();
	
}


function showLocation(){

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
		   document.getElementsByName("patAddCityLoc")[0].value="";
		    // elmt.options[elmt.selectedIndex].value="<%=RegistrationConfig.REGISTRATIONDESK_DEFAULT_STATE_CODE%>";
		 }
	}
	else{
		  document.getElementById("divpatAddCityLocCode").style.display="none"; 
		  document.getElementById("divpatAddCityLocation").style.display="";   
		    // elmt.options[elmt.selectedIndex].value="<%=RegistrationConfig.REGISTRATIONDESK_DEFAULT_STATE_CODE%>";
		 }
	
}


function disableAmountCollection(obj)
{   
   var unknown=document.getElementsByName("isUnknown")[0];
	if( (obj.checked==true) || (unknown.checked==true) )
	{
		document.getElementsByName("patAmountCollected")[0].disabled=true;
	}
	else
	{
		document.getElementsByName("patAmountCollected")[0].disabled=false;
	}
}

</script>

<%
   String divdisplay="\"\"";
   String divnddisplay="\"\"";
   String divisrefdisplay="\"\"";
   String divRefGnctdHospitalCode="\"\"";
   String divRefHospname="\"\"";
   String strdivage="\"\"";
   String strdivdob="\"\"";
   String RefGnctdHosNameradio=""; 
   String RefGnctdHoscoderadio="";    
   String divDocname="\"\"";
	String divDocTitle="\"\"";
	String divref="\"\"";
	String divRefGnctd="\"\"";
%>
<%
String st= (String)session.getAttribute("SYSDATE");
//System.out.println("date in jsp"+st);
String systemDate=hisglobal.presentation.WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy HH:mm");
%>



	
	 <bean:define id="clientFlag" name="<%=Config.CLIENT_NAME%>"></bean:define>
	<his:TitleTag name="Emergency New Patient Registration">
      	

          <b>
          <font  size="2" face="Verdana, Arial, Helvetica, sans-serif">
          
          	<%String ss=(String)session.getAttribute(Config.SYSDATE);
          	
          	%>
		  
		 </font></b>    
	  </his:TitleTag>
 <his:statusTransactionInProcess>
 
 
  <his:SubTitleTag name="Patient Details">  
  <td width="45%" >
  </td>
 
   <td >
		  <div align="right">
		  		<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		<B>  <bean:message key="unknown"/></B> </font></div>
	  </td>
	  
	   <td >
	   <div align="left">
	      <html:checkbox  name="EmgNewRegFB" property="isUnknown" value="1" tabindex="2" onclick="submitTile('UNKNOWN');"/>
	    </div>	      
	   </td>
	   
	    <td >
		  <div align="right">
		  		<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		 <b> <bean:message key="isBroughtDead1"/></b></font></div>
	  </td>
	  
	   <td >
	    <div align="left">
	      <html:checkbox  name="EmgNewRegFB" property="isBroughtDead" value="1" tabindex="2" onclick="onBroughtDead(this);"/>
	      </div>	      
	   </td>
	 
	       <td>
			<div align="right">
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
       	<b>	 <bean:message key="isExternal"/></b>
       		</font>
       	   </div>
        </td>
      
        <td >
         <div align="left">
        <html:checkbox tabindex="1" name="EmgNewRegFB"  property="isMLC" tabindex="2" value="<%=RegistrationConfig.IS_MLC_TRUE%>"/>
        </div>
        </td>
      
	   <td >
	   			<div align="right" style="display: none">
  	   		 	<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
        		<b>	<bean:message key="isFree"/></b>
        		</font>
        		</div>
        </td>
         <td >
          <div align="left" style="display: none">
        	 <html:checkbox tabindex="1" name="EmgNewRegFB" property="isFree" tabindex="2" value="<%=RegistrationConfig.IS_FREE_TRUE%>" onclick="disableAmountCollection(this)" onchange="disableAmountCollection(this)"/>
        	 </div>
	      </td>
   
  </his:SubTitleTag>
	
<his:ContentTag>
	<table border="0" cellpadding="0" cellspacing="0" width = "100%">
	   <tr>
	  
	   <td width="17%"  class="tdfonthead">
                <div align="right">
                <font color="#FF0000" size="1" >*</font>
                <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
               <bean:message key="unit"/>    
                </font>
                </div>
         </td>
	   	<td width="15%"  class="tdfont">
       <div align="left" >
          <html:select name="EmgNewRegFB"  property="departmentUnitCode"  tabindex="1"  styleClass ="regcbo" >   
			  <bean:define id="deptUnitCollection" type="java.util.List" name="<%=RegistrationConfig.ESSENTIAL_BO_OPTION_DEPT_UNIT_WITH_CASUALITY%>"></bean:define>
	       <%if(deptUnitCollection.size()>1){ %>
					<html:option value="-1">Select Value</html:option>
					<%} %> 
			  <html:options collection ="<%=RegistrationConfig.ESSENTIAL_BO_OPTION_DEPT_UNIT_WITH_CASUALITY%>" property = "value" labelProperty = "label"/>
	 	 </html:select>  
      	</div>
          </td>
          <logic:notEmpty name="<%=RegistrationConfig.ESSENTIAL_BO_OPTION_ACTIVE_DISASTER_LIST %>">
          <td width="15%"  class="tdfonthead">
                <div align="right">
                <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
               <bean:message key="disasterName"/>    
                </font>
                </div>
         </td>
	   	<td width="17%"  class="tdfont">
       <div align="left" >
          <html:select name="EmgNewRegFB"  property="disasterId"  tabindex="1"  styleClass ="regcbo" >   
					<html:option value="-1">Select Value</html:option>
			  <html:options collection ="<%=RegistrationConfig.ESSENTIAL_BO_OPTION_ACTIVE_DISASTER_LIST%>" property = "value" labelProperty = "label"/>
	 	 </html:select>  
      </div>
          </td>
          </logic:notEmpty>
          <logic:empty name="<%=RegistrationConfig.ESSENTIAL_BO_OPTION_ACTIVE_DISASTER_LIST %>">
          	 <td width="15%"  class="tdfonthead">
               
         	</td>
	   		<td width="17%"  class="tdfonthead">
	       
          </td>
          </logic:empty>
          
            <td width="16%"  class="tdfonthead">
            </td>
	   		<td width="15%"  class="tdfonthead">
       		  </td>
          
          
	</tr>
  </table> 
	
	
</his:ContentTag>
   
  <his:ContentTag>
	
	
      	<table width = "100%"  border="0" cellpadding="0" cellspacing="1">  
       <tr>
			<td width="17%" nowrap class="tdfonthead">
				<div align="right"><font color="#FF0000" size="1"
					face="Verdana, Arial, Helvetica, sans-serif">*</font> <font
					color="#000000" size="2"
					face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
					key="patient" /> <bean:message key="category" /> </font></div>
				</td>
				<td width="19%" class="tdfont"><font color="#000000" size="1"
					face="Verdana, Arial, Helvetica, sans-serif"> <html:select
					name="EmgNewRegFB" property="patPrimaryCatCode"
					tabindex="1" styleClass="regcbo"
					onchange="sendData()">
					<bean:define id="patCatCollection" type="java.util.List" name="<%=RegistrationConfig.ESSENTIALBO_OPTION_PRIMARY_CATEGORY%>"></bean:define>
					<%if(patCatCollection.size()>1){ %>
					<html:option value="-1">Select Value</html:option>
					<%} %>
					<html:options
						collection="<%=RegistrationConfig.ESSENTIALBO_OPTION_PRIMARY_CATEGORY%>"
						property="value" labelProperty="label" />
				</html:select> </font>
				</td>
				
			
				<td class="tdfonthead" width="14%" id="divempIdLabel" style="display: none;"  >
					<div align="right" >
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						        <bean:message 	key="getEmploeeDetailWithDependent" /> 
						 </font>
					 </div>  
				</td>
				<td class="tdfont" width="17%" nowrap id="divempIdControl" style="display: none;" >

					<div align="left"  > <img class="button"
						src="<his:path src='/hisglobal/images/ico_myfriends.gif'/>" tabindex="1"
						border="0" style="cursor:pointer" onkeypress="if(event.keyCode==13) showEmployeepopup(event);" onclick="showEmployeepopup(event);" >
					</div>
					
					<%String path= request.getContextPath();  
						String path1= request.getServletPath();  
						
					%>					
					
				</td>
			
			
				
				
				<td id="divstaffIdLabel" class="tdfonthead" width="14%" style="display: none;" >
					<div align="right"  ><font color="#000000"
						size="2" face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
						key="id" /> </font></div>
				</td>
				<td class="tdfont" width="17%" nowrap id="divstaffIdControl" style="display: none;" >

					<div align="right"  ><html:text
						name="EmgNewRegFB"
						onkeypress="return validateAlphaNumericOnly(event,this);"
						styleClass="textbox" property="patIdNo" maxlength="8"
						tabindex="1" /> <img class="button"
						src="<his:path src='/hisglobal/images/ico_myfriends.gif'/>" tabindex="1"
						border="0" style="cursor:pointer" onkeypress="if(event.keyCode==13) showEmployeepopup(event);" onclick="showEmployeepopup(event);" >
					</div>
					
									
					
				</td>
			
				
				<td width="17%" height="25" nowrap class="tdfonthead" id="divIdTextLabel" style="display:none">
				<div  align="right" >
				<font color="#000000" size="2"
					face="Verdana, Arial, Helvetica, sans-serif"><font color="#FF0000"
					size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font>
					<bean:message key="patientCategoryId" /> </font></div>
				</td>

				<td width="19%" class="tdfont" id="divIdTextBox" style="display:none">
				<div  align="left" >
				<html:text name="EmgNewRegFB" onkeypress="return validateAlphaNumericOnly(event,this);"
						styleClass="textbox" property="patCatIdNo" maxlength="16"
						tabindex="1" ></html:text>
				</div>
				</td>
				<td id="blankCategoryHead" width="17%" height="25" nowrap class="tdfonthead">
				</td>

				<td id="blankCategoryData" width="19%" class="tdfont" >
				</td>	
			
	 
		 <td width="14%" class="tdfonthead" colspan="1">
		 <div align="right">
		<img class="button" style="cursor:pointer" src='<his:path src="/hisglobal/images/camera.png"/>' 
		alt="Capture Photo" title="Capture Photo" onkeypress="if(event.keyCode==13) openPopup('<his:path src="/registration/uploadFile.cnt"/>',event,300,600)" 
		onclick="openPopup('<his:path src="/registration/uploadFile.cnt"/>',event,300,600)" tabindex="2">
		</div>	  
	    </td>
		
		<td class="tdfont" width="18"><img class="button" style="cursor:pointer" src='<his:path 
		src="/hisglobal/images/view.psd.gif"/>' alt="View Photo" title="View Photo" onkeypress="if(event.keyCode==13) 
		openPopup('<his:path src="/registration/enlargedImage.cnt"/>',event,300,600)" onclick="openPopup('<his:path src="/registration/enlargedImage.cnt"/>',event,300,600)" tabindex="2">
		</td>     
	   </tr>  
	   
	   <tr id="poorPatTR">
  	  <td width="19%" class="tdfonthead" >
  	  <div align="right" id="mmjrkDiv" >
  	 	 
			<font color="#000000" size="2"
			face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
			key="mmjrkNo" /> </font></div>
		
	 </td>
	
	 <td class="tdfont" colspan="1">
		 <div align="left" id="mmjrkTextDiv">
		 <html:text
					name="EmgNewRegFB" property="patCardNo"
					styleClass="textbox" maxlength="30" tabindex="1"
					onkeypress="return validateAlphaNumericWithDotsOnly(event,this)"
					 />
	
	 </div>	  
     </td>
   	 <td width="17%" height="25" nowrap  class="tdfonthead" >
	    <div align="right" id="familyHeadDiv" >
		    <font size="2" color="#000000" style="display:block" face="Verdana, Arial, Helvetica, sans-serif">
		    	<bean:message key="familyHeadName" />
	    	 </font>
    	 </div>
        </td>
         
        <td width="17%" class="tdfont">
	        <div  id="familyHeadTextDiv">
				<html:text name="EmgNewRegFB"
					property="patNickName" tabindex="1" styleClass="textbox"
					maxlength="30" onchange="isAlpha(this,'Nick Name')"
					onkeypress="return validateAlphabetsWithDotsOnly(event,this)" />
				</div>
        </td>
        
       <td width="17%" class="tdfonthead" nowrap="nowrap">
				<div align="right" id="relationDiv" >
			
				<font color="#000000" size="2"
					face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
					key="relation" /> </font></div>
				</td>
				

				<td width="17%" class="tdfont" nowrap="nowrap" >
					<div id="relationComboDiv" >
					<html:select name="EmgNewRegFB" tabindex="2" property="patHusbandOccupation"  styleClass="regcbo" >
		            <html:option value="">Select Value</html:option>
				  	<html:option value="28">Aunt</html:option>
					<html:option value="16">Brother</html:option>
					<html:option value="23">Brother In-Law</html:option>
					<html:option value="19">Cousin</html:option>
					<html:option value="18">Daughter</html:option>
					<html:option value="24">Daughter In-Law</html:option>
					<html:option value="11">Father</html:option>
					
					<html:option value="20">Father In-Law</html:option>
					<html:option value="30">Grand daughter</html:option>
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
				</td>
   		 </tr>
     	 <tr>
               <td width="19%" nowrap  class="tdfonthead"> 
        <div align="right">
        <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
        <bean:message key="patient"/>
        <bean:message key="name"/>
        </font></div></td>
        
        <td width="19%" class="tdfont">
        <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
        <font color="#FF0000">*</font>
        <bean:message key="first"/>
        </font>
        </td>

        <td width = "18%" class="tdfonthead" >
        <div align="left" style="display:block">
        <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
        <bean:message key="middle"/>
        </font>
        </div></td>

  		<td width="19%" class="tdfonthead">
			<div align="left"><font color="#000000" size="2"
			face="Verdana, Arial, Helvetica, sans-serif">
			<bean:message key="last" /> </font></div>
		</td>
			
				
	<td width="14%" class="tdfonthead">
				
	</td>
	<td width="17%" class="tdfont">
	</td>
	</tr>
	<tr>
         <td width="14%" class="tdfonthead" >&nbsp;</td>
        <td width="19%" class="tdfont">
        <html:text name="EmgNewRegFB" property="patFirstName"  tabindex="1" styleClass="textbox" maxlength ="30" onblur="isAlpha(this,'First Name')" onkeypress="return validateAlphabetsWithDotsAndForwardSlashWithHypen(event,this)"/>
        </td>
        <td width="18%" class="tdfont">
        <div align="left" style="display:block">
        <html:text name="EmgNewRegFB" property="patMiddleName" tabindex="2" styleClass="textbox" onblur="isAlpha(this,'Middle Name')" maxlength ="20" onkeypress="return validateAlphabetsWithDotsOnly(event,this)"/>
        </div>
        </td>
        <td width="18%" class="tdfont">
        <html:text name="EmgNewRegFB" property="patLastName" tabindex="1" styleClass="textbox" maxlength ="30" onblur="isAlpha(this,'Last Name')" onkeypress="return validateAlphabetsWithDotsOnly(event,this)"/>
        </td>
<%--        <logic:equal name="clientFlag"  value="<%=Config.CLIENT_PGIMER%>">	--%>
				<td width="14%" class="tdfonthead">
				
				</td>
				
				
				<td width="17%" class="tdfont">
				
				
				</td>
<%--				</logic:equal>--%>
				 <logic:notEqual name="clientFlag"  value="<%=Config.CLIENT_PGIMER%>">	
				<td width="14%" class="tdfonthead">
				
				</td>
				
				
				<td width="17%" class="tdfont">
				
				</td>
				</logic:notEqual>
        </tr> 
               
                 <logic:equal name="EmgNewRegFB" property="isAddressDelhi" value="0">                
                   <%
                        divdisplay="none";               
                        divnddisplay="";                   
                       //System.out.println("divdisplay"+divdisplay);
                  %>                                 
                 </logic:equal>
                 
                 <logic:equal name="EmgNewRegFB" property="isAddressDelhi" value="1">				  

				 <%
          			 divdisplay="";               
				     divnddisplay="none";                 
                     //System.out.println("divnddisplay"+divnddisplay);
                 %>				
				</logic:equal>
                        
        <tr>
        
          <td class="tdfonthead" width="19%" nowrap="nowrap">
        <font color="#FF0000">*</font>
        <font color="#000000" size="2" face="l Arial, Helvetica, sans-serif"> 
        <bean:message key="age"/></font>
        <html:radio name="EmgNewRegFB" property="isActualDob" value="0" tabindex="2" onclick="ageSelection()"/>    
   
        <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		<bean:message key="dob"/></font>
        <html:radio name="EmgNewRegFB" property="isActualDob" value="1" tabindex="2" onclick="ageSelection()"/>
        </td>
        <logic:empty name="EmgNewRegFB" property="isActualDob">
        <%
               strdivage="none";
               strdivdob="none";                
        %>
        </logic:empty>
          
        <logic:equal name="EmgNewRegFB" property="isActualDob" value="1">    
            <%
     		 
             strdivage="none";
             strdivdob="";           
          %>               
        </logic:equal> 
        <logic:equal name="EmgNewRegFB" property="isActualDob" value="0">
        <%
           //System.out.println("inside case 0isActualDob:::::::::");	 
           strdivage="";
           strdivdob="none";           
        %>               
        </logic:equal>                    
     	
        <td width="19%" nowrap class="tdfont" >
        <div id="divAge" style='display:<%=strdivage%>'>
        <html:text name="EmgNewRegFB" size="4" property="patAge" tabindex="1" maxlength ="3" onkeypress="return validateNumeric(event)"/> 
        <html:select name="EmgNewRegFB" property="patAgeUnit" tabindex="1" styleClass="smallcombo">
        <html:options collection = "<%=RegistrationConfig.ESSENTIALBO_OPTION_AGE_TYPE %>" property = "value" labelProperty = "label"/>
        </html:select>
        </div>                
      	<%//String strEmpdivdob="none"; 
			//	 if(((EmgNewPatientRegFB)pageContext.findAttribute
				//		 ("EmgNewRegFB")).getPatPrimaryCatCode().equals(Config.PRIMARY_CATEGORY_EMPLOYEE_CODE)){
					//	 strEmpdivdob="";
						// strdivdob="none";
						 //}%>
        <div id="divDob" style='display:<%=strdivdob%>'>         
        <bean:define name="EmgNewRegFB" property="patDOB" id="dob" type="java.lang.String"/>      
                 
		<div id="divPatDOB"></div>
		<script type="text/javascript">DateValidator.setup("divPatDOB","patDOB","<%=dob%>","dd-MM-yyyy","textbox");</script>
							
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
       </div></td>
       
       <td width="17%" class="tdfont">
       <html:select name="EmgNewRegFB" property="patGenderCode" tabindex="1" styleClass="regcbo">
       <html:option value="-1">Select Value</html:option>
	   <html:options collection = "<%=RegistrationConfig.ESSENTIALBO_OPTION_GENDER %>" property  = "value" labelProperty = "label"/>
	   </html:select>
       </td>
       
       <td width="18%" class="tdfonthead">
        <div align="right">
        <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
        <bean:message key="monthlyIncome"/>
        </font>
        </div>
        </td>
        
        <td class="tdfont" width="18%">
        <html:text name="EmgNewRegFB" property="patMonthlyIncome" styleClass="textbox" tabindex="2" maxlength="11" onkeypress="return(currencyFormat(this,',','.',event))"/>
        </td>
      
       </tr>
       
      
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
	        <html:text name="EmgNewRegFB" property="patGuardianName" tabindex="1" styleClass="textbox" onblur="isAlpha(this,'Middle Name')" maxlength ="60" onkeypress="return validateAlphabetsWithDotsOnly(event,this)"/>
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
	         <html:text name="EmgNewRegFB" property="patHusbandName" styleClass="textbox" tabindex="1" maxlength="60" onkeypress="return validateAlphabetsWithDotsOnly(event,this)"/>
	        </td> 
        
        	 <td width="17%" class="tdfonthead" nowrap>
			<div align="right"><font color="#000000" size="2"
				face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
				key="motherName" /></font></div>
			</td>
			<td width="17%" class="tdfont"><html:text name="EmgNewRegFB"
				property="patMotherName" styleClass="textbox" tabindex="2"
				maxlength="60" onchange="isAlpha(this,'Mother Name')"
				onkeypress="return validateAlphabetsWithDotsOnly(event,this)" />
			</td>
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
 		<div id=patCountryCombo>
        <html:select name="EmgNewRegFB" property="patNationalityCode" tabindex="2" styleClass="regcbo" >
	    <html:option value="-1">Select Value</html:option>
		<html:options collection ="<%=RegistrationConfig.ESSENTIALBO_OPTION_NATIONALITY %>"property = "value" labelProperty = "label"/>
        </html:select>	
        </div>
        </td>
        
       
        <td width="14%"  class="tdfonthead">
        <div align="right">
        <font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font>
        <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
        <bean:message key="religion"/>
        </font>
        </div>
        </td>
      
        
         
        
        <td width="17%"  class ="tdfont">
        <html:select name="EmgNewRegFB" property="patReligionCode" tabindex="2" styleClass="regcbo">
        <html:option value="-1">Select Value</html:option>
        <html:options collection = "<%=RegistrationConfig.ESSENTIALBO_OPTION_RELIGION %>" property = "value" labelProperty = "label"/>
        </html:select>
        </td>
        
         <td width="18%" class="tdfonthead">
       <div align="right">
       <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
       <bean:message key="category"/>
       </font>
       </div>
       </td>
       
       <td class="tdfont">
       <html:select name="EmgNewRegFB" property="patMaritalStatusCode" tabindex="2" styleClass ="regcbo">
       <html:option value="-1">Select Value</html:option>
	   <html:options collection = "<%=RegistrationConfig.ESSENTIALBO_OPTION_MARITAL_STATUS %>" property = "value" labelProperty = "label"/>
	 
       </html:select>
       </td>
        
        </tr>
         <logic:equal name="clientFlag"  value="<%=Config.CLIENT_PGIMER%>">
			
			<tr id="occupationdetail" >
				<td width="17%" class="tdfonthead" nowrap="nowrap" >
				<div align="right"><font color="#000000" size="2"
					face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
					key="patOccupation" /> </font></div>
				</td>
				<td width="17%" class="tdfont" nowrap>  
					<html:select name="EmgNewRegFB" tabindex="2" property="patOccupation"  styleClass="regcbo" >
		            <html:option value="">Select Value</html:option>
				  	<html:options collection ="<%=RegistrationConfig.ESSENTIALBO_OPTION_OCCUPATION_DTL %>"property = "value" labelProperty = "label"/>
                    </html:select>	
				</td>
				<td width="17%" class="tdfonthead" nowrap="nowrap" >
				<div align="right"><font color="#000000" size="2"
					face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
					key="patFatherOccupation" /> </font></div>
				</td>
					
				<td class="tdfont" width="17%" nowrap="nowrap" >
					<html:select name="EmgNewRegFB" tabindex="2" property="patFatherOccupation"  styleClass="regcbo" >
		            <html:option value="">Select Value</html:option>
				  	<html:options collection ="<%=RegistrationConfig.ESSENTIALBO_OPTION_OCCUPATION_DTL %>"property = "value" labelProperty = "label"/>
                    </html:select>
				</td>
				<td width="17%" class="tdfonthead" nowrap="nowrap" >
				
				</td>
				<%//System.out.println("before dept22223");

				%>

				<td width="17%" class="tdfont" nowrap="nowrap" >
				</td>
				<td class="tdfonthead" width="17%"></td>
				<td class="tdfont" width="17%"></td>
			</tr>
			
				</logic:equal>    
        
        <tr>
       
         <tr >  
        <td width="18%" class="tdfonthead" >
        <div align="right">
        <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	    <bean:message key="idMark"/>
	    <logic:equal name="EmgNewRegFB" property="isUnknown" value="1">
<!--	    <font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font>-->
	    </logic:equal>
	    </font>
	    </div>
	    </td>

         <td width="17%" colspan="2"  class="tdfont" > 
         <html:text  name="EmgNewRegFB"  size="40" maxlength="100"  property="patIdMark1" tabindex="2" onkeypress="return validateAlphabetsOnly(event,this)"/>
		 </td>
		 
		 <td width="18%" class="tdfonthead" >
        <div align="right" style="display:none;">
        <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	    <bean:message key="idMark2"/>
	    <logic:equal name="EmgNewRegFB" property="isUnknown" value="1">
<!--	    <font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font>-->
	    </logic:equal>
	    </font>
	    </div>
	    </td>

         <td width="17%" colspan="2"  class="tdfont" > 
          <div align="left" style="display:none;">
         <html:text  name="EmgNewRegFB"  size="40" maxlength="100" property="patIdMark2" tabindex="2" onkeypress="return validateAlphabetsOnly(event,this)"/>
         </div>
		 </td>
        
      </tr>      
     
                
        
        <tr>
     <logic:equal name="clientFlag"  value="<%=Config.CLIENT_GNCTD%>">	
				<td width="17%" class="tdfonthead">
				<div align="right"><font color="#000000" size="2"
					face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
					key="micnrNo" /> </font></div>
				</td>
				</logic:equal>
				<logic:notEqual name="clientFlag"  value="<%=Config.CLIENT_GNCTD%>">	
				<td width="17%" class="tdfonthead">
				<div align="right"><font color="#000000" size="2"
					face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
					key="uniqueId" /> </font></div>
				</td>
				</logic:notEqual>
				<td width="17%" class="tdfont" nowrap="nowrap"><html:text
					name="EmgNewRegFB" property="patNationalId"
					styleClass="textbox" maxlength="16" tabindex="2"
					onkeypress="return validateAlphaNumericWithSpecialCharacterOnly(event,this)"
					styleClass="textbox" /></td>       
				
	       	 <logic:equal name="clientFlag"  value="<%=Config.CLIENT_PGIMER%>">	
     
				<td width="17%" class="tdfonthead" nowrap="nowrap" >
			
				</td>
				
				<td width="17%" class="tdfont" nowrap>
				</td>
			
        </logic:equal>
        
          <logic:notEqual name="clientFlag"  value="<%=Config.CLIENT_PGIMER%>">	
          <td width="17%" class="tdfonthead" nowrap="nowrap" >
          </td>
          	<td width="17%" class="tdfont" nowrap>
          	</td>
     	 </logic:notEqual>
	       	
	     	   <td width="17%" class="tdfonthead" >
	       <div align="right">
	       <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	       <bean:message key="amountCollected"/> 
	       </font>
	       </div>
	    </td>	        
         <% //System.out.println("before dept22223"); %>

	       <td width ="17%" class="tdfont" >
	       <html:text name="EmgNewRegFB" property="patAmountCollected"   maxlength="10,2" readonly="true" styleClass="textbox" />
	       </td> 
	      
		</tr>
       
          
	 
    </table>
   
	</his:ContentTag>
	 
	  
            

        <bean:define name="EmgNewRegFB" property="isReferred" id="isref" type="java.lang.String"/>            
        <logic:equal name="isref" value="1">  
        <%
               divisrefdisplay="";  
            %>        
        </logic:equal> 
        <%
         
        %>
       <logic:equal name="isref" value="">  
            <%
             //  System.out.println("inside isref 0");
               divisrefdisplay="none";                 
               divRefGnctdHospitalCode="none";
               divRefHospname="none";
             ///  System.out.println("inside isref 0");
               			
            %>        
        </logic:equal>
          <logic:equal name="isref" value="0">  
            <%
             // System.out.println("inside isref 0");
               divisrefdisplay="none";                 
               divRefGnctdHospitalCode="none";
               divRefHospname="none";
              
              // System.out.println("inside isref 0");
            %>        
        </logic:equal>
         <logic:equal name="isref" value="1">  
            <logic:equal name="EmgNewRegFB" property="referringInstType" value="O">                    
             <%
                     // System.out.println("patRefGnctdHospitalCode:::::::::empty");
                      divRefGnctdHospitalCode="none"; 
                      divRefHospname  ="";
                  	  
             %>             
           </logic:equal>
           <logic:equal name="EmgNewRegFB" property="referringInstType" value="G">                    
             <%
                      System.out.println("patRefHospitalName:::::::::empty");
                      divRefGnctdHospitalCode=""; 
                      divRefHospname  ="none";
	                 
             %>             
           </logic:equal>           
        </logic:equal>                           
         
        <logic:equal name="isref" value="1">  
            <logic:empty name="EmgNewRegFB" property="patRefGnctdHospitalCode">                    
             <%
                     // System.out.println("patRefGnctdHospitalCode:::::::::empty");
                      divRefGnctdHospitalCode="none"; 
                      divRefHospname  ="";
             %>             
           </logic:empty>
           <logic:empty name="EmgNewRegFB" property="patRefHospitalName">                    
             <%
                    //  System.out.println("patRefHospitalName:::::::::empty");
                      divRefGnctdHospitalCode=""; 
                      divRefHospname  ="none";
             %>             
           </logic:empty>           
        </logic:equal>                           
          <%//System.out.println("divRefHospname:::::::::"+divRefHospname);%>           
        
      
      
       
	 
	 <his:SubTitleTagBroad name="Address Detail">
		   <table width="100%">
		   <tr>
             <td>	  
             		  <font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font>
                      <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
                      <bean:message key="country"/></font>
             </td>       
 			 <td>
 			 	       <html:select name="EmgNewRegFB" tabindex="1" property="patAddCountryCode"  styleClass="regcbo" onchange="showState()">
		                  	<html:option value="-1">Select Value</html:option>
				  		   <html:options collection ="<%=RegistrationConfig.ESSENTIALBO_OPTION_COUNTRY %>"property = "value" labelProperty = "label"/>
                       </html:select>	

             </td>
            <td>
             
             <div id="stateMandotary" style="" >
             		  <font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font>
             		  <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
             		  		<bean:message key="state"/>
             		  </font>
              </div>
              <div id="stateNotMandotary" style="" >
             		  <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
             		  		<bean:message key="state"/>
             		  </font>
              </div>
             </td>
             <td>     
             		 <div id="divpatAddStateCodeCombo" style="">                 
                     <html:select name="EmgNewRegFB" tabindex="1" property="patAddStateCode" styleClass="regcbo" onchange="if(this.value!='-1') sendDataForStateChange(this)" >
                       <html:option value="-1">Select Value</html:option>
                       <html:options collection = "<%=RegistrationConfig.ESSENTIALBO_OPTION_STATE %>" property = "value" labelProperty = "label"/>
                     </html:select>    
                     </div> 
                     <div id="divpatAddStateCodeText" style="" >
                     <html:text name="EmgNewRegFB" tabindex="1" property="patAddStateName" styleClass="regcbo" maxlength="50" onkeypress="return validateAlphaNumericWithDotsOnly(event,this)">
                     </html:text>
                     </div>                 
             </td>
             </tr>
             </table>
             </his:SubTitleTagBroad>   
             
 <his:ContentTag>
 
 <table width="100%"  cellspacing="1" cellpadding="0">                    
 <tr>
             					         

                  
 
                    <td  class="tdfonthead" width="14%" >
                    <div align="right">
	                     <font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font>
	                     <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	                     <bean:message key="address"/>
	                     </font> 
                    </div>                    
                    </td>        
                                
                <td  class="tdfont" width="17%" colspan="3">
                <html:textarea  name="EmgNewRegFB" onkeypress="return CheckMaxLength(event,this,100,1);" 
                	property="patAddHNo" tabindex="2" cols="55" rows="2"/>
                 <html:hidden  name="EmgNewRegFB" property="patAddStreet"/> 
                </td>
               
               <%-- 
	            <td  class="tdfonthead" width="18%">                    
	            <div align="right">
	            
	            <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	            <bean:message key="street"/>  
	            </font>
	            </div>                    
	            </td>
	                     
	            <td class="tdfont" width="17%">
	            <html:text  name="EmgNewRegFB" property="patAddStreet" onkeypress="return validateAlphaNumericWithDotsOnly(event,this);" tabindex="2" maxlength="30" styleClass ="textbox"/> 
	            </td>
                  --%>
                  
                <td  class="tdfonthead" width="17%">
	            <div align="right">
	            
	                     
	            <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	            <bean:message key="tehsil"/>
	            </font>
	            </div>
             </td>
 
 
     		  <td  class="tdfont" width="17%">
       
          
                <div id="divpatAddCityLocCode" >                
	            <html:select name="EmgNewRegFB" tabindex="1" styleClass="regcbo" property="patAddCityLocCode" onchange="if(this.value!='-1') sendDataForCityLocation(this)">
				<html:option value="-1">Select Value</html:option>
				<html:options collection ="<%=RegistrationConfig.ESSENTIALBO_OPTION_LOCATION%>" property = "value" labelProperty = "label"/>
	            </html:select>
	            </div>	                    	                    	                    	                    	                    	                    	                    
	      
	            <div id="divpatAddCityLocation" >	                     
				<html:text  name="EmgNewRegFB" tabindex="2"  maxlength="50" onkeypress="return validateAlphaNumericWithDotsOnly(event,this);" styleClass="textbox" property="patAddCityLoc"/>
				</div>
				
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
                <html:select name="EmgNewRegFB" tabindex="2" styleClass="regcbo" property="patAddDistrictCode">
				<html:option value="-1">Select Value</html:option>
				<html:options collection ="<%=RegistrationConfig.ESSENTIAL_BO_OPTION_DISTRICT_LIST_STATEWISE%>" property = "value" labelProperty = "label"/>
	            </html:select>
                </div>
                <div id="districtTextBox" style="display:none">
                <html:text  name="EmgNewRegFB" tabindex="2" property="patAddDistrict" onchange="isAlpha(this,'District')"  onkeypress="return validateAlphabetsWithDotsOnly(event,this)" maxlength="30" styleClass="textbox"/>
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
	            <html:text name="EmgNewRegFB" tabindex="1" property="patAddCity" onchange="isAlpha(this,'City')" onkeypress="return validateAlphabetsWithDotsOnly(event,this);" maxlength="30" styleClass="textbox"/> 
                </td>
                
                <td class="tdfonthead" width="14%">
	            <div align="right">
	            <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	            <bean:message key="pin"/>
	            </font> 
	            </div>
                </td>       
                  <%
							String pinCode = ((EmgNewPatientRegFB) pageContext
							.findAttribute("EmgNewRegFB")).getPatAddPIN();
					if (pinCode == null) {
						pinCode = "";
					}
					%>
                <td  class="tdfont" width="17%">
                <input type="text" id="pintext"  name="patAddPIN" tabindex="2"
                 size="17"  maxlength ="6"  onkeypress="return validateNumeric(event)" value="<%=pinCode %>"  /> 
                  </td>
                                                    
                    
              </tr>
                    
              <tr>
                      
                    <td  class="tdfonthead"  width="17%" style="display: none">
                    <div align="right" >
                    <font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font>
                    <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
                    <bean:message key="contactNo"/>
                    </font>
                    </div>
                    </td>
             		
                    <td  class="tdfont" width="17%" style="display: none">   
 		            <div >
         		    <html:text name="EmgNewRegFB" tabindex="1" property="patAddContactNo"  maxlength ="30" styleClass="textbox" onkeypress="return validateNumeric(event)"/> 
                    </div>
                    </td>
                    
             <td width="18%" class="tdfonthead" style="display: none">
				<div align="right"><font color="#000000" size="2"
					face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
					key="areaCategory" /> </font></div>
				</td>

				<td width="18%" class="tdfont" style="display: none"><html:select
					name="EmgNewRegFB" property="patIsUrban" tabindex="2"
					styleClass="regcbo">
					<html:option value="">Select Value</html:option>
					<html:options
						collection="<%= RegistrationConfig.ESSENTIALBO_OPTION_AREA_CATEGORY %>"
						property="value" labelProperty="label" />
				</html:select></td> 
             <td class="tdfonthead" width="18%" style="display: none"></td>
             <td class="tdfont" width="18%" style="display: none"></td>    
                  </tr>		
                </table>
   
 </his:ContentTag>


	 			<!--................................... code for referred details........... -->
	


<bean:define id="confFlagId" name="<%=Config.EMG_BROUGHT_BY_DETAIL_FLAG%>" ></bean:define>
	 <logic:equal name="confFlagId" value="<%=Config.EMG_BROUGHT_BY_DETAIL_FLAG_VALUE_TRUE%>">
	<his:SubTitleTag name="Brought By Details"> 
	  <table width="100%" cellspacing="1" cellpadding="0">
	  <tr>
	  <td width="90%">
	  <div align="right">
	  <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	          <bean:message key="isBroughtBy"/>      
	        </font>
	        </div>
	  </td>
	<td   >
	<div align="left" >
	
        	<html:checkbox name="EmgNewRegFB" tabindex="2" property="isBroughtBy" onclick="broughtBy(this)" tabindex="2"/>
      
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
        	<html:select name="EmgNewRegFB" property="isRelative" styleClass="regcbo" onchange="enableRelation(this)" tabindex="1">
        		<html:option value="-1">Select Value</html:option>
        		
        	 <logic:notEqual value="1" name="EmgNewRegFB" property="isUnknown">	
        		<html:option value="<%=RegistrationConfig.PATIENT_BROUGHT_BY_RELATIVE %>">Relative</html:option>
        	</logic:notEqual>
        		
        		<html:option value="<%=RegistrationConfig.PATIENT_BROUGHT_BY_POLICE %>">Police</html:option>
        		<html:option value="<%=RegistrationConfig.PATIENT_BROUGHT_BY_OTHER%>">Other</html:option>
        		<html:option value="<%=RegistrationConfig.PATIENT_BROUGHT_BY_108%>">108</html:option>

        	</html:select>
<!--             <html:checkbox name="EmgNewRegFB" tabindex="1" property="isRelative" onclick="enableRelation(this)" />-->
        </div>
        </td>
       <td width="25%"  class="tdfonthead" >
       <div align="right">
       <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
       <bean:message key="realtionship"/>
       </font>
       </div></td>
       
       <td width="25%" class="tdfont"  >
       <html:select name="EmgNewRegFB" property="broughtByRelationCode" tabindex="1" styleClass="regcbo">
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
       <html:select name="EmgNewRegFB" property="broughtByGenderCode" tabindex="1" styleClass="regcbo">
       <html:option value="-1">Select Value</html:option>
         <html:option value="1">Male</html:option>
           <html:option value="2">Female</html:option>
             <html:option value="3">Other</html:option>
	   </html:select>
       </td>--%>
      </tr>
     
      <tr>
       <td width="25%" class="tdfonthead"><!-- 16 -->
        <div align="right">
        <font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font>
	        <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	          <bean:message key="name"/>      
	        </font>
	     </div>
        </td>        
        <td width="25%" class="tdfont" > <!-- 18 -->
         <div align="left">
             <html:text name="EmgNewRegFB" tabindex="1" property="broughtByName" onkeypress="return validateAlphabetsWithDotsOnly(event,this)" tabindex="1" styleClass="textbox" onblur="isAlpha(this,'Middle Name')" maxlength="60"/>
            </div>
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
         <div align="left">
             <html:text name="EmgNewRegFB" tabindex="1" property="broughtByLocation"  maxlength="30" styleClass="textbox" onkeypress="return validateAlphabetsWithDotsOnly(event,this)"/>
             </div>
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
		      	 <div align="left">
		      		<html:text name="EmgNewRegFB" property="constableDesig" styleClass="textbox" maxlength="50" tabindex="1" onkeypress="return validateAlphaNumericOnly(event,this)"></html:text>
		      	</div>
		      	</td>
		      	<td width="25%" class="tdfonthead">
		      	 <div align="right">
		      	<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font>
		      		 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				       <bean:message key="badgeNo"/>
				      </font>
				  </div>
		      	</td>
		      	<td width="25%" class="tdfont">
		      	 <div align="left">
		      		<html:text name="EmgNewRegFB" property="constableBadgeNo" styleClass="textbox" maxlength="50" onkeypress="return validateAlphaNumericOnly(event,this)" tabindex="1"></html:text>
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
       <td width="50%"  style="background-color:#FFEBD5;height:45;font-family: 'MS Sans Serif';	font-size:18px; color:#653232;text-align:right;">
         <div align="right">
      <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	          <bean:message key="phone"/>  <bean:message key="no"/>        
	        </font>
	     </div>
       </td>
     
       
       <td width="50%"  style="background-Color:#F1ECE2;height:45;color:#990000;text-align:left;text-transform: capitalize;font-family:'Trebuchet MS';	font-size: 10px;font-weight: bold;font-variant: normal;">
        <div align="left">
         <html:text name="EmgNewRegFB" tabindex="2" property="broughtByPhone"  maxlength="30" styleClass="textbox" onkeypress="return validateNumeric(event)"/>
        </div>
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
       	 <div align="left">
            <html:textarea styleClass="textarea2" onkeypress="return CheckMaxLength(event,this,100,3)"  tabindex="2" name="EmgNewRegFB" property="broughtByAddress"/>
           </div>
        </td>
        </tr>
        </table>
        </td>
      </tr>
</table>

</his:ContentTag>
</div>
</logic:equal>
	 
 
  <bean:define name="EmgNewRegFB" property="isReferred" id="isref" type="java.lang.String"/>            
        
        <logic:equal name="isref" value="1">  
            <%
               divisrefdisplay="";  
            %>        
        </logic:equal> 
                     
        <logic:equal name="isref" value="">  
            <%
       
               divisrefdisplay="none";                 
               divRefGnctdHospitalCode="none";
               divRefHospname="none";
       
               divDocname="none";
			   divDocTitle="none";
			   divref="none";
			   divRefGnctd="none";				
            %>        
        </logic:equal>
          <logic:equal name="isref" value="0">  
            <%
              System.out.println("inside isref 0");
               divisrefdisplay="none";                 
               divRefGnctdHospitalCode="none";
               divRefHospname="none";
               divDocname="none";
				divDocTitle="none";
				divref="none";
				divRefGnctd="none";
               System.out.println("inside isref 0");
            %>        
        </logic:equal>
        
        <logic:equal name="isref" value="1">  
            <logic:equal name="EmgNewRegFB" property="referringInstType" value="O">                    
             <%
                      System.out.println("patRefGnctdHospitalCode:::::::::empty");
                      divRefGnctdHospitalCode="none"; 
                      divRefHospname  ="";
                  	  divDocname="";
					  divDocTitle="";
					  divref="";
       				divRefGnctd="";
             %>             
           </logic:equal>
           <logic:equal name="EmgNewRegFB" property="referringInstType" value="G">                    
             <%
                      System.out.println("patRefHospitalName:::::::::empty");
                      divRefGnctdHospitalCode=""; 
                      divRefHospname  ="none";
	                  divDocname="";
					  divDocTitle="";
					  divref="";
						divRefGnctd="";
             %>             
           </logic:equal>           
        </logic:equal>                           
          <%System.out.println("divRefHospname:::::::::"+divRefHospname);%>     
          
            
			<!--................................... code for referred details........... -->
	
  <his:SubTitleTag name="Refer Details">
  <table width="100%" cellspacing="1" cellpadding="1">
  
  </table>
   <table width="100%" cellspacing="1" cellpadding="0">
	  <tr>
	  <td width="90%">
	  <div align="right">
	  <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	          <bean:message key="isreferred"/>      
	        </font>
	        </div>
	  </td>
	<td   >
	<div align="left" >
	
        	  <html:checkbox name="EmgNewRegFB" tabindex="2" property="isReferred"  onclick="Isreferred(this)" value="1"/>
      
  </div>
  </td>
  </tr>
  </table>
  </his:SubTitleTag>
			
 	<div id="divReferred" style="display:<%=divRefGnctd%>">
 	<his:ContentTag>

		<table width="100%" cellpadding="1" cellspacing="1">
			<tr>
				<td width="50%" class="tdfonthead" colspan="2">
				<div id="divReferredInstitute" style="display:<%=divref%>" align="right">
			      
				<font
						color="#FF0000" size="1"
						face="Verdana, Arial, Helvetica, sans-serif">*</font> <font
						color="#000000" size="2"
						face="Verdana, Arial, Helvetica, sans-serif"> &nbsp;&nbsp;&nbsp;<bean:message
						key="gnctd" /> </font><html:radio name="EmgNewRegFB"
						property="referringInstType" tabindex="1" value="G"
						onclick="showdivhoscode(this)" /> 
<!--				</td>-->
<!--				<td width="25%" class="tdfont" nowrap="nowrap">-->
				<font color="#000000" size="2"
						face="Verdana, Arial, Helvetica, sans-serif"> &nbsp;&nbsp;&nbsp;<bean:message
						key="other" /> </font><html:radio
						name="EmgNewRegFB" property="referringInstType" value="O"
						tabindex="1" onclick="showdivhoscode(this)" />
				</td>
			
			<td width="25%" class="tdfonthead" nowrap="nowrap" colspan="1">
	         		&nbsp;&nbsp;
	         		<b><font
						color="#FF0000" size="1"
						face="Verdana, Arial, Helvetica, sans-serif">*</font> <font
						color="#000000" size="2"
						face="Verdana, Arial, Helvetica, sans-serif">
			   <bean:message key="hospital"/>
			   <bean:message key="name"/></font></b>
			   </td>
			   <td width="25%" class="tdfont"  colspan="1">
			   <div id='divRefHosCode' style='display:<%=divRefGnctdHospitalCode%>'>	
                  <html:select name="EmgNewRegFB" tabindex="1" property="patRefGnctdHospitalCode" styleClass="registrationCmb">
		         <html:option value="-1">Select Value</html:option>
				  <html:options collection = "<%=RegistrationConfig.ESSENTIALBO_OPTION_REF_HOSPITAL%>" property = "value" labelProperty = "label"/>		  
	          	  </html:select>
	         </div>	        
			<div id='divRefHosname' style='display:<%=divRefHospname%>'>
			  			  		   
			   <html:text  name="EmgNewRegFB" tabindex="1" property="patRefHospitalName" onkeypress="return validateAlphabetsOnly(event,this)" maxlength="100" styleClass="textboxBig" size="20"/>
	        </div>
       		 </td>  
			</tr>
		</table>
		</his:ContentTag>
		<his:ContentTag>	
		<table width="100%" cellpadding="1" cellspacing="1">
			<tr>
				<td width="25%" class="tdfonthead">
				<div align="right"><font color="#000000" size="2"
					face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
					key="referring" /> <bean:message key="hospital" /> <bean:message
					key="crNo" /> </font></div>
				</td>

				<td width="25%" nowrap="nowrap" class="tdfont"><html:text
					name="EmgNewRegFB" tabindex="1"
					property="patRefGnctdHospitalCrno" maxlength="13" tabindex="1"
					onkeydown="setPrevValue(this, event);"
					onkeyup="moveToRight(this, event);"
					onkeypress="return validateNumeric(event)" styleClass="textbox" />
				</td>
				<td width="15%" class="tdfonthead" nowrap>
				<div align="right"><font color="#FF0000" size="1"
					face="Verdana, Arial, Helvetica, sans-serif">*</font> <font
					color="#000000" size="2"
					face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
					key="referredBy" /></font></div>
				
				</td>
			
				<td width="15%" colspan="2" class="tdfont">
				<div align="left"><html:text name="EmgNewRegFB"
					 maxlength="60" property="patRefDoctor" 
					onchange="isAlpha(this,'Referred By Doctor')" tabindex="1" styleClass="textbox"
					onkeypress="return validateAlphabetsOnly(event,this)" /></div>
				
				</td>
				</tr>
				<tr>
				<td width="25%" class="tdfonthead" nowrap>
				<div align="right"><font color="#000000" size="2"
					face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
					key="referring" /> <bean:message key="hospital" /> <bean:message
					key="department" /> </font></div>
				</td>

				<td width="25%" class="tdfont">
				<html:select name="EmgNewRegFB" property="patRefGnctdHospitalDept" tabindex="1" styleClass="regCbo" onchange="checkReferDepartment(this)">
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
				<html:text name="EmgNewRegFB"  property="patRefHospitalDeptOther" maxlength="20"  tabindex="1" styleClass="textbox" disabled="true" onkeypress="return validateAlphabetsOnly(event,this)"/>
				</td>
			</tr>

			<tr>
				<td width="25%" class="tdfonthead">
				<div align="right">
				<font color="#000000" size="2"
					face="Verdana, Arial, Helvetica, sans-serif">
				<bean:message key="referring" /> <bean:message
					key="hospital" /> <bean:message key="unit" />
				</font></div>
				</td>

				<td width="25%" class="tdfont"><html:text name="EmgNewRegFB"
					property="patRefGnctdHospitalDeptUnit" tabindex="1" maxlength="15"
					styleClass="textbox" onkeypress="return validateAlphaNumericOnly(event,this)"/></td>
				<td width="25%" class="tdfonthead"></td>	
				<td width="25%" class="tdfont"></td>	
			</tr>
			
		</table>
	</his:ContentTag></div>
 
 <%
 
         //	System.out.println("dt::::::::::::::::");        	
        	Date dt=(Date)session.getAttribute(Config.SYSDATEOBJECT);
        	//System.out.println("dt:::::::::"+dt);        	
             String 	sysDate = hisglobal.presentation.WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy");
   
%>   

  <input type="hidden" name="sysDate" value="<%=sysDate%>"/>
 			<!--.......................... code for referred details......... ends here.......... -->
 
	 
	 <his:ButtonToolBarTag>
          <div align="center">
          <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>' tabindex="1" style="cursor:pointer" onkeypress="if(event.keyCode==13) submitFormOnValidate(validateEmg(),'SAVE');"   
          onclick ="submitFormOnValidate(validateEmg(),'SAVE');" >
	      <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' tabindex="1"  style=cursor:pointer onkeypress="if(event.keyCode==13) submitPage('CANCEL');" tabindex="1" onclick ="submitPage('CANCEL');">
          <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style="cursor:pointer" tabindex="1" onclick ="submitForm('NEW')" onkeypress="if(event.keyCode==13) submitForm('NEW');">
    </div>         
	</his:ButtonToolBarTag>
    
<%System.out.println("....2");%>
<input type="hidden" name="hmode" value="unspecified" /> 
<html:hidden name="EmgNewRegFB" property="isIdRequired"/>
<html:hidden name="EmgNewRegFB" property="patIdNo"/>
<html:hidden name="EmgNewRegFB" property="patDataFromEmployeeTable"/>
<html:hidden name="EmgNewRegFB" property="empIdChk"/>
<html:hidden name="EmgNewRegFB" property="patAddDistrictCodeHidden"/>
<html:hidden name="EmgNewRegFB" property="patAddPINHidden"/>
<html:hidden name="EmgNewRegFB" property="patAddCityHidden"/>
<html:hidden name="EmgNewRegFB" property="patIsUrbanHidden"/>

</his:statusTransactionInProcess>
<his:status/>


 
