package mrd.transaction.dao;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.TransactionContext;
import hisglobal.utility.Sequence;
import hisglobal.vo.CaseSheetDtlVO;
import hisglobal.vo.UserVO;
import java.util.HashMap;
import java.util.Map;

import mrd.MrdConfig;

public class CaseSheetDtlDAO extends DataAccessObject implements CaseSheetDtlDAOi
{
	public  CaseSheetDtlDAO(TransactionContext _tx) 
	{
		super(_tx);
	}
		
	public void create(CaseSheetDtlVO caseSheetDtlVO,UserVO userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_DAO;
		String queryKey = "INSERT.HIPD_CASESHEET_DTL";
		//String caseSheetId="";
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		
		//caseSheetId= caseSheetDtlVO.getPatAdmNo()+ Integer.parseInt(caseSheetDtlVO.getCaseSheetId().substring(11))+1;
		populateMAP.put(sq.next(), caseSheetDtlVO.getPatCrNo());
		populateMAP.put(sq.next(), caseSheetDtlVO.getCaseSheetStatus());
		//populateMAP.put(sq.next(), caseSheetDtlVO.getCaseSheetId());
		populateMAP.put(sq.next(), caseSheetDtlVO.getPatAdmNo());
		populateMAP.put(sq.next(), userVO.getHospitalCode());
		populateMAP.put(sq.next(), caseSheetDtlVO.getIsDuplicate());
		populateMAP.put(sq.next(), caseSheetDtlVO.getDepartmentUnitCode());
		populateMAP.put(sq.next(), caseSheetDtlVO.getPatAdmNo());
		populateMAP.put(sq.next(), caseSheetDtlVO.getWardCode());
		populateMAP.put(sq.next(), caseSheetDtlVO.getRoomCode());
		populateMAP.put(sq.next(), caseSheetDtlVO.getRemarks());
		populateMAP.put(sq.next(), caseSheetDtlVO.getCancelRemarks());
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), userVO.getSeatId());
		populateMAP.put(sq.next(), userVO.getHospitalCode());
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
	
	/* *********************************************************
	 * update the case sheet status on basis of case sheet Id
	 * *******************************************************/
	public void update(CaseSheetDtlVO caseSheetDtlVO,UserVO userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_DAO;
		String queryKey = "UPDATE.HIPD_CASESHEET_DTL";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		populateMAP.put(sq.next(), caseSheetDtlVO.getCaseSheetStatus());
		populateMAP.put(sq.next(), caseSheetDtlVO.getCaseSheetId());
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), userVO.getHospitalCode());
		

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
