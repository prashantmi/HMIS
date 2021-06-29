/* This flag tracks whether getOtherItemsModifyViewOnLoad invoked or not.*/
var gblGetOtherItemsModifyViewOnLoadInvokeFlag=0;
function validate1() {


	
		var hisValidator = new HISValidator("itemInventoryTransBean");

	hisValidator.addValidation("strProgramId", "dontselect=0","Please Select Programme Name");
	hisValidator.addValidation("strItemId", "dontselect=0","Please Select Equipment Name");
	hisValidator.addValidation("strItemBrandId", "dontselect=0","Please Select Equipment Model Name");
	
	document.forms[0].strManufactureId.disabled = false;
	document.forms[0].strItemSpecification.disabled = false;
	if(document.forms[0].strRegFlag.value != '2'){
		
			hisValidator.addValidation("strManufactureId", "dontselect=0","Please Select a Manufacturer");
	}
	hisValidator.addValidation("strInHandQuantity", "req","In Hand Quantity is a Mandatory Field");
	hisValidator.addValidation("strInHandQuantityUnitID", "dontselect=0","Please Select a In Hand Quantity Unit");



	hisValidator.addValidation("strItemSpecification", "maxlen=2000","Specification cannot be greater than 2000 Characters");




	if (document.forms[0].isCurrencyReq != null
			&& document.forms[0].isCurrencyReq.value == '1') {
	
		hisValidator.addValidation("strCurrencyValue", "req","Currency Value is a Mandatory Field");
		
	}


	hisValidator.addValidation("strRate", "req", "Rate is a Mandatory Field");
	hisValidator.addValidation("strRate", "amount=11,2","Rate format should be 000000000.00");
	hisValidator.addValidation("strUnitRateID", "dontselect=0","Please Select a Rate Unit");



	hisValidator.addValidation("strReceivedDate", "req","Received is a Mandatory Field");
	
	if(document.forms[0].strPoDate.value!="") {
		hisValidator.addValidation("strReceivedDate", "dtgtet="+document.forms[0].strPoDate.value,"Received Date should be Greater than or Equal to P.O. Date");
	}


	hisValidator.addValidation("strSuppliedBy", "dontselect=0","Please Select a Supplied By");
	//hisValidator.addValidation("strEquipStatusId", "dontselect=0","Please Select a Equipment Status");

	if(document.forms[0].strIsWarrantyDetails != null)
	if(document.forms[0].strIsWarrantyDetails.checked == true){
			
			
			
			hisValidator.addValidation("strWarrantyDate", "req","Warranty Date is a Mandatory Field");
			
			hisValidator.addValidation("strWarantyManufacturer", "dontselect=0","Please Select a Manufacture");
			
			hisValidator.addValidation("strWarrantyUpTo", "req","Warranty Upto is a Mandatory Field");
			hisValidator.addValidation("strWarrantyUpToUnit", "dontselect=0","Please Select a Unit");
			
			hisValidator.addValidation("strWarrantyRemarks", "maxlen=100","Remarks cannot be greater than 100 Characters");
			
			
	}

	if(document.forms[0].strIsInstallDetails != null)
	if(document.forms[0].strIsInstallDetails.checked == true){
		
		hisValidator.addValidation("strInstallStartDate", "req","Installation Start Date is a Mandatory Field");
		hisValidator.addValidation("strInstallEndDate", "req","Installation End Date is a Mandatory Field");
		//hisValidator.addValidation("strInstallStatus", "dontselect=0","Please Select an Installation Status");
		
		//hisValidator.addValidation("strInstallBy", "req","Install By is a Mandatory Field");
		hisValidator.addValidation("strInstallerContactNo", "req","Company Contact Detail is a Mandatory Field");
		//hisValidator.addValidation("strInstallEndDate", "req","Installation End Date is a Mandatory Field");
				
	}
	if(document.forms[0].isBatchReq.value == '0')
	{
		if(addedSerialNo=="")
		{
			alert("Serial No. is a Mandatory Field");
			return false;
		}
		else
		{
		document.forms[0].strAddedSlNumbers.value = addedSerialNo;
		}
	}
	
	 var retVal = hisValidator.validate();
	
	hisValidator.clearAllValidations();

	if (retVal) 
	{
		var response=confirm("Please re-check the details entered," +
				" Serial No(s). can't be modified once saved!!");
	if(response)
	{
		var checkAgain = confirm("Are you sure, you want to save the record?");
		if(checkAgain) {
		document.forms[0].hmode.value = "INSERT";
	    document.forms[0].submit();
		}
		else {
			return false;
		}
	}
	
	    
	} 
	else 
	{
		return false;
	}
	//}
	
	




	
}
function cancelPage(mode) {
	document.forms[0].hmode.value = mode;
	document.forms[0].submit();
}




 
	 /**
	  * showView
	  * @param {String} divId 
	  */
	  function showView(divId) {
	  	
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
  * showOrHideDetails
  * @param {Object} chkObj
  * @param {String} divId  
  */
  function showOrHideDetails(chkObj , divId) {
  	
  		if(document.getElementById(chkObj).checked){
  			
  			document.getElementById(divId).style.display = "block";
  			
  		}else{
  			
  			document.getElementById(divId).style.display = "none";
  		}
  	
  }
function showOrHideDetails()
{
	subGroupObj  = document.forms[0].strSubGroupId;
	var totalElement  =subGroupObj.length;
	var index = subGroupObj.selectedIndex;
	var val = subGroupObj.options[index].value.split("^");
	document.forms[0].isBatchReq.value = val[0];
	if(val[0] == "0" || document.forms[0].isConsumable.value == "0")
	{
		document.getElementById("inHandQtyRow").style.display="none";
		document.getElementById("batchNoDivId").innerHTML ='<font color="red">*</font>Serial No.';
		document.getElementById("imgStockDetails").style.display = "";
		document.getElementById("srlNo").style.display = "";
	}
	else if(val[0] == "1" || document.forms[0].isConsumable.value == "1")
	{
		document.getElementById("inHandQtyRow").style.display="";
		document.getElementById("batchNoDivId").innerHTML ='<font color="red">*</font>Cat No.';
		document.getElementById("imgStockDetails").style.display = "none";
		document.getElementById("srlNo").style.display = "none";
	
	}
}

function showOrHideDetailsOnModify()
{
	if(document.forms[0].isConsumable.value == "0")
	{
		document.getElementById("batchNoDivId").innerHTML ='<font color="red">*</font>Serial No.';
		document.getElementById("inHandRow").style.display = "none";
	}
	else
	{
		document.getElementById("batchNoDivId").innerHTML ='<font color="red">*</font>Cat No.';
		document.getElementById("inHandRow").style.display = "";
	}
}
function showOrHideDetailsOnView()
{
	if(document.forms[0].isConsumable.value == "0")
	{
		document.getElementById("batchNoDivId").innerHTML ='Serial No.';
		document.getElementById("inHandRow").style.display = "none";
	}
	else
	{
		document.getElementById("batchNoDivId").innerHTML ='Cat No.';
		document.getElementById("inHandRow").style.display = "";
	}
}

function ajaxItemName(mode) {

	//emptySrlNo();
	var mode = "ITEMNAME";
	var	subGroupObj  = document.forms[0].strSubGroupId;
	var index = subGroupObj.selectedIndex;
	var subGroupId = subGroupObj.options[index].value.split("^");
	document.forms[0].isConsumable.value = subGroupId[0];
	document.forms[0].subGroupId.value = subGroupId[1];
	var url = "ItemInventoryTransCNT.cnt?hmode=ITEMNAME&strSubGroupId="
			+ subGroupId[1] + "&GroupId="
			+ document.forms[0].strGroupId.value + "&storeId="
			+ document.forms[0].strStoreId.value+"&strCatCode="+document.forms[0].strItemCategoryNo.value;
			
	ajaxFunction(url, "1");

}

function ajaxItemBrandName1(mode) {
	//emptySrlNo();
	var mode = "UNITNAMECOMBO";

	// alert("strore id"+document.forms[0].strItemName.value);
	var url = "ItemInventoryTransCNT.cnt?hmode=UNITNAMECOMBO&strItemId="
			+ document.forms[0].strItemId.value;
	ajaxFunction(url, "3");
}

function ajaxItemBrandName(mode) {
	//emptySrlNo();
	var mode = "ITEMBRANDNAME";
	if(document.forms[0].strProgramId.value==0)
	{	
		alert("Please select a program name");
		document.forms[0].strItemId.value="0";
		document.forms[0].strProgramId.focus();
		return false;
	}
	// alert("strore id"+document.forms[0].strItemName.value);
	var url = "ItemInventoryTransCNT.cnt?hmode=ITEMBRANDNAME&strItemId="
			+ document.forms[0].strItemId.value + "&storeId="
			+ document.forms[0].strStoreId.value+"&strSubGroupId="
				+ document.forms[0].strSubGroupId.value + "&strGroupId="
				+ document.forms[0].strGroupId.value+"&strCatCode="+document.forms[0].strItemCategoryNo.value
				+"&strProgramId="+document.forms[0].strProgramId.value;
	ajaxFunction(url, "2");

}


function isCurrencyMandatory(obj) {
	
	
	if(obj.value != document.forms[0].strDefaultCurrencyCode.value){
		
		document.forms[0].isCurrencyReq.value = "1";
		document.forms[0].strCurrencyValue.disabled = false;
		document.getElementById("currencyDivId").innerHTML = "<font color='red'>*</font>Currency Value";
		
		
	}else{
		
		document.forms[0].isCurrencyReq.value = "0";
		document.forms[0].strCurrencyValue.disabled = true;
		document.getElementById("currencyDivId").innerHTML = "Currency Value";
	
		
	}
	
	
}

function getItemMandatoryDtls(index) {

	if (document.forms[0].strItemId.value != '0') {

		var mode = "GETITEMMANDATORYDTLS";
		var url = "MmsCNT.cnt?hmode=" + mode + "&itemId=" + document.forms[0].strItemId.value;		
		ajaxFunction(url, "4");

	} else {

		document.forms[0].isBatchReq.value = "0";
		showOrHideDetails();
		/*subGroupObj  = document.forms[0].strSubGroupId;
	var totalElement  =subGroupObj.length;
	for(var i=0;i<totalElement;i++)
		{
			if(subGroupObj.options[i].selected && subGroupObj.options[i].text.toUpperCase()=="GROUP A")
			{
				document.getElementById("inHandQtyRow").style.display="none";
				document.getElementById("batchNoDivId").innerHTML ='<font color="red">*</font>Serial No.';
				break;										
			}
			else if(subGroupObj.options[i].selected && subGroupObj.options[i].text.toUpperCase()=="GROUP B")
			{
				document.getElementById("inHandQtyRow").style.display="";
				document.getElementById("batchNoDivId").innerHTML ='<font color="red">*</font>Cat No.';
				break;
			}
		}*/
		
		//document.getElementById("batchNoDivId").innerHTML = "<font color='red'>*</font>Serial No.";

//		document.forms[0].isExpirtReq.value = "0";
		document.getElementById("expiryDateDivId").innerHTML = "Expiry Date";

	}

}

function ajaxManufectureName(mode) {
	if(mode=='MODIFY')
	{
		document.getElementById("manufDivIdDefault").style.display = "none";
		document.getElementById("manufDivId").style.display = "block";
	}
	var mode = "MANUFECTURENAME";
	var url = "ItemInventoryTransCNT.cnt?hmode=MANUFECTURENAME&strItemBrandId="
			+ document.forms[0].strItemBrandId.value +"&strCatCode="+document.forms[0].strItemCategoryNo.value;


	ajaxFunction(url, "5");
}


function getItemBrandDetails() {
	
	if(document.forms[0].strItemBrandId.value != 0){	
			
			var mode = "BRANDDETAILS";
			var url = "ItemInventoryTransCNT.cnt?hmode="+mode+"&strItemBrandId="
					+ document.forms[0].strItemBrandId[document.forms[0].strItemBrandId.selectedIndex].value ;
										
			ajaxFunction(url, "6");
	
		}else{
		
				document.getElementById("manfNotMandatoryDivId").style.display = "none";
			 	document.getElementById("manfMandatoryDivId").style.display = "block";
			 	
			 	document.getElementById("specNotMandatoryDivId").style.display = "none";
			 	document.getElementById("specMandatoryDivId").style.display = "block";
			 		 	
			 	document.forms[0].strManufactureId.selectedIndex = 0;	 	
			 	document.forms[0].strManufactureId.disabled = false; 	
			 	document.forms[0].strItemSpecification.value = "";	 	
			 	document.forms[0].strItemSpecification.disabled = false; 	
		}
}


function getManufecture(mode) {
	document.forms[0].hmode.value = mode;
	document.forms[0].submit();
}

function getAjaxResponse(res, mode) {

	var objVal;
	if (mode == "1") {
		objVal = document.getElementById("ItemNameId");
		objVal.innerHTML = "<select name ='strItemId' class='comboNormal' onChange = 'ajaxItemBrandName(\"ITEMBRANDNAME\");showOrHideDetails();resetSerialNumberDiv();' >"
				+ res + "</select>";
	}
	if (mode == "2") {
		//alert(res);
		objVal = document.getElementById("ItemBrandId");
		objVal.innerHTML = "<select name ='strItemBrandId' class='comboNormal' onChange='ajaxManufectureName(\"MANUFECTURENAME\");resetSerialNumberDiv();' >"
				+ res + "</select>";
		
		showOrHideDetails();
		getItemMandatoryDtls('0');
		
	}

	if (mode == "3") {
		//alert(res);
		objVal = document.getElementById("UnitRateID");
		objVal.innerHTML = "<select name ='strUnitRateID' class='comboNormal' onchange='return validateQty(\"strRate\",\"strUnitRateID\");' >"
				+ res + "</select>";
		document.getElementById("UnitRateID1").innerHTML = "<select name ='strUnitSaleID' class='comboNormal' onchange='return validateQty(\"strSalePrice\",\"strUnitSaleID\");' >"
				+ res + "</select>";
		document.getElementById("freeItemUnit").innerHTML = "<select name ='strInHandQuantityUnitID' class='comboNormal' onchange='return validateQty(\"strInHandQuantity\",\"strInHandQuantityUnitID\");' >"
				+ res + "</select>";
        //document.getElementById("freeItemUnitValue").innerHTML = "<select name ='strFreeItemQuantityUnitID' class='comboNormal' onChange='' >"
			//	+ res + "</select>";	

	}

	else if (mode == "4") {
			//alert(res);		
					/*
							SELECT NVL(HSTNUM_BATCHNO_REQ,0)||'^'||NVL(HSTNUM_SERIALNO_REQ,0)||'^'||NVL(HSTNUM_EXPIRYDATE_REQ,0)
                      FROM HSTT_ITEM_MST
                    WHERE GNUM_ISVALID = 1
                    AND GNUM_HOSPITAL_CODE = 998
                    AND HSTNUM_ITEM_ID =   18000001
                    */
                     
		var temp = res.split('^');
								
/*		document.forms[0].isBatchReq.value = temp[0];

		if (temp[0] == '1') {

			document.getElementById("batchNoDivId").innerHTML = "<font color='red'>*</font>Cat No.";
		}
		else 
		{
			document.getElementById("batchNoDivId").innerHTML = "<font color='red'>*</font>Serial No.";			
		}

		/*document.forms[0].isExpirtReq.value = temp[2];

		if (temp[2] == '1') {

			document.getElementById("expiryDateDivId").innerHTML = "<font color='red'>*</font>Expiry Date";
		} else {

			document.getElementById("expiryDateDivId").innerHTML = "Expiry Date";
		}*/

		ajaxItemBrandName1('UNITNAMECOMBO');
		
	}

	if (mode == '5') {
		//alert("ok mode 5");
		document.getElementById("manufDivId").innerHTML = "<select class='comboMax' name='strManufactureId' onchange='setWarrantyManfacturer(this);' >"
				+ res + "</select>";

		if(document.getElementById("warrantyManufDivId") != null)
		document.getElementById("warrantyManufDivId").innerHTML = "<select class='comboMax' name='strWarantyManufacturer' >"
				+ res + "</select>";

		getItemBrandDetails();

	}
	
	if (mode == '6') {
	
	
		 var brandDtls = res.split("^");
		 
		 document.forms[0].strRegFlag.value = brandDtls[0];
		 document.forms[0].strConfigIssueRate.value = brandDtls[4];
				 
		 if(brandDtls[0] == '2'){
		 	
		 	document.getElementById("manfNotMandatoryDivId").style.display = "none";
		 	document.getElementById("manfMandatoryDivId").style.display = "block";
		 	
		 	document.getElementById("specNotMandatoryDivId").style.display = "none";
		 	document.getElementById("specMandatoryDivId").style.display = "block";
		 		 	
		 	document.forms[0].strManufactureId.selectedIndex = 0;	 	
		 	document.forms[0].strManufactureId.disabled = false; 	
		 	document.forms[0].strItemSpecification.value = "";	 	
		 	document.forms[0].strItemSpecification.disabled = false; 	
		 		 	
		 }else{
		 	
		 	document.getElementById("manfNotMandatoryDivId").style.display = "block";
		 	document.getElementById("manfMandatoryDivId").style.display = "none";
		 	
		 	document.getElementById("specNotMandatoryDivId").style.display = "block";
		 	document.getElementById("specMandatoryDivId").style.display = "none";
		 	//alert(brandDtls[3]+"specification "+brandDtls[1]);	 	
		 	document.forms[0].strManufactureId.value = brandDtls[3];
		 	document.forms[0].strManufactureId.disabled = true; 	
		 	document.forms[0].strItemSpecification.value = brandDtls[1];	 	
			document.forms[0].strItemSpecification.disabled = true; 	
			
					 			 	
		 }
		 
	} else if(mode == '7') 
	{
			if(res == "false")
			{
				alert("Stock already Exist");
			}
			else
			{
				//addSerialNo();
				addSerialNumber();
			}
			
	} 
	if(mode == '8') {
			
			return false;

	}
	

}

/**
 * checkForWarrantyDtls
 * @param {String} divId 
 */
 function checkForWarrantyDtls(divId) {
 	
 	if(document.forms[0].strIsWarrantyDetails != null)
 	if(document.forms[0].strIsWarrantyDetails.checked == true){
  			
  			document.getElementById(divId).style.display = "block";
  			
  		}else{
  			
  			document.getElementById(divId).style.display = "none";
  		}
 }


/**
 * checkFoInstallationDtls
 * @param {String} divId 
 */
 function checkFoInstallationDtls(divId) {
 	
 	if(document.forms[0].strIsInstallDetails != null)
 	if(document.forms[0].strIsInstallDetails.checked == true){
  			
  			document.getElementById(divId).style.display = "block";
  			document.getElementById("NotInstallItemDtlsDivId").style.display = "none";
  			
  		}else{
  			
  			document.getElementById(divId).style.display = "none";
  			document.getElementById("NotInstallItemDtlsDivId").style.display = "block";
  		}
 }

/**
 * checkForSerialNo
 */
 function checkForSerialNo() {
 	
 		var srNo = document.forms[0].strSerialNo.value;
 	
 		if(srNo.length > 1){
 			
 			document.forms[0].strInHandQuantityUnitID.disabled = true;
 			
 		}
 	
 }



/**
 * setWarrantyManfacturer
 * @param {Object} comboObj 
 */
 function setWarrantyManfacturer(comboObj) {
 	
 	if(document.getElementsByName("strWarantyManufacturer").length > 0){
 		
 		document.forms[0].strWarantyManufacturer.selectedIndex = comboObj.selectedIndex;
 		
 	}
 	
 }

function validatemodify() {

	

	var hisValidator = new HISValidator("itemInventoryTransBean");

//	document.forms[0].strManufactureId.disabled = false;
	document.forms[0].strItemSpecification.disabled = false;

	var srNo = document.forms[0].strSerialNo.value;
 /*	
 		if(srNo.length > 1){
 			
 			var val = document.forms[0].strInHandQuantity.value;
 			
 			if(val == "" || val.length == 0) val = "0";
 			
 			val = parseInt(val);
 			
 			if(val > 1){
 				
 				 alert("In Hand Quantity Cannot be Greater than 1");
 				 
 				 return false;
 			}
 		}

*/

	hisValidator.addValidation("strRate", "req", "Rate is a Mandatory Field");
	hisValidator.addValidation("strRate", "amount=11,2","Rate format could be 000000000.00");
	hisValidator.addValidation("strUnitRateID", "dontselect=0","Please Select a Rate Unit");
	hisValidator.addValidation("strReceivedDate", "req","Received is a Mandatory Field");
	hisValidator.addValidation("strSuppliedBy", "dontselect=0","Please Select a Supplied By");
	
	if(document.forms[0].strPoDate.value!="" && document.forms[0].strReceivedDate.value!="") {
		hisValidator.addValidation("strReceivedDate", "dtgtet="+document.forms[0].strPoDate.value,"Received Date should be Greater than or Equal to P.O. Date");
	}

	if(document.forms[0].strIsInstallDetails != null)
	if(document.forms[0].strIsInstallDetails.checked == true){
		hisValidator.addValidation("strInstallStartDate", "req","Installation Start Date is a Mandatory Field");
		hisValidator.addValidation("strInstallEndDate", "req","Installation End Date is a Mandatory Field");
		hisValidator.addValidation("strInstallerContactNo", "req","Company Contact Detail is a Mandatory Field");
	}


	var retVal = hisValidator.validate();
	
	hisValidator.clearAllValidations();

	if (retVal) 
	{
		
			document.forms[0].strInHandQuantityUnitID.disabled = false;
			var parValObj = document.getElementsByName("strParamValue");
			for(var index=0; index<parValObj.length; index++) 
			{
				document.forms[0].strParamValue[index].disabled = false;
			}
  		   document.forms[0].hmode.value = "UPDATE";
   	       document.forms[0].submit();
	} 
	else 
	{
		return false;
	}
}


	/**
	 * addFreeItems
	  
	 */
	 function addFreeItems() {
	 	
	 	var hisValidator = new HISValidator("itemInventoryTransBean");
	 	
	 		hisValidator.addValidation("strItemId", "dontselect=0","Please Select a Generic Item");
			hisValidator.addValidation("strItemBrandId", "dontselect=0","Please Select a Item");
	
		var retVal = hisValidator.validate();
	
		hisValidator.clearAllValidations();

	if (retVal) {
	
	 	
	 		var mode 		= "1";
	 		var isBatchReq  = document.forms[0].isBatchReq.value;
	 		var isExpDtReq  = document.forms[0].isExpirtReq.value;
	 	
	 	getItemOtherDetailsView(mode, isBatchReq , isExpDtReq , '1' , "itemInventoryTransBean");
	}else{
		
		return false;
	}
	 	
	 }
	


	/**
	 * addFreeItems
	  
	 */
	 function addPartItems() {
	 	
	 	var hisValidator = new HISValidator("itemInventoryTransBean");
	 	
	 		hisValidator.addValidation("strItemId", "dontselect=0","Please Select a Generic Item");
			hisValidator.addValidation("strItemBrandId", "dontselect=0","Please Select a Item");
	
		var retVal = hisValidator.validate();
	
		hisValidator.clearAllValidations();

	if (retVal) {
	 	
	 		var mode 		= "2";
	 		var isBatchReq  = document.forms[0].isBatchReq.value;
	 		var isExpDtReq  = document.forms[0].isExpirtReq.value;
	 	
	 	getItemOtherDetailsView(mode, isBatchReq , isExpDtReq , '2' , "itemInventoryTransBean");
	 	
	 	}else{
		
		return false;
	}
	 	
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
	 		
	 		var itemVal = document.forms[0].strChk.value.split('@');
	 		
	 		showPopup(parentObject , mode , catNo , itemVal[2]);
	 			
	 		}
	 			 		
	 		
	 		
	 	} else{
	 		
	 		popup('popUpDivId','100','250');
	 		
	 	}
	 	
	 	
	 }
	
	
	
/**
 * validateQty - Qty validation.
 * @param {String} unitName
 * @param {String} qtyName  
 */
function validateQty(qtyName, unitName) {

	
		var unitObj = document.getElementsByName(unitName)[0];
		var qtyObj = document.getElementsByName(qtyName)[0];

		
			var qtyDeceimal = qtyObj.value;

			var unitVal = unitObj.value.split('^')[2];

			if (qtyDeceimal.indexOf('.') > -1 && unitVal == '0') {

				alert("Qty must be an Integer ");
				qtyObj.value = '0';
				qtyObj.focus();								
				return false;
			}


	return true;
}


function initializeModifyPage() {

	chkRecordSaved();
	chkInstallationsDtlExist();
	checkFoInstallationDtls('installItemDtlsDivId');
	checkForSerialNo();
	showOrHideDetailsOnModify();
	changeDivImg();
	/* To show free item details.*/
	/*
	 * No more required.
	 * Suggested by: Amit Kumar
	 * Changed by: Aritra
	 */
	/*
	getOtherItemsModifyViewOnLoad('1');
	showView('freeItemDtlsDivId');
	*/
	

	
	
	

}
function initializeViewPage() {

	/*checkForWarrantyDtls('warrantyItemDtlsDivId');
	checkFoInstallationDtls('installItemDtlsDivId');
	showOrHideDetailsOnModify();*/
	changeDivImg();
	chkInstallationsDtlExist();
	checkFoInstallationDtls('installItemDtlsDivId');
	checkForSerialNo();
	showOrHideDetailsOnView();
}
function getOtherItemsModifyViewOnLoad(mode){
 	
 	gblLayerIndexVal = mode;
 	++gblGetOtherItemsModifyViewOnLoadInvokeFlag;
 	
 		var hmode = "GETOTHERITEMMODIVIEW";
  	
  		var strChkVal = document.forms[0].strChk.value.split('@');
  		
  		var strItemId 		= strChkVal[1];
  		var strItemBrandId  = strChkVal[2];
  		var strBatchNo		= strChkVal[3];
  		
		var url = "MmsCNT.cnt?hmode=" + hmode +"&strItemId="+strItemId+"&strItemBrandId="
									  +strItemBrandId+"&strBatchNo="+strBatchNo+"&layerIndex="+mode+"&mode="+mode;
		
		ajaxFunction2(url, "7", "getItemOtherDtlsAjaxResponseOnload");
  		 	
 }

function getItemOtherDtlsAjaxResponseOnload(res, mode) 
{

	
	if (mode == '7') 
	{

		var resVal = res.split('@@');

		var itemParaObj = document.getElementById("id"+gblLayerIndexVal);

		itemParaObj.innerHTML = resVal[0];
	
		document.getElementsByName("rowIndex"+gblLayerIndexVal)[0].value = resVal[1];
		document.getElementsByName("rowLength"+gblLayerIndexVal)[0].value = resVal[1];
		
		/* To show component details.*/
		if(gblGetOtherItemsModifyViewOnLoadInvokeFlag==1) 
		{
			getOtherItemsModifyView('2');
			showView('partItemDtlsDivId');
			
			/* reseting gblGetOtherItemsModifyViewOnLoadInvokeFlag */
			gblGetOtherItemsModifyViewOnLoadInvokeFlag=0;
		}
	}
}

function ajaxChkDuplicate(srlNo) 
{
	if(document.getElementById("strBatchNo").value == "")
	{
		alert("Serial Number is Blank. Please enter a valid serial number." );
		return false;
	}
	var mode = "CHKDUPLICATE";
	var url = "ItemInventoryTransCNT.cnt?hmode=CHKDUPLICATE&strBatchNo=" + document.getElementById("strBatchNo").value
		    + "&strDepartmentId=" + document.forms[0].strDepartmentId.value + "&strItemId="
			+ document.forms[0].strItemId.value + "&storeId="
			+ document.forms[0].strStoreId.value+"&strItemBrandId="+document.forms[0].strItemBrandId.value;
			
	ajaxFunction(url, "7");
}
 
 function chkRecordSaved()
 {
 	if(document.forms[0].strRetValue.value=="1")
 	{
 		alert(document.forms[0].strMsg.value);
 		document.forms[0].hmode.value = "ListPage";
		document.forms[0].submit();
 	}
 }

function addSpan(slNo)
{
	var serialNoCount=1;
	var objDiv=document.getElementById("multiSerialNoDiv");
	if(objDiv.innerHTML.length==0)
		{
			objDiv.innerHTML="<span id='mSerialNo"+serialNoCount+"' style='margin-right: 10px; border: 1px solid turquoise; padding: 3px; background-color: #e7ecec;'>"+
			slNo+"<img onkeypress='if(event.keyCode==13) removeSerialNumber("+serialNoCount+");' tabindex='0' "+ 
			"onclick='removeSerialNumber("+serialNoCount+");' title='Click to remove Serial Number' style='position: relative;cursor: pointer;vertical-align: bottom;top: 2px;' "+ 
			"src='../../hisglobal/images/delete_on.gif'></span>";
			//objDiv.innerHTML="<span id='mSerialNo"+serialNoCount+"'>"+document.getElementById("strBatchNo").value+"</span>";
		}
	else{
		serialNoCount=addedSerialNo.split("#").length+1;//document.getElementsByName("mSerialNo").length+1;
		objDiv.innerHTML+="<span id='mSerialNo"+serialNoCount+"' style='margin-right: 10px; border: 1px solid turquoise; padding: 3px; background-color: #e7ecec;'>"+
		slNo+"<img onkeypress='if(event.keyCode==13) removeSerialNumber("+serialNoCount+");' tabindex='0' "+ 
			"onclick='removeSerialNumber("+serialNoCount+");' title='Click to remove Serial Number' style='position: relative;cursor: pointer;vertical-align: bottom;top: 2px;' "+ 
			"src='../../hisglobal/images/delete_on.gif'></span>";
	}	
		//alert("serialNoCount::"+serialNoCount);
		
}	
var addedSerialNo = "";
 function addSerialNumber()
 {
 	var srlNo = document.getElementById("strBatchNo").value;
 	if(checkDuplicateSrlNo(srlNo))
	{
		alert("Duplicate Serial Number");
		return false;
	}
	addSpan(srlNo);
	if(addedSerialNo!="")
 		addedSerialNo += "#"+srlNo;
 	else
 		addedSerialNo = srlNo;
 	//alert("Main addedSerialNo::"+addedSerialNo);
 	document.forms[0].strBatchNo.value = "";
 	document.forms[0].strBatchNo.focus();
 	getTotalSerialNoCount();
 }
 function checkDuplicateSrlNo(srlNo)
 {
 	var tmpSrlNo = addedSerialNo.split("#");
 	var flag = false;
 	for(i = 0;i<tmpSrlNo.length;i++)
 	{
 		if(tmpSrlNo[i] == srlNo)
 		{
 			flag = true;
 			return flag;
 		}
 	}
 }	
 function removeSerialNumber(idVal){

	 var d = document.getElementById('multiSerialNoDiv');

  var olddiv = document.getElementById("mSerialNo"+idVal);
  d.removeChild(olddiv);
  rebuildTotalSerial();
  getTotalSerialNoCount();
}
function rebuildTotalSerial()
{
	tmpTotalSrNos="";
	var objDiv=document.getElementById("multiSerialNoDiv");
	for(var i=1;i<=addedSerialNo.split("#").length+2;i++){
		if(document.getElementById("mSerialNo"+i)!=null){
			var tmpVal=document.getElementById("mSerialNo"+i).innerHTML.substr(0,document.getElementById("mSerialNo"+i).innerHTML.lastIndexOf("<")).trim();
			if(tmpTotalSrNos!="")
 				tmpTotalSrNos += "#"+tmpVal;
 			else
 				tmpTotalSrNos = tmpVal;
		}
		//else alert("Element at index::"+i+" was not found. It may have been deleted.");
	}//ends for loop
	addedSerialNo=tmpTotalSrNos;
	//alert("After addedSerialNo::"+addedSerialNo);
}
function resetSerialNumberDiv(){
	addedSerialNo="";
	var myNode = document.getElementById("multiSerialNoDiv");
	while (myNode.firstChild) {
    myNode.removeChild(myNode.firstChild);
	}
	getTotalSerialNoCount();
}
function chkInstallationsDtlExist()
{
	if(document.forms[0].strIsInstallDetails.value == "1")
 		document.forms[0].strIsInstallDetails.checked = true
}
function getUploadFile()
{
	
	 document.forms[0].hmode.value = "GETUPLOADEDFILE";
   	 document.forms[0].submit();
}

function changeDivImg()
{
	
	var imageURL = "../../hisglobal/images/";
	var strExt = document.forms[0].strUploadFileId.value;
	if(strExt.length<=3){
		document.getElementById("DocDownloadRow").style.display = "none";
		return false;
	}
			
	strExt = strExt.split(".")[1];
	
	if (strExt == "txt"){
		imageURL = imageURL + "text-icon.png";
	}else if ((strExt == "jpg") || (strExt == "jpeg") || (strExt == "gif") || (strExt == "png") || (strExt == "bmp")){
 		imageURL = imageURL + "images-icon.png";
	}else if (strExt == "pdf"){
 		imageURL = imageURL + "PDF_Small.png";
	} else if (strExt == "html"){
		imageURL = imageURL + "html-icon.png";
	}else if (strExt == "xml") {
		imageURL = imageURL + "xml-icon.png";
	} else if ((strExt == "doc") || (strExt == "docx")) {
		imageURL = imageURL + "doc-icon.png";
	} else if (strExt == "rdf") {
		imageURL = imageURL + "global-icon.png";
	} else if (strExt == "rtf") {
		imageURL = imageURL + "rtf-icon.png";
	}
	else  
	imageURL = imageURL + "global-icon.png";
	document.getElementById("IconDiv").innerHTML = "<img src='"+imageURL+"'>";
	
}
function getTotalSerialNoCount()
{
	var slNos = addedSerialNo.split("#");
	if(slNos.length>0 && slNos[0]!="") {
		document.getElementById("addedSlNoCount").innerHTML = slNos.length;
	}
	else {
		document.getElementById("addedSlNoCount").innerHTML = "";
	}
}