package new_investigation.reportGenerator.mongoapp;

import java.io.UnsupportedEncodingException;

import new_investigation.reportGenerator.DataHelper.PGDataHelper;
import new_investigation.reportGenerator.DataProcessing.PDFProcessing;

public class reportHIstorySave {

	public  static boolean jobProcessContinue = true;
	public  static String ServciceId=""; 
	
	public void execute() {		
		
		System.out.println( "save_report_history ");

		PDFProcessing ppp = new PDFProcessing();
        while (jobProcessContinue) {
            
        	ServciceId=PGDataHelper.getInstance().getFetchIdForReportHistory();
        	if(ServciceId==null) { ServciceId="1"; }
            
        	try {
				PGDataHelper.getInstance().insertReportHistory(ServciceId);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
        	jobProcessContinue=false;
        }
           
        if(!jobProcessContinue) { PGDataHelper.getInstance().updateReportHistory(ServciceId);}
        	
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
		

	

}
