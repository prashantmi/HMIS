
	// Global Vaiables
	
	var gblPayDtls = "";
	var gblPayEdit = "";
	var gblAmount = "";  
	var gblPayMode = "";
	var savedTariff="";
	    
	/**
	 * setOnlineOffLineMode
	 * @param {Object} checkObj 
	 */
	 function setOnlineOffLineMode(checkObj) {
	 	
	 	document.forms[0].strCrNo.value = "";
	 	
	 	if(!checkObj.checked){
	 		 	 		
	 		document.forms[0].hmode.value="CHANGEMODE";
	 		
	 		document.forms[0].currentState.value = "1";
	 		document.forms[0].strIsOnline.value = "1";
	 		
	 	} 
	 	
	 		document.forms[0].submit();
	 	
	 }
	
	
	/**
	 * selectPatientMode
	 * @param {Object} obj - Radio Button Object. 
	 */
	 function selectPatientMode(obj) {
	 	
	 	if(obj.value != '1'){
		 	 
	 		document.forms[0].hmode.value="WITHOUTCRNO";
	 	}
	 	
	 	document.forms[0].submit();
	 }
	
	
	/**
	 * showDetails - shows the given divId by showing the minus image and hiding the plus image also hides the oppdivId by showing the plus image and hiding the minus image
	 * @param {object} divId - div id which should be shown.
	 *  @param {object} oppdivId - div id which should be hide.
	 */
	function showDetails(divId,oppdivId){
		
		
		document.forms[0].currentState.value = '1';
		
			
		document.getElementById(divId).style.display="block";
		document.getElementById(oppdivId).style.display="none";
		
		document.getElementById('minus'+divId).style.display="block";
		document.getElementById('plus'+divId).style.display="none";
		
		document.getElementById('minus'+oppdivId).style.display="none";
		document.getElementById('plus'+oppdivId).style.display="block";
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
	 * hideDetails - hide the given divId by showing the plus image and hiding the minus image also shows the given oppdivId by showing the minus image and hiding the plus image.
	 * @param {object} divId - div id which should be shown.
	 *  @param {object} oppdivId - div id which should be hide.
	 */
	function hideDetails(divId,oppdivId){
		
		
		document.forms[0].currentState.value = '2';
						
		document.getElementById(divId).style.display="none";
		document.getElementById(oppdivId).style.display="block";
		
		document.getElementById('minus'+divId).style.display="none";
		document.getElementById('plus'+divId).style.display="block";
		
		document.getElementById('minus'+oppdivId).style.display="block";
		document.getElementById('plus'+oppdivId).style.display="none";
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
	 * wardDisplayStatus - display the Ward Combo when the param obj value is 2
	 * @param {object} obj - combo option Object.
	 */
	function wardDisplayStatus(obj){
		
		if(obj.value == '2'){
			
			document.getElementById("wardDivId").style.display="block";
		}else{
			document.getElementById("wardDivId").style.display="none";
		}
		
	}

	/**
	 * goFunc - validates the Cr Number Text Box and submit the page with hmode 'GO' if validation succeeds 
	 */
	function goFunc(){
	
	if(document.forms[0].strCrNo.disabled == false){
		var hisValidator = new HISValidator("cashCollectionOnlineTransBean");  
	
		hisValidator.addValidation("strCrNo", "req", "Cr No. is a Mandatory Field" );
		hisValidator.addValidation("strCrNo", "minlen="+document.forms[0].strCrNo.maxLength, "Cr No. must be "+document.forms[0].strCrNo.maxLength+" Digits" );
	
		var retVal = hisValidator.validate(); 
		hisValidator.clearAllValidations();
		
			if(retVal){
				document.forms[0].hmode.value="GO";
				document.forms[0].submit();
				
			}else{
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
	   		
	   		if(eve.keyCode == 13)
	   		{									
				return goFunc();
			}	   	
	   }else{
	   		return false;
	   }
	  
	 }
	
	/**
	 * clearFunc - clears the Cr Number Text Box and submit the page with hmode 'CANCEL'. 
	 */
	function clearFunc(cntMode){
		
		document.forms[0].strCrNo.value = "";
		document.forms[0].strCounterMode.value = cntMode;
		document.forms[0].strCrNo.disabled = false;
		document.forms[0].searchPatient.disabled = false;
		document.forms[0].hmode.value="unspecified";
		document.forms[0].submit();
	} 
	
	
	/**
	 * cancelFunc -to forward page to init page and  submit the page with hmode 'CANCELPAGE'. 
	 */
	function cancelFunc1(){
		
		document.forms[0].hmode.value="CANCELPAGE";
		document.forms[0].submit();
	}

	/**
	 * onLoadLogics - function invokes at the time of body load. 
	 * 				  	 
	 */
	function onLoadLogics()
	{
		//alert(document.getElementById("walacc"));
		
		addRows(new Array('strOnlinePaymentMode','strOnlinePaymentDtls','strOnlineAmount'),new Array('s','t','t'),'1','1','I');
		
		var errVal = document.getElementById("errMsg").innerHTML;	
				
		if(errVal.length > 1 && errVal.search("CR.") == -1)
		{
			document.forms[0].strCrNo.disabled = true;
			document.forms[0].strIsOnline.disabled = true;
			document.forms[0].strPatientMode[0].disabled = true;
			document.forms[0].strPatientMode[1].disabled = true;
		}
		else if(errVal.search("CR.") != -1)
		{
			document.forms[0].strCrNo.disabled = false;
		}
		if(document.forms[0].currentState.value == '1')
		{
			document.getElementById("searchIconDivId").style.display = "block";
		}
		else
		{
			document.getElementById("searchIconDivId").style.display = "none";
		}	
		
		var normalMsgVal = document.getElementById("normalMsg").innerHTML;	
			
		if(normalMsgVal.length > 1 && document.forms[0].strCrNo.disabled == false)
		{
			if(normalMsgVal.search(document.forms[0].strTempBillNo.value) != -1)
			{
					//var confirmFlag = confirm("Whether Receipt Printed Successfully?");
					var confirmFlag = true;
					if(!confirmFlag)
					{
						var printLimit = document.forms[0].strPrintMessageLimit.value;
						if(printLimit.length <0) printLimit = "0";
							printLimit = parseInt(printLimit);
						
						if(printLimit > 0)
						{
							gblPrintLimitVal = printLimit ;
							reprintContents();
						}
						else
						{
							var billNo = document.forms[0].strTempBillNo.value;
							var recNo  = document.forms[0].strTempReceiptNo.value;
										
							var hmode = "UPDATEPRINTSTATUS"; 
							var url = "CashCollectionOnlineTransCNT.cnt?hmode="+hmode+"&billNo="+billNo+"&recNo="+recNo;
															
							ajaxFunction(url,"1","updateStatusResponse");
						}				
					}										
					return false;										
			}				
		}		
		
			
		if(document.forms[0].strCrNo.disabled == false)
		{
			document.forms[0].strCrNo.focus();
			if(document.forms[0].currentState.value == '1')
			{
				document.getElementById("searchIconDivId").style.display = "block";
			}
			else
			{
				document.getElementById("searchIconDivId").style.display = "none";
			}
			if(errVal.search("CR.") != -1) return false;			
		}		
		if(document.forms[0].strCrNo.value != '' && document.forms[0].strCrNo.disabled == false)
		{
			if(checkCrdef(document.getElementById("strCrNoId"))==false)//Complete CR No Entered
			{
				document.getElementById("gblDivId").style.display="block";
				document.forms[0].strCrNo.disabled = true;
				document.forms[0].searchPatient.disabled = true;
			
				if(document.forms[0].currentState.value == '1')
				{
					document.getElementById("searchIconDivId").style.display = "block";
					var onLineRadioObj = document.getElementsByName("onLineDataRadio");
					
					if(onLineRadioObj.length > 0)
					{
						var valList = onLineRadioObj[0].value;
						var tempVal = valList.split('^');
						var hmode = "ONLINEDTLS"; 
						var url = "CashCollectionOnlineTransCNT.cnt?hmode="+hmode+"&crNo="+document.forms[0].strCrNo.value+"&chargeTypeId="+tempVal[3]+"&bServiceId="+tempVal[4]+"&reqNo="+tempVal[0]+"&admNo="+tempVal[10]+"&accNo="+tempVal[11]+"&treatCat="+tempVal[14]+"&reqFor="+tempVal[15]+"&reqType="+tempVal[13]+"&billNo="+tempVal[7]+"&grpId="+tempVal[2]+"&catCode="+tempVal[8];
						ajaxFunction(url,"1");
						
						//added by manisha 
						document.forms[0].currentReceiptPatCat.value=tempVal[8];
						document.forms[0].currentReceiptPayMode.value=tempVal[28];
						
						
					}
			}			
		}
		else//only predefined Digits in CR Field
				SetCursorToTextEnd('strCrNoId');
	    }
		document.forms[0].strMobileNo.value=document.getElementsByName("strPatientDtlHidVal")[0].value.split("^")[13];
		if(document.forms[0].strPatientDtlHidVal.value.split('^')[2]=="2")
		{
			if(document.forms[0].strPatientDtlHidVal.value.split('^')[14]!="1")
		    {
				document.getElementById("errMsg").innerHTML = "Patient Not Accepted in Ward, IPD Services Not Applicable";
				document.getElementById("errMsg").style.display = "block";
		    }
		}
	}
		
		
		var gblPrintLimitVal = 0;
	
	
	/**
	 * reprintContents
	 */
	 function reprintContents() {
	 	
	 		 	
	 		if(gblPrintLimitVal != 0){
	 			
	 			var hmode = "REPRINT"; 
				var url = "CashCollectionOnlineTransCNT.cnt?hmode="+hmode;
											
				ajaxFunction2(url,"1","rePrintStatus");	
	 			
	 		}else{
	 			
	 			var billNo = document.forms[0].strTempBillNo.value;
				var recNo  = document.forms[0].strTempReceiptNo.value;
							
				var hmode = "UPDATEPRINTSTATUS"; 
				var url = "CashCollectionOnlineTransCNT.cnt?hmode="+hmode+"&billNo="+billNo+"&recNo="+recNo;
												
				ajaxFunction2(url,"1","updateStatusResponse");
	 			
	 		}
	 		
	 		
	 	
	 }		
				
	
	/**
	 * rePrintStatus 
	 */
	 function rePrintStatus() 
	 {
	 	gblPrintLimitVal = gblPrintLimitVal - 1;
	 	//var confirmFlag = confirm("Whether Receipt Printed Successfully?");
		var confirmFlag = true;
		if(!confirmFlag)
		{
			reprintContents();
		}	 	
	 }			
				
		
	
	var gblOnLineHService = "0";
	var gblReqType = "0";
	var gblBServiceId = "0";
	function setClientDetails(valObj,mode)
	{		count=0;
			var valList = valObj.value;
			var tempVal = valList.split('^');
			
		//	alert(tempVal);
			//changePayMode();
			if(mode == '1')
			{
				document.forms[0].currentBserviceId.value = tempVal[4];
				document.forms[0].currentReceiptType.value = tempVal[13];
								
				gblOnLineHService = tempVal[3];
				gblReqType = tempVal[13];
				gblBServiceId = tempVal[4];
				document.forms[0].currentState.value = '1';
	 			document.forms[0].currentBserviceId.value  = gblBServiceId;
	 			document.forms[0].currentReceiptType.value = gblReqType ;
	 		
	 			var hmode = "ONLINEDTLS"; 
				var url = "CashCollectionOnlineTransCNT.cnt?hmode="+hmode+"&crNo="+document.forms[0].strCrNo.value+"&chargeTypeId="+tempVal[3]+"&bServiceId="+tempVal[4]+"&reqNo="+tempVal[0]+"&admNo="+tempVal[10]+"&accNo="+tempVal[11]+"&treatCat="+tempVal[14]+"&reqFor="+tempVal[15]+"&reqType="+tempVal[13]+"&billNo="+tempVal[7]+"&grpId="+tempVal[2]+"&catCode="+tempVal[8];
				ajaxFunction(url,"1");
				
			
				//added by manisha 
				document.forms[0].currentReceiptPatCat.value=tempVal[8];
				document.forms[0].currentReceiptPayMode.value=tempVal[28];
				
				
				
			} 
			
	}
	
	/* Added by manisha for getting payment mode from Patient Category-Payment mode mapping master */
	function getPaymentModeByPatCat()
	{
		var requestType=document.forms[0].currentReceiptType.value;
		var recieptPatCategory=document.forms[0].currentReceiptPatCat.value;
		var patReceiptPayMode=document.forms[0].currentReceiptPayMode.value;
		
		
		var hmode = "AJX_PAYMENTMODEBYPATCAT"; 
		var url="";
		if(requestType=='2')  // receipt type= refund
			url = "CashCollectionOfflineTransCNT.cnt?hmode="+hmode+"&hService=0&requestType="+requestType+"&recieptPatCategory="+recieptPatCategory+"&patReceiptPayMode="+ patReceiptPayMode;
						
		else
			url = "CashCollectionOfflineTransCNT.cnt?hmode="+hmode+"&hService=0&requestType="+requestType+"&patCategory="+recieptPatCategory;
		
		
		ajaxFunction(url,"32");
		 
		
		
		
	}
	
	function setDefaultPaymentMode()
    {
		
		  var defaultPaymentMode = "0";
    	if(document.forms[0].strOnlinePaymentMode!=undefined)
		{
			
			var paymentMode=document.getElementsByName("strOnlinePaymentMode")[0];
			var paymentModeValue=document.getElementsByName("strOnlinePaymentMode")[0].value;
			
									
				for(i=0; i<paymentMode.options.length;i++)
					{
					
					//if(paymentMode.options[i].value.split('#')[3]!="0")
					//	{
							if(paymentMode.options[i].value.split('#')[0]== paymentMode.options[0].value.split('#')[3])
							{	defaultPaymentMode=paymentMode.options[i].value;
								break;
							}
					//	}
						    			
					}
		}
		//$("#strOfflinePaymentMode").val(defaultPaymentMode);
		document.getElementsByName("strOnlinePaymentMode")[0].value=defaultPaymentMode;
		//alert(document.getElementsByName("strOfflinePaymentMode")[0].value);
    }
	
	/*End by manisha*/
	
	function getBillService(){
				
	 document.forms[0].currentReceiptType.value = document.forms[0].strOffLineRequestType[document.forms[0].strOffLineRequestType.selectedIndex].value;
	 document.forms[0].currentBserviceId.value = document.forms[0].strOffLineBillingService[document.forms[0].strOffLineBillingService.selectedIndex].value;
	 
	 
	 	if(document.forms[0].currentReceiptType.value == '2'){
	 		
	 		document.getElementById("offlineDepEpdTreatWardDivId").style.display = "none";	 		
	 		
	 	}else{
	 		document.getElementById("offlineDepEpdTreatWardDivId").style.display = "block";
	 		
	 	}
	 
	 
			var hmode = "BILLDTLS"; 
			var url = "CashCollectionOnlineTransCNT.cnt?hmode="+hmode+"&hService="+document.forms[0].strOffLineHospitalService[document.forms[0].strOffLineHospitalService.selectedIndex].value+"&requestType="+document.forms[0].strOffLineRequestType[document.forms[0].strOffLineRequestType.selectedIndex].value;
						
			ajaxFunction(url,"2");
	}
	
			
	
	
	   
	
	 function showReconcilPopup(hidVal,parentPos){
	 		 	
			var hmode = "RECONCILPOPUP"; 
			var url = "CashCollectionOnlineTransCNT.cnt?hmode="+hmode+"&hidVal="+hidVal;
		
		gblPopupParent = parentPos;
			
			ajaxFunction(url,"20");
	}
	  
	
	/**
	 * getOnlineThirdPartyAmtView
	 * 
	 */
	 function getOnlineThirdPartyAmtView() {
	 	
	 	/*if(document.getElementsByName("onLineDataRadio")[0].checked){
	 			
 			var valList = document.getElementsByName("onLineDataRadio")[0].value;
			var tempVal = valList.split('^');
					
			gblOnLineHService = tempVal[3];
			gblReqType = tempVal[13];
			gblBServiceId = tempVal[4];
 			
	 		 			
	 	}*/
	 		
 		var clientDtlStatus = document.getElementsByName("strOnlineClientDetails");
 		var clientStatus = "0";
	 		
 		if(clientDtlStatus.length > 0){
 			clientStatus = "1";
 		}
	 		
	 		/*	
	 				
	 	var hmode = "ONLINETHIRDPARTYAMTDTL"; 
			var url = "CashCollectionOnlineTransCNT.cnt?hmode="+hmode+"&strChargeTypeId="+gblOnLineHService+"&strRequestTypeId="+gblReqType+"&strBServiceId="+gblBServiceId+"&clientStatus="+clientStatus;
								
			ajaxFunction(url,"21"); */
			
						
		var opdThirdPartyBilling = document.forms[0].strOpdThirdPartyBenefit.value;
		var ipdThirdPartyBilling = document.forms[0].strIpdThirdPartyBenefit.value;
		var epdThirdPartyBilling = document.forms[0].strEmergencyThirdPartyBenefit.value;
				
		
		if (gblOnLineHService == '2' && (gblBServiceId == "10" || gblBServiceId == "21" || gblBServiceId == "11") && clientStatus == '1') {
				
				
				var cltDtls = document.forms[0].strOnlineClientDetails.value.split('^')[3];
				
				var cltBalanceAmt = parseFloat(cltDtls);	
				var payAmt = document.forms[0].strOnlineClientDetails.value.split('^')[5];	
				
				payAmt = roundValue(payAmt+"",2);
				
				document.forms[0].strOnlineGrandTotalAmount.value = payAmt;
				document.getElementById("onlineGrandTotalDivId").innerHTML = payAmt;
				
				
				if(payAmt > 0){
					
					if(cltBalanceAmt > payAmt){
						
					payAmt = roundValue(payAmt+"",2);
						
						document.forms[0].strOnlineMaxClientBenefitAmount.value = payAmt;
						document.getElementById("onlineMaxClientBenefitDivId").innerHTML = payAmt;
											
						
						document.forms[0].strOnlinePatNetPayAmount.value = 0;
						document.getElementById("onlinePatNetPayDivId").innerHTML = "0.00";
						
						document.forms[0].strOnlineTotalRecAmount.value = 0;
						document.getElementById("onlineTotalRecAmtDivId").innerHTML = "0.00";
						
					}else{
						
						cltBalanceAmt = roundValue(cltBalanceAmt+"",2);
						
						document.forms[0].strOnlineMaxClientBenefitAmount.value = cltBalanceAmt;
						document.getElementById("onlineMaxClientBenefitDivId").innerHTML = cltBalanceAmt;
						
						var patPayAmt = manipulateValue(payAmt,cltBalanceAmt,1);
						
						patPayAmt = roundValue(patPayAmt+"",2);
						
						document.forms[0].strOnlinePatNetPayAmount.value = patPayAmt;
						document.getElementById("onlinePatNetPayDivId").innerHTML = patPayAmt;
						
						document.forms[0].strOnlineTotalRecAmount.value = patPayAmt;
						document.getElementById("onlineTotalRecAmtDivId").innerHTML = patPayAmt;
						
					}
					
					
					
					
				}else{
					
					document.forms[0].strOnlineMaxClientBenefitAmount.value = 0;
					document.getElementById("onlineMaxClientBenefitDivId").innerHTML = "0.00";
					
					document.forms[0].strOnlinePatNetPayAmount.value = 0;
					document.getElementById("onlinePatNetPayDivId").innerHTML = "0.00";
				}
				
				
				
					document.getElementById("onlineGrandTotalDivPartId").style.display = "block";
					document.getElementById("onlineMaxClientBenefitDivPartId").style.display = "block";
					document.getElementById("onlinePatientPayableDivPartId").style.display = "block";
			
		 }else{
		 	
		 		document.getElementById("onlineGrandTotalDivPartId").style.display = "none";
		 		document.getElementById("onlineMaxClientBenefitDivPartId").style.display = "none";
				document.getElementById("onlinePatientPayableDivPartId").style.display = "none";
		 	
		 }
		 
		/* 
		 if ( gblBServiceId == "21"){
								
					document.getElementById("onlineDiscountAndServiceTaxDivPartId").style.display = "block";				

				}else{
						
					document.getElementById("onlineDiscountAndServiceTaxDivPartId").style.display = "none";
				
				
				}
		 */
	 
	}
	
	 
	 function changePayMode()
	 {
	 		var chkObj = document.getElementsByName("onLineDataRadio");
			if(chkObj.length > 0)
			{
				for(var i=0; i<chkObj.length; i++) 
				{
				  	if(chkObj[i].checked)
				  	{
				  		var temp = chkObj[i].value.split("^");
				  		var x = temp[14];
				  		

							var paymentMode=document.getElementsByName("strOnlinePaymentMode")[0];
							//alert(paymentMode.options.length);
							for(i=0; i<paymentMode.options.length;i++)
							{
								//alert("unmatched");
								if(x==paymentMode.options[i].text)
								{ 
									//alert("matched");
									document.getElementsByName("strOnlinePaymentMode")[0].value=paymentMode.options[i].value;
									break;
								}
								else
								{
								//alert("no mode onLoad");
								
								//document.getElementsByName("strOnlinePaymentMode")[0].value="1";
								for(j=0;j<paymentMode.options.length;j++)
								 { 
								if(paymentMode.options[j].value.split('#')[0]=="1")
									{		
								document.getElementsByName("strOnlinePaymentMode")[0].value=paymentMode.options[j].value;
								break;
									}
							     }
								}
							}			
					}				  		
				  		
				}
			}				
	 }
	 
	 
	 function validatePayMode()
		{
		 var prim_cat_req=document.getElementsByName("onLineDataRadio")[0].value.split("^")[8];
	     var prim_cat=prim_cat_req;

		 
		 if(prim_cat_req==undefined || prim_cat_req ==null || prim_cat_req=="" || prim_cat_req=="0")
			 prim_cat == document.getElementsByName("strCatgoryCode")[0].value; // document.forms[0].strCatgoryCode.value;
		 
		//alert(prim_cat);  
		var pay_mode=document.getElementsByName("strOnlinePaymentMode")[0].value;
		
		var strpaymode=document.getElementsByName("strOnlinePaymentMode")[0].value.split('#');
		
		var paymode_id=strpaymode[0];
		var paymode_name=strpaymode[1];
		var isbound_flag=strpaymode[2];
		var isbound_catcode=strpaymode[3];
		
	//	alert("isbound_flag "+isbound_flag);
		//alert("prim_cat "+prim_cat);
		
		
		if(isbound_flag=="1")
			{
			if(isbound_catcode==prim_cat)
			return true;
			else
				{
			//	alert("Patient Billing Category and Primary Category are different"); 
				//alert("Payment Mode '"+paymode_name+"' cannot be selected for Patient Category '"+document.getElementById("strCategoryName").innerHTML+"'");
				alert("Selected Payment Mode is not applicable for this Patient Billing Category");
				return false;
				}
			}
		else
			return true;
		
		
		}
	 
	function getAjaxResponse(res,mode)
	{			
		//alert(document.getElementsByName("strOnlinePaymentMode")[0].value);
		var err = document.getElementById("errMsg");	  	  
        var temp = res.split("####");   
        if(temp[0] == "ERROR")
	    {
          	err.innerHTML = temp[1];
          	err.style.display = "block";
          	return;
        }       
		if(mode == '1')
		{
		//	getPaymentModeByPatCat();
		//	setDefaultPaymentMode();
			
			 var paymentMode=document.getElementsByName("strOnlinePaymentMode")[0];
			 for(j=0;j<paymentMode.options.length;j++)
				{ 
					if(paymentMode.options[j].value.split('#')[0]=="7")
					{		
				document.getElementsByName("strOnlinePaymentMode")[0].value=paymentMode.options[j].value;  //Reset To WaLLET
				break;
					}
			    }
			 
			 if(paymentMode.value.split("#")[0]!=7)
					getPaymentModeByPatCat();
			
			document.getElementById("onlineRefundAmtDivId").innerHTML = "0.00";
			document.forms[0].strOnlineRefundAmount.value = 0;
			document.getElementById("onlineTotalPayAmtDivId").innerHTML = "0.00";
			document.forms[0].strOnlineTotalPayAmount.value = 0;
			
			if(gblBServiceId.length < 2)
			{
				var valList = document.getElementsByName("onLineDataRadio")[0].value;
				var tempVal = valList.split('^');						
				gblOnLineHService = tempVal[3];
				gblReqType = tempVal[13];
				gblBServiceId = tempVal[4];
	 		}			
						
			document.forms[0].currentState.value = '1';
		 	document.forms[0].currentBserviceId.value  = gblBServiceId;
		 	document.forms[0].currentReceiptType.value = gblReqType ;
			document.forms[0].currentHospService.value = gblOnLineHService;	
								
			
			cltObj = document.getElementById("clientDetailsDivId");
		    cltObj.innerHTML = "";
		    
		    document.forms[0].strOnlineTotalRecAmount.value = 0;
			document.getElementById("onlineTotalRecAmtDivId").innerHTML = "0.00";
		    
			if(res.indexOf("@@") == -1)
			{
				cltObj.innerHTML = res;	
				document.forms[0].strOnlineTotalRecAmount.value = 0;
				document.getElementById("onlineTotalRecAmtDivId").innerHTML = "0.00";
			}
			else
			{
				var temp = res.split("@@");
				cltObj.innerHTML = temp[0];
				document.forms[0].strOnlineTotalRecAmount.value = temp[1];
				document.getElementById("onlineTotalRecAmtDivId").innerHTML = temp[1];
			}
						 
			var partpaymentAmt = document.forms[0].strPartPaymentAmount;
			var advanceAmt = document.forms[0].strAdvanceAmount;
			var finalAdjustmentAmt = document.forms[0].strNetPaybleAmount;
			var tariffDetails 	   = document.getElementsByName("strTariffDetailsId");
			var strReconcileVal = document.getElementsByName("strReconcileHiddenDtls");
			var strTariffDetails = document.getElementsByName("strTariffRefundDetailsId");
			var strRefundAdvance = document.getElementsByName("strRefundAdvancePenelty");
					
			if(strTariffDetails.length > 0)
			{					
				document.getElementById("onlineTotalRecAmtDivId").innerHTML = "0.00";
	 			document.forms[0].strOnlineTotalRecAmount.value = 0;	 
			}
			var CreditPaymentType = document.getElementsByName("strCreditPaymentType");
			
			/*if(CreditPaymentType.length > 0)
			{
				document.getElementById("otherCreditDetailsId").style.display = "block";
			}*/
						
			if(strReconcileVal.length > 0 )
			{
				var reconcileTotal = parseInt("0");
				for(var i=0; i<strReconcileVal.length; i++) 
				{					
					reconcileTotal = reconcileTotal + parseInt(strReconcileVal[0].value);					
				}
				var amt = roundValue(reconcileTotal+"",2);								
				document.getElementById("onlineTotalRecAmtDivId").innerHTML = amt;
	 			document.forms[0].strOnlineTotalRecAmount.value = amt;	 
			}
			if(tariffDetails.length > 0)
			{						
				document.getElementById("onlineTotalRecAmtDivId").innerHTML = "0.00";
	 			document.forms[0].strOnlineTotalRecAmount.value = 0.00;	 
			}
			document.getElementById("onlineNetAmountDetailsDivId").style.display = "none";
			
			if(finalAdjustmentAmt != null && gblBServiceId == '21' &&  gblReqType != '3')
			{
				var amt = roundValue(finalAdjustmentAmt.value,2);
				document.getElementById("onlineTotalRecAmtDivId").innerHTML = amt;
	 			document.forms[0].strOnlineTotalRecAmount.value = amt;
	 			document.getElementById("onlineNetAmountDetailsDivId").style.display = "block";
	 
	 /*
	 			var discAmt 	= document.forms[0].strNetDiscountAmount.value;
	 			 discAmt = roundValue(discAmt,2);
	 			
	 			document.getElementById("onlineDiscountAmountDivId").innerHTML = discAmt;
	 			document.forms[0].strOnlineDiscountAmount.value = discAmt;
	 			
	 			var serTaxAmt 	= document.forms[0].strNetServiceTaxAmount.value; 
	 		 	serTaxAmt = roundValue(serTaxAmt,2);
	 			
	 			document.getElementById("onlineServiceTaxAmountDivId").innerHTML = serTaxAmt;
	 			document.forms[0].strOnlineServiceTaxAmount.value = serTaxAmt;
	 	*/	
	 			
			}
			 if(partpaymentAmt != null && gblBServiceId == '20')
			 {
				var amt = roundValue(partpaymentAmt.value,2);
				document.getElementById("onlineTotalRecAmtDivId").innerHTML = amt;
	 			document.forms[0].strOnlineTotalRecAmount.value = amt;
			 }
			 if(advanceAmt != null && gblBServiceId == '19')
			 {			 			 	
			 	var amt = roundValue(advanceAmt.value,2);
			 	document.getElementById("onlineTotalRecAmtDivId").innerHTML = amt;
	 			document.forms[0].strOnlineTotalRecAmount.value = amt;	 	
			 }
			 if(strRefundAdvance.length > 0 && gblBServiceId == '19' && gblReqType == '2' )
			 {
				var refundAdvanceAmt = document.forms[0].strTariffRefundCost.value;
				//refundAdvanceAmt = -1 * roundValue(refundAdvanceAmt,2);
				document.getElementById("onlineTotalRecAmtDivId").innerHTML = refundAdvanceAmt;
	 			document.forms[0].strOnlineTotalRecAmount.value = refundAdvanceAmt;
			 }
			 
			 if(gblOnLineHService!= '2' && document.forms[0].strWalletNo.value!=null && document.forms[0].strWalletNo.value!="" )//If Not IPD and Wallet No Present
			 {
				 if(parseInt(document.forms[0].strAvlWalletMoney.value)>0 )//Avalable Wallet Money Present
				 {
					 if(parseInt(document.forms[0].strAvlWalletMoney.value)>parseInt(document.forms[0].strOnlineTotalRecAmount.value))
					 {
						
						 // document.getElementsByName("strOnlinePaymentMode")[0].value="7";//Reset To WaLLET
						 var paymentMode=document.getElementsByName("strOnlinePaymentMode")[0];
						 for(j=0;j<paymentMode.options.length;j++)
						 { 
						if(paymentMode.options[j].value.split('#')[0]=="7") //Reset To WaLLET
							{		
						document.getElementsByName("strOnlinePaymentMode")[0].value=paymentMode.options[j].value;
						break;
							}
					     }
						 
						 
						 document.getElementsByName("strOnlinePaymentDtls")[0].value="Wallet No.: "+document.forms[0].strWalletNoMasked.value;
						 document.getElementsByName("imgPatientWallet")[0].style.display = "";
						 document.getElementsByName("imgPatientWallet")[1].style.display = "";
					 }
				 }
			 }
			 else				 
				 resetMultiRow('1');
			 addRows(new Array('strOnlinePaymentMode','strOnlinePaymentDtls','strOnlineAmount'),new Array('s','t','t'),'1','1','R');
			 getPaymentModeByPatCat();
				setDefaultPaymentMode();
			// changePayMode(); 
			 //alert("setTotalToAmount 1.0");
			 setTotalToAmount('1');
	 		 setTotalRefundAmount('1');
	  		 checkForOnLinePaymentLimit('1');
	 		 getOnlineThirdPartyAmtView();	
			 if(document.forms[0].stracc.value.split('^')[1]=="2" && document.getElementById("walacc")!=null)
			 {
					document.getElementById("walacc").checked=true;
					document.getElementById("walacc").value="1";
			 }
			 
			 
			 
		}
		
		if(mode == '2'){
								
			billObj = document.getElementById("billServiceDivId");
			
			billObj.innerHTML = "<select class='comboNormal' name='strOffLineBillingService' onchange='getPartAccDtls(this),resetTotalAmount(this);'>"+res+"</select>";
			
			//	getPartAccDtls(document.forms[0].strOffLineBillingService[document.forms[0].strOffLineBillingService.selectedIndex]);		
					
					getEpisodeList(document.forms[0].strOffLineRaisingDepartment[document.forms[0].strOffLineRaisingDepartment.selectedIndex]);
					
		}
		 
		
		
		if(mode=="14"){ 
			
			//alert(res);
			
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
		 
		
		if(mode == '20'){
					
			document.getElementById("reconcilTariffPopupDetailsDivId").innerHTML = res;				
				
				display_popup_menu(gblPopupParent,"reconcilTariffPopupDetailsDivId",'','');
		
		}
		
		
		 if(mode == '32')
		    {
		    	 
				//	alert(res);
					document.getElementsByName("strOnlinePaymentMode")[0].innerHTML =res;
					setDefaultPaymentMode();
					
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
	


	function validateCheckDD(){

var hisValidator = new HISValidator("cashCollectionOnlineTransBean");  
		
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

var hisValidator = new HISValidator("cashCollectionOnlineTransBean");  
	
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

var hisValidator = new HISValidator("cashCollectionOnlineTransBean");  
	
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
	
	var optVal = parent.value.split('#')[0];
	var c=0;
	var creditpay=document.forms[0].strPatientDtlHidVal.value;
	var paytype=document.getElementsByName("strCreditPaymentType");
	var x=creditpay.split("^")[12];
	
		totalVal = parseFloat("0");
	
	if(mode == '0'){
		
		totalVal = document.forms[0].strOnlineTotalRecAmount.value;	  		  	
	  	totalVal = parseFloat(totalVal);
	  	
	}else{
		
		totalVal = document.forms[0].strOfflineTotalRecAmount.value;	  		  	
	  	totalVal = parseFloat(totalVal);
		
	}
	
	if(totalVal < 0){
		
			if(optVal == 4 || optVal == 5 || optVal == 6 || optVal == 8 || optVal == 9){
				
				alert("This Mode is Not Applicable for Refund");
				
				parent.selectedIndex = 0;
				document.getElementById(payAmt).focus(); 
				
				return false;
			}
			if(optVal == 2 || optVal == 3){
				
				
				//	hide_popup('payDtlCDDMenu');
					
					popup('payDtlCDDMenu' , '250','250');
					
					document.forms[0].strPayCDBankName.focus();
					
				}else if(optVal == 4 || optVal == 5 || optVal == 8 || optVal == 9){
				
//					hide_popup('payDtlCDMenu');
					//cardlogic();
					//popup('cardAmtDiv' , '150','450');
					popup('payDtlCDMenu' , '250','250');
					
				document.forms[0].strPayCDDBankName.focus();
				
				}
				else if(optVal == 6)
				{
					popup('payDtlCLTMenu' , '250','250');		
					document.forms[0].strPayCNTClientName.focus();	
				}
				else if(optVal == 7)
				{
						popup('payDtlWalletMenu' , '250','250');
				}
				else
				{	
						gblPayDtls.disabled = false;
						gblAmount.focus();
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
	
	//if(x=="3" || x=="4")
	if(totalVal >= 0){//Not Refund
	for(var i=0;i<paytype.length;i++)
	{
		if(paytype[i].value=="10" || paytype[i].value=="12")
			c++;
	}
	if(c==0 && (x=="3" || x=="4"))
	{   //credit category/ credit payment type(no paid present)
		
		if(optVal == 2 || optVal == 3 || optVal == 4 || optVal == 5 || optVal == 6 || optVal == 7 || optVal == 8 || optVal == 9)
		{
			alert("For Credit Category, No Payment Mode Other Than Cash Is Allowed.");
			gblPayMode.selectedIndex = 0;
	        gblPayDtls.disabled = false;
		}
	}
	else
	{
	
	if(optVal == 2 || optVal == 3){
	
					
	//	hide_popup('payDtlCDDMenu');
		
		popup('payDtlCDDMenu' , '250','250');
		
		document.forms[0].strPayCDBankName.focus();
		
	}else if(optVal == 4 || optVal == 5 || optVal == 8 || optVal == 9){
	
		//hide_popup('payDtlCDMenu');
		//cardlogic();
		//popup('cardAmtDiv' , '150','450');
		popup('payDtlCDMenu' , '250','250');
		
	document.forms[0].strPayCDDBankName.focus();
	
	}
	else if(optVal == 6)
	{
		popup('payDtlCLTMenu' , '250','250');		
		document.forms[0].strPayCNTClientName.focus();	
	}
	else if(optVal == 7)
	{
			popup('payDtlWalletMenu' , '250','250');
	}
	else
	{	
			gblPayDtls.disabled = false;
			gblAmount.focus();
	}
	}
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
					
			}else if(payModeVal == 4 || payModeVal == 5 || payModeVal == 8 || payModeVal == 9){
				
				document.forms[0].strPayCDBankName.value = temp[0];
				document.forms[0].strCardNo.value = temp[1];
				document.forms[0].strAuthNo.value = temp[2];
				document.forms[0].strAuthDate.value = temp[3];
				if(temp[4].length == 4){
				document.forms[0].strCardType.selectedIndex = 2;
				}else if(temp[4].length == 5){
				document.forms[0].strCardType.selectedIndex = 3;
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

	function hidePayDetails(divId)
	{
		hide_popup(divId);		
		if(gblPayEdit != "Edit")
		{
			document.forms[0].strChequeDDDate.value = document.forms[0].strTempCtDate.value;
			document.forms[0].strAuthDate.value = document.forms[0].strTempCtDate.value;
			document.forms[0].strClientAuthDate.value = document.forms[0].strTempCtDate.value;
		
			gblPayMode.selectedIndex = 0;
			gblPayDtls.disabled = false;
			//gblAmount.focus();
		}
		
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


var gblTariffDiscountDtlsId = "";

	function hidePkgDtls(index){
		
		
			document.forms[0].strPkgServiceFlag.value = 0;
				pkgDivObj = document.getElementById("pkgConfigId"+index);							
				pkgDivObj.style.display="none";
	}

	 
	 /**
	  * calcOnlineTariffNetAmount
	  * @param {String} tariffDtls 
	  */
	  function calcOnlineTariffNetAmount(tariffDtls , index) 
	  {
		  //tariffDtls= 1240001^1*1^1701^20.00^1701^124^20.00^0^0.00^1^1001^0^No ^1^0^20.00^0^0^0^^0^/^0^/^1^0
		  //TariffId"^"ReqQty"*"QtyBaseVal"^"QtyUnitId "^"TariffRate"^"RateUnitId"^"GroupId"^"ActualRate"^"ServiceTax"^"DisountUnit"^"DiscountType"^"GblTariffId \
		  //"^"ApprovalId  "^"  UnitName  "^"RateBaseVal  "^"  IsPackage  "^"NetCost  "^"  Penelty  "^"  "0^"ReceiptNo  "^"  ApprovalDtl"^"ServiceId"^"ConsumableCharge;
	
		  
		  	var temp = tariffDtls.split('^');
		  	var creditType=document.getElementById("strCreditPaymentType"+index);
		  	var creditAppType=document.getElementsByName("strCreditApprovalRequired")[0].value;
		  	
		  	//alert(creditType.value);
		  	if(creditType.value=="11" || creditType.value=="13")//Credit or Credit Urgent
		  		var rate = 0;
		  	else
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
	 	
	 	 	///add consumabale charges
		 	var groupid =temp[5];
		 	var groupConsumableCharge =temp[25];
		 	//addConsumableCharge(groupid,groupConsumableCharge)
	 	
	 		if(netAmt < 0)
	 		{
	 			alert("Net Amount Value in Negative, Plese Check the Discount Amount");
	 			document.getElementById("strOnlineTariffDiscount"+index).value = 0;
				document.getElementById("strOnlineTariffDiscountDivId"+index).innerHTML = "0";
				var temp1 =  document.getElementById("strOnlineTariffDiscountConfigDetails"+index).value.split(",");
				document.getElementById("strOnlineTariffDiscountConfigDetails"+index).value = "0,1,"+temp1[2]+","+temp1[3]+","+temp1[4]+","+temp1[5];
	 			return false;
	 		}
	 		else
	 		{
		 		document.getElementById("strTariffServiceTaxAmt"+index).value = calAmt.oSerAmt;
				document.getElementById("strTariffDiscountAmt"+index).value = calAmt.oDisAmt;
				document.getElementById("strTariffDiscountAmtDivId"+index).innerHTML = calAmt.oDisAmt;
		 		document.getElementById("strTariffActualAmt"+index).value = calAmt.oActTrfAmt;
		 		document.getElementById("strTariffNetAmt"+index).value = Math.round(netAmt);
		 		document.getElementById("strTariffNetAmtDivId"+index).innerHTML = Math.round(netAmt);	 			
	 		}	 	
	 		
	 		
	 		if((creditType.value=="11" || creditType.value=="13") && creditAppType=="0")//Credit & Approval Not Required
	 		{
	 			/*var strCreditClientNo=0;
	 			var strCreditClientName=0;
	 			var objc=document.getElementsByName("onLineDataRadio");
	 			for(var x=0;x<objc.length;x++)
	 			{
	 				if(objc[x].checked)
	 				{
	 					var z=objc[x].value;
	 					
	 					strCreditClientNo=z.split('^')[25]; 
	 					strCreditClientName=z.split('^')[26]; 
	 				}
	 			}*/
	 			
	 			document.getElementById("strPaidByPat"+index).value = 0;
	 			document.getElementById("strPaidByClient"+index).value = (temp[3]*(temp[1].split("*")[0]));
	 			document.getElementById("strCreditBillFlag"+index).value =1;
	 			//document.getElementById("strCreditClientNo"+index).value =strCreditClientNo;
	 			//document.getElementById("strCreditBillStatus"+index).value =2;//1-Approved,0-Pending,2-Direct(No Approval)
	 			//document.getElementById("strCreditClientName"+index).value =strCreditClientName;	 			
	 		}
	 		else
	 		{
	 			
	 			document.getElementById("strPaidByPat"+index).value = (temp[3]*(temp[1].split("*")[0]));
	 			document.getElementById("strPaidByClient"+index).value = 0;
	 			document.getElementById("strCreditBillFlag"+index).value =0;
	 			//document.getElementById("strCreditClientNo"+index).value =0;
	 			//document.getElementById("strCreditBillStatus"+index).value =0;//1-Approved,0-Pending,2-Direct(No Approval)
	 			//document.getElementById("strCreditClientName"+index).value ="";	 	
	 		}
	 		
	 		calcOnlineTotalRecAmount();
	  }
	 
	 
	  function calcOnlineWithoutCrNoTariffNetAmount(tariffDtls , index) 
	  {	  	
	  		  	
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
	 		 
	 		document.getElementById("strTariffNetAmt"+index).value = Math.round(netAmt);
	 		document.getElementById("strTariffNetAmtDivId"+index).innerHTML = Math.round(netAmt);
	 			
	 		}	 		 		 	
			 		 		 		 	
			
	 	
	 		calcOnlineWithoutCrNoTotalRecAmount();	
	  	
	  	
	  }
	 
  function calcOnlineTotalRecAmount() 
  {
  	/* Calculate On Basis of selected Tariffs*/
  	
  	var grandTotal = calAllTariffNetCost("strTariffNetAmt");
 		 	
 	document.getElementById("onlineGrandTotalDivId").innerHTML = grandTotal;
 	document.forms[0].strOnlineGrandTotalAmount.value = grandTotal;
 	
 	
 	if(document.getElementsByName("strTariffServiceTaxAmt").length > 0)
 	{
 	 	var grandServiceTax = calAllTariffNetCost("strTariffServiceTaxAmt");
	 	document.forms[0].strTotalTariffServiceTaxAmount.value = grandServiceTax;
		
		var grandDiscountVal = calAllTariffNetCost("strTariffDiscountAmt"); 
	 	document.forms[0].strTotalTariffDiscountAmount.value = grandDiscountVal;
	 	
	 	var grandActualTariffVal = calAllTariffNetCost("strTariffActualAmt");
	 	document.forms[0].strTotalTariffActualAmount.value = grandActualTariffVal; 	
 	}
 	
 	var clientBalAmtObj 	= document.getElementsByName("strOnlineClientDetails");
 	var grandTotalAmt		= document.forms[0].strOnlineGrandTotalAmount;//total bill 
 	var patPayableAmtObj 	= document.forms[0].strOnlinePatNetPayAmount;
 	var patMaxBenefitAmtObj = document.forms[0].strOnlineMaxClientBenefitAmount;
 	
 	var patMaxBenObj = document.getElementById("onlineMaxClientBenefitDivId");
 	var patPayAmtObj = "0";
 	
 	if(clientBalAmtObj.length > 0)//If Client Details Found
 	{
 		clientBalAmt = parseFloat(clientBalAmtObj[0].value.split('^')[3]);//approved advance
 		patPayAmtObj = parseFloat(clientBalAmtObj[0].value.split('^')[5]);
 	}
 	else
 	{
 		clientBalAmt = parseFloat("0");
 	}
 		
 		grandTotalAmt 		= parseFloat(grandTotalAmt.value);
 		
 		//alert("clientBalAmtObj"+clientBalAmt);
	 	//alert("patPayableAmtObj"+patPayAmtObj);
	 	//alert("grandTotal"+grandTotalAmt);
	 	
 		var cltAmtDtls = calClientAmount(clientBalAmt,patPayAmtObj,grandTotalAmt);
 		
 		document.forms[0].strOnlineMaxClientBenefitAmount.value = Math.round(cltAmtDtls.oClientAmt);
 		document.getElementById("onlineMaxClientBenefitDivId").innerHTML = Math.round(cltAmtDtls.oClientAmt);
 		
 		var creditTypeObj=document.getElementsByName("strCreditPaymentType");
 		if(creditTypeObj.length>0) 
 		{
 			calClientAmountForCredit();
 		}
 		//alert(document.getElementsByName("strOnlineAmount")[0].value);	 		
 		document.getElementsByName("strOnlineAmount")[0].value = Math.round(cltAmtDtls.oPatAmt);
 		//alert(document.getElementsByName("strOnlineAmount")[0].value);
 			 			 			
 		document.forms[0].strOnlinePatNetPayAmount.value = Math.round(cltAmtDtls.oPatAmt);
 		document.getElementById("onlinePatNetPayDivId").innerHTML = Math.round(cltAmtDtls.oPatAmt) ;	 		
 			 			 			 		
 			 		
 		document.forms[0].strOnlineTotalRecAmount.value = Math.round(cltAmtDtls.oPatAmt);
 		document.getElementById("onlineTotalRecAmtDivId").innerHTML = Math.round(cltAmtDtls.oPatAmt) ;
 		//alert("setTotalToAmount 1.1");
 		setTotalToAmount('1'); 
 		setTotalRefundAmount('1');
 	
 	/*
 	var ctBSerId = document.forms[0].currentBserviceId.value ;
	var ctReqType = document.forms[0].currentReceiptType.value ;
 	if (ctReqType=='1'
					&& ((ctBSerId=='10')
							|| (ctBSerId=='11') ||
							 (ctBSerId==12)))
 	{*/
 			 	
 	///adding consumable to all cahrges
	 
	 var consumableCharge=document.getElementsByName("strConsumableCharge")[0].value	
	 //var totalCharge=document.getElementsByName("strOfflineTotalPayAmount")[0].value
	
	 document.getElementsByName("strOfflineTotalPayAmountWithoutConsumable")[0].value=Math.round(cltAmtDtls.oPatAmt);
	 
	 var totalCharge=roundValue(parseFloat(cltAmtDtls.oPatAmt)+parseFloat(consumableCharge),2)
	 
	 if(document.getElementById("onlineTotalRecAmtDivId") != null)
	 {
		 document.forms[0].strOnlineTotalRecAmount.value=Math.round(totalCharge);
		 document.getElementById("onlineTotalRecAmtDivId").innerHTML = "<a style='cursor: pointer;' title='Charge Details'><font color='red'><u>"+Math.round(totalCharge)+"</u></font></a>";
	 }	
	 if(document.getElementById("onlinePatNetPayDivId") != null)
	 {
		 document.forms[0].strOnlinePatNetPayAmount.value=Math.round(totalCharge);
		 document.getElementById("onlinePatNetPayDivId").innerHTML = Math.round(totalCharge);
	 }	 
	 if(document.getElementById("onlineTotalPayAmtDivId") != null)
	 {
		 document.forms[0].strOnlineTotalPayAmount.value=Math.round(totalCharge);
		 document.getElementById("onlineTotalPayAmtDivId").innerHTML = Math.round(totalCharge);
	 }	 
	 //alert("2:::"+document.getElementsByName("strOnlineAmount")[0].value);
	 document.getElementsByName("strOnlineAmount")[0].value=Math.round(totalCharge);
	 //alert("2.1:::"+document.getElementsByName("strOnlineAmount")[0].value);
 	//}
 	
	checkForOnLinePaymentLimit('1');	
 }
  
function calClientAmountForCredit()
{
	 var creditTypeObj=document.getElementsByName("strCreditPaymentType");
	 var chkObj=document.getElementsByName("strTariffDetailsId");
	 
	 var clientAmt=parseFloat("0");
	 
	 for(var x=0;x<chkObj.length;x++)
	 {
		 if(chkObj[x].checked && (creditTypeObj[x].value=="11" || creditTypeObj[x].value=="13"))//Credit or Credit Urgent
		 {
			 clientAmt=clientAmt+parseFloat(document.getElementsByName("strPaidByClient")[x].value);			 	
		 }
	 }
	 //alert(clientAmt);
	 if(Math.round(clientAmt)>0)
		 document.getElementById("onlineMaxClientBenefitDivPartId").style.display="block";
	 document.forms[0].strOnlineMaxClientBenefitAmount.value =Math.round(clientAmt);
	 document.getElementById("onlineMaxClientBenefitDivId").innerHTML = Math.round(clientAmt);
	  
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
	 
	 document.getElementsByName("strOfflineAmount")[0].value = grandTotal;
	 
	 document.forms[0].strOfflineTotalPayAmount.value = grandTotal;
					document.getElementById("offlineTotalPayAmtDivId").innerHTML = grandTotal;
	 		
					//alert("setTotalToAmount 1.2");
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
	 		 
	 		document.getElementById("strTariffNetAmt"+index).value = Math.round(netAmt);
	 		document.getElementById("strTariffNetAmtDivId"+index).innerHTML = Math.round(netAmt);
	 			
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
	 		
	 		//alert("setTotalToAmount 1.2");
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
	  * setOnlineTariffDetails
	  * @param {Object} chkObj 
	  * @param {String} index
	  */
	  function setOnlineTariffDetails(chkObj , index) 
	  {
		    var chkObjVal=chkObj.value;//strTariffId + "^" + strReqQty + "*"+ strQtyBaseVal + "^" + strQtyUnitId + "^"+ strTariffRate + "^" + strRateUnitId + "^"+ strGroupId + "^" + strActualRate + "^"+ strServiceTax + "^" + strDisountUnit + "^"+ strDiscountType + "^" + strGblTariffId + "^"+ strApprovalId + "^" + strUnitName + "^"+ strRateBaseVal + "^" + strIsPackage + "^"+ strNetCost + "^" + strPenelty + "^" + "0^"+ strReceiptNo + "^" + strApprovalDtl+"^"+strServiceId+"^"+strConsumableCharge;
			var reqQty=chkObjVal.split('^')[1].split('*')[0];
		//	getPaymentModeByPatCat();
		//	setDefaultPaymentMode();
		//	alert(chkObjVal);
			
			if(chkObj.checked == true)
			{
	  			
	  			document.getElementById("strTariffBilledQty"+index).disabled = false;
	  		    //document.getElementById("strTariffBilledQty"+index).value = 1;
	  			document.getElementById("strTariffBilledQty"+index).value = reqQty;	  			
	  			document.getElementById("strBillTariffUnit"+index).disabled = false;
	  			//document.getElementById("strOnlineTariffDiscountConfigDetails"+index).disabled = false;
	  			document.getElementById("strTariffServiceTaxAmt"+index).disabled = false;
	  			document.getElementById("strTariffActualAmt"+index).disabled = false;
	  			document.getElementById("strTariffDiscountAmt"+index).disabled = false;
	  			document.getElementById("strTariffNetAmt"+index).disabled = false;
	  			document.getElementById("strCreditPaymentType"+index).disabled = false;
	  			var objCredit=document.getElementById("strCreditPaymentType"+index);
	  			if(objCredit.value=="11" || objCredit.value=="13")//Credit or Credit Urgent
	  			{
	  				document.getElementById("strCreditLetterNoId"+index).disabled = false;
	  				//document.getElementById("strPaidByPat"+index).disabled = true;
	  				document.getElementById("strPaidByPat"+index).disabled = false;
	  				document.getElementById("strPaidByClient"+index).disabled = false;
		  			//document.getElementById("strCreditLetterDate"+index).disabled = false;
	  			}	  			
	  			else
	  			{
	  				document.getElementById("strCreditLetterNoId"+index).value = "0";
	  				//document.getElementById("strCreditLetterDate"+index).value = "";
	  				document.getElementById("strCreditLetterNoId"+index).disabled = true;
	  				document.getElementById("strPaidByPat"+index).disabled = false;
	  				document.getElementById("strPaidByClient"+index).disabled = false;
	  				//document.getElementById("strPaidByClient"+index).disabled = true;
		  			//document.getElementById("strCreditLetterDate"+index).disabled = true;
	  			}
	  				
	  		
	  			calcOnlineTariffNetAmount(chkObj.value , index);	  		
	  	}
	  	else
	  	{	  		
	  		document.getElementById("strTariffBilledQty"+index).value = 0;
	  		document.getElementById("strTariffBilledQty"+index).disabled = true;
	  		//document.getElementById("strBillTariffUnit"+index).selectedIndex = 0;
	  		document.getElementById("strBillTariffUnit"+index).disabled = true;	  		
	  		document.getElementById("strTariffNetAmt"+index).value = 0;
	  		document.getElementById("strTariffNetAmt"+index).disabled = true;
	  		document.getElementById("strPaidByPat"+index).disabled = true;
			document.getElementById("strPaidByClient"+index).disabled = true;
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
	  	//	document.getElementById("strCreditPaymentType"+index).value = "11";
	  		document.getElementById("strCreditPaymentType"+index).disabled = true;
	  		document.getElementById("strCreditLetterNoId"+index).value = "0";
	  		document.getElementById("strCreditLetterNoId"+index).disabled = true;
	  		//document.getElementById("strCreditLetterDate"+index).value = "";
  			//document.getElementById("strCreditLetterDate"+index).disabled = true;
	  		
	  		////removing consumable charges
	  		var temp = chkObj.value.split('^');
	 		var groupid =temp[5];
	 		var groupConsumableCharge =temp[25];
	 		
	 		//removeConsumableAmount(groupid,groupConsumableCharge)	  		
	  		
	  		calcOnlineTotalRecAmount();	  		
	  	}
	  	
	  }
	
	 
	 /**
	  * setOnlineTariffDltsOnQtyChange
	  * @param {Object} txtObj
	  * @param {String} trfDtls 
	  * @param {String} index
	  */
	  function setOnlineTariffDltsOnQtyChange(txtObj , tariffDtls , index) 
	  {
	  	var reqQty =  parseInt(document.getElementById("strTariffReqQty"+index).value);
	  	
	  	var qty = "0";
	  	if(txtObj.value.length > 0)
	  		qty = txtObj.value;
	  	  	
	  	var billQty = parseInt(qty);  	
	  		  	
	  	if(reqQty >= billQty)
	  	{	  	
	  		calcOnlineTariffNetAmount(tariffDtls , index);
	  	}
	  	else
	  	{
	  		alert("Billed Quantity Cannot be Greater than Required Quantity");	  		
	  		txtObj.value = 0;	  	
	  		calcOnlineTariffNetAmount(tariffDtls , index);
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
	  	
	  	var val = document.forms[0].strOffLineTariffDiscountReason[document.forms[0].strOffLineTariffDiscountReason.selectedIndex].value;
	  	var content = document.forms[0].strOffLineTariffDiscountReason[document.forms[0].strOffLineTariffDiscountReason.selectedIndex].text;
	  		  	
	  	if(val != '0'){
	  	document.forms[0].strOffLineTariffDiscountReasonText.disabled = true;
	  	document.forms[0].strOffLineTariffDiscountReasonText.value = content;
	  	
	  	}else{
	  		document.forms[0].strOffLineTariffDiscountReasonText.value = "";
	  		document.forms[0].strOffLineTariffDiscountReasonText.disabled = false;
	  	}
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
	  //alert("setTotalToAmount 1.3");
			setTotalToAmount('0');
	  		setTotalRefundAmount('0');
	  
	 	 		checkForOffLinePaymentLimit('1');
	 	
	 }
	 
	
	 	
	  function setTotalPaymentAmt(mode){
	 
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
	 
	 
	 /**
	  * getCashAmount
	  * @param {String} mode 
	  */
	  function getCashPayObject(mode) {
	  	
	  
	  		  		
	  		var payTot = document.getElementsByName("strOnlineAmount");
	 		var payMode = document.getElementsByName("strOnlinePaymentMode");	 
	 	 
	 	 	 var cashObj = null; 	
	 	 
	 	 	 var len = payTot.length - 1 ;	
	 	 		 	 	 		 		 
		 		for(var i = 0; i < len ; i++){
		 
		 			var payModeVal = payMode[i][payMode[i].selectedIndex].value.split('#')[0];		 			 	
		 			if(payModeVal == '1')		 	
		 				cashObj = payTot[i];
		 		}
		 	 	 
		 			
		return cashObj;  
	  		
	  
	  }
	 
	 
	  function validateTotalPaymentAmt(mode){
	 			 		
			var ctReqType = document.forms[0].currentReceiptType.value ;	 			 		
	 		
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
		 	
		 	var payModeVal = payMode[i][payMode[i].selectedIndex].value.split('#')[0];
		 			 	
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
	 
	 
	 /**
	  * setTotalToAmount
	  * @param {String} mode 
	  */
	  function setTotalToAmount(mode) {
	  	
	    	var payModeTemp = document.getElementsByName("strOnlinePaymentMode")[0].value;
		var payModeDtlTemp = document.getElementsByName("strOnlinePaymentDtls")[0].value;	  		  	
	  	var payModeDisable = document.getElementsByName("strOnlinePaymentDtls")[0].disabled;
	
	  		
	  		resetMultiRow('1');	
	  	
	  		addRows(new Array('strOnlinePaymentMode','strOnlinePaymentDtls','strOnlineAmount'),new Array('s','t','t'),'1','1','R');
	  		
	  		document.getElementsByName("strOnlinePaymentMode")[0].value = payModeTemp;
	  		document.getElementsByName("strOnlinePaymentDtls")[0].value = payModeDtlTemp;
	  		document.getElementsByName("strOnlinePaymentDtls")[0].disabled = payModeDisable ; 
	  		document.getElementsByName("strOnlineAmount")[0].value = "";
	  		
	  		var totRecAmt = parseFloat(document.forms[0].strOnlineTotalRecAmount.value);
	  	
	  		totRecAmt = roundValue(totRecAmt,2);
	  	
	  	if(parseFloat(totRecAmt) < 0){
		  		
		  		totRecAmt = -1 * parseFloat(totRecAmt);
		  		totRecAmt = roundValue(totRecAmt,2); 
		  		
		  	}
	  //alert("2.2:::"+document.getElementsByName("strOnlineAmount")[0].value);
	  		document.getElementsByName("strOnlineAmount")[0].value = Math.round(totRecAmt);
	  		//alert("2.3:::"+document.getElementsByName("strOnlineAmount")[0].value);
	  	
	  	document.forms[0].strOnlineTotalPayAmount.value = Math.round(totRecAmt);
	  	document.getElementById("onlineTotalPayAmtDivId").innerHTML = Math.round(totRecAmt);
	  	 	  
	
	  	
	  }
	 
	 
	 
	 /**
	  * setTotalRefundAmount
	  * @param {String} mode  0 - Off-line 
	  * 					  1 - Online
	  */
	  function setTotalRefundAmount(mode) {
	  		  	  		
			 	  		
	  		  	  		
	  	
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
     var url="CashCollectionOnlineTransCNT.cnt?hmode="+mode+"&modName="+modeVal;
     
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
			var url = "CashCollectionOnlineTransCNT.cnt?hmode="+hmode+"&hidValue="+hidVal;

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
			var url = "CashCollectionOnlineTransCNT.cnt?hmode="+hmode+"&unitId="+unitId+"&baseValue="+baseValue+"&rowIndex="+index;
			
			ajaxFunction(url,"16");
		 }
	 
	 /**
	  * activateTariffRefundDtls
	  * @param {Object} obj 
	  * @param {String} index 
	  */
	  function activateTariffRefundDtls(obj,index) {
	  	
		  getPaymentModeByPatCat();
		//	setDefaultPaymentMode();
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
	  var gblTotalPeneltyAmt=0;
	  var count=0;
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
	  	
	  	var strTotalPeneltyAmt=0;
	  	var grandPenaltyTotal    =   calAllTariffNetCost("strTariffPenaltyAmt");
	  	
	  	
		  var chkObj = document.getElementsByName("onLineDataRadio");
		  if(chkObj.length > 0){
			 for(var i=0; i<chkObj.length; i++) {
			  	if(chkObj[i].checked){
			  		var penlatyamt= document.getElementById("strProcessPenelty"+i).value;
			  		//alert(count);
			  		if(parseInt(penlatyamt)>0)
			  			{
			  		gblTotalPeneltyAmt=	parseInt(penlatyamt)+parseInt(grandPenaltyTotal);
			  			}else
			  				{
			  				gblTotalPeneltyAmt=0;
			  				}
			  		count=count+1;
			  	}
		  	}
		  }
			//alert(gblTotalPeneltyAmt);
			//alert(grandPenaltyTotal);
			document.forms[0].strTotalTariffPenaltyAmount.value = grandPenaltyTotal+gblTotalPeneltyAmt;
	  	//alert("2.4:::"+document.getElementsByName("strOnlineAmount")[0].value);
	  	document.getElementsByName("strOnlineAmount")[0].value = Math.round(grandTotal); 		
	  	//alert("2.5:::"+document.getElementsByName("strOnlineAmount")[0].value);
	 			 		  	 			 			 			
	 		document.forms[0].strOnlinePatNetPayAmount.value = Math.round(grandTotal);
	 		document.getElementById("onlinePatNetPayDivId").innerHTML = Math.round(grandTotal) ;	 		
	 		
	  	
	  	
	  	document.getElementById("onlineTotalRecAmtDivId").innerHTML = Math.round(grandTotal)+gblTotalPeneltyAmt;
	  	document.forms[0].strOnlineTotalRecAmount.value = Math.round(grandTotal)+gblTotalPeneltyAmt; 
	  	//alert("setTotalToAmount 1.4");
	  	setTotalToAmount('1');
	  	
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
	  			
	  			if(obj[i].value.split('#')[0] == '1')
					obj2[i].disabled = false;	  				  			
	  		}
	  		
	  	
	  	}else{
	  		
	  		if(mode == '0')
	  			addRows(new Array('strOfflinePaymentMode','strOfflinePaymentDtls','strOfflineAmount'),new Array('s','t','t'),'3','1','R');
	  	
	  		
	  		var obj = document.getElementsByName("strOfflinePaymentMode");
	  		var obj2 = document.getElementsByName("strOfflinePaymentDtls");
	  		  		
	  		for(var i = 0; i < obj.length  ; i++){
	  			
	  			if(obj[i].value.split('#')[0] == '4' || obj[i].value.split('#')[0] == '5' ){
	  				obj[i].disabled = true;
	  				
	  			}else{
	  				
	  				obj[i].disabled = false;
	  			}
	  			
	  			if(obj[i].value.split('#')[0] == '1')
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
	  		
	  		//alert(obj);
	  		for(var i = 0; i < obj.length  ; i++){
	  			
	  			obj[i].disabled = false;
	  			
	  			if(obj[i].value.split('#')[0] == '1')
					obj2[i].disabled = false; 			
	  		}
	  		
	  	
	  	}else{
	  		
	  		if(mode == '0')
	  			addRows(new Array('strOnlinePaymentMode','strOnlinePaymentDtls','strOnlineAmount'),new Array('s','t','t'),'1','1','R');
	  		
	  		
	  		var obj = document.getElementsByName("strOnlinePaymentMode");
	  		var obj2 = document.getElementsByName("strOnlinePaymentDtls");
	  		
	  		for(var i = 0; i < obj.length  ; i++){
	  			
	  			if(obj[i].value.split('#')[0] == '4' || obj[i].value.split('#')[0] == '5' ){
	  				obj[i].disabled = true;
	  				
	  			}else{
	  				
	  				obj[i].disabled = false;
	  			}
	  			
	  			if(obj[i].value.split('#')[0] == '1')
					obj2[i].disabled = false;
	  			
	  		}
	  		
	  	}
	  	if(document.forms[0].strOnlinePaymentMode.value.split("#")[0] !="7")
	  		getPaymentModeByPatCat();
	  	
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
		 		
		 			addRows(new Array('strOfflineTariffName','strOfflineTariffDetailsHidden','strOfflineTempTariffRateUnit','strOfflineTariffRateUnit','strOfflineTariffQty','strOfflineTariffUnit','strOfflineTariffServiceTax', 'strOfflineTariffServiceTaxAmtVal','strOfflineTariffDiscount','strOfflineTariffDiscountAmtVal' ,'strOfflineTariffNetAmount','strOfflineTotalActualTariffAmtVal'),new Array('t','t','t','t','t','s','t','t','t','t','t','t'),'2','1','R')
		 			
	  		
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
	  	
		//alert(strCltAmt);
	  	document.getElementById("finalAdjustExpenseAmount").innerHTML = strExpAmt;
	 	document.getElementById("finalAdjustClientAmount").innerHTML = strCltAmt;
	 	document.getElementById("finalAdjustDiscountAmount").innerHTML = strDisAmt;
	 	document.getElementById("finalAdjustServiceAmount").innerHTML = strSerAmt;
	 	
	 	display_popup_menu( parentObj , divId , "250" , "");
	  	
	  	
	  	}else{
	  		
	  		alert("No Details Available");
	  		
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
					
				var myWindow = window.open(createFHashAjaxQuery('CashCollectionOnlineTransCNT.cnt?hmode='+hmode+"&usrFuncName="+userJsFuncName,'popupWindow',featuresList)); 
		
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
  		
  		
  	var hmode = "FETCHBILLLISTING"; 
  	
  	var url = "CashCollectionOnlineTransCNT.cnt?hmode="+hmode+"&searchString="+convString+"&fromRow="+fromRows+"&rowPerPage="+gblRowPerPage+"&ctBlockSet="+blockSet;
			
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
	 				
	 				 		
	 				var hisValidator = new HISValidator("cashCollectionOnlineTransBean");  
	
					hisValidator.addValidation("strSearchString", "req", "Please Enter Guartor Name" );
						
					var retVal = hisValidator.validate(); 
					hisValidator.clearAllValidations();
		
					if(retVal){
						fetchBillList('1','1');
	 				}else{
						return false;
					}
	 		
	 		 return false;
		 }
		 
		
	 }
	 
	 
	
	 function getSearchBillListBySearch() {
	 	
	 	var hisValidator = new HISValidator("cashCollectionOnlineTransBean");  
	
					hisValidator.addValidation("strSearchString", "req", "Please Enter Patient Name" );
						
					var retVal = hisValidator.validate(); 
					hisValidator.clearAllValidations();
		
					if(retVal){
						fetchBillList('1','1');
	 				}else{
						return false;
					}
	 	
	 		 return false;
	 		
	 	} 
	 
	
	/**
	 * findServiceandSave
	 */
	 function findBillServices() 
	 {	 
		 if(!showAlert())
	 		return false;
	 		 
	 	var ctBSerId = document.forms[0].currentBserviceId.value ;
		var ctReqType = document.forms[0].currentReceiptType.value ;
	 			
 		// Online Receipt Part Payment Mode
 		if(ctBSerId == '20' && ctReqType == '1')	 			
 			document.forms[0].hmode.value = "ONRECPARTPAY";
 		
 		// Online Receipt Advance Mode
 		if(ctBSerId == '19' && ctReqType == '1')
 			document.forms[0].hmode.value = "ONRECADV";
 			
 		
 		if((ctBSerId == '10' || ctBSerId == '11' || ctBSerId == '12' || ctBSerId == '25') && ctReqType == '1')
 			document.forms[0].hmode.value = "ONRECSERVICE";
 			

		if((ctBSerId == '10' || ctBSerId == '11' || ctBSerId == '12' || ctBSerId == '25') && ctReqType == '2')
 			document.forms[0].hmode.value = "ONREFUNDSERVICE";	 			
 			
 		if(ctBSerId == '19' && ctReqType == '2' )
 			document.forms[0].hmode.value = "ONREFUNDADVANCE";	 			
 			
 		if(ctBSerId == '21' && ctReqType == '1' )
 			document.forms[0].hmode.value = "ONFINALADJUST";	 			
 				
 		
 		if(ctReqType == '3' )
 			document.forms[0].hmode.value = "ONRECONCILE";	 	
	 				
	 	 
	 	
	 	return validateAndSubmit(document.forms[0].hmode.value);
	 	
	 } 
	
	/**
	 * validateAndSubmit
	 */
	
	 function validateAndSubmit(hmodeVal) 
	 {
			if(document.getElementById("errMsg").innerHTML.length > 0 && document.getElementById("errMsg").style.display == 'block')
			{	
				alert("Cannot Save due to Error:"+document.getElementById("errMsg").innerHTML);
				return false;	 		 		
			}	
			//added by manisha for category bound 
			/*if(!validatePayMode())
				return false;*/  //commented by manisha
			
			
			 	
	 	// Online Receipt Part Payment and Online Receipt Advance
	 	if(hmodeVal == "ONRECPARTPAY" || hmodeVal == "ONRECADV")
	 	{
	 		document.forms[0].strAdvanceAmount.value=Math.round(document.forms[0].strAdvanceAmount.value);
	 		calcNetAmount();
	 		var objCredit=document.getElementById("strCreditPaymentType");
	 		//alert(document.forms[0].stracc.value);
	 		if(hmodeVal == "ONRECADV")
	 		{
	 			if(document.forms[0].stracc.value.split('^')[0]!="0")
	 			{
	 				alert("Account Already Opened/Advance Collected. Multiple Accounts cannot be opened");
	 				return false;
	 			}
	 		}
	 		//alert("objCredit.value"+objCredit.value);
	 		if(objCredit.value=="11" || objCredit.value=="13")//Credit or Credit Urgent
	 		{
	 			//if(document.getElementsByName("strCreditLetterNo")[0].value=="" || document.getElementsByName("strCreditLetterDate")[0].value=="")
	 			if(document.getElementsByName("strCreditLetterNo")[0].value=="0")
	 			{
	 				alert("Please Enter Credit Letter Details");
	 				return false;
	 			}
	 			if(document.getElementsByName("strCreditLetterNo")[0].value.split("^")[16]=="3")
				{
					alert("Seized Letter can't be used");
					return false;
				}
	 			document.forms[0].strAdvanceAmount.value=document.getElementsByName("strCreditLetterNo")[0].value.split("^")[8];
	 			alert("Credit Letter Limit is "+document.getElementsByName("strCreditLetterNo")[0].value.split("^")[8]+". The Advance Amount is updated to "+document.getElementsByName("strCreditLetterNo")[0].value.split("^")[8]);
		 		calcNetAmount();
	 			
	 			/*if(document.getElementsByName("strCreditLetterDate")[0].value=="" || document.getElementsByName("strCreditLetterDate")[0].value=="")
	 			{
	 				alert("Please Enter Credit Date Details");
	 				return false;
	 			}	*/ 			
	 			/*if(document.getElementsByName("strCreditLetterDate")[0].value != "")
				{
					//var datediffVar=dateDiff(document.getElementsByName("strCreditRefDate1")[i].value,document.forms[0].strTempCtDate.value).split('days')[0];
					var retVal=compareDate(document.getElementsByName("strCreditLetterDate")[0].value,document.forms[0].strTempCtDate.value);
					if(retVal.mode==2)
					{
						  alert("Credit Letter Date Can't Be Greater Than Current Date");
						  return false;
					}
					
					var datediffVar=dateDiff(document.getElementsByName("strCreditLetterDate")[0].value,document.forms[0].strTempCtDate.value);
					if(datediffVar!=undefined)
						datediffVar=parseInt(datediffVar.split('days')[0]);
					
					if(datediffVar>parseInt(document.forms[0].strCreditLetterValidity.value))
					{
					  alert("Credit Letter Date Can't Be Old More Than "+document.forms[0].strCreditLetterValidity.value+" Days");
					  return false;
					}
				} 
	 			if(document.forms[0].strPayClientName.value=="0")
	 			{
	 				alert("Please Select Client Name");
	 				return false;
	 			}*/
	 		}	  			
	 		else
	 		{
	 			document.getElementsByName("strCreditLetterNo")[0].value = "0";
	 			//document.getElementsByName("strCreditLetterDate")[0].value = "";
	 			document.getElementsByName("strCreditLetterNo")[0].disabled = true;
	 			//document.getElementsByName("strCreditLetterDate")[0].readOnly = true;
	 		}	 		
	 		
	 		var res = true;
	 			 	
	 		res = checkMultirow('strOnlinePaymentMode', "Payment Mode Already Exists");	
	 		
	 		if(res){
	 		
	 			var hisValidator = new HISValidator("cashCollectionOnlineTransBean");  
				
				hisValidator.addValidation("strOnlineAmount", "req", "Amount is Mandatory Field" );
				//hisValidator.addValidation("strOnlineAmount", "numgt=0", "Amount should be Greater than 0.00" );
				
				var res = hisValidator.validate(); 
				hisValidator.clearAllValidations();
	 		
		 		if(res){
		 		
		 		
		 	 		onlineSubmitPart();
		 			
		 		}else{
		 			
		 			return false;
		 		}
	 		
	 		}
	 	}
	 	
	 	// Opd, Ipd, Emergency Receipt Services for On-line
	 	if(hmodeVal == "ONRECSERVICE")
	 	{	 		
	 		var res = true;	 		
	 		var tariffs = document.getElementsByName("strTariffDetailsId");
	 		var payType = document.getElementsByName("strCreditPaymentType");
	 		var creditLetter = document.getElementsByName("strCreditLetterNo");
	 		//var creditLetterDate = document.getElementsByName("strCreditLetterDate");
            //var cltamt = document.forms[0].strOnlineMaxClientBenefitAmount.value;
	 		
	 		/*alert("tariffs.length=="+tariffs.length)
	 		alert("creditLetter.length=="+creditLetter.length)*/
           
	 	
	 		if(tariffs.length > 0)
	 		{
	 			var count = parseInt("0");
	 			var creditCount = parseInt("0");
	 			for(var i=0; i<tariffs.length; i++) 
	 			{	 				
	 				if(tariffs[i].checked)
	 				{
	 					count = count + 1;
	 				if(payType[i].value=="11" || payType[i].value=="13")//Credit or Credit Urgent
	 				{
	 					if(creditLetter[i].value=="0")//Credit
		 				{
	 						creditCount=creditCount + 1;
		 				}
	 					/*if(creditLetterDate[i].value=="")//Credit
		 				{
	 						creditCount=creditCount + 1;
		 				}*/
	 					else
	 					{
	 						/*var cltlmt = creditLetter[i].value.split('^')[4];
	 						alert(parseFloat(cltlmt)!="0.00");
	 						alert(cltamt+"::"+cltlmt)
	 						alert(parseFloat(cltamt)>parseFloat(cltlmt));
	 						if(parseFloat(cltamt)>parseFloat(cltlmt) && parseFloat(cltlmt)!="0.00")
	 			 			{
	 			 				alert("Total Availed Tariff Amount is greater than Credit Limit");
	 			 				return false;
	 			 			}*/
	 						
							var cltamt=0;
							//var c=0;
							//var cltamt=document.forms[0].strOfflineMaxClientBenefitAmount.value;
						    //var cltlmt = document.getElementsByName("strCreditLetterNo")[0].value.split('^')[4];
							//alert(letterNo.length);
							//alert(document.getElementById("strOfflineAmtPaidByClient").value);
							//var ltr=[];
							//var letterMap=new Map();
							
							for(var k=0;k<creditLetter[0].length;k++)
		        		    {
								cltamt=0;
								if(creditLetter[0].options[k].value!="0")
		        				{
									if(creditLetter[0].options[k].value.split("^")[16]=="3")
									{
										alert("Seized Letter can't be used");
										return false;
									}
			        				//letterNo#Limit
			        				//clKey=letterNo[0].options[k].value.split("^")[0]+"#"+letterNo[0].options[k].value.split("^")[4];
			        				var limit=parseFloat(creditLetter[0].options[k].value.split("^")[8]);
			        				if(limit>0)
			        				{
				        				var amtByClient=document.getElementsByName("strPaidByClient");
				        				//var amtByClient=document.getElementsByName("strOfflineAmtPaidByClient");
				        				for(var x=0;x<amtByClient.length;x++)
				        				{
				        					/*if(creditLetter[x].value == "0" && payType[x].value=="11")//CREDIT
											{
											  alert("Please Select Credit Letter");
											  return false;
											}*/
				        					if(creditLetter[x].value==creditLetter[0].options[k].value)
					        				{
					        						cltamt+=parseFloat(amtByClient[x].value);
					        				}		        					
				        				}
				        				//letterMap.set(clKey,cltamt);
				        				if(limit>0 && limit<cltamt)
				        				{
					        				alert("Total Availed Tariff Amount is greater than Balance Credit Limit");
					        				//generateRows();
				 							return false;
				        				}
			        				}
			        				var mergeFlag=creditLetter[0].options[k].value.split("^")[13];
			        				var newFlag=creditLetter[0].options[k].value.split("^")[12];
			        				if(newFlag==1 && mergeFlag==1)
			        				{
			        					for(var a=0;a<creditLetter.length;a++)
			    						{
			        						if(creditLetter[a].value != "0" && (document.getElementsByName("strCreditPaymentType")[a].value=="11" || document.getElementsByName("strCreditPaymentType")[a].value=="13") && creditLetter[a].value.split("^")[13]=="0")
			    							{
			    							  alert("Merged Letter cannot be used for Availing Services since its limit has already been combined with that of new letter. Kindly use new letter");
			    							  return false;
			    							}
			    						}
			        				}
		        				}
		        		    }
	 						/*var clDate=document.getElementsByName("strCreditLetterNo")[0].value.split('^')[1];
	 						var cDate=GetDate(clDate);
	 						var numberOfDaysToAdd = 15;
	 						cDate.setDate(cDate.getDate() + numberOfDaysToAdd); 
	 				        var sys = document.forms[0].strTempCtDate.value;
	 				        var sysdate=GetDate(sys);
	 						if(cDate<sysdate)
	 						{
	 							alert("Credit Letter has Expired");
	 							return false;
	 						}*/ //This check has been implemented at query end to show only valid credit letter
	 						/*var retVal=compareDate(creditLetterDate[i].value,document.forms[0].strTempCtDate.value);
							if(retVal.mode==2)
							{
								alert("Credit Letter Date Can't Be Greater Than Current Date");
								 return false;
							}
							
							var datediffVar=dateDiff(creditLetterDate[i].value,document.forms[0].strTempCtDate.value);
							if(datediffVar!=undefined)
								datediffVar=parseInt(datediffVar.split('days')[0]);
							
							if(datediffVar>parseInt(document.forms[0].strCreditLetterValidity.value))
							{
								alert("Credit Letter Date Can't Be Old More Than "+document.forms[0].strCreditLetterValidity.value+" Days");
								return false;
							}	*/						
	 					  }
	 				   }
	 				    creditLetter[i].disabled=false;
	 				}	 				
	 			}	 			
	 			if(count < 1)
	 			{	
	 				alert("Please Select at least One Tariff");
	 				return false;
	 			}
	 			if(creditCount > 0)
	 			{	
	 				alert("Please Enter Credit Details");
	 				return false;
	 			}
	 			
	 			
	 			
	 			if(payType[0].value=="11" || payType[0].value=="13")//Credit or Credit Urgent
 				{
	 				/*if(document.forms[0].strPayClientName.value=="0")
		 			{
		 				alert("Please Select Client Name");
		 				return false;
		 			}  */
	 				//document.forms[0].strPayClientName.value=document.getElementsByName("strCreditLetterNo")[0].value.split('^')[2];
	 				//alert(document.forms[0].strPayClientName.value);
	 				//CL101^21-JUN-2016^1003^Credit^5000.00^STAFF101^21-JUL-2016^^Daughter^CREDIT LI MIT TWO^21-JUL-2016
	 				
 				}
	 			if(gblOnLineHService!= '2' && document.forms[0].strWalletNo.value!=null && document.forms[0].strWalletNo.value!="" && document.getElementsByName("strOnlinePaymentMode")[0].value.split('#')[0]=="7")//If Not IPD and Wallet No Present and selected as payment mode
				{
					 if(parseInt(document.forms[0].strOnlineTotalRecAmount.value)>parseInt(document.forms[0].strAvlWalletMoney.value))
					 {
							 alert("Wallet Balance is less than the total tariff availed. Kindly recharge your wallet.");
							 return false;
					 }
				}
	 			res = checkMultirow('strOnlinePaymentMode', "Payment Mode Already Exists");	
	 			 		
	 			if(res)
	 			{	 			
	 				var hisValidator = new HISValidator("cashCollectionOnlineTransBean");  
	 				hisValidator.addValidation("strOnlineAmount", "req", "Amount is Mandatory Field" );
	 				//added by manisha for same payment type for all tariffs 
	 				if(!validatePaymentType())
	 					return false;
	 			 	//added by manisha for same payment type and payment mode validations
	 				if(!validateCreditForCMFund())
	 					return false;
	 				//hisValidator.addValidation("strOnlineAmount", "numgt=0", "Amount should be Greater than 0.00" );
	 				var res = hisValidator.validate(); 
	 				hisValidator.clearAllValidations();
	 				if(res)
	 				{
	 					onlineSubmitPart();
	 				}
	 				else
	 				{
	 					return false;
	 				}
	 			}	 			
	 		}
	 		else
	 		{	 			
	 			alert("Tariff(s) Not Available");
	 			return false;
	 		}	 		
	 	}
	 	
	 	
	 	
	 	// Opd, Ipd, Emergency Refund Services for On-line		
	 	if(hmodeVal == "ONREFUNDSERVICE"){
	 		
	 		//var hospServ = document.forms[0].currentHospService.value;
	 			
	 			var tariffs = document.getElementsByName("strTariffRefundDetailsId");
	 		
	 		if(tariffs.length > 0){
	 			
	 			var count = parseInt("0");
	 			
	 			for(var i=0; i<tariffs.length; i++) {
	 				
	 				if(tariffs[i].checked){
	 					count = count + 1;
	 				}
	 				
	 			}
	 			
	 		
	 			if(count < 1)
	 			{
	 				alert("Please Select at least One Tariff");
	 				return false;
	 			}
	 			
	 			
	 		}else{
	 			
	 			alert("Tariff(s) Not Available");
	 			return false;
	 		}
	 		
	 		
	 		var refQtyObj = document.getElementsByName("strTariffRefundQty");
	 		
	 		for(var i=0; i<tariffs.length; i++) {
	 					 				
	 				if(tariffs[i].checked == true){
	 					 					
	 					if(refQtyObj[i].value == 0){
	 					
	 						alert("Refund Quantity must be Greater than 0");
	 						refQtyObj[i].focus();	
	 						return false;
	 						
	 					}
	 					
	 				}
	 				
	 			}
	 		
	 			res = checkMultirow('strOnlinePaymentMode', "Payment Mode Already Exists");	
	 		
	 		if(res){
	 			
	 			
	 			var hisValidator = new HISValidator("cashCollectionOnlineTransBean");  
					
				hisValidator.addValidation("strOnlineAmount", "req", "Amount is Mandatory Field" );
				//hisValidator.addValidation("strOnlineAmount", "numgt=0", "Amount should be Greater than 0.00" );
				
				var res = hisValidator.validate(); 
				hisValidator.clearAllValidations();
	 		
	 		
		 		if(res){
		 			
		 	 		onlineSubmitPart();
		 			
		 		}else{
		 			
		 			return false;
		 		}
	 		}	
	 		
	 		
	 		
	 	}
	 	
	 	
	 		// Refund Advance for On-line
	 	if(hmodeVal == "ONREFUNDADVANCE"){
	 		
	 		var res = true;
	 		 	
	 		res = checkMultirow('strOnlinePaymentMode', "Payment Mode Already Exists");	
	 		
	 		if(res){
	 		
	 			var hisValidator = new HISValidator("cashCollectionOnlineTransBean");  
				
				hisValidator.addValidation("strOnlineAmount", "req", "Amount is Mandatory Field" );
				//hisValidator.addValidation("strOnlineAmount", "numgt=0", "Amount should be Greater than 0.00" );
				
				var res = hisValidator.validate(); 
				hisValidator.clearAllValidations();
	 		
	 		
		 		if(res){
		 			 		
		 			onlineSubmitPart();
		 			
		 		}else{
		 			
		 			return false;
		 		}
	 		
	 		}
		 		
	 	}
	 	
	 	
	 if(hmodeVal == "ONFINALADJUST"){
	 		
	 		var res = true;
	 		
	 		
	 			var hisValidator = new HISValidator("cashCollectionOnlineTransBean");  
				
				hisValidator.addValidation("strOnlineAmount", "req", "Amount is Mandatory Field" );
				//hisValidator.addValidation("strOnlineAmount", "numgt=0", "Amount should be Greater than 0.00" );
					
				var res = hisValidator.validate(); 
				hisValidator.clearAllValidations();

				
	 		if(res){
	 				 			
	 				 			
	 				var recTot = parseFloat(document.forms[0].strOnlineTotalRecAmount.value);  			
	 				var refTot = parseFloat(document.forms[0].strOnlineRefundAmount.value);
	 				//alert(recTot);
	 				//alert(document.getElementsByName("strOnlinePaymentMode")[0].value);
	 				if(recTot!=0 && document.getElementsByName("strOnlinePaymentMode")[0].value.split('#')[0]=="10")
	 					{
	 					//document.getElementsByName("strOnlinePaymentMode")[0].value="1";
	 					var paymentMode=document.getElementsByName("strOnlinePaymentMode")[0];
	 					for(j=0;j<paymentMode.options.length;j++)
						 { 
						if(paymentMode.options[j].value.split('#')[0]=="1")
							{		
						document.getElementsByName("strOnlinePaymentMode")[0].value=paymentMode.options[j].value;
						break;
							}
					     }
	 					}
	 					//alert("Payment Mode Changed to Cash as some amount needs to be paid by the patient");

	 
	 		
	 			if((recTot >= 0 && refTot <= 0) || (recTot <= 0 && refTot >= 0)){				
		 				
					document.forms[0].strCrNo.disabled= false;
		 					 			
	 						 			
		 			var payDtl = document.getElementsByName("strOnlinePaymentDtls");
		 			var payMode = document.getElementsByName("strOnlinePaymentMode");
		 			
		 			var len = payDtl.length - 1;
							
					for(var index=0; index<len; index++) {
					
						payDtl[index].disabled = false;
						payMode[index].disabled = false;
					}
					
					
					
						
		 		
		 	 	//	popup('billPrintConfirm' , '250','250');
		 			
		 	//		confirmLogic();
		 			
		 		//	return false;
					$( function() {
					    $( "#dialog-message" ).dialog({
					      modal: true,
					      position: 'top',
					      dialogClass: 'no-close',
					      buttons: {
					        Ok: function() {
					          $( this ).dialog( "close" );
					          confirmOk();
					        },
					        Close: function() {
					          $( this ).dialog( "close" );
					          confirmCancel();
					        }
					      }
					    });
					  } );
		 		
		 			 /*if(confirm("Are You Sure to Save it?"))
		 			 {
		 			 	confirmOk();
		 			 	return false;
		 			 }
		 			 else
		 			 {
		 			 	
		 			 	confirmCancel();
		 			 	return false;
		 			 	
		 			 }*/
					//confirmOk();
	 			}
	 			
	 			}
	 			/*
					
					document.forms[0].strCrNo.disabled= true;
					
						for(var index=0; index<len; index++) {
					
							if(payMode[index].value != 1)
								payDtl[index].disabled = true;
						
					}
					
	  				return false;
	  			 }
					
					
						var cashAmt = parseFloat("0");
			
			var cashAmtObj = getCashPayObject('1');
						
				if( cashAmtObj != null){
					
					cashAmt = parseFloat(cashAmtObj.value);
										
					//if(recTot > 0){
						cashAmtObj.value = roundValue(cashAmt - (refTot),  2);
					//}else{
					//	cashAmtObj.value = roundValue(cashAmt + (refTot),  2);
					//}
					
					
				}
					
									 				
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
	 	
	 	
	 	
	 	if(hmodeVal == "ONRECONCILE"){
	 		
	 		var res = true;
	 		
	 		
	 			var hisValidator = new HISValidator("cashCollectionOnlineTransBean");  
				
				hisValidator.addValidation("strOnlineAmount", "req", "Amount is Mandatory Field" );
					
				var res = hisValidator.validate(); 
				hisValidator.clearAllValidations();

				
	 		
	 		if(res){
	 				 		 
	 				 //onlineSubmitPart();		
	 				 			
	 				
	 				var recTot = parseFloat(document.forms[0].strOnlineTotalRecAmount.value);  			
	 				var refTot = parseFloat(document.forms[0].strOnlineRefundAmount.value);
	 		
	 			if((recTot >= 0 && refTot <= 0) || (recTot <= 0 && refTot >= 0)){	 			
		 				
					document.forms[0].strCrNo.disabled= false;
		 					 			
	 						 			
		 			var payDtl = document.getElementsByName("strOnlinePaymentDtls");
		 			var payMode = document.getElementsByName("strOnlinePaymentMode");
		 			
		 			var len = payDtl.length - 1;
							
					for(var index=0; index<len; index++) {
					
						payDtl[index].disabled = false;
						payMode[index].disabled = false;
					}
					
						
							
		 		
		 	 	//	popup('billPrintConfirm' , '250','250');
		 			
		 	//		confirmLogic();
		 			
		 		//	return false;
					$( function() {
					    $( "#dialog-message" ).dialog({
					      modal: true,
					      position: 'top',
					      dialogClass: 'no-close',
					      buttons: {
					        Ok: function() {
					          $( this ).dialog( "close" );
					          confirmOk();
					        },
					        Close: function() {
					          $( this ).dialog( "close" );
					          confirmCancel();
					        }
					      }
					    });
					  } );
		 		
		 			 /*if(confirm("Are You Sure to Save it?")){
		 			 	
		 			 	confirmOk();
		 			 	return false;
		 			 	
		 			 }else{
		 			 	
		 			 	confirmCancel();
		 			 	return false;
		 			 	
		 			 }*/
					//confirmOk();
	 			}
	 			
	 			}
	 			
	 	}
						 	
				/*		 	
						 	document.forms[0].strCrNo.disabled= true;
					
						for(var index=0; index<len; index++) {
					
					if(payMode[index].value != 1)
						payDtl[index].disabled = true;
						
					}
						 	
						 	
	  						return false;
	  			 		 }
					
					
					
					
			var cashAmt = parseFloat("0");
			
			var cashAmtObj = getCashPayObject('1');
						
				if( cashAmtObj != null){
					
					cashAmt = parseFloat(cashAmtObj.value);
										
					//if(recTot > 0){
						cashAmtObj.value = roundValue(cashAmt - (refTot),  2);
					//}else{
					//	cashAmtObj.value = roundValue(cashAmt + (refTot),  2);
					//}
					
					
				}
					
										 				
		 			document.forms[0].submit();
		 			
	 			}else{
	 				
	 				alert("Net Payment Amount cannot be Less than Net Payable Amount");
	 				return false;
	 			}
	 				
	 			
	 			
	 		}else{
	 			
	 			return false;
	 		}
	 		
	 		
	 	}
	 	*/
	 
	}
	
	
	/**
	 * confirmOk
	 */
	 function confirmOk() {
	 	
	 	
	 //	hide_popup('billPrintConfirm');
	 	
	  
	 	var ctReqType = document.forms[0].currentReceiptType.value ;	 	
	 
	  	//alert(document.forms[0].currentState.value)
	////code for adding hidden tariff of surcharge		
		/*var payTot  = document.getElementsByName("strOnlineAmount");
	    var payMode = document.getElementsByName("strOnlinePaymentMode");	 	 
 
 	    //var cashObj = null; 	
 
 	    var len = payTot.length - 1 ;	

 		for(var i = 0; i < len ; i++){
        
         //alert(payTot[i].value);
		 var payModeVal = payMode[i][payMode[i].selectedIndex].value;
		 var surval=0;
		 //alert(payModeVal);
 		
		if(payTot[i].value!="")
		{
			//alert(document.forms[0].defsurlim.value);
			if(payTot[i].value>0 && parseFloat(payTot[i].value)<=document.forms[0].defsurlim.value)
			{
				if(payModeVal==4)
				{
					surval=Math.round(parseFloat((document.forms[0].strSurCc.value/100)*payTot[i].value));
					payTot[i].value=Math.round(parseFloat(payTot[i].value)+surval);	
					alert("Please collect Rs. "+payTot[i].value+" ("+document.forms[0].strOnlineTotalRecAmount.value+"+"+document.forms[0].strSurCc.value+"% Surcharge)");
				}
				if(payModeVal==5)
				{
					surval=Math.round(parseFloat((document.forms[0].strSurDc.value/100)*payTot[i].value));
					payTot[i].value=Math.round(parseFloat(payTot[i].value)+surval);
					alert("Please collect Rs. "+payTot[i].value+" ("+document.forms[0].strOnlineTotalRecAmount.value+"+"+document.forms[0].strSurDc.value+"% Surcharge)");
				}
				if(payModeVal==8)
				{
					surval=Math.round(parseFloat((document.forms[0].strSurIc.value/100)*payTot[i].value));
					payTot[i].value=Math.round(parseFloat(payTot[i].value)+surval);
					alert("Please collect Rs. "+payTot[i].value+" ("+document.forms[0].strOnlineTotalRecAmount.value+"+"+document.forms[0].strSurIc.value+"% Surcharge)");
				}
				if(payModeVal==9)
				{
					surval=Math.round(parseFloat((document.forms[0].strSurId.value/100)*payTot[i].value));
					payTot[i].value=Math.round(parseFloat(payTot[i].value)+surval);
					alert("Please collect Rs. "+payTot[i].value+" ("+document.forms[0].strOnlineTotalRecAmount.value+"+"+document.forms[0].strSurId.value+"% Surcharge)");
				}
			}
			else if(parseFloat(payTot[i].value)>document.forms[0].defsurlim.value)
			{
				if(payModeVal==4)
				{
					surval=Math.round(parseFloat((document.forms[0].strSurCc1.value/100)*payTot[i].value));
					payTot[i].value=Math.round(parseFloat(payTot[i].value)+surval);
					alert("Please collect Rs. "+payTot[i].value+" ("+document.forms[0].strOnlineTotalRecAmount.value+"+"+document.forms[0].strSurCc1.value+"% Surcharge)");
				}
				if(payModeVal==5)
				{
					surval=Math.round(parseFloat((document.forms[0].strSurDc1.value/100)*payTot[i].value));
					payTot[i].value=Math.round(parseFloat(payTot[i].value)+surval);
					alert("Please collect Rs. "+payTot[i].value+" ("+document.forms[0].strOnlineTotalRecAmount.value+"+"+document.forms[0].strSurDc1.value+"% Surcharge)");
				}
				if(payModeVal==8)
				{
					surval=Math.round(parseFloat((document.forms[0].strSurIc1.value/100)*payTot[i].value));
					payTot[i].value=Math.round(parseFloat(payTot[i].value)+surval);
					alert("Please collect Rs. "+payTot[i].value+" ("+document.forms[0].strOnlineTotalRecAmount.value+"+"+document.forms[0].strSurIc1.value+"% Surcharge)");
				}
				if(payModeVal==9)
				{
					surval=Math.round(parseFloat((document.forms[0].strSurId1.value/100)*payTot[i].value));
					payTot[i].value=Math.round(parseFloat(payTot[i].value)+surval);
					alert("Please collect Rs. "+payTot[i].value+" ("+document.forms[0].strOnlineTotalRecAmount.value+"+"+document.forms[0].strSurId1.value+"% Surcharge)");
				}
			}
			if(surval>0)
			{
				if(!(document.forms[0].currentBserviceId.value=="19" || document.forms[0].currentBserviceId.value=="21"))
					document.forms[0].strOnlineTotalRecAmount.value = payTot[i].value;*/
				/*else
					document.forms[0].strTariffNetAmt.value= surval;*/
				/*document.getElementById("onlineTotalRecAmtDivId").innerHTML = payTot[i].value;
				document.getElementsByName("strOnlineAmount")[0].value = payTot[i].value;			
			    document.forms[0].strOnlineTotalPayAmount.value = payTot[i].value;
			    document.getElementById("onlineTotalPayAmtDivId").innerHTML = payTot[i].value;
			    //alert(document.getElementById("offlinePatNetPayDivId"));
			    if(document.getElementById("onlinePatNetPayDivId") != null)
		 		{		 			 			
			 		document.forms[0].strOnlinePatNetPayAmount.value = payTot[i].value;
			 		document.getElementById("onlinePatNetPayDivId").innerHTML = payTot[i].value;	 		
		 		}		 		
			    var offTariffName=document.getElementsByName("strTariffDetailsId");
			    var length=offTariffName.length;*/
			    //alert(length);
			    //length++;
			    
			    //alert(length);
			   /* if(document.getElementsByName("strTariffDetailsId").length > 0 && document.getElementsByName("strTariffDetailsId")[0].value.length > 0 )
	 	 		{
			    	document.forms[0].strTotalTariffActualAmount.value= payTot[i].value;*/
			    	/*String strHiddenVal = strTariffId + "^" + strReqQty + "*"
					+ strQtyBaseVal + "^" + strQtyUnitId + "^"
					+ strTariffRate + "^" + strRateUnitId + "^"
					+ strGroupId + "^" + strActualRate + "^"
					+ strServiceTax + "^" + strDisountUnit + "^"
					+ strDiscountType + "^" + strGblTariffId + "^"
					+ strApprovalId + "^" + strUnitName + "^"
					+ strRateBaseVal + "^" + strIsPackage + "^"
					+ strNetCost + "^" + strPenelty + "^" + "0^"
					+ strReceiptNo + "^" + strApprovalDtl+"^"+strServiceId+"^"+strConsumableCharge;*/
			    	//1300005^1*1^1701^45000.00^1701^130^45000.00^0^0.00^1^1067^0^No^1^0^45000.00^0^0^0^^0^/^0^/^6^0
				   // document.getElementsByName("strTariffDetailsId).value="1090001^1*1^1701^"+surval+"^1701^109^"+surval+"^0^0.00^1^0^0^No.^1^0^"+surval+"^0^0^0^^0^/^0^/^0^0";
				    /*for(var nTmpI=0; nTmpI<document.getElementsByName("strTariffDetailsId").length; nTmpI++){
				    	
						//alert(document.getElementsByName("strTariffDetailsId")[nTmpI].value);
				    	
						if(nTmpI==(length-1))
							{
							
							//alert(length);
							
							
							 //alert(document.getElementsByName("strTariffDetailsId").length);  
							 document.getElementById("strTariffDetailsChkId"+length).value="1090001^1*1^1701^"+surval+"^1701^109^"+surval+"^0^0.00^1^0^0^No.^1^0^"+surval+"^0^0^0^^0^/^0^/^0^0";
							 //alert(length);
							 document.getElementById("strTariffBilledQty"+length).value="1";
							 document.getElementById("strTariffNetAmt"+length).value=surval;
							 document.getElementById("strBillTariffUnit"+length).value="1701";
							 document.getElementById("strPaidByPat"+length).value=surval;
							 //alert( document.getElementById("strTariffNetAmt"+length).value);
							}
					} 
				    
				    
				    
				 //alert(document.getElementsByName("strTariffDetailsId").length);  
	 	 		}
			   
			}
		}
 		}*/
	  	
	 if(document.forms[0].currentState != null && document.forms[0].currentState.value == '1'){
	 			
	 	
	 		
	 		if(document.forms[0].hmode.value == "ONFINALADJUST" || document.forms[0].hmode.value == "ONRECONCILE"){
	 			
	 			document.getElementById("saveid").style.display = "none";
	 			document.forms[0].submit();
	 			return;
	 			
	 		}		
	 			
	 		else{	
	 				 			
			var refTot = parseFloat(document.forms[0].strOnlineRefundAmount.value);
		 			
			var cashAmt = parseFloat("0");
			
			var cashAmtObj = getCashPayObject('1');
						
				if( cashAmtObj != null){
					
					cashAmt = parseFloat(cashAmtObj.value);
										
					if(ctReqType == '2'){
						cashAmtObj.value = roundValue(cashAmt - (refTot),  2);
					}else{
						cashAmtObj.value = roundValue(cashAmt + (refTot),  2);
					}
					
					
				}
				addConsumableTariffBeforeSave()
				
				document.getElementById("saveid").style.display = "none";
					document.forms[0].submit();
					return;
	 		}
	 	}else{
	 			 			

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
					
					
				}
				
				
				addConsumableTariffBeforeSave()
		 	
				document.getElementById("saveid").style.display = "none";
					document.forms[0].submit();
					return;
	 		 		
	 		
	 	}
	 	
	 	
	 }
	
	
	/**
	 * confirmCancel
	  
	 */
	 function confirmCancel() {
	 	
	 //	hide_popup('billPrintConfirm');
	 	
	 			 	
	 	if(document.forms[0].currentState != null && document.forms[0].currentState.value == '1'){
	 		
	 		
	 		if(document.forms[0].hmode.value == "ONFINALADJUST" || document.forms[0].hmode.value == "ONRECONCILE"){
	 			
	 				
						 	document.forms[0].strCrNo.disabled= true;
					
						for(var index=0; index<len; index++) {
					
					if(payMode[index].value != 1)
						payDtl[index].disabled = true;
						
					}
	 			
	 			
	 		}	
	 		
	 		
	 		document.forms[0].strCrNo.disabled= true;
		 					 			
	 						 			
		 			var payDtl = document.getElementsByName("strOnlinePaymentDtls");
		 			var payMode = document.getElementsByName("strOnlinePaymentMode");
		 			
		 			var len = payDtl.length - 1;
							
					for(var index=0; index<len; index++) {
					
						if(payMode[index].value != 1)
						payDtl[index].disabled = true;
					
					}
		 			
	 			 		  	
	 		
	 		
	 		
	 	}
	 	
	 	
	 	return false;
	 }
	
	 
	 /**
	 * onlineSubmitPart
	 * @param {type}  
	 */
	 function onlineSubmitPart() {
	 
		 
		 var paymodval= document.getElementsByName("strOnlinePaymentMode")[0].value;
			var flag="";
			if(paymodval!=null && paymodval!=undefined && paymodval!=0)
				{
				var optVal=paymodval.split("#")[0];
				
				if(optVal == 2 || optVal == 3){  
					//validateCheckDD();
					if(document.forms[0].strPayCDDBankName.value=="")
					 {alert("Please enter Bank Name/Cheque/DDNo");
					 return false;
					 }
					}
				else if(optVal == 4 || optVal == 5 || optVal == 8 || optVal == 9){  
					//validateCreditDebit();
					if(document.forms[0].strCardNo.value=="")
					 {alert("Please enter Bank Name/CardNo/CardType");
					 return false;
					 }
						
					}
				else if(optVal == 6){
					//validateClientDetails();  
					if(document.forms[0].strPayCNTClientName.value=="")
					 {alert("Please enter Client Name");
					 return false;
					 }
					}
				else if(optVal == 7){
					validateWallet();
						//return false;
				    }
				
				}
	 		 
	 			var ctReqType = document.forms[0].currentReceiptType.value ;	
	 			var chkObj = document.getElementsByName("onLineDataRadio");
				if(chkObj.length > 0)
				{
					for(var i=0; i<chkObj.length; i++) 
					{
					  	if(chkObj[i].checked)
					  	{
					  		//alert(i);
	 			            //alert(document.getElementById("billcancelchk"+i).value>0);
	 			
				 			if(document.getElementById("billcancelchk"+i).value>0)
				 			{
				 			    alert("This Bill has already been Cancelled.")
				 			    return false;
				 			}
					     }
					 }
				}
	 
	 			if(validateTotalPaymentAmt('1')){
	 			
	 				var refTot = parseFloat(document.forms[0].strOnlineRefundAmount.value);
	 		
	 		 		
	 			if((refTot <= 0 && ctReqType == '1') || (refTot >= 0 && ctReqType == '2')){	 			
		 				
				document.forms[0].strCrNo.disabled= false;
		 					 			
	 						 			
		 			var payDtl = document.getElementsByName("strOnlinePaymentDtls");
		 			var payMode = document.getElementsByName("strOnlinePaymentMode");
		 			
		 			var len = payDtl.length - 1;
							
					for(var index=0; index<len; index++) {
					
						payDtl[index].disabled = false;
						payMode[index].disabled = false;
					}
		 			
		 				 					 			
		 			
		 		
		 	 	//	popup('billPrintConfirm' , '250','250');
		 			
		 	//		confirmLogic();
		 			
		 		//	return false;
					$( function() {
					    $( "#dialog-message" ).dialog({
					      modal: true,
					      position: 'top',
					      dialogClass: 'no-close',
					      buttons: {
					        Ok: function() {
					          $( this ).dialog( "close" );
					          confirmOk();
					        },
					        Close: function() {
					          $( this ).dialog( "close" );
					          confirmCancel();
					        }
					      }
					    });
					  } );
		 		
		 			 /*if(confirm("Are You Sure to Save it?")){
		 			 	
		 			 	confirmOk();
		 			 	return false;
		 			 	
		 			 }else{
		 			 	
		 			 	confirmCancel();
		 			 	return false;
		 			 	
		 			 }*/
					//confirmOk();
	 			}
	 			
	 			}
	 			 	
	 			 	/*
	 			 		document.forms[0].strCrNo.disabled= true;
		 					 			
	 						 			
		 			var payDtl = document.getElementsByName("strOnlinePaymentDtls");
		 			var payMode = document.getElementsByName("strOnlinePaymentMode");
		 			
		 			var len = payDtl.length - 1;
							
					for(var index=0; index<len; index++) {
					
						if(payMode[index].value != 1)
						payDtl[index].disabled = true;
					
					}
		 			
	 			 	
	 			 	
	  				return false;
	  			 }
		 			
		 			
			var cashAmt = parseFloat("0");
			
			var cashAmtObj = getCashPayObject('1');
						
				if( cashAmtObj != null){
					
					cashAmt = parseFloat(cashAmtObj.value);
										
					if(ctReqType == '2'){
						cashAmtObj.value = roundValue(cashAmt - (refTot),  2);
					}else{
						cashAmtObj.value = roundValue(cashAmt + (refTot),  2);
					}
					
					
				}
			
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
	 
	 
	
	function addConsumableTariffBeforeSave()
	{
	
	
		if (document.forms[0].hmode.value == "ONRECSERVICE")
				 {
				 	////code for adding hidden tariff of consumable charges		
	 		 		
			 	 var consumableCharges=document.getElementsByName("strConsumableCharge")[0].value
			 	 var consumablechargeGroupId=document.forms[0].strConsumableChargesGroupId.value;
	 			var consumablechargeTariffId=document.forms[0].strConsumableChargesTariffCode.value;
			 	
			 	var hiddenfieldvalue=consumablechargeTariffId+"^"+"1"+"*"+"1"+"^"+"1701"+"^"+
			 	consumableCharges+"^"+"1701"+"^"+consumablechargeGroupId+"^"+
			 	consumableCharges+"^"+"0"+"^"+"0"+"^"+"0"+"^"+"1996"+"^"+"0"+"^"+"Each"+"^"+"1"+"^"+"0"+"^"+
			 	consumableCharges+"^"+"0"+"^"+"0"+"^"+"0"+"^"+""+"^"+"0"+"^"+"/"+"^"+"0"+"^"+"/"+"^"+"1"+"^"+"0";
			 	
			 	if(parseInt(consumableCharges)>0)
			 	{
			 	var divInnerHtml="<input type='hidden' name='strTariffDetailsId' value='"+hiddenfieldvalue+"'/>"
			 	divInnerHtml=divInnerHtml+"<input type='hidden' name='strBillTariffName' value='"+hiddenfieldvalue+"'/>"
			 	divInnerHtml=divInnerHtml+"<input type='hidden' name='strTariffServiceTaxAmt' value='0'/>"
			 	divInnerHtml=divInnerHtml+"<input type='hidden' name='strTariffRate' value="+consumableCharges+"'/>"
			 	divInnerHtml=divInnerHtml+"<input type='hidden' name='strTariffActualAmt'  value='0'/>"
			 	divInnerHtml=divInnerHtml+"<input type='hidden' name='strTariffReqQty' value='1'/>"
			 	divInnerHtml=divInnerHtml+"<input type='hidden' name='strTariffBilledQty' value='1'/>"
			 	divInnerHtml=divInnerHtml+"<input type='hidden' name='strBillTariffUnit' value='1701'/>"
			 	divInnerHtml=divInnerHtml+"<input type='hidden' name='strTariffNetAmt'  value='"+consumableCharges+"'/>"
			 	divInnerHtml=divInnerHtml+"<input type='hidden' name='strTariffDiscountAmt'  value='0'/>"
			 	
			 	
			 	
			 	
			 		document.getElementById("consumableChargeDiv").innerHTML=divInnerHtml
	 			}
				 }
	}




function openPrintPopUp()
{
	if(document.forms[0].isOpenPopUp.value==1 && document.forms[0].printMode.value==1)//Printing Over Laser Printer
	{
		window.print();
			//window.close();
		
		/*var url='CashCollectionOfflineTransCNT.cnt?hmode=PRINTSLIP&filePath='+document.forms[0].filePath.value
		  child = window.open(createFHashAjaxQuery(url,'popupWindow','status=yes,scrollbars=yes,height=600,width=500,left=400,top=100'));  
		  child.moveTo(900,250);
		  child.focus();*/
	
	 }
	
	//alert(window.matchMedia('print'));
	 document.forms[0].isOpenPopUp.value=0;
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
	//alert(document.forms[0].filePath.value);
	if(document.forms[0].filePath.value=="")
	{
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
function printSlip()
{
	window.print();
	if(parseInt(globalCnt)<=2)
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
        	globalCnt++;
            printSlip();
        }
}
function calcCreditAmount(obj,index)
{
	
	var chkObj=document.getElementById("strTariffDetailsChkId"+index);
	//var creditLetter=document.getElementById("strCreditLetterNo"+index);
	//var creditDate=document.getElementById("strCreditLetterDate"+index);
	if(chkObj.checked)
	{
		if(obj.value=="11" || obj.value=="13")//Credit or Credit Urgent
		{		
			setOnlineTariffDetails(chkObj , index) ;
			
			//document.getElementsByName("strOnlinePaymentMode")[0].value="10";//Reset To CM Relief Fund
			var paymentMode=document.getElementsByName("strOnlinePaymentMode")[0];
			for(j=0;j<paymentMode.options.length;j++)
			 { 
			if(paymentMode.options[j].value.split('#')[0]=="10")  //Reset To CM Relief Fund
				{		
			document.getElementsByName("strOnlinePaymentMode")[0].value=paymentMode.options[j].value;
			break;
				}
		     }
			
		}
		else
		{
			setOnlineTariffDetails(chkObj , index) ;
			
			//document.getElementsByName("strOnlinePaymentMode")[0].value="1";//Reset To Cash
			var paymentMode=document.getElementsByName("strOnlinePaymentMode")[0];
			for(j=0;j<paymentMode.options.length;j++)
			 { 
			if(paymentMode.options[j].value.split('#')[0]=="1") //Reset To Cash
				{		
			document.getElementsByName("strOnlinePaymentMode")[0].value=paymentMode.options[j].value;
			break;
				}
		     }
		}
	}
	else
	{
		alert("Please Select Check Box/Record");
		return;
	}	
}

function applySameCreditDtls(obj)
{
	
	var tariffs = document.getElementsByName("strTariffDetailsId");
 	var payType = document.getElementsByName("strCreditPaymentType");
 	var creditLetter = document.getElementsByName("strCreditLetterNo");
 	//var creditLetterDate = document.getElementsByName("strCreditLetterDate");
 	
 	
 		
 	if(tariffs.length > 1)
 	{
 		if(obj.checked)
 	 	{
 			if(document.forms[0].strNewCreditLetterAddedFlag.value==0)
 			{
 				alert("Apply All Works only for New Credit Letter not Existing Credit Letter");
 				obj.checked=false;
 				return;
 			}
 			var creditLetterVal=creditLetter[0].value;
 		 	//var creditLetterDateVal=creditLetterDate[0].value;
 		 	
 		 	
 			for(var i=0; i<tariffs.length; i++) 
 			{
 				if(tariffs[i].checked)
 				{
 					//if(creditLetterVal=="" || creditLetterDateVal=="" )
 					if(creditLetterVal=="0")
 		 		 	{
 		 		 		alert("Enter Credit Details In First Row");
 		 		 		obj.checked=false;
 		 		 		return;
 		 		 	}
 					if(payType[i].value=="11" || payType[i].value=="13")//Credit or Credit Urgent
 	 				{
 						//creditLetter[i].value=creditLetterVal;
 						//creditLetterDate[i].value=creditLetterDateVal;
 						if(i!=0)
 						{ 						
	 						var daySelect = creditLetter[i];	 						
	 						var newOption = document.createElement("option");
	 						var optText=creditLetter[0][creditLetter[0].selectedIndex].text;
	 						newOption.text = optText;	 						
	 						var optValue=creditLetter[0][creditLetter[0].selectedIndex].value;	 						
	 						//<option title="CL101/21-Jun-16/Credit/5000.00" value="CL101^21-JUN-2016^1003^Credit^5000.00^STAFF101^21-JUL-2016^^Daughter^CREDIT LI MIT TWO^21-JUL-2016">CL101/21-Jun-16/Credit/5000.00</option>
	 						//CL102^25-Jun-2016^1007^Cghs^50^8569^06-MAY-2016^Grandmother^STSAFF^06-MAY-2016
	 						newOption.value = optValue;
	 						newOption.selected = 'selected';
	 						//newOption.id = "strNewCreditLetterNoOptionId";
	 						daySelect.appendChild(newOption);
	 						//hide_popup('creditDtlMenu');
 						} 						
 	 				}
 				}
 				else
 				{
 					alert("Select Atleast One Tariff");
 					obj.checked=false;
 	 		 		return;
 				}
 				
 			}
 	 		
 	 	}
 		else
 		{
 			for(var i=0; i<tariffs.length; i++) 
 			{
 				if(tariffs[i].checked)
 				{
 					if(payType[i].value=="11" || payType[i].value=="13")//Credit or Credit Urgent
 	 				{
 						creditLetter[i].value="0";
 						//creditLetterDate[i].value="";
 	 				}
 				}
 				
 			}
 			
 		}
 	}
 	else
 	{	 			
 			alert("Tariff(s) Not Available");
 			return false;
 	}	 		
 	
}

//added by manisha for same payment type for all tariffs 
function validatePaymentType()
{
		var tariffs = document.getElementsByName("strTariffDetailsId");
	 	var payType = document.getElementsByName("strCreditPaymentType");
	 	
	 	//alert(tariffs.length);
	 	//alert(payType.length);
	 	if(payType.length==0 || payType==null || payType==undefined)
	 	{
	 		return true;
	 	}	
	 		
	 	else if(tariffs.length == 2 ||  tariffs.length==1)
	 	{
	 		return true;
	 	}
	 	
	 	else if(tariffs.length > 2)
	 	{		
	 	
	 			for(var i=2; i<tariffs.length; i++) 
	 			{
	 				
	 				// alert("i  :"+payType[i].selectedIndex+"i .value"+payType[payType[i].selectedIndex].value); 
	 				// alert("i-1  :"+payType[i-1].selectedIndex+"i-1 value"+payType[payType[i-1].selectedIndex].value);
	 				var index= payType[i-1].selectedIndex;
	 				var index2= payType[i-2].selectedIndex;
	 				// alert(payType[i-1][index].value);
	 				// alert(payType[i-2][index2].value);
	 				 if (i > 1 &&  payType[i-1][index].value != payType[i-2][index2].value) 
	 				  {
	 					 alert("Select same Payment Type for all Tariffs");
	 					 return false;
	 				   }
	 			 }
	 			
	 			return true;
	 	}
}

// added by manisha for Payment type & payment mode wise Validations..  credit categores with credit payment type only and cash with paid only

function validateCreditForCMFund()
{
	 	var tariffs = document.getElementsByName("strTariffDetailsId");
	 	var payType = document.getElementsByName("strCreditPaymentType");
	 	
	 	if(document.getElementsByName("strOnlinePaymentMode")[0].value.split('#')[0]=="10") //cm relief fund
	 		{
	 		//alert(document.getElementsByName("strOnlinePaymentMode")[0].value.split('#')[0]);
	 		//alert(tariffs.length);
	 		
	 		for(var j=0; j<tariffs.length; j++) 
 			{
 			 	var index= payType[j].selectedIndex;
 			 	//alert(index);
 			 	//alert(payType[j][index].value);
 				 if (payType[j][index].value == "10" ||  payType[j][index].value == "12")  // credit options 
 				  { 
 					 alert("select Payment Type as 'Credit' for Payment Mode 'CM Relief Fund' ");
					 return false;
 				  }
 				 
 			 }
	 		 
	 		}
	 	
	 	
	 	if(document.getElementsByName("strOnlinePaymentMode")[0].value.split('#')[0]=="1") //cash
 		{
 		for(var j=0; j<tariffs.length; j++) 
			{
 			
			 	var index= payType[j].selectedIndex;
				//alert(payType[j][index].value);
				 if (payType[j][index].value == "11" ||  payType[j][index].value == "13")  // paid options 
				  { 
					 alert("select Payment Type as 'Paid' for Payment Mode 'Cash' ");
				 return false;
				  }
				 
			 }
 		 
 		}
	 	
	 	
	 	return true;
}


function calcNetAmount()
{
	var advanceAmt1 = document.forms[0].strAdvanceAmount;
	if(advanceAmt1!=undefined)
	{
		var amt1 = roundValue(advanceAmt1.value,2);
		document.getElementsByName("strOnlineAmount")[0].value = Math.round(amt1);
		document.getElementById("onlineTotalRecAmtDivId").innerHTML = Math.round(amt1);
		document.forms[0].strOnlineTotalRecAmount.value = Math.round(amt1);
		document.getElementById("onlineTotalPayAmtDivId").innerHTML = Math.round(amt1);
		document.forms[0].strOnlineTotalPayAmount.value = Math.round(amt1);
	}
}
function selectAllCheckBoxes(obj)
{
	var objAllChk=document.getElementsByName("strTariffDetailsId");
	
	if(obj.checked)
	{
		for(var i=0;i<objAllChk.length;i++)
		{
			var objChk=document.getElementsByName("strTariffDetailsId")[i];
			objChk.checked=true;
			setOnlineTariffDetails(objChk,(i+1));
		}
	}
	else
	{
		for(var i=0;i<objAllChk.length;i++)
		{
			var objChk=document.getElementsByName("strTariffDetailsId")[i];
			objChk.checked=false;
			setOnlineTariffDetails(objChk,(i+1));
		}
	}
}
function validateWallet()
{
	if(document.forms[0].strWalletNo.value!=null && document.forms[0].strWalletNo.value!="")//Wallet Details Present
	{
		if(parseInt(document.forms[0].strAvlWalletMoney.value)>0 )//Avalable Wallet Money Present
		{
			if(parseInt(document.forms[0].strAvlWalletMoney.value)<parseInt(document.forms[0].strOnlineTotalRecAmount.value) )//Avalable Wallet Money Present
			{
				alert("Not Enough Money In Wallet. Please Recharge");
				return false;
			}
			else
			{
				var resultVal = "Wallet No.: "+document.forms[0].strWalletNoMasked.value;				
				gblPayDtls.value = resultVal;
				gblPayEdit.disabled = false;				
				hide_popup('payDtlWalletMenu');
//				gblAmount.focus();
				return true;
			}
		}
		else
		{
			alert("Not Enough Money In Wallet. Please Recharge");
			return false;
		}
	}
	else
	{
		alert("No Wallet Found.Please Deposit Cash");
		hide_popup('payDtlWalletMenu');
		
		//document.getElementsByName("strOnlinePaymentMode")[0].value="1";//Reset To Cash
		var paymentMode=document.getElementsByName("strOnlinePaymentMode")[0];
		for(j=0;j<paymentMode.options.length;j++)
		 { 
		if(paymentMode.options[j].value.split('#')[0]=="1")  //Reset To Cash
			{		
		document.getElementsByName("strOnlinePaymentMode")[0].value=paymentMode.options[j].value;
		break;
			}
	     }
		return false;
	}
}
function setwalacc()
{
	if(document.getElementById("walacc").checked==false)
		document.getElementById("walacc").value="1";
	else
		document.getElementById("walacc").value="0";
}
function okcd()
{
	hide_popup('cardAmtDiv');
	//cardlogic();
	popup('payDtlCDMenu' , '250','250');
	
}
function ccd()
{
	hide_popup('cardAmtDiv');
	
	//document.getElementsByName("strOnlinePaymentMode")[0].value="1";//Reset To Cash
	var paymentMode=document.getElementsByName("strOnlinePaymentMode")[0];
	for(j=0;j<paymentMode.options.length;j++)
	 { 
	if(paymentMode.options[j].value.split('#')[0]=="1")  //Reset To Cash
		{		
	document.getElementsByName("strOnlinePaymentMode")[0].value=paymentMode.options[j].value;
	break;
		}
     }
}
function cardlogic()
{
	var payTot  = document.getElementsByName("strOnlineAmount");
    var payMode = document.getElementsByName("strOnlinePaymentMode");
    
  

	    //var cashObj = null; 	

	    var len = payTot.length - 1 ;	

		for(var i = 0; i <= len ; i++)
		{
			var payModeVal = payMode[i][payMode[i].selectedIndex].value.split('#')[0];
        var surval=0;
        var amt=0;
	 //alert(payModeVal);
		
	if(payTot[i].value!="")
	{
		amt=payTot[i].value;
		//alert(document.forms[0].defsurlim.value);
		if(payTot[i].value>0 && parseFloat(payTot[i].value)<=document.forms[0].defsurlim.value)
		{
			if(payModeVal==4) //Credit Card
				surval=Math.round(parseFloat((document.forms[0].strSurCc.value/100)*amt));
			else if(payModeVal==5) //Debit Card
			    surval=Math.round(parseFloat((document.forms[0].strSurDc.value/100)*amt));
			else if(payModeVal==8) // International Credit Card
			    surval=Math.round(parseFloat((document.forms[0].strSurIc.value/100)*amt));
			else if(payModeVal==9) //// International Debit Card
			    surval=Math.round(parseFloat((document.forms[0].strSurId.value/100)*amt));
		}
		else if(parseFloat(payTot[i].value)>document.forms[0].defsurlim.value)
		{
			if(payModeVal==4)
				surval=Math.round(parseFloat((document.forms[0].strSurCc1.value/100)*amt));
			else if(payModeVal==5)
				surval=Math.round(parseFloat((document.forms[0].strSurDc1.value/100)*amt));
			else if(payModeVal==8)
				surval=Math.round(parseFloat((document.forms[0].strSurIc1.value/100)*amt));
			else if(payModeVal==9)
			    surval=Math.round(parseFloat((document.forms[0].strSurId1.value/100)*amt));
		}
		amt=Math.round(parseFloat(payTot[i].value)+surval);	
		//alert(amt);
		document.getElementById("totalCardAmtDivId2").innerHTML = "<td class='LABEL' colspan='2'><font color='red'>#</font><font size='2' color='red'>Final Transaction Amount(with Surcharge)(<img src='/HBIMS/hisglobal/images/INR.png'>)&nbsp;<b>"+amt+"</b></font></td>";
		document.getElementById("totalCardAmtDivId3").innerHTML = "<td class='LABEL' colspan='2'><font color='red'>#</font><font size='2' color='red'>Final Transaction Amount(with Surcharge)(<img src='/HBIMS/hisglobal/images/INR.png'>)&nbsp;<b>"+amt+"</b></font></td>";
		document.getElementById("totalBillAmtDivId1").innerHTML = "<td class='LABEL'>Billed Amount(<img src='/HBIMS/hisglobal/images/INR.png'>)&nbsp;"+document.forms[0].strOnlineTotalRecAmount.value+"</td>";
		document.getElementById("totalSurAmtDivId1").innerHTML =  "<td class='LABEL'>Surcharge Amount(<img src='/HBIMS/hisglobal/images/INR.png'>)&nbsp;"+surval+"</td>";
	}
		}
}
