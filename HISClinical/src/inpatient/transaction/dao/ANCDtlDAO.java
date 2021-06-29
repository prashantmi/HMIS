package inpatient.transaction.dao;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.TransactionContext;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.Sequence;
import hisglobal.vo.ANCDetailVO;
import hisglobal.vo.UserVO;
import hisglobal.vo.ValueObject;
import inpatient.InpatientConfig;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import mrd.MrdConfig;
import opd.OpdConfig;

public class ANCDtlDAO extends DataAccessObject implements ANCDtlDAOi
{
	public ANCDtlDAO(TransactionContext _tx)
	{
		super(_tx);
	}

	/**
	 * Getting ANC Detail
	 * @param _ancDetailVO ANC Detail VO
	 * @param _userVO User Detail
	 * @return
	 */
	public ANCDetailVO getANCDetail(ANCDetailVO _ancDetailVO, UserVO _userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = InpatientConfig.QUERY_FILE_FOR_INPATIENT_DAO;
		String queryKey = "SELECT.PAT_ANC_DETAIL.HANCT_ANC_DTL";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), _userVO.getHospitalCode());
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), _userVO.getHospitalCode());
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), _ancDetailVO.getPatCrNo());
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), _userVO.getHospitalCode());
		populateMAP.put(sq.next(), _ancDetailVO.getPatCrNo());
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), _userVO.getHospitalCode());

		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (rs.next())
				HelperMethods.populateVOfrmRS(_ancDetailVO, rs);
			else
				_ancDetailVO=null;
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("Application Execution Exception" + e);
		}
		return _ancDetailVO;
	}

	/**
	 * Getting ANC Detail by Episode
	 * @param _ancDetailVO ANC Detail VO
	 * @param _userVO User Detail
	 * @return
	 */
	public ANCDetailVO getANCDetailByEpisode(ANCDetailVO _ancDetailVO, UserVO _userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = InpatientConfig.QUERY_FILE_FOR_INPATIENT_DAO;
		String queryKey = "SELECT.PAT_ANC_DETAIL_BY_EPISODE.HANCT_ANC_DTL";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), _userVO.getHospitalCode());
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), _userVO.getHospitalCode());
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), _ancDetailVO.getPatCrNo());
		populateMAP.put(sq.next(), _ancDetailVO.getEpisodeCode());
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), _userVO.getHospitalCode());
		populateMAP.put(sq.next(), _ancDetailVO.getPatCrNo());
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), _userVO.getHospitalCode());

		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (rs.next())
				HelperMethods.populateVOfrmRS(_ancDetailVO, rs);
			else
				_ancDetailVO=null;
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("Application Execution Exception" + e);
		}
		return _ancDetailVO;
	}

	/**
	 * Getting ANC Detail By Gravida
	 * @param _ancDetailVO ANC Detail VO
	 * @param _userVO User Detail
	 * @return
	 */
	public ANCDetailVO getANCDetailByGravida(ANCDetailVO _ancDetailVO, UserVO _userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = InpatientConfig.QUERY_FILE_FOR_INPATIENT_DAO;
		String queryKey = "SELECT.PAT_ANC_DETAIL_BY_GRAVIDA.HANCT_ANC_DTL";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), _userVO.getHospitalCode());
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), _userVO.getHospitalCode());
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), _ancDetailVO.getPatCrNo());
		populateMAP.put(sq.next(), _userVO.getHospitalCode());
		populateMAP.put(sq.next(), Config.ANC_DETAIL_MAXIMUM_GESTATION_WEEK_FOR_ANC);
		populateMAP.put(sq.next(), _ancDetailVO.getPatCrNo());
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), _userVO.getHospitalCode());
		populateMAP.put(sq.next(), _ancDetailVO.getGravidaNo());

		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (rs.next())
				HelperMethods.populateVOfrmRS(_ancDetailVO, rs);
			else
				_ancDetailVO=null;
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("Application Execution Exception" + e);
		}
		return _ancDetailVO;
	}

	/**
	 * Getting ANC Basic History Detail
	 * @param _ancDetailVO ANC Detail VO
	 * @param _userVO User Detail
	 * @return
	 */
	public ANCDetailVO getANCBasicHistory(ANCDetailVO _ancDetailVO, UserVO _userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = InpatientConfig.QUERY_FILE_FOR_INPATIENT_DAO;
		String queryKey = "SELECT.BASIC_ANC_HIS_DETAIL.HANCT_ANC_DTL";
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		populateMAP.put(sq.next(), _ancDetailVO.getPatCrNo());
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), _ancDetailVO.getPatCrNo());
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);

		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (rs.next())
				HelperMethods.populateVOfrmRS(_ancDetailVO, rs);
			else
			{				
				_ancDetailVO.setGravidaNo("1");
				_ancDetailVO.setParityNo("0");
				_ancDetailVO.setAbortusNo("0");
			}
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("Application Execution Exception" + e);
		}
		return _ancDetailVO;
	}

	/**
	 * Create New ANC Detail
	 * @param _ancDetailVO ANC Detail VO
	 * @param _userVO User Detail
	 * @return
	 */
	public void create(ANCDetailVO _ancDetailVO, UserVO _userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = InpatientConfig.QUERY_FILE_FOR_INPATIENT_DAO;
		String queryKey = "INSERT.NEW_RECORD.HANCT_ANC_DTL";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		_ancDetailVO = setVOEmptyToNull(_ancDetailVO);
		populateMAP.put(sq.next(), _ancDetailVO.getPatCrNo());
		populateMAP.put(sq.next(), _ancDetailVO.getPatCrNo());
		populateMAP.put(sq.next(), _ancDetailVO.getGravidaNo());
		populateMAP.put(sq.next(), _ancDetailVO.getAncNo());
		populateMAP.put(sq.next(), _ancDetailVO.getGravidaNo());
		populateMAP.put(sq.next(), _ancDetailVO.getEpisodeCode());
		populateMAP.put(sq.next(), _ancDetailVO.getEpisodeVisitNo());
		populateMAP.put(sq.next(), _ancDetailVO.getParityNo());
		populateMAP.put(sq.next(), _ancDetailVO.getAbortusNo());
		populateMAP.put(sq.next(), _ancDetailVO.getAdmissionNo());
		populateMAP.put(sq.next(), _ancDetailVO.getLmpDate());
		populateMAP.put(sq.next(), _ancDetailVO.getIsActualLMP());
		populateMAP.put(sq.next(), _ancDetailVO.getMenstrualCycleId());
		populateMAP.put(sq.next(), _ancDetailVO.getExpectedDeliveryDate());
		populateMAP.put(sq.next(), _ancDetailVO.getGestationStartDate());
		populateMAP.put(sq.next(), _ancDetailVO.getGestationPeriod());
		populateMAP.put(sq.next(), _ancDetailVO.getIsHighRiskPregnancy());
		populateMAP.put(sq.next(), _ancDetailVO.getComplications());
		populateMAP.put(sq.next(), _ancDetailVO.getQuickeningWeek());
		populateMAP.put(sq.next(), _ancDetailVO.getQuickeningRemarks());
		populateMAP.put(sq.next(), _ancDetailVO.getMenstrualCycleDays());
		populateMAP.put(sq.next(), _ancDetailVO.getIsContraceptiveUsed());
		populateMAP.put(sq.next(), _ancDetailVO.getContraceptiveRemarks());
		populateMAP.put(sq.next(), _ancDetailVO.getIsAntiDGiven());
		populateMAP.put(sq.next(), _ancDetailVO.getUltraSoundEDD());		
		populateMAP.put(sq.next(), _ancDetailVO.getDetectionMethod());
		populateMAP.put(sq.next(), _ancDetailVO.getDetectionDate());
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
	 * Update ANC Detail
	 * @param _ancDetailVO ANC Detail VO
	 * @param _userVO User Detail
	 * @return
	 */
	public void update(ANCDetailVO _ancDetailVO, UserVO _userVO)
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

		_ancDetailVO = setVOEmptyToNull(_ancDetailVO);
		populateMAP.put(sq.next(), _ancDetailVO.getLmpDate());
		populateMAP.put(sq.next(), _ancDetailVO.getMenstrualCycleId());
		populateMAP.put(sq.next(), _ancDetailVO.getExpectedDeliveryDate());
		populateMAP.put(sq.next(), _ancDetailVO.getActualDeliveryDate());
		populateMAP.put(sq.next(), _ancDetailVO.getGestationStartDate());
		populateMAP.put(sq.next(), _ancDetailVO.getGestationPeriod());
		populateMAP.put(sq.next(), _ancDetailVO.getIsHighRiskPregnancy());
		populateMAP.put(sq.next(), _ancDetailVO.getComplications());
		populateMAP.put(sq.next(), _ancDetailVO.getDeliveryStatus());
		populateMAP.put(sq.next(), _ancDetailVO.getQuickeningWeek());
		populateMAP.put(sq.next(), _ancDetailVO.getQuickeningRemarks());
		populateMAP.put(sq.next(), _ancDetailVO.getMenstrualCycleDays());
		populateMAP.put(sq.next(), _ancDetailVO.getIsContraceptiveUsed());
		populateMAP.put(sq.next(), _ancDetailVO.getContraceptiveRemarks());
		populateMAP.put(sq.next(), _ancDetailVO.getIsAntiDGiven());
		populateMAP.put(sq.next(), _ancDetailVO.getUltraSoundEDD());		
		populateMAP.put(sq.next(), _ancDetailVO.getDetectionMethod());		
		populateMAP.put(sq.next(), _ancDetailVO.getDetectionDate());		
		populateMAP.put(sq.next(), _userVO.getSeatId());		
		populateMAP.put(sq.next(), _ancDetailVO.getPatCrNo());
		populateMAP.put(sq.next(), _ancDetailVO.getGravidaNo());
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
	}
	
	
	private ANCDetailVO setVOEmptyToNull(ANCDetailVO _ancVO)
	{
		if(_ancVO.getLmpDate()!=null && _ancVO.getLmpDate().trim().equals("")) _ancVO.setLmpDate(null);
		if(_ancVO.getIsActualLMP()!=null && _ancVO.getIsActualLMP().trim().equals("")) _ancVO.setIsActualLMP(null);
		if(_ancVO.getMenstrualCycleId()!=null && _ancVO.getMenstrualCycleId().trim().equals("-1")) _ancVO.setMenstrualCycleId(null);
		if(_ancVO.getExpectedDeliveryDate()!=null && _ancVO.getExpectedDeliveryDate().trim().equals("")) _ancVO.setExpectedDeliveryDate(null);
		if(_ancVO.getGestationStartDate()!=null && _ancVO.getGestationStartDate().trim().equals("")) _ancVO.setGestationStartDate(null);
		if(_ancVO.getIsHighRiskPregnancy()!=null && _ancVO.getIsHighRiskPregnancy().trim().equals("")) _ancVO.setIsHighRiskPregnancy(null);
		if(_ancVO.getMenstrualCycleDays()!=null && _ancVO.getMenstrualCycleDays().trim().equals("")) _ancVO.setMenstrualCycleDays(null);
		if(_ancVO.getUltraSoundEDD()!=null && _ancVO.getUltraSoundEDD().trim().equals("")) _ancVO.setUltraSoundEDD(null);
		if(_ancVO.getDetectionMethod()!=null && _ancVO.getDetectionMethod().trim().equals("-1")) _ancVO.setDetectionMethod(null);
		if(_ancVO.getDetectionDate()!=null && _ancVO.getDetectionDate().trim().equals("")) _ancVO.setDetectionDate(null);
		
		return _ancVO;
	}
	
	
	
	///  Getting Delivery Status of Patient for JSY Registration ///
	public String getDeliveryStatus(UserVO _userVO,String patCrNo)
	{
		String deliveryStatus = null;
		Map _populateMapPatientDtl = new HashMap();
		String query = "";
		String filename = InpatientConfig.QUERY_FILE_FOR_INPATIENT_DAO;
		String queryKey = "SELECT_DELIVERYSTATUS.HANCT_ANC_DTL";
		Connection conn = super.getTransactionContext().getConnection();
		Sequence sq = new Sequence();
		
		_populateMapPatientDtl.put(sq.next(), patCrNo);
		_populateMapPatientDtl.put(sq.next(), Config.IS_VALID_ACTIVE );
		_populateMapPatientDtl.put(sq.next(), Config.IS_VALID_ACTIVE );
		_populateMapPatientDtl.put(sq.next(), _userVO.getHospitalCode());
		
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
			System.out.println("Query " + query);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		System.out.println("query" + query);
		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(conn, query, _populateMapPatientDtl);
			if (rs.next())
			{
				deliveryStatus = rs.getString(1);
			}
		}
		catch (Exception e)
		{
		    throw new HisDataAccessException("PatientDAO:retrieveByCrNo:HelperMethods :: " + e);
		}
		return deliveryStatus;
	}

	
	///  Getting Delivery Status of Patient for JSY Registration ///
	public String getCompareGestationDate(UserVO _userVO,String patCrNo)
	{
		String flag ="";
		Map _populateMapPatientDtl = new HashMap();
		String query = "";
		String filename = InpatientConfig.QUERY_FILE_FOR_INPATIENT_DAO;
		String queryKey = "SELECT_COMPARE_GESTATION_DATE";
		Connection conn = super.getTransactionContext().getConnection();
		Sequence sq = new Sequence();
		
		_populateMapPatientDtl.put(sq.next(), patCrNo);
		_populateMapPatientDtl.put(sq.next(), _userVO.getHospitalCode());
		_populateMapPatientDtl.put(sq.next(), Config.IS_VALID_ACTIVE );
		_populateMapPatientDtl.put(sq.next(), Config.IS_VALID_ACTIVE );
		_populateMapPatientDtl.put(sq.next(), InpatientConfig.TREATMENT_TYPE_JSY);
		_populateMapPatientDtl.put(sq.next(), Config.IS_VALID_ACTIVE );
		_populateMapPatientDtl.put(sq.next(), _userVO.getHospitalCode());
		
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
			System.out.println("Query " + query);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		System.out.println("query" + query);
		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(conn, query, _populateMapPatientDtl);
			if (rs.next())
			{
				flag = rs.getString(1);
			}
		}
		catch (Exception e)
		{
		    throw new HisDataAccessException("PatientDAO:retrieveByCrNo:HelperMethods :: " + e);
		}
		return flag;
	}

	
	/**
	 * Getting ANC Detail
	 * @param _ancDetailVO ANC Detail VO
	 * @param _userVO User Detail
	 * @return
	 */
	public ANCDetailVO getANCDetailForJSYRegistration(String patCrNo, UserVO _userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		ANCDetailVO aDetailVO=new ANCDetailVO();
		String filename = InpatientConfig.QUERY_FILE_FOR_INPATIENT_DAO;
		String queryKey = "SELECT_ANC_DETAIL.HANCT_ANC_DTL";
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		populateMAP.put(sq.next(), InpatientConfig.BIRTH_NATURE_LIVE_BIRTH);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), patCrNo);
		populateMAP.put(sq.next(), _userVO.getHospitalCode());
		populateMAP.put(sq.next(), Config.ANC_DETAIL_MAXIMUM_GESTATION_WEEK_FOR_ANC);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), patCrNo);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		
		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (rs.next())
				HelperMethods.populateVOfrmRS(aDetailVO, rs);
			else{ throw new HisRecordNotFoundException("ANC Detail not added"); }
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("Application Execution Exception" + e);
		}
		return aDetailVO;
	}

	
	
	
	/**
	 * Update ANC Detail
	 * @param _ancDetailVO ANC Detail VO
	 * @param _userVO User Detail
	 * @return
	 */
	public void updateJsyFlag(String patCrNo,String gravidaNo, UserVO _userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = InpatientConfig.QUERY_FILE_FOR_INPATIENT_DAO;
		String queryKey = "UPDATE_JSY_FLAG.HANCT_ANC_DTL";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		populateMAP.put(sq.next(), InpatientConfig.JSY_FLAG_YES);		
		populateMAP.put(sq.next(), patCrNo);
		populateMAP.put(sq.next(), gravidaNo);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), _userVO.getHospitalCode());

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

	
	///  Getting Count of Visit of Patient for JSY Registration ///
	public String getANCVisitCount(UserVO _userVO,String patCrNo)
	{
		String count = "";
		Map _populateMapPatientDtl = new HashMap();
		String query = "";
		String filename = InpatientConfig.QUERY_FILE_FOR_INPATIENT_DAO;
		String queryKey = "SELECT_RECORD_IN_ANC.HANCT_ANC_DTL";
		Connection conn = super.getTransactionContext().getConnection();
		Sequence sq = new Sequence();
	
		_populateMapPatientDtl.put(sq.next(), patCrNo);
		_populateMapPatientDtl.put(sq.next(), _userVO.getHospitalCode());
		_populateMapPatientDtl.put(sq.next(), Config.IS_VALID_ACTIVE );
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
			System.out.println("Query " + query);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		System.out.println("query" + query);
		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(conn, query, _populateMapPatientDtl);
			if (rs.next())
			{
				count = rs.getString(1);
			}
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("PatientDAO:retrieveByCrNo:HelperMethods :: " + e);
		}
		return count;
	}

	public ANCDetailVO getPatientANCDetail(String crNo,UserVO userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		ANCDetailVO ancDtlVO=new ANCDetailVO();
		String filename = InpatientConfig.QUERY_FILE_FOR_INPATIENT_DAO;
		String queryKey = "GET_DETAIL_FOR_PATIENT_DEATH.HANCT_ANC_DTL";
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), Config.ANC_DETAIL_MAXIMUM_GESTATION_WEEK_FOR_ANC);
		populateMAP.put(sq.next(), Config.ANC_DETAIL_MAXIMUM_WEEK_GAP_FOR_OBSTETRIC_DEATH);
		populateMAP.put(sq.next(), crNo);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), userVO.getHospitalCode());
		
		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (rs.next())
				HelperMethods.populateVOfrmRS(ancDtlVO, rs);
			else
				ancDtlVO=null;
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("Application Execution Exception" + e);
		}
		return ancDtlVO;
	}
	
	
	public ANCDetailVO[] getPatientAncDetails(ANCDetailVO _ancDetailVO, UserVO _userVO)
	{
		ANCDetailVO[] arrAncDetailVO=null;
		ValueObject vo[] = null;
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_DAO;
		String queryKey = "SELECT_PATIENT_ANC_DETAIL.HANCT_ANC_DTL";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		
		populateMAP.put(sq.next(), _ancDetailVO.getPatCrNo());
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), _userVO.getHospitalCode());
		populateMAP.put(sq.next(), _ancDetailVO.getPatCrNo());
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), _userVO.getHospitalCode());

		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				//throw new HisRecordNotFoundException("No ANC Detail Found");
			}
			rs.beforeFirst();

			vo = HelperMethods.populateVOfrmRS(ANCDetailVO.class, rs);
			arrAncDetailVO = new ANCDetailVO[vo.length];
			for (int i = 0; i < vo.length; i++)
			{
				arrAncDetailVO[i] = (ANCDetailVO) vo[i];
			}
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("Application Execution Exception" + e);
		}

		return arrAncDetailVO;
	}
	
	
	public ANCDetailVO[] getPatientAncDetailsForEMR(ANCDetailVO _ancDetailVO,String [] departmentUnitArray,String accessType, UserVO _userVO)
	{
		ANCDetailVO[] arrAncDetailVO=null;
		ValueObject vo[] = null;
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_DAO;
		String queryKey = "SELECT_PATIENT_ANC_DETAIL.HANCT_ANC_DTL.EMR";
		String orderBy="";
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
			if(accessType.equals(MrdConfig.EPR_DISPLAY_ALL_RECORD_NO)){
				String inArg="";
				for(int i=0;i<departmentUnitArray.length;i++){
					inArg+=","+departmentUnitArray[i];
				}
				inArg=inArg.replaceFirst(",","");
				query+="  and (SELECT hgnum_deptunitcode FROM hrgt_episode_dtl WHERE hrgnum_episode_code = x.hrgnum_episode_code "
						+ "and HRGNUM_PUK=x.HRGNUM_PUK and HRGNUM_VISIT_NO=x.HRGNUM_VISIT_NO and gnum_isvalid=x.gnum_isvalid AND "
						+ "gnum_hospital_code = x.gnum_hospital_code) in ("+inArg+") ";
			}
			query+=orderBy;
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		
		
		populateMAP.put(sq.next(), _ancDetailVO.getPatCrNo());
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), _userVO.getHospitalCode());
		populateMAP.put(sq.next(), _ancDetailVO.getPatCrNo());
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), _userVO.getHospitalCode());
		
		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				//throw new HisRecordNotFoundException("No ANC Detail Found");
			}
			rs.beforeFirst();
			
			vo = HelperMethods.populateVOfrmRS(ANCDetailVO.class, rs);
			arrAncDetailVO = new ANCDetailVO[vo.length];
			for (int i = 0; i < vo.length; i++)
			{
				arrAncDetailVO[i] = (ANCDetailVO) vo[i];
			}
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("Application Execution Exception" + e);
		}
		
		return arrAncDetailVO;
	}
	
	
}
