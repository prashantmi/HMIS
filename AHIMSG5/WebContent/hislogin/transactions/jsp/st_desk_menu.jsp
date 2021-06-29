<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%-- <%@taglib uri="/WEB-INF/tree.tld" prefix="treeBuilder"%>
<jsp:useBean id="MenuConfig" class="startup.menu.menuConfigure" scope="request" />
  
<%@page import="java.util.*" session="false"%>
<%
	
	String leafMenuColor = MenuConfig.getLeafMenuColor();
	String highLightColor = MenuConfig.getHighLightColor();
	
%>

<HTML lang='en'>
<HEAD>
<TITLE></TITLE>
<script language="JavaScript" type="text/JavaScript" src="tree.js"></script>
<script language="JavaScript" type="text/JavaScript" src="queue.js"></script>
<script language="JavaScript" type="text/JavaScript" src="stack.js"></script>

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
	background-color: #EDE6D9;
	background-repeat: repeat-y;
	background-position: left top;
	background-image: url(../hisglobal/images/MenuImage.png);
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

<link href="../css/menucolor.css" rel="stylesheet" type="text/css">

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
 --%>
 <%@page import="hislogin.config.HISLoginConfig"%>
<%@page import="hissso.config.HISSSOConfig"%>
<%@page import="hissso.validation.credentails.authorization.AuthorizationCredentials"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="hisglobal.utility.Entry"%>
<%
	AuthorizationCredentials objAuthorize = (AuthorizationCredentials) session.getAttribute(HISSSOConfig.LOGGEDIN_USER_AUTHORIZATION_OBJECT);
	if(objAuthorize!=null && objAuthorize.getMenusHirarchyMap()!=null){
	 Map zeroLevelModules = objAuthorize.getMenusHirarchyMap();
	 Set<Map.Entry> moduleSet = zeroLevelModules.entrySet();
	 
%>
<%! StringBuffer menuNames= new StringBuffer(); 
	StringBuffer menuURLs= new StringBuffer();
	StringBuffer menuJSON= new StringBuffer();
	%>
<%! public String traverseMap(Map map,String list){
						int menuLimitInOneCol=11;
						int curLevelMenuCount=0;
						boolean nestingTagOpened=false;
						Set<Map.Entry> columnMapSet=map.entrySet();
						for (Map.Entry column : columnMapSet) {
							if(++curLevelMenuCount % menuLimitInOneCol == 0 ){
								if(nestingTagOpened==false){
									/* list+="<li><a  href='#'> .... </a> ";
									list+="<ul style='display:none;'>";
									nestingTagOpened=true; */
								}else{
									/* list+="</ul></li>";
									list+="<li><a  href='#'> .... </a> ";
									list+="<ul style='display:none;'>"; */
									nestingTagOpened=true;
								}
								
							}
							//System.out.println("key: "+ column.getKey().toString());
							if (column.getValue() instanceof String) { // only one level in column
								String value= column.getValue().toString();
								/* String link="<li ><a class='leafNode' onclick=callMenu('"+value+"') href='"+ value+"'>"+column.getKey().toString() +"</a> </li>"; */
								/* String link="<li ><a  onclick=\"callMenu('"+value +"','"+column.getKey().toString()+"')\">"+column.getKey().toString() +"</a> </li>";
								list+=link; */
								menuNames.append("\""+column.getKey().toString().trim()+"\" ,");
								menuURLs.append("\""+ value.trim()+"\" ,");
							}
							else if (column.getValue() instanceof Map) {
								/* list+="<li><a  href='#'>"+column.getKey().toString() +"</a> ";
								list+="<ul style='display:none;'>";
								list+=traverseMap((Map)column.getValue(),"");
								list+="</ul></li>"; */
								
							}
						}
						if(nestingTagOpened==true){
							list+="</ul></li>";
							nestingTagOpened=false;
						}
						return list;
					}
					
					%>	
					<% menuNames=menuNames.delete(0, menuNames.length());
					 menuURLs=menuURLs.delete(0, menuURLs.length());
					 menuJSON=menuJSON.delete(0, menuJSON.length());
					%>
 <div id="menuContainer">
			<!-- <div id="arrow_left">
				<img src="/HIS/hisglobal/images/scroll_left.png" alt="scroll Menu left"
					title="Scroll Left" width="100%" height="100%">
			</div>
			<div id="arrow_right">
				<img src="/HIS/hisglobal/images/scroll_right.png" alt="scroll Menu right"
					title="scroll Right" width="100%" height="100%">
			</div> -->
			<div id="menuStrip">
			<div id="smoothmenu">
				<ul id="menuList">
				<!-- Code to Generate Menu Dynamically -->
					<%
						for (Map.Entry set : moduleSet) {
							String key = set.getKey().toString();
							//System.out.println("key: " + key);
							if (set.getValue() instanceof String) { // no dropdown menu is there
								%><li><a href="<%=set.getValue().toString() %>"> <%=set.getKey().toString() %></a> </li>
								<%
							}else if (set.getValue() instanceof HashMap) {
								%>
								<li id="<%=set.getKey().toString().trim().replaceAll("\\s+","_")%>"><a href="#" onclick="menuSelected('<%=set.getKey().toString() %>',true)"> <%=set.getKey().toString() %></a> 
								<ul> <!-- list to display column of 1st level -->
								<% HashMap columnHeadingMap=(HashMap)set.getValue();
								
								Set<Map.Entry> columnHeadingSet = columnHeadingMap.entrySet();
								int columnCount=0;
								columnCount=columnHeadingSet.size();
								String columnCountClass=""; // to refer to 1, 2 or 3 columns
								/* if(columnCount!=0){
									if(columnCount==1)
										columnCountClass="dropdown_1column";
									else if(columnCount==2)
										columnCountClass="dropdown_2columns";
									else if(columnCount==3)
										columnCountClass="dropdown_3columns";
								}  */
								%>
								<div class="<%= columnCountClass %>">
								<%
								for (Map.Entry columnHeadinigMapSet : columnHeadingSet) {
									 %>
									<%-- <li><a href="#"> <%=columnHeadinigMapSet.getKey().toString() %></a>
									<ul> --%>  
									<%
										HashMap columnMapFirstLevel=(HashMap) columnHeadinigMapSet.getValue();
										String list= traverseMap(columnMapFirstLevel,"");
										%>
										<%= list %>
									
									<!-- </ul>
									</li> -->
									
									<%
									}
								%>
								</ul>
								</li>
								<%
							} 
						}
					%>

					<!-- Code to Generate Menu Dynamically  Ends -->
					<div id="slideEnd"></div>
					<%
					if(menuURLs.toString().length()==0)
					menuJSON.append("{ \"menuNames\": ["+menuNames.toString() +"], \"menuURLs\" :["+menuURLs.toString()+"]}");
					else
					menuJSON.append("{ \"menuNames\": ["+menuNames.toString().substring(0, menuNames.toString().length()-1) +"], \"menuURLs\" :["+menuURLs.toString().substring(0, menuURLs.toString().length()-1)+"]}");
					%>
				</ul>
				</div>

			</div>

		</div>
		<input type="hidden" name="menuJSON" id="menuJSON" value='<%= menuJSON%>'>
		
		
		<%} %>
		