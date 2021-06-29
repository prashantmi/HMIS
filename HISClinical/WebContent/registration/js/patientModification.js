function callThisOnload()
{
	initMultilingual(LOCAL_LANGUAGE);	
	
	// Patient Detail
	if(document.getElementsByName("patCrNo")[0])	document.getElementsByName("patCrNo")[0].focus();
	
	//if(document.getElementsByName("patIdNo")[0])
	//{
		// showdelhidiv();
		//showPoorPatDiv(primaryCatPoorFreeCode);	
		enableRelation();
		checkEmployee();
		showState();
		showLocation();
		changeCardNField(document.getElementsByName("patDocType")[0]);
		//focusCrNo();
		
		// Address Detail callThisOnload
		if(document.getElementById("addTypeCombo"))
		{
			if(document.getElementsByName("addModify")[0].value==isAddressAdded)
			{
				document.getElementById("addTypeCombo").style.display="";
				document.getElementById("addTypeText").style.display="none";
			}
			if(document.getElementsByName("addModify")[0].value==isAddressModified)
			{
				document.getElementById("addTypeCombo").style.display="none";
				document.getElementById("addTypeText").style.display="";
			}
			setAddType();
		}
	//}
}

function enableRelation()
{	
	var obj = document.getElementsByName("requestBy")[0];
	if(!obj)	return;
	if(obj.value=="1")
		document.getElementsByName("requestRelation")[0].disabled=false;
	else 
		document.getElementsByName("requestRelation")[0].disabled=true;
	if(obj.value=="1")
	{
		document.getElementById("policeDetailDiv").style.display="none";
		document.getElementById("requesterDIV").style.display="block";
		document.getElementById("relationshipSelfId").style.display="none";	
		document.getElementById("relationshipRelativeId").style.display="block";
	}
	else if(obj.value=="2")
	{
		document.getElementById("policeDetailDiv").style.display="block";
		document.getElementById("requesterDIV").style.display="block";
	}
	else
	{
		document.getElementById("policeDetailDiv").style.display="none";
		document.getElementById("requesterDIV").style.display="none";
		document.getElementById("relationshipSelfId").style.display="block";	
		document.getElementById("relationshipRelativeId").style.display="none";	
	}
}

function checkEmployee()
{
	//var patIdNo=document.getElementsByName("patIdNo")[0].value;
	var patCatCode=document.getElementsByName("patPrimaryCatCode")[0].value;
	var patCatCodeEmployee=primaryCategoryEmployeeCode;
	var addtypeCode=document.getElementsByName("patFBAddTypeCode")[0].value;
	var addTypeCurrentTrue=isAddressCurrentTrue;
	var addModify=document.getElementsByName("addModify")[0].value 
	
	if( (patCatCode==patCatCodeEmployee) && ((fieldEditableValue==fieldEditableFalse) || (dataFromTableValue==dataFromTableTrue) ) )
	{
		document.getElementsByName("patFirstName")[0].disabled=true;
		document.getElementsByName("patMiddleName")[0].disabled=true;
		document.getElementsByName("patLastName")[0].disabled=true;
		
		document.getElementsByName("isActualDob")[0].disabled=true;
		document.getElementsByName("patAge")[0].disabled=true;
		document.getElementsByName("patAgeUnit")[0].disabled=true;
		document.getElementsByName("patDOB")[0].disabled=true;
		
		document.getElementsByName("patGenderCode")[0].disabled=true;
		document.getElementsByName("patCasteCode")[0].disabled=true;
		document.getElementsByName("patGuardianName")[0].disabled=true;
		document.getElementsByName("patHusbandName")[0].disabled=true;
		document.getElementsByName("patMotherName")[0].disabled=true;
		document.getElementsByName("patNationalityCode")[0].disabled=true;
		document.getElementsByName("patReligionCode")[0].disabled=true;

		document.getElementsByName("patCardNo")[0].disabled=true;
		document.getElementsByName("patNickName")[0].disabled=true;
		document.getElementsByName("patHusbandOccupation")[0].disabled=true;

		if( (addModify==isAddressModified) && (addtypeCode==addTypeCurrentTrue) )
		{
			document.getElementsByName("strPatAddressTehsil")[0].disabled=true;
			document.getElementsByName("patAddCountryCode")[0].disabled=true;
			document.getElementsByName("patAddStateCode")[0].disabled=true;
			document.getElementsByName("patAddStateName")[0].disabled=true;
			//  document.getElementsByName("patAddCityLocCode")[0].disabled=true;
			//  document.getElementsByName("patAddCityLoc")[0].disabled=true;
			document.getElementsByName("patAddHNo")[0].disabled=true;
			document.getElementsByName("patAddStreet")[0].disabled=true;
			document.getElementsByName("patAddDistrict")[0].disabled=true;
			document.getElementsByName("patAddDistrictCode")[0].disabled=true;
			document.getElementsByName("patIsUrban")[0].disabled=true;
			document.getElementsByName("patAddCity")[0].disabled=true;
			document.getElementsByName("patAddPIN")[0].disabled=true;
			document.getElementsByName("patAddContactNo")[0].disabled=true;
		}
	}
}

function showState()
{ 
	if(document.getElementsByName("patAddCountryCode")[0])
	{
		var contryCode = document.getElementsByName("patAddCountryCode")[0].value;
		var stateCode= document.getElementsByName("patAddStateCode")[0];
		if(contryCode==registrationDeskDefaultCountryCode)
		{
			document.getElementById("stateMandotary").style.display="";
			document.getElementById("stateNotMandotary").style.display="none";
			document.getElementById("divpatAddStateCodeCombo").style.display="";
			document.getElementById("divpatAddStateCodeText").style.display="none";
			/*if(stateCode.options[stateCode.selectedIndex].value==registrationDeskDefaultStateCode)
			{
				document.getElementById("divpatAddCityLocCode").style.display=""; 
		  		document.getElementById("divpatAddCityLocation").style.display="none"; 
		  	}*/
			document.getElementById("divpatAddCityLocCode").style.display="none"; 
		  	document.getElementById("divpatAddCityLocation").style.display="";
			document.getElementById("districtCombo").style.display="";
			document.getElementById("districtTextBox").style.display="none";
		}
		else
		{
			document.getElementById("stateMandotary").style.display="none";
			document.getElementById("stateNotMandotary").style.display="";
			document.getElementById("divpatAddStateCodeCombo").style.display="none";
			document.getElementById("divpatAddStateCodeText").style.display="";
			document.getElementById("divpatAddCityLocCode").style.display="none"; 
		  	document.getElementById("divpatAddCityLocation").style.display="";
		  	document.getElementById("districtCombo").style.display="none";
			document.getElementById("districtTextBox").style.display="";
			 
		  	document.getElementsByName('patAddCity')[0].value="";
		  	document.getElementsByName('patIsUrban')[0].value=registrationDeskDefaultAreaCatCode;
		  	document.getElementsByName('patAddPIN')[0].value="";
		  	document.getElementsByName('patAddCityLocCode')[0].value="-1";
		  	document.getElementsByName('patAddDistrictCode')[0].value="-1";
		}
	}
}
	
function showLocation()
{
	if(document.getElementsByName("patAddCountryCode")[0])
	{
		var contryCode=document.getElementsByName("patAddCountryCode")[0].value;
		var elmt= document.getElementsByName("patAddStateCode")[0];
		if(locationRequired==locationRequiredYes)
		{ 
			if((contryCode==registrationDeskDefaultCountryCode)  && (elmt.options[elmt.selectedIndex].value==registrationDeskDefaultStateCode))
			{
				document.getElementById("divpatAddCityLocCode").style.display=""; 
				document.getElementById("divpatAddCityLocation").style.display="none"; 
			}
			else
			{
				document.getElementById("divpatAddCityLocCode").style.display="none"; 
				document.getElementById("divpatAddCityLocation").style.display="";   
			}
		}
		else
		{
			document.getElementById("divpatAddCityLocCode").style.display="none"; 
			document.getElementById("divpatAddCityLocation").style.display="";   
			// elmt.options[elmt.selectedIndex].value=registrationDeskDefaultStateCode;
		}
	}
}

function setAddType()
{
	var addModify = document.getElementsByName("addModify")[0].value; 
	if(addModify==isAddressModified)
	{
		//var addTypeCodeArray=document.getElementsByName("patFBAddTypeCode")[0].value.split("@");
		document.getElementsByName("patAddTypeCode")[0].value=document.getElementsByName("patFBAddTypeCode")[0].value;
		//document.getElementsByName("patAddTypeLable")[0].value=//addTypeCodeArray[1]; //document.getElementsByName("patFBAddTypeCode")[0].options[document.getElementsByName("patFBAddTypeCode")[0]].text;
	}
}

function captureImage(e)
{
	document.getElementsByName("isNewImageUploaded")[0].value="1";
	openPopup("/HISClinical/registration/uploadFile.cnt",e,300,600);
}

function getAddressDetailForModification()
{
	setAddType();
	
	//checkValueOfCombo(document.getElementsByName('patFBAddTypeCode')[0],'Address Type','MODIFYADDRESS');
	submitForm('MODIFYADDRESS');
}


function ValidatePatientDtlModification()
{
	var valid=true;

	var addTypeCurrentTrue=isAddressCurrentTrue;

	//var patIdNo=document.getElementsByName("patIdNo")[0].value;
	var patCategoryCode=document.getElementsByName("patPrimaryCatCode")[0].value;

	var addtypeCode=document.getElementsByName("patAddTypeCode")[0].value;
	
	//In the first case of validation if the patient is employee
	if( (patCategoryCode==primaryCategoryEmployeeCode) && ( (fieldEditableValue==fieldEditableFalse) || (dataFromTableValue==dataFromTableTrue) )
	 //----- && (addtypeCode==isAddressCurrentTrue)
	)
	{
		if( validateDot(document.forms[0].patAddCityLoc,"Location")
			//&& validateLocation()
			&& validateRequestDetail()
			&& validateDocumentAdded())
		{
			valid=true;
		}
		else
			valid=false;
	}
	else	//In the Second case of validation if the patient is a Non Employee
	{
		if( isEmpty(document.forms[0].patFirstName,"First Name")
			&& validateDot(document.forms[0].patFirstName,"First Name")
			&& validateSpecialCharacter('/',document.forms[0].patFirstName,"First Name")
			&& validateSpecialCharacter('-',document.forms[0].patFirstName,"First Name")
			&& validateDot(document.forms[0].patMiddleName,"Middle Name") 
			//&& isEmpty(document.forms[0].patLastName,"Last Name")
			&& validateDot(document.forms[0].patLastName,"Last Name") 
			//&& comboValidation(document.forms[0].patCasteCode,"Patient Caste")
			
			//&& isEmpty(document.getElementsByName("patIdNo")[0],"Patient Category Id")
			
			//&& isEmpty(document.forms[0].patGuardianName,"Father Name")
			&& validateFatherNameSpouseName()
			&& validateSpouseName()
			&& validateDot(document.forms[0].patGuardianName,"Father Name") 
			&& validateDot(document.forms[0].patHusbandName,"Spouse Name") 
			&& validateDot(document.forms[0].patMotherName,"Mother Name") 
			
			&& isValid(document.forms[0].patAge,"Age",125) 
			&& isEmpty(document.forms[0].patAgeUnit,"Age Unit")
			//&& validateDateAgainstSysDate(document.forms[0].patDOB,"Date of Birth") 
			&& validatePatientDOBAgainstSysDate()
			&& checkAgeOrDob()

			&& isSelected(document.forms[0].patGenderCode,"Gender")
			&& isSelected(document.forms[0].patNationalityCode,"Nationality")
			&& checkAnotherID()
			//&& isEmpty(document.forms[0].patReligionCode,"Religion")
			&& isSelected(document.forms[0].patPrimaryCatCode,"Patient Category")
			

			// if Address Modified
			&& ( !(document.forms[0].patAddCountryCode) 
				|| ( validateAddressTypeCombo()
					&& isSelected(document.forms[0].patAddCountryCode,"Country")
					&& checkState()
					//&& validateLocation()	
					&& validateDot(document.forms[0].patAddCityLoc,"Location")
					&& validateDot(document.forms[0].patAddHNo,"House Number")
					//&& validateDot(document.forms[0].patAddStreet,"Street")
					&& validateDot(document.forms[0].patAddDistrict,"District")
					&& isEmpty(document.forms[0].patAddCity,"City / Village")
					&& validateDot(document.forms[0].patAddCity,"City / Village")
				 	&& validateMinimumLength(document.forms[0].patAddPIN,'Pin Number','6')
				 	&& validatePinNumber(document.forms[0].patAddPIN)
				 	//&& isEmpty(document.forms[0].patAddContactNo,"Contact No")
					&& isSelected(document.forms[0].patIsUrban,"Area Category") 
					&& validateEmail(document.forms[0].patAddEmailId))
				)
			&& checkSingle()
			&& validateDocumentAdded()
			&& validateRequestDetail()
		
		)
		{
			valid=true;
		}
		else
			valid=false;
			
	}
	
	// Setting Date from format 'dd-mm-yyyy' format to 'dd-Mon-yyyy'
	var patPriCode = document.getElementsByName("patPrimaryCatCode")[0].value;
	if(valid==true //&& patPriCode!=primaryCategoryEmployeeCode
	)
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

	//alert(valid);
	return valid;
}

function checkSingle()
{
	var valid =true;	
	if(document.getElementsByName("patMaritalStatusCode")[0].value=="2"&&document.getElementsByName("patHusbandName")[0].value!="")
	{
		alert("For Single, Spouse Name should be Blank..!");
		valid=false;
	}
	else if(document.getElementsByName("patMaritalStatusCode")[0].value=="-1"&&document.getElementsByName("patHusbandName")[0].value!="")
	{
		alert("Please Select the Marital Status..!");
		valid=false;
	}
		
	return valid;
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
	else
	{
		if (document.getElementsByName("patCardNo")[0].value!="")
		{
			alert("Plaese Select Other Id..!");
			document.getElementsByName("patDocType")[0].focus();
			valid=false;
		}
	}
	
	return valid;
}

function changeCardNField(obj)
{
	if(obj.value==-1)
		document.getElementsByName("patCardNo")[0].disabled=true;
	else
		document.getElementsByName("patCardNo")[0].disabled=false;
}

function validateAddressDtl()
{
	var valid=true;

	/*var patIdNo=document.getElementsByName("patIdNo")[0].value;
	var addtypeCode=document.getElementsByName("patAddTypeCode")[0].value;
	var patCatCode=document.getElementsByName("patPrimaryCatCode")[0].value;
	var patCatCodeEmployee=primaryCategoryEmployeeCode;
 	
 	//In the first case of validation if the patient is employee
 	
	 if( (patCatCode==patCatCodeEmployee) && ( (fieldEditableValue==fieldEditableFalse) || (dataFromTableValue==dataFromTableTrue) )  && (addtypeCode==isAddressCurrentTrue))
	 {
	 		if( validateDot(document.forms[0].patAddCityLoc,"Location") 
	 			//&& validateLocation()	
			  && validateDocumentAdded() 
			 && validateRequestDetail()
			  )
			  {
			  	valid=true;
			  }
			       
			 else
			 valid=false;  
	 }
	 else	//In the Second case of validation if the patient is a Non Employee
	 {
	 	var varPatAddTypeCode=document.getElementsByName("patAddTypeCode")[0].value; 
	// alert(">>>ADD TYPE>>>>"+varPatAddTypeCode);
		if(
			 validateAddressTypeCombo()
		&& isSelected(document.forms[0].patAddCountryCode,"Country")
		&& checkState()
		//&& validateLocation()	
		&& validateDot(document.forms[0].patAddCityLoc,"Location")
		&& validateDot(document.forms[0].patAddHNo,"House Number")
		&& validateDot(document.forms[0].patAddStreet,"Street")
		&& validateDot(document.forms[0].patAddDistrict,"District")
		&& isEmpty(document.forms[0].patAddCity,"City / Village")
		&& validateDot(document.forms[0].patAddCity,"City / Village")
	 	&& validateMinimumLength(document.forms[0].patAddPIN,'Pin Number','6')
	 	&& validatePinNumber(document.forms[0].patAddPIN)
	 	&& isEmpty(document.forms[0].patAddContactNo,"Contact No")
		&& isSelected(document.forms[0].patIsUrban,"Area Category")
		&& validateDocumentAdded()
		&& validateRequestDetail()
		
		)
		{
		  valid=true;
		}
	      
	  else
	  valid=false;     
 }
 //alert(valid);*/
	return valid;    
}

function validateRequestDetail()
{
	if(comboValidation(document.getElementsByName('requestBy')[0],'Request By') 
		&& requestByValidation() 
		&& validateRequestedByWhom())
	{
		return true;
	}
	else
	{
		return false;
	}
}

/*function requestByValidation()
{
	var valid=true;

	var patAge = parseInt(document.getElementsByName('patAge')[0].value)
	//--------var ageBeforeModification = parseInt(document.getElementsByName('beforeModificationAge')[0].value)
	var requestBy = document.getElementsByName('requestBy')[0].value;
	var mlcNo=document.getElementsByName('mlcNo')[0].value
	var patientIsMlc=document.getElementsByName('isMLC')[0].value
	var patientStatus=document.getElementsByName('patStatusCode')[0].value

	if(patientStatus==patientStatusCodeDead && requestBy==modificationRequestedBySelf)
	{
		alert("Dead Patient Cannot Request For Modification");
		return false;
	}
	else if(patAge<validAgeForRequestingModification && requestBy==modificationRequestedBySelf)
	//*--------else if(ageBeforeModification<validAgeForRequestingModification && requestBy==modificationRequestedBySelf)
	{
	  	alert("Patient Less Than "+validAgeForRequestingModification+" Years Cannot Ask For Modification")
		return false;
	}
	else
	{
		return true;
	}
}
*/
function validateRequestedByWhom()
{
	var valid=true;

	if(document.getElementsByName('requestBy')[0].value==modificationRequestedByPolice)
	{
		if(isEmpty(document.getElementsByName('requestByName')[0],'Requester Name')
			&& isEmpty(document.getElementsByName('requestByAddress')[0],'Requester Address')
			&& isEmpty(document.getElementsByName('constableDesig')[0],'Designation')
			&& isEmpty(document.getElementsByName('constableBadgeNo')[0],'Badge No.'))
			{
				valid= true
			}
			else
				valid= false
	}
	if(document.getElementsByName('requestBy')[0].value==modificationRequestedByRelative)
	{
		if(comboValidation(document.getElementsByName('requestRelation')[0],' Relationship ')
			&& isEmpty(document.getElementsByName('requestByName')[0],'Requester Name')
			&& isEmpty(document.getElementsByName('requestByAddress')[0],'Requester Address'))
			{
				valid= true
			}
			else
				valid= false
	}
	return valid;
}

function validateDocumentAdded()
{
	if(document.getElementsByName('verificationDocumentAdded')[0].value==1)
	{
		return true;
	}
	else
	{
		alert("Select A Verification Document");
		return false;
	}
}

function validatePatientDOBAgainstSysDate()
{
	if(document.getElementsByName('isActualDob')[1].checked)
	{
		//DateValidator.prototype.validateAgainstDateOnMode = function(_givenDate, _givenFormat, _mode, _dateName, _givenDateName)
		var ctrlObjPatDOB = document.forms[0].patDOB.Control_Object;
		//alert(ctrlObjPatDOB);
		var elemCurrentDate = document.getElementsByName('sysDate')[0];	
		//alert(elemCurrentDate.value);
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

function checkAgeOrDob()
{
	var valid=false;
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
	//alert(seniorCitizenCode);
	if(document.forms[0].patPrimaryCatCode.value==seniorCitizenCode)
	{
		//alert("validation for senior citizen");
		if(!validateForSeniorCitizen(parseInt(seniorCitizenAge)))	valid=false;
	}
	return valid;
}

function validateAddressTypeCombo()
{
	var valid=true;
	//alert("document.getElementsByName(addModify)[0].value"+document.getElementsByName("addModify")[0].value)
	//alert("document.getElementsByName(patAddTypeCode)[0].value"+document.getElementsByName("patAddTypeCode")[0].value)
	if(document.getElementsByName("addModify")[0].value==isAddressAdded)
	{
		valid = isSelected(document.getElementsByName("patAddTypeCode")[0],"Address Type");
	}
	else
	{
		//alert("inside else")
		document.getElementsByName("patAddTypeCode")[0].value = document.getElementsByName("patFBAddTypeCode")[0].value
		//alert("document.getElementsByName(patFBAddTypeCode)[0].value"+document.getElementsByName("patFBAddTypeCode")[0].value)
		//alert("patAddTypeCode"+document.getElementsByName("patAddTypeCode")[0].value)
	}
	return valid;
}

function checkState()
{
	var flag=false;
	var contryCode=document.getElementsByName("patAddCountryCode")[0].value;
	if(contryCode==registrationDeskDefaultCountryCode)
	{
		flag=isSelected(document.forms[0].patAddStateCode,"State");
	}
	else
	{
	 	//flag=isEmpty(document.forms[0].patAddStateName,"State Name") ;
		flag=true;
	}
	return flag;
}

function populate(selectedarray)
{
	var strHtml ="";
	var elem = document.getElementById("hiddenDivVerification");
	for(i=0; i<selectedarray.length; i++)
	{
		var arrayOfDocsData=selectedarray[i].split("|");
  	  
		strHtml+="<input type='hidden' name='arrSelectedVerifyDocs' value='"+selectedarray[i]+"'/>	";
		strHtml+=arrayOfDocsData[1]+"&nbsp; &nbsp;";
	}
	elem.innerHTML= ":: &nbsp;" + strHtml;
}  



function clearForm()
{
	document.getElementsByName("patFirstName")[0].value="";
	document.getElementsByName("patMiddleName")[0].value="";
	document.getElementsByName("patLastName")[0].value="";
	
	document.getElementsByName("isActualDob")[0].value="";
	document.getElementsByName("patAge")[0].value="";
	document.getElementsByName("patAgeUnit")[0].value="";
	document.getElementsByName("patDOB")[0].value=DateValidator.DATE_FORMAT;
	
	document.getElementsByName("patGenderCode")[0].value="-1";
	document.getElementsByName("patCasteCode")[0].value="-1";
	document.getElementsByName("patGuardianName")[0].value="";
	document.getElementsByName("patHusbandName")[0].value="";
	document.getElementsByName("patMotherName")[0].value="";
	document.getElementsByName("patNationalityCode")[0].value="-1";
	document.getElementsByName("patReligionCode")[0].value="-1";
	

	if(document.forms[0].patAddTypeCode && typeof document.forms[0].patAddTypeCode == "SELECT")
	{
		document.getElementsByName("patAddTypeCode")[0].value="-1"
	}	
	document.forms[0].strPatAddressTehsil.value="";
	document.forms[0].patAddCountryCode.value="-1";
	document.forms[0].patAddStateCode.value="-1";
	document.forms[0].patAddStateName.value="";
	document.forms[0].patAddCityLocCode.value="-1"
	document.forms[0].patAddCityLoc.value=""
	document.forms[0].patAddHNo.value=""
	document.forms[0].patAddStreet.value=""
	document.forms[0].patAddDistrictCode.value="-1"
	document.forms[0].patAddDistrict.value=""
	document.forms[0].patAddCity.value=""
	document.forms[0].patAddPIN.value=""
	document.forms[0].patIsUrban.value="-1"
	document.forms[0].patAddContactNo.value=""
	document.forms[0].patAddMobileNo.value=""
	document.forms[0].patAddFaxNo.value=""
	document.forms[0].patAddEmailId.value=""

	document.getElementsByName('requestBy')[0].value="-1"
	document.getElementsByName('requestRelation')[0].value="-1"
	document.getElementsByName('requestByName')[0].value=""
 	document.getElementsByName('requestByAddress')[0].value=""
 	document.getElementsByName('constableDesig')[0].value=""
 	document.getElementsByName('constableBadgeNo').value=""
	document.getElementsByName("verificationDocumentAdded")[0].value="0";
 	enableRelation();

	var str='<font color="red">:: &nbsp;Need To Provide Verification Documents</font><input type="hidden" name="arrSelectedVerifyDocs" value="" />';
	document.getElementById("hiddenDivVerification").innerHTML=str;
}











function  showdelhidiv()
{
	if(document.getElementsByName("isAddressDelhi")[0]!=null)
	{
		if(isAddressDelhi=='0')
		{
	 		document.getElementsByName("isAddressDelhi")[1].checked=true;
	 		document.getElementById("divpatAddCityLocCode").style.display="none"; 
		}
		else if(isAddressDelhi=='1')
		{
			document.getElementById("divpatAddCityLocCode").style.display="";   	
			document.getElementById("divpatAddCityLocation").style.display="none";

			document.getElementsByName("patAddStateCode")[0].disabled=true;   
			document.getElementsByName("patAddCountryCode")[0].disabled=true;
			document.getElementsByName("patAddStateCode")[0].value = registrationDeskDefaultStateCode;   
			document.getElementsByName("patAddCountryCode")[0].value = registrationDeskDefaultCountryCode;  
		}
	}




	
 	//<his:statusInProcessWithJsp>
 	document.getElementsByName("patFirstName")[0].focus();
 	if(isAddressDelhi==1)
 	{
	 document.getElementById("divpatAddCityLocCode").style.display="";   	
	 document.getElementById("divpatAddCityLocation").style.display="none";
	 //    document.getElementsByName("patAddStateCode")[0].disabled=true;   
      //  document.getElementsByName("patAddCountryCode")[0].disabled=true;
	 document.getElementsByName("patAddStateCode")[0].value=registrationDeskDefaultStateCode;   
       document.getElementsByName("patAddCountryCode")[0].value=registrationDeskDefaultCountryCode;
      } 
	
	 document.getElementsByName("patPrimaryCatCode")[0].disabled="true";
	 document.getElementsByName("patSecondaryCatCode")[0].disabled="true";
   // </his:statusInProcessWithJsp>
   
}

function validateLocation(){
		var contryCode=document.getElementsByName("patAddCountryCode")[0].value;
	    var elmt= document.getElementsByName("patAddStateCode")[0];    
		 //  alert("selected index value state combo"+elmt.options[elmt.selectedIndex].value);
		 //   alert("display style "+document.getElementsByName("patAddStateCode")[0].style.display)
		//  alert("display style state name"+document.getElementsByName("patAddStateName")[0].style.display)
	if(locationRequired==locationRequiredYes)
  {
 	 if(contryCode==registrationDeskDefaultCountryCode && elmt.options[elmt.selectedIndex].value==registrationDeskDefaultStateCode){ 	      
		  value=document.getElementsByName('patAddCityLocCode')[0].value;  				  				   			
		 // alert("selected index value location combo"+value);
			if(value=="-1"){
			 alert("Please Select the Location");
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


function ValidatePatientDtlModificationDesk()
{
	var valid=true;

	var addTypeCurrentTrue=isAddressCurrentTrue;

	//var patIdNo=document.getElementsByName("patIdNo")[0].value;
	var patCategoryCode=document.getElementsByName("patPrimaryCatCode")[0].value;

	var addtypeCode=document.getElementsByName("patAddTypeCode")[0].value;
	
	//In the first case of validation if the patient is employee
	if( (patCategoryCode==primaryCategoryEmployeeCode) && ( (fieldEditableValue==fieldEditableFalse) || (dataFromTableValue==dataFromTableTrue) )
	 //----- && (addtypeCode==isAddressCurrentTrue)
	)
	{
		if( validateDot(document.forms[0].patAddCityLoc,"Location")
			//&& validateLocation()
			//&& validateRequestDetail()
			//&& validateDocumentAdded()
			)
		{
			valid=true;
		}
		else
			valid=false;
	}
	else	//In the Second case of validation if the patient is a Non Employee
	{
		if( isEmpty(document.forms[0].patFirstName,"First Name")
			&& validateDot(document.forms[0].patFirstName,"First Name")
			&& validateSpecialCharacter('/',document.forms[0].patFirstName,"First Name")
			&& validateSpecialCharacter('-',document.forms[0].patFirstName,"First Name")
			&& validateDot(document.forms[0].patMiddleName,"Middle Name") 
			//&& isEmpty(document.forms[0].patLastName,"Last Name")
			&& validateDot(document.forms[0].patLastName,"Last Name") 
			//&& comboValidation(document.forms[0].patCasteCode,"Patient Caste")
			
			//&& isEmpty(document.getElementsByName("patIdNo")[0],"Patient Category Id")
			
			//&& isEmpty(document.forms[0].patGuardianName,"Father Name")
			&& validateFatherNameSpouseName()
			&& validateSpouseName()
			&& validateDot(document.forms[0].patGuardianName,"Father Name") 
			&& validateDot(document.forms[0].patHusbandName,"Spouse Name") 
			&& validateDot(document.forms[0].patMotherName,"Mother Name") 
			
			&& isValid(document.forms[0].patAge,"Age",125) 
			&& isEmpty(document.forms[0].patAgeUnit,"Age Unit")
			//&& validateDateAgainstSysDate(document.forms[0].patDOB,"Date of Birth") 
			&& validatePatientDOBAgainstSysDate()
			&& checkAgeOrDob()

			&& isSelected(document.forms[0].patGenderCode,"Gender")
			&& isSelected(document.forms[0].patNationalityCode,"Nationality")
			&& checkAnotherID()
			//&& isEmpty(document.forms[0].patReligionCode,"Religion")
			&& isSelected(document.forms[0].patPrimaryCatCode,"Patient Category")
			

			// if Address Modified
			&& ( !(document.forms[0].patAddCountryCode) 
				|| ( validateAddressTypeCombo()
					&& isSelected(document.forms[0].patAddCountryCode,"Country")
					&& checkState()
					//&& validateLocation()	
					&& validateDot(document.forms[0].patAddCityLoc,"Location")
					&& validateDot(document.forms[0].patAddHNo,"House Number")
					//&& validateDot(document.forms[0].patAddStreet,"Street")
					&& validateDot(document.forms[0].patAddDistrict,"District")
					&& isEmpty(document.forms[0].patAddCity,"City / Village")
					&& validateDot(document.forms[0].patAddCity,"City / Village")
				 	&& validateMinimumLength(document.forms[0].patAddPIN,'Pin Number','6')
				 	&& validatePinNumber(document.forms[0].patAddPIN)
				 	//&& isEmpty(document.forms[0].patAddContactNo,"Contact No")
					&& isSelected(document.forms[0].patIsUrban,"Area Category") 
					&& validateEmail(document.forms[0].patAddEmailId))
				)
			&& checkSingle()
			//&& validateDocumentAdded()
			//&& validateRequestDetail()
		
		)
		{
			valid=true;
		}
		else
			valid=false;
			
	}
	
	// Setting Date from format 'dd-mm-yyyy' format to 'dd-Mon-yyyy'
	var patPriCode = document.getElementsByName("patPrimaryCatCode")[0].value;
	if(valid==true //&& patPriCode!=primaryCategoryEmployeeCode
	)
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

	//alert(valid);
	return valid;
}



function checkDistrict(){
 	var flag=false;
 	var contryCode=document.getElementsByName("patAddCountryCode")[0].value;
	if(contryCode==registrationDeskDefaultCountryCode){
		flag=isSelected(document.forms[0].patAddDistrictCode,"District");
		// alert("flag "+flag)
	}
	else{
	   flag=isEmpty(document.forms[0].patAddDistrict,"District Name") ;
		// alert("flag "+flag)
		
	}
	
	return flag;
 }





function submitAddressDtl(mode,event){
	submitAddDtlHomeWork();
	if(validateAddressDtl()){
		submitTile(mode);
		return false;
	}
	else 
		return false;
}

function submitAddDtlHomeWork(){
// alert("check address type delhi..."+document.getElementsByName("isAddressDelhi")[0].value);
 // if((document.getElementsByName("isAddressDelhi")[0]!=null)){ 
  // document.getElementsByName("patAddStateCode")[0].disabled=false;   
  // document.getElementsByName("patAddCountryCode")[0].disabled=false;  
 // } 
}






 
 function  showdelhidetails()
  {

// alert("show delhi details");
		//   	 document.getElementsByName("isAddressDelhi")[0].checked=true;
		 	document.getElementById("divpatAddCityLocCode").style.display="";   	
			document.getElementById("divpatAddCityLocation").style.display="none";
			document.getElementsByName("patAddStateCode")[0].disabled=true;   
	     	document.getElementsByName("patAddCountryCode")[0].disabled=true;
		 	document.getElementsByName("patAddStateCode")[0].value=registrationDeskDefaultStateCode;   
	        document.getElementsByName("patAddCountryCode")[0].value=registrationDeskDefaultCountryCode;  
	  
 } 



function validateVerificationDocuments()
{
//alert("inside adress tile validation");

var valid=true;
//alert(document.getElementsByName('arrSelectedVerifyDocs')[0].value);
if(document.getElementsByName('arrSelectedVerifyDocs')){
valid=false;
alert("Show some Verification Document");
}
else
valid=true;

//alert(valid);
return valid;

}

function submitPage(mode)
{
	elmt=document.getElementsByName("hmode")[0];  
    elmt.value=mode;
    document.forms[0].submit();
}
function getDistrict()
{
	
	submitPage('GETDISTRICT');
}

function populateDetails()
{
	var locCode=document.getElementsByName("patAddCityLocCode")[0].value;
	//alert("locCode>>>>>"+locCode);
	if(locCode!="-1")
	{
		submitPage('LOCDETAIL');
	}
	else
	{
		alert("Select the Location");
	}
}

function submitPg(mode)
{
	elmt=document.getElementsByName("mode")[0];  
    elmt.value=mode;
    document.forms[0].submit();
}








function validateLocation(){
		var contryCode=document.getElementsByName("patAddCountryCode")[0].value;
	    var elmt= document.getElementsByName("patAddStateCode")[0];    
		 //  alert("selected index value state combo"+elmt.options[elmt.selectedIndex].value);
		 //   alert("display style "+document.getElementsByName("patAddStateCode")[0].style.display)
		//  alert("display style state name"+document.getElementsByName("patAddStateName")[0].style.display)
	if(locationRequired==locationRequiredYes)
  {
 	 if(contryCode==registrationDeskDefaultCountryCode && elmt.options[elmt.selectedIndex].value==registrationDeskDefaultStateCode){ 	      
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



function checkDistrict(){
 	var flag=false;
 	var contryCode=document.getElementsByName("patAddCountryCode")[0].value;
	if(contryCode==registrationDeskDefaultCountryCode){
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
 


function actionHusbandName()
{
if(document.getElementsByName("patGenderCode")[0].value==1)
	{
	document.getElementsByName("patHusbandName")[0].value="";
	document.getElementsByName("patHusbandName")[0].readOnly=true;
	}
	else
	{
	document.getElementsByName("patHusbandName")[0].readOnly=false;
	}
	
}


 


function populate1(e,d){ 
	document.getElementsByName('crNoToRetrieve')[0].value=e;
	submitForm("DGNDETAIL"); 
} 
function submit4image(){
	
 } 
 

 
 
 
 
 
 
 
 