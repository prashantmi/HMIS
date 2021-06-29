package inpatient.transaction.dao;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.Procedure;
import hisglobal.persistence.TransactionContext;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.Sequence;
import hisglobal.vo.PatIntakeOutDtlVO;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.UserVO;
import hisglobal.vo.ValueObject;
import inpatient.InpatientConfig;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mrd.MrdConfig;

public class PatIntakeOutDtlDAO extends DataAccessObject implements PatIntakeOutDtlDAOi
{
	public  PatIntakeOutDtlDAO(TransactionContext _tx) 
	{
		super(_tx);
	}
	
	public void saveOutParameter(PatIntakeOutDtlVO patIntakeOutDtlVO,UserVO userVO)
	{
		String query  = "";
        Map populateMAP =new HashMap();
        Sequence sq=new Sequence();
        String filename=InpatientConfig.QUERY_FILE_FOR_INPATIENT_DAO;
        String queryKey="INSERT_OUT_TAKE.HIPD_PAT_INTAKEOUT_DTL";
        
       
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
        	populateMAP.put(sq.next(),patIntakeOutDtlVO.getPatCrNo());
        	populateMAP.put(sq.next(),patIntakeOutDtlVO.getEpisodeCode());
        	populateMAP.put(sq.next(),patIntakeOutDtlVO.getEpisodeVisitNo());
        	populateMAP.put(sq.next(),patIntakeOutDtlVO.getPatAdmNo());
        	populateMAP.put(sq.next(),patIntakeOutDtlVO.getInTakeOutParaId());
        	populateMAP.put(sq.next(),patIntakeOutDtlVO.getInTakeOutTime());
        	populateMAP.put(sq.next(),patIntakeOutDtlVO.getRouteId());
        	populateMAP.put(sq.next(),patIntakeOutDtlVO.getInTakeOutMode());
        	populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
        	populateMAP.put(sq.next(),userVO.getSeatId());
        	populateMAP.put(sq.next(),patIntakeOutDtlVO.getVolume());
        	populateMAP.put(sq.next(),patIntakeOutDtlVO.getRemarks());
        	populateMAP.put(sq.next(),patIntakeOutDtlVO.getPatCrNo());
        	populateMAP.put(sq.next(),userVO.getHospitalCode());
        	populateMAP.put(sq.next(), patIntakeOutDtlVO.getLastModifySeatId());
        	populateMAP.put(sq.next(), userVO.getIpAddress());
        	populateMAP.put(sq.next(), patIntakeOutDtlVO.getLastModifyDate());
        	populateMAP.put(sq.next(), patIntakeOutDtlVO.getInTakeOutParaName());
        	populateMAP.put(sq.next(), patIntakeOutDtlVO.getEntryMode());
        	populateMAP.put(sq.next(), patIntakeOutDtlVO.getSnomdCIdRemarks());
        	populateMAP.put(sq.next(), patIntakeOutDtlVO.getSnomdPTRemarks());
        	populateMAP.put(sq.next(), patIntakeOutDtlVO.getSnomedParaId());
        	populateMAP.put(sq.next(), patIntakeOutDtlVO.getSnomedPtermPara());
        	
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
	
	public PatIntakeOutDtlVO[] getOutParaDetail(String mode,PatientDetailVO dailyPatVO,UserVO userVO)
	{
		PatIntakeOutDtlVO[] arrPatOuttakeDtlVO=null;
		ValueObject vo[]= null;
		String query  = "";
        Map populateMAP =new HashMap();
        Sequence sq=new Sequence();
        String filename=InpatientConfig.QUERY_FILE_FOR_INPATIENT_DAO;
        String queryKey="SELECT_OUT_TAKE_DETAIL.HIPD_PAT_INTAKEOUT_DTL";
        
        try
        {
        	query =HelperMethodsDAO.getQuery(filename,queryKey);
        }
        catch(Exception e)
        {
        	throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);
        }
        
     //   populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
        populateMAP.put(sq.next(), dailyPatVO.getPatCrNo());
       // populateMAP.put(sq.next(), dailyPatVO.getEpisodeCode());
       // populateMAP.put(sq.next(), dailyPatVO.getEpisodeVisitNo());
        populateMAP.put(sq.next(), dailyPatVO.getPatAdmNo());
        populateMAP.put(sq.next(), mode);
        populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
        populateMAP.put(sq.next(), userVO.getHospitalCode());
        
        
        try
     	{
     		ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);	 
 	 	    if(!rs.next())
 	 	    {
 	 	    	throw new HisRecordNotFoundException("No Record Found");	 	    
 	 	    }
 	 	    rs.beforeFirst();
 	 	   
 	 	    vo=HelperMethods.populateVOfrmRS(PatIntakeOutDtlVO.class, rs);
 	 	  arrPatOuttakeDtlVO= new PatIntakeOutDtlVO[vo.length];
 	 	    for(int i=0;i<vo.length;i++)
 	 	    {
 	 	    	arrPatOuttakeDtlVO[i]= (PatIntakeOutDtlVO) vo[i];
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
 		return arrPatOuttakeDtlVO;
	}
	
	public PatIntakeOutDtlVO[] getIntakeSummary(String patCrNo,UserVO userVO)
	{
		PatIntakeOutDtlVO[] arrPatIntakeSummaryVO=null;
		ValueObject vo[]= null;
		String query  = "";
        Map populateMAP =new HashMap();
        Sequence sq=new Sequence();
        String filename=InpatientConfig.QUERY_FILE_FOR_INPATIENT_DAO;
        String queryKey="SELECT_IN_OUT_TAKE_SUMMARY.HIPD_PAT_INTAKEOUT_DTL";
        
        try
        {
        	query =HelperMethodsDAO.getQuery(filename,queryKey);
        }
        catch(Exception e)
        {
        	throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);
        }
        
       // populateMAP.put(sq.next(), userVO.getHospitalCode());
        populateMAP.put(sq.next(), InpatientConfig.INTAKEOUT_MODE_INTAKE);
        populateMAP.put(sq.next(), patCrNo);
        populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
        populateMAP.put(sq.next(), userVO.getHospitalCode());
        
        
        try
     	{
     		ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);	 
 	 	   /* if(!rs.next())
 	 	    {
 	 	    	throw new HisRecordNotFoundException("No In Take Summary Found");	 	    
 	 	    }*/
 	 	    rs.beforeFirst();
 	 	   
 	 	    vo=HelperMethods.populateVOfrmRS(PatIntakeOutDtlVO.class, rs);
 	 	  arrPatIntakeSummaryVO= new PatIntakeOutDtlVO[vo.length];
 	 	    for(int i=0;i<vo.length;i++)
 	 	    {
 	 	    	arrPatIntakeSummaryVO[i]= (PatIntakeOutDtlVO) vo[i];
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
 		return arrPatIntakeSummaryVO;
	}
	
	public PatIntakeOutDtlVO[] getOuttakeSummary(String patCrNo,UserVO userVO)
	{
		PatIntakeOutDtlVO[] arrPatOuttakeSummaryVO=null;
		ValueObject vo[]= null;
		String query  = "";
        Map populateMAP =new HashMap();
        Sequence sq=new Sequence();
        String filename=InpatientConfig.QUERY_FILE_FOR_INPATIENT_DAO;
        String queryKey="SELECT_IN_OUT_TAKE_SUMMARY.HIPD_PAT_INTAKEOUT_DTL";
        
        try
        {
        	query =HelperMethodsDAO.getQuery(filename,queryKey);
        }
        catch(Exception e)
        {
        	throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);
        }
        
      //  populateMAP.put(sq.next(), userVO.getHospitalCode());
        populateMAP.put(sq.next(), InpatientConfig.INTAKEOUT_MODE_OUTTAKE);
        populateMAP.put(sq.next(), patCrNo);
        populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
        populateMAP.put(sq.next(), userVO.getHospitalCode());
        
        
        try
     	{
     		ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);	 
 	 	    /*if(!rs.next())
 	 	    {
 	 	    	throw new HisRecordNotFoundException("No Out Take Summary Found");	 	    
 	 	    }*/
 	 	    rs.beforeFirst();
 	 	   
 	 	    vo=HelperMethods.populateVOfrmRS(PatIntakeOutDtlVO.class, rs);
 	 	  arrPatOuttakeSummaryVO= new PatIntakeOutDtlVO[vo.length];
 	 	    for(int i=0;i<vo.length;i++)
 	 	    {
 	 	    	arrPatOuttakeSummaryVO[i]= (PatIntakeOutDtlVO) vo[i];
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
 		return arrPatOuttakeSummaryVO;
	}
	
	public PatIntakeOutDtlVO[] getViewSummaryDetail(String admNo,String fromDate,String toDate,UserVO userVO)
	{
		PatIntakeOutDtlVO[] arrPatOuttakeSummaryVO=null;
		ValueObject vo[]= null;
		String query  = "";
        Map populateMAP =new HashMap();
        Sequence sq=new Sequence();
        String filename=InpatientConfig.QUERY_FILE_FOR_INPATIENT_DAO;
        String queryKey="GET_IN_OUT_TAKE_VIEW_SUMMARY.HIPD_PAT_INTAKEOUT_DTL";
        
        try
        {
        	query =HelperMethodsDAO.getQuery(filename,queryKey);
        }
        catch(Exception e)
        {
        	throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);
        }
        
        
        populateMAP.put(sq.next(), admNo);
        populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
        populateMAP.put(sq.next(), userVO.getHospitalCode());
        populateMAP.put(sq.next(), fromDate);
        populateMAP.put(sq.next(), toDate);
        
        try
     	{
     		ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);	 
 	 	    /*if(!rs.next())
 	 	    {
 	 	    	throw new HisRecordNotFoundException("No Out Take Summary Found");	 	    
 	 	    }*/
 	 	    rs.beforeFirst();
 	 	   
 	 	    vo=HelperMethods.populateVOfrmRS(PatIntakeOutDtlVO.class, rs);
 	 	  arrPatOuttakeSummaryVO= new PatIntakeOutDtlVO[vo.length];
 	 	    for(int i=0;i<vo.length;i++)
 	 	    {
 	 	    	arrPatOuttakeSummaryVO[i]= (PatIntakeOutDtlVO) vo[i];
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
 		return arrPatOuttakeSummaryVO;
	}
	
	public PatIntakeOutDtlVO[] getOutParaDetailEMR(String mode,PatientDetailVO dailyPatVO,String []departmentUnitArray,String accessType,UserVO userVO)
	{
		PatIntakeOutDtlVO[] arrPatOuttakeDtlVO=null;
		ValueObject vo[]= null;
		String query  = "";
        Map populateMAP =new HashMap();
        Sequence sq=new Sequence();
        String filename=InpatientConfig.QUERY_FILE_FOR_INPATIENT_DAO;
        String queryKey="SELECT_OUT_TAKE_DETAIL.HIPD_PAT_INTAKEOUT_DTL_EMR";
        String orderBy=" ORDER BY A.HIPDT_INTAKEOUT_TIME DESC,inTakeOutParaName";
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
        
        populateMAP.put(sq.next(), dailyPatVO.getPatCrNo());
        populateMAP.put(sq.next(), mode);
        populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
       // populateMAP.put(sq.next(), userVO.getHospitalCode());
        
        
        try
     	{
     		ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);	 
 	 	    if(!rs.next())
 	 	    {
 	 	    	//throw new HisRecordNotFoundException("No Record Found");	 	    
 	 	    }
 	 	    rs.beforeFirst();
 	 	   
 	 	    vo=HelperMethods.populateVOfrmRS(PatIntakeOutDtlVO.class, rs);
 	 	  arrPatOuttakeDtlVO= new PatIntakeOutDtlVO[vo.length];
 	 	    for(int i=0;i<vo.length;i++)
 	 	    {
 	 	    	arrPatOuttakeDtlVO[i]= (PatIntakeOutDtlVO) vo[i];
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
 		return arrPatOuttakeDtlVO;
	}

	@Override
	public void getSnomedIdTerm(PatIntakeOutDtlVO patIntakeOutDtlVO, UserVO userVO) {
		// TODO Auto-generated method stub
		//PatIntakeOutDtlVO arrPatOuttakeDtlVO=null;
	//	ValueObject vo[]= null;
		String query  = "";
        Map populateMAP =new HashMap();
        Sequence sq=new Sequence();
        String filename=InpatientConfig.QUERY_FILE_FOR_INPATIENT_DAO;
        String queryKey="SELECT_SNOMED_ID_TERM_hipd_intakeout_para_mst";
        
        try
        {
        	query =HelperMethodsDAO.getQuery(filename,queryKey);
        }
        catch(Exception e)
        {
        	throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);
        }
        
     //   populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
        populateMAP.put(sq.next(), patIntakeOutDtlVO.getInTakeOutParaName());
        populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
        populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
        populateMAP.put(sq.next(), patIntakeOutDtlVO.getInTakeOutParaId());
     
        
        
        try
     	{
     		ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);	 
 	 	    if(!rs.next())
 	 	    {
 	 	    	throw new HisRecordNotFoundException("No Record Found");	 	    
 	 	    }
 	 	    rs.beforeFirst();
 	 	    while(rs.next())
 	 	    {
 	 	      patIntakeOutDtlVO.setSnomedParaId(rs.getString(1));
 	  	 	patIntakeOutDtlVO.setSnomedPtermPara(rs.getString(2));
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
 		//return arrPatOuttakeDtlVO;
		
	}
}
