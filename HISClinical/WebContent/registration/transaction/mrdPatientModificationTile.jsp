<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>



<%@page import="hisglobal.hisconfig.Config"%>
<his:css src="/hisglobal/css/calendar-blue2.css"/>


<his:javascript src="/registration/js/registration.js" />
<his:javascript src="/registration/js/calendar.js" />
<his:javascript src="/registration/js/validationCommon.js" />
<his:javascript src="/registration/js/validationCalls.js" />
<his:javascript src="/registration/js/commonFunctions.js" />
<his:javascript src="/registration/js/registration.js" />
<his:javascript src="/registration/js/popup.js" />
<his:javascript src="/registration/js/dateFunctions.js" />
<his:javascript src="/registration/js/stateChangeAjax.js" />
<his:javascript src="/registration/js/cityLocationAjax.js" />

<his:javascript src="/hisglobal/js/utilityFunctions.js" />
<his:javascript src="/hisglobal/utility/generictemplate/js/validationFunctions.js"/>
<his:javascript src="/hisglobal/utility/generictemplate/js/date_validator.js"/>

<%@ page import ="java.util.*,registration.*,hisglobal.vo.*,hisglobal.presentation.*,registration.controller.fb.mrdPatientDetailModFB" %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<script>
function captureImage(e){
	document.getElementsByName("isNewImageUploaded")[0].value="1";
	openPopup('<his:path src="/registration/uploadFile.cnt"/>',e,300,600);

}

function enableRelation()
{	
	 
	var obj=document.getElementsByName("requestBy")[0]
	if(obj){
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
		//document.getElementById("designationLabel").style.display="block";
		//document.getElementById("designationControl").style.display="block";
		//document.getElementById("badgeNoLabel").style.display="block";
		//document.getElementById("badgeNoControl").style.display="block";
	}
	else {
		document.getElementById("policeDetailDiv").style.display="none";
		document.getElementById("requesterDIV").style.display="none";
			//document.getElementById("designationLabel").style.display="none";
			//document.getElementById("designationControl").style.display="none";
			//document.getElementById("badgeNoLabel").style.display="none";
			//document.getElementById("badgeNoControl").style.display="none";
		document.getElementById("relationshipSelfId").style.display="block";	
		document.getElementById("relationshipRelativeId").style.display="none";	
	}
	}
}

function enableSpouseField()
{
// alert("sdsdsdsdsdsds")
	if(document.getElementsByName("patMaritalStatusCode")[0]){
	var maritalStatus=document.getElementsByName("patMaritalStatusCode")[0].value;
	var clientValue="<%=Config.CLIENT%>"; 
	var clientPGIMER="<%=Config.CLIENT_PGIMER%>";
	var maritalStatusSingle="<%=RegistrationConfig.REGISTRATIONDESK_MARITAL_STATUS_SINGLE%>";
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

function checkEmployee()
{ 			var patIdNo=document.getElementsByName("patIdNo")[0].value;
			// alert(patIdNo)
			var clientValue="<%=Config.CLIENT%>"; 
			var patCategoryCode=document.forms[0].patPrimaryCatCode.value
			var patEmployeeCode="<%=Config.PRIMARY_CATEGORY_EMPLOYEE_CODE %>";
			//alert("Primarg category = "+patCategoryCode+" "+patEmployeeCode )
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
}

function callThisOnload(){ 
	if(document.getElementsByName("patCrNo")[0]){
		document.getElementsByName("patCrNo")[0].focus();
	}
	showPoorPatDiv('<%=Config.PRIMARY_CATEGORY_POOR_FREE_CODE%>');	
	//enableSpouseField();
	enableRelation()
	checkEmployee();
	showState();
	showLocation();
	// focusCrNo();
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
	 //alert("default country")
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
	 //alert("not default country")
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

function  showdelhidiv()
  {

	
 	<his:statusInProcessWithJsp>
 	document.getElementsByName("patFirstName")[0].focus();
 	  <logic:equal name="mrdPatientDetailModFB" property="isAddressDelhi" value="1">
	   document.getElementById("divpatAddCityLocCode").style.display="";   	  
	   document.getElementById("divpatAddCityLocation").style.display="none";
	 //    document.getElementsByName("patAddStateCode")[0].disabled=true;   
      //  document.getElementsByName("patAddCountryCode")[0].disabled=true;
	   document.getElementsByName("patAddStateCode")[0].value="<%=RegistrationConfig.REGISTRATIONDESK_DEFAULT_STATE_CODE %>";   
       document.getElementsByName("patAddCountryCode")[0].value="<%=RegistrationConfig.REGISTRATIONDESK_DEFAULT_COUNTRY_CODE%>"; 
	</logic:equal>	
	
	 document.getElementsByName("patPrimaryCatCode")[0].disabled="true";
	 document.getElementsByName("patSecondaryCatCode")[0].disabled="true";
    </his:statusInProcessWithJsp>
   
 }
 
 function doHomeWork(){
 //alert("home work");
 <his:statusInProcessWithJsp>
 //alert(" in process  home work");
 // document.getElementsByName("patPrimaryCatCode")[0].disabled=false;
 // document.getElementsByName("patSecondaryCatCode")[0].disabled=false;
 </his:statusInProcessWithJsp>
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
      document.getElementById('hiddenDivVerificationId').style.display="block";
      
	  //elem.style.display="";
      // alert(elem.innerHTML);
      //document.getElementById("hiddenDivVerification").style.display="";
}   


function populate1(e,d){ 
	document.getElementsByName('crNoToRetrieve')[0].value=e;
	submitForm("DGNDETAIL"); 
} 
function submit4image(){
	
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
 
function validateRequestDetail()
 {
  
 if(isSelected(document.getElementsByName('requestBy')[0],'Request By') && 
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
 		if( isEmpty(document.getElementsByName('requestByName')[0],'Requester Name') &&
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
 			isEmpty(document.getElementsByName('requestByAddress')[0],'Requester Address'))
 			{
 				valid= true
 			}else
 			{
 				valid= false
 			}
 	}
 	return valid
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
 
// Changed on 30-Oct-2009
function ValidatePatientDtlModification()
{
	var valid=true;
	var patCatEmpFieldValue="<%=Config.PATCAT_EMPLOYEE_FIELD_VALUE%>";
	var patCatEmpFieldEditfalse="<%=Config.PATCAT_EMPLOYEE_FIELD_EDITABLE_FALSE%>";
	var patIdNo=document.getElementsByName("patIdNo")[0].value;
	var dataFromTableValue="<%=Config.PATCAT_EMPLOYEE_DATA_FROM_TABLE_VALUE%>";
	var dataFromTableTrue="<%=Config.PATCAT_EMPLOYEE_DATA_FROM_TABLE_TRUE%>";
	var patCategoryCode=document.forms[0].patPrimaryCatCode.value;
	var patEmployeeCode="<%=Config.PRIMARY_CATEGORY_EMPLOYEE_CODE %>";
	if( (patCategoryCode==patEmployeeCode) && ( (patCatEmpFieldValue==patCatEmpFieldEditfalse) || (dataFromTableValue==dataFromTableTrue) ))
	{
		if( validateDot(document.forms[0].patAddCityLoc,"Location")
			//&& validateLocation()
			&& validateRequestDetail()
			&& validateDocumentAdded()
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
			&& validateDot(document.forms[0].patFirstName,"First Name") 
			&& validateSpecialCharacter('/',document.forms[0].patFirstName,"First Name")
			&& validateSpecialCharacter('-',document.forms[0].patFirstName,"First Name")
			&& validateDot(document.forms[0].patMiddleName,"Middle Name") 
			//&& isEmpty(document.forms[0].patLastName,"Last Name")
			&& validateDot(document.forms[0].patLastName,"Last Name") 
			//&& isEmpty(document.getElementsByName("patIdNo")[0],"Patient Category Id")
			//&& isEmpty(document.forms[0].patGuardianName,"Father Name")
			&& validateFatherNameSpouseName()
			&& validateDot(document.forms[0].patGuardianName,"Father Name") 
			&& validateDot(document.forms[0].patHusbandName,"Husband Name") 
			&& validateDot(document.forms[0].patMotherName,"Mother Name") 
			&& isValid(document.forms[0].patAge,"Age",125) && isEmpty(document.forms[0].patAgeUnit,"Age Unit")
			
			//&& validateDateAgainstSysDate(document.forms[0].patDOB,"Date of Birth") 
			&& validatePatientDOBAgainstSysDate()
			
			&& checkAgeOrDob()
			&& isSelected(document.forms[0].patGenderCode,"Gender")
			&& isSelected(document.forms[0].patNationalityCode,"Nationality")
			&& isEmpty(document.forms[0].patReligionCode,"Religion")
			&& validateMonthlyIncome(document.getElementsByName("patMonthlyIncome")[0],'<%=RegistrationConfig.BPL_MONTHLY_INCOME%>','<%=Config.PRIMARY_CATEGORY_POOR_FREE_CODE%>')
			&& isSelected(document.forms[0].patPrimaryCatCode,"Patient Category")
			&& isSelected(document.forms[0].patAddCountryCode,"Country")
			&& checkState()
			//&& validateLocation()
			&& validateDot(document.forms[0].patAddCityLoc,"Location")
			&& isEmpty(document.forms[0].patAddHNo,"Address")
			&& validateDot(document.forms[0].patAddHNo,"Address")
			//&& validateDot(document.forms[0].patAddStreet,"Street")
			&& validateDot(document.forms[0].patAddDistrict,"District")
			&& isEmpty(document.forms[0].patAddCity,"City / Village")
			&& validateDot(document.forms[0].patAddCity,"City / Village")
			&& validateMinimumLength(document.forms[0].patAddPIN,'Pin Number','6')
			&& validatePinNumber(document.forms[0].patAddPIN)
			//&& isEmpty(document.forms[0].patAddContactNo,"Contact No")
			&& validateRequestDetail()
			&& validateDocumentAdded()
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

// Changed on 30-Oct-2009
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
	  document.getElementsByName("patGuardianName")[0].value="";
	  document.getElementsByName("patHusbandName")[0].value="";
	  document.getElementsByName("patMotherName")[0].value="";
	  document.getElementsByName("patMaritalStatusCode")[0].value="-1";
	  document.getElementsByName("patHusbandOccupation")[0].value="-1";
	  //enableSpouseField();
	  document.getElementsByName("patNationalityCode")[0].value="-1";
	  document.getElementsByName("patReligionCode")[0].value="-1";
	  document.getElementsByName("patIsUrban")[0].value="-1";
	  document.getElementsByName("patMonthlyIncome")[0].value="";
	  document.getElementsByName("patAddCountryCode")[0].value="-1";
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
		document.forms[0].patAddContactNo.value=""
		document.getElementsByName("patIsUrban")[0].value=""
		document.getElementsByName('requestBy')[0].value="-1"
		document.getElementsByName('requestRelation')[0].value="-1"
		document.getElementsByName('requestByName')[0].value=""
 		document.getElementsByName('requestByAddress')[0].value=""
 		document.getElementsByName('constableDesig')[0].value=""
 		document.getElementsByName('constableBadgeNo').value=""
 		enableRelation();
} 
 
</script>
<%
boolean varIsNewStatus=false;	
String varStatus="";
%>

 <% 
 
 
 String divpatAddCityLocCode="\"\"";
 String divpatAddCityLocation="\"\"";
 String divisrefdisplay="\"\"";
 String divRefGnctdHospitalCode="\"\"";
 String divRefHospname="\"\"";
 String strdivage="\"\"";
 String strdivdob="\"\"";
 String RefGnctdHosNameradio=""; 
 String RefGnctdHoscoderadio="";       
 String divRefInstitute="\"\"";     
 String gnctdrefDetails="\"\"";     
 String divDocTitle="\"\"";     
 String divDocName="\"\"";           
 
%>
<%
           String divdisplay="\"\"";
		   String divnddisplay="\"\"";
		   String systemDate=hisglobal.presentation.WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy HH:mm");  
  %>
<his:statusNew>
<%varIsNewStatus=true;
varStatus="New";%>	

</his:statusNew>
<his:TitleTag name="Patient Detail Modification">
	 <bean:define id="clientFlag" name="<%=Config.CLIENT_NAME%>"></bean:define>
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
	
<b>
<font size="2" face="Verdana, Arial, Helvetica, sans-serif">

</font>
</b>

</his:TitleTag>
<his:InputCrNoTag name="mrdPatientDetailModFB"></his:InputCrNoTag>

<bean:define id="crNo" name="mrdPatientDetailModFB" property="patCrNo" type="java.lang.String"/>
<%if(!crNo.trim().equals("")){%>
<his:statusTransactionInProcess>

  <his:ContentTag>
  <table width="100%" border="0" cellspacing="1" cellpadding="0">
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
					name="mrdPatientDetailModFB" property="patPrimaryCat" tabindex="1"
					styleClass="regcbo" readonly="true"></html:text></font>
		</td>
		<td width="15%" nowrap class="tdfonthead"></td>
		<td width="15%" nowrap class="tdfont"></td>			
		<td width="15%" nowrap class="tdfonthead"></td>
		<td width="15%" nowrap class="tdfont"></td>			
   </tr>				
   <tr id="poorPatTR">
  	  <td width="19%" class="tdfonthead">
  	  <div align="right" id="mmjrkDiv" style="display: none;"><font color="#000000" size="2"
					face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
					key="mmjrkNo" /> </font></div>
		
	 </td>
	
	 <td class="tdfont" colspan="1">
		 <div align="left" id="mmjrkTextDiv" style="display: none;">
		 <html:text
					name="mrdPatientDetailModFB" property="patCardNo"
					styleClass="textbox" maxlength="30" tabindex="1"
					onkeypress="return validateAlphaNumericWithDotsOnly(event,this)"
					 />
	
	 </div>	  
     </td>
   	 <td width="14%" height="25" nowrap  class="tdfonthead">
	    <div align="right" id="familyHeadDiv" style="display: none;"><font size="2" color="#000000" style="display:block"
					face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
					key="familyHeadName" /> </font></div>
        </td>
         
        <td width="17%" class="tdfont" >
	        <div style="display:none" id="familyHeadTextDiv">
				<html:text name="mrdPatientDetailModFB"
					property="patNickName" tabindex="1" styleClass="textbox"
					maxlength="30" onchange="isAlpha(this,'Nick Name')"
					onkeypress="return validateAlphabetsWithDotsOnly(event,this)" />
				</div>
        </td>
        
       <td width="17%" class="tdfonthead" nowrap="nowrap" >
				<div align="right" id="relationDiv" style="display: none;"><font color="#000000" size="2"
					face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
					key="relation" /> </font></div>
				</td>
				

				<td width="17%" class="tdfont" nowrap="nowrap" >
					<div id="relationComboDiv" style="display: none;">
					<html:select name="mrdPatientDetailModFB" tabindex="2" property="patHusbandOccupation"  styleClass="regcbo" >
		            <html:option value="">Select Value</html:option>
				  	<html:option value="28">Aunt</html:option>
					<html:option value="16">Brother</html:option>
					<html:option value="23">Brother In-Law</html:option>
					<html:option value="19">Cousin</html:option>
					<html:option value="18">Daughter</html:option>
					<html:option value="24">Daughter In-Law</html:option>
					<html:option value="11">Father</html:option>
					
					<html:option value="20">Father In-Law</html:option>
					<html:option value="30">Granddaughter</html:option>
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
   <tr><%varStatus="InProcess";%>
        <td width="14%" height="25" nowrap  class="tdfonthead">
	    <div align="right">
	    <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"><bean:message key="patient"/>
	    <bean:message key="name"/></font>
	    </div>
        </td>
         
        <td width="17%" class="tdfonthead">
	         <div align="left">
			    <font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font>
			    <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			    <bean:message key="first"/>
			    </font>
		    </div>
        </td>
        
        <td width = "18%" class="tdfonthead" >
	    <div align="left">
	    <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif" style="display:block">
	    <bean:message key="middle"/>
	    </font>
	    </div>
        </td>
       
        <td width="19%" class="tdfonthead">
			<div align="left"><font color="#000000" size="2"
			face="Verdana, Arial, Helvetica, sans-serif">
			<bean:message key="last" /> </font></div>
		</td>
		
		<td class="tdfonthead" colspan="1">
		<div align="right">
		<img class="button" style="cursor:pointer" src='<his:path src="/hisglobal/images/camera.png"/>' alt="Capture Photo" title="Capture Photo" onkeypress="if(event.keyCode==13) captureImage(event)" onclick="captureImage(event)">
		</div>	  
	    </td>
		
		<td class="tdfont" width="18"><img class="button" style="cursor:pointer" src='<his:path src="/hisglobal/images/view.psd.gif"/>' alt="View Photo" title="View Photo" onkeypress="if(event.keyCode==13) openPopup('<his:path src="/registration/enlargedImage.cnt"/>',event,300,600)" onclick="openPopup('<his:path src="/registration/enlargedImage.cnt"/>',event,300,600)">
		</td>
       	<!-- <td class="tdbackground">
        <div align="center0">
        <table border="1" width="50%" height="70%">
	    <tr>
	    <td>
	    <img style=cursor:pointer src="<his:path src='/hisglobal/image/showImage'/>" height="100%" width="100%" title="Click To Enlarge" onclick="openPopup('<his:path src="/enlargedImage.cnt"/>',event)">
	    </td>
         </tr>
	    </table>
	    </div>
	    </td>
     	</tr>-->
				
      	<tr> 
        <td width="14%" class="tdfonthead" >&nbsp;</td>
        <td width="17%"	 class="tdfont">
        <html:text  name="mrdPatientDetailModFB"  property="patFirstName" styleClass="textbox" tabindex="1" maxlength ="30" onblur="(this,'First Name')" onkeypress="return validateAlphabetsWithDotsAndForwardSlashWithHypen(event,this)"/>
        </td>
        
        <td width="18%"  class="tdfonthead">
        <div align="left">
        <html:text  name="mrdPatientDetailModFB" property="patMiddleName"  tabindex="1" styleClass="textbox" onblur="isAlpha(this,'Middle Name')" maxlength ="20" onkeypress="return validateAlphabetsWithDotsOnly(event,this)" style="display:block"/>
        </div>
        </td>
        
        <td class="tdfont">
        <html:text  name="mrdPatientDetailModFB" property="patLastName" tabindex="1" styleClass="textbox" onblur="isAlpha(this,'Last Name')" maxlength ="30" onkeypress="return validateAlphabetsWithDotsOnly(event,this)"/>
        </td>
        
       	<td width="17%" class="tdfonthead" nowrap="nowrap" >
				<div align="right" id="patientCatDiv" style="display: none;"><font color="#000000" size="2"
					face="Verdana, Arial, Helvetica, sans-serif"><font color="#FF0000"
					size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font> <bean:message
					key="patientCategoryId" /> </font></div>
				</td>
				<td width="17%" class="tdfont" >
					<div id="patientCatTextDiv" style="display: none;">
					<html:text name="mrdPatientDetailModFB" onkeypress="return validateAlphaNumericOnly(event);"
						styleClass="textbox" property="patIdNo" maxlength="16"
						tabindex="1" ></html:text>
					</div>	
				</td> 
		</tr>
        
         <tr> 
        
          
     <td width="17%"  nowrap  class="tdfonthead" > 
	 <div align="center">
	 <font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font>
     <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
     <bean:message key="age"/>
     
     </font>
     <html:radio name="mrdPatientDetailModFB" property="isActualDob" tabindex="1" value="0" onclick="ageModificationSelection()"/>    
   
     <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> <bean:message key="dob"/></font>
     <html:radio name="mrdPatientDetailModFB" property="isActualDob" tabindex="1" value="1" onclick="ageModificationSelection()"/>
     </div>
     </td>
     <logic:empty name="mrdPatientDetailModFB" property="isActualDob">
     
     <%
               strdivage="none";
               strdivdob="none";                
     %>
     
     </logic:empty>
          
     <logic:equal name="mrdPatientDetailModFB" property="isActualDob" value="1">    
     
     <%
     		 
             strdivage="none";
             strdivdob="block";           
     %>               
     
     </logic:equal> 
     <logic:equal name="mrdPatientDetailModFB" property="isActualDob" value="0">
     
     <%
           System.out.println("inside case 0isActualDob:::::::::");	 
           strdivage="block";
           strdivdob="none";           
          %>               
      </logic:equal>                    
     
     <td width="18%" nowrap class="tdfont" >
       <div id="divAge" style='display:<%=strdivage%>'>
        <html:text  name="mrdPatientDetailModFB" size="4" tabindex="1" property="patAge"  maxlength ="3" onkeypress="return validateNumeric(event)"/> 
          <html:select name="mrdPatientDetailModFB" property="patAgeUnit" tabindex="1" styleClass="smallcombo">
          <html:option value="-1">Select Value</html:option>
        <html:options collection = "<%=RegistrationConfig.ESSENTIALBO_OPTION_AGE_TYPE %>" property = "value" labelProperty = "label"/>
        </html:select>
        </div>
       <%//String strEmpdivdob="none"; 
		// if(((mrdPatientDetailModFB)pageContext.findAttribute
		//	 ("mrdPatientDetailModFB")).getPatPrimaryCatCode().equals(Config.PRIMARY_CATEGORY_EMPLOYEE_CODE)){
		//	 strEmpdivdob="";
		//	 strdivdob="none";
		// }%>         
		<div id="divDob" style='display:<%=strdivdob%>'>         
        	<bean:define name="mrdPatientDetailModFB" property="patDOB" id="dob" type="java.lang.String"/>
                       
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
        <font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font>
        <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
        <bean:message key="gender"/></font>
        </div>
        </td>
        <td width="17%"	class="tdfont" > 
        <html:select name="mrdPatientDetailModFB"  property="patGenderCode" tabindex="1" styleClass="regcbo" >	
        <html:option value="-1">Select Value</html:option>
	    <html:options collection = "<%=RegistrationConfig.ESSENTIALBO_OPTION_GENDER %>" property  = "value" labelProperty = "label"/>
		</html:select>
        </td>
         <td width="14%"  class="tdfonthead">
		 <div align="right">
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	        <bean:message key="monthlyIncome"/>
	        </font>
	     </div> 
      	 </td>
	    <td class="tdfont">
		<html:text  name="mrdPatientDetailModFB" property="patMonthlyIncome" 
		onkeypress="return(currencyFormat(this,',','.',event))" styleClass="textbox" tabindex="1"  maxlength="11"/>
        </td>  
     
     </tr> 	  
   
    <tr> 
        
      	<td width="14%" height="25" nowrap  class="tdfonthead">
	    <div align="right">
	    <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	    <font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font>
	    <bean:message key="fatherName"/>
	   </div>
        </td>
        <td width="17%"	 class="tdfont">
        <html:text  name="mrdPatientDetailModFB"  property="patGuardianName" styleClass="textbox" tabindex="1" maxlength ="60" onblur="isAlpha(this,'Father Name')" onkeypress="return validateAlphabetsWithDotsOnly(event,this)"/>
        </td>
     
        <td width="14%" height="25" nowrap  class="tdfonthead"><div align="right"><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"><bean:message key="husbandName"/></font> </div></td>
        <td class ="tdfont" width ="17%">        
        <html:text  name="mrdPatientDetailModFB" property="patHusbandName"  tabindex="1" styleClass="textbox"  maxlength ="60" onkeypress="return validateAlphabetsWithDotsOnly(event,this)"/>       
        </td>
        <td width="18%" height="25" nowrap  class="tdfonthead">
	    <div align="right">
	    <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"><bean:message key="motherName"/></font>
	   </div></td>
       <td class ="tdfont" width ="17%">        
        <html:text  name="mrdPatientDetailModFB" property="patMotherName"  tabindex="1" styleClass="textbox" onblur="isAlpha(this,'Mother Name')" maxlength ="60" onkeypress="return validateAlphabetsWithDotsOnly(event,this)"/>       
        </td>
        </tr>
   
   
       <tr>                 
	    <td  class="tdfonthead" width="18%">
		    <div align="right">
			    <font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font>
		        <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	            <bean:message key="nationality"/>
	            </font>
	        </div>        
        </td>                
	    <td  class="tdfont" width="17%" >   
		          <div id=patCountryCombo>
        		       <html:select name="mrdPatientDetailModFB" property="patNationalityCode" tabindex="1" styleClass="regcbo" >
                   <html:option value="-1">Select Value</html:option>
		  		   <html:options collection ="<%=RegistrationConfig.ESSENTIALBO_OPTION_NATIONALITY %>"property = "value" labelProperty = "label"/>
                        </html:select>	
                 </div>
	     </td>
       
	      
                     
              <td width="14%"  class="tdfonthead" ><div align="right">
              <font color="#FF0000" size="1"
					face="Verdana, Arial, Helvetica, sans-serif">*</font>
              <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
        			<bean:message key="religion"/></font></div>
	          </td>
        
	         <td width="17%"  class ="tdfont" >
	          <html:select name="mrdPatientDetailModFB" property="patReligionCode" tabindex="1" styleClass="regcbo">		  <html:option value="">Select Value</html:option>
     			   <html:options collection = "<%=RegistrationConfig.ESSENTIALBO_OPTION_RELIGION %>" property = "value" labelProperty = "label"/>
              </html:select> 
             </td>         
        
           <td width="17%" class="tdfonthead">
				<div align="right"><font color="#000000" size="2"
					face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
					key="category" /> </font></div>
				</td>
		<td width="17%" class="tdfont"><html:select
			name="mrdPatientDetailModFB" property="patMaritalStatusCode" 
			tabindex="2" styleClass="regcbo">
			<html:option value="-1">Select Value</html:option>
			<html:options collection="<%=RegistrationConfig.ESSENTIALBO_OPTION_MARITAL_STATUS %>" property="value" labelProperty="label" />
			</html:select>
		</td>   
	 </tr>
	 <logic:equal name="clientFlag" value="<%=Config.CLIENT_PGIMER %>">       
     <tr>
				
				<td width="17%" class="tdfonthead" nowrap="nowrap" >
			<div  align="right"><font color="#000000" size="2"
					face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
					key="patOccupation" /> </font></div>
				</td>
				<td width="17%" class="tdfont">  
					<html:select name="mrdPatientDetailModFB" tabindex="1" property="patOccupation"  styleClass="regcbo" >
		            <html:option value="">Select Value</html:option>
				  	<html:options collection ="<%=RegistrationConfig.ESSENTIALBO_OPTION_OCCUPATION_DTL %>"property = "value" labelProperty = "label"/>
                    </html:select>	
				</td>
				
				<td width="17%" class="tdfonthead" nowrap="nowrap" >
				<div align="right"><font color="#000000" size="2"
					face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
					key="patFatherOccupation" /> </font></div>
				</td>

				<td width="17%" class="tdfont">  
					<html:select name="mrdPatientDetailModFB" tabindex="1" property="patFatherOccupation"  styleClass="regcbo" >
		            <html:option value="">Select Value</html:option>
				  	<html:options collection ="<%=RegistrationConfig.ESSENTIALBO_OPTION_OCCUPATION_DTL %>"property = "value" labelProperty = "label"/>
                    </html:select>	
				</td>
				
				<td width="17%" class="tdfonthead" nowrap="nowrap" >
			
				</td>
				

				<td width="17%" class="tdfont" nowrap="nowrap" >
					
				</td>
				
				
				<td class="tdfonthead" width="17%"></td>
				<td class="tdfont" width="17%"></td>
			</tr>
		</logic:equal>
	 
	 
               <tr>       
		 	 <td width="14%"  class="tdfonthead" >
		 	 <div align="right">
	       	<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	        	<bean:message key="uniqueId"/>	     
	        </font>
               </div> 
             </td>
    		  <td width="17%"  class="tdfont" > 
                <html:text name="mrdPatientDetailModFB" property="patNationalId" styleClass="textbox"  maxlength ="16" tabindex="1" onkeypress="return validateAlphaNumericWithSpecialCharacterOnly(event,this)" styleClass="textbox"/>            
              </td>       
            <logic:equal name="clientFlag" value="<%=Config.CLIENT_PGIMER %>">      
  			<td width="17%" class="tdfonthead" nowrap="nowrap" >
				
				</td>
				
				<td width="17%" class="tdfont" ></td> 
	     </logic:equal>
	      <td width="14%"  class="tdfonthead" >
		 	 </td>
    		  <td width="17%"  class="tdfont" nowrap> 
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
 			 	       <html:select name="mrdPatientDetailModFB" tabindex="1" property="patAddCountryCode"  styleClass="regcbo" onchange="showState()">
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
                     <html:select name="mrdPatientDetailModFB" tabindex="1" property="patAddStateCode" styleClass="regcbo" onchange="if(this.value!='-1') sendDataForStateChange(this)" >
                       <html:option value="-1">Select Value</html:option>
                       <html:options collection = "<%=RegistrationConfig.ESSENTIALBO_OPTION_STATE %>" property = "value" labelProperty = "label"/>
                     </html:select>    
                     </div> 
                     <div id="divpatAddStateCodeText" style="" >
                     <html:text name="mrdPatientDetailModFB" tabindex="1" property="patAddStateName" styleClass="regcbo" maxlength="50" onkeypress="return validateAlphabetsWithDotsOnly(event,this)">
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
	                     <bean:message key="address"/>
	                     </font> 
                    </div>                    
                    </td>        
                                
                <td  class="tdfont" width="17%" colspan="3">
                 <html:textarea  name="mrdPatientDetailModFB" onkeypress="return CheckMaxLength(event,this,100,1);" 
                		property="patAddHNo" tabindex="2" cols="55" rows="2"/>
                	<input type="hidden" name="patAddStreet">
                </td>
               
	             <td  class="tdfonthead" width="17%">
	            <div align="right">
	            
	            <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	            <bean:message key="tehsil"/>
	            </font>
	            </div>
             </td>
 
 
		       <td  class="tdfont" width="17%">
		                <div id="divpatAddCityLocation" >	                     
						<html:text  name="mrdPatientDetailModFB" tabindex="2"  maxlength="50" onkeypress="return validateAlphaNumericWithDotsOnly(event,this);" styleClass="textbox" property="patAddCityLoc"/>
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
                <html:select name="mrdPatientDetailModFB" tabindex="1" styleClass="regcbo" property="patAddDistrictCode">
				<html:option value="-1">Select Value</html:option>
				<html:options collection ="<%=RegistrationConfig.ESSENTIAL_BO_OPTION_DISTRICT_LIST_STATEWISE%>" property = "value" labelProperty = "label"/>
	            </html:select>
                </div>
                <div id="districtTextBox" style="display:none">
                <html:text  name="mrdPatientDetailModFB" tabindex="1" property="patAddDistrict" onchange="isAlpha(this,'District')"  onkeypress="return validateAlphabetsWithDotsOnly(event,this)" maxlength="30" styleClass="textbox"/>
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
	            <html:text name="mrdPatientDetailModFB" tabindex="1" property="patAddCity" onchange="isAlpha(this,'City')" onkeypress="return validateAlphabetsWithDotsOnly(event,this);" maxlength="30" styleClass="textbox"/> 
                </td>
                
                <td class="tdfonthead" width="14%">
	            <div align="right">
	            <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	            <bean:message key="pin"/>
	            </font> 
	            </div>
                </td>       
                <% 
                String pinCode=((mrdPatientDetailModFB)pageContext.findAttribute("mrdPatientDetailModFB")).getPatAddPIN();
               if(pinCode==null)
            	   {
            	   pinCode="";
            	   }%>  
                <td  class="tdfont" width="17%">
                <input type="text" id="pintext"  name="patAddPIN" tabindex="1" value="<%=pinCode %>"
                 size="17"  maxlength ="6"  onkeypress="return validateNumeric(event)"  /> 
                  </td>
                                                    
                    
              </tr>
                    
              <tr style="display: none;">
                      
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
         		    <html:text name="mrdPatientDetailModFB" tabindex="1" property="patAddContactNo"  maxlength ="30" styleClass="textbox" onkeypress="return validateNumeric(event)"/> 
                    </div>
                    </td>
                    
             <td width="18%" class="tdfonthead">
				<div align="right"><font color="#000000" size="2"
					face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
					key="areaCategory" /> </font></div>
				</td>

				<td width="18%" class="tdfont"><html:select
					name="mrdPatientDetailModFB" property="patIsUrban" tabindex="1"
					styleClass="regcbo">
					<html:option value="">Select Value</html:option>
					<html:options
						collection="<%= RegistrationConfig.ESSENTIALBO_OPTION_AREA_CATEGORY %>"
						property="value" labelProperty="label" />
				</html:select></td> 
          		<td class="tdfonthead" width="18%"><div align="right"><font color="#000000" size="2"
								face="Verdana, Arial, Helvetica, sans-serif"> <font
								color="#FF0000" size="1"
								face="Verdana, Arial, Helvetica, sans-serif">*</font> <bean:message	key="verificationDocument" /> </font></div>
							</td>
              		<td class="tdfont" width="18%"><div align="left"><img class="button"
								src='<his:path src="/hisglobal/images/icon-vrf.png"/>'
								style=cursor:pointer alt="Verification Documents"
								title="Verification Documents"
								onclick="openPopup('<his:path src="/registration/verificationDocumentPopup.cnt"/>',event,300,600);"
								onkeypress="if(event.keyCode==13) {openPopup('<his:path src="/registration/verificationDocumentPopup.cnt"/>',event,300,600)}"
								size='7' tabindex="1"></div></td>
              		<td class="tdfont" width="36%"></td>
              		
              		<td class="tdfonthead" width="18%"></td>
          		    <td class="tdfont" width="18%"></td>
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
        	<html:select name="mrdPatientDetailModFB" property="requestBy" styleClass="regcbo" onchange="enableRelation()" tabindex="1">
        		<html:option value="-1">Select Value</html:option>
        		<html:option value="<%=RegistrationConfig.MODIFICATION_REQUESTED_BY_SELF %>">Self</html:option>
        		<html:option value="<%=RegistrationConfig.MODIFICATION_REQUESTED_BY_RELATIVE %>">Relative</html:option>
        		<%String mlcNo=((mrdPatientDetailModFB)pageContext.findAttribute("mrdPatientDetailModFB")).getMlcNo();
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
       </div></td>
       
       <td width="25%" class="tdfont"  >
       <html:select name="mrdPatientDetailModFB" property="requestRelation" tabindex="1" styleClass="regcbo" tabindex="1">
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
        <font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font>
	        <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	          <bean:message key="requestName"/>      
	        </font>
        </td>        
        <td width="25%" class="tdfont" > <!-- 18 -->
             <html:text name="mrdPatientDetailModFB" tabindex="1" property="requestByName" tabindex="1"  onkeypress="return validateAlphabetsWithDotsOnly(event,this)" styleClass="textbox"  maxlength="60"/>
        </td>
         
       <td width="25%" class="tdfonthead">
       <font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font>
          	<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
             <bean:message key="requestByAddress"/>
       		 </font>
       	</td>
      
       	<td width="25%"  class="tdfont"><!-- 3 -->
            <html:textarea name="mrdPatientDetailModFB" property="requestByAddress" tabindex="1"  onkeypress="return CheckMaxLength(event,this,100,3)"  tabindex="1" 
            style="width: 180px;font-family: Verdana, Arial, Helvetica, sans-serif;font-size: 12;height:40px;color: #000000;"/>
        </td>
        
      </tr>
      </table>
      </div>
      <div id="policeDetailDiv" >
	      <table width="100%" cellspacing="1" cellpadding="0">
		     <tr >
		      	<td width="25%" class="tdfonthead" >
		      	<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font>
		      		 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				       <bean:message key="designation"/>
				      </font>
		      	</td>
		      	<td  width="25%" class="tdfont">
		      		<html:text name="mrdPatientDetailModFB" property="constableDesig" tabindex="1" styleClass="textbox" maxlength="50" onkeypress="return validateAlphaNumericWithDotsOnly(event,this)"></html:text>
		      	</td>
		      	<td   width="25%" class="tdfonthead" >
		      	<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font>
		      		 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				       <bean:message key="batchNo"/>
				      </font>
		      	</td>
		      	<td  width="25%" class="tdfont" >
		      		<html:text name="mrdPatientDetailModFB" tabindex="1" property="constableBadgeNo" styleClass="textbox" maxlength="50" onkeypress="return validateAlphaNumericWithDotsOnly(event,this)"></html:text>
		      	</td>
		      </tr>
	      
	      </table>
      </div>
	      
</his:ContentTag>
 </his:statusTransactionInProcess>
                     
<%
  }

%>
 <his:ButtonToolBarTag>
   <%if(varStatus.equals("InProcess")){%>
<table width=100%>
					<tr>
					<td width='45%' align='right'>
					<div id='saveButton' >
					<img class="button"  src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>' tabindex="1" style='cursor:pointer' onclick = "submitFormOnValidate(ValidatePatientDtlModification(),'SAVE');" onkeypress="if(event.keyCode==13) submitFormOnValidate(ValidatePatientDtlModification(),'SAVE');">
					</div>
					</td>
	         		<td width='55%' align='left'><img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' tabindex="1"  style=cursor:pointer onkeypress="if(event.keyCode==13) submitForm('NEW');" tabindex="1" onclick ="submitForm('NEW');">
	         		<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style=cursor:pointer tabindex="1" onclick ="clearForm()" onkeypress="if(event.keyCode==13) clearForm();">
	         		</td>
        			</tr>
</table>

 <%} else{ %>

          <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' tabindex="1"  style=cursor:pointer onkeypress="if(event.keyCode==13) submitPage('CANCEL');" tabindex="1" onclick ="submitPage('CANCEL');">
          <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' tabindex="1" style=cursor:pointer  onclick ="submitForm('NEW')" onkeypress="if(event.keyCode==13) submitForm('NEW');">
	  <%} %>
</his:ButtonToolBarTag>
 <his:status/>
<%
     String	sysDate = hisglobal.presentation.WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy");
 %>
<%System.out.println("....2");%>
<html:hidden name="mrdPatientDetailModFB" property="patIdNo"/>
<html:hidden name="mrdPatientDetailModFB" property="hmode" value="unspecified" />
<html:hidden name="mrdPatientDetailModFB" property="patAddDistrictCodeHidden"/>
<html:hidden name="mrdPatientDetailModFB" property="patAddPINHidden"/>
<html:hidden name="mrdPatientDetailModFB" property="patAddCityHidden"/>
<html:hidden name="mrdPatientDetailModFB" property="patIsUrbanHidden"/>
<html:hidden name="mrdPatientDetailModFB" property="patPrimaryCatCode"/>
<input type="hidden" name="sysDate" value="<%=sysDate%>"/>
<html:hidden name="mrdPatientDetailModFB" property="beforeModificationAge"/>
<html:hidden name="mrdPatientDetailModFB" property="isMLC"/>
<html:hidden name="mrdPatientDetailModFB" property="mlcNo"/>
<html:hidden name="mrdPatientDetailModFB" property="verificationDocumentAdded"/>
<html:hidden name="mrdPatientDetailModFB" property="patStatusCode"/>
<html:hidden name="mrdPatientDetailModFB" property="isNewImageUploaded"/>

