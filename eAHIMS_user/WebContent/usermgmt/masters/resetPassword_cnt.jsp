
<%@page import="usermgmt.umgmtGlobal"%>
<%@page import="HisGlobal.HisResultSet"%>
<%@page import="HisGlobal.Conn"%>
<%@page import="java.sql.SQLException"%>
<jsp:useBean id="connect" class="HisGlobal.HisMethods" scope="request" />
<jsp:useBean id="pageObj" class="usermgmt.masters.Page" scope="request" />
<jsp:useBean id="funObj" class="usermgmt.FuncLib" scope="request" />
<jsp:useBean id="securePwd" class="login.SecurityUtil" scope="request" />
<script>
	window.history.forward() ;
	</script>
<%@ page import="java.util.*"%>
<%
try{
	
	String result="";
String hmode = request.getParameter("hmode");
 if ( hmode==null)
	  hmode ="";
 String seatId = (String)session.getAttribute("SEAT_ID");	
 if(seatId == null)
 {		
 	%>
 		<jsp:forward page="umgmtWarningPage.jsp"/>		
 	<%
 }
 	//seat.setSeatId(seatId);
	
//Declare Variable

String 	list_page 		= "resetPassword.jsp";
String 	result_page 	= "result.jsp";

if ( hmode== null  || hmode.equals("") || hmode.equals("INIT") ) 
{
	%> 
	   <jsp:forward page="<%=list_page%>" >
			<jsp:param name="hmode" value="INIT" />
	   </jsp:forward>
    <%
}	
 if (hmode.equals("RESET") || hmode.equals("UNLOCK") )
 {    //hmode="";
	 boolean flag1=false ;
	  boolean flag2=false ;
	  String p_user = request.getParameter("chk");
	  System.out.println("user"+p_user);
	//  String[] array=null;
	//  array=p_user.split("^"); 
	 //String id =  p_user.substring(0,p_user.length()-1);
	String[] arraymenu=p_user.replace("^", "#").split("#");
	 String id =  arraymenu[0];
	 String userName =  arraymenu[1];
	//  String id=array[0];
	//  String lock=array[1];
	  
	  System.out.println("user id "+id);
	//   System.out.println("lock---- "+lock);
	
	  String pwd="123456";	
	//Get the Field based on query  
		

	  System.out.println("pwd in cnt =="+pwd);
	  if(hmode.equals("RESET"))
		{
	 // String userPassward= securePwd.encrypt(pwd);
	  String strQueryPasHash ="SELECT sha1('"+pwd+userName+"') from dual ";

		String 		str		=	"";
		HisResultSet 	rs	=	null;

		try
		{
			rs = Conn.getInstance().getRecord(strQueryPasHash);
			if(rs.next())
				str=rs.getString(1);

		}
		catch(SQLException se)
		{
			System.out.println("3.error while fetching data from getField():\n"+se);
		}

	
	  
	 
	  String HospitalCode = (String)session.getAttribute("HOSPITAL_CODE");
	  //sssString HospitalCode=usermgmt.umgmtGlobal.HospitalCode;
	  System.out.println("HospitalCode =="+HospitalCode);
	   //String updateQry = " update gblt_user_mst set  GSTR_OLD_PASSWORD=GSTR_PASSWORD, GSTR_PASSWORD='"+userPassward+"' WHERE GNUM_USERID='"+id+"'";
	 
	   
	   String deleteQuery="delete from GBLT_LOGIN_UNSUCESSFUL_LOG where GSTR_LOGINID=(select GSTR_USER_NAME from gblt_user_mst where GNUM_USERID='"+id+"' and  GNUM_HOSPITAL_CODE='"+HospitalCode+"' )";
	   
	    System.out.println("deleteQuery----->"+deleteQuery);
	   
	   //String updateQry = "update  GBLT_USER_MST  set  GNUM_ISLOCKED='0',GSTR_HINT_ANSWER=null,GSTR_HINT_QUESTIONS=null, GSTR_OLD_PASSWORD=GSTR_PASSWORD,GSTR_PASSWORD='"+userPassward+"' WHERE GNUM_USERID='"+id+"' and  GNUM_HOSPITAL_CODE='"+HospitalCode+"'";
	  String updateQry = " update gblt_user_mst set  GNUM_ISLOCK='0',GSTR_HINT_ANSWER=null,GNUM_QUESTION_ID=null, GSTR_OLD_PASSWORD=GSTR_PASSWORD,GSTR_PASSWORD='"+str+"',GDT_CHANGEPASSWORD_DATE=sysdate,GNUM_LSTMOD_SEATID='"+seatId+"',GDT_LSTMOD_DATE=sysdate   WHERE GNUM_USERID='"+id+"' and  GNUM_HOSPITAL_CODE='"+HospitalCode+"'";
	
	  System.out.println("updateQuery----->"+updateQry);
	
	
	try{
	  
	   flag1 = connect.updateRecord(deleteQuery);
	   flag2 = connect.updateRecord(updateQry);
	  
	  }
	  catch(Exception e)
	  {
	  e.printStackTrace();
	  }
	}
	else if(hmode.equals("UNLOCK"))
	{
		  String HospitalCode = (String)session.getAttribute("HOSPITAL_CODE");
		   System.out.println("HospitalCode =="+HospitalCode);
		   String updateQry = " update gblt_user_mst set  GNUM_ISLOCK='0',GNUM_LSTMOD_SEATID='"+seatId+"',GDT_LSTMOD_DATE=sysdate   WHERE GNUM_USERID='"+id+"' and  GNUM_HOSPITAL_CODE='"+HospitalCode+"'";
		System.out.println("updateQuery----->"+updateQry);
		flag2 = connect.updateRecord(updateQry);
	}
	  if ( flag1 && flag2 && hmode.equals("RESET"))
	  {
		   	 
		   	  result="Password Reseted !";
		   		hmode="";
	  		//String display="";
			//display	+=	"<table><tr><td colspan='2'>&nbsp;</td>";
			//display	+="<td colspan='2'><font color='red' size=3>&nbsp;updation SUCCESSFULL------------</font></td></tr></table>";
			//out.print(display);
	  }
	  else if (flag2 && hmode.equals("UNLOCK"))
	  {
		  result="User Unlocked !";
		  hmode="";
	  }
	  else
	  {
	    System.out.println("error occur while updation....");
	    result="error occur while updation....";
	    //String display="";
		//display	+=	"<table><tr><td colspan='2'>&nbsp;</td>";
		//display	+="<td colspan='2'><font color='red' size=3>&nbsp;Do You want to reset the password....!!</font></td></tr></table>";
		//out.print(display);
	  }
	 %>
		 <jsp:forward page="<%=result_page%>" >
		 <jsp:param name = "nextpage" value="<%=list_page%>"/>
			<jsp:param name="pwd" value="<%=pwd%>"/>
			<jsp:param name="status" value="<%=result%>"/>
			<jsp:param name="hmode" value="INIT" />
	   </jsp:forward>
<%			  
 }
}catch(Exception e)
{
	System.out.println("Exception insiode cnt ="+e);
	 e.printStackTrace();
}
 
%>

