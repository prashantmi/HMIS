function checkUpdateMode(obj)
{ 
          if(obj.checked)
	      {
	        obj.value = 1;
           	obj.checked = true;
           
	      }
	      else
	      {
	         obj.value = 0;
           	 obj.checked = false;
	       	
  	      }
  	      

}
function submitDataAdd(mode)
{
   
	var hisValidator = new HISValidator("tariffBean");
	
	hisValidator.addValidation("strtrfPkgName","req","Tariff Name Is Mandatory Field");
	
	hisValidator.addValidation("strtrfPkgName","maxlen=100","Tariff Name should not contain greater than 50 characters" );

	var retVal = hisValidator.validate();
		hisValidator.clearAllValidations();
	
			
	if(retVal){	
	
		document.forms[0].hmode.value=mode;
		document.forms[0].submit();
	}else{		
		return false;
	}	
	
}
function submitDataModify(mode)
{


	var hisValidator = new HISValidator("tariffBean");
	
	hisValidator.addValidation("strtrfPkgName","req","Tariff Name Is Mandatory");
	hisValidator.addValidation("strtrfPkgName","maxlen=100","Tariff Name should not contain greater than 50 characters" );
	var retVal = hisValidator.validate();
	
	
	
	if(retVal){	
	
		document.forms[0].hmode.value=mode;
		document.forms[0].submit();
	}else{		
		return false;
	}	
	
}
