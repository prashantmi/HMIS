
function validate1()
{   
	  var hisValidator = new HISValidator("complaintMaintenanceStatusBean");
	  
	  
	  	 hisValidator.addValidation("strReminderDetails","req", "Reminder Details is a Mandatory Field" );
	     hisValidator.addValidation("strReminderDetails","maxlen=500", "Reminder Details should have less than or equal to 500 Characters" );
	  
	  
	  	 var retVal = hisValidator.validate();
	  	 
	  	   if(retVal)
          {
	         	   document.forms[0].hmode.value = "saveReminder";
	           	   document.forms[0].submit();
          }
          else
          {
             return false;
          }
}


// Clear Page
function clearPage()
{
	document.forms[0].reset();
	var strComplaintId = document.forms[0].strComplaintId.value;
	document.forms[0].hmode.value="initializeReminder";
	document.forms[0].strComplaintId.value=strComplaintId;
	document.forms[0].strHiddenComplaintId.value=strComplaintId;
	document.forms[0].submit();
}

function cancelPage()
{
	document.forms[0].hmode.value = "unspecified";
	document.forms[0].submit();
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
