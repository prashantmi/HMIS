package new_investigation.transactions.dao;

import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.TransactionContext;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import new_investigation.InvestigationConfig;
import new_investigation.transactions.controller.fb.InvValueAuditFB;
import new_investigation.transactions.controller.fb.invAntibioticProcessFB;
import new_investigation.vo.InvValueAuditVO;
import new_investigation.vo.invAntibioticProcessVO;
import new_investigation.vo.template.ResultEntryVO;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.JDBCTransactionContext;
import hisglobal.persistence.TransactionContext;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.Sequence;
import hisglobal.vo.UserVO;
import hisglobal.vo.ValueObject;
public class invAntibioticProcessDAO extends DataAccessObject {

	public invAntibioticProcessDAO(TransactionContext _tx) {
		super(_tx);
		// TODO Auto-generated constructor stub
	}
	
	
	public List LabComboForAudit(invAntibioticProcessFB invinvAntibioticProcessFB,UserVO _UserVO)
	{
		String query="";
		Map populateMap= new HashMap();
		ResultSet rs=null;
		List parameterCombo=new ArrayList();
		String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
		String queryKey="COMBO.ORGANISM_NAME.HIVT_ORGANISM_MST";
		Sequence sq= new Sequence();
		Connection conn=super.getTransactionContext().getConnection();
		
		
		populateMap.put(sq.next(), Config.IS_VALID_ACTIVE);
		
		 
		
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
	
	
	
	public List<invAntibioticProcessVO> getAntibioticName(invAntibioticProcessVO vo,UserVO _UserVO)
	{
		String query="";
		Map populateMap= new HashMap();
		ResultSet rs=null;
		List<invAntibioticProcessVO> parameterCombo=new ArrayList<invAntibioticProcessVO>();
		String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
		String queryKey="COMBO.ANTIBITIC_NAME.HIVT_ORGANISM_ANTIBIOTIC_MAPPING_MST";
		Sequence sq= new Sequence();
		Connection conn=super.getTransactionContext().getConnection();
		ValueObject[] valueObjects = null;
		List<invAntibioticProcessVO> lstInvResultEntryVO = new ArrayList<invAntibioticProcessVO>();
		

		populateMap.put(sq.next(),_UserVO.getHospitalCode());
		populateMap.put(sq.next(), vo.getOrganismCode());
		populateMap.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMap.put(sq.next(),_UserVO.getHospitalCode());
		populateMap.put(sq.next(), vo.getOrganismCode());
		
		
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
			//	throw new HisRecordNotFoundException();
			}
			else
			{
				rs.beforeFirst();
				valueObjects = HelperMethods.populateVOfrmRS(invAntibioticProcessVO.class, rs);
				//dailyPatientVOs = new PatientDetailVO[valueObjects.length];
				for (int i = 0; i < valueObjects.length; i++)
				{
					//dailyPatientVOs[i] = (PatientDetailVO) valueObjects[i];
					lstInvResultEntryVO.add((invAntibioticProcessVO)valueObjects[i]);
					
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
	
	
	public String getxml(invAntibioticProcessVO labMasterVO,UserVO _UserVO)
	{
		String query = "";
		ResultSet rs;
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
		String queryKey="GET_XML";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
	
		String record=null;
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
			if (e.getClass() == HisRecordNotFoundException.class) throw new HisRecordNotFoundException(e.getMessage());
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		return record;
	}
	
	
	
	public List<invAntibioticProcessVO> getAntibioticNamemapped(invAntibioticProcessVO vo,UserVO _UserVO)
	{
		String query="";
		Map populateMap= new HashMap();
		ResultSet rs=null;
		List<invAntibioticProcessVO> parameterCombo=new ArrayList<invAntibioticProcessVO>();
		String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
		String queryKey="COMBO.ANTIBITIC_NAME.HIVT_ORGANISM_ANTIBIOTIC_MAPPING_MST_PREFERRED_LIST";
		Sequence sq= new Sequence();
		Connection conn=super.getTransactionContext().getConnection();
		ValueObject[] valueObjects = null;
		List<invAntibioticProcessVO> lstInvResultEntryVO = new ArrayList<invAntibioticProcessVO>();
		

		populateMap.put(sq.next(),_UserVO.getHospitalCode());
		populateMap.put(sq.next(), vo.getOrganismCode());
		populateMap.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMap.put(sq.next(),_UserVO.getHospitalCode());
		populateMap.put(sq.next(), vo.getOrganismCode());
		
		
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
				//throw new HisRecordNotFoundException();
			}
			else
			{
				rs.beforeFirst();
				valueObjects = HelperMethods.populateVOfrmRS(invAntibioticProcessVO.class, rs);
				//dailyPatientVOs = new PatientDetailVO[valueObjects.length];
				for (int i = 0; i < valueObjects.length; i++)
				{
					//dailyPatientVOs[i] = (PatientDetailVO) valueObjects[i];
					lstInvResultEntryVO.add((invAntibioticProcessVO)valueObjects[i]);
					
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
	

	
	public List<invAntibioticProcessVO> getAntibioticNamepreferred(invAntibioticProcessVO vo,UserVO _UserVO)
	{
		String query="";
		Map populateMap= new HashMap();
		ResultSet rs=null;
		List<invAntibioticProcessVO> parameterCombo=new ArrayList<invAntibioticProcessVO>();
		String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
		String queryKey="COMBO.ANTIBITIC_NAME.HIVT_ORGANISM_ANTIBIOTIC_MAPPING_MST_PREFERRED_LIST";
		Sequence sq= new Sequence();
		Connection conn=super.getTransactionContext().getConnection();
		ValueObject[] valueObjects = null;
		List<invAntibioticProcessVO> lstInvResultEntryVO = new ArrayList<invAntibioticProcessVO>();
		

		populateMap.put(sq.next(),_UserVO.getHospitalCode());
		populateMap.put(sq.next(), vo.getOrganismCode());
		populateMap.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMap.put(sq.next(),_UserVO.getHospitalCode());
		populateMap.put(sq.next(), vo.getOrganismCode());
		
		
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
				//throw new HisRecordNotFoundException();
			}
			else
			{
				rs.beforeFirst();
				valueObjects = HelperMethods.populateVOfrmRS(invAntibioticProcessVO.class, rs);
				//dailyPatientVOs = new PatientDetailVO[valueObjects.length];
				for (int i = 0; i < valueObjects.length; i++)
				{
					//dailyPatientVOs[i] = (PatientDetailVO) valueObjects[i];
					lstInvResultEntryVO.add((invAntibioticProcessVO)valueObjects[i]);
					
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
	
	
}
