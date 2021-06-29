<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="hislogin.transactions.utl.UserDeskUTL"%>
<%@page import="java.awt.Toolkit"%>
<%@page import="java.awt.Dimension"%>
<%@page import="hissso.config.HISSSOServerConfig"%>
<%@page import="hissso.validation.credentails.authentication.AuthenticationCredentials"%>
<%@page import="vo.usermgmt.MenuMasterVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@page import="hislogin.config.HISLoginConfig"%>
<%@page import="hissso.config.HISSSOConfig"%>
<%@page import="hissso.validation.credentails.authorization.AuthorizationCredentials"%>
<%@page import="java.util.*"%>
<%@page import="hisglobal.utility.Entry"%>
<%@ taglib uri="/struts-tags" prefix="s" %>

<html>
<head>
<s:head/>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<META Http-Equiv="Cache-Control" Content="no-cache" />
<META Http-Equiv="Cache-Control" Content="no-store" />
<META Http-Equiv="Pragma" Content="no-cache" />
<META Http-Equiv="Expires" Content="0" />

<meta name="viewport" content="width=device-width, initial-scale=1">
<%
	response.setHeader("Cache-Control", "no-cache");
	response.setHeader("Pragma", "no-cache");
	response.setDateHeader("Expires", 0);
	response.setHeader("Cache-Control", "no-store");
%>
<title>HIS Login Desk</title>
<link rel="shortcut icon" href="/HIS/hisglobal/images/hisico.ico">

<link type="text/css" rel="stylesheet" href="/HIS/hisglobal/css/ddsmoothmenu.css" />
<link type="text/css" rel="stylesheet" href="/HIS/hisglobal/css/loginLayout.css" />
<link type="text/css" rel="stylesheet" href="/HIS/hisglobal/css/layout.css" />
<link type="text/css" rel="stylesheet" href="/HIS/hisglobal/css/menu.css"/>
<link type="text/css" rel="stylesheet" href="/HIS/hisglobal/css/image-slideshow.css" />
<link type="text/css" rel="stylesheet" href="/HIS/hisglobal/css/basic.css" media="screen" />
<link type="text/css" rel="stylesheet" href="/HIS/hisglobal/css/jquery-ui.css" />
<link href='/HIS/hisglobal/css/jquery.qtip.min.css' rel='stylesheet' />
<link rel="stylesheet" type="text/css" href="/HIS/hisglobal/css/easyui.css"> 
<link rel="stylesheet" type="text/css" href="/HIS/hisglobal/css/tabmenu/login_desk.css"> 

<style>
.modal-dialog{
    overflow-y: initial !important
}
.modal-body{
    /*  height: 350px; */
     max-height: 90vh;
    overflow-y: auto;
}
#modelalertLayer{
	position:fixed;
	z-index:9999;
	top:0;
	left:0;
	width:100%;
	height:100%;
	display:none; 
	background-color:rgba(30, 30, 30, 0.84);
}
#modelalert{
	margin-top: 50vh;
	margin-left: 50vw;
	background-color: white;
	border-radius: 10px;
	transform: translateY(-50%) translateX(-50%);
}
#modelalert .container-fluid{
	width:97%;
	padding:10px 15px; 
	padding: 5px;
}
#modelalert .container-fluid table{ 
	padding:5px; 
}
#modelalert .container-fluid table tr{ 
	padding:5px 5px; 
}
#modelalert .container-fluid table thead tr th {
    border-bottom: 1px solid #d5d2d2;
}
#modelalert .container-fluid table tr td,#modelalert .container-fluid table tr th{ 
	margin:5px 8px;
	padding:5px 10px; 
}
.text-danger{
	background-color: #ffb3b3;
}
#modelalert .container-fluid table tr td font.text-danger { 
    padding: 10px;
    border-radius: 5px;
}
#modelalert .container-fluid table tr td{
border-bottom: 2px solid #ccc;
color: red;

}
.buttonAck
{
/* background-color: #129898; */
background-color: #086ea5;
    color: white;
    border-radius: 10px;
    font-size: 14px;
    padding: 5px;
    cursor: pointer;
    float: right;
	display: none; 
}
.buttonAckAll{
/* background-color: #086ea5; */
    background-color: #086ea5;
    color: white;
    border-radius: 10px;
    font-size: 14px;
    padding: 5px;
    cursor: pointer;
  
    width: 110px;
	height: 30px;
	

}
.closeButton{
float: right;
background-color: rgba(204, 55, 55, 0.8) !important;
color: white;
border: 0;
padding: 2px 8px;
border-radius: 6px;
margin-right: 0px;
margin-top: 0px;
cursor: pointer;
width: 28px !important;
height: 24px !important;
}
  
  
 .closeButton hover{
 background-color: grey;
 }
 .modal-body{
/*  padding: 1% 0 2% 0;
	margin: 2% 0;
	transition:0.3s; */
 }
/*  .polaroid {
  width: 250px;
  box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);
  text-align: center;
} */
 
 .modal-lg{
/*  box-shadow: 0 1px 6px rgba(0, 0, 0, 0.12), 0 1px 4px rgba(0, 0, 0, 0.24);
 */  
  box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);
 }
 .modal-lg :hover{
 
 /*  box-shadow: 0 0.5px 20px 2px #c8c8c8;  */
   /*  -webkit-transform: translateY(-5px); */
   /*  transform: translateY(-1px); */
     -webkit-box-shadow: 0 15px 20px rgba(255, 255, 255, 0.1);
  -moz-box-shadow: 0 15px 20px rgba(255, 255, 255, 0.1);
  box-shadow: 0 5px 10px rgba(255, 255, 255, 0.1);     
    /* #modelalert{
    transform: translateY(-60);
    } */
 }
.setheight{
 height: 90vh;
}

</style>

<script type="text/javascript" src="/HIS/hisglobal/js/jquery.js"></script>
<script type="text/javascript" src="/HIS/hisglobal/js/jquery-ui.js"></script>
<script type="text/javascript" src="/HIS/hisglobal/js/image-slideshow.js"></script>
<script type="text/javascript" src="/HIS/hisglobal/js/ddsmoothmenu.js"></script>
<script type="text/javascript" src="/HIS/hisglobal/js/jquery.simplemodal.js"></script>
<script type="text/javascript" src="/HIS/hisglobal/js/commonFunctions.js"></script>
<script type="text/javascript" src="/HIS/hisglobal/js/utilityFunctions.js"></script>
<script type="text/javascript" src="/AHIMSG5/startup/transactions/js/login.js"></script>
<script type="text/javascript" src="/HIS/hisglobal/js/jquery/jqueryUtilityFunctions.js"></script>
<script type="text/javascript" src="/HIS/hisglobal/js/popup.js"></script>

<script type="text/javascript" src="/HIS/hisglobal/utility/dynamicdesk/js/jquery.min.js"></script>
<script type="text/javascript" src="/HIS/hisglobal/js/tabmenu/jquery/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/HIS/hisglobal/js/tabmenu/tabmenu.js"></script>

<!-- <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">  -->
<%-- <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script> --%>


<script src='/HIS/hisglobal/js/jquery/jquery.qtip.min.js'></script>

<script> 
	 
</script>
<script>

document.addEventListener('DOMContentLoaded', function(){
	var myCount=0;
	// e.preventDefault();
	 //e.stopPropagation();
	
	  $.ajax({  
		   type: "GET",  
		   async:true,
		   url: "/AHIMSG5/hislogin/getHighPriorityAlertUserDesk",
		   success: function(response){  
		  var finalArr = response.filter(function (el) {
			   return el.priority == 2;
			 });
		
		function sortByKey(array, key) {
		    return array.sort(function(a, b) {
		        var x = a[key]; var y = b[key];
		        return ((x < y) ? -1 : ((x > y) ? 1 : 0));
		    });
		}

		data = sortByKey(finalArr, 'alertTime');
		
		   console.log('finalArr ');
		   console.log(finalArr);
		   if(finalArr.length!=0)
		   var ackStr = [];
		   if(localStorage.getItem("alertId"))
			ackStr = JSON.parse(localStorage.getItem("alertId"));
		   //ackStr = localStorage.getItem("alertId");
		   console.log(ackStr);
		   for(var i=0;i<finalArr.length;i++)
		   {
			   //if(jQuery.inArray("test", myarray) !== -1)
			   if(finalArr[i].alertAction==1 && indexOfMyArr(ackStr,finalArr[i].alertID)==0) 
         		{
 			 $('#modelalert .highPriorityAlertLst tbody').append('<tr><td>'+finalArr[i].alertTime+'</td><td>'+finalArr[i].alertMsg+'</td><td><button type="button" class="buttonAck" onclick="ackAlertCurrentRow(this,\''+finalArr[i].alertID+'\')">Acknowledge</button></td></tr>');
 			//$('#modelalert .highPriorityAlertLst tbody').append('<hr>')  
 			 myCount++;
			    //console.log(ackStr.includes(finalArr[i].alertID));
			   //console.log($.inArray(ackStr,finalArr[i].alertID));
			  // console.log(ackStr.indexOf(finalArr[i].alertID).equals(-1));
			   
         		}
		   }
		   if(finalArr.length!=0 && myCount!=0){
			  $('#modelalertLayer').show(); 
			   $('#modelalert .highPriorityAlertLst tbody').append('<tr>'+finalArr[i].title+'</td><td>'+finalArr[i].alertMsg+'</td><td><button type="button" class="buttonAck" onclick="ackAlertCurrentRow(this,\''+finalArr[i].alertID+'\')">Acknowledge</button></td></tr>');
			  
		   }
		   else
		$('#modelalertLayer').hide();
		   
		 },  
		  error: function(e){  
		   //alert('Error: ' + e);
			   alert('No High Priority Alert Found!');   
		 }  
		 });     
});

 function indexOfMyArr(arr, val)
{
	 var existsCheck=0;  
	 for(var k=0; k<arr.length; k++)
	 	{
	 			if(arr[k]==val)
	 			{ 
	 				existsCheck=1;
	 				return existsCheck;
	 			} 
	 			else
	 			{ 
	 				existsCheck=0;	
	 			}
	 	} 
	 	return existsCheck; 
} 
function ackAlertCurrentRow(e,id){ 
	var temp = [];
	if(localStorage.getItem("alertId"))
	{
		temp= JSON.parse(localStorage.getItem("alertId")); 
		localStorage.removeItem("alertId"); 
	}   
		temp.push(id); 
		let x = function(temp){
			return	temp.filter(
					function(v,i){
						return (temp.indexOf(v) === i);
					});
		}
		temp = x(temp); 
		localStorage.setItem("alertId",JSON.stringify(temp));    
		$(e).closest('tr').remove();
} 

/* $('#closebtn').on('change',function(){
	$('#modelalertLayer').hide();
	//$('#modelalert').hide();
	// e.preventDefault();
	 //e.stopPropagation();
}); */
</script> 
<script>

$(function(){
	$( ".buttonAck" ).trigger( "click" );
	$(".buttonAck").hide();	
	//alert("inside clickAll func ");
	$("#ackAll").on("click", function(){
	$( ".buttonAck" ).trigger( "click" );
	//$("#modelalertLayer").hide();
	});	
	
});
</script>

<%
	String key=(String)session.getAttribute(HISSSOServerConfig.LOGIN_TAB_KEY);
	String strDefaultFirstMenu=null;
	
	AuthorizationCredentials objAuthorize = (AuthorizationCredentials) session.getAttribute(HISSSOConfig.LOGGEDIN_USER_AUTHORIZATION_OBJECT);
	if(objAuthorize!=null && objAuthorize.getMenusHirarchyMap()!=null){
		 Map menuMap=objAuthorize.getMenusHirarchyMap();
		 Map.Entry firstMenu = (Map.Entry)menuMap.entrySet().iterator().next();
		 
		 //System.out.println("HIS-Login:: First Menu: "+firstMenu.getKey().toString());
		 strDefaultFirstMenu=firstMenu.getKey().toString();
	}
%>
<script type="text/javascript">
var maxLastVisitedMenuCount=15;
var lastVisitedMenus= new Array(maxLastVisitedMenuCount);
var lastVisitedURLs= new Array(maxLastVisitedMenuCount);
var lastVisitedMenusStartIdx=-1;
var lastVisitedMenusEndIdx=-1;
var menuJSONObj;
var allMenus = new Array();

var varSecretKey= '<%=key%>';
//alert("window.name.toString() :"+window.name.toString());
if(window.name.toString()=='' || window.name.toString()!=varSecretKey)
{
	alert("You are trying to open New Tab/Window, Please go to original window.");
	//submitForm("errorLogin");
	// getBrowser().removeCurrentTab();
	if(typeof window.stop == 'undefined')
		document.execCommand("Stop");
	else
		window.stop();

	close();
}
else
{
     // alert("Winow name "+(window.name.toString()));
}

function loginDeskOnload()
{
	noBack();
	setTime();
}

function setTime()
{   
		
	if('<s:property value="varIsFirstTimeLogin" />'=='1')
		openURLInPopupWithoutClose("/AHIMSG5/hislogin/initFirstLoginLgnFtr","600","400");
	//changed by garima for extension change
	//openURLInPopupWithoutClose("/AHIMSG5/hislogin/initFirstLoginLgnFtr.action","600","400");	

	// Setting Time initially on Load
	var dateAsString = document.getElementsByName("varCurrentDate")[0].value;
	currentDate = convertStrToDate(dateAsString,'dd-MM-yyyy hh:mm'); // for format dd-MM-yyyy HH:mm  of 'dateAsString2'
	var tdDate=document.getElementById("dateTdId");
 	var newDateFormat = convertDateToStr(currentDate, "Week, dd-Mon-yyyy hh:mm");
	tdDate.innerHTML=newDateFormat;
	
	//alert("set time")
	InitializeTimer();
}

function noBack()
{
	window.history.forward();
}
noBack();

window.onload = loginDeskOnload;
window.onpageshow = function(evt) {
	if (evt.persisted)
		noBack();
}

window.onunload = function() {
		void (0);
		callLogOut(event);
}

//To Show the Menus in UI Tabs,Added by Singaravelan on 24-Jul-2015	<-Starts Here->
var MAX_TAB_COUNT=<%=HISSSOConfig.LOGGEDIN_USER_MAX_TAB_ALLOWABLE %>;
var tabCount=0;
function callMenu(url,menu)
{
		//alert('menu: '+menu+' called with url: '+ url);
		var targetURL = url;
		
		if(checkURL(url))
		{
			//alert (url.indexOf("?"));
			if(url.indexOf("?")==-1)
				targetURL+= "?varSSOTicketGrantingTicket=" + document.getElementsByName("varSSOTicketGrantingTicket")[0].value;
			else
				targetURL+= "&varSSOTicketGrantingTicket=" + document.getElementsByName("varSSOTicketGrantingTicket")[0].value;
			maintainLastSeenMenuArray(url,menu);	
		}
		else
		{
			targetURL = "/AHIMSG5/hissso/jsp/error/sso_error_login_appnotavail.jsp";
		}
		
		//Commented to Show the URLs in the UI Tabs,Done by Singaravelan on 24-Jul-2015
		/*var elemFrame = document.getElementById("frmMain"); 
		elemFrame.src=targetURL;*/
		
		//elemFrame.refresh();
		
		//To Show the Menus in UI Tabs,Added by Singaravelan on 24-Jul-2015		
		addTab(menu,targetURL);			
	
}

function showDefaultMenuinTab(url,menuName,moduleName,isShow){
	//alert("Inside showDefaultMenuinTab :: Module :: "+moduleName+":: Menu :: "+menu+" Url ::"+url+" isShow ::"+isShow);
	if(isShow=='true'){
		callMenu(url, menuName);
		menuSelected(moduleName,false);
	}
	else{
		menuSelected('<%=strDefaultFirstMenu%>',false);
	}
}
</script>

<script>
 $(function(){
	$(document).on('show.bs.modal','#modelalert', function () {
		var divh = $("#modelalert").height();
		var win = $(window).height();
		if (divh > win ) {
		$("#modelalert").addClass('setheight');
						}
		 });
		  
}); 

</script>
	
</head>

<%

	String strDefaultURL=null;
	String strDefaultURLModule= null;
	String strDefaultURLName= "Default";	
	
	boolean showDefaultUrlTab=false;
	AuthenticationCredentials objAuthenticate = (AuthenticationCredentials) session.getAttribute(HISSSOConfig.LOGGEDIN_USER_AUTHENTICATION_OBJECT);
	if (objAuthenticate != null)
	{
		String strDefaultMenuURL = objAuthenticate.getVoUser().getVarDefaultMenuURL(); 		
		if(strDefaultMenuURL!=null && !strDefaultMenuURL.trim().equals(""))
		{
			strDefaultURL = strDefaultMenuURL;
			showDefaultUrlTab=true;
			strDefaultURLName=objAuthenticate.getVoUser().getVarDefaultMenuName();
			strDefaultURLModule=objAuthenticate.getVoUser().getVarDefaultMenuModule();
			showDefaultUrlTab=UserDeskUTL.isDefaultURLAuthorized(strDefaultURL, strDefaultURLName, strDefaultURLModule,request,response);
		}
	}

%>


<body onload="loginDeskOnload();showDefaultMenuinTab('<%=strDefaultURL%>','<%=strDefaultURLName%>','<%=strDefaultURLModule%>','<%=showDefaultUrlTab%>')" onunload="callLogOut(event)" onbeforeunload="callLogOut(event)" >
<s:form action="UserDesk">
 
 <button type="button" id="btnModalalert" class="btn btn-info btn-lg" data-toggle="modal" data-target="#modelalert" style="display:none;"></button>
 <div id="modelalertLayer">
 <div id="modelalert" class="modal fade" role="dialog">
  <div class="modal-dialog modal-lg" id="modalbid">
  
    <!-- Modal content-->
    <div class="modal-content"> 
     
      <div class="modal-body">
      <input type="button" value="X"  class="closeButton" id="closebtn" onclick="$('#modelalertLayer').hide();"/>
     <div class="container-fluid" >
		 <div class="row">
			
		 	<div class="col-sm-12">
		 	  <div class="table-responsive">
		 		<table class="table table-hover highPriorityAlertLst" style="width: 100%; border-collapse: collapse;">
		 			<tbody> 
		 			</tbody>
		 			 <tfoot>
							    <tr>
							     <td colspan="3" style="border: 0px; text-align: right;">
									 		<input type="button" value="Acknowledge All" class="buttonAckAll" id="ackAll" style="width:110px; align: center !important; height:30px;  color:white; align: center; valign: bottom;" onclick="$('#modelalertLayer').hide();"/>
									 			
								</td>
							    </tr>
 					 </tfoot>
		 		</table>
		 	
		 	</div> 
		 </div>
		</div>
	 </div>
    </div> 
   </div> 
  </div>
</div>
</div>
<div class="wrapper">

<!-- code to display favourite menus icon on left side of window --> 
<!-- <div class='widget-reset' id='envato-widget' style='display: block !important;width: 3px;' onmouseover="showDiv();">
</div> -->

<div class='widget-reset' id='envato-widget1' style='display: none ;' onmouseout="hideDiv();">
<%
	List<MenuMasterVO> lstFavMenus = (List<MenuMasterVO>) session.getAttribute(HISLoginConfig.LOGGEDIN_USER_FAVAOURITE_LIST);
	if(lstFavMenus!=null && lstFavMenus.size()>0)
	{
		Iterator favIterator= lstFavMenus.iterator();
		while(favIterator.hasNext()){
			MenuMasterVO voMenu= (MenuMasterVO)favIterator.next();
			%>
			<a class='widget-button envato' title='<%= voMenu.getVarMenuName() %>' onclick="callMenu('<%=voMenu.getVarURL() %>')" rel='nofollow' href='javascript:void(0);' sl-processed='1'><%=voMenu.getVarURL() %></a>
			<%System.out.println("HIS-Login::List Element: "+ voMenu.getVarMenuName());
		}
		if(strDefaultURL==null)
			strDefaultURL = "/AHIMSG5/hislogin/transactions/jsp/st_desk_favourites_page.jsp";	
	}

	if(strDefaultURL==null && lstFavMenus.size()==0)
		strDefaultURL = "/AHIMSG5/hislogin/transactions/jsp/st_desk_background.jsp";
%>
</div>

<!-- code to display favourite menus icon on left side of window Ends -->

<jsp:include page="/hislogin/transactions/jsp/st_desk_header.jsp"></jsp:include>
		
		<div class="body">
<jsp:include page="/hislogin/transactions/jsp/st_desk_menu.jsp"></jsp:include>		
		<div class="bodyContent">
		<!-- Use Iframe here -->
			<div id="tabframe" class="easyui-tabs" style="overflow:auto;">
				<!-- <div title="Home" id="homemenuId">
					<iframe id="frmMain" src="/AHIMSG5/hislogin/transactions/jsp/st_desk_favourites_page.jsp" frameborder="0" height="100%" width="100%" onload="reloadCashDtl();"></iframe>
				</div> -->
				<div title="Home Menu" id="homemenuTabId">
					<iframe id="frmMainMenu" src="/AHIMSG5/hislogin/transactions/jsp/st_desk_background.jsp" frameborder="0" height="100%" width="100%" onload="reloadCashDtl();"></iframe>
				</div>
			</div>
		</div>
		
<!-- 		<iframe id="frmMain" src="/AHIMSG5/hislogin/transactions/jsp/st_desk_background.jsp" frameborder="0" height="100%" width="100%" onload="reloadCashDtl();"></iframe> -->
			<div id="loadingmessage" class="wrapper rounded" style="display:none;width:180px;height:100px;position:absolute;top:40%;left:42%;padding:2px;z-index:100;">
				<div class="div-table">
					<div class="div-table-row">
						<div class="div-table-col title width100 ">
								Please Wait...
						</div>
					</div>
					<div class="div-table-row alignCenter" >
						<div class="div-table-col width100 " style="margin-top: 7px;">
								<img src="/HIS/hisglobal/images/ajax-loader.gif" width="50" height="50" />
						</div>
					</div>
				</div>
			</div>
			<div id="loadingTab" class="wrapper rounded" style="display:none;width:180px;height:100px;position:absolute;top:40%;left:42%;padding:2px;z-index:100;">
				<div class="div-table">
					<div class="div-table-row">
						<div class="div-table-col title width100 ">
								Please Wait...
						</div>
					</div>
					<div class="div-table-row alignCenter" >
						<div class="div-table-col width100 " style="margin-top: 7px;">
								<img src="/HIS/hisglobal/images/ajax-loader.gif" width="50" height="50" />
						</div>
					</div>
				</div>
			</div>
			<div id="loadingMenu" class="wrapper rounded" style="display:none;width:180px;height:100px;position:absolute;top:40%;left:42%;padding:2px;z-index:100;">
				<div class="div-table">
					<div class="div-table-row">
						<div class="div-table-col title width100 ">
								Please Wait...
						</div>
					</div>
					<div class="div-table-row alignCenter" >
						<div class="div-table-col width100 " style="margin-top: 7px;">
								<img src="/HIS/hisglobal/images/ajax-loader.gif" width="50" height="50" />
						</div>
					</div>
				</div>
			</div>
<!-- 		</div> -->
		
		<!-- 		<input type="button" value="ToggleMEnu" id="toggle"> -->
		</div>
		<!-- Desk Marquee Added by Singaravelan on 28-Oct-2015-->
		<div class="marque">
			<marquee id="footmarquee" behavior="scroll" direction="left" scrollamount="5" style="width:100%; height:100%; vertical-align:middle; cursor:pointer;" onmouseover="javascript:this.setAttribute('scrollamount','0');" onmouseout="javascript:this.setAttribute('scrollamount','5');">
			<font color="#1277B5" style="font-weight: bold;font-style: italic;"><%=request.getSession().getAttribute("mrqMsg")%> </font>
			</marquee>
		</div>
		<div class="footer">
			<img src="/HIS/hisglobal/images/footerImage.png">
			<!-- <span class="titleTag"> Designed and Developed by Center For Development Of Advanced Computing</span> -->
		</div>
	</div>
	
<div id="fade"></div>
<div id="modal">
    <img id="loader" src="/HIS/hisglobal/images/loading.gif" />
</div>
<s:hidden name="varSSOTicketGrantingTicket" ></s:hidden>
<s:hidden name="varUserName" ></s:hidden>
<s:hidden name="varUsrName" ></s:hidden>
<s:hidden name="varCurrentDate" ></s:hidden>
<s:hidden name="varIsAutoRefresh" ></s:hidden>
<input type="hidden" name="alertCount" id="alertCount" value="<%=request.getSession().getAttribute("alertCount") %>">

<s:token/>
</s:form> 
</body>
</html>