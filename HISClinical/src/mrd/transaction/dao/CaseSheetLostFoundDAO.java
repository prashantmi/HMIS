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
import hisglobal.vo.CaseSheetLostFoundVO;
import hisglobal.vo.UserVO;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import mrd.MrdConfig;

public class CaseSheetLostFoundDAO extends DataAccessObject implements CaseSheetLostFoundDAOi
{
	public  CaseSheetLostFoundDAO(TransactionContext _tx) 
	{
		super(_tx);
	}
	
	
	public void create(CaseSheetLostFoundVO caseSheetLostFoundVO,UserVO userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_DAO;
		String queryKey = "";
		if( caseSheetLostFoundVO.getFoundDate()!=null &&  !caseSheetLostFoundVO.getFoundDate().equals(""))
			queryKey ="INSERT.HIPD_CASESHEET_LOSTFOUND_DTL";
		else
			queryKey ="INSERT.HIPD_CASESHEET_LOSTFOUND_DTL2";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		populateMAP.put(sq.next(), caseSheetLostFoundVO.getPatCrNo());
		populateMAP.put(sq.next(), caseSheetLostFoundVO.getPatAdmNo());
		populateMAP.put(sq.next(), caseSheetLostFoundVO.getCaseSheetId());
		populateMAP.put(sq.next(), userVO.getHospitalCode());
		populateMAP.put(sq.next(), caseSheetLostFoundVO.getLostDate());
		populateMAP.put(sq.next(), caseSheetLostFoundVO.getCaseSheetId());
		populateMAP.put(sq.next(), caseSheetLostFoundVO.getReportedBy());
		populateMAP.put(sq.next(), caseSheetLostFoundVO.getPatAdmNo());
		populateMAP.put(sq.next(), caseSheetLostFoundVO.getLastSeenDateTime());
		populateMAP.put(sq.next(), caseSheetLostFoundVO.getLostArea());
		populateMAP.put(sq.next(), caseSheetLostFoundVO.getLostType());
		populateMAP.put(sq.next(), caseSheetLostFoundVO.getLostRemarks());
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), caseSheetLostFoundVO.getDepartmentUnitCode());
		populateMAP.put(sq.next(), userVO.getSeatId());
		populateMAP.put(sq.next(), caseSheetLostFoundVO.getWardCode());
		populateMAP.put(sq.next(), caseSheetLostFoundVO.getRoomCode());
		populateMAP.put(sq.next(), userVO.getHospitalCode());
		if( caseSheetLostFoundVO.getFoundDate()!=null &&  !caseSheetLostFoundVO.getFoundDate().equals(""))
			populateMAP.put(sq.next(), caseSheetLostFoundVO.getFoundDate());
		populateMAP.put(sq.next(), caseSheetLostFoundVO.getFoundRemarks());
		populateMAP.put(sq.next(), caseSheetLostFoundVO.getFoundBy());
		populateMAP.put(sq.next(), userVO.getIpAddress());
		
		try
		{
			HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), query, populateMAP);
		
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("Application Execution Exception" + e);
		}
	}
	
	public CaseSheetLostFoundVO select(CaseSheetLostFoundVO caseSheetLostFoundVO,UserVO userVO)
	{
		ResultSet rs=null;
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_DAO;
		String queryKey = "SELECT.HIPD_CASESHEET_LOSTFOUND_DTL";
		
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		
		populateMAP.put(sq.next(), caseSheetLostFoundVO.getPatAdmNo());
		populateMAP.put(sq.next(), caseSheetLostFoundVO.getCaseSheetId());
		populateMAP.put(sq.next(), caseSheetLostFoundVO.getPatAdmNo());
		populateMAP.put(sq.next(), caseSheetLostFoundVO.getCaseSheetId());
		populateMAP.put(sq.next(), userVO.getHospitalCode());
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), userVO.getHospitalCode());
				
		try
		{
			rs=HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if(!rs.next()){
				throw new HisRecordNotFoundException("No Record Found");
			}
			else{
				rs.beforeFirst();
				HelperMethods.populateVOfrmRS(caseSheetLostFoundVO, rs);
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
		return caseSheetLostFoundVO;
	}
	
	
}
