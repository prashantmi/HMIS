<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="hislogin.transactions.utl.UserDeskUTL"%>
<%@page import="vo.usermgmt.MenuMasterVO"%>
<%@page import="hislogin.config.HISLoginConfig"%>
<%@page import="hissso.config.HISSSOConfig"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="hisglobal.utility.Entry"%>

<!-- @author 		: Singaravelan 
	 @created Date  : 04-Aug-2015
	 @purpose		: New Home Tab Menus
 --> 
 
<HTML>
<HEAD>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<META Http-Equiv="Cache-Control" Content="no-cache" />
<META Http-Equiv="Cache-Control" Content="no-store" />
<META Http-Equiv="Pragma" Content="no-cache" />
<META Http-Equiv="Expires" Content="0" />

<%
	response.setHeader("Cache-Control", "no-cache");
	response.setHeader("Pragma", "no-cache");
	response.setDateHeader("Expires", 0);
	response.setHeader("Cache-Control", "no-store");
%>
<link rel="stylesheet" type="text/css" href="/HIS/hisglobal/css/tabmenu/tabmenu.css"> 

<script type="text/javascript" src="/HIS/hisglobal/js/jquery.js"></script>
<!-- <script type="text/javascript" src="/HIS/hisglobal/js/jquery/jquery.easyui.min.js"></script> -->
<script type="text/javascript" src="/HIS/hisglobal/js/jquery/scrollbar/js/jquery.slimscroll-ext.js"></script>
<script type="text/javascript" src="/HIS/hisglobal/utility/dynamicdesk/js/jquery.min.js"></script>

<script>

var Base64={_keyStr:"ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=",encode:function(e){var t="";var n,r,i,s,o,u,a;var f=0;e=Base64._utf8_encode(e);while(f<e.length){n=e.charCodeAt(f++);r=e.charCodeAt(f++);i=e.charCodeAt(f++);s=n>>2;o=(n&3)<<4|r>>4;u=(r&15)<<2|i>>6;a=i&63;if(isNaN(r)){u=a=64}else if(isNaN(i)){a=64}t=t+this._keyStr.charAt(s)+this._keyStr.charAt(o)+this._keyStr.charAt(u)+this._keyStr.charAt(a)}return t},decode:function(e){var t="";var n,r,i;var s,o,u,a;var f=0;e=e.replace(/[^A-Za-z0-9\+\/\=]/g,"");while(f<e.length){s=this._keyStr.indexOf(e.charAt(f++));o=this._keyStr.indexOf(e.charAt(f++));u=this._keyStr.indexOf(e.charAt(f++));a=this._keyStr.indexOf(e.charAt(f++));n=s<<2|o>>4;r=(o&15)<<4|u>>2;i=(u&3)<<6|a;t=t+String.fromCharCode(n);if(u!=64){t=t+String.fromCharCode(r)}if(a!=64){t=t+String.fromCharCode(i)}}t=Base64._utf8_decode(t);return t},_utf8_encode:function(e){e=e.replace(/\r\n/g,"\n");var t="";for(var n=0;n<e.length;n++){var r=e.charCodeAt(n);if(r<128){t+=String.fromCharCode(r)}else if(r>127&&r<2048){t+=String.fromCharCode(r>>6|192);t+=String.fromCharCode(r&63|128)}else{t+=String.fromCharCode(r>>12|224);t+=String.fromCharCode(r>>6&63|128);t+=String.fromCharCode(r&63|128)}}return t},_utf8_decode:function(e){var t="";var n=0;var r=c1=c2=0;while(n<e.length){r=e.charCodeAt(n);if(r<128){t+=String.fromCharCode(r);n++}else if(r>191&&r<224){c2=e.charCodeAt(n+1);t+=String.fromCharCode((r&31)<<6|c2&63);n+=2}else{c2=e.charCodeAt(n+1);c3=e.charCodeAt(n+2);t+=String.fromCharCode((r&15)<<12|(c2&63)<<6|c3&63);n+=3}}return t}}
function clickMenu(url,menu,evCode){
	if(evCode.keyCode==13)
		callMenu(url,menu);
	else
		return false;
}

function callMenu(url,menu)
{
	var selMenu=menu;
	parent.ajaxStartMenu();
	menu=menu.toString().replace(/_/g,' ').replace(/XXY/g,'/').replace(/XXX/g,'&');
	url = Base64.decode(url).toString();
	parent.callMenu(url,menu);
	$("#"+selMenu).css("color","#6D00D5");
	parent.ajaxCompleteMenu();
}

function expandDiv(id){
	if($("#"+id).hasClass( "collapse" )){
		
		$('div[id*="_collapse"]').each(function(){
			if($(this).has('div').length==0){
				$(this).addClass( "collapse" );
				$('li[id*="_collapse_wrapper"]').css( "list-style-image", "url('/HIS/hisglobal/images/tabmenu/leaf-plus.png')" );
			}
		});
	
		
		$("#"+id+"_wrapper").css("list-style-image", "url('/HIS/hisglobal/images/tabmenu/leaf-minus.png')");
		$("#"+id+"_wrapper").attr("title","Click to Collapse");
		$("#"+id).removeClass( "collapse" );
	}
	else{
		$("#"+id+"_wrapper").css("list-style-image", "url('/HIS/hisglobal/images/tabmenu/leaf-plus.png')");
		$("#"+id+"_wrapper").attr("title","Click to Expand");
		$("#"+id).addClass( "collapse" );
	}
}

</script>
	
<style type="text/css"> 

html {
    height: 100%;
}
body {
    height: 100%;
    margin-left: 20px;
    background-repeat: no-repeat;
    background-attachment: fixed;
    overflow:hidden;
}

</style>
	</HEAD>
	<BODY  style="background: linear-gradient(to bottom, #E7EFF2 , #FFF ) transparent;">
	
		<%
		
		 StringBuffer str=new StringBuffer();
		 if(session.getAttribute(HISLoginConfig.LOGGEDIN_USER_SELECTED_MENU_PROCESS_LIST)!=null)
		 {
			 str.append(UserDeskUTL.generateMenuList(session));			 	 
		 }
		 
		 
	%>
		<div id="homeMenuId"><%=str %></div>
 		<input type="hidden" name="varSSOTicketGrantingTicket" value="<%=request.getParameter("varSSOTicketGrantingTicket")%>"> 
	</BODY>
</HTML>
		