package new_investigation.chatbot.util;

import hisglobal.presentation.ControllerUTIL;
import hisglobal.vo.UserVO;
//import investigationDesk.transactions.bo.InvestigationEssentialBO;

import java.io.IOException;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.ResourceBundle;

//import new_investigation.InvestigationConfig;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

/*import new_investigation.chatbot.dao.InvReportServiceDAO;
import new_investigation.chatbot.global.util.GlobalUtils;
import new_investigation.chatbot.security.AESEncryption;
import java.util.MissingResourceException;*/
//import new_investigation.chatbot.HelperMethods;
import new_investigation.chatbot.data.InvestigationChatBotDATA;
import new_investigation.chatbot.vo.InvestigationChatbotVO;
import new_investigation.transactions.controller.utl.MongoXmlHandler;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;

public class InvestigationChatBotUTIL extends ControllerUTIL{

	
public static String getRequisitionNo(String crNo, HttpServletRequest request)
{
	UserVO userVO = ControllerUTIL.getUserVO(request);
	List<InvestigationChatbotVO> reqLst = InvestigationChatBotDATA.getRequisitionNo(crNo, userVO);
	//String test_req = null;
	/*JSONArray mylist = new JSONArray();	
	JSONArray mylist2 = new JSONArray();
	
	try {	
		JSONObject jobj = new JSONObject();
		JSONObject jobj2 = new JSONObject();
		if(!reqLst.isEmpty()){
			for(int i=0;i<reqLst.size();i++){
				InvestigationChatbotVO vo=reqLst.get(i);
				jobj.put("status", 1);
				jobj.put("reqNo_vo", vo);
				jobj.put("message", "");
				//jobj.put("DateTime",date.toString());
				jobj.put("reqNo", vo.getreqNo());
				jobj.put("requisitionNo",vo.getPatrequisitioNo());
				jobj.put("labs",vo.getLabs());
				jobj.put("tests",vo.getTests());
				
			}
			jobj2.put("TotalCount", reqLst.size());
			jobj2.put("RequestStatus", "1");
			jobj2.put("message", "success");
			//jobjnew.put("Patdetails", jobjnewPatsetails);
			jobj2.put("data", mylist2);
			
			mylist2.put(jobj2);
			System.out.println("JOBJNEW "+jobj2);
			//jarray.put(data);
		}
		else
		{
			jobj2.put("TotalCount", reqLst.size());
			jobj2.put("RequestStatus", "2");
			jobj2.put("message", "No Data Found");
			jobj2.put("data", "");
			System.out.println("JOBJNEW "+jobj2);
			mylist2.put(jobj2);
			
			//jarray.put(data);
			
		}
		
	} 
	
	catch(Exception e)
	{
		e.printStackTrace();
	}
	
	return mylist.toString();
	*/
	
	JSONArray jarray2 = new JSONArray();
	JSONArray mydata = new JSONArray();
	JSONArray mydata1 = new JSONArray();
	JSONArray jarraynew = new JSONArray();
	//JSONArray jobjReqNo = new JSONArray();
	//Date date = new Date();
	JSONObject jobj1 = new JSONObject();
	
	JSONObject jobjnew = new JSONObject();
	try {
	//JSONArray jarray=new JSONArray();
	int count=0;
	if(!reqLst.isEmpty()){
		for(int i=0;i<reqLst.size();i++)
		{
			JSONObject jobj = new JSONObject();
			InvestigationChatbotVO vo=reqLst.get(i);
			
			 
			jobj.put("RequestStatus", "1");
			jobj.put("crno",vo.getCrno());
			jobj.put("reqno",vo.getReqno());
			jobj.put("reporturl",vo.getReporturl());
			String report=getReport(vo.getReporturl());
			jobj.put("report", report);
			jobj.put("reqdate",vo.getReqdate());
			jobj.put("testname",vo.getTestname());
			jobj.put("testcode",vo.getTestcode());
			jobj.put("reqdno",vo.getReqdno());
			mydata.put(jobj);
			System.out.println("JOBJ= "+jobj);
		
			if(count==0)
			{
		
				jobj1.put("RequestStatus", "1");
				jobj1.put("crno",vo.getCrno());
				jobj1.put("reqno",vo.getReqno());
				jobj1.put("reporturl",vo.getReporturl());
				jobj1.put("reqdate",vo.getReqdate());
				jobj1.put("testname",vo.getTestname());
				jobj1.put("testcode",vo.getTestcode());
				jobj1.put("reqdno",vo.getReqdno());
				mydata1.put(jobj1);
				/*jobj1.put("crno",vo.getCrno());
				jobj1.put("patname",vo.getPatname());
				jobj1.put("age",vo.getAge().trim());
				jobj1.put("gender",vo.getGender().trim());
				jobj1.put("patStatus",vo.getPatStatus());*/
				//jobj1.put("reqNo",vo.getreqNo());
				//test_req= jobj1.getString("reqNo");
				//System.out.println("REQ NO= "+test_req[i]);				
				//jobj1.put("requisitionNo",vo.getPatrequisitioNo());
				/*jobjReqNo.put(jobj1);
				count=1;
				System.out.println("JOBJ1 "+jobj1);*/
			
		}
		
		jarray2.put(jobj);
		}
		
		jobjnew.put("TotalCount", reqLst.size());
		jobjnew.put("RequestStatus", "1");
		jobjnew.put("message", "success");
		//jobjnew.put("Patdetails", jobjnewPatsetails);
		jobjnew.put("data", jarray2);
		
		jarraynew.put(jobjnew);
		System.out.println("JOBJNEW "+jobjnew);
		//jarray.put(data);
		
	}
	else
	{
		jobjnew.put("TotalCount", reqLst.size());
		jobjnew.put("RequestStatus", "2");
		jobjnew.put("message", "No Data Found");
		jobjnew.put("data", "");
		System.out.println("JOBJNEW "+jobjnew);
		jarraynew.put(jobjnew);
		
		
		//jarray.put(data);
		
	}
	//System.out.println("jarraynew= "+jarraynew);
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	/*if(encryptkey.split("#")[1].equals("1"))
		return AESEncryption.encrypt(jarraynew.toString(),encryptkey.split("#")[2]);
		else*/
			//return jarraynew.toString();
	System.out.println("MYDATA= "+mydata);
	System.out.println("jarray2= "+jarray2.toString());
	return jarray2.toString();
			
	//return reqLst.toString();
}


public static String getReport(String filename) throws IOException
{
	byte[] byteArray=null;
     
	byteArray=MongoXmlHandler.getInstance().latestFetchFile(filename);
	String base64EncodedString = org.apache.commons.codec.binary.Base64.encodeBase64String(byteArray);
	
    return base64EncodedString;
}
/*public static String getReport(String reqNo, HttpServletRequest request)
{
	UserVO userVO = ControllerUTIL.getUserVO(request);
	List<InvestigationChatbotVO> repLst = InvestigationChatBotDATA.getReport(reqNo, userVO);
	String test_rep = null;
	
	JSONArray jarray2 = new JSONArray();
	JSONArray jarraynew = new JSONArray();
	JSONArray jobjRepNo = new JSONArray();
	Date date = new Date();
	JSONObject jobj1 = new JSONObject();
	
	JSONObject jobjnew = new JSONObject();
	try {
	
	int count=0;
	if(!repLst.isEmpty()){
		for(int i=0;i<repLst.size();i++)
		{
			JSONObject jobj = new JSONObject();
			InvestigationChatbotVO vo=repLst.get(i);
		jobj.put("status","1");
		jobj.put("message","");
		jobj.put("DateTime",date.toString());
		jobj.put("repNo",vo.getRep());
		
		System.out.println("JOBJ= "+jobj);
		
		if(count==0)
		{
		
		jobj1.put("repNo",vo.getRep());
		test_rep= jobj1.getString("repNo");
		jobjRepNo.put(jobj1);
		count=1;
		System.out.println("JOBJ1 "+jobj1);
			
		}
		
		jarray2.put(jobj);
		}
		
		jobjnew.put("TotalCount", repLst.size());
		jobjnew.put("RequestStatus", "1");
		jobjnew.put("message", "success");
		jobjnew.put("data", jarray2);
		
		jarraynew.put(jobjnew);
		System.out.println("JOBJNEW "+jobjnew);
		
		
	}
	else
	{
		jobjnew.put("TotalCount", repLst.size());
		jobjnew.put("RequestStatus", "2");
		jobjnew.put("message", "No Data Found");
		jobjnew.put("data", "");
		System.out.println("JOBJNEW "+jobjnew);
		jarraynew.put(jobjnew);		
	}
	
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	
	return test_rep;*/

public static String getQues(String ques, HttpServletRequest request)
{
	UserVO userVO = ControllerUTIL.getUserVO(request);
	List<InvestigationChatbotVO> reqLst = InvestigationChatBotDATA.getQues(ques, userVO);
	//String test_req = null;
	JSONArray jarray2 = new JSONArray();
	JSONArray mydata = new JSONArray();
	JSONArray mydata1 = new JSONArray();
	JSONArray jarraynew = new JSONArray();
	//JSONArray jobjReqNo = new JSONArray();
	//Date date = new Date();
	JSONObject jobj1 = new JSONObject();
	
	JSONObject jobjnew = new JSONObject();
	try {
	int count=0;
	if(!reqLst.isEmpty()){
		for(int i=0;i<reqLst.size();i++)
		{
			JSONObject jobj = new JSONObject();
			InvestigationChatbotVO vo=reqLst.get(i);			 
			jobj.put("ques",vo.getQues());
			mydata.put(jobj);
			System.out.println("JOBJ= "+jobj);		
			if(count==0)
			{		
				jobj1.put("ques",vo.getQues());
				mydata1.put(jobj1);
		}		
		jarray2.put(jobj);
		}		
		jobjnew.put("TotalCount", reqLst.size());
		jobjnew.put("RequestStatus", "1");
		jobjnew.put("message", "success");
		jobjnew.put("data", jarray2);		
		jarraynew.put(jobjnew);
		System.out.println("JOBJNEW "+jobjnew);		
	}
	else
	{
		jobjnew.put("TotalCount", reqLst.size());
		jobjnew.put("RequestStatus", "2");
		jobjnew.put("message", "No Data Found");
		jobjnew.put("data", "");
		System.out.println("JOBJNEW "+jobjnew);
		jarraynew.put(jobjnew);
	}

	}
	catch(Exception e)
	{
		e.printStackTrace();
	}

	System.out.println("MYDATA= "+mydata);
	System.out.println("JARRAY2= "+jarray2.toString());
	return jarray2.toString();
			

}



public static String getAns(String ans, HttpServletRequest request)
{
	UserVO userVO = ControllerUTIL.getUserVO(request);
	List<InvestigationChatbotVO> reqLst = InvestigationChatBotDATA.getAns(ans, userVO);
	//String test_req = null;
	JSONArray jarray2 = new JSONArray();
	JSONArray mydata = new JSONArray();
	JSONArray mydata1 = new JSONArray();
	JSONArray jarraynew = new JSONArray();
	//JSONArray jobjReqNo = new JSONArray();
	//Date date = new Date();
	JSONObject jobj1 = new JSONObject();
	
	JSONObject jobjnew = new JSONObject();
	try {
	int count=0;
	if(!reqLst.isEmpty()){
		for(int i=0;i<reqLst.size();i++)
		{
			JSONObject jobj = new JSONObject();
			InvestigationChatbotVO vo=reqLst.get(i);			 
			jobj.put("ans",vo.getAns());
			mydata.put(jobj);
			System.out.println("JOBJ= "+jobj);		
			if(count==0)
			{		
				jobj1.put("ques",vo.getAns());
				mydata1.put(jobj1);
		}		
		jarray2.put(jobj);
		}		
		jobjnew.put("TotalCount", reqLst.size());
		jobjnew.put("RequestStatus", "1");
		jobjnew.put("message", "success");
		jobjnew.put("data", jarray2);		
		jarraynew.put(jobjnew);
		System.out.println("JOBJNEW "+jobjnew);		
	}
	else
	{
		jobjnew.put("TotalCount", reqLst.size());
		jobjnew.put("RequestStatus", "2");
		jobjnew.put("message", "No Data Found");
		jobjnew.put("data", "");
		System.out.println("JOBJNEW "+jobjnew);
		jarraynew.put(jobjnew);
	}

	}
	catch(Exception e)
	{
		e.printStackTrace();
	}

	System.out.println("MYDATA= "+mydata);
	return jarray2.toString();
			

}
public static String show_chatbot(String ans, HttpServletRequest request)
{
	UserVO userVO = ControllerUTIL.getUserVO(request);
	String reqLst = InvestigationChatBotDATA.show_chatbot(ans, userVO);
	return reqLst;
}
public static String show_chatbot_con(String ans, HttpServletRequest request)
{
	UserVO userVO = ControllerUTIL.getUserVO(request);
	String reqLst = InvestigationChatBotDATA.show_chatbot_con(ans, userVO);
	return reqLst;
}

public static String getQuesCat(String ques, HttpServletRequest request)
{
	UserVO userVO = ControllerUTIL.getUserVO(request);
	List<InvestigationChatbotVO> reqLst = InvestigationChatBotDATA.getQuesCat(ques, userVO);
	JSONArray jarray2 = new JSONArray();
	JSONArray mydata = new JSONArray();
	JSONArray mydata1 = new JSONArray();
	JSONArray jarraynew = new JSONArray();
	JSONObject jobj1 = new JSONObject();
	
	JSONObject jobjnew = new JSONObject();
	try {
	int count=0;
	if(!reqLst.isEmpty()){
		for(int i=0;i<reqLst.size();i++)
		{
			JSONObject jobj = new JSONObject();
			InvestigationChatbotVO vo=reqLst.get(i);			 
			jobj.put("ques",vo.getQuesCat());
			mydata.put(jobj);
			System.out.println("JOBJ= "+jobj);		
			if(count==0)
			{		
				jobj1.put("ques",vo.getQuesCat());
				mydata1.put(jobj1);
		}		
		jarray2.put(jobj);
		}		
		jobjnew.put("TotalCount", reqLst.size());
		jobjnew.put("RequestStatus", "1");
		jobjnew.put("message", "success");
		jobjnew.put("data", jarray2);		
		jarraynew.put(jobjnew);
		System.out.println("JOBJNEW "+jobjnew);		
	}
	else
	{
		jobjnew.put("TotalCount", reqLst.size());
		jobjnew.put("RequestStatus", "2");
		jobjnew.put("message", "No Data Found");
		jobjnew.put("data", "");
		System.out.println("JOBJNEW "+jobjnew);
		jarraynew.put(jobjnew);
	}

	}
	catch(Exception e)
	{
		e.printStackTrace();
	}

	System.out.println("MYDATA= "+mydata);
	System.out.println("JARRAY2= "+jarray2.toString());
	return jarray2.toString();
			

}


}
	
//public static String getRequisitionNo(String crno, String junk) throws Exception {
//		String data = null;
//		//InvReportServiceDAO objDao = new InvReportServiceDAO();
//		List<InvestigationChatbotVO> reqLst = getRequisitionNoDAO(crno);
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
//		/*if(encryptkey.split("#")[1].equals("1"))
//			return AESEncryption.encrypt(jarraynew.toString(),encryptkey.split("#")[2]);
//			else*/
//				return jarraynew.toString();
//	}

//
//
//
//public List<InvestigationReportVO> getRequisitionNoDAO(String crno) throws Exception {
//	Connection con = null;
//	PreparedStatement stmt = null;
//	ResultSet rs =  null;
//	String queryKey = "SELECT_REQUISITION_NO";
//	InvestigationReportVO thaladirVO = null;
//	List<InvestigationReportVO> reqLst = new ArrayList<InvestigationReportVO>();
//	String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_CHATBOT;
//	try {
//		System.out.println("queryKeyqueryKey"+queryKey);
//		String query = getQuery(filename, queryKey);
//		//String query = "select distinct(hivstr_report_url) as reportUrl,hivnum_isconfidential  as isConfidential from hivt_requisition_dtl where hivnum_req_no=? and  hivstr_report_url is not null;";
//		//con = GlobalUtils.getBloodBankUATConnection();		
//		con = getConnection();
//		stmt = con.prepareStatement(query);
//		stmt.setString(1, crno);
//		rs =  stmt.executeQuery();
//		while (rs.next()) {
//			thaladirVO= new InvestigationReportVO();
//            HelperMethods.populateVOfrmRS(thaladirVO, rs);
//            reqLst.add(thaladirVO);
//		}
//	} catch (SQLException e){
//		e.printStackTrace();
//	} finally {
//		try {
//			if (rs != null)
//				rs.close();
//			if (stmt != null)
//				stmt.close();				
//			if (con != null) 
//				con.close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}
//	
//		return reqLst;
//	}
//
//
//public static Connection getConnection(){
//	Connection con = null;
//	try {
//		Class.forName("org.postgresql.Driver");
//		con = DriverManager.getConnection("jdbc:postgresql://10.226.80.35:5444/hmis_aiims_patna", "hmisaiimsp", "hmisaiimsp");
//	} catch (Exception e) {
//		e.printStackTrace();
//	}
//	return con;
//}
//
//public static String getQuery(String filename, String queryKey) throws Exception{
//	//String query = "select distinct(hivstr_report_url) as reportUrl,hivnum_isconfidential  as isConfidential from hivt_requisition_dtl where hivnum_req_no=? and  hivstr_report_url is not null;";
//	ResourceBundle rb = ResourceBundle.getBundle(filename);
//	System.out.println("queryKey" + queryKey);
//	String query = rb.getString(queryKey);
//	
//	return query;
//}

