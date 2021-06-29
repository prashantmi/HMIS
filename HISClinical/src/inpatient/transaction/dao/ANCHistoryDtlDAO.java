package inpatient.transaction.dao;

import inpatient.InpatientConfig;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
import hisglobal.vo.ANCHistoryDetailVO;
import hisglobal.vo.UserVO;
import hisglobal.vo.ValueObject;

public class ANCHistoryDtlDAO extends DataAccessObject implements ANCHistoryDtlDAOi
{
	public ANCHistoryDtlDAO(TransactionContext _tx)
	{
		super(_tx);
	}

	/**
	 * Getting ANC History Detail 
	 * @param _patCrNo Patient Cr No
	 * @param _userVO User Detail
	 * @return List of ANC History
	 */
	public List<ANCHistoryDetailVO> getANCHistoryDetail(String _patCrNo, UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = InpatientConfig.QUERY_FILE_FOR_INPATIENT_DAO;
		String queryKey = "SELECT.ANC_HISTORY_DETAIL.HANCT_ANC_HISTORY_DTL";
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		Sequence sq = new Sequence();
		try
		{
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _patCrNo);
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		}
		catch(Exception e)
		{
			throw new HisApplicationExecutionException("ANCHistoryDtlDAO.populateMAP::" + e);
		}

		List<ANCHistoryDetailVO> lstANCHist = new ArrayList<ANCHistoryDetailVO>();
		ValueObject[] valueObjects = null;
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if(rs.next())
			{
				rs.beforeFirst();
				valueObjects = HelperMethods.populateVOfrmRS(ANCHistoryDetailVO.class, rs);
				for (int i = 0; i < valueObjects.length; i++)
					lstANCHist.add((ANCHistoryDetailVO) valueObjects[i]);
			}
		}
		catch (Exception e)
		{

			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		return lstANCHist;
	}

	/**
	 * Getting ANC History Detail
	 * @param _patCrNo Patient CR No
	 * @param _gravidaNo Gravida No
	 * @param _userVO User Detail
	 * @return ANC History Detail
	 */
	public ANCHistoryDetailVO get(String _patCrNo, String _gravidaNo, UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = InpatientConfig.QUERY_FILE_FOR_INPATIENT_DAO;
		String queryKey = "SELECT.ANC_HISTORY_DETAIL_BY_GRAVIDA.HANCT_ANC_HISTORY_DTL";
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		Sequence sq = new Sequence();
		try
		{
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _patCrNo);
			populateMAP.put(sq.next(), _gravidaNo);
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		}
		catch(Exception e)
		{
			throw new HisApplicationExecutionException("ANCHistoryDtlDAO.populateMAP::" + e);
		}

		ANCHistoryDetailVO ancHistVO = new ANCHistoryDetailVO();
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (rs.next())
				HelperMethods.populateVOfrmRS(ancHistVO, rs);
			else
				ancHistVO=null;
		}
		catch (Exception e)
		{

			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		return ancHistVO;
	}

	/**
	 * Create New ANC History Detail
	 * @param _ancHistDtlVO ANC History Detail VO
	 * @param _userVO User Detail
	 */
	public void create(ANCHistoryDetailVO _ancHistDtlVO, UserVO _userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = InpatientConfig.QUERY_FILE_FOR_INPATIENT_DAO;
		String queryKey = "INSERT.NEW_RECORD.HANCT_ANC_HISTORY_DTL";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		_ancHistDtlVO = setVOEmptyToNull(_ancHistDtlVO);
		populateMAP.put(sq.next(), _ancHistDtlVO.getPatCrNo());
		populateMAP.put(sq.next(), _ancHistDtlVO.getPatCrNo());
		populateMAP.put(sq.next(), _ancHistDtlVO.getGravidaNo());
		populateMAP.put(sq.next(), _ancHistDtlVO.getDeliveryDate());
		populateMAP.put(sq.next(), _ancHistDtlVO.getDeliveryStatus());
		populateMAP.put(sq.next(), _ancHistDtlVO.getPregnancyDuration());
		populateMAP.put(sq.next(), _ancHistDtlVO.getPregnancyRemarks());
		populateMAP.put(sq.next(), _ancHistDtlVO.getDeliveryPlaceId());
		populateMAP.put(sq.next(), _ancHistDtlVO.getLabourDuration());
		populateMAP.put(sq.next(), _ancHistDtlVO.getLabourRemarks());
		populateMAP.put(sq.next(), _ancHistDtlVO.getAncNo());
		populateMAP.put(sq.next(), _ancHistDtlVO.getGravidaNo());
		populateMAP.put(sq.next(), _ancHistDtlVO.getParityNo());
		populateMAP.put(sq.next(), _ancHistDtlVO.getAbortusNo());
		populateMAP.put(sq.next(), _ancHistDtlVO.getDeliveryTypeId());
		populateMAP.put(sq.next(), _ancHistDtlVO.getIsAntiDGiven());
		populateMAP.put(sq.next(), _ancHistDtlVO.getEpisodeCode());
		populateMAP.put(sq.next(), _ancHistDtlVO.getEpisodeVisitNo());
		populateMAP.put(sq.next(), _ancHistDtlVO.getAdmissionNo());
		populateMAP.put(sq.next(), _ancHistDtlVO.getEntryMode());
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), _userVO.getSeatId());
		populateMAP.put(sq.next(), _userVO.getHospitalCode());
		populateMAP.put(sq.next(), _userVO.getIpAddress());

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
	 * Updating ANC History Detail
	 * @param _ancHistDtlVO ANC History Detail VO
	 * @param _userVO User Detail
	 */
	public void update(ANCHistoryDetailVO _ancHistDtlVO, UserVO _userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = InpatientConfig.QUERY_FILE_FOR_INPATIENT_DAO;
		String queryKey = "UPDATE.HISTORY_RECORD.HANCT_ANC_HISTORY_DTL";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		_ancHistDtlVO = setVOEmptyToNull(_ancHistDtlVO);
		populateMAP.put(sq.next(), _ancHistDtlVO.getDeliveryDate());
		populateMAP.put(sq.next(), _ancHistDtlVO.getPregnancyDuration());
		populateMAP.put(sq.next(), _ancHistDtlVO.getPregnancyRemarks());
		populateMAP.put(sq.next(), _ancHistDtlVO.getDeliveryPlaceId());
		populateMAP.put(sq.next(), _ancHistDtlVO.getLabourDuration());
		populateMAP.put(sq.next(), _ancHistDtlVO.getLabourRemarks());
		populateMAP.put(sq.next(), _ancHistDtlVO.getDeliveryTypeId());
		populateMAP.put(sq.next(), _ancHistDtlVO.getIsAntiDGiven());
		populateMAP.put(sq.next(), _userVO.getSeatId());
		populateMAP.put(sq.next(), _ancHistDtlVO.getPatCrNo());
		populateMAP.put(sq.next(), _ancHistDtlVO.getGravidaNo());
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

	private ANCHistoryDetailVO setVOEmptyToNull(ANCHistoryDetailVO _vo)
	{
		if(_vo.getDeliveryDate()!=null && (_vo.getDeliveryDate().trim().equals("") || _vo.getDeliveryDate().equals("dd-mm-yyyy"))) _vo.setDeliveryDate(null);
		if(_vo.getDeliveryPlaceId()!=null && _vo.getDeliveryPlaceId().trim().equals("-1")) _vo.setDeliveryPlaceId(null);
		if(_vo.getDeliveryTypeId()!=null && _vo.getDeliveryTypeId().trim().equals("-1")) _vo.setDeliveryTypeId(null);
		if(_vo.getIsAntiDGiven()!=null && _vo.getIsAntiDGiven().trim().equals("-1")) _vo.setIsAntiDGiven(null);
		if(_vo.getLabourDuration()!=null && _vo.getLabourDuration().trim().equals("")) _vo.setLabourDuration(null);
		
		if(_vo.getParityNo()==null || _vo.getParityNo().trim().equals(""))	_vo.setParityNo("0");
		if(_vo.getAbortusNo()==null || _vo.getAbortusNo().trim().equals(""))	_vo.setAbortusNo("0");
		
		return _vo;
	}
}
