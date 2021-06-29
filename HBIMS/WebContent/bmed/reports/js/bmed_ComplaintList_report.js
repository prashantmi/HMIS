function validate()
{

		 var hisValidator = new HISValidator("listofComplaint");
		 if(document.forms[0].strUniqId.value == '1')
		 {
             hisValidator.addValidation("strDeptId", "dontselect=0", "Please Select Department Combo" );
         }    
         if(document.forms[0].strUniqId.value == '2')
		 {
             hisValidator.addValidation("strItemId", "dontselect=0", "Please Select Item Combo" );
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
		document.getElementsByName("issueChkDetail")[0].value="1";
		document.getElementsByName("strUserRemarks")[0].value="";
		document.getElementById("comboId").style.display="";
		document.getElementById("deptCmb").style.display="block";
		document.getElementById("itemCmb").style.display="none";
		document.getElementById("id1").innerHTML ="<font color='red'>*</font>Department Name";
		document.forms[0].strUniqId.value='1';
       
		
	}
	else if(document.getElementsByName("issueChkDetail")[1].checked)
	{
		document.getElementsByName("issueChkDetail")[1].value="2";
		document.getElementsByName("strUserRemarks")[0].value="";
		document.getElementById("comboId").style.display="";
		document.getElementById("deptCmb").style.display="none";
		document.getElementById("itemCmb").style.display="block";
		document.getElementById("id1").innerHTML ="<font color='red'>*</font>Item Name";
		document.forms[0].strUniqId.value='2';
	}
	else
	{
			document.getElementsByName("issueChkDetail")[1].value="3";
			document.getElementsByName("strUserRemarks")[0].value="";	
			document.getElementById("comboId").style.display="none";
			document.getElementById("deptCmb").style.display="none";
		    document.getElementById("itemCmb").style.display="none";
		    document.getElementById("id1").innerHTML ="";		
		    document.forms[0].strUniqId.value='3';
			
	}
}
function onLoadPage()
{
	document.forms[0].strIsFooter.checked = true;
	document.forms[0].strDeptId.value = "0";
	document.forms[0].strItemId.value = "0";	
	document.forms[0].strFromDate.value = document.forms[0].strCurrentDate.value;
	document.forms[0].strToDate.value = document.forms[0].strCurrentDate.value;
	document.forms[0].strUniqId.value='1';
	
}