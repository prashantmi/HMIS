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
import hisglobal.vo.EnclosureMasterVO;
import hisglobal.vo.UserVO;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import mrd.MrdConfig;

public class EnclosureMasterDAO extends DataAccessObject implements EnclosureMasterDAOi
{
	public EnclosureMasterDAO(TransactionContext _transactionContext)
	{
		super(_transactionContext);
	}
	
	
public void creat(EnclosureMasterVO enclosureMstVO ,UserVO userVO) {
		
		//ResultSet rs;
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_MASTER_DAO;
		String queryKey = "INSERT.HPMRT_ENCLOSURE_MST";
		
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
			
			populateMAP.put(sq.next(), enclosureMstVO.getSlNo());//for sereial No
			populateMAP.put(sq.next(), enclosureMstVO.getEnclosure());
			//populateMAP.put(sq.next(), enclosureMstVO.getMaxPages());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), userVO.getSeatId());
			populateMAP.put(sq.next(), enclosureMstVO.getLastModifyDate());
			populateMAP.put(sq.next(), enclosureMstVO.getLastModifySeatId());
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
public String checkDuplicateName(EnclosureMasterVO enclosureMstVO ,UserVO userVO)
{
	String query  = "";
	ResultSet rs = null;
  Map populateMAP =new HashMap();
  Sequence sq=new Sequence();
  String filename = MrdConfig.QUERY_FILE_FOR_MRD_MASTER_DAO;
  String queryKey="CHECK_DUPLICATE_NAME.HPMRT_ENCLOSURE_MST";
	
	
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
      	populateMAP.put(sq.next(),enclosureMstVO.getEnclosure());
  		//populateMAP.put(sq.next(), userVO.getHospitalCode());
      	populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE); // Added by Pawan Kumar B N on 03-Jul-2012
     	populateMAP.put(sq.next(), Config.IS_VALID_DELETED);
     	
      	
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

public EnclosureMasterVO getDataForModify(EnclosureMasterVO enclosureMstVO, UserVO _UserVO)
{
	String query = "";
	ResultSet rs;
	Map populateMAP = new HashMap();
	Sequence sq = new Sequence();
	EnclosureMasterVO vo=new EnclosureMasterVO();

	String filename = MrdConfig.QUERY_FILE_FOR_MRD_MASTER_DAO;
	String queryKey = "SELECT.HPMRT_ENCLOSURE_MST";
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
		populateMAP.put(sq.next(), enclosureMstVO.getEnclosureId());
		populateMAP.put(sq.next(), enclosureMstVO.getSlNo());
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

public String checkDuplicateNameForModify(EnclosureMasterVO enclosureMstVO,UserVO userVO)
{
	String query  = "";
	ResultSet rs = null;
    Map populateMAP =new HashMap();
    Sequence sq=new Sequence();
    String filename = MrdConfig.QUERY_FILE_FOR_MRD_MASTER_DAO;
    String queryKey="CHECK_DUPLICATE_NAMEFORMODIFY.HPMRT_ENCLOSURE_MST";
	
	
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
    	populateMAP.put(sq.next(),enclosureMstVO.getEnclosure());
    	//populateMAP.put(sq.next(), userVO.getHospitalCode());
    	populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE); // Added by Pawan Kumar B N on 03-Jul-2012
       	populateMAP.put(sq.next(), Config.IS_VALID_DELETED);
       	populateMAP.put(sq.next(),enclosureMstVO.getEnclosureId());
        	
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

public void updateEnclosureMaster(EnclosureMasterVO enclosureMstVO,UserVO _UserVO)
{
	String query = "";
	Map populateMAP = new HashMap();
	Sequence sq = new Sequence();
	String filename = MrdConfig.QUERY_FILE_FOR_MRD_MASTER_DAO;
	String queryKey = "UPDATE.HPMRT_ENCLOSURE_MST";

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
		populateMAP.put(sq.next(), enclosureMstVO.getEnclosureId());
		populateMAP.put(sq.next(), enclosureMstVO.getSlNo());
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

public void saveModEnclosureMaster(EnclosureMasterVO enclosureMstVO ,UserVO userVO) {
	
	//ResultSet rs;
	String query = "";
	Map populateMAP = new HashMap();
	Sequence sq = new Sequence();
	String filename = MrdConfig.QUERY_FILE_FOR_MRD_MASTER_DAO;
	String queryKey = "INSERT_MODIFY.HPMRT_ENCLOSURE_MST";
	
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
		populateMAP.put(sq.next(), enclosureMstVO.getEnclosureId());
		
		//populateMAP.put(sq.next(), userVO.getHospitalCode()); //for sereial No
		populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE); // Added by Pawan Kumar B N on 03-Jul-2012
		populateMAP.put(sq.next(), enclosureMstVO.getEnclosureId());
				
		populateMAP.put(sq.next(), enclosureMstVO.getEnclosure());
		populateMAP.put(sq.next(), enclosureMstVO.getMaxPages());
		populateMAP.put(sq.next(), enclosureMstVO.getIsValid());
		populateMAP.put(sq.next(), userVO.getSeatId());
		populateMAP.put(sq.next(), enclosureMstVO.getLastModifyDate());
		populateMAP.put(sq.next(), enclosureMstVO.getLastModifySeatId());
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
