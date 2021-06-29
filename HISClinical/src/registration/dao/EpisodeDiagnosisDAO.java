package registration.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mrd.MrdConfig;
import opd.OpdConfig;
import registration.RegistrationConfig;
import hisglobal.persistence.TransactionContext;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.Sequence;
import hisglobal.vo.EpisodeDiagnosisVO;
import hisglobal.vo.EpisodeVO;
import hisglobal.vo.PatDrugTreatmentDetailVO;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.UserVO;
import hisglobal.vo.ValueObject;

public class EpisodeDiagnosisDAO extends DataAccessObject implements EpisodeDiagnosisDAOi
{

	public EpisodeDiagnosisDAO(TransactionContext _tx)
	{
		super(_tx);
	}

	public void create(EpisodeDiagnosisVO _episodediagVO, UserVO _userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		String filename = RegistrationConfig.QUERY_FILE_FOR_DAO;
		String queryKey = "INSERT.HRGT_EPISODE_DIAGNOSIS_DTL";
		Connection conn = super.getTransactionContext().getConnection();

		_episodediagVO.setSeatId(_userVO.getSeatId());
		// call the getQueryMethod with arguments filename,querykey from prop file
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
			populateMap(_episodediagVO, _userVO, populateMAP);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		try
		{
			HelperMethodsDAO.excecuteUpdate(conn, query, populateMAP);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException("INSERT FAILED::EpisodeDiagnosisDAO:::" + e);
		}
	}

	public void populateMap(EpisodeDiagnosisVO _episodediagVO, UserVO _userVO, Map _populateMAP) throws HisApplicationExecutionException
	{
		Sequence sq = new Sequence();
		String strEpisodeVisitNo= _episodediagVO.getEpisodeVisitNo().replace("^","@");
		String strConsentNo=strEpisodeVisitNo.split("\\@")[0];
		String source=_episodediagVO.getPatAdmNo();
		if(source==null || source.isEmpty())
			source=OpdConfig.SOURCE_ISOPD;
		else
			source=OpdConfig.SOURCE_ISIPD;
		
		_populateMAP.put(sq.next(), _episodediagVO.getPatCrNo());
		_populateMAP.put(sq.next(), _episodediagVO.getEpisodeCode());
		_populateMAP.put(sq.next(), _episodediagVO.getEpisodeCode());
		_populateMAP.put(sq.next(), _episodediagVO.getPatCrNo());
		_populateMAP.put(sq.next(), strConsentNo);
		_populateMAP.put(sq.next(), _episodediagVO.getPatAdmNo());
		_populateMAP.put(sq.next(), strConsentNo);
		_populateMAP.put(sq.next(), _episodediagVO.getDiagnosticTypeCode());
		_populateMAP.put(sq.next(), _episodediagVO.getDiagnosticCode());
		_populateMAP.put(sq.next(), _episodediagVO.getSeatId());
		// _populateMAP.put(sq.next(),_episodediagVO.getEntryDate());
		_populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		_populateMAP.put(sq.next(), _episodediagVO.getEpisodeDate());
		_populateMAP.put(sq.next(), _episodediagVO.getRemarks());
		_populateMAP.put(sq.next(), _episodediagVO.getDiagnosisCodeType());
		_populateMAP.put(sq.next(), _userVO.getHospitalCode());
		_populateMAP.put(sq.next(), _episodediagVO.getIsRepeat());
		_populateMAP.put(sq.next(), _episodediagVO.getDiagnosisSite());
		
		//Added on data model change & snomd ct integration date: 08.07.2016
		_populateMAP.put(sq.next(), _episodediagVO.getDignosisName());
		_populateMAP.put(sq.next(), _episodediagVO.getDiagnosticTypeCode());
		_populateMAP.put(sq.next(), _episodediagVO.getDiagnosisSiteLabel());
		//_populateMAP.put(sq.next(), _episodediagVO.getDiagnosisSite());
		_populateMAP.put(sq.next(), source);
		
		
		

	}

	public EpisodeDiagnosisVO getDiagnosisDtl(EpisodeVO _episodeVO, UserVO _userVO)
	{
		EpisodeDiagnosisVO episodeDiagnosisVO = new EpisodeDiagnosisVO();

		Map _populateMapDiagnosisDtl = new HashMap();

		String query = "";
		String filename = RegistrationConfig.QUERY_FILE_FOR_DAO;
		String queryKey = "SELECT.HRGT_EPISODE_DIAGNOSIS_DTL";
		Sequence sq = new Sequence();

		try
		{
			_populateMapDiagnosisDtl.put(sq.next(), _episodeVO.getPatCrNo());
			_populateMapDiagnosisDtl.put(sq.next(), _episodeVO.getEpisodeCode());
			_populateMapDiagnosisDtl.put(sq.next(), _episodeVO.getEpisodeVisitNo());
			_populateMapDiagnosisDtl.put(sq.next(), Config.IS_VALID_ACTIVE);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("EpisodeDiagnosisDAO.populateMAP::" + e);
		}

		Connection conn = super.getTransactionContext().getConnection();
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(conn, query, _populateMapDiagnosisDtl);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException();
			}
			rs.beforeFirst();
			HelperMethods.populateVOfrmRS(episodeDiagnosisVO, rs);
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException();
			}
			else throw new HisDataAccessException("MlcDAO:retrieveByCrNo:HelperMethods :: " + e);
		}
		return episodeDiagnosisVO;
	}

	/**
	 * Getting Episode Diagnosis Detail for Patient Profile
	 * 
	 * @param _crNo Patient CR No.
	 * @param _episodeCode Episode Code
	 * @param _userVO User VO
	 * @return Array of Episode Diagnosis Detail VOs
	 */
	public EpisodeDiagnosisVO[] retrieveEpisodeDiagnosisForProfile(String _crNo, PatientDetailVO voDp, UserVO _userVO ,  String profileGenerationMode)
	{

		EpisodeDiagnosisVO[] _episodeDiagnosisVO;
		String query = "";
		Map _populateMap = new HashMap();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_DAO;
		String queryKey = "SELECT.PAT_PROFILE_DIGNOSIS_DETAIL.HRGT_EPISODE_DIAGNOSIS_DTL";
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
			String orderBy = " ORDER BY HRGNUM_VISIT_NO";
			if((profileGenerationMode.equals(OpdConfig.PROFILE_TYPE_GENERATION_MODE_CUSTOMIZED)) || (profileGenerationMode.equals(OpdConfig.PROFILE_TYPE_GENERATION_MODE_AUTOMATICE_AT_WHOLE_EPISODE)))
			{
				query= query+orderBy;
				System.out.println("All VISIT"+"  QUERY= "+query);
			}
			else if(profileGenerationMode.equals(OpdConfig.PROFILE_TYPE_GENERATION_MODE_AUTOMATICE_AT_CURRENT_VISIT))
			{
				query= query+"AND HRGNUM_VISIT_NO=?"+orderBy;
				System.out.println("Current VISIT"+"  QUERY= "+query);
			}
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::" + e);
		}

		Sequence sq = new Sequence();
		try
		{
			if((profileGenerationMode.equals(OpdConfig.PROFILE_TYPE_GENERATION_MODE_CUSTOMIZED)) || (profileGenerationMode.equals(OpdConfig.PROFILE_TYPE_GENERATION_MODE_AUTOMATICE_AT_WHOLE_EPISODE)))
			{
				_populateMap.put(sq.next(), Config.IS_VALID_ACTIVE);
				//_populateMap.put(sq.next(), Config.IS_VALID_ACTIVE);
				//_populateMap.put(sq.next(), _userVO.getHospitalCode());
				_populateMap.put(sq.next(), Config.IS_VALID_ACTIVE);
				_populateMap.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
				_populateMap.put(sq.next(), _crNo);
				_populateMap.put(sq.next(), voDp.getEpisodeCode());
				_populateMap.put(sq.next(), _crNo);
				_populateMap.put(sq.next(), voDp.getEpisodeCode());
				_populateMap.put(sq.next(), Config.IS_VALID_ACTIVE);
				_populateMap.put(sq.next(), _userVO.getHospitalCode());
				_populateMap.put(sq.next(), Config.IS_VALID_ACTIVE);
				_populateMap.put(sq.next(), _userVO.getHospitalCode());
			}
			else if(profileGenerationMode.equals(OpdConfig.PROFILE_TYPE_GENERATION_MODE_AUTOMATICE_AT_CURRENT_VISIT))
			{
				_populateMap.put(sq.next(), Config.IS_VALID_ACTIVE);
				//_populateMap.put(sq.next(), Config.IS_VALID_ACTIVE);
				//_populateMap.put(sq.next(), _userVO.getHospitalCode());
				_populateMap.put(sq.next(), Config.IS_VALID_ACTIVE);
				_populateMap.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
				_populateMap.put(sq.next(), _crNo);
				_populateMap.put(sq.next(), voDp.getEpisodeCode());
				_populateMap.put(sq.next(), _crNo);
				_populateMap.put(sq.next(), voDp.getEpisodeCode());
				_populateMap.put(sq.next(), Config.IS_VALID_ACTIVE);
				_populateMap.put(sq.next(), _userVO.getHospitalCode());
				_populateMap.put(sq.next(), Config.IS_VALID_ACTIVE);
				_populateMap.put(sq.next(), _userVO.getHospitalCode());
				_populateMap.put(sq.next(), voDp.getEpisodeVisitNo());
			}
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("EpisodeDiagnosisDAO.populateMAP::" + e);
		}

		ValueObject[] vo = {};
		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, _populateMap);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Diagnosis Record found");
			}
			rs.beforeFirst();
			vo = HelperMethods.populateVOfrmRS(EpisodeDiagnosisVO.class, rs);
			_episodeDiagnosisVO = new EpisodeDiagnosisVO[vo.length];
			for (int i = 0; i < vo.length; i++)
			{
				_episodeDiagnosisVO[i] = (EpisodeDiagnosisVO) vo[i];
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
		return _episodeDiagnosisVO;
	}
	
	public EpisodeDiagnosisVO[] retrieveAdmissionDiagnosisForProfile(String _crNo, String _admissionNO, UserVO _userVO)
	{

		EpisodeDiagnosisVO[] _episodeDiagnosisVO;
		String query = "";
		Map _populateMap = new HashMap();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_DAO;
		String queryKey = "SELECT.PAT_PROFILE_IPD_DIGNOSIS_DETAIL.HRGT_EPISODE_DIAGNOSIS_DTL";

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
			/*_populateMap.put(sq.next(), Config.IS_VALID_ACTIVE);
			_populateMap.put(sq.next(), Config.IS_VALID_ACTIVE);
			_populateMap.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
			_populateMap.put(sq.next(), Config.IS_VALID_ACTIVE);
			_populateMap.put(sq.next(), _userVO.getHospitalCode());
		*/	_populateMap.put(sq.next(), _crNo);
			_populateMap.put(sq.next(), _admissionNO);
			_populateMap.put(sq.next(), _crNo);
			_populateMap.put(sq.next(), _admissionNO);
			_populateMap.put(sq.next(), Config.IS_VALID_ACTIVE);
			_populateMap.put(sq.next(), _userVO.getHospitalCode());
			_populateMap.put(sq.next(), Config.IS_VALID_ACTIVE);
			_populateMap.put(sq.next(), _userVO.getHospitalCode());
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("EpisodeDiagnosisDAO.populateMAP::" + e);
		}

		ValueObject[] vo = {};
		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, _populateMap);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Diagnosis Record found");
			}
			rs.beforeFirst();
			vo = HelperMethods.populateVOfrmRS(EpisodeDiagnosisVO.class, rs);
			_episodeDiagnosisVO = new EpisodeDiagnosisVO[vo.length];
			for (int i = 0; i < vo.length; i++)
			{
				_episodeDiagnosisVO[i] = (EpisodeDiagnosisVO) vo[i];
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
		return _episodeDiagnosisVO;
	}
	
	public void revokeDiagnosis(String revokeCode,EpisodeDiagnosisVO episodeDiaVO,UserVO userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_DAO;
		String queryKey = "REVOKE.HRGT_EPISODE_DIAGNOSIS_DTL";
		Connection conn = super.getTransactionContext().getConnection();
		Sequence sq = new Sequence();
		
		
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
	    	 populateMAP.put(sq.next(), OpdConfig.DIAGNOSIS_IS_REPEAT_CANCELLED);
	    	 populateMAP.put(sq.next(), episodeDiaVO.getPatCrNo());
	    	 populateMAP.put(sq.next(), episodeDiaVO.getEpisodeCode());
	    	 populateMAP.put(sq.next(), revokeCode);
	    	 //populateMAP.put(sq.next(), episodeDiaVO.getPatAdmNo());
	    	 populateMAP.put(sq.next(), episodeDiaVO.getEpisodeVisitNo());
	    	 populateMAP.put(sq.next(), episodeDiaVO.getDiagnosisCodeType() );
	    	 populateMAP.put(sq.next(), userVO.getHospitalCode());
	    	 
	     }
	    catch(Exception e)
	    {
	       	throw new HisApplicationExecutionException("DoctorRoundDtlDAO.populateMAP::"+e);
	    }
	    try
	    {
	        	
	      	HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(),query,populateMAP);
	    }
	    catch(Exception e)
	    {
	       	e.printStackTrace();
	       	throw new HisDataAccessException("HisDataAccessException While UPDATING");
	    }
	}

	/**
	 * Getting Episode Diagnosis Detail for Patient EMR
	 * 
	 * @param _crNo Patient CR No.
	 * @param _userVO User VO
	 * @return Array of Episode Diagnosis Detail VOs
	 */
	public EpisodeDiagnosisVO[] retrieveEpisodeDiagnosisForEMR(String _crNo,String departmentUnitArray[],String accessType,UserVO _userVO)
	{

		EpisodeDiagnosisVO[] _episodeDiagnosisVO;
		String query = "";
		Map _populateMap = new HashMap();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_DAO;
		String queryKey = "SELECT.HRGT_EPISODE_DIAGNOSIS_DTL.ALL_EMR";
		String orderBy=" ORDER BY episodeisopen,isrepeat,episodecode,diagnostictypename,diagnosticcode";
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
			if(accessType.equals(MrdConfig.EPR_DISPLAY_ALL_RECORD_NO)){
				String inArg="";
				for(int i=0;i<departmentUnitArray.length;i++){
					inArg+=","+departmentUnitArray[i];
				}
				inArg=inArg.replaceFirst(",","");
				query+="  and (SELECT hgnum_deptunitcode FROM hrgt_episode_dtl WHERE hrgnum_episode_code = a.hrgnum_episode_code "
						+ "and HRGNUM_PUK=a.HRGNUM_PUK and HRGNUM_VISIT_NO=a.HRGNUM_VISIT_NO and gnum_isvalid=a.gnum_isvalid AND "
						+ "gnum_hospital_code = a.gnum_hospital_code) in ("+inArg+") ";
			}
			query+=orderBy;
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::" + e);
		}

		Sequence sq = new Sequence();
		try
		{
			_populateMap.put(sq.next(), _crNo);
			_populateMap.put(sq.next(), Config.IS_VALID_ACTIVE);
			_populateMap.put(sq.next(), Config.IS_VALID_ACTIVE);
			//_populateMap.put(sq.next(), _userVO.getHospitalCode());
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("EpisodeDiagnosisDAO.populateMAP::" + e);
		}

		ValueObject[] vo = {};
		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, _populateMap);
			if (!rs.next())
			{
				// throw new HisRecordNotFoundException("No Diagnosis Record found");
			}
			rs.beforeFirst();
			vo = HelperMethods.populateVOfrmRS(EpisodeDiagnosisVO.class, rs);
			_episodeDiagnosisVO = new EpisodeDiagnosisVO[vo.length];
			for (int i = 0; i < vo.length; i++)
			{
				_episodeDiagnosisVO[i] = (EpisodeDiagnosisVO) vo[i];
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
		return _episodeDiagnosisVO;
	}
	
	/**
	 * get current diagnosis detail of the patient
	 */
	public EpisodeDiagnosisVO[] retrieveCurrentEpisodeDiagnosisForEMR(String _crNo,UserVO _userVO)
	{
		
		EpisodeDiagnosisVO[] _episodeDiagnosisVO;
		String query = "";
		Map _populateMap = new HashMap();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_DAO;
		String queryKey = "SELECT.HRGT_EPISODE_DIAGNOSIS_DTL.CURRENT_DISEASE";
		
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
			_populateMap.put(sq.next(), Config.IS_VALID_ACTIVE);
			_populateMap.put(sq.next(), _userVO.getHospitalCode());
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("EpisodeDiagnosisDAO.populateMAP::" + e);
		}
		
		ValueObject[] vo = {};
		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, _populateMap);
			if (!rs.next())
			{
				// throw new HisRecordNotFoundException("No Diagnosis Record found");
			}
			rs.beforeFirst();
			vo = HelperMethods.populateVOfrmRS(EpisodeDiagnosisVO.class, rs);
			_episodeDiagnosisVO = new EpisodeDiagnosisVO[vo.length];
			for (int i = 0; i < vo.length; i++)
			{
				_episodeDiagnosisVO[i] = (EpisodeDiagnosisVO) vo[i];
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
		return _episodeDiagnosisVO;
	}
	
	
	/**
	 * Getting Episode Diagnosis Detail for Patient episode and visit wise EMR
	 * 
	 * @param _episodeDiagnosisVO
	 * @param _userVO User VO
	 * @return Array of Episode Diagnosis Detail VOs
	 */
	public EpisodeDiagnosisVO[] retrieveEpisodeDiagnosisEpisodeVisitWiseForEMR(EpisodeDiagnosisVO _episodeDiagnosisVO,UserVO _userVO)
	{

		
		String query = "";
		Map _populateMap = new HashMap();
		EpisodeDiagnosisVO[] episodeDiagnosisVOs=null;
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_DAO;
		String queryKey = "SELECT.HRGT_EPISODE_DIAGNOSIS_DTL.EPISODE_VISIT_WISE_EMR";

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
			_populateMap.put(sq.next(), _episodeDiagnosisVO.getPatCrNo());
			_populateMap.put(sq.next(), _episodeDiagnosisVO.getEpisodeCode());
			_populateMap.put(sq.next(), _episodeDiagnosisVO.getEpisodeVisitNo());
			_populateMap.put(sq.next(), Config.IS_VALID_ACTIVE);
			_populateMap.put(sq.next(), _userVO.getHospitalCode());
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("EpisodeDiagnosisDAO.populateMAP::" + e);
		}

		ValueObject[] vo = {};
		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, _populateMap);
			if (rs.next())
			{
				// throw new HisRecordNotFoundException("No Diagnosis Record found");
				
				rs.beforeFirst();
				vo = HelperMethods.populateVOfrmRS(EpisodeDiagnosisVO.class, rs);
				episodeDiagnosisVOs = new EpisodeDiagnosisVO[vo.length];
				for (int i = 0; i < vo.length; i++)
				{
					episodeDiagnosisVOs[i] = (EpisodeDiagnosisVO) vo[i];
				}
			}
			
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("EpisodeDAO:retrieveByCrNoEpisoeVisitEMR::Episode Details:: " + e);
		}
		return episodeDiagnosisVOs;
	}
	
	/**
	 * Remove the diagnosis by setting gnum_isvalid=0 for particular episodeCode,visitNo and serial No.
	 * 
	 */
	public void removeDiagnosis(EpisodeDiagnosisVO episodeDiaVO,UserVO userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_DAO;
		String queryKey = "REMOVE.HRGT_EPISODE_DIAGNOSIS_DTL";
		Connection conn = super.getTransactionContext().getConnection();
		Sequence sq = new Sequence();
		
		
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
	    	 populateMAP.put(sq.next(), Config.IS_VALID_DELETED);
	    	 populateMAP.put(sq.next(), episodeDiaVO.getPatCrNo());
	    	 populateMAP.put(sq.next(), episodeDiaVO.getEpisodeCode());
	    	 populateMAP.put(sq.next(), episodeDiaVO.getSerialNo());
	    	 populateMAP.put(sq.next(), episodeDiaVO.getEpisodeVisitNo());
	    	 populateMAP.put(sq.next(), userVO.getHospitalCode());
	    	 
	     }
	    catch(Exception e)
	    {
	       	throw new HisApplicationExecutionException("DoctorRoundDtlDAO.populateMAP::"+e);
	    }
	    try
	    {
	        	
	      	HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(),query,populateMAP);
	    }
	    catch(Exception e)
	    {
	       	e.printStackTrace();
	       	throw new HisDataAccessException("HisDataAccessException While UPDATING");
	    }
	}
	
	
	
	
	public List<EpisodeDiagnosisVO> getEssentialDiagnosisDetail(EpisodeDiagnosisVO previousDiagVO,UserVO userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_DAO;
		String queryKey = "DIAGNOSIS_DETAIL.HRGT_EPISODE_DIAGNOSIS_DTL";
		Connection conn = super.getTransactionContext().getConnection();
		Sequence sq = new Sequence();
		
		
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
	    	 
	    	 populateMAP.put(sq.next(), userVO.getHospitalCode());
	    	 populateMAP.put(sq.next(), previousDiagVO.getPatCrNo());
	    	 populateMAP.put(sq.next(), previousDiagVO.getEpisodeCode());
	    	 populateMAP.put(sq.next(), previousDiagVO.getEpisodeVisitNo());
	    	
	     }
	    catch(Exception e)
	    {
	       	throw new HisApplicationExecutionException("DoctorRoundDtlDAO.populateMAP::"+e);
	    }
	    List<EpisodeDiagnosisVO> _previousEpisodeDiagVO = new ArrayList<EpisodeDiagnosisVO>();
 		ValueObject vo[] = null;
	    try
	    {
	    	ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);	
	      	if (rs.next())
			{
				// throw new HisRecordNotFoundException("No Diagnosis Record found");
				
				rs.beforeFirst();
				vo = HelperMethods.populateVOfrmRS(EpisodeDiagnosisVO.class, rs);			
				for (ValueObject v : vo)
					_previousEpisodeDiagVO.add((EpisodeDiagnosisVO)v);
				
			}
	      	
	      	
	    }
	    catch(Exception e)
	    {
	       	e.printStackTrace();
	       	throw new HisDataAccessException("HisDataAccessException While UPDATING");
	    }
	    return _previousEpisodeDiagVO;
	}

	//AddedBy: NehaRajgariya Date:28July2016
	public String getIcdHospCode(String deptUnitCode,UserVO _userVO) {
		// TODO Auto-generated method stub
		String query = "";
		Map populateMAP = new HashMap();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_DAO;
		String queryKey = "SELECT_DIAGNOSIS_CODE.HGBT_UNIT_MST";
		Connection conn = super.getTransactionContext().getConnection();
		Sequence sq = new Sequence();
		String deptCode = null;
		
		
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
	    	 
	    	 populateMAP.put(sq.next(), deptUnitCode);
	    	 populateMAP.put(sq.next(), _userVO.getHospitalCode());
	    	 
	    	
	    	
	     }
	    catch(Exception e)
	    {
	       	throw new HisApplicationExecutionException("DoctorRoundDtlDAO.populateMAP::"+e);
	    }
	   
	    try
	    {
	    	ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);	
	      	if (rs.next())
			{
				// throw new HisRecordNotFoundException("No Diagnosis Record found");
				
				//rs.beforeFirst();
				
				deptCode = rs.getString(1);
			}
	      	
	      	
	    }
	    catch(Exception e)
	    {
	       	e.printStackTrace();
	       	throw new HisDataAccessException("HisDataAccessException While UPDATING");
	    }
	    return deptCode;
	}
	
	//Added by Vasu on 26.March.19 to push patient Diagnosis data into PHRMS
	public void pushDiagnosisDataIntoPHRMS(EpisodeDiagnosisVO _episodediagVO, UserVO _userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		String filename = RegistrationConfig.QUERY_FILE_FOR_DAO;
		String queryKey = "INSERT.HINT_PHRMS_UPLOAD_DIAGNOSIS_DTL_LOG";
		Connection conn = super.getTransactionContext().getConnection();

		_episodediagVO.setSeatId(_userVO.getSeatId());
		// call the getQueryMethod with arguments filename,querykey from prop file
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
			populateMapForPushDiagnosisDataIntoPhrms(_episodediagVO, _userVO, populateMAP);
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		try
		{
			HelperMethodsDAO.excecuteUpdate(conn, query, populateMAP);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException("INSERT FAILED::EpisodeDiagnosisDAO:::" + e);
		}
	}
		
    //Added by Vasu on 27.March.2019	
	public void populateMapForPushDiagnosisDataIntoPhrms(EpisodeDiagnosisVO _episodediagVO, UserVO _userVO, Map _populateMAP) throws HisApplicationExecutionException
	{
		Sequence sq = new Sequence();
		String strEpisodeVisitNo= _episodediagVO.getEpisodeVisitNo().replace("^","@");
		String strConsentNo=strEpisodeVisitNo.split("\\@")[0];
		String source=_episodediagVO.getPatAdmNo();
		if(source==null || source.isEmpty())
			source=OpdConfig.SOURCE_ISOPD;
		else
			source=OpdConfig.SOURCE_ISIPD;
		
		_populateMAP.put(sq.next(), _episodediagVO.getPatCrNo());
		_populateMAP.put(sq.next(), _episodediagVO.getEpisodeCode());
		_populateMAP.put(sq.next(), _episodediagVO.getEpisodeCode());
		_populateMAP.put(sq.next(), _episodediagVO.getPatCrNo());
		_populateMAP.put(sq.next(), strConsentNo);
		_populateMAP.put(sq.next(), _episodediagVO.getPatAdmNo());
		_populateMAP.put(sq.next(), strConsentNo);
		_populateMAP.put(sq.next(), _episodediagVO.getDiagnosticTypeCode());
		_populateMAP.put(sq.next(), _episodediagVO.getDiagnosticCode());
		//_populateMAP.put(sq.next(), _episodediagVO.getSeatId());
		// _populateMAP.put(sq.next(),_episodediagVO.getEntryDate());
		_populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		//_populateMAP.put(sq.next(), _episodediagVO.getEpisodeDate());
		_populateMAP.put(sq.next(), _episodediagVO.getRemarks());
		_populateMAP.put(sq.next(), _episodediagVO.getDiagnosisCodeType());
		_populateMAP.put(sq.next(), _userVO.getHospitalCode());
		//_populateMAP.put(sq.next(), _episodediagVO.getIsRepeat());
		_populateMAP.put(sq.next(), _episodediagVO.getDiagnosisSite());
		
		//Added on data model change & snomd ct integration date: 08.07.2016
		_populateMAP.put(sq.next(), _episodediagVO.getDignosisName());
		_populateMAP.put(sq.next(), _episodediagVO.getDiagnosticTypeCode());
		_populateMAP.put(sq.next(), _episodediagVO.getDiagnosisSiteLabel());
		//_populateMAP.put(sq.next(), _episodediagVO.getDiagnosisSite());
		//_populateMAP.put(sq.next(), source);
		_populateMAP.put(sq.next(), _episodediagVO.getPatCrNo());
		_populateMAP.put(sq.next(), _userVO.getUsrName());
		
	}
}
