function getEquipmentName()
{	
   var strStoreId=	document.forms[0].strStoreId.value;
   var url="EquipmentInspectionOrTestReportACTION.cnt?hmode=GETITEMNAMECOMBO&strStoreId="+strStoreId;
   ajaxFunction(url,"1");
   
  
} 

function getEquipmentSlNo()
{
	var strStoreId=	document.forms[0].strStoreId.value;
	var strItemId =	document.forms[0].strItemId.value;
	//alert("strItemId :"+strItemId);
    var url="EquipmentInspectionOrTestReportACTION.cnt?hmode=GETEQUIPMENTTESTNAMECOMBO&strStoreId="+strStoreId+"&strItemId="+strItemId;
    ajaxFunction(url,"2");
}

function validate()
{

		 var hisValidator = new HISValidator("equipmentInspectionOrTestReportFB");
		 if(document.forms[0].strUniqId.value == '1')
		 {
             hisValidator.addValidation("strStoreId", "dontselect=0", "Please Select Store Combo" );
         }  
         if(document.forms[0].strUniqId.value == '2')
		 {
             hisValidator.addValidation("strStoreId", "dontselect=0", "Please Select Store Combo" );
             hisValidator.addValidation("strTestId", "dontselect=0", "Please Select Inspection/Test Name Combo" );
         }  
         if(document.forms[0].strUniqId.value == '3')
		 {
             hisValidator.addValidation("strStoreId", "dontselect=0", "Please Select Store Combo" );
             hisValidator.addValidation("strEquipmentTestSlNoId", "dontselect=0", "Please Select Equipment Sl No Combo" );
         } 
         	 hisValidator.addValidation("strFromDate", "date","From Date is a mandatory field");
			 hisValidator.addValidation("strToDate", "date","To Date is a mandatory field");
			 hisValidator.addValidation("strToDate","dtltet="+document.forms[0].strCurrentDate.value,"Please Select To Date Less Than or Equal To Current Date");
			 hisValidator.addValidation("strToDate","dtgtet="+document.forms[0].strFromDate.value,"Please Select To Date Greater Than Or Equal To From Date");
		       	 
        
             var retVal = hisValidator.validate();
             hisValidator.clearAllValidations(); 

         if(retVal){
		document.forms[0].hmode.value = "SHOWRPT";
		
			if(document.forms[0].strReportFormat[document.forms[0].strReportFormat.selectedIndex].value == "html")
			{
				document.forms[0].target = "_self";
			}else
			{
				document.forms[0].target = "_blank";
			}
		document.forms[0].submit();
		}else{
		return false;
	}
}	
function cancelPage()
{
	document.forms[0].hmode.value="CANCEL";
	document.forms[0].target = "_self";
	document.forms[0].submit();

}

function getAjaxResponse(res,mode)
{
      var err = document.getElementById("errMsg");
      var temp = res.split("####");
   
       if(temp[0] == "ERROR")
	   {
          	err.innerHTML = temp[1];
          	return;
       } 
    if(mode=="1")
	{	
	    var itemParaObj = document.getElementById("ItemDiv");
        itemParaObj.innerHTML =	"<select name='strItemId' class='comboNormal' onchange='getEquipmentSlNo()'>" + res+ "</select>";
		getEquipmentSlNo();
          	        
	}
	
	if(mode=="2")
	{	
	    var itemParaObj = document.getElementById("SlDiv");
        itemParaObj.innerHTML =		"<select name='strEquipmentTestSlNoId' class='comboNormal'>" + res+ "</select>";
          	        
          	                 	        
	}
}

function setValueChk()	
{
	//alert("inside setValueChk()");
	if(document.getElementsByName("strEquipmentTestChkDetail")[0].checked)
	{
		document.getElementsByName("strEquipmentTestChkDetail")[0].value="1";
		document.getElementsByName("strUserRemarks")[0].value="";
		document.getElementById("divEuipmentCmb").style.display='';
		document.getElementById("divInspentionTestCmb").style.display="none";
		document.getElementById("divEuipmentSlNoCmb").style.display="none";
		//document.getElementById("id2").innerHTML ="Equipment Name";
		document.forms[0].strUniqId.value='1';
       
		
	}
	else if(document.getElementsByName("strEquipmentTestChkDetail")[1].checked)
	{
		document.getElementsByName("strEquipmentTestChkDetail")[1].value="2";
		document.getElementsByName("strUserRemarks")[0].value="";
		document.getElementById("divEuipmentCmb").style.display="none";
		document.getElementById("divInspentionTestCmb").style.display='';
		document.getElementById("divEuipmentSlNoCmb").style.display="none";
		//document.getElementById("id2").style.display="none";
		//document.getElementById("id2").innerHTML ="<font color='red'>*</font>Equipment Name";
		document.forms[0].strUniqId.value='2';
	}
	else
	{
		document.getElementsByName("strEquipmentTestChkDetail")[2].value="3";
		document.getElementsByName("strUserRemarks")[0].value="";
		document.getElementById("divEuipmentCmb").style.display='';
		document.getElementById("divInspentionTestCmb").style.display="none";
		document.getElementById("divEuipmentSlNoCmb").style.display='';
		//document.getElementById("id2").style.display="block";
		//document.getElementById("id2").innerHTML ="<font color='red'>*</font>Equipment Name";
		document.forms[0].strUniqId.value='3';
			
	}
}
function onLoadPage()
{
	document.forms[0].strIsFooter.checked = true;
	document.forms[0].strStoreId.value = "0";
	document.forms[0].strItemId.value = "0";
	document.forms[0].strEquipmentTestSlNoId.value = "0";		
	document.forms[0].strFromDate.value = document.forms[0].strCurrentDate.value;
	document.forms[0].strToDate.value = document.forms[0].strCurrentDate.value;
	document.forms[0].strUniqId.value='1';
	
}