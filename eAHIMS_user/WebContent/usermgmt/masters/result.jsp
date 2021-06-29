

<%
	
String sts = "";
	String pageName = "";
	
	sts = request.getParameter("status");
	
	pageName = request.getParameter("Name");
	if (pageName == null)
		pageName = "Result >>";
%>

<html>
<head>
<title>Result</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<style type="text/css">@import url(../../css/calendar-blue2.css);</style>
</head>

<script type="text/javascript">

window.history.forward() ;

function submitpage(e)
{
if(e.type=="click" || e.keyCode==13)
{
document.forms[0].submit();
}

}
</script>

<!-- 
<body background="../../images/cdac1.psd.gif" > -->
<body  >
<link href="../../css/hisStyle.css" rel="stylesheet" type="text/css">
<form class="formbg" name="form1" method="post" 
	action='<%=request.getParameter("nextpage")%>'>


<table align="center" width="100%" border="0" cellspacing="0"
	cellpadding="0">
	<tr class="ShadedSubTitleTagImage">
		<td><%=pageName%></td>
	</tr>
</table>

<table align="center" width="100%" border="0" cellpadding="1"
	cellspacing="1">
	<tr class="LABEL">
		<td>&nbsp;</td>
	</tr>

	<tr class="LABEL">
		<td height="24" width="51%" align="center"><%=sts%></td>
	</tr>
</table>

<table align="center" width="100%" border="0" cellspacing="0"
	cellpadding="0">
	<tr class="LABEL">
		<td>&nbsp;</td>
	</tr>
	<tr>
		<td><a style=cursor:pointer; class="button" tabindex=0 onClick='submitpage(event)' onkeypress='submitpage(event)' ><span class="cancel"> Cancel</span></a></td>
	</tr>
</table>

<input type="hidden" name="hmode"  value='<%=request.getParameter("mode")%>'></form>
</body>
</html>



