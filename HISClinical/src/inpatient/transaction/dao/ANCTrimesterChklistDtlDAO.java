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
import hisglobal.vo.ANCChecklistDetailVO;
import hisglobal.vo.UserVO;
import hisglobal.vo.ValueObject;

public class ANCTrimesterChklistDtlDAO extends DataAccessObject implements ANCTrimesterChklistDtlDAOi
{
	public ANCTrimesterChklistDtlDAO(TransactionContext _tx)
	{
		super(_tx);
	}

	/**
	 * Insert ANC Trimester Drug Checklist Detail
	 * @param _ancChklistDtlVO ANC CheckList Detail VO
	 * @param _userVO User Detail
	 */
	public void createDrugChklst(ANCChecklistDetailVO _ancChklistDtlVO, UserVO _userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = InpatientConfig.QUERY_FILE_FOR_INPATIENT_DAO;
		String queryKey = "INSERT.DRUG_CHKLST.HANCT_ANC_CHECKLIST_DTL";

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
			_ancChklistDtlVO = setVOEmptyToNull(_ancChklistDtlVO);
			populateMAP.put(sq.next(), _ancChklistDtlVO.getPatCrNo());
			populateMAP.put(sq.next(), _ancChklistDtlVO.getGravidaNo());
			populateMAP.put(sq.next(), _ancChklistDtlVO.getTrimester());
			populateMAP.put(sq.next(), _ancChklistDtlVO.getPatCrNo());
			populateMAP.put(sq.next(), _ancChklistDtlVO.getGravidaNo());
			populateMAP.put(sq.next(), _ancChklistDtlVO.getTrimester());
			populateMAP.put(sq.next(), _ancChklistDtlVO.getAncNo());
			populateMAP.put(sq.next(), _ancChklistDtlVO.getEpisodeCode());
			populateMAP.put(sq.next(), _ancChklistDtlVO.getEpisodeVisitNo());
			populateMAP.put(sq.next(), _ancChklistDtlVO.getAdmissionNo());
			populateMAP.put(sq.next(), _ancChklistDtlVO.getConductDate());
			populateMAP.put(sq.next(), _ancChklistDtlVO.getGestationStartDate());
			populateMAP.put(sq.next(), _ancChklistDtlVO.getChecklistId());
			populateMAP.put(sq.next(), _ancChklistDtlVO.getConductDate());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _userVO.getSeatId());
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), _userVO.getIpAddress());
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("ANCTrimesterChklistDtlDAO.populateMAP::" + e);
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
	 * Insert ANC Trimester Investigation Checklist Detail
	 * @param _ancChklistDtlVO ANC CheckList Detail VO
	 * @param _userVO User Detail
	 */
	public void createInvstChklst(ANCChecklistDetailVO _ancChklistDtlVO, UserVO _userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = InpatientConfig.QUERY_FILE_FOR_INPATIENT_DAO;
		String queryKey = "INSERT.INVST_CHKLST.HANCT_ANC_CHECKLIST_DTL";

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
			_ancChklistDtlVO = setVOEmptyToNull(_ancChklistDtlVO);
			populateMAP.put(sq.next(), _ancChklistDtlVO.getPatCrNo());
			populateMAP.put(sq.next(), _ancChklistDtlVO.getGravidaNo());
			populateMAP.put(sq.next(), _ancChklistDtlVO.getTrimester());
			populateMAP.put(sq.next(), _ancChklistDtlVO.getPatCrNo());
			populateMAP.put(sq.next(), _ancChklistDtlVO.getGravidaNo());
			populateMAP.put(sq.next(), _ancChklistDtlVO.getTrimester());
			populateMAP.put(sq.next(), _ancChklistDtlVO.getAncNo());
			populateMAP.put(sq.next(), _ancChklistDtlVO.getEpisodeCode());
			populateMAP.put(sq.next(), _ancChklistDtlVO.getEpisodeVisitNo());
			populateMAP.put(sq.next(), _ancChklistDtlVO.getAdmissionNo());
			populateMAP.put(sq.next(), _ancChklistDtlVO.getConductDate());
			populateMAP.put(sq.next(), _ancChklistDtlVO.getGestationStartDate());
			populateMAP.put(sq.next(), _ancChklistDtlVO.getChecklistId());
			populateMAP.put(sq.next(), _ancChklistDtlVO.getConductDate());
			populateMAP.put(sq.next(), _ancChklistDtlVO.getResult());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _userVO.getSeatId());
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), _userVO.getIpAddress());
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("ANCTrimesterChklistDtlDAO.populateMAP::" + e);
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

	private ANCChecklistDetailVO setVOEmptyToNull(ANCChecklistDetailVO _vo)
	{
		if(_vo.getAdmissionNo()!=null && _vo.getAdmissionNo().trim().equals("")) _vo.setAdmissionNo(null);
		return _vo;
	}
	
	/**
	 * Getting Checklist Type Wise ANC Checklist Detail
	 * @param _ancChklistDtlVO ANC CheckList Detail VO
	 * @param _type Checklist Type
	 * @param _userVO User Detail
	 * @return List of Chechlist Detail
	 */
	public List<ANCChecklistDetailVO> getChecklistDtlTypeWise(ANCChecklistDetailVO _ancChklistDtlVO, String _type, UserVO _userVO)
	{
		String query = "";
		ResultSet rs = null;
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = InpatientConfig.QUERY_FILE_FOR_INPATIENT_DAO;
		String queryKey = "SELECT.TYPE_WISE.HANCT_ANC_CHECKLIST_DTL";

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
			populateMAP.put(sq.next(), _ancChklistDtlVO.getPatCrNo());
			populateMAP.put(sq.next(), _ancChklistDtlVO.getGravidaNo());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), _type);
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("ANCTrimesterChklistDtlDAO.populateMAP::" + e);
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
		
		List<ANCChecklistDetailVO> lstChklstDtl = new ArrayList<ANCChecklistDetailVO>();
		ValueObject vo[] = null;
		try
		{
			if (rs.next())
			{
				rs.beforeFirst();
				vo = HelperMethods.populateVOfrmRS(ANCChecklistDetailVO.class, rs);			
				for (ValueObject v : vo)
					lstChklstDtl.add((ANCChecklistDetailVO)v);
			}
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HisDataAccessException  :get" + e);
		}
		return lstChklstDtl;
	}
}
