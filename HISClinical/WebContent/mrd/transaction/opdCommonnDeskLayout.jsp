<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/master.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/>
<his:css src="/hisglobal/css/calendar-blue2.css"/>

<his:javascript src="/registration/js/registration.js" />
<his:javascript src="/registration/js/calendar.js" />
<his:javascript src="/registration/js/validationCommon.js" />
<his:javascript src="/registration/js/validationCalls.js" />
<his:javascript src="/registration/js/commonFunctions.js" />
<his:javascript src="/registration/js/dateFunctions.js" />
<his:javascript src="/registration/js/popup.js" />
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<his:javascript src="/opd/opdJs/opd.js" />
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<script>
<!--

var st1;
var secs;
var timerID = null;
var timerRunning = false;
var delay = 1000;
var currentDate=null;
var dateAsString=null;
var request;

function checkToSubmit()
{
	//alert("dfdfdfdf");
	var doc="";
	var fele=document.getElementById("opdDeskFrame");
	if(window.XMLHttpRequest){
 	doc=frmElement.contentDocument;
 	} 
 	else if (window.ActiveXObject)
 	{
 	doc=frmElement.Document;
	}
	
	// alert(doc);
	var frm=doc.forms[0];
	frm.mode.value="unspecified";

	frm.hmode.value="unspecified";

	// alert(frm.action)
	// alert(frm);
	frm.submit();
	send();
}
function callThisOnload(){	
	// alert("sdsd "+document.getElementById("deskSubHeaderFrameId"))
	var frmElement=document.getElementById("deskSubHeaderFrameId")  ;
	var doc="";
	//alert("frmElement "+window.frameElement[0])
	if(window.XMLHttpRequest){
 	doc=frmElement.contentDocument;
 	} 
 	else if (window.ActiveXObject)
 	{
 	doc=frmElement.Document;
	}
	// alert("doc "+doc)
	var frm=doc.forms[0];
 	// alert("frm "+frm);
	//alert("dssdsdsdsdsd "+frmElement.date);
	//alert("date"+frmElement.date.value);
//	alert("in on load");
	//alert(frm.date.value);
	//alert(frm.dateAsString.value);
	
	currentDate=convertStrToDate(frm.date.value,frm.dateAsString.value);
	//new Date(frm.date.value);
// 	alert("currentDate "+currentDate);
	
	dateAsString=frm.dateAsString.value;
//	alert("currentDate"+currentDate);
	InitializeTimer();
	sendData();
}

function convertStrToDate(_date,_dateAsString)
{
	//alert(_date);
	//alert(_dateAsString);
	
	var fT=["dd","MM","yyyy","hh","mm","ss"];	// formatTokens
	var value= new Array(6);
	
	// Date  As String is in Date Format dd/mm/yyyy hh:mm
	var _fDAS="dd/MM/yyyy hh:mm";
	
	for(var i=0;i<fT.length;i++)
	{
		var beg=_fDAS.indexOf(fT[i]);
		if(beg!=-1)
		{
			var len=fT[i].length;
			value[i]=parseInt(_dateAsString.substr(beg,len));
		}
	}
	var beg=_date.indexOf(":",_date.indexOf(":")+1)+1;
	value[5]=parseInt(_date.substr(beg,fT[5].length));
	
	//alert(value);
	return new Date(value[2],value[1]-1,value[0],value[3],value[4],value[5]);	
}


function InitializeTimer()
{
//	alert("in init");
    // Set the length of the timer, in seconds
    secs = 60;
    StopTheClock();
    StartTheTimer();
}

function StopTheClock()
{	
//	alert("in stop clock");
    if(timerRunning)
        clearTimeout(timerID)
    timerRunning = false
}

function StartTheTimer()
{	// alert("in StartTheTimer");
    if (secs==0)
    {
        StopTheClock();
       var newYear=dateAsString.substring(dateAsString.lastIndexOf('/')+1,dateAsString.lastIndexOf(' '));
		
		//alert(newYear);
		
		//alert("old date"+currentDate);
        //currentDate.setDate(10);
     //   alert("seconds"+currentDate.getSeconds());
     	currentDate.setSeconds(currentDate.getSeconds()+60);
     	
     	var newMin=currentDate.getMinutes();
       // alert(currentDate.getMinutes());
        if(currentDate.getMinutes()>=0 && currentDate.getMinutes()<=9)
     	{
     		//	alert("in if");
     		 newMin="0"+newMin;
     		 
     	}
     	
	//	alert("new date"+currentDate.toString());
	//	alert(currentDate.getYear());
		var newMonth=currentDate.getMonth()+1;
		var newDay=currentDate.getDate();
		var newHour=currentDate.getHours(); 
		
		if(newMonth<=9){
			newMonth="0"+newMonth;
		}
		var newDate=newDay+"/"+newMonth+"/"+newYear+" "+newHour+":"+newMin;
	//	alert("check new date"+newDate);
   //     alert("You have just wasted 10 seconds of your life.")
        // var form=document.getElementById("dateTdId");
        var frmElement=document.getElementById("deskSubHeaderFrameId")  ;
		var doc="";
		//alert("frmElement "+window.frameElement[0])
		if(window.XMLHttpRequest){
	 	doc=frmElement.contentDocument;
	 	} 
	 	else if (window.ActiveXObject)
	 	{
	 	doc=frmElement.Document;
		}
		// alert("doc "+doc)
		var form=doc.getElementById("dateTdId");
	 // 	alert("frm "+form);
    //  alert("check"+form);
     	form.innerHTML="<b><font color='#000000' size='2'"+
		"face='Verdana, Arial, Helvetica, sans-serif'> Date And Time "
		+newDate+"</font></b>";
   //   	alert("check"+form.innerHTML);
      	InitializeTimer();
     	sendData();
    }
    else
    {
        self.status = secs
        secs = secs - 1
      	timerRunning = true
        timerID = self.setTimeout("StartTheTimer()", delay)
    }
}


var queryString; //will hold the POSTed data
function sendData(){
// alert("unit============== "+document.getElementsByName("unitCode")[0].value);
var frmElement=document.getElementById("deskSubHeaderFrameId")  ;
	var doc="";
	//alert("frmElement "+window.frameElement[0])
	if(window.XMLHttpRequest){
 	doc=frmElement.contentDocument;
 	} 
 	else if (window.ActiveXObject)
 	{
 	doc=frmElement.Document;
	}
// 	alert("doc "+doc)
	var frm=doc.forms[0];

// alert("sdsdsdsdsdd "+frm.unitCode.value);
var unitCode=frm.unitCode.value;
var roomCode=frm.roomCode.value; 
// alert("unitCode "+unitCode+" roomCode "+roomCode);
 //alert("room============== "+document.getElementsByName("roomCode")[0].value);

 var url='/HISClinical/getValue?UNITCODE='+unitCode+'&ROOMCODE='+roomCode;
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
 request.send(queryString);
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

 function handleResponse(){
 
 if(request.readyState == 4){
 if(request.status == 200){
var refresfPatListButtonId=document.getElementById("refresfPatListButton");
// alert("sdsdsdkkkkkkkkkkkkkkkkkkkkkk")
//alert(refresfPatListButtonId.style.backgroundColor)
 var frmElement=document.getElementById("deskSubHeaderFrameId");
 var doc="";
 	if(window.XMLHttpRequest){
 	doc=frmElement.contentDocument;
 	} 
 	else if (window.ActiveXObject)
 	{
 	doc=frmElement.Document;
	}
	
	
 var id=doc.getElementById("tdid");
 var patAttendedId=doc.getElementById("attendedPatId");
 var imageId=doc.getElementById("imgId");
 //alert("patAttendedId"+patAttendedId.innerHTML)
 // alert("response "+request.responseText)
   var st=request.responseText.substring(request.responseText.indexOf("^")+1,request.responseText.lastIndexOf("^"));
   var attendedPat=request.responseText.substring(request.responseText.lastIndexOf("^")+1);
  st1=request.responseText.substring(0,request.responseText.indexOf("^"));
 //alert(id.valign)
 if(st==0){
    	id.innerHTML="<div align='right' >Total No Of Patient = "+st1+" "+"</div>";
    // 	imageId.innerHTML="<div align='right' ><img id='refreshImgId' class='button'  class='button' src='../../hisglobal/images/Refresh.png'  border='0' style='cursor:pointer'  ></div>";
    	// refresfPatListButtonId.style.backgroundColor='#666699';
    	}
    	else{
    	//	id.valign='middle';
    		//alert(id.valign)
    		imageId.innerHTML="<div align='right' ><img id='refreshImgId' class='button'  class='button' src='../../hisglobal/images/Refresh.png'  border='0' style='cursor:pointer' onClick='submitForSubHeader();' ></div>";
    		id.innerHTML="<div align='right' ><font color='FF0000' face='Verdana, Arial, Helvetica, sans-serif'>"+
						   "Total No Of Patient = "+st1+"</font></div>";
			doc.forms[0].totalPatient.value=st1;
				// refresfPatListButtonId.style.backgroundColor='#CC0000';
    	}
    	
//  alert("patAttendedId"+patAttendedId.innerHTML)
patAttendedId.innerHTML="<div align='center' >No Of Attended Patient ="+attendedPat+"</div>";
 
 

 } else {
 alert("A problem occurred with communicating between "+
 "the XMLHttpRequest object and the server program.");
 }
 }//end outer if
}

function parseMessage() {
	// alert("in parse");
    var message = request.responseXML.getElementsByTagName("message")[0];
  //   alert(message);
   // setMessage(message.childNodes[0].nodeValue);
}

</script>
<%System.out.println("inside coomon layout");%>
<%try {

				%>
<%@page import="opd.OpdConfig"%>
<html>

<form id="opdCommonDeskLayoutForm" >
<head>
<meta http-equiv="pragma" content="no-cache">

<his:css src="/css/Color.css" />
<his:css src="/css/master.css" />
<his:css src="/css/hisStyle.css" />
<his:css src="/css/hisStyleExt.css" />
</head>

<%String kk="hello"; %>





<frameset rows="9%,77%,9%" border="1" bordercolor="#CC6699"
FRAMESPACING="0" TOPMARGIN="0" LEFTMARGIN="0" MARGINHEIGHT="0" MARGINWIDTH="0">

<frame id="deskSubHeaderFrameId" src="/HISClinical/opd/transaction/deskSubHeader.jsp"  scrolling="no" 
   TOPMARGIN="0" LEFTMARGIN="0" MARGINHEIGHT="0"  >
  
<frameset cols="15%,60%,15%" border="1" bordercolor="#CC6699"
FRAMESPACING="0" TOPMARGIN="0" LEFTMARGIN="0" MARGINHEIGHT="0" >

<frame id="leftMenuFrame" src="/HISClinical/opd/leftMenuOpdDesk.cnt"  scrolling="auto" 
    TOPMARGIN="0" LEFTMARGIN="0" MARGINHEIGHT="0" >
  
<frame id="opdDeskFrame" src="/HISClinical/opd/opdDesk.cnt"   scrolling="auto" 
   TOPMARGIN="0" LEFTMARGIN="0" MARGINHEIGHT="0"  >
  
<frame src="/HISClinical/opd/righttMenuOpdDesk.cnt"  scrolling="auto" 
    TOPMARGIN="0" LEFTMARGIN="0" MARGINHEIGHT="0" >
  
</frameset>

<frame src="/HISClinical/opd/transaction/opdFooter.jsp"  scrolling="no" 
    TOPMARGIN="0" LEFTMARGIN="0" MARGINHEIGHT="0" >
</frameset>

<%} catch (Exception e) {
				e.printStackTrace();
			}

		%>

</form>
</html>
		