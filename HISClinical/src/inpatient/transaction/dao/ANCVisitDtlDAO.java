package inpatient.transaction.dao;

import inpatient.InpatientConfig;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.TransactionContext;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.Sequence;
import hisglobal.vo.ANCVisitDetailVO;
import hisglobal.vo.UserVO;

public class ANCVisitDtlDAO extends DataAccessObject implements ANCVisitDtlDAOi
{
	public ANCVisitDtlDAO(TransactionContext _tx)
	{
		super(_tx);
	}

	/**
	 * Getting ANC Visit Detail 
	 * @param _ancVisitDetailVO ANC Visit Detail VO
	 * @param _userVO User Detail
	 * @return ANC Visit Detail
	 */
	public ANCVisitDetailVO getANCVisitDetail(ANCVisitDetailVO _ancVisitDetailVO, UserVO _userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = InpatientConfig.QUERY_FILE_FOR_INPATIENT_DAO;
		String queryKey = "SELECT.VISIT_DTL.HANCT_ANC_VISIT_DTL";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		populateMAP.put(sq.next(), _ancVisitDetailVO.getPatCrNo());
		populateMAP.put(sq.next(), _ancVisitDetailVO.getEpisodeCode());
		populateMAP.put(sq.next(), _ancVisitDetailVO.getEpisodeVisitNo());
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), _userVO.getHospitalCode());

		ANCVisitDetailVO ancVisitDtlVO = new ANCVisitDetailVO();
		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (rs.next())
				HelperMethods.populateVOfrmRS(ancVisitDtlVO, rs);
			else
				ancVisitDtlVO=null;
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("Application Execution Exception" + e);
		}
		return ancVisitDtlVO;
	}

	/**
	 * Create ANC Visit Detail
	 * @param _ancVisitDetailVO ANC Detail VO
	 * @param _userVO User Detail
	 */
	public void create(ANCVisitDetailVO _ancVisitDetailVO, UserVO _userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = InpatientConfig.QUERY_FILE_FOR_INPATIENT_DAO;
		String queryKey = "INSERT.NEW_RECORD.HANCT_ANC_VISIT_DTL";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		populateMAP.put(sq.next(), _ancVisitDetailVO.getPatCrNo());
		populateMAP.put(sq.next(), _ancVisitDetailVO.getPatCrNo());
		populateMAP.put(sq.next(), _ancVisitDetailVO.getGravidaNo());
		populateMAP.put(sq.next(), _ancVisitDetailVO.getAncNo());
		populateMAP.put(sq.next(), _ancVisitDetailVO.getGravidaNo());
		populateMAP.put(sq.next(), _ancVisitDetailVO.getEpisodeCode());
		populateMAP.put(sq.next(), _ancVisitDetailVO.getEpisodeVisitNo());
		populateMAP.put(sq.next(), _ancVisitDetailVO.getAdmissionNo());
		populateMAP.put(sq.next(), _ancVisitDetailVO.getGestationPeriod());
		populateMAP.put(sq.next(), _ancVisitDetailVO.getIsHighRiskPregnancy());
		populateMAP.put(sq.next(), _ancVisitDetailVO.getComplications());
		populateMAP.put(sq.next(), _ancVisitDetailVO.getQuickeningWeek());
		populateMAP.put(sq.next(), _ancVisitDetailVO.getQuickeningRemarks());
		populateMAP.put(sq.next(), _ancVisitDetailVO.getRemarks());
		populateMAP.put(sq.next(), _userVO.getIpAddress());
		populateMAP.put(sq.next(), _userVO.getHospitalCode());
		populateMAP.put(sq.next(), _userVO.getSeatId());
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);

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
			else throw new HisDataAccessException("Application Execution Exception" + e);
		}
	}

	/**
	 * Update ANC Visit Detail
	 * @param _ancVisitDetailVO ANC Detail VO
	 * @param _userVO User Detail
	 */
	public void update(ANCVisitDetailVO _ancVisitDetailVO, UserVO _userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = InpatientConfig.QUERY_FILE_FOR_INPATIENT_DAO;
		String queryKey = "UPDATE.RECORD.HANCT_ANC_VISIT_DTL";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		populateMAP.put(sq.next(), _ancVisitDetailVO.getAncNo());
		populateMAP.put(sq.next(), _ancVisitDetailVO.getGestationPeriod());
		populateMAP.put(sq.next(), _ancVisitDetailVO.getIsHighRiskPregnancy());
		populateMAP.put(sq.next(), _ancVisitDetailVO.getComplications());
		populateMAP.put(sq.next(), _ancVisitDetailVO.getQuickeningWeek());
		populateMAP.put(sq.next(), _ancVisitDetailVO.getQuickeningRemarks());
		populateMAP.put(sq.next(), _ancVisitDetailVO.getRemarks());
		populateMAP.put(sq.next(), _ancVisitDetailVO.getPatCrNo());
		populateMAP.put(sq.next(), _ancVisitDetailVO.getGravidaNo());
		populateMAP.put(sq.next(), _ancVisitDetailVO.getEpisodeCode());
		populateMAP.put(sq.next(), _ancVisitDetailVO.getEpisodeVisitNo());
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);

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
			else throw new HisDataAccessException("Application Execution Exception" + e);
		}
	}
}
