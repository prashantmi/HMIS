package Ftp;

/*import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.persistence.JDBCTransactionContext;*/

import java.io.InputStream;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamSource;

//import oracle.jdbc.OracleTypes;

import org.w3c.dom.Document;

public class InvestigationStyleSheetConfigurationCacheManager {
	private static InvestigationStyleSheetConfigurationCacheManager instance;
	private static int checkedOut = 0;
	private static Map<String,Object> investigationStylesheetConfigurationCacheVO;
	private static DocumentBuilderFactory builderFactory;
	private static DocumentBuilder builder;
	private static TransformerFactory transformerFactory;
	
	private InvestigationStyleSheetConfigurationCacheManager()
	{
		try
		{
			investigationStylesheetConfigurationCacheVO = new HashMap<String, Object>(16);
			builderFactory= DocumentBuilderFactory.newInstance();
			builder=builderFactory.newDocumentBuilder();
			transformerFactory=TransformerFactory.newInstance();
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
		// Handle cache region initialization failure
		}
	
	
	// Do other initialization that may be necessary, such as getting
	// references to any data access classes we may need to populate value objects later
	}
	/**
	* Singleton access point to the manager.
	*/
	public static InvestigationStyleSheetConfigurationCacheManager getInstance()
	{
		synchronized (InvestigationStyleSheetConfigurationCacheManager.class)
		{
			if (instance == null)
			{
			instance = new InvestigationStyleSheetConfigurationCacheManager();
			}
		}
		synchronized (instance)
		{
			instance.checkedOut++;
		}
		return instance;
	}
	
	/**
	* Retrieves a CacheLaboratoryTestVO Object. Default to look in the cache.
	*/
	/*public Transformer getTransformerObj(String id)
	{
	return getTransformerObj(id, true);
	}
	public Transformer getNewTransformer() throws TransformerConfigurationException  
	{
	return transformerFactory.newTransformer();
	}
	
	public Document getDocumentObj(String id)
	{
		Document document= (Document)investigationStylesheetConfigurationCacheVO.get("investigationStylesheetDocument_"+id);
		
		try
		{
			if(document==null)
			{
			 document=builder.parse(getClobObjectFromDatabaseForDocument(id));
			 investigationStylesheetConfigurationCacheVO.put("investigationStylesheetDocument_" + id, document);
			}
		}
		catch (Exception e) {
				e.printStackTrace();
		}
		
		document= (Document)investigationStylesheetConfigurationCacheVO.get("investigationStylesheetDocument_"+id);
		
	return document;
	}*/
	
	/**
	* Retrieves a CacheLaboratoryTestVO Object. 
	* Second argument decides whether to look * in the cache. Returns a new value object if one can't be
	* loaded from the database. Database cache synchronization is * handled by removing cache elements upon modification.
	* @param id
	*/
	/*public Transformer getTransformerObj(String id, boolean fromCache)
	{
		Transformer transformer = null;
		// First, if requested, attempt to load from cache
		if (fromCache)
		{
			transformer = (Transformer) investigationStylesheetConfigurationCacheVO.get("investigationStylesheetConfigurationCacheVO" + id);
		}
		// Either fromCache was false or the object was not found, so
		// call loadBookVObj to create it
		if (transformer == null)
		{
			transformer = loadDocumentObj(id);
		}
		
		return transformer;
	}*/
	 /**
	* Creates a CacheLaboratoryTestVO based on the id of the BOOK table. Data
	* access could be direct JDBC, some or mapping tool, or an EJB.
	* @param id 
	*/
	 
	/* public Transformer loadDocumentObj(String id)
		{
		 System.out.println("loadDocumentObj"+id);
			Document vObj = null;
			Transformer transformer=null;
			try
			{
			boolean found = false;
			// set found to true if it was found
			found = true;
			// cache the value object if found
				if (found)
				{
					
					 transformer=transformerFactory.newTransformer(new StreamSource(getClobObjectFromDatabaseForDocument(id)));
					investigationStylesheetConfigurationCacheVO.put("investigationStylesheetConfigurationCacheVO" + id, transformer);
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
				System.out.println("id->"+id);
				System.out.println("id->"+id+e.getCause());
				
			// Handle failure putting object to cache
			}
			
			System.out.println("id->"+id);	
			
		return transformer;
		}*/
	 
	 /*public InputStream getClobObjectFromDatabaseForDocument(String id) {

			JDBCTransactionContext tx = new JDBCTransactionContext();
			tx.begin();
			Connection conn = tx.getConnection();
			java.io.InputStream ip = null;
			CallableStatement cstmt = null;
			try {
				cstmt = conn.prepareCall("{call inv_configuration.getStyleSheet(?,?,?)}");
				cstmt.setString(1, id);
				cstmt.registerOutParameter(2, OracleTypes.CLOB);
				cstmt.registerOutParameter(3, OracleTypes.VARCHAR);
				cstmt.execute();

				Clob requisitionFormClob = cstmt.getClob(2);
				String error = cstmt.getString(3);
				System.out.println(error);
				if(error.equals("SUCCESS"))
				{
				ip = requisitionFormClob.getAsciiStream();
				}
				
				

			} catch (HisApplicationExecutionException e) {
				e.printStackTrace();
				tx.rollback();
			
			}

			catch (HisDataAccessException e) {
				e.printStackTrace();
				tx.rollback();
			
			} catch (Exception e) {
				e.printStackTrace();
			
				tx.rollback();
			
			} finally {
				try {
						cstmt.close();
				} catch (SQLException e) {
				
					e.printStackTrace();
				}
				tx.close();
			}
			return ip;
		}*/
	 
	
}
