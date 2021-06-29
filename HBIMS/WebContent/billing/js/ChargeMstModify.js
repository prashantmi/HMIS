function groupCombo(){
  
   
  if(document.forms[0].strIsPackage.checked == false){
  	 
 
	var temp = document.forms[0].hospitalService[document.forms[0].hospitalService.selectedIndex].value ;
	var mode="GRPCMB";
	var url="CNTChargeMst.cnt?hmode="+mode+"&service="+temp;
	
		
	ajaxFunction(url,"1");
	 }
}

function tariffCombo(){	
  
	var temp = document.forms[0].strGroupName[document.forms[0].strGroupName.selectedIndex].value ;
	var mode="TRFCMB";
	var url1="CNTChargeMst.cnt?hmode="+mode+"&service="+temp;
	ajaxFunction(url1,"2");
}

function unitCombo(){
 
	var temp = document.forms[0].strTariffName[document.forms[0].strTariffName.selectedIndex].value.split('^') ;
	
	var mode="UNTCMB";
	var url1="CNTChargeMst.cnt?hmode="+mode+"&service="+temp[1];
	
	ajaxFunction(url1,"3");
}

function wardCombo(){

/*
	var hService = document.forms[0].hospitalService[document.forms[0].hospitalService.selectedIndex].value ;
	
	if(hService == 1){
		var objVal = document.getElementById("wardId");
		objVal.innerHTML = "<select name = 'strWardType'><option value='0'>Select Value</option></select>";
		
		document.forms[0].strWardType.disabled = true;
		document.forms[0].strWardType.selectedIndex = 0;
		
	}else{
	  */
		var temp1 = document.forms[0].hospitalService[document.forms[0].hospitalService.selectedIndex].value ;
		
		var mode="WRDCMB";
		var url="CNTChargeMst.cnt?hmode="+mode+"&service1="+temp1;
		
		ajaxFunction(url,"4");
	//}
}

function packGroupCombo(){
	alert("1");
	var temp = document.chargeBean.strPackHospService[document.chargeBean.strPackHospService.selectedIndex].value;
	
	var mode="GRPCMB1";
	var url="CNTChargeMst.cnt?hmode="+mode+"&service="+temp;
	ajaxFunction(url,"5");
}

function packTariffCombo(){	
	var temp = document.chargeBean.strPackGroupName[document.chargeBean.strPackGroupName.selectedIndex].value;
	var mode="TRFCMB1";
	var url1="CNTChargeMst.cnt?hmode="+mode+"&service="+temp;
	ajaxFunction(url1,"6");
}

function packUnitCombo(){
	
	var temp = document.chargeBean.strPackTariffName[document.chargeBean.strPackTariffName.selectedIndex].value.split('^');

	var mode="UNTCMB1";
	var url1="CNTChargeMst.cnt?hmode="+mode+"&service="+temp[1];
	
	ajaxFunction(url1,"7");
}

function packageDetails(){
	var temp = document.chargeBean.strPackTariffName[document.chargeBean.strPackTariffName.selectedIndex].value.split('^');
	var mode="PACKDET";
	var url1="CNTChargeMst.cnt?hmode="+mode+"&service="+temp[0];
	
	ajaxFunction(url1,"9");
}

function packWardCombo(){
	
	/*if(document.getElementsByName("hospitalService")[0].text == "OPD" || document.getElementsByName("hospitalService")[0].text == "opd"){
		document.getElementsByName("strWardType")[0].disabled = true;
		document.getElementsByName("strWardType")[0].value = "0";
		var objVal = document.getElementById("wardId");
		objVal.innerHTML = "<select name = 'strWardType'><option value='0'>Select Value</option></select>";
	}else{ */
		var temp1 = document.chargeBean.strPackHospService[document.chargeBean.strPackHospService.selectedIndex].value;
		
		var mode="WRDCMB";
		var url="CNTChargeMst.cnt?hmode="+mode+"&service1="+temp1;	
		
		ajaxFunction(url,"8");
	//}
}

function getAjaxResponse(res,mode){	

 // for(var v=0;v<4000;v++){}
	
		if(mode==1){
	
	 	var objVal = document.getElementById("grpName");
		objVal.innerHTML = "<select onChange='tariffCombo();'>" + res + "</select>";		
		wardCombo();
	} else if(mode==2){
	
	  	var objVal = document.getElementById("trfId");
		objVal.innerHTML = "<select  onChange='unitCombo();'>" + res + "</select>";
	} else if(mode==3){

	 	var objVal = document.getElementById("unitId");
		objVal.innerHTML = "<select >" + res + "</select>";
	} else if(mode==4){
	
	  	var objVal = document.getElementById("wardId");
		objVal.innerHTML = "<select >" + res + "</select>";
	} else if(mode==5){
	alert(""+res);
		var objVal = document.getElementById("grpName1");
		objVal.innerHTML = "<select name='a3'  onChange='packTariffCombo();'>" + res + "</select>";
		packWardCombo();
	} else if(mode==6){

		var objVal = document.getElementById("trfId1");
		objVal.innerHTML = "<select name='a1' onChange='packUnitCombo();'>" + res + "</select>";
	} else if(mode==7){
	
		var objVal = document.getElementById("unitId1");
		objVal.innerHTML = "<select name='a2' >" + res + "</select>";
		packageDetails();
	} else if(mode==8){
	
		var objVal = document.getElementById("wardId1");
		objVal.innerHTML = "<select name='a5' >" + res + "</select>";
	} else if(mode==9){

		if(res != ""){
		var objVal = document.getElementById("packDetailsId");
		objVal.innerHTML =  res ;
		}
	}
}


function initializeValues(){
		  
	  if(document.forms[0].strIsPackage.value == '1'){
		document.chargeBean.strIsPackage.checked = true;
		// TEST////
		
	    document.getElementById("forPackages").style.display="block";
		document.getElementById("forServices").style.display="none";
		//////////
		//document.getElementById("packageDetails").style.display="block";
		
		var obj = document.getElementsByName("strRefundable");
		for(var i=0;i<obj.length;i++){
			if(obj[i].value=="1")
				obj[i].checked = true;
		}
		}else{
	
	
		//document.getElementById("packageDetails").style.display="none";
		document.getElementById("forPackages").style.display="none";
		document.getElementById("forServices").style.display="block";
	}
	
}

function submitData(mode){
	var strCheckBox = document.getElementsByName("strCheckBox");
	for(var nTmpI=0; nTmpI<strCheckBox.length; nTmpI++){
		var re = compareDate(strCheckBox[nTmpI].value.split("^")[3],document.getElementsByName("strFromDateArr")[nTmpI].value);
		if(re == 2){
			alert("Effective from date must greater than or equals to previous effective from date "+strCheckBox[nTmpI].value.split("^")[3]);
			return;
		}
	}
	alert(re);
	return;
	
	var hisValidator = new HISValidator("chargeBean");
		
	document.chargeBean.strIsPackage.disabled = false;
	var isPackageVal = document.chargeBean.strIsPackage.checked;
	document.chargeBean.strIsPackage.disabled = true;
	
	
	if(isPackageVal)
	{
		document.chargeBean.strIsPackage.value = "1";
		
							
		if(document.getElementById("packDetailsId").innerHTML != "")
		{						
			hisValidator.addValidation("strPackProcdCost","amount=8,2","Please Enter Valid Amount.");
			hisValidator.addValidation("strPackTariffCost","amount=8,2","Please Enter Valid Amount.");
			hisValidator.addValidation("strPackTotalCost","amount=8,2","Please Enter Valid Amount.");
			
			//if(document.chargeBean.strIsRefundable.checked)
			//	document.chargeBean.strIsRefundable.value = "1";
		}
		//hisValidator.addValidation("strPackHospService","dontselect=0","Please Select Hospital Service From The Hospital Service Combo");		
		//hisValidator.addValidation("strPackGroupName","dontselect=0","Please Select Group Name From The Group Name Combo");
		//hisValidator.addValidation("strPackTariffName","dontselect=0","Please Select Tariff Name From The Tariff Name Combo");
		//hisValidator.addValidation("strPackUnit","dontselect=0","Please Select Unit From The Unit Combo");	
	   // hisValidator.addValidation("strPackWardType","dontselect=0","Please Select Ward Type From Ward Type Combo");
		//hisValidator.addValidation("strPackPatientCategory","dontselect=0","Please Select Patient Category From The Patient Category Combo");
		hisValidator.addValidation("strPackProcdCost","req","Please Enter Procedure cost.");
		hisValidator.addValidation("strPackTariffCost","req","Please Enter Tariff Cost.");
		
	}else
	{
		document.chargeBean.strIsPackage.value = "0";		
			
		//hisValidator.addValidation("hospitalService","dontselect=0","Please Select Hospital Service From The Hospital Service Combo");		
		//hisValidator.addValidation("strGroupName","dontselect=0","Please Select Group Name From The Group Name Combo");
		//hisValidator.addValidation("strTariffName","dontselect=0","Please Select Tariff Name From The Tariff Name Combo");
		//hisValidator.addValidation("strUnit","dontselect=0","Please Select Unit From The Unit Combo");
		//hisValidator.addValidation("strWardType","dontselect=0","Please Select Ward Type From Ward Type Combo");
		//hisValidator.addValidation("strPatientCategory","dontselect=0","Please Select Patient Category From The Patient Category Combo");
		hisValidator.addValidation("strProductCost","req","Please Enter Procedure Cost.");
		hisValidator.addValidation("strProductCost","amount=8,2","Please Enter Valid Amount.");
		hisValidator.addValidation("strTariffCost","req","Please Enter Tariff Cost.");
		hisValidator.addValidation("strTariffCost","amount=8,2","Please Enter Valid Amount.");
		
	}
	if(document.forms[0].strUpdateMode.value=="1")  
	{
	hisValidator.addValidation("strEffectiveFrm","req","Effective From is a mandatory field"); 
	hisValidator.addValidation("strEffectiveFrm","dtgtet=${chargeBean.strCurrentDate}" ,"Please Select Effective From Date Greater Than Or Equal To Current Date.");
	}
	if(document.chargeBean.strEffectiveTo.value != ""){
		hisValidator.addValidation("strEffectiveTo","dtgtet="+document.chargeBean.strEffectiveFrm.value,"Please Select Effective To Date Greater Than Or Equal To Effective From Date.");
		}
		
	hisValidator.addValidation("strRemarks","maxlen=50","Remarks Should Not Be Greater Than 50 Characters");
	
	var retVal = hisValidator.validate();
	hisValidator.clearAllValidations();
	if(retVal)
	{
	
	
		if(isPackageVal){
		
			if(parseFloat(document.forms[0].strPackTotalCost.value) != parseFloat(document.forms[0].strPackGrandTotalCost.value)){
				
									alert("Total Cost and Grand Total Cost are not Same. Please re-enter the Package Detail Amount");
					
									return false;
								}
			
		}
	
	var advObj = document.getElementsByName("strIsAdvance");
	var refObj = document.getElementsByName("strIsRefundable");
	
	var len = advObj.length ;
	
	if(len > 0){
		
			for(var i=0; i< len ; i++){
			
				if(advObj[i].checked == true){
					advObj[i].value = 1;
				}else{
				advObj[i].checked = true;
				advObj[i].value = 0;
				}
			
			if(refObj[i].checked == true){
					refObj[i].value = 1;
				}else{
				refObj[i].checked = true;
				refObj[i].value = 0;
				}
			
			}
	}
	
	
	var isRefObj = document.getElementsByName("strRefundable");
	 
	var len = isRefObj.length ;
	
	if(len > 0){
		
			for(var i=0; i< len ; i++){
			
							
			if(isRefObj[i].checked == true){
					isRefObj[i].value = 1;
				}else{
				isRefObj[i].checked = true;
				isRefObj[i].value = 0;
				}
			
			}
	}
	
	
		/*document.chargeBean.hospitalService.disabled = false;
		document.chargeBean.strGroupName.disabled = false;
		document.chargeBean.strTariffName.disabled = false;
		document.chargeBean.strUnit.disabled = false;
		document.chargeBean.strWardType.disabled = false;
		document.chargeBean.strPatientCategory.disabled = false;
	*/
		document.chargeBean.hmode.value = mode;
		document.chargeBean.submit();
	}
	else{		
		return false;
	}	
}	


function calcTotalAmount(index){
		
			var retVal = false;
		
			var adv = document.getElementById("strProductCost"+index).value;
			var sec = document.getElementById("strTariffCost"+index).value
			var total  = 0;
			
			if(isNaN(adv) || adv=="") adv = "0";
			if(isNaN(sec) || sec=="") sec = "0";
			
					total = manipulateValue(parseFloat(adv),parseFloat(sec),0);
					document.getElementById("strTotalCost"+index).value = total;
					retVal = true;
					
			
		
		return retVal;
	}
	
	
	function enterAmount(index){
	
		
			var retVal = false;
		
		
			if(index != '0'){
			var adv = document.getElementById("strPackProcCost"+index).value;	
			var sec = document.getElementById("strPackTrfCost"+index).value;
			var total  = 0;
			
			if(isNaN(adv) || adv=="") adv = "0";
			if(isNaN(sec) || sec=="") sec = "0";
			
					total = manipulateValue(parseFloat(adv),parseFloat(sec),0);
					document.getElementById("strPackTotal"+index).value = total;
					retVal = true;
					
					calcGrandTotal(1);
					
			}else{
			
			var adv = document.forms[0].strPackProcdCost.value;	
			var sec = document.forms[0].strPackTariffCost.value;	
			var total  = 0;
			
			if(isNaN(adv) || adv=="") adv = "0";
			if(isNaN(sec) || sec=="") sec = "0";
			
					total = manipulateValue(parseFloat(adv),parseFloat(sec),0);
					document.forms[0].strPackTotalCost.value = total;
					retVal = true;
			
			}
		return retVal;
	}
	
	function calcGrandTotal(index){
		
		var totObj = null;
		var totalVal = 0;
		var len = 0;
		
		if(index == 1){
			totObj = document.getElementsByName("strPackTotal");
			len = totObj.length ;
		}else{
		
		totObj = document.getElementsByName("strTotalCost");
			len = totObj.length - 1;
		}
		
		
		if(len > 0){
			for(var i=0; i<len; i++){
				
				var tempTot = totObj[i].value;
				
				if(isNaN(tempTot) || tempTot=="") tempTot = "0";
				
				totalVal = manipulateValue(parseFloat(totalVal),parseFloat(tempTot),0);
			}
				if(index == 1){
			document.forms[0].strPackGrandTotalCost.value = totalVal;
			}
			}
	}
	
	
	
	function checkUpdateMode(radioObj){
	
		if(radioObj.value == 0){
		
			document.getElementById("modifyDateId").style.display = "none";
			document.getElementById("displayDateId").style.display = "block";
			
			document.forms[0].strEffectiveFrm.value = "${chargeBean.strEffectiveFrm}";
			
		}else{
		
			document.getElementById("displayDateId").style.display = "none";
			document.getElementById("modifyDateId").style.display = "block";
		}

	}

