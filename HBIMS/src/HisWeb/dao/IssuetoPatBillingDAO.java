package HisWeb.dao;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONException;
import org.json.JSONObject;


import hisglobal.transactionmgnt.HisDAO;

public class IssuetoPatBillingDAO {
	
	
	 public static String issuetopatbill(String mode, String dept,String chargetypeid,String serviceid,String reqno,String cat,String episode,String crno,String lfaccno,String trf,String trfbatch,String trfrate,String qty,String name,String add,String contactno,String age,String ageunit,String gender,String refdoc,String refdoccontactno,String seatid,String hospcode,String wardcode,String admno,String visitno) throws JSONException {
    	
    	String err = "";
    	String proc_name1 = "{call bill_interface.dml_online_billreq_drugs(?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?,?,?)}";
    	int procIndex1 = 0;
     
        HisDAO dao = null;
        //JSONObject mainObj = new JSONObject();
         //String hosp_code=null;
         //HttpServletRequest request=null;
         //StringBuffer buffer=new StringBuffer();
         JSONObject mainObj1 = new JSONObject(); 
        
        try {
        	
   			dao = new HisDAO("WebServices", "IssuetoPatBillingDAO.issuetopatbill()");
            procIndex1 = dao.setProcedure(proc_name1);
            dao.setProcInValue(procIndex1, "modval", mode,1);
            dao.setProcInValue(procIndex1, "gnum_dept_code", dept,2);
            dao.setProcInValue(procIndex1, "sblnum_chargetype_id", chargetypeid,3);
            dao.setProcInValue(procIndex1, "sblnum_service_id", serviceid,4);
            dao.setProcInValue(procIndex1, "gstr_req_no", reqno,5);
            dao.setProcInValue(procIndex1, "gnum_treatment_cat", cat,6);
            dao.setProcInValue(procIndex1, "hrgnum_episode_code", episode,7);
            dao.setProcInValue(procIndex1, "hrgnum_puk", crno,8); 
            dao.setProcInValue(procIndex1, "hblnum_pat_lfaccount_no", lfaccno,9);
            dao.setProcInValue(procIndex1, "gstr_tariff", trf,10);
            dao.setProcInValue(procIndex1, "gstr_tariff_batch", trfbatch,11);
            
            dao.setProcInValue(procIndex1, "gstr_tariff_rates",  trfrate,12);
            dao.setProcInValue(procIndex1, "gstr_reqqty",  qty,13);
            dao.setProcInValue(procIndex1, "hblstr_patient_name",  name,14);
            dao.setProcInValue(procIndex1, "hblstr_pat_address",  add,15);
            dao.setProcInValue(procIndex1, "hblstr_contact_no",  contactno,16);
            dao.setProcInValue(procIndex1, "age",  age,17);
            
            dao.setProcInValue(procIndex1, "ageunit", ageunit,18); 
            dao.setProcInValue(procIndex1, "gender", gender,19); 
            dao.setProcInValue(procIndex1, "refdoctor", refdoc,20); 
            dao.setProcInValue(procIndex1, "refdoccontactno", refdoccontactno,21); 
            //System.out.println(accno);
            dao.setProcInValue(procIndex1, "gnum_seatid", seatid,22); 
            dao.setProcInValue(procIndex1, "hosp_code", hospcode,23); 
            dao.setProcInValue(procIndex1, "ward_code", wardcode,24); 
            dao.setProcInValue(procIndex1, "admno", admno,25); 
            dao.setProcInValue(procIndex1, "visitno", visitno,26); 
    
            dao.setProcOutValue(procIndex1, "err", 1,27);
            dao.executeProcedureByPosition(procIndex1);
            
            
            err=dao.getString(procIndex1, "err");
            
            return mainObj1.put("SUCCESS", "1").toString();
            
            /*synchronized (dao) {
				dao.fire();
			}*/
   		//return "";
        }
        catch (Exception e) {
            e.printStackTrace();
            return mainObj1.put("FAIL", "2").toString();
        }
        finally {
        	if (dao != null) {
                dao.free();
                dao = null;
            }
        }
    }

}
