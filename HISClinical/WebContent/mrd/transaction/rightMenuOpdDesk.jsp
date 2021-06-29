
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<%@page import="opd.*"%>

<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/master.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/>
<his:css src="/hisglobal/css/calendar-blue2.css"/>
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<his:javascript src="/registration/js/calendar.js" />
<his:javascript src="/registration/js/popup.js" />
<his:javascript src="/registration/js/registration.js" />
<his:javascript src="/registration/js/validationCalls.js" />
<his:javascript src="/registration/js/validationCommon.js" />
<his:javascript src="/registration/js/commonFunctions.js" />
<his:javascript src="/registration/js/dateFunctions.js" />

<his:javascript src="/opd/opdJs/opd.js" />
<his:javascript src="/opd/opdJs/opdAjax.js"/>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<script>
 var backGroundColor;
var queryString;
var secs
var timerID = null
var timerRunning = false
var delay = 1000
function callThisOnload(){	
	//alert("inside on load right menu");
	var consultaionInbox=document.getElementById("CONSULTATIONINBOX");
	 backGroundColor=consultaionInbox.style.backgroundColor;
	sendData();
	InitializeTimer();
}

function InitializeTimer()
{
	
    // Set the length of the timer, in seconds
    secs = 10
    StopTheClock()
    StartTheTimer()
}

function StopTheClock()
{	
	//alert("in stop clock");
    if(timerRunning)
        clearTimeout(timerID)
    timerRunning = false
}

function StartTheTimer()
{	//// alert("in StartTheTimer");
//alert("secs "+secs);
    if (secs==0)
    {
        StopTheClock()
        // Here's where you put something useful that's
        // supposed to happen after the allotted time.
        // For example, you could display a message:
		InitializeTimer();
		//alert("in before send");
       sendData();
    }
    else
    {
  //  alert("in else of satrt timer")
        self.status = secs
        secs = secs - 1
      	timerRunning = true
        timerID = self.setTimeout("StartTheTimer()", delay)
    }
}

function handleResponse(){
 
 if(request.readyState == 4){
 if(request.status == 200){
 //alert("in handle response");
 //alert("request.responseText "+request.responseText);
 // document.getElementsByName("patAmountCollected")[0].value=request.responseText;
 var consultaionInbox=document.getElementById("CONSULTATIONINBOX");
 var consultaionInboxLable=(consultaionInbox.innerHTML);

 //alert("zxzxzxzx"+consultaionInboxLable)
 var index=consultaionInboxLable.indexOf('(');
 if(index!=-1){
 consultaionInboxLable=consultaionInboxLable.substring(0,consultaionInboxLable.indexOf('('));
// alert("consultaionInboxLable "+consultaionInboxLable)
}
 consultaionInboxLable=consultaionInboxLable+"("+request.responseText+")";
 var count=request.responseText;
// alert("request.responseText "+consultaionInboxLable);
consultaionInbox.innerHTML=consultaionInboxLable;
//alert(count);
if(count==0){
	//ssalert(count);
	consultaionInbox.style.backgroundColor=backGroundColor;
}
else{
	consultaionInbox.style.backgroundColor='#CC0000';
}
 } else {
 alert("A problem occurred with communicating between "+
 "the XMLHttpRequest object and the server program.");
 }
 }//end outer if
}

function sendData(){
//alert("send");
	var url='<%=OpdConfig.OPD_URL_TO_GET_NO_OF_NEW_MAIL%>';
//	alert("url "+url);
//alert(url);
 httpRequest("GET",url,true);
}
</script>


<his:statusInProcess>
	<his:ContentTag> 
		<table width="100%" cellpadding="0" cellspacing="0">
			<logic:iterate id="buttonList"
				name="<%=opd.OpdConfig.OPD_RIGHT_DESK_MENU_DTL%>">
				<tr>
					<his:opdDeskButtonTag
						name="buttonList">
					</his:opdDeskButtonTag>
				</tr>
			</logic:iterate>
		</table>
	</his:ContentTag>
</his:statusInProcess> <his:status />

