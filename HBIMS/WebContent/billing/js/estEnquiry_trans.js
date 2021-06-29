
	// Global Vaiables
	
	var defaultColor = "blue";
	var setColor = "red";
	
	var gblPayDtls = "";
	var gblPayEdit = "";
	var gblAmount = "";  
	var gblPayMode = "";
	var savedTariff="";
	function setTariff(){
		var offTariffName=document.getElementsByName("strOfflineTariffName");
		for(var nTmpI=0; nTmpI<offTariffName.length; nTmpI++){
			if(savedTariff=="" && offTariffName[nTmpI].value!="")
				savedTariff=document.getElementsByName("strOffLineTreatmentCategory")[0].value;
		}
		
	}
	
	function openPrintPopUp()
{
		//alert("hi");
	//	document.forms[0].isOpenPopUp.value = "1";
		//document.forms[0].filePath.value = "C:/NIMS/AHIMSG5/PrintTemp/Billing.dat"
		//alert(document.forms[0].isOpenPopUp.value);
	if(document.forms[0].isOpenPopUp.value==1 )//Printing Over Laser Printer
	{
		//alert(document.forms[0].filePath.value);
		/*  var url='EstEnquiryTransCNT.cnt?hmode=PRINTSLIP&filePath='+document.forms[0].filePath.value;
		  child = window.open(url,'popupWindow','status=yes,scrollbars=yes,height=550,width=450,left=400,top=100');  
		  child.moveTo(900,250);
		  child.focus();*/
		window.print();
	 }
	 document.forms[0].isOpenPopUp.value=0;
}
	
	function hidePrintableSlip()
	{
		//alert("hide");
		document.getElementById("printableSlip").style.display="none"; 
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
	function printSlip()
	{
		window.print();
		if(parseInt(globalCnt)<=2)
		{
			var t=setTimeout("printSlip1()",2000);
		}
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
	        	globalCnt++;
	            printSlip();
	        }
	}

	function generateRows(){
		if(!showAlert())
			return false;
			
		addRows(new Array('strOfflineTariffName','strOfflineTariffDetailsHidden','strOfflineTempTariffRateUnit','strOfflineTariffRateUnit','strOfflineTariffQty','strOfflineTariffUnit','strOfflineTariffNetAmount','strOfflineTotalActualTariffAmtVal'),new Array('t','t','t','t','t','s','t','t','t','t','t','t'),'2','1','R')
		
	
var tempVal = "strOfflineTariffName2-"+document.multirow.rowIndex2.value;
	 
			var tempObj = document.getElementById(tempVal);
		
		
		tempObj.select();
		tempObj.focus();
		
	
	}
	
	
	/**
	 * setRateValue
	 * @param {String} index 
	 */
	 function setRateValue(index) {
	 	
	 		document.getElementById("strOfflineTariffRateUnit"+index).value = document.getElementById("strOfflineTempTariffRateUnit"+index).value; 
	 	
	 	calcOffLineTariffNetAmount(index);
	 	
	 }
	
	
	
	/**
	 * selectPatientMode
	 * @param {Object} obj - Radio Button Object. 
	 */
	 function selectPatientMode(obj) {
	 	
	 	if(obj.value == '1'){
		 	document.forms[0].hmode.value="WITHCRNO";
	 	} 
	 	
	 	document.forms[0].submit();
	 }
	
	
	/**
	 * showCltDetails - shows the given divId by showing the minus image and hiding the plus image.
	 * @param {object} divId - div id which should be shown.
	 */
	function showCltDetails(divId){
		
		document.getElementById(divId).style.display="block";
				
		document.getElementById('minus'+divId).style.display="block";
		document.getElementById('plus'+divId).style.display="none";
		
	}
	

	
	/**
	 * hideCltDetails - hide the given divId by hiding the minus image and showing the plus image.
	 * @param {object} divId - div id which should be shown.
	 */
	function hideCltDetails(divId){
		document.getElementById(divId).style.display="none";
			
		document.getElementById('minus'+divId).style.display="none";
		document.getElementById('plus'+divId).style.display="block";
		
	}

	
	/**
	 * cancelFunc -to forward page to init page and  submit the page with hmode 'CANCELPAGE'. 
	 */
	function cancelFunc1(){
		
		document.forms[0].hmode.value="CANCELPAGE";
		document.forms[0].submit();
	}

		
	/**
	 * withoutCrNoOnLoadLogics
	 * @param {}  
	 */
	 function estEnquiryOnLoadLogics() {
	 	
	 	
	 		if(document.forms[0].strIsPreviousCrNoReq.value == '0'){
	 			
	 				document.getElementById("previousCrNoNonMandatoryDivId").style.display = "block";
	 				document.getElementById("previousCrNoMandatoryDivId").style.display = "none";
	 				
	 				document.forms[0].strGuarantorName.disabled = false;
	 				document.forms[0].strGuarantorContactNo.disabled = false;
	 				document.forms[0].strGuartorAddress.disabled = false;
	 				document.forms[0].strReferringPhysician.disabled = false;
	 				document.forms[0].strReferringPhysicianContactNo.disabled = false;
	 				document.forms[0].strAge.disabled = false;
	 				document.forms[0].strAgeUnit.disabled = false;
	 				document.forms[0].strGender.disabled = false;
	 				//document.forms[0].strOffLineRaisingDepartment.disabled = false;
	 				
	 				document.forms[0].strGuarantorName.focus();	 	
	 				
	 							
	 			
	 		}else{
	 			
	 				document.getElementById("previousCrNoNonMandatoryDivId").style.display = "none";
	 				document.getElementById("previousCrNoMandatoryDivId").style.display = "block";
	 				
	 				document.forms[0].strPreviousCrNo.focus();
	 				document.forms[0].strPreviousCrNo.select();
	 		}
	 		
	 		if(document.forms[0].strIsRefPhysicanReq.value == '0'){
	 			
	 				document.getElementById("referringPhysicianNonMandatoryDivId").style.display = "block";
	 				document.getElementById("referringPhysicianMandatoryDivId").style.display = "none";
	 			
	 		}else{
	 			
	 				document.getElementById("referringPhysicianNonMandatoryDivId").style.display = "none";
	 				document.getElementById("referringPhysicianMandatoryDivId").style.display = "block";
	 			
	 		}
	 	
	 	
	 		var normalMsgVal = document.getElementById("normalMsg").innerHTML;	
				
			if(normalMsgVal.length > 1){
				
				if(normalMsgVal.search(document.forms[0].strTempBillNo.value) != -1)
				{											
						//else path (after successful print) copied here..
						/*var billNo = document.forms[0].strTempBillNo.value;
						var recNo  = document.forms[0].strTempReceiptNo.value;
						var hmode = "UPDATEPRINTSTATUS"; 
						var url = "CashCollectionTransCNT.cnt?hmode="+hmode+"&billNo="+billNo+"&recNo="+recNo;
																
						ajaxFunction(url,"1","updateStatusResponse");*/
						
						//this has been commented because we r showing data in popup..		
						/*		
						var confirmFlag = confirm("Whether Receipt Printed Successfully?");
					
						if(!confirmFlag){
							 
							 
							 var printLimit = document.forms[0].strPrintMessageLimit.value;
					
						if(printLimit.length <0) printLimit = "0";
						
						printLimit = parseInt(printLimit);
							
							if(printLimit > 0){
								
								gblPrintLimitVal = printLimit;
																
								reprintContents();
											
								
							}else{
								
								var billNo = document.forms[0].strTempBillNo.value;
								var recNo  = document.forms[0].strTempReceiptNo.value;
											
								var hmode = "UPDATEPRINTSTATUS"; 
								var url = "CashCollectionTransCNT.cnt?hmode="+hmode+"&billNo="+billNo+"&recNo="+recNo;
																
								ajaxFunction(url,"1","updateStatusResponse");
								
								
							}
							 
										
						} */
										
						return false;
				}
				
			}
	 	
	 	 
	 	
	 }	
	 
	 /**
	 * loadIntExtPatLogics
	 * @param {}  
	 */
	 function loadIntExtPatLogics() {
	 	
	 	/*//alert(document.forms[0].strIntExtPat.value);
	 		//if(document.forms[0].strPatTypeBoth.value == '1' && document.forms[0].strIntExtPat.value=='1'){//Both-Internal
	 		//		document.getElementById("onlineRequestComboDivId").style.display = "none";	
	 							
	 			
	 		//}
	 		//else if(document.forms[0].strPatTypeBoth.value == '1' && document.forms[0].strIntExtPat.value=='2'){//Both-External
	 		//		document.getElementById("onlineRequestComboDivId").style.display = "block";	
	 							
	 			
	 		//}else if(document.forms[0].strPatTypeBoth.value == '2'){//Internal
	 			
	 		//		document.getElementById("onlineRequestComboDivId").style.display = "none";	
	 		//}
	 		else if(document.forms[0].strPatTypeBoth.value == '3'){//External
	 		document.getElementById("onlineRequestComboDivId").style.display = "block";	
	 		}
	 		else
	 		{
	 			document.getElementById("onlineRequestComboDivId").style.display = "block";	
	 		}*/
	 		
	 	 
	 	
	 }
	 
	 /**
	 * selPatTypeLogic
	 * @param {}  
	 */
	 function selPatTypeLogic(obj) {
	 	
	 	
	 		if(obj.value == '1'){//Internal
	 			
	 				document.getElementById("onlineRequestComboDivId").style.display = "none";	
	 				document.forms[0].hmode.value="INTERNALPATIENT";
	 				document.forms[0].submit();
	 		}
	 		else if(obj.value == '2'){//External
	 		document.getElementById("onlineRequestComboDivId").style.display = "block";	
	 		document.forms[0].hmode.value="EXTERNALPATIENT";
	 		document.forms[0].submit();
	 		}
	 		else
	 		{
	 			document.getElementById("onlineRequestComboDivId").style.display = "block";	
	 		}
	 		
	 	 
	 	
	 }
		
	var gblPrintLimitVal = 0;
	
	
	/**
	 * reprintContents
	 */
	 function reprintContents() {
	 	
	 		 	
	 		if(gblPrintLimitVal != 0){
	 			
	 			var hmode = "REPRINT"; 
				var url = "CashCollectionWithoutCrTransCNT.cnt?hmode="+hmode;
											
				ajaxFunction2(url,"1","rePrintStatus");	
	 			
	 		}else{
	 			
	 			var billNo = document.forms[0].strTempBillNo.value;
				var recNo  = document.forms[0].strTempReceiptNo.value;
							
				var hmode = "UPDATEPRINTSTATUS"; 
				var url = "CashCollectionWithoutCrTransCNT.cnt?hmode="+hmode+"&billNo="+billNo+"&recNo="+recNo;
												
				ajaxFunction2(url,"1","updateStatusResponse");
	 			
	 		}
	 		
	 		
	 	
	 }		
				
	
	/**
	 * rePrintStatus 
	 */
	 function rePrintStatus() {
	 	
	 	gblPrintLimitVal = gblPrintLimitVal - 1;
	 	
	 		var confirmFlag = confirm("Whether Receipt Printed Successfully?");
					
						if(!confirmFlag){
							
							reprintContents();
							
						}
	 	
	 }			
				
				
		var gblWithoutCrNoReqObj = "";
		
	/**
	 * getWithoutCrNoOnlineRequests
	 * @param {Object} reqObj 
	 */
	 function getWithoutCrNoOnlineRequests(reqObj) {
	 	
	 	gblWithoutCrNoReqObj = reqObj;
	 	
	 	if(reqObj.value.length > 1){
	 		
	 		document.getElementById("refundDtlsDivId").style.display = "none";
	  		document.getElementById("withoutCrNoRefundTariffDivId").style.display = "none";
	  		document.getElementById("otherRefundDetailsDivId").style.display = "none";
	  		
	  		document.getElementById("receiptDtlsDivId").style.display = "block";
	  		document.getElementById("receiptTariffDivId").style.display = "block";
	  		document.getElementById("paymentModeDtlsDivId").style.display = "block";
	 		
	 		var temp = reqObj.value.split("^");
	 		
	 		var hmode = "WITHOUTCRNOREQTARIFF"; 
			var url = "CashCollectionWithoutCrTransCNT.cnt?hmode="+hmode+"&strReqNo="+temp[0]+"&strChargeTypeId=1";
										
				ajaxFunction(url,"26");		
	 			 
	 			 		
	 	}else{
	 		
	 		document.getElementById("onlineRequestTariffDivId").innerHTML = "";
	 		 		
	 		withoutCrNoChangeMode(reqObj);
	 	}
	 	
	 	
	 }	
		
		
		
		/**
		 * withoutCrNoChangeMode
		 * @param {Object} reqObj 
		 */
		 function withoutCrNoChangeMode(reqObj) {
		 	
		 	
		 	document.forms[0].strOfflineTotalRecAmount.value = 0;
		 	//document.forms[0].strOfflineTotalPayAmount.value = 0;
		 	document.forms[0].strOfflineRefundAmount.value = 0;
		 	
		 	document.getElementById("totalRecAmtDivId").innerHTML = "0.00";
		 //	document.getElementById("offlineTotalPayAmtDivId").innerHTML = "0.00";
		 	document.getElementById("offlineRefundAmtDivId").innerHTML = "0.00";
		 	
		 	
		 	if(reqObj.value.length > 1){
		 				 		
		 		document.forms[0].currentMode.value = 0;
		 		
		 		var temp = reqObj.value.split("^");
		 		
		 		document.forms[0].strGuarantorName.value = temp[4];
		 		document.forms[0].strGuarantorName.disabled = true;
		 				 		
		 		document.forms[0].strGuartorAddress.value = temp[5];
		 		document.forms[0].strGuartorAddress.disabled = true;
		 		
		 		document.forms[0].strGuarantorContactNo.value =temp[6];
		 		document.forms[0].strGuarantorContactNo.disabled = true;
		 		
		 		document.forms[0].strOffLineRequestType.value = temp[7];
		 		document.forms[0].strOffLineRequestType.disabled = true;
		 			
		 		document.forms[0].strOffLineBillingService.value = temp[8];
		 		document.forms[0].strOffLineBillingService.disabled = true;
		 			
		 	//	document.forms[0].strOffLineRaisingDepartment.value = temp[9];
		 	//	document.forms[0].strOffLineRaisingDepartment.disabled = true;	 				 		
		 				 		
		 		document.forms[0].strAge.value = temp[14];
		 		document.forms[0].strAge.disabled = true;
		 				 		
		 		document.forms[0].strAgeUnit.value = temp[15];
		 		document.forms[0].strAgeUnit.disabled = true;
		 				 		
		 		document.forms[0].strReferringPhysician.value = temp[16];
		 		document.forms[0].strReferringPhysician.disabled = true;
		 				 				 		
		 		document.forms[0].strGender.value = temp[17];
		 		document.forms[0].strGender.disabled = true;		 		
		 				 		
		 		document.forms[0].strReferringPhysicianContactNo.value = temp[18];
		 		document.forms[0].strReferringPhysicianContactNo.disabled = true;		 		
		 				 		
		 		document.getElementById("receiptTariffDivId").style.display = "none";
		 		
		 		resetMultiRow(3);
		 		
		 		addRows(new Array('strOfflinePaymentMode','strOfflinePaymentDtls','strOfflineAmount'),new Array('s','t','t'),'3','1','R');
		 	
		 		
		 		document.getElementById("onlineRequestTariffDivId").style.display = "block";
		 		
		 		
		 	}else{
		 		document.forms[0].currentMode.value = 1;
		 					 			
		 		document.forms[0].strOffLineRequestType.selectedIndex = 0;
		 		document.forms[0].strOffLineRequestType.disabled = false;
		 		
		 		document.forms[0].strOffLineBillingService.selectedIndex = 0;
		 		document.forms[0].strOffLineBillingService.disabled = false;
		 		
		 	//	document.forms[0].strOffLineRaisingDepartment.selectedIndex = 0;
		 	//	document.forms[0].strOffLineRaisingDepartment.disabled = false;
		 		
		 		document.forms[0].strGuarantorName.value ="";
		 		document.forms[0].strGuarantorName.disabled = false;
		 		
		 		document.forms[0].strGuarantorContactNo.value ="";
		 		document.forms[0].strGuarantorContactNo.disabled = false;
		 		
		 		document.forms[0].strGuartorAddress.value ="";
		 		document.forms[0].strGuartorAddress.disabled = false;
		 		
		 				 		
		 		document.forms[0].strAge.value = "";
		 		document.forms[0].strAge.disabled = false;
		 				 		
		 		document.forms[0].strAgeUnit.value = 0;
		 		document.forms[0].strAgeUnit.disabled = false;
		 				 		
		 		document.forms[0].strReferringPhysician.value = "";
		 		document.forms[0].strReferringPhysician.disabled = false;
		 				 				 		
		 		document.forms[0].strGender.value = "";
		 		document.forms[0].strGender.disabled = false;	
		 		
		 		document.forms[0].strReferringPhysicianContactNo.value = "";
		 		document.forms[0].strReferringPhysicianContactNo.disabled = false;		 
		 		
		 		resetMultiRow(2);
		 		
		 		addRows(new Array('strOfflineTariffName','strOfflineTariffDetailsHidden','strOfflineTempTariffRateUnit','strOfflineTariffRateUnit','strOfflineTariffQty','strOfflineTariffUnit','strOfflineTariffNetAmount','strOfflineTotalActualTariffAmtVal'),new Array('t','t','t','t','t','s','t','t','t','t','t','t'),'2','1','R')
		 		
		 		resetMultiRow(3);
		 		
		 		addRows(new Array('strOfflinePaymentMode','strOfflinePaymentDtls','strOfflineAmount'),new Array('s','t','t'),'3','1','R');
		 		
		 		document.getElementById("receiptTariffDivId").style.display = "block";
		 		
		 		document.getElementById("onlineRequestTariffDivId").style.display = "none";
		 		
		 	}
		 	
		 }
		
	
	
	
	function getOffLineGroupDtls(){
		
		var hmode = "GROUPDTLS"; 
			var url = "CashCollectionWithoutCrTransCNT.cnt?hmode="+hmode+"&hService="+document.forms[0].strOffLineHospitalService[document.forms[0].strOffLineHospitalService.selectedIndex].value+"&withPack=0";
		
			ajaxFunction(url,"5");
	}
	
	  
	
	/**
		 * getOffLineTariffUnit
		 * @param {String} unitId
		 * @param {String} baseValue
		 * @param {String} index 
		 */
		 function getOffLineTariffUnit(unitId,baseValue,index) {
		 	if(unitId!=0 && baseValue!=0){
		 	var hmode = "TRFUNIT"; 
			var url = "CashCollectionWithoutCrTransCNT.cnt?hmode="+hmode+"&unitId="+unitId+"&baseValue="+baseValue+"&rowIndex="+index;
			
			ajaxFunction(url,"10");
		 	}else{
		 		document.getElementById("offlineTariffUnitDivId"+index).innerHTML = "<select name='strOfflineTariffUnit' id='strOfflineTariffUnit"+index+"' class='comboMin' onChange='calcOffLineTariffNetAmount(\""+index+"\");'><option value=0>Select Value</option></select>";				
		 			 		
		 		calcOffLineTariffNetAmount(index);
		 	}
		 }
	
	
	 var gblPopupParent = "";
	 
	 /**
	  * showTariffPopup
	  * @param {Link Object} parent 
	  */
	  function showTariffPopup(obj,hidVal) {
	    	
	  	gblPopupParent = obj;
	  	
	  	var hmode = "REFUNDBILLTARIFFPOPUPDTLS"; 
			var url = "CashCollectionWithoutCrTransCNT.cnt?hmode="+hmode+"&hidValue="+hidVal;

			ajaxFunction(url,"13");
	  }
	  
	  
	  // without cr No logics
	  
	  /**
	   * getwithoutCrNoBillService 
	   */
	   function getwithoutCrNoBillService() {
	   	
	   	var hmode = "BILLDTLS"; 
			var url = "CashCollectionWithoutCrTransCNT.cnt?hmode="+hmode+"&hService="+document.forms[0].strOffLineHospitalService.value+"&requestType="+document.forms[0].strOffLineRequestType[document.forms[0].strOffLineRequestType.selectedIndex].value;
						
			ajaxFunction(url,"17");
	   	
	   }
	  
	  
	  
	  
	var trfdelIndex ;
	var trfObj;
	var trfPreviousData = "";
	var tempCode = "";
	
		
	function getwithoutCrNoTariffDtls(obj , eve , delIndex){

  
		trfdelIndex = delIndex;
		trfObj = obj;
		
	 
		var key;
		var previousDate = obj.value;
		
		if(window.event)
		key = window.event.keyCode;
	else
	{
		if (eve)
			key = eve.which;
		 
	}		
		
	tempCode = key;
 
 
 if(tempCode == 222){
 			
 			var input = document.getElementById('strOfflineTariffName'+delIndex).value;
 			document.getElementById('strOfflineTariffName'+delIndex).value = input.substring(0,input.length-1);
 			return false;
 			
 		}
 
	   
			if(obj.value.length >= 1 && obj.value.length <= 3 && trfPreviousData != previousDate){
				
			trfPreviousData = previousDate;
			 
			var hmode = "TARIFFDTLS"; 
			var url =  "CashCollectionWithoutCrTransCNT.cnt?hmode="+hmode+"&groupId="+document.forms[0].strOffLineGroup[document.forms[0].strOffLineGroup.selectedIndex].value+"&hService="+document.forms[0].strGeneralChargeTypeId.value+"&treatmentCat="+document.forms[0].strOffLineTreatmentCategory.value+"&ward="+document.forms[0].strWardTypeId.value+"&searchLetter="+obj.value;
				
			ajaxFunction(url,"18");
			
			
			}else{
				

			searchSel(eve,'strOfflineTariffName'+delIndex,'1',obj);		

				
			}
			
	}
	
	
	 
	 
	 /**
	  * initOfflineDetails
	  *
	  */
	  function initOfflineRaisingDepartment() {
	  		  	

	  		 
	  		 var chargeTypeId = document.forms[0].strOffLineHospitalService[document.forms[0].strOffLineHospitalService.selectedIndex].value;
	  		 var crNo = document.forms[0].strCrNo.value;
	  		 
	  		 	var hmode = "OFFLINEDEPTDTL"; 
			var url = "CashCollectionWithoutCrTransCNT.cnt?hmode="+hmode+"&strChargeTypeId="+chargeTypeId+"&strCrNo="+crNo;
															
			ajaxFunction(url,"23"); 	
	  		  	
	  }
	 
	 
	 /**
	  * initOfflineTreatmentCategory 
	  */
	  function initOfflineTreatmentCategory() {
	  	
	  	 var chargeTypeId = document.forms[0].strOffLineHospitalService[document.forms[0].strOffLineHospitalService.selectedIndex].value;
	  		 var crNo = document.forms[0].strCrNo.value;
	  		 
	  		 	var hmode = "OFFLINETREATCATDTL"; 
			var url = "CashCollectionWithoutCrTransCNT.cnt?hmode="+hmode+"&strChargeTypeId="+chargeTypeId+"&strCrNo="+crNo;
															
			ajaxFunction(url,"24"); 
	  	
	  }
	  
	  
	  
	 /**
	  * resetTariffs
	  */
	  function resetTariffs() {
	  	
	  	previousData = "";
	  	
	  }
	 	
	 
	 
	function getAjaxResponse(res,mode)
	{
	
				
	  var err = document.getElementById("errMsg");
	  	  
      var temp = res.split("####");
   
       if(temp[0] == "ERROR")
	   {
          	err.innerHTML = temp[1];
          	err.style.display = "block";
          	return;
       }
       
		
		
		if(mode == '5'){
				
				document.getElementById("groupDivId").innerHTML = "<table border='0' cellpadding='0' cellspacing='0'><tr><td class='TITLE'>Group </td><td class='TITLE'><select name='strOffLineGroup' class='comboMax' onchange='resetTariffs();'>"+res+"</select></td></tr></table>";
			
				
		}
		
		 
		 
		
		if(mode == '8'){
						
				document.getElementById("packageGroupDivId").innerHTML = "<b>Group</b> <select name='strOffLinePackageGroup' class='comboNormal' onchange='getPackageDtls(this);'>"+res+"</select>";
				
			 
		}
		
	
	
	if(mode == '10'){
			
			var temp = res.split('@');
					
			document.getElementById("offlineTariffUnitDivId"+temp[0]).innerHTML = "<select name='strOfflineTariffUnit' id='strOfflineTariffUnit"+temp[0]+"' class='comboMin' onChange='calcOffLineTariffNetAmount(\""+temp[0]+"\");'>"+temp[1]+"</select>";				
				
					calcOffLineTariffNetAmount(temp[0]);
		}
		
		  
		
		if(mode == '13'){
					
			document.getElementById("offlineBillTariffPopupDetailsDivId").innerHTML = res;				
				
				display_popup_menu(gblPopupParent,"offlineBillTariffPopupDetailsDivId",'','');
		
		}
		
		if(mode=="14"){ 
     var rateVal = res.split('@');     //Here We Split the Response from Index
     var objVal = document.getElementById("particularDtlsId"+rateVal[1]);
     objVal.innerHTML = rateVal[0];
	 objVal.style.display="block";
   
  }
		
		if(mode == '15'){
					
			document.getElementById("refundTariffPopupDetailsDivId").innerHTML = res;				
				
				display_popup_menu(refundTariffPopupParent,"refundTariffPopupDetailsDivId",'','');
		
		}
		
		if(mode == '16'){
			
			
			var temp = res.split('@');
					
			document.getElementById("tariffRefundUnitDivId"+temp[0]).innerHTML = "<select name='strTariffRefundUnit' id='strTariffRefundUnit"+temp[0]+"' class='comboMin' onChange='calcRefundTariffAmount(\""+temp[0]+"\");'>"+temp[1]+"</select>";				
				
					 calcRefundTariffAmount(temp[0]);
		}
		
		
		if(mode == '17'){
						
										
			billObj = document.getElementById("billServiceDivId");
			
			billObj.innerHTML = "<select class='comboNormal' name='strOffLineBillingService' onchange=''>"+res+"</select>";
			
			
			
		}
		
		if(mode == '18'){
					
				//	alert(res);
					
				//document.getElementById("dropdown1").innerHTML = "";
				document.getElementById("dropdown1").innerHTML = res;
			 
			 	searchSelWithCode(tempCode,'strOfflineTariffName'+trfdelIndex,'1',trfObj);
				
		}
		
		if(mode == '19'){
										
				if(res.indexOf('@') == -1){
				
				if(document.getElementById("errMsg").innerHTML.length <= 0){
					document.getElementById("errMsg").innerHTML = "";
					document.getElementById("errMsg").style.display = "none";
				}
				
			  
				
				document.getElementById("withoutCrNoRefundTariffDivId").innerHTML = res;
				document.getElementById("withoutCrNoRefundTariffDivId").style.display = "block";
				document.getElementById("otherRefundDetailsDivId").style.display = "block";
				
				document.getElementById("paymentModeDtlsDivId").style.display = "block";
				
			//  document.forms[0].strOffLineRaisingDepartment.disabled = false;	
			//  document.forms[0].strOffLineRaisingDepartment.value = document.forms[0].strGarantorDepartmentId.value;
				
		}else{
			
			var temp = res.split('@');
				
				if(temp[1].length > 0){
					document.getElementById("errMsg").innerHTML = temp[1];
					document.getElementById("errMsg").style.display = "block";
				}else{
					if(document.getElementById("errMsg").innerHTML.length <= 0){
						document.getElementById("errMsg").innerHTML = "";
						document.getElementById("errMsg").style.display = "none";
				}
				}
				document.getElementById("withoutCrNoRefundTariffDivId").style.display = "none";
				document.getElementById("withoutCrNoRefundTariffDivId").style.display = "none";
				document.getElementById("otherRefundDetailsDivId").style.display = "none";
				document.getElementById("paymentModeDtlsDivId").style.display = "none";
				
				document.forms[0].strBillNo.value = "";
				document.forms[0].strBillNo.disabled = false;
				document.forms[0].strBillNo.focus();
		}
		}
		
		  
				
				
	
		if(mode == '23'){
			
			
			  var temp = res.split("####");
   
		       if(temp[0] == "ERROR")
			   {
		          	err.innerHTML = temp[1];
		          	err.style.display = "block";
		          	return;
		       }
			
		//	document.getElementById("offlineRaisingDepartmentDivId").innerHTML = "<select class='comboNormal' name='strOffLineRaisingDepartment' onchange='getEpisodeList(this);' >"+res+"</select>";	
			
			
			initOfflineTreatmentCategory();
			
		}
			
		
		if(mode == '24'){
			
			
			  var temp = res.split("####");
   
		       if(temp[0] == "ERROR")
			   {
		          	err.innerHTML = temp[1];
		          	err.style.display = "block";
		          	return;
		       }
			
			
			document.getElementById("offlineTreatmentCategoryDivId").innerHTML = "<select class='comboNormal' name='strOffLineTreatmentCategory' onfocus='setTariff(),showAlert()' onchange='changeTreatment()'>"+res+"</select>";	
			
			
				if(document.getElementsByName("strCatgoryCode").length > 0){
			
					
					 document.forms[0].strOffLineTreatmentCategory.value = document.forms[0].strCatgoryCode.value;
				}
			
			if(document.forms[0].strOffLineHospitalService[document.forms[0].strOffLineHospitalService.selectedIndex].value == '2'){
				
					 document.forms[0].strOffLineTreatmentCategory.value = document.forms[0].strCatgoryCode.value;
					
			}
			
			
			initOfflineWard();
			
		}
		
		  
		  	if(mode == '26'){
			
			
			//	document.getElementById("normalMsg").innerHTML = "";
			//document.getElementById("normalMsg").style.display = "block";
			
			 var temp = res.split("####");
   
		       if(temp[0] == "ERROR"){
		       	
		       	document.getElementById("normalMsg").style.display ="none";
		       	
		          	err.innerHTML = temp[1];
		          	err.style.display = "block";
		          	return;
		       }else{
		       	
		      // 	alert("res : "+res);
		       	
		       	document.getElementById("onlineRequestTariffDivId").innerHTML = res;
		     
		       	withoutCrNoChangeMode(gblWithoutCrNoReqObj);
		       	
		      
		       	
		       }
		
		}	
		  
		
			if(mode == '27'){
			
			
			//	document.getElementById("normalMsg").innerHTML = "";
			//document.getElementById("normalMsg").style.display = "block";
			
			 var temp = res.split("####");
   
		       if(temp[0] == "ERROR"){
		       	
		       	document.getElementById("normalMsg").style.display ="none";
		       	
		          	err.innerHTML = temp[1];
		          	err.style.display = "block";
		          	return;
		       }else{
		       	
		      var tempVal = res.split("@@");
		      
		      	if(tempVal[0] == '0'){
		      		
		      			alert("No Tariff Found to The Corresponding Tariff Code ");
		      			document.forms[0].strTariffCode.value = '';
		      			document.forms[0].strTariffCode.focus();
		      			return false;
		      		
		      	}else{
		      		
		      		var indexVal = '2-'+document.multirow.rowIndex2.value;
		      				      		
		      		 varObj = document.getElementsByName("strOfflineTariffName");
		      		 
		      		 for(var i=0; i<varObj.length - 1; i++) {
		      		 	
		      		 		if(varObj[i].value == tempVal[1]){
		      		 			
		      		 			alert("Tariff Name Already Exists");
		      		 			document.forms[0].strTariffCode.value = '';
		      					document.forms[0].strTariffCode.focus();
		      			
		      		 			return false;
		      		 		}
		      		 	
		      		 }
		      		 
		      			
		      				setSelectedTariff(indexVal ,tempVal[1], tempVal[2] );
		      			
		        
		      			document.forms[0].strTariffCode.value = '';
		      			document.forms[0].strTariffCode.focus();
		      		
		      	}	      
		       	
		       }
		
		}	
		
		
		if(mode == '28'){
		
			 
				 var temp = res.split("####");
   
		       if(temp[0] == "ERROR"){
		       	
		       	document.getElementById("normalMsg").style.display ="none";
		       	
		          	err.innerHTML = temp[1];
		          	err.style.display = "block";
		          	return;
		       }else{
		       
		       		var tempVal = res.split('^');
		       		
		       		if(tempVal.length >= 8){
		       			if(tempVal[3] == '1')	
		       				document.forms[0].strGender.value = 'M';
		       			else if (tempVal[3] == '2')
		       				document.forms[0].strGender.value = 'F';
		       			else
		       				document.forms[0].strGender.value = 'N';
			       		document.forms[0].strGuarantorName.value = tempVal[0];
			       		document.forms[0].strAge.value = tempVal[1];
			       		document.forms[0].strAgeUnit.value = tempVal[2];
			       		//document.forms[0].strGender.value = tempVal[3];
			       		document.forms[0].strGuartorAddress.value = tempVal[4];
			       		document.forms[0].strGuarantorContactNo.value = tempVal[5];
			       		document.forms[0].strReferringPhysician.value = tempVal[6];
			       		document.forms[0].strReferringPhysicianContactNo.value = tempVal[7];
 				       					     
			       		document.forms[0].strPreviousCrNo.disabled = false;
			       		document.forms[0].strGuarantorName.disabled = true;
			       		document.forms[0].strAge.disabled = true;
			       		document.forms[0].strAgeUnit.disabled = true;
			       		document.forms[0].strGender.disabled = true;
			       		document.forms[0].strGuartorAddress.disabled = true;
			       		document.forms[0].strGuarantorContactNo.disabled = true;
			       		document.forms[0].strReferringPhysician.disabled = true;
			       		document.forms[0].strReferringPhysicianContactNo.disabled = true;
						//document.forms[0].strOffLineRaisingDepartment.disabled = true;
						//document.forms[0].strOffLineRaisingDepartment.disabled = false;
			       				       					       				       		
						document.forms[0].strPreviousCrNoDtlFlag.value = "1";			       				       				       		
			       		document.forms[0].strTariffCode.focus();		       		
			       		document.forms[0].strTariffCode.value = '';
		      			 	       		
			       	 		       		
		       		}else{
		       			
		       			document.forms[0].strPreviousCrNoDtlFlag.value = "0";				
		       				
		       			document.forms[0].strGuarantorName.value = '';
		       			
		       			document.forms[0].strPreviousCrNo.disabled = false;		       					       
			       		document.forms[0].strGuarantorName.disabled = false;	
			       		document.forms[0].strAge.disabled = false;	
			       		document.forms[0].strAgeUnit.disabled = false;	
			       		document.forms[0].strGender.disabled = false;	
			       		document.forms[0].strGuartorAddress.disabled = false;	
			       		document.forms[0].strGuarantorContactNo.disabled = false;	
			       		document.forms[0].strReferringPhysician.disabled = false;	
			       		document.forms[0].strReferringPhysicianContactNo.disabled = false;	
		       			//document.forms[0].strOffLineRaisingDepartment.disabled = false;
		       			
		      			document.forms[0].strGuarantorName.focus();	
		       			 
		       		}
		       		
		       
		       }
				
			
			 		
		}
		
							
	}

	/**
	 * updateStatusResponse
	 * @param {}  
	 */
	 function updateStatusResponse() {
	 	
	 		document.getElementById("normalMsg").style.display = "block";
	 	
			 var temp = res.split("####");
   
		       if(temp[0] == "ERROR"){
		       	
		       	 	document.getElementById("normalMsg").style.display ="none";
		       	
		          	err.innerHTML = temp[1];
		          	err.style.display = "block";
		          	return;
		       }
			
			alert("Please Go to the Re Print Service to Re-Print the Receipt");
						
			return false;
			
	 	
	 }
	


/*
 function myfunc(obj)
      {
      alert("function called");
            window.jsCode ;
            alert("function called11");
           // var scriptTags = obj.getElementsByTagName("<script>");
            var scriptTags = document.getElementById("script");
            alert("function called12");
            var head = document.getElementsByTagName('HEAD')[0];
            alert("function called13 = " + scriptTags.innerHTML);
            
            var scriptObj = document.createElement("script");  
            alert("4");          
                    scriptObj.setAttribute("type", "text/javascript");
                    alert("5");
                    //scriptObj.setAttribute("src", scriptTags[no].src);
                    window.jsCode = scriptTags.innerHTML;
                        setTimeout('eval(window.jsCode[0])',100);      // Has to wait because we want to make all objects part of the window object(global variables) instead of locale
                    alert("6");
                   // head.appendChild(scriptObj);
                    alert("7");
                    
            
            for(var no=0;no<scriptTags.length;no++){                  
                if (scriptTags[no].src){
                alert("inside src");
                    var head = document.getElementsByTagName("head")[0];
                    var scriptObj = document.createElement("script");            
                    scriptObj.setAttribute("type", "text/javascript");
                    scriptObj.setAttribute("src", scriptTags[no].src);             
                    head.appendChild(scriptObj);
            
                }else{                      
                        var code = scriptTags[no].innerHTML;
                        alert("code = " + code );                        
                        window.jsCode[no] = code;
                        setTimeout('eval(window.jsCode[' + no + '])',100);      // Has to wait because we want to make all objects part of the window object(global variables) instead of locale
                }            
            } 
                 
      }
	*/

	function validateCheckDD(){

var hisValidator = new HISValidator("EstEnquiryTransBean");  
		
	hisValidator.addValidation("strPayCDDBankName", "req", "Bank Name is a Mandatory Field" );
	hisValidator.addValidation("strChequeDDNo","req", "Cheque / DD No. is a Mandatory Field" );
	hisValidator.addValidation("strChequeDDDate", "req", "Cheque / DD Date is a Mandatory Field" );
	var retVal = hisValidator.validate(); 
	hisValidator.clearAllValidations();
		if(retVal){
				
				var resultVal = document.forms[0].strPayCDDBankName.value+","+document.forms[0].strChequeDDNo.value+","+document.forms[0].strChequeDDDate.value;
				
				gblPayDtls.value = resultVal;
				gblPayEdit.disabled = false;
				
				
				hide_popup('payDtlCDDMenu');
			
				gblAmount.focus();
				
		}else{
		
		return false;
		}

}

function validateCreditDebit(){

var hisValidator = new HISValidator("EstEnquiryTransBean");  
	
	hisValidator.addValidation("strPayCDBankName", "req", "Bank Name is a Mandatory Field" );
	hisValidator.addValidation("strCardNo","req", "Card No. is a Mandatory Field" );
	hisValidator.addValidation("strCardNo","minlen=4", "Card No. must be 4 Digits" );
	hisValidator.addValidation("strAuthNo","req", "Auth No. is a Mandatory Field" );
	hisValidator.addValidation("strAuthDate", "req", "Auth Date is a Mandatory Field" );
	hisValidator.addValidation("strAuthDate", "date", "Please Enter a Valid Auth Date" );
	hisValidator.addValidation("strCardType", "dontselect=0", "Please Select a Card Type" );
	
	var retVal = hisValidator.validate(); 
	hisValidator.clearAllValidations();
		if(retVal){
			
			var resultVal = document.forms[0].strPayCDBankName.value+","+document.forms[0].strCardNo.value+","+document.forms[0].strAuthNo.value+","+document.forms[0].strAuthDate.value+","+document.forms[0].strCardType[document.forms[0].strCardType.selectedIndex].text;
				
				gblPayDtls.value = resultVal;
				gblPayEdit.disabled = false;
				
				hide_popup('payDtlCDMenu');
				
					gblAmount.focus();
		}else{
		
		return false;
		}

}


function validateClientDetails(){

var hisValidator = new HISValidator("EstEnquiryTransBean");  
	
	hisValidator.addValidation("strPayCNTClientName", "dontselect=0", "Client Name is a Mandatory Field" );
	hisValidator.addValidation("strClientAuthNo","req", "Auth No. is a Mandatory Field" );
	hisValidator.addValidation("strClientAuthDate", "req", "Auth Date is a Mandatory Field" );
	hisValidator.addValidation("strClientAuthDate", "date", "Please Enter a Valid Auth Date" );
	
	var retVal = hisValidator.validate(); 
	hisValidator.clearAllValidations();
		if(retVal){
			
			var resultVal = document.forms[0].strPayCNTClientName[document.forms[0].strPayCNTClientName.selectedIndex].value+","+document.forms[0].strClientAuthNo.value+","+document.forms[0].strClientAuthDate.value;
				
				gblPayDtls.value = resultVal;
				gblPayEdit.disabled = false;
				
				hide_popup('payDtlCLTMenu');
				
					gblAmount.focus();
		}else{
		
		return false;
		}

}

	function displayPayDetails(parent,payDtl,payEdit,payAmt , mode){
		
		var optVal = parent.value;
		
			totalVal = parseFloat("0");
		
		if(mode == '0'){
			
			totalVal = document.forms[0].strOnlineTotalRecAmount.value;	  		  	
		  	totalVal = parseFloat(totalVal);
		  	
		}else{
			
			totalVal = document.forms[0].strOfflineTotalRecAmount.value;	  		  	
		  	totalVal = parseFloat(totalVal);
			
		}
		
		if(totalVal < 0){
			
				if(optVal == 2 || optVal == 3){
					
					alert("This Mode is Not Applicable for Refund");
					
					parent.selectedIndex = 0;
					document.getElementById(payAmt).focus(); 
					
					return false;
				}

		}
					
		
		gblPayMode = parent;
		 gblPayDtls = document.getElementById(payDtl);
		 gblPayEdit = document.getElementById(payEdit);
		 gblAmount  = document.getElementById(payAmt); 
		 
			document.forms[0].strPayCDDBankName.value = "";
			document.forms[0].strChequeDDNo.value = "";
			//document.forms[0].strChequeDDDate.value = "";
		
		document.forms[0].strPayCDBankName.value = "";
		document.forms[0].strCardNo.value = "";
		document.forms[0].strAuthNo.value = "";
		//document.forms[0].strAuthDate.value = "";
		document.forms[0].strCardType.selectedIndex = 0;
		
		document.forms[0].strPayCNTClientName.selectedIndex = 0;
		document.forms[0].strClientAuthNo.value = "";
	
		
		gblPayDtls.value = "";
		gblPayDtls.disabled = true;
		gblPayEdit.disabled = true;
		
		
	
		if(optVal == 2 || optVal == 3){
		
						
		//	hide_popup('payDtlCDDMenu');
			
			popup('payDtlCDDMenu' , '250','250');
			
			document.forms[0].strPayCDBankName.focus();
			
		}else if(optVal == 4 || optVal == 5){
		
		//	hide_popup('payDtlCDMenu');
			
			popup('payDtlCDMenu' , '250','250');
			
		document.forms[0].strPayCDDBankName.focus();
		
		}else if(optVal == 6){
		
		//	hide_popup('payDtlCDMenu');
			
			popup('payDtlCLTMenu' , '250','250');
			
		document.forms[0].strPayCNTClientName.focus();
		
		}else{
		
				//hide_popup('payDtlCDDMenu');
				
				gblPayDtls.disabled = false;
				
				gblAmount.focus();
		}
		
	}


function displayPayWithDataDetails(parent,payDtl,payModeObj){
		
		 gblPayDtls = document.getElementById(payDtl);
		
		gblPayEdit = parent.value;
		
			var myVal = gblPayDtls.value; 
			var temp = myVal.split(',');
		
		var payModeVal = document.getElementById(payModeObj).value;
			
			if(payModeVal == 2 || payModeVal == 3){
					document.forms[0].strPayCDDBankName.value = temp[0];
					document.forms[0].strChequeDDNo.value = temp[1];
					document.forms[0].strChequeDDDate.value = temp[2];
					
					popup('payDtlCDDMenu', '250','250');
					
			}else if(payModeVal == 4 || payModeVal == 5){
				
				document.forms[0].strPayCDBankName.value = temp[0];
				document.forms[0].strCardNo.value = temp[1];
				document.forms[0].strAuthNo.value = temp[2];
				document.forms[0].strAuthDate.value = temp[3];
				if(temp[4].length == 4){
				document.forms[0].strCardType.selectedIndex = 2;
				}else{
				document.forms[0].strCardType.selectedIndex = 1;
				}
				
				popup('payDtlCDMenu' , '250','250');
				
			}else if(payModeVal == 6){
				
				document.forms[0].strPayCNTClientName.value = temp[0];
				document.forms[0].strClientAuthNo.value = temp[1];
				document.forms[0].strClientAuthDate.value = temp[2];
				
				popup('payDtlCLTMenu' , '250','250');
			}
			
	}

	function hidePayDetails(divId){
		
		if(gblPayEdit != "Edit"){
		
		document.forms[0].strChequeDDDate.value = document.forms[0].strTempCtDate.value;
		document.forms[0].strAuthDate.value = document.forms[0].strTempCtDate.value;
		document.forms[0].strClientAuthDate.value = document.forms[0].strTempCtDate.value;
		
		gblPayMode.selectedIndex = 0;
		gblPayDtls.disabled = false;
		gblAmount.focus();
		}
		hide_popup(divId);
		
	}

	function groupComboPartPayment(){

		var len = document.forms[0].dr.length;

		if(document.forms[0].dr.value == 0){
			
			document.forms[0].strRemarksCombo2.value = "";
			document.forms[0].strRemarksCombo2.disabled=false;
		
		}else{ 
			
			document.forms[0].strRemarksCombo2.value= document.forms[0].dr[document.forms[0].dr.selectedIndex].text;
			document.forms[0].strRemarksCombo2.disabled=true;
		
		}
	}


	function ftnTick(){

		if(document.forms[0].strChk_value.checked == true){

			document.forms[0].strPartpayment.disabled=false;
			document.getElementById("combo").style.display="block";
			document.forms[0].strChk_value.value = 1;
			
			document.forms[0].strOfflineTotalRecAmount.value = "0";
			document.getElementById("totalRecAmtDivId").innerHTML = "0.00";
			
					
		}else{
	
			document.forms[0].strPartpayment.disabled =true;
			document.forms[0].strPartpayment.value = document.forms[0].strdummypartpayment.value;
			document.getElementById("combo").style.display="none";
			document.forms[0].strChk_value.value = 0;
						
			document.forms[0].strOfflineTotalRecAmount.value = roundValue( document.forms[0].strPartpayment.value , 2 );
			document.getElementById("totalRecAmtDivId").innerHTML = roundValue( document.forms[0].strPartpayment.value , 2 );
			
		} 
		
		setTotalToAmount('0');
		
		//document.getElementsByName("strOfflineAmount")[0].focus();
		
		checkForOffLinePaymentLimit('1');
	}

function setSelectedTariffCode(userValue ,resultText, resultValue){
	
		document.forms[0].strTariffCode.value = resultText;
		document.forms[0].strTariffCode.focus();
	}

	function setSelectedTariff(userValue ,resultText, resultValue){
		
		 varObj = document.getElementsByName("strOfflineTariffName");
		      		 
		       		 
		      		 for(var i=0; i<varObj.length - 1; i++) {
		      		 	
		      		 		if(varObj[i].value == resultText){
		      		 			
		      		 			alert("Tariff Name Already Exists");
		      		 			document.forms[0].strTariffCode.value = '';
		      					document.forms[0].strTariffCode.focus();
		      			
		      		 			return false;
		      		 		}
		      		 	
		      		 }
		
			var tariffObj = document.getElementById("strOfflineTariffName"+userValue);
			tariffObj.value = resultText;
			tariffObj.readOnly="readonly";
			var temp = resultValue.split('^');
			
			
			if(temp[17] == '1'){
				
				document.getElementById("strOfflineTariffRateUnitDivId"+userValue).style.display = "none";	
				
				document.getElementById("strOfflineTempTariffRateUnitDivId"+userValue).innerHTML= "<input type='text' name='strOfflineTempTariffRateUnit' id='strOfflineTempTariffRateUnit"+userValue+"' class='txtFldMin' value='"+temp[4]+"'  maxlength='8' onkeypress='return validateData(event,7);' onkeyup='setRateValue(\""+userValue+"\");' >" +" / "+temp[14];
				
				document.getElementById("strOfflineTempTariffRateUnitDivId"+userValue).style.display = "block";
				
				document.getElementById("strOfflineTempTariffRateUnit"+userValue).value = temp[4];
				
				
			}else{
				
				document.getElementById("strOfflineTariffRateUnitDivId"+userValue).style.display = "block";
				document.getElementById("strOfflineTempTariffRateUnitDivId"+userValue).style.display = "none";
			}
			
			
			document.getElementById("strOfflineTariffRateUnit"+userValue).value = temp[4]+" / "+temp[14];
			document.getElementById("strOfflineTariffRateUnitDivId"+userValue).innerHTML = temp[4]+" / "+temp[14];
			
			//document.getElementById("strOfflineTariffDiscount"+userValue).value = 0;
			//document.getElementById("strOfflineTariffDiscountDivId"+userValue).innerHTML = 0 ;
			
			//document.getElementById("strOfflineTariffServiceTax"+userValue).value = temp[13];
			//document.getElementById("strOfflineTariffServiceTaxDivId"+userValue).innerHTML = temp[13];
			
			document.getElementById("strOfflineTariffQty"+userValue).value = 1;
			
			document.getElementById("strOfflineTariffNetAmount"+userValue).value = 0;
			document.getElementById("strOfflineTariffNetAmountDivId"+userValue).innerHTML = 0;
		
			document.getElementById("strOfflineTariffDetailsHidden"+userValue).value = resultValue;
		//	document.getElementById("strOfflineTariffDiscountConfigDetails"+userValue).value = '';
			
			getOffLineTariffUnit(temp[5],temp[6],userValue);
						
		document.getElementById("tariffFullNameDiv").innerHTML = "";
		
			if(isAddRowRequired("strOfflineTariffName")){
				generateRows();
			}
		 
		
			
	}

	function setDropDownSelectedTariff(userValue , resultValue){
		var a = resultValue.split('^');
		var b=true;
		for(var i = 0 ; i<a.length; i++)
			if(a[i]!="0"){
				b=false;
				break;
			}
		if(b || checkForDuplicate_select('strOfflineTariffName',document.getElementById("strOfflineTariffName"+userValue),'Tariff Name')){
		var temp = resultValue.split('^');

		
			if(temp[17] == '1'){
				
				document.getElementById("strOfflineTariffRateUnitDivId"+userValue).style.display = "none";	
				
				document.getElementById("strOfflineTempTariffRateUnitDivId"+userValue).innerHTML= "<input type='text' name='strOfflineTempTariffRateUnit' id='strOfflineTempTariffRateUnit"+userValue+"' class='txtFldMin' value='"+temp[4]+"'  maxlength='8' onkeypress='return validateData(event,7);' onkeyup='setRateValue(\""+userValue+"\");' >" +" / "+temp[14];
				
				document.getElementById("strOfflineTempTariffRateUnitDivId"+userValue).style.display = "block";
				
				document.getElementById("strOfflineTempTariffRateUnit"+userValue).value = temp[4];
				
				
			}else{
				
				document.getElementById("strOfflineTariffRateUnitDivId"+userValue).style.display = "block";
				document.getElementById("strOfflineTempTariffRateUnitDivId"+userValue).style.display = "none";
			}

			document.getElementById("strOfflineTariffRateUnit"+userValue).value = temp[4]+" / "+temp[14];
			document.getElementById("strOfflineTariffRateUnitDivId"+userValue).innerHTML = temp[4]+" / "+temp[14];
			
			//document.getElementById("strOfflineTariffDiscount"+userValue).value = 0;
			//document.getElementById("strOfflineTariffDiscountDivId"+userValue).innerHTML = 0 ;
			
			//document.getElementById("strOfflineTariffServiceTax"+userValue).value = temp[13];
			//document.getElementById("strOfflineTariffServiceTaxDivId"+userValue).innerHTML = temp[13];
			
			document.getElementById("strOfflineTariffQty"+userValue).value = 1;
			
			document.getElementById("strOfflineTariffNetAmount"+userValue).value = 0;
			document.getElementById("strOfflineTariffNetAmountDivId"+userValue).innerHTML = 0;
		
			document.getElementById("strOfflineTariffDetailsHidden"+userValue).value = resultValue;
			//document.getElementById("strOfflineTariffDiscountConfigDetails"+userValue).value = '';
			
			getOffLineTariffUnit(temp[5],temp[6],userValue);
		}
				
		document.getElementById("tariffFullNameDiv").innerHTML = "";
		
		document.getElementById("strOfflineTariffName"+userValue).focus();
		document.getElementById("strOfflineTariffName"+userValue).readOnly="readOnly";
		
			if(isAddRowRequired("strOfflineTariffName")){
				generateRows();
			}
		 		
		
	}

function showTariffSearchPopup(e,compName,index){
	
	
	var groupId = "0";
	var wardCode = "0";
		
	var groupId = document.forms[0].strOffLineGroup.value;
				
		if(document.getElementById("wardDivId") != null)		
		if(document.getElementById("wardDivId").style.display == "block" ){
			wardCode = document.forms[0].strOffLineWard.value;
		}
		
	tariffSearchPopUp(e,compName,document.forms[0].strOffLineHospitalService.value,document.forms[0].strOffLineTreatmentCategory.value,wardCode,groupId,'setSelectedTariff',index);
}



/**
 * showTariffSearchPopupOnClick
 */
 function showTariffSearchPopupOnClick() {
 	
 	
 		var groupId = "0";
	var wardCode = "0";
		
	var groupId = document.forms[0].strOffLineGroup.value;
				
		if(document.getElementById("wardDivId") != null)		
		if(document.getElementById("wardDivId").style.display == "block" ){
			wardCode = document.forms[0].strOffLineWard.value;
		}
				
		var compName = document.getElementsByName("strOfflineTariffName")[document.getElementsByName("strOfflineTariffName").length - 2].id;
 		
 		var index = document.getElementsByName("strOfflineTariffName")[document.getElementsByName("strOfflineTariffName").length - 2].id.split("-")[1];
 		
 		var index = "2-"+index;
 		
 	
 	tariffSearchPopUpWithoutEvent(compName,document.forms[0].strOffLineHospitalService.value,document.forms[0].strOffLineTreatmentCategory.value,wardCode,groupId,'setSelectedTariff',index);
 	
 }


function showTariffCodeSearchPopup(e,compName,index){
	
	var groupId = "0";
 
		
	var groupId = document.forms[0].strOffLineGroup.value;
				
		if(document.getElementById("wardDivId") != null)		
		if(document.getElementById("wardDivId").style.display == "block" ){
			wardCode = document.forms[0].strOffLineWard.value;
		}
		
	tariffCodeSearchPopUp(e,compName,document.forms[0].strGeneralChargeTypeId.value,document.forms[0].strOffLineTreatmentCategory.value,document.forms[0].strWardTypeId.value,groupId,'setSelectedTariffCode',index);
}


var gblTariffDiscountDtlsId = "";

	function hidePkgDtls(index){
		
		
			document.forms[0].strPkgServiceFlag.value = 0;
				pkgDivObj = document.getElementById("pkgConfigId"+index);							
				pkgDivObj.style.display="none";
	}

	function getOffLinePkgDiscountDetails(divId,parent){
			
			popup('pkgDiscountDtls' , '250','250');
			
	}

	function getOffLineTariffDiscountDetails(divId,parent){
			
		/*	if(document.forms[0].strOffLineHospitalService[document.forms[0].strOffLineHospitalService.selectedIndex] != null){ // With Cr No. Cleark Discount Logic
				
				
			var hospService = document.forms[0].strOffLineHospitalService[document.forms[0].strOffLineHospitalService.selectedIndex].value
								
			if(hospService == '1'){
				
				var opdDis =  document.forms[0].strIsOpdDiscount.value;
				
				if(opdDis == '0'){
					
					alert("Clerk Discount is Not Applicable");
					return false;
					
				}
				
				
			}else if(hospService == '2'){
				
				var ipdDis =  document.forms[0].strIsIpdDiscount.value;
				
				if(ipdDis == '0'){
					
					alert("Clerk Discount is Not Applicable");
					return false;
					
				}
				
			}else{
				
				var emeDis =  document.forms[0].strIsEmergencyDiscount.value;
				
				if(emeDis == '0'){
					
					alert("Clerk Discount is Not Applicable");
					return false;
					
				}
				
			}
			
			}else{ // Without Cr No. Cleark Discount Logic
				
				
				var opdDis =  document.forms[0].strIsOpdDiscount.value;
				
				if(opdDis == '0'){
					
					alert("Clerk Discount is Not Applicable");
					return false;
					
				}
				
				
			}
			
			
			gblTariffDiscountDtlsId = divId;
			
			var tariff =  document.getElementById("strOfflineTariffName"+divId).value;	 		 	
	 		 	
		if(tariff != ''){	
			
			if(document.getElementById("strOfflineTariffDiscountConfigDetails"+gblTariffDiscountDtlsId).value != ''){
				
				var temp =  document.getElementById("strOfflineTariffDiscountConfigDetails"+gblTariffDiscountDtlsId).value.split(",");
										
				document.forms[0].strOffLineTariffDiscountUnit.value = temp[0];
				document.forms[0].strOffLineTariffDiscountType.value = temp[1];
				document.forms[0].strOffLineTariffDiscountBy.value = temp[2];
				document.forms[0].strOffLineTariffDiscountReason.value = temp[3];
				document.forms[0].strOffLineTariffDiscountReasonText.value = temp[4];
				document.forms[0].strOffLineTariffDiscountDate.value = temp[5];	
							
			}
			
				popup('tariffDiscountDtls' , '250','250');
			
				setReasonText();
				document.forms[0].strOffLineTariffDiscountUnit.focus();
		}else{
			alert("Please Select a Tariff");
		}*/
	}


function getOnLineWithoutCrNoTariffDiscountDetails(divId,parent){
			
							
			/*	
				var opdDis =  document.forms[0].strIsOpdDiscount.value;
				
				if(opdDis == '0'){
					
					alert("Clerk Discount is Not Applicable");
					return false;
					
				}
				
				
					
			
			gblTariffDiscountDtlsId = divId;
			
			var tariff =  document.getElementById("strTariffDetailsId"+divId);	 		 	
	 		 	
		if(tariff.checked){	
			
			if(document.getElementById("strTariffDiscountConfigDetails"+gblTariffDiscountDtlsId).value.length > 1){
				
				var temp =  document.getElementById("strTariffDiscountConfigDetails"+gblTariffDiscountDtlsId).value.split(",");
										
			//	document.forms[0].strOffLineTariffDiscountUnit.value = temp[0];
			//	document.forms[0].strOffLineTariffDiscountType.value = temp[1];
			//	document.forms[0].strOffLineTariffDiscountBy.value = temp[2];
			//	document.forms[0].strOffLineTariffDiscountReason.value = temp[3];
			//	document.forms[0].strOffLineTariffDiscountReasonText.value = temp[4];
			//	document.forms[0].strOffLineTariffDiscountDate.value = temp[5];	
							
			}
			
				popup('tariffDiscountDtls' , '250','250');
			
				setReasonText();
				document.forms[0].strOffLineTariffDiscountUnit.focus();
		}else{
			alert("Please Select a Tariff");
		}*/
	}


function hideOffLinePkgDiscountDetails(divId){
		
		hide_popup(divId);
	}
	
	
	function hideWithoutCrNoTariffDiscountDetails(divId){
		
		/*	var conf = confirm("Are you Sure to Cancel the Discount");
		
			  if(conf){
			  	
			  		if(document.getElementById("strOfflineTariffDiscountConfigDetails"+gblTariffDiscountDtlsId) != null)
			  		document.getElementById("strOfflineTariffDiscountConfigDetails"+gblTariffDiscountDtlsId).value = "";
			  		
			  		
			  		if(document.getElementById("strTariffDiscountConfigDetails"+gblTariffDiscountDtlsId) != null)
			  		document.getElementById("strTariffDiscountConfigDetails"+gblTariffDiscountDtlsId).value = "";
			  		
			  		document.forms[0].strOffLineTariffDiscountReasonText.value = document.forms[0].strTempCtDate.value;
			  		
			  	
			  		if(document.forms[0].strWithoutCrNoOnlineRequestNo[document.forms[0].strWithoutCrNoOnlineRequestNo.selectedIndex].value.length > 1){
			  			
			  		document.getElementById("strTariffDiscountAmtDivId"+gblTariffDiscountDtlsId).value = 0;					
					document.getElementById("strTariffDiscountAmtDivId"+gblTariffDiscountDtlsId).innerHTML = "0";
			  			
			  			calcOnlineWithoutCrNoTariffNetAmount(document.getElementById("strTariffDetailsId"+gblTariffDiscountDtlsId).value ,gblTariffDiscountDtlsId);
			  			
			  		}else{
			  			
			  		document.getElementById("strOfflineTariffDiscount"+gblTariffDiscountDtlsId).value = 0;					
					document.getElementById("strOfflineTariffDiscountDivId"+gblTariffDiscountDtlsId).innerHTML = "0";
					
					calcOffLineTariffNetAmount(gblTariffDiscountDtlsId);
			  			
			  		}
			  	
			  														
					
												
							
				
			  	
			  	
			  }	
		
				document.forms[0].strOffLineTariffDiscountUnit.value = "";
				document.forms[0].strOffLineTariffDiscountType.selectedIndex = 0;
				document.forms[0].strOffLineTariffDiscountBy.selectedIndex = 0;
				document.forms[0].strOffLineTariffDiscountReason.selectedIndex = 0;
				document.forms[0].strOffLineTariffDiscountReasonText.value = "";								
						
		hide_popup(divId);*/
	}
	
	
	function hideOffLineTariffDiscountDetails(divId){
		
		/*	var conf = confirm("Are you Sure to Cancel the Discount");
		
			  if(conf){
			  	
			  		if(document.getElementById("strOfflineTariffDiscountConfigDetails"+gblTariffDiscountDtlsId) != null)
			  		document.getElementById("strOfflineTariffDiscountConfigDetails"+gblTariffDiscountDtlsId).value = "";
			  		
			  		
			  		if(document.getElementById("strTariffDiscountConfigDetails"+gblTariffDiscountDtlsId) != null)
			  		document.getElementById("strTariffDiscountConfigDetails"+gblTariffDiscountDtlsId).value = "";
			  		
			  	
			  		document.getElementById("strOfflineTariffDiscount"+gblTariffDiscountDtlsId).value = 0;					
					document.getElementById("strOfflineTariffDiscountDivId"+gblTariffDiscountDtlsId).innerHTML = "0";
									
											
												
					document.forms[0].strOffLineTariffDiscountReasonText.value = document.forms[0].strTempCtDate.value;
												
							
				calcOffLineTariffNetAmount(gblTariffDiscountDtlsId);
			  	
			  	
			  }	
		
				document.forms[0].strOffLineTariffDiscountUnit.value = "";
				document.forms[0].strOffLineTariffDiscountType.selectedIndex = 0;
				document.forms[0].strOffLineTariffDiscountBy.selectedIndex = 0;
				document.forms[0].strOffLineTariffDiscountReason.selectedIndex = 0;
				document.forms[0].strOffLineTariffDiscountReasonText.value = "";								
						
		hide_popup(divId);*/
	}
	
	/**
	 * validateTariffDiscountDetails -  
	 */
	 function validateTariffDiscountDetails() {
	 	
	 	/*
	 	 	
	 	var hisValidator = new HISValidator("EstEnquiryTransBean");  
			
	hisValidator.addValidation("strOffLineTariffDiscountUnit", "req", "Tariff Discount Unit is a Mandatory Field" );
		
	if(document.forms[0].strOffLineTariffDiscountType[document.forms[0].strOffLineTariffDiscountType.selectedIndex].value == 2){
		
		hisValidator.addValidation("strOffLineTariffDiscountUnit", "numltet=100", "Percentage Cannot be Greater than 100" );
	}else{
			hisValidator.addValidation("strOffLineTariffDiscountUnit", "amount=8,2", "Please Enter a Valid Amount" );
	}
		
	hisValidator.addValidation("strOffLineTariffDiscountBy","dontselect=0", "Please Select a Value from Tariff Discount By" );
		
	if(document.forms[0].strOffLineTariffDiscountReason[document.forms[0].strOffLineTariffDiscountReason.selectedIndex].value == 0){

		hisValidator.addValidation("strOffLineTariffDiscountReasonText","req", "Please Enter the Discount Reason" );
		
	}
		
	hisValidator.addValidation("strOffLineTariffDiscountDate", "req", "Tariff Discount Date is a Mandatory Field" );
	hisValidator.addValidation("strOffLineTariffDiscountDate", "date", "Please Enter a Valid Discount Date" );
		
	var retVal = hisValidator.validate(); 
	hisValidator.clearAllValidations();
	
		
		if(retVal){
				
				
				var tariffHidDtls = document.getElementById("strOfflineTariffDetailsHidden"+gblTariffDiscountDtlsId).value; 
				
				if(tariffHidDtls != null && tariffHidDtls != ''){
					
					var temp = tariffHidDtls.split('^');
				
					var rateVal = parseFloat(temp[4]);	
				
					if(rateVal <= 0){
							
						alert("Discount is Not Applicable for Rate Rs. 0.00");	
						return false;
						
					}
				
				
					
				}
				
				
				var discountUnit = document.forms[0].strOffLineTariffDiscountUnit.value;
				var discountType = document.forms[0].strOffLineTariffDiscountType[document.forms[0].strOffLineTariffDiscountType.selectedIndex].value;
				var discountBy = document.forms[0].strOffLineTariffDiscountBy[document.forms[0].strOffLineTariffDiscountBy.selectedIndex].value;
				var discountReason = document.forms[0].strOffLineTariffDiscountReason[document.forms[0].strOffLineTariffDiscountReason.selectedIndex].value;
				var discountDate = document.forms[0].strOffLineTariffDiscountDate.value;				
				var discountText = document.forms[0].strOffLineTariffDiscountReasonText.value;
				document.getElementById("strOfflineTariffDiscountConfigDetails"+gblTariffDiscountDtlsId).value = discountUnit+","+discountType+","+discountBy+","+discountReason+","+discountText+","+discountDate;
				
				var displayResult = "";

				if(discountType == 2){
					 displayResult = discountUnit+" %";										
				}else{
					displayResult = discountUnit;
				}
									
				document.getElementById("strOfflineTariffDiscount"+gblTariffDiscountDtlsId).value = displayResult;					
				document.getElementById("strOfflineTariffDiscountDivId"+gblTariffDiscountDtlsId).innerHTML = displayResult;
												
				document.forms[0].strOffLineTariffDiscountReasonText.value = document.forms[0].strTempCtDate.value;
										
										
				
				document.forms[0].strOffLineTariffDiscountUnit.value = "";
				document.forms[0].strOffLineTariffDiscountType.selectedIndex = 0;
				document.forms[0].strOffLineTariffDiscountBy.selectedIndex = 0;
				document.forms[0].strOffLineTariffDiscountReason.selectedIndex = 0;
				document.forms[0].strOffLineTariffDiscountReasonText.value = "";	
												
				hide_popup('tariffDiscountDtls');				
																
				calcOffLineTariffNetAmount(gblTariffDiscountDtlsId);
				
		}else{
		
		return false;
		}
	 		*/
	 }
	
	
	 function validateWithoutCrNoTariffDiscountDetails() {
	 	
	 	/*	if(document.forms[0].strWithoutCrNoOnlineRequestNo!= null && document.forms[0].strWithoutCrNoOnlineRequestNo.value == 0 ){
	 			
	 			validateTariffDiscountDetails();
	 			return false;
	 		}
	 	
	 	 	
	 	var hisValidator = new HISValidator("EstEnquiryTransBean");  
			
	hisValidator.addValidation("strOffLineTariffDiscountUnit", "req", "Tariff Discount Unit is a Mandatory Field" );
		
	if(document.forms[0].strOffLineTariffDiscountType[document.forms[0].strOffLineTariffDiscountType.selectedIndex].value == 2){
		
		hisValidator.addValidation("strOffLineTariffDiscountUnit", "numltet=100", "Percentage Cannot be Greater than 100" );
	}else{
			hisValidator.addValidation("strOffLineTariffDiscountUnit", "amount=8,2", "Please Enter a Valid Amount" );
	}
		
	hisValidator.addValidation("strOffLineTariffDiscountBy","dontselect=0", "Please Select a Value from Tariff Discount By" );
		
	if(document.forms[0].strOffLineTariffDiscountReason[document.forms[0].strOffLineTariffDiscountReason.selectedIndex].value == 0){

		hisValidator.addValidation("strOffLineTariffDiscountReasonText","req", "Please Enter the Discount Reason" );
		
	}
		
	hisValidator.addValidation("strOffLineTariffDiscountDate", "req", "Tariff Discount Date is a Mandatory Field" );
	hisValidator.addValidation("strOffLineTariffDiscountDate", "date", "Please Enter a Valid Discount Date" );
		
	var retVal = hisValidator.validate(); 
	hisValidator.clearAllValidations();
	
		
		if(retVal){
				
				var discountUnit = document.forms[0].strOffLineTariffDiscountUnit.value;
				var discountType = document.forms[0].strOffLineTariffDiscountType[document.forms[0].strOffLineTariffDiscountType.selectedIndex].value;
				var discountBy = document.forms[0].strOffLineTariffDiscountBy[document.forms[0].strOffLineTariffDiscountBy.selectedIndex].value;
				var discountReason = document.forms[0].strOffLineTariffDiscountReason[document.forms[0].strOffLineTariffDiscountReason.selectedIndex].value;
				var discountDate = document.forms[0].strOffLineTariffDiscountDate.value;				
				var discountText = document.forms[0].strOffLineTariffDiscountReasonText.value;
				
				document.getElementById("strTariffDiscountConfigDetails"+gblTariffDiscountDtlsId).disabled = false;
				document.getElementById("strTariffDiscountConfigDetails"+gblTariffDiscountDtlsId).value = discountUnit+","+discountType+","+discountBy+","+discountReason+","+discountText+","+discountDate;
				
				var displayResult = "";

				if(discountType == 2){
					 displayResult = discountUnit+" %";										
				}else{
					displayResult = discountUnit;
				}
									
									
				document.getElementById("strTariffDiscountAmtDivId"+gblTariffDiscountDtlsId).innerHTML = displayResult;
												
			document.forms[0].strOffLineTariffDiscountReasonText.value = document.forms[0].strTempCtDate.value;
																				
				hide_popup('tariffDiscountDtls');
				
				calcOnlineWithoutCrNoTariffNetAmount(document.getElementById("strTariffDetailsId"+gblTariffDiscountDtlsId).value,gblTariffDiscountDtlsId);
				
		}else{
		
		return false;
		}
	 		*/
	 }
	
	/**
	 * calcOffLineTariffNetAmount
	 * @param {Object} qtyObj - Quatity Text Box object 
	 * @param {String} index 
	 */
	 function calcOffLineTariffNetAmount(index) {
		var tariff =  document.getElementById("strOfflineTariffName"+index).value;	 		 	
	 	var a = document.getElementById("strOfflineTariffDetailsHidden"+index).value.split('^');
		var b=true;
		for(var i = 0 ; i<a.length; i++)
			if(a[i]!="0"){
				b=false;
				break;
			}
		if(b || tariff != ''){	 		 	
	 		var temp = document.getElementById("strOfflineTariffDetailsHidden"+index).value.split('^');
	 			 		
	 		var rate = temp[4];
	 		
	 		if(temp[17] == '1'){
	 			
	 			rate = document.getElementById("strOfflineTempTariffRateUnit"+index).value;
	 		}
	 		
	 		var actRate = temp[11];
	 		var rateBaseValue = temp[6];
	 		var qtyBaseValue = document.getElementById("strOfflineTariffUnit"+index).options[document.getElementById("strOfflineTariffUnit"+index).selectedIndex].value;
	 		var qtyVal =  document.getElementById("strOfflineTariffQty"+index).value;
	 	//	var discountVal = document.getElementById("strOfflineTariffDiscount"+index).value;
	 	//	var serviceVal = document.getElementById("strOfflineTariffServiceTax"+index).value;
	 	//	var hiddenVal = document.getElementById("strOfflineTariffDiscountConfigDetails"+index).value;
	 		var discountType = 0;
	 		
	 		var netAmt = 0;
	 		
	 		/*if(hiddenVal != ''){
	 			
	 			var tempArr = hiddenVal.split(',');
	 			
	 			discountVal = tempArr[0];
	 			discountType = tempArr[1];
	 		}*/
	 		
	 		
	 		var calAmt = calTrfNetAmountWithActTrfAmt(rate, actRate ,rateBaseValue,0,discountType,qtyVal,qtyBaseValue,0,0,0); 
	 				 			 		 		 	
	 	
	 	netAmt = calAmt.oNetTrfAmt;
	 	
	 	///add consumabale charges
	 	var groupid =temp[1];
	 	var groupConsumableCharge =temp[18];
	 		 	
	 	addConsumableCharge(groupid,groupConsumableCharge)
	 	
	 		if(netAmt < 0){
	 			
	 			//alert("Net Amount Value is Less than Rs. 0.00, Please Check the Discount Amount");
	 			
	 			//document.getElementById("strOfflineTariffDiscount"+index).value = 0;					
				//document.getElementById("strOfflineTariffDiscountDivId"+index).innerHTML = "0";

					
				//var temp =  document.getElementById("strOfflineTariffDiscountConfigDetails"+index).value.split(",");
																
				//document.getElementById("strOfflineTariffDiscountConfigDetails"+index).value = "0,1,"+temp[2]+","+temp[3]+","+temp[4]+","+temp[5];
			 				 	
			 	//calcOffLineTariffNetAmount(index);			 	
			 				 			
	 			//return false;
	 		}
	 			
	 			
	 		//	document.getElementById("strOfflineTariffServiceTaxAmtVal"+index).value = calAmt.oSerAmt;
			//	document.getElementById("strOfflineTariffDiscountAmtVal"+index).value = calAmt.oDisAmt;
	 			document.getElementById("strOfflineTotalActualTariffAmtVal"+index).value = calAmt.oActTrfAmt;
	 		 
	 			document.getElementById("strOfflineTariffNetAmount"+index).value = netAmt;
	 			document.getElementById("strOfflineTariffNetAmountDivId"+index).innerHTML = netAmt;
	 			
	 	
	 		calcTotalRecAmount(index);	
		}	 	
	 }
	
	
	/**
	 * calcTotalRecAmount 
	 */
	 function calcTotalRecAmount(index) {
	 	
	 	
	 	
	 	var grandTotal = calAllTariffNetCost("strOfflineTariffNetAmount");
	 		 
	 	if(document.getElementById("offlineMaxGrandTotalDivId") != null){	 	
		 	document.getElementById("offlineMaxGrandTotalDivId").innerHTML = grandTotal;
		 	document.forms[0].strOfflineGrandTotalAmount.value = grandTotal;
	 	}
	 	
	 	if(document.getElementsByName("strOfflineTotalServiceTaxAmount").length > 0){
	 	
		 	var grandServiceTax = calAllTariffNetCost("strOfflineTariffServiceTaxAmtVal");
		 	document.forms[0].strOfflineTotalServiceTaxAmount.value = grandServiceTax;
		 	
		 		 	
		//	var grandDiscountVal = calAllTariffNetCost("strOfflineTariffDiscountAmtVal"); 
		// 	document.forms[0].strOfflineTotalDiscountAmount.value = grandDiscountVal;
		 	
		 	var grandActualTariffVal = calAllTariffNetCost("strOfflineTotalActualTariffAmtVal");
		 	document.forms[0].strOfflineTotalActualTariffAmount.value = grandActualTariffVal;
	 	
	 	}
	 	
	 	var clientBalAmtObj 	= document.getElementsByName("strOfflineClientDetails");
	 	var grandTotalAmt		= document.forms[0].strOfflineGrandTotalAmount;
	 	var patPayableAmtObj 	= document.forms[0].strOfflinePatNetPayAmount;
	 	var patMaxBenefitAmtObj = document.forms[0].strOfflineMaxClientBenefitAmount;
	 	
	 	var patMaxBenObj = document.getElementById("strOfflineMaxClientBenefitAmount");
	 	var patPayAmtObj = "0";
	 	
	 	var patPayAmt = "0";
	 	
	 	if(clientBalAmtObj.length > 0){
	 	
	 		clientBalAmt = parseFloat(clientBalAmtObj[0].value.split('^')[3]);
	 		patPayAmtObj  = parseFloat(clientBalAmtObj[0].value.split('^')[5]);
	 	}else{
	 		
	 		clientBalAmt = parseFloat("0");
	 	}
	 	
	 		if(grandTotalAmt != null){
	 		grandTotalAmt 		= parseFloat(grandTotalAmt.value);
	 		}else{
	 			
	 			grandTotalAmt = parseFloat(grandTotal);
	 		}
	 			 	
	 		if(patPayAmtObj != null){
	 			
	 			patPayAmt = patPayAmtObj.value;
	 			
	 		}else{
	 			
	 			patPayAmt = parseFloat("0");
	 			
	 		}	 	
	 			 	
	 			 		
	/*
	 		if(clientBalAmt > grandTotalAmt){
	 			
	 			grandTotalAmt = roundValue(grandTotalAmt,2);
	 			patMaxBenefitAmtObj.value = grandTotalAmt;
	 			patMaxBenObj.innerHTML = grandTotalAmt;
	 			
	 		}else{
	 			
	 			clientBalAmt = roundValue(clientBalAmt,2);
	 			patMaxBenefitAmtObj.value = clientBalAmt;
	 			patMaxBenObj.innerHTML = clientBalAmt;
	 		
	 		} 		
	 		
	 		var patPayAmt = manipulateValue(grandTotalAmt,parseFloat(patMaxBenefitAmtObj.value),1);
	 		
	 		patPayAmt = roundValue(patPayAmt,2);
	 		 
	 		patPayableAmtObj.value = patPayAmt;
	 		
	 		patPayAmtObj.innerHTML = patPayAmt;
	 */
	 		 		
	 	
	 		var cltAmtDtls = calClientAmount(clientBalAmt,patPayAmt,grandTotalAmt);
	 		
	 		
	 		if(document.getElementById("offlineMaxGrandTotalDivId") != null){	 	 		
		 		document.forms[0].strOfflineMaxClientBenefitAmount.value = cltAmtDtls.oClientAmt;
		 		document.getElementById("offlineMaxClientBenefitDivId").innerHTML = cltAmtDtls.oClientAmt;
		 	}
	 			 			 			
	 		if(document.getElementById("offlinePatNetPayDivId") != null){		 			 			
		 		document.forms[0].strOfflinePatNetPayAmount.value = cltAmtDtls.oPatAmt;
		 		document.getElementById("offlinePatNetPayDivId").innerHTML = cltAmtDtls.oPatAmt ;	 		
	 		}	
	 		
	 	 	 			 		
	 		document.forms[0].strOfflineTotalRecAmount.value = cltAmtDtls.oPatAmt;
	 		document.getElementById("totalRecAmtDivId").innerHTML = cltAmtDtls.oPatAmt ;
	 		
	 	 		
	 			
					
					
	 		
	 if(parseFloat(cltAmtDtls.oPatAmt) < 0){
		  		
		  		cltAmtDtls.oPatAmt = -1 * parseFloat(cltAmtDtls.oPatAmt);
		  		cltAmtDtls.oPatAmt = roundValue(cltAmtDtls.oPatAmt,2); 
		  		
		  	}
		  	
		  	//document.getElementsByName("strOfflineAmount")[0].value = cltAmtDtls.oPatAmt;
	 		
	 	//	document.forms[0].strOfflineTotalPayAmount.value = cltAmtDtls.oPatAmt;
					//document.getElementById("offlineTotalPayAmtDivId").innerHTML = cltAmtDtls.oPatAmt;
	 		
	 			
	 			 
	 			 setTotalToAmount('0');
	 			 				 			 			
	 			setTotalRefundAmount('0');
	 			
	 			///adding consumable to all cahrges
		 
		 var consumableCharge=document.getElementsByName("strConsumableCharge")[0].value	
		 //var totalCharge=document.getElementsByName("strOfflineTotalPayAmount")[0].value
		
		 document.getElementsByName("strOfflineTotalPayAmountWithoutConsumable")[0].value=cltAmtDtls.oPatAmt;
		 
		 var totalCharge=roundValue(parseFloat(cltAmtDtls.oPatAmt)+parseFloat(consumableCharge),2)
		 
		 if(document.getElementById("totalRecAmtDivId") != null){
		 document.forms[0].strOfflineTotalRecAmount.value=totalCharge
		 document.getElementById("totalRecAmtDivId").innerHTML = "<td width='15%' class='CONTROL' style='font-weight: bold'>"+totalCharge+"</td>";
		 }
		
		// if(document.getElementById("offlineTotalPayAmtDivId") != null){
		// document.forms[0].strOfflineTotalPayAmount.value=totalCharge
		 //document.getElementById("offlineTotalPayAmtDivId").innerHTML = totalCharge
		 //}
		 
		 
		 if(document.getElementById("offlinePatNetPayDivId") != null){
		 document.getElementById("offlinePatNetPayDivId").innerHTML = totalCharge
		 document.forms[0].strOfflinePatNetPayAmount.value = totalCharge
		 }
		 
		 //document.getElementsByName("strOfflineAmount")[0].value=totalCharge
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	 	
	 	checkForOffLinePaymentLimit('1');
	 	
	 	
	 	 	
 	
	 }
	 
	 
	 
	 /**
	  * calcOnlineTariffNetAmount
	  * @param {String} tariffDtls 
	  */
	  function calcOnlineTariffNetAmount(tariffDtls , index) {
	  	
	  	
	  	var temp = tariffDtls.split('^');
	 			 		
	 		var rate = temp[3];
	 		var actRate = temp[6];
	 		var rateBaseValue = temp[13];
	 		var qtyBaseValue = document.getElementById("strBillTariffUnit"+index).options[document.getElementById("strBillTariffUnit"+index).selectedIndex].value;
	 		var qtyVal =  document.getElementById("strTariffBilledQty"+index).value;
	 		var discountVal = temp[8];
	 		var serviceVal = temp[7];
	 		var discountType = temp[9];
	 		
	 		var netAmt = 0;
	 			 		 			 		
	 		var calAmt = calTrfNetAmountWithActTrfAmt(rate, actRate ,rateBaseValue,discountVal,discountType,qtyVal,qtyBaseValue,serviceVal,0,0); 
	 		
	 		
	 	netAmt = calAmt.oNetTrfAmt;
	 	
	 	
	 		if(netAmt < 0){
	 			
	 			alert("Net Amount Value in Negative, Plese Check the Discount Amount");
	 			
	 			
	 			document.getElementById("strOnlineTariffDiscount"+index).value = 0;					
				document.getElementById("strOnlineTariffDiscountDivId"+index).innerHTML = "0";

					
				var temp =  document.getElementById("strOnlineTariffDiscountConfigDetails"+index).value.split(",");
																
				document.getElementById("strOnlineTariffDiscountConfigDetails"+index).value = "0,1,"+temp[2]+","+temp[3]+","+temp[4]+","+temp[5];
			 			
			 	 
	 			
	 			return false;
	 			
	 		}else{
	 			
	 		document.getElementById("strTariffServiceTaxAmt"+index).value = calAmt.oSerAmt;
			document.getElementById("strTariffDiscountAmt"+index).value = calAmt.oDisAmt;
			document.getElementById("strTariffDiscountAmtDivId"+index).innerHTML = calAmt.oDisAmt;
	 		 document.getElementById("strTariffActualAmt"+index).value = calAmt.oActTrfAmt;
	 		 
	 		document.getElementById("strTariffNetAmt"+index).value = netAmt;
	 		document.getElementById("strTariffNetAmtDivId"+index).innerHTML = netAmt;
	 			
	 		}	 		 		 	
			 		 		 		 	
			
	 	
	 		calcOnlineTotalRecAmount();	
	  	
	  	
	  }
	 
	 
	  function calcOnlineWithoutCrNoTariffNetAmount(tariffDtls , index) {
	  	
	  		  	
	  	var temp = tariffDtls.split('^');
	 			 		
	 		var rate = temp[3];
	 		var actRate = temp[6];
	 		var rateBaseValue = temp[13];
	 		var qtyBaseValue = document.getElementById("strBillTariffUnit"+index).options[document.getElementById("strBillTariffUnit"+index).selectedIndex].value;
	 		var qtyVal =  document.getElementById("strTariffBilledQty"+index).value;
	 		var discountVal = document.getElementById("strTariffDiscountConfigDetails"+index).value.split(',')[0];
	 		var serviceVal = temp[7];
	 		var discountType = 0;
	 		
	 		if(document.getElementById("strTariffDiscountConfigDetails"+index).value.split(',')[1] != null){
	 			discountType = document.getElementById("strTariffDiscountConfigDetails"+index).value.split(',')[1];
	 		}
	 			 		
	 		var netAmt = 0;
	 			 		 			 		
	 		var calAmt = calTrfNetAmountWithActTrfAmt(rate, actRate ,rateBaseValue,discountVal,discountType,qtyVal,qtyBaseValue,serviceVal,0,0); 
	 		
	 		
	 	netAmt = calAmt.oNetTrfAmt;
	 	
	 	
	 		if(netAmt < 0){
	 			
	 			alert("Net Amount Value in Negative, Plese Check the Discount Amount");
	 			
	 			
	 			document.getElementById("strTariffDiscountAmt"+index).value = 0;					
				document.getElementById("strTariffDiscountAmtDivId"+index).innerHTML = "0";

					
				var temp =  document.getElementById("strTariffDiscountConfigDetails"+index).value.split(",");
																
				document.getElementById("strTariffDiscountConfigDetails"+index).value = "0,1,"+temp[2]+","+temp[3]+","+temp[4]+","+temp[5];
			 				 
	 			
	 			calcOnlineWithoutCrNoTariffNetAmount(tariffDtls , index);
	 			
	 			return false;
	 			
	 		}else{
	 			
	 				 			
	 		document.getElementById("strTariffServiceTaxAmt"+index).value = calAmt.oSerAmt;
	 		document.getElementById("strTariffServiceTaxAmtDivId"+index).innerHTML = calAmt.oSerAmt;
	 		
			document.getElementById("strTariffDiscountAmt"+index).value = calAmt.oDisAmt;
			document.getElementById("strTariffDiscountAmtDivId"+index).innerHTML = calAmt.oDisAmt;
			
	 		 document.getElementById("strTariffActualAmt"+index).value = calAmt.oActTrfAmt;
	 		 
	 		document.getElementById("strTariffNetAmt"+index).value = netAmt;
	 		document.getElementById("strTariffNetAmtDivId"+index).innerHTML = netAmt;
	 			
	 		}	 		 		 	
			 		 		 		 	
			
	 	
	 		calcOnlineWithoutCrNoTotalRecAmount();	
	  	
	  	
	  }
	 
	  function calcOnlineTotalRecAmount() {
	 	
	 	
	 	var grandTotal = calAllTariffNetCost("strTariffNetAmt");
	 		 	
	 	document.getElementById("onlineGrandTotalDivId").innerHTML = grandTotal;
	 	document.forms[0].strOnlineGrandTotalAmount.value = grandTotal;
	 	
	 	
	 	if(document.getElementsByName("strTariffServiceTaxAmt").length > 0){
	 	
		 	var grandServiceTax = calAllTariffNetCost("strTariffServiceTaxAmt");
		 	document.forms[0].strTotalTariffServiceTaxAmount.value = grandServiceTax;
		 	
		 		 	
			var grandDiscountVal = calAllTariffNetCost("strTariffDiscountAmt"); 
		 	document.forms[0].strTotalTariffDiscountAmount.value = grandDiscountVal;
		 	
		 	var grandActualTariffVal = calAllTariffNetCost("strTariffActualAmt");
		 	document.forms[0].strTotalTariffActualAmount.value = grandActualTariffVal;
	 	
	 	}
	 	
	 	var clientBalAmtObj 	= document.getElementsByName("strOnlineClientDetails");
	 	var grandTotalAmt		= document.forms[0].strOnlineGrandTotalAmount;
	 	var patPayableAmtObj 	= document.forms[0].strOnlinePatNetPayAmount;
	 	var patMaxBenefitAmtObj = document.forms[0].strOnlineMaxClientBenefitAmount;
	 	
	 	var patMaxBenObj = document.getElementById("onlineMaxClientBenefitDivId");
	 	var patPayAmtObj = "0";
	 	
	 	if(clientBalAmtObj.length > 0){
	 	
	 		clientBalAmt = parseFloat(clientBalAmtObj[0].value.split('^')[3]);
	 		patPayAmtObj = parseFloat(clientBalAmtObj[0].value.split('^')[5]);
	 	}else{
	 		
	 		clientBalAmt = parseFloat("0");
	 	}
	 		
	 		grandTotalAmt 		= parseFloat(grandTotalAmt.value);
	 		
	 		
		 	
	 		var cltAmtDtls = calClientAmount(clientBalAmt,patPayAmtObj,grandTotalAmt);
	 		
	 				 			 		
	 		document.forms[0].strOnlineMaxClientBenefitAmount.value = cltAmtDtls.oClientAmt;
	 		document.getElementById("onlineMaxClientBenefitDivId").innerHTML = cltAmtDtls.oClientAmt;
	 			 		
	 			 			 			
	 		document.forms[0].strOnlinePatNetPayAmount.value = cltAmtDtls.oPatAmt;
	 		document.getElementById("onlinePatNetPayDivId").innerHTML = cltAmtDtls.oPatAmt ;	 		
	 			 		
	 			 		
	 		document.forms[0].strOnlineTotalRecAmount.value = cltAmtDtls.oPatAmt;
	 		document.getElementById("onlineTotalRecAmtDivId").innerHTML = cltAmtDtls.oPatAmt ;
	 
	 	 setTotalToAmount('1');
	 
	 	setTotalRefundAmount('1');
	 	
		checkForOnLinePaymentLimit('1');
	 	
	 	
	 }
	 
	 
	 function calcOnlineWithoutCrNoTotalRecAmount() {
	 	
	 	
	 	var grandTotal = calAllTariffNetCost("strTariffNetAmt");
	 		 	
		
	 	
	 	if(document.getElementsByName("strTariffServiceTaxAmt").length > 0){
	 	
		 	var grandServiceTax = calAllTariffNetCost("strTariffServiceTaxAmt");
		 	document.forms[0].strTotalTariffServiceTaxAmount.value = grandServiceTax;
		 	
		 		 	
			var grandDiscountVal = calAllTariffNetCost("strTariffDiscountAmt"); 
		 	document.forms[0].strTotalTariffDiscountAmount.value = grandDiscountVal;
		 	
		 	var grandActualTariffVal = calAllTariffNetCost("strTariffActualAmt");
		 	document.forms[0].strTotalTariffActualAmount.value = grandActualTariffVal;
	 	
	 	}
	 	
	 			 		
	 		document.forms[0].strOfflineTotalRecAmount.value = grandTotal;
	 		document.getElementById("totalRecAmtDivId").innerHTML = grandTotal ;
	 
	 		
	 			
					
					
	 
	 if(parseFloat(grandTotal) < 0){
		  		
		  		grandTotal = -1 * parseFloat(grandTotal);
		  		grandTotal = roundValue(grandTotal,2); 
		  		
		  	}
	 
	// document.getElementsByName("strOfflineAmount")[0].value = grandTotal;
	 
	// document.forms[0].strOfflineTotalPayAmount.value = grandTotal;
			//		document.getElementById("offlineTotalPayAmtDivId").innerHTML = grandTotal;
	 		
	 
	 	 setTotalToAmount('0');
	 
	 	setTotalRefundAmount('0');
	 	
		checkForOffLinePaymentLimit('1');
	 	
	 	
	 }
	 
	 
	   function calcOnlineReconcileTariffNetAmount(tariffDtls , index , qtyType) {
	  	
	  	
	  	if(tariffDtls.checked){
	  	
	  	var temp = tariffDtls.value.split('^');
	 			 		
	 		var rate = temp[3];
	 		var actRate = temp[6];
	 		var rateBaseValue = temp[13];
	 		var qtyVal =  temp[1].split('*')[0];
	 		var qtyBaseValue = temp[1].split('*')[1];
	 		var discountVal = temp[8];
	 		var serviceVal = temp[7];
	 		var discountType = temp[9];
	 		
	 		
	 		var netAmt = 0;
	 		var calAmt = 0;
	 		
	 		
	 		if(qtyType == '1'){
	 			
	 			calAmt = calTrfNetAmountWithActTrfAmt(rate, actRate ,rateBaseValue,discountVal,discountType,qtyVal,qtyBaseValue,serviceVal,0, 0);
	 			
	 		}else{
	 			
	 			calAmt = calTrfNetAmountWithActTrfAmt(rate, actRate ,rateBaseValue,discountVal,discountType,qtyVal,qtyBaseValue,serviceVal,0, 1);
	 		}
	 			 		 			 		
	 		 	 		
	 	netAmt = calAmt.oNetTrfAmt;
	 	
	 	
	 		if(netAmt < 0 && qtyType == '1'){
	 			
	 			alert("Net Amount Value in Negative, Plese Check the Discount Amount");
	 			
	 			
	 			document.getElementById("strOnlineTariffDiscount"+index).value = 0;					
				document.getElementById("strOnlineTariffDiscountDivId"+index).innerHTML = "0";

					
				var temp =  document.getElementById("strOnlineTariffDiscountConfigDetails"+index).value.split(",");
																
				document.getElementById("strOnlineTariffDiscountConfigDetails"+index).value = "0,1,"+temp[2]+","+temp[3]+","+temp[4]+","+temp[5];
			 				 
	 			
	 			return false;
	 			
	 		}else{
	 			
	 		document.getElementById("strTariffServiceTaxAmt"+index).value = calAmt.oSerAmt;
			document.getElementById("strTariffDiscountAmt"+index).value = calAmt.oDisAmt;
			document.getElementById("strTariffDiscountAmtDivId"+index).innerHTML = calAmt.oDisAmt;
	 		 document.getElementById("strTariffActualAmt"+index).value = calAmt.oActTrfAmt;
	 		 
	 		 netAmt = roundValue(netAmt,2);
	 		 
	 		document.getElementById("strTariffNetAmt"+index).value = netAmt;
	 		document.getElementById("strTariffNetAmtDivId"+index).innerHTML = netAmt;
	 			
	 		}	 		 	
	 	
	 /*		if(qtyType == "1"){ 
	 				 			
	 			document.getElementById("strTariffNetAmt"+index).value = netAmt;
	 			document.getElementById("strTariffNetAmtDivId"+index).innerHTML = netAmt;
	 		}else{
	 			
	 			alert("inside qtytype 1  Refund");
	 			
	 			document.getElementById("strTariffNetAmt"+index).value = netAmt * -1;
	 			document.getElementById("strTariffNetAmtDivId"+index).innerHTML = netAmt * -1;
	 		}
	 */
	 	//	calcOnlineTotalReconcileRecAmount();	
	  	
	  	}else{
	  		
	  		document.getElementById("strTariffServiceTaxAmt"+index).value = 0;
			document.getElementById("strTariffDiscountAmt"+index).value = 0;
			document.getElementById("strTariffDiscountAmtDivId"+index).innerHTML = "0.00";
	 		document.getElementById("strTariffActualAmt"+index).value = 0;
	  		
	  		document.getElementById("strTariffNetAmt"+index).value = 0;
	 		document.getElementById("strTariffNetAmtDivId"+index).innerHTML = "0.00";
	  		
	  	}
	  	
	  	calcOnlineTotalReconcileRecAmount();
	  	
	  }
	 
	  
	  function calcOnlineTotalReconcileRecAmount() {
	 	
	 	
	 	var grandTotal = calAllTariffNetCost("strTariffNetAmt");
	 		 	
	 	document.getElementById("onlineGrandTotalDivId").innerHTML = grandTotal;
	 	document.forms[0].strOnlineGrandTotalAmount.value = grandTotal;
	 	
	 	if(document.getElementsByName("strTariffServiceTaxAmt").length > 0){
	 	
		 	var grandServiceTax = calAllTariffNetCost("strTariffServiceTaxAmt");
		 	document.forms[0].strTotalTariffServiceTaxAmount.value = grandServiceTax;
		 	
		 		 	
			var grandDiscountVal = calAllTariffNetCost("strTariffDiscountAmt"); 
		 	document.forms[0].strTotalTariffDiscountAmount.value = grandDiscountVal;
		 	
		 	var grandActualTariffVal = calAllTariffNetCost("strTariffActualAmt");
		 	document.forms[0].strTotalTariffActualAmount.value = grandActualTariffVal;
	 	
	 	}
	 	
	 	var clientBalAmtObj 	= document.getElementsByName("strOnlineClientDetails");
	 	var grandTotalAmt		= document.forms[0].strOnlineGrandTotalAmount;
	 	var patPayableAmtObj 	= document.forms[0].strOnlinePatNetPayAmount;
	 	var patMaxBenefitAmtObj = document.forms[0].strOnlineMaxClientBenefitAmount;
	 	
	 	var patMaxBenObj = document.getElementById("onlineMaxClientBenefitDivId");
	 	var patPayAmtObj = "0"
	 	
	 	if(clientBalAmtObj.length > 0){
	 	
	 		clientBalAmt = parseFloat(clientBalAmtObj[0].value.split('^')[3]);
	 		patPayAmtObj = parseFloat(clientBalAmtObj[0].value.split('^')[5]);
	 		
	 	}else{
	 		
	 		clientBalAmt = parseFloat("0");
	 	}
	 		
	 		grandTotalAmt 		= parseFloat(grandTotalAmt.value);
	 		
	 		grandTotalAmt = roundValue(grandTotalAmt,2);
	 		
	 			 		
	/*
	 		if(clientBalAmt > grandTotalAmt){
	 			
	 			grandTotalAmt = roundValue(grandTotalAmt,2);
	 			patMaxBenefitAmtObj.value = grandTotalAmt;
	 			patMaxBenObj.innerHTML = grandTotalAmt;
	 			
	 		}else{
	 			
	 			clientBalAmt = roundValue(clientBalAmt,2);
	 			patMaxBenefitAmtObj.value = clientBalAmt;
	 			patMaxBenObj.innerHTML = clientBalAmt;
	 		
	 		} 		
	 		
	 		var patPayAmt = manipulateValue(grandTotalAmt,parseFloat(patMaxBenefitAmtObj.value),1);
	 		
	 		patPayAmt = roundValue(patPayAmt,2);
	 		 
	 		patPayableAmtObj.value = patPayAmt;
	 		
	 		patPayAmtObj.innerHTML = patPayAmt;
	 */
	 		
	 	
	 		var cltAmtDtls = calClientAmount(clientBalAmt,patPayAmtObj,grandTotalAmt);
	 		
	 			 		
	 		document.forms[0].strOnlineMaxClientBenefitAmount.value = cltAmtDtls.oClientAmt;
	 		document.getElementById("onlineMaxClientBenefitDivId").innerHTML = cltAmtDtls.oClientAmt;
	 			 		
	 			 			 			
	 		document.forms[0].strOnlinePatNetPayAmount.value = cltAmtDtls.oPatAmt;
	 		document.getElementById("onlinePatNetPayDivId").innerHTML = cltAmtDtls.oPatAmt ;	 		
	 			 				 		
	 		//document.forms[0].strOnlineTotalRecAmount.value = cltAmtDtls.oPatAmt;
	 		//document.getElementById("onlineTotalRecAmtDivId").innerHTML = cltAmtDtls.oPatAmt ;
	 		
	 		
	 			 		
	 		document.forms[0].strOnlineTotalRecAmount.value = grandTotalAmt;
	 		document.getElementById("onlineTotalRecAmtDivId").innerHTML = grandTotalAmt ;
	 		
	 		
	 		 setTotalToAmount('1');
	 		
	 		setTotalRefundAmount('1');
	 		
	 
		checkForOnLinePaymentLimit('1');
	 	
	 	
	 }
	 
	 
	 
	 
	 /**
	  * setOnlineReconcileTariffDetails
	  * @param {Object} chkObj 
	  * @param {String} index
	  */
	  function setOnlineReconcileTariffDetails(chkObj , index ,strQtyType ) {
	  	
	//  	document.getElementById("onlineGrandTotalDivPartId").style.display = "block";
	  	
	  	
	  		if(chkObj.checked == true){
	  		
	  		  			
	  			document.getElementById("strTariffServiceTaxAmt"+index).disabled = false;
	  			document.getElementById("strTariffActualAmt"+index).disabled = false;
	  			document.getElementById("strTariffDiscountAmt"+index).disabled = false;
	  			document.getElementById("strTariffNetAmt"+index).disabled = false;
	  			
	  			var temp = chkObj.value.split('^');
	  			
	  			var qtyType = temp[temp.length - 1];
	  			
	  			
	  			
	  		calcOnlineReconcileTariffNetAmount(chkObj , index , strQtyType);
	  			
	  			
	  		/*	if(qtyType == "1"){
	  				
	  				calcOnlineTotalReconcileRecAmount();
	  				
	  			}else{
	  				
	  				calcRefundTotalAmount();
	  				
	  			} */
	  			
	  	}else{
	  		
	  		document.getElementById("strTariffNetAmtDivId"+index).innerHTML = "0.00";
	  		document.getElementById("strTariffNetAmt"+index).value = 0;
	  		
	  		document.getElementById("strTariffDiscountAmtDivId"+index).innerHTML = "0.00";
	  		
	  		document.getElementById("strTariffServiceTaxAmt"+index).disabled = true;
	  		document.getElementById("strTariffServiceTaxAmt"+index).value = 0;
	  		
	  		document.getElementById("strTariffActualAmt"+index).disabled = true;
	  		document.getElementById("strTariffActualAmt"+index).value = 0;
	  		
	  		document.getElementById("strTariffDiscountAmt"+index).disabled = true;
	  		document.getElementById("strTariffDiscountAmt"+index).value = 0;
	  		
	  		document.getElementById("strTariffNetAmt"+index).disabled = true;
	  		document.getElementById("strTariffNetAmt"+index).value = 0;
	  		
	  		calcOnlineReconcileTariffNetAmount(chkObj , index , strQtyType);
	  		
	   	}
	  	
	  	
	  	
	  }
	 
	 
	 /**
	  * setOnlineReconcileTariffDltsOnQtyChange
	  * @param {Object} txtObj
	  * @param {String} trfDtls 
	  * @param {String} index
	  */
	/*  function setOnlineReconcileTariffDltsOnQtyChange(txtObj , tariffDtls , index) {
	  	
	  		var reqQty =  parseInt(document.getElementById("strTariffReqQty"+index).value);
	  	
	  	  	
	  	var billQty = parseInt(txtObj.value);  	
	  		  	
	  	if(reqQty >= billQty){
	  		
	  		
	  		
	  		var temp = tariffDtls.split('^');
	  			
	  			var qtyType = temp[temp.length - 1];
	  			
	  			
	  				
	  			if(qtyType == "1"){
	  				
	  				calcOnlineTotalRecAmount();
	  				
	  			}else{
	  				
	  				calcRefundTotalAmount();
	  				
	  			}
	  		
	  	//calcOnlineTariffNetAmount(tariffDtls , index);
	  	
	  	
	  	}else{
	  		
	  		alert("Billed Quantity Cannot be Greater than Required / Refund Quantity");
	  		
	  		txtObj.value = 0;
	  	
	  	
	  //	calcOnlineTariffNetAmount(tariffDtls , index);	
	  		
	  	}
	  	
	  }*/
	  
	  
	   /**
	  * setOnlineReconcileTariffDltsOnQtyChange
	  * @param {Object} cmbObj
	  * @param {String} trfDtls 
	  * @param {String} index
	  */
	/*  function setOnlineReconcileTariffDltsOnQtyUnitChange(cmbObj , tariffDtls , index) {
	  	
	  	
	  	var temp = chkObj.value.split('^');
	  			
	  			var qtyType = temp[temp.length - 1];
	  			
	  			
	  				
	  			if(qtyType == "1"){
	  				
	  				calcOnlineTotalRecAmount();
	  				
	  			}else{
	  				
	  				calcRefundTotalAmount();
	  				
	  			}
	  	
	  	//calcOnlineTariffNetAmount(tariffDtls , index);
	  		  	
	  }
	  */
	 
	 
	 /**
	  * setOnlineTariffDetails
	  * @param {Object} chkObj 
	  * @param {String} index
	  */
	  function setOnlineTariffDetails(chkObj , index) {
	  		  	
	  	if(chkObj.checked == true){
	  		
	  			document.getElementById("strTariffBilledQty"+index).disabled = false;
	  			document.getElementById("strTariffBilledQty"+index).value = 1;
	  			document.getElementById("strBillTariffUnit"+index).disabled = false;
	  			
	  			//document.getElementById("strOnlineTariffDiscountConfigDetails"+index).disabled = false;
	  			
	  			document.getElementById("strTariffServiceTaxAmt"+index).disabled = false;
	  			document.getElementById("strTariffActualAmt"+index).disabled = false;
	  			document.getElementById("strTariffDiscountAmt"+index).disabled = false;
	  			document.getElementById("strTariffNetAmt"+index).disabled = false;
	  				  	
	  		calcOnlineTariffNetAmount(chkObj.value , index);		  			
	  		
	  	}else{
	  		
	  		document.getElementById("strTariffBilledQty"+index).value = 0;
	  		document.getElementById("strTariffBilledQty"+index).disabled = true;
	  		//document.getElementById("strBillTariffUnit"+index).selectedIndex = 0;
	  		document.getElementById("strBillTariffUnit"+index).disabled = true;
	  		
	  		document.getElementById("strTariffNetAmt"+index).value = 0;
	  		document.getElementById("strTariffNetAmt"+index).disabled = true;
	  		document.getElementById("strTariffNetAmtDivId"+index).innerHTML = "0.00";
	  		
	  		
	  		document.getElementById("strTariffDiscountAmtDivId"+index).innerHTML = "0.00";
	  		
	  	//	document.getElementById("strOnlineTariffDiscountConfigDetails"+index).disabled = true;
	  	//	document.getElementById("strOnlineTariffDiscountConfigDetails"+index).value = "";
	  		
	  		
	  		document.getElementById("strTariffServiceTaxAmt"+index).disabled = true;
	  		document.getElementById("strTariffServiceTaxAmt"+index).value = 0;
	  		
	  		document.getElementById("strTariffActualAmt"+index).disabled = true;
	  		document.getElementById("strTariffActualAmt"+index).value = 0;
	  		
	  		document.getElementById("strTariffDiscountAmt"+index).disabled = true;
	  		document.getElementById("strTariffDiscountAmt"+index).value = 0;
	  		
	  		calcOnlineTotalRecAmount();
	  		
	  		  	}
	  	
	  }
	
	 function setOnlineWithoutCrNoTariffDetails(chkObj , index) {
	  		  	
	  	if(chkObj.checked == true){
	  		
	  			document.getElementById("strTariffBilledQty"+index).disabled = false;
	  			document.getElementById("strTariffBilledQty"+index).value = 1;
	  			document.getElementById("strBillTariffUnit"+index).disabled = false;
	  			document.getElementById("strTariffNetAmt"+index).disabled = false;
	  			document.getElementById("strTariffServiceTaxAmt"+index).disabled = false;
	  			document.getElementById("strTariffActualAmt"+index).disabled = false;
	  			document.getElementById("strTariffDiscountAmt"+index).disabled = false;
	  			document.getElementById("strTariffDiscountConfigDetails"+index).disabled = false;
	  				  	
	  		calcOnlineWithoutCrNoTariffNetAmount(chkObj.value , index);		  			
	  		
	  	}else{
	  		
	  		document.getElementById("strTariffBilledQty"+index).value = 0;
	  		document.getElementById("strTariffBilledQty"+index).disabled = true;
	  		
	  		document.getElementById("strTariffServiceTaxAmtDivId"+index).innerHTML = "0.00";
	  		
	  		document.getElementById("strBillTariffUnit"+index).disabled = true;
	  		document.getElementById("strTariffNetAmtDivId"+index).innerHTML = "0.00";
	  		
	  		document.getElementById("strTariffNetAmt"+index).disabled = true;
	  		document.getElementById("strTariffNetAmt"+index).value = 0;
	  		
	  		document.getElementById("strTariffDiscountAmtDivId"+index).innerHTML = "0.00";
	  		
	  		document.getElementById("strTariffDiscountConfigDetails"+index).disabled = true;
	  		document.getElementById("strTariffDiscountConfigDetails"+index).value = "";
	  		
	  		document.getElementById("strTariffServiceTaxAmt"+index).disabled = true;
	  		document.getElementById("strTariffServiceTaxAmt"+index).value = 0;
	  		
	  		document.getElementById("strTariffActualAmt"+index).disabled = true;
	  		document.getElementById("strTariffActualAmt"+index).value = 0;
	  		
	  		document.getElementById("strTariffDiscountAmt"+index).disabled = true;
	  		document.getElementById("strTariffDiscountAmt"+index).value = 0;
	  		
	  		calcOnlineWithoutCrNoTotalRecAmount();
	  	}
	  	
	  }
	
	 
	 /**
	  * setOnlineTariffDltsOnQtyChange
	  * @param {Object} txtObj
	  * @param {String} trfDtls 
	  * @param {String} index
	  */
	  function setOnlineTariffDltsOnQtyChange(txtObj , tariffDtls , index) {
	  	  	
	  	var reqQty =  parseInt(document.getElementById("strTariffReqQty"+index).value);
	  	
	  	var qty = "0";
	  	if(txtObj.value.length > 0)
	  		qty = txtObj.value;
	  	  	
	  	var billQty = parseInt(qty);  	
	  		  	
	  	if(reqQty >= billQty){
	  	
	  	calcOnlineTariffNetAmount(tariffDtls , index);
	  	
	  	}else{
	  		
	  		alert("Billed Quantity Cannot be Greater than Required Quantity");
	  		
	  		txtObj.value = 0;
	  	
	  	calcOnlineTariffNetAmount(tariffDtls , index);	
	  		
	  	}
	  }
	 
	  function setOnlineWithoutCrNoTariffDltsOnQtyChange(txtObj , tariffDtls , index) {
	  	  	
	  	var reqQty =  parseInt(document.getElementById("strTariffReqQty"+index).value);
	  	
	  	  	
	  	var billQty = parseInt(txtObj.value);  	
	  		  	
	  	if(reqQty >= billQty){
	  	
	  	calcOnlineWithoutCrNoTariffNetAmount(tariffDtls , index);
	  	}else{
	  		
	  		alert("Billed Quantity Cannot be Greater than Required Quantity");
	  		
	  		txtObj.value = 0;
	  	
	  	calcOnlineWithoutCrNoTariffNetAmount(tariffDtls , index);	
	  		
	  	}
	  }
	 
	 
	  /**
	  * setOnlineTariffDltsOnQtyChange
	  * @param {Object} cmbObj
	  * @param {String} trfDtls 
	  * @param {String} index
	  */
	  function setOnlineTariffDltsOnQtyUnitChange(cmbObj , tariffDtls , index) {
	  	
	  	calcOnlineTariffNetAmount(tariffDtls , index);
	  		  	
	  }
	 
	 
	 function setOnlineWithoutCrNoTariffDltsOnQtyUnitChange(cmbObj , tariffDtls , index) {
	  	
	  	calcOnlineWithoutCrNoTariffNetAmount(tariffDtls , index);
	  		  	
	  }
	 
	
	/**
	 * removeTariffRow
	 * @param {Object} index 
	 */
	 function removeTariffRow(index) {
	 	if(!showAlert())
	 		return false;
	 		
	 	var temp = document.getElementById("strOfflineTariffDetailsHidden"+index).value.split('^');
	 	 var groupid =temp[1];
	 	var groupConsumableCharge =temp[18];
	 	
	 	removeConsumableAmount(groupid,groupConsumableCharge)
	 	
	 	deleteRow(index,'2','1');
	 	
	 	resetMultiRow('3');
		addRows(new Array('strOfflinePaymentMode','strOfflinePaymentDtls','strOfflineAmount'),new Array('s','t','t'),'3','1','R');
			 		 	
	 	calcTotalRecAmount();
	 }
	 
	 
	 
	 
	 /**
	  * setAdvPartPayRemarksText
	
	  */
	  function setAdvPartPayRemarksText() {
	  	
	  	var val = document.forms[0].dr[document.forms[0].dr.selectedIndex].value;
	  	var content = document.forms[0].dr[document.forms[0].dr.selectedIndex].text;
	  		  	
	  	if(val != '0'){
	  	document.forms[0].strRemarksCombo2.disabled = true;
	  	document.forms[0].strRemarksCombo2.value = content;
	  	
	  	}else{
	  		document.forms[0].strRemarksCombo2.value = "";
	  		document.forms[0].strRemarksCombo2.disabled = false;
	  	}
	  	
	  }
	 
	 
	 /**
	  * setReasonText
	  */
	  function setReasonText() {
	  /*	
	  	var val = document.forms[0].strOffLineTariffDiscountReason[document.forms[0].strOffLineTariffDiscountReason.selectedIndex].value;
	 	var content = document.forms[0].strOffLineTariffDiscountReason[document.forms[0].strOffLineTariffDiscountReason.selectedIndex].text;
	  		  	
	  	if(val != '0'){
	  	document.forms[0].strOffLineTariffDiscountReasonText.disabled = true;
	  	document.forms[0].strOffLineTariffDiscountReasonText.value = content;
	  	
	  	}else{
	  		document.forms[0].strOffLineTariffDiscountReasonText.value = "";
	  		document.forms[0].strOffLineTariffDiscountReasonText.disabled = false;
	  	}*/
	  }
	  
	   /**
	  * setRefundRemarksText
	  */
	  function setRefundRemarksText() {
	  	
	  	var val = document.forms[0].strOffLineRefundReason[document.forms[0].strOffLineRefundReason.selectedIndex].value;
	  	var content = document.forms[0].strOffLineRefundReason[document.forms[0].strOffLineRefundReason.selectedIndex].text;
	  	
	  	if(val != '0'){
	  	document.forms[0].strOffLineRefundReasonText.disabled = true;
	  	document.forms[0].strOffLineRefundReasonText.value = content;
	  	
	  	}else{
	  		document.forms[0].strOffLineRefundReasonText.value = "";
	  		document.forms[0].strOffLineRefundReasonText.disabled = false;
	  	}
	  }
	  
	  /**
	   * addTariffRows
	   * @param {Object} obj - Text Box Object
	   * @param {Event} e - 
	   */
	   function addTariffRows(obj,e,divId) {
	
			if(e.keyCode == 13){
								
				if(obj.value > 20){
					
					alert("Please Enter No. of Rows Less than 20");
					return false;
				}
				
				addRows(new Array('strOfflineTariffName','strOfflineTariffDetailsHidden','strOfflineTempTariffRateUnit','strOfflineTariffRateUnit','strOfflineTariffQty','strOfflineTariffUnit','strOfflineTariffNetAmount','strOfflineTotalActualTariffAmtVal'),new Array('t','t','t','t','t','s','t','t','t','t','t','t'),'2',obj.value,'R')
				 hideMultiRowAdder(divId);
				return false;
			}	   	
			return true;
	   }
	  
	  /**
	   * showMultiRowAdder 
	   */
	   function showMultiRowAdder(evt) {
	   	
	   	 evt = (evt) ? evt : ((window.event) ? event : null);
		
		 if (evt) {
		 	
        	if (evt.keyCode == 82 && evt.ctrlKey && evt.altKey) {
	   	
	   		popup('multiRowAdderDivId','250','250');
	   		document.forms[0].strOffLineTariffNoOfRows.focus();
	   		
        	}
		 }
		
	   }
	
		/**
		 * hideMultiRowAdder
		 * @param {String} divid 
		 */
		 function hideMultiRowAdder(divid) {
		 	 	document.forms[0].strOffLineTariffNoOfRows.value = "";
		 	 hide_popup(divid);
		 }
	
	/**
	 * setPenalty
	 * @param {Object} Obj 
	 * @param {String} index
	 */
	 function setPenalty(Obj,index) {
	 	
	 	pTypeObj = document.getElementById("strBillTariffPenaltyType"+index);
	 	
	 	if(pTypeObj.value == 2){
	 		
	 		document.getElementById("strBillTariffPenalty"+index).value = document.getElementById("xmlValId").value;
	 		document.getElementById("strBillTariffPenaltyDivId"+index).innerHTML = document.getElementById("xmlValId").value;
	 		
	 		
	 		
	 	}else{
	 		document.getElementById("strBillTariffPenalty"+index).value  = 0;
	 		document.getElementById("strBillTariffPenaltyDivId"+index).innerHTML = "0";
	 	}
	 	
	 	calcBillTariffRefundAmt(index , document.getElementById("strBillTariffVal"+index).value);
	 	
	 }
	 
	 
	 /**
	  * setAdmissionCancellationPenalty
	  * @param {Object} cmbObj 
	  */
	  function setAdmissionCancellationPenalty(cmbObj) {
	  		  	
	  	if(cmbObj.value == 2){
	 		
	 		document.forms[0].strAdmissionCancellationPenalty.value = document.forms[0].strOfflineIpdPenaltyVal.value;
	 		document.getElementById("strAdmissionCancellationPenaltyDivId").innerHTML = document.forms[0].strOfflineIpdPenaltyVal.value + " %";
	 			 		
	 	}else{
	 		document.forms[0].strAdmissionCancellationPenalty.value = 0;
	 		document.getElementById("strAdmissionCancellationPenaltyDivId").innerHTML = "0 %";
	 		
	 	}
	  	
	  calcOfflineIpdPenaltyAmt();
	  	
	  }
	 
	 /**
	  * calcOfflineIpdPenaltyAmt 
	  */
	  function calcOfflineIpdPenaltyAmt() {
	  	
	  	
	  	var recAmt = document.forms[0].strAdmissionCancellationReceivedAmount.value;
	  	
	  	var expAmt = document.forms[0].strAdmissionCancellationExpenseAmount.value ;
	  	
	  	
	  	
	  	var refundAmt = parseFloat(manipulateValue(recAmt,expAmt,1));
	  		  	
	  	var pltVal	  = document.forms[0].strAdmissionCancellationPenalty.value;
	  	
	  	var pltAmt = calTrfPeneltyCost(refundAmt , pltVal);
	  	  		  	  	
	  	document.forms[0].strRefundAdvancePaneltyAmt.value = pltAmt;
	  	  	
	    var actRefundAmt = manipulateValue(refundAmt,pltAmt,0);
	  	
	  	actRefundAmt = -1.0 * parseFloat(actRefundAmt);
	  	
	  	actRefundAmt = roundValue(actRefundAmt,2);
	  	
	 		  	
	  	document.forms[0].strOfflineTotalRecAmount.value = actRefundAmt;
	  	document.getElementById("totalRecAmtDivId").innerHTML = actRefundAmt;
	    
	     setTotalToAmount('0');
	  		setTotalRefundAmount('0');
	    		  	   		  	
	  }
	 
	 
	 /**
	  * initBillTariff
	  * @param {Object} obj - checkbox object 
	  * @param {String} index 
	  * @param {String} unitVal
	  */
	  function initBillTariff(obj,index , unitVal) {
	  		  	
	  	if(obj.checked){
	  		
	  		var temp = unitVal.split(' ');
	  		
	  		document.getElementById("strBillTariffRefund"+index).value = temp[0];
	  		document.getElementById("strBillTariffRefund"+index).disabled = false;
	  		document.getElementById("strBillTariffPenaltyType"+index).disabled = false;
	  		document.getElementById("strBillTariffUnit"+index).disabled = false;
	  			  		
	  		document.getElementById("strBillTariffRefundAmount"+index).disabled = false;
	  		
	  		
	  		
	  		calcBillTariffRefundAmt(index , obj.value);
	  		
	  	}else{
	  		document.getElementById("strBillTariffRefund"+index).value = 0;
	  		document.getElementById("strBillTariffRefund"+index).disabled = true;
	  		document.getElementById("strBillTariffPenaltyType"+index).disabled = true;
	  		document.getElementById("strBillTariffUnit"+index).disabled = true;
	  		
	  		document.getElementById("strBillTariffRefundAmount"+index).disabled = true;
	  		
	  		document.getElementById("strOfflineRefundServiceTaxAmount"+index).value = 0;
	  		document.getElementById("strOfflineRefundDiscountAmount"+index).value = 0;
	  		document.getElementById("strOfflineRefundPenaltyAmount"+index).value = 0;
	  		document.getElementById("strOfflineRefundActualTariffAmount"+index).value = 0;
	  		
	  			  		
	  		document.getElementById("strBillTariffRefundAmount"+index).value = 0;
	  		document.getElementById("strBillTariffRefundAmountDivId"+index).innerHTML = "0.00";
	  		document.getElementById("strBillTariffPenaltyType"+index).selectedIndex = 0;
	  		document.getElementById("strBillTariffUnit"+index).selectedIndex = 0;
	  		
	  				calcRefundTotalAmount();
	  	}
	  
	  
	  }
	
	/**
	 * calcBillTariffRefundAmt
	 * @param {String} index
	 * @param {String} val		 
	 */
	 function calcBillTariffRefundAmt(index , val) {
	 		 	
	 	var temp = val.split('^');
	 	
	 	var reminedQty = temp[1];
	 	var refundQty = document.getElementById("strBillTariffRefund"+index).value;
	 	var refundUnitBaseVal = document.getElementById("strBillTariffUnit"+index)[document.getElementById("strBillTariffUnit"+index).selectedIndex].value;	 	
	 	var refundUnitBaseValTemp = refundUnitBaseVal.split('^')[1];
	 	var rateUnitBaseVal = temp[13];
	 	var rate = temp[3];
	 	var discountUnit = temp[8];
	 	var discountType = temp[9];
	 	var actRate = temp[6];
	 	var serviceUnit = temp[7];
	 	var penaltyUnit = document.getElementById("strBillTariffPenalty"+index).value;
	 	 	
	 	 	 	 	
	 	var total = "";
	 	var discountAmt = "";
	 	var serviceAmt = "";
	 	var penaltyAmt = "";
	 	
	 	reminedQty = (reminedQty != '')? parseInt(reminedQty) : parseInt("0");
	 	refundQty = (refundQty != '')? parseInt(refundQty) : parseInt("0");
		refundUnitBaseValTemp = (refundUnitBaseValTemp != '')? parseFloat(refundUnitBaseValTemp) : parseInt("0");
		rateUnitBaseVal = (rateUnitBaseVal != '')? parseFloat(rateUnitBaseVal) : parseInt("0");
		rate = (rate != '')? parseFloat(rate) : parseInt("0");
		discountUnit = (discountUnit != '')? parseFloat(discountUnit) : parseInt("0");	 	 		
		discountType = (discountType != '')? parseInt(discountType) : parseInt("0");
		serviceUnit = (serviceUnit != '')? parseFloat(serviceUnit) : parseInt("0");
		penaltyUnit = (penaltyUnit != '')? parseFloat(penaltyUnit) : parseInt("0");
	 		
	 	
		var calAmt = calTrfNetAmountWithActTrfAmt(rate,actRate,rateUnitBaseVal,discountUnit,discountType,refundQty,refundUnitBaseVal,serviceUnit,penaltyUnit,1);	 		
	 			
	 	var refundQtyAmt = refundQty * refundUnitBaseValTemp;
	 	 		 	 	
	 	if(refundQtyAmt <= reminedQty){
	 		
	 		/*rate = rate / rateUnitBaseVal;
	 		
	 		total = rate * refundQtyAmt ;
	 		
	 		if(discountUnit > 0){
	 			
	 			if(discountType == 1){
	 				discountAmt = refundQtyAmt * discountUnit;
	 			}else{
	 				discountAmt = total * discountUnit / 100;
	 			}
	 			
			total = manipulateValue(total , discountAmt , 1);
				 			
	 		}
	 		
	 		if(serviceUnit > 0){
	 			
	 			serviceAmt = total * serviceUnit / 100 ; 
	 			
	 			total = manipulateValue(total , serviceAmt , 0);
	 		}
	 		
	 		if(penaltyUnit > 0){
	 			
	 			penaltyAmt = total * penaltyUnit / 100 ; 
	 			
	 			total = manipulateValue(total , penaltyAmt , 1);
	 		
	 		}
	 		*/
	 		
	 		document.getElementById("strBillTariffRefundAmount"+index).value = calAmt.oNetTrfAmt;
	 		document.getElementById("strBillTariffRefundAmountDivId"+index).innerHTML = calAmt.oNetTrfAmt;
	 		
	 		document.getElementById("strOfflineRefundServiceTaxAmount"+index).value = calAmt.oSerAmt;
	 		document.getElementById("strOfflineRefundDiscountAmount"+index).value = calAmt.oDisAmt;
	 		document.getElementById("strOfflineRefundPenaltyAmount"+index).value = calAmt.oPenAmt;
	 		document.getElementById("strOfflineRefundActualTariffAmount"+index).value = calAmt.oActTrfAmt;
	 		
	 		
	 		
	 	}else{
	 		
	 			alert("Refund Quantity Cannot be More than Balance Quantity");
	 			document.getElementById("strBillTariffRefund"+index).value = 0;
	 			
	 			calcBillTariffRefundAmt(index,val);
	 	}
	 	
	 	calcRefundTotalAmount();
	 }
	 
	 
	 /**
	 * calcTotalRecAmount 
	 */
	 function calcRefundTotalAmount() {
	 	/*
	 	var obj = document.getElementsByName("strBillTariffRefundAmount");
	 	var total = 0;
	 	var amt = 0;
	 	if(obj.length > 0){
	 		
	 		for(var index=0 , stopVal = obj.length ; index<stopVal; index++) {
	 			
	 			amt = parseFloat(obj[index].value);
	 			
	 			total = manipulateValue(total , amt , 0);
	 			
	 		}
	 		
	 	}
	 	
	 	
	 	alert("Total :"+total);
	 	*/
	 	
	 	var grandTotal = calAllTariffNetCost("strBillTariffRefundAmount");
	 	document.getElementById("totalRecAmtDivId").innerHTML = grandTotal;
	 	document.forms[0].strOfflineTotalRecAmount.value = grandTotal;
	 		 	
	 	if(document.getElementsByName("strOfflineRefundTotalServiceTaxAmount").length > 0 ){
	 	
		 	var grandSerTotal = calAllTariffNetCost("strOfflineRefundServiceTaxAmount");
		 	document.forms[0].strOfflineRefundTotalServiceTaxAmount.value = grandSerTotal;
		 	
		 	var grandDisTotal = calAllTariffNetCost("strOfflineRefundDiscountAmount");
		 	document.forms[0].strOfflineRefundTotalDiscountAmount.value = grandDisTotal;
		 	
		 	var grandPenTotal = calAllTariffNetCost("strOfflineRefundPenaltyAmount");
		 	document.forms[0].strOfflineRefundTotalPenaltyAmount.value = grandPenTotal;
		 	
		 	var grandActTarTotal = calAllTariffNetCost("strOfflineRefundActualTariffAmount");
		 	document.forms[0].strOfflineRefundTotalActualTariffAmount.value = grandActTarTotal;
		 	
		 	setTotalToAmount('0');
	  			
	  			//	document.getElementsByName("strOfflineAmount")[0].focus();
		 	
		 	setTotalRefundAmount('0');
		 	
	 	}
	 }
	 
	 /**
	  * resetTotalAmount
	  * @param {Object} Select Object 
	  */
	  function resetTotalAmount(obj) {
	  	
	  	var reqType = document.forms[0].strOffLineRequestType.value;
	    	
	  	if(obj.value.length != 2){
	  	
	  		if(obj.value == 1){
	  			calcTotalRecAmount();	
	  		}else{
	  			calcRefundTotalAmount();
	  		}
	  	}else{
	  		
	  		if(reqType  == '1' && (obj.value == 19 || obj.value == 20)){
	  			
	  			
	  			var val = 0;
	  			
	  			if(document.getElementById("strPartpayment") != null)
	  			 val = document.getElementById("strPartpayment").value; 
	  			
	  			 	val = roundValue(val,2);
	  			
	  			document.getElementById("totalRecAmtDivId").innerHTML = val;
	  			
	  			document.forms[0].strOfflineTotalRecAmount.value = val;
	  			  			
	  			setTotalToAmount('0');
	  			
	  			//	document.getElementsByName("strOfflineAmount")[0].focus();
	  			
	  		}
	  	}
	  	
	  	if(reqType == '2'){
	  		
	  		 setTotalToAmount('0');
	  		setTotalRefundAmount('0');
	  	}
	  	
	  	
	  }
	 
	 
	 
	 
	 	 
	 /**
	 * calcTotalRecAmount 
	 */
	 function calcRefundReconcileTotalAmount() {
	 	var grandTotal = calAllTariffNetCost("strTariffNetAmt");
	 		 	
	 	document.getElementById("onlineGrandTotalDivId").innerHTML = grandTotal;
	 	document.forms[0].strOnlineGrandTotalAmount.value = grandTotal;
	 	
	 	if(document.getElementsByName("strTariffServiceTaxAmt").length > 0){
	 	
		 	var grandServiceTax = calAllTariffNetCost("strTariffServiceTaxAmt");
		 	document.forms[0].strTotalTariffServiceTaxAmount.value = grandServiceTax;
		 	
		 		 	
			var grandDiscountVal = calAllTariffNetCost("strTariffDiscountAmt"); 
		 	document.forms[0].strTotalTariffDiscountAmount.value = grandDiscountVal;
		 	
		 	var grandActualTariffVal = calAllTariffNetCost("strTariffActualAmt");
		 	document.forms[0].strTotalTariffActualAmount.value = grandActualTariffVal;
	 	
	 	}
	 	
	 	var clientBalAmtObj 	= document.forms[0].strOnLineBalanceAmount;
	 	var patPayableAmtObj 	= document.forms[0].strOnlinePatNetPayAmount;
	 	var patMaxBenefitAmtObj = document.forms[0].strOnlineMaxClientBenefitAmount;
	 	
	 		 	
	 	var cltAmtDtls = calClientAmount(clientBalAmtObj.value,patPayableAmtObj.value,grandTotal);
	 	
	 	
	 	patMaxBenefitAmtObj.value = cltAmtDtls.oClientAmt;
	 	
	 	
	 	/*
	 	if(clientBalAmtObj != null && patPayableAmtObj != null){
	 		
	 		clientBalAmt 		= parseFloat(clientBalAmtObj.value);
	 		patPayableAmt 		= parseFloat(patPayableAmtObj.value);
	 		
	 		if(clientBalAmt > patPayableAmt){
	 			
	 			patMaxBenefitAmtObj.value = patPayableAmt;
	 			
	 		}else{
	 			
	 			patMaxBenefitAmtObj.value = clientBalAmt;
	 		} 		
	 		
	 		
	 	}
	 	
	 	*/
	 
	 	
		checkForOnLinePaymentLimit('1');
	 	
	 }
	 
	 
	 
	 
	 
	 
	 /**
	  * setAdvPartAmt
	  */
	  function setAdvPartAmt() {
	  	
	  		var val = document.forms[0].strPartpayment.value; 
	  		
	  		 if(val != ''){	
	  	
	  	val = manipulateValue(parseFloat(val),0.00,0);
	   	val = roundValue(val,2);
	   	
	 	document.getElementById("totalRecAmtDivId").innerHTML = val;		
	  	document.forms[0].strOfflineTotalRecAmount.value = val;
	  	
	  	  	
	  }else{
	  		  	
	  	document.getElementById("totalRecAmtDivId").innerHTML = '0.00';		
	  	document.forms[0].strOfflineTotalRecAmount.value = 0;
	  }
	  
	  
	  	//	setTotalRefundAmount('0');
	  
	 	 	checkForOffLinePaymentLimit('1');
	 	
	 	
	  		
	  		
	  }
	 
	 
	 function setTotalRecAmt(obj){
	 	
	 	val = obj.value;
	 		 
	  if(val != ''){	
	  	
	  	val = manipulateValue(parseFloat(val),0.00,0);
	   	val = roundValue(val,2);
	   	
	 	document.getElementById("totalRecAmtDivId").innerHTML = val;		
	  	document.forms[0].strOfflineTotalRecAmount.value = val;
	  	
	  	
	  }else{
	  	document.getElementById("totalRecAmtDivId").innerHTML = '0.00';		
	  	document.forms[0].strOfflineTotalRecAmount.value = 0;
	  	
	  }
			
			setTotalToAmount('0');
	  		setTotalRefundAmount('0');
	  
	 	 		checkForOffLinePaymentLimit('1');
	 	
	 }
	 
	
	 	
	  function setTotalPaymentAmt(mode){
	 			 		
	 	if(mode == '0'){	
	 	 
	 	 var payTot  = document.getElementsByName("strOfflineAmount");
	 		 	 
	 	 var len = payTot.length - 1 ;	
	 	 		
	 	 var tot = parseFloat("0");	 
		 		 
		 for(var i = 0; i < len ; i++){
		 
		 		if(payTot[i].value == ''){
		 			 payTempTot = "0";
		 		}else{
		 			payTempTot = payTot[i].value;
		 		}
		 			 	
		 	tot = manipulateValue(tot,parseFloat(payTempTot),0);
		 	
		 }
		 	 	 
		 tot = roundValue(tot,2);
	 			
	 	//document.getElementById("offlineTotalPayAmtDivId").innerHTML = tot;		
	  //	document.forms[0].strOfflineTotalPayAmount.value = tot;
	  	
	 	  	
	  	setTotalRefundAmount('0'); 	
	  	
	 	}else{
	 		
	 		 var payTot = document.getElementsByName("strOnlineAmount");
	 	 var len = payTot.length - 1 ;	
	 	 		
	 	var tot = parseFloat("0");	 
		
		var payTempTot = 0; 		 
		 		 
		 for(var i = 0; i < len ; i++){
		 	if(payTot[i].value == ''){
		 		 payTempTot = "0";
		 	}else{
		 		payTempTot = payTot[i].value;
		 	}
		 	tot = manipulateValue(tot,parseFloat(payTempTot),0);
		 	
		 }
		 	 	 
		 tot = roundValue(tot,2);
	 			
	 	document.getElementById("onlineTotalPayAmtDivId").innerHTML = tot;		
	  	document.forms[0].strOnlineTotalPayAmount.value = tot;
	 		
	 	 		
	 		setTotalRefundAmount('1'); 	
	 		
	 	}
	 	 	 	
	 	 	
	 	
	 }
	 
	 
	 /**
	  * getCashAmount
	  * @param {String} mode 
	  */
	  function getCashPayObject(mode) {
	  	
	  	if(mode == '0'){
	  		
	  		
	  		
	  		 var payTot  = document.getElementsByName("strOfflineAmount");
	 		 var payMode = document.getElementsByName("strOfflinePaymentMode");	 	 
	 	 
	 	 	 var cashObj = null; 	
	 	 
	 	 	 var len = payTot.length - 1 ;	

		 		for(var i = 0; i < len ; i++){
		 
					var payModeVal = payMode[i][payMode[i].selectedIndex].value;
		 			 	
		 			if(payModeVal == '1')		 	
		 				cashObj = payTot[i];
		 		}
		 	 	 
		 	 	 
		 			
		return cashObj;  		
	  		
	  	}else{
	  		  		
	  		  		
	  		  		
	  		var payTot = document.getElementsByName("strOnlineAmount");
	 		var payMode = document.getElementsByName("strOnlinePaymentMode");	 
	 	 
	 	 	 var cashObj = null; 	
	 	 
	 	 	 var len = payTot.length - 1 ;	
	 	 		 	 	 		 		 
		 		for(var i = 0; i < len ; i++){
		 
		 			var payModeVal = payMode[i][payMode[i].selectedIndex].value;		 			 	
		 			if(payModeVal == '1')		 	
		 				cashObj = payTot[i];
		 		}
		 	 	 
		 			
		 				 			
		return cashObj;  
	  		
	  		
	  	}
	  	
	  	return null;
	  	
	  }
	 
	 
	  function validateTotalPaymentAmt(mode){
	 			 		
			var ctReqType = document.forms[0].currentReceiptType.value ;	 			 		
	 			 		
	 	if(mode == '0'){	
	 	 
	 	 var payTot  = document.getElementsByName("strOfflineAmount");
	 	 var payMode = document.getElementsByName("strOfflinePaymentMode");	 	 
	 	 
	 	 
	 	 
	 	 var len = payTot.length - 1 ;	
	 	 		
	 	 var tot = parseFloat("0");	 
		 		 
		 for(var i = 0; i < len ; i++){
		 
		 		if(payTot[i].value == ''){
		 			 payTempTot = "0";
		 		}else{
		 			payTempTot = payTot[i].value;
		 		}

				var payModeVal = payMode[i][payMode[i].selectedIndex].value;
		 			 	
		 		if(payModeVal != '1')		 	
		 			tot = manipulateValue(tot,parseFloat(payTempTot),0);
		 }
		 	 	 
		 tot = parseFloat(roundValue(tot,2));
	 			
		 var totRecAmt = parseFloat(document.forms[0].strOfflineTotalRecAmount.value) ;
	  	
	   	
	   
	   	
	  	if(ctReqType == '2'){
	  		
	  		totRecAmt  =  -1 * roundValue(totRecAmt,2);
	  	}
	  	
	  	 if(tot > totRecAmt){
	  		
	  		alert("Payment(s) Total, Other than Cash, Cannot be Grater than Net Payable Amount");		
	  		return false;
	  		
	  	 }else{
	  	 	
	  	 	return true;
	  	 }
	  	
	  
	  	//setTotalRefundAmount('0'); 	
	  	
	 	}else{
	 		
	 	
	 	var payTot = document.getElementsByName("strOnlineAmount");
	 	var payMode = document.getElementsByName("strOnlinePaymentMode");	 	 
	 	
	 	var len = payTot.length - 1 ;	
	 	 		
	 	var tot = parseFloat("0");	 
		
		var payTempTot = 0; 		 
		 		 
		 for(var i = 0; i < len ; i++){
		 	if(payTot[i].value == ''){
		 		 payTempTot = "0";
		 	}else{
		 		payTempTot = payTot[i].value;
		 	}
		 	
		 	var payModeVal = payMode[i][payMode[i].selectedIndex].value;
		 			 	
		 		if(payModeVal != '1')		 	
		 			tot = manipulateValue(tot,parseFloat(payTempTot),0);
		 	
		 }
		 	 	 
		 tot = parseFloat(roundValue(tot,2));
	 			
		 var totRecAmt = parseFloat(document.forms[0].strOnlineTotalRecAmount.value) ;
	  	
	  		if(ctReqType == '2'){
	  		
	  		totRecAmt  =  -1 * roundValue(totRecAmt,2);
	  	}
	  	
	  	 if(tot > totRecAmt){
	  		
	  		alert("Payment(s) Total Other than Cash, Cannot be Grater than Net Payable Amount");		
	  		return false;
	  		
	  	 }else{
	  	 	
	  	 	return true;
	  	 }
	 		//setTotalRefundAmount('1'); 	
	 		
	 	}
	 	 	 	
	 	 	
	 	
	 }
	 
	 
	 /**
	  * setTotalToAmount
	  * @param {String} mode 
	  */
	  function setTotalToAmount(mode) {
	  	
	  	
	  	var payModeTemp = document.getElementsByName("strOfflinePaymentMode")[0].value;
		var payModeDtlTemp = document.getElementsByName("strOfflinePaymentDtls")[0].value;	  		  	
	  	var payModeDisable = document.getElementsByName("strOfflinePaymentDtls")[0].disabled;
	  	
	  	if(mode == '0'){
	  			
	  		resetMultiRow('3');	
	  	
	  		addRows(new Array('strOfflinePaymentMode','strOfflinePaymentDtls','strOfflineAmount'),new Array('s','t','t'),'3','1','R');
	  		
	  		document.getElementsByName("strOfflinePaymentMode")[0].value = payModeTemp;
	  		document.getElementsByName("strOfflinePaymentDtls")[0].value = payModeDtlTemp;
	  		document.getElementsByName("strOfflinePaymentDtls")[0].disabled = payModeDisable ; 
	  		document.getElementsByName("strOfflineAmount")[0].value = "";
	  			  		
	  		var  totRecAmt = parseFloat(document.forms[0].strOfflineTotalRecAmount.value);	
	  	
	  		totRecAmt = roundValue(totRecAmt,2);
	  		
		  	if(parseFloat(totRecAmt) < 0){
		  		
		  		totRecAmt = -1 * parseFloat(totRecAmt);
		  		totRecAmt = roundValue(totRecAmt,2); 
		  		
		  	}
		  	
		  	document.getElementsByName("strOfflineAmount")[0].value = totRecAmt;
		  	
		  				  	
		  	//document.forms[0].strOfflineTotalPayAmount.value = totRecAmt;
		  	//document.getElementById("offlineTotalPayAmtDivId").innerHTML = totRecAmt;
		  
	  		
	  	}else{
	  		
	  		resetMultiRow('1');	
	  	
	  		addRows(new Array('strOnlinePaymentMode','strOnlinePaymentDtls','strOnlineAmount'),new Array('s','t','t'),'1','1','R');
	  		
	  		document.getElementsByName("strOfflinePaymentMode")[0].value = payModeTemp;
	  		document.getElementsByName("strOfflinePaymentDtls")[0].value = payModeDtlTemp;
	  		document.getElementsByName("strOfflinePaymentDtls")[0].disabled = payModeDisable ;
	  		document.getElementsByName("strOnlineAmount")[0].value = "";
	  		
	  		var totRecAmt = parseFloat(document.forms[0].strOnlineTotalRecAmount.value);
	  	
	  		totRecAmt = roundValue(totRecAmt,2);
	  	
	  		document.getElementsByName("strOnlineAmount")[0].value = totRecAmt;
	  	
	  	document.forms[0].strOnlineTotalPayAmount.value = totRecAmt;
	  	document.getElementById("onlineTotalPayAmtDivId").innerHTML = totRecAmt;
	  	 	  	 
	  	 
	  		
	  	}
	  	
	  }
	 
	 
	 
	 /**
	  * setTotalRefundAmount
	  * @param {String} mode  0 - Off-line 
	  * 					  1 - Online
	  */
	  function setTotalRefundAmount(mode) {
	  		  	  		
				var ctReqType = document.forms[0].currentReceiptType.value ;	  		  	  		
	  		  	  		  
	  	if(mode == '0'){
	  		
	  		/*
	  		  var totRecAmt = parseFloat("0");
	  		
	  		if(document.forms[0].currentReceiptType.value == '1'){
	  			
	  			   totRecAmt = parseFloat(document.forms[0].strOfflineTotalRecAmount.value);
	  			
	  		}else{
	  			
	  			   totRecAmt = -1 * parseFloat(document.forms[0].strOfflineTotalRecAmount.value);
	  		}
	  		
	  */		  	
	    
	  
	  //var  totRecAmt = parseFloat(document.forms[0].strOfflineTotalRecAmount.value);	
	  	
	  
	//  	  var totPayAmt = parseFloat(document.forms[0].strOfflineTotalPayAmount.value);
	  	
	  	//	if(totRecAmt > 0){
	  	//		tot = manipulateValue(totRecAmt,totPayAmt,1);
	  	//	}else{
	  	//			tot = manipulateValue(totRecAmt,totPayAmt,0);
	  	//	}
	  	//	tot = roundValue(tot,2);	
	  		  	
	  	//	document.forms[0].strOfflineRefundAmount.value = tot;
	  	//	document.getElementById("offlineRefundAmtDivId").innerHTML = tot;
	  	
	  	}else{
	  		
	  		
	  		/*
	  		 var totRecAmt = parseFloat("0");
	  		
	  		if(gblReqType == '1'){
	  			
	  			   totRecAmt = parseFloat(document.forms[0].strOnlineTotalRecAmount.value);
	  			
	  		}else{
	  			
	  			   totRecAmt = -1 * parseFloat(document.forms[0].strOnlineTotalRecAmount.value);
	  		}
	  		
	  	*/
	  	
	  	
	  	var totRecAmt = parseFloat(document.forms[0].strOnlineTotalRecAmount.value);
	  	 
	  	 
	  	  var totPayAmt = parseFloat(document.forms[0].strOnlineTotalPayAmount.value);
	  	
	  		if(totRecAmt > 0){
	  			tot = manipulateValue(totRecAmt,totPayAmt,1);
	  		}else{
	  				tot = manipulateValue(totRecAmt,totPayAmt,0);
	  		}
	  		tot = roundValue(tot,2);	
	  		  	
	  		document.forms[0].strOnlineRefundAmount.value = tot;
	  		document.getElementById("onlineRefundAmtDivId").innerHTML = tot;
	  	
	  	}
	  	
	}
	 
	 
	 /**
	  * getTariffByCode
	  * @param {Object} teriffCode 
	  * @param {event} e
	  */
	  function getTariffByCode(teriffCode , e) {
	  		  	
	  	if(e.keyCode == 13 && document.forms[0].strTariffCode.value.length > 0){
								 
			var hmode = "TARIFFCODEDTLS"; 
								
			var url = "";	
		 
			if(document.getElementById("wardDivId") != null && document.getElementById("wardDivId").style.display == "block"){
					
				var wardIdVal = document.forms[0].strOffLineWard[document.forms[0].strOffLineWard.selectedIndex].value.split('^')[0];	
						
				 url = "CashCollectionWithoutCrTransCNT.cnt?hmode="+hmode+"&groupId="+document.forms[0].strOffLineGroup[document.forms[0].strOffLineGroup.selectedIndex].value+"&hService="+document.forms[0].strOffLineRequestType[document.forms[0].strOffLineRequestType.selectedIndex].value+"&treatmentCat="+treatCat+"&ward="+wardIdVal+"&tariffCode="+document.forms[0].strTariffCode.value;
		 		 
			}else if(document.getElementsByName("strOffLineHospitalService") > 0){
				 url = "CashCollectionWithoutCrTransCNT.cnt?hmode="+hmode+"&groupId="+document.forms[0].strOffLineGroup[document.forms[0].strOffLineGroup.selectedIndex].value+"&hService="+document.forms[0].strOffLineRequestType[document.forms[0].strOffLineRequestType.selectedIndex].value+"&treatmentCat="+document.forms[0].strOffLineTreatmentCategory[document.forms[0].strOffLineTreatmentCategory.selectedIndex].value+"&ward=0"+"&tariffCode="+document.forms[0].strTariffCode.value;
			}else{
				
					url = "CashCollectionWithoutCrTransCNT.cnt?hmode="+hmode+"&groupId="+document.forms[0].strOffLineGroup[document.forms[0].strOffLineGroup.selectedIndex].value+"&hService="+document.forms[0].strOffLineRequestType.value+"&treatmentCat="+document.getElementsByName("strOffLineTreatmentCategory")[0].value+"&ward="+document.forms[0].strWardTypeId.value+"&tariffCode="+document.forms[0].strTariffCode.value;
					 			
				 
			}
			
		 			
			ajaxFunction(url,"27");
								 
				 
				return false;
			}	   	
	  	
	  	return false;
	  }
	 
	 
	 // functions from amit for Final Adjustment.
	 
	 function tariffDtl(parent,index,vals) //(this,index,ServiceTax,Discount,Penlty,TariffRate,Unit Name)
{        
	
		
		temp = vals.split('^');
	
        var objVal1 = document.getElementById("tariffId1");
        
        objVal1.innerHTML = temp[3]+"/"+ temp[4];
         
        var objVal2 = document.getElementById("tariffId2");
        objVal2.innerHTML = temp[1];        
        var objVal3 = document.getElementById("tariffId3");
        objVal3.innerHTML = temp[0];        
        var objVal4 = document.getElementById("tariffId4");
        objVal4.innerHTML = temp[2];        
                
		display_popup_menu(parent,'tariffDtl','300','');
}



function getParticularsDetails(obj,grpId,acctNo,index) 
{
 //alert("currId->"+document.forms[0].currId.value);
   var currId = document.forms[0].currId.value;
   var flg=document.getElementsByName("flag");
  
   var flag = 0;
   var obj = document.getElementById("particularDtlsId"+currId);
  	
  if(currId >= 0 && index != currId) 
  {
    	obj.style.display="none";
  }
  else 
  {
		//same button is clicked
		if(index == currId)
		 {
		 // alert((obj.style.display).toUpperCase());
			if((obj.style.display).toUpperCase() == "BLOCK") 
			{
				obj.style.display="none";
				flag = 1;
			}
		}
		
  }
  if(flg[index].value == "0")
  {
    //document.getElementById("id"+currId).style.display="none";
    fetchPopUpData(grpId,acctNo,index);
    document.forms[0].flag[index].value = "1";
  }
  else
  {
  	 if(flag == 0) 
  	 { 
     	document.getElementById("particularDtlsId"+index).style.display="block";
     }
  }
   if(flag == 1)
 		document.forms[0].currId.value = -1;		//all div closed
 	else	
 		document.forms[0].currId.value = index;	//particular div is open
 }
 
 //        Ajax Function in Response of Pop-Up       //
 function fetchPopUpData(grpId,acctNo,index)
  { 
     var mode="UNITVAL12";
     var modeVal = grpId+"^"+acctNo+"^"+index;
     var url="CashCollectionWithoutCrTransCNT.cnt?hmode="+mode+"&modName="+modeVal;
     
     ajaxFunction(url,"14");
  }

 
 /**
  * setSelectedCrNo
  * @param {String} crNo 
  */
  function setSelectedCrNo(crNo) {
  	
  	document.forms[0].strCrNo.value = crNo;
  	goFunc();
  	
  }
	 
	 
	 var refundTariffPopupParent = "";
	 
	 /**
	  * showRefundTariffPopup
	  * @param {Object} parent
	  * @param {String} hiddenValue  
	  */
	  function showRefundTariffPopup(parent,hidVal) {
	  	
	  	refundTariffPopupParent = parent;
	  	
	  		var hmode = "REFUNDTARIFFPOPUPDTLS"; 
			var url = "CashCollectionWithoutCrTransCNT.cnt?hmode="+hmode+"&hidValue="+hidVal;

			ajaxFunction(url,"15");
	  	
	  }
	 
	 /**
		 * getRefundTariffUnit
		 * @param {String} unitId
		 * @param {String} baseValue
		 * @param {String} index 
		 */
		 function getRefundTariffUnit(unitId,baseValue,index) {
		 	var hmode = "TRFUNIT"; 
			var url = "CashCollectionWithoutCrTransCNT.cnt?hmode="+hmode+"&unitId="+unitId+"&baseValue="+baseValue+"&rowIndex="+index;
			
			ajaxFunction(url,"16");
		 }
	 
	 /**
	  * activateTariffRefundDtls
	  * @param {Object} obj 
	  * @param {String} index 
	  */
	  function activateTariffRefundDtls(obj,index) {
	  	
	  	if(obj.checked){
	  				  			  				  				  			  		
	  		document.getElementById("strTariffRefundQty"+index).disabled = false;
	  		document.getElementById("strTariffRefundUnit"+index).disabled = false;
	  		document.getElementById("strRefundTariffDetails"+index).disabled = false;
	  		document.getElementById("strTariffToBeRefundQty"+index).disabled = false;
	  		document.getElementById("strTariffRefundCost"+index).disabled = false;
	  		
	  		document.getElementById("strTariffServiceTaxAmt"+index).disabled = false;
	  		document.getElementById("strTariffActualAmt"+index).disabled = false;
	  		document.getElementById("strTariffDiscountAmt"+index).disabled = false;
	  		document.getElementById("strTariffPenaltyAmt"+index).disabled = false;
	  		
	  		
	  		
	  		document.getElementById("strTariffRefundQty"+index).value = document.getElementById("strTariffToBeRefundQty"+index).value;
	  			  		
	  		calcRefundTariffAmount(index);
	  			  			  		
	  	}else{
	  		document.getElementById("strTariffRefundQty"+index).value = 0;
	  		document.getElementById("strTariffRefundQty"+index).disabled = true;
	  		
	  		
	  		document.getElementById("strTariffRefundUnit"+index).disabled = true;
	  		document.getElementById("strRefundTariffDetails"+index).disabled = true;
	  		document.getElementById("strTariffToBeRefundQty"+index).disabled = true;
	  		document.getElementById("strTariffRefundCost"+index).disabled = true;
	  		
	  		document.getElementById("strTariffServiceTaxAmt"+index).disabled = true;
	  		document.getElementById("strTariffActualAmt"+index).disabled = true;
	  		document.getElementById("strTariffDiscountAmt"+index).disabled = true;
	  		document.getElementById("strTariffPenaltyAmt"+index).disabled = true;
	  		
	  		 calcRefundTariffAmount(index);
	  	}
	  
	 
	  
	  }
	  
	  
	  
	  
	 
	 /**
	  * calcRefundTariffAmount
	  * @param {String} index 
	  */
	  function calcRefundTariffAmount(index) {
	  	
	  	  	
	  	hidVal = document.getElementById("strRefundTariffDetails"+index).value;
	  	  	
	  	var tempArr =  hidVal.split('^');
	  	
	  	var rate = tempArr[3];
	  	var discountUnit = tempArr[8];
	  	var discountType = tempArr[9];
	  	var serviceUnit = tempArr[7];
	  	var penaltyUnit = tempArr[16];
	  	var rateUnitBaseVal = tempArr[13];
	  	var actRate = tempArr[6];
	  	
	  	var toBeRefundQty = document.getElementById("strTariffToBeRefundQty"+index).value;
	  	
	  	var refundQty = document.getElementById("strTariffRefundQty"+index).value;
	  		  	
	  	if(toBeRefundQty >= refundQty ){
	  	
	  	var refundUnitBaseVal = document.getElementById("strTariffRefundUnit"+index)[document.getElementById("strTariffRefundUnit"+index).selectedIndex].value; 
	  	
	  	if(refundUnitBaseVal == '0'){
	  		refundUnitBaseVal = "0^0";
	  	}
	  
	  	var calAmt = calTrfNetAmountWithActTrfAmt(rate, actRate ,rateUnitBaseVal,discountUnit,discountType,refundQty,refundUnitBaseVal,serviceUnit,penaltyUnit,1);
	  	
	  	document.getElementById("strTariffRefundCost"+index).value = calAmt.oNetTrfAmt;  
	  	document.getElementById("strTariffRefundCostDivId"+index).innerHTML = calAmt.oNetTrfAmt;  	
	  	
	  	
	  	document.getElementById("strTariffServiceTaxAmt"+index).value = calAmt.oSerAmt;  
	  	document.getElementById("strTariffActualAmt"+index).value = calAmt.oActTrfAmt;  
	  	document.getElementById("strTariffDiscountAmt"+index).value = calAmt.oDisAmt;  
	  	document.getElementById("strTariffPenaltyAmt"+index).value = calAmt.oPenAmt;
	  	
	  	
	  	}else{
	 		
	 			alert("Refund Quantity Cannot be More than To Be Refund Quantity");
	 			document.getElementById("strTariffRefundQty"+index).value = 0;
	 			
	 			calcRefundTariffAmount(index);
	 	}
	  	
	  	var grandTotal = calAllTariffNetCost("strTariffRefundCost");
	  	
	  	var grandServiceTaxTotal = calAllTariffNetCost("strTariffServiceTaxAmt");
	  	document.forms[0].strTotalTariffServiceTaxAmount.value = grandServiceTaxTotal;
	  	
	  	var grandActTariffTotal  = calAllTariffNetCost("strTariffActualAmt");
	  	document.forms[0].strTotalTariffActualAmount.value = grandActTariffTotal;
	  	
	  	var grandDiscTotal    =   calAllTariffNetCost("strTariffDiscountAmt");
	  	document.forms[0].strTotalTariffDiscountAmount.value = grandDiscTotal;
	  	
	  	var grandPenaltyTotal    =   calAllTariffNetCost("strTariffPenaltyAmt");
	  	document.forms[0].strTotalTariffPenaltyAmount.value = grandPenaltyTotal;
	  	
	  	
	  	
	  	
	  	document.getElementById("onlineTotalRecAmtDivId").innerHTML = grandTotal;
	  	document.forms[0].strOnlineTotalRecAmount.value = grandTotal; 
	  	
	  }
	 
	 /**
	  * checkForOffLinePaymentLimit 
	  * @param {String} mode - 0 : checks the payment limit if amount is more than 0 adds a new row and enables the payment mode combos else disables all payment mode combos 
	  * 					 - 1 : checks the payment limit if amount is more than 0 enables the payment mode combos else disables all payment mode combos
	  */
	  function checkForOffLinePaymentLimit(mode) {
	  	
	  	
	  	var totalVal = document.getElementById("strOfflineTotalRecAmount").value;
	  		  	
	  	totalVal = parseFloat(totalVal);
	  	
	  	
	  	if(totalVal > 0){
	  		
	  		if(mode == '0')
	  			addRows(new Array('strOfflinePaymentMode','strOfflinePaymentDtls','strOfflineAmount'),new Array('s','t','t'),'3','1','R');
	  		
	  		var obj = document.getElementsByName("strOfflinePaymentMode");
	  		var obj2 = document.getElementsByName("strOfflinePaymentDtls");
	  		
	  		for(var i = 0; i < obj.length  ; i++){
	  			
	  			obj[i].disabled = false;
	  			
	  			if(obj[i].value == '1')
					obj2[i].disabled = false;	  				  			
	  		}
	  		
	  	
	  	}else{
	  		
	  		if(mode == '0')
	  			addRows(new Array('strOfflinePaymentMode','strOfflinePaymentDtls','strOfflineAmount'),new Array('s','t','t'),'3','1','R');
	  	
	  		
	  		var obj = document.getElementsByName("strOfflinePaymentMode");
	  		var obj2 = document.getElementsByName("strOfflinePaymentDtls");
	  		  		
	  		for(var i = 0; i < obj.length  ; i++){
	  			
	  			if(obj[i].value == '4' || obj[i].value == '5' ){
	  				obj[i].disabled = true;
	  				
	  			}else{
	  				
	  				obj[i].disabled = false;
	  			}
	  			
	  			if(obj[i].value == '1')
					obj2[i].disabled = false;
	  			
	  		}
	  		
	  	}
	  	
	  }
	
	 /**
	  * checkForOnLinePaymentLimit 
	  * @param {String} mode - 0 : checks the payment limit if amount is more than 0 adds a new row and enables the payment mode combos else disables all payment mode combos 
	  * 					 - 1 : checks the payment limit if amount is more than 0 enables the payment mode combos else disables all payment mode combos
	  */
	  function checkForOnLinePaymentLimit(mode) {
	  	
	  	
	  	var totalVal = document.forms[0].strOnlineTotalRecAmount.value;
	  		  		  	
	  	totalVal = parseFloat(totalVal);
	  		  	
	  	if(totalVal > 0){
	  		
	  		if(mode == '0')
	  			addRows(new Array('strOnlinePaymentMode','strOnlinePaymentDtls','strOnlineAmount'),new Array('s','t','t'),'1','1','R');
	  		
	  		var obj = document.getElementsByName("strOnlinePaymentMode");
	  		var obj2 = document.getElementsByName("strOnlinePaymentDtls");
	  		for(var i = 0; i < obj.length  ; i++){
	  			
	  			obj[i].disabled = false;
	  			
	  			if(obj[i].value == '1')
					obj2[i].disabled = false; 			
	  		}
	  		
	  	
	  	}else{
	  		
	  		if(mode == '0')
	  			addRows(new Array('strOnlinePaymentMode','strOnlinePaymentDtls','strOnlineAmount'),new Array('s','t','t'),'1','1','R');
	  		
	  		
	  		var obj = document.getElementsByName("strOnlinePaymentMode");
	  		var obj2 = document.getElementsByName("strOnlinePaymentDtls");
	  		
	  		for(var i = 0; i < obj.length  ; i++){
	  			
	  			if(obj[i].value == '4' || obj[i].value == '5' ){
	  				obj[i].disabled = true;
	  				
	  			}else{
	  				
	  				obj[i].disabled = false;
	  			}
	  			
	  			if(obj[i].value == '1')
					obj2[i].disabled = false;
	  			
	  		}
	  		
	  	}
	  	
	  }
	
	 
	 /**
	  * changeWithoutCrNoRequestMode
	  * @param {Object} obj 
	  */
	  function changeWithoutCrNoRequestMode(obj) {
	  	
	  	document.forms[0].currentReceiptType.value = obj.value;
	  	
	  	if(obj.value == 1){
	  		
	  		document.getElementById("refundDtlsDivId").style.display = "none";
	  		document.getElementById("withoutCrNoRefundTariffDivId").style.display = "none";
	  		document.getElementById("otherRefundDetailsDivId").style.display = "none";
	  		
	  		document.getElementById("receiptDtlsDivId").style.display = "block";
	  		document.getElementById("receiptTariffDivId").style.display = "block";
	  		document.getElementById("paymentModeDtlsDivId").style.display = "block";
	  		
	  		document.getElementById("onlineRequestComboDivId").style.display = "block";
	  		
	  			document.forms[0].strGuarantorName.value ="";		 		
		 		document.forms[0].strGuarantorContactNo.value ="";		 		
		 		document.forms[0].strGuartorAddress.value ="";
		 		
		 		resetMultiRow('2');
		 		
		 			addRows(new Array('strOfflineTariffName','strOfflineTariffDetailsHidden','strOfflineTempTariffRateUnit','strOfflineTariffRateUnit','strOfflineTariffQty','strOfflineTariffUnit','strOfflineTariffNetAmount','strOfflineTotalActualTariffAmtVal'),new Array('t','t','t','t','t','s','t','t','t','t','t','t'),'2','1','R')
		 			
	  		
	  	}else{
	  		
	  		document.forms[0].strBillNo.disabled = false;
	  		document.forms[0].strBillNo.value = "";
	  				
	  		document.getElementById("refundDtlsDivId").style.display = "block";
	  		
	  		document.getElementById("receiptDtlsDivId").style.display = "none";
	  		document.getElementById("receiptTariffDivId").style.display = "none";
	  		document.getElementById("paymentModeDtlsDivId").style.display = "none";
	  		document.getElementById("onlineRequestComboDivId").style.display = "none";
	  	
	  	}
	  		
	  		calcOnlineWithoutCrNoTotalRecAmount();
	  			  	
	  }
	  
		
	/**
	 * showRefundAdvanceDtls
	 * @param {Object} parentObj 
	 * @param {String} penaltyDtls
	 */
	 function showRefundAdvanceDtls(parentObj , penaltyDtls) {
	 	
	 	var temp = penaltyDtls.split('^');
	 	
	 	var penaltyVal 		= temp[0];
	 	var penaltyAmtVal 	= temp[1];
	 	
	 	penaltyAmtVal = roundValue(penaltyAmtVal,2);
	 	
	 	document.getElementById("refAdvancePenalty").innerHTML = penaltyVal;
	 	document.getElementById("refAdvancePenaltyAmt").innerHTML = penaltyAmtVal;
	 		 	
	 	display_popup_menu( parentObj , "refundAdvanceDtl" , "" , "");
	 
	 } 
	 
	 
	 /**
	  * displayOnlineNetAmountDetails
	  * @param {Object} parentObj
	  * @param {String} divId  
	  */
	  function displayOnlineNetAmountDetails(parentObj , divId) {
	  	
	  	var expObj = document.getElementsByName("strExpenseAmount");
	  	
	  	if(expObj.length > 0){
	  	
	  	var strExpAmt = roundValue(expObj[0].value,2);
	  	var strCltAmt = roundValue(document.forms[0].strNetClientAmount.value,2);
		var strDisAmt = roundValue(document.forms[0].strNetDiscountAmount.value,2);
		var strSerAmt = roundValue(document.forms[0].strNetServiceTaxAmount.value,2);	  		
	  	
	  	document.getElementById("finalAdjustExpenseAmount").innerHTML = strExpAmt;
	 	document.getElementById("finalAdjustClientAmount").innerHTML = strCltAmt;
	 	document.getElementById("finalAdjustDiscountAmount").innerHTML = strDisAmt;
	 	document.getElementById("finalAdjustServiceAmount").innerHTML = strSerAmt;
	 	
	 	display_popup_menu( parentObj , divId , "250" , "");
	  	
	  	
	  	}else{
	  		
	  		alert("No Details Available");
	  		
	  	}
	  }
	 
	 	 
	 
	 /**
	  * goBillFunc 
	  */
	  function goBillFunc() {
	  	
	  	if(document.forms[0].strBillNo.value != ""){
	  	
	  	var val = document.forms[0].strBillNo.value.charAt(0);
	  		
	  		//if(val == '5'){
	  	
	  			var hisValidator = new HISValidator("EstEnquiryTransBean");  
	
				hisValidator.addValidation("strBillNo", "req", "Receipt No. is a Mandatory Field" );
				hisValidator.addValidation("strBillNo", "minlen=10", "Receipt No. must be 10 Digits" );
	
				var retVal = hisValidator.validate(); 
				hisValidator.clearAllValidations();
		
					if(retVal){
						var hmode = "GOBILL"; 
						var url = "CashCollectionWithoutCrTransCNT.cnt?hmode="+hmode+"&billNo="+document.forms[0].strBillNo.value;
		
						document.forms[0].strBillNo.disabled = true;
		
						ajaxFunction(url,"19");
						
						setRefundRemarksText();
								
					}else{
						return false;
					}
	  		/*}else{
	  			
	  			document.getElementById("errMsg").innerHTML = "Invalid Receipt No.";
				document.forms[0].strBillNo.value = "";
				document.forms[0].strBillNo.focus();
	  		}*/
	  	}
	  }
	  
	  
	  function onKeyPressLogic(strMode , eve){
	  
	  		if(eve.keyCode == 13){
												
			 	if(strMode == '1'){
			 		 
			 			return findBillServices();
			 			 
			 	}else if(strMode == '2'){
			 		
			 		clearFunc(document.forms[0].strCounterMode.value);
			 		
			 	}else{
			 		
			 		cancelFunc();
			 	
			 	} 
				
				return false;
	   	
			}else{
	   		return false;
	   }
	  
	  }
	  
	  
	  function onKeyPressLogic1(strMode , eve){
	  
	  		if(eve.keyCode == 13){
												
			 	if(strMode == '1'){
			 		 
			 				return saveWithouCrNoDtls();
			 				 
			 		
			 	}else if(strMode == '2'){
			 		
			 		clearBillFunc();
			 		
			 	}else{
			 		
			 		cancelFunc();
			 	
			 	} 
				
				return false;
	   	
			}else{
	   		return false;
	   }
	  
	  }
	  	  
	  
	  /**
	   * initGoBillFunc
	   * @param {Event} eve 
	   */
	   function initGoBillFunc(eve){
	   	
	   	 var flag=validateData(eve,5);
  		 if(flag){
	   	
	   	if(eve.keyCode == 13){
												
				goBillFunc();
				
				return false;
			}	   	
			}else{
	   		return false;
	   }
	   }
	 
	  /**
	   * initGoBillFunc
	   * @param {Event} eve 
	   */
	   function initGoFunc(eve){
	   	
	   	
	   	 var flag=validateData(eve,5);
  		 if(flag){
	   		
	   		if(eve.keyCode == 13){									
				return goFunc();
			}	   	
	   }else{
	   		return false;
	   }
	  
	 }
	 
	 
	  /**
	   * initWithoutCrNoGoFunc
	   * @param {Event} eve 
	   */
	   function initWithoutCrNoGoFunc(eve){
	   	
	   	
	   	 var flag=validateData(eve,8);
  		 if(flag){
	   		
	   		if(eve.keyCode == 13){									
				return goWithoutCrNoFunc();
			}	   	
	   }else{
	   		return false;
	   }
	  
	 }
	 
	 
	 
	 
	 
	 
	/**
	 * goWithoutCrNoFunc - validates the Previous Cr Number Text Box  
	 */
	function goWithoutCrNoFunc(){
	
	if(document.forms[0].strPreviousCrNo.disabled == false){
		var hisValidator = new HISValidator("EstEnquiryTransBean");  
	
		hisValidator.addValidation("strPreviousCrNo", "req", "Unique Id. is a Mandatory Field" );
		hisValidator.addValidation("strPreviousCrNo", "maxlen=10", "Unique Id. must be Maximum 10 Digits" );
	
		var retVal = hisValidator.validate(); 
		hisValidator.clearAllValidations();
		
			if(retVal){
				 				 
	document.forms[0].strIsDetailsReceived.value = 1;			 
		
	document.forms[0].strTempPreviousCrNo.value = document.forms[0].strPreviousCrNo.value;			 
				 
  	var hmode = "PREVIOUSCRNODTL"; 
  	
  	var url = "EstEnquiryTransCNT.cnt?hmode="+hmode+"&strPreviousCrNo="+document.forms[0].strPreviousCrNo.value;
		//alert(url);	
		ajaxFunction(url,"28");
  	 
		
		return false;
				
			}else{
				return false;
			}
	}else{
		return false;
	}
	}
	 
	 
	 
	 /**
	  * cancelFunc 
	  */
	  function clearBillFunc() {
	  	
	  	var val = document.forms[0].strOffLineRequestType[document.forms[0].strOffLineRequestType.selectedIndex].value ;
	  	
	  	if(val == 1){
	  		
	  		document.forms[0].hmode.value = "BILLCANCEL";
	  		document.forms[0].submit();
	  	}else{
	  		
	  		document.forms[0].strBillNo.disabled = false;
	  		document.forms[0].strBillNo.value ="";
	  		
	  		document.getElementById("refundDtlsDivId").style.display = "block";
	  		
	  		document.getElementById("withoutCrNoRefundTariffDivId").style.display = "none";
	  		document.getElementById("otherRefundDetailsDivId").style.display = "none";
	  		document.getElementById("receiptDtlsDivId").style.display = "none";
	  		document.getElementById("receiptTariffDivId").style.display = "none";
	  		document.getElementById("paymentModeDtlsDivId").style.display = "none";
	  		
	  	}
	  	
	  	calcOnlineWithoutCrNoTotalRecAmount();
	  	
	  }
	 
	 
	 /**
	  * setSelectedBillNo
	  * @param {String} billNo 
	  */
	  function  setSelectedBillNo(billNo) {
	  	
	  	document.forms[0].strBillNo.value = billNo;
	  	
	  	goBillFunc();
	  }
	 
	 
	 // gblRowPerPage - which determines how many record should be shown in a Page, also determines how many record should be fetch from 
	var gblRowPerPage = "10";
	 
	 /**
 * showBillListingWindow
 * @param {String} mode 
 * @param {String} userJsFunctionName 
 */
 function showBillListingWindow(userJsFuncName) {
 	
 	if(document.forms[0].strBillNo.value == ""){
 	
 				var hmode = "BILLLISTING"; 
				
				var featuresList = "width=700,height=400,ALIGN=CENETER,left=200,top=200,scrollbars=yes"
					
				var myWindow = window.open(createFHashAjaxQuery('CashCollectionWithoutCrTransCNT.cnt?hmode='+hmode+"&usrFuncName="+userJsFuncName,'popupWindow',featuresList)); 
		
					if(! myWindow.opener){
						myWindow.opener = window;
					}
 	}
				
 }
	 
	 /**
  * fetchPatientList
  * @param {String} patListType 
  */
  function fetchBillList(fromRows,blockSet) {
  	
  		
  		var convString = "";
  		
  		var searchString = document.forms[0].strSearchString.value ;
  		  		
  		convString = searchString;
  		
  		if(searchString.indexOf('%') != -1){
  			
  			var searchArr = searchString.split("%");
  			for(var i=0;i<searchArr.length;i++)
  				if(i == 0)
  					convString = searchArr[i];
  				else
  					convString = convString + "^" + searchArr[i];
  		}
  		
  		var searchType = document.forms[0].strSearchType[document.forms[0].strSearchType.selectedIndex].value;
  		
  	var hmode = "FETCHBILLLISTING"; 
  	
  	var url = "CashCollectionWithoutCrTransCNT.cnt?hmode="+hmode+"&searchString="+convString+"&searchType="+searchType+"&fromRow="+fromRows+"&rowPerPage="+gblRowPerPage+"&ctBlockSet="+blockSet;
			
		ajaxFunction2(url,"1","getBillListDetailAjaxResponse");
  	
  	
  }
  
  /**
   * getPatientListDetailAjaxResponse
   * @param {String} res
   * @param {String} mode  
   */
   function getBillListDetailAjaxResponse(res , mode) {
   	
   	
	   	if(mode == '1'){
	
			var objEle = document.getElementById("fetchRecordDivId");
							
			var resultVal = res.split("####");
							
			if(resultVal[0] == "ERROR"){
					
					objEle.innerHTML = "<table width='100%'align='center' cellspacing='1px'><tr><td class='multiControl'><b><font color='red'>"+resultVal[1]+"</font></b></td></tr></table>";
					
			}else{
					objEle.innerHTML = res;
			}

	   }
	   	
	   	if(mode=='2')
	   	{
	   		var objVal= document.getElementById("billServiceDivId");
			objVal.innerHTML = "<select name ='strOffLineBillingService' class='comboNormal' onChange =''>" + res + "</select>";
	   	}
  }
   
    /**
   	 * setBillNo
   	 */
   	 function setBillNo() {
   	 		
   	 		var billNoObj = document.getElementsByName("strBillNo");
   	 		var flag = false;
   	 		for(var i = 0, end = billNoObj.length ; i < end ; i++){
   	 			
   	 			if(billNoObj[i].checked){
   	 				
   	 				var result = billNoObj[i].value;
   	 				flag = true;
   	 				 window.close();
					window.opener.eval(document.forms[0].userJsFunctionName.value+"('"+result+"')");
   	 			}
   	 			
   	 		}
   	 		
   			if(! flag) alert("Please Select a Record");
	
   	 }
   
   function layerIndexNavigator(index,endVal){
		
			hideAll(endVal);
		
		initObj = document.getElementsByName("linId");
		
		if(initObj.length > 0){
			
			for(var i = 0 , len = initObj.length ; i < len ; i ++ ){
				
				initObj[i].style.color = defaultColor;
			}
			
	}
		
		
		
		obj = document.getElementById("linId"+index);
		if(obj != null) obj.style.color = setColor;
		
		document.getElementById("tariffDivId"+index).style.display="block";
	}
   
   function hideAll(endVal){
	
		for(var i = 1; i <= endVal ; i++){
			document.getElementById("tariffDivId"+i).style.display="none";
		}
		
	}   
	
	 /**
	 * showCltDetails - shows the given divId by showing the minus image and hiding the plus image.
	 * @param {object} divId - div id which should be shown.
	 */
	function showHelpDetails(divId){
		
		document.getElementById(divId).style.display="block";
				
		document.getElementById('minus'+divId).style.display="block";
		document.getElementById('plus'+divId).style.display="none";
		
	}
	
	
	
	/**
	 * hideCltDetails - hide the given divId by hiding the minus image and showing the plus image.
	 * @param {object} divId - div id which should be shown.
	 */
	function hideHelpDetails(divId){
		document.getElementById(divId).style.display="none";
			
		document.getElementById('minus'+divId).style.display="none";
		document.getElementById('plus'+divId).style.display="block";
		
	}
	 
	 
	 function validateSearchText(eve){
	 	
	 		if(eve.keyCode == 13){
	 				
	 				
	 				 		
	 				var hisValidator = new HISValidator("EstEnquiryTransBean");  
	
					hisValidator.addValidation("strSearchString", "req", "Please Enter a Search String" );
						
					var retVal = hisValidator.validate(); 
					hisValidator.clearAllValidations();
		
					if(retVal){
						
						var searchType = document.forms[0].strSearchType[document.forms[0].strSearchType.selectedIndex].value;
						if(searchType== 3){
						 
						 var ve = document.forms[0].strSearchString.value;
						 var str = "0123456789%";
						 
						 for (n = 0; n < ve.length; n++)
						 	if (((str).indexOf(ve.charAt(n)) <= -1)){
         						alert("Search String must be Numeric");
        						 return false;
      							}
						 						
					} 
						
						fetchBillList('1','1');
	 				}else{
						return false;
					}
	 		
	 		 return false;
		 }
		 
		
	 }
	 
	 
	
	 function getSearchBillListBySearch() {
	 	
	 	var hisValidator = new HISValidator("EstEnquiryTransBean");  
	
				var searchType = document.forms[0].strSearchType[document.forms[0].strSearchType.selectedIndex].value;
	
					hisValidator.addValidation("strSearchString", "req", "Please Enter a Search String" );
					 
					var retVal = hisValidator.validate(); 
					hisValidator.clearAllValidations();
		
					if(retVal){
						
						if(searchType== 3){
						 
						 var ve = document.forms[0].strSearchString.value;
						 var str = "0123456789";
						 
						 for (n = 0; n < ve.length; n++)
						 	if (((str).indexOf(ve.charAt(n)) <= -1)){
         						alert("Search String must be Numeric");
        						 return false;
      							}
						 						
					} 
						
						fetchBillList('1','1');
	 				}else{
						return false;
					}
	 	
	 		 return false;
	 		
	 	} 
	 
	
	/**
	 * findServiceandSave
	 */
	 function findBillServices() {
		if(!showAlert())
	 		return false;
	 	var ctState = document.forms[0].currentState.value ;
	 		 	
	 	
	 	if(ctState == '1'){ // On-line mode
	 		
	 			var ctBSerId = document.forms[0].currentBserviceId.value ;
			 	var ctReqType = document.forms[0].currentReceiptType.value ;
	 			
	 			
	 			//alert("ctBSerId : "+ctBSerId);
	 			//alert("ctReqType : "+ctReqType);
	 			
	 		// Online Receipt Part Payment Mode
	 		if(ctBSerId == '20' && ctReqType == '1')	 			
	 			document.forms[0].hmode.value = "ONRECPARTPAY";
	 		
	 		// Online Receipt Advance Mode
	 		if(ctBSerId == '19' && ctReqType == '1')
	 			document.forms[0].hmode.value = "ONRECADV";
	 			
	 		
	 		if((ctBSerId == '10' || ctBSerId == '11' || ctBSerId == '12') && ctReqType == '1')
	 			document.forms[0].hmode.value = "ONRECSERVICE";
	 			

			if((ctBSerId == '10' || ctBSerId == '11' || ctBSerId == '12') && ctReqType == '2')
	 			document.forms[0].hmode.value = "ONREFUNDSERVICE";	 			
	 			
	 		if(ctBSerId == '19' && ctReqType == '2' )
	 			document.forms[0].hmode.value = "ONREFUNDADVANCE";	 			
	 			
	 		if(ctBSerId == '21' && ctReqType == '1' )
	 			document.forms[0].hmode.value = "ONFINALADJUST";	 			
	 				
	 		
	 		if(ctReqType == '3' )
	 			document.forms[0].hmode.value = "ONRECONCILE";	 	
	 				
	 		
	 		
	 	}else{ // Off-line Mode
	 		
	 			
	 			var ctBSerId = document.forms[0].strOffLineBillingService[document.forms[0].strOffLineBillingService.selectedIndex].value ;
			 	var ctReqType = document.forms[0].strOffLineRequestType[document.forms[0].strOffLineRequestType.selectedIndex].value ;
	 			
	 			
	 		//	alert("ctBSerId : "+ctBSerId);
	 		//	alert("ctReqType : "+ctReqType);
	 			
	 		//	alert("offline mode");
	 			// Off-line Receipt Part Payment Mode
	 		if(ctBSerId == '20' && ctReqType == '1')
	 		document.forms[0].hmode.value = "OFFRECPARTPAY";
	 		
	 		// Off-line Receipt Advance Mode
	 		if(ctBSerId == '19' && ctReqType == '1')
	 			document.forms[0].hmode.value = "OFFRECADV";

			// Off-line Receipt Service Mode	 				 		
	 		if((ctBSerId == '10'|| ctBSerId == '11' || ctBSerId == '12') && ctReqType == '1')
	 			document.forms[0].hmode.value = "OFFRECSER";

			if((ctBSerId == '10'|| ctBSerId == '11' || ctBSerId == '12') && ctReqType == '2')
	 			document.forms[0].hmode.value = "OFFREFUNDSER";
	 			 		
			if(ctBSerId == '19' && ctReqType == '2')
	 			document.forms[0].hmode.value = "OFFREFUNDADMCANCEL";
	 			 		
	 	}
	 	
	 	//alert("mode = "+document.forms[0].hmode.value);
	 	
	 	return validateAndSubmit(document.forms[0].hmode.value);
	 	
	 } 
	
	/**
	 * validateAndSubmit
	 */
	
	 function validateAndSubmit(hmodeVal) {
	 		 	
	 	 		
	 		 	if(document.getElementById("errMsg").innerHTML.length > 0 && document.getElementById("errMsg").style.display == 'block'){
	
					alert("Cannot Save due to Error:"+document.getElementById("errMsg").innerHTML);
					return false;	 		 		
	 		 		
	 		 	}		 	
		 		 	
	 		 	
	 	// Opd, Ipd, Emergency Receipt Services for Offline
	 	if(hmodeVal == "OFFRECSER"){
	 		 		 	 		 		 
	 		 		 deleteUnwantedRows("strOfflineTariffName" , '2' );
	 		 		 	 		 		 		
	 		 		 		
	 			var hisValidator = new HISValidator("EstEnquiryTransBean");  
					hisValidator.addValidation("strOfflineTariffName", "req", "Tariff Name is Mandatory Field" );	
					hisValidator.addValidation("strOfflineTempTariffRateUnit", "req", "Tariff Rate/Unit is Mandatory Field" );
					hisValidator.addValidation("strOfflineTariffQty", "req", "Tariff Quantity is Mandatory Field" );
					hisValidator.addValidation("strOfflineTariffUnit", "dontselect=0", "Please Select a Tariff Unit" );
					hisValidator.addValidation("strOfflineAmount", "req", "Amount is Mandatory Field" );
					//hisValidator.addValidation("strOfflineAmount", "numgt=0", "Amount should be Grater than 0.00" );
					
					var res = hisValidator.validate(); 
					hisValidator.clearAllValidations();

					if(! res){
						
						generateRows();	
						
						 return false;
					} 
					
		res = checkMultirow('strOfflineTariffName', "Tariff Name Already Exists");			
			
				if(! res){
						
						generateRows();	
						
						 return false;
					} 

					res = checkMultirow('strOfflinePaymentMode', "Payment Mode Already Exists");										
										
				if(res){
	 			
	 				return offlineSubmitPart(); 	
	 			
	 		}else{
	 			 
	 			 generateRows();	
	 			 
	 			return false;
	 		}
	 		
	 		
	 	}
	 	
	 	
	 	// Opd, Ipd, Emergency Refund Services for Offline
	 	if(hmodeVal == "OFFREFUNDSER"){
	 		
	 		var billObj = document.getElementsByName("strOfflineRefundBillDetails");
	 		
	 		if(billObj.length < 1){
	 			
	 			alert("No Refund Receipt(s) Available");
	 			return false;
	 			
	 		}
	 			 		
	 		var chkObj = document.getElementsByName("strBillTariffVal");
	 		
	 		if(chkObj.length > 0){
	 			
	 			var count = parseInt("0");
	 			
	 			for(var index=0, len = chkObj.length; index<len; index++) {
	 				
	 				if(chkObj[index].checked){
	 					count = count + 1;
	 				}
	 				
	 			}
	 				
	 		
	 			if(count < 1){
	 					
	 				alert("Please Select atleast One Tariff for Refund");
	 				return false;
	 						 					
	 			}		
	 				 				
	 		}else{	
	 			alert("No Refund Tariff(s) Available");
	 			return false;
	 		}				
	 					
	 					
	 		var hisValidator = new HISValidator("EstEnquiryTransBean");  
			hisValidator.addValidation("strOffLineRefundBy", "dontselect=0", "Please Select a Refund By" );						
			hisValidator.addValidation("strOffLineRefundReasonText", "req", "Refund Reason is a Mandatory Field" );
			hisValidator.addValidation("strOfflineRefundDate", "req", "Refund Date is a Mandatory Field" );
			hisValidator.addValidation("strOfflineAmount", "req", "Amount is Mandatory Field" );
			//hisValidator.addValidation("strOfflineAmount", "numgt=0", "Amount should be Grater than 0.00" );
			
			var res = hisValidator.validate(); 
			hisValidator.clearAllValidations();

			if(! res) return false;
			
			res = checkMultirow('strOfflineTariffName', "Tariff Name Already Exists");			
			
			if(! res) return false;		
					
	 		res = checkMultirow('strOfflinePaymentMode', "Payment Mode Already Exists");	
	 									
	 		if(res){
	 					
	 				return offlineSubmitPart(); 	
	 				
	 		}else{
	 			return false;
	 		}
	 					
	 			 	
		 }
	 
	 	 
	}
	
	
	/**
	 * confirmOk
	 */
	 function confirmOk() {
	 	
	 	
	 //	hide_popup('billPrintConfirm');
	 	
	/*  
	 	var ctReqType = document.forms[0].currentReceiptType.value ;	 	
	 
	  

		var refTot = parseFloat(document.forms[0].strOfflineRefundAmount.value);	
		 			
			var cashAmt = parseFloat("0");
			
			var cashAmtObj = getCashPayObject('0');
						
										
				if( cashAmtObj != null){
					
					cashAmt = parseFloat(cashAmtObj.value);
										
					if(ctReqType == '2'){
						cashAmtObj.value = roundValue(cashAmt - (refTot),  2);
					}else{
						cashAmtObj.value = roundValue(cashAmt + (refTot),  2);
					}
					
					
				}*/
				document.getElementById("saveid").style.display = "none";
					
		 		////code for adding hidden tariff of consumable charges		
	 		 		
	 	/* var consumableCharges=document.getElementsByName("strConsumableCharge")[0].value
	 	  var consumablechargeGroupId=document.forms[0].strConsumableChargesGroupId.value;
	 	var consumablechargeTariffId=document.forms[0].strConsumableChargesTariffCode.value;
	 	
	 	var hiddenfieldvalue=consumablechargeTariffId+"^"+consumablechargeGroupId+"^"+"0"+"^"+consumableCharges+"^"+consumableCharges+"^"+"1701"+"^"+"1"+"^"+"0"+"^"+"0"+"^"+"0"+"^"+consumableCharges+"^"+consumableCharges+"^"+"0"+"^"+"0"+"^"+"each"+"^"+"0"+"^"+consumablechargeTariffId+"^"+"0"+"^"+"0"+"^"+"General Charges";
	 	if(parseInt(consumableCharges)>0)
	 	{
	 	var divInnerHtml="<input type='hidden' name='strOfflineTariffDetailsHidden' value='"+hiddenfieldvalue+"'/>"
	 	divInnerHtml=divInnerHtml+"<input type='hidden' name='strOfflineTariffName' value='Consumable Charges - (Con)'/>"
	 	divInnerHtml=divInnerHtml+"<input type='hidden' name='strOfflineTariffQty' value='1'/>"
	 //	divInnerHtml=divInnerHtml+"<input type='hidden' name='strOfflineTariffDiscount' value='0'/>"
	 //	divInnerHtml=divInnerHtml+"<input type='hidden' name='strOfflineTariffServiceTax' value='0'/>"
	 	divInnerHtml=divInnerHtml+"<input type='hidden' name='strOfflineTariffNetAmount' value='"+consumableCharges+"'/>"
	 	divInnerHtml=divInnerHtml+"<input type='hidden' name='strOfflineTariffUnit' value='1701^1'/>"
	 //	divInnerHtml=divInnerHtml+"<input type='hidden' name='strOfflineTariffServiceTaxAmtVal' value='0'/>"
	 //	divInnerHtml=divInnerHtml+"<input type='hidden' name='strOfflineTariffDiscountAmtVal' value='0'/>"
	 	
	 	
	 	
	 		document.getElementById("consumableChargeDiv").innerHTML=divInnerHtml
	 	}		*/
	 	 
	 document.forms[0].submit();	
	 }
	
	
	/**
	 * confirmCancel
	  
	 */
	 function confirmCancel() {
	 	
	 //	hide_popup('billPrintConfirm');
	 	
	 			 	
	 	 
	 		
	 		
	 		if(document.forms[0].hmode.value == "WITHOUTCRNOREFUND"){
	 			
	 			document.forms[0].strBillNo.disabled = true;
	 		
	 		}
	 		
	 	 		if(document.getElementsByName("strOfflineTariffName").length > 0 && document.getElementsByName("strOfflineTariffName")[0].value.length > 0 ){
	 	 			
	 	 					addRows(new Array('strOfflineTariffName','strOfflineTariffDetailsHidden','strOfflineTempTariffRateUnit','strOfflineTariffRateUnit','strOfflineTariffQty','strOfflineTariffUnit','strOfflineTariffNetAmount','strOfflineTotalActualTariffAmtVal'),new Array('t','t','t','t','t','s','t','t','t','t','t','t'),'2','1','R')
	 	 			
	 	 		}
	 	 		
	 	 	
	 	 		
	 		
	 		if(!document.forms[0].strPatientMode[1].checked)
	 		document.forms[0].strCrNo.disabled= true;
		 					 			
	 						 			
		 			var payDtl = document.getElementsByName("strOfflinePaymentDtls");
		 			var payMode = document.getElementsByName("strOfflinePaymentMode");
		 			
		 			var len = payDtl.length - 1;
							
					for(var index=0; index<len; index++) {
					
						if(payMode[index].value != 1)
						payDtl[index].disabled = true;
					
					}
		 		 	 	
	 	
	 	return false;
	 }
	
	   
	
	
	/**
	 * offlineSubmitPart
	 * @param {type}  
	 */
	 function offlineSubmitPart() {
	 
	 
	 			var ctReqType = document.forms[0].currentReceiptType.value ;	 			
	 
				 if(ctReqType == '1'){
	 				
	 				calcTotalRecAmount();
	 				
	 			}
	 
	 			if(validateTotalPaymentAmt('0')){
	 			
	 				var refTot = parseFloat(document.forms[0].strOfflineRefundAmount.value);
	 		
	 		 		
	 			if((refTot <= 0 && ctReqType == '1') || (refTot >= 0 && ctReqType == '2')){	 			
		 				
				document.forms[0].strCrNo.disabled= false;
		 					 			
	 						 			
		 			var payDtl = document.getElementsByName("strOfflinePaymentDtls");
		 			var payMode = document.getElementsByName("strOfflinePaymentMode");
		 			
		 			var len = payDtl.length - 1;
							
					for(var index=0; index<len; index++) {
					
						payDtl[index].disabled = false;
						payMode[index].disabled = false;
					}
		 			
		 		
		 	//	popup('billPrintConfirm' , '250','250');
		 			
		 	//		confirmLogic();
		 			
		 		//	return false;
		 		
		 			 if(confirm("Are You Sure to Save it?")){
		 			 	
		 			 	confirmOk();
		 			 	return false;
		 			 	
		 			 }else{
		 			 	
		 			 	confirmCancel();
		 			 	return false;
		 			 	
		 			 }
				 	
	 			}
	 			
	 			}
				 	
				 /*	
				 	document.forms[0].strCrNo.disabled= true;
		 					 			
	 						 			
		 			var payDtl = document.getElementsByName("strOfflinePaymentDtls");
		 			var payMode = document.getElementsByName("strOfflinePaymentMode");
		 			
		 			var len = payDtl.length - 1;
							
					for(var index=0; index<len; index++) {
						
						if(payMode[index].value != 1)
						payDtl[index].disabled = true;
						
					}
				 	
				 	
	  				return false;
	  			 }
		 			
		 			
			var cashAmt = parseFloat("0");
			
			var cashAmtObj = getCashPayObject('0');
			
				if( cashAmtObj != null){
					
					cashAmt = parseFloat(cashAmtObj.value);
					
					if(ctReqType == '2'){
						cashAmtObj.value = roundValue(cashAmt - (refTot),  2);
					}else{
						cashAmtObj.value = roundValue(cashAmt + (refTot),  2);
					}
					
					
				}

						document.forms[0].strOffLineRaisingDepartment.disabled = false;
		 			document.forms[0].strOffLineRefundReasonText.disabled = false;	
					document.forms[0].submit();
		 			
	 			}else{
	 				
	 				alert("Net Payment Amount cannot be Less than Net Payable Amount");
	 				return false;
	 			}
	 			
	 			}else{
	 				
	 				return false;
	 			}
*/
	 	
	 }
	
	
	 function withoutCrNoSubmitPart() {
	 
	 			
	 			/*var ctReqType = document.forms[0].currentReceiptType.value ;
	 			
 			
	 			if(ctReqType == '1' & document.getElementsByName("strTariffNetAmt").length == 0){
	 				
	 					 				
	 				calcTotalRecAmount();
	 				
	 			}
	 				 			
	 
	 			if(validateTotalPaymentAmt('0')){
	 			
	 				 			
	 				var refTot = parseFloat(document.forms[0].strOfflineRefundAmount.value);
	 		
	 		 		
	 			if((refTot <= 0 && ctReqType == '1') || (refTot >= 0 && ctReqType == '2')){	 			
		 							 			
	 						 			
		 			var payDtl = document.getElementsByName("strOfflinePaymentDtls");
		 			var payMode = document.getElementsByName("strOfflinePaymentMode");
		 			
		 					 			
		 			var len = payDtl.length - 1;
							
					for(var index=0; index<len; index++) {
					
						payDtl[index].disabled = false;
						payMode[index].disabled = false;
					}
		 			*/
		 					 			
		 			 	//	popup('billPrintConfirm' , '250','250');
		 			
		 	//		confirmLogic();
		 			
		 		//	return false;
		 		
		 			 if(confirm("Are You Sure to Save it?")){
		 			 	
		 			 	confirmOk();
		 			 	return false;
		 			 	
		 			 }else{
		 			 	
		 			 	confirmCancel();
		 			 	return false;
		 			 	
		 			 }
				 	
	 			//}
	 			
	 			//}
				 	
					/* 	
					 	
					 		var payDtl = document.getElementsByName("strOfflinePaymentDtls");
		 			var payMode = document.getElementsByName("strOfflinePaymentMode");
		 			
		 			var len = payDtl.length - 1;
							
					for(var index=0; index<len; index++) {
					
					if(payMode[index].value != 1)
						payDtl[index].disabled = true;
					
					}
		 			
					 	
					 	
	  					return false;
	  			 	 }
		 			
		 			
		 			
			var cashAmt = parseFloat("0");
			
			var cashAmtObj = getCashPayObject('0');
			
				if( cashAmtObj != null){
					
					cashAmt = parseFloat(cashAmtObj.value);
					
					if(ctReqType == '2'){
						cashAmtObj.value = roundValue(cashAmt - (refTot),  2);
					}else{
						cashAmtObj.value = roundValue(cashAmt + (refTot),  2);
					}
					
					
				}
					

		 			document.forms[0].strOffLineRefundReasonText.disabled = false;	
					document.forms[0].strBillNo.disabled = false;

						

					 document.forms[0].submit();
		 			
	 			}else{
	 				
	 				alert("Net Payment Amount cannot be Less than Net Payable Amount");
	 				return false;
	 			}
	 			
	 			}else{
	 				
	 				return false;
	 			}
*/
	 	
	 }
	 
	 
	 /**
	  * confirmLogic
	  * @param {}  
	  */
	/*  function confirmLogic() {
	  	
	  	if(document.forms[0].strConfirmationType.value == '0'){
	  		
	  		document.forms[0].billConfirmCancel.focus();
	  		
	  	}else{
	  		
	  		document.forms[0].billConfirmOk.focus();
	  		
	  	}
	  	
	  }
	 */
	
	/**
	 * saveWithouCrNoDtls 
	 */
	 function saveWithouCrNoDtls() {
		 showCltDetails('otherDtlsDivId');
	 	
	    if(document.forms[0].strOffLineRequestType[document.forms[0].strOffLineRequestType.selectedIndex].value == 1 && document.forms[0].strIsPreviousCrNoReq.value == '1' )
	 	if(document.forms[0].strPreviousCrNo.value.toUpperCase() != document.forms[0].strTempPreviousCrNo.value.toUpperCase()){
	 	
	 		alert("Previous CR. No. Changed, Please Click the Go Button to get Previous CR. No. Details");
	 		return false;
	 	}
	 
	 	 
	 	
	 	if(document.forms[0].strWithoutCrNoOnlineRequestNo!= null && document.forms[0].strWithoutCrNoOnlineRequestNo.value != 0 ){
	 			
	 			var tariffs = document.getElementsByName("strTariffDetailsId");
	 			
	 			if(tariffs.length > 0){
	 				
	 				var count = parseInt("0");
	 				
	 				for(var i=0; i<tariffs.length; i++) {
	 					
	 					if(tariffs[i].checked){
	 						count = count + 1;
	 					}	 					
	 				}
	 				
	 			if(count < 1){
	 					
	 					alert("Please Select at least One Tariff");
	 					return false;
	 				}		
	 				
	 			var hisValidator = new HISValidator("EstEnquiryTransBean");  
	 			hisValidator.addValidation("strOffLineRequestType", "dontselect=0", "Please Select Service Type" );
	 			hisValidator.addValidation("strOffLineBillingService", "dontselect=0", "Please Select Billing Service" );
				hisValidator.addValidation("strTariffBilledQty", "req", "Billed Quantity is Mandatory Field" );
				hisValidator.addValidation("strBillTariffUnit", "dontselect=0", "Please Select a Tariff Unit" );
				hisValidator.addValidation("strOfflineAmount", "req", "Amount is Mandatory Field" );
				//hisValidator.addValidation("strOfflineAmount", "numgt=0", "Amount must be grater than 0.00" );
	 				
	 			
	 				var res = hisValidator.validate(); 
				hisValidator.clearAllValidations();
		 		
		 		// WITHOUTCRNORECEIPT
		 	
		 
		 	 	
		 		if(! res) return false;
			
			
					
			
			
				document.forms[0].hmode.value = "ONLINEWITHOUTCRNORECEIPT";	
			
				withoutCrNoSubmitPart();
	 				
	 				
	 			}else{
	 				
	 				alert("Tariff(s) Not Available");
	 				return false;
	 			}
	 			
	 			
	 	}else{
	 	
		 	var ctBSerId = document.forms[0].currentBserviceId.value ;
		 	var ctReqType = document.forms[0].currentReceiptType.value ;
		 	
		 	
		 	if(ctBSerId == '1' && ctReqType == '1'){
		 		
		 		
		 		
		 		if(document.forms[0].strIsDetailsReceived.value == 0 && document.forms[0].strIsPreviousCrNoReq.value == '1'){
			 			 		 			
			 			alert("Please Click the Go button for Previous CR No. Details");
			 			return false;
			 			
			 		}
		 		
		 		
		 		  	 		 	 		 		 
	 		 		  deleteUnwantedRows("strOfflineTariffName" , '2' );
	 		 		 
	 		 		 		
		 		
		 		var hisValidator = new HISValidator("EstEnquiryTransBean");  
		 		
				//hisValidator.addValidation("strOffLineRaisingDepartment", "dontselect=0", "Please Select a Raising Department" );
				
				if(document.forms[0].strIsPreviousCrNoReq.value == '1'){
				
					hisValidator.addValidation("strPreviousCrNo", "req", "Previous CR No. is a Mandatory Field" );	
							
				}
				
				hisValidator.addValidation("strOffLineRequestType", "dontselect=0", "Please Select Service Type" );
	 			hisValidator.addValidation("strOffLineBillingService", "dontselect=0", "Please Select Billing Service" );
				hisValidator.addValidation("strGuarantorName", "req", "Patient Name is a Mandatory Field" );
				//hisValidator.addValidation("strGuarantorContactNo", "req", "Patient Contact No. is a Mandatory Field" );
				//hisValidator.addValidation("strGuartorAddress", "req", "Patient Address is a Mandatory Field" );
				hisValidator.addValidation("strGuartorAddress", "maxlen=250", "Patient Address cannot be more than 250 Characters" );
				//alert(document.forms[0].strAge.value);
				//if(document.forms[0].strIsRefPhysicanReq.value  == '1'){
				
				//hisValidator.addValidation("strReferringPhysician", "req", "Referring Physician is a Mandatory Field" );
				
				//}
				
				hisValidator.addValidation("strAge", "req", "Age is a Mandatory Field" );
				hisValidator.addValidation("strAge", "numgt=0", "Age cannot be zero" );
				hisValidator.addValidation("strAge", "numltet=150", "Age cannot be grater than 150" );
				hisValidator.addValidation("strAgeUnit", "dontselect=0", "Please Select an Age Unit" );
				hisValidator.addValidation("strGender", "dontselect=0", "Please Select a Gender" );
				
				hisValidator.addValidation("strOfflineTariffName", "req", "Tariff Name is Mandatory Field" );		
				hisValidator.addValidation("strOfflineTempTariffRateUnit", "req", "Tariff Rate/Unit is Mandatory Field" );				
				hisValidator.addValidation("strOfflineTariffQty", "req", "Tariff Quantity is Mandatory Field" );
				hisValidator.addValidation("strOfflineTariffUnit", "dontselect=0", "Please Select a Tariff Unit" );
				//hisValidator.addValidation("strOfflineAmount", "req", "Amount is Mandatory Field" );
				
				var res = hisValidator.validate(); 
				hisValidator.clearAllValidations();
		 		
		 		// WITHOUTCRNORECEIPT
		 	
		 
		 	
		 
		 	 	
		 		if(! res){
		 			
		 			if(document.getElementsByName("strOfflineTariffName").length >= 2 && document.getElementsByName("strOfflineTariffName")[0].value.length > 0)
		 				generateRows();
		 			
		 			 return false;
		 		}
				document.forms[0].hmode.value = "WITHOUTCRNORECEIPT";	
			
				
						document.forms[0].strPreviousCrNo.disabled = false;		       					       
			       		document.forms[0].strGuarantorName.disabled = false;	
			       		document.forms[0].strAge.disabled = false;	
			       		document.forms[0].strAgeUnit.disabled = false;	
			       		document.forms[0].strGender.disabled = false;	
			       		document.forms[0].strGuartorAddress.disabled = false;	
			       		document.forms[0].strGuarantorContactNo.disabled = false;	
			       		document.forms[0].strReferringPhysician.disabled = false;	
			       		document.forms[0].strReferringPhysicianContactNo.disabled = false;			       					       	
					//	document.forms[0].strOffLineRaisingDepartment.disabled = false;
			
				withoutCrNoSubmitPart();
					 	 		
		 	}else{
		 			 		
		 		
				var hisValidator = new HISValidator("EstEnquiryTransBean");						
				hisValidator.addValidation("strBillNo", "req", "Receipt No. is a Mandatory Field" );
				hisValidator.addValidation("strOffLineRefundBy", "dontselect=0", "Please Select a Refund By" );						
				hisValidator.addValidation("strOffLineRefundReasonText", "req", "Refund Reason is a Mandatory Field" );
			
				
				var res = hisValidator.validate(); 
				hisValidator.clearAllValidations();
		 		
		 		
		 		if(! res) return false;
				
				var chkObj = document.getElementsByName("strBillTariffVal")
					
				if(chkObj.length > 0){
					
					var count = parseInt("0");
					
					for(var i = 0, len = chkObj.length ; i < len ; i ++){
			
						if(chkObj[i].checked){
							
							count = count + 1;				
						}
					}
					
					if(count == 0){
						
						alert("Please Select Atleast One Tariff to Refund");
						return false;
					}
					
					
				}else{
					
					alert("No Tariff(s) Available");
					return false;
				}		
						
				
					document.forms[0].hmode.value = "WITHOUTCRNOREFUND";	
		 	
		 		document.forms[0].strBillNo.disabled = false;
		 		document.forms[0].strOffLineRefundReasonText.disabled = false;
		 	
		 			withoutCrNoSubmitPart();
				
				/*
						
		 		res = checkMultirow('strOfflinePaymentMode', "Payment Mode Already Exists");	
		 									
		 		if(res){
		 						
		 			var refTot = parseFloat(document.forms[0].strOfflineRefundAmount.value);
		 		
		 			if(refTot <= 0){	 			
			 								 			
			 			var payDtl = document.getElementsByName("strOfflinePaymentDtls");
			 			var payMode = document.getElementsByName("strOfflinePaymentMode");
			 			
			 			var len = payDtl.length - 1;
															
						for(var index=0; index<len; index++) {
						
							payDtl[index].disabled = false;
							payMode[index].disabled = false;
						}
			 						 			
						document.forms[0].strBillNo.disabled = false;
						document.forms[0].strOffLineRefundReasonText.disabled = false;
			 						 			
			 			document.forms[0].hmode.value = "WITHOUTCRNOREFUND";			 			
			 			document.forms[0].submit();
			 			
		 			}else{
		 				
		 				alert("Net Payment Amount cannot be Less than Net Payable Amount");
		 				return false;
		 			}
		 						
		 		}else{
		 			return false;
		 		}
		 		
		 		*/
		 	}
	 	
	 	}
	 	
	 }
	
	
	/**
	 * ageValidation 
	 */
	 function ageValidation() {
	 	
	 var ageObj 	= document.forms[0].strAge;
	 var ageUnitObj = document.forms[0].strAgeUnit[document.forms[0].strAgeUnit.selectedIndex]; 
	 	
	 var ageTemp = ageObj.value;	
	 	
	 	if(ageObj.value == '') ageTemp = "0";
	 	
	 	
	 var age = parseInt(ageTemp);	
	 	
	 	if(ageUnitObj.value == '3' && age > 31){
	 		
	 		alert("Age cannot be "+age+" Days");
	 		ageObj.value = 0;
	 		return false;
	 		
	 	}
	 	
	 	if(ageUnitObj.value == '2' && age > 12){
	 		
	 		alert("Age cannot be "+age+" Months");
	 		ageObj.value = 0;
	 		return false;
	 		
	 	}
	 	
	 	
	 }
	
	
	/**
	 * isAddRowRequired
	 * @param {String} fieldName 
	 */
	 function isAddRowRequired(fieldName) {
	 	
	 	var fieldObject = document.getElementsByName(fieldName);
	 	
	 	var res = true;
	 	var count = parseInt("0");
	 	
	 	for(var i=0; i<fieldObject.length; i++) {
	 		
	 		if(fieldObject[i].value.length == 0 || fieldObject[i].value == ""){
	 			
	 				count = count + 1;
	 		}else{
	 			
	 			if(count > 0){
	 				
	 				count = 0;
	 				
	 			}
	 			
	 		}
	 		
	 	}
	 	 
	 	
	 	if(count > 1) res = false;
	 	
	 	
	 	return res;
	 	
	 }	
	
	
	/**
	 * deleteUnwantedRows
	 * @param {String} fieldName 
	 * @param {String} layerIndex
	 */
	 function deleteUnwantedRows(fieldName , layerIndex) {
	 	
	 		var fieldObjectVal = document.getElementsByName(fieldName);
	 	
	 		var len = fieldObjectVal.length - 1;
	 	
	 		for(var k=0; k< len ; k++) {
	 		
	 		  		 
	 			if(fieldObjectVal[k].value.length == 0  ){
	 				 		 	 			
	 				var indexVal = fieldObjectVal[k].id.split('-')[1];
	 		 	 
	 				 deleteRow("2-"+indexVal,layerIndex,'1');
	 			  
	 					 len = len - 1;
	 					 k = k-1;
	 			 
	 			}
	 		
	 	}
	 	
	 }
	 
	 
	 function getServiceDetails(obj)
	 {
		 
			var hmode = "FETCHSERVICELIST"; 
		  	
		  	var url = "EstEnquiryTransCNT.cnt?hmode="+hmode+"&serviceType="+obj.value;
					
				ajaxFunction2(url,"2","getBillListDetailAjaxResponse"); 
		 
	 }
	
	 function printLastBill()
	 {
	 	//alert("show");
	 	//document.getElementById("printableSlip").style.display="";
	 	//alert(document.forms[0].filePath.value);
	 	if(document.forms[0].filePath.value=="")
	 	{
	 		alert("No Bill Generated Yet.");
	 		return;
	 	}
	 	else
	 	window.print();
	 }