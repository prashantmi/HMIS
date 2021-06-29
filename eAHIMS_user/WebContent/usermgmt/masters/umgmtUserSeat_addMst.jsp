
<jsp:useBean id="user" class="usermgmt.masters.UmgmtUserSeatMstBean"
	scope="request" />
<%@taglib uri="/WEB-INF/otTags.tld" prefix="otTags"%>
<%
	String result = request.getParameter("emp");
	System.out.println("result..." + result);
	if (result == null) {
		result = "1";
	} else if (result.equals("")) {
		result = "1";
	}
	String type = null;
	String hmode = "";
	String title = "";
	hmode = request.getParameter("hmode");
	System.out.println("hmode..." + hmode);

	if (hmode.equals("SAVE")) {
		title = "Add";
	}
	if (hmode.equals("UPDATE")) {
		title = "Modify";

	}
%>

<html>
<head>
<title>User-Seat Master</title>

<script language="JavaScript" src="../js/Validation.js"
	type="text/JavaScript"></script>
<script language="JavaScript" src="../js/functionality.js"
	type="text/JavaScript"></script>
<script language="JavaScript" src="../js/util.js" type="text/JavaScript"></script>
<style type="text/css">
@import url(../../css/calendar-blue2.css);
</style>
<script language="JavaScript" src="../js/calendar.js"></script>

<script>
	
	function detail()
	  	{
			ajaxFunction("umgmtUserSeatResponse.jsp?hmode=DET&uid="+document.forms[0].userName.value,"1")
		}
		
	function detail1()
	  	{
			ajaxFunction("umgmtUserSeatResponse.jsp?hmode=SEAT&gid="+document.forms[0].groupName.value,"2")
		}
	
	function getAjaxResponse(res,mode)
	{
		if(mode=="1")
		{
			//alert(res);			  
			var i;
		  	var sp = "^";
			var temp = res.split(sp);
		 	for(i =0; i < temp.length ; i++)
		   // alert(temp[i]);
		    document.forms[0].ufullName.value=temp[0];
		    document.forms[0].designation.value=temp[1];
		    document.forms[0].effDate.value=temp[2];
		     document.forms[0].expDate.value=temp[3];
	    }
	    
	    if(mode=="2")
		{
			document.getElementById("seatAssociateId").innerHTML="<select name='userSeatNo'>"+res+"</select>";
		}
	}
	  	
	function assignMode1(e,form1,mode)
	{
		var dateCheck; 
		var dateCheck1;
		
		dateCheck		=	compareTwodates ("",document.forms[0].effDate.value);
		dateCheck1		=	compareTwodates(document.forms[0].effDate.value,document.forms[0].expDate.value);
			
		
		if(document.form1.effDate.value=="")	{
		
			alert("Please Enter The Effective Date");
			document.form1.effDate.focus();
		}
		else if(document.form1.expDate.value==""){
			alert("Please Enter The Expiry Date");
			document.form1.expDate.focus();
		}
		else if(dateCheck==false&& mode=='SAVE'){
			alert("Effective Date should be Greater than or equal to Current Date");
		}
		else if(dateCheck1==false){
			alert("Expiry Date Must be Greater Than Effective Date");
		}
		else
		{
			document.form1.hmode.value = mode;			
			document.form1.submit();
		}
		return;
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
	function fun()
	{
		document.form1.hmode.value="ADD";
		document.form1.submit();
	}
	

	</script>
<link href="../../css/hisStyle.css" rel="stylesheet" type="text/css">
</head>
<body background="../../images/cdac1.psd.gif" width="100%" topmargin="0">
<form name="form1" method="post" action="umgmtUserSeat_cntMst.jsp">
<table width="100%" align="center" border="0" cellspacing="1"
	cellpadding="0">
	<tr>
		<td colspan=2><font color='#000066' size='-1'
			face='Geneva, Arial, Helvetica, sans-serif'> <strong>User-Seat
		Master >> <%=title%> Page</strong></font></td>
	</tr>
	<%
	if (hmode.equals("UPDATE")) {
	%><tr>
		<td class="addheader" colspan=2 align="right">Status <select
			name="isValid">
			<option value="1" <%=user.getCombo1().equals("1")?"selected":""%>>Active</option>
			<option value="2" <%=user.getCombo1().equals("2")?"selected":""%>>InActive</option>
		</select></td>
	</tr>
	<%
	} else {
	%>
	<tr>
		<td class="addheader" colspan=2 align="right">Status<select
			name="isValid">
			<option value="1">Active</option>
			<option value="2">InActive</option>
		</select></td>
	</tr>
	<%
			String sel1 = "";
			String sel2 = "";
			if (result.equals("1")) {
				sel1 = "checked";
			} else if (result.equals("2")) {
				sel2 = "checked";
			}
		}
	%>

	<%
	if (hmode.equals("SAVE")) {
	%>
	<tr>
		<td class="adddatalabel">
		<div align="right">User Id</div>
		</td>
		<td class="adddatavalue">
		<div align="left"><select name="userName" onChange="detail();">
			<option value="0">Select User ID</option>
			<%=user.userOptions()%></select></div>
		</td>
	</tr>
	<%
		}
		if (hmode.equals("UPDATE")) {
	%>
	<tr>
		<td class="adddatalabel">
		<div align="right">User Id</div>
		</td>
		<td class="adddatavalue">
		<div align="left"><input type="text" name="userName"
			value="<%=user.getUserName()%>" readonly></div>
		</td>
	</tr>
	<%
	}
	%>
	<tr>
		<td class="adddatalabel">
		<div align="right">User/Employee Name</div>
		</td>
		<td class="adddatavalue">
		<div align="left"><input type="text" name="ufullName" size='30'
			value="<%=user.getUfullName()%>" readonly
			onkeypress="return checkInput(this,5,50,event);"></div>
		</td>
	</tr>
	<tr>
		<td class="adddatalabel">
		<div align="right">Designation</div>
		</td>
		<td class="adddatavalue">
		<div align="left"><input type="text" name="designation"
			value="<%=user.getDesignation()%>" readonly
			onkeypress="return checkInput(this,5,50,event);"></div>
		</td>
	</tr>

	<tr>
		<td class="adddatalabel">
		<div align="right">Group Name</div>
		</td>
		<td class="adddatavalue">
		<div align="left"><select name="groupName" onChange="detail1();">
			<option value="0">Select Group Name</option>
			<%=user.groupOptions()%></select></div>
		</td>
	</tr>

	<tr>
		<td class="adddatalabel">
		<div align="right"><font color='red'>*</font>Seat Associates</div>
		</td>
		<td class="adddatavalue">
		<div align="left" id="seatAssociateId"><select name="userSeatNo">
			<option value="0">Select Seat</option>
			<%=user.seatOptions()%></select></div>
		</td>
	</tr>

	<tr>
		<td class="adddatalabel" width="50%">
		<div align="right"><font color='red'>*</font>Effective Date</div>
		</td>
		<%
		if (hmode.equals("SAVE")) {
		%>
		<td class="adddatalabel" width="50%"><otTags:date name='effDate'
			dateFormate="%d-%b-%Y" value='<%=user.getEffDate()%>' /></td>
		<%
		} else if (hmode.equals("UPDATE")) {
		%>
		<td class="adddatalabel"><input type="text" name="effDate"
			value='<%=user.getEffDate()%>' readonly></td>
		<%
		}
		%>
	</tr>
	<tr>
		<td class="adddatalabel" width="50%">
		<div align="right"><font color='red'>*</font>Expiry Date</div>
		</td>
		<%
		if (hmode.equals("SAVE")) {
		%>
		<td class="adddatalabel" width="50%"><otTags:date
			name='<%="expDate"%>' dateFormate="%d-%b-%Y"
			value="<%=user.getExpDate()%>" /></td>
		<%
		} else if (hmode.equals("UPDATE")) {
		%>
		<td class="adddatalabel"><input type="text" name="expDate"
			value='<%=user.getExpDate()%>' readonly></td>
		<%
		}
		%>
	</tr>

	<tr>
		<td class="addheader" colspan=2>&nbsp;</td>
	</tr>

	<tr>
		<td colspan=2>
		<div align="center"><a style="cursor: hand"><img
			src="../../images/Save.gif" class="link" tabindex=0
			onClick='return assignMode1(event,form1,"<%=hmode%>");'
			onkeyPress='return assignMode1(event,form1,"<%=hmode%>");'></a> <%
 if (hmode.equals("SAVE")) {
 %> <a style="cursor: hand"><img src="../../images/Clear.gif"
			class="link" tabindex=0 onClick='document.form1.reset();'
			onkeyPress='resetForm(event,form1,"RESET")'></a> <%
 }
 %> <a style="cursor: hand"><img src="../../images/Cancel.gif"
			class="link" tabindex=0 onClick='resetForm(event,form1,"DEFAULT")'
			onkeyPress='resetForm(event,form1,"DEFAULT")'></a></div>
		</td>
	</tr>
</table>
<input type="hidden" name="userId" value="<%=user.getUserId()%>">
<input type="hidden" name="hmode"></form>
</body>
</html>