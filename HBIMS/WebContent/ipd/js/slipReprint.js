var child = null;
var popIndex = 0;
var gblCntrlObj = null;
function validate1()
{
	/*var hisValidator = new HISValidator("slipReprintTransBean"); 
	hisValidator.addValidation("strCrNo","req","CR No. is mandatory field");
	//hisValidator.addValidation("strFatherName","req","Father Name is mandatory field");
	if((document.forms[0].strFatherName.value=="" &&  !document.forms[0].strSpouseName.value=="") || (!document.forms[0].strFatherName.value=="" &&  document.forms[0].strSpouseName.value=="") || (!document.forms[0].strFatherName.value=="" &&  !document.forms[0].strSpouseName.value==""))
	{
			//alert("Father/Spouse Name is Mandatory Field");
	}
	else
	{
			alert("Father/Spouse Name is Mandatory Field");
			return;
	}
	//hisValidator.addValidation("strMotherTreatmentCateg","dontselect=0","Please select Treatment Category "); 
	hisValidator.addValidation("strReligion","dontselect=0","Please select Religon");
	//hisValidator.addValidation("strStreet", "req", "Street Name  is a Mandatory Field." );
	hisValidator.addValidation("strCity", "req", "City Name  is a Mandatory Field." );
	//hisValidator.addValidation("strDistrict", "req", "District Name  is a Mandatory Field in New Born Baby Form" );
	hisValidator.addValidation("strState","dontselect=0","Please select State");
	hisValidator.addValidation("strCountry","dontselect=0","Please select Country");
	
	// Consultant Selection Validation (updated at 31-Mar-2011 by Pragya)
	hisValidator.addValidation("strConsultantCode","dontselect=0","Please select Consultant");
	
	if(document.getElementsByName("strOccIsDept")[0].checked)
	{
		hisValidator.addValidation("strOccRelation","dontselect=0","Please Select Relation in Patient Occupation Detail");
		hisValidator.addValidation("strOccEmpName", "req", "Dependent  On  is a Mandatory Field in Patient Occupation Detail" ); 
	}
	//hisValidator.addValidation("strOccDesc", "req", "Please Enter Designation in Patient Occupation Detail" );
	//hisValidator.addValidation("strOccOrgType","dontselect=0","Please select a Orgnisation type in Patient Occupation Detail");
	//hisValidator.addValidation("strOccOffName", "req", "Please Enter office name in Patient Occupation Detail" );
	//hisValidator.addValidation("strOccAdd1", "req", "Please Enter office address in Patient Occupation Detail" );
	hisValidator.addValidation("strOccCity", "req", "Please Enter city in Patient Occupation Detail" );
	hisValidator.addValidation("strOccState","dontselect=0","Please select state in Patient Occupation Detail");
	//hisValidator.addValidation("strOccOffPhNo", "req", "Please Enter office phone number in Patient Occupation Detail" );
	var retVal = hisValidator.validate(); */
	var retVal =true;
	//hisValidator.clearAllValidations();
	if(retVal)
	{
	/**if(document.forms[0].strMsApprovalStatus.value==0)
		{
			alert("!!!Msapproval required!!!");
			return false;
		}*/
		/*if(document.forms[0].strOccRelation.disabled=true)
		{
			document.forms[0].strOccRelation.disabled=false;
			//document.forms[0].strOccOrgType.disabled=false;
			//	document.forms[0].strOccState.disabled=false;
		}*/
			document.forms[0].hmode.value = "INSERT";
			document.forms[0].submit();
			
		
	}
	
	
}
function getAjaxResponse(res,mode){
		if(mode==1)
		{
			document.getElementById("unitDivId").innerHTML="<select name='strUnitNewBorn'>"+res+"</select>";
			
		}
		
	}
	
function goRetFunc(obj)
{
	var flag=validateData(obj,5);
	if(flag){
		if(obj.keyCode==13)
		{
			var hisValidator = new HISValidator("slipReprintTransBean");  
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
function goFunc(){
	
	var hisValidator = new HISValidator("slipReprintTransBean");  
	hisValidator.addValidation("strCrNo", "req", "CR No. is a Mandatory Field" );
	hisValidator.addValidation("strCrNo","minlen="+document.forms[0].strCrNo.maxLength,"CR No. must be "+document.forms[0].strCrNo.maxLength+" Digits" );
	//hisValidator.addValidation("strCrNo", "minlen=13", "Cr No. must be 13 Digits" );
	var retVal = hisValidator.validate(); 
	if(retVal){
				document.forms[0].hmode.value="GO";
				document.forms[0].submit();
				
		}else{
		
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
			break;
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
		var deptcode="";
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
		
		var url='SlipReprintTransCNT.cnt?hmode='+hmode+'&wardTypeCode='+document.forms[0].strWardTypeCode.value+'&roomTypeCode='+document.forms[0].strRoomTypeCode.value+
	    '&wardCode='+document.forms[0].strWardCode.value+'&bedTypeCode='+document.forms[0].strBedTypeCode.value
	    +'&deptCode='+deptcode+'&crNo='+document.forms[0].strCrNo.value+'&deptUnitCode='+deptUnitCode+'&msApprovalStatus='+document.forms[0].strMsApprovalStatus.value+'&msApprovalFlag='+document.forms[0].strMsApprovalFlag.value+'&wardName='+document.forms[0].strWardName.value+'&roomCode='+document.forms[0].strRoomCode.value+'&deptName='+deptName+'&treatmentCategCode='+document.forms[0].strTreatmentCategoryCode.value+'&ageCode='+document.forms[0].strAgeUnit.value+'&sexCode='+ document.forms[0].strSexCode.value+'&age='+document.forms[0].strAge.value;
	  
	   //alert("treatment Category Code2"+document.forms[0].strTreatmentCategoryCode.value);
		var featuresList = "width=380,height=390,ALIGN=CENTER,left=300,top=300,scrollbars=yes"
		 myWindow = window.open(url,'popupWindow',featuresList); 
		myWindow.focus();
		
		

}
function view()
{
	if(document.forms[0].strCrNo.value!="")
	{
		if(checkCrdef(document.getElementById("strCrNoId"))==false)//Complete CR No Entered
		{
			document.getElementById("id4").style.display="block";
			document.getElementById("wardDivId").style.display="block";
			//document.getElementById("newModificationId").style.display="block";
			//document.getElementById("PatientOccId").style.display="block";
			document.getElementById("patientCrEdId").style.display="none";
			document.getElementById("patientCrId").style.display="block";
			document.getElementById("newModificationFormId").style.display="block";
			//document.getElementById("PatientOccId").style.display="block";
			document.getElementById("mandCRId").style.display="none";
			if(document.forms[0].strNewBorn.value==0)
			{
				
				document.getElementById("DeparmentUnitId").style.display="block";
			}
			if(document.forms[0].strNewBorn.value==1)
			{
				
				document.getElementById("DeparmentUnitModiId").style.display="block";
			}
		
	     }
	   	 else//only predefined Digits in CR Field
	     {
	   		SetCursorToTextEnd('strCrNoId');
	   		document.getElementById("newModificationFormId").style.display="none";
			document.getElementById("newAddressModiId").style.display="none";
			document.getElementById("DeparmentUnitModiId").style.display="none";
			document.getElementById("DeparmentUnitId").style.display="none";
			document.getElementById("PatientOccId").style.display="none";
			document.getElementById("PatientOccDtl").style.display="none";
			document.getElementById("wardDivId").style.display="none";
			document.getElementById("patientCrEdId").style.display="block";
			document.getElementById("id4").style.display="none";
			document.getElementById("patientCrId").style.display="none";
			document.getElementById("mandCRId").style.display="";
			document.forms[0].strCrNo.focus();
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
		document.getElementById("id4").style.display="none";
		document.getElementById("patientCrId").style.display="none";
		document.getElementById("mandCRId").style.display="";
		document.forms[0].strCrNo.focus();
	}
	
}	
function view1()
{
	var o1=document.getElementById("id1");
	var o2=document.getElementById("id2");
	//var o3=document.getElementById("PatientOccDtl");
	o1.style.display="none";
	o2.style.display="block";
	//o3.style.display="block";	
}
function getUnitCombo()
{
	var hmode = "GETUNIT"; 
	var url = "NewBornBabyTransCNT.cnt?hmode="+hmode+"&deptCode="+document.forms[0].strDeptNameNewBorn.value+"$strCrNo="+document.forms[0].strCrNo.value;
	ajaxFunction(url,"1");

}
function view2()
{
	
	var o1=document.getElementById("id1");
	var o2=document.getElementById("id2");
	//var o3=document.getElementById("PatientOccDtl");
	o1.style.display="block";
	o2.style.display="none";
	//o3.style.display="none";
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
function clearRecord()
{
	/*document.forms[0].strCrNo.value="";
	view();*/
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
 function onchangeState()
 {
 	var cbo = document.getElementsByName("strState")[0];
 	var text = "";
 	if(cbo.value!="0")
 		text = cbo.options[cbo.selectedIndex].text;
 	document.getElementsByName("strStateName")[0].value = text;
 	
 }
