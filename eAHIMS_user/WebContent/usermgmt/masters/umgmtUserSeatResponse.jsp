<%@page import="usermgmt.masters.UmgmtUserSeatMstBean"%>
<%@ page import="java.util.*"%>
<%

String mode= request.getParameter("hmode");
//System.out.println("welcome to DET"+mode);
	List AL_List;
	
	if(mode.equals("DET"))
	{
		//System.out.println("welcome to DET");
		usermgmt.masters.UmgmtUserSeatMstBean user=new UmgmtUserSeatMstBean(); 
		String uid=(String)request.getParameter("uid");
		Object temp []=null;
		String temp1="";
		//System.out.println("welcome to ajax"+uid);
		user.setUserName(uid);
		AL_List = user.initializeviewMode();
		temp=AL_List.toArray();
		for(int i=0;i<temp.length;i++)
		{
			//System.out.println("temp"+(String)temp[i]);	
			temp1=temp1+(String)temp[i]+"^";
		}
		
		response.getWriter().write(temp1);
	}
	
	if(mode.equals("SEAT"))
	{
		String option="";
		//System.out.println("welcome to SEAT");
		usermgmt.masters.UmgmtUserSeatMstBean user=new UmgmtUserSeatMstBean(); 
		String gid=(String)request.getParameter("gid");
		//System.out.println("welcome to ajax"+gid);	
		user.setGroupName(gid);
		option=user.seatOptions();
		response.getWriter().write(option);
	}
	%>