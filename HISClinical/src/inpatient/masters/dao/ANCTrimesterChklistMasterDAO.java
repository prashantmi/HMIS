package inpatient.masters.dao;

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
import hisglobal.vo.ANCTrimesterChecklistMasterVO;
import hisglobal.vo.UserVO;
import hisglobal.vo.ValueObject;

public class ANCTrimesterChklistMasterDAO extends DataAccessObject implements ANCTrimesterChklistMasterDAOi
{
	public ANCTrimesterChklistMasterDAO(TransactionContext _tx)
	{
		super(_tx);
	}

	/**
	 * Insert Child Handover Detail
	 * @param _ancChildHandoverDtlVO ANC Child Handover Detail VO
	 * @param _userVO User Detail
	 */
	/*public void create(ANCChildHandoverDetailVO _ancChildHandoverDtlVO, UserVO _userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = InpatientConfig.QUERY_FILE_FOR_INPATIENT_DAO;
		String queryKey = "INSERT.RECORD.HANCT_NEWBORN_HANDOVER_DTL";

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
			_ancChildHandoverDtlVO = setVOEmptyToNull(_ancChildHandoverDtlVO);
			populateMAP.put(sq.next(), _ancChildHandoverDtlVO.getPatCrNo());
			populateMAP.put(sq.next(), _ancChildHandoverDtlVO.getPatCrNo());
			populateMAP.put(sq.next(), _ancChildHandoverDtlVO.getHandoverDateTime());

			populateMAP.put(sq.next(), _ancChildHandoverDtlVO.getRemarks());
			populateMAP.put(sq.next(), _ancChildHandoverDtlVO.getRelativeName());
			populateMAP.put(sq.next(), _ancChildHandoverDtlVO.getRelativeAddress());
			populateMAP.put(sq.next(), _ancChildHandoverDtlVO.getRelativeCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _userVO.getSeatId());
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), _userVO.getIpAddress());
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("ANCChildHandoverDtlDAO.populateMAP::" + e);
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
	}*/

	private ANCTrimesterChecklistMasterVO setVOEmptyToNull(ANCTrimesterChecklistMasterVO _vo)
	{
		return _vo;
	}
	
	/**
	 * Getting Child Handover Detail
	 * @param _ancNeonatalDtlVO ANC Child Handover Detail VO
	 * @param _userVO User Detail
	 * @return 
	 */
	/*public ANCChildHandoverDetailVO get(ANCChildHandoverDetailVO _ancChildHandoverDtlVO, UserVO _userVO)
	{
		String query = "";
		ResultSet rs = null;
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = InpatientConfig.QUERY_FILE_FOR_INPATIENT_DAO;
		String queryKey = "SELECT.RECORD.HANCT_NEWBORN_HANDOVER_DTL";

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
			populateMAP.put(sq.next(), _ancChildHandoverDtlVO.getPatCrNo());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("ANCChildHandoverDtlDAO.populateMAP::" + e);
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
		
		ANCChildHandoverDetailVO voANCChildHandover = null;
		try
		{
			if(rs.next())
			{
				voANCChildHandover = new ANCChildHandoverDetailVO();
				HelperMethods.populateVOfrmRS(voANCChildHandover, rs);
			}
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HisDataAccessException  :get" + e);
		}
		return voANCChildHandover;
	}*/


	/**
	 * Getting All Trimester Checklist Detail
	 * @param _userVO User Detail
	 * @return 
	 */
	public List<ANCTrimesterChecklistMasterVO> getAll(UserVO _userVO)
	{
		String query = "";
		ResultSet rs = null;
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = InpatientConfig.QUERY_FILE_FOR_INPATIENT_DAO;
		String queryKey = "SELECT.ALL_TRIMESTERWISE_CHKLIST.HANCT_TRIMESTER_CHECKLIST_MST";

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
			populateMAP.put(sq.next(), InpatientConfig.TRIMESTER_ONE);
			populateMAP.put(sq.next(), InpatientConfig.TRIMESTER_CHECKLIST_FLAG_MUST);
			populateMAP.put(sq.next(), InpatientConfig.TRIMESTER_CHECKLIST_FLAG_YES);
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), InpatientConfig.TRIMESTER_CHECKLIST_FLAG_NO);
			populateMAP.put(sq.next(), InpatientConfig.TRIMESTER_TWO);
			populateMAP.put(sq.next(), InpatientConfig.TRIMESTER_CHECKLIST_FLAG_MUST);
			populateMAP.put(sq.next(), InpatientConfig.TRIMESTER_CHECKLIST_FLAG_YES);
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), InpatientConfig.TRIMESTER_CHECKLIST_FLAG_NO);
			populateMAP.put(sq.next(), InpatientConfig.TRIMESTER_THREE);
			populateMAP.put(sq.next(), InpatientConfig.TRIMESTER_CHECKLIST_FLAG_MUST);
			populateMAP.put(sq.next(), InpatientConfig.TRIMESTER_CHECKLIST_FLAG_YES);
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), InpatientConfig.TRIMESTER_CHECKLIST_FLAG_NO);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("ANCTrimesterChklistDtlDAO.populateMAP::" + e);
		}

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if(!rs.next())
				throw new HisRecordNotFoundException("No Record Found for Trimester-Wise Checklist");
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("Application Execution Exception" + e);
		}
		
		List<ANCTrimesterChecklistMasterVO> lstTrimesterChklist = new ArrayList<ANCTrimesterChecklistMasterVO>();
		ValueObject vo[] = null;
		try
		{
			//if (rs.next())
			{
				rs.beforeFirst();
	
				vo = HelperMethods.populateVOfrmRS(ANCTrimesterChecklistMasterVO.class, rs);			
				for (ValueObject v : vo)
					lstTrimesterChklist.add((ANCTrimesterChecklistMasterVO)v);
			}
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HisDataAccessException  :getAll" + e);
		}
		return lstTrimesterChklist;
	}
}
