package usermgmt.masters;
import usermgmt.*;
import HisGlobal.*;
import java.sql.*;
import java.util.*;
import HisGlobal.*;
import javax.servlet.http.HttpServletRequest;

public class UmgmtMetatableColMstBean extends HisGlobal.HisMethods

{

	private String mstTable="";
	private String colName="";
	private String dispCol="";
	private String seatId="";
	private String hospitalcode="";
	private String message	="" ;
	private String dataSetName="";
	
	/*
	setter method
	*/

	public void setMstTable(String mstTable)
	{
		this.mstTable=mstTable;
	}

	public void setColName(String colName)
	{
		this.colName=colName;
	}

	public void setDispCol(String dispCol)
	{
		this.dispCol=dispCol;
	}

	public void setSeatId(String SEAT_ID)
	{
		this.seatId=SEAT_ID;
	}

	/*
	getter method
	*/

	public String getSeatId()
	{
		return ((seatId==null)?" ":seatId);
	}

	public String getMstTable()
	{
		return this.mstTable;
	}

	public String getColName()
	{
		return this.colName;
	}

	public String getDispCol()
	{
		return this.dispCol;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	
	public String getHospitalcode() {
		return hospitalcode;
	}

	public void setHospitalcode(String hospitalcode) {
		this.hospitalcode = hospitalcode;
	}
	
	public String getDataSetName() {
		return dataSetName;
	}
	
	public void setDataSetName(String dataSetName) {
		this.dataSetName = dataSetName;
	}
	
	//method used in bean
	

	public String getMstTableOptions(String selectedTable)
	{
		String options="";
		/*String query="SELECT TABLE_NAME,INITCAP(TABLE_NAME) FROM TABS "+
					 "WHERE TABLE_NAME LIKE '%MST' AND TABLE_NAME NOT IN (SELECT GSTR_TABLE_NAME "+
					 "FROM GBLT_METATABLE_COLUMN_MST "+
					 "WHERE GSTR_TABLE_NAME LIKE '%MST' AND GBL_ISVALID=1 and gnum_hospital_code='"+ this.hospitalcode +"' ) order by INITCAP(TABLE_NAME) ";
		*/
		
		
		String query="select tablename,INITCAP(tablename) from pg_tables A"+
		" where UPPER(tablename) like UPPER('%mst%')"+
		" AND schemaname='ahiscl'"+
		//Exits condition appended to remove those tables from list which don't have hospital code in maharashtra
		"AND  EXISTS "+
		 "( "+
		 "select 'X' from USER_CONS_COLUMNS "+
		 " where "+
		 " upper(TABLE_NAME)=upper(A.tablename) AND "+
		 " COLUMN_NAME='GNUM_HOSPITAL_CODE'"+
		 ")"+
		" AND upper(tablename) NOT IN"+ 
		" (SELECT upper(GSTR_TABLE_NAME) FROM GBLT_METATABLE_COLUMN_MST "+
		//" WHERE GSTR_TABLE_NAME LIKE '%MST' AND GBL_ISVALID=1 and gnum_hospital_code='"+ this.hospitalcode +"')"+
		//" WHERE upper(GSTR_TABLE_NAME) LIKE '%MST' AND GBL_ISVALID=1 and gnum_hospital_code='100')"+
		" WHERE upper(GSTR_TABLE_NAME) LIKE '%MST' AND GBL_ISVALID=1)"+
		" order by INITCAP(tablename)" ;
		System.out.print("query 1"+query);
		
		
	HisResultSet rs=null;
		
		try
		{
			rs=getRecord(query);
			if(rs!=null)
			{
				while(rs.next())
				{
					String val1=rs.getString(1);
					String val=rs.getString(2);

					if(val1.equals(selectedTable))
					{
						options+="<option value='"+val1+"' selected>"+val+"</option>";
					}
					else
					{
						options+="<option value='"+val1+"'>"+val+"</option>";
					}
				}
				rs.close();
			}
		}
		catch(Exception e)
		{
			System.out.print("\n Exception in getMstTableOptions"+e);
		}
		return options;
	}//End of getMstTableOptions


	public String getColNameOptions(String mstTable,String selectedCol)
	{
		String options="";
		
	
		String query="SELECT  A.COLUMN_NAME FROM (select * from USER_CONS_COLUMNS where upper(schema_name)=upper('ahiscl')) A,"+
                      "(select * from USER_CONSTRAINTS where upper(schema_name)=upper('ahiscl') and index_owner='AHISCL') B "+
                       "WHERE Upper(B.TABLE_NAME) LIKE Upper('"+mstTable+"') "+
                        "AND A.CONSTRAINT_NAME=B.CONSTRAINT_NAME AND A.TABLE_NAME=B.TABLE_NAME AND B.CONSTRAINT_TYPE='P' "+
                         "order by initcap(A.COLUMN_NAME) ";
		
	/*String query="SELECT distinct A.COLUMN_NAME FROM USER_CONS_COLUMNS A,USER_CONSTRAINTS B "+
					 "WHERE Upper(B.TABLE_NAME) LIKE Upper('"+mstTable+"') AND "+
					 "A.CONSTRAINT_NAME=B.CONSTRAINT_NAME AND "+
					 "A.TABLE_NAME=B.TABLE_NAME AND "+
					 "B.CONSTRAINT_TYPE='P' order by initcap(A.COLUMN_NAME)";*/
		
		
		HisResultSet rs=null;
		System.out.println("mstTable valus is....."+mstTable);
		System.out.println("null condition is..."+(mstTable!=null));
		System.out.println("First Condition is "+(mstTable!=""));
		System.out.println("Second condition is  "+(mstTable!="-1"));
		if(mstTable!=null && mstTable!=""  && !mstTable.equalsIgnoreCase("-1"))
		{
		try
		{
			System.out.println("query of colo name is "+query);
			rs=getRecord(query);

			if(rs!=null)
			{
				while(rs.next())
				{
					String val=rs.getString(1);
					
					if(val.equals(selectedCol))
					{
						options+="<option value='"+val+"' selected>"+val+"</option>";
					}
					else
					{
						options+="<option value='"+val+"'>"+val+"</option>";
					}
				}
				rs.close();
			}
		}

		catch(Exception e)
		{
			System.out.print("\n Exception in getMstTableOptions"+e);
		}
		}
		return options;

	}//End of getColNameOptions





	public String getAllColumnOptions(String mstTable)
	{
		String options="";
		String query="SELECT COLUMN_NAME,INITCAP(COLUMN_NAME) FROM USER_TAB_COLUMNS "+
					 "WHERE Upper(TABLE_NAME) LIKE Upper('"+mstTable+"') and UPPER(schema_name)=UPPER('ahiscl') order by INITCAP(COLUMN_NAME)";

		
		HisResultSet rs=null;

		if(mstTable!=null && mstTable!="" && !mstTable.equalsIgnoreCase("-1"))
		{
		try
		{
			rs=getRecord(query);

			if(rs!=null)
			{
				while(rs.next())
				{
					String val=rs.getString(1);

					if(val.equals(mstTable))
					{
						options+="<option value='"+val+"' selected>"+rs.getString(2)+"</option>";
					}
					else
					{
						options+="<option value='"+val+"'>"+rs.getString(2)+"</option>";
					}
				}
				rs.close();
			}
		}

		catch(Exception e)
		{
			System.out.print("\n Exception in getMstTableOptions"+e);
		}

		}
		return options;
	}//End of getAllColumnOptions


	public void save()
	{
		//String query="SELECT NVL(MAX(TO_NUMBER(GNUM_METATABLE_ID)),100) FROM GBLT_METATABLE_COLUMN_MST where GNUM_HOSPITAL_CODE='"+this.hospitalcode+"' ";
		String query="SELECT NVL(MAX(TO_NUMBER(GNUM_METATABLE_ID)),100) FROM GBLT_METATABLE_COLUMN_MST";
		String max="100";
		HisResultSet rs=null;

		try
		{
			rs=getRecord(query);
			if(rs!=null)
			{
				if(rs.next())
					max=String.valueOf( Integer.parseInt(rs.getString(1)) +1);

				rs.close();
			}

			query="INSERT INTO GBLT_METATABLE_COLUMN_MST "+
				  "(GNUM_METATABLE_ID, GSTR_TABLE_NAME, GSTR_COLUMN_NAME, GSTR_DISPLAY_COLUMN,GNUM_SEAT_ID,GSTR_DATASET_NAME) "+
				  "VALUES("+max+",UPPER('"+this.mstTable+"'),'"+this.colName+
				  "','"+this.dispCol+"','"+this.seatId+"','"+this.dataSetName+"')";

			System.out.println("query "+query);
			boolean flag=updateRecord(query);
//<<<<<<< UmgmtMetatableColMstBean.java
			
//=======
			System.out.println(flag);
			if (flag==true)
			{	
			message="Record Successfully Inserted !";
			}
//>>>>>>> 1.5
		}

		catch(Exception e)
		{
			System.out.print("\n Exception in save "+e);

		}

	}//End of Save

	

}
