package mrd.masters.dao;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.JDBCTransactionContext;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.Sequence;
import hisglobal.vo.RackShelfMstVO;
import hisglobal.vo.UserVO;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import mrd.MrdConfig;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class RackShelfMstDAO extends DataAccessObject implements RackShelfMstDAOi {

	Logger log;

	public RackShelfMstDAO(JDBCTransactionContext _tx)
	{
		super(_tx);
		log = LogManager.getLogger(this.getClass());
	}

	
	//generate rackShelfId
	
	public void create(RackShelfMstVO rackShelfMstVO,UserVO _userVO){
		
		//ResultSet rs;
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		//String rackShelfId="";
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_MASTER_DAO;
		String queryKey = "INSERT.HPMRT_RACK_SHELF_MST";
		
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
			populateMAP.put(sq.next(), rackShelfMstVO.getRackId());
			populateMAP.put(sq.next(), rackShelfMstVO.getRackId());
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), rackShelfMstVO.getRackId());
			populateMAP.put(sq.next(), rackShelfMstVO.getShelfStatus());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), rackShelfMstVO.getShelfCapacity());
			populateMAP.put(sq.next(), rackShelfMstVO.getShelfLabel());
			populateMAP.put(sq.next(), _userVO.getSeatId());
						
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
			throw new HisDataAccessException("HelperMethodsDAO.create" + e);
		}
		
	}
	
	
	/* **********************************************************************************************
	 * Check For Existing Shelf Name before inserting a record
	 * If A same shelf name exists for the rackId then the method return true
	 * otherwise return false
	 * **********************************************************************************************/
	
	public boolean checkDuplicateBeforeSave(RackShelfMstVO rackShelfMstVO,UserVO _userVO){
		
		ResultSet rs;
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		boolean flag=false;
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_MASTER_DAO;
		String queryKey = "CHECK_DUPLICATE.BEFORE_INSERT.HPMRT_RACK_SHELF_MST";
		
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
			populateMAP.put(sq.next(), rackShelfMstVO.getShelfLabel().trim());
			populateMAP.put(sq.next(), Config.IS_VALID_DELETED);
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), rackShelfMstVO.getRackId());
						
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("RackShelfMstDAO.populateMAP::" + e);
		}
		try
		{
			rs=HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if(rs.next()){
				flag=false;
			}
			else{
				flag=true;
			}
			
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		
		return flag;
	}
	
	/* **********************************************************************************************
	 * Check For Existing Shelf Name before modifying a record
	 * If A same shelf name exists for the rackId then the method return true
	 * otherwise return false
	 * **********************************************************************************************/
	
	public boolean checkDuplicateBeforeModify(RackShelfMstVO rackShelfMstVO,UserVO _userVO){
		
		ResultSet rs;
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		boolean flag=false;
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_MASTER_DAO;
		String queryKey = "CHECK_DUPLICATE.BEFORE_MODIFY.HPMRT_RACK_SHELF_MST";
		
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
			populateMAP.put(sq.next(), rackShelfMstVO.getShelfLabel().trim());
			populateMAP.put(sq.next(), Config.IS_VALID_DELETED);
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), rackShelfMstVO.getRackId());
			populateMAP.put(sq.next(), rackShelfMstVO.getRackShelfId());
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("RackShelfMstDAO.populateMAP::" + e);
		}
		try
		{
			rs=HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if(rs.next()){
				flag=false;
			}
			else{
				flag=true;
			}
			
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		
		return flag;
	}

	//get RackShelf Detail
	public RackShelfMstVO getRackShelfDetail(RackShelfMstVO rackShelfMstVO,UserVO _userVO){
		
		ResultSet rs;
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_MASTER_DAO;
		String queryKey = "SELECT.BY_PRIMARY_KEY.HPMRT_RACK_SHELF_MST";
		
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
			
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(),rackShelfMstVO.getRackShelfId());
			populateMAP.put(sq.next(),rackShelfMstVO.getSerialNo());
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
						
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("RackShelfMstDAO.populateMAP::" + e);
		}
		try
		{
			rs=HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if(rs.next()){
				HelperMethods.populateVOfrmRS(rackShelfMstVO, rs);
			}
			else{
				throw new HisRecordNotFoundException("Record Not Found");
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
		
		return rackShelfMstVO;
	}

	public void modifyRackShelf(RackShelfMstVO rackShelfVO ,UserVO userVO) {
		
		//ResultSet rs;
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_MASTER_DAO;
		String queryKey = "MODIFY.HPMRT_RACK_SHELF_MST";
		
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
			populateMAP.put(sq.next(), userVO.getSeatId());
			populateMAP.put(sq.next(), Config.IS_VALID_DELETED);
			populateMAP.put(sq.next(), rackShelfVO.getRackShelfId());
			populateMAP.put(sq.next(), userVO.getHospitalCode());
			populateMAP.put(sq.next(), rackShelfVO.getSerialNo());
						
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
	
	public void modifyInsertRackShelf(RackShelfMstVO rackShelfVO ,UserVO userVO) {
		
		//ResultSet rs;
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		//String rackShelfId="";
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_MASTER_DAO;
		String queryKey = "MODIFY.INSERT.HPMRT_RACK_SHELF_MST";
		
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
			populateMAP.put(sq.next(), rackShelfVO.getRackShelfId());
			populateMAP.put(sq.next(), userVO.getHospitalCode());
			populateMAP.put(sq.next(), rackShelfVO.getRackShelfId());
			populateMAP.put(sq.next(), userVO.getHospitalCode());
			populateMAP.put(sq.next(), rackShelfVO.getRackId());
			populateMAP.put(sq.next(), rackShelfVO.getShelfStatus());
			populateMAP.put(sq.next(), rackShelfVO.getIsValid());
			populateMAP.put(sq.next(), rackShelfVO.getShelfCapacity());
			populateMAP.put(sq.next(), rackShelfVO.getShelfLabel());
			populateMAP.put(sq.next(), userVO.getSeatId());
						
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
			throw new HisDataAccessException("HelperMethodsDAO.create" + e);
		}
			
	}
	
	// on Add page for Saving Data

	/*public List getNotAssignedShelf(String rackId,UserVO _userVO){
		
		ResultSet rs;
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		List notAssignedShelfList=new ArrayList();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_MASTER_DAO;
		String queryKey = "SELECT.NOT_ASSIGNED_SHELF.HPMRT_RACK_MST";
		
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
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), rackId);	
						
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("RackShelfMstDAO.populateMAP::" + e);
		}
		try
		{
			rs=HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if(rs.next()){
				notAssignedShelfList=HelperMethodsDAO.getAlOfEntryObjects(rs);
			}
			else{
				throw new HisRecordNotFoundException("Shelf Record Not Found");
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
		
		return notAssignedShelfList;
	}
	
	public List getShelfList(UserVO _userVO){
		
		ResultSet rs;
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		List shelfList=new ArrayList();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_MASTER_DAO;
		String queryKey = "SELECT.SHELF_NAME_LIST.HPMRT_SHELF_MST";
		
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
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
						
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("RackShelfMstDAO.populateMAP::" + e);
		}
		try
		{
			rs=HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if(rs.next()){
				shelfList=HelperMethodsDAO.getAlOfEntryObjects(rs);
			}
			else{
				throw new HisRecordNotFoundException("Shelf Record Not Found");
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
		
		return shelfList;
	}
	
	public List getAssignedShelf(String rackId,UserVO _userVO){
		
		ResultSet rs;
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		List assignedShelfList=new ArrayList();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_MASTER_DAO;
		String queryKey = "SELECT.ASSIGNED_SHELF.HPMRT_RACK_MST";
		
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
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), rackId);
						
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("RackShelfMstDAO.populateMAP::" + e);
		}
		try
		{
			rs=HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if(rs.next()){
				assignedShelfList=HelperMethodsDAO.getAlOfEntryObjects(rs);
			}
			else{
				throw new HisRecordNotFoundException("Shelf Record Not Found");
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
		
		return assignedShelfList;
	}
	*/
	
	
	
	
	
}
