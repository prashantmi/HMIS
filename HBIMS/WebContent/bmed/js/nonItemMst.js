
// For saving the data on Add Page
function validate1()
{   

     
             var hisValidator = new HISValidator("nonItemBean");
             
              hisValidator.addValidation("strEngineeringItemTypeId","dontselect=0","Please Select Engineering Item Type");
              hisValidator.addValidation("strEngineeringItemSubTypeId","dontselect=0","Please Select Engineering Item Sub Type");
	          hisValidator.addValidation("strNonItemName","req", "Non-Item Name is a Mandatory Field" );
 
            
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
     
      var hisValidator = new HISValidator("nonItemBean");
 

	          hisValidator.addValidation("strNonItemName","req", "Non-Item Name is a Mandatory Field" );
   		      
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
        var url ="NonItemMstCNT.cnt?hmode="+mode+"&enggItemType="+document.forms[0].strEngineeringItemTypeId.value;
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
			document.getElementById("enggItemSubTypeCmbDivId").innerHTML="<select name ='strEngineeringItemSubTypeId' class='comboNormal' tabindex='1' ><option value='0'>Select Value</option></select>";
		}
		else
		{
			var objVal= document.getElementById("enggItemSubTypeCmbDivId");
			objVal.innerHTML = "<select name ='strEngineeringItemSubTypeId' class='comboNormal' tabindex='1' >"+res+"</select>";		
		}
	}	
}

function clearMsg(strTmp)
{
	document.forms[0].hmode.value = strTmp;
	document.forms[0].submit();
}
