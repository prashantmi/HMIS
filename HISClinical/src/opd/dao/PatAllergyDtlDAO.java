package opd.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import opd.OpdConfig;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.TransactionContext;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.Sequence;
import hisglobal.vo.EpisodeAllergiesVO;
import hisglobal.vo.PatAllergyDtlVO;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.UserVO;
import hisglobal.vo.ValueObject;

// TODO: Auto-generated Javadoc
/**
 * The Class PatAllergyDtlDAO.
 */
public class PatAllergyDtlDAO extends DataAccessObject
{

	/**
	 * Instantiates a new pat allergy dtl dao.
	 * 
	 * @param _tx the _tx
	 */
	public  PatAllergyDtlDAO(TransactionContext _tx)
	{
		super(_tx);
	}
	
	/**
	 * Gets the episode allergies by patient.
	 * 
	 * @param _patAllergyVO the _pat allergy vo
	 * @param _userVO the _user vo
	 * 
	 * @return the episode allergies by patient
	 */
	public PatAllergyDtlVO[] getEpisodeAllergiesByPatient(PatAllergyDtlVO _patAllergyVO,UserVO _userVO){
		
		PatAllergyDtlVO[] _previousPatAllergyVO= null; 
		ValueObject vo[]= null;
		String query  = "";
        Map populateMAP =new HashMap();
        Sequence sq=new Sequence();
        String filename=OpdConfig.QUERY_FILE_FOR_OPD_DAO;
        String queryKey="SELECT.PREVIOUS.ALLERGY.HPMRT_PAT_ALLERGY_DTL";
        
        
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
	
	/**
	 * Creates the.
	 * 
	 * @param patAllergyDtlVO the pat allergy dtl vo
	 * @param userVO the user vo
	 */
	public void create(PatAllergyDtlVO patAllergyDtlVO,UserVO userVO)
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
        	populateMAP.put(sq.next(), patAllergyDtlVO.getAllergyTypeCode());
        	populateMAP.put(sq.next(), patAllergyDtlVO.getEffectiveTo());
        	populateMAP.put(sq.next(), patAllergyDtlVO.getAllergyTypeName());
        	populateMAP.put(sq.next(), patAllergyDtlVO.getRevokeRemarks());
        	populateMAP.put(sq.next(), patAllergyDtlVO.getAllergyID());
        	populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
        	populateMAP.put(sq.next(), userVO.getSeatId());
        	populateMAP.put(sq.next(), userVO.getHospitalCode());
        	populateMAP.put(sq.next(), userVO.getIpAddress());
        	populateMAP.put(sq.next(), patAllergyDtlVO.getDuration());
        	populateMAP.put(sq.next(), patAllergyDtlVO.getSerialNo());
        	
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
	
	/**
	 * Update new if exists.
	 * 
	 * @param epiAllergyVO the epi allergy vo
	 * @param userVO the user vo
	 */
	public void updateNewIfExists(PatAllergyDtlVO epiAllergyVO,UserVO userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_DAO;
		String queryKey = "UPDATE.NEW_IF_EXISTS.HPMRT_PAT_ALLERGY_DTL";
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
	    	 populateMAP.put(sq.next(), epiAllergyVO.getPatCrNo());
	    	 populateMAP.put(sq.next(), epiAllergyVO.getAllergyTypeCode());
	    	 populateMAP.put(sq.next(), epiAllergyVO.getAllergyID());
	    	 populateMAP.put(sq.next(), userVO.getHospitalCode());
	    	 
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

	/**
	 * Check already exist code.
	 * 
	 * @param patAllergyDtlVO the pat allergy dtl vo
	 * @param userVO the user vo
	 * 
	 * @return true, if successful
	 */
	public boolean checkAlreadyExistCode(PatAllergyDtlVO patAllergyDtlVO,UserVO userVO)
	{
		boolean exist=false;
		String query="";
		Map populateMap= new HashMap();
		String filename= OpdConfig.QUERY_FILE_FOR_OPD_DAO;
		String queryKey="EXIST_ALLERGY_CODE.HPMRT_PAT_ALLERGY_DTL";
		
		Sequence sq= new Sequence();
		
		Connection conn=super.getTransactionContext().getConnection();
		
		populateMap.put(sq.next(), patAllergyDtlVO.getPatCrNo());
		populateMap.put(sq.next(), patAllergyDtlVO.getAllergyTypeCode());
		populateMap.put(sq.next(), patAllergyDtlVO.getAllergyID());
		populateMap.put(sq.next(), userVO.getHospitalCode());
		populateMap.put(sq.next(), Config.IS_VALID_ACTIVE);
		
		try
		{
			query = HelperMethodsDAO.getQuery(filename,queryKey);
		}
		catch(Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::"+e);
		}
		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(conn, query, populateMap);
			
			if (!rs.next())
			{
				exist=false;
			}
			else
			{
				exist=true;
			}
		}
		catch(Exception e)
		{
			if(e.getClass()==HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException();	
			}
			else			 		
			 throw new HisDataAccessException("HisDataAccessException:: "+e);			 
		 }	
		return exist;
	}

	
	/**
	 * Gets the patient allergies detail.
	 * Purpose: To take globale data for patient Allergy
	 * @param _crNo the _cr no
	 * @param _userVO the _user vo
	 * @param voDP the vo dp
	 * @param profileGenerationMode the profile generation mode
	 * 
	 * @return the patient allergies detail
	 * Change By : Akash Singh
	 */
	public PatAllergyDtlVO[] getPatientAllergiesDetail(String _crNo,UserVO _userVO, PatientDetailVO voDP, String profileGenerationMode)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_DAO;
		String queryKey = "SELECT.HPMRT_PAT_ALLERGY_DTL.PAT_PROFILE_BY_CRNO";
		String queryKey_Auto = "SELECT.HPMRT_PAT_ALLERGY_DTL.PAT_AUTO_PROFILE_BY_CRNO";
		
		try
		{
			System.out.println("in PatAllergyDtlDAO in getPatientAllergiesDetail fun");
			String orderBy = " ORDER BY A.GDT_ENTRY_DATE";
			if(profileGenerationMode.equals(OpdConfig.PROFILE_TYPE_GENERATION_MODE_CUSTOMIZED))
			{
				query = HelperMethodsDAO.getQuery(filename, queryKey);
				query= query+orderBy;
				System.out.println("All VISIT Custom"+"  QUERY= "+query);
			}
			else if(profileGenerationMode.equals(OpdConfig.PROFILE_TYPE_GENERATION_MODE_AUTOMATICE_AT_WHOLE_EPISODE))
			{
				query = HelperMethodsDAO.getQuery(filename, queryKey);
				query= query+orderBy;
				System.out.println("All VISIT Autometic"+"  QUERY= "+query);
			}
			/*else if(profileGenerationMode.equals(OpdConfig.PROFILE_TYPE_GENERATION_MODE_AUTOMATICE_AT_WHOLE_EPISODE))
			{
				query = HelperMethodsDAO.getQuery(filename, queryKey_Auto);
				query= query+orderBy;
				System.out.println("All VISIT Autometic"+"  QUERY= "+query);
			}*/
			else if(profileGenerationMode.equals(OpdConfig.PROFILE_TYPE_GENERATION_MODE_AUTOMATICE_AT_CURRENT_VISIT))
			{
				query = HelperMethodsDAO.getQuery(filename, queryKey_Auto);
				query= query+"AND HRGNUM_VISIT_NO=?"+orderBy;
				System.out.println("Current VISIT"+"  QUERY= "+query);
			}
			//query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		try
		{
			if(profileGenerationMode.equals(OpdConfig.PROFILE_TYPE_GENERATION_MODE_CUSTOMIZED))
			{
				populateMAP.put(sq.next(), _crNo);
				//populateMAP.put(sq.next(), _userVO.getHospitalCode());
				populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			}
			else if((profileGenerationMode.equals(OpdConfig.PROFILE_TYPE_GENERATION_MODE_CUSTOMIZED)) || (profileGenerationMode.equals(OpdConfig.PROFILE_TYPE_GENERATION_MODE_AUTOMATICE_AT_WHOLE_EPISODE)))
			{
				populateMAP.put(sq.next(), _crNo);
				//populateMAP.put(sq.next(), _userVO.getHospitalCode());
				populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			}
			/*else if((profileGenerationMode.equals(OpdConfig.PROFILE_TYPE_GENERATION_MODE_CUSTOMIZED)) || (profileGenerationMode.equals(OpdConfig.PROFILE_TYPE_GENERATION_MODE_AUTOMATICE_AT_WHOLE_EPISODE)))
			{
				populateMAP.put(sq.next(), _crNo);
				//populateMAP.put(sq.next(), _userVO.getHospitalCode());
				populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
				populateMAP.put(sq.next(), "0");	//FOR checking hrgnum_isrevoked
				populateMAP.put(sq.next(), voDP.getEpisodeCode());
			}*/
			else if(profileGenerationMode.equals(OpdConfig.PROFILE_TYPE_GENERATION_MODE_AUTOMATICE_AT_CURRENT_VISIT))
			{
				populateMAP.put(sq.next(), _crNo);
				//populateMAP.put(sq.next(), _userVO.getHospitalCode());
				populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
				populateMAP.put(sq.next(), "0");	//FOR checking hrgnum_isrevoked
				populateMAP.put(sq.next(), voDP.getEpisodeCode());
				populateMAP.put(sq.next(), voDP.getEpisodeVisitNo());
				
			}
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("EpisodeDiagnosisDAO.populateMAP::" + e);
		}

		PatAllergyDtlVO[] patAllergyDtlVO = null;
		ValueObject vo[] = null;
		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Allergies Record Found");
			}
			rs.beforeFirst();
			vo = HelperMethods.populateVOfrmRS(PatAllergyDtlVO.class, rs);
			patAllergyDtlVO = new PatAllergyDtlVO[vo.length];
			for (int i = 0; i < vo.length; i++)
			{
				patAllergyDtlVO[i] = (PatAllergyDtlVO) vo[i];
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
		return patAllergyDtlVO;
	}
	
	/**
	 * Update allergy id.
	 * 
	 * @param epiAllergyVO the epi allergy vo
	 * @param userVO the user vo
	 */
	public void updateAllergyId(EpisodeAllergiesVO epiAllergyVO,UserVO userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_DAO;
		String queryKey = "UPDATE_REVOKE_ALLERGY.HPMRT_PAT_ALLERGY_DTL";
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
	    	 populateMAP.put(sq.next(), userVO.getSeatId());
	    	 populateMAP.put(sq.next(), epiAllergyVO.getPatCrNo());
	    	 populateMAP.put(sq.next(), epiAllergyVO.getAllergyTypeCode());
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
	
	
	
	public void updateAllergyId_EpisodeAllergydtl(EpisodeAllergiesVO epiAllergyVO,UserVO userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_DAO;
		String queryKey = "UPDATE_REVOKE_ALLERGY.HRGT_EPISODE_ALLERGY_DTL";
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
	    	 populateMAP.put(sq.next(), epiAllergyVO.getPatCrNo());
	    	 populateMAP.put(sq.next(), epiAllergyVO.getAllergyTypeCode());
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
	
	//Added by Dheeraj to get Drug allergy count
	public String getCount(PatAllergyDtlVO patAllergyDtlVO)
	{
		String query  = "select COALESCE(max(serial_no),0) from HPMRT_PAT_ALLERGY_DTL where hrgnum_puk = ? and hgstr_allergy_name = ? and hgstr_allergy_id = ?";
		String serialNo = "";
		int count = 0;
        Map populateMAP =new HashMap();
        Sequence sq=new Sequence();
        
        try
        {
        	populateMAP.put(sq.next(), patAllergyDtlVO.getPatCrNo());
        	populateMAP.put(sq.next(), patAllergyDtlVO.getAllergyName());
        	populateMAP.put(sq.next(), patAllergyDtlVO.getAllergyID());
        	
        }
        catch(Exception e)
        {
        	throw new HisApplicationExecutionException("PatAllergyDtlDAO.populateMAP::"+e);
        }
        try
        {
        	 
        	ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(),query,populateMAP);
        	if(rs.next())
        	{
        		count = Integer.parseInt(rs.getString(1));
        		count = count + 1;
        	}
        }
        catch(Exception e)
        {
        	e.printStackTrace();
        	throw new HisDataAccessException("HisDataAccessException While ADDING");
        }
        
        serialNo = Integer.toString(count);
        return serialNo;
	}
	
}	