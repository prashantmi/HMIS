	
	 
	 function validateGraphOrText(){
	 
	// alert("inside validateGraphOrText");	
	// alert("sddddd"+document.getElementsByName('graphOrText')[0].selectedIndex);
      if(document.getElementsByName('graphOrText')[0].selectedIndex==0){
         alert("Please select Report Type Option");
         document.getElementsByName('graphOrText')[0].focus(); 
         return false;   
         }
         else
         return true;
    }
  function validateTextual(){
      if(document.getElementsByName('reportType')[0].selectedIndex==0){
          alert("Please select Pdf or Rtf option");
          document.getElementsByName('reportType')[0].focus();
          return false;   
         }
         else
         return true;
    }
    
    function validateTodayOrDate(){
    alert("inside validateTodayOrDate");
     success=false;        	   
            if(document.getElementsByName('choice')[0].checked){            
            //   alert('Today option selectedwwwwwwwwww');               
                if(validateHrMin())
                {
            //      alert("validateHrMin validated");
                  success=true;     
                 }
            }
            //case Date
            if(document.getElementsByName('choice')[1].checked){
    //           alert('DateWise option selected:: Code for validation to be added later'); 
               
               valFromDate=document.getElementsByName('fromDate')[0];
	           valToDate=document.getElementsByName('toDate')[0];
	           valSysDate=document.getElementsByName('sysDate')[0];
      			
               if(compareDateCall(valFromDate,valToDate,2,"from date","to date") && compareDateCall(valToDate,valSysDate,2,"to date","system date"))
               {
                success=true;
               }             
            }  
            return success;        
    } 
    
    valFromHr="";
    valToHr="";
    valToMin="";
    valFromMin="";
    	if(typeof document.getElementsByName("fromHr")[0] != 'undefined')
		{
		if(document.getElementsByName("fromHr")[0] != null)
		{
			 valFromHr=document.getElementsByName('fromHr')[0].value;
		}
		}
		
		if(typeof document.getElementsByName("toHr")[0] != 'undefined')
		{
		if(document.getElementsByName("toHr")[0] != null)
		{
			valToHr=document.getElementsByName('toHr')[0].value;
		}
		}
		if(typeof document.getElementsByName("toMin")[0] != 'undefined')
		{
		if(document.getElementsByName("toMin")[0] != null)
		{
			valToMin=document.getElementsByName('toMin')[0].value;
		}
		}
		if(typeof document.getElementsByName("fromMin")[0] != 'undefined')
		{
		if(document.getElementsByName("fromMin")[0] != null)
		{
		 	valFromMin=document.getElementsByName('fromMin')[0].value;
		}
		}
    
   
	 
	 
    
     
	 function compareTime(){ 
	// alert("inside compareTime");
           if(valFromHr > valToHr)
            {
              alert("From hr cannot be greater than to hour");
              document.getElementsByName('fromHr')[0].focus();            
              return false;
            }
            if(valFromHr == valToHr)
            {
               if(valFromMin >= valToMin)
               {
                 alert ("From min should be less than to min");
                 document.getElementsByName('fromMin')[0].focus(); 
                 return false;                            
               }
            }
            return true;
        }   
	
	function validateHrMin(){
	   success=false;
	//   alert ("validateHrMin");
	    if(validatefromHr()&& validateToHr()&& validateFromMin() && validateToMin()){
	       if(compareTime()){
    	       success=true;
    	   }    	    
	    }       
      return success ;
	}
	
     
     
	function validatefromHr(){	
//	 alert("inside validate validatefromHr");
      valFromHr=document.getElementsByName('fromHour')[0].value;
 //     alert('valFromHr'+valFromHr);
	  if(valFromHr==null||valFromHr==""){	  	       
	      alert("Please Enter value in Hr Field");	  	      
	      document.getElementsByName('fromHr')[0].focus();
	      return false;     
	  }
	  if(valFromHr>23){
	    alert("Hr Value canont be greater than 23");
	    document.getElementsByName('fromHour')[0].focus();
	    return false;     
	  }
	  return true;	  
	} 
	
        
    function validateToHr(){	 
      valToHr=document.getElementsByName('toHour')[0].value;
	  if(valToHr==null||valToHr==""){	  	       
	      alert("Please Enter value in To Hr Field");	  	      
	      document.getElementsByName('toHour')[0].focus();
	      return false;     
	  }
	  if(valToHr>23){
	    alert("Hr Value canont be greater than 23");
	    document.getElementsByName('toHour')[0].focus();
	    return false;     
	  }
	  return true;	  
	} 
	
	function validateToMin(){	 
      valToMin=document.getElementsByName('toMin')[0].value;
	  if(valToMin==null||valToMin==""){	  	       
	      alert("Please Enter value in TO Min Field");	  	      
	      document.getElementsByName('toMin')[0].focus();
	      return false;     
	  }
	  if(valToMin>59){
	    alert("To Min Value canont be greater than 59");
	    document.getElementsByName('toMin')[0].focus();
	    return false;     
	  }
	  return true;	  
	}
	
	function validateFromMin(){	 
      valFromMin=document.getElementsByName('fromMin')[0].value;
	  if(valFromMin==null||valFromMin==""){	  	       
	      alert("Please Enter value in From Min Field");	  	      
	      document.getElementsByName('fromMin')[0].focus();
	      return false;     
	  }
	  if(valFromMin>59){
	    alert("From Min Value canont be greater than 59");
	    document.getElementsByName('fromMin')[0].focus();
	    return false;     
	  }
	  return true;	  
	}
	
	function validateGraphForAgeWiseReport(){
	// alert("validateGraphForAgeWiseReport");
      if(validateDepartmentCombo()&& validatePrimCatCombo()&& validateSecCatCombo())
          return true;
         else
          return false; 
    }
    
    function validateDepartmentCombo(){	
  //  alert("validateDepartmentCombo");
       if(document.getElementsByName('departmentCode')[0].selectedIndex==0){
         alert("Please select department");
         document.getElementsByName('departmentCode')[0].focus();
         return false;
         }
         else
         return true;       
       }
    
    function validatePrimCatCombo(){
 //   alert("validatePrimCatCombo");	
    if(document.getElementsByName('patPrimaryCatCode')[0].selectedIndex==0){
         alert("Please select Primary Category");
         document.getElementsByName('patPrimaryCatCode')[0].focus();
         return false;
         }
         else
         return true;       
       }    
    
    function validateSecCatCombo(){	
     if(document.getElementsByName('patSecondaryCatCode')[0].selectedIndex==0){
         alert("Please select Secondary Category");
         document.getElementsByName('patSecondaryCatCode')[0].focus();
         return false;
         }
         else
         return true;       
       }
       
    function compareDateCall(d1,d2,mode,l1,l2){
	//alert("compare called    "+l1 +"      " +l2);
		var valid=true;
		if(isEmpty(d1,l1) && isEmpty(d2,l2)){
			//alert("isempty");
		 if(compareDate(d1,d2, mode)){
				valid = true;
			}
		 else {
			alert(l1+" can not be greater than "+l2);
			valid = false;
			}
		}
		
		else
		valid=false;
		
		return valid;
	}       
   
function compareDate(frDate,toDate,mode)
{
	var frValue, toValue,frYear, frMon, frDay,sts = 0;

	//alert("small date " + frDate);
//	alert("big date " + toDate);
	if (frDate.value == "" || frDate == null)
	{
		//alert("if,,,,,,, 1");
		frValue = new Date;
		frYear = frValue.getYear();
		frMon = frValue.getMonth();
		frDay = frValue.getDate();
	}
	else
	{
		//alert("else1");
		if (isDate(frDate,mode) == true)
		{
		//alert("if 22222222");
			frYear = intYear;
			frMon = intMon;
			frDay = intDay;

			if (isDate(toDate,mode) == true)
			{
		//	alert("if in else");
				if (frYear > intYear)
					sts = 1;
				else
				{
					if (frYear == intYear)
					{
						if (frMon > intMon)
							sts = 1;
						else
						{
							if (frMon == intMon)
							{
								if (frDay > intDay)
									sts = 1;
							}
						}
					}
				}
			}
			else
			{
			//alert("false");
				toDate.focus();
				return false;
			}
		}
		else
		{
		//	alert("false");
			frDate.focus();
			return false;
		}
	}

	if (sts == 1)
	{
		if (frDate.value == "" || frDate == null)	{
			//validating current date with toDate
		//	alert("if................................blank");
			//getMsg(5,toDate.name);
			}
		else
			//alert(frDate.name + " is greater than " + toDate.name);

		frDate.focus();
		return false;
	}

	return true;

}

//**************** Validating Form *******************
// Empty Checking
function isEmpty(obj,name)
{//alert(obj);
if(obj!=null && obj!='undefined')
{
	if(obj.value=="" || obj.value=="-1")
	{
		alert("Please Enter the "+ name);
		obj.focus();
		return false;
	}
	//alert("return true");
	return true;
}
return false;
}