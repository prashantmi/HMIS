<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<%@page import="hisglobal.utility.dynamicdesk.DynamicDeskConfig"%>

<%@page import="inpatient.InpatientConfig"%>
<html>
<head>
<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/master.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/>
<his:css src="/hisglobal/css/calendar-blue2.css"/>

<script type="text/javascript" src="/HISClinical/hisglobal/js/dojotoolkit/dojo/dojo.js" djConfig="parseOnLoad: true"> </script>
<his:javascript src="/registration/js/registration.js"/>
<his:javascript src="/hisglobal/js/commonFunctions.js" />
<his:javascript src="/hisglobal/js/utilityFunctions.js" />
<his:javascript src="/hisglobal/js/utilityFunctions.js" />
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<script type="text/javascript">
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
		//Calling the onload method of Consent Inbox Frame
	sendConsentInboxData(); 
} 


// Calling Sub Header Function AJAX function
function subHeaderAJAX()
{
		// Patient Counts
	InitializeTimer();
	sendData();

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
	<%	if(InpatientConfig.IPD_NURSING_DESK_UNIT_SELECTION.equals(InpatientConfig.UNIT_SELECTION_NO))	{	%>
		unitCode=0;
	<%	}	%>
	var wardCode=frm.wardCode.value;
	var roomCode=frm.roomCode.value;
	var url='/HISClinical/ipdPatientCount?UNITCODE='+unitCode+'&WARDCODE='+wardCode+'&ROOMCODE='+roomCode;
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
			var todayAdmId=document.getElementById("todayAdmId");
			var todayDisId=document.getElementById("todayDisId");
			var imageId=document.getElementById("imgId");
			
			if(request.responseText.indexOf("$")!=-1)
			{
				var totalPatients=request.responseText.split("\\$")[0];
				var todayAdm=request.responseText.split("\\$")[1];
				var todayDis=request.responseText.split("\\$")[2];
				var isRefresh=request.responseText.split("\\$")[3];
			
				document.getElementsByName("totalPatient")[0].value=totalPatients;
				
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
// ************  End Patient Counts for Header Functions
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
			else if(crnos[i].tagName=="INPUT" && crnos[i].type=="hidden")
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
// ------- Nursing Desk Data Setting ------
var deskMenuFrame = null;
var deskMenuElement=null;
var deskMenuBackgroundColor='';

function getSetDeskMenuInfo(menuURL, deskMenuId)
{
	deskMenuFrame=null;
	deskMenuElement=null;
	deskMenuBackgroundColor=null;
	
	
	
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
	if(formDocLeft!=null && formDocLeft.getElementsByName("deskMenuID#"+deskMenuId)[0])//formDocLeft.getElementById(menuURL))
	{	
		var elem = formDocLeft.getElementsByName("deskMenuID#"+deskMenuId)[0];
		deskMenuElement=elem.parentNode;		
		//deskMenuElement=formDocLeft.getElementById(menuURL);
		deskMenuFrame = frmLeft;
	}
	else if(formDocRight!=null && formDocRight.getElementsByName("deskMenuID#"+deskMenuId)[0])//formDocRight.getElementById(menuURL))
	{	
		var elem = formDocRight.getElementsByName("deskMenuID#"+deskMenuId)[0];
		deskMenuElement=elem.parentNode;
		//deskMenuElement=formDocRight.getElementById(menuURL);
		deskMenuFrame = frmRight;
	}  
	else if(formDocTop!=null && formDocTop.getElementsByName("deskMenuID#"+deskMenuId)[0])//formDocTop.getElementById(menuURL))
	{	
		var elem = formDocTop.getElementsByName("deskMenuID#"+deskMenuId)[0];
		deskMenuElement=elem.parentNode;		
		//deskMenuElement=formDocTop.getElementById(menuURL);
		deskMenuFrame = frmTop;
	}  
	else if(formDocBottom!=null && formDocBottom.getElementsByName("deskMenuID#"+deskMenuId)[0])//formDocBottom.getElementById(menuURL))
	{	
		var elem = formDocBottom.getElementsByName("deskMenuID#"+deskMenuId)[0];
		deskMenuElement=elem.parentNode;		
		//deskMenuElement=formDocBottom.getElementById(menuURL);
		deskMenuFrame = frmBottom;		
	}
	if(deskMenuElement!=null)
		deskMenuBackgroundColor=deskMenuElement.style.backgroundColor;
}


function setDeskMenuColorsOnNursingData()
{
	getPatCrno();
	if(consentInboxPatCrNo!=null)
	{
		var objMenuWiseDataFlag = getMenuWisePatientNursingDataFlag(consentInboxPatCrNo);
		if(objMenuWiseDataFlag!=null)
		{
			for(var i=0;i<objMenuWiseDataFlag.length;i++)
			{
				var flg = false;
				if(objMenuWiseDataFlag[i].flag=="1")
					flg = true;
				setDeskMenuColor(objMenuWiseDataFlag[i].menuURL, objMenuWiseDataFlag[i].deskMenuId, flg);
			}
		}
	}
}
// Set Desk Menu/Tab Color
function setDeskMenuColor(deskMenuURL,deskMenuId, flag)
{
	getSetDeskMenuInfo(deskMenuURL,deskMenuId);
	if(flag)
		deskMenuElement.style.backgroundColor='#89E894';
	else
		 deskMenuElement.style.backgroundColor=deskMenuBackgroundColor;
		
}	

// Ajax Calling
function getMenuWisePatientNursingDataFlag(_patCrNo)
{
	var flg = false;
	var objMenuWiseDataFlag = null;
	var objXHR = {url: "/HISClinical/inpatient/nursingDeskPatientDataFlag.cnt?patCrNo="+_patCrNo, sync:true, postData: "", handleAs: "json",
		load: function(data) 
		{
			objMenuWiseDataFlag = data;
			flg = true;
		},
		
        error: function(error)
        {
            //if(typeof objMenuWiseDataFlag == 'undefined' || objMenuWiseDataFlag==null || objMenuWiseDataFlag=="" || objMenuWiseDataFlag.length==0)
            	//alert("No Meal Time found for Meal Type");
            //alert(error+"Error while populating Nursing Data Information");
            objMenuWiseDataFlag = null;
            flg = false;
        }};
	var objDojoAjax = dojo.xhrPost(objXHR);
	return objMenuWiseDataFlag;
}

//---- Nursing Desk Data Setting  ----

</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>

<body>
<form id="ipdNursingDeskSubheaderId" >
<his:TitleTag name="IPD NURSING DESK">
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
</body>
</html>