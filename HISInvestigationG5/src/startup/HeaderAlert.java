package startup;


public class HeaderAlert 
{
	static boolean flag = true;
	public String getAlert()
	{
		String str = "";
		//HisResultSet rs;
		String query = "";
		
		try
		{
			query = "";
			//rs = getRecord(query);
			//str = rs.getString(1);
			if(flag)
			{
				str="1";
				flag=false;
			}
			else
			{
				str="0";
				flag=true;
			}
			
		}
		catch(Exception e)
		{
			System.out.println("error is "+e);
		}
		return str;
		
	}
	
}