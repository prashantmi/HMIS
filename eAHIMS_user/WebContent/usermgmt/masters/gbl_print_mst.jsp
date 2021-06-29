
<jsp:useBean id="pageObj" class="usermgmt.masters.Page" scope="request" />

<script language="JavaScript" type="text/JavaScript"
	src="../js/functionality.js"></script>
<script>

function callMe1(e)
{
if(e.type=="click" || e.keyCode==13)
 {
	changeState('id1','hidden');
	window.print();
	changeState('id1','visible');
 }
}
function submitpage1(e,hmode)
{
if(e.type=="click" || e.keyCode==13)
{
document.forms[0].hmode.value=hmode;
document.forms[0].submit();
}

}

 
</script>

<html>
<head>
<title>Master Report</title>
<style type="text/css">@import url(../../css/calendar-blue2.css);</style>
</head>
<!-- 
<body onLoad='checkBrowser();' background="../../images/cdac1.psd.gif"> -->
<body onLoad='checkBrowser();' >
<link href="../../css/hisStyle.css" rel="stylesheet" type="text/css">
<form name='form1' METHOD='POST'
	action='<%=request.getParameter("nextpage")%>'>
	<%out.print(pageObj.getHtmlStr());%>
<input type="hidden" name="hmode"> <input type="hidden" name="combo1"
	value='<%=request.getParameter("combo1")%>'> <input type="hidden"
	name="combo2" value='<%=request.getParameter("combo2")%>'> <input
	type="hidden" name="combo3" value='<%=request.getParameter("combo3")%>'>
<input type="hidden" name="cboSearch"
	value='<%=request.getParameter("cboSearch")%>'> <input type="hidden"
	name="txtSearch" value='<%=request.getParameter("txtSearch")%>'></form>
</body>

</html>
