package mongoapp;

import java.io.UnsupportedEncodingException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import TemplateHelper.vo.ResultEntryVO;
import TemplateHelper.vo.ResultEntryVOGroupByValidatedBy;
import TemplateHelper.vo.UserVO;
import DataHelper.PGDataHelper;
import DataHelper.PGDataHelper_kpran;
import DataHelper.PropertiesHelper;
import DataHelper.QueryConfig;
import DataProcessing.PGPDFProcessing;
import DataProcessing.PGTemplateProcessing;
import DataProcessing.TemplateProcessingUSE;
import FileHandler.XMLFileHandler;

public class FetchMachineData implements Job {

	public  static boolean jobProcessContinue = true;
	public  static String ServciceId=""; 
	@Override
	public void execute(JobExecutionContext jec) throws JobExecutionException {
		// TODO Auto-generated method stub
		
		
		System.out.println("BEEP Machine ");
     
		
		   try
            	{
			   processingData();
            
            	}
		   catch(Exception e)
		   {
			   e.printStackTrace();
		   }

        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
		

    public boolean processingData() {
        boolean jobprocessContinue = false;
       // Log(Level.INFO, "Processing to generate Patient XML");
        String processingCRNo = null;
        String processingworkorder = null;
        Map<String, String> reportingCRNo = new HashMap<String, String>(5);

        ResultSet workorderdetailsQueryRC = null;
        Connection conn = null;
        CallableStatement cstmt = null;
        try {
        	conn = PGDataHelper.getInstance().getConnection();
            if (conn == null) {
                conn = PGDataHelper.getInstance().createPostgresConnection();  
               
            }
           
            if(conn==null || conn.isClosed())
            {
          	  
          	
            }
            else
            {
            	
            
            cstmt = conn.prepareCall("{call pkg_insert_machine_result.proc_insert_hmit_result_dtl()}");
           
        //    cstmt.registerOutParameter(1, Types.REF);
          //  cstmt.registerOutParameter(2, Types.VARCHAR);
           // cstmt.registerOutParameter(3, Types.VARCHAR);
            //cstmt.registerOutParameter(4, Types.VARCHAR);
            cstmt.execute();
          
           // processingCRNo = cstmt.getString(3);
            //processingworkorder = cstmt.getString(2);
            }

            
          
            

        } catch (Exception e) {
            e.printStackTrace();
       //     Log(Level.SEVERE, "PGTemplateProcessing: " + e.getMessage());
            try {
            	if(conn!=null && !conn.isClosed())
                conn.rollback();
            } catch (SQLException ex) {
     //           Log(Level.SEVERE, ex);
            }
            jobprocessContinue = false;
        } finally {
            try {
                if (cstmt != null) {
                    cstmt.close();
                }

                if (conn != null) {
                    conn.commit();
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
           // Log(Level.INFO, "Processing for generating XML Completed");

        }

        return jobprocessContinue;
    }



}
