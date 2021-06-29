
// For saving the data on Add Page
function validate1()
{   

     
             var hisValidator = new HISValidator("maintenanceBean");
             
              hisValidator.addValidation("strEngineeringItemTypeId","dontselect=0","Please Select Engineering Item Type");
              hisValidator.addValidation("strEngineeringItemSubTypeId","dontselect=0","Please Select Engineering Item Sub Type");
	          hisValidator.addValidation("strMaintenanceName","req", "Maintenance Name is a Mandatory Field" );
 
            hisValidator.addValidation("strEffectiveFrom", "date","Effective From is a Mandatory Field");
            hisValidator.addValidation("strRemarks", "maxlen=100", "Remarks should have less than or equal to 100 Characters" );
            
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
     
      var hisValidator = new HISValidator("maintenanceBean");
 

	          hisValidator.addValidation("strMaintenanceName","req", "Maintenance Name is a Mandatory Field" );
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

// For populating the Engg item sub type combo based on the Engg item type combo on the Add Page
function getEnggItemSubTypeCmb()
{
        var mode = "ENGGITEMSUBTYPECMB";
        var url ="MaintenanceMstCNT.cnt?hmode="+mode+"&enggItemType="+document.forms[0].strEngineeringItemTypeId.value;
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

function clearMsg(strTemp)
{
	document.forms[0].hmode.value = strTemp;
	document.forms[0].submit();
}

function cancelPage()
{
	document.forms[0].hmode.value = "unspecified";
	document.forms[0].submit();
}