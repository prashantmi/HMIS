package mongoapp;

import DataHelper.JakartaFTPWrapper;
import DataHelper.PGDataHelper;
import DataHelper.PropertiesHelper;
import Ftp.InvestigationStaticConfigurator;
import Logging.ServiceLogger;
import MongoHelper.MongoXmlHandler;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.quartz.JobBuilder.*;

import org.apache.commons.net.ftp.FTPClient;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;

import static org.quartz.SimpleScheduleBuilder.*;

import org.quartz.Trigger;

import static org.quartz.TriggerBuilder.*;

import org.quartz.impl.StdSchedulerFactory;
public class demo {

	
	    public static void main(String args[])  {

	        try {
	            
	        	
	        	
	        	
	        	
	        	
	         
	           boolean mongoFlag = true;
	           
	           if(PropertiesHelper.getISFtporMongo())
	           {
	        	 if(MongoXmlHandler.getInstance() == null)
	           {
	        		 mongoFlag = false;
	        	   System.out.println("Cannot connect to mongodb"); 
	           }
	           }
	           else
	           {
	        	   
	        	   JakartaFTPWrapper ftp = new JakartaFTPWrapper();
	   			//String ftpUserName=InvestigationStaticConfigurator.patientfileftpusername;
	   		//	String ftpUserPassword=InvestigationStaticConfigurator.patientfileftppassword;
	   			
	   			String ftpUserName=PropertiesHelper.getFTPConnectionUsername();
	   			String ftpUserPassword=PropertiesHelper.getFTPConnectionPassword();
	   			String ftpserver=PropertiesHelper.getFTPConnectionURI();
	   			
	   			
		           
	   			if (ftp.connectAndLogin(PropertiesHelper.getFTPConnectionIP(), ftpUserName, ftpUserPassword))
	   			{
	   				Log(Level.INFO,"FTP Connection Created: " + PropertiesHelper.getFTPConnectionURI());
	                System.out.println("FTP Connection Created: " + PropertiesHelper.getFTPConnectionURI());
	                
	                BufferedOutputStream bos = null ;
	           		URL urlftp=null;
	           		URLConnection urlc=null;
	           		
	           	//ftpserver/96101/2019/100000/10000/961011900005083/961011900005083.xml
	       			String hospitalCode="96101";
	       			String year="19";
	       			String insideyear="100000";
	       			String count="10000";
	       			String directory="961011900005083";

	           		try
	           		{
	           		//urlftp =new URL(InvestigationStaticConfigurator.patientretrievefileftpaddress+"/"+directory+"/"+directory+".xml");
	           			//urlftp =new URL(PropertiesHelper.getFTPConnectionURI()+"/"+hospitalCode+""+"20"+year+"/"+insideyear+"/"+directory+"/"+directory+".xml");
	           			urlftp =new URL(PropertiesHelper.getFTPConnectionURI()+"/"+hospitalCode+"/"+"20"+year+"/"+insideyear+"/"+count+"/"+directory+"/"+directory+".xml");
	           			urlc=	urlftp.openConnection();
	           		}
	           		catch(Exception ex)
	           		{
	           			ex.printStackTrace();
	           		}
	           		
	           		
	           		try
	       			{
	       			//urlftp =new URL(InvestigationStaticConfigurator.patientretrievefileftpaddress+"/"+directory+"/"+directory+".xml");
	       			//urlftp =new URL(PropertiesHelper.getFTPConnectionURI()+"/"+hospitalCode+"/"+ "20"+year+"/"+insideyear+"/"+directory+"/"+directory+".xml");
	       		   		URLConnection urlc1=null;

	       			//	urlftp =new URL(PropertiesHelper.getFTPConnectionURI()+"/"+hospitalCode+"/"+ "20"+year+"/"+insideyear+"/"+count+"/"+directory+"/"+directory+".xml");
	       			
	       			
	       			bos=new BufferedOutputStream(urlc.getOutputStream());
	       			System.out.println("BOS reconstructed XML created");
	       			}
	       			catch (Exception e) 
	       			{
	       				e.printStackTrace();
	       			}
	                
	                
	   			}
	   			else
	   			{
	   				mongoFlag = false;
	         	   System.out.println("Cannot connect to FTP server");
	  				Log(Level.INFO,"Cannot connect to FTP server " + PropertiesHelper.getFTPConnectionURI());

	   			}
	        	  
	           }
	        	
	            
	     //       sched1.start();
	            Thread.sleep(90L * 1000L);
	        } catch (Exception e) {
                     e.printStackTrace();      
	        }
//	            ServiceLogger.Log(QScheduler.class.getName(), Level.SEVERE, ex);
	        } 
	    
	    private static void Log(Level level, String msg) {
	        ServiceLogger.Log(MongoXmlHandler.class.getName(), level, msg);
	    }
	}
