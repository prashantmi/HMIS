package opd.master.dao;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import hisglobal.hisconfig.Config;
import opd.OpdConfig;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import hisglobal.persistence.TransactionContext;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.exceptions.HisUpdateUnsuccesfullException;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.Sequence;
import hisglobal.vo.AllergyTypeVO;
import hisglobal.vo.UserVO;

public class AllergyTypeDAO extends DataAccessObject
{
	Logger log;
	
	public AllergyTypeDAO(TransactionContext _tx) 
	{
		super(_tx);	
		log=LogManager.getLogger(this.getClass());
	}
	
	public List getTheValues(UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();

		String filename = OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String queryKey = "SELECT.ALL_TABLE_NAME";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		

		System.out.println("   -------> query  :: " + query);
		Sequence sq = new Sequence();

		//populateMAP.put(sq.next(), _userVO.getHospitalCode());
		/*populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), _userVO.getHospitalCode());*/

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		List alRecord = new ArrayList();
		try
		{
			if (rs.next()) alRecord = HelperMethodsDAO.getAlOfEntryObjects(rs);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HisDataAccessException  :getAllDepartment" + e);
		}
		return alRecord;
	}
	
	
	
	public List getTableData(UserVO _userVO,String TableId)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();

		String filename = OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String queryKey = "SELECT.COLUMN_NAME_BY_TABLE_NAME";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		log.error(query + "\n");

		System.out.println("   -------> query :: " + query);
		Sequence sq = new Sequence();

		try
		{
				
			populateMAP.put(sq.next(), TableId);
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("OpdEssentialDAO.populateMAP::" + e);
		}

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		List alRecord = new ArrayList();
		try
		{
			if (rs.next()){
				alRecord = HelperMethodsDAO.getAlOfEntryObjects(rs);
			}
				
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HisDataAccessException  :getAllUnitList" + e);
		}
		return alRecord;
	}
	
	public List getPrimaryKey(String tableName)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();

		String filename = OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String queryKey = "SELECT.PRIMARY_KEY_BY_TABLE_NAME";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		log.error(query + "\n");

		System.out.println("   -------> query :: " + query);
		Sequence sq = new Sequence();

		try
		{
				
			populateMAP.put(sq.next(), tableName);
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("OpdEssentialDAO.populateMAP::" + e);
		}

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		List primaryKey = new ArrayList();
		try
		{
			if (rs.next()){
				primaryKey = HelperMethodsDAO.getAlOfEntryObjects(rs);
			}
				
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HisDataAccessException  :getAllUnitList" + e);
		}
		return primaryKey;
	}

//	Inserting Data in dynamic mode
	public void createInDynamicMode(AllergyTypeVO _allergyTypeVO, UserVO _userVO)
	{
		String query  = "";
        Map populateMAP =new HashMap();
        Sequence sq=new Sequence();
        String filename=OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
        String queryKey="INSERT.HGBT_ALLERGY_TYPE_MST_DYNAMIC_MODE";
        
        try
        {
        	query =HelperMethodsDAO.getQuery(filename,queryKey);
        }
        catch(Exception e)
        {
        	throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);
        }
        log.info(query);
        
        try
        {
        	populateMAP.put(sq.next(),Config.SUPER_USER_HOSPITAL_CODE);
        	populateMAP.put(sq.next(),_allergyTypeVO.getAllergiesType());
        	populateMAP.put(sq.next(),_allergyTypeVO.getAllergiesDesc());
        	populateMAP.put(sq.next(),_allergyTypeVO.getSourceFlag());
        	populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
        	populateMAP.put(sq.next(),_allergyTypeVO.getLongQuery());
        	populateMAP.put(sq.next(),_userVO.getSeatId());
        	populateMAP.put(sq.next(),Config.SUPER_USER_HOSPITAL_CODE);
        	populateMAP.put(sq.next(),_allergyTypeVO.getAllergySensitivity());
        
        }
        catch(Exception e){
        	throw new HisApplicationExecutionException(""+e);
        }
        try
        {
        	HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(),query,populateMAP);
        }
        catch(Exception e)
        {
        	e.printStackTrace();
        	throw new HisDataAccessException("HelperMethodsDAO.getResultset"+e);
        }
	}
	
		
	public String getAllergyTypeCode(AllergyTypeVO _allergyTypeVO, UserVO _userVO)
	{
		String query =  "" ;
	   	Map populateMAP =new HashMap();  
	   	ResultSet rs;
	   	Sequence sq=new Sequence();
	   	String filename=OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
	   	String queryKey="SELECT_ALLERGY_TYPE_CODE";
	   	
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
        	
        	populateMAP.put(sq.next(),Config.SUPER_USER_HOSPITAL_CODE);
        }
        catch(Exception e)
        {
        	throw new HisApplicationExecutionException("AllergyTypeDAO.populateMAP::"+e);
        }
        try
        {
        	rs=HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(),query,populateMAP);
        	if(!rs.next())
        		throw new HisRecordNotFoundException("No Allergy Type Code Exists ... ");
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
	
	
	
//	Inserting Data in allergy master
	public void insert(AllergyTypeVO _allergyTypeVO, UserVO _userVO, String allergyTypeCode)
	{
		String query  = "";
        Map populateMAP =new HashMap();
        Sequence sq=new Sequence();
        String filename=OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
        String queryKey="INSERT.HGBT_ALLERGY_MST";
        
        try
        {
        	query =HelperMethodsDAO.getQuery(filename,queryKey);
        }
        catch(Exception e)
        {
        	throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);
        }
        log.info(query);
        
        try
        {
        	populateMAP.put(sq.next(),Config.SUPER_USER_HOSPITAL_CODE);
        	populateMAP.put(sq.next(),allergyTypeCode);
        	populateMAP.put(sq.next(),Config.SUPER_USER_HOSPITAL_CODE);
        	populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
         	populateMAP.put(sq.next(),_userVO.getSeatId());
         
        }
        catch(Exception e){
        	throw new HisApplicationExecutionException(""+e);
        }
        try
        {
        	HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(),query,populateMAP);
        }
        catch(Exception e)
        {
        	e.printStackTrace();
        	throw new HisDataAccessException("HelperMethodsDAO.getResultset"+e);
        }
	}
	
	// Getting the detail of Allergy Type
	public AllergyTypeVO getDetail(String _allergyCode,UserVO _userVO)
	{
		String query  = "";
		AllergyTypeVO	allergyTypeVO=new AllergyTypeVO(); 
        Map populateMAP =new HashMap();
        Sequence sq=new Sequence();
        String filename=OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
        String queryKey="SELECT.HGBT_ALLERGIES_TYPE_MST";
        
        
        try
        {
        	query =HelperMethodsDAO.getQuery(filename,queryKey);
        }
        catch(Exception e)
        {
        	throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);
        }
        log.info(query);
        
     
        populateMAP.put(sq.next(),_allergyCode);
        populateMAP.put(sq.next(),Config.SUPER_USER_HOSPITAL_CODE);
        try
        {
     		ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);	 
     	    if(!rs.next())
     	    {
     	 	   	throw new HisRecordNotFoundException("No record for previous diagnosis found");	 	    
     	 	}
     	 	rs.beforeFirst();
     	 	   
     	 	HelperMethods.populateVOfrmRS(allergyTypeVO,rs);
     	}
     	catch(Exception e)
     	{
     		if(e.getClass()==HisRecordNotFoundException.class)
     		{
     			throw new HisRecordNotFoundException(e.getMessage());	
     		}
     		else			 		
     		 throw new HisDataAccessException("Application Execution Exception"+e);			 
     	}			 
        return allergyTypeVO;
	}
	
	// Updating the Allergy Type
	public void update(AllergyTypeVO _allergyTypeVO,UserVO _userVO)
	{
		String query  = "";
	    Map populateMAP =new HashMap();
        Sequence sq=new Sequence();
        String filename=OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
        String queryKey="UPDATE.HGBT_ALLERGIES_TYPE_MST";
        
        try
        {
        	query =HelperMethodsDAO.getQuery(filename,queryKey);
        }
        catch(Exception e)
        {
        	throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);
        }
        log.info(query);
        
        try
        {
        	populateMAP.put(sq.next(),_allergyTypeVO.getAllergiesType());
        	populateMAP.put(sq.next(),_allergyTypeVO.getAllergiesDesc());
        	populateMAP.put(sq.next(),_allergyTypeVO.getSourceFlag());
        	populateMAP.put(sq.next(),_allergyTypeVO.getLongQuery());
        	populateMAP.put(sq.next(),_allergyTypeVO.getIsValid());
        	populateMAP.put(sq.next(),_allergyTypeVO.getAllergySensitivity());
        	populateMAP.put(sq.next(),_allergyTypeVO.getAllergiesCode());
        	populateMAP.put(sq.next(),Config.SUPER_USER_HOSPITAL_CODE);
        	
        }
        catch(Exception e)
        {
        	throw new HisApplicationExecutionException(""+e);
        }
        
        try
        {
        	int i=HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(),query,populateMAP);
        	if(i==0)
        	{
           		throw new HisUpdateUnsuccesfullException();
           	}
        }
        catch(Exception e)
        {
        	e.printStackTrace();
        	throw new HisUpdateUnsuccesfullException("HelperMethodsDAO.getResultset"+e);
        }
	}
	
	//checking for duplicate before insert
	
	public String checkDuplicateBeforeInsert(AllergyTypeVO _allergyTypeVO,UserVO _userVO)
	{
		ResultSet rs;
		String query  = "";
		Map populateMAP =new HashMap();
		Sequence sq=new Sequence();
		String filename=OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String queryKey="CHECK_DUPLICATE_BEFORE_INSERT.HGBT_ALLERGIES_TYPE_MST";
		String record=null;
		
		try
		{
			query =HelperMethodsDAO.getQuery(filename,queryKey);
		}
		catch(Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);
		}
		log.info(query);
		
		try
		{
			populateMAP.put(sq.next(),_allergyTypeVO.getAllergiesType());
			populateMAP.put(sq.next(),Config.IS_VALID_DELETED);
			populateMAP.put(sq.next(),Config.SUPER_USER_HOSPITAL_CODE);
		}
		catch(Exception e)
		{
			throw new HisApplicationExecutionException(""+e);
		}
		
		try
		{
			rs=HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(),query,populateMAP);
			if(rs.next() )
			{
				record=rs.getString(1);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			throw new HisUpdateUnsuccesfullException("HelperMethodsDAO.getResultset"+e);
		}
		return record;
	}
	public String checkDuplicateBeforeUpdate(AllergyTypeVO _allergyTypeVO,UserVO _userVO)
	{
		ResultSet rs;
		String query  = "";
		Map populateMAP =new HashMap();
		Sequence sq=new Sequence();
		String filename=OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String queryKey="CHECK_DUPLICATE_BEFORE_UPDATE.HGBT_ALLERGIES_TYPE_MST";
		String record=null;
		
		try
		{
			query =HelperMethodsDAO.getQuery(filename,queryKey);
		}
		catch(Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);
		}
		log.info(query);
		
		try
		{
			populateMAP.put(sq.next(),_allergyTypeVO.getAllergiesType());
			populateMAP.put(sq.next(),Config.IS_VALID_DELETED);
			populateMAP.put(sq.next(),Config.SUPER_USER_HOSPITAL_CODE);
			populateMAP.put(sq.next(),_allergyTypeVO.getAllergiesCode());
		}
		catch(Exception e)
		{
			throw new HisApplicationExecutionException(""+e);
		}
		
		try
		{
			rs=HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(),query,populateMAP);
			if(rs.next() )
			{
				record=rs.getString(1);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			throw new HisUpdateUnsuccesfullException("HelperMethodsDAO.getResultset"+e);
		}
		return record;
	}
	
	public String getAllergySensitivitydesc(String sensitivity,UserVO _userVO)
	{
		ResultSet rs;
		String query  = "";
		Map populateMAP =new HashMap();
		Sequence sq=new Sequence();
		String filename=OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String queryKey="SELECT_SENSITIVITY_DESC.HGBT_ALLERGY_SENSITIVITY_MST";
		String record=null;
		
		try
		{
			query =HelperMethodsDAO.getQuery(filename,queryKey);
		}
		catch(Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);
		}
		log.info(query);
		
		try
		{
			populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(),Config.SUPER_USER_HOSPITAL_CODE);
			populateMAP.put(sq.next(),sensitivity);
		}
		catch(Exception e)
		{
			throw new HisApplicationExecutionException(""+e);
		}
		
		try
		{
			rs=HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(),query,populateMAP);
			if(rs.next() )
			{
				record=rs.getString(1);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			throw new HisUpdateUnsuccesfullException("HelperMethodsDAO.getResultset"+e);
		}
		return record;
	}
}
