
//-----------JS FUNCTIONS from JSP file-------------------------//

function goFunc()                //  for CR No. field validation
{

  
		var hisValidator = new HISValidator("billPrintTransBean");  
	 	hisValidator.addValidation("strCrNo", "req", "CR No. is a Mandatory Field" );
		 hisValidator.addValidation("strCrNo", "minlen="+document.forms[0].strCrNo.maxLength,"CR No. must be "+document.forms[0].strCrNo.maxLength+" Digits" );
		 var retVal = hisValidator.validate(); 
	 
	    if(retVal)
	    {
	           	document.forms[0].hmode.value="GO";
	           	document.forms[0].submit();
		}else{
		
		return false;
		}
}
//------------------This Function Call When We Get Cr No from Patient Listing------------------------//
function hlpOnLoad()
{   
	
 var divObj = document.getElementById("billTypeDivId");
 
   var reqValues = "0^0";
	var reqObj = document.getElementsByName("requestOption");	
	
	if(reqObj.length > 0){
		
	reqValues = reqObj[0].value;
	document.forms[0].strRequestValue.value =  reqValues;
	}
	  

  var bServiceId = reqValues.split('^')[1];
  
  if(bServiceId == '21'){
  		
  		divObj.style.display = "block";
  		
  }else{
  	
  	divObj.style.display = "none";
  	
  }
	
}



     /**
	   * initGoFunc
	   * @param {Event} eve 
	   */
      function initGoFunc(eve)
      {
      	
      	 var flag=validateData(eve,5);
  		 if(flag){
      	
		   	if(eve.keyCode == 13)
		   	{
			  return goFunc();
			   
			}	   	
  }else{
	   		return false;
	   }
	  }
//------------If Enter Key Press--------------//
function goFuncOnEnter(e)
{
   if(e.keyCode == 13)
   {
	 goFunc();
	}
	else
	{
	 return false;
	}
}  



function groupCheck(chkObj)
{ 
  
  document.forms[0].strRequestValue.value =  chkObj.value;
  
  var divObj = document.getElementById("billTypeDivId");
  
  var reqValues = chkObj.value;
  
  
  
  var bServiceId = reqValues.split('^')[1];
  
  if(bServiceId == '21'){
  		
  		divObj.style.display = "block";
  		
  }else{
  	
  	divObj.style.display = "none";
  	
  }
  
   
}


function showDetails(){
		
		divId = "onLineId";
		
		document.getElementById(divId).style.display="block";
				
		document.getElementById('minus'+divId).style.display="block";
		document.getElementById('plus'+divId).style.display="none";
		
	}
	
	
	function hideDetails(){
		
		divId = "onLineId";
		
		document.getElementById(divId).style.display="none";
			
		document.getElementById('minus'+divId).style.display="none";
		document.getElementById('plus'+divId).style.display="block";
		
	}

function printBill()                //  for CR No. field validation
{

		
		if(document.forms[0].strCrNo.value != "" ){
			
			var reqObj = document.getElementsByName("requestOption");	
	
		if(reqObj.length > 0){
		
				document.forms[0].hmode.value="PRINT";
	           	document.forms[0].submit();
		
		}else{
			
			alert("No Request Available");
			return false;
			
		}
			
		}

		
}
	
	
	
 function clearData()
 {
        document.getElementById("errMsg").innerHTML = "";
        
        document.forms[0].strCrNo.value = "";
        
	 	document.forms[0].hmode.value = "APPROVED";
  	    document.forms[0].submit();
 }
	
	
 function cancel()
 {
        document.getElementById("errMsg").innerHTML = "";
	 	document.forms[0].hmode.value = "CANCEL";
  	    document.forms[0].submit();
 }