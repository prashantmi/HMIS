<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<%@page import="hisglobal.hisconfig.Config"%>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<his:javascript src="/registration/js/popup.js" />
<his:javascript src="/registration/js/validationCommon.js" />
<his:javascript src="/registration/js/validationCalls.js" />
<his:javascript src="/registration/js/registration.js" />
<his:javascript src="/registration/js/commonFunctions.js" />
<his:javascript src="/registration/js/stateChangeAjax.js" />
<his:javascript src="/registration/js/cityLocationAjax.js" />
<his:javascript src="/registration/js/dateFunctions.js" />
<his:javascript src="/registration/js/calendar.js" />

<his:javascript src="/hisglobal/js/utilityFunctions.js" />
<his:javascript src="/hisglobal/utility/generictemplate/js/validationFunctions.js"/>
<his:javascript src="/hisglobal/utility/generictemplate/js/date_validator.js"/>


<%@ page
	import="java.util.*,registration.*,registration.controller.fb.PatDtlModificationFB"%>

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

/*alert("refDeptValue="+refDeptValue)
	for(i=0;i<optionsArray.length;i++)
	{
	alert("refDeptValue="+refDeptValue+"optionsArray[i].value="+optionsArray[i].value)
	if(refDeptValue==optionsArray[i].value)
	{
	document.getElementsByName('patRefGnctdHospitalDept')[0].value=optionsArray[i].value
		break;
	}
	else
	{
	document.getElementsByName('patRefGnctdHospitalDept')[0].value=='0'
	document.getElementsByName('patRefHospitalDeptOther')[0].value==refDeptValue
	}
	
	}///for loopr
	alert("document.getElementsByName('patRefGnctdHospitalDept')[0].value="+document.getElementsByName('patRefGnctdHospitalDept')[0].value)
	alert("document.getElementsByName('patRefHospitalDeptOther')[0].value="+document.getElementsByName('patRefHospitalDeptOther')[0].value)
	if(document.getElementsByName('patRefGnctdHospitalDept')[0].value=='0')
	{
		document.getElementsByName('patRefHospitalDeptOther')[0].disabled=false
		
		}
	else
	{
	document.getElementsByName('patRefHospitalDeptOther')[0].disabled=true
		}
		
	*/
	<%String client=Config.CLIENT; %>
	//alert(<%=client%>)
	//if(<%=client%>==<%=Config.CLIENT_PGIMER%>){
		if(document.getElementsByName('patRefGnctdHospitalDept')[0]){
		var selectObject=document.getElementsByName('patRefGnctdHospitalDept')[0]
		var optionsArray=selectObject.options;
		var refDeptValue=document.getElementsByName('patRefGnctdHospitalDept')[0].value
		if(selectObject.value=='0'){
			document.getElementsByName('patRefHospitalDeptOther')[0].disabled=false
			//document.getElementsByName('patRefHospitalDeptOther')[0].value=""
		}
		else{
			document.getElementsByName('patRefHospitalDeptOther')[0].value=""
			document.getElementsByName('patRefHospitalDeptOther')[0].disabled=true
		}
		}
	//}	
}

/*
function checkForEmployee()
{ //alert("sdsdsdsdsd")
	if(document.getElementsByName("patPrimaryCatCode")[0]){
	 var patPriCode=document.getElementsByName("patPrimaryCatCode")[0].value;
  //alert("cxcxc")
	 var patEmployeeCode="<%=Config.PRIMARY_CATEGORY_EMPLOYEE_CODE %>";
	 
	 var clientValue="<%=Config.CLIENT%>";
	 var clientPGIMER="<%=Config.CLIENT_PGIMER%>";
	 if(patPriCode==patEmployeeCode)
	 {
	 <logic:equal name="clientFlag"  value="<%=Config.CLIENT_GNCTD%>">
		 // document.getElementsByName("prevCrNo")[0].disabled=true;
		 </logic:equal>
		  document.getElementsByName("patFirstName")[0].disabled=true;
		  document.getElementsByName("patMiddleName")[0].disabled=true;
		  document.getElementsByName("patLastName")[0].disabled=true;
		  document.getElementsByName("isActualDob")[0].disabled=true;
		  document.getElementsByName("patAge")[0].disabled=true;
		  document.getElementsByName("patAgeUnit")[0].disabled=true;
		  document.getElementsByName("patDOB")[0].disabled=true;
		  document.getElementsByName("patGenderCode")[0].disabled=true;
		  document.getElementsByName("patGuardianName")[0].disabled=true;
		  document.getElementsByName("patHusbandName")[0].disabled=true;
		  document.getElementsByName("patMotherName")[0].disabled=true;
		  document.getElementsByName("patMaritalStatusCode")[0].disabled=true;
		  document.getElementsByName("patNationalityCode")[0].disabled=true;
		  document.getElementsByName("patReligionCode")[0].disabled=true;
		  document.getElementsByName("patIsUrban")[0].disabled=true;
		  document.getElementsByName("patMonthlyIncome")[0].disabled=true;
			  if(clientValue==clientPGIMER)
				  {
				  	  document.getElementsByName("patNickName")[0].disabled=true;
					  document.getElementsByName("patOccupation")[0].disabled=true;
					  document.getElementsByName("patFatherOccupation")[0].disabled=true;
					  document.getElementsByName("patHusbandOccupation")[0].disabled=true;
					  document.getElementsByName("patCardNo")[0].disabled=true;
				  }
				  else{}
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
		else
		{
		}	  
	}
}
function enableSpouseField()
{
// alert("sdsdsdsdsdsds")
	var maritalStatus;
	if(document.getElementsByName("patMaritalStatusCode")[0]){
		maritalStatus=document.getElementsByName("patMaritalStatusCode")[0].value;
		
	
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
}
*/


function checkState(){
 	var flag=false;
 	// alert("check state")
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
	if(document.getElementsByName("patAddCountryCode")[0]){
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
		//  alert("1="+document.getElementsByName("patAddCityLoc")[0].value) 
		  // document.getElementsByName("patAddCityLoc")[0].value="";
		    // elmt.options[elmt.selectedIndex].value="<%=RegistrationConfig.REGISTRATIONDESK_DEFAULT_STATE_CODE%>";
		 }
	}
	else{
		  document.getElementById("divpatAddCityLocCode").style.display="none"; 
		  document.getElementById("divpatAddCityLocation").style.display="";   
		    // elmt.options[elmt.selectedIndex].value="<%=RegistrationConfig.REGISTRATIONDESK_DEFAULT_STATE_CODE%>";
		 }
	
}

 
function callThisOnload(){

	if(document.getElementsByName("patCrNo")[0]){
		document.getElementsByName("patCrNo")[0].focus();
	}
	showState();
//	showdelhidiv();
	showLocation();
	//checkForEmployee();
	checkReferDepartment();
	//focusCrNo();

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
		// alert("in else is refer");
		flag=true;
	}
	return flag;
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

// Changed on 30-Oct-2009
function Validate()
{
	//alert("in Validate");
	var client="<%=Config.CLIENT%>";
	var clientSms="<%=Config.CLIENT_SMS%>";
	var patPriCode=document.getElementsByName("patPrimaryCatCode")[0].value;
	var patEmployeeCode="<%=Config.PRIMARY_CATEGORY_EMPLOYEE_CODE %>";
	var valid=true;

	if(patPriCode==patEmployeeCode)
	{
		if( comboValidation(document.forms[0].patPrimaryCatCode,"Patient Category")
			//&& validateLocation()	
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
		if( isEmpty(document.forms[0].patFirstName,"First Name")
			&& checkAgeOrDob()
			&& validateDot(document.forms[0].patFirstName,"First Name")
			&& validateSpecialCharacter('/',document.forms[0].patFirstName,"First Name")
			&& validateSpecialCharacter('-',document.forms[0].patFirstName,"First Name")
			&& validateDot(document.forms[0].patMiddleName,"Middle Name")
			&& (client==clientSms ?(isEmpty(document.forms[0].patLastName,"Last Name")):true )
			&& validateDot(document.forms[0].patLastName,"Last Name")
			&& isValid(document.forms[0].patAge,"Age",125)
			&& isEmpty(document.forms[0].patAgeUnit,"Age Unit")

			//&& validateDateAgainstSysDate(document.forms[0].patDOB,"Date of Birth")
			&& validatePatientDOBAgainstSysDate()
			
			&& checkIsrefer()
			&& isSelected(document.forms[0].patGenderCode,"Gender")
			//&& isEmpty(document.forms[0].patGuardianName,"Father's Name")
			&& validateFatherNameSpouseName()
			&& validateDot(document.forms[0].patGuardianName,"Father Name")
			&& validateDot(document.forms[0].patHusbandName,"Husband Name")
			&& validateDot(document.forms[0].patMotherName,"Mother Name")
			&& isSelected(document.forms[0].patPrimaryCatCode,"Primary Category")
			&& isSelected(document.forms[0].patNationalityCode,"Nationality")
			&& comboValidation(document.forms[0].patReligionCode,"Religion")
			&& validateMonthlyIncome(document.getElementsByName("patMonthlyIncome")[0],'<%=RegistrationConfig.BPL_MONTHLY_INCOME%>','<%=Config.PRIMARY_CATEGORY_POOR_FREE_CODE%>')
			&& isSelected(document.forms[0].patAddCountryCode,"Country")
			&& checkState()
			//&& validateLocation()
			//&& validateDot(document.forms[0].patAddCityLoc,"Location")
			//&& validateDot(document.forms[0].patAddHNo,"House Number")
			//&& validateDot(document.forms[0].patAddStreet,"Street")
			&& isEmpty(document.forms[0].patAddHNo,"Address")
			&& validateDot(document.forms[0].patAddCityLoc,"Tehsil")
			&& validateDot(document.forms[0].patAddDistrict,"District")
			&& isEmpty(document.forms[0].patAddCity,"City / Village")
			&& validateDot(document.forms[0].patAddCity,"City / Village")
			&& validateMinimumLength(document.forms[0].patAddPIN,'Pin Number','6')
			&& validatePinNumber(document.forms[0].patAddPIN)
			//&& isEmpty(document.forms[0].patAddContactNo,"Contact No")
			&& isEmpty(document.forms[0].patRefDoctor,"Referred By Doctor")
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

function doHomeWork(){
// alert("home work modificaiton");
// document.getElementsByName("crNo1")[0].disabled=false;
//	 document.getElementsByName("crNo2")[0].disabled=false;
//document.getElementsByName("crNo3")[0].disabled=false;

}


  <%--  function  showdelhidiv()
  {

	  
 	<his:statusInProcessWithJsp> 
 	  <logic:equal name="PatDtlModificationFB" property="isAddressDelhi" value="1">
	   document.getElementById("divpatAddCityLocCode").style.display="";   	  
	   document.getElementById("divpatAddCityLocation").style.display="none";
	    document.getElementsByName("patAddStateCode")[0].disabled="true";   
       document.getElementsByName("patAddCountryCode")[0].disabled="true";
	   document.getElementsByName("patAddStateCode")[0].value="<%=RegistrationConfig.REGISTRATIONDESK_DEFAULT_STATE_CODE %>";   
       document.getElementsByName("patAddCountryCode")[0].value="<%=RegistrationConfig.REGISTRATIONDESK_DEFAULT_COUNTRY_CODE%>"; 
	</logic:equal>	
	  //alert("before logic");
      <logic:equal name="PatDtlModificationFB" property="isReferred" value="1">
      //alert("logic equal isReferred 1");
      Isreferred(document.getElementsByName("isReferred")[0]);
	 </logic:equal>	
	 
	 </his:statusInProcessWithJsp>
 }--%>
 
function populate(e){ 

document.getElementsByName('crNoToRetrieve')[0].value=e;

submitForm("DGNDETAIL"); 

} 
</script>
<%
	String divpatAddCityLocCode = "\"\"";
	String divpatAddCityLocation = "\"\"";
	String divisrefdisplay = "\"\"";
	String divRefGnctdHospitalCode = "\"\"";
	String divRefHospname = "\"\"";
	String strdivage = "\"\"";
	String strdivdob = "\"\"";
	String RefGnctdHosNameradio = "";
	String RefGnctdHoscoderadio = "";
	String divRefInstitute = "\"\"";
	String gnctdrefDetails = "\"\"";
	String divDocTitle = "\"\"";
	String divDocName = "\"\"";

	String systemDate = hisglobal.presentation.WebUTIL
			.getCustomisedSysDate((Date) session
			.getAttribute(Config.SYSDATEOBJECT),
			"dd-MMM-yyyy HH:mm");
%>
<%
	boolean varIsNewStatus = false;
	String varStatus = "";
%>
<his:statusNew>
	<%
		varIsNewStatus = true;
		varStatus = "New";
	%>

</his:statusNew>

<logic:equal name="PatDtlModificationFB" property="isAddressDelhi"
	value="0">

	<%
		divpatAddCityLocCode = "none";
		divpatAddCityLocation = "";
	%>
</logic:equal>

<logic:equal name="PatDtlModificationFB" property="isAddressDelhi"
	value="1">

	<%
		divpatAddCityLocCode = "";
		divpatAddCityLocation = "none";
	%>
</logic:equal>
<his:TitleTag name="Patient Detail Modification">

	<!--<b>
		<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		<a onclick="openPopup('registration/searchByNamePopup.cnt',event)" style=cursor:pointer>
		<bean:message key="search"/>
		<bean:message key="by"/>
		<bean:message key="name"/>
		</a>
		</font>
		</b>
		-->

	<b> <font size="2" face="Verdana, Arial, Helvetica, sans-serif">
	 </font> </b>
</his:TitleTag>

<his:statusNew>

</his:statusNew>

<his:InputCrNoTag name="PatDtlModificationFB"></his:InputCrNoTag>
<his:statusNew>
	<input type='hidden' name='crNoToRetrieve' />
	<his:ContentTag>

		<table width="100%" border="0" cellspacing="1" cellpadding="0">
			<logic:notEmpty name="<%=RegistrationConfig.PATIENT_VO%>">
				<logic:iterate id="PATIENT_VO"
					name="<%=RegistrationConfig.PATIENT_VO%>">

					<tr>
						<td width="5%" class="tdfont"><input type="radio"
							name="crNoToModify" onclick="submitForm('GETPATDTL');"
							value='<bean:write name="PATIENT_VO" property="patCrNo" />' tabindex="1" /></td>
						<td width="20%" class="tdfont"><bean:write name="PATIENT_VO"
							property="patCrNo" /></td>
						<td width="25%" class="tdfont"><bean:write name="PATIENT_VO"
							property="patFirstName" /> <bean:write name="PATIENT_VO"
							property="patMiddleName" /> <bean:write name="PATIENT_VO"
							property="patLastName" /></td>
						<td width="25%" class="tdfont"></td>
						<td width="25%" class="tdfont"></td>
						<td width="25%" class="tdfont"></td>
					</tr>
				</logic:iterate>
			</logic:notEmpty>
		</table>
	</his:ContentTag>
</his:statusNew>


<%--<bean:define name="<%=RegistrationConfig.STATUS_OBJECT%>" id="objStatus" type="registration.global.util.Sattus" />--%>
<his:statusInProcessWithJsp>
	<%
	varStatus = "InProcess";
	%>
	<bean:define id="clientFlag" name="<%=Config.CLIENT_NAME %>"></bean:define>
	<his:ContentTag>
		<table width="100%" border="0" cellspacing="1" cellpadding="0"
			bgcolor="#EBEBEB">
			<tr>
				<td width="15%" nowrap class="tdfonthead">
				<div align="right"><font color="#FF0000" size="1"
					face="Verdana, Arial, Helvetica, sans-serif">*</font> <font
					color="#000000" size="2"
					face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
					key="patient" /> <bean:message key="category" /> </font></div>
				</td>

				<td width="17%" class="tdfont"><font color="#000000" size="1"
					face="Verdana, Arial, Helvetica, sans-serif"> <html:text
					name="PatDtlModificationFB" property="patPrimaryCat" tabindex="1"
					styleClass="regcbo" readonly="true"></html:text> <html:hidden
					name="PatDtlModificationFB" property="patPrimaryCatCode" /> </font></td>
				<%--
         	<td class="tdfonthead" width="17%">
    	      <logic:equal name="PatDtlModificationFB" property="patPrimaryCatCode" value="<%=RegistrationConfig.PRIMARY_CATEGORY_EMPLOYEE_CODE %>">
			        <div align="right" id ="divempIdLabel">
				        <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					        <bean:message key="id"/>
				        </font>
		    	    </div>
		        </logic:equal>
	        </td>	                
    	    <td class="tdfont" width="17%" nowrap>
    	      <logic:equal name="PatDtlModificationFB" property="patPrimaryCatCode" value="<%=RegistrationConfig.PRIMARY_CATEGORY_EMPLOYEE_CODE %>">
    	        <div align="right" id ="divempIdControl">    	        
	        	 	<html:text name="PatDtlModificationFB"  onkeypress="return validateAlphaNumericOnly(event);" styleClass="textbox" property="patIdNo"  maxlength="10" tabindex="1"/>
			        <img class="button" img src="../hisglobal/images/ico_myfriends.gif" tabindex="1" border="0" style="cursor:pointer" onClick="submitFormOnValidate(validatePrevCRNo(),'GETPATDTLBYPREVCRNO');" onkeypress="if(event.keyCode==13) submitFormOnValidate(validatePrevCRNo(),'GETPATDTLBYPREVCRNO');">
		        </div>
		        </logic:equal>    	     
        	</td>        --%>
        	
        		<logic:equal name="clientFlag"  value="<%=Config.CLIENT_GNCTD%>">	
        	<td class="tdfonthead" width="17%"></td>
				<td class="tdfont" width="17%" nowrap></td>
				<td class="tdfonthead" width="17%">        	
		      <div align="right">
			       	<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			        	<bean:message key="previous"/>
				        <bean:message key="crNo"/>
			        </font>
		        </div>
	        </td>  
	        
	         
    	    <td class="tdfont" width="17%" nowrap>  
		        <html:text name="PatDtlModificationFB" property="prevCrNo" styleClass="textbox"  maxlength ="13" tabindex="1" onkeypress="return validateNumeric(event)" styleClass="textbox"/> 
		        
       		</td>  
       		 </logic:equal>          
     			<logic:notEqual name="clientFlag"  value="<%=Config.CLIENT_GNCTD%>">	
				<td class="tdfonthead" width="17%"></td>
				<td class="tdfont" width="17%" nowrap></td>
				<td class="tdfonthead" width="17%"></td>
				<td class="tdfont" width="17%"></td>
				</logic:notEqual>
			
			</tr>
			<tr>
				<td width="14%" height="25" nowrap class="tdfonthead">
				<div align="right"><font color="#000000" size="2"
					face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
					key="patient" /> <bean:message key="name" /> </font></div>
				</td>

				<td width="19%" class="tdfonthead">
				<div align="left"><font color="#000000" size="2"
					face="Verdana, Arial, Helvetica, sans-serif"><font
					color="#FF0000" size="1"
					face="Verdana, Arial, Helvetica, sans-serif">*</font> <bean:message
					key="first" /> </font></div>
				</td>

				<td width="18%" class="tdfonthead">
				<div align="left"><font size="2" color="#000000"  style="display:block"
					face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
					key="middle" /> </font></div>
				</td>
			
				<logic:equal name="clientFlag" value="<%=Config.CLIENT_SMS%>">
					<td width="19%" class="tdfonthead">
					<div align="left"><font color="#000000" size="2"
						face="Verdana, Arial, Helvetica, sans-serif"><font
						color="#FF0000" size="1"
						face="Verdana, Arial, Helvetica, sans-serif">*</font> <bean:message
						key="last" /> </font></div>
					</td>
				
				</logic:equal>
				<logic:notEqual name="clientFlag" value="<%=Config.CLIENT_SMS%>">
					<td width="17%" class="tdfonthead">
					<div align="left"><font color="#000000" size="2"
						face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
						key="last" /> </font></div>
					</td>
				</logic:notEqual>
				<td class="tdfonthead" width="17%"></td>
				<td class="tdfont" width="17%" nowrap></td>
			</tr>
			<tr>

				<td width="14%" class="tdfonthead">&nbsp;</td>

				<td width="17%" class="tdfont"><html:text
					name="PatDtlModificationFB" property="patFirstName"
					styleClass="textbox" tabindex="1" maxlength="30"
					onblur="isAlpha(this,'First Name')"
					onkeypress="return validateAlphabetsWithDotsAndForwardSlashWithHypen(event,this)" /></td>

				<td width="18%" class="tdfonthead"><html:text
					name="PatDtlModificationFB" property="patMiddleName" tabindex="1"
					styleClass="textbox" onblur="isAlpha(this,'Middle Name')"
					maxlength="20"
					onkeypress="return validateAlphabetsWithDotsOnly(event,this)" style="display:block"/></td>

				<td width="17%" class="tdfont"><html:text
					name="PatDtlModificationFB" property="patLastName" tabindex="1"
					styleClass="textbox" maxlength="30"
					onblur="isAlpha(this,'Last Name')"
					onkeypress="return validateAlphabetsWithDotsOnly(event,this)" /></td>
				<logic:equal name="clientFlag" value="<%=Config.CLIENT_PGIMER%>">
					<td width="14%" class="tdfonthead">
					<div id="nicklable" align="right"><font size="2" style="display:none"
						color="#000000" face="Verdana, Arial, Helvetica, sans-serif">
					<bean:message key="familyHeadName" /> </font></div>
					</td>


					<td width="17%" class="tdfont">
					<div id="nickNameControl" style="display:none"><html:text
						name="PatDtlModificationFB" property="familyHeadName" tabindex="1"
						styleClass="textbox" maxlength="30"	onkeypress="return validateAlphabetsWithDotsOnly(event,this)" /></div>
					</td>
				</logic:equal>
				<logic:notEqual name="clientFlag" value="<%=Config.CLIENT_PGIMER%>">
				<td class="tdfonthead" width="17%"></td>
				<td class="tdfont" width="17%" nowrap></td>
				</logic:notEqual>
			<tr>

				<td width="18%" nowrap class="tdfonthead">
				<div align="center"><font color="#FF0000" size="1"
					face="Verdana, Arial, Helvetica, sans-serif">*</font> <font
					color="#000000" size="2"
					face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
					key="age" /></font> <html:radio name="PatDtlModificationFB"
					property="isActualDob" tabindex="1" value="0"
					onclick="ageModificationSelection()" /> <font color="#000000"
					size="2" face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
					key="dob" /></font> <html:radio name="PatDtlModificationFB"
					property="isActualDob" tabindex="1" value="1"
					onclick="ageModificationSelection()" /></div>
				</td>


				<logic:equal name="PatDtlModificationFB" property="isActualDob"
					value="1">
					<%
						strdivage = "none";
						strdivdob = "";
					%>
				</logic:equal>
				<logic:equal name="PatDtlModificationFB" property="isActualDob"
					value="0">
					<%
						System.out.println("inside case 0isActualDob:::::::::");
						strdivage = "";
						strdivdob = "none";
					%>
				</logic:equal>

				<td width="18%" class="tdfont" nowrap="nowrap">
				<div id="divAge" style='display: <%= strdivage %>'><html:text
					name="PatDtlModificationFB" size="4" property="patAge" tabindex="1"
					maxlength="3" onkeypress="return validateNumeric(event)" /> <html:select
					name="PatDtlModificationFB" property="patAgeUnit" tabindex="1"
					styleClass="smallcombo">
					<html:options
						collection="<%=RegistrationConfig.ESSENTIALBO_OPTION_AGE_TYPE %>"
						property="value" labelProperty="label" />
				</html:select></div>
				<%//String strEmpdivdob="none"; 
				// if(((PatDtlModificationFB)pageContext.findAttribute
						// ("PatDtlModificationFB")).getPatPrimaryCatCode().equals(Config.PRIMARY_CATEGORY_EMPLOYEE_CODE)){
					//	 strEmpdivdob="";
					//	 strdivdob="none";
					//	 }%>
				<div id="divDob" style='display: <%= strdivdob %>'>
					<bean:define name="PatDtlModificationFB" property="patDOB" id="dob" type="java.lang.String" />

					<div id="divPatDOB"></div>
					<script type="text/javascript">DateValidator.setup("divPatDOB","patDOB","<%=dob%>","dd-Mon-yyyy","textbox");</script>
										
					<!--<his:date name='<%="patDOB"%>' dateFormate="%d-%b-%Y" value='<%=dob%>' />-->
					
				</div>
				<%--<div id="divEmpDob" style='display:<%=strEmpdivdob%>'>
					<input type="text" value='<%=dob%>' readonly="readonly" size="12"/>
				</div>	
				--%>
				</td>
				<td width="14%" class="tdfonthead">
				<div align="right"><font color="#FF0000" size="1"
					face="Verdana, Arial, Helvetica, sans-serif">*</font> <font
					color="#000000" size="2"
					face="Verdana, Arial, Helvetica, sans-serif"><bean:message
					key="gender" /></font></div>
				</td>

				<td width="17%" class="tdfont"><html:select
					name="PatDtlModificationFB" property="patGenderCode" tabindex="1"
					styleClass="regcbo">
					<html:option value="-1">Select Value</html:option>
					<html:options
						collection="<%=RegistrationConfig.ESSENTIALBO_OPTION_GENDER %>"
						property="value" labelProperty="label" />
				</html:select>
				</td>
				<td width="14%" class="tdfonthead">
					<div align="right"><font color="#000000" size="2"
					face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
					key="monthlyIncome" /> </font></div>
				</td>

				<td class="tdfont"><html:text name="PatDtlModificationFB"
					property="patMonthlyIncome"
					onkeypress="return(currencyFormat(this,',','.',event))"
					styleClass="textbox" tabindex="1" maxlength="11" />
				</td>
			<tr>
				<td width="17%" class="tdfonthead" nowrap>
				<div align="right"><font color="#000000" size="2"
					face="Verdana, Arial, Helvetica, sans-serif">
					<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font>
					 <bean:message key="fathersName" /></font></div>
				</td>

				<td width="17%" class="tdfont"><html:text
					name="PatDtlModificationFB" property="patGuardianName"
					styleClass="textbox" tabindex="1" maxlength="60"
					onblur="isAlpha(this,'Guardian Name')"
					onkeypress="return validateAlphabetsWithDotsOnly(event,this)" /></td>

				<td width="17%" class="tdfonthead" nowrap>
				<div align="right"><font color="#000000" size="2"
					face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
					key="husbandName" /></font></div>
				</td>
				<td width="17%" class="tdfont"><html:text
					name="PatDtlModificationFB" property="patHusbandName"
					styleClass="textbox" tabindex="1" maxlength="60"
					onblur="isAlpha(this,'Guardian Name')"
					onkeypress="return validateAlphabetsWithDotsOnly(event,this)" /></td>
				
				<td width="17%" class="tdfonthead" nowrap>
				<div align="right"><font color="#000000" size="2"
					face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
					key="motherName" /></font></div>
				</td>
				<td width="17%" class="tdfont"><html:text
					name="PatDtlModificationFB" property="patMotherName"
					styleClass="textbox" tabindex="1" maxlength="60"
					onblur="isAlpha(this,'Guardian Name')"
					onkeypress="return validateAlphabetsWithDotsOnly(event,this)" /></td>
			</tr>
			<tr>
				<td class="tdfonthead" width="18%">
				<div align="right"><font color="#FF0000" size="1"
					face="Verdana, Arial, Helvetica, sans-serif">*</font> <font
					color="#000000" size="2"
					face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
					key="nationality" /> </font></div>
				</td>
				<td class="tdfont" width="17%">
				<div id="patCountryCombo"><html:select
					name="PatDtlModificationFB" property="patNationalityCode"
					tabindex="1" styleClass="regcbo">
					<html:option value="-1">Select Value</html:option>
					<html:options
						collection="<%=RegistrationConfig.ESSENTIALBO_OPTION_NATIONALITY %>"
						property="value" labelProperty="label" />
				</html:select></div>
				</td>

				<td width="14%" class="tdfonthead">
				<div align="right">
				<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font>
				<font color="#000000" size="2"
					face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
					key="religion" /></font></div>
				</td>

				<td width="17%" class="tdfont"><html:select
					name="PatDtlModificationFB" property="patReligionCode" tabindex="1"
					styleClass="regcbo">
					<html:option value="">Select Value</html:option>
					<logic:present name="<%=RegistrationConfig.ESSENTIALBO_OPTION_RELIGION %>">
					<html:options
						collection="<%=RegistrationConfig.ESSENTIALBO_OPTION_RELIGION %>"
						property="value" labelProperty="label" /></logic:present>
				</html:select></td>
				
				<td width="18%" class="tdfonthead">
				<div align="right"><font color="#000000" size="2"
					face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
					key="category" /> </font></div>
				</td>
				<td width="17%" class="tdfont"><html:select
					name="PatDtlModificationFB" property="patCasteCategoryCode" tabindex="1" styleClass="regcbo">
					<html:option value="-1">Select Value</html:option>
					<logic:present name="<%=RegistrationConfig.ESSENTIALBO_OPTION_MARITAL_STATUS %>">
					<html:options
						collection="<%=RegistrationConfig.ESSENTIALBO_OPTION_MARITAL_STATUS %>"
						property="value" labelProperty="label" /></logic:present>
					</html:select>
				</td>
				
		
			</tr>
			<logic:equal name="clientFlag" value="<%=Config.CLIENT_PGIMER %>">
				<tr>

					<td width="17%" class="tdfonthead" nowrap="nowrap">
					<div align="right"><font color="#000000" size="2"
						face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
						key="patOccupation" /> </font></div>
					</td>
					<td width="17%" class="tdfont" ><html:select
						name="PatDtlModificationFB" tabindex="1" property="patOccupation"
						styleClass="regcbo">
						<html:option value="">Select Value</html:option>
						<logic:present name="<%=RegistrationConfig.ESSENTIALBO_OPTION_OCCUPATION_DTL %>" >
						<html:options
							collection="<%=RegistrationConfig.ESSENTIALBO_OPTION_OCCUPATION_DTL %>"
							property="value" labelProperty="label" /></logic:present>
					</html:select></td>

					<td width="17%" class="tdfonthead" nowrap="nowrap">
					<div align="right"><font color="#000000" size="2"
						face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
						key="patFatherOccupation" /> </font></div>
					</td>

					<td width="17%" class="tdfont" ><html:select
						name="PatDtlModificationFB" tabindex="1"
						property="patFatherOccupation" styleClass="regcbo">
						<html:option value="">Select Value</html:option>
						<logic:present name="<%=RegistrationConfig.ESSENTIALBO_OPTION_OCCUPATION_DTL %>" >
						<html:options
							collection="<%=RegistrationConfig.ESSENTIALBO_OPTION_OCCUPATION_DTL %>"
							property="value" labelProperty="label" /></logic:present>
					</html:select></td>

				<td width="17%" class="tdfonthead" nowrap="nowrap" >
					<div align="right">
					<font color="#000000" size="2"
						face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
						key="relation" /> </font></div>
				</td>
				
				<td width="17%" class="tdfont" nowrap="nowrap" >
					<html:select name="PatDtlModificationFB" tabindex="2" property="patRelationCode"  styleClass="regcbo" >
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


					<td class="tdfonthead" width="17%"></td>
					<td class="tdfont" width="17%"></td>
				</tr>
			</logic:equal>


			<tr>
				<td width="14%" class="tdfonthead">
				<div align="right"><font color="#000000" size="2"
					face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
					key="uniqueId" /> </font></div>
				</td>
				<td width="17%" class="tdfont" ><html:text
					name="PatDtlModificationFB" property="patNationalId"
					styleClass="textbox" maxlength="16" tabindex="1"
					onkeypress="return validateAlphaNumericWithSpecialCharacterOnly(event,this)"
					styleClass="textbox" /></td>
				<logic:equal name="clientFlag" value="<%=Config.CLIENT_PGIMER %>">
					<td width="17%" class="tdfonthead" nowrap="nowrap">
					<div align="right"><font color="#000000" size="2"
						face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
						key="mmjrkNo" /> </font></div>
					</td>

					<td width="17%" class="tdfont" ><html:text
						name="PatDtlModificationFB" property="patCardNo"
						styleClass="textbox" maxlength="30" tabindex="1" onkeypress="return validateAlphaNumericOnly(event,this)"/></td>
				</logic:equal>
				<td width="14%" class="tdfonthead"></td>
				<td width="17%" class="tdfont" nowrap></td>

			</tr>

		</table>
		<bean:define name="PatDtlModificationFB" property="isReferred"
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
				System.out.println("inside isref 0");
				divisrefdisplay = "none";
				divRefGnctdHospitalCode = "none";
				divRefHospname = "none";
				System.out.println("inside isref 0");
			%>
		</logic:equal>
		<logic:equal name="isref" value="0">
			<%
				System.out.println("inside isref 0");
				divisrefdisplay = "none";
				divRefGnctdHospitalCode = "none";
				divRefHospname = "none";
				gnctdrefDetails = "none";
				divRefInstitute = "none";
				divDocTitle = "none";
				divDocName = "none";
				System.out.println("inside isref 0");
			%>
		</logic:equal>

		<logic:equal name="isref" value="1">
			<logic:equal name="PatDtlModificationFB" property="referringInstType"
				value="O">
				<%
					System.out.println("patRefGnctdHospitalCode:::::::::empty");
					divRefGnctdHospitalCode = "none";
					divRefHospname = "";
					gnctdrefDetails = "none";
					divDocTitle = "";
					divDocName = "";
					gnctdrefDetails = "";
				%>
			</logic:equal>
			<logic:equal name="PatDtlModificationFB" property="referringInstType"
				value="G">
				<%
					System.out.println("patRefHospitalName:::::::::empty");
					divRefGnctdHospitalCode = "";
					divRefHospname = "none";
					gnctdrefDetails = "";
					divDocTitle = "";
					divDocName = "";
					gnctdrefDetails = "";
				%>
			</logic:equal>
		</logic:equal>
		<%
		System.out.println("divRefHospname:::::::::" + divRefHospname);
		%>
	</his:ContentTag>
	<his:SubTitleTagBroad name="Address Detail">
		<table width="100%">
			<tr>
				<td><font color="#FF0000" size="1"
					face="Verdana, Arial, Helvetica, sans-serif">*</font> <font
					color="#000000" size="2"
					face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
					key="country" /></font></td>
				<td><html:select name="PatDtlModificationFB" tabindex="1"
					property="patAddCountryCode" styleClass="regcbo"
					onchange="showState()">
					<html:option value="-1">Select Value</html:option>
					<html:options
						collection="<%=RegistrationConfig.ESSENTIALBO_OPTION_COUNTRY %>"
						property="value" labelProperty="label" />
				</html:select></td>
				<td>

				<div id="stateMandotary" style=""><font color="#FF0000"
					size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font> <font
					color="#000000" size="2"
					face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
					key="state" /> </font></div>
				<div id="stateNotMandotary" style=""><font color="#000000"
					size="2" face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
					key="state" /> </font></div>
				</td>
				<td>
				<div id="divpatAddStateCodeCombo" style=""><html:select
					name="PatDtlModificationFB" tabindex="1" property="patAddStateCode"
					styleClass="regcbo"
					onchange="if(this.value!='-1') sendDataForStateChange(this)">
					<html:option value="-1">Select Value</html:option>
					<html:options
						collection="<%=RegistrationConfig.ESSENTIALBO_OPTION_STATE %>"
						property="value" labelProperty="label" />
				</html:select></div>
				<div id="divpatAddStateCodeText" style=""><html:text
					name="PatDtlModificationFB" tabindex="1" property="patAddStateName"
					styleClass="regcbo" maxlength="50" onkeypress="return validateAlphabetsOnly(event,this)">
				</html:text></div>
				</td>
			</tr>
		</table>
	</his:SubTitleTagBroad>

	<his:ContentTag>

		<table width="100%" cellspacing="1" cellpadding="0">
			<tr>
				



				<td class="tdfonthead" width="14%">
				<div align="right">
					<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font>
					<font color="#000000" size="2"
					face="Verdana, Arial, Helvetica, sans-serif"> 
					<bean:message key="address" /> </font></div>
				</td>

				<td class="tdfont" width="17%" colspan="3">
					<html:textarea  name="PatDtlModificationFB" onkeypress="return CheckMaxLength(event,this,100,1);" 
                		property="strPatAddress" tabindex="2" cols="55" rows="2"/>
				</td>

				<td class="tdfonthead" width="18%">
				<div align="right"><font color="#000000" size="2"
					face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
					key="tehsil" /> </font></div>
				</td>

				<td class="tdfont" width="17%"><html:text
					name="PatDtlModificationFB" property="strPatAddressTehsil"
					onkeypress="return validateAlphaNumericWithDotsOnly(event,this);"
					tabindex="1" maxlength="30" styleClass="textbox" />
				</td>
				
			</tr>

			<tr>
				<td class="tdfonthead" width="14%">
				<div align="right"> <font
					color="#000000" size="2"
					face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
					key="district" /> </font></div>
				</td>

				<td class="tdfont" width="17%">
				<div id="districtCombo"><html:select
					name="PatDtlModificationFB" tabindex="1" styleClass="regcbo"
					property="patAddDistrictCode">
					<html:option value="-1">Select Value</html:option>
					<html:options
						collection="<%=RegistrationConfig.ESSENTIAL_BO_OPTION_DISTRICT_LIST_STATEWISE%>"
						property="value" labelProperty="label" />
				</html:select></div>
				<div id="districtTextBox" style="display: none"><html:text
					name="PatDtlModificationFB" tabindex="1" property="patAddDistrict"
					onchange="isAlpha(this,'District')"
					onkeypress="return validateAlphabetsWithDotsOnly(event,this)"
					maxlength="30" styleClass="textbox" /></div>
				</td>

				<td class="tdfonthead" width="18%">
				<div align="right">
				<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font>
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
					  	<bean:message key="city"/>
			            <bean:message key="slash"/>
			            <bean:message key="village"/>
					 </font></div>
				</td>

				<td class="tdfont" width="17%"><html:text
					name="PatDtlModificationFB" tabindex="1" property="patAddCity"
					onchange="isAlpha(this,'City')"
					onkeypress="return validateAlphabetsWithDotsOnly(event,this);"
					maxlength="30" styleClass="textbox" /></td>

				<td class="tdfonthead" width="14%">
				<div align="right"><font color="#000000" size="2"
					face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
					key="pin" /> </font></div>
				</td>
				<%
							String pinCode = ((PatDtlModificationFB) pageContext
							.findAttribute("PatDtlModificationFB")).getPatAddPIN();
					if (pinCode == null) {
						pinCode = "";
					}
				%>
				<td class="tdfont" width="17%"><input type="text" id="pintext"
					name="patAddPIN" tabindex="1" value="<%=pinCode %>" size="17"
					maxlength="6" onkeypress="return validateNumeric(event)" /></td>


			</tr>

			<tr style="display: none;">

				<td class="tdfonthead" width="17%">
				<div align="right">
				<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font>
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				 <bean:message key="contactNo" /> </font></div>
				</td>

				<td class="tdfont" width="17%">
				<div><html:text name="PatDtlModificationFB" tabindex="1"
					property="patAddContactNo" maxlength="30" styleClass="textbox"
					onkeypress="return validateNumeric(event)" /></div>
				</td>

				<td width="18%" class="tdfonthead">
				<div align="right"><font color="#000000" size="2"
					face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
					key="areaCategory" /> </font></div>
				</td>

				<td width="18%" class="tdfont"><html:select
					name="PatDtlModificationFB" property="patIsUrban" tabindex="1"
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
	<!--................................... code for referred details........... -->

	<his:SubTitleTag name="Refer Detail">
	<table width="100%">
		<tr>
			<td>
			<div align="right">
				<font color="#000000"
					size="2" face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
					key="isreferred" /> </font> <html:checkbox name="PatDtlModificationFB"
					tabindex="1" property="isReferred" onclick="Isreferred(this)"
					value="1" />
			</div>
			</td>
		</tr>
	</table>
	</his:SubTitleTag>
	<his:ContentTag>
		<table width="100%" cellspacing="1" cellpadding="1">
			<tr>
				<td width="50%" class="tdfonthead" colspan="2">
				<div id="divReferredInstitute"
					style="display: <%= divRefInstitute %>"><font color="#000000"
					size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<font color="#FF0000">*</font> <bean:message
					key="gnctd" />
				</font> <html:radio name="PatDtlModificationFB"
					property="referringInstType" tabindex="1" value="G"
					onclick="showdivhoscode(this)" /> <font color="#000000" size="2"
					face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
					key="other" /> </font> &nbsp;<html:radio name="PatDtlModificationFB"
					property="referringInstType" value="O" tabindex="1"
					onclick="showdivhosname(this)" /></div>
				</td>



				<td width="25%" class="tdfont" nowrap>

				<div id='divRefHosCode'
					style='display: <%= divRefGnctdHospitalCode %>'>&nbsp;&nbsp;
				<font color="#FF0000" size="1"
					face="Verdana, Arial, Helvetica, sans-serif">*</font> <font
					color="#000000" size="2"
					face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
					key="hospital" /> <bean:message key="name" /></font> <html:select
					name="PatDtlModificationFB" tabindex="1"
					property="patRefGnctdHospitalCode" styleClass="registrationCmb">
					<html:option value="-1">Select Value</html:option>
					<html:options
						collection="<%=RegistrationConfig.ESSENTIALBO_OPTION_REF_HOSPITAL%>"
						property="value" labelProperty="label" />
				</html:select></div>
				<div id='divRefHosname' style='display: <%= divRefHospname %>'>
				&nbsp;&nbsp; <font color="#FF0000" size="1"
					face="Verdana, Arial, Helvetica, sans-serif">*</font> <font
					color="#000000" size="2"
					face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
					key="hospital" /> <bean:message key="name" /></font> <html:text
					name="PatDtlModificationFB" tabindex="1"
					property="patRefHospitalName"
					onkeypress="return validateAlphabetsOnly(event,this)" maxlength="100"
					styleClass="textboxBig" size="20" /></div>
				</td>
			</tr>
		</table>
	</his:ContentTag>


	<div id="divReferred" style="display:<%=gnctdrefDetails%>"><his:ContentTag>

		<table width="100%" cellpadding="1" cellspacing="1">

			<tr>
				<td width="25%" class="tdfonthead">
				<div align="right"><font color="#000000" size="2"
					face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
					key="referring" /> <bean:message key="hospital" /> <bean:message
					key="crNo" /> </font></div>
				</td>

				<td width="25%" nowrap="nowrap" class="tdfont"><html:text
					name="PatDtlModificationFB" tabindex="1"
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
					key="referredBy" /></font>
				</div>
				</td>

				<td width="15%" colspan="2" class="tdfont">
			
				<div align="left"><html:text name="PatDtlModificationFB"
					 maxlength="60" property="patRefDoctor" 
					onchange="isAlpha(this,'Referred By Doctor')" tabindex="1" styleClass="textbox"
					onkeypress="return validateAlphabetsOnly(event,this)" /></div>
			
				</td>
				
			</tr>
			
			
			<tr  >
				<td width="25%" class="tdfonthead" nowrap>
				<div align="right"><font color="#000000" size="2"
					face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
					key="referring" /> <bean:message key="hospital" /> <bean:message
					key="department" /> </font></div>
				</td>

				<td width="25%" class="tdfont">
				
				<html:select name="PatDtlModificationFB" property="patRefGnctdHospitalDept" tabindex="1" styleClass="regCbo" onchange="checkReferDepartment(this)">
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
				<html:text name="PatDtlModificationFB"  property="patRefHospitalDeptOther" maxlength="20"  tabindex="1" styleClass="textbox" disabled="true"/>
				</td>
				
				
			</tr>
			
			<tr>
				<td width="25%" class="tdfonthead">
				<div align="right"><font color="#000000" size="2"
					face="Verdana, Arial, Helvetica, sans-serif" >  <bean:message key="referring" /> <bean:message
					key="hospital" /> <bean:message key="unit" /> </font>
					</div>
				</td>
					<td width="25%" class="tdfont"><html:text name="PatDtlModificationFB"
					property="patRefGnctdHospitalDeptUnit" tabindex="1" maxlength="15"
					styleClass="textbox" onkeypress="return validateAlphaNumericOnly(event,this)"/></td>
					
				<td width="25%" class="tdfonthead">
				
				</td>
				<td width="25%" class="tdfont">
				
				</td>
		<%--		<td class="tdfonthead" width="18%"></td>
				<td class="tdfont" width="18%"></td> --%>
				
			</tr>
			
		</table>
	</his:ContentTag></div>

	<!--.......................... code for referred details......... ends here.......... -->



</his:statusInProcessWithJsp>


<his:ButtonToolBarTag>
	<%
	if (varStatus.equals("InProcess")) {
	%>
	<img class='button' src='./../HIS/hisglobal/images/btn-sv.png'
		style="cursor: pointer" tabindex="1"
		onclick="submitFormOnValidate(Validate(),'SAVE');"
		onkeypress="if(event.keyCode==13)submitFormOnValidate(Validate(),'SAVE');")>
	<img class="button"
		src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' 
		style="cursor: pointer"
		onkeypress="if(event.keyCode==13) submitPage('CANCEL');" tabindex="1"
		onclick="submitPage('CANCEL');">
	<img class="button"
		src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>'
		style="cursor: pointer" onclick="submitForm('NEW')"
		onkeypress="if(event.keyCode==13) submitForm('NEW');" tabindex="1">
	<%
	} else {
	%>
	<img class="button"
		src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' tabindex="1"
		style="cursor: pointer"
		onkeypress="if(event.keyCode==13) submitPage('CANCEL');" tabindex="1"
		onclick="submitPage('CANCEL');">
	<img class="button"
		src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>'
		style="cursor: pointer" tabindex="1" onclick="submitForm('NEW')"
		onkeypress="if(event.keyCode==13) submitForm('NEW');">

	<%
	}
	%>
</his:ButtonToolBarTag>
<%
	System.out.println("dt::::::::::::::::");
	Date dt = (Date) session.getAttribute(Config.SYSDATEOBJECT);
	System.out.println("dt:::::::::" + dt);
	String sysDate = hisglobal.presentation.WebUTIL
			.getCustomisedSysDate((Date) session
			.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy");
	System.out.println("dt:::::::::" + sysDate);
%>
<input type="hidden" name="sysDate" value="<%=sysDate%>" />
<html:hidden name="PatDtlModificationFB" property="patCrNo" />
<html:hidden name="PatDtlModificationFB" property="departmentCode" />
<his:status />

<%
System.out.println("....2");
%>
<input type="hidden" name="hmode" value="unspecified" />
<html:hidden name="PatDtlModificationFB" property="patAddDistrictCodeHidden"/>
<html:hidden name="PatDtlModificationFB" property="patAddPINHidden"/>
<html:hidden name="PatDtlModificationFB" property="patAddCityHidden"/>
<html:hidden name="PatDtlModificationFB" property="patIsUrbanHidden"/>

<!-- fields added to run the code without changing the js code-->
<input type="hidden" name="patMaritalStatusCode">
<input type="hidden" name="patAddHNo">
<input type="hidden" name="patNickName">
<input type="hidden" name="patHusbandOccupation">
<!-- -----ends here------------------- -->
