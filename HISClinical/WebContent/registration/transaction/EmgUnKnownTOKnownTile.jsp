<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@page import="hisglobal.hisconfig.Config"%>
<%@page import="java.util.Date"%>
<%@page import="registration.controller.fb.EmgUnKnownTOKnownFB"%>
<his:css src="/hisglobal/css/calendar-blue2.css"/>
<his:javascript src="/registration/js/calendar.js"/>
<%@ page import ="registration.*" %>
<his:javascript src="/registration/js/registration.js"/>
<his:javascript src="/registration/js/validationCalls.js"/>
<his:javascript src="/registration/js/validationCommon.js"/>
<his:javascript src="/registration/js/stateChangeAjax.js" />
<his:javascript src="/registration/js/cityLocationAjax.js" />
<his:javascript src="/registration/js/commonFunctions.js"/>
<his:javascript src="/registration/js/popup.js"/>
<his:javascript src="/registration/js/dateFunctions.js" />
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<his:javascript src="/hisglobal/js/utilityFunctions.js" />
<his:javascript src="/hisglobal/utility/generictemplate/js/validationFunctions.js"/>
<his:javascript src="/hisglobal/utility/generictemplate/js/date_validator.js"/>

<%!
   String divdisplay="\"\"";
   String divnddisplay="\"\"";
   String divisrefdisplay="\"\"";
   String divRefGnctdHospitalCode="\"\"";
   String divRefHospname="\"\"";
   String strdivage="\"\"";
   String strdivdob="\"\"";
   String RefGnctdHosNameradio=""; 
   //String RefGnctdHoscoderadio="";    
%>

<script> 

function enableRelation()
{	
	 
	var obj=document.getElementsByName("requestBy")[0]
	if(obj.value=="1")
	{
			document.getElementsByName("requestRelation")[0].disabled=false;
			
	}
	else 
	{
			document.getElementsByName("requestRelation")[0].disabled=true;
	}
	if(obj.value=="1"){
		document.getElementById("policeDetailDiv").style.display="none";
		document.getElementById("requesterDIV").style.display="block";
		document.getElementById("requesterNo").style.display="block";
		
		//document.getElementById("designationLabel").style.display="block";
		//document.getElementById("designationControl").style.display="block";
		//document.getElementById("badgeNoLabel").style.display="block";
		//document.getElementById("badgeNoControl").style.display="block";
		document.getElementById("relationshipSelfId").style.display="none";	
		document.getElementById("relationshipRelativeId").style.display="block";
	}
	else if(obj.value=="2"){
	document.getElementById("policeDetailDiv").style.display="block";
	document.getElementById("requesterDIV").style.display="block";
	document.getElementById("requesterNo").style.display="none";
	
		//document.getElementById("designationLabel").style.display="block";
		//document.getElementById("designationControl").style.display="block";
		//document.getElementById("badgeNoLabel").style.display="block";
		//document.getElementById("badgeNoControl").style.display="block";
	}
	else {
	document.getElementById("policeDetailDiv").style.display="none";
	document.getElementById("requesterDIV").style.display="none";
	document.getElementById("requesterNo").style.display="none";
	
		//document.getElementById("designationLabel").style.display="none";
		//document.getElementById("designationControl").style.display="none";
		//document.getElementById("badgeNoLabel").style.display="none";
		//document.getElementById("badgeNoControl").style.display="none";
		document.getElementById("relationshipSelfId").style.display="block";	
		document.getElementById("relationshipRelativeId").style.display="none";
	}
	
}

function populate(selectedarray){
 // alert("length:::"+selectedarray.length);  
  	  strHtml ="";
  	  elem = document.getElementById("hiddenDivVerification");
  	  for(i=0; i<selectedarray.length; i++){
  	  	strHtml+="<input type='hidden' name='arrSelectedVerifyDocs' value='"+selectedarray[i]+"'/>	";
  	  	//strHtml+=selectedarray[i]+"&nbsp; &nbsp;";
  	  	strHtml+=selectedarray[i].split("|")[1]+"&nbsp; &nbsp;";
  	  }
      elem.innerHTML= strHtml;
	  //elem.style.display="";
    //   alert(elem.innerHTML);
      //document.getElementById("hiddenDivVerification").style.display="";
      document.getElementById('hiddenDivVerificationId').style.display="block";
      document.getElementById("patAddStreetId").colspan="2";
      document.getElementById("patAddPinId").colspan="2";
     // document.getElementById("saveButton1").style.display="none";
       
}   

function validateDocumentAdded()
 {
 	if(document.getElementsByName('verificationDocumentAdded')[0].value==1)
 	{
 		return true
 	}
 	else
 		{
 		alert("Select A Verification Document")
 		return false
 		}
 }

function ValidateUnknowntoknown(){
var valid=true;
 var client="<%=Config.CLIENT%>";   
	 var clientSms="<%=Config.CLIENT_SMS%>"; 
	 
 if(isEmptyUnknown(document.forms[0].patFirstName,"First Name") 
	 && validateDot(document.forms[0].patFirstName,"First Name")
	 && validateSpecialCharacter('/',document.forms[0].patFirstName,"First Name")
	 && validateSpecialCharacter('-',document.forms[0].patFirstName,"First Name")
	 //&& validateDot(document.forms[0].patMiddleName,"Middle Name")
	 //&& isEmpty(document.forms[0].patLastName,"Last Name")
	 && validateDot(document.forms[0].patLastName,"Last Name")
	 && validateDot(document.forms[0].patGuardianName,"Guardian Name")
	 && isValid(document.forms[0].patAge,"Age",125) 
	 && isEmpty(document.forms[0].patAgeUnit,"Age Unit") 
	 //&& isEmpty(document.forms[0].patDOB,"Date of Birth")  
	 && checkAgeOrDob() 
	&& validatePatientDOBAgainstSysDate()
	 && isSelected(document.forms[0].patGenderCode,"Gender")
	  && validateFatherNameSpouseName()
	 //&& isEmpty(document.forms[0].patGuardianName,"Father's Name")
	 && comboValidation(document.forms[0].patNationalityCode,"Nationality") 
	 && comboValidation(document.forms[0].patReligionCode,"Religion") 
	 //&& validateMonthlyIncome(document.getElementsByName("patMonthlyIncome")[0],'<%=RegistrationConfig.BPL_MONTHLY_INCOME%>','<%=Config.PRIMARY_CATEGORY_POOR_FREE_CODE%>')
	 //&& isEmptyUnknown(document.forms[0].patIdMark1,"Id Mark 1")
	 //&& isEmptyUnknown(document.forms[0].patIdMark2,"Id Mark 2")
	 && isSelected(document.forms[0].patAddCountryCode,"Country") 
	 && isSelected(document.forms[0].patAddStateCode,"State") 
	 && checkState()
	 //&& validateLocation()
	 && validateDot(document.forms[0].patAddCityLoc,"Location")
	 && validateDot(document.forms[0].patAddHNo,"House Number")
	 && validateDot(document.forms[0].patAddStreet,"Street")
	 && isEmpty(document.forms[0].patAddCity,"City / Village")
	 && validateDot(document.forms[0].patAddCity,"City / Village")
	 && validateMinimumLength(document.getElementById("pintext"),'Pin Number','6') 
	 && validatePinNumber(document.getElementById("pintext"))
	 //&& isEmpty(document.forms[0].patAddContactNo,"Contact No")
	 && validateRequestDetail()
	 && validateDocumentAdded() )
 
  {
      valid=true;
	}
	       
	  else
	  valid=false;     
	    
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

function enableSpouseField()
{
// alert("sdsdsdsdsdsds")
	var maritalStatus=document.getElementsByName("patMaritalStatusCode")[0].value;
	
	var maritalStatusSingle="<%=RegistrationConfig.REGISTRATIONDESK_MARITAL_STATUS_SINGLE%>";
	var clientValue="<%=Config.CLIENT%>";
	 var clientPGIMER="<%=Config.CLIENT_PGIMER%>";
	// alert("maritalStatus "+maritalStatus+" maritalStatusSingle  "+maritalStatusSingle)
	if( (maritalStatus=="-1") || (maritalStatus==maritalStatusSingle) )
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


function submitTile(mode){
doHomeWork();                // in case of unknown remove the disabled field
elem = document.getElementsByName('hmode')[0];
	elem.value = mode;
	//alert(elem.value);
	//alert("unknown on submit"+document.getElementsByName("isUnknown")[0].value);
	document.forms[0].submit();
}

function isEmptyUnknown(obj,name)

{

  	if(obj!=null)
	{
	     var value;
		 //alert(obj.name+ "" + obj.value);
		 
		
		if(obj.name=='patIdMark') {
		if(document.getElementsByName('patFirstName').value=='<%=RegistrationConfig.PATIENT_UNKNOWN%>')
		value=obj.value;
		else 
	 	value='~';
		}
		else{
 			value=obj.value;
			}
	//alert("valeue......."+value);
         
		if(value=="" ||value=='<%=RegistrationConfig.PATIENT_UNKNOWN%>')
			{

			alert("Please Enter the "+ name);

			obj.value=value;

			obj.focus();

			return false;

		}

		else
			//obj.value=value;
		return true;
	}
	return false;
}

function submitTile(mode){
//alert ("inside submitTile");
	elem = document.getElementsByName('hmode')[0];
	elem.value = mode;
	//alert(elem.value);
	document.forms[0].submit();
}
function checkForUnknownOnLoad(){
<logic:equal name="EmgUnKnownTOKnownFB" property="isUnknown" value="1">
	//alert("unknown true");
	document.getElementsByName("isUnknown")[0].checked.disabled=true;
		document.getElementsByName("patFirstName")[0].value="<%=RegistrationConfig.PATIENT_UNKNOWN%>";
		document.getElementsByName("patFirstName")[0].disabled=true;
		document.getElementsByName("patPrimaryCatCode")[0].value="<%=RegistrationConfig.REGISTRATIONDESK_UNKNOWN_PRIMARY_CATEGORY%>";
		document.getElementsByName("patSecondaryCatCode")[0].value="<%=RegistrationConfig.REGISTRATIONDESK_UNKNOWN_SECONDARY_CATEGORY%>";
		document.getElementsByName("patPrimaryCatCode")[0].disabled=true;
		document.getElementsByName("patSecondaryCatCode")[0].disabled=true;
 	    if( document.getElementsByName('isAddressDelhi')[0].checked){
 	       document.getElementsByName("patAddCityLocCode")[0].value="<%=RegistrationConfig.REGISTRATIONDESK_UNKNOWN_LOCATION_CODE%>";
 	       document.getElementsByName("patAddCityLocCode")[0].disabled=true;
 	       }
 	    else{
	    document.getElementsByName("patAddCityLoc")[0].value="<%=RegistrationConfig.PATIENT_UNKNOWN%>";
	    document.getElementsByName("patAddCityLoc")[0].disabled=true;
	    }	    		
	</logic:equal>
}

function  showdelhidivOnLoad()
  {

      /* <logic:equal name="EmgUnKnownTOKnownFB" property="isAddressDelhi" value="1">
	   document.getElementById("divpatAddCityLocCode").style.display="";   	  
	   document.getElementById("divpatAddCityLocation").style.display="none";
	    document.getElementsByName("patAddStateCode")[0].disabled=true;   
       document.getElementsByName("patAddCountryCode")[0].disabled=true;
	   document.getElementsByName("patAddStateCode")[0].value="<%=RegistrationConfig.REGISTRATIONDESK_DEFAULT_STATE_CODE %>";   
       document.getElementsByName("patAddCountryCode")[0].value="<%=RegistrationConfig.REGISTRATIONDESK_DEFAULT_COUNTRY_CODE%>"; 
       </logic:equal> */
       <his:statusInProcessWithJsp>
 	  <logic:equal name="EmgUnKnownTOKnownFB" property="isAddressDelhi" value="1">
	   document.getElementById("divpatAddCityLocCode").style.display="";   	  
	   document.getElementById("divpatAddCityLocation").style.display="none";
	   document.getElementsByName("patAddStateCode")[0].disabled=false;   
       document.getElementsByName("patAddCountryCode")[0].disabled=false;
	   document.getElementsByName("patAddStateCode")[0].value="<%=RegistrationConfig.REGISTRATIONDESK_DEFAULT_STATE_CODE %>";   
       document.getElementsByName("patAddCountryCode")[0].value="<%=RegistrationConfig.REGISTRATIONDESK_DEFAULT_COUNTRY_CODE%>"; 
	 </logic:equal>	     
	 
	 </his:statusInProcessWithJsp>
 }
 
 function  showdelhidiv()
  {

       <logic:equal name="EmgUnKnownTOKnownFB" property="isAddressDelhi" value="1">
	  // alert("showdelhidiv222222222");
    
	   document.getElementById("divpatAddCityLocCode").style.display="";   	  
       document.getElementsByName("isAddressDelhi")[0].checked="true";
	   document.getElementById("divpatAddCityLocation").style.display="none";
	   document.getElementsByName("patAddStateCode")[0].disabled=true;   
       document.getElementsByName("patAddCountryCode")[0].disabled=true;
	   document.getElementsByName("patAddStateCode")[0].value="<%=RegistrationConfig.REGISTRATIONDESK_DEFAULT_STATE_CODE %>";   
       document.getElementsByName("patAddCountryCode")[0].value="<%=RegistrationConfig.REGISTRATIONDESK_DEFAULT_COUNTRY_CODE%>";  
       </logic:equal>
     Isreferred(document.getElementsByName("isReferred")[0]);
 }
 function callThisOnload(){
	focusCrNo();
	showdelhidivOnLoad();
	showState();
	showLocation();
	enableRelation();
	// showdelhidiv();
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
	     flag=isEmpty(document.forms[0].patAddDistrict,"District Name") ;
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

function validateRequestDetail()
 {
  
 if(comboValidation(document.getElementsByName('requestBy')[0],'Request By') && 
 requestByValidation() &&
 validateRequestedByWhom())
	 {
	 	return true
	 }
 else
	 {
	 	return false
	 }
 }
 
 function requestByValidation()
 {
 	  var valid=true
  var ageBeforeModification=parseInt(document.getElementsByName('beforeModificationAge')[0].value)
  var validge=<%=Integer.parseInt(RegistrationConfig.VALID_AGE_FOR_REQUESTING_MODIFICATION)%>
  var requestBy=document.getElementsByName('requestBy')[0].value
  var requestBySelf=<%=RegistrationConfig.MODIFICATION_REQUESTED_BY_SELF%>
  var requestByRelative=<%=RegistrationConfig.MODIFICATION_REQUESTED_BY_RELATIVE%>
  var requestByPolice=<%=RegistrationConfig.MODIFICATION_REQUESTED_BY_POLICE%>
  var mlcNo=document.getElementsByName('mlcNo')[0].value
  var patientIsMlc=document.getElementsByName('isMLC')[0].value
  var mlcTrue='<%=RegistrationConfig.IS_MLC_TRUE%>'
  var patientStatus=document.getElementsByName('patStatusCode')[0].value
  var patStatusDead=<%=RegistrationConfig.PATIENT_STATUS_CODE_DEAD%>
  
  if(patientStatus==patStatusDead && requestBy==requestBySelf)
  {
  	alert("Dead Patient Cannot Request For Modification");
  	return false
  }
  else if(ageBeforeModification<validge && requestBy==requestBySelf)
  {
  	alert("Patient Less Than "+validge+" Years Cannot Ask For Modification")
  	return false
  }
  else
  {
  	return true
  }
 }
 
 function validateRequestedByWhom()
 {
 
 var valid=true
 
 	if(document.getElementsByName('requestBy')[0].value==<%=RegistrationConfig.MODIFICATION_REQUESTED_BY_POLICE%>)
 	{
 		if(	isEmpty(document.getElementsByName('requestByName')[0],'Requester Name') &&
 			isEmpty(document.getElementsByName('requestByAddress')[0],'Requester Address') &&
 			isEmpty(document.getElementsByName('constableDesig')[0],'Designation') &&
 			isEmpty(document.getElementsByName('constableBadgeNo')[0],'Badge No.'))
 			{
 				valid= true
 			}else
 			{
 				valid= false
 			}
 	}
 	if(document.getElementsByName('requestBy')[0].value==<%=RegistrationConfig.MODIFICATION_REQUESTED_BY_RELATIVE%>)
 	{
 		if(	comboValidation(document.getElementsByName('requestRelation')[0],' Relationship ') &&
 			isEmpty(document.getElementsByName('requestByName')[0],'Requester Name') &&
 			isEmpty(document.getElementsByName('requestByAddress')[0],'Requester Address') &&
 			isEmpty(document.getElementsByName('requestByContactNo')[0],'Requester Contact No.'))
 			{
 				valid= true
 			}else
 			{
 				valid= false
 			}
 	}
 	return valid
 }
 

</script>

<%
String st= (String)session.getAttribute("SYSDATE");
System.out.println("date in jsp"+st);
System.out.println("inside unknowntoknownTile"+st);
//String systemDate=hisglobal.presentation.WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy HH:mm");
%>
<%
//boolean varIsNewStatus=false;	
String varStatus="";
%>
<his:statusNew>
<%
//varIsNewStatus=true;
varStatus="New";%>	
</his:statusNew>
 <bean:define id="clientFlag" name="<%=Config.CLIENT_NAME%>"></bean:define>
<%  String msg=""; %>
 <his:TitleTag>
    <his:name>
         <bean:message key="emgUnknownTOKnownConversion"/>
     </his:name>
        <b> <font size="2"	face="Verdana, Arial, Helvetica, sans-serif">
       
             </font>        </b>
           
 </his:TitleTag>

<his:InputCrNoTag name="EmgUnKnownTOKnownFB"> </his:InputCrNoTag> 
 <his:statusTransactionInProcess>
<his:ContentTag>
 <table width="100%" cellspacing="1" cellpadding="0">
        
      <tr> 
        <td width="14%" height="25" nowrap  class="tdfonthead"> <div align="right"><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"><bean:message key="patient"/>
         <bean:message key="name"/></font></div></td>
        <td width="17%" class="tdfonthead"><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
        <bean:message key="first"/>
        </font>
        <font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font></td>
        <td width = "18%" class="tdfonthead" >
        <div align="left">
        <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
       	 <bean:message key="middle"/>
        </font>
        </div>
        </td>
        
        	
		<td width="19%" class="tdfonthead">
			<div align="left"><font color="#000000" size="2"
			face="Verdana, Arial, Helvetica, sans-serif">
			<bean:message key="last" /> </font></div>
		</td>
			
				
        <td width="17%" class="tdfonthead"></td>
        <td width="17%" class="tdfont"></td>
        </tr>
        
        <tr>
        <td class ="tdfonthead" nowrap>
        <div align="right">
        </div>
        </td>
        
        <td width="17%"	class="tdfont">
        <html:text name="EmgUnKnownTOKnownFB" property="patFirstName" tabindex="1" styleClass="textbox"  maxlength ="30" onblur="isAlpha(this,'First Name')" onkeypress="return validateAlphabetsWithDotsAndForwardSlashWithHypen(event,this)"/>
        </td>
        
        <td width="18%" class="tdfonthead">
        <div align="left">
        	<html:text name="EmgUnKnownTOKnownFB" property="patMiddleName" tabindex="1" styleClass="textbox" onblur="isAlpha(this,'Middle Name')" maxlength ="20" onkeypress="return validateAlphabetsWithDotsOnly(event,this)"/>
        </div>
        </td>
        <td class="tdfont">
        <html:text  name="EmgUnKnownTOKnownFB" property="patLastName" tabindex="1" styleClass="textbox" maxlength ="30" onblur="isAlpha(this,'Last Name')" onkeypress="return validateAlphabetsWithDotsOnly(event,this)"/>
        </td>
        
				<td width="14%" class="tdfonthead">
				<div id="nicklable" align="right"><font size="2" color="#000000"
					face="Verdana, Arial, Helvetica, sans-serif"> 
					<!--  
					<bean:message key="nickName" /> 
					--></font></div>
				</td>
				
				
				<td width="17%" class="tdfont">
				<div id="nickNameControl">
				<!--  
				<html:text name="EmgUnKnownTOKnownFB"
					property="patNickName" tabindex="1" styleClass="textbox"
					maxlength="30" onchange="isAlpha(this,'Nick Name')"
					onkeypress="return validateAlphabetsWithDotsOnly(event,this)" />
					-->
				</div>
				</td>
				
    	
        </tr>
        
        <tr> 
        
            <td width="19%"  nowrap="nowrap"  class="tdfonthead" > 
		 <div align="center">
     	<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	     <font color="#FF0000">*</font></font>
	      <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	     <bean:message key="age"/>
	     </font>
      	 <html:radio name="EmgUnKnownTOKnownFB" property="isActualDob" value="0" onclick="ageSelection()"/>    
   
         <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
         <bean:message key="dob"/>
         </font>
         <html:radio name="EmgUnKnownTOKnownFB" property="isActualDob" value="1" onclick="ageSelection()"/>
         </div>
         </td>
   
         <logic:empty name="EmgUnKnownTOKnownFB" property="isActualDob">
         <%
               strdivage="none";
               strdivdob="none";                
         %>
     	</logic:empty>
          
        <logic:equal name="EmgUnKnownTOKnownFB" property="isActualDob" value="1">    
            <%
     		 
             strdivage="none";
             strdivdob="";           
          %>               
       </logic:equal> 
       
       <logic:equal name="EmgUnKnownTOKnownFB" property="isActualDob" value="0">
         <%
           System.out.println("inside case 0isActualDob:::::::::");	 
           strdivage="";
           strdivdob="none";           
          %>               
       </logic:equal>                    
     
       <td width="19%" align="left" class="tdfont"  nowrap="nowrap"  >
       <div align="left" id="divAge" style='display:<%=strdivage%>'>
       <html:text  name="EmgUnKnownTOKnownFB" size="4" property="patAge" tabindex="1" maxlength ="3" onkeypress="return validateNumeric(event,this,false)"/> 
       <html:select name="EmgUnKnownTOKnownFB" property="patAgeUnit" tabindex="1" styleClass="smallcombo" >
       <html:options collection = "<%=RegistrationConfig.ESSENTIALBO_OPTION_AGE_TYPE %>" property = "value" labelProperty = "label"/>
       </html:select>
       </div>                
       
       <div align="left" id="divDob" style='display:<%=strdivdob%>'>         
       <bean:define name="EmgUnKnownTOKnownFB" property="patDOB" id="dob" type="java.lang.String"/>               

		<div id="divPatDOB"></div>
		<script type="text/javascript">DateValidator.setup("divPatDOB","patDOB","<%=dob%>","dd-Mon-yyyy","textbox");</script>
							
		<!--<his:date name='<%="patDOB"%>' dateFormate="%d-%b-%Y" value='<%=dob%>' />-->

       </div>
       </td>     
        
        <td width="14%" class="tdfonthead" >
        <div align="right">
        <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
        <bean:message key="gender"/>
        <font color="#FF0000">*</font>
        </font>
        </div>
        </td>
        
        <td width="17%" class="tdfont" >
        <html:select name="EmgUnKnownTOKnownFB"  property="patGenderCode" tabindex="1" styleClass="regcbo">
        <html:option value="-1">Select Value</html:option>
	    <html:options collection = "<%=RegistrationConfig.ESSENTIALBO_OPTION_GENDER %>" property  = "value" labelProperty = "label"/>
		</html:select>
        </td>
        
          <td width="18%" class="tdfonthead">
        <div align="right">
        <!-- <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
        
        <bean:message key="category"/>
        </font> -->
        </div>
        </td>
        
        <td class="tdfont">
        <!--<html:select name="EmgUnKnownTOKnownFB" property="patMaritalStatusCode" tabindex="1" styleClass ="regcbo" onchange="enableSpouseField(this);" >
        <html:option value="">Select Value</html:option>
		<html:options collection = "<%=RegistrationConfig.ESSENTIALBO_OPTION_MARITAL_STATUS %>" property = "value" labelProperty = "label"/>
		   <%System.out.println("before1");%>
         </html:select>-->
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
        <html:text name="EmgUnKnownTOKnownFB" property="patGuardianName" tabindex="1" styleClass="textbox" onblur="isAlpha(this,'Middle Name')" maxlength ="60" onkeypress="return validateAlphabetsWithDotsOnly(event,this)"/>
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
         <html:text name="EmgUnKnownTOKnownFB" property="patHusbandName" styleClass="textbox" tabindex="1" maxlength="60" onkeypress="return validateAlphabetsWithDotsOnly(event,this)"/>
        </td>      
       
        <td width="17%" class="tdfonthead" nowrap>
				<div align="right"><font color="#000000" size="2"
					face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
					key="motherName" /></font></div>
				</td>
		<td width="17%" class="tdfont"><html:text name="EmgUnKnownTOKnownFB"
			property="patMotherName" styleClass="textbox" tabindex="1"
			maxlength="60" onchange="isAlpha(this,'Mother Name')"
			onkeypress="return validateAlphabetsWithDotsOnly(event,this)" />
		</td>
					
          </tr>  
       
	   <tr>  
	   
	     
       <td class="tdfonthead" width="14%">
       <div align="right">
       <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
       <bean:message key="nationality"/>
       <font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font>
       </font>
       </div>
       </td>
       
       <td  class="tdfont" width="17%">   
 	   <div id=patCountryCombo>
       <html:select name="EmgUnKnownTOKnownFB" property="patNationalityCode" tabindex="1" styleClass="regcbo" >
	   <html:option value="-1">Select Value</html:option>
	   <html:options collection ="<%=RegistrationConfig.ESSENTIALBO_OPTION_NATIONALITY %>"property = "value" labelProperty = "label"/>
       </html:select>	
       </div>
       </td>  
       
	   
	     <td width="14%" class="tdfonthead" >
       <div align="right">
       <font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font>
       <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
       <bean:message key="religion"/>
       </font>
       </div>
       </td>
       
       <td width="17%" class ="tdfont" >
       <html:select name="EmgUnKnownTOKnownFB" property="patReligionCode" tabindex="1" styleClass="regcbo">
       <html:option value="">Select Value</html:option>
       <html:options collection = "<%=RegistrationConfig.ESSENTIALBO_OPTION_RELIGION %>" property = "value" labelProperty = "label"/>
       </html:select>
       </td>
     
      	  <td width="18%" class="tdfonthead">
        <div align="right">
        <!-- <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
        <bean:message key="monthlyIncome"/>
        </font> -->
        </div>
        </td>
        
        <td class="tdfont" width="18%">
       		<!--  <html:text name="EmgUnKnownTOKnownFB" property="patMonthlyIncome" styleClass="textbox" tabindex="1" 
       			maxlength="11" onkeypress="return(currencyFormat(this,',','.',event))"/>  -->
        </td>
	       	
	  
	   </tr> 
	   
	    <%-- 
			<tr id="occupationdetail" >
				<td width="17%" class="tdfonthead" nowrap="nowrap" >
				<div align="right"><font color="#000000" size="2"
					face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
					key="patOccupation" /> </font></div>
				</td>
				<td width="17%" class="tdfont" nowrap>  
					<html:select name="EmgUnKnownTOKnownFB" tabindex="1" property="patOccupation"  styleClass="regcbo" >
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
					<html:select name="EmgUnKnownTOKnownFB" tabindex="1" property="patFatherOccupation"  styleClass="regcbo" >
		            <html:option value="">Select Value</html:option>
				  	<html:options collection ="<%=RegistrationConfig.ESSENTIALBO_OPTION_OCCUPATION_DTL %>"property = "value" labelProperty = "label"/>
                    </html:select>
				</td>
				<td width="17%" class="tdfonthead" nowrap="nowrap" >
				<div align="right"><font color="#000000" size="2"
					face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
					key="patHusbandOccupation" /> </font></div>
				</td>
				<%System.out.println("before dept22223");

				%>

				<td width="17%" class="tdfont" nowrap="nowrap" >
					<html:select name="EmgUnKnownTOKnownFB" tabindex="1" property="patHusbandOccupation"  styleClass="regcbo" >
		            <html:option value="">Select Value</html:option>
				  	<html:options collection ="<%=RegistrationConfig.ESSENTIALBO_OPTION_OCCUPATION_DTL %>"property = "value" labelProperty = "label"/>
                    </html:select>
				</td>
				<td class="tdfonthead" width="17%"></td>
				<td class="tdfont" width="17%"></td>
			</tr>
		 --%>	
		  
       <tr>  
        <td width="18%" class="tdfonthead" >
        <div align="right">
        <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	    <bean:message key="idMark1"/>
	    <logic:equal name="EmgUnKnownTOKnownFB" property="isUnknown" value="1">
<!--	    <font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font>-->
	    </logic:equal>
	    </font>
	    </div>
	    </td>

         <td width="17%" colspan="2"  class="tdfont" > 
         <html:text  name="EmgUnKnownTOKnownFB"  size="40" maxlength="100"  property="patIdMark1" tabindex="1" onkeypress="return validateAlphabetsOnly(event,this)"/>
		 </td>
		 
		 <td width="18%" class="tdfonthead" >
        <div align="right">
        <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	    <bean:message key="idMark2"/>
	    <logic:equal name="EmgUnKnownTOKnownFB" property="isUnknown" value="1">
<!--	    <font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font>-->
	    </logic:equal>
	    </font>
	    </div>
	    </td>

         <td width="17%" colspan="2"  class="tdfont" > 
         <html:text  name="EmgUnKnownTOKnownFB"  size="40" maxlength="100" property="patIdMark2" tabindex="1" onkeypress="return validateAlphabetsWithDotsOnly(event,this)"/>
		 </td>
        
      </tr>       
       
     <tr>
				<td width="17%" class="tdfonthead">
				<div align="right"><font color="#000000" size="2"
					face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
					key="uniqueId" /> </font></div>
				</td>
				<td width="17%" class="tdfont" nowrap="nowrap"><html:text
					name="EmgUnKnownTOKnownFB" property="patNationalId"
					styleClass="textbox" maxlength="16" tabindex="1"
					onkeypress="return validateAlphaNumericWithSpecialCharacterOnly(event,this)"
					styleClass="textbox" /></td>       
				
	       
				<td width="17%" class="tdfonthead" nowrap="nowrap" >
					<div align="right"><font color="#000000" size="2"
						face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
						key="patCardNo" /> </font></div>
				</td>
				
				<td width="17%" class="tdfont" nowrap="nowrap"><html:text
					name="EmgUnKnownTOKnownFB" property="patCardNo"
					styleClass="textbox" maxlength="30" tabindex="1" 
					onkeypress="return validateAlphaNumericOnly(event,this)"
					 /></td>
			
         
	      <td width="17%" class="tdfonthead" nowrap="nowrap" >
          </td>
          	<td width="17%" class="tdfont" nowrap>
          	</td> 
		</tr>
       


    </table>
   </his:ContentTag>
    <%varStatus="InProcess";%>
    
     
  

   		   <his:SubTitleTagBroad name="Address Detail">
		   <table width="100%">
		   <tr>
             <td>	  
             		  <font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font>
                      <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
                      <bean:message key="country"/></font>
             </td>       
 			 <td>
 			 	       <html:select name="EmgUnKnownTOKnownFB" tabindex="1" property="patAddCountryCode"  styleClass="regcbo" onchange="if(this.value!='-1') showState()">
		                  	<html:option value="">Select Value</html:option>
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
                     <html:select name="EmgUnKnownTOKnownFB" tabindex="1" property="patAddStateCode" styleClass="regcbo" onchange="if(this.value!='-1') sendDataForStateChange(this)" >
                       <html:option value="-1">Select Value</html:option>
                       <html:options collection = "<%=RegistrationConfig.ESSENTIALBO_OPTION_STATE %>" property = "value" labelProperty = "label"/>
                     </html:select>    
                     </div> 
                     <div id="divpatAddStateCodeText" style="" >
                     <html:text name="EmgUnKnownTOKnownFB" tabindex="1" property="patAddStateName" styleClass="regcbo" onkeypress="return validateAlphabetsWithDotsOnly(event,this)">
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
                <html:text  name="EmgUnKnownTOKnownFB" onkeypress="return validateAlphaNumericWithDotsOnly(event,this);" property="patAddHNo" tabindex="1" maxlength="15" styleClass="textbox"/>
                </td>
               
	            <td  class="tdfonthead" width="18%">                    
	            <div align="right">
	            
	            <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	            <bean:message key="street"/>  
	            </font>
	            </div>                    
	            </td>
	                     
	            <td class="tdfont" width="17%" id="patAddStreetId" >
	            <html:text  name="EmgUnKnownTOKnownFB" property="patAddStreet" onkeypress="return validateAlphaNumericWithDotsOnly(event,this);" tabindex="1" maxlength="30" styleClass ="textbox"/> 
	            </td>
                  
             <td  class="tdfonthead" width="17%">
	            <div align="right">
	            <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	            <bean:message key="location"/>
	            </font>
	            </div>
             </td>
 
 
     		  <td  class="tdfont" width="17%">
       
          
                <div id="divpatAddCityLocCode" >                
	            <html:select name="EmgUnKnownTOKnownFB" tabindex="1" styleClass="regcbo" property="patAddCityLocCode" onchange="if(this.value!='-1') sendDataForCityLocation(this)">
				<html:option value="-1">Select Value</html:option>
				<html:options collection ="<%=RegistrationConfig.ESSENTIALBO_OPTION_LOCATION%>" property = "value" labelProperty = "label"/>
	            </html:select>
	            </div>	                    	                    	                    	                    	                    	                    	                    
	      
	            <div id="divpatAddCityLocation" >	                     
				<html:text  name="EmgUnKnownTOKnownFB" tabindex="1"  maxlength="50" onkeypress="return validateAlphaNumericWithDotsOnly(event,this);" styleClass="textbox" property="patAddCityLoc"/>
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
                <html:select name="EmgUnKnownTOKnownFB" tabindex="1" styleClass="regcbo" property="patAddDistrictCode">
				<html:option value="-1">Select Value</html:option>
				<html:options collection ="<%=RegistrationConfig.ESSENTIAL_BO_OPTION_DISTRICT_LIST_STATEWISE%>" property = "value" labelProperty = "label"/>
	            </html:select>
                </div>
                <div id="districtTextBox" style="display:none">
                <html:text  name="EmgUnKnownTOKnownFB" tabindex="1" property="patAddDistrict" onchange="isAlpha(this,'District')"  onkeypress="return validateAlphabetsWithDotsOnly(event,this)" maxlength="30" styleClass="textbox"/>
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
	            <html:text name="EmgUnKnownTOKnownFB" tabindex="1" property="patAddCity" onchange="isAlpha(this,'City')" onkeypress="return validateAlphabetsWithDotsOnly(event,this);" maxlength="30" styleClass="textbox"/> 
                </td>
                
                <td class="tdfonthead" width="14%">
	            <div align="right">
	            <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	            <bean:message key="pin"/>
	            </font> 
	            </div>
                </td>       
                    
                <td id="patAddPinId"  class="tdfont" width="17%" colspan="2" >
                <input type="text" id="pintext"  name="patAddPIN" tabindex="1"
                 size="17"  maxlength ="6"  onkeypress="return validateNumeric(event)"  /> 
                  </td>
                                                    
                    
              </tr>
                    
              <tr>
                      
                    <td  class="tdfonthead"  width="17%" >
                    <div align="right" > 
                    <font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font>
                    <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
                    <bean:message key="contactNo"/>
                    </font>
                    </div>
                    </td>
             		
                    <td  class="tdfont" width="17%">   
 		            <div >
         		    <html:text name="EmgUnKnownTOKnownFB" tabindex="1" property="patAddContactNo"  maxlength ="30" styleClass="textbox" onkeypress="return validateNumeric(event)"/> 
                    </div>
                    </td>
                    
						<td width="17%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<bean:message key="tehsil" />
								</font>
							</div>
						</td>
						<td width="17%" class="tdfont">
							<html:text name="EmgUnKnownTOKnownFB" tabindex="1" property="strPatAddressTehsil" maxlength="30"
								styleClass="textbox" onkeypress="return validateAlphabetsOnly(event,this)" />
						</td>

             <td width="17%" class="tdfonthead">
				<div align="right" ><font color="#000000" size="2"
					face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
					key="areaCategory" /> </font></div>
				</td>

				<td width="17%" class="tdfont">
				<div>
				<html:select
					name="EmgUnKnownTOKnownFB" property="patIsUrban" tabindex="1"
					styleClass="regcbo">
					<html:option value="">Select Value</html:option>
					<html:options
						collection="<%= RegistrationConfig.ESSENTIALBO_OPTION_AREA_CATEGORY %>"
						property="value" labelProperty="label" />
				</html:select>
				</div>
				</td> 
                  </tr>
                  <tr>		
             	<td class="tdfonthead" width="17%"><div align="right"><font color="#000000" size="2"
								face="Verdana, Arial, Helvetica, sans-serif"> <font
								color="#FF0000" size="1"
								face="Verdana, Arial, Helvetica, sans-serif">*</font> <bean:message	key="verificationDocument" /> </font></div>
							</td>
              		<td class="tdfont" width="17%"><div align="left"><img class="button"
								src='<his:path src="/hisglobal/images/icon-vrf.png"/>'
								style=cursor:pointer alt="Verification Documents"
								title="Verification Documents"
								onclick="openPopup('<his:path src="/registration/verificationDocumentPopup.cnt"/>',event,300,600);"
								onkeypress="if(event.keyCode==13) {openPopup('<his:path src="/registration/verificationDocumentPopup.cnt"/>',event,300,600)}"
								size='7' tabindex="1"></div></td>
              		<td width="17%" class="tdfont" ></td>
              		<td width="17%" class="tdfont" ></td>
              		<td width="17%" class="tdfont" ></td>
              		<td width="16%" class="tdfont" ></td>
                  </tr>		
                  
                </table>
                <div id="hiddenDivVerificationId" style="display: none;">
	                 <table width="100%" cellspacing="1" cellpadding="0" >
	                 	<tr >
	                		<td class="tdfont" width="100%" >
	                			<div id='hiddenDivVerification'>
	                			</div>
	                		</td>
                	</tr>
                
                	 
   				</table> </div>	
   				
 </his:ContentTag>
 
  <his:SubTitleTag name="Modification Requested By">
 
 </his:SubTitleTag>
 <his:ContentTag>
       <table width="100%" cellspacing="1" cellpadding="0">
       <tr>       
        <td width="25%" class="tdfonthead">
      	 <div align="right" >
      	  <font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font>
	        <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	          <bean:message key="requestBy"/>      
	        </font>
	       </div>
        </td>        
        <td width="25%" class="tdfont" >
        <div align="left" >
        	<html:select name="EmgUnKnownTOKnownFB" property="requestBy" styleClass="regcbo" onchange="enableRelation()">
        		<html:option value="-1">Select Value</html:option>
        		<html:option value="<%=RegistrationConfig.MODIFICATION_REQUESTED_BY_SELF %>">Self</html:option>
        		<html:option value="<%=RegistrationConfig.MODIFICATION_REQUESTED_BY_RELATIVE %>">Relative</html:option>
        		<%String mlcNo=((EmgUnKnownTOKnownFB)pageContext.findAttribute("EmgUnKnownTOKnownFB")).getMlcNo();
        		if(mlcNo!=null && !mlcNo.equals(""))
        		{%>
        		<html:option value="<%=RegistrationConfig.MODIFICATION_REQUESTED_BY_POLICE%>">Police</html:option>
        		<%} %>
        	</html:select>
        </div>
        </td>
       <td width="25%"  class="tdfonthead" >
        <div align="right" id="relationshipSelfId">        
       <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
       <bean:message key="realtionship"/>
       </font>
       </div>
       
       <div align="right" id="relationshipRelativeId">
       <font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font>
       <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
       <bean:message key="realtionship"/>
       </font>
       </div>
       </td>
       
       <td width="25%" class="tdfont"  >
       <html:select name="EmgUnKnownTOKnownFB" property="requestRelation" tabindex="1" styleClass="regcbo">
       <html:option value="-1">Select Value</html:option>
	   <html:options collection = "<%=RegistrationConfig.ESSENTIALBO_OPTION_RELATION_DTL %>" property  = "value" labelProperty = "label"/>
	   </html:select>
       </td>
       
      </tr>
     </table>
     <div id="requesterDIV">
     <table width="100%" cellspacing="1" cellpadding="0">
      <tr>
       <td width="25%" class="tdfonthead"><!-- 16 -->
       <div align="right" >
        <font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font>
	        <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	          <bean:message key="requestName"/>      
	        </font>
	     </div>   
        </td>        
        <td width="25%" class="tdfont" > <!-- 18 -->
             <html:text name="EmgUnKnownTOKnownFB" tabindex="1" property="requestByName" onkeypress="return validateAlphabetsWithDotsOnly(event,this)" styleClass="textbox"  maxlength="60"/>
        </td>
         
       <td width="25%" class="tdfonthead">
       <div align="right" >
       <font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font>
          	<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
             <bean:message key="requestByAddress"/>
       		 </font>
       	</div>	 
       	</td>
      
       	<td width="25%"  class="tdfont"><!-- 3 -->
            <html:textarea name="EmgUnKnownTOKnownFB" property="requestByAddress"   onkeypress="return CheckMaxLength(event,this,100,1)"  tabindex="1" 
            style="width: 180px;font-family: Verdana, Arial, Helvetica, sans-serif;font-size: 12;height:40px;color: #000000;"/>
        </td>
        
      </tr>
      </table>
     
      <div align="right" id="requesterNo">
       <table width="100%" cellspacing="1" cellpadding="0">
       <tr>
       <td width="25%" class="tdfonthead"><!-- 16 -->
       <div align="right" >
        <font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font>
	        <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	          <bean:message key="requestByContact"/>      
	        </font>
	     </div>   
        </td>        
        <td width="25%" class="tdfont" > <!-- 18 -->
             <html:text name="EmgUnKnownTOKnownFB" tabindex="1" property="requestByContactNo" onkeypress="return validateNumeric(event)" styleClass="textbox"  maxlength="30"/>
        </td>
         
       <td width="25%" class="tdfonthead">
       </td>
      
       	<td width="25%"  class="tdfont"><!-- 3 -->
        </td>        
      </tr>
      </table>
      </div>
      <div id="policeDetailDiv" >
	      <table width="100%" cellspacing="1" cellpadding="0">
		     <tr >
		      	<td width="25%" class="tdfonthead" >
		      	<div align="right" >
		      	<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font>
		      		 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				       <bean:message key="designation"/>
				      </font>
				 </div>     
		      	</td>
		      	<td  width="25%" class="tdfont">
		      		<html:text name="EmgUnKnownTOKnownFB" property="constableDesig" styleClass="textbox" maxlength="50" onkeypress="return validateAlphaNumericOnly(event,this)"></html:text>
		      	</td>
		      	<td   width="25%" class="tdfonthead" >
		      	<div align="right" >
		      	<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font>
		      		 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				       <bean:message key="batchNo"/>
				      </font>
				 </div>     
		      	</td>
		      	<td  width="25%" class="tdfont" >
		      		<html:text name="EmgUnKnownTOKnownFB" property="constableBadgeNo" styleClass="textbox" maxlength="50" onkeypress="return validateAlphaNumericOnly(event,this)"></html:text>
		      	</td>
		      </tr>
	      
	      </table>
      </div>
	      
		      
      
      
      

</his:ContentTag>
 
   </his:statusTransactionInProcess>

    
     <%if(varStatus.equals("InProcess")){%>
     
<his:ButtonToolBarTag  >
          <%--  <div align="center" id="saveButton1" >
         
          <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' tabindex="1"  style=cursor:pointer onkeypress="if(event.keyCode==13) submitPage('CANCEL');" tabindex="1" onclick ="submitPage('CANCEL');">
          <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style=cursor:pointer tabindex="1" onclick ="submitForm('NEW')" onkeypress="if(event.keyCode==13) submitForm('NEW');">
          </div>
          --%>
     <table width=100%>
         <tr>
			<td width='45%' align='right'>
			<div id='saveButton'>
          		<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>'  style="cursor:pointer;" onkeypress="if(event.keyCode==13) submitFormOnValidate(ValidateUnknowntoknown(),'SAVE');" tabindex="1" onclick ="submitFormOnValidate(ValidateUnknowntoknown(),'SAVE');" >
          	</div>
			</td>
	        <td width='55%' align='left'><img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' tabindex="1"  style=cursor:pointer onkeypress="if(event.keyCode==13) submitPage('CANCEL');" tabindex="1" onclick ="submitPage('CANCEL');">
	           <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style=cursor:pointer tabindex="1" onclick ="submitForm('NEW')" onkeypress="if(event.keyCode==13) submitForm('NEW');">
          </td>
        </tr>
       </table>
      </his:ButtonToolBarTag>
     
       <% 
       } else{ 
      
       %> 
	  <his:ButtonToolBarTag>
	   <div align="center" >	     
          <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' tabindex="1"  style=cursor:pointer onkeypress="if(event.keyCode==13) submitPage('CANCEL');" tabindex="1" onclick ="submitPage('CANCEL');">
          <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style=cursor:pointer tabindex="1" onclick ="submitForm('NEW')" onkeypress="if(event.keyCode==13) submitForm('NEW');">
	  </div>
	  </his:ButtonToolBarTag>
	  <%} %>

 
<%System.out.println("....2");%>
<%=msg%>
<his:status/>

<input type= "hidden" name="hmode" value="unspecified"/>
<html:hidden name="EmgUnKnownTOKnownFB" property="patPrimaryCatCode"/>

<%
     String	sysDate = hisglobal.presentation.WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy");
 %>
<input type= "hidden" name="sysDate" value="<%=sysDate %>"/>

<input type= "hidden" name="hmode" value="unspecified"/>
<html:hidden name="EmgUnKnownTOKnownFB" property="beforeModificationAge"/>
<html:hidden name="EmgUnKnownTOKnownFB" property="isMLC"/>
<html:hidden name="EmgUnKnownTOKnownFB" property="mlcNo"/>
<html:hidden name="EmgUnKnownTOKnownFB" property="patStatusCode"/>
<html:hidden name="EmgUnKnownTOKnownFB" property="verificationDocumentAdded"/>
