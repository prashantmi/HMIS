<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<HTML>
<HEAD>  

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<% 

String str		=	""; 
String str1		=	(String)request.getParameter("no_of_combo");
String cmb[]	=	(String[])request.getAttribute("combo");
  
if(cmb!=null) 
{ 
	for(int i=0;i<cmb.length;i++) 
	{
	  str +="<input type='hidden' name='cmb' value="+cmb[i]+">";
	}  
}

//js 
String strJs = (String)request.getAttribute("js");
if(strJs == null) strJs = "";
String temp="";
if(request.getAttribute("masterName")!=null)
temp=(String)request.getAttribute("masterName");
else
	temp="Master";
String masterName=temp.split("#")[0];
 %>
<TITLE> <%=masterName%> List Page</TITLE>
<script>

//The following variables used for view poup functionality
var child = null;
var popIndex = 0;
var gblCntrlObj = null;
//
			
window.history.forward(1);
//window.document.onkeydown = _suppressKeyPress;


function _suppressKeyPress()
{

keys = new Array();
keys[0] = 'Ctrl';
keys[1] = 'Backspace';

saveCode="";
if(window.event.keyCode == 116)
{
saveCode=window.event.keyCode;
window.event.keyCode = 505;
}


if(window.event.keyCode == 505)
{
alert('Ctrl has been disabled');
return false;
}


if(window.event && window.event.keyCode == 8 && !window.document.activeElement.isTextEdit)
{
alert("window.event.keyCode"+window.event.keyCode);
saveCode=window.event.keyCode;
window.event.keyCode = 506;
}
if(window.event && window.event.keyCode == 506)
{
alert('Backspace has been disabled');
return false;
}

}

</script>

<script language="JavaScript" src="../../hisglobal/masterutil/js/master.js"></script>
<script language="JavaScript" src="../../hisglobal/js/validation.js"></script>
<script language="JavaScript" src="../../hisglobal/js/util.js"></script>


<logic:notEmpty scope="request" name="js"><script language="JavaScript" src="${requestScope.js}"></script></logic:notEmpty>
<script>
InitDragDrop();
</script>
<link href="../../hisglobal/masterutil/css/master.css" rel="stylesheet" type="text/css">

</HEAD>  
<BODY onload='fetchRecords(null,null,null);assignClassToButtons();' onFocus="checkPopUp_master();" onUnload="closePopUp_master();" class='bodycolor' >
<html:form action='<%=(String)request.getAttribute("cnt_page")%>' >
	<input type= "hidden" name="totalpage">
	<TABLE ALIGN="center" WIDTH="95%" CELLPADDING="0" CELLSPACING="0" BORDER="0">
		<TR><TD ALIGN="center">
				<TABLE  ALIGN="center">	  
		 			<TR>
						<TD>
							<DIV ID="message" ALIGN="center"></DIV>
						</TD>
					</TR>
	 			</TABLE>
			</TD>
		</TR>
			<TR>
				<TD ALIGN="center">
					<TABLE WIDTH="100%" border='0' cellpadding="1" cellspacing="1" >
						<TR>
							<TD>
								<DIV ID="start" ALIGN="left"></DIV>
								
		 	 				</TD>
						</TR>
					</TABLE>
					
					
				</TD>
			</TR> 
			
			  
			
	</TABLE> 

	<TABLE ALIGN="center" WIDTH="95%" CELLPADDING="1" CELLSPACING="1" BORDER="0">
		<TR>
			<TD>
				<DIV ID="searchid" ALIGN="right"></DIV>
			</TD>
		</TR>  
		
	</TABLE>
	
	
	<div id='footer' style='display:none'>
	<table align=center><tr><td class=addtoolbar height='23' align = 'center'> 
	
	<%
	 if (request.getAttribute("BUTTONS") !=null)
	   	out.println(request.getAttribute("BUTTONS"));
		%>
	  
	   
	</td></tr>
	
	
	</table>
	</div> 
	<input type="hidden" name="record_per_page">
  	<input type="hidden" name="counter" value='0'>
	<input type="hidden" name="no_of_combo" value='<%=str1%>'>
	<input type="hidden" name="actual_page_block">
	<input type="hidden" name="nextBlock">
	<input type="hidden" name="prevBlock">         
	<input type="hidden" name="divisionId"> 
	<input type="hidden" name="divid">
	<input type="hidden" name="hmode">
	<input type="hidden" name="flag" value='1'>
	<input type="hidden" name="comboValue">
	<input type="hidden" name="cnt_page" value='<%=request.getAttribute("cnt_page")%>'>
	<input type='hidden' name='orderByName' id='orderById'>
	<input type="hidden" name="isGlobal" value='<%=request.getAttribute("isGlobal")%>'>
	<%=str%>	
</html:form>

</BODY>
<script>
var allImageObjectForDragTarget=document.getElementsByTagName("img");
for(var nTmpI=0;nTmpI<allImageObjectForDragTarget.length;nTmpI++){
	var strImageName=allImageObjectForDragTarget[nTmpI].src.split("/")[allImageObjectForDragTarget[nTmpI].src.split("/").length-1];
	if(strImageName=="Modify.gif" || strImageName=="Delete.gif" || strImageName=="View.gif")
		addDropTarget(allImageObjectForDragTarget[nTmpI]);
}
</script>
</HTML>
