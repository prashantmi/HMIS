 
package new_investigation.reportGenerator.MongoHelper;

import new_investigation.reportGenerator.DataHelper.PGDataHelper;
import new_investigation.reportGenerator.DataHelper.PropertiesHelper;
 
 
import new_investigation.reportGenerator.Logging.ServiceLogger;

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

import new_investigation.reportGenerator.mongoapp.DeletePdfMongo;

 
public class MongoXmlHandler {

    private static String connectionURI = PropertiesHelper.getMongoConnectionURI();//ServiceConfiguration.MongoConnectionURI;
    private static MongoClient mongoClient;
    private static DB database;
  
    String dbName = PropertiesHelper.getMongoDBName();
    //String collectionName=PropertiesHelper.getMongoDBCollectionName();
    private static  volatile MongoXmlHandler myInstance = null;
    private static final Object lock = new Object();
    private static final int var=0;

    
    public static int  getInstance1() {
    	              
        return var;
    }
    
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
  //  	Log(Level.INFO, "called " + ++k);
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
        Log(Level.INFO, "File Saved Successfullly " + id);
    }

    public String fetchFile(String id) throws IOException {
        return latestFetchFile(id);
         
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
        
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        //Log(Level.INFO, output);
        output.writeTo(baos);
        String r = new String(baos.toByteArray());
        //Log(Level.INFO, r);
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
        
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        //Log(Level.INFO, output);
        output.writeTo(baos);
        String r = new String(baos.toByteArray());
        //Log(Level.INFO, r);
        return baos.toByteArray();
    }
    
    
    public String DeletefetchFileXML(String id) throws IOException {   //after finding new xml for a certain crno delete finding xmls before save new xml which contain old xml data also for a particular crno. by chandann on 29-Aug-2019
        return DeletefetchXML(id);
         
    }

    
    
    public String DeletefetchXML(String id) throws IOException {
    	
    	 String newFileName = id;
         GridFS gfsPhoto = new GridFS(database);
         //Map<String, String> hm = new HashMap<String, String>();
         //hm.put("filename", id);
        // hm.put("uploadDate", "-1");
         //BasicDBObject query = new BasicDBObject("metadata", new BasicDBObject(hm));
         
         BasicDBObject sort = new BasicDBObject("uploadDate", -1);//sort in descending order
         BasicDBObject query = new BasicDBObject("filename", id);
         
          gfsPhoto.remove(query);

         
         //Log(Level.INFO, r);
         
    	
    	
    	 
         
        //Log(Level.INFO, r);
        return null;
    }

    
    public static DBCollection getDbCollectionXML()
    {
    	
    	return database.getCollection("cdacpatprofile8");
    	
    	
    }
    
    
    
    public String DeletePdfs(BasicDBObject query) throws IOException {
    	
    	
    	//Log(Level.INFO, "===qyery fo"+query.toString());
   	 //String newFileName = id;
        GridFS gfsPhoto = new GridFS(database);
        //Map<String, String> hm = new HashMap<String, String>();
        //hm.put("filename", id);
       // hm.put("uploadDate", "-1");
        //BasicDBObject query = new BasicDBObject("metadata", new BasicDBObject(hm));
        
       //// BasicDBObject sort = new BasicDBObject("uploadDate", -1);//sort in descending order
       // BasicDBObject query = new BasicDBObject("filename", id);
        try{
        	Log(Level.INFO, "Delete Pdfs......call before deletion");
         gfsPhoto.remove(query);
         DeletePdfMongo.time.cancel();
      	PGDataHelper.getInstance().insertInReportDeleteLog("1");
        
        }
        catch(Exception e)
        {
        	Log(Level.INFO, "error while report delete scheduler:"+e);
        	DeletePdfMongo.time.cancel();	
        	PGDataHelper.getInstance().insertInReportDeleteLog("0");
        }
        
        //Log(Level.INFO, r);
        
   	
   	
   	 
        
       //Log(Level.INFO, r);
       return null;
   }
    
    
}
