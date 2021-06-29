<%try{ %>
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>

<%@page import="hisglobal.hisconfig.Config"%>
<%@page import="registration.controller.fb.EmgNewPatientRegFBDuplicate"%>
<%@ page import ="java.util.*,registration.*" %>

<head> 

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>Emergency Patient Registration</title>
</head>


<style type="text/css"> 
@media print { 
		#noprint 
		{
		 display: none; 
		}
		#tabHeader 
		{
			display: none;
		}
		#divCRNoLabel 
		{
			display: block;
		}
		#divEpisodeDateLabel 
		{
			display: block;
		}
		#divPatientNameLabel 
		{
			display: block;
		}
		#divAgeLabel 
		{
			display: block;
		}
		#divGenderLabel 
		{
			display: block;
		}
		#divAreaLabel 
		{
			display: block;
		}
		#divTokenLabel 
		{
			display: block;
		}
		#divConsultantLabel 
		{
			display: block;
		}
	    #divDiagnosisLabel 
		{
			display: block;
		}
		#divOccupationLabel 
		{
			display: block;
		}
		#divOPDCardLabel 
		{
			display: block;
		}
		#divOPDCardLogo 
		{
			display: block;
		}
		#divOPDCardHeader 
		{
			display: block;
		}
		#divPatientRegisteredLabel 
		{
			display: none;
		}
		#divBlank1 
		{
			display: block;
		}
		#divBlank2 
		{
			display: block;
		}
		
		
}
 td.TdBorder{
 		 border:2px solid black;
		}
		#divFooter 
		{
            width:779px; 
            margin-top:20px;
        }
 
 </style>
<script language="JavaScript" src="/HISClinical/registration/js/registration.js"></script>
<script language="JavaScript" src="/HISClinical/registration/js/validationCalls.js"></script>
<script language="JavaScript" src="/HISClinical/registration/js/validationCommon.js"></script>
<script language="JavaScript" src="/HISClinical/registration/js/commonFunctions.js"></script>
<script language="JavaScript" src="/HISClinical/registration/js/dateFunctions.js"></script>
<script language="JavaScript" src="/HISClinical/registration/js/calendar.js"></script>
<script language="JavaScript" src="/HISClinical/registration/js/popup.js"></script>
<script language="JavaScript" src="/HISClinical/hisglobal/js/utilityFunctions.js"></script>
<script language="JavaScript" src="/HISClinical/registration/js/emgNewRegistration.js"></script>
<script language="JavaScript" src="/HISClinical/hisglobal/utility/generictemplate/js/validationFunctions.js"></script>
<script language="JavaScript" src="/HISClinical/hisglobal/utility/generictemplate/js/date_validator.js"></script>

<script language="JavaScript" src="/HISClinical/hisglobal/js/multilingualSupport/pramukhime.js"></script>
<script language="JavaScript" src="/HISClinical/hisglobal/js/multilingualSupport/pramukhindic.js"></script>
<script language="JavaScript" src="/HISClinical/hisglobal/js/multilingualSupport/pramukhime-common.js"></script>
<script language="JavaScript" src="/HISClinical/hisglobal/js/multilingualSupport/multilingualSupport.js"></script>
<script>

function openBPLPopup(e)
{
		//openPopup('/HISClinical/registration/transaction/regBPLSearchPopup.jsp',e,300,600);
		var hmode = "CHECKBPLDETAILS"; 
		var bplCardNo=document.forms[0].patBPLCardNo.value;
		var url = "/HISClinical/registration/newRegistrationDeskDuplicate.cnt?hmode="+hmode+"&bplCardNo="+bplCardNo;
		ajaxFunction(url,"3");
}
function getDistrictBasedOnState(obj)
{
	var stateCode=obj.value;
	var defaltStateCode="<%=RegistrationConfig.REGISTRATIONDESK_DEFAULT_STATE_CODE%>";  
	if(stateCode!="" && stateCode!='-1')
	{
		if(stateCode==defaltStateCode)
		{
	 		document.getElementById("districtCombo").style.display="";
	 		document.getElementById("districtComboNonDefault").style.display="none";
	 	}
	 	else
	 	{
	 		var hmode = "GETDISTRICTLISTBASEDONSTATE"; 
			var url = "/HISClinical/registration/newRegistrationDeskDuplicate.cnt?hmode="+hmode+"&patAddStateCode="+stateCode;
			ajaxFunction(url,"2");
			document.getElementById("districtCombo").style.display="none";
			document.getElementById("districtComboNonDefault").style.display="";
		}
 	}
}
var queryString;

function spouseNameMandatory()
{
	var maritalStatus=document.getElementsByName("patMaritalStatusCode")[0].value;
	var maritalStatusMarried="<%=RegistrationConfig.REGISTRATIONDESK_MARITAL_STATUS_MARRIED%>";
	var gender=document.getElementsByName("patGenderCode")[0].value;
	var genderFemale="<%=Config.GENDER_TYPE_FEMALE%>";
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
function changeSpouseField(obj)
{
	if(obj.value==2)
	 	document.getElementsByName("patHusbandName")[0].disabled=true;
	else
		document.getElementsByName("patHusbandName")[0].disabled=false;		
}

function Isreferred(elem)
{
  	if(elem.checked){
// 	 	document.getElementById('divReferred').style.display="";
		document.getElementsByName("patRefGnctdHospitalCode")[0].value="-1";
		document.getElementsByName("patRefHospitalName")[0].value="";
 	if(document.getElementsByName('referringInstType')[0].checked){
        //document.getElementsByName('referringInstType')[0].checked="true";    
		document.getElementById('divRefHosname').style.display="none";
		document.getElementById('divRefHosCode').style.display="";
		document.getElementById('divReferredInstitute').style.display="";
		document.getElementById('divReferred').style.display="";
		document.getElementsByName("patRefDoctor")[0].value="";
		document.getElementsByName("patRefGnctdHospitalDept")[0].value="";
		document.getElementsByName("patRefGnctdHospitalDeptUnit")[0].value="";
		document.getElementsByName("patRefGnctdHospitalCrno")[0].value="";
		}
		else{
		document.getElementById('divRefHosname').style.display="";
		document.getElementById('divRefHosCode').style.display="none";
		document.getElementById('divReferredInstitute').style.display="";

		document.getElementById('divReferred').style.display="none";
		document.getElementsByName("patRefDoctor")[0].value="";
		//document.getElementById('divReferred').style.display="none";
		document.getElementById('divReferred').style.display="";
		document.getElementsByName("patRefGnctdHospitalDept")[0].value="";
		document.getElementsByName("patRefGnctdHospitalDeptUnit")[0].value="";
		document.getElementsByName("patRefGnctdHospitalCrno")[0].value="";
		}
		var client= "<%=Config.CLIENT%>";
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

function validateCategoryIDRequired()
{
	var valid=true
	var patPriCode=document.getElementsByName("patPrimaryCatCode")[0].value;
	var patEmployeeCode="<%=Config.PRIMARY_CATEGORY_EMPLOYEE_CODE %>";
	if(document.getElementsByName("isIdRequired")[0].value==<%=RegistrationConfig.PAT_CAT_ID_REQUIRED_YES%>)
	{
		if(patPriCode!=patEmployeeCode)
		{
		valid=isEmpty(document.getElementsByName("patCatIdNo")[0],"Patient Category Id")
		}
	}
	return valid
}
function enableSpouseField()
{
	var maritalStatus=document.getElementsByName("patMaritalStatusCode")[0].value;
	var maritalStatusSingle="<%=RegistrationConfig.REGISTRATIONDESK_MARITAL_STATUS_SINGLE%>";
	 var clientValue="<%=Config.CLIENT%>";
	 var clientPGIMER="<%=Config.CLIENT_PGIMER%>";
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
		document.getElementsByName("patHusbandName")[0].disabled=false;
		if(clientValue==clientPGIMER)
		{
		document.getElementsByName("patHusbandOccupation")[0].disabled=false;
		}
	}
}
function checkPatientCategory() 
{
 	 var patPriCode=document.getElementsByName("patPrimaryCatCode")[0].value;
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
 	 	//document.getElementById("divempIdLabel").style.display="";
 	 	//document.getElementById("divempIdControl").style.display="";
 	 	//document.getElementById("divstaffIdLabel").style.display="none";
 	 	//document.getElementById("divstaffIdControl").style.display="none";
 	 	document.getElementById("divIdTextLabel").style.display="none";
 	 	document.getElementById("divIdTextBox").style.display="none";
 	 	//document.getElementById("blankCategoryHead").style.display="none";
 	 	//document.getElementById("blankCategoryData").style.display="none";
 	 		
 	 	//disableForEmployee(dataFromTableStatusObject,dataFromTabletrue,clientValue,clientPGIMER,patCatEmpFieldValue,patCatEmpFieldEditFalse);
 	 }
 	else
 	{
 	 if(patPriCode==patStaffCode)
	 {
	 	 	//document.getElementById("divempIdLabel").style.display="none";
	 	 	//document.getElementById("divempIdControl").style.display="none";
	 	 	////document.getElementById("divstaffIdLabel").style.display="";
	 	 	//document.getElementById("divstaffIdControl").style.display="";
	 	 	//document.getElementById("divIdTextLabel").style.display="none";
 	 		//document.getElementById("divIdTextBox").style.display="none";
 	 		////document.getElementById("blankCategoryHead").style.display="none";
 	 		//document.getElementById("blankCategoryData").style.display="none";
	 }
	 else
	 {	
	 if(document.getElementsByName("isIdRequired")[0].value==<%=RegistrationConfig.PAT_CAT_ID_REQUIRED_YES%>)
	 { 
	 	 	//document.getElementById("divempIdLabel").style.display="none";
	 	 	//document.getElementById("divempIdControl").style.display="none";
	 	 	//document.getElementById("divstaffIdLabel").style.display="none";
	 	 	//document.getElementById("divstaffIdControl").style.display="none";
	 	 	document.getElementById("divIdTextLabel").style.display="";
 	 		document.getElementById("divIdTextBox").style.display="";
 	 		//document.getElementById("blankCategoryHead").style.display="none";
 	 		//document.getElementById("blankCategoryData").style.display="none";
 	 		
 	 		//document.getElementById("divEmpDob").style.display="none"
 	 		ageSelection();
	 }
	 else
	 {
	 	 	//document.getElementById("divempIdLabel").style.display="none";
	 	 	//document.getElementById("divempIdControl").style.display="none";
	 	 	//document.getElementById("divstaffIdLabel").style.display="none";
	 	 	//document.getElementById("divstaffIdControl").style.display="none";
	 	 	document.getElementById("divIdTextLabel").style.display="none";
 	 		document.getElementById("divIdTextBox").style.display="none";
 	 		//document.getElementById("blankCategoryHead").style.display="";
 	 		//document.getElementById("blankCategoryData").style.display="";
 	 		//document.getElementById("divEmpDob").style.display="none";
 	 		ageSelection();
	 }
	 }	 
	}
	
 	/*if(patPriCode=='<%=Config.PRIMARY_CATEGORY_POOR_FREE_CODE%>'){
 		document.getElementById("poorPatTR").style.display="table-row";
	}
	else{
		document.getElementById("poorPatTR").style.display="none"; 
	} 	*/

}

function handlePostResponse()
{ 
 
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
function checkBPLMandatoryFields()
{
	 var patientPriCatCode=document.getElementsByName("patPrimaryCatCode")[0].value;
	 var patBPLCode="<%=Config.PRIMARY_CATEGORY_BPL_CODE %>";
	 if(patientPriCatCode==patBPLCode)
	 {
	 		if(isEmpty(document.forms[0].patBPLCardNo,"BPL Card No.") 
		 && validateDot(document.forms[0].patBPLCardNo,"BPL Card No.")
		 && validateSpecialCharacter('/',document.forms[0].patBPLCardNo,"BPL Card No.")
		 && validateSpecialCharacter('-',document.forms[0].patBPLCardNo,"BPL Card No.")
		 //&& isEmpty(document.forms[0].familyHeadName,"BPL Family Head Name") 
		 && validateDot(document.forms[0].familyHeadName,"BPL Family Head Name")
		 && validateSpecialCharacter('/',document.forms[0].familyHeadName,"BPL Family Head Name")
		 && validateSpecialCharacter('-',document.forms[0].familyHeadName,"BPL Family Head Name")
		 //&& comboValidation(document.forms[0].bplRelationCode,"BPL Head Relation")
		 //&& isEmpty(document.forms[0].bplFamilyId,"BPL Family Id") 
		 && validateDot(document.forms[0].bplFamilyId,"BPL Family Id")
		 && validateSpecialCharacter('/',document.forms[0].bplFamilyId,"BPL Family Id")
		 && validateSpecialCharacter('-',document.forms[0].bplFamilyId,"BPL Family Id")
		 && comboValidation(document.forms[0].patIsUrban,"Area Category")
		 )
		 return true;
		 else
		 return false;
		 
	 }
	 else
	  return true;
}
function sendData(){

	 var patientPriCatCode=document.getElementsByName("patPrimaryCatCode")[0].value;
	 var patEmployeeCode="<%=Config.PRIMARY_CATEGORY_EMPLOYEE_CODE %>";
	 var patBPLCode="<%=Config.PRIMARY_CATEGORY_BPL_CODE %>";
	 if(patientPriCatCode!=patEmployeeCode)
	 {
	 	if(patientPriCatCode==patBPLCode)
	 	{
	 		//document.getElementById("bplFamilyTrId").style.display="";
			//document.getElementById("bplFamilyDivId").style.display="";
			//document.getElementById("mandatoryAreaCat").style.display="";
			//document.forms[0].patBPLCardNo.focus();
	 	}
	 	else
	 	{
	 		//document.getElementById("bplFamilyTrId").style.display="none";
			//document.getElementById("bplFamilyDivId").style.display="none";
			//document.getElementById("mandatoryAreaCat").style.display="none";
	 	}
	 	enableForNonEmployee();
	 	document.getElementById("bplFamilyTrId").style.display="none";
		document.getElementById("bplFamilyDivId").style.display="none";
	 	document.getElementById("divempIdLabel").style.display="none";
 	 	document.getElementById("divempIdControl").style.display="none";
	 }
	 if(patientPriCatCode==patEmployeeCode)
 	 {	
 	 	document.getElementById("divempIdLabel").style.display="";
 	 	document.getElementById("divempIdControl").style.display="";
 	 	document.getElementById("bplFamilyTrId").style.display="none";
		document.getElementById("bplFamilyDivId").style.display="none";
 	 	//document.getElementById("divstaffIdLabel").style.display="none";
 	 	//document.getElementById("divstaffIdControl").style.display="none";
 	 	
 	 	document.getElementById("divIdTextLabel").style.display="none";
 	 	document.getElementById("divIdTextBox").style.display="none";
 	 	
 	 	//document.getElementById("divCategoryAlignmentHead").style.display="none";
 	 	//document.getElementById("divCategoryAlignment").style.display="none";
 	 	disableForEmployee();
 	 }
 	 else
 	 {
		var hmode = "GETAMOUNTANDBPLDETAILS"; 
		var tariffIdValue='<%=RegistrationConfig.EMERGENCY_REGISTRATION_TARIFF_ID%>';
		var url = "/HISClinical/registration/emgNewPatRegistration.cnt?hmode="+hmode+"&tariffId="+tariffIdValue+"&patientCat="+patientPriCatCode;
		ajaxFunction(url,"1");
	}
}
function getAjaxResponse(res,mode)
{
		//alert(res);
		if(mode == '1')
		{
			var patientPriCatCode=document.getElementsByName("patPrimaryCatCode")[0].value;
			//if(patientPriCatCode=='13')//BPL
			//{
				//document.getElementById("bplFamilyTrId").style.display="";
				//var objEle = document.getElementById("bplFamilyDivId");
				//objEle.innerHTML = res.split('^')[1];
				//document.forms[0].patAmountCollected.value=res.split('^')[0];
				//document.forms[0].patBPLCardNo.focus();
			//}
			//else{
				document.getElementById("bplFamilyTrId").style.display="none";
				document.forms[0].patAmountCollected.value=res.split('^')[0];
				document.forms[0].isIdRequired.value=res.split('^')[1];
				//alert("document.forms[0].isIdRequired.value"+document.forms[0].isIdRequired.value)
				checkPatientCategory();
			//}
		}
		if(mode == '2')
		{
			var stateCode=document.getElementsByName("patAddStateCode")[0].value;
			var defaltStateCode="<%=RegistrationConfig.REGISTRATIONDESK_DEFAULT_STATE_CODE%>";  
			if(stateCode==defaltStateCode)
			{
		 		document.getElementById("districtCombo").style.display="";
		 		document.getElementById("districtComboNonDefault").style.display="none";
	 		}
	 		else
	 		{
		 		document.getElementById("districtCombo").style.display="none";
				document.getElementById("districtComboNonDefault").innerHTML="<select name='patAddDistrictCode' tabindex='2' class='regcbo'><option value='-1'>Select Value</option>"+res+"</select>";
				document.getElementById("districtComboNonDefault").style.display="";
			}			
		}
		if(mode == '3')
		{
			if(res>0)
			{
				var path="/HISClinical/registration/newRegistrationDeskDuplicate.cnt?hmode=BPLPOPUP&bplCardNo="+document.forms[0].patBPLCardNo.value;
				openPopup(path);
			}
		}
		if(mode == '4')
		{
			document.forms[0].vehicleHosCode.value=res;
		}
		
} 
function checkAgeOrDob()
{
	var valid=true;
	if(document.getElementsByName("isActualDob")[0].checked)
	{
		valid=isEmpty(document.forms[0].patAge,"Age");
		document.forms[0].patDOB.value="";
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

 
function validateBroughtByPolice()
{
 	valid=true
 	if(document.forms[0].isRelative.value== <%=RegistrationConfig.PATIENT_BROUGHT_BY_POLICE %>)
 	{
 		if(isEmpty(document.getElementsByName('constableDesig')[0],'Designation' )
 		 //&&	isEmpty(document.getElementsByName('constableBadgeNo')[0],'Badge No.')
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
function validateEmg()
{
 	var valid=false;
 	var broughtDetailsRequired="<%=Config.EMG_BROUGHT_BY_DETAIL_FLAG_VALUE%>"
 	var broughtByDetailsRequiredTrue="<%=Config.EMG_BROUGHT_BY_DETAIL_FLAG_VALUE_TRUE%>"
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
	// && checkBPLMandatoryFields()
	 && isEmpty(document.forms[0].patFirstName,"First Name") 
	 && validateDot(document.forms[0].patFirstName,"First Name")
	 && validateSpecialCharacter('/',document.forms[0].patFirstName,"First Name")
	 && validateSpecialCharacter('-',document.forms[0].patFirstName,"First Name")
	 && validateDot(document.forms[0].patMiddleName,"Middle Name")
	 && isEmpty(document.forms[0].patLastName,"Last Name")
	 && validateDot(document.forms[0].patLastName,"Last Name")
	 //&& comboValidation(document.forms[0].patCasteCode,"Patient Caste")
	 && checkAgeOrDob()
	 && isValid(document.forms[0].patAge,"Age",125) 
	 && isEmpty(document.forms[0].patAgeUnit,"Age Unit")
	 && validatePatientDOBAgainstSysDate()
	 && comboValidation(document.forms[0].patGenderCode,"Gender")
	 && validateDot(document.forms[0].patGuardianName,"Father Name")
	 && validateFatherNameSpouseName()
	 && validateDot(document.forms[0].patHusbandName,"Spouse Name")
	 && validateDot(document.forms[0].patMotherName,"Mother Name")
	 && comboValidation(document.forms[0].patNationalityCode,"Nationality")
	 && chackAdhaarID()	 
	 && checkAnotherID()
	 //&& comboValidation(document.forms[0].patReligionCode,"Religion")
	 && comboValidation(document.forms[0].patAddCountryCode,"Country")
	 && checkState()
	 //&& isEmpty(document.forms[0].patAddHNo,"Address")
	 && validateDot(document.forms[0].patAddCityLoc,"Location")
	 && validateDot(document.forms[0].patAddDistrict,"District")
	 && isEmpty(document.forms[0].patAddCity,"City / Village")
	 && validateDot(document.forms[0].patAddCity,"City / Village")
	 && validateMinimumLength(document.getElementById("pintext"),'Pin Number','6') 
	 && validatePinNumber(document.getElementById("pintext"))
	 && checkIsrefer()
	 && isEmpty(document.forms[0].patRefDoctor,"Referred By Doctor") 
	 && validateBroughtBy()
	 ) 
	 {
	  valid=true;
	 }	       
	 else
	 valid=false;     
	 }

	return valid;    
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

function checkForUnknownOnLoad()
{
	 <logic:equal name="EmgNewRegFB" property="isUnknown" value="1">
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
	    document.getElementsByName("patMiddleName")[0].value="";
	    document.getElementsByName("patMiddleName")[0].disabled=true;
	    document.getElementsByName("patLastName")[0].value="";
	    document.getElementsByName("patLastName")[0].disabled=true;
	    
	    document.getElementsByName("patFirstNameInMultiLang")[0].value="";
	    document.getElementsByName("patMiddleNameInMultiLang")[0].value="";
	    document.getElementsByName("patLastNameInMultiLang")[0].value="";
	    
	    document.getElementsByName("patFirstNameInMultiLang")[0].disabled=true;
	    document.getElementsByName("patMiddleNameInMultiLang")[0].disabled=true;
	    document.getElementsByName("patLastNameInMultiLang")[0].disabled=true;
	    document.getElementsByName("patGuardianName")[0].disabled=true;
	    document.getElementsByName("patHusbandName")[0].disabled=true;
    	//document.getElementsByName("prevCrNo")[0].disabled=true;
    	
    	document.getElementsByName("patCasteCode")[0].disabled=true;
    	document.getElementsByName("patMaritalStatusCode")[0].disabled=true;
    	document.getElementsByName("patMotherName")[0].disabled=true;    	
    	document.getElementsByName("patBirthPlace")[0].disabled=true;    	
    	document.getElementsByName("patOccupation")[0].disabled=true;    	
    	
    	document.getElementsByName("patReligionCode")[0].disabled=true;
    	document.getElementsByName("patNationalityCode")[0].disabled=true;
    	document.getElementsByName("patIsUrban")[0].disabled=true;
    	//document.getElementsByName("patMaritalStatusCode")[0].disabled=true;
    	document.getElementsByName("patNationalId")[0].disabled=true;
    	document.getElementsByName("patAmountCollected")[0].disabled=true;
    	document.getElementsByName("patMonthlyIncome")[0].disabled=true;
    	
    	
    	 
    	//document.getElementsByName("patAddCityLocCode")[0].disabled=true;
    	document.getElementsByName("patAddCityLoc")[0].disabled=true;
    	document.getElementsByName("patAddCity")[0].disabled=true;
    	document.getElementsByName("patMotherName")[0].disabled=true;
    	
    	
    	var contryCode=document.getElementsByName("patAddCountryCode")[0].value;
		var defaltcontryCode="<%=RegistrationConfig.REGISTRATIONDESK_DEFAULT_COUNTRY_CODE%>";  
    	if(contryCode==defaltcontryCode)
    	{ 
		    document.getElementsByName("patAddCountryCode")[0].disabled=true;
			document.getElementsByName("patAddStateCode")[0].disabled=true;
			document.getElementsByName("patAddDistrictCode")[0].disabled=true;
		}
		else
		{
			document.getElementsByName("patAddCountryCode")[0].disabled=true;
			document.getElementsByName("patAddStateName")[0].disabled=true;
			document.getElementsByName("patAddDistrict")[0].disabled=true;	
		}
	    		
	</logic:equal>
	
}

function printPage() 
{
var frameElement = parent.document.getElementById("frmMain"); 
//alert("frameElement :"+frameElement);
var win = frameElement.contentWindow ;
win.print();
}

function callThisOnload(){
// alert("on load call");
	var LOCAL_LANGUAGE = "<%= RegistrationConfig.LOCAL_LANGUAGE %>";
	initMultilingual(LOCAL_LANGUAGE);	
	
   if(document.getElementsByName('print')[0].value=='1')
	{
		//document.getElementById('divCardPrint').style.display="block"
		printPage();
		document.getElementsByName('print')[0].value='0'
		//document.getElementById('divCardPrint').style.display="none"
	}
	if(document.getElementsByName("isUnknown")[0].checked==true || (document.getElementsByName("isBroughtDead")[0].checked==true)){
		document.getElementsByName("isBroughtBy")[0].checked=true;
	}
	else{
		document.getElementsByName("isBroughtBy")[0].checked=false;
	}
	document.getElementsByName("departmentUnitCode")[0].focus()
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
	
	
	if(document.getElementsByName("isDuplicatePatientPopup")[0].value=="1"){
		
		var path="/HISClinical/registration/newRegistrationDeskDuplicate.cnt?hmode=POPUP";;
		openPopup(path);
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
	
	var defaltcontryCode="<%=RegistrationConfig.REGISTRATIONDESK_DEFAULT_COUNTRY_CODE%>";  
	var stateCode= document.getElementsByName("patAddStateCode")[0];
	if(contryCode==defaltcontryCode)
	{
		document.getElementById("stateMandotary").style.display="";
		document.getElementById("divpatAddStateCodeCombo").style.display="";
		document.getElementById("divpatAddStateCodeText").style.display="none";
		document.getElementById("stateNotMandotary").style.display="none";
		document.getElementById("districtCombo").style.display="";
		document.getElementById("districtTextBox").style.display="none";
		/* if(stateCode.options[stateCode.selectedIndex].value=="<%=RegistrationConfig.REGISTRATIONDESK_DEFAULT_STATE_CODE%>")
		{
		document.getElementById("divpatAddCityLocCode").style.display=""; 
	  	document.getElementById("divpatAddCityLocation").style.display="none"; 
	  	}*/ 
	// 	document.getElementsByName("patAddStateCode")[0].value="<%=RegistrationConfig.REGISTRATIONDESK_DEFAULT_STATE_CODE%>";
		
	}
	else{

		document.getElementById("stateMandotary").style.display="none";
		document.getElementById("divpatAddStateCodeCombo").style.display="none";
		document.getElementById("divpatAddStateCodeText").style.display="";
		document.getElementById("stateNotMandotary").style.display="";
		//document.getElementById("divpatAddCityLocCode").style.display="none"; 
	  	document.getElementById("divpatAddCityLocation").style.display="";
	  	document.getElementById("districtCombo").style.display="none";
		document.getElementById("districtTextBox").style.display="";
		 
	  	document.getElementsByName('patAddCity')[0].value=""
	  	document.getElementsByName('patIsUrban')[0].value="<%=RegistrationConfig.REGISTRATIONDESK_DEFAULT_AREA_CATEGORY_CODE%>";
	  	document.getElementsByName('patAddPIN')[0].value=""
	  	//document.getElementsByName('patAddCityLocCode')[0].value="-1"
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



function validateAlphaNumericWithSpecialCharacterOnlyforBPLCardNo(e)
{
	var key;
	var keychar;

	if (window.event)
	   key = window.event.keyCode;
	else if (e)
	   key = e.which;
	else
	   return true;
	keychar = String.fromCharCode(key);

	keychar = keychar.toUpperCase();
	//alert(key);
	// control keys
	if ((key==null) || (key==0) || (key==8) ||
		(key==9) || (key==13) || (key==27) || (key==32)|| (key==47) || (key==45) || (key==95))
	   return true;
	
	// alphas and numeric
	else if ((("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ/-").indexOf(keychar) > -1))
	   return true;
		   
	   else if ((("0123456789").indexOf(keychar) > -1))
	   return true;
	   
	   
	   
	else
	   return false;
}




function validateBroughtByVehicle(){
		
	var valid=true
 	if(document.forms[0].isRelative.value== <%=RegistrationConfig.PATIENT_BROUGHT_BY_108 %>)
 	{
 		if(isEmpty(document.getElementsByName('broughtByName')[0],'EMT Name' )&&isEmpty(document.getElementsByName('broughtByPhone')[0],'EMT Phone No.' ))
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

function changeCardNField(obj)
{
	if(obj.value==-1)
	 	document.getElementsByName("patCardNo")[0].disabled=true;
	else
		document.getElementsByName("patCardNo")[0].disabled=false;
}

</script>

<body>
<div id="noprint">	
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
 
 <div class="errMsg" id="errMsg"></div>
	<div class="warningMsg" id="warningMsg"></div>
	<div class="normalMsg" id="normalMsg"></div>
    <his:SubTitleTag name="Patient Details">  
    <td width="45%" ></td> 
    <td>
		  <div align="right">
		  		<font color="#ffffff" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		<B>  <bean:message key="unknown"/></B> </font></div>
	</td>	  
	<td>
	   <div align="left">
	      <html:checkbox  name="EmgNewRegFB" property="isUnknown" value="1" tabindex="2" onclick="submitTile('UNKNOWN');"/>
	    </div>	      
	</td>
	<td>
		  <div align="right">
		  		<font color="#ffffff" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		 <b> <bean:message key="isBroughtDead1"/></b></font></div>
	</td>
	<td>
	    <div align="left">
	      <html:checkbox  name="EmgNewRegFB" property="isBroughtDead" value="1" tabindex="2" onclick="onBroughtDead(this);"/>
	      </div>	      
	</td>
	<td>
			<div align="right">
			<font color="#ffffff" size="2" face="Verdana, Arial, Helvetica, sans-serif">
       	<b>	 <bean:message key="mlc"/></b>
       		</font>
       	   </div>
    </td>
    <td>
         <div align="left">
        <html:checkbox tabindex="1" name="EmgNewRegFB"  property="isMLC" tabindex="2" value="<%=RegistrationConfig.IS_MLC_TRUE%>"/>
        </div>
    </td>
    <td>
	   			<div align="right" style="display: none">
  	   		 	<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
        		<b>	<bean:message key="isFree"/></b>
        		</font>
        		</div>
    </td>
    <td>
          <div align="left" style="display: none">
        	 <html:checkbox tabindex="2" name="EmgNewRegFB" property="isFree" tabindex="2" value="<%=RegistrationConfig.IS_FREE_TRUE%>" onclick="disableAmountCollection(this)" onchange="disableAmountCollection(this)"/>
        	 </div>
	</td>
   
  </his:SubTitleTag>
	
<his:ContentTag>
	<table border="0" cellpadding="0" cellspacing="0" width = "100%">
	   <tr>	  
	   <td width="17%"  class="LABEL">
                <font color="#FF0000" size="2" >*</font>
               <bean:message key="visitingDeptUnit" />
         </td>
	   	<td width="15%"  class="CONTROL" colspan="3">
       <div align="left">
          <html:select name="EmgNewRegFB"  property="departmentUnitCode"  tabindex="1"  styleClass ="regcbo" >   
			  <bean:define id="deptUnitCollection" type="java.util.List" name="<%=RegistrationConfig.ESSENTIAL_BO_OPTION_DEPT_UNIT_WITH_CASUALITY%>"></bean:define>
	       <%if(deptUnitCollection.size()>1){ %>
					<html:option value="-1">Select Value</html:option>
					<%} %> 
			  <html:options collection ="<%=RegistrationConfig.ESSENTIAL_BO_OPTION_DEPT_UNIT_WITH_CASUALITY%>" property = "value" labelProperty = "label"/>
	 	 </html:select>  
      	</div>
          </td>
          <%--<logic:notEmpty name="<%=RegistrationConfig.ESSENTIAL_BO_OPTION_ACTIVE_DISASTER_LIST %>">
          <td width="15%"  class="LABEL">
                <div align="right">
                
               <bean:message key="disasterName"/>    
                </font>
                </div>
         </td>
	   	<td width="17%"  class="CONTROL">
       <div align="left" >
          <html:select name="EmgNewRegFB"  property="disasterId"  tabindex="1"  styleClass ="regcbo" >   
					<html:option value="-1">Select Value</html:option>
			  <html:options collection ="<%=RegistrationConfig.ESSENTIAL_BO_OPTION_ACTIVE_DISASTER_LIST%>" property = "value" labelProperty = "label"/>
	 	 </html:select>  
      </div>
          </td>
          </logic:notEmpty>
          <logic:empty name="<%=RegistrationConfig.ESSENTIAL_BO_OPTION_ACTIVE_DISASTER_LIST %>">
          	 <td width="15%"  class="LABEL">
               
         	</td>
	   		<td width="17%"  class="LABEL">
	       
          </td>
          </logic:empty>
          
            <td width="16%"  class="LABEL">
            </td>
	   		<td width="15%"  class="LABEL">
       		  </td>--%>
          
          
	</tr>
  </table>
	
</his:ContentTag>   
<his:ContentTag>
      	<table width = "100%"  border="0" cellpadding="0" cellspacing="1">  
        <tr>
			<td width="17%" nowrap class="LABEL">
				<font color="#FF0000" size="2">*</font>
				<bean:message key="patient" /> <bean:message key="category" />
				</td>
				<td width="19%" class="CONTROL">
				<html:select name="EmgNewRegFB" property="patPrimaryCatCode"
					tabindex="1" styleClass="regcbo"
					onchange="sendData()">
					<bean:define id="patCatCollection" type="java.util.List" name="<%=RegistrationConfig.ESSENTIALBO_OPTION_PRIMARY_CATEGORY%>"></bean:define>
					<%if(patCatCollection.size()>1){ %>
					<html:option value="-1">Select Value</html:option>
					<%} %>
					<html:options
						collection="<%=RegistrationConfig.ESSENTIALBO_OPTION_PRIMARY_CATEGORY%>"
						property="value" labelProperty="label" />
				</html:select>
				</td>
				<td class="LABEL" width="14%" id="divempIdLabel" style="display: none;"  >
						        <bean:message 	key="getEmploeeDetailWithDependent" />
				</td>
				<td class="CONTROL" width="17%" nowrap id="divempIdControl" style="display: none;" >
				<img class="button" src="/HISClinical/hisglobal/images/ico_myfriends.gif" tabindex="1"
						border="0" style="cursor:pointer" onkeypress="if(event.keyCode==13) showEmployeepopup(event);" onclick="showEmployeepopup(event);" >
					<%String path= request.getContextPath();  
						String path1= request.getServletPath();  
						
					%>
				</td>
				<td id="divstaffIdLabel" class="LABEL" width="14%" style="display: none;" >
					<bean:message key="id" />
				</td>
				<td class="CONTROL" width="17%" nowrap id="divstaffIdControl" style="display: none;" >
					<html:text name="EmgNewRegFB" onkeypress="return validateAlphaNumericOnly(event,this);"
						styleClass="textbox" property="patIdNo" maxlength="8" tabindex="1" /> 
						<img class="button" src="/HISClinical/hisglobal/images/ico_myfriends.gif" tabindex="1"
						border="0" style="cursor:pointer" onkeypress="if(event.keyCode==13) showEmployeepopup(event);" onclick="showEmployeepopup(event);" >
				</td>				
				<td width="17%" height="25" nowrap class="LABEL" id="divIdTextLabel" style="display:none">
				<font color="#FF0000" size="2" >*</font>
					<bean:message key="patientCategoryId" />
				</td>
				<td width="19%" class="CONTROL" id="divIdTextBox" style="display:none">
				<html:text name="EmgNewRegFB" onkeypress="return validateAlphaNumericOnly(event,this);"
						styleClass="textbox" property="patCatIdNo" maxlength="16" tabindex="1" ></html:text>
				</td>
				<td id="blankCategoryHead" width="17%" height="25" nowrap class="LABEL"></td>
				<td id="blankCategoryData" width="19%" class="CONTROL"></td>			
	 
		 <td width="14%" class="LABEL" colspan="1">
		 <div align="right" style="display: none;">
		<img class="button" style="cursor:pointer" src='/HISClinical/hisglobal/images/camera2.gif' 
		alt="Capture Photo" title="Capture Photo" onkeypress="if(event.keyCode==13) openPopup('/HISClinical/registration/uploadFile.cnt',event,300,600)" 
		onclick="openPopup('/HISClinical/registration/uploadFile.cnt',event,300,600)" tabindex="2"></div></td>
		
		<td class="CONTROL" width="18%"><div style="display: none;">
		<img class="button" style="cursor:pointer" src='/HISClinical/hisglobal/images/view.psd.1.gif' alt="View Photo" title="View Photo" 
		onkeypress="if(event.keyCode==13) openPopup('/HISClinical/registration/enlargedImage.cnt',event,300,600)" 
		onclick="openPopup('/HISClinical/registration/enlargedImage.cnt',event,300,600)" tabindex="2"></div></td>     
	   </tr>	   
	   <tr style="display: none;" id="bplFamilyTrId">
					<td width="100%" colspan="6">
					<div id="bplFamilyDivId">
					<table width='100%'cellspacing='1px' cellpadding='0'>
						<tr><td width='18%' nowrap class='LABEL'>
						<bean:message key="mandatory1"/><bean:message key="patCardNo" /></td>
						<td width='17%' class='CONTROL'>
						<input type='text' name='patBPLCardNo' value='' class='textbox' tabindex="1" maxlength='10' onblur="openBPLPopup(event);" onkeypress="return validateAlphaNumericWithSpecialCharacterOnlyforBPLCardNo(event,this)" >
						</td>
						<td width='17%' nowrap class='LABEL'><div align="left">
						<input type='button' name='BPL Search' value='BPL Search' onclick='openBPLPopup(event);'></div></td>
						<td width='17%' class='CONTROL'></td>
						<td width='15%' nowrap class='LABEL'></td>
						<td width='17%' class='CONTROL'></td>
						</tr>
						<tr><td width='18%' nowrap class='LABEL'><bean:message key="familyHeadName" /></td>
						<td width='17%' class='CONTROL'>
						<input type='text' class='textbox' name='familyHeadName' value='' onclick='' maxlength='80' onkeypress="return validateAlphabetsOnly(event,this)">
						</td>
						<td width='17%' nowrap class='LABEL'><bean:message key="familyHeadRelation" /></td>
						<td width='17%' class='CONTROL'>
						<html:select name="EmgNewRegFB" property='bplRelationCode' styleClass='regcbo'>
						<html:option value='-1'>Select Value</html:option>
						<html:options collection="<%=RegistrationConfig.ESSENTIALBO_OPTION_RELATION_DTL %>"
							property="value" labelProperty="label" />
							</html:select>
						</td>
						<td width='15%' nowrap class='LABEL'><bean:message key="stateIdNo" /></td>
						<td width='17%' class='CONTROL'>
						<input type='text' class='textbox' name='bplStateId' value='' onclick='' maxlength='10'></td>
						</tr>
						<tr><td width='18%' nowrap class='LABEL'><bean:message key="mmjrkNo" /></td>
						<td width='17%' class='CONTROL'>
						<input type='text' name='bplMMJRKNo' value='' class='textbox' onclick='' maxlength='10'>
						</td>
						<td width='17%' nowrap class='LABEL'><bean:message key="bplFamilyId" /></td>
						<td width='17%' class='CONTROL'><input type='text' class='textbox' name='bplFamilyId' value='' onclick='' maxlength='10'></td>
						<td width='15%' nowrap class='LABEL'><div align='right'></div></td>
						<td width='17%' class='CONTROL'></td>
						</tr>						
	  			</table></div>
				</td>
		 </tr>
     	 <tr>
              <td width="19%" nowrap  class="LABEL">       
		        <bean:message key="patient"/>
		        <bean:message key="name"/></td>		        
		        <td width="19%" class="CONTROL">		       
		        <font color="#FF0000">*</font>
		        <bean:message key="first"/>
		        </td>
		        <td width = "18%" class="LABEL" >
		        <div align="left" style="display:block">
		        <font size="2" color="#000000"
						face="Verdana, Arial, Helvetica, sans-serif"
						style="display: block;"><bean:message key="middle"/></font>
		        </div></td>

  		<td width="19%" class="LABEL">
			<div align="left"><font color="#000000" size="2"
						face="Verdana, Arial, Helvetica, sans-serif"><font color="#FF0000">*</font> <bean:message
						key="last" /> </font></div>
		</td>
		<td width="14%" class="LABEL"></td>
				<td width="17%" class="CONTROL"></td>
	</tr>
	<tr>
         <td width="14%" class="LABEL" >&nbsp;</td>
        <td width="19%" class="CONTROL">
        <html:text name="EmgNewRegFB" property="patFirstName"  tabindex="1" styleClass="textbox" maxlength ="30" onblur="isAlpha(this,'First Name')" 
        onkeypress="return validateAlphabetsWithDotsAndForwardSlashWithHypen(event,this)"
        onkeyup="multilingualConversion(this,document.getElementsByName('patFirstNameInMultiLang')[0]);"/>
        </td>
        <td width="18%" class="CONTROL">
        <div align="left" style="display:block">
        <html:text name="EmgNewRegFB" property="patMiddleName" tabindex="2" styleClass="textbox" onblur="isAlpha(this,'Middle Name')" maxlength ="20" 
        onkeypress="return validateAlphabetsWithDotsOnly(event,this)"
        onkeyup="multilingualConversion(this,document.getElementsByName('patMiddleNameInMultiLang')[0]);"/>
        </div>
        </td>
        <td width="18%" class="CONTROL">
        <html:text name="EmgNewRegFB" property="patLastName" tabindex="2" styleClass="textbox" maxlength ="30" onblur="isAlpha(this,'Last Name')" 
        onkeypress="return validateAlphabetsWithDotsOnly(event,this)"
        onkeyup="multilingualConversion(this,document.getElementsByName('patLastNameInMultiLang')[0]);"/>
        </td>
		<td width="17%" class="LABEL">
					<bean:message key="patCaste" /></td>
					<td class="CONTROL" width="17%"><html:select
						name="EmgNewRegFB" property="patCasteCode"
						tabindex="2" styleClass="regcbo">
						<html:option value="-1">Select Value</html:option>
						<html:options
							collection="<%=RegistrationConfig.ESSENTIALBO_OPTION_CASTE %>"
							property="value" labelProperty="label" />
					</html:select></td>
    </tr> 
    <tr style="display: none">
        <td width="14%" class="LABEL" >&nbsp;</td>
        <td width="19%" class="CONTROL">
	        <input type="text" name="patFirstNameInMultiLang" value="${EmgNewRegFB.patFirstNameInMultiLang}"  tabindex="1" class="textbox" maxlength ="30" 
	        onblur="callOnBlur();" onfocus="callOnClick();"/>
        </td>
        <td width="18%" class="CONTROL">
	        <div align="left" style="display:block">
	        <input type="text" name="patMiddleNameInMultiLang" value="${EmgNewRegFB.patMiddleNameInMultiLang}" tabindex="2" class="textbox" maxlength ="20" 
	        onblur="callOnBlur();" onfocus="callOnClick();"/>
        </div>
        </td>
        <td width="18%" class="CONTROL">
	        <input type="text" name="patLastNameInMultiLang" value="${EmgNewRegFB.patLastNameInMultiLang}" tabindex="2" class="textbox" maxlength ="30" 
	        onblur="callOnBlur();" onfocus="callOnClick();"/>
        </td>
		<td width="17%" class="LABEL"></td>
		<td width="14%" class="LABEL"></td>
		<td width="17%" class="CONTROL"></td>
    </tr>
        <tr>
        
          <td class="LABEL" width="19%" nowrap="nowrap">
        <font color="#FF0000" size="2">*</font>       
        <bean:message key="age"/>
        <html:radio name="EmgNewRegFB" property="isActualDob" value="0" tabindex="2" onclick="ageSelection()"/>    
   
        <font color="#000000" size="2" >
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
           strdivage="";
           strdivdob="none";           
        %>               
        </logic:equal>                    
     	
        <td width="19%" nowrap class="CONTROL" >
        <div id="divAge" style='display:<%=strdivage%>'>
        <html:text name="EmgNewRegFB" size="4" property="patAge" tabindex="1" maxlength ="3" onkeypress="return validateNumeric(event)"/> 
        <html:select name="EmgNewRegFB" property="patAgeUnit" tabindex="1" styleClass="smallcombo">
        <html:options collection = "<%=RegistrationConfig.ESSENTIALBO_OPTION_AGE_TYPE %>" property = "value" labelProperty = "label"/>
        </html:select>
        </div>                
      	<%//String strEmpdivdob="none"; 
			//	 if(((EmgNewPatientRegFBDuplicate)pageContext.findAttribute
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
         <td width="14%" class="LABEL" >
         <font color="#FF0000">*</font>
       <bean:message key="gender"/>
       </td>
       
       <td width="17%" class="CONTROL">
       <html:select name="EmgNewRegFB" property="patGenderCode" tabindex="1" styleClass="regcbo">
       <html:option value="-1">Select Value</html:option>
	   <html:options collection = "<%=RegistrationConfig.ESSENTIALBO_OPTION_GENDER %>" property  = "value" labelProperty = "label"/>
	   </html:select>
       </td>
       
       <td width="18%" class="LABEL">
        <!--<bean:message key="monthlyIncome"/>-->
        <bean:message
					key="maritalStatus" />
        </td>
        
        <td class="CONTROL" width="18%">
        <!--<html:text name="EmgNewRegFB" property="patMonthlyIncome" styleClass="textbox" tabindex="2" maxlength="11" onkeypress="return(currencyFormat(this,',','.',event))"/>-->
        <html:select
					name="EmgNewRegFB" property="patMaritalStatusCode" 
					tabindex="2" styleClass="regcbo" onchange="changeSpouseField(this)">
					<html:option value="-1">Select Value</html:option>
					<html:options
						collection="<%=RegistrationConfig.ESSENTIALBO_OPTION_MARITAL_STATUS %>"
						property="value" labelProperty="label" />
				</html:select>
        </td>      
       </tr>
        <tr>
       
		    <td width="19%" class="LABEL">
	        <font color="#FF0000" size="1" >*</font>
	        <bean:message key="fathersName"/>
	        </td>       
	        <td width="19%" class="CONTROL">
	        <html:text name="EmgNewRegFB" property="patGuardianName" tabindex="1" styleClass="textbox" onblur="isAlpha(this,'Middle Name')" maxlength ="60" onkeypress="return validateAlphabetsWithDotsOnly(event,this)"/>
	        </td>
	         <td class="LABEL" width="18%">
	        <bean:message key="husbandName"/>
	        </td>
	        <td class="CONTROL" width="18%">
	         <html:text name="EmgNewRegFB" property="patHusbandName" styleClass="textbox" tabindex="2" maxlength="60" onkeypress="return validateAlphabetsWithDotsOnly(event,this)"/>
	        </td>
        	 <td width="17%" class="LABEL" nowrap>
				<bean:message key="motherName" />
			</td>
			<td width="17%" class="CONTROL"><html:text name="EmgNewRegFB"
				property="patMotherName" styleClass="textbox" tabindex="2"
				maxlength="60" onchange="isAlpha(this,'Mother Name')"
				onkeypress="return validateAlphabetsWithDotsOnly(event,this)" />
			</td>
       </tr>
       <tr>
        
       <td class="LABEL" width="18%">
        <font color="#FF0000" size="2" >*</font>
        <bean:message key="nationality"/>
        </td>
        
        <td class="CONTROL" width="17%">   
 		<div id="patCountryCombo">
        <html:select name="EmgNewRegFB" property="patNationalityCode" tabindex="1" styleClass="regcbo" >
	    <html:option value="-1">Select Value</html:option>
		<html:options collection ="<%=RegistrationConfig.ESSENTIALBO_OPTION_NATIONALITY %>"property = "value" labelProperty = "label"/>
        </html:select>	
        </div>
        </td>
        <td width="14%"  class="LABEL">
        
        <bean:message key="religion"/>
        </td>  
        <td width="17%"  class ="CONTROL">
        <html:select name="EmgNewRegFB" property="patReligionCode" tabindex="2" styleClass="regcbo">
        <html:option value="-1">Select Value</html:option>
        <html:options collection = "<%=RegistrationConfig.ESSENTIALBO_OPTION_RELIGION %>" property = "value" labelProperty = "label"/>
        </html:select>
        </td>
        
       <td width="18%" class="LABEL"><bean:message key="birplace" /></td>       
       <td class="CONTROL"><html:text
						name="EmgNewRegFB" property="patBirthPlace"
						styleClass="textbox" tabindex="2" maxlength="60"
						onchange="isAlpha(this,'Guardian Name')"
						onkeypress="return validateAlphabetsWithDotsOnly(event,this)" /></td>        
        </tr>
        <tr>       
        <tr>  
        <td width="18%" class="LABEL" >
        <bean:message key="idMark1"/>
	    <logic:equal name="EmgNewRegFB" property="isUnknown" value="1">
<!--	    <font color="#FF0000" size="1" >*</font>-->
	    </logic:equal>
	    </td>
         <td width="17%" colspan="2"  class="CONTROL" > 
         <html:text  name="EmgNewRegFB"  size="40" maxlength="100" property="patIdMark1" tabindex="2" onkeypress="return validateAlphabetsOnly(event,this)"/>
		 </td>		 
		 <td width="18%" class="LABEL" >
         <bean:message key="idMark2"/>
	     <logic:equal name="EmgNewRegFB" property="isUnknown" value="1">
<!--	    <font color="#FF0000" size="1" >*</font>-->
	     </logic:equal>
	    </td>
         <td width="17%" colspan="2"  class="CONTROL" > 
          <div align="left">
         <html:text  name="EmgNewRegFB"  size="40" maxlength="100"  property="patIdMark2" tabindex="2" onkeypress="return validateAlphabetsOnly(event,this)"/>
         </div>
		 </td>        
      </tr>     
      <tr>
				<td width="17%" class="LABEL"><bean:message key="patOccupation" /></td>
				<td width="17%" class="tdfont"><html:select
					name="EmgNewRegFB" property="patOccupation"
					tabindex="2" styleClass="regcbo">
					<html:option value="-1">Select Value</html:option>
					<html:options
						collection="<%=RegistrationConfig.ESSENTIALBO_OPTION_OCCUPATION_DTL %>"
						property="value" labelProperty="label" />
				</html:select></td>
				<td class="LABEL" width="17%"><bean:message key="monthlyIncome" />
				</td>
				<td width="17%" class="tdfont"><html:text
					name="EmgNewRegFB" property="patMonthlyIncome"
					styleClass="textbox" maxlength="16" tabindex="2"
					onkeypress="return validateNumericOnly(this,event)" />
				</td>

	       	
	     	   <td width="17%" class="LABEL" >
	       <bean:message key="amountCollected"/> 
	    </td>
	       <td width ="17%" class="CONTROL" >
	       <html:text name="EmgNewRegFB" property="patAmountCollected"   maxlength="10,2" readonly="true" styleClass="textbox" />
	       </td>	      
		</tr>
		<tr>
					<td class="LABEL" width="17%"><bean:message key="adhar" />
					</td>
					<td width="17%" class="tdfont"><html:text
						name="EmgNewRegFB" property="patNationalId"
						styleClass="textbox" maxlength="16" tabindex="2"
						onkeypress="return validateAlphaNumericWithSpecialCharacterOnly(event)" />
					</td>
					<td width="17%" class="LABEL"><bean:message key="otherId" /></td>
					<td width="17%" class="tdfont"><html:select
						name="EmgNewRegFB" property="patDocType"
						tabindex="2" styleClass="regcbo" onchange="changeCardNField(this)">
						<html:option value="-1">Select Value</html:option>
						<html:options
							collection="<%=RegistrationConfig.ESSENTIALBO_OPTION_VERIFICATION_DOCUMENTS %>"
							property="value" labelProperty="label" />
					</html:select></td>
					<td width="17%" class="LABEL"><bean:message key="patCardNo" /></td>
					<td width="17%" class="tdfont"><html:text
						name="EmgNewRegFB" property="patCardNo" disabled="true"
						styleClass="textbox" maxlength="11"/></td>
				</tr>
    </table>
   
	</his:ContentTag>
	 
	  
            

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
               			
            %>        
        </logic:equal>
          <logic:equal name="isref" value="0">  
            <%
               divisrefdisplay="none";                 
               divRefGnctdHospitalCode="none";
               divRefHospname="none";
              
            %>        
        </logic:equal>
         <logic:equal name="isref" value="1">  
            <logic:equal name="EmgNewRegFB" property="referringInstType" value="O">                    
             <%
                      divRefGnctdHospitalCode="none"; 
                      divRefHospname  ="";
                  	  
             %>             
           </logic:equal>
           <logic:equal name="EmgNewRegFB" property="referringInstType" value="G">                    
             <%
                      divRefGnctdHospitalCode=""; 
                      divRefHospname  ="none";
	                 
             %>             
           </logic:equal>           
        </logic:equal>                           
         
        <logic:equal name="isref" value="1">  
            <logic:empty name="EmgNewRegFB" property="patRefGnctdHospitalCode">                    
             <%
                      divRefGnctdHospitalCode="none"; 
                      divRefHospname  ="";
             %>             
           </logic:empty>
           <logic:empty name="EmgNewRegFB" property="patRefHospitalName">                    
             <%
                      divRefGnctdHospitalCode=""; 
                      divRefHospname  ="none";
             %>             
           </logic:empty>           
        </logic:equal>                           

	 <his:SubTitleTagBroad name="Address Detail">
		   <table width="100%">
		   <tr>
             <td>	  
             		  <font color="#FF0000" size="2" >*</font>
                      <font color="#ffffff" size="2"
						face="Verdana, Arial, Helvetica, sans-serif"><bean:message key="country"/></font>
             </td>       
 			 <td>
 			 	       <html:select name="EmgNewRegFB" tabindex="1" property="patAddCountryCode"  styleClass="regcbo" onchange="showState()">
		                  	<html:option value="-1">Select Value</html:option>
				  		   <html:options collection ="<%=RegistrationConfig.ESSENTIALBO_OPTION_COUNTRY %>"property = "value" labelProperty = "label"/>
                       </html:select>	

             </td>
            <td>
             
             <div id="stateMandotary" style="" >
             		  <font color="#FF0000" size="2" >*</font>
             		  <font color="#ffffff" size="2"
						face="Verdana, Arial, Helvetica, sans-serif"> 
             		  		<bean:message key="state"/>
             		  </font>
              </div>
              <div id="stateNotMandotary" style="" >
             		  <font color="#ffffff" size="2"
						face="Verdana, Arial, Helvetica, sans-serif"> 
             		  		<bean:message key="state"/>
             		  </font>
              </div>
             </td>
             <td>     
             		 <div id="divpatAddStateCodeCombo" style="">                 
                     <html:select name="EmgNewRegFB" tabindex="1" property="patAddStateCode" styleClass="regcbo" onchange="if(this.value!='-1') getDistrictBasedOnState(this)" >
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
 <td  class="LABEL" width="14%" >
                         <bean:message key="hno"/>
	                </td>        
                                
                <td  class="CONTROL" width="17%">
                <html:text  name="EmgNewRegFB" onkeypress="return CheckMaxLength(event,this,15,1);" 
                	property="patAddHNo" tabindex="2" styleClass="textbox"/>
                </td>
                <td  class="LABEL" width="18%">                    
	            <bean:message key="street"/>  
	            </td>
	            <td class="CONTROL" width="17%">
	            <html:text  name="EmgNewRegFB" property="patAddStreet" onkeypress="return validateAlphaNumericWithDotsOnly(event,this);" tabindex="2" maxlength="30" styleClass ="textbox"/> 
	            </td>
                <td  class="LABEL" width="17%">
	            <bean:message key="location"/>	            
             </td>
             <td  class="CONTROL" width="17%">
                <div id="divpatAddCityLocCode" >                
	            <html:select name="EmgNewRegFB" tabindex="2" styleClass="regcbo" property="patAddCityLocCode" onchange="if(this.value!='-1') sendDataForCityLocation(this)">
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
                <td class="LABEL" width="14%">
                <bean:message key="district"/> 
	            </td>                     
                <td class="CONTROL" width="17%">
                <div id="districtComboNonDefault" style="display: none;">
                <html:select name="EmgNewRegFB" tabindex="2" styleClass="regcbo" property="patAddDistrictCode">
				<html:option value="-1">Select Value</html:option>			
	            </html:select>
                </div>
                <div id="districtCombo" style="display: none;">
                <html:select name="EmgNewRegFB" tabindex="2" styleClass="regcbo" property="patAddDistrictCode">
				<html:option value="-1">Select Value</html:option>
				<html:options collection ="<%=RegistrationConfig.ESSENTIAL_BO_OPTION_DISTRICT_LIST_STATEWISE%>" property = "value" labelProperty = "label"/>
	            </html:select>
                </div>
                <div id="districtTextBox" style="display:none">
                <html:text  name="EmgNewRegFB" tabindex="2" property="patAddDistrict" onchange="isAlpha(this,'District')"  onkeypress="return validateAlphabetsWithDotsOnly(event,this)" maxlength="30" styleClass="textbox"/>
                </div>					
                </td>
                  
                <td  class="LABEL" width="18%" >
	            	<font color="#FF0000" size="2" >*</font>
			                    <bean:message key="city"/>
					            <bean:message key="slash"/>
					            <bean:message key="village"/>	
	            </td>
	            <td class="CONTROL" width="17%">
	            <html:text name="EmgNewRegFB" tabindex="1" property="patAddCity" onchange="isAlpha(this,'City')" onkeypress="return validateAlphabetsWithDotsOnly(event,this);" maxlength="30" styleClass="textbox"/> 
                </td>
                
                <td class="LABEL" width="14%">
	            <bean:message key="pin"/>
	            </td>       
                  <%
							String pinCode = ((EmgNewPatientRegFBDuplicate) pageContext
							.findAttribute("EmgNewRegFB")).getPatAddPIN();
					if (pinCode == null) {
						pinCode = "";
					}
					%>
                <td  class="CONTROL" width="17%">
                <input type="text" id="pintext" class="textbox" name="patAddPIN" tabindex="2"
                 size="17"  maxlength ="6"  onkeypress="return validateNumeric(event)" value="<%=pinCode %>"  /> 
                  </td>
              </tr> 
              <tr>
                      
                    <td class="LABEL" width="17%"><bean:message key="tehsil" /></td>
					<td class="CONTROL" width="17%">
					<div><html:text name="EmgNewRegFB"
						tabindex="2" property="strPatAddressTehsil" maxlength="30"
						styleClass="textbox" onkeypress="return validateAlphabetsOnly(event,this)" />
					</div>
					</td>
					
                    
             <td width="18%" class="LABEL" nowrap="nowrap">
             <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif" 
             id="mandatoryAreaCat" style="display: none;"><bean:message key="mandatory1" /></font> 
             	<bean:message key="areaCategory" />
				</td>

				<td width="18%" class="CONTROL"><html:select
					name="EmgNewRegFB" property="patIsUrban" tabindex="2"
					styleClass="regcbo">
					<html:option value="">Select Value</html:option>
					<html:options
						collection="<%= RegistrationConfig.ESSENTIALBO_OPTION_AREA_CATEGORY %>"
						property="value" labelProperty="label" />
				</html:select></td> 
             <td  class="LABEL"  width="17%">
                    <bean:message key="contactNo"/>
                    </td>             		
                    <td  class="CONTROL" width="17%" >   
 		            <html:text name="EmgNewRegFB" tabindex="2" property="patAddContactNo"  maxlength ="30" styleClass="textbox" onkeypress="return validateNumeric(event)"/> 
                    </td>
                  </tr>		
                </table>
   
 </his:ContentTag>


<bean:define id="confFlagId" name="<%=Config.EMG_BROUGHT_BY_DETAIL_FLAG%>" ></bean:define>
	 <logic:equal name="confFlagId" value="<%=Config.EMG_BROUGHT_BY_DETAIL_FLAG_VALUE_TRUE%>">
	<his:SubTitleTag name="Brought By Details"> 
	  <table width="100%" cellspacing="1" cellpadding="0">
	  <tr>
	  <td width="90%">
	  <div align="right">
	  <font color="#ffffff" size="2"
						face="Verdana, Arial, Helvetica, sans-serif"> 
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
        <td width="25%" class="LABEL"><!-- 16 -->
      	  <font color="#FF0000" size="2" >*</font>
	          <bean:message key="broughtBy"/>	        
        </td>        
        <td width="25%" class="CONTROL" > <!-- 18 -->
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
	

		<td width="25%"  class="LABEL" >
       <bean:message key="realtionship"/>
       </td>
       
       <td width="25%" class="CONTROL"  >
       <html:select name="EmgNewRegFB" property="broughtByRelationCode" tabindex="1" styleClass="regcbo">
       <html:option value="-1">Select Value</html:option>
	   <html:options collection = "<%=RegistrationConfig.ESSENTIALBO_OPTION_RELATION_DTL %>" property  = "value" labelProperty = "label"/>
	   </html:select>
       </td>
        <%--<td width="25%" class="LABEL"><!-- 16 -->
	        <font color="#000000" size="2" >
	          <bean:message key="gender"/>      
	        </font>
        </td>        
        <td width="25%" class="CONTROL">
       <html:select name="EmgNewRegFB" property="broughtByGenderCode" tabindex="1" styleClass="regcbo">
       <html:option value="-1">Select Value</html:option>
         <html:option value="1">Male</html:option>
           <html:option value="2">Female</html:option>
             <html:option value="3">Other</html:option>
	   </html:select>
       </td>--%>
      </tr>
     
      <tr>
       <td width="25%" class="LABEL"><!-- 16 -->
       <div id="nameBroughtBy">
        <font color="#FF0000" size="2" >*</font>
	          <bean:message key="name"/>
	    </div>      
	    </td>        
        <td width="25%" class="CONTROL" > <!-- 18 -->
         <div align="left">
             <html:text name="EmgNewRegFB" tabindex="1" property="broughtByName" onkeypress="return validateAlphabetsWithDotsOnly(event,this)" tabindex="1" styleClass="textbox" onblur="isAlpha(this,'Middle Name')" maxlength="60"/>
            </div>
        </td>
         <td width="25%" class="LABEL">
        
       <font color="#FF0000" size="2" >*</font>
              <bean:message key="broughtFromLoacation"/>         
	    </td>        
        <td width="25%" class="CONTROL" > <!-- 18 -->
         <div align="left">
             <html:text name="EmgNewRegFB" tabindex="1" property="broughtByLocation"  maxlength="30" styleClass="textbox" onkeypress="return validateAlphabetsWithDotsOnly(event,this)"/>
             </div>
        </td> 
      </tr>
      </table>
      <div id="policeDetailDiv" style="display: none">
	      <table width="100%" cellspacing="1" cellpadding="0">
		      <tr>
		      	<td width="25%" class="LABEL">		      	 
		      	<font color="#FF0000" size="2" >*</font>
		      		   <bean:message key="designation"/>
		      	</td>
		      	<td width="25%" class="CONTROL">
		      	 <div align="left">
		      		<html:text name="EmgNewRegFB" property="constableDesig" styleClass="textbox" maxlength="50" tabindex="1" onkeypress="return validateAlphaNumericOnly(event,this)"></html:text>
		      	</div>
		      	</td>
		      	<td width="25%" class="LABEL">
<!--		      	<font color="#FF0000" size="2" >*</font>-->
				       <bean:message key="badgeNo"/>
		      	</td>
		      	<td width="25%" class="CONTROL">
		      	 <div align="left">
		      		<html:text name="EmgNewRegFB" property="constableBadgeNo" styleClass="textbox" maxlength="50" onkeypress="return validateAlphaNumericOnly(event,this)" tabindex="1"></html:text>
		      	</div>	
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
     			 <html:select name="EmgNewRegFB" property="broughtByVehicleCode" tabindex="1" styleClass="regcbo" onchange="  getVehicleHosCode(this)">
      			 <html:option value="-1">Select Value</html:option>
	  			 <html:options collection = "<%=RegistrationConfig.ESSENTIALBO_OPTION_VEHICLE %>" property  = "value" labelProperty = "label"/>
	 	    	 </html:select>
      			 </td>
		      	
		      	<td width="25%" class="LABEL">
				    <bean:message key="vehicle"/>  <bean:message key="location"/>    
		      	</td>
		      	<td width="25%" class="CONTROL">
		      	 <div align="left">
		      	
		      		<html:text name="EmgNewRegFB" property="vehicleHosCode" styleClass="textbox11" disabled="true" value ="" tabindex="1"></html:text>
		      	</div>	
		      	</td>
		      </tr>
	      </table>
      </div>
      
      <table width="100%" cellspacing="1" cellpadding="0">
      <tr> 
      	<td width="50%" height="100%">
       <table width="100%" cellspacing="0" cellpadding="0">
       <tr>
       <!--<td width="50%"  style="background-color:#FFEBD5;height:45;font-family: 'MS Sans Serif';	font-size:18px; color:#653232;text-align:right;">
	        --><td width="50%" class="LABEL">
	        <div id="phoneBroughtByDiv">
	          <font color="#000000" size="2"
						face="Verdana, Arial, Helvetica, sans-serif"><bean:message key="phone"/>  <bean:message key="no"/>
						</font>
			</div>        
       </td>
     
       
       <!--<td width="50%"  style="background-Color:#F1ECE2;height:45;color:#990000;text-align:left;text-transform: capitalize;font-family:'Trebuchet MS';	font-size: 10px;font-weight: bold;font-variant: normal;">
        --><td width="50%" class="CONTROL">
        <div align="left">
         <html:text name="EmgNewRegFB" tabindex="2" property="broughtByPhone"  maxlength="30" styleClass="textbox" onkeypress="return validateNumeric(event)" tabindex="1"/>
        </div>
       </td>
       </tr>
       </table>
       </td>
       <td width="50%">
        <table width="100%" cellspacing="1" cellpadding="0">
        <tr>
       <td width="25%" valign="top" rowspan="2" class="LABEL"><!-- 18 -->
             <bean:message key="broughtbyaddress"/>
       	</td>
      
       	<td colspan="2" width="35%" rowspan="2" class="CONTROL" valign="top" ><!-- 3 -->
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
              
               divisrefdisplay="none";                 
               divRefGnctdHospitalCode="none";
               divRefHospname="none";
               divDocname="none";
				divDocTitle="none";
				divref="none";
				divRefGnctd="none";
               
            %>        
        </logic:equal>
        
        <logic:equal name="isref" value="1">  
            <logic:equal name="EmgNewRegFB" property="referringInstType" value="O">                    
             <%
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
                      divRefGnctdHospitalCode=""; 
                      divRefHospname  ="none";
	                  divDocname="";
					  divDocTitle="";
					  divref="";
						divRefGnctd="";
             %>             
           </logic:equal>           
        </logic:equal>                           
               
          
            
			<!--................................... code for referred details........... -->
	
  <his:SubTitleTag name="Refer Details">
  <table width="100%" cellspacing="1" cellpadding="1">
  
  </table>
   <table width="100%" cellspacing="1" cellpadding="0">
	  <tr>
	  <td width="90%"><div align="right">
	  <font color="#ffffff" size="2"
						face="Verdana, Arial, Helvetica, sans-serif"> 
	          <bean:message key="isreferred"/></font></div>
	  </td>
	<td>
	<div align="left">
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
				<td width="50%" class="LABEL" colspan="2">
				<div id="divReferredInstitute" style="display:<%=divref%>" align="right">
			      
				<font color="#FF0000" size="2" 
						>*</font> <font color="#000000" size="2"
						face="Verdana, Arial, Helvetica, sans-serif">  &nbsp;&nbsp;&nbsp;<bean:message
						key="gnctd" /> </font><html:radio name="EmgNewRegFB"
						property="referringInstType" tabindex="1" value="G"
						onclick="showdivhoscode(this)" /> 
<!--				</td>-->
<!--				<td width="25%" class="CONTROL" nowrap="nowrap">-->
				<font color="#000000" size="2"
						> &nbsp;&nbsp;&nbsp;<bean:message
						key="other" /> </font><html:radio
						name="EmgNewRegFB" property="referringInstType" value="O"
						tabindex="1" onclick="showdivhoscode(this)" /></div>
				</td>
			
			<td width="25%" class="LABEL" nowrap="nowrap" colspan="1">
	         		&nbsp;&nbsp;
	         		<b><font
						color="#FF0000" size="1"
						>*</font> <font color="#000000" size="2"
						face="Verdana, Arial, Helvetica, sans-serif"> 
			   <bean:message key="institute"/>
			   <bean:message key="name"/></font></b>
			   </td>
			   <td width="25%" class="CONTROL"  colspan="1">
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
				<td width="25%" class="LABEL">
				<bean:message
					key="referring" /> <bean:message key="institute" /> <bean:message
					key="crNo" />
				</td>

				<td width="25%" nowrap="nowrap" class="CONTROL"><html:text
					name="EmgNewRegFB" tabindex="1"
					property="patRefGnctdHospitalCrno" maxlength="13" tabindex="1"
					onkeydown="setPrevValue(this, event);"
					onkeyup="moveToRight(this, event);"
					onkeypress="return validateNumeric(event)" styleClass="textbox" />
				</td>
				<td width="15%" class="LABEL" nowrap>
				<font color="#FF0000" size="1"
					>*</font> <bean:message
					key="referredBy" />
				
				</td>
			
				<td width="15%" colspan="2" class="CONTROL">
				<div align="left"><html:text name="EmgNewRegFB"
					 maxlength="60" property="patRefDoctor" 
					onchange="isAlpha(this,'Referred By Doctor')" tabindex="1" styleClass="textbox"
					onkeypress="return validateAlphabetsOnly(event,this)" /></div>
				
				</td>
				</tr>
				<tr>
				<td width="25%" class="LABEL" nowrap>
				<bean:message
					key="referring" /> <bean:message key="institute" /> <bean:message
					key="department" />
				</td>

				<td width="25%" class="CONTROL">
				<html:select name="EmgNewRegFB" property="patRefGnctdHospitalDept" tabindex="1" styleClass="regCbo" onchange="checkReferDepartment(this)">
					<html:option value="">Select Value</html:option>
					<html:option value="0">Other</html:option>
					<html:options collection="<%=RegistrationConfig.ESSENTIALBO_OPTION_REFERAL_DEPARTMENT_DTL%>" property="value" labelProperty="label" />
					 
				</html:select> 
			 </td>
    		 <td  class="LABEL" >
    			 	<bean:message key="other" />
					<bean:message key="department" />
				</td>
				<td class="CONTROL">
				<html:text name="EmgNewRegFB"  property="patRefHospitalDeptOther" maxlength="20"  tabindex="1" styleClass="textbox" disabled="true" onkeypress="return validateAlphabetsOnly(event,this)"/>
				</td>
			</tr>
			<tr>
				<td width="25%" class="LABEL">
				<bean:message key="referring" /> <bean:message
					key="institute" /> <bean:message key="unit" />
				</td>
				<td width="25%" class="CONTROL"><html:text name="EmgNewRegFB"
					property="patRefGnctdHospitalDeptUnit" tabindex="1" maxlength="15"
					styleClass="textbox" onkeypress="return validateAlphaNumericOnly(event,this)"/></td>
				<td width="25%" class="LABEL"></td>	
				<td width="25%" class="tdfont"></td>	
			</tr>
			
		</table>
	</his:ContentTag></div>
 
 <%
        	Date dt=(Date)session.getAttribute(Config.SYSDATEOBJECT);
             String 	sysDate = hisglobal.presentation.WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy");
   
%>   

  <input type="hidden" name="sysDate" value="<%=sysDate%>"/>
 			<!--.......................... code for referred details......... ends here.......... -->
 
	 
	<table class="TABLEWIDTH" align="center" cellspacing="1px">
		<tr>
			<td align="center">
			<div align="center">
          <img class="button" src='../HIS/hisglobal/images/btn-sv.png' tabindex="1" style="cursor:pointer" onkeypress="if(event.keyCode==13) submitFormOnValidate(validateEmg(),'SAVE');" onclick ="submitFormOnValidate(validateEmg(),'SAVE');" >
	      <img class="button" src='../HIS/hisglobal/images/buttons/btn-ccl.png' tabindex="1"  style=cursor:pointer onkeypress="if(event.keyCode==13) submitPage('CANCEL');" tabindex="1" onclick ="submitPage('CANCEL');">
          <img class="button" src='../../HIS/hisglobal/images/buttons/btn-clr.png' style="cursor:pointer" tabindex="1" onclick ="submitForm('NEW')" onkeypress="if(event.keyCode==13) submitForm('NEW');">
          <img class="button" src='../hisglobal/images/btn-reprint.png' tabindex="1" style="cursor: pointer; display: none;" onclick="printCard()" onkeypress="if(event.keyCode==13) printCard();">
    </div>         
	</td></tr></table>
    
	<html:hidden name="EmgNewRegFB" property="isDuplicatePatientPopup" />
<input type="hidden" name="hmode" value="unspecified" /> 
<html:hidden name="EmgNewRegFB" property="isIdRequired"/>
<html:hidden name="EmgNewRegFB" property="patIdNo"/>
<html:hidden name="EmgNewRegFB" property="patDataFromEmployeeTable"/>
<html:hidden name="EmgNewRegFB" property="empIdChk"/>
<html:hidden name="EmgNewRegFB" property="patAddDistrictCodeHidden"/>
<html:hidden name="EmgNewRegFB" property="patAddPINHidden"/>
<html:hidden name="EmgNewRegFB" property="patAddCityHidden"/>
<html:hidden name="EmgNewRegFB" property="patIsUrbanHidden"/>
<html:hidden name="EmgNewRegFB" property="patVehicleNo" />
<html:hidden name="EmgNewRegFB" property="print"/>
<input type="hidden" name="bplDetailsFound">


</his:statusTransactionInProcess>
</div>
<his:status/>
</body>

 

<%}catch(Exception e)
{
e.printStackTrace();	
}%>