
// For saving the data on Add Page
function validate1()
{   

     
             var hisValidator = new HISValidator("MajorHeadMstFormBean");
             
             
	          hisValidator.addValidation("strMajorHeadCode","req", "Scheme Code is a Mandatory Field" );
   		      hisValidator.addValidation("strMajorHeadName", "req","Scheme Name is a mandatory field");
			  hisValidator.addValidation("strEffectiveFrom", "date","Effective From is a mandatory field");
	   		  hisValidator.addValidation("strRemarks", "maxlen=100", "Remarks should have less than or equal to 100 Characters");
			
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

// For saving the data on Modify Page
function validate2()
{   
     
      var hisValidator = new HISValidator("BudgetMstFormBean");
 

	          hisValidator.addValidation("strBudgetTypeCode","req", "Budget Type Code is a Mandatory Field" );
   		      hisValidator.addValidation("strBudgetTypeName", "req","Budget Type Name is a mandatory field");
			  hisValidator.addValidation("strEffectiveFrom", "date","Effective From is a mandatory field");
	   		  hisValidator.addValidation("strRemarks", "maxlen=100", "Remarks should have less than or equal to 100 Characters");
			
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


function getEnggItemSubTypeCmb()
{
        var mode = "ENGGITEMSUBTYPECMB";
        var url ="EquipmentTestMstCNT.cnt?hmode="+mode+"&enggItemType="+document.forms[0].strEngineeringItemTypeId.value;
 		ajaxFunction(url,"1");		

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
			document.getElementById("enggItemSubTypeCmbDivId").innerHTML="<select name ='strItemCat' class='comboNormal' ><option value='0'>Data N/A</option></select>";
		}
		else
		{
			var objVal= document.getElementById("enggItemSubTypeCmbDivId");
			objVal.innerHTML = "<select name ='strEngineeringItemSubTypeId' class='comboNormal' >"+res+"</select>";		
		}
	}	
}

function clearPage()
{
alert("ji");
document.getElementsByName("strGrantCode").value="";
document.getElementsByName("strGrantName").value="";
document.getElementsByName("strBankName").value="";
document.getElementsByName("strBranchName").value="";

}
