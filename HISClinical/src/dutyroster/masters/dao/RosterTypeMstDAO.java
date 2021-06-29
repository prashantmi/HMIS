package dutyroster.masters.dao;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.JDBCTransactionContext;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.Sequence;
import hisglobal.vo.RosterCategoryMstVO;
import hisglobal.vo.RosterTypeMstVO;
import hisglobal.vo.UserVO;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import dutyroster.DutyRosterConfig;


public class RosterTypeMstDAO extends DataAccessObject implements RosterTypeMstDAOi {

	Logger log;

	public RosterTypeMstDAO(JDBCTransactionContext _tx)
	{
		super(_tx);
		log = LogManager.getLogger(this.getClass());
	}

	// on Add page for Saving Data
	public void create(RosterTypeMstVO rosterTypeVO, UserVO userVO) {
	
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = DutyRosterConfig.QUERY_FILE_FOR_DUTY_ROSTER_MASTERSDAO;
		String queryKey = "INSERT.HDRT_ROSTER_TYPE_MST";

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
			populateMAP.put(sq.next(), userVO.getHospitalCode());
			populateMAP.put(sq.next(), userVO.getHospitalCode()); 			
			populateMAP.put(sq.next(), rosterTypeVO.getRosterTypeName());
			populateMAP.put(sq.next(), rosterTypeVO.getRosterCategory());
			populateMAP.put(sq.next(), rosterTypeVO.getAreaType());
			populateMAP.put(sq.next(), rosterTypeVO.getDutyType());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), userVO.getSeatId());
			populateMAP.put(sq.next(), rosterTypeVO.getRosterGenerationMethod());
			populateMAP.put(sq.next(), rosterTypeVO.getReliverMode());
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("BloodDonorTypeMstDAO.populateMAP::" + e);
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

	// on modify Page  for Showing Data of Selected Record
	public RosterTypeMstVO fetch(RosterTypeMstVO rosterTypeVO, UserVO userVO) 
	{
		String query = "";
		ResultSet rs;
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence(); 
		
		String filename = DutyRosterConfig.QUERY_FILE_FOR_DUTY_ROSTER_MASTERSDAO;
		String queryKey = "SELECT.HDRT_ROSTER_TYPE_MST";

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
			populateMAP.put(sq.next(), rosterTypeVO.getRosterTypeId());
			populateMAP.put(sq.next(), rosterTypeVO.getSerialNo());
			populateMAP.put(sq.next(), userVO.getHospitalCode()); 		
			
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("BloodDonorTypeMstDAO.populateMAP::" + e);
		}
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			
			if(rs.next()){
				 	HelperMethods.populateVOfrmRS(rosterTypeVO, rs);
			}else
			{
				throw new HisRecordNotFoundException("Details Not Found");
			}	
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class) throw new HisRecordNotFoundException(e.getMessage());
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
	 return rosterTypeVO;
		
	}

	// for Updating The old Record
	public void update(RosterTypeMstVO rosterTypeVO, UserVO userVO) {
	
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = DutyRosterConfig.QUERY_FILE_FOR_DUTY_ROSTER_MASTERSDAO;
		String queryKey = "UPDATE.HDRT_ROSTER_TYPE_MST";

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
			populateMAP.put(sq.next(), Config.IS_VALID_DELETED);
			populateMAP.put(sq.next(), userVO.getSeatId());
			populateMAP.put(sq.next(), rosterTypeVO.getRosterTypeId());
			populateMAP.put(sq.next(), userVO.getHospitalCode()); 
			populateMAP.put(sq.next(), rosterTypeVO.getSerialNo());

		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("BloodDonorTypeMstDAO.populateMAP::" + e);
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

	public void createwhileUpdate(RosterTypeMstVO rosterTypeVO, UserVO userVO) { 

		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = DutyRosterConfig.QUERY_FILE_FOR_DUTY_ROSTER_MASTERSDAO;
		String queryKey = "MODIFY.INSERT.HDRT_ROSTER_TYPE_MST";

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
			populateMAP.put(sq.next(), rosterTypeVO.getRosterTypeId());
			populateMAP.put(sq.next(), userVO.getHospitalCode());
			populateMAP.put(sq.next(), rosterTypeVO.getRosterTypeId());
			populateMAP.put(sq.next(), userVO.getHospitalCode()); 			
			populateMAP.put(sq.next(), rosterTypeVO.getRosterTypeName());
			populateMAP.put(sq.next(), rosterTypeVO.getRosterCategory());
			populateMAP.put(sq.next(), rosterTypeVO.getAreaType());
			populateMAP.put(sq.next(), rosterTypeVO.getDutyType());
			populateMAP.put(sq.next(), rosterTypeVO.getIsActive());
			populateMAP.put(sq.next(), userVO.getSeatId());
			populateMAP.put(sq.next(), rosterTypeVO.getRosterGenerationMethod());
			populateMAP.put(sq.next(), rosterTypeVO.getReliverMode());
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("BloodDonorTypeMstDAO.populateMAP::" + e);
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
	
	public int checkDuplicateBeforeInsert(String rosterTypeName,UserVO userVO) {
		
		int result=0;
		String query  = "";
	    Map populateMAP =new HashMap();
	    String filename = DutyRosterConfig.QUERY_FILE_FOR_DUTY_ROSTER_MASTERSDAO;
	    String queryKey="DUPLICATE_CHECK.INSERT.HDRT_ROSTER_TYPE_MST";
	    String record=null;
	    ResultSet rs=null;
	  
	    try{
	    	query =HelperMethodsDAO.getQuery(filename,queryKey);
	    }
	    catch(Exception e){
	    	throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);
	    }
	    
	    try{
	    	Sequence sq=new Sequence();
	    	populateMAP.put(sq.next(), rosterTypeName);
	    	populateMAP.put(sq.next(), userVO.getHospitalCode());
	    	populateMAP.put(sq.next(), Config.IS_VALID_DELETED);
	    }
	    catch(Exception e){
	    	throw new HisApplicationExecutionException("DeptMasterDAO:populateMap(_deptMasterVO,populateMAP)::"+e);
	    }
	    
	    try{
			  rs=HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(),query,populateMAP);
			 if(rs.next()){
				 if(rs.getRow()>0)
					 result=rs.getInt(1);
			 }	   
				 
		   }
	    catch(Exception e){
	    	e.printStackTrace();
	    	throw new HisDataAccessException("HelperMethodsDAO.getResultset"+e);
	    }
		return result;
	}

public int checkDuplicateBeforeModify(RosterTypeMstVO rosterTypeVO,UserVO userVO) {
		
		int result=0;
		String query  = "";
	    Map populateMAP =new HashMap();
	    String filename = DutyRosterConfig.QUERY_FILE_FOR_DUTY_ROSTER_MASTERSDAO;
	    String queryKey="DUPLICATE_CHECK.MODIFY.HDRT_ROSTER_TYPE_MST";
	    String record=null;
	    ResultSet rs=null;
	       
	    try{
	    	query =HelperMethodsDAO.getQuery(filename,queryKey);
	    }
	    catch(Exception e){
	    	throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);
	    }
	    
	    try{
	    	Sequence sq=new Sequence();
	    	populateMAP.put(sq.next(), rosterTypeVO.getRosterTypeId());
	    	populateMAP.put(sq.next(), rosterTypeVO.getRosterTypeName());
	    	populateMAP.put(sq.next(), userVO.getHospitalCode());
	    	populateMAP.put(sq.next(), Config.IS_VALID_DELETED);
	    }
	    catch(Exception e){
	    	throw new HisApplicationExecutionException("DeptMasterDAO:populateMap(_deptMasterVO,populateMAP)::"+e);
	    }
	    
	    try{
			 rs=HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(),query,populateMAP);
			 if(rs.next()){
				 result=rs.getInt(1);
				 
			 }	   
				 
		   }
	    catch(Exception e){
	    	e.printStackTrace();
	    	throw new HisDataAccessException("HelperMethodsDAO.getResultset"+e);
	    }
		return result;
	}


	public List getRosterCat(UserVO userVO) {
	
		ResultSet rs;
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		List rosterCatList=new ArrayList();
		String filename = DutyRosterConfig.QUERY_FILE_FOR_DUTY_ROSTER_MASTERSDAO;
		String queryKey = "SELECT.ROSTER_CAT.HDRT_ROSTER_CAT_MST";
	
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
			populateMAP.put(sq.next(), userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("BloodDonorTypeMstDAO.populateMAP::" + e);
		}
		try
		{
			rs=HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if(rs.next()){
				rosterCatList=HelperMethodsDAO.getAlOfEntryObjects(rs);
			}
			else{
				throw new HisRecordNotFoundException("Record of Roster Category Not Found");
			}
			
		}
		catch (Exception e)
		{

			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}

		return rosterCatList;
	}
	
	public List getDutyAreaType(UserVO userVO) {
		
		ResultSet rs;
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		List dutyAreaTypeList=new ArrayList();
		String filename = DutyRosterConfig.QUERY_FILE_FOR_DUTY_ROSTER_MASTERSDAO;
		String queryKey = "SELECT.AREA_TYPE.HDRT_DUTY_AREA_MST";
		
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
			populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);//userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
						
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("BloodDonorTypeMstDAO.populateMAP::" + e);
		}
		try
		{
			rs=HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			
			if(rs.next()){
				dutyAreaTypeList=HelperMethodsDAO.getAlOfEntryObjects(rs);
			}
			else{
				throw new HisRecordNotFoundException("Record of Area Type Not Found");
			}
		}
		catch (Exception e)
		{

			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}

		return dutyAreaTypeList;
	}
	
	
	public List checkDutyTypeWhileInsert(RosterTypeMstVO _rosterTypeVO, UserVO _userVO){
		
		String query  = "";
	    Map populateMAP =new HashMap();
	    String filename = DutyRosterConfig.QUERY_FILE_FOR_DUTY_ROSTER_MASTERSDAO;
	    String queryKey="SELECT_DUTY_TYPE_INSERT.HDRT_ROSTER_TYPE_MST";
	    
	     
	    ResultSet rs=null;
	    List dutyType=new ArrayList();
	    
	    try{
	    	query =HelperMethodsDAO.getQuery(filename,queryKey);
	    	
	    	
	    }
	    catch(Exception e){
	    	throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);
	    }
	  
	    
	    
	    try{
	    	Sequence sq=new Sequence();
	    	
	    	populateMAP.put(sq.next(), _rosterTypeVO.getRosterCategory());
	    	populateMAP.put(sq.next(), _rosterTypeVO.getAreaType());
	    	populateMAP.put(sq.next(), _userVO.getHospitalCode());
	    	populateMAP.put(sq.next(), Config.IS_VALID_DELETED);
	    	
	    }
	    catch(Exception e){
	    	throw new HisApplicationExecutionException("DeptMasterDAO:populateMap(_deptMasterVO,populateMAP)::"+e);
	    }
	    
	    try{
			  rs=HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(),query,populateMAP);
			 if(!rs.next()){
				// throw new HisRecordNotFoundException("Details Not Found");
			               }else
			               {
			             	   rs.beforeFirst();
			            	   while(rs.next())
			            	   {
			            		   dutyType.add(rs.getString(1));
			            	   }
			            		   
			               }	   
				 
		   }
	    catch(Exception e){
	    	e.printStackTrace();
	    	throw new HisDataAccessException("HelperMethodsDAO.getResultset"+e);
	    }
	  
		return dutyType;
	    
	}
	

public Map checkDutyTypeWhileModify(RosterTypeMstVO _rosterTypeVO, UserVO _userVO){
		
		String query  = "";
	    Map populateMAP =new HashMap();
	    String filename = DutyRosterConfig.QUERY_FILE_FOR_DUTY_ROSTER_MASTERSDAO;
	    String queryKey="SELECT_DUTY_TYPE_MODIFY.HDRT_ROSTER_TYPE_MST";
	    
	     
	    ResultSet rs=null;
	    Map dutyType=new HashMap();
	    
	    try{
	    	query =HelperMethodsDAO.getQuery(filename,queryKey);
	    	
	    	
	    }
	    catch(Exception e){
	    	throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);
	    }
	  
	    
	    
	    try{
	    	Sequence sq=new Sequence();
	    	
	    	populateMAP.put(sq.next(), _rosterTypeVO.getRosterCategory());
	    	populateMAP.put(sq.next(), _rosterTypeVO.getAreaType());
	    	populateMAP.put(sq.next(), _userVO.getHospitalCode());
	    	populateMAP.put(sq.next(), Config.IS_VALID_DELETED);
	    	populateMAP.put(sq.next(), _rosterTypeVO.getRosterTypeId());
	    	
	    }
	    catch(Exception e){
	    	throw new HisApplicationExecutionException("DeptMasterDAO:populateMap(_deptMasterVO,populateMAP)::"+e);
	    }
	    
	    try{
			  rs=HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(),query,populateMAP);
			 if(!rs.next()){
				// throw new HisRecordNotFoundException("Details Not Found");
			               }else
			               {
			             	   rs.beforeFirst();
			            	   while(rs.next())
			            	   {
			            		   dutyType.put(rs.getString(1), rs.getString(1));
			            	   }
			            		   
			               }	   
				 
		   }
	    catch(Exception e){
	    	e.printStackTrace();
	    	throw new HisDataAccessException("HelperMethodsDAO.getResultset"+e);
	    }
	  
		return dutyType;
	    
	}
	
}
