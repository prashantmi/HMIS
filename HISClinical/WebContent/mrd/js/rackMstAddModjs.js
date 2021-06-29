window.onload=function(){
	if(document.getElementsByName('mrdSize')[0].value==1){
		document.getElementById("mrdDiv").innerHTML=null
	}	
}
function submitPage(mode)
{
	document.forms[0].hmode.value=mode;
	document.forms[0].submit();
}

function validateFinalSubmit(){
     
     // These All Fields are Mandatory
	var valid=false;
	
	if(isEmpty(document.getElementsByName('rackName')[0],"Rack Name")
	 && isSelected(document.getElementsByName('rackStatus')[0],"Rack Status")
	 && isSelected(document.getElementsByName('mrdCode')[0],"Mrd Name")
	 && validateIsActive(document.getElementsByName('isValid')[0])
	 //&& isSelected(document.getElementsByName('buildingCode')[0],"Building")
	 //&& isSelected(document.getElementsByName('blockId')[0],"Block")
	 //&& isSelected(document.getElementsByName('floorId')[0],"Floor")
		)
	{
		valid=true;
	}
	
	else 
	{
		valid=false;
	}
	
/*	else if(document.getElementsByName('itemId')[0].value=="-1")
	{
		alert("Select Item...");
		document.getElementsByName('itemId')[0].focus();
		valid=false;
	
	}	*/
	
	return valid;
 } 	
	
function validateIsActive(isActive)
{
	if(isActive){
		if (isSelected(isActive,"Is Active Status")){
			return true;                          
		}
		else{
			return false
		}	
	}
	else{
		return true;
	}
}
function finalSubmit(mode)
{
	
	if (validateFinalSubmit())
	{		
	submitPage(mode);
	}
	
}


function clearForm()
 {
   document.getElementsByName('rackName')[0].value="";
   document.getElementsByName('rackStatus')[0].value="-1";
   if(document.getElementsByName('mrdSize')[0].value!=1){
   		document.getElementsByName('mrdCode')[0].value="-1";
   }	
   document.getElementsByName('remarks')[0].value="";
   document.getElementsByName('buildingCode')[0].value="-1";
   document.getElementsByName('blockId')[0].value="-1";
   document.getElementsByName('floorId')[0].value="-1";
   document.getElementsByName('roomId')[0].value="-1";
   if(document.getElementsByName('isValid')[0])
   {
   		document.forms[0].isValid.value="-1"
   }
  
  
 }

  

 
 