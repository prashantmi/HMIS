package MongoHelper;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.bson.Document;
import org.bson.types.ObjectId;

import DataHelper.PGDataHelper;
import DataHelper.PropertiesHelper;
import Logging.ServiceLogger;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import java.util.logging.Level;

public class MongoDataAnalyzer {

	@SuppressWarnings("unused")
	public static void main(String[] args) throws ParseException {
		
		List<String> filesToBeDeleted = new ArrayList<String>();
		
		Log(Level.INFO, "Files for CR NOs. for which requisition has been raised within below specified dates will be copied.");
		
        Log(Level.INFO, "Enter From Date (in dd/mm/yyyy format) : ");
        @SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
        String fromDate = scanner.nextLine();
        //System.out.println("fromDate : " + fromDate);
        
        Log(Level.INFO, "Enter To Date  (in dd/mm/yyyy format) : ");
        scanner = new Scanner(System.in);
        String toDate = scanner.nextLine();
        //System.out.println("toDate : " + toDate);
        
        Log(Level.INFO, "Start copying reports for CR NOs. between "+fromDate+" and "+toDate+" ....");
        
        BufferedWriter out = null;
		FileWriter fstream = null;
		
		try {
			fstream = new FileWriter("output.txt", true); //true tells to append data.
			out = new BufferedWriter(fstream);
			out.write("\n\nStart copying reports for CR NOs. between "+fromDate+" and "+toDate+" ....\n");
		} catch (IOException e1) {
			e1.printStackTrace();
		}finally {
			if(out != null) {
		        try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		    }
			if(fstream != null) {
		        try {
		        	fstream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		    }
		}
		
		
		
        
       List<String> crNos = PGDataHelper.getCrNos(fromDate,toDate);
		//List<String> crNos=new ArrayList<String>();
		//crNos.add("961012000000651");

        
		try {
			filesToBeDeleted = MongoToFtpFileTransfer.getInstance().getFiledetails(crNos);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}
	
	private static void Log(Level level, String msg) {
        ServiceLogger.Log(MongoDataAnalyzer.class.getName(), level, msg);
    }

    private static void Log(Level level, Exception e) {
        ServiceLogger.Log(MongoDataAnalyzer.class.getName(), level, e);
    }
	
	
}
