/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MongoHelper;

import DataHelper.PropertiesHelper;
import DataHelper.ServiceConfiguration;
import FileHandler.XMLFileHandler;
import Logging.ServiceLogger;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

/**
 *
 * @author Siddharth Srivastava <siddharthsrivastava@cdac.in>
 */
public class MongoXmlHandler {

    private static String connectionURI = PropertiesHelper.getMongoConnectionURI();//ServiceConfiguration.MongoConnectionURI;
    private static MongoClient mongoClient;
    private static DB database;
  
    String dbName = PropertiesHelper.getMongoDBName();
    //String collectionName=PropertiesHelper.getMongoDBCollectionName();
    private static  volatile MongoXmlHandler myInstance = null;
    private static final Object lock = new Object();
    
    public static  MongoXmlHandler getInstance() {
    	MongoXmlHandler r = myInstance;
        if (null == r || null == mongoClient || null == database) {  
        	synchronized(lock) {
        		r = myInstance;
        		if(r == null) {
        			r = new MongoXmlHandler();
        			myInstance = r;
        		}
        	}
        }                
        return r;
    }
//    public static  MongoXmlHandler getInstance() {
//    	 
//        if (null == myInstance || null == mongoClient || null == database) {  
//        	
//        			myInstance = new MongoXmlHandler();
//        		
//        	}
//                       
//        return myInstance;
//    }


 //   private static int k = 0;
    private MongoXmlHandler() {
  //  	System.out.println("called " + ++k);
        createConnection();
    }

    public void createConnection() {
        if (mongoClient == null || database == null) {
        	
            mongoClient = new MongoClient(new MongoClientURI(connectionURI));
            database = mongoClient.getDB(dbName);
            
             
            if(database != null)
            	Log(Level.INFO,"MongoDB Connection Created: " + connectionURI + " dbName: " + dbName);
        }
    }
    
    public static void closeConnection()
    {
    	if(mongoClient != null )
    	{
    		mongoClient.close();
    	}
    }

    public void saveFile(String id, String xmlContent) {
        String newFileName = id;
        //File file = new File(fileName);

        //   String text = new String(Files.readAllBytes(Paths.get(fileName)), StandardCharsets.UTF_8);
        GridFS gfsPhoto = new GridFS(database);
        GridFSInputFile gfsFile = gfsPhoto.createFile(xmlContent.getBytes());
        gfsFile.setFilename(newFileName);
        gfsFile.save();
        Log(Level.INFO, "File Saved Successfullly " + id);
    }

    public void savePDFFile(String id, byte[] pdfData) {
        String newFileName = id;
        //File file = new File(fileName);

        //   String text = new String(Files.readAllBytes(Paths.get(fileName)), StandardCharsets.UTF_8);
        GridFS gfsPhoto = new GridFS(database);
        //GridFS gfsPhoto = new GridFS(database,collectionName);
        //GridFS gfsPhoto = new GridFS(collection);
        GridFSInputFile gfsFile = gfsPhoto.createFile(pdfData);
        gfsFile.setFilename(newFileName);
        gfsFile.save();
        System.out.println("File Saved Successfullly " + id);
    }

    public String fetchFile(String id) throws IOException {
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

    public String latestFetchFile(String id) throws IOException {
        String newFileName = id;
        GridFS gfsPhoto = new GridFS(database);
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
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        //System.out.println(output);
        output.writeTo(baos);
        String r = new String(baos.toByteArray());
        //System.out.println(r);
        return r;
    }

    private void Log(Level level, String msg) {
        ServiceLogger.Log(MongoXmlHandler.class.getName(), level, msg);
    }

    private void Log(Level level, Exception e) {
        ServiceLogger.Log(MongoXmlHandler.class.getName(), level, e);
    }

    
    public byte[] latestFetchFileByte(String id) throws IOException {
        String newFileName = id;
        GridFS gfsPhoto = new GridFS(database);
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
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        //System.out.println(output);
        output.writeTo(baos);
        String r = new String(baos.toByteArray());
        //System.out.println(r);
        return baos.toByteArray();
    }
}
