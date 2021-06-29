/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hisglobal.utility.noSqlDB.mongodb;

//import DataHelper.PropertiesHelper;
//import DataHelper.ServiceConfiguration;
//import FileHandler.XMLFileHandler;
//import Logging.ServiceLogger;
import hisglobal.config.HISConfig;
import hisglobal.presentation.ControllerDATA;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;

/**
 *
 * @author Siddharth Srivastava <siddharthsrivastava@cdac.in>
 * Updated By @Pragya Sharma dated 2017.12.12
 */
public class MongoXmlHandler
{
	private static Map<String, MongoClient> mongoClientConnPool = null;//new HashMap<String, MongoClient>(); 
	private static Map<String, Map<String, DB>> mongoDBConnPool = null;//new HashMap<String, Map<String,DB>>(); 

	private static MongoClient mongoDBClientDefault;
	private static DB mongoDataBaseDefault;
	
	private MongoClient mongoDBClient;
	private DB mongoDataBase;
	
    public static MongoXmlHandler getInstanceWithURL(String serverURL_Key_p, String dbName_Key_p)
    {
    	createMongoDBConnectionPool();
        return new MongoXmlHandler(serverURL_Key_p, dbName_Key_p);
    }

    public static MongoXmlHandler getInstance(String dbName_Key_p)
    {
    	createMongoDBConnectionPool();
        return new MongoXmlHandler(HISConfig.HIS_MONGODB_SERVER_URL_DEFAULT_Key, dbName_Key_p);
    }
    
    public static MongoXmlHandler getInstance()
    {
    	createMongoDBConnectionPool();
        return new MongoXmlHandler(HISConfig.HIS_MONGODB_SERVER_URL_DEFAULT_Key, HISConfig.HIS_MONGODB_DBNAME_DEFAULT_Key);
    }

    private MongoXmlHandler(String serverURL_Key_p, String dbName_Key_p)
    {
    	this.mongoDBClient = mongoClientConnPool.get(serverURL_Key_p);
    	this.mongoDataBase = mongoDBConnPool.get(serverURL_Key_p).get(dbName_Key_p);	
    	
    	if(serverURL_Key_p==null || this.mongoDBClient == null)
    	{
    		this.mongoDBClient = mongoDBClientDefault;
    		serverURL_Key_p = HISConfig.HIS_MONGODB_SERVER_URL_DEFAULT_Key;
    	}

    	if(this.mongoDataBase == null)
    	{
			if(dbName_Key_p !=null)
			{
				DB database = this.mongoDBClient.getDB(dbName_Key_p);
	            if(database != null)
	            {
	            	System.out.println("MongoDB Database Connection Created ->> Server Key: " + serverURL_Key_p + " DB Key: " + dbName_Key_p + " DB Name: " + dbName_Key_p);
	            	if(mongoDBConnPool.get(serverURL_Key_p)==null) mongoDBConnPool.put(serverURL_Key_p, new HashMap<String, DB>());
					mongoDBConnPool.get(serverURL_Key_p).put(dbName_Key_p, database);
					this.mongoDataBase = database;
	            }
	            else
	            	this.mongoDataBase = mongoDataBaseDefault;
			}	          
            else
            	this.mongoDataBase = mongoDataBaseDefault;
    	}
    }

	public static void createMongoDBConnectionPool()
    {
		// Modified by Vasu for Creating Connection Pool for reusing the Connection dated 2017.12.08
		if(mongoClientConnPool == null || mongoDBConnPool == null)
		{
			mongoClientConnPool = new HashMap<String, MongoClient>();
			mongoDBConnPool = new HashMap<String, Map<String, DB>>();
			
			Map<String, String> mpMongoConfigs = (Map<String, String>) ControllerDATA.getMongoDBConfigurationsfromDB();
			
			for (String serverKey : mpMongoConfigs.keySet())
			{
				if(serverKey.startsWith("MONGODB_SERVER_URL"))
				{
					// Settings MongoDB Server URL and MongoClient
					String serverURL = mpMongoConfigs.get(serverKey);
					MongoClient mongoClient = new MongoClient(new MongoClientURI(serverURL));
    	            if(mongoClient != null)
    	            {
    	            	System.out.println("MongoDB Server Connection Created ->> Server Key: " + serverKey + " Server URL: " + serverURL);
    	            	mongoClientConnPool.put(serverKey, mongoClient);
    	            }
				}
			}
			
			for (String dbKey : mpMongoConfigs.keySet())
			{
				if(dbKey.startsWith("MONGODB_DBNAME"))
				{
					for(String serverKey : mongoClientConnPool.keySet())
					{
						if(mongoDBConnPool.get(serverKey)==null || mongoDBConnPool.get(serverKey).get(dbKey)==null)
						{
							MongoClient mongoClient = mongoClientConnPool.get(serverKey);
							String dbName = mpMongoConfigs.get(dbKey);
							DB database = mongoClient.getDB(dbName);
		    	            if(database != null)
		    	            {
		    	            	System.out.println("MongoDB Database Connection Created ->> Server Key: " + serverKey + " DB Key: " + dbKey + " DB Name: " + dbName);
		    	            	if(mongoDBConnPool.get(serverKey)==null) mongoDBConnPool.put(serverKey, new HashMap<String, DB>());
			    				mongoDBConnPool.get(serverKey).put(dbKey, database);
			    			}
						}
					}
				}
			}

			// Setting Default MongoClient and MongoDB 
			mongoDBClientDefault = mongoClientConnPool.get(HISConfig.HIS_MONGODB_SERVER_URL_DEFAULT_Key);
			mongoDataBaseDefault = mongoDBConnPool.get(HISConfig.HIS_MONGODB_SERVER_URL_DEFAULT_Key).get(HISConfig.HIS_MONGODB_DBNAME_DEFAULT_Key);
		}
    }
	
    public void saveFile(String id, String xmlContent)
    {
    	String newFileName = id;
        GridFS gfsPhoto = new GridFS(this.mongoDataBase);
        GridFSInputFile gfsFile = gfsPhoto.createFile(xmlContent.getBytes());
        gfsFile.setFilename(newFileName);
        gfsFile.save();
        System.out.println("File Saved Successfullly ->> File Name: " + id);
    }

    public void savePDFFile(String id, byte[] pdfData)
    {
        String newFileName = id;
        GridFS gfsPhoto = new GridFS(this.mongoDataBase);
        GridFSInputFile gfsFile = gfsPhoto.createFile(pdfData);
        gfsFile.setFilename(newFileName);
        gfsFile.save();
        System.out.println("PDF File Saved Successfullly ->> File Name: " + id);
    }

    public byte[] fetchFile(String id) throws IOException
    {
        return latestFetchFile(id);
    }

    public byte[] latestFetchFile(String id) throws IOException
    {
        GridFS gfsPhoto = new GridFS(this.mongoDataBase);
        BasicDBObject sort = new BasicDBObject("uploadDate", -1);
        BasicDBObject query = new BasicDBObject("filename", id);
        
        List<GridFSDBFile> outputFiles = gfsPhoto.find(query, sort);

        if (outputFiles == null || outputFiles.size() == 0)
        {
        	System.out.println("Document Not found. Id: " + id);
            return null;
        }

        GridFSDBFile output = outputFiles.get(0);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        System.out.println("File Found and Retrievd Successfullly ->> File Name: " + id + " File Data: " + output);
        output.writeTo(baos);
        return baos.toByteArray();
    }
    
    
    
    /* public static createConnection()
    {
		/*
    	if (this.mongoClient == null || this.database == null)
    	{
    		if (this.serverURL!=null && this.serverURL.trim().equals("") && this.dbName!=null && this.dbName.trim().equals(""))
    		{
    			if((this.mongoClient=mongoClientConnPool.get(this.serverURL))== null)
    			{
    				this.mongoClient = new MongoClient(new MongoClientURI(this.serverURL));
    				mongoClientConnPool.put(this.serverURL, this.mongoClient);
    				mongoDBConnPool.put(this.serverURL, new HashMap<String, DB>());
    			}
    			if((this.database=mongoDBConnPool.get(this.serverURL).get(this.dbName))==null)
    			{
    				this.database = this.mongoClient.getDB(this.dbName);
    	            if(this.database != null)
    	            	Log(Level.INFO,"MongoDB Connection Created: " + this.serverURL + " dbName: " + this.dbName);
    				mongoDBConnPool.get(this.serverURL).put(this.dbName, this.database);
    			}
    		}
        }*/
    	
    	/* public void createConnection() {
        if (mongoClient == null || database == null) {
            mongoClient = new MongoClient(new MongoClientURI(this.serverURL));
            database = mongoClient.getDB(this.dbName);
            if(database != null)
            	Log(Level.INFO,"MongoDB Connection Created: " + this.serverURL + " dbName: " + this.dbName);
        }*/
    	 
    	// Modified by Vasu for Creating Connection Pool for reusing the Connection dated 2017.12.08
    	
/*    	if (mongoClient == null || database == null)
    	{
    		if (this.serverURL!=null && this.serverURL.trim().equals("") && this.dbName!=null && this.dbName.trim().equals(""))
    		{
    			if((this.mongoClient=mongoClientConnPool.get(this.serverURL))== null)
    			{
    				this.mongoClient = new MongoClient(new MongoClientURI(this.serverURL));
    				mongoClientConnPool.put(this.serverURL, this.mongoClient);
    				mongoDBConnPool.put(this.serverURL, new HashMap<String, DB>());
    			}
    			if((this.database=mongoDBConnPool.get(this.serverURL).get(this.dbName))==null)
    			{
    				this.database = this.mongoClient.getDB(this.dbName);
    	            if(this.database != null)
    	            	Log(Level.INFO,"MongoDB Connection Created: " + this.serverURL + " dbName: " + this.dbName);
    				mongoDBConnPool.get(this.serverURL).put(this.dbName, this.database);
    			}
    		}
        }
    }	
*/
   // public void closeConnection()
    {
    	/*if (mongoClient != null)
    	{
    		mongoClient.close();
    		database = null;
    		mongoClient = null;
           	Log(Level.INFO,"MongoDB Connection Closed: " + this.serverURL + " dbName: " + this.dbName);
        }*/
    //}

    /*public void saveFile(String id, String xmlContent)
    {
    	createConnection();
    	String newFileName = id;
        //File file = new File(fileName);

        //   String text = new String(Files.readAllBytes(Paths.get(fileName)), StandardCharsets.UTF_8);
        GridFS gfsPhoto = new GridFS(this.database);
        GridFSInputFile gfsFile = gfsPhoto.createFile(xmlContent.getBytes());
        gfsFile.setFilename(newFileName);
        gfsFile.save();
        Log(Level.INFO, "File Saved Successfullly " + id);
        closeConnection();
    }

    public void savePDFFile(String id, byte[] pdfData) {
    	createConnection();
        String newFileName = id;
        //File file = new File(fileName);

        //   String text = new String(Files.readAllBytes(Paths.get(fileName)), StandardCharsets.UTF_8);
        GridFS gfsPhoto = new GridFS(this.database);
        GridFSInputFile gfsFile = gfsPhoto.createFile(pdfData);
        gfsFile.setFilename(newFileName);
        gfsFile.save();
        System.out.println("File Saved Successfullly " + id);
        closeConnection();
    }

    public byte[] fetchFile(String id) throws IOException {
        return latestFetchFile(id);
        /*String newFileName = id;
        GridFS gfsPhoto = new GridFS(database);
        GridFSDBFile output = gfsPhoto.findOne(newFileName);
        if (output == null) {
            Log(Level.INFO, "XML Document Not found. Id: " + id);
            return null;
        }
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        //System.out.println(output);
        output.writeTo(baos);
        String r = new String(baos.toByteArray());
     
        //System.out.println(r);
        return r;*/
    }

   /* public byte[] latestFetchFile(String id) throws IOException {
    	createConnection();
        String newFileName = id;
        GridFS gfsPhoto = new GridFS(this.database);
        //Map<String, String> hm = new HashMap<String, String>();
        //hm.put("filename", id);
       // hm.put("uploadDate", "-1");
        //BasicDBObject query = new BasicDBObject("metadata", new BasicDBObject(hm));
        
        BasicDBObject sort = new BasicDBObject("uploadDate", -1);//sort in descending order
        BasicDBObject query = new BasicDBObject("filename", id);
        
        List<GridFSDBFile> outputFiles = gfsPhoto.find(query, sort);//findOne(newFileName);

        if (outputFiles == null || outputFiles.size() == 0) {
            Log(Level.INFO, "Document Not found. Id: " + id);
            return null;
        }

        GridFSDBFile output = outputFiles.get(0);
       /* for (int i = 0; i < outputFiles.size(); i++) {

            System.out.println(outputFiles.get(i).getUploadDate() + " " + outputFiles.get(i).getId());
        }*/
      /*  ByteArrayOutputStream baos = new ByteArrayOutputStream();
        //System.out.println(output);
        output.writeTo(baos);
        closeConnection();
        return baos.toByteArray();
        //System.out.println(r);
      //  return r;
    }*/

    /*private static void Log(Level level, String msg) {
        //ServiceLogger.Log(MongoXmlHandler.class.getName(), level, msg);
    }*/

    /*private void static Log(Level level, Exception e) {
       ServiceLogger.Log(MongoXmlHandler.class.getName(), level, e);
    }*/

}
