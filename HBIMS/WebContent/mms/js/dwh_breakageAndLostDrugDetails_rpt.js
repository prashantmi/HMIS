
function getItemCatCmb(){ 


	if(document.getElementsByName("strWhetherConsolidatedDataForAllInstitutesOfDistrictDrugWarehouseCheckBox")[0].checked==true)
	{
			if(document.forms[0].strDistrictStoreId.value!=0)
			{
				var url ="BreakageAndLostDrugDetailsRptCNT.cnt?hmode=ITEMCATCMB&storeId="+document.forms[0].strDistrictStoreId.value;
		 		ajaxFunction(url,"1");
		 	}
	}
	else
	{
		if(document.forms[0].strStoreId.value!=0)
		{
			var url ="BreakageAndLostDrugDetailsRptCNT.cnt?hmode=ITEMCATCMB&storeId="+document.forms[0].strStoreId.value;
	 		ajaxFunction(url,"1");
	 	}
	}
}	



function getAjaxResponse(res,mode){
	
	if(mode=="1"){ 
		
		
			var objVal= document.getElementById("itemCatDivId");
			objVal.innerHTML = "<select name ='strItemCatNo' class='comboNormal' >"+res+"</select>";		
	}	
}	


function validate(){

	var hisValidator = new HISValidator("breakageAndLostDrugDetailsRptFB");

		
		hisValidator.addValidation("strStoreId", "dontselect=-1","Drug Warehouse is a mandatory field");
		hisValidator.addValidation("strItemCatNo", "dontselect=0","Item Category is a mandatory field");

		hisValidator.addValidation("strFromDate", "date","From Date is a mandatory field");
		hisValidator.addValidation("strToDate", "date","To Date is a mandatory field");
		hisValidator.addValidation("strToDate","dtltet="+document.forms[0].strCurrentDate.value,"Please Select To Date Less Than or Equal To Current Date");
		hisValidator.addValidation("strToDate","dtgtet="+document.forms[0].strFromDate.value,"Please Select To Date Greater Than Or Equal To From Date");
	
	var retVal = hisValidator.validate();
	hisValidator.clearAllValidations();

if(retVal)
{
	if(document.getElementsByName("strWhetherConsolidatedDataForAllInstitutesOfDistrictDrugWarehouseCheckBox")[0].checked==true)
	{
		document.forms[0].strStoreName.value = document.forms[0].strDistrictStoreId[document.forms[0].strDistrictStoreId.selectedIndex].text;	
	}
	else
	{
		document.forms[0].strStoreName.value = document.forms[0].strStoreId[document.forms[0].strStoreId.selectedIndex].text;
	}
	
	
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

	

function cancelPage(){
	document.forms[0].hmode.value="CANCEL";
	document.forms[0].target = "_self";
	document.forms[0].submit();

}


function setDistrictDrugWarehouseCombo()
{
	if(document.getElementsByName("strWhetherConsolidatedDataForAllInstitutesOfDistrictDrugWarehouseCheckBox")[0].checked==true)
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



function onLoadPage()
{

	document.forms[0].strStoreId.value = "0";
	document.forms[0].strDistrictStoreId.value = "0";
	document.forms[0].strItemCatNo.value = "0";

}


