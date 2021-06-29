<!-- @author: Adil Wasi (Used for Pagination on DataSet combo)
	  Date  : 05-Apr-2013 -->

<%@page import="usermgmt.masters.UmgmtRoleSeatTableMstBean"%>
<%@ page import="java.util.*"%>
<%

String mode= request.getParameter("ajaxHmode");
//System.out.println("welcome to DET"+mode);
	List AL_List;
	
	if(mode.equals("GETLEFTCOLUMN"))
	{
		//System.out.println("welcome to DET");
		usermgmt.masters.UmgmtRoleSeatTableMstBean user=new UmgmtRoleSeatTableMstBean(); 
		String strGroup=(String)request.getParameter("strGroup");
		String strSeat=(String)request.getParameter("strSeat");
		String strTable=(String)request.getParameter("strTable");
		String strRightColumnList=(String)request.getParameter("strRightColumnList");
		String strAlphabet=(String)request.getParameter("strAlphabet");
		String strModule=(String)request.getParameter("strModule");
		String hospitalcode=(String)request.getParameter("hospitalcode");
		String uid=(String)request.getParameter("uid");
		Object temp []=null;
		String temp1="";
		//System.out.println("welcome to ajax"+uid);
		//user.setUserName(uid);
		//System.out.println("strGroup :"+strGroup.toString());
		//System.out.println("strSeat :"+strSeat.toString());
		//System.out.println("strTable :"+strTable.toString());
		temp1=user.getLeftColumnComboForAjax(strGroup, strSeat, strTable,hospitalcode,strRightColumnList,strAlphabet,strModule);
		response.getWriter().write(temp1);
	}
	
	if(mode.equals("SEAT"))
	{
		String option="";
		//System.out.println("welcome to SEAT");
		usermgmt.masters.UmgmtRoleSeatTableMstBean user=new UmgmtRoleSeatTableMstBean(); 
		String gid=(String)request.getParameter("gid");
		//System.out.println("welcome to ajax"+gid);	
		/* user.setGroupName(gid);
		option=user.seatOptions(); */
		response.getWriter().write(option);
	}
	%>