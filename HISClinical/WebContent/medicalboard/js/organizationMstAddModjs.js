

function validateOnSave()
{
    var valid=false;
   if(isEmpty(document.getElementsByName('orgName')[0],"Organozation Name")
      && comboValidation(document.getElementsByName('orgTypeID')[0],"Organization Type")
      && isEmpty(document.getElementsByName('orgAddress')[0],"Organozation Address") )
		 	 {
			  valid=true;
			 }
			 else { valid=false;}
	return 	valid;	      
  }



function clearForm()
{
  document.getElementsByName('orgName')[0].value="";
  document.getElementsByName('orgTypeID')[0].value="-1";
  document.getElementsByName('orgAddress')[0].value="";
}