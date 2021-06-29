function buttonLogicsOnClick1(mode)
{
	       var Obj = document.getElementsByName("combo");
   	 	
 	       if(Obj[0].value =="0")
		   {
				alert("Please Select Hospital Name!!!");
				Obj[0].focus();
				return ;
			}
		    else
			{ 
				if(Obj[1].value =="0")
				{
				   alert("Please Select Lab Name!!!");
				   Obj[1].focus();
				   return ;	
				}
				else
				{
					 add(mode); 	
				}
   	 	   	 		
		   	}

}
function submitforValues()
{alert(document.getElementById("strLabName").value);
	document.getElementById("hmode").value="ADD";
document.forms[0].submit();	
}

function getLabCmb()
{
        var mode = "LABCOMBO";

        var url ="HemmsDeskDelegationConfigCNT.cnt?hmode="+mode+"&parentcombo="+document.forms[0].strHospital.value;
 		ajaxFunction(url,"1");		

}
function getEmpcmb()
{
        var mode = "EMPCOMBO";

        var url ="HemmsDeskDelegationConfigCNT.cnt?hmode="+mode+"&parentcombo="+document.forms[0].strUserID.value;
 		ajaxFunction(url,"2");		

}

function getAjaxResponse(res,mode)
{

      var err = document.getElementById("strErrMsg");
      var temp = res.split("####");
      if(temp[0] == "ERROR")
	   {
          	err.innerHTML = temp[1];
          	return;
       } 


   if(mode=="1")
   { 
     
		if(res=="")
		{
			document.getElementById("LabNameDiv").innerHTML="<select name ='strLabName' class='comboNormal' ><option value='0'>Data N/A</option></select>";
		}
		else
		{
			var objVal= document.getElementById("LabNameDiv");
			objVal.innerHTML = "<select name ='strLabName' class='comboNormal' >"+res+"</select>";		
		}
	}
	if(mode=="2")
	{
		if(res=="")
		{
			document.getElementById("EmpNameDIv").innerHTML="<select name ='strEmpID' class='comboNormal' ><option value='0'>Data N/A</option></select>";
		}
		else
		{
			var objVal= document.getElementById("EmpNameDIv");
			objVal.innerHTML = "<select name ='strEmpID' class='comboNormal' >"+res+"</select>";		
		}
	}	
}

function validate1()
{   

     
             var hisValidator = new HISValidator("strComplaintDeskName");
             
             hisValidator.addValidation("strHospital","dontselect=0","Please Select Hospital Name");
              hisValidator.addValidation("strLabName","dontselect=0","Please Select Lab Name");
	         
	          hisValidator.addValidation("strUserID","dontselect=0","Please Select User Name");
              hisValidator.addValidation("strEmpID","dontselect=0","Please Select Emp Name");
	          hisValidator.addValidation("strEvent","dontselect=0","Please Select Event Type");
	         
	      //    hisValidator.addValidation("strTestName","req", "Test Name is a Mandatory Field" );
 
            hisValidator.addValidation("strEffectiveFrom", "date","Effective From is a Mandatory Field");
          //  hisValidator.addValidation("strRemarks", "maxlen=100", "Remarks should have less than or equal to 100 Characters" );
            
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
