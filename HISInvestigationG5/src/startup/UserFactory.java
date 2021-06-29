package startup;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class UserFactory
{
	private static Map map = null;
	private static UserFactory uf = null;
		
	private UserFactory()
	{
		map = Collections.synchronizedMap(new HashMap());
	}
	
	public static UserFactory getInstance()
	{
		if(uf==null)
			uf = new UserFactory();
		return uf;
	}
	
	public static UserFactory createUserFactory()
	{
		uf = getInstance();
		
		return uf;
	}
	
	public User getUser(String userName)
	{		 
		User userobj = new User();
		UserVO obj = userobj.createUserVO(userName);
		
		return userobj;
	}
	
	public User getUser(UserVO objUserVO)
	{
		User userObj = new User(objUserVO);
		return userObj;
	}
	
	public class User	//////inner class
	{		
		UserVO objUserVO = null;
		private User(){}
		
		private User(UserVO obj)
		{
			this.objUserVO = obj;
		}
		
		private UserVO createUserVO(String userName)
	   	{
			objUserVO = new UserVO();
			objUserVO.setUserName(userName);
			
			return objUserVO;
	   	}
		
		public UserVO getUserVO()
		{
			if(this.objUserVO == null)
				return new UserVO();
			else
				return this.objUserVO;
		}	
		
		public boolean isLoggedIn()
		{
			boolean retVal = false;
			try
			{
				if(!map.isEmpty())
				{
					retVal = map.containsKey(objUserVO);
				}
			}
			catch(Exception e)
			{
				System.out.println("error is "+e);
			}			
			if(retVal)
				throw new HisUserAlreadyLoggedInException();			
			return retVal;	
		}
		
		public String loggedInIP()
		{
			String ipAdd = null;
			if(!map.isEmpty())
			{
				if(map.containsKey(objUserVO))
					ipAdd = (String)map.get(objUserVO);
			}
			return ipAdd;
		}
		
		public void register(String ipAddr)
		{
			try
			{
				map.put(this.objUserVO,ipAddr);
			}
			catch(Exception e)
			{
				throw new HisRegisterUserException();
			}
		}
		
		public void logout()
		{
			try
			{
				map.remove(this.objUserVO);
			}
			catch(Exception e)
			{
				throw new HisLogOutUserException();
			}
		}
	}
}