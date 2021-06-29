/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MongoHelper;

import DataHelper.PropertiesHelper;
import DataHelper.PGDataHelper;
import DataHelper.ServiceConfiguration;
import DataProcessing.SavePDF;
import FileHandler.XMLFileHandler;
import Ftp.FTPCredentialDemo;
import Ftp.HisAppletConfigurator;
import Ftp.JakartaFtpWrapper;
import Logging.ServiceLogger;

import com.jscape.inet.ftp.FtpException;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

import org.bson.Document;
import org.bson.types.ObjectId;

/**
 *
 * @author Ashutosh Kumar
 */
public class MongoToFtpFileTransfer {

    private static String connectionURI = PropertiesHelper.getMongoConnectionURI();//ServiceConfiguration.MongoConnectionURI;
    private static MongoClient mongoClient;
    private static DB database;
  
    String dbName = PropertiesHelper.getMongoDBName();
    //String collectionName=PropertiesHelper.getMongoDBCollectionName();
    private static  volatile MongoToFtpFileTransfer myInstance = null;

    private static final Object lock = new Object();
    
    public static  MongoToFtpFileTransfer getInstance() {
    	MongoToFtpFileTransfer r = myInstance;
        if (null == r || null == mongoClient || null == database) {  
        	synchronized(lock) {
        		r = myInstance;
        		if(r == null) {
        			r = new MongoToFtpFileTransfer();
        			myInstance = r;
        		}
        	}
        }                
        return r;
    }

 //   private static int k = 0;
    private MongoToFtpFileTransfer() {
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
       
        GridFS gfsPhoto = new GridFS(database);
        GridFSInputFile gfsFile = gfsPhoto.createFile(pdfData);
        gfsFile.setFilename(newFileName);
        gfsFile.save();
        System.out.println("File Saved Successfullly " + id);
    }

    public String fetchFile(String id) throws IOException {
        return latestFetchFile(id);
    }

    public String latestFetchFile(String id) throws IOException {
        String newFileName = id;
        GridFS gfsPhoto = new GridFS(database);
        
        BasicDBObject sort = new BasicDBObject("uploadDate", -1);//sort in descending order
        BasicDBObject query = new BasicDBObject("filename", id);
        
        List<GridFSDBFile> outputFiles = gfsPhoto.find(query, sort);//findOne(newFileName);

        if (outputFiles == null || outputFiles.size() == 0) {
            Log(Level.INFO, "Document Not found. Id: " + id);
            return null;
        }

        GridFSDBFile output = outputFiles.get(0);
        
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
    
    public List<String> getFiledetails(List<String> crNos) throws IOException, ParseException {

    	GridFS gfsPhoto = new GridFS(database);
       // GridFSDBFile output;
        String fileName = null;
        
        List<String> filesCopied = new ArrayList<String>();
       // List<ObjectId> filesIdToBeDeleted = new ArrayList<ObjectId>();
        int countPDFFilesCopied = 0;
        int countXMLFilesCopied = 0;
        int countTotalNumberOfPDFFiles = 0;
        int countTotalNumberOfXMLFiles = 0;
        boolean saveXMLFile;
        //ObjectId objectId = null;
        
        String MongoToFtpURI = null;
        String pdfFtpurl = null;
        String FTPConnectionUsername = PropertiesHelper.getFTPConnectionUsername();
        String FTPConnectionPassword = PropertiesHelper.getFTPConnectionPassword();
        String FTPConnectionIP = PropertiesHelper.getFTPConnectionIP();
        String FTPSaveTempXMLFilePath = PropertiesHelper.getFTPSaveTempXMLFilePath();
        String FTPSaveTempPDFFilePath = PropertiesHelper.getFTPSaveTempPDFFilePath();
        
        
        int totalCRNOs = 0;
        
        BasicDBObject sort = new BasicDBObject("uploadDate", -1);//sort in descending order
        
        BasicDBObject query = new BasicDBObject();
        //query.put("filename",new BasicDBObject("$regex",id).append("$options", "i"));
        
        for(String crNo : crNos){
        	
        	
        	
        	BufferedOutputStream bos=null;
        	String hoscode = crNo.substring(0,5);
			String year = crNo.substring(5,7);
         
			String insideyear=PGDataHelper.getInsideYear(crNo);
			String count=  PGDataHelper.getcount(crNo);
			//System.out.println("crNo : "+crNo+ "insideyear : "+insideyear+ "hoscode :"+hoscode);
 
			//PropertiesHelper.getFTPConnectionURI()+"/"+hoscode +"/"+"20"+year+"/"+insideyear+"/"+patCRNo+"/"
			
			MongoToFtpURI = PropertiesHelper.getMongoToFtpURI() +"/"+hoscode +"/"+"20"+year+"/"+insideyear+"/"+count+"/"+crNo+"/";
        	pdfFtpurl = PropertiesHelper.getFTPConnectionURI() +"/"+hoscode +"/"+"20"+year+"/"+insideyear+"/"+count+"/"+crNo+"/";
			
        	/*MongoToFtpURI = PropertiesHelper.getMongoToFtpURI() +"/"+crNo+"/";
        	pdfFtpurl = PropertiesHelper.getFTPConnectionURI() +"/"+crNo+"/";*/
        	
        	totalCRNOs++;
        	
        	query.put("filename",new BasicDBObject("$regex", crNo));
            
            List<GridFSDBFile> outputFiles = gfsPhoto.find(query, sort);//findOne(newFileName);

            if (outputFiles == null || outputFiles.size() == 0) {
                Log(Level.INFO, "No output file found for crNo : " + crNo);
                continue;
            }
            
            saveXMLFile = true;
            
            for (int i = 0; i < outputFiles.size(); i++) {
            
            	fileName =  outputFiles.get(i).getFilename();
            	//System.out.println("fileName -> "  +fileName+" : Upload Date -> "+outputFiles.get(i).getUploadDate()+" : Object ID -> "+ outputFiles.get(i).getId());
            	
            	if(fileName != null && fileName.equals(crNo)){
            		
            		//totalSizeXML += outputFiles.get(i).getChunkSize();
            		//uploadXMLDate = outputFiles.get(i).getUploadDate();
            		countTotalNumberOfXMLFiles++;
            		
            		//Log(Level.INFO, "InputStream -> "+ outputFiles.get(i).getInputStream());
            		
            		if(saveXMLFile){
            			Log(Level.INFO, "Transfer XML -> "  +fileName);
            			
            			byte[] byteArr = SavePDF.getReport(fileName);
            			String base64EncodedString = org.apache.commons.codec.binary.Base64.encodeBase64String(byteArr);
            			SavePDF.byteArrayToFileXML(base64EncodedString);
            			
            			Log(Level.INFO, "Upload File To FTP URL : "+MongoToFtpURI+fileName+".xml");
            			
            			try {
            				
            				
            				URL urlftp =new URL(pdfFtpurl+fileName+".xml");
            				URLConnection urlc=	urlftp.openConnection();
            				
            				try
            				{
            					bos=new BufferedOutputStream(urlc.getOutputStream());
            					
            				}
            				catch(Exception ex)
            				{
            					System.out.println("<!-- CreateRequisitionDirectory -->");
            					//ex.printStackTrace();
            				}
            				
            				if(bos==null)
            				{
            					String[] folder=pdfFtpurl.replace("/", "#").split("#");
            					
            					/*if(folder[2]!=null && folder[2].replace("@", "#").split("#").length>1)
            					createDirectoryStructure(folder[2].replace("@", "#").split("#")[1],folder);
            					else
            					createDirectoryStructure(folder[2],folder);	
            					
            					*/
            					
            					if(folder[2]!=null && folder[2].contains("%40") && folder[2].replace("@", "#").split("#").length>=1)
            		   			{
            		   				String data=folder[2];
            		   				data=data.replaceAll("%40", "@");
            		   				createDirectoryStructure(data.replace("@", "#").split("#")[2],folder);
            		   			}
            		   			else if(folder[2]!=null && folder[2].replace("@", "#").split("#").length>=1)
            		   			{
            		   				String data=folder[2];
            		   				data=data.replaceAll("%40", "@");
            		   				createDirectoryStructure(data.replace("@", "#").split("#")[1],folder);
            		   			}
            		   			else
            		   			{
            		   				createDirectoryStructure(folder[2],folder);
            		   			}
            					
            					
            					bos=new BufferedOutputStream(urlc.getOutputStream());
            				}
            				
            				//Log(Level.INFO, "end");
            				
            			}catch (Exception e) 
            			{
            				System.out.println("<!-- CreateRequisitionDirectory -->");
            				//e.printStackTrace();
            			}finally{
            				try {
            					if(bos!=null)
            						bos.close();
            					
            				} catch (IOException e) {
            					
            					e.printStackTrace();
            				}
            			}  
            			
            			FTPCredentialDemo.uploadfile(FTPConnectionIP, FTPConnectionUsername, FTPConnectionPassword, FTPSaveTempXMLFilePath, MongoToFtpURI+fileName+".xml");
            			
            			saveXMLFile = false;
            			countXMLFilesCopied++;
            		}
            	
            	}
            	
            	if(fileName != null && fileName.endsWith(".pdf")){
            		
                	countTotalNumberOfPDFFiles++;

                			byte[] byteArr = SavePDF.getReport(fileName);
                			String base64EncodedString = org.apache.commons.codec.binary.Base64.encodeBase64String(byteArr);
                			SavePDF.byteArrayToFile(base64EncodedString);
   
                			Log(Level.INFO, "Upload File To FTP URL : "+MongoToFtpURI+fileName);
                			
                			try {
                				
                				
                				URL urlftp =new URL(pdfFtpurl+fileName);
                				URLConnection urlc=	urlftp.openConnection();
                				
                				try
                				{
                					bos=new BufferedOutputStream(urlc.getOutputStream());
                					
                				}
                				catch(Exception ex)
                				{
                					System.out.println("<!-- CreateRequisitionDirectory -->");
                					//ex.printStackTrace();
                				}
                				
                				if(bos==null)
                				{
                					String[] folder=pdfFtpurl.replace("/", "#").split("#");
                					
                					if(folder[2]!=null && folder[2].replace("@", "#").split("#").length>1)
                					createDirectoryStructure(folder[2].replace("@", "#").split("#")[1],folder);
                					else
                					createDirectoryStructure(folder[2],folder);	
                					
                					bos=new BufferedOutputStream(urlc.getOutputStream());
                				}
                				
                				//Log(Level.INFO, "end");
                				
                			}catch (Exception e) 
                			{
                				System.out.println("<!-- CreateRequisitionDirectory -->");
                				//e.printStackTrace();
                			}finally{
                				try {
                					if(bos!=null)
                						bos.close();
                					
                				} catch (IOException e) {
                					
                					e.printStackTrace();
                				}
                			} 
                			
                			FTPCredentialDemo.uploadfile(FTPConnectionIP, FTPConnectionUsername, FTPConnectionPassword, FTPSaveTempPDFFilePath, MongoToFtpURI+fileName);
                			
                    		filesCopied.add(fileName);
            				countPDFFilesCopied++;
            	}
            	
            	
            }
            
            
            Log(Level.INFO, "CR Numbers traversed till now : "+totalCRNOs);
        }
        
        try{
        } catch(Exception ex){
        	ex.printStackTrace();  
        }
        
        Log(Level.INFO, "Total Number of CR Numbers traversed : "+totalCRNOs);
       // Log(Level.INFO, "Total Number of XML files : "+countTotalNumberOfXMLFiles);
       // Log(Level.INFO, "Number of XML files migrated : "+countXMLFilesCopied);
        Log(Level.INFO, "Total Number of PDF files : "+countTotalNumberOfPDFFiles);
        Log(Level.INFO, "Number of PDF files migrated : "+countPDFFilesCopied);
        
        BufferedWriter out = null;
		FileWriter fstream = null;
        
        try {
			fstream = new FileWriter("output.txt", true); //true tells to append data.
			out = new BufferedWriter(fstream);
			out.write("\n\nTotal Number of CR Numbers traversed : "+totalCRNOs+"\n");
			out.write("Total Number of PDF files : "+countTotalNumberOfPDFFiles+"\n");
			out.write("Number of PDF files migrated : "+countPDFFilesCopied+"\n");
		} catch (IOException e1) {
			e1.printStackTrace();
		}finally {
			if(out != null) {
		        try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		    }
			if(fstream != null) {
		        try {
		        	fstream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		    }
		}
        
        
        
        return filesCopied;
      
    }
    
    private synchronized static void createDirectoryStructure(String ftpserver, String[] folders) {
		JakartaFtpWrapper ftp = null;
		try {
			 ftp = new JakartaFtpWrapper();
			
			String ftpUserName= PropertiesHelper.getFTPConnectionUsername();
			String ftpUserPassword=PropertiesHelper.getFTPConnectionPassword();
			
					
			System.out.println("Connecting to " + ftpserver + "ftpUserName :"+ftpUserName+" ftpUserPassword :"+ftpUserPassword);
			if (ftp.connectAndLogin(PropertiesHelper.getFTPConnectionIP(), ftpUserName, ftpUserPassword))
			{
				System.out.println("Connected to " + ftpserver);
				ftp.setPassiveMode(true);
			//	ftp.changeWorkingDirectory("ftpserver");
				System.out.println("Present Working Directory :"+ftp.pwd());
				for(int i=3;i<folders.length;i++)
				{
						//System.out.println("directory "+folders[i]+" created");
				ftp.mkd(folders[i]);
				ftp.changeWorkingDirectory(folders[i]);
				}
			}
			else 
			{
				System.out.println("Unable to connect to" + ftpserver);
			}
			
		}
		catch(Exception e) 
		{
			e.printStackTrace();
		}
	
	finally {
		try
		{
			if(ftp!=null)
			{
			ftp.logout();
			ftp.disconnect();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	}
    

}
