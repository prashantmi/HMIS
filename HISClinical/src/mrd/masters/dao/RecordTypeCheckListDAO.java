package mrd.masters.dao;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.TransactionContext;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.Sequence;
import hisglobal.vo.RecordTypeCheckListMstVO;
import hisglobal.vo.UserVO;
import hisglobal.vo.ValueObject;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mrd.MrdConfig;

public class RecordTypeCheckListDAO extends DataAccessObject implements RecordTypeCheckListDAOi
{
	public RecordTypeCheckListDAO(TransactionContext _tx)
	{
		super(_tx);
	}
	
	public RecordTypeCheckListMstVO[] getCheckListForMedNFitCertificate(String checkListMode,String recordType,UserVO userVO)
	{
		RecordTypeCheckListMstVO[] arrChecklistDtlVO = null;
		ValueObject vo[] = null;
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_DAO;
		String queryKey = "CHECKLIST_FOR_RECORD_TYPE_WISE.HPMRT_RECORD_TYPE_CHECKLIST";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE); // Added by Pawan Kumar B N on 05-Jul-2012
		populateMAP.put(sq.next(), recordType);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), userVO.getHospitalCode());
		populateMAP.put(sq.next(), checkListMode);
		
		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				//throw new HisRecordNotFoundException("No record found");
			}
			rs.beforeFirst();

			vo = HelperMethods.populateVOfrmRS(RecordTypeCheckListMstVO.class, rs);
			arrChecklistDtlVO = new RecordTypeCheckListMstVO[vo.length];
			for (int i = 0; i < vo.length; i++)
			{
				arrChecklistDtlVO[i] = (RecordTypeCheckListMstVO) vo[i];
			}
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else 
				throw new HisDataAccessException("Application Execution Exception" + e);
		}

		return arrChecklistDtlVO;
	}
	
	public List getAllAddedCheckLstForRecordType(String recordType, String checkListMode,UserVO _userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_MASTER_DAO;
		String queryKey = "SELECT.ADDED_CHECK_LIST.HPMRT_RECORD_TYPE_CHECKLIST";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		
		populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE); // Added by Pawan Kumar B N on 03-Jul-2012
		populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE); // Added by Pawan Kumar B N on 03-Jul-2012s
		populateMAP.put(sq.next(), recordType);
		populateMAP.put(sq.next(), checkListMode);
		populateMAP.put(sq.next(), _userVO.getHospitalCode());
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);

		List recordTypeCheckListMstVOList = new ArrayList();
		ValueObject vo[] = null;
		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (rs.next())
			{
				rs.beforeFirst();
	
				vo = HelperMethods.populateVOfrmRS(RecordTypeCheckListMstVO.class, rs);			
				for (ValueObject v : vo)
					recordTypeCheckListMstVOList.add((RecordTypeCheckListMstVO)v);
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
		return recordTypeCheckListMstVOList;
	}
	
	public List getCheckListNotMapped(RecordTypeCheckListMstVO recordTypeCheckListVO,UserVO userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_MASTER_DAO;
		String queryKey = "GET_CHECLIST_NOT_MAPPED.HPMRT_RECORD_TYPE_CHECKLIST";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		Sequence sq = new Sequence();
		try
		{
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE); // Added by Pawan Kumar B N on 03-Jul-2012
			populateMAP.put(sq.next(), userVO.getHospitalCode());
			//populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), recordTypeCheckListVO.getRecordTypeId());
			populateMAP.put(sq.next(), recordTypeCheckListVO.getCheckListMode());
						
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("RecordTypeWiseEnclosureMstDAO.populateMAP::" + e);
		}

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			/*
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("");
			}
			*/

		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}

		List alRecord = new ArrayList();
		try
		{
			alRecord = HelperMethodsDAO.getAlOfEntryObjects(rs);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("RecordTypeWiseEnclosureMstDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}
	
		return alRecord;
	}
	
public void creat(RecordTypeCheckListMstVO recordTypeCheckListVO ,UserVO userVO) {
		
		//ResultSet rs;
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_MASTER_DAO;
		String queryKey = "INSERT.HPMRT_RECORD_TYPE_CHECKLIST";
		
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		
		
		try 
		{
			populateMAP.put(sq.next(), recordTypeCheckListVO.getRecordTypeId());
			populateMAP.put(sq.next(), recordTypeCheckListVO.getChecklistId());
			populateMAP.put(sq.next(), recordTypeCheckListVO.getCheckListMode());
			populateMAP.put(sq.next(), userVO.getHospitalCode());
			
			populateMAP.put(sq.next(), recordTypeCheckListVO.getRecordTypeId());//for sereialNo
			populateMAP.put(sq.next(), recordTypeCheckListVO.getChecklistId());
			populateMAP.put(sq.next(), recordTypeCheckListVO.getCheckListMode());
			populateMAP.put(sq.next(), userVO.getHospitalCode()); 
			
			populateMAP.put(sq.next(), recordTypeCheckListVO.getIsCompulsory());
			populateMAP.put(sq.next(), recordTypeCheckListVO.getDisplayOrder());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), userVO.getSeatId());
			populateMAP.put(sq.next(), recordTypeCheckListVO.getLastModifyDate());
			populateMAP.put(sq.next(), recordTypeCheckListVO.getLastModifySeatId());
			//entryDate
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("ReqPurposeMstDAO.populateMAP::" + e);
		}
		try
		{
			HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), query, populateMAP);
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		
		
	}

public void update(RecordTypeCheckListMstVO recordTypeCheckListVO ,UserVO userVO) {
	
	//ResultSet rs;
	String query = "";
	Map populateMAP = new HashMap();
	Sequence sq = new Sequence();
	String filename = MrdConfig.QUERY_FILE_FOR_MRD_MASTER_DAO;
	String queryKey = "UPDATE.HPMRT_RECORD_TYPE_CHECKLIST";
	
	try
	{
		query = HelperMethodsDAO.getQuery(filename, queryKey);
	}
	catch (Exception e)
	{
		throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
	}
	
	
	try 
	{
		populateMAP.put(sq.next(), Config.IS_VALID_DELETED);
		populateMAP.put(sq.next(), userVO.getSeatId());
		populateMAP.put(sq.next(), recordTypeCheckListVO.getRecordTypeId());
		populateMAP.put(sq.next(), recordTypeCheckListVO.getCheckListMode());
		populateMAP.put(sq.next(), userVO.getHospitalCode());
		
		
		
	}
	catch (Exception e)
	{
		throw new HisApplicationExecutionException("ReqPurposeMstDAO.populateMAP::" + e);
	}
	try
	{
		HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), query, populateMAP);
		
	}
	catch (Exception e)
	{
		e.printStackTrace();
		throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
	}
}



}
