
function submitPage(mode)
{
	
	document.forms[0].hmode.value=mode;
	document.forms[0].submit();
}

function validateFinalSubmit(){
     
     var valid=false;
     // These All Fields are Mandatory
	if(isEmpty(document.getElementsByName('rosterTypeName')[0],"Roster Name")
	&& comboValidation(document.getElementsByName('rosterCategory')[0],"Roster Category")
	&& comboValidation(document.getElementsByName('areaType')[0],"Area Type")
	&& comboValidation(document.getElementsByName('dutyType')[0],"Duty Type")
	&& comboValidation(document.getElementsByName('rosterGenerationMethod')[0],"Roster Generation Method ")
	&& validateIsActive()
	)
	{
	valid=true;
	}
		
	
    return valid;
 } 	
	
function finalSubmit(mode)
{
	if (!validateFinalSubmit()) return;
	submitPage(mode);
	
}

function clearForm()
 {
   
    document.getElementsByName('rosterTypeName')[0].value="";
    document.getElementsByName('rosterCategory')[0].value="-1"; 
    document.getElementsByName('areaType')[0].value="-1";
    document.getElementsByName('dutyType')[0].value="-1"; 
    document.getElementsByName('rosterGenerationMethod')[0].value="-1";
    
    if( document.getElementsByName('hmode')[0].value=="MODIFY"){
    	document.getElementsByName('isActive')[0].value="-1";
  	}
 }

 
function validateIsActive(){
 	var valid=true;
 	if( document.getElementsByName('hmode')[0].value=="MODIFY"){	
		valid=comboValidation(document.forms[0].isActive,"Is Active")
		
	}		
	return valid
 }