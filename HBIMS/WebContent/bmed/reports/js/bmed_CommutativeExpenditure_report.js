function validate()
{

		 var hisValidator = new HISValidator("commutativeExp");
		 if(document.forms[0].strUniqId.value == '1')
		 {
             hisValidator.addValidation("strItemId", "dontselect=0", "Please Select Department Combo" );
         }    
         if(document.forms[0].strUniqId.value == '3')
		 {
             hisValidator.addValidation("strComplaintId", "req", "Please enter the Complaint Id " );
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
function onLoadPage()
{
	document.forms[0].strIsFooter.checked = true;
document.getElementById("trItemId").style.display="block";
document.getElementById("trComplaint").style.display="none";

	document.forms[0].strFromDate.value = document.forms[0].strCurrentDate.value;
	document.forms[0].strToDate.value = document.forms[0].strCurrentDate.value;
	document.forms[0].strUniqId.value='1';
	
}