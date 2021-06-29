



function validateOnSave()
{
    var valid=false;
   if(isEmpty(document.getElementsByName('roleName')[0],"Role Name") )
		 	 {
			  valid=true;
			 }
			 else { valid=false;}
	return 	valid;	      
  }



function clearForm()
{
 document.getElementsByName('roleName')[0].value="";
}