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
      
}