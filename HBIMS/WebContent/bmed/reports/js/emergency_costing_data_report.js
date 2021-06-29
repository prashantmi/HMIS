function validate()
{

		 var hisValidator = new HISValidator("emergencyCostingDataRpt");
		 
             hisValidator.addValidation("strCostingNumber", "dontselect=0", "Please Select Costing Number Combo" );
         
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
     
}

function setValueChk()
{
	if(document.getElementsByName("issueChkDetail")[0].checked)
	{
		document.getElementsByName("issueChkDetail")[1].value="1";
	
			document.getElementById("trComplaint").style.display="none";
			
		
		document.getElementById("trItemId").style.display="block";
		document.forms[0].strUniqId.value='1';
       
		
	}
	else if(document.getElementsByName("issueChkDetail")[2].checked)
	{
		document.getElementsByName("issueChkDetail")[1].value="3";
		

		document.getElementById("trItemId").style.display="none";
		document.getElementById("trComplaint").style.display="block";
		document.forms[0].strUniqId.value='3';
	}
	else
	{
			document.getElementsByName("issueChkDetail")[1].value="2";
				document.getElementById("trItemId").style.display="none";
		document.getElementById("trComplaint").style.display="none";
		    document.forms[0].strUniqId.value='2';
			
	}
}
