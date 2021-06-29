/**********************************************************
 Project:	   AHIMS_G5	
 File:         ServiceLocator.java
 Created:      Jan, 2014
 Last Changed: Mar, 2016
 Author:       Pragya Sharma

This code is copyright (c) 2014 C-DAC Noida.
***********************************************************/

package hisglobal.config.locator;

import hisglobal.exceptions.HISServiceLocatorException;

import java.util.*;
import java.sql.*;
import javax.sql.DataSource;
import javax.naming.*;

public class ServiceLocator
{
	private static ServiceLocator objServiceLocator = null;

	// Prefix for JNDI Lookup
	private static final String strLookUpPrefix = "java:comp/env/"; // Tomcat
	// private static final String strLookUpPrefix = ""; //For WAS
	// private static final String strLookUpPrefix = "java:jboss/"; // JBoss 7

	// HashTables for keeping the instances of JNDI Returned Objects
	private static Hashtable cacheDataSource = null;

	// private static Hashtable cacheDataSourceJPA = null;
	// private static Hashtable cacheEJBHome = null;
	// private static Hashtable cacheEJBLocalHome = null;
	// private static Hashtable cacheQueueConnectionFactory = null;
	// private static Hashtable cacahMessageQueue = null;

	private ServiceLocator()
	{
		// Initializing all Cache Hashtables
		cacheDataSource = new Hashtable();
	}

	/* Implementing Singleton Pattern using getInstance() method */
	public static ServiceLocator getInstance()
	{
		if (objServiceLocator == null) objServiceLocator = new ServiceLocator();
		return objServiceLocator;
	}
	
	/* Getting DataSource Connection Object from Data Source Cache */
	public Connection getDataSource(String strSeviceName) throws Exception
	{
		Connection objConnection = null;
		try
		{
			// Check Is Data Source present in cache
			if (cacheDataSource.contains(strSeviceName))
			{
				// If Present, fetch object and return
				DataSource ds = (DataSource) cacheDataSource.get(strSeviceName);
				objConnection = ds.getConnection();
			}
			else
			{
				Context ctx = new InitialContext();
				DataSource newDS = (DataSource) ctx.lookup(strLookUpPrefix + strSeviceName);
				cacheDataSource.put(strSeviceName, newDS);
				objConnection = newDS.getConnection();
			}
		}
		catch (SQLException e)
		{
			throw new HISServiceLocatorException("SQL Exception has occured: \n" + e);
		}
		catch (NamingException e)
		{
			e.printStackTrace();
			throw new HISServiceLocatorException("Naming Exception has occured: \n" + e);

		}
		catch (Exception e)
		{
			throw new HISServiceLocatorException("Exception has occured: \n" + e);
		}

		return objConnection;
	}
}
