package new_investigation.transactions.controller.utl;


 

import hisglobal.config.HISConfig;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.utility.Sequence;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import new_investigation.InvestigationConfig;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;

public class MongoXmlHandler {

 
    private static MongoClient mongoClient;
    private static DB database;
    String dbName = HISConfig.HIS_MONGOSERVER_INV_DBNAME;
    //static String collectionName=HISConfig.HIS_MONGOSERVER_INV_DBNAME_COLLECTION_NAME;
    private static MongoXmlHandler myInstance = null;
    private static final Object lock = new Object();

    /*public static MongoXmlHandler getInstance() {
        if (null == myInstance || null == mongoClient || null == database) {
            myInstance = new MongoXmlHandler();
        }
        return myInstance;
    }*/

    public static MongoXmlHandler getInstance() {
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
    
    private MongoXmlHandler() {
        createConnection();
    }

    public void createConnection() {
    	System.out.println("HISConfig.HIS_MONGOSERVER_INV_CONNECTION : "+HISConfig.HIS_MONGOSERVER_INV_CONNECTION);
        if (mongoClient == null || database == null) {
        	
        	//String connectionURI = HISConfig.HIS_MONGOSERVER_INV_CONNECTION;
        	//String connectionURI = "mongodb://127.0.0.1:27017";
        //	String connectionURI = "mongodb://10.10.10.48:27017";
        	//String connectionURI = "mongodb://10.226.2.169:27017";
        	//String connectionURI = "mongodb://10.10.10.72:27017";
        //	String connectionURI = "mongodb://10.10.36.128:27017";
        	//String connectionURI = "mongodb://aiims_patna:aiims_patna123@10.0.1.38:27017/?authSource=cdacpatprofile8";
       // 	String connectionURI =   "mongodb://localhost:27017";
        	//String connectionURI = "mongodb://aiims_patna:aiims_patna123@127.0.0.1:27017/?authSource=cdacpatprofile8";
          	//String connectionURI ="mongodb://10.10.10.96:27017";
        
          	//System.out.println("connectionURI : "+connectionURI);
            //mongoClient = new MongoClient(new MongoClientURI(connectionURI));
    		mongoClient = new MongoClient(new MongoClientURI(HISConfig.HIS_MONGODB_SERVER_INV_URL));

        	System.out.println("dbName : "+dbName);
            dbName =  "cdacpatprofile8";
            System.out.println("updated dbName : "+dbName);
            database = mongoClient.getDB(dbName);
        }
    }

    public static DBCollection getDbCollection()
    {
    	
    	return database.getCollection("patientDocumentsFiles1001");
    	
    	
    }

    public static byte[]  latestFetchFile(String id) throws IOException {
        String newFileName = id;
      /*  GridFS gfsPhoto = new GridFS(database,collectionName);*/
        GridFS gfsPhoto = new GridFS(database);
        //Map<String, String> hm = new HashMap<String, String>();
        //hm.put("filename", id);
       // hm.put("uploadDate", "-1");
        //BasicDBObject query = new BasicDBObject("metadata", new BasicDBObject(hm));
        
        BasicDBObject sort = new BasicDBObject("uploadDate", -1);//sort in descending order
        BasicDBObject query = new BasicDBObject("filename", id);
        
        List<GridFSDBFile> outputFiles = gfsPhoto.find(query, sort);//findOne(newFileName);

        if (outputFiles == null || outputFiles.size() == 0) {
         //   Log(Level.INFO, "Document Not found. Id: " + id);
        	
        //	System.out.println("Document Not found. Id: " + id);
            return null;
        }

        GridFSDBFile output = outputFiles.get(0);
       /* for (int i = 0; i < outputFiles.size(); i++) {

        }*/
       // System.out.println(outputFiles.get(0).getUploadDate() + " " + outputFiles.get(0).getId());
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        //System.out.println(output);
        output.writeTo(baos);
        String r = new String(baos.toByteArray());
       // System.out.println(r);
        return baos.toByteArray();
    }
    
    public static byte[]  latestFetchFile1(String id) throws IOException {
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
         //   Log(Level.INFO, "Document Not found. Id: " + id);
        	
        //	System.out.println("Document Not found. Id: " + id);
            return null;
        }

        GridFSDBFile output = outputFiles.get(0);
       /* for (int i = 0; i < outputFiles.size(); i++) {

        }*/
       // System.out.println(outputFiles.get(0).getUploadDate() + " " + outputFiles.get(0).getId());
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        //System.out.println(output);
        output.writeTo(baos);
        String r = new String(baos.toByteArray());
       // System.out.println(r);
        return baos.toByteArray();
    }
 
    
    

}