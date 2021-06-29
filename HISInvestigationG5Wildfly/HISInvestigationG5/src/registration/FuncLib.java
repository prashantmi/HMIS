/*	
*	Trimmed funcLib() created by Ashish Nayyar for Registration Module of Chandigarh.
*	
*	NOT TO BE CHANGED BY ANY ONE UNDER ANY CIRCUMSTANCES WITHOUT THE CONSENT OF THE DEVELOPER.
*
*				WARNING  :: this is NOT the orignal funcLib()
*	Last Modified on :: 13-Sept-2004	
*/

package registration;

import  hisglobal.*;
import hisglobal.persistence.HisResultSet;

import java.util.*;
import java.sql.ResultSet;
import java.sql.*;

public class FuncLib extends hisglobal.utility.HisMethods
{

	public List getDetails(String tname,String codefield,String namefield)throws Exception
	{
		List l1=new ArrayList();
		String query="Select "+codefield+","+namefield+
					" from "+tname+
					" order by "+namefield;

		HisResultSet rs=getRecord(query);
		while(rs.next())
		{
			l1.add(rs.getString(1));
			l1.add(rs.getString(2));
		}
		rs.close();
		return l1;
	}//1
	
	public List getDetails(String tname,String namefield)throws Exception
	{
		List l1=new ArrayList();
		String query="Select "+namefield+
					" from "+tname+
					" order by "+namefield;


		HisResultSet rs=getRecord(query);
		while(rs.next())
		{
			l1.add(rs.getString(1));

		}
		rs.close();
		return l1;
	}//2

	public List getDetails(String query,int noofcol)throws Exception
	{
		List l1=new ArrayList();
		HisResultSet rs=getRecord(query);
		while(rs.next())
		{
			for(int i=1;i<=noofcol;i++)
				l1.add(rs.getString(i));

		}
		rs.close();
		return l1;
	}//3
	

	public String getCombo(String comboName, String query,String selectedValue, String action, int combo)throws Exception
	{
		System.out.println("inside funclib sel val is "+selectedValue);

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
				html = html + "<select name = " + comboName + ">";
			else
				html = html + "<select name = " + comboName + " " + action + ">";
		}

		html = html + "<option selected value = 0>Select Value</option>";

		for( i = 0; i < AL_Data.size()-1; i+=2 )
		{
			if(selectedValue != "" &&  selectedValue.compareTo((String)AL_Data.get(i)) == 0)
				html = html + "<option selected value = \"" + AL_Data.get(i)+"\">" + AL_Data.get(i+1) + "</option>";
			else
				html = html + "<option value = \"" + AL_Data.get(i)+"\">" + AL_Data.get(i+1) + "</option>";
		}

		if (combo == 1)
			html = html + "</select>";

	return html;
	}//2

	public String createCombo(String comboname,String query,String displayoption,String selectedval,String funclist)throws Exception
	{
		String html="";
		try
		{
			html=html+"<select class=cbo2 name="+comboname+" "+funclist+">";
			if(!displayoption.equals(""))
			{
				html+="<option value=0>"+displayoption+"</option>";
			}
			HisResultSet rs=getRecord(query);
			while(rs.next())
			{
				if(selectedval.equals(rs.getString(1)))
					html=html+"<option selected value="+rs.getString(1)+">"+rs.getString(2)+"</option>";
				else
					html=html+"<option value="+rs.getString(1)+">"+rs.getString(2)+"</option>";
			}
			html=html+"</select>";
			rs.close() ; // P.dixit
		}
		catch(Exception e)
		{
			System.out.println("Exception in createCombo() "+query);

		}
		return html;
	}
	public String getNextNo(String field, String table, String def)throws Exception
	{
		String query = "SELECT MAX(" + field + ") FROM " + table;
		String nextNo = "";

		HisResultSet rs = getRecord(query);
		rs.next();

		nextNo = rs.getString(1);
		if(nextNo != null)
			nextNo = String.valueOf(Integer.parseInt(nextNo)+ 1);
		else
			nextNo = def;

		rs.close();

	return nextNo;
	}//8

	public String createList(String comboname,String query,String displayoption,String selectedval,String funclist)throws Exception
	{
		String html="";
		try
		{
			html=html+"<select class=cbo3 name="+comboname+" "+funclist+" multiple size=\"10\">";
			if(!displayoption.equals(""))
			{
				html+="<option value=0>"+displayoption+"</option>";
			}
			HisResultSet rs=getRecord(query);
			while(rs.next())
			{
				if(selectedval.equals(rs.getString(1)))
					html=html+"<option selected value="+rs.getString(1)+">"+rs.getString(2)+"</option>";
				else
					html=html+"<option value="+rs.getString(1)+">"+rs.getString(2)+"</option>";
			}
			html=html+"</select>";
		rs.close() ; // P.dixit
		}
		catch(Exception e)
		{
			System.out.println("Exception in createCombo() "+query);

		}
		return html;
	}

	public java.sql.Date getSqlDate(String strdate)
	{
		int day=0;
		int month=0;
		int year=0;

		String[] str={"jan","feb","mar","apr","may","jun","jul","aug","sep","oct","nov","dec"};

		StringTokenizer st = new StringTokenizer(strdate,"-");

		day=Integer.parseInt((String)st.nextToken());

		String tempstr=st.nextToken();
		for(int i=0;i<12;i++)
		{
			if(str[i].equals(tempstr.toLowerCase()))
			{
				month=i+1;
				break;
			}
		}

		year=Integer.parseInt((String)st.nextToken());

		String datenew=String.valueOf(year)+"-"+String.valueOf(month)+"-"+String.valueOf(day);

		java.sql.Date dt=new java.sql.Date(111111);
		dt=dt.valueOf(datenew);
		return dt;
	}//9

	public String getDatePicker(String formName, String fieldName)
	{
		String dateString = new String("");
		dateString += "<input type=text name=" + fieldName +" size=11 maxlength=11>";
		dateString += "<A HREF=# onClick=if(oDP)oDP.open(" + formName + "." + fieldName + ");return false;><IMG SRC=iconPicDate.gif BORDER=0 WIDTH=16 HEIGHT=15 ALT=Pick a date></A><BR>";
		return dateString;
	}//10

	public String getDatePicker(String formName, String fieldName, String datevalue)
	{
			String dateString = new String("");
			dateString += "<input type=text name=" + fieldName +" size=11 maxlength=11 value="+datevalue+">";
			dateString += "<A HREF=# onClick=if(oDP)oDP.open(" + formName + "." + fieldName + ");return false;><IMG SRC=iconPicDate.gif BORDER=0 WIDTH=16 HEIGHT=15 ALT=Pick a date></A><BR>";
			return dateString;
	}//11

	public String toInitCap(String mystring)
	{
		StringTokenizer st = new StringTokenizer(mystring," ");
		String temp1="";
		String temp="";
		String newstr="";
		while(st.hasMoreTokens())
		{
			temp = st.nextToken();
			temp1 = temp.substring(0,1).toUpperCase();
			temp1=temp1+temp.substring(1).toLowerCase() ;
			newstr=newstr+temp1+" ";
		}

		return newstr;
	}//12

	public String colorSwitch(String color1,String color2,String currentcolor)
	{
		if(currentcolor.equals(color1))
			return color2;
		else
			return color1;
	}//13

	
		//These functions are added by Ashish and shd not be changed under any circumstances

		public String getCurrentDate()throws Exception
		{
			String query="SELECT TO_CHAR(SYSDATE,'dd-Mon-yyyy') FROM DUAL";
			return getValue(query);
		}
        public String getCurrentDateTime()throws Exception
		{
			String query="SELECT TO_CHAR(SYSDATE,'DD-MON-YYYY HH24:MM:SS') FROM DUAL";
			return getValue(query);
		}

		public String getCurrentMonth()throws Exception
		{
			String query="SELECT TO_CHAR(SYSDATE,'mm') FROM DUAL";
			return getValue(query);
		}

		public String getCurrentDay()throws Exception
		{
			String query="SELECT TO_CHAR(SYSDATE,'dd') FROM DUAL";
			return getValue(query);
		}
		public String getCurrentYear()throws Exception
		{
			String query="SELECT TO_CHAR(SYSDATE,'yy') FROM DUAL";
			return getValue(query);
		}
		public void drawChilds(String root,Connection c) throws Exception
		{
			String htmlStr="";
			Connection con=c;
			String q="SELECT HGNUM_PARAMETER_ID, HGSTR_PARAMETER, HGNUM_PARENT_ID, HGSTR_PARA_LEVEL_ID  FROM HGBT_EXAM_PARAMETER_MST  WHERE HGNUM_PARENT_ID ="+root+" AND HGSTR_PARA_LEVEL_ID <> 0";
			Statement stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery(q);

			//if the node has more children
			//System.out.println("Inside drawChild");
			if(noOfChild(root,con)!=0)
			{
				while(rs.next())	//iterate thru all children
				{
					for(int i=0;i<Integer.parseInt(rs.getString(4));i++)		//adjust the view acco to level

							System.out.print("  ");
						System.out.print(rs.getString(2)+"\n");		//display name
				 	drawChilds(rs.getString(1),con);					//find its next childern
				}
			}
		rs.close() ; // P.dixit
		stmt.close(); // P.dixit
		}

		private int noOfChild(String root,Connection c) throws Exception
		{
			Connection con=c;
			int res=0;
			String q="SELECT count(*) FROM HGBT_EXAM_PARAMETER_MST  WHERE HGNUM_PARENT_ID="+root+" AND HGSTR_PARA_LEVEL_ID <> 0";
			Statement stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery(q);
			if(rs.next())
				res= Integer.parseInt(rs.getString(1));
			rs.close();  // P.dixit
			stmt.close(); // P.dixit 

			return res;
		
		}
		
		public String getPrimaryKey(String tableName)
		{
			StringBuffer query	=	new StringBuffer();
				
				query.append(" SELECT A.COLUMN_NAME "); 
				query.append(" FROM USER_CONS_COLUMNS A,USER_CONSTRAINTS B "); 
				query.append(" WHERE B.TABLE_NAME LIKE '"+tableName+"' AND "); 
				query.append(" A.CONSTRAINT_NAME=B.CONSTRAINT_NAME AND ");
				query.append(" A.TABLE_NAME=B.TABLE_NAME AND ");
				query.append(" B.CONSTRAINT_TYPE='P' ");
				String hold = new String ("");
				try
				{
					HisResultSet rs	=	getRecord(query.toString());
					
					if(rs.next())
					{	 hold = rs.getString(1);
					     rs.close() ; // P.dixit
					}
					 
				}
				catch(Exception e)
				{
					System.out.println("Exception in getPrimaryKey() "+e);
				}
			
			return hold ; 
		}
		
		//These functions are added by Ashish
	public String getSysdate() throws Exception
	{
		String query = new String("");
		String retValue = new String("");
		String date = new String("");
		query = "SELECT TO_CHAR(SYSDATE,'DD-mon-YYYY') ";
		query += "FROM DUAL";

		HisResultSet rs = getRecord(query);
		rs.next();
		if (rs == null)
			return retValue;
		else
		{
				date = rs.getString(1);
				rs.close() ; // P.dixit
				return date ;
		}
		

	 }//22
	 
	public String getSysdate(String formate)
	{
		String 		query 		= 	new String("");
		String 		retValue 	= 	new String("");
		HisResultSet 	rs			=	null;
		try
		{
		    query = " SELECT TO_CHAR(SYSDATE,'"+formate+"') "+
					" FROM DUAL";

			rs = super.getRecord(query);			
			if (rs.next())
				retValue	=	rs.getString(1);
			else
				retValue	=	null;										
		}
		catch(Exception e)
		{
		    System.out.println("Exception "+e);
		}
		finally
		{
			rs.close();	
		}
		return retValue;
	 }//2

	public int getNext(String field, String table, int def)throws Exception
	{
		String query = "SELECT MAX(" + field + ") FROM " + table;
		int nextNo=0;

		HisResultSet rs = getRecord(query);
		rs.next();

		if(rs.getString(1) != null)
			nextNo = Integer.parseInt(rs.getString(1)) + 1;

		if(nextNo == 0)
			nextNo = def;

		rs.close();

	return nextNo;
	}//11
	
}//class

