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
import hisglobal.vo.ANCHistoryDeliveryDetailVO;
import hisglobal.vo.UserVO;
import hisglobal.vo.ValueObject;

public class ANCHistoryDeliveryDtlDAO extends DataAccessObject implements ANCHistoryDeliveryDtlDAOi
{
	public ANCHistoryDeliveryDtlDAO(TransactionContext _tx)
	{
		super(_tx);
	}

	/**
	 * Getting ANC History Delivery Detail 
	 * @param _patCrNo Patient Cr No
	 * @param _userVO User Detail
	 * @return List of ANC History
	 */
	public List<ANCHistoryDeliveryDetailVO> getANCHistoryDeliveryDetail(String _patCrNo, UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = InpatientConfig.QUERY_FILE_FOR_INPATIENT_DAO;
		String queryKey = "SELECT.ANC_HISTORY_DELIVERY_DETAIL.HANCT_ANC_HISTORY_DELIVERY_DTL";
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
			throw new HisApplicationExecutionException("ANCHistoryDeliveryDtlDAO.populateMAP::" + e);
		}

		List<ANCHistoryDeliveryDetailVO> lstANCHistDelivery = new ArrayList<ANCHistoryDeliveryDetailVO>();
		ValueObject[] valueObjects = null;
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if(rs.next())
			{
				rs.beforeFirst();
				valueObjects = HelperMethods.populateVOfrmRS(ANCHistoryDeliveryDetailVO.class, rs);
				for (int i = 0; i < valueObjects.length; i++)
					lstANCHistDelivery.add((ANCHistoryDeliveryDetailVO) valueObjects[i]);
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
		return lstANCHistDelivery;
	}

	/**
	 * Getting ANC History Delivery Detail
	 * @param _patCrNo Patient CR No
	 * @param _gravidaNo Gravida No
	 * @param _userVO User Detail
	 * @return ANC History Delivery Detail
	 */
	public ANCHistoryDeliveryDetailVO get(String _patCrNo, String _gravidaNo, UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = InpatientConfig.QUERY_FILE_FOR_INPATIENT_DAO;
		String queryKey = "SELECT.ANC_HISTORY_DELIVERY_DETAIL_BY_GRAVIDA.HANCT_ANC_HISTORY_DELIVERY_DTL";
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
			throw new HisApplicationExecutionException("ANCHistoryDeliveryDtlDAO.populateMAP::" + e);
		}

		ANCHistoryDeliveryDetailVO ancHistDelivery = new ANCHistoryDeliveryDetailVO();
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (rs.next())
				HelperMethods.populateVOfrmRS(ancHistDelivery, rs);
			else
				ancHistDelivery=null;
		}
		catch (Exception e)
		{

			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		return ancHistDelivery;
	}

	/**
	 * Create New ANC History Delivery Detail
	 * @param _ancHistDtlVO ANC History Delivery Detail VO
	 * @param _userVO User Detail
	 */
	public void create(ANCHistoryDeliveryDetailVO _ancHistDtlVO, UserVO _userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = InpatientConfig.QUERY_FILE_FOR_INPATIENT_DAO;
		String queryKey = "INSERT.NEW_RECORD.HANCT_ANC_HISTORY_DELIVERY_DTL";

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
		populateMAP.put(sq.next(), _ancHistDtlVO.getGravidaNo());
		populateMAP.put(sq.next(), _ancHistDtlVO.getPatCrNo());
		populateMAP.put(sq.next(), _ancHistDtlVO.getGravidaNo());
		populateMAP.put(sq.next(), _ancHistDtlVO.getBabyBloodGroupCode());		
		populateMAP.put(sq.next(), _ancHistDtlVO.getGenderCode());
		populateMAP.put(sq.next(), _ancHistDtlVO.getChildCrNo());
		populateMAP.put(sq.next(), _ancHistDtlVO.getBirthNatureId());
		populateMAP.put(sq.next(), _ancHistDtlVO.getWeight());
		populateMAP.put(sq.next(), _ancHistDtlVO.getDeathCause());
		populateMAP.put(sq.next(), _ancHistDtlVO.getDeathAge());
		populateMAP.put(sq.next(), _ancHistDtlVO.getPresentHealth());
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
	 * Update ANC History Delivery Detail
	 * @param _ancHistDtlVO ANC History Delivery Detail VO
	 * @param _userVO User Detail
	 */
	public void update(ANCHistoryDeliveryDetailVO _ancHistDtlVO, UserVO _userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = InpatientConfig.QUERY_FILE_FOR_INPATIENT_DAO;
		String queryKey = "UPDATE.HISTORY_DELIVERY_RECORD.HANCT_ANC_HISTORY_DELIVERY_DTL";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		_ancHistDtlVO = setVOEmptyToNull(_ancHistDtlVO);
		populateMAP.put(sq.next(), _ancHistDtlVO.getBabyBloodGroupCode());		
		populateMAP.put(sq.next(), _ancHistDtlVO.getGenderCode());
		populateMAP.put(sq.next(), _ancHistDtlVO.getChildCrNo());
		populateMAP.put(sq.next(), _ancHistDtlVO.getWeight());
		populateMAP.put(sq.next(), _ancHistDtlVO.getDeathCause());
		populateMAP.put(sq.next(), _ancHistDtlVO.getDeathAge());
		populateMAP.put(sq.next(), _ancHistDtlVO.getPresentHealth());
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

	private ANCHistoryDeliveryDetailVO setVOEmptyToNull(ANCHistoryDeliveryDetailVO _vo)
	{
		if(_vo.getBabyBloodGroupCode()!=null && _vo.getBabyBloodGroupCode().trim().equals("-1")) _vo.setBabyBloodGroupCode(null);
		if(_vo.getGenderCode()!=null && _vo.getGenderCode().trim().equals("-1")) _vo.setGenderCode(null);
		return _vo;
	}
}
