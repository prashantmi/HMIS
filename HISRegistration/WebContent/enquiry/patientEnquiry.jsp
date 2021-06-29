<%try{ %>
<%@page import="hisglobal.hisconfig.*"%>
<%@page import="registration.config.*"%>
<%-- <%@page import="hisglobal.utility.servlets.ServletsUtilityConfig"%> --%>
<%@page import="hisglobal.presentation.WebUTIL"%>
<%@page import="java.util.Date"%>
<html>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<script type="text/javascript" src="/HIS/hisglobal/js/avai/calendar.js" /></script>
<script type="text/javascript" src="/HIS/hisglobal/js/validationCommon.js" /></script>
<script type="text/javascript" src="/HIS/hisglobal/js/validationCalls.js" /></script>
<script type="text/javascript" src="/HIS/hisglobal/js/commonFunctions.js" /></script>
<script type="text/javascript" src="/HIS/hisglobal/js/popup.js" /></script>
<script type="text/javascript" src="/HIS/hisglobal/js/dateFunctions.js" /></script>
<script type="text/javascript" src="/HISRegistration/registration/transactions/js/regValidation.js" /></script> 

<link rel="stylesheet" href="/HIS/hisglobal/css/avai/Color.css">
<link rel="stylesheet" href="/HIS/hisglobal/css/avai/hisStyle.css">
<link rel="stylesheet" href="/HIS/hisglobal/css/avai/hisStyleExt.css">
<link rel="stylesheet" href="/HIS/hisglobal/css/avai/calendar-blue2.css">

<link href="/HIS/hisglobal/css/layout.css" rel="stylesheet" type="text/css">
<link href="/HIS/hisglobal/css/buttons.css" rel="stylesheet" type="text/css">

<script><!--

var queryString;

function sendDataForBagType(url,imagePath)
{	
	//var componentId=obj.value;
	queryString="documentStoragePath="+ imagePath;
	//alert("queryString = "+queryString)
	//alert(componentId)
	//if(componentId!="" && componentId!='-1')
    //{
 //   	var url="/AHIMS/ShowFileFromDir?documentStoragePath="+imagePath+"&"+"fileName="+fileName+"&"+"isExtension=1&extensionName=jpeg";
 	//}
 	httpRequestForImage("GET",url,true)
 	
}

function httpRequestForImage(reqType,url,asynch)
{
	if(window.XMLHttpRequest)
	{
	 request = new XMLHttpRequest();
	 //alert("request"+request)
	 
 	 initReqForImage(reqType,url,asynch);
	}
	else
	 if (window.ActiveXObject)
	 {
 		request=new ActiveXObject("Msxml2.XMLHTTP");
		 if (! request)
		 {
		 request=new ActiveXObject("Microsoft.XMLHTTP");
		 }
		 if(request){
 		initReqForImage(reqType,url,asynch);
		 }
		else 
		{
		 alert("Your browser does not permit the use of all of this application's features!");
		 }
	 }
	 else
	 {
		 alert("Your browser does not permit the use of all of this application's features!");
	}

}

function initReqForImage(reqType,url,isAsynch)
{
 //if(isNewComponent=="0")
 	request.onreadystatechange=handleResponseForImage;
 //else
 	//request.onreadystatechange=handleResponseForRemainingComponent;
 request.open(reqType,url,isAsynch);
 request.setRequestHeader("Content-Type","application/x-www-form-urlencoded; charset=UTF-8");
 request.send(queryString);

}

function handleResponseForImage()
{
 if(request.readyState == 4)
 {
	 if(request.status == 200)
	 {
	 
	 var responseString=request.responseText
	    
	    document.getElementById("confirmMsg").style.display="block"
	 	document.getElementById("confirmMsg").innerHTML=responseString;
	 	enableBlanket();
	 }
	 else
	 {
	 alert("A problem occurred with communicating between the XMLHttpRequest object and the server program.");
	 ///document.getElementById("deptCodeID").innerHTML=" <select name='departmentUnitCode' tabindex='1' style='font-family: Verdana; font-size: 12px; font-style: normal; text-decoration: none;  height:20px;width: 115px;' onchange='sendDataForUnitChange(this)'><option value='-1'>Select Value</option></select>"
	 }
 }
}

function enableBlanket()
{
	document.getElementById("blanket").style.display="block";
}
function disableBlanket()
{
	document.getElementById("blanket").style.display="none";
}

function submitPage(hmode){
	document.forms[0].hmode.value=hmode;
	document.forms[0].submit();
}
function getOrderBy(mode,order){
	document.forms[0].hmode.value=mode;
	document.forms[0].order.value=order;
	document.forms[0].submit();
}
function showState(){ 
	var contryCode=document.getElementsByName("gnum_country_code")[0].value;
	
// alert(<%=RegistrationConfig.REGISTRATIONDESK_DEFAULT_COUNTRY_CODE%>)
	var defaltcontryCode="<%=RegistrationConfig.REGISTRATIONDESK_DEFAULT_COUNTRY_CODE%>";  
	 //alert("chcking conuntry code "+contryCode+"  "+defaltcontryCode);
	var stateCode= document.getElementsByName("gnum_state_code")[0];
	if(contryCode==defaltcontryCode)
	{
		//alert("default country")
		document.getElementById("stateMandotary").style.display="";
		document.getElementById("divpatAddStateCodeCombo").style.display="";
		document.getElementById("divpatAddStateCodeText").style.display="none";
		//alert("state")
		document.getElementById("stateNotMandotary").style.display="none";
		document.getElementById("districtCombo").style.display="";
		document.getElementById("districtTextBox").style.display="none";
		
	// 	document.getElementsByName("patAddStateCode")[0].value="<%=RegistrationConfig.REGISTRATIONDESK_DEFAULT_STATE_CODE%>";
		
	}
	else{
		//alert("not default country")
		document.getElementById("stateMandotary").style.display="none";
		document.getElementById("divpatAddStateCodeCombo").style.display="none";
		document.getElementById("divpatAddStateCodeText").style.display="";
		document.getElementById("stateNotMandotary").style.display="";
		document.getElementById("divpatAddCityLocCode").style.display="none"; 
	  	
	  	document.getElementById("districtCombo").style.display="none";
		document.getElementById("districtTextBox").style.display="";
		 
	  	document.getElementsByName('hrgstr_city_location')[0].value=""
	  	
	  	document.getElementsByName('hrgnum_pincode')[0].value=""
	  	//document.getElementsByName('gnum_city_loc_code')[0].value="-1"
	  	document.getElementsByName('num_dist_id')[0].value="-1"
	  	
	}
	//  showLocation();
	
}

function showLocation(){

  var elmt= document.getElementsByName("gnum_state_code")[0];
  var locationRequired=0;
  var locationRequiredYes=1;
  
  if(locationRequired==locationRequiredYes)
  { 
  
	  if(elmt.options[elmt.selectedIndex].value!=<%=RegistrationConfig.REGISTRATIONDESK_DEFAULT_STATE_CODE%>)
	  	  {  
	  	   
		  
		   document.getElementsByName("hrgstr_city")[0].value=""; 
		  }
	  else{
		 //alert("inside else hide divpatAddCityLocation") 
	
		   document.getElementsByName("hrgstr_city")[0].value="";
		    // elmt.options[elmt.selectedIndex].value="<%=RegistrationConfig.REGISTRATIONDESK_DEFAULT_STATE_CODE%>";
		 }
	}
	else{
		
			document.getElementsByName("hrgstr_city")[0].value="";   
		    // elmt.options[elmt.selectedIndex].value="<%=RegistrationConfig.REGISTRATIONDESK_DEFAULT_STATE_CODE%>";
		 }
	
}

function callThisOnload(){	
	document.getElementsByName("fromDate")[0].value=document.getElementsByName("fromDateHidden")[0].value;
	document.getElementsByName("toDate")[0].value=document.getElementsByName("toDateHidden")[0].value;
	showState();
	unknownChecked();	
	showLocation();
	//sendData();
}

function validatePatientEnquiry()
{
	var valid;
	if(document.forms[0].hrgstr_fname.value!="" ||
	document.forms[0].hrgstr_mname.value!="" ||
	document.forms[0].hrgstr_lname.value!="" ||
	document.forms[0].gnum_gender_code.value!="-1" ||
	document.forms[0].hrgstr_father_name.value!="" ||
	document.forms[0].hrgstr_spousename.value!="" ||
	document.forms[0].hrgnum_isunknown.checked ||
	//document.forms[0].gnum_dept_code.value!="-1" ||
	document.forms[0].fromDate.value!="" ||
	document.forms[0].toDate.value!="" ||
	document.forms[0].hrgnum_puk.value!="" ||
	document.forms[0].gnum_country_code.value!="-1" ||
	document.forms[0].gnum_state_code.value!="-1" ||
	document.forms[0].hrgstr_state_name.value!="" ||
	//document.forms[0].gnum_city_loc_code.value!="-1" ||
	document.forms[0].hrgstr_city_location.value!="" ||
	document.forms[0].hrgstr_house_no.value!="" ||
	document.forms[0].hrgstr_street_no.value!="" ||
	document.forms[0].hrgstr_district.value!="" ||
	document.forms[0].hrgstr_city.value!="" ||
	document.forms[0].hrgnum_pincode.value!="" ||
	document.forms[0].num_dist_id.value!="" //||
	//document.forms[0].hrgstr_contact_no.value!="" 
	)
	{
		//valid=true;
		if(validateDate())
		{
			//console.log("crNoSearchCriteria :"+crNoSearchCriteria());
			//console.log("dateRangeSearchCriteria :"+dateRangeSearchCriteria());
			//console.log("patNameSearchCriteria :"+patNameSearchCriteria());
			if(crNoSearchCriteria() || dateRangeSearchCriteria() || patNameSearchCriteria()){
				if(patNameSearchCriteria()){
					if(document.getElementsByName("gnum_gender_code")[0].value=="-1"){
						alert("Kindly Select Gender with Patient Name");
						valid=false;
						return false;
					}
					else
					{
						var fname=document.getElementsByName("hrgstr_fname")[0].value;
						var mname=document.getElementsByName("hrgstr_mname")[0].value;
						var lname=document.getElementsByName("hrgstr_lname")[0].value;
						if(fname.length>=3||mname.length>=3||lname.length>=3){
							valid=true;
							submitForm('SEARCH')
						}
						else
						{
							alert("Kindly Enter Atleast 3 characters in Patient Name");
							return false;
						}	
						
					}
				}
				valid=true;
				submitForm('SEARCH')
			}else{
				if(document.forms[0].hrgnum_isunknown.checked){
				alert("Please fill atleast One Search Criteria From\n"+
						"\t1. Cr No\n"+
						"\t2. Either FromDate or ToDate or both\n");
				}
				else{
					alert("Please fill atleast One Search Criteria From\n"+
							"\t1. Cr No\n"+
							"\t2. Either FromDate or ToDate or both\n"+
							"\t3. Patient Name");
					
					}
				valid=false;
				
			}
			
		}
	}
	else
	{
		valid=false
		alert("Please Enter Value In Atleast One Field")
	}
	
	return valid
}

function unknownChecked()
{
	 
	if(document.forms[0].hrgnum_isunknown.checked)
	{
		
		document.forms[0].hrgstr_fname.disabled=true
		document.forms[0].hrgstr_mname.disabled=true
		document.forms[0].hrgstr_lname.disabled=true
		document.forms[0].hrgstr_father_name.disabled=true
		document.forms[0].hrgstr_spousename.disabled=true
		//document.forms[0].gnum_dept_code.disabled=true
		//document.forms[0].hrgnum_puk.disabled=true
		document.forms[0].gnum_country_code.disabled=true
		document.forms[0].gnum_state_code.disabled=true
		document.forms[0].hrgstr_state_name.disabled=true
		//document.forms[0].gnum_city_loc_code.disabled=true
		document.forms[0].hrgstr_city_location.disabled=true
		document.forms[0].hrgstr_house_no.disabled=true
		document.forms[0].hrgstr_street_no.disabled=true
		document.forms[0].hrgstr_district.disabled=true
		document.forms[0].hrgstr_city.disabled=true
		document.forms[0].hrgnum_pincode.disabled=true
		document.forms[0].hrgstr_district.disabled=true
		document.forms[0].num_dist_id.disabled=true
		//document.forms[0].hrgstr_contact_no.disabled=true
		//document.forms[0].hrgnum_is_urban.disabled=true
		document.forms[0].gstr_tehsil.disabled=true
		document.forms[0].num_dist_id.disabled=true
	}
	else
	{
		document.forms[0].hrgstr_fname.disabled=false
		document.forms[0].hrgstr_mname.disabled=false
		document.forms[0].hrgstr_lname.disabled=false
		document.forms[0].hrgstr_father_name.disabled=false
		document.forms[0].hrgstr_spousename.disabled=false
		//document.forms[0].gnum_dept_code.disabled=false
		document.forms[0].hrgnum_puk.disabled=false
		document.forms[0].gnum_country_code.disabled=false
		document.forms[0].gnum_state_code.disabled=false
		document.forms[0].hrgstr_state_name.disabled=false
		//document.forms[0].gnum_city_loc_code.disabled=false
		document.forms[0].hrgstr_city_location.disabled=false
		document.forms[0].hrgstr_house_no.disabled=false
		document.forms[0].hrgstr_street_no.disabled=false
		document.forms[0].hrgstr_district.disabled=false
		document.forms[0].hrgstr_city.disabled=false
		document.forms[0].hrgnum_pincode.disabled=false
		document.forms[0].hrgstr_district.disabled=false
		document.forms[0].num_dist_id.disabled=false
		//document.forms[0].hrgstr_contact_no.disabled=false
		//document.forms[0].hrgnum_is_urban.disabled=false
	}
}
function isBroughtDeadChecked()
{
	 
	if(document.forms[0].hrgnum_is_broughtdead.checked && document.forms[0].hrgnum_isunknown.checked)
	{
		document.forms[0].hrgstr_fname.disabled=true
		document.forms[0].hrgstr_mname.disabled=true
		document.forms[0].hrgstr_lname.disabled=true
		document.forms[0].hrgstr_father_name.disabled=true
		document.forms[0].hrgstr_spousename.disabled=true
		//document.forms[0].gnum_dept_code.disabled=true
		document.forms[0].hrgnum_puk.disabled=true
		document.forms[0].gnum_country_code.disabled=true
		document.forms[0].gnum_state_code.disabled=true
		document.forms[0].hrgstr_state_name.disabled=true
		//document.forms[0].gnum_city_loc_code.disabled=true
		document.forms[0].hrgstr_city_location.disabled=true
		document.forms[0].hrgstr_house_no.disabled=true
		document.forms[0].hrgstr_street_no.disabled=true
		document.forms[0].hrgstr_district.disabled=true
		document.forms[0].hrgstr_city.disabled=true
		document.forms[0].hrgnum_pincode.disabled=true
		document.forms[0].hrgstr_district.disabled=true
		document.forms[0].num_dist_id.disabled=true
		//document.forms[0].hrgstr_contact_no.disabled=true
		//document.forms[0].hrgnum_is_urban.disabled=true
	}
	else if(!document.forms[0].hrgnum_isunknown.checked)
	{
		document.forms[0].hrgstr_fname.disabled=false
		document.forms[0].hrgstr_mname.disabled=false
		document.forms[0].hrgstr_lname.disabled=false
		document.forms[0].hrgstr_father_name.disabled=false
		document.forms[0].hrgstr_spousename.disabled=false
		//document.forms[0].gnum_dept_code.disabled=false
		document.forms[0].hrgnum_puk.disabled=false
		document.forms[0].gnum_country_code.disabled=false
		document.forms[0].gnum_state_code.disabled=false
		document.forms[0].hrgstr_state_name.disabled=false
		//document.forms[0].gnum_city_loc_code.disabled=false
		document.forms[0].hrgstr_city_location.disabled=false
		document.forms[0].hrgstr_house_no.disabled=false
		document.forms[0].hrgstr_street_no.disabled=false
		document.forms[0].hrgstr_district.disabled=false
		document.forms[0].hrgstr_city.disabled=false
		document.forms[0].hrgnum_pincode.disabled=false
		document.forms[0].hrgstr_district.disabled=false
		document.forms[0].num_dist_id.disabled=false
		//document.forms[0].hrgstr_contact_no.disabled=false
		//document.forms[0].hrgnum_is_urban.disabled=false
	}
}

function isMlcChecked()
{
	 
	if(document.forms[0].hrgnum_is_mlc.checked && document.forms[0].hrgnum_isunknown.checked)
	{
		document.forms[0].hrgstr_fname.disabled=true
		document.forms[0].hrgstr_mname.disabled=true
		document.forms[0].hrgstr_lname.disabled=true
		document.forms[0].hrgstr_father_name.disabled=true
		document.forms[0].hrgstr_spousename.disabled=true
		//document.forms[0].gnum_dept_code.disabled=true
		document.forms[0].hrgnum_puk.disabled=true
		document.forms[0].gnum_country_code.disabled=true
		document.forms[0].gnum_state_code.disabled=true
		document.forms[0].hrgstr_state_name.disabled=true
		//document.forms[0].gnum_city_loc_code.disabled=true
		document.forms[0].hrgstr_city_location.disabled=true
		document.forms[0].hrgstr_house_no.disabled=true
		document.forms[0].hrgstr_street_no.disabled=true
		document.forms[0].hrgstr_district.disabled=true
		document.forms[0].hrgstr_city.disabled=true
		document.forms[0].hrgnum_pincode.disabled=true
		document.forms[0].hrgstr_district.disabled=true
		document.forms[0].num_dist_id.disabled=true
		//document.forms[0].hrgstr_contact_no.disabled=true
		//document.forms[0].hrgnum_is_urban.disabled=true
	}
	else if(!document.forms[0].hrgnum_isunknown.checked)
	{
		document.forms[0].hrgstr_fname.disabled=false
		document.forms[0].hrgstr_mname.disabled=false
		document.forms[0].hrgstr_lname.disabled=false
		document.forms[0].hrgstr_father_name.disabled=false
		document.forms[0].hrgstr_spousename.disabled=false
		//document.forms[0].gnum_dept_code.disabled=false
		document.forms[0].hrgnum_puk.disabled=false
		document.forms[0].gnum_country_code.disabled=false
		document.forms[0].gnum_state_code.disabled=false
		document.forms[0].hrgstr_state_name.disabled=false
		//document.forms[0].gnum_city_loc_code.disabled=false
		document.forms[0].hrgstr_city_location.disabled=false
		document.forms[0].hrgstr_house_no.disabled=false
		document.forms[0].hrgstr_street_no.disabled=false
		document.forms[0].hrgstr_district.disabled=false
		document.forms[0].hrgstr_city.disabled=false
		document.forms[0].hrgnum_pincode.disabled=false
		document.forms[0].hrgstr_district.disabled=false
		document.forms[0].num_dist_id.disabled=false
		//document.forms[0].hrgstr_contact_no.disabled=false
		//document.forms[0].hrgnum_is_urban.disabled=false
	}
}

function openPopUpShowImage(e,path)
{
	openPopup(path,e,300,600);
}


var queryString;

function sendDataForStateChange(obj)
{	
	var stateCode=obj.value;
	queryString='patAddStateCode='+stateCode;
	//alert("queryString = "+queryString)
	if(stateCode!="" && stateCode!='-1')
    {
 	var url="/AHIMS/DistrictListByState";
 	}
 	httpRequestForStateChange("POST",url,true)
 	
}

function httpRequestForStateChange(reqType,url,asynch)
{
	if(window.XMLHttpRequest)
	{
	 request = new XMLHttpRequest();
	 //alert("request"+request)
	 
 	 initReqForStateChange(reqType,url,asynch);
	}
	else
	 if (window.ActiveXObject)
	 {
 		request=new ActiveXObject("Msxml2.XMLHTTP");
		 if (! request)
		 {
		 request=new ActiveXObject("Microsoft.XMLHTTP");
		 }
		 if(request){
 		initReqForStateChange(reqType,url,asynch);
		 }
		else 
		{
		 alert("Your browser does not permit the use of all of this application's features!");
		 }
	 }
	 else
	 {
		 alert("Your browser does not permit the use of all of this application's features!");
	}

}

function initReqForStateChange(reqType,url,isAsynch)
{
 request.onreadystatechange=handleResponseForStateChange;
 request.open(reqType,url,isAsynch);
 request.setRequestHeader("Content-Type","application/x-www-form-urlencoded; charset=UTF-8");
 request.send(queryString);

}

function handleResponseForStateChange()
{
 if(request.readyState == 4)
 {
	 if(request.status == 200)
	 {
	 
	 var responseString=request.responseText
	 document.getElementById("districtCombo").innerHTML=" <select name='hrgstr_district' tabindex='1' style='font-family: Verdana; font-size: 12px; font-style: normal; text-decoration: none;  height:20px;width: 115px;'><option value='-1'>Select Value</option>"+responseString+"</select>"
	 document.forms[0].hrgstr_district.value="-1"
	 document.forms[0].hrgnum_pincode.value=""
	 document.forms[0].hrgstr_city_location.value=""
	 //document.forms[0].hrgnum_is_urban.value="0"
			
	 showLocation()
	 }
	 else
	 {
	 alert("A problem occurred with communicating between the XMLHttpRequest object and the server program.");
	 }
 }
}

var queryString;

function sendDataForCityLocation(obj)
{	
	var locationCode=obj.value;
	queryString='patAddCityLocCode='+locationCode;
	if(locationCode!="" && locationCode!='-1')
    {
 	var url="/AHIMS/DetailByCityLocation";
 	}
 	httpRequestForCityLocation("POST",url,true)
 	
}


function httpRequestForCityLocation(reqType,url,asynch)
{
	if(window.XMLHttpRequest)
	{
	 request = new XMLHttpRequest();
	 //alert("request"+request)
	 
 	 initReqForCityLocation(reqType,url,asynch);
	}
	else
	 if (window.ActiveXObject)
	 {
 		request=new ActiveXObject("Msxml2.XMLHTTP");
		 if (! request)
		 {
		 request=new ActiveXObject("Microsoft.XMLHTTP");
		 }
		 if(request){
 		initReqForCityLocation(reqType,url,asynch);
		 }
		else 
		{
		 alert("Your browser does not permit the use of all of this application's features!");
		 }
	 }
	 else
	 {
		 alert("Your browser does not permit the use of all of this application's features!");
	}

}

function initReqForCityLocation(reqType,url,isAsynch)
{
//alert("url"+url)
//alert("reqType"+reqType)
//alert("isAsynch"+isAsynch)
//alert("queryString"+queryString)
 request.onreadystatechange=handleResponseForCityLocation;
 request.open(reqType,url,isAsynch);
 request.setRequestHeader("Content-Type","application/x-www-form-urlencoded; charset=UTF-8");
 request.send(queryString);

}

function handleResponseForCityLocation()
{
 if(request.readyState == 4)
 {
	 if(request.status == 200)
	 {
	 
	 var responseString=request.responseText
	 var responseArray=responseString.split('^')
	// alert("responseString"+responseString)
	 	document.forms[0].num_dist_id.value=responseArray[0]
	 	//alert(responseArray[0])
	 	//alert(document.forms[0].hrgstr_district.value)
	 	document.forms[0].hrgnum_pincode.value=responseArray[1]
		document.forms[0].hrgstr_city.value=responseArray[2]
		//document.forms[0].hrgnum_is_urban.value=responseArray[3]
		
		//////Setting value in hidden field for employee////////
	
	 }
	 else
	 {
	 alert("A problem occurred with communicating between the XMLHttpRequest object and the server program.");
	 }
 }
}

function validateDate(){
	var fromDate=document.getElementsByName("fromDate")[0]
	var toDate=document.getElementsByName("toDate")[0]
	var sysdate=document.getElementsByName("sysdate")[0]
	if(fromDate.value!="" && toDate.value!=""){
		if(!compareDate(fromDate,toDate,2)){
			alert("From Date cannot be greater than To Date")
			return false;
		}
	}	
	if(!compareDate(fromDate,sysdate,2)){
			alert("From Date cannot be greater than Current Date")
			return false;
	}
	if(!compareDate(toDate,sysdate,2)){
			alert("To Date cannot be greater than Current Date")
			return false;
	}
	return true;
	
}

function validateDate(){
	var fromDate=document.getElementsByName("fromDate")[0]
	var toDate=document.getElementsByName("toDate")[0]
	var sysdate=document.getElementsByName("sysdate")[0]
	if(fromDate.value!="" && toDate.value!=""){
		if(!compareDate(fromDate,toDate,2)){
			alert("From Date cannot be greater than To Date")
			return false;
		}
	}	
	if(!compareDate(fromDate,sysdate,2)){
			alert("From Date cannot be greater than Current Date")
			return false;
	}
	if(!compareDate(toDate,sysdate,2)){
			alert("To Date cannot be greater than Current Date")
			return false;
	}
	return true;
	
}
function crNoSearchCriteria(){
	console.log("inside crNoSearchCriteria()");
	if(document.getElementsByName("hrgnum_puk")[0].value.trim()=="")
		return false;
	else
		return true;
}
function dateRangeSearchCriteria(){
	console.log("inside dateRangeSearchCriteria()");
	var fromDate=document.getElementsByName("fromDate")[0].value;
	var toDate=document.getElementsByName("toDate")[0].value;
	if(fromDate=="" && toDate=="")
		return false;
	else
		return true;
}
function patNameSearchCriteria(){
	console.log("inside patNameSearchCriteria()");

	var fname=document.getElementsByName("hrgstr_fname")[0].value;
	var mname=document.getElementsByName("hrgstr_mname")[0].value;
	var lname=document.getElementsByName("hrgstr_lname")[0].value;
	
	if(fname.trim()=="" && mname.trim()=="" && lname.trim()=="" )
		return false;	
	else
		return true;
		
	
}

function submitForm(mode)
{    
    //alert("submitform....");
    document.getElementsByName("hmode")[0].value=mode;
    document.forms[0].submit();
	 
}
--></script>

<body >
<logic:equal name="patientEnquiryFB" property="isDirectCall" value="DIRECT">
	<form action="<his:path src='/enquiry/patientEnquiry.cnt'/>">
</logic:equal>
<%@page import="registration.*,enquiry.*"%>

<his:TitleTag name="Patient Enquiry">
</his:TitleTag>
<his:SubTitleTag name="Patient Detail">
<table width="100%">
		<tr>
        	<td>	
            	<div align="right">  
            		<font color="#ffffff" size="2" face="Verdana, Arial, Helvetica, sans-serif">
          		   <html:radio name="patientEnquiryFB" property="isCurrentHospitalSearch" value="0"></html:radio><b>All</b>&nbsp;
          		   <html:radio name="patientEnquiryFB" property="isCurrentHospitalSearch" value="1"></html:radio><b>Current Hospital</b>
          		   </font>
                </div>
             </td>       
		</tr>
	</table>
</his:SubTitleTag>

<his:ContentTag>
	<table  width="100%" border="0" cellspacing="1" cellpadding="0">
		<tr>
			<td width="16%"  class="tdfonthead">
				<div align="right">	 <font color="red">*</font>          
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<bean:message key="fname"/></font></div>
	  		</td>
	  		<td width="16%" class="tdfont">
	  			<div align="left">
				<html:text name="patientEnquiryFB"  maxlength="30" size="25" property="hrgstr_fname" styleClass ="textbox" tabindex="1" onkeypress="return validateAlphabetsWithDotsAndForwardSlashWithHypen(event,this)" />
	  			</div>
	  		</td>
	  		
	  		<td width="16%"  class="tdfonthead">
				<div align="right">	           
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<bean:message key="mname"/></font></div>
	  		</td>
	  		<td width="16%" class="tdfont">
	  			<div align="left">
				<html:text name="patientEnquiryFB"  maxlength="20" size="25" property="hrgstr_mname"  styleClass ="textbox" tabindex="1" onkeypress="return validateAlphabetsWithDotsOnly(event,this)" />
	  			</div>
	  		</td>
	  		
	  		<td width="16%"  class="tdfonthead">
				<div align="right">	           
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<bean:message key="lname"/></font></div>
	  		</td>
	  		<td width="16%" class="tdfont">
	  			<div align="left">
				<html:text name="patientEnquiryFB"  maxlength="30" size="25" property="hrgstr_lname" styleClass ="textbox" tabindex="1" onkeypress="return validateAlphabetsWithDotsOnly(event,this)" />
	  			</div>
	  		</td>
	  		
	 	</tr>
		<tr>
			<td width="16%"  class="tdfonthead">
				<div align="right">	           
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<bean:message key="fatherName"/></font></div>
	  		</td>
	  		<td width="16%" class="tdfont">
	  			<div align="left">
				<html:text name="patientEnquiryFB"  maxlength="60" size="30" property="hrgstr_father_name" styleClass="textbox" tabindex="1" onkeypress="return validateAlphabetsWithDotsOnly(event,this)" ></html:text>
	  			</div>
	  		</td>
	  		<td width="16%"  class="tdfonthead">
				<div align="right">	           
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<bean:message key="husbandName"/></font></div>
	  		</td>
	  		<td width="16%" class="tdfont">
	  			<div align="left">
				<html:text name="patientEnquiryFB"  maxlength="60" size="30" property="hrgstr_spousename" styleClass="textbox" tabindex="1" onkeypress="return validateAlphabetsWithDotsOnly(event,this)" ></html:text>
	  			</div>
	  		</td>
	  		<td width="16%" class="tdfonthead" >	  
           		<div>
           			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
           			<bean:message key="ageRange"/></font>
           		</div>
            </td>       
 			<td width="16%" class="tdfont" >
 			 	<div>
					<html:select name="patientEnquiryFB" property="hrgstr_age" styleClass="regcbo" tabindex="1">
						<html:option value="-1">Select Value</html:option>
						<html:option value="0-1">Below 1 Year</html:option>
						<html:option value="1-5">1-5 Year</html:option>
						<html:option value="5-12">5-12 Year</html:option>
						<html:option value="12-18">12-18 Year</html:option>
						<html:option value="18-30">18-30 Year</html:option>
						<html:option value="30-40">30-40 Year</html:option>
						<html:option value="40-60">40-60 Year</html:option>
						<html:option value="60-125">60-125 Year</html:option>
					
				</html:select>
				</div>
	 	    </td>
	  	</tr>
		
		<tr>
		  <td width="16%"  class="tdfonthead">
				<div align="right">	 <font color="red">*</font>            
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<bean:message key="gender"/></font></div>
	  		</td>
	  		<td width="16%" class="tdfont">
	  			<div align="left" >
	  			<html:select name="patientEnquiryFB" tabindex="1" property="gnum_gender_code" styleClass="regcbo" >
					<html:option value="-1">Select Value</html:option>
  					<html:options  collection="<%=RegistrationConfig.ESSENTIALBO_OPTION_GENDER%>" property="value" labelProperty="label" />
	  			</html:select>
	  			</div>
	  		</td>
		   <td width="16%" class="tdfonthead" >	  
             	  <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
                     <bean:message key="fromDate"/>
                  </font>
             </td> 
             <td  width="16%" class="tdfont" >
	    			               		 
					<his:date name='fromDate' dateFormate="%d-%b-%Y" />
				
             </td>
             <td width="16%" class="tdfonthead" >	  
             		 
                      <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
                      <bean:message key="toDate"/></font>
             </td> 
             <td width="16%" class="tdfont" >
	    			               		 
					<his:date name='toDate' dateFormate="%d-%b-%Y" />
				
             </td>
           
             
		</tr>
		<tr>
		 <td width="16%" class="tdfonthead" >	  
       		    <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
                <bean:message key="crNo"/></font>
             </td> 
             <td  width="16%" class="tdfont">
            <div align="left">
				<html:text name="patientEnquiryFB"  maxlength="13" size="20" property="hrgnum_puk" styleClass="textbox" tabindex="1" onkeypress="return validateNumeric(event)"  ></html:text>
	  		</div>
  		</td>
  		<td width="16%" class="tdFontHead" >
			<div align="right">
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<bean:message key="department" />
				</font>
			</div>
		</td>
  		<td width="16%" class="tdfont">
			<div align="left">
				<html:select name="patientEnquiryFB"
					tabindex="1" property="gnum_dept_code" styleClass="regcbo">
					<html:option value="%">All Department</html:option>
					<html:options
						collection="<%=RegistrationConfig.ESSENTIAL_BO_OPTION_ALLDEPT%>"
						property="value" labelProperty="label" />
				</html:select>
			</div>
		</td>
		<td class="tdFontHead">
		<div align="right">
           		 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			  	  <bean:message key="mobileNo"/></font>
			  </div>
		</td>
		<td class="tdFont">
		 <div id="divpatAddMobileText" style="" align="left">
                   <html:text name="patientEnquiryFB" tabindex="1" property="hrgstr_mobile_no" styleClass="regcbo" >
                   </html:text>
                   </div>     
		</td>
		
		</tr>
		
		<tr>
			
     		<td class="tdfonthead" width="16%">
			  <div align="right">
			  		<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			  <bean:message key="unknown"/></font></div>
		    </td>
		  
		   <td class="tdfont" align="left" width="16%">
		      <html:checkbox  name="patientEnquiryFB" property="hrgnum_isunknown" value="1" tabindex="1" onclick="unknownChecked();"/>	      
		   </td>
            <td width="13%" class="tdfonthead">
          	<div align="right">
          		<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			  <bean:message key="isbroughtdead"/></font>
			  </div>
			
  			</td>
			<td class="tdfont" align="left" width="16%">
		      <html:checkbox  name="patientEnquiryFB" property="hrgnum_is_broughtdead" value="1" tabindex="1" onclick="isBroughtDeadChecked();"/>	      
		   </td>
             
             <td width="16%" class="tdfonthead">
             	<div align="right">
           		 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			  	  <bean:message key="mlc"/></font>
			  </div>
			
	  		</td>
	  		<td class="tdfont" align="left" width="16%">
		      <html:checkbox  name="patientEnquiryFB" property="hrgnum_is_mlc" value="1" tabindex="1" onclick="isMlcChecked();"/>	      
		   </td>
    	</tr>
	  				
	</table>
</his:ContentTag>
  <his:SubTitleTagBroad name="Address Detail">
		   <table width="100%">
		   <tr>
             <td>	
            	 <div align="right">  
          		   <font color="#ffffff" size="2" face="Verdana, Arial, Helvetica, sans-serif">
                   <b><bean:message key="country"/></b></font>
             </td>       
 			 <td >
 			 	<div align="left">
		 	       <html:select name="patientEnquiryFB" tabindex="1" property="gnum_country_code"  styleClass="regcbo" onchange="if(this.value!='-1') showState()">
	                 	<html:option value="">Select Value</html:option>
		  		   <html:options collection ="<%=RegistrationConfig.ESSENTIALBO_OPTION_COUNTRY %>" property = "value" labelProperty = "label"/>
	                    </html:select>	
				</div>
             </td>
           	<td>
             	<div id="stateMandotary" style="" align="right" >
             		 <b> <font color="#ffffff" size="2" face="Verdana, Arial, Helvetica, sans-serif">
             		  		<bean:message key="state"/></b>
             		  </font>
             	 </div>
              <div id="stateNotMandotary" style="" align="right" >
             		  <font color="#ffffff" size="2" face="Verdana, Arial, Helvetica, sans-serif">
             		  		<b><bean:message key="state"/></b>
             		  </font>
              </div>
             </td>
             <td>     
           		 <div id="divpatAddStateCodeCombo" style="" align="left">                 
                   <html:select name="patientEnquiryFB" tabindex="1" property="gnum_state_code" styleClass="regcbo" onchange="if(this.value!='-1') sendDataForStateChange(this)" >
                     <html:option value="-1">Select Value</html:option>
                     <html:options collection = "<%=RegistrationConfig.ESSENTIALBO_OPTION_STATE %>" property = "value" labelProperty = "label"/>
                   </html:select>    
                   </div> 
                   <div id="divpatAddStateCodeText" style="" align="left">
                   <html:text name="patientEnquiryFB" tabindex="1" property="hrgstr_state_name" styleClass="regcbo" >
                   </html:text>
                   </div>                 
             </td>
             </tr>
             </table>
      </his:SubTitleTagBroad>  
             
             
             
             
             
 <his:ContentTag>
 <table width="100%"  cellspacing="1" cellpadding="0">     
 <tr>
					<td class="tdfonthead" width="14%"><bean:message key="hno" /></td>
					<td class="tdfont" width="17%"><html:text
						name="patientEnquiryFB"
						onkeypress="return validateAlphaNumericWithDotsOnly(event,this);"
						property="hrgstr_house_no" tabindex="2" maxlength="15"
						styleClass="textbox" /></td>
					<td class="tdfonthead" width="18%"><bean:message key="street" /></td>
					<td class="tdfont" width="17%"><html:text
						name="patientEnquiryFB" property="hrgstr_street_no"
						onkeypress="return validateAlphaNumericWithDotsOnly(event,this);"
						tabindex="2" maxlength="30" styleClass="textbox" /></td>
					<td class="tdfonthead" width="17%"><bean:message key="location" />
					</td>
					<td class="tdfont" width="17%">
					
					<div id="divpatAddCityLocation"><html:text
						name="patientEnquiryFB" tabindex="2" maxlength="50"
						onkeypress="return validateAlphaNumericWithDotsOnly(event,this);"
						styleClass="textbox" property="hrgstr_city_location" /></div>
					</td>
				</tr>
				<tr>
					<td class="tdfonthead" width="14%"><bean:message key="district" />
					</td>
					<td class="tdfont" width="17%">
					<div id="districtCombo"><html:select
						name="patientEnquiryFB" tabindex="2" styleClass="regcbo"
						property="num_dist_id">
						<html:option value="-1">Select Value</html:option>
						<html:options
							collection="<%=RegistrationConfig.ESSENTIAL_BO_OPTION_DISTRICT_LIST_STATEWISE%>"
							property="value" labelProperty="label" />
					</html:select></div>
					<div id="districtComboNonDefault" style="display: none;"><select
						name="num_dist_id" tabindex="2" class="regcbo">
						<option value="-1">Select Value</option>
					</select></div>
					<div id="districtTextBox" style="display: none"><html:text
						name="patientEnquiryFB" tabindex="2"
						property="hrgstr_district" onchange="isAlpha(this,'District')"
						onkeypress="return validateAlphabetsWithDotsOnly(event)"
						maxlength="30" styleClass="textbox" /></div>
					</td>
					<td class="tdfonthead" width="18%">
					<bean:message key="city" /> <bean:message key="slash" /> <bean:message
						key="village" /></td>
					<td class="tdfont" width="17%"><html:text
						name="patientEnquiryFB" tabindex="1"
						property="hrgstr_city" onchange="isAlpha(this,'City')"
						onkeypress="return validateAlphabetsWithDotsOnly(event,this);"
						maxlength="30" styleClass="textbox" /></td>
					<td class="tdfonthead" width="14%"><bean:message key="pin" /></td>
					<td class="tdfont" width="17%"><input type="text" id="pintext"
						name="hrgnum_pincode" tabindex="2" size="17" maxlength="6"
						onkeypress="return validateNumeric(event)" /></td>
				</tr>
				<tr style="display:none">
					<td class="tdfonthead" width="17%"><bean:message key="tehsil" /></td>
					<td class="tdfont" width="17%">
					<div><html:text name="patientEnquiryFB"
						tabindex="2" property="gstr_tehsil" maxlength="30"
						styleClass="textbox" onkeypress="return validateAlphabetsOnly(event,this)" />
					</div>
					</td>
					<td width="18%" class="tdfonthead">
					</td>
					<td width="18%" class="tdfont"></td>
					<td class="tdfonthead" width="18%"></td>
					<td class="tdfont" width="18%"></td>
				</tr>
    
       </table>
 </his:ContentTag>
 <his:ButtonToolBarTag>
 		<img class="button" style="cursor:pointer" src="/HIS/hisglobal/images/buttons/btn-search.png" tabindex="1" alt="Search" title="Search" onclick="validatePatientEnquiry();"  onkeypress="if(event.keyCode==13) validatePatientEnquiry();"> 
 		<logic:equal name="patientEnquiryFB" property="isDirectCall" value="DIRECT">
 		<img class="button" src="/HIS/hisglobal/images/buttons/btn-ccl.png" tabindex="1"  style=cursor:pointer onkeypress="if(event.keyCode==13) submitPage('CANCEL');" tabindex="1" onclick ="submitPage('CANCEL');">
 		</logic:equal>
 		<logic:notEqual name="patientEnquiryFB" property="isDirectCall" value="DIRECT">
 		<img class="button" src="/HIS/hisglobal/images/buttons/btn-ccl.png" tabindex="1"  style=cursor:pointer onkeypress="if(event.keyCode==13)submitDesk('NEW');" tabindex="1" onclick ="submitDesk('NEW');">
 		</logic:notEqual>
	  <his:statusTransactionInProcess>
	  <logic:equal name="patientEnquiryFB" property="isDirectCall" value="DIRECT">
	  <img class="button" src="/HIS/hisglobal/images/buttons/btn-clr.png" tabindex="1"  style=cursor:pointer onclick ="submitForm('unspecified')" onkeypress="if(event.keyCode==13) submitForm('unspecified');">
       </logic:equal>
	  <logic:equal name="patientEnquiryFB" property="isDirectCall" value="DESK">
	  <img class="button" src="/HIS/hisglobal/images/buttons/btn-clr.png" tabindex="1"  style=cursor:pointer onclick ="submitForm('NEW')" onkeypress="if(event.keyCode==13) submitForm('NEW');">
       </logic:equal>
       </his:statusTransactionInProcess>       
 </his:ButtonToolBarTag>
 
 <his:statusTransactionInProcess>
 <strong>Search Result</strong>
 <his:ContentTag>
 <%String address; %>
 	<table width="100%"  cellspacing="1" cellpadding="0">                    
		 <tr>
             <td  class="tdfonthead" width="10%">
	            <div align="center">
	            <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"><b>
	            <bean:message key="crNo"/></b>
	            </font>
	            <a style="">
					<img src="/AHIMS/hisglobal/images/arrow_up.gif" style="cursor: pointer;" alt="Ascending order" onclick="getOrderBy('ORDERBYCRNO','0')" tabindex="1" border="0" height="10" width="10">
				</a>
				<a style="">
						<img src="/AHIMS/hisglobal/images/arrow_down.gif" style="cursor: pointer;" alt="Desccending order" onclick="getOrderBy('ORDERBYCRNO','1')" tabindex="1" border="0" height="10" width="10">
				</a>
	            </div>
             </td>
             <td  class="tdfonthead" width="16%">
	            <div align="center">
	            <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"><b>
	            <bean:message key="patientName"/></b>
	            </font>
	            <a style="">
					<img src="/AHIMS/hisglobal/images/arrow_up.gif" style="cursor: pointer;" alt="Ascending order" onclick="getOrderBy('ORDERBYNAME','0')" tabindex="1" border="0" height="10" width="10">
				</a>
				<a style="">
					<img src="/AHIMS/hisglobal/images/arrow_down.gif" style="cursor: pointer;" alt="Desccending order" onclick="getOrderBy('ORDERBYNAME','1')" tabindex="1" border="0" height="10" width="10">
				</a>
	            </div>
             </td>
             <td  class="tdfonthead" width="10%">
	            <div align="center">
	            <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"><b>
	            <bean:message key="visitDate"/></b>
	            </font>
	            
	            </div>
             </td>
             <td  class="tdfonthead" width="10%">
	            <div align="center">
	            <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"><b>
	            <bean:message key="department"/></b>
	            </font>
	            </div>
             </td>
             
             <td  class="tdfonthead" width="8%">
	            <div align="center">
	            <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"><b>
	            <bean:message key="gender/age"/></b>
	            </font>
	            </div>
             </td>
             
             <td  class="tdfonthead" width="10%">
	            <div align="center">
	            <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"><b>
	            <bean:message key="status"/></b>
	            </font>
	            </div>
             </td>
             <td  class="tdfonthead" width="10%">
	            <div align="center">
	            <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"><b>
	            <bean:message key="fatherSpouseName"/></b>
	            </font>
	            </div>
             </td>
             <td  class="tdfonthead" width="25%">
	            <div align="center">
	            <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"><b>
	            <bean:message key="address"/></b>
	            </font>
	            </div>
             </td>
             <td  class="tdfonthead" width="10%">
	            <div align="center">
	            <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"><b>
	            <bean:message key="hospital"/></b>
	            </font>
	            </div>
             </td>
              <!--<td  class="tdfonthead" width="5%">
	            <div align="center">
	            <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	            <bean:message key="image"/>
	            </font>
	            </div>
             </td>
 		--></tr>
 		<logic:present name="<%=enquiryConfig.PATIENT_ENQUIRY_COMMON_ENQUIRY_VO %>">
 		<logic:iterate id="commonEnqVO" name="<%=enquiryConfig.PATIENT_ENQUIRY_COMMON_ENQUIRY_VO %>" type="hisglobal.vo.CommonEnquiryVO">
 			<tr>
 			<td class="tdfont">
	  			<div align="center">
	  			<%=commonEnqVO.getHrgnum_puk() %>	
	  			</div>
	  		</td>
	  		<td class="tdfont"> 
	  			<div align="center">
	  			<%=commonEnqVO.getHrgstr_fname()+" "+
	  			commonEnqVO.getHrgstr_mname()+" "+
	  			commonEnqVO.getHrgstr_lname()%>	
	  			</div>
	  		</td>
            <td class="tdfont">
	  			<div align="center">
	  			<%=commonEnqVO.getHrgdt_episode_date() %>
	  			</div>
	  		</td>
	  		 <td class="tdfont">
	  			<div align="center">
	  			<%=commonEnqVO.getGstr_dept_name() %>	
	  			</div>
	  		</td>
	  		
	  		<td class="tdfont">
	  			<div align="center">
	  			<%=commonEnqVO.getGstr_gender_name()+"/"+commonEnqVO.getHrgstr_age() %>	
	  			</div>
	  		</td>
	  		
	  		<td class="tdfont">
	  			<div align="center">
	  			<%=commonEnqVO.getHgnum_pat_status_code() %>	
	  			</div>
	  		</td>
	  		
	  		<td class="tdfont" >
	  			<div align="center">
	  			<%
	  			if(commonEnqVO.getHrgstr_fhname()==null)
	  				commonEnqVO.setHrgstr_fhname("");
	  			if(commonEnqVO.getHrgstr_spousename()==null)
	  				commonEnqVO.setHrgstr_spousename("");
	  			%>
	  			<%=commonEnqVO.getHrgstr_fhname()+" / "+ commonEnqVO.getHrgstr_spousename()%>	
	  			
	  			</div>
	  		</td>
	  		
	  		<td class="tdfont" >
	  			<div align="center">
	  			<% address="";
	  				if(!commonEnqVO.getHrgstr_house_no().equals(""))
	  					address=address+commonEnqVO.getHrgstr_house_no()+", ";
	  				if(!commonEnqVO.getHrgstr_street_no().equals(""))
	  					address=address+ commonEnqVO.getHrgstr_street_no()+", ";
	  				if(!commonEnqVO.getHrgstr_city_location().equals(""))
	  					address=address+commonEnqVO.getHrgstr_city_location()+", ";
	  				if(!commonEnqVO.getHrgstr_city().equals(""))
	  					address=address+commonEnqVO.getHrgstr_city()+", ";
	  				if(!commonEnqVO.getGstr_tehsil().equals(""))
	  					address=address+commonEnqVO.getGstr_tehsil()+", ";
	  				if(!commonEnqVO.getHrgstr_state_name().equals(""))
	  					address=address+commonEnqVO.getHrgstr_state_name()+", " ;
	  				if(!commonEnqVO.getGstr_countryname().equals(""))
	  					address=address+commonEnqVO.getGstr_countryname() ;
	  				if(commonEnqVO.getHrgnum_pincode().equals("0")|| commonEnqVO.getHrgnum_pincode().equals(""))
	  				{
	  					address=address;
	  					
	  				}
	  				else
	  					address=address+" , \n"+commonEnqVO.getHrgnum_pincode();
	  			%>
	  			<%=address%>	
	  			</div>
	  		</td>
	  		<td class="tdfont">
	  			<div align="center">
	  			<%=commonEnqVO.getGstr_hospital_name() %>	
	  			</div>
	  		</td>
	  		<% String imageuploaded=commonEnqVO.getPatientImage();
	  		if(imageuploaded!=null){
	  		String path="";
// 	  		String fileName=imageuploaded.substring(imageuploaded.lastIndexOf("/")+1);
// 	  		fileName=fileName.replace("#","%23");
// 					String path = ServletsUtilityConfig.SERVLET_DISPLAY_FILE_URL + "?" 
// 							+ ServletsUtilityConfig.FILE_PATH_WINDOWS + "=" + Config.PATIENT_IMAGE_FILE_STORAGE_PATH + "&" 
// 							+ ServletsUtilityConfig.FILE_PATH_LINUX + "=" + Config.PATIENT_IMAGE_FILE_STORAGE_PATH_LINUX + "&" 
// 							+ ServletsUtilityConfig.FILE_NAME + "=" + fileName; 
					%>
	  		<!--<td class="tdfont">
	  			<div align="center">
	  			 <a onclick="getUploadedeFile(event,'<%=path%>')"  style="cursor: pointer;">Show Image</a> 	
	  			<%-- <a onclick="sendDataForBagType('<%=path %>','<%=imagePath %>')" style="cursor: pointer;">Show Image</a>--%>
	  			</div>
	  		</td>
	  		--><%} else{%>
	  		<!--<td class="tdfont"></td>
	  		--><%} %>
             </tr>
 		
 		</logic:iterate>
 		</logic:present>
 	</table>
 </his:ContentTag>
 
 </his:statusTransactionInProcess>
<%String sysdate=(String)WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT),"dd-MMM-yyyy"); %>
<html:hidden name="patientEnquiryFB" property="hmode"/>
<html:hidden name="patientEnquiryFB" property="sortOn"/>
<html:hidden name="patientEnquiryFB" property="order"/>
<html:hidden name="patientEnquiryFB" property="isDirectCall"/>
<html:hidden name="patientEnquiryFB" property="fromDateHidden"/>
<html:hidden name="patientEnquiryFB" property="toDateHidden"/>
<input type="hidden" name="sysdate" value="<%=sysdate %>"/>

<logic:equal name="patientEnquiryFB" property="isDirectCall" value="DIRECT">
</form>
</logic:equal>
<his:status/>
</body>
</html>

<div id="blanket" style="height: 580;width: 1024;display: none;"></div>

<div id="confirmMsg" style="display: none; height: 110px;width: 310px;position:absolute; top: 20%;left: 30%;z-index: 9100; ">
	<div id="alertMessage1" align="center" style="position: absolute;left: 30%;top: 30%;"></div>
	 <div id='close' style='position:absolute;left: 33%; top: 58%;'>
	 <input type='button' value='Close' name="yesButton" onclick='closeAlert(true)' onkeyPress='if(event.keyCode==13) closeAlert(true)'
	  tabindex='1' id='yesButton' style='font-weight:bold;margin-right:10px;cursor:pointer;left: '>
	 </div>
</div>
<%}catch(Exception e)
{
System.out.println(e.getMessage());	
}%>