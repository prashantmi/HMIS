
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
       	            itemParaObj.innerHTML ="<select name = 'strLabCode' onchange='' class='comboNormal'>" + res + "</select>";
        }       
    }
    
    
   
}

function getStoreName()
{
	var mode = "GETSTORENAME"
	var url = "ManpowerAssignmentDeskCNT.cnt?hmode="+mode+"&deptId="+document.forms[0].strDeptCode.value;
	ajaxFunction(url,"1");
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

		 var hisValidator = new HISValidator("ManpowerAssignmentDeskRptFB");
		 
		 	var obj = document.forms[0].strDeptCode;
			document.forms[0].strStoreName.value = obj[obj.selectedIndex].text;
		
		 //alert("inside validate1");
		/* hisValidator.addValidation("strDeptId", "dontselect=0", "Please Select Department Name" );
         
     	
		 hisValidator.addValidation("strToDate","dtltet="+document.forms[0].strCurrentDate.value,"Please Select To Date Less Than or Equal To Current Date");
		 hisValidator.addValidation("strToDate","dtgtet="+document.forms[0].strFromDate.value,"Please Select To Date Greater Than Or Equal To From Date");
		       	 
        
         var retVal = hisValidator.validate();
         hisValidator.clearAllValidations(); */
         
       //  hisValidator.addValidation("strLabCode", "dontselect=0", "Please Select Lab Name" );
       //   var retVal = hisValidator.validate();
       //  hisValidator.clearAllValidations();
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



