

function validateWhenDepartment()
 {
    var valid=false;
   if( 
       comboValidation(document.getElementsByName('deptCode')[0],"Department")
       && comboValidation(document.getElementsByName('isOptional')[0],"Is Optional")
	  )
		 	 {
			  valid=true;
			 }
			 else { valid=false;}
	return 	valid;	      
  }
  
  
function validateWhenUnit()
 {
    var valid=false;
   if( 
       comboValidation(document.getElementsByName('deptUnitCode')[0],"Unit")
       && comboValidation(document.getElementsByName('isOptional')[0],"Is Optional")
	  )
		 	 {
			  valid=true;
			 }
			 else { valid=false;}
	return 	valid;	      
  }
  
 
function validateOnAdd()
 {
 var valid=false;
    var refertype=document.getElementsByName('referType')[0];
   if(refertype.checked){
         valid= validateWhenDepartment();}
       else{
       valid=validateWhenUnit(); }
      
     return valid;
 }
 


function removeModData(obj)
 {
   document.getElementsByName('index')[0].value=obj;
    submitForm('REMOVELIST');
 }
 
 
 
 function showDeptOrUnit()
 {
   var refertype=document.getElementsByName('referType')[0];
   if(refertype.checked)
   {
     document.getElementById('deptID').style.display="block";
     document.getElementById('deptIdValue').style.display="block";
     
     document.getElementById('unitID').style.display="none";
     document.getElementById('unitIdValue').style.display="none";
   }else
   {
    document.getElementById('deptID').style.display="none";
     document.getElementById('deptIdValue').style.display="none";
     
     document.getElementById('unitID').style.display="block";
     document.getElementById('unitIdValue').style.display="block";
   }
 }
 
 
 
function validateOnSave()
{
  	//alert("inside validate");
	//alert("noOfRow "+document.getElementsByName('noOfRow')[0].value);
    var noOfRow=parseInt(document.getElementsByName('noOfRow')[0].value);
     valid=true;
      if(noOfRow=="0")
      {
       alert("Please select at least one department/unit") 
        document.getElementById('addButton').focus();
      valid=false; 
      }
     return valid;
 }  
 
 
 
