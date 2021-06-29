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
import hisglobal.vo.ANCNeonatalApgarVO;
import hisglobal.vo.UserVO;
import hisglobal.vo.ValueObject;

public class ANCNeonatalApgarDtlDAO extends DataAccessObject implements ANCNeonatalApgarDtlDAOi
{
	public ANCNeonatalApgarDtlDAO(TransactionContext _tx)
	{
		super(_tx);
	}

	/**
	 * Create Apgar Detail Record
	 * @param _ancNeonatalApgarDtlVO Apgar Detail
	 * @param _userVO User Detail
	 */
	public void create(ANCNeonatalApgarVO _ancNeonatalApgarDtlVO, UserVO _userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = InpatientConfig.QUERY_FILE_FOR_INPATIENT_DAO;
		String queryKey = "INSERT.RECORD.HANCT_APGAR_DTL";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		_ancNeonatalApgarDtlVO = setVOEmptyToNull(_ancNeonatalApgarDtlVO);
		populateMAP.put(sq.next(), _ancNeonatalApgarDtlVO.getPatCrNo());
		populateMAP.put(sq.next(), _ancNeonatalApgarDtlVO.getGravidaNo());
		populateMAP.put(sq.next(), _ancNeonatalApgarDtlVO.getSlNo());
		populateMAP.put(sq.next(), _ancNeonatalApgarDtlVO.getPatCrNo());
		populateMAP.put(sq.next(), _ancNeonatalApgarDtlVO.getGravidaNo());
		populateMAP.put(sq.next(), _ancNeonatalApgarDtlVO.getSlNo());
		populateMAP.put(sq.next(), _ancNeonatalApgarDtlVO.getChildCrNo());		
		populateMAP.put(sq.next(), _ancNeonatalApgarDtlVO.getChildAdmissionNo());
		populateMAP.put(sq.next(), _ancNeonatalApgarDtlVO.getPeaditricianEmpId());
		populateMAP.put(sq.next(), _ancNeonatalApgarDtlVO.getHeartRateApgar());
		populateMAP.put(sq.next(), _ancNeonatalApgarDtlVO.getHeartRate());
		populateMAP.put(sq.next(), _ancNeonatalApgarDtlVO.getRespirationApgar());
		populateMAP.put(sq.next(), _ancNeonatalApgarDtlVO.getRespiration());
		populateMAP.put(sq.next(), _ancNeonatalApgarDtlVO.getColorApgar());
		populateMAP.put(sq.next(), _ancNeonatalApgarDtlVO.getColor());
		populateMAP.put(sq.next(), _ancNeonatalApgarDtlVO.getActivityApgar());
		populateMAP.put(sq.next(), _ancNeonatalApgarDtlVO.getActivity());
		populateMAP.put(sq.next(), _ancNeonatalApgarDtlVO.getGrimaceApgar());
		populateMAP.put(sq.next(), _ancNeonatalApgarDtlVO.getGrimace());
		populateMAP.put(sq.next(), _ancNeonatalApgarDtlVO.getApgarScore());
		populateMAP.put(sq.next(), _ancNeonatalApgarDtlVO.getApgarTime());
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
	 * Getting List of All Apgar Details of Given Mother Gravida 
	 * @param _ancNeonatalApgarDtlVO Neo Nat Detail
	 * @param _userVO User Detail
	 * @return List of Apgar Scores
	 */
	public List<ANCNeonatalApgarVO> getAllNeoNatApgarDtl(ANCNeonatalApgarVO _ancNeonatalApgarDtlVO, UserVO _userVO)
	{
		String query = "";
		ResultSet rs = null;
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = InpatientConfig.QUERY_FILE_FOR_INPATIENT_DAO;
		String queryKey = "SELECT.ALL_NEW_NAT_APGARS.HANCT_APGAR_DTL";

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
			populateMAP.put(sq.next(), _ancNeonatalApgarDtlVO.getPatCrNo());
			populateMAP.put(sq.next(), _ancNeonatalApgarDtlVO.getGravidaNo());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("ANCNeonatalDtlDAO.populateMAP::" + e);
		}

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("Application Execution Exception" + e);
		}
		
		ValueObject[] vo={};
		List<ANCNeonatalApgarVO> lstApgars = new ArrayList<ANCNeonatalApgarVO>();
		try
		{
			if(rs.next())
			{
				rs.beforeFirst();
				vo=HelperMethods.populateVOfrmRS(ANCNeonatalApgarVO.class, rs);
				for(ValueObject v : vo)
					lstApgars.add((ANCNeonatalApgarVO)v);
			}
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HisDataAccessException  :getAllNeoNatApgarDtl" + e);
		}
		return lstApgars;
	}

	private ANCNeonatalApgarVO setVOEmptyToNull(ANCNeonatalApgarVO _vo)
	{
		if(_vo.getChildCrNo()!=null && _vo.getChildCrNo().trim().equals("")) _vo.setChildCrNo(null);
		if(_vo.getChildAdmissionNo()!=null && _vo.getChildAdmissionNo().trim().equals("")) _vo.setChildAdmissionNo(null);
		if(_vo.getPeaditricianEmpId()!=null && _vo.getPeaditricianEmpId().trim().equals("")) _vo.setPeaditricianEmpId(null);
		return _vo;
	}
}
