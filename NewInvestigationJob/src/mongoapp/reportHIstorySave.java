package mongoapp;

import java.io.UnsupportedEncodingException;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import DataHelper.PGDataHelper;
import DataProcessing.PGPDFProcessing;
import DataProcessing.PGTemplateProcessing;

public class reportHIstorySave implements Job {

	public  static boolean jobProcessContinue = true;
	public  static String ServciceId=""; 
	@Override
	public void execute(JobExecutionContext jec) throws JobExecutionException {
		// TODO Auto-generated method stub
		
		
		System.out.println("save_report_history ");
     
		
		PGPDFProcessing ppp = new PGPDFProcessing();
        while (jobProcessContinue) {
            
        	ServciceId=PGDataHelper.getInstance().getFetchIdForReportHistory();
        	
        	if(ServciceId==null)
            {
            	 ServciceId="1";
          
            }
            
            	try {
					PGDataHelper.getInstance().insertReportHistory(ServciceId);
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            
            jobProcessContinue=false;
        }
           
            if(!jobProcessContinue)
            	PGDataHelper.getInstance().updateReportHistory(ServciceId);
            
        

        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
		

	

}
