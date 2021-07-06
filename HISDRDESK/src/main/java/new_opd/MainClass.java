package new_opd;

import hisglobal.backutil.HisException;
import hisglobal.transactionmgnt.HisDAO;

import java.util.ArrayList;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.sql.rowset.WebRowSet;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.sun.xml.internal.fastinfoset.util.StringArray;

public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		getPatinetEHRDtls("", "", "", "", "", "", "");

	}

	
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
            dao.setProcInValue(procIndex1, "modeval", "4",1);
            dao.setProcInValue(procIndex1, "crno", "",2);
            dao.setProcInValue(procIndex1, "episodeCode", "",3);
            dao.setProcInValue(procIndex1, "visitNo", "",4);
            dao.setProcInValue(procIndex1, "seatId", "",5);
            dao.setProcInValue(procIndex1, "entrydate", "",6);
            dao.setProcInValue(procIndex1, "hosp_code", "",7);
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
                			
                		
                		ResonOfVisit(value);
                		Investigation(value);
                		//j2.getJSONArray(key)
                	
                	}
                	              	
                }
              }
          
          mainObj.put("status", status);
          mainObj.put("pat_details", jsonolist);
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
	public static void ResonOfVisit(String jsonString) {
		
		
		try {
			String ar[] ;
		
		JSONParser parser = new JSONParser();
		org.json.simple.JSONObject json = (org.json.simple.JSONObject) parser.parse(jsonString);
		
		
    	JSONObject j2=new JSONObject(json);
		//System.out.println(":::::::::::::::::::::::::::::::::::");
		//System.out.println(j2.get("ReasonOfVisit").toString());
		org.json.simple.JSONArray ReasonOfVisit =(org.json.simple.JSONArray) j2.get("ReasonOfVisit");
		
		 ArrayList<JSONObject> jsonolist = new ArrayList<JSONObject>();
		if(ReasonOfVisit!=null)
				{
				
	   			for (int i=0 ;i<ReasonOfVisit.size();i++)
	   			{
	   				JSONObject js=new JSONObject();
					String temp[]=ReasonOfVisit.get(i).toString().split("\\^");
					if(temp[0] == "0")
					js.put("IsExternalVisit", "1");
					else
					js.put("IsExternalVisit", "0");	
					
					js.put("VisitReasonName", temp[1]);
					js.put("VisitReasonCode" , temp[0]);
					js.put( "VisitReasonSideCode" , temp[2]);
					js.put( "VisitReasonSideName" , "Bilateral");
					js.put(  "VisitReasonNoOfDays" , temp[3]);
					js.put( "VisitComplaintDurationCode" , temp[4]);
					js.put(    "VisitComplaintDurationName" , "Week/s");
					if(temp.length == 6)
					js.put(   "VisitReasonRemarks" , temp[5]);
					if(temp.length == 5)
						js.put(   "VisitReasonRemarks" , temp[4]);
						
	   				
		   			jsonolist.add(js);
	   			}

	   			//System.out.println("jsonolist::::::::::::::\n"+jsonolist);
			}
	
	
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	
public static void Investigation(String jsonString) {
		
		
		try {
			String ar[] ;
		
		JSONParser parser = new JSONParser();
		org.json.simple.JSONObject json = (org.json.simple.JSONObject) parser.parse(jsonString);
		
		
    	JSONObject j2=new JSONObject(json);
		//System.out.println(":::::::::::::::::::::::::::::::::::");
		//System.out.println(j2.get("InvTestCodeToPrint").toString());
		org.json.simple.JSONArray Investigation =(org.json.simple.JSONArray) j2.get("InvTestCodeToPrint");
		
		 ArrayList<JSONObject> jsonolist = new ArrayList<JSONObject>();
		if(Investigation!=null)
				{
				
	   			for (int i=0 ;i<Investigation.size();i++)
	   			{
	   				JSONObject js=new JSONObject();
					String temp[]=Investigation.get(i).toString().split("\\^");
					
					
					if(temp[0]=="0" || temp[0].equalsIgnoreCase("0"))
					js.put( "IsExternal", "1");
					else
					js.put( "IsExternal", "0");
					js.put(  "TestName", temp[5]);
					js.put("TestCode", temp[0]);
					js.put( "LabCode", temp[1]);
					js.put( "SampleCode", temp[2]);
					js.put(   "SampleName", temp[3]);
					js.put(   "LabName", temp[4]);
					js.put(   "IsTestGroup", "0");
					  
									  jsonolist.add(js);
	   			}

	   			//System.out.println("jsonolist::::::::::::::\n"+jsonolist);
			}
	
	
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
