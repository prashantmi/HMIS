package opd.dao;

import java.util.HashMap;
import java.util.Map;

import opd.OpdConfig;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.TransactionContext;
import hisglobal.utility.Sequence;
import hisglobal.vo.PatDietAdviceDetailVO;
import hisglobal.vo.UserVO;

public class PatDietAdviceDetailDAO extends DataAccessObject implements PatDietAdviceDetailDAOi
{
	public PatDietAdviceDetailDAO(TransactionContext _tx)
	{
		super(_tx);
	}
	
	public void savePatDietAdviceDetail(PatDietAdviceDetailVO patDietAdviceDetailVO, UserVO _userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_DAO;
		String queryKey = "INSERT.NEW.OPD.HRGT_EPISODE_DIETADVICE_DTL";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		Sequence sq = new Sequence();
		
		try
		{
			populateMAP.put(sq.next(), patDietAdviceDetailVO.getPatCrNo());
			populateMAP.put(sq.next(), patDietAdviceDetailVO.getEpisodeCode());
			populateMAP.put(sq.next(), patDietAdviceDetailVO.getPatCrNo());
			populateMAP.put(sq.next(), patDietAdviceDetailVO.getEpisodeCode());
			populateMAP.put(sq.next(), patDietAdviceDetailVO.getEpisodeVisitNo());
			if(patDietAdviceDetailVO.getAdmissionNo()==null) patDietAdviceDetailVO.setAdmissionNo("");
			populateMAP.put(sq.next(), patDietAdviceDetailVO.getAdmissionNo());
			populateMAP.put(sq.next(), patDietAdviceDetailVO.getEpisodeVisitNo());
			populateMAP.put(sq.next(), patDietAdviceDetailVO.getDietTypeCode());
			populateMAP.put(sq.next(), _userVO.getSeatId());
			
			populateMAP.put(sq.next(), patDietAdviceDetailVO.getDays());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			//populateMAP.put(sq.next(), patDietAdviceDetailVO.getStartDate());
			//populateMAP.put(sq.next(), patDietAdviceDetailVO.getStartDate());
			populateMAP.put(sq.next(), patDietAdviceDetailVO.getDays());
			//populateMAP.put(sq.next(), _patExtTreatmentDetailVO.getEndDate());
			populateMAP.put(sq.next(), patDietAdviceDetailVO.getRemarks());
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), _userVO.getUserEmpID());
			
			
			
			
		}
		catch (Exception e)
		{
			throw new HisDataAccessException(
					"PatDrugTreatmentDetailDAO.getPatientEpisodeClinicalData::populateMAP " + e);
		}

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
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
	}
	
	public void updatePatDietAdviceDetail(PatDietAdviceDetailVO patDietAdviceDetailVO, UserVO _userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_DAO;
		String queryKey = "UPDATE.OPD.HRGT_EPISODE_DIETADVICE_DTL";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		Sequence sq = new Sequence();
		
		try
		{
			populateMAP.put(sq.next(), Config.IS_VALID_DELETED);
			populateMAP.put(sq.next(), patDietAdviceDetailVO.getPatCrNo());
			populateMAP.put(sq.next(), patDietAdviceDetailVO.getEpisodeCode());
			populateMAP.put(sq.next(), patDietAdviceDetailVO.getSerialNo());
			populateMAP.put(sq.next(), patDietAdviceDetailVO.getEpisodeVisitNo());
			
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			
						
		}
		catch (Exception e)
		{
			throw new HisDataAccessException(
					"PatDrugTreatmentDetailDAO.getPatientEpisodeClinicalData::populateMAP " + e);
		}

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
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
	}

}
