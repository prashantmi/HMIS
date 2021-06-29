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
import hisglobal.vo.ProfileInvDtlVO;
import hisglobal.vo.UserVO;
import hisglobal.vo.ValueObject;

public class ProfileInvDtlDAO extends DataAccessObject implements ProfileInvDtlDAOi
{
	
	public ProfileInvDtlDAO(TransactionContext _tx)
	{
		super(_tx);
	}

	/** 
	 * Saving Patient Profile Access Detail
	 * @param _profileAccessDtlVO Profile Access Detail
	 * @param _userVO User VO
	 */
	public void create(ProfileInvDtlVO _profileInvDtlVO, UserVO _userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_DAO;
		String queryKey = "INSERT.HPMRT_PROFILE_INV_DTL";
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
			populateMAP.put(sq.next(), _profileInvDtlVO.getProfileId());
			populateMAP.put(sq.next(), _profileInvDtlVO.getReqDNo());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _userVO.getSeatId());
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("ProfileInvDtlDAO:populateMap::" + e);
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

	
	public ProfileInvDtlVO[] fetchInvestigationProfileDetails(PatientProfileDetailVO _patientProfileDtlVO, UserVO _userVO)
	{

		ProfileInvDtlVO[] profileInvDtlVO;
		String query = "";
		Map _populateMap = new HashMap();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_DAO;
		String queryKey = "SELECT_INVESTIGATION_DTL.HPMRT_PROFILE_INV_DTL";

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
			_populateMap.put(sq.next(), _userVO.getHospitalCode());
			//_populateMap.put(sq.next(), _userVO.getHospitalCode());  by Akash Singh, Dated on 04-06-2015
			_populateMap.put(sq.next(), _patientProfileDtlVO.getProfileId());
			_populateMap.put(sq.next(), _userVO.getHospitalCode());
			_populateMap.put(sq.next(), Config.IS_VALID_ACTIVE);
			_populateMap.put(sq.next(), _patientProfileDtlVO.getPatCrNo());
			_populateMap.put(sq.next(), _patientProfileDtlVO.getEpisodeCode());
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("ProfileInvDtlDAO.populateMAP::" + e);
		}

		ValueObject[] vo = {};
		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, _populateMap);
			if (!rs.next())
			{
				//throw new HisRecordNotFoundException("No Diagnosis Record found");
			}
			rs.beforeFirst();
			vo = HelperMethods.populateVOfrmRS(ProfileInvDtlVO.class, rs);
			profileInvDtlVO = new ProfileInvDtlVO[vo.length];
			for (int i = 0; i < vo.length; i++)
			{
				profileInvDtlVO[i] = (ProfileInvDtlVO) vo[i];
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
		return profileInvDtlVO;
	}
	
	
	public void updateIsValidStatus(ProfileInvDtlVO _profileInvDtlVO, UserVO _userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_DAO;
		String queryKey = "UPDATE_IS_VALID.HPMRT_PROFILE_INV_DTL";
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
			populateMAP.put(sq.next(), _profileInvDtlVO.getProfileId());
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("ProfileInvDtlDAO:populateMap::" + e);
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
	
	public ProfileInvDtlVO[] fetchInvestigationProfileDetailstoGenerate(ProfileInvDtlVO[] profileInvDtlVO, UserVO _userVO)
	{

		//ProfileInvDtlVO[] profileInvDtlVO;
		String query = "";
		Map _populateMap = new HashMap();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_DAO;
		String queryKey = "SELECT_INVESTIGATION_IPD_TEST.HIVT_RESULTVALIDATION_DTL";
        String requisition="";
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
			for(int i=0;i<profileInvDtlVO.length;i++)
			{
				requisition = profileInvDtlVO[i].getReqDNo()+","+requisition;
			}
			if(!requisition.equals("")){
			requisition=requisition.substring(0, requisition.length() - 1);
			}
			//if(requisition!=null){
			if(!requisition.equals("")){
			query=query.replace("$$$"," where HIVTNUM_REQ_DNO in("+requisition+") ORDER BY x.hivt_entry_date,testname, hivtnum_order,parametername");
			}
			else
			{
				//query=query.replace("$$$"," ORDER BY x.hivt_entry_date,testname, hivtnum_order,parametername");
				query=query.replace("$$$"," where HIVTNUM_REQ_DNO is null");
			}
			
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::" + e);
		}

		Sequence sq = new Sequence();
		try
		{
			/*_populateMap.put(sq.next(), _userVO.getHospitalCode());
			//_populateMap.put(sq.next(), _userVO.getHospitalCode());  
			_populateMap.put(sq.next(), _patientProfileDtlVO.getProfileId());
			_populateMap.put(sq.next(), _userVO.getHospitalCode());
			_populateMap.put(sq.next(), Config.IS_VALID_ACTIVE);
			_populateMap.put(sq.next(), _patientProfileDtlVO.getPatCrNo());
			_populateMap.put(sq.next(), _patientProfileDtlVO.getEpisodeCode());*/
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("ProfileInvDtlDAO.populateMAP::" + e);
		}

		ValueObject[] vo = {};
		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, _populateMap);
			if (!rs.next())
			{
				//throw new HisRecordNotFoundException("No Diagnosis Record found");
			}
			rs.beforeFirst();
			vo = HelperMethods.populateVOfrmRS(ProfileInvDtlVO.class, rs);
			profileInvDtlVO = new ProfileInvDtlVO[vo.length];
			for (int i = 0; i < vo.length; i++)
			{
				profileInvDtlVO[i] = (ProfileInvDtlVO) vo[i];
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
		return profileInvDtlVO;
	}
	
	/*public ProfileInvDtlVO[] fetchInvestigationProfileDetailstoGenerate(ProfileInvDtlVO[] profileInvDtlVO, UserVO _userVO)
	{

		//ProfileInvDtlVO[] profileInvDtlVO;
		String query = "";
		Map _populateMap = new HashMap();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_DAO;
		String queryKey = "SELECT_INVESTIGATION_IPD_TEST.HIVT_RESULTVALIDATION_DTL";
        String requisition="";
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
			for(int i=0;i<profileInvDtlVO.length;i++)
			{
				requisition = profileInvDtlVO[i].getReqDNo()+","+requisition;
			}
			if(requisition!=null && requisition.length()>0)
			{
			requisition=requisition.substring(0, requisition.length() - 1);
			query=query.replace("$$$"," where HIVTNUM_REQ_DNO in("+requisition+") ORDER BY x.hivt_entry_date,testname, hivtnum_order,parametername");
			}
			else
			{
				query=query.replace("$$$"," ORDER BY x.hivt_entry_date,testname, hivtnum_order,parametername");
			}
			
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::" + e);
		}

		Sequence sq = new Sequence();
		try
		{
			_populateMap.put(sq.next(), _userVO.getHospitalCode());
			//_populateMap.put(sq.next(), _userVO.getHospitalCode());  
			_populateMap.put(sq.next(), _patientProfileDtlVO.getProfileId());
			_populateMap.put(sq.next(), _userVO.getHospitalCode());
			_populateMap.put(sq.next(), Config.IS_VALID_ACTIVE);
			_populateMap.put(sq.next(), _patientProfileDtlVO.getPatCrNo());
			_populateMap.put(sq.next(), _patientProfileDtlVO.getEpisodeCode());
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("ProfileInvDtlDAO.populateMAP::" + e);
		}

		ValueObject[] vo = {};
		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, _populateMap);
			if (!rs.next())
			{
				//throw new HisRecordNotFoundException("No Diagnosis Record found");
			}
			rs.beforeFirst();
			vo = HelperMethods.populateVOfrmRS(ProfileInvDtlVO.class, rs);
			profileInvDtlVO = new ProfileInvDtlVO[vo.length];
			for (int i = 0; i < vo.length; i++)
			{
				profileInvDtlVO[i] = (ProfileInvDtlVO) vo[i];
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
		return profileInvDtlVO;
	}

	*/
}
