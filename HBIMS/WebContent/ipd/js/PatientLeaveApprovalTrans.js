function displayLeaveApprDtls()
{
  var o1=document.getElementsByName("strLeaveApproval");
  if(o1[0].checked==true)
  {
    document.getElementById("disBnRj").style.display="block";
    var o=document.getElementById("rmkMandate"); 
    o.innerHTML="Remarks:";
    o1[0].value="1";
    o1[1].value="0";
  }  
  if(o1[1].checked==true)
  {
    document.getElementById("disBnRj").style.display="block"; 
    var o=document.getElementById("rmkMandate"); 
    o.innerHTML="<font color='red'>*</font>Remarks:";
    o1[0].value="0";
    o1[1].value="1";
  }
}

function hlpOnLoad_LJ()
{     
          var cnt=document.forms[0].cnt.value;
          var frmName=document.forms[0].name;
          if(document.forms[0].strIsBedVacant.value=="1")
            document.forms[0].strIsBedVacant.checked=true;
          else
            document.forms[0].strIsBedVacant.checked=false;  
          if(document.forms[0].strCrNo.value>0)
          {
            var o1=document.getElementById("admDtlTld");
            if((o1.innerHTML).length>9)
            {
               document.getElementById("disBnR").style.display="block";
               document.getElementById("patTldglbdiv").style.display="block";
               document.getElementById("admDtlTldglbdiv").style.display="block";
               document.getElementById("patDtlTld").style.display="none";
               document.getElementById("crNoId").style.display="none";
               document.getElementById("admDtlTld").style.display="block";
               document.getElementById("transChng").style.display="block";
               document.getElementById("leaveApproval").style.display="block";
               document.getElementById("disBnRj").style.display="block";
               document.getElementById("LeaveId").style.display="block";
               document.getElementById("mandCRId").style.display="none";
               document.getElementById("searchImgId").style.display="none";
		    }
		    else
		    {
		       document.forms[0].strErrMsgString.value=document.forms[0].strGlbErrMsgTLD.value;
		       document.getElementById("patTldglbdiv").style.display="none";
		       document.getElementById("admDtlTldglbdiv").style.display="none";
               document.getElementById("patDtlTld").style.display="none";
               document.getElementById("crNoId").style.display="";
               document.getElementById("transChng").style.display="none";
               document.getElementById("admDtlTld").style.display="none";
               document.getElementById("disBnR").style.display="none";
               document.getElementById("leaveApproval").style.display="none";
               document.getElementById("disBnRj").style.display="none";
               document.getElementsByName("strErrMsgString")[0].value="Invalid CRNo/Data Not Found";
               document.getElementById("errMsg").style.display="block";
               document.getElementById("mandCRId").style.display="block";
               document.getElementById("searchImgId").style.display="none";
               if(frmName=="patientLeaveApprovalTransBean")
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

function admStatus()
{
  var mode="admStatus";
  if(typeof(document.forms[0].curAdmNo)!="undefined")
  {
   var admno=document.forms[0].curAdmNo.value;
  // if(document.forms[0].strCrNo.value!="")
   {
     var url="PatientLeaveApprovalTransCNT.cnt?hmode="+mode+"&admno="+admno+"&crNo="+document.forms[0].strCrNo.value;
     ajaxFunction(url,"777");
   }
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
 var frmName=document.forms[0].name;
 var hisValidator = new HISValidator(frmName); 
  hisValidator.addValidation("strCtDate","dtltet="+document.forms[0].strLeavReqToDate.value,"Request Expired!!!!.Current Date greater than Leave Request To Date.");
  hisValidator.addValidation("strValidTo", "req","Leave Sanctioned To Date Is  Mandatory");
  hisValidator.addValidation("strValidFrom","dtgtet="+document.forms[0].strLeavReqFrmDate.value,"Please Select Sanctioned From Date Greater Than Or Equal To Leave Request From Date.");
  hisValidator.addValidation("strValidFrom","dtltet="+document.forms[0].strLeavReqToDate.value,"Please Select Sanctioned From Date Less Than Or Equal To Leave Request To Date.");
  hisValidator.addValidation("strValidTo","dtgtet="+document.forms[0].strValidFrom.value,"Please Select Sanctioned To Date Greater Than Or Equal To Sanctioned From Date.");
  hisValidator.addValidation("strValidTo","dtltet="+document.forms[0].strLeavReqToDate.value,"Please Select Sanctioned To Date Less Than Or Equal To Leave Request To Date.");
  var o=document.getElementsByName("strLeaveApproval");
 if(o[1].checked)
 {
   document.forms[0].strLeaveStatusCode.value="3";  
   hisValidator.addValidation("strApprRejectRmk", "req","Leave Request Reject Remarks Is  Mandatory");
 }
 hisValidator.addValidation("strApprRejectRmk","maxlen=100","");
 if(o[0].checked)
   document.forms[0].strLeaveStatusCode.value="2";
 var retVal = hisValidator.validate();
 
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
 document.getElementById("plusonLineId").style.display="block";
 document.getElementById("patDtlTld").style.display="none";
 document.getElementById("crNoId").style.display="none";
}
function showPatDetails()
{
 document.getElementById("plusonLineId").style.display="none";
 document.getElementById("minusonLineId").style.display="block";
 document.getElementById("patDtlTld").style.display="block";
 document.getElementById("crNoId").style.display="";
}