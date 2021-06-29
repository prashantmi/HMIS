package hr.common;


import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisNoRecordException;
import hisglobal.transactionmgnt.HisDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CommonDataBaseManip {
	
	public static synchronized boolean insertData(String query,List dataList) throws HisException
	{
		boolean result = false;
		PreparedStatement ps = null;
		Connection conn 	 = null;
		try
		{
			conn = getConnection();
			//conn.setAutoCommit(false);
			ps = conn.prepareStatement(query);
			for(int i=0;i<dataList.size();i++)
			{
				ps.setString(i+1,String.valueOf(dataList.get(i)));
			}
			result = ps.execute();
			 //conn.commit();
			//  conn.setAutoCommit(true);

		}
		catch(SQLException e)
		{
				throw new HisException("hisglobal.backutil.CommonDataManip.insertData():"+e.getMessage());
		}
		catch(HisException e)
		{
				throw new HisException("hisglobal.backutil.CommonDataManip.insertData():"+e.getMessage());
		}
		catch(Exception e)
		{
				throw new HisException("hisglobal.backutil.CommonDataManip.insertData():"+e.getMessage());
		}
		finally
		{
				try
				{
					ps.close();
					closeConnection(conn);
				}
				catch(SQLException e)
				{
						throw new HisException("hisglobal.backutil.CommonDataManip.insertData():"+e.getMessage());
				}
				catch(HisException e)
				{
						throw new HisException("hisglobal.backutil.CommonDataManip.insertData():"+e.getMessage());
				}
				catch(Exception e)
				{
						throw new HisException("hisglobal.backutil.CommonDataManip.insertData():"+e.getMessage());
				}

		}

		return result;
	}

	public static synchronized List getDetail(String query)throws HisNoRecordException,HisException
	{

		List resultList = new ArrayList();
		Connection conn 	 = null;
		Statement st		 = null;
		try
		{
			conn = getConnection();
			st	 = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
		    ResultSet rs = st.executeQuery(query);

		    if(rs==null)
		    	throw new HisNoRecordException("hisglobal.backutil.CommonDataManip.getDetail():ResultSet is Null");
		    ResultSetMetaData rsmd 	= rs.getMetaData();
			int noOfColumn 			= rsmd.getColumnCount();

			while(rs.next())
			{
				int i=1;
				while( i <= noOfColumn )
				{
					resultList.add((rs.getString(i) == null)?"":rs.getString(i).trim());
					i++;
				}
			}

			rs.close();
		}
		catch(Exception e)
		{

		}
		finally
		{
			try
			{	st.close();
				closeConnection(conn);
			}
			catch(SQLException e)
			{
					throw new HisException("hisglobal.backutil.CommonDataManip.getDetail():"+e.getMessage());
			}
			catch(HisException e)
			{
					throw new HisException("hisglobal.backutil.CommonDataManip.getDetail():"+e.getMessage());
			}
			catch(Exception e)
			{
					throw new HisException("hisglobal.backutil.CommonDataManip.getDetail():"+e.getMessage());
			}
		}

		return resultList;

	}


	public static synchronized ResultSet getResultSet(String query)throws HisNoRecordException,HisException
	{

		Connection conn 	 = null;
		Statement st		 = null;
		ResultSet rs		 = null;
		try
		{
			conn = getConnection();
			st	 = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
		    rs = st.executeQuery(query);

		    if(rs==null)
		    	throw new HisNoRecordException("hisglobal.backutil.CommonDataManip.getDetail():ResultSet is Null");

		}
		catch(Exception e)
		{

		}
		finally
		{
			try
			{	st.close();
				closeConnection(conn);
			}
			catch(SQLException e)
			{
					throw new HisException("hisglobal.backutil.CommonDataManip.getDetail():"+e.getMessage());
			}
			catch(HisException e)
			{
					throw new HisException("hisglobal.backutil.CommonDataManip.getDetail():"+e.getMessage());
			}
			catch(Exception e)
			{
					throw new HisException("hisglobal.backutil.CommonDataManip.getDetail():"+e.getMessage());
			}
		}

		return rs;

	}






	public static synchronized List getDetail(String query,List dataList) throws HisException,HisNoRecordException
	{
		List resultList = new ArrayList();
		PreparedStatement ps = null;
		Connection conn 	 = null;

		try
		{
			conn = getConnection();
			//conn.setAutoCommit(false);
			ps = conn.prepareStatement(query,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			for(int i=0;i<dataList.size();i++)
			{
				ps.setString(i+1,String.valueOf(dataList.get(i)));
			}
			ResultSet resultSet = ps.executeQuery();

			if(resultSet==null)
		    	throw new HisNoRecordException("hisglobal.backutil.CommonDataManip.getDetail():ResultSet is Null");

			ResultSetMetaData rsmd 	= resultSet.getMetaData();
			int noOfColumn 			= rsmd.getColumnCount();

			while(resultSet.next())
			{
				int i=1;
				while( i <= noOfColumn )
				{
					resultList.add( (resultSet.getString(i) == null)?"":resultSet.getString(i).trim());
					i++;
				}
			}

			//myMan.returnConnection(conn1);
			resultSet.close();
		}
		catch(SQLException e)
		{
				throw new HisException("hisglobal.backutil.CommonDataManip.getDetail():"+e.getMessage());
		}
		catch(HisException e)
		{
				throw new HisException("hisglobal.backutil.CommonDataManip.getDetail():"+e.getMessage());
		}
		catch(Exception e)
		{
				throw new HisException("hisglobal.backutil.CommonDataManip.getDetail():"+e.getMessage());
		}
		finally
		{
				try
				{
					ps.close();
					closeConnection(conn);
				}
				catch(SQLException e)
				{
						throw new HisException("hisglobal.backutil.CommonDataManip.getDetail():"+e.getMessage());
				}
				catch(HisException e)
				{
						throw new HisException("hisglobal.backutil.CommonDataManip.getDetail():"+e.getMessage());
				}
				catch(Exception e)
				{
						throw new HisException("hisglobal.backutil.CommonDataManip.getDetail():"+e.getMessage());
				}

		}
		return resultList;
	}


	public static synchronized ResultSet getResultSet(String query,List dataList) throws HisException,HisNoRecordException
	{
		PreparedStatement ps = null;
		Connection conn 	 = null;
		ResultSet resultSet	 = null;
		try
		{
			conn = getConnection();
			//conn.setAutoCommit(false);
			ps = conn.prepareStatement(query,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			for(int i=0;i<dataList.size();i++)
			{
				ps.setString(i+1,String.valueOf(dataList.get(i)));
			}
			resultSet = ps.executeQuery();

			if(resultSet==null)
		    	throw new HisNoRecordException("hisglobal.backutil.CommonDataManip.getDetail():ResultSet is Null");

		}
		catch(SQLException e)
		{
				throw new HisException("hisglobal.backutil.CommonDataManip.getDetail():"+e.getMessage());
		}
		catch(HisException e)
		{
				throw new HisException("hisglobal.backutil.CommonDataManip.getDetail():"+e.getMessage());
		}
		catch(Exception e)
		{
				throw new HisException("hisglobal.backutil.CommonDataManip.getDetail():"+e.getMessage());
		}
		finally
		{
				try
				{
					ps.close();
					closeConnection(conn);
				}
				catch(SQLException e)
				{
						throw new HisException("hisglobal.backutil.CommonDataManip.getDetail():"+e.getMessage());
				}
				catch(HisException e)
				{
						throw new HisException("hisglobal.backutil.CommonDataManip.getDetail():"+e.getMessage());
				}
				catch(Exception e)
				{
						throw new HisException("hisglobal.backutil.CommonDataManip.getDetail():"+e.getMessage());
				}

		}
		return resultSet;
	}





	public static synchronized String getNextKey(String query)throws HisException
	{

		String nextKey	="1";
		Connection conn 	 = null;
		Statement st		 = null;
		try
		{
			conn = getConnection();
			st	 = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
		    ResultSet rs = st.executeQuery(query);

		    if(rs==null)
		    	nextKey = "1";

			if(rs.next())
			{
				nextKey = ((rs.getString(1) == null)?"1": String.valueOf( Integer.parseInt(rs.getString(1))+1));
			}

			rs.close();
		}
		catch(SQLException e)
		{
				throw new HisException("hisglobal.backutil.CommonDataManip.getNextKey():"+e.getMessage());
		}
		catch(HisException e)
		{
				throw new HisException("hisglobal.backutil.CommonDataManip.getNextKey():"+e.getMessage());
		}
		catch(Exception e)
		{
				throw new HisException("hisglobal.backutil.CommonDataManip.getNextKey():"+e.getMessage());
		}
		finally
		{
			try
			{	st.close();
				closeConnection(conn);
			}
			catch(SQLException e)
			{
					throw new HisException("hisglobal.backutil.CommonDataManip.getPrimaryKey():"+e.getMessage());
			}
			catch(HisException e)
			{
					throw new HisException("hisglobal.backutil.CommonDataManip.getPrimaryKey():"+e.getMessage());
			}
			catch(Exception e)
			{
					throw new HisException("hisglobal.backutil.CommonDataManip.getPrimaryKey():"+e.getMessage());
			}
		}

		return nextKey;

	}


	//Getting Connection From Conn for multiple update
	public static synchronized Connection getConnection() throws HisException
	{
		Connection conn = null;
		try
		{
			HisDAO objHisDAO = new HisDAO("Pis","EmployeeBO");
			conn = objHisDAO.getConnectionInstance();

		}		
		catch(Exception e)
		{
			throw new HisException("hisglobal.backutil.CommonDataManip.getConnection():"+e.getMessage());
		}
		return conn;
	}

	//	Closing the Connection
	public static synchronized void closeConnection(Connection connection) throws HisException
	{
		try
		{
			if(connection == null)
				throw new HisException("hisglobal.backutil.CommonDataManip.closeConnection():Invalide Argument:Connection==null");
			connection.close();
		}
		catch(SQLException e)
		{
			throw new HisException("hisglobal.backutil.CommonDataManip.getConnection():"+e.getMessage());
		}
		catch(Exception e)
		{
			throw new HisException("hisglobal.backutil.CommonDataManip.getConnection():"+e.getMessage());
		}
	}



}//End of class
