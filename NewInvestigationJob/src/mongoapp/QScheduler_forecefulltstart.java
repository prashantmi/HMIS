// 
// Decompiled by Procyon v0.5.36
// 

package mongoapp;

import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;
import Logging.ServiceLogger;
import MongoHelper.MongoXmlHandler;

import java.util.logging.Level;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.ScheduleBuilder;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.TriggerBuilder;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Properties;

public class QScheduler_forecefulltstart
{
    public static void main(final String[] args) {
        final String s = null;
        final int calling_freq = 1;
        try {
            final Properties properties = new Properties();
            properties.put("org.quartz.scheduler.instanceName", "cdacjob");
            properties.put("org.quartz.threadPool.threadCount", String.valueOf(1));
            properties.put("org.quartz.scheduler.skipUpdateCheck", "true");
            properties.put("org.quartz.threadPool.class", "org.quartz.simpl.SimpleThreadPool");
            final StdSchedulerFactory sf = new StdSchedulerFactory();
            sf.initialize(properties);
            final Scheduler sched = sf.getScheduler();
            final JobDetail jobSMS = JobBuilder.newJob(QSMSJob__forecefulltstart.class).withIdentity("job1", "group1").build();
         //   final Trigger triggerSMS = TriggerBuilder.newTrigger().withIdentity("trigger1", "group1").startNow().withSchedule((ScheduleBuilder<Trigger>)SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(calling_freq).repeatForever()).build();
            Trigger triggerSMS = newTrigger()
                    .withIdentity("trigger3", "group2")
                    .startNow()
                    .withSchedule(simpleSchedule()
                    		//.withIntervalInHours(SMSFrequency)
                           .withIntervalInSeconds(calling_freq)
                            .repeatForever())
                    .build();
            
            sched.scheduleJob(jobSMS, triggerSMS);
            sched.start();
        }
        catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
    
    private static void Log(final Level level, final String msg) {
        ServiceLogger.Log(MongoXmlHandler.class.getName(), level, msg);
    }
}
