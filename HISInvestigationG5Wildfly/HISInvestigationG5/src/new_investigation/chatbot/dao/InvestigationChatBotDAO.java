package new_investigation.chatbot.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
//import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import new_investigation.InvestigationConfig;
//import new_investigation.chatbot.HelperMethods;
//import new_investigation.chatbot.global.util.GlobalUtils;
import new_investigation.chatbot.vo.InvestigationChatbotVO;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.JDBCTransactionContext;
//import hisglobal.persistence.TransactionContext;
import hisglobal.utility.Sequence;
import hisglobal.vo.UserVO;

public class InvestigationChatBotDAO extends DataAccessObject
{

	public InvestigationChatBotDAO(JDBCTransactionContext _tx) {
		super(_tx);
		// TODO Auto-generated constructor stub
	}
	
	
	public List<InvestigationChatbotVO> getRequisitionNo(String crno, UserVO userVO) throws Exception {
		String query="";
		Map populateMap= new HashMap();
		ResultSet rs=null;
		//List parameterCombo=new ArrayList();
		String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_CHATBOT;
		//String queryKey="SELECT_REQUISITION_NO";
		String queryKey="SELECT_REPORT";
		Sequence sq= new Sequence();
		Connection conn=super.getTransactionContext().getConnection();
		
		//InvestigationChatbotVO thaladirVO = null;
		List<InvestigationChatbotVO> reqLst = new ArrayList<InvestigationChatbotVO>();
		//InvestigationChatbotVO voobj = new InvestigationChatbotVO();
		populateMap.put(sq.next(),crno);		
		
		 
		
		try
		{
			query = HelperMethodsDAO.getQuery(filename,queryKey);
			System.out.println(query);
			PreparedStatement stmt = null;
			stmt = conn.prepareStatement(query);
			stmt.setString(1, crno);
			
		}
		catch(Exception e)
		{
			
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::"+e);
			
		}
		try
		{
			rs = HelperMethodsDAO.executeQuery(conn, query, populateMap);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException();
			}
			else
			{
				rs.beforeFirst();
			//updated by krishnan nema on 28/09/2018
				//parameterCombo=HelperMethodsDAO.getAlOfEntryObjectsInvLabs(rs);
				while(rs.next()){
					InvestigationChatbotVO voobj1 = new InvestigationChatbotVO();
					String crno1=rs.getString(1);
					voobj1.setCrno(crno1);
					String reqno=rs.getString(2);
					voobj1.setReqno(reqno);
					String reporturl=rs.getString(3);
					voobj1.setReporturl(reporturl);
					String reqdate=rs.getString(4);
					voobj1.setReqdate(reqdate);
					String testname=rs.getString(5);
					voobj1.setTestname(testname);
					String testcode=rs.getString(6);
					voobj1.setTestcode(testcode);
					String reqdno=rs.getString(7);
					voobj1.setReqdno(reqdno);
					reqLst.add(voobj1);
				}
				
					/*thaladirVO= new InvestigationChatbotVO();
		            HelperMethods.populateVOfrmRS(thaladirVO, rs);
		            reqLst.add(thaladirVO);
		            thaladirVO.setreqNo(rs);
		            thaladirVO.setreqNo((List<Integer>) rs.getArray(1));*/
		            
		       
				
			}
		}
		catch(Exception e)
		{
			if(e.getClass()==HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException();	
			}
			else			 		
			 throw new HisDataAccessException("HisDataAccessException:: "+e);			 
		 }	
		return reqLst;
		}
	
	
	
	/*public List<InvestigationChatbotVO> getReport(String rep, UserVO userVO) throws Exception {
		String query="";
		Map populateMap= new HashMap();
		ResultSet rs=null;
		List parameterCombo=new ArrayList();
		String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_CHATBOT;
		String queryKey="SELECT_REPORT_PDF";
		Sequence sq= new Sequence();
		Connection conn=super.getTransactionContext().getConnection();
		
		InvestigationChatbotVO thaladirVO = null;
		List<InvestigationChatbotVO> repLst = new ArrayList<InvestigationChatbotVO>();
		InvestigationChatbotVO voobj = new InvestigationChatbotVO();
		populateMap.put(sq.next(),rep);		
		
		 
		
		try
		{
			query = HelperMethodsDAO.getQuery(filename,queryKey);
			System.out.println(query);
			PreparedStatement stmt = null;
			stmt = conn.prepareStatement(query);
			stmt.setString(1, rep);
			
		}
		catch(Exception e)
		{
			
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::"+e);
			
		}
		try
		{
			rs = HelperMethodsDAO.executeQuery(conn, query, populateMap);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException();
			}
			else
			{
				rs.beforeFirst();
			
				while(rs.next()){
					InvestigationChatbotVO voobj1 = new InvestigationChatbotVO();
					String rq=rs.getString(1);
					voobj1.setRep(rq);					
					repLst.add(voobj1);
				}
				 
			}
		}
		catch(Exception e)
		{
			if(e.getClass()==HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException();	
			}
			else			 		
			 throw new HisDataAccessException("HisDataAccessException:: "+e);			 
		 }	
		return repLst;
		}
	*/
	
	public List<InvestigationChatbotVO> getQues(String ques,UserVO userVO) throws Exception {
		String query="";
		Map populateMap= new HashMap();
		ResultSet rs=null;
		//List parameterCombo=new ArrayList();
		String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_CHATBOT;
		String queryKey="SELECT_QUES";
		Sequence sq= new Sequence();
		Connection conn=super.getTransactionContext().getConnection();		
		//InvestigationChatbotVO thaladirVO = null;
		List<InvestigationChatbotVO> reqLst = new ArrayList<InvestigationChatbotVO>();
		//InvestigationChatbotVO voobj = new InvestigationChatbotVO();
		populateMap.put(sq.next(),ques);		
		try
		{
			query = HelperMethodsDAO.getQuery(filename,queryKey);
			System.out.println(query);
			PreparedStatement stmt = null;
			stmt = conn.prepareStatement(query);
			stmt.setString(1, ques);			
		}
		catch(Exception e)
		{			
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::"+e);
			
		}
		try
		{
			rs = HelperMethodsDAO.executeQuery(conn, query, populateMap);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException();
			}
			else
			{
				rs.beforeFirst();
				while(rs.next()){
					InvestigationChatbotVO voobj1 = new InvestigationChatbotVO();
					String ques1=rs.getString(1);
					voobj1.setQues(ques1);
					reqLst.add(voobj1);
				}

			}
		}
		catch(Exception e)
		{
			if(e.getClass()==HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException();	
			}
			else			 		
			 throw new HisDataAccessException("HisDataAccessException:: "+e);			 
		 }	
		return reqLst;
		}
	
	public List<InvestigationChatbotVO> getAns(String ans, UserVO userVO) throws Exception {
		String query="";
		Map populateMap= new HashMap();
		ResultSet rs=null;
		//List parameterCombo=new ArrayList();
		String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_CHATBOT;
		String queryKey="SELECT_ANS";
		Sequence sq= new Sequence();
		Connection conn=super.getTransactionContext().getConnection();		
		//InvestigationChatbotVO thaladirVO = null;
		List<InvestigationChatbotVO> reqLst = new ArrayList<InvestigationChatbotVO>();
		//InvestigationChatbotVO voobj = new InvestigationChatbotVO();
		populateMap.put(sq.next(),ans);		
		try
		{
			query = HelperMethodsDAO.getQuery(filename,queryKey);
			System.out.println(query);
			PreparedStatement stmt = null;
			stmt = conn.prepareStatement(query);
			stmt.setString(1, ans);			
		}
		catch(Exception e)
		{			
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::"+e);
			
		}
		try
		{
			rs = HelperMethodsDAO.executeQuery(conn, query, populateMap);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException();
			}
			else
			{
				rs.beforeFirst();
				while(rs.next()){
					InvestigationChatbotVO voobj1 = new InvestigationChatbotVO();
					String ques1=rs.getString(1);
					voobj1.setAns(ques1);
					reqLst.add(voobj1);
				}

			}
		}
		catch(Exception e)
		{
			if(e.getClass()==HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException();	
			}
			else			 		
			 throw new HisDataAccessException("HisDataAccessException:: "+e);			 
		 }	
		return reqLst;
		}
	
	public String show_chatbot(String ans, UserVO userVO) throws Exception {
		String flg = "";
		String query="";
		Map populateMap= new HashMap();
		ResultSet rs=null;
		String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_CHATBOT;
		String queryKey="SHOW_CHATBOT";
		Sequence sq= new Sequence();
		Connection conn=super.getTransactionContext().getConnection();		
		//List<InvestigationChatBotVO> reqLst = new ArrayList<InvestigationChatBotVO>();
		//populateMap.put(sq.next(),ans);		
		try
		{
			query = HelperMethodsDAO.getQuery(filename,queryKey);
			System.out.println("query= "+query);
			PreparedStatement stmt = null;
			stmt = conn.prepareStatement(query);
		}
		catch(Exception e)
		{			
			//throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::"+e);
			char ch = '0';
			String s=Character.toString(ch);
			return (s);
			
		}
		try
		{
			rs = HelperMethodsDAO.executeQuery(conn, query);
			if (!rs.next())
			{
				//throw new HisRecordNotFoundException();
				char ch = '0';
				String s=Character.toString(ch);
				return (s);
			}
			else
			{
				rs.beforeFirst();
				while(rs.next()){
					flg=rs.getString(1);					
				}

			}
		}
		catch(Exception e)
		{
			if(e.getClass()==HisRecordNotFoundException.class)
			{
				//throw new HisRecordNotFoundException();
				char ch = '0';
				String s=Character.toString(ch);
				return (s);
			}
			else{
				char er = '0';
				String s=String.valueOf(er);  
				return (s);
			 //throw new HisDataAccessException("HisDataAccessException:: "+e);
			}
		 }	
		return flg;
		}
	
	public String show_chatbot_con(String ans, UserVO userVO) throws Exception {
		String flg = "";
		String query="";
		Map populateMap= new HashMap();
		ResultSet rs=null;
		String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_CHATBOT;
		String queryKey="SHOW_CHATBOT_CON";
		Sequence sq= new Sequence();
		Connection conn=super.getTransactionContext().getConnection();		
		try
		{
			query = HelperMethodsDAO.getQuery(filename,queryKey);
			System.out.println("query= "+query);
			PreparedStatement stmt = null;
			stmt = conn.prepareStatement(query);
		}
		catch(Exception e)
		{			
			//throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::"+e);
			char ch = '0';
			String s=Character.toString(ch);
			return (s);
			
		}
		try
		{
			rs = HelperMethodsDAO.executeQuery(conn, query);
			if (!rs.next())
			{
				//throw new HisRecordNotFoundException();
				char ch = '0';
				String s=Character.toString(ch);
				return (s);
			}
			else
			{
				rs.beforeFirst();
				while(rs.next()){
					flg=rs.getString(1);					
				}

			}
		}
		catch(Exception e)
		{
			if(e.getClass()==HisRecordNotFoundException.class)
			{
				//throw new HisRecordNotFoundException();
				char ch = '0';
				String s=Character.toString(ch);
				return (s);
			}
			else{
				char er = '0';
				String s=String.valueOf(er);  
				return (s);
			 //throw new HisDataAccessException("HisDataAccessException:: "+e);
			}
		 }	
		return flg;
		}
	
	public List<InvestigationChatbotVO> getQuesCat(String ques,UserVO userVO) throws Exception {
		System.out.println("Check this == "+ques);
		String query="";
		Map populateMap= new HashMap();
		ResultSet rs=null;
		String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_CHATBOT;
		String queryKey="SELECT_QUES_CAT";
		Sequence sq= new Sequence();
		Connection conn=super.getTransactionContext().getConnection();		
		List<InvestigationChatbotVO> reqLst = new ArrayList<InvestigationChatbotVO>();
		populateMap.put(sq.next(),ques);		
		try
		{
			query = HelperMethodsDAO.getQuery(filename,queryKey);
			System.out.println("Check This== "+query);
			PreparedStatement stmt = null;
			stmt = conn.prepareStatement(query);
			stmt.setString(1, ques);			
		}
		catch(Exception e)
		{			
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::"+e);
			
		}
		try
		{
			rs = HelperMethodsDAO.executeQuery(conn, query, populateMap);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException();
			}
			else
			{
				rs.beforeFirst();
				while(rs.next()){
					InvestigationChatbotVO voobj1 = new InvestigationChatbotVO();
					String ques1=rs.getString(1);
					voobj1.setQuesCat(ques1);
					reqLst.add(voobj1);
				}

			}
		}
		catch(Exception e)
		{
			if(e.getClass()==HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException();	
			}
			else			 		
			 throw new HisDataAccessException("HisDataAccessException:: "+e);			 
		 }	
		return reqLst;
		}
	
}