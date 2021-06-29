package registration.mongodb;


import hisglobal.config.HISConfig;
import hisglobal.utility.filetransfer.config.FileTransferConfig;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;


import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;

public class MongoXmlHandler {

 
   // private static String connectionURI = "mongodb://10.226.30.152:27017";
 
    private static MongoClient mongoClient;
    private static DB database;
    String dbName = FileTransferConfig.MONGO_DATASOURCE_PATIENT_REGISTRATION_IMAGE_UPLOAD;
    private static MongoXmlHandler myInstance = null;

    public static MongoXmlHandler getInstance() {
        if (null == myInstance || null == mongoClient || null == database) {
            myInstance = new MongoXmlHandler();
        }
        return myInstance;
    }

    private MongoXmlHandler() {
        createConnection();
    }

    public void createConnection() {
    	System.out.println(HISConfig.HIS_MONGODB_SERVER_URL);
        if (mongoClient == null || database == null) {
            mongoClient = new MongoClient(new MongoClientURI(HISConfig.HIS_MONGODB_SERVER_URL));
            database = mongoClient.getDB(dbName);
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

    public static byte[]  latestFetchFile(String id) throws IOException {
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