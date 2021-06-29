/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataHelper;

import Logging.ServiceLogger;
import java.util.logging.Level;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;






/**
 * *************************Start of program***************************** ##
 * Copyright Information	: C-DAC, Noida ## Project Name	: CCD SDK ## Name of
 * Developer	: Siddharth Srivastava ## Module Name	: Health Standards ##
 * Process/Database Object Name	: ## Purpose : ## Date of Creation	: ##
 * Modification Log	: ##	Modify Date	: ##	Reason	(CR/PRS)	: ##	Modify By	:
 */
/**
 *
 * @author Siddharth Srivastava <siddharthsrivastava@cdac.in>
 */
public class PropertiesHelper {


    static Configuration config;
    PropertiesHelper() {
        try {
           config  = new PropertiesConfiguration("investigationjobproperties.properties");
        } catch (ConfigurationException ex) {
            ServiceLogger.Log(PropertiesHelper.class.getName(), Level.SEVERE, ex);
        }

    }
    
    static void instantiateProperties()
    {
        if(config == null)
            new PropertiesHelper();
    }
    
    public static String getMongoUserName()
    {
        instantiateProperties();
        String mUserName = config.getString("MongoConnectionUsername");
        return mUserName;
    }
    
    public static String getMongoConnectionURI()
    {
        instantiateProperties();
        String mConnectionURI = config.getString("MongoConnectionURI");
        return mConnectionURI;
    }
    
    public static String getMongoPassword()
    {
        instantiateProperties();
        String mConnectionPassword = config.getString("MongoConnectionPassword");
        return mConnectionPassword;
    }
    
    public static String getPGUserName()
    {
        instantiateProperties();
        String pUserName = config.getString("PGUsername");
        return pUserName;
    }
    
    public static String getPGConnectionURI()
    {
        instantiateProperties();
        String pConnectionURI = config.getString("PGConnectionURI");
        return pConnectionURI;
    }
    
    public static String getPGPassword()
    {
        instantiateProperties();
        String pConnectionPassword = config.getString("PGPassword");
        return pConnectionPassword;
    }
    
    public static boolean getIsDebugConfiguration()
    {
        instantiateProperties();
        boolean isDebug = config.getBoolean("ISDEBUG");
        return isDebug;
    }
    
    public static String getReportLogoPath()
    {
         instantiateProperties();
         String strReportPath = config.getString("REPORTLOGOPATH");
         return strReportPath;
    }
    
    public static String getNablLogoPath()
    {
         instantiateProperties();
         String strNablLogoPath = config.getString("NABLOGOPATH");
         return strNablLogoPath;
    }
    
      public static int getNThreads()
    {
         instantiateProperties();
         int nThreads = config.getInt("NTHREADS");
         return nThreads;
    }
      
      public static String getMongoDBName()
      {
    	  instantiateProperties();
          String strMongoDBName = config.getString("MongoDBName");
          return strMongoDBName;
      }
      
      public static String getSMSUserName()
      {
    	  instantiateProperties();
          String strSMSUserName = config.getString("SMSUSERNAME");
          return strSMSUserName;
      }
    
      
      public static String getSMSPassword()
      {
    	  instantiateProperties();
          String strSMSPassword = config.getString("SMSPASSWORD");
          return strSMSPassword;
      }
      
      public static String getSMSId()
      {
    	  instantiateProperties();
          String strSMSId = config.getString("SMSID");
          return strSMSId;
      }
      
      public static String getSMSUrl()
      {
    	  instantiateProperties();
          String strSMSUrl= config.getString("SMSURL");
          return strSMSUrl;
      }
    
      public static String getFontFile()
      {
    	  instantiateProperties();
          String strSMSUrl= config.getString("FONT_FILE");
          return strSMSUrl;
    	  
      }
      
      public static String getGenericTemplate()
      {
    	  instantiateProperties();
          String genericTemplate= config.getString("GENERIC_TEMPLATE");
          return genericTemplate;
    	  
      }
      
      public static int getReportHistorySaveFrequency()
      {
           instantiateProperties();
           int Frequency = config.getInt("ReportHistorySaveFrequency");
           return Frequency;
      }
      
      public static String getMongoDBCollectionName()
      {
    	  instantiateProperties();
          String strMongoDBName = config.getString("MongoDBCollectionName");
          return strMongoDBName;
      }
    
      public static String getReportHeaderPath()
      {
           instantiateProperties();
           String strReportPath = config.getString("REPORTHEADERPATH");
           return strReportPath;
      }
      
      public static String getTemplateXMLPath()
      {
           instantiateProperties();
           String strReportPath = config.getString("TEMPLATEXMLPATH");
           return strReportPath;
      }
 
     /* public static int getDeletepdffrequency()
      {
           instantiateProperties();
           int Frequency = config.getInt("DELETEPDFSCHEDULERFREQUENCY");
           return Frequency;
      }*/
      
      public static int getDeletepdffrequencyHour()
      {
           instantiateProperties();
           int Frequency = config.getInt("SchedulerRunDeletePdfInHour");
           return Frequency;
      }
      
      public static int getDeletepdffrequencyMin()
      {
           instantiateProperties();
           int Frequency = config.getInt("SchedulerRunDeletePdfInMin");
           return Frequency;
      }

      
      public static String getFTPConnectionURI() {
  		
  		instantiateProperties();
          String strMongoDBName = config.getString("FTPConnectionURI");
          return strMongoDBName;
    }
    
    public static String getFTPConnectionUsername() {
		
		instantiateProperties();
        String strMongoDBName = config.getString("FTPConnectionUsername");
        return strMongoDBName;
  }
    
   public static String getFTPConnectionPassword() {
		
		instantiateProperties();
        String strMongoDBName = config.getString("FTPConnectionPassword");
        return strMongoDBName;
  }
    
   public static String getMongoToFtpURI() {
		
		instantiateProperties();
        String strMongoDBName = config.getString("MongoToFtpURI");
        return strMongoDBName;
  }
   
   public static String getFTPConnectionIP() {
 		
 		instantiateProperties();
         String strMongoDBName = config.getString("FTPConnectionIP");
         return strMongoDBName;
   }
   
   public static String getFTPSaveTempXMLFilePath() {
  		
  		instantiateProperties();
          String strMongoDBName = config.getString("FTPSaveTempXMLFilePath");
          return strMongoDBName;
    }
   
   public static String getFTPSaveTempPDFFilePath() {
  		
  		instantiateProperties();
          String strMongoDBName = config.getString("FTPSaveTempPDFFilePath");
          return strMongoDBName;
    }
   
   
   public static boolean getISFtporMongo()
   {
       instantiateProperties();
       boolean isDebug = config.getBoolean("ISMONGO");
       return isDebug;
   }
 
   
   public static boolean getfetchnoramltemplatetestwise()
   {
       instantiateProperties();
       boolean isDebug = config.getBoolean("FETCH_NORMAL_TEMPLATE_TESTWISE");
       return isDebug;
   }
   
   public static String getFTPDirectory() {
		
		instantiateProperties();
       String strMongoDBName = config.getString("FTPDIRECTORY");
       return strMongoDBName;
 }

   public static int getBillUpdateSaveFrequency()
   {
        instantiateProperties();
        int Frequency = config.getInt("BILLSCHEDULERFREQUENCY");
        return Frequency;
   }

   
   public static boolean getisbilldatamovescheduleronorofff()
   {
       instantiateProperties();
       boolean isDebug = config.getBoolean("ISBILLSCHEDULER");
       return isDebug;
   }

   
   public static String getFTPConnectionURIPORT() {
 		
 		instantiateProperties();
         String strMongoDBName = config.getString("FTPConnectionURIPORT");
         return strMongoDBName;
   }

   
   public static int getFTPPORT() {
		
 		instantiateProperties();
         int strMongoDBName = Integer.parseInt(config.getString("FTPPORT"));
         return strMongoDBName;
   }

   
   public static boolean is_data_insert_hprms()
   {
       instantiateProperties();
       boolean isDebug = config.getBoolean("is_data_insert_hprms");
       return isDebug;
   }

   
   
   public static String getPGUserName_kpran()
   {
       instantiateProperties();
       String pUserName = config.getString("PGUsername_KPRAN");
       return pUserName;
   }
   
   public static String getPGConnectionURI_kpran()
   {
       instantiateProperties();
       String pConnectionURI = config.getString("PGConnectionURI_KPRAN");
       return pConnectionURI;
   }
   
   public static String getPGPassword_kpran()
   {
       instantiateProperties();
       String pConnectionPassword = config.getString("PGPassword_KPRAN");
       return pConnectionPassword;
   }

   public static boolean get_IS_BILLDATA_MOVE_SAMETBL()
   {
       instantiateProperties();
       boolean isDebug = config.getBoolean("IS_DATA_MOVE_IN_SAME_DB");
       return isDebug;
   }
   
   
   public static boolean get_IS_BILLDATA_MOVE_KOPRANTBL()
   {
       instantiateProperties();
       boolean isDebug = config.getBoolean("IS_DATA_MOVE_IN_KOPRAN_DB");
       return isDebug;
   }

   
   public static boolean getReportXSL_latest()
   {
       instantiateProperties();
       boolean isDebug = config.getBoolean("ReportPrintingxsl");
       return isDebug;
   }

   
   public static boolean getisbilldatamovescheduleronorofff_kopran()
   {
       instantiateProperties();
       boolean isDebug = config.getBoolean("ISBILLSCHEDULER_KOPRAN");
       return isDebug;
   }
   
   
}
