function validate1()
{   
      var hisValidator = new HISValidator("subgroupBean");
      hisValidator.addValidation("strsubgroupCode","req","Sub Group Code is a Mandatory Field" );
      hisValidator.addValidation("strsubgroupCode","maxlen=10","Sub Group Code should not contain greater than 35 characters" );
      hisValidator.addValidation("strsubgroupName","req","Sub Group Name is a Mandatory Field" );
      hisValidator.addValidation("strsubgroupName","maxlen=35","Sub Group Name should not contain greater than 35 characters" );
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
    function alphanumeric(){
      var subgroup = document.getElementById('strsubgroupName').value;
      for(var i=0; i<subgroup.length; i++)
      {
        var char1 = subgroup.charAt(i);
        var cc = char1.charCodeAt(0);

        if((cc>47 && cc<58) || (cc>64 && cc<91) || (cc>96 && cc<123))
        {

        }
         else {
         alert('Sub Group Name should be alphanumeric');
         return false;
         }
      }
     return true;     
   }
    function validate2()
{   
      var hisValidator = new HISValidator("subgroupBean");
      
      hisValidator.addValidation("strsubgroupCode","req","Sub Group Code is a Mandatory Field" );
      hisValidator.addValidation("strsubgroupCode","maxlen=10","Sub Group Code should not contain greater than 35 characters" );
      
        hisValidator.addValidation("strsubgroupName", "req","Sub Group Name is a Mandatory Field");
		hisValidator.addValidation("strsubgroupName", "maxlen=35", "Sub group Name should have less than or equal to 35 Characters" );
		
       
         
            var retVal = hisValidator.validate();
 
    
          if(retVal)
          {
             		    document.forms[0].hmode.value = "UPDATE";
      				  
      				    document.forms[0].submit();
                        
            }else{

           return false;
          }
    }
    
    function cancelPage()
   {
   	document.forms[0].hmode.value='LIST';
	document.forms[0].submit();
   }