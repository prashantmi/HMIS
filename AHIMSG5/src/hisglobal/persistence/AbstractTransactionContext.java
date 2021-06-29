/**********************************************************
 Project:	   AHIMS_G5	
 File:         AbstractTransactionContext.java
 Created:      Jan, 2014
 Last Changed: Mar, 2016
 Author:       Pragya Sharma

This code is copyright (c) 2014 C-DAC Noida.
***********************************************************/

package hisglobal.persistence;

import hisglobal.exceptions.HISApplicationExecutionException;
import hisglobal.exceptions.HISInvalidTransactionStateException;
import hisglobal.exceptions.HISTransactionException;

import java.sql.Connection;
import java.util.HashMap;

public abstract class AbstractTransactionContext implements TransactionContext
{
	protected HashMap labelConnectionMap = new HashMap();
	protected HashMap connectionStateMap = new HashMap();
	protected boolean isTransactionOpen;

	// Get Default Connection
	public Connection getConnection() throws HISTransactionException
	{
		return getConnection(CONNECTION_DEFAULT);
	}

	// Get Connection of Passed Data Source JNDI ServiceName
	public Connection getConnection(String strServiceName) throws HISTransactionException
	{
		Connection objConnection = null;
		if (!isValidTransactionState()) throw new HISInvalidTransactionStateException("Transaction: NOT STARTED");
		if (strServiceName == null) throw new HISApplicationExecutionException("Service Name: Should not NULL");

		objConnection = this.getRegisteredConnection(strServiceName);
		if (objConnection == null)
		{
			objConnection = this.getNewConnection(strServiceName);
			this.registerConnection(strServiceName, objConnection);
		}
		return objConnection;
	}

	// Close all connections
	public boolean close() throws HISTransactionException
	{
		if (!this.isTransactionOpen) throw new HISInvalidTransactionStateException("Transaction: NOT STARTED");

		this.commitAll();
		this.closeAllConnections();
		this.isTransactionOpen = false;

		labelConnectionMap = new HashMap();
		connectionStateMap = new HashMap();

		return true;
	}

	protected Connection getRegisteredConnection(String strServiceName)
	{
		Connection objConnection = null;
		if (strServiceName == null) throw new HISApplicationExecutionException("Service Name: Should not NULL");
		objConnection = (Connection) this.labelConnectionMap.get(strServiceName);
		if (objConnection != null && !this.isValidConnection(strServiceName))
			throw new HISInvalidTransactionStateException("Invalid Transaction State - Label: " + strServiceName);
		return objConnection;
	}

	protected void registerConnection(String strServiceName, Connection objConnection)
	{
		if (strServiceName == null || objConnection == null) throw new HISApplicationExecutionException("Service Name: " + strServiceName + " Connection: " + objConnection); 
		this.labelConnectionMap.put(strServiceName, objConnection);
		this.connectionStateMap.put(strServiceName, CONNECTION_STATE_VALID);
	}

	protected boolean isValidTransactionState()
	{
		return this.isTransactionOpen;
	}

	protected abstract void closeAllConnections();

	protected abstract boolean isValidConnection(String strServiceName);

	protected abstract Connection getNewConnection(String strServiceName) throws HISTransactionException;
}
