function checkReferDepartment(obj)
{

	if(document.getElementsByName('patRefGnctdHospitalDept')[0].value=='0')
	{
		//alert("in 1");
		/*document.getElementById('divRefHospitalDeptOtherId')[0].style.display="";
		alert("in 2");*/
		document.getElementsByName('patRefHospitalDeptOther')[0].style.display="";
		//alert("in 3");
		document.getElementsByName('patRefHospitalDeptOther')[0].disabled=false
		document.getElementsByName('patRefHospitalDeptOther')[0].focus()
		}
	else
	{
	
	document.getElementsByName('patRefHospitalDeptOther')[0].disabled=true
	document.getElementsByName('patRefHospitalDeptOther')[0].style.display="none";
	document.getElementsByName('patRefHospitalDeptOther')[0].value=""
	document.getElementsByName('patRefGnctdHospitalDeptUnit')[0].focus();
	
	
	}
}

function spouseNameMandatory()
{
//alert("spouseNameMandatory")
	var maritalStatus=document.getElementsByName("patMaritalStatusCode")[0].value;
	
	var maritalStatusMarried="<%=RegistrationConfig.REGISTRATIONDESK_MARITAL_STATUS_MARRIED%>";
	
	var gender=document.getElementsByName("patGenderCode")[0].value;
	
	var genderFemale="<%=Config.GENDER_TYPE_FEMALE%>";
	//alert("maritalStatus="+maritalStatus+":maritalStatusMarried="+maritalStatusMarried+": genderFemale= "+genderFemale+": gender="+gender)
	//alert(maritalStatus==maritalStatusMarried)
	//alert(gender==genderFemale)
	if((maritalStatus==maritalStatusMarried) && (gender==genderFemale))
	{
	
		if(document.getElementsByName("patHusbandName")[0].value=="")
		{
	
			alert("Spouse Name Is Mandatory For Married Female")
			document.getElementsByName("patHusbandName")[0].focus()
			return false
		}
	}
	return true
}

/*
function enableSpouseField()
{
// alert("sdsdsdsdsdsds")
	var maritalStatus=document.getElementsByName("patMaritalStatusCode")[0].value;
	
	var maritalStatusSingle="<%=RegistrationConfig.REGISTRATIONDESK_MARITAL_STATUS_SINGLE%>";
	// alert("maritalStatus "+maritalStatus+" maritalStatusSingle  "+maritalStatusSingle)
	var clientValue="<%=Config.CLIENT%>";
	 var clientPGIMER="<%=Config.CLIENT_PGIMER%>";
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
  */

var queryString;
 function handlePostResponse(){
 
 if(request.readyState == 4){
 if(request.status == 200){
 // alert("xcxcxcxcxcxc")
  document.getElementsByName("patAmountCollected")[0].value=reply.substring(0,reply.indexOf('^'));
	 document.getElementsByName("isIdRequired")[0].value=reply.substring(reply.indexOf('^')+1);
	 //alert("isIdRequired"+document.getElementsByName("isIdRequired")[0].value)
	checkPatientCategory();

 	
 } else {
 alert("A problem occurred with communicating between "+
 "the XMLHttpRequest object and the server program.");
 }
 }
 //end outer if
}

function handleResponse(){
 
 if(request.readyState == 4){
 if(request.status == 200){
 // alert("xcxcxcxcxcxc")
 
 
 var reply=request.responseText
 
 
	 document.getElementsByName("patAmountCollected")[0].value=reply.substring(0,reply.indexOf('^'));
	 document.getElementsByName("isIdRequired")[0].value=reply.substring(reply.indexOf('^')+1);
	checkPatientCategory();

 	
 } else {
 alert("A problem occurred with communicating between "+
 "the XMLHttpRequest object and the server program.");
 }
 }//end outer if
}




function initReq(reqType,url,isAsynch){
// alert("inside initReq()")

 /* Specify the function that will handle the HTTP response */

 request.onreadystatechange=handleResponse;
//alert("url in initreq "+url);
 request.open(reqType,url,isAsynch);

 /* set the Content-Type header for a POST request */

 request.setRequestHeader("Content-Type",

 "application/x-www-form-urlencoded; charset=UTF-8");
//alert("query String"+queryString)
 request.send(queryString);

}



function httpRequest(reqType,url,asynch){

 //Mozilla-based browsers
//alert("inside httpRequest")
 if(window.XMLHttpRequest){

 request = new XMLHttpRequest();
 //alert("url in http"+url);
 initReq(reqType,url,asynch);

 } else if (window.ActiveXObject){
//alert("For Internet Explorer")
 request=new ActiveXObject("Msxml2.XMLHTTP");
	//alert("request object:Mscml2"+request)
 if (! request){

 request=new ActiveXObject("Microsoft.XMLHTTP");
//alert("request object:Microsoft"+request)
 }

 if(request){
//	alert("request branched here")
 initReq(reqType,url,asynch);


 /* Unlikely to branch here, as IE uses will be able to use either 

 one of the constructors*/

 } else {

 alert("Your browser does not permit the use of all "+

 "of this application's features!");}

 } else {

 alert("Your browser does not permit the use of all "+

 "of this application's features!");}

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
 
 



 
function checkvalue(){
//  alert("check val......");
if(document.getElementsByName('departmentCode')[0].value=="-1") 
alert("Select a department");
else
{
	if(document.getElementsByName('departmentCode')[0].value=="") 
	{
		alert("You Have Already Added All The Departments")
	}
	else
	{
		submitForm('ADDDEPT'); 
	}
 
 }
 }
function poulateFieldsWithEmpDtl(selectedElem){

	document.getElementsByName("empIdChk")[0].value=selectedElem;	
	submitForm("GETEMPDEPENDENTDTL");
}  

function callThisOnload(){	
	//alert("onload")
	//alert(document.getElementsByName('print')[0].value)
	
	if(document.getElementsByName('print')[0].value=='1')
	{
		//document.getElementById('divCardPrint').style.display="block"
		printPage();
		document.getElementsByName('print')[0].value='0'
		//document.getElementById('divCardPrint').style.display="none"
	}
	document.getElementsByName("departmentCode")[0].focus();
	if(document.forms[0].isOldPatient.value=="1"){
		var patCrno=document.forms[0].oldPatCrNo.value
		document.forms[0].isOldPatient.value=""
		document.forms[0].oldPatCrNo.value=""
		parent.document.getElementById("f2").src="/AHIMS/registration/commonaction.cnt?mode=OLDPATVISIT&hmode=GETPATDTL&patCrNo="+patCrno;
		//var path="/AHIMS/registration/commonaction.cnt?mode=OLDPATVISIT&hmode=GETPATDTL&patCrNo="+patCrno;
		//var child=window.open(path,"Old Patient Visit","height=400,width=600")
		//child.close();
		
	}
	var patCatCode=document.getElementsByName("patPrimaryCatCode")[0].value;
	showState();
	//showLocation();
	document.getElementsByName("departmentCode")[0].focus;
	sendData();
    if(document.getElementsByName("isDuplicatePatientPopup")[0].value=="1"){
		
		var path="/AHIMS/registration/newRegistrationDeskDuplicate.cnt?hmode=POPUP";;
		openPopup(path);
	}
	
	checkPatientCategory();
	//enableSpouseField();
	if(document.getElementsByName("errorMessage")[0].value!=""){
		//alert(document.getElementsByName("errorMessage")[0].value)
		document.getElementsByName("errorMessage")[0].value=""
	}
}

function openPopup(url)
{
	 //alert("in open popup")
	  var width=700
	  var height=400
	  var winl = (screen.width - width) / 2;
	  var wint = (screen.height - height) / 2;
	  //winprops = 'height='+height+',width='+width+',top='+wint+',left='+winl+ ' scrollbars=yes'
	 
	  popup = window.open(url,'popupWindow','width=' + width + ',height=' + height + ',top='+ wint + ',left=' + winl + ',scrollbars=yes');
	  if (!popup.opener)
	   popup.opener = self;
}

function showEmployeeDiv(){
  document.getElementById("divempIdLabel").style.display=""; 
  document.getElementById("divempIdControl").style.display=""; 
}

function showEmployeepopup(e){
    // if(!document.getElementsByName("patIdNo")[0].value=="")
    //	  openPopupWide(document.getElementsByName("patIdNo")[0].value,'<his:path src="/registration/employeeDtlWithDependents.cnt"/>',e);  
    //	  else
    //	  alert("Please enter Employee ID");
    openPopup('/AHIMS/registration/employeeDtlWithDependents.cnt',e,300,600);
  }





function primCatHandler(mode){
	submitForm(mode);		
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
	
	if(flag){
		if(document.getElementsByName("patRefGnctdHospitalCrno")[0].value!=""){
			flag=crNoCheckSum(document.getElementsByName("patRefGnctdHospitalCrno")[0].value)
		}
	}
	
	
	return flag; 
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

/** function to set the default value as female in case of Deptt Of Obstetrics and Deptt Of Gynaecology */
function setGender(obj){
	var deptCode='<%=RegistrationConfig.GENDER_SPECIFIC_DEPT_CODE%>'
	var deptCodeArray=deptCode.split(',');
	var flag=false;
	for(var i=0;i<deptCodeArray.length;i++){
		if(obj.value==deptCodeArray[i]){
			document.getElementsByName("patGenderCode")[0].value='<%=Config.GENDER_TYPE_FEMALE%>';
			flag=true;
			break;
		}	
	}	
	
	if(!flag){
		document.getElementsByName("patGenderCode")[0].value='-1';
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
	
	 	document.forms[0].patAddDistrictCode.value=responseArray[0]
	 	document.forms[0].patAddPIN.value=responseArray[1]
		document.forms[0].patAddCity.value=responseArray[2]
		document.forms[0].patIsUrban.value=responseArray[3]
		
		//////Setting value in hidden field for employee////////
		
		document.forms[0].patAddDistrictCodeHidden.value=responseArray[0]
	 	document.forms[0].patAddPINHidden.value=responseArray[1]
		document.forms[0].patAddCityHidden.value=responseArray[2]
		document.forms[0].patIsUrbanHidden.value=responseArray[3]
		
	 
	 }
	 else
	 {
	 alert("A problem occurred with communicating between the XMLHttpRequest object and the server program.");
	 }
 }
}


function printCard(){

	document.getElementsByName("hmode")[0].value="PRINTLASTOPDCARD"
	document.forms[0].submit();

}

function checkBPL()
{
	var patientPriCatCode=document.getElementsByName("patPrimaryCatCode")[0].value;
	var patBPLCode="<%=Config.PRIMARY_CATEGORY_BPL_CODE %>";
 	if(patientPriCatCode==patBPLCode)
	{
		if(document.getElementsByName("patIsUrban")[0].value=="-1")
		{	
			alert("Area Category is required field for BPL Category.")
			document.getElementsByName("patIsUrban")[0].focus()
			return false;
		}
	}
	return true;
}

//To check the CR No valid or not on checksum basis, Added by Singaravelan on 17-Jul-2015
function crNoCheckSum(crNo)
{
	if(crNo.length==13)
	  {
		var modulo_1=crNo.substring(0,1)*7 + crNo.substring(1,2)*6 + 
		crNo.substring(2,3)*5 + crNo.substring(3,4)*4 +  
		crNo.substring(4,5)*3 + crNo.substring(5,6)*2 +  
		crNo.substring(6,7)*7 + crNo.substring(7,8)*6 +
		crNo.substring(8,9)*5 + crNo.substring(9,10)*4 +  
		crNo.substring(10,11)*3 + crNo.substring(11,12)*2 ;

		var modulo=modulo_1 % 11;
		var checksum = (modulo==0)?1:(11-modulo)%10;
		if(checksum==crNo.charAt(crNo.length-1))
			return true;
	  }
	  else if(crNo.length==15)
	  {
		 var modulo_1=crNo.substring(0,1)*3 + crNo.substring(1,2)*2 + 
		 crNo.substring(2,3)*7 + crNo.substring(3,4)*6 +  
		 crNo.substring(4,5)*5 + crNo.substring(5,6)*4 +  
		 crNo.substring(6,7)*3 + crNo.substring(7,8)*2 +
		 crNo.substring(8,9)*7 + crNo.substring(9,10)*6 +  
		 crNo.substring(10,11)*5 + crNo.substring(11,12)*4 +
		 crNo.substring(12,13)*3 + crNo.substring(13,14)*2 ;

		var modulo=modulo_1 % 11;
		var checksum = (modulo==0)?1:(11-modulo)%10;
		if(checksum==crNo.charAt(crNo.length-1))
			return true;
	  }	
	  else{
		  alert("Invalid CR No.")
		  return false;
	  }
}