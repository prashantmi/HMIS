package new_investigation.reportGenerator.mongoapp;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Timer;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import new_investigation.reportGenerator.DataHelper.PropertiesHelper;

public class DeletePdfMongo implements Job {
     public static Timer time = new Timer(); // Instantiate Timer Object

	public  static boolean jobProcessContinue = true;
	public  static List ServciceId=new ArrayList<>(); 
	@Override
	public void execute(JobExecutionContext jec) throws JobExecutionException {
		// TODO Auto-generated method stub
		
		runTask();
		 

        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
		


	 public void runTask(){

		 int deletePdfFrequencyHour =PropertiesHelper.getDeletepdffrequencyHour();
		 int deletePdfFrequencyMin =PropertiesHelper.getDeletepdffrequencyMin();
	        Calendar calendar = Calendar.getInstance();
	        
	        calendar.set(Calendar.HOUR_OF_DAY, deletePdfFrequencyHour);
	        calendar.set(Calendar.MINUTE, deletePdfFrequencyMin);
	        calendar.set(Calendar.SECOND, 0);
	        calendar.set(Calendar.MILLISECOND, 0);




	        // Start running the task on Monday at 15:40:00, period is set to 8 hours
	        // if you want to run the task immediately, set the 2nd parameter to 0
	        
	        Calendar now = Calendar.getInstance();
			//   Log(Level.INFO, now.get(Calendar.HOUR_OF_DAY) + ":" + now.get(Calendar.MINUTE));
			   
			   int hour=now.get(Calendar.HOUR_OF_DAY);
			   int min=now.get(Calendar.MINUTE);
			    
	      
	}
	 

}
