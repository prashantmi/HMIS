
package new_investigation.reportGenerator.mongoapp;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

import com.google.gson.JsonObject;

 

import new_investigation.reportGenerator.DataHelper.JakartaFTPWrapper;
import new_investigation.reportGenerator.DataHelper.PGDataHelper;
//import new_investigation.reportGenerator.DataHelper.PGDataHelper_kpran;
import new_investigation.reportGenerator.DataHelper.PropertiesHelper;
import new_investigation.reportGenerator.DataProcessing.PDFProcessing;
import new_investigation.reportGenerator.DataProcessing.PGTemplateProcessing;
import new_investigation.reportGenerator.Logging.ServiceLogger;
import new_investigation.reportGenerator.MongoHelper.MongoXmlHandler;
import new_investigation.reportGenerator.TemplateHelper.vo.ResultEntryVO;
import new_investigation.reportGenerator.vo.ReportGeneratorVO;

@SuppressWarnings("unused")
public class ReportGeneratorCls {
	
	 
	
	public static JsonObject startReportGenerator(ResultEntryVO resultEntryVO)  {
		JsonObject jsonResponse = new JsonObject();
        try {
        	
           boolean pgFlag = true;
           if(PGDataHelper.createPostgresConnection() == null)
           {
              pgFlag = false;
              Log(Level.INFO, "Cannot connect to Postgres");
           }


           boolean mongoFlag = true;       
           if(PropertiesHelper.getISFtporMongo())
           {
           	 if(MongoXmlHandler.getInstance() == null)
           	{
           		mongoFlag = false;
           	   Log(Level.INFO, "Cannot connect to mongodb"); 
           	}
           } else {
                   	   
           	JakartaFTPWrapper ftp = new JakartaFTPWrapper();
           	 
           	
           	String ftpUserName=PropertiesHelper.getFTPConnectionUsername();
           	String ftpUserPassword=PropertiesHelper.getFTPConnectionPassword();
           	String ftpserver=PropertiesHelper.getFTPConnectionURI();
           	
           	
              
           	if (ftp.connectAndLogin(PropertiesHelper.getFTPConnectionIP(), ftpUserName, ftpUserPassword))
           	{
           		Log(Level.INFO,"FTP Connection Created: " + PropertiesHelper.getFTPConnectionURI());
           		Log(Level.INFO, "FTP Connection Created: " + PropertiesHelper.getFTPConnectionURI());
               
               if(ftp.isConnected())
               {
               	try {
               	
               		ftp.logout();
           			ftp.disconnect();
                       
                     } catch(IOException ioe) {
                   	  Log(Level.INFO, "error... ftp not connected");
                     }
                }

           	}
           	else
           	{
           		mongoFlag = false;
           		Log(Level.INFO, "Cannot connect to FTP server");
           		Log(Level.INFO,"Cannot connect to FTP server " + PropertiesHelper.getFTPConnectionURI());

           	}
                   	  
           }
           
           if(pgFlag && mongoFlag)
           {
        	  startReportGeneration(resultEntryVO);
           }
           else
           {
          	 Log(Level.INFO, "Cannot start service due to unavailability of DB connection");
          	 System.exit(0);
           }
           
        } catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
        	//ServiceLogger.Log(QScheduler.class.getName(),Level.SEVERE, e);
			e.printStackTrace();
		}
        return jsonResponse;
    }
    
    private static void Log(Level level, String msg) {
        ServiceLogger.Log(MongoXmlHandler.class.getName(), level, msg);
    }
    
    public static JsonObject startReportGeneration(ResultEntryVO resultEntryVO) {
    	JsonObject jsonResponse = new JsonObject();
    	ReportGeneratorVO reportGeneratorVO = new ReportGeneratorVO();
    	Map xmlReportDoc= new HashMap();
    	 
    	 
		 
        Log(Level.INFO, "beepXML ");
        boolean xmljobProcessContinue = true;
        PGTemplateProcessing pgtp = new PGTemplateProcessing();
              
         
        xmlReportDoc = pgtp.processingData(resultEntryVO);
        reportGeneratorVO.setXmlReportDoc(xmlReportDoc);
         
        boolean pdfjobProcessContinue = true;
        Log(Level.INFO, "beepPDF ");
        PDFProcessing ppp = new PDFProcessing();
         
        jsonResponse = ppp.processingData(reportGeneratorVO);
         
         
        
        //String serviceId = reportHIstorySave.ServciceId ;
 	   	//PGDataHelper.getInstance().deleteReportHistory(serviceId);
        Log(Level.INFO, "In shutdown hook");
        MongoXmlHandler.closeConnection();
        PGDataHelper.closeConnection();
        
        return jsonResponse;
    }
}
