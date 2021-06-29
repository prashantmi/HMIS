function validate1(){   
    
      var hisValidator = new HISValidator("unitvalueBean");
            
            hisValidator.addValidation("strmoduleName","dontselect=0","Please select a value from Module Combo");
            hisValidator.addValidation("strfromUnit","dontselect=0","Please select a value from From Unit Combo");
            hisValidator.addValidation("strtoUnit","dontselect=0","Please select a value from To Unit Combo");
            hisValidator.addValidation("strconvertedValue", "req", "Converted Value is a Mandatory Field" );
            hisValidator.addValidation("strconvertedValue", "amount=7,3", "Converted Value should be less than 10000 and decimal digits can be upto 3 ,eg: 0000.000" );
            hisValidator.addValidation("streffectiveFrom", "req","Effective Date is a Mandatory Field");
            hisValidator.addValidation("streffectiveFrom", "date","Effective Date should be a valid Date");
            hisValidator.addValidation("document.forms[0].streffectiveFrom", "dtgtet=document.forms[0].strCtDate.value" , "Effective From should be Greater than or Equal to Current Date");        
            hisValidator.addValidation("strremark", "maxlen=50", "Remarks should have less than or equal to 50 Characters" );
            var retVal = hisValidator.validate(); 
     
          if(retVal){
                       document.forms[0].hmode.value = "SAVE";
        
                        document.forms[0].submit();
            }else{

           return false;

          }
    }


function combo1(mode)
 { 
	 var url;
	 if(mode=="FROMUNITVAL")
	 {
	  url="CNTUnitValueMst.cnt?hmode="+mode+"&modName="+document.forms[0].strmoduleName.value;
	  ajaxFunction(url,"1");
	 }
	else
	if(mode=="TOUNITVAL")
	{
	 var frmUnitId = document.forms[0].strfromUnit.value.split("#")[1]; 
	 url="CNTUnitValueMst.cnt?hmode="+mode+"&modName="+document.forms[0].strmoduleName.value+"&fromunit="+frmUnitId;
	 ajaxFunction(url,"2");
	}
  }
  function getAjaxResponse(res,mode)
  {
	if(mode=="1")
	{   
	var objVal= document.getElementById("fromUnitId");
	objVal.innerHTML = "<select name ='strfromUnit' onChange ='combo1(\"TOUNITVAL\");'>" + res + "</select>";
	}
	else if(mode=="2")
	{
	var objVal = document.getElementById("toUnitId");
	objVal.innerHTML = "<select name='strtoUnit'>" + res + "</select>";
	}
  }
  
  
function validate2(){   
   
      var hisValidator = new HISValidator("unitvalueBean");
             
          
            hisValidator.addValidation("strconvertedValue", "req", "Converted Value is a Mandatory Field" );
            hisValidator.addValidation("strconvertedValue", "amount=7,3", "Converted Value should be less than 10000 and decimal digits can be upto 3,eg: 0000.000" );
            hisValidator.addValidation("strconvertedValue", "num", "Converted Value is a numeric Field" );
            hisValidator.addValidation("streffectiveFrom", "req","Effective Date is a Mandatory Field");
            
                     
            var retVal = hisValidator.validate(); 
       
          if(retVal){
                       document.forms[0].hmode.value = "UPDATE"  ;
        
                        document.forms[0].submit();
            }else{

           return false;

          }
    }
  