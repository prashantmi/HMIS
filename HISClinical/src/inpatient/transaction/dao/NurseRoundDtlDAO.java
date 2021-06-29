package inpatient.transaction.dao;

import inpatient.InpatientConfig;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.TransactionContext;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.Sequence;
import hisglobal.vo.NurseRoundDtlVO;
import hisglobal.vo.UserVO;
import hisglobal.vo.ValueObject;

public class NurseRoundDtlDAO extends DataAccessObject implements NurseRoundDtlDAOi {

	public  NurseRoundDtlDAO(TransactionContext _tx) 
	{
		super(_tx);
	}
	
	public void create(NurseRoundDtlVO nurseRoundDtlVO,UserVO userVO){
		String query  = "";
        Map populateMAP =new HashMap();
        Sequence sq=new Sequence();
        String filename=InpatientConfig.QUERY_FILE_FOR_INPATIENT_DAO;
        String queryKey="INSERT.HIPD_NURSE_ROUND_DTL";
        
       
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
        	populateMAP.put(sq.next(),nurseRoundDtlVO.getPatCrNo());
        	populateMAP.put(sq.next(),nurseRoundDtlVO.getEpisodeCode());
        	populateMAP.put(sq.next(),nurseRoundDtlVO.getEpisodeVisitNo());
        	populateMAP.put(sq.next(),nurseRoundDtlVO.getPatCrNo());
        	populateMAP.put(sq.next(),nurseRoundDtlVO.getPatAdmNo());
        	populateMAP.put(sq.next(),nurseRoundDtlVO.getProgressNote());
        	populateMAP.put(sq.next(),nurseRoundDtlVO.getStatusCode());
        	populateMAP.put(sq.next(), userVO.getUserEmpID());
        	populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
        	populateMAP.put(sq.next(),userVO.getSeatId());
        	populateMAP.put(sq.next(),userVO.getHospitalCode());
        	populateMAP.put(sq.next(),nurseRoundDtlVO.getLastModSeatId());
        	populateMAP.put(sq.next(),userVO.getIpAddress());
        	populateMAP.put(sq.next(),nurseRoundDtlVO.getLastModDate());
        	populateMAP.put(sq.next(),nurseRoundDtlVO.getSnomdCIdProgressNotes());
        	populateMAP.put(sq.next(),nurseRoundDtlVO.getSnomdPTProgessNotes());
        	
        }
        catch(Exception e)
        {
        	throw new HisApplicationExecutionException("NurseRoundDtlDAO.populateMAP::"+e);
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
	
	public NurseRoundDtlVO[] getPreviousProgressNotes(String patAdmNo,UserVO userVO)
	{
		NurseRoundDtlVO[] arrNursePatPrevProgressNotes=null;
		ValueObject vo[]= null;
		String query  = "";
        Map populateMAP =new HashMap();
        Sequence sq=new Sequence();
        String filename=InpatientConfig.QUERY_FILE_FOR_INPATIENT_DAO;
        String queryKey="SELECT_PAT_PREVIOUS_PROGRESS_NOTERS_HIPD_NURSE_ROUND_DTL";
        
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
        
        try
     	{
     		ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);	 
 	 	    if(!rs.next())
 	 	    {
 	 	    	throw new HisRecordNotFoundException("No Previous Progress Notes Found");	 	    
 	 	    }
 	 	    rs.beforeFirst();
 	 	   
 	 	    vo=HelperMethods.populateVOfrmRS(NurseRoundDtlVO.class, rs);
 	 	  arrNursePatPrevProgressNotes= new NurseRoundDtlVO[vo.length];
 	 	    for(int i=0;i<vo.length;i++)
 	 	    {
 	 	    	arrNursePatPrevProgressNotes[i]= (NurseRoundDtlVO) vo[i];
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
 		return arrNursePatPrevProgressNotes;
	}
}
