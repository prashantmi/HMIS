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
import hisglobal.utility.HelperMethods;
import hisglobal.utility.Sequence;
import hisglobal.vo.DeceasedRelativeDtlVO;
import hisglobal.vo.PostmortemOpinionReqDtlVO;
import hisglobal.vo.UserVO;
import hisglobal.vo.ValueObject;

public class PostmortemOpinionReqDAO extends DataAccessObject implements PostmortemOpinionReqDAOi
{
	public PostmortemOpinionReqDAO(TransactionContext _tx)
	{
		super(_tx);
	}
	

	//Inserting Record
	public void create(PostmortemOpinionReqDtlVO postmortmOpinionReqVO, UserVO userVO)
	{
		String query  = "";
	    Map populateMAP =new HashMap();
	    Sequence sq=new Sequence();
	    String filename= MortuaryConfig.QUERY_FILE_FOR_MORTUARY_DAO;
	    String queryKey="INSERT.HMRT_POSTMORTEM_OPINIONREQ_DTL";
	    
	   
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
	    	populateMAP.put(sq.next(), postmortmOpinionReqVO.getPostmortemId());
	    	populateMAP.put(sq.next(), postmortmOpinionReqVO.getOpinionCode());
	    	populateMAP.put(sq.next(), postmortmOpinionReqVO.getRemarks());
	    	populateMAP.put(sq.next(), userVO.getSeatId());
	    	populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
	    	populateMAP.put(sq.next(), postmortmOpinionReqVO.getPostmortemId());
	    	populateMAP.put(sq.next(), userVO.getHospitalCode());
	    	      	
	    }
	    catch(Exception e)
	    {
	    	throw new HisApplicationExecutionException("PostmortemOpinionReqDAO.populateMAP::"+e);
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
	
	public PostmortemOpinionReqDtlVO[] getRequestedOpinion(String postmortemId, UserVO userVO)
	{
		PostmortemOpinionReqDtlVO[] arrOpinionReqVO=null;
		ValueObject vo[] = null;
		String query="";
		Map populateMap= new HashMap();
		String filename= MortuaryConfig.QUERY_FILE_FOR_MORTUARY_ESSENTIALDAO;
		String queryKey="GET_REQUESTED_OPINION.HMRT_POSTMORTEM_OPINIONREQ_DTL";
		
		Sequence sq= new Sequence();
		
		Connection conn=super.getTransactionContext().getConnection();
		
		populateMap.put(sq.next(),postmortemId);
		populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
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
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMap);
			if (!rs.next())
			{
				arrOpinionReqVO=null;
			}
			else
			{
				rs.beforeFirst();
	
				vo = HelperMethods.populateVOfrmRS(PostmortemOpinionReqDtlVO.class, rs);
				arrOpinionReqVO = new PostmortemOpinionReqDtlVO[vo.length];
				for (int i = 0; i < vo.length; i++)
				{
					arrOpinionReqVO[i] = (PostmortemOpinionReqDtlVO) vo[i];
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
		return arrOpinionReqVO;
	}
	
	public void update(PostmortemOpinionReqDtlVO postmortmOpinionReqVO, UserVO userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MortuaryConfig.QUERY_FILE_FOR_MORTUARY_ESSENTIALDAO;
		String queryKey = "UPDATE.HMRT_POSTMORTEM_OPINIONREQ_DTL";
		
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		
		populateMAP.put(sq.next(), userVO.getSeatId());
		populateMAP.put(sq.next(), postmortmOpinionReqVO.getPostmortemId());
		populateMAP.put(sq.next(), postmortmOpinionReqVO.getOpinionCode());
		
		try
		{
			HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("Application Execution Exception" + e);
		}
	}
	
}
