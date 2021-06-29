var queryString;

function sendDataForCityLocation(obj)
{	
	var locationCode=obj.value;
	queryString='patAddCityLocCode='+locationCode;
	if(locationCode!="" && locationCode!='-1')
    {
 	var url="/HISClinical/DetailByCityLocation";
 	}
 	httpRequestForCityLocation("POST",url,true)
 	
}


function httpRequestForCityLocation(reqType,url,asynch)
{
	if(window.XMLHttpRequest)
	{
	 request = new XMLHttpRequest();
	 //alert("request"+request)
	 
 	 initReqForCityLocation(reqType,url,asynch);
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
 		initReqForCityLocation(reqType,url,asynch);
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

function initReqForCityLocation(reqType,url,isAsynch)
{
//alert("url"+url)
//alert("reqType"+reqType)
//alert("isAsynch"+isAsynch)
//alert("queryString"+queryString)
 request.onreadystatechange=handleResponseForCityLocation;
 request.open(reqType,url,isAsynch);
 request.setRequestHeader("Content-Type","application/x-www-form-urlencoded; charset=UTF-8");
 request.send(queryString);

}

function handleResponseForCityLocation()
{
 if(request.readyState == 4)
 {
	 if(request.status == 200)
	 {
	 
	 var responseString=request.responseText
	 var responseArray=responseString.split('^')
	
	 	document.forms[0].patAddDistrictCode.value=responseArray[0]
	 	document.forms[0].patAddPIN.value=responseArray[1]
		document.forms[0].patAddCity.value=responseArray[2]
		document.forms[0].patIsUrban.value=responseArray[3]
		
		//////Setting value in hidden field for employee////////
		
		document.forms[0].patAddDistrictCodeHidden.value=responseArray[0]
	 	document.forms[0].patAddPINHidden.value=responseArray[1]
		document.forms[0].patAddCityHidden.value=responseArray[2]
		document.forms[0].patIsUrbanHidden.value=responseArray[3]
		
	 
	 }
	 else
	 {
	 alert("A problem occurred with communicating between the XMLHttpRequest object and the server program.");
	 }
 }
}

