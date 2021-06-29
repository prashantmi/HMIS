function validate1(){   
     
      var hisValidator = new HISValidator("grpBean");
            hisValidator.addValidation("strgroupName","req","Group Name is a Mandatory Field" );
            hisValidator.addValidation("strgroupName","maxlen=35","Group Name should not contain greater than 35 characters" );
            hisValidator.addValidation("strEffectiveFrom", "date","Effective Date is a mandatory field");
			hisValidator.addValidation("document.forms[0].strEffectiveFrom", "dtgtet=document.forms[0].strctdate.value" , "Effective Date should be Greater than or Equal to Current Date");         
            hisValidator.addValidation("strremark","maxlen=50","Remarks should not be greater than 50 characters");        
            hisValidator.addValidation("strconsumeableCharge","amount=6,2","Please Enter Valid Consumable Charge.");
            hisValidator.addValidation("strGlobalGroupName","dontselect=0","Please Select Global Group Name");
            var retVal = hisValidator.validate(); 
            var checkboxes = document.getElementsByName('hospServiceValue');
            var status=false;
            if(retVal)
            {
            	for(var i=0;i<checkboxes.length;i++)
        		{
        			if(checkboxes[i].checked)
        			{	
        				status=true;
           				break;
        			}
	            }
            }
            if(!status && retVal)
            {
            	alert("Please Select Checkbox of Hospital Service Type");
            	retVal=false;
           	}
            if(retVal){         
      				   document.forms[0].hmode.value = "SAVE";
        
                        document.forms[0].submit();
            }else{

           return false;

          }
    }
    function groupname()
    {
    var selIndex=document.forms[0].strGlobalGroupName.selectedIndex;
    var Id=document.forms[0].strGlobalGroupName.value;
    document.forms[0].strgroupName.value=document.forms[0].strGlobalGroupName.options[selIndex].text;
   
    if(Id.split('^')[1]!=undefined)
    {
	    document.forms[0].strgroupId.value=Id.split('^')[0];
	    document.forms[0].strGroupCode.value=Id.split('^')[1];
    }
    else
    	document.forms[0].strGroupCode.value="";
    }
    function checkboxname()
    {
    	var checkboxes = document.getElementsByName('hospServiceValue');
    	var selected = [];
    	for(var i=0;i<checkboxes.length;i++)
    	{
    			if(checkboxes[i].checked)
    				  selected.push(checkboxes[i].value);
    	}
    	document.forms[0].hospServiceValue.value=selected;
    	
    }
    
    function checkboxmodify()
    {
    	var checkboxes = document.getElementsByName('hospServiceValue');
    	var selected = [];
    	for(var i=0;i<checkboxes.length;i++)
    	{
    			if(checkboxes[i].checked && !(checkboxes[i].disabled))				    
    			{
    				selected.push(checkboxes[i].value);    	
    			}
    	}
    	if(selected.length!=0)
    		document.getElementsByName('hospServiceValue').value=selected;
    }
    
    
 function validate2()
 {   
        var hisValidator = new HISValidator("grpBean");
        hisValidator.addValidation("strgroupName", "req","Group Name is a Mandatory Field");
		hisValidator.addValidation("strgroupName", "maxlen=35", "Group Name should have less than or equal to 35 Characters" );
        hisValidator.addValidation("strremark","maxlen=50","Remarks should not be greater than 50 characters");    
        hisValidator.addValidation("strconsumeableCharge","amount=6,2","Please Enter Valid Consumable Charge.");
        var retVal = hisValidator.validate();
        if(retVal)
        {
             		    document.forms[0].hmode.value = "UPDATE";
      				  
      				    document.forms[0].submit();
                        
         }
        else
        {
           return false;
          }
    }
    
   function cancelPage()
   {
   	document.forms[0].hmode.value='LIST';
	document.forms[0].submit();
   }