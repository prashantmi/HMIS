package inpatient.transaction.dao;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.TransactionContext;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.Sequence;
import hisglobal.vo.DoctorRoundDtlVO;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.UserVO;
import hisglobal.vo.ValueObject;
import inpatient.InpatientConfig;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import mrd.MrdConfig;
import opd.OpdConfig;

public class DoctorRoundDtlDAO extends DataAccessObject implements DoctorRoundDtlDAOi
{
	public  DoctorRoundDtlDAO(TransactionContext _tx) 
	{
		super(_tx);
	}
	
	public void create(DoctorRoundDtlVO docRoundDtlVO,UserVO userVO){
		String query  = "";
        Map populateMAP =new HashMap();
        Sequence sq=new Sequence();
        String filename=InpatientConfig.QUERY_FILE_FOR_INPATIENT_DAO;
        String queryKey="INSERT_NURSE.HIPD_DOCTOR_ROUND_DTL";
        
       
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
        	populateMAP.put(sq.next(),docRoundDtlVO.getPatCrNo());
        	populateMAP.put(sq.next(),docRoundDtlVO.getEpisodeCode());
        	populateMAP.put(sq.next(),docRoundDtlVO.getEpisodeVisitNo());
        	populateMAP.put(sq.next(),docRoundDtlVO.getPatCrNo());
        	populateMAP.put(sq.next(), "1");		//INSERTING "1" INTO HIPNUM_SLNO
        	populateMAP.put(sq.next(),docRoundDtlVO.getPatAdmNo());
        	populateMAP.put(sq.next(),docRoundDtlVO.getVisitNote());
        	populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
        	populateMAP.put(sq.next(),docRoundDtlVO.getNextVisitDate());
        	populateMAP.put(sq.next(),userVO.getSeatId());
        	populateMAP.put(sq.next(),InpatientConfig.ENTER_BY_NURSE);
        	populateMAP.put(sq.next(),docRoundDtlVO.getEmployeeNo());
        	populateMAP.put(sq.next(),userVO.getHospitalCode());
        	populateMAP.put(sq.next(),userVO.getIpAddress());
        	populateMAP.put(sq.next(),InpatientConfig.NOTES_VERIFICATION_REQUIRED);
        	
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
	
	public DoctorRoundDtlVO[] getDoctorInstruction(String patAdmNo,UserVO userVO)
	{
		DoctorRoundDtlVO[] arrDocInstructionVO=null;
		ValueObject vo[]= null;
		String query  = "";
        Map populateMAP =new HashMap();
        Sequence sq=new Sequence();
        String filename=InpatientConfig.QUERY_FILE_FOR_INPATIENT_DAO;
        String queryKey="SELECT_DOC_INSTRUCTION.HIPD_DOCTOR_ROUND_DTL";
        
        try
        {
        	query =HelperMethodsDAO.getQuery(filename,queryKey);
        }
        catch(Exception e)
        {
        	throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);
        }
        
        populateMAP.put(sq.next(), patAdmNo );
        populateMAP.put(sq.next(), InpatientConfig.NOTES_VERIFICATION_VERIFIED);
        populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
        populateMAP.put(sq.next(), userVO.getHospitalCode());
        
        try
     	{
     		ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);	 
 	 	    if(!rs.next())
 	 	    {
 	 	    	throw new HisRecordNotFoundException("No Doctor Instructions Found");	 	    
 	 	    }
 	 	    rs.beforeFirst();
 	 	   
 	 	    vo=HelperMethods.populateVOfrmRS(DoctorRoundDtlVO.class, rs);
 	 	    arrDocInstructionVO= new DoctorRoundDtlVO[vo.length];
 	 	    for(int i=0;i<vo.length;i++)
 	 	    {
 	 	    	arrDocInstructionVO[i]= (DoctorRoundDtlVO) vo[i];
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
 		return arrDocInstructionVO;
	}
	
	public DoctorRoundDtlVO[] getUnverifiedEntryByNurse(String patAdmNo,UserVO userVO)
	{
		DoctorRoundDtlVO[] arrUnverifiedRecordVO=null;
		ValueObject vo[]= null;
		String query  = "";
        Map populateMAP =new HashMap();
        Sequence sq=new Sequence();
        String filename=InpatientConfig.QUERY_FILE_FOR_INPATIENT_DAO;
        String queryKey="SELECT_UNVERIFIED_ENTRY_PATWISE.HIPD_DOCTOR_ROUND_DTL";
        
        try
        {
        	query =HelperMethodsDAO.getQuery(filename,queryKey);
        }
        catch(Exception e)
        {
        	throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);
        }
        
        populateMAP.put(sq.next(), patAdmNo );
        populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
        populateMAP.put(sq.next(), userVO.getHospitalCode());
        populateMAP.put(sq.next(), InpatientConfig.NOTES_VERIFICATION_REQUIRED);
        populateMAP.put(sq.next(), userVO.getUserEmpID());
        
        try
     	{
     		ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);	 
 	 	    /*if(!rs.next())
 	 	    {
 	 	    	throw new HisRecordNotFoundException("No Unverified Record Found");	 	    
 	 	    }*/
 	 	    rs.beforeFirst();
 	 	   
 	 	    vo=HelperMethods.populateVOfrmRS(DoctorRoundDtlVO.class, rs);
 	 	  arrUnverifiedRecordVO= new DoctorRoundDtlVO[vo.length];
 	 	    for(int i=0;i<vo.length;i++)
 	 	    {
 	 	    	arrUnverifiedRecordVO[i]= (DoctorRoundDtlVO) vo[i];
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
 		return arrUnverifiedRecordVO;
	}
	
	public DoctorRoundDtlVO getRecordDetail(DoctorRoundDtlVO unverifiedRecordVO,UserVO userVO)
	{
		DoctorRoundDtlVO recordDtlVO=new DoctorRoundDtlVO();
		String query  = "";
        Map populateMAP =new HashMap();
        Sequence sq=new Sequence();
        String filename=InpatientConfig.QUERY_FILE_FOR_INPATIENT_DAO;
        String queryKey="SELECT_UNVERIFIED_RECORD_DETAIL.HIPD_DOCTOR_ROUND_DTL";
        
        try
        {
        	query =HelperMethodsDAO.getQuery(filename,queryKey);
        }
        catch(Exception e)
        {
        	throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);
        }
        
        populateMAP.put(sq.next(), unverifiedRecordVO.getPatCrNo());
        populateMAP.put(sq.next(), unverifiedRecordVO.getEpisodeCode());
        populateMAP.put(sq.next(), unverifiedRecordVO.getEpisodeVisitNo());
        populateMAP.put(sq.next(), unverifiedRecordVO.getRoundNo());
        populateMAP.put(sq.next(), unverifiedRecordVO.getSerialNo());
        populateMAP.put(sq.next(), unverifiedRecordVO.getPatAdmNo());
        populateMAP.put(sq.next(), userVO.getHospitalCode());
        populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
        populateMAP.put(sq.next(), unverifiedRecordVO.getRoundDate());
        
        
        try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			
			if (!rs.next())
			{
				throw new HisRecordNotFoundException();
			}
			else
			{
				rs.beforeFirst();
				while(rs.next())
				{
					HelperMethods.populateVOfrmRS(recordDtlVO,rs);
				}
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
		return recordDtlVO;
	}
	
	public void updateVerifiedData(DoctorRoundDtlVO docRoundDtlVO,UserVO userVO)
	{
		String query  = "";
        Map populateMAP =new HashMap();
        Sequence sq=new Sequence();
        String filename=InpatientConfig.QUERY_FILE_FOR_INPATIENT_DAO;
        String queryKey="UPDATE_DOCTOR_NOTES.HIPD_DOCTOR_ROUND_DTL";
        
       
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
        	populateMAP.put(sq.next(),docRoundDtlVO.getProgressNote());
        	populateMAP.put(sq.next(),docRoundDtlVO.getVisitNote());
        	populateMAP.put(sq.next(),docRoundDtlVO.getInstruction());
        	populateMAP.put(sq.next(),userVO.getSeatId());
        	populateMAP.put(sq.next(),userVO.getSeatId());
        	populateMAP.put(sq.next(),InpatientConfig.NOTES_VERIFICATION_VERIFIED);
        	populateMAP.put(sq.next(),docRoundDtlVO.getPatCrNo());
        	populateMAP.put(sq.next(),docRoundDtlVO.getEpisodeCode());
        	populateMAP.put(sq.next(),docRoundDtlVO.getEpisodeVisitNo());
        	populateMAP.put(sq.next(), docRoundDtlVO.getRoundNo());
        	populateMAP.put(sq.next(), docRoundDtlVO.getSerialNo());
        	populateMAP.put(sq.next(),docRoundDtlVO.getPatAdmNo());
        	populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
        	populateMAP.put(sq.next(),userVO.getHospitalCode());
        	populateMAP.put(sq.next(),docRoundDtlVO.getRoundDate());
        	
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
	
	public void saveNotesByDoctor(DoctorRoundDtlVO docRoundDtlVO,UserVO userVO)
	{
		String query  = "";
        Map populateMAP =new HashMap();
        Sequence sq=new Sequence();
        String filename=InpatientConfig.QUERY_FILE_FOR_INPATIENT_DAO;
        String queryKey="INSERT_BY_DOCTOR.HIPD_DOCTOR_ROUND_DTL";
        
       
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
        	populateMAP.put(sq.next(),docRoundDtlVO.getPatCrNo());
        	populateMAP.put(sq.next(),docRoundDtlVO.getEpisodeCode());
        	populateMAP.put(sq.next(),docRoundDtlVO.getEpisodeVisitNo());
        	populateMAP.put(sq.next(),docRoundDtlVO.getPatCrNo());
        //	populateMAP.put(sq.next(),docRoundDtlVO.getEpisodeCode());
        //	populateMAP.put(sq.next(),docRoundDtlVO.getEpisodeVisitNo());
        	populateMAP.put(sq.next(), "1");		//INSERTING "1" INTO HIPNUM_SLNO
        	populateMAP.put(sq.next(),docRoundDtlVO.getPatAdmNo());
        	populateMAP.put(sq.next(),docRoundDtlVO.getProgressNote());
        	populateMAP.put(sq.next(),docRoundDtlVO.getVisitNote());
        	populateMAP.put(sq.next(),docRoundDtlVO.getInstruction());
        	populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
        	populateMAP.put(sq.next(),(docRoundDtlVO.getDocVisitDate()+" "+docRoundDtlVO.getDocVisitTimeHr()+":"+docRoundDtlVO.getDocVisitTimeMin()));
        	populateMAP.put(sq.next(),docRoundDtlVO.getNextVisitDate());
        	populateMAP.put(sq.next(),userVO.getSeatId());
        	populateMAP.put(sq.next(),InpatientConfig.ENTER_BY_DOCTOR);
        	populateMAP.put(sq.next(),userVO.getUserEmpID());
        	populateMAP.put(sq.next(),userVO.getHospitalCode());
        	populateMAP.put(sq.next(),userVO.getIpAddress());
        	populateMAP.put(sq.next(),userVO.getSeatId());
        	populateMAP.put(sq.next(),InpatientConfig.NOTES_VERIFICATION_VERIFIED);
        	//snomed-ct integration
        	populateMAP.put(sq.next(),docRoundDtlVO.getSnomdPTVisitNotes());
        	populateMAP.put(sq.next(),docRoundDtlVO.getSnomdCIdVisitNotes()); 
        	populateMAP.put(sq.next(),docRoundDtlVO.getSnomdPTProgessNotes());
        	populateMAP.put(sq.next(),docRoundDtlVO.getSnomdCIdProgressNotes());
        	populateMAP.put(sq.next(),docRoundDtlVO.getSnomdPTInstruction());
        	populateMAP.put(sq.next(),docRoundDtlVO.getSnomdCIdInstruction());
        
		
        	
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


	/** Getting Doctor Prev Round Dtl on the Basis of Admission No
	 * @param patAdmNo
	 * @param userVO
	 * @return
	 */
	public DoctorRoundDtlVO[] getDoctorPrevRoundDetail(String patAdmNo, UserVO userVO)
	{
		DoctorRoundDtlVO[] arrDocInstructionVO=null;
		ValueObject vo[]= null;
		String query  = "";
        Map populateMAP =new HashMap();
        Sequence sq=new Sequence();
        String filename=InpatientConfig.QUERY_FILE_FOR_INPATIENT_DAO;
        String queryKey="SELECT.DOC_PREV_ROUND_DTL.HIPD_DOCTOR_ROUND_DTL";
        
        try
        {
        	query =HelperMethodsDAO.getQuery(filename,queryKey);
        }
        catch(Exception e)
        {
        	throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);
        }
        
        populateMAP.put(sq.next(), patAdmNo );
        populateMAP.put(sq.next(), InpatientConfig.NOTES_VERIFICATION_VERIFIED);
        populateMAP.put(sq.next(), userVO.getSeatId());
        populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
        populateMAP.put(sq.next(), userVO.getHospitalCode());
        
        try
     	{
     		ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);	 
 	 	    if(!rs.next())
 	 	    {
 	 	    	throw new HisRecordNotFoundException("No Doctor Previous Round Detail Found");	 	    
 	 	    }
 	 	    rs.beforeFirst();
 	 	   
 	 	    vo=HelperMethods.populateVOfrmRS(DoctorRoundDtlVO.class, rs);
 	 	    arrDocInstructionVO= new DoctorRoundDtlVO[vo.length];
 	 	    for(int i=0;i<vo.length;i++)
 	 	    {
 	 	    	arrDocInstructionVO[i]= (DoctorRoundDtlVO) vo[i];
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
 		return arrDocInstructionVO;
	}
	
	/** To get progress notes of patient entered by doctor
	 * @param patCrNo
	 * @param userVO
	 * @return DoctorRoundDtlVO[]
	 */
	public DoctorRoundDtlVO[] getPatientProgressNotesEMR(String patCrNo,String[] departmentUnitArray, String accessType, UserVO userVO)
	{
		DoctorRoundDtlVO[] arrDocInstructionVO=null;
		ValueObject vo[]= null;
		String query  = "";
        Map populateMAP =new HashMap();
        Sequence sq=new Sequence();
        String filename=InpatientConfig.QUERY_FILE_FOR_INPATIENT_DAO;
        String queryKey="SELECT.DOC_PREV_ROUND_DTL.HIPD_DOCTOR_ROUND_DTL_EMR";
        String orderBy=" ORDER BY TO_DATE(entryDate,'dd-Mon-yyyy hh24:mi') DESC";
        
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
						+ "and HRGNUM_PUK=a.HRGNUM_PUK and HRGNUM_VISIT_NO=a.HRGNUM_VISIT_NO and gnum_isvalid=a.gnum_isvalid AND "
						+ "gnum_hospital_code = a.gnum_hospital_code) in ("+inArg+") ";
			}
			query+=orderBy;
        }
        catch(Exception e)
        {
        	throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);
        }
        
        populateMAP.put(sq.next(), patCrNo );
        populateMAP.put(sq.next(), patCrNo );//Added By Shweta on 24-04-2019
        populateMAP.put(sq.next(), patCrNo );//Added By Shweta on 24-04-2019
        populateMAP.put(sq.next(), patCrNo );//Added By Shweta on 24-04-2019
       // populateMAP.put(sq.next(), userVO.getHospitalCode());
        
        try
     	{
     		ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);	 
 	 	    if(rs.next())
 	 	    {
	 	 	    rs.beforeFirst();
	 	 	   
	 	 	    vo=HelperMethods.populateVOfrmRS(DoctorRoundDtlVO.class, rs);
	 	 	    arrDocInstructionVO= new DoctorRoundDtlVO[vo.length];
	 	 	    for(int i=0;i<vo.length;i++)
	 	 	    {
	 	 	    	arrDocInstructionVO[i]= (DoctorRoundDtlVO) vo[i];
	 	 	    }
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
 		return arrDocInstructionVO;
	}
	
	public DoctorRoundDtlVO[] getPatientProgressNotes(String _crNo,UserVO _userVO, PatientDetailVO voDP, String profileGenerationMode)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_DAO;
		String queryKey = "SELECT.HIPD_DOCTOR_ROUND_DTL.PAT_PROFILE_BY_CRNO";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
			String orderBy = " AND HIPSTR_PROGRESS_NOTES IS NOT NULL ORDER BY gdt_entry_date DESC";
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
				populateMAP.put(sq.next(), voDP.getPatAdmNo());
				populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
				populateMAP.put(sq.next(), _userVO.getHospitalCode());
				populateMAP.put(sq.next(), InpatientConfig.NOTES_VERIFICATION_VERIFIED);
				populateMAP.put(sq.next(), voDP.getEpisodeCode());
			}
			else if(profileGenerationMode.equals(OpdConfig.PROFILE_TYPE_GENERATION_MODE_AUTOMATICE_AT_CURRENT_VISIT))
			{
				populateMAP.put(sq.next(), _crNo);
				populateMAP.put(sq.next(), voDP.getPatAdmNo());
				populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
				populateMAP.put(sq.next(), _userVO.getHospitalCode());
				populateMAP.put(sq.next(), InpatientConfig.NOTES_VERIFICATION_VERIFIED);
				populateMAP.put(sq.next(), voDP.getEpisodeCode());
				populateMAP.put(sq.next(), voDP.getEpisodeVisitNo());
			}		
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("DoctorRoundDtlDAO.populateMAP::" + e);
		}

		DoctorRoundDtlVO[] doctorRoundDtlVO=null;
		ValueObject vo[] = null;
		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				//throw new HisRecordNotFoundException("No Progress Notes Found");
			}
			rs.beforeFirst();
			vo = HelperMethods.populateVOfrmRS(DoctorRoundDtlVO.class, rs);
			doctorRoundDtlVO = new DoctorRoundDtlVO[vo.length];
			for (int i = 0; i < vo.length; i++)
			{
				doctorRoundDtlVO[i] = (DoctorRoundDtlVO) vo[i];
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
		return doctorRoundDtlVO;
	}
	
	//Added by Vasu on 26.Sept.2018 to update doctor Notes
	public void updateNotesByDoctor(DoctorRoundDtlVO docRoundDtlVO,UserVO userVO)
	{
		String query  = "";
        Map populateMAP =new HashMap();
        Sequence sq=new Sequence();
        String filename=InpatientConfig.QUERY_FILE_FOR_INPATIENT_DAO;
        String queryKey="UPDATE_BY_DOCTOR.HIPD_DOCTOR_ROUND_DTL";
        
        String dateVal=docRoundDtlVO.getDocVisitDate()+" "+docRoundDtlVO.getDocVisitTimeHr()+":"+docRoundDtlVO.getDocVisitTimeMin();
       
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
        	populateMAP.put(sq.next(),docRoundDtlVO.getProgressNote());
        	populateMAP.put(sq.next(),docRoundDtlVO.getVisitNote());
        	populateMAP.put(sq.next(),docRoundDtlVO.getInstruction());
        	populateMAP.put(sq.next(),dateVal);
        	populateMAP.put(sq.next(),docRoundDtlVO.getPatCrNo());
        	populateMAP.put(sq.next(),docRoundDtlVO.getRoundDate());
        	populateMAP.put(sq.next(),docRoundDtlVO.getRoundNo());
        	populateMAP.put(sq.next(),docRoundDtlVO.getSerialNo());
        	populateMAP.put(sq.next(),userVO.getHospitalCode());
        	populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
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
}
