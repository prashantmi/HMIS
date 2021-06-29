package mongoapp;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.sun.tools.xjc.gen.Array;

import sun.print.resources.serviceui;
import sun.security.jca.ServiceId;
import DataHelper.PGDataHelper;
import DataHelper.PropertiesHelper;
import DataProcessing.PGPDFProcessing;
import DataProcessing.PGTemplateProcessing;
import MongoHelper.MongoXmlHandler;

public class DeletePdfMongo implements Job {
     public static Timer time = new Timer(); // Instantiate Timer Object

	public  static boolean jobProcessContinue = true;
	public  static List ServciceId=new ArrayList<>(); 
	@Override
	public void execute(JobExecutionContext jec) throws JobExecutionException {
		// TODO Auto-generated method stub
		
		runTask();
		/*System.out.println("Delete pdf files.. ");
     
		
		PGPDFProcessing ppp = new PGPDFProcessing();
     
            
        	ServciceId=PGDataHelper.getInstance().getPdfsrequisitiondtl();
       
        
        	if(ServciceId!=null && ServciceId.size()>0)
        	{
        		BasicDBObject query = new BasicDBObject();
        		BasicDBObject queryy = new BasicDBObject();
        		
        		String  data[]=new String[ServciceId.size()];
        		
        		for(int i=0;i<ServciceId.size();i++)
        		{
        	
        			System.out.println("pdf find:"+ServciceId.get(i));
        			data[i]=ServciceId.get(i).toString();
        			
        		}
    			try {
    				String m="pdf";
    				BasicDBObject query1 = new BasicDBObject();
    				query1.put("$nin",data);
    				//query.put("filename", new BasicDBObject(query1));
    				
    				
    				
    			//	BasicDBObject query2 = new BasicDBObject();
    			//	query2.put("$and","/"+m+"/");
    			//	queryy.put("filename", "/"+m+"/");
    				
    				
    				
    				
    				query.put("filename", new BasicDBObject(query1));
    				
    			//	List<BasicDBObject> obj = new ArrayList<BasicDBObject>();
    			//	obj.add(query);
    				//obj.add(queryy);
    			//	BasicDBObject andQuery = new BasicDBObject();
    			//	andQuery.put("$and", obj);
    				//query.put("filename", new BasicDBObject("$and", "/"+m+"/"));
					MongoXmlHandler.getInstance().DeletePdfs(query);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} //delete all xmls for a particular crno before inserting which contain all xmls data in one. chandann by 29 aug-2018
        		
        		//jobProcessContinue=false;
        	
        	
        	
        }*/

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
			//   System.out.println(now.get(Calendar.HOUR_OF_DAY) + ":" + now.get(Calendar.MINUTE));
			   
			   int hour=now.get(Calendar.HOUR_OF_DAY);
			   int min=now.get(Calendar.MINUTE);
			   if(deletePdfFrequencyHour>=hour && deletePdfFrequencyMin>=min)
	        time.schedule(new demo1(), calendar.getTime());
	      
	}
	 

}
