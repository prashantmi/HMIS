<!-- Copyright ©.-->
<!-- created by cdac using Pramati Studio 3.0 -->


<% String message = (String)request.getAttribute("message"); 
  if ( message == null ) 
	   message ="";
%>
<%@page import="HisGlobal.Config"%>
<HTML>
<HEAD>
<TITLE>login.jsp</TITLE>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META Http-Equiv="Cache-Control" Content="no-cache" />
<META Http-Equiv="Cache-Control" Content="no-store" />
<META Http-Equiv="Pragma" Content="no-cache" />
<META Http-Equiv="Expires" Content="-1" />

<%
	response.setHeader("Cache-Control", "no-cache");
	response.setHeader("Pragma", "no-cache");
	response.setDateHeader("Expires", -1);
	response.setHeader("Cache-Control", "no-store");
%>

<%String IS_CAPTCHA_REQ =Config.CAPTCHA_IMPLEMENTATION;%>
<script language="JavaScript" src="../usermgmt/js/Validation.js" type="text/JavaScript"></script>
<LINK href="../css/master.css" type=TEXT/CSS rel=STYLESHEET>
<link href="../css/ColorNew.css" rel="stylesheet" type="text/css">
<link href="../css/hisStyle.css" rel="stylesheet" type="text/css">
<script language="JavaScript" src="../usermgmt/js/sha.js"></script>
<script language='javascript'>


function validateAndSubmit(e)
{
	if(e.type=="click" || e.keyCode==13)
	{
		if(document.forms[0].user.value=="")
		{
			alert(" User Id Should Not Be Blank");
			document.forms[0].user.focus();
		}
		else if(document.forms[0].pass.value=="")
		{
			alert(" Password Should Not Be Blank");
			document.forms[0].pass.focus();
		}
		else if(document.forms[0].captchaResponse!=undefined && document.forms[0].captchaResponse.value=="")
		{
			alert("Captcha Should Not Be Blank");
			document.forms[0].pass.focus();
		}
		
		else
		{
			
			var saltedPass =  document.forms[0].pass.value + document.forms[0].user.value;
			var hashObj = new jsSHA(saltedPass, "ASCII");
			try 
			{
				hashedPass = hashObj.getHash("SHA-1", "HEX");	
			}
			catch(e)
			{
				isSuccess = false;	
			}
			//alert(saltedPass);
			//alert(hashedPass);
			
			
			var saltedHashedPass = document.form1.salt.value+hashedPass;
			var salthashObj = new jsSHA(saltedHashedPass, "ASCII");
			try
			{
				SalthashedPass = salthashObj.getHash("SHA-1", "HEX");	
			}
			catch(e)
			{
				isSuccess = false;	
			}
			 document.forms[0].pass.value =SalthashedPass
			formSubmit("LOGIN");
		}
	}
}


/*
function validateAndSubmit(e)
{
	
	if(e.type=="click" || e.keyCode==13)
	{
		if(document.forms[0].user.value=="")
			alert("User Name is Blank");
		else if(document.forms[0].pass.value=="")
			alert("Password is Blank");
		else
			formSubmit("LOGIN");
	}

}*/

function formSubmit(hmode)
{
		document.form1.hmode.value=hmode;
		document.forms[0].submit();

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
    var relWidthUserId=590;
   var absolWidthUserId=oX+relWidthUserId;
   
    var relWidthGo=620;
   var absolWidthGo=oX+relWidthGo;
   
    var relWidthLayer4=780;
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
document.getElementById("blanket").style.height=500;
    
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
function unloadPage()
{
//alert("hmode----"+document.forms[0].hmode.value)
if(document.forms[0].hmode.value!="LOGIN")
	child.close();
}
</script>


</HEAD>
<BODY  bgcolor="#FFFFFF" text="#000000" topmargin="0" onunload="unloadPage()" onclick="setObjCoords()" onresize="setObjCoords()"  leftmargin="0"    onload='setObjCoords(),document.form1.user.focus();'>

<!--<form name='form1' method='post' action='login_cnt.jsp' >-->
<form name='form1' method='post' action='admin' autocomplete="off">
<div id="blanket" style="display: none;"></div>
 <table  border="0" cellspacing="0" align="center" valign="middle" cellpadding="0" >
	<tr>
		<!--<td ><img src="../images/Main.png" id="indexImageId">
		--><td ><img src="../images/login.jpg" id="indexImageId">
		</td>
	</tr>
</table>


<!--<div style="position:absolute;left: 420px;top:412px;width:35%;" id="userLogin"> 
--><div style="position:absolute;left: 0px;top:375px;width:35%;" id="userLogin"> 
<table width="25%">
<tr> 
 <td width="25%">
 <input type="text"  tabindex=1 name="user" id="user" maxlength="20" readvar="" size="17" onkeypress = "return checkInput(this,3,20,event);">
 </td>

 </tr>
 
 <tr>
 <td width="25%">
     <input type="password" tabindex=1 name="pass" id="pass" maxlength="20" readvar="" onkeypress = "checkInput(this,3,15,event), validateAndSubmit(event);"    size="17" readVar>
 </td>
  </tr>
 </table>
 </div>
 <!--<div style="position:absolute;left: 420px;top:470px;width:35%;" id="userGo">
 -->
 
 <%if(IS_CAPTCHA_REQ.equals("ON")) {%>
 <div style="position:absolute;left: 760px;top:430px;width:35%;">
 <table width="100%">
 <tr>
 <td style="width:23%">
  <img id="captchaImg" src="/eAHIMS_user/Captcha"  alt="Captcha Image" height="30">

 </td>
 <td>
 <input type="text" tabindex="1" class="captcha-Text" style="width:35%;height: 23px;"  maxlength="6" name="captchaResponse" id="captchaResponse" placeholder="Enter Captcha" autocomplete="off"/>
 <img src="/HIS/hisglobal/images/buttons/refresh.png" alt="Reload"  onclick="document.forms[0].captchaImg.src='/eAHIMS_user/Captcha'+'?id='+Math.random();" style="cursor:pointer"/>
 
 </td>
 </tr>
 </table>
 </div>
 <%} %>
 <div style="position:absolute;left: 25px;top:460px;width:30%;" id="userGo">
 <table width="25%">
 <!-- <tr>
 <td>
 <input type="text" tabindex="1" class="captcha-Text" style="width:34%"  maxlength="6" name="captchaResponse" id="captchaResponse" placeholder="Enter Captcha" autocomplete="off"/>
 <img id="captchaImg" src="/eAHIMS_user/Captcha" style="width:30%" alt="Captcha Image" height="30">
<img src="/HIS/hisglobal/images/buttons/refresh.png" alt="Reload"  onclick="document.forms[0].captchaImg.src='/eAHIMS_user/Captcha'+'?id='+Math.random();" style="cursor:pointer"/> 
 
 </td>
 </tr> -->
 <tr>
  
 
 <td width="25%" >
<!--<img src="../images/GoFront.png" tabindex="1"  id="goButton"  style="position:absolute;" onKeyPress="validateAndSubmit(event);"
			onClick="validateAndSubmit(event);" > 
 --><a class="button" tabindex="1"  id="goButton"  style="position:absolute;width: 50px " onKeyPress="validateAndSubmit(event);"
			onClick="validateAndSubmit(event);" ><span>Login</span></a>
 </td>
 </tr>
 
  </table>

 
</div>

	<div id="Layer4" style="position:absolute; width:150px; height:22px; z-index:2; left:70px%; top:415px;">
<%
  		String status=(String)request.getAttribute("message");
		if(status!=null && !status.equals(" "))
		{
			out.println("<font color=Red style=verdana size=3><strong>"+status+"</strong</font>");
		}
	//	HttpSession session = null;	
		session = request.getSession(false);
	%>
    
</div>

<input type='hidden' name='hmode' value=''>
<input type='hidden' name='salt' value='<%=(String)session.getAttribute("SALT") %>'></form>

</BODY>
</HTML>
