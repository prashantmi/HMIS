function submitFormData()
{
document.forms[0].submit();
}

function submitPage(mode)
{
var flag=true;

if(mode=="GET_DUTY_LIST_EXCHANGE_WITH_EMP" && document.getElementsByName("mode")[1].checked==true)
	{
	var check=false;
	
	for(var i=0;i < document.getElementsByName("selectRequestedEmp").length ;i++)
			{
			if(document.getElementsByName("selectRequestedEmp")[i].checked==true)
				 {
				 	check=true;
					break;
				 }	//inner if closed
			}//for closed
			
			if(check==false)
				{
				flag=false;
			    alert("Please Select a Duty of Exchange Requested By Employee");
			    } 
	}//outer if closed
	else
if(mode=="GET_DUTY_LIST_REQUEST_BY_EMP" && document.getElementsByName("exchangedEmp")[0])
	{
	document.getElementsByName("exchangedEmp")[0].value="-1";
	}		 	




if(flag==true)
	{		
	 document.forms[0].hmode.value=mode;
	 document.forms[0].submit();
	 }
  		
}
function validateEmp()
{
if(document.getElementsByName("mode")[0].checked==true)
		return validateEmpForExchange();
  else
if(document.getElementsByName("mode")[1].checked==true)
		return validateEmpForChange();

}


function validateEmpForExchange()
{
var requestDateShiftCheckFlag=true;
var exchangeDateShiftCheckFlag=true;

 var requestedEmpArray="";
 var exchangeEmpArray=""; 

var checkRequestEmp=false;
var checkExchangeEmp=false;



//************************Gets the 1st emp data*************************///////////////

for(var i=0;i < document.getElementsByName('selectRequestedEmp').length;i++)
		{
		if(document.getElementsByName('selectRequestedEmp')[i].checked==true)
				{
			
				requestedEmpArray=document.getElementsByName('selectRequestedEmp')[i].value.split("@");
				checkRequestEmp=true;
				break;
				}
		}


//************************Gets the 2nd  emp data*************************///////////////

for(var i=0;i < document.getElementsByName('selectExchangedEmp').length;i++)
		{
		if(document.getElementsByName('selectExchangedEmp')[i].checked==true)
				{
				exchangeEmpArray=document.getElementsByName('selectExchangedEmp')[i].value.split("@");
				checkExchangeEmp=true;
				break;
				}
		}
		
		
		
		//alert('requestedEmpArray1---'+requestedEmpArray[1])
	//alert('exchangeEmpArray1---'+exchangeEmpArray[1])
	//alert('requestedEmpArray2---'+requestedEmpArray[2])
	//alert('exchangeEmpArray-2--'+exchangeEmpArray[2])
		

//***********Checking for whether the exchange emp date && shift is not same with the requesting emp*************************///////////////

for(var i=0;i < document.getElementsByName('selectRequestedEmp').length;i++)
		{
		
		var requestedEmpDetails=document.getElementsByName('selectRequestedEmp')[i].value.split("@");
		
		
		
		//if exchange Emp Date  == requested Emp Date  && exchange Emp Shift  == requested Emp Shift
		if(exchangeEmpArray[1]==requestedEmpDetails[1] && exchangeEmpArray[2]==requestedEmpDetails[2])
				{
				requestDateShiftCheckFlag=false;
	
		//alert('exchangeEmpArray1--'+exchangeEmpArray[1]+"----requestedEmpDetails1----"+requestedEmpDetails[1]);
		//alert('exchangeEmpArray2--'+exchangeEmpArray[2]+"----requestedEmpDetails2----"+requestedEmpDetails[2]);
		///alert("requestDateShiftCheckFlag---------"+requestDateShiftCheckFlag)
	
				break;
				}
				
		}


//***********Checking for whether the requested emp date && shift is not same with the Exchanged emp*************************///////////////

for(var i=0;i < document.getElementsByName('selectExchangedEmp').length;i++)
		{
			var exchangeEmpDetails=document.getElementsByName('selectExchangedEmp')[i].value.split("@");
		
		
		
		//if exchange Emp Date  == requested Emp Date  && exchange Emp Shift  == requested Emp Shift
		if(requestedEmpArray[1]==exchangeEmpDetails[1] && requestedEmpArray[2]==exchangeEmpDetails[2])
				{
				exchangeDateShiftCheckFlag=false;
	//	alert('requestedEmpArray1--'+requestedEmpArray[1]+"----requestedEmpArray1----"+requestedEmpArray[1]);
	///	alert('requestedEmpArray2--'+requestedEmpArray[2]+"----requestedEmpArray2----"+requestedEmpArray[2]);
	///	alert("exchangeDateShiftCheckFlag---------"+exchangeDateShiftCheckFlag)
		
				break;
				}
				
		}		
		
		
		
	
	
		
		
if(checkRequestEmp==false)
	{
		alert("Select Duty of Exchange Requested By Employee");	
		return false;
	
	}
	else
if(checkExchangeEmp==false)
	{
		alert("Select Duty of Exchange  With Employee");	
		return false;
	
	}
	else
if(requestedEmpArray[1]==exchangeEmpArray[1] && requestedEmpArray[2]==exchangeEmpArray[2])
	{	
		alert("Shift of Request By and Exchange With Employee Cannot be Same,for the Same Day");
		return false;
	}
	else
if(requestDateShiftCheckFlag==false)	
	{
	alert("Exchange is not Possible,Since the Same Shift Duty exists for Exchange Requested By ");
	return false;
	}
	else
if(exchangeDateShiftCheckFlag==false)	
	{
	alert("Exchange is not Possible,Since the Same Shift Duty exists for Exchange With ");
	return false;
	}
	else
	return true;
}




function validateEmpForChange()
{
 var requestedEmpArray="";
 var exchangeEmpArray=""; 

var checkRequestEmp=false;
var checkExchangeEmp=false;


for(var i=0;i < document.getElementsByName('selectRequestedEmp').length;i++)
		{
		if(document.getElementsByName('selectRequestedEmp')[i].checked==true)
				{
			
				requestedEmpArray=document.getElementsByName('selectRequestedEmp')[i].value.split("@");
				checkRequestEmp=true;
				break;
				}
		}




for(var i=0;i < document.getElementsByName('selectExchangedEmp').length;i++)
		{
		
				exchangeEmpArray=document.getElementsByName('selectExchangedEmp')[i].value.split("@");
			
				if(requestedEmpArray[2]==exchangeEmpArray[2])
					{
					checkExchangeEmp=true;
					break;
					}
		}
		
	//alert('requestedEmpArray1---'+requestedEmpArray[1])
	//alert('exchangeEmpArray1---'+exchangeEmpArray[1])
	//alert('requestedEmpArray2---'+requestedEmpArray[2])
	//alert('exchangeEmpArray-2--'+exchangeEmpArray[2])
	
//	alert("checkRequestEmp----"+checkRequestEmp)	
	
//	alert("checkExchangeEmp----"+checkExchangeEmp)
		
if(checkRequestEmp==false)
	{
		alert("Select Duty of Exchange Requested By Employee");	
		return false;
	
	}
	else
if(checkExchangeEmp==true)//checking whether the shift is same/different
	{	alert("Shift of Change Requested By Employee and Change With Employee Cannot be Same");
		return false;
	}
	else
	return true;
}

function validateFinalSubmit(){
     
 //   These All Fields are Mandatory
  var flag=false;
  
 var valSelectedDate=document.getElementsByName('selectedDate')[0];
 var valSysDate=document.getElementsByName('sysDate')[0];   
// alert("hi");
 
 /* if( 
  	comboValidation(document.getElementsByName('year')[0],"Year")
 && comboValidation(document.getElementsByName('month')[0],"Month")
 && comboValidation(document.getElementsByName('rosterMainCatg')[0],"Roster Main Category")
 && comboValidation(document.getElementsByName('rosterCatg')[0],"Roster Category")
 &&	comboValidation(document.getElementsByName('requestedEmp')[0],"Exchange Requested By")
 &&(document.getElementsByName('exchangedEmp')[0])
 && comboValidation(document.getElementsByName('exchangedEmp')[0],"Exchange With") 
 && validateEmp()   ///validating the Exchange Requested By  and Exchanged Employee 
  	)
	{
	flag=true;
	}
 
 */
 
 flag=true;
    return flag;
} 	
	
function finalSubmit(mode)
{
if (validateFinalSubmit()) 
	{
	  submitPage(mode);
	//alert("save");
	}
    
}//function closed




function clearForm()
 {
 
    document.getElementsByName('rosterMainCatg')[0].value="-1";
  	document.getElementsByName('rosterCatg')[0].value="-1";
    document.getElementsByName('requestedEmp')[0].value="-1";
    document.getElementsByName('exchangedEmp')[0].value="-1";
    document.getElementsByName("mode")[1].checked=true;
    
    submitPage('NEW'); 
        
 }


 
  
   function backButton() 
 { 
  history.back(); 
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
if((document.getElementsByName("exchangedEmp")[0]) && document.getElementsByName("exchangedEmp")[0].value=="-1") 
  	document.getElementsByName("exchangedEmp")[0].focus();
	else
if( (document.getElementsByName("reason")[0]) && document.getElementsByName("reason")[0].value=="") 
  	document.getElementsByName("reason")[0].focus();
	
	if(document.getElementsByName("exchangeWithMonth")[0] && document.getElementsByName("exchangeWithMonth")[1])
		checkModeWithoutSubmit();
	
} 

function checkModeWithoutSubmit()
{
if(document.getElementsByName("mode")[1].checked==true)
		{
		document.getElementsByName("exchangeWithMonth")[1].disabled=true;
		document.getElementsByName("exchangeWithMonth")[0].checked=true;
		}
		else
		document.getElementsByName("exchangeWithMonth")[1].disabled=false;
		
}


function checkMode()
{
if(document.getElementsByName("mode")[1].checked==true)
		{
	  if(document.getElementsByName("exchangeWithMonth")[0] && document.getElementsByName("exchangeWithMonth")[1])	
	  		{	
		document.getElementsByName("exchangeWithMonth")[1].disabled=true;
		document.getElementsByName("exchangeWithMonth")[0].checked=true;
			}	
		}
		else
	if(document.getElementsByName("exchangeWithMonth")[1])	
		document.getElementsByName("exchangeWithMonth")[1].disabled=false;
		
		
			var check=true;
	
		
	//alert(document.getElementsByName("mode")[0].checked);	
	
	//alert(document.getElementsByName("mode")[1].checked);
		
if(document.getElementsByName("mode")[1].checked==true)
 {	
 	var check=false;
 	
	for(var i=0;i < document.getElementsByName("selectRequestedEmp").length ;i++)
			{
			if(document.getElementsByName("selectRequestedEmp")[i].checked==true)
				 {
				 	check=true;
					break;
				 }	//inner if closed
			}//for closed
}//outer if closed
			
			if(check==false)
				  {
					flag=false;
			    	alert("Please Select a Duty of Exchange Requested By Employee");
			    	document.getElementsByName("mode")[0].checked=true;
			    	
			    if(document.getElementsByName("exchangeWithMonth")[1])	
			    	document.getElementsByName("exchangeWithMonth")[1].disabled=false;
			  	  } 
			    else
			  		document.forms[0].submit();
}
function submitRequestedEmp()
{
if(document.getElementsByName("mode")[1].checked==true)
	{
		submitFormData()
	}


}
function submitYearMonth()
{
		document.forms[0].hmode.value="GET_DUTY_LIST_REQUEST_BY_EMP";
		
		if(document.forms[0].exchangedEmp)
			document.forms[0].exchangedEmp.value="-1";
		submitFormData()
}



