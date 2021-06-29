package mortuary.transaction.dao;

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
import hisglobal.vo.PostmortemOpinionDetailVO;
import hisglobal.vo.UserVO;
import hisglobal.vo.ValueObject;

public class PostmortemOpinionDetailDAO extends DataAccessObject implements PostmortemOpinionDetailDAOi
{
	public PostmortemOpinionDetailDAO(TransactionContext _tx)
	{
		super(_tx);
	}
	
	//Inserting Record
	public void create(PostmortemOpinionDetailVO postmortemOpinionVO, UserVO userVO)
	{
		String query  = "";
	    Map populateMAP =new HashMap();
	    Sequence sq=new Sequence();
	    String filename= MortuaryConfig.QUERY_FILE_FOR_MORTUARY_DAO;
	    String queryKey="INSERT.HMRT_POSTMORTEM_OPINION_DTL";
	    
	   
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
	    	populateMAP.put(sq.next(), postmortemOpinionVO.getPostmortemId());
	    	populateMAP.put(sq.next(), postmortemOpinionVO.getOpinionCode());
	    	populateMAP.put(sq.next(), postmortemOpinionVO.getOpinion());
	    	populateMAP.put(sq.next(), userVO.getSeatId());
	    	populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
	    	populateMAP.put(sq.next(), postmortemOpinionVO.getPostmortemId());
	    	populateMAP.put(sq.next(), userVO.getHospitalCode());
	    	      	
	    }
	    catch(Exception e)
	    {
	    	throw new HisApplicationExecutionException("PostmortemOpinionDetailDAO.populateMAP::"+e);
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
	
	public PostmortemOpinionDetailVO[] getAddedOpinion(String postmortemId,UserVO userVO)
	{
		PostmortemOpinionDetailVO[] arrOpinionVO = null;
		ValueObject vo[] = null;
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MortuaryConfig.QUERY_FILE_FOR_MORTUARY_ESSENTIALDAO;
		String queryKey = "GET_ADDED_OPINION.HMRT_POSTMORTEM_OPINION_DTL";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), postmortemId);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), userVO.getHospitalCode());

		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Deceased in Stretcher");
			}
			else
			{
				rs.beforeFirst();
	
				vo = HelperMethods.populateVOfrmRS(PostmortemOpinionDetailVO.class, rs);
				arrOpinionVO = new PostmortemOpinionDetailVO[vo.length];
				for (int i = 0; i < vo.length; i++)
				{
					arrOpinionVO[i] = (PostmortemOpinionDetailVO) vo[i];
				}
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

		return arrOpinionVO;
	}
	
	public void invalidTheRecord(String postmortemId, UserVO userVO)
	{
		String query  = "";
	    Map populateMAP =new HashMap();
	    Sequence sq=new Sequence();
	    String filename= MortuaryConfig.QUERY_FILE_FOR_MORTUARY_DAO;
	    String queryKey="UPDATE_INVALID_STATUS.HMRT_POSTMORTEM_OPINION_DTL";
	    
	    try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		
		
		populateMAP.put(sq.next(), Config.IS_VALID_INACTIVE);
		populateMAP.put(sq.next(), postmortemId);
		populateMAP.put(sq.next(), userVO.getHospitalCode());
		
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
	
	public void updateOpinion(PostmortemOpinionDetailVO postmortemOpinionVO, UserVO userVO)
	{
		String query  = "";
	    Map populateMAP =new HashMap();
	    Sequence sq=new Sequence();
	    String filename= MortuaryConfig.QUERY_FILE_FOR_MORTUARY_DAO;
	    String queryKey="UPDATE_OPINION.HMRT_POSTMORTEM_OPINION_DTL";
	    
	    try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		
		populateMAP.put(sq.next(), postmortemOpinionVO.getOpinion());
		populateMAP.put(sq.next(), postmortemOpinionVO.getPostmortemId());
		populateMAP.put(sq.next(), postmortemOpinionVO.getOpinionCode());
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		
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
