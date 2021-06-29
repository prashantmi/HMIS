/*	
*	Trimmed funcLib() created by Ashish Nayyar for Registration Module of Chandigarh.
*	
*	NOT TO BE CHANGED BY ANY ONE UNDER ANY CIRCUMSTANCES WITHOUT THE CONSENT OF THE DEVELOPER.
*
*				WARNING  :: this is NOT the orignal funcLib()
*	Last Modified on :: 13-Sept-2004	
*/
package usermgmt;

import java.util.*;
import java.sql.*;

import HisGlobal.*;

public class FuncLib extends HisGlobal.HisMethods
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
			l1.add((Object)rs.getString(1));
			
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
	
	/*public String getField(String query)throws Exception
	{

		String str = "";

		HisResultSet rs = getRecord(query);
		if (rs == null) return str;
		//System.out.println("query="+query);
		if(rs.next()) str = rs.getString(1);
		rs.close();

	return str;
	}*/

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
				html = html + "<select name = " + comboName + ">";
			else
				html = html + "<select name = " + comboName + " " + action + ">";
		}

		html = html + "<option selected value = 0>Select Value</option>";

		for( i = 0; i < AL_Data.size()-1; i+=2 )
		{
			if(selectedValue != "" &&  selectedValue.compareTo( (String)AL_Data.get(i) ) == 0)
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
		}
		catch(Exception e)
		{
			System.out.println("Exception in createCombo() "+query);

		}
		return html;
	}
	public String getNextNo(String field, String table, String def,String hcode)throws Exception
	{
		//String query = "SELECT MAX(" + field + ") FROM " + table;
		
		
		
		String query = "SELECT MAX(" + field + ") FROM "+table+" where gnum_hospital_code="+hcode;
		
		
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
		//dt=dt.valueOf(datenew);
		dt = java.sql.Date.valueOf(datenew);
		
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
		/*public void drawChilds(String root,Connection c) throws Exception
		{
			String htmlStr="";
			Connection con=c;
			String q="SELECT HGNUM_PARAMETER_ID, HGSTR_PARAMETER, HGNUM_PARENT_ID, HGSTR_PARA_LEVEL_ID  FROM HGBT_EXAM_PARAMETER_MST  WHERE HGNUM_PARENT_ID ="+root+" AND HGSTR_PARA_LEVEL_ID <> 0";
			Statement stmt=con.createStatement();
			HisResultSet rs=stmt.executeQuery(q);

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
		}*/

		/*private int noOfChild(String root,Connection c) throws Exception
		{
			Connection con=c;
			int res=0;
			String q="SELECT count(*) FROM HGBT_EXAM_PARAMETER_MST  WHERE HGNUM_PARENT_ID="+root+" AND HGSTR_PARA_LEVEL_ID <> 0";
			Statement stmt=con.createStatement();
			HisResultSet rs=stmt.executeQuery(q);
			if(rs.next())
				res= Integer.parseInt(rs.getString(1));
			return res;
		}*/
		
		public String getPrimaryKey(String tableName)
		{
			StringBuffer query	=	new StringBuffer();
				
				query.append(" SELECT A.COLUMN_NAME "); 
				query.append(" FROM USER_CONS_COLUMNS A,USER_CONSTRAINTS B "); 
				query.append(" WHERE B.TABLE_NAME LIKE '"+tableName+"' AND "); 
				query.append(" A.CONSTRAINT_NAME=B.CONSTRAINT_NAME AND ");
				query.append(" A.TABLE_NAME=B.TABLE_NAME AND ");
				query.append(" B.CONSTRAINT_TYPE='P' ");
				
				try
				{
					HisResultSet rs	=	getRecord(query.toString());
					if(rs.next())
						return rs.getString(1);
				}
				catch(Exception e)
				{
					System.out.println("Exception in getPrimaryKey() "+e);
				}
			
			return ""; 
		}
		
		//These functions are added by Ashish
	public String getSysdate() throws Exception
	{
		String query = new String("");
		String retValue = new String("");

		query = "SELECT TO_CHAR(SYSDATE,'dd-Mon-YYYY') ";
		query += "FROM DUAL";

		HisResultSet rs = getRecord(query);
		rs.next();
		if (rs == null)
			return retValue;
		else
			return rs.getString(1);

	 }//22

public String populateCombo(String query, String selectedValue)throws Exception
	{
		String 		text		=	"";
		String 		value		=	"";
		String 		html		=	"";
		HisResultSet	rs 			= 	getRecord(query);

		try
		{
			while(rs.next())
			{
				value = rs.getString(1);
				text  = rs.getString(2);
				//System.out.println("value="+value);
				//System.out.println("text="+text);

				if(selectedValue.equals(value))//if equal
					html=html+"<option selected value="+value+" >"+text+"</option>";
				else
					html=html+"<option value="+value+" >"+text+"</option>";
			}
		}
		catch(Exception e)
		{
			System.out.println("Error while populating combo:"+e);
		}
		if(rs!=null)
			rs.close();

	return html;
	}//end of populateCombo
	
	public List getDetail(String query)throws Exception
	{
		int i=1;
		List AL_Detail = new ArrayList();

		//SQLManager myMan = getSqlManager();
		Connection conn1  = super.getConnection();
		Statement st = conn1.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
	    ResultSet rs = st.executeQuery(query);

		ResultSetMetaData rsmd 	= rs.getMetaData();
		int noOfColumn 			= rsmd.getColumnCount();

		if (rs == null)
			return AL_Detail;
		
		while(rs.next())
		{
			i=1;
			while( i <= noOfColumn )
			{
				AL_Detail.add((rs.getString(i) == null)?"":rs.getString(i).trim());
				i++;
			}
		}

		rs.close();
		st.close();
		super.closeConnection(conn1);

		return AL_Detail;
	}
	
	 public boolean isRedundentName(String query)
		{
		 System.out.println("ReduntantQuery---"+query);
		 
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
			
			System.out.println("result---"+result);
			
			return result;
		}
	 /* This Method is used in modify page of user management master, ADDED ON 06 February 2007 */
	 /* the functionality of the function is ..................
	  * it compares the 'Status in database' and the 'Status value in Jsp Page'  
	  * 
	  * */
	 public boolean isRecordExist(String query,int comboStatus)throws Exception
	  {
		 System.out.println("isRecordExist Query ="+query);
		 
		 System.out.println("comboStatus inside funclib ="+comboStatus);
	   HisResultSet rs			=	null;
	   boolean result			=	false;
	   int isStatusValid		=	0;// if comboStatus ==1 then selected combo is 'Active' ; if comboStatus==2 then  selected combo is 'InActive'
	   try {
		   	rs	=	super.getRecord(query);
			if( rs != null )
			{ 
						     if(rs.next())
						     {
						    	 	isStatusValid	=	Integer.parseInt(rs.getString(1));//rs.getString() contains the value of GNUM_ISVALID
						    	 	 System.out.println("isStatusValid inside funclib="+isStatusValid);
								     if(isStatusValid==comboStatus){
								       result=true;
								      }
								      else {
								       result=false;
								      }
								     rs.close();
								     rs =	null;
						     }
						     else 
						     {
						      result = false ;
						      
						     }
			}
			else{
				result=false;
				System.out.println("RESULTSET IS NULL inside isRecordExist() in funclib ");
			}
	   }
	   catch(Exception e){
		    result=false;
		    System.out.println("Exception inside isRecordExist = "+e);
		    e.printStackTrace();
	   }
	   
	   System.out.println("result ="+result);
	   
	   return result;
	  }
	 
	 /* Genearte Password , 09-February-2007  */
	 public String generatePassword() throws Exception
	 {
		 String pwd						=	"";
		 double random_no				=	0.0;
		 double randomfirst				=	0.0;
		 double randomSecond			=	0.0;
		 long numberAssumed				=	0l;
		 String [] alpha				=	{"A","B","C","D","E","F","G","H","I","J","K","L","M","N","P","Q","R","S","T","U"};
		 
		 try {
			 random_no		=	Math.random()*10000+Math.random()*10000+Math.random()*10000;
			 numberAssumed	=	(long)random_no;
			 randomfirst	=	Math.random()*10;
			 randomSecond	=	Math.random()*10;
			 pwd			=	""+alpha[(int)randomfirst]+""+numberAssumed+alpha[(int)randomSecond];
		 }catch(Exception e)
		 {
			 System.out.println("eXCEPTION INSIDE GENERATE PAWD ="+e);
			 e.printStackTrace();
		 }
		 
		 return pwd ; 
		 
	 
	 }
	 /**
	  * This function is used to check duplicate ip address
	  * @param query
	  * @return
	  */
	 public int isRedundentIpAddress(String query)
		{
			HisResultSet rs=null;
			int result=0;
			try
			{
				rs=super.getRecord(query);
				if( rs != null )
				{	
					while(rs.next())
					{
						result=Integer.parseInt(rs.getString(1));
					}
				}
				
			}
			
			catch(Exception e)
			{
				System.out.println("Exception is "+e);
			}
			
			return result;
		}


}//class

