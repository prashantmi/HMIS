package mrd.transaction.dao;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.TransactionContext;
import hisglobal.utility.Sequence;
import hisglobal.vo.DischargePrintingVO;
import hisglobal.vo.UserVO;

import java.util.HashMap;
import java.util.Map;

import mrd.MrdConfig;

public class DischargePrintDAO extends DataAccessObject implements DischargePrintDAOi
{
	public  DischargePrintDAO(TransactionContext _tx) 
	{
		super(_tx);
	}
	
	
	public void save(DischargePrintingVO dischargePrintingVO,UserVO userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_ESSENTIALDAO;
		String queryKey = "INSERT.HIPD_DISCHARGE_PRINT_DTL";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		populateMAP.put(sq.next(), dischargePrintingVO.getPatAdmNo());
		populateMAP.put(sq.next(), dischargePrintingVO.getPatAdmNo());
		populateMAP.put(sq.next(), userVO.getHospitalCode());
		populateMAP.put(sq.next(), dischargePrintingVO.getPatCrNo());
		populateMAP.put(sq.next(), dischargePrintingVO.getIsDuplicate());
		populateMAP.put(sq.next(), dischargePrintingVO.getIsReceiptTaken());
		populateMAP.put(sq.next(), dischargePrintingVO.getProfileId());
		//populateMAP.put(sq.next(), dischargePrintingVO.getHandOverDate());
		populateMAP.put(sq.next(), dischargePrintingVO.getHandOverTo());
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), dischargePrintingVO.getDepartmentUnitCode());
		populateMAP.put(sq.next(), dischargePrintingVO.getRelativeName());
		populateMAP.put(sq.next(), dischargePrintingVO.getRelativeAddress());
		populateMAP.put(sq.next(), userVO.getSeatId());
		populateMAP.put(sq.next(), dischargePrintingVO.getWardCode());
		populateMAP.put(sq.next(), dischargePrintingVO.getRelativeCode());
		populateMAP.put(sq.next(), dischargePrintingVO.getOfficerName());
		populateMAP.put(sq.next(), dischargePrintingVO.getOfficerDesign());
		populateMAP.put(sq.next(), userVO.getHospitalCode());
		populateMAP.put(sq.next(), dischargePrintingVO.getBatchNo());
		populateMAP.put(sq.next(), dischargePrintingVO.getRemarks());
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
	
}
