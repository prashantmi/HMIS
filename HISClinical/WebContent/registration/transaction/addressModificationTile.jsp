<%try{ %>
<%@page autoFlush="true" %>
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<%@page import="hisglobal.hisconfig.Config"%>
<%@page import="registration.controller.fb.AddressModificationFB"%>
<his:css src="/hisglobal/css/tab.css" />
<his:css src="/hisglobal/css/Color.css" />
<his:css src="/hisglobal/css/master.css" />
<his:css src="/hisglobal/css/hisStyle.css" />
<his:css src="/hisglobal/css/calendar-blue2.css" />
<his:css src="/hisglobal/css/hisStyleExt.css" />

<his:javascript src="/registration/js/popup.js" />
<his:javascript src="/registration/js/registration.js" />
<his:javascript src="/registration/js/validationCalls.js" />
<his:javascript src="/registration/js/validationCommon.js" />
<his:javascript src="/registration/js/stateChangeAjax.js" />
<his:javascript src="/registration/js/cityLocationAjax.js" />
<his:javascript src="/registration/js/commonFunctions.js" />
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<%@ page import="registration.*"%>
<%
Collection addressType = new HashSet();

addressType=(HashSet)session.getAttribute(RegistrationConfig.ESSENTIALBO_OPTION_EDIT_ADDRESS_TYPE_FOR_VALIDATION);

//List lst=(ArrayList)session.getAttribute(RegistrationConfig.ESSENTIALBO_OPTION_EDIT_ADDRESS_TYPE);

System.out.println("addressType-----"+addressType);
//System.out.println("addressType--List---"+lst);
%>

<script type="text/javascript">


var childwindow=null;

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
	document.getElementById("relationshipSelfId").style.display="none";	
	document.getElementById("relationshipRelativeId").style.display="block";
	
		//document.getElementById("designationLabel").style.display="block";
		//document.getElementById("designationControl").style.display="block";
		//document.getElementById("badgeNoLabel").style.display="block";
		//document.getElementById("badgeNoControl").style.display="block";
	}
	else if(obj.value=="2"){
	document.getElementById("policeDetailDiv").style.display="block";
	document.getElementById("requesterDIV").style.display="block";
		
	}
	else {
	document.getElementById("policeDetailDiv").style.display="none";
	document.getElementById("requesterDIV").style.display="none";
	document.getElementById("relationshipSelfId").style.display="block";	
	document.getElementById("relationshipRelativeId").style.display="none";	
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
  var patAge=parseInt(document.getElementsByName('patAge')[0].value)
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
  else if(patAge<validge && requestBy==requestBySelf)
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
 		if(isEmpty(document.getElementsByName('requestByName')[0],'Requester Name') &&
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
 		if(
 		  	comboValidation(document.getElementsByName('requestRelation')[0],' Relationship ') &&
 			isEmpty(document.getElementsByName('requestByName')[0],'Requester Name') &&
 			isEmpty(document.getElementsByName('requestByAddress')[0],'Requester Address') 
 		   )
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

function setAddType()
{


	var addModify=document.getElementsByName("addModify")[0].value 
	var addTrue=<%=RegistrationConfig.IS_ADDRESS_ADDED%>;
	var ModTrue=<%=RegistrationConfig.IS_ADDRESS_MODIFIED%>;
	if(addModify==ModTrue)
	{
//	var addTypeCodeArray=document.getElementsByName("patFBAddTypeCode")[0].value.split("@");

	document.getElementsByName("patAddTypeCode")[0].value=document.getElementsByName("patFBAddTypeCode")[0].value;

	//document.getElementsByName("patAddTypeLable")[0].value=//addTypeCodeArray[1]; //document.getElementsByName("patFBAddTypeCode")[0].options[document.getElementsByName("patFBAddTypeCode")[0]].text;

	}

}

//Submit Tile by setting hmode
function submitTile(mode){
//alert("123456")
	elem = document.getElementsByName('hmode')[0];
	elem.value = mode;
if(mode=="SAVE"){
		if(document.getElementsByName("arrSelectedVerifyDocs").length!=0){
			//alert(elem.value);
			document.forms[0].submit();
		}
		else
			alert("Verification for the addresses Not Specified !! ");
	}
	else
if(mode=="ADDADDRESS")	
	{

	
		<%if(addressType!=null && addressType.size()==0){ %>
				alert("All Address Types have been Added Please Select from the list to Modify")
			<% }else{ %>
				document.forms[0].submit();
			<% } %>
	}	
	else
	{
		// alert("hmode  ="+elem.value);
		document.forms[0].submit();
		}
}

function checkEmployee()
{ 			var patIdNo=document.getElementsByName("patIdNo")[0].value;
			var patCatCode=document.getElementsByName("patPrimaryCatCode")[0].value;
			var patCatCodeEmployee="<%=Config.PRIMARY_CATEGORY_EMPLOYEE_CODE%>";
			var addtypeCode=document.getElementsByName("patFBAddTypeCode")[0].value;
			var addTypeCurrentTrue=<%=RegistrationConfig.IS_ADDRESS_CURRENT_TRUE%>;
			var addModify=document.getElementsByName("addModify")[0].value 
			var addTrue=<%=RegistrationConfig.IS_ADDRESS_ADDED%>;
			var ModTrue=<%=RegistrationConfig.IS_ADDRESS_MODIFIED%>;
		// 	 alert(patIdNo)
			var clientValue="<%=Config.CLIENT%>"; 
	 		var clientPGIMER="<%=Config.CLIENT_PGIMER%>";
	 		var fieldEditableValue="<%=Config.PATCAT_EMPLOYEE_FIELD_VALUE%>";
	 		var fieldEditableFalse="<%=Config.PATCAT_EMPLOYEE_FIELD_EDITABLE_FALSE%>";
	 		var dataFromTableValue="<%=Config.PATCAT_EMPLOYEE_DATA_FROM_TABLE_VALUE%>";
	 		var dataFromTableTrue="<%=Config.PATCAT_EMPLOYEE_DATA_FROM_TABLE_TRUE%>";
	 		
	 if( (patCatCode==patCatCodeEmployee) && ((fieldEditableValue==fieldEditableFalse) || (dataFromTableValue==dataFromTableTrue) ) && (addModify==ModTrue) && (addtypeCode==addTypeCurrentTrue) )
	 {
				  document.getElementsByName("patAddCountryCode")[0].disabled=true;
				  document.getElementsByName("patAddStateCode")[0].disabled=true;
				  document.getElementsByName("patAddStateName")[0].disabled=true;
				//  document.getElementsByName("patAddCityLocCode")[0].disabled=true;
				//  document.getElementsByName("patAddCityLoc")[0].disabled=true;
				  document.getElementsByName("patAddHNo")[0].disabled=true;
				  document.getElementsByName("patAddStreet")[0].disabled=true;
				  document.getElementsByName("patAddDistrict")[0].disabled=true;
				  document.getElementsByName("patAddDistrictCode")[0].disabled=true;
				  document.getElementsByName('patIsUrban')[0].disabled=true;
				  document.getElementsByName("patAddCity")[0].disabled=true;
				  document.getElementsByName("patAddPIN")[0].disabled=true;
				  document.getElementsByName("patAddContactNo")[0].disabled=true;
				  
				  //document.getElementsByName("patAddCountryCode")[0].disabled=true;
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
 
 
 
function showState(){ 
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
		 //  document.getElementsByName("patAddCityLoc")[0].value="";
		    // elmt.options[elmt.selectedIndex].value="<%=RegistrationConfig.REGISTRATIONDESK_DEFAULT_STATE_CODE%>";
		 }
	}
	else{
		  document.getElementById("divpatAddCityLocCode").style.display="none"; 
		  document.getElementById("divpatAddCityLocation").style.display="";   
		    // elmt.options[elmt.selectedIndex].value="<%=RegistrationConfig.REGISTRATIONDESK_DEFAULT_STATE_CODE%>";
		 }
	
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

function validateAddressDtl(){
	var valid=false;
	// alert("validate"+validateDot(document.forms[0].patAddHNo,"House Number"))
	var patCatEmpFieldValue="<%=Config.PATCAT_EMPLOYEE_FIELD_VALUE%>";
	 var patCatEmpFieldEditfalse="<%=Config.PATCAT_EMPLOYEE_FIELD_EDITABLE_FALSE%>";
	var patIdNo=document.getElementsByName("patIdNo")[0].value;
	var addtypeCode=document.getElementsByName("patAddTypeCode")[0].value;
	var addTypeCurrentTrue=<%=RegistrationConfig.IS_ADDRESS_CURRENT_TRUE%>;
	var patCatCode=document.getElementsByName("patPrimaryCatCode")[0].value;
	var patCatCodeEmployee="<%=Config.PRIMARY_CATEGORY_EMPLOYEE_CODE%>";
 	var dataFromTableValue="<%=Config.PATCAT_EMPLOYEE_DATA_FROM_TABLE_VALUE%>";
 	var dataFromTableTrue="<%=Config.PATCAT_EMPLOYEE_DATA_FROM_TABLE_TRUE%>";
 	
 	//In the first case of validation if the patient is employee
 	
	 if( (patCatCode==patCatCodeEmployee) && ( (patCatEmpFieldValue==patCatEmpFieldEditfalse) || (dataFromTableValue==dataFromTableTrue) )  && (addtypeCode==addTypeCurrentTrue))
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
 //alert(valid);
	return valid;    
}

function validateAddressTypeCombo()
{
var valid=true
var addTrue=<%=RegistrationConfig.IS_ADDRESS_ADDED%>;
var ModTrue=<%=RegistrationConfig.IS_ADDRESS_MODIFIED%>;
	//alert("document.getElementsByName(addModify)[0].value"+document.getElementsByName("addModify")[0].value)
	//alert("document.getElementsByName(patAddTypeCode)[0].value"+document.getElementsByName("patAddTypeCode")[0].value)
	if(document.getElementsByName("addModify")[0].value==addTrue)
	{
	valid=isSelected(document.getElementsByName("patAddTypeCode")[0],"Address Type")
	}
	else
	{
	//alert("inside else")
	document.getElementsByName("patAddTypeCode")[0].value=document.getElementsByName("patFBAddTypeCode")[0].value
	//alert("document.getElementsByName(patFBAddTypeCode)[0].value"+document.getElementsByName("patFBAddTypeCode")[0].value)
	//alert("patAddTypeCode"+document.getElementsByName("patAddTypeCode")[0].value)
	}
	return valid
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

function callThisOnload(){
//	 showdelhidiv();
	if(document.getElementsByName("patCrNo")[0]){
		document.getElementsByName("patCrNo")[0].focus();
	}
	
	var addTrue=<%=RegistrationConfig.IS_ADDRESS_ADDED%>;
	var ModTrue=<%=RegistrationConfig.IS_ADDRESS_MODIFIED%>;
	// alert("addModify "+document.getElementsByName("addModify")[0].value);
if(document.getElementsByName("addModify")[0].value==addTrue)
{
	document.getElementById("addTypeCombo").style.display="";
	document.getElementById("addTypeText").style.display="none";
}
if(document.getElementsByName("addModify")[0].value==ModTrue)
{
	document.getElementById("addTypeCombo").style.display="none";
	document.getElementById("addTypeText").style.display="";
}
	setAddType();
	checkEmployee();
	showState();
	showLocation();
	enableRelation();
	
// 	focusCrNo();
	
}



 function  showdelhidiv()
  {


	   if(document.getElementsByName("isAddressDelhi")[0]!=null)
	   {
	       if('<bean:write name="AddressModificationFB" property="isAddressDelhi"/>'=='0'){
		   		document.getElementsByName("isAddressDelhi")[1].checked=true;
		   		document.getElementById("divpatAddCityLocCode").style.display="none"; 
		   }else if('<bean:write name="AddressModificationFB" property="isAddressDelhi"/>'=='1'){
		   	    //   alert("bean if  delhi");
			   document.getElementById("divpatAddCityLocCode").style.display="";   	  
			   document.getElementById("divpatAddCityLocation").style.display="none";

			   document.getElementsByName("patAddStateCode")[0].disabled=true;   
	       	   document.getElementsByName("patAddCountryCode")[0].disabled=true;

		   	   document.getElementsByName("patAddStateCode")[0].value="<%=RegistrationConfig.REGISTRATIONDESK_DEFAULT_STATE_CODE %>";   
	           document.getElementsByName("patAddCountryCode")[0].value="<%=RegistrationConfig.REGISTRATIONDESK_DEFAULT_COUNTRY_CODE%>";  
	       }
		}
	
 } 


 
 function  showdelhidetails()
  {

// alert("show delhi details");
		//   	   document.getElementsByName("isAddressDelhi")[0].checked=true;
		   	  document.getElementById("divpatAddCityLocCode").style.display="";   	  
			  document.getElementById("divpatAddCityLocation").style.display="none";
			  document.getElementsByName("patAddStateCode")[0].disabled=true;   
	       	  document.getElementsByName("patAddCountryCode")[0].disabled=true;
		   	  document.getElementsByName("patAddStateCode")[0].value="<%=RegistrationConfig.REGISTRATIONDESK_DEFAULT_STATE_CODE %>";   
	          document.getElementsByName("patAddCountryCode")[0].value="<%=RegistrationConfig.REGISTRATIONDESK_DEFAULT_COUNTRY_CODE%>";  
	    
 } 


function populate(selectedarray){
 // alert("length:::"+selectedarray.length);  
  	  strHtml ="";
  	  elem = document.getElementById("hiddenDivVerification");
  	  for(i=0; i<selectedarray.length; i++){
  	  //alert(selectedarray[i])
  	  var arrayOfDocsData=selectedarray[i].split("|");
  	  
  	  	strHtml+="<input type='hidden' name='arrSelectedVerifyDocs' value='"+selectedarray[i]+"'/>	";
  	  	strHtml+=arrayOfDocsData[1]+"&nbsp; &nbsp;";
  	  }
      elem.innerHTML= strHtml;
	  //elem.style.display="";
    //  alert(elem.innerHTML);
      //document.getElementById("hiddenDivVerification").style.display="";
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

function clearForm(){
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
	//if(document.forms[0].patAddTypeCode){
		document.getElementsByName("patAddTypeCode")[0].value="-1"
	//}	

	document.getElementsByName("requestBy")[0].value="-1";
	document.getElementsByName("requestRelation")[0].value="-1";
	document.getElementsByName("requestByName")[0].value="";
	document.getElementsByName("requestByAddress")[0].value="";
	document.getElementsByName("verificationDocumentAdded")[0].value="0";
	
	
	enableRelation();

var str='<font color="red"><bean:message key="reqdVerificationDocument" /></font><input type="hidden" name="arrSelectedVerifyDocs" value="" />';

	document.getElementById("hiddenDivVerification").innerHTML=str;
	
}

function getAddressDetailForModification(){
setAddType();
//checkValueOfCombo(document.getElementsByName('patFBAddTypeCode')[0],'Address Type','MODIFYADDRESS');
 submitForm('MODIFYADDRESS');

}
</script>


<%@ page import="java.util.*,registration.*,hisglobal.vo.*"%>
<%boolean varIsNewStatus = false;
			String varStatus = "";
			String systemDate=hisglobal.presentation.WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy HH:mm");
			
			  
			
%>
<his:statusNew>
	<%varIsNewStatus = true;
				varStatus = "New";
								
				%>

</his:statusNew>




<his:TitleTag>

	<his:name>
		<bean:message key="address" />
		<bean:message key="modification" />
	</his:name>

	<b><font size="2"
		face="Verdana, Arial, Helvetica, sans-serif"> 
		 </font></b>


</his:TitleTag>

<his:InputCrNoTag name="AddressModificationFB">
</his:InputCrNoTag>



<bean:define id="status_object" 	name="<%=Config.STATUS_OBJECT%>" 	type="hisglobal.presentation.Status" />


<%System.out.println("status_object:  "
					+ status_object.getStatusList());
			if (status_object != null
					&& !status_object.contains(status_object.UNSUCESSFULL)
					&& status_object.contains(status_object.INPROCESS)) { %>

<jsp:include page="/registration/patientDetail.cnt" flush="true" />

<his:SubTitleTagBroad>

	<his:name>
		<bean:message key="address" />
		<bean:message key="detail" />
	</his:name>
	

		
	<img class="button" src='<his:path src="/hisglobal/images/RoundPlus12x12.png"/>'
		style=cursor:pointer onclick="submitTile('ADDADDRESS');"
		onkeypress="if(event.keyCode==13) submitTile('ADDADDRESS');" size='7'
		tabindex="1">
		
		 
		
</his:SubTitleTagBroad>


<his:ContentTag>
<logic:present name="<%=RegistrationConfig.REGDESK_ADDRESSVO_ARR%>">
	
						<table width="100%" cellspacing="1" cellpadding="0">

							<%varStatus = "InProcess";%>


		<tr>
		
		<td class="tdfont" width="15%">
			<div align="left">
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				
					<bean:message key="addressType"/>
				</font>		
			</div>
		</td>
	  <td class="tdfont" width="85%">
			<div align="left">
			    <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<bean:message key="address"/>
				</font>		
			</div>
		</td>
	</tr>

		<logic:iterate id="address" name="<%=RegistrationConfig.REGDESK_ADDRESSVO_ARR%>" type="hisglobal.vo.AddressVO">
		
		 
		
								<tr>
								
									<td width="15%" Class="tdfonthead">
									<div align="left">
								
								
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							
							<html:radio name="AddressModificationFB" property='patFBAddTypeCode' value="<%=address.getPatAddTypeCode()%>" onclick="getAddressDetailForModification()"/>			
										
							
								<bean:write name="address" property="patAddType" /></font>
										
										
										</div>
									
									</td>


							
									<td width="85%" Class="tdfonthead" >
									<div align="left">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
											<bean:write	name="address" property="patAddHNo" />
												<logic:notEqual	name="address" property="patAddHNo" value="">,
												</logic:notEqual>
											<bean:write name="address" property="patAddStreet" />
												<logic:notEqual	name="address" property="patAddStreet" value="">,
												</logic:notEqual>
											<bean:write name="address" property="patAddCityLoc" />
												<logic:notEqual	name="address" property="patAddCityLoc" value="">,
												</logic:notEqual>
											<bean:write name="address" property="patAddCityLocMstValue" />
												<logic:notEqual	name="address" property="patAddCityLocMstValue" value="">,
												</logic:notEqual>
											<bean:write name="address" property="patAddDistrict" />
												<logic:notEqual	name="address" property="patAddDistrict" value="">,
												</logic:notEqual>
											<bean:write name="address" property="patAddDistrictMstValue" />
												<logic:notEqual	name="address" property="patAddDistrictMstValue" value="">,
												</logic:notEqual>	
											<bean:write name="address" property="patAddCity" />
												<logic:notEqual	name="address" property="patAddCity" value="">,
												</logic:notEqual>
											<bean:write name="address" property="patAddState" /> 
												<logic:notEqual	name="address" property="patAddState" value="">,
												</logic:notEqual>
											<bean:write name="address" property="patAddCountry" /> 
												<logic:notEqual	name="address" property="patAddPIN" value="">
													<bean:message key="line" />
												</logic:notEqual> 
											<bean:write name="address" property="patAddPIN" /> 
												<logic:notEqual name="address" property="patAddContactNo" value="">
													<bean:message key="addressContactNo" />
												</logic:notEqual> 
											<bean:write name="address" property="patAddContactNo" /> 
										</font>
										</div>	
									</td>
								</tr>
							</logic:iterate>

						</table>
</logic:present>
						

</his:ContentTag>
<%}%>






<bean:define name="AddressModificationFB"
	property="arrSelectedVerifyDocs" id="arrVerificationDocs"
	type="java.lang.String[]" />

<%if (varStatus.equals("InProcess")) { %>

<%if (arrVerificationDocs.length != 0) {

					System.out.println("mode..."
							+ request.getParameter("hmode"));

					%>


 
  
 <bean:define name="AddressModificationFB" property="patFBAddTypeCode" 		id="typeCode" />
<bean:define name="AddressModificationFB" property="hmode" id="hmode" />
			
			<%if (!typeCode.equals("-1") || hmode.equals("ADDADDRESS")) {

						%>

			
			<%String divdisplay = "\"\"";
						String divnddisplay = "\"\"";
						String divisrefdisplay = "\"\"";
						String divRefGnctdHospitalCode = "\"\"";
						String divRefHospname = "\"\"";
						String strdivage = "\"\"";
						String strdivdob = "\"\"";
						String RefGnctdHosNameradio = "";
						String RefGnctdHoscoderadio = "";


						%>
<his:SubTitleTag name=" ">
<div align="left">

<bean:message key="address" />
<%if (request.getParameter("hmode").equals(	"MODIFYADDRESS")) { %>
	 <bean:message key="modification" /> 
<%} else { %>
     <bean:message key="addition" /> 
<%}	%> 
	<bean:message key="detail" />
</div>	
</his:SubTitleTag>
			
					<table width="100%" cellpadding="0" cellspacing="1" border="0">
						
					
						<logic:equal name="AddressModificationFB"
							property="isAddressDelhi" value="0">

							<%divdisplay = "none";
							divnddisplay = "";
							System.out.println("divdisplay" + divdisplay);

						%>
						</logic:equal>

						<logic:equal name="AddressModificationFB"
							property="isAddressDelhi" value="1">

							<%divdisplay = "";
							divnddisplay = "none";
							System.out.println("divnddisplay" + divnddisplay);

						%>
						</logic:equal>
						<tr>
							<td class="tdfonthead">
							<div align="right"><font color="#000000" size="2"
								face="Verdana, Arial, Helvetica, sans-serif"> <font
								color="#FF0000" size="1"
								face="Verdana, Arial, Helvetica, sans-serif">*</font> <bean:message
								key="address" /> <bean:message key="type" /> </font></div>
							</td>
						
							<td id="addTypeCombo" class="tdfont"><html:select tabindex="1" name="AddressModificationFB"
								property="patAddTypeCode" styleClass="regcbo">
								<html:option value="-1">Select Value</html:option>
								<html:options
									collection="<%=RegistrationConfig.ESSENTIALBO_OPTION_EDIT_ADDRESS_TYPE%>"
									property="value" labelProperty="label" />
							</html:select></td>  
							
				
							<td id="addTypeText" class="tdfont"><html:text tabindex="1"
								name="AddressModificationFB" property="patAddTypeLable"
								 maxlength="50" readonly="true"
								styleClass="textbox" /></td>
								
								<td width="25%" class="tdfonthead"></td>
								<td width="25%" class="tdfont"></td>
						</tr>
							<tr>

							<td class="tdfonthead" width="25%">
							<div align="right"><font color="#FF0000" size="1"
								face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
								key="mandatory" /></font> <font color="#000000" size="2"
								face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
								key="country" /></font></div>
							</td>

							<td class="tdfont" width="25%">
							<div id="patCountryCombo"><html:select tabindex="1"
								name="AddressModificationFB" property="patAddCountryCode" onchange="showState();"
								styleClass="regcbo">
								<html:option value="-1">Select Value</html:option>
								<html:options
									collection="<%=RegistrationConfig.ESSENTIALBO_OPTION_COUNTRY %>"
									property="value" labelProperty="label" />
							</html:select></div>
							</td>
							
						<td class="tdfonthead" width="25%">
						  <div id="stateMandotary" style="" align="right">
             			  <font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font>
             			  <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
             		  		<bean:message key="state"/>
             		 	 </font>
             			 </div>
             			 
            			  <div id="stateNotMandotary" style="" align="right">
             			  <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
             			  
             		  		<bean:message key="state"/>
             			  </font>
              			</div>
							</td>
							
							<td class="tdfont" width="25%">

							<div id="divpatAddStateCodeCombo" align="left">
							<html:select tabindex="1"
								name="AddressModificationFB" property="patAddStateCode" 
								onchange="if(this.value!='-1') sendDataForStateChange(this)"
								styleClass="regcbo">
								<html:option value="-1">Select Value</html:option>
								<html:options
									collection="<%=RegistrationConfig.ESSENTIALBO_OPTION_STATE %>"
									property="value" labelProperty="label" />
							</html:select></div>
							
							 <div id="divpatAddStateCodeText" style="" align="left">
                		     <html:text name="AddressModificationFB" tabindex="1" property="patAddStateName" styleClass="regcbo" maxlength="50" onkeypress="return validateAlphabetsWithDotsOnly(event,this);">
                 		    </html:text>
                 		    </div>              
							</td>
						</tr>
						
						<tr>
							<td class="tdfonthead">
							<div align="right"><font color="#000000" size="2"
								face="Verdana, Arial, Helvetica, sans-serif"><bean:message
								key="location" /> </font> <font color="#FF0000" size="1"
								face="Verdana, Arial, Helvetica, sans-serif"></font></div>
							</td>
							
							<td class="tdfont">
							
							
							
							<div id="divpatAddCityLocCode" >
							<html:select
								tabindex="1" name="AddressModificationFB"
								property="patAddCityLocCode" styleClass="regcbo" 
								onchange="if(this.value!='-1') sendDataForCityLocation(this)">
								<html:option value="-1">Select Value</html:option>
								<html:options
									collection="<%=RegistrationConfig.ESSENTIALBO_OPTION_LOCATION%>"
									property="value" labelProperty="label" />
							</html:select></div>
							
							<div id="divpatAddCityLocation" >
							<html:text tabindex="2" name="AddressModificationFB"
								property="patAddCityLoc"
								onkeypress="return validateAlphabetsWithDotsOnly(event,this);"
								maxlength="50" styleClass="textbox" /></div>
								
							</td>

						
							<td class="tdfonthead">
							<div align="right"><font color="#000000" size="2"
								face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
								key="hno" /> </font></div>
							</td>
							<td class="tdfont"><html:text tabindex="1"
								onkeypress="return validateAlphaNumericWithDotsOnly(event,this);"
								maxlength="15" name="AddressModificationFB" property="patAddHNo"
								styleClass="textbox" /></td>
						</tr>
						
						
						<tr>
							<td class="tdfonthead">
							<div align="right"><font color="#000000" size="2"
								face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
								key="street" /> </font></div>
							</td>
							<td class="tdfont"><html:text tabindex="1"
								name="AddressModificationFB" property="patAddStreet"
								onkeypress="return validateAlphaNumericWithDotsOnly(event,this);"
								maxlength="50" styleClass="textbox" /></td>
						
							<td class="tdfonthead">
								<div align="right"><font color="#000000" size="2"
								face="Verdana, Arial, Helvetica, sans-serif"><bean:message
								key="district" /> </font> <font color="#FF0000" size="1"
								face="Verdana, Arial, Helvetica, sans-serif"></font></div>
							
							</td>
							<td class="tdfont">
								<div id="districtCombo" style='display:<%=divdisplay%>'><html:select
								tabindex="1" name="AddressModificationFB"
								property="patAddDistrictCode" styleClass="regcbo">
								<html:option value="-1">Select Value</html:option>
								<html:options
									collection="<%=RegistrationConfig.ESSENTIALBO_OPTION_DISTRICT_ON_DEFAULT_STATE%>"
									property="value" labelProperty="label" />
							</html:select></div>
							<div id="districtTextBox" style='display:<%=divnddisplay%>'>
							<html:text tabindex="1" name="AddressModificationFB"
								property="patAddDistrict"
								onkeypress="return validateAlphabetsWithDotsOnly(event,this);"
								maxlength="30" styleClass="textbox" /></div>
														
							</td>
						</tr>
						
						
						<tr>
							<td class="tdfonthead">
							<div align="right">
							<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font>
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								  <bean:message key="city"/>
	            				  <bean:message key="slash"/>
	            				  <bean:message key="village"/>
	             </font></div>
							</td>
							<td class="tdfont"><html:text tabindex="1"
								name="AddressModificationFB" property="patAddCity"
								onblur="isAlpha(this,'City');"
								onkeypress="return validateAlphabetsWithDotsOnly(event,this);" maxlength="30"
								styleClass="textbox" /></td>
						
							<td class="tdfonthead">
							<div align="right"><font color="#000000" size="2"
								face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
								key="pin" /> </font></div>
							</td>
							<td class="tdfont"><html:text tabindex="1"
								name="AddressModificationFB" property="patAddPIN" maxlength="6"
								styleClass="textbox" onkeypress="return validateNumeric(event)" />
							</td>
						</tr>
						
						<tr>
							<td class="tdfonthead">
							<div align="right">
							<font color="#FF0000" size="2" face="Verdana, Arial, Helvetica, sans-serif">*</font>
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="areaCategory" /> </font></div>
							</td>
							<td class="tdfont">
							<html:select tabindex="1" name="AddressModificationFB" property="patIsUrban" styleClass="regcbo">
								<html:option value="-1">Select Value</html:option>
								<html:options collection="<%=RegistrationConfig.ESSENTIALBO_OPTION_AREA_CATEGORY%>" property="value" labelProperty="label" />
							</html:select>
							</td>
						
							<td class="tdfonthead">
							<div align="right">
							<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font>
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:message key="contactNo" /> </font></div>
							</td>
							<td class="tdfont"><html:text tabindex="1"
								name="AddressModificationFB" property="patAddContactNo"
								onkeypress="return validateNumeric(event);" maxlength="30"
								styleClass="textbox" /></td>
						</tr>

						<tr>
							<td class="tdfonthead">
							<div align="right"><font color="#000000" size="2"
								face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
								key="mobileNo" /> </font></div>
							</td>
							<td class="tdfont"><html:text tabindex="1"
								name="AddressModificationFB" property="patAddMobileNo"
								onkeypress="return validateNumeric(event);" maxlength="15"
								styleClass="textbox" /></td>
						
							<td class="tdfonthead">
							<div align="right"><font color="#000000" size="2"
								face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
								key="faxNo" /> </font></div>
							</td>
							<td class="tdfont"><html:text tabindex="1"
								name="AddressModificationFB" property="patAddFaxNo"
								onkeypress="return validateNumeric(event);" maxlength="30"
								styleClass="textbox" /></td>
						</tr>
						
						<tr>
							<td class="tdfonthead">
							<div align="right"><font color="#000000" size="2"
								face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
								key="email" /> </font></div>
							</td>
							<td class="tdfont"><html:text tabindex="1"
								name="AddressModificationFB" property="patAddEmailId"
								 maxlength="50"
								styleClass="textbox" onkeypress="return CheckMaxLength(event,this,50,3)"/></td> 
						
							
							
							<%String modeOnOK = "ADD";
						if (request.getParameter("hmode").equals(
								"MODIFYADDRESS")) {
							modeOnOK = "MODIFY";
						}

						%>

							<td class="tdfonthead" >
							<div align="right"><font color="#000000" size="2"
								face="Verdana, Arial, Helvetica, sans-serif"> <font
								color="#FF0000" size="1"
								face="Verdana, Arial, Helvetica, sans-serif">*</font>
								 <bean:message	key="verificationDocument" />
								  </font></div>
							</td>


							<td class="tdfont">
							<div align="left"><img class="button"
								src='<his:path src="/hisglobal/images/icon-vrf.png"/>'
								style="cursor:pointer;" alt="Verification Documents"
								title="Verification Documents"
								onclick="openPopup('<his:path src="/registration/verificationDocumentPopup.cnt"/>',event,300,600);"
								onkeypress="if(event.keyCode==13) {openPopup('<his:path src="/registration/verificationDocumentPopup.cnt"/>',event,300,600)}"
								size='7' tabindex="1"></div>
							</td>
							
						</tr>
					</table>
				
			<%}%>
<table width="100%" cellspacing="0" cellpadding="0" bgcolor="#EBEBEB"
		style="clear:both; border-left:1px solid #003366;">
		<tr>
			<td width="5%" nowrap valign="middle"><img
				src='<his:path src="/hisglobal/images/icon-vrf.png"/>' /></td>

			<td width="10%" nowrap><font color="#000000" size="2"
				face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
				key="verificationDocument" /> :: </font></td>

			<td class="tdfont" nowrap><font color="#000000" size="2"
				face="Verdana, Arial, Helvetica, sans-serif">
			<div id="hiddenDivVerification">
			
			<logic:iterate 	name="AddressModificationFB" property="arrSelectedVerifyDocs" id="selectedVerifyDocs" type="java.lang.String"> 

				<%if (selectedVerifyDocs.equals("")
									|| selectedVerifyDocs == null) {
								System.out.println("heeeeee......");

							%>
				<font color="red"><bean:message key="reqdVerificationDocument" /></font>
				<input type="hidden" name="arrSelectedVerifyDocs" value="" />
				<%}

							else {

								%>
				<bean:define id="selectedVerifyDocs" name="selectedVerifyDocs" type="java.lang.String"/>
				<%
				String[] arrayOfDocsData=selectedVerifyDocs.split("|");
				%>
				
				<div id="arrayOfDocsDataId">
				<%=arrayOfDocsData[0]%>&nbsp; &nbsp;
				</div>
				
				<input type="hidden" name="arrSelectedVerifyDocs"
					value="<%=selectedVerifyDocs%>" />
				<%}

						%>
			</logic:iterate></div>
			</font></td>
		</tr>
	</table>
	
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
        	<html:select name="AddressModificationFB" property="requestBy" styleClass="regcbo" onchange="enableRelation()" tabindex="1">
        		<html:option value="-1">Select Value</html:option>
        		<html:option value="<%=RegistrationConfig.MODIFICATION_REQUESTED_BY_SELF %>">Self</html:option>
        		<html:option value="<%=RegistrationConfig.MODIFICATION_REQUESTED_BY_RELATIVE %>">Relative</html:option>
        		<%String mlcNo=((AddressModificationFB)pageContext.findAttribute("AddressModificationFB")).getMlcNo();
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
       <html:select name="AddressModificationFB" property="requestRelation" tabindex="1" styleClass="regcbo" tabindex="1">
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
             <html:text name="AddressModificationFB" tabindex="1" property="requestByName" tabindex="1" onkeypress="return validateAlphabetsWithDotsOnly(event,this)" tabindex="1" styleClass="textbox" onblur="isAlpha(this,'Middle Name')" maxlength="60"/>
        </td>
         
       <td width="25%" class="tdfonthead">
       <font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font>
          	<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
             <bean:message key="requestByAddress"/>
       		 </font>
       	</td>
      
       	<td width="25%"  class="tdfont"><!-- 3 -->
            <html:textarea name="AddressModificationFB" property="requestByAddress" tabindex="1"  onkeypress="return CheckMaxLength(event,this,100,3)"  tabindex="1" 
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
		      		<html:text name="AddressModificationFB" property="constableDesig" styleClass="textbox" tabindex="1" maxlength="50" onkeypress="return validateAlphaNumericWithDotsOnly(event,this)"></html:text>
		      	</td>
		      	<td   width="25%" class="tdfonthead" >
		      	<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font>
		      		 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				       <bean:message key="batchNo"/>
				      </font>
		      	</td>
		      	<td  width="25%" class="tdfont" >
		      		<html:text name="AddressModificationFB" property="constableBadgeNo" styleClass="textbox" tabindex="1" maxlength="50" onkeypress="return validateAlphaNumericWithDotsOnly(event,this)"></html:text>
		      	</td>
		      </tr>
	      
	      </table>
      </div>
	      
</his:ContentTag>


<%}

				else {%>
<input type="hidden" name="arrSelectedVerifyDocs" value="" />
<%}
			}

			%>


<his:ButtonToolBarTag>
	<%if (varStatus.equals("InProcess")) {String hmd = request.getParameter("hmode");
					System.out.println("hmd...." + hmd);
					String	display="none";
					if (hmd.equals("ADDADDRESS")) 
						hmd="ADD";
					
					if(hmd.equals("MODIFYADDRESS"))
						hmd="MODIFY";

					%><table width=100%>
					<tr>
					<td width='45%' align='right'>
					<div id="saveButton" >
					<img class="button" style='cursor:pointer'  src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>'  onkeypress="if(event.keyCode==13) submitFormOnValidate(validateAddressDtl(),'SAVE');" tabindex="1" onclick="submitFormOnValidate(validateAddressDtl(),'SAVE');" >
					</div>
					</td>
					<td width='55%' align='left'>
					<img class="button"	src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style=cursor:pointer onclick="clearForm()" onkeypress="if(event.keyCode==13) clearForm();" tabindex="1">
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style=cursor:pointer onkeypress="if(event.keyCode==13) submitForm('NEW')" onclick="submitForm('NEW')" tabindex="1"/>
					</td>
					</tr></table>
	<%} else {
					//System.out.println("hmd.3333..." + hmd);

				%>

	<div align="center">
	
	
		<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style="cursor:pointer;"
		onclick="submitForm('NEW')"
		onkeypress="if(event.keyCode==13) submitForm('NEW');" tabindex="1">
		
	<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style=cursor:pointer
		onkeypress="if(event.keyCode==13) submitPg('CANCEL')" onclick="submitPg('CANCEL')" tabindex="1"> 
		</div>
	<%}

			%>


  
</his:ButtonToolBarTag>
<html:hidden name="AddressModificationFB" property="hmode" value="unspecified"/>
<html:hidden name="AddressModificationFB" property="patIdNo" />
<html:hidden name="AddressModificationFB" property="patCrNo" />
<html:hidden name="AddressModificationFB" property="addModify" />
<html:hidden name="AddressModificationFB" property="patAddTypeCode" />
<html:hidden name="AddressModificationFB" property="patPrimaryCatCode" />
<html:hidden name="AddressModificationFB" property="isMLC"/>
<html:hidden name="AddressModificationFB" property="mlcNo"/>
<html:hidden name="AddressModificationFB" property="verificationDocumentAdded"/>
<html:hidden name="AddressModificationFB" property="patStatusCode"/>
<html:hidden name="AddressModificationFB" property="patAge"/>

<his:status />
<%}catch(Exception e){e.printStackTrace();}%>