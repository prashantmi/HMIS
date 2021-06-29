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
import hisglobal.vo.DeceasedDetailVO;
import hisglobal.vo.PostmortemTeamDetailVO;
import hisglobal.vo.UserVO;
import hisglobal.vo.ValueObject;

public class PostmortemTeamDetailDAO extends DataAccessObject implements PostmortemTeamDetailDAOi
{
	public PostmortemTeamDetailDAO(TransactionContext _tx)
	{
		super(_tx);
	}
	
	//Inserting Record
	public void create(PostmortemTeamDetailVO postmortemTeamVO, UserVO userVO)
	{
		String query  = "";
	    Map populateMAP =new HashMap();
	    Sequence sq=new Sequence();
	    String filename= MortuaryConfig.QUERY_FILE_FOR_MORTUARY_DAO;
	    String queryKey="INSERT.HMRT_POSTMORTEM_TEAM_DTL";
	    
	   
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
	    	populateMAP.put(sq.next(), postmortemTeamVO.getPostmortemId());
	    	populateMAP.put(sq.next(), postmortemTeamVO.getEmpId());
	    	populateMAP.put(sq.next(), postmortemTeamVO.getRoleId());
	    	populateMAP.put(sq.next(), userVO.getSeatId());
	    	populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
	    	populateMAP.put(sq.next(), postmortemTeamVO.getPostmortemId());
	    	populateMAP.put(sq.next(), userVO.getHospitalCode());
	    	populateMAP.put(sq.next(), postmortemTeamVO.getIsIncharge());
	    	populateMAP.put(sq.next(), postmortemTeamVO.getReason());
	    	populateMAP.put(sq.next(), postmortemTeamVO.getIsPerformed());
	    	      	
	    }
	    catch(Exception e)
	    {
	    	throw new HisApplicationExecutionException("PostmortemTeamDetailDAO.populateMAP::"+e);
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
	
	public PostmortemTeamDetailVO[] getAddedTeamMember(String postmortmId,UserVO userVO)
	{
		PostmortemTeamDetailVO[] arrTeamVO = null;
		ValueObject vo[] = null;
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MortuaryConfig.QUERY_FILE_FOR_MORTUARY_ESSENTIALDAO;
		String queryKey = "GET_ADD_TEAM.HMRT_POSTMORTEM_TEAM_DTL";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), postmortmId);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), userVO.getHospitalCode());

		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				arrTeamVO=null;
			}
			else
			{
				rs.beforeFirst();
	
				vo = HelperMethods.populateVOfrmRS(PostmortemTeamDetailVO.class, rs);
				arrTeamVO = new PostmortemTeamDetailVO[vo.length];
				for (int i = 0; i < vo.length; i++)
				{
					arrTeamVO[i] = (PostmortemTeamDetailVO) vo[i];
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

		return arrTeamVO;
	}
	
	public void update(PostmortemTeamDetailVO postmortemTeamVO, UserVO userVO)
	{
		String query  = "";
	    Map populateMAP =new HashMap();
	    Sequence sq=new Sequence();
	    String filename= MortuaryConfig.QUERY_FILE_FOR_MORTUARY_DAO;
	    String queryKey="UDDATE.HMRT_POSTMORTEM_TEAM_DTL";
	    
	    try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		
		populateMAP.put(sq.next(), postmortemTeamVO.getReason());
		populateMAP.put(sq.next(), postmortemTeamVO.getIsPerformed());
		populateMAP.put(sq.next(), postmortemTeamVO.getPostmortemId());
		populateMAP.put(sq.next(), postmortemTeamVO.getEmpId());
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), postmortemTeamVO.getSrNO());
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
}
