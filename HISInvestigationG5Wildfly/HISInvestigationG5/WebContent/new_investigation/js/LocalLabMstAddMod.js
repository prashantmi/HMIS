function submitPage(mode)
{
	
	document.forms[0].hmode.value=mode;
	document.forms[0].submit();
}
function showTime(obj)
{
	var time=document.getElementsByName("time");
	if(obj.checked)
	{
		for(i=0;i<time.length;i++)
		{
			time[i].style.display="";
		}
		
	}
	else
	{
		
		for(i=0;i<time.length;i++)
		{
			time[i].style.display="none";
		}
		
	}
}

function validateFinalSubmit(){
     //alert("wsewq");
     // These All Fields are Mandatory
 var endTimeHH=document.getElementsByName("endTimeHH")[0].value;
 var startTimeHH=document.getElementsByName("startTimeHH")[0].value;
	if(document.getElementsByName("labCode")[0].value=="-1")
	{
		alert("Select Global Laboratory ");
		//document.forms[0].sampleName.focus();
		document.getElementsByName("labCode")[0].focus();
		return false;
	}
	/*{
	alert("combo_new ====="+document.getElementById("combo_new").value);
	return null;
	}*/
	var combocheck=document.getElementById("combo_new")[0].value;
	
	{
	if(combocheck=="0")
		{}
	else
		{
		/* alert("har");*/
		
		/*if(document.getElementByName("entryuser").checked = true)
			{
		var check=document.getElementsByName("entryuser")[1].value;
		     return true;
			}
		else if(document.getElementByName("validation").checked = true)
			{
		var check=document.getElementsByName("validation")[1].value;
		    return true;
			}
		else if(document.getElementByName("revalidation").checked = true)
			{
		var check=document.getElementsByName("revalidation")[1].value;
		  return true;
		}*/
		document.getElementsByName("entryuser")[0].value;
		document.getElementsByName("validation")[0].value;
		document.getElementsByName("revalidation")[0].value;
		
		/*document.getElementsByName("entryuser")[1].value;
		document.getElementsByName("validation")[1].value;
		document.getElementsByName("revalidation")[1].value;*/
	}
	}
	
	if(document.getElementsByName("laboratoryName")[0].value=="")
	{
		alert("Enter Laboratory Name   ");
		//document.forms[0].sampleName.focus();
		document.getElementsByName("laboratoryName")[0].focus();
		return false;
	}
	if(document.getElementsByName("labShortSName")[0].value=="")
	{
		alert("Enter Laboratory  Short Name   ");
		document.getElementsByName("labShortSName")[0].focus();
		return false;                          
	}
	if(document.getElementsByName("department")[0].value=="-1")
	{
		alert("Select Department   ");
		document.forms[0].department.focus();
		return false;                          
	}
	 
	if(document.getElementsByName("numberofTests")[0].value=="")
	{
		alert("Enter No.Of Tests   ");
		document.getElementsByName("numberofTests")[0].focus();
		return false;                          
	}
	
	if(document.getElementsByName("location")[0].value=="-1")
	{
		alert("Select Location");
		document.getElementsByName("location")[0].focus();
		return false;                          
	}
	
	if(document.getElementsByName("labIncharge")[0].value=="-1")
	{
		alert("Select Lab Incharge");
		document.getElementsByName("labIncharge")[0].focus();
		return false;                          
	}
	if(document.getElementsByName("isTimeBound")[0].checked)
	{
		
	if(endTimeHH.length<2 || startTimeHH.length<2 || document.getElementsByName("startTimeMM")[0].value.length<2 || document.getElementsByName("endTimeMM")[0].value.length<2 )
		{
			alert("Please Enter Time in HH:MM Format");
			return false;
		}
	if(document.getElementsByName("startTimeHH")[0].value=="")
	{
		alert("Please Enter Start Time HH");
		document.getElementsByName("startTimeHH")[0].focus();
		return false;                          
	}
	if(document.getElementsByName("startTimeMM")[0].value=="")
	{
		alert("Please Enter Start Time MM");
		document.getElementsByName("startTimeMM")[0].focus();
		return false;                          
	}
	if(document.getElementsByName("endTimeHH")[0].value=="")
	{
		alert("Please Enter End Time HH");
		document.getElementsByName("endTimeHH")[0].focus();
		return false;                          
	}
	if(document.getElementsByName("endTimeMM")[0].value=="")
	{
		alert("Please Enter End Time MM");
		document.getElementsByName("endTimeMM")[0].focus();
		return false;                          
	}
	//-------------------
	if(parseInt(document.getElementsByName("startTimeHH")[0].value)>24)
	{
		alert("Please Enter Correct Start Time HH");
		document.getElementsByName("startTimeHH")[0].focus();
		return false;                          
	}
	if(parseInt(document.getElementsByName("startTimeMM")[0].value)>59)
	{
		alert("Please Enter Correct Start Time MM");
		document.getElementsByName("startTimeMM")[0].focus();
		return false;                          
	}
	if(parseInt(document.getElementsByName("endTimeHH")[0].value)>24)
	{
		alert("Please  Enter Correct End Time HH");
		document.getElementsByName("endTimeHH")[0].focus();
		return false;                          
	}
	if(document.getElementsByName("endTimeMM")[0].value=="")
	{
		alert("Please Enter End Time MM");
		document.getElementsByName("endTimeMM")[0].focus();
		return false;                          
	}
	if(parseInt(endTimeHH)<parseInt(startTimeHH))
	{
		alert("End Time HH Should be Greater then Start Time HH");
		document.getElementsByName("endTimeHH")[0].focus();
		return false;                          
	}
	if((document.getElementsByName("endTimeHH")[0].value==document.getElementsByName("startTimeHH")[0].value)&&(parseInt(document.getElementsByName("endTimeMM")[0].value)<parseInt(document.getElementsByName("startTimeMM")[0].value)))
	{
		alert("End Time should be Greater then Start Time");
		document.getElementsByName("endTimeMM")[0].focus();
		return false;                          
	}
	if((document.getElementsByName("startTimeHH")[0].value==24)&&parseInt((document.getElementsByName("startTimeMM")[0].value>0)))
	{
		alert("Please Enter Time According to 24 Hr Clock");
		document.getElementsByName("startTimeMM")[0].focus();
		return false;                          
	}
	
	if((document.getElementsByName("endTimeHH")[0].value==24)&&(parseInt(document.getElementsByName("endTimeMM")[0].value)>0))
	{
		alert("Please Enter Time According to 24 Hr Clock");
		document.getElementsByName("endTimeMM")[0].focus();
		return false;                          
	}
	//alert(document.getElementsByName("startTimeHH")[0].value);
	//alert(document.getElementsByName("endTimeHH")[0].value);
	
	}	
	
   return true;
 } 	
	
function finalSubmit(mode)
{
	//alert("dfsf");
	if (!validateFinalSubmit()) return;
	submitPage(mode);
	
}

function clearAddForm()
 {
  
   document.getElementsByName('laboratoryName')[0].value="";
   document.getElementsByName('labCode')[0].value="-1";

   document.getElementsByName('labShortSName')[0].value="";
   document.getElementsByName('numberofTests')[0].value="9999";
   document.getElementsByName('headertext')[0].value="";
   document.getElementsByName('footerText')[0].value="";
   document.getElementsByName('remarks')[0].value="";
   document.getElementsByName('department')[0].value="-1";
   document.getElementsByName('location')[0].value="-1";
   document.getElementsByName('labIncharge')[0].value="-1";

   
   document.getElementsByName('sampleNumberConfig')[0].checked="true";
   document.getElementsByName('labNumberConfig')[0].checked="true";
   document.getElementsByName('displayHeader')[0].checked="true";
   document.getElementsByName('finalRemark')[0].checked="true";
   document.getElementsByName('labType')[0].checked="true";
   document.getElementsByName('chkMon')[0].checked="true";
   document.getElementsByName('chkTue')[0].checked="true";
   document.getElementsByName('chkWed')[0].checked="true";
   document.getElementsByName('chkThu')[0].checked="true";
   document.getElementsByName('chkFri')[0].checked="true";
   document.getElementsByName('chkSat')[0].checked="true";
   document.getElementsByName('chkSun')[0].checked="true";
   document.getElementsByName('sopbased')[0].checked="true";





   document.getElementsByName('remarks')[0].value="";
   document.getElementsByName('remarks')[0].value="";
   var displaydiv=document.getElementById('ulist');
	var displaydivcol=document.getElementById('ucol');
	displaydiv.style.display='none';
	displaydivcol.style.display='none';
   
 }
  
$(document).ready(function(){
	//alert("hi jQuery");
	   document.getElementsByName('sopbased')[0].checked="true";

	
});

 
 
  
 