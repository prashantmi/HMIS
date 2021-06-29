package registration.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import registration.RegistrationConfig;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.TransactionContext;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.Sequence;
import hisglobal.vo.MlcVO;
import hisglobal.vo.PoliceVerificationDtlVO;
import hisglobal.vo.UserVO;

public class PoliceVerificationDAO extends DataAccessObject implements PoliceVerificationDAOi
{
	public PoliceVerificationDAO(TransactionContext _transactionContext)
	{
		super(_transactionContext);
	}
	
	public void create(PoliceVerificationDtlVO policeVerDtlVO,UserVO userVO)
	{
		String query  = "";
        Map populateMAP =new HashMap();
        Sequence sq=new Sequence();
        String filename=RegistrationConfig.QUERY_FILE_FOR_DAO;
        String queryKey="INSERT.HRGT_POLICE_VERIFICATION_DTL";
        
        
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
        	populateMAP.put(sq.next(), policeVerDtlVO.getPatCrNo() );
        	populateMAP.put(sq.next(), policeVerDtlVO.getEpisodeCode());
        	populateMAP.put(sq.next(), policeVerDtlVO.getPatCrNo() );
        	populateMAP.put(sq.next(), policeVerDtlVO.getEpisodeCode());
        	populateMAP.put(sq.next(), policeVerDtlVO.getEpisodeVisitNo());
        	populateMAP.put(sq.next(), policeVerDtlVO.getEpisodeVisitNo());
        	populateMAP.put(sq.next(), policeVerDtlVO.getPatMlcNo());
        	populateMAP.put(sq.next(), policeVerDtlVO.getCaseNo());
        	populateMAP.put(sq.next(), policeVerDtlVO.getPoliceStation() );
        	populateMAP.put(sq.next(), policeVerDtlVO.getDocketNo());
        	populateMAP.put(sq.next(), policeVerDtlVO.getOfficerIncharge());
        	populateMAP.put(sq.next(), policeVerDtlVO.getIoDesignation());
        	populateMAP.put(sq.next(), policeVerDtlVO.getIoBatchNo());
        	populateMAP.put(sq.next(), policeVerDtlVO.getDutyOffName());
        	populateMAP.put(sq.next(), userVO.getSeatId());
        	populateMAP.put(sq.next(), policeVerDtlVO.getDutyOffDesignation());
        	populateMAP.put(sq.next(), policeVerDtlVO.getDutyOffBatchNo());
        	populateMAP.put(sq.next(), policeVerDtlVO.getIsBroughtDead());
        	populateMAP.put(sq.next(), policeVerDtlVO.getCaseRemarks());
        	populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
        	populateMAP.put(sq.next(), userVO.getHospitalCode());
        	
        }
        catch(Exception e)
        {
        	throw new HisApplicationExecutionException("PoliceVerificationDAO.populateMAP::"+e);
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
	
	public PoliceVerificationDtlVO select(MlcVO mlcVO,UserVO userVO)
	{
		PoliceVerificationDtlVO policeVerDtlVO=new PoliceVerificationDtlVO();
		String query="";
		Map populateMap= new HashMap();
		String filename= RegistrationConfig.QUERY_FILE_FOR_DAO;
		String queryKey="SELECT.HRGT_POLICE_VERIFICATION_DTL";
		
		Sequence sq= new Sequence();
		
		Connection conn=super.getTransactionContext().getConnection();
		
		populateMap.put(sq.next(),mlcVO.getPatCrNo());
		populateMap.put(sq.next(),mlcVO.getEpisodeCode());
		populateMap.put(sq.next(),mlcVO.getEpisodeVisitNo());
		populateMap.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMap.put(sq.next(),mlcVO.getPatCrNo());
		populateMap.put(sq.next(),mlcVO.getEpisodeCode());
		populateMap.put(sq.next(),mlcVO.getEpisodeVisitNo());
		populateMap.put(sq.next(),userVO.getHospitalCode());
		
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
				//throw new HisRecordNotFoundException();
				policeVerDtlVO=null;
			}
			else
			{
				rs.beforeFirst();
				while(rs.next())
				{
					HelperMethods.populateVOfrmRS(policeVerDtlVO,rs);
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
		return policeVerDtlVO;
	}
}
