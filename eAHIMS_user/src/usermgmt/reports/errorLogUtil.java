package usermgmt.reports;
import usermgmt.FuncLib;



import java.io.*;
import java.text.DateFormat;
import java.util.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.*;

import java.util.Calendar;
import java.util.List;
import java.util.ArrayList;

import HisGlobal.HisCombo;

import HisGlobal.HisResultSet;
import HisGlobal.HisMethods;


public class errorLogUtil extends FuncLib
{

	public List getDeptCombo() throws Exception
	{

		DocumentBuilderFactory factory 	= null;
		DocumentBuilder builder  		= null;
		File f							= null;
		Document doc					= null;

		factory   	= DocumentBuilderFactory.newInstance();
		builder   	= factory.newDocumentBuilder();


		String osName = System.getProperties().getProperty("os.name");
		
		if(osName.startsWith("Win"))
			doc 		= builder.parse("c:\\RAOL\\AHIS\\HisErrorLogDesc.xml");
		else if(osName.startsWith("Lin"))
			doc 		= builder.parse("/root/RAOL/AHIS/HisErrorLogDesc.xml");


		Element root = doc.getDocumentElement();

		NodeList children = root.getChildNodes();

		List itemList = new ArrayList();

		for (int i = 0; i < children.getLength(); i++)
		{
		   Node child = children.item(i);
		   if (child instanceof Element)
		   {

				Element childElement = (Element)child;

				NodeList newChildren = childElement.getChildNodes();

				for(int j=0;j<newChildren.getLength();j++)
				{
					Node newChild = newChildren.item(j);

					if (newChild instanceof Element)
				   	{
						Element newChildElement = (Element)newChild;
						Text textNode = (Text)newChildElement.getFirstChild();
						String text = textNode.getData().trim();
						itemList.add(text);
				   }
				}
		   }
		}

		ArrayList comboList = new ArrayList();
		//comboList.add(new HisCombo("-1","<-Select Value->"));
		for(int i=0;i<itemList.size();i+=2)
		{
				comboList.add(new HisCombo(itemList.get(i+1).toString(),itemList.get(i+0).toString()));
		}
		return comboList;


	}

	public List getErrorDetails(String path,String fromDate,String toDate) throws Exception
	{
		/*
		int fromDateYear = 0;
		int toDateYear = 0;
		int fromDateMonth = 0;
		int toDateMonth = 0;
		int fromDateDay = 0;
		int toDateDay = 0;
		
		String dayToken 	= 	null;
		String monthToken 	=	null;
		String yearToken 	= 	null;
		
		StringTokenizer fromDateTokenizer 	= new StringTokenizer(fromDate,"-");		
		
		dayToken 	= 	fromDateTokenizer.nextToken().toString();
		monthToken 	=	fromDateTokenizer.nextToken().toString();
		yearToken 	= 	fromDateTokenizer.nextToken().toString();
		
		fromDateYear = Integer.parseInt(yearToken);
		fromDateMonth = getMonthCode(monthToken);
		fromDateDay  = Integer.parseInt(dayToken);		

		GregorianCalendar fromDateObj = new GregorianCalendar(fromDateYear,fromDateMonth,fromDateDay);
		
		
		
		
		StringTokenizer toDateTokenizer 	= new StringTokenizer(toDate,"-");
		
		dayToken 	= 	toDateTokenizer.nextToken().toString();
		monthToken 	=	toDateTokenizer.nextToken().toString();
		yearToken 	= 	toDateTokenizer.nextToken().toString();
		
		toDateYear = Integer.parseInt(yearToken);
		toDateMonth = getMonthCode(monthToken);
		toDateDay  = Integer.parseInt(dayToken);
		
		
		GregorianCalendar toDateObj = new GregorianCalendar(toDateYear,toDateMonth,toDateDay);
		
		List lst = new ArrayList();
		File f = new File(path);
		
	

		String[] allFiles = f.list();
		
		StringTokenizer st = null;
		
		String osName = System.getProperties().getProperty("os.name");
		char sepChar = '\\';
		if(osName.startsWith("Win"))
			sepChar = '\\';
		else if(osName.startsWith("Lin"))
			sepChar = '/';
		
		int creationTimeYear 	= 0;
		int creationTimeMon 	= 0;
		int creationTimeDay 	= 0;
		
		if(allFiles!=null)
		{

				for(int i=0;i<allFiles.length;i++) 
				{
		
					st = new StringTokenizer(allFiles[i],"_");

					String year = st.nextElement().toString();
					String mon =st.nextElement().toString();
					String date = st.nextElement().toString();
					
					creationTimeYear = Integer.parseInt(year);
					creationTimeMon	=  getMonthCode(mon);
					creationTimeDay = Integer.parseInt(date);
					
					GregorianCalendar creationDateObj = new GregorianCalendar(creationTimeYear,creationTimeMon,creationTimeDay);
					
										
					boolean validFile 	= false;
					boolean toContinue	= false;
					
					if(creationDateObj.after(fromDateObj) && creationDateObj.before(toDateObj))
					{
						validFile 	= true;
					}
										
					String time = st.nextElement().toString()+":"+st.nextElement().toString()+" "+st.nextElement().toString();
					String fileName =  st.nextElement().toString();
					String methodName =  st.nextElement().toString();
					methodName = methodName.substring(0,methodName.indexOf("."));
					String fullPath = path+sepChar+allFiles[i];

						
					fullPath = fullPath.replace(sepChar,'*');

					if(validFile)
					{
						lst.add(fileName);                      //file name
						lst.add(methodName);                    //method name
						lst.add(year+" "+mon+" "+date+" "+time);//date time
						lst.add(fullPath);	                //file Name
					}
				}
		}

		

		return lst;
*/
		StringTokenizer st = null;
		File myFile=new File(path);						//make a new file and pass path as the parameter
		String arrList[]=myFile.list();					//calls the list method of file and stored it in the arraylist
		List lst=new ArrayList();						//make a new array list object
		Date date=null;
		
		if(arrList!=null)
		{System.out.println("array list is not null"+arrList.length);

				for(int i=0;i<arrList.length;i++) 		//checks the length 
				{
					lst.add(arrList[i]);				//add those files
					
					String fullPath = path+"/" +		//represents the fullpath and concatenate(+) it with the previously read file
							""+arrList[i];
					lst.add(fullPath);					//add the fullpath
					System.out.println(arrList[i]);
					System.out.println("fullpath is"+fullPath);
					File file =new File(fullPath);
					Date date1=new Date(file.lastModified());			//calls the date method last modified
					System.out.println("last modified date"+date1);
					Calendar cal=Calendar.getInstance();
					cal.setTime(date1);									//calls the calendar method setTime
					DateFormat df= DateFormat.getDateInstance();
					String dateStr=df.format(date1);					//format the date
					
					//String date2=date1.toGMTString();
					
					lst.add(dateStr);									//add it and a for loop is used in errorLog.jsp to display
					
					
					
					
					
					
					
					
					
					
				}
		}
				
		return lst;
	}
	
	private int getMonthCode(String month)
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




}
