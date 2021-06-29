function ClearPage()
{
  	 document.forms[0].reset();
}
function cancelPage()
{
	
    document.forms[0].hmode.value="CANCELPAGE";
	document.forms[0].submit();
}

function changeMode(chkObj)
{ 	
    if(chkObj.value =='1')
 	{ 	
 		 document.forms[0].strStatus.value = '1'; 
 		 document.forms[0].strIsApproved.checked = true;
 		 document.forms[0].strIsReject.checked = false;
 		 
 	}
 	if(chkObj.value =='2')
 	{ 			 
 		 document.forms[0].strStatus.value = '2'; 
 		 document.forms[0].strIsApproved.checked = false;
 		 document.forms[0].strIsReject.checked = true;
 		 
 	}
}


function check()
{
	document.forms[0].strIsApproved.checked = true;
	document.forms[0].strIsReject.checked = false;
	document.forms[0].strStatus.value = '1'; 
	chkRecordSaved();
}

function validate1() 
{
	if(document.forms[0].strIsApproved.checked || document.forms[0].strIsReject.checked)
  	{
	
          var hisValidator = new HISValidator("hemComplaintAppDeskFB");
          //hisValidator.addValidation("strRemarks", "req","Remarks is a Mandatory Field");
  	  	
  	      var retVal = hisValidator.validate(); 
          hisValidator.clearAllValidations();
           if(retVal)
           {
                 	  document.forms[0].hmode.value = "SAVE";
                      document.forms[0].target = "_self";
                      document.forms[0].submit();
           }
           else
           {
           	           document.forms[0].strRemarks.focus(); 
                       return false;
           }
  	}
  	else
    {
    	document.forms[0].strIsApproved.focus();
                       alert("Approval is a Mandatory");
                       return false;
    }

}

function validate2() 
{
	if(document.forms[0].strIsApproved.checked || document.forms[0].strIsReject.checked)
  	{
	
          var hisValidator = new HISValidator("hemComplaintAppDeskFB");
          //hisValidator.addValidation("strRemarks", "req","Remarks is a Mandatory Field");
  	  	
  	      var retVal = hisValidator.validate(); 
          hisValidator.clearAllValidations();
           if(retVal)
           {
                 	  document.forms[0].hmode.value = "SAVEAPPROVAL";
                      document.forms[0].target = "_self";
                      document.forms[0].submit();
           }
           else
           {
           	           document.forms[0].strRemarks.focus(); 
                       return false;
           }
  	}
  	else
    {
    	document.forms[0].strIsApproved.focus();
                       alert("Approval is a Mandatory");
                       return false;
    }

}

function validate3() 
{
	if(document.forms[0].strIsApproved.checked)
  	{
	
          var hisValidator = new HISValidator("hemComplaintAppDeskFB");
          //hisValidator.addValidation("strRemarks", "req","Remarks is a Mandatory Field");
  	  	
  	      var retVal = hisValidator.validate(); 
          hisValidator.clearAllValidations();
           if(retVal)
           {
                 	  document.forms[0].hmode.value = "SAVEFINALAPPROVAL";
                      document.forms[0].target = "_self";
                      document.forms[0].submit();
           }
           else
           {
           	           document.forms[0].strRemarks.focus(); 
                       return false;
           }
  	}
  	else
    {
    	document.forms[0].strIsApproved.focus();
                       alert("Approval is a Mandatory");
                       return false;
    }

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

function buttonLogicsOnRecordCheck(these)
{  

     //  alert(document.forms[0].chk.checked);
       var chkObj  = document.getElementsByName("chk");
       var count = parseInt("0");
       var status=document.getElementById("comboid2").value;
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
   	  	// disableButton("Acknowledgement");
	     disableButton("Approval");
		 disableButton("View");
		 disableButton("Final Close");  	    	
   	     return false;
   	   }
       else if(count==1)
       {   
         enableButton("View");
        //disableButton("Generate");
         //var tmp = document.getElementById("comboid2").value.split("^");
         //var reqType = tmp[1];
         var status  = document.getElementById("comboid2").value;
           if(status == "1")
            {
             //disableButton("Approval");
             disableButton("View");
             disableButton("Final Close");
             enableButton("Approval");
             //enableButton("Acknowledgement");
             return;
             
            }
           /*if(status == "2")
            { 
              disableButton("Acknowledgement");
              disableButton("Final Close");
              enableButton("Approval");
              enableButton("View");
              return;
            }*/  
         if(status == "3")
            { 
	          //disableButton("Acknowledgement");
	          disableButton("Approval");
	          disableButton("Final Close");
              enableButton("View");
              return;
            }
         if(status == "8")
         { 
	        //disableButton("Acknowledgement");
	        disableButton("Approval");
	        disableButton("View");
	        enableButton("Final Close");
           	return;
         }
         
	   }
	   else{
	   	 //disableButton("Acknowledgement");
	     disableButton("Approval");
		 disableButton("View");
		 disableButton("Final Close");
		 } 
}



function buttonLogicsOnClick1(modeNo, mode , display)
{
        		
		     /*if(document.getElementById("comboid0").value =="0")
		     {
				alert("Please Select Hospital Name");
				document.getElementById("comboid0").focus();
				return;
			 }*/
						
    		 var chkObj = document.getElementsByName("chk");
   	 
   			 var count = parseInt("0");
   	 
   	 		 for(var i=0; i<chkObj.length; i++) 
   	 		 {
   	 			if(chkObj[i].checked)
   	 			{
   	 				count = count + 1;
   	 		    }		
   	 		  
   	 		 }
   	 		 if(count!='0' || count=='1')
   	 		 {
   	 		 	add(mode);
   	 		 }
   	 		 else
   	 		 {
   	 		 	alert("Please select single record!!");
   	 		 	return;
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
function chkRecordSaved()
 {
 	if(document.forms[0].strRetValue.value=="1")
 	{
 		alert(document.forms[0].strMsgString.value);
 		document.forms[0].hmode.value = "ListPage";
		document.forms[0].submit();
 	}
 }

function onUploadedFileName(obj,index,fileId)
{
	
		if(fileId!='0')
		{
			document.forms[0].hmode.value="GETUPLOADEDFILE"; 
			document.forms[0].strUploadFileId.value=fileId;
			document.forms[0].target = "_blank";
			document.forms[0].submit();
		}
		else
		{
			alert("No File to Up-Load!!");			
		}
	
}

