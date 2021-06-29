package new_investigation.transactions.dao;


import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import new_investigation.InvestigationConfig;
import new_investigation.transactions.controller.fb.InvValueAuditFB;
import new_investigation.vo.InvValueAuditVO;
import new_investigation.vo.template.ResultEntryVO;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.JDBCTransactionContext;
import hisglobal.persistence.TransactionContext;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.Sequence;
import hisglobal.vo.UserVO;
import hisglobal.vo.ValueObject;

public class InvValueAuditDAO extends DataAccessObject {

	public InvValueAuditDAO(TransactionContext _tx) {
		super(_tx);
		// TODO Auto-generated constructor stub
	}
	
	public List LabComboForAudit(InvValueAuditFB invInvValueAuditfb,UserVO _UserVO)
	{
		String query="";
		Map populateMap= new HashMap();
		ResultSet rs=null;
		List parameterCombo=new ArrayList();
		String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
		String queryKey="SELECT_LAB_COMBO_FOR_AUDIT_PROCESS_HIVT_LABORATORY_MST";
		Sequence sq= new Sequence();
		Connection conn=super.getTransactionContext().getConnection();
		
		populateMap.put(sq.next(),_UserVO.getHospitalCode());
		populateMap.put(sq.next(), InvestigationConfig.MODULE_ID_INVESTIGATION);
		populateMap.put(sq.next(),_UserVO.getUserSeatId());
		populateMap.put(sq.next(),_UserVO.getHospitalCode());
		
		 
		
		try
		{
			query = HelperMethodsDAO.getQuery(filename,queryKey);
		}
		catch(Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::"+e);
		}
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
				parameterCombo=HelperMethodsDAO.getAlOfEntryObjects(rs);
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
		return parameterCombo;
	}
	
	
	public List AllTestComboForAudit(InvValueAuditVO vo,UserVO _UserVO)
	{
		String query="";
		Map populateMap= new HashMap();
		ResultSet rs=null;
		List parameterCombo=new ArrayList();
		String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
		String queryKey="SELECT_ALL_TEST_COMBO_FOR_AUDIT_PROCESS_HIVT_LABORATORY_TEST_MST";
		Sequence sq= new Sequence();
		Connection conn=super.getTransactionContext().getConnection();
		
		populateMap.put(sq.next(), InvestigationConfig.IS_VALID_ACTIVE);
		
		
		try
		{
			query = HelperMethodsDAO.getQuery(filename,queryKey);
		}
		catch(Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::"+e);
		}
		
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
				parameterCombo=HelperMethodsDAO.getAlOfEntryObjects(rs);
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
		return parameterCombo;
	}

	
	public List TestComboForAudit(InvValueAuditVO vo,UserVO _UserVO)
	{
		String query="";
		Map populateMap= new HashMap();
		ResultSet rs=null;
		List parameterCombo=new ArrayList();
		String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
		String queryKey="SELECT_TEST_COMBO_FOR_AUDIT_PROCESS_HIVT_LABORATORY_TEST_MST";
		Sequence sq= new Sequence();
		Connection conn=super.getTransactionContext().getConnection();
		
		populateMap.put(sq.next(), InvestigationConfig.IS_VALID_ACTIVE);
		populateMap.put(sq.next(),_UserVO.getHospitalCode());
		
		String conditionLab;
		if(vo.getLabCode().equals("%"))
			conditionLab=" and gnum_labcode like '%' ";
		else
			conditionLab=" and gnum_labcode="+vo.getLabCode();
		
		
		try
		{
			query = HelperMethodsDAO.getQuery(filename,queryKey);
		}
		catch(Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::"+e);
		}
		
		query+=conditionLab;
		
		
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
				parameterCombo=HelperMethodsDAO.getAlOfEntryObjects(rs);
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
		return parameterCombo;
	}
	
	
	public List<InvValueAuditVO> getlistauditprocess(InvValueAuditVO vo,UserVO _UserVO)
	{
		String query="";
		Map populateMap= new HashMap();
		ResultSet rs=null;
		List<InvValueAuditVO> parameterCombo=new ArrayList<InvValueAuditVO>();
		String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
		String queryKey="SELECT_LIST_DATA_FOR_AUDIT_PROCESS_HIVT_RESULT_LOG_DTL";
		Sequence sq= new Sequence();
		Connection conn=super.getTransactionContext().getConnection();
		ValueObject[] valueObjects = null;
		List<InvValueAuditVO> lstInvResultEntryVO = new ArrayList<InvValueAuditVO>();
		
		populateMap.put(sq.next(),_UserVO.getHospitalCode());
		populateMap.put(sq.next(),vo.getFromDate());
		populateMap.put(sq.next(),vo.getToDate());
		
		String conditionLab;
		String conditiontest;
		String conditionorder;
		
		if(vo.getLabCode().equals("%"))
			conditionLab=" and hivtnum_lab_code like '%' ";
		else
			conditionLab=" and hivtnum_lab_code="+vo.getLabCode();
		
		if(vo.getTestCode().equals("%"))
			conditiontest=" and hivtnum_test_code like '%' ";
		else
			conditiontest=" and hivtnum_test_code="+vo.getTestCode();
	
		
		conditionorder=" order by hivt_entry_date desc";
		
		try
		{
			query = HelperMethodsDAO.getQuery(filename,queryKey);
		}
		catch(Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::"+e);
		}
		
		query+=conditionLab;
		query+=conditiontest;
		query+=conditionorder;
		
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
				valueObjects = HelperMethods.populateVOfrmRS(InvValueAuditVO.class, rs);
				//dailyPatientVOs = new PatientDetailVO[valueObjects.length];
				for (int i = 0; i < valueObjects.length; i++)
				{
					//dailyPatientVOs[i] = (PatientDetailVO) valueObjects[i];
					lstInvResultEntryVO.add((InvValueAuditVO)valueObjects[i]);
					
					parameterCombo.add(lstInvResultEntryVO.get(i)); //add all other values
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
		return parameterCombo;
	}
	
	
	
	public List<InvValueAuditVO> showPatDetails(InvValueAuditVO vo,UserVO _UserVO,String dNo,String testCode,String labCode)
	{
		String query="";
		Map populateMap= new HashMap();
		ResultSet rs=null;
		List<InvValueAuditVO> parameterCombo=new ArrayList<InvValueAuditVO>();
		String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
		String queryKey="SELECT_LIST_DATA_SHOW_FOR_AUDIT_PROCESS_HIVT_RESULT_LOG_DTL";
		Sequence sq= new Sequence();
		Connection conn=super.getTransactionContext().getConnection();
		ValueObject[] valueObjects = null;
		List<InvValueAuditVO> lstInvResultEntryVO = new ArrayList<InvValueAuditVO>();
		String whereClause=" and hivtnum_req_dno in ("+dNo+") and hivtnum_lab_code in ("+labCode+") and hivtnum_test_code in ("+testCode+")";
	    String orderBy="order by hivtnum_req_dno desc, hrgnum_s_no desc ";
	
		populateMap.put(sq.next(),_UserVO.getHospitalCode());
		
	
		try
		{
			query = HelperMethodsDAO.getQuery(filename,queryKey);
		}
		catch(Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::"+e);
		}
		
		query+=whereClause;
		query+=orderBy;
		
				
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
				valueObjects = HelperMethods.populateVOfrmRS(InvValueAuditVO.class, rs);
				//dailyPatientVOs = new PatientDetailVO[valueObjects.length];
				for (int i = 0; i < valueObjects.length; i++)
				{
					//dailyPatientVOs[i] = (PatientDetailVO) valueObjects[i];
					lstInvResultEntryVO.add((InvValueAuditVO)valueObjects[i]);
					
					parameterCombo.add(lstInvResultEntryVO.get(i)); //add all other values
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
		return parameterCombo;
	}
	
	
	public String setComboValueNameForNewValue(String testCode, String paraCode, String paraEntry)
	{
		String query = "";
		ResultSet rs;
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
		String queryKey ="CHECK_COMBO_VALUE_AUDIT_NEW_VALUE.HIVT_TEST_PARAMETER_COMBO_MST";
		String record=null;
		try
		{
			int value= Integer.parseInt(paraEntry);
			paraEntry.valueOf(value);
		}
		catch(Exception e)
		{
			paraEntry="0";
		}

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			return record;
		}
		try
		{
			populateMAP.put(sq.next(), InvestigationConfig.IS_VALID_ACTIVE);       
			populateMAP.put(sq.next(), testCode);
			populateMAP.put(sq.next(),paraCode);
			populateMAP.put(sq.next(),paraEntry);

		}

		catch (Exception e)
		{
			return record;
		}
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);



			while (rs.next())
			{
				record=rs.getString(1);
			}
		}
		catch (Exception e)
		{
			return record;
		}
		return record;

	}

	public String setComboValueNameForPreValue(String testCode, String paraCode, String paraEntry)
	{
		String query = "";
		ResultSet rs;
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
		String queryKey ="CHECK_COMBO_VALUE_AUDIT_PRE_VALUE.HIVT_TEST_PARAMETER_COMBO_MST";
		String record=null;
		try
		{
			int value= Integer.parseInt(paraEntry);
			paraEntry.valueOf(value);
		}
		catch(Exception e)
		{
			paraEntry="0";
		}

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			return record;
		}
		try
		{
			populateMAP.put(sq.next(), InvestigationConfig.IS_VALID_ACTIVE);       
			populateMAP.put(sq.next(), testCode);
			populateMAP.put(sq.next(),paraCode);
			populateMAP.put(sq.next(),paraEntry);

		}

		catch (Exception e)
		{
			return record;
		}
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);



			while (rs.next())
			{
				record=rs.getString(1);
			}
		}
		catch (Exception e)
		{
			return record;
		}
		return record;

	}

}
