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

		 var hisValidator = new HISValidator("EquipManpowerAssignmentRegReportFB");
		 
		 
		 
		 alert("inside validate1");
		 hisValidator.addValidation("strDeptCode", "dontselect=0", "Please Select Hospital Name" );
		 hisValidator.addValidation("strLabCode", "dontselect=0", "Please Select Lab Name" );
         
        
         var retVal = hisValidator.validate();
         hisValidator.clearAllValidations(); 
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



