function funroom_JoinEntry()
{ 
   var mode="ROOM";
   var myArray=new Array();
   var s=document.forms[0].curWrdBedCode.value;
   myArray=s.split("^");
   var temp=myArray[0]+"^"+document.forms[0].strRoomType.value;
   //alert("for Room->"+temp);
   var url=document.forms[0].cnt.value+"?hmode="+mode+"&modRoomType="+temp;
   ajaxFunction(url,"3");
}

function hour(obj, eve){
 	 	if(eve.keyCode != 13){
 		if (parseInt(obj.value ) > 12 || parseInt(obj.value) < 00 ){
     		alert("hours should be in between 00-12");
     		obj.value="";
 	 		}
 	 		else
 	 		{
 	 		//	focusDeathMin(obj);
 	 		}
 		}
 	}
 	function min(obj, eve){
 	 	if(eve.keyCode != 13){
 			if (parseInt(obj.value ) > 60 || parseInt(obj.value) < 00 ){
     			alert("Mintues should be in between 00-60");
     			obj.value="";
 	 		}
 	 		else
 	 		{
 	 			//focusDeathSec(obj);
 	 		}
 	 	
 		}
 	}
 	function sec(obj, eve){
 	 	if(eve.keyCode != 13){
 			if (parseInt(obj.value ) > 60 || parseInt(obj.value) < 00 ){
     			alert("Seconds should be in between 00-60");
     			obj.value="";
 	 		}
 	 			 	
 		}
 	}


function hlpOnLoad_LJ()
{     
          var cnt=document.forms[0].cnt.value;
          var frmName=document.forms[0].name;
         
          if(document.forms[0].strCrNo.value>0)
          {
            var o1=document.getElementById("admDtlTld");
            if((o1.innerHTML).length>9)
            {
              
               document.getElementById("patTldglbdiv").style.display="block";
               document.getElementById("admDtlTldglbdiv").style.display="block";
               document.getElementById("patDtlTld").style.display="none";
               document.getElementById("crNoId").style.display="none";
               document.getElementById("admDtlTld").style.display="block";
             //  document.getElementById("id1").style.display="block";
               document.getElementById("transChng").style.display="block";
               if(frmName=="patientLeaveBean")
               {
                 document.getElementById("patDtlTld").style.display="block";
                 document.getElementById("LeaveId").style.display="block";
               //  alert("jHLP->"+document.forms[0].strjHLP.value);
               //  alert("admStatCode->"+document.forms[0].strAdmStatCode.value)
               if(document.forms[0].strjHLP.value=="1")
                 {
                   // alert("bedVacant->"+document.forms[0].strIsBedVacant.value);
                    if(document.forms[0].strIsBedVacant.value=="1")
                        document.forms[0].strIsBedVacant.checked=true;
                    else
                        document.forms[0].strIsBedVacant.checked=false;  
                        
                   document.getElementById("transChngj").style.display="block";
                   document.getElementById("disBnRj").style.display="block";
                   document.getElementById("disBnR_LdtlJ").style.display="block";
                   document.getElementById("disBnR").style.display="none";
                   var o1=document.getElementById("entryDt");
                   var o2=document.getElementById("validFrm");
                   var o3=document.getElementById("validTo");
                   o1.innerHTML=document.forms[0].strEntryDate.value;
                   o2.innerHTML=document.forms[0].strValidFromDate.value;
                   o3.innerHTML=document.forms[0].strValidToDate.value;
                  // alert("strValidFromDate->"+document.forms[0].strValidFromDate.value);
                  // alert("strValidToDate->"+document.forms[0].strValidToDate.value);
                   var diff=dateDiff(document.forms[0].strValidFromDate.value,document.forms[0].strValidToDate.value);
                  // alert("diffLeave Dtls->"+diff);
                   document.forms[0].strDaysOnLeave.value=diff;
                   document.forms[0].strAddrLeave.readOnly=true;
                   document.forms[0].strPhoneNo.readOnly=true;
                   document.forms[0].strPatCondL.readOnly=true;
                   document.forms[0].strAdviceL.readOnly=true;
                   document.forms[0].strIsBedVacant.disabled=true;
                 }
                 else
                 {
                   document.forms[0].strDaysOnLeave.value="0";
                   document.forms[0].strAddrLeave.value="";
                   document.forms[0].strPhoneNo.value="";
                   document.forms[0].strPatCondL.value="";
                   document.forms[0].strAdviceL.value="";
                   document.forms[0].strIsBedVacant.value="0";
                   document.getElementById("transChngj").style.display="none";
                   document.getElementById("disBnRj").style.display="none";
                   document.getElementById("disBnR").style.display="block";
                 }
               }
               var currDtUtWrRmBd=document.forms[0].curDtUtWrRmBd.value;
	           var mode="transOf";
	          // alert("cnt->"+cnt);
	          // alert("strjHLPonLoad->"+document.forms[0].strjHLP.value);
		       var url=cnt+"?hmode="+mode+"&modName="+1+"&currDtl="+currDtUtWrRmBd+"&jHLP="+document.forms[0].strjHLP.value+"&isBedVacant="+document.forms[0].strIsBedVacant.value;
		       ajaxFunction(url,"111");
		    }
		    else
		    {
		       document.forms[0].strErrMsgString.value=document.forms[0].strGlbErrMsgTLD.value;
		       document.getElementById("patTldglbdiv").style.display="none";
		       document.getElementById("admDtlTldglbdiv").style.display="none";
		       document.getElementById("crNoId").style.display="";
               document.getElementById("patDtlTld").style.display="none";
               document.getElementById("id1").style.display="none";
               document.getElementById("transChng").style.display="none";
               document.getElementById("admDtlTld").style.display="none";
               document.getElementById("disBnR").style.display="none";
               document.getElementsByName("strErrMsgString")[0].value="Invalid CrNo/Data Not Found";
               document.getElementById("errMsg").style.display="block";
               if(frmName=="patientLeaveBean")
               {
                 document.getElementById("transChngj").style.display="none";
                 document.getElementById("LeaveId").style.display="none";
                 document.getElementById("disBnRj").style.display="none";
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
/*
function admStatus()
{
  var mode="admStatus";
  if(typeof(document.forms[0].curAdmNo)!="undefined")
  {
   var admno=document.forms[0].curAdmNo.value;
  // if(document.forms[0].strCrNo.value!="")
   {
     var url="PatientLeaveCNT.cnt?hmode="+mode+"&admno="+admno+"&crNo="+document.forms[0].strCrNo.value;
     ajaxFunction(url,"777");
   }
  }   
}*/

function save()
{
 var frmName=document.forms[0].name;
 var hisValidator = new HISValidator(frmName); 
 if(document.forms[0].strAdmStatCode.value=="12")
 { 
  hisValidator.addValidation("strValidTo", "req","Probable Joining Date Is  Mandatory");
  hisValidator.addValidation("strSactionDate","dtltet="+document.forms[0].strCtDate.value,"Please Select Sanction Date Less Than Or Equal To Current Date.");
  hisValidator.addValidation("strValidTo","dtgtet="+document.forms[0].strCtDate.value,"Please Select Joining Date Greater Than Or Equal To Current Date.");
  hisValidator.addValidation("strAddrLeave", "req","Address During Leave is Mandatory");
  hisValidator.addValidation("strAddrLeave", "maxlen=100","Address During Leave can't have more than 100 characters");
  hisValidator.addValidation("strPhoneNo", "req","Phone No. is Mandatory");
  hisValidator.addValidation("strPhoneNo", "maxlen=30","Phone No. can't have more than 30 digits");
  hisValidator.addValidation("strPatCondL", "req","Patient Condition at the time of leave is Mandatory");
 if(document.forms[0].strRemarksOthersOfflineMandatoryFlag.value==1)	 
  	hisValidator.addValidation("strAdviceL", "req","Advice given at the time of leave is Mandatory");
  hisValidator.addValidation("strRmkL", "dontselect=0","Leave Sanctioned By is Mandatory");
  hisValidator.addValidation("strPatCondL", "maxlen=200","Patient Condition at the time of leave can't have more than 200 characters");
  hisValidator.addValidation("strRsnL", "req","Leave Reason is Mandatory");
  hisValidator.addValidation("strRsnL", "maxlen=50","Leave Reason can't have more than 50 characters");
  hisValidator.addValidation("strAdviceL", "maxlen=100","Leave Advice can't have more than 100 characters");
 }
 else
 {
  hisValidator.addValidation("strShiftHour_J", "req","Hour Is  Mandatory");
  hisValidator.addValidation("strShiftMin_J", "req","Minutes Is  Mandatory");
  hisValidator.addValidation("strShiftSec_J", "req","Seconds Is  Mandatory");
  hisValidator.addValidation("strPatCondJ", "req","Patient Condition at the time of Joining Is  Mandatory");
  hisValidator.addValidation("strRmkJ", "dontselect=0","Join Accepted By Is  Mandatory");
   if(document.forms[0].strRemarksOthersJoinMandatoryFlag.value==1)	 
  	hisValidator.addValidation("strRsnJ", "req","Join Remarks Is  Mandatory");
 } 
 document.forms[0].strIsBedVacant.disabled=false;
 //alert("isBedVacant..SAVE->"+document.forms[0].strIsBedVacant.value);
 if(document.forms[0].strIsBedVacant.checked==true)
 {
    document.forms[0].strIsBedVacant.value="1";
    hisValidator.addValidation("strRoomType", "dontselect=0","Please select Room Type!!");
    hisValidator.addValidation("strRoom", "dontselect=0","Please select a Room!!");
    hisValidator.addValidation("strBedType", "dontselect=0","Please select Bed Type!!");
    hisValidator.addValidation("strBed", "dontselect=0","Please select a bed!!");
 }
  else
    document.forms[0].strIsBedVacant.value="0";  
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
function saveIPD()
{
 var frmName=document.forms[0].name;
 var hisValidator = new HISValidator(frmName); 
 if(document.forms[0].strAdmStatCode.value=="12")
 { 
  hisValidator.addValidation("strValidTo", "req","Probable Joining Date Is  Mandatory");
  hisValidator.addValidation("strSactionDate","dtltet="+document.forms[0].strCtDate.value,"Please Select Sanction Date Less Than Or Equal To Current Date.");
  hisValidator.addValidation("strValidTo","dtgtet="+document.forms[0].strCtDate.value,"Please Select Joining Date Greater Than Or Equal To Current Date.");
  hisValidator.addValidation("strAddrLeave", "req","Address During Leave is Mandatory");
  hisValidator.addValidation("strAddrLeave", "maxlen=100","Address During Leave can't have more than 100 characters");
  hisValidator.addValidation("strPhoneNo", "req","Phone No. is Mandatory");
  hisValidator.addValidation("strPhoneNo", "maxlen=30","Phone No. can't have more than 30 digits");
  hisValidator.addValidation("strPatCondL", "req","Patient Condition at the time of leave is Mandatory");
 if(document.forms[0].strRemarksOthersOfflineMandatoryFlag.value==1)	 
  	hisValidator.addValidation("strAdviceL", "req","Advice given at the time of leave is Mandatory");
  hisValidator.addValidation("strRmkL", "dontselect=0","Leave Sanctioned By is Mandatory");
  hisValidator.addValidation("strPatCondL", "maxlen=200","Patient Condition at the time of leave can't have more than 200 characters");
  hisValidator.addValidation("strRsnL", "req","Leave Reason is Mandatory");
  hisValidator.addValidation("strRsnL", "maxlen=50","Leave Reason can't have more than 50 characters");
  hisValidator.addValidation("strAdviceL", "maxlen=100","Leave Advice can't have more than 100 characters");
 }
 else
 {
  hisValidator.addValidation("strShiftHour_J", "req","Hour Is  Mandatory");
  hisValidator.addValidation("strShiftMin_J", "req","Minutes Is  Mandatory");
  hisValidator.addValidation("strShiftSec_J", "req","Seconds Is  Mandatory");
  hisValidator.addValidation("strPatCondJ", "req","Patient Condition at the time of Joining Is  Mandatory");
  hisValidator.addValidation("strRmkJ", "dontselect=0","Join Accepted By Is  Mandatory");
   if(document.forms[0].strRemarksOthersJoinMandatoryFlag.value==1)	 
  	hisValidator.addValidation("strRsnJ", "req","Join Remarks Is  Mandatory");
 } 
 document.forms[0].strIsBedVacant.disabled=false;
 //alert("isBedVacant..SAVE->"+document.forms[0].strIsBedVacant.value);
 if(document.forms[0].strIsBedVacant.checked==true)
 {
    document.forms[0].strIsBedVacant.value="1";
    hisValidator.addValidation("strRoomType", "dontselect=0","Please select Room Type!!");
    hisValidator.addValidation("strRoom", "dontselect=0","Please select a Room!!");
    hisValidator.addValidation("strBedType", "dontselect=0","Please select Bed Type!!");
    hisValidator.addValidation("strBed", "dontselect=0","Please select a bed!!");
 }
  else
    document.forms[0].strIsBedVacant.value="0";  
 var retVal = hisValidator.validate();   
 if(retVal==true)
 {
 	
   document.forms[0].hmode.value="SAVEIPD";
   hisValidator.clearAllValidations();
   hisValidator=null;
   document.forms[0].submit();
 }
 else
 {
    return false;
 }  
}