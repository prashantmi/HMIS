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
import hisglobal.vo.MortuaryPoliceVerificationVO;
import hisglobal.vo.UserVO;
import hisglobal.vo.ValueObject;

public class DeceasedRelativeDtlDAO extends DataAccessObject implements DeceasedRelativeDtlDAOi
{
	public DeceasedRelativeDtlDAO(TransactionContext _tx)
	{
		super(_tx);
	}
	
	//Inserting Record
	public void create(DeceasedRelativeDtlVO deceasedRelativeVO, UserVO userVO)
	{
		String query  = "";
	    Map populateMAP =new HashMap();
	    Sequence sq=new Sequence();
	    String filename= MortuaryConfig.QUERY_FILE_FOR_MORTUARY_DAO;
	    String queryKey="INSERT.HMRT_DECEASED_RELATIVE_DTL";
	    
	   
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
	    	populateMAP.put(sq.next(), deceasedRelativeVO.getDeceasedNo());
	    	populateMAP.put(sq.next(), deceasedRelativeVO.getRelativeName());
	    	populateMAP.put(sq.next(), deceasedRelativeVO.getRelativeAddress());
	    	populateMAP.put(sq.next(), deceasedRelativeVO.getRelativeContactNo());
	    	populateMAP.put(sq.next(), deceasedRelativeVO.getRelativeCode());
	    	populateMAP.put(sq.next(), userVO.getSeatId());
	    	populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
	    	populateMAP.put(sq.next(), deceasedRelativeVO.getDeceasedNo());
	    	populateMAP.put(sq.next(), userVO.getHospitalCode());
	    	populateMAP.put(sq.next(), deceasedRelativeVO.getRelativeFlag());
	    	      	
	    }
	    catch(Exception e)
	    {
	    	throw new HisApplicationExecutionException("DeceasedRelativeDtlDAO.populateMAP::"+e);
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
	
	public DeceasedRelativeDtlVO[] getDeceasedExistingRelative(String deceasedNo,UserVO userVO)
	{
		DeceasedRelativeDtlVO[] arrRelativeVO = null;
		ValueObject vo[] = null;
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MortuaryConfig.QUERY_FILE_FOR_MORTUARY_ESSENTIALDAO;
		String queryKey = "GET_RELATIVE_DETAIL.HMRT_DECEASED_RELATIVE_DTL";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		populateMAP.put(sq.next(), deceasedNo);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), userVO.getHospitalCode());

		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				arrRelativeVO = null;
			}
			else
			{
				rs.beforeFirst();
	
				vo = HelperMethods.populateVOfrmRS(DeceasedRelativeDtlVO.class, rs);
				arrRelativeVO = new DeceasedRelativeDtlVO[vo.length];
				for (int i = 0; i < vo.length; i++)
				{
					arrRelativeVO[i] = (DeceasedRelativeDtlVO) vo[i];
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
		return arrRelativeVO;
	}
	
	public DeceasedRelativeDtlVO getPostmortemRequestedByRelative(String deceasedNo,UserVO userVO)
	{
		DeceasedRelativeDtlVO pmReqRelativeVO=new DeceasedRelativeDtlVO();
		String query="";
		Map populateMap= new HashMap();
		String filename= MortuaryConfig.QUERY_FILE_FOR_MORTUARY_DAO;
		String queryKey="SELECT_PM_REQUEST_RELATIVE.HMRT_DECEASED_RELATIVE_DTL";
		
		Sequence sq= new Sequence();
		
		Connection conn=super.getTransactionContext().getConnection();
		
		
		populateMap.put(sq.next(),deceasedNo);
		populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
		populateMap.put(sq.next(),userVO.getHospitalCode());
		populateMap.put(sq.next(),MortuaryConfig.DECEASED_RELATIVE_FLAG_POSTMORTEM_REQUEST_BY_POLICE);
		
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
				pmReqRelativeVO=null;
				//throw new HisRecordNotFoundException();
			}
			else
			{
				rs.beforeFirst();
				while(rs.next())
				{
					HelperMethods.populateVOfrmRS(pmReqRelativeVO,rs);
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
		return pmReqRelativeVO;
	}
}