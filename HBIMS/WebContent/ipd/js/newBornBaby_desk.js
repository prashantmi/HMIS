function validate1NewBorn()
{
	//alert("inside new baby desk");
	var hisValidator = new HISValidator("DeskFB");
	hisValidator.addValidation("strCrNo","req","Cr.No is Mandatory Field");
	hisValidator.addValidation("strMotherTreatmentCateg","dontselect=0","Please select Treatment Category ");
	hisValidator.addValidation("strBirthMin","req","Time is Mandatory Field"); 
	hisValidator.addValidation("strBirthHour","req","Time is Mandatory Field"); 
	hisValidator.addValidation("strGender","dontselect=0","Please select Gender");
	//hisValidator.addValidation("strReligion","dontselect=0","Please select Religon");
	hisValidator.addValidation("strPatientCaste","dontselect=0","Please select Patient Caste");
	hisValidator.addValidation("strStreet", "req", "Street Name  is a Mandatory Field in New Born Baby Form" );
	hisValidator.addValidation("strCity", "req", "City Name  is a Mandatory Field in New Born Baby Form" );
	hisValidator.addValidation("strDistrict", "req", "District Name  is a Mandatory Field in New Born Baby Form" );
	hisValidator.addValidation("strState","dontselect=0","Please select State");
	hisValidator.addValidation("strCountry","dontselect=0","Please select Country");
	hisValidator.addValidation("strPinCode", "req", "Pin Code  is a Mandatory Field in New Born Baby Form" );
	hisValidator.addValidation("strOccEmpName", "req", "Employee Name  is a Mandatory Field in Patient Occupation Detail" ); 
	hisValidator.addValidation("strOccRelation","dontselect=0","Please select a Relation in Patient Occupation Detail");
	hisValidator.addValidation("strOccBasic", "req", "Please Enter Basic Salary in Patient Occupation Detail" );
	hisValidator.addValidation("strOccDesc", "req", "Please Enter Designation in Patient Occupation Detail" );
	hisValidator.addValidation("strOccOrgType","dontselect=0","Please select a Orgnisation type in Patient Occupation Detail");
	hisValidator.addValidation("strOccOffName", "req", "Please Enter office name in Patient Occupation Detail" );
	hisValidator.addValidation("strOccAdd1", "req", "Please Enter office address in Patient Occupation Detail" );
	hisValidator.addValidation("strOccCity", "req", "Please Enter city in Patient Occupation Detail" );
	hisValidator.addValidation("strOccState","dontselect=0","Please select state in Patient Occupation Detail");
	hisValidator.addValidation("strOccOffPhNo", "req", "Please Enter office phone number in Patient Occupation Detail" );
	hisValidator.addValidation("StrDeptNameNewBorn","dontselect=0","Please select Department "); 
	hisValidator.addValidation("strUnitNewBorn","dontselect=0","Please select Unit");
	//hisValidator.addValidation("strAdmissionType","dontselect=0","Please select Admission Type");
	var retVal = hisValidator.validate(); 
	if(retVal)
	{
	
		if(document.forms[0].strOccRelation.disabled=true)
		{
			document.forms[0].strOccRelation.disabled=false;
			document.forms[0].strOccOrgType.disabled=false;
			document.forms[0].strOccState.disabled=false;
		}
		if(document.forms[0].strFlag.value==1)
		{
			document.forms[0].strDobTime.value=document.forms[0].strDob.value+" "+document.forms[0].strBirthHour.value+":"+document.forms[0].strBirthMin.value+" "+document.forms[0].strAmPm[document.forms[0].strAmPm.selectedIndex].text;
			document.forms[0].hmode.value = "INSERT";
			document.forms[0].deskmode.value="NEWBORNBABY";
			document.forms[0].submit();
			window.close();
		}
		else
		{
			alert("There is no vacant bed in this ward.Please select another ward and room");
			return false;
		}
	}
	
	
}
function getAjaxResponse(res,mode){
		if(mode==1)
		{
			document.getElementById("unitDivId").innerHTML="<select name='strUnitNewBorn'>"+res+"</select>";
			
		}
		
	}
	
function goRetFuncNB(obj)
{
	if(obj.keyCode==13)
	{
		var hisValidator = new HISValidator("DeskFB");  
		hisValidator.addValidation("strCrNo", "req", "Cr No. is a Mandatory Field" );
		hisValidator.addValidation("strCrNo", "minlen=13", "Cr No. must be 13 Digits" );
		var retVal = hisValidator.validate(); 
		if(retVal){
					document.forms[0].hmode.value="GO";
					document.forms[0].deskmode.value="NEWBORNBABY";
					document.forms[0].submit();
		}else{
			return false;
		}
	}
}
function goFuncNB1(){
	
	var hisValidator = new HISValidator("DeskFB");  
	hisValidator.addValidation("strCrNo", "req", "Cr No. is a Mandatory Field" );
	hisValidator.addValidation("strCrNo", "minlen=13", "Cr No. must be 13 Digits" );
	var retVal = hisValidator.validate(); 
	if(retVal){
				document.forms[0].hmode.value="GO";
				document.forms[0].deskmode.value="NEWBORNBABY";
				
				document.forms[0].submit();
				
	}else{
			return false;
		}
		
}
function bedDetailsNB()
{
		var hisValidator = new HISValidator("DeskFB");
		hisValidator.addValidation("StrDeptNameNewBorn","dontselect=0","Please select Department "); 
		hisValidator.addValidation("strUnitNewBorn","dontselect=0","Please select Unit");
		var retVal = hisValidator.validate();
		if(retVal)
		{
			var hmode = "BEDSTATUS";  
			var url='NewBornBabyTransCNT.cnt?hmode='+hmode+'&wardTypeCode='+document.forms[0].strMotherWardTypeCode.value+'&roomTypeCode='+document.forms[0].strMotherRoomTypeCode.value+
	    	'&wardCode='+document.forms[0].strMotherWardCode.value+'&bedTypeCode='+document.forms[0].strMotherBedTypeTypeCode.value
	    	+'&deptCode='+document.forms[0].strMotherDeptCode.value+'&crNo='+document.forms[0].strCrNo.value+'&deptUnitCode='+document.forms[0].strUnitNewBorn.value;
			var featuresList = "width=380,height=390,ALIGN=CENTER,left=300,top=300,scrollbars=yes"
			 myWindow = window.open(url,'popupWindow',featuresList); 
			myWindow.focus();
		}
}

function viewN()
{    	
			if(document.getElementsByName("strPatStatusCode")[0].value==12)
		{
			
			document.forms[0].strCrNo.value="";
			document.getElementById("PatientOccId").style.display="none";
			document.getElementById("PatientOccDtl").style.display="none";
			document.getElementById("newModificationId").style.display="none";
			document.getElementById("newAddressModiId").style.display="none";
			document.getElementById("id5").style.display="none";
			document.getElementById("wardDivId").style.display="none";
			document.getElementById("admissionId").style.display="none";
			document.getElementById("registrationId").style.display="none";
			document.getElementById("TotalId").style.display="none";
			document.getElementById("patientCrEdId").style.display="block";
			document.getElementById("patientCrId").style.display="none";
			document.getElementById("admissionAdvanceId").style.display="none";
			document.getElementById("chargesDtlId").style.display="none";
			document.forms[0].strCrNo.focus();
			return false;
		}
	if(checkCrdef(document.getElementById("strCrNoId"))==false && document.forms[0].strCrNo.value!="")
	{
		document.getElementById("id5").style.display="block";
		document.getElementById("wardDivId").style.display="block";
		document.getElementById("newModificationId").style.display="block";
		document.getElementById("PatientOccId").style.display="block";
		document.getElementById("patientCrEdId").style.display="none";
		document.getElementById("patientCrId").style.display="block";
		document.getElementById("PatientOccId").style.display="block";
		var tempTotal=parseInt(document.forms[0].strAdmissionChargeValue.value)+parseInt(document.forms[0].strNewBornRegistrationChargeVal.value);
		if(document.forms[0].strNewBornRegistrationCharge.value=="1")
		{
			document.getElementById("registrationId").style.display="block";
			document.forms[0].strNewBornRegistrationChargeVal.readOnly="true";
		}
		if(document.forms[0].strAdmissionCharge.value=="1")
		{
			document.getElementById("admissionId").style.display="block";
			document.forms[0].strAdmissionChargeValue.readOnly="true";
		}
		if(document.forms[0].strAdmissionAdvance.value=="1")
		{
			document.getElementById("admissionAdvanceId").style.display="block";
			//document.forms[0].strAdmissionChargeValue.readOnly="true";
		}
		if(document.forms[0].strNewBornRegistrationCharge.value=="1" &&  document.forms[0].strAdmissionCharge.value=="1")
		{
			document.getElementById("TotalId").style.display="block";
			document.forms[0].strTotalChargeVal.value=tempTotal;
			document.forms[0].strTotalChargeVal.readOnly="true";
			
		}
		
	}
	else
	{
		SetCursorToTextEnd('strCrNoId');
		document.getElementById("PatientOccId").style.display="none";
		document.getElementById("PatientOccDtl").style.display="none";
		document.getElementById("newModificationId").style.display="none";
		document.getElementById("newAddressModiId").style.display="none";
		document.getElementById("id5").style.display="none";
		document.getElementById("wardDivId").style.display="none";
		document.getElementById("admissionId").style.display="none";
		document.getElementById("registrationId").style.display="none";
		document.getElementById("TotalId").style.display="none";
		document.getElementById("patientCrEdId").style.display="block";
		document.getElementById("patientCrId").style.display="none";
		document.getElementById("admissionAdvanceId").style.display="none";
		document.getElementById("chargesDtlId").style.display="none";
		//document.forms[0].strCrNo.focus();
		
	}
		
	
}	
function view1()
{
	
	var o1=document.getElementById("id1");
	var o2=document.getElementById("id2");
	var o3=document.getElementById("PatientOccDtl");
	o1.style.display="none";
	o2.style.display="block";
	o3.style.display="block";
	
	
}
function getUnitComboNB()
{
	var hmode = "GETUNIT"; 
	//alert("Hello"+document.forms[0].StrDeptNameNewBorn.value);
	var url = "NewBornBabyTransCNT.cnt?hmode="+hmode+"&deptCode="+document.forms[0].StrDeptNameNewBorn.value;
	ajaxFunction(url,"1");

}
function view2()
{
	
	var o1=document.getElementById("id1");
	var o2=document.getElementById("id2");
	var o3=document.getElementById("PatientOccDtl");
	o1.style.display="block";
	o2.style.display="none";
	o3.style.display="none";
	var obj=document.getElementById("wardDivId");
	obj.style.display="block";
	
}
function view3()
{
	
	var o1=document.getElementById("plusAddModiId");
	var o2=document.getElementById("minusAddModiId");
	var o3=document.getElementById("newAddressModiId");
	o1.style.display="none";
	o2.style.display="block";
	o3.style.display="block";
	
	
}
function view4()
{
	
	var o1=document.getElementById("plusAddModiId");
	var o2=document.getElementById("minusAddModiId");
	var o3=document.getElementById("newAddressModiId");
	o1.style.display="block";
	o2.style.display="none";
	o3.style.display="none";
	var obj=document.getElementById("wardDivId");
	obj.style.display="block";
	
}
function clearRecordNB()
{
		document.getElementById("errId").innerHTML="";
		document.getElementById("nrmId").innerHTML="";
		document.getElementById("PatientOccId").style.display="none";
		document.getElementById("PatientOccDtl").style.display="none";
		document.getElementById("newModificationId").style.display="none";
		document.getElementById("newAddressModiId").style.display="none";
		document.getElementById("id5").style.display="none";
		document.getElementById("wardDivId").style.display="none";
		document.getElementById("admissionId").style.display="none";
		document.getElementById("registrationId").style.display="none";
		document.getElementById("TotalId").style.display="none";
		document.getElementById("patientCrEdId").style.display="block";
		document.getElementById("patientCrId").style.display="none";
		document.getElementById("admissionAdvanceId").style.display="none";
		document.getElementById("chargesDtlId").style.display="none";
		document.forms[0].strCrNo.value="";
		document.forms[0].strCrNo.focus();
	
}
function funUnit()
{
	//alert("Unit"+document.forms[0].strUnitNewBorn.value);
}
 function hour(obj, eve){
 	 	if(eve.keyCode != 13){
 		if (parseInt(obj.value ) > 12 || parseInt(obj.value) < 00 ){
     		alert("hours should be in between 00-12");
     		obj.value="";
 	 		}
 	 		else
 	 		{
 	 			//
 	 		}
 		}
 	}
 	function min(obj, eve){
 	 	if(eve.keyCode != 13){
 		if (parseInt(obj.value )>=60 || parseInt(obj.value) < 00 ){
     		alert("hours should be in between 00-50");
     		obj.value="";
 	 		}
 	 		else
 	 		{
 	 			//
 	 		}
 		}
 	}
 	function focus(obj)
 	{
 		document.forms[0].strBirthMin.focus();
 	}
