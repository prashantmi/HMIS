<%@taglib uri="/WEB-INF/tree.tld" prefix="treeBuilder"%>
<jsp:useBean id="MenuConfig" class="startup.menu.menuConfigure" scope="request" />
  
<%@page import="java.util.*" session="false"%>
<%
	
	String leafMenuColor = MenuConfig.getLeafMenuColor();
	String highLightColor = MenuConfig.getHighLightColor();
	
%>

<HTML lang='en'>
<HEAD>
<TITLE></TITLE>
<script language="JavaScript" type="text/JavaScript" src="/AHIMS/hisglobal/js/tree.js"></script>
<script language="JavaScript" type="text/JavaScript" src="/AHIMS/hisglobal/js/queue.js"></script>
<script language="JavaScript" type="text/JavaScript" src="/AHIMS/hisglobal/js/stack.js"></script>
<script>

 //////////////////////////////////CUT HERE/////////////////////////////////////////
	/*
	 *<p>Method::focusFrameId_f2 is called each time focus shifts to this frame.
	 *<p>It transfers focus to the main frame[3](id='f3'),so that shortcut keys are
	 *<p>globally detected.
	 *<p>Method::focusFrameId_f2::START
	 *@param event
	 */
	document.onclick=focusFrameId_f2;//Event::When this frame is focused
	
	function focusFrameId_f2(e)
	{
	  var myFrame=parent.window.frames;
	 /* for(i=0;i<myFrame.length;i++)
	  {
	  	alert("myFrame[i].i="+i+myFrame[i].document.getElementsByName('hmode')[0])
	  	alert("myFrame[i].i="+i+myFrame[i].document.getElementsByName('hmode')[0].value)
	  }*/
	  if(!document.all)
	  {
	     if(typeof(myFrame[0].document.getElementsByName("hmode")[0])!="undefined")
		 myFrame[0].document.getElementsByName("hmode")[0].focus();
	  }   
	  else
	  {
	     if(typeof(myFrame[0].document.all.tags("BODY")[0])!="undefined")
		 myFrame[0].document.all.tags("BODY")[0].focus();  
	  } 
	}
	/* <p>Method::focusFrameId_f2::END */
	
    /////////////////////////////////////////////////////////////////////////////////////


var flag="0";
var LMColor = "<%=leafMenuColor%>";
var HLColor = "<%=highLightColor%>";
// called for TreeBuilder.java
function callMe(url,trId,root)   //what is trId
{

//alert("111"+root); 
//alert("url"+url)
//alert("tdid"+trId)
//alert("root"+root)

	var i;
	var field;
	if(flag!="0")
	{
		document.getElementById(flag).color=LMColor;
		flag="0";
	}	
	if(trId != "0")
	{
		document.getElementById(trId).color=HLColor;
		flag=trId;
	}	
	parent.document.getElementById("frmMain").src= url;		//set the required page src.
}
function callMouseOver(trId)
{	
	document.getElementById(trId).color=HLColor;
}
function callMouseOut(trId)
{
	if(trId != flag)
		document.getElementById(trId).color=LMColor;
}
function changeFrameSize(e)
{
	var key = e.keyCode;
	//alert(key);
	if(key==17)
	{
		if(parent.document.getElementById("fs2").cols == "230,*")
		{
			parent.document.getElementById("fs2").cols = "0,*";
		}
		else
		{
			parent.document.getElementById("fs2").cols = "230,*";
		}
	}
}
function changeMode()
{
	document.form1.submit();
}
function toggleNodeOfTree(idToToggle,url)  //what this finction do
{	
    elem=document.getElementById(idToToggle);
    isVisible=(elem.style.display!="none")
	//isVisible=(elem.style.visibility!="hidden")
    node=document.getElementById("x" + idToToggle);
	if (isVisible)
	{
	 elem.style.display="none";
	 //elem.style.visibility="hidden";
	 node.innerHTML="<img src='<%=MenuConfig.getPlusImage()%>'>";
	} 
	else 
    {
	   elem.style.display="block";
	   //elem.style.visibility="visible";
	   node.innerHTML="<img src='<%=MenuConfig.getMinusImage()%>'>";	  
    }
	callMe(url,"0");
}
</script>

<link href="../css/Color.css" rel="stylesheet" type="text/css">
<link href="../css/master.css" rel="stylesheet" type="text/css">
<link href="../css/hisStyle.css" rel="stylesheet" type="text/css">

<style type="text/css">
	td, body {color:#000000; font-family:sans-serif; font-size:10px;}
	a {
	color:#004080;
	text-decoration:none;
	font-weight:bold;
	font-family: Verdana;
	font-size: 10px;
}
	a:hover {color:#000000; text-decoration:none; font-weight:bold;}
	a:active {color:#000000; text-decoration:none; font-weight:bold ;}
</style>
<style>
.greenColor{
	background-Color:#00FF00;
	height:7;
	font-size:12;
	font-family: Verdana;
	color:#000099;
	text-align:left;
	}
.bg 
{
	background-attachment: fixed;
	background-color: #e0ebeb;
	background-repeat: repeat-y;
	background-position: left top;
}
	
td.menufont
{
	font-size: 10px;
	font-style: normal;
	line-height: normal;
	font-weight: lighter;
	font-variant: normal;
	text-transform: capitalize;
	color: #FF9933;
}
</style>

<link href="../hisglobal/css/menucolor.css" rel="stylesheet" type="text/css">

</HEAD>
<body onKeyUp="changeFrameSize(event);" class="bg"> 
<form name="form1" action="result.jsp">

<%	try
	{	
%>
		<treeBuilder:tree userId='<%=(String)request.getParameter("userId") %>' seatId='<%=(String)request.getParameter("seatId")%>' hospitalCode='<%=(String)request.getParameter("hospitalCode") %>' />
<%
	}
	catch(Exception e)
	{
		//System.out.println("exception in test.jsp: "+e);
	}
%>
</form>
</body>
</HTML>
