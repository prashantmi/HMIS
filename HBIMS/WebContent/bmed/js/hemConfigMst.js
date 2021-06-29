function validateAdd()
{   

             var hisValidator = new HISValidator("hemConfigBean");
             
              hisValidator.addValidation("strShowMcDetailsUpto","numltet=365", "Show Expiry Maintenance Contract Details Upto Enter Values from 0-365" );
              hisValidator.addValidation("strShowMcDetailsUpto","req", "Show Expiry Maintenance Contract Details Upto is a Mandatory Field" );
              
              hisValidator.addValidation("strShowWarrantyDetailsUpto","numltet=365", "Show Expiry Maintenance Contract Details Upto Enter Values from 0-365" );
	          hisValidator.addValidation("strShowWarrantyDetailsUpto","req", "Show Expiry Warranty Contract Details Upto is a Mandatory Field" );
	          
	          hisValidator.addValidation("strFinancialStartDate","req", "Financial Start Date is a Mandatory Field" );  
	          hisValidator.addValidation("strFinancialEndDate","req", "Financial End Date is a Mandatory Field" );
                        
              hisValidator.addValidation("strFinancialStartDate","dtltet="+document.forms[0].strFinancialEndDate.value, "Please select Financial Start Date Less Than Equal To Financial End Date");  
              hisValidator.addValidation("strFolderName","req", "Folder Name is a Mandatory Field" );
                      
                        
                        
			  var retVal = hisValidator.validate(); 
			  if(retVal)
			  {
			  	var str = document.forms[0].strFolderName.value.toUpperCase();
			  	if(str=="CON" || str=="PRN" || str=="AUX" || str=="CLOCK$" || str=="NUL" || str=="COM1" ||
			  		str=="COM2" || str=="COM3" || str=="COM4" || str=="COM5" || str=="COM6" || str=="COM7" ||
			  		str=="COM8" || str=="COM9" || str=="LPT1" || str=="LPT2" || str=="LPT3" || str=="LPT4"||
			  		str=="LPT5" || str=="LPT6" || str=="LPT7" || str=="LPT8" || str=="LPT9")
			  	{
			  		alert("Folders with name: CON, PRN, AUX, CLOCK$, NUL, COM1, COM2, COM3, COM4, COM5, " +
			  				"COM6, COM7, COM8, COM9, LPT1, LPT2, LPT3, LPT4, LPT5, LPT6, LPT7, LPT8, & LPT9 " +
			  				"cannot be created");
			  		retVal=false;
			  	}
			  }

	          if(retVal)
	          {
	                 	   document.forms[0].hmode.value = "SAVE";
	                       document.forms[0].submit();
	          }
	          else
	          {
	             return false;
	          }
}

function cancelThePage()
{
				document.forms[0].hmode.value="CANCEL";
               	document.forms[0].submit();
}
function clearNormalMessage()
{
	document.getElementById("normalMsg").innerHTML="";	
}

function clearMsg(strTmp)
{
	document.forms[0].hmode.value = strTmp;
	document.forms[0].submit();
}