package opd.dao;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.TransactionContext;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.Sequence;
import hisglobal.vo.EpisodeExtInvDtlVO;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.UserVO;
import hisglobal.vo.ValueObject;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import mrd.MrdConfig;
import opd.OpdConfig;

public class EpisodeExtInvDtlDAO extends DataAccessObject implements EpisodeExtInvDtlDAOi
{
	public  EpisodeExtInvDtlDAO(TransactionContext _tx) 
	{
		super(_tx);
	}
	
	public void saveExtInvestigationDtl(EpisodeExtInvDtlVO epiExtInvDtlVO,UserVO userVO)
	{
		String query  = "";
        Map populateMAP =new HashMap();
        Sequence sq=new Sequence();
        String filename=OpdConfig.QUERY_FILE_FOR_OPD_DAO;
        String queryKey="INSERT_EXT_INV.HRGT_EPISODE_EXTINV_DTL";
        
       
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
        	populateMAP.put(sq.next(), epiExtInvDtlVO.getPatCrNo());
        	populateMAP.put(sq.next(), epiExtInvDtlVO.getEpisodeCode());
        	populateMAP.put(sq.next(), epiExtInvDtlVO.getPatCrNo());
        	//populateMAP.put(sq.next(), epiExtInvDtlVO.getRecordDate());
        	populateMAP.put(sq.next(), epiExtInvDtlVO.getRecordDate()+" "+epiExtInvDtlVO.getRecordTime());
        	populateMAP.put(sq.next(), epiExtInvDtlVO.getEpisodeVisitNo());
        	//populateMAP.put(sq.next(), epiExtInvDtlVO.getAdmissionNo());
        	populateMAP.put(sq.next(), epiExtInvDtlVO.getPatAdmNo());
        	populateMAP.put(sq.next(), epiExtInvDtlVO.getParaValue());
        	populateMAP.put(sq.next(), epiExtInvDtlVO.getParaId());
        	populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
        	populateMAP.put(sq.next(), epiExtInvDtlVO.getTestName());
        	populateMAP.put(sq.next(), userVO.getSeatId());
        	populateMAP.put(sq.next(), epiExtInvDtlVO.getExtLabName());
        	populateMAP.put(sq.next(), userVO.getHospitalCode());
        	populateMAP.put(sq.next(), epiExtInvDtlVO.getExtLabAdd());
        	populateMAP.put(sq.next(), epiExtInvDtlVO.getExtLabContactNo());
        	populateMAP.put(sq.next(), epiExtInvDtlVO.getMinValue());
        	populateMAP.put(sq.next(), epiExtInvDtlVO.getMaxValue());
        	populateMAP.put(sq.next(), epiExtInvDtlVO.getRemarks());
        	populateMAP.put(sq.next(), epiExtInvDtlVO.getEntryMode());
        	populateMAP.put(sq.next(), epiExtInvDtlVO.getTestConductedFrom());
        	
        	
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
        	throw new HisDataAccessException("HisDataAccessException While ADDING");
        }
	}
	
	public EpisodeExtInvDtlVO[] getAddedExternalInvDetail(String patCrNo,UserVO userVO)
	{
		EpisodeExtInvDtlVO[] arrAddedExtInvVO=null;
		ValueObject vo[]= null;
		String query  = "";
        Map populateMAP =new HashMap();
        Sequence sq=new Sequence();
        String filename=OpdConfig.QUERY_FILE_FOR_OPD_DAO;
        String queryKey="SELECT_ADDED_EXT_INV.HRGT_EPISODE_EXTINV_DTL";
        
        try
        {
        	query =HelperMethodsDAO.getQuery(filename,queryKey);
        }
        catch(Exception e)
        {
        	throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);
        }
        
        populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
        populateMAP.put(sq.next(), patCrNo);
        populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
        populateMAP.put(sq.next(), userVO.getHospitalCode());
        
        
        try
     	{
     		ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);	 
 	 	    /*if(!rs.next())
 	 	    {
 	 	    	throw new HisRecordNotFoundException("No Record Found");	 	    
 	 	    }*/
 	 	    rs.beforeFirst();
 	 	   
 	 	    vo=HelperMethods.populateVOfrmRS(EpisodeExtInvDtlVO.class, rs);
 	 	  arrAddedExtInvVO= new EpisodeExtInvDtlVO[vo.length];
 	 	    for(int i=0;i<vo.length;i++)
 	 	    {
 	 	    	arrAddedExtInvVO[i]= (EpisodeExtInvDtlVO) vo[i];
 	 	    }
 	 	}
 		catch(Exception e)
 		{
 			if(e.getClass()==HisRecordNotFoundException.class)
 			{
 				throw new HisRecordNotFoundException(e.getMessage());	
 			}
 			else			 		
 			 throw new HisDataAccessException("Application Execution Exception"+e);			 
 		}
 		return arrAddedExtInvVO;
	}
	
	public EpisodeExtInvDtlVO[] getPatientExternalInvDetailEMR(String patCrNo,String [] departmentUnitArray,String accessType,UserVO userVO)
	{
		EpisodeExtInvDtlVO[] arrExtInvVO=null;
		ValueObject vo[]= null;
		String query  = "";
        Map populateMAP =new HashMap();
        Sequence sq=new Sequence();
        String filename=OpdConfig.QUERY_FILE_FOR_OPD_DAO;
        String queryKey="SELECT_HRGT_EPISODE_EXTEXAM_DTL_INVESTIGATION_BY_CRNO_EMR";
        String orderBy=" ORDER BY a.hrgdt_record_date DESC";
        try
        {
        	query =HelperMethodsDAO.getQuery(filename,queryKey);
        	if(accessType.equals(MrdConfig.EPR_DISPLAY_ALL_RECORD_NO)){
				String inArg="";
				for(int i=0;i<departmentUnitArray.length;i++){
					inArg+=","+departmentUnitArray[i];
				}
				inArg=inArg.replaceFirst(",","");
				query+="  and (SELECT hgnum_deptunitcode FROM hrgt_episode_dtl WHERE hrgnum_episode_code = a.hrgnum_episode_code "
						+ "and HRGNUM_PUK=a.HRGNUM_PUK and HRGNUM_VISIT_NO=a.HRGNUM_VISIT_NO and gnum_isvalid=a.gnum_isvalid "
						+ " AND gnum_hospital_code = a.gnum_hospital_code) in ("+inArg+") ";
			}
			query+=orderBy;
        }
        catch(Exception e)
        {
        	throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);
        }
        
        populateMAP.put(sq.next(), patCrNo);
        populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
       // populateMAP.put(sq.next(), userVO.getHospitalCode());
        
        
        try
     	{
     		ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);	 
 	 	    /*if(!rs.next())
 	 	    {
 	 	    	throw new HisRecordNotFoundException("No Record Found");	 	    
 	 	    }*/
 	 	    rs.beforeFirst();
 	 	   
 	 	    vo=HelperMethods.populateVOfrmRS(EpisodeExtInvDtlVO.class, rs);
 	 	  arrExtInvVO= new EpisodeExtInvDtlVO[vo.length];
 	 	    for(int i=0;i<vo.length;i++)
 	 	    {
 	 	    	arrExtInvVO[i]= (EpisodeExtInvDtlVO) vo[i];
 	 	    }
 	 	}
 		catch(Exception e)
 		{
 			if(e.getClass()==HisRecordNotFoundException.class)
 			{
 				throw new HisRecordNotFoundException(e.getMessage());	
 			}
 			else			 		
 			 throw new HisDataAccessException("Application Execution Exception"+e);			 
 		}
 		return arrExtInvVO;
	}

	// Getting External Investigation List of Patient in given Episode for Patient Profile
	public EpisodeExtInvDtlVO[] getEpisodeExtInvList(String _crNo, PatientDetailVO voDP, String profileGenerationMode, UserVO _userVO)
	{
		String query = "";
		ResultSet rs = null;
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
        String filename=OpdConfig.QUERY_FILE_FOR_OPD_DAO;
        String queryKey="SELECT.EPISODE_EXT_INVS.HRGT_EPISODE_EXTINV_DTL";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
			String orderBy = " ORDER BY A.HRGDT_RECORD_DATE";
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
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		try
		{
			if((profileGenerationMode.equals(OpdConfig.PROFILE_TYPE_GENERATION_MODE_CUSTOMIZED)) || (profileGenerationMode.equals(OpdConfig.PROFILE_TYPE_GENERATION_MODE_AUTOMATICE_AT_WHOLE_EPISODE)))
			{
		        populateMAP.put(sq.next(), _crNo);
				populateMAP.put(sq.next(), voDP.getEpisodeCode());
				populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
				populateMAP.put(sq.next(), _userVO.getHospitalCode());
			}
			else if(profileGenerationMode.equals(OpdConfig.PROFILE_TYPE_GENERATION_MODE_AUTOMATICE_AT_CURRENT_VISIT))
			{
		        populateMAP.put(sq.next(), _crNo);
				populateMAP.put(sq.next(), voDP.getEpisodeCode());
				populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
				populateMAP.put(sq.next(), _userVO.getHospitalCode());
				populateMAP.put(sq.next(), voDP.getEpisodeVisitNo());
			}
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("EpisodeExtInvDtlDAO.populateMAP::" + e);
		}

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}

		EpisodeExtInvDtlVO[] arr = new EpisodeExtInvDtlVO[0];
		ValueObject vo[] = null;
		try
		{
			if (rs.next())
			{
				rs.beforeFirst();
				vo = HelperMethods.populateVOfrmRS(EpisodeExtInvDtlVO.class, rs);
				arr= new EpisodeExtInvDtlVO[vo.length];
				for(int i=0;i<vo.length;i++)
					arr[i]= (EpisodeExtInvDtlVO) vo[i];
			}
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HisDataAccessException  :getting Previos Patient Image List" + e);
		}
		return arr;
	}
	
	/* Functions Created By Pawan Kumar B N */
	public void savePatientComplaintsDtl(EpisodeExtInvDtlVO epiExtInvDtlVO,UserVO userVO)
	{
		String query  = "";
        Map populateMAP =new HashMap();
        Sequence sq=new Sequence();
        String filename=OpdConfig.QUERY_FILE_FOR_OPD_DAO;
        String queryKey="INSERT_EXT_INV.HRGT_EPISODE_COMPLAINT_DTL";
        
       
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
        	populateMAP.put(sq.next(), epiExtInvDtlVO.getPatCrNo());
        	populateMAP.put(sq.next(), epiExtInvDtlVO.getEpisodeCode());
        	populateMAP.put(sq.next(), epiExtInvDtlVO.getPatCrNo());
        	//populateMAP.put(sq.next(), epiExtInvDtlVO.getRecordDate());
        	populateMAP.put(sq.next(), epiExtInvDtlVO.getRecordDate()+" "+epiExtInvDtlVO.getRecordTime());
        	populateMAP.put(sq.next(), epiExtInvDtlVO.getEpisodeVisitNo());
        	//populateMAP.put(sq.next(), epiExtInvDtlVO.getAdmissionNo());
        	populateMAP.put(sq.next(), epiExtInvDtlVO.getPatAdmNo());
        	populateMAP.put(sq.next(), epiExtInvDtlVO.getParaValue());
        	populateMAP.put(sq.next(), epiExtInvDtlVO.getParaId());
        	populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
        	populateMAP.put(sq.next(), epiExtInvDtlVO.getTestName());
        	populateMAP.put(sq.next(), userVO.getSeatId());
        	populateMAP.put(sq.next(), epiExtInvDtlVO.getExtLabName());
        	populateMAP.put(sq.next(), userVO.getHospitalCode());
        	populateMAP.put(sq.next(), epiExtInvDtlVO.getExtLabAdd());
        	populateMAP.put(sq.next(), epiExtInvDtlVO.getExtLabContactNo());
        	populateMAP.put(sq.next(), epiExtInvDtlVO.getMinValue());
        	populateMAP.put(sq.next(), epiExtInvDtlVO.getMaxValue());
        	populateMAP.put(sq.next(), epiExtInvDtlVO.getRemarks());
        	populateMAP.put(sq.next(), epiExtInvDtlVO.getEntryMode());
        	populateMAP.put(sq.next(), epiExtInvDtlVO.getTestConductedFrom());
        	
        	
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
        	throw new HisDataAccessException("HisDataAccessException While ADDING");
        }
	}
	/* Function Created By Pawan Kumar B N */
	public EpisodeExtInvDtlVO[] getAddedPatientComplaintsDetail(String patCrNo,UserVO userVO)
	{
		EpisodeExtInvDtlVO[] arrAddedExtInvVO=null;
		ValueObject vo[]= null;
		String query  = "";
        Map populateMAP =new HashMap();
        Sequence sq=new Sequence();
        String filename=OpdConfig.QUERY_FILE_FOR_OPD_DAO;
        String queryKey="SELECT_ADDED_EXT_INV.HRGT_EPISODE_COMPLAINT_DTL";
        
        try
        {
        	query =HelperMethodsDAO.getQuery(filename,queryKey);
        }
        catch(Exception e)
        {
        	throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);
        }
        
        populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
        populateMAP.put(sq.next(), patCrNo);
        populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
        populateMAP.put(sq.next(), userVO.getHospitalCode());
        
        
        try
     	{
     		ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);	 
 	 	    /*if(!rs.next())
 	 	    {
 	 	    	throw new HisRecordNotFoundException("No Record Found");	 	    
 	 	    }*/
 	 	    rs.beforeFirst();
 	 	   
 	 	    vo=HelperMethods.populateVOfrmRS(EpisodeExtInvDtlVO.class, rs);
 	 	  arrAddedExtInvVO= new EpisodeExtInvDtlVO[vo.length];
 	 	    for(int i=0;i<vo.length;i++)
 	 	    {
 	 	    	arrAddedExtInvVO[i]= (EpisodeExtInvDtlVO) vo[i];
 	 	    }
 	 	}
 		catch(Exception e)
 		{
 			if(e.getClass()==HisRecordNotFoundException.class)
 			{
 				throw new HisRecordNotFoundException(e.getMessage());	
 			}
 			else			 		
 			 throw new HisDataAccessException("Application Execution Exception"+e);			 
 		}
 		return arrAddedExtInvVO;
	}
	
}
