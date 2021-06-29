<jsp:useBean id="pageObj" class="usermgmt.masters.Page" scope="request" />

<html>
<head>
<title>View</title>
<link href="../../css/hisStyle.css" rel="stylesheet" type="text/css">
<script type="text/javascript">
function submitpage2(e,form1,mode)
{
if(e.type=="click" || e.keyCode==13)
					{
    document.forms[0].hmode.value=mode;
	document.forms[0].submit();
                    }

}

</script>
<script language="JavaScript" src="../js/functionality.js"></script>
</head>
<!-- 
<body background="../../images/cdac1.psd.gif" width="100%" topmargin="0" > -->
<body  width="100%" topmargin="0" >
<form name="form1" method="post"
	action='<%=request.getParameter("nextpage")%>'><%-- <!--<%@ include file = "/HisGlobal/header.jsp" %>--> --%>
	<table width="97%" align="center" class="topmargin">
<tr><td>
<%=pageObj.getHtmlStr()%> <input type="hidden" name="hmode"> <input
	type="hidden" name="combo1" value='<%=request.getParameter("combo1")%>'>
<input type="hidden" name="combo2"
	value='<%=request.getParameter("combo2")%>'> <input type="hidden"
	name="combo3" value='<%=request.getParameter("combo3")%>'> <input
	type="hidden" name="cboSearch"
	value='<%=request.getParameter("cboSearch")%>'> <input type="hidden"
	name="txtSearch" value='<%=request.getParameter("txtSearch")%>'>
</td>
<td>

</td>
</tr>
</table>
	</form>
</body>
</html>
