package HisWeb.dao;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;

import hisglobal.transactionmgnt.HisDAO;
import hisglobal.utility.SMSHttpsNICPostClient;

import javax.sql.rowset.WebRowSet;

import org.json.simple.JSONObject;
import org.json.JSONArray;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;

public class walletWebserviceDao {
	
	private static final int OTP_MAX_LENGTH=8;
	public static String getPatWalletData(String mode,String crno) {
	    	/* mode=1 Mobileno
	    	 * mode=2 crno
	    	 * mode=3 WalletNo
	    	 */
	    	String err = "";
	    	String proc_name1 = "{call PKG_WEBSERVICES.getPatWalletData(?,?,?,?,?)}";
	        int procIndex1 = 0;
	        HisDAO dao = null;
	        WebRowSet ws = null;
	        JSONObject jsonObject3=null;
	         
	         JSONObject mainObj = new JSONObject();
	         JSONArray ja = new JSONArray();
	         String status="0";
	        try {
	            dao = new HisDAO("WebServices", "walletWebserviceDao.getPatWalletData()");
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
					//vo.setCountryWS(ws);
					//System.out.println("size====="+ws.size());
				}
	          if (ws != null && ws.size() > 0) {
	            
	               // ws.beforeFirst();
	                while (ws.next()) {
	                	JSONObject js=new JSONObject();
	                	status="1";
	                	js.put("pat_acc_no",ws.getString(1));
	    				js.put("pat_puk",ws.getString(2));
	    				js.put("acc_open_date",ws.getString(3));
	    				js.put("pat_category", ws.getString(4));
	    				//js.put("pat_adm_no", ws.getString(5));
	    				js.put("pat_adv_amt", ws.getString(6));
	    				//js.put("pat_act_amt", ws.getString(7));
	    				js.put("pat_wallet_amt", ws.getString(8));
	    				js.put("pat_acc_status", ws.getString(9));
	    				js.put("pat_mob_no", ws.getString(10));
	    				 ja.put(js);
	    				}
	                
	               
	              
	                	//jsonObject3 = Json.createObjectBuilder().add("status", status).add("pat_dealis", js.toString()).build();
	                  }
	          mainObj.put("status", status);
              mainObj.put("pat_details", ja);
	             if(ws != null){
	        		ws.close();
	        		ws = null;
	        	}          
	            return  mainObj.toString();//Json.createObjectBuilder().add("status", status).add("pat_dealis", js.toString()).build().toString();
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
	   
	  
	  public static String getPatValidation(String mode,String crno) {
	    	/* mode=5 Mobileno
	    	 * mode=6 crno
	    	 * mode=7 WalletNo
	    	 */
	    	String err = "";
	    	String proc_name1 = "{call PKG_WEBSERVICES.getPatWalletData(?,?,?,?,?)}";
	        int procIndex1 = 0;
	        HisDAO dao = null;
	        WebRowSet ws = null;
	        JsonObject jsonObject3=null;
	        JSONObject js=null;
	         JSONObject mainObj = new JSONObject();
	         JSONArray ja = new JSONArray();
	         String status="0";
	        try {
	            dao = new HisDAO("WebServices", "walletWebserviceDao.getPatWalletData()");
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
					//vo.setCountryWS(ws);
					//System.out.println("size====="+ws.size());
				}
	          if (ws != null && ws.size() > 0) {
	            
	               // ws.beforeFirst();
	                while (ws.next()) {
	                	js=new JSONObject();
	                	status="1";
	                	String Mobile_No=ws.getString(1);
	                	System.out.println("Mobile No  "+Mobile_No);
	                	String otp=walletWebserviceDao.getOTP();
	                	System.out.println("otp"+otp);
	                	String message="Your Otp for login is "+otp;
	                	SMSHttpsNICPostClient.sendTextSMSThroughNICSMSGateway("", "", "", "", Mobile_No, message);
	                	js.put("CR_NO",ws.getString(2));
	                	js.put("MOBILE_NO",Mobile_No);
	    				js.put("OTP",otp);
	    				}
	                }else
	                {
	                	js=new JSONObject();
	                	js.put("PATIENT_DETAILS","Data Not Found");
	                }
	         
	             if(ws != null){
	        		ws.close();
	        		ws = null;
	        	}          
	            return  js.toString();
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
	   	  
	  
	  
	  public static String getwalletDataSummary(String mode,String crno) {
	    	/* mode=1 Mobileno
	    	 * mode=2 crno
	    	 * mode=3 WalletNo
	    	 */
	    	String err = "";
	    	String proc_name1 = "{call PKG_WEBSERVICES.getwalletDataSummary(?,?,?,?,?)}";
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
	  
	  public static String getInvestigationTransSummary(String hosp_code , String crno) {
	    	/* mode=1 Mobileno
	    	 * mode=2 crno
	    	 * mode=3 WalletNo
	    	 */
	    	String err = "";
	    	String proc_name1 = "{call PKG_WEBSERVICES.getInvestigationTransSummary(?,?,?,?,?)}";
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
	            dao.setProcInValue(procIndex1, "modeval", "1",1);
	            dao.setProcInValue(procIndex1, "crno", crno,2);
	            dao.setProcInValue(procIndex1, "hosp_code", hosp_code,3); 
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
	          
	         
            //mainObj.put("status", status);
            mainObj.put("INVESTIGATION_DETAILS", jsonolist);
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
	  
	
	  public static String getOTP() {
			SecureRandom random = new SecureRandom();
		   return new BigInteger(130, random).abs().toString().substring(0, OTP_MAX_LENGTH);
		 }

}
