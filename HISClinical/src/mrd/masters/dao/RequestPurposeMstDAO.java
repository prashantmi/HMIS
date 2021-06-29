package mrd.masters.dao;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.TransactionContext;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.Sequence;
import hisglobal.vo.RequestPurposeMstVO;
import hisglobal.vo.UserVO;
import hisglobal.vo.ValueObject;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import mrd.MrdConfig;

public class RequestPurposeMstDAO extends DataAccessObject implements RequestPurposeMstDAOi
{
	public RequestPurposeMstDAO(TransactionContext _transactionContext)
	{
		super(_transactionContext);
	}
	
public void creat(RequestPurposeMstVO reqPurposeMstVO ,UserVO userVO) {
		
		//ResultSet rs;
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_MASTER_DAO;
		String queryKey = "INSERT.HPMRT_REQ_PURPOSE_MST";
		
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
			//populateMAP.put(sq.next(), userVO.getHospitalCode()); //for reqPurposeId
			populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE); // Added by Pawan Kumar B N on 03-Jul-2012
			
			populateMAP.put(sq.next(), reqPurposeMstVO.getSlNo());//for sereial No
			populateMAP.put(sq.next(), reqPurposeMstVO.getRecordType());
			populateMAP.put(sq.next(), reqPurposeMstVO.getPurpose());
			populateMAP.put(sq.next(), reqPurposeMstVO.getPriority());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), userVO.getSeatId());
			populateMAP.put(sq.next(), reqPurposeMstVO.getLastModifyDate());
			populateMAP.put(sq.next(), reqPurposeMstVO.getLastModifySeatId());
			//populateMAP.put(sq.next(), userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE); // Added by Pawan Kumar B N on 03-Jul-2012
			//entry date
			
			
					
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("ReqPurposeMstDAO.populateMAP::" + e);
		}
		try
		{
			HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), query, populateMAP);
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		
		
	}

//Checking For Duplicate Name
public String checkDuplicateName(RequestPurposeMstVO reqPurposeMstVO ,UserVO userVO)
{
	String query  = "";
	ResultSet rs = null;
    Map populateMAP =new HashMap();
    Sequence sq=new Sequence();
    String filename = MrdConfig.QUERY_FILE_FOR_MRD_MASTER_DAO;
    String queryKey="CHECK_DUPLICATE_NAME.HPMRT_REQ_PURPOSE_MST";
	
	
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
        populateMAP.put(sq.next(),reqPurposeMstVO.getPurpose());
    	//populateMAP.put(sq.next(), userVO.getHospitalCode());
        populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE); // Added by Pawan Kumar B N on 03-Jul-2012
       	populateMAP.put(sq.next(), Config.IS_VALID_DELETED);
       	populateMAP.put(sq.next(),reqPurposeMstVO.getRecordType());
        	
    }
    catch(Exception e)
    {
    	throw new HisApplicationExecutionException("MortuaryMasterDAO.populateMAP::"+e);
    }
    try
	{
    	rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
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

	
	public RequestPurposeMstVO[] getRequestPurpose(String recordType,UserVO userVO)
	{
		RequestPurposeMstVO[] arrReqPurposeVO = null;
		ValueObject vo[] = null;
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_DAO;
		String queryKey = "GET_REQUEST_PURPOSE.HPMRT_REQ_PURPOSE_MST";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		//populateMAP.put(sq.next(), userVO.getHospitalCode());
		populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE); // Added by Pawan Kumar B N on 05-Jul-2012
		populateMAP.put(sq.next(), recordType);

		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				//throw new HisRecordNotFoundException("No Request Purpose Found");
				arrReqPurposeVO = null;
			}
			else
			{
				rs.beforeFirst();
	
				vo = HelperMethods.populateVOfrmRS(RequestPurposeMstVO.class, rs);
				arrReqPurposeVO = new RequestPurposeMstVO[vo.length];
				for (int i = 0; i < vo.length; i++)
				{
					arrReqPurposeVO[i] = (RequestPurposeMstVO) vo[i];
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
		return arrReqPurposeVO;
	}
	
	public RequestPurposeMstVO getDataForModify(RequestPurposeMstVO reqPurposeMstVO, UserVO _UserVO)
	{
		String query = "";
		ResultSet rs;
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		RequestPurposeMstVO vo=new RequestPurposeMstVO();

		String filename = MrdConfig.QUERY_FILE_FOR_MRD_MASTER_DAO;
		String queryKey = "SELECT.HPMRT_REQ_PURPOSE_MST";
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
			populateMAP.put(sq.next(), reqPurposeMstVO.getReqPurposeId());
			populateMAP.put(sq.next(), reqPurposeMstVO.getSlNo());
			//populateMAP.put(sq.next(), _UserVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE); // Added by Pawan Kumar B N on 03-Jul-2012
			//populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("BloodStorageMstDAO.populateMAP::" + e);
		}
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (rs.next())
			{
				HelperMethods.populateVOfrmRS(vo, rs);
			}
			
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class) throw new HisRecordNotFoundException(e.getMessage());
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		return vo;
	}
	
	public String checkDuplicateNameForModify(RequestPurposeMstVO reqPurposeMstVO,UserVO userVO)
	{
		String query  = "";
		ResultSet rs = null;
	    Map populateMAP =new HashMap();
	    Sequence sq=new Sequence();
	    String filename = MrdConfig.QUERY_FILE_FOR_MRD_MASTER_DAO;
	    String queryKey="CHECK_DUPLICATE_NAMEFORMODIFY.HPMRT_REQ_PURPOSE_MST";
		
		
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
	    	populateMAP.put(sq.next(),reqPurposeMstVO.getPurpose());
	    	//populateMAP.put(sq.next(), userVO.getHospitalCode());
	    	populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE); // Added by Pawan Kumar B N on 03-Jul-2012
	       	populateMAP.put(sq.next(), Config.IS_VALID_DELETED);
	       	populateMAP.put(sq.next(),reqPurposeMstVO.getRecordType());
	       	populateMAP.put(sq.next(),reqPurposeMstVO.getReqPurposeId());
	        	
	    }
	    catch(Exception e)
	    {
	    	throw new HisApplicationExecutionException("MortuaryMasterDAO.populateMAP::"+e);
	    }
	    try
		{
	    	rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
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
	
	public void updateReqPurposeMaster(RequestPurposeMstVO reqPurposeMstVO,UserVO _UserVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_MASTER_DAO;
		String queryKey = "UPDATE.HPMRT_REQ_PURPOSE_MST";

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
			populateMAP.put(sq.next(), _UserVO.getSeatId());
			populateMAP.put(sq.next(), Config.IS_VALID_DELETED);
			populateMAP.put(sq.next(), reqPurposeMstVO.getReqPurposeId());
			populateMAP.put(sq.next(), reqPurposeMstVO.getSlNo());
			//populateMAP.put(sq.next(), _UserVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE); // Added by Pawan Kumar B N on 03-Jul-2012
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("BldDonCompMstDAO.populateMAP::" + e);
		}
		try
		{
			HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), query, populateMAP);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
	}
	
	public void saveModReqPurposeMaster(RequestPurposeMstVO reqPurposeMstVO ,UserVO userVO) {
		
		//ResultSet rs;
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_MASTER_DAO;
		String queryKey = "INSERT_MODIFY.HPMRT_REQ_PURPOSE_MST";
		
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
			populateMAP.put(sq.next(), reqPurposeMstVO.getReqPurposeId());
			
			//populateMAP.put(sq.next(), userVO.getHospitalCode()); //for sereial No
			populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE); // Added by Pawan Kumar B N on 03-Jul-2012
			
			populateMAP.put(sq.next(), reqPurposeMstVO.getReqPurposeId());
					
			populateMAP.put(sq.next(), reqPurposeMstVO.getRecordType());
			populateMAP.put(sq.next(), reqPurposeMstVO.getPurpose());
			populateMAP.put(sq.next(), reqPurposeMstVO.getPriority());
			//populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), reqPurposeMstVO.getIsActive());
			populateMAP.put(sq.next(), userVO.getSeatId());
			populateMAP.put(sq.next(), reqPurposeMstVO.getLastModifyDate());
			populateMAP.put(sq.next(), reqPurposeMstVO.getLastModifySeatId());
			//populateMAP.put(sq.next(), userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE); // Added by Pawan Kumar B N on 03-Jul-2012
			//entrydate
			
					
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("RackShelfMstDAO.populateMAP::" + e);
		}
		try
		{
			HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), query, populateMAP);
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		
		
	}
}
