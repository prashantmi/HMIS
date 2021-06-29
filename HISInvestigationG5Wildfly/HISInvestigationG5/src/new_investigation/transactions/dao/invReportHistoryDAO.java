package new_investigation.transactions.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import new_investigation.InvestigationConfig;
import new_investigation.transactions.controller.fb.invReportHistoryFB;
import new_investigation.vo.invReportHistoryVO;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.TransactionContext;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.Sequence;
import hisglobal.vo.UserVO;
import hisglobal.vo.ValueObject;

public class invReportHistoryDAO extends DataAccessObject {

	public invReportHistoryDAO(TransactionContext _tx) {
		super(_tx);
		// TODO Auto-generated constructor stub
	}

	
	
	public List<invReportHistoryVO> fetchActiveReportData(invReportHistoryFB fb,UserVO _UserVO)
	{
		String query="";
		Map populateMap= new HashMap();
		ResultSet rs=null;
		List<invReportHistoryVO> patlst=new ArrayList<invReportHistoryVO>();
		String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
		String queryKey="SELECT_LIST_DATA_SHOW_FOR_ACTIVE_REPORT_HISTORY1";
		Sequence sq= new Sequence();
		Connection conn=super.getTransactionContext().getConnection();
		ValueObject[] valueObjects = null;
		List<invReportHistoryVO> lstInvResultEntryVO = new ArrayList<invReportHistoryVO>();
		
	  
		populateMap.put(sq.next(),fb.getFromDate());
		populateMap.put(sq.next(),fb.getToDate());
		//populateMap.put(sq.next(),InvestigationConfig.IS_VALID_ACTIVE);
		/*populateMap.put(sq.next(),_UserVO.getHospitalCode());*/
		//populateMap.put(sq.next(),reqNos);
		
	
		try
		{
			query = HelperMethodsDAO.getQuery(filename,queryKey);
		}
		catch(Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::"+e);
		}
		
		
		System.out.println(query);		
		try
		{
			rs =  HelperMethodsDAO.executeQuery(conn, query, populateMap);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException();
			}
			else
			{
				rs.beforeFirst();
				valueObjects = HelperMethods.populateVOfrmRS(invReportHistoryVO.class, rs);
				//dailyPatientVOs = new PatientDetailVO[valueObjects.length];
				for (int i = 0; i < valueObjects.length; i++)
				{
					//dailyPatientVOs[i] = (PatientDetailVO) valueObjects[i];
					lstInvResultEntryVO.add((invReportHistoryVO)valueObjects[i]);
					
					patlst.add(lstInvResultEntryVO.get(i)); //add all other values
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
		return patlst;
	}
	
	
	public List<invReportHistoryVO> fetchActiveReportData1(invReportHistoryFB fb,UserVO _UserVO)
	{
		String query="";
		Map populateMap= new HashMap();
		ResultSet rs=null;
		List<invReportHistoryVO> patlst=new ArrayList<invReportHistoryVO>();
		String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
		String queryKey="SELECT_LIST_DATA_SHOW_FOR_ACTIVE_REPORT_HISTORY1";
		Sequence sq= new Sequence();
		Connection conn=super.getTransactionContext().getConnection();
		ValueObject[] valueObjects = null;
		List<invReportHistoryVO> lstInvResultEntryVO = new ArrayList<invReportHistoryVO>();
		
		populateMap.put(sq.next(),fb.getFromDate());
		populateMap.put(sq.next(),fb.getToDate());
		
		//populateMap.put(sq.next(),InvestigationConfig.IS_VALID_ACTIVE);
		/*populateMap.put(sq.next(),_UserVO.getHospitalCode());*/
		//populateMap.put(sq.next(),reqNos);
		
	
		try
		{
			query = HelperMethodsDAO.getQuery(filename,queryKey);
		}
		catch(Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::"+e);
		}
		
		
		System.out.println(query);		
		try
		{
			rs =  HelperMethodsDAO.executeQuery(conn, query, populateMap);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException();
			}
			else
			{
				rs.beforeFirst();
				valueObjects = HelperMethods.populateVOfrmRS(invReportHistoryVO.class, rs);
				//dailyPatientVOs = new PatientDetailVO[valueObjects.length];
				for (int i = 0; i < valueObjects.length; i++)
				{
					//dailyPatientVOs[i] = (PatientDetailVO) valueObjects[i];
					lstInvResultEntryVO.add((invReportHistoryVO)valueObjects[i]);
					
					patlst.add(lstInvResultEntryVO.get(i)); //add all other values
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
		return patlst;
	}
	
	
	public List<invReportHistoryVO> fetchActiveReportDataall(invReportHistoryFB fb,UserVO _UserVO)
	{
		String query="";
		Map populateMap= new HashMap();
		ResultSet rs=null;
		List<invReportHistoryVO> patlst=new ArrayList<invReportHistoryVO>();
		String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
		String queryKey="SELECT_LIST_DATA_SHOW_FOR_ACTIVE_REPORT_HISTORY_ALL";
		Sequence sq= new Sequence();
		Connection conn=super.getTransactionContext().getConnection();
		ValueObject[] valueObjects = null;
		List<invReportHistoryVO> lstInvResultEntryVO = new ArrayList<invReportHistoryVO>();
		
		populateMap.put(sq.next(),fb.getFromDate());
		populateMap.put(sq.next(),fb.getToDate());
		//populateMap.put(sq.next(),InvestigationConfig.IS_VALID_ACTIVE);
		/*populateMap.put(sq.next(),_UserVO.getHospitalCode());*/
		//populateMap.put(sq.next(),reqNos);
		
	
		try
		{
			query = HelperMethodsDAO.getQuery(filename,queryKey);
		}
		catch(Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::"+e);
		}
		
		
		System.out.println(query);		
		try
		{
			rs =  HelperMethodsDAO.executeQuery(conn, query, populateMap);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException();
			}
			else
			{
				rs.beforeFirst();
				valueObjects = HelperMethods.populateVOfrmRS(invReportHistoryVO.class, rs);
				//dailyPatientVOs = new PatientDetailVO[valueObjects.length];
				for (int i = 0; i < valueObjects.length; i++)
				{
					//dailyPatientVOs[i] = (PatientDetailVO) valueObjects[i];
					lstInvResultEntryVO.add((invReportHistoryVO)valueObjects[i]);
					
					patlst.add(lstInvResultEntryVO.get(i)); //add all other values
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
		return patlst;
	}


	public List<invReportHistoryVO> fetchActiveReportDataallInactive(invReportHistoryFB fb,UserVO _UserVO)
	{
		String query="";
		Map populateMap= new HashMap();
		ResultSet rs=null;
		List<invReportHistoryVO> patlst=new ArrayList<invReportHistoryVO>();
		String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
		String queryKey="SELECT_LIST_DATA_SHOW_FOR_ACTIVE_REPORT_HISTORY_ALL_INACTIVE";
		Sequence sq= new Sequence();
		Connection conn=super.getTransactionContext().getConnection();
		ValueObject[] valueObjects = null;
		List<invReportHistoryVO> lstInvResultEntryVO = new ArrayList<invReportHistoryVO>();
		
		populateMap.put(sq.next(),fb.getFromDate());
		populateMap.put(sq.next(),fb.getToDate());
		//populateMap.put(sq.next(),InvestigationConfig.IS_VALID_ACTIVE);
		/*populateMap.put(sq.next(),_UserVO.getHospitalCode());*/
		//populateMap.put(sq.next(),reqNos);
		
	
		try
		{
			query = HelperMethodsDAO.getQuery(filename,queryKey);
		}
		catch(Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::"+e);
		}
		
		
		System.out.println(query);		
		try
		{
			rs =  HelperMethodsDAO.executeQuery(conn, query, populateMap);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException();
			}
			else
			{
				rs.beforeFirst();
				valueObjects = HelperMethods.populateVOfrmRS(invReportHistoryVO.class, rs);
				//dailyPatientVOs = new PatientDetailVO[valueObjects.length];
				for (int i = 0; i < valueObjects.length; i++)
				{
					//dailyPatientVOs[i] = (PatientDetailVO) valueObjects[i];
					lstInvResultEntryVO.add((invReportHistoryVO)valueObjects[i]);
					
					patlst.add(lstInvResultEntryVO.get(i)); //add all other values
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
		return patlst;
	}

	
	
	public void reportresume(invReportHistoryFB voResultEntry, UserVO _UserVO) {

		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
		String queryKey ="INSERT.hivt_report_start.DATA";

		
		try {
			query = HelperMethodsDAO.getQuery(filename, queryKey);

		} catch (Exception e) {
			throw new HisApplicationExecutionException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"
							+ e);
		}

		try {
			 
			 
			
			 
			
		 
           // populateMAP.put(sq.next(), "");
			
			
	    	
	        	
		} catch (Exception e) {
			throw new HisApplicationExecutionException(
					"IcdGroupMasterDAO.populateMAP::" + e);
		}
		try {

			HelperMethodsDAO.excecuteUpdate(super.getTransactionContext()
					.getConnection(), query, populateMAP);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new HisDataAccessException("Exception While insertion in  HIVT_JOBWORKORDER_DATA Table");
		}

	}
	
}



