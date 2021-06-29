package opd.dao;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import opd.OpdConfig;

import hisglobal.persistence.TransactionContext;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.Sequence;

import hisglobal.vo.ProfileReviewDtlVO;
import hisglobal.vo.UserVO;
import hisglobal.vo.ValueObject;

public class ProfileReviewDtlDAO extends DataAccessObject implements ProfileReviewDtlDAOi 
{
	
	public ProfileReviewDtlDAO(TransactionContext _tx)
	{
		super(_tx);
	}

	/** 
	 * Saving Patient Profile
	 * @param _patProfileDtlVO Patient Detail VO
	 * @param _userVO User VO
	 * @return Generated Profile Id 
	 */
	
	public void create(ProfileReviewDtlVO _profileReviewDtlVO, UserVO userVO)
	{
		String query  = "";
	    Map populateMAP =new HashMap();
	    Sequence sq=new Sequence();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_DAO;
		String queryKey = "INSERT.HPMRT_PROFILE_REVIEW_DTL";
	    
	   
	    try
	    {
	    	query =HelperMethodsDAO.getQuery(filename,queryKey);
	    }
	    catch(Exception e)
	    {
	    	throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);
	    }
	    
	    try
	    {
	    	populateMAP.put(sq.next(), _profileReviewDtlVO.getPatCrNo());
			populateMAP.put(sq.next(), _profileReviewDtlVO.getPatCrNo());
			populateMAP.put(sq.next(), _profileReviewDtlVO.getAdmissionNo());
			populateMAP.put(sq.next(), _profileReviewDtlVO.getProfileId());
			populateMAP.put(sq.next(), userVO.getUserEmpID());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), userVO.getSeatId());
   			populateMAP.put(sq.next(), userVO.getHospitalCode());
			populateMAP.put(sq.next(), _profileReviewDtlVO.getRemarks());
			populateMAP.put(sq.next(), userVO.getIpAddress());  	
	    }
	    catch(Exception e)
	    {
	    	throw new HisApplicationExecutionException("ProfileReviewDtlDAO.populateMAP::"+e);
	    }
	    try
		{
			HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), query, populateMAP);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
	}
	
	
	public ProfileReviewDtlVO[] fetchProfileReviewDetails(String _crNo,String profileId,UserVO _userVO)
	{
		
		ProfileReviewDtlVO[] profileReviewDtlVOs;
		String query = "";
		Map _populateMap = new HashMap();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_DAO;
		String queryKey = "SELECT_PREVIOUS_PROFILE.HPMRT_PROFILE_REVIEW_DTL";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::" + e);
		}

		Sequence sq = new Sequence();
		try
		{
			_populateMap.put(sq.next(), _crNo);
			_populateMap.put(sq.next(), profileId);
			_populateMap.put(sq.next(), _userVO.getHospitalCode());
			_populateMap.put(sq.next(), Config.IS_VALID_ACTIVE);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("PatientDAO.populateMAP::" + e);
		}

		ValueObject[] vo = {};
		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, _populateMap);
			if (!rs.next())
			{
				//throw new HisRecordNotFoundException("No Profile Detail Found");
			}
			rs.beforeFirst();
			vo = HelperMethods.populateVOfrmRS(ProfileReviewDtlVO.class, rs);
			profileReviewDtlVOs = new ProfileReviewDtlVO[vo.length];
			for (int i = 0; i < vo.length; i++)
			{
				profileReviewDtlVOs[i] = (ProfileReviewDtlVO) vo[i];
			}
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("EpisodeDAO:retrieveByCrNo::Episode Details:: " + e);
		}
		return profileReviewDtlVOs;
	}

}
