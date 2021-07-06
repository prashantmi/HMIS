package HisWeb.dao;

import hisglobal.transactionmgnt.HisDAO;


import java.util.ArrayList;

import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.olap.cursor.Blob;
import javax.sql.rowset.WebRowSet;

import org.json.JSONArray;
import org.json.JSONObject;


public class PatientDtlsForPrescriptonDao {
	
	
	public static String getPatineDetails(String mode,String crno) {
    	/* mode=1 Mobileno
    	 * mode=2 crno
    	 * mode=3 WalletNo
    	 */
    	String err = "";
    	String proc_name1 = "{call PKG_WEBSERVICES.getPatDtlsForPrescription(?,?,?,?,?)}";
        int procIndex1 = 0;
        HisDAO dao = null;
        WebRowSet ws = null;
        JsonObject jsonObject3 = null,jsonObject4=null;
         
         String status="0";
         JSONObject mainObj = new JSONObject();
         
         JSONArray ja = new JSONArray();
         JsonArrayBuilder arrayBuilder =null;
         ArrayList<String> columnlist = new ArrayList<String>();
         ArrayList<JSONObject> jsonolist = new ArrayList<JSONObject>();
         int length=0;
        try {
            dao = new HisDAO("WebServices", "walletWebserviceDao.getwalletDataSummary()");
            procIndex1 = dao.setProcedure(proc_name1);
            dao.setProcInValue(procIndex1, "modeval", mode,1);
            dao.setProcInValue(procIndex1, "crno", crno,2);
            dao.setProcInValue(procIndex1, "hosp_code", "10911",3); 
            dao.setProcOutValue(procIndex1, "err", 1,4);
            dao.setProcOutValue(procIndex1, "resultset", 2,5);
            //dao.executeProcedureByPosition(procIndex1);
            
            dao.executeProcedureByPosition(procIndex1);
            err=dao.getString(procIndex1, "err");
			if(err.equals(""))
			{
				ws = dao.getWebRowSet(procIndex1, "resultset");
				 length=	ws.getMetaData().getColumnCount();
				for(int i=1;i<=length;i++)
				{
					columnlist.add(ws.getMetaData().getColumnName(i).toUpperCase());
				}
				
			}
          if (ws != null && ws.size() > 0) {
              while (ws.next()) {
            	  int j=0;
                	status="1";
                	JSONObject js=new JSONObject();
                	for (int i=1;i<=length;i++)
                	{
                		String key=columnlist.get(i-1);
                		String value=ws.getString(i);
                		js.put(key, value);
                		
                	}
                	jsonolist.add(js) ;               	
                	
                }
              //ja.put(jsonolist);
              }
          //System.out.println("jsonolist"+jsonolist);
          
         
          mainObj.put("status", status);
          mainObj.put("pat_details", jsonolist);
          //jsonObject3 =  Json.createObjectBuilder().add("status", status).add("pat_dealis", Json.createArrayBuilder().add(jsonolist.toString())).build();
     	 
        
             if(ws != null){
        		ws.close();
        		ws = null;
        	}          
            return mainObj.toString();
        }
        catch (Exception e) {
            e.printStackTrace();
            return mainObj.toString();
        }
        finally {
        	if (dao != null) {
                dao.free();
                dao = null;
            }
        }
    }
	
	
	
	
	//343058
	public static String getPatineDetailsViewPrescription(String mode,String crno) {
    	
    	String err = "";
    	String proc_name1 = "{call PKG_WEBSERVICES.getPatDtlsForPrescription(?,?,?,?,?)}";
        int procIndex1 = 0;
        HisDAO dao = null;
        WebRowSet ws = null;
        JsonObject jsonObject3 = null,jsonObject4=null;
         
         String status="0";
         JSONObject mainObj = new JSONObject();
         
         JSONArray ja = new JSONArray();
         JsonArrayBuilder arrayBuilder =null;
         ArrayList<String> columnlist = new ArrayList<String>();
         ArrayList<JSONObject> jsonolist = new ArrayList<JSONObject>();
         int length=0;
        try {
            dao = new HisDAO("WebServices", "walletWebserviceDao.getwalletDataSummary()");
            procIndex1 = dao.setProcedure(proc_name1);
            dao.setProcInValue(procIndex1, "modeval", mode,1);
            dao.setProcInValue(procIndex1, "crno", crno,2);
            dao.setProcInValue(procIndex1, "hosp_code", "10911",3);
            dao.setProcOutValue(procIndex1, "err", 1,4);
            dao.setProcOutValue(procIndex1, "resultset", 2,5);
            //dao.executeProcedureByPosition(procIndex1);
            
            dao.executeProcedureByPosition(procIndex1);
            err=dao.getString(procIndex1, "err");
			if(err.equals(""))
			{
				ws = dao.getWebRowSet(procIndex1, "resultset");
				 length=	ws.getMetaData().getColumnCount();
				for(int i=1;i<=length;i++)
				{
					columnlist.add(ws.getMetaData().getColumnName(i).toUpperCase());
				}
				
			}
          if (ws != null && ws.size() > 0) {
              while (ws.next()) {
            	  int j=0;
                	status="1";
                	JSONObject js=new JSONObject();
                	for (int i=1;i<=length;i++)
                	{
                		String key=columnlist.get(i-1);
                		String value=ws.getString(i);
                		js.put(key, value);
                		
                	}
                	jsonolist.add(js) ;               	
                	
                }
              //ja.put(jsonolist);
              }
          //System.out.println("jsonolist"+jsonolist);
          
         
          mainObj.put("status", status);
          mainObj.put("pat_details", jsonolist);
          //jsonObject3 =  Json.createObjectBuilder().add("status", status).add("pat_dealis", Json.createArrayBuilder().add(jsonolist.toString())).build();
     	 
        
             if(ws != null){
        		ws.close();
        		ws = null;
        	}          
            return mainObj.toString();
        }
        catch (Exception e) {
            e.printStackTrace();
            return mainObj.toString();
        }
        finally {
        	if (dao != null) {
                dao.free();
                dao = null;
            }
        }
    }
	
	
	public static String retriveImageData(String mode,String DeptValue) {
    	/* mode=1 Mobileno
    	 * mode=2 crno
    	 * mode=3 WalletNo
    	 */
		
		String s="";
		
		String value="";
    	String err = "";
    //	String proc_name1 = "{call PKG_WEBSERVICES.getPatDtlsForPrescription(?,?,?,?,?)}";
    	String proc_name1 = "{call PKG_WEBSERVICES.getpatprescriptionimage(?,?,?,?,?,?,?)}";
        int procIndex1 = 0;
        HisDAO dao = null;
        WebRowSet ws = null;
        JsonObject jsonObject3 = null,jsonObject4=null;
         String status="0";
         JSONObject mainObj = new JSONObject();
         
         JSONArray ja = new JSONArray();
         JsonArrayBuilder arrayBuilder =null;
         ArrayList<String> columnlist = new ArrayList<String>();
         ArrayList<JSONObject> jsonolist = new ArrayList<JSONObject>();
         int length=0;
        try {
        	String temp=DeptValue.replaceAll("\\^", "#");
        	String val[]=temp.split("#");
        	
            dao = new HisDAO("WebServices", "walletWebserviceDao.getwalletDataSummary()");
            procIndex1 = dao.setProcedure(proc_name1);
            dao.setProcInValue(procIndex1, "modeval", mode,1);
            dao.setProcInValue(procIndex1, "crno", val[1],2);
            dao.setProcInValue(procIndex1, "visit_no", val[2],3);
            
            dao.setProcInValue(procIndex1, "hosp_code", val[4],4); 
        /*changes 343058 */
            dao.setProcInValue(procIndex1, "episode_code", val[3],5); 
            dao.setProcOutValue(procIndex1, "err", 1,6);
            dao.setProcOutValue(procIndex1, "resultset", 2,7);
            System.out.println("val[0]"+val[0]);
            System.out.println("val[1]"+val[1]);
            System.out.println("val[2]"+val[2]);
            System.out.println("val[3]"+val[3]);
            System.out.println("val[4]"+val[4]);
            //dao.executeProcedureByPosition(procIndex1);
            
           dao.executeProcedureByPosition(procIndex1);
            err=dao.getString(procIndex1, "err");
			if(err.equals(""))
			{
				ws = dao.getWebRowSet(procIndex1, "resultset");
				 length=	ws.getMetaData().getColumnCount();
				for(int i=1;i<=length;i++)
				{
					columnlist.add(ws.getMetaData().getColumnName(i).toUpperCase());
				}
				
			}
          if (ws != null && ws.size() > 0) {
              while (ws.next()) {
            	  int j=0;
                	status="1";
                	JSONObject js=new JSONObject();
                	for (int i=1;i<=length;i++)
                	{
                		String key=columnlist.get(i-1);
                		if(i==2)
                		{
                			
                			//value=Base64.decode(ws.getString(i)).toString();
                			// value=ws.getString(i);
                			 
                			 
                			 //mobile app update 
                			//343058
                			 
                			
                		
              			
                			byte[] imgBytes = ws.getBytes(i);
                			String str = new String(imgBytes);
                			js.put(key, str);
                		
                			 
                			 
                			/* byte[] bytes = ws.getString(i).getBytes();
                			 String s = new String(bytes);*/
                			// System.out.println("value::::::::::\n"+value.toString());  
                		}else{
                			 value=ws.getString(i);	
                			 js.put(key, value);
                		}
                		
                		
                		//js.put(key, value);
                		
                	}
                	jsonolist.add(js) ;               	
                	
                }
              //ja.put(jsonolist);
              }
          //System.out.println("jsonolist"+jsonolist);
          
         
          mainObj.put("status", status);
          mainObj.put("ImageData", jsonolist);
          //jsonObject3 =  Json.createObjectBuilder().add("status", status).add("pat_dealis", Json.createArrayBuilder().add(jsonolist.toString())).build();
     	 System.out.println(mainObj.toString());
        
             if(ws != null){
        		ws.close();
        		ws = null;
        	}          
            return mainObj.toString();
        }
        catch (Exception e) {
            e.printStackTrace();
            return mainObj.toString();
        }
        finally {
        	if (dao != null) {
                dao.free();
                dao = null;
            }
        }
    }
	
	
	
	
	
public static String checkPatinePrescriptionDetails(String mode,String crno,String episode_code,String visit_no) {
    	
    	String err = "";
    	String proc_name1 = "{call PKG_WEBSERVICES.getpatprescriptionstatus(?,?,?,?,?,?,?)}";
        int procIndex1 = 0;
        HisDAO dao = null;
        WebRowSet ws = null;
        JsonObject jsonObject3 = null,jsonObject4=null;
         
         String status="0";
         JSONObject mainObj = new JSONObject();
         
         JSONArray ja = new JSONArray();
         JsonArrayBuilder arrayBuilder =null;
         ArrayList<String> columnlist = new ArrayList<String>();
         ArrayList<JSONObject> jsonolist = new ArrayList<JSONObject>();
         int length=0;
        try {
            dao = new HisDAO("WebServices", "walletWebserviceDao.getwalletDataSummary()");
            procIndex1 = dao.setProcedure(proc_name1);
            dao.setProcInValue(procIndex1, "modeval", mode,1);
            dao.setProcInValue(procIndex1, "crno", crno,2);
            dao.setProcInValue(procIndex1, "hosp_code", "10911",3);
            dao.setProcInValue(procIndex1, "episode_code", episode_code, 4);
            dao.setProcInValue(procIndex1, "visit_no", visit_no, 5);
            dao.setProcOutValue(procIndex1, "err", 1,6);
            dao.setProcOutValue(procIndex1, "resultset", 2,7);
            //dao.executeProcedureByPosition(procIndex1);
            
            dao.executeProcedureByPosition(procIndex1);
            err=dao.getString(procIndex1, "err");
			if(err.equals(""))
			{
				ws = dao.getWebRowSet(procIndex1, "resultset");
				 length=	ws.getMetaData().getColumnCount();
				for(int i=1;i<=length;i++)
				{
					columnlist.add(ws.getMetaData().getColumnName(i).toUpperCase());
				}
				
			}
          if (ws != null && ws.size() > 0) {
              while (ws.next()) {
            	  int j=0;
                	status="1";
                	JSONObject js=new JSONObject();
                	for (int i=1;i<=length;i++)
                	{
                		String key=columnlist.get(i-1);
                		String value=ws.getString(i);
                		js.put(key, value);
                		
                	}
                	jsonolist.add(js) ;               	
                	
                }
              //ja.put(jsonolist);
              }
          //System.out.println("jsonolist"+jsonolist);
          
         
          mainObj.put("status", status);
          mainObj.put("pat_details", jsonolist);
          //jsonObject3 =  Json.createObjectBuilder().add("status", status).add("pat_dealis", Json.createArrayBuilder().add(jsonolist.toString())).build();
     	 
        
             if(ws != null){
        		ws.close();
        		ws = null;
        	}          
            return mainObj.toString();
        }
        catch (Exception e) {
            e.printStackTrace();
            return mainObj.toString();
        }
        finally {
        	if (dao != null) {
                dao.free();
                dao = null;
            }
        }
    }



public static String getUserPatineDetails(String mode,String crno,String seatid) {
	/* mode=1 Mobileno
	 * mode=2 crno
	 * mode=3 WalletNo
	 */
	String err = "";
	String proc_name1 = "{call PKG_WEBSERVICES.getuserPatDtlsForPrescription(?,?,?,?,?,?)}";
    int procIndex1 = 0;
    HisDAO dao = null;
    WebRowSet ws = null;
    JsonObject jsonObject3 = null,jsonObject4=null;
     
     String status="0";
     JSONObject mainObj = new JSONObject();
     
     JSONArray ja = new JSONArray();
     JsonArrayBuilder arrayBuilder =null;
     ArrayList<String> columnlist = new ArrayList<String>();
     ArrayList<JSONObject> jsonolist = new ArrayList<JSONObject>();
     int length=0;
    try {
        dao = new HisDAO("WebServices", "walletWebserviceDao.getwalletDataSummary()");
        procIndex1 = dao.setProcedure(proc_name1);
        dao.setProcInValue(procIndex1, "modeval", mode,1);
        dao.setProcInValue(procIndex1, "crno", crno,2);
        dao.setProcInValue(procIndex1, "hosp_code", "10911",3); 
        dao.setProcInValue(procIndex1, "seat_id",seatid,4); 
        dao.setProcOutValue(procIndex1, "err", 1,5);
        dao.setProcOutValue(procIndex1, "resultset", 2,6);
        //dao.executeProcedureByPosition(procIndex1);
        
        dao.executeProcedureByPosition(procIndex1);
        err=dao.getString(procIndex1, "err");
		if(err.equals(""))
		{
			ws = dao.getWebRowSet(procIndex1, "resultset");
			 length=	ws.getMetaData().getColumnCount();
			for(int i=1;i<=length;i++)
			{
				columnlist.add(ws.getMetaData().getColumnName(i).toUpperCase());
			}
			
		}
      if (ws != null && ws.size() > 0) {
          while (ws.next()) {
        	  int j=0;
            	status="1";
            	JSONObject js=new JSONObject();
            	for (int i=1;i<=length;i++)
            	{
            		String key=columnlist.get(i-1);
            		String value=ws.getString(i);
            		js.put(key, value);
            		
            	}
            	jsonolist.add(js) ;               	
            	
            }
          //ja.put(jsonolist);
          }
      //System.out.println("jsonolist"+jsonolist);
      
     
      mainObj.put("status", status);
      mainObj.put("pat_details", jsonolist);
      //jsonObject3 =  Json.createObjectBuilder().add("status", status).add("pat_dealis", Json.createArrayBuilder().add(jsonolist.toString())).build();
 	 
    
         if(ws != null){
    		ws.close();
    		ws = null;
    	}          
        return mainObj.toString();
    }
    catch (Exception e) {
        e.printStackTrace();
        return mainObj.toString();
    }
    finally {
    	if (dao != null) {
            dao.free();
            dao = null;
        }
    }
}

public static String getPatientDetails(String crno) {
	
	String err = "";
	String proc_name1 = "{call PKG_WEBSERVICES.getpatientdtls(?,?,?)}";
    int procIndex1 = 0;
    HisDAO dao = null;
    WebRowSet ws = null;
    JsonObject jsonObject3 = null,jsonObject4=null;
     
     String status="0";
     JSONObject mainObj = new JSONObject();
     
     JSONArray ja = new JSONArray();
     JsonArrayBuilder arrayBuilder =null;
     ArrayList<String> columnlist = new ArrayList<String>();
     ArrayList<JSONObject> jsonolist = new ArrayList<JSONObject>();
     int length=0;
    try {
        dao = new HisDAO("WebServices", "walletWebserviceDao.getwalletDataSummary()");
        procIndex1 = dao.setProcedure(proc_name1);
        dao.setProcInValue(procIndex1, "crno", crno,1);
        dao.setProcOutValue(procIndex1, "err", 1,2);
        dao.setProcOutValue(procIndex1, "resultset", 2,3);
        //dao.executeProcedureByPosition(procIndex1);
        
        dao.executeProcedureByPosition(procIndex1);
        err=dao.getString(procIndex1, "err");
		if(err.equals(""))
		{
			ws = dao.getWebRowSet(procIndex1, "resultset");
			 length=	ws.getMetaData().getColumnCount();
			for(int i=1;i<=length;i++)
			{
				columnlist.add(ws.getMetaData().getColumnName(i).toUpperCase());
			}
			
		}
      if (ws != null && ws.size() > 0) {
          while (ws.next()) {
        	  int j=0;
            	status="1";
            	JSONObject js=new JSONObject();
            	for (int i=1;i<=length;i++)
            	{
            		String key=columnlist.get(i-1);
            		String value=ws.getString(i);
            		js.put(key, value);
            		
            	}
            	jsonolist.add(js) ;               	
            	
            }
          //ja.put(jsonolist);
          }
      //System.out.println("jsonolist"+jsonolist);
      
     
      mainObj.put("status", status);
      mainObj.put("pat_details", jsonolist);
      //jsonObject3 =  Json.createObjectBuilder().add("status", status).add("pat_dealis", Json.createArrayBuilder().add(jsonolist.toString())).build();
 	 
    
         if(ws != null){
    		ws.close();
    		ws = null;
    	}          
        return mainObj.toString();
    }
    catch (Exception e) {
        e.printStackTrace();
        return mainObj.toString();
    }
    finally {
    	if (dao != null) {
            dao.free();
            dao = null;
        }
    }
}


public static String getAdmissionAdvice(String crno) {
	
	String err = "";
	String proc_name1 = "{call PKG_WEBSERVICES.getpatientdtls(?,?,?)}";
    int procIndex1 = 0;
    HisDAO dao = null;
    WebRowSet ws = null;
    JsonObject jsonObject3 = null,jsonObject4=null;
     
     String status="0";
     JSONObject mainObj = new JSONObject();
     
     JSONArray ja = new JSONArray();
     JsonArrayBuilder arrayBuilder =null;
     ArrayList<String> columnlist = new ArrayList<String>();
     ArrayList<JSONObject> jsonolist = new ArrayList<JSONObject>();
     int length=0;
    try {
        dao = new HisDAO("WebServices", "walletWebserviceDao.getwalletDataSummary()");
        procIndex1 = dao.setProcedure(proc_name1);
        dao.setProcInValue(procIndex1, "crno", crno,1);
        dao.setProcOutValue(procIndex1, "err", 1,2);
        dao.setProcOutValue(procIndex1, "resultset", 2,3);
        //dao.executeProcedureByPosition(procIndex1);
        
        dao.executeProcedureByPosition(procIndex1);
        err=dao.getString(procIndex1, "err");
		if(err.equals(""))
		{
			ws = dao.getWebRowSet(procIndex1, "resultset");
			 length=	ws.getMetaData().getColumnCount();
			for(int i=1;i<=length;i++)
			{
				columnlist.add(ws.getMetaData().getColumnName(i).toUpperCase());
			}
			
		}
      if (ws != null && ws.size() > 0) {
          while (ws.next()) {
        	  int j=0;
            	status="1";
            	JSONObject js=new JSONObject();
            	for (int i=1;i<=length;i++)
            	{
            		String key=columnlist.get(i-1);
            		String value=ws.getString(i);
            		js.put(key, value);
            		
            	}
            	jsonolist.add(js) ;               	
            	
            }
          //ja.put(jsonolist);
          }
      //System.out.println("jsonolist"+jsonolist);
      
     
      mainObj.put("status", status);
      mainObj.put("pat_details", jsonolist);
      //jsonObject3 =  Json.createObjectBuilder().add("status", status).add("pat_dealis", Json.createArrayBuilder().add(jsonolist.toString())).build();
 	 
    
         if(ws != null){
    		ws.close();
    		ws = null;
    	}          
        return mainObj.toString();
    }
    catch (Exception e) {
        e.printStackTrace();
        return mainObj.toString();
    }
    finally {
    	if (dao != null) {
            dao.free();
            dao = null;
        }
    }
}

}
