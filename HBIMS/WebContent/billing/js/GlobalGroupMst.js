function validate1()
{   
      var hisValidator = new HISValidator("groupBean");
      hisValidator.addValidation("strgroupCode","req","Group Code is a Mandatory Field" );
      hisValidator.addValidation("strgroupCode","maxlen=10","Group Code should not contain greater than 35 characters" );
      hisValidator.addValidation("strgroupName","req","Group Name is a Mandatory Field" );
      hisValidator.addValidation("strgroupName","maxlen=35","Group Name should not contain greater than 35 characters" );
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
      var group = document.getElementById('strgroupName').value;
      for(var i=0; i<group.length; i++)
      {
        var char1 = group.charAt(i);
        var cc = char1.charCodeAt(0);

        if((cc>47 && cc<58) || (cc>64 && cc<91) || (cc>96 && cc<123))
        {

        }
         else {
         alert('Group Name should be alphanumeric');
         return false;
         }
      }
     return true;     
   }
    function validate2()
{   
      var hisValidator = new HISValidator("groupBean");
      
      hisValidator.addValidation("strgroupCode","req","Group Code is a Mandatory Field" );
      hisValidator.addValidation("strgroupCode","maxlen=10","Group Code should not contain greater than 35 characters" );
      
        hisValidator.addValidation("strgroupName", "req","Group Name is a Mandatory Field");
		hisValidator.addValidation("strgroupName", "maxlen=35", "Group Name should have less than or equal to 35 Characters" );
		
       
         
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