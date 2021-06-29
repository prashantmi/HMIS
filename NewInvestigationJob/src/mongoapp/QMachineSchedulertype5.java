/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mongoapp;

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
import DataHelper.JakartaFTPWrapper;
import DataHelper.PGDataHelper;
import DataHelper.PGDataHelper_kpran;
import DataHelper.PropertiesHelper;
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

public class QMachineSchedulertype5 {

    public static void main(String args[])  {

        try {
            
        	
        	
        
           // PropertiesHelper.getMongoConnectionURI();
           // System.out.println(p);
            
            int numberOfThreads = PropertiesHelper.getNThreads();
            int xmlFrequency = 20;
            int pdfFrequency = 30;
            int SMSFrequency = 120;//in sec...... in hr-6
            int historySaveFrequency =PropertiesHelper.getReportHistorySaveFrequency();
            int billscheduler =PropertiesHelper.getBillUpdateSaveFrequency();

           // int deletePdfFrequency =PropertiesHelper.getDeletepdffrequency();
            Properties properties = new Properties();
            properties.put("org.quartz.scheduler.instanceName", "cdacjob");
            properties.put("org.quartz.threadPool.threadCount", String.valueOf(numberOfThreads));
            properties.put("org.quartz.scheduler.skipUpdateCheck", "true");
            properties.put("org.quartz.threadPool.class", "org.quartz.simpl.SimpleThreadPool");
            StdSchedulerFactory sf = new StdSchedulerFactory();
            sf.initialize(properties);
            Scheduler sched = sf.getScheduler();
            //Scheduler sched1 = sf.getScheduler();
            JobDetail job = newJob(QXMLJob.class)
                    .withIdentity("job1", "group1")
                    .build();
            
            int historySaveFrequencyy =PropertiesHelper.getReportHistorySaveFrequency();
            // scheduler for jobSaveReportHistory by chandan 
            JobDetail jobSaveReportHistory = newJob(reportHIstorySave.class)
                    .withIdentity("job3", "group1")
                    .build();

            JobDetail fetchbilldata = newJob(FetchBillData.class)
                    .withIdentity("job4", "group1")
                    .build();
            
// Trigger the job to run now, and then repeat every 40 seconds 
            Trigger trigger = newTrigger()
                    .withIdentity("trigger1", "group1")
                    .startNow()
                    .withSchedule(simpleSchedule()
                            .withIntervalInSeconds(xmlFrequency)
                            .repeatForever())
                    .build();

            JobDetail jobPDF = newJob(QPDFJob.class)
                    .withIdentity("job2", "group1")
                    .build();

// Trigger the job to run now, and then repeat every 40 seconds 
            Trigger triggerPDF = newTrigger()
                    .withIdentity("trigger2", "group1")
                    .startNow()
                    .withSchedule(simpleSchedule()
                            .withIntervalInSeconds(pdfFrequency)
                            .repeatForever())
                    .build();
            
            
            //trigger for jobsavereporthistory
            Trigger triggerJobSaveReportHistory = newTrigger()
                    .withIdentity("trigger3", "group1")
                    .startNow()
                    .withSchedule(simpleSchedule()
                            .withIntervalInMinutes(historySaveFrequency)
                            .repeatForever())
                    .build();

            //trigger for fetchbilldata
            Trigger triggerJobFetchbilldata = newTrigger()
                    .withIdentity("trigger4", "group1")
                    .startNow()
                    .withSchedule(simpleSchedule()
                            .withIntervalInMinutes(billscheduler)
                            .repeatForever())
                    .build();
            
            //trigger for jobsavereporthistory
            Trigger triggerJobDeleteDuplicateReportsInMongo = newTrigger()
                    .withIdentity("job4", "group2")
                    .startNow()
                    .withSchedule(simpleSchedule()
                            .withIntervalInMinutes(1)
                            .repeatForever())
                    .build();
            
            JobDetail jobDeletePdf = newJob(DeletePdfMongo.class)
                    .withIdentity("job3", "group4")
                    .build();
            
            
            // SMS JOB
            
            JobDetail jobSMS = newJob(FetchMachineData.class)
                    .withIdentity("job3", "group2")
                    .build();
            
            
           //SMS Trigger 
            Trigger triggerSMS = newTrigger()
                    .withIdentity("trigger3", "group2")
                    .startNow()
                    .withSchedule(simpleSchedule()
                    		//.withIntervalInHours(SMSFrequency)
                           .withIntervalInSeconds(SMSFrequency)
                            .repeatForever())
                    .build();
            
            
            System.out.println("Scheduling Machine Job");
// Tell quartz to schedule the job using our trigger
      
             sched.scheduleJob(jobSMS,triggerSMS);
            
         // for kopran needs to be comment
          //  sched.scheduleJob(jobSaveReportHistory, triggerJobSaveReportHistory);
          //  sched.scheduleJob(job, trigger);
          // sched.scheduleJob(jobPDF, triggerPDF);
      
           if(PropertiesHelper.getisbilldatamovescheduleronorofff())
           {
          // sched.scheduleJob(fetchbilldata,triggerJobFetchbilldata);
           }
            
            
            
            // for kopran needs to be uncomment and jar name should be Investigation_kopran_Job.jar
            /*if(PropertiesHelper.getisbilldatamovescheduleronorofff_kopran())
            {
            sched.scheduleJob(fetchbilldata,triggerJobFetchbilldata);
            }*/
           
           
            
         //  sched.scheduleJob(jobDeletePdf, triggerJobDeleteDuplicateReportsInMongo);
           
           //for sms
           
           //***********************************************************************************************
                            
           
           
           //shutdown hook to close connection on service stop
           Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
               public void run() {
            	String serviceId=   reportHIstorySave.ServciceId ;
            	PGDataHelper.getInstance().deleteReportHistory(serviceId);
                   System.out.println("In shutdown hook");
                   MongoXmlHandler.closeConnection();
                   PGDataHelper.closeConnection();
               }
           }, "Shutdown-thread"));
           
           boolean pgFlag = true;
           if(PGDataHelper.createPostgresConnection() == null)
           {
        	   pgFlag = false;
        	   System.out.println("Cannot connect to Postgres");
           }
           
           

        	 if(pgFlag )
        	 {
        		 sched.start();
        	 }
        	 else
        	 {
        		 System.out.println("Cannot start service due to unavailability of DB connection");
        		 System.exit(0);
        	 }
           
            
     //       sched1.start();
            Thread.sleep(90L * 1000L);
        } catch (SchedulerException ex) {
            ServiceLogger.Log(QScheduler.class.getName(),Level.SEVERE, ex);
        } catch (InterruptedException ex) {
//            ServiceLogger.Log(QScheduler.class.getName(), Level.SEVERE, ex);
        }
    }
    
    private static void Log(Level level, String msg) {
        ServiceLogger.Log(MongoXmlHandler.class.getName(), level, msg);
    }
}
