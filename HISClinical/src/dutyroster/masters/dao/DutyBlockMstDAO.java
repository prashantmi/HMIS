package dutyroster.masters.dao;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.JDBCTransactionContext;
import hisglobal.utility.Sequence;
import hisglobal.vo.DutyBlockMstVO;
import hisglobal.vo.UserVO;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import dutyroster.DutyRosterConfig;


public class DutyBlockMstDAO extends DataAccessObject implements DutyBlockMstDAOi {

	Logger log;

	public DutyBlockMstDAO(JDBCTransactionContext _tx)
	{
		super(_tx);
		log = LogManager.getLogger(this.getClass());
	}

	// on Add page for Saving Data
	public void saveDutyBlock(DutyBlockMstVO blockMstVO, UserVO userVO) {
	
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = DutyRosterConfig.QUERY_FILE_FOR_DUTY_ROSTER_MASTERSDAO;
		String queryKey = "INSERT.HDRT_DUTY_BLOCK_MST";

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
			populateMAP.put(sq.next(), blockMstVO.getDutyBlockName());
			populateMAP.put(sq.next(), blockMstVO.getDutyBlockDesc());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), userVO.getSeatId());

		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("DutyBlockMstDAO.populateMAP::" + e);
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
	public DutyBlockMstVO getDutyBlock(DutyBlockMstVO blockMstVO, UserVO userVO) 
	{
		String query = "";
		ResultSet rs;
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence(); 
		
		String filename = DutyRosterConfig.QUERY_FILE_FOR_DUTY_ROSTER_MASTERSDAO;
		String queryKey = "SELECT.HDRT_DUTY_BLOCK_MST";

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
			populateMAP.put(sq.next(), blockMstVO.getDutyBlockId());
			populateMAP.put(sq.next(), userVO.getHospitalCode()); 		
			populateMAP.put(sq.next(), blockMstVO.getSerialNo());
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("DutyBlockMstDAO.populateMAP::" + e);
		}
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			
			if(rs.next()){
				 	blockMstVO.setDutyBlockName(rs.getString(1));
				 	blockMstVO.setDutyBlockDesc(rs.getString(2));
				 	blockMstVO.setIsActive(rs.getString(3));
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
	 return blockMstVO;
		
	}

	// for Updating The old Record
	public void modifyDutyBlock(DutyBlockMstVO blockMstVO, UserVO userVO) {
	
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = DutyRosterConfig.QUERY_FILE_FOR_DUTY_ROSTER_MASTERSDAO;
		String queryKey = "UPDATE.HDRT_DUTY_BLOCK_MST";

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
			populateMAP.put(sq.next(), blockMstVO.getDutyBlockId());
			populateMAP.put(sq.next(), userVO.getHospitalCode()); 
			populateMAP.put(sq.next(), blockMstVO.getSerialNo());

		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("DutyBlockMstDAO.populateMAP::" + e);
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

	
	//inserting modified Record
	
	public void modifyInsertDutyBlock(DutyBlockMstVO blockMstVO, UserVO userVO) { 

		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = DutyRosterConfig.QUERY_FILE_FOR_DUTY_ROSTER_MASTERSDAO;
		String queryKey = "MODIFY.INSERT.HDRT_DUTY_BLOCK_MST";

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
			populateMAP.put(sq.next(), blockMstVO.getDutyBlockId());
			populateMAP.put(sq.next(), userVO.getHospitalCode()); 	
			populateMAP.put(sq.next(), blockMstVO.getDutyBlockId());
			populateMAP.put(sq.next(), userVO.getHospitalCode()); 			
			populateMAP.put(sq.next(), blockMstVO.getDutyBlockName());
			populateMAP.put(sq.next(), blockMstVO.getDutyBlockDesc());
			populateMAP.put(sq.next(), blockMstVO.getIsActive());
			populateMAP.put(sq.next(), userVO.getSeatId());

		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("DutyBlockMstDAO.populateMAP::" + e);
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
	
	public String checkDuplicateBeforeInsert(String dutyBlockName,UserVO userVO) {
		
		String query  = "";
	    Map populateMAP =new HashMap();
	    String filename = DutyRosterConfig.QUERY_FILE_FOR_DUTY_ROSTER_MASTERSDAO;
	    String queryKey="DUPLICATE_CHECK.INSERT.HDRT_DUTY_BLOCK_MST";
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
	    	populateMAP.put(sq.next(), dutyBlockName);
	    	populateMAP.put(sq.next(), Config.IS_VALID_DELETED);
	    	populateMAP.put(sq.next(), userVO.getHospitalCode());
	    	
	    }
	    catch(Exception e){
	    	throw new HisApplicationExecutionException("DutyBlockMstDAO:populateMap(_deptMasterVO,populateMAP)::"+e);
	    }
	    
	    try{
			  rs=HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(),query,populateMAP);
			 if(rs.next()){
				 record=rs.getString(1); 
			 }	   
				 
		   }
	    catch(Exception e){
	    	e.printStackTrace();
	    	throw new HisDataAccessException("HelperMethodsDAO.getResultset"+e);
	    }
		return record;
	}

	public String checkDuplicateBeforeModify(DutyBlockMstVO blockMstVO,UserVO userVO) {
		
		String query  = "";
	    Map populateMAP =new HashMap();
	    String filename = DutyRosterConfig.QUERY_FILE_FOR_DUTY_ROSTER_MASTERSDAO;
	    String queryKey="DUPLICATE_CHECK.MODIFY.HDRT_DUTY_BLOCK_MST";
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
	    	populateMAP.put(sq.next(), blockMstVO.getDutyBlockId());
	    	populateMAP.put(sq.next(), blockMstVO.getDutyBlockName());
	    	populateMAP.put(sq.next(), Config.IS_VALID_DELETED);
	    	populateMAP.put(sq.next(), userVO.getHospitalCode());
	    	
	    }
	    catch(Exception e){
	    	throw new HisApplicationExecutionException("DutyBlockMstDAO:populateMap(_deptMasterVO,populateMAP)::"+e);
	    }
	    
	    try{
			 rs=HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(),query,populateMAP);
			 if(rs.next()){
				 record=rs.getString(1); 
			 }	   
				 
		   }
	    catch(Exception e){
	    	e.printStackTrace();
	    	throw new HisDataAccessException("HelperMethodsDAO.getResultset"+e);
	    }
		return record;
	}
	
}
