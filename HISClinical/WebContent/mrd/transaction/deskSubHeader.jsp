<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>


<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<%@page import="opd.OpdConfig"%>
<%@page import="hisglobal.utility.dynamicdesk.DynamicDeskConfig"%>

<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/master.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/>
<his:css src="/hisglobal/css/calendar-blue2.css"/>

<his:javascript src="/hisglobal/js/commonFunctions.js" />
<his:javascript src="/hisglobal/js/utilityFunctions.js" />
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<script type="text/javascript">
function callThisOnload()
{
	//alert("This is Called");
	//subHeaderAJAX();
	subHeaderOnce();
}

// Calling Sub Header function once without AJAX
function subHeaderOnce()
{
		// Patient Counts
	sendData();
		//Calling the onload method of Cosultation Inbox Frame
	sendConInboxData();		
		//Calling the onload method of Consent Inbox Frame
	sendConsentInboxData(); 
} 


// Calling Sub Header Function AJAX function
function subHeaderAJAX()
{
		// Patient Counts
	InitializeTimer();	
	sendData();
	
		//Calling the onload method of Cosultation Inbox Frame
	callThisOnLoadRightMenu();
		
		//Calling the onload method of Consent Inbox Frame
	callThisOnLoadConsentInbox(); 
}

// -------------------------------------------------------------------------------
// ************  Patient Counts for Header Functions
var st1;
var secs;
var timerID = null;
var timerRunning = false;
var delay = 1000;
var currentDate=null;
var dateAsString=null;
var request;
var queryString; //will hold the POSTed data

function InitializeTimer()
{
    secs = 60;
    StopTheClock();
    StartTheTimer();
}

function StopTheClock()
{	
    if(timerRunning)	clearTimeout(timerID);
    timerRunning = false
}

function StartTheTimer()
{
	if (secs==0)
	{
		StopTheClock();
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

function sendData()
{	
	var frm=document.forms[0];
	var unitCode=frm.unitCode.value;
	var roomCode=frm.roomCode.value;
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
	request.setRequestHeader("Content-Type","application/x-www-form-urlencoded; charset=UTF-8");
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
			 request=new ActiveXObject("Microsoft.XMLHTTP");
		if(request)
		{
			initReq(reqType,url,asynch);
			/* Unlikely to branch here, as IE uses will be able to use either 
			one of the constructors*/
		}
		else
			alert("Your browser does not permit the use of all of this application's features!");
 	}
 	else
		alert("Your browser does not permit the use of all of this application's features!");
}

function handleResponse()
{
	if(request.readyState == 4)
	{
		if(request.status == 200)
		{
			//alert(request.responseText + "    "+request.responseText.split("\\$"));
			//alert(request.responseText.substring(0,request.responseText.indexOf("$")));
			//alert(request.responseText.substring(request.responseText.indexOf("^")+1,request.responseText.lastIndexOf("^")));
			//alert(request.responseText.substring(request.responseText.lastIndexOf("^")+1));
			var totalPatients=request.responseText.split("\\$")[0];//substring(0,request.responseText.indexOf("^"));
			var isRefresh=request.responseText.split("\\$")[1];//substring(request.responseText.indexOf("^")+1,request.responseText.lastIndexOf("^"));
			var attendedPat=request.responseText.split("\\$")[2];//substring(request.responseText.lastIndexOf("^")+1);
			var pendingPat=request.responseText.split("\\$")[3];
			//alert(totalPatients+" "+isRefresh+" "+attendedPat);

			/*var totalPatId=document.getElementById("totalPatId");
			var totalPatAttendedId=document.getElementById("totalPatAttendedId");
			var totalPatPendingId=document.getElementById("totalPatPendingId");
			var imageId=document.getElementById("imgId");
		
			totalPatAttendedId.innerHTML="&nbsp;&nbsp;&nbsp;Total Attended Patients = "+attendedPat;
			totalPatPendingId.innerHTML="Total Waiting Patients = "+pendingPat;
			totalPatId.innerHTML="Total Number of Patients = "+totalPatients+"&nbsp;&nbsp;&nbsp;";
			document.getElementsByName("totalPatient")[0].value=totalPatients;
			if(isRefresh==0)
			{
				imageId.innerHTML="";
			}
			else
			{
				imageId.innerHTML="<img id='refreshImgId' title='Refresh Patients List' class='button' src='/HISClinical/hisglobal/images/Refresh.png' border='0' style='cursor:pointer' onClick='submitForSubHeader();'>";
			}*/
		
			var htmlCode = "";
			
			var imgHtmlCode = "";
			var isRefreshNum = parseInt(isRefresh);
			if(isRefreshNum != 0)
			{
				htmlCode = "Total Patients:" + totalPatients
				+ "&nbsp;&nbsp;&nbsp;&nbsp;Attended:" + attendedPat
				+ "&nbsp;&nbsp;&nbsp;&nbsp;Waiting:" + pendingPat;
				imgHtmlCode = "<img id='refreshImgId' title='Refresh Patients List' class='button' src='/HISClinical/hisglobal/images/Refresh.png' border='0' style='cursor:pointer' onClick='submitForSubHeader();'>&nbsp;&nbsp;&nbsp;&nbsp;";
			}
			else
			{
				htmlCode = "Total Patients:" + totalPatients
				+ "&nbsp;&nbsp;&nbsp;&nbsp;Attended:" + attendedPat
				+ "&nbsp;&nbsp;&nbsp;&nbsp;Waiting:" + pendingPat;
			}
			//document.getElementById("divRefreshImg").innerHTML = imgHtmlCode;
			document.getElementById("divTotalPatCount").innerHTML = htmlCode;
		}
		else
			alert("A problem occurred with communicating between the XMLHttpRequest object and the server program.");
	}
}

function submitForSubHeader()
{
	var frmCenter=parent.document.getElementById("frmDynamicDeskCenter");
	var frmDoc="";	
	if(window.XMLHttpRequest) // Mozilla
	{
 		frmDoc=frmCenter.contentDocument;
 	} 
 	else if (window.ActiveXObject)
 	{
 		frmDoc=frmCenter.Document;
	}
	var form=frmDoc.forms[0];
	//frmCenter.submitToDesk("NEW","NEW");
	frmDoc.getElementsByName("hmode")[0].value="NEW";
	frmDoc.getElementsByName("mode")[0].value="NEW";
	
	var imageId = document.getElementById("divRefreshImg");
	imageId.innerHTML="";
	sendData();

	form.submit();
}

// ************  End Patient Counts for Header Functions
// -------------------------------------------------------------------------------

// -------------------------------------------------------------------------------
// ************ For CONSULTATION INBOX 
var backGroundColor;
var CIqueryString;
var seconds;
var CITimerID = null;
var CIFrame = null;
var CONInbox = null;
var CItimerRunning = false;
var CIRequest;

function callThisOnLoadRightMenu()
{	
	CIInitializeTimer();
	sendConInboxData();
}

function getConsultationInbox()
{
	CIFrame=null;
	CONInbox=null;
	backGroundColor=null;
	// Searching in All Menus for Consultaion Inbox Tab
	var frmLeft=parent.document.getElementById("frmDynamicDeskLeftMenus");
	var frmRight=parent.document.getElementById("frmDynamicDeskRightMenus");
	var frmTop=parent.document.getElementById("frmDynamicDeskTopMenus");
	var frmBottom=parent.document.getElementById("frmDynamicDeskBottomMenus");
	var formDocLeft= null;	
	var formDocRight= null;	
	var formDocTop= null;	
	var formDocBottom= null;
	
	if(window.XMLHttpRequest) // Mozilla
	{
		formDocLeft=frmLeft.contentDocument;
		formDocRight=frmRight.contentDocument;
		formDocTop=frmTop.contentDocument;
		formDocBottom=frmBottom.contentDocument;
 	} 
 	else if (window.ActiveXObject)
 	{
 		formDocLeft=frmLeft.Document;
 		formDocRight=frmRight.Document;
 		formDocTop=frmTop.Document;
 		formDocBottom=frmBottom.Document;
	}
	if(formDocLeft!=null && formDocLeft.getElementById("CONSULTATIONINBOX"))
	{	
		CONInbox=formDocLeft.getElementById("CONSULTATIONINBOX");
		CIFrame = frmLeft;
	}
	else if(formDocRight!=null && formDocRight.getElementById("CONSULTATIONINBOX"))
	{	
		CONInbox=formDocRight.getElementById("CONSULTATIONINBOX");
		CIFrame = frmRight;
	}  
	else if(formDocTop.getElementById("CONSULTATIONINBOX"))
	{	
		CONInbox=formDocTop.getElementById("CONSULTATIONINBOX");
		CIFrame = frmTop;
	}  
	else if(formDocBottom.getElementById("CONSULTATIONINBOX"))
	{	
		CONInbox=formDocBottom.getElementById("CONSULTATIONINBOX");
		CIFrame = frmBottom;
	}
	if(CONInbox!=null)
		backGroundColor=CONInbox.style.backgroundColor;
}

function CIInitializeTimer()
{	
	seconds = 20;
	CIStopTheClock();
	CIStartTheTimer();
}

function CIStopTheClock()
{	
	if(CItimerRunning)	clearTimeout(CITimerID);
    CItimerRunning = false;
}

function CIStartTheTimer()
{
    if (seconds==0)
    {
        CIStopTheClock();
		CIInitializeTimer();
		sendConInboxData();
    }
    else
    {
        self.status = seconds;
        seconds = seconds - 1;
      	CItimerRunning = true;
        CITimerID = self.setTimeout("CIStartTheTimer()", delay);
    }
}

function CIinitReq(reqType,url,isAsynch)
{
	CIRequest.onreadystatechange=CIHandleResponse;
	CIRequest.open(reqType,url,isAsynch);
	CIRequest.setRequestHeader("Content-Type","application/x-www-form-urlencoded; charset=UTF-8");
	CIRequest.send(CIqueryString);
}

function CIHttpRequest(reqType,url,asynch)
{
	//Mozilla-based browsers
	if(window.XMLHttpRequest)
	{
		CIRequest = new XMLHttpRequest();
		CIinitReq(reqType,url,asynch);
	}
	else if (window.ActiveXObject)
	{
		CIRequest=new ActiveXObject("Msxml2.XMLHTTP");
		if (!CIRequest)
			 CIRequest=new ActiveXObject("Microsoft.XMLHTTP");
		if(CIRequest)
			CIinitReq(reqType,url,asynch);
		else
			alert("Your browser does not permit the use of all of this application's features!");
 	}
 	else
		alert("Your browser does not permit the use of all of this application's features!");
}

function CIHandleResponse()
{
 if(CIRequest.readyState == 4)
 { 	
 	if(CIRequest.status == 200)
 	{	
 		var consultaionInboxLable=(CONInbox.innerHTML);
		var index=consultaionInboxLable.indexOf('(');
		if(index!=-1)
 		{
 			consultaionInboxLable=consultaionInboxLable.substring(0,consultaionInboxLable.indexOf('('));
		}
		consultaionInboxLable=consultaionInboxLable+"("+CIRequest.responseText+")";
		var count=CIRequest.responseText;
		CONInbox.innerHTML=consultaionInboxLable;
		if(count==0)
			CONInbox.style.backgroundColor=backGroundColor;
		else
			CONInbox.style.backgroundColor='#CC0000';
	}
 	else
 		alert("A problem occurred with communicating between the XMLHttpRequest object and the server program.");
 }//end outer if
}

function sendConInboxData()
{
	if(CONInbox==null)
		getConsultationInbox();
	if(CONInbox!=null)
	{
		var url='<%=OpdConfig.OPD_URL_TO_GET_NO_OF_NEW_MAIL%>';
		CIHttpRequest("GET",url,true);
	}
}
// ************ End For CONSULTATION INBOX 
// -------------------------------------------------------------------------------


// -------------------------------------------------------------------------------
// ************ For Consent Inbox
var consentInboxbackGroundColor;
var consentInboxqueryString;
var consentSeconds;
var consentInboxTimerID = null;
var consentInboxFrame = null;
var consentInbox = null;
var consentInboxTimerRunning = false;
var consentInboxPatCrNo=null;
var consentInboxRequest;

function callThisOnLoadConsentInbox()
{	
	//alert("inside on load header fron Consent Inbox>>>>>>");
	consentInboxInitializeTimer();
	sendConsentInboxData();		
}

function getConsentInbox()
{
	consentInboxFrame=null;
	consentInbox=null;
	consentInboxbackGroundColor=null;
	
	// Searching in All Menus for Consent Inbox Tab
	var frmLeft=parent.document.getElementById("frmDynamicDeskLeftMenus");
	var frmRight=parent.document.getElementById("frmDynamicDeskRightMenus");
	var frmTop=parent.document.getElementById("frmDynamicDeskTopMenus");
	var frmBottom=parent.document.getElementById("frmDynamicDeskBottomMenus");
	var formDocLeft= null;	
	var formDocRight= null;	
	var formDocTop= null;	
	var formDocBottom= null;
	
	if(window.XMLHttpRequest) // Mozilla
	{
		formDocLeft=frmLeft.contentDocument;
		formDocRight=frmRight.contentDocument;
		formDocTop=frmTop.contentDocument;
		formDocBottom=frmBottom.contentDocument;
			
 	} 
 	else if (window.ActiveXObject)
 	{
 		formDocLeft=frmLeft.Document;
 		formDocRight=frmRight.Document;
 		formDocTop=frmTop.Document;
 		formDocBottom=frmBottom.Document;
	}
	if(formDocLeft!=null && formDocLeft.getElementById("CONSENTINBOX"))
	{	
		consentInbox=formDocLeft.getElementById("CONSENTINBOX");
		consentInboxFrame = frmLeft;
	}
	else if(formDocRight!=null && formDocRight.getElementById("CONSENTINBOX"))
	{	
		consentInbox=formDocRight.getElementById("CONSENTINBOX");
		consentInboxFrame = frmRight;
	}  
	else if(formDocTop.getElementById("CONSENTINBOX"))
	{	
		consentInbox=formDocTop.getElementById("CONSENTINBOX");
		consentInboxFrame = frmTop;
	}  
	else if(formDocBottom.getElementById("CONSENTINBOX"))
	{	
		consentInbox=formDocBottom.getElementById("CONSENTINBOX");
		consentInboxFrame = frmBottom;		
	}
	if(consentInbox!=null)
		consentInboxbackGroundColor=consentInbox.style.backgroundColor;
}

function getPatCrno()
{
	var frmCenter=parent.document.getElementById("frmDynamicDeskCenter");
	var doc="";
	if(window.XMLHttpRequest)
		doc=frmCenter.contentDocument;
	else if (window.ActiveXObject)
		doc=frmCenter.Document;
	if(typeof doc.getElementsByName('patCrNo') == 'undefined') return;
	var crnos = doc.getElementsByName('patCrNo');
	consentInboxPatCrNo=null;
	if(crnos.length>0)
		for(var i=0;i<crnos.length;i++)
			if(crnos[i].tagName=="INPUT" && crnos[i].type=="radio" && crnos[i].checked)
			{
				consentInboxPatCrNo=crnos[i].value;
				break;
			}
}

function sendConsentInboxData()
{
	//alert("alert in send data");
	if(consentInbox==null)
		getConsentInbox();
	if(consentInbox!=null)
	{
		getPatCrno();
		if(consentInboxPatCrNo!=null)
		{
			var url='/HISClinical/GetNewConsentRequestConsentInbox?PatCrNo='+consentInboxPatCrNo;
			ConsentInboxHttpRequest("GET",url,true);
		}
	}
}

function ConsentInboxHttpRequest(reqType,url,asynch)
{
	//Mozilla-based browsers
	if(window.XMLHttpRequest)
	{
		consentInboxRequest = new XMLHttpRequest();
		ConsentInboxinitReq(reqType,url,asynch);
	}
	else if (window.ActiveXObject)
	{
		consentInboxRequest=new ActiveXObject("Msxml2.XMLHTTP");
		if (! consentInboxRequest)
			 consentInboxRequest=new ActiveXObject("Microsoft.XMLHTTP");
		if(consentInboxRequest)
		{
			ConsentInboxinitReq(reqType,url,asynch);
			/* Unlikely to branch here, as IE uses will be able to use either 
			one of the constructors*/
		}
		else
			alert("Your browser does not permit the use of all of this application's features!");
 	}
 	else
		alert("Your browser does not permit the use of all of this application's features!");
}
function ConsentInboxinitReq(reqType,url,isAsynch)
{
	consentInboxRequest.onreadystatechange=consentInboxHandleResponse;
	consentInboxRequest.open(reqType,url,isAsynch);
	consentInboxRequest.setRequestHeader("Content-Type","application/x-www-form-urlencoded; charset=UTF-8");
	consentInboxRequest.send(consentInboxqueryString);
}
function consentInboxHandleResponse()
{
 if(consentInboxRequest.readyState == 4)
 {
 	if(consentInboxRequest.status == 200)
 	{
		var consentInboxLable=(consentInbox.innerHTML);
		var index=consentInboxLable.indexOf('(');
 		if(index!=-1)
 		{
 			consentInboxLable=consentInboxLable.substring(0,consentInboxLable.indexOf('('));
		}
		consentInboxLable=consentInboxLable+"("+consentInboxRequest.responseText+")";
		var count=consentInboxRequest.responseText;
		consentInbox.innerHTML=consentInboxLable;
		if(count==0)
			consentInbox.style.backgroundColor=consentInboxbackGroundColor;
		else
			consentInbox.style.backgroundColor='#CC0000';
 	}
 	else
 		alert("A problem occurred with communicating between the XMLHttpRequest object and the server program.");
 }//end outer if
}

function consentInboxInitializeTimer()
{	
    // Set the length of the timer, in seconds
	consentSeconds =30;
	consentInboxStopTheClock();
	consentInboxStartTheTimer();
}
function consentInboxStopTheClock()
{	
    if(consentInboxTimerRunning)
        clearTimeout(consentInboxTimerID);
    consentInboxTimerRunning = false;
}

function consentInboxStartTheTimer()
{
    if (consentSeconds==0)
    {
        consentInboxStopTheClock();
		consentInboxInitializeTimer();
		sendConsentInboxData();
    }
    else
    {
        self.status = consentSeconds;
        consentSeconds = consentSeconds - 1;
      	consentInboxTimerRunning = true;
        consentInboxTimerID = self.setTimeout("consentInboxStartTheTimer()", delay);
    }
}
// ************ End For Consent Inbox
// -------------------------------------------------------------------------------
</script>
</head>

<body>
<form id="deskSubheaderId" >
<his:TitleTag name="DOCTOR DESK">
	<div align="left">
		<%=(String)session.getAttribute(DynamicDeskConfig.DYNAMIC_DESK_DESK_NAME) %>
	</div>	
</his:TitleTag>

<his:ContentTag>
	<table width="100%" cellspacing="0" cellpadding="0">
		<tr>
			<!-- <td width="30%" valign="top" class="tdfonthead" nowrap>
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"><b>
				<div id="totalPatAttendedId" align="left">
					&nbsp;&nbsp;&nbsp;Total Attended Patients =
				</div>
				</b></font>
			</td>
			<td width="30%" valign="top" class="tdfonthead" nowrap>
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"><b>
				<div id="totalPatPendingId" align="center">
					Total Waiting Patients =
				</div>
				</b></font> 
			</td>
			<td width="10%" valign="top" class="tdfonthead" nowrap>
				<div id="imgId" align="center">
				</div>
			</td>
			<td width="30%" valign="top" class="tdfonthead" nowrap>
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"><b>
				<div id="totalPatId" align="right">
					Total Number of Patients = &nbsp;&nbsp;&nbsp;
				</div>			
				</b></font>
			</td> -->
			<td width="80%" valign="top" class="tdfonthead" nowrap>
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"><b>
				<div id="divTotalPatCount" align="left">
					Total Patients:&nbsp;&nbsp;&nbsp;&nbsp;Attended:&nbsp;&nbsp;&nbsp;&nbsp;Waiting:
				</div>
				</b></font>
			</td>
			<td width="10%" valign="top" class="tdfonthead" nowrap>
				<div id="divRefreshImg" align="right">					
				</div>
			</td>
		</tr>
	</table>
</his:ContentTag>

<input  type="hidden" name="unitCode" value="<%=(String)session.getAttribute(DynamicDeskConfig.DYNAMIC_DESK_UNIT_CODE)%>">
<input type="hidden" name="roomCode" value="<%=(String)session.getAttribute(DynamicDeskConfig.DYNAMIC_DESK_ROOM_CODE)%>">
<input type="hidden" name="totalPatient"/>

</form>
</body>
</html>