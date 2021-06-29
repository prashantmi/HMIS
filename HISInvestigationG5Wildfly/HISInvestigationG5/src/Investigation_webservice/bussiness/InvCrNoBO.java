package Investigation_webservice.bussiness;



import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.JDBCTransactionContext;
import hisglobal.utility.HelperMethods;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;

import Investigation_webservice.dao.InvCrNoDAO;
import Investigation_webservice.dao.InvReportServiceDAO;
import Investigation_webservice.security.AESEncryption;
import Investigation_webservice.vo.InvauthVO;
import Investigation_webservice.vo.InvestigationReportVO;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mongodb.util.JSON;

public class InvCrNoBO {

	
	
	
	public  String getDetailsBasedOnCrNo(String crno,String hospitalCode) throws Exception {
		String data = null;
		JSONObject jsonResponse = new JSONObject();
		JSONObject patDemographicJson = null;
		JSONObject patEncounterJson = null;
		JSONArray jsarr1 = new JSONArray();
		JDBCTransactionContext tx = new JDBCTransactionContext();
		tx.begin();
		InvCrNoDAO objDao = new InvCrNoDAO(tx);
		
		String patDemographicStr   = null;
		String patEncounterStr   = null;
		
		patDemographicStr=InvCrNoDAO.InvCrNoBasedDetails("0",crno,hospitalCode);
		System.out.println("patDemographicStr: "+patDemographicStr);
		if(patDemographicStr!=null && !patDemographicStr.equals("")) {
			
			patDemographicJson = new JSONObject(patDemographicStr);
			if(patDemographicJson!=null) {
				jsonResponse.put("patient_demographic_data", patDemographicJson.get("patient_demographic_data"));
				
				JSONArray patDemographicJsonArr = patDemographicJson.getJSONArray("patient_demographic_data");
				if(patDemographicJsonArr.length()>0) {
					
					String patientStatus = patDemographicJsonArr.getJSONObject(0).getString("PATSTATUS");
					if (patientStatus.equalsIgnoreCase("IPD")) {
						patEncounterStr=InvCrNoDAO.InvCrNoBasedDetails("2",crno,hospitalCode);
					} else {
						patEncounterStr=InvCrNoDAO.InvCrNoBasedDetails("1",crno,hospitalCode);
					}
					
					System.out.println("patEncounterStr: "+patEncounterStr);
					if(patEncounterStr!=null) {
						patEncounterJson = new JSONObject(patEncounterStr);
						jsonResponse.put("patient_Encounter_data", patEncounterJson.get("patient_Encounter_data"));
					} else {
						jsonResponse.put("patient_Encounter_data", jsarr1);
					}
				} else {
					jsonResponse.put("patient_Encounter_data", jsarr1);
				}
			}
		} else {
			jsonResponse.put("patient_demographic_data", jsarr1);
			
			jsonResponse.put("patient_Encounter_data", jsarr1);
		}
		
		
		return jsonResponse.toString();
	}
	
	
		
	
	public  boolean logDetail(String ipaddress,String source,String key,String url,String type) {

		String data = null;
		boolean flag=false;
		//InvReportServiceDAO objDao = new InvReportServiceDAO();
		JDBCTransactionContext tx = new JDBCTransactionContext();
		tx.begin();
		InvReportServiceDAO objDao = new InvReportServiceDAO(tx);
		
		flag = objDao.logDetail(ipaddress, source, key, url, type);
		tx.commitAll();
			return flag;
	}
	
	
	public  InvauthVO getauthdata(String sourcee) throws Exception {

		InvauthVO thaladirVO = null;
		try {
			
			String data = null;
			boolean flag=false;
			//InvReportServiceDAO objDao = new InvReportServiceDAO();
			JDBCTransactionContext tx = new JDBCTransactionContext();
			tx.begin();
			InvReportServiceDAO objDao = new InvReportServiceDAO(tx);
			
			thaladirVO = objDao.getauthdata(sourcee);
			
				
	            //reqLst.add(thaladirVO);
			}
	     catch (SQLException e){
			e.printStackTrace();
		} 
		
			return thaladirVO;
		}
	
	
}
