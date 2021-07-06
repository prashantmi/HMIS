package HisWeb.dao;

import hisglobal.backutil.HisException;
import hisglobal.transactionmgnt.HisDAO;
import hisglobal.utility.HisUtil;
import new_opd.vo.DoctorDeskVO;

import java.util.ArrayList;

import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.sql.rowset.WebRowSet;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;

public class EHRDetailsDAO {
	
	public static String getPatinetEHRDtls(String mode,String crno, String episodeCode, String visitNo, String seatId, String entrydate, String hosp_code) {
    	/* Get EHR Details */
    	String err = "";
    	String proc_name1 = "{call pkg_opddrdesk_view.get_pat_ehr_Details(?,?,?,?,? ,?,?,?,?)}";
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
        	//System.out.println("crno::::::::::::::"+crno);
            dao = new HisDAO("WebServices", "EHRDetailsDAO.getPatinetEHRDtls()");
            procIndex1 = dao.setProcedure(proc_name1);
            dao.setProcInValue(procIndex1, "modeval", mode,1);
            dao.setProcInValue(procIndex1, "crno", crno,2);
            dao.setProcInValue(procIndex1, "episodeCode", episodeCode,3);
            dao.setProcInValue(procIndex1, "visitNo", visitNo,4);
            dao.setProcInValue(procIndex1, "seatId", seatId,5);
            dao.setProcInValue(procIndex1, "entrydate", entrydate,6);
            dao.setProcInValue(procIndex1, "hosp_code", hosp_code,7);
            dao.setProcOutValue(procIndex1, "err", 1,8);
            dao.setProcOutValue(procIndex1, "resultset", 2,9);
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
                		if(key.equalsIgnoreCase("hrstr_json_data")) {
                		JSONParser parser = new JSONParser();
                		org.json.simple.JSONObject json = (org.json.simple.JSONObject) parser.parse(value);
                		js.put(key, json);
                		}else {
                			js.put(key, value);
                		}
                		
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
        	HisException eObj = new HisException("OPD Lite","getPatinetEHRDtls.getPatinetEHRDtls()-->", e.getMessage() + "-->" + e);
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

	public static String getVitalDtlsDtls(String mode,String crno, String episodeCode, String visitNo, String seatId, String entrydate, String hosp_code) {
    	/* Get EHR Details */
    	String err = "";
    	String proc_name1 = "{call pkg_opddrdesk_view.get_vital_dtls(?,?,?,?,? ,?,?,?,?)}";
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
        	//System.out.println("crno::::::::::::::"+crno);
            dao = new HisDAO("WebServices", "EHRDetailsDAO.getPatinetEHRDtls()");
            procIndex1 = dao.setProcedure(proc_name1);
            dao.setProcInValue(procIndex1, "modeval", mode,1);
            dao.setProcInValue(procIndex1, "crno", crno,2);
            dao.setProcInValue(procIndex1, "episodeCode", episodeCode,3);
            dao.setProcInValue(procIndex1, "visitNo", visitNo,4);
            dao.setProcInValue(procIndex1, "seatId", seatId,5);
            dao.setProcInValue(procIndex1, "entrydate", entrydate,6);
            dao.setProcInValue(procIndex1, "hosp_code", hosp_code,7);
            dao.setProcOutValue(procIndex1, "err", 1,8);
            dao.setProcOutValue(procIndex1, "resultset", 2,9);
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
                		JSONParser parser = new JSONParser();
                		org.json.simple.JSONObject json = (org.json.simple.JSONObject) parser.parse(value);
                		js.put(key, json);
                		
                	}
                	jsonolist.add(js) ;               	
                	
                }
              //ja.put(jsonolist);
              }
          //System.out.println("jsonolist"+jsonolist);
          
         
          mainObj.put("status", status);
          mainObj.put("VitalDtls", jsonolist);
          //jsonObject3 =  Json.createObjectBuilder().add("status", status).add("pat_dealis", Json.createArrayBuilder().add(jsonolist.toString())).build();
     	 
        
             if(ws != null){
        		ws.close();
        		ws = null;
        	}          
            return mainObj.toString();
        }
        catch (Exception e) {
        	HisException eObj = new HisException("OPD Lite","getPatinetEHRDtls.getVitalDtlsDtls()-->", e.getMessage() + "-->" + e);
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
	public static String getPatientEHRDtlsForPastPrescription(String mode,String crno, String episodeCode, String visitNo, String seatId, String entrydate, String hosp_code) {
    	/* Get EHR Details */
    	String err = "";
    	String proc_name1 = "{call pkg_opddrdesk_view.get_pat_ehr_Details(?,?,?,?,? ,?,?,?,?)}";
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
        	//System.out.println("crno::::::::::::::"+crno);
            dao = new HisDAO("WebServices", "EHRDetailsDAO.getPatinetEHRDtls()");
            procIndex1 = dao.setProcedure(proc_name1);
            dao.setProcInValue(procIndex1, "modeval", mode,1);
            dao.setProcInValue(procIndex1, "crno", crno,2);
            dao.setProcInValue(procIndex1, "episodeCode", episodeCode,3);
            dao.setProcInValue(procIndex1, "visitNo", visitNo,4);
            dao.setProcInValue(procIndex1, "seatId", seatId,5);
            dao.setProcInValue(procIndex1, "entrydate", entrydate,6);
            dao.setProcInValue(procIndex1, "hosp_code", hosp_code,7);
            dao.setProcOutValue(procIndex1, "err", 1,8);
            dao.setProcOutValue(procIndex1, "resultset", 2,9);
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
                		
                		if(key.equalsIgnoreCase("hrstr_json_data")){
                			JSONParser parser = new JSONParser();
                    		org.json.simple.JSONObject json = (org.json.simple.JSONObject) parser.parse(value);
                    		js.put(key, json);
                		}
                		else{
                			js.put(key, value);
                		}
                		j++;
                	}

                	jsonolist.add(js) ;
                	
                }
              //ja.put(jsonolist);
              }
          //System.out.println("jsonolist"+jsonolist);
          
         
          //mainObj.put("status", status);
          mainObj.put("pat_details", jsonolist);
          JSONParser parser = new JSONParser();
  		//org.json.simple.JSONObject json1 = (org.json.simple.JSONObject) parser.parse(getPatientEHRDtlsForTemplatePrescription(mode, crno, episodeCode, visitNo, seatId, entrydate, hosp_code));
          mainObj.put("Template",getPatientEHRDtlsForTemplatePrescription(mode, crno, episodeCode, visitNo, seatId, entrydate, hosp_code) );
          
          mainObj.put("PaptientDoc",getPatientEHRDtlsForTemplatePrescription1(mode, crno, episodeCode, visitNo, seatId, entrydate, hosp_code) );
         // mainObj.
          ja.put(mainObj);
          //jsonObject3 =  Json.createObjectBuilder().add("status", status).add("pat_dealis", Json.createArrayBuilder().add(jsonolist.toString())).build();
     	 
        
             if(ws != null){
        		ws.close();
        		ws = null;
        	}          
             return mainObj.toString();
        }
        catch (Exception e) {
        	HisException eObj = new HisException("OPD Lite","getPatinetEHRDtls.getPatientEHRDtlsForPastPrescription()-->", e.getMessage() + "-->" + e);
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
	
	public static ArrayList<JSONObject> getPatientEHRDtlsForTemplatePrescription(String mode,String crno, String episodeCode, String visitNo, String seatId, String entrydate, String hosp_code) {
    	/* Get EHR Details */
    	String err = "";
    	String proc_name1 = "{call pkg_opddrdesk_view.get_pat_ehr_Details(?,?,?,?,? ,?,?,?,?)}";
        int procIndex1 = 0;
        HisDAO dao = null;
        WebRowSet ws = null;
        JSONObject mainObj = new JSONObject();
         
         JSONArray ja = new JSONArray();
         ArrayList<String> columnlist = new ArrayList<String>();
         ArrayList<JSONObject> jsonolist = new ArrayList<JSONObject>();
         int length=0;
        try {
        	//System.out.println("crno::::::::::::::"+crno);
            dao = new HisDAO("WebServices", "EHRDetailsDAO.getPatinetEHRDtls()");
            procIndex1 = dao.setProcedure(proc_name1);
            dao.setProcInValue(procIndex1, "modeval", "6",1);
            dao.setProcInValue(procIndex1, "crno", crno,2);
            dao.setProcInValue(procIndex1, "episodeCode", episodeCode,3);
            dao.setProcInValue(procIndex1, "visitNo", visitNo,4);
            dao.setProcInValue(procIndex1, "seatId", seatId,5);
            dao.setProcInValue(procIndex1, "entrydate", entrydate,6);
            dao.setProcInValue(procIndex1, "hosp_code", hosp_code,7);
            dao.setProcOutValue(procIndex1, "err", 1,8);
            dao.setProcOutValue(procIndex1, "resultset", 2,9);
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
                	//status="1";
                	JSONObject js=new JSONObject();
                	for (int i=1;i<=length;i++)
                	{
                		String key=columnlist.get(i-1);
                		String value=ws.getString(i);
                		
                		js.put(key, value);
                		
                		j++;
                	}

                	jsonolist.add(js) ;
                	
                }
            
              }
         
          mainObj.put("pat_details", jsonolist);
          
          ja.put(mainObj);
           if(ws != null){
        		ws.close();
        		ws = null;
        	}          
             return jsonolist ;
        }
        catch (Exception e) {
        	HisException eObj = new HisException("OPD Lite","getPatinetEHRDtls.getPatientEHRDtlsForPastPrescription()-->", e.getMessage() + "-->" + e);
            e.printStackTrace();
            return jsonolist;
        }
        finally {
        	if (dao != null) {
                dao.free();
                dao = null;
            }
        }
    }
	
	
	public static ArrayList<JSONObject> getPatientEHRDtlsForTemplatePrescription1(String mode,String crno, String episodeCode, String visitNo, String seatId, String entrydate, String hosp_code) {
    	/* Get EHR Details */
    	String err = "";
    	String proc_name1 = "{call pkg_opddrdesk_view.get_pat_ehr_Details(?,?,?,?,? ,?,?,?,?)}";
        int procIndex1 = 0;
        HisDAO dao = null;
        WebRowSet ws = null;
        JSONObject mainObj = new JSONObject();
         
         JSONArray ja = new JSONArray();
         ArrayList<String> columnlist = new ArrayList<String>();
         ArrayList<JSONObject> jsonolist = new ArrayList<JSONObject>();
         int length=0;
        try {
        	//System.out.println("crno::::::::::::::"+crno);
            dao = new HisDAO("WebServices", "EHRDetailsDAO.getPatinetEHRDtls()");
            procIndex1 = dao.setProcedure(proc_name1);
            dao.setProcInValue(procIndex1, "modeval", "8",1);
            dao.setProcInValue(procIndex1, "crno", crno,2);
            dao.setProcInValue(procIndex1, "episodeCode", episodeCode,3);
            dao.setProcInValue(procIndex1, "visitNo", visitNo,4);
            dao.setProcInValue(procIndex1, "seatId", seatId,5);
            dao.setProcInValue(procIndex1, "entrydate", entrydate,6);
            dao.setProcInValue(procIndex1, "hosp_code", hosp_code,7);
            dao.setProcOutValue(procIndex1, "err", 1,8);
            dao.setProcOutValue(procIndex1, "resultset", 2,9);
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
                	//status="1";
                	JSONObject js=new JSONObject();
                	for (int i=1;i<=length;i++)
                	{
                		String key=columnlist.get(i-1);
                		String value=ws.getString(i);
                		
                		js.put(key, value);
                		
                		j++;
                	}

                	jsonolist.add(js) ;
                	
                }
            
              }
         
          mainObj.put("pat_details", jsonolist);
          
          ja.put(mainObj);
           if(ws != null){
        		ws.close();
        		ws = null;
        	}          
             return jsonolist ;
        }
        catch (Exception e) {
        	HisException eObj = new HisException("OPD Lite","getPatinetEHRDtls.getPatientEHRDtlsForPastPrescription()-->", e.getMessage() + "-->" + e);
            e.printStackTrace();
            return jsonolist;
        }
        finally {
        	if (dao != null) {
                dao.free();
                dao = null;
            }
        }
    }

	
	public static ArrayList<JSONObject> getAdmissionAdvice(String mode,String crno, String episodeCode, String visitNo, String seatId, String entrydate, String hosp_code) {
    	/* Get EHR Details */
    	String err = "";
    	String proc_name1 = "{call pkg_opddrdesk_view.get_pat_ehr_Details(?,?,?,?,? ,?,?,?,?)}";
        int procIndex1 = 0;
        HisDAO dao = null;
        WebRowSet ws = null;
        JSONObject mainObj = new JSONObject();
         
         JSONArray ja = new JSONArray();
         ArrayList<String> columnlist = new ArrayList<String>();
         ArrayList<JSONObject> jsonolist = new ArrayList<JSONObject>();
         int length=0;
        try {
        	//System.out.println("crno::::::::::::::"+crno);
            dao = new HisDAO("WebServices", "EHRDetailsDAO.getPatinetEHRDtls()");
            procIndex1 = dao.setProcedure(proc_name1);
            dao.setProcInValue(procIndex1, "modeval", "7",1);
            dao.setProcInValue(procIndex1, "crno", crno,2);
            dao.setProcInValue(procIndex1, "episodeCode", episodeCode,3);
            dao.setProcInValue(procIndex1, "visitNo", visitNo,4);
            dao.setProcInValue(procIndex1, "seatId", seatId,5);
            dao.setProcInValue(procIndex1, "entrydate", entrydate,6);
            dao.setProcInValue(procIndex1, "hosp_code", hosp_code,7);
            dao.setProcOutValue(procIndex1, "err", 1,8);
            dao.setProcOutValue(procIndex1, "resultset", 2,9);
            //dao.executeProcedureByPosition(procIndex1);
            
            dao.executeProcedureByPosition(procIndex1);
            err=dao.getString(procIndex1, "err");
			if(err.equals(""))
			{
				ws = dao.getWebRowSet(procIndex1, "resultset");
				 length=	ws.getMetaData().getColumnCount();
				 //System.out.println("lengthb:::::::::"+length);
				for(int i=1;i<=length;i++)
				{
					columnlist.add(ws.getMetaData().getColumnName(i).toUpperCase());
				}
				
			}
          if (ws != null && ws.size() > 0) {
        	  
              while (ws.next()) {
            	    int j=0;
                	//status="1";
                	JSONObject js=new JSONObject();
                	for (int i=1;i<=length;i++)
                	{
                		String key=columnlist.get(i-1);
                		String value=ws.getString(i);
                		
                		js.put(key, value);
                		
                		j++;
                	}
                	//System.out.println(js.toString());	
                	jsonolist.add(js) ;
                	
                }
            
              }
         
          if(ws != null){
        		ws.close();
        		ws = null;
        	}          
          //System.out.println(jsonolist);
             return jsonolist ;
        }
        catch (Exception e) {
        	HisException eObj = new HisException("OPD Lite","getPatinetEHRDtls.getPatientEHRDtlsForPastPrescription()-->", e.getMessage() + "-->" + e);
            e.printStackTrace();
            return jsonolist;
        }
        finally {
        	if (dao != null) {
                dao.free();
                dao = null;
            }
        }
    }

	public static String getPatVitalDataDtlsForDetailedPrescription(String mode,String crno, String episodeCode, String visitNo, String seatId, String entrydate, String hosp_code) {
    	/* Get EHR Details */
    	String err = "";
    	String proc_name1 = "{call pkg_opddrdesk_view.get_pat_ehr_Details(?,?,?,?,? ,?,?,?,?)}";
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
        	//System.out.println("crno::::::::::::::"+crno);
            dao = new HisDAO("WebServices", "EHRDetailsDAO.getPatinetEHRDtls()");
            procIndex1 = dao.setProcedure(proc_name1);
            dao.setProcInValue(procIndex1, "modeval", mode,1);
            dao.setProcInValue(procIndex1, "crno", crno,2);
            dao.setProcInValue(procIndex1, "episodeCode", episodeCode,3);
            dao.setProcInValue(procIndex1, "visitNo", visitNo,4);
            dao.setProcInValue(procIndex1, "seatId", seatId,5);
            dao.setProcInValue(procIndex1, "entrydate", entrydate,6);
            dao.setProcInValue(procIndex1, "hosp_code", hosp_code,7);
            dao.setProcOutValue(procIndex1, "err", 1,8);
            dao.setProcOutValue(procIndex1, "resultset", 2,9);
            
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
                		
                		if(j%2 != 0){
                			JSONParser parser = new JSONParser();
                    		org.json.simple.JSONObject json = (org.json.simple.JSONObject) parser.parse(value);
                    		js.put(key, json);
                		}
                		else{
                			js.put(key, value);
                		}
                		j++;
                	}

                	jsonolist.add(js) ;
                	
                }
              
              }

          mainObj.put("pat_vital_details", jsonolist);
          
          ja.put(mainObj);
        
             if(ws != null){
        		ws.close();
        		ws = null;
        	}          
             return mainObj.toString();
        }
        catch (Exception e) {
        	HisException eObj = new HisException("OPD Lite","getPatinetEHRDtls.getPatVitalDataDtlsForDetailedPrescription()-->", e.getMessage() + "-->" + e);
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
	
	
	public static String getHospitalHeaderName(String mode,String crno, String episodeCode, String visitNo, String seatId, String entrydate, String hosp_code) {
		String strproc_name = "{call pkg_opdDrDesk_view.proc_dept_dtl(?,?,?,?,?,?,?)}";
		HisDAO dao = null;
		HisUtil util = null;

		int nProcIndex = 0 ,length=0;

		String strErr = "";
		WebRowSet ws = null;
		 JSONObject mainObj = new JSONObject();
	        
		 JSONArray ja = new JSONArray();
         JsonArrayBuilder arrayBuilder =null;
         ArrayList<String> columnlist = new ArrayList<String>();
         ArrayList<JSONObject> jsonolist = new ArrayList<JSONObject>();

		try {
			util = new HisUtil("OPDDRDRDESk",
					"DoctorDeskDAO()");
			dao = new HisDAO("MMS",
					"transactions.DoctorDeskDAO..setItemCatValues()");
			
			nProcIndex = dao.setProcedure(strproc_name);
			dao.setProcInValue(nProcIndex, "modeval", "8",1);
			dao.setProcInValue(nProcIndex, "hosp_code", hosp_code,2);
			dao.setProcInValue(nProcIndex, "seat_id", "0",3);
			dao.setProcInValue(nProcIndex, "deptCode", "",4);
			dao.setProcInValue(nProcIndex, "Room_No", "",5);
			dao.setProcOutValue(nProcIndex, "err", 1,6);
			dao.setProcOutValue(nProcIndex, "resultset", 2,7);
			dao.executeProcedureByPosition(nProcIndex);

			strErr = dao.getString(nProcIndex, "err");

			if(strErr.equals(""))
			{
				ws = dao.getWebRowSet(nProcIndex, "resultset");
				 length=	ws.getMetaData().getColumnCount();
				for(int i=1;i<=length;i++)
				{
					columnlist.add(ws.getMetaData().getColumnName(i).toUpperCase());
				}
				
			}
          if (ws != null && ws.size() > 0) {
        	  
              while (ws.next()) {
            	    int j=0;
                	JSONObject js=new JSONObject();
                	for (int i=1;i<=length;i++)
                	{
                		String key=columnlist.get(i-1);
                		String value=ws.getString(i);
                		
                		//if(key.equalsIgnoreCase("HRSTR_JSON")){
                			JSONParser parser = new JSONParser();
                			if(value!=null) {
                			//	org.json.simple.JSONObject json = (org.json.simple.JSONObject) parser.parse(value);
                				js.put(key, value);
                			}else {
                				js.put(key, "{}");
                			}
                    		
                    		
						/*
						 * } else { js.put(key, value); }
						 */
                		j++;
                	
                	}
                	jsonolist.add(js) ;
                	
                }
              
              }

          mainObj.put("pat_vital_details", jsonolist);
          
          ja.put(mainObj);
        
             if(ws != null){
        		ws.close();
        		ws = null;
        	}    
             //System.out.println(jsonolist.toString());
             return jsonolist.toString();
        }
        catch (Exception e) {
        	e.printStackTrace();
        	HisException eObj = new HisException("OPD Lite","getPatinetEHRDtls.getPatVitalDataDtlsForDetailedPrescription()-->", e.getMessage() + "-->" + e);
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
		


	
	public static String getpdfForDigiLocaker(String mode,String crno, String episodeCode, String visitNo, String seatId, String entrydate, String hosp_code) {
    	/* Get EHR Details */
    	String err = "";
    	String proc_name1 = "{call pkg_opddrdesk_view.get_pat_ehr_Details(?,?,?,?,? ,?,?,?,?)}";
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
        	//System.out.println("crno::::::::::::::"+crno);
            dao = new HisDAO("WebServices", "EHRDetailsDAO.getPatinetEHRDtls()");
            procIndex1 = dao.setProcedure(proc_name1);
            dao.setProcInValue(procIndex1, "modeval", "5",1);
            dao.setProcInValue(procIndex1, "crno", crno,2);
            dao.setProcInValue(procIndex1, "episodeCode", episodeCode,3);
            dao.setProcInValue(procIndex1, "visitNo", visitNo,4);
            dao.setProcInValue(procIndex1, "seatId", seatId,5);
            dao.setProcInValue(procIndex1, "entrydate", entrydate,6);
            dao.setProcInValue(procIndex1, "hosp_code", hosp_code,7);
            dao.setProcOutValue(procIndex1, "err", 1,8);
            dao.setProcOutValue(procIndex1, "resultset", 2,9);
            
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
                		
                		//if(key.equalsIgnoreCase("HRSTR_JSON")){
                			JSONParser parser = new JSONParser();
                			if(value!=null) {
                				
                				if(key.equalsIgnoreCase("HRSTR_JSON") || key.equalsIgnoreCase("VITAL_DATA") ){
                				org.json.simple.JSONObject json = (org.json.simple.JSONObject) parser.parse(value);
                				js.put(key, json);
                			}else {
                				js.put(key, value);
                			}
                				
                			}else {
                				js.put(key, "{}");
                			}
                    		
                    		
						/*
						 * } else { js.put(key, value); }
						 */
                		j++;
                	
                	}
                	jsonolist.add(js) ;
                	
                }
              
              }

          mainObj.put("pat_vital_details", jsonolist);
          
          ja.put(mainObj);
        
             if(ws != null){
        		ws.close();
        		ws = null;
        	}    
             //System.out.println(jsonolist.toString());
             return jsonolist.toString();
        }
        catch (Exception e) {
        	e.printStackTrace();
        	HisException eObj = new HisException("OPD Lite","getPatinetEHRDtls.getPatVitalDataDtlsForDetailedPrescription()-->", e.getMessage() + "-->" + e);
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
