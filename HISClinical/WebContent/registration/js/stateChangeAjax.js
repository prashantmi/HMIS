var queryString;

function sendDataForStateChange(obj)
{	
	var stateCode=obj.value;
	queryString='patAddStateCode='+stateCode;
	//alert("queryString = "+queryString)
	if(stateCode!="" && stateCode!='-1')
    {
 	var url="/HISClinical/DistrictListByState";
 	}
 	httpRequestForStateChange("POST",url,true)
 	
}


function httpRequestForStateChange(reqType,url,asynch)
{
	if(window.XMLHttpRequest)
	{
	 request = new XMLHttpRequest();
	 //alert("request"+request)
	 
 	 initReqForStateChange(reqType,url,asynch);
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
 		initReqForStateChange(reqType,url,asynch);
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

function initReqForStateChange(reqType,url,isAsynch)
{
 request.onreadystatechange=handleResponseForStateChange;
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
	 document.getElementById("districtCombo").innerHTML=" <select name='patAddDistrictCode' tabindex='1' style='font-family: Verdana; font-size: 12px; font-style: normal; text-decoration: none;  height:20px;width: 115px;'><option value='-1'>Select Value</option>"+responseString+"</select>"
	 document.forms[0].patAddDistrictCode.value="-1"
	 document.forms[0].patAddPIN.value=""
	 document.forms[0].patAddCity.value=""
	 document.forms[0].patIsUrban.value="0"
			
	 showLocation()
	 }
	 else
	 {
	 alert("A problem occurred with communicating between the XMLHttpRequest object and the server program.");
	 }
 }
}

