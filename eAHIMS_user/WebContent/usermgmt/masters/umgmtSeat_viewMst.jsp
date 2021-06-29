<jsp:useBean id="seat" class="usermgmt.masters.umgmtSeatMasterBean" scope="request" />
<%@taglib uri="/WEB-INF/otTags.tld" prefix="otTags"%>

<html>
<head>
<title>Seat Master</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<script language="JavaScript" src="../js/Validation.js" type="text/JavaScript"></script>
<script language="JavaScript" src="../js/functionality.js" type="text/JavaScript"></script>
<style type="text/css">@import url(../../css/calendar-blue2.css);</style>
<script language="JavaScript" src="../js/calendar.js"></script>
<script language="JavaScript" src="../js/multirow.js"></script>
<link href="../../css/hisStyle.css" rel="stylesheet" type="text/css">
<script type="text/javascript">
	function resetForm(event,formObj,hmode)
	{
		if(event.type=="click" || event.keyCode==13)
		{
			document.form1.hmode.value = hmode;
			document.form1.submit();
		
		}
	}
</script>

</head>
<!-- 
<body background="../../images/cdac1.psd.gif" topmargin="0"> -->
<body >
<form name="form1" method="post" action="umgmtSeatMst_cnt.jsp">

<%
String hmode = "";
String title = "";
String userSeatId	=	"";

hmode = request.getParameter("hmode");
if(hmode==null)
	hmode="SAVE";

if(hmode.equals("SAVE"))
{
	title="Add";
	System.out.println("I m inside add");	
}
if(hmode.equals("UPDATE"))
{
	title = "Modify";
	System.out.println("I m inside modify");
}
%>
<table class="topmargin" width="100%" align="center" cellspacing="1px">
	<tr>
		<td class="ShadedSubTitleTagImage" colspan=2>
		<strong>Seat Master >> View Page</strong></td>
	</tr>
</table>

<table class="formbg" width="97%" align="center" cellspacing="1px">

	<tr>

		<td class="label" width="50%">
		<div align="right">Seat Name</div>
		</td>
		<td class="control" width="50%">
		<div align="left">
		<input type="text" name="seatName" value="<%=seat.getSeatName()%>" tabindex=0 onkeypress = "return checkInput(this,3,20,event);" readonly="readonly">
		<!-- replace checkAlphaNumeric(this,3,20,event); -->
		</div>
		</td>

	</tr>

	<tr>

		<td class="label" width="50%">
		<div align="right">Seat Description</div>
		</td>
		<td class="control" width="50%">
		<div align="left"><input type="text" name="seatDesc" value="<%=seat.getSeatDesc()%>" tabindex=0 onkeypress ="return checkInput(this,5,100,event);" readonly="readonly"></div>
		</td>
  
	</tr>
	<tr>
		<td class="label"><div align="right">Group Assigned</div></td>
		<td class="control"><div align="left"><input type="text" value="<%=seat.getGroupName()%>" tabindex=0  readonly="readonly"></div></td>
	</tr>
	<tr>
		<td class="label"><div align="right">Associated IP</div></td>
		<td class="control"><div align="left"><input type="text" value="<%=seat.getIpAddress()%>" tabindex=0  readonly="readonly"></div></td>
	</tr>
	</table>
	<div id="id1">
		<%=seat.getStrHtlmlModi() %>
	</div>
	<table class="formbg" cellspacing="1px" align="center" width="97%">
		<tr>
			<td class="label" width="50%">
				<div align="right">Effective Date</div>
			</td>
			<td class="control" width="50%">
				<input type="text" name="effectiveDate" value="<%=seat.getEffectDate()%>" tabindex=0  readonly="readonly">
			</td>
	</tr>
		
	</table>
	
	
	
	<table width="97%" cellspacing="0" cellpadding="0" align="center">
	<tr class="FOOTER">

		<td colspan=2>
	</tr>
	
	<tr>
		<td colspan=2>
		<div align="center" class="control_button"> 
				<a style=cursor:pointer; class="button" tabindex=0 onClick='resetForm(event,form1,"DEFAULT")'	onkeyPress='resetForm(event,form1,"DEFAULT")'><span class="cancel">Cancel</span></a>
		</div>
		</td>

	</tr>
</table>
 <input type="hidden" name="hmode" value="<%=hmode%>">
 <input type="hidden" name="prevGroup" value="<%=seat.getGroup()%>">
 <input type="hidden" name="userSeatId" 	value="<%=seat.getUserSeatId()%>">
 <input type="hidden" name="seatId"value="<%=seat.getSeatId()%>">
 <input type="hidden" name="multiModi" value="<%=seat.getStrHtlmlModi()%>">
 
 </FORM>
 <jsp:include page="umgmtSeatMst_multirow.jsp"></jsp:include>	
</BODY>
</HTML>
