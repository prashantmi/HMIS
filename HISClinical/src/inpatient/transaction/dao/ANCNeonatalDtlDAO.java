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
import hisglobal.vo.ANCNeonatalDetailVO;
import hisglobal.vo.UserVO;
import hisglobal.vo.ValueObject;

public class ANCNeonatalDtlDAO extends DataAccessObject implements ANCNeonatalDtlDAOi
{
	public ANCNeonatalDtlDAO(TransactionContext _tx)
	{
		super(_tx);
	}

	/**
	 * Create Initial Entry for ANC Neonatal Detail
	 * @param _ancHistDtlVO ANC History Delivery Detail VO
	 * @param _userVO User Detail
	 */
	public void create(ANCNeonatalDetailVO _ancNeonatalDtlVO, UserVO _userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = InpatientConfig.QUERY_FILE_FOR_INPATIENT_DAO;
		String queryKey = "INSERT.INITIAL_RECORD.HANCT_NEWNATAL_DTL";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		_ancNeonatalDtlVO = setVOEmptyToNull(_ancNeonatalDtlVO);
		populateMAP.put(sq.next(), _ancNeonatalDtlVO.getPatCrNo());
		populateMAP.put(sq.next(), _ancNeonatalDtlVO.getGravidaNo());
		populateMAP.put(sq.next(), _ancNeonatalDtlVO.getPatCrNo());
		populateMAP.put(sq.next(), _ancNeonatalDtlVO.getGravidaNo());
		populateMAP.put(sq.next(), _ancNeonatalDtlVO.getBabyBloodGroupCode());		
		populateMAP.put(sq.next(), _ancNeonatalDtlVO.getGenderCode());
		populateMAP.put(sq.next(), _ancNeonatalDtlVO.getBirthDateTime());
		populateMAP.put(sq.next(), _ancNeonatalDtlVO.getBirthNatureId());
		populateMAP.put(sq.next(), _ancNeonatalDtlVO.getWeight());
		populateMAP.put(sq.next(), _ancNeonatalDtlVO.getDeathStillBirthCause());
		populateMAP.put(sq.next(), _ancNeonatalDtlVO.getDeathAge());
		populateMAP.put(sq.next(), _ancNeonatalDtlVO.getPresentHealth());
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
	 * Getting Last Delivered New Nats Detail
	 * @param _ancNeonatalDtlVO
	 * @param _userVO User Detail
	 * @return List of New Nats
	 */
	public List<ANCNeonatalDetailVO> getLastAllNeoNatDtl(ANCNeonatalDetailVO _ancNeonatalDtlVO, UserVO _userVO)
	{
		String query = "";
		ResultSet rs = null;
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = InpatientConfig.QUERY_FILE_FOR_INPATIENT_DAO;
		String queryKey = "SELECT.LAST_DEL_NEW_NATS.HANCT_NEWNATAL_DTL";

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
			populateMAP.put(sq.next(), _ancNeonatalDtlVO.getPatCrNo());
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
		List<ANCNeonatalDetailVO> lstANCNeoNats = new ArrayList<ANCNeonatalDetailVO>();
		try
		{
			if(rs.next())
			{
				rs.beforeFirst();
				vo=HelperMethods.populateVOfrmRS(ANCNeonatalDetailVO.class, rs);
				for(ValueObject v : vo)
					lstANCNeoNats.add((ANCNeonatalDetailVO)v);
			}
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HisDataAccessException  :getAllDepartment" + e);
		}
		return lstANCNeoNats;
	}

	/**
	 * Update ANC New Natal Detail
	 * @param _ancNeonatalDtlVO ANC Detail VO
	 * @param _userVO User Detail
	 * @return
	 */
	public void update(ANCNeonatalDetailVO _ancNeonatalDtlVO, UserVO _userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = InpatientConfig.QUERY_FILE_FOR_INPATIENT_DAO;
		String queryKey = "UPDATE.RECORD.HANCT_NEWNATAL_DTL";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		_ancNeonatalDtlVO = setVOEmptyToNull(_ancNeonatalDtlVO);
		populateMAP.put(sq.next(), _ancNeonatalDtlVO.getBabyBloodGroupCode());
		populateMAP.put(sq.next(), _ancNeonatalDtlVO.getGenderCode());
		//populateMAP.put(sq.next(), _ancNeonatalDtlVO.getChildCrNo());
		//populateMAP.put(sq.next(), _ancNeonatalDtlVO.getBirthDateTime());
		//populateMAP.put(sq.next(), _ancNeonatalDtlVO.getBirthNatureId());
		populateMAP.put(sq.next(), _ancNeonatalDtlVO.getWeight());
		populateMAP.put(sq.next(), _ancNeonatalDtlVO.getDeathStillBirthCause());
		populateMAP.put(sq.next(), _ancNeonatalDtlVO.getDeathAge());
		//populateMAP.put(sq.next(), _ancNeonatalDtlVO.getChildAdmissionNo());
		//populateMAP.put(sq.next(), _ancNeonatalDtlVO.getPeaditricianEmpId());
		populateMAP.put(sq.next(), _ancNeonatalDtlVO.getPresentHealth());
		populateMAP.put(sq.next(), _ancNeonatalDtlVO.getBabylength());
		populateMAP.put(sq.next(), _ancNeonatalDtlVO.getIsAnomolyPresent());
		populateMAP.put(sq.next(), _ancNeonatalDtlVO.getAnomolyRemarks());
		populateMAP.put(sq.next(), _ancNeonatalDtlVO.getUmbilicalArteries());
		populateMAP.put(sq.next(), _ancNeonatalDtlVO.getTransferToFlag());		
		populateMAP.put(sq.next(), _ancNeonatalDtlVO.getIsFootPrintTaken());
		populateMAP.put(sq.next(), _ancNeonatalDtlVO.getLiquorAppearance());		
		populateMAP.put(sq.next(), _ancNeonatalDtlVO.getIsCordBloodSampleTaken());		
		populateMAP.put(sq.next(), _ancNeonatalDtlVO.getHeadCircumferences());		
		populateMAP.put(sq.next(), _ancNeonatalDtlVO.getCryDateTime());
		populateMAP.put(sq.next(), _ancNeonatalDtlVO.getUrineDateTime());
		populateMAP.put(sq.next(), _ancNeonatalDtlVO.getMotherFeedDateTime());
		populateMAP.put(sq.next(), _ancNeonatalDtlVO.getFeedDateTime());
		populateMAP.put(sq.next(), _ancNeonatalDtlVO.getIsBirthTrauma());		
		populateMAP.put(sq.next(), _ancNeonatalDtlVO.getBirthTraumaRemarks());
		populateMAP.put(sq.next(), _ancNeonatalDtlVO.getTraumaCauseId());
		populateMAP.put(sq.next(), _ancNeonatalDtlVO.getWhenStillBirthDetection());		
		populateMAP.put(sq.next(), _ancNeonatalDtlVO.getAnomolyTypeId());		
		populateMAP.put(sq.next(), _userVO.getSeatId());		
		populateMAP.put(sq.next(), _ancNeonatalDtlVO.getPatCrNo());
		populateMAP.put(sq.next(), _ancNeonatalDtlVO.getGravidaNo());
		populateMAP.put(sq.next(), _ancNeonatalDtlVO.getSlNo());
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

	private ANCNeonatalDetailVO setVOEmptyToNull(ANCNeonatalDetailVO _vo)
	{
		if(_vo.getBabyBloodGroupCode()!=null && _vo.getBabyBloodGroupCode().trim().equals("-1")) _vo.setBabyBloodGroupCode(null);
		if(_vo.getGenderCode()!=null && _vo.getGenderCode().trim().equals("-1")) _vo.setGenderCode(null);
		if(_vo.getWeight()!=null && _vo.getWeight().trim().equals("")) _vo.setWeight(null);
		if(_vo.getDeathAge()!=null && _vo.getDeathAge().trim().equals("")) _vo.setDeathAge(null);
		if(_vo.getBabylength()!=null && _vo.getBabylength().trim().equals("")) _vo.setBabylength(null);
		if(_vo.getIsAnomolyPresent()!=null && _vo.getIsAnomolyPresent().trim().equals("")) _vo.setIsAnomolyPresent(null);
		if(_vo.getUmbilicalArteries()!=null && _vo.getUmbilicalArteries().trim().equals("-1")) _vo.setUmbilicalArteries(null);
		if(_vo.getTransferToFlag()!=null && _vo.getTransferToFlag().trim().equals("")) _vo.setTransferToFlag(null);
		if(_vo.getIsFootPrintTaken()!=null && _vo.getIsFootPrintTaken().trim().equals("")) _vo.setIsFootPrintTaken(null);
		if(_vo.getLiquorAppearance()!=null && _vo.getLiquorAppearance().trim().equals("")) _vo.setLiquorAppearance(null);
		if(_vo.getIsCordBloodSampleTaken()!=null && _vo.getIsCordBloodSampleTaken().trim().equals("")) _vo.setIsCordBloodSampleTaken(null);
		if(_vo.getHeadCircumferences()!=null && _vo.getHeadCircumferences().trim().equals("")) _vo.setHeadCircumferences(null);
		if(_vo.getCryDateTime()!=null && _vo.getCryDateTime().trim().equals("")) _vo.setCryDateTime(null);
		if(_vo.getFeedDateTime()!=null && _vo.getFeedDateTime().trim().equals("")) _vo.setFeedDateTime(null);
		if(_vo.getUrineDateTime()!=null && _vo.getUrineDateTime().trim().equals("")) _vo.setUrineDateTime(null);
		if(_vo.getMotherFeedDateTime()!=null && _vo.getMotherFeedDateTime().trim().equals("")) _vo.setMotherFeedDateTime(null);
		if(_vo.getIsBirthTrauma()!=null && _vo.getIsBirthTrauma().trim().equals("")) _vo.setIsBirthTrauma(null);
		if(_vo.getTraumaCauseId()!=null && _vo.getTraumaCauseId().trim().equals("-1")) _vo.setTraumaCauseId(null);
		if(_vo.getWhenStillBirthDetection()!=null && _vo.getWhenStillBirthDetection().trim().equals("")) _vo.setWhenStillBirthDetection(null);
		if(_vo.getAnomolyTypeId()!=null && _vo.getAnomolyTypeId().trim().equals("-1")) _vo.setAnomolyTypeId(null);
		return _vo;
	}
	
	public void updateNewnatalDeath(String deathDate, String childCrNo, UserVO userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = InpatientConfig.QUERY_FILE_FOR_INPATIENT_DAO;
		String queryKey = "UPDATE.NEWNATAL_DEATH.HANCT_NEWNATAL_DTL";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		populateMAP.put(sq.next(), InpatientConfig.BIRTH_NATURE_NEONATAL_DEATH);
		populateMAP.put(sq.next(), deathDate);
		populateMAP.put(sq.next(), childCrNo);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), userVO.getHospitalCode());
		
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
	 * Getting Child Neo Nat Detail By Pat Crno
	 * @param _ancNeonatalDtlVO
	 * @param _userVO User Detail
	 * @return 
	 */
	public ANCNeonatalDetailVO getNeoNatDtlByChild(ANCNeonatalDetailVO _ancNeonatalDtlVO, UserVO _userVO)
	{
		String query = "";
		ResultSet rs = null;
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = InpatientConfig.QUERY_FILE_FOR_INPATIENT_DAO;
		String queryKey = "SELECT.NEO_NAT_DTL_BY_CHILD.HANCT_NEWNATAL_DTL";

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
			populateMAP.put(sq.next(), _ancNeonatalDtlVO.getChildCrNo());
			populateMAP.put(sq.next(), _ancNeonatalDtlVO.getChildAdmissionNo());
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
		
		ANCNeonatalDetailVO voANCNeoNats = null;
		try
		{
			if(rs.next())
			{
				voANCNeoNats = new ANCNeonatalDetailVO();
				HelperMethods.populateVOfrmRS(voANCNeoNats, rs);
			}
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HisDataAccessException  :getNeoNatDtlByChild" + e);
		}
		return voANCNeoNats;
	}
}
