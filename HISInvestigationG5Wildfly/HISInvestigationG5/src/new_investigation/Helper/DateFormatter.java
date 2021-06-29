package new_investigation.Helper;
//import static hisglobal.tools.LoggerConfiguration.LOGGER_INV;

import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateFormatter {

	

	

	  String format(Date visited ,String outputDateFormate) {
	    SimpleDateFormat sdf = new SimpleDateFormat(outputDateFormate); 
	    FieldPosition pos = new FieldPosition(0);
	    StringBuffer empty = new StringBuffer();
	    StringBuffer date = sdf.format(visited, empty, pos);
	    return date.toString();
	  }
	 

	
	  public static String getDate(String inputDate, String inputDateFormate,String outputDateFormate)
	  {
		  String outputDate="";
		  DateFormatter df = new DateFormatter();
		  Date day ;
		  if(inputDate!=null && inputDate.equals("")==false) 
		  {
	        DateFormat formatter ; 
	        //	LOGGER_INV.log(Level.INFO,"inputDateFormate :"+inputDateFormate+" input Date="+inputDate);
	             formatter = new SimpleDateFormat(inputDateFormate);
	                 try {
						day = (Date)formatter.parse(inputDate);
						
						  outputDate= df.format(day,outputDateFormate) ;
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} 
		  }
		//  LOGGER_INV.log(Level.INFO,"outputDate :"+outputDate);
		  return outputDate;
	  }

	  public static String getDateBeforeNoofDays(String inputDate, String inputDateFormate,String outputDateFormate,int days)
	  {
		  String outputDate="";
		  DateFormatter df = new DateFormatter();
		  Date day ;
		  if(inputDate!=null && inputDate.equals("")==false) 
		  {
	        DateFormat formatter ; 
	        //	LOGGER_INV.log(Level.INFO,"inputDateFormate :"+inputDateFormate+" input Date="+inputDate);
	             formatter = new SimpleDateFormat(inputDateFormate);
	                 try {
						day = (Date)formatter.parse(inputDate);
						Calendar c=Calendar.getInstance(); 
						c.setTime(day);
						c.add(Calendar.DAY_OF_MONTH, -days);
						  outputDate= df.format(c.getTime(),outputDateFormate) ;
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} 
		  }
		//  LOGGER_INV.log(Level.INFO,"outputDate :"+outputDate);
		  return outputDate;
	  }
	  
	  public static Date getDate(String inputDate, String inputDateFormate)
	  {
		  
		  Date day=null ;
		  if(inputDate!=null && inputDate.equals("")==false) 
		  {
	        DateFormat formatter ; 
	        //	LOGGER_INV.log(Level.INFO,"inputDateFormate :"+inputDateFormate+" input Date="+inputDate);
	             formatter = new SimpleDateFormat(inputDateFormate);
	                 try {
						day = (Date)formatter.parse(inputDate);
						
						 
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} 
		  }
		 return day;
	  }
	  
	  
	  public static void main(String[] args) {
		  try {    String str_date="11/09/2009 18:27";
		  DateFormatter df = new DateFormatter();
		          DateFormat formatter ; 
		      Date date ; 
		           formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm");
		               date = (Date)formatter.parse(str_date); 
		      //        LOGGER_INV.log(Level.INFO,df.format(date, "dd-MMM-yyyy"));    
		               
		              DateFormatter.getDateBeforeNoofDays(str_date,"dd/MM/yyyy hh:mm","dd-MMM-yyyy",30);
		     } catch (ParseException e)
		     {//LOGGER_INV.log(Level.INFO,"Exception :"+e);    
		    	    
		     }
		      
		    }

	
	  
}
