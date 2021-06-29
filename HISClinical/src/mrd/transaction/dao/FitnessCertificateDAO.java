package mrd.transaction.dao;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.TransactionContext;
import hisglobal.utility.Sequence;
import hisglobal.vo.PatFitnessDtlVO;
import hisglobal.vo.UserVO;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import mrd.MrdConfig;

public class FitnessCertificateDAO extends DataAccessObject implements FitnessCertificateDAOi
{
	public  FitnessCertificateDAO(TransactionContext _tx) 
	{
		super(_tx);
	}
	
	//  Saving the Fitness Date of The Patient
	public void create(PatFitnessDtlVO patFitnessDtlVO,String unitCode,String genMode,UserVO userVO,String id)
	{
		String query  = "";
        Map populateMAP =new HashMap();
        Sequence sq=new Sequence();
        String filename=MrdConfig.QUERY_FILE_FOR_MRD_DAO;
        String queryKey="INSERT.HRGT_PAT_FITNESS_DTL";
        
       
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
        	populateMAP.put(sq.next(), patFitnessDtlVO.getPatCrNo());
        	populateMAP.put(sq.next(), id);
        	populateMAP.put(sq.next(), patFitnessDtlVO.getEpisodeCode());
        	populateMAP.put(sq.next(), patFitnessDtlVO.getEpisodeVisitNo());
        	populateMAP.put(sq.next(), id);
        	populateMAP.put(sq.next(), userVO.getUserEmpID());
        	populateMAP.put(sq.next(), patFitnessDtlVO.getFitnessCertificateDesc());
        	populateMAP.put(sq.next(), patFitnessDtlVO.getPatAdmNo());
        	populateMAP.put(sq.next(), patFitnessDtlVO.getMedicalCertificateDesc());
        	populateMAP.put(sq.next(), patFitnessDtlVO.getSufferingFrom());
        	populateMAP.put(sq.next(), patFitnessDtlVO.getMedicalCertificateId());
        	populateMAP.put(sq.next(), patFitnessDtlVO.getFitnessDate());
        	populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
        	populateMAP.put(sq.next(), userVO.getSeatId());
        	populateMAP.put(sq.next(), userVO.getHospitalCode());
        	
        	
        	      	
        }
        catch(Exception e)
        {
        	throw new HisApplicationExecutionException("MedicalCertificateDAO.populateMAP::"+e);
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
	
	//  Checking For Already Exist Fitness Date
	public String checkEsistingFitnessDate(PatFitnessDtlVO patFitnessDtlVO,UserVO userVO)
	{
		String query="";
		Map populateMap= new HashMap();
		String filename= MrdConfig.QUERY_FILE_FOR_MRD_DAO;
		String queryKey="CHECK_EXISTING_RECORD.HRGT_PAT_FITNESS_DTL";
		ResultSet rs;
		Sequence sq= new Sequence();
		
		
		populateMap.put(sq.next(), patFitnessDtlVO.getPatCrNo());
		populateMap.put(sq.next(), patFitnessDtlVO.getEpisodeCode());
		populateMap.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMap.put(sq.next(), userVO.getHospitalCode());
		populateMap.put(sq.next(), patFitnessDtlVO.getFitnessDate());
		
		
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
        	rs=HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(),query,populateMap);
        	if(!rs.next())
        		throw new HisRecordNotFoundException("No Record Found ");
            rs.first();
            return rs.getString(1);
        }
        catch(Exception e)
        {
        	if(e.getClass()==HisRecordNotFoundException.class)
        	{
        		throw new HisRecordNotFoundException(e.getMessage());	
        	}
        	else
        		throw new HisDataAccessException("HelperMethodsDAO.getResultset"+e);
        }
	}
	
	public String generateFitnessCertificateId(PatFitnessDtlVO patFitnessDtlVO,String unitCode,String genMode,UserVO userVO)
	{
		String query="";
		Map populateMap= new HashMap();
		String filename= MrdConfig.QUERY_FILE_FOR_MRD_DAO;
		String queryKey="GENERATE_CERTIFICATE_ID";
		ResultSet rs;
		Sequence sq= new Sequence();
		
		//Connection conn=super.getTransactionContext().getConnection();
		
		populateMap.put(sq.next(), userVO.getHospitalCode());
		populateMap.put(sq.next(), MrdConfig.RECORD_TYPE_FITNESS);
		populateMap.put(sq.next(), unitCode);
		populateMap.put(sq.next(), genMode);
		populateMap.put(sq.next(), patFitnessDtlVO.getFitnessCertificateDesc());
		
		
		
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
        	rs=HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(),query,populateMap);
        	if(!rs.next())
        		throw new HisRecordNotFoundException("");
            rs.first();
            return rs.getString(1);
        }
        catch(Exception e)
        {
        	if(e.getClass()==HisRecordNotFoundException.class)
        	{
        		throw new HisRecordNotFoundException(e.getMessage());	
        	}
        	else
        		throw new HisDataAccessException("HelperMethodsDAO.getResultset"+e);
        }
	}
}
