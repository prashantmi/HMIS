package Investigation_webservice.bussiness;



import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.JDBCTransactionContext;
import hisglobal.utility.HelperMethods;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;









import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;

import Investigation_webservice.dao.InvReportServiceDAO;
import Investigation_webservice.security.AESEncryption;
import Investigation_webservice.vo.InvauthVO;
import Investigation_webservice.vo.InvestigationReportVO;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mongodb.util.JSON;

public class InvReportServiceBO {

	
	public String getMobileNo(String crno,String encryptkey) throws Exception {
		String data = null;
		JDBCTransactionContext tx = new JDBCTransactionContext();
		tx.begin();
		InvReportServiceDAO objDao = new InvReportServiceDAO(tx);
		List<InvestigationReportVO> reqLst = objDao.getMobileNo(crno);
		JSONArray jarray2 = new JSONArray();
		JSONArray jarraynew = new JSONArray();
		Date date = new Date();
		JSONObject jobjnew = new JSONObject();
		JSONArray jarray=new JSONArray();
		if(!reqLst.isEmpty()){
			for(int i=0;i<reqLst.size();i++)
			{
				JSONObject jobj = new JSONObject();
				InvestigationReportVO vo=reqLst.get(i);
			jobj.put("status","1");
			jobj.put("message","");
			jobj.put("DateTime",date.toString());
			jobj.put("MobileNo",vo.getMobileNo());
			
			jarray2.put(jobj);
			}
			
			jobjnew.put("TotalCount", reqLst.size());
			jobjnew.put("RequestStatus", "1");
			jobjnew.put("message", "success");
			jobjnew.put("data", jarray2);
			
			jarraynew.put(jobjnew);	//	jarray.put(data);
			
		}
		else
		{
			jobjnew.put("TotalCount", reqLst.size());
			jobjnew.put("RequestStatus", "2");
			jobjnew.put("message", "Mobile No Not Found");
			jobjnew.put("data", "");
			
			jarraynew.put(jobjnew);
			//jarray.put(data);
			
			
		}
		if(encryptkey.split("#")[1].equals("1"))
			return AESEncryption.encrypt(jarraynew.toString(),encryptkey.split("#")[2]);
			else
				return jarraynew.toString();
	}

	
	public String getRequisitionNo(String crno,String days,String encryptkey) throws Exception {
		String data = null;
		JDBCTransactionContext tx = new JDBCTransactionContext();
		tx.begin();
		InvReportServiceDAO objDao = new InvReportServiceDAO(tx);
	//	InvReportServiceDAO objDao = new InvReportServiceDAO();
		List<InvestigationReportVO> reqLst = objDao.getRequisitionNo(crno,days);
		JSONArray jarray2 = new JSONArray();
		JSONArray jarraynew = new JSONArray();
		JSONArray jobjnewPatsetails = new JSONArray();
		Date date = new Date();
		JSONObject jobj1 = new JSONObject();
		
		JSONObject jobjnew = new JSONObject();
		
		JSONArray jarray=new JSONArray();
		int count=0;
		if(!reqLst.isEmpty()){
			for(int i=0;i<reqLst.size();i++)
			{
				JSONObject jobj = new JSONObject();
				InvestigationReportVO vo=reqLst.get(i);
			jobj.put("status","1");
			jobj.put("message","");
			jobj.put("DateTime",date.toString());
			jobj.put("requisitionNo",vo.getPatrequisitioNo());
			jobj.put("labs",vo.getLabs());
			jobj.put("tests",vo.getTests());
			
			if(count==0)
			{
			
			jobj1.put("crno",vo.getCrno());
			jobj1.put("patname",vo.getPatname());
			jobj1.put("age",vo.getAge().trim());
			jobj1.put("gender",vo.getGender().trim());
			jobj1.put("patStatus",vo.getPatStatus());
			jobjnewPatsetails.put(jobj1);
			count=1;
				
			}
			
			jarray2.put(jobj);
			}
			
			jobjnew.put("TotalCount", reqLst.size());
			jobjnew.put("RequestStatus", "1");
			jobjnew.put("message", "success");
			jobjnew.put("Patdetails", jobjnewPatsetails);
			jobjnew.put("data", jarray2);
			
			jarraynew.put(jobjnew);
			//jarray.put(data);
			
		}
		else
		{
			jobjnew.put("TotalCount", reqLst.size());
			jobjnew.put("RequestStatus", "2");
			jobjnew.put("message", "No Data Found");
			jobjnew.put("data", "");
			
			jarraynew.put(jobjnew);
			//jarray.put(data);
			
		}
		if(encryptkey.split("#")[1].equals("1"))
			return AESEncryption.encrypt(jarraynew.toString(),encryptkey.split("#")[2]);
			else
				return jarraynew.toString();
	}
	
	
	public String getReport(String crno,String requisitionNo,String encryptkey) throws Exception {
		String data = null;
		//InvReportServiceDAO objDao = new InvReportServiceDAO();
		JDBCTransactionContext tx = new JDBCTransactionContext();
		tx.begin();
		InvReportServiceDAO objDao = new InvReportServiceDAO(tx);
		
		JSONArray jarray2 = new JSONArray();
		JSONArray jarraynew = new JSONArray();
		 Date date = new Date();
		JSONObject jobjnew = new JSONObject();
		List<InvestigationReportVO> reqLst = objDao.getReport(crno,requisitionNo);
		if(!reqLst.isEmpty()){
//			Gson gson = new GsonBuilder().create();
//			data = gson.toJson(reqLst);
			for(int i=0;i<reqLst.size();i++)
			{
				JSONObject jobj = new JSONObject();
				InvestigationReportVO vo=reqLst.get(i);
			jobj.put("status",vo.getStatus());
			jobj.put("message",vo.getMessage());
			jobj.put("DateTime",date.toString());
			jobj.put("ReportData",vo.getData());
			
			jarray2.put(jobj);
			}
			
			jobjnew.put("TotalCount", reqLst.size());
			jobjnew.put("RequestStatus", "1");
			jobjnew.put("message", "success");
			jobjnew.put("data", jarray2);
			
			jarraynew.put(jobjnew);
//			JSONArray jarraydata=new JSONArray(data);
//			JSONObject jobject=new JSONObject();		
//			
//			jobject.put("TotalCount", reqLst.size());
//			
//			jobject.put("message", "success");
//			jobject.put("Data",jarraydata);
//			
//			jarray.put(jobject);
			//jarray.put(jarraydata);
			
			
			
		
			
		//     j.put(data);
			
		}
		else
		{

			jobjnew.put("TotalCount", reqLst.size());
			jobjnew.put("RequestStatus", "2");
			jobjnew.put("message", "No Report Found");
			jobjnew.put("data", "");
			
			jarraynew.put(jobjnew);
			/*//JSONObject jobject=new JSONObject();
			jobject.put("TotalCount", reqLst.size());
			
			jobject.put("message", "No Report Found");
			//jobject.put("Data", "");
			 jarray=new JSONArray();
			jarray.put(jobject);
			jarray.put(data);*/
		}
		
	
		//System.out.println("==="+jarraynew.toString());
		if(encryptkey.split("#")[1].equals("1"))
		return AESEncryption.encrypt(jarraynew.toString(),encryptkey.split("#")[2]);
		else
			return jarraynew.toString();
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
