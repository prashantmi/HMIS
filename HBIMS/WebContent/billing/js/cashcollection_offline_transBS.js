
	// Global Vaiables
	
	var gblPayDtls = "";
	var gblPayEdit = "";
	var gblAmount = "";  
	var gblPayMode = "";
	var savedTariff="";
	var wardVal = "";
	
	function setTariff(){
		var offTariffName=document.getElementsByName("strOfflineTariffName");
		for(var nTmpI=0; nTmpI<offTariffName.length; nTmpI++){
			if(savedTariff=="" && offTariffName[nTmpI].value!="")
				savedTariff=document.getElementsByName("strOffLineTreatmentCategory")[0].value;
		}
		
	
		
	}
	
	
	function setWard(){
		var offTariffName=document.getElementsByName("strOfflineTariffName");
		for(var nTmpI=0; nTmpI<offTariffName.length; nTmpI++){
			if(wardVal=="" && offTariffName[nTmpI].value!="")
				wardVal=document.getElementsByName("strOffLineWard")[0].value;
		}
		
	}
	
	
	function changeTreatment(){
		var fFlag=true;
		var offTariffName=document.getElementsByName("strOfflineTariffName");
		
	 
		for(var nTmpI=0; nTmpI<offTariffName.length; nTmpI++){
			if(offTariffName[nTmpI].value!="")
				fFlag=false;
		}
		 
		if(fFlag){
			 
			resetTariffRows();
		}
		if(savedTariff!="" && confirm("All Tariff will be reset")){
			document.getElementsByName("strOfflineTariffName")[0].focus();
			savedTariff="";
		 
			resetTariffRows();
			
			
				previousData = "";
			
	getPartAccDtls(document.getElementsByName("strOfflineTariffName")[0]);
			
			
		}else if(savedTariff!=""){
			document.getElementsByName("strOffLineTreatmentCategory")[0].value=savedTariff;
			document.getElementsByName("strOfflineTariffName")[0].focus();
			savedTariff="";
		}
		
	
	getPartAccDtls(document.getElementsByName("strOfflineTariffName")[0]);
		
	}
	
	
	function changeWard(){
		var fFlag=true;
		var offTariffName=document.getElementsByName("strOfflineTariffName");
	
	 
		for(var nTmpI=0; nTmpI<offTariffName.length; nTmpI++){
			if(offTariffName[nTmpI].value!="")
				fFlag=false;
		}
		 

		 
		if(fFlag){
			 
			resetTariffRows();
		}
		
		if(wardVal!="" && confirm("All Tariff will be reset")){
			document.getElementsByName("strOfflineTariffName")[0].focus();
			wardVal="";
		 
			resetTariffRows();
			
				previousData = "";
			
		getPartAccDtls(document.getElementsByName("strOfflineTariffName")[0]);
			
		}else if(wardVal!=""){
			document.getElementsByName("strOffLineWard")[0].value=wardVal;
			document.getElementsByName("strOfflineTariffName")[0].focus();
			wardVal="";
		}
		
	getPartAccDtls(document.getElementsByName("strOfflineTariffName")[0]);
		
	}
	
	
	function generateRows()
	{
		if(!showAlert())
			return false;
			
		//addRows(new Array('strOfflineTariffName','strOfflineTariffDetailsHidden','strOfflineTempTariffRateUnit','strOfflineTariffRateUnit','strOfflineTariffQty','strOfflineTariffUnit','strOfflineTariffServiceTax', 'strOfflineTariffServiceTaxAmtVal','strOfflineTariffDiscount','strOfflineTariffDiscountAmtVal' ,'strOfflineTariffNetAmount','strOfflineTotalActualTariffAmtVal','strCreditLetterNo','strCreditRefDate1','uploadedFile'),new Array('t','t','t','t','t','s','t','t','t','t','t','t','t','d','t'),'2','1','R')
		addRows(new Array('strOfflineTariffName','strOfflineTariffDetailsHidden','strOfflineTempTariffRateUnit','strOfflineTariffRateUnit','strOfflineTariffQty','strOfflineTariffUnit','strOfflineTariffServiceTax', 'strOfflineTariffServiceTaxAmtVal','strOfflineTariffDiscount','strOfflineTariffDiscountAmtVal' ,'strOfflineTariffNetAmount','strOfflineTotalActualTariffAmtVal','strCreditPaymentType','strCreditLetterNo','uploadedFile'),new Array('t','t','t','t','t','s','t','t','t','t','t','t','s','s','t'),'2','1','R');
		
		var tempVal = "strOfflineTariffName2-"+document.multirow.rowIndex2.value;
	 	var tempObj = document.getElementById(tempVal);
		tempObj.select();
		tempObj.focus();
		chkPaidCredit();
	}
	
	
	/**
	 * setRateValue
	 * @param {String} index 
	 */
	 function setRateValue(index) 
	 {
		 	document.getElementById("strOfflineTariffRateUnit"+index).value = document.getElementById("strOfflineTempTariffRateUnit"+index).value+" / No."; 
	 		calcOffLineTariffNetAmount(index); 	
	 }
	
	/**
	 * setOnlineOffLineMode
	 * @param {Object} checkObj 
	 */
	 function setOnlineOffLineMode(checkObj) {
	 	
	 	document.forms[0].strCrNo.value = "";
	 	
	 	if(checkObj.checked){
	 		
	 		 		
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
		
		if(divId == 'onLineId'){
		document.forms[0].currentState.value = '1';
		}
		if(divId == 'offLineId'){
		document.forms[0].currentState.value = '2';
		}
			
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
		
		if(divId == 'onLineId'){
		document.forms[0].currentState.value = '2';
		}
		if(divId == 'offLineId'){
		document.forms[0].currentState.value = '1';
		}
				
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
			document.getElementById("specialWardDivId").style.display="block";	
			
					 
						
		}else{
			document.getElementById("wardDivId").style.display="none";
			document.getElementById("specialWardDivId").style.display="none";
			
		}
		
	}

	/**
	 * goFunc - validates the Cr Number Text Box and submit the page with hmode 'GO' if validation succeeds 
	 */
	function goFunc()
	{
		if(document.forms[0].strCrNo.disabled == false)
		{
			var hisValidator = new HISValidator("cashCollectionOfflineTransBean");  
		
			hisValidator.addValidation("strCrNo", "req", "CR No. is a Mandatory Field" );
			hisValidator.addValidation("strCrNo", "minlen="+document.forms[0].strCrNo.maxLength, "CR No. must be "+document.forms[0].strCrNo.maxLength+" Digits" );
		
			var retVal = hisValidator.validate(); 
			hisValidator.clearAllValidations();
			
				if(retVal)
				{
					if(validateFunc(document.forms[0].strCrNo,5))
					{
						document.forms[0].hmode.value="GO";
						document.forms[0].submit();
					}
				}
				else
				{
					return false;
				}
		}
		else
		{
			return false;
		}
	}
	
	/**
	 * clearFunc - clears the Cr Number Text Box and submit the page with hmode 'CANCEL'. 
	 */
	function clearFunc(cntMode){
		
		document.forms[0].strCrNo.value = "";
		document.forms[0].strEpisodeFound.value="";
		document.forms[0].hmode.value="unspecified";
		document.forms[0].submit();
		 
	} 
	
	
	/**
	 * poorFreeCheck
	 * @param {}  
	 */
	 function poorFreeCheck(strCompName) {
	 	//alert(document.forms[0].strOffLineTreatmentCategory.value.split("^")[1]);
		//alert("1");
		 
		 //alert ("2");
		
	 			/*
	 			By: Prakhar Misra date: 12-03-12 made hidden as per RAOL LLD
	 			if(document.getElementsByName(strCompName).length > 0){
				
				if(document.forms[0].strFreeCategory.value == document.getElementsByName(strCompName)[0].value){
					
					var freeCatConf = confirm("The Patient is Poor Free, Click Cancel for Cash Collection.");
					
					
					if(freeCatConf){
						
						clearFunc(document.forms[0].strCounterMode.value);
						
					}
					
					
				}
				
				
			}	*/
//Reset To Cash
	 }
	
	var keyupTimer;
	/**
	 * cancelFunc -to forward page to init page and  submit the page with hmode 'CANCELPAGE'. 
	 */
	function cancelFunc1(){
		
		document.forms[0].hmode.value="CANCELPAGE";
		document.forms[0].submit();
	}

	function chkPaidCredit()
	{
		/*alert("rowLengths"+document.getElementsByName("rowLength2")[0].value);
		alert("strCreditPaymentType"+document.getElementsByName("strCreditPaymentType").length*/
		/*alert("1"+document.forms[0].strOffLineTreatmentCategory.value);
		alert("2"+document.getElementsByName("strCreditPaymentType")[0].value);
		alert("3"+document.getElementsByName("strCreditPaymentType").length);*/
		
		if(document.forms[0].strOffLineTreatmentCategory.value != null && document.forms[0].strOffLineTreatmentCategory.value != "" && document.getElementsByName("strCreditPaymentType")[0].value != null)
			for(var i = 0;i<document.getElementsByName("strCreditPaymentType").length ; i++)
			{
				//alert("4"+document.getElementsByName("strCreditLetterNo")[i].id);
				//alert("5"+document.getElementsByName("strCreditPaymentType")[i].value);
				if(document.forms[0].strOffLineTreatmentCategory.value.split("^")[1] == "3" || document.forms[0].strOffLineTreatmentCategory.value.split("^")[1] == "4")
				{
					//rowLength2	
					if(document.getElementsByName("strCreditLetterNo")[i].id != 'strCreditLetterNo#delIndex#' && document.getElementsByName("strOfflineTariffName")[i].value=="" )
					{
						document.getElementsByName("strCreditPaymentType")[i].value = "11";
						document.getElementsByName("strCreditLetterNo")[i].readOnly = false;
					}
					
				}
				else
				{
					if(document.getElementsByName("strCreditLetterNo")[i].id != 'strCreditLetterNo#delIndex#' && document.getElementsByName("strOfflineTariffName")[i].value=="")
					{
						document.getElementsByName("strCreditPaymentType")[i].value = "10";
						document.getElementsByName("strCreditLetterNo")[i].readOnly = true;
					}
					//document.getElementById("strCreditLetterNo"+i).value = "";
				}
			}
		changePayMode();
			
			
		document.getElementsByName("strOfflinePaymentDtls")[0].value="";
	
	}
	

	function changeComboClass(obj)
	{
		//var list = document.forms[0].strOffLineHospitalService;
		if(obj.value=='4')//
			obj.className='comboRed';
		else
			obj.className='comboNormal';
	}
	function changeEpisodeClass(obj)
	{
		//var list = document.forms[0].strOffLineHospitalService;
		if(obj.value.split('^')[4]=='4')//
			obj.className='comboRed';
		else
			obj.className='comboNormal';
	}
	
	
	function changePayMode()
	{
	 		
		if(document.forms[0].strOfflinePaymentMode!=undefined)
		{
			var treatmentcat = document.getElementsByName("strOffLineTreatmentCategory")[0];
			var paymentMode=document.getElementsByName("strOfflinePaymentMode")[0];
			var paymentModeValue=document.getElementsByName("strOfflinePaymentMode")[0].value;
			var index=treatmentcat.selectedIndex;
			for(i=0; i<paymentMode.options.length;i++)
			{
				if(treatmentcat.options[index].text==paymentMode.options[i].text)
				{
					document.getElementsByName("strOfflinePaymentMode")[0].value=paymentMode.options[i].value;
					break;
				}
				else
				{
					
				//	alert("no mode onLoad");
					if(paymentMode.options.length>1)
						{
					for(j=0;j<paymentMode.options.length;j++)
					{ 
						if(paymentMode.options[j].value.split('#')[0]=="1")
						{		
							document.getElementsByName("strOfflinePaymentMode")[0].value=paymentMode.options[j].value;
							break;
						}
				    }
						}
				
			    }
		    }			
		}			
	 }
	
	function validatePayMode()
	{
	var prim_cat= document.getElementsByName("strOffLineTreatmentCategory")[0].value.split('^')[0]; // document.forms[0].strCatgoryCode.value;
	//alert(prim_cat);  alert(document.getElementsByName("strOffLineTreatmentCategory")[0].value);
	var pay_mode=document.getElementsByName("strOfflinePaymentMode")[0].value;
	
	var strpaymode=document.getElementsByName("strOfflinePaymentMode")[0].value.split('#');
	
	
	var paymode_id=strpaymode[0];
	var paymode_name=strpaymode[1];
	var isbound_flag=strpaymode[2];
	var isbound_catcode=strpaymode[3];
	
	if(isbound_flag=="1")
		{
		if(isbound_catcode==prim_cat)
		return true;
		else
			{
			//	alert("Patient Billing Category and Primary Category are different"); 
			//alert("Payment Mode '"+paymode_name+"' cannot be selected for Patient Category '"+document.getElementById("strCategoryName").innerHTML+"'");
			//alert("Payment Mode '"+paymode_name+"' cannot be selected for Patient Category '"+document.forms[0].strOffLineTreatmentCategory[document.forms[0].strOffLineTreatmentCategory.selectedIndex].text+"'");
			alert("Selected Payment Mode is not applicable for this Patient Billing Category");
			return false;
			}
		}
	else
		return true;
	
	
	}
	
	

	function getPaymentModeByPatCat(){
	//	alert("hii");
		
		// document.forms[0].currentReceiptType.value = document.forms[0].strOffLineRequestType[document.forms[0].strOffLineRequestType.selectedIndex].value;
	//	 document.forms[0].currentBserviceId.value = document.forms[0].strOffLineBillingService[document.forms[0].strOffLineBillingService.selectedIndex].value;
	//	 document.forms[0].currentTreatmentCat.value = document.forms[0].strOffLineRequestType[document.forms[0].strOffLineTreatmentCategory.selectedIndex].value;
		// alert("Request type"+document.forms[0].strOffLineRequestType[document.forms[0].strOffLineRequestType.selectedIndex].value);
		if(document.forms[0].strOffLineRequestType[document.forms[0].strOffLineRequestType.selectedIndex].value=="1")
			{
		 		var hmode = "AJX_PAYMENTMODEBYPATCAT"; 
				var url = "CashCollectionOfflineTransBSCNT.cnt?hmode="+hmode+"&hService="+document.forms[0].strOffLineHospitalService[document.forms[0].strOffLineHospitalService.selectedIndex].value+"&requestType="+document.forms[0].strOffLineRequestType[document.forms[0].strOffLineRequestType.selectedIndex].value+"&patCategory="+document.forms[0].strOffLineTreatmentCategory[document.forms[0].strOffLineTreatmentCategory.selectedIndex].value;
				ajaxFunction(url,"33");
			}
		
		
		if(document.forms[0].strOffLineRequestType[document.forms[0].strOffLineRequestType.selectedIndex].value=="2")
		{
	 		var hmode = "AJX_PAYMENTMODEBYPATCAT"; 
	 		//alert(document.forms[0].strOfflineRefundPatCat.value);
	 		//alert(document.forms[0].strOfflineRefundPayMode.value);
	 		
	 		
	 		//alert(document.getElementsByName("strOfflineRefundPatCat")[0].value);
	 		//alert(document.getElementsByName("strOfflineRefundPayMode")[0].value);
	 		
			var url = "CashCollectionOfflineTransBSCNT.cnt?hmode="+hmode+"&hService="+document.forms[0].strOffLineHospitalService[document.forms[0].strOffLineHospitalService.selectedIndex].value+"&requestType="+document.forms[0].strOffLineRequestType[document.forms[0].strOffLineRequestType.selectedIndex].value+"&recieptPatCategory="+document.getElementsByName("strOfflineRefundPatCat")[0].value+"&patReceiptPayMode="+  document.getElementsByName("strOfflineRefundPayMode")[0].value;
			ajaxFunction(url,"33");
			
			
			
		}
	}

	function getOnloadPaymentMode()
	{
	//	alert(document.forms[0].strOffLineRequestType[document.forms[0].strOffLineRequestType.selectedIndex].value);
		
		//if(document.forms[0].strOffLineRequestType[document.forms[0].strOffLineRequestType.selectedIndex].value=="2")
		//	getPaymentModeByPatCat();
			
		//else 
		setDefaultPaymentMode();
	}
	
	function setDefaultPaymentMode()
    {
		
		  var defaultPaymentMode = "0";
    	if(document.forms[0].strOfflinePaymentMode!=undefined)
		{
			
			var paymentMode=document.getElementsByName("strOfflinePaymentMode")[0];
			var paymentModeValue=document.getElementsByName("strOfflinePaymentMode")[0].value;
			
									
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
		document.getElementsByName("strOfflinePaymentMode")[0].value=defaultPaymentMode;
		//alert(document.getElementsByName("strOfflinePaymentMode")[0].value);
    }
	
	
	/**
	 * onLoadLogics - function invokes at the time of body load. 
	 * 				  	 
	 */
	function onLoadLogics()
	{	
		
		var tmpstat="", pataccstatus="";
		 
	 	if(document.forms[0].strAdmissionDtlHidVal != undefined)
	 	{	
	  		if(document.forms[0].strAdmissionDtlHidVal.value !=null && document.forms[0].strAdmissionDtlHidVal.value !="")
	  		{
	 		 tmpstat=document.forms[0].strAdmissionDtlHidVal.value.split("^");
		     if(tmpstat.length>16)
			 pataccstatus= document.forms[0].strAdmissionDtlHidVal.value.split("^")[17]
		    // alert(pataccstatus);
	  		}
		}  // added for : no cash collection after ipd bill finalize by manisha dt:12.3.19  
	 	
		
		/* strVisitType=1-OPD Normal 2-Emg MLC 3-EMG Non MLC 4-Special Clinic*/
		if(document.getElementsByName("strOffLineHospitalService")[0].value!= "2" && document.forms[0].strPatientDtlHidVal!=undefined && document.forms[0].strPatientDtlHidVal.value.split('^')[2]=="2")
		{
			alert("The patient is admitted. This hospital service can't be availed.");
			return false;	
		}
		
		
	 	if(pataccstatus!="" && pataccstatus!=null && (pataccstatus=="0" || pataccstatus=="5"))
		{
	 		alert("Bill is finalized. This hospital service can't be availed.");
			return false;	
			
		}
		
		if(document.forms[0].isOpenPopUp.value == "0")
		{
			if(document.getElementsByName("strVisitType")!=undefined && document.getElementsByName("strVisitType")[0].value != '' && document.getElementsByName("strVisitType")[0].value != null && document.getElementsByName("strOffLineHospitalService")!=null && document.getElementsByName("strOffLineHospitalService")[0].value == "4" )			
			{
				if(document.getElementsByName("strVisitType")[0].value != "4")
				{
					document.forms[0].strAlertPromptConfirmFlag.value="1";
					if(!confirm("Episode details for OPD special clinic not found. Do you want to proceed ??"))
						return false;					
				}
			}		
			if(document.getElementsByName("strVisitType")[0].value != '' && document.getElementsByName("strVisitType")[0].value != null && document.getElementsByName("strOffLineHospitalService")[0].value == "3" )			
			{
				if(document.getElementsByName("strVisitType")[0].value == "1" || document.getElementsByName("strVisitType")[0].value == "4")
				{
					document.forms[0].strAlertPromptConfirmFlag.value="1";
					if(!confirm("Episode details for Emergency not found. Do you want to proceed ??"))
					{
						return false;					
					}
						
				}			
			}
			if(document.forms[0].strOffLineTreatmentCategory.value != null && document.forms[0].strOffLineTreatmentCategory.value != "" && document.getElementsByName("strOffLineHospitalService")[0].value != "2")
			{
				//if(document.forms[0].strOffLineTreatmentCategory.value.split("^")[1] == "4")
				if(document.forms[0].strOffLineTreatmentCategory.value.split("^")[0] == document.forms[0].strArogyaCatId.value)
				{
					document.forms[0].strAlertPromptConfirmFlag.value="1";
					if(!confirm("No Direct Billing Allowed for Arogya Shree AP(Dr. NTR VaidyaSevaTruct (DrNTRVST) AP) Patient. Do you want to proceed ??"))
					return false;
				}
			}			
			//Check Same Condition Again because patient might have changed his treatment category to WCH (Arogya To WCH COnversion-Online Billing).Now Check on Primary Category
			if(document.forms[0].strCatgoryCode!=undefined && document.forms[0].strCatgoryCode.value != null && document.forms[0].strCatgoryCode.value != "" && document.getElementsByName("strOffLineHospitalService")[0].value != "2")
			{
				if(document.forms[0].strCatgoryCode.value == document.forms[0].strArogyaCatId.value)
				{
					document.forms[0].strAlertPromptConfirmFlag.value="1";
					if(!confirm("No Direct Billing Allowed for Arogya Shree AP(Dr. NTR VaidyaSevaTruct (DrNTRVST) AP) Patient. Do you want to proceed ??"))
					return false;
				}
			}
		/*	if(document.forms[0].strEpisodeFound!=undefined && document.forms[0].strEpisodeFound.value != null && document.forms[0].strEpisodeFound.value != "")			
			{
				if(document.forms[0].strEpisodeFound.value != "1")
				{
					document.forms[0].strAlertPromptConfirmFlag.value="1";
					if(!confirm("No Episode Found. Do you want to proceed ??"))
					{
						document.forms[0].strAlertPromptConfirmFlag.value="1";
						return false;					
					}
				}
			}*/
		}
		
			
		document.getElementsByName("strOfflinePaymentDtls")[0].value="";
	
			if(document.getElementsByName("strOffLineHospitalService")[0].value!= "2" && document.forms[0].strWalletNo.value!=null && document.forms[0].strWalletNo.value!="" )//If Not IPD and Wallet No Present
			 {
				 if(parseInt(document.forms[0].strAvlWalletMoney.value)>0 )//Avalable Wallet Money Present
				 {
					 if(parseInt(document.forms[0].strAvlWalletMoney.value)>parseInt(document.forms[0].strOfflineTotalRecAmount.value))
					 {
						
						 // document.getElementsByName("strOfflinePaymentMode")[0].value="7";//Reset To WaLLET
						 var paymentMode=document.getElementsByName("strOfflinePaymentMode")[0];
						 for(j=0;j<=paymentMode.options.length;j++)
							{ 
								if(paymentMode.options[j].value.split('#')[0]=="7")
								{		
							document.getElementsByName("strOfflinePaymentMode")[0].value=paymentMode.options[j].value;  //Reset To WaLLET
							break;
								}
						    }
						 
						 document.getElementsByName("strOfflinePaymentDtls")[0].value="Wallet No.: "+document.forms[0].strWalletNoMasked.value;
						 //document.getElementsByName("imgPatientWallet")[0].style.display = "";
						 //document.getElementsByName("imgPatientWallet")[1].style.display = "";
					 }
				 }
			 }
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
					document.forms[0].strCrNo.focus();	
					return false;				
			}
			//To Set Red Color in OPD Spl in Hospital Service Combo 
			var list = document.forms[0].strOffLineHospitalService;
			if(list.value=='4')//
				document.forms[0].strOffLineHospitalService.className='comboRed';
		     for(var i = 0; i < list.options.length; ++i)
		     {
		    	 var values=list.options[i].value;
		    	 if(values=='4')
		    	 {		    	
		    		 list.options[i].className='important';
		    	 	 //document.forms[0].strOffLineHospitalService.className='comboRed';
		    	 }
		    	 else
		    		 list.options[i].className='noneimportant';
		    	 
		     }
		     var list = document.forms[0].strOffLineEpisode;
		     for(var i = 0; i < list.options.length; ++i)
		     {
		    	 var values=list.options[i].value;
		    	 if(values.split('^')[4]=='4')
		    		 list.options[i].className='important';
		    	 else
		    		 list.options[i].className='noneimportant';
		    	 
		     }
			
			
			var normalMsgVal = document.getElementById("normalMsg").innerHTML;	
				
			if(normalMsgVal.length > 1 && document.forms[0].strCrNo.disabled == false)
			{
				if(normalMsgVal.search(document.forms[0].strTempBillNo.value) != -1)
				{
				 		//var confirmFlag = confirm("Whether Receipt Printed Successfully?");
						var confirmFlag = true;
						if(!confirmFlag)//Cancel
						{
							var printLimit = document.forms[0].strPrintMessageLimit.value;					
							if(printLimit.length <0) printLimit = "0";						
							printLimit = parseInt(printLimit);
							
							if(printLimit > 0)
							{
								gblPrintLimitVal = printLimit;																
								reprintContents();
							}
							else
							{
								var billNo = document.forms[0].strTempBillNo.value;
								var recNo  = document.forms[0].strTempReceiptNo.value;											
								var hmode = "UPDATEPRINTSTATUS"; 
								var url = "CashCollectionOfflineTransBSCNT.cnt?hmode="+hmode+"&billNo="+billNo+"&recNo="+recNo;
																
								ajaxFunction(url,"1","updateStatusResponse");
							}											
						}
						document.forms[0].strCrNo.focus();	
						document.forms[0].strCrNo.select();
						//clearTimeout(keyupTimer);
						//keyupTimer = setTimeout("showNormalMsg()",3000); 
						return false;										
				}				
			}
				
			if(document.forms[0].strCrNo.value.length == document.forms[0].strCrNo.maxLength )
			{				
				document.forms[0].strCrNo.disabled = true;				
			}
				
				// confirms whether to continue for bill the poorfree or to cancel the process.
				//poorFreeCheck('strCatgoryCode');
					
						
		if(document.forms[0].strCrNo.disabled == false)
		{
			document.getElementById("offlineDetailsDivId").style.display = "none";			
			document.forms[0].strCrNo.focus();
			document.forms[0].strCrNo.select();			
			
			if(errVal.search("CR.") != -1) 
			return false;			
		}
		else
		{
			document.getElementById("offlineDetailsDivId").style.display = "block";
			document.getElementById("savebutton").style.display = "block";
			document.getElementById("goid").style.display = "none";
			
			//document.getElementById("disBnR").style.display='block';
			
			
			 /*if(document.forms[0].strOffLineBillingService[document.forms[0].strOffLineBillingService.selectedIndex].value == '10' &&
			 document.forms[0].strOffLineRequestType[document.forms[0].strOffLineRequestType.selectedIndex].value == '1')
			 {
				 document.getElementById("disBnR").style.display='block';
			 }*/

				
			document.forms[0].strOffLineHospitalService.disabled = true;	
			document.forms[0].strOffLineBillingService.disabled = true;
			document.forms[0].strOffLineRequestType.disabled = true;				
							
			/*if(document.getElementsByName("strCatgoryCode").length > 0)
			{								
				document.forms[0].strOffLineTreatmentCategory.value = document.forms[0].strCatgoryCode.value;							 
			}*/
			
			getPartAccDtls(document.getElementsByName("strOfflineTariffName")[0]);
			
			wardDisplayStatus(document.forms[0].strOffLineHospitalService[document.forms[0].strOffLineHospitalService.selectedIndex]);
						 
			document.getElementsByName("strTariffCode")[0].focus();
		}	
		document.forms[0].strMobileNo.value=document.getElementsByName("strPatientDtlHidVal")[0].value.split("^")[13];
		document.forms[0].strCrNo.focus();
		document.forms[0].strCrNo.select();
		SetCursorToTextEnd('strCrNoId');
		
		//strOffLineHospitalService  strOffLineBillingService  strOffLineRequestType
		if(document.forms[0].strOffLineHospitalService.value == "2"  && document.forms[0].strOffLineBillingService.value == "19" && document.forms[0].strOffLineRequestType.value == "2")
			{
				advanceRefund();
			}
}
		
		
function showNormalMsg()
{
			document.getElementById("normalMsg").style.display="block";//showing normal msg
}
	
	var gblPrintLimitVal = 0;
	
	
	/**
	 * reprintContents
	 */
	 function reprintContents() 
	 {	 		 	
	 		if(gblPrintLimitVal != 0)
	 		{	 			
	 			var hmode = "REPRINT"; 
				var url = "CashCollectionOfflineTransBSCNT.cnt?hmode="+hmode;											
				ajaxFunction3(url,"1","rePrintStatus");	 			
	 		}
	 		else
	 		{
	 			var billNo = document.forms[0].strTempBillNo.value;
				var recNo  = document.forms[0].strTempReceiptNo.value;							
				var hmode = "UPDATEPRINTSTATUS"; 
				var url = "CashCollectionOfflineTransBSCNT.cnt?hmode="+hmode+"&billNo="+billNo+"&recNo="+recNo;
												
				ajaxFunction3(url,"1","updateStatusResponse");
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
		if(!confirmFlag)//Cancel
		{
			reprintContents();
		}
	 }			
				
	 
		
		
	function getBillService(){
				
	 document.forms[0].currentReceiptType.value = document.forms[0].strOffLineRequestType[document.forms[0].strOffLineRequestType.selectedIndex].value;
	 document.forms[0].currentBserviceId.value = document.forms[0].strOffLineBillingService[document.forms[0].strOffLineBillingService.selectedIndex].value;
	 
	 
	 	if(document.forms[0].currentReceiptType.value == '2'){
	 		
	 		document.getElementById("offlineDepEpdTreatWardDivId").style.display = "none";	 		
	 		
	 	}else{
	 		document.getElementById("offlineDepEpdTreatWardDivId").style.display = "block";
	 		
	 	}
	 
	 
			var hmode = "BILLDTLS"; 
			var url = "CashCollectionOfflineTransBSCNT.cnt?hmode="+hmode+"&hService="+document.forms[0].strOffLineHospitalService[document.forms[0].strOffLineHospitalService.selectedIndex].value+"&requestType="+document.forms[0].strOffLineRequestType[document.forms[0].strOffLineRequestType.selectedIndex].value;
						
			ajaxFunction(url,"2");
	}
	
	function getEpisodeList(obj){
		
		
			var hmode = "EPISODEDTLS"; 
			var url = "CashCollectionOfflineTransBSCNT.cnt?hmode="+hmode+"&crNo="+document.forms[0].strCrNo.value+"&deptCode="+obj.value;
			ajaxFunction(url,"3");
		
	}
	
		
	
function getPartAccDtls(obj)
{
		var bServiceId = document.forms[0].strOffLineBillingService[document.forms[0].strOffLineBillingService.selectedIndex].value;	
		var reqType  = document.forms[0].currentReceiptType.value = document.forms[0].strOffLineRequestType[document.forms[0].strOffLineRequestType.selectedIndex].value;;
		document.forms[0].currentBserviceId.value = obj.value;
	
		if(bServiceId == '19' && bServiceId == '2')
		{
				strPatStatus = document.forms[0].strPatientDtlHidVal.value.split('^')[2];
				if(strPatStatus == '2')
				{
					document.getElementById("errMsg").innerHTML = "Patient Not Admitted";
					document.getElementById("offlineBillDetailsDivId").style.display = "none";
					document.getElementById("offlineBillTariffDivId").style.display = "none";	
					document.getElementById("otherRefundDetailsDivId").style.display = "none";
					return false;					
				}				
		}
		var hmode = "PARTACCAMT";	
		var wardId = "0";
		var splWardId = "0"; 
	
	if(document.forms[0].strOffLineHospitalService[document.forms[0].strOffLineHospitalService.selectedIndex].value == 2)
	{
		wardId =  document.getElementsByName("strOffLineWard")[0].value;
		splWardId =  document.getElementsByName("strOffLineSpecialWard")[0].value;	
		var url = "CashCollectionOfflineTransBSCNT.cnt?hmode="+hmode+"&hService="+document.forms[0].strOffLineHospitalService[document.forms[0].strOffLineHospitalService.selectedIndex].value+"&requestType="+document.forms[0].strOffLineRequestType[document.forms[0].strOffLineRequestType.selectedIndex].value+"&billService="+bServiceId+"&treatmentCat="+document.getElementsByName("strOffLineTreatmentCategory")[0].value+"&ward="+wardId+"&strCrNo="+document.forms[0].strCrNo.value+"&strSplWard="+splWardId;
		ajaxFunction(url,"4");
	}
	else
	{
			getOffLineGroupDtls();
	}
}
	
	function getOffLineGroupDtls()
	{
		var hmode = "GROUPDTLS"; 
		var url = "CashCollectionOfflineTransBSCNT.cnt?hmode="+hmode+"&hService="+document.forms[0].strOffLineHospitalService[document.forms[0].strOffLineHospitalService.selectedIndex].value+"&withPack=0&catCode="+document.forms[0].strOffLineTreatmentCategory.value+"&strCrNo="+document.forms[0].strCrNo.value;		
		ajaxFunction(url,"5");
	}
	
	function getOffLineClientDtls()
	{	
		var hmode = "OFFLINECLTDTLS"; 
		var url = "CashCollectionOfflineTransBSCNT.cnt?hmode="+hmode+"&crNo="+document.forms[0].strCrNo.value+"&hService="+document.forms[0].strOffLineHospitalService[document.forms[0].strOffLineHospitalService.selectedIndex].value;		
		ajaxFunction(url,"6");
	}
	
 
	var trfdelIndex ;
	var trfObj;
	var trfPreviousData = "";
	var tempCode = "";
	
	  	
function getTariffDtls(obj , eve , delIndex)
{
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
 
	var temp = document.getElementById("strOfflineTariffDetailsHidden"+delIndex).value.split('^');
	
	if(temp[17] !='1')
	{
	
 if(tempCode == 222)
 { 			
 			var input = document.getElementById('strOfflineTariffName'+delIndex).value;
 			document.getElementById('strOfflineTariffName'+delIndex).value = input.substring(0,input.length-1);
 			return false;
 }
 
//alert("val : "+obj.value);

	if(obj.value.length >= 1 && obj.value.length <= 3 && trfPreviousData != previousDate)
	{
		var hmode = "TARIFFDTLS"; 
		var url = "";	
	
		if(document.getElementById("wardDivId").style.display == "block")
		{		
			var wardIdVal = document.forms[0].strOffLineWard[document.forms[0].strOffLineWard.selectedIndex].value.split('^')[0];
			var treatmentCat=document.forms[0].strOffLineTreatmentCategory[document.forms[0].strOffLineTreatmentCategory.selectedIndex].value.split('^')[0]; 
			url = "CashCollectionOfflineTransBSCNT.cnt?hmode="+hmode+"&groupId="+document.forms[0].strOffLineGroup.value+"&hService="+document.forms[0].strOffLineHospitalService.value+"&treatmentCat="+treatmentCat+"&ward="+wardIdVal+"&searchLetter="+obj.value+"&bService="+document.forms[0].strOffLineBillingService.value;	 
		}
		else
		{
			var treatmentCat=document.forms[0].strOffLineTreatmentCategory[document.forms[0].strOffLineTreatmentCategory.selectedIndex].value.split('^')[0]; 
			url = "CashCollectionOfflineTransBSCNT.cnt?hmode="+hmode+"&groupId="+document.forms[0].strOffLineGroup.value+"&hService="+document.forms[0].strOffLineHospitalService.value+"&treatmentCat="+treatmentCat+"&ward=0"+"&searchLetter="+obj.value;
		}
	
		//alert("tariff url : "+url);
	
		ajaxFunction(url,"7");
		
	}
	else
	{	
		if(document.getElementById("dropdown1").innerHTML.length <=0)
		{		
				var input = document.getElementById('strOfflineTariffName'+delIndex).value;
				document.getElementById('strOfflineTariffName'+delIndex).value = input.substring(0,input.length-1);
				return false;
		}	
		searchSel(eve,'strOfflineTariffName'+delIndex,'1',obj);		
	}
	}
}
	
	
	
	
	function getOffLinePkgsGroupDtls(){
				
		var hmode = "PKGGROUPDTLS"; 
			var url = "CashCollectionOfflineTransBSCNT.cnt?hmode="+hmode+"&hService="+document.forms[0].strOffLineHospitalService[document.forms[0].strOffLineHospitalService.selectedIndex].value+"&withPack=1";
		
			ajaxFunction(url,"8");
	}
	
	
	function getOffLinePkgConfigDetails(index){
	
		if(document.forms[0].strPkgServiceFlag.value == 0){
	
		flgObj = document.getElementById("flag"+index);
		
		if(flgObj.value == 0){
	
		var hmode = "PKGDTLS"; 
			var url = "CashCollectionOfflineTransBSCNT.cnt?hmode="+hmode+"&rowIndex="+index;
		
			ajaxFunction(url,"9");
			
		}else{
		
		pkgDivObj = document.getElementById("pkgConfigId"+index);
		pkgDivObj.style.display = "block";
		
		}
		}else{
		
		tempObj = document.getElementsByName("flag");
			
		if((tempObj.length-1) > 1)
			alert("Please Add or Cancel the Exiting Package Configuration");
		}
		
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
			var url = "CashCollectionOfflineTransBSCNT.cnt?hmode="+hmode+"&unitId="+unitId+"&baseValue="+baseValue+"&rowIndex="+index;
			
			ajaxFunction(url,"10");
		 	}else{
		 		document.getElementById("offlineTariffUnitDivId"+index).innerHTML = "<select name='strOfflineTariffUnit' id='strOfflineTariffUnit"+index+"' class='comboMin' onChange='calcOffLineTariffNetAmount(\""+index+"\");'><option value=0>Select Value</option></select>";				
		 			 		
		 		calcOffLineTariffNetAmount(index);
		 	}
		 }
	
	
	/**
	 * getOffLineRefundBillDetails 
	 */
	 function getOffLineRefundBillDetails() {
	 		 
	 		var accNo = "0";
	 		
	 		if(document.forms[0].strOffLineAccountNo != null){
	 			
	 			accNo = document.forms[0].strOffLineAccountNo.value;
	 		}
	 		
	 		 
	 	var hmode = "REFUNDBILLDTLS"; 
			var url = "CashCollectionOfflineTransBSCNT.cnt?hmode="+hmode+"&crNo="+document.forms[0].strCrNo.value+"&chargeType="+document.forms[0].strOffLineHospitalService[document.forms[0].strOffLineHospitalService.selectedIndex].value+"&accNo="+accNo;
			
			ajaxFunction(url,"11");
	 	
	 	
	 }
	
	
	function getOffLinePartPayRefundBillDetails() {
	 		 
	 		var accNo = "0";
	 		
	 		if(document.forms[0].strOffLineAccountNo != null){
	 			
	 			accNo = document.forms[0].strOffLineAccountNo.value;
	 		}
	 		
	 		 
	 	var hmode = "REFUNDPARTPAYBILLDTLS"; 
			var url = "CashCollectionOfflineTransBSCNT.cnt?hmode="+hmode+"&crNo="+document.forms[0].strCrNo.value+"&chargeType="+document.forms[0].strOffLineHospitalService[document.forms[0].strOffLineHospitalService.selectedIndex].value+"&accNo="+accNo;
			
			ajaxFunction(url,"30");
	 	
	 	
	 }
	
	var gblResVal = "";
	
	/**
	 * advanceAndPartPaymentLogics
	 * @param {}  
	 */
	 function advanceAndPartPaymentLogics(res) {
	 	
	 	
			var amtObj = document.getElementById("partPayAdvanceDivId");
			
			amtObj.style.display = "none";
				amtObj.innerHTML = "";
			

			var refAdv = document.getElementById("offlineAdmissionCancellationDivId");
			
			var reqType = document.forms[0].strOffLineRequestType[document.forms[0].strOffLineRequestType.selectedIndex].value;
			var billSer = document.forms[0].strOffLineBillingService[document.forms[0].strOffLineBillingService.selectedIndex].value;
			

			
			var flg = false;
			var flg1 = true;		
			
				if(reqType == '1' &&  (billSer == '19' || billSer == '20' || billSer == '13')){
						
											
					if(billSer == '19'){
						 	
													
						var accNoObj = document.getElementsByName("strOffLineAccountNo");
						
							var accNo = '0';
						
						
						
						if(accNoObj.length > 0){
							accNo = accNoObj[0].value;
						}
									
									
						
																				
						if(accNo.length > 1 ){
								flg1 = false;
						}
							
						if(flg1){
							
							amtObj.innerHTML = res;	
							if(res.length > 0){
							amtObj.style.display="block";
							}else{
							amtObj.style.display="none";
							} 
							
							setAdvPartAmt();
							setAdvPartPayRemarksText();
							
						}else{
							amtObj.innerHTML = "";
							amtObj.style.display="none";
							document.getElementById("errMsg").innerHTML = "Advance Already Collected.";
							document.getElementById("errMsg").style.display = "block";
													
							document.getElementById("totalRecAmtDivId").innerHTML = '0.00';	
							document.getElementById("totalRecAmtLabId").innerHTML = '0.00';
							document.getElementById("totalCltAmtLabId").innerHTML = '0.00';
	  						document.forms[0].strOfflineTotalRecAmount.value = 0;
					
					
						}
							
					}
						
					if(billSer == '20' || billSer == '13'){
							
														
						var accNoObj = document.getElementsByName("strOffLineAccountNo");
						
							var accNo = '0';
						
						
						
						if(accNoObj.length > 0){
							accNo = accNoObj[0].value;
						}
										
									
																						
						if(accNo.length > 1 ){
								flg = true;
						}
							
						if(flg){
							
							if(billSer == '20')
							{					
							amtObj.innerHTML = res;		
							if(res.length > 0){
							amtObj.style.display="block";
							}else{
							amtObj.style.display="none";
							}
							
							setAdvPartAmt();
							
							setAdvPartPayRemarksText();
							}
							
							if(billSer == '13')
							{
	  							if(document.forms[0].strPatientDtlHidVal.value.split('^')[2]!="2")
	  							{
	  								amtObj.innerHTML = "";
	  								amtObj.style.display="none";
	  								document.getElementById("errMsg").innerHTML = "Patient Not Admitted, Package Not Applicable.";
	  								document.getElementById("errMsg").style.display = "block";
	  								document.getElementById("totalRecAmtDivId").innerHTML = '0.00';	
	  								document.getElementById("totalRecAmtLabId").innerHTML = '0.00';	
	  								document.getElementById("totalCltAmtLabId").innerHTML = '0.00';	
	  		  						document.forms[0].strOfflineTotalRecAmount.value = 0;
	  							}
							}
							
						}else{
							amtObj.innerHTML = "";
							amtObj.style.display="none";
							document.getElementById("errMsg").innerHTML = "Account Not Opened, Part Payment Not Applicable.";
							document.getElementById("errMsg").style.display = "block";
							document.getElementById("totalRecAmtDivId").innerHTML = '0.00';	
							document.getElementById("totalRecAmtLabId").innerHTML = '0.00';	
							document.getElementById("totalCltAmtLabId").innerHTML = '0.00';	
	  						document.forms[0].strOfflineTotalRecAmount.value = 0;
	  						
	  													
						}
					}
						
						
				refAdv.style.display="none";		
			 	document.getElementById("otherRefundDetailsDivId").style.display = "none"; 						
				}else{
					
					 
						
					if(reqType == '2' &&  (billSer == '19' || billSer == '20')){
					
						
						refAdv.style.display="none";
						
						
					}else{
						
						refAdv.style.display="none";
					
					}
						
					amtObj.style.display="none";
				}
					
							


			document.getElementById("offlineBillDetailsDivId").style.display = "none";
			document.getElementById("offlineBillTariffDivId").style.display = "none";	
			 
	 	
	 }
	
	
	
	/**
	 * getBillTariffDtls
	 * @param {Object} obj -Radio Object
	 */
	 function getBillTariffDtls(obj) {
	 	
	 	if(obj == null){
	 		
	 		tempObj = document.getElementsByName("strOfflineRefundBillDetails");
	 		
	 		if(tempObj.length > 0)
	 			obj = tempObj[0];
	 		
	 	}
	 		 	
	 	var tempVal = obj.value.split('^'); 
	 	
	 	
	 	var hmode = "REFUNDBILLTARIFFDTLS"; 
			var url = "CashCollectionOfflineTransBSCNT.cnt?hmode="+hmode+"&billNo="+tempVal[0]+"&accNo="+tempVal[4]+"&hospService="+document.forms[0].strOffLineHospitalService[document.forms[0].strOffLineHospitalService.selectedIndex].value;
			
		
			ajaxFunction(url,"12");
			
		
			
	 }
	 
	 
	 /**
	 * getPartPayBillTariffDtls
	 * @param {Object} obj -Radio Object
	 */
	 function getPartPayBillTariffDtls(obj) {
	 	
	 	if(obj == null){
	 		
	 		tempObj = document.getElementsByName("strOfflineRefundBillDetails");
	 		
	 		if(tempObj.length > 0)
	 			obj = tempObj[0];
	 		
	 	}
	 		 	
	 	var tempVal = obj.value.split('^'); 
	 	
	 	
	 	var hmode = "REFUNDPARTPAYBILLTARIFFDTLS"; 
			var url = "CashCollectionOfflineTransBSCNT.cnt?hmode="+hmode+"&billNo="+tempVal[0]+"&accNo="+tempVal[4]+"&hospService="+document.forms[0].strOffLineHospitalService[document.forms[0].strOffLineHospitalService.selectedIndex].value;
			
		
			ajaxFunction(url,"29");
	 }
	
	 var gblPopupParent = "";
	 
	 /**
	  * showTariffPopup
	  * @param {Link Object} parent 
	  */
	  function showTariffPopup(obj,hidVal) {
	    	
	  	gblPopupParent = obj;
	  	
	  	var hmode = "REFUNDBILLTARIFFPOPUPDTLS"; 
			var url = "CashCollectionOfflineTransBSCNT.cnt?hmode="+hmode+"&hidValue="+hidVal;

			ajaxFunction(url,"13");
	  }
	  
	   
	
	 function showReconcilPopup(hidVal,parentPos){
	 		 	
			var hmode = "RECONCILPOPUP"; 
			var url = "CashCollectionOfflineTransBSCNT.cnt?hmode="+hmode+"&hidVal="+hidVal;
		
		gblPopupParent = parentPos;
			
			ajaxFunction(url,"20");
	}
	  
	 
	
	/**
	 * getOfflineThirdPartyAmtView
	 * 
	 */
	 function getOfflineThirdPartyAmtView() {
	 	 	
	 	 	
	 	 	var clientDtlStatus = document.getElementsByName("strOfflineClientDetails");
	 		var clientStatus = "0";
	 		
	 		if(clientDtlStatus.length > 0){
	 			clientStatus = "1";
	 		}
	 		
	 		var chargeTypeId = document.forms[0].strOffLineHospitalService[document.forms[0].strOffLineHospitalService.selectedIndex].value;
	 		var reqType = document.forms[0].strOffLineRequestType[document.forms[0].strOffLineRequestType.selectedIndex].value;
			var billSer = document.forms[0].strOffLineBillingService[document.forms[0].strOffLineBillingService.selectedIndex].value;
		
	 		
	 	/* 	
	 	var hmode = "OFFLINETHIRDPARTYAMTDTL"; 
			var url = "CashCollectionOfflineTransBSCNT.cnt?hmode="+hmode+"&strChargeTypeId="+document.forms[0].strOffLineHospitalService.value+"&strRequestTypeId="+document.forms[0].strOffLineRequestType[document.forms[0].strOffLineRequestType.selectedIndex].value+"&strBServiceId="+document.forms[0].strOffLineBillingService[document.forms[0].strOffLineBillingService.selectedIndex].value+"&clientStatus="+clientStatus;;
															
			ajaxFunction(url,"22");
	 	*/
	 	
	 	
		 	var opdThirdPartyBilling = document.forms[0].strOpdThirdPartyBenefit.value;
			var ipdThirdPartyBilling = document.forms[0].strIpdThirdPartyBenefit.value;
			var epdThirdPartyBilling = document.forms[0].strEmergencyThirdPartyBenefit.value;
					
	 	
		 		if (chargeTypeId == '2' && (billSer == "10" || billSer == "21" ) && clientStatus == '1') {
		 			
					// whether request type : 1 - Receipt & bill service : 10 - Service
					if (reqType == "1" && billSer == "10"){
						
						document.getElementById("offlineGrandTotalDivPartId").style.display = "block";
						document.getElementById("offlineMaxClientBenefitDivPartId").style.display = "block";
						document.getElementById("offlinePatientPayableDivPartId").style.display = "block";
						
					}else{
					
						document.getElementById("offlineGrandTotalDivPartId").style.display = "none";
						document.getElementById("offlineMaxClientBenefitDivPartId").style.display = "none";
						document.getElementById("offlinePatientPayableDivPartId").style.display = "none";
					}
			 	
		 	}
	
	}
	 
	 
	 
	 
	 
	 /**
	  * initOfflineTreatmentCategory 
	  */
	  function initOfflineTreatmentCategory() {
	  	
	  	 var chargeTypeId = document.forms[0].strOffLineHospitalService[document.forms[0].strOffLineHospitalService.selectedIndex].value;
	  		 var crNo = document.forms[0].strCrNo.value;
	  		 var treatCat = "0";
	  		 
	  		 if(document.getElementsByName("strAccTreatCat").length > 0){
	  		 	
	  		 	treatCat = document.getElementsByName("strAccTreatCat")[0].value;
	  		 	
	  		 }
	  		 
	  		 
	  		 
	  		 	var hmode = "OFFLINETREATCATDTL"; 
			var url = "CashCollectionOfflineTransBSCNT.cnt?hmode="+hmode+"&strChargeTypeId="+chargeTypeId+"&strCrNo="+crNo+"&strTreatCat="+treatCat;
															
			ajaxFunction(url,"24"); 
	  	
	  }
	 	
	  /**
	  * initOfflineWard 
	  */
	  function initOfflineWard() {
	  	
	  		 var chargeTypeId = document.forms[0].strOffLineHospitalService[document.forms[0].strOffLineHospitalService.selectedIndex].value;
	  		 	  		 
	  		 var crNo = document.forms[0].strCrNo.value;
	  		    var strWard = "0";
	  		 
	  		 if(document.getElementsByName("strAccChargeType").length > 0){
	  		 	
	  		 	strWard = document.getElementsByName("strAccChargeType")[0].value;
	  		 	
	  		 }		 
	  		   		 
	  		   		 
	  		 	var hmode = "OFFLINEWARDDTL"; 
			var url = "CashCollectionOfflineTransBSCNT.cnt?hmode="+hmode+"&strChargeTypeId="+chargeTypeId+"&strCrNo="+crNo+"&strWard="+strWard;
															
			ajaxFunction(url,"25"); 
	  	
	  }
	 
	 
	 /**
	  * getSpecialWardDtls
	  * @param {String} deptId 
	  */
	  function getSpecialWardDtls(deptId) {
	 	  	
	  		var hmode = "OFFLINESPLWARDDTL"; 
			var url = "CashCollectionOfflineTransBSCNT.cnt?hmode="+hmode+"&strDept="+deptId;
															
			ajaxFunction(url,"28"); 
	  	
	  	
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
	document.forms[0].currentBserviceId.value  = document.forms[0].strOffLineBillingService[document.forms[0].strOffLineBillingService.selectedIndex].value;
	document.forms[0].currentHospService.value = document.getElementsByName("strOffLineHospitalService")[0].value;
  // alert(mode);
    if(temp[0] == "ERROR")
    {
      	err.innerHTML = temp[1];
      	err.style.display = "block";
      	return;
    }
    if(mode == '2')
    {
			billObj = document.getElementById("billServiceDivId");
			billObj.innerHTML = "<select class='comboNormal' name='strOffLineBillingService' onchange='resetTotalAmount(this);'>"+res+"</select>";
			//	getPartAccDtls(document.forms[0].strOffLineBillingService[document.forms[0].strOffLineBillingService.selectedIndex]);		
	}
    
    if(mode == '33')
    {
    	 
			//alert(res);
			document.getElementsByName("strOfflinePaymentMode")[0].innerHTML =res;
			setDefaultPaymentMode();
			
		
			
	}
    
	if(mode == '3')
	{
			temp = res.split("@");
			episodeObj = document.getElementById("episodeDivId");
			episodeObj.innerHTML = "<select class='comboNormal' name='strOffLineEpisode' onchange='changeEpisodeClass(this)'>"+temp[0]+"</select>";
	 		var bServId = document.forms[0].strOffLineBillingService[document.forms[0].strOffLineBillingService.selectedIndex].value;
	 		if(bServId == '19' || bServId == '20')
	 		{
		 			getSpecialWardDtls(document.forms[0].strOffLineRaisingDepartment[document.forms[0].strOffLineRaisingDepartment.selectedIndex].value);
		 	}
	 		else
	 		{
		 			getPartAccDtls(document.forms[0].strOffLineBillingService[document.forms[0].strOffLineBillingService.selectedIndex]);
		 	}			
	}
		
	if(mode == '4')
	{
		
		gblResVal = res;
		
		resetMultiRow('3');
		addRows(new Array('strOfflinePaymentMode','strOfflinePaymentDtls','strOfflineAmount'),new Array('s','t','t'),'3','1','R');
					
		document.getElementById("totalRecAmtDivId").innerHTML = "0.00";
		document.getElementById("totalRecAmtLabId").innerHTML = "0.00";
		//document.getElementById("totalCltAmtLabId").innerHTML = "0.00";
		document.forms[0].strOfflineTotalRecAmount.value = 0;				
		
		document.getElementById("offlineTotalPayAmtDivId").innerHTML = "0.00";
		document.forms[0].strOfflineTotalPayAmount.value = 0;			
		
		document.getElementById("offlineRefundAmtDivId").innerHTML = "0.00";
		document.forms[0].strOfflineRefundAmount.value = 0;			
		
		
							
		document.getElementById("otherRefundDetailsDivId").style.display = "none";															
		document.getElementById("errMsg").innerHTML = "";
		document.getElementById("errMsg").style.display = "none";
		
		var amtObj = document.getElementById("partPayAdvanceDivId");
		
		amtObj.style.display = "none";
			amtObj.innerHTML = "";
		
		var refAdv = document.getElementById("offlineAdmissionCancellationDivId");
		
		var reqType = document.forms[0].strOffLineRequestType[document.forms[0].strOffLineRequestType.selectedIndex].value;
		var billSer = document.forms[0].strOffLineBillingService[document.forms[0].strOffLineBillingService.selectedIndex].value;
		
				
		if( reqType == '1' && ( billSer == '13' || billSer == '14' || billSer == '15'))
		{
			amtObj.style.display = "none";
			amtObj.innerHTML = "";
			getPackageAmount();
		}
		else if(reqType == '2' && billSer == '19')
		{
			var temp = res.split('@');
			if(temp.length == 2)
			{
				document.getElementById("errMsg").innerHTML = temp[1];
				document.getElementById("errMsg").style.display = "block";
			}
			else
			{
				document.getElementById("errMsg").style.display = "none";	
				//alert(temp[0]);
				var temp1 = temp[0].split('^');
				document.getElementById("offlineAdmissionCancellationDivId").innerHTML = temp1[0];
				document.getElementsByName("strOffLineRefundAdvanceAccountNo")[0].value = temp1[2] ;
				var val =  1.0 * parseFloat(temp1[1]);
  				val = roundValue(val,2);
				document.forms[0].strOfflineTotalRecAmount.value = val;
				document.getElementById("totalRecAmtDivId").innerHTML = val;
				document.getElementById("totalRecAmtLabId").innerHTML = val;
				if(document.getElementById("otherRefundDetailsDivId").innerHTML.length > 0)
				document.getElementById("otherRefundDetailsDivId").style.display = "block";
				
				
								
						
				if(parseFloat(val) < 0)
				{
					val = -1 * parseFloat(val);
					val = roundValue(val,2);	  		
				}
	  	
		  	document.getElementsByName("strOfflineAmount")[0].value = val;			
			document.forms[0].strOfflineTotalPayAmount.value = val;
			document.getElementById("offlineTotalPayAmtDivId").innerHTML = val;
			setRefundRemarksText();
			}
		}				
		getOffLineGroupDtls();
		
		
	}
		
		
		if(mode == '5')
		{				
				var temp = res.split("####");
				document.getElementById("groupDivId").innerHTML = "<table border='0' cellpadding='0' cellspacing='0'><tr><td class='TITLE'><select name='strOffLineGroup' class='browser-default custom-select' onchange='resetTariffs();'>"+temp[0]+"</select></td></tr></table>";
				if(temp[1]!=null && temp[1]!="")
				{
					populateCreditLetter(temp[1]);
				}
				getOffLineClientDtls();	
				
				
						
		}
		
		if(mode == '6')
		{						
				if(res.length > 0)
				{	
					document.getElementById("offlineClientDivId").innerHTML = res;
					document.getElementById("offlineClientDivId").style.display = "block";
				}
				else
				{					
					document.getElementById("offlineClientDivId").style.display = "none";					
				}					
				var amtObj = document.getElementById("partPayAdvanceDivId");			
				var refAdv = document.getElementById("offlineAdmissionCancellationDivId");							
				var reqType = document.forms[0].strOffLineRequestType[document.forms[0].strOffLineRequestType.selectedIndex].value;
				var billSer = document.forms[0].strOffLineBillingService[document.forms[0].strOffLineBillingService.selectedIndex].value;
			
				if( (reqType == '1'|| reqType =='3') && ( billSer == '10' || billSer == '11' || billSer == '12'))
				{
					var offlineHospitalService = document.forms[0].strOffLineHospitalService[document.forms[0].strOffLineHospitalService.selectedIndex].value;
					var patientStatus = "0";
					var accountNo = "0";				
					if(document.getElementsByName("strOffLineAccountNo").length > 0 )
					{
						accountNo = document.getElementsByName("strOffLineAccountNo")[0].value ;
					}				
					if(document.getElementsByName("strPatientDtlHidVal") != null)
					{
						patientStatus = document.forms[0].strPatientDtlHidVal.value.split('^')[2];
					}				 
					///used to check if a zero balance account has to be opened
					var isAdvanceRequired=0;
					isAdvanceRequired = document.getElementsByName("strIsAdvanceRequired")[0].value ;
				
					if (offlineHospitalService == '2' && patientStatus == '3' && accountNo.length < 2 && isAdvanceRequired=='1')
					{					 					
						document.getElementById("offlineTariffDivId").style.display="none";
						document.getElementById("errMsg").innerHTML = "Account Not Opened, IPD Services are Not Applicable";
						document.getElementById("errMsg").style.display = "block";							
					}
					else
					{
						amtObj.style.display = "none";
						amtObj.innerHTML = "";
						addRows(new Array('strOfflineTariffName','strOfflineTariffRateUnit','strOfflineTariffReqQty','strOfflineTariffDiscount','strOfflineTariffBilledQty','strOfflineTariffCost'),new Array('t','t','t','t','t','t'),'2','1','I');		
						document.getElementById("offlineTariffDivId").style.display="block";					
					}			
				}
				else
				{				
					resetMultiRow(2);
					document.getElementById("offlineTariffDivId").style.display="none";
				}
				var reqType = document.forms[0].strOffLineRequestType[document.forms[0].strOffLineRequestType.selectedIndex].value;
				var bServId = document.forms[0].strOffLineBillingService[document.forms[0].strOffLineBillingService.selectedIndex].value;
				
			//	alert("above wardDisplayStatus invoke");
					
				if(reqType == '2' && bServId != '19' && bServId != '20')
				{				
					getOffLineRefundBillDetails();					
					//alert("inside getOffLineRefundBillDetails ");					
				}
				
				if(reqType == '2' && bServId == '20')
				{				
			//	alert("inside getOffLinePartPayRefundBillDetails ");
					getOffLinePartPayRefundBillDetails();
				}				
				advanceAndPartPaymentLogics(gblResVal);
				checkForOffLinePaymentLimit('1');
				//getTariffDtls();				
				//getOffLinePkgsGroupDtls();
				getPaymentModeByPatCat();
		}
		
		if(mode == '7'){
				
			//	alert("7 res  : "+res);
								
				//document.getElementById("dropdown1").innerHTML = "";
				document.getElementById("dropdown1").innerHTML = res;
							
						
																
				searchSelWithCode(tempCode,'strOfflineTariffName'+trfdelIndex,'1',trfObj);
				
				
		}
		
		if(mode == '8'){
						
				document.getElementById("packageGroupDivId").innerHTML = "<b>Group</b> <select name='strOffLinePackageGroup' class='comboNormal' onchange='getPackageDtls(this);'>"+res+"</select>";
				
			//	getTariffDtls();
		}
		
		if(mode == '9'){
			
			var temp = res.split('@');
						
				pkgDivObj = document.getElementById("pkgConfigId"+temp[0]);						
						
				pkgDivObj.innerHTML = temp[1];
								
				document.getElementById("flag"+temp[0]).value=1;
				document.forms[0].strPkgServiceFlag.value = 1;
				
				pkgDivObj.style.display="block";
				
				
				
		}
	
	if(mode == '10'){
			
			var temp = res.split('@');
					
			document.getElementById("offlineTariffUnitDivId"+temp[0]).innerHTML = "<select name='strOfflineTariffUnit' id='strOfflineTariffUnit"+temp[0]+"' class='comboMin' onChange='calcOffLineTariffNetAmount(\""+temp[0]+"\");'>"+temp[1]+"</select>";				
				
					calcOffLineTariffNetAmount(temp[0]);
		}
		
		if(mode == '11'){
							
		 			
							
			if(res.length > 0){		
				var temp = res.split('@'); 				

	 

				if(temp.length > 1){
					
				document.getElementById("offlineBillDetailsDivId").innerHTML = temp[0];				
				document.getElementById("offlineBillTariffDivId").innerHTML = temp[1];
			 				
				document.getElementById("offlineBillDetailsDivId").style.display = "block";	
				document.getElementById("offlineBillTariffDivId").style.display = "block";	
				if(document.getElementById("otherRefundDetailsDivId").innerHTML.length > 0)
				document.getElementById("otherRefundDetailsDivId").style.display = "block";
			
				document.getElementById("partPayAdvanceDivId").style.display = "none";
				document.getElementById("offlineTariffDivId").style.display = "none";
			
			
			//getBillTariffDtls(null);
			setRefundRemarksText();
					
				}	else{
					
				document.getElementById("offlineBillDetailsDivId").innerHTML = temp[0];				
				document.getElementById("offlineBillTariffDivId").innerHTML = "";
			 				
				document.getElementById("offlineBillDetailsDivId").style.display = "block";	
				document.getElementById("offlineBillTariffDivId").style.display = "none";
					
				document.getElementById("otherRefundDetailsDivId").style.display = "none";
				document.getElementById("partPayAdvanceDivId").style.display = "none";
				document.getElementById("offlineTariffDivId").style.display = "none";
			
					
				}																										
																											
				
			}else{
				document.getElementById("offlineBillDetailsDivId").innerHTML ="";				
				document.getElementById("offlineBillTariffDivId").innerHTML = "";
			 	
			 	document.getElementById("offlineBillDetailsDivId").style.display = "block";	
				document.getElementById("offlineBillTariffDivId").style.display = "block";	
				
				document.getElementById("otherRefundDetailsDivId").style.display = "none";
			
				document.getElementById("partPayAdvanceDivId").style.display = "none";
				document.getElementById("offlineTariffDivId").style.display = "none";
			
			}
			
				
			getOfflineThirdPartyAmtView();
			
		
			
		}
		
		if(mode == '12'){
					
			document.getElementById("offlineBillTariffDivId").innerHTML = res;				
				
			document.getElementById("offlineBillTariffDivId").style.display = "block";	
			
			if(document.getElementById("otherRefundDetailsDivId").innerHTML.length > 0)
			document.getElementById("otherRefundDetailsDivId").style.display = "block";
			
			setRefundRemarksText();
			
			getPaymentModeByPatCat(); // added by manisha 
			//setDefaultPaymentMode();
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
		
		if(mode == '20'){
					
			document.getElementById("reconcilTariffPopupDetailsDivId").innerHTML = res;				
				
				display_popup_menu(gblPopupParent,"reconcilTariffPopupDetailsDivId",'','');
		
		}
		
	/*	
		if(mode == '21'){
					
			if(res != ""){
				document.getElementById("onLineThirdPartyAmtDtlsViewDivId").style.display = "block";
				document.getElementById("onLineThirdPartyAmtDtlsViewDivId").innerHTML = res;		
			}else{
				document.getElementById("onLineThirdPartyAmtDtlsViewDivId").style.display = "none";
				document.getElementById("onLineThirdPartyAmtDtlsViewDivId").innerHTML = "";
			}
		}
		
		
		if(mode == '22'){
										
			if(res != ""){
				document.getElementById("offLineThirdPartyAmtDtlsViewDivId").style.display = "block";
				document.getElementById("offLineThirdPartyAmtDtlsViewDivId").innerHTML = res;		
			}else{
				document.getElementById("offLineThirdPartyAmtDtlsViewDivId").style.display = "none";
				document.getElementById("offLineThirdPartyAmtDtlsViewDivId").innerHTML = "";
			}			
						
		}
		
	*/
			
				
		 
		
		if(mode == '24'){
			
			
			  var temp = res.split("####");
   
		       if(temp[0] == "ERROR")
			   {
		          	err.innerHTML = temp[1];
		          	err.style.display = "block";
		          	return;
		       }
			
			
			document.getElementById("offlineTreatmentCategoryDivId").innerHTML = "<select class='comboNormal' name='strOffLineTreatmentCategory' onfocus='showAlert()' onchange='poorFreeCheck(\"strOffLineTreatmentCategory\"),changeTreatment()'>"+res+"</select>";	
			
			
				if(document.getElementsByName("strCatgoryCode").length > 0){
			
					
					 document.forms[0].strOffLineTreatmentCategory.value = document.forms[0].strCatgoryCode.value;
				}
			
			
				
			
			
			
			if(document.forms[0].strOffLineHospitalService[document.forms[0].strOffLineHospitalService.selectedIndex].value == '2'){
				
					 document.forms[0].strOffLineTreatmentCategory.value = document.forms[0].strCatgoryCode.value;
					
						initOfflineWard();
					
			}else{

				getEpisodeList(document.forms[0].strOffLineRaisingDepartment[document.forms[0].strOffLineRaisingDepartment.selectedIndex]);
			
			}
			
			
		
			
		}
		
		
		if(mode == '25'){
			
			
			  var temp = res.split("####");
   
		       if(temp[0] == "ERROR")
			   {
		          	err.innerHTML = temp[1];
		          	err.style.display = "block";
		          	return;
		       }
			
			document.getElementById("offlineWardDivId").innerHTML = "<select class='comboNormal' name='strOffLineWard' onfocus='setWard();' onchange='changeWard();'>"+res+"</select>";		
			
			  getEpisodeList(document.forms[0].strOffLineRaisingDepartment[document.forms[0].strOffLineRaisingDepartment.selectedIndex]);
		}	
					 
		
		
	if(mode == '27')
	{
		 var temp = res.split("####");
   	     if(temp[0] == "ERROR")
   	     {
		       	document.getElementById("normalMsg").style.display ="none";
		       	err.innerHTML = temp[1];
		       	err.style.display = "block";
		        return;
		 }
   	     else
   	     {
		       	
		      var tempVal = res.split("@@");
		      //alert(res);
		    
		      	if(tempVal[0] == '0')
		      	{
		      			alert("No Tariff/Tariff Code Found to The Selected Criteria ");
		      			document.forms[0].strAlertPromptConfirmFlag.value="1";
		      			document.forms[0].strTariffCode.value = '';
		      			document.forms[0].strTariffCode.focus();
		      			return false;
		      	}
		      	else
		      	{
		      		
		      		var indexVal = '2-'+document.multirow.rowIndex2.value;
		      		//varObj = document.getElementsByName("strOfflineTariffName");
		      		varObj = document.getElementsByName("strOfflineTariffDetailsHidden");
		      		 for(var i=0; i<varObj.length - 1; i++) 
		      		 {
		      		 		//if(varObj[i].value == tempVal[1])
		      		 		if(varObj[i].value.split('^')[0] == tempVal[2].split('^')[0])
		      		 		{
		      		 			alert("Tariff Name Already Exists");
		      		 			document.forms[0].strAlertPromptConfirmFlag.value="1";
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
   	  //getPaymentModeByPatCat(); 
	}	
		
		
		
		if(mode == '28'){
			
			
			  var temp = res.split("####");
   
		       if(temp[0] == "ERROR")
			   {
		          	err.innerHTML = temp[1];
		          	err.style.display = "block";
		          	return;
		       }
			
			document.getElementById("offlineSpecialWardDivId").innerHTML = "<select class='comboNormal' name='strOffLineSpecialWard'  onchange='changeWard();'>"+res+"</select>";		
			
				getPartAccDtls(document.forms[0].strOffLineBillingService[document.forms[0].strOffLineBillingService.selectedIndex]);
			
			
		}
		
		
		if(mode == '29'){
					
			document.getElementById("offlineBillTariffDivId").innerHTML = res;				
				
			document.getElementById("offlineBillTariffDivId").style.display = "block";	
			
			if(document.getElementById("otherRefundDetailsDivId").innerHTML.length > 0)
			document.getElementById("otherRefundDetailsDivId").style.display = "block";
			
			setRefundRemarksText();
			
					
					
			document.getElementsByName("strBillTariffVal")[0].click();
			
		 			
		}
		
		
		if(mode == '30'){
							
		 			
							
			if(res.length > 0){		
				var temp = res.split('@'); 				

	 

				if(temp.length > 1){
					
				document.getElementById("offlineBillDetailsDivId").innerHTML = temp[0];				
				document.getElementById("offlineBillTariffDivId").innerHTML = temp[1];
			 				
				document.getElementById("offlineBillDetailsDivId").style.display = "block";	
				document.getElementById("offlineBillTariffDivId").style.display = "block";	
				if(document.getElementById("otherRefundDetailsDivId").innerHTML.length > 0)
				document.getElementById("otherRefundDetailsDivId").style.display = "block";
			
				document.getElementById("partPayAdvanceDivId").style.display = "none";
				document.getElementById("offlineTariffDivId").style.display = "none";
			
			
			//getBillTariffDtls(null);
			setRefundRemarksText();
					
				}	else{
					
				document.getElementById("offlineBillDetailsDivId").innerHTML = temp[0];				
				document.getElementById("offlineBillTariffDivId").innerHTML = "";
			 				
				document.getElementById("offlineBillDetailsDivId").style.display = "block";	
				document.getElementById("offlineBillTariffDivId").style.display = "none";
					
				document.getElementById("otherRefundDetailsDivId").style.display = "none";
				document.getElementById("partPayAdvanceDivId").style.display = "none";
				document.getElementById("offlineTariffDivId").style.display = "none";
			
					
				}																										
																											
				
			}else{
				document.getElementById("offlineBillDetailsDivId").innerHTML ="";				
				document.getElementById("offlineBillTariffDivId").innerHTML = "";
			 	
			 	document.getElementById("offlineBillDetailsDivId").style.display = "block";	
				document.getElementById("offlineBillTariffDivId").style.display = "block";	
				
				document.getElementById("otherRefundDetailsDivId").style.display = "none";
			
				document.getElementById("partPayAdvanceDivId").style.display = "none";
				document.getElementById("offlineTariffDivId").style.display = "none";
			
			}
			
			
			document.getElementsByName("strOfflineRefundBillDetails")[0].click();
							
			getOfflineThirdPartyAmtView();
			 
		
			
		}
		if(mode == '31'){
			//alert(res);
			var packapp=document.getElementById("pack");
			packapp.innerHTML="<select name='strPackageApply' class='comboNormal' onChange='setAdvAmt();' >"+res+"</select>";
		}

		chkPaidCredit();
		
		
		if(mode == '32'){
			document.forms[0].accountCategory.innerHTML=res;
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

var hisValidator = new HISValidator("cashCollectionOfflineTransBean");  
		
	hisValidator.addValidation("strPayCDDBankName", "req", "Bank Name is a Mandatory Field" );
	hisValidator.addValidation("strChequeDDNo","req", "Cheque / DD No. is a Mandatory Field" );
	hisValidator.addValidation("strChequeDDDate", "req", "Cheque / DD Date is a Mandatory Field" );
	hisValidator.addValidation("strChequeDDDate", "date", "Please enter a valid Cheque / DD Date" );
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

var hisValidator = new HISValidator("cashCollectionOfflineTransBean");  
	
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
				
				//alert(gblAmount.value)
					gblAmount.focus();
		}else{
		
		return false;
		}

}


function validateClientDetails(){

var hisValidator = new HISValidator("cashCollectionOfflineTransBean");  
	
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
		//alert(mode); alert(parent.value) //validatePayMode();
		var optVal = parent.value.split('#')[0];
		var creditpay=document.forms[0].strOffLineTreatmentCategory.value;
		var x=creditpay.split("^")[1];
		
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
		
        /*if(x=="3" || x=="4"){   //credit category
			
			if(optVal == 2 || optVal == 3 || optVal == 4 || optVal == 5 || optVal == 6 || optVal == 7 || optVal == 8 || optVal == 9)
			{
			alert("For Credit Category, No Payment Mode Other Than Cash Is Allowed.");
			gblPayMode.selectedIndex = 0;
            gblPayDtls.disabled = false;
			}
		}
		else{*/
		
	
		if(optVal == 2 || optVal == 3){
		
						
		//	hide_popup('payDtlCDDMenu');
			
			popup('payDtlCDDMenu' , '250','250');
			
			document.forms[0].strPayCDBankName.focus();
			
		}else if(optVal == 4 || optVal == 5 || optVal == 8 || optVal == 9){
		
		//	hide_popup('payDtlCDMenu');
			//cardlogic();
			//popup('cardAmtDiv' , '150','450');
			popup('payDtlCDMenu' , '250','250');
			
		document.forms[0].strPayCDDBankName.focus();
		
		}else if(optVal == 6){
		
		//	hide_popup('payDtlCDMenu');
			
			popup('payDtlCLTMenu' , '250','250');
			
		document.forms[0].strPayCNTClientName.focus();
		
		}else if(optVal == 7){
			
			popup('payDtlWalletMenu' , '250','250');
			
	    }else{
		
				//hide_popup('payDtlCDDMenu');
				
				gblPayDtls.disabled = false;
				
				gblAmount.focus();
		}
		//}
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

	function hidePayDetails(divId){
		
		if(gblPayEdit != "Edit"){
		
		document.forms[0].strChequeDDDate.value = document.forms[0].strTempCtDate.value;
		document.forms[0].strAuthDate.value = document.forms[0].strTempCtDate.value;
		document.forms[0].strClientAuthDate.value = document.forms[0].strTempCtDate.value;
		
		gblPayMode.selectedIndex = 0;
		gblPayDtls.disabled = false;
		//gblAmount.focus();
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
	
	//New function added for Adavnce Security Flag by garima for HIS_PGI_BILL_CR_1on 17 Aug 2011
	function ftnadvSecTick(){
     
		if(document.forms[0].advSecFlag.checked == true){
   			document.forms[0].strRemarks.disabled=false;
			document.forms[0].strRemarks.value="Against Security";
			document.forms[0].advSecFlag.value = 1;
									
		}else{
			document.forms[0].strRemarks.disabled=true;
			document.forms[0].strRemarks.value="";
			document.forms[0].advSecFlag.value = 0;
		} 
		
		}
	


	function ftnTick(){

		if(document.forms[0].strChk_value.checked == true){

			document.forms[0].strPartpayment.disabled=false;
			document.getElementById("combo").style.display="block";
			document.forms[0].strChk_value.value = 1;
			
			document.forms[0].strOfflineTotalRecAmount.value = roundValue( document.forms[0].strPartpayment.value , 2 );
			document.getElementById("totalRecAmtDivId").innerHTML = roundValue( document.forms[0].strPartpayment.value , 2 );
			document.getElementById("totalRecAmtLabId").innerHTML = roundValue( document.forms[0].strPartpayment.value , 2 );
								
		}else{
	
			document.forms[0].strPartpayment.disabled =true;
			document.forms[0].strPartpayment.value = document.forms[0].strdummypartpayment.value;
			document.getElementById("combo").style.display="none";
			document.forms[0].strChk_value.value = 0;
						
			document.forms[0].strOfflineTotalRecAmount.value = roundValue( document.forms[0].strPartpayment.value , 2 );
			document.getElementById("totalRecAmtDivId").innerHTML = roundValue( document.forms[0].strPartpayment.value , 2 );
			document.getElementById("totalRecAmtLabId").innerHTML = roundValue( document.forms[0].strPartpayment.value , 2 );
			
		} 
		
		setTotalToAmount('0');
		
		document.getElementsByName("strOfflineAmount")[0].focus();
		
		checkForOffLinePaymentLimit('1');
	}

	function setSelectedTariffCode(userValue ,resultText, resultValue)
	{
		if(resultText.includes("^"))
		{
			document.forms[0].strTariffCode.value=resultText.split("^")[0];
			document.forms[0].grpid.value=resultText.split("^")[1];
			document.forms[0].strTariffCode.focus();
		}
		else
		{
			document.forms[0].strTariffCode.value = resultText;
			document.forms[0].grpid.value="0";
			document.forms[0].strTariffCode.focus();
		}
	}

	function setSelectedTariff(userValue ,resultText, resultValue)
	{
		//alert("INS");
		varObj = document.getElementsByName("strOfflineTariffDetailsHidden");
 		 for(var i=0; i<varObj.length - 1; i++) 
 		 {
 		 		//if(varObj[i].value == tempVal[1])
 		 		if(varObj[i].value.split('^')[0] == resultValue.split('^')[0])
 		 		{
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
				
			
			if(temp[17] == '1')
			{
				tariffObj.readOnly=false;
				document.getElementById("strOfflineTariffRateUnitDivId"+userValue).style.display = "none";				
				document.getElementById("strOfflineTempTariffRateUnitDivId"+userValue).innerHTML= "<input type='text' name='strOfflineTempTariffRateUnit' id='strOfflineTempTariffRateUnit"+userValue+"' class='txtFldMin' value='"+temp[4]+"'  maxlength='8' onkeypress='return validateData(event,7);' onkeyup='setRateValue(\""+userValue+"\");' >" +" / "+temp[14];				
				document.getElementById("strOfflineTempTariffRateUnitDivId"+userValue).style.display = "block";
				document.getElementById("strOfflineTempTariffRateUnit"+userValue).value = temp[4];
			}
			else
			{
				document.getElementById("strOfflineTariffRateUnitDivId"+userValue).style.display = "block";
				document.getElementById("strOfflineTempTariffRateUnitDivId"+userValue).style.display = "none";
			}
			
			
			document.getElementById("strOfflineTariffRateUnit"+userValue).value = temp[4]+" / "+temp[14];
			document.getElementById("strOfflineTariffRateUnitDivId"+userValue).innerHTML = temp[4]+" / "+temp[14];
			
			document.getElementById("strOfflineTariffDiscount"+userValue).value = 0;
			document.getElementById("strOfflineTariffDiscountDivId"+userValue).innerHTML = 0 ;
			
			document.getElementById("strOfflineTariffServiceTax"+userValue).value = temp[13];
			document.getElementById("strOfflineTariffServiceTaxDivId"+userValue).innerHTML = temp[13];
			
			document.getElementById("strOfflineTariffQty"+userValue).value = 1;
			
			document.getElementById("strOfflineTariffNetAmount"+userValue).value = 0;
			document.getElementById("strOfflineTariffNetAmountDivId"+userValue).innerHTML = 0;
		
			document.getElementById("strOfflineTariffDetailsHidden"+userValue).value = resultValue;
			document.getElementById("strOfflineTariffDiscountConfigDetails"+userValue).value = '';
			
			getOffLineTariffUnit(temp[5],temp[6],userValue);
						
			//document.getElementById("tariffFullNameDiv").innerHTML = "";
			//alert(document.getElementById("strOfflineTariffNetAmount"+userValue).value);
		
			if(isAddRowRequired("strOfflineTariffName"))
			{
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
		var temp = resultValue.split('^');
		//alert(resultValue)
		document.getElementById("strOfflineTariffDetailsHidden"+userValue).value = resultValue;
		//alert(document.getElementById("strOfflineTariffName"+userValue).value);
		//alert(document.getElementById("strOfflineTariffDetailsHidden"+userValue).value);
		if(b || checkForDuplicate_select('strOfflineTariffDetailsHidden',document.getElementById("strOfflineTariffDetailsHidden"+userValue),'Tariff Name')){

		
			if(temp[17] == '1'){
				
				document.getElementById("strOfflineTariffRateUnitDivId"+userValue).style.display = "none";	
				
				document.getElementById("strOfflineTempTariffRateUnitDivId"+userValue).innerHTML= "<input type='text' name='strOfflineTempTariffRateUnit' id='strOfflineTempTariffRateUnit"+userValue+"' class='txtFldMin' value='"+temp[4]+"'  maxlength='8' onkeypress='return validateData(event,7);' onkeyup='setRateValue(\""+userValue+"\");' >" +" / "+temp[14];
				
				document.getElementById("strOfflineTempTariffRateUnitDivId"+userValue).style.display = "block";
				
				document.getElementById("strOfflineTempTariffRateUnit"+userValue).value = temp[4];
				
				
			}else{
				
				document.getElementById("strOfflineTariffRateUnitDivId"+userValue).style.display = "block";
				document.getElementById("strOfflineTempTariffRateUnitDivId"+userValue).style.display = "none";
				document.getElementById("strOfflineTariffName"+userValue).readOnly="readOnly";
			}

			document.getElementById("strOfflineTariffRateUnit"+userValue).value = temp[4]+" / "+temp[14];
			document.getElementById("strOfflineTariffRateUnitDivId"+userValue).innerHTML = temp[4]+" / "+temp[14];
			
			document.getElementById("strOfflineTariffDiscount"+userValue).value = 0;
			document.getElementById("strOfflineTariffDiscountDivId"+userValue).innerHTML = 0 ;
			
			document.getElementById("strOfflineTariffServiceTax"+userValue).value = temp[13];
			document.getElementById("strOfflineTariffServiceTaxDivId"+userValue).innerHTML = temp[13];
			
			document.getElementById("strOfflineTariffQty"+userValue).value = 1;
			
			document.getElementById("strOfflineTariffNetAmount"+userValue).value = 0;
			document.getElementById("strOfflineTariffNetAmountDivId"+userValue).innerHTML = 0;
		
			document.getElementById("strOfflineTariffDetailsHidden"+userValue).value = resultValue;
			document.getElementById("strOfflineTariffDiscountConfigDetails"+userValue).value = '';
	
			getOffLineTariffUnit(temp[5],temp[6],userValue);
		}
		else
		{
			document.getElementById("strOfflineTariffDetailsHidden"+userValue).value = "";
			document.getElementById("strOfflineTariffName"+userValue).value = "";
			document.getElementById("strOfflineTariffName"+userValue).readOnly=false;
			document.getElementById("strOfflineTariffName"+userValue).focus();
		}
			
				
		//document.getElementById("tariffFullNameDiv").innerHTML = "";
		
		document.getElementById("strOfflineTariffName"+userValue).focus();
		
		/*if(temp[17] != '1')
		    document.getElementById("strOfflineTariffName"+userValue).readOnly="readOnly";*/
		
			if(isAddRowRequired("strOfflineTariffName")){
				generateRows();
			}
		 		
		
	}

function showTariffSearchPopup(e,compName,index)
{	
	var groupId = "0";
	var wardCode = "0";
		
	var groupId = document.forms[0].strOffLineGroup.value;
				
	if(document.getElementById("wardDivId") != null)		
	if(document.getElementById("wardDivId").style.display == "block" )
	{
		wardCode = document.forms[0].strOffLineWard.value;
	}
		
	tariffSearchPopUp(e,compName,document.forms[0].strOffLineHospitalService.value,document.forms[0].strOffLineTreatmentCategory.value,wardCode,groupId,'setSelectedTariff',index);
}

function showTariffCodeSearchPopup(e,compName,index){
	
	var groupId = "0";
	var wardCode = "0";
		
	var groupId = document.forms[0].strOffLineGroup.value;
				
		if(document.getElementById("wardDivId") != null)		
		if(document.getElementById("wardDivId").style.display == "block" ){
			wardCode = document.forms[0].strOffLineWard.value;
		}
		
	tariffCodeSearchPopUp(e,compName,document.forms[0].strOffLineHospitalService.value,document.forms[0].strOffLineTreatmentCategory.value,wardCode,groupId,'setSelectedTariffCode',index);
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
			
			if(document.forms[0].strOffLineHospitalService[document.forms[0].strOffLineHospitalService.selectedIndex] != null){ // With Cr No. Cleark Discount Logic
				
				
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
		}
	}


function getOnLineWithoutCrNoTariffDiscountDetails(divId,parent){
			
							
				
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
		}
	}


function hideOffLinePkgDiscountDetails(divId){
		
		hide_popup(divId);
	}
	
	
	function hideWithoutCrNoTariffDiscountDetails(divId){
		
			var conf = confirm("Are you Sure to Cancel the Discount");
		
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
						
		hide_popup(divId);
	}
	
	
	function hideOffLineTariffDiscountDetails(divId){
		
			var conf = confirm("Are you Sure to Cancel the Discount");
		
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
						
		hide_popup(divId);
	}
	
	/**
	 * validateTariffDiscountDetails -  
	 */
	 function validateTariffDiscountDetails() {
	 	
	 	
	 	 	
	 	var hisValidator = new HISValidator("cashCollectionOfflineTransBean");  
			
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
	 		
	 }
	
	
	
	/**
	 * calcOffLineTariffNetAmount
	 * @param {Object} qtyObj - Quatity Text Box object 
	 * @param {String} index 
	 */
	 function calcOffLineTariffNetAmount(index) 
	 {
		var tariff =  document.getElementById("strOfflineTariffName"+index).value;	 		 	
	 	var a = document.getElementById("strOfflineTariffDetailsHidden"+index).value.split('^');
	 	//"1240003^124^0.00^24.00^24.00^1701^1^0^1^1^24.00^24.00^0^0^No^1^10003^0^0^Biochemistry";18 VALUES
	 	//TARIFFID^GROUPCODE^PROCEDURE_COST^TARIFF_COST^HBLNUM_COST^RATEUNIT^UNITBASEVALUE^IS_PACKAGE^IS_ADVANCE^IS_REFUNDABLE^
	 	//HBLNUM_DEFAULT_RATE^HBLNUM_COST^MAX_DISCOUNT^SERVICE_TAX^UNITNAME^SBLNUM_SERVICE_ID^GSTRTARIFFID^UNDEFINED_CHARGE^GROUPNAME
	 	
		var b=true;
		for(var i = 0 ; i<a.length; i++)
			if(a[i]!="0")
			{
				b=false;
				break;
			}
		if(b || tariff != '')
		{
	 		var temp = document.getElementById("strOfflineTariffDetailsHidden"+index).value.split('^');	 	
	 		var rate = temp[4];
	 		var actRate = temp[11];
	 		if(temp[17] == '1')//UNDEFINED CHARGES
	 		{	 			
	 			rate = document.getElementById("strOfflineTempTariffRateUnit"+index).value;
	 			actRate = rate;//ACTURAL RATE AND USER ENTRY RATE ALL SHOULD BE SAME.CHANGED ON 29.05.17 BY AJAY DESHWAL
	 		}
	 		document.getElementById("strOfflineTariffRateUnit"+index).value=Math.round(rate)+" / No.";
	 		var creditType=document.getElementById("strCreditPaymentType"+index);
		  	/*if(creditType.value=="11")//Credit
		  		var rate = 0;
		  	else
		  		var rate = temp[3];*/
		  	
	 		
	 		var rateBaseValue = temp[6];
	 		var qtyBaseValue = document.getElementById("strOfflineTariffUnit"+index).options[document.getElementById("strOfflineTariffUnit"+index).selectedIndex].value;
	 		var qtyVal =  document.getElementById("strOfflineTariffQty"+index).value;
	 		var discountVal = document.getElementById("strOfflineTariffDiscount"+index).value;
	 		var serviceVal = document.getElementById("strOfflineTariffServiceTax"+index).value;
	 		var hiddenVal = document.getElementById("strOfflineTariffDiscountConfigDetails"+index).value;
	 		var discountType = 0;
	 		
	 		var netAmt = 0;
	 		var amtPaidByClient = 0; 
	 		
	 		if(hiddenVal != '')
	 		{	 			
	 			var tempArr = hiddenVal.split(',');	 			
	 			discountVal = tempArr[0];
	 			discountType = tempArr[1];
	 		}
	 		
	 		
	 		var calAmt = calTrfNetAmountWithActTrfAmt(rate, actRate ,rateBaseValue,discountVal,discountType,qtyVal,qtyBaseValue,serviceVal,0,0);
	 		if((creditType.value=="11" || creditType.value=="13"))//Credit or Credit Urgent
	 		{
	 			netAmt = roundValue(0.00,2);
	 			amtPaidByClient=calAmt.oNetTrfAmt;
	 		}	 			
		  	else
		  	{
		  		netAmt = calAmt.oNetTrfAmt;
		  		amtPaidByClient=roundValue(0.00,2);
		  	}
	 	///add consumabale charges
	 	//var groupid =temp[1];
	 	//var groupConsumableCharge =temp[18];
	 	//alert(groupid)
	 	//alert(groupConsumableCharge)
	 	
	 	//addConsumableCharge(groupid,groupConsumableCharge)
	 	
	 	
	 		if(netAmt < 0)
	 		{
	 			
	 			alert("Net Amount Value is Less than Rs. 0.00, Please Check the Discount Amount");
	 			
	 			document.getElementById("strOfflineTariffDiscount"+index).value = 0;					
				document.getElementById("strOfflineTariffDiscountDivId"+index).innerHTML = "0";
					
				var temp =  document.getElementById("strOfflineTariffDiscountConfigDetails"+index).value.split(",");
																
				document.getElementById("strOfflineTariffDiscountConfigDetails"+index).value = "0,1,"+temp[2]+","+temp[3]+","+temp[4]+","+temp[5];
			 				 	
			 	calcOffLineTariffNetAmount(index);			 	
			 				 			
	 			return false;
	 		}
	 			
	 			
	 			document.getElementById("strOfflineTariffServiceTaxAmtVal"+index).value = calAmt.oSerAmt;
				document.getElementById("strOfflineTariffDiscountAmtVal"+index).value = calAmt.oDisAmt;
				//alert("1");
	 			document.getElementById("strOfflineTotalActualTariffAmtVal"+index).value = calAmt.oActTrfAmt;
	 		 
	 			document.getElementById("strOfflineTariffNetAmount"+index).value = Math.round(netAmt);
	 			document.getElementById("strOfflineAmtPaidByClient"+index).value = Math.round(amtPaidByClient);
	 			document.getElementById("strOfflineTariffNetAmountDivId"+index).innerHTML = Math.round(netAmt);
	 			
	 	
	 		calcTotalRecAmount(index);	
		}	 	
	//	getPaymentModeByPatCat();
	 }
	 
	 
	 
	
	/**
	 * calcTotalRecAmount 
	 */
function calcTotalRecAmount(index) 
{	 		 	
	 	var grandTotal = calAllTariffNetCost("strOfflineTariffNetAmount");
	 		 
	 	if(document.getElementById("offlineMaxGrandTotalDivId") != null)
	 	{	 	
		 	document.getElementById("offlineMaxGrandTotalDivId").innerHTML = grandTotal;
		 	document.forms[0].strOfflineGrandTotalAmount.value = grandTotal;
	 	}
	 	
	 	if(document.getElementsByName("strOfflineTotalServiceTaxAmount").length > 0)
	 	{	 	
		 	var grandServiceTax = calAllTariffNetCost("strOfflineTariffServiceTaxAmtVal");
		 	document.forms[0].strOfflineTotalServiceTaxAmount.value = grandServiceTax;
		 	
		 		 	
			var grandDiscountVal = calAllTariffNetCost("strOfflineTariffDiscountAmtVal"); 
		 	document.forms[0].strOfflineTotalDiscountAmount.value = grandDiscountVal;
		 	
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
	 	
	 	if(clientBalAmtObj.length > 0)
	 	{	 	
	 		clientBalAmt = parseFloat(clientBalAmtObj[0].value.split('^')[3]);
	 		patPayAmtObj  = parseFloat(clientBalAmtObj[0].value.split('^')[5]);
	 	}
	 	else
	 	{	 		
	 		clientBalAmt = parseFloat("0");
	 	} 	
	 	
 		
 		if(grandTotalAmt != null)
 		{
 			grandTotalAmt 		= parseFloat(grandTotalAmt.value);
 		}
 		else
 		{	 			
 			grandTotalAmt = parseFloat(grandTotal);
 		}	 			 	
 		if(patPayAmtObj != null)
 		{	 			
 			patPayAmt = patPayAmtObj.value;
 		}
 		else
 		{
 			patPayAmt = parseFloat("0");
 		}	 
 		var cltAmtDtls = calClientAmount(clientBalAmt,patPayAmt,grandTotalAmt);	 		
 		
 		if(document.getElementById("offlineMaxGrandTotalDivId") != null)
 		{	 	 		
	 		document.forms[0].strOfflineMaxClientBenefitAmount.value = Math.round(cltAmtDtls.oClientAmt);
	 		document.getElementById("offlineMaxClientBenefitDivId").innerHTML = Math.round(cltAmtDtls.oClientAmt);
	 		document.getElementById("totalCltAmtLabId").innerHTML = Math.round(cltAmtDtls.oClientAmt);
	 	}	 			 			 			
 		if(document.getElementById("offlinePatNetPayDivId") != null)
 		{		 			 			
	 		document.forms[0].strOfflinePatNetPayAmount.value = Math.round(cltAmtDtls.oPatAmt);
	 		document.getElementById("offlinePatNetPayDivId").innerHTML = Math.round(cltAmtDtls.oPatAmt) ;	 		
 		}		 		
 		var creditTypeObj=document.getElementsByName("strCreditPaymentType");
 		if(creditTypeObj.length>0) 
 		{
 			calClientAmountForCredit();
 		}
 		document.forms[0].strOfflineTotalRecAmount.value = Math.round(cltAmtDtls.oPatAmt);
 		document.getElementById("totalRecAmtDivId").innerHTML = Math.round(cltAmtDtls.oPatAmt) ;
 		document.getElementById("totalRecAmtLabId").innerHTML = Math.round(cltAmtDtls.oPatAmt) ;
	 		
	 if(parseFloat(cltAmtDtls.oPatAmt) < 0)
	 {
		  		
		  		cltAmtDtls.oPatAmt = -1 * parseFloat(cltAmtDtls.oPatAmt);
		  		cltAmtDtls.oPatAmt = roundValue(cltAmtDtls.oPatAmt,2); 
		  		
	 }
	 document.getElementsByName("strOfflineAmount")[0].value = Math.round(cltAmtDtls.oPatAmt);
	 document.forms[0].strOfflineTotalPayAmount.value = Math.round(cltAmtDtls.oPatAmt);
	 document.getElementById("offlineTotalPayAmtDivId").innerHTML = Math.round(cltAmtDtls.oPatAmt);
	 setTotalToAmount('0');
	 setTotalRefundAmount('0');
	 			
	 			 	
	 	///adding consumable to all cahrges
		 
		 //var consumableCharge=document.getElementsByName("strConsumableCharge")[0].value	
		 //var totalCharge=document.getElementsByName("strOfflineTotalPayAmount")[0].value
		
		 document.getElementsByName("strOfflineTotalPayAmountWithoutConsumable")[0].value=Math.round(cltAmtDtls.oPatAmt);
		 
		 //var totalCharge=roundValue(parseFloat(cltAmtDtls.oPatAmt)+parseFloat(consumableCharge),2)
		 var totalCharge=roundValue(parseFloat(cltAmtDtls.oPatAmt),2);
		 if(document.getElementById("totalRecAmtDivId") != null)
		 {
			 document.forms[0].strOfflineTotalRecAmount.value=totalCharge
			 //document.getElementById("totalRecAmtDivId").innerHTML = "<a style='cursor: pointer;' onclick='showChargeDtl(300,500)' title='Charge Details'><font color='blue'><u>"+totalCharge+"</u></font></a>";
			 document.getElementById("totalRecAmtDivId").innerHTML = "<font color='red'>"+totalCharge+"</font>";
		 }	
		 if(document.getElementById("totalRecAmtLabId") != null)
		 {
			 document.forms[0].strOfflineTotalRecAmount.value=totalCharge
			 //document.getElementById("totalRecAmtLabId").innerHTML = "<a style='cursor: pointer;' onclick='showChargeDtl(300,500)' title='Charge Details'><font color='blue'><u>"+totalCharge+"</u></font></a>";
			 document.getElementById("totalRecAmtLabId").innerHTML = totalCharge;
		 }	
		 if(document.getElementById("offlineTotalPayAmtDivId") != null)
		 {
			 document.forms[0].strOfflineTotalPayAmount.value=totalCharge
			 document.getElementById("offlineTotalPayAmtDivId").innerHTML = totalCharge
		 }		 
		 if(document.getElementById("offlinePatNetPayDivId") != null)
		 {
			 document.getElementById("offlinePatNetPayDivId").innerHTML = totalCharge
			 document.forms[0].strOfflinePatNetPayAmount.value = totalCharge
		 }		 
		 document.getElementsByName("strOfflineAmount")[0].value=totalCharge
		 checkForOffLinePaymentLimit('1');
}
	 
	 
	  
 function calClientAmountForCredit()
 {
 	 var creditTypeObj=document.getElementsByName("strCreditPaymentType");
 	 
 	 var clientAmt=parseFloat("0");
 	 
 	 for(var x=0;x<creditTypeObj.length;x++)
 	 {
 		 if((creditTypeObj[x].value=="11" || creditTypeObj[x].value=="13"))//Credit or Credit Urgent
 		 {
 			 clientAmt=clientAmt+parseFloat(document.getElementsByName("strOfflineTotalActualTariffAmtVal")[x].value);			 	
 		 }
 	 }
 	 if(document.forms[0].strOffLineTreatmentCategory.value.split("^")[0] == document.forms[0].strArogyaTSCatId.value)//If Arogyashree TS Cat then Limit is 2000
	 {
		if(clientAmt>=document.forms[0].strArogyaTSCatCreditLimit.value)
		{
			if(!confirm("Arogyashree TS Credit Limit: "+document.forms[0].strArogyaTSCatCreditLimit.value+" Exceeded!!!. Do you want to proceed ??"))
				return false;
		} 		
	 }
 	 document.forms[0].strOfflineMaxClientBenefitAmount.value =Math.round(clientAmt);
 	 document.getElementById("offlineMaxClientBenefitDivId").innerHTML = Math.round(clientAmt); 
 	 document.getElementById("totalCltAmtLabId").innerHTML = Math.round(clientAmt);
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
	 	
	 	//removeConsumableAmount(groupid,groupConsumableCharge)
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
				
				//addRows(new Array('strOfflineTariffName','strOfflineTariffDetailsHidden','strOfflineTempTariffRateUnit','strOfflineTariffRateUnit','strOfflineTariffQty','strOfflineTariffUnit','strOfflineTariffServiceTax', 'strOfflineTariffServiceTaxAmtVal','strOfflineTariffDiscount','strOfflineTariffDiscountAmtVal' ,'strOfflineTariffNetAmount','strOfflineTotalActualTariffAmtVal'),new Array('t','t','t','t','t','s','t','t','t','t','t','t'),'2',obj.value,'R')
				addRows(new Array('strOfflineTariffName','strOfflineTariffDetailsHidden','strOfflineTempTariffRateUnit','strOfflineTariffRateUnit','strOfflineTariffQty','strOfflineTariffUnit','strOfflineTariffServiceTax', 'strOfflineTariffServiceTaxAmtVal','strOfflineTariffDiscount','strOfflineTariffDiscountAmtVal' ,'strOfflineTariffNetAmount','strOfflineTotalActualTariffAmtVal','strCreditPaymentType','strCreditLetterNo','uploadedFile'),new Array('t','t','t','t','t','s','t','t','t','t','t','t','s','s','t'),'2',obj.value,'R')
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
	  	document.getElementById("totalRecAmtLabId").innerHTML = actRefundAmt;
	    
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
	  		  	
	  		  	//alert("inside initBillTariff" );
	  		  	
	  		  	//alert("obj : "+obj);
	  		  	//alert("obj.checked :: "+obj.checked);
		  getPaymentModeByPatCat(); // added by manisha 
			//setDefaultPaymentMode();
	  		  	
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
	 	document.getElementById("totalRecAmtLabId").innerHTML = grandTotal;
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
	  			
	  				document.getElementsByName("strOfflineAmount")[0].focus();
		 	
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
	  			document.getElementById("totalRecAmtLabId").innerHTML = val;
	  			
	  			document.forms[0].strOfflineTotalRecAmount.value = val;
	  			  			
	  			setTotalToAmount('0');
	  			
	  				document.getElementsByName("strOfflineAmount")[0].focus();
	  			
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
	   	
	   	document.getElementsByName("strOfflineAmount")[0].value = val;
	   	
	 	document.getElementById("totalRecAmtDivId").innerHTML = val;	
	 	document.getElementById("totalRecAmtLabId").innerHTML = val;
	 	document.getElementById("offlineTotalPayAmtDivId").innerHTML = val;
	  	document.forms[0].strOfflineTotalRecAmount.value = val;
	  	document.forms[0].strOfflineTotalPayAmount.value = val;
	  	
	  	  	
	  }else{
	  	
	  	document.getElementsByName("strOfflineAmount")[0].value = '0.00';	  	
	  	document.getElementById("totalRecAmtDivId").innerHTML = '0.00';	
	  	document.getElementById("totalRecAmtLabId").innerHTML = '0.00';
	  	document.getElementById("offlineTotalPayAmtDivId").innerHTML ='0.00';
	  	document.forms[0].strOfflineTotalRecAmount.value = 0;
	  	document.forms[0].strOfflineTotalPayAmount.value = 0;
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
	 	document.getElementById("totalRecAmtLabId").innerHTML = val;
	  	document.forms[0].strOfflineTotalRecAmount.value = val;
	  	
	  	
	  }else{
	  	document.getElementById("totalRecAmtDivId").innerHTML = '0.00';	
	  	document.getElementById("totalRecAmtLabId").innerHTML = '0.00';	
	  	document.forms[0].strOfflineTotalRecAmount.value = 0;
	  	
	  }
			
			setTotalToAmount('0');
	  		setTotalRefundAmount('0');
	  		
	  			getPaymentModeByPatCat(); 
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
		 
		 ///adding consumable to total cahrges
		 
		/* var consumableCharge=document.getElementsByName("strConsumableCharge")[0].value	
		 //var totalCharge=document.getElementsByName("strOfflineTotalPayAmount")[0].value
		
		 document.getElementsByName("strOfflineTotalPayAmountWithoutConsumable")[0].value=tot;
		 tot=tot+parseInt(consumableCharge)
		 alert("strOfflineTotalPayAmountWithoutConsumable="+document.getElementsByName('strOfflineTotalPayAmountWithoutConsumable')[0].value+"consumableCharge="+consumableCharge+"total="+tot)
		 */
		 tot = roundValue(tot,2);
		 
	 			
	 	document.getElementById("offlineTotalPayAmtDivId").innerHTML = tot;		
	  	document.forms[0].strOfflineTotalPayAmount.value = tot;
	  	
	 	  	
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
		 
					var payModeVal = payMode[i][payMode[i].selectedIndex].value.split('#')[0];
		 			 	
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
		 
		 			var payModeVal = payMode[i][payMode[i].selectedIndex].value.split('#')[0];		 			 	
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

				var payModeVal = payMode[i][payMode[i].selectedIndex].value.split('#')[0];
		 			 	
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
	 	 	 		 	
	 }
	 
	 
	 /**
	  * setTotalToAmount
	  * @param {String} mode 
	  */
	  function setTotalToAmount(mode) {
		  
		  //	alert(document.getElementsByName("strOfflinePaymentMode")[0].value);
	  	
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
		  	
		  				  	
		  	document.forms[0].strOfflineTotalPayAmount.value = totRecAmt;
		  	document.getElementById("offlineTotalPayAmtDivId").innerHTML = totRecAmt;
		  
	  		
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
	    
	  
	  var  totRecAmt = parseFloat(document.forms[0].strOfflineTotalRecAmount.value);	
	  	
	  
	  	  var totPayAmt = parseFloat(document.forms[0].strOfflineTotalPayAmount.value);
	  	
	  		if(totRecAmt > 0){
	  			tot = manipulateValue(totRecAmt,totPayAmt,1);
	  		}else{
	  				tot = manipulateValue(totRecAmt,totPayAmt,0);
	  		}
	  		tot = roundValue(tot,2);	
	  		  	
	  		document.forms[0].strOfflineRefundAmount.value = tot;
	  		document.getElementById("offlineRefundAmtDivId").innerHTML = tot;
	  	
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
	  function getTariffByCode(teriffCode , e) 
	  {	  
	  	if(e.keyCode == 13 && document.forms[0].strTariffCode.value.length > 0)
	  	{
			var hmode;
			if(document.forms[0].grpid.value!="107")
	 			hmode = "TARIFFCODEDTLS";	
	 		else
	 			hmode =  "TARIFFCODEDTLSDRUG";							
			var url = "";	
		 
			
			if(document.forms[0].strOffLineGroup.value=="107")
				hmode =  "TARIFFCODEDTLSDRUG";
			if(document.getElementById("wardDivId") != null && document.getElementById("wardDivId").style.display == "block")
			{
				var wardIdVal = document.forms[0].strOffLineWard[document.forms[0].strOffLineWard.selectedIndex].value.split('^')[0];	
				 url = "CashCollectionOfflineTransBSCNT.cnt?hmode="+hmode+"&groupId="+document.forms[0].strOffLineGroup.value+"&hService="+document.forms[0].strOffLineHospitalService.value+"&treatmentCat="+document.getElementsByName("strOffLineTreatmentCategory")[0].value.split("^")[0]+"&ward="+wardIdVal+"&tariffCode="+document.forms[0].strTariffCode.value+"&bService="+document.forms[0].strOffLineBillingService.value;
		 	}
			else if(document.getElementsByName("strOffLineHospitalService") > 0)
			{
				 url = "CashCollectionOfflineTransBSCNT.cnt?hmode="+hmode+"&groupId="+document.forms[0].strOffLineGroup.value+"&hService="+document.forms[0].strOffLineHospitalService.value+"&treatmentCat="+document.forms[0].strOffLineTreatmentCategory.value.split("^")[0]+"&ward=0"+"&tariffCode="+document.forms[0].strTariffCode.value;
			}
			else
			{
					url = "CashCollectionOfflineTransBSCNT.cnt?hmode="+hmode+"&groupId="+document.forms[0].strOffLineGroup.value+"&hService="+document.forms[0].strOffLineHospitalService.value+"&treatmentCat="+document.getElementsByName("strOffLineTreatmentCategory")[0].value.split("^")[0]+"&ward=0"+"&tariffCode="+document.forms[0].strTariffCode.value;
			}			
			//alert(document.forms[0].strOffLineHospitalService.value+"--"+document.forms[0].strOffLineHospitalService[document.forms[0].strOffLineHospitalService.selectedIndex].value);
			//alert(url);
			ajaxFunction(url,"27");
			document.forms[0].grpid.value="0";
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
     var url="CashCollectionOfflineTransBSCNT.cnt?hmode="+mode+"&modName="+modeVal;
     
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
			var url = "CashCollectionOfflineTransBSCNT.cnt?hmode="+hmode+"&hidValue="+hidVal;

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
			var url = "CashCollectionOfflineTransBSCNT.cnt?hmode="+hmode+"&unitId="+unitId+"&baseValue="+baseValue+"&rowIndex="+index;
			
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
	  	//alert(totalVal);
	  	//alert(mode);
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
	  		
	  		if(val == '5'){
	  	
	  			var hisValidator = new HISValidator("cashCollectionOfflineTransBean");  
	
				hisValidator.addValidation("strBillNo", "req", "Receipt No. is a Mandatory Field" );
				hisValidator.addValidation("strBillNo", "minlen=10", "Receipt No. must be 10 Digits" );
	
				var retVal = hisValidator.validate(); 
				hisValidator.clearAllValidations();
		
					if(retVal){
						var hmode = "GOBILL"; 
						var url = "CashCollectionOfflineTransBSCNT.cnt?hmode="+hmode+"&billNo="+document.forms[0].strBillNo.value;
		
						document.forms[0].strBillNo.disabled = true;
		
						ajaxFunction(url,"19");
						
						setRefundRemarksText();
								
					}else{
						return false;
					}
	  		}else{
	  			
	  			document.getElementById("errMsg").innerHTML = "Invalid Receipt No.";
				document.forms[0].strBillNo.value = "";
				document.forms[0].strBillNo.focus();
	  		}
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
	   		
	   		if(eve.keyCode == 13)
	   		{									
				return goFunc();
			}	   	
	   }else{
	   		return false;
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
	 
	  
	 
	
	/**
	 * findServiceandSave
	 */
	 function findBillServices() 
	 {
		 
		if(!showAlert())
	 		return false;
	 	var ctState = document.forms[0].currentState.value ;
	 		 	
	 	 
	   // Off-line Mode
	 		
	 			
	 			var ctBSerId = document.forms[0].strOffLineBillingService[document.forms[0].strOffLineBillingService.selectedIndex].value ;
			 	var ctReqType = document.forms[0].strOffLineRequestType[document.forms[0].strOffLineRequestType.selectedIndex].value ;
			 	
	 			
	 		//	alert("ctBSerId : "+ctBSerId);
	 		//	alert("ctReqType : "+ctReqType);
	 			
	 		//	alert("offline mode");
	 			// Off-line Receipt Part Payment Mode
	 		if((ctBSerId == '20') && ctReqType == '1')
	 		document.forms[0].hmode.value = "OFFRECPARTPAY";
	 		 
	 		// Off-line Receipt Advance Mode
	 		if(ctBSerId == '19' && ctReqType == '1')
	 			document.forms[0].hmode.value = "OFFRECADV";

			// Off-line Receipt Service Mode	 				 		
	 		if((ctBSerId == '10'|| ctBSerId == '11' || ctBSerId == '12') && ctReqType == '1')
	 			document.forms[0].hmode.value = "OFFRECSER";

			if((ctBSerId == '10'|| ctBSerId == '11' || ctBSerId == '12' || ctBSerId == '13') && ctReqType == '2')
	 			document.forms[0].hmode.value = "OFFREFUNDSER";
			
			// Off-line Estimation Mode	 				 		
	 		if((ctBSerId == '10'|| ctBSerId == '11' || ctBSerId == '12') && ctReqType == '3')
	 			document.forms[0].hmode.value = "OFFESTIMATION";
	 			 		
			if(ctBSerId == '19' && ctReqType == '2')
	 			document.forms[0].hmode.value = "OFFREFUNDADMCANCEL";
	 			
	 		if(ctBSerId == '20' && ctReqType == '2')
	 			document.forms[0].hmode.value = "OFFREFUNDPARTPAYCANCEL";
	 		
	 		if(ctBSerId == '13' && ctReqType == '1')
	 			document.forms[0].hmode.value = "OFFRECPACK";
	 			 		
	// alert(document.forms[0].hmode.value);
	 		 
	
	//return false;
	 		if(document.forms[0].onlyCatCahnge.value!=undefined){
	 			if(document.forms[0].onlyCatCahnge.value=="1")
		 			document.forms[0].submit();
		 		else
		 			return validateAndSubmit(document.forms[0].hmode.value);
	 		}else{
	 			return validateAndSubmit(document.forms[0].hmode.value);
	 		}
	 			
	 			
	 	
	 	
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
		 		 	
	 		 	
	 	// Offline Receipt Part Payment and Offline Receipt Advance 
	 	if(hmodeVal == "OFFRECPARTPAY" || hmodeVal == "OFFRECADV")
	 	{
	 		if(hmodeVal == "OFFRECADV")
	 		{
	 			if(document.forms[0].stracc.value.split('^')[0]!="0")
	 			{
	 				alert("Account Already Opened/Advance Collected. Multiple Accounts cannot be opened");
	 				return false;
	 			}
	 		}
	 		var res = true;	
	 		document.forms[0].strPartpayment.value=Math.round(document.forms[0].strPartpayment.value);
	 		
	 		
	 		
	 		setAdvPartAmt();
	 		
	 		
	 	
	 				
	 		/*var amtCheck = document.forms[0].strChk_value;	 		
	 		if(amtCheck.checked == true)
	 		{
	 			amtCheck.value = 1;	 			
	 			if(document.forms[0].strPartpayment.value == '')
	 			{	 				
	 				alert("Amount is Manditory Field");
	 				document.forms[0].strPartpayment.focus();
	 				return false;
	 			}
	 			
	 			if(document.forms[0].strIsApprovalRequired.value == '1')
	 			if(document.forms[0].strApprovedByCombo[document.forms[0].strApprovedByCombo.selectedIndex].value == 0)
	 			{
	 				alert("Please Select Approved By Value");
	 				document.forms[0].strApprovedByCombo.focus();
	 				return false;
	 			}	 			
	 			
	 			document.forms[0].strRemarksCombo2.disabled = false;
	 			if(document.forms[0].strRemarksCombo2.value == '')
	 			{	 				
	 				alert("Remarks is Manditory Field");
	 				document.forms[0].strRemarksCombo2.focus();
	 				return false;
	 			}	 			
	 		}
	 		else
	 		{
	 			amtCheck.value = 0;
	 		}*/	
	 		if(document.getElementsByName("strOffLineTreatmentCategory")[0].value.split("^")[1] == "3" || document.getElementsByName("strOffLineTreatmentCategory")[0].value.split("^")[1] == "4")
            {
				var letterNo=document.getElementsByName("strCreditLetterNo");
				//alert(letterNo.length);
	
				for(var i=0;i<letterNo.length;i++)
				{							
					//if(document.getElementsByName("strCreditLetterNo")[i].value == "" && document.getElementsByName("strCreditPaymentType")[i].value=="11")//CREDIT
					if(document.getElementsByName("strCreditLetterNo")[i].value == "0" && (document.getElementsByName("strCreditPaymentType")[i].value=="11" || document.getElementsByName("strCreditPaymentType")[i].value=="13"))//CREDIT OR CREDIT URGENT
					{
					  alert("Please Select Credit Letter");
					  return false;
					}
					
			    }
				if(document.getElementsByName("strCreditPaymentType")[0].value=="11" || document.getElementsByName("strCreditPaymentType")[0].value=="13")
				{/*if(letterNo[0].value.split("^")[4]>0)
	 			{*/
					document.forms[0].strPartpayment.value=letterNo[0].value.split("^")[8];
					alert("Credit Letter Limit is "+letterNo[0].value.split("^")[8]+". The Amount is updated to "+letterNo[0].value.split("^")[8]);
			 		setAdvPartAmt();
	 			//}
                 }
             }
	 		
	 		var payTot = parseFloat(document.forms[0].strOfflineTotalPayAmount.value);
	 		
	 		res = checkMultirow('strOfflinePaymentMode', "Payment Mode Already Exists");
	 		
	 			if(res)
	 			{
	 				var hisValidator = new HISValidator("cashCollectionOfflineTransBean");  
					hisValidator.addValidation("strOfflineAmount", "req", "Amount is Mandatory Field" );	
					//hisValidator.addValidation("strOfflineAmount", "numgt=0", "Amount should be Grater than 0.00" );							
					var res = hisValidator.validate();					
					hisValidator.clearAllValidations();					
	 			}
	 			else
	 			{
	 				return res;
	 			}	 		 		
	 		
	 		if(res)
	 		{	 			
	 				document.forms[0].strPartpayment.disabled = false;	 			
	 				return offlineSubmitPart();	 			
	 		}
	 		else
	 		{	 			
	 			return false;
	 		}	 		
	 	}
	 	
	 	
	 	if(document.getElementsByName("strOffLineHospitalService")[0].value=="2" &&  (document.getElementsByName("strOffLineBillingService")[0].value=="19" || document.getElementsByName("strOffLineBillingService")[0].value=="20") &&  document.getElementsByName("strOffLineRequestType")[0].value=="2"){
 			
	 		var pMode="";
	 			 		
	 		if(document.getElementsByName("strOffLineBillingService")[0].value=="19")
	 			pMode=document.getElementById("advancePayMode").value;
	 		else
	 			pMode =document.getElementsByName("lnkVal")[0].value.split("^")[21];
	 		 
 			
 			if(pMode =="1" || pMode =="2" || pMode =="3" || pMode =="4" || pMode =="5" || pMode =="8" || pMode =="9")
 					console.log('payment mode code:::::'+pMode);
 			else{
 				alert("only Advance/Part payment paid by Cash,Cheque,Demand Draft,Credit Card,Debit Card,Internation Debit,Card Internation Credit Card can be refunded");
 				return false;
 			}
 		}
 		 		
	 	// Opd, Ipd, Emergency Receipt Services for Offline
	 	if(hmodeVal == "OFFRECSER" || hmodeVal == "OFFESTIMATION" )
	 	{
	 		
	 			 	deleteUnwantedRows("strOfflineTariffName" , '2' );
	 			 		 		 		
	 		 		var hisValidator = new HISValidator("cashCollectionOfflineTransBean");  
					hisValidator.addValidation("strOfflineTariffName", "req", "Tariff Name is Mandatory Field" );	
					hisValidator.addValidation("strOfflineTempTariffRateUnit", "req", "Tariff Rate/Unit is Mandatory Field" );
					hisValidator.addValidation("strOfflineTariffQty", "req", "Tariff Quantity is Mandatory Field" );
					hisValidator.addValidation("strOfflineTariffUnit", "dontselect=0", "Please Select a Tariff Unit" );
					hisValidator.addValidation("strOfflineAmount", "req", "Amount is Mandatory Field" );
					/*if(document.getElementsByName("strOffLineTreatmentCategory")[0].value.split("^")[1] == "3" || document.getElementsByName("strOffLineTreatmentCategory")[0].value.split("^")[1] == "4")
						hisValidator.addValidation("strPayClientName","dontselect=0","Please Select Client")*/
					
					
					//Validation of file upload which takes only images and pdf's as data of maximum 2 MB of size
					var checkNoOfRows=document.getElementsByName("strOfflineTariffDetailsHidden");
					//alert("checkNoOfRows"+checkNoOfRows);
					//alert("checkNoOfRowscheckNoOfRows.length-1"+checkNoOfRows.length-1);
					for(var j=0;j<checkNoOfRows.length-1;j++ )
					{
					//	var _validFileExtensions = [".jpg", ".jpeg", ".bmp", ".gif", ".png",".pdf"];					   
						var _validFileExtensions = [".jpg", ".jpeg",".pdf"];
						if (document.getElementsByName('uploadedFile2')[j]!=null && document.getElementsByName('uploadedFile2')[j]!=undefined && document.getElementsByName('uploadedFile2')[j].type == "file")
				        {
				            var sFileName = document.getElementsByName('uploadedFile2')[j].value;
				            
				            if (sFileName.length > 0)
				            {
				                var blnValid = false;
				                
				                
				                for (var k= 0; k < _validFileExtensions.length; k++) 
				                {
				                    var sCurExtension = _validFileExtensions[k];
				                    if (sFileName.substr(sFileName.length - sCurExtension.length, sCurExtension.length).toLowerCase() == sCurExtension.toLowerCase()) 
				                    {
				                        blnValid = true;				                        
				                        break;
				                    }				                    
				                }
				                if (!blnValid)
				                {
				                    alert("Only image(s) and pdf(s) of size 2MB can be uploaded");
				                    retVal = false;
									return;				                   
				                }				           
				            }				            
				        }					    
					    
					 }
					
						
					
					//alert(document.getElementsByName("rowLength3")[0].value);
					///in case of credit Patient credit letter number and credit Reference date is mandatory...
					
					if(document.getElementsByName("strOffLineTreatmentCategory")[0].value.split("^")[1] == "3" || document.getElementsByName("strOffLineTreatmentCategory")[0].value.split("^")[1] == "4")
                    {
						var letterNo=document.getElementsByName("strCreditLetterNo");
						var cltamt=0;
						//var c=0;
						//var cltamt=document.forms[0].strOfflineMaxClientBenefitAmount.value;
					    //var cltlmt = document.getElementsByName("strCreditLetterNo")[0].value.split('^')[4];
						//alert(letterNo.length);
						//alert(document.getElementById("strOfflineAmtPaidByClient").value);
						//var ltr=[];
						//var letterMap=new Map();
						//alert(letterNo.length-1);
						for(j=0;j<letterNo.length-1;j++)
						{
							//if(document.getElementsByName("strCreditLetterNo")[i].value == "" && document.getElementsByName("strCreditPaymentType")[i].value=="11")//CREDIT
							if(letterNo[j].value == "0" && (document.getElementsByName("strCreditPaymentType")[j].value=="11" || document.getElementsByName("strCreditPaymentType")[j].value=="13"))//CREDIT OR CREDIT URGENT
							{
							  alert("Please Select Credit Letter");
							  generateRows();
							  return false;
							}
						}
						
						for(var k=0;k<letterNo[0].length;k++)
	        		    {
							cltamt=0;
							if(letterNo[0].options[k].value!="0")
	        				{
		        				//letterNo#Limit
		        				//clKey=letterNo[0].options[k].value.split("^")[0]+"#"+letterNo[0].options[k].value.split("^")[4];
		        				var limit=parseFloat(letterNo[0].options[k].value.split("^")[4]);
		        				if(limit>0)
		        				{
			        				var amtByClient=document.getElementsByName("strOfflineAmtPaidByClient");
			        				//alert(amtByClient.length);
			        				//var amtByClient=document.getElementsByName("strOfflineAmtPaidByClient");
			        				for(var i=0;i<amtByClient.length;i++)
			        				{
			        					/*if(letterNo[i].value == "0" && document.getElementsByName("strCreditPaymentType")[i].value=="11")//CREDIT
										{
										  alert("Please Select Credit Letter");
										  generateRows();
										  return false;
										}*/
			        					if(letterNo[i].value==letterNo[0].options[k].value)
				        				{
				        						cltamt+=parseFloat(amtByClient[i].value);
				        				}		        					
			        				}
			        				//letterMap.set(clKey,cltamt);
			        				if(limit>0 && limit<cltamt)
			        				{
				        				alert("Total Availed Tariff Amount is greater than Credit Limit");
				        				generateRows();
			 							return false;
			        				}
		        				}
		        				var mergeFlag=letterNo[0].options[k].value.split("^")[13];
		        				var newFlag=letterNo[0].options[k].value.split("^")[12];
		        				if(newFlag==1 && mergeFlag==1)
		        				{
		        					for(var a=0;a<letterNo.length-1;a++)
		    						{
		        						if(letterNo[a].value != "0" && (document.getElementsByName("strCreditPaymentType")[a].value=="11" || document.getElementsByName("strCreditPaymentType")[a].value=="13") && letterNo[a].value.split("^")[13]=="0")
		    							{
		    							  alert("Merged Letter cannot be used for Availing Services since its limit has already been combined with that of new letter. Kindly use new letter");
		    							  return false;
		    							}
		    						}
		        				}
	        				}
	        		    }
						
        				/*for(var i=0;i<letterNo.length-1;i++)
						{	
							if(i==0 && document.getElementsByName("strCreditLetterNo")[i].value != "0" && document.getElementsByName("strCreditPaymentType")[i].value=="11")
							ltr[i]=letterNo[i].value;
							else
							{
								for(var j=i-1;j>=0;j--)
								{
								  if(letterNo[i].value != "0" && document.getElementsByName("strCreditPaymentType")[i].value=="11" && letterNo[i].value==letterNo[j].value)
						        	  c=1;
								}
								alert("c"+c);
							if(c!=1 && letterNo[i].value != "0")
								  ltr[i]=letterNo[i].value;
							else
								  ltr[i]="0^0^0^0^0";
							}
							alert(ltr[i]);
						}*/
			            //alert(ltr.length);
						/*for(i=0;i<ltr.length;i++)
						{	
							//alert(document.getElementsByName("strOfflineAmtPaidByClient")[i].value);
							//alert(ltr[i]);
							var cltlmt = ltr[i].split('^')[4];
							
							for(j=0;j<letterNo.length-1;j++)
							{
							//if(document.getElementsByName("strCreditLetterNo")[i].value == "" && document.getElementsByName("strCreditPaymentType")[i].value=="11")//CREDIT
							if(document.getElementsByName("strCreditLetterNo")[j].value == "0" && document.getElementsByName("strCreditPaymentType")[j].value=="11")//CREDIT
							{
							  alert("Please Select Credit Letter");
							  generateRows();
							  return false;
							}
							if(document.getElementsByName("strCreditLetterNo")[j].value != "0" && document.getElementsByName("strCreditPaymentType")[j].value=="11" && parseFloat(cltlmt)!="0.00")//CREDIT
							{
								if(document.getElementsByName("strCreditLetterNo")[j].value==ltr[i])
								{
									cltamt+=parseFloat(document.getElementsByName("strOfflineAmtPaidByClient")[j].value);
								}
									alert(cltlmt);
									alert(cltamt);
								if(parseFloat(cltamt)>parseFloat(cltlmt))
	 			 			    {
	 			 				alert("Total Availed Tariff Amount is greater than Credit Limit");
	 			 				generateRows();
	 							return false;
	 			 			    }*/
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
		 						}*/
							//}
							//}
							//cltamt=0;
							/*if(document.getElementsByName("strCreditRefDate1")[i].value == "" && document.getElementsByName("strCreditPaymentType")[i].value=="11")
							{
							  alert("Please Enter Credit Letter Date");
							  return false;
							}
							if(document.getElementsByName("strCreditRefDate1")[i].value != "" && document.getElementsByName("strCreditPaymentType")[i].value=="11")
							{
								//var datediffVar=dateDiff(document.getElementsByName("strCreditRefDate1")[i].value,document.forms[0].strTempCtDate.value).split('days')[0];
								var retVal=compareDate(document.getElementsByName("strCreditRefDate1")[i].value,document.forms[0].strTempCtDate.value);
								if(retVal.mode==2)
								{
									  alert("Credit Letter Date Can't Be Greater Than Current Date");
									  return false;
								}
								
								var datediffVar=dateDiff(document.getElementsByName("strCreditRefDate1")[i].value,document.forms[0].strTempCtDate.value);
								if(datediffVar!=undefined)
									datediffVar=parseInt(datediffVar.split('days')[0]);
								
								if(datediffVar>parseInt(document.forms[0].strCreditLetterValidity.value))
								{
								  alert("Credit Letter Date Can't Be Old More Than "+document.forms[0].strCreditLetterValidity.value+" Days");
								  return false;
								}
							}*/
							//dateDiff(date_1,date_2) 
					   // }
                   }
				   else
				   {
					   var letterNo=document.getElementsByName("strCreditLetterNo");
					   for(var i=0;i<letterNo.length;i++)
						{							
							//if(!document.getElementsByName("strCreditLetterNo")[i].value == "")
						   if(document.getElementsByName("strCreditLetterNo")[i].id!="strCreditLetterNo#delIndex#" && document.getElementsByName("strCreditLetterNo")[i].value != "0")
							{
							  alert("Credit Details Required Only For Credit Category");
							  document.getElementsByName("strCreditLetterNo")[i].value="0";
							  return false;
							}
							/*if(!document.getElementsByName("strCreditRefDate1")[i].value == "")
							{
								alert("Credit Details Required Only For Credit Category");
								document.getElementsByName("strCreditRefDate1")[i].value = "";
								return false;
							}	*/						
					    }					  
				   }
					if(document.getElementsByName("strOffLineHospitalService")[0].value!= "2" && document.forms[0].strWalletNo.value!=null && document.forms[0].strWalletNo.value!="" && document.getElementsByName("strOfflinePaymentMode")[0].value.split('#')[0]=="7")//If Not IPD and Wallet No Present and selected as payment mode
					{
						 if(parseInt(document.forms[0].strOfflineTotalRecAmount.value)>parseInt(document.forms[0].strAvlWalletMoney.value))
						 {
								 alert("Wallet Balance is less than the total tariff availed. Kindly recharge your wallet.");
								 return false;
						 }
					}
					
					
					var prim_cat=document.forms[0].strCatgoryCode.value;
					var treat_cat=document.getElementsByName("strOffLineTreatmentCategory")[0].value.split("^")[0];
					
					
					 //commented by manisha --start
					
					/*
					if(prim_cat != treat_cat && treat_cat != document.forms[0].strWchCatId.value)
					{
						if(!confirm("Patient Billing Category and Primary Category("+document.getElementById("strCategoryName").innerHTML+") are different. Do you want to continue?"))
						   return false;
							//document.getElementsByName("strOffLineTreatmentCategory")[0].value=prim_cat+'^'+document.forms[0].strPatientDtlHidVal.value.split('^')[12];;
					}
					
					//added by manisha for category bound 
					 if(!validatePayMode())
					 return false; */
					//added by manisha for same payment type for all tariffs 
					if(!validatePaymentType())
						return false;
					//added by manisha for same payment type and payment mode validations
					if(!validateCreditForCMFund())
						return false;
						
					   //comment end
								
					  
					  /* var prim_cat= document.getElementsByName("strOffLineTreatmentCategory")[0].value.split('^')[0]; // document.forms[0].strCatgoryCode.value;
						//alert(prim_cat);  alert(document.getElementsByName("strOffLineTreatmentCategory")[0].value);
						var pay_mode=document.getElementsByName("strOfflinePaymentMode")[0].value;
						
						var strpaymode=document.getElementsByName("strOfflinePaymentMode")[0].value.split('#');*/
						
						
					 
					 
					var res = hisValidator.validate(); 
					hisValidator.clearAllValidations();
					
					if(! res)
					{						
						generateRows();						
						 return false;
					}					
					res = checkMultirow('strOfflineTariffName', "Tariff Name Already Exists");			
					if(! res)
					{						
						generateRows();							
						return false;
					}
					res = checkMultirow('strOfflinePaymentMode', "Payment Mode Already Exists");										
					if(res)
					{						
						calcTotalRecAmount();						
						var arrInputs = document.getElementsByTagName('input');
						 var x=0;
						 
						 for(var i=0; i<arrInputs.length; i++)
						 {
							 if(arrInputs[i].getAttribute('type')=='file')
							 {
								 if(arrInputs[i].id!='uploadedFile#delIndex#')
								 {
									 arrInputs[i].setAttribute('name', 'uploadedFile2'+'['+x+']');
									 x++;
								 }									 
							 }
						}
						return offlineSubmitPart();
					}
					else
					{						
						generateRows();			 			 
			 			return false;
					}
	 	}
	 	
	 	
	 	// Opd, Ipd, Emergency Refund Services for Offline
	 	if(hmodeVal == "OFFREFUNDSER" || hmodeVal == "OFFREFUNDPARTPAYCANCEL"){
	 		
	 		var billObj = document.getElementsByName("strOfflineRefundBillDetails");
	 		
	 		if(billObj.length < 1){
	 			
	 			alert("No Refund Bill(s) Available");
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
	 			else
	 			{
	 				for(var index=0, len = chkObj.length; index<len; index++) 
	 				{
	 					if(chkObj[index].checked)
	 					{
	 						if(document.getElementsByName("strBillTariffRefund")[index].value==0)
	 						{
	 							alert("Refund Qty. should be greater than Zero");
	 							return;
	 						}
	 					}	 				
	 				}
	 			}		
	 				 				
	 		}else{	
	 			alert("No Refund Tariff(s) Available");
	 			return false;
	 		}				
	 					
	 					
	 		var hisValidator = new HISValidator("cashCollectionOfflineTransBean");  
	 		
	 		
	 		//hisValidator.addValidation("strBillTariffRefund", "numgt=0", "Refund Qty. should be greater than Zero" );
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
	 
	 
	 	// Ipd Refund Admission Cancellation for Offline
	 	if(hmodeVal == "OFFREFUNDADMCANCEL"){
	 		
	 		var hisValidator = new HISValidator("cashCollectionOfflineTransBean");  
	 		
	 		if(document.getElementById("offlineTotalPayAmtDivId").innerHTML!=0)
	 			{
		 			hisValidator.addValidation("strOffLineRefundBy", "dontselect=0", "Please Select a Refund By" );						
					hisValidator.addValidation("strOffLineRefundReasonText", "req", "Refund Reason is a Mandatory Field" );
					hisValidator.addValidation("strOfflineRefundDate", "req", "Refund Date is a Mandatory Field" );
	 			}
	 		
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
	 	
	 	if(hmodeVal == "OFFRECPACK")
	 	{
	 		var hisValidator = new HISValidator("cashCollectionOfflineTransBean");  
	 	
	 		
	 		hisValidator.addValidation("strPackageApply", "req", "Package Application is a Mandatory Field" );
	 		hisValidator.addValidation("strPackAppDate", "req", "Package Application Date is a Mandatory Field"  );
			if(document.forms[0].strPackageApply.value=="0")
			{
				alert("Please select an OT package to apply");
				return false;
			}
			if(document.getElementsByName("strOffLineTreatmentCategory")[0].value.split("^")[1] == "3" || document.getElementsByName("strOffLineTreatmentCategory")[0].value.split("^")[1] == "4")
            {
				var letterNo=document.getElementsByName("strCreditLetterNo");
				//alert(letterNo.length);
	
				for(var i=0;i<letterNo.length;i++)
				{							
					//if(document.getElementsByName("strCreditLetterNo")[i].value == "" && document.getElementsByName("strCreditPaymentType")[i].value=="11")//CREDIT
					if(document.getElementsByName("strCreditLetterNo")[i].value == "0" && (document.getElementsByName("strCreditPaymentType")[i].value=="11" || document.getElementsByName("strCreditPaymentType")[i].value=="13"))//CREDIT OR CREDIT URGENT
					{
					  alert("Please Select Credit Letter");
					  return false;
					}
					
			    }
             }
			if(document.forms[0].stravailpack.value!="0^0")
			{
				alert("A Package has already been availed by patient. Another one is not allowed");
				return false;
			}
			var res = hisValidator.validate(); 
			hisValidator.clearAllValidations();

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
	 	
	 	
	// 	hide_popup('billPrintConfirm');
	 	
	  
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
					
					
				}
				
				
				document.forms[0].strOffLineHospitalService.disabled = false;	
					document.forms[0].strOffLineBillingService.disabled = false;
					document.forms[0].strOffLineRequestType.disabled = false;	


		 			document.forms[0].strOffLineRefundReasonText.disabled = false;	
				
					document.getElementById("saveid").style.display = "none";
					
		 	
		 	
		 	////code for adding hidden tariff of consumable charges		
	 		 		
	 	 //var consumableCharges=document.getElementsByName("strConsumableCharge")[0].value
	 	 //var consumablechargeGroupId=document.forms[0].strConsumableChargesGroupId.value;
	 	//var consumablechargeTariffId=document.forms[0].strConsumableChargesTariffCode.value;
	 	
	 	//var hiddenfieldvalue=consumablechargeTariffId+"^"+consumablechargeGroupId+"^"+"0"+"^"+consumableCharges+"^"+consumableCharges+"^"+"1701"+"^"+"1"+"^"+"0"+"^"+"0"+"^"+"0"+"^"+consumableCharges+"^"+consumableCharges+"^"+"0"+"^"+"0"+"^"+"each"+"^"+"0"+"^"+consumablechargeTariffId+"^"+"0"+"^"+"0"+"^"+"General Charges";
	 	/* if(parseInt(consumableCharges)>0)
	 	{
	 	var divInnerHtml="<input type='hidden' name='strOfflineTariffDetailsHidden' value='"+hiddenfieldvalue+"'/>"
	 	divInnerHtml=divInnerHtml+"<input type='hidden' name='strOfflineTariffName' value='Consumable Charges - (Con)'/>"
	 	divInnerHtml=divInnerHtml+"<input type='hidden' name='strOfflineTariffQty' value='1'/>"
	 	divInnerHtml=divInnerHtml+"<input type='hidden' name='strOfflineTariffDiscount' value='0'/>"
	 	divInnerHtml=divInnerHtml+"<input type='hidden' name='strOfflineTariffServiceTax' value='0'/>"
	 	divInnerHtml=divInnerHtml+"<input type='hidden' name='strOfflineTariffNetAmount' value='"+consumableCharges+"'/>"
	 	divInnerHtml=divInnerHtml+"<input type='hidden' name='strOfflineTariffUnit' value='1701^1'/>"
	 	divInnerHtml=divInnerHtml+"<input type='hidden' name='strOfflineTariffServiceTaxAmtVal' value='0'/>"
	 	divInnerHtml=divInnerHtml+"<input type='hidden' name='strOfflineTariffDiscountAmtVal' value='0'/>"
	 	
	 	
	 	
	 		document.getElementById("consumableChargeDiv").innerHTML=divInnerHtml
	 	} */
	 	//alert(document.getElementById("consumableChargeDiv").innerHTML)
				////code for adding hidden tariff of surcharge		
					/*var payTot  = document.getElementsByName("strOfflineAmount");
				    var payMode = document.getElementsByName("strOfflinePaymentMode");	 	 
			 
			 	    //var cashObj = null; 	
			 
			 	    var len = payTot.length - 1 ;	

			 		for(var i = 0; i <= len ; i++)
			 		{
			 			var payModeVal = payMode[i][payMode[i].selectedIndex].value;
                        var surval=0;
					 //alert(payModeVal);
			 		
					if(payTot[i].value!="")
					{
						//alert(document.forms[0].defsurlim.value);
						if(payTot[i].value>0 && parseFloat(payTot[i].value)<=document.forms[0].defsurlim.value)
						{
							if(payModeVal==4) //Credit Card
							{
								surval=Math.round(parseFloat((document.forms[0].strSurCc.value/100)*payTot[i].value));
								payTot[i].value=Math.round(parseFloat(payTot[i].value)+surval);	
								alert("Please collect Rs. "+payTot[i].value+" ("+document.forms[0].strOfflineTotalRecAmount.value+"+"+document.forms[0].strSurCc.value+"% Surcharge)");
							}
							if(payModeVal==5) //Debit Card
							{
								surval=Math.round(parseFloat((document.forms[0].strSurDc.value/100)*payTot[i].value));
								payTot[i].value=Math.round(parseFloat(payTot[i].value)+surval);
								alert("Please collect Rs. "+payTot[i].value+" ("+document.forms[0].strOfflineTotalRecAmount.value+"+"+document.forms[0].strSurDc.value+"% Surcharge)");
							}
							if(payModeVal==8) // International Credit Card
							{
								surval=Math.round(parseFloat((document.forms[0].strSurIc.value/100)*payTot[i].value));
								payTot[i].value=Math.round(parseFloat(payTot[i].value)+surval);
								alert("Please collect Rs. "+payTot[i].value+" ("+document.forms[0].strOfflineTotalRecAmount.value+"+"+document.forms[0].strSurIc.value+"% Surcharge)");
							}
							if(payModeVal==9) //// International Debit Card
							{
								surval=Math.round(parseFloat((document.forms[0].strSurId.value/100)*payTot[i].value));
								payTot[i].value=Math.round(parseFloat(payTot[i].value)+surval);
								alert("Please collect Rs. "+payTot[i].value+" ("+document.forms[0].strOfflineTotalRecAmount.value+"+"+document.forms[0].strSurId.value+"% Surcharge)");
							}
						}
						else if(parseFloat(payTot[i].value)>document.forms[0].defsurlim.value)
						{
							if(payModeVal==4)
							{
								surval=Math.round(parseFloat((document.forms[0].strSurCc1.value/100)*payTot[i].value));
								payTot[i].value=Math.round(parseFloat(payTot[i].value)+surval);
								alert("Please collect Rs. "+payTot[i].value+" ("+document.forms[0].strOfflineTotalRecAmount.value+"+"+document.forms[0].strSurCc1.value+"% Surcharge)");
							}
							if(payModeVal==5)
							{
								surval=Math.round(parseFloat((document.forms[0].strSurDc1.value/100)*payTot[i].value));
								payTot[i].value=Math.round(parseFloat(payTot[i].value)+surval);
								alert("Please collect Rs. "+payTot[i].value+" ("+document.forms[0].strOfflineTotalRecAmount.value+"+"+document.forms[0].strSurDc1.value+"% Surcharge)");
							}
							if(payModeVal==8)
							{
								surval=Math.round(parseFloat((document.forms[0].strSurIc1.value/100)*payTot[i].value));
								payTot[i].value=Math.round(parseFloat(payTot[i].value)+surval);
								alert("Please collect Rs. "+payTot[i].value+" ("+document.forms[0].strOfflineTotalRecAmount.value+"+"+document.forms[0].strSurIc1.value+"% Surcharge)");
							}
							if(payModeVal==9)
							{
								surval=Math.round(parseFloat((document.forms[0].strSurId1.value/100)*payTot[i].value));
								payTot[i].value=Math.round(parseFloat(payTot[i].value)+surval);
								alert("Please collect Rs. "+payTot[i].value+" ("+document.forms[0].strOfflineTotalRecAmount.value+"+"+document.forms[0].strSurId1.value+"% Surcharge)");
							}
						}
						if(surval>0)
						{
							
							if(document.forms[0].currentBserviceId.value=="10" || document.forms[0].currentBserviceId.value=="11" || document.forms[0].currentBserviceId.value=="12")
							{
								document.forms[0].strOfflineTotalRecAmount.value = payTot[i].value;
								document.forms[0].strOfflineTotalActualTariffAmount.value= payTot[i].value;
							}
							else if(document.forms[0].currentBserviceId.value=="13" || document.forms[0].currentBserviceId.value=="19" || document.forms[0].currentBserviceId.value=="20" || document.forms[0].currentBserviceId.value=="21")
								document.forms[0].strOfflineTotalActualTariffAmount.value= surval;
							document.getElementById("totalRecAmtDivId").innerHTML = payTot[i].value;
							document.getElementById("totalRecAmtLabId").innerHTML = payTot[i].value;
							document.getElementsByName("strOfflineAmount")[0].value = payTot[i].value;			
						    document.forms[0].strOfflineTotalPayAmount.value = payTot[i].value;
						    document.getElementById("offlineTotalPayAmtDivId").innerHTML = payTot[i].value;
						    //alert(document.getElementById("offlinePatNetPayDivId"));
						    if(document.getElementById("offlinePatNetPayDivId") != null)
					 		{		 			 			
						 		document.forms[0].strOfflinePatNetPayAmount.value = payTot[i].value;
						 		document.getElementById("offlinePatNetPayDivId").innerHTML = payTot[i].value;	 		
					 		}		 		
					 		
						    if(document.getElementsByName("strOfflineTariffName").length > 0 && document.getElementsByName("strOfflineTariffName")[0].value.length > 0 )
				 	 		{
							    addRows(new Array('strOfflineTariffName','strOfflineTariffDetailsHidden','strOfflineTempTariffRateUnit','strOfflineTariffRateUnit','strOfflineTariffQty','strOfflineTariffUnit','strOfflineTariffServiceTax', 'strOfflineTariffServiceTaxAmtVal','strOfflineTariffDiscount','strOfflineTariffDiscountAmtVal' ,'strOfflineTariffNetAmount','strOfflineTotalActualTariffAmtVal','strCreditPaymentType','strCreditLetterNo','uploadedFile'),new Array('t','t','t','t','t','s','t','t','t','t','t','t','s','s','t'),'2','1','R');
							    var offTariffName=document.getElementsByName("strOfflineTariffName");
							    var length=offTariffName.length;
							    document.getElementsByName("strOfflineTariffName")[length-2].value="(SURC)-Surcharge";
							    document.getElementsByName("strOfflineTariffQty")[length-2].value="1";
							    document.getElementsByName("strOfflineTariffDetailsHidden")[length-2].value="1090001^109^0.00^"+surval+"^"+surval+"^1701^1^0^1^0^"+surval+"^"+surval+"^0^0^No.^0^0^0^0^Bill Chg";
							    document.getElementsByName("strOfflineTariffRateUnit")[length-2].value=surval+" / No.";
							    document.getElementsByName("strOfflineTariffNetAmount")[length-2].value=surval;
				 	 		}
						    /*for(var nTmpI=0; nTmpI<offTariffName.length; nTmpI++){
								alert(document.getElementsByName("strOfflineTariffName")[nTmpI].value);
							}*/
						/*}
					}
			 		/*alert(document.forms[0].strOfflineTotalActualTariffAmount.value);
			 		alert(document.forms[0].strOfflineTotalRecAmount.value);*/
			 		
					
	 	document.forms[0].submit();
	 }
	
	
	/**
	 * confirmCancel
	  
	 */
	 function confirmCancel() 
	 {	 	
	 //	hide_popup('billPrintConfirm');
	 	
	 			 	 
	 	 		if(document.getElementsByName("strOfflineTariffName").length > 0 && document.getElementsByName("strOfflineTariffName")[0].value.length > 0 )
	 	 		{	 	 			
	 	 					//addRows(new Array('strOfflineTariffName','strOfflineTariffDetailsHidden','strOfflineTempTariffRateUnit','strOfflineTariffRateUnit','strOfflineTariffQty','strOfflineTariffUnit','strOfflineTariffServiceTax', 'strOfflineTariffServiceTaxAmtVal','strOfflineTariffDiscount','strOfflineTariffDiscountAmtVal' ,'strOfflineTariffNetAmount','strOfflineTotalActualTariffAmtVal'),new Array('t','t','t','t','t','s','t','t','t','t','t','t'),'2','1','R')
	 	 					addRows(new Array('strOfflineTariffName','strOfflineTariffDetailsHidden','strOfflineTempTariffRateUnit','strOfflineTariffRateUnit','strOfflineTariffQty','strOfflineTariffUnit','strOfflineTariffServiceTax', 'strOfflineTariffServiceTaxAmtVal','strOfflineTariffDiscount','strOfflineTariffDiscountAmtVal' ,'strOfflineTariffNetAmount','strOfflineTotalActualTariffAmtVal','strCreditPaymentType','strCreditLetterNo','uploadedFile'),new Array('t','t','t','t','t','s','t','t','t','t','t','t','s','s','t'),'2','1','R');	 	 			
	 	 		}
	 		
	 	 		if(!document.forms[0].strPatientMode[1].checked)
	 	 			document.forms[0].strCrNo.disabled= true;
		 					 			
	 						 			
		 			var payDtl = document.getElementsByName("strOfflinePaymentDtls");
		 			var payMode = document.getElementsByName("strOfflinePaymentMode");
		 			
		 			var len = payDtl.length - 1;
							
					for(var index=0; index<len; index++) 
					{					
						if(payMode[index].value.split('#')[0] != 1)
						payDtl[index].disabled = true;					
					}
		 			
					var arrInputs = document.getElementsByTagName('input');
					 var x=0;
					 
					 for(var i=0; i<arrInputs.length; i++)
					 {
						 if(arrInputs[i].getAttribute('type')=='file')
						 {
							 if(arrInputs[i].id!='uploadedFile#delIndex#')
							 {
								 arrInputs[i].setAttribute('name', 'uploadedFile2');
								 x++;
							 }									 
						 }
					}	  	 
	 	
	 	return false;
	 }
	
	   
	
	
	/**
	 * offlineSubmitPart
	 * @param {type}  
	 */
	 function offlineSubmitPart() 
	 {	 
		 
		 var paymodval= document.getElementsByName("strOfflinePaymentMode")[0].value;
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
	 			if(validateTotalPaymentAmt('0'))
	 			{
	 				var refTot = parseFloat(document.forms[0].strOfflineRefundAmount.value);	 		 		
		 			if((refTot <= 0 && (ctReqType == '1' || ctReqType == '3')) || (refTot >= 0 && ctReqType == '2'))
		 			{
			 			document.forms[0].strCrNo.disabled= false;		 						 			
			 			var payDtl = document.getElementsByName("strOfflinePaymentDtls");
			 			var payMode = document.getElementsByName("strOfflinePaymentMode");
			 			
			 			var len = payDtl.length - 1;
								
						for(var index=0; index<len; index++) 
						{
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
							          document.forms[0].strAlertPromptConfirmFlag.value="1";
							          confirmOk();
							        },
							        Close: function() {
							          $( this ).dialog( "close" );
							          document.forms[0].strAlertPromptConfirmFlag.value="1";
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
			 			 }	*/	
						//confirmOk();
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

					document.forms[0].strOffLineHospitalService.disabled = false;	
					document.forms[0].strOffLineBillingService.disabled = false;
					document.forms[0].strOffLineRequestType.disabled = false;	


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
	
	 
	 
	 /**
	  * confirmLogic
	  * @param {}  
	  */
	  function confirmLogic() {
	  	
	  	if(document.forms[0].strConfirmationType.value == '0'){
	  		
	  		document.forms[0].billConfirmCancel.focus();
	  		
	  	}else{
	  		
	  		document.forms[0].billConfirmOk.focus();
	  		
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

			
/**
* ajaxFunction is the global function to retrieve data using ajax.
* param 1. myurl - url to be given by the user.
* param 2. mode - there will be hardcoded function i.e. getAjaxResponse(res,mode). This function will be defined by
* param 3. resFunctionName -  function name in which user need ajax Response
the developer. Mode specifies unique value provided by user at the time of calling ajaxfunction().
* This function will recieve the final response(to be defined at user end).
*/

var gblResFunctionName = "";

function ajaxFunction3(myurl,mode,resFunctionName)
{		
	userMode = mode;
	gblResFunctionName = resFunctionName;
	// checking browser 
	if (navigator.userAgent.indexOf("Opera")>=0){
		alert("This example doesn't work in Opera"); 
		return; 
	}
	if (navigator.userAgent.indexOf("MSIE")>=0) { 
		var strName="Msxml2.XMLHTTP"
		
		if (navigator.appVersion.indexOf("MSIE 5.5")>=0){
			strName="Microsoft.XMLHTTP"
		} 

		try { 
			objXmlHttp=new ActiveXObject(strName)
			objXmlHttp.onreadystatechange=sendReqUsingMethodName2
		} 
		catch(e){ 
			alert("Error. Scripting for ActiveX might be disabled");
			return; 
		} 
	}
	else {
		if (navigator.userAgent.indexOf("Mozilla")>=0){
			objXmlHttp=new XMLHttpRequest();
			objXmlHttp.onload=sendReqUsingMethodName2
			objXmlHttp.onerror=sendReqUsingMethodName2
		}
	}

	objXmlHttp.open("GET",myurl,true)
	objXmlHttp.send(null)
} 

//internal function called from ajaxFunction() function
function sendReqUsingMethodName2()
{	
	function adt_create_loading_msg(){var qh=80;var qw=300;var dh=0;var dw=0;if(window.innerHeight){dh=window.innerHeight;dw=window.innerWidth;}else {dh=document.documentElement.clientHeight;dw=document.documentElement.clientWidth;}var tpos=parseInt((dh-qh)/2);var lpos=parseInt((dw-qw)/2);var wt='<div id="qmvi_loading_div" style="top:'+tpos+'px;left:'+lpos+'px;height:'+qh+'px;width:'+qw+'px;position:absolute;text-align:center;font-family:Arial;text-decoration:none;font-weight:normal;font-size:13px;color:#00224A;background-color:#ffffff;border-width:1px;border-color:#828EA2;border-style:solid;">';wt+='<div style="padding:20px;"><img src="../../hisglobal/images/loading.gif" width=16 height=16><br><br><font size="2" weight="bold">Fetching Details.Please Wait!</font></div>';wt+='</div>';document.getElementById("normalMsg").innerHTML=wt;}
	
	if (objXmlHttp.readyState==4 || objXmlHttp.readyState==200) 
	{
		var res = objXmlHttp.responseText;		
		eval(gblResFunctionName + '(res' + "," + userMode + ")"); // evel is a keyword - it calls the function passed in it as argument.
		
		 //document.getElementById("normalMsg").style.display="none";//hiding normal msg
		 try
		 {
	     	autoTabIndexing();
	     }
		 catch(_Err)
		 {
	     	//alert("Application Error[TabIndex not Included] Please Contact System Administrator");
	     }
		 try
		 {
	     	eventElementObj.focus();
	     }
		 catch(_Err)
		 {	     	
	     }
	} 
}


function openPrintPopUp()
{
	if(document.forms[0].isOpenPopUp.value==1 && document.forms[0].printMode.value==1)//Printing Over Laser Printer
	{
			window.print();
			//window.close();
		
		/*var url='CashCollectionOfflineTransBSCNT.cnt?hmode=PRINTSLIP&filePath='+document.forms[0].filePath.value
		  child = window.open(url,'popupWindow','status=yes,scrollbars=yes,height=600,width=500,left=400,top=100');  
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
function uploadSet(obj)
{
	//alert(obj.value);
	
	//document.getElementById(obj.id).innerHTML
}
function calcCreditAmount(obj,index)
{
	var cat=document.getElementsByName("strOffLineTreatmentCategory")[0].value.split("^")[1];//Category Group--0 Paid,1-Emp/Staff,2,-Free,3,4-Credit
	//alert(obj.value);
	if((obj.value=="11" || obj.value=="13") && !(cat=="3" || cat=="4"))//Credit Payment Type Selected And Category Group Not Paid
	{
		alert("Credit Billing Not Allowed for Paid/Free/Staff Category");
		obj.value="10";
		return;
	}
	else
	{
		//document.getElementById("strCreditLetterNo"+index).readOnly = false;
		
		/*if(document.forms[0].strOffLineBillingService.value=="19" || document.forms[0].strOffLineBillingService.value=="20")
			document.getElementById("strCreditLetterNoId"+index).disabled = false;
		else*/
		if(document.forms[0].strOffLineBillingService.value!="19" && document.forms[0].strOffLineBillingService.value!="20")
			document.getElementById("strCreditLetterNo"+index).disabled = false;
	}
	
	if(obj.value=="11")//Credit
	{
		if(document.forms[0].strOffLineBillingService.value!="19" && document.forms[0].strOffLineBillingService.value!="20")
		calcOffLineTariffNetAmount(index) ;
		
		//document.getElementsByName("strOfflinePaymentMode")[0].value="10";//Reset To CM Relief Fund
		var paymentMode= document.getElementsByName("strOfflinePaymentMode")[0];
		for(j=0;j<=paymentMode.options.length;j++)
		{ 
			if(paymentMode.options[j].value.split('#')[0]=="10")
			{		
		document.getElementsByName("strOfflinePaymentMode")[0].value=paymentMode.options[j].value;
		break;
			}
	    }
	}
	else if(obj.value=="10")//Paid
	{
		//document.getElementById("strCreditLetterNo"+index).value = "";
		if(document.forms[0].strOffLineBillingService.value!="19" && document.forms[0].strOffLineBillingService.value!="20")
		document.getElementById("strCreditLetterNo"+index).value = "0";
		
		//document.getElementsByName("strOfflinePaymentMode")[0].value="1";//Reset To Cash
		var paymentMode= document.getElementsByName("strOfflinePaymentMode")[0];
		for(j=0;j<=paymentMode.options.length;j++)
		{ 
			if(paymentMode.options[j].value.split('#')[0]=="1")
			{		
		document.getElementsByName("strOfflinePaymentMode")[0].value=paymentMode.options[j].value;
		break;
			}
	    }
		
		
		//document.getElementById("strCreditLetterNo"+index).readOnly = true;
		//document.getElementById("strCreditLetterNo"+index).disabled = true;
		calcOffLineTariffNetAmount(index) ;
	}	
	else if(obj.value=="12")//Paid Urgent
	{
		//document.getElementById("strCreditLetterNo"+index).value = "";
		if(document.forms[0].strOffLineBillingService.value!="19" && document.forms[0].strOffLineBillingService.value!="20")
		document.getElementById("strCreditLetterNo"+index).value = "0";
		
		//document.getElementsByName("strOfflinePaymentMode")[0].value="1";//Reset To Cash
		var paymentMode= document.getElementsByName("strOfflinePaymentMode")[0];
		for(j=0;j<=paymentMode.options.length;j++)
		{ 
			if(paymentMode.options[j].value.split('#')[0]=="1")
			{		
		document.getElementsByName("strOfflinePaymentMode")[0].value=paymentMode.options[j].value;
		break;
			}
	    }
		
		//document.getElementById("strCreditLetterNo"+index).readOnly = true;
		//document.getElementById("strCreditLetterNo"+index).disabled = true;
		calcOffLineTariffNetAmountUrgent(index) ;
	}
	else if(obj.value=="13")//Credit Urgent
	{
		if(document.forms[0].strOffLineBillingService.value!="19" && document.forms[0].strOffLineBillingService.value!="20")
		calcOffLineTariffNetAmountUrgent(index) ;
		
		//document.getElementsByName("strOfflinePaymentMode")[0].value="10";//Reset To CM Relief Fund
		var paymentMode= document.getElementsByName("strOfflinePaymentMode")[0];
		for(j=0;j<=paymentMode.options.length;j++)
		{ 
			if(paymentMode.options[j].value.split('#')[0]=="10")
			{		
		document.getElementsByName("strOfflinePaymentMode")[0].value=paymentMode.options[j].value;
		break;
			}
	    }
		
	}
}
function applySameCreditDtls(obj)
{
	var tariffs = document.getElementsByName("strOfflineTariffName");
 	var payType = document.getElementsByName("strCreditPaymentType");
 	var creditLetter = document.getElementsByName("strCreditLetterNo");
 	//var creditLetterDate = document.getElementsByName("strCreditRefDate1");
 		
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
 				//if(creditLetterVal=="" || creditLetterDateVal=="" )
 				if(creditLetterVal=="0")
 		 		{
 		 		 		alert("Enter Credit Details In First Row");
 		 		 		obj.checked=false;
 		 		 		return;
 		 		}
 				if((payType[i].value=="11" || payType[i].value=="13"))//Credit or Credit Urgent
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
	 						//optValue.split('^')[12]="0";//0 is assigned since only 1 added letter is to be inserted.
	 						newOption.value = optValue;
	 						newOption.selected = 'selected';
	 						//newOption.id = "strNewCreditLetterNoOptionId";
	 						daySelect.appendChild(newOption);
	 						//hide_popup('creditDtlMenu');
						} 
 	 			} 				
 			} 	 		
 	 	}
 		else
 		{
 			for(var i=0; i<tariffs.length; i++) 
 			{
 				if((payType[i].value=="11" || payType[i].value=="13"))//Credit or Credit Urgent
 	 			{
 						creditLetter[i].value="0";
 						//creditLetterDate[i].value="";
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
		var tariffs = document.getElementsByName("strOfflineTariffName");
	 	var payType = document.getElementsByName("strCreditPaymentType");
	 	
	 //	alert(tariffs.length);
	 //	alert(payType.length);
	 	if(payType.length==0 || payType==null || payType==undefined)
	 	{
	 		return true;
	 	}	
	 	
	 	if(tariffs.length == 2 ||  tariffs.length==1)
	 	{
	 		return true;
	 	}
	 	
	 	if(tariffs.length > 2)
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
//added by manisha for Payment type & payment mode wise Validations..  credit categores with credit payment type only and cash with paid only

function validateCreditForCMFund()
{
	//alert("cm fund");
	 	var tariffs = document.getElementsByName("strOfflineTariffName");
	 	var payType = document.getElementsByName("strCreditPaymentType");
	 //alert(payType.value);
	 	if(document.getElementsByName("strOfflinePaymentMode")[0].value.split('#')[0]=="10") //cm relief fund
	 		{
	 		for(var j=0; j<tariffs.length-1; j++) 
 			{
	 			
 			 	var index= payType[j].selectedIndex;
 				//alert(payType[j][index].value);
 				 if (payType[j][index].value == "10" ||  payType[j][index].value == "12")  // credit options 
 				  { 
 					 alert("select Payment Type as 'Credit' for Payment Mode 'CM Relief Fund' ");
					 return false;
 				  }
 				 
 			 }
	 		 
	 		}
	 	
	 	if(document.getElementsByName("strOfflinePaymentMode")[0].value.split('#')[0]=="1") //cash
 		{
 		for(var j=0; j<tariffs.length-1; j++) 
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


function validateWallet()
{
	if(document.forms[0].strWalletNo.value!=null && document.forms[0].strWalletNo.value!="")//Wallet Details Present
	{
		if(parseInt(document.forms[0].strAvlWalletMoney.value)>0 )//Avalable Wallet Money Present
		{
			if(parseInt(document.forms[0].strAvlWalletMoney.value)<parseInt(document.forms[0].strOfflineTotalRecAmount.value) )//Avalable Wallet Money Present
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
				gblAmount.focus();
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
		// hide_popup('payDtlWalletMenu');
		
		//document.getElementsByName("strOnlinePaymentMode")[0].value="1"//Reset To Cash
		var paymentMode= document.getElementsByName("strOfflinePaymentMode")[0];
		for(j=0;j<=paymentMode.options.length;j++)
		{ 
			if(paymentMode.options[j].value.split('#')[0]=="1")  //Reset To Cash
			{		
				document.getElementsByName("strOfflinePaymentMode")[0].value=paymentMode.options[j].value;
				break;
			}
	    }
			
		return false;
	}
}
function resetAmt()
{
document.getElementById("totalRecAmtDivId").innerHTML = "0.00";
document.getElementById("totalRecAmtLabId").innerHTML = "0.00";
document.getElementById("totalCltAmtLabId").innerHTML = "0.00";
document.forms[0].strOfflineTotalRecAmount.value = 0;				

document.getElementById("offlineTotalPayAmtDivId").innerHTML = "0.00";
document.forms[0].strOfflineTotalPayAmount.value = 0;			

document.getElementById("offlineRefundAmtDivId").innerHTML = "0.00";
document.forms[0].strOfflineRefundAmount.value = 0;		
document.getElementsByName("strOfflineAmount")[0].value = '0.00';
document.forms[0].strNewCreditLetterAddedFlag.value=0;
}
function calcOffLineTariffNetAmountUrgent(index) 
{
	var tariff =  document.getElementById("strOfflineTariffName"+index).value;	 		 	
	var a = document.getElementById("strOfflineTariffDetailsHidden"+index).value.split('^');
	//"1240003^124^0.00^24.00^24.00^1701^1^0^1^1^24.00^24.00^0^0^No^1^10003^0^0^Biochemistry";18 VALUES
	//TARIFFID^GROUPCODE^PROCEDURE_COST^TARIFF_COST^HBLNUM_COST^RATEUNIT^UNITBASEVALUE^IS_PACKAGE^IS_ADVANCE^IS_REFUNDABLE^
	//HBLNUM_DEFAULT_RATE^HBLNUM_COST^MAX_DISCOUNT^SERVICE_TAX^UNITNAME^SBLNUM_SERVICE_ID^GSTRTARIFFID^UNDEFINED_CHARGE^GROUPNAME
	
	var b=true;
	for(var i = 0 ; i<a.length; i++)
		if(a[i]!="0")
		{
			b=false;
			break;
		}
	if(b || tariff != '')
	{
		var temp = document.getElementById("strOfflineTariffDetailsHidden"+index).value.split('^');	
		//alert(document.forms[0].strUrgSur.value);
		var urg=document.forms[0].strUrgSur.value;
		var rate = temp[4]*(100+parseFloat(urg))/100;
		var actRate = temp[11]*(100+parseFloat(urg))/100;
		if(temp[17] == '1')//UNDEFINED CHARGES
		{	 			
			rate = document.getElementById("strOfflineTempTariffRateUnit"+index).value*(100+parseFloat(urg))/100;
			actRate = rate*(100+parseFloat(urg))/100;//ACTURAL RATE AND USER ENTRY RATE ALL SHOULD BE SAME.CHANGED ON 29.05.17 BY AJAY DESHWAL
		}
		//document.getElementById("strOfflineTariffRateUnit"+index).value.split(' /')[0]=rate;
		document.getElementById("strOfflineTariffRateUnit"+index).value=Math.round(rate)+" / No.";
		var creditType=document.getElementById("strCreditPaymentType"+index);
	  	/*if(creditType.value=="11")//Credit
	  		var rate = 0;
	  	else
	  		var rate = temp[3];*/
	  	
		
		var rateBaseValue = temp[6];
		var qtyBaseValue = document.getElementById("strOfflineTariffUnit"+index).options[document.getElementById("strOfflineTariffUnit"+index).selectedIndex].value;
		var qtyVal =  document.getElementById("strOfflineTariffQty"+index).value;
		var discountVal = document.getElementById("strOfflineTariffDiscount"+index).value;
		var serviceVal = document.getElementById("strOfflineTariffServiceTax"+index).value;
		var hiddenVal = document.getElementById("strOfflineTariffDiscountConfigDetails"+index).value;
		var discountType = 0;
		
		var netAmt = 0;
		var amtPaidByClient = 0; 
		
		if(hiddenVal != '')
		{	 			
			var tempArr = hiddenVal.split(',');	 			
			discountVal = tempArr[0];
			discountType = tempArr[1];
		}
		
		
		var calAmt = calTrfNetAmountWithActTrfAmt(rate, actRate ,rateBaseValue,discountVal,discountType,qtyVal,qtyBaseValue,serviceVal,0,0);
		if(creditType.value=="13")//Credit Urgent
		{
			netAmt = roundValue(0.00,2);
			amtPaidByClient=calAmt.oNetTrfAmt;
		}	 			
	  	else if(creditType.value=="12")//Paid Urgent
	  	{
	  		netAmt = calAmt.oNetTrfAmt;
	  		amtPaidByClient=roundValue(0.00,2);
	  	}
	///add consumabale charges
	//var groupid =temp[1];
	//var groupConsumableCharge =temp[18];
	//alert(groupid)
	//alert(groupConsumableCharge)
	
	//addConsumableCharge(groupid,groupConsumableCharge)
	
	
		if(netAmt < 0)
		{
			
			alert("Net Amount Value is Less than Rs. 0.00, Please Check the Discount Amount");
			
			document.getElementById("strOfflineTariffDiscount"+index).value = 0;					
			document.getElementById("strOfflineTariffDiscountDivId"+index).innerHTML = "0";
				
			var temp =  document.getElementById("strOfflineTariffDiscountConfigDetails"+index).value.split(",");
															
			document.getElementById("strOfflineTariffDiscountConfigDetails"+index).value = "0,1,"+temp[2]+","+temp[3]+","+temp[4]+","+temp[5];
		 				 	
		 	calcOffLineTariffNetAmount(index);			 	
		 				 			
			return false;
		}
			
			
			document.getElementById("strOfflineTariffServiceTaxAmtVal"+index).value = calAmt.oSerAmt;
			document.getElementById("strOfflineTariffDiscountAmtVal"+index).value = calAmt.oDisAmt;
			//alert("1");
			document.getElementById("strOfflineTotalActualTariffAmtVal"+index).value = calAmt.oActTrfAmt;
		 
			document.getElementById("strOfflineTariffNetAmount"+index).value = Math.round(netAmt);
			document.getElementById("strOfflineAmtPaidByClient"+index).value = Math.round(amtPaidByClient);
			document.getElementById("strOfflineTariffNetAmountDivId"+index).innerHTML = Math.round(netAmt);
			
	
		calcTotalRecAmount(index);	
	}	 	
}
function setAdvAmt()
{
	var temp=document.forms[0].strPackageApply.value.split("^");
	if(document.forms[0].strPackageApply.value!="0")
    {
	      if(temp[1]!="")
	      {
	    	  if(document.forms[0].strOffLineTreatmentCategory.value.split("^")[1] == "3" || document.forms[0].strOffLineTreatmentCategory.value.split("^")[1] == "4")
	    	  {
	    		  document.getElementById("totalCltAmtLabId").innerHTML = Math.round(temp[1]);
	    		  document.forms[0].strOfflineTotalRecAmount.value = Math.round(temp[1]);
	    		  document.getElementsByName("strOfflineAmount")[0].value = "0.00";
	    	  }
	    	  else
	    	  {
		    	  document.getElementById("totalRecAmtDivId").innerHTML = Math.round(temp[1]);
		    	  document.getElementById("totalRecAmtLabId").innerHTML = Math.round(temp[1]);
		    	  document.forms[0].strOfflineTotalRecAmount.value = Math.round(temp[1]);	
	
		    	  document.getElementById("offlineTotalPayAmtDivId").innerHTML = Math.round(temp[1]);
		    	  document.forms[0].strOfflineTotalPayAmount.value = Math.round(temp[1]);
		    	  document.getElementsByName("strOfflineAmount")[0].value = Math.round(temp[1]);
	    	  }
	    	 
	   	  }
	      else
	      {
	    	  
		      document.getElementById("totalRecAmtDivId").innerHTML ="0.00";
	    	  document.getElementById("totalRecAmtLabId").innerHTML ="0.00";
	    	  document.getElementById("totalCltAmtLabId").innerHTML = "0.00";
	    	  document.forms[0].strOfflineTotalRecAmount.value = 0;	
	
	    	  document.getElementById("offlineTotalPayAmtDivId").innerHTML = "0.00";
	    	  document.forms[0].strOfflineTotalPayAmount.value = 0;
	    	  document.getElementsByName("strOfflineAmount")[0].value = "0.00";
	      }
    }
}
function getPackageAmount()
{    
		var hmode ="PACKAGEAMOUNT";
		wardId =  document.getElementsByName("strOffLineWard")[0].value;
		var url = "CashCollectionOfflineTransBSCNT.cnt?hmode="+hmode+"&treatmentCat="+document.getElementsByName("strOffLineTreatmentCategory")[0].value+"&ward="+wardId;
		//alert(url);
		ajaxFunction(url,"31");
		//alert("1");
		if(document.forms[0].strPackageApply.value!="" && document.forms[0].strPackageApply.value!="0") 
		    alert("Applied Package has been reset. You need to re-apply");
		
		//splWardId =  document.getElementsByName("strOffLineSpecialWard")[0].value;	
		
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
	document.getElementsByName("strOfflinePaymentMode")[0].value="1";//Reset To Cash
}
function cardlogic()
{
	var payTot  = document.getElementsByName("strOfflineAmount");
    var payMode = document.getElementsByName("strOfflinePaymentMode");
    
  

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
		document.getElementById("totalCardAmtDivId").innerHTML = "<td class='LABEL' colspan='2'><font color='red'>#</font><font size='2' color='red'>Final Transaction Amount(with Surcharge)(<img src='/HBIMS/hisglobal/images/INR.png'>)&nbsp;<b>"+amt+"</b></font></td>";
		document.getElementById("totalCardAmtDivId1").innerHTML = "<td class='LABEL' colspan='2'><font color='red'>#</font><font size='2' color='red'>Final Transaction Amount(with Surcharge)(<img src='/HBIMS/hisglobal/images/INR.png'>)&nbsp;<b>"+amt+"</b></font></td>";
		document.getElementById("totalBillAmtDivId").innerHTML = "<td class='LABEL'>Billed Amount(<img src='/HBIMS/hisglobal/images/INR.png'>)&nbsp;"+document.forms[0].strOfflineTotalRecAmount.value+"</td>";
		document.getElementById("totalSurAmtDivId").innerHTML =  "<td class='LABEL'>Surcharge Amount(<img src='/HBIMS/hisglobal/images/INR.png'>)&nbsp;"+surval+"</td>";
	}
		}
}



function advanceRefund(){
	
	
	
	/*document.getElementById("offlineAdmissionCancellationDivId").style.display="none";*/
	document.getElementById("offlineDepEpdTreatWardDivId").style.display="none";
	document.getElementById("RefundadvanceDtlsDiv").style.display="";
	document.getElementById("accountCatDiv").style.display="";
	document.getElementById("offlineTariffDivId").style.display="none";
	
	
	
	document.forms[0].strAdvCancelRefund.value=2;
	
	/*var url = "CashCollectionOfflineTransBSCNT.cnt?hmode=advanceDtls";
	ajaxFunction(url,"32");*/
	
}


function advanceRefundCancel(obj)
{
	
	var refundDtl=document.getElementById("otherRefundDetailsDivId");
	
	if(obj.checked==true)
		{
			
					if(document.getElementById("onlyRefund").value=="1")
						{
						
							if(obj.value=="1"){
								alert('After Partpayement or Service Availed , Advance can not be cancelled,Only Refund can be done');
								document.getElementById("changeAccCat").style.display="none"; 
								document.getElementById("otherRefundDetailsDivId").style.display="none";
								document.getElementsByName("accountCatDivCheked")[0].checked=false;
								obj.checked=false;
							}else
							{
								document.getElementById("otherRefundDetailsDivId").style.display="";
								refundDtl.querySelectorAll("td")[0].innerHTML="<font color='red'>*</font>Refund By:";
								refundDtl.querySelectorAll("tr")[1].querySelectorAll("td")[0].innerHTML="<font color='red'>*</font>Refund Reason:";
								refundDtl.querySelectorAll("tr")[3].querySelectorAll("td")[0].innerHTML="Refund Date:";
							}
							

						}else
							{
							 if(obj.value=="1")
								{				
									refundDtl.querySelectorAll("td")[0].innerHTML="<font color='red'>*</font>Cancelled By:";
									refundDtl.querySelectorAll("tr")[1].querySelectorAll("td")[0].innerHTML="<font color='red'>*</font>Cancellation Reason:";
									refundDtl.querySelectorAll("tr")[3].querySelectorAll("td")[0].innerHTML="Cancel Date:";
								}else
									{
									refundDtl.querySelectorAll("td")[0].innerHTML="<font color='red'>*</font>Refund By:";
									refundDtl.querySelectorAll("tr")[1].querySelectorAll("td")[0].innerHTML="<font color='red'>*</font>Refund Reason:";
									refundDtl.querySelectorAll("tr")[3].querySelectorAll("td")[0].innerHTML="Refund Date:";
									}
							}
						
						
						
						document.forms[0].accountCatDivCheked.value="2";
						document.getElementById("changeAccCat").style.display="none";
					
		
		
		}
	
	
			
}



function changeAccCat(obj)
{
	
	var catCode=document.getElementById("accCatCode").value;
	var accCanRef=document.getElementsByName("strAdvCancelRefund");
	
	for(var i=0;i<2;i++)
		{
			if(accCanRef[i].checked==true)
				{
				if(accCanRef[i].value=="1")
					{
						alert('no need for category change in advance cancellation since account will be closed');
						obj.checked=false;
					}}}
	
	if(obj.checked==true)
		{
		//alert(	document.getElementsByName("onlyCatCahnge")[0].value);
		//alert(document.getElementById("errMsg").innerHTML);
		if(obj.value=="1")
			{
			if(document.getElementById("errMsg").innerHTML!=""){
				document.getElementById("errMsg").innerHTML="";
				document.getElementsByName("onlyCatCahnge")[0].value="1";
				document.getElementsByName("strOfflineAmount")[0].value="0";
				
			//	var obj = document.getElementsByName("strOfflinePaymentMode")[0];
				
			//	obj.disabled = true;
			}
				
				var url = "CashCollectionOfflineTransBSCNT.cnt?hmode=accountCategory&catCode="+catCode;
				ajaxFunction(url,"32");
				document.getElementById("changeAccCat").style.display="";
				//var obj = document.getElementsByName("strOfflinePaymentMode");
				//obj.disabled = true;
				
			}else
				{
					document.getElementById("changeAccCat").style.display="none";
				}
		}
	
	
}