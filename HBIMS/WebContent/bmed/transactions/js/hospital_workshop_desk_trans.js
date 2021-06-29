var sparePartInfo = [];
var sparePartDtl = [];
var sparePartRecvdDtl = [];
var spareUpdateInfo = [];;

var index = 0;
var sprPtIndex = 0;
var spareCost = 0
var flag = 0;
function userDefinedOnLoadFunc()
{
	 
}
function buttonLogicsOnRecordCheck1(these)
{  

     //  alert(document.forms[0].chk.checked);
       var chkObj  = document.getElementsByName("chk");
       var count = parseInt("0");
   	   for(var i=0; i<chkObj.length; i++) 
   	   {
   	   //  alert(chkObj[i].checked);
   	  		if(chkObj[i].checked)
   	  		{
   	 		  count = count + 1;
   	 	    }	
   	 	     	 	       	 	    	
   	   }
   	   if(count > 1)
   	   {
   	     alert("Plz Select Single Record at a time!!!!!");
   	     for(var i=0; i<chkObj.length; i++) 
   	     {
   	       	chkObj[i].checked=false;
   	  	 }	 	
   	  	 disableButton("Acknowledgment");
	     disableButton("Schedule");
	     disableButton("Attend");
	     disableButton("Close");
	     disableButton("View");
   	     return false;
   	   }
       else if(count==1)
       {   
         
         enableButton("View");
        //disableButton("Generate");
        // var tmp = document.getElementById("comboid2").value.split("^");
         //var reqType = tmp[1];
         var status  = document.getElementById("comboid2").value;
         if(status=="3")
         {
         	enableButton("Acknowledgment");
         	disableButton("Schedule");
	     	disableButton("Attend");
	     	disableButton("Close");
	     	disableButton("View");
         	return;
         }
         if(status=="4")
         {
         	enableButton("Schedule");
         	disableButton("Acknowledgment");
         	disableButton("Attend");
	     	disableButton("Close");
	     	disableButton("View");
         	return;
         }
          if(status=="5")
         {
         	enableButton("Attend");
         	disableButton("Acknowledgment");
         	disableButton("Schedule");
	     	disableButton("Close");
	     	disableButton("View");
         	return;
         }
         if(status=="6")
         {
         	disableButton("Attend");
         	disableButton("Acknowledgment");
         	disableButton("Schedule");
	     	disableButton("Close");
	     	disableButton("View");
         	return;
         }
         if(status=="10")
         {
         	enableButton("Close");
         	disableButton("Acknowledgment");
         	disableButton("Schedule");
	     	disableButton("Attend");
	     	disableButton("View");
         	return;
         }
          if(status=="8")
         {
         	enableButton("View");
         	disableButton("Acknowledgment");
         	disableButton("Schedule");
	     	disableButton("Attend");
	     	disableButton("Close");
         	return;
         }
       }
       else
       {
       		disableButton("Acknowledgment");
         	disableButton("Schedule");
	     	disableButton("Attend");
	     	disableButton("Close");
	     	disableButton("View");
       }
}
function buttonLogicsOnRecordCheck(these)
{
    var checkCount = parseInt("0");
	var check = document.getElementsByName("chk");
	
	for(i=0;i<check.length;i++)
	{
		if(check[i].checked==true)
		{
			checkCount++;
		}			
	}
	try
	{
		//alert("checkCount"+checkCount);
		if(checkCount=='1')
		{
		var status  = document.getElementById("comboid2").value;
         if(status=="3")
         {
         	enableButton("Acknowledgment");
         	disableButton("Schedule");
	     	disableButton("Attend");
	     	disableButton("Service Action");
	     	disableButton("Close");
	     	disableButton("View");
         	return;
         }
         if(status=="4")
         {
         	enableButton("Schedule");
         	disableButton("Acknowledgment");
         	disableButton("Attend");
         	disableButton("Service Action");
	     	disableButton("Close");
	     	disableButton("View");
         	return;
         }
          if(status=="5")
         {
         	enableButton("Attend");
         	disableButton("Acknowledgment");
         	disableButton("Schedule")
         	disableButton("Service Action");;
	     	disableButton("Close");
	     	disableButton("View");
         	return;
         }
         if(status=="6")
         {
         	enableButton("Service Action");
         	disableButton("Attend");
         	disableButton("Acknowledgment");
         	disableButton("Schedule");
	     	disableButton("Close");
	     	disableButton("View");
         	return;
         }
         if(status=="10")
         {
         	enableButton("Close");
         	disableButton("Acknowledgment");
         	disableButton("Schedule");
	     	disableButton("Attend");
	     	disableButton("Service Action");
	     	disableButton("View");
         	return;
         }
          if(status=="8")
         {
         	enableButton("View");
         	disableButton("Acknowledgment");
         	disableButton("Schedule");
	     	disableButton("Attend");
	     	disableButton("Service Action");
	     	disableButton("Close");
         	return;
         }
		}
		else
		{
			for(i=0;i<check.length;i++)
			{
				check[i].checked=false;
			}
			if(checkCount!='0')
			{
				alert("Please Select One Record!!!");	
				disableButton("Acknowledgment");
	         	disableButton("Schedule");
		     	disableButton("Attend");
		     	disableButton("Service Action");
		     	disableButton("Close");
		     	disableButton("View");
			}
			
			return false;
		}
		
	}
	catch(Err)
	{
		alert(Err);
	}
}


function buttonLogicsOnClick1(modeNo, mode , display)
{
	
	var checkCount = parseInt("0");
	var check = document.getElementsByName("chk");
	
	for(i=0;i<check.length;i++)
	{
		if(check[i].checked==true)
		{
			checkCount++;
		}			
	}
	try
	{
		
		if(checkCount=='1')
		{
 
   	 	   	  add(mode); 	
		}
		else
		{
			for(i=0;i<check.length;i++)
			{
				check[i].checked=false;
			}
			alert("Please Select One Record!!!");
			return false;
		}
	}
	catch(Err)
	{
		alert(Err);
	}
				
		

}
function buttonLogicsOnClick2(modeNo, mode , display)
{
       if(modeNo != 7)
		{
		
		   var chkObj = document.getElementsByName("chk");
   	 
   			 var count = parseInt("0");
   	 
   	 		 for(var i=0; i<chkObj.length; i++) 
   	 		 {
   	 			if(chkObj[i].checked)
   	 			{
   	 				count = count + 1;
   	 		    }		
   	 		
   	 		 }
   	 	   	  add(mode); 			
		}
		else
		{
			 //add(mode);
		}

}

function buttonLogicsOnClickCancel(modeNo, mode , display)
{
	if(document.getElementById("comboid3").value !="0")
	{
				alert("Please Select Status");
				document.getElementById("comboid3").focus();
				return;
	}
    var chkObj = document.getElementsByName("chk");
   	 
   	var count = parseInt("0");
   	 
   	for(var i=0; i<chkObj.length; i++) 
    {
   	 	
   	 				if(chkObj[i].checked)
   	 				{
   	 			
   	 					count = count + 1;
   	 			
   	 				}		
   	 		
   	}
   	if(count!=0 && count == '1') 
   	{
   	     res=prompt("ENTER REMARKS FOR CANCELATION!","");
         if(!res=="")
         {
           var conf = confirm("Are you sure !!!");
           if(conf == true)
           {
             var chkObj = document.getElementsByName("chk");  
             for(var i=0; i<chkObj.length; i++) 
   	 	     {
   	 	       if(chkObj[i].checked)
   	 		   {
   	 		       chkObj[i].value = chkObj[i].value+"^"+res;
   	 		   }		
   	 		 }
   	 		 add(mode);
           }
           else
           {
             return false;
           }
           
          }
          else
          {
           if(res=="")
           { 
            alert("Enter remarks for rejection");
           } 
          }
      }
      else
      {
           if(res=="")
           { 
            alert("Enter remarks for rejection");
           } 
           else
           {
             if(count>1)
                   alert("Please Select Single Record at a Time!!!");
               else
                   alert("Please Select Record !!!!!");
             
           } 
          return false;
      }
   	
			
}		




function buttonLogicsOnClickReturn(modeNo, mode , display)
{
	   if(modeNo != 7)
		{
		   if(document.getElementById("comboid0").value =="0")
		   {
				alert("Please Select A Store Name");
				document.getElementById("comboid0").focus();
				return;
			}
			
			if(document.getElementById("comboid1").value =="0")
			{
				alert("Please Select A Item Category");
				document.getElementById("comboid1").focus();
				return;
			}
			
			if(document.getElementById("comboid2").value =="0")
			{
				alert("Please Select A Indent Type");
				document.getElementById("comboid2").focus();
				return;
			}
			
			if(document.getElementById("comboid3").value =="0")
			{
				alert("Please Select Status");
				document.getElementById("comboid3").focus();
				return;
			}
    		 var chkObj = document.getElementsByName("chk");
   	 
   			 var count = parseInt("0");
   	 
   	 		 for(var i=0; i<chkObj.length; i++) 
   	 		 {
   	 	
   	 				if(chkObj[i].checked)
   	 				{
   	 			
   	 					count = count + 1;
   	 			
   	 				}		
   	 		
   	 			}
   	 		
   	 	 if(count > 1)
   		 {
   	 		alert("Please Select A Single Record");
   	 		return false;
   		  }
   		  else
   		  {
   		     add(mode);
   		  }
   		 
   	    }
   	    else
   	    {
   	     return false;
   	    }
   	    
  } 	    

function buttonLogicsOnClickPrint(modeNo, mode , display)
{
  var retVal = false;
 
	    if(modeNo == 5)
		{
		   if(document.getElementById("comboid0").value =="0")
		   {
				alert("Please Select A Store Name");
				document.getElementById("comboid0").focus();
				disableButton("Print");
				return;
			}
			else			
			if(document.getElementById("comboid1").value =="0")
			{
				alert("Please Select A Item Category");
				document.getElementById("comboid1").focus();
				disableButton("Print");
				return;
			}
			else
			if(document.getElementById("comboid2").value =="0")
			{
				alert("Please Select A Request Type");
				document.getElementById("comboid2").focus();
				disableButton("Print");
				return;
			}
			
    		 retVal = true;
   		     
   		}
   		
   		if(retVal)
   		{
   		  enableButton("Print");
   		  document.forms[0].target = "_blank";
   		  add(mode);
   		}
   		else
   	    {
   	     return false;
   	    }
} 	    
/************************************************************/
function validate1() 
{
	 var hisValidator = new HISValidator("hospitalWorkshopDeskFB");
	  var complaintStatus = document.getElementById("strComplaintStatus").value;
      var retVal=false;
     if(complaintStatus=="3")
	{
      //hisValidator.addValidation("strIsHEMAcknowledge","req","Time Of Escalation is a mandatory field");
      hisValidator.addValidation("strAckRemarks","req","Remarks is a mandatory field");
  	  retVal = hisValidator.validate(); 
      hisValidator.clearAllValidations();
      }	
	if(retVal)
	{
	//alert(complaintStatus);
		 document.forms[0].hmode.value = "SAVEACKNOWLEDGE";
	     //document.forms[0].target = "_self";
	     document.forms[0].submit();
	}
	if(complaintStatus=="4")
	{		
		hisValidator.addValidation("strScheduleDate","req","Expected Visit Date is a mandatory field");
		hisValidator.addValidation("strScheduleTime","dontselect=0","Expected Visit Time is a mandatory field");
		hisValidator.addValidation("strServiceEngineerRemarks","req","Complaint Redressal Details is a mandatory field");
		hisValidator.addValidation("strServiceEngineerRemarks", "maxlen=100","Complaint Redressal Details cannot be greater than 100 Characters");
		hisValidator.addValidation("strIntimationDate","req","Intimation Date is a mandatory field");
		hisValidator.addValidation("strIntimationTime","dontselect=0","Intimation Time is a mandatory field");
		hisValidator.addValidation("strContactPerson","req","Person Contacted is a mandatory field");
		hisValidator.addValidation("strContactNo","req","Person Contact No. is a mandatory field");
		hisValidator.addValidation("strCompanyName","req","Company Name and Address is a mandatory field");
		hisValidator.addValidation("strCompanyName", "maxlen=300","Company Name and Address Field cannot be greater than 300 Characters");
		hisValidator.addValidation("strCommunicationId","dontselect=0","Communication Medium is a mandatory field");
		//hisValidator.addValidation("strCompanyAddress","req","Company Email is a mandatory field");
		if(document.forms[0].strCompanyAddress.value.toUpperCase()=="NA")
		{
			;
		}
		else
		{
			hisValidator.addValidation("strCompanyAddress","req","Company Email is a mandatory field");
			hisValidator.addValidation("strCompanyAddress", "email","Please Enter a Valid Email ID");
		}
		if(document.forms[0].strScheduleDate.value!="") 
		{
			hisValidator.addValidation("strScheduleDate", "dtgtet="+document.forms[0].strComplaintDate.value.substr(0,12),"Schedule Date should be Greater then Complaint Date");
		}

		if(document.forms[0].strIntimationDate.value!="") 
		{
			hisValidator.addValidation("strScheduleDate", "dtgtet="+document.forms[0].strIntimationDate.value,"Intimation Date should be Less than or Equal to Expected Visit Date");
		}
  	  	retVal = hisValidator.validate(); 
      	hisValidator.clearAllValidations();
      	if(retVal)
      	{
		 document.forms[0].hmode.value = "SAVESCHEDULE";
	     //document.forms[0].target = "_self";
	     document.forms[0].submit();
	    }
	}
	if(complaintStatus=="5")
	{		
		hisValidator.addValidation("strEngineerName","req","Service Engineer Name is a mandatory field");
		hisValidator.addValidation("strMobileNo","req","Service Engineer Mobile Number is a mandatory field");
		hisValidator.addValidation("strAttendDate","req","Attend Date is a mandatory field");
		hisValidator.addValidation("strAttendTime","dontselect=0","Attend Time is a mandatory field");
		hisValidator.addValidation("strActualProblemDesc","req","Actual Problem Description is a mandatory field");
		hisValidator.addValidation("strActualProblemDesc", "maxlen=100","Actual Problem Description Field cannot be greater than 100 Characters");
		
		if(document.forms[0].strIntimationDate.value!="") 
		hisValidator.addValidation("strAttendDate", "dtgtet="+document.forms[0].strIntimationDate.value,"Attend Date should be Greater than or Equal to Intimation Date");
	
		retVal = hisValidator.validate(); 
      	hisValidator.clearAllValidations();
      	if(retVal)
      	{
		document.forms[0].hmode.value = "SAVEATTEND";
	     //document.forms[0].target = "_self";
	     document.forms[0].submit();
	    }
	}
	if(complaintStatus=="6")
	{		
		hisValidator.addValidation("strProblemDescription","req","Problem Description is a mandatory field");
		hisValidator.addValidation("strProblemDescription", "maxlen=100","Problem Description Field cannot contain more than 100 Characters");
		hisValidator.addValidation("strSolutionProvided","req","Solution Provided is a mandatory field");
		hisValidator.addValidation("strSolutionProvided", "maxlen=100","Solution Provided Field cannot contain more than 100 Characters");
		hisValidator.addValidation("strServiceRemarks", "maxlen=100","Remarks Field cannot contain more than 100 Characters");
		if(document.getElementsByName("strIsItemInWorkingCondition")[1].checked==true)
             
             {
		          hisValidator.addValidation("strFromDate", "date","From Date is a Mandatory Field");
     	          hisValidator.addValidation("strFromTime","req", "From Time is a Mandatory Field" );
             }
        hisValidator.addValidation("strTotalCost","req","Total Cost is a mandatory field");     
        if(document.getElementsByName("strIsPenality")[0].checked==true)
             
             {
		          hisValidator.addValidation("strPenalityAmount", "req","Penalty Charge is a Mandatory Field");
             }     
		hisValidator.addValidation("strNetCost","req","Net Cost is a mandatory field");
		retVal = hisValidator.validate(); 
      	hisValidator.clearAllValidations();
      	if(document.forms[0].strIsSparePartsMaintenanceInvolved.value=="1")
      	{
	      	if(parseInt(sparePartDtl.length)<=0)
	      	{
	      		alert("Please enter Spare Part detail");
	      		return;
	      	}
	      	document.forms[0].strSparePartInfoVal.value =  sparePartDtl + "#" + sparePartRecvdDtl; 
	     }
      	if(retVal)
      	{
		document.forms[0].hmode.value = "SAVESERVICE";
	     //document.forms[0].target = "_self";
	     document.forms[0].submit();
	    }
	}
	if(complaintStatus=="10")
	{		
		//hisValidator.addValidation("strClosingDate","date","Closing Date is a mandatory field");
		hisValidator.addValidation("strClosingDate","req","Closing Date is a mandatory field");
		if(document.forms[0].strAttendDate.value!="") 
			hisValidator.addValidation("strClosingDate", "dtgtet="+document.forms[0].strAttendDate.value,"Closing Date should be Greater than or Equal to Attend Date i.e."+document.forms[0].strAttendDate.value);
		
		hisValidator.addValidation("strClosingTime", "dontselect=0","Closing Time is a Mandatory Field.");
		//below code checks if closing time is less than complaint time if the date of closing is same as date of complaint raising
		cmpDt=document.forms[0].strComplaintDate.value.split(" ")[0];
        if(cmpDt.trim()==document.forms[0].strClosingDate.value.trim())
        	checktime(document.forms[0].strComplaintDate.value.split(" ")[1].trim()+" "+document.forms[0].strComplaintDate.value.split(" ")[2].trim(),document.forms[0].strClosingTime.value.trim()+":00 "+document.forms[0].strScheduleTimeMeridian.value.trim(),"Complaint","Closing")
        
		hisValidator.addValidation("strHEMReasonForClosing","req","Reason For Closing is a mandatory field");
		hisValidator.addValidation("strHEMReasonForClosing", "maxlen=100","Reason For Closing Field cannot contain more than 100 Characters");
		hisValidator.addValidation("strClosingComplaintStatus","req","Complaint Closing Status is a mandatory field");
		if(document.forms[0].strClosingComplaintStatus.value=="P")
             {
		         hisValidator.addValidation("strPendencyRemarks","req", "Pendency Remarks is a Mandatory Field" );
             }
        if(document.forms[0].strHEMVendorId.value=="NA")
		{
			;
		}
		else
		{
        	hisValidator.addValidation("strHEMVendorId","req","Vendor Invoice Number is a mandatory field");
		}
             
  //      hisValidator.addValidation("strHEMVendorId","req","Vendor Invoice Number is a mandatory field");
        hisValidator.addValidation("strHEMTotalCost", "req","Total Cost is a Mandatory Field");
        hisValidator.addValidation("strDownTotalTime","req","Total Down Time is a mandatory field");     
       	if(parseInt(document.forms[0].strDownTotalTime.value.split(":")[0])<0)
       	{
       		document.forms[0].strClosingTime.value="0";
       		alert("Total DownTime cannot be a negative value.");
     	}
        retVal = hisValidator.validate(); 
      	hisValidator.clearAllValidations();
      	if(retVal)
      	{
		 document.forms[0].hmode.value = "SAVECLOSE";
	     //document.forms[0].target = "_self";
	     document.forms[0].submit();
	    } 
	}         	
}

function validate2() 
{
	 var hisValidator = new HISValidator("hospitalWorkshopDeskFB");
	  var complaintStatus = document.getElementById("strComplaintStatus").value;
      var retVal=false;
     if(complaintStatus=="3")
	{
      //hisValidator.addValidation("strIsHEMAcknowledge","req","Time Of Escalation is a mandatory field");
      hisValidator.addValidation("strAckRemarks","req","Remarks is a mandatory field");
  	  retVal = hisValidator.validate(); 
      hisValidator.clearAllValidations();
      }	
	if(retVal)
	{
	//alert(complaintStatus);
		 document.forms[0].hmode.value = "SAVEACKNOWLEDGE";
	     //document.forms[0].target = "_self";
	     document.forms[0].submit();
	}
	if(complaintStatus=="4")
	{		
		hisValidator.addValidation("strScheduleDate","req","Expected Visit Date is a mandatory field");
		hisValidator.addValidation("strScheduleTime","dontselect=0","Expected Visit Time is a mandatory field");
		hisValidator.addValidation("strServiceEngineerRemarks","req","Complaint Redressal Details is a mandatory field");
		hisValidator.addValidation("strServiceEngineerRemarks", "maxlen=100","Complaint Redressal Details cannot be greater than 100 Characters");
		hisValidator.addValidation("strIntimationDate","req","Intimation Date is a mandatory field");
		hisValidator.addValidation("strIntimationTime","dontselect=0","Intimation Time is a mandatory field");
		hisValidator.addValidation("strContactPerson","req","Person Contacted is a mandatory field");
		hisValidator.addValidation("strContactNo","req","Person Contact No. is a mandatory field");
		hisValidator.addValidation("strCompanyName","req","Company Name and Address is a mandatory field");
		hisValidator.addValidation("strCompanyName", "maxlen=300","Company Name and Address Field cannot be greater than 300 Characters");
		hisValidator.addValidation("strCompanyAddress","req","Company Email is a mandatory field");
		hisValidator.addValidation("strCompanyAddress", "email","Please Enter a Valid Email ID");
		hisValidator.addValidation("strCommunicationId","dontselect=0","Communication Medium is a mandatory field");
		//hisValidator.addValidation("strCompanyAddress","req","Company Email is a mandatory field");
		if(document.forms[0].strScheduleDate.value!="") 
		{
			hisValidator.addValidation("strScheduleDate", "dtgtet="+document.forms[0].strComplaintDate.value.substr(0,12),"Schedule Date should be Greater then Complaint Date");
		}

		if(document.forms[0].strIntimationDate.value!="") 
		{
			hisValidator.addValidation("strScheduleDate", "dtgtet="+document.forms[0].strIntimationDate.value,"Intimation Date should be Less than or Equal to Expected Visit Date");
		}
  	  	retVal = hisValidator.validate(); 
      	hisValidator.clearAllValidations();
      	if(retVal)
      	{
		 document.forms[0].hmode.value = "SAVESCHEDULE";
	     //document.forms[0].target = "_self";
	     document.forms[0].submit();
	    }
	}
	if(complaintStatus=="5")
	{		
		hisValidator.addValidation("strEngineerName","req","Service Engineer Name is a mandatory field");
		hisValidator.addValidation("strMobileNo","req","Service Engineer Mobile Number is a mandatory field");
		hisValidator.addValidation("strAttendDate","req","Attend Date is a mandatory field");
		hisValidator.addValidation("strAttendTime","dontselect=0","Attend Time is a mandatory field");
		hisValidator.addValidation("strActualProblemDesc","req","Actual Problem Description is a mandatory field");
		hisValidator.addValidation("strActualProblemDesc", "maxlen=100","Actual Problem Description Field cannot be greater than 100 Characters");
		
		if(document.forms[0].strIntimationDate.value!="") 
		hisValidator.addValidation("strAttendDate", "dtgtet="+document.forms[0].strIntimationDate.value,"Attend Date should be Greater than or Equal to Intimation Date");
	
		retVal = hisValidator.validate(); 
      	hisValidator.clearAllValidations();
      	if(retVal)
      	{
		document.forms[0].hmode.value = "SAVEATTEND";
	     //document.forms[0].target = "_self";
	     document.forms[0].submit();
	    }
	}
	if(complaintStatus=="6")
	{		
		hisValidator.addValidation("strProblemDescription","req","Problem Description is a mandatory field");
		hisValidator.addValidation("strProblemDescription", "maxlen=100","Problem Description Field cannot contain more than 100 Characters");
		hisValidator.addValidation("strSolutionProvided","req","Solution Provided is a mandatory field");
		hisValidator.addValidation("strSolutionProvided", "maxlen=100","Solution Provided Field cannot contain more than 100 Characters");
		hisValidator.addValidation("strServiceRemarks", "maxlen=100","Remarks Field cannot contain more than 100 Characters");
		if(document.getElementsByName("strIsItemInWorkingCondition")[1].checked==true)
             
             {
		          hisValidator.addValidation("strFromDate", "date","From Date is a Mandatory Field");
     	          hisValidator.addValidation("strFromTime","req", "From Time is a Mandatory Field" );
             }
        hisValidator.addValidation("strTotalCost","req","Total Cost is a mandatory field");     
        if(document.getElementsByName("strIsPenality")[0].checked==true)
             
             {
		          hisValidator.addValidation("strPenalityAmount", "req","Penalty Charge is a Mandatory Field");
             }     
		hisValidator.addValidation("strNetCost","req","Net Cost is a mandatory field");
		retVal = hisValidator.validate(); 
      	hisValidator.clearAllValidations();
      	if(retVal)
      	{
      	document.forms[0].strSparePartInfoVal.value = sparePartInfo;
		document.forms[0].hmode.value = "SAVESERVICE";
	     //document.forms[0].target = "_self";
	     document.forms[0].submit();
	    }
	}
	if(complaintStatus=="10")
	{		
		//hisValidator.addValidation("strClosingDate","date","Closing Date is a mandatory field");
		hisValidator.addValidation("strClosingDate","req","Closing Date is a mandatory field");
		if(document.forms[0].strAttendDate.value!="") 
			hisValidator.addValidation("strClosingDate", "dtgtet="+document.forms[0].strAttendDate.value,"Closing Date should be Greater than or Equal to Attend Date i.e."+document.forms[0].strAttendDate.value);
		
		hisValidator.addValidation("strClosingTime", "dontselect=0","Closing Time is a Mandatory Field.");
		//below code checks if closing time is less than complaint time if the date of closing is same as date of complaint raising
		cmpDt=document.forms[0].strComplaintDate.value.split(" ")[0];
        if(cmpDt.trim()==document.forms[0].strClosingDate.value.trim())
        	checktime(document.forms[0].strComplaintDate.value.split(" ")[1].trim()+" "+document.forms[0].strComplaintDate.value.split(" ")[2].trim(),document.forms[0].strClosingTime.value.trim()+":00 "+document.forms[0].strScheduleTimeMeridian.value.trim(),"Complaint","Closing")
        
		hisValidator.addValidation("strHEMReasonForClosing","req","Reason For Closing is a mandatory field");
		hisValidator.addValidation("strHEMReasonForClosing", "maxlen=100","Reason For Closing Field cannot contain more than 100 Characters");
		hisValidator.addValidation("strClosingComplaintStatus","req","Complaint Closing Status is a mandatory field");
		if(document.forms[0].strClosingComplaintStatus.value=="P")
             {
		         hisValidator.addValidation("strPendencyRemarks","req", "Pendency Remarks is a Mandatory Field" );
             }
        hisValidator.addValidation("strHEMVendorId","req","Vendor Invoice Number is a mandatory field");
        hisValidator.addValidation("strHEMTotalCost", "req","Total Cost is a Mandatory Field");
        hisValidator.addValidation("strDownTotalTime","req","Total Down Time is a mandatory field");     
       	if(parseInt(document.forms[0].strDownTotalTime.value.split(":")[0])<0)
       	{
       		document.forms[0].strClosingTime.value="0";
       		alert("Total DownTime cannot be a negative value.");
     	}
        retVal = hisValidator.validate(); 
      	hisValidator.clearAllValidations();
      	if(retVal)
      	{
		 document.forms[0].hmode.value = "SAVECLOSE";
	     //document.forms[0].target = "_self";
	     document.forms[0].submit();
	    } 
	}         	
}


 
function cancelPage()
{
	
    document.forms[0].hmode.value="CANCELPAGE";
	document.forms[0].submit();
}

function getEnggItemSubTypeCombo()
{	   
   var itemParaObj = document.getElementById("serviceEnggNameID");
   itemParaObj.innerHTML = "<select name = 'strServiceEnggId' class='COMBO_NORMAL' ><option value='0'>Select Value</option></select>";
   
   var itemParaObj1 = document.getElementById("esclationLevelId");
   itemParaObj1.innerHTML = "<select name = 'strEscalationLevelId' class='COMBO_NORMAL'  ><option value='0'>Select Value</option></select>";
   
   
   var itemParaObj1 = document.getElementById("deptId");
   var itemParaObj2 = document.getElementById("contactNo");
   var itemParaObj3 = document.getElementById("emilaId");
		            
   itemParaObj1.innerHTML = "";
   itemParaObj2.innerHTML = "";
   itemParaObj3.innerHTML = "";
	
   var url="HemDeskACTION.cnt?hmode=GETENGGITEMSUBTYPE&enggItemTypeId="+document.forms[0].strEnggItemTypeId.value;
   ajaxFunction(url,"1");
} 

function getServiceEnggNameCmb()
{	   
   var url="HemDeskACTION.cnt?hmode=GETSERVICEENGG&enggItemTypeId="+document.forms[0].strEnggItemTypeId.value+"&enggItemSubTypeId="+document.forms[0].strEnggItemSubTypeId.value;
   ajaxFunction(url,"2");
} 

function getEscLevelCmb()
{	   
   var url="HemDeskACTION.cnt?hmode=GETESCLEVEL&enggItemTypeId="+document.forms[0].strEnggItemTypeId.value+"&enggItemSubTypeId="+document.forms[0].strEnggItemSubTypeId.value;
   ajaxFunction(url,"3");
}


function getDetails()
{	   
   var url="HemDeskACTION.cnt?hmode=GETDETAILS&escalationLevelId="+document.forms[0].strEscalationLevelId.value;
   ajaxFunction(url,"4");
   
}

function getGroupNameCmb()
{	   
   var url="HospitalWorkshopDeskACTION.cnt?hmode=GROUPNAME&enggItemTypeId="+document.forms[0].strEnggItemTypeId.value+"&enggItemSubTypeId="+document.forms[0].strEnggItemSubTypeId.value;
   ajaxFunction(url,"5");
}
function getEquipNameCmb()
{	   
   var url="HospitalWorkshopDeskACTION.cnt?hmode=GETEQUIPNAME&groupId="+document.forms[0].strGroupName.value+"&storeId="+document.forms[0].strWorkshopName.value;
   ajaxFunction(url,"6");
}
function getSparePartCmb()
{	   
   var url="HospitalWorkshopDeskACTION.cnt?hmode=GETSPAREPARTNAME&cmbValues="+document.forms[0].strEquipName.value;
   ajaxFunction(url,"7");
}
function getSparePartSrlNoCmb()
{	   
   var url="HospitalWorkshopDeskACTION.cnt?hmode=GETSPAREPARTSRLNO&equipId="+document.forms[0].strEquipName.value+"&groupId="+document.forms[0].strGroupName.value
   					+"&spareId="+document.forms[0].strSparePartName.value+"&storeId="+document.forms[0].strWorkshopName.value;
   ajaxFunction(url,"8");
}
function getSparePartDtl()
{	   
//   var url="HemDeskACTION.cnt?hmode=GETSPAREPARTDTL&srlNo="+document.forms[0].strSpareSreialNo.value;
//   ajaxFunction(url,"9");
	flag = 0;
	var indx = document.getElementById("strSpareSreialNo").selectedIndex;
	var srlNo = document.getElementById("strSpareSreialNo")[indx];
	var str = document.forms[0].strGroupName.value + "^" + document.forms[0].strEquipName.value + "^" + document.forms[0].strSparePartName.value + "^"
			  +srlNo.text + "^0^0^" + srlNo.value.split("^")[1] + "^1";
	document.forms[0].strSparePartInfoVal.value = str;
	
}
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
	       	            var itemParaObj = document.getElementById("engineeringItemSubTypeId");
	       	            itemParaObj.innerHTML = "<select name = 'strEnggItemSubTypeId' class='COMBO_NORMAL' onChange='getServiceEnggNameCmb();'>" + res + "</select>";
	    }
	    else if(mode=="2")
		{	
	       	            var itemParaObj = document.getElementById("serviceEnggNameID");
	       	            itemParaObj.innerHTML = "<select name = 'strServiceEnggId' class='COMBO_NORMAL' >" + res + "</select>";
	       	            getEscLevelCmb();
	    }
	    else if(mode=="3")
		{	
	       	            var itemParaObj = document.getElementById("esclationLevelId");
	       	            itemParaObj.innerHTML = "<select name = 'strEscalationLevelId' class='COMBO_NORMAL'  onChange='getDetails();'>" + res + "</select>";
	    }
	    else if(mode=="4")
		{	
			            var arrVal = res.split("^");
			            var itemParaObj1 = document.getElementById("deptId");
			            var itemParaObj2 = document.getElementById("contactNo");
			            var itemParaObj3 = document.getElementById("emilaId");
			            document.getElementById("strEscDtl").value=res;			            
			            
			            //            E-Mail                Mob No             Designation          Department
			            if(arrVal[3]!=' '||arrVal[3]!='')
			            {
			              itemParaObj1.innerHTML = arrVal[3];
			            } 
			            else
			            {
			              itemParaObj1.innerHTML = "----";	
			            }
			            if(arrVal[1]!=' '||arrVal[1]!='')
			            { 
			              itemParaObj2.innerHTML = arrVal[1];
			            }
			            else
			            {
			              itemParaObj2.innerHTML = "----";	
			            }
			            if(arrVal[0]!=' '||arrVal[0]!='')
			            {  
			              itemParaObj3.innerHTML = arrVal[0];
			            } 
			            else
			            {
			              itemParaObj3.innerHTML = "----";	
			            } 
	    }
	    else if(mode=="5")
		{	
	       	            var itemParaObj = document.getElementById("GroupNameDiv");
	       	            itemParaObj.innerHTML = "<select name='strGroupName' id='strGroupName' onChange='getEquipNameCmb();' >" + res + "</select>";
	    }
	    else if(mode=="6")
		{	
	       	            var itemParaObj = document.getElementById("EquipmentNameDiv");
	       	            itemParaObj.innerHTML = "<select name='strEquipName'  id='strEquipName'   onChange='getSparePartCmb();'>" + res + "</select>";
	       	            setDefaultComboValue("SparePartNameDiv","strSparePartName");
	       	            setDefaultComboValue("SrlNoDiv","strSpareSreialNo");
	       	            document.getElementById("strStatus").selectedIndex = 0;
	    }
	    else if(mode=="7")
		{	
	       	            var itemParaObj = document.getElementById("SparePartNameDiv");
	       	            itemParaObj.innerHTML = "<select name='strSparePartName' id='strSparePartName'  onChange='getSparePartSrlNoCmb();' >" + res + "</select>";
	       	            setDefaultComboValue("SrlNoDiv","strSpareSreialNo");
	       	            document.getElementById("strStatus").selectedIndex = 0;
	    }
	    else if(mode=="8")
		{	
	       	            var str = res.split("#");
	       	            if(res.split("#")[0]!="")
	       	            {
 							setInHandQty(res);
 							return true;	       	            
 						}
 						document.getElementById("srlNoLabel").innerHTML = "<font color='red'>*</font>Serial No.";
 						document.getElementById("SrlNoDiv").style.display = "";	
						document.getElementById("SpareQtyDiv").style.display = "none";
	       	            var itemParaObj = document.getElementById("SrlNoDiv");
	       	            itemParaObj.innerHTML = "<select name = 'strSpareSreialNo' id='strSpareSreialNo'   onChange=''>" + str[1] + "</select>";
	       	            document.getElementById("strStatus").selectedIndex = 0;
	    }
	    else if(mode=="9")
		{	
	       	            document.forms[0].strSparePartInfoVal.value = res;
	    }
	    
}

function setInHandQty(res)
{
	var str = res.split("#");
	var obj = document.getElementById("spareQty") ;
	document.getElementById("srlNoLabel").innerHTML = "<font color='red'>*</font>"+str[0];
	document.getElementById("SrlNoDiv").style.display = "none";	
	document.getElementById("SpareQtyDiv").style.display = "";
	document.getElementById("spareQty").value = str[2];
//	document.getElementById("spareQty").innerHTML = str[2];
	document.forms[0].strReceivedNo.value = str[1];
}



function clearPage()
{
	var strComplaintId = document.forms[0].strComplaintId.value;
	var strHemDesk = document.forms[0].strHemDesk.value;
	var strChk = document.forms[0].strChk.value;
	var complaintStatus = document.getElementById("strComplaintStatus").value;
		
	if(complaintStatus=="3")
	{
		 document.forms[0].hmode.value = "ACKNOWLEDGE";
	}
	if(complaintStatus=="4")
	{		
		 document.forms[0].hmode.value = "SCHEDULE";
	}
	if(complaintStatus=="5")
	{	 
		document.forms[0].hmode.value = "ATTEND";
	}
	if(complaintStatus=="6")
	{	 
		document.forms[0].hmode.value = "SERVICE";
	}
	if(complaintStatus=="10")
	{
		document.forms[0].hmode.value = "CLOSE";
	}  
	
	document.forms[0].reset();
	
	//document.forms[0].hmode.value="GRIEVANCES";
	document.forms[0].strComplaintId.value=strComplaintId;
	document.forms[0].strHemDesk.value = strHemDesk;
	document.forms[0].strChk.value = strChk;
	
	document.forms[0].submit();
}
function tableShow(strTableId, imageElement) {
	var elementTable = document.getElementById(strTableId);
	elementTable.style.display = "table";
	var strOnclick = "tableHide('" + strTableId + "',this);";
	imageElement.setAttribute("onclick", strOnclick);
	imageElement.setAttribute("src", "/DWH/hisglobal/images/minus.gif");

}

function tableHide(strTableId, imageElement) {

	var elementTable = document.getElementById(strTableId);
	elementTable.style.display = "none";
	var strOnclick = "tableShow('" + strTableId + "',this);";
	imageElement.setAttribute("onclick", strOnclick);
	imageElement.setAttribute("src", "/DWH/hisglobal/images/plus.gif");

}
function calculateTotal()
{
	var check = document.forms[0].strSpareCost.value;
	var otherCharges = document.getElementById("strOtherCharges").value;
	if(isNaN(otherCharges))
			otherCharges=0;
	if(check=="")
			check=0;		
	
	document.getElementById("strTotalCost").value=parseInt(calTotal())+parseInt(otherCharges);
}
function calTotal()
{
	var table = document.getElementById("SparePartTable");
	var tCost = 0;
	for(var i=0;i<table.rows.length-1;i++)
	{
		var row = document.getElementById("row"+i);
		tCost = parseInt(row.cells[4].innerHTML) + parseInt(tCost);
	}
	return tCost;
}

function setNetAmount(obj)
{
	val=obj.value;
	if(isNaN(val)||val.length<1)
		{
			alert("Please enter a valid numeric value");
			obj.focus();
		}
	else{
		document.getElementById("strNetCost").value=(parseInt(document.getElementById("strTotalCost").value)-parseInt(val));
		
		}	
		
}
function isPenaltyConditionchangeMode()
{	
	var rowid = document.getElementById("penaltyAmtId");
	//var rowid1 = document.getElementById("penaltyAmtNotId");
	//var rowid2 = document.getElementById("orderDtlId");	
	
	 if(document.getElementsByName("strIsPenality")[0].checked==true)
     {	
     //document.getElementById("strIsPenality").value="0";
 		   rowid.style.display="block";
 		   //rowid1.style.display="none";
 		//   rowid2.style.display="block";
 	 }
	else
	 {
 		   rowid.style.display="none";
 		   document.getElementsByName("strPenalityAmount").value="0";
 		   //document.getElementsByName("strOfficeOrderNo").value="0";
 		   //document.getElementsByName("strOfficeOrderDt").value="";
 		   document.getElementsByName("strPenaltyRemarks").value="";
 		   //rowid1.style.display="block";
 		  // rowid2.style.display="none";
 	 }
 	 
}

function compareTime(){ 
       	
       	  valFromHr=document.getElementsByName('strPreferredFromTime')[0].value.split(":")[0];
           valToHr=document.getElementsByName('strPreferredToTime')[0].value.split(":")[0];
           valFromMin=document.getElementsByName('strPreferredFromTime')[0].value.split(":")[1];
           valToMin=document.getElementsByName('strPreferredToTime')[0].value.split(":")[1];
     
       	
    
       	
          if(valFromHr > valToHr)
            {
              alert("From hr cannot be greater than to hour");
              document.getElementsByName('fromHour')[0].focus();            
              return false;
            }
            if(valFromHr == valToHr)
            {
               if(valFromMin >= valToMin)
               {
                 alert ("From min should be less than to min");
                 document.getElementsByName('fromMin')[0].focus(); 
                 return false;                            
               }
            }
            return true;
        }
function isItemInWorkingConditionchangeMode()
{	
	var rowid = document.getElementById("dateAndTimeId");	
	
	 if(document.getElementsByName("strIsItemInWorkingCondition")[0].checked==true)
     {
 		   rowid.style.display="none";
 	 }
	
	 if(document.getElementsByName("strIsItemInWorkingCondition")[1].checked==true)
 	 {
			rowid.style.display=''; 	
 	 }
}
function isSparePartsUsed()
{
	//getSpareParts();
  var div1=document.getElementById("id1");
  var elementTable = document.getElementById("SparePartMultiRowId");
	

	//if(document.forms[0].strIsSparePartsMaintenanceInvolved.checked)
	if(document.forms[0].strIsSparePartsMaintenanceInvolved.value=="1")
	{
	elementTable.style.display = "";
	  		 div1.style.display='';
	 		 document.forms[0].strIsSparePartsMaintenanceInvolved.value="1";
	      	 
	 }
	else{
	elementTable.style.display = "none";
	div1.style.display='none';
	document.forms[0].strIsSparePartsMaintenanceInvolved.value="0";
	}
}
function showHidePendencyRemarks(obj)
{
	var div1=document.getElementsByName("strClosingComplaintStatus");
  	var elementRow = document.getElementById("PendencyRemarksRowId");
  	var elementRowDefault = document.getElementById("PendencyRemarksRowIdDefault");
	
	if(document.forms[0].strClosingComplaintStatus.value=="P")
	{
			 elementRow.style.display = "block";
			 elementRowDefault.style.display = "block";
	      	 
	 }
	else{
	elementRow.style.display = "none";
	elementRowDefault.style.display = "none";
	elementRow.value="";
	}
}

function calculateTotalDownTime()
{
	var complaintDate=document.getElementById("strComplaintDate").value;
	//alert("complaintDate::"+complaintDate);
	var clDt=document.getElementById("strClosingDate").value;
	var clTime=document.getElementById("strClosingTime").value;
	var clMeridian=document.getElementById("strScheduleTimeMeridian").value;
	var closedDate=clDt+" "+clTime+" "+clMeridian;
	
		var ret=parseDate(complaintDate.split(" ")[0],"-");
		var ret1=parseDate(clDt,"-");
		var dt1=ret.month+"-"+ret.day+"-"+ret.year;
		
		var dt2=ret1.month+"-"+ret1.day+"-"+ret1.year;
	var complaintTime=document.getElementById("strComplaintDate").value.split(" ")[1];
	var meridian=document.getElementById("strComplaintDate").value.split(" ")[2];
		
	if(meridian=="Am" || meridian=="AM" || meridian=="am")
		{
		if(complaintTime.split(":")[0]=="12")
		{
			//temp=parseInt(complaintTime.split(":")[0])+12;
			complaintTime="00"+":"+complaintTime.split(":")[1];
		}
	}
	if(meridian=="Pm" || meridian=="PM" || meridian=="pm")
	{
		if(complaintTime.split(":")[0]=="12")
			complaintTime=("00"+complaintTime.split(":")[1]);
		temp=parseInt(complaintTime.split(":")[0])+12;
		complaintTime=temp+":"+complaintTime.split(":")[1];
		//alert("complaintTime"+complaintTime);
	}
	if(clMeridian=="Am" || clMeridian=="AM" || clMeridian=="am")
		{
		if(clTime.split(":")[0]=="12")
		{
			//temp=parseInt(clTime.split(":")[0])+12;
			clTime="00"+":"+clTime.split(":")[1];
			//alert("inside am::clTime::"+clTime);
		}
	}
	if(clMeridian=="Pm" || clMeridian=="PM" || clMeridian=="pm")
	{
		temp=parseInt(clTime.split(":")[0])+12;
		clTime=temp+":"+clTime.split(":")[1];
		//alert("clTime"+clTime);
	}
	var compHr=complaintTime.split(":")[0];
	var compMin=complaintTime.split(":")[1];
	var closeHr=clTime.split(":")[0];
	var closeMin=clTime.split(":")[1];
		
	finalComplaintDateTime = new Date(ret.year,ret.month,ret.day, compHr, compMin);
	finalCloseDateTime = new Date(ret1.year,ret1.month,ret1.day, closeHr, closeMin);
	oDiff = get_time_difference(finalComplaintDateTime, finalCloseDateTime);
	//alert("It has been " +oDiff.days+" Days "+ oDiff.hours + " hours since India won it's first cricket worldcup");
	var o=document.getElementsByName("strDownTotalTime");
	var ok=((parseInt(oDiff.days)*24)+oDiff.hours);
	//o.innerHTML=ok;
	document.forms[0].strDownTotalTime.value=ok+":"+oDiff.minutes;
	
}

// Simple function to calculate time difference between 2 Javascript date objects
function get_time_difference(earlierDate,laterDate)
{
       var nTotalDiff = laterDate.getTime() - earlierDate.getTime();
       var oDiff = new Object();
 
       oDiff.days = Math.floor(nTotalDiff/1000/60/60/24);
       nTotalDiff -= oDiff.days*1000*60*60*24;
 
       oDiff.hours = Math.floor(nTotalDiff/1000/60/60);
       nTotalDiff -= oDiff.hours*1000*60*60;
 
       oDiff.minutes = Math.floor(nTotalDiff/1000/60);
       nTotalDiff -= oDiff.minutes*1000*60;
 
       oDiff.seconds = Math.floor(nTotalDiff/1000);
 
       return oDiff;
 
}
function checktime(start,end,startName,endName)
{
	if(startName.length==0 || startName=="0") startName="Start";
	if(endName.length==0 || endName=="0")	endName="End";	
    //var start = document.getElementById("eventstarttime").value;
    //var end = document.getElementById("eventstoptime").value;
	//alert("start time::"+start+" and end time::"+end);

    if(Date.parse('01/01/2011 '+end) < Date.parse('01/01/2011 '+start))
    {
        alert(endName+" time should exceed the "+startName+" time");
        return false;
    }
    else if(Date.parse('01/01/2011 '+end) -Date.parse('01/01/2011 '+start)==0)
    {
        alert(startName+" time and "+endName+" time cannot be same");
        return false;
    }
    return true;
}

function chkRecordSaved()
 {
 	if(document.forms[0].strRetValue.value=="1")
 	{
 		alert(document.forms[0].strMsgString.value);
 		document.forms[0].hmode.value = "ListPage";
		document.forms[0].submit();
 	}
 }
 
 function addSparePart()
 {
	if(validateSparePart())
	{
		return false;
	}
	if(document.getElementById("SrlNoDiv").style.display=="none")
	{
		if(chkQty())
		{
			return ;
		}
	}
	else
	{	
		if(chkComboIsSelected("strSpareSreialNo","Spare-Part Serial No is a mandatory field"))
		{	
 			return ;
 		}
		getSparePartDtl();
	}	
	if(flag=="1")
 	{
 		if(chkSparePartQtyAdded())
 		{
 			setDefaultValue();
 			calculateTotal();
 			return ;
 		}
 	}
	else
	{
		if(chkSparePartAdded())
		{
			alert("Spare-Part already added.");
			return ;
		}
	}
	  
//	document.forms[0].strSparePartInfoVal.value = document.forms[0].strSparePartInfoVal.value + "^" + document.getElementById("strStatus").value;
	
	addRow();

	sparePartInfo[index] = document.forms[0].strSparePartInfoVal.value; 
	document.forms[0].strSpareCost.value = spareCost;
	calculateTotal();
	setSparePartUsedInfo();
	index++;
//	document.getElementById("spareQty").value = document.getElementById("strIssuedQty").value;
	setDefaultValue();	
 }
 function setDefaultValue()
 {
	setDefaultComboValue("EquipmentNameDiv","strEquipName");
	setDefaultComboValue("SparePartNameDiv","strSparePartName");
	setDefaultComboValue("SrlNoDiv","strSpareSreialNo");

	document.getElementById("strGroupName").selectedIndex = 0;
	document.getElementById("strStatus").selectedIndex = 0;

 	document.getElementById("strIssuedQty").value = 0;
	document.getElementById("spareQty").value = 0;
 	
 }
 function chkSparePartAdded()
 {
 	
 	var flg = false;
 	var spareVal = document.forms[0].strSparePartInfoVal.value;
 	for(i=0;i<sparePartInfo.length;i++)
 	{
 		var str = sparePartInfo[i];
 		var length = str.length;
 		var subStr = str.substr(0,length-2);
 		var retVal = str.localeCompare(spareVal);
 		if(retVal == "0")
 		{
 			flg = true;
 		}
 	}
 	return flg;
 }
 
 function chkSparePartQtyAdded()
 {
 	var spareVal = document.getElementById("strGroupName").value+"^"+document.getElementById("strEquipName").value+"^"+document.getElementById("strSparePartName").value;
 	var qty = 0;
 	for(i=0;i<sparePartInfo.length;i++)
 	{
 		var str = sparePartInfo[i];
 		var length = spareVal.length;
 		var subStr = str.substr(0,length);
 		var retVal = spareVal.localeCompare(subStr);
 		if(retVal == "0")
 		{
 			var table = document.getElementById("row"+i);
 			table.cells[3].innerHTML = document.forms[0].strSparePartInfoVal.value.split("^")[3];
 			table.cells[4].innerHTML = document.forms[0].strSparePartInfoVal.value.split("^")[6];
 			return true;
 		}
 	}
 }
 
 function addRow()
 {
 	var table = document.getElementById("SparePartTable");
 	var rowIndex = table.rows.length;
 	var row = table.insertRow(rowIndex);
 	row.id = "row"+index;
 	insertColumn(row);
 }
 function insertColumn(row)
 {
    var val = document.forms[0].strSparePartInfoVal.value;
 	var clmn = row.insertCell(0);
	clmn.style.width = "18%";
 	clmn.className = "CONTROL_TD";
	clmn.textContent = document.getElementById("strGroupName").selectedOptions[0].text;
	clmn.style.textAlign = "CENTER";
	
	var clmn = row.insertCell(1);
	clmn.style.width = "18%";
 	clmn.className = "CONTROL_TD";
	clmn.textContent= document.getElementById("strEquipName").selectedOptions[0].text;
	clmn.style.textAlign = "CENTER";
	
	var clmn = row.insertCell(2);
	clmn.style.width = "18%";
 	clmn.className = "CONTROL_TD";
	clmn.textContent= document.getElementById("strSparePartName").selectedOptions[0].text;
	clmn.style.textAlign = "CENTER";
	
	var clmn = row.insertCell(3);
	clmn.style.width = "18%";
 	clmn.className = "CONTROL_TD";
	clmn.textContent= val.split("^")[3];
	clmn.style.textAlign = "CENTER";
	
	var clmn = row.insertCell(4);
	clmn.style.width = "15%";
 	clmn.className = "CONTROL_TD";
	clmn.textContent= val.split("^")[6];
	clmn.style.textAlign = "CENTER";

	var clmn = row.insertCell(5);
	clmn.style.width = "10%";
 	clmn.className = "CONTROL_TD";
	clmn.textContent= "Active";//document.getElementById("strStatus").selectedOptions[0].text;
	clmn.style.textAlign = "CENTER";
	
	var clmn = row.insertCell(6);
	clmn.style.width = "3%";
	clmn.className = "CONTROL_TD";
//	clmn.innerHTML = "<img align='right' Src='../../hisglobal/images/minus.gif' style='cursor: pointer;' title='Delete Spare Part' onclick='deletePart(this);'/>";
	
	spareCost = parseInt(spareCost) + parseInt(val.split("^")[6]);
	
 }
 
 function deletePart(Obj)
 {
  	var obj1 = Obj.parentElement.parentElement.rowIndex;
 	document.getElementById("SparePartTable").deleteRow(obj1);
 	deleteSparePartFromArray(obj1-1)
 }
 
 function setDefaultComboValue(DivId,comboId)
 {
	var objVal= document.getElementById(DivId);
	objVal.innerHTML ="<select name='"+ comboId +"' id='"+ comboId +"' class='comboNormal'><option>Select Value </option></select>";
 }
 function deleteSparePartFromArray(rowIndex)
 {
 	var temp = [];
 	var j = 0;
 	for(i=0;i<sparePartInfo.length;i++)
 	{
 	    if(rowIndex!=i)
 			temp[j++] = sparePartInfo[i];
 		else 
 			spareCost = spareCost - sparePartInfo[i].split("^")[6];	
 	}
 	sparePartInfo = temp;
 	document.forms[0].strSpareCost.value = spareCost;
 	calculateTotal();
 	index--;
 }

 function validateSparePart()
 {
 	if(chkComboIsSelected("strGroupName","Group Name is a mandatory field"))
 	{
 		return true;
 	}
 	else if(chkComboIsSelected("strEquipName","Equipment Name is a mandatory field"))
	{
 		return true;
 	}
 	else if(chkComboIsSelected("strSparePartName","Spare-Part Name is a mandatory field"))
	{
 		return true;
 	}
/* 	else if(chkComboIsSelected("strSpareSreialNo","Spare-Part Serial No is a mandatory field"))
	{
 		return true;
 	}
 	else if(chkComboIsSelected("strStatus","Spare-Part Status is a mandatory field"))
	{
 		return true;
 	}*/
 }
 
 function chkComboIsSelected(id,msg)
 { 
 	if(getSelectedIndex(id)=="0")
 	{
 		alert(msg);
 		return true;
 	}
 }
 function getSelectedIndex(Obj)
 {
 	return document.getElementById(Obj).selectedIndex;
 }
 
 function chkQty()
 {
 	var qty = document.getElementById("spareQty").value;
 	var isuQty = document.getElementById("strIssuedQty").value;
 	flag = 1;
 	if(parseInt(isuQty)<=0)
	{
		alert("Please enter valid quantity.");
//		setDefaultValue();
		return true;
	}
 	if(parseInt(isuQty) > parseInt(qty))
 	{
 		alert("Not enough In Hand Quantity");
 		document.getElementById("strIssuedQty").value = 0;
 		document.getElementById("strIssuedQty").focus();
 		return true;
 	}
 	getSparePartQty();
 }
 function getSparePartQty()
{	   
	if(chkQtyIsExced())
	{
//		setDefaultValue();
		return true;
	}
	var recVal = document.forms[0].strReceivedNo.value.split("^");
	var qty = document.getElementById("strIssuedQty").value;
	var cost = [],recId = [],rQty = [],j=0;
	var newQty = 0, newCost = 0;
	var tCost = 0;
	var str;
	for(i=1;i<recVal.length;i = i+3)
	{
		recId[j] = recVal[i];
		cost[j] = recVal[i+1];
		rQty[j] = recVal[i+2];
		j++;
	} 
	for(var k=0;k<sparePartDtl.length;k++)
	{
		var tempDtl = sparePartDtl[k].split("^");
		for(var x=0;x<recId.length;x++)
		{
			if(recId[x]==tempDtl[1])
			{
				newQty = parseInt(newQty) + parseInt(tempDtl[4]);
				newCost = parseInt(newCost) + parseInt(tempDtl[6]);
			}
		}
	}
	for(var k=0;k<recId.length;k++)
	{
		var temp = rQty[k];
		if(parseInt(qty) >= 1)
		{
			if(parseInt(temp) < parseInt(qty))
			{
				tCost = tCost + (cost[k] * temp);
				sparePartRecvdDtl[k] = recId[k] +"^" + temp;
				qty = qty - temp;
			}
			else
			{
				tCost = tCost + (cost[k] * qty);
				sparePartRecvdDtl[k] = recId[k] +"^" + qty; 
				qty = qty - qty;
			}
		}
	}
	if(parseInt(newQty)==0)
	{
		str = document.forms[0].strGroupName.value + "^" + document.forms[0].strEquipName.value + "^" + document.forms[0].strSparePartName.value + "^"
			  + document.getElementById("strIssuedQty").value + "^0^0^" + tCost + "^1";
	}
	else
	{
		str = document.forms[0].strGroupName.value + "^" + document.forms[0].strEquipName.value + "^" + document.forms[0].strSparePartName.value + "^"
			  + newQty + "^0^0^" + newCost + "^1";
		
	}
	document.forms[0].strSparePartInfoVal.value = str;	
//	setSparePartInfo();	  
		
}

function setSparePartUsedInfo()
{
	var strData = document.forms[0].strSparePartInfoVal.value.split("^");
	var recvdNo;
	var strSpareUsedInfo;
	//Used Spare Part Detail --0.Consumable_Flag 1.ReceviedId 2.SpareParId 3.SerialNo 4.Quantity 5.SupplierId 6.Cost 7.Status  
	
	//Udated value of spare part --0.Consumable_Flag 1.ReceviedId 2.SerialNo 3.Quantity
	if(flag=="0")
	{
		recvdNo = document.getElementById("strSpareSreialNo").value.split("^");
		sparePartDtl[sprPtIndex] = flag +"^"+ recvdNo[0] +"^"+ strData[2] +"^"+ strData[3] +"^"+ "1" +"^"+ strData[5] +"^"+ strData[6] +"^"+ strData[7];
		spareUpdateInfo[sprPtIndex] = flag +"^"+ recvdNo[0] +"^"+ strData[3] + "1"; 
		sprPtIndex++;
	}
/*	else
	{	 
		for(var i=0;i<sparePartRecvdDtl.length;i++)
		{
			sparePartDtl[sprPtIndex] = flag +"^"+ sparePartRecvdDtl[i].split("^")[0] +"^"+ strData[2] +"^"+ "N/A" +"^"+ sparePartRecvdDtl[i].split("^")[1] +"^"+ strData[5] +"^"+ strData[6] +"^"+ strData[7];
			spareUpdateInfo[sprPtIndex] = flag +"^"+ sparePartRecvdDtl[i].split("^")[0] +"^"+ "N/A" +"^"+ sparePartRecvdDtl[i].split("^")[1]; 
			sprPtIndex++;
		}
	}
	*/
}

function chkQtyIsExced()
{
	var flg = false;
	var recVal = document.forms[0].strReceivedNo.value.split("^");
	var qty = isuQty = document.getElementById("strIssuedQty").value;
	var cost = [],recId = [],rQty = [],j=1;
	var tCost = 0;
	for(var i=0;i<sparePartDtl.length;i++)
	{
		var temp = sparePartDtl[i].split("^");
		if(recVal[j]==temp[1])
		{
			qty = parseInt(qty) + parseInt(temp[4]);
			if(parseInt(qty) > parseInt(document.getElementById("spareQty").value))
			{
				flg = true;
				qty = qty - document.getElementById("strIssuedQty").value;
				alert("Not enough Quantity. Already "+qty+" parts Added");
				return flg;
			}
			else
			{
				var old = temp[4];
				var nw = recVal[j+2];
				tCost = sparePartDtl[i].split("^")[6];
				if(parseInt(old) < parseInt(nw))
				{
					old = nw - old;
					tCost = parseInt(tCost) + parseInt(old * recVal[j+1]);
					sparePartDtl[i] = temp[0]+"^"+temp[1]+"^"+temp[2]+"^"+temp[3]+"^"+nw+"^"+temp[5]+"^"+tCost+"^"+temp[7];
					isuQty = isuQty - old;
				}
			}
		j = j + 3;
		}
	}
	if(parseInt(isuQty)>=1)
	{
		for(var i=j;i<recVal.length;i=i+3)
		{
			if(parseInt(isuQty) >= 1)
			{
				var rQty = recVal[i+2];
				
				if(parseInt(rQty)<parseInt(isuQty))
				{
					sparePartDtl[sprPtIndex] = flag +"^"+ recVal[i] +"^"+ document.forms[0].strSparePartName.value +"^"+ "N/A" +"^"+ rQty +"^0^"+ (recVal[i+1] * rQty) +"^1";
					isuQty = isuQty - rQty;
					sprPtIndex++;
				}
				else
				{
					sparePartDtl[sprPtIndex] = flag +"^"+ recVal[i] +"^"+ document.forms[0].strSparePartName.value +"^"+ "N/A" +"^"+ isuQty +"^0^"+ (recVal[i+1] * isuQty) +"^1";
					isuQty = isuQty - isuQty;
					sprPtIndex++;
				}
			}
		}
	}
	if(flg)
	{
		qty = qty - document.getElementById("strIssuedQty").value;
		alert("Not enough Quantity. Already "+qty+" parts Added");
	}
	return flg;
}
