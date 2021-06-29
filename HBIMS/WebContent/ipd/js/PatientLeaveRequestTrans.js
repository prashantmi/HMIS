
function hlpOnLoad_LJ()
{     
          var cnt=document.forms[0].cnt.value;
          var frmName=document.forms[0].name;
          if(document.forms[0].strCrNo.value>0)
          {
            var o1=document.getElementById("admDtlTld");
            if((o1.innerHTML).length>9)
            {
               document.getElementById("disBnR").style.display="block";
               document.getElementById("patTldglbdiv").style.display="block";
               document.getElementById("crNoId").style.display="none";
               document.getElementById("admDtlTldglbdiv").style.display="block";
               document.getElementById("patDtlTld").style.display="none";
               document.getElementById("admDtlTld").style.display="block";
               document.getElementById("transChng").style.display="block";
               if(frmName=="patientLeaveRequestTransBean")
               {
                 document.getElementById("patDtlTld").style.display="block";
                 document.getElementById("LeaveId").style.display="block";
                 document.getElementById("init").style.display="none";//hide loading msg
               }
		    }
		    else
		    {
		       document.forms[0].strErrMsgString.value=document.forms[0].strGlbErrMsgTLD.value;
		       document.getElementById("patTldglbdiv").style.display="none";
		       document.getElementById("admDtlTldglbdiv").style.display="none";
		       document.getElementById("crNoId").style.display="";
               document.getElementById("patDtlTld").style.display="none";
               document.getElementById("transChng").style.display="none";
               document.getElementById("admDtlTld").style.display="none";
               document.getElementById("disBnR").style.display="none";
               document.getElementsByName("strErrMsgString")[0].value="Invalid CR No./Data Not Found";
               document.getElementById("errMsg").style.display="block";
               if(frmName=="patientLeaveRequestTransBean")
               {
                 document.getElementById("LeaveId").style.display="none";
               }
               document.forms[0].hmode.value="CANCEL";
               document.forms[0].strCrNo.value="";
               document.forms[0].submit();
		    }
		  }
		  else
		  {
		  }
}


function save()
{
	if(document.forms[0].strCrNo.value=="")
	{
		alert("Please Enter CR No.");
		return;
	}
 var frmName=document.forms[0].name;
 var hisValidator = new HISValidator(frmName); 
  hisValidator.addValidation("strValidFrom", "req","Leave From Date Is  Mandatory");
  hisValidator.addValidation("strValidTo", "req","Leave To Date Is  Mandatory");
  hisValidator.addValidation("strValidFrom","dtgtet="+document.forms[0].strCtDate.value,"Please Select Leave From Date Greater Than Or Equal To Current Date.");
  hisValidator.addValidation("strValidTo","dtgtet="+document.forms[0].strValidFrom.value,"Please Select Leave To Date Greater Than Or Equal To Leave From Date.");
  hisValidator.addValidation("strAddrLeave", "req","Address During Leave is Mandatory");
  hisValidator.addValidation("strAddrLeave", "maxlen=100","Address During Leave can't have more than 100 characters");
  //hisValidator.addValidation("strPhoneNo", "req","Phone No. is Mandatory");
  hisValidator.addValidation("strPhoneNo", "maxlen=30","Phone No. can't have more than 30 digits");
  hisValidator.addValidation("strPatCondL", "req","Patient Condition at the time of leave is Mandatory");
  hisValidator.addValidation("strPatCondL", "maxlen=200","Patient Condition at the time of leave can't have more than 200 characters");
  hisValidator.addValidation("strRsn", "req","Leave Reason is Mandatory");
  hisValidator.addValidation("strRsn", "maxlen=50","Leave Reason can't have more than 50 characters");
  var retVal = hisValidator.validate();

 if(document.forms[0].strIsBedVacant.checked==true)
    document.forms[0].strIsBedVacant.value="1";
  else
    document.forms[0].strIsBedVacant.value="0";
 if(retVal==true)
 {
   document.forms[0].hmode.value="SAVE";
   hisValidator.clearAllValidations();
   hisValidator=null;
   document.forms[0].submit();
 }
 else
 {
    return false;
 }  
}

function hidePatDetails()
{
 document.getElementById("minusonLineId").style.display="none";
 document.getElementById("crNoId").style.display="none";
 document.getElementById("plusonLineId").style.display="block";
 document.getElementById("patDtlTld").style.display="none";
}
function showPatDetails()
{
 document.getElementById("plusonLineId").style.display="none";
 document.getElementById("crNoId").style.display="";
 document.getElementById("minusonLineId").style.display="block";
 document.getElementById("patDtlTld").style.display="block";
}