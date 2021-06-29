<%@ page import="java.util.*"%>
<%@ page import="java.io.*"%>
<jsp:useBean id="inv" class="usermgmt.reports.errorPopupBean"
	scope="request" />

<HTML>
<HEAD>
<TITLE>Welcome to errorPopup.jsp</TITLE>
<link href="../../css/color.css" rel="stylesheet" type="text/css">
<link href="../../css/hisStyle.css" rel="stylesheet" type="text/css">
<link href="../../css/master.css" rel="stylesheet" type="text/css">
<script language="Javascript" src="../../usermgmt/js/empPopup.js"></script>
<script language="Javascript" src="../../usermgmt/js/Validation.js"></script>
<script language="Javascript" src="../../usermgmt/js/searchstr.js"></script>

<script type="text/javascript">

	function cancel()
{
	alert("in close");
	document.forms[0].hmode.value="SAME";
	document.forms[0].submit();
	alert("after close");
}
	</script>
</HEAD>
<BODY>
<%
	String filePath = request.getParameter("fullpath");
	String val = inv.getContents(filePath);
%>



<FORM METHOD='POST'>
<table width="100%" align="center">
	<tr>
		<td class="header"></td>
	</tr>
	<tr>
		<td class="tdfonthead">Contents of the File</td>
	</tr>
	<tr>
		<td class="tdfont"><textarea name="content" rows=10 cols=100><%=val%></textarea></td>
	</tr>
	<tr><td width="100%"><table width="100%"><tr>	
		<td class="tdfonthead" width="25%">Search in Contents </td>
		<td class="tdfonthead" width="25%">
			<input name="srchstr" maxlength="30" onkeypress="if(event.keycode==13) searchStringInTA(document.getElementsByName('srchstr')[0].value,document.getElementsByName('content')[0]); else return checkInput(this,7,30,event);"/>
		</td>
		<td class="tdfonthead" width="50%"><img src="../../images/Search.gif" onclick="searchStringInTA(document.getElementsByName('srchstr')[0].value,document.getElementsByName('content')[0]);"></td>
	</tr></table></td></tr>
	<tr>
		<td class="header"></td>
	</tr>
	<tr>
		<td align="center"><img src="../../images/Cancel.gif" onClick="javascript:window.close();"></td>
	</tr>
</table>

</FORM>
</BODY>
</HTML>
