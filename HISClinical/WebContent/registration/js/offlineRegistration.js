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
	return flag;
}


function poulateFieldsWithEmpDtl(selectedElem){
 //  alert("sdsd"+selectedElem);
 // 	alert(" document.getElementsByName('patAddCountryCode')[0].value; = "+document.getElementsByName("patAddCountryCode")[0].value)
	document.getElementsByName("empIdChk")[0].value=selectedElem;	
	submitForm("GETEMPDEPENDENTDTL");
}  
function printPage() 
{
var frameElement = parent.document.getElementById("frmMain"); 
//alert("frameElement :"+frameElement);
var win = frameElement.contentWindow ;
win.print();
}
function callThisOnload(){	                 
 	//alert("sdsdsdsd")  
 	//alert(document.getElementsByName('print')[0].value)
	if(document.getElementsByName('print')[0].value=='1')
	{
		//document.getElementById('divCardPrint').style.display="block"
		printPage();
		document.getElementsByName('print')[0].value='0'
		//document.getElementById('divCardPrint').style.display="none"
	}
	//alert(document.forms[0].isOldPatient.value)
	if(document.forms[0].isOldPatient.value=="1"){
		var patCrno=document.forms[0].oldPatCrNo.value
		document.forms[0].isOldPatient.value=""
		document.forms[0].oldPatCrNo.value=""
		parent.document.getElementById("frmMain").src="/HISClinical/registration/commonaction.cnt?mode=OLDPATVISIT&hmode=GETPATDTL&patCrNo="+patCrno;
		//var path="/HISClinical/registration/commonaction.cnt?mode=OLDPATVISIT&hmode=GETPATDTL&patCrNo="+patCrno;
		//var child=window.open(path,"Old Patient Visit","height=400,width=600")
		//child.close();
		
	}   
	
	document.getElementsByName("departmentCode")[0].focus();
	// alert(" document.getElementsByName('patAddCountryCode')[0].value; = "+document.getElementsByName("patAddCountryCode")[0].value)
	showState();
	showLocation();
	sendData();
	checkPatientCategory();
	//disableAmountCollection(document.getElementsByName("isFree")[0])
	//enableSpouseField();
	//alert(document.forms[0].patOldRegExpires.value)
	if(document.forms[0].patOldRegExpires.value=="Expired"){
		alert("Please take the Regsitration Charge \nas the registration has been expired.");
		document.getElementsByName("patOldRegExpires")[0].value="";
	}
	//alert("before error message")
	if(document.getElementsByName("errorMessage")[0].value!=""){
		alert(document.getElementsByName("errorMessage")[0].value)
		document.getElementsByName("errorMessage")[0].value=""
	}
	//alert(document.forms[0].isDuplicatePatientPopup.value)
	if(document.forms[0].isDuplicatePatientPopup.value=="1"){
		//alert("in onload");
		var path="/HISClinical/registration/offlineRegistration.cnt?hmode=POPUP";;
		window.open(path,"popup","height=400,width=600")
		//openPopup(path);
	}
	
	
	setGender(document.getElementsByName("departmentCode")[0])
}

function showEmployeeDiv(){
  document.getElementById("divempIdLabel").style.display=""; 
  document.getElementById("divempIdControl").style.display=""; 
}

function primCatHandler(mode){
	submitForm(mode);		
}


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

/*function Isreferred(elem)
 {
 	alert("inside Isreferred");
  	if(elem.checked){
		document.getElementsByName("patRefGnctdHospitalCode")[0].value="-1";
		document.getElementsByName("patRefHospitalName")[0].value="";
 	if(document.getElementsByName('referringInstType')[0].checked){

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
		document.getElementsByName("patRefGnctdHospitalDept")[0].value=""; 		
 		
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
 }*/

function checkReferDepartment()
{
	if(document.getElementsByName('patRefGnctdHospitalDept')[0].value=='0')
	{
		document.getElementsByName('patRefHospitalDeptOther')[0].disabled=false
		
		}
	else
	{
		document.getElementsByName('patRefHospitalDeptOther')[0].value=""
		document.getElementsByName('patRefHospitalDeptOther')[0].disabled=true
	}
}
function getUnit(obj)
{
	if(obj.value!='-1')
	{
		submitForm('GETUNIT');
	}
	else
	{
		submitForm('NEW');
	}
}
function getRoom(obj)
{
	if(obj.value!='-1')
	{
		submitForm('GETROOM');
	}
	else{
		document.getElementsByName('roomCode')[0].value="-1"
	}
}

function disableAmountCollection(obj)
{   
    if(obj.checked)
	{
		//alert("amount " +document.getElementsByName("patAmountCollected")[0].value)
		document.getElementById("fileidControl").style.display="block"
		document.getElementById("fileid").style.display="block"
		document.getElementById("oldRegDateDiv").style.display="block"
		document.getElementById("oldRegDateDivLabel").style.display="block"
		document.getElementById("preCRNoDiv").style.display="block"
		document.getElementById("preCRNoDivId").style.display="block"
		document.forms[0].patAmountCollected.value="0";
	}
	else
	{
		var amount=document.forms[0].amountCollected.value
		document.getElementsByName("patAmountCollected")[0].value=amount;
		document.getElementById("fileidControl").style.display="none"
		document.getElementById("fileid").style.display="none"
		document.getElementById("oldRegDateDiv").style.display="none"
		document.getElementById("oldRegDateDivLabel").style.display="none"
		document.getElementById("preCRNoDiv").style.display="none"
		document.getElementById("preCRNoDivId").style.display="none"
	}
	
	
}
function validateDept()
{   
    var dept=document.getElementsByName("departmentCode")[0]
    var unit=document.getElementsByName("departmentUnitCode")[0]
    var room=document.getElementsByName("roomCode")[0]
    
    if(isSelected(dept,"Department")
	    && isSelected(unit,"Unit")
	    && isSelected(room,"Room") )
	{
		return true;
	}
	else
	{
		return false;
	}
}

function validateOldRegDate(isPaidFree,obj,sysdate){
	var valid=true
	if(isPaidFree.checked){
		// file number is made mandatory
		if(document.forms[0].fileNo.value=="" || document.forms[0].fileNo.value.length==0){
			alert("Please Enter File No")
			document.forms[0].fileNo.focus()
			return false;
		}
		if(obj.value=="dd-mm-yyyy"){
			alert("Please Enter Prev Reg Date")
			obj.focus()
			return false;
		}
		else if(!isEmpty(obj,"Prev Reg Date")){
			return false
		} 
	}
	
	if(obj.value!="dd-mm-yyyy" && obj.value!=""){
	if(!DateValidator.validate(obj,"Prev Reg Date"))
			return false;

	var ctrlObjOldRegDate = document.forms[0].oldRegDate.Control_Object;
		//alert(ctrlObjPatDOB);
		var elemCurrentDate = document.getElementsByName('sysDate')[0];		
		var flag = ctrlObjOldRegDate.validateAgainstDateOnMode(elemCurrentDate.value,"dd-Mon-yyyy",DateValidator.COMPARE_MODES["LessNEqual"],"Prev Reg Date","Current Date");
	//if(valid && compareDateCall(obj,sysdate,2,"Prev Reg Date","Current Date"))
   /// {
     //   valid=true;
   // } 
  //  else
    //	valid=false;
    	
    	/*if(flag==true)
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
				alert("Old Reg Date ("+ageInYears+" Years) cannot be greater than 125 Years");
				flag=false;
			}
		}	*/
		valid=flag;
	}	
	return valid
}

function printCard(){

	document.getElementsByName("hmode")[0].value="PRINTLASTOPDCARD"
	document.forms[0].submit();

}