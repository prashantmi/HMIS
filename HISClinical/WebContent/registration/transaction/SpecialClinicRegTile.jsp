<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<%@ page import="java.util.*,registration.*"%>

<%@page import="hisglobal.hisconfig.Config"%>
<%@page import="registration.controller.fb.SpecialClinicNewRegistrationFB"%>
<his:css src="/hisglobal/css/calendar-blue2.css" />

<his:javascript src="/registration/js/registration.js" />
<his:javascript src="/registration/js/calendar.js" />
<his:javascript src="/registration/js/validationCommon.js" />
<his:javascript src="/registration/js/validationCalls.js" />
<his:javascript src="/registration/js/commonFunctions.js" /> 
<his:javascript src="/registration/js/registration.js" />
<his:javascript src="/registration/js/stateChangeAjax.js" />
<his:javascript src="/registration/js/cityLocationAjax.js" />
<his:javascript src="/registration/js/dateFunctions.js" />
<his:javascript src="/registration/js/popup.js" />
<his:javascript src="/opd/opdJs/opdAjax.js"/>

<his:javascript src="/hisglobal/js/utilityFunctions.js" />
<his:javascript src="/hisglobal/utility/generictemplate/js/validationFunctions.js"/>
<his:javascript src="/hisglobal/utility/generictemplate/js/date_validator.js"/>

<%String st = (String) session.getAttribute("SYSDATE");
			
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
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
 		///}
		//document.getElementById('divDocTitle').style.display="";
		//document.getElementById('divDocName').style.display="";
  	}
 	
 	else
 	{
	 	document.getElementById('divRefHosname').style.display="none";
		document.getElementById('divRefHosCode').style.display="none";
		document.getElementById('divReferredInstitute').style.display="none";
		//document.getElementById('divReferredByDoc').style.display="none";
		//document.getElementById('divReferredByDocText').style.display="none";
		document.getElementById('divReferred').style.display="none";
		//document.getElementById('divDocTitle').style.display="none";
		//document.getElementById('divDocName').style.display="none";
	 }
 }

function checkReferDepartment()
{
	//if(<%=Config.CLIENT%>==<%=Config.CLIENT_PGIMER%>){
		if(document.getElementsByName('patRefGnctdHospitalDept')[0].value=='0')
		{
			document.getElementsByName('patRefHospitalDeptOther')[0].disabled=false
			
			}
		else
		{
			document.getElementsByName('patRefHospitalDeptOther')[0].value=""
			document.getElementsByName('patRefHospitalDeptOther')[0].disabled=true
		}
	//}	
}

var queryString;

function enableSpouseField()
{
 
	var maritalStatus=document.getElementsByName("patMaritalStatusCode")[0].value;
	
	var maritalStatusSingle="<%=RegistrationConfig.REGISTRATIONDESK_MARITAL_STATUS_SINGLE%>";
	var clientValue="<%=Config.CLIENT%>";
	 var clientPGIMER="<%=Config.CLIENT_PGIMER%>";
	//   alert("maritalStatus "+maritalStatus+" maritalStatusSingle  "+maritalStatusSingle)
	if( (maritalStatus=="-1") || (maritalStatus==maritalStatusSingle) )
	{
	// alert("true")
		document.getElementsByName("patHusbandName")[0].disabled=true;
		if(clientValue==clientPGIMER)
		{
		document.getElementsByName("patHusbandOccupation")[0].disabled=true;
		}
	}
	else
	{
	//  alert("elsse")
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
//  alert("cxcxc")
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
	showState()
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
 	 	document.getElementById("divCategoryAlignmentHead").style.display="none"
 	 	document.getElementById("divCategoryAlignment").style.display="none"
 	 	
 	 		
 	 	disableForEmployee(dataFromTableStatusObject,dataFromTabletrue,clientValue,clientPGIMER,patCatEmpFieldValue,patCatEmpFieldEditFalse);
 	 }
 	
	 else
	 {	
	 if(document.getElementsByName("isIdRequired")[0].value==<%=RegistrationConfig.PAT_CAT_ID_REQUIRED_YES%>)
	 	 	{
	 	 	document.getElementById("divempIdLabel").style.display="none"
	 	 	document.getElementById("divempIdControl").style.display="none"
	 	 	document.getElementById("divstaffIdLabel").style.display="none"
	 	 	document.getElementById("divstaffIdControl").style.display="none"
	 	 	document.getElementById("divIdTextLabel").style.display=""
 	 		document.getElementById("divIdTextBox").style.display=""
 	 		document.getElementById("divCategoryAlignmentHead").style.display="none"
 	 		document.getElementById("divCategoryAlignment").style.display="none"
 	 		
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
 	 		document.getElementById("divCategoryAlignmentHead").style.display=""
 	 		document.getElementById("divCategoryAlignment").style.display=""
 	 	
 	 		//document.getElementById("divEmpDob").style.display="none"
 	 		ageSelection()
	 	 	}
	 }	 
	
	
}



function handlePostResponse(){
 
 if(request.readyState == 4){
 if(request.status == 200){
 var reply=request.responseText
  document.getElementsByName("patAmountCollected")[0].value=reply.substring(0,reply.indexOf('^'));
  document.getElementsByName("isIdRequired")[0].value=reply.substring(reply.indexOf('^')+1);
	 var patPriCode=document.getElementsByName("patPrimaryCatCode")[0].value;
	 var patEmployeeCode="<%=Config.PRIMARY_CATEGORY_EMPLOYEE_CODE %>";
	 var patStaffCode="<%=Config.PRIMARY_CATEGORY_STAFF_CODE %>";
 	checkPatientCategory();
 } else {
 alert("A problem occurred with communicating between "+
 "the XMLHttpRequest object and the server program.");
 }
 }//end outer if
}

function setQueryString(){
 var patientCat=document.getElementsByName("patPrimaryCatCode")[0].value;
 var tariffIdValue='<%=RegistrationConfig.SPECIAL_CLINIC_REGISTRATION_TARIFF_ID%>';
 var tariffIdLable='<%=RegistrationConfig.TARIFF_ID%>';
 var patientCatLable='<%=RegistrationConfig.PATIENT_CAT%>';
 
 queryString=tariffIdLable+'='+tariffIdValue+'&'+patientCatLable+'='+patientCat;
}

function sendData(){
	setQueryString();
 //alert("queryString"+queryString);
	//var url='http://localhost:8080/HISClinical/AmountByPatientCat';
	var patPriCode=document.getElementsByName("patPrimaryCatCode")[0].value;
 //alert("queryString"+queryString);
  // alert("document.getElementsByName(patPrimaryCatCode)[0].value "+document.getElementsByName("patPrimaryCatCode")[0].value)
  if(patPriCode =='-1'){
  	document.getElementsByName("isIdRequired")[0].value="";
  	checkPatientCategory();
  }
  else{
  	if(patPriCode!=""){  //&& patPriCode!='-1'){
 	
	var url='<%=RegistrationConfig.REG_URL_FOR_AMOUNT_BY_CAT%>';
	httpRequestPost("POST",url,true);
	}
  }	
//alert(url);

 //httpRequest("POST",url,true);
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
		//flag=true;
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

function poulateFieldsWithEmpDtl(selectedElem){
 //  alert("sdsd"+selectedElem);
 // 	alert(" document.getElementsByName('patAddCountryCode')[0].value; = "+document.getElementsByName("patAddCountryCode")[0].value)
	document.getElementsByName("empIdChk")[0].value=selectedElem;	
	submitForm("GETEMPDEPENDENTDTL");
}  

function callThisOnload(){	                 
// 	alert("sdsdsdsd")     
	document.getElementsByName("departmentUnitCode")[0].focus();
	// alert(" document.getElementsByName('patAddCountryCode')[0].value; = "+document.getElementsByName("patAddCountryCode")[0].value)
	showState();
	showLocation();
	sendData();
	checkPatientCategory();
	enableSpouseField();
}

function showEmployeepopup(e){
    // if(!document.getElementsByName("patIdNo")[0].value=="")
    //	  openPopupWide(document.getElementsByName("patIdNo")[0].value,'<his:path src="/registration/employeeDtlWithDependents.cnt"/>',e);  
    //	  else
    //	  alert("Please enter Employee ID");
  	// alert(" document.getElementsByName('patAddCountryCode')[0].value; = "+document.getElementsByName("patAddCountryCode")[0].value)
    openPopup('<his:path src="/registration/employeeDtlWithDependents.cnt"/>',e,300,600);
  }

function showEmployeeDiv(){
  document.getElementById("divempIdLabel").style.display=""; 
  document.getElementById("divempIdControl").style.display=""; 
}



function primCatHandler(mode){
	submitForm(mode);		
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
	  // 	flag=isEmpty(document.forms[0].patAddStateName,"State Name") ;
	flag=true;
	}
	return flag;
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

function Validate()
{
	//alert("in Validate");
	var valid=true;	
	//var bb=isEmpty(document.forms[0].patAge,"Age");
	
// 	alert("dfgdsfhsgffgjgf");
	//alert("in Validate"); 
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
		 if(comboValidation(document.forms[0].patPrimaryCatCode,"Patient Category")
		  	&& validateLocation()	
			 && validateDot(document.forms[0].patAddCityLoc,"Location")
			  && checkIsrefer()
		 	  && isEmpty(document.forms[0].patRefDoctor,"Referred By Doctor") 
		 	)
		 	 {
			  valid=true;
			 }
			       
			 else
			 valid=false;     
			 
		 	
	}
	else
	{
	if(
	 comboValidation(document.forms[0].patPrimaryCatCode,"Patient Category")	
	 && validateCategoryIDRequired()	
	 && isEmpty(document.forms[0].patFirstName,"First Name") 
	 && validateDot(document.forms[0].patFirstName,"First Name")
	 && validateSpecialCharacter('/',document.forms[0].patFirstName,"First Name")
	 && validateSpecialCharacter('-',document.forms[0].patFirstName,"First Name")
	 && validateDot(document.forms[0].patMiddleName,"Middle Name")
	 && (client==clientSms ?(isEmpty(document.forms[0].patLastName,"Last Name")):true )
	 && validateDot(document.forms[0].patLastName,"Last Name")
	 && checkAgeOrDob()
	 && isValid(document.forms[0].patAge,"Age",125) 
	 && isEmpty(document.forms[0].patAgeUnit,"Age Unit")

	//&& validateDateAgainstSysDate(document.forms[0].patDOB,"Date of Birth")
	&& validatePatientDOBAgainstSysDate()
	&& validateAgeForDeptReg('<%=RegistrationConfig.MAX_AGE_TO_REGISTER_IN_CHILD_DEPT%>','<%=RegistrationConfig.CHILD_DEPT_CODE%>',document.getElementsByName("departmentUnitCode")[0])
	 && isSelected(document.forms[0].patGenderCode,"Gender")
	 && isEmpty(document.forms[0].patGuardianName,"Father Name")
	 && validateDot(document.forms[0].patGuardianName,"Father Name")
	 && validateDot(document.forms[0].patHusbandName,"Husband Name")
	 && validateDot(document.forms[0].patMotherName,"Mother Name")
	 && isSelected(document.forms[0].patNationalityCode,"Nationality")
	 && validateMonthlyIncome(document.getElementsByName("patMonthlyIncome")[0],'<%=RegistrationConfig.BPL_MONTHLY_INCOME%>','<%=Config.PRIMARY_CATEGORY_POOR_FREE_CODE%>')
	 && isSelected(document.forms[0].patAddCountryCode,"Country")
	  && checkState()
	  && validateLocation()
	 // && checkDistrict()	
	 && validateDot(document.forms[0].patAddCityLoc,"Location")
	 && validateDot(document.forms[0].patAddHNo,"House Number")
	 && validateDot(document.forms[0].patAddStreet,"Street")
	 && validateDot(document.forms[0].patAddDistrict,"District")
	 && validateDot(document.forms[0].patAddCity,"City")
	 && validateMinimumLength(document.forms[0].patAddPIN,'Pin Number','6') 
	 && validatePinNumber(document.forms[0].patAddPIN)
	 && isEmpty(document.forms[0].patAddContactNo,"Contact No")
	 && checkIsrefer()
	 && isEmpty(document.forms[0].patRefDoctor,"Referred By Doctor")
	 <his:madatory property="departmentUnitCode" /> 	 
	  )	 	 
	 
	 {
	  valid=true;
	 }
	       
	 else
	 valid=false;     
	  	 }

   //alert(valid);
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
</script>



<%String divdisplay = "\"\"";
			String divnddisplay = "\"\"";
			String divisrefdisplay = "\"\"";
			String divRefGnctdHospitalCode = "\"\"";
			String divRefHospname = "\"\"";
			String strdivage = "\"\"";
			String strdivdob = "\"\"";
			String divDocname = "\"\"";
			String divDocTitle = "\"\"";
			String divref = "\"\"";
			String divRefGnctd = "\"\"";
			String systemDate=hisglobal.presentation.WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy HH:mm");
			%>

<his:statusTransactionInProcess>
	<his:TitleTag name="Special Clinic New Patient Registration">
		<b><font  size="2" 
			face="Verdana, Arial, Helvetica, sans-serif"> </font></b>
		</his:TitleTag>
	
		   <% boolean flag=true;
		   Collection deptWithCSpeciality=(List)session.getAttribute(RegistrationConfig.REG_DESK_UNIT_WITH_SPECIALITY); 
		   Iterator itr=deptWithCSpeciality.iterator();
		   if(!itr.hasNext()){ 
			  flag=false; 
		   }
		   
		   %>
<%if(flag==true){ %>
	
	<his:ContentTag>

		<!--................... table 1   start ..........................................................................-->
<bean:define id="clientFlag" name="<%=Config.CLIENT_NAME %>" ></bean:define>
 <bean:define id="confFlagId" name="<%=Config.FILE_NO_GENERATION_FLAG_NAME %>" ></bean:define>
		<table width="100%" border="0" cellspacing="1" cellpadding="0"
			bgcolor="#EBEBEB">
			<tr>
				<td width="20%" class="tdfonthead">
				<div align="left"><font color="#FF0000" size="1"
					face="Verdana, Arial, Helvetica, sans-serif">*</font> <font
					color="#000000" size="2"
					face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
					key="specialClinic" /> </font></div>
				</td>

				<td width="35%" class="tdfont"><%System.out.println("before dept");

					%> <html:select name="SpecialClinicNewReg"
					property="departmentUnitCode" tabindex="1" >
					<bean:define id="unitCollection" type="java.util.List" name="<%=RegistrationConfig.REG_DESK_UNIT_WITH_SPECIALITY%>"></bean:define>
					<%if(unitCollection.size()>1){ %>
					<html:option value="-1">Select Value</html:option>
					<%} %>
					<html:options
						collection="<%=RegistrationConfig.REG_DESK_UNIT_WITH_SPECIALITY%>"
						property="value" labelProperty="label" />
				</html:select></td>
				<logic:equal  name="confFlagId" value="<%=Config.FILE_NO_GENRATION_MANNUAL_TRUE%>">
				<td width="17%" class="tdfonthead">
				<div align="right" id="fileidControl"   ><font color="#000000" size="2"
					face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
					key="fileNo" /> </font></div>
				</td>
				<td class="tdfont">
				<div align="left" id="fileid"  ><html:text name="SpecialClinicNewReg"
					property="fileNo" tabindex="1" maxlength="30" /></div></td>
				</logic:equal>
			</tr>
		</table>
	</his:ContentTag>
	<his:SubTitleTag name="Patient Detail"> 
	</his:SubTitleTag>

	<his:ContentTag>
		<table width="100%" border="0" cellspacing="1" cellpadding="0"
			bgcolor="#EBEBEB">
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
					name="SpecialClinicNewReg" property="patPrimaryCatCode"
					tabindex="1" styleClass="regcbo"
					onchange="sendData()">
					<bean:define id="patCatCollection" type="java.util.List" name="<%=RegistrationConfig.ESSENTIALBO_OPTION_PRIMARY_CATEGORY%>"></bean:define>
					<%if(patCatCollection.size()>1){ %>
					<html:option value="-1">Select Value</html:option>
					<%} %>
					<html:options
						collection="<%=RegistrationConfig.ESSENTIALBO_OPTION_PRIMARY_CATEGORY%>"
						property="value" labelProperty="label" />
				</html:select> </font></td>

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
					
									
					
				</td>
			
			
				
				
				<td id="divstaffIdLabel" class="tdfonthead" width="14%" style="display: none;" >
					<div align="right"  ><font color="#000000"
						size="2" face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
						key="id" /> </font></div>
				</td>
				<td class="tdfont" width="17%" nowrap id="divstaffIdControl" style="display: none;" >

					<div align="right"  ><html:text
						name="SpecialClinicNewReg"
						onkeypress="return validateAlphaNumericOnly(event,this);"
						styleClass="textbox" property="patIdNo" maxlength="8"
						tabindex="1" /> <img class="button"
						src="<his:path src='/hisglobal/images/ico_myfriends.gif'/>" tabindex="1"
						border="0" style="cursor:pointer" onkeypress="if(event.keyCode==13) showEmployeepopup(event);" onclick="showEmployeepopup(event);" >
					</div>
					
									
					
				</td>
				<td id="divCategoryAlignmentHead" style="display:none" width="14%" height="25" nowrap class="tdfonthead">
				</td>


				<td id="divCategoryAlignment" width="15%" class="tdfont" style="display:none;" >
				</td>	
				
							
			
			<td id="divIdTextLabel" width="17%" height="25" nowrap class="tdfonthead" style="display:none">
				<div align="right" >
				<font color="#000000" size="2"
					face="Verdana, Arial, Helvetica, sans-serif"><font color="#FF0000"
					size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font>
					<bean:message key="patientCategoryId" /> </font></div>
				</td>

				<td id="divIdTextBox" width="19%" class="tdfont" style="display:none" >
				<div  align="left" >
				<html:text name="SpecialClinicNewReg" onkeypress="return validateAlphaNumericOnly(event,this);"
						styleClass="textbox" property="patCatIdNo" maxlength="16"
						tabindex="1" ></html:text>
				</div>
				</td>
				
				<logic:equal name="clientFlag"  value="<%=Config.CLIENT_GNCTD%>">
				<td class="tdfonthead" width="17%">
				<div align="right"><font color="#000000" size="2"
					face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
					key="previous" /> <bean:message key="crNo" /> </font></div>
				</td>

				<td class="tdfont" width="17%" nowrap><html:text
					name="SpecialClinicNewReg" property="prevCrNo" styleClass="textbox"
					maxlength="13" tabindex="1"
					onkeypress="return validateNumeric(event)" />
						
				<img class="button"
					src="<his:path src='/hisglobal/images/PT_PORTAL_IC_LOGOUT_ENG_1.gif'/>"
					tabindex="1" border="0" style="cursor:pointer"
					onClick="submitFormOnValidate(validatePrevCRNo(),'GETPATDTLBYPREVCRNO');"
					onkeypress="if(event.keyCode==13) submitFormOnValidate(validatePrevCRNo(),'GETPATDTLBYPREVCRNO');">
					
				</td>
				</logic:equal>
				<logic:notEqual name="clientFlag"  value="<%=Config.CLIENT_GNCTD%>">
				<td width="17%" height="25" class="tdfonthead">
				</td>

				<td width="17%" class="tdfont" >
				</td>
				</logic:notEqual>
			</tr>
			<tr>
				<td width="17%" height="25" nowrap class="tdfonthead">
				<div align="right"><font color="#000000" size="2"
					face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
					key="patient" /> <bean:message key="name" /> </font></div>
				</td>

				<td width="19%" class="tdfonthead">
				<div align="left"><font color="#000000" size="2"
					face="Verdana, Arial, Helvetica, sans-serif"><font color="#FF0000"
					size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font> <bean:message
					key="first" /> </font></div>
				</td>

				<td width="14%" class="tdfonthead">
				<div align="left"><font size="2" color="#000000"
					face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
					key="middle" /> </font></div>
				</td>

				<logic:equal name="clientFlag"  value="<%=Config.CLIENT_SMS%>">	
				<td width="19%" class="tdfonthead">
				<div align="left"><font color="#000000" size="2"
					face="Verdana, Arial, Helvetica, sans-serif"><font color="#FF0000"
					size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font> <bean:message
					key="last" /> </font></div>
				</td>
				</logic:equal>
				<logic:notEqual name="clientFlag"  value="<%=Config.CLIENT_SMS%>">	
				<td width="17%" class="tdfont">
				<div align="left">
				<font color="#000000" size="2"
					face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
					key="last" /> </font></div></td>
				</logic:notEqual>
				<td width="17%" class="tdfonthead"></td>
				<td width="17%" class="tdfont"></td>

			</tr>
			<tr>

				<td width="17%" class="tdfonthead">&nbsp;</td>

				<td width="19%" class="tdfont"><html:text name="SpecialClinicNewReg"
					property="patFirstName" styleClass="textbox" tabindex="1"
					maxlength="30" onblur="isAlpha(this,'First Name')"
					onkeypress="return validateAlphabetsWithDotsAndForwardSlashWithHypen(event,this)" /></td>

				<td width="14%" class="tdfont"><html:text
					name="SpecialClinicNewReg" property="patMiddleName" tabindex="1"
					styleClass="textbox" onblur="isAlpha(this,'Middle Name')"
					maxlength="20" onkeypress="return validateAlphabetsWithDotsOnly(event,this)" />
				</td>

				<td width="17%" class="tdfont"><html:text name="SpecialClinicNewReg"
					property="patLastName" tabindex="1" styleClass="textbox"
					maxlength="20" onblur="isAlpha(this,'Last Name')"
					onkeypress="return validateAlphabetsWithDotsOnly(event,this)" /></td>
					
				<logic:equal name="clientFlag" value="<%=Config.CLIENT_PGIMER %>">
				<td width="14%" class="tdfonthead">
				<div id="nicklable" align="right"><font size="2" color="#000000"
					face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
					key="nickName" /> </font></div>
				</td>
				<td width="17%" class="tdfont">
				<div >
				<html:text name="SpecialClinicNewReg"
					property="patNickName" tabindex="1" styleClass="textbox"
					maxlength="30" onchange="isAlpha(this,'Nick Name')"
					onkeypress="return validateAlphabetsWithDotsOnly(event,this)" />
				</div>
				</td>
				
				</logic:equal>
</tr>
			

				<td width="17%" nowrap="nowrap" class="tdfonthead"  >
				<div align="center"><font color="#FF0000" size="1"
					face="Verdana, Arial, Helvetica, sans-serif">*</font> <font
					color="#000000" size="2"
					face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
					key="age" /></font> <html:radio name="SpecialClinicNewReg"
					property="isActualDob" tabindex="1" value="0"
					onclick="ageSelection()" /> <font color="#000000" size="2"
					face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
					key="dob" /></font> <html:radio name="SpecialClinicNewReg"
					property="isActualDob" tabindex="1" value="1"
					onclick="ageSelection()" /></div>
				</td>


				<logic:equal name="SpecialClinicNewReg" property="isActualDob"
					value="1">
					<%strdivage = "none";
						strdivdob = "";

					%>
				</logic:equal>
				<logic:equal name="SpecialClinicNewReg" property="isActualDob"
					value="0">
					<%System.out.println("inside case 0isActualDob:::::::::");
						strdivage = "";
						strdivdob = "none";

					%>
				</logic:equal>


				<td width="19%" class="tdfont" nowrap="nowrap" >
				<div id="divAge" style='display:<%=strdivage%>'><html:text
					name="SpecialClinicNewReg" size="4" property="patAge" tabindex="1"
					maxlength="3" onkeypress="return validateNumeric(event)" /> 
				<html:select
					name="SpecialClinicNewReg" property="patAgeUnit" tabindex="1"
					styleClass="smallcombo">
					<html:options
						collection="<%=RegistrationConfig.ESSENTIALBO_OPTION_AGE_TYPE %>"
						property="value" labelProperty="label" />
				</html:select></div>
				<%//String strEmpdivdob="none"; 
				// if(((SpecialClinicNewRegistrationFB)pageContext.findAttribute
					//	 ("SpecialClinicNewReg")).getPatPrimaryCatCode().equals(Config.PRIMARY_CATEGORY_EMPLOYEE_CODE)){
					//	 strEmpdivdob="";
					//	 strdivdob="none";
					//	 }%>
				<div id="divDob" style='display:<%=strdivdob%>'>
					<bean:define name="SpecialClinicNewReg" property="patDOB" id="dob" type="java.lang.String" />
					
					<div id="divPatDOB"></div>
					<script type="text/javascript">DateValidator.setup("divPatDOB","patDOB","<%=dob%>","dd-Mon-yyyy","textbox");</script>
										
					<!--<his:date name='<%="patDOB"%>' dateFormate="%d-%b-%Y" value='<%=dob%>' />-->
				</div>
				<%--<div id="divEmpDob" style='display:<%=strEmpdivdob%>'>
					<input type="text" value='<%=dob%>' readonly="readonly" size="12"/> </div>	
				--%>
				</td>
				<td width="14%" class="tdfonthead" nowrap="nowrap" >
				<div align="right"><font color="#FF0000" size="1"
					face="Verdana, Arial, Helvetica, sans-serif">*</font> <font
					color="#000000" size="2"
					face="Verdana, Arial, Helvetica, sans-serif"><bean:message
					key="gender" /></font></div>
				</td>

				<td width="17%" class="tdfont" nowrap="nowrap" ><html:select
					name="SpecialClinicNewReg" property="patGenderCode" tabindex="1"
					styleClass="regcbo">
					<html:option value="-1">Select Value</html:option>
					<html:options
						collection="<%=RegistrationConfig.ESSENTIALBO_OPTION_GENDER %>"
						property="value" labelProperty="label" />
				</html:select></td>
				
				<td width="17%" class="tdfonthead">
				<div align="right"><font color="#000000" size="2"
					face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
					key="maritalStatus" /> </font></div>
				</td>
				<td width="17%" class="tdfont"><html:select
					name="SpecialClinicNewReg" property="patMaritalStatusCode" onchange="enableSpouseField()"
					tabindex="1" styleClass="regcbo">
					<html:option value="-1">Select Value</html:option>
					<html:options
						collection="<%=RegistrationConfig.ESSENTIALBO_OPTION_MARITAL_STATUS %>"
						property="value" labelProperty="label" />
				</html:select></td>
			
			<tr>
			
				<td width="17%" class="tdfonthead" nowrap>
				<div align="right"><font color="#FF0000" size="1"
					face="Verdana, Arial, Helvetica, sans-serif">*</font> 
					<font color="#000000" size="2"
					face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
					key="fathersName" /></font></div>
				</td>

				<td width="17%" class="tdfont"><html:text name="SpecialClinicNewReg"
					property="patGuardianName" styleClass="textbox" tabindex="1"
					maxlength="60" onblur="isAlpha(this,'Father Name')"
					onkeypress="return validateAlphabetsWithDotsOnly(event,this)" /></td>
				
				<td width="17%" class="tdfonthead" nowrap>
				<div align="right"><font color="#000000" size="2"
					face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
					key="motherName" /></font></div>
				</td>
				<td width="17%" class="tdfont"><html:text name="SpecialClinicNewReg"
					property="patMotherName" styleClass="textbox" tabindex="1"
					maxlength="60" onblur="isAlpha(this,'Mother Name')"
					onkeypress="return validateAlphabetsWithDotsOnly(event,this)" /></td>

				<td width="17%" class="tdfonthead" nowrap>
				<div align="right"><font color="#000000" size="2"
					face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
					key="husbandName" /></font></div>
				</td>
				<td width="17%" class="tdfont"><html:text name="SpecialClinicNewReg"
					property="patHusbandName" styleClass="textbox" tabindex="1"
					maxlength="60" onblur="isAlpha(this,'Husband Name')"
					onkeypress="return validateAlphabetsWithDotsOnly(event,this)" /></td>
				
				
			</tr>
			<tr>
				<td class="tdfonthead" width="17%">
				<div align="right"><font color="#FF0000" size="1"
					face="Verdana, Arial, Helvetica, sans-serif">*</font> <font
					color="#000000" size="2"
					face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
					key="nationality" /> </font></div>
				</td>
				<td class="tdfont" width="17%">
				<div id="patCountryCombo"><html:select name="SpecialClinicNewReg"
					property="patNationalityCode" tabindex="1" styleClass="regcbo">
					<html:option value="-1">Select Value</html:option>
					<html:options
						collection="<%=RegistrationConfig.ESSENTIALBO_OPTION_NATIONALITY %>"
						property="value" labelProperty="label" />
				</html:select></div>
				</td>



				<td width="17%" class="tdfonthead">
				<div align="right"><font color="#000000" size="2"
					face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
					key="religion" /></font></div>
				</td>

				<td width="17%" class="tdfont"><html:select
					name="SpecialClinicNewReg" property="patReligionCode" tabindex="1"
					styleClass="regcbo">
					<html:option value="">Select Value</html:option>
					<html:options
						collection="<%=RegistrationConfig.ESSENTIALBO_OPTION_RELIGION %>"
						property="value" labelProperty="label" />
				</html:select>
				</td>
				
				
				<td width="17%" class="tdfonthead">
				<div align="right"><font color="#000000" size="2"
					face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
					key="monthlyIncome" /> </font></div>
				</td>

				<td class="tdfont" width="17%"><html:text name="SpecialClinicNewReg"
					property="patMonthlyIncome"
					onkeypress="return(currencyFormat(this,',','.',event))"
					tabindex="1" maxlength="11" styleClass="textbox" />
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
					<html:select name="SpecialClinicNewReg" tabindex="1" property="patOccupation"  styleClass="regcbo" >
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
					<html:select name="SpecialClinicNewReg" tabindex="1" property="patFatherOccupation"  styleClass="regcbo" >
		            <html:option value="">Select Value</html:option>
				  	<html:options collection ="<%=RegistrationConfig.ESSENTIALBO_OPTION_OCCUPATION_DTL %>"property = "value" labelProperty = "label"/>
                    </html:select>
				</td>
				<td width="17%" class="tdfonthead" nowrap="nowrap" >
				<div align="right"><font color="#000000" size="2"
					face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
					key="patHusbandOccupation" /> </font></div>
				</td>
				

				<td width="17%" class="tdfont" nowrap="nowrap" >
					<html:select name="SpecialClinicNewReg" tabindex="1" property="patHusbandOccupation"  styleClass="regcbo" >
		            <html:option value="">Select Value</html:option>
				  	<html:options collection ="<%=RegistrationConfig.ESSENTIALBO_OPTION_OCCUPATION_DTL %>"property = "value" labelProperty = "label"/>
                    </html:select>
				</td>
				<td class="tdfonthead" width="17%"></td>
				<td class="tdfont" width="17%"></td>
			</tr>
			
				</logic:equal>
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
					key="nationalId" /> </font></div>
				</td>
				</logic:notEqual>
				
				<td width="17%" class="tdfont" nowrap><html:text
					name="SpecialClinicNewReg" property="patNationalId"
					styleClass="textbox" maxlength="16" tabindex="1"
					onkeypress="return validateAlphaNumericWithSpecialCharacterOnly(event,this)"/>
				</td>
				
				<logic:equal name="clientFlag"  value="<%=Config.CLIENT_PGIMER%>">
				<td width="17%" class="tdfonthead" nowrap="nowrap" >
				<div align="right"><font color="#000000" size="2"
					face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
					key="patCardNo" /> </font></div>
				</td>
				
				<td width="17%" class="tdfont" nowrap><html:text
					name="SpecialClinicNewReg" property="patCardNo"
					styleClass="textbox" maxlength="30" tabindex="1" onkeypress="return validateAlphaNumericOnly(event,this)"/>
				</td>
				</logic:equal>
				<logic:notEqual name="clientFlag"  value="<%=Config.CLIENT_PGIMER%>">
				<td width="17%" class="tdfonthead" nowrap="nowrap" >
				</td>
				
				<td width="17%" class="tdfont">
				</td>
				</logic:notEqual>
				<td width="17%" class="tdfonthead">
				<div align="right"><font color="#000000" size="2"
					face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
					key="amountCollected" /> </font></div>
				</td>
				

				<td width="17%" class="tdfont">
				<html:text
					name="SpecialClinicNewReg" property="patAmountCollected"
					styleClass="textbox" maxlength="11" tabindex="1" readonly="true" />
				</td>
				
			</tr>
			 
				
		</table>
	</his:ContentTag>
	<his:SubTitleTagBroad name="Address Detail">
		   <table width="100%">
		   <tr>
             <td>	  
             		  <font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font>
                      <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
                      <bean:message key="country"/></font>
             </td>       
 			 <td>
 			 	       <html:select name="SpecialClinicNewReg" tabindex="1" property="patAddCountryCode"  styleClass="regcbo" onchange="showState()">
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
                     <html:select name="SpecialClinicNewReg" tabindex="1" property="patAddStateCode" styleClass="regcbo" onchange="if(this.value!='-1') sendDataForStateChange(this)" >
                       <html:option value="-1">Select Value</html:option>
                       <html:options collection = "<%=RegistrationConfig.ESSENTIALBO_OPTION_STATE %>" property = "value" labelProperty = "label"/>
                     </html:select>    
                     </div> 
                     <div id="divpatAddStateCodeText" style="" >
                     <html:text name="SpecialClinicNewReg" tabindex="1" property="patAddStateName" styleClass="regcbo" maxlength="50" onkeypress="return validateAlphabetsOnly(event,this)">
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
	                     <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	                     <bean:message key="hno"/>
	                     </font> 
                    </div>                    
                    </td>        
                                
                <td  class="tdfont" width="17%">
                <html:text  name="SpecialClinicNewReg" onkeypress="return validateAlphaNumericWithDotsOnly(event,this);" property="patAddHNo" tabindex="1" maxlength="15" styleClass="textbox"/>
                </td>
               
	            <td  class="tdfonthead" width="18%">                    
	            <div align="right">
	            
	            <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	            <bean:message key="street"/>  
	            </font>
	            </div>                    
	            </td>
	                     
	            <td class="tdfont" width="17%">
	            <html:text  name="SpecialClinicNewReg" property="patAddStreet" onkeypress="return validateAlphaNumericWithDotsOnly(event,this);" tabindex="1" maxlength="30" styleClass ="textbox"/> 
	            </td>
                  
                 <td  class="tdfonthead" width="17%">
	            <div align="right">
	           
	            <font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font>
	           
	            <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	            <bean:message key="location"/>
	            </font>
	            </div>
             </td>
 
 
       			<td  class="tdfont" width="17%">
       
          
                <div id="divpatAddCityLocCode" >                
	            <html:select name="SpecialClinicNewReg" tabindex="1" styleClass="regcbo" property="patAddCityLocCode" onchange="if(this.value!='-1') sendDataForCityLocation(this)">
				<html:option value="-1">Select Value</html:option>
				<html:options collection ="<%=RegistrationConfig.ESSENTIALBO_OPTION_LOCATION%>" property = "value" labelProperty = "label"/>
	            </html:select>
	            </div>	                    	                    	                    	                    	                    	                    	                    
	      
	            <div id="divpatAddCityLocation" >	                     
				<html:text  name="SpecialClinicNewReg" tabindex="1"  maxlength="50" onkeypress="return validateAlphaNumericWithDotsOnly(event,this);" styleClass="textbox" property="patAddCityLoc"/>
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
                <html:select name="SpecialClinicNewReg" tabindex="1" styleClass="regcbo" property="patAddDistrictCode">
				<html:option value="-1">Select Value</html:option>
				<html:options collection ="<%=RegistrationConfig.ESSENTIAL_BO_OPTION_DISTRICT_LIST_STATEWISE%>" property = "value" labelProperty = "label"/>
	            </html:select>
                </div>
                <div id="districtTextBox" style="display:none">
                <html:text  name="SpecialClinicNewReg" tabindex="1" property="patAddDistrict" onchange="isAlpha(this,'District')"  onkeypress="return validateAlphabetsWithDotsOnly(event,this)" maxlength="30" styleClass="textbox"/>
                </div>					
                </td>
                  
                <td  class="tdfonthead" width="18%" ><div align="right">
	            <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	            <bean:message key="city"/>
	            </font>
	            </div>
	            </td>
	            
	            <td class="tdfont" width="17%">
	            <html:text name="SpecialClinicNewReg" tabindex="1" property="patAddCity" onchange="isAlpha(this,'City')" onkeypress="return validateAlphabetsWithDotsOnly(event,this);" maxlength="30" styleClass="textbox"/> 
                </td>
                
                <td class="tdfonthead" width="14%">
	            <div align="right">
	            <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	            <bean:message key="pin"/>
	            </font> 
	            </div>
                </td>       
                    
                <td  class="tdfont" width="17%">
                <input type="text" id="pintext"  name="patAddPIN" tabindex="1"
                 size="17"  maxlength ="6"  onkeypress="return validateNumeric(event)"  /> 
                  </td>
                                                    
                    
              </tr>
                    
              <tr>
                      
                    <td  class="tdfonthead"  width="17%">
                    <div align="right">
                    <font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font>
                    <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
                    <bean:message key="contactNo"/>
                    </font>
                    </div>
                    </td>
             		
                    <td  class="tdfont" width="17%">   
 		            <div >
         		    <html:text name="SpecialClinicNewReg" tabindex="1" property="patAddContactNo"  maxlength ="30" styleClass="textbox" onkeypress="return validateNumeric(event)"/> 
                    </div>
                    </td>
                    
             <td width="18%" class="tdfonthead">
				<div align="right"><font color="#000000" size="2"
					face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
					key="areaCategory" /> </font></div>
				</td>

				<td width="18%" class="tdfont"><html:select
					name="SpecialClinicNewReg" property="patIsUrban" tabindex="1"
					styleClass="regcbo">
					<html:option value="">Select Value</html:option>
					<html:options
						collection="<%= RegistrationConfig.ESSENTIALBO_OPTION_AREA_CATEGORY %>"
						property="value" labelProperty="label" />
				</html:select></td> 
             <td class="tdfonthead" width="18%"></td>
             <td class="tdfont" width="18%"></td>    
                  </tr>		
                </table>
   
 </his:ContentTag>
	<bean:define name="SpecialClinicNewReg" property="isReferred"
		id="isref" type="java.lang.String" />

	<logic:equal name="isref" value="1">
		<%divisrefdisplay = "";

				%>
	</logic:equal>
	<%System.out.println("inside isref 0" + isref);

				%>
	<logic:equal name="isref" value="">
		<%System.out.println("inside isref 0");
					divisrefdisplay = "none";
					divRefGnctdHospitalCode = "none";
					divRefHospname = "none";
					System.out.println("inside isref 0");
					divDocname = "none";
					divDocTitle = "none";
					divref = "none";
					divRefGnctd = "none";

				%>
	</logic:equal>
	<logic:equal name="isref" value="0">
		<%System.out.println("inside isref 0");
					divisrefdisplay = "none";
					divRefGnctdHospitalCode = "none";
					divRefHospname = "none";
					System.out.println("inside isref 0");
					divDocname = "none";
					divDocTitle = "none";
					divref = "none";
					divRefGnctd = "none";

				%>
	</logic:equal>

	<logic:equal name="isref" value="1">
		<logic:equal name="SpecialClinicNewReg" property="referringInstType"
			value="O">
			<%System.out
								.println("patRefGnctdHospitalCode:::::::::empty");
						divRefGnctdHospitalCode = "none";
						divRefHospname = "";
						divDocname = "";
						divDocTitle = "";
						divref = "";
						divRefGnctd = "none";

					%>
		</logic:equal>
		<logic:equal name="SpecialClinicNewReg" property="referringInstType"
			value="G">
			<%System.out.println("patRefHospitalName:::::::::empty");
						divRefGnctdHospitalCode = "";
						divRefHospname = "none";
						divDocname = "";
						divDocTitle = "";
						divref = "";
						divRefGnctd = "";

					%>
		</logic:equal>
	</logic:equal>
	<%System.out.println("divRefHospname:::::::::" + divRefHospname);%>


	<!--................................... code for referred details........... -->

	<his:SubTitleTag name="Refer Detail">
	<table>
		<tr>
			<td width="20%" nowrap>
				<div align="right"><font color="#000000" size="2"
					face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
					key="isreferred" /> </font> <html:checkbox
					name="SpecialClinicNewReg" tabindex="1" property="isReferred"
					onclick="Isreferred(this)" value="1" />
				</div>
			</td>
		</tr>
	</table>
	</his:SubTitleTag>
	
	<div id="divReferredInstitute" style="display:<%=divref%>" >
	<his:ContentTag>
		<table width="100%" cellspacing="1" cellpadding="1">
			<tr>
			 <td width="25%" nowrap class="tdfonthead">
			
					<font color="#FF0000" size="1"
					face="Verdana, Arial, Helvetica, sans-serif">*</font> <font
					color="#000000" size="2"
					face="Verdana, Arial, Helvetica, sans-serif"> &nbsp;&nbsp;&nbsp;  <bean:message
					key="gnctd" /> </font> <html:radio name="SpecialClinicNewReg"
					property="referringInstType" tabindex="1" value="G"
					onclick="showdivhoscode(this)" /> <font color="#000000" size="2"
					face="Verdana, Arial, Helvetica, sans-serif">  &nbsp;&nbsp;&nbsp; <bean:message
					key="other" /> </font><html:radio
					name="SpecialClinicNewReg" property="referringInstType" value="O"
					tabindex="1" onclick="showdivhoscode(this)" />
				</td>

				<td width="25%" class="tdfont" nowrap>


				<div id='divRefHosCode' style='display:<%=divRefGnctdHospitalCode%>'>
				<font
					color="#000000" size="2"
					face="Verdana, Arial, Helvetica, sans-serif">  &nbsp;&nbsp;&nbsp; <bean:message
					key="hospital" /> <bean:message key="name" /></font> 
				<html:select name="SpecialClinicNewReg" tabindex="1"
					property="patRefGnctdHospitalCode" styleClass="registrationCmb">
					&nbsp;&nbsp;&nbsp;
					
					<html:option value="-1">Select Value</html:option>
					<html:options
						collection="<%=RegistrationConfig.ESSENTIALBO_OPTION_REF_HOSPITAL%>"
						property="value" labelProperty="label" />
				</html:select></div>
				<div id='divRefHosname' style='display:<%=divRefHospname%>'><font
					color="#000000" size="2"
					face="Verdana, Arial, Helvetica, sans-serif">  &nbsp;&nbsp;&nbsp; <bean:message
					key="hospital" /> <bean:message key="name" /></font> <html:text
					name="SpecialClinicNewReg" tabindex="1"
					property="patRefHospitalName"
					onkeypress="return validateAlphabetsOnly(event,this)" maxlength="100"
					styleClass="textboxBig" size="20" /></div>
				</td>
			</tr>
		</table>
	</his:ContentTag>
</div>

	<div id="divReferred" style="display:<%=divRefGnctd%>">
	<his:ContentTag>

		<table width="100%" cellpadding="1" cellspacing="1">

			<tr>
				<td width="25%" class="tdfonthead">
				<div align="right"><font color="#000000" size="2"
					face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
					key="referring" /> <bean:message key="hospital" /> <bean:message
					key="crNo" /> </font></div>
				</td>

				<td width="25%" class="tdfont"><html:text
					name="SpecialClinicNewReg" tabindex="1"
					property="patRefGnctdHospitalCrno" maxlength="13" tabindex="1"
					onkeydown="setPrevValue(this, event);"
					onkeyup="moveToRight(this, event);"
					onkeypress="return validateNumeric(event)" styleClass="textbox" />
				</td>

				<td width="25%" class="tdfonthead" nowrap>
				
				<div align="right"><font color="#FF0000" size="1"
					face="Verdana, Arial, Helvetica, sans-serif">*</font> <font
					color="#000000" size="2"
					face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
					key="referredBy" /></font></div>
				
				</td>

				<td width="25%" colspan="2" class="tdfont">
				
				<div align="left"><html:text name="SpecialClinicNewReg"
					styleClass="textbox" maxlength="100" property="patRefDoctor"
					onblur="isAlpha(this,'Referred By Doctor')" tabindex="1"
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
				<html:select name="SpecialClinicNewReg" property="patRefGnctdHospitalDept" tabindex="1" styleClass="regCbo" onchange="checkReferDepartment()">
					<html:option value="">Select Value</html:option>
					<html:option value="0">Other</html:option>
					<html:options collection="<%=RegistrationConfig.ESSENTIALBO_OPTION_REFERAL_DEPARTMENT_DTL %>" property="value" labelProperty="label"/>
				</html:select> 
			</td>
			 <td width="25%" class="tdfonthead" >
    			 	<div  align="right">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<bean:message key="other" />
					<bean:message key="department" /> </font>
					</div>
				</td>
				<td width="25%" class="tdfont">
				<html:text name="SpecialClinicNewReg"  property="patRefHospitalDeptOther" maxlength="20"  tabindex="1" styleClass="textbox" disabled="true" onkeypress="return validateAlphabetsOnly(event,this)"/>
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

				<td width="25%" class="tdfont"><html:text name="SpecialClinicNewReg"
					property="patRefGnctdHospitalDeptUnit" tabindex="1" maxlength="15"
					styleClass="textbox" onkeypress="return validateAlphaNumericOnly(event,this)"/>
				</td>
				<td width="25%" class="tdfonthead"></td>
				<td width="25%" class="tdfont"></td>
			</tr>
		</table>
	</his:ContentTag>
	</div>

	<!--.......................... code for referred details......... ends here.......... -->


	<!--.......................... code for visit stamp starts.......... -->



	<his:ButtonToolBarTag>

		<div align="center"><img class="button"
			src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>' tabindex="1"
			style=cursor:pointer
			onkeypress="if(event.keyCode==13)submitFormOnValidate(ValidateDepartmentUnit(document.getElementsByName('departmentUnitCode')[0]) && Validate(),'SAVE');"
			onclick="submitFormOnValidate(ValidateDepartmentUnit(document.getElementsByName('departmentUnitCode')[0]) && Validate(),'SAVE');" tabindex="1" /> <img
			class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'
			tabindex="1" style=cursor:pointer
			onkeypress="if(event.keyCode==13) submitPage('CANCEL');" tabindex="1"
			onclick="submitPage('CANCEL');"> <img class="button"
			src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' tabindex="1"
			style="cursor:pointer;" onclick="submitForm('NEW')"
			onkeypress="if(event.keyCode==13) submitForm('NEW');"></div>

	</his:ButtonToolBarTag>
	<%
				Date dt = (Date) session
						.getAttribute(Config.SYSDATEOBJECT);
				System.out.println("dt:::::::::" + dt);
				String sysDate = hisglobal.presentation.WebUTIL
						.getCustomisedSysDate(
								(Date) session
										.getAttribute(Config.SYSDATEOBJECT),
								"dd-MMM-yyyy");

				%>

	<%System.out.println("....2");%>
	<input type="hidden" name="sysDate" value="<%=sysDate%>" />
	<input type="hidden" name="hmode" value="unspecified" />
<html:hidden name="SpecialClinicNewReg" property="empIdChk"/>
	<html:hidden name="SpecialClinicNewReg" property="patDataFromEmployeeTable"/>
	<html:hidden name="SpecialClinicNewReg" property="patIdNo"/>
	<html:hidden name="SpecialClinicNewReg" property="isIdRequired"/>
	<html:hidden name="SpecialClinicNewReg" property="patAddDistrictCodeHidden"/>
<html:hidden name="SpecialClinicNewReg" property="patAddPINHidden"/>
<html:hidden name="SpecialClinicNewReg" property="patAddCityHidden"/>
<html:hidden name="SpecialClinicNewReg" property="patIsUrbanHidden"/>
	<%} %>
</his:statusTransactionInProcess>
	<his:status />

