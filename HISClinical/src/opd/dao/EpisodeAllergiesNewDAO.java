package opd.dao;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import mrd.MrdConfig;
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
import hisglobal.vo.DailyPatientVO;
import hisglobal.vo.EpisodeAllergiesVO;
import hisglobal.vo.PatAllergyDtlVO;
import hisglobal.vo.UserVO;
import hisglobal.vo.ValueObject;

public class EpisodeAllergiesNewDAO extends DataAccessObject
{

	public EpisodeAllergiesNewDAO(TransactionContext _tx)
	{
		super(_tx);
	}
	
	
	

	public void create(EpisodeAllergiesVO _episodeAllergyVO,EpisodeAllergiesVO vo, UserVO _userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_DAO;
		String queryKey = "INSERT.HRGT_EPISODE_PAT_ALLERGIES_DTL";

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
			populateMAP.put(sq.next(), vo.getPatCrNo());
			populateMAP.put(sq.next(), vo.getEpisodeCode());
			populateMAP.put(sq.next(), vo.getEpisodeVisitNo());
			populateMAP.put(sq.next(), vo.getPatCrNo());
			populateMAP.put(sq.next(), vo.getEpisodeCode());
			populateMAP.put(sq.next(), vo.getEpisodeVisitNo());
			populateMAP.put(sq.next(), _episodeAllergyVO.getAllergiesCode()); 
			populateMAP.put(sq.next(), vo.getPatAdmNo());
		//	populateMAP.put(sq.next(), _episodeAllergyVO.getRemarks());
			populateMAP.put(sq.next(), _episodeAllergyVO.getAllergyName());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			
			populateMAP.put(sq.next(), _episodeAllergyVO.getSensitivityCode());
			populateMAP.put(sq.next(), _userVO.getSeatId());
			populateMAP.put(sq.next(), _episodeAllergyVO.getSensitivityName());
			
			populateMAP.put(sq.next(), _episodeAllergyVO.getSnomedCTAllergySymptomsCode());
			populateMAP.put(sq.next(), _episodeAllergyVO.getSnomedCTAllergySymptomsName());
			
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), _userVO.getIpAddress());
			
			populateMAP.put(sq.next(), _episodeAllergyVO.getSnomedCTAllergySiteCode());
			populateMAP.put(sq.next(), _episodeAllergyVO.getSnomedCTAllergySiteName());
			
			populateMAP.put(sq.next(), _episodeAllergyVO.getAllergySymtoms());
			populateMAP.put(sq.next(), _episodeAllergyVO.getAllergySite());

			
			
			populateMAP.put(sq.next(), _episodeAllergyVO.getSnomdCIdAllergyAdvice());
			populateMAP.put(sq.next(), _episodeAllergyVO.getAdvice());
			populateMAP.put(sq.next(), _episodeAllergyVO.getSnomdPTAllergyAdvice());
			
       	//	populateMAP.put(sq.next(), _episodeAllergyVO.getIsRevoked());
			populateMAP.put(sq.next(), OpdConfig.PATIENT_ALERT_REVOKED_NO);
			
			
			populateMAP.put(sq.next(), _episodeAllergyVO.getDuration());
			
			

		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("EpisodeAllergiesDAO.populateMAP::" + e);
		}
		try
		{

			HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), query, populateMAP);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException("HisDataAccessException While ADDING");
		}

	}
	
	
	
	public void saveinEpisodeAllergy_dtl(EpisodeAllergiesVO _episodeAllergyVO, UserVO _userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_DAO;
		String queryKey = "INSERT.HRGT_EPISODE_ALLERGY_DTL";

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
			populateMAP.put(sq.next(), _episodeAllergyVO.getPatCrNo());
			populateMAP.put(sq.next(), _episodeAllergyVO.getEpisodeCode());
			populateMAP.put(sq.next(), _episodeAllergyVO.getEpisodeVisitNo());
			populateMAP.put(sq.next(), _episodeAllergyVO.getPatCrNo());
			populateMAP.put(sq.next(), _episodeAllergyVO.getEpisodeCode());
			populateMAP.put(sq.next(), _episodeAllergyVO.getEpisodeVisitNo());
			
			
			populateMAP.put(sq.next(), OpdConfig.ALLERGY_TYPE_CODE_MISCELLANEOUS);
			populateMAP.put(sq.next(), _episodeAllergyVO.getPatAdmNo());
			populateMAP.put(sq.next(), _episodeAllergyVO.getRemarks());
			populateMAP.put(sq.next(), _episodeAllergyVO.getAllergiesCode());
			populateMAP.put(sq.next(), _episodeAllergyVO.getSensitivityCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _userVO.getSeatId());
			populateMAP.put(sq.next(), _episodeAllergyVO.getAllergyName());// allergy Name
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), _userVO.getIpAddress());
			populateMAP.put(sq.next(), _episodeAllergyVO.getAllergySymtoms());
			populateMAP.put(sq.next(), _episodeAllergyVO.getAllergySite());
			populateMAP.put(sq.next(), _episodeAllergyVO.getAdvice());
			populateMAP.put(sq.next(), "");
			populateMAP.put(sq.next(), _episodeAllergyVO.getDuration());
			populateMAP.put(sq.next(), _episodeAllergyVO.getIsRevoked());

		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("EpisodeAllergiesDAO.populateMAP::" + e);
		}
		try
		{

			HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), query, populateMAP);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException("HisDataAccessException While ADDING");
		}

	}
	
	
	
	public void saveinPatAllergydtl(EpisodeAllergiesVO patAllergyDtlVO,UserVO userVO)
	{
		String query  = "";
        Map populateMAP =new HashMap();
        Sequence sq=new Sequence();
        String filename=OpdConfig.QUERY_FILE_FOR_OPD_DAO;
        String queryKey="INSERT.HPMRT_PAT_ALLERGY_DTL";
        
       
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
        	populateMAP.put(sq.next(), patAllergyDtlVO.getPatCrNo());
        	populateMAP.put(sq.next(), patAllergyDtlVO.getAllergyName());
        	populateMAP.put(sq.next(), OpdConfig.ALLERGY_TYPE_CODE_MISCELLANEOUS);
        	populateMAP.put(sq.next(), "");
        	populateMAP.put(sq.next(), patAllergyDtlVO.getAllergyTypeName());
        	populateMAP.put(sq.next(), "");
        	populateMAP.put(sq.next(), patAllergyDtlVO.getAllergiesCode());
        	populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
        	populateMAP.put(sq.next(), userVO.getSeatId());
        	populateMAP.put(sq.next(), userVO.getHospitalCode());
        	populateMAP.put(sq.next(), userVO.getIpAddress());
        	populateMAP.put(sq.next(), patAllergyDtlVO.getDuration());
        	populateMAP.put(sq.next(), patAllergyDtlVO.getSerialNo());  //added by Dheeraj on 12-Jan-2019
        	
        }
        catch(Exception e)
        {
        	throw new HisApplicationExecutionException("PatAllergyDtlDAO.populateMAP::"+e);
        }
        try
        {
        	
        	HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(),query,populateMAP);
        }
        catch(Exception e)
        {
        	e.printStackTrace();
        	throw new HisDataAccessException("HisDataAccessException While ADDING");
        }
	}
	
	public void updateAllergyId(EpisodeAllergiesVO epiAllergyVO,UserVO userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_DAO;
		String queryKey = "UPDATE_REVOKE_ALLERGY.HRGT_EPISODE_PAT_ALLERGIES_DTL";
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
	    	 populateMAP.put(sq.next(), epiAllergyVO.getRemarks());
	    	 populateMAP.put(sq.next(), epiAllergyVO.getIsRevoked());
	    	 populateMAP.put(sq.next(), userVO.getSeatId());
	    	 populateMAP.put(sq.next(), epiAllergyVO.getPatCrNo());
	    	// populateMAP.put(sq.next(), epiAllergyVO.getAllergyTypeCode());
	    	 populateMAP.put(sq.next(), epiAllergyVO.getReasonCode());
	    	
	    	 
	    	 
	     }
	    catch(Exception e)
	    {
	       	throw new HisApplicationExecutionException("PatAlertsDetailDAO.populateMAP::"+e);
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
	
	
	
public PatAllergyDtlVO[] getEpisodeAllergiesByPatient(PatAllergyDtlVO _patAllergyVO,UserVO _userVO){
		
		PatAllergyDtlVO[] _previousPatAllergyVO= null; 
		ValueObject vo[]= null;
		String query  = "";
        Map populateMAP =new HashMap();
        Sequence sq=new Sequence();
        String filename=OpdConfig.QUERY_FILE_FOR_OPD_DAO;
        String queryKey="SELECT.PREVIOUS.ALLERGY.HRGT_EPISODE_PAT_ALLERGIES_DTL";
        
        
        try{
        	query =HelperMethodsDAO.getQuery(filename,queryKey);
        	
        }
        catch(Exception e){
        	throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);
        }
        
        
         populateMAP.put(sq.next(),_patAllergyVO.getPatCrNo());
         populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE );
     	      	
     	
     	try{
 			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);	 
 	 	    if(rs.next())
 	 	    {
 	 	    	//throw new HisRecordNotFoundException("No record for previous allergies found");	 	    
 	 	    
 	 	    rs.beforeFirst();
 	 	   
 	 	    vo=HelperMethods.populateVOfrmRS(PatAllergyDtlVO.class, rs);
 	 	    _previousPatAllergyVO= new PatAllergyDtlVO[vo.length];
 	 	    for(int i=0;i<vo.length;i++)
 	 	    {
 	 	    	_previousPatAllergyVO[i]= (PatAllergyDtlVO) vo[i];
 	 	    }
 	 	    }
 	 	  			
 	 	  }
 		catch(Exception e){
 			if(e.getClass()==HisRecordNotFoundException.class){
 				throw new HisRecordNotFoundException(e.getMessage());	
 			}
 			else			 		
 			 throw new HisDataAccessException("Application Execution Exception"+e);			 
 		 }			 
    
		return _previousPatAllergyVO;

	}
	
	

	/*
	 * public EpisodeAllergiesVO[] getEpisodeAllergiesByPatient(EpisodeAllergiesVO _episodeAllergyVO,UserVO _userVO){
	 * 
	 * EpisodeAllergiesVO[] _previousEpisodeAllergyVO= null; ValueObject vo[]= null; String query = ""; Map populateMAP =new
	 * HashMap(); Sequence sq=new Sequence(); String filename=OpdConfig.QUERY_FILE_FOR_OPD_DAO; String
	 * queryKey="SELECT.PREVIOUS.ALLERGIES.HRGT_EPISODE_ALLERGIES_DTL";
	 * 
	 * 
	 * try{ query =HelperMethodsDAO.getQuery(filename,queryKey);
	 *  } catch(Exception e){ throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR
	 * getting query out of property file"+e); }
	 * 
	 * 
	 * populateMAP.put(sq.next(),_episodeAllergyVO.getPatCrNo()); populateMAP.put(sq.next(),_userVO.getHospitalCode()); //
	 * populateMAP.put(sq.next(),_episodeAllergyVO.getEpisodeCode()); //
	 * populateMAP.put(sq.next(),_episodeAllergyVO.getEpisodeVisitNo());
	 * 
	 * 
	 * try{ ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
	 * if(!rs.next()){ throw new HisRecordNotFoundException("No record for previous allergies found"); } rs.beforeFirst();
	 * 
	 * vo=HelperMethods.populateVOfrmRS(EpisodeAllergiesVO.class, rs); _previousEpisodeAllergyVO= new
	 * EpisodeAllergiesVO[vo.length]; for(int i=0;i<vo.length;i++) { _previousEpisodeAllergyVO[i]= (EpisodeAllergiesVO)
	 * vo[i]; }
	 * 
	 *  } catch(Exception e){ if(e.getClass()==HisRecordNotFoundException.class){ throw new
	 * HisRecordNotFoundException(e.getMessage()); } else throw new HisDataAccessException("Application Execution
	 * Exception"+e); }
	 * 
	 * return _previousEpisodeAllergyVO;
	 *  }
	 */

	

	
	
	public EpisodeAllergiesVO[] getAllergyDtlEpisodeWiseNew(String selAllergyID,  DailyPatientVO _dailyPatientVO, UserVO _userVO)
	{
		EpisodeAllergiesVO[] arrEpisodeAllergyDtlVO = null;
		ValueObject vo[] = null;
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_DAO;
		String queryKey = "SELECT_ALL_ALLERGY_EPISODE.HRGT_EPISODE_PAT_ALLERGIES_DTL";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		populateMAP.put(sq.next(), _dailyPatientVO.getPatCrNo());
	//	populateMAP.put(sq.next(), _dailyPatientVO.getEpisodeCode());
		//populateMAP.put(sq.next(), selAllergyCode); //commendted on 06.10.2016
		populateMAP.put(sq.next(), selAllergyID); //commendted on 06.10.2016
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE );
	    populateMAP.put(sq.next(), _userVO.getHospitalCode());

		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No record for previous allergies found");
			}
			rs.beforeFirst();

			vo = HelperMethods.populateVOfrmRS(EpisodeAllergiesVO.class, rs);
			arrEpisodeAllergyDtlVO = new EpisodeAllergiesVO[vo.length];
			for (int i = 0; i < vo.length; i++)
			{
				arrEpisodeAllergyDtlVO[i] = (EpisodeAllergiesVO) vo[i];
			}
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("Application Execution Exception" + e);
		}

		return arrEpisodeAllergyDtlVO;
	}
	
	
	
	public EpisodeAllergiesVO[] getAllergyDtlEpisodeWiseVisitWise(EpisodeAllergiesVO _episodeAllergiesVO, UserVO _userVO)
	{
		EpisodeAllergiesVO[] arrEpisodeAllergyDtlVO = null;
		ValueObject vo[] = null;
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_DAO;
		String queryKey = "SELECT.HRGT_EPISODE_ALLERGY_DTL.BY_CRNO_EPISODE_VISIT";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		populateMAP.put(sq.next(), _episodeAllergiesVO.getPatCrNo());
		populateMAP.put(sq.next(), _episodeAllergiesVO.getEpisodeCode());
		populateMAP.put(sq.next(), _episodeAllergiesVO.getEpisodeVisitNo());
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), _userVO.getHospitalCode());

		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Allergies Found");
			}
			rs.beforeFirst();

			vo = HelperMethods.populateVOfrmRS(EpisodeAllergiesVO.class, rs);
			arrEpisodeAllergyDtlVO = new EpisodeAllergiesVO[vo.length];
			for (int i = 0; i < vo.length; i++)
			{
				arrEpisodeAllergyDtlVO[i] = (EpisodeAllergiesVO) vo[i];
			}
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("Application Execution Exception" + e);
		}

		return arrEpisodeAllergyDtlVO;
	}
	
	public EpisodeAllergiesVO[] getAllergyDtlAll(EpisodeAllergiesVO _episodeAllergiesVO, String[] departmentUnitArray, String accessType, UserVO _userVO)
	{
		EpisodeAllergiesVO[] arrEpisodeAllergyDtlVO = null;
		ValueObject vo[] = null;
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_DAO;
		String queryKey = "SELECT.HRGT_EPISODE_ALLERGY_DTL.ALL_EMR";
		String orderBy=" ORDER BY episodeisopen,hrgnum_isrevoked,GDT_ENTRY_DATE desc,INITCAP (hgstr_allergy_name),hgnum_allergy_type_code" ;
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
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		populateMAP.put(sq.next(), _episodeAllergiesVO.getPatCrNo());
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		//populateMAP.put(sq.next(), _userVO.getHospitalCode());

		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Allergies Found");
			}
			rs.beforeFirst();

			vo = HelperMethods.populateVOfrmRS(EpisodeAllergiesVO.class, rs);
			arrEpisodeAllergyDtlVO = new EpisodeAllergiesVO[vo.length];
			for (int i = 0; i < vo.length; i++)
			{
				arrEpisodeAllergyDtlVO[i] = (EpisodeAllergiesVO) vo[i];
			}
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("Application Execution Exception" + e);
		}

		return arrEpisodeAllergyDtlVO;
	}
	/**By Vasu on 26.03.2019*/
	public void uploadAllergyDetailsLogPHRMS(EpisodeAllergiesVO  epiAllergyVO, UserVO userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_DAO;
		String queryKey = "INSERT.HINT_PHRMS_UPLOAD_ALGY_DTL_LOG";

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
			populateMAP.put(sq.next(), epiAllergyVO.getPatCrNo()); 
			populateMAP.put(sq.next(), epiAllergyVO.getEpisodeCode());
			populateMAP.put(sq.next(), epiAllergyVO.getAllergiesCode()); 
			populateMAP.put(sq.next(), epiAllergyVO.getPatAdmNo());
			
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), epiAllergyVO.getSensitivityCode());
			
			populateMAP.put(sq.next(), epiAllergyVO.getSnomedCTAllergySymptomsCode());
			populateMAP.put(sq.next(), userVO.getHospitalCode());
			populateMAP.put(sq.next(), userVO.getIpAddress());
			
			populateMAP.put(sq.next(), epiAllergyVO.getSnomedCTAllergySiteCode());
			populateMAP.put(sq.next(), epiAllergyVO.getAllergySymtoms());
			populateMAP.put(sq.next(), epiAllergyVO.getAllergySite());
			populateMAP.put(sq.next(), epiAllergyVO.getSnomdCIdAllergyAdvice());
			
			populateMAP.put(sq.next(), epiAllergyVO.getAdvice());
			populateMAP.put(sq.next(), epiAllergyVO.getDuration());		
			populateMAP.put(sq.next(), epiAllergyVO.getAllergyName());//HGSTR_ALLERGY_NAME
			populateMAP.put(sq.next(), epiAllergyVO.getPatCrNo());//for mobile no
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("EpisodeAllergiesDAO.populateMAP::" + e);
		}
		try
		{
			HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), query, populateMAP);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException("HisDataAccessException While ADDING");
		}
	}
}
