/**********************************************************
 Project:	   AHIMS_G5	
 File:         TransactionContext.java
 Created:      Jan, 2014
 Last Changed: Mar, 2016
 Author:       Pragya Sharma

This code is copyright (c) 2014 C-DAC Noida.
***********************************************************/

package hisglobal.persistence;

import hisglobal.config.HISConfig;
import hisglobal.exceptions.HISTransactionException;

import java.sql.*;

public interface TransactionContext
{
	public static final String CONNECTION_STATE_VALID = "1";
	public static final String CONNECTION_STATE_INVALID = "0";
	public static final String CONNECTION_DEFAULT = HISConfig.JNDI_LOOKUP_ID_DATASOURCE_OLTP;

	// Get Connection of Passed Data Source JNDI ServiceName
	public Connection getConnection(String strServiceName) throws HISTransactionException;

	// Get Default Connection
	public Connection getConnection() throws HISTransactionException;

	// Begin Transaction
	public void begin() throws HISTransactionException;

	// Commit All
	public boolean commitAll() throws HISTransactionException;

	// Roll back 
	public boolean rollback() throws HISTransactionException;

	// Close all connections
	public boolean close() throws HISTransactionException;
}
