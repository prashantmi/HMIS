
/************************************ AJAX FUNCTION ***************************************/
/* */
/******************************************************************************************/
/*
* objXmlHttp - global variable.
* userFunction - global variable.
*/

var objXmlHttp = null;
var userMode = "";

/**
* ajaxFunction is the global function to retrieve data using ajax.
* param 1. myurl - url to be given by the user.
* param 2. mode - there will be hardcoded function i.e. getAjaxResponse(res,mode). This function will be defined by 
the developer. Mode specifies unique value provided by user at the time of calling ajaxfunction().
* This function will recieve the final response(to be defined at user end).
*/

function ajaxFunction(myurl,mode)
{
	//reg_create_loading_msg();//loading_msg
	userMode = mode;
	// checking browser 
	if (navigator.userAgent.indexOf("Opera")>=0){
		alert("This example doesn't work in Opera"); 
		return; 
	}
	if (navigator.userAgent.indexOf("MSIE")>=0) { 
		var strName="Msxml2.XMLHTTP"
		if (navigator.appVersion.indexOf("MSIE 5.5")>=0){
			strName="Microsoft.XMLHTTP"
		} 
		try { 
			objXmlHttp=new ActiveXObject(strName)
			objXmlHttp.onreadystatechange=sendReq
		} 
		catch(e){ 
			alert("Error. Scripting for ActiveX might be disabled");
			return; 
		} 
	}
	else {
		if (navigator.userAgent.indexOf("Mozilla")>=0){
			objXmlHttp=new XMLHttpRequest();
			  objXmlHttp.onload=sendReq
			  objXmlHttp.onerror=sendReq
		}
	}

	objXmlHttp.open("GET",myurl,true)
	objXmlHttp.send(null)
} 

/***loading please wait  Added by:Deepak***/
function reg_create_loading_msg(){var qh=80;var qw=300;var dh=0;var dw=0;if(window.innerHeight){dh=window.innerHeight;dw=window.innerWidth;}else {dh=document.documentElement.clientHeight;dw=document.documentElement.clientWidth;}var tpos=parseInt((dh-qh)/2);var lpos=parseInt((dw-qw)/2);var wt='<div id="qmvi_loading_div" style="top:'+tpos+'px;left:'+lpos+'px;height:'+qh+'px;width:'+qw+'px;position:absolute;text-align:center;font-family:Arial;text-decoration:none;font-weight:normal;font-size:13px;color:#00224A;background-color:#ffffff;border-width:1px;border-color:#828EA2;border-style:solid;">';wt+='<div style="padding:20px;"><img src="../../hisglobal/images/loading.gif" width=16 height=16><br><br><font size="2" weight="bold">Fetching Details.Please Wait!</font></div>';wt+='</div>';document.getElementById("normalMsg").innerHTML=wt;}
/***end***/

//internal function called from ajaxFunction() function
function sendReq()
{
    //for(var i=0;i<=500000;i=i+1);
    if (objXmlHttp.readyState==4 || objXmlHttp.readyState==200) {
		var res = objXmlHttp.responseText; 
		eval("getAjaxResponse" + '(res' + "," + userMode + ")"); // evel is a keyword - it calls the function passed in it as argument.
	     document.getElementById("normalMsg").style.display="none";//hiding normal msg
	     try{
	     	//autoTabIndexing();
	     }catch(_Err){
	     	//alert("Application Error[TabIndex not Included] Please Contact System Administrator");
	     }
	     //alert(eventElementObj.name);
	     try{
	     	eventElementObj.focus();
	     }catch(_Err){
	     	
	     }
	}
}




/**
* ajaxFunction is the global function to retrieve data using ajax.
* param 1. myurl - url to be given by the user.
* param 2. mode - there will be hardcoded function i.e. getAjaxResponse(res,mode). This function will be defined by
* param 3. resFunctionName -  function name in which user need ajax Response
the developer. Mode specifies unique value provided by user at the time of calling ajaxfunction().
* This function will recieve the final response(to be defined at user end).
*/

var gblResFunctionName = "";

function ajaxFunction2(myurl,mode,resFunctionName)
{
	
		
	userMode = mode;
	gblResFunctionName = resFunctionName;
	// checking browser 
	if (navigator.userAgent.indexOf("Opera")>=0){
		alert("This example doesn't work in Opera"); 
		return; 
	}

	if (navigator.userAgent.indexOf("MSIE")>=0) { 
		var strName="Msxml2.XMLHTTP"
		
		if (navigator.appVersion.indexOf("MSIE 5.5")>=0){
			strName="Microsoft.XMLHTTP"
		} 

		try { 
			objXmlHttp=new ActiveXObject(strName)
			objXmlHttp.onreadystatechange=sendReqUsingMethodName
		} 
		catch(e){ 
			alert("Error. Scripting for ActiveX might be disabled");
			return; 
		} 
	}
	else {
		if (navigator.userAgent.indexOf("Mozilla")>=0){
			objXmlHttp=new XMLHttpRequest();
			objXmlHttp.onload=sendReqUsingMethodName
			objXmlHttp.onerror=sendReqUsingMethodName
		}
	}

	objXmlHttp.open("GET",myurl,true)
	objXmlHttp.send(null)
} 

//internal function called from ajaxFunction() function
function sendReqUsingMethodName(){
	
	
	function reg_create_loading_msg(){var qh=80;var qw=300;var dh=0;var dw=0;if(window.innerHeight){dh=window.innerHeight;dw=window.innerWidth;}else {dh=document.documentElement.clientHeight;dw=document.documentElement.clientWidth;}var tpos=parseInt((dh-qh)/2);var lpos=parseInt((dw-qw)/2);var wt='<div id="qmvi_loading_div" style="top:'+tpos+'px;left:'+lpos+'px;height:'+qh+'px;width:'+qw+'px;position:absolute;text-align:center;font-family:Arial;text-decoration:none;font-weight:normal;font-size:13px;color:#00224A;background-color:#ffffff;border-width:1px;border-color:#828EA2;border-style:solid;">';wt+='<div style="padding:20px;"><img src="../../hisglobal/images/loading.gif" width=16 height=16><br><br><font size="2" weight="bold">Fetching Details.Please Wait!</font></div>';wt+='</div>';document.getElementById("normalMsg").innerHTML=wt;}
	
	if (objXmlHttp.readyState==4 || objXmlHttp.readyState==200) {
		var res = objXmlHttp.responseText; 
		
		eval(gblResFunctionName + '(res' + "," + userMode + ")"); // evel is a keyword - it calls the function passed in it as argument.
		
		 document.getElementById("normalMsg").style.display="none";//hiding normal msg
		 try{
	     	autoTabIndexing();
	     }catch(_Err){
	     	//alert("Application Error[TabIndex not Included] Please Contact System Administrator");
	     }
		 try{
	     	eventElementObj.focus();
	     }catch(_Err){
	     	
	     }
	} 
}


// Changed on 30-Oct-2009 to select the age or dob
function ageSelection()
{
	if(document.getElementsByName("isActualDob")[0].checked)
	{
		//document.getElementsByName("patDOB")[0].value="";
		document.getElementById("divAge").style.display="";
		document.getElementById("divDob").style.display="none";
		document.getElementsByName("patDOB")[0].value=DateValidator.DATE_FORMAT;
	}
	else
	{
		document.getElementsByName("patAge")[0].value="";
		document.getElementsByName("patAgeUnit")[0].selectedIndex=0;
		document.getElementById("divAge").style.display="none";
		document.getElementById("divDob").style.display="block";		
	}
}


function ageModificationSelection() 
{
	//if(document.getElementById("divEmpDob")){
	//	 	if(document.getElementById("divEmpDob").style.display==""){
		 	// alert("function 1 ");
	//	 	  return;
	//	 	}
	 ///	}
	 if(document.getElementsByName("isActualDob")[0].checked){
	//document.getElementsByName("patDOB")[0].value="";
	 document.getElementById("divAge").style.display="";
	  document.getElementById("divDob").style.display="none";
	 // document.getElementsByName("patDOB")[0].value="";
	  }
	else {
	//  document.getElementsByName("patAge")[0].value="";
	  // document.getElementsByName("patAgeUnit")[0].selectedIndex=0;
	  document.getElementById("divAge").style.display="none";
	  document.getElementById("divDob").style.display="block";
	 } 
}

function showdepartmentdiv()
{
	document.getElementsByName('departmentdiv')[0].value="1";
}
  
function submitFormOnValidate(flag,mode)
{
 	if(flag)
	{
		submitForm(mode);
	}
	else
	{
		return false
	}	
}


function submitFormOnValidateCMO(flag,mode)
{
	if(flag)
		submitRegister(mode);
}

/*function submitForm(mode)
{     alert("submitting");
	 document.getElementsByName("hmode")[0].value=mode;
	 alert("submitform Hmode"+document.getElementsByName("hmode")[0].value);   
	 doHomeWork();  
	 document.forms[0].submit();
}
*/
function deleteRow(idx)
{
	document.getElementsByName("removeDept")[0].value = idx;
	submitForm("REMOVEDEPT");
}










////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

/////////////////////////////////////////////////////function for validating minimum length of a field///////////////////////////////////////////////////////////////////////////////////////////////////////////////


function validateMinLength(elem,minlen) {
	 var isValid = true;
     if(elem)
		value=elem.value;
     else
		value="";
				     
       if ((value.length<minlen))
				               {
				                isValid = false;
				              }
   return isValid;
 } 





////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

////////////////////////////////////////////validation for mandatory fields////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


function isValid(obj,name,minVal)

{
 //alert(obj.name);

  	if(obj!=null)
{
	
var val	=	new String(obj.value);
		var ageUnit=document.getElementsByName('patAgeUnit')[0].value
		if(document.getElementsByName('isActualDob')[0].checked)
		{
      //   alert("if.checked");		
		




      //  alert(val);

		if((val=="" || val>minVal) && ageUnit=="Y")

		{

			alert("Please Enter valid "+ name +"(<125)");

			obj.value=val;

			obj.focus();

			return false;

		}
		else
		{
			if(val=="0")
			{
				alert("Please Enter Valid"+name+"{>0}");
				obj.value=val;

				obj.focus();

				return false;
			}
		}
      }
     else
			obj.value=val;
		return true;
	}
	return false;

}

// Changed on 30-Oct-2009 
function isEmpty(obj,name)
{


  	if(obj!=null && obj!='undefined')
// alert("name "+obj.name)
	{
	var value;
		//alert(obj.name);
		if(obj.name=='isReferred')
 							{
 							//value=	new String(obj.value);
 							if(document.getElementsByName('isReferred')[0].checked)
 							{
 							
 							 if(document.getElementsByName('referringInstType')[0].checked)
 							 value=document.getElementsByName('patRefGnctdHospitalCode')[0].value;
 							 else if(document.getElementsByName('referringInstType')[1].checked)
 							 value=document.getElementsByName('patRefHospitalName')[0].value;
 							 else 
 							 value="";
 							}
 							else
 							value="~";

 							}
 		else if(obj.name=='patRefDoctor'){
 							if(document.getElementsByName('isReferred')[0].checked)
 							 value=document.getElementsByName('patRefDoctor')[0].value;
 							 else
 							 value="~";
 							
 							}
		else if(obj.name=='patIdMark')
						{
						if(document.getElementsByName('isUnknown')[0].checked)
						value=document.getElementsByName('patIdMark')[0].value;
						else
 						value="~";
						
						}
		else if(obj.name=='patIdMark1')
						{
						if(document.getElementsByName('isUnknown')[0].checked)
						value=document.getElementsByName('patIdMark1')[0].value;
						else
 						value="~";
						
						}
		else if(obj.name=='patIdMark2')
						{
						if(document.getElementsByName('isUnknown')[0].checked)
						value=document.getElementsByName('patIdMark2')[0].value;
						else
 						value="~";
						
						}								
 		else if(obj.name=='patDOB')
				 		{
				 		
				 		if(document.getElementsByName('isActualDob')[1].checked)
				 		if(obj.value==DateValidator.DATE_FORMAT)	value="";
				 		else	value=obj.value;
				 		else
				 		value="~";		 		
				 		}
		
		 else if(obj.name=='patAgeUnit')
		 				{
		 				if((document.getElementsByName('isActualDob')[0].checked)&& (document.getElementsByName('patAge').value!=""))
		 				{
		 				value=obj.value;
		 				}
		 				else
				 		value="~";	
		 				}	
		  else if(obj.name=='patAddCityLocCode')
		  				{
		  				
		  				if(document.getElementsByName('isUnknown')[0].checked==false)
		  				{
		  				value=document.getElementsByName('patAddCityLocCode')[0].value;}
		  				else
				 		value="~";	
		  				
		  				
		  				}
 		  else if(obj.name=='patAddCityLoc')
		     			{
		     			if( document.getElementsByName('isUnknown')[0].checked==false)
		  				value=document.getElementsByName('patAddCityLoc')[0].value;
		  				else
				 		value="~";	
		     			    			
		     			
		     			}
		  else if(obj.name=='patAddStateCode')
		     			{
//alert("state");
		     			if(document.getElementsByName('isAddressDelhi')[1].checked)
		  					value=document.getElementsByName('patAddStateCode')[0].value;
		  				else
				 		value="~";	
		     			    			
		     			
		     			}
		   else if(obj.name=='patNationalityCode')
						{
						
 						value=document.getElementsByName('patNationalityCode')[0].value;
						}
		  else if(obj.name=='patAddCountryCode')
		     			{
		     			if(document.getElementsByName('isAddressDelhi')[1].checked)
		  					value=document.getElementsByName('patAddCountryCode')[0].value;
		  				else
				 		value="~";	
		     			    			
		     			
		     			}

 		else
 							{
 							
 							
				value=obj.value;
							}
	
         //alert("value....................................................."+value);
		if(value=="" ||value=="-1")

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



function isSelected(combo,name)

{
//alert(combo);

	if(combo!=null && combo.selectedIndex==0)

	{

		alert("Please Select the "+ name);

		combo.focus();

		return false;

	}

	return true;

}





function submitForm(mode)
{
    
    // alert("submitform....");
     document.getElementsByName("hmode")[0].value=mode;
     doHomeWork();  
  //   alert("submit form action is"+document.forms[0].action);  
    // alert("hmode "+document.getElementsByName("hmode")[0].value)   
    
	 document.forms[0].submit();
	 
}
function disableForEmployee()
{
	document.getElementsByName("patFirstName")[0].disabled=true;
				  document.getElementsByName("patMiddleName")[0].disabled=true;
				  document.getElementsByName("patLastName")[0].disabled=true;
				  document.getElementsByName("isActualDob")[0].disabled=true;
				  document.getElementsByName("patAge")[0].disabled=true;
				  document.getElementsByName("patAgeUnit")[0].disabled=true;
				   document.getElementsByName("strPatAddressTehsil")[0].disabled=true;
				 // document.getElementsByName("patDOB")[0].value="";
				  document.getElementsByName("patDOB")[0].disabled=true;				  
				  document.getElementsByName("patGenderCode")[0].disabled=true;
				  document.getElementsByName("patGuardianName")[0].disabled=true;
				  document.getElementsByName("patHusbandName")[0].disabled=true;
				  document.getElementsByName("patMotherName")[0].disabled=true;
				  document.getElementsByName("patCasteCode")[0].disabled=true;
				  document.getElementsByName("patMaritalStatusCode")[0].disabled=true;
				  document.getElementsByName("patNationalityCode")[0].disabled=true;
				  document.getElementsByName("patReligionCode")[0].disabled=true;
				  document.getElementsByName("patIsUrban")[0].disabled=true;
				  //document.getElementsByName("patMonthlyIncome")[0].disabled=true;
				  document.getElementsByName("patAddCountryCode")[0].disabled=true;
				  document.getElementsByName("patAddStateCode")[0].disabled=true;
				  document.getElementsByName("patAddStateName")[0].disabled=true;
				//  document.getElementsByName("patAddCityLocCode")[0].disabled=true;
				 // document.getElementsByName("patAddCityLoc")[0].disabled=true;
				  document.getElementsByName("patAddHNo")[0].disabled=true;
				  document.getElementsByName("patAddStreet")[0].disabled=true;
				  document.getElementsByName("patAddDistrict")[0].disabled=true;
				  document.getElementsByName("patAddDistrictCode")[0].disabled=true;
				  document.getElementsByName("patAddCity")[0].disabled=true;
				  document.getElementsByName("patAddPIN")[0].disabled=true;
				  //document.getElementsByName("patAddContactNo")[0].disabled=true;
				  // document.getElementById("divpatAddCityLocCode").style.display="none"; 
		 		 //document.getElementById("divpatAddCityLocation").style.display=""; 
				  //document.getElementsByName("patAddCountryCode")[0].disabled=true;

}

function enableForNonEmployee()
{
				  document.getElementsByName("patFirstName")[0].disabled=false;
				  document.getElementsByName("patMiddleName")[0].disabled=false;
				  document.getElementsByName("patLastName")[0].disabled=false;
				  document.getElementsByName("isActualDob")[0].disabled=false;
				  document.getElementsByName("patAge")[0].disabled=false;
				  document.getElementsByName("patAgeUnit")[0].disabled=false;
				 document.getElementsByName("strPatAddressTehsil")[0].disabled=false;
				  document.getElementsByName("patDOB")[0].disabled=false;
				  document.getElementsByName("patGenderCode")[0].disabled=false;
				  document.getElementsByName("patGuardianName")[0].disabled=false;
				  document.getElementsByName("patHusbandName")[0].disabled=false;
				  document.getElementsByName("patMotherName")[0].disabled=false;
				  document.getElementsByName("patMaritalStatusCode")[0].disabled=false;
				  document.getElementsByName("patNationalityCode")[0].disabled=false;
				  document.getElementsByName("patReligionCode")[0].disabled=false;
				  document.getElementsByName("patIsUrban")[0].disabled=false;
				  //document.getElementsByName("patMonthlyIncome")[0].disabled=false;
				   document.getElementsByName("patAddCountryCode")[0].disabled=false;
				  document.getElementsByName("patAddStateCode")[0].disabled=false;
				  document.getElementsByName("patAddStateName")[0].disabled=false;
				  document.getElementsByName("patAddHNo")[0].disabled=false;
				  document.getElementsByName("patAddStreet")[0].disabled=false;
				  document.getElementsByName("patAddDistrict")[0].disabled=false;
				  document.getElementsByName("patAddDistrictCode")[0].disabled=false;
				  document.getElementsByName("patAddCity")[0].disabled=false;
				  document.getElementsByName("patAddPIN")[0].disabled=false;
				  document.getElementsByName("patCasteCode")[0].disabled=false;
				  //document.getElementsByName("patAddContactNo")[0].disabled=false;
					
					
					//document.getElementsByName("patFirstName")[0].value="";
				  //document.getElementsByName("patMiddleName")[0].value="";
				 // document.getElementsByName("patLastName")[0].value="";
				 // document.getElementsByName("isActualDob")[0].value="0";
				 // document.getElementsByName("patAge")[0].value="";
				 // document.getElementsByName("patAgeUnit")[0].value="Y";
				 // document.getElementsByName("patDOB")[0].value=DateValidator.DATE_FORMAT;
				 // document.getElementsByName("patGenderCode")[0].value="-1";
				//  document.getElementsByName("patGuardianName")[0].value="";
				 // document.getElementsByName("patHusbandName")[0].value="";
				 // document.getElementsByName("patMotherName")[0].value="";
				 // document.getElementsByName("patMaritalStatusCode")[0].value="-1";
				 // document.getElementsByName("patNationalityCode")[0].value="";
				 // document.getElementsByName("patReligionCode")[0].value="-1";
				//  document.getElementsByName("patIsUrban")[0].value="";
				//  document.getElementsByName("patMonthlyIncome")[0].value="";
				//   document.getElementsByName("patAddCountryCode")[0].value="";
				//  document.getElementsByName("patAddStateCode")[0].value="";
				//  document.getElementsByName("patAddStateName")[0].value="";
				//  document.getElementsByName('patAddCityLoc')[0].value="";
				//  document.getElementsByName('patAddCityLocCode')[0].value="-1";
				 // document.getElementsByName("patAddHNo")[0].value="";
				 // document.getElementsByName("patAddStreet")[0].value="";
				 // document.getElementsByName("patAddDistrict")[0].value="";
				//  document.getElementsByName("patAddDistrictCode")[0].value="-1";
				//  document.getElementsByName("patAddCity")[0].value="";
				 // document.getElementsByName("patAddPIN")[0].value="";
				//  document.getElementsByName("patAddContactNo")[0].value="";
				//document.getElementsByName("patIdNo")[0].value="";
				//document.getElementsByName("patDataFromEmployeeTable")[0].value="";
				  	  //document.getElementsByName("patNickName")[0].disabled=false;
					  //document.getElementsByName("patOccupation")[0].disabled=false;
					  //document.getElementsByName("patFatherOccupation")[0].disabled=false;
					  //document.getElementsByName("patHusbandOccupation")[0].disabled=false;
					  document.getElementsByName("patCardNo")[0].disabled=false;
					  
					//   document.getElementsByName("patNickName")[0].value="";
					//  document.getElementsByName("patOccupation")[0].value="";
					//  document.getElementsByName("patFatherOccupation")[0].value="";
					//  document.getElementsByName("patHusbandOccupation")[0].value="";
					//  document.getElementsByName("patCardNo")[0].value="";
}




function comboValidation(obj, str)
{	
var valid= true
	if(obj.value==-1)
	{
		alert("Please Select the "+str);
		valid=false;
		obj.focus();
	}
	return valid
}

 function ValidateDepartment(field) {
         var isValid = true;
        

      value2=document.getElementsByName("departmentCode")[0].value; 
    
      if(field)
      value=field.value;
      else
      value="";
      
         if ((value.length == 0)||(value=='-1'))
               { 
               if(value2=='-1'){
               	alert("Please Select the Department");
                isValid = false;
                document.getElementsByName("departmentCode")[0].focus();
              }
              }
              
  return isValid;
        

       } 
function ValidateDepartmentUnit(field) {
         var isValid = true;
        
//alert('inside ValidateDepartment of registration.js....');
      value2=document.getElementsByName("departmentUnitCode")[0].value; 
    //  alert(value2);
      if(field)
      value=field.value;
      else
      value="";
      
         if ((value.length == 0)||(value=='-1'))
               { 
               if(value2=='-1'){
               	alert("Please Select the Unit");
                isValid = false;
                document.getElementsByName("departmentUnitCode")[0].focus();
              }
              }
 // alert(isValid);               
  return isValid;
        

       } 


function ValidateReferToDepartment(field){

var isValid = true;
        

      value2=document.getElementsByName("refToDepartmentCode")[0].value; 
    //  alert(value2);
      if(field)
      value=field.value;
      else
      value="";
      
         if ((value.length == 0)||(value=='-1'))
               { 
               if(value2=='-1'){
               	alert("Select Referred to Department");
                isValid = false;
                document.getElementsByName("refToDepartmentCode")[0].focus();
              }
              }
 // alert(isValid);               
  return isValid;

}





	

function checkValueOfCombo(elem,field,mode)

{
if(elem.value=="-1")
alert("Select a "+field);
else
{

 submitForm(mode);
 
 }
 }
	


function showdivEmployee()
{
//alert("show emp");
document.getElementById("divCmoName").style.display="none";
document.getElementById("divLabelcmoName").style.display="";
//document.getElementById("divemployeeLabelcmoCode").style.display="";
document.getElementById("divemployeeCmoCode").style.display="";
document.getElementsByName("doctorName")[0].value="";
document.getElementsByName("cmoCode")[0].value="";
}


function showdivnonemployee(){
document.getElementById("divCmoName").style.display="";
document.getElementById("divLabelcmoName").style.display="";
//document.getElementById("divemployeeLabelcmoCode").style.display="none";
document.getElementById("divemployeeCmoCode").style.display="none";
document.getElementsByName("doctorName")[0].value="";
document.getElementsByName("cmoCode")[0].value="";

}

///////////////////////////////function to validate prevCRNO......................................

function validatePrevCRNo(){
var valid=true;
if(validateMinLength(document.getElementsByName('prevCrNo')[0],13))
valid=true;
else{
valid=false;  
alert("InValid Previous CR Number");
}
return valid;

}

////////////////////////function to check the minimum length allowed for any text box////////

function validateMinimumLength(obj,name,minimumSize)
{
	
	var valid=true
	var len=obj.value.length
	if(parseInt(len)>0 && parseInt(len)<parseInt(minimumSize))
	{
		alert(name +' cannot be less than '+ minimumSize +' digits')
		valid=false
		obj.focus();
		
	}
	return valid
}


/////////////////////////function to check if the pin number starts with zero/////////


function validatePinNumber(obj)
{	

	var valid=true
	var len=obj.value.length
	var val=obj.value
		
	if (parseInt(len)>0 )
	{
		
		if((val.charAt(0))==0)
		{
		alert("Pin Number Cannot Start with Zero")
		valid=false
		obj.focus()
		}
		else
		{
			valid=true
		}
	}
	else
	{
		valid=true
	}
	
	return valid
	
}

/////////////////function to check if the text starts with dot/////////////////////////

function validateStartingWithDot(obj,name)
{
	var valid=true
	var len=obj.value.length
	var val=obj.value
	
	if(parseInt(len)>0)
	{
		if((val.charAt(0))=='.')
		{
			alert(name+" cannot start with special character dot")
			valid=false
			return valid
		}
		
	}
	
	return valid
}


////////////////////////function to check for two consecutive dots////////////////

function validateTwoConsecutiveDots(obj,name)
{
	var valid=true
	var len=obj.value.length
	var val=obj.value
	
	if(parseInt(len)>0)
	{
		for(i=0;i<len;i++)
		{
			if((val.charAt(i))=='.')
			{
				if((val.charAt(i))==(val.charAt(i+1)))
				{
				alert(name+" cannot have two consecutive special character dot")
				valid=false
				
				return valid
				}
			}
		}
	}
	
	return valid	
}


//////////////////////function to check if the text ends with dot/////////////////////

function validateEndingWithDot(obj,name)
{
	var valid=true
	var len=obj.value.length
	var val=obj.value
	
	if(parseInt(len)>0)
	{
		if((val.charAt(len-1))=='.')
		{
			alert(name+" cannot end with special character dot")
			valid=false
			return valid
		}
		
	}
	
	return valid
} 


//////////////////////////validate text area with dots//////////////////

function validateDot(obj,name)
{
	var valid=false
	
	if(validateStartingWithDot(obj,name)
	&& validateTwoConsecutiveDots(obj,name)
	&& validateEndingWithDot(obj,name))
	{
		
		valid=true
	}

	return valid
}


/////////////////function to check if the text starts with special character/////////////////////////

function validateStartingWithSpecialCharacter(obj,name)
{
	var valid=true
	var len=obj.value.length
	var val=obj.value
	
	if(parseInt(len)>0)
	{
		if((val.charAt(0))=='.' ||  (val.charAt(0))=='/' || (val.charAt(0))=='-')
		{
			alert(name+" cannot start with special character ")
			valid=false
			return valid
		}
		
	}
	
	return valid
}


////////////////////////function to check for two consecutive special character////////////////

function validateTwoConsecutiveSpecialCharacter(obj,name)
{
	var valid=true
	var len=obj.value.length
	var val=obj.value
	
	if(parseInt(len)>0)
	{
		for(i=0;i<len;i++)
		{
			if((val.charAt(i))=='.' || (val.charAt(i))=='/' || (val.charAt(i))=='-')
			{
				if((val.charAt(i))==(val.charAt(i+1)))
				{
				alert(name+" cannot have two consecutive special character ")
				valid=false
				
				return valid
				}
			}
		}
	}
	
	return valid	
}


//////////////////////function to check if the text ends with special character/////////////////////

function validateEndingWithSpecialCharacter(obj,name)
{
	var valid=true
	var len=obj.value.length
	var val=obj.value
	
	if(parseInt(len)>0)
	{
		if((val.charAt(len-1))=='.' || (val.charAt(len-1))=='/' || (val.charAt(len-1))=='-')
		{
			alert(name+" cannot end with special character ")
			valid=false
			return valid
		}
		
	}
	
	return valid
} 


//////////////////////////validate text area with special character//////////////////

function validateSpecialCharacter(obj,name)
{
	var valid=false
	//alert("entry")
	if(validateStartingWithSpecialCharacter(obj,name)
	&& validateTwoConsecutiveSpecialCharacter(obj,name)
	&& validateEndingWithSpecialCharacter(obj,name))
	{
		
		valid=true
	}

	return valid
}

/////////////////////////////////////functions for display country state location ////////////////////
var empCode="";
function varifyEmployeeCode()
{
	// empCode
}

/////////////////////////////////function to verify the senior Citizen category////////////////////////
// Changed on 30-Oct-2009
function validateForSeniorCitizen(seniorCitizenAge)
{
	valid=true;
	if(document.getElementsByName("isActualDob")[0].checked)
	{
		var age=document.getElementsByName("patAge")[0].value;
		var ageUnit=document.getElementsByName("patAgeUnit")[0].value;
		if(parseInt(age)<parseInt(seniorCitizenAge) || (ageUnit!="Y" && ageUnit!="Yr"))
		{
			alert("Age cannot be less than "+seniorCitizenAge+" Years for Senior Citizen");
			valid=false;
		}
		else
		{
			valid=true;
		}
	}
	else
	{
		/*var dob=document.getElementsByName("patDOB")[0].value;
		var sysdate=document.getElementsByName("sysDate")[0];
		var value= new Array(3);
		value=dob.split('-');
		//alert(value);
		//var datStr=value[1]+','+value[0]+','+value[2];
		//var dobYear=parseInt(value[2])+parseInt(seniorCitizenAge);
		//alert("inside validateForSeniorCitizen")
		var dateToCheckLess=value[0]+'-'+value[1]+'-'+(parseInt(value[2])+parseInt(seniorCitizenAge));
		//document.getElementsByName("donorDob")[0].value=dateToCheckLess;
		document.getElementsByName("patDOB")[0].value=dateToCheckLess;
		if(compareDateCallForCheckLess(document.getElementsByName("patDOB")[0],sysdate,2,'Age','',parseInt(seniorCitizenAge)))
		{
			valid=true;
			document.getElementsByName("patDOB")[0].value=dob;
		}
		else
		{
			valid=false;
			document.getElementsByName("patDOB")[0].value=dob;
		}*/
    	
    	var dateOfBirth = convertStrToDate(document.getElementsByName("patDOB")[0].value,"dd-MM-yyyy");
    	var currentDate = convertStrToDate(document.getElementsByName("sysDate")[0].value,"dd-Mon-yyyy");
    	// utilityFunctions.js :: function dateDifference(_bigDt,_smallDt,_format{"Y","M","D","H","MI","S"})
		var ageInYears = dateDifference(currentDate,dateOfBirth,"Y");
		//alert("Age : " + ageInYears +" Years");
		if(parseInt(ageInYears)<seniorCitizenAge)
		{
			alert("Age ("+ageInYears+" Years) cannot be less than "+seniorCitizenAge+" Years for Senior Citizen");
			valid=false;
		}
		else
			valid=true;
	}
	return valid;
}

///////////////////

function compareDateCallForCheckLess(d1,d2,mode,l1,l2,age){
//alert("compare called    "+l1 +"      " +l2);
var valid=true;
if(isEmpty(d1,l1) && isEmpty(d2,l2)){
 if(compareDate(d1,d2, mode)){
		valid = true;
	}
 else {
	alert(l1+" Cannot Be Less Than "+age+" Years for Senior Citizen");
	valid = false;
	}
}

else
valid=false;

return valid;
}

//checking whether the monthly of the poor category patient is not more than the specified value
function validateMonthlyIncome(patMonthlyIncome,poorMonthlyIncome,poorPatCategory){
	
	var poorMonthlyIncome=parseInt(poorMonthlyIncome)
	var patCategory=document.getElementsByName("patPrimaryCatCode")[0].value
	if(patMonthlyIncome.value!=""){
		if(patCategory == poorPatCategory
			&& parseFloat(patMonthlyIncome.value) > poorMonthlyIncome){
			alert("Monthly Income Should be less than " + poorMonthlyIncome +" for BPL patient");
			return false;
		}
	}
	return true;
}

//check for age if dept is age bound
function validateAgeForDeptReg(patAgeForDeptReg,verifyWithDept,deptUnitCode){

	valid=true;
	var deptName="";
	var deptUnit=null;
	deptUnit=deptUnitCode
	if(deptUnit==null)
	{
		var deptCode=document.getElementsByName("departmentCode")[0].value
		var deptCombo=document.getElementsByName("departmentCode")[0];
		for(var i=0;i<deptCombo.length;i++){
			if(deptCombo.options[i].selected){
				deptName=deptCombo.options[i].text;
			}
		}
	}
	else{
		var deptUnitCombo=deptUnitCode;
		for(var i=0;i<deptUnitCombo.length;i++){
			if(deptUnitCombo.options[i].selected){
				deptName=deptUnitCombo.options[i].text;
			}
		}
	}	
	if(document.getElementsByName("departmentsToVisitStamp"))
	{
		for(var i=0;i<document.getElementsByName("departmentsToVisitStamp").length;i++){
			if(verifyWithDeptCode==document.getElementsByName("departmentsToVisitStamp")[i].value){
				deptCode=document.getElementsByName("departmentsToVisitStamp")[i].value;
				deptName=document.getElementsByName("departmentNameToVisit")[i].value	
			}	
		}
	}
	
	var verifyWithDeptCode=verifyWithDept.split(",")
	//alert(verifyWithDeptCode)
	for(var i=0;i<verifyWithDept.length;i++){
		if(deptCode==verifyWithDeptCode[i] || (!deptUnitCode?false:(deptUnitCode.value.substring(0,3)==verifyWithDeptCode[i]))){
		
			var age=document.getElementsByName("patAge")[0].value;
			var ageUnit=document.getElementsByName("patAgeUnit")[0].value;
			if(document.getElementsByName("isActualDob")[0].checked)
			{
				if(parseInt(age) > parseInt(patAgeForDeptReg) && ageUnit=="Y"){
					alert("Age Should be less than " + patAgeForDeptReg +" Years to register in " + deptName);
					return false;
				}	
				//alert(parseInt(age)/12)
				if(ageUnit=="M" && parseInt(age)/12 > parseInt(patAgeForDeptReg)){
					alert("Age Should be less than " + patAgeForDeptReg +" Years to register in " + deptName);
					return false;
				}
				if(ageUnit=="W" && parseInt(age)/52 > parseInt(patAgeForDeptReg)){
					alert("Age Should be less than " + patAgeForDeptReg +" Years to register in " + deptName);
					return false;
				}
			}
			else{
				var dateOfBirth = convertStrToDate(document.getElementsByName("patDOB")[0].value,"dd-MM-yyyy");
		    	var currentDate = convertStrToDate(document.getElementsByName("sysDate")[0].value,"dd-Mon-yyyy");
		    	var ageInYears = dateDifference(currentDate,dateOfBirth,"Y");
				if(ageInYears > patAgeForDeptReg){
					alert("Age Should be less than " + patAgeForDeptReg +" Years to register in " + deptName);
					return false;
				}	
			}
			
		}	
	
	}
	return true;
}


function validateAgeForDeptToVisit(patAgeForDeptReg,verifyWithDept,deptUnitCode){
	valid=true;
	var deptName="";
	var deptUnit=null;
	deptUnit=deptUnitCode
	
	if(deptUnit==null){
		var deptCode=document.getElementsByName("departmentCode")[0].value
		var deptCombo=document.getElementsByName("departmentCode")[0];
		for(var i=0;i<deptCombo.length;i++){
			if(deptCombo.options[i].selected){
				deptName=deptCombo.options[i].text;
			}
		}
	}
	else{
		var deptUnitCombo=deptUnitCode;
		for(var i=0;i<deptUnitCombo.length;i++){
			if(deptUnitCombo.options[i].selected){
				deptName=deptUnitCombo.options[i].text;
			}
		}
	}	
	
	var patAge=document.getElementsByName("patAge")[0].value
	var verifyWithDeptCode=verifyWithDept.split(",")
	//alert(verifyWithDeptCode)
	for(var i=0;i<verifyWithDept.length;i++){
		if(deptCode==verifyWithDeptCode[i] || (!deptUnitCode?false:(deptUnitCode.value.substring(0,3)==verifyWithDeptCode[i]))){
			if(parseInt(patAge) > parseInt(patAgeForDeptReg)){
				alert("Cannnot make visit stamp in  "  + deptName+ ". Age is greater than "+ patAgeForDeptReg +" Years");
				return false;
			}		
		}
	}
	return true;

}

function validateAgeForSpecialClinicToVisit(patAgeForDeptReg,verifyWithDept,deptUnitCode){
	valid=true;
	var deptName="";
	var deptUnit=null;
	deptUnit=deptUnitCode
	
	/*if(deptUnit==null){
		var deptCode=document.getElementsByName("departmentCode")[0].value
		var deptCombo=document.getElementsByName("departmentCode")[0];
		for(var i=0;i<deptCombo.length;i++){
			if(deptCombo.options[i].selected){
				deptName=deptCombo.options[i].text;
			}
		}
	}*/
	//else{
		var deptUnitCombo=deptUnitCode;
		for(var i=0;i<deptUnitCombo.length;i++){
			if(deptUnitCombo.options[i].selected){
				deptName=deptUnitCombo.options[i].text;
			}
		}
	//}
		
	
	var patAge=document.getElementsByName("patAge")[0].value
	var verifyWithDeptCode=verifyWithDept.split(",")
	//alert(verifyWithDeptCode)
	for(var i=0;i<verifyWithDept.length;i++){
		if(deptUnitCode.value.substring(0,3)==verifyWithDeptCode[i]){
			if(parseInt(patAge) > parseInt(patAgeForDeptReg)){
				alert("Cannnot make visit stamp in  "  + deptName+ ". Age is greater than "+ patAgeForDeptReg +" Years");
				return false;
			}		
		}
	}
	return true;

}

function validateFatherNameSpouseName(){
	var fatherName=document.getElementsByName("patGuardianName")[0].value
	var spouseName=document.getElementsByName("patHusbandName")[0].value
	if(fatherName=="" && spouseName==""){
		alert("Please enter either Father Name or Spouse Name");
		document.getElementsByName("patGuardianName")[0].focus();
		return false;
	}
	return true;
}

function isChecked(obj,name){
	if(!obj.checked){
		alert("Please Select "+ name);
		return false;
	}
	return true;

}

//for arogya online

function validateForPoorPatient(patientCatCode,poorCatCode){
	var valid=true;
	if(patientCatCode.value==poorCatCode){
		if( isEmpty(document.forms[0].patNickName,"Family Head Name")
		&& comboValidation(document.getElementsByName("patHusbandOccupation")[0],"Relation")
		&& isEmpty(document.getElementsByName("patCardNo")[0],"MMJRK No")){
			valid= true;
		}	
		else
			valid=false;
		
	}
	
	return valid;
}

function showPoorPatDiv(poorCatCode){
	if(document.getElementsByName("patIdNo")[0].value!=""){
		document.getElementById("patientCatDiv").style.display="block"
		document.getElementById("patientCatTextDiv").style.display="block"
	}
	else{
		document.getElementById("patientCatDiv").style.display="none"
		document.getElementById("patientCatTextDiv").style.display="none"
			
	
	}
	if(document.getElementsByName("patPrimaryCatCode")[0].value==poorCatCode){
		//document.getElementById("poorPatTR").style.display="block"
		document.getElementById("mmjrkDiv").style.display="block"
		document.getElementById("mmjrkTextDiv").style.display="block"
		document.getElementById("familyHeadDiv").style.display="block"
		document.getElementById("familyHeadTextDiv").style.display="block"
		document.getElementById("relationDiv").style.display="block"
		document.getElementById("relationComboDiv").style.display="block"
	}
	else{
		document.getElementById("poorPatTR").style.display="none"
		document.getElementById("mmjrkDiv").style.display="none"
		document.getElementById("mmjrkTextDiv").style.display="none"
		document.getElementById("familyHeadDiv").style.display="none"
		document.getElementById("familyHeadTextDiv").style.display="none"
		document.getElementById("relationDiv").style.display="none"
		document.getElementById("relationComboDiv").style.display="none"
	}

}


//////////////////////common validation function//////////////////////////////////////////////
 
function validateObjects(obj,label)
{		//alert(obj.name + "   " +obj.value);
if(obj!=null)

 {
		var value;
		//alert("not null");
	//	alert("object name"+obj.name); 
	 	 if(obj.name=='referringInstType')
	 	 						{
	 	 						if(document.getElementsByName('referringInstType')[0].checked)
			  					value=document.getElementsByName('patRefGnctdHospitalCode')[0].value;
			  					else if(document.getElementsByName('referringInstType')[1].checked)
			  					value=document.getElementsByName('patRefHospitalName')[0].value;
			  					else
			  					value="";
		 						}
	 	 else if(obj.name=='patRelativeName' || obj.name=='relativeCode' || obj.name=='patRelativeAddress' )
	 	 						{
	 	 						if(document.getElementsByName('bodyHandOverTo')[0].checked)
			  					value=obj.value;
			  					else 
	 	 						value='~';
	 	 						}
	 	
	 	 else if(obj.name=='bodyHandOverTo')
	 	 						{
	 	 						if(document.getElementsByName('bodyHandOverTo')[1].checked)
			  					value=document.getElementsByName('bodyHandOverTo')[0].value;
		 						else
		 						value="~"; 	 
	 	 						}
	 	 else if(obj.name=='cmoCode')
	 	 						{
	 	 						if(document.getElementsByName('cmoType')[0].checked)
			  					value=document.getElementsByName('cmoCode')[0].value;
		 						else
		 						value="~"; 	 
	 	 						}		
	 				
		  else if(obj.name=='patAddCityLocCode')
		  				{
		  				
		  				if(document.getElementsByName('isAddressDelhi')[0].checked)
		  				{
		  				value=document.getElementsByName('patAddCityLocCode')[0].value;}
		  				else
				 		value="~";	
		  				
		  				
		  				}
 		  else if(obj.name=='patAddCityLoc')
		     			{
		     			if(document.getElementsByName('isAddressDelhi')[1].checked)
		  				value=document.getElementsByName('patAddCityLoc')[0].value;
		  				else
				 		value="~";	
		     			}
		 else if(obj.name=='doctorName')
	 	 						{
	 	 						if(document.getElementsByName('cmoType')[1].checked)
			  					value=document.getElementsByName('doctorName')[0].value;
		 						else
		 						value="~"; 	 
	 	 						}		
		   
	 							
	 	 else
	  	 value=obj.value;
								
		 
	     
			if(value=="")
	
			{
	         //   alert("df");
				alert("Please Enter the "+ label);
	
				obj.value=value;
	
				obj.focus();
	
				return false;
	
			}
			else if(value=="-1")
			{
	
				alert("Please Select the "+ label);
	
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

////////////////////validate null objects/////////////////////////
function validateNullObjects(field,label)
{
 var isValid = true;
   //alert("value"+field.value);
   //alert("validateNullObjects:  "+field);
    if(field==null || field=="undefined")
              { 
               	//alert(label+"is required");
                isValid = false;
              }
            
     return isValid;
        

} 

/////////////////////validate CR NO............................
function validateCRNo()
{
var valid=true;
if(validateMinLength(document.getElementsByName('crNo1')[0],3)&& 
 // validateMinLength(document.getElementsByName('crNo2')[0],2)&& 
 // validateMinLength(document.getElementsByName('crNo3')[0],8)&&
 validateMinLength(document.getElementsByName('crNo3')[0],10)&& 
  checkSum())
	valid=true;
else{
	valid=false;  
	//alert("Invalid CR Number");
}
	return valid;

}

///////////////////////////////////Validate cr no with size as argument


function validateCRNoCHECK(size)
{
	//alert("kak");
	var valid=false;
	if(validateMinLength(document.getElementsByName('patCrNo')[0],size) &&
		validateCheckSumBySize(size))
	{
		valid=true
	//	alert("valid"+valid)
	}
	else
	{	
	alert("InValid CR No");
	if(document.getElementsByName("patCrNo")[0]){
		document.getElementsByName("patCrNo")[0].focus()
	}	
		valid=false
	}
// 	alert("valid 123456 "+valid)
	return valid
}
///////////////////////////////////////////

function validateCheckSumBySize(size)
{
	var valid=false
// 	alert("validateCheckSumBySize")
	if(size==12)
	{
		valid=checkSumValidation()
	}
	if(size==13)
	{
		valid=checkSum()
	}
	if(size==15)
	{
		valid=checkSum15()
	}
	return valid
}

//////////////////////////////////////////////////////

function toggleBox(szDivID, iState) // 1 visible, 0 hidden
{
    if(document.layers)	   //NN4+
    {
       document.layers[szDivID].visibility = iState ? "show" : "hide";
    }
    else if(document.getElementById)	  //gecko(NN6) + IE 5+
    {
        var obj = document.getElementById(szDivID);
        obj.style.display = iState ? "inline" : "none";
    }
    else if(document.all)	// IE 4
    {
        document.all[szDivID].style.display = iState ? "inline" : "none";
    }
}

//////////////////////////////////////////////////////////////

function validateTextArea(event,obj,maxLength){
//alert("In maxlength");
var valid=true;

if(CheckMaxLength(event,obj,maxLength))
valid=true;
else
valid=false;  

return valid;

}

function validateTextAreaFor(constraint, event,obj,maxLength){
//constraint='A' or 'a' X x 9 ^
var valid=true;

if(CheckMaxLength(event,obj,maxLength)&& validateAlphaNumericOnly(event))
valid=true;
else
valid=false;  

return valid;

}

//////////////////////////////////////////////////////////////////////////////


function compareDateCall(d1,d2,mode,l1,l2){
// alert("compare called    "+l1 +"      " +l2);
var valid=true;
if(isEmpty(d1,l1) && isEmpty(d2,l2)){
//alert("inside first if of copmparedate "+compareDate(d1,d2, mode));
 // alert("return compareDate(d1,d2, mode)"+compareDate(d1,d2, mode))
 if(compareDate(d1,d2, mode)){
  //  alert("valid Date");
		valid = true;
	}
 else {
	 alert(l1+" can not be greater than "+l2);
	valid = false;
	}
} 

else
valid=false;
//alert("valid    "+valid);
return valid;
}

/////////////////////////////////////  Menu Tab Hide Show

	function changeFrameSize()
	{	
	//	alert("in initpage");
		
			if(parent.document.getElementById("fs2").cols == "0,*")
			{
				parent.document.getElementById("fs2").cols = "230,*";
				parent.document.getElementById("f7").contentDocument.getElementById("Image").src="../hisglobal/images/arrsingle-left.png";
			
			}
			else
			{
				
				parent.document.getElementById("f7").contentDocument.getElementById("Image").src="../hisglobal/images/arrsingle-left.png";
			}
			  
	}	
	
function hideMenuFrame()
{	
//alert("parent.document.getElementById('fs2').cols="+parent.document.getElementById("fs2").cols)
	if(window.XMLHttpRequest) // Mozilla
	{
		if(parent.document.getElementById("fs2").cols == "230,*")
		{
			parent.document.getElementById("fs2").cols = "0,*";
			parent.document.getElementById("f7").contentDocument.getElementById("Image").src="../hisglobal/images/arrsingle-right.png";
		}
	}
	//if (window.ActiveXObject)
	else
	{
		if(parent.document.getElementById("fs2").cols == "230,*")
		{
			parent.document.getElementById("fs2").cols = "0,*";
			parent.document.getElementById("f7").Document.getElementById("Image").src="../hisglobal/images/arrsingle-right.png";
		}
	}	
}

function showMenuFrame()
{	
//alert("showMenuFrame in reg");
	if(window.XMLHttpRequest) // Mozilla
	{
		parent.document.getElementById("fs2").cols = "230,*";
		parent.document.getElementById("f7").contentDocument.getElementById("Image").src="../hisglobal/images/arrsingle-left.png";
	}
	//if (window.ActiveXObject)
	else
	{
		parent.document.getElementById("fs2").cols = "230,*";
		parent.document.getElementById("f7").Document.getElementById("Image").src="../hisglobal/images/arrsingle-left.png";
	}	
}
	

	
	
	
	
	
	
	
	
	
	///........................................to validate MLC .......................................


function checkCmoType(){
		var valid=false;
		//alert("ElementsByName(cmoType)"+document.getElementsByName("cmoType")[0].value);
		if(document.getElementsByName("cmoType")[0].checked){
		//alert("validate doctor code");
		valid=validateObjects(document.forms[0].cmoCode,"CMO Name")
		}
		else{
	//	alert("validate doctor name");
		valid=validateObjects(document.forms[0].doctorName,"CMO Name")
		}
		return valid;
}

////////////////////////////////////////////////////validate Date.........................................

function compareDate(frDate,toDate,mode)
{
	var frValue, toValue,frYear, frMon, frDay,sts = 0;

	//alert("compare date " + frDate);
	//alert("compare date " + toDate);
	if (frDate == null || frDate.value == "")
	{
		//alert("if,,,,,,, 1");
		frValue = new Date;
		frYear = frValue.getYear();
		frMon = frValue.getMonth();
		frDay = frValue.getDate();
	}
	else
	{
		//alert("else1");
		if (isDate(frDate,mode) == true)
		{
	//	alert("if 22222222");
			frYear = intYear;
			frMon = intMon;
			frDay = intDay;

			if (isDate(toDate,mode) == true)
			{
		//	alert("if in else");
				if (frYear > intYear)
					sts = 1;
				else
				{
					if (frYear == intYear)
					{
						if (frMon > intMon)
							sts = 1;
						else
						{
							if (frMon == intMon)
							{
								if (frDay > intDay)
									sts = 1;
							}
						}
					}
				}
			}
			else
			{
			//alert("false");
				//toDate.focus();
				return false;
			}
		}
		else
		{
		//	alert("false");
			//frDate.focus();
			return false;
		}
	}

	if (sts == 1)
	{
		if (frDate.value == "" || frDate == null)	{
			//validating current date with toDate
		//	alert("if................................blank");
			//getMsg(5,toDate.name);
			}
		else
			//alert(frDate.name + " is greater than " + toDate.name);

		//frDate.focus();
		return false;
	}

	return true;

}

/////////////////////////////////////////////////////modify unit detail....................................
function validateUnitType()
{
		if(document.getElementsByName('isGeneral')[0].value=="-1")
			alert("Select a Unit");
		else
		{
			submitTile('ADDROOM'); 
 		}
}

//...............................to validate Unit Transfer........................................
function validateUnitTransfer(){
var valid=true;

if(validateDepartments() && validateObjects(document.forms[0].toDepartmentUnitCode,"Unit"))
valid=true;
else
valid=false;  

return valid;

}



//.............................to validate emergency old patient visit......................................



function ValidateEmergVisitStamp(){
var valid=true;

	 if(isEmpty(document.forms[0].patRegCatCode,"Emergency Type"))
	 //&& isEmpty(document.forms[0].isReferred,"Referring institution") &&  isEmpty(document.forms[0].patRefDoctor,"Referred By Doctor"))
	 	 
	 valid=true;
	 
	 else
     
     valid=false;  
     
return valid;

}

///////////////////////////////////validate old patient visit...............................................
function validateOldPatientVisit()
{

var valid=true;

if(validateOldDepartment())
valid=true;
else
valid=false;  

return valid;

}

///////////////////////////////////////validate New Department Visit//////////////////////

function validateNewDeptVisit()
{
var valid=true;
if	(isEmpty(document.forms[0].patPrimaryCatCode,"Primary Category")&&
	 isEmpty(document.forms[0].patSecondaryCatCode,"Secondary Category"))

	valid=true;
else

	valid=false;

return valid;
}

///////////////////////////////////validate change  patient category.............................
function validateChangePatCat()
{
var valid=true;
if(validateObjects(document.forms[0].patPrimaryCatCode,"Primary Category"))
valid=true;
else
valid=false;  

return valid;


}
//////////////////validate duplicate card........................
function validateDuplicateCard(){
var valid=true;

if(validateDepartments() )
{
alert("validate department return true")
valid=true;
}
else
{
valid=false;
}  
alert("validateDuplicateCard"+valid)
return valid;


}



//////////////////validate   AddressModification().................

function validateAddressModification(){
//alert("inside adress validation function");
var valid=true;

if(validateVerificationDocuments())
valid=true;
else
valid=false;  

//alert(valid);
return valid;

//////////////////////////////////////////////////////Modify Room To Unit..................................

function checkval()
{
element=document.getElementsByName("capacity");
	for(i=0;i<element.length;i++)
		{
			if(element[i].value.length==0)
				{				
				alert("Please enter the capacity");
				element[i].focus();
				return false;
				}
				else{
				//  alert("True");				
				}
		}
}		



}
////////////////////////////////////////emergency............................................................

function ValidateEmgReg(){

 if(isEmpty(document.forms[0].patFirstName,"First Name") 
 &&   isValid(document.forms[0].patAge,"Age",125)
 &&   isEmpty(document.forms[0].patAgeUnit,"Age Unit")  
 &&   isEmpty(document.forms[0].patDOB,"Date of Birth") 
 &&   validateDateAgainstSysDate(document.forms[0].patDOB,"Date of Birth")
 &&   isEmpty(document.forms[0].isReferred,"Referring institution")
 &&   isSelected(document.forms[0].patGenderCode,"Gender")
 &&   isSelected(document.forms[0].patPrimaryCatCode,"Patient Category")
 &&   isSelected(document.forms[0].patSecondaryCatCode,"Secondary Category")
 &&   isEmpty(document.forms[0].patAddCityLocCode,"Location")
 &&   isEmpty(document.forms[0].patAddCityLoc,"Location")
 &&   isEmpty(document.forms[0].patRegCatCode,"Emergency Type")
 &&   isEmpty(document.forms[0].patIdMark1,"Id Mark 1")
 &&   isEmpty(document.forms[0].patIdMark2,"Id Mark 2")
 &&   isSelected(document.forms[0].patAddCountryCode,"Country")
 &&   isSelected(document.forms[0].patAddStateCode,"State")
 &&   isEmpty(document.forms[0].patNationalityCode,"Nationality ")
 &&   isSelected(document.forms[0].departmentCode,"Department")
 &&   isEmpty(document.forms[0].patRefDoctor,"Reffered By Doctor Name"))
    {
      return true;
    }
  else
  return false;     
}

/////////////////////////////////////////////////validate add unit.................................................
function ValidateAddUnit(){


	effectiveFrom = document.getElementsByName("effectiveFrom")[0];
	effectiveTo = document.getElementsByName("effectiveTo")[0];
	entryDate = document.getElementsByName("entryDate")[0];

	result = true;
	if(isEmpty(document.forms[0].unitName,"Unit Name")&&
	isSelected(document.forms[0].isGeneral,"Unit Type") &&
	isSelected(document.forms[0].isExpiry,"Is Expiry") &&
	validateDayMonth() &&
	compareDateCall(entryDate,effectiveFrom,2,"Current Date","Effective From") &&
	validateDate())
	
	return result;
}

////////////////////////////////////////////////////////validate modify unit layout..................................

function ValidateModifyUnit(){

if(isEmpty(document.forms[0].unitName,"Unit Name") &&
	isSelected(document.forms[0].isGeneral,"Unit Type"))
	
		{
		return true;
	}
	else
	return false;
}

////////////////////////////////////////////Refer Department Visit..................................................

/*function validateReferDeptVisit()
{
var valid=true;
if	(isEmpty(document.forms[0].patPrimaryCatCode,"Primary Category")&&
	 isEmpty(document.forms[0].patSecondaryCatCode,"Secondary Category"))

	valid=true;
else

	valid=false;

return valid;
}*/

//////////////////////////////////////////validates registration desk's ...registration and modification interfaces///////////////////////


	
	
///////////////////////////////////////////////////Unknown to Known/////////////////////////////////////////////////


///////////////////////////////////////////refer dept visit stamp.............

function ValidateReferDeptVisitStamp(){

//alert("valid call  ValidateReferDeptVisitStamp  "+document.getElementsByName('episodeCode')[0].value);

  var valid=true;
	if(validateEpisodeCode() )
	//&& isEmpty(document.forms[0].patPrimaryCatCode,"Primary Category")&&
	 // isEmpty(document.forms[0].patSecondaryCatCode,"Secondary Category"))
	//if(validateEpisodeCode())
	   valid=true;
	else
	     valid=false;     
	    
   // alert("refer dept validation  ValidateReferDeptVisitStamp"+valid);
	return valid;
 
}

function validateDateAgainstSysDate(obj,label){
// alert("dob select");
    if(document.getElementsByName('isActualDob')[1].checked){
		 //	alert("inside validateDateAgainstSysDate");
		    obj2= document.getElementsByName('sysDate')[0]    
		  //  alert(document.getElementsByName('sysDate')[0].value);
			if(compareDateCall(obj,obj2,2,label,"Current date"))			
			      return true;      	      
			  else	 
	    		  return false;	
	      }	 
	      else
	      return true;
}


function validateMlcDate(obj,label){
//	alert("inside validate MLC date");
    obj2= document.getElementsByName('sysDate')[0]    
	if(compareDateCall(obj,obj2,2,label,"System date"))	{
	//alert("sdksgdvgdb");
	      return true;      
	      }
	  else	 
	      return false;	
	 
}


///////////////////////set focus on cr no////////////////////////////////////////////
 function focusCrNo(){
  document.getElementsByName("patCrNo")[0].focus();
 }
 
 /////////////////////////////functions
 //////////////////////////// handles cursor movements and value assignments 
 /////////////////////////////for three  text boxes for CR Number in each tile.........................
 
function moveToRight(elem, evt){
		val=elem.value;
		maxLength =elem.getAttribute('maxlength');
		//alert(maxLength);
		i=0;
		if(val.length==maxLength){
			for(i=0; i<prevValue.length;i++)
				if(prevValue.charAt(i)!=val.charAt(i))
					break;
		}
		if(i==maxLength-1){
			if(elem.name == 'crNo1'){
				document.getElementsByName("crNo2")[0].focus();
			}else if(elem.name == 'crNo2'){
				document.getElementsByName("crNo3")[0].focus();
			}
		}	
	}
	
	function setPrevValue(elem, evt){
			prevValue = elem.value;
	}
	//////////////////////for 12 digit cr no
	
	function checkSumValidation(){
	
		var crNum  = document.getElementsByName("patCrNo")[0].value;
		 var checkSum = 0;
		 var power  = 12;
		 
		 for(var i=0;i<crNum.length-1;i++)
		 
		  checkSum += parseInt(crNum.substring(i,i+1)* power--);
		
		 checkSum=checkSum%11;
		 if((checkSum!=0 && checkSum!=1))
		  checkSum=11-checkSum;
		 if(checkSum==crNum.substring(11))
		   return true;
		 else
		 {
		  //alert("InValid CR No");
		  return false;
		 }

}
	
	
	
	
	/////////////////////For 13 digit cr no
	function checkSum(){
			var isValid = true;
			crNo=document.getElementsByName("patCrNo")[0].value;
			
	modulo_1=crNo.substring(0,1)*7 + crNo.substring(1,2)*6 + 
			crNo.substring(2,3)*5 + crNo.substring(3,4)*4 +  
			crNo.substring(4,5)*3 + crNo.substring(5,6)*2 +  
			crNo.substring(6,7)*7 + crNo.substring(7,8)*6 +
			crNo.substring(8,9)*5 + crNo.substring(9,10)*4 +  
			crNo.substring(10,11)*3 + crNo.substring(11,12)*2 ;

	modulo=modulo_1 % 11;
		checksum = (modulo==0)?1:(11-modulo)%10;
	if(checksum==crNo.charAt(crNo.length-1)){
	isValid = true;
	}
		else
	{
	//alert("InValid CR No");
	isValid = false;
	}
	return isValid;
}

	/////////////////////For 15 digit cr no Added by Singaravelan on 10-Apr-2015
	function checkSum15(){
			var isValid = true;
			crNo=document.getElementsByName("patCrNo")[0].value;
			
	modulo_1=crNo.substring(0,1)*3 + crNo.substring(1,2)*2 + 
			 crNo.substring(2,3)*7 + crNo.substring(3,4)*6 +  
			 crNo.substring(4,5)*5 + crNo.substring(5,6)*4 +  
			 crNo.substring(6,7)*3 + crNo.substring(7,8)*2 +
			 crNo.substring(8,9)*7 + crNo.substring(9,10)*6 +  
			 crNo.substring(10,11)*5 + crNo.substring(11,12)*4 +
			 crNo.substring(12,13)*3 + crNo.substring(13,14)*2 ;

	modulo=modulo_1 % 11;
		checksum = (modulo==0)?1:(11-modulo)%10;
	if(checksum==crNo.charAt(crNo.length-1)){
	isValid = true;
	}
		else
	{
	//alert("InValid CR No");
	isValid = false;
	}
	return isValid;
	}
	
////////////////////////////////////Padding with Zero	
	function padWithZeros(elem){
		val = elem.value;
		if(val.length>0){
			maxlength = elem.getAttribute('maxlength');
			zeroString="";
			for(i=0; i<maxlength-val.length;i++)
				zeroString+="0";
			elem.value = zeroString+val;
		}
	}
//////////////////////////for on load////////////////////////////////////////////
 var oldonload = window.onload;
 if (typeof window.onload != 'function') {
   window.onload = function() {
     callThisOnload();
   }
 } else {
   window.onload = function() {
     oldonload();
     callThisOnload();
   }
 } 
 //override this function in the respective tile
 function callThisOnload(){
 
 }
 
/////////////////////////Check MaxLength in case of fields like TextArea/////////////////////////////////////////////////////////

function CheckMaxLength(e,elem,maxLen){
		//alert("inside: CheckMaxLength");
    	key = e.keyCode;
        //alert(key);
		var valid=true;
		if(key==8 || key==46) //backspace || del
			return true;
		if(key==13){	//return not allowed
		valid=true;
		return valid;
		}
		val = elem.value; 
		if(val.length+1>maxLen){

		valid=false;
		
		}
		else{
		
		valid= true;

		}
		
		
		return valid;		
		}

/////////////////////////Check MaxLength and validate for alphabets,alphanumeric and numeric in case of fields like TextArea/////////////////////////////////////////////////////////

function CheckMaxLength(e,elem,maxLen,constraint){
		//constraint  0- alphabets, 1- alphanumeric, 2- numeric, 3- any character 
		//
		//alert("inside check max length")
		key = e.keyCode;
        //alert(key);
        if (window.event)
	   key = window.event.keyCode;
		else if (e)
		   key = e.which;
		else
	   return true;
		var valid=true;
		if(key==8 || key==46) //backspace || del
			return true;
		if(key==13){	//return not allowed
		valid=true;
		return valid;
		}
		if(constraint==0){ 				// Alphabets only 
			if(!validateAlphabetsOnly(e,elem))
				return false;
		}
		else if(constraint==1){					// Alphabets with numbers
			if(!validateAlphaNumericOnly(e,elem))
				return false;
		}
		else if(constraint==2){				// numbers only
			if(!validateNumeric(e))
				return false;
		}
		else if(constraint==3){				// any characters without initial space
			//alert(key)
			 if((getCursorIdex(elem)==0) && (key==32)){
					return false
				}
		}
		val = elem.value; 
		if(val.length+1>maxLen){

		valid=false;
		
		}
		else{
		
		valid= true;

		}
		
		
		return valid;		
}


///////////////////////////////////////check  min length for PIN..........................

function CheckMinLengthForPIN(elem,minLen){
//alert("pin min");
if(document.getElementsByName("isAddressDelhi")[0].checked){
//alert("delhi");
	if(CheckMinLength(elem,minLen,"PIN"))
	return true;
 	else
   	return false;
}
 else
 return true;

}
///////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////check min length......................................................


function CheckMinLength(elem,minLen,name){
//alert("check min length function");
var valid=true;
val = elem.value; 

if(val.length<minLen){
        
		//alert("if less than true");
 		alert(name+" Requires minimum"+minLen+" Digits");
 		valid=false;
 		elem.focus();
 		}
		else{
		//alert("false less than");
		valid= true;

		}
		
		//alert("min valid......1111"+valid);
		return valid;		
}



/////////////////////////////////////////////////////////////////////////////////////////////................................

///////////////////////////////alphabets only////////>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>



function validateAlphabetsOnly(e)
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
//	alert(key);
	// control keys
	if ((key==null) || (key==0) || (key==8) ||
		(key==9) || (key==13) || (key==27) || (key==32))
	   return true;

	// alphas and space
	else if ((("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ").indexOf(keychar) > -1))
	   return true;
	else
	   return false;
}

//////////////alphabets without initial space//////////////

function validateAlphabetsOnly(e,obj)
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
	//alert(keychar)
	//alert("indexof="+('abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ').indexOf(keychar))
	// control keys
	if ((key==null) || (key==0) || (key==8) ||
		(key==9) || (key==13) || (key==27))
	   return true;
	else if((getCursorIdex(obj)>0) && (key==32))
		return true
	// alphas and space
	
	else if ((("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ").indexOf(keychar) > -1))
	   return true;
	else
	   return false;
}


///////////////////////validate alphabets with dots//////////////////////////////


function validateAlphabetsWithDotsOnly(e,obj)
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
//	alert(key);
	// control keys
	if ((key==null) || (key==0) || (key==8) ||
		(key==9) || (key==13) || (key==27) )
	   return true;
	   
	else if((getCursorIdex(obj)>0) && (key==32))
		return true
	// alphas and space
	else if ((("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ.").indexOf(keychar) > -1))

	   return true;
	 
	else
	   return false;
}




///////////////////////////////////validate numeric//////////>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


function validateNumeric(e)
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
	keychar = keychar.toLowerCase();

	// control keys
	if ((key==null) || (key==0) || (key==8) ||
		(key==9) || (key==13) || (key==27) )
	   return true;

	// numbers
	else if ((("0123456789").indexOf(keychar) > -1))
	   return true;
	else
	   return false;
}//end of numericOnly

//////////////////////////////////////////////validate alpha numeric  without any special characters.................

function validateAlphaNumericOnly(e)
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
		(key==9) || (key==13) || (key==27) || (key==32)|| (key==47) || (key==45) || (key==95) || (key==44))
	   return true;
	

	// alphas and numeric
	else if ((("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ").indexOf(keychar) > -1))
	   return true;
		   
	   else if ((("0123456789").indexOf(keychar) > -1))
	   return true;
	   
	   
	   
	else
	   return false;
}

//////////////////////////////////////////////validate alpha numeric  without any special characters and without initial spaces.................

function validateAlphaNumericOnly(e,obj)
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
		(key==9) || (key==13) || (key==27))
	   return true;
	else if((getCursorIdex(obj)>0) && (key==32))
		return true

	// alphas and numeric
	else if ((("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ").indexOf(keychar) > -1))
	   return true;
		   
	   else if ((("0123456789").indexOf(keychar) > -1))
	   return true;
	   
	   
	   
	else
	   return false;
}

/////////////////////////////////////validate alphanumeric with dots only//////////////////

function validateAlphaNumericWithDotsOnly(e)
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
		(key==9) || (key==13) || (key==27) || (key==32)|| (key==47) || (key==45) || (key==95) || (key==44))
	   return true;
	

	// alphas and numeric
	else if ((("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ.").indexOf(keychar) > -1))
	   return true;
		   
	   else if ((("0123456789").indexOf(keychar) > -1))
	   return true;
	   
	   
	   
	else
	   return false;
}

/////////////////////////////////////validate alphanumeric with dots only without initial space//////////////////

function validateAlphaNumericWithDotsOnly(e,obj)
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
		(key==9) || (key==13) || (key==27) || (key==47) || (key==45) || (key==95) || (key==44))
	   return true;
	else if((getCursorIdex(obj)>0) && (key==32))
		return true

	// alphas and numeric
	else if ((("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ.").indexOf(keychar) > -1))
	   return true;
		   
	   else if ((("0123456789").indexOf(keychar) > -1))
	   return true;
	   
	   
	   
	else
	   return false;
}


/////////////////////////////////////validate alphanumeric with special character (.,/,-) only//////////////////

function validateAlphaNumericWithSpecialCharacterOnly(e)
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
		(key==9) || (key==13) || (key==27) || (key==32)|| (key==47) || (key==45) || (key==95) || (key==44))
	   return true;
	
	// alphas and numeric
	else if ((("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ./-").indexOf(keychar) > -1))
	   return true;
		   
	   else if ((("0123456789").indexOf(keychar) > -1))
	   return true;
	   
	   
	   
	else
	   return false;
}

/////////////////////////////////////validate alphanumeric with special character (.,/,-) only without initial space//////////////////

function validateAlphaNumericWithSpecialCharacterOnly(e,obj)
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
		(key==9) || (key==13) || (key==27) || (key==47) || (key==45) || (key==95) || (key==44))
	   return true;
	
	else if((getCursorIdex(obj)>0) && (key==32))
		return true
	// alphas and numeric
	else if ((("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ./-").indexOf(keychar) > -1))
	   return true;
		   
	   else if ((("0123456789").indexOf(keychar) > -1))
	   return true;
	   
	   
	   
	else
	   return false;
}


///////////////////////////////////validate alpha numeric field on Blur..........................


function IsAlphaNumeric(elem,fieldName)
{

var pari=parseInt(elem.value);
//alert(pari);
/*
if(parseInt(elem.value))
{
alert("Can't put Numeric Value in "+fieldName);
elem.focus();
return false;
}
else
return true;*/
}

////////////////////////////////////validate currency fields.......................................

/*
function validateCurrency(e,elem)
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
	keychar = keychar.toLowerCase();

	// control keys
	if ((key==null) || (key==0) || (key==8) ||
		(key==9) || (key==13) || (key==27) )
	   return true;

	// numbers
	else if ((("0123456789.").indexOf(keychar) > -1))
	   return true;
	else
	   return false;
  }
  */
  /////////////////////////////////////validate amount collected////////////////
  
function currencyFormat(fld, milSep, decSep, e)
{
	var sep = 0;
	var key = '';
	var milSep="";
	var i = j = 0;
	var len = len2 = 0;
	var strCheck = '0123456789';
	var aux = aux2 = '';
	var whichCode = (window.Event) ? e.which : e.keyCode;
	//if (whichCode == 13) return true;  // Enter
	if (whichCode == 0)	return true; //tab-index
	//alert(whichCode);
	if (whichCode == 8) return true;  // Back-Space 
	key = String.fromCharCode(whichCode);  // Get key value from key code
		if (strCheck.indexOf(key) == -1) return false;  // Not a valid key
		//len = fld.value.length;
		len=11;
		for(i = 0; i < len; i++)
		if ((fld.value.charAt(i) != '0') && (fld.value.charAt(i) != decSep)) break;
		aux = '';
	for(; i < len; i++)
	if (strCheck.indexOf(fld.value.charAt(i))!=-1) aux += fld.value.charAt(i);
	aux += key;
	len = aux.length;
	if (len == 0) fld.value = '';
	if (len == 1) fld.value = ''+ decSep + '' + aux;
	if (len == 2) fld.value = ''+ decSep + aux;
	if (len > 2) {
	aux2 = '';
	for (j = 0, i = len - 3; i >= 0; i--) {
	if (j == 3) {
	aux2 += milSep;
	j = 0;
}
aux2 += aux.charAt(i);
j++;
}
fld.value = " ";
len2 = aux2.length;
for (i = len2 - 1; i >= 0; i--)
fld.value += aux2.charAt(i);
fld.value += decSep + aux.substr(len - 2, len);
}
return false;
}
//////////////////////////////////////////////////// doHomeWork empty........///////////////////////////////////////////////////////////////////

function doHomeWork(){
}



//  ******************************************************************************
// *** Validation Functions

/**
 * Purpose : To ensure to enter a Numeric Value
 * Calling On Event : onkeypress
 * Parameters : 	1.	this	&	2.	event
 * Return Type : boolean
 
 * Ascii Code allowed 0 - 48 To 9 - 57 , for . - 46, Minus - 45
*/
function validateNumericOnly(obj,e)
{
	//alert("Char Code = "+e.charCode+"   Key Code = "+e.keyCode);
	var charCode;
	if(typeof e.charCode != 'undefined')	// Other
		charCode=e.charCode;
	else									// IE
		charCode=e.keyCode;
	//alert(charCode);
	var pattern=/\./;
	if( charCode==0 ||
		(!pattern.test(obj.value) && charCode==46) ||
		(obj.value.length==0 && charCode==45) || 
		(charCode>=48 && charCode<= 57) )
		return true;
	else
		return false;
}

/**
 * Purpose : To ensure to enter a Integer Value
 * Calling On Event : onkeypress
 * Parameters : 	1.	this	&	2.	event
 * Return Type : boolean
 
 * Ascii Code allowed 0 - 48 To 9 - 57 , Minus - 45 
*/
function validateIntegerOnly(obj,e)
{
	//alert("Char Code = "+e.charCode+"   Key Code = "+e.keyCode);
	var charCode;
	if(typeof e.charCode != 'undefined')	// Other
		charCode=e.charCode;
	else									// IE
		charCode=e.keyCode;
	//alert(charCode);
	if( charCode==0 || 
		(obj.value.length==0 && charCode==45) || 
		(charCode >= 48 && charCode <= 57) )
		return true;
	else
		return false;
}

/**
 * Purpose : To ensure to enter a Positive Integer Value
 * Calling On Event : onkeypress
 * Parameters : 	1.	this	&	2.	event
 * Return Type : boolean
 
 * Ascii Code allowed 0 - 48 To 9 - 57 
*/
function validatePositiveIntegerOnly(obj,e)
{
	//alert("Char Code = "+e.charCode+"   Key Code = "+e.keyCode);
	var charCode;
	if(typeof e.charCode != 'undefined')	// Other
		charCode=e.charCode;
	else									// IE
		charCode=e.keyCode;
	//alert(charCode);
	if( charCode==0 || 
		( charCode>=48 && charCode<= 57 ) )
		return true;
	else
		return false;
}


/**
 * Purpose : To ensure to enter a Alphabetic Value
 * Calling On Event : onkeypress
 * Parameters : 	1.	this	&	2.	event
 * Return Type : boolean
 
 * Ascii Code allowed A-65, Z-90, a-97, z-122, Space- 32, . -46
*/
function validateAlphaOnly(obj,e)
{
	//alert("Char Code = "+e.charCode+"   Key Code = "+e.keyCode);
	var charCode;
	if(typeof e.charCode != 'undefined')	// Other
		charCode=e.charCode;
	else									// IE
		charCode=e.keyCode;
	//alert(charCode);
	if( charCode==0 || 
		charCode==32 || 
		charCode==46 || 
		(charCode>=65 && charCode<=90) || 
		(charCode>=97 && charCode<=122) )
		return true;
	else
		return false;
}

/**
 * Purpose : To ensure to enter a Alphanumeric Value
 * Calling On Event : onkeypress
 * Parameters : 	1.	this	&	2.	event
 * Return Type : boolean
 
 * Ascii Code allowed 0 - 48 To 9 - 57,A-65, Z-90, a-97, z-122, Space- 32, for , - 44 
*/
function validateAlphaNumOnly(obj,e)
{
	//alert("Char Code = "+e.charCode+"   Key Code = "+e.keyCode);
	var charCode;
	if(typeof e.charCode != 'undefined')	// Other
		charCode=e.charCode;
	else									// IE
		charCode=e.keyCode;
	//alert(charCode);
	if( charCode==0 || 
		charCode==44 || 
		charCode==32 || 
		charCode==46 || 
		(charCode>=48 && charCode<=57) || 
		(charCode>=65 && charCode<=90) || 
		(charCode>=97 && charCode<=122) )
		return true;
	else
		return false;
}

/**
 * Purpose : To ensure that entered Value don't have any Special Character
 * Calling On Event : onkeypress
 * Parameters : 	1.	this	&	2.	event
 * Return Type : boolean
 
 * Restrict to enter ~!@#$%^&
 * ~ - 126, ! - 33, @ - 64, # - 35, $ - 36, % - 37,^ - 94, & - 38 
*/
function notSpecChar(obj,e) 
{
	//alert("Char Code = "+e.charCode+"   Key Code = "+e.keyCode);
	var charCode;
	if(typeof e.charCode != 'undefined')	// Other
		charCode=e.charCode;
	else									// IE
		charCode=e.keyCode;
	//alert(charCode);
	if( charCode==126 || charCode==33 || 
		charCode==64 || charCode==35 || 
		charCode==36 || charCode==37 || 
		charCode==94 || charCode== 38 )
		return false;
	else
		return true;
}

/**
 * Purpose : To validate whether a given String a Numeric Value
 * Calling On Event : onchange, user-defined way
 * Parameters : 	1.	val/string to validate
 * Return Type : boolean
 
 * Ascii Code allowed 0 - 48 To 9 - 57 , for . - 46, Minus - 45
*/
function validateNumericValue(val)
{
	var pattern=/^-?\d*\.?\d*$/;
	return pattern.test(val);
}

/**
 * Purpose : To validate whether a given String a Integer Value
 * Calling On Event : onchange, user-defined way
 * Parameters : 	1.	val/string to validate
 * Return Type : boolean
 
 * Ascii Code allowed 0 - 48 To 9 - 57 , Minus - 45
*/
function validateIntegerValue(val)
{
	var pattern=/^-?[0-9]*$/;
	return pattern.test(val);
}

/**
 * Purpose : To validate whether a given String a Positive Integer Value
 * Calling On Event : onchange, user-defined way
 * Parameters : 	1.	val/string to validate
 * Return Type : boolean
 
 * Ascii Code allowed 0 - 48 To 9 - 57
*/
function validatePositiveIntegerValue(val)
{
	var pattern=/^[0-9]*$/;
	return pattern.test(val);
}

/**
 * Purpose : To validate whether a given String a Alphabetic Value
 * Calling On Event : onchange, user-defined way
 * Parameters : 	1.	val/string to validate
 * Return Type : boolean
 
 * Ascii Code allowed A-65, Z-90, a-97, z-122, Space- 32, . -46
*/
function validateAlphaValue(val)
{
	var pattern=/^[a-zA-Z .]*$/;
	return pattern.test(val);
}

/**
 * Purpose : To validate whether a given String a Alphanumeric Value
 * Calling On Event : onchange, user-defined way
 * Parameters : 	1.	val/string to validate
 * Return Type : boolean
 
 * Ascii Code allowed 0 - 48 To 9 - 57,A-65, Z-90, a-97, z-122, Space- 32, for , - 44
*/
function validateAlphaNumValue(val)
{
	var pattern=/^[a-zA-Z, .0-9]*$/;
	return pattern.test(val);
}

/**
 * Purpose : To validate whether a given String don't have any Special Character
 * Calling On Event : onchange, user-defined way
 * Parameters : 	1.	val/string to validate
 * Return Type : boolean
 
 * Restrict to enter ~!@#$%^&
 * ~ - 126, ! - 33, @ - 64, # - 35, $ - 36, % - 37,^ - 94, & - 38 
*/
function notSpecCharValue(val)
{
	var pattern=/^[^~!@#$%^&]*$/;
	return pattern.test(val);
}

///////////////////////getting index of char on keypress////////////////////////////////////////////////

function getCursorIdex(o) {
	if (o.createTextRange) {
		var r = document.selection.createRange().duplicate()
		r.moveEnd('character', o.value.length)
		if (r.text == '') return o.value.length
		return o.value.lastIndexOf(r.text)
	} else return o.selectionStart
}



/* <p>Developer : Deepak Tiwari
 * <p>
 * <p>Fuction shortcutKeysEventHandler handles eventListeners
 * <p>attached to Save,Clear & Cancel images in whole module.
 * @param event
 * <Note> :: Check For <div id="normalMsg"> tag in your JSP. Help wold not work in case of absence
 */	
 
var moduleCommonDIV="shortCutKey";
 
var first_key_Down=false;

var _helpOpenFlag=false;

var imgArray;

var keyCodeArray;

var enableShortCutKey=true;

var masterHotKeyCode=18; // Key :: ALT 

var _helpKeyCode=112; // Key :: F1

imgArray  = new Array();

// imgArray stores names of Images used for various events.
// There can be multiple images for a single event.
// Corresponding to single event there can be only single event key code.
// Event Images and Key code used for that event should be at same index within their repective arrays.
// keyCodeArray defined in sequence with imgArray :: Event wise key code defined
// Keys || Insert : 45 :: Delete : 46 :: End : 35

imgArray  = [
               ['save_tab.gif' ,'Save.gif' , 'btn-sv.png' ],   //Array of Images Used for Save Event
               
               ['clear_tab.gif' ,'btn-clr.png'],   //Array of Images Used for Clear Event 
               
               ['cancel_tab.gif', 'btn-ccl.png']  //Array of Images Used for Cancel Event
            ];


keyCodeArray = new Array("45","46","35"); 

var ie = document.all;

var nn6 = document.getElementById &&! document.all;

if(ie)
{
    document.attachEvent('onkeydown',firstKeyDown);
    
    document.attachEvent('onkeyup',shortcutKeysEventHandler);
}
else
{
    document.onkeydown=firstKeyDown;
    
    document.onkeyup=shortcutKeysEventHandler;
    
    window.focus();
}

function firstKeyDown(e)
{
	if(e.keyCode==masterHotKeyCode)
	{
	    first_key_Down=true;
	}  
}   
   
function shortcutKeysEventHandler(e)
{
	 var retEval          =  false;
	 
	 var imgName          =  false;
	 
	 var bugReported      =  false;
	 
	 var listenerIndx     =  false;
	 
	 var imageVisible     =  true;
	 
	 var imgArrForKeyCode = new Array();
	 
	 if(e.keyCode==masterHotKeyCode)
	 {
	    first_key_Down  = false;
	 }
	 else
	 {
	 	 if(first_key_Down == true && enableShortCutKey)
	 	 {
	 	 	for(var i=0;i<keyCodeArray.length;i++)
	 	 	{
	 	 		if(parseInt(e.keyCode)==parseInt(keyCodeArray[i]))
	 	 		{
	 	 			if(keyCodeArray.length==imgArray.length || keyCodeArray.length<imgArray.length)
	 	 			{
	 	 			   imgArrForKeyCode=imgArray[i];
	 	 			  
	 	 			   imgName=true;
	 	 			}
	 	 			else
	 	 			{
	 	 			   alert("BUG::Image Sets Not defined for every Key Codes");
	 	 			   
	 	 			   imgName=false;
	 	 			   
	 	 			   bugReported=true;
	 	 			} 
	 	 		}
	 	 	}
	 	 }
	 }   
	 
	 if(imgName != false)
	 {
	    var obj = document.getElementsByTagName("img");
	    
	    for(var i = obj.length-1 ; (i >= 0) && (listenerIndx==false) ; i--)
	    {
	   	   var strArr = new Array();
	   	   
	   	   strArr     = obj[i].src.split("/");
	   	   
	       for(var x=0;x<imgArrForKeyCode.length;x++)
	       {
	          if(strArr[strArr.length-1]==imgArrForKeyCode[x])
	          {
	   	         listenerIndx = i;
	   	         
	   	         var selObj=obj[i];
	   	         
	   	         // While Loop::Checking whether the Button is visible on Screen or not.
	   	         while(selObj.parentNode.tagName!="FORM")
	   	         {
	   	             if(selObj.parentNode.tagName=="DIV")
	   	             {
	   	            	if(selObj.parentNode.style.display=="none")
	   	            	{
	   	            		imageVisible=false;
	   	            	}
	   	             }
	   	             selObj=selObj.parentNode;
	   	         }
	          } 
	       }  
	    }
	    if(listenerIndx != false)
	    {
	       if(typeof(obj[listenerIndx].attributes['onclick'])!="undefined")
	       {
	          var invokeFuncName=obj[listenerIndx].attributes['onclick'].value;
	          
	          if(invokeFuncName!="" && invokeFuncName.length>2 && invokeFuncName.indexOf('(')>-1 && invokeFuncName.indexOf(')')>-1)
	          {  
	             if(invokeFuncName.indexOf("return" ) > -1)
	             {
	                invokeFuncName=invokeFuncName.split("return ")[1];
	             }
	             if(_helpOpenFlag)
	             {
	             	document.getElementById(moduleCommonDIV).innerHTML="";
     	    	    
     	    	    _helpOpenFlag=false;
	             }
	             //alert("imageVisible->"+imageVisible);
	             if(imageVisible)
	             {
	               var retEval=eval(invokeFuncName);
	             }
	             else
	             {
	             	alert("Associated Image Not Visible.");
	             }  
	            
	             first_key_Down=false;
	          }
	          else
	          {
	             alert("No Event Handler Attached To :: onClick ::  Found.");
	          }    
	       }
	       else
	       {
	       	   alert("No Event Listener :: onClick ::  Found.");
	       }   
	    }
	    else
	    {
	    	alert("Shortcut Associated Image Not Found.");
	    }  
     }  
     else
     {
         if(first_key_Down==true)
         {
     	    if(e.keyCode==_helpKeyCode)
     	    {
     	    	if(_helpOpenFlag==false)
     	    	{
     	    	    _helpOpenFlag=true;
     	    	   
     	    	    shortCutKeysHELP();
     	    	}
     	    	else
     	    	{
     	    	    document.getElementById(moduleCommonDIV).innerHTML="";
     	    	    
     	    	    _helpOpenFlag=false;
     	    	}   
     	    }
     	    else
     	    {
     	        if(bugReported==false && enableShortCutKey)
     	        {
     	          // alert("Sorry, No ShortCut Event Attached.For Help Press ::  Alt+F1");
     	        }   
    	    }  
    	    first_key_Down=false;
         }   
     }
}

//This Function opens ShortCut Key Help 

function shortCutKeysHELP() 
 {
   var qh=150;var qw=300;var dh=0;var dw=0;
   
   if(window.innerHeight)
   {
      dh=window.innerHeight;
      
      dw=window.innerWidth;
   }
   else 
   {
      dh=document.documentElement.clientHeight;
      
      dw=document.documentElement.clientWidth;
   }
   var tpos=parseInt((dh-qh)/2);
   
   var lpos=parseInt((dw-qw)/2);
   
   var buttonStr;
   
   var wt = '<div id="qmvi_loading_div" style="top:'+tpos+'px;left:'+lpos+'px;width:'+qw+'px;position:absolute;text-align:center;font-family:Arial;text-decoration:none;font-weight:normal;font-size:13px;color:#00224A;background-color:#ffffff;border-width:1px;border-color:#828EA2;border-style:solid;">';
       wt+= '<table width="100%" cellspacing="1px" cellpadding="1px" border="0">';
       wt+= '<tr class="HEADER"><td colspan="2">Short Cut Key Help Menu</td></tr>';
       wt+= '<tr><td class="multiLabel" width="50%">Short Cut Keys</td><td class="multiLabel" width="50%">Event</td></tr>';
       wt+= '<tr><td class="multiControl" width="50%">ALT + Insert</td><td class="multiControl" width="50%">SAVE</td></tr>';
       wt+= '<tr><td class="multiControl" width="50%">ALT + Delete</td><td class="multiControl" width="50%">CLEAR</td></tr>';
       wt+= '<tr><td class="multiControl" width="50%">ALT + End</td><td class="multiControl" width="50%">CANCEL</td></tr>';
       wt+= '<tr><td class="multiControl" width="50%">ALT + F1</td><td class="multiControl" width="50%">HELP/Hide HELP</td></tr>';
       if(enableShortCutKey)
       {
         buttonStr="Disable";
       } 
       else
       {
         buttonStr="Enable"; 
       }    
       wt+= '<tr><td class="multiControl" width="100%" colspan="2"><input type="button" name="shortCutHelpED_Button" value="'+buttonStr+' Short Cut Keys" onClick="enableDisableShortCutKeys();"</td></tr>';
       wt+= '<tr class="FOOTER"><td colspan="2"></td></tr>';
       wt+= '</table>';
       wt+='</div>';
       
   document.getElementById(moduleCommonDIV).innerHTML="";
   
   document.getElementById(moduleCommonDIV).style.display="block";
   
   document.getElementById(moduleCommonDIV).innerHTML=wt;
 }


// This Function Enables or Disables ShortCutKey Event Handler
 
function enableDisableShortCutKeys()
{
	if(enableShortCutKey)
	{
	    enableShortCutKey=false;
	  
	    //alert("Short Cut Keys Disabled");
	  
	    document.forms[0].shortCutHelpED_Button.value="Enable Short Cut Keys";
	  
	    document.getElementById(moduleCommonDIV).innerHTML="";
	  
	    _helpOpenFlag=false;
	}
	else
	{
	    enableShortCutKey=true;
	 
	    //alert("Short Cut Keys Enabled");
	   
	    document.forms[0].shortCutHelpED_Button.value="Disable Short Cut Keys";
	 
	    document.getElementById(moduleCommonDIV).innerHTML="";
	 
	    _helpOpenFlag=false;
	}    
}


///////////Function for focusing on the First element of a form ////////
 ////Note : You have to call this function onload of your form and ////
 ////take care of that the element should be the first element ,  ////
 ////on which you want to focus no hidden elements should be in between it
 
 function focusFirstElementOnLoad()
 {
 document.forms[0].elements[0].focus();
 }
 

///////////////////////validate alphabets with dots//////////////////////////////


function validateAlphabetsWithDotsAndForwardSlashWithHypen(e,obj)
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
//	alert(key);
	// control keys
	if ((key==null) || (key==0) || (key==8) ||
		(key==9) || (key==13) || (key==27) )
	   return true;
	   
	else if((getCursorIdex(obj)>0) && (key==32))
		return true
	// alphas and space
	else if ((("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ./-").indexOf(keychar) > -1))

	   return true;
	 
	else
	   return false;
} 
 
// *** End Validation Functions
//  ******************************************************************************
 
 
 
     
   
   

function isAlpha(elem,fieldName)
{
if(parseInt(elem.value))
{
alert("Can't put Numeric Value in "+fieldName);
elem.focus();
return false;
}
else
return true;
}

 function  shownondelhidiv(){   
   document.getElementById("divpatAddCityLocCode").style.display="none";            
   document.getElementById("divpatAddCityLocation").style.display="block";
  document.getElementsByName("patAddCityLoc")[0].value="";
   document.getElementsByName("patAddStateCode")[0].value="84";   
   document.getElementsByName("patAddCountryCode")[0].value="1";
   document.getElementsByName("patAddStateCode")[0].disabled=false;   
   document.getElementsByName("patAddCountryCode")[0].disabled=false;  	   	          
   }
     
function  shownondelhidiv(stateCode,countryCode){   
  document.getElementById("divpatAddCityLocCode").style.display="none";            
  document.getElementById("divpatAddCityLocation").style.display="block";
  document.getElementsByName("patAddCityLoc")[0].value="";
  document.getElementsByName("patAddStateCode")[0].value=stateCode;   
  document.getElementsByName("patAddCountryCode")[0].value=countryCode;
  document.getElementsByName("patAddStateCode")[0].disabled=false;   
  document.getElementsByName("patAddCountryCode")[0].disabled=false;  	   	          
  }
  
  function IsreferredOnLoad(elem)
 {

//  alert("Isreferred called");

 // alert("Isreferredonload called");

  /*
    if (elem.checked)
    {
      //  alert("Isreferred checked");

		document.getElementById('divReferredByDoc').style.display="";
		document.getElementById('divReferredByDocText').style.display="";
		if(document.getElementsByName('referringInstType')[0].checked){alert("if");
        //document.getElementsByName('referringInstType')[0].checked="true";    
		document.getElementById('divRefHosname').style.display="none";
		document.getElementById('divRefHosCode').style.display="";
		document.getElementById('divReferredInstitute').style.display="";
		
		
		}
		else{
		document.getElementById('divRefHosname').style.display="";
		document.getElementById('divRefHosCode').style.display="none";
		document.getElementById('divReferredInstitute').style.display="";
		
			}
    }
    else
 	{  
    
		document.getElementById('divRefHosname').style.display="none";
		document.getElementById('divRefHosCode').style.display="none";
		document.getElementById('divReferredInstitute').style.display="none";
		document.getElementById('divReferredByDoc').style.display="none";
		document.getElementById('divReferredByDocText').style.display="none";
	
 	}    */ 
 	//alert("before isrefered check");
 
 	if(elem.checked){
//alert("isref true");
// 	 	document.getElementById('divReferred').style.display="";
 	if(document.getElementsByName('referringInstType')[0].checked){
// alert("gnctd true checking");
        //document.getElementsByName('referringInstType')[0].checked="true";    
		document.getElementById('divRefHosname').style.display="none";
		document.getElementById('divRefHosCode').style.display="";
		document.getElementById('divReferredInstitute').style.display="";
		document.getElementById('divReferred').style.display="";
		
		//alert("ref doct value"+document.getElementsByName("patRefDoctor")[0].value);
		}
		else{
	//	alert("other");
		document.getElementById('divRefHosname').style.display="";
		document.getElementById('divRefHosCode').style.display="none";
		document.getElementById('divReferredInstitute').style.display="";

		document.getElementById('divReferred').style.display="none";
		
		//alert("ref doct value"+document.getElementsByName("patRefDoctor")[0].value);

		//document.getElementById('divReferred').style.display="none";
		document.getElementById('divReferred').style.display="";
		
		

			}
		
		//document.getElementById('divDocTitle').style.display="";
		document.getElementById('divDocName').style.display="";
 	
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
		document.getElementById('divDocName').style.display="none";
 	
 } 
 }
  
  
 function Isreferred(elem)
 {
 // alert("Isreferred called");
  /*
    if (elem.checked)
    {
      //  alert("Isreferred checked");

		document.getElementById('divReferredByDoc').style.display="";
		document.getElementById('divReferredByDocText').style.display="";
		if(document.getElementsByName('referringInstType')[0].checked){alert("if");
        //document.getElementsByName('referringInstType')[0].checked="true";    
		document.getElementById('divRefHosname').style.display="none";
		document.getElementById('divRefHosCode').style.display="";
		document.getElementById('divReferredInstitute').style.display="";
		
		
		}
		else{
		document.getElementById('divRefHosname').style.display="";
		document.getElementById('divRefHosCode').style.display="none";
		document.getElementById('divReferredInstitute').style.display="";
		
			}
    }
    else
 	{  
    
		document.getElementById('divRefHosname').style.display="none";
		document.getElementById('divRefHosCode').style.display="none";
		document.getElementById('divReferredInstitute').style.display="none";
		document.getElementById('divReferredByDoc').style.display="none";
		document.getElementById('divReferredByDocText').style.display="none";
	
 	}    */ 
 	
 	if(elem.checked){
//alert("isref true");
// 	 	document.getElementById('divReferred').style.display="";
		document.getElementById('divRefDtlId').style.display="block";
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
		
		//document.getElementById('divDocTitle').style.display="";
		//document.getElementById('divDocName').style.display="";
 	
 	}
 	
 	else
 	{
	 	document.getElementById('divRefDtlId').style.display="none";
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
 function showdivhosname()
 {
//    alert("showdivhosname");
    document.getElementById("divRefHosCode").style.display="none";  
	document.getElementById("divRefHosname").style.display="";  
	document.getElementsByName("patRefGnctdHospitalCode")[0].selectedIndex=0;
	Isreferred(document.getElementsByName("isReferred")[0]);
 }
 
 function showdivhoscode()
 {

  

// alert("showdivhoscode");

    document.getElementById("divRefHosCode").style.display="";  
	document.getElementById("divRefHosname").style.display="none"; 
	document.getElementsByName("patRefHospitalName")[0].value="";
	document.getElementsByName("patRefDoctor")[0].value="";
	//alert("checing!!!!!!!!!");
	Isreferred(document.getElementsByName("isReferred")[0]);
 } 
 
function showdepartmentdiv()
{
document.getElementsByName('departmentdiv')[0].value="1";

}
  
function submitFormOnValidate(flag,mode)
{
	//alert("flag 123456 "+flag+" mode 123456 "+mode);
	if(flag)
	{
	// alert("inside if")
		//if(!validateDivDob()) return;
		submitForm(mode);
	}
	else{
// 	alert("elesee")
	}
	
}


function submitFormOnValidateCMO(flag,mode){
//alert("submit form on vlaidate");
//alert(mode);
	if(flag)
		submitRegister(mode);
}

/*function submitForm(mode)
{     alert("submitting");
	 document.getElementsByName("hmode")[0].value=mode;
	 alert("submitform Hmode"+document.getElementsByName("hmode")[0].value);   
	 doHomeWork();  
	 document.forms[0].submit();
}
*/
function deleteRow(idx){
//	alert(idx);
	//alert(document.getElementsByName("hmode")[0].value)
	//alert("remove check");
	document.getElementsByName("removeDept")[0].value = idx;
	submitForm("REMOVEDEPT");
}










////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

/////////////////////////////////////////////////////function for validating minimum length of a field///////////////////////////////////////////////////////////////////////////////////////////////////////////////


function validateMinLength(elem,minlen) {
	 var isValid = true;
     if(elem)
		value=elem.value;
     else
		value="";
				     
       if ((value.length<minlen))
				               {
				                isValid = false;
				              }
   return isValid;
 } 





////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

////////////////////////////////////////////validation for mandatory fields////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


function isValid(obj,name,minVal)

{
 //alert(obj.name);

  	if(obj!=null)
{
	
var val	=	new String(obj.value);
		if(document.getElementsByName('isActualDob')[0].checked)
		{
      //   alert("if.checked");		
		




      //  alert(val);

		if(val=="" || val>minVal)

		{

			alert("Please Enter valid "+ name +"(<125)");

			obj.value=val;

			obj.focus();

			return false;

		}
		else
		{
			if(val=="0")
			{
				alert("Please Enter Valid"+name+"{>0}");
				obj.value=val;

				obj.focus();

				return false;
			}
		}
      }
     else
			obj.value=val;
		return true;
	}
	return false;

}

function isSelected(combo,name)

{
//alert(combo);

	if(combo!=null && combo.selectedIndex==0)

	{

		alert("Please Select the "+ name);

		combo.focus();

		return false;

	}

	return true;

}





function submitForm(mode)
{
    
    // alert("submitform....");
     document.getElementsByName("hmode")[0].value=mode;
   //  doHomeWork();  
  //   alert("submit form action is"+document.forms[0].action);  
    // alert("hmode "+document.getElementsByName("hmode")[0].value)   
    
	 document.forms[0].submit();
	 
}

function comboValidation(obj, str)
{	var valid= true
	if(obj.value==-1 || obj.value=="")
	{
		alert("Please Select the "+str)
		valid=false
		obj.focus()
	}
	return valid
}

function ValidateDepartmentUnit(field) {
         var isValid = true;
        
//alert('inside ValidateDepartment of registration.js....');
      value2=document.getElementsByName("departmentUnitCode")[0].value; 
    //  alert(value2);
      if(field)
      value=field.value;
      else
      value="";
      
         if ((value.length == 0)||(value=='-1'))
               { 
               if(value2=='-1'){
               	alert("Please Select the Unit");
                isValid = false;
                document.getElementsByName("departmentUnitCode")[0].focus();
              }
              }
 // alert(isValid);               
  return isValid;
        

       } 


function ValidateReferToDepartment(field){

var isValid = true;
        

      value2=document.getElementsByName("refToDepartmentCode")[0].value; 
    //  alert(value2);
      if(field)
      value=field.value;
      else
      value="";
      
         if ((value.length == 0)||(value=='-1'))
               { 
               if(value2=='-1'){
               	alert("Select Referred to Department");
                isValid = false;
                document.getElementsByName("refToDepartmentCode")[0].focus();
              }
              }
 // alert(isValid);               
  return isValid;

}





	

function checkValueOfCombo(elem,field,mode)

{
if(elem.value=="-1")
alert("Select a "+field);
else
{

 submitForm(mode);
 
 }
 }
	


function showdivEmployee()
{
//alert("show emp");
document.getElementById("divCmoName").style.display="none";
document.getElementById("divLabelcmoName").style.display="";
//document.getElementById("divemployeeLabelcmoCode").style.display="";
document.getElementById("divemployeeCmoCode").style.display="";
document.getElementsByName("doctorName")[0].value="";
document.getElementsByName("cmoCode")[0].value="";
}


function showdivnonemployee(){
document.getElementById("divCmoName").style.display="";
document.getElementById("divLabelcmoName").style.display="";
//document.getElementById("divemployeeLabelcmoCode").style.display="none";
document.getElementById("divemployeeCmoCode").style.display="none";
document.getElementsByName("doctorName")[0].value="";
document.getElementsByName("cmoCode")[0].value="";

}

///////////////////////////////function to validate prevCRNO......................................

function validatePrevCRNo(){
var valid=true;
if(validateMinLength(document.getElementsByName('prevCrNo')[0],12))
valid=true;
else{
valid=false;  
alert("InValid Previous CR Number");
}
return valid;

}

////////////////////////function to check the minimum length allowed for any text box////////

function validateMinimumLength(obj,name,minimumSize)
{
	
	var valid=true
	var len=obj.value.length
	if(parseInt(len)>0 && parseInt(len)<parseInt(minimumSize))
	{
		alert(name +' cannot be less than '+ minimumSize +' digits')
		valid=false
		obj.focus();
		
	}
	return valid
}


/////////////////////////function to check if the pin number starts with zero/////////


function validatePinNumber(obj)
{	

	var valid=true
	var len=obj.value.length
	var val=obj.value
		
	if (parseInt(len)>0 )
	{
		
		if((val.charAt(0))==0)
		{
		alert("Pin Number Cannot Start with Zero")
		valid=false
		obj.focus()
		}
		else
		{
			valid=true
		}
	}
	else
	{
		valid=true
	}
	
	return valid
	
}

/////////////////function to check if the text starts with dot/////////////////////////

function validateStartingWithDot(obj,name)
{

	var valid=true
	var len=obj.value.length
	var val=obj.value
	
	if(parseInt(len)>0)
	{
		if((val.charAt(0))=='.')
		{
			alert(name+" cannot start with special character dot")
			valid=false
			return valid
		}
		
	}
	
	return valid
}


////////////////////////function to check for two consecutive dots////////////////

function validateTwoConsecutiveDots(obj,name)
{
	var valid=true
	var len=obj.value.length
	var val=obj.value
	
	if(parseInt(len)>0)
	{
		for(i=0;i<len;i++)
		{
			if((val.charAt(i))=='.')
			{
				if((val.charAt(i))==(val.charAt(i+1)))
				{
				alert(name+" cannot have two consecutive special character dot")
				valid=false
				
				return valid
				}
			}
		}
	}
	
	return valid	
}


//////////////////////function to check if the text ends with dot/////////////////////

function validateEndingWithDot(obj,name)
{
	var valid=true
	var len=obj.value.length
	var val=obj.value
	
	if(parseInt(len)>0)
	{
		if((val.charAt(len-1))=='.')
		{
			alert(name+" cannot end with special character dot")
			valid=false
			return valid
		}
		
	}
	
	return valid
} 


//////////////////////////validate text area with dots//////////////////

function validateDot(obj,name)
{

	var valid=false
	
	if(validateStartingWithDot(obj,name)
	&& validateTwoConsecutiveDots(obj,name)
	&& validateEndingWithDot(obj,name))
	{
		
		valid=true
	}

	return valid
}


/////////////////function to check if the text starts with special character/////////////////////////

function validateStartingWithSpecialCharacter(obj,name)
{
	var valid=true
	var len=obj.value.length
	var val=obj.value
	
	if(parseInt(len)>0)
	{
		if((val.charAt(0))=='.' ||  (val.charAt(0))=='/' || (val.charAt(0))=='-')
		{
			alert(name+" cannot start with special character ")
			valid=false
			return valid
		}
		
	}
	
	return valid
}


////////////////////////function to check for two consecutive special character////////////////

function validateTwoConsecutiveSpecialCharacter(obj,name)
{
	var valid=true
	var len=obj.value.length
	var val=obj.value
	
	if(parseInt(len)>0)
	{
		for(i=0;i<len;i++)
		{
			if((val.charAt(i))=='.' || (val.charAt(i))=='/' || (val.charAt(i))=='-')
			{
				if((val.charAt(i))==(val.charAt(i+1)))
				{
				alert(name+" cannot have two consecutive special character ")
				valid=false
				
				return valid
				}
			}
		}
	}
	
	return valid	
}


//////////////////////function to check if the text ends with special character/////////////////////

function validateEndingWithSpecialCharacter(obj,name)
{
	var valid=true
	var len=obj.value.length
	var val=obj.value
	
	if(parseInt(len)>0)
	{
		if((val.charAt(len-1))=='.' || (val.charAt(len-1))=='/' || (val.charAt(len-1))=='-')
		{
			alert(name+" cannot end with special character ")
			valid=false
			return valid
		}
		
	}
	
	return valid
} 


//////////////////////////validate text area with special character//////////////////

function validateSpecialCharacter(obj,name)
{
	var valid=false
	//alert("entry")
	if(validateStartingWithSpecialCharacter(obj,name)
	&& validateTwoConsecutiveSpecialCharacter(obj,name)
	&& validateEndingWithSpecialCharacter(obj,name))
	{
		
		valid=true
	}

	return valid
}

/////////////////////////////////////functions for display country state location ////////////////////
var empCode="";
function varifyEmployeeCode()
{
	// empCode
}

//////////////////////////////////function to move element up in list ////////////////////////////////
function moveUP(Obj)
{
	var list=Obj;	
	var len=list.length;
	for(var i=0;i<len;i++)
	{
		if(list.options[i].selected)
		{
			if(i==0) return;
			
			var temp;
			temp=list.options[i-1].value;
			list.options[i-1].value=list.options[i].value;
			list.options[i].value=temp;
			
			temp=list.options[i-1].text;
			list.options[i-1].text=list.options[i].text;
			list.options[i].text=temp;
			
			list.options[i-1].selected=true;;
			list.options[i].selected=false;
		}
	}
}


//////////////////////////////////function to move element down in list ////////////////////////////////
function moveDOWN(Obj)
{
	
	var list=Obj;	

	var len=list.length;
	for(var i=len-1;i>=0;i--)
	{
		if(list.options[i].selected)
		{
			if(i==(len-1)) return;
			
			var temp;
			temp=list.options[i+1].value;
			list.options[i+1].value=list.options[i].value;
			list.options[i].value=temp;
			
			temp=list.options[i+1].text;
			list.options[i+1].text=list.options[i].text;
			list.options[i].text=temp;
			
			list.options[i+1].selected=true;;
			list.options[i].selected=false;
		}
	}
}


//////////////////////////////////function for Trimming ////////////////////////////////

function trimData(val)
{
	//alert((typeof val).toUpperCase());
	if(val && val!=null && val!="" && (typeof val).toUpperCase() == 'STRING')
	{
		while(val.substr(0,1)==' ')	val=val.substr(1);
		while(val.substr(val.length-1,1)==' ')	val=val.substr(0,val.length-1);			
	}
	return val;
}

function trimLeftZero(val)
{
	if((typeof val) != 'undefined' && val.length>1)
	{
		while(val.substr(0,1)=='0')	val=val.substr(1);
	}
	return val;
}

/**************************** Date Conversion And Adding Date *************************************/

var monthShortNames = ["Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"];
var monthFullNames = ["January","February","March","April","May","June","July","August",
						"September","October","November","December"];
var weekdayShortNames = ["Sun","Mon","Tue","Wed","Thu","Fri","Sat"];
var weekdayFullNames = ["Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"];
function getShortMonthNameNo(mon)
{	
	for(var i=0;i<monthShortNames.length;i++)
	{
		if(monthShortNames[i].toUpperCase()==mon.toUpperCase())
			return i;
	}
	return -1;
}

function getShortMonthName(no)
{	
	if(typeof no=="number" && no>=0 && no<=11)
		return monthShortNames[no];
	else
		return null;
}

function convertStrToDate(strDate, strFormat)
{
	try
	{
		var fT=["dd","MM","Mon","yyyy","yy","hh","mm","ss"];	// formatTokens 
		// dd date(01), MM month(01), Mon month(Jan), yyyy Year(2002), yy Year(02), hh Hours(23), 
		// mm Minutes(28), ss Seconds(34)
		
		var value= ["","","","","","","",""];	// formatToken Values
		var day="00", month="00", year="0000", hour="00", min="00", sec="00";
		
		// Date As String is in Date Format dd/mm/yyyy hh:mm
		var _fDAS="dd/MM/yyyy hh:mm";
		_fDAS=strFormat;
		
		for(var i=0;i<fT.length;i++)
		{
			var beg=_fDAS.indexOf(fT[i]);
			if(beg!=-1)
			{
				var len=fT[i].length;
				value[i]=trimLeftZero(strDate.substr(beg,len));
			}
		}
		//alert(value);
		for(var i=0;i<value.length;i++)
		{
			if(fT[i]=="dd" && value[i]!="")
				day = parseInt(value[i]);
			else if(fT[i]=="MM" && value[i]!="")
				month=parseInt(value[i])-1;					
			else if(fT[i]=="Mon" && value[i]!="")
				month = getShortMonthNameNo(value[i]);
			else if(fT[i]=="yyyy" && value[i]!="")
				year = parseInt(value[i]);
			else if(fT[i]=="yy" && value[i]!="")
			{
				if(year==null)
				{
					var now = new Date();
					year = parseInt(now.getFullYear().substr(0,2)+value[i]);
				}
			}
			else if(fT[i]=="hh" && value[i]!="")
				hour = parseInt(value[i]);
			else if(fT[i]=="mm" && value[i]!="")
				min = parseInt(value[i]);
			else if(fT[i]=="ss" && value[i]!="")
				sec = parseInt(value[i]);				
		}
		var dt = new Date(year,month,day,hour,min,sec);
		//alert(year+","+month+","+day+","+hour+","+min+","+sec);
		//alert(dt);
		return dt;
	}
	catch(e)
	{
		//alert("Error Message -> "+e.message);
		return null;
	}	
}

function addToDate(_dt,n,_format)
{
	if(_format=="Y")
		_dt.setYear(_dt.getFullYear()+n);
	else if(_format=="M")
		_dt.setMonth(_dt.getMonth()+n);
	else if(_format=="D")
		_dt.setDate(_dt.getDate()+n);
	else if(_format=="H")
		_dt.setHours(_dt.getHours()+n);
	else if(_format=="MI")
		_dt.setMinutes(_dt.getMinutes()+n);
	else if(_format=="S")
		_dt.setSeconds(_dt.getSeconds()+n);
	return _dt;
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

function convertDateToStr(_date, _strFormat)
{
	try
	{
		var fT=["dd","MM","Mon","yyyy","yy","hh","mm","ss","WWW","Week","Month"];	// formatTokens 
		// dd date(01), MM month(01), Mon month(Jan), yyyy Year(2002), yy Year(02), hh Hours(23), 
		// mm Minutes(28), ss Seconds(34), WWW WeekDay(Mon), Week WeekDay(Monday), Month Month(January)
		
		var strDate = _strFormat;
		
		for(var i=0;i<fT.length;i++)
		{
			var beg=strDate.indexOf(fT[i]);
			if(beg!=-1)
			{
				var len=fT[i].length;
				var value = "";				
				if(fT[i]=="dd")
				{
					value = _date.getDate();
					if(value<=9)	value="0"+value;
				}
				else if(fT[i]=="MM")
				{
					value = _date.getMonth()+1;
					if(value<=9)	value="0"+value;
				}
				else if(fT[i]=="Mon")
					value = getShortMonthName(_date.getMonth());
				else if(fT[i]=="yyyy")
					value = _date.getFullYear();
				else if(fT[i]=="yy")
					value = _date.getFullYear().substr(2,2);
				else if(fT[i]=="hh")
					value = _date.getHours();
				else if(fT[i]=="mm")
					value = _date.getMinutes();
				else if(fT[i]=="ss")
					value = _date.getSeconds();
				else if(fT[i]=="WWW")
					value = weekdayShortNames[_date.getDay()];
				else if(fT[i]=="Week")
					value = weekdayFullNames[_date.getDay()];
				else if(fT[i]=="Month")
					value = monthFullNames[_date.getMonth()];
				//alert(strDate);
				strDate = strDate.substring(0,beg)+value+strDate.substr(beg+len); 
				//alert(strDate);
			}				
		}
		return strDate;
	}
	catch(e)
	{
		//alert("Error Message -> "+e.message);
		return null;
	}	
}

//Function for getting the month code from the month name
function getMonthCode(monthName)
{
	for(var i=0; i < monthShortNames.length ; i++)
		if(monthShortNames[i]==monthName)
			return i+1;
}

/***********************  END Date Conversion And Adding Date *************************************/



////Function for setting a date after adding some days to a date 
//into some other text box ,on click of a checkbox/button..etc.


function setDateInTextBoxAfterAddingDaysToDate(_date,_dateAsString,_dateToSetWhere,_daysToAdd)
{



	var referenceDate;

	var fT=["dd","MMM","yyyy"];	// formatTokens
	var value= new Array(3);
	
	
	// Date  As String is in Date Format dd/mmm/yyyy 
	var _fDAS="dd-MMM-yyyy";
	
	
	//first iterating through all the formatted tokens 
	//and now getting the index,length,finally value 
	// of each and every tokens from our String Date  
	
	for(var i=0;i<fT.length;i++)
	{
		var beg=_fDAS.indexOf(fT[i]);
		if(beg!=-1 && i!=1)
		{
			var len=fT[i].length;
			value[i]=parseInt(trimLeftZero(_dateAsString.substr(beg,len)));
			
		}
		else
		if(i==1)
		{		
		var len=fT[i].length;
		value[i]=	value[1]=getMonthCode(_dateAsString.substr(beg,len));
		}
		
	}
		
	referenceDate= new Date(value[2],value[1]-1,value[0]);	



//now adding  (_daysToAdd number of days)  to the referenceDate  


referenceDate.setDate(referenceDate.getDate()+_daysToAdd);

//alert("referenceDate2----"+referenceDate);

var nextDateYear=referenceDate.getFullYear();
var nextDateMonthCode=referenceDate.getMonth()+1;
var nextDateDay=referenceDate.getDate();


var nextDateMonth=monthShortNames[parseInt(nextDateMonthCode)-1];

if(parseInt(nextDateDay)<=9)
			nextDateDay="0"+nextDateDay;	
			
		

		
			
//now making the next date 

var newDate=nextDateDay+"-"+nextDateMonth+"-"+nextDateYear; /// dd-mmm-yyyy

//setting the next date

document.getElementsByName(_dateToSetWhere)[0].value=newDate;


}


//////////////////////////validate text area with dots//////////////////

function validateSpecialCharacter(char1,obj,name)
{
//alert("special")
	//alert(char);

	var valid=false
	
	if(validateStartingWithSpecialCharacter(char1,obj,name)
	&& validateTwoConsecutiveSpecialCharacter(char1,obj,name)
	&& validateEndingWithSpecialCharacter(char1,obj,name))
	{
		
		valid=true
	}

	return valid
}


/////////////////function to check if the text starts with dot/////////////////////////

function validateStartingWithSpecialCharacter(char1,obj,name)
{
	//alert(char);
	var valid=true
	var len=obj.value.length
	var val=obj.value
	
	var specialChar="' "+char1+" '"	
	
	if(parseInt(len)>0)
	{
		if((val.charAt(0))==char1)
		{
			alert(name+" cannot start with special character "+specialChar);
			valid=false
			return valid
		}
		
	}
	
	return valid
}

////////////////////////function to check for two consecutive dots////////////////

function validateTwoConsecutiveSpecialCharacter(char1,obj,name)
{
	var valid=true
	var len=obj.value.length
	var val=obj.value
	
	var specialChar="' "+char1+" '"	
		
	
	if(parseInt(len)>0)
	{
		for(i=0;i<len;i++)
		{
			if((val.charAt(i))==char1)
			{
				if((val.charAt(i))==(val.charAt(i+1)))
				{
				alert(name+" cannot have two consecutive special character "+specialChar)
				valid=false
				
				return valid
				}
			}
		}
	}
	
	return valid	
}

//////////////////////function to check if the text ends with dot/////////////////////

function validateEndingWithSpecialCharacter(char1,obj,name)
{
	var valid=true
	var len=obj.value.length
	var val=obj.value
	var specialChar="' "+char1+" '"	

		
	if(parseInt(len)>0)
	{
		if((val.charAt(len-1))==char1)
		{
			alert(name+" cannot end with special character "+specialChar)
			valid=false
			return valid
		}
		
	}
	
	return valid
} 

////get label of selected index of list
function getSelectedLabel(selectObject)
{
	//var selectObject=selectObj
	var value=selectObject.value
	var selectedIndex=selectObject.selectedIndex;
	var optionsArray=selectObject.options;
	var label=optionsArray[selectedIndex].text;
	//alert(label)
	return label
	//selectObject.remove(selectedIndex);
}

/////////////////////////////////////return no of days between two date string ///////////////////////////////////////
//////date format dd-mon-yyyy

/*function dateDifference(fromDate,toDate)
{
	var days=0;
	var aArray=fromDate.split("-");
	var aday=aArray[0];
	var amonth=aArray[1];
	var ayear=aArray[2];
	var adate=new Date(amonth +" "+ aday+" "+ayear);
	var bArray=toDate.split("-");
	var bday=bArray[0];
	var bmonth=bArray[1];
	var byear=bArray[2];
	var bdate=new Date(bmonth +" "+ bday+" "+byear);
	days=((bdate-adate)/86400000);

	return days;
}*/



/**************************** Movement in Lists and Other Function  ************************************/
// Moving Single in Lists fromSource to toTarget 
function moveSingle(_fromSource,_toTarget)
{
	var source = _fromSource;
	var target = _toTarget;

	var totalElement = source.length;
	var val = "";
	var txt = "";
	var targetlen = 0;

	for(var i=0;i<totalElement;i++)
	{
		if(source.options[i].selected)
		{
			val = source.options[i].value;
			txt = source.options[i].text;			
		
			targetlen = target.length;							
			target.length = (targetlen+1);				
			
			target.options[targetlen].value = val;
			target.options[targetlen].text  = txt;													
		}
	}
	deleteTransferred(target,source);
}

// Moving All in Lists fromSource to toTarget
function moveAll(_fromSource,_toTarget)
{
	var source = _fromSource;
	var target = _toTarget;

	var totalElement = source.length;
	var val = "";
	var txt = "";
	var targetlen = 0;

	for(var i=0;i<totalElement;i++)
	{
		val = source.options[i].value;
		txt = source.options[i].text;			
		
		targetlen = target.length;							
		target.length = (targetlen+1);				
			
		target.options[targetlen].value = val;
		target.options[targetlen].text  = txt;													
	}
	deleteTransferred(target,source);
}

// Deleting from Source that in Target
function deleteTransferred(source,target)
{	
	var tarlen = target.length;
	var srclen = source.length;

	var a1 = new Array(tarlen);
	var a2 = new Array(tarlen);
	var a3 = new Array(srclen);

	for(var i=0;i<tarlen;i++)
	{		
		a1[i]= target.options[i].value;
		a2[i]= target.options[i].text;	
	}
	for( i=0;i<srclen;i++)		
		a3[i]= source.options[i].value;

	target.length = 0;
	var counter = 0;
	var k = 0;
	var flag = 0;

	for(i=0;i<tarlen;i++)
	{		
		flag = 0;
		for(k=0;k<srclen;k++)
			if(a1[i]==a3[k])
				flag = 1;					
		if(flag==0)
		{
			target.length = (counter+1);
			target.options[counter].value = a1[i];
			target.options[counter].text  = a2[i]; 
			counter++;			
		}		
	}
}

/***********************  END Movement in Lists and Other Function  ************************************/
   
   
   
// ******************************************************************************
// *** Validation Functions

ValidationFunctions = function(){};


/**
 * Purpose : Just Calling a Functions that does Nothing
 * Calling On Event : onkeypress
 * Parameters : 	1.	event
 * Return Type : boolean
*/
ValidationFunctions.evtValidateNone = function(evnt)
{
	return true;
};


		/***** Numeric *****/
/**
 * Purpose : To ensure to enter a Numeric Value
 * Calling On Event : onkeypress
 * Parameters : 	1.	event
 * Return Type : boolean
 
 * Ascii Code allowed 0 - 48 To 9 - 57 , for . - 46, Minus - 45
*/
ValidationFunctions.evtValidateNumericOnly = function(evnt)
{
	try
	{
		var flag = true;
		var charCode;
		if(typeof evnt.charCode != 'undefined')	// Other
			charCode=evnt.charCode;
		else									// IE
			charCode=evnt.keyCode;
		if( charCode==0 || 
			(charCode==46) || 
			(charCode==45) || 
			(charCode>=48 && charCode<= 57))
			flag=true;
		else
			flag=false;
		if(!flag && typeof evnt.preventDefault != 'undefined')
		{
			evnt.preventDefault();
		}
		return flag;
	}
	catch(e)
	{
		alert("Error Message -> "+e.message);
	}
};

/**
 * Purpose : To ensure to enter a Numeric Value
 * Calling On Event : onkeypress
 * Parameters : 	1.	this	&	2.	event
 * Return Type : boolean
 
 * Ascii Code allowed 0 - 48 To 9 - 57 , for . - 46, Minus - 45
*/
/*function validateNumericOnly(obj,e)
{
	//alert("Char Code = "+e.charCode+"   Key Code = "+e.keyCode);
	var charCode;
	if(typeof e.charCode != 'undefined')	// Other
		charCode=e.charCode;
	else									// IE
		charCode=e.keyCode;
	//alert(charCode);
	var pattern=/\./;
	if( charCode==0 ||
		(!pattern.test(obj.value) && charCode==46) ||
		(obj.value.length==0 && charCode==45) || 
		(charCode>=48 && charCode<= 57) )
		return true;
	else
		return false;
}*/

/**
 * Purpose : To validate whether a given String a Numeric Value
 * Calling On Event : onchange, user-defined way
 * Parameters : 	1.	val/string to validate
 * Return Type : boolean
 
 * Ascii Code allowed 0 - 48 To 9 - 57 , for . - 46, Minus - 45
*/
ValidationFunctions.validateNumericValue = function (val)
{
	var pattern=/^-?\d*\.?\d*$/;
	return pattern.test(val);
};

/**
 * Purpose : To validate whether a given String a Numeric Value
 * Calling On Event : onchange, user-defined way
 * Parameters : 	1.	val/string to validate
 * Return Type : boolean
 
 * Ascii Code allowed 0 - 48 To 9 - 57 , for . - 46, Minus - 45
*/
/*function validateNumericValue(val)
{
	var pattern=/^-?\d*\.?\d*$/;
	return pattern.test(val);
}*/



		/***** Positive Numeric *****/
/**
 * Purpose : To ensure to enter a Positive Numeric Value
 * Calling On Event : onkeypress
 * Parameters : 	1.	event
 * Return Type : boolean
 
 * Ascii Code allowed 0 - 48 To 9 - 57 , for . - 46
*/
ValidationFunctions.evtValidatePositiveNumericOnly = function(evnt)
{
	try
	{
		var flag = true;
		var charCode;
		if(typeof evnt.charCode != 'undefined')	// Other
			charCode=evnt.charCode;
		else									// IE
			charCode=evnt.keyCode;
		if( charCode==0 || 
			(charCode==46) || 
			(charCode>=48 && charCode<= 57))
			flag=true;
		else
			flag=false;
		if(!flag && typeof evnt.preventDefault != 'undefined')
		{
			evnt.preventDefault();
		}
		return flag;
	}
	catch(e)
	{
		alert("Error Message -> "+e.message);
	}
};

/**
 * Purpose : To ensure to enter a Positive Numeric Value
 * Calling On Event : onkeypress
 * Parameters : 	1.	this	&	2.	event
 * Return Type : boolean
 
 * Ascii Code allowed 0 - 48 To 9 - 57 , for . - 46
*/
/*function validatePositiveNumericOnly(obj,e)
{
	//alert("Char Code = "+e.charCode+"   Key Code = "+e.keyCode);
	var charCode;
	if(typeof e.charCode != 'undefined')	// Other
		charCode=e.charCode;
	else									// IE
		charCode=e.keyCode;
	//alert(charCode);
	var pattern=/\./;
	if( charCode==0 ||
		(!pattern.test(obj.value) && charCode==46) || 
		(charCode>=48 && charCode<= 57) )
		return true;
	else
		return false;
}*/

/**
 * Purpose : To validate whether a given String a Positive Numeric Value
 * Calling On Event : onchange, user-defined way
 * Parameters : 	1.	val/string to validate
 * Return Type : boolean
 
 * Ascii Code allowed 0 - 48 To 9 - 57 , for . - 46
*/
ValidationFunctions.validatePositiveNumericValue = function (val)
{
	var pattern=/^\d*\.?\d*$/;
	return pattern.test(val);
};

/**
 * Purpose : To validate whether a given String a Positive Numeric Value
 * Calling On Event : onchange, user-defined way
 * Parameters : 	1.	val/string to validate
 * Return Type : boolean
 
 * Ascii Code allowed 0 - 48 To 9 - 57 , for . - 46, Minus - 45
*/
/*function validatePositiveNumericValue(val)
{
	var pattern=/^\d*\.?\d*$/;
	return pattern.test(val);
}*/



		/***** Integer *****/
/**
 * Purpose : To ensure to enter a Integer Value
 * Calling On Event : onkeypress
 * Parameters : 	1.	event
 * Return Type : boolean
 
 * Ascii Code allowed 0 - 48 To 9 - 57 , Minus - 45 
*/
ValidationFunctions.evtValidateIntegerOnly = function(evnt)
{
	try
	{
		var flag = true;
		var charCode;
		if(typeof evnt.charCode != 'undefined')	// Other
			charCode=evnt.charCode;
		else									// IE
			charCode=evnt.keyCode;
		if( charCode==0 || 
			(charCode==45) || 
			(charCode >= 48 && charCode <= 57) )
			flag=true;
		else
			flag=false;
		if(!flag && typeof evnt.preventDefault != 'undefined')
		{
			evnt.preventDefault();
		}
		return flag;
	}
	catch(e)
	{
		alert("Error Message -> "+e.message);
	}
};

/**
 * Purpose : To ensure to enter a Integer Value
 * Calling On Event : onkeypress
 * Parameters : 	1.	this	&	2.	event
 * Return Type : boolean
 
 * Ascii Code allowed 0 - 48 To 9 - 57 , Minus - 45 
*/
/*function validateIntegerOnly(obj,e)
{
	//alert("Char Code = "+e.charCode+"   Key Code = "+e.keyCode);
	var charCode;
	if(typeof e.charCode != 'undefined')	// Other
		charCode=e.charCode;
	else									// IE
		charCode=e.keyCode;
	//alert(charCode);
	if( charCode==0 || 
		(obj.value.length==0 && charCode==45) || 
		(charCode >= 48 && charCode <= 57) )
		return true;
	else
		return false;
}*/

/**
 * Purpose : To validate whether a given String a Integer Value
 * Calling On Event : onchange, user-defined way
 * Parameters : 	1.	val/string to validate
 * Return Type : boolean
 
 * Ascii Code allowed 0 - 48 To 9 - 57 , Minus - 45
*/
ValidationFunctions.validateIntegerValue = function(val)
{
	var pattern=/^-?[0-9]*$/;
	return pattern.test(val);
};

/**
 * Purpose : To validate whether a given String a Integer Value
 * Calling On Event : onchange, user-defined way
 * Parameters : 	1.	val/string to validate
 * Return Type : boolean
 
 * Ascii Code allowed 0 - 48 To 9 - 57 , Minus - 45
*/
/*function validateIntegerValue(val)
{
	var pattern=/^-?[0-9]*$/;
	return pattern.test(val);
}*/



		/***** Positive Integer *****/
/**
 * Purpose : To ensure to enter a Positive Integer Value
 * Calling On Event : onkeypress
 * Parameters : 	1.	event
 * Return Type : boolean
 
 * Ascii Code allowed 0 - 48 To 9 - 57 
*/
ValidationFunctions.evtValidatePositiveIntegerOnly = function(evnt)
{
	try
	{
		var flag = true;
		var charCode;
		if(typeof evnt.charCode != 'undefined')	// Other
			charCode=evnt.charCode;
		else									// IE
			charCode=evnt.keyCode;
		if( charCode==0 || 
			( charCode>=48 && charCode<=57 ) )
			flag=true;
		else
			flag=false;
		if(!flag && typeof evnt.preventDefault != 'undefined')
		{
			evnt.preventDefault();
		}
		return flag;
	}
	catch(e)
	{
		alert("Error Message -> "+e.message);
	}
};

/**
 * Purpose : To ensure to enter a Positive Integer Value
 * Calling On Event : onkeypress
 * Parameters : 	1.	this	&	2.	event
 * Return Type : boolean
 
 * Ascii Code allowed 0 - 48 To 9 - 57 
*/
/*function validatePositiveIntegerOnly(obj,e)
{
	//alert("Char Code = "+e.charCode+"   Key Code = "+e.keyCode);
	var charCode;
	if(typeof e.charCode != 'undefined')	// Other
		charCode=e.charCode;
	else									// IE
		charCode=e.keyCode;
	//alert(charCode);
	if( charCode==0 || 
		( charCode>=48 && charCode<=57 ) )
		return true;
	else
		return false;
}*/

/**
 * Purpose : To validate whether a given String a Positive Integer Value
 * Calling On Event : onchange, user-defined way
 * Parameters : 	1.	val/string to validate
 * Return Type : boolean
 
 * Ascii Code allowed 0 - 48 To 9 - 57
*/
ValidationFunctions.validatePositiveIntegerValue = function(val)
{
	var pattern=/^[0-9]*$/;
	return pattern.test(val);
};

/**
 * Purpose : To validate whether a given String a Positive Integer Value
 * Calling On Event : onchange, user-defined way
 * Parameters : 	1.	val/string to validate
 * Return Type : boolean
 
 * Ascii Code allowed 0 - 48 To 9 - 57
*/
/*function validatePositiveIntegerValue(val)
{
	var pattern=/^[0-9]*$/;
	return pattern.test(val);
}*/


		/***** Alphabetic *****/
/**
 * Purpose : To ensure to enter a Alphabetic Value
 * Calling On Event : onkeypress
 * Parameters : 	1.	event
 * Return Type : boolean
 
 * Ascii Code allowed A-65, Z-90, a-97, z-122, Space- 32, . -46
*/
ValidationFunctions.evtValidateAlphaOnly = function(evnt)
{
	try
	{
		var flag = true;
		var charCode;
		if(typeof evnt.charCode != 'undefined')	// Other
			charCode=evnt.charCode;
		else									// IE
			charCode=evnt.keyCode;
		if( charCode==0 || 
			(charCode==32) || 
			(charCode==46) || 
			(charCode>=65 && charCode<=90) || 
			(charCode>=97 && charCode<=122) )
			flag=true;
		else
			flag=false;
		if(!flag && typeof evnt.preventDefault != 'undefined')
		{
			evnt.preventDefault();
		}
		return flag;
	}
	catch(e)
	{
		alert("Error Message -> "+e.message);
	}
};

/**
 * Purpose : To ensure to enter a Alphabetic Value
 * Calling On Event : onkeypress
 * Parameters : 	1.	this	&	2.	event
 * Return Type : boolean
 
 * Ascii Code allowed A-65, Z-90, a-97, z-122, Space- 32, . -46
*/
/*function validateAlphaOnly(obj,e)
{
	//alert("Char Code = "+e.charCode+"   Key Code = "+e.keyCode);
	var charCode;
	if(typeof e.charCode != 'undefined')	// Other
		charCode=e.charCode;
	else									// IE
		charCode=e.keyCode;
	//alert(charCode);
	if( charCode==0 || 
		charCode==32 || 
		charCode==46 || 
		(charCode>=65 && charCode<=90) || 
		(charCode>=97 && charCode<=122) )
		return true;
	else
		return false;
}*/

/**
 * Purpose : To validate whether a given String a Alphabetic Value
 * Calling On Event : onchange, user-defined way
 * Parameters : 	1.	val/string to validate
 * Return Type : boolean
 
 * Ascii Code allowed A-65, Z-90, a-97, z-122, Space- 32, . -46
*/
ValidationFunctions.validateAlphaValue = function(val)
{
	var pattern=/^[a-zA-Z .]*$/;
	return pattern.test(val);
};

/**
 * Purpose : To validate whether a given String a Alphabetic Value
 * Calling On Event : onchange, user-defined way
 * Parameters : 	1.	val/string to validate
 * Return Type : boolean
 
 * Ascii Code allowed A-65, Z-90, a-97, z-122, Space- 32, . -46
*/
/*function validateAlphaValue(val)
{
	var pattern=/^[a-zA-Z .]*$/;
	return pattern.test(val);
}*/



		/***** Alpha-Numeric *****/
/**
 * Purpose : To ensure to enter a Alphanumeric Value
 * Calling On Event : onkeypress
 * Parameters : 	1.	event
 * Return Type : boolean
 
 * Ascii Code allowed 0 - 48 To 9 - 57,A-65, Z-90, a-97, z-122, Space- 32, comma(,)-44 , .-46 
*/
ValidationFunctions.evtValidateAlphaNumOnly = function(evnt)
{
	try
	{
		var flag = true;
		var charCode;
		if(typeof evnt.charCode != 'undefined')	// Other
			charCode=evnt.charCode;
		else									// IE
			charCode=evnt.keyCode;
		if( charCode==0 || 
			(charCode==44) || 
			(charCode==32) || 
			(charCode==46) || 
			(charCode>=48 && charCode<=57) || 
			(charCode>=65 && charCode<=90) || 
			(charCode>=97 && charCode<=122) )
			flag=true;
		else
			flag=false;
		if(!flag && typeof evnt.preventDefault != 'undefined')
		{
			evnt.preventDefault();
		}
		return flag;
	}
	catch(e)
	{
		alert("Error Message -> "+e.message);
	}
};

/**
 * Purpose : To ensure to enter a Alphanumeric Value
 * Calling On Event : onkeypress
 * Parameters : 	1.	this	&	2.	event
 * Return Type : boolean
 
 * Ascii Code allowed 0 - 48 To 9 - 57,A-65, Z-90, a-97, z-122, Space- 32, comma(,)-44 , .-46 
*/
/*function validateAlphaNumOnly(obj,e)
{
	//alert("Char Code = "+e.charCode+"   Key Code = "+e.keyCode);
	var charCode;
	if(typeof e.charCode != 'undefined')	// Other
		charCode=e.charCode;
	else									// IE
		charCode=e.keyCode;
	//alert(charCode);
	if( charCode==0 || 
		charCode==44 || 
		charCode==32 || 
		charCode==46 || 
		(charCode>=48 && charCode<=57) || 
		(charCode>=65 && charCode<=90) || 
		(charCode>=97 && charCode<=122) )
		return true;
	else
		return false;
}*/

/**
 * Purpose : To validate whether a given String a Alphanumeric Value
 * Calling On Event : onchange, user-defined way
 * Parameters : 	1.	val/string to validate
 * Return Type : boolean
 
 * Ascii Code allowed 0 - 48 To 9 - 57,A-65, Z-90, a-97, z-122, Space- 32, for , - 44
*/
ValidationFunctions.validateAlphaNumValue = function(val)
{
	var pattern=/^[a-zA-Z, .0-9]*$/;
	return pattern.test(val);
};

/**
 * Purpose : To validate whether a given String a Alphanumeric Value
 * Calling On Event : onchange, user-defined way
 * Parameters : 	1.	val/string to validate
 * Return Type : boolean
 
 * Ascii Code allowed 0 - 48 To 9 - 57,A-65, Z-90, a-97, z-122, Space- 32, for , - 44
*/
/*function validateAlphaNumValue(val)
{
	var pattern=/^[a-zA-Z, .0-9]*$/;
	return pattern.test(val);
}*/



		/***** Not Some Special Characters *****/
/**
 * Purpose : To ensure that entered Value don't have any Special Character
 * Calling On Event : onkeypress
 * Parameters : 	1.	event
 * Return Type : boolean
 
 * Restrict to enter ~!@#$%^&
 * ~ - 126, ! - 33, @ - 64, # - 35, $ - 36, % - 37,^ - 94, & - 38 
*/
ValidationFunctions.evtNotSpecChar = function(evnt) 
{
	try
	{
		var flag = true;
		var charCode;
		if(typeof evnt.charCode != 'undefined')	// Other
			charCode=evnt.charCode;
		else									// IE
			charCode=evnt.keyCode;
		if( charCode==126 || charCode==33 || 
			charCode==64 || charCode==35 || 
			//charCode==38 || charCode==37 || 
			charCode==94 || charCode==36)
			flag=false;
		else
			flag=true;
		if(!flag && typeof evnt.preventDefault != 'undefined')
		{
			evnt.preventDefault();
		}
		return flag;
	}
	catch(e)
	{
		alert("Error Message -> "+e.message);
	}
};

/**
 * Purpose : To ensure that entered Value don't have any Special Character
 * Calling On Event : onkeypress
 * Parameters : 	1.	this	&	2.	event
 * Return Type : boolean
 
 * Restrict to enter ~!@#$%^&
 * ~ - 126, ! - 33, @ - 64, # - 35, $ - 36, % - 37,^ - 94, & - 38 
*/
/*function notSpecChar(obj,e) 
{
	//alert("Char Code = "+e.charCode+"   Key Code = "+e.keyCode);
	var charCode;
	if(typeof e.charCode != 'undefined')	// Other
		charCode=e.charCode;
	else									// IE
		charCode=e.keyCode;
	//alert(charCode);
	if( charCode==126 || charCode==33 || 
		charCode==64 || charCode==35 || 
		charCode==36 || charCode==37 || 
		charCode==94 || charCode== 38 )
		return false;
	else
		return true;
}*/

/**
 * Purpose : To validate whether a given String don't have any Special Character
 * Calling On Event : onchange, user-defined way
 * Parameters : 	1.	val/string to validate
 * Return Type : boolean
 
 * Restrict to enter ~!@#$%^&
 * ~ - 126, ! - 33, @ - 64, # - 35, $ - 36, % - 37,^ - 94, & - 38 
*/
ValidationFunctions.notSpecCharValue = function(val)
{
	var pattern=/^[^~!@#$%^&]*$/;
	return pattern.test(val);
};

/**
 * Purpose : To validate whether a given String don't have any Special Character
 * Calling On Event : onchange, user-defined way
 * Parameters : 	1.	val/string to validate
 * Return Type : boolean
 
 * Restrict to enter ~!@#$%^&
 * ~ - 126, ! - 33, @ - 64, # - 35, $ - 36, % - 37,^ - 94, & - 38 
*/
/*function notSpecCharValue(val)
{
	var pattern=/^[^~!@#$%^&]*$/;
	return pattern.test(val);
}*/



		/***** Not Some Special Characters for Regular Expression *****/
/**
 * Purpose : To ensure that entered Value don't have any Special Character for Regular Expression 
 * Calling On Event : onkeypress
 * Parameters : 	1.	event
 * Return Type : boolean
 
 * // Restrict to enter ~!@#$%^& in all fileds  allow ^ and  $ in Regular Expression only
 * ~ - 126, ! - 33, @ - 64, # - 35, % - 37, & - 38 
*/
ValidationFunctions.evtNotSpecCharRegEx = function(evnt) 
{
	try
	{
		var flag = true;
		var charCode;
		if(typeof evnt.charCode != 'undefined')	// Other
			charCode=evnt.charCode;
		else									// IE
			charCode=evnt.keyCode;
		if( charCode==126 || charCode==33 || 
			charCode==64 || charCode==35 || 
			charCode==37 || charCode== 38)
			flag=false;
		else
			flag=true;
		if(!flag && typeof evnt.preventDefault != 'undefined')
		{
			evnt.preventDefault();
		}
		return flag;
	}
	catch(e)
	{
		alert("Error Message -> "+e.message);
	}
};

/**
 * Purpose : To ensure that entered Value don't have any Special Character for Regular Expression
 * Calling On Event : onkeypress
 * Parameters : 	1.	this	&	2.	event
 * Return Type : boolean
 
 * Restrict to enter ~!@#%&
 * ~ - 126, ! - 33, @ - 64, # - 35, % - 37, & - 38 
*/
/*function notSpecCharRegEx(obj,e)
{
	try
	{
		//alert("Char Code = "+e.charCode+"   Key Code = "+e.keyCode);
		var charCode;
		if(typeof e.charCode != 'undefined')	// Other
			charCode=e.charCode;
		else									// IE
			charCode=e.keyCode;
		//alert(charCode);
		if( charCode==126 || charCode==33 || 
			charCode==64 || charCode==35 || 
			charCode==37 || charCode== 38 )
			return false;
		else
			return true;
	}
	catch(e)
	{
		alert("Error Message -> "+e.message);
	}
}*/

/**
 * Purpose : To validate whether a given String don't have any Special Character
 * Calling On Event : onchange, user-defined way
 * Parameters : 	1.	val/string to validate
 * Return Type : boolean
 
 * Restrict to enter ~!@#%&
 * ~ - 126, ! - 33, @ - 64, # - 35, % - 37, & - 38 
*/
ValidationFunctions.notSpecCharRegEx = function(val)
{
	var pattern=/^[^~!@#%&]*$/;
	return pattern.test(val);
};

/**
 * Purpose : To validate whether a given String don't have any Special Character for Regular Expression
 * Calling On Event : onchange, user-defined way
 * Parameters : 	1.	val/string to validate
 * Return Type : boolean
 
 * Restrict to enter ~!@#%&
 * ~ - 126, ! - 33, @ - 64, # - 35, % - 37, & - 38 
*/
/*function notSpecCharRegEx(val)
{
	var pattern=/^[^~!@#%&]*$/;
	return pattern.test(val);
}*/



		/***** Allow Some Characters *****/
/**
 * Purpose : To ensure that entered Value have given Character
 * Calling On Event : onkeypress
 * Parameters : 	1.	event 
 * Return Type : boolean
*/
ValidationFunctions.evtValidateWithCharacters = function(evnt) 
{
	try
	{
		var chars = "";//window.Pragyas_ALLOW_CHARS;//---------
		var flag = true;
		var charCode;
		if(typeof evnt.charCode != 'undefined')	// Other
			charCode=evnt.charCode;
		else									// IE
			charCode=evnt.keyCode;
		var char1 = String.fromCharCode(charCode);
		if ( chars.indexOf(char1) > -1)
			flag=true;
		else
			flag=false;
		if(!flag && typeof evnt.preventDefault != 'undefined')
		{
			evnt.preventDefault();
		}
		return flag;
	}
	catch(e)
	{
		alert("Error Message -> "+e.message);
	}
};

/**
 * Purpose : To ensure that entered Value have given Character
 * Calling On Event : onkeypress
 * Parameters : 	1.	this	2.	event	3. string of characters allowed 
 * Return Type : boolean
*/
/*function validateWithCharacters(obj,e,chars) 
{
	//alert("Char Code = "+e.charCode+"   Key Code = "+e.keyCode);
	var charCode;
	if(typeof e.charCode != 'undefined')	// Other
		charCode=e.charCode;
	else									// IE
		charCode=e.keyCode;
	//alert(charCode);
	var char = String.fromCharCode(charCode);
	if ( chars.indexOf(char) > -1)
		return true;
	else
		return false;
}*/


		/***** Formula *****/
/**
 * Purpose : To ensure that entered Value is suitable for a formula
 * Calling On Event : onkeypress
 * Parameters : 	1.	event
 * Return Type : boolean
 
 * Ascii Code 0 - 48 To 9 - 57, Space- 32, . 46 , + 43 , - 45 , ( 40 , ) 41 , * 42 , / 47 , % 37
 * U-85, W-87, X-88, Y-89, Z-90
 *  
*/
ValidationFunctions.evtValidateFormulaOnly = function(evnt)
{ 
	try
	{
		var flag = true;
		var charCode;
		if(typeof evnt.charCode != 'undefined')	// Other
			charCode=evnt.charCode;
		else									// IE
			charCode=evnt.keyCode;
		if( charCode==0 || 
			charCode==32 || charCode==37 || 
			charCode==40 || charCode==41 || 
			charCode==42 || charCode==43 || 
			charCode==45 ||	charCode==46 || 
			charCode==47 || 
			charCode==85 || charCode==87 || charCode==88 || charCode==89 || charCode==90 || 
			(charCode>=48 && charCode<=57))
			flag=true;
		else
			flag=false;
		if(!flag && typeof evnt.preventDefault != 'undefined')
		{
			evnt.preventDefault();
		}
		return flag;
	}
	catch(e)
	{
		alert("Error Message -> "+e.message);
	}
};

		/***** Corresponding Formula *****/
/**
 * Purpose : To ensure that entered Value is suitable for a formula
 * Calling On Event : onkeypress
 * Parameters : 	1.	event
 * Return Type : boolean
 
 * Ascii Code 0 - 48 To 9 - 57, Space- 32, . 46 , + 43 , - 45 , ( 40 , ) 41 , * 42 , / 47 , % 37
 * V-86
 *  
*/
ValidationFunctions.evtValidateCorrespondingFormulaOnly = function(evnt)
{ 
	try
	{
		var flag = true;
		var charCode;
		if(typeof evnt.charCode != 'undefined')	// Other
			charCode=evnt.charCode;
		else									// IE
			charCode=evnt.keyCode;
		if( charCode==0 || 
			charCode==32 || charCode==37 || 
			charCode==40 || charCode==41 || 
			charCode==42 || charCode==43 || 
			charCode==45 ||	charCode==46 || 
			charCode==47 || charCode==86 || 
			(charCode>=48 && charCode<=57))
			flag=true;
		else
			flag=false;
		if(!flag && typeof evnt.preventDefault != 'undefined')
		{
			evnt.preventDefault();
		}
		return flag;
	}
	catch(e)
	{
		alert("Error Message -> "+e.message);
	}
};


		/***** Date Format *****/
/**
 * Purpose : To ensure that entered Value is suitable for a Date in dd-mm-yyyy format
 * Calling On Event : onkeypress
 * Parameters : 	1.	event
 * Return Type : boolean
 
 * Ascii Code 0 - 48 To 9 - 57 , for - 45 
*/
ValidationFunctions.evtValidateDateFormat = function(evnt)
{
	try
	{
		var flag = true;
		var charCode;
		if(typeof evnt.charCode != 'undefined')	// Other
			charCode=evnt.charCode;
		else									// IE
			charCode=evnt.keyCode;
		if( charCode==0 || 
			//(charCode==45) || 
			(charCode>=48 && charCode<= 57))
			flag=true;
		else
			flag=false;
		if(!flag && typeof evnt.preventDefault != 'undefined')
		{
			evnt.preventDefault();
		}
		return flag;
	}
	catch(e)
	{
		alert("Error Message -> "+e.message);
	}
};

ValidationFunctions.validateDateFormatValue = function (val)
{// dd-mm-yyyy
	//alert(val);
	var pattern=/^\d{2}-\d{2}-\d{4}$/;
	return pattern.test(val);
};


		/***** Time Format *****/
/**
 * Purpose : To ensure that entered Value is suitable for a Time in hh:mm format
 * Calling On Event : onkeypress
 * Parameters : 	1.	event
 * Return Type : boolean
 
 * Ascii Code 0 - 48 To 9 - 57 , for : 58 
*/
ValidationFunctions.evtValidateTimeFormat = function(evnt)
{
	try
	{
		var flag = true;
		var charCode;
		if(typeof evnt.charCode != 'undefined')	// Other
			charCode=evnt.charCode;
		else									// IE
			charCode=evnt.keyCode;
		if(	charCode==0 || 
			//(charCode==58) || 
			(charCode>=48 && charCode<= 57))
			flag=true;
		else
			flag=false;
		if(!flag && typeof evnt.preventDefault != 'undefined')
		{
			evnt.preventDefault();
		}
		return flag;
	}
	catch(e)
	{
		alert("Error Message -> "+e.message);
	}
};

ValidationFunctions.validateTimeFormatValue = function (val)
{// hh:mm
	//alert(val);
	var pattern=/^\d{2}:\d{2}$/;
	return pattern.test(val);
};


	/****** Validate Regular Expression *****/
ValidationFunctions.validateRegularExpression = function(_val, _re)
{
	if(_re!="")
	{ 
		var rep=new RegExp(_re,"");
		return rep.test(_val);
	}
	return true;
};

	//****** Validate Corresponding Value Related to Function given
ValidationFunctions.validateValueForFunction = function(_value, _fun)
{
	if( /None/i.test(_fun) )
		return true;
	var valFunc =_fun.replace("Only","Value");
	return ValidationFunctions[valFunc](_value);
};


	//****** Validate Minimum Legth of the Field
ValidationFunctions.validateMinLength = function(_value, _minLen)
{
	alert(typeof _minLen);
	if(_value && _value!="" && _minLen && typeof _minLen=='integer' && _value.length>=_minLen)
		return true;
	else
		return false;
};

function validateAlphabetsSpaceDotAndBracket(e,obj)
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
	//alert(keychar)
	//alert("indexof="+('abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ').indexOf(keychar))
	// control keys
	if ((key==null) || (key==0) || (key==8) ||
		(key==9) || (key==13) || (key==27))
	   return true;
	else if((getCursorIdex(obj)>0) && (key==32))
		return true
	// alphas and space
	
	else if ((("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ() .").indexOf(keychar) > -1))
	   return true;
	else
	   return false;
}


function validateAlphaNumericandSpaceOnly(e,obj)
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
		(key==9) || (key==13) || (key==27))
	   return true;
	else if((getCursorIdex(obj)>0) && (key==32))
		return true

	// alphas and numeric
	else if ((("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ ").indexOf(keychar) > -1) || (key==32))
	   return true;
		   
	   else if ((("0123456789").indexOf(keychar) > -1))
	   return true;
	   
	   
	   
	else
	   return false;
}


// *** End Validation Functions
// ******************************************************************************
