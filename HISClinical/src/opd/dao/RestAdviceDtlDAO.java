package opd.dao;

import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.TransactionContext;
import hisglobal.utility.Sequence;
import hisglobal.vo.RestAdviceDtlVO;
import hisglobal.vo.UserVO;

import java.util.HashMap;
import java.util.Map;

import opd.OpdConfig;

public class RestAdviceDtlDAO extends DataAccessObject implements RestAdviceDtlDAOi
{
	
	public RestAdviceDtlDAO(TransactionContext _tx)
	{
		super(_tx);
	}

	public void savePatRestAdviceTreatmentDetail(RestAdviceDtlVO restAdviceDtlVO, UserVO _userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_DAO;
		String queryKey = "INSERT.NEW.OPD.HRGT_EPISODE_RESTADVICE_DTL";

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
			populateMAP.put(sq.next(), restAdviceDtlVO.getPatCrNo());
			populateMAP.put(sq.next(), restAdviceDtlVO.getEpisodeCode());
			populateMAP.put(sq.next(), restAdviceDtlVO.getPatCrNo());
			populateMAP.put(sq.next(), restAdviceDtlVO.getEpisodeCode());
			populateMAP.put(sq.next(), restAdviceDtlVO.getEpisodeVisitNo());
			if(restAdviceDtlVO.getAdmissionNo()==null) restAdviceDtlVO.setAdmissionNo("");
			populateMAP.put(sq.next(), restAdviceDtlVO.getAdmissionNo());
			populateMAP.put(sq.next(), restAdviceDtlVO.getEpisodeVisitNo());
			populateMAP.put(sq.next(), restAdviceDtlVO.getRestReason());
			populateMAP.put(sq.next(), _userVO.getSeatId());
			populateMAP.put(sq.next(), restAdviceDtlVO.getDays());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			//populateMAP.put(sq.next(), _patDrugDtlVO.getRestStartDate());
			populateMAP.put(sq.next(), restAdviceDtlVO.getDays());
			populateMAP.put(sq.next(), restAdviceDtlVO.getRemarks());
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), _userVO.getUserEmpID());
			populateMAP.put(sq.next(), restAdviceDtlVO.getCirtificateStatus());
			
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
	
	public void updatePatRestAdviceDetail(RestAdviceDtlVO restAdviceDtlVO, UserVO _userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_DAO;
		String queryKey = "UPDATE.OPD.HRGT_EPISODE_RESTADVICE_DTL";

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
			populateMAP.put(sq.next(), restAdviceDtlVO.getPatCrNo());
			populateMAP.put(sq.next(), restAdviceDtlVO.getEpisodeCode());
			//populateMAP.put(sq.next(), restAdviceDtlVO.getSerialNo());
			populateMAP.put(sq.next(), restAdviceDtlVO.getEpisodeVisitNo());
			
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
