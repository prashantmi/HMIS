
	
	// Batch Update page functions//
	
	function chooseOption(){
	if(document.forms[0].strSelectOption[0].checked){
		document.getElementById("newChange").style.display = "block";
		document.getElementById("copyTo").style.display = "none";
	} else {
		document.getElementById("newChange").style.display = "none";
		document.getElementById("copyTo").style.display = "block";
		if(document.forms[0].hospitalService[document.forms[0].hospitalService.selectedIndex].value != 2){
		document.getElementsByName("strCopyWardType")[0].disabled = true;
		document.getElementsByName("strCopyWardType")[0].value = "0";
		var objVal = document.getElementById("wardId1");
		objVal.innerHTML = "<select name = 'strCopyWardType'><option value='0'>Select Value</option></select>";
	}else{
		var temp1 = document.getElementsByName("hospitalService")[0].value;
		var mode="WRDCMB";
		var url="CNTChargeMst.cnt?hmode="+mode+"&service1="+temp1;
		
		ajaxFunction(url,"3");
	}
	}
}

function groupCombo(){


	var temp = document.getElementsByName("hospitalService")[0].value;
	var mode="GRPCMB";
	var url="CNTChargeMst.cnt?hmode="+mode+"&service="+temp;
	
	ajaxFunction(url,"1");
}

function wardCombo(){	
	if(document.forms[0].hospitalService[document.forms[0].hospitalService.selectedIndex].value != 2){
		document.getElementsByName("strWardType")[0].disabled = true;
		document.getElementsByName("strWardType")[0].value = "0";
		var objVal = document.getElementById("wardId");
		objVal.innerHTML = "<select name = 'strWardType'><option value='0'>Select Value</option></select>";
	}else{
		var temp1 = document.getElementsByName("hospitalService")[0].value;
		var mode="WRDCMB";
		var url="CNTChargeMst.cnt?hmode="+mode+"&service1="+temp1;
		ajaxFunction(url,"2");
	}
}

function getAjaxResponse(res,mode){
	for(var v = 0; v < 1000; v++){
	}
	if(mode==1){
	   
		var objVal = document.getElementById("grpName");
		objVal.innerHTML = "<select name = 'strGroupName' >" + res + "</select>";
		wardCombo();
	} else if(mode==2){
	  
		var objVal = document.getElementById("wardId");
		objVal.innerHTML = "<select name = 'strWardType'>" + res + "</select>";	
		
		var objVal = document.getElementById("wardId1");
		objVal.innerHTML = "<select name = 'strCopyWardType'>" + res + "</select>";
					
	} else if(mode == 3){
		var objVal = document.getElementById("wardId1");
		objVal.innerHTML = "<select name = 'strCopyWardType'>" + res + "</select>";
	}
}

function submitData(mode){	
	var hisValidator = new HISValidator("chargeBean");
	hisValidator.addValidation("hospitalService","dontselect=0","Please Select Hospital Service From The Hospital Service Combo");
	hisValidator.addValidation("strTempPatientCategory","dontselect=0","Please Select Patient Category From The Patient Category Combo");
	
	if(document.forms[0].hospitalService[document.forms[0].hospitalService.selectedIndex].value == 2){
		hisValidator.addValidation("strWardType","dontselect=0","Please Select Ward Type From The Ward Type Combo");
	}
			
	hisValidator.addValidation("strGroupName","dontselect=0","Please Select Group Name From The Group Name Combo");

	
	if(document.forms[0].strSelectOption[0].checked){
		hisValidator.addValidation("strNewProcCost","req","New Procedure Cost is a Mandatory Field");
		hisValidator.addValidation("strNewProcCost","numltet=100","Please Select % Less Than Or Equal To 100");
		hisValidator.addValidation("strNewProcCost","amount=5,2","Please Enter Valid Percentage.");
		hisValidator.addValidation("strNewTrfCost","req","New Tariff Cost is a Mandatory Field");
		hisValidator.addValidation("strNewTrfCost","amount=5,2","Please Enter Valid Percentage.");
		hisValidator.addValidation("strNewTrfCost","numltet=100","Please Select % Less Than Or Equal To 100");
	} else {
		hisValidator.addValidation("strCopyPatCat","dontselect=0","Please Select Patient Category From The Patient Category Combo");
		if(document.forms[0].hospitalService[document.forms[0].hospitalService.selectedIndex].value == 2){
		hisValidator.addValidation("strCopyWardType","dontselect=0","Please Select Ward Type From The Ward Type Combo");
		}
		
		hisValidator.addValidation("strCopyProcCost","req","Copy Procedure Cost is a Mandatory Field");
		hisValidator.addValidation("strCopyProcCost","numltet=100","Please Select % Less Than Or Equal To 100");
		hisValidator.addValidation("strCopyProcCost","amount=5,2","Please Enter Valid Percentage.");
		hisValidator.addValidation("strCopyTrfCost","req","Copy Tariff Cost is a Mandatory Field");
		hisValidator.addValidation("strCopyTrfCost","amount=5,2","Please Enter Valid Percentage.");
		hisValidator.addValidation("strCopyTrfCost","numltet=100","Please Select % Less Than Or Equal To 100");
		if(document.forms[0].strTempPatientCategory.value == document.forms[0].strCopyPatCat.value){
			alert("Please Choose Different Patient Categories");
			return false;
		}
	}
	
	hisValidator.addValidation("strEffectiveFrm","dtgtet="+"document.forms[0].strEffectiveFrm.value","Please Select Effective From Date Greater Than Or Equal To Current Date.");	
	if(document.forms[0].strEffectiveTo.value != "")
		hisValidator.addValidation("strEffectiveTo","dtgtet="+document.forms[0].strEffectiveFrm.value,"Please Select Effective To Date Greater Than Or Equal To Effective From Date.");
	var retVal = hisValidator.validate();	
	if(retVal){
		var obj = document.getElementsByName("strLevel");
		if(obj[0].value == "+"){
			document.forms[0].strNewProcCost.value = document.forms[0].strNewProcCost.value/100;
		} else {
			document.forms[0].strNewProcCost.value = -(document.forms[0].strNewProcCost.value/100);
		}
		if(obj[1].value == "+"){
			document.forms[0].strNewTrfCost.value = document.forms[0].strNewTrfCost.value/100;
		} else {
			document.forms[0].strNewTrfCost.value = -(document.forms[0].strNewTrfCost.value/100);
		}
		if(!document.forms[0].strSelectOption[0].checked){
			if(obj[2].value == "+"){
				document.forms[0].strCopyProcCost.value = document.forms[0].strCopyProcCost.value/100;
			} else {
				document.forms[0].strCopyProcCost.value = -(document.forms[0].strCopyProcCost.value/100);
			}
			if(obj[3].value == "+"){
				document.forms[0].strCopyTrfCost.value = document.forms[0].strCopyTrfCost.value/100;
			} else {
			document.forms[0].strCopyTrfCost.value = -(document.forms[0].strCopyTrfCost.value/100);
			}		
		}
		document.forms[0].hmode.value = mode;
		document.forms[0].submit();
	}
	else{		
		return false;
	}
}


