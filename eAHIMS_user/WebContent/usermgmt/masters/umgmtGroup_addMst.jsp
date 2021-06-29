	
	<jsp:useBean id="group" class="usermgmt.masters.UmgmtGroupMstBean" scope="request" />
	<jsp:useBean id="global" class="usermgmt.FuncLib" scope="request" />
	
	<%@taglib uri="/WEB-INF/otTags.tld" prefix="otTags"%>
	<%@taglib uri="/WEB-INF/anti_csrf.tld" prefix="anticsrf"%>
	
	<%
	System.out.println("Sysdate ----"+global.getSysdate());
	
    System.out.println("Status"+group.getStatus());
	String hmode = "";
	String title = "";
	String userSeatId	=	"";
	//group.setHospitalcode(request.getParameter("HOSPITALCODE"));
	hmode = request.getParameter("hmode");
	
	if(hmode.equals("SAVE"))
	{
		title="Add";
	}
	if(hmode.equals("UPDATE"))
	{
		title = "Modify";
	}
	%>
	
	<html>
	<head>
	<title>Group Master</title>
	<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
	<script language="JavaScript" src="../js/Validation.js" type="text/JavaScript"></script>
	<script language="JavaScript" src="../js/functionality.js" type="text/JavaScript"></script>
	<style type="text/css">@import url(../../css/calendar-blue2.css);</style>
	<script language="JavaScript" src="../js/calendar.js"></script>
	
	
	<script>
	function focusOnLoad()
	{
	document.forms[0].group_name.focus();	
	}
		
		window.history.forward() ;	
		
	function assignMode1(e,form1,mode)
{
	if(e.type=="click"||e.keyCode==13)
  {
	var check=true;
	var dateCheck=compareTwodates(document.forms[0].sysdate.value,document.forms[0].effect_date.value);
	
	if(document.forms[0].group_name.value=="")
		{
			alert("Please Enter the Group Name");
		    check=false;
		     document.forms[0].group_name.focus();
		}	
		else
	 if(document.forms[0].effect_date.value=="")
		{
			alert("Please Enter Effective Date");
			check=false;
		    document.forms[0].effect_date.focus();
		}	
		else
	 if(mode=='SAVE' && dateCheck==false)
		{
			alert("Effective Date should be Greater than or equal to Current Date");
			check=false;
		    document.forms[0].effect_date.focus();
		}
		else
	if(check==true)	
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

	function checkFirstCharacter(e)
	{
		
		var code=e.charCode;
		var i=document.forms[0].group_name.value.length;
		if(i==0&& code>=49&& code<=57)
			return false;
		else
			return true;
	}
		
		
	
</script>
	
	<link href="../../css/hisStyle.css" rel="stylesheet" type="text/css">
	</head>
	
<!-- 	<body background="../../images/MainPgNewNew.png" width="100%" topmargin="0" onload="focusOnLoad()"> -->
	<body  width="100%" topmargin="0" onload="focusOnLoad()">
	<form class="topmargin" name="form1" method="post" action="umgmtGroup_cntMst.jsp">
	
	<table class="formbg" width="97%" align="center"   cellspacing="0" cellpadding="0">
		<tr class="ShadedSubTitleTagImage">
			<td   colspan=2>
			<strong>Group Master >> <%=title%> Page</strong></td>
		</tr>
		
	
		<%
	      if(hmode.equals("UPDATE"))
		    {%>
	
		<!--
		<tr><td colspan="2"><div align="right"></div></td></tr>
	      <tr >
			<td class= "addHeaderNew" colspan=2  align="right" >Status <select name="isvalid" tabindex="-1">
				<option value="1" <%=group.getCombo1().equals("1")?"selected":""%>>Active</option>
				<option value="2" <%=group.getCombo1().equals("2")?"selected":""%>>InActive</option>
			</select></td>
		</tr>
		
		 <tr>
			<td class="addHeaderNew" colspan=2  align="right" ></td>
			</tr>-->
			
		<tr>
			<td class="LABEL">
			<div align="right"><font color='red'>*</font>Group Name</div>
			</td>
			<td class="CONTROL">
			<div align="left"><input type="text" name="group_name" tabindex="1"
				value="<%=group.getGroup_name()%>" maxlength="20" onkeypress = "return checkInput(this,3,20,event);"  ></div>
			</td></tr>
		<%     
	 }
	  else
	    {
	%>
		<!-- <tr>
			<td class="addHeaderNew" colspan=2  align="right" >Status<select name="isvalid" tabindex="-1">
				<option value="1">Active</option>
				<option value="2">InActive</option>
			</select></td>
		</tr>
		
		 <tr>
			<td class="addHeaderNew" colspan=2  align="right" ></td>
			</tr> -->
		    <tr>
			<td class="LABEL">
			<div align="right"><font color='red'>*</font>Group Name</div>
			</td>
			<td class="CONTROL">
			<div align="left">
		
			<input type="text" tabindex="1" name="group_name" value="<%=group.getGroup_name()%>" maxlength="20" onkeypress = "return checkInput(this,3,20,event);"   >
			</div>
			</td>
	
		</tr>
		<%}
	%>
	
		
	
		<tr>
	
			<td class="LABEL" width="50%">
			<div align="right"><font color='red'>*</font>Effective Date</div>
			</td>
			<%if(hmode.equals("SAVE")) 
			{%>
				<td class="CONTROL" width="50%"><otTags:date
				name='<%="effect_date"%>' dateFormate="%d-%b-%Y"
				value='<%=group.getSysdate()%>' /></td>
			<%}
		
		else if(hmode.equals("UPDATE"))
		{%>
		<td class="CONTROL"><input typ="text" tabindex="2" name="effect_date" value='<%=group.getEffect_date()%>'readonly></td>
		<% }%>
		</tr>
		<tr>
		<!--			<td class="addHeaderNew"   colspan=2></td>-->
		<td><div align="center"><%=group.getStatus() %></td>
		</tr>
		</table>
		
		<table width="97%" cellspacing="0" cellpadding="0" align="center">
		<tr class="FOOTER">
		 <td colspan="2"></td>
		 </tr>
		<tr>
			<td colspan=2><div class="control_button2" align="center"><a style=cursor:pointer
			class="button" tabindex=0 onClick='assignMode1(event,form1,"<%=hmode%>")'
				onkeyPress='assignMode1(event,form1,"<%=hmode%>")'><span class="save"> Save</span></a> 
			<%if(hmode.equals("SAVE")) 
			{%>
			
			<a style=cursor:pointer class="button" tabindex=0 
			onClick='resetForm(event,form1,"RESET")' onkeyPress='resetForm(event,form1,"RESET")'><span class="clear"> Clear</span></a>
			<% }%>	 
				 <a style=cursor:pointer  class="button" tabindex=0
				onClick='resetForm(event,form1,"DEFAULT")'
				onkeyPress='resetForm(event,form1,"DEFAULT")'><span class="cancel"> Cancel</span></a></div>
			</td>
		</tr>
	
	</table>
	
	<!-- <input type="hidden" name="hospitalcode" value="<%--=group.getHospitalcode() --%>">-->
	<input type="hidden" name="group_code" value="<%=group.getGroup_code() %>"> 
	<input type="hidden" name="hmode">
	<input type="hidden" name="sysdate" value="<%=global.getSysdate()%>" />
	
	<anticsrf:csrftoken/>
	
	</FORM>
	</BODY>
	</HTML>
