<jsp:useBean id="beanObj" class="usermgmt.masters.umgmtRoleSeatMenuMstBean" scope="request"/>
<%@ page language="java" import="java.util.*"%>
<%

String[] chk = request.getParameterValues("chk");


String condition = "";
String moduleId = null;
String roleId = null;
String seatId = null;
if(chk!=null)
{
	
	for(int i=0;i<chk.length;i++)
	{

		StringTokenizer st = new StringTokenizer(chk[i],"^");
		moduleId = st.nextElement().toString();
		roleId 	= st.nextElement().toString();
		seatId 	= st.nextElement().toString();
		
		condition += " ( GNUM_MODULE_ID = "+moduleId+" and GNUM_ROLE_ID = "+roleId+" and GNUM_SEATID = "+seatId+") or";
	}
	condition = condition.substring(0,condition.length()-2);
}


String query = 	" select  "+
				" 	("+
				"		select initcap(GSTR_MODULE_NAME)"+
				"		from GBLT_METATABLE_TYPE_MST"+
				"		where GNUM_MODULE_ID = a.GNUM_MODULE_ID"+
				"	) module_name,	"+	
				"	("+
				" 		select initcap(gstr_role_name)"+
				"		from gblt_role_mst"+
				"		where GNUM_ROLE_ID=a.GNUM_ROLE_ID"+
				"		and  GNUM_MODULE_ID = a.gnum_module_id"+
				"	) rolename,	"+		
				"	("+
				"		select initcap(GSTR_SEAT_NAME) seatname"+
				"		from gblt_seat_mst"+
				"		where GNUM_SEATID=a.GNUM_SEATID"+
				"	) seatname, 	"+
				"	("+
				"		select initcap(GSTR_MENU_NAME) menuname"+
				"		from GBLT_MENU_MST"+
				"		where GNUM_MENU_ID=a.GNUM_MENU_ID"+
				"	) menuname 	"+
				" from GBLT_ROLE_SEAT_MENU_DTL a where GNUM_ISVALID = '1' ";
if(!condition.equals(""))
{
		query += " and ("+condition+")";

}
	
query += " ORDER BY GNUM_SEATID,GNUM_MODULE_ID,GNUM_ROLE_ID";

List dataList = beanObj.getDetails(query,4);

System.out.println("query is "+query);

%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>Seat Master</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<script language="JavaScript" src="../js/Validation.js" type="text/JavaScript" ></script>
<script language="JavaScript" src="../js/functionality.js" type="text/JavaScript" ></script>

<script>
<%
	String currentStatusValue	=	beanObj.getCombo1();
	String currentStatus		=	"";	
	if(currentStatusValue.equals("1"))
		currentStatus	=	"Active";
	else if(currentStatusValue.equals("2"))
		currentStatus	=	"InActive";
%>

function submitMode(hmode)
{
	document.forms[0].hmode.value = hmode;
	document.forms[0].submit();
}
function callMe()
{

//	document.all.id1.display='none';
	changeState('id1','hidden');
	window.print();
	//document.all.id1.display='block';
	changeState('id1','visible');
}
 


</script>

<link href="../../css/hisStyle.css" rel="stylesheet" type="text/css">
</head>

<body bgcolor="#FFFFFF" text="#000000">
<form name="form1" method="post" action="umgmtRoleSeatMenuMst_cnt.jsp">

<table width='100%' border='0' cellspacing='1' cellpadding='0'>
<tr>
<td>
<div  id = 'id1' align='right'>
<a style=cursor:hand><img src='../../images/Cancel.gif' tabindex='1' onKeyPress='submitMode("DEFAULT");' border=0 onClick='submitMode("DEFAULT");'></a>
<a style=cursor:hand><img src='../../images/Print.gif' border=0 tabindex='1' onKeyPress='callMe()' onClick='callMe();'></a>
</div>
</td>
</tr>
</table>

	<table width="80%" align="center">
    <tr> 		
      <td colspan='5' align='center'>
      <strong>
      <font size='3' face='Verdana, Arial, Helvetica, sans-serif'>G.N.C.T.D</font></strong></td>	  
    </tr>
    <tr> 		
      <td colspan='5' align='center'><b>Role Seat Menu Detail Report , Status	:<u><%=currentStatus%></u></b></td>	  
    </tr>
      <tr> 		
      <td colspan='5' align='right'>Report Date : <%=beanObj.getSysdate() %></td>	  
    </tr>
     <tr> 		
      <td colspan='5' align='center'>&nbsp;</td>	  
    </tr>
     <tr> 		
      <td colspan='5' align='center'>&nbsp;</td>	  
    </tr>
     <tr> 		
      <td colspan='5' align='center'>&nbsp;</td>	  
    </tr>        
   
 
  <%
 
  int slNo = 1;

  boolean displayHeading = false;
  boolean firstRecord = true;
  boolean displayLine = false;
  String prevModule = "";
  String prevRole = "";
  String prevSeat = "";
  int count = 1;

  for(int i=0;i<dataList.size();i+=4)
  {
	  String currModule = dataList.get(i+0).toString();
	  String currRole 	= dataList.get(i+1).toString();
	  String currSeat 	= dataList.get(i+2).toString();
	  
	  if(firstRecord)
	  {
		
		  
		  displayHeading = true;
		  firstRecord    = false;
	  }
	  else if( !currModule.equals(prevModule) || !currRole.equals(prevRole) || !currSeat.equals(prevSeat) )
	  {
		 
		  displayHeading = true;
		  displayLine = true;
		  slNo = 1;
	  }
	  
  
	  if(displayHeading)
	  {
		  if(displayLine)
		  {
			  %>
			  <tr>					   
		  	   <td colspan='5' align='left'><hr width='100%'></td>
		  	   </tr>	
		  	   <%
				  displayLine = false;
		  }
			  %> 			  
			   <tr>		
			   <td colspan='1' align='left'><b>Module : <%=currModule%></b></td>
			   <td colspan='1' align='left'><b>Role : <%=currRole%></b></td>
			   <td colspan='1' align='left'><b>Seat : <%=currSeat%></b></td>
		  	   <td colspan='1' align='left'></td></tr>		  	 
		  	   
		  	   
		  	   <tr>
			   <%
			   displayHeading=false;
			  count++;
	   }
	   %>

	   <td colspan='1' align='left'><%=dataList.get(i+3)%></td>
	     
	  <%
	  if(slNo%4==0)
	  {
		  %>
		  </tr>
		  <tr>
		  <%
	  
	  }
	  slNo++;

	  
	  prevModule = 	  currModule;
	  prevRole = 	  currRole;
	  prevSeat = 	  currSeat;

  } %>
  	   </tr>
  	    <tr>					   
		  	   <td colspan='5' align='left'><hr width='100%'></td>
		  	   </tr>	
  	   </table>
    
    
    
    <input type='hidden' name='hmode'>
    
    </FORM>
	</BODY>
</HTML>
