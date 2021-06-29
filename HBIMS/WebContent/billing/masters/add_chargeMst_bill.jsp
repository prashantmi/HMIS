<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>



<%@ taglib uri="/WEB-INF/dateTag.tld" prefix="dateTag"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>
<%@ taglib uri="/WEB-INF/comboPersistence.tld" prefix="cmbPers"%>
<html>
<head>
<meta charset=utf-8>
<title>Add Page-Charge Master</title>
<link href="../css/master.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet" type="text/css">
<style type="text/css">
@import url(../../hisglobal/css/calendar-tas.css);
</style>
<script type="text/javascript" src="../../hisglobal/js/calendar.js"></script>
<script language="JavaScript" src="../../hisglobal/js/tab.js"></script>
<script language="JavaScript" src="../../hisglobal/masterutil/js/master.js"></script>
<script language="JavaScript" src="../../hisglobal/js/multirow.js"></script>
<script language="JavaScript" src="../../hisglobal/js/util.js"></script>
<script language="JavaScript" src="../../hisglobal/js/validation.js"></script>
<script language="JavaScript">

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

	function groupCombo()
	{
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

	function unitCombo(){
 
		var temp = document.forms[0].strTariffName[document.forms[0].strTariffName.selectedIndex].value.split('^') ;
		
		var mode="UNTCMB";
		if(temp.length > 1){
		var url1="CNTChargeMst.cnt?hmode="+mode+"&service="+temp[2];
		}else{
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
				objVal.innerHTML = "<select name = 'strTariffName' onChange='unitCombo();'>" + res + "</select>";
	
				//getPreviousDetails();
				document.getElementById("previousDataDetailsDivId").style.display = "none";
				
			} else if(mode==3){

	 			var objVal = document.getElementById("unitId");
				objVal.innerHTML = "<select name = 'strUnit'>" + res + "</select>";
				getPreviousDetails();
				document.getElementById("previousDataDetailsDivId").style.display = "none";
				
			} else if(mode==4){
				
	  			var objVal = document.getElementById("wardId");
				objVal.innerHTML = "<select name = 'strWardType' onchange='getPreviousDetails();'>" + res + "</select>";
				
			} else if(mode==5){
	
				var objVal = document.getElementById("grpName1");
				objVal.innerHTML = "<select name = 'strPackGroupName' onChange='packTariffCombo();'>" + res + "</select>";
				
				getPreviousDetails();
				document.getElementById("previousPackDataDetailsDivId").style.display = "none";
				
				packWardCombo();
			} else if(mode==6){
				
				var objVal = document.getElementById("trfId1");
				objVal.innerHTML = "<select name = 'strPackTariffName' onChange='packUnitCombo();'>" + res + "</select>";
			
				getPreviousDetails();
				document.getElementById("previousPackDataDetailsDivId").style.display = "none";
			} else if(mode==7){
	
				var objVal = document.getElementById("unitId1");
				objVal.innerHTML = "<select name = 'strPackUnit'>" + res + "</select>";
				packageDetails();		
			} else if(mode==8){
	
				var objVal = document.getElementById("wardId1");
				objVal.innerHTML = "<select name = 'strPackWardType' onchange='getPreviousDetails();'>" + res + "</select>";
			
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
			document.getElementById("strPatientCategorydiv#delIndex#").innerHTML = "<select name='strPatientCategory' id='strPatientCategory#delIndex#'>"+res+"</select>";
			document.getElementById("strPackPatientCategorydiv").innerHTML = "<select name='strPackPatientCategory'>"+res+"</select>";
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
			
			
			hisValidator.addValidation("strPackProcCost","req","Please Enter Package Detail Procedure Cost.");
			hisValidator.addValidation("strPackProcCost","amount=8,2","Please Enter Valid Amount.");
			hisValidator.addValidation("strPackTrfCost","req","Please Enter Package Details Tariff Cost.");
			hisValidator.addValidation("strPackTrfCost","amount=8,2","Please Enter Valid Amount.");
				
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
	
			hisValidator.addValidation("strEffectiveFrm","dtgtet=${chargeBean.strCurrentDate}" ,"Please Select Effective From Date Greater Than Or Equal To Current Date.");
	
			hisValidator.addValidation("strRemarks","maxlen=50","Remarks Should Not Be Greater Than 50 Characters");
	
	
			var retVal = hisValidator.validate();
	hisValidator.clearAllValidations();
			if(retVal){
			
	 
				if(document.chargeBean.strIsPackage.checked){ 
		
						//if(gblPackPrevPatDtl){
							
			
								retVal = checkDataExists("strPatCatHidden","strPackPatientCategory",'1',"Patient Category Already Exists.");
								
												
						/*	}else{
			
								getPreviousDetails();
								
								document.getElementById("previousPackDataDetailsDivId").style.display="none";
			
									retVal = checkDataExists("strPatCatHidden","strPackPatientCategory",'1',"Patient Category Already Exists.");
										
							}		*/
							
							
							if(retVal){
							
								if(parseFloat(document.forms[0].strPackTotalCost.value) != parseFloat(document.forms[0].strPackGrandTotalCost.value)){
				
					//alert("parseFloat(document.forms[0].strPackTotalCost.value)"+parseFloat(document.forms[0].strPackTotalCost.value)); 
					//alert("parseFloat(document.forms[0].strPackGrandTotalCost.value"+parseFloat(document.forms[0].strPackGrandTotalCost.value)); 
									alert("Total Cost and Grand Total Cost are not Same. Please re-enter the Package Detail Amount");
					
									return false;
								}
							
								
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
		objgrpNameVal.innerHTML = "<select name = 'strGroupName' onChange='tariffCombo();'><option value='0' selected>Select Value</option></select>";		
	
	
	  	var objtrfIdVal = document.getElementById("trfId");
		objtrfIdVal.innerHTML = "<select name = 'strTariffName' onChange='unitCombo();'><option value='0' selected>Select Value</option></select>";
	
	 	var objunitIdVal = document.getElementById("unitId");
		objunitIdVal.innerHTML = "<select name = 'strUnit'><option value='0' selected>Select Value</option></select>";
		
	  	var objwardIdVal = document.getElementById("wardId");
		objwardIdVal.innerHTML = "<select name = 'strWardType'><option value='0' selected>Select Value</option></select>";
	
		var objgrpName1Val = document.getElementById("grpName1");
		objgrpName1Val.innerHTML = "<select name = 'strPackGroupName' onChange='packTariffCombo();'><option value='0' selected>Select Value</option></select>";
		
		var objtrfId1Val = document.getElementById("trfId1");
		objtrfId1Val.innerHTML = "<select name = 'strPackTariffName' onChange='packUnitCombo();'><option value='0' selected>Select Value</option></select>";
	
		var objunitId1Val = document.getElementById("unitId1");
		objunitId1Val.innerHTML = "<select name = 'strPackUnit'><option value='0' selected>Select Value</option></select>";
	
		var objwardId1Val = document.getElementById("wardId1");
		objwardId1Val.innerHTML = "<select name = 'strPackWardType'><option value='0' selected>Select Value</option></select>";
	
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
		document.getElementById("strAllProductCost").style.display="none";
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

function add1stRow()
{
	document.getElementById("id1").innerHTML = "";
	document.getElementsByName("rowIndex1")[0].value = 0;
	document.getElementsByName("rowLength1")[0].value = 0;
	addRows(new Array('strPatientCategory','strProductCost','strTariffCost','strTotalCost','strIsAdvance','strIsRefundable'),new Array('s','t','t','t','c','c'),'1','1','R');
	alert("5");
	checkAdvance();
	alert("6");
}
function checkAdvance()
{

alert("1");
	if(document.chargeBean.hospitalService.value=="2" )//IPD
	{
		alert("2");
		var length=document.getElementsByName("strIsAdvance").length;
		for(var i=0;i<length;i++)
		{
			document.getElementsByName("strIsAdvance")[i].checked=false;
			document.getElementsByName("strIsAdvance")[i].value=0;
		}
	}
	else
	{
		alert("3");
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
	alert("4");
	addRows(new Array('strPatientCategory','strProductCost','strTariffCost','strTotalCost','strIsAdvance','strIsRefundable'),new Array('s','t','t','t','c','c'),'1','1','R');
	checkAdvance();
}

</script>
</head>
<body
	onload="resetServices(),addRows(new Array('strPatientCategory','strProductCost','strTariffCost','strIsAdvance','strIsRefundable'),new Array('s','t','t','c','c'),'1','1','I');">
<html:form action="/masters/CNTChargeMst.cnt" name="chargeBean"
	type="billing.masters.VOChargeMst">
	<div class="errMsg" id="errMsg"><bean:write name="chargeBean"
		property="strErrMsg" /></div>
	<div class="warningMsg" id="warningMsg"><bean:write
		name="chargeBean" property="strWarningMsg" /></div>
	<div class="normalMsg" id="normalMsg"><bean:write
		name="chargeBean" property="strNormalMsg" /></div>

	<tag:tab tabLabel="Add Charge" selectedTab="FIRST" align="center" onlyTabIndexing="0"
		width="TABLEWIDTH">
	</tag:tab>

	<table class="TABLEWIDTH" border="0" align="center" cellspacing="1px">
		<tr class="HEADER">
			<td colspan="4">
			<table width="100%" border="0" align="center" cellspacing="1px">
				<tr class="HEADER">
					<td width="50%">
					<div align="left">Charge Master&gt;&gt;Add</div>
					</td>
					<td width="50%">
					<div align="right"><input type="checkbox" name="strIsPackage"
						onClick="servicePackage();">Package</div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
	<div id="forServices" style="display: block">
	<table class="TABLEWIDTH" border="0" align="center" cellspacing="1px">
		<tr>
			<td class="CONTROL" colspan="4"></td>
			<td class="LABEL"><font color="red">*</font>Hospital Service</td>
			<td class="CONTROL" colspan="2"><select name="hospitalService"
				class="comboNormal" onChange="groupCombo();">
				<bean:write name="chargeBean" property="strHospitalServiceCombo"
					filter="false" />
			</select></td>
		</tr>

		<tr>
			<td class="LABEL"><font color="red">*</font>Group Name</td>
			<td class="CONTROL" colspan="6">
			<div id="grpName"><select name="strGroupName"
				class="comboNormal" onChange="tariffCombo();">
				<option value="0">Select Value</option>
			</select></div>
			</td>
		</tr>

		<tr>
			<td class="LABEL"><font color="red">*</font>Tariff/Package Name</td>
			<td class="CONTROL" colspan="3">
			<div id="trfId"><select name="strTariffName"
				class="comboNormal" onchange="changeCatCombo(this)">
				<option value="0">Select Value</option>
			</select></div>
			</td>
			<td class="LABEL"><font color="red">*</font>Unit</td>
			<td class="CONTROL" colspan="2">
			<div id="unitId"><select name="strUnit" class="comboNormal">
				<option value="0">Select Value</option>
			</select></div>
			</td>
		</tr>
		<tr>
			<td class="LABEL">Ward Type</td>
			<td class="CONTROL" colspan="6">
			<div id="wardId"><select name="strWardType" class="comboNormal">
				<option value="0">Select Value</option>
			</select></div>
			</td>
			</tr>
	</table>
	
	<table class="TABLEWIDTH" align="center" cellpadding="1px"
		cellspacing="1px">
		<tr>
			<td class="TITLE" colspan="5">
			<div id="divPreviousDataPlusID" align="left"><img
				src="../../hisglobal/images/plus.gif"
				title='Show Previous Data Details'
				onclick="callDtl();"
				style="cursor: pointer;"> Previous Data</div>
			<div id="divPreviousDataMinusID" style="display: none;" style="display: block;"
				align="left"><img src="../../hisglobal/images/minus.gif"
				title='Hide Previous Data Details'
				onclick="hideDiv('divPreviousDataMinusID'),hideDiv('previousDataDetailsDivId'),showDiv('divPreviousDataPlusID');"
				style="cursor: pointer;"> Previous Data</div>
			</td>
		</tr>
	</table>
	<div id="previousDataDetailsDivId" style="display:none"></div>
	
	<table class="TABLEWIDTH" border="0" align="center" cellspacing="1px">
		<tr class="TITLE">
			<td colspan="7">Current Charges</td>
		</tr>
		<tr>
			<td class="multiLabel" width="20%"><font color="red">*</font>Patient
			Category .<input type="checkbox" name="strIsAllCategory" value="1"
				onclick="chkAllCategory();">All</td>
			<td class="multiLabel" width="14%"><font color="red">*</font>Procedure
			Cost(<img src='/HBIMS/hisglobal/images/INR.png'>)</td>
			<td class="multiLabel" width="14%"><font color="red">*</font>Tariff
			Cost(<img src='/HBIMS/hisglobal/images/INR.png'>)</td>
			<td class="multiLabel" width="14%"><font color="red">*</font>Total
			Cost(<img src='/HBIMS/hisglobal/images/INR.png'>)</td>
			<td class="multiLabel" width="14%">IsAdvance</td>
			<td class="multiLabel" width="14%">IsRefundable</td>
			<td class="multiLabel" width="10%">
			<div id="plusDiv" style="display: block"><img
				src="../../hisglobal/images/plus.gif"
				onClick="addMoreRows()"></div>
			</td>
		</tr>
	</table>
	<div id="id1"></div>
	<div id="allCatDivId" style="display: none">

	<table class="TABLEWIDTH" border="0" align="center" cellspacing="1px">
		<tr>
			<td class="multiControl" width="20%"><select class="comboNormal"
				name="strAllPatientCategory">
				<option value="1">All</option>
			</select></td>
		 	<td style="display: none" class="multiControl" width="14%"><input  class="txtFldSmall"
				type="text" name="strAllProductCost" value="" maxlength="9"
				onkeypress="return validateData(event,7);"
				onkeyup="calcAllTotalAmount();"></td>
			<td class="multiControl" width="14%"><input class="txtFldSmall"
				type="text" name="strAllTariffCost" value="" maxlength="9"
				onkeypress="return validateData(event,7);"
				onkeyup="calcAllTotalAmount();"></td>
			<td class="multiControl" width="14%"><input class="txtFldSmall"
				type="text" name="strAllTotalCost" value="" maxlength="9"
				onkeypress="return validateData(event,7);" readonly="readonly"></td>
			<td class="multiControl" width="14%"><input type="checkbox"
				name="strAllIsAdvance" value="1"></td>
			<td class="multiControl" width="14%"><input type="checkbox"
				name="strAllIsRefundable" value="1"></td>
			<td class="multiControl" width="10%"></td>
		</tr>
	</table>

	</div>
	</div>

	<div id="forPackages" style="display: none">
	<table class="TABLEWIDTH" border="0" align="center" cellspacing="1px">
		<tr>
			<td class="CONTROL" width="50%" colspan="2">&nbsp;</td>
			<td class="LABEL" width="25%"><font color="red">*</font>Hospital Service</td>
			<td class="CONTROL" width="25%"><select name="strPackHospService"
				class="comboNormal" onChange="packGroupCombo();">
				<bean:write name="chargeBean" property="strHospitalServiceCombo"
					filter="false" />
			</select></td>
		</tr>
		<tr>
			<td class="LABEL" width="25%"><font color="red">*</font>Group Name</td>
			<td class="CONTROL" width="25%" colspan=3>
			<div id="grpName1"><select name="strPackGroupName"
				class="comboNormal" onChange="packTariffCombo();">
				<option value="0">Select Value</option>
			</select></div>
			</td>
		</tr>
		<tr>
			<td class="LABEL" width="25%"><font color="red">*</font>Tariff/Package
			Name</td>
			<td class="CONTROL" width="25%">
			<div id="trfId1"><select name="strPackTariffName"
				class="comboNormal" onChange="packUnitCombo();">
				<option value="0">Select Value</option>
			</select></div>
			</td>
			<td class="LABEL" width="25%"><font color="red">*</font>Unit</td>
			<td class="CONTROL" width="25%">
			<div id="unitId1"><select name="strPackUnit"
				class="comboNormal">
				<option value="0">Select Value</option>
			</select></div>
			</td>
		</tr>
		<tr>
			<td class="LABEL" width="25%">Ward Type</td>
			<td class="CONTROL" width="75%" colspan=3>
			<div id="wardId1"><select name="strPackWardType"
				class="comboNormal">
				<option value="0">Select Value</option>
			</select></div>
			</td>
		</tr>
	</table>
	
	<table class="TABLEWIDTH" align="center" cellpadding="1px"
		cellspacing="1px">
		<tr>
			<td class="TITLE" colspan="5">
			<div id="divPreviousDataPPlusID" align="left"><img
				src="../../hisglobal/images/plus.gif"
				title='Show Previous Data Details'
				onclick="callDtl1();"
				style="cursor: pointer;"> Previous Data</div>
			<div id="divPreviousDataPMinusID" style="display: none;" style="display: block;"
				align="left"><img src="../../hisglobal/images/minus.gif"
				title='Hide Previous Data Details'
				onclick="hideDiv('divPreviousDataPMinusID'),hideDiv('previousPackDataDetailsDivId'),showDiv('divPreviousDataPPlusID');"
				style="cursor: pointer;"> Previous Data</div>
			</td>
		</tr>
	</table>
	<div id="previousPackDataDetailsDivId"></div>
	<table class="TABLEWIDTH" border="0" align="center">
		<tr><td class="TITLE" colspan="6"></td></tr>
		<tr>
			<td class="multiLabel" width="20%"><font color="red">*</font>Patient
			Category</td>
			<td class="multiLabel" width="16%"><font color="red">*</font>Procedure
			Cost</td>
			<td class="multiLabel" width="16%"><font color="red">*</font>Tariff
			Cost</td>
			<td class="multiLabel" width="16%">Total Cost</td>
			<!-- <td class="multiLabel" width="16%">Is-Advance</td> -->
			<td class="multiLabel" width="16%">Is-Refundable</td>
			<td class="multiLabel" width="16%">Is-Advance</td>
		</tr>

		<tr>
			<td class="multiControl"><div id="strPackPatientCategorydiv"><select name="strPackPatientCategory"
				class="comboNormal">
				<bean:write name="chargeBean" property="patientCategory"
					filter="false" />
			</select></div></td>
			<td class="multiControl"><input type="text" class="txtFldNormal"
				name="strPackProcdCost" id="strPackProcdCost" maxlength="9"
				value="0" onkeypress="return validateData(event,7);"
				onkeyup="enterAmount('0');"></td>
			<td class="multiControl"><input type="text" class="txtFldSmall"
				name="strPackTariffCost" id="strPackTariffCost" maxlength="9"
				value="0" onkeypress="return validateData(event,7);"
				onkeyup="enterAmount('0');"></td>
			<td class="multiControl"><input type="text" class="txtFldSmall"
				name="strPackTotalCost" id="strPackTotalCost" maxlength="9"
				value="0" readonly="readonly"
				onkeypress="return validateData(event,7);"></td>
			<!-- <td class="multiControl"><input type="checkbox"
				name="strPackIsAdvance"></td> -->
			<td class="multiControl"><input type="checkbox"
				name="strPackIsRefundable" value='0'></td>
			<td class="multiControl"><input type="checkbox"
				name="strPackIsAdvance" value='0'></td>
		</tr>
	</table>
	<table class="TABLEWIDTH" border="0" align="center" cellspacing="1px">
		<tr class="HEADER">
			<td colspan="8">Package Details</td>
		</tr>
		<tr>

			<td class="multiLabel" width="15%">Tariff Name</td>
			<td class="multiLabel" width="15%">Quantity</td>
			<td class="multiLabel" width="15%">Unit</td>
			<td class="multiLabel" width="15%">Procedure Cost/unit(<img src='/HBIMS/hisglobal/images/INR.png'>)</td>
			<td class="multiLabel" width="15%">Tariff Cost/unit(<img src='/HBIMS/hisglobal/images/INR.png'>)</td>
			<td class="multiLabel" width="15%">Total Cost/unit(<img src='/HBIMS/hisglobal/images/INR.png'>)</td>
			<td class="multiLabel" width="10%">Refundable</td>
		</tr>
	</table>
	<div id="packDetailsId"></div>
	</div>
	<table class="TABLEWIDTH" border="0" align="center">
		<tr>
			<td width="50%" class="LABEL"><font
				color="red">*</font>Effective From</td>
			<td width="50%" class="CONTROL"><dateTag:date
				name="strEffectiveFrm" value="${chargeBean.strEffectiveFrm}"></dateTag:date></td>
		</tr>
		<tr>
			<td class="LABEL">Remarks</td>
			<td class="CONTROL"><textarea rows="2"
				name="strRemarks"></textarea></td>
		</tr>
		<tr class="FOOTER">
			<td colspan="2"><font color="red">*</font>Mandatory Fields</td>
		</tr>
	</table>
	<div align="center"><img name="save"
		src="../../hisglobal/images/btn-sv.png"
		style="cursor: pointer; cursor: hand;" title="Save Record"
		onClick="return submitData('SAVEADD');"> <img
		src="../../hisglobal/images/btn-clr.png"
		style="cursor: pointer; cursor: hand;" title="Reset Content"
		onClick="resetChargeMst();" /> <img
		src="../../hisglobal/images/btn-ccl.png"
		style="cursor: pointer; cursor: hand;" title="Cancel Process"
		onclick="cancel('LIST');"></div>
	<input type="hidden" name="hmode" value="" />
	<input type="hidden" name="previousDataFlag" value="0" />
	<input type="hidden" name="packPreviousDataFlag" value="0" />
	<cmbPers:cmbPers />
</html:form>
<jsp:include page="addmultirow_chargeMst_bill.jsp"></jsp:include>
<tag:autoIndex></tag:autoIndex>
</body>
</html>
