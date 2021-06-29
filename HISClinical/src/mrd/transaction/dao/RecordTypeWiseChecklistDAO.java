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
import hisglobal.vo.RecordCheckListDtlVO;
import hisglobal.vo.RecordTypeCheckListMstVO;
import hisglobal.vo.UserVO;
import hisglobal.vo.ValueObject;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mrd.MrdConfig;

public class RecordTypeWiseChecklistDAO extends DataAccessObject implements RecordTypeWiseChecklistDAOi
{
	public  RecordTypeWiseChecklistDAO(TransactionContext _tx) 
	{
		super(_tx);
	}
	
	
	public void saveCaseSheetDispatchCheckListDetails(RecordTypeCheckListMstVO _RecordTypeCheckListMstVOs,UserVO userVO)
	{
		//ValueObject vo[] = null;
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_DAO;
		String queryKey = "SAVE_CASESHEET_DISPATCH_CHECKLIST_DETAILS";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		populateMAP.put(sq.next(), _RecordTypeCheckListMstVOs.getRecordId());
		populateMAP.put(sq.next(), MrdConfig.RECORD_TYPE_CASESHEET);
		populateMAP.put(sq.next(), _RecordTypeCheckListMstVOs.getChecklistId());
		populateMAP.put(sq.next(),_RecordTypeCheckListMstVOs.getCheckListBy());
		populateMAP.put(sq.next(), _RecordTypeCheckListMstVOs.getRecordId());
		populateMAP.put(sq.next(), MrdConfig.RECORD_TYPE_CASESHEET);
		populateMAP.put(sq.next(), _RecordTypeCheckListMstVOs.getChecklistId());
		populateMAP.put(sq.next(), _RecordTypeCheckListMstVOs.getCheckListBy());
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), userVO.getSeatId());
		populateMAP.put(sq.next(),_RecordTypeCheckListMstVOs.getRemarks());
	
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
	
	public List<RecordTypeCheckListMstVO> selectCheckListDtl(String recordId,UserVO userVO)
	{
		RecordTypeCheckListMstVO recordTypeCheckListVO=null;
		List<RecordTypeCheckListMstVO> recordTypeCheckListVOList=new ArrayList<RecordTypeCheckListMstVO>();
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_DAO;
		String queryKey = "SELECT.CHECKLIST_BY_RECORDID.HPMRT_RECORD_CHECKLIST_DTL";
		
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		
		populateMAP.put(sq.next(), userVO.getHospitalCode());
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), MrdConfig.RECORD_TYPE_CASESHEET);
		populateMAP.put(sq.next(), recordId);
			
		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if(rs.next()){
				rs.beforeFirst();
				while(rs.next()){
					recordTypeCheckListVO=new RecordTypeCheckListMstVO();
					HelperMethods.populateVOfrmRS(recordTypeCheckListVO, rs);
					recordTypeCheckListVOList.add(recordTypeCheckListVO);
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
		return recordTypeCheckListVOList;
	}
	
	
	public void create(RecordCheckListDtlVO recordCheckListDtlVO,UserVO userVO)
	{
		String query  = "";
        Map populateMAP =new HashMap();
        Sequence sq=new Sequence();
        String filename=MrdConfig.QUERY_FILE_FOR_MRD_DAO;
        String queryKey="INSERT.HPMRT_RECORD_CHECKLIST_DTL";
        
       
        try
        {
        	query =HelperMethodsDAO.getQuery(filename,queryKey);
        }
        catch(Exception e)
        {
        	throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);
        }
        
        try
        {
        	populateMAP.put(sq.next(), recordCheckListDtlVO.getRecordId());
        	populateMAP.put(sq.next(), recordCheckListDtlVO.getDispatchId());
        	populateMAP.put(sq.next(), recordCheckListDtlVO.getRecordType());
        	populateMAP.put(sq.next(), recordCheckListDtlVO.getCheckListId());
        	populateMAP.put(sq.next(), recordCheckListDtlVO.getCheckListBy());
        	populateMAP.put(sq.next(), recordCheckListDtlVO.getRemarks());
        	populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
        	populateMAP.put(sq.next(), userVO.getSeatId());
        	populateMAP.put(sq.next(), userVO.getHospitalCode());
        	
        }
        catch(Exception e)
        {
        	throw new HisApplicationExecutionException("RecordTypeWiseChecklistDAO.populateMAP::"+e);
        }
        try
        {
        	
        	HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(),query,populateMAP);
        }
        catch(Exception e)
        {
        	e.printStackTrace();
        	throw new HisDataAccessException("HisDataAccessException While ADDING");
        }
	}
	
	public RecordCheckListDtlVO[] getCheckListedRecord(String dispatchId,String recordType,UserVO userVO)
	{
		RecordCheckListDtlVO[] arrCheckListVO = null;
		ValueObject vo[] = null;
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_DAO;
		String queryKey = "GET_CHECKED_CHECKLIST_BY_DISPATCHID.HPMRT_RECORD_CHECKLIST_DTL";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		populateMAP.put(sq.next(), dispatchId );
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), userVO.getHospitalCode());
		//populateMAP.put(sq.next(), MrdConfig.CERTIFICATE_DISPATCH_RECORD_STATUS_SEND_HANDOVER);

		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				//throw new HisRecordNotFoundException("No Record Found");
				arrCheckListVO = null;
			}
			else
			{
				rs.beforeFirst();
	
				vo = HelperMethods.populateVOfrmRS(RecordCheckListDtlVO.class, rs);
				arrCheckListVO = new RecordCheckListDtlVO[vo.length];
				for (int i = 0; i < vo.length; i++)
				{
					arrCheckListVO[i] = (RecordCheckListDtlVO) vo[i];
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
		return arrCheckListVO;
	}
	
}


