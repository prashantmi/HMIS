/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DataHelper.persistence;

/**
 *
 * @author Siddharth Srivastava <siddharthsrivastava@cdac.in>
 */

import java.sql.*;

public interface TransactionContext
{
	public static final String CONNECTION_STATE_VALID = "1";
	public static final String CONNECTION_STATE_INVALID = "0";
	public static final String CONNECTION_DEFAULT = "DEFAULT";
	//public static final String CONNECTION_DEFAULT = "AYUSH";

	public Connection getConnection(String _label) throws TransactionException;

	public Connection getConnection() throws TransactionException;

	public void begin() throws TransactionException;

	public boolean commitAll() throws TransactionException;

	//public boolean commit(String _label) throws TransactionException;    
	public boolean rollback() throws TransactionException;

	public boolean close() throws TransactionException; //close all connections
}
