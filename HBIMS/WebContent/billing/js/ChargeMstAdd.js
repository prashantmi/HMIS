function servicePackage()
{	
		if(document.chargeBean.strIsPackage.checked)
		{
			document.getElementById("forPackages").style.display="block";
			document.getElementById("forServices").style.display="none";
		}
		else
		{
			document.getElementById("forPackages").style.display="none";
			document.getElementById("forServices").style.display="block";
		}
		
		document.forms[0].hospitalService.selectedIndex = 0;
		document.forms[0].strGroupName.selectedIndex = 0;
		document.forms[0].strTariffName.selectedIndex = 0;
		document.forms[0].strUnit.selectedIndex = 0;
		document.forms[0].strWardType.selectedIndex = 0;
		
		document.forms[0].strPackHospService.selectedIndex = 0;
		document.forms[0].strPackGroupName.selectedIndex = 0;
		document.forms[0].strPackTariffName.selectedIndex = 0;
				
		
		document.getElementById("previousDataDetailsDivId").innerHTML = "";
		document.getElementById("previousPackDataDetailsDivId").innerHTML = "";
		
		preData = false;
	    packpreData = false;
		
	}

	function groupCombo(){
 		 if(document.forms[0].strIsPackage.checked == false){
  	 		var temp = document.forms[0].hospitalService[document.forms[0].hospitalService.selectedIndex].value ;
			var mode="GRPCMB";
			var url="CNTChargeMst.cnt?hmode="+mode+"&service="+temp;
	
	document.getElementById("id1").innerHTML = "";
	document.getElementsByName("rowIndex1")[0].value = 0;
	document.getElementsByName("rowLength1")[0].value = 0;
			ajaxFunction(url,"1");
	 	}
	}

	function tariffCombo(){	
  
		var temp = document.forms[0].strGroupName[document.forms[0].strGroupName.selectedIndex].value ;
		var mode="TRFCMB";
		var url1="CNTChargeMst.cnt?hmode="+mode+"&service="+temp;
		
		ajaxFunction(url1,"2");
	}

	function unitCombo()
	{
 		var temp = document.forms[0].strTariffName[document.forms[0].strTariffName.selectedIndex].value.split('^') ;
		
		var mode="UNTCMB";
		if(temp.length > 1)
		{
			var url1="CNTChargeMst.cnt?hmode="+mode+"&service="+temp[2];
		}
		else
		{
			var url1="CNTChargeMst.cnt?hmode="+mode+"&service=0000";
		}
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
		
			if(temp.length > 1){
				var url1="CNTChargeMst.cnt?hmode="+mode+"&service="+temp[2];
			}else{
				var url1="CNTChargeMst.cnt?hmode="+mode+"&service=0000";
			}
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

	//var gblPrevPatDtl = false;
	//var gblPackPrevPatDtl = false;

	function getPreviousDetails(){
		var mode="PREVDATA";
		var url = "";
		if(document.chargeBean.strIsPackage.checked){
			url="CNTChargeMst.cnt?hmode="+mode+"&hService="+document.forms[0].strPackHospService.value+"&tariffId="+document.forms[0].strPackTariffName.value+"&wardType="+document.forms[0].strPackWardType.value;	
		}else{
			url="CNTChargeMst.cnt?hmode="+mode+"&hService="+document.forms[0].hospitalService.value+"&tariffId="+document.forms[0].strTariffName.value+"&wardType="+document.forms[0].strWardType.value;
		}
	ajaxFunction(url,"10");
	}

	function getAjaxResponse(res,mode){	

 // for(var v=0;v<4000;v++){}
	
			if(mode==1){
	
			 	var objVal = document.getElementById("grpName");
				objVal.innerHTML = "<select name = 'strGroupName' class='comboMax' onChange='tariffCombo();'>" + res + "</select>";	
				
				var objVal1 = document.getElementById("trfId");
				objVal1.innerHTML = "<select name = 'strTariffName' class='comboMax' onChange='unitCombo();'><option value='0'>Select Value</option></select>";
	
				
				getPreviousDetails();
				document.getElementById("previousDataDetailsDivId").style.display = "none";
					
				wardCombo();
				
			} else if(mode==2){
	
	  			var objVal = document.getElementById("trfId");
				objVal.innerHTML = "<select name = 'strTariffName' class='comboMax' onChange='unitCombo();'>" + res + "</select>";
	
				//getPreviousDetails();
				document.getElementById("previousDataDetailsDivId").style.display = "none";
				
			} else if(mode==3){

	 			var objVal = document.getElementById("unitId");
				objVal.innerHTML = "<select name = 'strUnit' class='comboNormal''>" + res + "</select>";
				getPreviousDetails();
				document.getElementById("previousDataDetailsDivId").style.display = "none";
				
			} else if(mode==4){
				
	  			var objVal = document.getElementById("wardId");
				objVal.innerHTML = "<select name = 'strWardType' class='comboNormal' onchange='getPreviousDetails();'>" + res + "</select>";
				
			} else if(mode==5){
	
				var objVal = document.getElementById("grpName1");
				objVal.innerHTML = "<select name = 'strPackGroupName' class='comboNormal' onChange='packTariffCombo();'>" + res + "</select>";
				
				getPreviousDetails();
				document.getElementById("previousPackDataDetailsDivId").style.display = "none";
				
				packWardCombo();
			} else if(mode==6){
				
				var objVal = document.getElementById("trfId1");
				objVal.innerHTML = "<select name = 'strPackTariffName' class='comboNormal' onChange='packUnitCombo();'>" + res + "</select>";
			
				getPreviousDetails();
				document.getElementById("previousPackDataDetailsDivId").style.display = "none";
			} else if(mode==7){
	
				var objVal = document.getElementById("unitId1");
				objVal.innerHTML = "<select name = 'strPackUnit' class='comboNormal'>" + res + "</select>";
				packageDetails();		
			} else if(mode==8){
	
				var objVal = document.getElementById("wardId1");
				objVal.innerHTML = "<select name = 'strPackWardType' class='comboNormal' onchange='getPreviousDetails();'>" + res + "</select>";
			
			} else if(mode==9){

				if(res != ""){
					var objVal = document.getElementById("packDetailsId");
					objVal.innerHTML =  res ;
					
					getPreviousDetails();
				document.getElementById("previousPackDataDetailsDivId").style.display = "none";
				}
	
			} else if(mode == 10){
	
				var objVal = "";
				if(document.chargeBean.strIsPackage.checked){
				
					objVal = document.getElementById("previousPackDataDetailsDivId");
					//gblPackPrevPatDtl = true;
				}else{
		
					objVal = document.getElementById("previousDataDetailsDivId");
					//gblPrevPatDtl = true;
				}
				
			objVal.innerHTML =  res ;
			
			changeCatCombo();
		
		} else if(mode == 11) {
			document.getElementById("strPatientCategorydiv#delIndex#").innerHTML = "<select name='strPatientCategory' class='comboNormal' id='strPatientCategory#delIndex#'>"+res+"</select>";
			document.getElementById("strPackPatientCategorydiv").innerHTML = "<select name='strPackPatientCategory' class='comboNormal'>"+res+"</select>";
			add1stRow();
		}
	}
/*
function addMltiRow(){
	if(document.getElementsByName("strWardType").selected==false){
		alert("Please select ward type!!");
	} else{
		addRows(new Array('strPatientCategory','strProductCost','strTariffCost','strIsAdvance','strIsRefundable'),new Array('s','t','t','c','c'),'1','1','R');
	}
}
*/
		function submitData(mode){
	
			var hisValidator = new HISValidator("chargeBean");
		
				if(document.chargeBean.strIsPackage.checked){
					document.chargeBean.strIsPackage.value = "1";			
										
		if(document.getElementById("packDetailsId").innerHTML != ""){						
			hisValidator.addValidation("strPackProcdCost","amount=8,2","Please Enter Valid Amount.");
			hisValidator.addValidation("strPackTariffCost","amount=8,2","Please Enter Valid Amount.");
			hisValidator.addValidation("strPackTotalCost","amount=8,2","Please Enter Valid Amount.");
			
			//if(document.chargeBean.strIsRefundable.checked)
			//	document.chargeBean.strIsRefundable.value = "1";
		}
		
			hisValidator.addValidation("strPackHospService","dontselect=0","Please Select Hospital Service");		
			hisValidator.addValidation("strPackGroupName","dontselect=0","Please Select Group Name");
			hisValidator.addValidation("strPackTariffName","dontselect=0","Please Select Tariff Name");
			hisValidator.addValidation("strPackUnit","dontselect=0","Please Select Unit Name");	
	 
	 if(document.forms[0].strPackHospService[document.forms[0].strPackHospService.selectedIndex].value == 2){
				hisValidator.addValidation("strPackWardType","dontselect=0","Please Select Ward From The Ward Combo");
			}
	 
	
			hisValidator.addValidation("strPackPatientCategory","dontselect=0","Please Select Patient Category");
			
			hisValidator.addValidation("strPackProcdCost","req","Please Enter Procedure cost.");
			hisValidator.addValidation("strPackTariffCost","req","Please Enter Tariff Cost.");
			
			
			//hisValidator.addValidation("strPackProcCost","req","Please Enter Package Detail Procedure Cost.");
			//hisValidator.addValidation("strPackProcCost","amount=8,2","Please Enter Valid Amount.");
			//hisValidator.addValidation("strPackTrfCost","req","Please Enter Package Details Tariff Cost.");
			//hisValidator.addValidation("strPackTrfCost","amount=8,2","Please Enter Valid Amount.");
				
		}else{
	

			document.chargeBean.strIsPackage.value = "0";		
			
			hisValidator.addValidation("hospitalService","dontselect=0","Please Select Hospital Service");		
			hisValidator.addValidation("strGroupName","dontselect=0","Please Select Group Name");
			hisValidator.addValidation("strTariffName","dontselect=0","Please Select Tariff Name");
			hisValidator.addValidation("strUnit","dontselect=0","Please Select Unit Name");

if(document.forms[0].hospitalService[document.forms[0].hospitalService.selectedIndex].value == 2){
				hisValidator.addValidation("strWardType","dontselect=0","Please Select Ward From The Ward Combo");
			}
 		if(document.forms[0].strIsAllCategory.checked==false){ 
	 		
			hisValidator.addValidation("strPatientCategory","dontselect=0","Please Select Patient Category");
			hisValidator.addValidation("strProductCost","req","Please Enter Procedure Cost.");
			hisValidator.addValidation("strProductCost","amount=8,2","Please Enter Valid Amount.");
			hisValidator.addValidation("strTariffCost","req","Please Enter Tariff Cost.");
			hisValidator.addValidation("strTariffCost","amount=8,2","Please Enter Valid Amount.");
		
		} 
		else{
			
			hisValidator.addValidation("strAllPatientCategory","dontselect=0","Please Select Patient Category");
			hisValidator.addValidation("strAllProductCost","req","Please Enter Procedure Cost.");
			hisValidator.addValidation("strAllProductCost","amount=8,2","Please Enter Valid Amount.");
			hisValidator.addValidation("strAllTariffCost","req","Please Enter Tariff Cost.");
			hisValidator.addValidation("strAllTariffCost","amount=8,2","Please Enter Valid Amount.");
		}
		}
		hisValidator.addValidation("strEffectiveFrm","req","Effective From is a mandatory field");
		
			hisValidator.addValidation("strEffectiveFrm","dtgtet="+document.forms[0].strCurrentDate.value ,"Please Select Effective From Date Greater Than Or Equal To Current Date.");
	
			hisValidator.addValidation("strRemarks","maxlen=50","Remarks Should Not Be Greater Than 50 Characters");
	
	
			var retVal = hisValidator.validate();
	hisValidator.clearAllValidations();
			if(retVal){
			
	 
				if(document.chargeBean.strIsPackage.checked)
				{ 
						//if(gblPackPrevPatDtl){
						retVal = checkDataExists("strPatCatHidden","strPackPatientCategory",'1',"Patient Category Already Exists.");
						/*	}else{
			
								getPreviousDetails();
								
								document.getElementById("previousPackDataDetailsDivId").style.display="none";
			
									retVal = checkDataExists("strPatCatHidden","strPackPatientCategory",'1',"Patient Category Already Exists.");
										
							}		*/
							
							
							if(retVal)
							{
								/* if(parseFloat(document.forms[0].strPackTotalCost.value) != parseFloat(document.forms[0].strPackGrandTotalCost.value))
								{
									alert("Total Cost and Grand Total Cost are not Same. Please re-enter the Package Detail Amount");					
									return false;
								}*/
								setPackCheckBoxes();
							}
		}else{
		
				//if(gblPrevPatDtl){
						
						retVal  = checkDataExists("strPatCatHidden","strPatientCategory","Patient Category Already Exists.");
						if(retVal){
						retVal = checkMultirow("strPatientCategory","Patient Category Already Selected.");  
						}
								
				
		/*}else{
			getPreviousDetails();
			document.getElementById("previousDataDetailsDivId").style.display="none";
									
			retVal  = checkDataExists("strPatCatHidden","strPatientCategory",'0',"Patient Category Already Exists.");
				
			alert("retVal : "+retVal);
			
			retVal =  false;
		}	*/
		
			if(retVal){
												
						checkNonPackcheckBoxes();
		}
		
	}
	
			
			if(retVal){
			destroyMultiRow('1');
						
						
				document.chargeBean.hmode.value = mode;
				document.chargeBean.submit();
				 
				//return false;
			}else{
		
				return false;
			}
		
		
 		}else{		
	
			return false;
		}	
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
	
		
function calcTotalAmount(index){
		
			var retVal = false;
		
			var adv = document.getElementById("strProductCost"+index).value;
			var sec = document.getElementById("strTariffCost"+index).value;
			
			//alert("adv-->"+adv);
			//alert("sec-->"+sec);
			
			var total ;
			
			if(isNaN(adv) || adv=="") adv = "0";
			if(isNaN(sec) || sec=="") sec = "0";
			
					total = manipulateValue(parseFloat(adv),parseFloat(sec),0);
					document.getElementById("strTotalCost"+index).value = total;
					retVal = true;
					
			
		
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


	var preData = false;
	var packpreData = false;
	
	function blockPreviousData(index){
		
	
		var objVal = "";
		var hisValidator = new HISValidator("chargeBean");
	
		if(document.chargeBean.strIsPackage.checked){
	
		
			hisValidator.addValidation("strPackHospService","dontselect=0","Please Select Hospital Service From The Hospital Service Combo");		
			hisValidator.addValidation("strPackTariffName","dontselect=0","Please Select Tariff Name From The Tariff Name Combo");
	
			if(document.forms[0].strPackHospService[document.forms[0].strPackHospService.selectedIndex].value == 2){
				hisValidator.addValidation("strPackWardType","dontselect=0","Please Select Ward From The Ward Combo");
			}
	
			objVal = document.getElementById("previousPackDataDetailsDivId");
			
			if(index == '2' && packpreData){
				
				objVal.style.display = "none";
				
					packpreData = false;
					
					return false;
			}
			
		}else{
	

			hisValidator.addValidation("hospitalService","dontselect=0","Please Select Hospital Service From The Hospital Service Combo");		
			hisValidator.addValidation("strTariffName","dontselect=0","Please Select Tariff Name From The Tariff Name Combo");
		
			if(document.forms[0].hospitalService[document.forms[0].hospitalService.selectedIndex].value == 2){
				hisValidator.addValidation("strWardType","dontselect=0","Please Select Ward From The Ward Combo");
			}
	
			objVal = document.getElementById("previousDataDetailsDivId");
			
			if(index == '1' && preData){
				
					
				objVal.style.display = "none";
				
					preData = false;
					
					return false;
			}
		}
	
	
	var retVal = hisValidator.validate();
	hisValidator.clearAllValidations();
	
	
			if(retVal){	
			
				if(index == '1') {
					preData = true;
				}
				if(index == '2') packpreData = true;
			
				//objVal.style.display = "block";
			}else{		
			
				return false;
			}	
	
	}
function callDtl(){
	if(!blockPreviousData('1')){
		showDiv('divPreviousDataMinusID');
		hideDiv('divPreviousDataPlusID');
		showDiv('previousDataDetailsDivId');
	}
}
function callDtl1(){
	if(!blockPreviousData('2')){
		showDiv('divPreviousDataPMinusID');
		hideDiv('divPreviousDataPPlusID');
		showDiv('previousPackDataDetailsDivId');
	}
}

function setPackCheckBoxes(){
	
	
						var advObj = document.forms[0].strPackIsAdvance;
							var refObj = document.forms[0].strPackIsRefundable;
	
							if(advObj.checked == true){
							
									advObj.value = 1;
			
							}else{
								
								advObj.checked = true;
								advObj.value = 0;
							}
			
					if(refObj.checked == true){
				
						refObj.value = 1;
						
					}else{
				
						refObj.checked = true;
						refObj.value = 0;
					}
			
		
				var refundObj =  document.getElementsByName("strRefundable");
				var len = refundObj.length ;
	
				if(len > 0){
		
					for(var i=0; i< len ; i++){
						
						if(refundObj[i].checked == true){
				
							refundObj[i].value = 1;
				
						}else{
				
							refundObj[i].checked = true;
							refundObj[i].value = 0;
						}
			
					}
				}
	
}


function checkNonPackcheckBoxes(){
	

	var advObj = document.getElementsByName("strIsAdvance");
				var refObj = document.getElementsByName("strIsRefundable");
	
				var len = advObj.length-1 ;
	
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
		
	
}

function resetChargeMst(){
	
	resetMultiRow('1');
	
	addRows(new Array('strPatientCategory','strProductCost','strTariffCost','strIsAdvance','strIsRefundable'),new Array('s','t','t','c','c'),'1','1','I');
		
	var objgrpNameVal = document.getElementById("grpName");
		objgrpNameVal.innerHTML = "<select name = 'strGroupName' class='comboNormal' onChange='tariffCombo();'><option value='0' selected>Select Value</option></select>";		
	
	
	  	var objtrfIdVal = document.getElementById("trfId");
		objtrfIdVal.innerHTML = "<select name = 'strTariffName' class='comboNormal' onChange='unitCombo();'><option value='0' selected>Select Value</option></select>";
	
	 	var objunitIdVal = document.getElementById("unitId");
		objunitIdVal.innerHTML = "<select name = 'strUnit' class='comboNormal'><option value='0' selected>Select Value</option></select>";
		
	  	var objwardIdVal = document.getElementById("wardId");
		objwardIdVal.innerHTML = "<select name = 'strWardType' class='comboNormal'><option value='0' selected>Select Value</option></select>";
	
		var objgrpName1Val = document.getElementById("grpName1");
		objgrpName1Val.innerHTML = "<select name = 'strPackGroupName' class='comboNormal' onChange='packTariffCombo();'><option value='0' selected>Select Value</option></select>";
		
		var objtrfId1Val = document.getElementById("trfId1");
		objtrfId1Val.innerHTML = "<select name = 'strPackTariffName'  class='comboNormal' onChange='packUnitCombo();'><option value='0' selected>Select Value</option></select>";
	
		var objunitId1Val = document.getElementById("unitId1");
		objunitId1Val.innerHTML = "<select name = 'strPackUnit' class='comboNormal'><option value='0' selected>Select Value</option></select>";
	
		var objwardId1Val = document.getElementById("wardId1");
		objwardId1Val.innerHTML = "<select name = 'strPackWardType' class='comboNormal'><option value='0' selected>Select Value</option></select>";
	
	var objVal = document.getElementById("packDetailsId");
		objVal.innerHTML =  "" ;
	
	
	if(document.forms[0].strIsPackage.checked){
	document.forms[0].reset();
	document.forms[0].strIsPackage.checked = true;
	document.getElementById("previousPackDataDetailsDivId").style.display="none";
	}else{
	document.forms[0].reset();
		document.getElementById("previousDataDetailsDivId").style.display="none";
	}
}

function resetServices(){
		
	document.forms[0].hospitalService.selectedIndex = 0;
	document.forms[0].strPackHospService.selectedIndex = 0;
		
}
function chkAllCategory()
{
	// alert("chkAllCategory()");
	if(document.forms[0].strIsAllCategory.checked==true)
	{
	// alert("checked"); 
	document.getElementById("plusDiv").style.display="none";
	document.getElementById("id1").style.display="none"; 
	document.getElementById("allCatDivId").style.display="block"; 
	}
	else{
	document.getElementById("plusDiv").style.display="block"; 
	document.getElementById("id1").style.display="block";  
	document.getElementById("allCatDivId").style.display="none"; 
	}
}
function calcAllTotalAmount()
{
// alert("calcAllTotalAmount ");

var procCost = document.forms[0].strAllProductCost.value;
var trfCost = document.forms[0].strAllTariffCost.value;
// alert("procCost-"+procCost);
// alert("trfCost-"+trfCost);
if(trfCost=="") 
trfCost = 0;
if(procCost=="") 
procCost = 0;
	document.forms[0].strAllTotalCost.value=parseFloat(procCost)+parseFloat(trfCost);  
}

function showDiv(_strDivId){
	try{
		document.getElementById(_strDivId).style.display = "block";
	}catch(_Err){
		
	}
}

function hideDiv(_strDivId){
	try{
		document.getElementById(_strDivId).style.display = "none";
	}catch(_Err){
		
	}
}

function changeCatCombo(_these)
{
	add1stRow();
	var mode = "GETPATCATCOMBO";
	if(document.chargeBean.strIsPackage.checked)
	{
		url="CNTChargeMst.cnt?hmode="+mode+"&hService="+document.forms[0].strPackHospService.value+"&tariffId="+document.forms[0].strPackTariffName.value.split("^")[0]+"&groupId="+document.forms[0].strPackGroupName.value+"&wardType="+document.forms[0].strWardType.value;
	}
	else
	{
		url="CNTChargeMst.cnt?hmode="+mode+"&hService="+document.getElementsByName("hospitalService")[0].value+"&tariffId="+document.getElementsByName("strTariffName")[0].value.split("^")[0]+"&groupId="+document.getElementsByName("strGroupName")[0].value+"&wardType="+document.forms[0].strWardType.value;
	}
	ajaxFunction(url,"11");
}

function add1stRow(){
	document.getElementById("id1").innerHTML = "";
	document.getElementsByName("rowIndex1")[0].value = 0;
	document.getElementsByName("rowLength1")[0].value = 0;
	addRows(new Array('strPatientCategory','strProductCost','strTariffCost','strTotalCost','strIsAdvance','strIsRefundable'),new Array('s','t','t','t','c','c'),'1','1','R');
	checkAdvance();
}

function checkAdvance()
{
	if(document.chargeBean.hospitalService.value=="2" )//IPD
	{
		var length=document.getElementsByName("strIsAdvance").length;
		for(var i=0;i<length;i++)
		{
			document.getElementsByName("strIsAdvance")[i].checked=true;
			document.getElementsByName("strIsAdvance")[i].value=0;
		}
	}
	else
	{
		var length=document.getElementsByName("strIsAdvance").length;
		for(var i=0;i<length;i++)
		{
			document.getElementsByName("strIsAdvance")[i].checked=true;
			document.getElementsByName("strIsAdvance")[i].value=1;
		}
	}	
}

function addMoreRows()
{
	addRows(new Array('strPatientCategory','strProductCost','strTariffCost','strTotalCost','strIsAdvance','strIsRefundable'),new Array('s','t','t','t','c','c'),'1','1','R');
	checkAdvance();
}