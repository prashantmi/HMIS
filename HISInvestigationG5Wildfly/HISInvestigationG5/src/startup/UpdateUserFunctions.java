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

public class UpdateUserFunctions extends UpdateUser
{
	
	
//------------------Function used for getting all the roles list assigned to a user------------------------
	public  static String  getRolesList(HttpServletRequest request)
	{
		StringBuffer rolesList=new StringBuffer();
		String roleIdConcate="(";
		int i=0;
		
		String[] roleIdArray=(String[])request.getSession().getAttribute(Config.LIST_OF_ROLES_ID);
		String[]roleNameArray=(String[])request.getSession().getAttribute(Config.LIST_OF_ROLES_NAME);
		
		String checkValueSelected=request.getParameter("roleId");
		String checkAllValueSelected=request.getParameter("allRoles");
		
		rolesList.append("<div align=\"left\"><font  size=\"2\" face=\"Verdana, Arial, Helvetica, sans-serif\">");
		for(i=0 ;i <roleIdArray.length ; i++ )
		{
			
			rolesList.append(roleNameArray[i]);
			
			
		if( checkValueSelected!=null && checkValueSelected.equals(roleIdArray[i]) )
			rolesList.append("<input type=\"radio\" name=\"role\" checked=\"checked\" id=\""+i+"\" value=\""+roleIdArray[i]+"\" onclick=\"getRoleMenus(this)\" >");
		else
			rolesList.append("<input type=\"radio\" name=\"role\" id=\""+i+"\" value=\""+roleIdArray[i]+"\" onclick=\"getRoleMenus(this)\" >");
		
		
		
		
			roleIdConcate+=roleIdArray[i]+",";
		}
	 	
		roleIdConcate=roleIdConcate.substring(1,roleIdConcate.length()-1)+")";
				
		rolesList.append("All");
		
	if(checkAllValueSelected!=null && !checkAllValueSelected.equals(""))
		rolesList.append("<input type=\"radio\" name=\"role\"  checked=\"checked\" id=\""+i+"\" value=\""+roleIdConcate+"\" onclick=\"getAllMenus(this)\" >");
	else
		rolesList.append("<input type=\"radio\" name=\"role\"   id=\""+i+"\" value=\""+roleIdConcate+"\" onclick=\"getAllMenus(this)\" >");
		
		rolesList.append("</font>");
		rolesList.append("</div>");
	 	
		
		return rolesList.toString();
		
	}
	
	
	
	
//------------------Function Used for getting all the menus corresponding to all the roles  assigned to a user--------- 
	
	public static RequestDispatcher  getAllMenusForAllRoles(HttpServletRequest request,RequestDispatcher rd )
	{
		
		
		login loginObj = new login();
		
		String roleId=request.getParameter("allRoles");
		String hospitalCode=(String)request.getSession().getAttribute("HOSPITAL_CODE");
		
		String allMenusList=loginObj.getUserAllMenus(roleId, hospitalCode);
	WebUTIL.setAttributeInSession(request,Config.LIST_OF_MENUS, allMenusList);
		
		String rolesList=UpdateUserFunctions.getRolesList(request);				
	WebUTIL.setAttributeInSession(request,Config.USER_ROLES_LIST, rolesList);
			
		
		request.setAttribute("message"," ");
		rd = request.getRequestDispatcher("../startup/getRolesAndPrivelages.jsp");
		
		return rd;
	}
	

	
//----------------Function Used for getting  menus corresponding to a single  role assigned to a user------------- 
	
	public static RequestDispatcher  getMenusForSingleRoleAssigned(HttpServletRequest request,RequestDispatcher rd )
	{
		login loginObj = new login();
		
		String roleId=request.getParameter("roleId");
		String hospitalCode=(String)request.getSession().getAttribute("HOSPITAL_CODE");
		
		String menusList=loginObj.getUserMenus(roleId, hospitalCode);
	WebUTIL.setAttributeInSession(request,Config.LIST_OF_MENUS, menusList);
		

		
		String rolesList=UpdateUserFunctions.getRolesList(request);
	WebUTIL.setAttributeInSession(request,Config.USER_ROLES_LIST, rolesList);		
		
		request.setAttribute("message"," ");
		rd = request.getRequestDispatcher("../startup/getRolesAndPrivelages.jsp");
		
		return rd;
	}
	
//-------------------Function Used for getting  roles and Privelages  Assigned to a user----------------- 
	
	public static RequestDispatcher  getRolesAndPrivelagesAssigned(HttpServletRequest request,RequestDispatcher rd )
	{
		
		WebUTIL.refreshTransState(request);
		login loginObj = new login();
					
		String userId=(String)request.getSession().getAttribute("USER_ID");
		String hospitalCode=(String)request.getSession().getAttribute("HOSPITAL_CODE");
		
		Map EssentialMap=new HashMap();
		
		EssentialMap=loginObj.getUserRolesAndPrivelages(userId, hospitalCode);
		WebUTIL.setMapInSession(EssentialMap,request);
		
		
		String rolesList="";
		
	
if(((String[])EssentialMap.get(Config.LIST_OF_ROLES_ID)).length > 1 )		
	{	
	
	rolesList=UpdateUserFunctions.getRolesList(request);
if(rolesList!=null)
	request.getSession().setAttribute(Config.USER_ROLES_LIST, rolesList);

	}
	
	
		
		request.setAttribute("message"," ");
		rd = request.getRequestDispatcher("../startup/getRolesAndPrivelages.jsp");
		
		return rd;
	}
	
//---------------------Function Used for Authenticating the  user-------------------------- 
	
	public static RequestDispatcher  AuthenticateUser(HttpServletRequest request,RequestDispatcher rd )
	{
		
		login loginObj = new login();
		
		int validUser=loginObj.oldPasswordCheck(request);
		//System.out.println("validUser IN AUTHENTICATE_PASSWORD---"+validUser);
		loginObj.hintQuestions(request);
		
		 
		if(validUser==1)
		{
			request.setAttribute("message"," ");
			loginObj.hintQuestions(request);
			
			rd = request.getRequestDispatcher("../startup/changeUserDetails.jsp?seatId="+request.getSession().getAttribute("SEATID")+"&empId="+loginObj.getEmpId());
			
			}else
			{
			   request.setAttribute("message","Wrong Password Entered");
			   rd = request.getRequestDispatcher("../startup/authenticatePassword.jsp");
			       
		    }
		
		return rd;
	}
	
//---------------------Function Used for changing the  user  Details after Authenticating the user------------ 
	
	public static RequestDispatcher  ChangeUserDetails(HttpServletRequest request,RequestDispatcher rd )
	{
		login loginObj = new login();
		loginObj.hintQuestions(request);
		
	
        String oldValue="GSTR_USR_NAME"+"^"+request.getSession().getAttribute("UserFullName")+"#"+
        				"GNUM_QUESTION_ID"+"^"+request.getSession().getAttribute("QUESTION_ID")+"#"+	 
        				"GSTR_HINT_ANSWER"+"^"+request.getSession().getAttribute("ANSWER")+"#"+
        				"GNUM_MOBILE_NUMBER"+"^"+request.getSession().getAttribute("MOBILE_NO")+"#"+	 
        				"GSTR_EMAIL_ID"+"^"+request.getSession().getAttribute("EMAIL_ID");
        
       // System.out.println("oldValue in controller ---"+oldValue);
        
		int result=0;
		result=loginObj.updateUserDetails(request);
		if(result >0)
		{
			request.getSession().setAttribute("MOBILE_NO", request.getParameter("mobileNo"));
			request.getSession().setAttribute("EMAIL_ID", request.getParameter("emailId"));
			
			int auditlog=loginObj.updateAuditLog(request,oldValue);
			//System.out.println("auditlog ---"+auditlog);
			
			rd = request.getRequestDispatcher("../startup/changeUserDetails.jsp");
			request.setAttribute("message"," Your Details Have Been Updated ");	
		}else
		{
			rd = request.getRequestDispatcher("../startup/changeUserDetails.jsp");
			request.setAttribute("message"," Your Details Have Not Been Updated ");		
		}
		
		
		return rd;
	}
	
//-------------------Function Used for changing the  user Password while  Authenticating the user------------ 
	
	public static RequestDispatcher  ChangePassword(HttpServletRequest request,RequestDispatcher rd )
	{
		login loginObj = new login();
		
		String oldValue="GSTR_USR_NAME"+"^"+request.getSession().getAttribute("UserFullName")+"#"+
         				"GNUM_QUESTION_ID"+"^"+request.getSession().getAttribute("QUESTION_ID")+"#"+	 
         				"GSTR_HINT_ANSWER"+"^"+request.getSession().getAttribute("ANSWER")+"#"+
         				"GNUM_MOBILE_NUMBER"+"^"+request.getSession().getAttribute("MOBILE_NO")+"#"+	 
         				"GSTR_EMAIL_ID"+"^"+request.getSession().getAttribute("EMAIL_ID");
					
						int result=loginObj.updatePasswordOnly(request);
						
		
	//	System.out.println("oldValue in controller ---"+oldValue);
		
	//	System.out.println("result in controller ---"+result);
		
						if(result==0)
						{
						  					   
						   request.setAttribute("message"," Old Password is Wrong");
						   rd = request.getRequestDispatcher("../startup/changePassword.jsp");
						   
						  
						}
						else{
							request.setAttribute("message","Your Password has been Changed");   												
							rd = request.getRequestDispatcher("../startup/changePassword.jsp");
							
							int auditlog=loginObj.updateAuditLog(request,oldValue);
						//	System.out.println("auditlog2 ---"+auditlog);
						    }
		
		
		return rd;
	}
	
//------------------Function Used for getting all the menus corresponding to all the roles  assigned to a user--------- 
	
	public static RequestDispatcher  getAllLowLevelUsersForTheUserGroup(HttpServletRequest request,RequestDispatcher rd )
	{
		WebUTIL.refreshTransState(request);
		
		login loginObj = new login();
		login[] loginObjArray=null;
		
		
		String userId=(String)request.getSession().getAttribute("USER_ID");
		String hospitalCode=(String)request.getSession().getAttribute("HOSPITAL_CODE");
		
		
		loginObjArray=loginObj.getAllLowLevelUsersForTheUserGroup(userId, hospitalCode);
		 
		
		
		 WebUTIL.setAttributeInSession(request,Config.LIST_OF_ALL_LOW_LEVEL_USERS_FOR_THE_GROUP, loginObjArray);
		
					
		
		request.setAttribute("message"," ");
		rd = request.getRequestDispatcher("../startup/resetPassword.jsp");
		
		return rd;
	}
	
	
//------------------Function Used for getting all the menus corresponding to all the roles  assigned to a user--------- 
	
	public static RequestDispatcher  resetPassword(HttpServletRequest request,RequestDispatcher rd )
	{
			
		login loginObj = new login();
				
		
		String userId=(String)request.getParameter("userIdToReset");
		String hospitalCode=(String)request.getSession().getAttribute("HOSPITAL_CODE");
		String superUserId=(String)request.getSession().getAttribute("USER_ID"); 
		
		
			  String pwd=Config.RESET_PASSWORD;	
			  String encryptPassword= SecurityUtil.encrypt(pwd);
			
			
		
		
		int result =loginObj.resetPassword(superUserId,userId, hospitalCode,encryptPassword);
		 
		
				
	if(result > 0)
		request.setAttribute("message","Your Password has been Reset ");
	else
		request.setAttribute("message","Your Password has not been Reset ");
	
		rd = request.getRequestDispatcher("../startup/resetPassword.jsp");
		
		return rd;
	}
	
}
