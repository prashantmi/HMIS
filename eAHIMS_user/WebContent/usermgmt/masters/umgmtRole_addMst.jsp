

<%@ page language="java" import="java.sql.*"%>

<jsp:useBean id="beanpage" class="usermgmt.masters.UmgmtRoleMstBean" scope="request">
	<jsp:setProperty name="beanpage" property="*" />
</jsp:useBean>
<jsp:useBean id="global" class="usermgmt.FuncLib" scope="request" />
<%@taglib uri="/WEB-INF/otTags.tld" prefix="otTags"%>
<%@taglib uri="/WEB-INF/anti_csrf.tld" prefix="anticsrf"%>


<%
	
	String hmode = "";
	String title = "";
	String query = "";
	String hospitalCode = (String)session.getAttribute("HOSPITAL_CODE");
	hmode = request.getParameter("hmode");

	if(hmode == null)
		hmode ="SAVE";
		
	if(hmode.equals("SAVE"))
		title="Add";
	else
		title = "Modify";
		
	query  	= " SELECT GNUM_MODULE_ID, INITCAP(GSTR_MODULE_NAME) "+
			   " FROM GBLT_METATABLE_TYPE_MST M "+
			  // " where M.GNUM_HOSPITAL_CODE='100' and GBL_ISVALID=1 "+
			 // " where M.GNUM_HOSPITAL_CODE='"+hospitalCode+"' and GBL_ISVALID=1 "+
			  " where GBL_ISVALID=1 "+
			   " order by INITCAP(GSTR_MODULE_NAME)";
			
	 		System.out.println("Module Query----"+query);  
%>

<html>
<head>


<title>Role Master</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href="../../CSS/hisStyle.css" rel="stylesheet" type="text/css">
<script language="JavaScript" type="text/JavaScript" src="../js/Validation.js"></script>
<script language="JavaScript" type="text/JavaScript" src="../js/functionality.js"></script>
<script language="JavaScript" src="../js/funclistbox.js"></script>
<style type="text/css">@import url(../../css/calendar-blue2.css);</style>
<script language="JavaScript" src="../js/calendar.js"></script>

<script >

	
	
	
window.history.foward();

function focusOnLoad()
	{

	document.forms[0].role_name.focus();	
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
function assignMode1(e)
{
if(e.type=="click"||e.keyCode==13)
  {
	var mode = "<%=hmode%>";
	var rolename="";
	
	//rolename = myTrim(document.form1.role_name.value);		
	//document.form1.role_name.value = rolename;
	
if(document.form1.role_name.value=="")
	  {
	  	alert("Role Name is BLANK");
	  	document.form1.role_name.focus();
	  }	
	else
if(document.form1.module_id.value=="0")
     {
	alert("Select Module");
		document.form1.module_id.focus();
	}
	else 
if(document.form1.effect_date.value=="")
    {
	alert("Enter Effective Date");
	document.form1.effect_date.focus();
	}
	else 
if(mode=='SAVE')
		{
			var dateCheck=compareTwodates(document.forms[0].sysdate.value,document.forms[0].effect_date.value);
			if(dateCheck==false)
				alert("Effective Date should be Greater than or equal to Current Date");
			else
			{
				document.form1.hmode.value = mode;		
		 		document.form1.submit();	
		 	}
		}
	
	
	else
	{	
		
		submitpage(mode);
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

<form class="topmargin" name="form1" method="post" action="umgmtRole_cntMst.jsp">
<table class="" width="97%" border="0" cellspacing="0" cellpadding="0" align="center">
	<tr>
		<td>
		<div align="center">
		<tr class="ShadedSubTitleTagImage">
				<td colspan=2>
				<strong>Role Master >> <%=title%> Page</strong></td>
			</tr>
			
		</table>	
	
	<!--  
	<tr bgcolor="#dee1f2">
		<td class="addHeaderNew" colspan=2 align="right">Status<select name="isvalid" tabindex="-1">
			<option value="1">Active</option>
			<option value="2">InActive</option>
		</select></td>
	</tr>
	-->
	
	<table class="formbg" width="97%" border="0" cellspacing="2" cellpadding="0" align="center">
			<tr>
				<td class="LABEL" width="50%">
				<div align="right"><font size="2"
					face="Verdana, Arial, Helvetica, sans-serif"><font color='red'>*</font> Role Name </font></div>
				</td>
				<td class="CONTROL" width="50%"><input name="role_name" type="text" tabindex=0
					value='<jsp:getProperty name="beanpage" property="role_name"/>'   onkeypress ="return checkInput(this,3,30,event);" ></td>
			</tr>
			
			
			<tr>
				<td class="LABEL">
				<div align="right"><font size="2"
					face="Verdana, Arial, Helvetica, sans-serif"><font color='red'>*</font> Module Name </font></div>
				</td>
				<td class="CONTROL"><%=beanpage.getCombo("module_id",query,beanpage.getModule_id(),"",1)%></td>
			</tr>
						
			
			
			<tr>
				<!-- <td bgcolor="#F5F3F3">
				<div align="right"><font size="2"
					face="Verdana, Arial, Helvetica, sans-serif"><font color='red'>*</font> Role Type </font></div>
				</td>
				<td bgcolor="#F5F3F3"><select name="role_type">
					<%-- String str=beanpage.getRole_type();
				  String strSel="",strSel1="";
				  if(str==""){%>
					<option selected value="0">Select Type</option>
					<%}
				  else{
					if(str.equals("A")) 
					{
						strSel="Selected";
					}
					else
					strSel="";
					if(str.equals("D")) 
					{
						strSel1="Selected";
					}
					else
					strSel1="";
				  }
					--%>
					<option value="A" <%--=strSel--%>>Application</option>
					<option value="D" <%--=strSel1--%>>Database</option>
				</select></td>-->
			</tr>
			<tr>
					<td class="LABEL">
				<div align="right"><font size="2"
					face="Verdana, Arial, Helvetica, sans-serif"><font color='red'>*</font>Effective Date </font></div>
				</td>
					<td class="CONTROL"><otTags:date 
					name='<%="effect_date"%>' dateFormate="%d-%b-%Y"
					value="<%=beanpage.getSysDate()%>" /></td>
			</tr>
			<!--<tr >
				<td class="addHeaderNew" colspan=2>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;
				&nbsp;&nbsp;</td>
			</tr>
		--></table>
		
<table width="97%" cellspacing="0" cellpadding="0" align="center">
	<tr class="FOOTER">

		<td colspan=2>
		</tr>
		<tr>
				<td colspan=2>
				<div class="control_button2" align="center"><a style=cursor:pointer; class="button" 
					tabindex=0
					onKeyPress="assignMode1(event);"
					onClick='assignMode1(event);'><span class="save">Save</span></a> <% if(hmode.equals("SAVE"))
		 {%> <a style=cursor:pointer; class="button" tabindex=0
					onKeyPress="resetForm(event,form1,'RESET')"
					onClick="resetForm(event,form1,'RESET')"><span class="clear">Clear</span></a> <%}%> <a style=cursor:pointer;
					class="button" 
					tabindex=0
					onKeyPress="submitpage2(event,form1,'DEFAULT');"
					onClick='submitpage2(event,form1,"DEFAULT");'><span class="cancel">Cancel</span></a></div>
				</td>
			</tr>
		</table>
<anticsrf:csrftoken/>	
<input type="hidden" name="orole_name" 	value='<jsp:getProperty name="beanpage" property="role_name"/>'> 
	<input 	type="hidden" name="role_id" 	value='<jsp:getProperty name="beanpage" property="role_id"/>'> 
	<input	type="hidden" name="omodule_id" value='<jsp:getProperty name="beanpage" property="module_id"/>'> 
	<input	type="hidden" name="hmode" value="">
	 <input type="hidden" 	name="seat_id" value='<%=session.getAttribute("SEATID")%>'>
	 <input type="hidden" name="sysdate" value="<%=global.getSysdate()%>"/>

	
	</form>
</body>
</html>
