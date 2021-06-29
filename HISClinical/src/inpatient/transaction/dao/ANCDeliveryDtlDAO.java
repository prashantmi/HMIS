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
import hisglobal.vo.ANCDeliveryDetailVO;
import hisglobal.vo.ANCDetailVO;
import hisglobal.vo.UserVO;

public class ANCDeliveryDtlDAO extends DataAccessObject implements ANCDeliveryDtlDAOi
{
	public ANCDeliveryDtlDAO(TransactionContext _tx)
	{
		super(_tx);
	}

	/**
	 * Getting ANC Delivery Detail 
	 * @param _ancDeliveryDetailVO ANC Delivery Detail VO
	 * @param _userVO User Detail
	 * @return
	 */
	public ANCDeliveryDetailVO getANCDeliveryDetail(ANCDeliveryDetailVO _ancDeliveryDetailVO, UserVO _userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = InpatientConfig.QUERY_FILE_FOR_INPATIENT_DAO;
		String queryKey = "SELECT.PAT_ANC_DELIVERY_DETAIL.HANCT_ANC_DELIVERY_DTL";

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
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);			
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);			
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);			
			populateMAP.put(sq.next(), _ancDeliveryDetailVO.getPatCrNo());
			populateMAP.put(sq.next(), _ancDeliveryDetailVO.getGravidaNo());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
		}
		catch(Exception e)
		{
			throw new HisApplicationExecutionException("ANCDeliveryDtlDAO.populateMAP::" + e);
		}		

		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (rs.next())
				HelperMethods.populateVOfrmRS(_ancDeliveryDetailVO, rs);
			else
				_ancDeliveryDetailVO=null;
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("Application Execution Exception" + e);
		}
		return _ancDeliveryDetailVO;
	}

	/**
	 * Create New ANC Delivery Detail
	 * @param _ancDeliveryDetailVO ANC Delivery Detail VO
	 * @param _userVO User Detail
	 */
	public void create(ANCDeliveryDetailVO _ancDeliveryDetailVO, UserVO _userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = InpatientConfig.QUERY_FILE_FOR_INPATIENT_DAO;
		String queryKey = "INSERT.NEW_RECORD.HANCT_ANC_DELIVERY_DTL";

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
			_ancDeliveryDetailVO = setVOEmptyToNull(_ancDeliveryDetailVO);
			populateMAP.put(sq.next(), _ancDeliveryDetailVO.getPatCrNo());
			populateMAP.put(sq.next(), _ancDeliveryDetailVO.getPatCrNo());
			populateMAP.put(sq.next(), _ancDeliveryDetailVO.getGravidaNo());
			populateMAP.put(sq.next(), _ancDeliveryDetailVO.getDeliveryDate());
			populateMAP.put(sq.next(), _ancDeliveryDetailVO.getDeliveryStatus());
			populateMAP.put(sq.next(), _ancDeliveryDetailVO.getPregnancyDuration());
			populateMAP.put(sq.next(), _ancDeliveryDetailVO.getPregnancyRemarks());
			populateMAP.put(sq.next(), _ancDeliveryDetailVO.getDeliveryPlaceId());
			populateMAP.put(sq.next(), _ancDeliveryDetailVO.getLabourDuration());
			populateMAP.put(sq.next(), _ancDeliveryDetailVO.getLabourRemarks());
			populateMAP.put(sq.next(), _ancDeliveryDetailVO.getLabourRoomAreaId());
			populateMAP.put(sq.next(), _ancDeliveryDetailVO.getGravidaNo());
			populateMAP.put(sq.next(), _ancDeliveryDetailVO.getDeliveryTypeId());
			populateMAP.put(sq.next(), _ancDeliveryDetailVO.getIsInduced());
			populateMAP.put(sq.next(), _ancDeliveryDetailVO.getIndicationOfInduction());
			populateMAP.put(sq.next(), _ancDeliveryDetailVO.getInductionMethodId());
			populateMAP.put(sq.next(), _ancDeliveryDetailVO.getPlacentaRemovalMethodId());
			populateMAP.put(sq.next(), _ancDeliveryDetailVO.getEpisodeCode());
			populateMAP.put(sq.next(), _ancDeliveryDetailVO.getPlacentaWeight());
			populateMAP.put(sq.next(), _ancDeliveryDetailVO.getEpisodeVisitNo());
			populateMAP.put(sq.next(), _ancDeliveryDetailVO.getPlacentaTypeId());
			populateMAP.put(sq.next(), _ancDeliveryDetailVO.getAdmissionNo());
			populateMAP.put(sq.next(), _ancDeliveryDetailVO.getPlacentaMorphology());
			populateMAP.put(sq.next(), _ancDeliveryDetailVO.getPlacentaHistopath());
			populateMAP.put(sq.next(), _ancDeliveryDetailVO.getRuptureType());		
			populateMAP.put(sq.next(), _ancDeliveryDetailVO.getRuptureDuration());
			populateMAP.put(sq.next(), _ancDeliveryDetailVO.getIsFoetalDistress());
			populateMAP.put(sq.next(), _ancDeliveryDetailVO.getRuptureDateTime());		
			populateMAP.put(sq.next(), _ancDeliveryDetailVO.getLabourStage1Duration());
			populateMAP.put(sq.next(), _ancDeliveryDetailVO.getLabourStage2Duration());
			populateMAP.put(sq.next(), _ancDeliveryDetailVO.getLabourStage3Duration());
			populateMAP.put(sq.next(), _ancDeliveryDetailVO.getLabourStage1Remarks());
			populateMAP.put(sq.next(), _ancDeliveryDetailVO.getLabourStage2Remarks());
			populateMAP.put(sq.next(), _ancDeliveryDetailVO.getLabourStage3Remarks());
			populateMAP.put(sq.next(), _ancDeliveryDetailVO.getComplicationId());		
			populateMAP.put(sq.next(), _ancDeliveryDetailVO.getComplicationRemarks());
			populateMAP.put(sq.next(), _ancDeliveryDetailVO.getIsEAS());
			populateMAP.put(sq.next(), _userVO.getUserEmpID());		
			populateMAP.put(sq.next(), _ancDeliveryDetailVO.getIndicationOfDeliveyType());
			populateMAP.put(sq.next(), _ancDeliveryDetailVO.getMotherStatus());
			populateMAP.put(sq.next(), _ancDeliveryDetailVO.getDeathCause());		
			populateMAP.put(sq.next(), _userVO.getIpAddress());
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), _userVO.getSeatId());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		}
		catch(Exception e)
		{
			throw new HisApplicationExecutionException("ANCDeliveryDtlDAO.populateMAP::" + e);
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
			else throw new HisDataAccessException("Application Execution Exception" + e);
		}
	}

	/**
	 * Update ANC Detail
	 * @param _ancDeliveryDetailVO ANC Detail VO
	 * @param _userVO User Detail
	 * @return
	 */
/*	public void update(ANCDetailVO _ancDeliveryDetailVO, UserVO _userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = InpatientConfig.QUERY_FILE_FOR_INPATIENT_DAO;
		String queryKey = "UPDATE.RECORD.HANCT_ANC_DTL";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		_ancDeliveryDetailVO = setVOEmptyToNull(_ancDeliveryDetailVO);
		populateMAP.put(sq.next(), _ancDeliveryDetailVO.getLmpDate());
		populateMAP.put(sq.next(), _ancDeliveryDetailVO.getMenstrualCycleId());
		populateMAP.put(sq.next(), _ancDeliveryDetailVO.getExpectedDeliveryDate());
		populateMAP.put(sq.next(), _ancDeliveryDetailVO.getActualDeliveryDate());
		populateMAP.put(sq.next(), _ancDeliveryDetailVO.getGestationStartDate());
		populateMAP.put(sq.next(), _ancDeliveryDetailVO.getGestationPeriod());
		populateMAP.put(sq.next(), _ancDeliveryDetailVO.getIsHighRiskPregnancy());
		populateMAP.put(sq.next(), _ancDeliveryDetailVO.getComplications());
		populateMAP.put(sq.next(), _ancDeliveryDetailVO.getDeliveryStatus());
		populateMAP.put(sq.next(), _ancDeliveryDetailVO.getQuickeningWeek());
		populateMAP.put(sq.next(), _ancDeliveryDetailVO.getQuickeningRemarks());
		populateMAP.put(sq.next(), _ancDeliveryDetailVO.getMenstrualCycleDays());
		populateMAP.put(sq.next(), _ancDeliveryDetailVO.getIsContraceptiveUsed());
		populateMAP.put(sq.next(), _ancDeliveryDetailVO.getContraceptiveRemarks());
		populateMAP.put(sq.next(), _ancDeliveryDetailVO.getIsAntiDGiven());
		populateMAP.put(sq.next(), _ancDeliveryDetailVO.getUltraSoundEDD());		
		populateMAP.put(sq.next(), _ancDeliveryDetailVO.getDetectionMethod());		
		populateMAP.put(sq.next(), _ancDeliveryDetailVO.getDetectionDate());		
		populateMAP.put(sq.next(), _userVO.getSeatId());		
		populateMAP.put(sq.next(), _ancDeliveryDetailVO.getPatCrNo());
		populateMAP.put(sq.next(), _ancDeliveryDetailVO.getGravidaNo());
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);

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
			else throw new HisDataAccessException("Application Execution Exception" + e);
		}
	}*/
	
	private ANCDeliveryDetailVO setVOEmptyToNull(ANCDeliveryDetailVO _vo)
	{
		if(_vo.getComplicationId()!=null && _vo.getComplicationId().trim().equals("-1")) _vo.setComplicationId(null);
		if(_vo.getDeathCause()!=null && _vo.getDeathCause().trim().equals("-1")) _vo.setDeathCause(null);
		if(_vo.getInductionMethodId()!=null && _vo.getInductionMethodId().trim().equals("-1")) _vo.setInductionMethodId(null);
		if(_vo.getMotherStatus()!=null && _vo.getMotherStatus().trim().equals("-1")) _vo.setMotherStatus(null);
		if(_vo.getPlacentaRemovalMethodId()!=null && _vo.getPlacentaRemovalMethodId().trim().equals("-1")) _vo.setPlacentaRemovalMethodId(null);
		if(_vo.getPlacentaTypeId()!=null && _vo.getPlacentaTypeId().trim().equals("-1")) _vo.setPlacentaTypeId(null);
		if(_vo.getRuptureType()!=null && _vo.getRuptureType().trim().equals("-1")) _vo.setRuptureType(null);
		
		if(_vo.getLabourDuration()!=null && _vo.getLabourDuration().trim().equals("")) _vo.setLabourDuration(null);
		if(_vo.getLabourStage1Duration()!=null && _vo.getLabourStage1Duration().trim().equals("")) _vo.setLabourStage1Duration(null);
		if(_vo.getLabourStage2Duration()!=null && _vo.getLabourStage2Duration().trim().equals("")) _vo.setLabourStage2Duration(null);
		if(_vo.getLabourStage3Duration()!=null && _vo.getLabourStage3Duration().trim().equals("")) _vo.setLabourStage3Duration(null);
		if(_vo.getPregnancyDuration()!=null && _vo.getPregnancyDuration().trim().equals("")) _vo.setPregnancyDuration(null);
		if(_vo.getRuptureDateTime()!=null && _vo.getRuptureDateTime().trim().equals("")) _vo.setRuptureDateTime(null);
		if(_vo.getRuptureDuration()!=null && _vo.getRuptureDuration().trim().equals("")) _vo.setRuptureDuration(null);
		
		return _vo;
	}
	
	public void updateMotherStatus(ANCDetailVO ancDetailVO,UserVO userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = InpatientConfig.QUERY_FILE_FOR_INPATIENT_DAO;
		String queryKey = "UPDATE.MOTHER_STATUS.HANCT_ANC_DTL";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		
		populateMAP.put(sq.next(), InpatientConfig.MOTHER_STATUS_DEAD);
		populateMAP.put(sq.next(), ancDetailVO.getDeathCause());
		populateMAP.put(sq.next(), ancDetailVO.getPatCrNo());
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), userVO.getHospitalCode());
		populateMAP.put(sq.next(), ancDetailVO.getGravidaNo());
		populateMAP.put(sq.next(), ancDetailVO.getPatCrNo());
		populateMAP.put(sq.next(), ancDetailVO.getGravidaNo());
		
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
			else throw new HisDataAccessException("Application Execution Exception" + e);
		}
	}
}
