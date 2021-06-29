package opd.dao;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import opd.OpdConfig;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.exceptions.HisUpdateUnsuccesfullException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.TransactionContext;
import hisglobal.utility.Sequence;
import hisglobal.vo.ConsentRequestVO;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.UserVO;

public class EpisodeConsentDtlDAO extends DataAccessObject 
{
	public  EpisodeConsentDtlDAO(TransactionContext _tx) 
	{
		super(_tx);
	}
	
	public void updateStatus(ConsentRequestVO consentRequestVO,UserVO _userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		String fileName = OpdConfig.QUERY_FILE_FOR_OPD_DAO;
		String queryKey = "UPDATE.CONSENT_STATUS.HGBT_CONSENT_DTL";

		try
		{
			query = HelperMethodsDAO.getQuery(fileName, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		try
		{
			Sequence sq = new Sequence();
			
			populateMAP.put(sq.next(), OpdConfig.CONSENT_RECEIVED);
			populateMAP.put(sq.next(), _userVO.getSeatId());//received By
			///
			populateMAP.put(sq.next(), consentRequestVO.getGivenBy());
			populateMAP.put(sq.next(), consentRequestVO.getRelativeName());
			populateMAP.put(sq.next(), consentRequestVO.getRelativeAddr());
			populateMAP.put(sq.next(), consentRequestVO.getRelativeIdRemark());
			populateMAP.put(sq.next(), consentRequestVO.getRelationCode());
			populateMAP.put(sq.next(), consentRequestVO.getRelativeContactNo());
			////
			//populateMAP.put(sq.next(), consentRequestVO.getTemplateId());
			populateMAP.put(sq.next(), consentRequestVO.getPatCrNo());
			populateMAP.put(sq.next(), consentRequestVO.getEpisodeCode());
			populateMAP.put(sq.next(), consentRequestVO.getEpisodeVisitNo());
			////
			populateMAP.put(sq.next(), consentRequestVO.getRequestID());
			populateMAP.put(sq.next(), consentRequestVO.getServiceTypeId());
			populateMAP.put(sq.next(), consentRequestVO.getServiceId());
			/////
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(),_userVO.getHospitalCode() );
			
			populateMAP.put(sq.next(), consentRequestVO.getServiceConsentId());
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("EpisodeDAO:updateIsEpisodeOpen::" + e);
		}
		try
		{
			HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), query, populateMAP);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisUpdateUnsuccesfullException("HelperMethodsDAO.getResultSet" + e);
		}
	}
	
	public String getNoOfNewConsentRequest(ConsentRequestVO _consentVO,String hospitalCode)
	{
		String query = "";
		ResultSet rs;
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_DAO;
		String queryKey = "SELECT.NOOFREQUESTSTATUS.HGBT_CONSENT_DTL";
		
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		
		populateMAP.put(sq.next(), _consentVO.getPatCrNo());
		populateMAP.put(sq.next(), _consentVO.getEpisodeCode());
		populateMAP.put(sq.next(), _consentVO.getEpisodeVisitNo());
		populateMAP.put(sq.next(), OpdConfig.CONSENT_REQUEST);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), hospitalCode);
		
		
		
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if(!rs.next())
			{
				throw new HisRecordNotFoundException("No Consent Is Here");
			}
			return rs.getString(1);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.executeQuery(" + e);
		}
		
		
	}
	
	public PatientDetailVO getPatInfoByCrNoAndTodayVisit(ConsentRequestVO _consentVO,UserVO _userVO)
	{
		String query = "";
		ResultSet rs;
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_DAO;
		String queryKey = "SELECT.PATINFOBYCRNO.HRGT_EPISODE_DTL";
		PatientDetailVO patientDetailVO=new PatientDetailVO();
		
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		
		populateMAP.put(sq.next(), _consentVO.getPatCrNo());
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), _userVO.getHospitalCode());
		
		
		
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if(!rs.next())
			{
				throw new HisRecordNotFoundException("No Pending Consent Request Exists");
			}
			
			patientDetailVO.setEpisodeCode(rs.getString(1));
			patientDetailVO.setEpisodeVisitNo(rs.getString(2));
			return patientDetailVO;
		}
		catch (Exception e)
		{
			throw new HisRecordNotFoundException("No Pending Consent Request Exists");
		}
		
		
	}
	

}
