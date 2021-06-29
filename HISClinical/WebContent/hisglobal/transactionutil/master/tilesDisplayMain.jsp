<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>

<%String str1 = (String) request.getParameter("no_of_combo");
			String cmb[] = (String[]) request.getAttribute("combo");
			String str = "";
			if (cmb != null) {
				for (int i = 0; i < cmb.length; i++) {
					str += "<input type='hidden' name='cmb' value=" + cmb[i]+ ">";
				}
			}
			String cmbReset[] = (String[]) request.getAttribute("comboReset");
			String strReset = "";
			if (cmbReset != null) {
				for (int i = 0; i < cmbReset.length; i++) {
					strReset += "<input type='hidden' name='comboReset' value=" + cmbReset[i]+ ">";
				}
			}
			
			String strJs = (String) request.getAttribute("js");
			if (strJs == null)
				strJs = "";
%>

<html>
<head>
<title><%=request.getAttribute("masterName")%></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="../../hisglobal/transactionutil/css/master.css" rel="stylesheet"
	type="text/css">
<link href="../../hisglobal/transactionutil/css/menu.css" rel="stylesheet"
	type="text/css">
<script language="JavaScript" src="../../hisglobal/transactionutil/js/master.js"></script>
<script language="JavaScript" src="../../hisglobal/transactionutil/js/button.js"></script>
<script language="JavaScript" src="<%=strJs%>"></script>
<script language="JavaScript" type="text/javascript">

function setPersistenceValues(){
document.forms[0].searchColumn.value='<%=request.getParameter("searchColumn")%>'
document.forms[0].search.value='<%=request.getParameter("search")%>'
}

function checkChangeDiv(){
var divisionId	=	'<%=request.getParameter("divisionId")%>'

changeDiv(divisionId);

}

//The following variables used for view poup functionality
var child = null;
var popIndex = 0;
var gblCntrlObj = null;
window.history.forward(1);

function _suppressKeyPress(e){
		keys = new Array();
		keys[0] = 'Ctrl';
		keys[1] = 'Backspace';
		saveCode="";
		if (window.event)
			key = window.event.keyCode;
		else
			key = e.which;
		if(key == 116){
			saveCode = key;
			key = 505;
		}
		
		if(key == 505){
			alert('Ctrl has been disabled');
			return false;
		}
		
		if(window.event && key == 8 && !window.document.activeElement.isTextEdit){
			alert("window.event.keyCode"+key);
			saveCode=window.event.keyCode;
			window.event.keyCode = 506;
		}
		
		if(window.event && key == 506){
			alert('Backspace has been disabled');
			return false;
		}
}
function hideMenu()
{
		if(document.getElementsByName("strVisibilityMode")[0].value=="1")
		{
			hideMenuFrame();
		}

}
function hideMenuFrame()
{	
//alert("parent.document.getElementById('fs2').cols="+parent.document.getElementById("fs2").cols)
	if(window.XMLHttpRequest) // Mozilla
	{
		if(parent.document.getElementById("fs2").cols == "230,*")
		{
			parent.document.getElementById("fs2").cols = "0,*";
			parent.document.getElementById("f7").contentDocument.getElementById("Image").src="../hisglobal/images/arrsingle-right.png";
		}
	}
	//if (window.ActiveXObject)
	else
	{
		if(parent.document.getElementById("fs2").cols == "230,*")
		{
			parent.document.getElementById("fs2").cols = "0,*";
			parent.document.getElementById("f7").Document.getElementById("Image").src="../hisglobal/images/arrsingle-right.png";
		}
	}	
}
function showMenuFrame()
{	
//alert("showMenuFrame in reg");
	if(window.XMLHttpRequest) // Mozilla
	{
		parent.document.getElementById("fs2").cols = "230,*";
		parent.document.getElementById("f7").contentDocument.getElementById("Image").src="../hisglobal/images/arrsingle-left.png";
	}
	//if (window.ActiveXObject)
	else
	{
		parent.document.getElementById("fs2").cols = "230,*";
		parent.document.getElementById("f7").Document.getElementById("Image").src="../hisglobal/images/arrsingle-left.png";
	}	
}
</script>
</head>

<%
String blockNo=request.getParameter("blockNo");
String rowNum=request.getParameter("rowNum");
String prevNext=request.getParameter("prevNext") ;
String functionname="fetchRecords(null,null,null)";

if(blockNo != null && !blockNo.equals(""))
	functionname="fetchRecords('m^"+blockNo+"^0','"+rowNum+"','"+prevNext+"')";


	
%>

<body onload="setPersistenceValues();<%=functionname %>;hideMenu();" onkeydown="searchData(event);" onKeyDown="return _suppressKeyPress(event);" onFocus="checkPopUp_master();" onUnload="closePopUp_master();">
<html:form action='<%=(String)request.getAttribute("cnt_page")%>'>


	<table height="35px" width="100%" cellpadding="0" cellspacing="0">
		<tbody><tr><td class="ShadedTitleTagImage" width='100%'>
			<table height="100%" width="100%" cellpadding="2" cellspacing="0">
				<tbody>
				<tr>
					<td valign="top" width="30%" align="left"></td>
					<td width="30%"><div id="doctorDeskHeader" align="center"><%=(String)request.getAttribute("masterName")%></div></td>
					<td width="40%"><div id="divPatDeskStatistics" align="right"></div></td>
				</tr>
			</tbody></table>
		</td></tr>
	</tbody></table>
	
	<table align="center" width="100%" CELLPADDING="0" CELLSPACING="0"  style="border: 1px solid #013157; border-collapse: collapse;">
		<tr>
			<td valign="top" width="100%" colspan='3'><div id="comboId" ></div></td>
		</tr>
		<tr>
			<td colspan="1" width='3%' valign="top"><tiles:insert attribute="menu" /></td>
			<td colspan="2" width='97%' valign="top"><tiles:insert attribute="ListPage" /></td>				
		</tr>
		<tr>
			<%String strAlign = (String) request.getAttribute("buttonConfig");
				if(strAlign==null || strAlign.equals(""))
					strAlign = "left";
				if (strAlign.equalsIgnoreCase("left")) {

				%>
				
				<td colspan="1" width='3%' valign="top"><tiles:insert attribute="menu" /></td>
				<td colspan="2" width='97%' valign="top"><div onmouseover="if(child!=null) {child.close();}" ><tiles:insert attribute="ListPage" /></div></td>		
			
			<!--  <td valign="top" width="10%"></td>
			<td  colspan="2" valign="top" width="85%"><div onmouseover="if(child!=null) {child.close();}" ><tiles:insert attribute="ListPage" /></div></td>-->
			<%} else if (strAlign.equalsIgnoreCase("right")) {

				%>
				<td colspan="2" width='97%' valign="top"><div onmouseover="if(child!=null) {child.close();}" ><tiles:insert attribute="ListPage" /></div></td>
				<td colspan="1" width='3%' valign="top"><tiles:insert attribute="menu" /></td>				
				
			<!--<td valign="top" width="85%"><div onmouseover="if(child!=null) {child.close();}"><tiles:insert attribute="ListPage" /></div></td>
			<td colspan="2" valign="top" width="10%"></td>-->
			<%} else if (strAlign.equalsIgnoreCase("both")) {

				%>
				
				<td colspan="1" width='3%' valign="top"><tiles:insert attribute="menu" /></td>
				<td colspan="1" width='94%' valign="top"><div onmouseover="if(child!=null) {child.close();}" ><tiles:insert attribute="ListPage" /></div></td>
				<td colspan="1" width='3%' valign="top"><tiles:insert attribute="menu1" /></td>	
				
			<!--<td valign="top"></td>
			<td valign="top"><div onmouseover="if(child!=null) {child.close();}"><tiles:insert attribute="ListPage" /></div></td>
			<td valign="top"></td>-->
			<%}%>
		</tr>
	
	
		<tr>
			<td colspan="3" width="100%">
			<DIV ID="searchid" ALIGN="top"></DIV>
			</td>
		</tr>
	
		<tr>
			<td colspan="3">
			<div id='footer' style='display:none'></div>
			</td>
		</tr>
	</table>
	
	<table align="center" width="100%" border="1" CELLPADDING="0" CELLSPACING="0" bgcolor="" bordercolor="#013157 ">
		<TR><TD colspan="3" ALIGN="center"><DIV ID="message" ALIGN="top"></DIV></TD></TR>	
	</table>
	
	<table align=center width="90%">
		<tr>
			<td class='addtoolbar' height='23' align='center' id="buttonID"></td>
		</tr>
	</table>
	<input type="hidden" name="record_per_page">
	<input type="hidden" name="counter" value='0'>
	<input type="hidden" name="no_of_combo" value='<%=str1%>'>
	<input type="hidden" name="actual_page_block">
	<input type="hidden" name="nextBlock">
	<input type="hidden" name="prevBlock">
	<input type="hidden" name="divisionId" >
	<input type="hidden" name="divid">
	<input type="hidden" name="hmode">
	<input type="hidden" name="checkCount">
	<input type="hidden" name="flag" value='1'>
	<input type="hidden" name="comboValue">
	<input type="hidden" name="chkValue" value="">
	<input type="hidden" name="chkLength" value="0">
	<input type="hidden" name="totalpage">
	<input type="hidden" name="cnt_page" value='<%=request.getAttribute("cnt_page")%>'>
	<input type="hidden" name="search" >
	<input type="hidden" name="searchColumn">
	<input type="hidden" name="rowNum" >
	<input type="hidden" name="prevNext">
	<%=str%>
	<%=strReset%>
	<input type="hidden" name="strVisibilityMode" value='<%=request.getAttribute("visibilityMode")%>'>

</html:form>
</body>
</html>
