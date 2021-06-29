var child = null;
var popIndex = 0;
var gblCntrlObj = null;


function viewshow(obj1,obj2,obj3)
{
		document.getElementById(obj1).style.display="none";
		document.getElementById(obj2).style.display="";
		document.getElementById(obj3).style.display="";
}
function viewhide(obj1,obj2,obj3)
{
		document.getElementById(obj1).style.display="";
		document.getElementById(obj2).style.display="none";
		document.getElementById(obj3).style.display="none";
}


function validate1()
{
	var hisValidator = new HISValidator("patientAdmissionModiTransBean"); 
	hisValidator.addValidation("strCrNo","req","CR No. is mandatory field");
	//hisValidator.addValidation("strFatherName","req","Father Name is mandatory field");
	
	/*if((document.forms[0].strFatherName.value=="" &&  !document.forms[0].strSpouseName.value=="") || (!document.forms[0].strFatherName.value=="" &&  document.forms[0].strSpouseName.value=="") || (!document.forms[0].strFatherName.value=="" &&  !document.forms[0].strSpouseName.value==""))
	{
			//alert("Father/Spouse Name is Mandatory Field");
	}
	
	else
	{
			alert("Father/Spouse Name is Mandatory Field");
			return;
	}*/
	
	
	/* hisValidator.addValidation("strMotherTreatmentCateg","dontselect=0","Please select Treatment Category ");
	hisValidator.addValidation("strReligion","dontselect=0","Please select Religon");
	hisValidator.addValidation("strPatientCaste", "dontselect=0", "Patient Caste is a Mandatory Field" );
	hisValidator.addValidation("strStreet", "req", "Street/Mohallah Name  is a Mandatory Field" );
	hisValidator.addValidation("strCity", "req", "City is a Mandatory Field" );
	//hisValidator.addValidation("strTehsil", "req", "Tehsil is a Mandatory Field" );
	hisValidator.addValidation("strMaritalStatus", "dontselect=0", "Marital Status is a Mandatory Field" );
	
	//hisValidator.addValidation("strDistrict", "req", "District Name  is a Mandatory Field in New Born Baby Form" );
	hisValidator.addValidation("strStateName","dontselect=0","Please select State");
  	hisValidator.addValidation("strCountry","dontselect=0","Please select Country");
	hisValidator.addValidation("strPhoneNo","req","Phone No. is mandatory field"); */
	
	/*if(document.getElementsByName("strOccIsDept")[0].checked)
	{
		hisValidator.addValidation("strOccRelation","dontselect=0","Please Select Relation in Patient Occupation Detail");
		hisValidator.addValidation("strOccEmpName", "req", "Dependent  On  is a Mandatory Field in Patient Occupation Detail" ); 
	}*/
	
	//hisValidator.addValidation("strReligion","dontselect=0","Please select Religon");
	
	if(document.forms[0].strPatIsUnknown.value=="0" || document.forms[0].strPatIsUnknown.value=="" || document.forms[0].strPatIsUnknown.value==" " || document.forms[0].strPatIsUnknown.value==null)
	{	
		hisValidator.addValidation("strStreet", "req", "Street/Mohallah Name  is a Mandatory Field." );
		hisValidator.addValidation("strCity", "req", "City Name  is a Mandatory Field." );
		//hisValidator.addValidation("strMaritalStatus", "dontselect=0", "Marital Status  is a Mandatory Field." );
		
		if(document.getElementsByName("strCountry")[0].value=="IND")
			hisValidator.addValidation("strState", "dontselect=0", "State is a Mandatory Field." );
	
		hisValidator.addValidation("strCountry", "dontselect=0", "Country is a Mandatory Field." );
		hisValidator.addValidation("strMobileNo", "req", "Mobile Number is a Mandatory Field." );
		hisValidator.addValidation("strMobileNo", "maxlen=10","Mobile No Cannot be More than 10 digits");
		hisValidator.addValidation("strPhoneNo", "maxlen=13","Phone No Cannot be More than 13 digits");
		if(document.getElementsByName("strPhoneNo")[0].value != "")
			hisValidator.addValidation("strPhoneNo", "minlen=8","Phone No Cannot be Less than 8 digits");
		if(document.getElementsByName("strMobileNo")[0].value != "")
		{
			if(document.getElementsByName("strCountry")[0].value=="IND" && !(document.getElementsByName("strMobileNo")[0].value.startsWith("6") || document.getElementsByName("strMobileNo")[0].value.startsWith("7") || document.getElementsByName("strMobileNo")[0].value.startsWith("8") || document.getElementsByName("strMobileNo")[0].value.startsWith("9")))
			{	
				alert("Mobile Number must start with 6,7,8 or 9");
			    return false;
			}
		}
		
		if(document.getElementsByName("strFirstPersonName")[0].value == "" && document.getElementsByName("strSecondPersonName")[0].value != "")
		{
			alert("You cannot enter Second person details without entering first person details!!");
			return false;
		}
			
	    if(document.getElementsByName("strFirstPersonName")[0].value != "")
		{
			hisValidator.addValidation("strFirstPersonRelationCode", "dontselect=0", "First Person Relation Is Mandatory." );
			hisValidator.addValidation("strEmgAddress1", "req", "First Person Address Is Mandatory" );
			hisValidator.addValidation("strFirstpersonphone","req","First perosn Phone is mandatory");
			hisValidator.addValidation("strFirstpersonphone", "maxlen=13","First Person Phone No Cannot be More than 13 digits");
			hisValidator.addValidation("strFirstpersonphone", "minlen=8","First Person Phone No Cannot be Less than 8 digits");
		}
		if(document.getElementsByName("strSecondPersonName")[0].value != "")
		{
			hisValidator.addValidation("strSecondPersonRelationCode", "dontselect=0", "Second Person Relation Is Mandatory." );
			hisValidator.addValidation("strEmgAddress2", "req", "Second Person Address Is Mandatory" );
			hisValidator.addValidation("strSecondpersonphone","req","Second perosn Phone is mandatory");
			hisValidator.addValidation("strSecondpersonphone", "maxlen=13","Second person Phone No Cannot be More than 13 digits");
			hisValidator.addValidation("strSecondpersonphone", "minlen=8","Second person Phone No Cannot be Less than 8 digits");
		}
	}
	//hisValidator.addValidation("strOccBasic", "req", "Monthly Income is a Mandatory Field." );
	//hisValidator.addValidation("strPatientCaste", "dontselect=0", "Patient caste is a Mandatory Field." );
	
	
	
	
	//hisValidator.addValidation("strOccDesc", "req", "Please Enter Designation in Patient Occupation Detail" );
	//hisValidator.addValidation("strOccOrgType","dontselect=0","Please select a Orgnisation type in Patient Occupation Detail");
	//hisValidator.addValidation("strOccOffName", "req", "Please Enter office name in Patient Occupation Detail" );
	//hisValidator.addValidation("strOccAdd1", "req", "Please Enter office address in Patient Occupation Detail" );
	//hisValidator.addValidation("strOccCity", "req", "Please Enter city in Patient Occupation Detail" );
	//hisValidator.addValidation("strOccState","dontselect=0","Please select state in Patient Occupation Detail");
	//hisValidator.addValidation("strOccOffPhNo", "req", "Please Enter office phone number in Patient Occupation Detail" );
   	hisValidator.addValidation("strDeptCode","dontselect=0","Please Select Department");
	/*if(document.forms[0].strUnitReq.value=='1')
		hisValidator.addValidation("strDeptUnitCode","dontselect=0","Please Select Unit");
	else if(document.forms[0].strRoomReq.value=='1')
		hisValidator.addValidation("strRoomCode","dontselect=0","Please Select Room");
	else
	{}*/
	
		// Consultant Selection Validation (updated at 31-Mar-2011 by Pragya)
	hisValidator.addValidation("strTreatmentCategoryCode","dontselect=0","Please select Treatment Category");
	hisValidator.addValidation("strWardCode","dontselect=0","Please select Ward");
	hisValidator.addValidation("strConsultantCode","dontselect=0","Please select Consultant");
	hisValidator.addValidation("strCaseSheetNo", "req","Please Enter Gate Pass No.");

	//hisValidator.addValidation("strAdmissionType", "dontselect=0", "Admission Type is a Mandatory Field" );
	//hisValidator.addValidation("strReliefFund", "dontselect=0", "Relief Fund is a Mandatory Field" ); 
	
	var retVal = hisValidator.validate(); 
	hisValidator.clearAllValidations();
	
	document.forms[0].strTreatmentCategoryCode.disabled=false;
	if(retVal)
	{
	/**if(document.forms[0].strMsApprovalStatus.value==0)
		{
			alert("!!!Msapproval required!!!");
			return false;
		}*/
		/* if(document.forms[0].strOccRelation.disabled=true)
		{
			document.forms[0].strOccRelation.disabled=false;
			//document.forms[0].strOccOrgType.disabled=false;
			//	document.forms[0].strOccState.disabled=false;
		} */
			var deptUnit="";
			var room="";
			/* if(document.forms[0].strUnitReq.value=='1')
			{
				deptUnit=document.forms[0].strDeptCode[document.forms[0].strDeptCode.selectedIndex].text+"/"+document.forms[0].strDeptUnitCode[document.forms[0].strDeptUnitCode.selectedIndex].text;
				document.forms[0].strUnitName.value=document.forms[0].strDeptUnitCode[document.forms[0].strDeptUnitCode.selectedIndex].text;
			}
			else
			{
				deptUnit=document.forms[0].strDeptCode[document.forms[0].strDeptCode.selectedIndex].text;
				document.forms[0].strUnitName.value="";
			}
			document.forms[0].strDeptUnitName.value=deptUnit;
			document.forms[0].strDeptName.value=document.forms[0].strDeptCode[document.forms[0].strDeptCode.selectedIndex].text;
			
			if(document.forms[0].strRoomReq.value=='1')
			{
				room=document.forms[0].strRoomCode[document.forms[0].strRoomCode.selectedIndex].text;
			}
			else
			{
				room="";
			}*/
			//document.forms[0].strRoom.value=room;
			//document.forms[0].strWardName.value=document.forms[0].strWardCode[document.forms[0].strWardCode.selectedIndex].text;
			//document.forms[0].strConsultantName.value=document.forms[0].strConsultantCode[document.forms[0].strConsultantCode.selectedIndex].text;
			document.forms[0].strTreatmentCategoryCode.disabled=false;
			document.forms[0].hmode.value = "INSERT";
			document.forms[0].submit();
			
		
	}
	
	
}
function getAjaxResponse(res,mode){
		if(mode==1)
		{
			document.getElementById("unitDivId").innerHTML="<select name='strUnitNewBorn'>"+res+"</select>";			
		}
		if(mode==2)
		{
			document.getElementById("UnitId").innerHTML="<select name='strDeptUnitCode' class='comboNormal' onchange='getCombo(this)'>"+res+"</select>";			
			document.getElementById("consId").innerHTML="<select name='strConsultantCode' class='comboNormal' onchange='getCombo(this)'><option value='0'>Select Value</option></select>";			
			document.getElementById("WardId").innerHTML="<select name='strWardCode' class='comboNormal'><option value='0'>Select Value</option></select>";
			if(document.forms[0].strRoomReq.value=='1')
			document.getElementById("RoomId").innerHTML="<select name='strRoomCode' class='comboNormal'><option value='0'>Select Value</option></select>";
		}
		if(mode==3)
		{
			
			
			document.getElementById("WardId").innerHTML="<select name='strWardCode' class='comboNormal' onchange='getCombo(this)'>"+res.split('^')[0]+"</select>";			
			//document.getElementById("treatCat").innerHTML="<select name='strTreatmentCategoryCode'>"+res+"</select>";
			document.getElementById("consId").innerHTML="<select name='strConsultantCode' class='comboNormal' onchange='getCombo(this)'>"+res.split('^')[1]+"</select>";
			if(document.forms[0].strRoomReq.value=='1')
			document.getElementById("RoomId").innerHTML="<select name='strRoomCode' class='comboNormal'><option value='0'>Select Value</option></select>";
		}
		if(mode==4)
		{
			
			document.getElementById("WardId").innerHTML="<select name='strWardCode' class='comboNormal' onchange='getCombo(this)'>"+res.split('^')[0]+"</select>";			
			//document.getElementById("treatCat").innerHTML="<select name='strUnitNewBorn'>"+res+"</select>";
			document.getElementById("consId").innerHTML="<select name='strConsultantCode' class='comboNormal' onchange='getCombo(this)'>"+res.split('^')[1]+"</select>";
			if(document.forms[0].strRoomReq.value=='1')
			document.getElementById("RoomId").innerHTML="<select name='strRoomCode' class='comboNormal'><option value='0'>Select Value</option></select>";
		}
		if(mode==5)
		{
			document.getElementById("RoomId").innerHTML="<select name='strRoomCode' class='comboNormal'>"+res+"</select>";				
		}
		if(mode==6)
		{
			document.getElementById("treatCat").innerHTML="<select name='strTreatmentCategoryCode' class='comboNormal'>"+res+"</select>";				
		}	
		if(mode == 7)
		{
			var objEle = document.getElementById("districtSelectBoxDiv");
			objEle.innerHTML ="<select name='strDistrict' class='comboNormal' onclick='onchangeDistrict();' >"+res+"</select>";
		}
		if(mode == '8')
		{
			
			var resp=res.split("#");
			document.getElementById("strStateId").value = resp[0];
			document.getElementById("strDistrictId").value = resp[1];
		}
		if(mode==9)
		{
			//alert(res);
			document.getElementById("WardId").innerHTML="<select name='strWardCode' class='comboNormal' onchange='getCombo(this)'>"+res+"</select>";				
		}
	}
	
function goRetFunc(obj)
{
	var flag=validateData(obj,5);
	if(flag){
		if(obj.keyCode==13)
		{
			var hisValidator = new HISValidator("patientAdmissionModiTransBean");  
			hisValidator.addValidation("strCrNo", "req", "CR No. is a Mandatory Field" );
			hisValidator.addValidation("strCrNo","minlen="+document.forms[0].strCrNo.maxLength,"CR No. must be "+document.forms[0].strCrNo.maxLength+" Digits" );
			var retVal = hisValidator.validate(); 
			if(retVal){
						document.forms[0].hmode.value="GO";
						document.forms[0].submit();
			}else{
			
			return false;
			}
		}
	}
	else
	{
		return false;
	}
}
function goFunc()
{
	var hisValidator = new HISValidator("patientAdmissionModiTransBean");  
	hisValidator.addValidation("strCrNo", "req", "CR No. is a Mandatory Field" );
	hisValidator.addValidation("strCrNo","minlen="+document.forms[0].strCrNo.maxLength,"CR No. must be "+document.forms[0].strCrNo.maxLength+" Digits" );
	//hisValidator.addValidation("strCrNo", "minlen=13", "CR No. must be 13 Digits" );
	document.forms[0].strSaveFlag.value="";
	document.forms[0].strPatientCrNo.value="";
	
	
	var retVal = hisValidator.validate(); 
	if(retVal){
				document.forms[0].hmode.value="GO";
				document.forms[0].submit();
				
		}
		else
		{
		
		return false;
		}
		
}
/*
 * This function set Age, Age Unit Code and Sex Code
 */
function getAgeSex()
{
	var temp1=document.forms[0].strHiddenPatDtl.value.split("^");
	var strUnitCode="1";
	var sexCode="3";
	var strTemp=temp1[3];
	var strAgeSign="";
	var temp2=strTemp.split("/");
	/**
	 * This loop is for generating age Code
	 */
	for(var i=0;i<strTemp.length;i++)
	{
		if(strTemp.charAt(i)=='Y' || strTemp.charAt(i)=='y'){
			strAgeSign=strTemp.charAt(i);
		strUnitCode="3";
		break;
		}
		else if(strTemp.charAt(i)=='M' || strTemp.charAt(i)=='m')
		{
			strAgeSign=strTemp.charAt(i);
			strUnitCode="2";
			break;
		}
		else if(strTemp.charAt(i)=='D' || strTemp.charAt(i)=='d')
		{
			strAgeSign=strTemp.charAt(i);
			strUnitCode="1";
			break;
		}
		else if(strTemp.charAt(i)=='W' || strTemp.charAt(i)=='w')
		{
			strAgeSign=strTemp.charAt(i);
			strUnitCode="4";
			break;
		} 
		else if(strTemp.charAt(i)=='H' || strTemp.charAt(i)=='h')
		{
			strAgeSign=strTemp.charAt(i);
			strUnitCode="5";
			break;
		} 
		else {
			strUnitCode="3";
			//break;
		}
		
	 }
	 /**
	  * This loop is for generating Sex Code
	  */
	for(var i=0;i<temp2[1].length;i++)
	{
		if(temp2[1].charAt(i)=='M' || temp2[1].charAt(i)=='m'){
		sexCode="1";
		break;
		}
		if(temp2[1].charAt(i)=='F' || temp2[1].charAt(i)=='f')
		{
			sexCode="2";
			break;
		}
			
	 }
	 var temp3=temp2[0].split(strAgeSign);
	  document.forms[0].strAge.value=temp3[0];
	  if(document.forms[0].strAge.value<=0)
	  	document.forms[0].strAge.value=1;
	   document.forms[0].strAgeUnit.value=strUnitCode; 
	   document.forms[0].strSexCode.value=sexCode;
 }

function bedDetails()
{
		/*var deptcode="";
		var deptUnitCode="";
		var deptName="";
		getAgeSex();
		
		if(document.forms[0].strNewBorn.value=="1")
		{
		deptcode=document.forms[0].strDeptNameNewBorn.value;
		deptUnitCode=document.forms[0].strUnitNewBorn.value;
		deptName=document.forms[0].strDeptNameNewBorn[document.forms[0].strDeptNameNewBorn.selectedIndex].text;
		}
		else
		{
			deptcode=document.forms[0].strDeptCode.value;
			deptUnitCode=document.forms[0].strDeptUnitCode.value;
			deptName=document.forms[0].strDeptName.value;
		}
		
		//alert("treatment Category Code1"+document.forms[0].strTreatmentCategoryCode.value);
		var hmode = "BEDSTATUS"; 
		
		var url='PatientAdmissionModificationTransCNT.cnt?hmode='+hmode+'&wardTypeCode='+document.forms[0].strWardTypeCode.value+'&roomTypeCode='+document.forms[0].strRoomTypeCode.value+
	    '&wardCode='+document.forms[0].strWardCode.value+'&bedTypeCode='+document.forms[0].strBedTypeCode.value
	    +'&deptCode='+deptcode+'&crNo='+document.forms[0].strCrNo.value+'&deptUnitCode='+deptUnitCode+'&msApprovalStatus='+document.forms[0].strMsApprovalStatus.value+'&msApprovalFlag='+document.forms[0].strMsApprovalFlag.value+'&wardName='+document.forms[0].strWardName.value+'&roomCode='+document.forms[0].strRoomCode.value+'&deptName='+deptName+'&treatmentCategCode='+document.forms[0].strTreatmentCategoryCode.value+'&ageCode='+document.forms[0].strAgeUnit.value+'&sexCode='+ document.forms[0].strSexCode.value+'&age='+document.forms[0].strAge.value;
	  
	   //alert("treatment Category Code2"+document.forms[0].strTreatmentCategoryCode.value);
		var featuresList = "width=380,height=390,ALIGN=CENTER,left=300,top=300,scrollbars=yes"
		openModalPopUp(createFHashAjaxQuery(url),"400","350","1"); 
		*/
	
	$('.modal-container').css('display','block');		
	var mode="BEDSTATUS1";
	WrdRoomBedtypUnt_code=document.forms[0].strWardCode.value.split('$')[0]+"^0^0^0^0";
	var url="IpdCNT.cnt?hmode="+mode+"&modPopUp="+WrdRoomBedtypUnt_code;
	$('#modalSpaceId').load(createFHashAjaxQuery(url));

	
}

function view()
{
	if(document.getElementsByName("strCrNo")[0].value!="")
	{
		if(checkCrdef(document.getElementById("strCrNoId"))==false)//Complete CR No. Entered
		{
			isDependent();
			getAgeSex();
			//document.getElementById("id4").style.display="none";
			document.getElementById("wardDivId").style.display="block";
			document.getElementById("csno").style.display="block";
			//document.getElementById("newModificationId").style.display="block";
			document.getElementById("PatientOccId").style.display="block";
			document.getElementById("patientCrEdId").style.display="none";
			document.getElementById("patientCrLabelId").style.display="none";
			//document.getElementById("patientCrId").style.display="block";
			//document.getElementById("patientCrId1").style.display="block";
			document.getElementById("patName1").innerHTML=document.forms[0].strPatName.value;
			document.getElementById("patName2").innerHTML=document.forms[0].strPatName.value;
			document.getElementById("patInformationIdPlus").style.display="block";
			document.getElementById("patInformationIdMinus").style.display="none";
			document.getElementById("newModificationFormId").style.display="block";
			document.getElementById("PatientOccId").style.display="block";
			document.getElementById("mandCRId").style.display="none";
			document.getElementById("DeparmentUnitModiId").style.display="block";
			document.getElementById("remarksId").style.display="block";
			document.getElementById("deptWardId").style.display="block";
			document.getElementById("searchdata").style.display="none";
			
			var object=document.getElementById("emrgencyDivId");
			object.style.display="block";
			
			if(document.forms[0].strNewBorn.value==0)
			{
				
				//document.getElementById("DeparmentUnitId").style.display="block";
			}
			if(document.forms[0].strNewBorn.value==1)
			{
				document.getElementById("DeparmentUnitModiId").style.display="block";
			}
			document.forms[0].strTreatmentCategoryCode.disabled=true;

	    }
		else//only predefined Digits in CR Field
		{
			//SetCursorToTextEnd('strCrNoId');
			SetSelectedCrNo();
			document.getElementById("newModificationFormId").style.display="none";
			document.getElementById("newAddressModiId").style.display="none";
			document.getElementById("DeparmentUnitModiId").style.display="none";
			document.getElementById("DeparmentUnitId").style.display="none";
			document.getElementById("PatientOccId").style.display="none";
			document.getElementById("PatientOccDtl").style.display="none";
			document.getElementById("wardDivId").style.display="none";
			document.getElementById("patientCrEdId").style.display="block";
			document.getElementById("patientCrLabelId").style.display="block";
			document.getElementById("id4").style.display="none";
			document.getElementById("patientCrId").style.display="none";
			document.getElementById("mandCRId").style.display="";
			document.getElementById("patInformationIdPlus").style.display="none";
			document.getElementById("patInformationIdMinus").style.display="none";
			document.getElementById("remarksId").style.display="none";
			document.getElementById("deptWardId").style.display="none";
			document.getElementById("csno").style.display="none";
			//document.forms[0].strCrNo.focus();
		}
	}
	else
	{
		document.getElementById("newModificationFormId").style.display="none";
		document.getElementById("newAddressModiId").style.display="none";
		document.getElementById("DeparmentUnitModiId").style.display="none";
		document.getElementById("DeparmentUnitId").style.display="none";
		document.getElementById("PatientOccId").style.display="none";
		document.getElementById("PatientOccDtl").style.display="none";
		document.getElementById("wardDivId").style.display="none";
		document.getElementById("patientCrEdId").style.display="block";
		document.getElementById("patientCrLabelId").style.display="block";
		document.getElementById("id4").style.display="none";
		document.getElementById("patientCrId").style.display="none";
		document.getElementById("mandCRId").style.display="";
		document.getElementById("patInformationIdPlus").style.display="none";
		document.getElementById("patInformationIdMinus").style.display="none";
		document.getElementById("remarksId").style.display="none";
		document.getElementById("deptWardId").style.display="none";
		document.getElementById("csno").style.display="none";
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
function getUnitCombo()
{
	var hmode = "GETUNIT"; 
	var url = "NewBornBabyTransCNT.cnt?hmode="+hmode+"&deptCode="+document.forms[0].strDeptNameNewBorn.value+"$strCrNo="+document.forms[0].strCrNo.value;
	ajaxFunction(url,"1");

}
function getCombo(obj)
{
	if(obj.name=='strDeptCode' && document.forms[0].strUnitReq.value=='1')
	{
		var hmode = "GETUNIT"; 
		var url = "PatientAdmissionModificationTransCNT.cnt?hmode="+hmode+"&deptCode="+obj.value;
		ajaxFunction(url,"2");
	}
	if(obj.name=='strDeptCode' && document.forms[0].strUnitReq.value=='0')
	{
		var hmode = "GETWARDCATCONSULTANT"; 
		var url = "PatientAdmissionModificationTransCNT.cnt?hmode="+hmode+"&deptCode="+obj.value+"&unitReq="+document.forms[0].strUnitReq.value+"&age="+document.forms[0].strAge.value+"&ageCode="+document.forms[0].strAgeUnit.value+"&sexCode="+document.forms[0].strSexCode.value+"&crNo="+document.forms[0].strCrNo.value+"&treatmentCategCode="+document.forms[0].strTreatmentCategoryCode.value+"&wardTypeCode="+document.forms[0].strWardTypeCode.value;
		ajaxFunction(url,"3");
	}
	if(obj.name=='strDeptUnitCode')
	{
		var hmode = "GETWARDCATCONSULTANT"; 
		var url = "PatientAdmissionModificationTransCNT.cnt?hmode="+hmode+"&deptUnitCode="+obj.value+"&unitReq="+document.forms[0].strUnitReq.value+"&age="+document.forms[0].strAge.value+"&ageCode="+document.forms[0].strAgeUnit.value+"&sexCode="+document.forms[0].strSexCode.value+"&crNo="+document.forms[0].strCrNo.value+"&treatmentCategCode="+document.forms[0].strTreatmentCategoryCode.value+"&wardTypeCode="+document.forms[0].strWardTypeCode.value;
		ajaxFunction(url,"4");
	}
	if(obj.name=='strWardCode' && document.forms[0].strUnitReq.value=='1' && document.forms[0].strRoomReq.value=='1')
	{
		var hmode = "GETROOM"; 
		var url = "PatientAdmissionModificationTransCNT.cnt?hmode="+hmode+"&wardCode="+obj.value+"&deptUnitCode="+document.forms[0].strDeptUnitCode.value+"&unitReq="+document.forms[0].strUnitReq.value+"&age="+document.forms[0].strAge.value+"&ageCode="+document.forms[0].strAgeUnit.value+"&sexCode="+document.forms[0].strSexCode.value+"&crNo="+document.forms[0].strCrNo.value+"&treatmentCategCode="+document.forms[0].strTreatmentCategoryCode.value+"&wardTypeCode="+document.forms[0].strRoomTypeCode.value;
		ajaxFunction(url,"5");
	}
	if(obj.name=='strWardCode' && document.forms[0].strUnitReq.value=='0' && document.forms[0].strRoomReq.value=='1')
	{
		var hmode = "GETROOM"; 
		var url = "PatientAdmissionModificationTransCNT.cnt?hmode="+hmode+"&wardCode="+obj.value+"&deptCode="+document.forms[0].strDeptCode.value+"&unitReq="+document.forms[0].strUnitReq.value+"&age="+document.forms[0].strAge.value+"&ageCode="+document.forms[0].strAgeUnit.value+"&sexCode="+document.forms[0].strSexCode.value+"&crNo="+document.forms[0].strCrNo.value+"&treatmentCategCode="+document.forms[0].strTreatmentCategoryCode.value+"&wardTypeCode="+document.forms[0].strRoomTypeCode.value;
		ajaxFunction(url,"5");
	}
	if(obj.name=='strTreatmentCategoryCode')
	{
		var hmode = "GETWARD"; 
		var url = "PatientAdmissionModificationTransCNT.cnt?hmode="+hmode+"&deptCode="+document.forms[0].strDeptNameNewBorn.value+"$strCrNo="+document.forms[0].strCrNo.value;
		ajaxFunction(url,"6");
	}
	if(obj.name=='strConsultantCode')
	{
		var hmode = "GETWARD"; 
		var unit=0;
		if(document.forms[0].strConsultantCode.value!=0)
			unit=document.forms[0].strConsultantCode.value.split("$")[1];
		var url = "PatientAdmissionModificationTransCNT.cnt?hmode="+hmode+"&deptCode="+document.forms[0].strDeptCode.value+"&deptUnitCode="+unit+"&age="+document.forms[0].strAge.value+"&ageCode="+document.forms[0].strAgeUnit.value+"&sexCode="+document.forms[0].strSexCode.value+"&crNo="+document.forms[0].strCrNo.value+"&treatmentCategCode="+document.forms[0].strTreatmentCategoryCode.value+"&wardTypeCode="+document.forms[0].strRoomTypeCode.value;
		ajaxFunction(url,"9");
	}
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
//Plus Click
function viewX()
{	
	document.getElementById("patInformationIdMinus").style.display="";
	document.getElementById("patInformationIdPlus").style.display="none";
	document.getElementById("id4").style.display="";
}
//Minus Click
function viewY()
{	
	document.getElementById("patInformationIdMinus").style.display="none";
	document.getElementById("patInformationIdPlus").style.display="";
	document.getElementById("id4").style.display="none";
}
function clearRecord()
{
	/*document.forms[0].strCrNo.value="";
	view();*/
	document.forms[0].strSaveFlag.value="";
	document.forms[0].strPatientCrNo.value="";	
	document.forms[0].hmode.value="CLEAR";
	document.forms[0].submit();
}
function funUnit()
{
//	alert("Unit"+document.forms[0].strUnitNewBorn.value);
}
 function cancelPage(){
	document.forms[0].hmode.value="INITIALPAGE";
	document.forms[0].submit();
}

function openSlipPopup()
{
				if(document.forms[0].strSaveFlag.value=='1' && document.forms[0].strPatientCrNo.value!='')
				{
					var url='PatientAdmissionTransCNT.cnt?hmode=PRINTSLIP&strCrNo='+document.forms[0].strPatientCrNo.value+'&strAdmNo='+document.forms[0].strAdmNo.value+'&duplicateMode=1';
					//var url='PatientAdmissionTransCNT.cnt?hmode=PRINTSLIP&strCrNo=1011100004939';
					child = window.open(createFHashAjaxQuery(url),'popupWindow','status=yes,scrollbars=yes,height=600,width=500,left=400,top=100');  
	 				child.moveTo(900,250);
	 				child.focus();
				}
}
function printSlip()
{
	window.print();
	var t=setTimeout("printSlip1()",2000);
}
function printSlip1()
{
	    if (confirm("Whether Printed Successfully?")) 
        {
            window.close();
			self.close();
        }
        else
        {
        	printSlip();
        }
}
function setCrData(crno)
{
	document.getElementsByName("strCrNo")[0].value=crno;
	goFunc();
}
function openPrintPopUp()
{
	if(document.forms[0].strSaveFlag.value=='1' && document.forms[0].strPatientCrNo.value!='' )
	{
		printSlip();
		//document.forms[0].hmode.value="PRINTSLIP";
		//alert(document.forms[0].strPatientCrNo.value);
		//document.forms[0].strCrNo.value=document.forms[0].strPatientCrNo.value;
		window.print();
		//document.forms[0].submit();
		//alert(document.forms[0].strCrNo.value);
		//alert(document.forms[0].hmode.value);
		
			//window.close();
		
	
	 }
	
	//alert(window.matchMedia('print'));
	 document.forms[0].strSaveFlag.value=0;
	 //window.onbeforeprint = beforePrint;
	 //window.onafterprint = hidePrintableSlip();
	 //setTimeout("hidePrintableSlip()",2000);
	 //document.getElementById("printableSlip").style.display="none"; 
}
function hidePrintableSlip()
{
	//alert("hide");
	document.getElementById("printableSlip").style.display="none"; 
}
function printLastBill()
{
	//alert("show");
	//document.getElementById("printableSlip").style.display="";
	if(document.getElementById("printableSlip").style.display=="")
	{
		// alert("showsdsdsdsdsd");
		alert("No Bill Generated Yet.");
		return;
	}
	else
	    window.print();
}
function showPrintableSlip()
{
	//alert("show");
	document.getElementById("printableSlip").style.display=""; 
}
var globalCnt=0;
String.prototype.replaceAll=function(target, replacement) 
{
	  return this.split(target).join(replacement);
}
/*function onchangeState()
{
	var cbo = document.getElementsByName("strState")[0];
	var text = "";
	if(cbo.value!="0")
		text = cbo.options[cbo.selectedIndex].text;
	document.getElementsByName("strStateName")[0].value = text;
	var hmode = "GETDISTRICT"; 
	var url='PatientAdmissionModificationTransCNT.cnt?hmode='+hmode+'&stateCode='+cbo.value;
	ajaxFunction(url,"7");
	
}*/

function onchangeState()
{   var cboCountry = document.getElementsByName("strCountry")[0];
	var cbo = document.getElementsByName("strState")[0];
	var cboDist = document.getElementsByName("strDistrictCode")[0];

	
  	if(cboCountry.value == "IND"){
  		
	var text = "";
	if(cbo.value!="0")
		text = cbo.options[cbo.selectedIndex].text;
	document.getElementsByName("strStateName")[0].value = text;
	var hmode = "GETDISTRICT"; 
	var url='PatientAdmissionModificationTransCNT.cnt?hmode='+hmode+'&stateCode='+cbo.value;
	ajaxFunction(url,"7");
  	}

	}
	


function onchangeCountry()
{
	var cbo = document.getElementsByName("strCountry")[0];
	var cr =document.getElementById("strCrNoId").value;	
		if(cbo.value != "IND")
		{ 	
			
			document.getElementById("stateTextBoxDiv").style.display=""; 
			document.getElementById("stateComboDiv").style.display="none"; 
			
			document.getElementById("districtTextBoxDiv").style.display="";
	  		document.getElementById("districtSelectBoxDiv").style.display="none";
	  		
	  		/*var hmode = "GETSTATE"; 
	  		var url='PatientAdmissionModificationTransCNT.cnt?hmode='+hmode+'&crno='+cr;
	  		ajaxFunction(url,"8");*/
			
		}
		if(cbo.value == "IND")	
		{  
			document.getElementById("stateTextBoxDiv").style.display="none"; 
			document.getElementById("stateComboDiv").style.display=""; 
			
			document.getElementById('districtTextBoxDiv').style.display="none";
	  		document.getElementById('districtSelectBoxDiv').style.display="";
		}
}
function onchangeDistrict(){

	var cboDist = document.getElementsByName("strDistrict")[0];
	var texts = cboDist.options[cboDist.selectedIndex].text;
	document.getElementsByName("strDistrictName")[0].value = texts;
}
