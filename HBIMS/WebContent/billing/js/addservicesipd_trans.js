
var gblStatusForTariff = "0";
 
 /**
  * onLoadLogics
  * @param {}  
  */
  function onLoadLogics() {
  	
  		   	
  		if(document.forms[0].strCrNo.value.length == document.forms[0].strCrNo.maxLength){
  			
  			document.forms[0].strCrNo.disabled = true
  			document.forms[0].strGeneralTariffCode.focus();
  			
  			if(document.getElementsByName("strCatgoryCode").length > 0){
  				 document.forms[0].strPatientCategory.value = document.forms[0].strCatgoryCode.value;
  			}
  			  
  			  gblStatusForTariff = "1";			
  			  getTariffDtls('1');			
  			 			
  			  			
  		}else{
  			
  			document.forms[0].strCrNo.disabled = false;
  			document.forms[0].strCrNo.focus();
  			
  			
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
	 * goFunc - validates the Cr Number Text Box and submit the page with hmode 'GO' if validation succeeds 
	 */
	function goFunc(){
	
	if(document.forms[0].strCrNo.disabled == false){
		var hisValidator = new HISValidator("AddServicesIPDTransBean");  
	
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
	
	
	function setSelectedTariff(userValue ,resultText, resultValue){
		
		 varObj = document.getElementsByName("strOfflineTariffName");
		      		 
		       		/* 
		      		 for(var i=0; i<varObj.length - 1; i++) {
		      		 	
		      		 		if(varObj[i].value == resultText){
		      		 			
		      		 			alert("Tariff Name Already Exists");
		      		 			document.forms[0].strTariffCode.value = '';
		      					document.forms[0].strTariffCode.focus();
		      			
		      		 			return false;
		      		 		}
		      		 	
		      		 }
			*/
			
			tariffObj = document.getElementById("strOfflineTariffName"+userValue);
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
				
			document.getElementById("strOfflineTariffServiceTax"+userValue).value = temp[13];
			document.getElementById("strOfflineTariffServiceTaxDivId"+userValue).innerHTML = temp[13];
			
			document.getElementById("strOfflineTariffQty"+userValue).value = 1;
			
			document.getElementById("strOfflineTariffNetAmount"+userValue).value = 0;
			document.getElementById("strOfflineTariffNetAmountDivId"+userValue).innerHTML = 0;
		
			document.getElementById("strOfflineTariffDetailsHidden"+userValue).value = resultValue;
		
			
			getOffLineTariffUnit(temp[5],temp[6],userValue);
						
			 var tempVal = userValue.split("-");			
						
			if(document.getElementById("strOfflineTariffName"+tempVal[0]+"-"+ document.getElementsByName("rowIndex"+tempVal[0])[0].value) != null && document.getElementById("strOfflineTariffName"+tempVal[0]+"-"+ document.getElementsByName("rowIndex"+tempVal[0])[0].value).value.length > 0){
	 			 	
				generateRows(tempVal[0]);
				 	 
				document.getElementById("strOfflineTariffName"+tempVal[0]+"-"+ document.getElementsByName("rowIndex"+tempVal[0])[0].value ).focus();
				 	 
			 	}
	    
						
	//	document.getElementById("tariffFullNameDiv").innerHTML = "";
		
			
	}
	
	
	
	function setDropDownSelectedTariff(userValue , resultValue){
		 			
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
				
			document.getElementById("strOfflineTariffServiceTax"+userValue).value = temp[13];
			document.getElementById("strOfflineTariffServiceTaxDivId"+userValue).innerHTML = temp[13];
			
			document.getElementById("strOfflineTariffQty"+userValue).value = 1;
			
			document.getElementById("strOfflineTariffNetAmount"+userValue).value = 0;
			document.getElementById("strOfflineTariffNetAmountDivId"+userValue).innerHTML = 0;
		
			document.getElementById("strOfflineTariffDetailsHidden"+userValue).value = resultValue;
											
									
											
			getOffLineTariffUnit(temp[5],temp[6],userValue);
			
			 var tempVal = userValue.split("-");			
						
			if(document.getElementById("strOfflineTariffName"+tempVal[0]+"-"+ document.getElementsByName("rowIndex"+tempVal[0])[0].value) != null && document.getElementById("strOfflineTariffName"+tempVal[0]+"-"+document.getElementsByName("rowIndex"+tempVal[0])[0].value).value.length > 0){
	 			 	
				generateRows(tempVal[0]);
				 	 
				document.getElementById("strOfflineTariffName"+tempVal[0]+"-"+ document.getElementsByName("rowIndex"+tempVal[0])[0].value ).focus();
				 	 
			 	}
	    
			
	 
				
	//	document.getElementById("tariffFullNameDiv").innerHTML = "";
		
		
	}

function showTariffSearchPopup(e,compName,index){
	
	var groupId = "0";
	var wardCode = document.getElementById("strCompChargeCheck"+index).value;
	var patCat = document.forms[0].strPatientCategory[document.forms[0].strPatientCategory.selectedIndex].value;	
		
		
	if(document.getElementById("strCompChargeCheck"+index).value == 1){
		
		groupId = document.forms[0].strGeneralOffLineGroup[document.forms[0].strGeneralOffLineGroup.selectedIndex].value;
		
	}else{
		
		groupId = document.forms[0].strPrivateOffLineGroup[document.forms[0].strPrivateOffLineGroup.selectedIndex].value;
	}	
		
	
	 
			
				 
		
//	tariffSearchPopUp(e,compName,"2","",wardCode,groupId,'setSelectedTariff',index);
	tariffSearchPopUp(e,compName,"2",patCat,wardCode,groupId,'setSelectedTariff',index);
}


 
//function tariffDtl(parent,index,a,b,c,d,e) //(this,index,ServiceTax,Discount,Penlty,TariffRate,Unit Name)
  function tariffDtl(parent,index,a)
  {        
        var temp = a.split('^');
        var objVal1 = document.getElementById("1");
        objVal1.innerHTML = temp[3]+"/"+temp[4];
        var objVal2 = document.getElementById("2");
        objVal2.innerHTML = temp[1];        
        var objVal3 = document.getElementById("3");
        objVal3.innerHTML = temp[0];        
        var objVal4 = document.getElementById("4");
        objVal4.innerHTML = temp[2];   
                         
         display_popup_menu(parent,'tariffDtl','300','');
}
	
	
	
	/**
		 * getOffLineTariffUnit
		 * @param {String} unitId
		 * @param {String} baseValue
		 * @param {String} index 
		 */
		 function getOffLineTariffUnit(unitId,baseValue,index) 
		 {
		 	if(unitId!=0 && baseValue!=0){
		 	var hmode = "TRFUNIT"; 
		 	var modeVal = unitId+"^"+baseValue+"^"+index;
		    var url="AddServicesIPDTransCNT.cnt?hmode="+hmode+"&modName="+modeVal;
			ajaxFunction(url,"3");
			}else{
		 		document.getElementById("offlineTariffUnitDivId"+index).innerHTML = "<select name='strOfflineTariffUnit' id='strOfflineTariffUnit"+index+"' class='comboMin' onChange='calcOffLineTariffNetAmount(\""+index+"\");'><option value=0>Select Value</option></select>";				
		 		calcOffLineTariffNetAmount(index);
		 	}
		 	
		 	
		 }
	
	
	
	/**
	 * removeTariffRow
	 * @param {Object} index 
	 */
	 function removeTariffRow(index) {
		if(!showAlert())
			return false;
	 		 	
	 	deleteRow(index,'1','0');
	 	
	 	 calcTotals();
	 	
	 }
	
	
	/**
	 * showDetailsDependent - shows the given divId by showing the minus image and hiding the plus image also hides the oppdivId by showing the plus image and hiding the minus image
	 * @param {object} divId - div id which should be shown.
	 *  @param {object} oppdivId - div id which should be hide.
	 */
	function showDetailsDependent(chkObj , wardType , divIdName){
			 
			 if(!document.forms[0].strIsGeneralRequired.checked && !document.forms[0].strIsPrivateRequired.checked){
			 	
			 		document.forms[0].strIsPrivateTariffAvailable.value = '0';
			 		document.forms[0].strIsGeneralTariffAvailable.value = '0';
			 		
			 		document.getElementById("disDateCompDivId").style.display = "block";
			 		document.getElementById("disDateDipslayDivId").style.display = "none";
			 		
			 		document.forms[0].strPatientCategory.disabled = false;
			 	
			 }else{
			 				 		
			 		document.forms[0].strIsPrivateTariffAvailable.value = '0';
			 		document.forms[0].strIsGeneralTariffAvailable.value = '0';
			 		
			 		document.getElementById("disDateCompDivId").style.display = "none";
			 		document.getElementById("disDateDipslayDivId").style.display = "block";
			 		
			 		document.forms[0].strPatientCategory.disabled = true;
			 	
			 }
			 
				if(chkObj.checked){
				 
					if(document.forms[0].strIsPrivateTariffAvailable.value == '0' && wardType == '2'){
						
						document.forms[0].strIsPrivateTariffAvailable.value = '1';
						getTariffDtls(wardType);
						
					} else if(document.forms[0].strIsGeneralTariffAvailable.value == '0' && wardType == '1'){
						
						document.forms[0].strIsGeneralTariffAvailable.value = '1';
						getTariffDtls(wardType);
						
					} else {
						
						 getDefaultTariffList(wardType);
						
					}
									
					
					
					document.getElementById(divIdName).style.display = "block";	
									
				}else{
					
					document.getElementById(divIdName).style.display = "none";
					 				
				
					resetMultiRow(wardType);
					generateRows(wardType);
		 
		 			if(wardType == '1'){
		 				
		 				document.getElementById("generalCompulsoryCharges").innerHTML = "";
		 				
		 			}else{
		 				
		 				document.getElementById("privateCompulsoryCharges").innerHTML = "";
		 				
		 			}
		 
					
				}	
								
			  
				calcTotals();
		 
	}
	
	
	/**
	 * showDetails
	 * @param {String} divId 
	 */
	 function showDetails(divId) {
	 	
	 	document.getElementById(divId).style.display="block";
	 	document.getElementById('minus'+divId).style.display="block";
		document.getElementById('plus'+divId).style.display="none";
	 	
	 }
	
	/**
	 * hideDetails
	 * @param {String} divId 
	 */
	 function hideDetails(divId) {
	 	
	 	document.getElementById(divId).style.display="none";
	 	document.getElementById('minus'+divId).style.display="none";
		document.getElementById('plus'+divId).style.display="block";
	 	
	 }
	
	
	
	/**
	 * manageCompalsaryChargesTariff
	 * @param {Object} chkObj 
	 * @param {String} index	
	 */
	 function manageCompalsaryChargesTariff(chkObj , index) {
	 	
	 	if(chkObj.checked){
	 		
	 			
	 	//	var qty = getDateDifference(document.forms[0].strStartDate.value , document.forms[0].strEndDate.value);
	 					
	 	 var qty = "1";
	 					
	 			document.getElementById("strOfflineTariffName"+index).disabled = false;
	 			document.getElementById("strOfflineTariffDetailsHidden"+index).disabled = false;
	 			document.getElementById("strOfflineTariffRateUnit"+index).disabled = false;
	 			document.getElementById("strOfflineTariffQty"+index).value = qty;
	 			document.getElementById("strOfflineTariffQty"+index).disabled = false;
	 			document.getElementById("strOfflineTariffUnit"+index).disabled = false;
	 			document.getElementById("strOfflineTariffServiceTax"+index).disabled = false;
	 			document.getElementById("strOfflineTariffServiceTaxAmtVal"+index).disabled = false;
	 			document.getElementById("strOfflineActualTariffAmtVal"+index).disabled = false;
	 			document.getElementById("strOfflineTariffNetAmount"+index).disabled = false;
	 				 					
	 		
	 	}else{
	 		
	 			document.getElementById("strOfflineTariffName"+index).disabled = true;
	 			document.getElementById("strOfflineTariffDetailsHidden"+index).disabled = true;
	 			document.getElementById("strOfflineTariffRateUnit"+index).disabled = true;
	 			document.getElementById("strOfflineTariffQty"+index).value = 0;
	 			document.getElementById("strOfflineTariffQty"+index).disabled = true;
	 			document.getElementById("strOfflineTariffUnit"+index).disabled = true;
	 			document.getElementById("strOfflineTariffServiceTax"+index).disabled = true;
	 			document.getElementById("strOfflineTariffServiceTaxAmtVal"+index).disabled = true;
	 			document.getElementById("strOfflineActualTariffAmtVal"+index).disabled = true;
	 			document.getElementById("strOfflineTariffNetAmount"+index).value = 0;
	 			document.getElementById("strOfflineTariffNetAmount"+index).disabled = true;
	 			document.getElementById("strOfflineTariffNetAmountDivId"+index).innerHTML = "0.00";
	 				 		
	 	}
	 	
	 	calcOffLineTariffNetAmount(index);
	 	
	 	
	 }
	
	
	
	
// tariff total calculation
          /** calcOffLineTariffNetAmount

             * @param {Object} qtyObj - Quatity Text Box object 

             * @param {String} index 

             */

             function calcOffLineTariffNetAmount(index) 
             {
             	   	
             var tariff =  document.getElementById("strOfflineTariffName"+index).value;	 		 	
	 	var a = document.getElementById("strOfflineTariffDetailsHidden"+index).value.split('^');
		var b=true;
		for(var i = 0 ; i<a.length; i++)
			if(a[i]!="0"){
				b=false;
				break;
			}
			
	
		if(b ||tariff != ''){	 		
			 	
	 		var temp = document.getElementById("strOfflineTariffDetailsHidden"+index).value.split('^');
	 			 		
	 		var rate = temp[4];
	 		
	 		if(temp[17] == '1'){
	 			
	 			rate = document.getElementById("strOfflineTempTariffRateUnit"+index).value;
	 		}
	 		
	 		var actRate = temp[11];
	 		var rateBaseValue = temp[6];
	 		var qtyBaseValue = "0";
	 		var qtyVal =  document.getElementById("strOfflineTariffQty"+index).value;
	 		var discountVal = 0;
	 		var serviceVal = document.getElementById("strOfflineTariffServiceTax"+index).value;
	 		var discountType = 0;
	 		
	 		var netAmt = 0;
	 		
	 		
	 		
	 		if(document.getElementById("strOfflineTariffUnit"+index).selectedIndex != null){
	 			
	 			qtyBaseValue = document.getElementById("strOfflineTariffUnit"+index).options[document.getElementById("strOfflineTariffUnit"+index).selectedIndex].value;
	 			
	 		}else{
	 			
	 			qtyBaseValue = document.getElementById("strOfflineTariffUnit"+index).value+"^1";
	 			
	 		}
	 		
	 		 		
	 		var calAmt = calTrfNetAmountWithActTrfAmt(rate, actRate ,rateBaseValue,discountVal,discountType,qtyVal,qtyBaseValue,serviceVal,0,0); 
	 		
	 		
	  		 			 		 		 	
	 	
	 	netAmt = calAmt.oNetTrfAmt;
	 	
	 	
	 	
	 			document.getElementById("strOfflineTariffServiceTaxAmtVal"+index).value = calAmt.oSerAmt;
	 			document.getElementById("strOfflineActualTariffAmtVal"+index).value = calAmt.oActTrfAmt;
	 			document.getElementById("strOfflineTariffNetAmount"+index).value = netAmt;
	 			document.getElementById("strOfflineTariffNetAmountDivId"+index).innerHTML = netAmt;
	 			        
     }            
     
     
	 	calcTotals();
              
 }
 
	
	/**
	 * calcTotals
	 */
	 function calcTotals() {
	 		 	
	 	var genTotal = parseFloat("0");
	 			var priTotal = parseFloat("0");
	 			
	 			
	 			var chkObj = document.getElementsByName("strCompChargeCheck");
	 			
	 			var netAmtObj = document.getElementsByName("strOfflineTariffNetAmount");
	 			
	 			for(var i=0 , stopI = chkObj.length ; i<stopI; i++) {
	 				
	 					if(chkObj[i].value == 1){
	 						
	 						if(netAmtObj[i].value.length > 0)
	 							genTotal = genTotal + parseFloat(netAmtObj[i].value);
	 						
	 					}else{
	 						
	 						if(netAmtObj[i].value.length > 0)
	 							priTotal = priTotal + parseFloat(netAmtObj[i].value);
	 						
	 					}
	 					 				
	 			}
	 			
	 			
	 			genTotal = roundValue(genTotal , 2);
	 			priTotal = roundValue(priTotal , 2);
	 				 			
	 			var grantTotal = manipulateValue( genTotal , priTotal , 0 );
	 			
	 			grantTotal = roundValue(grantTotal , 2);
	 			
	 		
	 		document.getElementById("generalTotalDivId").innerHTML = genTotal;
	 		document.getElementById("privateTotalDivId").innerHTML = priTotal;
	 		document.getElementById("grantTotalDivId").innerHTML = grantTotal;
	 			
	 	
	 }
	 
	 
	 /**
	  * getQtyCalculator
	  * @param {Object} parentObj 
	  * @param {String} wardType
	  */
	  function getQtyCalculator(parentObj , wardType) {
	  	   	  	  
	  		popup('genQtyCalculatorDivId','200','200');
	  		
	  		document.getElementsByName("strFromDate")[0].focus();
	  	
	  }
	 
	  /**
	  * getQtyCalculator
	  * @param {Object} parentObj 
	  * @param {String} wardType
	  */
	  function getPvtQtyCalculator(parentObj , wardType) {
	  	
	  		 popup('prvQtyCalculatorDivId','200','200');
	  		
	  		document.getElementsByName("strPrivateFromDate")[0].focus();
	  	
	  }
	 
	 
	 /**
	  * setQuantityValues
	  * @param {String} wardType 
	  */
	  function setQuantityValues(wardType) {
	  		  	
	  	if(wardType == '1'){
	  		
	  		var hisValidator = new HISValidator("AddServicesIPDTransBean");  	
	  		
	  		hisValidator.addValidation("strFromDate", "req", "From Date is a Mandatory Field" );
	  		hisValidator.addValidation("strFromDate", "date", "From Date should be a Valid Date" );
	  		hisValidator.addValidation("strFromDate", "dtgtet="+document.forms[0].strAcctOpngDate.value, "From Date should be greater than or equal to Account Opening Date" );
	  		hisValidator.addValidation("strFromHr", "req", "From Time is a Mandatory Field" );
	  		hisValidator.addValidation("strFromHr", "numlt=60", "From Time Hour should be less than 60" );
	  		hisValidator.addValidation("strFromMm", "req", "From Time is a Mandatory Field" );
	  		hisValidator.addValidation("strFromMm", "numlt=60", "From Time Minute should be less than 60" );
	  		
	  		hisValidator.addValidation("strToDate", "req", "To Date is a Mandatory Field" );
	  		hisValidator.addValidation("strToDate", "date", "To Date should be a Valid Date" );
	  		hisValidator.addValidation("strToDate", "dtltet="+document.forms[0].strDischargeDate.value, "To Date should be lesser than or equal to Discharge Date" );
	  		hisValidator.addValidation("strToHr", "req", "To Time is a Mandatory Field" );
	  		hisValidator.addValidation("strToHr", "numlt=60", "To Time Hour should be less than 60" );
	  		hisValidator.addValidation("strToMm", "req", "To Time is a Mandatory Field" );
	  		hisValidator.addValidation("strToMm", "numlt=60", "To Time Minute should be less than 60" );
	  		
	  		
	  		
	  		
	  			  		
	  		var retVal = hisValidator.validate(); 
	    	hisValidator.clearAllValidations();
			
			if(retVal)
			{
			
					var strDateString = "";
			
					var frmDateObj = document.getElementsByName("strFromDate");
					var toDateObj =  document.getElementsByName("strToDate");
					var frmHrObj = document.getElementsByName("strFromHr");
					var frmMmObj = document.getElementsByName("strFromMm");
					var toHrObj = document.getElementsByName("strToHr");
					var toMmObj = document.getElementsByName("strToMm");
					
					
					for(var i=0; i<frmDateObj.length - 1; i++) {
						
						var compDate = compareDate(frmDateObj[i].value , toDateObj[i].value);
							
						if(compDate.mode == 2){
							
							alert("From Date cannot be Greater than To Date");
							frmDateObj[i].focus();	
							return false;
						}
						
						
						if(frmDateObj[i + 1] != null && frmDateObj[i + 1].value.length > 0){
							
							var newcompDate = compareDate(toDateObj[i].value , frmDateObj[i + 1].value );
							
							if(newcompDate.mode == 2){
							
							alert("From Date cannot be Less than Previous To Date");
							frmDateObj[i + 1].focus();	
							return false;
						}
							
						}
						
						
						var fromTime = parseInt(frmHrObj[i].value +""+frmMmObj[i].value);
						var toTime = parseInt(toHrObj[i].value +""+toMmObj[i].value);
						
						if(compDate.mode == 1 && fromTime > toTime){
							
							alert("From Time cannot be Greater than To Time");
							frmHrObj[i].focus();
							return false;
						}						
						
						if( i == 0){
							
							strDateString = frmDateObj[i].value+" "+frmHrObj[i].value+":"+frmMmObj[i].value+","+
										toDateObj[i].value+" "+toHrObj[i].value+":"+toMmObj[i].value;
							
						}else{
							
							strDateString = strDateString + "$" + 
										frmDateObj[i].value+" "+frmHrObj[i].value+":"+frmMmObj[i].value+","+
										toDateObj[i].value+" "+toHrObj[i].value+":"+toMmObj[i].value;
							
						}
						
						
						
					}
				
				 hide_popup('genQtyCalculatorDivId');		
								
													
			var hmode = "GETQTY"; 
			var url = "AddServicesIPDTransCNT.cnt?hmode="+hmode+"&strWardType="+wardType+"&strDateString="+strDateString;
		
			ajaxFunction(url,"8");
			
															
			
			}else{
				return false;
			}
	  		
	  		
	  	}else{
	  		
	  		
	  		
	  		var hisValidator = new HISValidator("AddServicesIPDTransBean");  	
	  		
	  		hisValidator.addValidation("strPrivateFromDate", "req", "From Date is a Mandatory Field" );
	  		hisValidator.addValidation("strPrivateFromDate", "date", "From Date should be a Valid Date" );
	  		hisValidator.addValidation("strPrivateFromDate", "dtgtet="+document.forms[0].strAcctOpngDate.value, "From Date should be greater than or equal to Account Opening Date" );
	  		hisValidator.addValidation("strPrivateFromHr", "req", "From Time is a Mandatory Field" );
	  		hisValidator.addValidation("strPrivateFromHr", "numlt=60", "From Time Hour should be less than 60" );
	  		hisValidator.addValidation("strPrivateFromMm", "req", "From Time is a Mandatory Field" );
	  		hisValidator.addValidation("strPrivateFromMm", "numlt=60", "From Time Minute should be less than 60" );
	  		
	  		hisValidator.addValidation("strPrivateToDate", "req", "To Date is a Mandatory Field" );
	  		hisValidator.addValidation("strPrivateToDate", "date", "To Date should be a Valid Date" );
	  		hisValidator.addValidation("strPrivateToDate", "dtltet="+document.forms[0].strDischargeDate.value, "To Date should be lesser than or equal to Discharge Date" );
	  		hisValidator.addValidation("strPrivateToHr", "req", "To Time is a Mandatory Field" );
	  		hisValidator.addValidation("strPrivateToHr", "numlt=60", "To Time Hour should be less than 60" );
	  		hisValidator.addValidation("strPrivateToMm", "req", "To Time is a Mandatory Field" );
	  		hisValidator.addValidation("strPrivateToMm", "numlt=60", "To Time Minute should be less than 60" );
	  		
	  		
	  		
	  		
	  			  		
	  		var retVal = hisValidator.validate(); 
	    	hisValidator.clearAllValidations();
			
			if(retVal)
			{
			
					var strDateString = "";
			
					var frmDateObj = document.getElementsByName("strPrivateFromDate");
					var toDateObj =  document.getElementsByName("strPrivateToDate");
					var frmHrObj = document.getElementsByName("strPrivateFromHr");
					var frmMmObj = document.getElementsByName("strPrivateFromMm");
					var toHrObj = document.getElementsByName("strPrivateToHr");
					var toMmObj = document.getElementsByName("strPrivateToMm");
					
					
					for(var i=0; i<frmDateObj.length - 1; i++) {
						
						var compDate = compareDate(frmDateObj[i].value , toDateObj[i].value);
							
						if(compDate.mode == 2){
							
							alert("From Date cannot be Greater than To Date");
							frmDateObj[i].focus();	
							return false;
						}
						
						
						if(frmDateObj[i + 1] != null && frmDateObj[i + 1].value.length > 0){
							
							var newcompDate = compareDate(toDateObj[i].value , frmDateObj[i + 1].value );
							
							if(newcompDate.mode == 2){
							
							alert("From Date cannot be Less than Previous To Date");
							frmDateObj[i + 1].focus();	
							return false;
						}
							
						}
						
						
						var fromTime = parseInt(frmHrObj[i].value +""+frmMmObj[i].value);
						var toTime = parseInt(toHrObj[i].value +""+toMmObj[i].value);
						
						if(compDate.mode == 1 && fromTime > toTime){
							
							alert("From Time cannot be Greater than To Time");
							frmHrObj[i].focus();
							return false;
						}						
						
						if( i == 0){
							
							strDateString = frmDateObj[i].value+" "+frmHrObj[i].value+":"+frmMmObj[i].value+","+
										toDateObj[i].value+" "+toHrObj[i].value+":"+toMmObj[i].value;
							
						}else{
							
							strDateString = strDateString + "$" + 
										frmDateObj[i].value+" "+frmHrObj[i].value+":"+frmMmObj[i].value+","+
										toDateObj[i].value+" "+toHrObj[i].value+":"+toMmObj[i].value;
							
						}
						
						
						
					}
				
				 hide_popup('prvQtyCalculatorDivId');		
												
													
			var hmode = "GETQTY"; 
			var url = "AddServicesIPDTransCNT.cnt?hmode="+hmode+"&strWardType="+wardType+"&strDateString="+strDateString;
		
					
			ajaxFunction(url,"9");
																	
			
			}else{
				return false;
			}
	  		
	  			  		
	  		
	  	}
	  	
	  }
	  
	
	  
	  
	  /**
	   * cancelQuantityValues
	   * @param {String} wardType 
	   */
	   function cancelQuantityValues(wardType) {
	   	
	   		if(! confirm("Cancel the Quantity Calculation Process? Are You Sure?")){
	   			
	   			return false;
	   		}
	   			
	   	
	   		if(wardType == '1'){
	   			
	   			 hide_popup('genQtyCalculatorDivId');
	   			resetMultiRow('3');
	   			addRows( new Array('strFromDate','strFromHr','strFromMm','strToDate','strToHr','strToMm') , new Array('t','t','t','t','t','t'), '3' , '1' , 'R' );
	   			 			
	   			
	   		}else{
	   			
	   			 hide_popup('prvQtyCalculatorDivId');
	   			resetMultiRow('4');
	   			addRows( new Array('strPrivateFromDate','strPrivateFromHr','strPrivateFromMm','strPrivateToDate','strPrivateToHr','strPrivateToMm') , new Array('t','t','t','t','t','t'), '4' , '1' , 'R' );
	   		}
	   	
	   	 	
	   	 	var date1 = document.forms[0].strAcctOpngDate.value;
	   	 	var date2 = document.forms[0].strDischargeDate.value;
	   	
	   		var qty = dateDiff(date1,date2);
	   		
	   		if(qty == '0') qty = "1";
	   		
	   		 	   		
	   			setQtyValues(wardType , qty);	
	   		
	   	
	   }
	  
	  
	  
	  
function dateDiff(date_1,date_2){
 
	var retVal=compareDate(date_1,date_2);

	if(retVal.mode==0){
			var ret=parse_Date(date_1,"-");
			var ret1=parse_Date(date_2,"-");
			var dt1=ret.month+"-"+ret.day+"-"+ret.year;
			var dt2=ret1.month+"-"+ret1.day+"-"+ret1.year;
			date1 = new Date();
			date2 = new Date();
			diff  = new Date();
 	
 
			var myDate1=new Array();
			myDate1=dt1.split("-");
			date1temp = new Date(myDate1[2],myDate1[0],myDate1[1]);
			date1.setTime(date1temp.getTime());

  
			var myDate2=new Array();
			myDate2=dt2.split("-");
			date2temp = new Date(myDate2[2],myDate2[0],myDate2[1]); 		
			date2.setTime(date2temp.getTime());
 
 
		diff.setTime(Math.abs(date1.getTime() - date2.getTime()));
		timediff = diff.getTime();

		weeks = Math.floor(timediff / (1000 * 60 * 60 * 24 * 7));
		timediff -= weeks * (1000 * 60 * 60 * 24 * 7);
		days = Math.floor(timediff / (1000 * 60 * 60 * 24)); 
		timediff -= days * (1000 * 60 * 60 * 24);
		days=parseInt(weeks)*7+days;
 
 		return days;
 
	}
}
	  
	  
	  function parse_Date(dtStr,seprator)
{
	var pos1,pos2,tempLen=0;
	var tempStr = "";
	var intDay = 0,intMon = 0,intYear = 0;
	var retValue = false;
	pos1 = dtStr.indexOf(seprator);
	pos2 = dtStr.indexOf(seprator,pos1+1);
	if(pos1 > 0 && pos2 > (pos1 + 1))
	{
		//day
		tempStr = dtStr.substring(0,pos1);
		tempLen = tempStr.length;
		if(tempLen > 0 && tempLen <=2)
		{
			intDay = parseInt(tempStr,'10');
			//month
			tempStr = dtStr.substring(pos1+1,pos2);
			tempLen = tempStr.length;
			if(tempLen > 0 && tempLen <= 3)
			{
				//format given as dd/mmm/yyyy
				if(tempLen == 3) tempStr = get_MonthInt(tempStr);	
				intMon = parseInt(tempStr,'10');
				if(intMon > 0) 
				{
					//year
					tempStr = dtStr.substring(pos2+1);
					tempLen = tempStr.length;
					if(tempLen == 4)
					{
						intYear = parseInt(tempStr,'10');
						if (intYear >= 1900 && intYear <= 9900)
						{
							if (intMon >= 1 && intMon <= 12)
							{
								if (intDay > 0 && intDay <= Days_InMonth(intMon, intYear))
									retValue = true;
							}
						}
					}
				}
			}
		}
	}
	
	return {cancelKey :retValue,day:intDay,month:intMon,year:intYear};
}
	
	
	
//this is internal function that converts the month(in string) into month(in integer)
function get_MonthInt(str)
{
	var month = "0";
	
	switch(str.toUpperCase())
	{
		case "JAN":
			month = "1";
			break;
		case "FEB":
			month = "2";
			break;
		case "MAR":
			month = "3";
			break;
		case "APR":
			month = "4";
			break;
		case "MAY":
			month = "5";
			break;
		case "JUN":
			month = "6";
			break;
		case "JUL":
			month = "7";
			break;
		case "AUG":
			month = "8";
			break;
		case "SEP":
			month = "9";
			break;
		case "OCT":
			month = "10";
			break;
		case "NOV":
			month = "11";
			break;
		case "DEC":
			month = "12";
			break;
	}
	return month;
}

//this is internal function that returns no of days in a month for the specified year
function Days_InMonth(mon, year) 
{
	var retVal;
	
	retVal = 31;
	if (mon == 4 || mon == 6 || mon == 9 || mon == 11) {retVal = 30;}
	if (mon == 2) {retVal = days_InFebruary(year);}
   	return retVal;
}

//this is internal function that returns day in feb month for specified year
function days_InFebruary (year)
{
	return (((year % 4 == 0) && ( (!(year % 100 == 0)) || (year % 400 == 0))) ? 29 : 28 );
}
	   
	  
	  
	  
	  /**
	   * setQtyValues
	   * @param {String} wardType 
	   * @param {String} qtyValue
	   */
	   function setQtyValues(wardType , qtyValue) {
	   	 
	   			var chkObj = document.getElementsByName("strCompChargeCheck"); 
	   			
	   			for(var i=0; i<chkObj.length; i++) {
	   				
	   				if(chkObj[i].checked && chkObj[i].value == wardType){
	   						   					
	   					var index = wardType+""+(i+1);
	   					
	   					document.getElementById("strOfflineTariffQty"+index).value = qtyValue;
	   					
	   					calcOffLineTariffNetAmount(index);
	   					
	   				}
	   				
	   			}
	   			 
	   }
	  
	  
	
	function generateRows(index){
	 	
		 if(!showAlert())
			return false;
		 
			addRows(new Array('strOfflineTariffName','strOfflineTariffDetailsHidden', 'strCompChargeCheck', 'strOfflineTempTariffRateUnit','strOfflineTariffRateUnit','strOfflineTariffQty','strOfflineTariffUnit','strOfflineTariffServiceTax', 'strOfflineTariffServiceTaxAmtVal','strOfflineActualTariffAmtVal','strOfflineTariffNetAmount'),new Array('t','t','t','t','t','t','s','t','t','t','t'),index,'1','R');
			
			
			
			var tempVal = "strOfflineTariffName"+index+"-"+document.getElementsByName("rowIndex"+index).value;
	 
			var tempObj = document.getElementById(tempVal);
		
		
		tempObj.select();
		tempObj.focus();
			
	}
	
	
	/**
	 * getPreviousDtls
	 * @param {}  
	 */
	 function getPreviousDtls() {
	 	
	 		var hmode = "PREVDTLS"; 
			var url = "AddServicesIPDTransCNT.cnt?hmode="+hmode+"&strAccountNo="+document.getElementsByName("strActHLPHid")[0].value.split('^')[2];
		
			ajaxFunction(url,"1");
	 	
	 }
	 
	
	var gblWardType = "1";
	
	 /**
	  * getTariffByCode
	  * @param {String} wardType 
	  */
	  function getDefaultTariffList(wardType) {
	  		  
	  		  gblWardType = wardType;
	  		  		   						
		
			var startDate = document.forms[0].strCtDate.value;
			
			if(document.getElementsByName("strAcctOpngDate").length > 0){
				 	
				 		startDate = document.forms[0].strAcctOpngDate.value;
				 						 			
				 	} 	
			  		  		   						
	  		  		   						
	  		  		   						 
			var hmode = "DEFULTTARIFFLIST"; 				
			var url ="AddServicesIPDTransCNT.cnt?hmode="+hmode+"&hService=2&treatmentCat="+document.forms[0].strPatientCategory[document.forms[0].strPatientCategory.selectedIndex].value+"&startDate="+startDate+"&endDate="+document.forms[0].strDischargeDate.value+"&wardType="+wardType;
											
			ajaxFunction(url,"2");
								 				 			 
	  }
	 
	 
	 
	 /**
	  * getGeneralTariffByGroup
	  * @param {}  
	  */
	  function getGeneralTariffByGroup() {
	  	
	  	gblStatusForTariff = "0";
	  	
	  	getTariffDtls('1');
	  	
	  }
	 
	 
	 /**
	  * getGeneralTariffByGroup
	  * @param {}  
	  */
	  function getPrivateTariffByGroup() {
	  	
	  	gblStatusForTariff = "0";
	  	
	  	getTariffDtls('2');
	  	
	  }
	 
	 
	 
	 
	 /**
	  * getTariffByCode
	  * @param {Object} tariffCode 
	  * @param {event} e
	  * @param {String} wardType
	  */
	  function getTariffByCode(tariffCode , e , wardType) {
	  		  	
	  	if(e.keyCode == 13){
								 
			var hmode = "TARIFFCODEDTLS"; 
			
			var groupId = "0";
				
			  if(wardType == 1){
	 			
	 			groupId = document.forms[0].strGeneralOffLineGroup[document.forms[0].strGeneralOffLineGroup.selectedIndex].value;
	 			
	 			 			
	 			resMode = "6";
	 			
	 		}else{
	 			
	 			groupId = document.forms[0].strPrivateOffLineGroup[document.forms[0].strPrivateOffLineGroup.selectedIndex].value;
	 				 			
	 			resMode = "7";
	 		}
	  	 
				
			var url = "IpdBillManagementTransCNT.cnt?hmode="+hmode+"&groupId="+groupId+"&hService=2"+"&treatmentCat="+document.forms[0].strPatientCategory[document.forms[0].strPatientCategory.selectedIndex].value+"&ward="+wardType+"&tariffCode="+tariffCode.value;
		 
						
			ajaxFunction(url, resMode);
								 
				 
				return false;
			}	   	
	  	
	  	return false;
	  }
	 
	 
	 
function getTariffDtls(index)
{ 		 	
    		var mode = "TARIFFDTLS"; 

	 		var groupId = "0";
	 		var resMode = "0"; 
	 		  
	 		if(index == 1){
	 			
	 			groupId = document.forms[0].strGeneralOffLineGroup[document.forms[0].strGeneralOffLineGroup.selectedIndex].value;
	 			
	 			resMode = "4";
	 			
	 		}else{
	 			
	 			groupId = document.forms[0].strPrivateOffLineGroup[document.forms[0].strPrivateOffLineGroup.selectedIndex].value;
	 			resMode = "5";
	 		}
	   
				
			var url = "AddServicesIPDTransCNT.cnt?hmode="+mode+"&groupId="+groupId+"&hService=2"+"&treatmentCat="+document.forms[0].strPatientCategory[document.forms[0].strPatientCategory.selectedIndex].value+"&ward="+index;
		 
		 
			ajaxFunction(url,resMode);
}
	
		
	function getAjaxResponse(res,mode){
	
				
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
			
			document.getElementById("previousDetailsDivId").innerHTML = res;
			showDetails("previousDetailsDivId");			
		}
		
		if(mode == '2')
		{
			
			if(gblWardType == '1'){
				
				resetMultiRow('1');
				generateRows('1');
				
				document.getElementById("generalCompulsoryCharges").innerHTML = res;
				
			}else{
				
				resetMultiRow('2');
				generateRows('2');
				
				document.getElementById("privateCompulsoryCharges").innerHTML = res;
				
			}
			
				calcTotals();	
						 		
		}
		
		
	if(mode == '3'){
		       
		var temp = res.split('@');
		document.getElementById("offlineTariffUnitDivId"+temp[0]).innerHTML = "<select name='strOfflineTariffUnit' id='strOfflineTariffUnit"+temp[0]+"' class='comboMin' onChange='calcOffLineTariffNetAmount(\""+temp[0]+"\");'>"+temp[1]+"</select>";	
		calcOffLineTariffNetAmount(temp[0]);   // This Methdo is Called When we Add Value In DropDown Utilty
	}
		
	if(mode == '4'){
						
			document.getElementById("dropdown1").innerHTML = res;
				
				if(gblStatusForTariff == 1){
					
				 getDefaultTariffList('1');
					
				}	
				 
					
		}
		
		if(mode == '5'){
						
			document.getElementById("dropdown2").innerHTML = res;
					
				if(gblStatusForTariff == 1){
					
					 getDefaultTariffList('2');
					
				}		
		}	
		
		
		
		if(mode == '6'){
			 
		       	
		      var tempVal = res.split("@@");
		      
		      	if(tempVal[0] == '0'){
		      		
		      			alert("No Tariff Found to The Corresponding Tariff Code ");
		      			document.forms[0].strGeneralTariffCode.value = '';
		      			document.forms[0].strGeneralTariffCode.focus();
		      			return false;
		      		
		      	}else{
		      		
		      		var indexVal = '1-'+document.multirow.rowIndex1.value;
		      				    
		      			
		      				setSelectedTariff(indexVal ,tempVal[1], tempVal[2] );
		      			
		      		
		      			document.forms[0].strGeneralTariffCode.value = '';
		      			document.forms[0].strGeneralTariffCode.focus();
		      	 		      		 
		      	}	      
		       	
		}	
		
		
		if(mode == '7'){
			 
		       	
		      var tempVal = res.split("@@");
		      
		      	if(tempVal[0] == '0'){
		      		
		      			alert("No Tariff Found to The Corresponding Tariff Code ");
		      			document.forms[0].strPrivateTariffCode.value = '';
		      			document.forms[0].strPrivateTariffCode.focus();
		      			return false;
		      		
		      	}else{
		      		
		      		var indexVal = '2-'+document.multirow.rowIndex2.value;
		      				    
		      			
		      				setSelectedTariff(indexVal ,tempVal[1], tempVal[2] );
		      			
		      		
		      			document.forms[0].strPrivateTariffCode.value = '';
		      			document.forms[0].strPrivateTariffCode.focus();
		      	 		      		 
		      	}	      
		       	
		}	
		
		
		
		if(mode == '8'){
			
			setQtyValues('1' , res);		
		}
		
		if(mode == '9'){
				
				setQtyValues('2' , res);		 
						 
		}
		
		
	}
	
	
	