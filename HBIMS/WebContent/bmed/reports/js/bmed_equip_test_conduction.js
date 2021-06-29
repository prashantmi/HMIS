function getStoreName()
{
	var mode = "GETSTORENAME";
	var url = "EquipmentTestConductionRptCNT.cnt?hmode="+mode+"&deptId="+document.forms[0].strDeptCode.value;
	ajaxFunction(url,"1");
}

function getItemNameOnBasisOfStore()
{
	//alert('inside method');
	 var url="EquipmentTestConductionRptCNT.cnt?hmode=GETITEMNAME&flag="+0+"&storeId="+document.forms[0].strDeptCode.value;
	 ajaxFunction(url,"5");	
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
	   var err = document.getElementById("errMsg");
	   var temp1 = res.split("####");
	   if(temp1[0] == "ERROR")
	   {
         err.innerHTML = temp1[1];	
       }
       else
       {
       	            var itemParaObj = document.getElementById("labNameId");
       	            //alert(res);
       	            itemParaObj.innerHTML ="<select name = 'strLabCode' onchange='getItemNameOnBasisOfStore()' class='comboNormal'>" + res + "</select>";
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
       	            var itemParaObj = document.getElementById("equipmentNameId");
       	            itemParaObj.innerHTML ="<select name = 'strEquipmentNameId' onchange='' class='comboNormal'>" + res + "</select>";       
        }       
    } 
	
  
}




/************************************************************/




function cancelPage()
{
	document.forms[0].hmode.value="CANCEL";
	document.forms[0].target = "_self";
	document.forms[0].submit();

}
function validate()
{

		 var hisValidator = new HISValidator("EquipmentTestConductionRptFB");
		 
		 	var obj = document.forms[0].strDeptCode;
			document.forms[0].strStoreName.value = obj[obj.selectedIndex].text;
			//document.forms[0].strLabName.value = document.forms[0].strLabCode[document.forms[0].strLabCode.selectedIndex].text; 
		
		// alert("inside validate1");
		/* hisValidator.addValidation("strDeptId", "dontselect=0", "Please Select Department Name" );
         
     	
		 hisValidator.addValidation("strToDate","dtltet="+document.forms[0].strCurrentDate.value,"Please Select To Date Less Than or Equal To Current Date");
		 hisValidator.addValidation("strToDate","dtgtet="+document.forms[0].strFromDate.value,"Please Select To Date Greater Than Or Equal To From Date");
		       	 
        
         var retVal = hisValidator.validate();
         hisValidator.clearAllValidations(); */
         
      //  hisValidator.addValidation("strLabCode", "dontselect=0", "Please Select Lab Name" );
        // hisValidator.addValidation("strEquipmentNameId", "dontselect=0", "Please Select Item Name" );
         
        // var retVal = hisValidator.validate();
        // hisValidator.clearAllValidations();
		 retVal=true;
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



