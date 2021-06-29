<%@taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<%@ page import="java.util.*"  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<his:css src="/hisglobal/css/hisStyleExt.css" />
<his:css src="/hisglobal/css/banner.css" />
<his:css src="/hisglobal/css/loginLayout.css" />
<head>
<LINK REL="SHORTCUT ICON" HREF="../hisglobal/images/hisico.ico">
<title>Login</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<his:javascript src="/registration/js/popup.js" />
<his:javascript src="/hisglobal/js/jquery.js" />
<his:javascript src="/hisglobal/js/banner.js" />

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
document.getElementById("goButton").style.visibility="hidden";

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

 $(function() {
		  $(document).ready(function() { 
			  var ht = Math.round($(window).height() - $(".header").outerHeight()-$(".footer").outerHeight()) + "px";
			 var wt = Math.round($(window).width()) + "px";
			 $('.body').height(ht); 
			
			 var margin=Math.round(($(window).height() - $(".header").outerHeight()-$(".footer").outerHeight()-230-$(".HISBlocksImages table").height())/2);
			//$('.login').css("margin-top",margin + "px"); 
			//$('.HISBlocksImages').css("margin-top",margin + "px"); 
			//$('.capsLock').css("margin-top",margin +100+ "px");
		  });
		});

</script>
</head>
<%

String msg="Caps Lock is On.\n\nTo prevent entering your password incorrectly,\nyou should press Caps Lock to turn it off.";

%>
<body class="mainLoginBg" onunload="unloadPage()"  onLoad="document.forms[0].uid.focus();">
 <form name="form1" method="post" action="loginAction" >
<div id="blanket" style="display: none;"></div>

	<div class="header">
		<img src="../hisglobal/images/headerImage.png">
		<img src="../hisglobal/images/phdm.png" align="right">
		<!-- <span class="titleTag"> e-Sushrut Advanced Hospital Information System </span> -->
	</div>
	<div  class="body">
		<div class="centerLayout">
		
		 <div id="banner" style="width: 994px; margin:0px auto; ">
		        
		        <div class="slideshow">
		                    <ul class="buttons">
		                        <li class="" id="button1"><a href="#" title="Reduced costs…Higher Revenue">1</a></li>
		                        <li class="active" id="button2"><a href="#" title="Greater Control">2</a></li>
		                        <li class="" id="button3"><a href="#" title="Uninterrupted Flow of Information">3</a></li>
		                        <li class="" id="button4"><a href="#" title="Fully integrated">4</a></li>
		                    </ul>
		                    <ul class="slides">
		                        <li style="visibility: hidden; opacity: 1;" class="slide" id="image1"><img alt="Hospital management system (HMS)" src="../hisglobal/images/slide1.png"></li>
		                        <li style="visibility: visible; opacity: 1;" class="slide" id="image2"><img alt="Greater Control" src="../hisglobal/images/slide2.png"></li>
		                        <li style="visibility: hidden; opacity: 1;" class="slide" id="image3"><img alt="Uninterrupted Flow of Information" src="../hisglobal/images/slide3.png"></li>
		                        <li style="visibility: hidden; opacity: 1;" class="slide" id="image4"><img alt="Fully integrated" src="../hisglobal/images/slide4.png"></li>
		                    </ul>
		                </div>
		        
		      </div><!--
		

		
		
		<div class="HISBlocksImages" style="float: left; width: 35%; margin-left: 50px; margin-top: 15px;" >
		<table width="100%"> <tr> <td align="center"> <img src="../hisglobal/images/1.png"></td> </tr> </table>
		
		</div>
		<div class="login"  style="float: left; width: 35%; margin-left: 50px; margin-top: 15px;">
		<h3> Login to AHMIS</h3>
		
		    <input tabindex="1" class="loginTextBox" placeholder="Enter User Name" name="uid" id="uid" maxlength="20" type="text"><br>
			
			<input tabindex="1" class="loginTextBox" placeholder="Enter Password" value="" name="pwd" id="pwd" maxlength="20" type="password" onkeypress="capLock(event),formSubmit(event,this);" ><br>
			 <a id="forgotPasw" style="cursor: pointer;" onclick="openDependentPopupNew('/AHIMS/startup/forgotPassword.jsp',event,250,500);" onmouseover="showTitle(this)" onmousemove="showTitle(this)"><b><u>Forgot Password</u>&nbsp;?</b></a>
			 
			<input tabindex="1" value="Login" class="login-button" id="goButton" onKeyPress="formSubmit(event);" onClick="formSubmit(event);" type="button"> <br/>
		    
		   
			            		
		</div>
		<div class="capsLock" style="float: left; width: 15%; margin-left: -100px; margin-top: 15px;">
			<img src="../hisglobal/images/CapsOn22.png"  id="capsLockIdNew" style="position:relative;display:none;">
			</div>
	   </div>
	
-->

<div class="noticeBoard "> 

 						<h3>Notice Board</h3>
									<ul class="list-1">
											<li> Implements EHR semantics over store</li>
											<li>Harnessing technology to provide the healing touch.</li> 
											<li>Connects people, process and data in real time across all the hospitals on a single platform.</li>
											
											

									 </ul>
                                  

</div>
		<div class="loginParams"> 
	<h3> Login to AHMIS</h3>
		
		    <input tabindex="1" class="loginTextBox" placeholder="Enter User Name" name="uid" id="uid" maxlength="20" type="text"><br>
			
			<input tabindex="1" class="loginTextBox" placeholder="Enter Password" value="" name="pwd" id="pwd" maxlength="20" type="password" onkeypress="capLock(event),formSubmit(event,this);" ><br>
			<input tabindex="1" value="Login" class="login-button" id="goButton" onKeyPress="formSubmit(event);" onClick="formSubmit(event);" type="button"><br />
			 <a id="forgotPasw" style="cursor: pointer;" onclick="openDependentPopupNew('/AHIMS/startup/forgotPassword.jsp',event,250,500);" onmouseover="showTitle(this)" onmousemove="showTitle(this)"><b><u>Forgot Password</u>&nbsp;?</b></a>
			 <div class="alertMessage"  id="capsLockIdNew" style="position:relative;display:none;">
			<span>Caps Lock is ON!</span>
			</div>
			<div class="alertMessage">
			<%
  		String status=(String)request.getAttribute("message");
		if(status!=null && !status.equals(" "))
		{
			out.println(status);
		}
	%>
		    </div>
		   

		</div>	


</div>
</div>
<div class="footer">
<img src="../hisglobal/images/footerImage.png">
	</div>
<input type="hidden" name="hmode" value="">
</form>
</body>
</html>
