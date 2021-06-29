package opd.dao;

import java.util.HashMap;
import java.util.Map;

import opd.OpdConfig;
import hisglobal.persistence.TransactionContext;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.utility.Sequence;
import hisglobal.vo.PatientBelongingVO;
import hisglobal.vo.UserVO;

public class PatietnBelongingDAO extends DataAccessObject
{
	public PatietnBelongingDAO(TransactionContext _tx)
	{
		super(_tx);
	}

	public void create(PatientBelongingVO _patBelongingVO, UserVO _userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_DAO;
		String queryKey = "INSERT.HOPT_PAT_BELONGING_DTL";

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
			populateMAP.put(sq.next(), _patBelongingVO.getPatCrNo());
			populateMAP.put(sq.next(), _patBelongingVO.getBelongingItemCode());
			populateMAP.put(sq.next(), _patBelongingVO.getQuantity());
			populateMAP.put(sq.next(), _patBelongingVO.getRemarks());
			populateMAP.put(sq.next(), _patBelongingVO.getHandOverBy());
			populateMAP.put(sq.next(), _patBelongingVO.getHandOverDate());
			populateMAP.put(sq.next(), _patBelongingVO.getHandOverTo());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _userVO.getSeatId());
			populateMAP.put(sq.next(), _patBelongingVO.getLastModifyDate());
			populateMAP.put(sq.next(), _patBelongingVO.getLastModifiedSeatID());
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("PatietnBelongingDAO.populateMAP::" + e);
		}
		try
		{
			HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), query, populateMAP);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException("INSERT Failed :: ");
		}
	}

	public void UpdateBelongingHandoverDetails(PatientBelongingVO _patBelongingVO, UserVO _userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_DAO;
		String queryKey = "UPDATE.HANDOVER.DETAILS.HOPT_PAT_BELONGING_DTL";

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
			populateMAP.put(sq.next(), _patBelongingVO.getHandOverBy());
			populateMAP.put(sq.next(), _patBelongingVO.getHandOverTo());
			populateMAP.put(sq.next(), _patBelongingVO.getLastModifiedSeatID());
			populateMAP.put(sq.next(), _patBelongingVO.getPatCrNo());
			populateMAP.put(sq.next(), _patBelongingVO.getBelongingItemCode());
			populateMAP.put(sq.next(), _patBelongingVO.getCollectionDate());
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("PatietnBelongingDAO.populateMAP::" + e);
		}
		
		try
		{
			HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), query, populateMAP);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException("Excetion While ADDING");
		}

	}

	public void modifyBelongingDetails(PatientBelongingVO _patBelongingVO, String _oldItemCode, String _collectionDate, UserVO _userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_DAO;
		String queryKey = "UPDATE.BELONGING.DETAILS.HOPT_PAT_BELONGING_DTL";

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
			populateMAP.put(sq.next(), _patBelongingVO.getBelongingItemCode());
			populateMAP.put(sq.next(), _patBelongingVO.getQuantity());
			populateMAP.put(sq.next(), _patBelongingVO.getRemarks());
			populateMAP.put(sq.next(), _patBelongingVO.getSeatID());
			populateMAP.put(sq.next(), _patBelongingVO.getPatCrNo());
			populateMAP.put(sq.next(), _oldItemCode);
			populateMAP.put(sq.next(), _collectionDate);
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("PatietnBelongingDAO.populateMAP::" + e);
		}
		
		try
		{
			HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), query, populateMAP);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException("Excetion While Modification");
		}
	}
}
