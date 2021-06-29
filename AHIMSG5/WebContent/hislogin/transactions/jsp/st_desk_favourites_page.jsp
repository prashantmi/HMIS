<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="vo.usermgmt.MenuMasterVO"%>
<%@page import="hislogin.config.HISLoginConfig"%>
<%@page import="hissso.config.HISSSOConfig"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="hisglobal.utility.Entry"%>

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
	<script>


function showDiv()
{
	document.getElementById("envato-widget").style.display= "";
}



function callMenu(url,menu)
{
	parent.callMenu(url,menu);
}
</script>
	
	<style type="text/css"> 
#mydiv {
   margin-top: 3em; /*set to a negative number 1/2 of your height*/
    margin-left: 5em; /*set to a negative number 1/2 of your width*/;
    font-family: verdana;
    font-size: 11px;
    font-weight: bold;
    text-align: center;
    cursor: pointer; 
    color: rgb(6,69,173);
    width:150px;height:90px;border:1px solid #C0C0C0;
    word-wrap: break-word;
}
#mydiv img{
width: 145px;
height: 90px;
}

.sidebar {
   width: 30px;
   height: 200px;
   position: fixed;
   left: 760px;
   top: 160px;
   border: 1px solid grey;
}
.widget-reset div,.widget-reset form,.widget-reset a,.widget-reset ul,.widget-reset li{
	border:0;
	outline:0;
	font-weight:inherit;
	font-style:inherit;
	font-size:100%;
	font-family:inherit;
	vertical-align:baseline;
	margin:0;
	padding:0
}
#envato-widget {
    background: url("images/px.gif") repeat-y scroll right top rgb(244, 244, 244);
    border-width: 1px 1px 1px 1px;
    border-style: solid none solid solid;
    border-color: rgb(190, 190, 190) rgb(190, 190, 190) rgb(190, 190, 190) rgb(190, 190, 190) ;
    -moz-border-top-colors: none;
    -moz-border-right-colors: none;
    -moz-border-bottom-colors: none;
    -moz-border-left-colors: none;
    border-image: none;
    display: block !important;
    font: 13px/17px Helvetica,Arial,sans-serif;
    height: 200px;
    
    right:-2px;
    position: fixed;
    top: 165px;
    width: 24px;
    z-index: 890;
    border-top-right-radius: 3px;
    border-bottom-right-radius: 3px;
    border-top-left-radius: 3px;
    border-bottom-left-radius: 3px;
    box-shadow: 1px 1px 3px rgba(0, 0, 0, 0.1);
}
#envato-widget a.widget-button.envato {
    background-position: -4px 0px;
    border-top: medium none;
    height: 32px;
}
#envato-widget a.widget-button {
    background-image: url("/AHIMSG5/hisglobal/images/D.png"); 
    background-repeat: no-repeat;
    border-bottom: 1px solid rgb(254, 254, 254);
    border-top: 1px solid rgb(228, 228, 228);
    cursor: pointer;
    display: block;
    float: left;
    height: 31px;
     text-indent: -9999px; 
    width: 23px;
}
</style>
	</HEAD>
	<BODY  style="background-image: url(/HIS/hisglobal/images/login/bodybg3.jpg); background-color: #fFF;">
		<table id="homeTabTableId" cellpadding="0" cellspacing="0" width="100%"> 
    	
    		<% 
    		 ArrayList defaultURL=new ArrayList<String>();
    		 List<MenuMasterVO> lstFavMenus = (List<MenuMasterVO>) session.getAttribute(HISLoginConfig.LOGGEDIN_USER_FAVAOURITE_LIST); 
    		/* defaultURL=(ArrayList<String>)request.getSession().getAttribute("DEFAULTURLLIST"); */
    		int maxFavIcons= 12;
    		String[] favMenuNameList= new String[maxFavIcons]; // max 12 icons allowed
    		String[] favMenuURLList=  new String[maxFavIcons];
    		int x=0;
    		if(lstFavMenus!=null)
    		{
	    		
	    		Iterator favIterator= lstFavMenus.iterator();
	    		while(favIterator.hasNext()){
	    			MenuMasterVO voMenu= (MenuMasterVO)favIterator.next();
	    			favMenuNameList[x]=voMenu.getVarMenuName();
	    			favMenuURLList[x]=voMenu.getVarURL();
	    			x++;	    				
	            }
    		}
    		while(x<maxFavIcons){
    			favMenuNameList[x]="";
    			favMenuURLList[x]="#";
    			x++;
    		}
    		String menu;
    		for(int i=0; i<maxFavIcons; i++){
    			menu=favMenuNameList[i];
    			if(menu.length() > 30)
    				menu=menu.substring(0, 30)+"...";
    			if((i+1)%4==1){
    				out.println("<tr>");
    			}
    			if(favMenuNameList[i].equals(""))
    				out.println("<td><div id=\"mydiv\" >"+
    		    			"<img src='/HIS/hisglobal/images/xIcon.png' title='"+favMenuNameList[i]+"' onclick=\"callMenu('"+favMenuURLList[i]+"','"+favMenuNameList[i]+"');\"  style='cursor: pointer;background-attachment: fixed;'>&nbsp;<a title='"+favMenuNameList[i]+"' onclick=\"callMenu('"+favMenuURLList[i]+"','"+favMenuNameList[i]+"');\" ><div>"+menu+"</div></a></div></td>");
    			else
    			out.println("<td><div id=\"mydiv\" >"+
		    			"<img src='/HIS/hisglobal/images/pageIconLogo.png' title='"+favMenuNameList[i]+"'  onclick=\"callMenu('"+favMenuURLList[i]+"','"+favMenuNameList[i]+"');\"  style='cursor: pointer;background-attachment: fixed;'>&nbsp;<a title='"+favMenuNameList[i]+"' onclick=\"callMenu('"+favMenuURLList[i]+"','"+favMenuNameList[i]+"');\" ><div>"+menu+"</div></a></div></td>");
    			if((i+1)%4==0){
    				out.println("</tr>");
    			}
    		}
		 %>
			 

 		</table>
 		<input type="hidden" name="varSSOTicketGrantingTicket" value="<%=request.getParameter("varSSOTicketGrantingTicket")%>"> 
	</BODY>
</HTML>
		