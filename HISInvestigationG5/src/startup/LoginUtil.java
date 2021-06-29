package startup;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import startup.UserFactory.User;
import hisglobal.Status;

public class LoginUtil 
{	
	public static boolean login(HttpServletRequest request)
	{
		boolean retVal= false;
		Status statusObj = new Status();
		String ipNumber = "";
		try
		{	
			
			LoginData LDObj = new LoginData();
			UserFactory UFObj = UserFactory.createUserFactory();
			User userObj = UFObj.getUser(request.getParameter("uid"));		
			if(LDObj.isValid(userObj.getUserVO(),request.getParameter("pwd")))
			{
				ipNumber = userObj.loggedInIP();
				if(ipNumber == null)
				{
					LDObj.register(request.getRemoteAddr(),userObj.getUserVO());					
					userObj.register(request.getRemoteAddr());
					HttpSession session = request.getSession();
					
					session.setAttribute("userVO",userObj.getUserVO());
					session.setAttribute("SEATID",userObj.getUserVO().getSeatId());
					session.setAttribute("EMPID",userObj.getUserVO().getUserEmpID());
					session.setAttribute("userId",userObj.getUserVO().getUserId());
					session.setAttribute("userName",userObj.getUserVO().getUserName());
					
					request.setAttribute("SEATID",userObj.getUserVO().getSeatId());
					request.setAttribute("EMPID",userObj.getUserVO().getUserEmpID());
					request.setAttribute("userId",userObj.getUserVO().getUserId());
					request.setAttribute("userName",userObj.getUserVO().getUserName());
					
					retVal = true;
				}
				else
					throw new HisUserAlreadyLoggedInException();
			}
			else
				 throw new HisInvalidUserNameException();
			
		}
		catch(HisInvalidUserNameException e1)
		{
			statusObj.add(Status.LOGININVALIDUID,"Invalid User Name or Password","");			
		}
		catch(HisUserAlreadyLoggedInException e2)
		{	
			statusObj.add(Status.LOGINCONFLICT,"User is Already Logged IN on IP Addresss "+ipNumber,"");
		} 
		catch(HisLoginInsertFailed e3)
		{
			statusObj.add(Status.LOGININSERTFAILED,"Insertion Failed","");
		}
		catch(HisRegisterUserException e4)
		{
			statusObj.add(Status.LOGINFAILED,"Login Failed","");
		}
		catch(Exception e)
		{
			System.out.println("error is "+e);
			statusObj.add(Status.LOGINFAILED,"Login Failed","");
		}
		finally
		{
			request.setAttribute("statusobject",statusObj);
		}
		return retVal;
	}
	
	public static boolean logout(HttpServletRequest request)
	{
		boolean retVal= true;
		Status statusObj = new Status();
		try
		{
			HttpSession session = request.getSession();
			LoginData LDObj = new LoginData();
			LDObj.logoutUser(request.getRemoteAddr(),(UserVO)session.getAttribute("userVO"));
			UserFactory UFObj = UserFactory.createUserFactory();
			User userObj = UFObj.getUser((UserVO)session.getAttribute("userVO"));
			userObj.logout();
			session.invalidate();
		}
		catch(HisLogoutUpdateFailed e1)
		{
			statusObj.add(Status.LOGOUTUPDATEFAILED,"Updation Failed","");
			retVal= false;
		}
		catch(HisLogOutUserException e2)
		{
			statusObj.add(Status.FAILURE,"Error while LogOut","");
			retVal= false;
		}
		catch(Exception e)
		{
			System.out.println("error is "+e);
			retVal= false;
		}
		return retVal;
	}
	
}