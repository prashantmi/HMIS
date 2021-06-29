
function showState(){ 
	var contryCode=document.getElementsByName("patAddCountryCode")[0].value;
	alert("country")
  alert(<%=RegistrationConfig.REGISTRATIONDESK_DEFAULT_COUNTRY_CODE%>)
	var defaltcontryCode="<%=RegistrationConfig.REGISTRATIONDESK_DEFAULT_COUNTRY_CODE%>";  
	 //alert("chcking conuntry code "+contryCode+"  "+defaltcontryCode);
	var stateCode= document.getElementsByName("patAddStateCode")[0];
	if(contryCode==defaltcontryCode)
	{
	   alert("default country")
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

  var elmt= document.getElementsByName("patAddStateCode")[0];
  var locationRequired="<%=Config.IS_LOCATION_COMBO_REQ%>"
  var locationRequiredYes="<%=Config.IS_LOCATION_COMBO_REQ_YES%>"
  
  if(locationRequired==locationRequiredYes)
  { 
  
	  if(elmt.options[elmt.selectedIndex].value!=<%=RegistrationConfig.REGISTRATIONDESK_DEFAULT_STATE_CODE%>)
	  	  {  
	  	   
		  document.getElementById("divpatAddCityLocCode").style.display="none"; 
		  document.getElementById("divpatAddCityLocation").style.display=""; 
		  }
	  else{
		 //alert("inside else hide divpatAddCityLocation") 
	  	  document.getElementById("divpatAddCityLocCode").style.display=""; 
		  document.getElementById("divpatAddCityLocation").style.display="none";   
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
	 	return true
	 }
}
function checkAgeOrDob()
{
	if(document.getElementsByName("isActualDob")[0].checked){
	 return isEmpty(document.forms[0].patAge,"Age"); 
	 	 }
	 else{
	return isEmpty(document.forms[0].patDOB,"Date of Birth");}
}



/*

 function Validate()
{ 
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
	 && validateDot(document.forms[0].patMiddleName,"Middle Name")
	 && (client==clientSms ?(isEmpty(document.forms[0].patLastName,"Last Name")):true )
	 && validateDot(document.forms[0].patLastName,"Last Name")
	 && checkAgeOrDob()
	 && isValid(document.forms[0].patAge,"Age",125) 
	 && isEmpty(document.forms[0].patAgeUnit,"Age Unit")
	 && validateDateAgainstSysDate(document.forms[0].patDOB,"Date of Birth")
	 && comboValidation(document.forms[0].patGenderCode,"Gender")
	 && validateDot(document.forms[0].patGuardianName,"Father Name")
	 && validateDot(document.forms[0].patHusbandName,"Husband Name")
	 && validateDot(document.forms[0].patMotherName,"Mother Name")
	 && comboValidation(document.forms[0].patNationalityCode,"Nationality")
	 && checkState()
	 && validateLocation()
	 && checkDistrict()	
	 && validateDot(document.forms[0].patAddCityLoc,"Location")
	 && validateDot(document.forms[0].patAddHNo,"House Number")
	 && validateDot(document.forms[0].patAddStreet,"Street")
	 && validateDot(document.forms[0].patAddDistrict,"District")
	 && validateDot(document.forms[0].patAddCity,"City")
	 && validateMinimumLength(document.getElementById("pintext"),'Pin Number','6') 
	 && validatePinNumber(document.getElementById("pintext"))
	 && checkIsrefer()
	 && isEmpty(document.forms[0].patRefDoctor,"Referred By Doctor") 
	  <his:madatory property="departmentCode" /> 
	  ) 
	 
	 {
	  valid=true;
	 }
	       
	 else
	 valid=false;     
	 }

   //alert(valid);
	return valid;    
	}

*/

/*
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
 
 */
 



