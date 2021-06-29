package opd.master.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import opd.OpdConfig;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.TransactionContext;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.Sequence;
import hisglobal.vo.DrugListMasterVO;
import hisglobal.vo.UserVO;

public class DrugListMasterDAO extends DataAccessObject implements DrugListMasterDAOi
{
	Logger log;

	public DrugListMasterDAO(TransactionContext _tx)
	{
		super(_tx);
		log = LogManager.getLogger(this.getClass());
	}
	
public void creat(DrugListMasterVO drugListMasterVO ,UserVO userVO) {
		
		//ResultSet rs;
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String queryKey = "INSERT.HGBT_DRUG_LIST_MST";
		
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
			populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
			populateMAP.put(sq.next(), drugListMasterVO.getDrugListName());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), drugListMasterVO.getRemark());
			populateMAP.put(sq.next(), drugListMasterVO.getSlNo());
			populateMAP.put(sq.next(), userVO.getSeatId());
			populateMAP.put(sq.next(), drugListMasterVO.getDiseaseCode());
			populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
			populateMAP.put(sq.next(), drugListMasterVO.getLastModifyDate());
			populateMAP.put(sq.next(), drugListMasterVO.getLastModofySeatId());
			
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
public String checkDuplicateName(DrugListMasterVO drugListMasterVO,UserVO userVO)
{
	String query  = "";
	ResultSet rs = null;
  Map populateMAP =new HashMap();
  Sequence sq=new Sequence();
  String filename = OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
  String queryKey="CHECK_DUPLICATE_NAME.HGBT_DRUG_LIST_MST";
	
	
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
      	populateMAP.put(sq.next(),drugListMasterVO.getDrugListName());
  		populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
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

public DrugListMasterVO getDataForModify(DrugListMasterVO drugListMasterVO, UserVO _UserVO)
{
	String query = "";
	ResultSet rs;
	Map populateMAP = new HashMap();
	Sequence sq = new Sequence();
	DrugListMasterVO vo=new DrugListMasterVO();

	String filename = OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
	String queryKey = "SELECT.HGBT_DRUG_LIST_MST";
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
		populateMAP.put(sq.next(), drugListMasterVO.getDrugListId());
		populateMAP.put(sq.next(), drugListMasterVO.getSlNo());
		populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
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

public String checkDuplicateNameForModify(DrugListMasterVO drugListMasterVO,UserVO userVO)
{
	String query  = "";
	ResultSet rs = null;
    Map populateMAP =new HashMap();
    Sequence sq=new Sequence();
    String filename = OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
    String queryKey="CHECK_DUPLICATE_NAMEFORMODIFY.HGBT_DRUG_LIST_MST";
	
	
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
    	populateMAP.put(sq.next(),drugListMasterVO.getDrugListName());
    	populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
       	populateMAP.put(sq.next(), Config.IS_VALID_DELETED);
       	populateMAP.put(sq.next(),drugListMasterVO.getDrugListId());
        	
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

public void updateDrugListMaster(DrugListMasterVO drugListMasterVO,UserVO _UserVO)
{
	String query = "";
	Map populateMAP = new HashMap();
	Sequence sq = new Sequence();
	String filename = OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
	String queryKey = "UPDATE.HGBT_DRUG_LIST_MST";

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
		populateMAP.put(sq.next(), drugListMasterVO.getDrugListId());
		populateMAP.put(sq.next(), drugListMasterVO.getSlNo());
		populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
		
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

public void saveModDrugListMaster(DrugListMasterVO drugListMasterVO ,UserVO userVO) {
	
	//ResultSet rs;
	String query = "";
	Map populateMAP = new HashMap();
	Sequence sq = new Sequence();
	String filename = OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
	String queryKey = "INSERT_MODIFY.HGBT_DRUG_LIST_MST";
	
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
		populateMAP.put(sq.next(), drugListMasterVO.getDrugListId());
		populateMAP.put(sq.next(), drugListMasterVO.getDrugListName());
		populateMAP.put(sq.next(), drugListMasterVO.getIsValid());
		populateMAP.put(sq.next(), drugListMasterVO.getRemark());
		populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
		populateMAP.put(sq.next(), drugListMasterVO.getDrugListId());
		populateMAP.put(sq.next(), userVO.getSeatId());
		populateMAP.put(sq.next(), drugListMasterVO.getDiseaseCode());
		populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
		populateMAP.put(sq.next(), drugListMasterVO.getLastModifyDate());
		populateMAP.put(sq.next(), drugListMasterVO.getLastModofySeatId());
			
				
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

public List getDrugListNameList(UserVO _userVO)
{
	String query = "";
	ResultSet rs;
	Map populateMAP = new HashMap();
	Sequence sq = new Sequence(); 
	List drugListNameList=new ArrayList();
			
	String filename = OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
	String queryKey = "SELECT.DRUGLISTNAME_LIST.HGBT_DRUG_LIST_MST";

	try
	{
		query = HelperMethodsDAO.getQuery(filename, queryKey);
	}
	catch (Exception e)
	{
		throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
	}
	log.info(query);

	try
	{									
		populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE); 		
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE); 		
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
	}
	catch (Exception e)
	{
		throw new HisApplicationExecutionException("EssentialDAO.populateMAP::" + e);
	}
	try
	{
		rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
		
		if(!rs.next()){
			 throw new HisRecordNotFoundException("Record Not Found");
		               }
	}
	catch (Exception e)
	{
		if (e.getClass() == HisRecordNotFoundException.class) throw new HisRecordNotFoundException(e.getMessage());
		else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
	}
	
	try
	{
		drugListNameList = HelperMethodsDAO.getAlOfEntryObjects(rs);
	}
	catch (Exception e)
	{
		throw new HisDataAccessException("EssentialDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
	}
 return drugListNameList;
	
}
}
