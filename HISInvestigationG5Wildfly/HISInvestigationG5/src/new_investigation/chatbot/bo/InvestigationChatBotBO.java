package new_investigation.chatbot.bo;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.persistence.JDBCTransactionContext;
import hisglobal.vo.UserVO;

//import java.util.Date;
import java.util.List;

//import oracle.sql.DATE;

/*import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mongodb.util.JSON;*/

import new_investigation.chatbot.dao.InvestigationChatBotDAO;
//import new_investigation.chatbot.security.AESEncryption;
import new_investigation.chatbot.vo.InvestigationChatbotVO;
//import new_investigation.transactions.bo.InvestigationEssentialBOi;
//import new_investigation.transactions.dao.InvestigationEssentialDAO;
//import new_investigation.vo.Inv_RequisitionRaisingPatientVO;
public class InvestigationChatBotBO  implements InvestigationChatBotBOi{

	
//	public String getMobileNo(String crno,String encryptkey) throws Exception {
//		String data = null;
//		InvReportServiceDAO objDao = new InvReportServiceDAO();
//		List<InvestigationReportVO> reqLst = objDao.getMobileNo(crno);
//		JSONArray jarray2 = new JSONArray();
//		JSONArray jarraynew = new JSONArray();
//		Date date = new Date();
//		JSONObject jobjnew = new JSONObject();
//		JSONArray jarray=new JSONArray();
//		if(!reqLst.isEmpty()){
//			for(int i=0;i<reqLst.size();i++)
//			{
//				JSONObject jobj = new JSONObject();
//				InvestigationReportVO vo=reqLst.get(i);
//			jobj.put("status","1");
//			jobj.put("message","");
//			jobj.put("DateTime",date.toString());
//			jobj.put("MobileNo",vo.getMobileNo());
//			
//			jarray2.put(jobj);
//			}
//			
//			jobjnew.put("TotalCount", reqLst.size());
//			jobjnew.put("RequestStatus", "1");
//			jobjnew.put("message", "success");
//			jobjnew.put("data", jarray2);
//			
//			jarraynew.put(jobjnew);	//	jarray.put(data);
//			
//		}
//		else
//		{
//			jobjnew.put("TotalCount", reqLst.size());
//			jobjnew.put("RequestStatus", "2");
//			jobjnew.put("message", "Mobile No Not Found");
//			jobjnew.put("data", "");
//			
//			jarraynew.put(jobjnew);
//			//jarray.put(data);
//			
//			
//		}
//		if(encryptkey.split("#")[1].equals("1"))
//			return AESEncryption.encrypt(jarraynew.toString(),encryptkey.split("#")[2]);
//			else
//				return jarraynew.toString();
//	}
//
//	
//	public String getRequisitionNo(String crno,String days,String encryptkey) throws Exception {
//		String data = null;
//		InvReportServiceDAO objDao = new InvReportServiceDAO();
//		List<InvestigationReportVO> reqLst = objDao.getRequisitionNo(crno,days);
//		JSONArray jarray2 = new JSONArray();
//		JSONArray jarraynew = new JSONArray();
//		JSONArray jobjnewPatsetails = new JSONArray();
//		Date date = new Date();
//		JSONObject jobj1 = new JSONObject();
//		
//		JSONObject jobjnew = new JSONObject();
//		
//		JSONArray jarray=new JSONArray();
//		int count=0;
//		if(!reqLst.isEmpty()){
//			for(int i=0;i<reqLst.size();i++)
//			{
//				JSONObject jobj = new JSONObject();
//				InvestigationReportVO vo=reqLst.get(i);
//			jobj.put("status","1");
//			jobj.put("message","");
//			jobj.put("DateTime",date.toString());
//			jobj.put("requisitionNo",vo.getPatrequisitioNo());
//			jobj.put("labs",vo.getLabs());
//			jobj.put("tests",vo.getTests());
//			
//			if(count==0)
//			{
//			
//			jobj1.put("crno",vo.getCrno());
//			jobj1.put("patname",vo.getPatname());
//			jobj1.put("age",vo.getAge().trim());
//			jobj1.put("gender",vo.getGender().trim());
//			jobj1.put("patStatus",vo.getPatStatus());
//			jobjnewPatsetails.put(jobj1);
//			count=1;
//				
//			}
//			
//			jarray2.put(jobj);
//			}
//			
//			jobjnew.put("TotalCount", reqLst.size());
//			jobjnew.put("RequestStatus", "1");
//			jobjnew.put("message", "success");
//			jobjnew.put("Patdetails", jobjnewPatsetails);
//			jobjnew.put("data", jarray2);
//			
//			jarraynew.put(jobjnew);
//			//jarray.put(data);
//			
//		}
//		else
//		{
//			jobjnew.put("TotalCount", reqLst.size());
//			jobjnew.put("RequestStatus", "2");
//			jobjnew.put("message", "No Data Found");
//			jobjnew.put("data", "");
//			
//			jarraynew.put(jobjnew);
//			//jarray.put(data);
//			
//		}
//		System.out.println(jarraynew);
//		if(encryptkey.split("#")[1].equals("1"))
//			return AESEncryption.encrypt(jarraynew.toString(),encryptkey.split("#")[2]);
//			else
//				return jarraynew.toString();
//	}
//	
//	
//	public String getReport(String crno,String requisitionNo,String encryptkey) throws Exception {
//		String data = null;
//		InvReportServiceDAO objDao = new InvReportServiceDAO();
//		JSONArray jarray2 = new JSONArray();
//		JSONArray jarraynew = new JSONArray();
//		 Date date = new Date();
//		JSONObject jobjnew = new JSONObject();
//		List<InvestigationReportVO> reqLst = objDao.getReport(crno,requisitionNo);
//		if(!reqLst.isEmpty()){
////			Gson gson = new GsonBuilder().create();
////			data = gson.toJson(reqLst);
//			for(int i=0;i<reqLst.size();i++)
//			{
//				JSONObject jobj = new JSONObject();
//				InvestigationReportVO vo=reqLst.get(i);
//			jobj.put("status",vo.getStatus());
//			jobj.put("message",vo.getMessage());
//			jobj.put("DateTime",date.toString());
//			jobj.put("ReportData",vo.getData());
//			
//			jarray2.put(jobj);
//			}
//			
//			jobjnew.put("TotalCount", reqLst.size());
//			jobjnew.put("RequestStatus", "1");
//			jobjnew.put("message", "success");
//			jobjnew.put("data", jarray2);
//			
//			jarraynew.put(jobjnew);
////			JSONArray jarraydata=new JSONArray(data);
////			JSONObject jobject=new JSONObject();		
////			
////			jobject.put("TotalCount", reqLst.size());
////			
////			jobject.put("message", "success");
////			jobject.put("Data",jarraydata);
////			
////			jarray.put(jobject);
//			//jarray.put(jarraydata);
//			
//			
//			
//		
//			
//		//     j.put(data);
//			
//		}
//		else
//		{
//
//			jobjnew.put("TotalCount", reqLst.size());
//			jobjnew.put("RequestStatus", "2");
//			jobjnew.put("message", "No Report Found");
//			jobjnew.put("data", "");
//			
//			jarraynew.put(jobjnew);
//			/*//JSONObject jobject=new JSONObject();
//			jobject.put("TotalCount", reqLst.size());
//			
//			jobject.put("message", "No Report Found");
//			//jobject.put("Data", "");
//			 jarray=new JSONArray();
//			jarray.put(jobject);
//			jarray.put(data);*/
//		}
//		
//	
//		//System.out.println("==="+jarraynew.toString());
//		if(encryptkey.split("#")[1].equals("1"))
//		return AESEncryption.encrypt(jarraynew.toString(),encryptkey.split("#")[2]);
//		else
//			return jarraynew.toString();
//	}
//	/*ABHIJAT CHATURVEDI*/
//	public String getQuestion(String query) throws Exception {
//		InvReportServiceDAO objDao = new InvReportServiceDAO();
//		List<String> reqLst = objDao.getQuestion(query);
//		return reqLst.toString();
//	}
//	public String getAnswer(String asked) throws Exception {
//		InvReportServiceDAO objDao = new InvReportServiceDAO();
//		List<String> reqLst = objDao.getAnswer(asked);
//		return reqLst.toString();
//	}
//	/*--------------------*/


	@Override
	public List<InvestigationChatbotVO> getRequisitionNo(String crNo,
			UserVO _UserVO) {
		// TODO Auto-generated method stub
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List<InvestigationChatbotVO> reqList = null;
		try {

			tx.begin();

			InvestigationChatBotDAO invChatbotDAO =new InvestigationChatBotDAO(tx);
			reqList=invChatbotDAO.getRequisitionNo(crNo, _UserVO);
			
			System.out.println("mylist= "+reqList);



		} catch (HisApplicationExecutionException e) {
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		} catch (HisDataAccessException e) {
			e.printStackTrace();
			throw new HisDataAccessException();
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
			System.out.println("error.... Essential BO");
			
		} finally {

			tx.close();
		}

		return reqList;
	}
	
	/*public List<InvestigationChatbotVO> getReport(String reqNo,
			UserVO _UserVO) {
		// TODO Auto-generated method stub
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List<InvestigationChatbotVO> repList = null;
		try {

			tx.begin();

			InvestigationChatBotDAO invChatbotDAO =new InvestigationChatBotDAO(tx);
			repList=invChatbotDAO.getReport(reqNo, _UserVO);
			
			System.out.println("mylist= "+repList);



		} catch (HisApplicationExecutionException e) {
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		} catch (HisDataAccessException e) {
			e.printStackTrace();
			throw new HisDataAccessException();
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
			System.out.println("error.... Essential BO");
			
		} finally {

			tx.close();
		}

		return repList;
	}*/
	
	public List<InvestigationChatbotVO> getQues(String ques,UserVO _UserVO) {
		// TODO Auto-generated method stub
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List<InvestigationChatbotVO> quesList = null;
		try {
			tx.begin();
			InvestigationChatBotDAO invChatbotDAO =new InvestigationChatBotDAO(tx);
			quesList=invChatbotDAO.getQues(ques, _UserVO);			
			System.out.println("mylist= "+quesList);
		} catch (HisApplicationExecutionException e) {
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		} catch (HisDataAccessException e) {
			e.printStackTrace();
			throw new HisDataAccessException();
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
			System.out.println("error.... Essential BO");			
		} finally {

			tx.close();
		}

		return quesList;
	}
	
	
	public List<InvestigationChatbotVO> getAns(String ans,
			UserVO _UserVO) {
		// TODO Auto-generated method stub
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List<InvestigationChatbotVO> quesList = null;
		try {
			tx.begin();
			InvestigationChatBotDAO invChatbotDAO =new InvestigationChatBotDAO(tx);
			quesList=invChatbotDAO.getAns(ans, _UserVO);			
			System.out.println("mylist= "+quesList);
		} catch (HisApplicationExecutionException e) {
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		} catch (HisDataAccessException e) {
			e.printStackTrace();
			throw new HisDataAccessException();
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
			System.out.println("error.... Essential BO");			
		} finally {

			tx.close();
		}

		return quesList;
	}
	
	public String show_chatbot(String ques,UserVO _UserVO) {
		// TODO Auto-generated method stub
		JDBCTransactionContext tx = new JDBCTransactionContext();
		String quesList = null;
		try {
			tx.begin();
			InvestigationChatBotDAO invChatbotDAO =new InvestigationChatBotDAO(tx);
			quesList=invChatbotDAO.show_chatbot(ques, _UserVO);			
			System.out.println("show_chatbot= "+quesList);
		} catch (HisApplicationExecutionException e) {
			e.printStackTrace();
			//throw new HisApplicationExecutionException();
			tx.close();
			char ch = '0';
			String s=Character.toString(ch);
			return (s);
		} catch (HisDataAccessException e) {
			e.printStackTrace();
			//throw new HisDataAccessException();
			tx.close();
			char ch = '0';
			String s=Character.toString(ch);
			return (s);
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
			System.out.println("error.... Essential BO");	
			tx.close();
			char ch = '0';
			String s=Character.toString(ch);
			return (s);
		} finally {

			tx.close();
		}

		return quesList;
	}
	
	public String show_chatbot_con(String ques,UserVO _UserVO) {
		// TODO Auto-generated method stub
		JDBCTransactionContext tx = new JDBCTransactionContext();
		String quesList = null;
		try {
			tx.begin();
			InvestigationChatBotDAO invChatbotDAO =new InvestigationChatBotDAO(tx);
			quesList=invChatbotDAO.show_chatbot_con(ques, _UserVO);			
			System.out.println("show_chatbot_con= "+quesList);
		} catch (HisApplicationExecutionException e) {
			e.printStackTrace();
			//throw new HisApplicationExecutionException();
			tx.close();
			char ch = '0';
			String s=Character.toString(ch);
			return (s);
		} catch (HisDataAccessException e) {
			e.printStackTrace();
			//throw new HisDataAccessException();
			tx.close();
			char ch = '0';
			String s=Character.toString(ch);
			return (s);
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
			System.out.println("error.... Essential BO");	
			tx.close();
			char ch = '0';
			String s=Character.toString(ch);
			return (s);
		} finally {

			tx.close();
		}

		return quesList;
	}
	public List<InvestigationChatbotVO> getQuesCat(String ques,UserVO _UserVO) {
		// TODO Auto-generated method stub
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List<InvestigationChatbotVO> quesList = null;
		try {
			tx.begin();
			InvestigationChatBotDAO invChatbotDAO =new InvestigationChatBotDAO(tx);
			quesList=invChatbotDAO.getQuesCat(ques, _UserVO);			
			System.out.println("mylist= "+quesList);
		} catch (HisApplicationExecutionException e) {
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		} catch (HisDataAccessException e) {
			e.printStackTrace();
			throw new HisDataAccessException();
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
			System.out.println("error.... Essential BO");			
		} finally {

			tx.close();
		}

		return quesList;
	}
}
