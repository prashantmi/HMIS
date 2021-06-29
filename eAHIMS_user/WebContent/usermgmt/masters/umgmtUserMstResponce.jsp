<%@page import="usermgmt.masters.UmgmtUserMstBean"%>
<%@ page import="java.util.*"%>

<%

String mode= request.getParameter("hmode");

	List AL_List;
	
	
	if(mode.equals("SEAT"))
	{
		String option="";
		//System.out.println("welcome to SEAT");
		usermgmt.masters.UmgmtUserMstBean user=new UmgmtUserMstBean(); 
		String gid=(String)request.getParameter("gid");
		String hospitalCode=(String)request.getParameter("hospitalCode");
		//System.out.println("welcome to ajax"+gid);	
		user.setGroupNo(gid);
		user.setHospitalCode(hospitalCode);
		option=user.seatOptions();
		System.out.println("Hello India"+option.length()+"abc");
		response.getWriter().write(option);
		
	}
	%>