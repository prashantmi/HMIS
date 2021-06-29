
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
	//adt_create_loading_msg();//loading_msg
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
function adt_create_loading_msg(){var qh=80;var qw=300;var dh=0;var dw=0;if(window.innerHeight){dh=window.innerHeight;dw=window.innerWidth;}else {dh=document.documentElement.clientHeight;dw=document.documentElement.clientWidth;}var tpos=parseInt((dh-qh)/2);var lpos=parseInt((dw-qw)/2);var wt='<div id="qmvi_loading_div" style="top:'+tpos+'px;left:'+lpos+'px;height:'+qh+'px;width:'+qw+'px;position:absolute;text-align:center;font-family:Arial;text-decoration:none;font-weight:normal;font-size:13px;color:#00224A;background-color:#ffffff;border-width:1px;border-color:#828EA2;border-style:solid;">';wt+='<div style="padding:20px;"><img src="../../hisglobal/images/loading.gif" width=16 height=16><br><br><font size="2" weight="bold">Fetching Details.Please Wait!</font></div>';wt+='</div>';document.getElementById("normalMsg").innerHTML=wt;}
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
	
	
	function adt_create_loading_msg(){var qh=80;var qw=300;var dh=0;var dw=0;if(window.innerHeight){dh=window.innerHeight;dw=window.innerWidth;}else {dh=document.documentElement.clientHeight;dw=document.documentElement.clientWidth;}var tpos=parseInt((dh-qh)/2);var lpos=parseInt((dw-qw)/2);var wt='<div id="qmvi_loading_div" style="top:'+tpos+'px;left:'+lpos+'px;height:'+qh+'px;width:'+qw+'px;position:absolute;text-align:center;font-family:Arial;text-decoration:none;font-weight:normal;font-size:13px;color:#00224A;background-color:#ffffff;border-width:1px;border-color:#828EA2;border-style:solid;">';wt+='<div style="padding:20px;"><img src="../../hisglobal/images/loading.gif" width=16 height=16><br><br><font size="2" weight="bold">Fetching Details.Please Wait!</font></div>';wt+='</div>';document.getElementById("normalMsg").innerHTML=wt;}
	
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
		
		document.getElementById('divDocTitle').style.display="";
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
		document.getElementById('divDocTitle').style.display="none";
		document.getElementById('divDocName').style.display="none";
 	
 } 
 }
  
  
 function Isreferred(elem)
 {
 	if(elem.checked)
 	{
		document.getElementById('divRefDtlId').style.display="block";
// 	 	document.getElementById('divReferred').style.display="";
		document.getElementsByName("patRefGnctdHospitalCode")[0].value="-1";
		document.getElementsByName("patRefHospitalName")[0].value="";
 	if(document.getElementsByName('referringInstType')[0].checked){
        //document.getElementsByName('referringInstType')[0].checked="true";    
		document.getElementById('divRefHosname').style.display="none";
		document.getElementById('divRefHosCode').style.display="block";
		document.getElementById('divReferredInstitute').style.display="block";
		document.getElementById('divReferred').style.display="block";
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
		
		document.getElementById('divDocTitle').style.display="";
		document.getElementById('divDocName').style.display="";
 	
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
		document.getElementById('divDocTitle').style.display="none";
		document.getElementById('divDocName').style.display="none";
 	
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
 


// Changed on 30-Oct-2009
// to select the age or dob
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
	// alert("function 1 ");
	//if(document.getElementById("divEmpDob")){
	//	 	if(document.getElementById("divEmpDob").style.display==""){
		 	// alert("function 1 ");
	//	 	  return;
	//	 	}
	 ///	}
	 if(document.getElementsByName("isActualDob")[0].checked){
	  //alert("function if ");
	//document.getElementsByName("patDOB")[0].value="";
	 document.getElementById("divAge").style.display="";
	  document.getElementById("divDob").style.display="none";
	 // document.getElementsByName("patDOB")[0].value="";
	  }
	else {
//	alert("else checked");
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
 // alert("flag 123456 "+flag+" mode 123456 "+mode);
 // alert("cr no  "+document.getElementsByName("patCrNo")[0]);
  //alert("submitFormOnValidate")
 	if(flag)
	{
	// alert("inside if")
		submitForm(mode);
	}
	else{
// 	alert("elesee")
		return false;
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
				  //document.getElementsByName("patMaritalStatusCode")[0].disabled=false;
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
		alert("Please Select The "+str)
		valid=false
		obj.focus()
	}
	return valid
}

 function ValidateDepartment(field) {
         var isValid = true;
        
//alert('inside ValidateDepartment of registration.js....');
      value2=document.getElementsByName("departmentCode")[0].value; 
    //  alert(value2);
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
 // alert(isValid);               
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
		if(parseInt(age)<parseInt(seniorCitizenAge) || ageUnit!="Y")
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
		var deptCode=document.getElementsByName("departmentUnitCode")[0].value
		var deptCombo=document.getElementsByName("departmentUnitCode")[0];
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

function printCard(){

	document.getElementsByName("hmode")[0].value="PRINTLASTOPDCARD"
	document.forms[0].submit();

}

/////////////////////////////