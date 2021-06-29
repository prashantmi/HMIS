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
import hisglobal.vo.PatientProfileDetailVO;
import hisglobal.vo.ProfileDietAdviceDtlVO;
import hisglobal.vo.UserVO;

public class ProfileDietAdviceDtlDAO extends DataAccessObject implements ProfileDietAdviceDtlDAOi
{
	
	public ProfileDietAdviceDtlDAO(TransactionContext _tx)
	{
		super(_tx);
	}

	/** 
	 * Saving Patient Profile Access Detail
	 * @param _profileAccessDtlVO Profile Access Detail
	 * @param _userVO User VO
	 */
	public void create(ProfileDietAdviceDtlVO _profileDietAdviceDtlVO, UserVO _userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_DAO;
		String queryKey = "INSERT.HPMRT_PROFILE_DIETADVICE_DTL";
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
			populateMAP.put(sq.next(), _profileDietAdviceDtlVO.getProfileId());
			populateMAP.put(sq.next(), _profileDietAdviceDtlVO.getDietTypeCode());
			populateMAP.put(sq.next(), _userVO.getSeatId());
			populateMAP.put(sq.next(), _profileDietAdviceDtlVO.getDays());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _profileDietAdviceDtlVO.getRemarks());
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
						
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("ProfileDietAdviceDtlDAO:populateMap::" + e);
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
	
	public ProfileDietAdviceDtlVO fetchDischargeDietAdviceDetails(PatientProfileDetailVO _patientProfileDtlVO,UserVO _userVO){
		{
			//DoctorCallBookVO callBookVO=new DoctorCallBookVO();
			//ProfileAccessPolicyVO profileAccessPolicyVO=new ProfileAccessPolicyVO();
			ProfileDietAdviceDtlVO profileDietAdviceVO=null;
			String query = "";
			//ValueObject[] vo=null;
			ResultSet rs;
			Map populateMAP = new HashMap();
			Sequence sq = new Sequence();
			//Connection conn=super.getTransactionContext().getConnection();
			
			String filename = OpdConfig.QUERY_FILE_FOR_OPD_DAO;
			String queryKey = "SELECT_DISCHARGE_DIET_DETAIL.HPMRT_PROFILE_DIETADVICE_DTL";
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
				populateMAP.put(sq.next(), _patientProfileDtlVO.getProfileId());
				populateMAP.put(sq.next(), _userVO.getHospitalCode());
				populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
				
			}
			catch (Exception e)
			{
				throw new HisApplicationExecutionException("ProfileDietAdviceDtlDAO.populateMAP::" + e);
			}
			try
			{
				rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
				rs.beforeFirst();
				if (rs.next())
				{
					profileDietAdviceVO=new ProfileDietAdviceDtlVO();
					HelperMethods.populateVOfrmRS(profileDietAdviceVO,rs);
					
				}
				
			}
			catch(Exception e)
			{
				if(e.getClass()==HisRecordNotFoundException.class)
				{
					throw new HisRecordNotFoundException("No Diet Advice Found");	
				}
				else			 		
				 throw new HisDataAccessException("HisDataAccessException:: "+e);			 
			 }
			return profileDietAdviceVO;
		}
	}
	
	public void updateIsValidStatus(ProfileDietAdviceDtlVO profileDietAdviceDtlVO, UserVO _userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_DAO;
		String queryKey = "UPDATE_IS_VALID.HPMRT_PROFILE_DIETADVICE_DTL";
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
			populateMAP.put(sq.next(), Config.IS_VALID_DELETED);
			populateMAP.put(sq.next(), profileDietAdviceDtlVO.getProfileId());
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("ProfileDietAdviceDtlDAO:populateMap::" + e);
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

}
