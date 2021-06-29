<%@taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<%@ page import="java.util.*"  %>

<html>
<his:css src="/hisglobal/css/hisStyleExt.css" />
<head>
<LINK REL="SHORTCUT ICON" HREF="../hisglobal/images/hisico.ico">
<title>Login</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<his:javascript src="/registration/js/popup.js" />
<script language="JavaScript" type="text/JavaScript">



history.forward();

 var child ;
 var readVar;
 
function formSubmit(e)
{

	if(e.type=="click"||e.keyCode==13)
	{
		document.forms[0].hmode.value="LOGIN";
		document.forms[0].submit();
	}
}

function checkCapsLock(these,e ) {
	var myKeyCode=0;
	var myShiftKey=false;
	var myMsg='Caps Lock is On.\n\nTo prevent entering your password incorrectly,\nyou should press Caps Lock to turn it off.';




//alert(these.title);
	// Internet Explorer 4+
	if ( document.all ) {
		myKeyCode=e.keyCode;
		myShiftKey=e.shiftKey;

	// Netscape 4
	} else if ( document.layers ) {
		myKeyCode=e.which;
		myShiftKey=( myKeyCode == 16 ) ? true : false;

	// Netscape 6
	} else if ( document.getElementById ) {
		myKeyCode=e.which;
		myShiftKey=( myKeyCode == 16 ) ? true : false;
	}
//alert(myKeyCode)
//alert(myShiftKey)

var elem=document.getElementsByName(these.name)[0];

var div=document.getElementById('capsLockId');
			

if(myKeyCode>=65 && myKeyCode<=90 )
   {
  
   // alert(myMsg);
    
    if(div.style.display=="none")
			{
			    var o=elem;
				var l=0,t=0;
				while(o.offsetParent)
				{
			//	alert(o.offsetLeft)
			//	alert(o.offsetTop)
			//	alert(o.offsetParent)
				
					l+=o.offsetLeft;
					t+=o.offsetTop;
					o=o.offsetParent;
				}
			//	alert(l)
			//	alert(t)
			//	alert(o)
			var LE=l+(l/10)
		
				div.style.left=l;
				div.style.top=t+elem.offsetHeight;
			//	div.style.pixelWidth=elem.style.pixelWidth;
				div.style.display="block";
			}	
				
   }
   else
  {
 // alert("bye");
  
   		if(div.style.display=="block")
			{
				div.style.display="none";
			}	
				
				
   }

	

}
function capsLockCheck(these,e)
{
var myKeyCode=e.which;

var elem=document.getElementsByName(these.name)[0];

var div=document.getElementById('capsLockId');
			
alert(myKeyCode)
if(myKeyCode>=65 && myKeyCode<=90 )
   {
  
    alert("hi");
    
    if(div.style.display=="none")
			{
			    var o=elem;
				var l=0,t=0;
				while(o.offsetParent)
				{
					l+=o.offsetLeft;
					t+=o.offsetTop;
					o=o.offsetParent;
				}
				div.style.left=l;
				div.style.top=t+elem.offsetHeight;
				div.style.pixelWidth=elem.style.pixelWidth;
				div.style.display="block";
			}	
				
   }
   else
  {
  alert("bye");
  
   		if(div.style.display=="block")
			{
				var o=elem;
				var l=0,t=0;
				while(o.offsetParent)
				{
					l+=o.offsetLeft;
					t+=o.offsetTop;
					o=o.offsetParent;
				}
				div.style.left=l;
				div.style.top=t+elem.offsetHeight;
				div.style.pixelWidth=elem.style.pixelWidth;
				div.style.display="none";
			}	
				
				
   }
	   

}

	
function checkCapsLockNew(these,e ) {
	var myKeyCode=0;
	var myShiftKey=false;
	var myMsg='Caps Lock is On.\n\nTo prevent entering your password incorrectly,\nyou should press Caps Lock to turn it off.';




//alert(these.title);
	// Internet Explorer 4+
	if ( document.all ) {
		myKeyCode=e.keyCode;
		myShiftKey=e.shiftKey;

	// Netscape 4
	} else if ( document.layers ) {
		myKeyCode=e.which;
		myShiftKey=( myKeyCode == 16 ) ? true : false;

	// Netscape 6
	} else if ( document.getElementById ) {
		myKeyCode=e.which;
		myShiftKey=( myKeyCode == 16 ) ? true : false;
	}
//alert(myKeyCode)
//alert(myShiftKey)

var elem=document.getElementsByName(these.name)[0];

var div=document.getElementById('capsLockIdNew');
			

if(myKeyCode>=65 && myKeyCode<=90 )
   {
  
   // alert(myMsg);
    
    if(div.style.display=="none")
			{
			    var o=elem;
				var l=0,t=0;
				while(o.offsetParent)
				{
			//	alert(o.offsetLeft)
			//	alert(o.offsetTop)
			//	alert(o.offsetParent)
				
					l+=o.offsetLeft;
					t+=o.offsetTop;
					o=o.offsetParent;
				}
			//	alert(l)
			//	alert(t)
			//	alert(o)
			var LE=l+(l/10)
		
				div.style.left=LE;
				div.style.top=t+elem.offsetHeight;
			//	div.style.pixelWidth=elem.style.pixelWidth;
				div.style.display="block";
			}	
				
   }
   else
  {
 // alert("bye");
  
   		if(div.style.display=="block")
			{
				div.style.display="none";
			}	
				
				
   }



}

function capLock(e)

        { 
        
        var elem=document.getElementsByName("pwd")[0];

		var div=document.getElementById('capsLockIdNew');
        

               kc = e.keyCode?e.keyCode:e.which;

               sk = e.shiftKey?e.shiftKey:((kc == 16)?true:false); 
               
              
          
              if(((kc >= 65 && kc <= 90) && !sk)||((kc >= 97 && kc <= 122) && sk))
              		{

			    var o=elem;
				var l=0,t=0;
				while(o.offsetParent)
				{
			//	alert(o.offsetLeft)
			
			//	alert(o.offsetTop)
			//	alert(o.offsetParent)
				
					l+=o.offsetLeft;
					t+=o.offsetTop;
					o=o.offsetParent;
				}
			//	alert(l)
			//	alert(t)
			//	alert(o)
			var LE=l-(l/2)-(l/25);
		//	alert("LE---"+LE)
			var TP=t-(t/2)-(t/3)-(t/5);
		
				//div.style.right=LE
				div.style.left=LE;
				div.style.top=TP
			//	div.style.pixelWidth=elem.style.pixelWidth;
			//	div.style.display="block";
			
			    document.getElementById('capsLockIdNew').style.display = 'block'; 
					
					
					}
               else  

                       document.getElementById('capsLockIdNew').style.display = 'none';

        }
function showTitle(these)
{
these.title="Forgot Password";
}

function setObjCoords() {
//alert("fn called");
var o=document.getElementById("indexImageId");
    var oX = 0;
    var oY = 0;
    if (o.offsetParent) {
        while (1) {
            oX+=o.offsetLeft;
            oY+=o.offsetTop;
                if (!o.offsetParent) {
                    break;
                }
            o=o.offsetParent;
        }
    } else if (o.x) {
        oX+=o.x;
        oY+=o.y;
    }
   // alert(oX + ":" + oY);
    //return new Point(oX, oY);
    var relWidthUserId=400;
   var absolWidthUserId=oX+relWidthUserId;
   
    var relWidthGo=295;
   var absolWidthGo=oX+relWidthGo;
   
    var relWidthLayer4=600;
   var absolWidthLayer4=oX+relWidthLayer4;
   
        	
   		
   var relWidthCaps=110;
   var absolWidthCaps=oX+relWidthCaps;
   
   
  // alert(absolWidth)
    
    document.getElementById("userLogin").style.left=absolWidthUserId
    document.getElementById("userGo").style.left=absolWidthGo
    document.getElementById("Layer4").style.left=absolWidthLayer4
     
   // document.getElementById("capsLockIdNew").style.left=absolWidthCaps 
    
    
 var myWidth = getInitialViewerWidth();
var myHeight = getInitialViewerHeight();


//alert(myWidth+"-----------"+myHeight)

//document.getElementById("blanket").style.left=oX;
document.getElementById("blanket").style.width=1024;
document.getElementById("blanket").style.height=580;
    
}

function openDependentPopupNew(url,eventObj, height, width)
{
if(eventObj.type=="click" || eventObj.keyCode==13)
 {
   	child = window.open(url,'popupWindow','status=yes,scrollbars=yes,height='+height+',width='+width+',left=10,top=10,dependent=yes,resizable=no');  
  	child.moveTo(250,250);
 	child.focus(); 
 	




//document.getElementById("blanket").style.display="block"


document.getElementById("uid").disabled=true;
document.getElementById("pwd").disabled=true;
document.getElementById("goButton").style.display="none";

//document.body.disabled=true;


if(!child.opener)
   child.opener = self;
 }
 return child
}

function unloadPage()
{
//alert("hmode----"+document.forms[0].hmode.value)
if(document.forms[0].hmode.value!="LOGIN")
	child.close();
}
function getInitialViewerHeight() {
	var height = 0;

	if (typeof(window.innerWidth) == 'number') {// non-IE browsers
		height = window.innerHeight;
	} else if (document.documentElement
			&& document.documentElement.clientHeight) { // IE 6+ in 'standards
		// compliant mode'
		height = document.documentElement.clientHeight;
	} else if (document.body && document.body.clientHeight) { // IE 4
		// compatible
		height = document.body.clientHeight;
	}
	return height;
}

function getInitialViewerWidth() {
	var width = 0;

	if (typeof(window.innerWidth) == 'number') {// non-IE browsers
		width = window.innerWidth;
	} else if (document.documentElement && document.documentElement.clientWidth) {// IE
		// 6+
		// in
		// 'standards
		// compliant
		// mode'
		width = document.documentElement.clientWidth;
	} else if (document.body && document.body.clientWidth) {// IE 4 compatible
		width = document.body.clientWidth;
	}
	return width;
}



</script>
</head>
<%

String msg="Caps Lock is On.\n\nTo prevent entering your password incorrectly,\nyou should press Caps Lock to turn it off.";

%>
<body bgcolor="#FFFFFF" text="#000000" topmargin="0" onunload="unloadPage()" onclick="setObjCoords()" onresize="setObjCoords()"  onLoad="setObjCoords(),document.forms[0].uid.focus();" leftmargin="0"  >

 <form name="form1" method="post" action="loginAction" >

<div id="blanket" style="display: none;"></div>

  <table  border="0" cellspacing="0" align="center" valign="middle" cellpadding="0" >
    <tr>
      <td ><img src="../hisglobal/images/AyushLoginImage.jpg" id="indexImageId">
      </td>
    </tr>
  </table>

<div style="position:absolute;left:500px;top:355px;width:35%;" id="userLogin">  
<table width="45%">
 <tr> 
 <td width="75%"></td>
 <td width="25%">
 <input type="text" tabindex=1 name="uid" id="uid" maxlength="20" readVar size="15" align="right">
 </td>

 </tr>
 
 <tr>
  <td width="75%"></td>
 <td width="25%">
     <input type="password" tabindex=1 name="pwd" id="pwd" maxlength="20" onkeypress="capLock(event),formSubmit(event,this);"  size="15" readVar>
 </td>
  </tr>
 </table>
 </div>
 
 <div style="position:absolute;left:500px;top:405px;width:35%;" id="userGo">
 <br>
 <table width="100%">
 <tr>
  <td width="60%" align="right">
 <a id="forgotPasw" style="cursor: pointer;" onclick="openDependentPopupNew('/AHIMS/startup/forgotPassword.jsp',event,250,500);" onmouseover="showTitle(this)" onmousemove="showTitle(this)"><font color="#663399"><b><u>Forgot Password</u>&nbsp;?</b></font></a>
 </td>
 
 <td width="35%" align="LEFT">
<img src="../hisglobal/images/GoFront.png" tabindex="1"  id="goButton" onKeyPress="formSubmit(event);" onClick="formSubmit(event);"> 
 </td>
<td width="5%">
<img src="../hisglobal/images/CapsOn22.png"  id="capsLockIdNew" style="position:absolute;display:none;">
 </td>
 </tr>
 
  </table>

 
</div>

	<div id="Layer4" style="position:absolute; width:150px; height:22px; z-index:2; left:70px%; top:380px;">
<%
  		String status=(String)request.getAttribute("message");
		if(status!=null && !status.equals(" "))
		{
			out.println("<font color=Red style=verdana size=3><strong>"+status+"</strong</font>");
		}
	%>
    
</div>
<input type="hidden" name="hmode" value="">
</form>
</body>
</html>
