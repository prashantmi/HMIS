function validate()
{

		 var hisValidator = new HISValidator("listOfPendingComplaintsReportFB");
          
          if(document.forms[0].strUniqId)
          {
	  			if(document.getElementsByName("strIssueChkDetail")[0].checked==true)
		  		{
		  			hisValidator.addValidation("strUniqId", "dontselect=0", "Please Select the Department Name Combo" );		
		  		}
		  		
		  		if(document.getElementsByName("strIssueChkDetail")[1].checked==true)
		  		{
		  			hisValidator.addValidation("strUniqId", "dontselect=0", "Please Select the Item Combo" );		
		  		}
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
	if(document.getElementsByName("strIssueChkDetail")[0].checked)
	{
		document.getElementsByName("strIssueChkDetail")[0].value="1";
		document.getElementsByName("strUserRemarks")[0].value="";
		
		if(document.forms[0].strUniqId)
		 {
		 	document.getElementsByName("strUniqId")[0].value="0";
		 }			
	}
	else if(document.getElementsByName("strIssueChkDetail")[1].checked)
	{
		document.getElementsByName("strIssueChkDetail")[1].value="2";
		document.getElementsByName("strUserRemarks")[0].value="";
		
		if(document.forms[0].strUniqId)
		 {
		 	document.getElementsByName("strUniqId")[0].value="0";
		 }			
	}
		
	else
	{
			document.getElementsByName("strIssueChkDetail")[2].value="3";
			document.getElementsByName("strUserRemarks")[0].value="";
			
		if(document.forms[0].strUniqId)
		 {
		 	document.getElementsByName("strUniqId")[0].value="0";
		 }			
			
	}
	document.forms[0].hmode.value="INITDETAIL";
	document.forms[0].submit();
}
function onLoadPage()
{
	document.forms[0].strIsFooter.checked = true;
	document.getElementsByName("strIssueChkDetail")[0].value="1";
	document.forms[0].strFromDate.value = document.forms[0].strCurrentDate.value;
	document.forms[0].strToDate.value = document.forms[0].strCurrentDate.value;
	
	
	
}