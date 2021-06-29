	
	<jsp:useBean id="bean" class="usermgmt.masters.UmgmtSeatMenuMstBean" scope="request"/>
	<html>
	<title>Seat Menu</title>
	<link href="../../css/hisStyle.css" rel="stylesheet" type="text/css">
	<script language="JavaScript" type="text/JavaScript" src="../js/functionality.js"></script>
	
	<script>
	function submitaddpage(mode)
	{
		if(document.form1.module.value=="0")
			alert("Select Module");
		else if(document.form1.role.value=="0")
			alert("Select Role");
		else if(document.form1.newSeat.value=="0")
			alert("Select new Seat");
		else if(document.form1.assignSeat.value=="0")
			alert("Select Assign Seat");
		else
		{
			var mList=0;
			for(var i=0;i<document.forms[0].menuList.length;i++)
   			{
   				var j=document.all.item("menuList")[i].value;
				mList +=j;
			}
			document.form1.mList1.value=mList;
			document.forms[0].hmode.value = mode;
			document.forms[0].submit();
		}
	}
	</script>
	
	<body background="../../images/cdac1.psd.gif" width="100%" topmargin="0">
	<form name="form1" method="post" action="umgmtSeatMenu_cntMst.jsp">
	
	<p>
	<table width="80%" align="center" >
		<tr>
			<td colspan=2 class="header"><font color='#000066' size='-1' face='Geneva, Arial, Helvetica, sans-serif'>
			<strong>Seat Menu >> </strong></font></td>
		</tr>
		<tr>
			<td class="adddatalabel"><div align="left">Module</div></td>
			<td class="adddatavalue" align ='left' onChange="submitpage('ADDPAGE')"><div align="left">
			<select name='module'>
			<option value='0'>Select Module</option>
			<%=bean.getModuleName()%></select></div></td>
		</tr>
		<tr>
			<td class="adddatalabel">Role</td><td class="adddatavalue" align ='left'>
			<select name='role'>
			<option value='0'>Select Role</option>
			<%=bean.getRoleName()%></select></td>
		</tr>
		
		<tr>
			<td class="adddatalabel">New Seat</td>
			<td class="adddatavalue" align ='left'><select name='newSeat'>
			<option value='0'>Select New Seat</option>
			<%=bean.getNewSeatName()%></select></td>
		</tr>
		
		<tr>
			<td class="adddatalabel">Assigned Seat</td>
			<td class="adddatavalue" align ='left' onChange="submitpage('ADDPAGE')">
			<select name='assignSeat'>
			<option value='0'> Select Assign Seat</option>
			<%=bean.getAssignSeatName()%></select></td> 
		</tr>
	</table>
		
	<p>
	<table align=center bgcolor="#FFCCCC" >
		<tr>
			<td><select name="menuList" size="10" >
			<%=bean.getMenuListWise()%></select></td></tr>
	</table>
	
	<table align=center>
		<tr>
			<td>
			<a style=cursor:hand>
			<img src="../../images/Save.gif" class="link" tabindex=0 onClick='submitaddpage("SAVE")'onkeyPress='submitaddpage("SAVE")'></a> 
			</td>
		</tr>
	</table>
	
	<input type="hidden" name="mList1">
	<input type="hidden" name="hmode">
	</body>
	</html>