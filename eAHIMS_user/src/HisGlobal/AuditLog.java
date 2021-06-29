package HisGlobal ;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;


import usermgmt.reports.*;



public class AuditLog extends HisMethods
{
	 
	private String pK = null ;
	private String oldVal = null ;
	private String newVal = null ;
	private String remark  = null ;
	private String table = null ;
	public AuditLog(){
		
	}

   public AuditLog( String pk , String oldval, String newval, String remark , String table  )
    {
    	this.pK=pk ; this.oldVal = oldval ;  this.newVal = newval ; this.remark=remark ; 
		this.table = table ; 
		
    }

   
	
   public PreparedStatement updateAuditLog(HttpServletRequest request, java.sql.Connection conn) throws MyException
   {
   	
   	String menuId = "10" ;  //request.getParameter("menuId");
   	String moduleId = "12" ; //menuId.substring(3,5);
   	String seatId   = "1001" ;  //request.getParameter("SEATID");
   	String ipAddress = request.getRemoteAddr();
   	
   	System.out.println("ipAddress = "+ipAddress);
   	
   	String activityDate = super.getField("select to_char(sysdate,'DD-MON-YYYY') from dual");
   	
   	PreparedStatement ps = null;
   	String query = "insert into HAUT_ADMIN_ACTIVITY values(?,?,?,?,?,to_date(?,'dd-mon-yyyy'),?,?,?,?,?,?)";
   	
   	try
   	{  
   	   ps = conn.prepareStatement(query);
   	   ps.setString(1,moduleId);
   	   ps.setString(2,menuId);
   	   ps.setString(3,this.pK);
   	   ps.setString(4,this.oldVal);
   	   ps.setString(5,this.newVal);
   	   ps.setString(6,activityDate);
   	   ps.setString(7,"");
   	   ps.setString(8,seatId);
   	   ps.setString(9,seatId);
   	   ps.setString(10,ipAddress);
   	   ps.setString(11,this.remark);
   	   ps.setString(12,this.table);
   	   ps.execute();
   	}
   	catch(Exception e)
   	{
   		System.out.println("exception in Audit log transaction..."+e); 
   		try 
   		{  ps.close(); ps =null;  throw new MyException("error in audit log file trans...terminated unsuccesfully");
   		} 
   		catch( SQLException se ) { System.out.println("error in try block..."+se); }
   	}
   	return ps ;

   	
   }
  
	
public static List getAuditLogDetails(String path,String fromDate,String toDate) throws Exception
{
	int fromDateYear = 0;
	int toDateYear = 0;
	int fromDateMonth = 0;
	int toDateMonth = 0;
	int fromDateDay = 0;
	int toDateDay = 0;
	inv_Adminactivity_ActionForm formobj= null;
	String dayToken 	= 	null;
	String monthToken 	=	null;
	String yearToken 	= 	null;
	
	StringTokenizer fromDateTokenizer 	= new StringTokenizer(fromDate,"-");
	System.out.println("path"+path);
	System.out.println("fromDate"+fromDate);
	System.out.println("toDate"+toDate);
	//System.out.println("fromDateTokenizer"+fromDateTokenizer);
	
	dayToken 	= 	fromDateTokenizer.nextToken().toString();
	monthToken 	=	fromDateTokenizer.nextToken().toString();
	yearToken 	= 	fromDateTokenizer.nextToken().toString();
	System.out.println("dayToken"+dayToken);
	System.out.println("monthToken"+monthToken);
	System.out.println("yearToken"+yearToken);
	
	fromDateYear = Integer.parseInt(yearToken);
	fromDateMonth = getMonthCode(monthToken);
	fromDateDay  = Integer.parseInt(dayToken);		

	GregorianCalendar fromDateObj = new GregorianCalendar(fromDateYear,fromDateMonth,fromDateDay);
	//System.out.println("fromDateObj"+fromDateObj);
	
	
	
	
	StringTokenizer toDateTokenizer 	= new StringTokenizer(toDate,"-");
	
	dayToken 	= 	toDateTokenizer.nextToken().toString();
	monthToken 	=	toDateTokenizer.nextToken().toString();
	yearToken 	= 	toDateTokenizer.nextToken().toString();
	
	toDateYear = Integer.parseInt(yearToken);
	toDateMonth = getMonthCode(monthToken);
	toDateDay  = Integer.parseInt(dayToken);
	
	
	GregorianCalendar toDateObj = new GregorianCalendar(toDateYear,toDateMonth,toDateDay);
	//System.out.println("toDateObj"+toDateObj);
	List lst = new ArrayList();
	File f = new File(path);
	


	String[] allFiles = f.list();
	//System.out.println("allFiles"+allFiles[0]);
	
	StringTokenizer st = null;
	
	String osName = System.getProperties().getProperty("os.name");
	char sepChar = '\\';
	if(osName.startsWith("Win"))
		sepChar = '\\';
	else if(osName.startsWith("Lin"))
		sepChar = '/';
	
	int creationTimeYear 	= 0;
	int creationTimeMon 	= 0;
	int creationTimeMon1 	= 0;
	int creationTimeDay 	= 0;
	
	if(allFiles!=null)
	{
		System.out.println("Inside allfiles");
		System.out.println("filelength"+allFiles.length);

			for(int i=0;i<allFiles.length;i++) 
			{
	
				st = new StringTokenizer(allFiles[i],"_");
				String filename=allFiles[i];
				System.out.println("allFiles1"+allFiles[0]);
				
				//String year = st.nextToken().toString();
				String year=filename.substring(7,9);
				System.out.println("year"+year);
				//String mon =  st.nextToken().toString();
				String mon=filename.substring(5,7);
				int month=Integer.parseInt(mon);
				System.out.println("mon"+mon);
				String month1=getMonth(month);
				System.out.println("MONTH"+month1);
				//String date = st.nextToken().toString();
				String date=filename.substring(3,5);
				System.out.println("date"+date);
				
				creationTimeYear = Integer.parseInt("20"+year);
				//creationTimeMon	=  getMonthCode(mon);
				creationTimeMon	=  Integer.parseInt(mon);
				creationTimeDay = Integer.parseInt(date);
				System.out.println("creationTimeYear"+creationTimeYear);
				System.out.println("creationTimeMon"+creationTimeMon);
				System.out.println("creationTimeDay"+creationTimeDay);
				
				
				GregorianCalendar creationDateObj = new GregorianCalendar(creationTimeYear,creationTimeMon,creationTimeDay);
				
									
				boolean validFile 	= false;
				boolean toContinue	= false;
				
				if(creationDateObj.after(fromDateObj) && creationDateObj.before(toDateObj))
				{
					System.out.println("validFile"+validFile);
					validFile 	= true;
					System.out.println("validFile"+validFile);
				}
				if((creationDateObj.compareTo(fromDateObj)==0)|| (creationDateObj.compareTo(toDateObj)==0))
				{
					System.out.println("validFile"+validFile);
					validFile 	= true;
					System.out.println("validFile"+validFile);
				}
									
				//String time = st.nextElement().toString()+":"+st.nextElement().toString()+" "+st.nextElement().toString();
				//String fileName =  st.nextElement().toString();
				//String methodName =  st.nextElement().toString();
				//methodName = methodName.substring(0,methodName.indexOf("."));
				//String fullPath = path+sepChar+allFiles[i];
				//String fileName=allFiles[i];
				//System.out.println("fileName"+fileName);

					
				//fullPath = fullPath.replace(sepChar,'*');

				if(validFile)
				{
					formobj=new inv_Adminactivity_ActionForm();
					formobj.setFilename(allFiles[i]);
					formobj.setDtDate(date+"-"+month1+"-"+year);
					System.out.println("filename"+formobj.getFilename());
					System.out.println("Date"+formobj.getDtDate());
					//lst.add(methodName);                    //method name
					lst.add(formobj);
					//lst.add(fullPath);	
					//file Name
					
					//System.out.println("Date"+lst.get(1));
					//System.out.println("size"+lst.size());
				}
				
			}
			
	}

	

	return lst;

}

private static int getMonthCode(String month)
{
	int monthCode = 0;
	month = month.toUpperCase();
	if(month.equals("JAN"))
		monthCode = 1;
	else if(month.equals("FEB"))
		monthCode = 2;
	else if(month.equals("MAR"))
		monthCode = 3;
	else if(month.equals("APR"))
		monthCode = 4;
	else if(month.equals("MAY"))
		monthCode = 5;
	else if(month.equals("JUN"))
		monthCode = 6;
	else if(month.equals("JUL"))
		monthCode = 7;
	else if(month.equals("AUG"))
		monthCode = 8;
	else if(month.equals("SEP"))
	
		monthCode = 9;
	else if(month.equals("OCT"))
		monthCode = 10;
	else if(month.equals("NOV"))
		monthCode = 11;
	else if(month.equals("DEC"))
		monthCode = 12;
	return monthCode;
	
	
}
private static String getMonth(int month)
{
	String monthCode = "";
	//month = month.toUpperCase();
	if(month==1)
		monthCode = "JAN";
	else if(month==2)
		monthCode = "FEB";
	else if(month==3)
		monthCode = "MAR";
	else if(month==4)
		monthCode = "APR";
	else if(month==5)
		monthCode = "MAY";
	else if(month==6)
		monthCode = "JUNE";
	else if(month==7)
		monthCode = "JULY";
	else if(month==8)
		monthCode = "AUG";
	else if(month==9)
		monthCode = "SEP";
	else if(month==10)
		monthCode = "OCT";
	else if(month==11)
		monthCode = "NOV";
	else if(month==12)
		monthCode = "DEC";
	return monthCode;
	
	
}

class MyException extends Exception
{
	String detail = "" ;
	public MyException(String det ) { this.detail = det ;}
	public String toString()
    {
		return "myException is = "+detail ;
	}
}
}