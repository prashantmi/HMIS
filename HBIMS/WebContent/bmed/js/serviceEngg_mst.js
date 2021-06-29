function chequeDuplicacy(objValue,index)
{
          
           var chkObj  = document.getElementsByName("strServiceEnggNameId");
           //alert(objValue.options[objValue.selectedIndex].text+":::"+objValue.options[objValue.selectedIndex].value);
           var count = parseInt("0");
           var valArr = index.split("-");
           var tmpVal = objValue.options[objValue.selectedIndex].value;
           
		   	       for(var i=0; i<valArr[1]-1; i++) 
		   	       {
		   	       	 
		   	             //alert("i:::"+i+":::chkObj[i].value:::::"+chkObj[i].value+"::::val::"+tmpVal);
			   	 		 
			   	 		 if(chkObj[i].value == tmpVal)
				   	  	 {
						   	  alert("Duplicate Value!!!");
						   	  document.getElementById("strServiceEnggName"+index).value  ='0';
						   	  return  false;
						 }
			   	 	   	
		   	 	     	 	       	 	    	
				   }
            
}
function buttonLogicsOnClick(mode)
{
	       var Obj = document.getElementsByName("combo");
   	       if(Obj[0].value =="0")
		   {
				alert("Please Select A Engineering Item Type");
				Obj[0].focus();
				return ;
			}
			if(Obj[1].value =="0")
		    {
				alert("Please Select A Engineering Item Sub Type");
				Obj[1].focus();
				return ;
			}
		    else
			{ 
   	 	   	  add(mode); 			
		   	}

}
function validate1()
 {
	  var hisValidator = new HISValidator("serviceEnggMstBean");
	  hisValidator.addValidation("strEffectiveFrom","req","Effective From is a mandatory field");
	  hisValidator.addValidation("strRemarks","maxlen=100","Remarks should not contain greater than 100 characters!!!" );
  	  var retVal = hisValidator.validate(); 
      hisValidator.clearAllValidations();
  	var retVal = true; 
	if(retVal)
	{		
		   
		   var chkObj  = document.getElementsByName("strServiceEnggNameId");
           var count = parseInt("0");
           //alert(chkObj.length);
           if(chkObj.length>'1')
           {
	   	       for(var i=0; i<chkObj.length-1; i++) 
	   	       {   	        
	   	       	//alert("index:::"+i+"::::"+chkObj[i].value);   
		   	 		 if(chkObj[i].value =='0')
			   	  	 {
			   	 		  count = count + 1;
			   	 	 }
		   	 	     	 	     	 	       	 	    	
			   }		  
			   if(count > 0)
			   {
			      	alert("Please Select Service Engineer Name!!!");
	   	 	   	    return  false;
			   
			     	
			   	
			   }
			   else
			   { 
			   	   document.forms[0].hmode.value = "SAVE";
			       document.forms[0].submit();	
			   	  
			   }
		   
           }
           else
          {
          	alert("Please Select Service Engineer Name!!!");
	   	 	   	    return  false;
          	
          } 
		
	}		
	
}	


function validateUpdate()
 {
	var hisValidator = new HISValidator("serviceEnggMstBean");
		 
  	 hisValidator.addValidation("strEffectiveFrom","req","Effective From is a mandatory field");
  	 hisValidator.addValidation("strRemarks","maxlen=100","Remarks should not contain greater than 100 characters!!!" );
  	 
	
	 var retVal = hisValidator.validate(); 
      hisValidator.clearAllValidations();
	
	
	if(retVal)
	{
		
	
		document.forms[0].hmode.value = "UPDATE";

		document.forms[0].submit();
		
	}		
	
}	