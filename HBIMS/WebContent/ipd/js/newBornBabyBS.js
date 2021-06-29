function validate1NewBorn()
{
	//alert("inside new baby");
	var cboCountry = document.getElementsByName("strCountry")[0];
	var hisValidator = new HISValidator("newBornTransBean");
	if(document.forms[0].strCrNo.value=="")
	{
		alert("CR No. is Mandatory Field");
		document.forms[0].strCrNo.focus();
		return;
	}
	else
	{
		validateThroughRegExp(document.forms[0].strCrNo,1);
		hisValidator.addValidation("strCrNo","req","CR No. is Mandatory Field");
	//	hisValidator.addValidation("strMotherTreatmentCateg","dontselect=0","Please select Treatment Category ");
		//hisValidator.addValidation("strBirthMin","req","Time is Mandatory Field"); 
		//hisValidator.addValidation("strBirthHour","req","Time is Mandatory Field"); 
		/*alert(document.forms[0].strDob.value);
		alert(document.forms[0].strCtDate.value);
		alert(document.forms[0].strMothAdmDate.value);*/
		
		//alert(document.forms[0].datetime.value);
		/*var mon=['0','Jan','Feb','Mar','Apr','May','Jun','Jul','Aug','Sep','Oct','Nov','Dec'];
		var temp=document.forms[0].datetime.value.split(" ")[0];
		temp=temp.split("-")[1];
		if(temp<10)
			temp=temp.substring(1);
		var temp1=document.forms[0].datetime.value;
		temp1=replaceAt(temp1,3,mon[temp],2);
		//document.forms[0].datetime.value=temp1;
		document.forms[0].strDob.value=temp1;
		//alert(document.forms[0].strDob.value);
		
		*/
		
		
	//	temp=temp.replaceAt(3,"df");
		
		//alert(document.forms[0].strCtDate.value);
		//alert($('#datetimepicker1').data("DateTimePicker").date());;
	//document.forms[0].strDob.value=document.forms[0].datetime.value.split(" ")[0];
	//	alert(document.forms[0].datetime);
		document.forms[0].strDob.value=document.forms[0].datetime.value;
		var ret=compareDate(document.forms[0].strDob.value.split(" ")[0],document.forms[0].strCtDate.value);
		//alert(ret.mode);
		if(ret.mode==2)
		{
			alert("Date of Birth Can't Be Greater Than Current Date");
			return false;
		}
		var ret1=compareDate(document.forms[0].strDob.value.split(" ")[0],document.forms[0].strMothAdmDate.value);
		if(ret1.mode==0)
		{
			alert("Date of Birth Can't Be Less Than Mother's Admission Date");
			return false;
		}
		
		hisValidator.addValidation("strGender","dontselect=0","Please Select Gender");
		
		//hisValidator.addValidation("strReligion","dontselect=0","Please select Religon");
		hisValidator.addValidation("strFatherName","req","Please Enter Father Name");
		//hisValidator.addValidation("strPatientCaste","dontselect=0","Please select Patient Caste");
		hisValidator.addValidation("strStreet", "req", "Street/Mohallah Name  is a Mandatory Field in New Born Baby Form" );
		hisValidator.addValidation("strCity", "req", "City Name  is a Mandatory Field in New Born Baby Form" );
		//hisValidator.addValidation("strDistrict", "req", "District Name  is a Mandatory Field in New Born Baby Form" );
		//hisValidator.addValidation("strState","dontselect=0","Please select State");
		if(cboCountry.value == "IND"){
			hisValidator.addValidation("strState", "dontselect=0", "State is a Mandatory Field" );
		}
		if(cboCountry.value != "IND"){
			hisValidator.addValidation("strStateName", "req","State is a Mandatory Field");
		}
		hisValidator.addValidation("strCountry","dontselect=0","Please select Country");
		if(document.getElementsByName("strOccIsDept")[0]!=undefined && document.getElementsByName("strOccIsDept")[0].checked)
		{
				//hisValidator.addValidation("strOccRelation","dontselect=0","Please Select Relation in Patient Occupation Detail");
				hisValidator.addValidation("strOccEmpName", "req", "Dependent  On  is a Mandatory Field in Patient Occupation Detail" ); 
		}
		hisValidator.addValidation("strIdMark1","maxlen=12","ID Mark1 Can't Be more than 12 characters");
		hisValidator.addValidation("strIdMark2","maxlen=12","ID Mark2 Can't Be more than 12 characters");
		hisValidator.addValidation("strOccDesc","maxlen=30","Designation Can't Be more than 30 characters");
		hisValidator.addValidation("strOccOffName", "maxlen=70","Office Name Cannot be More than 70 Characters");
		hisValidator.addValidation("strPinCode", "maxlen=6","Pin Code Cannot be More than 6 digits");
		hisValidator.addValidation("strMobileNo", "req","Mobile No is a Mandatory Field");
		if(document.getElementsByName("strMobileNo")[0].value != "")
		{
		if(document.getElementsByName("strCountry")[0].value=="IND" && !(document.getElementsByName("strMobileNo")[0].value.startsWith("6") || document.getElementsByName("strMobileNo")[0].value.startsWith("7") || document.getElementsByName("strMobileNo")[0].value.startsWith("8") || document.getElementsByName("strMobileNo")[0].value.startsWith("9")))
		{	
			alert("Mobile Number must start with 6,7,8 or 9");
		    return false;
		}
		}
		//hisValidator.addValidation("strMobileNo", "maxlen=12","Mobile No Cannot be More than 12 digits");
		//hisValidator.addValidation("strPhoneNo", "maxlen=13","Phone No Cannot be More than 13 digits");
		hisValidator.addValidation("strOccCity", "maxlen=50","Occupation City Cannot be More than 50 digits");
		hisValidator.addValidation("strOccOffPhNo", "maxlen=30","Occuapation Phone No Cannot be More than 30 digits");
		hisValidator.addValidation("strOccAdd2", "maxlen=50","Occuapation Add2 Cannot be More than 50 digits");
		hisValidator.addValidation("strOccAdd1", "maxlen=50","Occuapation Add1 Cannot be More than 50 digits");
		//hisValidator.addValidation("strOccEmpName", "req", "Employee Name  is a Mandatory Field in Patient Occupation Detail" ); 
		//hisValidator.addValidation("strOccRelation","dontselect=0","Please select a Relation in Patient Occupation Detail");
		hisValidator.addValidation("strOccBasic", "req", "Please Enter Basic Salary in Patient Occupation Detail" );
		//hisValidator.addValidation("strOccDesc", "req", "Please Enter Designation in Patient Occupation Detail" );
		//hisValidator.addValidation("strOccOrgType","dontselect=0","Please select a Orgnisation type in Patient Occupation Detail");
		//hisValidator.addValidation("strOccOffName", "req", "Please Enter office name in Patient Occupation Detail" );
		//hisValidator.addValidation("strOccAdd1", "req", "Please Enter office address in Patient Occupation Detail" );
		//hisValidator.addValidation("strOccCity", "req", "Please Enter City in Patient Occupation Detail" );
		//hisValidator.addValidation("strOccState","dontselect=0","Please select State in Patient Occupation Detail");
		//hisValidator.addValidation("strOccOffPhNo", "req", "Please Enter office phone number in Patient Occupation Detail" );
		hisValidator.addValidation("strDeptNameNewBorn","dontselect=0","Please select Department"); 
		hisValidator.addValidation("strUnitNewBorn","dontselect=0","Please select Unit");
		hisValidator.addValidation("strConsNewBorn","dontselect=0","Please select Consultant");
		//hisValidator.addValidation("strAdmissionType","dontselect=0","Please select Admission Type");
		hisValidator.addValidation("strRemarks", "maxlen=200","Remarks Cannot be More than 200 Characters");
		var retVal = hisValidator.validate(); 
		hisValidator.clearAllValidations();
		if(retVal)
		{
		
			/*if(document.forms[0].strOccRelation.disabled=true)
			{
				document.forms[0].strOccRelation.disabled=false;
				document.forms[0].strOccOrgType.disabled=false;
				document.forms[0].strOccState.disabled=false;
			}*/
				/*if(document.forms[0].strWard.value=="0" )
				{
					alert("Please Select Ward From Pop Up Window");
					return false;
				}*/
				/*if(document.forms[0].strRoomCode.value=="0" )
				{
					alert("Please Select Room From Pop Up Window");
					return false;
				}*/
			if(document.forms[0].strFlag.value==1)
			{
				//alert(document.forms[0].strRoomCode.value);
				document.forms[0].strAmountChargeFinal.value=document.forms[0].strTotalAmount.value;
				//alert(document.forms[0].strAmountChargeFinal.value);
				document.forms[0].strBabyDeptUntName.value=document.forms[0].strDeptNameNewBorn[document.forms[0].strDeptNameNewBorn.selectedIndex].text+"/"+document.forms[0].strUnitNewBorn[document.forms[0].strUnitNewBorn.selectedIndex].text;
				document.forms[0].strGenderName.value=document.forms[0].strGender[document.forms[0].strGender.selectedIndex].text;
				//document.forms[0].strDobTime.value=document.forms[0].strDob.value+" "+document.forms[0].strBirthHour.value+":"+document.forms[0].strBirthMin.value+" "+document.forms[0].strAmPm[document.forms[0].strAmPm.selectedIndex].text;
					/*if(document.forms[0].strOccRelation.disabled=true)
					{
						document.forms[0].strOccRelation.disabled=false;
					}*/
				//if(document.forms[0].setStrMotherCatgrp.value!="2" && document.getElementsByName("strMotherTreatmentCateg").split("^")[1]!="2")
				if(document.forms[0].strMotherTreatmentCateg.value.split("^")[1]!="0")
				{
					alert("Mother's Treatment Category is Beneficiary(Credit/Staff). Baby Can't be admitted in Beneficary Category");
					return false;
				}
				if(document.forms[0].strIsBedSharable.value!=0)
				{
					if((document.forms[0].strWard.value==document.forms[0].strMotherWardCode.value) && (document.forms[0].strRoomCode.value==document.forms[0].strMotherRoomCode.value))
					    document.forms[0].sameBedAsMother.value=1;
					else
						document.forms[0].sameBedAsMother.value=0;
				}
				document.getElementsByName("strDeptNameNewBorn")[0].disabled=false;
				
				
				document.getElementsByName("strUnitNewBorn")[0].disabled=false;
				document.forms[0].hmode.value = "INSERT";
				document.forms[0].strNumberOfChildrenBorn.disabled=false;
				document.forms[0].submit();
				
			}
			else
			{
				alert("There is no vacant bed in this ward.Please select another ward and room");
				return false;
			}
		}
	}
	
}
function getAjaxResponse(res,mode){
		if(mode==1)
		{
			document.getElementById("unitDivId").innerHTML="<select name='strUnitNewBorn' onchange='getWard();' class='browser-default custom-select'>"+res+"</select>";
			
		}
		if(mode==2)
		{
			
			var temp=res.split('^');
			document.forms[0].strRegistrationChargeHidden.value=temp[2];
			createTable(temp[0],temp[1],temp[2]);
			/*document.forms[0].strAdmissionChargeValue.value=temp[0];
			document.forms[0].strAdmissionAdvanceChargeValue.value=temp[1];
			document.forms[0].strRegistrationChargeHidden.value=temp[2];
			document.getElementById("admissionChargeId").innerHTML=temp[0];
			document.getElementById("RegistrationChargeId").innerHTML==temp[2];
			document.getElementById("advanceAdmissionChargeId").innerHTML==temp[1];*/
			
			var glob=0;
			if(document.forms[0].strAdmissionCharge.value=="1")
			{
				glob=parseInt(glob)+parseInt(isNaN(temp[0])?0:temp[0]);
			}
			if(document.forms[0].strAdmissionAdvance.value=="1")
			{
				glob=parseInt(glob)+parseInt(isNaN(temp[1])?0:temp[1]);				
			}
		    if (document.forms[0].strNewBornRegistrationCharge.value=="1")
			{
		    	glob=parseInt(glob)+parseInt(isNaN(temp[2])?0:temp[2]);				
			}
			document.getElementById("TotalID").innerHTML="<img src='/HBIMS/hisglobal/images/INR.png'>"+glob;;
			document.forms[0].strTotalAmount.value=glob;	
			funUnit();
		}
		if(mode==3)
		{
			var isMatch=res;
			if(isMatch=="0")
			{
				//document.forms[0].strSameBedAsMotherFlg.checked=false;
				//document.forms[0].sameBedAsMother.value=0;
				//document.getElementsByName("strSameBedAsMotherFlg")[0].disabled=true;
				//document.getElementsByName("strDeptNameNewBorn")[0].disabled=false;
				//document.getElementsByName("strUnitNewBorn")[0].disabled=false;
				//document.getElementById("wardNameId").innerHTML="";
				//document.getElementById("roomBedId").innerHTML="";
				alert("Same Bed as mother can not be alloted for this baby. Ward Room Criteria does not match for baby !!!");
				//return false;
				WardCriteria="0";
			}
			else if(isMatch=="999")
			{
				alert("Please Enter Baby DOB less than current Date & Time!!!");
				//return false;
				WardCriteria="0";
			}
			else
			{
				//return true;
				WardCriteria="1";
				
				validate1NewBorn();
			}
		}
		if(mode == '4')
		{
			var objEle = document.getElementById("districtSelectBoxDiv");
			objEle.innerHTML ="<select name='strDistrict' class='browser-default custom-select' onclick='onchangeDistrict()'>"+res+"</select>";
		
		}
		if(mode == '5')
		{
			
			//document.forms[0].sameBedAsMother.value=1;
			//alert(res);
			/*if(document.forms[0].strIsBedSharable.value!=0)
			 * 
			{*///alert(res);
				if(res=="0")
				{
					document.forms[0].sameBedAsMother.value=0;
					document.forms[0].strRoom.value="0";
					document.forms[0].strRoomType.value="0";
					document.forms[0].strBedType.value="0";
					var hmode = "GETWARD"; 
					
					
					var url = "NewBornBabyTransBSCNT.cnt?hmode="+hmode+"&wardTypeCode="+document.forms[0].strwardType.value+"&deptCode="+document.forms[0].strDeptNameNewBorn.value+"&sexCode="+document.forms[0].strGender.value+"&treatmentCategory="+document.forms[0].strMotherTreatmentCateg.value.split("^")[0]+'&deptUnitCode='+document.forms[0].strUnitNewBorn.value+'&crNo='+document.forms[0].strCrNo.value;
					//alert(url);
					ajaxFunction(url,"7");
					
				}
				else
				{
					document.forms[0].sameBedAsMother.value=1;

					
					document.forms[0].strRoom.value="0";
					document.forms[0].strRoomType.value="0";
					document.forms[0].strBedType.value="0";
					var hmode = "GETWARDSHARABLE"; 
					
					
					var url = "NewBornBabyTransBSCNT.cnt?hmode="+hmode+"&wardTypeCode="+document.forms[0].strwardType.value+"&deptCode="+document.forms[0].strDeptNameNewBorn.value+"&sexCode="+document.forms[0].strGender.value+"&treatmentCategory="+document.forms[0].strMotherTreatmentCateg.value.split("^")[0]+'&deptUnitCode='+document.forms[0].strUnitNewBorn.value+'&crNo='+document.forms[0].strCrNo.value+'&mothward='+document.forms[0].strMotherWardCode.value;
					//alert(url);
					ajaxFunction(url,"9");
					/*var strward=document.forms[0].strWard;
					strward.selectedIndex=100;
					
					
					alert(strward.options[strward.selectedIndex]);
					alert(strward.options[strward.selectedIndex].text);*/
					
					
					/*document.getElementById("wardNameId").innerHTML=document.forms[0].strWardName.value;
					document.getElementById("roomBedId").innerHTML=document.forms[0].strRoom.value;*/
				//	document.forms[0].strWard.selectedIndex=document.forms[0].strMotherWardCode.value;
					
					//document.forms[0].strWard.options[document.forms[0].strWard.selectedIndex].value=document.forms[0].strMotherWardCode.value;
					//document.forms[0].strWardCode.value=document.forms[0].strMotherWardCode.value;
					//document.forms[0].strRoomCode.selectedIndex=document.forms[0].strMotherRoomCode.value;
					//document.forms[0].strRoomCode.value=document.forms[0].strMotherRoomCode.value;
					//if(document.forms[0].strIsBedSharable.value==1)
					
				}
			/*}
			else
			{
				document.getElementById("wardNameId").innerHTML="";
				document.getElementById("roomBedId").innerHTML="";
				document.forms[0].strWardCode.value="0";
				document.forms[0].strRoomCode.value="0";
				//if(document.forms[0].strIsBedSharable.value==1)
				document.forms[0].sameBedAsMother.value=0;
			}*/
			//	getChargeValue();
		}
		if(mode==6)
		{
			
			document.getElementById("consDivId").innerHTML="<select name='strConsNewBorn' class='browser-default custom-select'>"+res+"</select>";
			getChargeValue();
		
		}
		if(mode==7)
		{
			//alert(res);
			var objEle=document.getElementById("wardNameId");
			objEle.innerHTML='<select name="strWard"  onChange="getRoomNo();" class="browser-default custom-select">'+res+'</select>';
			getRoomNo();
		}
		if(mode==8)
		{
			//alert(res);
			var objEle=document.getElementById("roomBedId");
			objEle.innerHTML='<select name="strRoomCode" onChange=""  class="browser-default custom-select">'+res+'</select>';
			getWardChargeValue();
		}
		if(mode==9)
		{
			//alert(res);
			var objEle=document.getElementById("wardNameId");
			objEle.innerHTML='<select name="strWard"  onChange="getRoomNo();" class="browser-default custom-select">'+res+'</select>';
			getRoomNoSh();
		}
		if(mode==10)
		{
			
			var temp=res.split('^');
			document.forms[0].strRegistrationChargeHidden.value=temp[2];
			createTable(temp[0],temp[1],temp[2]);
			/*document.forms[0].strAdmissionChargeValue.value=temp[0];
			document.forms[0].strAdmissionAdvanceChargeValue.value=temp[1];
			document.forms[0].strRegistrationChargeHidden.value=temp[2];
			document.getElementById("admissionChargeId").innerHTML=temp[0];
			document.getElementById("RegistrationChargeId").innerHTML==temp[2];
			document.getElementById("advanceAdmissionChargeId").innerHTML==temp[1];*/
			
			var glob=0;
			if(document.forms[0].strAdmissionCharge.value=="1")
			{
				glob=parseInt(glob)+parseInt(isNaN(temp[0])?0:temp[0]);
			}
			if(document.forms[0].strAdmissionAdvance.value=="1")
			{
				glob=parseInt(glob)+parseInt(isNaN(temp[1])?0:temp[1]);				
			}
		    if (document.forms[0].strNewBornRegistrationCharge.value=="1")
			{
		    	glob=parseInt(glob)+parseInt(isNaN(temp[2])?0:temp[2]);				
			}
			document.getElementById("TotalID").innerHTML="<img src='/HBIMS/hisglobal/images/INR.png'>"+glob;;
			document.forms[0].strTotalAmount.value=glob;	
			
		}
	}
	
function goRetFuncNB(obj)
{
	if(obj.keyCode==13)
	{
		var hisValidator = new HISValidator("newBornTransBean");  
		hisValidator.addValidation("strCrNo", "req", "CR No. is a Mandatory Field" );
	hisValidator.addValidation("strCrNo","minlen="+document.forms[0].strCrNo.maxLength,"Cr No. must be "+document.forms[0].strCrNo.maxLength+" Digits" );
		var retVal = hisValidator.validate(); 
		if(retVal){
					document.forms[0].hmode.value="GO";
					document.forms[0].submit();
		}else{
			return false;
		}
	}
}
function goFuncNB1(){
	
	var hisValidator = new HISValidator("newBornTransBean");  
	hisValidator.addValidation("strCrNo", "req", "CR No. is a Mandatory Field" );
	//hisValidator.addValidation("strCrNo", "minlen=13", "CR No. must be 13 Digits" );
	hisValidator.addValidation("strCrNo","minlen="+document.forms[0].strCrNo.maxLength,"CR No. must be "+document.forms[0].strCrNo.maxLength+" Digits" );
	
	var retVal = hisValidator.validate(); 
	
	if(retVal){
		
		checkIsGivenBirth();
		
				document.forms[0].hmode.value="GO";
		
				document.forms[0].submit();
				
	}else{
			return false;
		}
		
}
function bedDetailsNB()
{
		var hisValidator = new HISValidator("newBornTransBean");
		var strTreatment="";
		
		hisValidator.addValidation("StrDeptNameNewBorn","dontselect=0","Please Select Department "); 
		hisValidator.addValidation("strUnitNewBorn","dontselect=0","Please Select Unit");
		hisValidator.addValidation("strGender","dontselect=0","Please Select Gender");
		
		var retVal = hisValidator.validate();
		
		
		if(retVal)
		{
			
			if(document.forms[0].strMotherTreatmentCateg.value=="0")
			{
				strTreatment=document.forms[0].strTreatmentCategoryCode.value;
			}
			else
			{
				strTreatment=document.forms[0].strMotherTreatmentCateg.value;
			}
			
			
			var hmode = "BEDSTATUS";  
			var url='NewBornBabyTransBSCNT.cnt?hmode='+hmode+'&wardTypeCode='+document.forms[0].strMotherWardTypeCode.value+'&roomTypeCode='+document.forms[0].strMotherRoomTypeCode.value+
	    	'&wardCode='+document.forms[0].strMotherWardCode.value+'&bedTypeCode='+document.forms[0].strMotherBedTypeTypeCode.value
	    	+'&deptCode='+document.forms[0].strDeptNameNewBorn.value+'&crNo='+document.forms[0].strCrNo.value+'&deptUnitCode='+document.forms[0].strUnitNewBorn.value+'&deptName='+document.forms[0].strDeptNameNewBorn[document.forms[0].strDeptNameNewBorn.selectedIndex].text+'&roomCode='+document.forms[0].strRoomCode.value+"&treatmentCategory="+strTreatment+"&sexCode="+document.forms[0].strGender.value;
			var featuresList = "width=780,height=390,ALIGN=CENTER,left=300,top=300,scrollbars=yes"
			 myWindow = window.open(createFHashAjaxQuery(url),'popupWindow',featuresList); 
			myWindow.focus();
		}
}

function viewN()
{
	if(document.forms[0].strOnlineOrNot.value==2)//Offline
	{
		document.getElementById("searchPatImg").style.display="none";
	}
	if(document.forms[0].strOnlineOrNot.value==1)//Online
	{
		document.getElementById("searchPatImg").style.display="";
	}
	if(document.getElementsByName("strPatStatusCode")[0].value==12)
	{
			document.forms[0].strCrNo.value="";
			//document.getElementById("PatientOccId").style.display="none";
			document.getElementById("PatientOccDtl").style.display="none";
			//document.getElementById("newModificationId").style.display="none";
			document.getElementById("newAddressModiId").style.display="none";
			//document.getElementById("id5").style.display="none";
			document.getElementById("wardDivId").style.display="none";
			document.getElementById("admissionId").style.display="none";
			document.getElementById("registrationId").style.display="none";
			document.getElementById("TotalID").style.display="none";
			document.getElementById("patientCrEdId").style.display="block";
			document.getElementById("patientCrId").style.display="none";
			document.getElementById("admissionAdvanceId").style.display="none";
			document.getElementById("chargesDtlId").style.display="none";
			document.forms[0].strCrNo.focus();
	}
	if(checkCrdef(document.getElementById("strCrNoId"))==false && document.forms[0].strCrNo.value!="")//complete cr enetered
	{
		//document.getElementById("id5").style.display="none";
		document.getElementById("wardDivId").style.display="block";
		//document.getElementById("newModificationId").style.display="block";
		document.getElementById("newAddressModiId").style.display="";
		//document.getElementById("minusAddModiId").style.display="";
		//document.getElementById("plusAddModiId").style.display="none";
		//document.getElementById("PatientOccId").style.display="block";
		document.getElementById("patientCrEdId").style.display="none";
		document.getElementById("patientCrId").style.display="none";
		//document.getElementById("PatientOccId").style.display="block";
		document.getElementById("mandCRId").style.display="none";
		//document.getElementById("plusMomId").style.display="";
		//document.forms[0].strOccRelation.value="3";
		var tempTotal=parseInt(document.forms[0].strAdmissionChargeValue.value)+parseInt(document.forms[0].strNewBornRegistrationChargeVal.value);
		
		var tempStr = "";
		var colspan = "1";
		var flag = 0;
		var innerHtmlStr = "";
		var charge="";
		var o= document.getElementById("admissionId");
		if (document.forms[0].strAdmissionCharge.value=="1" || document.forms[0].strNewBornRegistrationCharge.value=="1" ||
		document.forms[0].strAdmissionAdvance.value=="1")
		{
		
			if (document.forms[0].strAdmissionCharge.value=="1")
			{
			
				tempStr = "<td width='25%' class='LABEL'>Admission Charges</td>";
				tempStr = tempStr + "<td width='25%' class='CONTROL'><input type='hidden' name='strAdmissionChargeValue' value='"+document.forms[0].strAdmissionChargeHidden.value+"'><div id='admissionChargeId'><img src='/HBIMS/hisglobal/images/INR.png'>"+document.forms[0].strAdmissionChargeHidden.value+"</div></td>";
				flag = 1;
			}
			else
				colspan = "2";
				
			if (document.forms[0].strNewBornRegistrationCharge.value=="1")
			{
			//alert(tempStr);
				tempStr += "<td width='25%' class='LABEL'>Registration Charges</td>";
				tempStr += "<td width='25%' class='CONTROL' colspan = " + colspan + "><input type='hidden' name='strNewBornRegistrationChargeVal' value='"+document.forms[0].strRegistrationChargeHidden.value+"'><div id='RegistrationChargeId'><img src='/HBIMS/hisglobal/images/INR.png'>"+document.forms[0].strRegistrationChargeHidden.value+"</div></td>";
				flag = 1;
			//	alert(tempStr);
			}
			
			if (flag == 1)
			{
				innerHtmlStr="<table class='TABLEWIDTH' align='center' cellspacing='1px'><tr>" + tempStr + "</tr></table>";
			}
			//
			colspan = "1";
			tempStr = "";
			if (document.forms[0].strAdmissionAdvance.value=="1")
			{
			//alert("4");
				tempStr = "<td  width='25%' class='LABEL'>Advance Charge</td>";
				tempStr = tempStr + "<td  width='25%' class='CONTROL'><input type='hidden' name='strAdmissionAdvanceChargeValue' value='"+document.forms[0].strAdvanceAmount.value+"'><div id='advanceAdmissionChargeId'><img src='/HBIMS/hisglobal/images/INR.png'>"+document.forms[0].strAdvanceAmount.value+"</div></td>";
				flag = 2;
			}
			else
				colspan = "2";
				
			if(flag > 0)
			{
				tempStr += "<td class='LABEL' width='25%'>Total Charge</td>";
				tempStr += "<td class='CONTROL'  width='25%' colspan=" + colspan + "><font color='red'><div id='TotalID' ></div></font></td>";
				
				innerHtmlStr += "<table class='TABLEWIDTH' align='center' cellspacing='1px'><tr>" + tempStr + "</tr></table>";
				o.innerHTML = innerHtmlStr;
				o.style.display="block";
			}
			var glob=0;
			if(document.forms[0].strAdmissionCharge.value=="1")
			{
				glob=parseInt(glob)+parseInt(isNaN(document.forms[0].strAdmissionChargeValue.value)?0:document.forms[0].strAdmissionChargeValue.value);
			}
			if(document.forms[0].strAdmissionAdvance.value=="1")
			{
				glob=parseInt(glob)+parseInt(isNaN(document.forms[0].strAdvanceAmount.value)?0:document.forms[0].strAdvanceAmount.value);				
			}
			if (document.forms[0].strNewBornRegistrationCharge.value=="1")
			{				
				glob=parseInt(glob)+parseInt(isNaN(document.forms[0].strRegistrationChargeHidden.value)?0:document.forms[0].strRegistrationChargeHidden.value);
			}
		    document.getElementById("TotalID").innerHTML="<img src='/HBIMS/hisglobal/images/INR.png'>"+glob;
			document.forms[0].strTotalAmount.value=glob;
		}
		else
			o.innerHTML = "";
		
		document.getElementById("motherDelDetUpdate").style.display="none";
		document.getElementById("patAdmLink").style.display="none";
		document.getElementById("goBox").style.display = 'none';
		document.getElementById("savebutton").style.display = '';
		document.getElementById("printbutton").style.display = 'none';
		$('#viewportDiv').addClass('vpinside');


	}
	else
	{
		//SetCursorToTextEnd('strCrNoId');
		SetSelectedCrNo();
		//document.getElementById("PatientOccId").style.display="none";
		document.getElementById("PatientOccDtl").style.display="none";
		//document.getElementById("newModificationId").style.display="none";
		document.getElementById("newAddressModiId").style.display="none";
		//document.getElementById("id5").style.display="none";
		document.getElementById("wardDivId").style.display="none";
		document.getElementById("admissionId").style.display="none";
		//document.getElementById("registrationId").style.display="none";
		//document.getElementById("TotalID").style.display="none";
		document.getElementById("patientCrEdId").style.display="block";
		document.getElementById("patientCrId").style.display="none";
		//document.getElementById("admissionAdvanceId").style.display="none";
		document.getElementById("chargesDtlId").style.display="none";
		//document.forms[0].strCrNo.focus();
		document.getElementById("mandCRId").style.display="";
	}
	document.getElementsByName("strDeptNameNewBorn")[0].disabled=true;
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
		document.getElementById("wardNameId").innerHTML="";
		document.getElementById("roomBedId").innerHTML="";
		document.forms[0].strWardCode.value="0";
		document.forms[0].strRoomCode.value="0";
		var hmode = "GETUNIT"; 
		var url = "NewBornBabyTransBSCNT.cnt?hmode="+hmode+"&deptCode="+document.forms[0].strDeptNameNewBorn.value;
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
function viewX()
{
	document.getElementById("plusMomId").style.display="none";
	document.getElementById("minusMomId").style.display="";
	document.getElementById("momDtlId").style.display="";
}
function viewY()
{
	document.getElementById("plusMomId").style.display="";
	document.getElementById("minusMomId").style.display="none";
	document.getElementById("momDtlId").style.display="none";
}
function clearRecordNB()
{
	/*	document.getElementById("errId").innerHTML="";
		document.getElementById("nrmId").innerHTML="";
		document.getElementById("PatientOccId").style.display="none";
		document.getElementById("PatientOccDtl").style.display="none";
		document.getElementById("newModificationId").style.display="none";
		document.getElementById("newAddressModiId").style.display="none";
		document.getElementById("id5").style.display="none";
		document.getElementById("wardDivId").style.display="none";
		document.getElementById("admissionId").style.display="none";
		//document.getElementById("registrationId").style.display="none";
		document.getElementById("TotalID").style.display="none";
		document.getElementById("patientCrEdId").style.display="block";
		document.getElementById("patientCrId").style.display="none";
		//document.getElementById("admissionAdvanceId").style.display="none";
		document.getElementById("chargesDtlId").style.display="none";
		document.forms[0].strCrNo.value="";
		document.forms[0].strCrNo.focus();*/
		//document.getElementById("PatientOccId").style.display="none";
		//document.getElementById("PatientOccDtl").style.display="none";
		
	    document.forms[0].strCrNo.value = "";
	    document.forms[0].strSaveFlag.value="";
		document.forms[0].strPatientCrNo.value="";
		document.forms[0].strCaseSheetNo.value="";
		document.forms[0].hmode.value="CLEAR";
		//document.forms[0].strCrNo.value="";
	    document.forms[0].submit();
	
}
function funUnit()
{
	if(document.forms[0].strIsBedSharable.value!=0)
	{
		var hmode = "GETSHARABLE"; 
		var url = "NewBornBabyTransBSCNT.cnt?hmode="+hmode+"&deptCode="+document.forms[0].strDeptNameNewBorn.value+"&unitcode="+document.forms[0].strUnitNewBorn.value+"&wardCode="+document.forms[0].strMotherWardCode.value+"&roomCode="+document.forms[0].strMotherRoomCode.value+"&bedCode="+document.forms[0].strBedCode.value;
		//alert(url);
		ajaxFunction(url,"5");
	}
	else
	{
		document.forms[0].sameBedAsMother.value=0;
		document.forms[0].strRoom.value="0";
		document.forms[0].strRoomType.value="0";
		document.forms[0].strBedType.value="0";
		var hmode = "GETWARD"; 
	
	
		var url = "NewBornBabyTransBSCNT.cnt?hmode="+hmode+"&wardTypeCode="+document.forms[0].strwardType.value+"&deptCode="+document.forms[0].strDeptNameNewBorn.value+"&sexCode="+document.forms[0].strGender.value+"&treatmentCategory="+document.forms[0].strMotherTreatmentCateg.value.split("^")[0]+'&deptUnitCode='+document.forms[0].strUnitNewBorn.value+'&crNo='+document.forms[0].strCrNo.value;
		//alert(url);
		ajaxFunction(url,"7");
	}
	
		/*document.getElementById("wardNameId").innerHTML="";
		document.getElementById("roomBedId").innerHTML="";
		document.forms[0].strWardCode.value="0";
		document.forms[0].strRoomCode.value="0";*/
}
function hour(obj, eve){
 	 	if(eve.keyCode != 13){
 		if (parseInt(obj.value ) > 12 || parseInt(obj.value) < 00 ){
     		alert("hours should be in between 00-12");
     		obj.value="";
 	 		}
 	 		else
 	 		{
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
 	 		}
 		}
}
function focus(obj)
{
 		document.forms[0].strBirthMin.focus();
}
function cancelPage(){
	
	document.forms[0].hmode.value="INITIALPAGE";
	document.forms[0].submit();
}
function getChargeValue()
{
	var hmode = "GETADVANCEAMOUNT"; 
	
	var url = "NewBornBabyTransBSCNT.cnt?hmode="+hmode+"&wardCode="+0+"&teatmentCategory="+document.forms[0].strMotherTreatmentCateg.value.split("^")[0]+"&unit_code="+document.forms[0].strUnitNewBorn.value;
	
	ajaxFunction(url,"2");
	
}
function getWardChargeValue()
{
	var hmode = "GETADVANCEAMOUNT"; 
	
	var url = "NewBornBabyTransBSCNT.cnt?hmode="+hmode+"&wardCode="+document.forms[0].strWard.value+"&teatmentCategory="+document.forms[0].strMotherTreatmentCateg.value.split("^")[0]+"&unit_code="+document.forms[0].strUnitNewBorn.value;
	
	ajaxFunction(url,"10");
	
}
function createTable(ob1,ob2,ob3)
{
	var tempStr = "";
	var colspan = "1";
	var flag = 0;
	var innerHtmlStr = "";
	var charge="";
	var o= document.getElementById("admissionId");
	if (document.forms[0].strAdmissionCharge.value=="1" || document.forms[0].strNewBornRegistrationCharge.value=="1" ||
		document.forms[0].strAdmissionAdvance.value=="1")
		{
			if (document.forms[0].strAdmissionCharge.value=="1")
			{
				tempStr = "<td width='25%' class='LABEL'>Admission Charges</td>";
				tempStr = tempStr + "<td width='25%' class='CONTROL'><input type='hidden' name='strAdmissionChargeValue' value='"+ob1+"'><div id='admissionChargeId'><img src='/HBIMS/hisglobal/images/INR.png'>"+ob1+"</div></td>";
				flag = 1;
			}
			else
				colspan = "2";
				
			if (document.forms[0].strNewBornRegistrationCharge.value=="1")
			{
				tempStr += "<td width='25%' class='LABEL'>Registration Charges</td>";
				tempStr += "<td width='25%' class='CONTROL' colspan = " + colspan + "><input type='hidden' name='strNewBornRegistrationChargeVal' value='"+ob3+"'><div id='RegistrationChargeId'><img src='/HBIMS/hisglobal/images/INR.png'>"+ob3+"</div></td>";
				flag = 1;
			}
			
			if (flag == 1)
			{
				innerHtmlStr="<table class='TABLEWIDTH' align='center' cellspacing='1px'><tr>" + tempStr + "</tr></table>";
			}
			colspan = "1";
			tempStr = "";
			if (document.forms[0].strAdmissionAdvance.value=="1")
			{
				tempStr = "<td  width='25%' class='LABEL'>Advance Charge</td>";
				tempStr = tempStr + "<td  width='25%' class='CONTROL'><input type='hidden' name='strAdmissionAdvanceChargeValue' value='"+ob2+"'><div id='advanceAdmissionChargeId'><img src='/HBIMS/hisglobal/images/INR.png'>"+ob2+"</div></td>";
				flag = 2;
			}
			else
				colspan = "2";				
			if(flag > 0)
			{
				tempStr += "<td class='LABEL' width='25%'>Total Charge</td>";
				tempStr += "<td class='CONTROL'  width='25%' colspan=" + colspan + "><font color='red'><div id='TotalID' > </div></font></td>";
				
				innerHtmlStr += "<table class='TABLEWIDTH' align='center' cellspacing='1px'><tr>" + tempStr + "</tr></table>";
				o.innerHTML = innerHtmlStr;
				o.style.display="block";
			}
			
		}
	else
	  o.innerHTML = "";
}
function showOnline(){
	document.getElementById("plusOnlineId").style.display="none";
	document.getElementById("minusOnlineId").style.display="block";
	document.getElementById("onlineDetailsId").style.display="block";
}

function hideOnline(){
	document.getElementById("plusOnlineId").style.display="";
	document.getElementById("minusOnlineId").style.display="none";
	document.getElementById("onlineDetailsId").style.display="none";
}

function selectOnlineBabyList(these){
	document.getElementById("MotherOnlineId").style.display="block";
	if(document.getElementsByName("strCrNo")[0].value!="" && document.forms[0].strOnlineOrNot.value=="1"){
		try{
			if(!these)
				these=document.getElementsByName("strOnlineBaby")[0];
			if(!these)
				throw "No baby";
		}catch(_Err){
			document.getElementById("errId").innerHTML="No baby found for this Patient in database.";
			document.forms[0].hmode.value="unspecified";
			document.getElementsByName("strCrNo")[0].value="";
			document.getElementById("MotherOnlineId").style.display="none";
			return;
		}
		var strSelectedData = these.value.split("^");
		var strGenderCode = strSelectedData[0];
		var strBirthDate = strSelectedData[1].split("/")[0];
		var strTimeH = strSelectedData[1].split("/")[1].split(":")[0];
		var strTimeM = strSelectedData[1].split("/")[1].split(":")[1].split(" ")[0];
		var strTimeAM = strSelectedData[1].split("/")[1].split(":")[1].split(" ")[1];
		var strStrAMPM = strTimeAM;
		if(strTimeAM=="AM")
			strTimeAM = 1;
		else if(strTimeAM=="PM")
			strTimeAM = 2;
		
		document.getElementsByName("strDob")[0].value=strBirthDate;
		document.getElementsByName("strGender")[0].value=strGenderCode;
		document.getElementsByName("strBirthHour")[0].value=strTimeH;
		document.getElementsByName("strBirthMin")[0].value=strTimeM;
		document.getElementsByName("strAmPm")[0].value=strTimeAM;
		
		
		document.getElementsByName("strSlNo")[0].value=strSelectedData[4];
		document.getElementsByName("strGravidaNo")[0].value=strSelectedData[3];
		
		
		document.getElementById("birthTimeId").style.display="none";
		document.getElementById("birthDateId").style.display="none";
		document.getElementById("birthGenderId").style.display="none";
		
		document.getElementById("birthTimeOnlineId").style.display="block";
		document.getElementById("birthDateOnlineId").style.display="block";
		document.getElementById("birthGenderOnlineId").style.display="block";
		
		
		document.getElementById("birthTimeOnlineId").innerHTML=strTimeH+":"+strTimeM+" "+strStrAMPM;
		document.getElementById("birthDateOnlineId").innerHTML=strBirthDate;
		document.getElementById("birthGenderOnlineId").innerHTML=strSelectedData[2];
	
	}
}
function moveToNextBox(elem, evt)
{
	if(elem.value.length==1)
	{
		elem.value="0"+elem.value;
	}
	if(elem.name=="strBirthHour")
	{
	if(elem.value.length==2)
		document.getElementsByName("strBirthMin")[0].focus();
	}
}
function checkFlag()
{
	//if(document.forms[0].strSameBedAsMotherFlg.checked==false)
	 bedDetailsNB(); 
}
function roomBedStatus()
{
if(document.forms[0].strSameBedAsMotherFlg.checked==false)
{
	document.forms[0].sameBedAsMother.value=0;
	document.getElementsByName("strDeptNameNewBorn")[0].disabled=false;
	document.getElementsByName("strUnitNewBorn")[0].disabled=false;
}
else
{
	document.forms[0].sameBedAsMother.value=1;
	//document.getElementsByName("strDeptNameNewBorn")[0].disabled=true;
	//document.getElementsByName("strUnitNewBorn")[0].disabled=true;
	document.forms[0].hmode.value="SAMEBEDASMOTHER";
	document.forms[0].submit();
}
}
function checkBedIsSharable()
{
var strIsBedSharable=document.forms[0].strIsBedSharable.value;

	if(strIsBedSharable==0)
	{
		document.forms[0].strSameBedAsMotherFlg.checked=false;
		document.forms[0].sameBedAsMother.value=0;
		document.getElementsByName("strSameBedAsMotherFlg")[0].disabled=true;
		//document.getElementsByName("strDeptNameNewBorn")[0].disabled=false;
		document.getElementsByName("strUnitNewBorn")[0].disabled=false;
	}
}
function matchWardRoomCriteria()
{
	var hisValidator = new HISValidator("newBornTransBean");
	if(document.forms[0].strCrNo.value=="")
	{
		alert("CR No. is Mandatory Field");
		document.forms[0].strCrNo.focus();
		return;
	}
	else
	{
		validateThroughRegExp(document.forms[0].strCrNo,1);
		//var strBirthHour=document.forms[0].strBirthHour.value;
		//var strBirthMin=document.forms[0].strBirthMin.value;
		//var strAmPm=document.forms[0].strAmPm.value;
		var strDob=document.forms[0].datetime.value;
		var strFatherName=document.forms[0].strFatherName.value;
		if(strFatherName ==" ")
			document.forms[0].strFatherName.value="";
		//alert(strFatherName);
		
/*		hisValidator.addValidation("strCrNo","req","CR No. is Mandatory Field");
		hisValidator.addValidation("strBirthMin","req","Time is Mandatory Field"); 
		hisValidator.addValidation("strBirthHour","req","Time is Mandatory Field");   
		hisValidator.addValidation("strFatherName","req","father name is Mandatory Field"); 
		hisValidator.addValidation("strGender","dontselect=0","Please select Gender");
		hisValidator.addValidation("strDeptNameNewBorn","dontselect=0","Please select Department"); 
		hisValidator.addValidation("strUnitNewBorn","dontselect=0","Please select Unit");
*/		hisValidator.addValidation("strDeptNameNewBorn","dontselect=0","Please select Department"); 
        hisValidator.addValidation("strUnitNewBorn","dontselect=0","Please select Unit");
		var retVal = hisValidator.validate(); 
		hisValidator.clearAllValidations();
		if(retVal)
		{
				if(document.forms[0].strWard.value=="0" )
				{
					alert("Please Select Ward From Pop Up Window");
					return false;
				}
				if(document.forms[0].strRoomCode.value=="0" )
				{
					alert("Please Select Room From Pop Up Window");
					return false;
				}
			var wardCode=document.forms[0].strWardCode.value;
			var roomNo=document.forms[0].strRoomCode.value;
			var sexCode=document.forms[0].strGender.value;
			var strTreatment="";
			if(document.forms[0].strMotherTreatmentCateg.value=="0")
			{
				strTreatment=document.forms[0].strTreatmentCategoryCode.value;
			}
			else
			{
				strTreatment=document.forms[0].strMotherTreatmentCateg.value;
			}
			//document.forms[0].strDob.value=document.forms[0].datetime.value
			
			//var strDobTime=document.forms[0].strDob.value.split(" ")[0]+" "+document.forms[0].strBirthHour.value+":"+document.forms[0].strBirthMin.value+" "+document.forms[0].strAmPm[document.forms[0].strAmPm.selectedIndex].text;
			//var strDobTime=document.forms[0].strDob.value.split(" ")[0];
			//alert(document.forms[0].datetime.value);
			//datetimepicker1
			document.forms[0].strDobTime.value=document.forms[0].datetime.value;
			validate1NewBorn();
			
			/*var hmode = "MATCHCRITERIA"; 
			var url = "NewBornBabyTransCNT.cnt?hmode="+hmode+"&wardCode="+wardCode+"&roomNo="+roomNo+"&strTreatment="+strTreatment+"&sexCode="+sexCode+"&dob="+strDobTime;
			ajaxFunction(url,"3");*/
		}
	}
}
function checkIsGivenBirth()
{
	var strIsGivenBirth=document.forms[0].strIsGivenBirth.value;
	//var strNumberOfChildrenBorn=document.forms[0].strNumberOfChildrenBorn.value;
	if(strIsGivenBirth==1)
	{
		var conf=confirm("Mother Already Given Birth To a Child. Do You Want To Continue ? ");
		if(!conf)
		{
			clearRecordNB();
		}
		/*else
		{
			if((9-strNumberOfChildrenBorn)>=strIsGivenBirth)
			{
				
			}
		}*/
	}
}
// function to open 'updateMotherDeliveryDetails.jsp'
function openUpdateMotherDelDet()
{
	//alert("called..");
	document.forms[0].hmode.value="UPDATEMOTHERDETAILS";
					
}
/*function onchangeState()
{
	var cbo = document.getElementsByName("strState")[0];
	var text = "";
	if(cbo.value!="0")
		text = cbo.options[cbo.selectedIndex].text;
	document.getElementsByName("strStateName")[0].value = text;
	
}
*/
function onchangeState()
{   var cboCountry = document.getElementsByName("strCountry")[0];
	var cbo = document.getElementsByName("strState")[0];
	var cboDist = document.getElementsByName("strDistrict")[0];
  	if(cboCountry.value == "IND"){
  		
	var text = "";
	if(cbo.value!="0")
	text = cbo.options[cbo.selectedIndex].text;
	document.getElementsByName("strStateName")[0].value = text;
	var hmode = "GETDISTRICT"; 
	//var url='PatientAdmissionTransCNT.cnt?hmode='+hmode+'&stateCode='+cbo.value;
	var url='NewBornBabyTransBSCNT.cnt?hmode='+hmode+'&stateCode='+cbo.value;
	ajaxFunction(url,"4");	
/*	var texts = cboDist.options[cbo.selectedIndex].text;
	document.getElementsByName("strDistrictName")[0].value = texts;*/
	


  	}
	
	}
function onchangeCountry()
{
	var cbo = document.getElementsByName("strCountry")[0];

		if(cbo.value != "IND")
		{ 			
			document.getElementById("stateTextBoxDiv").style.display=""; 
			document.getElementById("stateComboDiv").style.display="none"; 
			
			
			document.getElementById("districtTextBoxDiv").style.display="";
	  		document.getElementById("districtSelectBoxDiv").style.display="none";


			
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
function removeSpace()
{
	/*if(document.forms[0].strSaveFlag.value!='1')
	{
		var el = document.forms[0].strFatherName.value;
		if(el==" "){
			el=null;
		}
	}*/
}
function getCons()
{
	var hmode = "GETCONS"; 
	
	var url = "NewBornBabyTransBSCNT.cnt?hmode="+hmode+"&unit_code="+document.forms[0].strUnitNewBorn.value;
	
	
	ajaxFunction(url,"6");
	
}
function getWard()
{
	document.forms[0].strRoom.value="0";
	document.forms[0].strRoomType.value="0";
	document.forms[0].strBedType.value="0";
	var hmode = "GETWARD"; 
	
	
	var url = "NewBornBabyTransBSCNT.cnt?hmode="+hmode+"&wardTypeCode="+document.forms[0].strwardType.value+"&deptCode="+document.forms[0].strDeptNameNewBorn.value+"&sexCode="+document.forms[0].strGender.value+"&treatmentCategory="+document.forms[0].strMotherTreatmentCateg.value.split("^")[0]+'&deptUnitCode='+document.forms[0].strUnitNewBorn.value+'&crNo='+document.forms[0].strCrNo.value;
	//alert(url);
	ajaxFunction(url,"7");
}
function getRoomNo()
{
	var hmode = "GETROOM"; 
	var url = "NewBornBabyTransBSCNT.cnt?hmode="+hmode+"&roomTypeCode="+document.forms[0].strRoomType.value+"&wardCode="+
	document.forms[0].strWard.value+'&treatmentCategCode='+document.forms[0].strMotherTreatmentCateg.value.split("^")[0]+'&ageCode=1&sexCode='+document.forms[0].strGender.value+'&age=1&crNo='+document.forms[0].strCrNo.value+'&deptUnitCode='+document.forms[0].strUnitNewBorn.value;
	//alert(url);
	ajaxFunction(url,"8");
}
function getRoomNoSh()
{
	var hmode = "GETROOMSH"; 
	var url = "NewBornBabyTransBSCNT.cnt?hmode="+hmode+"&roomTypeCode="+document.forms[0].strRoomType.value+"&wardCode="+
	document.forms[0].strWard.value+'&treatmentCategCode='+document.forms[0].strMotherTreatmentCateg.value.split("^")[0]+'&ageCode=1&sexCode='+document.forms[0].strGender.value+'&age=1&crNo='+document.forms[0].strCrNo.value+'&deptUnitCode='+document.forms[0].strUnitNewBorn.value+"&mothroom="+
	document.forms[0].strMotherRoomCode.value;
	//alert(url);
	ajaxFunction(url,"8");
}
function openPatAdm()
{
	//alert("called..");
	document.forms[0].hmode.value="PATADMISSION";
	document.forms[0].submit();
					
}
function checkMsg() {
	var err=document.getElementById("errID");
	var nor=document.getElementById("normalMsg");
	var warn=document.getElementById("wrnID");
	if (err.innerHTML != "") {
		
		err.innerHTML="<i class='fas fa-exclamation-circle'></i>"+"&nbsp;&nbsp;&nbsp;<strong>Error!</strong>&nbsp;&nbsp;"+err.innerHTML+"<button type='button' class='close' data-dismiss='alert'>&times;</button>";
		err.style.display = "";
		
	}
	if (nor.innerHTML != "") {
		nor.innerHTML="<i class='far fa-thumbs-up'></i>"+"&nbsp;&nbsp;&nbsp;<strong>Success!</strong>&nbsp;&nbsp;"+nor.innerHTML+"<button type='button' class='close' data-dismiss='alert'>&times;</button>";
		nor.style.display = "";
	}
	if (warn.innerHTML != "") {
		warn.innerHTML="<i class='fas fa-bell'></i>"+"&nbsp;&nbsp;&nbsp;<strong>Success!</strong>&nbsp;&nbsp;"+warn.innerHTML+"<button type='button' class='close' data-dismiss='alert'>&times;</button>";
		warn.style.display = "";
	}
}
function replaceAt(s, n, t,skipp) {
    return s.substring(0, n) + t + s.substring(n + skipp);
}

function modalSlipPrint()
{
	printElement(document.getElementById("printableSlip"));
}
function printElement(elem) {
    var domClone = elem.cloneNode(true);
    
    var $printSection = document.getElementById("printSection");
    
    if (!$printSection) {
        var $printSection = document.createElement("div");
        $printSection.id = "printSection";
        document.body.appendChild($printSection);
    }
    
    $printSection.innerHTML = "";
    $printSection.appendChild(domClone);
   // window.print();
}


