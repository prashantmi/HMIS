package usermgmt.masters;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class umgmtSessionMgtConfigUtil {
	public List getPreviousValues() throws Exception 
	{
		List lst = new ArrayList();		
		lst = (new umgmtSessionConfigXmlHandler()).getConfigValues();		
		return lst;		
	}
	
	public List getPreviousValues(String hoscode) throws Exception 
	{
		List lst = new ArrayList();		
		lst = (new umgmtSessionConfigXmlHandler()).getConfigValues(hoscode);		
		return lst;		
	}
	
	public boolean updateValues(Map valueMap)  
	{
		
		boolean status = true;
		try
		{			
			new umgmtSessionConfigXmlHandler().updateFile(valueMap);
		}
		catch(Exception e)
		{
			System.out.println("Exception in updateValues "+e);
			status = false;
			
		}
		return status;		
	}
	


}
