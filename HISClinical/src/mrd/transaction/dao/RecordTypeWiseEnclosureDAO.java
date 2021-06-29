package mrd.transaction.dao;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.TransactionContext;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.Sequence;
import hisglobal.vo.CaseSheetDispatchVO;
import hisglobal.vo.RecordTypeCheckListMstVO;
import hisglobal.vo.RecordTypeWiseEnclosureMstVO;
import hisglobal.vo.UserVO;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mrd.MrdConfig;

public class RecordTypeWiseEnclosureDAO extends DataAccessObject implements RecordTypeWiseEnclosureDAOi
{
	public  RecordTypeWiseEnclosureDAO(TransactionContext _tx) 
	{
		super(_tx);
	}
	
	
	public void saveCaseSheetDispatchEnclosureDetails(CaseSheetDispatchVO _CaseSheetDispatchVO,RecordTypeWiseEnclosureMstVO _RecordTypeWiseEnclosureMstVOs,RecordTypeCheckListMstVO[] _RecordTypeCheckListMstVOs,UserVO userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_DAO;
		String queryKey = "SAVE_CASESHEET_DISPATCH_ENCLOSURE_DETAILS";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		populateMAP.put(sq.next(), _CaseSheetDispatchVO.getRecordId());
		populateMAP.put(sq.next(), MrdConfig.RECORD_TYPE_CASESHEET);
		populateMAP.put(sq.next(), _CaseSheetDispatchVO.getSelectedEnclosureId());
		populateMAP.put(sq.next(), _CaseSheetDispatchVO.getRecordId());
		populateMAP.put(sq.next(), MrdConfig.RECORD_TYPE_CASESHEET);
		populateMAP.put(sq.next(), _CaseSheetDispatchVO.getSelectedEnclosureId());
		populateMAP.put(sq.next(), _CaseSheetDispatchVO.getPages());
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), userVO.getSeatId());
	

		try
		{
			HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("Application Execution Exception" + e);
		}
	}
	
	public void create(RecordTypeWiseEnclosureMstVO enclosureMstVO,UserVO userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_DAO;
		String queryKey = "INSERT.HPMRT_RECORD_ENCLOSURE_SUMMARY";
		
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		
		populateMAP.put(sq.next(), enclosureMstVO.getDispatchId());
		populateMAP.put(sq.next(), enclosureMstVO.getRecordId());
		populateMAP.put(sq.next(), enclosureMstVO.getRecordTypeId());
		populateMAP.put(sq.next(), enclosureMstVO.getEnclosureId());
		populateMAP.put(sq.next(),  enclosureMstVO.getPages());
		populateMAP.put(sq.next(),  enclosureMstVO.getVerifiedByPeon());
		populateMAP.put(sq.next(),  enclosureMstVO.getVerifiedByMrd());
		populateMAP.put(sq.next(),  enclosureMstVO.getRemarks());
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), userVO.getSeatId());
		
		try
		{
			HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), query, populateMAP);
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("Application Execution Exception" + e);
		}
	}
	
	public void update(RecordTypeWiseEnclosureMstVO _RecordTypeWiseEnclosureMstVOs,UserVO userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_ESSENTIALDAO;
		String queryKey = "UPDATE.HPMRT_RECORD_ENCLOSURE_SUMMARY";
		
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		
		populateMAP.put(sq.next(), _RecordTypeWiseEnclosureMstVOs.getVerifiedByMrd());
		populateMAP.put(sq.next(), _RecordTypeWiseEnclosureMstVOs.getRemarks());
		populateMAP.put(sq.next(), _RecordTypeWiseEnclosureMstVOs.getRecordId());
		populateMAP.put(sq.next(), MrdConfig.RECORD_TYPE_CASESHEET);
		populateMAP.put(sq.next(), _RecordTypeWiseEnclosureMstVOs.getEnclosureId());
		populateMAP.put(sq.next(), _RecordTypeWiseEnclosureMstVOs.getSlNo());
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		
		try
		{
			HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), query, populateMAP);
			
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("Application Execution Exception" + e);
		}
	}
	
	public void updateVerifyByPeon(RecordTypeWiseEnclosureMstVO _RecordTypeWiseEnclosureMstVOs,UserVO userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_DAO;
		String queryKey = "UPDATE.VERIFY_BY_PEON.HPMRT_RECORD_ENCLOSURE_SUMMARY";
		
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		
		populateMAP.put(sq.next(), _RecordTypeWiseEnclosureMstVOs.getVerifiedByPeon());
		populateMAP.put(sq.next(), _RecordTypeWiseEnclosureMstVOs.getDispatchId());
		populateMAP.put(sq.next(), _RecordTypeWiseEnclosureMstVOs.getEnclosureId());
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		
		try
		{
			HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), query, populateMAP);
			
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("Application Execution Exception" + e);
		}
	}
	
	public List<RecordTypeWiseEnclosureMstVO> getEnclosureDetailByDispatchId(String dispatchId,String recordType,UserVO userVO)
	{
		List<RecordTypeWiseEnclosureMstVO> lstEnclosureVO=new ArrayList<RecordTypeWiseEnclosureMstVO>();
		RecordTypeWiseEnclosureMstVO enclosureVO;
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_DAO;
		String queryKey = "GET_ALL_ACCEPTED_ENCLOSURE_BY_DISPATCHID.HPMRT_RECORD_ENCLOSURE_SUMMARY";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		
		populateMAP.put(sq.next(), userVO.getHospitalCode());
		populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE); // Added by Pawan Kumar B N on 05-Jul-2012
		populateMAP.put(sq.next(), dispatchId);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), recordType);
		
		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (rs.next())
			{
				rs.beforeFirst();
				while(rs.next())
				{
					enclosureVO=new RecordTypeWiseEnclosureMstVO();
					HelperMethods.populateVOfrmRS(enclosureVO, rs);
					lstEnclosureVO.add(enclosureVO);
				}
			}
		}	
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("Application Execution Exception" + e);
		}
		return lstEnclosureVO;
	}
	
	public void updateEnclosurePagesInMrd(RecordTypeWiseEnclosureMstVO recordTypeWiseEnclosureMstVOs,UserVO userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_DAO;
		String queryKey = "UPDATE_PAGES_IN_MRD.HPMRT_RECORD_ENCLOSURE_SUMMARY";
		
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		
		populateMAP.put(sq.next(), recordTypeWiseEnclosureMstVOs.getVerifiedByMrd());
		populateMAP.put(sq.next(), recordTypeWiseEnclosureMstVOs.getDispatchId());
		populateMAP.put(sq.next(), recordTypeWiseEnclosureMstVOs.getRecordTypeId());
		populateMAP.put(sq.next(), recordTypeWiseEnclosureMstVOs.getEnclosureId());
		
		
		try
		{
			HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), query, populateMAP);
			
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("Application Execution Exception" + e);
		}
	}
	
}
