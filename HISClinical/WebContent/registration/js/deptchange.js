var queryString;

function sendDataForDeptChange(obj)
{	
	var departmentCode=obj.value;
	queryString='departmentCode='+departmentCode;
	//alert("queryString = "+queryString)
	if(departmentCode!="" && departmentCode!='-1')
    {
 	var url="/HISClinical/registration/offlineRegistration.cnt?hmode=GETUNIT&departmentCode="+departmentCode;
 	}
 	httpRequestForStateChange("POST",url,true,"dept")
 	
}


function httpRequestForStateChange(reqType,url,asynch,mode)
{
	if(window.XMLHttpRequest)
	{
	 request = new XMLHttpRequest();
	 //alert("request"+request)
	 
 	 initReqForStateChange(reqType,url,asynch,mode);
	}
	else
	 if (window.ActiveXObject)
	 {
 		request=new ActiveXObject("Msxml2.XMLHTTP");
		 if (! request)
		 {
		 request=new ActiveXObject("Microsoft.XMLHTTP");
		 }
		 if(request){
 		initReqForStateChange(reqType,url,asynch,mode);
		 }
		else 
		{
		 alert("Your browser does not permit the use of all of this application's features!");
		 }
	 }
	 else
	 {
		 alert("Your browser does not permit the use of all of this application's features!");
	}

}

function initReqForStateChange(reqType,url,isAsynch,mode)
{
if(mode=="dept")
 request.onreadystatechange=handleResponseForStateChange;
 else
 request.onreadystatechange=handleResponseForUnitChange;
 request.open(reqType,url,isAsynch);
 request.setRequestHeader("Content-Type","application/x-www-form-urlencoded; charset=UTF-8");
 request.send(queryString);

}

function handleResponseForStateChange()
{
 if(request.readyState == 4)
 {
	 if(request.status == 200)
	 {
	 
	 var responseString=request.responseText
	 document.getElementById("deptCodeID").innerHTML=" <select name='departmentUnitCode' tabindex='1' style='font-family: Verdana; font-size: 12px; font-style: normal; text-decoration: none;  height:20px;width: 115px;' onchange='sendDataForUnitChange(this)'><option value='-1'>Select Value</option>"+responseString+"</select>"
	 document.getElementsByName("roomCode")[0].value="-1";
	 }
	 else
	 {
	 alert("A problem occurred with communicating between the XMLHttpRequest object and the server program.");
	 document.getElementById("deptCodeID").innerHTML=" <select name='departmentUnitCode' tabindex='1' style='font-family: Verdana; font-size: 12px; font-style: normal; text-decoration: none;  height:20px;width: 115px;' onchange='sendDataForUnitChange(this)'><option value='-1'>Select Value</option></select>"
	 }
 }
}

function handleResponseForUnitChange()
{
 if(request.readyState == 4)
 {
	 if(request.status == 200)
	 {
	 
	 var responseString=request.responseText
	 document.getElementById("roomCodeID").innerHTML=" <select name='roomCode' tabindex='1' style='font-family: Verdana; font-size: 12px; font-style: normal; text-decoration: none;  height:20px;width: 115px;'><option value='-1'>Select Value</option>"+responseString+"</select>"
	 }
	 else
	 {
	 alert("A problem occurred with communicating between the XMLHttpRequest object and the server program.");
	  document.getElementById("roomCodeID").innerHTML=" <select name='roomCode' tabindex='1' style='font-family: Verdana; font-size: 12px; font-style: normal; text-decoration: none;  height:20px;width: 115px;'><option value='-1'>Select Value</option></select>"
	 }
 }
}


function sendDataForUnitChange(obj)
{	
	var departmentUnitCode=obj.value;
	queryString='departmentUnitCode='+departmentUnitCode;
	//alert("queryString = "+queryString)
	if(departmentUnitCode!="" && departmentUnitCode!='-1')
    {
 	var url="/HISClinical/registration/offlineRegistration.cnt?hmode=GETROOM&departmentUnitCode="+departmentUnitCode;
 	}
 	httpRequestForStateChange("POST",url,true,"unit")
 	
}
