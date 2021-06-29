function callSearchWindow()
{
  var o=document.getElementsByName("strIs_Leave_Join");
  if(o[0].checked)
    showPatientListingWindow('2',document.forms[0].strCrNo,'setSelectedCrNo');
  if(o[1].checked)
    showPatientListingWindow('3',document.forms[0].strCrNo,'setSelectedCrNo');
}

function goFunc_recordEntry(frmName)        //  for CR No. field validation
{
		var hisValidator = new HISValidator(frmName); 
		var typ=hisValidator.type;
	 	hisValidator.addValidation("strCrNo", "req", "CR No. is a Mandatory Field" );
	    hisValidator.addValidation("strCrNo", "minlen=13", "CR No. must be 13 Digits" );
        var retVal = hisValidator.validate(); 
	    document.forms[0].strTempVal.value=document.forms[0].strCrNo.value;
	    var o=document.getElementsByName("strIs_Leave_Join");
	    if(o[0].checked)
	      document.forms[0].strTempLeaveJoin.value="leave";
	    if(o[1].checked)
	      document.forms[0].strTempLeaveJoin.value="join"; 
	    if(retVal){
				document.forms[0].hmode.value="GO";
				document.forms[0].submit();
				adt_create_loading_msg();//loading_msg
		}else{
		    return false;
		}
}

function leaveJoin()
{
               document.forms[0].strCrNo.value="";
               document.forms[0].strCrNo.readOnly=false;
               document.forms[0].strCrNo.focus();
               document.getElementsByName("strErrMsgString")[0].value="";
               var objErrMsg=document.getElementById("errMsg");
               objErrMsg.innerHTML="";
		       document.getElementById("admDtlTldglbdiv").style.display="none";
               document.getElementById("patDtlTld").style.display="none";
               document.getElementById("id1").style.display="none";
               document.getElementById("transChng").style.display="none";
               document.getElementById("admDtlTld").style.display="none";
               document.getElementById("disBnR").style.display="none";
               document.getElementById("transChngj").style.display="none";
               document.getElementById("LeaveId").style.display="none";
               document.getElementById("disBnRj").style.display="none";
               document.getElementById("transChngl").style.display="none";
               document.getElementById("id0").style.display="none";
}

function leave_join()
{
          if(document.forms[0].strCrNo.value>0)
          { 
            var o=document.getElementsByName("strIs_Leave_Join");
            var cnt="PatientLeaveJoinRecordTransCNT.cnt";
            var currDtUtWrRmBd=document.forms[0].curDtUtWrRmBd.value;
	        var mode="transOf";
		    var url=cnt+"?hmode="+mode+"&modName="+1+"&currDtl="+currDtUtWrRmBd+"&isBedVacant="+document.forms[0].strIsBedVacant.value;
		    ajaxFunction(url,"111");
            if(o[0].checked)
            {
             document.getElementById("transChngj").style.display="none";
             document.getElementById("disBnRj").style.display="none";
             document.getElementById("id1").style.display="none";
             document.getElementById("transChngl").style.display="block";
             document.getElementById("id0").style.display="block";
            } 
            if(o[1].checked)
            {
             document.getElementById("transChngj").style.display="block";
             document.getElementById("disBnRj").style.display="block";
             document.getElementById("id1").style.display="block";
             
             document.getElementById("transChngl").style.display="none";
             document.getElementById("id0").style.display="none";
            }
          }
          else
          {
             document.getElementById("transChngj").style.display="none";
             document.getElementById("disBnRj").style.display="none";
             document.getElementById("id1").style.display="none";
             document.getElementById("transChngl").style.display="none";
             document.getElementById("id0").style.display="none";
          } 
}
function leave_join_ipd()
{
          if(document.forms[0].strCrNo.value>0)
          { 
            
             document.getElementById("transChngj").style.display="block";
             document.getElementById("disBnRj").style.display="block";
             document.getElementById("id1").style.display="block";
             
             document.getElementById("transChngl").style.display="none";
             document.getElementById("id0").style.display="none";
            
          }
          else
          {
             document.getElementById("transChngj").style.display="none";
             document.getElementById("disBnRj").style.display="none";
             document.getElementById("id1").style.display="none";
             document.getElementById("transChngl").style.display="none";
             document.getElementById("id0").style.display="none";
          } 
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

function funroom_JoinEntry()
{ 
   var mode="ROOM";
   var myArray=new Array();
   var s=document.forms[0].curWrdBedCode.value;
   myArray=s.split("^");
   var temp=myArray[0]+"^"+document.forms[0].strRoomType.value;
   var url=document.forms[0].cnt.value+"?hmode="+mode+"&modRoomType="+temp+'&ageCode='+document.forms[0].strAgeUnit.value+'&sexCode='+ document.forms[0].strSexCode.value+'&age='+document.forms[0].strAge.value+'&treatmentCategCode='+document.forms[0].strCategoryCode.value+"&deptUnitCode="+document.forms[0].curDept_Unt_RomCode.value.split("^")[1]+"&strCrNo="+document.forms[0].strCrNo.value;
   ajaxFunction(url,"3");
}
     
function hlpOnLoad_LJ()
{
          var o=document.getElementsByName("strIs_Leave_Join");
          var tmp=document.forms[0].strTempLeaveJoin.value;
          if(tmp=="leave")
            o[0].checked=true;
          if(tmp=="join")
            o[1].checked=true; 
          if(document.forms[0].strIsBedVacant.value=="1")
            document.forms[0].strIsBedVacant.checked=true;
          else
            document.forms[0].strIsBedVacant.checked=false;  
          var cnt=document.forms[0].cnt.value;
          var frmName=document.forms[0].name;
          if(document.forms[0].strCrNo.value>0)
          {
            var o1=document.getElementById("admDtlTld");
            if((o1.innerHTML).length>9)
            {
               document.getElementById("disBnR").style.display="block";
               document.getElementById("patTldglbdiv").style.display="block";
               document.getElementById("admDtlTldglbdiv").style.display="block";
               //document.getElementById("patDtlTld").style.display="none";
               document.getElementById("crNoId").style.display="none";
               document.getElementById("admDtlTld").style.display="block";
               document.getElementById("transChng").style.display="block";
               if(frmName=="patientLeaveJoinRecordTransBean")
               {
                 document.getElementById("LeaveId").style.display="block";
               }
              
               document.getElementById("transChngj").style.display="block";
               document.getElementById("disBnRj").style.display="block";
		    }
		    else
		    {
		       document.forms[0].strErrMsgString.value=document.forms[0].strGlbErrMsgTLD.value;
		       document.getElementById("patTldglbdiv").style.display="none";
		       document.getElementById("admDtlTldglbdiv").style.display="none";
               //document.getElementById("patDtlTld").style.display="none";
               document.getElementById("crNoId").style.display="";
               document.getElementById("id1").style.display="none";
               document.getElementById("transChng").style.display="none";
               document.getElementById("admDtlTld").style.display="none";
               document.getElementById("disBnR").style.display="none";
               document.getElementsByName("strErrMsgString")[0].value="Invalid CR No/Data Not Found";
               document.getElementById("errMsg").style.display="block";
               if(frmName=="patientLeaveJoinRecordTransBean")
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

function save()
{
 var frmName=document.forms[0].name;
 var hisValidator = new HISValidator(frmName); 
 if(document.forms[0].strTempLeaveJoin.value=="leave")
 {
   hisValidator.addValidation("strAdviceL", "req","Advice given at the time of Leave  Is  Mandatory");
    document.forms[0].strLeavingDt.value=document.forms[0].strCtDate.value;
 }
 else
 {
   hisValidator.addValidation("strPatCondJ", "req","Patient Condition at the time of Joining Is  Mandatory");
   hisValidator.addValidation("strPatCondJ", "maxlen=200","Patient Condition at the time of Joining can't have more than 200 characters");
   //hisValidator.addValidation("strRoomType", "dontselect=0","Please select Room Type!!");
   hisValidator.addValidation("strRoom", "dontselect=0","Please select a Room!!");
   //hisValidator.addValidation("strBedType", "dontselect=0","Please select Bed Type!!");
   hisValidator.addValidation("strBed", "dontselect=0","Please select a bed!!");
   hisValidator.addValidation("strRmkJ", "dontselect=0","Join Accepted By Is  Mandatory");
   hisValidator.addValidation("strRsn_Entry", "req","Joining Remarks Is  Mandatory");
   hisValidator.addValidation("strRsn_Entry", "maxlen=100","Joining Remarks can't have more than 100 characters");
   document.forms[0].strEntryDate.value=document.forms[0].strCtDate.value;
 }
 document.forms[0].strIsBedVacant.disabled=false;
 if(document.forms[0].strIsBedVacant.checked)
    document.forms[0].strIsBedVacant.value="1";
 else
    document.forms[0].strIsBedVacant.value="0";   
 var o=document.getElementsByName("strIs_Leave_Join");
 if(o[0].checked)
    document.forms[0].strTempLeaveJoin.value="leave";
 if(o[1].checked)
    document.forms[0].strTempLeaveJoin.value="join";
    
 var retVal = hisValidator.validate();
 
 /******time validation******/
 
 /*var timeStatFlg=1;
 var datwidTime=document.forms[0].strCtDateTime.value;
 var myTimeArr=new Array();
 myTimeArr=datwidTime.split("/");
 var netTime=myTimeArr[1];
 myTimeArr=netTime.split(":");
 var am_entry = document.forms[0].strShiftAmPm_L.value;
 if(am_entry=="2")
 {
   var hh_entry = parseInt(document.forms[0].strShiftHour_L.value) +12; //entry Hour
 }
 else
 {
   var hh_entry = document.forms[0].strShiftHour_L.value; //entry Hour
 }  
 var mm_entry = document.forms[0].strShiftMin_L.value;  //entry Mint
 var ss_entry = document.forms[0].strShiftSec_L.value;  //entry Secs
 var hh_curnt = myTimeArr[0]; //current Hour
 var mm_curnt = myTimeArr[1]; //current Mint
 var ss_curnt = myTimeArr[2]; //current Secs
 if(parseInt(hh_curnt)>=parseInt(hh_entry))
 {
   timeStatFlg=0;
   if(parseInt(hh_curnt)==parseInt(hh_entry))
   {
      if(parseInt(mm_curnt)>=parseInt(mm_entry)) 
      {
        timeStatFlg=0;
         if(parseInt(mm_curnt)==parseInt(mm_entry))
         {
             if(parseInt(ss_curnt)>parseInt(ss_entry))
             {
               timeStatFlg=0;
             }
             else
             {
               timeStatFlg=1;
             }
         }     
      }
      else
      {
        timeStatFlg=1;
      }       
   }
 }
 else
 {
   timeStatFlg=1;
 }
 if(o[0].value=="leave")
 if(timeStatFlg==1)
 {
   alert("Leaving Time cannot be greater than current Time!!!");
   retVal=false;
 }*/
 
 /******time validation******/
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
 if(document.forms[0].strTempLeaveJoin.value=="leave")
 {
   hisValidator.addValidation("strAdviceL", "req","Advice given at the time of Leave  Is  Mandatory");
    document.forms[0].strLeavingDt.value=document.forms[0].strCtDate.value;
 }
 else
 {
   hisValidator.addValidation("strPatCondJ", "req","Patient Condition at the time of Joining Is  Mandatory");
   hisValidator.addValidation("strPatCondJ", "maxlen=200","Patient Condition at the time of Joining can't have more than 200 characters");
   //hisValidator.addValidation("strRoomType", "dontselect=0","Please select Room Type!!");
   hisValidator.addValidation("strRoom", "dontselect=0","Please select a Room!!");
   //hisValidator.addValidation("strBedType", "dontselect=0","Please select Bed Type!!");
   hisValidator.addValidation("strBed", "dontselect=0","Please select a bed!!");
   hisValidator.addValidation("strRmkJ", "dontselect=0","Join Accepted By Is  Mandatory");
   hisValidator.addValidation("strRsn_Entry", "req","Joining Remarks Is  Mandatory");
   hisValidator.addValidation("strRsn_Entry", "maxlen=100","Joining Remarks can't have more than 100 characters");
   document.forms[0].strEntryDate.value=document.forms[0].strCtDate.value;
 }
 document.forms[0].strIsBedVacant.disabled=false;
 if(document.forms[0].strIsBedVacant.checked)
    document.forms[0].strIsBedVacant.value="1";
 else
    document.forms[0].strIsBedVacant.value="0";   
 var o=document.getElementsByName("strIs_Leave_Join");
 if(o[0].checked)
    document.forms[0].strTempLeaveJoin.value="leave";
 if(o[1].checked)
    document.forms[0].strTempLeaveJoin.value="join";
    
 var retVal = hisValidator.validate();
 
 /******time validation******/
 
 /*var timeStatFlg=1;
 var datwidTime=document.forms[0].strCtDateTime.value;
 var myTimeArr=new Array();
 myTimeArr=datwidTime.split("/");
 var netTime=myTimeArr[1];
 myTimeArr=netTime.split(":");
 var am_entry = document.forms[0].strShiftAmPm_L.value;
 if(am_entry=="2")
 {
   var hh_entry = parseInt(document.forms[0].strShiftHour_L.value) +12; //entry Hour
 }
 else
 {
   var hh_entry = document.forms[0].strShiftHour_L.value; //entry Hour
 }  
 var mm_entry = document.forms[0].strShiftMin_L.value;  //entry Mint
 var ss_entry = document.forms[0].strShiftSec_L.value;  //entry Secs
 var hh_curnt = myTimeArr[0]; //current Hour
 var mm_curnt = myTimeArr[1]; //current Mint
 var ss_curnt = myTimeArr[2]; //current Secs
 if(parseInt(hh_curnt)>=parseInt(hh_entry))
 {
   timeStatFlg=0;
   if(parseInt(hh_curnt)==parseInt(hh_entry))
   {
      if(parseInt(mm_curnt)>=parseInt(mm_entry)) 
      {
        timeStatFlg=0;
         if(parseInt(mm_curnt)==parseInt(mm_entry))
         {
             if(parseInt(ss_curnt)>parseInt(ss_entry))
             {
               timeStatFlg=0;
             }
             else
             {
               timeStatFlg=1;
             }
         }     
      }
      else
      {
        timeStatFlg=1;
      }       
   }
 }
 else
 {
   timeStatFlg=1;
 }
 if(o[0].value=="leave")
 if(timeStatFlg==1)
 {
   alert("Leaving Time cannot be greater than current Time!!!");
   retVal=false;
 }*/
 
 /******time validation******/
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
function hidePatDetails()
{
 document.getElementById("minusonLineId").style.display="none";
 document.getElementById("crNoId").style.display="none";
 document.getElementById("plusonLineId").style.display="block";
 //document.getElementById("patDtlTld").style.display="none";
}
function showPatDetails()
{
 document.getElementById("plusonLineId").style.display="none";
 document.getElementById("crNoId").style.display="";
 document.getElementById("minusonLineId").style.display="block";
 //document.getElementById("patDtlTld").style.display="block";
}