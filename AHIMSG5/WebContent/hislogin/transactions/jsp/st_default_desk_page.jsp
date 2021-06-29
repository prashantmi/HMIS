<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="hissso.validation.credentails.authentication.AuthenticationCredentials"%>
<%@page import="vo.usermgmt.MenuMasterVO"%>
<%@page import="hislogin.config.HISLoginConfig"%>
<%@page import="hissso.config.HISSSOConfig"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="hisglobal.utility.Entry"%>

<html>
	<head>
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
<script src="/HIS/hisglobal/js/jquery.js"></script>
<script type="text/javascript">
	$(function() {
		$(document).ready(function() {
			var ht = Math.round($(window).height())-50 + "px";
			var wt = Math.round($(window).width())-50 + "px";
			$('body').height(ht);
			$('body').width(wt);
		});
		
	});
	
	function callMenu(url)
	{
		//alert('menu called with url: '+ url);
		var targetURL = url + "?varSSOTicketGrantingTicket=" + document.getElementsByName("varSSOTicketGrantingTicket")[0].value;
		
		
		var elemFrame = parent.document.getElementById("frmMain");
		elemFrame.src=targetURL;
		elemFrame.refresh();
	}
</script>
</head>

<body style="background-image: url(/HIS/hisglobal/images/login/bodybg.jpg);background-repeat: no-repeat; background-position: center;">
	<%
		String strDefaultURL= null;
		AuthenticationCredentials objAuthenticate = (AuthenticationCredentials) session.getAttribute(HISSSOConfig.LOGGEDIN_USER_AUTHENTICATION_OBJECT);
		if (objAuthenticate != null)
		{
			String strDefaultMenuURL = objAuthenticate.getVoUser().getVarDefaultMenuURL(); 
			if(strDefaultMenuURL!=null && !strDefaultMenuURL.trim().equals(""))
			{
				strDefaultURL = strDefaultMenuURL;
			}
		}
		List<MenuMasterVO> lstFavMenus = (List<MenuMasterVO>) session.getAttribute(HISLoginConfig.LOGGEDIN_USER_FAVAOURITE_LIST);
		if(lstFavMenus!=null && lstFavMenus.size()>0 && strDefaultURL==null)
			strDefaultURL = "/AHIMSG5/hislogin/transactions/jsp/st_desk_favourites_page.jsp";
		if(lstFavMenus.size()==0 && strDefaultURL==null)
			strDefaultURL="/AHIMSG5/hislogin/transactions/jsp/st_desk_background.jsp";
	%>
	<input type="hidden" name="varSSOTicketGrantingTicket" value="<%=request.getParameter("varSSOTicketGrantingTicket")%>">
	
	<script type="text/javascript">
		callMenu('<%=strDefaultURL%>');
	</script>
	 
</body>
</html>		