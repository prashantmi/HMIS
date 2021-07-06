package HisWeb.dao;

import hisglobal.exceptions.HisException;
import hisglobal.transactionmgnt.HisDAO;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.simple.parser.*;

public class TemplateDao {
	
	
public static void SaveTemplateData(String JsonData) {
        int procIndex1 = 0;
        HisDAO dao = null;
        try {
        	
        	JSONObject object = new JSONObject(JsonData);
			System.out.println("JsonData"+JsonData);
			//String strPatJsonData=(String) object.get("strPatJsonData");
   			JSONObject PatJson = new JSONObject(object.getJSONObject("strPatJsonData"));
   			System.out.println("PatJson   "+object.getJSONObject("strPatJsonData").getString("patGeneralDtl"));
   			JSONArray strFormData =(JSONArray) object.get("strFormData");
   			String tempval=object.getJSONObject("strPatJsonData").getString("patGeneralDtl");
   			//object.getJSONArray("strFormData").get(1).
   			System.out.println("strFormData"+strFormData.length());
   			dao = new HisDAO("OPD DR DESK DAO", "opdDrDeskDao.save()");
   			for (int i = 0; i < strFormData.length(); i++) {
   				
   				JSONParser parser = new JSONParser();
   				org.json.simple.JSONObject jsonObj = (org.json.simple.JSONObject) parser.parse(strFormData.getString(i));
   				//JSONObject jsonObj=(org.json.simple.JSONObject) strFormData.getString(i);
   			 for (Object keyObj : jsonObj.keySet()) {
                 String key = (String)keyObj;
                 
                 if (jsonObj.get(key) instanceof String) {
                	 String valObj =(String) jsonObj.get(key);
                     if(valObj !=null && valObj !="" && !valObj.equals("") && !valObj.equals("  "))
           			 {
           			String proc_name1 = "{call pkg_opddesk_template_dml.hopl_template_dtl(?,?,?,?,?, ?,?,?,?,?,?  ,?,?,?)}";
           			
        			
                    procIndex1 = dao.setProcedure(proc_name1);
                    dao.setProcInValue(procIndex1, "p_mode", "1",1);
                    dao.setProcInValue(procIndex1, "p_puk", object.getJSONObject("strPatJsonData").getString("CR_No"),2);
                    dao.setProcInValue(procIndex1, "p_hospcode", object.getJSONObject("strPatJsonData").getString("Hosp_Code"),3); 
                    dao.setProcInValue(procIndex1, "p_seatId", "",4); 
                    dao.setProcInValue(procIndex1, "p_episodecode",object.getJSONObject("strPatJsonData").getString("episodeCode") ,5); 
                    dao.setProcInValue(procIndex1, "p_visitno", object.getJSONObject("strPatJsonData").getString("episodeVisitNo"),6);
                    dao.setProcInValue(procIndex1, "p_json", JsonData,7);
        			dao.setProcInValue(procIndex1, "p_isvalid", "1",8);
        			
        			dao.setProcInValue(procIndex1, "p_template_id", key,9);
        			dao.setProcInValue(procIndex1, "p_template_val", valObj.toString(),10);
        			dao.setProcInValue(procIndex1, "p_template_code", object.getString("strTemplateId"),11);
        			
        			dao.setProcInValue(procIndex1, "dept_code", tempval.split("#")[6],12);
           			dao.setProcInValue(procIndex1, "deptunit_code", tempval.split("#")[5],13);
           			
           			//dao.setProcInValue(procIndex1, "p_template_id", "",9);
           			dao.setProcOutValue(procIndex1, "err", 1,14);
           			
                    dao.execute(procIndex1, 1);
           			}
                	}else {
                		System.out.println("keykeykeykeykeykeykey  "+key);
                		org.json.simple.JSONArray jsarray =(org.json.simple.JSONArray) jsonObj.get(key);
                		for (int j = 0; j < jsarray.size(); j++) {
                			String valObj =(String) jsarray.get(j);
                			System.out.println("valObj  "+valObj);
                    		
                            
                			if(valObj !=null && valObj !="" && !valObj.equals("") && !valObj.equals("  "))
                  			 {
                  			String proc_name1 = "{call pkg_opddesk_template_dml.hopl_template_dtl(?,?,?,?,?, ?,?,?,?,?,?  ,?,?  ,?)}";
                  			
               			
                           procIndex1 = dao.setProcedure(proc_name1);
                           dao.setProcInValue(procIndex1, "p_mode", "1",1);
                           dao.setProcInValue(procIndex1, "p_puk", object.getJSONObject("strPatJsonData").getString("CR_No"),2);
                           dao.setProcInValue(procIndex1, "p_hospcode", object.getJSONObject("strPatJsonData").getString("Hosp_Code"),3); 
                           dao.setProcInValue(procIndex1, "p_seatId", "",4); 
                           dao.setProcInValue(procIndex1, "p_episodecode",object.getJSONObject("strPatJsonData").getString("episodeCode") ,5); 
                           dao.setProcInValue(procIndex1, "p_visitno", object.getJSONObject("strPatJsonData").getString("episodeVisitNo"),6);
                           dao.setProcInValue(procIndex1, "p_json", JsonData,7);
               			dao.setProcInValue(procIndex1, "p_isvalid", "1",8);
               			
               			dao.setProcInValue(procIndex1, "p_template_id", key,9);
               			dao.setProcInValue(procIndex1, "p_template_val", valObj.toString(),10);
               			dao.setProcInValue(procIndex1, "p_template_code", object.getString("strTemplateId"),11);
               			
               			dao.setProcInValue(procIndex1, "dept_code", tempval.split("#")[6],12);
               			dao.setProcInValue(procIndex1, "deptunit_code", tempval.split("#")[5],13);
               			
               			//dao.setProcInValue(procIndex1, "p_template_id", "",9);
               			dao.setProcOutValue(procIndex1, "err", 1,14);
                           dao.execute(procIndex1, 1);
                  			}
                		}
                	}
                 
                // System.out.println(key   +"        ::::::     "+valObj);
   			 
   			 }
   			}
         //   dao.executeProcedureByPosition(procIndex1);
   			
   			synchronized (dao) {
   				dao.fire();
				
			}
		   		
		   			return ;
        }
        catch (Exception e) {
        	e.printStackTrace();
        	HisException eObj = new HisException("OPD Lite","TemplateDao.SaveTemplateData()-->", e.getMessage() + "-->" + e);
           // e.printStackTrace();
            return ;
        }
        finally {
        	if (dao != null) {
                dao.free();
                dao = null;
            }
        }
		
		
	
	
	}

public static void savePdf(String pdf) {
	// TODO Auto-generated method stub
	HisDAO dao=null;
	int procIndex1 = 0;
	try {
		dao = new HisDAO("OPD DR DESK DAO", "opdDrDeskDao.save()");
		String proc_name1 = "{call pkg_opddesk_template_dml.hopl_template_dtl(?,?,?,?,?, ?,?,?,?,?,?  ,?,?,?)}";
			
			
        procIndex1 = dao.setProcedure(proc_name1);
        dao.setProcInValue(procIndex1, "p_mode", "3",1);
        dao.setProcInValue(procIndex1, "p_puk", "",2);
        dao.setProcInValue(procIndex1, "p_hospcode", "",3); 
        dao.setProcInValue(procIndex1, "p_seatId", "",4); 
        dao.setProcInValue(procIndex1, "p_episodecode","" ,5); 
        dao.setProcInValue(procIndex1, "p_visitno", "",6);
        dao.setProcInValue(procIndex1, "p_json", pdf,7);
		dao.setProcInValue(procIndex1, "p_isvalid", "1",8);
		
		dao.setProcInValue(procIndex1, "p_template_id", "",9);
		dao.setProcInValue(procIndex1, "p_template_val","",10);
		dao.setProcInValue(procIndex1, "p_template_code","",11);
		dao.setProcInValue(procIndex1, "dept_code", "",12);
			dao.setProcInValue(procIndex1, "deptunit_code", "",13);
		
		//dao.setProcInValue(procIndex1, "p_template_id", "",9);
		dao.setProcOutValue(procIndex1, "err", 1,14);
        dao.execute(procIndex1, 1);
        
    	synchronized (dao) {
				dao.fire();
			
		}
	}        catch (Exception e) {
    	e.printStackTrace();
    	HisException eObj = new HisException("OPD Lite","TemplateDao.SavePDFData()-->", e.getMessage() + "-->" + e);
       // e.printStackTrace();
        return ;
    }
    finally {
    	if (dao != null) {
            dao.free();
            dao = null;
        }
	
}

}

public static void SaveTemplateJson(String JsonData) {
        int procIndex1 = 0;
        HisDAO dao = null;
        try {
        	
        	JSONObject object = new JSONObject(JsonData);
			System.out.println("JsonData"+JsonData);
			//String strPatJsonData=(String) object.get("strPatJsonData");
   			JSONObject PatJson = new JSONObject(object.getJSONObject("strPatJsonData"));
   			System.out.println("PatJson   "+object.getJSONObject("strPatJsonData").getString("CR_No"));
   			JSONArray strFormData =(JSONArray) object.get("strFormData");
   			String tempval=object.getJSONObject("strPatJsonData").getString("patGeneralDtl");
   			//object.getJSONArray("strFormData").get(1).
   			System.out.println("strFormData"+strFormData.length());
   			dao = new HisDAO("OPD DR DESK DAO", "opdDrDeskDao.save()");
   			//for (int i = 0; i < strFormData.length(); i++) {
   				
   				//JSONParser parser = new JSONParser();
   				//org.json.simple.JSONObject jsonObj = (org.json.simple.JSONObject) parser.parse(strFormData.getString(i));
   				//JSONObject jsonObj=(org.json.simple.JSONObject) strFormData.getString(i);
   			
           			String proc_name1 = "{call pkg_opddesk_template_dml.hopl_template_dtl(?,?,?,?,?, ?,?,?,?,?,?  ,?,?,?)}";
           			
        			
                    procIndex1 = dao.setProcedure(proc_name1);
                    dao.setProcInValue(procIndex1, "p_mode", "2",1);
                    dao.setProcInValue(procIndex1, "p_puk", object.getJSONObject("strPatJsonData").getString("CR_No"),2);
                    dao.setProcInValue(procIndex1, "p_hospcode", object.getJSONObject("strPatJsonData").getString("Hosp_Code"),3); 
                    dao.setProcInValue(procIndex1, "p_seatId", "",4); 
                    dao.setProcInValue(procIndex1, "p_episodecode",object.getJSONObject("strPatJsonData").getString("episodeCode") ,5); 
                    dao.setProcInValue(procIndex1, "p_visitno", object.getJSONObject("strPatJsonData").getString("episodeVisitNo"),6);
                    dao.setProcInValue(procIndex1, "p_json", JsonData,7);
        			dao.setProcInValue(procIndex1, "p_isvalid", "1",8);
        			
        			dao.setProcInValue(procIndex1, "p_template_id", "",9);
        			dao.setProcInValue(procIndex1, "p_template_val", "",10);
        			dao.setProcInValue(procIndex1, "p_template_code", object.getString("strTemplateId"),11);
        			
        			dao.setProcInValue(procIndex1, "dept_code", tempval.split("#")[6],12);
           			dao.setProcInValue(procIndex1, "deptunit_code", tempval.split("#")[5],13);
        			
        			//dao.setProcInValue(procIndex1, "p_template_id", "",9);
        			dao.setProcOutValue(procIndex1, "err", 1,14);
                    dao.execute(procIndex1, 1);
           			//}
                	
         
   			
   			synchronized (dao) {
   				dao.fire();
				
			}
		   		
		   			return ;
        }
        catch (Exception e) {
        	e.printStackTrace();
        	HisException eObj = new HisException("OPD Lite","TemplateDao.SaveTemplateData()-->", e.getMessage() + "-->" + e);
           // e.printStackTrace();
            return ;
        }
        finally {
        	if (dao != null) {
                dao.free();
                dao = null;
            }
        }
		
		
	
	
	}
}
