function initReq(reqType,url,isAsynch){

 /* Specify the function that will handle the HTTP response */

 request.onreadystatechange=handleResponse;
//alert("url in initreq "+url);
 request.open(reqType,url,isAsynch);

 /* set the Content-Type header for a POST request */

 request.setRequestHeader("Content-Type",

 "application/x-www-form-urlencoded; charset=UTF-8");
//alert("query String"+queryString)
 request.send(queryString);

}

function initReqPost(reqType,url,isAsynch){

 /* Specify the function that will handle the HTTP response */

 request.onreadystatechange=handlePostResponse;
//alert("reqtype in init "+reqType);
 request.open(reqType,url,isAsynch);

 /* set the Content-Type header for a POST request */

  request.setRequestHeader("Content-Type",
 "application/x-www-form-urlencoded; charset=UTF-8");


 request.send(queryString);

}

 

function httpRequest(reqType,url,asynch){

 //Mozilla-based browsers
//alert("inside httpRequest")
 if(window.XMLHttpRequest){

 request = new XMLHttpRequest();
 //alert("url in http"+url);
 initReq(reqType,url,asynch);

 } else if (window.ActiveXObject){

 request=new ActiveXObject("Msxml2.XMLHTTP");

 if (! request){

 request=new ActiveXObject("Microsoft.XMLHTTP");

 }

 if(request){

  initReq(reqType,url,asynch);

 /* Unlikely to branch here, as IE uses will be able to use either 

 one of the constructors*/

 } else {

 alert("Your browser does not permit the use of all "+

 "of this application's features!");}

 } else {

 alert("Your browser does not permit the use of all "+

 "of this application's features!");}

}

 
function httpRequestPost(reqType,url,asynch){

 //Mozilla-based browsers
//alert("inside httpRequestPost")
//alert("request type"+reqType
//alert("url"+url))
 if(window.XMLHttpRequest){

 request = new XMLHttpRequest();
//alert("reqtype before init "+reqType)
 initReqPost(reqType,url,asynch);

 } else if (window.ActiveXObject){
 
 //alert("For Internet Explorer")

 request=new ActiveXObject("Msxml2.XMLHTTP");
 
 //alert("request object:Mscml2"+request)

 if (! request){

 request=new ActiveXObject("Microsoft.XMLHTTP");
//alert("request object:Microsoft"+request)
 }

 if(request){
	//alert("request branched here")
 initReqPost(reqType,url,asynch);

 /* Unlikely to branch here, as IE uses will be able to use either 

 one of the constructors*/

 } else {

 alert("Your browser does not permit the use of all "+

 "of this application's features!");}

 } else {

 alert("Your browser does not permit the use of all "+

 "of this application's features!");}

}

 
 