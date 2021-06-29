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
import hisglobal.vo.PatientMonitoringMstVO;
import hisglobal.vo.UserVO;
import hisglobal.vo.ValueObject;

public class MonitorVitalsDtlDAO extends DataAccessObject
{
	public MonitorVitalsDtlDAO(TransactionContext _tx)
	{
		super(_tx);
	}
	
	public void saveVitalsDetail(PatientMonitoringMstVO patMonitringMstVO,UserVO userVO)
	{
		String query  = "";
        Map populateMAP =new HashMap();
        Sequence sq=new Sequence();
        String filename=InpatientConfig.QUERY_FILE_FOR_INPATIENT_DAO;
        String queryKey="INSERT.HIPD_PAT_MONITORING_MST";
               
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
        	populateMAP.put(sq.next(),patMonitringMstVO.getPatCrNo());
        	populateMAP.put(sq.next(),patMonitringMstVO.getEpisodeCode());
        	populateMAP.put(sq.next(),patMonitringMstVO.getEpisodeVisitNo());
        	populateMAP.put(sq.next(),userVO.getHospitalCode());
        	populateMAP.put(sq.next(),patMonitringMstVO.getPatCrNo());
        	populateMAP.put(sq.next(),patMonitringMstVO.getEpisodeCode());
        	populateMAP.put(sq.next(),patMonitringMstVO.getEpisodeVisitNo());
        	populateMAP.put(sq.next(),patMonitringMstVO.getParaId());
        	populateMAP.put(sq.next(),patMonitringMstVO.getAdmissionNo());
        	populateMAP.put(sq.next(),patMonitringMstVO.getRemarks());
        	populateMAP.put(sq.next(),patMonitringMstVO.getDuration());
        	populateMAP.put(sq.next(),userVO.getUserEmpID());
        	populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
        	populateMAP.put(sq.next(),patMonitringMstVO.getVarifyFlag());
        	populateMAP.put(sq.next(),userVO.getSeatId());//seat id
        	//populateMAP.put(sq.next(),userVO.getSeatId());//nurse seat id
        	populateMAP.put(sq.next(),userVO.getSeatId());//doctor seat id
        	populateMAP.put(sq.next(),userVO.getHospitalCode());
        	populateMAP.put(sq.next(), userVO.getIpAddress());
        	populateMAP.put(sq.next(),patMonitringMstVO.getInstructionMode());
        	populateMAP.put(sq.next(),patMonitringMstVO.getDayFrequency());
        	populateMAP.put(sq.next(),patMonitringMstVO.getMonitorMode());
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
	
	public PatientMonitoringMstVO[] getVitalDetail(PatientMonitoringMstVO patMonitringMstVO, UserVO userVO)
	{
		PatientMonitoringMstVO[] _patMonitringMstVO=null;
		ValueObject vo[]= null;
		String query  = "";
        Map populateMAP =new HashMap();
        Sequence sq=new Sequence();
        String filename=InpatientConfig.QUERY_FILE_FOR_INPATIENT_DAO;
        String queryKey="SELECT_VITAL_DETAIL.HIPD_PAT_MONITORING_MST";
        
        try
        {
        	query =HelperMethodsDAO.getQuery(filename,queryKey);
        }
        catch(Exception e)
        {
        	throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);
        }
        
        populateMAP.put(sq.next(),patMonitringMstVO.getPatCrNo());
    	populateMAP.put(sq.next(),patMonitringMstVO.getEpisodeCode());
    	populateMAP.put(sq.next(),patMonitringMstVO.getEpisodeVisitNo());
    	populateMAP.put(sq.next(), userVO.getHospitalCode());
        populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
        
        try
     	{
     		ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);	 
 	 	    /*if(!rs.next())
 	 	    {
 	 	    	throw new HisRecordNotFoundException("No Unverified Record Found");	 	    
 	 	    }*/
 	 	    rs.beforeFirst();
 	 	   
 	 	    vo=HelperMethods.populateVOfrmRS(PatientMonitoringMstVO.class, rs);
 	 	  _patMonitringMstVO= new PatientMonitoringMstVO[vo.length];
 	 	    for(int i=0;i<vo.length;i++)
 	 	    {
 	 	    	_patMonitringMstVO[i]= (PatientMonitoringMstVO) vo[i];
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
 		return _patMonitringMstVO;
		
	}
	
	public void updateVitalsDetail(PatientMonitoringMstVO patMonitringMstVO,UserVO userVO)

	{
		String query  = "";
        Map populateMAP =new HashMap();
        Sequence sq=new Sequence();
        String filename=InpatientConfig.QUERY_FILE_FOR_INPATIENT_DAO;
        String queryKey="UPDATE_VITAL_DETAIL.HIPD_PAT_MONITORING_MST";
        
       
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
        	populateMAP.put(sq.next(),patMonitringMstVO.getRemarks());
        	populateMAP.put(sq.next(),patMonitringMstVO.getDuration());
        	populateMAP.put(sq.next(),patMonitringMstVO.getDayFrequency());
        	populateMAP.put(sq.next(),patMonitringMstVO.getMonitorMode());
        	
        	populateMAP.put(sq.next(),patMonitringMstVO.getPatCrNo());
        	populateMAP.put(sq.next(),patMonitringMstVO.getEpisodeCode());
        	populateMAP.put(sq.next(),patMonitringMstVO.getEpisodeVisitNo());
        	populateMAP.put(sq.next(),userVO.getHospitalCode());
        	populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
        	populateMAP.put(sq.next(), patMonitringMstVO.getSerialNo());
        	populateMAP.put(sq.next(),patMonitringMstVO.getParaId());
        	
        	
        	
        	
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
	
	public void revokeVitals(String paraId, PatientMonitoringMstVO patMonitoringVitalVO, UserVO userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		String filename = InpatientConfig.QUERY_FILE_FOR_INPATIENT_DAO;
		String queryKey = "REVOKE_VITALS.HIPD_PAT_MONITORING_MST";
		Sequence sq = new Sequence();

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
			populateMAP.put(sq.next(), patMonitoringVitalVO.getPatCrNo());
			populateMAP.put(sq.next(), patMonitoringVitalVO.getEpisodeCode());
			populateMAP.put(sq.next(), patMonitoringVitalVO.getEpisodeVisitNo());
			populateMAP.put(sq.next(), paraId);
			populateMAP.put(sq.next(), userVO.getHospitalCode());
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("MonitorVitalsDtlDAO.populateMAP::" + e);
		}
		try
		{
			HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), query, populateMAP);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException("HisDataAccessException While UPDATING");
		}
	}
}
