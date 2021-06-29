

function checkReferDepartment(obj)
{

	if(document.getElementsByName('patRefGnctdHospitalDept')[0].value=='0')
	{
		document.getElementsByName('patRefHospitalDeptOther')[0].disabled=false
		document.getElementsByName('patRefHospitalDeptOther')[0].focus()
		}
	else
	{
	
	document.getElementsByName('patRefHospitalDeptOther')[0].disabled=true
	
	document.getElementsByName('patRefHospitalDeptOther')[0].value=""
	document.getElementsByName('patRefGnctdHospitalDeptUnit')[0].focus();
	
	
	}
}

function broughtBy(obj)
{
// alert(document.getElementsByName("isRelative")[0].value)
	if(obj.checked==true)
	{
	// alert("inside brought by if")
		document.getElementById("broughtById").style.display="";
	}
	else
	{
	// 	alert("inside brought by else")
		document.getElementById("broughtById").style.display="none";
		document.getElementsByName("isRelative")[0].value="-1"
		document.getElementsByName("broughtByRelationCode")[0].value="-1"
		document.getElementsByName("broughtByName")[0].value=""
		document.getElementsByName("broughtByLocation")[0].value=""
		document.getElementsByName("constableDesig")[0].value=""
		document.getElementsByName("constableBadgeNo")[0].value=""
		document.getElementsByName("broughtByPhone")[0].value=""
		document.getElementsByName("broughtByAddress")[0].value=""
		enableRelation(document.getElementsByName("isRelative")[0])
	}
}

function onBroughtDead(obj)
{
	if( (obj.checked==true) || (document.getElementsByName("isUnknown")[0].checked==true) )
	{
		//document.getElementsByName("isMLC")[0].checked=true;
		//document.getElementsByName("isMLC")[0].disabled=true;
	}
	else
	{
		//document.getElementsByName("isMLC")[0].checked=false;
		//document.getElementsByName("isMLC")[0].disabled=false;
	}
	
	if(obj.checked==true){
		document.getElementsByName("isBroughtBy")[0].checked=true;
		broughtBy(document.getElementsByName("isBroughtBy")[0]);
	}
	else{
		document.getElementsByName("isBroughtBy")[0].checked=false;
		broughtBy(document.getElementsByName("isBroughtBy")[0]);
	}
}


function enableRelation(obj)
{	
	 
	
	if(obj.value=="1")
	{
		document.getElementById("nameBroughtBy").innerHTML="<font color='#FF0000' size='2' >*</font><font color='#000000' size='2' face='Verdana, Arial, Helvetica, sans-serif'>  Name";
		document.getElementsByName("broughtByRelationCode")[0].disabled=false;
		document.getElementById("phoneBroughtByDiv").innerHTML=" <font color='#000000' size='2' face='Verdana, Arial, Helvetica, sans-serif'> Phone No.";			
	}
	else 
	{
		document.getElementById("nameBroughtBy").innerHTML="<font color='#FF0000' size='2' >*</font><font color='#000000' size='2' face='Verdana, Arial, Helvetica, sans-serif'>  Name";
		document.getElementsByName("broughtByRelationCode")[0].disabled=true;
		document.getElementById("phoneBroughtByDiv").innerHTML="<font color='#000000' size='2' face='Verdana, Arial, Helvetica, sans-serif'> Phone No.";			
		
	}
	if(obj.value=="2"){
		document.getElementById("nameBroughtBy").innerHTML="<font color='#FF0000' size='2' >*</font> <font color='#000000' size='2' face='Verdana, Arial, Helvetica, sans-serif'> Name";
		document.getElementById("policeDetailDiv").style.display="block";
		document.getElementById("phoneBroughtByDiv").innerHTML="<font color='#000000' size='2' face='Verdana, Arial, Helvetica, sans-serif'> Phone No.";	
	}
	else{
		document.getElementById("nameBroughtBy").innerHTML="<font color='#FF0000' size='2' >*</font> <font color='#000000' size='2' face='Verdana, Arial, Helvetica, sans-serif'> Name";
		document.getElementById("policeDetailDiv").style.display="none";
		document.getElementById("phoneBroughtByDiv").innerHTML="<font color='#000000' size='2' face='Verdana, Arial, Helvetica, sans-serif'> Phone No.";
	}
	// in order to change the label "name" of brought by to EMT name & to diplay the combo for vehicle in case of choice by 108
	if(obj.value=="3"){
		document.getElementById("nameBroughtBy").innerHTML="<font color='#FF0000' size='2' >*</font> <font color='#000000' size='2' face='Verdana, Arial, Helvetica, sans-serif'> EMT Name</font>";
		document.getElementById("ambulanceDtlDiv").style.display="block";
		document.getElementById("phoneBroughtByDiv").innerHTML="<font color='#FF0000' size='2' >*</font><font color='#000000' size='2' face='Verdana, Arial, Helvetica, sans-serif'> EMT Phone No.";
	}
	else{
		document.getElementById("ambulanceDtlDiv").style.display="none";

	}
	
	
}

function checkIsrefer(){
	var flag=false;
	
	if(document.getElementsByName("isReferred")[0].checked){
		// alert("inst value="+document.getElementsByName("referringInstType")[0].value); 
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
	return flag;
}


function primCatHandler(mode){
// 	alert("fggfgf");
	submitForm(mode);		
}

 function submit4image(){
	//elem = document.getElementsByName('hmode')[0];
	//elem.value = "REFRESHFORIMAGE"; 
	//document.forms[0].submit();
 }


 function validateBroughtBy()
 {
 		
 	if((document.forms[0].isBroughtBy.checked) || ((document.forms[0].isUnknown.checked) ) || (document.forms[0].isBroughtDead.checked))
 	{
	 	if(comboValidation(document.forms[0].isRelative,"Brought By") &&validateBroughtByVehicle()&& 
	 	isEmpty(document.getElementsByName('broughtByName')[0],'Brought By Name') &&
	 	isEmpty(document.getElementsByName('broughtByLocation')[0],'Brought From Location') &&
	 	validateBroughtByPolice())
	 	{
	 		return true
	 	}
	 	else
	 	{
	 		return false
	 	}
	 }
	 else
	 {
	 	return true
	 }
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


 function checkDisabled()
 {
 // alert("checkDisabled");
 obj=document.forms[0].isUnknown;
 if(obj.type=="checkbox" && obj.checked)
 {
//  alert("checked");
 	return true;
 }else{
 return (isSelected(document.forms[0].patPrimaryCatCode,"Primary Category") &&   validateLocation(document.forms[0].patAddCityLocCode,"Location"));
 }
 }
 





function submitTile(mode){
//alert ("inside submitTile");
	elem = document.getElementsByName('hmode')[0];
	elem.value = mode;
	//alert(elem.value);
	document.forms[0].submit();
}
function poulateFieldsWithEmpDtl(selectedElem){
//   alert("sdsd"+selectedElem);
	document.getElementsByName("empIdChk")[0].value=selectedElem;		
	submitForm("GETEMPDEPENDENTDTL");
}  


function showEmployeepopup(e){
    // if(!document.getElementsByName("patIdNo")[0].value=="")
    //	  openPopupWide(document.getElementsByName("patIdNo")[0].value,'<his:path src="/registration/employeeDtlWithDependents.cnt"/>',e);  
    //	  else
    //	  alert("Please enter Employee ID");
    openPopup('<his:path src="/registration/employeeDtlWithDependents.cnt"/>',e,300,600);
  }
function doHomeWork(){
//	alert("home work karo");
	document.getElementsByName("patPrimaryCatCode")[0].disabled=false;
    document.getElementsByName("patAddCityLocCode")[0].disabled=false;
    document.getElementsByName("patAddHNo")[0].disabled=false;
    document.getElementsByName("patAddStreet")[0].disabled=false;
    document.getElementsByName("patAddDistrict")[0].disabled=false;
   	document.getElementsByName("patAddCity")[0].disabled=false;
   	document.getElementsByName("patAddContactNo")[0].disabled=false;
    document.getElementsByName("patAddPIN")[0].disabled=false;
	
}
function disableAmountCollection(obj)
{   
   var unknown=document.getElementsByName("isUnknown")[0];
	if( (obj.checked==true) || (unknown.checked==true) )
	{
		document.getElementsByName("patAmountCollected")[0].disabled=true;
	}
	else
	{
		document.getElementsByName("patAmountCollected")[0].disabled=false;
	}
}

function getVehicleHosCode(obj)
{
		
		var hmode = "GETVEHICLEHOSPITALCODE"; 
		document.forms[0].patVehicleNo.value=	document.forms[0].broughtByVehicleCode[document.forms[0].broughtByVehicleCode.selectedIndex].text
	
        
		var broughtByVehicleCode=document.getElementsByName("broughtByVehicleCode")[0].value;
		var url = "/HISClinical/registration/emgNewPatRegistration.cnt?hmode="+hmode+"&broughtByVehicleCode="+broughtByVehicleCode;
		ajaxFunction(url,"4");

}