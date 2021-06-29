
package startup;


import javax.servlet.*;
import javax.servlet.http.*; 

import java.util.*;

import hisglobal.exceptions.HisException;
import hisglobal.hisconfig.Config;
import hisglobal.presentation.WebUTIL;
import hisglobal.transactionmgnt.HisDAO;
import hisglobal.utility.HisUtil;
import java.security.MessageDigest;
import javax.sql.rowset.WebRowSet;


public class UpdateUser extends HttpServlet {

	public void init(ServletConfig config) throws ServletException
	{
		//System.out.println("in the init of loginaction");
		super.init(config);
	}
		
	public void doGet(HttpServletRequest request,HttpServletResponse response)
	{
		//System.out.println("in the doget of loginaction");
		this.doPost(request,response);
	}    
   
	public void doPost(HttpServletRequest request,HttpServletResponse response)
	
	{
		//System.out.println("in the dopost of loginaction");
		RequestDispatcher rd = null;
		
		try	{
			
			String hmode =	request.getParameter("hmode");
			//System.out.println("hmode in UpdateUser servlet is ------->"+hmode);
		
			
			if(hmode.equals("PASSWORD_IS_RESET"))
			   {
			  	rd=UpdateUserFunctions.resetPassword(request, rd);
				 rd.forward(request,response);							
			   }
			 else
			if(hmode.equals("RESET_PASSWORD"))
			   {
			  	rd=UpdateUserFunctions.getAllLowLevelUsersForTheUserGroup(request, rd);
				 rd.forward(request,response);							
			   }
			 else
			if(hmode.equals("GET_ALL_MENUS"))
			   {
			  	rd=UpdateUserFunctions.getAllMenusForAllRoles(request, rd);
				 rd.forward(request,response);							
			   }
			 else
			if(hmode.equals("GET_MENUS"))
			   {
				rd=UpdateUserFunctions.getMenusForSingleRoleAssigned(request, rd);
				 rd.forward(request,response);		 
					
			}else
			if(hmode.equals("GET_ROLES_AND_PRIVELAGES"))
			   {
			
				 rd=UpdateUserFunctions.getRolesAndPrivelagesAssigned(request, rd);
				 rd.forward(request,response);
				 
					
			}else
			if(hmode.equals("CHANGE_USER_DETAILS"))
			   {
				login loginObj = new login();	
				
				request.setAttribute("message"," ");
				rd = request.getRequestDispatcher("../startup/authenticatePassword.jsp");
				 rd.forward(request,response);
				 
					
			}else
				if(hmode.equals("CHANGE_PASSWORD"))
				   {
					login loginObj = new login();				
					request.setAttribute("message"," ");
		    	rd = request.getRequestDispatcher("../startup/changePassword.jsp?seatId="+request.getSession().getAttribute("SEATID")+"&empId="+loginObj.getEmpId());
        	    rd.forward(request,response);
					 						
				}else
				if(hmode.equals("AUTHENTICATION_CHECK"))
			   {
				 rd=UpdateUserFunctions.AuthenticateUser(request, rd);			
				 rd.forward(request,response);
				
			}else
		if(hmode.equals("CHANGE_USER_DETAILS_CHECK"))
			{
				rd=UpdateUserFunctions.ChangeUserDetails(request, rd);			
				rd.forward(request,response);				
			}else
		if(hmode.equals("CHANGE_PASSWORD_CHECK"))
		   {
				rd=UpdateUserFunctions.ChangePassword(request, rd);
				 rd.forward(request,response);
			}
			
			
			
			
		}//try closed 
catch(Exception e)
		{
				
		//System.out.println("Error in Controller page >> : "+e);
		e.printStackTrace();
			
		  }			
finally  {
		 rd = null;
		  }
   
}//do Post closed 
	
	
}//Class closed 
