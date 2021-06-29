package mrd.transaction.dao;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.TransactionContext;
import hisglobal.utility.Sequence;
import hisglobal.vo.CaseSheetDispatchVO;
import hisglobal.vo.RecordTypeCheckListMstVO;
import hisglobal.vo.RecordTypeWiseEnclosureMstVO;
import hisglobal.vo.UserVO;

import java.util.HashMap;
import java.util.Map;

import mrd.MrdConfig;

public class CaseSheetDispatchDAO extends DataAccessObject implements CaseSheetDispatchDAOi
{
	public  CaseSheetDispatchDAO(TransactionContext _tx) 
	{
		super(_tx);
	}
	
	
	public void saveCaseSheetDispatch(CaseSheetDispatchVO _CaseSheetDispatchVO,RecordTypeWiseEnclosureMstVO[] _RecordTypeWiseEnclosureMstVOs,RecordTypeCheckListMstVO[] _RecordTypeCheckListMstVOs,UserVO userVO)
	{
		//ValueObject vo[] = null;
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_DAO;
		String queryKey = "SAVE_CASESHEET_DISPATCH_DETAILS";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		populateMAP.put(sq.next(), _CaseSheetDispatchVO.getPatAdmNo());
		populateMAP.put(sq.next(), MrdConfig.RECORD_TYPE_CASESHEET);
		populateMAP.put(sq.next(), _CaseSheetDispatchVO.getPatAdmNo());
		populateMAP.put(sq.next(), MrdConfig.RECORD_TYPE_CASESHEET);
		populateMAP.put(sq.next(), _CaseSheetDispatchVO.getDepartmentUnitCode());
		populateMAP.put(sq.next(), _CaseSheetDispatchVO.getWardCode());
		populateMAP.put(sq.next(), userVO.getSeatId());
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), userVO.getSeatId());
		populateMAP.put(sq.next(), _CaseSheetDispatchVO.getEnclosurePages());
		populateMAP.put(sq.next(), userVO.getHospitalCode());
		populateMAP.put(sq.next(), userVO.getIpAddress());
		populateMAP.put(sq.next(), _CaseSheetDispatchVO.getPatAdmNo());
		populateMAP.put(sq.next(), _CaseSheetDispatchVO.getRecordStatus());
		populateMAP.put(sq.next(), userVO.getUserEmpID());
		populateMAP.put(sq.next(), _CaseSheetDispatchVO.getHandoverTo());
		populateMAP.put(sq.next(), _CaseSheetDispatchVO.getDepartmentCode());

		try
		{
			HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), query, populateMAP);
		
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("Application Execution Exception" + e);
		}
	}
	
	public void update(CaseSheetDispatchVO _CaseSheetDispatchVO,UserVO userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_ESSENTIALDAO;
		String queryKey = "UPDATE.HPMRT_RECORD_DISPATCH_DTL";
		
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		
		populateMAP.put(sq.next(), _CaseSheetDispatchVO.getRecordStatus());
		populateMAP.put(sq.next(), _CaseSheetDispatchVO.getReturnReason());
		populateMAP.put(sq.next(), _CaseSheetDispatchVO.getRecordId());
		populateMAP.put(sq.next(), MrdConfig.RECORD_TYPE_CASESHEET);
//		populateMAP.put(sq.next(), _CaseSheetDispatchVO.getSlNo());
		populateMAP.put(sq.next(), userVO.getHospitalCode());
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
}
