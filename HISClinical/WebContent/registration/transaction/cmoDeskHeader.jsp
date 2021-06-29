
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

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

<script type="text/javascript">
function callThisOnload()
{
		// Patient Counts
	InitializeTimer();	
	sendData();
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
	var url='/HISClinical/cmoPatientListCount?UNITCODE='+unitCode;
	httpRequest("GET",url,true);
}

function initReq(reqType,url,isAsynch)
{
	request.onreadystatechange=handleResponse;
	request.open(reqType,url,isAsynch);
 	request.setRequestHeader("Content-Type","application/x-www-form-urlencoded; charset=UTF-8");
	request.send(queryString);
}

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
			initReq(reqType,url,asynch);
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
			var totalPatId=document.getElementById("totalPatId");
			var imageId=document.getElementById("imgId");
			if(request.responseText.indexOf("$")!=-1)
			{
				var totalPatients=request.responseText.split("\\$")[0];
				var isRefresh=request.responseText.split("\\$")[1];
				
				totalPatId.innerHTML="Total Number of Patients = "+totalPatients+"&nbsp;&nbsp;&nbsp;";
				document.getElementsByName("totalPatient")[0].value=totalPatients;
				if(isRefresh==0)
				{
					imageId.innerHTML="";
				}
				else
				{
					imageId.innerHTML="<img id='refreshImgId' class='button' title='Refresh Patients List' src='/HISClinical/hisglobal/images/Refresh.png'  border='0' style='cursor:pointer' onClick='submitForSubHeader();' >";
				}
			}
		}
		else
		{
			alert("A problem occurred with communicating between "+"the XMLHttpRequest object and the server program.");
		}
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
	form.submit();
	var imageId=document.getElementById("imgId");
	imageId.innerHTML="";
}
// ************  End Patient Counts for Header Functions
// -------------------------------------------------------------------------------
</script>
</head>

<body>
<form id="cmoDeskHeaderId" >
<his:TitleTag name="EMO DESK">
	<div align="left">
		<%=(String)session.getAttribute(DynamicDeskConfig.DYNAMIC_DESK_DESK_NAME) %>
	</div>	
</his:TitleTag>

<his:ContentTag>
	<table width="100%" cellspacing="0" cellpadding="0">
		<tr>
			<td width="50%" valign="top" class="tdfonthead" nowrap>
			</td>
			<td width="10%" valign="top" class="tdfonthead" nowrap>
				<div id="imgId" align="center">
				</div>
			</td>
			<td width="40%" valign="top" class="tdfonthead" nowrap>
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"><b>
				<div id="totalPatId" align="right">
					Total Number of Patients = &nbsp;&nbsp;&nbsp;
				</div>
				</b></font>
			</td>
		</tr>
	</table>
</his:ContentTag>

<input type="hidden" name="unitCode" value="<%=(String)session.getAttribute(DynamicDeskConfig.DYNAMIC_DESK_UNIT_CODE)%>">
<input type="hidden" name="roomCode" value="<%=(String)session.getAttribute(DynamicDeskConfig.DYNAMIC_DESK_ROOM_CODE)%>">
<input type="hidden" name="totalPatient"/>

</form>
</body>
</html>