/*************************************************************************************
 * SchedulerPlugIn schedule GetPatientDtlJob class to get patient Detail at a specified time 
 * as specified by the trigger
 * implements init() method of struts PlugIn
 **************************************************************************************/

package hisglobal.presentation;

import javax.servlet.ServletContext;

import org.apache.struts.action.ActionServlet;
import org.apache.struts.action.PlugIn;
import org.apache.struts.config.ModuleConfig;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.quartz.TriggerUtils;
import org.quartz.ee.servlet.QuartzInitializerServlet;
import org.quartz.impl.StdSchedulerFactory;

public class SchedulerPlugIn implements PlugIn {
	
	Long triggerTime=240L*1000L; // for how long the scheduler will wait before executing the job 
	public void init(ActionServlet actionServlet, ModuleConfig moduleConfig) {

		System.out.println("Initializing Scheduler PlugIn for Jobs!");
		// Retrieve the ServletContext
		ServletContext ctx = actionServlet.getServletContext();
		// The Quartz Scheduler
		Scheduler scheduler = null;
		//get the scheduler factory from servletContext
		StdSchedulerFactory factory =  (StdSchedulerFactory)ctx.getAttribute(QuartzInitializerServlet.QUARTZ_FACTORY_KEY);
		//StdSchedulerFactory factory =  new StdSchedulerFactory();
		System.out.println("Scheduler Factory :" + factory);
		try{
		   // Retrieve the scheduler from the factory
		   scheduler = factory.getScheduler();
		   //System.out.println("Scheduler  :" +scheduler);
		   //creating trigger for the scheduler
		   //trigger that fires as the plugin started and every 2 minutes there after
		  /* SimpleTrigger trigger=new SimpleTrigger("mytrigger",scheduler.DEFAULT_GROUP,new Date(),
				   null,SimpleTrigger.REPEAT_INDEFINITELY,triggerTime);
		   System.out.println("Trigger  :" + trigger);*/
		   
		  
		   // Triggers that Fire Every Day at 4:28 p.m.
		  /* Trigger trigger = TriggerUtils.makeDailyTrigger(16, 28);
		   trigger.setName("trigger1");
		   trigger.setGroup("group1");*/
		   
		   // Triggers that Fire after every 2 hours
		  Trigger trigger = TriggerUtils.makeHourlyTrigger(2);
		   trigger.setName("trigger1");
		   trigger.setGroup("group1");
		   
		   // Triggers that Fire after every 1 min
		  /* Trigger trigger = TriggerUtils.makeMinutelyTrigger(1);
		   trigger.setName("trigger1");
		   trigger.setGroup("group1");*/
		   
	   
		   JobDetail jobDetail =null; 
		   //System.out.println("Job Detail  :" + jobDetail);
		   // creating jobdetail object which takes job class 
		   jobDetail= new JobDetail("Get Patient Detail","Patient Detail group",GetPatientDtlJob.class);
		   //System.out.println("Job Detail  :" + jobDetail); 	
		   //System.out.println("Scheduling Job....................");
		   //scheduling the job and trigger to scheduler
		   scheduler.scheduleJob(jobDetail,trigger);
		   System.out.println("Starting scheduler..............");
		   scheduler.start();
		   System.out.println("scheduler started..............");
	
		} catch (Exception e){
			e.printStackTrace();
		}
	//sm_scheduler = scheduler;
	}

	public void destroy() {
		
	}
	
}
