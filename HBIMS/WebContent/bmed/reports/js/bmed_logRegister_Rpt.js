function validate()
{

		 var hisValidator = new HISValidator("logRegisterReportFB");
		// alert("inside validate1");
		 hisValidator.addValidation("strDeptId", "dontselect=0", "Please Select Department Name" );
         hisValidator.addValidation("strStoreId", "dontselect=0", "Please Select Store Name" );
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

function getStoreNameCombo()
{	
    //alert("Store ID:::"+document.forms[0].strStoreId.value+":::Dept ID:::"+document.forms[0].strDeptId.value);	
   var url="logRegisterReportACTION.cnt?hmode=GETSTORENAMECOMBO&strDeptId="+document.forms[0].strDeptId.value;
   ajaxFunction(url,"1");
   
  
} 

function getAjaxResponse(res,mode)
{	
	  /* var objVal;
	   var err = document.getElementById("errMsg");
	   var temp1 = res.split("####");
	   if(temp1[0] == "ERROR")
	   {
         err.innerHTML = temp1[1];	
         return;
       } */
       
    if(mode=="1")
	{	
	    var storeParaObj = document.getElementById("divStoreId");
        storeParaObj.innerHTML ="<select name = 'strStoreId' class='COMBO_MAX'>" + res + "</select>";
          	        
	}
}
function cancelPage()
{
	document.forms[0].hmode.value="CANCEL";
	document.forms[0].target = "_self";
	document.forms[0].submit();

}




function onLoadPage()
{
	document.forms[0].strIsFooter.checked = true;
	document.forms[0].strDeptId.value = "0";
	document.forms[0].strStoreId.value = "0";
	document.forms[0].strFromDate.value = document.forms[0].strCurrentDate.value;
	document.forms[0].strToDate.value = document.forms[0].strCurrentDate.value;
	
}