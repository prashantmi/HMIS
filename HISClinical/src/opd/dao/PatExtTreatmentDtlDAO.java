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
import hisglobal.vo.PatExtTreatmentDetailVO;
import hisglobal.vo.UserVO;

public class PatExtTreatmentDtlDAO extends DataAccessObject implements PatExtTreatmentDtlDAOi
{
	public PatExtTreatmentDtlDAO(TransactionContext _tx)
	{
		super(_tx);
	}
	
	public void savePatExtTreatmentDetail(PatExtTreatmentDetailVO _patExtTreatmentDetailVO, UserVO _userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_DAO;
		String queryKey = "INSERT.NEW.OPD.HRGT_EPISODE_EXTTREATMENT_DTL";

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
			populateMAP.put(sq.next(), _patExtTreatmentDetailVO.getPatCrNo());
			populateMAP.put(sq.next(), _patExtTreatmentDetailVO.getEpisodeCode());
			populateMAP.put(sq.next(), _patExtTreatmentDetailVO.getPatCrNo());
			populateMAP.put(sq.next(), _patExtTreatmentDetailVO.getEpisodeCode());
			populateMAP.put(sq.next(), _patExtTreatmentDetailVO.getEpisodeVisitNo());
			if(_patExtTreatmentDetailVO.getAdmissionNo()==null) _patExtTreatmentDetailVO.setAdmissionNo("");
			populateMAP.put(sq.next(), _patExtTreatmentDetailVO.getAdmissionNo());
			populateMAP.put(sq.next(), _patExtTreatmentDetailVO.getEpisodeVisitNo());
			populateMAP.put(sq.next(), _patExtTreatmentDetailVO.getExtTreatmentId());
			populateMAP.put(sq.next(), _patExtTreatmentDetailVO.getDoseId());
			populateMAP.put(sq.next(), _userVO.getSeatId());
			populateMAP.put(sq.next(), _patExtTreatmentDetailVO.getDoseName());
			populateMAP.put(sq.next(), _patExtTreatmentDetailVO.getFrequencyId());
			populateMAP.put(sq.next(), _patExtTreatmentDetailVO.getDays());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _patExtTreatmentDetailVO.getStartDate());
			populateMAP.put(sq.next(), _patExtTreatmentDetailVO.getStartDate());
			populateMAP.put(sq.next(), _patExtTreatmentDetailVO.getDays());
			//populateMAP.put(sq.next(), _patExtTreatmentDetailVO.getEndDate());
			populateMAP.put(sq.next(), _patExtTreatmentDetailVO.getRemarks());
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), _userVO.getUserEmpID());
			populateMAP.put(sq.next(), _patExtTreatmentDetailVO.getRxContinue());
			populateMAP.put(sq.next(), _patExtTreatmentDetailVO.getIsMasterBound());
			populateMAP.put(sq.next(), _patExtTreatmentDetailVO.getExtTreatmentName());
			populateMAP.put(sq.next(), _patExtTreatmentDetailVO.getTreatmentType());
			
			
			
			
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
	
	public void saveRxandRevoke(PatExtTreatmentDetailVO _patExtTreatmentDetailVO, UserVO _userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_DAO;
		String queryKey = "INSERT.RX_AND_REVOKE.HRGT_EPISODE_EXTTREATMENT_DTL";

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
			populateMAP.put(sq.next(), _patExtTreatmentDetailVO.getPatCrNo());
			populateMAP.put(sq.next(), _patExtTreatmentDetailVO.getEpisodeCode());
			//SEREAL no
			populateMAP.put(sq.next(), _patExtTreatmentDetailVO.getPatCrNo());
			populateMAP.put(sq.next(), _patExtTreatmentDetailVO.getEpisodeCode());
			populateMAP.put(sq.next(), _patExtTreatmentDetailVO.getEpisodeVisitNo());
			
			
			if(_patExtTreatmentDetailVO.getAdmissionNo()==null) _patExtTreatmentDetailVO.setAdmissionNo("");
			populateMAP.put(sq.next(), _patExtTreatmentDetailVO.getAdmissionNo());
			populateMAP.put(sq.next(), _patExtTreatmentDetailVO.getEpisodeVisitNo());
			populateMAP.put(sq.next(), _patExtTreatmentDetailVO.getExtTreatmentId());
			populateMAP.put(sq.next(), _patExtTreatmentDetailVO.getDoseId());
			populateMAP.put(sq.next(), _userVO.getSeatId());
			populateMAP.put(sq.next(), _patExtTreatmentDetailVO.getDoseName());
			populateMAP.put(sq.next(), _patExtTreatmentDetailVO.getFrequencyId());
			populateMAP.put(sq.next(), _patExtTreatmentDetailVO.getDays());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			
			
			populateMAP.put(sq.next(), _patExtTreatmentDetailVO.getStartDate());
			//populateMAP.put(sq.next(), _patExtTreatmentDetailVO.getStartDate());
			//populateMAP.put(sq.next(), _patExtTreatmentDetailVO.getDays());
			populateMAP.put(sq.next(), _patExtTreatmentDetailVO.getEndDate());
			
			
			populateMAP.put(sq.next(), _patExtTreatmentDetailVO.getRemarks());
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), _userVO.getUserEmpID());
			populateMAP.put(sq.next(), _patExtTreatmentDetailVO.getRxContinue());
			populateMAP.put(sq.next(), _patExtTreatmentDetailVO.getIsMasterBound());
			populateMAP.put(sq.next(), _patExtTreatmentDetailVO.getExtTreatmentName());
			populateMAP.put(sq.next(), _patExtTreatmentDetailVO.getTreatmentType());
			
			
			
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
	
	public void updateRevoke(PatExtTreatmentDetailVO _patExtTreatDtlVO, UserVO _userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_DAO;
		String queryKey = "UPDATE.OPD.HRGT_EPISODE_EXTTREATMENT_DTL";

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
			populateMAP.put(sq.next(), _patExtTreatDtlVO.getRxContinue());
			//populateMAP.put(sq.next(), _patDrugDtlVO.getEndDate());
			populateMAP.put(sq.next(), _patExtTreatDtlVO.getPatCrNo());
			populateMAP.put(sq.next(), _patExtTreatDtlVO.getEpisodeCode());
			populateMAP.put(sq.next(), _patExtTreatDtlVO.getEpisodeVisitNo());
			populateMAP.put(sq.next(), _patExtTreatDtlVO.getSerialNo());
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
						
		}
		catch (Exception e)
		{
			throw new HisDataAccessException(
					"PatDrugTreatmentDetailDAO.getPatientEpisodeClinicalData::populateMAP " + e);
		}

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
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
	}
	
	
	public void updateLastTodayVisitRecord(PatExtTreatmentDetailVO _patExtTreatDtlVO, UserVO _userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_DAO;
		String queryKey = "UPDATE.TODAY_VISIT_RECORD.HRGT_EPISODE_EXTTREATMENT_DTL";

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
			//populateMAP.put(sq.next(), _patDrugDtlVO.getEndDate());
			populateMAP.put(sq.next(), _patExtTreatDtlVO.getPatCrNo());
			populateMAP.put(sq.next(), _patExtTreatDtlVO.getEpisodeCode());
			populateMAP.put(sq.next(), _patExtTreatDtlVO.getEpisodeVisitNo());
			//populateMAP.put(sq.next(), _patExtTreatDtlVO.getSerialNo());
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _patExtTreatDtlVO.getEntryDate());
						
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
