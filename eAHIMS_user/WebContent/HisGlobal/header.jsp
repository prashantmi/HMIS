<%@page import="java.text.DateFormat"%>
<%@page import="java.util.*" %>
<%@page import="usermgmt.umgmtGlobal"%>
<html>
<head>
<style type="text/css">
	.white
	{ 
		background-Color:#EAEAEA; font:  11px tahoma,verdana,sans-serif; color: #215dc6;
	}
	.blue
	{
		background-Color:#DADADA; font:  12px tahoma,verdana,sans-serif; color: #2E2E2E;
	}
	.grey
	{
		background-Color:#dee1f2; font:  11px tahoma,verdana,sans-serif; color: #215dc6;
	}
	.piyush
	{
		font:  11px tahoma,verdana,sans-serif; color: #215dc6;
	}
</style>

<title>Header Page</title>
<script>


window.history.forward() ;

var st1;
var secs;
var timerID = null;
var timerRunning = false;
var delay = 1000;
var currentDate=null;
var dateAsString=null;
var request;

function setTime()
{
var form=document.forms[0];	
currentDate=convertStrToDate(form.dateAsString2.value,form.dateAsString2.value);
dateAsString=form.dateAsString2.value;
InitializeTimer();

}
<%
	String UserFullName="";
	
try{
		
	umgmtGlobal uGobal=new umgmtGlobal();		
	String hosName="";
	//System.out.println("------Session------"+session+"-----------");
	if(session!=null)	
    	  {	
					
			//System.out.println("------Hos Code------"+session.getAttribute("HOSPITAL_CODE")+"-----------");
			hosName=uGobal.getHospitalName((String)session.getAttribute("HOSPITAL_CODE"));
	  		UserFullName="Welcome, "+session.getAttribute("USER_ID")+" ("+hosName+")";
	
	      }
	else
		UserFullName="";
	  
	}
catch(Exception e)
	{
	UserFullName="";
	e.printStackTrace();
	}
	
	
	%>
function convertStrToDate(_date,_dateAsString)
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
}


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




function checkOut(obj)
{
	
		obj.className='grey'; 
	
}
function checkIn(obj)
{

	obj.className='blue';		
}

function submitPage()
{
	document.logout.hmode.value="LOGOUT";
	document.logout.action = "../startup/admin";
	deleteCookies();
	document.logout.submit();
}

function deleteCookies() {

//alert("Deleting Cookies");
   var allcookies = document.cookie.split(";");

   for (var i = 0; i < allcookies.length; i++) {
        var cookie = allcookies[i];
        var eqPos = cookie.indexOf("=");
        var name = eqPos > -1 ? cookie.substr(0, eqPos) : cookie;
        document.cookie = name + "=;expires=Thu, 01 Jan 1970 00:00:00 GMT";
        //document.cookie = 'UP-759283=; expires=Thu, 01-Jan-70 00:00:01 GMT;'; 
        // document.cookie = name+'="";-1; path=/';
    }
}

function HideFrame() {
	//alert("in menu hide");
			if(parent.document.getElementById("tmp").cols == "220,*")
			{
				parent.document.getElementById("tmp").cols = "0,*";
				document.getElementById("Image").src="../images/arrsingle-right.png"
			}
			else
			{
				parent.document.getElementById("tmp").cols = "220,*";
				document.getElementById("Image").src="../images/arrsingle-left.png"
			}
			  
	}
	
function showTitle(these)
{
these.title="Log Out"
}
function showMenuTitle(these)
{
if(parent.document.getElementById("tmp").cols == "220,*")
	these.title="Hide Menu"
	else
if(parent.document.getElementById("tmp").cols == "0,*")
	these.title="Show Menu"

}
	
</script>

</head>
<!--<body   marginheight="0" bottommargin="0" topmargin="0" background="../images/HeaderMiddle.png">-->
<body   marginheight="0" bottommargin="0" topmargin="0" >
<form name="logout" target="_parent" >
<div > 
  <table background="../images/header_2.png" cellpadding="0" cellspacing="0" >
		<tr width="100%">
		<td valign="bottom" ><img src="../images/banner_logo.png" style="background-attachment: fixed;"> </td>
<!--	 <td align="right" valign="bottom" width="100%"><img src="../images/HeaderMiddle.png" style="background-attachment: fixed;background-repeat: repeat-x;height:50px;" ></td>-->
		<td width="100%"> &nbsp;</td>
</tr>
</table>
<!--<table cellpadding="0" cellspacing="0" >-->
<table background="../images/menu.png" width="100%"  cellpadding="0" cellspacing="0" >
		
<tr>
	<td valign="center">
		<img class="button" id="Image" src="../images/arrsingle-left.png" tabindex="1" style="cursor:pointer;position: absolute;left: 0px;top: 48px;" onmouseover="showMenuTitle(this)" onmousemove="showMenuTitle(this)" 	 tabindex="1"  onclick="HideFrame()" >
		</td>
		<td>
	<font color="#fff" style="position: absolute;left:3%;top:48px;" size="2" face="Verdana, Arial, Helvetica, sans-serif"><b><%=UserFullName%></b></font>
	</td>
	<td>&nbsp;
	</td>
	<!--  Sing-->
	
	
	
		<td  align="right">		
	<div align="right" id="leftButton">
<!--	<img src="../images/Logout.png" onClick="submitPage();" onmouseover="showTitle(this)" style="position:  absolute;right:0%;top:  48px;cursor: pointer;">-->
	<a  onClick="submitPage();" onmouseover="showTitle(this)" style="position:  absolute;right:1%;top:  50px;cursor: pointer;">
	<font color="#fff" size="2" face="Verdana, Arial, Helvetica, sans-serif"><b> Logout</b></font>
	</a>
	
		</td>	
		
		
	
		
</tr>	
	</table>
</div>
			<input type="hidden" name="hmode">
</body>
</html>
