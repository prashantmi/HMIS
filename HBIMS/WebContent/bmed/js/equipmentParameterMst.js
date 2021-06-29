
// For saving the data on Add Page
function validate1()
{   

     
             var hisValidator = new HISValidator("equipmentParameterMstFB");
             
             // hisValidator.addValidation("strEngineeringItemTypeId","dontselect=0","Please Select Engineering Item Type");
             // hisValidator.addValidation("strEngineeringItemSubTypeId","dontselect=0","Please Select Engineering Item Sub Type");
	          hisValidator.addValidation("strParameterName","req", "Parameter Name is a Mandatory Field" );
 
            hisValidator.addValidation("strEffectiveFrom", "date","Effective From is a Mandatory Field");
            if(document.forms[0].strEffectiveFrom.value!="")
            {
               hisValidator.addValidation("strCurrentDate","dtgtet="+document.forms[0].strEffectiveFrom.value,"Effective From Date should be Less Than Or Equal to Current Date");
            }
            hisValidator.addValidation("strRemarks", "maxlen=100", "Remarks should have less than or equal to 100 Characters" );
            
  var retVal = hisValidator.validate(); 
  hisValidator.clearAllValidations(); 

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
     
      var hisValidator = new HISValidator("equipmentParameterMstFB");
 

	          hisValidator.addValidation("strParameterName","req", "Test Name is a Mandatory Field" );
   		      hisValidator.addValidation("strEffectiveFrom", "date","Effective From is a mandatory field");
       		  hisValidator.addValidation("strRemarks", "maxlen=100", "Remarks should have less than or equal to 100 Characters");
			
			       
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

function clearMsg(strTmp)
{
	document.forms[0].hmode.value = strTmp;
	document.forms[0].submit();
}
