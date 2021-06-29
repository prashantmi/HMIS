<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<%@page import="hisglobal.utility.dynamicdesk.DynamicDeskConfig"%>
<%@page import="inpatient.InpatientConfig"%>
<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/master.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/>
<his:css src="/hisglobal/css/calendar-blue2.css"/>

<!-- <his:javascript src="/opd/opdJs/opd.js" /> -->
<his:javascript src="/hisglobal/js/commonFunctions.js" />
<his:javascript src="/registration/js/registration.js"/>
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<script type="text/javascript">


var st1;
var secs;
var timerID = null;
var timerRunning = false;
var delay = 1000;
var currentDate=null;
var dateAsString=null;
var request;

function callThisOnload()
{
	//subHeaderAJAX();
	subHeaderOnce();
}

// Calling Sub Header function once without AJAX
function subHeaderOnce()
{
		// Patient Counts
	sendData();
} 


// Calling Sub Header Function AJAX function
function subHeaderAJAX()
{
		// Date is not required in Desk Header
	/*var form=document.forms[0];	
	currentDate=convertStrToDate(form.date.value,form.dateAsString.value);	
	dateAsString=form.dateAsString.value;*/
		
		// Patient Counts
	InitializeTimer();
	sendData();
}

/*function convertStrToDate(_date,_dateAsString)
{
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
			value[i]=parseInt(trimNum(_dateAsString.substr(beg,len)));
		}
	}
	var beg=_date.indexOf(":",_date.indexOf(":")+1)+1;
	value[5]=parseInt(trimNum(_date.substr(beg,fT[5].length)));
	return new Date(value[2],value[1]-1,value[0],value[3],value[4],value[5]);	
}

function trimNum(n)
{
	while(n.substr(0,1)=='0')	n=n.substr(1);
	return n;
}*/

function InitializeTimer()
{
    // Set the length of the timer, in seconds
    secs = 60;
    StopTheClock();
    StartTheTimer();
}

function StopTheClock()
{	
    if(timerRunning)
        clearTimeout(timerID);
    timerRunning = false
}

function StartTheTimer()
{
	if (secs==0)
	{
		StopTheClock();
		
			// Date is not required in Desk Header
		/*var newYear=dateAsString.substring(dateAsString.lastIndexOf('/')+1,dateAsString.lastIndexOf(' '));
		currentDate.setSeconds(currentDate.getSeconds()+60);
		var newMin=currentDate.getMinutes();
		if(currentDate.getMinutes()>=0 && currentDate.getMinutes()<=9)
			newMin="0"+newMin;
		var newMonth=currentDate.getMonth()+1;
		var newDay=currentDate.getDate();
		var newHour=currentDate.getHours(); 
		if(newMonth<=9)
			newMonth="0"+newMonth;
		var newDate=newDay+"/"+newMonth+"/"+newYear+" "+newHour+":"+newMin;
		var tdDate=document.getElementById("dateTdId");
     	tdDate.innerHTML="<b><font color='#000000' size='2'"+
		"face='Verdana, Arial, Helvetica, sans-serif'> Date And Time "
		+newDate+"</font></b>";*/
		
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
	var frm=document.forms[0];
	var unitCode=frm.unitCode.value;
	<%	if(InpatientConfig.IPD_NURSING_STATION_UNIT_SELECTION.equals(InpatientConfig.UNIT_SELECTION_NO))	{	%>
		unitCode=0;
	<%	}	%>
	var wardCode=frm.wardCode.value;
	var roomCode=frm.roomCode.value;
	var url='/HISClinical/ipdPatientCount?UNITCODE='+unitCode+'&WARDCODE='+wardCode+'&ROOMCODE='+roomCode;
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
			var totalPatId=document.getElementById("totalPatId");
			var todayAdmId=document.getElementById("todayAdmId");
			var todayDisId=document.getElementById("todayDisId");
			var imageId=document.getElementById("imgId");
			
			if(request.responseText.indexOf("$")!=-1)
			{
				var totalPatients=request.responseText.split("\\$")[0];
				var todayAdm=request.responseText.split("\\$")[1];
				var todayDis=request.responseText.split("\\$")[2];
				var isRefresh=request.responseText.split("\\$")[3];
			
				todayAdmId.innerHTML="&nbsp;&nbsp;&nbsp;Today Admitted Patients = "+todayAdm;
				todayDisId.innerHTML="Today Discharged Patients = "+todayDis;
				totalPatId.innerHTML="Total Number of Patients = "+totalPatients+"&nbsp;&nbsp;&nbsp;";

				if(isRefresh==0)
					imageId.innerHTML="";
				/*else
					imageId.innerHTML="<img id='refreshImgId' class='button' title='Refresh Patients List' src='/HISClinical/hisglobal/images/Refresh.png'  border='0' style='cursor:pointer' onClick='submitForSubHeader();' >";*/
				
			}
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
	form.submit();
	var imageId=document.getElementById("imgId");
	imageId.innerHTML="";
}

</script>

<form id="ipdNursingStationSubheaderId" >
<his:TitleTag name="NURSING STATION">
	<div align="left">
		<%=(String)session.getAttribute(DynamicDeskConfig.DYNAMIC_DESK_DESK_NAME) %>
	</div>	
</his:TitleTag>

<his:ContentTag>
	<table width="100%" cellspacing="0" cellpadding="0" style="background:#BBBBBB;position: relative">
		<tr>
			<td width="30%" valign="top" class="tdfonthead" nowrap>
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"><b>
				<div id="todayAdmId" align="left">
					&nbsp;&nbsp;&nbsp;Today Admitted Patients =
				</div>
				</b></font>
			</td>
			<td width="30%" valign="top" class="tdfonthead" nowrap>
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"><b>
				<div id="todayDisId" align="center">
					Today Discharged Patients = 
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
			</td>
		</tr>
	</table>
</his:ContentTag>

<input type="hidden" name="unitCode" value="<%=(String)session.getAttribute(DynamicDeskConfig.DYNAMIC_DESK_UNIT_CODE)%>">
<input type="hidden" name="wardCode" value="<%=(String)session.getAttribute(DynamicDeskConfig.DYNAMIC_DESK_WARD_CODE)%>">
<input type="hidden" name="roomCode" value="<%=(String)session.getAttribute(DynamicDeskConfig.DYNAMIC_DESK_ROOM_CODE)%>">
<input type="hidden" name="totalPatient"/>
</form>
