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
       else
       {   
         
         enableButton("View");
         enableButton("Status");
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
         	enableButton("Status");
         	return;
         }
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
         	enableButton("Status");
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
	 var hisValidator = new HISValidator("centralizeWorkshopMonitoringDeskFB");
	 
	 /*hisValidator.addValidation("strEnggItemTypeId", "dontselect=0",	"Please Select a Engg Item Type");
	 hisValidator.addValidation("strEnggItemSubTypeId", "dontselect=0",	"Please Select a Engg Item Sub Type");
	 hisValidator.addValidation("strServiceEnggId", "dontselect=0",	"Please Select a Service Engineer Name");
	 hisValidator.addValidation("strEscalationLevelId","dontselect=0","Escalation Level is a mandatory field");
     hisValidator.addValidation("strName","req","Name is a mandatory field");
     hisValidator.addValidation("strDesignation","req","Designation is a mandatory field");
	 hisValidator.addValidation("strModeofEscId","dontselect=0","Mode Of Escalation is a mandatory field");
	 hisValidator.addValidation("strEscDate","req","Date Of Escalation is a mandatory field");
	 hisValidator.addValidation("strEscTime","req","Time Of Escalation is a mandatory field");
     
  	  var retVal = hisValidator.validate(); 
      hisValidator.clearAllValidations();*/
  	
	//if(retVal)
	var complaintStatus = document.getElementById("strComplaintStatus").value;
	alert(complaintStatus);
	if(complaintStatus=="3")
	{		
	
		 document.forms[0].hmode.value = "SAVEACKNOWLEDGE";
	     //document.forms[0].target = "_self";
	     document.forms[0].submit();
	}
	if(complaintStatus=="4")
	{		
	
		 document.forms[0].hmode.value = "SAVESCHEDULE";
	     //document.forms[0].target = "_self";
	     document.forms[0].submit();
	}
	if(complaintStatus=="5")
	{		
	
		 document.forms[0].hmode.value = "SAVEATTEND";
	     //document.forms[0].target = "_self";
	     document.forms[0].submit();
	}
	if(complaintStatus=="10")
	{		
	
		 document.forms[0].hmode.value = "SAVECLOSE";
	     //document.forms[0].target = "_self";
	     document.forms[0].submit();
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
	    
	    if(mode=="2")
		{	
	       	            var itemParaObj = document.getElementById("serviceEnggNameID");
	       	            itemParaObj.innerHTML = "<select name = 'strServiceEnggId' class='COMBO_NORMAL' >" + res + "</select>";
	       	            getEscLevelCmb();
	    }
	    
	    if(mode=="3")
		{	
	       	            var itemParaObj = document.getElementById("esclationLevelId");
	       	            itemParaObj.innerHTML = "<select name = 'strEscalationLevelId' class='COMBO_NORMAL'  onChange='getDetails();'>" + res + "</select>";
	    }
	    
	    if(mode=="4")
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
}



function clearPage()
{
	var strComplaintId = document.forms[0].strComplaintId.value;
	var strHemDesk = document.forms[0].strHemDesk.value;
	var strChk = document.forms[0].strChk.value;
	
	document.forms[0].reset();
	
	document.forms[0].hmode.value="GRIEVANCES";
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
	imageElement.setAttribute("src", "/HEMMS_ODISHA/hisglobal/images/minus.gif");

}

function tableHide(strTableId, imageElement) {

	var elementTable = document.getElementById(strTableId);
	elementTable.style.display = "none";
	var strOnclick = "tableShow('" + strTableId + "',this);";
	imageElement.setAttribute("onclick", strOnclick);
	imageElement.setAttribute("src", "/HEMMS_ODISHA/hisglobal/images/plus.gif");

}