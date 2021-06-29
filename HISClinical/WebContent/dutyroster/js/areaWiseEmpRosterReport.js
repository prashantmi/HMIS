function submitFormData()
{
if(document.forms[0].hmode.value!="" && document.forms[0].hmode.value!="SAVE" && document.forms[0].hmode.value!="NEW")
		document.forms[0].submit();
	
}
function submitPage(mode)
{
if(mode=="GET_DUTY_AREA")
	{
	
	}

if(mode=="GET_REPORT")
	{
		
	if(validateGeneration())
		{	
		document.forms[0].hmode.value=mode;
		document.forms[0].submit();
		}
	}
	else
	{
		
	document.forms[0].hmode.value=mode;
	document.forms[0].submit();
	}
	


}

function reportValidation(){
     
 //   These All Fields are Mandatory
  var flag=false;
 
  if( 
 	  comboValidation(document.getElementsByName('year')[0],"Year")
   && comboValidation(document.getElementsByName('month')[0],"Month")
   && comboValidation(document.getElementsByName('rosterCategory')[0],"Roster Category")
   && comboValidation(document.getElementsByName('areaCode')[0],"Duty Area")
   && comboValidation(document.getElementsByName('rosterId')[0],"Roster Name")
 
	)
	{
	flag=true;
	}
 
 


    return flag;
} 	
	


function clearForm()
 {
  	document.getElementsByName('year')[0].value="-1";
    document.getElementsByName('month')[0].value="-1";
    document.getElementsByName('rosterCategory')[0].value="-1";
    document.getElementsByName('rosterId')[0].value="-1";
    document.getElementsByName('areaCode')[0].value="-1";
    document.getElementsByName('startDate')[0].value="";
    document.getElementsByName('endDate')[0].value="";
    
        
 }


 function printPage() 
 {
 var frameElement = parent.document.getElementById("f2");  
 var win = frameElement.contentWindow ;
 document.getElementById("noPrint").style.display="none"; 
 win.print(); 
  document.getElementById("noPrint").style.display="block" ; 
 } 
   function backButton() 
 { 
  //history.back();
   submitPage('NEW');
 }
  
function validateGeneration()
{

	
		var year=document.getElementsByName("year")[0].value;
		var month=document.getElementsByName("month")[0].value;
		
		var monthArray=new Array("Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec");


	if(reportValidation()==false)
		return false;
		else
     if(document.getElementsByName('startDate')[0].value!="" &&  document.getElementsByName('endDate')[0].value!="")
    	{
    	var flag=true;
    		
    	var  valFromDate=document.getElementsByName('startDate')[0];
   		var  valToDate=document.getElementsByName('endDate')[0];
    		
    		
    	var startDateArray=document.getElementById("startDate").value.split("-");		
		var endDateArray=document.getElementById("endDate").value.split("-");
		
		if(year!=startDateArray[2])
			{
			flag=false;
			alert("Please Select The  Year in Report Printing From Date,Same as the Year in Combo");
			document.getElementsByName('startDate')[0].focus();
			}
			else
		if(year!=endDateArray[2])
			{
			flag=false;
			alert("Please Select The  Year in Report Printing To Date,Same as the Year in Combo");
			document.getElementsByName('endDate')[0].focus();
			}
			else
		if(monthArray[month-1]!=startDateArray[1])
			{
			flag=false;
			alert("Please Select The  Month in Report Printing From Date,Same as the Month in Combo");
			document.getElementsByName('startDate')[0].focus();
			}
			else
		if(monthArray[month-1]!=endDateArray[1])
			{
			flag=false;
			alert("Please Select The  Month in Report Printing To Date,Same as the Month in Combo");
			document.getElementsByName('endDate')[0].focus();
			}
			else
		if(!(compareDateCall(valFromDate,valToDate,2,"From Date","To Date") ))
			{
			flag=false
			}		
		
		
			return flag;
    	
    	}
    	else
    		return true;
}


function focusOnLoad()
{
if(document.forms[0].elements[0].value=="-1")
  	document.forms[0].elements[0].focus();
  	else
if(document.forms[0].elements[1].value=="-1")
  	document.forms[0].elements[1].focus();
  	else
if(document.forms[0].elements[2].value=="-1")
  	document.forms[0].elements[2].focus();
  	else
if(document.forms[0].elements[3].value=="-1")
  	document.forms[0].elements[3].focus();
	else
if(document.forms[0].elements[4].value=="-1")
  	document.forms[0].elements[4].focus();
		else
if(document.forms[0].elements[5].value=="-1")
  	document.forms[0].elements[5].focus();
  		else
if(document.forms[0].elements[6].value=="-1")
  	document.forms[0].elements[6].focus();
  		else
if(document.forms[0].elements[7].value=="-1")
  	document.forms[0].elements[7].focus();
  	
}
