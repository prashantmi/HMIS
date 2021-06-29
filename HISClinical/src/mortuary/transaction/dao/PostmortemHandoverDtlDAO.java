package mortuary.transaction.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import mortuary.MortuaryConfig;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.TransactionContext;
import hisglobal.utility.Sequence;
import hisglobal.vo.PostmortemHandoverDtlVO;
import hisglobal.vo.UserVO;

public class PostmortemHandoverDtlDAO extends DataAccessObject implements PostmortemHandoverDtlDAOi
{
	public PostmortemHandoverDtlDAO(TransactionContext _tx)
	{
		super(_tx);
	}
	
	//Inserting Record
	public void create(PostmortemHandoverDtlVO postmortemHandoverDtlVO, UserVO userVO)
	{
		String query  = "";
	    Map populateMAP =new HashMap();
	    Sequence sq=new Sequence();
	    String filename= MortuaryConfig.QUERY_FILE_FOR_MORTUARY_DAO;
	    String queryKey="INSERT.HMRT_POSTMORTEM_HANDOVER_DTL";
	    
	   
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
	    	populateMAP.put(sq.next(), postmortemHandoverDtlVO.getPostmortemId());
	    	populateMAP.put(sq.next(), postmortemHandoverDtlVO.getPostmortemId());
	    	populateMAP.put(sq.next(), postmortemHandoverDtlVO.getIsHandoverTo());
	    	populateMAP.put(sq.next(), postmortemHandoverDtlVO.getEmpId());
	    	populateMAP.put(sq.next(), postmortemHandoverDtlVO.getHandoverToName());
	    	populateMAP.put(sq.next(), postmortemHandoverDtlVO.getHandoverToAddress());
	    	populateMAP.put(sq.next(), postmortemHandoverDtlVO.getHandoverToPhone() );
	    	populateMAP.put(sq.next(), postmortemHandoverDtlVO.getOfficerDesignation());
	    	populateMAP.put(sq.next(), postmortemHandoverDtlVO.getRelativeCode());
	    	populateMAP.put(sq.next(), userVO.getIpAddress());
	    	populateMAP.put(sq.next(), postmortemHandoverDtlVO.getOfficerBadgeNo());
	    	populateMAP.put(sq.next(), userVO.getUserEmpID());
	    	populateMAP.put(sq.next(), userVO.getSeatId());
	    	populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
	    	populateMAP.put(sq.next(), userVO.getHospitalCode());
	    	      	
	    }
	    catch(Exception e)
	    {
	    	throw new HisApplicationExecutionException("PostmortemHandoverDtlDAO.populateMAP::"+e);
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
	
	public boolean checkForPostmortemHandover(String deceasedNo,UserVO userVO)
	{
		boolean exist=false;
		String query  = "";
	    Map populateMAP =new HashMap();
	    Sequence sq=new Sequence();
	    String filename= MortuaryConfig.QUERY_FILE_FOR_MORTUARY_DAO;
	    String queryKey="CHECK_EXISTING_RECORD.HMRT_POSTMORTEM_HANDOVER_DTL";
	    Connection conn=super.getTransactionContext().getConnection();
	    
	    
	    populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
	    populateMAP.put(sq.next(), userVO.getHospitalCode());
	    populateMAP.put(sq.next(), deceasedNo);
	    
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
			ResultSet rs = HelperMethodsDAO.executeQuery(conn, query, populateMAP);
			
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
}
