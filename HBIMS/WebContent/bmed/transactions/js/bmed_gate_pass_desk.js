function validate()
{   

       //   alert("Hiiii in validate()");
          var hisValidator = new HISValidator("stEquipgatepassrequestDeskName");
                  hisValidator.addValidation("strRequestDate","date","Please Enter Request Date");
              hisValidator.addValidation("strRequestTime","req","Please Enter Request Time");
	         
	          hisValidator.addValidation("strGatePassRequestFor","req","Please Enter User Name");
	          hisValidator.addValidation("strGatePassIssueTo","req","Please EnterIssue to Name");
	          hisValidator.addValidation("strGatePassReceiverAdd","req","Please Enter Receiver Address ");
	          hisValidator.addValidation("strGatePassPurpose","req","Please Enter Purpose");

              hisValidator.addValidation("strGatePassRequester","dontselect=0","Please Select requester Name");
	                  
		  var retVal = hisValidator.validate(); 
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
