package usermgmt.masters;
import usermgmt.*;
import java.sql.*;
import java.util.*;

import HisGlobal.*; 


abstract class UserFunc extends HisGlobal.HisMethods
{
	public String getNextNo(String colName,String tableName,String defValue)
	{
		String next=null;
		String query="SELECT NVL(MAX("+colName+"),"+defValue+") "+
					 "FROM "+tableName;
		
		System.out.println("Query "+query);
		try
		{
			HisResultSet rs= getRecord(query);
			
			if(rs.next())
				next=rs.getString(1);
			
			rs.close();
		}
		catch(Exception e)
		{
			System.out.println("Exception in getNextNo of UserFunc "+e);
		}
		
		return next;
	}//End of getNextNo
	
	public String getSysDate()
	{
		try
		{
			HisResultSet rs=getRecord("SELECT TO_CHAR (SYSDATE,'dd-Mon-YYYY') "+
									"FROM DUAL");
								
			if(rs.next())
				return rs.getString(1);
		}
		catch(Exception e)
		{
			System.out.println("Exception in getNextNo of UserFunc "+e);
		}
		
		return null; 
	}//End of getSysdaye
	
	
	public List getDetail(String query)throws Exception
	{
		int i=1;
		List AL_Detail = new ArrayList();

		//SQLManager myMan = getSqlManager();
		Connection conn1  = super.getConnection();	
		Statement st = conn1.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
	    ResultSet rs = st.executeQuery(query);
		//
		ResultSetMetaData rsmd = rs.getMetaData();
		int noOfColumn = rsmd.getColumnCount();
		
		if (rs == null) return AL_Detail;
		if(rs.next())
		{
			while( i <= noOfColumn )
			{
				AL_Detail.add((rs.getString(i) == null)?"":rs.getString(i).trim());
				i++;
			}
		}
		
		
		rs.close();
		st.close();
		super.closeConnection(conn1);
		//System.out.println("out Get Details");
		return AL_Detail;
		
	}//End of getDetail
public String getDatePicker(String formName, String fieldName, String datevalue)
	{
			String dateString = new String("");
			dateString += "<input type=text name=" + fieldName +" size=11 maxlength=11 value="+datevalue+">";
			dateString += "<A HREF=# onClick=if(oDP)oDP.open(" + formName + "." + fieldName + ");return false;><IMG SRC=iconPicDate.gif BORDER=0 WIDTH=16 HEIGHT=15 ALT=Pick a date></A><BR>";
			return dateString;
	}//11
	
	public String getCombo(String comboName, String query,String selectedValue, String action, int combo)throws Exception
	{

		String html  = new String("");
		int i = 0;
		String value = "";
		String text  = "";

		List AL_Data = new ArrayList();

		HisResultSet rs = getRecord(query);
		if (rs == null)
			return html;

		while(rs.next())
		{

			value = (rs.getString(1)).trim();
			text  = (rs.getString(2)).trim();

			AL_Data.add(value);
			AL_Data.add(text);

		}

		rs.close();

		//html tag starts from here
		if (combo == 1)			//combo required
		{
			if (action == "")
				html = html + "<select name = " + comboName + "  tabindex=\"0\" style=\"font-family:Verdana; font-size:12px;font-style:normal;text-decoration:none;height:20px;width:170px;\">";
			else
				html = html + "<select name = " + comboName + " " + action + " tabindex=\"0\" style=\"font-family:Verdana; font-size:12px;font-style:normal;text-decoration:none;height:20px;width:170px;\">";
		}

		html = html + "<option selected value = 0>Select Value</option>";

		for( i = 0; i < AL_Data.size()-1; i+=2 )
		{
			if(selectedValue != "" &&  selectedValue.compareTo((String)AL_Data.get(i)) == 0)
				html = html + "<option selected value =\""+ AL_Data.get(i)+"\">" + AL_Data.get(i+1) + "</option>";
			else
				html = html + "<option value =\""+AL_Data.get(i)+"\">" + AL_Data.get(i+1) + "</option>";
		}

		if (combo == 1)
			html = html + "</select>";
		return html;
	
	}//2
	
	public boolean isRedundentName(String query)
		{
			HisResultSet rs=null;
			boolean result=false;
			try
			{
				rs=super.getRecord(query);
				if( rs != null )
				{	
					if(rs.next())
					{
						result=true; 
					}
					else 
					{
						result = false ;
					}
				}
				else
				{
					result=false; 
				}
			}
			
			catch(Exception e)
			{
				result=true;
				System.out.println("Exception is "+e);
			}
			
			return result;
		}

	 public boolean isRecordExist(String query,int comboStatus)throws Exception
	   {
	   
	    HisResultSet rs   = null;
	    boolean result   = false;
	    int isStatusValid  = 0;// if comboStatus ==1 then selected combo is 'Active' ; if comboStatus==2 then  selected combo is 'InActive'
	    try 
	    {
	    	rs = super.getRecord(query);
	    	if( rs != null )
	    	{ 
	    		if(rs.next())
	    		{
	    			isStatusValid = Integer.parseInt(rs.getString(1));//rs.getString() contains the value of GNUM_ISVALID
	    			if(isStatusValid==comboStatus)
	    			{
	    				result=true;
	    			}
	    			else 
	    			{
	    				result=false;
	    			}
	    			rs.close();
	    			rs = null;
	           }
	           else 
	           {
	        	   result = false ;
	           }
	    	}
	    	else
	    	{
	    		result=false;
	    
	    	}
	    }
	    catch(Exception e)
	    {
	    	result=false;
	    	System.out.println("Exception inside isRecordExist = "+e);
	    	e.printStackTrace();
	    }
	     return result;
	  }


}