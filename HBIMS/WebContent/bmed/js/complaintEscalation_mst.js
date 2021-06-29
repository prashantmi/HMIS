

function buttonLogicsOnClick1(mode)
{
	alert('www'+mode);
	       var Obj = document.getElementsByName("combo");
   	 	
 	       if(Obj[0].value =="0")
		   {
				alert("Please Select Engineering Item Type!!!");
				Obj[0].focus();
				return ;
			}
		    else
			{ 
				if(Obj[1].value =="0")
				{
				   alert("Please Select Engineering Item Sub Type!!!");
				   Obj[1].focus();
				   return ;	
				}
				else
				{
					 add(mode); 	
				}
   	 	   	 		
		   	}

}

function validate1()
 {
	 var hisValidator = new HISValidator("complaintMstBean");
	 hisValidator.addValidation("strEmpNameId","dontselect=0","Please Select a Employee Name!!!");
  	 hisValidator.addValidation("strLevelType","dontselect=0","Please Select Level type!!!");
     hisValidator.addValidation("strUnitId","dontselect=0","Please Select a Unit!!!");
  	 hisValidator.addValidation("strPeriod","req","Period is a mandatory field");
  	 
	
	 var retVal = hisValidator.validate(); 
    hisValidator.clearAllValidations(); 
	
	
	 if(retVal)
	 {
		
	
		document.forms[0].hmode.value = "SAVE";

		document.forms[0].submit();
		
	 }		
	
}	


function validateUpdate()
{
	 var hisValidator = new HISValidator("complaintMstBean");
	 hisValidator.addValidation("strUnitId","dontselect=0","Please Select a Unit!!!");
  	 hisValidator.addValidation("strPeriod","req","Period is a mandatory field");
  	 
	
	var retVal = hisValidator.validate(); 
    hisValidator.clearAllValidations(); 
	
	
	if(retVal)
	{
		
	
		document.forms[0].hmode.value = "UPDATE";

		document.forms[0].submit();
		
	}		
	
}	
function getEmpInformation()
{	
   var mode ="EMPINFO"; 
   var url="ComplaintEscalationMstCNT.cnt?hmode="+mode+"&empNo="+document.forms[0].strEmpNameId.value;
   ajaxFunction(url,"1");
} 
function getAjaxResponse(res,mode)
{
	var objVal;
	if(mode=="1")
	{	
	   var err = document.getElementById("errMsg");
	   var temp1 = res.split("####");
	   if(temp1[0] == "ERROR")
	   {
         err.innerHTML = temp1[1];	
       }
       else
       {
       	        var temp = res.split("@@");
       	       	objVal= document.getElementById("contactNoDiv");
       	       	if(temp[0]=='')
       	       	{
       	       		objVal.innerHTML = "----";
       	       		document.forms[0].strContactNo.value = "0";
       	       	}	
       	       	else
       	       	{
       	       	    objVal.innerHTML = temp[0];
       	       	    document.forms[0].strContactNo.value = temp[0];
       	       	}
				
				
				objVal1= document.getElementById("emailIdDiv");
					if(temp[0]=='')
					{
       	       		  objVal1.innerHTML = "----";
       	       		  document.forms[0].strEmailId.value ="NA";
					}
       	       	    else
       	       	    {
       	       	    objVal1.innerHTML = temp[1];
       	       	    document.forms[0].strEmailId.value = temp[1];
       	       	    }	
				
				
		  
        }
       
    }
}

function ResetPage()
{
	document.getElementById("errMsg").innerHTML = "";
	document.getElementById("contactNoDiv").innerHTML = "";
	document.getElementById("emailIdDiv").innerHTML = "";
	document.forms[0].reset();
	
}