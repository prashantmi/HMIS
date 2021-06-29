
<jsp:useBean id="meta" class="usermgmt.masters.gblMetaTableMstBean" scope="request" />
<jsp:useBean id="global" class="usermgmt.FuncLib" scope="request" />
<%@taglib uri="/WEB-INF/otTags.tld" prefix="otTags"%>
<%

String hmode = "";
String title = "View";
String html = "";
	
hmode = request.getParameter("hmode");
if(hmode != null)
	hmode = request.getParameter("hmode");

if(hmode.equals("SAVE"))
	title="Add";
if(hmode.equals("UPDATE"))
	title = "Modify";

%>
<html>
<head>
<title>MetaTable Type Master</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<script language="JavaScript" src="../js/functionality.js" type="text/JavaScript"></script>
<style type="text/css">@import url(../../css/calendar-blue2.css);</style> 
<script language="JavaScript" src="../js/calendar.js"></script>
<script language="JavaScript" src="../js/Validation.js" type="text/JavaScript"></script>
<script>

	function focusOnLoad()
	{
	document.forms[0].moduleName.focus();	
	}
	
	
window.history.forward() ;

function assignMode1(e,form1,mode)
{

if(e.type=="click"||e.keyCode==13)
  {
	
	if(document.form1.moduleName.value=="")
	   	alert("Please Enter the Module Name");
		else
	   {		
		document.form1.hmode.value = mode;			
		document.form1.submit();
	   }
	
  }	
}

function resetForm(e,form1,mode)
{
	if(e.type=="click"||e.keyCode==13)
	{
		if(mode == "RESET")
			document.form1.reset();
		else
		{
			document.form1.hmode.value = mode;			
			document.form1.submit();
		}	
	}	
}

function submitpage2(e,form1,mode)
{
if(e.type=="click" || e.keyCode==13)
					{
    document.forms[0].hmode.value=mode;
	document.forms[0].submit();
                    }

}

</script>
<link href="../../css/hisStyle.css" rel="stylesheet" type="text/css">

</head>

<body background="../../images/cdac1.psd.gif" width="100%" topmargin="0" onload="focusOnLoad()">
<form name="form1" method="post" action="gblMetaTable_cntMst.jsp">

<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td>
		<div align="center">
		<p>&nbsp;</p>
		<table width="100%" border="0" cellspacing="1" cellpadding="0">
			<tr>
				<td colspan=2><font color='#000066' size='-1' face='Geneva, Arial, Helvetica, sans-serif'>
			<strong>Module Master >> <%=title%> Page</strong></font></td>
			</tr>
		<%
 		if(hmode.equals("UPDATE"))
	    {
        System.out.println("in modify");
	  
    %>

			<tr>
				<td class="addheader" colspan=2 align="right">Status <select tabindex="-1"
					name="isvalid">
					<option value="1" <%=meta.getCombo1().equals("1")?"selected":""%>>Active</option>
					<option value="2" <%=meta.getCombo1().equals("2")?"selected":""%>>InActive</option>
				</select></td>
			</tr>
			<%     
 }
  else
    {
%>
			<tr>
				<td class="addheader" colspan=2 align="right">Status<select tabindex="-1"
					name="isvalid">
					<option value="1">Active</option>
					<option value="2">InActive</option>
				</select></td>
			</tr>
			<%}
%>


			<tr>
				<td width="50%" height="20" bgcolor="#F5F3F3">
				<div align="right"><font size="2"
					face="Verdana, Arial, Helvetica, sans-serif"><font color='red'>*</font> Module Name </font></div>
				</td>
				<td width="50%" bgcolor="#F5F3F3">
				<input name="moduleName"type="text" value="<%=meta.getModuleName()%>"    tabindex=0  onkeypress = " return checkInput(this,7,50,event);">
				</textbox>
				</td>
			<tr>

			</tr>
			<tr bgcolor="#dee1f2">
				<td height="17" colspan="2">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;
				&nbsp;&nbsp;</td>
			</tr>
		</table>
		</div>
		</td>
	</tr>
	<tr>

		<td colspan=2>
		<div align="center"><a style=cursor:hand><img
			src="../../images/Save.gif" class="link" tabindex=0
			onClick='assignMode1(event,form1,"<%=hmode%>")'
			onkeyPress='assignMode1(event,form1,"<%=hmode%>")'></a> 
			<%if(hmode.equals("SAVE"))
			{%>
				<a style=cursor:hand><img src="../../images/Clear.gif" class="link"  tabindex=0
			width="60" height="20" onClick="resetForm(event,form1,'RESET')"     onkeypress="resetForm(event,form1,'RESET')"></a>
			
			<%}%>
			 <a style=cursor:hand><img src="../../images/Cancel.gif" class="link"  tabindex=0
			width="60" height="20" onClick='submitpage2(event,form1,"DEFAULT");' onkeypress='submitpage2(event,form1,"DEFAULT");'></a>&nbsp;&nbsp;&nbsp;
		</td>
	</tr>
</table>
<input type="hidden" name="hmode"> <input type="hidden" name="moduleId" value='<%=meta.getModuleId()%>'>
	<input type="hidden" name="sysdate" value="<%=global.getSysdate()%>"/>
	</form>
</body>
</html>
