package auditlogClient.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.Writer;
import java.lang.reflect.Method;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletResponse;



public class Usefulmethods {
	
	

	public static  void writeInResponse(HttpServletResponse response , String strResult,boolean isJson) {
	try{
		response.flushBuffer();
		if(isJson)
			response.setContentType("application/json");
		response.setHeader("Cache-Control", "no-cache");		 
		PrintWriter writer=response.getWriter();
		writer.write(strResult);
		
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}	
	   public static String clobStringConversion(Clob clb) throws Exception 
	   {
			 StringBuffer str = new StringBuffer(5000);
		     String strng=null;
		     String resultStr=null;
		     BufferedReader bufferRead=null;
			 
		 try { 
		   if (clb == null){
			   System.out.println("clb is null"); 
	  	   return  null;
		   }
	     bufferRead = new BufferedReader(clb.getCharacterStream());
	  
	     while ((strng=bufferRead.readLine())!=null)
	      str.append(strng);
	     
	     resultStr=str.toString();
	     
		   }
		 catch (Exception e) {
			 resultStr=null;
		 	 e.printStackTrace();
		 }	
		 finally
		 {
			 if(bufferRead!=null){
				 try {
					bufferRead.close();
					bufferRead=null;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 
			 }	
			 if(clb!=null){
				 clb=null;
			 }
		 }
	     return resultStr;
	   }
	   
		

		public static String  populateQuery(String query, List<String> condition)throws SQLException
		{
			if(condition!=null)
			{	
		 	for(String entryObj:condition)
		 	{	 		
		 		query=query.replaceFirst("\\?",entryObj);	
		 	}
			}
			System.out.println("query ="+query);
		 	return query;
		}

		 
		
		
		 
		 public static String getQuery(String _filename,String _queryKey)throws Exception {
			 ResourceBundle rb= loadPropertiesFile(_filename);		
			 System.out.println("_queryKey"+_queryKey);
			 String query= rb.getString(_queryKey);
			 System.out.println("query in getQuery(String _filename,String _queryKey):: "+query);
			 return query;
		 }
		 public static ResourceBundle loadPropertiesFile(String _fileName)throws Exception{
			 System.out.println("fileName::::::::::::::::::::::::::::");   
			 String BUNDLE_NAME = _fileName;       
			     System.out.println("fileName"+_fileName);
		        ResourceBundle rb =  ResourceBundle.getBundle(BUNDLE_NAME);	        
		        try {	        	
		       
		        } catch (MissingResourceException e) {
		        }
		    
			 
				return rb;			
		 }	
		 public static ResultSet executeQuery(Connection _conn,String _query)throws Exception
		 {	
			 ResultSet rs=null;
			 Statement st=null;		 
			 st= _conn.createStatement();
			 rs=st.executeQuery(_query);		 
			 return rs;	 
		 } 
		
		 public  static void populate(Object obj1, Object obj2){//data from object2 is populated into obj1
				//getter of object2 is called 
				//setter of object1 is called
					
					Class cls2 = obj2.getClass();
					
					Method[] cls2Methods = cls2.getMethods();
						
					HashMap mpGettersInCls2 = new HashMap();
					for(int i=0; i<cls2Methods.length; i++){
						if(cls2Methods[i].getName().indexOf("get")==0){//if the method name starts with set
							mpGettersInCls2.put(cls2Methods[i].getName().substring(3), new Integer(i));
						}
					}	
					
					Class cls1 = obj1.getClass();
					Method[] cls1Methods = cls1.getMethods();
					
					try{
						for(int i=0; i<cls1Methods.length; i++){
						//System.out.println("cls1Methods[i].getName().indexOf(set):  "+cls1Methods[i].getName().indexOf("set"));
						if(cls1Methods[i].getName().indexOf("set")==0){//if the method name starts with set
							if(mpGettersInCls2.containsKey(cls1Methods[i].getName().substring(3))){
								//System.out.println("pos2 of "+i);
								int idx = ((Integer)mpGettersInCls2.get(cls1Methods[i].getName().substring(3))).intValue();
								//System.out.println("pos3 of "+i+" cls1Methods[i].getName():  "+cls1Methods[i].getName()+"  cls2Methods[idx].getName():  "+cls2Methods[idx].getName());
								Object str = cls2Methods[idx].invoke(obj2, null);
								//System.out.println("pos4 of "+i +"  str: "+ str);
								cls1Methods[i].invoke(obj1, new Object[]{str});
							}
						}
					}	
					}catch(Exception e){
						e.printStackTrace();						
					}
					//System.out.println("after populate ");
				}
		 
		
			
			 /*function for getting image file  as byte array */
			 public  byte[] getBytesFromFile(String filePath)  {
			 	byte[] bytes=null;
			 	try
			 	{
			 	File file = new File(filePath); 
			     InputStream is = new FileInputStream(file);

			     // Get the size of the file
			     long length = file.length();

			     if (length > Long.MAX_VALUE) {
			         // File is too large
			     }

			     // Create the byte array to hold the data
			     bytes = new byte[(int)length];

			     // Read in the bytes
			     int offset = 0;
			     int numRead = 0;
			     while (offset < bytes.length
			            && (numRead=is.read(bytes, offset, bytes.length-offset)) >= 0) {
			         offset += numRead;
			     }

			     // Ensure all the bytes have been read in
			     if (offset < bytes.length) {
			         throw new IOException("Could not completely read file "+file.getName());
			     }

			     // Close the input stream and return bytes
			     is.close();
			 	}		
			 	catch(FileNotFoundException ex)
			 	{
			 		System.out.println("FileNotFoundException : " + ex);		
			 	}
			 	catch(IOException ioe)
			 	{
			 		System.out.println("IOException : " + ioe);		
			 	}
			     return bytes;
			 }
			 
			 public static Map sortMapByComparator(Map unsortMap) {
				 Map sortedMap = new LinkedHashMap();
				 if(unsortMap!=null && unsortMap.size()>0){
					List list = new LinkedList(unsortMap.entrySet());
			 		// sort list based on comparator
					Collections.sort(list, new Comparator() {
						public int compare(Object o1, Object o2) {
							return ((Comparable) ((Map.Entry) (o1)).getValue())
			                                       .compareTo(((Map.Entry) (o2)).getValue());
						}
					});
			 		// put sorted list into map again
			        //LinkedHashMap make sure order in which keys were inserted
					
					for (Iterator it = list.iterator(); it.hasNext();) {
						Map.Entry entry = (Map.Entry) it.next();
						sortedMap.put(entry.getKey(), entry.getValue());
						
					}
				 }
					//printMap(sortedMap);
					return sortedMap;
				}
				
}
