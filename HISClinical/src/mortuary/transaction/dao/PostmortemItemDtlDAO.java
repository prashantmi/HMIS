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
import hisglobal.vo.PostmortemItemDtlVO;
import hisglobal.vo.UserVO;
import hisglobal.vo.ValueObject;

public class PostmortemItemDtlDAO extends DataAccessObject implements PostmortemItemDtlDAOi
{
	public PostmortemItemDtlDAO(TransactionContext _tx)
	{
		super(_tx);
	}
	
	//Inserting Record
	public void create(PostmortemItemDtlVO postmortemItemDtlVO, UserVO userVO)
	{
		String query  = "";
	    Map populateMAP =new HashMap();
	    Sequence sq=new Sequence();
	    String filename= MortuaryConfig.QUERY_FILE_FOR_MORTUARY_DAO;
	    String queryKey="INSERT.HMRT_POSTMORTEM_ITEM_DTL";
	    
	   
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
	    	populateMAP.put(sq.next(), postmortemItemDtlVO.getPostmortemId());
	    	populateMAP.put(sq.next(), postmortemItemDtlVO.getItemCode());
	    	populateMAP.put(sq.next(), postmortemItemDtlVO.getRemarks());
	    	populateMAP.put(sq.next(), postmortemItemDtlVO.getIsPreserved());
	    	populateMAP.put(sq.next(), postmortemItemDtlVO.getPreservativeId());
	    	populateMAP.put(sq.next(), postmortemItemDtlVO.getStatus());
	    	populateMAP.put(sq.next(), userVO.getSeatId());
	    	populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
	    	populateMAP.put(sq.next(), postmortemItemDtlVO.getPostmortemId());
	    	populateMAP.put(sq.next(), userVO.getHospitalCode());
	    	      	
	    }
	    catch(Exception e)
	    {
	    	throw new HisApplicationExecutionException("PostmortemItemDtlDAO.populateMAP::"+e);
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

	public PostmortemItemDtlVO[] getPreservedItem(String postmortemId,UserVO userVO)
	{
		PostmortemItemDtlVO[] arrItemDtlVO = null;
		ValueObject vo[] = null;
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MortuaryConfig.QUERY_FILE_FOR_MORTUARY_DAO;
		String queryKey = "GET_PRESERVED_ITEM_DETAIL.HMRT_POSTMORTEM_ITEM_DTL";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		populateMAP.put(sq.next(), postmortemId);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), userVO.getHospitalCode());

		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				arrItemDtlVO = null;
			}
			else
			{
				rs.beforeFirst();
	
				vo = HelperMethods.populateVOfrmRS(PostmortemItemDtlVO.class, rs);
				arrItemDtlVO = new PostmortemItemDtlVO[vo.length];
				for (int i = 0; i < vo.length; i++)
				{
					arrItemDtlVO[i] = (PostmortemItemDtlVO) vo[i];
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
		return arrItemDtlVO;
	}
	
	public void updateStatus(String postmortemId,String itemCode,String status,UserVO userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MortuaryConfig.QUERY_FILE_FOR_MORTUARY_DAO;
		String queryKey = "UPDATE_ITEM_STATUS.HMRT_POSTMORTEM_ITEM_DTL";
		
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		
		populateMAP.put(sq.next(), status);
		populateMAP.put(sq.next(), postmortemId);
		populateMAP.put(sq.next(), itemCode);
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
	
	public PostmortemItemDtlVO[] getPreservedItemInMortuary(String postmortemId,UserVO userVO)
	{
		PostmortemItemDtlVO[] arrItemDtlVO = null;
		ValueObject vo[] = null;
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MortuaryConfig.QUERY_FILE_FOR_MORTUARY_DAO;
		String queryKey = "GET_PRESERVED_ITEM_IN_MORTUARY_DETAIL.HMRT_POSTMORTEM_ITEM_DTL";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		populateMAP.put(sq.next(), postmortemId);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), userVO.getHospitalCode());
		populateMAP.put(sq.next(), MortuaryConfig.DECEASED_ITEM_STATUS_MORTUARY);

		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				//arrItemDtlVO = null;
				throw new HisRecordNotFoundException("No Sample Found To Send");
			}
			else
			{
				rs.beforeFirst();
	
				vo = HelperMethods.populateVOfrmRS(PostmortemItemDtlVO.class, rs);
				arrItemDtlVO = new PostmortemItemDtlVO[vo.length];
				for (int i = 0; i < vo.length; i++)
				{
					arrItemDtlVO[i] = (PostmortemItemDtlVO) vo[i];
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
		return arrItemDtlVO;
	}
}
