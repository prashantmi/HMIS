
function getItemNameCombo()
{	
    //alert("Store ID:::"+document.forms[0].strStoreId.value+":::Dept ID:::"+document.forms[0].strDeptId.value);	
   var url="EquipmentIssueAndRestorationReportACTION.cnt?hmode=GETITEMNAMECOMBO&strDeptId="+document.forms[0].strDeptId.value;
   ajaxFunction(url,"1");
   
  
} 

function getAjaxResponse(res,mode)
{	
	   var objVal;
	   var err = document.getElementById("errMsg");
	   var temp1 = res.split("####");
	   if(temp1[0] == "ERROR")
	   {
         err.innerHTML = temp1[1];	
         return;
       } 
       
    if(mode=="1")
	{	
	    var itemParaObj = document.getElementById("divItemCmbId");
        itemParaObj.innerHTML ="<select name = 'strItemId' class='COMBO_NORMAL'>" + res + "</select>";
          	        
	}
	
	 if(mode=="4")
	{	
	   var err = document.getElementById("errMsg");
	   var temp1 = res.split("####");
	   if(temp1[0] == "ERROR")
	   {
         err.innerHTML = temp1[1];	
       }
       else
       {
       	            var itemParaObj = document.getElementById("storeNameId");
       	            //alert(res);
       	            itemParaObj.innerHTML ="<select name = 'strStoreId' onchange='getItemNameOnBasisOfStore();' class='COMBO_NORMAL'>" + res + "</select>";
        }       
    }
    
    if(mode=="5")
	{	
	   var err = document.getElementById("errMsg");
	   var temp1 = res.split("####");
	   if(temp1[0] == "ERROR")
	   {
         err.innerHTML = temp1[1];	
       }
       else
       {
       	            var itemParaObj = document.getElementById("divItemCmbId");
       	            itemParaObj.innerHTML ="<select name = 'strItemId' onchange='' class='COMBO_NORMAL'>" + res + "</select>";       
        }       
    }   
}

function validate()
{

		 var hisValidator = new HISValidator("EquipmentIssueAndRestorationReportFB");
		 //alert("inside validate1");
		 hisValidator.addValidation("strDeptId", "dontselect=0", "Please Select Department Name" );
         hisValidator.addValidation("strStoreId", "dontselect=0", "Please Select Department Name" );
         
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




function onLoadPage()
{
	document.forms[0].strIsFooter.checked = true;
	document.forms[0].strDeptId.value = "0";
	document.forms[0].strFromDate.value = document.forms[0].strCurrentDate.value;
	document.forms[0].strToDate.value = document.forms[0].strCurrentDate.value;
	
}



function getStoreName()
{
	var mode = "GETSTORENAME"
	var url = "EquipmentIssueAndRestorationReportCNT.cnt?hmode="+mode+"&deptId="+document.forms[0].strDeptId.value;
	ajaxFunction(url,"4");
}


function getItemNameOnBasisOfStore()
{
	 var url="EquipmentIssueAndRestorationReportCNT.cnt?hmode=GETITEMNAME&flag="+0+"&storeId="+document.forms[0].strStoreId.value;
	 ajaxFunction(url,"5");	
}
  