
function validate1()
{


	var hisValidator = new HISValidator("issueToThirdPartyRptFB");
	
	hisValidator.addValidation("strItemCatNo", "dontselect=0","Select Item Category");
	hisValidator.addValidation("strStoreId","dontselect=-1","Please Select DDW");
	hisValidator.addValidation("strFromDate", "date","From Date is a mandatory field");
		hisValidator.addValidation("strToDate", "date","To Date is a mandatory field");
		hisValidator.addValidation("strToDate","dtltet="+document.forms[0].strCurrentDate.value,"Please Select To Date Less Than or Equal To Current Date");
		hisValidator.addValidation("strToDate","dtgtet="+document.forms[0].strFromDate.value,"Please Select To Date Greater Than Or Equal To From Date");
			
	
	var retVal = hisValidator.validate();
	hisValidator.clearAllValidations();
	
		if(retVal){
			
			
			document.forms[0].strStoreName.value = document.forms[0].strStoreId[document.forms[0].strStoreId.selectedIndex].text;
			document.forms[0].strSourceName.value = document.forms[0].strThirdPartyId[document.forms[0].strThirdPartyId.selectedIndex].text;
			
			document.forms[0].hmode.value = "SHOWRPT";
		
				if(document.forms[0].strReportFormat[document.forms[0].strReportFormat.selectedIndex].value == "html")
				{
					document.forms[0].target = "_self";
				}else{
					document.forms[0].target = "_blank";
				}
				document.forms[0].submit();
				}else{
			return false;
		}
	}
	
function getStoreCmb()
{ 
	
		if(document.forms[0].strItemCatNo.value!=0)
		{
			var url ="IssueToThirdPartyRptCNT.cnt?hmode=STORECMB&categoryId="+document.forms[0].strItemCatNo.value;
	 		ajaxFunction(url,"1");
	 		
	 	}
	 			
}	

function getAjaxResponse(res,mode){
	
	if(mode=="1"){ 
		
		
			var objVal= document.getElementById("drugWarehouseDivId");
			objVal.innerHTML = "<select name ='strStoreId' class='comboNormal' >"+res+"</select>";		
		
	}	
	
	if(mode=="2"){ 
			var objVal= document.getElementById("groupDivId");
			objVal.innerHTML = "<select name ='strGroupId' class='comboNormal' >"+res+"</select>";		
		
	}	
}	
	
function cancelPage(){
	document.forms[0].hmode.value="CANCEL";
	document.forms[0].target = "_self";
	document.forms[0].submit();

}

function onBodyDisplay()
{
	document.forms[0].strStoreId.value = "0";
	document.forms[0].strItemCatNo.value="0";
	
}

function onDateDisplay(){

	if(document.getElementsByName("strCurrentStock1")[0].checked == true){
	
	document.getElementById("dateDivId").style.display = "none";
		document.forms[0].strCurrentStock.value = 1;
		 
		
	}else{
	document.getElementById("dateDivId").style.display = "block";
		document.forms[0].strCurrentStock.value = 0;
		
	 
	}
		
}

function onClickClear(){

		document.forms[0].strStoreId.value = "0";
		document.forms[0].strItemCatNo.value = "0";	
	if(document.forms[0].strCurrentStock1.checked == false){
		document.getElementById("dateDivId").style.display = "none";
		document.forms[0].strCurrentStock1.checked = true;
		document.forms[0].strCurrentStock.checked = 1;
		document.forms[0].strDate.value = document.forms[0].strCurrentDate.value;
	}
}

function onClickBatch(){

		if(document.forms[0].strBatchNo.checked){
		
			document.forms[0].strBatchNo.value = "1";
		
		}else if(document.forms[0].strBatchNo.checked == false){
		
			document.forms[0].strBatchNo.value = "0";
		
		}

}

function setDistrictDrugWarehouseCombo()
{
	if(document.getElementsByName("whetherConsolidatedStockForAllInstitutseOfDistrictDrugWarehouse")[0].checked==true)
	{
		document.getElementById("drugWarehouseDivId").style.display='none';
		
		document.getElementById("districtDrugWarehouseDivId").style.display='';
		
		
		
	}
	else
	{
		document.getElementById("drugWarehouseDivId").style.display='';
		
		document.getElementById("districtDrugWarehouseDivId").style.display='none';
		
	}
	
}
