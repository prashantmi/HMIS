/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mongoapp;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.Level;

/**
 *
 * @author Siddharth Srivastava <siddharthsrivastava@cdac.in>
 */
public class MongoDemo {
    private static String connectionURI = "mongodb://localhost:27017";
    MongoClient mongoClient;
    DB database;
    String dbName = "cdacpatprofile8";
    
    public static void main(String args[]) throws IOException
    {
        MongoDemo md = new MongoDemo();
      //  md.saveFile( "1","F:\\1011100003622.xml");
      //  md.fetchFile("1");
    }
    
    MongoDemo()
    {
        mongoClient = new MongoClient(new MongoClientURI(connectionURI));
        //MongoDatabase database = mongoClient.getDatabase("cdacdemo");
        database = mongoClient.getDB(dbName);//.getDatabase("cdacdemo");
    }
    
    public void demonstrateMongo()
    {  
        int c = 0;
        while(c != -1)
        {
            
        }
        

        switch(c)
        {
            case 1:
                
        }       
    }
    public String saveFile(String id, String fileName) throws IOException
    {
        String newFileName = id;
	//File file = new File(fileName);
        
        String text = new String(Files.readAllBytes(Paths.get(fileName)), StandardCharsets.UTF_8);                
	GridFS gfsPhoto = new GridFS(database);
        
        if(fetchFile(newFileName) != null)
        {
            String msg = "File already exisits";
            System.out.println(msg);
            return msg;
        }
	GridFSInputFile gfsFile = gfsPhoto.createFile(text.getBytes());
        
	gfsFile.setFilename(newFileName);
	gfsFile.save();
        
        String msg = "File Saved Successfullly ";
        System.out.println(msg + id);
        return msg;
    }
    
    public String fetchFile(String id) throws IOException
    {
        return latestFetchFile(id);
        /*
        String newFileName = id;
	GridFS gfsPhoto = new GridFS(database);
	GridFSDBFile output = gfsPhoto.findOne(newFileName);
        if(output == null)
            return null;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        System.out.println(output);
        output.writeTo(baos);   
        String r = new String(baos.toByteArray());
	System.out.println(r);
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
            //Log(Level.INFO, "Document Not found. Id: " + id);
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

    
    
}
