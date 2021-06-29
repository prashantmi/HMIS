<!-- 
/**
 * @author CDAC
 */
-->

<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>

<%@taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<his:css src="/hisglobal/css/Color.css" />
<his:css src="/hisglobal/css/master.css" />
<his:css src="/hisglobal/css/hisStyle.css" />
<his:css src="/hisglobal/css/hisStyleExt.css" />
<his:css src="/hisglobal/css/calendar-blue2.css" />

<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<his:javascript src="/registration/js/popup.js" />
<his:javascript src="/registration/js/calendar.js" />
<his:javascript src="/registration/js/registration.js" />
<his:javascript src="/registration/js/dateFunctions.js" />
<his:javascript src="/registration/js/validationCalls.js" />
<his:javascript src="/registration/js/commonFunctions.js" />
<his:javascript src="/registration/js/validationCommon.js" />

<his:javascript src="/opd/js/casulty_desk.js" />


<script>

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
	var fele=document.getElementById("opdDeskFrame");
	var doc=fele.contentDocument;
	alert(doc);
	var frm=doc.forms[0];
	frm.mode.value="unspecified";

	frm.hmode.value="unspecified";

	alert(frm.action)
	alert(frm);
	frm.submit();
	send();
}

function callThisOnload()
{	
	var frm=document.getElementById("csultyCommonDeskLayoutForm");
	
	currentDate=convertStrToDate(frm.date.value,frm.dateAsString.value);//new Date(frm.date.value);
	// alert(currentDate);
	
	dateAsString=frm.dateAsString.value;
	// alert("currentDate"+currentDate);
	InitializeTimer();
	sendData();
}

function convertStrToDate(_date,_dateAsString)
{
	// alert(_date);
	// alert(_dateAsString);
	
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
	// alert("in init");
	// Set the length of the timer, in seconds
	secs = 10;
	StopTheClock();
	StartTheTimer();
}

function StopTheClock()
{	
	// alert("in stop clock");
	if(timerRunning)
		clearTimeout(timerID);
	timerRunning = false;
}

function StartTheTimer()
{	
	// alert("in StartTheTimer");
	if (secs==0)
	{
		StopTheClock();

		// Here's where you put something useful that's
		// supposed to happen after the allotted time.
		// For example, you could display a message:

		var newYear=dateAsString.substring(dateAsString.lastIndexOf('/')+1,dateAsString.lastIndexOf(' '));
		
		//alert(newYear);
		
		//alert("old date"+currentDate);
		//currentDate.setDate(10);
		//alert("seconds"+currentDate.getSeconds());
		currentDate.setSeconds(currentDate.getSeconds()+10);

		var newMin=currentDate.getMinutes();
		// alert(currentDate.getMinutes());
		if(currentDate.getMinutes()>=0 && currentDate.getMinutes()<=9)
		{
			// alert("in if");
			newMin="0"+newMin;
		}

		// alert("new date"+currentDate.toString());
		// alert(currentDate.getYear());
		var newMonth=currentDate.getMonth()+1;
		var newDay=currentDate.getDate();
		var newHour=currentDate.getHours();

		if(newMonth<=9){
			newMonth="0"+newMonth;
		}
		var newDate=newDay+"/"+newMonth+"/"+newYear+" "+newHour+":"+newMin;
		// alert("check new date"+newDate);
		// alert("You have just wasted 10 seconds of your life.")
		var form=document.getElementById("dateTdId");
		// alert("check"+form.innerHTML);
		form.innerHTML="<b><font color='#000000' size='2'"+ 
						"face='Verdana, Arial, Helvetica, sans-serif'> Date And Time "
						+newDate+"</font></b>";
		// alert("check"+form.innerHTML);
		InitializeTimer();
		sendData();
	}
	else
	{
		self.status = secs;
		secs = secs - 1;
		timerRunning = true;
		timerID = self.setTimeout("StartTheTimer()", delay);
	}
}

var queryString; //will hold the POSTed data

function sendData()
{
	// alert("unit============== "+document.getElementsByName("unitCode")[0].value);
	var unitCode=document.getElementsByName("unitCode")[0].value;
	var roomCode=document.getElementsByName("roomCode")[0].value;
	// alert("room============== "+document.getElementsByName("roomCode")[0].value);

	var url='/HISClinical/getValue?UNITCODE='+unitCode+'&ROOMCODE='+roomCode;
	httpRequest("GET",url,true);
}

/* Initialize a Request object that is already constructed
 reqType: The HTTP request type such as "GET" or "POST." 
 url: The URL of the server program.
 isAsynch: Whether to send the request asynchronously or not. */

function initReq(reqType,url,isAsynch)
{
	/* Specify the function that will handle the HTTP response */
	request.onreadystatechange=handleResponse;
	request.open(reqType,url,isAsynch);
	/* set the Content-Type header for a POST request */
	request.setRequestHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
	request.send(queryString);
}

/* Wrapper function for constructing a Request object.
 Parameters:
 reqType: The HTTP request type such as GET or POST.
 url: The URL of the server program.
 asynch: Whether to send the request asynchronously or not. */

function httpRequest(reqType,url,asynch)
{
	//Mozilla-based browsers
	if(window.XMLHttpRequest)
	{
		request = new XMLHttpRequest();
		initReq(reqType,url,asynch);
	}
	else if (window.ActiveXObject)
	{
		request=new ActiveXObject("Msxml2.XMLHTTP");
		if (! request)
		{
			request=new ActiveXObject("Microsoft.XMLHTTP");
		}
		if(request)
		{
			initReq(reqType,url,asynch);
			/* Unlikely to branch here, as IE uses will be able to use either 
			one of the constructors*/
		}
		else
		{
			alert("Your browser does not permit the use of all "+ "of this application's features!");
		}
	}
	else
	{
		alert("Your browser does not permit the use of all "+ "of this application's features!");
	}
}

function handleResponse()
{

	if(request.readyState == 4)
	{
		if(request.status == 200)
		{
			var refresfPatListButtonId=document.getElementById("refresfPatListButton");
			//alert(refresfPatListButtonId.style.backgroundColor);
			var fele=document.getElementById("deskSubHeaderFrameId");
			var doc=fele.contentDocument;
	
			var id=doc.getElementById("tdid");
			var patAttendedId=doc.getElementById("attendedPatId");
			var imageId=doc.getElementById("imgId");
			//alert("patAttendedId"+patAttendedId.innerHTML);
			var st=request.responseText.substring(request.responseText.indexOf("^")+1,request.responseText.lastIndexOf("^"));
			var attendedPat=request.responseText.substring(request.responseText.lastIndexOf("^")+1);
			st1=request.responseText.substring(0,request.responseText.indexOf("^"));
			//alert(id.valign);
			if(st==0)
			{
				id.innerHTML="<div align='right' >Total No Of Patient = "+st1+"</div>";
				// 	imageId.innerHTML="<div align='right' ><img id='refreshImgId' class='button'  class='button' src='../../hisglobal/images/Refresh.png'  border='0' style='cursor:pointer'  ></div>";
				// refresfPatListButtonId.style.backgroundColor='#666699';
			}
			else
			{
				//	id.valign='middle';
				//alert(id.valign);
				imageId.innerHTML="<div align='right' ><img id='refreshImgId' class='button'  class='button' src='../../hisglobal/images/Refresh.png'  border='0' style='cursor:pointer' onClick='submitForSubHeader();' ></div>";
				id.innerHTML="<div align='right' ><font color='FF0000' face='Verdana, Arial, Helvetica, sans-serif'>"+"Total No Of Patient = "+st1+"</font></div>";
				// refresfPatListButtonId.style.backgroundColor='#CC0000';
			}
			//alert("patAttendedId"+patAttendedId.innerHTML);
			patAttendedId.innerHTML="<div align='center' >No Of Attended Patient ="+attendedPat+"</div>";
		}
		else
		{
			alert("A problem occurred with communicating between "+"the XMLHttpRequest object and the server program.");
		}
	}
}

function parseMessage()
{
	alert("in parse");
	var message = request.responseXML.getElementsByTagName("message")[0];
	alert(message);
	// setMessage(message.childNodes[0].nodeValue);
}

</script>


<%
	System.out.println("inside coomon layout");
	try
	{
%>

<%@page import="opd.OpdConfig"%>

<html>
	<head>
		<meta http-equiv="pragma" content="no-cache">
	</head>
	<body>
		<form id="csultyCommonDeskLayoutForm">
			<his:TitleTag name="CASULAITY DESK">
				<his:insertDateTag />
			</his:TitleTag>

			<table width="110%" cellspacing="0" cellpadding="0" style="background: #BBBBBB; position: relative" height="80%">
				<tr style="margin: 0%;" height="100%">
					<td width="18%" nowrap="nowrap">
						<iframe id="leftMenuFrame" style="border-color: #3C32FA; border-bottom-style: groove; border-width: medium;"
							src="/HISClinical/opd/leftMenuOpdDesk.cnt" align="middle" width="100%" height="100%">
						</iframe>
					</td>
					<td width="64%" nowrap="nowrap">
						<iframe id="opdDeskFrame" src="/HISClinical/opd/csultyDesk.cnt" style="border-color: #3C32FA; border-bottom-style: groove; border-width: medium"
							align="middle" width="100%" height="100%">
						</iframe>
					</td>
					<td width="18%" nowrap="nowrap">
						<iframe src="/HISClinical/opd/righttMenuOpdDesk.cnt" style="border-color: #3C32FA; border-bottom-style: groove; border-width: medium;"
							align="middle" width="100%" height="100%">
						</iframe>
					</td>
				</tr>
			</table>
			
			<table width="100%" cellspacing="0" cellpadding="0" height="5%" style="background: #BBBBBB">
				<tr style="margin: 0%">
				</tr>
			</table>
		</form>
	</body>
	<%
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	%>
	
	<input type="hidden" name="unitCode" value="<%=(String)session.getAttribute(OpdConfig.OPD_DESK_UNIT_CODE)%>">
	<input type="hidden" name="roomCode" value="<%=(String)session.getAttribute(OpdConfig.OPD_DESK_ROOM_CODE)%>">
</html>