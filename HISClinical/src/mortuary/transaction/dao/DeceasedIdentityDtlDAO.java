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
import hisglobal.vo.DeceasedIdentityDtlVO;
import hisglobal.vo.UserVO;
import hisglobal.vo.ValueObject;

public class DeceasedIdentityDtlDAO extends DataAccessObject implements DeceasedIdentityDtlDAOi
{
	public DeceasedIdentityDtlDAO(TransactionContext _tx)
	{
		super(_tx);
	}
	
	public void create(DeceasedIdentityDtlVO identityDtlVO, UserVO userVO)
	{
		String query  = "";
	    Map populateMAP =new HashMap();
	    Sequence sq=new Sequence();
	    String filename= MortuaryConfig.QUERY_FILE_FOR_MORTUARY_DAO;
	    String queryKey="INSERT.HMRT_DECEASED_IDENTY_DTL";
	    
	   
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
	    	populateMAP.put(sq.next(), identityDtlVO.getDeceasedNo());
	    	populateMAP.put(sq.next(), identityDtlVO.getDeceasedNo());
	    	populateMAP.put(sq.next(), identityDtlVO.getIsIdentifiyBy());
	    	populateMAP.put(sq.next(), identityDtlVO.getEmpId());
	    	populateMAP.put(sq.next(), identityDtlVO.getIdentifiyByName());
	    	populateMAP.put(sq.next(), identityDtlVO.getIdentifiyByAddress());
	    	populateMAP.put(sq.next(), identityDtlVO.getIdentifiyByPhone());
	    	populateMAP.put(sq.next(), identityDtlVO.getOfficerDesignation());
	    	populateMAP.put(sq.next(), identityDtlVO.getRelativeCode());
	    	populateMAP.put(sq.next(), userVO.getIpAddress());
	    	populateMAP.put(sq.next(), identityDtlVO.getOfficerBadgeNo());
	    	populateMAP.put(sq.next(), identityDtlVO.getPostmortemId());
	    	populateMAP.put(sq.next(), userVO.getSeatId());
	    	populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
	    	populateMAP.put(sq.next(), userVO.getHospitalCode());
	    	populateMAP.put(sq.next(), identityDtlVO.getIdentityMode());
	    	populateMAP.put(sq.next(), identityDtlVO.getIdentityRemarks());
	    	
	    	      	
	    }
	    catch(Exception e)
	    {
	    	throw new HisApplicationExecutionException("DeceasedIdentityDtlDAO.populateMAP::"+e);
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
	
	public boolean checkBodyIdentified(String deceasedNo,UserVO userVO)
	{
		boolean exist=false;
		String query  = "";
	    Map populateMAP =new HashMap();
	    Sequence sq=new Sequence();
	    String filename= MortuaryConfig.QUERY_FILE_FOR_MORTUARY_DAO;
	    String queryKey="CHECK_EXISTING_RECORD.HMRT_DECEASED_IDENTY_DTL";
	    Connection conn=super.getTransactionContext().getConnection();
	    
	    populateMAP.put(sq.next(), deceasedNo);
	    populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
	    populateMAP.put(sq.next(), userVO.getHospitalCode());
	    
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
	
	public DeceasedIdentityDtlVO[] getIdentifiedByDetailByDeceasedNo(String deceasedNo,UserVO userVO)
	{
		DeceasedIdentityDtlVO[] arrIdentityVO = null;
		ValueObject vo[] = null;
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MortuaryConfig.QUERY_FILE_FOR_MORTUARY_ESSENTIALDAO;
		String queryKey = "GET_IDENTIFY_BY_DETAIL.HMRT_DECEASED_IDENTY_DTL";

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
				arrIdentityVO = null;
			}
			else
			{
				rs.beforeFirst();
	
				vo = HelperMethods.populateVOfrmRS(DeceasedIdentityDtlVO.class, rs);
				arrIdentityVO = new DeceasedIdentityDtlVO[vo.length];
				for (int i = 0; i < vo.length; i++)
				{
					arrIdentityVO[i] = (DeceasedIdentityDtlVO) vo[i];
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

		return arrIdentityVO;
	}
	
}
