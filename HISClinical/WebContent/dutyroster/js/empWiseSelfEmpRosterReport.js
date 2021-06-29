function submitPage(mode)
{


if(mode=="GET_REPORT")
	{
		
	if(validateReportGeneration())
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
	&&  comboValidation(document.getElementsByName('month')[0],"Month")
	)
	{
	flag=true;
	}
 
 


    return flag;
} 	
function validateReportGeneration()
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

function clearForm()
 {
  	document.getElementsByName('year')[0].value="-1";
    document.getElementsByName('month')[0].value="-1";
    document.getElementsByName('startDate')[0].value="";
    document.getElementsByName('endDate')[0].value="";
    document.getElementsByName('printingFormat')[0].checked=true;
   //submitPage('NEW'); 
        
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
