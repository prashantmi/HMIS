<%@page import="HisGlobal.Config"%>

<html>
<head>

<title>C-DAC Admin Console</title>
<style type="text/css">
	.white{
		background-Color:#EAEAEA; font:  11px tahoma,verdana,sans-serif; color: #215dc6; width: 185px;
				  height: 15px;
	}
	.lightOrange{
		  font: bold 11px  Arial, Helvetica, sans-serif;  width: 185px;color:  #800080;
				  height: 15px;
	}
	.grey{
		font:  11px tahoma,verdana,sans-serif;
				  color: #FFEBD5;
	}
	.orange{
		  font: bold 11px tahoma,verdana,sans-serif; color:  #800080; width: 185px;
				  height: 15px;
	}

	.magic {
				  font: bold 11px tahoma,verdana,sans-serif;
				  letter-spacing: 0;
					
				  width: 185px;
				  height: 15px;
				  color: #004080;
				  -moz-border-radius: 0;
				  padding: 0px;


       }
.piyush
{
font:  11px tahoma,verdana,sans-serif; color: #004080;
}

A:hover {  background-Color:  #004080; }
</style>

<script>

window.history.forward() ;


USER = '1';  ROLE = '1' ; LOG = '1' ; IMP_EXP = '1' ; selectedObj ='0';  

HIDE='0';
var i=0
var k = 0 ;
//var c=0
var c=0
var intShow
var intFrame
var speed=3

function expandAll()
{
	if(HIDE=='0')
	{
	USER = '0' ;ROLE = '0' ; LOG = '0' ; IMP_EXP = '0' ;

	document.all.usermgmt.style.display ='block';
	document.all.roleMgmt.style.display ='block';
	document.all.logMgmt.style.display  ='block';
	<%if(!((Config.CLIENT).equals(Config.CLIENT_SMS)))
			
		{
		%>
	 document.all.imp_exp.style.display  ='block';
	<% }
	%>
	
	document.getElementById("img_user").src="../images/mi_small.gif";  // up
	document.getElementById("img_role").src="../images/mi_small.gif";  // up
	document.getElementById("img_log").src="../images/mi_small.gif";  // up
	document.getElementById("expand").src="../images/minus.gif";
	<% if(!((Config.CLIENT).equals(Config.CLIENT_SMS)))
			
		{
		%>
	 document.getElementById("img_impexp").src="../images/mi_small.gif";  // up
	 <%
	 }
	%>
	parent.document.getElementById("content").src = "content.jsp";
	HIDE='1';
	}
	else
	{
	USER = '1' ;ROLE = '1' ; LOG = '1' ; IMP_EXP = '1' ;

	document.all.usermgmt.style.display ='none';
	document.all.roleMgmt.style.display ='none';
	document.all.logMgmt.style.display  ='none';
	<%if(!((Config.CLIENT).equals(Config.CLIENT_SMS)))
			
		{
		%>
	document.all.imp_exp.style.display  ='none';
	document.getElementById("img_impexp").src="../images/pl_small.gif"; 
		<%
		}
	%>
	document.getElementById("img_user").src="../images/pl_small.gif";  // down
	document.getElementById("img_role").src="../images/pl_small.gif";  // down
	document.getElementById("img_log").src="../images/pl_small.gif";  // down
	document.getElementById("expand").src="../images/plus.gif";
	// document.getElementById("img_impexp").src="../images/pl_small.gif";  // down
	parent.document.getElementById("content").src = "content.jsp";
	HIDE='0';
	}
	
}

function collapseAll()
{
	USER = '1' ;ROLE = '1' ; LOG = '1' ; IMP_EXP = '1' ;

	document.all.usermgmt.style.display ='none';
	document.all.roleMgmt.style.display ='none';
	document.all.logMgmt.style.display  ='none';
	document.all.imp_exp.style.display  ='none';
	
	document.getElementById("img_user").src="../images/pl_small.gif";  // down
	document.getElementById("img_role").src="../images/pl_small.gif";  // down
	document.getElementById("img_log").src="../images/pl_small.gif";  // down
	// document.getElementById("img_impexp").src="../images/pl_small.gif";  // down
	parent.document.getElementById("content").src = "content.jsp";

}
function changeUser(bar)
{
   if ( bar == '1' )  // user management
   {
   
   		if  ( USER == '0' )
	    {
		   document.all.usermgmt.style.display='none';  USER = '1' ;
  		   document.getElementById("img_user").src="../images/pl_small.gif";  // down
	        //  document.all.roleMgmt.style.display='none';
	        //  document.getElementById("img_role").src="../images/pl_small.gif"; }
	        //  document.all.roleMgmt.style.display='block';
	         // document.getElementById("img_role").src="../images/mi_small.gif"; }
		}
		else
	    {
	        
	        
	        document.all.usermgmt.style.display='block';  USER = '0' ;
	        //  ROLE = '1' ; LOG = '1' ; IMP_EXP = '1' ;
	    
	        // document.all.roleMgmt.style.display ='none';
			// document.all.logMgmt.style.display  ='none';
			// document.all.imp_exp.style.display  ='none';
			//  document.getElementById("img_role").src="../images/pl_small.gif";  // down
	  		// document.getElementById("img_log").src="../images/pl_small.gif";  // down
	  		// document.getElementById("img_impexp").src="../images/pl_small.gif";  // down
	  	
	  		document.getElementById("img_user").src="../images/mi_small.gif";   // up
	  		
		}
		
   }
   
   if ( bar == '2' )   // role management
   {
   		if  ( ROLE == '0' )
	    {
		   document.all.roleMgmt.style.display='none';  ROLE = '1' ;
  		   document.getElementById("img_role").src="../images/pl_small.gif";  // down
        }
		else
	    {
			document.all.roleMgmt.style.display='block';  ROLE = '0' ; 
	  		
	  	   // document.all.usermgmt.style.display  ='none';
  		   //  document.all.logMgmt.style.display  ='none';
		   //  document.all.imp_exp.style.display  ='none';
		    
	       //  document.getElementById("img_user").src="../images/pl_small.gif";  // down
	       //  document.getElementById("img_log").src="../images/pl_small.gif";  // down
	  	// 	document.getElementById("img_impexp").src="../images/pl_small.gif";  // down
		   
	  		document.getElementById("img_role").src="../images/mi_small.gif";   // up
		}
   }
   if ( bar == '3' )  // log management
   {
   		if  ( LOG == '0' )
	    {
		   document.all.logMgmt.style.display='none';  LOG = '1' ;
  		   document.getElementById("img_log").src="../images/pl_small.gif";  // down
        }
		else
	    {
			document.all.logMgmt.style.display='block'; LOG = '0' ; 
	  		
	  	    // document.all.usermgmt.style.display  ='none';
  		    // document.all.roleMgmt.style.display  ='none';
		   //  document.all.imp_exp.style.display  ='none';
		    
		  //   document.getElementById("img_user").src="../images/pl_small.gif";  // down
	       //  document.getElementById("img_role").src="../images/pl_small.gif";  // down
	  	// 	document.getElementById("img_impexp").src="../images/pl_small.gif";  // down
	  		
		   
	  		document.getElementById("img_log").src="../images/mi_small.gif";   // up
		}
   }

  if ( bar == '4' )  // import export utility
   {
    if  ( IMP_EXP == '0' )
	  {
	   document.all.imp_exp.style.display='none';  IMP_EXP = '1' ;
  	    document.getElementById("img_impexp").src="../images/pl_small.gif";  // down
      }
	 else
	  {
		 	document.all.imp_exp.style.display='block';  IMP_EXP = '0' ; 
	  		document.getElementById("img_impexp").src="../images/mi_small.gif";   // up
	 }
   		
   }
   		    parent.document.getElementById("content").src="content.jsp" ; 
 }

function setNavigation()
{
	document.all.usermgmt.style.display ='none';
	document.all.roleMgmt.style.display ='none';
	document.all.logMgmt.style.display  ='none';
<%
	if(!((Config.CLIENT).equals(Config.CLIENT_SMS)))
	{
		%>

	 document.all.imp_exp.style.display  ='none';
	 <%}%>
	 
}

function callPage(url)
{
	parent.document.getElementById("content").src = url;

}
function checkOut(obj)
{
	if(selectedObj == obj)
		{	obj.className='grey';  }
	else
			obj.className='lightOrange';
}
function checkIn(obj)
{
	if(selectedObj == obj)
		 {	obj.className='grey';  }
	else
		obj.className='grey';
}
function setTdColor(obj)
{
		selectedObj.className='lightOrange';
		obj.className='grey';
		selectedObj = obj ;
}

function show_hide_menu()
{
if (c==0)
	{
		c=1
		clearInterval(intShow)
		intHide=setInterval("hide()",10)

	}
else
	{
		c=0
		clearInterval(intHide)
		intShow=setInterval("show()",10)
		frame_setting();

	}
}

function show()
{
	if (i<0)
	{
		i=i+speed
		document.getElementById('admin_head').style.left=i
	}

}
function hide()
{
	if (i>-30)
	{
		i=i-speed
		document.getElementById('admin_head').style.left=i
	}
	else
	{	clearInterval(intHide)
		frame_setting();
	}

}





</script>
<link href="../css/ColorNew.css" rel="stylesheet" type="text/css">

</head>


<!--<body onLoad="setNavigation();" bgcolor="#EDE6D9" id='menu_body'  >-->
<body onLoad="setNavigation();" bgcolor="#efefef" id='menu_body'  >

<form name="form1" method="post" action="">

<div id="admin_head" style="position:relative">

<table width="100%" >


<tr >
    <td width="50%"><div align='right'><img  id="expand" alt="expand all" onClick='expandAll();' style="cursor:pointer"
			src="../images/plus.gif" ></div>
	</td>
</tr>
</table>


<!-- Iamges and css changed for Menus by Asha on 4th Jan 2011 -->


<div id="usermgmt_header" style="position: relative">
<table >
	<tr>
		<td valign="baseline" class='magic' onClick="changeUser(1);">&nbsp;&nbsp;<img
			id='img_user'  style="cursor:pointer"
			src="../images/pl_small.gif" > <font color="#004080" face="Verdana" size="1" style="cursor:pointer">User
		Management</font>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </td>
	</tr>

</table>
</div>

<div id="usermgmt" style="position: relative">
<table width="185" border="0"  cellspacing="0" cellpadding="0">
	<tr valign="middle" >
		<td valign="middle" class='magic' style="cursor:pointer" nowrap	onMouseOver="checkIn(this);"
		 onMouseOut="checkOut(this);" onClick="setTdColor(this);callPage('../usermgmt/masters/umgmtGroup_cntMst.jsp');">
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <font color="#004080" face="Verdana" size="1" style="cursor:pointer" id="group">Group Master</font></td>
	</tr>
	<tr>
		<td valign="middle" class='magic' style="cursor:pointer" nowrap
			onMouseOver="checkIn(this);"  onMouseOut="checkOut(this);" 
			onClick=" setTdColor(this);callPage('../usermgmt/masters/umgmtSeatMst_cnt.jsp');">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<font color="#004080" face="Verdana" size="1" style="cursor:pointer">Seat Master</font></td>
	</tr>
	<tr>
		<td valign="middle" class='magic' style="cursor:pointer" nowrap
			onMouseOver="checkIn(this);"  onMouseOut="checkOut(this);"
			onClick="setTdColor(this);callPage('../usermgmt/masters/umgmtUser_cntMst.jsp');">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<font color="#004080" face="Verdana" size="1" style="cursor:pointer">User Master</font></td>
	</tr>
	
	<!-- 

	<tr>
		<td valign="middle" class='piyush' style="cursor:pointer" nowrap
			onMouseOver="checkIn(this);" onMouseOut="checkOut(this);"
			onClick="setTdColor(this);callPage('../usermgmt/masters/gblMetaTable_cntMst.jsp');">&nbsp;&nbsp;<img
			src="../images/bullet-large.png" width='13' height='15'>&nbsp;&nbsp;&nbsp;&nbsp;Module Master</td>
	</tr>
		 -->
	
	
	<!-- <tr>
		<td valign="middle" class='magic' style="cursor:pointer" nowrap
			onMouseOver="checkIn(this);" onMouseOut="checkOut(this);"
			onClick="setTdColor(this);callPage('../usermgmt/masters/umgmtMenu_cntMst.jsp');">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<font color="#004080" face="Verdana" size="1" style="cursor:pointer">Menu Master</font></td>
	</tr>-->
	<% 
	System.out.println("client value in "+Config.CLIENT);
	%>
	<%if(!((Config.CLIENT).equals(Config.CLIENT_SMS)))
			
		{
		System.out.println("client value"+Config.CLIENT);%>
	<tr>
		<td valign="middle" class='magic' style="cursor:pointer" nowrap
			onMouseOver="checkIn(this);" onMouseOut="checkOut(this);"
			onClick="setTdColor(this);callPage('../usermgmt/masters/resetPassword_cnt.jsp');">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<font color="#004080" face="Verdana" size="1" style="cursor:pointer">Reset User
		Password</font></td>
	</tr>
	<%
		
		} %>
	<tr>
		<td valign="middle" class='magic' style="cursor:pointer" nowrap
			onMouseOver="checkIn(this);" onMouseOut="checkOut(this);"
			onClick="setTdColor(this);callPage('../usermgmt/masters/ChangeAdminPassword_cnt.jsp');">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<font color="#004080" face="Verdana" size="1" style="cursor:pointer">Change Admin
		Password</font></td>
	</tr>
</table>
</div>



<!--Role Management-->
<table id="role_mgmt_header">

	<tr>
		<td valign="middle" class='magic' onClick="changeUser(2);">&nbsp;&nbsp;<img
			id='img_role'  style="cursor:pointer"
			src="../images/pl_small.gif" ><font color="#004080" face="Verdana" size="1" style="cursor:pointer">Role
		Management</font>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
	</tr>

</table>
<div id="roleMgmt" style="position: relative">
<table width="185" border="0"  
	bordercolor="#333333" cellspacing="0" cellpadding="0">
	<tr>
		<td class='magic' style="cursor:pointer" nowrap align='left'
			onMouseOver="checkIn(this);" onMouseOut="checkOut(this);"
			onClick="setTdColor(this);callPage('../usermgmt/masters/umgmtRole_cntMst.jsp');">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<font color="#004080" face="Verdana" size="1" style="cursor:pointer">Role  Master</font></td>
	</tr>
	<%if(!((Config.CLIENT).equals(Config.CLIENT_SMS)))
			
		{%>
	<tr>
		<td class='magic' style="cursor:pointer" nowrap align='left'
			onMouseOver="checkIn(this);" onMouseOut="checkOut(this);"
			onClick="setTdColor(this);callPage('../usermgmt/masters/umgmtGroupRole_cntMst.jsp');">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<font color="#004080" face="Verdana" size="1" style="cursor:pointer">Group Role Master</font></td>
	</tr>
	<%} %>
	<tr>
		<td class='magic' style="cursor:pointer" nowrap align='left'
			onMouseOver="checkIn(this);" onMouseOut="checkOut(this);"
			onClick="setTdColor(this);callPage('../usermgmt/masters/umgmtRoleMenuCnt_Mst.jsp');">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<font color="#004080" face="Verdana" size="1" style="cursor:pointer">Role Menu Master</font></td>
	</tr>
	
	<tr>
		<td class='magic' style="cursor:pointer" nowrap align='left'
			onMouseOver="checkIn(this);" onMouseOut="checkOut(this);"
			onClick="setTdColor(this);callPage('../usermgmt/masters/umgmt_seat_role_cntMst.jsp');">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<font color="#004080" face="Verdana" size="1" style="cursor:pointer">Seat
		Role Master</font>&nbsp;</td>
	</tr>
	<%-- 
	<tr>
		<td class='piyush' style="cursor:pointer" nowrap align='left'
			onMouseOver="checkIn(this);" onMouseOut="checkOut(this);"
			onClick="setTdColor(this);callPage('../usermgmt/masters/seat_role_cntMst_Advanced.jsp');">&nbsp;&nbsp;<img
			src="../images/bullet-large.png" width='13' height='15'>&nbsp;&nbsp;&nbsp;&nbsp;Advanced Seat
		Role Master&nbsp;</td>
	</tr>
	--%>
	<tr>
		<td class='magic' style="cursor:pointer" nowrap align='left'
			onMouseOver="checkIn(this);" onMouseOut="checkOut(this);"
			onClick="setTdColor(this);callPage('../usermgmt/masters/umgmtRoleSeatTableMst_cnt.jsp');">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color="#004080" face="Verdana" size="1" style="cursor:pointer">Seat
		Permission Master</font>&nbsp;</td>
	</tr>
	
	<!--<tr>
		<td class='magic' style="cursor:pointer" nowrap align='left'
			onMouseOver="checkIn(this);" onMouseOut="checkOut(this);"
			onClick="setTdColor(this);callPage('../usermgmt/masters/umgmtButtonLevel_cntMst.jsp');">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color="#004080" face="Verdana" size="1" style="cursor:pointer">Button Level Privileges
			</font></td>
	</tr>
	
--></table>
</div>


<!--Log Management-->
<table id="log_mgmt_header">

	<tr valign="middle">
		<td class='magic' onClick="changeUser(3);">&nbsp;&nbsp;<img
			id='img_log'  style="cursor:pointer"
			src="../images/pl_small.gif" ><font color="#004080" face="Verdana" size="1" style="cursor:pointer">Log&nbsp;&nbsp;
		Management</font>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </td>
	</tr>

</table>
<div id="logMgmt" style="position: relative">
<table width="185" border="0" 
	bordercolor="#333333" cellspacing="0" cellpadding="0">
	
	<!-- <tr>
		<td class='piyush' style="cursor:pointer" nowrap
			onMouseOver="checkIn(this);" onMouseOut="checkOut(this);"
			onClick="setTdColor(this);callPage('../usermgmt/reports/admin_action.cnt');">&nbsp;&nbsp;<img
			src="../images/bullet-large.png" width='13' height='15'>&nbsp;&nbsp;&nbsp;&nbsp;Audit
		Log</td>
	</tr> -->
	<%if(!((Config.CLIENT).equals(Config.CLIENT_SMS)))
			
		{%>
	<tr>
		<td class='magic' style="cursor:pointer" nowrap
			onMouseOver="checkIn(this);" onMouseOut="checkOut(this);"
			onClick="setTdColor(this);callPage('../usermgmt/reports/umgmtUserProfile_cntMst.jsp');">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color="#004080" face="Verdana" size="1" style="cursor:pointer">User Profile</font></td>
	</tr> <%} %>
	<tr>
		<td class='magic' style="cursor:pointer" nowrap
			onMouseOver="checkIn(this);" onMouseOut="checkOut(this);"
			onClick="setTdColor(this);callPage('../usermgmt/reports/user_log.cnt');">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color="#004080" face="Verdana" size="1" style="cursor:pointer">User Log Report</font></td>
	</tr>
	<!--<tr>
		<td class='piyush' style="cursor:pointer" nowrap
			onMouseOver="checkIn(this);" onMouseOut="checkOut(this);"
			onClick="setTdColor(this);callPage('../usermgmt/reports/errorLogController.cnt');">&nbsp;&nbsp;<img
			src="../images/bullet-large.png" width='13' height='15'>&nbsp;&nbsp;&nbsp;&nbsp;Error Log</td>
	</tr>-->
	<%if(!((Config.CLIENT).equals(Config.CLIENT_SMS)))
			
		{%>
	<tr>
<td class='magic' style="cursor:pointer" nowrap
			onMouseOver="checkIn(this);" onMouseOut="checkOut(this);"
			onClick="setTdColor(this);callPage('../usermgmt/reports/umgmtTreeView.jsp');">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color="#004080" face="Verdana" size="1" style="cursor:pointer">User Management Tree View</font>
		</td>
	</tr> 

<tr>
<td class='magic' style="cursor:pointer" nowrap
			onMouseOver="checkIn(this);" onMouseOut="checkOut(this);"
			onClick="setTdColor(this);callPage('../usermgmt/reports/seatRoleAuditLogCnt.jsp');">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color="#004080" face="Verdana" size="1" style="cursor:pointer">Seat Role Audit Log Report</font>
		</td>
</tr>
	 
<tr>
<td class='magic' style="cursor:pointer" nowrap
			onMouseOver="checkIn(this);" onMouseOut="checkOut(this);"
			onClick="setTdColor(this);callPage('../usermgmt/reports/roleMenuAuditLogCnt.jsp');">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color="#004080" face="Verdana" size="1" style="cursor:pointer">Role Menu Audit Log Report</font>
		</td>
</tr>
	
	 
<tr>
<td class='magic' style="cursor:pointer" nowrap
			onMouseOver="checkIn(this);" onMouseOut="checkOut(this);"
			onClick="setTdColor(this);callPage('../usermgmt/reports/userAuditLogCnt.jsp');">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color="#004080" face="Verdana" size="1" style="cursor:pointer">User Audit Log Report</font>
		</td>
</tr>	
	
<tr>
<td class='magic' style="cursor:pointer" nowrap
			onMouseOver="checkIn(this);" onMouseOut="checkOut(this);"
			onClick="setTdColor(this);callPage('../usermgmt/reports/seatPermissionAuditLogCnt.jsp');">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color="#004080" face="Verdana" size="1" style="cursor:pointer">Seat Permission Audit Log Report</font>
		</td>
</tr>
 <%} %>
</table>
</div>

<!--Import / Export -->
<%if(!((Config.CLIENT).equals(Config.CLIENT_SMS)))
			
		{%>
<table id="imp_exp_header">

	<tr valign="middle">
		<td class='magic' onClick="changeUser(4);">&nbsp;&nbsp;<img
			id='img_impexp'  style="cursor:pointer"
			src="../images/pl_small.gif" ><font color="#004080" face="Verdana" size="1" style="cursor:pointer">Setting</font>       
		 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

		</td>
	</tr>

</table>
<%} %>
 <%if(!((Config.CLIENT).equals(Config.CLIENT_SMS)))
			
		{%>
<div id="imp_exp" style="position: relative">
<table width="185" border="0" 
	bordercolor="#333333" cellspacing="0" cellpadding="0">
	<!-- <tr>
		<td class='magic' style="cursor:pointer" nowrap
			onMouseOver="checkIn(this);" onMouseOut="checkOut(this);"
			onClick="setTdColor(this);callPage('../usermgmt/masters/userMgtConfigController.cnt');">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color="#004080" face="Verdana" size="1" style="cursor:pointer">Configuartion Master</font>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
	</tr> -->
	
	
	<tr>
		<td class='magic' style="cursor:pointer" nowrap
			onMouseOver="checkIn(this);" onMouseOut="checkOut(this);"
			onClick="setTdColor(this);callPage('../usermgmt/masters/HospitalMaster_cnt.jsp ');">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color="#004080" face="Verdana" size="1" style="cursor:pointer">Hospital Master</font>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
	</tr>
	
	

</table>
</div>
 <%}%>
</div>
</form>

</body>
</html>