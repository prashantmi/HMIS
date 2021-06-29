<%try {%>

<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
 				
<%@ page import="java.util.*,registration.*"%>
<%@page import="registration.controller.fb.NewRegistrationFBDuplicate"%>
<%@page import="hisglobal.hisconfig.Config"%>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>

<his:javascript src="/registration/js/commonFunctions.js" />
<script language="JavaScript" src="/HISClinical/registration/js/regValidation.js"></script>
<script language="JavaScript" src="/HISClinical/registration/js/popup.js"></script>
<script language="JavaScript" src="/HISClinical/registration/js/newRegistration.js"></script>

<script language="JavaScript" src="/HISClinical/hisglobal/js/multilingualSupport/pramukhime.js"></script>
<script language="JavaScript" src="/HISClinical/hisglobal/js/multilingualSupport/pramukhindic.js"></script>
<script language="JavaScript" src="/HISClinical/hisglobal/js/multilingualSupport/pramukhime-common.js"></script>
<script language="JavaScript" src="/HISClinical/hisglobal/js/multilingualSupport/multilingualSupport.js"></script>


<script language="JavaScript" src="/HISClinical/hisglobal/utility/generictemplate/js/date_validator.js"></script>
<script language="JavaScript" src="/HISClinical/hisglobal/js/barcode_code39.js"></script>
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
<script>

function printPage() 
{
var frameElement = parent.document.getElementById("frmMain"); 
//alert("frameElement :"+frameElement);
var win = frameElement.contentWindow ;
win.print();
}

function callThisOnload(){	
	//alert("onload");
	//alert(document.getElementsByName('print')[0].value)	
	var LOCAL_LANGUAGE = "<%= RegistrationConfig.LOCAL_LANGUAGE %>";
	initMultilingual(LOCAL_LANGUAGE);	
	
	if(document.getElementsByName('print')[0].value=='1')
	{
		//get_object("divBarCodeControl").innerHTML=DrawCode39Barcode(get_object("divBarCodeControl").innerHTML, 0);
		//document.getElementById('divCardPrint').style.display="block"
		printPage();
		document.getElementsByName('print')[0].value='0'
		//document.getElementById('divCardPrint').style.display="none"
	}
	//alert(document.getElementsByName("isIdRequired")[0].value);
	document.getElementsByName("departmentCode")[0].focus();
	var patCatCode=document.getElementsByName("patPrimaryCatCode")[0].value;
	showState();
	//showLocation();
	document.getElementsByName("departmentCode")[0].focus;
	sendData();
	if(document.getElementsByName("isDuplicatePatientPopup")[0].value=="1"){
		
		var path="/HISClinical/registration/newRegistrationDeskDuplicate.cnt?hmode=POPUP";;
		openPopup(path);
	}
	
	//checkPatientCategory();
	//enableSpouseField();
	if(document.getElementsByName("errorMessage")[0].value!=""){
		//alert(document.getElementsByName("errorMessage")[0].value)
		document.getElementsByName("errorMessage")[0].value=""
	}
	//alert("Hi");
	document.getElementsByName("patCardNo")[0].disabled=true;
}


function openBPLPopup(e)
{
		//openPopup('/HISClinical/registration/transaction/regBPLSearchPopup.jsp',e,300,600);
		var hmode = "CHECKBPLDETAILS"; 
		var bplCardNo=document.forms[0].patBPLCardNo.value;
		var url = "/HISClinical/registration/newRegistrationDeskDuplicate.cnt?hmode="+hmode+"&bplCardNo="+bplCardNo;
		ajaxFunction(url,"3");
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

function sendData()
{    
	//alert("Inside send Data");
	 var patientPriCatCode=document.getElementsByName("patPrimaryCatCode")[0].value;
	 var patEmployeeCode="<%=Config.PRIMARY_CATEGORY_EMPLOYEE_CODE %>";
	 var patBPLCode="<%=Config.PRIMARY_CATEGORY_BPL_CODE %>";
	 if(patientPriCatCode!=patEmployeeCode)
	 {	
	 	//if(patientPriCatCode==patBPLCode)
	 	//{
	 	//	document.getElementById("bplFamilyTrId").style.display="";
		//	document.getElementById("bplFamilyDivId").style.display="";
		//	document.getElementById("mandatoryAreaCat").style.display="";
	 	//}
	 	//else
	 	//{
	 	//	document.getElementById("bplFamilyTrId").style.display="none";
		//	document.getElementById("bplFamilyDivId").style.display="none";
		//	document.getElementById("mandatoryAreaCat").style.display="none";
	 	//}
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
		var tariffIdValue='<%=RegistrationConfig.NEW_REGISTRATION_TARIFF_ID%>';
		var url = "/HISClinical/registration/newRegistrationDeskDuplicate.cnt?hmode="+hmode+"&tariffId="+tariffIdValue+"&patientCat="+patientPriCatCode;
		ajaxFunction(url,"1");
	}
	
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
function getAjaxResponse(res,mode)
{
		//alert("Response"+res);
		if(mode == '1')
		{
			var patientPriCatCode=document.getElementsByName("patPrimaryCatCode")[0].value;
			//if(patientPriCatCode=='13')//BPL
			//{
			//	document.getElementById("bplFamilyTrId").style.display="";
			//	var objEle = document.getElementById("bplFamilyDivId");
				//objEle.innerHTML = res.split('^')[1];
			//	document.forms[0].patAmountCollected.value=res.split('^')[0];
			//	document.forms[0].patBPLCardNo.focus();
			//}
			//else
			//{
				document.getElementById("bplFamilyTrId").style.display="none";
				document.forms[0].patAmountCollected.value=res.split('^')[0];
				document.forms[0].isIdRequired.value=res.split('^')[1];
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
		//showLocation();
	 }
	 
 	 if(patPriCode==patEmployeeCode)
 	 {	
 	 	//document.getElementById("divempIdLabel").style.display=""
 	 	//document.getElementById("divempIdControl").style.display=""
 	 	//document.getElementById("divstaffIdLabel").style.display="none"
 	 	//document.getElementById("divstaffIdControl").style.display="none"
 	 	document.getElementById("divIdTextLabel").style.display="none"
 	 	document.getElementById("divIdTextBox").style.display="none"
 	 	//document.getElementById("divCategoryAlignmentHead").style.display="none"
 	 	//document.getElementById("divCategoryAlignment").style.display="none"
 	 	//disableForEmployee(dataFromTableStatusObject,dataFromTabletrue,clientValue,clientPGIMER,patCatEmpFieldValue,patCatEmpFieldEditFalse);
 	 }
 	 else
 	 {
 	 	 //alert(document.getElementsByName("isIdRequired")[0].value);
 		 if(document.getElementsByName("isIdRequired")[0].value==<%=RegistrationConfig.PAT_CAT_ID_REQUIRED_YES%>)
	 	 {
	 	 	//document.getElementById("divempIdLabel").style.display="none";
	 	 	//document.getElementById("divempIdControl").style.display="none";
	 	 	//document.getElementById("divstaffIdLabel").style.display="none";
	 	 	//document.getElementById("divstaffIdControl").style.display="none";
	 	 	document.getElementById("divIdTextLabel").style.display="";
 	 		document.getElementById("divIdTextBox").style.display="";
 	 		//document.getElementById("divCategoryAlignmentHead").style.display="none";
 	 		//document.getElementById("divCategoryAlignment").style.display="none"; 	 	
 	 		//ageSelection()
	 	 }
	 	 else
	 	 {
	 	 	//document.getElementById("divempIdLabel").style.display="none";
	 	 	//document.getElementById("divempIdControl").style.display="none";
	 	 	//document.getElementById("divstaffIdLabel").style.display="none";
	 	 	//document.getElementById("divstaffIdControl").style.display="none";
	 	 	document.getElementById("divIdTextLabel").style.display="none";
 	 		document.getElementById("divIdTextBox").style.display="none";
 	 		//document.getElementById("divCategoryAlignmentHead").style.display="";
 	 		//document.getElementById("divCategoryAlignment").style.display=""; 	 		
 	 		//ageSelection()
	 	 }
	 	 	document.getElementsByName("patHusbandName")[0].disabled=false;
	 }	
	  
	 if(patPriCode=='<%=Config.PRIMARY_CATEGORY_POOR_FREE_CODE%>')
	 {
		//document.getElementById("familyHeadDiv").style.display="block";
		//document.getElementById("familyHeadTextDiv").style.display="block";
		//document.getElementById("relationDiv").style.display="block";
		//document.getElementById("relationComboDiv").style.display="block";
		//document.getElementById("mmjrkDiv").style.display="block";
		//document.getElementById("mmjrkTextDiv").style.display="block";
		//document.getElementsByName("patHusbandOccupation")[0].disabled=false;	
	}
	else
	{
		//document.getElementById("familyHeadDiv").style.display="none";
		//document.getElementById("familyHeadTextDiv").style.display="none";
		//document.getElementById("relationDiv").style.display="none";
		//document.getElementById("relationComboDiv").style.display="none";
		//document.getElementById("mmjrkDiv").style.display="none";
		//document.getElementById("mmjrkTextDiv").style.display="none";
	} 	
}



function validateCategoryIDRequired()
{
	var valid=true
	var patPriCode=document.getElementsByName("patPrimaryCatCode")[0].value;
	var patEmployeeCode="<%=Config.PRIMARY_CATEGORY_EMPLOYEE_CODE %>";
	if(document.getElementsByName("isIdRequired")[0].value=="1")
	{
		if(patPriCode!=patEmployeeCode)
		{
			valid=isEmpty(document.getElementsByName("patCatIdNo")[0],"Patient Category Id")
		}
	}
	return valid
}
 
function showState()
{ 
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
		document.getElementById("districtComboNonDefault").style.display="none";
		/*if(stateCode.options[stateCode.selectedIndex].value=="<%=RegistrationConfig.REGISTRATIONDESK_DEFAULT_STATE_CODE%>")
		{
			document.getElementById("divpatAddCityLocCode").style.display=""; 
		  	document.getElementById("divpatAddCityLocation").style.display="none"; 
	  	}*/
	 	document.getElementsByName("patAddStateCode")[0].value="<%=RegistrationConfig.REGISTRATIONDESK_DEFAULT_STATE_CODE%>";
		
	}
	else
	{
		document.getElementById("stateMandotary").style.display="none";
		document.getElementById("divpatAddStateCodeCombo").style.display="none";
		document.getElementById("divpatAddStateCodeText").style.display="";
		document.getElementById("stateNotMandotary").style.display="";
		//document.getElementById("divpatAddCityLocCode").style.display="none"; 
	  	document.getElementById("divpatAddCityLocation").style.display="";
	  	document.getElementById("districtCombo").style.display="none";
		document.getElementById("districtTextBox").style.display="";
		document.getElementById("districtComboNonDefault").style.display="none";
	  	document.getElementsByName('patAddCity')[0].value="";
	  	document.getElementsByName('patIsUrban')[0].value="<%=RegistrationConfig.REGISTRATIONDESK_DEFAULT_AREA_CATEGORY_CODE%>";
	  	document.getElementsByName('patAddPIN')[0].value="";
	  	//document.getElementsByName('patAddCityLocCode')[0].value="-1";
	  	document.getElementsByName('patAddDistrictCode')[0].value="-1";
	  	
	}
	//  showLocation();
	
}
function Validate()
{ 
	var valid=true;	
	var dataFromTableStatus=document.getElementsByName("patDataFromEmployeeTable")[0].value;
	//var bb=isEmpty(document.forms[0].patAge,"Age");
	var patPriCode=document.getElementsByName("patPrimaryCatCode")[0].value;
	var patEmployeeCode="<%=Config.PRIMARY_CATEGORY_EMPLOYEE_CODE %>";
	if((patPriCode==patEmployeeCode))
	{ 
		if(dataFromTableStatus=="1")//True 
	 	valid=true;
	 	else
	 	{
		 	alert("Please Fetch Data For Employee")
		 	valid=false; 
		 	return valid
	 	}
	}
	if((patPriCode==patEmployeeCode) && (dataFromTableStatus=="1") )
	{
		 if(comboValidation(document.forms[0].patPrimaryCatCode,"Patient Category")
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
		if(comboValidation(document.forms[0].patPrimaryCatCode,"Patient Category")
		 && validateCategoryIDRequired()
		 //&& checkBPLMandatoryFields()
		 && isEmpty(document.forms[0].patFirstName,"First Name") 
		 && validateDot(document.forms[0].patFirstName,"First Name")
		 && isEmpty(document.forms[0].patLastName,"Last Name")
		 && validateSpecialCharacter('/',document.forms[0].patFirstName,"First Name")
		 && validateSpecialCharacter('-',document.forms[0].patFirstName,"First Name")
		 && validateDot(document.forms[0].patMiddleName,"Middle Name")
		 //&& isEmpty(document.forms[0].patLastName,"Last Name")
		 && validateDot(document.forms[0].patLastName,"Last Name")
		 //&& comboValidation(document.forms[0].patCasteCode,"Patient Caste")
		 && checkAgeOrDob()
		 && isValid(document.forms[0].patAge,"Age",125) 
		 && isEmpty(document.forms[0].patAgeUnit,"Age Unit")
		 && validatePatientDOBAgainstSysDate()
		 && validateAgeForDeptReg('<%=RegistrationConfig.MAX_AGE_TO_REGISTER_IN_CHILD_DEPT%>','<%=RegistrationConfig.CHILD_DEPT_CODE%>')
		 && comboValidation(document.forms[0].patGenderCode,"Gender")
		 && validateFatherNameSpouseName()
		 && validateSpouseName()
		 && validateDot(document.forms[0].patGuardianName,"Father Name")
		 && validateDot(document.forms[0].patHusbandName,"Spouse Name")
		 && validateDot(document.forms[0].patMotherName,"Mother Name")
		 && comboValidation(document.forms[0].patNationalityCode,"Nationality")
		 //&& comboValidation(document.forms[0].patReligionCode,"Religion")
		 && chackAdhaarID()			 
		 && checkAnotherID()
		 && checkState()
		 && isEmpty(document.forms[0].patAddHNo,"Address")
		 && validateDot(document.forms[0].patAddCityLoc,"Location")
		 && validateDot(document.forms[0].patAddDistrict,"District")
		 && isEmpty(document.forms[0].patAddCity,"City / Village")
		 && validateDot(document.forms[0].patAddCity,"City / Village")
		 && validateMinimumLength(document.getElementById("pintext"),'Pin Number','6') 
		 && validatePinNumber(document.getElementById("pintext"))
		 && checkBPL()
		 && checkIsrefer()
	 	 && isEmpty(document.forms[0].patRefDoctor,"Referred By Doctor") 
	  ) 
	 {
	  	valid=true;
	 }
	 else
	 	valid=false;     
	 }
	// Setting Date from format 'dd-mm-yyyy' format to 'dd-Mon-yyyy'
	var patPriCode = document.getElementsByName("patPrimaryCatCode")[0].value;
	var patEmployeeCode = "<%=Config.PRIMARY_CATEGORY_EMPLOYEE_CODE %>";
	if(valid==true && patPriCode!=patEmployeeCode)
	{
		if(document.forms[0].patDOB.value!="")
		{
			var dateOfBirth = convertStrToDate(document.forms[0].patDOB.value,"dd-MM-yyyy");
			document.forms[0].patDOB.maxLength = 11;
			document.forms[0].patDOB.value = convertDateToStr(dateOfBirth,"dd-Mon-yyyy");
		}
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

function changeSpouseField(obj)
{
	if(obj.value==2)
	 	document.getElementsByName("patHusbandName")[0].disabled=true;
	else
		document.getElementsByName("patHusbandName")[0].disabled=false;		
}

function changeCardNField(obj)
{
	if(obj.value==-1)
	 	document.getElementsByName("patCardNo")[0].disabled=true;
	else
		document.getElementsByName("patCardNo")[0].disabled=false;
}

function validateSpouseName()
{
	var valid=true;
	if(document.getElementsByName("patMaritalStatusCode")[0].value==1)
	{	
		if(document.getElementsByName("patHusbandName")[0].value=="")
		{
			alert("For Married,Spouse Name Should not be Blank!");
			document.getElementsByName("patHusbandName")[0].focus();
			valid=false;
		}
	}
	return valid;	
}


</script>
<body>
<div id="noprint">	
<his:statusTransactionInProcess>
	<div class="errMsg" id="errMsg"></div>
	<div class="warningMsg" id="warningMsg"></div>
	<div class="normalMsg" id="normalMsg"></div>
	<his:TitleTag name="New Patient Registration">
		<b><font size="2" face="Verdana, Arial, Helvetica, sans-serif">
		</font></b>
	</his:TitleTag>

	<%
		String systemDate = hisglobal.presentation.WebUTIL.getCustomisedSysDate((Date) session.getAttribute(Config.SYSDATEOBJECT),"dd-MMM-yyyy HH:mm");
		boolean flag = true;
		//Collection dept = (List) session
		//		.getAttribute(RegistrationConfig.REGISTRATIONDESK_OPTION_DEPARTMENT);
		Collection dept = (List) session
				.getAttribute(RegistrationConfig.REGISTRATIONDESK_OPTION_DEPARTMENT);
		Iterator itr = dept.iterator();
		//System.out.println("------In JSP-------"+dept+"-----------------");
		NewRegistrationFBDuplicate fb = (NewRegistrationFBDuplicate) pageContext
				.findAttribute("NewRegistrationFBDuplicate");

		if (!itr.hasNext() && fb.getDepartmentsToVisitStamp().length == 0) {
			flag = false;
		}
	%>
	<%
	if (flag == true) {
	%>

	<his:statusTransactionInProcess>
		<his:ContentTag>
			<table width="100%" border="0" cellspacing="1" cellpadding="0"
				bgcolor="#EBEBEB">
				<tr>
					<td width="20%" class="LABEL"><font color="#FF0000" size="1"
						face="Verdana, Arial, Helvetica, sans-serif">*</font> <bean:message
						key="visitingDepartment" /></td>

					<td width="35%" class="tdfont"><bean:define
						id="deptCollection" type="java.util.List"
						name="<%=RegistrationConfig.REGISTRATIONDESK_OPTION_DEPARTMENT%>"></bean:define>
					<html:select name="NewRegistrationFBDuplicate"
						property="departmentCode" tabindex="1" styleClass="regCbo">
						<%
						if (deptCollection.size() > 1) {
						%>
						<html:option value="-1">Select Value</html:option>
						<%
						}
						%>
						<html:options
							collection="<%=RegistrationConfig.REGISTRATIONDESK_OPTION_DEPARTMENT%>"
							property="value" labelProperty="label" />
					</html:select></td>
				</tr>
			</table>
		</his:ContentTag>
		<his:SubTitleTag name="Patient Detail">
		</his:SubTitleTag>

		<his:ContentTag>
			<table width="100%" border="0" cellspacing="1" cellpadding="0">
				<tr>
					<td width="17%" nowrap class="LABEL"><bean:message
						key="mandatory1" /> <bean:message key="patient" /> <bean:message
						key="category" /></td>
					<td width="19%" class="tdfont"><font color="#000000" size="1"
						face="Verdana, Arial, Helvetica, sans-serif"> <html:select
						name="NewRegistrationFBDuplicate" property="patPrimaryCatCode"
						tabindex="2" styleClass="regcbo"
						onchange="if(this.value!='-1') sendData()">
						<html:option value="-1">Select Value</html:option>
						<html:options
							collection="<%=RegistrationConfig.ESSENTIALBO_OPTION_PRIMARY_CATEGORY%>"
							property="value" labelProperty="label" />
					</html:select> </font></td>
					<td class="LABEL" width="14%">
					<div id="divempIdLabel" style="display: none;"><font
						color="#000000" size="2"
						face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
						key="getEmploeeDetailWithDependent" /></font></div>
					<div id="divIdTextLabel" style="display: none;">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font>
					<bean:message key="categoryId" /></font>	
					</td>
					<td class="tdfont" width="17%" nowrap>
					<div id="divempIdControl" style="display: none;"><img
						class="button" src="../hisglobal/images/ico_myfriends.gif"
						tabindex="1" border="0" style="cursor: pointer"
						onkeypress="if(event.keyCode==13) showEmployeepopup(event);"
						onclick="showEmployeepopup(event);"></div>
					<div id="divIdTextBox" style="display: none;">
					<html:text name="NewRegistrationFBDuplicate" onkeypress="return validateAlphaNumericOnly(event,this);"
						styleClass="textbox" property="patCatIdNo" maxlength="16"
						tabindex="1" ></html:text>
					</div>
					</td>
					<td class="LABEL" width="14%"></td>
					<td class="tdfont" width="17%"></td>
				</tr>
				<tr style="display: none;" id="bplFamilyTrId">
					<td width="100%" colspan="6">
					<div id="bplFamilyDivId">
					<table width='100%'cellspacing='1px' cellpadding='0'>
						<tr><td width='17%' nowrap class='tdfonthead'>
						<div align='right'><font color='#FF0000' size='1' 
						face='Verdana, Arial, Helvetica, sans-serif'>*</font>
						<font color='#000000' size='2' face='Verdana, Arial, Helvetica, sans-serif'>
						<bean:message key="patCardNo" /></font></div></td>
						<td width='19%' class='tdfont'>
						<font color='#000000' size='1' face='Verdana, Arial, Helvetica, sans-serif'>
						<input type='text' name='patBPLCardNo' value='' tabindex="1" class='textbox' maxlength='10' onblur="openBPLPopup(event);"  onkeypress="return validateAlphaNumericWithSpecialCharacterOnlyforBPLCardNo(event,this)">
						</font></td>
						<td width='18%' nowrap class='tdfonthead'><div align='left'>
						<input type='button' name='BPL Search' value='BPL Search' onclick='openBPLPopup(event);'></div></td>
						<td width='18%' nowrap class='tdfonthead'></td>
						<td width='19%' class='tdfont'></td>
						<td width='17%' nowrap class='tdfonthead'><div align='right'></div></td>
						<td width='19%' class='tdfont'></td>
						</tr>
						<tr><td width='17%' nowrap class='tdfonthead'>
						<div align='right'>
						<font color='#000000' size='2' face='Verdana, Arial, Helvetica, sans-serif'>
						<bean:message key="familyHeadName" /></font></div></td>
						<td width='19%' class='tdfont'>
						<font color='#000000' size='1' face='Verdana, Arial, Helvetica, sans-serif'>
						<input type='text' class='textbox' name='familyHeadName' value='' onclick='' maxlength='80' onkeypress="return validateAlphabetsOnly(event,this)">
						</font></td>
						<td width='17%' nowrap class='tdfonthead'>
						<div align='right'>
						<font color='#000000' size='2' face='Verdana, Arial, Helvetica, sans-serif'>
						<bean:message key="familyHeadRelation" /></font></div></td>
						<td width='19%' class='tdfont'>
						<html:select name="NewRegistrationFBDuplicate" property='bplRelationCode'  
						styleClass='regcbo'>
						<html:option value='-1'>Select Value</html:option>
						<html:options
							collection="<%=RegistrationConfig.ESSENTIALBO_OPTION_RELATION_DTL %>"
							property="value" labelProperty="label" />
							</html:select>
						</td>
						<td width='17%' nowrap class='tdfonthead'><div align='right'>
						<font color='#000000' size='2' face='Verdana, Arial, Helvetica, sans-serif'><bean:message key="stateIdNo" /></font></div></td>
						<td width='20%' class='tdfont'>
						<input type='text' class='textbox' name='bplStateId' value='' onclick='' maxlength='10'></td>
						</tr>
						<tr><td width='17%' nowrap class='tdfonthead'>
						<div align='right'>
						<font color='#000000' size='2' face='Verdana, Arial, Helvetica, sans-serif'>
						 <bean:message key="mmjrkNo" /></font></div></td>
						<td width='19%' class='tdfont'>
						<font color='#000000' size='1' face='Verdana, Arial, Helvetica, sans-serif'>
						<input type='text' name='bplMMJRKNo' value='' class='textbox' onclick='' maxlength='10'>
						</font></td>
						<td width='18%' nowrap class='tdfonthead'><div align='right'>
						<font color='#000000' size='2' face='Verdana, Arial, Helvetica, sans-serif'><bean:message key="bplFamilyId" /></font></div></td>
						<td width='19%' class='tdfont'><input type='text' class='textbox' name='bplFamilyId' value='' onclick='' maxlength='10'></td>
						<td width='17%' nowrap class='tdfonthead'><div align='right'></div></td>
						<td width='19%' class='tdfont'></td>
						</tr>
	  		</table></div>
					</td>
				</tr>
				<tr>
					<td width="17%" height="25" nowrap class="LABEL"><bean:message
						key="patient" /> <bean:message key="name" /></td>
					<td width="19%" class="LABEL">
					<div align="left"><font color="#000000" size="2"
						face="Verdana, Arial, Helvetica, sans-serif"><bean:message
						key="mandatory1" /> <bean:message key="first" /> </font></div>
					</td>

					<td width="14%" class="LABEL">
					<div align="left"><font size="2" color="#000000"
						face="Verdana, Arial, Helvetica, sans-serif"
						style="display: block;"> <bean:message key="middle" /> </font></div>
					</td>
					<td width="19%" class="LABEL">
					<div align="left"><font color="#000000" size="2"
						face="Verdana, Arial, Helvetica, sans-serif"><bean:message
						key="mandatory1" /><bean:message
						key="last" /> </font></div>
					</td>
					<td width="17%" class="LABEL">
					<div id="relationDiv" style="display: none;"><font
						color="#000000" size="2"
						face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
						key="relation" /> </font></div>
					</td>
					<td width="17%" class="tdfont">
					<div id="relationComboDiv" style="display: none;"></div>
					</td>
				</tr>
				<tr>
					<td width="17%" class="LABEL">&nbsp;</td>
					<td width="19%" class="tdfont"><html:text
						name="NewRegistrationFBDuplicate" property="patFirstName"
						styleClass="textbox" tabindex="1" maxlength="30"
						onchange="isAlpha(this,'First Name')"
						onkeypress="return validateAlphabetsWithDotsAndForwardSlashWithHypen(event,this)" 
						onkeyup="multilingualConversion(this,document.getElementsByName('patFirstNameInMultiLang')[0]);"/></td>
					<td width="14%" class="tdfont" align="left"><html:text
						name="NewRegistrationFBDuplicate" property="patMiddleName"
						tabindex="2" styleClass="textbox"
						onchange="isAlpha(this,'Middle Name')" maxlength="20"
						onkeypress="return validateAlphabetsWithDotsOnly(event,this)"
						onkeyup="multilingualConversion(this,document.getElementsByName('patMiddleNameInMultiLang')[0]);"
						style="display:block;" /></td>
					<td width="17%" class="tdfont"><html:text
						name="NewRegistrationFBDuplicate" property="patLastName"
						tabindex="2" styleClass="textbox" tabindex="1" maxlength="30"
						onchange="isAlpha(this,'Last Name')"
						onkeyup="multilingualConversion(this,document.getElementsByName('patLastNameInMultiLang')[0]);"
						onkeypress="return validateAlphabetsWithDotsOnly(event,this)" /></td>
					<td width="17%" class="LABEL">
					<bean:message key="patCaste" /></td>
					<td class="tdfont" width="17%"><html:select
						name="NewRegistrationFBDuplicate" property="patCasteCode"
						tabindex="2" styleClass="regcbo">
						<html:option value="-1">Select Value</html:option>
						<html:options
							collection="<%=RegistrationConfig.ESSENTIALBO_OPTION_CASTE %>"
							property="value" labelProperty="label" />
					</html:select></td>
				</tr>
				<tr style="display: none">
					<td width="17%" class="LABEL">&nbsp;</td>
					<td width="19%" class="tdfont">
						<input type="text"
						name="patFirstNameInMultiLang" value="${NewRegistrationFBDuplicate.patFirstNameInMultiLang}"
						class="textbox" tabindex="1" maxlength="30"
						onchange="isAlpha(this,'First Name')"
						onkeypress="return validateAlphabetsWithDotsAndForwardSlashWithHypen(event,this)"
						onblur="callOnBlur();" onfocus="callOnClick();"/>
					</td>
					<td width="14%" class="tdfont" align="left">
						<input type="text"
						name="patMiddleNameInMultiLang" value="${NewRegistrationFBDuplicate.patMiddleNameInMultiLang}"
						tabindex="2" class="textbox"
						onchange="isAlpha(this,'Middle Name')" maxlength="20"
						onkeypress="return validateAlphabetsWithDotsOnly(event,this)"
						onblur="callOnBlur();" onfocus="callOnClick();"
						style="display:block;" />
						
					</td>
					<td width="17%" class="tdfont">
						<input type="text"
						name="patLastNameInMultiLang" value="${NewRegistrationFBDuplicate.patLastNameInMultiLang}"
						tabindex="2" class="textbox" tabindex="1" maxlength="30"
						onchange="isAlpha(this,'Last Name')"
						onkeypress="return validateAlphabetsWithDotsOnly(event,this)" 
						onblur="callOnBlur();" onfocus="callOnClick();"/>
					</td>
					
					<td width="17%" class="LABEL">
					</td>
					<td class="tdfont" width="17%"></td>
					
				</tr>
				<tr>
					<td width="17%" nowrap class="LABEL"><bean:message
						key="mandatory1" /> <bean:message key="age" /><html:radio
						name="NewRegistrationFBDuplicate" property="isActualDob"
						tabindex="2" value="0" onclick="ageSelection()" /><bean:message
						key="dob" /><html:radio name="NewRegistrationFBDuplicate"
						property="isActualDob" tabindex="2" value="1"
						onclick="ageSelection()" /></td>
					<td width="19%" class="tdfont" nowrap="nowrap">
					<div id="divAge"><html:text name="NewRegistrationFBDuplicate"
						size="4" property="patAge" tabindex="1" maxlength="3"
						onkeypress="return validateNumeric(event)" /> <html:select
						name="NewRegistrationFBDuplicate" property="patAgeUnit"
						tabindex="1" styleClass="smallcombo">
						<html:options
							collection="<%=RegistrationConfig.ESSENTIALBO_OPTION_AGE_TYPE %>"
							property="value" labelProperty="label" />
					</html:select></div>
					<div id="divDob" style="display: none;"><bean:define
						name="NewRegistrationFBDuplicate" property="patDOB" id="dob"
						type="java.lang.String" />
					<div id="divPatDOB"></div>
					<script type="text/javascript">DateValidator.setup("divPatDOB","patDOB","<%=dob%>","dd-MM-yyyy","textbox");</script>
					</div>
					</td>
					<td width="14%" class="LABEL" nowrap="nowrap"><bean:message
						key="mandatory1" /> <bean:message key="gender" /></td>
					<td width="17%" class="tdfont" nowrap="nowrap"><html:select
						name="NewRegistrationFBDuplicate" property="patGenderCode"
						tabindex="1" styleClass="regcbo">
						<html:option value="-1">Select Value</html:option>
						<html:options
							collection="<%=RegistrationConfig.ESSENTIALBO_OPTION_GENDER %>"
							property="value" labelProperty="label" />
					</html:select></td>
				<td width="17%" class="LABEL">
				<bean:message
					key="maritalStatus" />
				</td>
				<td width="17%" class="tdfont"><html:select
					name="NewRegistrationFBDuplicate" property="patMaritalStatusCode" 
					tabindex="2" styleClass="regcbo" onchange="changeSpouseField(this)">
					<html:option value="-1">Select Value</html:option>
					<html:options
						collection="<%=RegistrationConfig.ESSENTIALBO_OPTION_MARITAL_STATUS %>"
						property="value" labelProperty="label" />
				</html:select></td>

				</tr>
				<tr>
					<td width="17%" class="LABEL"><bean:message key="mandatory1" /><bean:message
						key="fathersName" /></td>
					<td width="17%" class="tdfont"><html:text
						name="NewRegistrationFBDuplicate" property="patGuardianName"
						styleClass="textbox" tabindex="1" maxlength="60"
						onchange="isAlpha(this,'Guardian Name')"
						onkeypress="return validateAlphabetsWithDotsOnly(event,this)" /></td>
					<td width="17%" class="LABEL"><bean:message key="husbandName" />
					</td>
					<td width="17%" class="tdfont"><html:text
						name="NewRegistrationFBDuplicate" property="patHusbandName"
						styleClass="textbox" tabindex="2" maxlength="60"
						onchange="isAlpha(this,'Guardian Name')" readonly="false"
						onkeypress="return validateAlphabetsWithDotsOnly(event,this)" /></td>
					<td width="17%" class="LABEL"><bean:message key="motherName" />
					</td>
					<td width="17%" class="tdfont"><html:text
						name="NewRegistrationFBDuplicate" property="patMotherName"
						styleClass="textbox" tabindex="2" maxlength="60"
						onchange="isAlpha(this,'Guardian Name')"
						onkeypress="return validateAlphabetsWithDotsOnly(event,this)" /></td>
				</tr>
				<tr>
					<td class="LABEL" width="17%"><bean:message key="mandatory1" />
					<bean:message key="nationality" /></td>
					<td class="tdfont" width="17%">
					<div id="patCountryCombo"><html:select
						name="NewRegistrationFBDuplicate" property="patNationalityCode"
						tabindex="1" styleClass="regcbo">
						<html:option value="-1">Select Value</html:option>
						<html:options
							collection="<%=RegistrationConfig.ESSENTIALBO_OPTION_NATIONALITY %>"
							property="value" labelProperty="label" />
					</html:select></div>
					</td>
					<td width="17%" class="LABEL">
					<bean:message key="religion" /></td>
					<td width="17%" class="tdfont">
					<html:select
						name="NewRegistrationFBDuplicate" property="patReligionCode"
						tabindex="2" styleClass="regcbo">
						<html:option value="-1">Select Value</html:option>
						<html:options
							collection="<%=RegistrationConfig.ESSENTIALBO_OPTION_RELIGION %>"
							property="value" labelProperty="label" />
					</html:select></td>
					<td width="17%" class="LABEL"><bean:message key="birplace" />
					</td>
					<td width="17%" class="tdfont"><html:text
						name="NewRegistrationFBDuplicate" property="patBirthPlace"
						styleClass="textbox" tabindex="2" maxlength="60"
						onchange="isAlpha(this,'Guardian Name')"
						onkeypress="return validateAlphabetsWithDotsOnly(event,this)" /></td>
				</tr>
				<tr>
					<td width="17%" class="LABEL"><bean:message key="patOccupation" /></td>
					<td width="17%" class="tdfont"><html:select
						name="NewRegistrationFBDuplicate" property="patOccupation"
						tabindex="2" styleClass="regcbo">
						<html:option value="-1">Select Value</html:option>
						<html:options
							collection="<%=RegistrationConfig.ESSENTIALBO_OPTION_OCCUPATION_DTL %>"
							property="value" labelProperty="label" />
					</html:select></td>
					<td class="LABEL" width="17%"><bean:message key="monthlyIncome" />
					</td>
					<td width="17%" class="tdfont"><html:text
						name="NewRegistrationFBDuplicate" property="patMonthlyIncome"
						styleClass="textbox" maxlength="16" tabindex="2"
						onkeypress="return validateNumericOnly(this,event)" />
					</td>
					<td width="17%" class="LABEL"><bean:message
						key="amountCollected" /></td>
					<td width="17%" class="tdfont"><html:text
						name="NewRegistrationFBDuplicate" property="patAmountCollected"
						styleClass="textbox" maxlength="11" readonly="true" />
						<bean:define name="NewRegistrationFBDuplicate" property="patAmountCollected" id="patAmountCollectedId" />
						<%System.out.println("patAmountCollectedId :"+patAmountCollectedId); %></td>
					
				</tr>
				<tr>
					<td class="LABEL" width="17%"><bean:message key="adhar" />
					</td>
					<td width="17%" class="tdfont"><html:text
						name="NewRegistrationFBDuplicate" property="patNationalId"
						styleClass="textbox" maxlength="16" tabindex="2"
						onkeypress="return validateAlphaNumericWithSpecialCharacterOnly(event)" />
					</td>
					<td width="17%" class="LABEL"><bean:message key="otherId" /></td>
					<td width="17%" class="tdfont"><html:select
						name="NewRegistrationFBDuplicate" property="patDocType"
						tabindex="2" styleClass="regcbo" onchange="changeCardNField(this)">
						<html:option value="-1">Select Value</html:option>
						<html:options
							collection="<%=RegistrationConfig.ESSENTIALBO_OPTION_VERIFICATION_DOCUMENTS %>"
							property="value" labelProperty="label" />
					</html:select></td>
					<td width="17%" class="LABEL"><bean:message key="patCardNo" /></td>
					<td width="17%" class="tdfont"><html:text
						name="NewRegistrationFBDuplicate" property="patCardNo" disabled="true"
						styleClass="textbox" maxlength="11"/></td>
				</tr>
			</table>

		</his:ContentTag>
		<his:SubTitleTagBroad name="Address Detail">
			<table width="100%">
				<tr>
					<td><bean:message key="mandatory1" /> <font color="#ffffff"
						size="2" face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
						key="country" /></font></td>
					<td><html:select name="NewRegistrationFBDuplicate"
						tabindex="1" property="patAddCountryCode" styleClass="regcbo"
						onchange="if(this.value!='-1') showState()">
						<html:option value="">Select Value</html:option>
						<html:options
							collection="<%=RegistrationConfig.ESSENTIALBO_OPTION_COUNTRY %>"
							property="value" labelProperty="label" />
					</html:select></td>
					<td>
					<div id="stateMandotary"><bean:message key="mandatory1" /> <font
						color="#ffffff" size="2"
						face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
						key="state" /> </font></div>
					<div id="stateNotMandotary"><font color="#000000" size="2"
						face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
						key="state" /></font></div>
					</td>
					<td>
					<div id="divpatAddStateCodeCombo" style=""><html:select
						name="NewRegistrationFBDuplicate" tabindex="1"
						property="patAddStateCode" styleClass="regcbo"
						onchange="if(this.value!='-1') getDistrictBasedOnState(this)">
						<html:option value="-1">Select Value</html:option>
						<html:options
							collection="<%=RegistrationConfig.ESSENTIALBO_OPTION_STATE %>"
							property="value" labelProperty="label" />
					</html:select></div>
					<div id="divpatAddStateCodeText" style="display: none;"><html:text
						name="NewRegistrationFBDuplicate" tabindex="1"
						property="patAddStateName" styleClass="regcbo">
					</html:text></div>
					</td>
				</tr>
			</table>
		</his:SubTitleTagBroad>

		<his:ContentTag>
			<table width="100%" cellpadding="0">
				<tr>
					<td class="LABEL" width="14%"><bean:message key="hno" /></td>
					<td class="tdfont" width="17%"><html:text
						name="NewRegistrationFBDuplicate"
						onkeypress="return validateAlphaNumericWithDotsOnly(event,this);"
						property="patAddHNo" tabindex="2" maxlength="15"
						styleClass="textbox" /></td>
					<td class="LABEL" width="18%"><bean:message key="street" /></td>
					<td class="tdfont" width="17%"><html:text
						name="NewRegistrationFBDuplicate" property="patAddStreet"
						onkeypress="return validateAlphaNumericWithDotsOnly(event,this);"
						tabindex="2" maxlength="30" styleClass="textbox" /></td>
					<td class="LABEL" width="17%"><bean:message key="location" />
					</td>
					<td class="tdfont" width="17%">
					<div id="divpatAddCityLocCode"></div>
					<div id="divpatAddCityLocation"><html:text
						name="NewRegistrationFBDuplicate" tabindex="2" maxlength="50"
						onkeypress="return validateAlphaNumericWithDotsOnly(event,this);"
						styleClass="textbox" property="patAddCityLoc" /></div>
					</td>
				</tr>
				<tr>
					<td class="LABEL" width="14%"><bean:message key="district" />
					</td>
					<td class="tdfont" width="17%">
					<div id="districtCombo"><html:select
						name="NewRegistrationFBDuplicate" tabindex="2" styleClass="regcbo"
						property="patAddDistrictCode">
						<html:option value="-1">Select Value</html:option>
						<html:options
							collection="<%=RegistrationConfig.ESSENTIAL_BO_OPTION_DISTRICT_LIST_STATEWISE%>"
							property="value" labelProperty="label" />
					</html:select></div>
					<div id="districtComboNonDefault" style="display: none;"><select
						name="patAddDistrictCode" tabindex="2" class="regcbo">
						<option value="-1">Select Value</option>
					</select></div>
					<div id="districtTextBox" style="display: none"><html:text
						name="NewRegistrationFBDuplicate" tabindex="2"
						property="patAddDistrict" onchange="isAlpha(this,'District')"
						onkeypress="return validateAlphabetsWithDotsOnly(event)"
						maxlength="30" styleClass="textbox" /></div>
					</td>
					<td class="LABEL" width="18%"><bean:message key="mandatory1" />
					<bean:message key="city" /> <bean:message key="slash" /> <bean:message
						key="village" /></td>
					<td class="tdfont" width="17%"><html:text
						name="NewRegistrationFBDuplicate" tabindex="1"
						property="patAddCity" onchange="isAlpha(this,'City')"
						onkeypress="return validateAlphabetsWithDotsOnly(event,this);"
						maxlength="30" styleClass="textbox" /></td>
					<td class="LABEL" width="14%"><bean:message key="pin" /></td>
					<td class="tdfont" width="17%"><input type="text" id="pintext"
						name="patAddPIN" tabindex="2" size="17" maxlength="6"
						onkeypress="return validateNumeric(event)" /></td>
				</tr>
				<tr>
					<td class="LABEL" width="17%"><bean:message key="tehsil" /></td>
					<td class="tdfont" width="17%">
					<div><html:text name="NewRegistrationFBDuplicate"
						tabindex="2" property="strPatAddressTehsil" maxlength="30"
						styleClass="textbox" onkeypress="return validateAlphabetsOnly(event,this)" />
					</div>
					</td>
					<td width="18%" class="LABEL"><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif" 
             id="mandatoryAreaCat" style="display: none;">
					<bean:message key="mandatory1" /></font>
					<bean:message key="areaCategory" />
					</td>
					<td width="18%" class="tdfont"><html:select
						name="NewRegistrationFBDuplicate" property="patIsUrban"
						tabindex="2" styleClass="regcbo">
						<html:option value="">Select Value</html:option>
						<html:options
							collection="<%= RegistrationConfig.ESSENTIALBO_OPTION_AREA_CATEGORY %>"
							property="value" labelProperty="label" />
					</html:select></td>
					<td class="LABEL" width="18%"><bean:message key="contactNo" /></td>
					<td class="tdfont" width="18%">
					<div><html:text name="NewRegistrationFBDuplicate"
						tabindex="2" property="patAddContactNo"  maxlength ="30" styleClass="textbox" onkeypress="return validateNumeric(event)" />
					</div>
					</td>
				</tr>
			</table>
		</his:ContentTag>
		<bean:define name="NewRegistrationFBDuplicate" property="isReferred"
			id="isref" type="java.lang.String" />
		<!--................................... code for referred details........... -->

		<his:SubTitleTag name="Refer Detail">
			<table width="100%" cellspacing="1" cellpadding="1">
			</table>
			<table width="100%" cellspacing="1" cellpadding="0">
				<tr>
					<td width="90%">
					<div align="right"><font color="#ffffff" size="2"
						face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
						key="isreferred" /> </font></div>
					</td>
					<td>
					<div align="left"><html:checkbox
						name="NewRegistrationFBDuplicate" tabindex="2"
						property="isReferred" onclick="Isreferred(this)" value="1" /></div>
					</td>
				</tr>
			</table>
		</his:SubTitleTag>
		<his:ContentTag>
			<table width="100%" cellspacing="1" id="divRefDtlId" cellpadding="1"
				style="display: none;">
				<tr>
					<td width="50%" nowrap class="LABEL" colspan="2">
					<div id="divReferredInstitute" align="right"><bean:message
						key="mandatory1" /> &nbsp;&nbsp;&nbsp; <font color="#000000"
						size="2" face="Verdana, Arial, Helvetica, sans-serif">
					&nbsp;&nbsp;&nbsp; <bean:message key="gnctd" /> </font> <html:radio
						name="NewRegistrationFBDuplicate" property="referringInstType"
						tabindex="1" value="G" onclick="showdivhoscode(this)" />
					&nbsp;&nbsp;&nbsp; <font color="#000000" size="2"
						face="Verdana, Arial, Helvetica, sans-serif">
					&nbsp;&nbsp;&nbsp; <bean:message key="other" /> </font> <html:radio
						name="NewRegistrationFBDuplicate" property="referringInstType"
						value="O" tabindex="1" onclick="showdivhoscode(this)" /></div>
					</td>
					<td width="25%" class="tdfont" nowrap>
					<div id='divRefHosCode'>&nbsp;&nbsp; <bean:message
						key="mandatory1" /> &nbsp;&nbsp;&nbsp; <font color="#000000"
						size="2" face="Verdana, Arial, Helvetica, sans-serif">
					&nbsp;&nbsp;&nbsp; <bean:message
						key="institute" /> <bean:message key="name" /></font> <html:select
						name="NewRegistrationFBDuplicate" tabindex="1"
						property="patRefGnctdHospitalCode" styleClass="registrationCmb">
						<html:option value="">Select Value</html:option>
						<html:options
							collection="<%=RegistrationConfig.ESSENTIALBO_OPTION_REF_HOSPITAL%>"
							property="value" labelProperty="label" />
					</html:select></div>
					<div id='divRefHosname' style='display: none;'>&nbsp;&nbsp;&nbsp;
					<bean:message key="mandatory1" />  &nbsp;&nbsp;&nbsp; <font color="#000000"
						size="2" face="Verdana, Arial, Helvetica, sans-serif">
					&nbsp;&nbsp;&nbsp; <bean:message
						key="institute" /> <bean:message key="name" /></font> <html:text
						name="NewRegistrationFBDuplicate" tabindex="1"
						property="patRefHospitalName"
						onkeypress="return validateAlphabetsOnly(event,this)"
						maxlength="100" styleClass="textboxBig" size="20" /></div>
					</td>
				</tr>
			</table>
		</his:ContentTag>
		<div id="divReferred" style="display: none;"><his:ContentTag>
			<table width="100%" cellpadding="1" cellspacing="1">
				<tr>
					<td width="25%" class="LABEL"><bean:message key="referring" />
					<bean:message key="institute" /> <bean:message key="crNo" /></td>
					<td width="25%" nowrap="nowrap" class="tdfont"><html:text
						name="NewRegistrationFBDuplicate" tabindex="1"
						property="patRefGnctdHospitalCrno" maxlength="13" tabindex="1"
						onkeydown="setPrevValue(this, event);"
						onkeyup="moveToRight(this, event);"
						onkeypress="return validateNumeric(event)" styleClass="textbox" />
					</td>
					<td width="15%" class="LABEL" nowrap><bean:message
						key="mandatory1" /><bean:message key="referredBy" /></td>
					<td width="15%" colspan="2" class="tdfont">
					<div align="left"><html:text
						name="NewRegistrationFBDuplicate" maxlength="60"
						property="patRefDoctor"
						onchange="isAlpha(this,'Referred By Doctor')" tabindex="1"
						styleClass="textbox"
						onkeypress="return validateAlphabetsOnly(event,this)" /></div>
					</td>
				</tr>
				<tr>
					<td width="25%" class="LABEL" nowrap><bean:message
						key="referring" /> <bean:message key="institute" /> <bean:message
						key="department" /></td>
					<td width="25%" class="tdfont"><html:select
						name="NewRegistrationFBDuplicate"
						property="patRefGnctdHospitalDept" tabindex="1"
						styleClass="regCbo" onchange="checkReferDepartment(this)">
						<html:option value="-1">Select Value</html:option>
						<html:option value="0">Other</html:option>
						<html:options
							collection="<%=RegistrationConfig.ESSENTIALBO_OPTION_REFERAL_DEPARTMENT_DTL%>"
							property="value" labelProperty="label" />
					</html:select></td>
					<td class="LABEL"><bean:message key="other" /> <bean:message
						key="department" /></td>
					<td class="tdfont"><html:text
						name="NewRegistrationFBDuplicate"
						property="patRefHospitalDeptOther" maxlength="20" tabindex="1"
						styleClass="textbox" disabled="true"
						onkeypress="return validateAlphabetsOnly(event,this)" /></td>
				</tr>
				<tr>
					<td width="25%" class="LABEL"><bean:message key="referring" />
					<bean:message key="institute" /> <bean:message key="unit" /></td>
					<td width="25%" class="tdfont"><html:text
						name="NewRegistrationFBDuplicate"
						property="patRefGnctdHospitalDeptUnit" tabindex="1" maxlength="15"
						styleClass="textbox"
						onkeypress="return validateAlphaNumericOnly(event,this)" /></td>
					<td width="25%" class="LABEL"></td>
					<td width="25%" class="tdfont"></td>
				</tr>
			</table>
		</his:ContentTag></div>

		<!--.......................... code for referred details......... ends here.......... -->

	</his:statusTransactionInProcess>

	<table class="TABLEWIDTH" align="center" cellspacing="1px">
		<tr>
			<td align="center">
			<div align="center"><img class="button"
				src='../HIS/hisglobal/images/btn-sv.png' tabindex="1"
				style="cursor: pointer;"
				onkeypress="if(event.keyCode==13)submitFormOnValidate(ValidateDepartment(document.getElementsByName('departmentsToVisitStamp')[0]) && Validate(),'SAVE');"
				onclick="submitFormOnValidate(ValidateDepartment(document.getElementsByName('departmentsToVisitStamp')[0]) && Validate(),'SAVE');"
				tabindex="1" /> <img class="button"
				src='../HIS/hisglobal/images/buttons/btn-ccl.png' tabindex="1"
				style="cursor: pointer;"
				onkeypress="if(event.keyCode==13) submitPage('CANCEL');"
				tabindex="1" onclick="submitPage('CANCEL');"> <img
				class="button" src='../../HIS/hisglobal/images/buttons/btn-clr.png' tabindex="1"
				style="cursor: pointer;" onclick="submitForm('NEW')"
				onkeypress="if(event.keyCode==13) submitForm('NEW');"> 
				<!--<img
				class="button" src='../hisglobal/images/btn-reprint.png' tabindex="1"
				
				style="cursor: pointer;" onclick="printCard()"
				onkeypress="if(event.keyCode==13) printCard();">
				
				--></div>
			</td>
		</tr>
	</table>

	<input type="hidden" name="sysDate" value="<%=systemDate%>" />
	<input type="hidden" name="hmode" value="unspecified" />

	<html:hidden name="NewRegistrationFBDuplicate" property="empIdChk" />
	<html:hidden name="NewRegistrationFBDuplicate" property="patIdNo" />
	<html:hidden name="NewRegistrationFBDuplicate" property="isIdRequired" />
	<html:hidden name="NewRegistrationFBDuplicate" property="patDataFromEmployeeTable" />
	<html:hidden name="NewRegistrationFBDuplicate" property="patAddDistrictCodeHidden" />
	<html:hidden name="NewRegistrationFBDuplicate" property="patAddPINHidden" />
	<html:hidden name="NewRegistrationFBDuplicate" property="patAddCityHidden" />
	<html:hidden name="NewRegistrationFBDuplicate" property="patIsUrbanHidden" />
	<html:hidden name="NewRegistrationFBDuplicate" property="isDuplicatePatientPopup" />
	<html:hidden name="NewRegistrationFBDuplicate" property="patCrNo" />
	<html:hidden name="NewRegistrationFBDuplicate" property="errorMessage" />
	<html:hidden name="NewRegistrationFBDuplicate" property="isOldPatient" />
	<html:hidden name="NewRegistrationFBDuplicate" property="oldPatCrNo" />
	<html:hidden name="NewRegistrationFBDuplicate" property="print"/>
	

	<input type="hidden" name="patMaritalStatusCode">
	<input type="hidden" name="patAddHNo">
	<input type="hidden" name="patNickName">
	<input type="hidden" name="patHusbandOccupation">
	<input type="hidden" name="bplDetailsFound">


	<%
	}
	%>
</his:statusTransactionInProcess>
<html:hidden name="NewRegistrationFBDuplicate" property="configFlag" />
</div>
<his:status />

</body>

<%}catch(Exception e)
{
	System.out.println("e: " + e);

e.printStackTrace();	
}%>