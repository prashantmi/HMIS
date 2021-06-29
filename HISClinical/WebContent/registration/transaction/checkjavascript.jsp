<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" 
 "http://www.w3.org/TR/1999/REC-html401-19991224/strict.dtd">
 <%@ page
	import="org.apache.struts.tiles.ComponentContext,registration.*"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<his:css src="/hisglobal/css/hisStyle.css" />
<his:javascript src="/registration/js/commonFunctions.js" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

 <script>
function other(){
	//alert(document.getElementById("selid").);
	if(document.getElementById("selid").value !=""){
	document.getElementById("texid").value=document.getElementById("selid").value;
	document.getElementById("selid").value="";
	document.getElementById("sid").style.display="none";
	}
}
function gettext(eve){
	//alert(document.getElementById("texid").value);
	//alert(eve.keyCode);
	document.getElementById("sid").style.display="";
	if(eve.keyCode==40){
		changeControl();
	}
	
	//document.getElementById("texid").value=document.getElementById("selid")[0].value
	//document.getElementById("tid").focus();
	//var text=document.getElementById("texid").value;
	//document.getElementById("tid").value="sdfsfsf";
}
function textarea(){
	//alert("hello");
	//sssalert(document.getElementById("selid").lable)
	document.getElementById("texid").value=document.getElementById("selid").value;
}

function hideta(eve){
		if(eve.keyCode){
		document.getElementById("texid").value=document.getElementById("selid").value;
		document.getElementById("selid").value="";
	document.getElementById("sid").style.display="none";
	
	}
	
}
function changeControl(){
	
	//alert(document.getElementById("oid1").value);
	document.getElementById("oid1").selected=true;
	document.getElementById("selid").focus();
}
 function setQueryString(){
 //initialize the top-level variable; also reset the variable to cover when
 //the user clicks multiple times
 queryString="";
 var frm = document.forms[0];
 var numberElements = frm.elements.length;
 for(var i = 0; i < numberElements; i++) {
 if(i < numberElements-1) {
 queryString += frm.elements[i].name+"="+frm.elements[i].value+"&";
 } else {
 queryString += frm.elements[i].name+"="+frm.elements[i].value;
 }
 }
}
var request;
var queryString; //will hold the POSTed data
function sendData(){
 var url="http://localhost:8080/HISClinical/getList";
 httpRequest("GET",url,true);
}

/* Initialize a Request object that is already constructed
 reqType: The HTTP request type such as "GET" or "POST." 
 url: The URL of the server program.
 isAsynch: Whether to send the request asynchronously or not. */
 function initReq(reqType,url,isAsynch){
 /* Specify the function that will handle the HTTP response */
 request.onreadystatechange=handleResponse;
 request.open(reqType,url,isAsynch);
 /* set the Content-Type header for a POST request */
 request.setRequestHeader("Content-Type",
 "application/x-www-form-urlencoded; charset=UTF-8");
 request.send(null);
}

/* Wrapper function for constructing a Request object.
 Parameters:
 reqType: The HTTP request type such as GET or POST.
 url: The URL of the server program.
 asynch: Whether to send the request asynchronously or not. */

function httpRequest(reqType,url,asynch){
 //Mozilla-based browsers
 if(window.XMLHttpRequest){
 request = new XMLHttpRequest();
 initReq(reqType,url,asynch);
 } else if (window.ActiveXObject){
 request=new ActiveXObject("Msxml2.XMLHTTP");
 if (! request){
 request=new ActiveXObject("Microsoft.XMLHTTP");
 }
 if(request){
 // initReq(reqType,url,asynch);
 /* Unlikely to branch here, as IE uses will be able to use either 
 one of the constructors*/
 } else {
 alert("Your browser does not permit the use of all "+
 "of this application's features!");}
 } else {
 alert("Your browser does not permit the use of all "+
 "of this application's features!");}
}

 function handleResponse(){
 if(request.readyState == 4){
 if(request.status == 200){
 // alert("it's a server response");
 var form=document.getElementById("tdid");
 
    // alert("check"+form.innerHTML);
   // var str=(String)request.responseText;
  //  alert(str);
  alert(request.responseText.substring(request.responseText.indexOf("^")+1));
 var st=request.responseText.substring(request.responseText.indexOf("^")+1);
 var st1=request.responseText.substring(0,request.responseText.indexOf("^"));
 alert(st);
 alert(st1);
  //String str=""+request.responseText;
  alert("handle");
  //String str=request.responseText;
  
 // alert(str);
 alert(request.responseText);   
   form.innerHTML="Select Something"+request.responseText;
    //	 alert("check"+form.innerHTML);
 
 
 
 
 

 } else {
 alert("A problem occurred with communicating between "+
 "the XMLHttpRequest object and the server program.");
 }
 }//end outer if
}

function parseMessage() {
	alert("in parse");
    var message = request.responseXML.getElementsByTagName("message")[0];
    alert(message);
   // setMessage(message.childNodes[0].nodeValue);
}
function remove(){
	
}
function checkXy(obj,eve){
	alert(eve.pageX);
	alert(eve.pageY);
	//var x=obj.getBoundingClientRect().top;
	var selObj=document.getElementById("selid");
	alert(selObj.value);
	
	alert(selObj.selectedIndex);
	var index=selObj.selectedIndex;
	alert(selObj.options[selObj.selectedIndex].text);
	selObj.options[1].selected='selected';
	var st=document.getElementById("tttii").style;
	alert(st)
	//st.position='absolute';
	//st.left=780;
///	st.top=890;
	
//	alert(st.left);
//st.display="";
}
 </script>
 <meta http-equiv="content-type" content="text/html; charset=utf-8">
 <title>Send a data tidbit</title>
</head>

<body onunload="remove()">
<%String dd=(String)request.getParameter("dd");

%>
<%=dd %>
<h3>A Few Facts About Yourself...</h3>
<form action="javascript:void%200" onsubmit=
"setQueryString();sendData();return false">


<table><tr>
<td>
<p>First name: <input type="text" name="firstname" size="20"></p>
</td>
<td>
<p>Last name: <input type="text" name="lastname" size="20"> </p>
</td>
<td>
<p>Gender: <input type="text" id="gid" name="gender" value="42" size="2"> </p>
</td>

<td>
<p>Country of origin: <input type="text" name="country" size="20"> </p>
</td>
<td id="tdid">
jkjkj
</td>
</tr>
</table>
<logic:greaterThan name="gender" value="42">
<%System.out.println("cvcvc"); %>
dfdgdgfgdfg
</logic:greaterThan>
<table>
<tr>
<td></td>
<span>
<input type="text" id="texid"  onKeyUp="gettext(event)"/>

<div id="sid" style="position: absolute; " >
<select id="selid"  multiple   onKeyPress="hideta(event)" ondblclick="other()" onclick="other()" style='position: relative;left:500;min-width:5' >
<option id="oid1" selected="selected" value="hhhh"  >hhhhh  </option>
<option value="fffff" >fffff  </option>
<option value="hjkhjk" >hjkhjk  </option>
<option value="qweqwe" >qweqwe  </option>
</select>

</div>

</span>
<div>
hello<input type="text" id="texid1"  onclick="checkXy(this,event)"   />
</div>
</tr>
</table>
<p><button type="submit">Send Data</button></p>
</form>
<div id="userIdMessage"></div>
<div><input type="file" name="lll" value="ff"  >		</div>

<span id="sid1" style="display: none;position: absolute;left: 780" class="hisStyle.css">
bye<input type="text" id="tttii" value="jjkhjkhj"/> 

 
</span>
<span> 
<img src="/HISClinical/ShowFileFromDir?IMAGE_NAME=C:\NewHeader.JPG" >
</span>
</body>
</html>
