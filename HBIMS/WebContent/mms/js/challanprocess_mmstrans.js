
function CalculateDrugCost()
{
		           /**
			 		 * Calculate Total Cost of Item
			 		 */
			 		// document.forms[0].strSalePrice.disabled = "false";
			 		 
			 		 document.forms[0].strSalePriceUnitId.disabled = false;			 		 
			 		 var  rateObj              = document.forms[0].strSalePrice.value;	   
			 		 var  rate_unit_base_value = document.forms[0].strSalePriceUnitId.value.split("^")[1];
					 var  qtyObj               = document.forms[0].strAcceptedQuantity.value;
					 var  qty_unit_base_value  = document.forms[0].strAcceptedQuantityUnitId.value.split("^")[1];	
					 //alert("qty:::"+qtyObj+"::Rate:::"+rateObj+"::rate_unit_base_value:::"+rate_unit_base_value+"::qty_unit_base_value::"+qty_unit_base_value);	
       				
       				 if(document.forms[0].strSalePriceUnitId.value!='0' && document.forms[0].strAcceptedQuantityUnitId.value!='0')  
       				 {  				
						 
						 var total = parseFloat("0.00");
						 var qty = parseFloat("0.00");
						 var rate = parseFloat("0.00");
						 
						  qty    = qtyObj;	
						  rate   = rateObj;
								 
					
						 if(isNaN(rate) || rate=="") rate = "0";
						 if(isNaN(qty)  || qty=="") qty = "0";
						 if(qty=='0')		
						 {
						  qty_unit_base_value  = '0';	
						  rate_unit_base_value = '0'; 
						  total = parseFloat(qty * qty_unit_base_value * rate);
						 }
						 else
						 {
						   total = parseFloat(qty * qty_unit_base_value * (rate /rate_unit_base_value));
						 } 
						 document.getElementById("strDrugTotCost").value = roundValue(total,2);
						//alert("IInd Case::::"+roundValue(total,2));
       				 }
       				// document.forms[0].strSalePrice.disabled = "true";
			 		 //document.forms[0].strSalePriceUnitId.disabled = "true";			       				 
			 		//NVL(HSTNUM_PURCHASE_RATE,''0'')/Mms_Mst.getUnitBaseValue(HSTNUM_RATE_UNITID,GNUM_HOSPITAL_CODE))*NVL( (HSTNUM_RECIEVED_QTY - HSTNUM_RETURNED_QTY)
			 		/**
			 		 * Total Cost Calculation End
			 		 */
}

     function calcIssueRateTwo()
	 {
	 	
	 	        /**
	 	         * Default Supplier Performance Detail Checked Value 
	 	         */
	 	         //HSTSTR_REPORT_NO||'^'||HSTDT_REPORT_DATE||'^'||HSTSTR_DRUG_CONDITION||'^'||
	 	         //HSTSTR_IS_QC_PRINTED||'^'||HSTSTR_IS_GENERIC||'^'||HSTSTR_IS_MRP_PRINTED
	 	         /*
	 	         if(document.forms[0].strSupplierPerformanceFlag.value>'0' ||document.forms[0].strSupplierPerformanceFlag.value=='1')
	 	         {
	 	         		 	         	
		 	         if(document.forms[0].strSupplierPerformanceInfo.value.split("^")[0]!='')
		 	         {
		 	         	document.forms[0].isTestReport[0].checked=true;
		 	         	document.forms[0].isTestReport[1].checked=false;
		 	         	document.forms[0].isTestReport[0].disabled = true;
		 	         	document.forms[0].isTestReport[1].disabled = true;
		 	         	 document.forms[0].strTestReportFlg.value='1';
		 	         	document.forms[0].strReportNumber.value = document.forms[0].strSupplierPerformanceInfo.value.split("^")[0];
		 	         	document.forms[0].strReportDate.value = document.forms[0].strSupplierPerformanceInfo.value.split("^")[1];
		 	         	document.forms[0].strReportNumber.disabled = true;
		 	         	document.forms[0].strReportDate.disabled = true;
		 	         }
		 	         else
		 	         {
		 	         	document.forms[0].isTestReport[1].checked=true;
		 	         	document.forms[0].isTestReport[0].checked=false;
		 	         	document.forms[0].isTestReport[0].disabled = true;
		 	         	document.forms[0].isTestReport[1].disabled = true;
		 	         	document.forms[0].strTestReportFlg.value='0';
		 	         	document.forms[0].strReportNumber.value = document.forms[0].strSupplierPerformanceInfo.value.split("^")[0];
		 	         	document.forms[0].strReportDate.value = document.forms[0].strSupplierPerformanceInfo.value.split("^")[1];
		 	         	document.forms[0].strReportNumber.disabled = true;
		 	         	document.forms[0].strReportDate.disabled = true;
		 	         }
		 	         if(document.forms[0].strSupplierPerformanceInfo.value.split("^")[2]!='1')
		 	         {
		 	            document.forms[0].isGoodCond[0].checked=false;
		 	            document.forms[0].isGoodCond[1].checked=true;
		 	            document.forms[0].strMedicienCondFlg.value='1';
		 	            document.forms[0].isGoodCond[0].disabled = true;
		 	            document.forms[0].isGoodCond[1].disabled = true;
		 	         }
		 	         else
		 	         {
		 	         	document.forms[0].isGoodCond[0].checked=true;
		 	            document.forms[0].isGoodCond[1].checked=false;
		 	            document.forms[0].strMedicienCondFlg.value='0';
		 	            document.forms[0].isGoodCond[0].disabled = true;
		 	            document.forms[0].isGoodCond[1].disabled = true;
		 	         	
		 	         }
		 	         if(document.forms[0].strSupplierPerformanceInfo.value.split("^")[2]!='1')
		 	         {
		 	            document.forms[0].isGoodCond[0].checked=false;
		 	            document.forms[0].isGoodCond[1].checked=true;
		 	            document.forms[0].strMedicienCondFlg.value='1';
		 	            document.forms[0].isGoodCond[0].disabled = true;
		 	            document.forms[0].isGoodCond[1].disabled = true;
		 	         }
		 	         else
		 	         {
		 	         	document.forms[0].isGoodCond[1].checked=false;
		 	            document.forms[0].isGoodCond[0].checked=true;
		 	            document.forms[0].strMedicienCondFlg.value='0';
		 	            document.forms[0].isGoodCond[0].disabled = true;
		 	            document.forms[0].isGoodCond[1].disabled = true;
		 	         }
		 	         if(document.forms[0].strSupplierPerformanceInfo.value.split("^")[3]!='1')
		 	         {
		 	            document.forms[0].isNotForSale[0].checked=false;
		 	            document.forms[0].isNotForSale[1].checked=true;
		 	            document.forms[0].strGovtSupplyFlg.value='1';
		 	            document.forms[0].isNotForSale[0].disabled = true;
		 	            document.forms[0].isNotForSale[1].disabled = true;
		 	         }
		 	         else
		 	         {
		 	         	document.forms[0].isNotForSale[1].checked=false;
		 	            document.forms[0].isNotForSale[0].checked=true;
		 	            document.forms[0].strGovtSupplyFlg.value='0';
		 	            document.forms[0].isNotForSale[0].disabled = true;
		 	            document.forms[0].isNotForSale[1].disabled = true;
		 	         }
		 	         if(document.forms[0].strSupplierPerformanceInfo.value.split("^")[4]!='1')
		 	         {
		 	            document.forms[0].isGenricName[0].checked=false;
		 	            document.forms[0].isGenricName[1].checked=true;
		 	            document.forms[0].strMedicineTypeFlg.value='1';
		 	            document.forms[0].isGenricName[0].disabled = true;
		 	            document.forms[0].isGenricName[1].disabled = true;
		 	         }
		 	         else
		 	         {
		 	         	document.forms[0].isGenricName[1].checked=false;
		 	            document.forms[0].isGenricName[0].checked=true;
		 	            document.forms[0].strMedicineTypeFlg.value='0';
		 	            document.forms[0].isGenricName[0].disabled = true;
		 	            document.forms[0].isGenricName[1].disabled = true;
		 	         	
		 	         }
		 	         if(document.forms[0].strSupplierPerformanceInfo.value.split("^")[5]!='1')
		 	         {
		 	            document.forms[0].isMRPPrint[0].checked=false;
		 	            document.forms[0].isMRPPrint[1].checked=true;
		 	            document.forms[0].strMrpPrintedFlg.value='1';
		 	            document.forms[0].isMRPPrint[0].disabled = true;
		 	            document.forms[0].isMRPPrint[1].disabled = true;
		 	         }
		 	         else
		 	         {
		 	         	document.forms[0].isMRPPrint[1].checked=false;
		 	            document.forms[0].isMRPPrint[0].checked=true;
		 	            document.forms[0].strMrpPrintedFlg.value='0';
		 	            document.forms[0].isMRPPrint[0].disabled = true;
		 	            document.forms[0].isMRPPrint[1].disabled = true;
		 	         }
		 	         
		 	         
	 	         }
	 	         else
	 	         {   */ 
	 	         
	 	         document.getElementsByName("strSalePriceUnitId")[0].disabled = false; 
		 	         document.forms[0].isTestReport[0].checked=true;
		 	         document.forms[0].isGoodCond[0].checked=true;
		 	         document.forms[0].isNotForSale[0].checked=true;
		 	         document.forms[0].isGenricName[0].checked=true;
		 	         document.forms[0].isMRPPrint[0].checked=true;
		 	         
		 	         document.forms[0].strTestReportFlg.value='1';
					 document.forms[0].strMedicienCondFlg.value='1';
					 document.forms[0].strGovtSupplyFlg.value='1';
					 document.forms[0].strMedicineTypeFlg.value='1';
					 document.forms[0].strMrpPrintedFlg.value='1';
	 	         //}
	 	        //document.forms[0].strSalePriceUnitId.disabled = true;
 	
			 	var         isseRate = parseFloat("0");
			 	var     purchaseRate = document.forms[0].strDummySalePrice.value;
			 	var confgIsseRateVal = document.forms[0].strConfigIssueRate.value;
			 	
			 				
			 	
			 	if(document.forms[0].strIssueRateConfigFlg.value=='1')
			 	{
			 		var result1 = (Math.abs(purchaseRate) * confgIsseRateVal) / Math.pow(10, 2);
			 	    var cost = roundValue(result1,2);
			 	    document.forms[0].strSalePrice.value = cost;
			 	    //alert("purchaseRate::::"+purchaseRate+"::confgIsseRateVal::"+confgIsseRateVal+"::Tota Cost:::"+cost);
			 	   // document.forms[0].strSalePrice.disabled = "true";  commented by shalini as per priyesh sir's comments
			 	    document.forms[0].strSalePriceUnitId.disabled = false; 
			 		
			 		//document.forms[0].strSalePrice.disabled = "true";
			 		//document.forms[0].strSalePrice.value = purchaseRate.split("/")[0];
			 	}
			 	else
			 	{
			 		document.forms[0].strSalePrice.value = purchaseRate.split("/")[0];
			 		//document.forms[0].strSalePrice.disabled = "false";
			 	
			 	}
	 	
	 	
	 }
	 



	/**
	 * showItemDtls
	 * @param {Object} parentObj 
	 * @param {String} manfName
	 * @param {String} rateDtls
	 * @param {String} itemName
	 */
	 function showItemDtls(parentObj,manfName,rateDtls , itemName) {
	 	
	 	
	 	document.getElementById("itemDtlsTitleDivId").innerHTML = itemName+" - Item Details";
	 	document.getElementById("itemManufDivId").innerHTML = manfName;
	 	document.getElementById("itemRateDivId").innerHTML = rateDtls;
	 	
	 	display_popup_menu(parentObj, "itemDtlsDivId" , "","");
	 	
	 }
	 
	 
	 /**
	 * showBalQtyDtls
	 * @param {Object} parentObj 
	 * @param {String} ordQty
	 * @param {String} recQty
	 * @param {String} itemName
	 */
	 function showBalQtyDtls(parentObj,ordQty,recQty , itemName) {
	 	
	 	document.getElementById("balQtyTitleDivId").innerHTML = itemName+" - Item Balance Qty.";
	 	document.getElementById("ordQtyDivId").innerHTML = ordQty;
	 	document.getElementById("recQtyDivId").innerHTML = recQty;
	 	
	 	display_popup_menu(parentObj, "balQtyDtlsDivId" , "","");
	 	
	 }
	 
	 
	 
	 function calcIssueRate()
	 {
		if(document.forms[0].strIssueRateConfigFlg.value=='1')
		{
		 	
				 	var         isseRate = parseFloat("0");
				 	var     purchaseRate = document.forms[0].strRate.value;
				 	var confgIsseRateVal = document.forms[0].strConfigIssueRate.value;
				 	
				 	
				 	var result1 = (Math.abs(purchaseRate) * confgIsseRateVal) / Math.pow(10, 2);
				 	
				 	var cost = roundValue(result1,2);
				 	document.forms[0].strSalePrice.value = cost;
				 	//document.forms[0].strSalePrice.disabled = true;
				 	CalculateDrugCost();
				 	
		 	
		}
		 	
	 }

function pageResetMethod()
{
	
	document.forms[0].reset();
	//document.forms[0].strSalePrice.disabled = true;
	document.forms[0].strSalePrice.value = document.forms[0].strDummySalePrice.value;
	
	
}
function pageReset()
{
	document.forms[0].reset();
	
	document.getElementById("recpCalender").style.display="block";
	document.getElementById("supplierRecDate").style.display="none";
		  
	document.getElementById("calDate").style.display="block";
	document.getElementById("dateVal").style.display="none";
	
	objVal = document.getElementById("scheduleDtlsDivId");
	objVal.innerHTML = "";
	document.forms[0].strSupplierReceiptNo.disabled=false;
	 document.getElementById("goImageDiv").style.display="block"; 
	
}	 
/**

	JS Function used in committetype
**/
function getMemberDtl(mode)
{
	var mode=mode
	document.getElementsByName("strReportNumber")[0].focus();
	var url="ChallanProcessTransCNT.cnt?hmode="+mode+"&committeType="+document.forms[0].strCommitteeType[document.forms[0].strCommitteeType.selectedIndex].value+"&itemCategNo="+document.forms[0].strItemCategoryId.value;
	ajaxFunction(url,"2");
	document.getElementsByName("strReportNumber")[0].focus();
}
	 
	 
	 /**
	  * getScheduleDtls
	  * @param {object} cmbObj 
	  */
	  function getScheduleDtls(cmbObj) 
	  {
	  	
	  	
	  	var mode = "GETSCHEDULEDTLS";

		var url = "ChallanProcessTransCNT.cnt?hmode=" + mode + "&strScheduleDtls="+cmbObj.value+"&strPoNo="+
								document.forms[0].strPoNo.value+"&strPoStoreId="+document.forms[0].strPoStoreId.value;
		ajaxFunction(url, "1");
	  	
	  }
	function getScheduleDetails() 
	{			
		var hisValidator = new HISValidator("challanProcessBean");
		hisValidator.addValidation("strReceiveDate", "req", "Received Date a Mandatory Field");
		hisValidator.addValidation("strSupplierReceiptNo", "req", "Challan/Invoice No. a Mandatory Field");
		//alert("Ct Date::"+document.forms[0].strCtDate.value+"::Rece::"+document.forms[0].strSupplierReceiptDate.value);
	    hisValidator.addValidation("strSupplierReceiptDate", "req", "Challan/Invoice Date a Mandatory Field");
	    hisValidator.addValidation("strSupplierReceiptDate","dtltet="+document.forms[0].strCtDate.value,"Please Select Challan/Invoice Date Less Than or Equal To Current Date");
	  	hisValidator.addValidation("strReceiveDate", "dtgtet="+document.forms[0].strSupplierReceiptDate.value,"Received Date should be Greater Than or Equal Challan/Invoice Date");
	  	hisValidator.addValidation("strReceiveDate", "dtltet="+document.forms[0].strCtDate.value,"Received Date should be Less Than or Equal To Current Date");
		var retVal = hisValidator.validate();
		hisValidator.clearAllValidations();		
		if (retVal) 
		{
			//if(document.forms[0].strChallanCount.value=='0')
			//{
			 
			 
			 var mode = "SCHEDULEDTL";			 
			 var url = "ChallanProcessTransCNT.cnt?hmode="+mode+"&strPoNo="+document.forms[0].strPoNo.value+"&strPoStoreId="+document.forms[0].strPoStoreId.value+"&strReceiveDate="+document.forms[0].strReceiveDate.value+"&strStoreId="+document.forms[0].strStoreId.value;
			 //alert(url);
			 ajaxFunction(url, "4");
			//}
		} 
		else 
		{
			return false;
		}
	}
	  
	  
	  function getAjaxResponse(res, mode) 
	  {
			  	var err = document.getElementById("errMsg");
				var temp = res.split("####");
				if(temp[0] == "ERROR")
				{
					err.innerHTML = temp[1];
					return;
				}
				var objVal;
				if (mode == "1") 
				{
			        
					objVal = document.getElementById("scheduleDtlsDivId");
					objVal.innerHTML = res;
				}
					
				if(mode=="2")
				{				
				
					document.getElementById("memberDtlInner").innerHTML=res;	
					document.getElementsByName("strReportNumber")[0].focus();
					document.getElementsByName("strReportNumber")[0].focus();
					document.getElementById("strReportNumber").focus();	
				//	document.getElementsByName("strMemberRecommendation")[0].focus();
				}
		
				if(mode=="3")
				{				
				
					document.getElementById("balQtyDtlsDivId").innerHTML=res;	
					display_popup_menu(gblParentObj, "balQtyDtlsDivId" , "","");
										
				}
				if (mode == "4") 
				{
			        
					objVal = document.getElementById("scheduleDtlsDivId");
					objVal.innerHTML = res;
					document.getElementById("scheduleDtlsDivId").style.display="block"; 	
					
					document.getElementById("calDate").style.display="none";
		            document.getElementById("dateVal").style.display="block";
		            document.getElementById("dateVal").innerHTML=document.getElementsByName("strReceiveDate")[0].value;
					
					document.getElementById("recpCalender").style.display="none";
		            document.getElementById("supplierRecDate").style.display="block";
		            document.getElementById("supplierRecDate").innerHTML=document.getElementsByName("strSupplierReceiptDate")[0].value;
					document.forms[0].strSupplierReceiptNo.disabled=true;
					document.getElementById("goImageDiv").style.display="none"; 
					
					
					
					document.getElementById("ID1").innerHTML = "Scheduler Date";
					document.getElementById("ID2").innerHTML = document.getElementsByName("strScheduleDate")[0].value;
					document.getElementById("ID3").innerHTML = "Delivery Date";
					document.getElementById("ID4").innerHTML = document.getElementsByName("strDeliveryDate")[0].value;
					
					
					document.getElementsByName("strShelfLife")[0].value=document.getElementsByName("strShelfLifeFromPO")[0].value;
					
					
					
					document.getElementById("remarksid").style="display : block";
					
					

					
					
				}
				if(mode=="5")
				{					
					document.getElementById("receivedItemDtlsDivId").innerHTML=res;
					document.getElementById("verifiedItemDtlsDivId").style.display = "none";						
				}
				if(mode=="6")
				{					
					document.getElementById("verifiedItemDtlsDivId").innerHTML=res;	
					document.getElementById("verifiedItemDtlsDivId").style.display = "block";					
				}
	  }
	  
	  
	 
	 /**
	  * validation 
	  */
	  function validationRec() 
	  {
	  	
		  
		var errContent = document.getElementById("errMsg").innerHTML ;
	  	if(errContent.length > 0)
	  	{
	  		alert("Process Cannot be Saved due to '"+errContent+"'");
	  		return false;
	  	}
	  	var hisValidator = new HISValidator("challanProcessBean");
	  	if(document.forms[0].strPoTypeId.value == '24')
	  	{
	  		hisValidator.addValidation("strAwbNo", "req", "AWB No. is a Mandatory Field");
	  		hisValidator.addValidation("strBeNo", "req", "BE No. is a Mandatory Field");
	  		hisValidator.addValidation("strBeDate", "req", "BE Date is a Mandatory Field");
	  	}
	  	hisValidator.addValidation("strReceiveDate", "req", "Received Date a Mandatory Field");
	  	hisValidator.addValidation("strSupplierReceiptNo", "req", "Challan/Invoice No. a Mandatory Field");	  	
	  	hisValidator.addValidation("strSupplierReceiptDate", "req", "Challan/Invoice Date a Mandatory Field");
	  	if(document.getElementsByName("strSupplierReceiptDate")[0].value!="")
	  	{
	  		hisValidator.addValidation("strSupplierReceiptDate","dtltet="+document.forms[0].strCtDate.value,"Please Select Challan/Invoice Date Less Than or Equal To Current Date");
	  		hisValidator.addValidation("strReceiveDate", "dtgtet="+document.forms[0].strSupplierReceiptDate.value,"Received Date should be Greater Than or Equal Challan/Invoice Date");
	  		
	  	}
	  
	    var poDate = document.forms[0].strPourchaseOrderDate.value;
	  	hisValidator.addValidation("strSupplierReceiptDate","dtgtet="+poDate+"", "Please Select Challan/Invoice Date Greater Than or Equal To PO Date"+poDate);
	  	
	  	hisValidator.addValidation("strNoOfPackets", "req", "No. of Packets is a Mandatory Field");
	  	hisValidator.addValidation("strDeliveryMode", "dontselect=0", "Please Select a Delivery Mode");
	  	
	  	
	  	 var checkLength=document.getElementsByName("strReceivedQty").length;
		  for(var i=1;i<=checkLength;i++)
		  {
		     if(document.getElementsByName("strMultiRowManufacterDate"+i+"")[0].value == "")
		     {
		         alert("Please Enter Manufactured Date");
		         return false;
		     }
		     
		     if(document.getElementsByName("strMultiRowExpireDate"+i+"")[0].value == "")
		     {
		    	 alert("Please Enter Expiry Date");
		    	 return false;
		     }
		  }
	
		
		  
		  
	  	
      	
	  	//Added By vikrant Date 24-July-2015 for date checking
		  
	   var checkLength=document.getElementsByName("strReceivedQty").length;
	   for(var i=1;i<=checkLength;i++)
	   {  
	  	
	  	var strMfgDate           = document.getElementsByName("strMultiRowManufacterDate"+i+"")[0];
    	var strExpiryDate        = document.getElementsByName("strMultiRowExpireDate"+i+"")[0]; 

	  	
	  	if(strExpiryDate.value.length > 0) 
		{
			var nFlag = compareDate(document.getElementsByName("strCtDate")[0].value,strExpiryDate.value); 				
			if(nFlag.mode == 2 || nFlag.mode == 1)
			{
				alert("Expiry Date must be greater then Current Date.");
				return false;
			}
		}
		
		if(strMfgDate.value.length > 0) 
		{
			var nFlag = compareDate(document.getElementsByName("strCtDate")[0].value,strMfgDate.value); 				
			if(nFlag.mode < 2)
			{
				alert("Manufacturer Date must be less then Current Date.");
				return false;
			}
		}
	   }
	   
	   
		
  	  		  	
	  	hisValidator.addValidation("strReceivedQty" , "req" , "Received Quantity is a Mandatory Field");
	  	hisValidator.addValidation("strReceivedUnitId", "dontselect=0", "Please Select a Unit Name ");

 		hisValidator.addValidation("strDeliveryMode","dontselect=0","Please Select a Value from Delivery Mode Combo" );
		hisValidator.addValidation("strReceivedBy", "req", "Name of the Receiver is a Mandatory Field");
		
		hisValidator.addValidation("strReceivedComboBy","dontselect=0","Please Select a Value from Received By Combo" );
		hisValidator.addValidation("strDeliveryModeText", "req", "Deliver Mode Name is a Mandatory Field");
	
		hisValidator.addValidation("strRemarks", "maxlen=100", "Remarks cannot contains more than 100 Characters");
		hisValidator.addValidation("strPackageWeight", "numltet=9999.99", "Weight cannot be more than 9999.99 kg");
		
		if(document.getElementsByName("strItemCategoryId")[0].value == 10)
		hisValidator.addValidation("strShelfLife", "req", "Shelf Life Is Mandatory Field");
		
		
		
		
		
		//Shelf Life Validation Related Date 23-July-2015


	    var shelfLife=document.getElementsByName("strShelfLife")[0].value;
	    
	    
	    if(parseInt(shelfLife) >0)
 		{
	 		  var  dateDiffOne,perShelfLife,dateDiffTwo;  
	 		  if(shelfLife!='0')
	 		  {	    
	 		    perShelfLife = roundValue((Math.round(shelfLife*75)/100),2);
	 		  }
	 		  else
	 		  {
	 		  	perShelfLife = '0';
	 		  } 	
	 		  
	 		  var checkLength=document.getElementsByName("strReceivedQty").length;
	 		  
	 		  var j=0;	 
	 		  
	 		  for(var i=1;i<=checkLength;i++)
	 		  {
	 		
	 		  dateDiffOne = dateDifferenceInDays(document.getElementsByName("strMultiRowManufacterDate"+i+"")[j].value,document.getElementsByName("strMultiRowExpireDate"+i+"")[j].value)+1;
 		 	  dateDiffTwo = dateDifferenceInDays(document.getElementsByName("strReceiveDate")[0].value,document.getElementsByName("strMultiRowExpireDate"+i+"")[j].value)+1;
	 		  
 		 	  
 		 	  if(parseInt(dateDiffOne) < shelfLife)
		 	   {
		 		 	alert("The Difference between Manufacturing date and Expiry date [ "+dateDiffOne+" ] should be greater than Or Equal to shelf life [ "+shelfLife+" ]");
		 		 	return false;	 		 	
		 	   }
		 	  if(parseInt(dateDiffTwo) < Math.round(perShelfLife)  )
		 	   {
		 		 	alert("The Difference between Received date and Expiry date [ "+dateDiffTwo+" ] should be greater than Or Equal to 75 % of Shelf life [ "+Math.round(perShelfLife)+" ]");
		 		 	return false;	 		 	
		 	   }
	 		  
	 		  }
	 	 }	
 			
		  	
		  
		  	
	  	var retVal = hisValidator.validate();
	
		hisValidator.clearAllValidations();

		if (retVal) 
		{
			
			var count = 0;
		  	var len = document.getElementsByName("strReceivedQty").length;		  	
		  	for(var i = 0; i<len ; i++)
			{
				if(	parseInt(document.getElementsByName("strReceivedQty")[i].value)>0 )	
				count++;
			}
			
			
			if(count==0)
			{
				alert(" Received Qty for Atleast one of Item Name should be greater than 0");
				document.getElementsByName("strReceivedQty")[0].focus();
				return false;
			}
						
			           
			           var conf = confirm("You Are Going To Save Records");
					                  if(conf == true)
					                  {
					                       var conf1 = confirm("Are you sure !!!");
					                       if(conf1 == true)
					                       {
					                       	  document.forms[0].strDeliveryModeText.disabled = false;
					                       	  document.forms[0].strSupplierReceiptNo.disabled = false;
					                       	  
					                          document.forms[0].hmode.value = "INSERT";
                                              document.forms[0].submit();
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
     	 
			
			
		} else {
			return false;
		}
	  	
	  } 
	  
	  
	  /**
	   * verifyValidation 
	   */
function verifyValidation() 
{
	   	
	   	document.forms[0].strVerifyFlag.value = 0;
	   //	document.forms[0].strSalePrice.disabled = false;
	   	
	   	var totReceivedQty = parseFloat(document.forms[0].strReceivedQuantity.value);
	   	var totVerifiedQty = parseFloat(document.forms[0].strVerifiedQuantityInBaseVal.value);
	  // 	alert("Received Qty"+totReceivedQty);
	  // 	alert("Verified Qty"+totVerifiedQty);
		
	   	if(totReceivedQty!=totVerifiedQty || totVerifiedQty>totReceivedQty)
	   	{
	   		var hisValidator = new HISValidator("challanProcessBean");
		   		   		
		   	if (document.forms[0].isBatchReq != null && document.forms[0].isBatchReq.value == '1') 
		   	{
			
				hisValidator.addValidation("strBatchNo", "req","Batch No. is a Mandatory Field");
			}
			
			hisValidator.addValidation("strManufactureDate", "req","Manufacture Date is a Mandatory Field");
			hisValidator.addValidation("strManufactureDate", "dtltet="+document.forms[0].strCtDate.value,"Manufacture Date should be Less than or Equal to Current Date"); 		
			
	
			if (document.forms[0].strExpiryDate.value != "" ) 
			{
				//	hisValidator.addValidation("strExpiryDate", "req","Expiry Date is a Mandatory Field");
					hisValidator.addValidation("strExpiryDate", "dtgtet="+document.forms[0].strCtDate.value,"Expiry Date should be Greater than or Equal to Current Date"); 		
					hisValidator.addValidation("strExpiryDate", "dtgt="+document.forms[0].strManufactureDate.value,"Expiry Date should be Greater than Manufacture Date"); 		
			}
			
			
			if(document.forms[0].strItemCategoryId.value == "10" && document.forms[0].strExpiryDate.value == "" )
				{
					alert("Expiry Date is a Mandatory Field");
					return false;
				}
		    
		   	hisValidator.addValidation("strAcceptedQuantity", "req", "Accepted Quantity is a Mandatory Field");
		   	hisValidator.addValidation("strAcceptedQuantityUnitId", "dontselect=0", "Please Select a Accepted Quantity Unit");
		   	
		   	if(parseInt(document.forms[0].strAcceptedQuantity.value)==0 )
		   	{
		   		alert("Accepted Quantity should be greater than 0	");
		   		document.forms[0].strAcceptedQuantity.focus();
		   		return false;
		   		
		   	}
		   	
		   	var rejQty = document.forms[0].strRejectedQuantity.value;
		   	
		   	if(rejQty.length == 0)rejQty = "0";
		   	
		   	var rejQtyVal = parseFloat(rejQty);
		   	
		   	if(rejQtyVal > 0)
		   	{
		   		
		   		hisValidator.addValidation("strRejectedQuantityUnitId", "dontselect=0", "Please Select a Rejected Quantity Unit");
		   	}
		   	
		   	var brkQty = document.forms[0].strBreakageQuantity.value;
		   	
		   	if(brkQty.length == 0)brkQty = "0";
		   	
		   	var brkQtyVal = parseFloat(brkQty);
		   	
		   	if(brkQtyVal > 0)
		   	{
		   		
		   		hisValidator.addValidation("strBreakageQuantityUnitId", "dontselect=0", "Please Select a Breakage Quantity Unit");
		   	}
		   	
		   	
		   	var excQty = document.forms[0].strExcessQty.value;
		   	
		   	if(excQty.length == 0)excQty = "0";
		   	
		   	var excQtyVal = parseFloat(excQty);
		   	
		   	if(excQtyVal > 0)
		   	{
		   		
		   		hisValidator.addValidation("strExcessQtyUnitId", "dontselect=0", "Please Select a Excess Quantity Unit");
		   	}
		   	
		   	var excessUnit = document.forms[0].strExcessQtyUnitId.value;
		   	var excessUnitBase = parseFloat("0"); 
		   	
		   	if(excessUnit.length > 1)
		   	{
		   		excessUnitBase = parseFloat(excessUnit.split('^')[1]);
		   	}
		   	excQtyVal = excQtyVal * excessUnitBase;
		   	
		   	var rejUnit = document.forms[0].strRejectedQuantityUnitId.value;
		   	var rejUnitBase = parseFloat("0"); 
		   	
		   	if(rejUnit.length > 1)
		   	{
		   		
		   		rejUnitBase = parseFloat(rejUnit.split('^')[1]);
		   		
		   	}
		   	
		   	var brkUnit = document.forms[0].strBreakageQuantityUnitId.value;
		   	var brkUnitBase = parseFloat("0"); 
		   	
		   	if(brkUnit.length > 1)
		   	{
		   		
		   		brkUnitBase = parseFloat(brkUnit.split('^')[1]);
		   		
		   	}
		   	
		   	var accUnit = document.forms[0].strAcceptedQuantityUnitId.value;
		   	var accUnitBase = parseFloat("0"); 
		   	
		   	if(accUnit.length > 1)
		   	{
		   		
		   		accUnitBase = parseFloat(accUnit.split('^')[1]);
		   		
		   	}
		   	
		   	
		   	var verfVal = parseFloat(document.forms[0].strVerifiedQuantityInBaseVal.value);
		   	var rejVal = rejQtyVal * rejUnitBase;
		   	var brkVal = brkQtyVal * brkUnitBase;
		   	var accVal = parseFloat(document.forms[0].strAcceptedQuantity.value) * accUnitBase;
		   	
		   	var totVerfVal = verfVal + accVal + rejVal + brkVal;
		    var balQtyVal = parseFloat(document.forms[0].strReceivedQuantity.value) * parseFloat(document.forms[0].strReceivedQuantityUnitBaseValue.value);
		    
		   	if((accVal + verfVal) < balQtyVal)
		   	{
		   		if(excQtyVal > 0)
		   		{
		   			alert("Excess Qty can not be greater than zero if total accepted qty is less than balance qty !!");
		   			return false;
		   		}
		   	}
		   	
		   	if((accVal + verfVal) == balQtyVal)
		   	{
		   		if(rejVal > 0)
		   		{
		   			alert("Rejected Qty can not be greater than zero if total accepted qty is equal to balance qty, enter in excess qty !!");
		   			return false;
		   		}
		   		
		   		if(brkVal > 0)
		   		{
		   			alert("Breakage Qty can not be greater than zero if total accepted qty is equal to balance qty, enter in excess qty !!");
		   			return false;
		   		}
		   	}
		   		
		   	if(totVerfVal > balQtyVal)
		   	{
		   		
		   		alert("Sum of Verified, Accepted, Rejected and Breakage Quantity should not be Greater than Receive Quantity "+balQtyVal+" No.");
		   		return false;
		   	}
		   
		   	hisValidator.addValidation("strSalePrice", "req", "Sales Price is a Mandatory Field");
		   	hisValidator.addValidation("strSalePriceUnitId", "dontselect=0", "Please Select a Sales Price Unit");
		   		
		   	if(document.getElementsByName("strWarrantyDate").length > 0)
		   	{
		   		
		   		hisValidator.addValidation("strWarrantyDate", "req","Warranty Date is a Mandatory Field");
				
				hisValidator.addValidation("strWarantyManufacturer", "dontselect=0","Please Select a Manufacture");
				
				hisValidator.addValidation("strWarrantyUpTo", "req","Warranty Upto is a Mandatory Field");
				hisValidator.addValidation("strWarrantyUpToUnit", "dontselect=0","Please Select a Unit");
				
				hisValidator.addValidation("strWarrantyRemarks", "maxlen=100","Remarks cannot be greater than 100 Characters");
					   		
		   	}	
		   		
		   	if(document.getElementsByName("strInstallStartDate").length > 0)
		   	{
		   		
			   	hisValidator.addValidation("strInstallStartDate", "req","Installation Start Date is a Mandatory Field");
				hisValidator.addValidation("strInstallEndDate", "req","Installation End Date is a Mandatory Field");
				hisValidator.addValidation("strInstallStatus", "dontselect=0","Please Select an Installation Status");
				
				hisValidator.addValidation("strInstallBy", "req","Install By is a Mandatory Field");
				hisValidator.addValidation("strInstallerContactNo", "req","Installer Contact No. is a Mandatory Field");
				hisValidator.addValidation("strInstallEndDate", "req","Installation End Date is a Mandatory Field");
				
		   	}
		   	//Validate Supplier Performance Details
		   
			   	hisValidator.addValidation("strReportNumber", "req","Report Number is a Mandatory Field");
				hisValidator.addValidation("strReportDate", "req","Report Date is a Mandatory Field");
				hisValidator.addValidation("strReportDate", "dtltet="+document.forms[0].strCtDate.value,"Report Date should be Less than or Equal to Current Date"); 		
				
			
		   
		   			   	
   	        hisValidator.addValidation("strPageNumber","req", "Page No. of Stock Register where entry has been made is a Mandatory Field" );
		   			   			   		
		   	hisValidator.addValidation("strCommitteeType", "dontselect=0", "Please Select a Committee Type");	
		   //	hisValidator.addValidation("strRemarks", "req", "Remarks is a Mandatory Field");
			hisValidator.addValidation("strRemarks", "maxlen=100", "Remarks cannot contains more than 100 Characters");
	
		  
		   	var retVal = hisValidator.validate();
		
			hisValidator.clearAllValidations();
	
			if (retVal) 
			{
				 	if(document.getElementsByName("strPageNumber")[0].value==0)
			           {
			           		alert("Please Enter Page No. greater than 0 ");
			           		document.getElementsByName("strPageNumber")[0].focus();
			           		return false;
			           		
			           } 
				
				if(totVerfVal != balQtyVal)
				{
					
					var flg = confirm("Whether All the Batch of the Selected Item(s) have been Received");
					
						if(flg) document.forms[0].strVerifyFlag.value = 1;
									
				}
				else
				{
					
					document.forms[0].strVerifyFlag.value = 1;
				}
				
				
				if(	confirm("You Are Going To Save Challan Details. The Item details are as follows" +
					    "\n\n Item Name :  " + document.forms[0].strItemBrandName.value 
					   +"\n Batch No : "         + document.forms[0].strBatchNo.value 
					   +"\nAccepted Quantity : " + document.forms[0].strAcceptedQuantity.value
					  )
				)
			
				{
					if(confirm(" Do you want to Save ?"))
					{
					
							//document.forms[0].strSalePrice.disabled = false;
							document.forms[0].strSalePriceUnitId.disabled = false;
							document.forms[0].hmode.value = "INSERTVERIFY";
							document.forms[0].submit();
					}	
					
				}
					
				
				
			
			} else {
				return false;
			}
	   }
	   else
	   {
	   	  alert("No Item Remaining to Verify!!!");
	   	  return false;
	   }
	   	
	   }
	  
	  
	  function checkQtyDtls(index, qtyName, unitName) 
	  {

		var unitObj = document.getElementById(unitName+""+index);
		var qtyObj = document.getElementById(qtyName+""+index);

		
			var qtyDeceimal = qtyObj.value;

			var unitVal = unitObj.value.split('^')[2];

			if (qtyDeceimal.indexOf('.') > -1 && unitVal == '0') 
			{

				alert("Qty must be an Integer ");
				qtyObj.value = '0';
								
				return false;
			}
			//if(qtyObj.value==0)
			//{
			//	alert("Qty must be greater than Zero ");
				
			//	return false;
			//}

		
	var itemDtlsObj =  document.getElementById("strItemDtls"+index);

	var balQty = itemDtlsObj.value.split('^')[2];
	var balQtyBaseUnitVal = itemDtlsObj.value.split('^')[10];

	var balQtyVal = parseFloat(balQty) * parseFloat(balQtyBaseUnitVal);


	var qty = "0";
	var unitBase = "0";

	if(qtyObj.value.length > 0) qty = qtyObj.value;

	if(unitObj.value.length > 1) unitBase = unitObj.value.split('^')[1];
	
		
	var reqQtyVal = parseFloat(qty) * parseFloat(unitBase);
	
	
	
	if(reqQtyVal > balQtyVal){
		
		alert("Required Quantity Cannot be Greater than Balance Quantity");
		qtyObj.value = 0;
		unitObj.value = 0;
		return false;
	}

	return true;
}
	  
	  
 function cancelPage(mode) {
	 //alert("cancel");
	document.forms[0].hmode.value = mode;
	document.forms[0].submit();
}


function ftnTickFive(mode)
{
  if(mode=='1')
  {
  	document.forms[0].strMrpPrintedFlg.value='1';
  	document.forms[0].isMRPPrint[0].checked=true;
  	document.forms[0].isMRPPrint[1].checked=false;
  }
  else
  {
  	document.forms[0].strMrpPrintedFlg.value='0';
  	document.forms[0].isMRPPrint[1].checked=true;
  	document.forms[0].isMRPPrint[0].checked=false;
  }
	
} 


function ftnTickFour(mode)
{
  if(mode=='1')
  {
  	document.forms[0].strMedicineTypeFlg.value='1';
  	document.forms[0].isGenricName[0].checked=true;
  	document.forms[0].isGenricName[1].checked=false;
  }
  else
  {
  	document.forms[0].strMedicineTypeFlg.value='0';
  	document.forms[0].isGenricName[1].checked=true;
  	document.forms[0].isGenricName[0].checked=false;
  }
	
} 


function ftnTickThree(mode)
{
  if(mode=='1')
  {
  	document.forms[0].strGovtSupplyFlg.value='1';
  	document.forms[0].isNotForSale[0].checked=true;
  	document.forms[0].isNotForSale[1].checked=false;
  }
  else
  {
  	document.forms[0].strGovtSupplyFlg.value='0';
  	document.forms[0].isNotForSale[1].checked=true;
  	document.forms[0].isNotForSale[0].checked=false;
  }
	
} 


function ftnTickTwo(mode)
{
  if(mode=='1')
  {
  	document.forms[0].strMedicienCondFlg.value='1';
  	document.forms[0].isGoodCond[0].checked=true;
  	document.forms[0].isGoodCond[1].checked=false;
  }
  else
  {
  	document.forms[0].strMedicienCondFlg.value='0';
  	document.forms[0].isGoodCond[1].checked=true;
  	document.forms[0].isGoodCond[0].checked=false;
  }
	
} 

function ftnTickOne(mode)
{
  if(mode=='1')
  {
  	document.getElementById("showReportId").style.display = "block";
  	document.forms[0].strTestReportFlg.value='1';
  	document.forms[0].isTestReport[0].checked=true;
  	document.forms[0].isTestReport[1].checked=false;
  }
  else
  {
  	document.getElementById("showReportId").style.display = "none";
  	document.forms[0].strTestReportFlg.value='0';
  	document.forms[0].isTestReport[1].checked=true;
  	document.forms[0].isTestReport[0].checked=false;
  }
	
} 

	  

	 /**
	  * showView
	  * @param {String} divId 
	  */
	  function showView(divId) 
	  {
	  	
	  	document.getElementById(divId+"PlusId").style.display = "none";
	  	document.getElementById(divId+"MinusId").style.display = "block";
	  	
	  	document.getElementById(divId).style.display = "block";
	  	
	  }
	 	 
	 	 
	 /**
	  * hideView
	  * @param {String} divId 
	  */
	  function hideView(divId) {
	  	
	  	document.getElementById(divId+"PlusId").style.display = "block";
	  	document.getElementById(divId+"MinusId").style.display = "none";
	  	
	  	document.getElementById(divId).style.display = "none";
	  	
	  }	 
	 	 

	/**
	 * addFreeItems
	  
	 */
	 function addFreeItems() {
	 	
	 		var mode 		= "1";
	 		var isBatchReq  = document.forms[0].isBatchReq.value;
	 		var isExpDtReq  = document.forms[0].isExpirtReq.value;
	 	
	 	getItemOtherDetailsView(mode, isBatchReq , isExpDtReq , '1' , "challanProcessBean");
	 	
	 }
	


	/**
	 * addFreeItems
	  
	 */
	 function addPartItems() {
	 	
	 		var mode 		= "2";
	 		var isBatchReq  = document.forms[0].isBatchReq.value;
	 		var isExpDtReq  = document.forms[0].isExpirtReq.value;
	 	
	 	getItemOtherDetailsView(mode, isBatchReq , isExpDtReq , '2' , "challanProcessBean");
	 	
	 }
	
	
	/**
	 * checkForPopup
	 * @param {Object} parentObject 
	 * @param {String} catNo
	 */
	 function checkForPopup(parentObject , catNo , mode) {
	 	
	 	if(document.getElementById("itemParameterDtlDivId").innerHTML.length == 0){
	 		
	 		if(mode == '1'){
	 			
	 			showPopup(parentObject , mode , catNo , '');
	 			
	 		}else if (mode == '2'){
	 		
	 		var itemVal = document.forms[0].strGenericItemId.value;
	 		
	 		showPopup(parentObject , mode , catNo , itemVal[2]);
	 			
	 		}
	 			 		
	 		
	 		
	 	} else{
	 		
	 		popup('popUpDivId','100','250');
	 		
	 	}
	 	
	 	
	 }
		  
	  
	  
	  
function openDivPopu()
{
		if(document.getElementsByName("strCommitteeType")[0].value=="0")
   	 	{
   	 		alert("Please Select Committee Type");
   	 		document.getElementsByName("strReportNumber")[0].focus();
   	 	}
 	 	else
 	 	{
  		     document.getElementsByName("strReportNumber")[0].focus();
  			 popup('memberDtl' , '130','250');
  			 
  		}
}
function closePopUpDiv()
{
	hide_popup('memberDtl');
}
function clearData()
{
	var size=document.getElementsByName("strMemberRecommendation").length;
	if(size>1){
		for(var i=0;i<size;i++){
			document.getElementsByName("strMemberRecommendation")[i].value="";
		}
	}
	else{
		document.getElementsByName("strMemberRecommendation")[0].value="";
	}

}
	  
	  
	  
	/**
	 * getVerifiedQtyDtls
	  * @param {Object} parentObj 
	 */
	 function getVerifiedQtyDtls(parentObj) {
	 	
	 
	 	document.getElementById("verAccQtyDivId").innerHTML =  document.forms[0].strPreviousAcceptedQty.value + " + " +
	 															document.forms[0].strPreviousRejectedQty.value + " + " +
	 															document.forms[0].strPreviousBreakageQty.value ;
	 
	 	
	 	display_popup_menu(parentObj, "verifiedQtyDtlsDivId" , "250","");
	 	
	 }   
	  
	  var gblParentObj = "";
	 /**
	  * getBalanceQtyDtls
	  * @param {Object} parentObj 
	  */
	  function getBalanceQtyDtls(parentObj) {
	  	
	  	gblParentObj = parentObj;
	  	
	  	if(document.getElementById("balQtyDtlsDivId").innerHTML.length > 0){
	  		
	  	display_popup_menu(parentObj, "balQtyDtlsDivId" , "","");
	  		
	  	}else{
		  	var mode = "BALQTYDTLS";
	
			var url = "ChallanProcessTransCNT.cnt?hmode=" + mode + "&strPoNo="+ document.forms[0].strPoNo.value + 
																   "&strPoStoreId="+ document.forms[0].strPoStoreId.value +
																   "&strItemId="+ document.forms[0].strGenericItemId.value +
																   "&strItemBrandId="+ document.forms[0].strItemBrandId.value +
																   "&strScheduleNo="+ document.forms[0].strScheduleNo.value ;
																   
													   
																   
			ajaxFunction(url, "3");
	  	}
	  }
	  
	  
	function selectRecord(obj,index)
{ 
   var passValue = document.getElementById("strHiddenChallanValue"+index).value;
   var chkObj = document.getElementById("strCheckOne"+index);
   
   if(chkObj.checked)
   {
   		chkObj.value = passValue;   		
   }
   else
   {
   		chkObj.value="";
   } 
 
}
	  
function fetchReceivedItemDetail(obj,index)
{		  	
  	var mode = "FETCHRECEIVEDITEMETAIL";
   
    var itemDetails = document.getElementById("strHiddenChallanValue"+index).value;
    var storeId = itemDetails.split('$')[0];   
    var poNo = itemDetails.split('$')[2];
    var challanNo = itemDetails.split('$')[3];
    
	var url = "ChallanProcessTransCNT.cnt?hmode=" + mode + "&strPoNo="+ poNo + "&strChallanNo="+ challanNo+ "&strStoreId="+ storeId ;
															   
	ajaxFunction(url, "5");	
}

function fetchVerifiedItemDetail(obj,index)
{		  	
  	var mode = "FETCHVERIFIEDITEMETAIL";
     //11-Dec-2012$06-Mar-2013$---$Ciprofloxacin I/V$Aspirin Soluble Tab$11 No.$11 No.$0 No.$0 No.$0 No.$Active$6301$6301$0$1$1$101012$ANALGESICS AND ANTIINFLAMMATORY DRUGS (NSAIDS)$0$null
     /*1. REC_DATE
      *2. CANCEL_DATE 
      *3. FREEZ_DATE
      *4. ITEM_NAME 
      *5. GEN_ITEM_NAME
      *6. REC_QTY 
      *7. BAL_QTY 
      *8. ACCQTY 
      *9. REJQTY 
      *10.BRKQTY 
      *11.STATUS 
      *12.NVL(HSTNUM_RECIEVED_QTY_UNITID,0) 
      *13.NVL(HSTNUM_BALQTY_UNITID,0) 
      *14.NVL(HSTNUM_EXCESSQTY_UNITID,0) 
      *15.RECQTY_BASE_VAL 
      *16.BAL_BASE_VAL 
      *17.HSTNUM_GROUP_ID 
      *18.GRP_NAME 
      *19.HSTNUM_SUBGROUP_ID 
      *20.SUBGRP_NAME 
      *21.HSTNUM_STORE_ID
      *22.HSTNUM_PO_STORE_ID
      *23.HSTNUM_PO_NO
      *24.HSTNUM_CHALLAN_NO
      *25.HSTNUM_ITEM_ID
      *26.HSTNUM_ITEMBRAND_ID*/
      
      //Challan No, Item Id, Item Brand Id
    var itemDetails = document.getElementById("strHiddenReceivedItems"+index).value;
    var storeId = itemDetails.split('$')[20];   
    var poNo = itemDetails.split('$')[22];
    var challanNo = itemDetails.split('$')[23];
    var itemId = itemDetails.split('$')[24];
    var itemBrandId = itemDetails.split('$')[25];
    
	var url = "ChallanProcessTransCNT.cnt?hmode=" + mode + "&strPoNo="+ poNo + "&strChallanNo="+ challanNo+ "&strStoreId="+ storeId+ "&strItemId="+ itemId+ "&strItemBrandId="+ itemBrandId ;
															   
	ajaxFunction(url, "6");	
}

function getPRINTAjaxResponse(res, mode) {

	//STOCKDTLSINIT
	if (mode == '1') {

		var itemStockObj = document.getElementById("printDtlsDivId");

		itemStockObj.innerHTML = res;

		popup('popUpDiv', '80', '60');

	}

}

function printPage(index)
{
	//alert("print");
	 var itemDetails = document.getElementById("strHiddenReceivedItems"+index).value;
	    var storeId = itemDetails.split('$')[20];   
	    var challanNo = itemDetails.split('$')[23];
	//alert(storeId);
	//alert(challanNo);
	var itemStockObj = document.getElementById("printDtlsDivId");
	if (itemStockObj.innerHTML == "") {

		var hmode = "PRINT";
		var url = "ChallanProcessTransCNT.cnt?hmode=" + hmode
				+ "&strStoreId=" + storeId + "&strChallanNo=" + challanNo;
		//	alert(url);	
		ajaxFunction2(url, "1", "getPRINTAjaxResponse");

	} else {
		// set for 1024 * 760 screen don't change this
		popup('popUpDiv', '80', '60');
	}



}

function printDataOne(mode) 
{
	newwin = window.open('', 'printwin',
			'left=100,top=100,width=700,height=700,scrollbars=yes');
	newwin.document.write('<HTML><HEAD>');
	newwin.document.write((document.getElementsByTagName("head")[0]).innerHTML);
	newwin.document.write('<style type="text/css"> .hidecontrol { display: none; } </style>\n')
	newwin.document.write('<script>\n');
	newwin.document.write('function chkstate(){ \n');
	// newwin.document.write('if(document.readystate=="complete" ||
	// document.readystate=="undefined"){\n');
	newwin.document.write('window.close();\n');
	// newwin.document.write('}\n');
	// newwin.document.write('else{\n');
	// newwin.document.write('setTimeout("chkstate()",2000)\n');
	// newwin.document.write('}\n');
	newwin.document.write('}\n');
	newwin.document.write('function print_win(){\n');
	newwin.document.write('window.print();\n');
	newwin.document.write('chkstate();\n');
	newwin.document.write('}\n');

	newwin.document.write('<\/script>\n');
	newwin.document.write('</HEAD>\n');
	newwin.document.write('<BODY onload="print_win()">\n');
	if(mode=='1')
	{
	  newwin.document.write(document.getElementById('printDtlsDivId').innerHTML);	  
	}
	newwin.document.write('</BODY>\n');
	newwin.document.write('</HTML>\n');
	newwin.document.close();
	 //document.getElementById("strCrNo").focus();

}

	  
	  
//Date Difference Function For Date Difference Reference From DWH


function dateDifferenceInDays(mfgDate,expDate)
{
	  var t1 = convertDate_ddmmyyyy(mfgDate);
      var t2 = convertDate_ddmmyyyy(expDate);
 //Total time for one day
      var one_day=1000*60*60*24; 
 //Here we need to split the inputed dates to convert them into standard format  for furter execution
      var x=t1.split("/");     
      var y=t2.split("/");
 //date format(Fullyear,month,date) 

      var date1=new Date(x[2],(x[1]-1),x[0]);

      var date2=new Date(y[2],(y[1]-1),y[0]);
      var month1=x[1]-1;
      var month2=y[1]-1;
      
      //Calculate difference between the two dates, and convert to days
             
      _Diff=Math.ceil((date2.getTime()-date1.getTime())/(one_day)); 
     // alert("Difference Between Mfg & Exp Dates:::"+_Diff);
     return _Diff;
}



function convertDate_ddmmyyyy(dtValue)
{
  
   var val1  = trimAll(dtValue).split("-").join(".");
   var val2  = val1.split("-").join(".");
   var date  = val2.split(".")[0];
   var month = val2.split(".")[1];
    
   var convMonth ;   
   
   if(month=='JAN'||month=='Jan')
   {
   	 convMonth = "01";
   }
   if(month=='Feb')
   { 
   	 convMonth = "02";		  	   	
   }
   if(month=='Mar')
   {
   	 convMonth = "03";
   }
   if(month=='Apr')
   { 
   	 convMonth = "04";		  	   	
   }
   if(month=='May')
   {
   	 convMonth = "05";
   }
   if(month=='Jun')
   { 
   	 convMonth = "06";		  	   	
   }
   
   if(month=='Jul')
   { 
   	 convMonth = "07";		  	   	
   }
   if(month=='Aug')
   {
   	 convMonth = "08";
   }
   if(month=='Sep')
   { 
   	 convMonth = "09";		  	   	
   }
   
   if(month=='Oct')
   { 
   	 convMonth = "10";		  	   	
   }
   if(month=='Nov')
   {
   	 convMonth = "11";
   }
   if(month=='Dec')
   { 
   	 convMonth = "12";		  	   	
   }
   
   var year  = val2.split(".")[2];   
   return date+"/"+convMonth+"/"+year;
}