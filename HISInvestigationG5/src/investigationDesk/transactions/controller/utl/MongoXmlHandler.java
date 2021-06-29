package investigationDesk.transactions.controller.utl;


 

import hisglobal.config.HISConfig;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;

public class MongoXmlHandler {

 
   // private static String connectionURI = "mongodb://10.226.30.152:27017";
 
    private static MongoClient mongoClient;
    private static DB database;
    String dbName = "cdacpatprofile8";
    private static MongoXmlHandler myInstance = null;

    public static MongoXmlHandler getInstance() {
    	
    	System.out.println("in Mongo Xml Handler getinstance");
        if (null == myInstance || null == mongoClient || null == database) {
            myInstance = new MongoXmlHandler();
        }
       // System.out.println("mongo db:"+dbName);	
        System.out.println("return mongo server url:"+HISConfig.HIS_MONGODB_SERVER_INV_URL);
        System.out.println("return mongo client:"+mongoClient);
        return myInstance;
    }

    private MongoXmlHandler() {
        createConnection();
    }

    public void createConnection() {
    	
    //	System.out.println("hispathmongo:"+HISConfig.HIS_MONGODB_SERVER_INV_URL);
    	 
  //  	 System.out.println("mongo dbname:"+dbName);
    	 System.out.println("create connection in mongo xml handler");
    	 
        if (mongoClient == null || database == null) {
     //   	System.out.println("mongo server url:"+HISConfig.HIS_MONGODB_SERVER_INV_URL);
    //    	System.out.println("mongo db:"+dbName);	
    		//String connectionURI = "mongodb://10.10.10.72:27017";
    		mongoClient = new MongoClient(new MongoClientURI(HISConfig.HIS_MONGODB_SERVER_INV_URL));
            
        	//mongoClient = new MongoClient(new MongoClientURI(connectionURI));
            System.out.println("mongo client:"+mongoClient);
            database = mongoClient.getDB(dbName);
            System.out.println("mongo connection create successfully");
        }
    }


    public static byte[]  latestFetchFile(String id) throws IOException {
    	try {
    		  mongoClient.getAddress();
    		} catch (Exception e) {
    		  System.out.println("Mongo is down");
    		  mongoClient.close();
    		 // return;
    		}
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
        	
        	System.out.println("Document Not found Id: " + id);
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