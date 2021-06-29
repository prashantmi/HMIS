<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<%@ page import="java.util.*"%>
<%@ page import="java.io.*"%><jsp:useBean id="user" class="usermgmt.masters.UmgmtUserMstBean" scope="request" />
<%@taglib uri="/WEB-INF/otTags.tld" prefix="otTags"%>
 <link href="../../css/hisStyle.css" rel="stylesheet" type="text/css">
	<link href="../../css/master.css" rel="stylesheet" type="text/css">
	<link href="../../css/ColorNew.css" rel="stylesheet" type="text/css">
<script>
function wclose()
	{   
	    var found=false;           
	    var menuids=window.opener.document.getElementById("menuides");
	    menuids.innerHTML="";	    
	    var menus=document.getElementsByName("menuIdArray");    
	  
	    for(i=0 ; i < menus.length ;i++ )
	       {
	       if(menus[i].checked)
	           {
	           found=true;
	       menuids.innerHTML+="<input type=\"hidden\" name=\"menuIdArray\" value=\""+menus[i].value+"\">";
	           }
            }
           if(found==false)
           menuids.innerHTML+="<input type=\"hidden\" name=\"menuIdArrayCheck\" value=\""+found+"\">";
           
           	window.close();
			
	}

</script>
<%String hospitalCode= (String)session.getAttribute("HOSPITAL_CODE"); 
  user.setHospitalCode(hospitalCode);
  
 
  
   
					 
				 
  System.out.println("MenuId.............."+session.getAttribute("MENU_IDS"));
  System.out.println("MenuName.............."+session.getAttribute("MENU_NAMES"));
  String menuids=(String)session.getAttribute("MENU_IDS");
  String menunames=(String)session.getAttribute("MENU_NAMES");
  
    String title="";
  String hmode = "";
	
	if(hmode.equals("MENU"))
	{
		title="menu";
	}
  %>

<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Menu Details</title>

	<script language="JavaScript" src="../js/Validation.js" type="text/JavaScript"></script>
	<script language="JavaScript" src="../js/functionality.js" type="text/JavaScript"></script>
	<script language="JavaScript" src="../js/empPopup.js" type="text/JavaScript"></script>
	
	<script language="JavaScript" src="../js/calendar.js"></script>
	<script language="JavaScript" src="../js/util.js" type="text/JavaScript"></script>
	
</head>
   

<body>
<form name="form1" method="post" action="umgmtUser_cntMst.jsp">
<input type="hidden" name="hospitalCode" value="<%=user.getHospitalCode()%>">
<table width="100%" align="center"  border="0" cellspacing="1" cellpadding="0">
		
		<tr class="ShadedSubTitleTagImage">
		<td align="center" width="50%" >
		<strong>
			Select</strong>
			
			</td>
			 <td   width="50%"  align="center">
			 <strong>
			Menu Name</strong>
	 	
	 	</td>	
	 	</tr>
	 	<tr><td class='tdfont' width="50%"></td><td class='tdfont' width="50%"></td></tr>
	 	<tr><td class='tdfont' width="50%"></td><td class='tdfont' width="50%"></td></tr>
	 	
	 	<% 
	 	String menuidSelected="";
menuidSelected=request.getParameter("menuids");
System.out.println("menus slelected..........."+menuidSelected);
	 	 	
	 		
	 	String newmenuID="";
	 	String menuID="";
		String menuName="";
		StringTokenizer st1 = new StringTokenizer(menuids,"^");
		StringTokenizer st2 = new StringTokenizer(menunames,"^");
		
		
		
		if(menuidSelected!="")
	 	{
	 	System.out.println("when modify page is called-------->");
	 	while(st1.hasMoreElements() && st2.hasMoreElements())
		
		{
		    boolean found=false; 
		    System.out.println("initally boolean-------->"+found);
		    menuID=(String)st1.nextToken().toString();
			menuName=(String)st2.nextToken().toString();
			System.out.println("menuID-------->"+menuID);
			System.out.println("menuName------>"+menuName);
		
		StringTokenizer st3 = new StringTokenizer(menuidSelected,"^");
		
		  while(st3.hasMoreElements())
		     {
		    newmenuID=(String)st3.nextToken().toString();
		     System.out.println("newmenuID------->"+newmenuID);
		     
		     if(menuID.equals(newmenuID))
		        {
		       
		        found=true;
		        break;
		        }
		        
		       }//inner while closed 
		        System.out.println("finally found------->"+found);
		        if(found)
		        {
		         System.out.println("select_checkbox------->");
		        %>
		        <tr>
		
			
			<td class='tdfont'><div align='center'>
			<input type='checkbox' checked  name='menuIdArray' value='<%=menuID %>'>
			</div>
			</td>
			 <td class='tdfont'>
			 <div align="center">
			<%=menuName %>
	 	 </div>
	 	</td>	
	   <tr>
		        
		        <%
		         }
		        else
		        {
		         System.out.println("UNselect_checkbox------->");
		        %>
		        
	 <tr>
		<td class='tdfont'><div align='center'>
			<input type='checkbox'  name='menuIdArray' value='<%=menuID %>'>
			</div>
			</td>
			 <td class='tdfont'>
			 <div align="center">
			<%=menuName %>
	 	 </div>
	 	</td>	
	  <tr>
		        
		        <%
		        }//inner else closed
		     
		     } //outer while closed
			
	 	
	 	
	 	
	 	
	 	}//outer if closed
       else
       {
       	System.out.println("when add page is called-------->");
		StringTokenizer st4 = new StringTokenizer(menuids,"^");
		StringTokenizer st5 = new StringTokenizer(menunames,"^");
		
		while(st4.hasMoreElements() && st5.hasMoreElements())
		
		{
			menuID=(String)st4.nextToken().toString();
			menuName=(String)st5.nextToken().toString();
			System.out.println("menuID else-------->"+menuID);
			System.out.println("menuName else------>"+menuName);
			  %>
	<tr>
		
			
			<td class='formbg'><div align='center'>
			<input type='checkbox'  name='menuIdArray' value='<%=menuID %>'>
			</div>
			</td>
			 <td class='formbg'>
			 <div align="center">
			<%=menuName %>
	 	</div>
	 	</td>	
	 	
</tr>
			
			<%
			}//last while closed
	     }//else closed		
			%>
<tr><td colspan='2'><center><img src="../../images/submit_tab.gif" onClick="wclose()"></center></td></tr>
</table>
 
</form>

</body>
</html>