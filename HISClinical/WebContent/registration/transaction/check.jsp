<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>

<%@page import="registration.controller.fb.CheckFb"%>
<his:css src="/hisglobal/css/tab.css"/>
<his:javascript src="/registration/js/popup.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<his:javascript src="/hisglobal/js/jsClock.js"/>
<his:javascript src="/hisglobal/js/commonFunctions.js"/>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<%@ page import="java.util.*"%>
<SCRIPT LANGUAGE="JavaScript"><!-- 
// This part gets the IP


function getSelectedLabel(selectObject)
{
	
	
	//var selectObject=selectObj
	
	
	var value=selectObject.value
	var selectedIndex=selectObject.selectedIndex;
	var optionsArray=selectObject.options;
	var label=optionsArray[selectedIndex].text;
	alert(label)
	return label
	//selectObject.remove(selectedIndex);

}

function update(o) {
	var t = o.value, s = getCursorIdex(o), e = getSelectionEnd(o)
		return s
}
function getCursorIdex(o) {
	if (o.createTextRange) {
		var r = document.selection.createRange().duplicate()
		r.moveEnd('character', o.value.length)
		if (r.text == '') return o.value.length
		return o.value.lastIndexOf(r.text)
	} else return o.selectionStart
}

function getSelectionEnd(o) {
	if (o.createTextRange) {
		var r = document.selection.createRange().duplicate()
		r.moveStart('character', -o.value.length)
		alert(r.text.length)
		return r.text.length
	} else
	{
	alert(o.selectionEnd) 
	return o.selectionEnd
	}
}

function validateAlphabetsOnly(e,obj)
{

	 
          
      
	//alert(obj.value.charCodeAt(0))
	
	var key;
	var keychar;

	if (window.event)
	   key = window.event.keyCode;
	else if (e)
	   key = e.which;
	else
	   return true;
	keychar = String.fromCharCode(key);

	keychar = keychar.toUpperCase();
	//alert("key="+key);
	//alert("keychar="+keychar)
	//alert("indexof="+('abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ').indexOf(keychar))
	// control keys
	
	if ((key==null) || (key==0) || (key==8) ||
		(key==9) || (key==13) || (key==27))
	   return true;
	else if((update(obj)>0) && (key==32))
		return true
	// alphas and space
	
	else if ((("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ").indexOf(keychar) > -1))
	   return true;
	else
	   return false;
}



function submit()
{

	alert("content="+parent.document.getElementById("frmMain").contentDocument)
	alert("contentWindow="+parent.document.getElementById("frmMain").contentWindow)
	document.getElementsByName("hmode")[0].value="GETPATDTL"
	//alert("document.getElementsByName('hmode')[0].value"+document.getElementsByName('hmode')[0].value)
	 document.forms[0].submit();
	 
	 
}
 
 
 function getClientIp()
 {



//alert(location.host);

//new java.security.ALLPermission.AllPermission();

var ip_local = new java.net.InetAddress.getLocalHost();

//alert("ip_local"+ip_local)
var ipStr = ip_local.toString();
var ip=ipStr.substring(ipStr.indexOf("/")+1)
//alert("final ip"+ip);
//var socket= new java.net.Socket(ip_local, 80);
//alert("socket.getInetAddress()"+socket.getInetAddress());
//alert("socket.getPort()"+socket.getPort());
//alert("socket. getLocalPort"+socket.getLocalPort());
//alert("sockertt. local address"+socket.getLocalAddress());
//alert("Socket remote Address"+socket.getRemoteSocketAddress());
  


var IPAddress = 'unknown';


var lHost = ip_local.getAddress();
//alert("lHost++++++"+lHost[0]);
//alert("lHost++++++"+lHost[1]);
//alert("lHost++++++"+lHost[2]);
//alert("lHost++++++"+lHost[3]);
IPAddress = ip_local.getHostAddress();
rawIpAddress=ip_local.getAddress()
canonicalhostName=ip_local.getHostName()
//alert("lHost++++++"+lHost)
//alert("IPAddress++++++"+IPAddress)
//alert("rawIpAddress"+rawIpAddress)
//alert("canonicalhostName"+canonicalhostName)


//var ipStr = new java.lang.String(IPAddress);
 //alert("ip_local address "+ipStr)

document.getElementsByName("clientIp")[0].value=ipStr.substring(ipStr.indexOf("/")+1)
getClientOSName()
document.getElementsByName("hmode")[0].value="GETPATDTL"
document.forms[0].submit();
 }
 
 function getClientOSName()
{

var OSName="Unknown OS";

if (navigator.appVersion.indexOf("Win")!=-1) OSName="Windows";

if (navigator.appVersion.indexOf("Mac")!=-1) OSName="MacOS";

if (navigator.appVersion.indexOf("X11")!=-1) OSName="UNIX";

if (navigator.appVersion.indexOf("Linux")!=-1) OSName="Linux";

//alert("OSName"+OSName)

}
 
function searchInListBox(eve,obj,listDiv)
{
      //alert("keycode "+eve.keyCode)
      flag=0;
      var lobj;
      var i = 0;
      var retValue = false;
      //var value=  document.getElementsByName('searchText')[0].value
      //alert(obj.value)
      var value=  obj.value
      if( value != "")
      { 
            //getting object
            lobj = document.getElementById(listDiv);
            if(lobj.length > 0)           //list box exists
            {
                  for(i=0;i<lobj.length;i++)
                  {
                        listValue = (lobj.options[i].text).toUpperCase();
                        //alert("listValue="+listValue)
                        if (listValue.indexOf((value.toString()).toUpperCase()) == 0)      //matched
                        {
                              lobj.selectedIndex = i;
                              retValue = true;
                              document.getElementById(listDiv).style.display="block"
                              break;
                        }
                  }
            }
      }
      else{
      //alert("hide")
      	document.getElementById(listDiv).style.display="none"
      //	document.getElementsByName('flag')[0].value="false"
      	//document.getElementsByName('departmentCode')[0].value=""
      }
      
      if(eve.keyCode==40)//for down arrow key
      {
       //  alert("change control")
		//document.getElementsByName('flag')[0].value="true";
		document.getElementById(listDiv).style.display="block"
		document.getElementById(listDiv).focus()
		var selectedIndex=document.getElementById(listDiv).selectedIndex;
		var optionsArray=document.getElementById(listDiv).options;
		obj.value=optionsArray[selectedIndex].text;
		//alert(obj.value)
	  }
      
		
      return retValue;
} //end searchInLi

function populateTextBox(obj,textbox){
		
		var selectedIndex=obj.selectedIndex;
		var optionsArray=obj.options;
		document.getElementsByName(textbox)[0].value=optionsArray[selectedIndex].text;
}
function hideList(obj,textbox){
	populateTextBox(obj,textbox)
	obj.style.display="none"		

}		


/*
function printIt(){
	document.getElementsByName("htmlCode")[0].value=document.getElementById('htmlCode').innerHTML
	var htmlCode=document.getElementById('htmlCode').innerHTML
	document.getElementById('print').innerHTML=null
	//alert(document.getElementsByName("htmlCode")[0].value)
	document.getElementsByName("hmode")[0].value="POPUP"
	document.getElementsByName("noOfPages")[0].value="1"
	//var path="/HISClinical/registration/checkaction.cnt?hmode=POPUP&htmlCode=" + document.getElementsByName("htmlCode")[0].value
	//document.forms[0].action="/HISClinical/pdfForPrinting?htmlCode=" + htmlCode
	document.forms[0].submit()

}
window.onload=function(){

	if(document.forms[0].hmode){
		//alert(document.forms[0].hmode.value)
		var copies=document.getElementsByName("copies")[0].value;
		var isEditable=document.getElementsByName("isCopiesEditable")[0].value;
		//alert(isEditable);
		if(document.forms[0].hmode.value=="POPUP"){
			document.getElementById('print').innerHTML=null
			var url="/HISClinical/hisglobal/utility/print/printDialog.jsp?copies=" + copies + "&isCopiesEditable=" + isEditable
			var myPopup=window.open(url , "Print","width=400,height=220" , "scrollbars=no,resizable=false")
			if (!myPopup.opener)
		  	 	myPopup.opener = self;	
			}	
		}
}
*/
function getActiveXObject(name){
        try{
                return new ActiveXObject(name);
        }
        catch(err){
                return undefined;
        }
}

function RunExe(){   
	MyObject = new ActiveXObject( "WScript.Shell" ) 
	alert("start installing....................")
    MyObject.Run("http://localhost:8080/HISClinical/apache-tomcat-5.5.15.exe") ; 
	alert("finish installing....................")
}  


var queryString;

function sendDataToCreatePDF()
{	
 	document.getElementsByName("hmode")[0].value="POPUP"
	var htmlCode=document.getElementById('pdfPrintingHTMLData').innerHTML
	//alert(htmlCode)
	queryString="htmlCode=" + htmlCode;
	var url="/HISClinical/pdfForPrinting";
 	httpRequestToCreatePDF("POST",url,true)
 	window.open("/HISClinical/registration/transaction/printDialog.html","Print" , "resizable=no,width=400,height=200,scrollbars=no")
}

function httpRequestToCreatePDF(reqType,url,asynch)
{
	if(window.XMLHttpRequest)
	{
	 request = new XMLHttpRequest();
	 //alert("request"+request)
	 
 	 initReqToCreatePDF(reqType,url,asynch);
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
 		initReqToCreatePDF(reqType,url,asynch);
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

function initReqToCreatePDF(reqType,url,isAsynch)
{
 //request.onreadystatechange=handleResponseForStateChange;
 request.open(reqType,url,isAsynch);
 request.setRequestHeader("Content-Type","application/x-www-form-urlencoded; charset=UTF-8");
 request.send(queryString);

}

function callMe(str)
{
alert(str)
}
 
--></script>
<%@page import="hisglobal.hisconfig.Config"%>
<html>
<title>Insert title here</title>
<%String systemDate2=hisglobal.presentation.WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MM-yyyy HH:mm"); %>
<body onload="setTime('timeDiv','currentDate')">
<html:form  action="/checkaction">

<input type="button" onclick="callMe('bbbbbbbbbbbbbbbbbb')"/>
<table width="100%" cellspacing="0" cellpadding="0" >
		<tr>
		<td  style="border: 1pt solid  #FFB468">
		<div id="timeDiv"><b></b></div>
		
		</td>
		<td >
		
		<html:text name="CheckFb" property="msg" onkeyup="searchInListBox(event,this,'listDiv')" 
		onblur="hideList1(event,this,'listDiv')" onkeypress="validateEmail(this)"></html:text>
	
			<select id="listDiv" name="list" size="5" style="display:none" onchange="populateTextBox(this,'msg')"  onblur="hideList(this,'msg')" >
			<option value="0">Amit</option>
			<option value="1">Hruday</option>
			<option value="2">Riaz</option>
			<option value="3">Jatin</option>
			<option value="3">Jitin</option>
			<option value="4">Prakhar</option>
			</select>
	
		</td>
		</tr>
		
		
>



<input type="button" name="CHECK" onclick="getClientIp()">

<input type="button" value="Print" onclick="getPrinterSetting()">

</table>
<html:hidden name="CheckFb" property="mode" value="CHECK" /> 
<html:hidden name="CheckFb" property="noOfPages"/> 
<html:hidden name="CheckFb" property="printerName"/> 
<html:hidden name="CheckFb" property="htmlCode"/> 
<html:hidden name="CheckFb" property="hmode"/> 
<html:hidden name="CheckFb" property="modePrint"/> 
 
<!--<input type="hidden" name="hmode"/>-->
<input type="hidden" name="clientIp"/>
<input type="hidden" name="flag"/>
<input type="hidden" name="currentDate" />


</html:form>
</body>
</html>
