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
import hisglobal.vo.MrdMasterVO;
import hisglobal.vo.UserVO;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import mrd.MrdConfig;

public class MrdMasterDAO extends DataAccessObject implements MrdMasterDAOi
{
	public MrdMasterDAO(TransactionContext _tx)
	{
		super(_tx);
	}
	
public void creat(MrdMasterVO mrdMasterVO ,UserVO userVO) {
		
		//ResultSet rs;
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_MASTER_DAO;
		String queryKey = "INSERT.HPMRT_MRD_MST";
		
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
			populateMAP.put(sq.next(), userVO.getHospitalCode()); //for mrdCode
			
			populateMAP.put(sq.next(), mrdMasterVO.getSlNo());//for sereial No
			
			
			populateMAP.put(sq.next(), mrdMasterVO.getMrdName());
			populateMAP.put(sq.next(), mrdMasterVO.getShortName());
			populateMAP.put(sq.next(), userVO.getHospitalCode());
			populateMAP.put(sq.next(), mrdMasterVO.getDeptCode());
			populateMAP.put(sq.next(), mrdMasterVO.getEmpNo());
			populateMAP.put(sq.next(), mrdMasterVO.getLocationDesc());
			populateMAP.put(sq.next(), mrdMasterVO.getBuildingCode());
			populateMAP.put(sq.next(), mrdMasterVO.getNumBlockId());
			populateMAP.put(sq.next(), mrdMasterVO.getNumFloorId());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), mrdMasterVO.getNumRoomId());
			populateMAP.put(sq.next(), userVO.getSeatId());
			//entryDate
			populateMAP.put(sq.next(), mrdMasterVO.getLastModifyDate());
			populateMAP.put(sq.next(), mrdMasterVO.getLastModifySeatId());
			populateMAP.put(sq.next(), mrdMasterVO.getMrdType());
					
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

public String checkMainMrdExistence(UserVO _UserVO)
{
	String query = "";
	ResultSet rs;

	Map populateMAP = new HashMap();
	Sequence sq = new Sequence();

	String filename = MrdConfig.QUERY_FILE_FOR_MRD_MASTER_DAO;
	String queryKey ="CHECKMAINMRD.HPMRT_MRD_MST";

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
		populateMAP.put(sq.next(), MrdConfig.MRD_TYPE_MAIN_MRD);
		populateMAP.put(sq.next(), _UserVO.getHospitalCode());
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		
	}
	catch (Exception e)
	{
		throw new HisApplicationExecutionException("BldDonCompMstDAO.populateMAP::" + e);
	}
	String record=null;
	try
	{
		rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
		while (rs.next())
		{
			record=rs.getString(1);
			
		}
	}
	catch (Exception e)
	{
		if (e.getClass() == HisRecordNotFoundException.class) throw new HisRecordNotFoundException(e.getMessage());
		else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
	}
	return record;
}

public MrdMasterVO getDataForModify(MrdMasterVO mrdMasterVO, UserVO _UserVO)
{
	String query = "";
	ResultSet rs;
	Map populateMAP = new HashMap();
	Sequence sq = new Sequence();
	MrdMasterVO vo=new MrdMasterVO();

	String filename = MrdConfig.QUERY_FILE_FOR_MRD_MASTER_DAO;
	String queryKey = "SELECT.HPMRT_MRD_MST";
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
		populateMAP.put(sq.next(), mrdMasterVO.getMrdCode());
		populateMAP.put(sq.next(), mrdMasterVO.getSlNo());
		populateMAP.put(sq.next(), _UserVO.getHospitalCode());
		
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

public void saveModMrdMaster(MrdMasterVO mrdMasterVO ,UserVO userVO) {
	
	//ResultSet rs;
	String query = "";
	Map populateMAP = new HashMap();
	Sequence sq = new Sequence();
	String filename = MrdConfig.QUERY_FILE_FOR_MRD_MASTER_DAO;
	String queryKey = "INSERT_MODIFY.HPMRT_MRD_MST";
	
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
		populateMAP.put(sq.next(), mrdMasterVO.getMrdCode());
		
		populateMAP.put(sq.next(), userVO.getHospitalCode()); //for sereial No
		populateMAP.put(sq.next(), mrdMasterVO.getMrdCode());
				
		
		populateMAP.put(sq.next(), mrdMasterVO.getMrdName());
		populateMAP.put(sq.next(), mrdMasterVO.getShortName());
		populateMAP.put(sq.next(), userVO.getHospitalCode());
		populateMAP.put(sq.next(), mrdMasterVO.getDeptCode());
		populateMAP.put(sq.next(), mrdMasterVO.getEmpNo());
		populateMAP.put(sq.next(), mrdMasterVO.getLocationDesc());
		populateMAP.put(sq.next(), mrdMasterVO.getBuildingCode());
		populateMAP.put(sq.next(), mrdMasterVO.getNumBlockId());
		populateMAP.put(sq.next(), mrdMasterVO.getNumFloorId());
		//populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), mrdMasterVO.getIsActive());
		populateMAP.put(sq.next(), mrdMasterVO.getNumRoomId());
		populateMAP.put(sq.next(), userVO.getSeatId());
		//entryDate
		populateMAP.put(sq.next(), mrdMasterVO.getLastModifyDate());
		populateMAP.put(sq.next(), mrdMasterVO.getLastModifySeatId());
		populateMAP.put(sq.next(), mrdMasterVO.getMrdType());
				
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

public void updateMrdMaster(MrdMasterVO mrdMasterVO,UserVO _UserVO)
{
	String query = "";
	Map populateMAP = new HashMap();
	Sequence sq = new Sequence();
	String filename = MrdConfig.QUERY_FILE_FOR_MRD_MASTER_DAO;
	String queryKey = "UPDATE.HPMRT_MRD_MST";

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
		populateMAP.put(sq.next(), mrdMasterVO.getMrdCode());
		populateMAP.put(sq.next(), mrdMasterVO.getSlNo());
		populateMAP.put(sq.next(), _UserVO.getHospitalCode());
		
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

public String checkDuplicateNameForModify(MrdMasterVO mrdMasterVO,UserVO userVO)
{
	String query  = "";
	ResultSet rs = null;
    Map populateMAP =new HashMap();
    Sequence sq=new Sequence();
    String filename = MrdConfig.QUERY_FILE_FOR_MRD_MASTER_DAO;
    String queryKey="CHECK_DUPLICATE_NAMEFORMODIFY.HPMRT_MRD_MST";
	
	
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
        populateMAP.put(sq.next(),mrdMasterVO.getMrdName());
    	populateMAP.put(sq.next(), Config.IS_VALID_DELETED);
       	populateMAP.put(sq.next(),mrdMasterVO.getMrdCode());
       
       	populateMAP.put(sq.next(), userVO.getHospitalCode());
        	
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

//Checking For Duplicate Short Name
public String checkDuplicateShortNameForModify(MrdMasterVO mrdMasterVO,UserVO userVO)
{
	String query  = "";
	ResultSet rs = null;
    Map populateMAP =new HashMap();
    Sequence sq=new Sequence();
    String filename = MrdConfig.QUERY_FILE_FOR_MRD_MASTER_DAO;
    String queryKey="CHECK_DUPLICATE_SHORT_NAMEFORMODIFY.HPMRT_MRD_MST";
	
	
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
      		populateMAP.put(sq.next(),mrdMasterVO.getShortName());
      	 	populateMAP.put(sq.next(), Config.IS_VALID_DELETED);
	       	populateMAP.put(sq.next(),mrdMasterVO.getMrdCode());
	       
	       	populateMAP.put(sq.next(), userVO.getHospitalCode());
        	
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

//Checking For Duplicate Name
public String checkDuplicateName(MrdMasterVO mrdMasterVO,UserVO userVO)
{
	String query  = "";
	ResultSet rs = null;
    Map populateMAP =new HashMap();
    Sequence sq=new Sequence();
    String filename = MrdConfig.QUERY_FILE_FOR_MRD_MASTER_DAO;
    String queryKey="CHECK_DUPLICATE_NAME.HPMRT_MRD_MST";
	
	
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
        populateMAP.put(sq.next(),mrdMasterVO.getMrdName());
    	populateMAP.put(sq.next(), userVO.getHospitalCode());
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

//Checking For Duplicate Short Name
public String checkDuplicateShortName(MrdMasterVO mrdMasterVO,UserVO userVO)
{
	String query  = "";
	ResultSet rs = null;
    Map populateMAP =new HashMap();
    Sequence sq=new Sequence();
    String filename = MrdConfig.QUERY_FILE_FOR_MRD_MASTER_DAO;
    String queryKey="CHECK_DUPLICATE_SHORT_NAME.HPMRT_MRD_MST";
	
	
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
      	populateMAP.put(sq.next(),mrdMasterVO.getShortName());
       	populateMAP.put(sq.next(), userVO.getHospitalCode());
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

}
