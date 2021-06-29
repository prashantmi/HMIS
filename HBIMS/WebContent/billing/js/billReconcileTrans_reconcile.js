



	/**
	 * setRateValue
	 * @param {String} index 
	 */
	 function setRateValue(index) {
	 	
	 		document.getElementById("strOfflineTariffRateUnit"+index).value = document.getElementById("strOfflineTempTariffRateUnit"+index).value; 
	 	
	 	calcOffLineTariffNetAmount(index);
	 	
	 }
 
	 /**
	  * getTariffByCode
	  * @param {Object} teriffCode 
	  * @param {event} e
	  */
	  function getTariffByCode(teriffCode , e) {
	  		  	
	  	if(e.keyCode == 13){
								 
			var hmode = "TARIFFCODEDTLS"; 
				 
			var selBillDtl = document.forms[0].strBillNoVal.value;
			var billArr = selBillDtl.split("^");
			 
				var url ="BillReconcileTransCNT.cnt?hmode="+hmode+"&groupId="+document.forms[0].strOffLineGroup[document.forms[0].strOffLineGroup.selectedIndex].value+"&hService=" + billArr[1] + "&treatmentCat=" + billArr[13] + "&ward=" + billArr[7]+"&tariffCode="+document.forms[0].strTariffCode.value;
		 
						
			ajaxFunction(url,"7");
								 
				 
				return false;
			}	   	
	  	
	  	return false;
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
function generateRows(){
		if(!showAlert())
			return false;
		addRows(new Array('strOfflineTariffName','strOfflineTariffDetailsHidden','strOfflineTempTariffRateUnit','strOfflineTariffRateUnit','strOfflineTariffQty','strOfflineTariffUnit','strOfflineTariffServiceTax', 'strOfflineTariffServiceTaxAmtVal','strOfflineTariffDiscount','strOfflineTariffDiscountAmtVal' ,'strOfflineTariffNetAmount','strOfflineTotalActualTariffAmtVal'),new Array('t','t','t','t','t','s','t','t','t','t','t','t'),'3','1','R');
	}


/**
When Go button is pressed
*/
function goFunc()                //  for CR No. field validation
{
		var hisValidator = new HISValidator("billReconcileTransBean");  
	 	hisValidator.addValidation("strCrNo", "req", "Cr No. is a Mandatory Field" );
	    //hisValidator.addValidation("strCrNo", "minlen=13", "Cr No. must be 13 Digits" );
	    hisValidator.addValidation("strCrNo", "minlen="+document.forms[0].strCrNo.maxLength,"Cr No. must be "+document.forms[0].strCrNo.maxLength+" Digits" );
        var retVal = hisValidator.validate(); 
	    //document.forms[0].strTempVal.value=document.forms[0].strCrNo.value;
	    if(retVal){
				document.forms[0].hmode.value="GO";
				document.forms[0].submit();
		}else{
			return false;
		}
	}
	



/**
	called from body onload
*/
function setCrNoReadOnlyLogic()
{
	if( document.forms[0].strCrNo.value!="")
 		document.forms[0].strCrNo.readOnly=true;
 	 	
 	document.forms[0].strErrMsg.value="";
}

/**
	This function is called when bill no is checked
*/
function groupCheck(chkObj){
	
     //reset bill service details/net amount as bill no is selected
     var recAmtObj = document.getElementsByName("strReconcileTariffAmount");
     
	 if(recAmtObj.length > 0) {
	 	recAmtObj[0].value = 0;
	 	(document.getElementById("totalRecTrfAmtDivId")).innerHTML = "0.0";
	 }
	 
     var recAmtObj = document.getElementById("strOfflineNetRecAmount");
	 if(recAmtObj != null) {
	 	recAmtObj.value = 0;
	 	(document.getElementById("netRecAmountDivId")).innerHTML = "0.0";
	 }

     hideDetails1(0);
     
     //billNoVal is hidden field having the selected bill no option value
     document.forms[0].strBillNoVal.value = chkObj.value;
     
      var mode="GETBILLTARIFFDTLS";
     
     var url="BillReconcileTransCNT.cnt?hmode="+mode+"&optionVal="+chkObj.value;
     ajaxFunction(url,"2");
     	
}

/**
called from body onload
*/
function onLoadLogics()
 {    
    if(document.forms[0].strCrNo.value != "")
     {
           document.getElementById("tldglbdiv").style.display="block";
           document.getElementById("patdtltld").style.display="block";
           document.getElementById("divBillDtl").style.display="block";
           document.getElementById("billDtl").style.display="block";
           
           var mode="GETBILLDTLSLIST";
	 	   var url="BillReconcileTransCNT.cnt?hmode="+mode+"&modName="+document.forms[0].strCrNo.value;
		   ajaxFunction(url,"1");
	  }
	 else
	  {
		document.getElementById("tldglbdiv").style.display="none";
	    document.getElementById("billDtl").style.display="none";
	  }
	 
	 //called so that reconciliation reason set at the time of page loading
	 setReconcilationReason();
}

/**
	This function is used to hide the new tariff details [in case of new services]
	mode = 1 called from image
		 = 0 called from groupCheck() function	
*/
function hideDetails1(mode)
{
	var msg = true;
	var htmlStr = document.getElementById("id3").innerHTML;
	
	if(htmlStr != '') 
	{
		if(mode == 1) msg = confirm("Unsaved data will be lost\nAre You Sure !!");
  	
	  	if(msg == true)
	  	{
	  		//reset the multirow (global function)
	  		resetMultiRow('3');
	  		document.getElementById("minusonLineId1").style.display="none";
	 		document.getElementById("plusonLineId1").style.display="block";
	 		document.getElementById("divNewTrfEntry").style.display="none";
	 		document.getElementById("id3").style.display="none";
	 		document.getElementById("id4").style.display="none";
	 		
	 		document.forms[0].strOfflineTotalRecAmount.value = 0;
	 		document.getElementById("totalRecAmtDivId").innerHTML = "0.0";
	 		
	 			 		
	 		//call the function to calculate net reconcile amount
	 		calcNetReconcileAmt();
	 	}
 	}
 	
 	return msg;	
}

/**
	This function is used to show the new tariff details [in case of new services] and add a row
*/
function showDetails1()
{
	document.getElementById("plusonLineId1").style.display="none";
 	document.getElementById("minusonLineId1").style.display="block";
 	document.getElementById("divNewTrfEntry").style.display="block";
 	document.getElementById("id3").style.display="block";
 	document.getElementById("id4").style.display="block";
 	
 	//call the following function to add a row
 	addRows(new Array('strOfflineTariffName','strOfflineTariffDetailsHidden','strOfflineTempTariffRateUnit','strOfflineTariffRateUnit','strOfflineTariffQty','strOfflineTariffUnit','strOfflineTariffServiceTax', 'strOfflineTariffServiceTaxAmtVal','strOfflineTariffDiscount','strOfflineTariffDiscountAmtVal' ,'strOfflineTariffNetAmount','strOfflineTotalActualTariffAmtVal'),new Array('t','t','t','t','t','s','t','t','t','t','t','t'),'3','1','R');
}


function hideDetails()
{
	document.getElementById("minusonLineId").style.display="none";
 	document.getElementById("plusonLineId").style.display="block";
 	document.getElementById("patdtltld").style.display="none";
}

function showDetails()
{
	document.getElementById("plusonLineId").style.display="none";
 	document.getElementById("minusonLineId").style.display="block";
 	document.getElementById("patdtltld").style.display="block";
}

function hideDetails2()
{
	document.getElementById("minusonLineId2").style.display="none";
 	document.getElementById("plusonLineId2").style.display="block";
 	document.getElementById("billDtl").style.display="none";
}

function showDetails2()
{
	document.getElementById("plusonLineId2").style.display="none";
 	document.getElementById("minusonLineId2").style.display="block";
 	document.getElementById("billDtl").style.display="block";
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


/*
	This function is called when reconciliation reason is selected
*/
function setReconcilationReason()
{
	if(document.forms[0].strReconcilationReason.selectedIndex >= 0) {
		var val = document.forms[0].strReconcilationReason[document.forms[0].strReconcilationReason.selectedIndex].value;
		var content = document.forms[0].strReconcilationReason[document.forms[0].strReconcilationReason.selectedIndex].text;
		
		if(val != 0)
		{
			document.forms[0].strReconcilationReasonText.disabled = true;
		  	document.forms[0].strReconcilationReasonText.value = content;
		  	
		}
		else
		{
		  	document.forms[0].strReconcilationReasonText.disabled = false;
		  	document.forms[0].strReconcilationReasonText.value = "";
		}
	}
}

/**
to get ajax response

Note >> do not use mode > 10 b'coz it will be used in IPD Bill (Final Settlement)
*/
function getAjaxResponse(res,mode)
{
	if(mode == '0'){
		var objEle = document.getElementById("menu1");
		objEle.innerHTML = res;
		objEle.style.display="none";
		display_popup_menu(pWindow,"menu1","","");
	}
	
	if(mode=="1")	//called from body onload to get bill details
 	{
 		var objVal = document.getElementById("billDtl");
 		
 		
 		var resValues = res.split("####");
 		
 		if(resValues[0] == "ERROR"){
 			
 			document.getElementById("errMsg").innerHTML = resValues[1];
 			
 		}else{
 			
 			 objVal.innerHTML = resValues[0]; 
 		}
 		
 		
	   
    }
	
	
	if(mode == '2')	//for bill service details
   	{
   		
   		var resValues = res.split("####");
 		
 		if(resValues[0] == "ERROR"){
 			
 			document.getElementById("errMsg").innerHTML = resValues[1];
 			
 		}else{
 			
 		var temp = res.split('@');
   		var objEle = document.getElementById("trfDtl");
		objEle.innerHTML = temp[0];
		document.getElementById("offlineTariffDivId").style.display="block";
	 	document.getElementById("disBnR").style.display="block";
	 	document.getElementById("groupDivId").innerHTML = "<b>Group</b> <select name='strOffLineGroup' class='comboNormal' onchange='getTariffDtls();'>"+temp[1]+"</select>";
	 	document.getElementById("dropdown1").innerHTML = temp[2];
	   
	 	getTariffDtls();
 		}
   		
	}
		
	if(mode == '4')	//New Tariff Data as group is changed
	{
		var dropObj = document.getElementById("dropdown1");
		dropObj.innerHTML = res;
	}
	
	if(mode == '5')
	{
		var temp = res.split('@');
		document.getElementById("offlineTariffUnitDivId"+temp[0]).innerHTML = "<select name='strOfflineTariffUnit' id='strOfflineTariffUnit"+temp[0]+"' class='comboMin' onChange='calcOffLineTariffNetAmount(\""+temp[0]+"\");'>"+temp[1]+"</select>";				
		calcOffLineTariffNetAmount(temp[0]);
	}
		
	
		if(mode == '7'){
			
			
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
		      		
		      		
		      		
		      		var indexVal = '3-'+document.multirow.rowIndex3.value;
		      		
		       		setSelectedTariff(indexVal ,tempVal[1], tempVal[2] );
		      			
		        
		      			document.forms[0].strTariffCode.value = '';
		      			document.forms[0].strTariffCode.focus();
		      		
		      	}	      
		       	
		       }
		
		}	
		
		
		
		
	if(mode == '9')
	{
		
		var objEle = document.getElementById("menu1");
		var err = document.getElementById("errMsg");
    	var temp = res.split("###");
    
        if(temp[0] == "ERROR"){
       		  	err.innerHTML = temp[1];
       		  		
      	}else{
      			objEle.innerHTML = res;
      		 	objEle.style.display="none";
				display_popup_menu(pWindow,"menu1","","");
         }
    }
	
	if(mode == '10')	//for new tariff unit data as tariff name is selected from drop down
	{
		var temp = res.split('@');
		document.getElementById("offlineTariffUnitDivId"+temp[0]).innerHTML = "<select name='strOfflineTariffUnit' id='strOfflineTariffUnit"+temp[0]+"' class='comboMin' onChange='calcOffLineTariffNetAmount(\""+temp[0]+"\");'>"+temp[1]+"</select>";				
	    calcOffLineTariffNetAmount(temp[0]);
	}
			
	if(parseInt(mode) > 10)	{
		
							//for group details in IPD Bill (Consolidated details)
		var nmod = mode-11;
		var objEle = document.getElementById("pt"+nmod);
		  //var objEle1 = document.getElementById("p"+nmod);
						    
		  objEle.style.display = "block";
		  
		  var temp = res.split("@@");
		  		 	    
		  document.getElementById("strGroupListLen"+nmod).value = temp[1];
		  
		   var divOpenObj = document.getElementById("divOpenFlag" + nmod);
		   if(divOpenObj != null) divOpenObj.value = 1;
					objEle.innerHTML = temp[0];
					
					
			}
			
 }
 
  /**
	  * This function is used when checkbox (bill service detail) is checked/unchecked
	  * @param {Object} obj - checkbox object 
	  * @param {String} index 
	  * @param {String} unitVal
	  */
	  function initReconcileTariff(obj,index , balQty) {
	  		  	
	  	if(obj.checked){
	  		
	  		var temp = balQty.split(' ');
	  		
	  		document.getElementById("strBillTariffRefund"+index).value = temp[0];
	  		document.getElementById("strBillTariffRefund"+index).disabled = false;
	  		document.getElementById("strBillTariffUnit"+index).disabled = false;
	  		
	  		//to calculate total amount
	  		calcBillTariffRefundAmt(index , obj.value);
	  		
	  	}else{
	  		document.getElementById("strBillTariffRefund"+index).value = 0;
	  		document.getElementById("strBillTariffRefund"+index).disabled = true;
	  		document.getElementById("strBillTariffUnit"+index).disabled = true;
	  		
	  		document.getElementById("strBillTariffRefundAmount"+index).value = 0;
	  		document.getElementById("billTrfAmtDivId"+index).innerHTML = '0.0';
	  		document.getElementById("strBillTariffUnit"+index).selectedIndex = 0;
	  		
	  		//to calculate total amount
	  		calcRefundTotalAmount();
	  	}
	  
	  
	  }
 
 	/**
	 * This function is used to calculate the cost for the selected tariff [Tariff Detail section]
	 and it is called when check box is checked, reconcile qty is changed or unit is changed
	 
	 * @param {String} index
	 * @param {String} val		 
	 */
	 function calcBillTariffRefundAmt(index ,val) {
	 	
	 	var refundUnitBaseValTemp = 0;
	 	
	 	var temp = val.split('^');
	 	var reminedQty = temp[1];	//converted balance qty
	 	
	 	var refundQty = document.getElementById("strBillTariffRefund"+index).value;
	 	var refundUnitBaseVal = document.getElementById("strBillTariffUnit"+index)[document.getElementById("strBillTariffUnit"+index).selectedIndex].value;
	 	
	 	//if unit exists
	 	if(refundUnitBaseVal != 0)
	 		refundUnitBaseValTemp = refundUnitBaseVal.split('^')[1]; //unitId^base_value
	 	
	 	//convert the reconcile qty into base value
	 	var convRefundQty = refundQty * refundUnitBaseValTemp;
	 		
	 	//entered reconcile qty can not be greater than balance qty
	 	if(convRefundQty > reminedQty){
	 		alert("Reconcile Quantity Cannot be More than Balance Quantity\nSelect valid quantity !!");
	 		document.getElementById("strBillTariffRefund"+index).value = 0;
	 		document.getElementById("strBillTariffRefundAmount"+index).value = 0;
	 		document.getElementById("billTrfAmtDivId"+index).innerHTML = '0.0';
	 		document.getElementById("strBillTariffRefund"+index).focus();
	 	}else{
	 		//got the value
		 	var rate = temp[3];
		 	var rateUnitBaseVal = temp[13];
		 	var discountUnit = temp[8];
		 	var discountType = temp[9];
		 	var serTax = temp[7];
		 		
		 	if(rate == '') rate = 0;
		 	if(rateUnitBaseVal == '') rateUnitBaseVal = 0;
		 	if(discountUnit == '') discountUnit = 0;
		 	if(discountType == '') discountType = 1;
		 	if(serTax == '') serTax = 0;
		 	
	 		//calculate tariff reconcile cost
	 		var calAmt = calTrfNetAmount(rate,rateUnitBaseVal,discountUnit,discountType,refundQty,refundUnitBaseValTemp,serTax,0,1);
	 		
	 		 
	 
	 		
	 		document.getElementById("strBillTariffRefundDiscountAmount"+index).value = calAmt.oDisAmt;
	 		document.getElementById("strBillTariffRefundServiceTaxAmount"+index).value = calAmt.oSerAmt;
	 		
	 		document.getElementById("strBillTariffRefundAmount"+index).value = calAmt.oNetTrfAmt;
	 		document.getElementById("billTrfAmtDivId"+index).innerHTML = calAmt.oNetTrfAmt;
	 	}

	 	//calculate total amount	
	 	calcRefundTotalAmount();
	 }
	 
	 
	 /**
	 * This function is used to calculate the total amount for reconcile tariff
	 */
	 function calcRefundTotalAmount() {
	 		 	
	 	var grandTotal = calAllTariffNetCost("strBillTariffRefundAmount");
	 	document.getElementById("totalRecTrfAmtDivId").innerHTML = grandTotal;
	 	document.forms[0].strReconcileTariffAmount.value = grandTotal;
	 	
	 	//to calculate net reconcile amount
	 	calcNetReconcileAmt();
	 }

	/**
	 * This function is used to calculate the Net reconcile amount
	 */
	 function calcNetReconcileAmt() {
	 	
	 	var recoTariffAmt = 0;
	 	
	 	if(document.getElementsByName("strReconcileTariffAmount").length > 0){
	 		
	 		recoTariffAmt = document.getElementsByName("strReconcileTariffAmount")[0].value;
	 		
	 	}
	 	
	 	
	 	var netAmt = manipulateValue(recoTariffAmt,
	 				document.forms[0].strOfflineTotalRecAmount.value,0);
	 	
	 	netAmt = roundValue(netAmt,2);
	 	
	 	document.getElementById("netRecAmountDivId").innerHTML = netAmt;
	 	document.forms[0].strOfflineNetRecAmount.value = netAmt;
	 }
	
    function showTariffSearchPopup(e,compName,index){
	 var groupId = "";
	 var wardCode = "";
	 var groupId = document.forms[0].strOffLineGroup.value;
		//tariffSearchPopUp(e,compName,hospital Service,treatment Category,wardCode,groupId,'setSelectedTariff',index);
		tariffSearchPopUp(e,compName,"1","11",wardCode,groupId,'setSelectedTariff',index);
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
	 		var discountVal = document.getElementById("strOfflineTariffDiscount"+index).value;
	 		var serviceVal = document.getElementById("strOfflineTariffServiceTax"+index).value;
	 		var hiddenVal = document.getElementById("strOfflineTariffDiscountConfigDetails"+index).value;
	 		var discountType = 0;
	 		
	 		var netAmt = 0;
	 		
	 		if(hiddenVal != ''){
	 			
	 			var tempArr = hiddenVal.split(',');
	 			
	 			discountVal = tempArr[0];
	 			discountType = tempArr[1];
	 		}
	 		
	 		
	 		var calAmt = calTrfNetAmountWithActTrfAmt(rate, actRate ,rateBaseValue,discountVal,discountType,qtyVal,qtyBaseValue,serviceVal,0,0); 
	 		
	 		
	 	/*	
	 		var qty = 0;
	 		var discountPercentageFlag = false;
	 	
	 		var rate = parseFloat(temp[10]);
	 		var baseValue = parseFloat(temp[6]);
	 		
	 		var discountUnit = 0;
	 		var serviceTaxAmt = 0;
	 		var discountAmt = 0;
	 		var total = 0;
	 		var balanceAmt = 0;
	 		var netAmt = 0;
	 	
	 	
	 		if(!isNaN(temp1[1])){
	 			qtyBaseValue = parseFloat(temp1[1]);
	 		}else{
	 			qtyBaseValue = 0;
	 		}
	 	
	 		if(!isNaN(qtyVal) && qtyVal != ''){
	 		
	 			qty = parseInt(qtyVal);
	 		}else{
	 		
	 			qty = 0;
	 		}
	 	
	 		var check = discountVal.indexOf('%');
	 	
	 		if(check!= -1){
	 			discountVal = discountVal.substring(0,check);
	 			discountPercentageFlag = true;
	 		}
	 	
	 		if(!isNaN(discountVal)){
	 			discountUnit = parseFloat(discountVal);
	 		}
	 	
	 		
	 		if(!isNaN(serviceVal)){
	 			serviceTaxAmt = parseFloat(serviceVal);
	 		}
	 		 	
	 		total = (rate / baseValue)*(qty * qtyBaseValue);
			 	
				 	
			if(discountUnit > 0){
			
				if(discountPercentageFlag){
					discountAmt = total * discountUnit / 100
				}else{
					discountAmt = qty * qtyBaseValue * discountUnit ;	
				}
				
			}	 	

			 			 	
			balanceAmt = manipulateValue(total,discountAmt,1);
		
			if(serviceTaxAmt > 0){
			
				serviceTaxAmt = balanceAmt * (serviceTaxAmt / 100);
			} 			 	
			 		 		 		 				 		 		 		 	
			netAmt = manipulateValue(balanceAmt , serviceTaxAmt , 0);	 		 		 		 	
	 		 */		 		 			 		 		 	
	 	
	 	netAmt = calAmt.oNetTrfAmt;
	 	
	 	
	 		if(netAmt < 0){
	 			
	 			alert("Net Amount Value in Negative, Plese Check the Discount Amount");
	 			return false;
	 		}	 		 		 	
			 		 		 		 	
			document.getElementById("strOfflineTariffServiceTaxAmtVal"+index).value = calAmt.oSerAmt;
			document.getElementById("strOfflineTariffDiscountAmtVal"+index).value = calAmt.oDisAmt;
	 		 document.getElementById("strOfflineTotalActualTariffAmtVal"+index).value = calAmt.oActTrfAmt;
	 		 
	 		document.getElementById("strOfflineTariffNetAmount"+index).value = netAmt;
	 		document.getElementById("strOfflineTariffNetAmountDivId"+index).innerHTML = netAmt;
	 	
	 		calcTotalRecAmount();	
		}	 	
	 }
	
	
	/**
	 * This function is used to calcultae the tariff total amount and net reconcile amount
	 */
	 function calcTotalRecAmount() {
	 	
	 	var total = 0;
	 	var amt = 0;
	 	
	 	var grandTotal = calAllTariffNetCost("strOfflineTariffNetAmount");
	 	document.getElementById("totalRecAmtDivId").innerHTML = grandTotal;
	 	document.forms[0].strOfflineTotalRecAmount.value = grandTotal;
	 	
	 	//to calculate net reconcile amount
	 	calcNetReconcileAmt();
	}
	 
    /*
	 * This function is called to delete the multi row [new tariff]
	 * @param {Object} index 
	 */
	function removeTariffRow(index) {
		if(!showAlert())
			return false;
		
	 		deleteRow(index,'3','1');
	 		calcTotalRecAmount();
	}

   function getOffLineTariffDiscountDetails(divId,parent){
			
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

	function hideOffLineTariffDiscountDetails(divId){
		
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
	 	
	 			 		 
	 	
	 	var hisValidator = new HISValidator("billReconcileTransBean");  
		
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
				document.getElementById("strOfflineTariffDiscountConfigDetails"+gblTariffDiscountDtlsId).value = discountUnit+","+discountType+","+discountBy+","+discountReason+","+discountText+","+discountDate;
				
				var displayResult = "";

				if(discountType == 2){
					 displayResult = discountUnit+" %";										
				}else{
					displayResult = discountUnit;
				}
									
				document.getElementById("strOfflineTariffDiscount"+gblTariffDiscountDtlsId).value = displayResult;					
				document.getElementById("strOfflineTariffDiscountDivId"+gblTariffDiscountDtlsId).innerHTML = displayResult;
												
				hideOffLineTariffDiscountDetails('tariffDiscountDtls');
				
				calcOffLineTariffNetAmount(gblTariffDiscountDtlsId);
				
		}else{
		 
		return false;
		}
	 		
	 }
	
	
	 /**
	  * This function is called when discount reason from pop up window is selected
	  */
	  function setReasonText() {
	  	
	  	var val = document.forms[0].strOffLineTariffDiscountReason[document.forms[0].strOffLineTariffDiscountReason.selectedIndex].value;
	  	var content = document.forms[0].strOffLineTariffDiscountReason[document.forms[0].strOffLineTariffDiscountReason.selectedIndex].text;
	  	
	  	if(val != 0){
	  	document.forms[0].strOffLineTariffDiscountReasonText.disabled = true;
	  	document.forms[0].strOffLineTariffDiscountReasonText.value = content;
	  	
	  	}else{
	  		document.forms[0].strOffLineTariffDiscountReasonText.value = "";
	  		document.forms[0].strOffLineTariffDiscountReasonText.disabled = false;
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
			addRows(new Array('strOfflineTariffName','strOfflineTariffDetailsHidden','strOfflineTempTariffRateUnit','strOfflineTariffRateUnit','strOfflineTariffQty','strOfflineTariffUnit','strOfflineTariffServiceTax', 'strOfflineTariffServiceTaxAmtVal','strOfflineTariffDiscount','strOfflineTariffDiscountAmtVal' ,'strOfflineTariffNetAmount','strOfflineTotalActualTariffAmtVal'),new Array('t','t','t','t','t','s','t','t','t','t','t','t'),'3',obj.value,'R');
				hideMultiRowAdder(divId);
				return false;
			}	   	
			return true;
	   }
 
	
	/**
		This function is used to get tariff data when group is changed. This data will reflect on drop down
		tariff functionality
	*/ 
	function getTariffDtls(){
			var hmode = "TARIFFDTLS"; 
			var url = "";	
			// please enter the required values (currently the values have been hardcoded)
			// please pass Hospital Service, TreatmentCategory and Ward Code
			var selBillDtl = document.forms[0].strBillNoVal.value;
			var billArr = selBillDtl.split("^");
			
			url = "BillReconcileTransCNT.cnt?hmode="+hmode+"&groupId="+document.forms[0].strOffLineGroup[document.forms[0].strOffLineGroup.selectedIndex].value+"&hService=" + billArr[1] + "&treatmentCat=" + billArr[13] + "&ward=" + billArr[7];
			//alert(url);
			ajaxFunction(url,"4");
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
			var url = "BillReconcileTransCNT.cnt?hmode="+hmode+"&unitId="+unitId+"&baseValue="+baseValue+"&rowIndex="+index;
			ajaxFunction(url,"10");
		 	}else{
		 		document.getElementById("offlineTariffUnitDivId"+index).innerHTML = "<select name='strOfflineTariffUnit' id='strOfflineTariffUnit"+index+"' class='comboMin' onChange='calcOffLineTariffNetAmount(\""+index+"\");'><option value=0>Select Value</option></select>";				
		 		calcOffLineTariffNetAmount(index);
		 	}
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
			
			document.getElementById("strOfflineTariffDiscount"+userValue).value = 0;
			document.getElementById("strOfflineTariffDiscountDivId"+userValue).innerHTML = 0 ;
			
			document.getElementById("strOfflineTariffServiceTax"+userValue).value = temp[13];
			document.getElementById("strOfflineTariffServiceTaxDivId"+userValue).innerHTML = temp[13];
			
			document.getElementById("strOfflineTariffQty"+userValue).value = 1;
			
			document.getElementById("strOfflineTariffNetAmount"+userValue).value = 0;
			document.getElementById("strOfflineTariffNetAmountDivId"+userValue).innerHTML = "0.00";
		
			document.getElementById("strOfflineTariffDetailsHidden"+userValue).value = resultValue;
			document.getElementById("strOfflineTariffDiscountConfigDetails"+userValue).value = '';
			
			getOffLineTariffUnit(temp[5],temp[6],userValue);
			
			
			
				if(document.getElementById("strOfflineTariffName"+userValue) != null && document.getElementById("strOfflineTariffName"+userValue).value.length > 0){
	 			 	
		generateRows();
		 	 
		document.getElementById("strOfflineTariffName3-"+ document.multirow.rowIndex3.value ).focus();
		 	 
	 	}
			
	//	}
				
	
	}
	
	/**
		This function is used to set the tariff value when user clicks on tariff name from 
		tariff drop down utility
	*/
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
			
			document.getElementById("strOfflineTariffDiscount"+userValue).value = 0;
			document.getElementById("strOfflineTariffDiscountDivId"+userValue).innerHTML = 0 ;
			
			document.getElementById("strOfflineTariffServiceTax"+userValue).value = temp[13];
			document.getElementById("strOfflineTariffServiceTaxDivId"+userValue).innerHTML = temp[13];
			
			document.getElementById("strOfflineTariffQty"+userValue).value = 1;
			
			document.getElementById("strOfflineTariffNetAmount"+userValue).value = 0;
			document.getElementById("strOfflineTariffNetAmountDivId"+userValue).innerHTML = "0.00";
		
			document.getElementById("strOfflineTariffDetailsHidden"+userValue).value = resultValue;
			document.getElementById("strOfflineTariffDiscountConfigDetails"+userValue).value = '';
			
			getOffLineTariffUnit(temp[5],temp[6],userValue);
			
				if(document.getElementById("strOfflineTariffName"+userValue) != null && document.getElementById("strOfflineTariffName"+userValue).value.length > 0){
	 			 	
		generateRows();
		 	 
		document.getElementById("strOfflineTariffName3-"+ document.multirow.rowIndex3.value ).focus();
		 	 
	 	}
			
		}
				
	
	}
	
	function setSelectedCrNo(crNo)
	{
	
	 document.forms[0].strCrNo.value=crNo;
	 return goFunc();
	}
	
	function initPage(){
	 	document.forms[0].hmode.value="INITIALPAGE";
		document.forms[0].submit();
	 }
	 
	 //used in ipd bill (final settlement)
	
	
	
	/**
	 This function is called from view group details of Final Settlement (consolidated details) section
	 */
	function callMe(index){

		
    	var logic = 11;
	 	var mode="FINALSETTLEMENTDTLS";
	 	
	 	var flagValue = document.forms[0].flag.value;
	 	var divOpenObj = document.getElementById("divOpenFlag" + index);
	 	
	 	if(parseInt(flagValue) == 0 || parseInt(flagValue) == parseInt(index))
	 	{
		 	//if one time ajax response is got then next time, ajax function will not be called
		 	if(divOpenObj == null || parseInt(divOpenObj.value) == 0) {
		 		
		 		var billNo  = document.forms[0].strBillNoVal.value;
		 		var groupIdObj = document.forms[0].strGroupId[(parseInt(index) - 1)].value;
		 	//	var groupIdObj = document.getElementById("strGroupId" + index);
		   		logic = parseInt(logic) + parseInt(index);
		
		  		var url="BillReconcileTransCNT.cnt?hmode="+mode+"&billNo="+billNo + "&groupId=" + groupIdObj + "&index=" + index;
		  	
		  		ajaxFunction(url,logic);
		 	}
		 	else {
		 		var trfConObj = document.getElementById("pt" + index);
		 		if(trfConObj != null) trfConObj.style.display="block";
		 	}
		 	
		 	//set the flag value having current open div value
		 	document.forms[0].flag.value = index;
		 }
		 else
		 {
		 	alert("Unsaved Tariff Data exists,\nEither add or cancel it and then continue !!");
		 }	
	}
	 
	/**
		This function is used when checkbox (bill service detail) is checked/unchecked
	*/	
	function ipdTrfChk(obj,index,balQty)
	{
		if(obj.checked){
			
			var temp = balQty.split(' ');
	  		
	  		document.getElementById("strBillTariffRefund"+index).value = temp[0];
	  		document.getElementById("strBillTariffRefund"+index).disabled = false;
	  		document.getElementById("strBillTariffUnit"+index).disabled = false;
	  		
	  		// group id is appended in final settlement hidden value 	  		
	  		obj.value = obj.value+"^"+document.forms[0].strGroupId[(parseInt(index) - 1)].value;
	  		  		
	  		//to calculate total amount for single tariff
	  		ipdTrfQty(index , obj.value);
	  		
	  		
	  		
	  	}else{
	  		document.getElementById("strBillTariffRefund"+index).value = 0;
	  		document.getElementById("strBillTariffRefund"+index).disabled = true;
	  		document.getElementById("strBillTariffUnit"+index).disabled = true;
	  		
	  		document.getElementById("reconcileCost"+index).value = 0;
	  		document.getElementById("reconcileCostDivId"+index).innerHTML = '0.0';
	  		document.getElementById("strBillTariffUnit"+index).selectedIndex = 0;
	  		  		
	  		  	// group id is removed form final settlement hidden value 	
	  		obj.value = obj.value.substring(0 , obj.value.length - 4);
	  		
	  	}
	 }
 
 	 /**
	 	This function is used to calculate the cost for the selected tariff [Tariff Detail section in IPD Final bill]
	 	and it is called when check box is checked, reconcile qty is changed or unit is changed
	 */
 	function ipdTrfQty(index ,val)
 	{
   		var refundUnitBaseValTemp = 0;
	 	
	 	var temp = val.split('^');
	 	var reminedQty = temp[18];	//converted balance qty
	 	
	 	var refundQty = document.getElementById("strBillTariffRefund"+index).value;
	 	var refundUnitBaseVal = document.getElementById("strBillTariffUnit"+index)[document.getElementById("strBillTariffUnit"+index).selectedIndex].value;
	 	
	 	//if unit exists
	 	if(refundUnitBaseVal != 0)
	 		refundUnitBaseValTemp = refundUnitBaseVal.split('^')[1]; //unitId^base_value
	 	
	 	//convert the reconcile qty into base value
	 	var convRefundQty = refundQty * refundUnitBaseValTemp;
	 		
	 	//entered reconcile qty can not be greater than balance qty
	 	if(convRefundQty > reminedQty){
	 		alert("Reconcile Quantity Cannot be More than Balance Quantity\nSelect valid quantity !!");
	 		document.getElementById("strBillTariffRefund"+index).value = 0;
	 		document.getElementById("reconcileCost"+index).value = 0;
	 		document.getElementById("reconcileCostDivId"+index).innerHTML = '0.0';
	 		document.getElementById("strBillTariffRefund"+index).focus();
	 	}else{
	 		//got the value
		 	var rate = temp[12];
		 	var rateUnitBaseVal = temp[19];
		 	var discountUnit = temp[7];
		 	var discountType = temp[8];
		 	var serTax = temp[20];
		 		
		 	if(rate == '') rate = 0;
		 	if(rateUnitBaseVal == '') rateUnitBaseVal = 0;
		 	if(discountUnit == '') discountUnit = 0;
		 	if(discountType == '') discountType = 1;
		 	if(serTax == '') serTax = 0;
		 	
	 		//calculate tariff reconcile cost
	 		var calAmt = calTrfNetAmount(rate,rateUnitBaseVal,discountUnit,discountType,refundQty,refundUnitBaseValTemp,serTax,0,1);
	 		
	 		document.getElementById("reconcileCost"+index).value = calAmt.oNetTrfAmt;
	 		document.getElementById("reconcileCostDivId"+index).innerHTML = calAmt.oNetTrfAmt;
	 	}
 	}
 	
 	/**
 		This function is called when Ok button in tariff details (Final Adjustment) is clicked
 	*/
 	function ipdTrfSelOk(divindex)
 	{
 		
 		var fSettleListLen =  parseInt(document.getElementById("strGroupListLen"+divindex).value);
 			
 		if(fSettleListLen > 0){
 			
 			var flag = 0;
 			var grandTotal = 0;
 			
 			for(var index=0; index<fSettleListLen; index++) {
 			 				
 				var chkObj = document.getElementById("strBillTariffVal"+divindex+"-"+ (index + 1));
 				var qtyObj = document.getElementById("strBillTariffRefund"+divindex+"-"+(index + 1));
 				var unitObj = document.getElementById("strBillTariffUnit"+divindex+"-"+(index + 1));
 				var costObj = document.getElementsByName("reconcileCost"+divindex)[0];
 				
 				if(chkObj != null){
 					
 					if(chkObj.checked){
 						
 						if(qtyObj.value == null || qtyObj.value == 0 || qtyObj.value == ''){
 							alert("Entered Reconcile Qty is In-Valid\nEnter Valid Reconcile Qty");
 							qtyObj.focus();
 							return;
 						}
 					
 						if(unitObj.value == null || unitObj.value == 0 || unitObj.value == ''){
 							alert("Entered Unit is In-Valid\nEnter Valid Unit");
 							unitObj.focus();
 							return;
 						}
 						
 						flag = 1;		
 						
 					}
 					
 				}
 				
 				
 			}
 			
 			
 			//No check box is checked
 		if(flag == 0) 
 		{
 			alert("Please Select at least one check box & then click on Add Button !!");
 		}
 		else 
 		{
 		 			
 			//call the function that will calculate reconcile cost for a group
 			var grandTotal = calAllTariffNetCost("reconcileCost" + divindex);
 			document.getElementById("strBillTariffRefundAmount"+divindex).value = grandTotal;
	 		document.getElementById("billTrfAmtDivId"+divindex).innerHTML = grandTotal;
	 				
 			//call the function that will calculate the total/net reconcile cost
 			calcRefundTotalAmount();
 			
 			//hide the current div
 			var trfConObj = document.getElementById("pt" + divindex);
	 		if(trfConObj != null) trfConObj.style.display="none";
 			document.forms[0].flag.value = 0;
 			
 		
 		}
 			
 			
 		}else {
 					alert("No Record Available. Please Cancel The Process");
 				}	
 			
 			
 	}
 	
 	/**
 		This function is called when Cancel button in tariff details (Final Adjustment) is clicked
 	*/
 	function ipdTrfSelCan(index)
 	{
 		
 		var fSettleListLen =  parseInt(document.getElementById("strGroupListLen"+index).value);
 			
 		if(fSettleListLen > 0){
 		
 		
 		var msg = confirm("Unsaved data will be lost\nAre You Sure !!");
  	  	if(msg == true) {
  	  		
  	  		
  	  			for(var i=0; i<fSettleListLen; i++) {
  	  		
  	  				var chkObj = document.getElementById("strBillTariffVal"+index+"-"+ (i+ 1));
 					var qtyObj = document.getElementById("strBillTariffRefund"+index+"-"+(i + 1));
 					var unitObj = document.getElementById("strBillTariffUnit"+index+"-"+(i + 1));
 					var recCostObj = document.getElementsByName("reconcileCost"+index)[0];
 					
 					var recCostDivObj = document.getElementById("reconcileCostDivId" + index + "-" + (i+1));	
 					 			
 					if(chkObj != null) {
 			
 						if(chkObj.checked) {
 							chkObj.checked = false;
 							qtyObj.value = 0;
 							qtyObj.disabled = true;
 							unitObj.disabled = true;
 							unitObj.selectedIndex = 0;
 							recCostObj.value = 0;
 						}
 				
 					}
  	  			}
  	  			
  	  			
 				recCostDivObj.innerHTML = '0.0';
  	  			
 			document.getElementById("strBillTariffRefundAmount"+index).value = 0;
	 		document.getElementById("billTrfAmtDivId"+index).innerHTML = '0.0';
	 				
 			//call the function that will calculate the total/net reconcile cost
 			calcRefundTotalAmount();
 			
 			//hide the current div
 			var trfConObj = document.getElementById("pt" + index);
	 		if(trfConObj != null) trfConObj.style.display="none";
 			document.forms[0].flag.value = 0;
 		}
 	}else{
 		var trfConObj = document.getElementById("pt" + index);
	 		if(trfConObj != null) trfConObj.style.display="none";
 			document.forms[0].flag.value = 0;
 		
 	}
 	
 	}
 	
 	
 	/**
 	 * saveData
 	  
 	 */
 	 function saveData() {
 	 	
 	  	 	
 	 	var billDtls = document.getElementsByName("strBillDetailsValue");
 	 	
 	 	if(billDtls.length < 1){
 	 		
 	 		alert("No Reconcile Bill(s) Details Available");
 	 		return false;
 	 	}
 	 	
 	 	
 	 	
	 	 var indexVal = document.multirow.rowIndex3.value;
		 		 
		 		 var offTrfDtl = document.getElementById("strOfflineTariffName3-"+indexVal);
		 		 		 		 
	 		 		 if(offTrfDtl != null && offTrfDtl.value.length <= 0){
	 		 		  		 		 	 		 		 
	 		 		 		deleteLastRow('3','1');
	 		 		 }
 	 	
 	 	
 	 	var billNoVal = document.forms[0].strBillNoVal.value;
 	 	var billArray = billNoVal.split('^');
 	 	
 	 	var billServiceIdVal = parseInt(billArray[3]);
 	 	
 	 		
		var newTariffName = document.getElementsByName("strOfflineTariffName");
 	 	 	 	
 	 	var newTariffValueObj = document.getElementsByName("strOfflineTariffDetailsHidden");
 	 	var newTariffValueLen = newTariffValueObj.length;
 	 	
 	 	var selectedTariffValueObj = document.getElementsByName("strBillTariffVal");
 	 	var selectedTariffValueLen = selectedTariffValueObj.length;
 	 	var selectedTariffArray = new Array();
 	 	
 	 	
 	 	
 	 	var count = parseInt("0");
 	 	
 	 	
 	 	for(var i=0; i<selectedTariffValueLen; i++) {
 	 		
 	 			if(selectedTariffValueObj[i].checked){
 	 				
 	 				selectedTariffArray[count] = selectedTariffValueObj[i].value;
 	 			 	 				
 	 				count = count + 1 ;
 	 				
 	 			}
 	 	}
 	 	
 	 	
 	 	if(count > 0 && newTariffValueLen > 1){
 	 		
 	 	if(billServiceIdVal != 21 ){
 	 		 	 
 	 		 for(var i=0; i<count; i++) {
 	 		 	
 	 		 		var tariffId = selectedTariffArray[i].split('^')[0];
 	 		 		 		 	
 	 		 	for(var j=0; j<(newTariffValueLen - 1); j++) {
 	 		 		 	 		 		
 	 		 		var newTariffId = newTariffValueObj[j].value.split('^')[0];
 	 		 		
 	 		 		if(tariffId == newTariffId){
 	 		 			
 	 		 			alert("Tariff Already Exists");
						newTariffName[j].focus();
						
						return false;
						 	 		 				
 	 		 		} // end if		 		
 	 		 		
 	 		 	} // end inner for
 	 		 	
 	 		 } // end for	 
 	 		 	 		  		
 	 		
 	 	}else{
 	 		
 	 		for(var i=0; i<count; i++) {
 	 		 	
 	 		 		var tariffId = selectedTariffArray[i].split('^')[3];
 	 		 	
 	 		 	for(var j=0; j< (newTariffValueLen - 1); j++) {
 	 		 		 		 		
 	 		 		var newTariffId = newTariffValueObj[j].value.split('^')[0];
 	 		 		
 	 		 		if(tariffId == newTariffId){
 	 		 		
 	 		 			alert("Tariff Already Exists");
						newTariffName[j].focus();
						
						return false;
						 	 		 				
 	 		 		} // end if		 		
 	 		 		
 	 		 	} // end inner for
 	 		 	
 	 		 } // end for	
 	 		
 	 	} // end else
 	 	
 	 		
 	 	}
 	 	
 	 	
 	 				var hisValidator = new HISValidator("billReconcileTransBean");  
 				
 				if(count > 0 && newTariffValueLen > 1){
	 				hisValidator.addValidation("strOfflineTariffName", "req", "Tariff Name is Mandatory Field" );
	 				hisValidator.addValidation("strOfflineTempTariffRateUnit", "req", "Tariff Rate/Unit is Mandatory Field" );
	 				hisValidator.addValidation("strOfflineTariffQty", "req", "Tariff Quantity is Mandatory Field" );
	 				hisValidator.addValidation("strOfflineTariffUnit", "dontselect=0", "Tariff Unit is Mandatory Field" );
 				}	
	 				hisValidator.addValidation("strReconcilationBy", "dontselect=0", "Reconciliation By is a Mandatory Field" );
	    			hisValidator.addValidation("strReconcilationReasonText", "req", "Reason is Mandatory Field" );
        			var retVal = hisValidator.validate(); 
        			hisValidator.clearAllValidations();
        			
        			if(retVal)
        			{	        				
        					
        					document.forms[0].strReconcilationReasonText.disabled = false;
        				
        					document.forms[0].hmode.value="INSERT1";
		    				document.forms[0].submit();
											
        			}
        			else{
        				
        				generateRows();
        				
        				return false;
        			}
        				
 	 	
 	 	
 	 	
 	 }
 	 
 	
 	/**
 		This function is used to submit the page for inserting the reconciliation data after validation
 	*/
 /*	
 	function saveData()
 	{
 		var selBillNoObj = document.getElementsByName("strBillNoVal");
 		var selBillNo = '';
 		var i = 0;
 		var j = 0;
 		var k = 0;
 		var counter = 0;
 		var dataFlag = 0;
 		var grpDtl = '';
 		var bServiceId = '';
 		
 		var qtyObj;
 		var unitObj;
 		var grpDtlObj; 
 			
 		if(selBillNoObj.length > 0) 
 		{
 			selBillNo = selBillNoObj[0].value;
 			if(selBillNo != null && selBillNo != '')
 			{
 				var selBillArray = selBillNo.split("^");
 				bServiceId = selBillArray[3];
 				
 				//in case of opd/ipd bill, append the tariff values and set into strRecTrfDtl hidden field
 				if(bServiceId != null && bServiceId != '')
 				{
 				//	if(parseInt(bServiceId) != 21)	//not final settlement, only opd/ipd bill
 				//	{
 						var trfChkObj = document.getElementsByName("strBillTariffVal");
 						qtyObj = document.getElementsByName("strBillTariffRefund");
 						unitObj = document.getElementsByName("strBillTariffUnit");
 						var costObj = document.getElementsByName("strBillTariffRefundAmount");
 						
 						//hidden field having consolidated tariff data
 						grpDtlObj = document.getElementsByName("strTariffDetailsValue")[0];
 						
 						alert("grpDtlObj : "+grpDtlObj.value);
 						
 						grpDtlObj.value = '';
 						
 						alert("trfChkObj : "+trfChkObj.length);
 						
 						if(trfChkObj.length > 0)
 						{
 							for(i=0;i<trfChkObj.length;i++)
 							{
 								if(trfChkObj[i].checked)
 								{
 									//check the user entered value
 									if(qtyObj[i].value == null || qtyObj[i].value == 0 || qtyObj[i].value == '') {
				 						alert("Entered Reconcile Qty is In-Valid\nEnter Valid Reconcile Qty");
				 						qtyObj[i].focus();
				 						return;
				 					}
				 					//unit
				 					if(unitObj[i].value == null || unitObj[i].value == 0 || unitObj[i].value == '') {
				 						alert("Entered Unit is In-Valid\nEnter Valid Unit");
				 						unitObj[i].focus();
				 						return;
				 					}
				 					//append the value and put into strRecTrfDtl hidden field [checkBox Value@qty@unit@cost#.....]
				 					if(counter == 0)
				 					{
				 						grpDtl = trfChkObj[i].value + "@" + qtyObj[i].value + "@" + unitObj[i].value + 
				 								"@" + costObj[i].value;
				 					}
				 					else
				 					{
				 						grpDtl += "#" + trfChkObj[i].value + "@" + qtyObj[i].value + "@" + unitObj[i].value + 
				 								"@" + costObj[i].value;
				 					}
				 						
				 					counter++;
 									
 								}
 							} //for loop
 							
 							//tariff check box is checked
 							if(counter > 0) {
 								grpDtlObj.value = grpDtl;
 								
 								alert("grpDtlObj : "+grpDtlObj.value);
 								
 								dataFlag = 1;	//data exists for insert
 							}	
 						}
 				//	}
 					
 					//check for tariff name [same name in new tariff and reconciliation are not allowed]
 					
 					//store multirow contents in order to avoid wrong validations
 					var multiRowHtml = (document.getElementById("rowAdded3")).innerHTML;
 					(document.getElementById("rowAdded3")).innerHTML = "";
 					
 					grpDtlObj = document.getElementsByName("strTariffDetailsValue"); //hidden field having tariff details
 					
 					var newTrfHiddArray;
 					var newTrfId;
 					var recTrfId;
 					var grpDtlArray;
 					var tempTrfDtlArray;
 					
 					var newTrfObj = document.getElementsByName("strOfflineTariffName");
 					var newTrfHiddObj = document.getElementsByName("strOfflineTariffDetailsHidden");
 					
 					qtyObj = document.getElementsByName("strOfflineTariffQty");
 					unitObj = document.getElementsByName("strOfflineTariffUnit");
 					
 					if(newTrfObj.length > 0)
 					{
 						//loop till the end of new tariff
 						for(i=0;i<newTrfObj.length;i++)
 						{
 							//tariff
 							if(newTrfObj[i].value == null || newTrfObj[i].value == '')
 							{
 								alert("Tariff Name is empty\nplease select tariff");
 								newTrfObj[i].focus();
 								//put the store innerhtml into div for multirow
 								(document.getElementById("rowAdded3")).innerHTML = multiRowHtml;
 								return;
 							}
 							//tariff hidden field
 							if(newTrfHiddObj[i].value == null || newTrfHiddObj[i].value == '')
 							{
 								alert("Problem with tariff name selection\nplease try again !!");
 								newTrfObj[i].focus();
 								//put the store innerhtml into div for multirow
 								(document.getElementById("rowAdded3")).innerHTML = multiRowHtml;
 								return;
 							}
 							//qty
 							if(qtyObj[i].value == null || qtyObj[i].value == 0 || qtyObj[i].value == '') {
				 				alert("Entered Qty is In-Valid\nEnter Valid Qty");
				 				qtyObj[i].focus();
				 				//put the store innerhtml into div for multirow
 								(document.getElementById("rowAdded3")).innerHTML = multiRowHtml;
				 				return;
				 			}
				 			//unit
				 			if(unitObj[i].value == null || unitObj[i].value == 0 || unitObj[i].value == '') {
				 				alert("Entered Unit is In-Valid\nEnter Valid Unit");
				 				unitObj[i].focus();
				 				//put the store innerhtml into div for multirow
 								(document.getElementById("rowAdded3")).innerHTML = multiRowHtml;
				 				return;
				 			}
				 			
				 			//check for duplicacy
				 			//get value from tariff hidden field
				 			newTrfHiddArray = (newTrfHiddObj[i].value).split("^");
				 			newTrfId = newTrfHiddArray[0];
				 			
				 			//grpDtlObj will have consolidated tariff seperated by #
				 			for(j=0;j<grpDtlObj.length;j++)
				 			{
				 				if(grpDtlObj[j].value != null && grpDtlObj[j].value != '')
				 				{
				 					grpDtlArray = (grpDtlObj[j].value).split('#');
				 					//sinfle group will have tariff details (chk value@qty@unit@cost)
				 					for(k=0;k<grpDtlArray.length;k++)
				 					{
				 						trfDtlArray = (grpDtlArray[k]).split("@");
				 						tempTrfDtlArray = trfDtlArray[0].split("^");	//split check box value
				 						
				 						if(parseInt(bServiceId) == 21)	//Final Settlement
				 							recTrfId = tempTrfDtlArray[3];
				 						else	 
				 							recTrfId = tempTrfDtlArray[0];
				 						
				 						if(recTrfId.toUpperCase() == newTrfId.toUpperCase()) 
				 						{
				 							alert("New Tariff Name and Reconciled tariff can not be same\nCorrect it and then continue !!");
				 							newTrfObj[i].focus();
				 							//put the store innerhtml into div for multirow
 											(document.getElementById("rowAdded3")).innerHTML = multiRowHtml;
				 							return;
				 						}
				 					}
				 				}
				 			} //for loop
				 			
 						} //for loop
 						
 						dataFlag = 1;	//data exists for insert
 					}
 					
 					//put the store innerhtml into div for multirow
 					(document.getElementById("rowAdded3")).innerHTML = multiRowHtml;
 					 					
 					//check data exists for insert whether reconcile entry is made
 					if(dataFlag == 0)
 					{
 						for(i=0;i<grpDtlObj.length;i++)
 						{
 							if(grpDtlObj[i].value != null && grpDtlObj[i].value != '')
 							{
 								dataFlag = 1;	//data exists for insert
 								break;
 							}	
 						}
 					}
 					
 					if(dataFlag == 0)
 					{
 						alert("No data for save\nplease make entry before submission !!");
 						return;
 					}
 					
 					//check mandatory field
 		 			var hisValidator = new HISValidator("billReconcileTransBean");  
	 				
	 				hisValidator.addValidation("strReconcilationBy", "dontselect=0", "Reconciliation By is a Mandatory Field" );
	    			hisValidator.addValidation("strReconcilationReasonText", "req", "Reason is Mandatory Field" );
        			var retVal = hisValidator.validate(); 
        			
        			if(retVal)
        			{
        				//clear object
        				hisValidator.clearAllValidations();
        				//submit the page
        				{
        					//alert("inside insert");
        					//alert("--->"+document.forms[0].strOfflineTariffDetailsHidden.value);
        					
        					//alert("---dis unit-->"+document.forms[0].strOffLineTariffDiscountUnit.value);
		    				document.forms[0].hmode.value="INSERT1";
		    				document.forms[0].submit();
						}
						
        			}
        			else
        				hisValidator.clearAllValidations();
 				}
 				else
 					alert("Bill No is not properly loaded\nContact System Administrator");
 			}
 			else
 			{
 				alert("Bill No is not selected\nPlease Select a Bill and then continue !!");
 				document.forms[0].strCrNo.focus();
 			}	
 		}
 		else
 		{
 			alert("Bill No does not exist !!");
 		}
 	}
*/
 	
 /*
  * This function is used for pop up on click of tariff name in tariff details.
  */	
 	
 	 var pWindow ="";
function myFunc(obj,linkVal,id)
{
  pWindow = obj;
  var mode = "PopUp"; 
  var url="BillReconcileTransCNT.cnt?hmode="+mode+"&modName="+linkVal+"&modeType="+id;
  
  ajaxFunction(url,"9"); 
}



function showFirstPage() {
 	
 	document.forms[0].strCrNo.value = "";
 	//document.forms[0].CrNo.value = "";
 	//document.forms[0].patdtltld.value="";
 	document.forms[0].hmode.value = "RECONCILE";
	document.forms[0].submit();
 }	
 
 
 	
	  		