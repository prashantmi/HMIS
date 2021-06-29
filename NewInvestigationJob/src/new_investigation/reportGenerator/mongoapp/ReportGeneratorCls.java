
package new_investigation.reportGenerator.mongoapp;

/**
 * @author Prashant Mishra <https://github.com/prashantmi>
 */

import new_investigation.reportGenerator.DataHelper.JakartaFTPWrapper;
import new_investigation.reportGenerator.DataHelper.PGDataHelper;
//import new_investigation.reportGenerator.DataHelper.PGDataHelper_kpran;
import new_investigation.reportGenerator.DataHelper.PropertiesHelper;
import new_investigation.reportGenerator.DataProcessing.PGPDFProcessing;
import new_investigation.reportGenerator.DataProcessing.PGTemplateProcessing;
import new_investigation.reportGenerator.Logging.ServiceLogger;
import new_investigation.reportGenerator.MongoHelper.MongoXmlHandler;
import new_investigation.reportGenerator.TemplateHelper.vo.ResultEntryVO;

import java.io.IOException;
import java.util.logging.Level;

@SuppressWarnings("unused")
public class ReportGeneratorCls {

	public static void main(String args[])  {
		ResultEntryVO resultEntryVO = new ResultEntryVO();
		resultEntryVO.setHospitalCode("96101");
		resultEntryVO.setPatCRNo("961012000000732");
		resultEntryVO.setLaboratoryCode("10001");
		resultEntryVO.setIsDeptEntry("0");
		resultEntryVO.setRequisitionNo("9610110001200128100007");
		startReportGenerator(resultEntryVO);
	}
	
	public static void startReportGenerator(ResultEntryVO resultEntryVO)  {

        try {
        	
           boolean pgFlag = true;
           if(PGDataHelper.createPostgresConnection() == null)
           {
              pgFlag = false;
              System.out.println("Cannot connect to Postgres");
           }


           boolean mongoFlag = true;       
           if(PropertiesHelper.getISFtporMongo())
           {
           	 if(MongoXmlHandler.getInstance() == null)
           	{
           		mongoFlag = false;
           	   System.out.println("Cannot connect to mongodb"); 
           	}
           } else {
                   	   
           	JakartaFTPWrapper ftp = new JakartaFTPWrapper();
           	/*String ftpUserName=InvestigationStaticConfigurator.patientfileftpusername;
           	String ftpUserPassword=InvestigationStaticConfigurator.patientfileftppassword;*/
           	
           	String ftpUserName=PropertiesHelper.getFTPConnectionUsername();
           	String ftpUserPassword=PropertiesHelper.getFTPConnectionPassword();
           	String ftpserver=PropertiesHelper.getFTPConnectionURI();
           	
           	
              
           	if (ftp.connectAndLogin(PropertiesHelper.getFTPConnectionIP(), ftpUserName, ftpUserPassword))
           	{
           		Log(Level.INFO,"FTP Connection Created: " + PropertiesHelper.getFTPConnectionURI());
           		System.out.println("FTP Connection Created: " + PropertiesHelper.getFTPConnectionURI());
               
               if(ftp.isConnected())
               {
               	try {
               	
               		ftp.logout();
           			ftp.disconnect();
                       
                     } catch(IOException ioe) {
                   	  System.out.println("error... ftp not connected");
                     }
                }

           	}
           	else
           	{
           		mongoFlag = false;
           		System.out.println("Cannot connect to FTP server");
           		Log(Level.INFO,"Cannot connect to FTP server " + PropertiesHelper.getFTPConnectionURI());

           	}
                   	  
           }
           
           if(pgFlag && mongoFlag)
           {
        	  startReportGeneration(resultEntryVO);
           }
           else
           {
          	 System.out.println("Cannot start service due to unavailability of DB connection");
          	 System.exit(0);
           }
           
        } catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
        	//ServiceLogger.Log(QScheduler.class.getName(),Level.SEVERE, e);
			e.printStackTrace();
		}
    }
    
    private static void Log(Level level, String msg) {
        ServiceLogger.Log(MongoXmlHandler.class.getName(), level, msg);
    }
    
    public static void startReportGeneration(ResultEntryVO resultEntryVO) {
    	/*----------------------------------------*/
    	/*
    	System.out.println("beepSaveReportHistory ");
        reportHIstorySave objSaveReportHistory = new reportHIstorySave();
        objSaveReportHistory.execute();
        */
		/*----------------------------------------*/
        System.out.println("beepXML ");
        boolean xmljobProcessContinue = true;
        PGTemplateProcessing pgtp = new PGTemplateProcessing();
              
        /*while (xmljobProcessContinue) {
            xmljobProcessContinue = pgtp.processingData();
        }
        */
        xmljobProcessContinue = pgtp.processingData(resultEntryVO);
        /*----------------------------------------*/
        boolean pdfjobProcessContinue = true;
        System.out.println("beepPDF ");
        PGPDFProcessing ppp = new PGPDFProcessing();
        /*
        while (pdfjobProcessContinue) {
        	pdfjobProcessContinue = ppp.processingData(resultEntryVO);
        }
        */
        pdfjobProcessContinue = ppp.processingData(resultEntryVO);
        /*----------------------------------------*/
        /*
    	System.out.println("beepFetchbilldata ");
        FetchBillData objfetchbilldata = new FetchBillData();
        if(PropertiesHelper.getisbilldatamovescheduleronorofff())
        {
        	objfetchbilldata.execute();
        }
        */
        
        //String serviceId = reportHIstorySave.ServciceId ;
 	   	//PGDataHelper.getInstance().deleteReportHistory(serviceId);
        System.out.println("In shutdown hook");
        MongoXmlHandler.closeConnection();
        PGDataHelper.closeConnection();
    }
}
