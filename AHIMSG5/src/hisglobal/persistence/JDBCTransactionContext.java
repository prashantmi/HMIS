/**********************************************************
 Project:	   AHIMS_G5	
 File:         JDBCTransactionContext.java
 Created:      Jan, 2014
 Last Changed: Mar, 2016
 Author:       Pragya Sharma

This code is copyright (c) 2014 C-DAC Noida.
***********************************************************/

package hisglobal.persistence;

import hisglobal.config.locator.ServiceLocator;
import hisglobal.exceptions.HISApplicationExecutionException;
import hisglobal.exceptions.HISTransactionException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Set;

public class JDBCTransactionContext extends AbstractTransactionContext
{
	// Begin Transaction
	public void begin() throws HISTransactionException
	{
		if (isTransactionOpen == true) throw new HISTransactionException("Transaction.isTransactionOpen : true");
		isTransactionOpen = true;
	}

	// Commit All
	public boolean commitAll() throws HISTransactionException
	{
		Set st = this.labelConnectionMap.keySet();
		Iterator it = st.iterator();
		try
		{
			while (it.hasNext())
				((Connection) this.labelConnectionMap.get(it.next())).commit();
		}
		catch (SQLException e)
		{
			throw new HISTransactionException("Transaction.Commit: " + e.getMessage());
		}
		return true;
	}

	// Roll back
	public boolean rollback() throws HISTransactionException
	{
		Set st = this.labelConnectionMap.keySet();
		Iterator it = st.iterator();
		try
		{
			while (it.hasNext())
				((Connection) this.labelConnectionMap.get(it.next())).rollback();
		}
		catch (SQLException e)
		{
			throw new HISTransactionException("Transaction.Rollback: " + e.getMessage());
		}
		return true;
	}

	protected boolean isValidConnection(String strServiceName)
	{
		if (strServiceName == null) throw new HISApplicationExecutionException("Service Name: Should not NULL");
		try
		{
			Connection conn = (Connection) labelConnectionMap.get(strServiceName);
			String connState = (String) connectionStateMap.get(strServiceName);

			if (conn.isClosed() || connState.equals(CONNECTION_STATE_INVALID))
			// connection is closed using the the raw connection object in the
			// DAO explicitly
				return false;
		}
		catch (SQLException e)
		{
			throw new HISTransactionException("Transaction.isValidConnection: " + e.getMessage());
		}
		return true;
	}

	protected Connection getNewConnection(String strServiceName) throws HISTransactionException
	{
		if (strServiceName == null) throw new HISApplicationExecutionException("Service Name: Should not NULL");
		Connection conn = null;
		try
		{
			ServiceLocator locator = ServiceLocator.getInstance();
			conn = locator.getDataSource(strServiceName);

			conn.setAutoCommit(false);
			this.labelConnectionMap.put(strServiceName, CONNECTION_STATE_VALID);

		}
		catch (Exception e)
		{
			throw new HISTransactionException("Transaction.newConnection: " + e);
		}

		return conn;
	}

	protected void closeAllConnections()
	{
		Set st = this.labelConnectionMap.keySet();
		Iterator it = st.iterator();
		try
		{
			while (it.hasNext())
			{
				String strKey = (String) it.next();
				Connection conn = (Connection) this.labelConnectionMap.get(strKey);
				conn.close();
				this.connectionStateMap.put(strKey, CONNECTION_STATE_INVALID);
			}
		}
		catch (SQLException e)
		{
			throw new HISTransactionException("Transaction.CloseAllConnections: " + e.getMessage());
		}
	}

}
