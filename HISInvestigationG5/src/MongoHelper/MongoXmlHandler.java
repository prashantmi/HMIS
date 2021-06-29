/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MongoHelper;

//import DataHelper.PropertiesHelper;
//import DataHelper.ServiceConfiguration;
//import FileHandler.XMLFileHandler;
//import Logging.ServiceLogger;
import hisglobal.config.HISConfig;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;

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
 */
public class MongoXmlHandler
{
    //private static String connectionURI = "mongodb://10.226.30.152:27017";
   private String dbName = HISConfig.HIS_MONGOSERVER_INV_DBNAME;
   // private static String connectionURI = PropertiesHelper.getMongoConnectionURI();//ServiceConfiguration.MongoConnectionURI;
    private static MongoClient mongoClient;
    private static DB database;
   // String dbName = PropertiesHelper.getMongoDBName();
    private static MongoXmlHandler myInstance = null;

    public static MongoXmlHandler getInstance(String dbName_p) {
        if (null == myInstance || null == mongoClient || null == database) {
            myInstance = new MongoXmlHandler(dbName_p);
        }
        
        return myInstance;
    }

    private MongoXmlHandler(String dbName_p) {
    	this.dbName = dbName_p;
        createConnection();
    }

    public void createConnection() {
    	
    	if (mongoClient == null || database == null) {

    		//        	String connectionURI = HISConfig.HIS_MONGOSERVER_INV_CONNECTION;
        	
    		//String connectionURI = "mongodb://10.10.10.72:27017";
    	//	String connectionURI = "mongodb://10.10.36.128:27017";
    		
        	//String connectionURI = "mongodb://aiims_patna:aiims_patna123@10.0.1.38:27017/?authSource=cdacpatprofile8";
    		mongoClient = new MongoClient(new MongoClientURI(HISConfig.HIS_MONGODB_SERVER_INV_URL));

            //mongoClient = new MongoClient(new MongoClientURI(connectionURI));
            database = mongoClient.getDB(dbName);
            if(database != null)
            	Log(Level.INFO,"MongoDB Connection Created: " + HISConfig.HIS_MONGODB_SERVER_INV_URL + " dbName: " + this.dbName);
        }
    	
//        if (mongoClient == null || database == null) {
//        	String connectionURI = "mongodb://" + HISConfig.HIS_MONGOSERVER_USEHIS_MONGOSERVER_INV_DBNAMERNAME + ":" +
//        							HISConfig.HIS_MONGOSERVER_PASSWORD + "@" +
//        							HISConfig.HIS_MONGOSERVER_IPADDRESS;
//        	//mongodb://test1:test123@localhost:27017
//            mongoClient = new MongoClient(new MongoClientURI(connectionURI));
//            database = mongoClient.getDB(this.dbName);
//            if(database != null)
//            	Log(Level.INFO,"MongoDB Connection Created: " + connectionURI + " dbName: " + this.dbName);
//        }
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
        GridFSInputFile gfsFile = gfsPhoto.createFile(pdfData);
        gfsFile.setFilename(newFileName);
        gfsFile.save();
        System.out.println("File Saved Successfullly " + id);
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

    public byte[] latestFetchFile(String id) throws IOException {
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
        return baos.toByteArray();
        //System.out.println(r);
      //  return r;
    }

    private void Log(Level level, String msg) {
       // ServiceLogger.Log(MongoXmlHandler.class.getName(), level, msg);
    }

    private void Log(Level level, Exception e) {
       // ServiceLogger.Log(MongoXmlHandler.class.getName(), level, e);
    }

}
