package hisglobal.masterxml.masterworkshop.dao;

import hisglobal.persistence.TransactionContext;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;

import java.util.*;
import java.sql.*;

public class MasterBuilderDb extends DataAccessObject
{

	public MasterBuilderDb(TransactionContext _tx)
	{
		super(_tx);
	}

	public int redundentCheck(String _query) throws Exception
	{
		int count = 0;
		//JDBCTransactionContext tx =new JDBCTransactionContext();
		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), _query);
			ResultSetMetaData rsmd = rs.getMetaData();
			int col = rsmd.getColumnCount();
			rs.next();
			count=rs.getInt(1);
		}
		catch (Exception e)
		{
			System.out.println("exception while fetch record()" + e);
			e.printStackTrace();
			throw new Exception();
		}
		return count;
	}
	
	public ArrayList fetchRecord(String _query) throws Exception
	{
		ArrayList alList = new ArrayList();
		//JDBCTransactionContext tx =new JDBCTransactionContext();
		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), _query);
			ResultSetMetaData rsmd = rs.getMetaData();
			int col = rsmd.getColumnCount();
			while (rs.next())
			{
				for (int i = 0; i < col; i++)
					alList.add(rs.getString(i + 1));
			}
		}
		catch (Exception e)
		{
			System.out.println("exception while fetch record()" + e);
			e.printStackTrace();
			throw new Exception();
		}
		return alList;
	}

	public boolean updateRecord(String _query) throws Exception
	{
		boolean retValue = false;
		//JDBCTransactionContext tx =new JDBCTransactionContext();
		try
		{

			int i = HelperMethodsDAO.excecuteUpdateForMasters(super.getTransactionContext().getConnection(), _query);
			if (i != 0) retValue = true;
			//System.out.println("retValue in conn" + retValue);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("EXception while inserting record");
			throw new HisDataAccessException();
		}

		//System.out.println("after catch block");
		return retValue;
	}

	public boolean deleteRecord(String[] chkValues, ArrayList alPrimaryKey, String tableName, String isvalidFld)
			throws Exception
	{
		boolean retValue = false;
		ArrayList alNewPrimaryKey = new ArrayList();
		//since alprimarykey might have table alias prefixed to it.
		//it is neccasry to split that string and extract table name
		try
		{
			for (int k = 0; k < alPrimaryKey.size(); k++)
			{
				String pkString = (String) alPrimaryKey.get(k);
				String primaryKeyArr[] = (pkString.replace('.', ':')).replace(")"," ").split(":");
				
				alNewPrimaryKey.add(primaryKeyArr[1]);
			}
			String deleteQuery = "";
			//System.out.println("inside delete record");
			////checking for deletion of all////
			if(chkValues[0].equals("on"))
			{
				String[] tempChkValues=new String[chkValues.length-1];
				for(int m=0;m<tempChkValues.length;m++)
				{
					tempChkValues[m]=chkValues[m+1];
				}
				chkValues=tempChkValues;
			}
			
			
			for (int i = 0; i < chkValues.length; i++)
			{
				if (alNewPrimaryKey.size() == 1)
				{
					/// Building query with primary key in quotes///

					//					split the chkvalues
					//System.out.println("dsldsfs");
					deleteQuery = "UPDATE " + tableName + " SET GNUM_ISVALID=0 WHERE " + alNewPrimaryKey.get(0) + "='"
							+ chkValues[i] + "'";
					System.out.println("deleteQuery" + deleteQuery);
				}
				else
				{
					String chk[] = chkValues[i].replace('^', ':').split(":");
					deleteQuery = "UPDATE " + tableName + " SET GNUM_ISVALID=0 WHERE ";
					for (int j = 0; j < chk.length; j++)
					{
						if (j == chk.length - 1)
						{
							deleteQuery = deleteQuery + alNewPrimaryKey.get(j) + "='" + chk[j] + "'";
						}
						else
						{
							deleteQuery = deleteQuery + alNewPrimaryKey.get(j) + "='" + chk[j] + "'" + " AND ";
						}
					}
				}
				//System.out.println("delete Query::::");
				HelperMethodsDAO.excecuteUpdateForMasters(super.getTransactionContext().getConnection(), deleteQuery);
				//System.out.println("record deleted :::" + i + "::");
			}
			retValue = true;
		}//end of tryy

		catch (Exception e)
		{
			e.printStackTrace();
			throw new Exception();
		}

		return retValue;
	}//end of deleteRecord  
}//end of MasterBuilderDb

