function setUserName(objUserCode)
{
	document.getElementsByName("userName")[0].value = objUserCode.options[objUserCode.selectedIndex].text;
}

function printPage() 
{
	window.print();
}

function showdivtoday()
{	
	document.getElementById("divfrom").style.display="";
	document.getElementById("divfromhrcontrol").style.display="";
	document.getElementById("divfromMinControl").style.display="";
	document.getElementById("divto").style.display="";
	document.getElementById("divtohrcontrol").style.display="";
	document.getElementById("divtoMinControl").style.display="";
	document.getElementById("divfromDate").style.display="none";
	document.getElementById("divfromDateControl").style.display="none";
	document.getElementById("divtoDate").style.display="none";
	document.getElementById("divtoDateControl").style.display="none";
} 
           
function showdivdatewise()
{
	document.getElementById("divfrom").style.display="none";
	document.getElementById("divfromhrcontrol").style.display="none";
	document.getElementById("divfromMinControl").style.display="none";
	document.getElementById("divto").style.display="none";
	document.getElementById("divtohrcontrol").style.display="none";
	document.getElementById("divtoMinControl").style.display="none";
	document.getElementById("divfromDate").style.display="";
	document.getElementById("divfromDateControl").style.display="";
	document.getElementById("divtoDate").style.display="";
	document.getElementById("divtoDateControl").style.display="";
}

function submitPage(mode)
{
	elmt=document.getElementsByName("reportMode")[0];  
    elmt.value=mode;
    document.forms[0].submit();
}

function showpdfrtfdiv()
{

  val=document.getElementsByName("graphOrText")[0].selectedIndex;
  if(val==1){
  	document.getElementById("divrtfpdf").style.display="";
 	document.getElementById("mydiv").style.display=""; 
	if(document.getElementById("divGraph")){   
    	document.getElementById("divGraph").style.display="none";
   }
      	
  }
  if(val==2){
    document.getElementById("divGraph").style.display="";
 		document.getElementById("divrtfpdf").style.display="none";
    	document.getElementById("mydiv").style.display="none";  
    		
    	
  }
}

function showReport()
{
	var flag = true;
	// Validate dates
	if(document.getElementsByName('choice')[1].checked)
	{
		var dtCurrent = convertStrToDate(document.getElementsByName("sysDate")[0].value,'dd-Mon-yyyy');
		var dtFrom = convertStrToDate(document.getElementsByName("fromDate")[0].value,'dd-Mon-yyyy');
		var dtTo = convertStrToDate(document.getElementsByName("toDate")[0].value,'dd-Mon-yyyy');
		if(dtFrom>dtCurrent)
		{
			alert("From Date should less than or equals to Current Date");
			document.getElementsByName("fromDate")[0].focus();
			return false;
		}
		if(dtTo>dtCurrent)
		{
			alert("To Date should less than or equals to Current Date");
			document.getElementsByName("toDate")[0].focus();
			return false;
		}
		if(dtTo<dtFrom)
		{
			alert("To Date should greater than or equals to From Date");
			document.getElementsByName("toDate")[0].focus();
			return false;
		}
	}
	else
	{
		if(document.getElementsByName("fromHour")[0].value=="")
		{
			alert("Enter From Hour");
			document.getElementsByName("fromHour")[0].focus();
			return false;
		}
		if(document.getElementsByName("fromMin")[0].value=="")
		{
			alert("Enter From Minute");
			document.getElementsByName("fromMin")[0].focus();
			return false;
		}
		if(document.getElementsByName("toHour")[0].value=="")
		{
			alert("Enter To Hour");
			document.getElementsByName("toHour")[0].focus();
			return false;
		}
		if(document.getElementsByName("toMin")[0].value=="")
		{
			alert("Enter To Minute");
			document.getElementsByName("toMin")[0].focus();
			return false;
		}
	}
	
	if(flag)	submitPage("REPORT");
	else	return;
} 
