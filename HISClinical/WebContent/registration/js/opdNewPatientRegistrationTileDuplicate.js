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
	if(document.getElementsByName('print')[0].value=='1')
	{
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