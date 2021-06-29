package startup;
import startup.userInfo;
import java.util.*; 

public class userInfoList {
	List userList = null;
	userInfoList()
	{		
		userList = new ArrayList();
	}
	public void add(String seatId,String IPAddress)
	{
		userInfo obj = new userInfo();
		obj.setSeatId(seatId);
		obj.setIPAddress(IPAddress);
		userList.add(obj);		
	}
	public boolean contains(String seatId)
	{
		boolean retValue = false;
		userInfo obj = null;
		if(userList!=null)
		{
			for(int i=0;i<this.userList.size();i++)
			{
				obj = (userInfo)userList.get(i);
				String tempSeatId = obj.getSeatId();
				if(tempSeatId.equals(seatId))
				{
					retValue = true;
					break;
				}	
			}
		}
		return retValue;	
	}
	public void remove(String seatId)
	{	
		List tempList = new ArrayList();
		userInfo obj = null;
		if(userList!=null)
		{
			for(int i=0;i<this.userList.size();i++)
			{
				obj = (userInfo)userList.get(i);
				String tempSeatId = obj.getSeatId();
				if(!tempSeatId.equals(seatId))
				{
					tempList.add(obj);
				}	
			}
		}
		userList = tempList;		
	}
	public int size()
	{		
		return this.userList.size();
	}
	public userInfoList get(int location) throws Exception
	{
		userInfoList obj = (userInfoList)this.userList.get(location);
		return obj;		
	}
	public String getFirstIPAddress(String seatId)
	{
		String retValue = "";
		userInfo obj = null;
		if(userList!=null)
		{
			for(int i=0;i<this.userList.size();i++)
			{
				obj = (userInfo)userList.get(i);
				String tempSeatId = obj.getSeatId();
				if(tempSeatId.equals(seatId))
				{
					retValue = obj.getIPAddress();
					break;
				}	
			}
		}
		return retValue;	
	}
	
	
	

}
