package inpatient.transaction.dao;

import inpatient.InpatientConfig;

import java.util.HashMap;
import java.util.Map;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.TransactionContext;
import hisglobal.utility.Sequence;
import hisglobal.vo.ANCLogDetailVO;
import hisglobal.vo.UserVO;

public class ANCLogDtlDAO extends DataAccessObject implements ANCLogDtlDAOi
{
	public ANCLogDtlDAO(TransactionContext _tx)
	{
		super(_tx);
	}

	/**
	 * Create ANC Log Detail
	 * @param _ancLogDetailVO ANC Log Detail VO
	 * @param _userVO User Detail
	 * @return
	 */
	public void create(ANCLogDetailVO _ancLogDetailVO, UserVO _userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = InpatientConfig.QUERY_FILE_FOR_INPATIENT_DAO;
		String queryKey = "INSERT.LOG_RECORD.HANCT_ANC_LOG_DTL";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		_ancLogDetailVO = setVOEmptyToNull(_ancLogDetailVO);
		populateMAP.put(sq.next(), _ancLogDetailVO.getPatCrNo());
		populateMAP.put(sq.next(), _ancLogDetailVO.getPatCrNo());
		populateMAP.put(sq.next(), _ancLogDetailVO.getGravidaNo());
		populateMAP.put(sq.next(), _ancLogDetailVO.getEpisodeCode());
		populateMAP.put(sq.next(), _ancLogDetailVO.getEpisodeVisitNo());
		populateMAP.put(sq.next(), _ancLogDetailVO.getAncNo());
		populateMAP.put(sq.next(), _ancLogDetailVO.getGravidaNo());
		populateMAP.put(sq.next(), _ancLogDetailVO.getEpisodeCode());
		populateMAP.put(sq.next(), _ancLogDetailVO.getEpisodeVisitNo());
		populateMAP.put(sq.next(), _ancLogDetailVO.getParityNo());
		populateMAP.put(sq.next(), _ancLogDetailVO.getAbortusNo());
		populateMAP.put(sq.next(), _ancLogDetailVO.getAdmissionNo());
		populateMAP.put(sq.next(), _ancLogDetailVO.getLmpDate());
		populateMAP.put(sq.next(), _ancLogDetailVO.getIsActualLMP());
		populateMAP.put(sq.next(), _ancLogDetailVO.getMenstrualCycleId());
		populateMAP.put(sq.next(), _ancLogDetailVO.getExpectedDeliveryDate());
		populateMAP.put(sq.next(), _ancLogDetailVO.getMenstrualCycleDays());
		populateMAP.put(sq.next(), _ancLogDetailVO.getActualDeliveryDate());
		populateMAP.put(sq.next(), _ancLogDetailVO.getUltraSoundEDD());		
		populateMAP.put(sq.next(), _ancLogDetailVO.getGestationStartDate());		
		populateMAP.put(sq.next(), _ancLogDetailVO.getGestationPeriod());
		populateMAP.put(sq.next(), _ancLogDetailVO.getIsHighRiskPregnancy());
		populateMAP.put(sq.next(), _ancLogDetailVO.getComplications());
		populateMAP.put(sq.next(), _ancLogDetailVO.getDeliveryStatus());
		populateMAP.put(sq.next(), _ancLogDetailVO.getIsContraceptiveUsed());		
		populateMAP.put(sq.next(), _ancLogDetailVO.getQuickeningWeek());
		populateMAP.put(sq.next(), _ancLogDetailVO.getContraceptiveRemarks());
		populateMAP.put(sq.next(), _ancLogDetailVO.getIsAntiDGiven());
		populateMAP.put(sq.next(), _ancLogDetailVO.getQuickeningRemarks());
		populateMAP.put(sq.next(), _ancLogDetailVO.getDetectionDate());
		populateMAP.put(sq.next(), _ancLogDetailVO.getDetectionMethod());				
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

	private ANCLogDetailVO setVOEmptyToNull(ANCLogDetailVO _vo)
	{
		if(_vo.getLmpDate()!=null && _vo.getLmpDate().trim().equals("")) _vo.setLmpDate(null);
		if(_vo.getIsActualLMP()!=null && _vo.getIsActualLMP().trim().equals("")) _vo.setIsActualLMP(null);
		if(_vo.getMenstrualCycleId()!=null && _vo.getMenstrualCycleId().trim().equals("-1")) _vo.setMenstrualCycleId(null);
		if(_vo.getExpectedDeliveryDate()!=null && _vo.getExpectedDeliveryDate().trim().equals("")) _vo.setExpectedDeliveryDate(null);
		if(_vo.getGestationStartDate()!=null && _vo.getGestationStartDate().trim().equals("")) _vo.setGestationStartDate(null);
		if(_vo.getIsHighRiskPregnancy()!=null && _vo.getIsHighRiskPregnancy().trim().equals("")) _vo.setIsHighRiskPregnancy(null);
		if(_vo.getMenstrualCycleDays()!=null && _vo.getMenstrualCycleDays().trim().equals("")) _vo.setMenstrualCycleDays(null);
		if(_vo.getUltraSoundEDD()!=null && _vo.getUltraSoundEDD().trim().equals("")) _vo.setUltraSoundEDD(null);
		if(_vo.getDetectionMethod()!=null && _vo.getDetectionMethod().trim().equals("-1")) _vo.setDetectionMethod(null);
		if(_vo.getDetectionDate()!=null && _vo.getDetectionDate().trim().equals("")) _vo.setDetectionDate(null);

		return _vo;
	}
}
