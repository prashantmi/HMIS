
<%@taglib uri="/WEB-INF/anti_csrf.tld" prefix="anticsrf"%>
<jsp:useBean id="beanpage" class="usermgmt.masters.UmgmtRoleMstBean"
	scope="request" />

<html>
<head>
<title>welcome to umgmtrole_modmst.jsp</title>
<script language="JavaScript" type="text/JavaScript"
	src="../js/Validation.js"></script>
<script language="JavaScript" type="text/JavaScript"
	src="../js/functionality.js"></script>
<script language="JavaScript" src="../js/funclistbox.js"></script>

<script language=javascript>
window.history.foward();


function focusOnLoad()
	{

	document.form1.role_name.focus();	
	}
	
	
function assignMode12(e)
{
if(e.type=="click" || e.keyCode==13)
  {
	var rolename="";
	rolename = myTrim(document.form1.role_name.value);		
	document.form1.role_name.value = rolename;
	
	if(document.form1.role_name.value=="")
	  {
	alert("Role Name is BLANK");
	document.form1.role_name.focus();
	  }
	else
	{
		document.form1.hmode.value="UPDATE";
		document.form1.submit();
	}
  }		
}

function submitpage2(e,form1,mode)
{
 if(e.type=="click" || e.keyCode==13)
  {
	       document.form1.hmode.value=mode;
		   document.form1.submit();
  }		   
}	
</script>
<link href="../../css/hisStyle.css" rel="stylesheet" type="text/css">
</head>
<!-- 
<body background="../../images/cdac1.psd.gif" width="100%" topmargin="0" onload="focusOnLoad()"> -->
<body  width="100%" topmargin="0" onload="focusOnLoad()">
<% String title = "Modify"; %>

<form class="topmargin" name='form1' method='post'>

<table width="97%" border="0" cellspacing="0" cellpadding="0" align="center">
	<tr>
		<td>
		<div align="center">
		
			<tr>
				<td colspan=2 class="ShadedSubTitleTagImage">
				<strong>Role Master >> <%=title%> Page</strong></td>
			</tr>
		
  		<!--  
  		<tr bgcolor='#dee1f2'><td class="addHeaderNew" colspan=2 align="right">Status <select name="isvalid">
			<option value="1"<%=beanpage.getCombo1().equals("1")?"selected":""%>>Active</option>
			<option value="2"<%=beanpage.getCombo1().equals("2")?"selected":""%>>InActive</option>
	</select></td></tr>
	-->
	
	</table>
	
	<table  class="formbg" width="97%" border="0" cellspacing="2" cellpadding="0" align="center">	
		
			<tr>
				<td class="label" width="50%">
				<div align="right"><font size="2"
					face="Verdana, Arial, Helvetica, sans-serif"><font color='red'>*</font>Role Name </font></div>
				</td>
				<td class="control" width="50%"><input name="role_name" type="text" 
					value="<%=beanpage.getRole_name()%>" onkeypress = "return checkInput(this,3,30,event);"></td>
			</tr>
			<tr>
				<td class="label" width="50%">
				<div align="right"><font size="2"
					face="Verdana, Arial, Helvetica, sans-serif"><font color='red'>*</font>Module Name </font></div>
				</td>
				<td class="control" width="50%"><input name="moduleName" type="text"
					 value="<%=beanpage.getModuleName()%>" readonly></td>
			</tr>
		<tr>
				<td class="label" width="50%">
				<div align="right"><font size="2"
					face="Verdana, Arial, Helvetica, sans-serif"><font color='red'>*</font>Effective Date </font></div>
				</td>
				<td class="control" width="50%"><input name="effect_date" type="text"
					 maxlength='30'
					value='<jsp:getProperty name="beanpage" property="effect_date"/>' readonly></td>
			</tr>
	</table>		
	



<table width="97%" cellspacing="0" cellpadding="0" align="center">
	<tr class="FOOTER">

		<td colspan=2>
	</tr>
	
	<tr>
		<td colspan=2>
				<div align="center" class="control_button2">	
					 <a style=cursor:hand class="button" tabindex=0 onClick='assignMode12(event);' onkeypress="assignMode12(event);"><span class="save">Save</span></a>
					 <a style=cursor:hand class="button" tabindex="0" onKeyPress=" submitpage2(event,form1,'DEFAULT');" onClick='submitpage2(event,form1,"DEFAULT");'> <span class="cancel">Cancel</span></a>
					</div>
				</td>
			</tr>
</table>
<anticsrf:csrftoken/>
<input type="hidden" name="hmode" value=""> <input type="hidden"
	name="role_id"
	value='<jsp:getProperty name="beanpage" property="role_id"/>'> <input
	type="hidden" name="module_id"
	value='<jsp:getProperty name="beanpage" property="module_id"/>'></form>
</body>
</html>
