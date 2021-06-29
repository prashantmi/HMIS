
// method for page UPDATE
function validateUpdate(){   
     
      var hisValidator = new HISValidator("expertiseMstBean");
           
        hisValidator.addValidation("strExpertiseName","req","Expertise Name is a Mandatory Field" );
        hisValidator.addValidation("strEffectiveFrom","date","Effective Date is a mandatory field");
        //hisValidator.addValidation("strEffectiveFrom", "dtgtet=${itemParameterBean.strCtDate}" , "Effective Date should be Greater than or Equal to Current Date");         
           
  var retVal = hisValidator.validate(); 
      		hisValidator.clearAllValidations();
          if(retVal){
                       document.forms[0].hmode.value = "UPDATE";
                       document.forms[0].submit();
            }else{

           return false;
}	
}
 // method for page SAVE records
function validate1(){   
     
      var hisValidator = new HISValidator("expertiseMstBean");
           
        hisValidator.addValidation("strExpertiseName","req","Expertise Name is a Mandatory Field" );
        hisValidator.addValidation("strEffectiveFrom","date","Effective Date is a mandatory field");
        //hisValidator.addValidation("strEffectiveFrom", "dtgtet=${itemParameterBean.strCtDate}" , "Effective Date should be Greater than or Equal to Current Date");         
           
  var retVal = hisValidator.validate(); 
      		hisValidator.clearAllValidations();
          if(retVal){
                       document.forms[0].hmode.value = "SAVE";
                       document.forms[0].submit();
            }else{

           return false;
}	
}





// method for page ADD 
function callComboAdd(form1, mode) {
	
		with (form1) {
		
			add("ADD");

		}
	

}
// method for page MODIFY 

function callComboModify(form1, mode) {


	with (form1) {

	
			edit("MODIFY");
		
	}
}


function clearMsg(mode)
{

document.forms[0].hmode.value = mode;
document.forms[0].submit();

}
