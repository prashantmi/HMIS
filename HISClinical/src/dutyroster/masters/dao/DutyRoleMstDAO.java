package dutyroster.masters.dao;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisDuplicateRecordException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.JDBCTransactionContext;
import hisglobal.utility.Sequence;
import hisglobal.vo.DutyRoleMstVO;
import hisglobal.vo.UserVO;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import dutyroster.DutyRosterConfig;


public class DutyRoleMstDAO extends DataAccessObject implements DutyRoleMstDAOi {

	Logger log;

	public DutyRoleMstDAO(JDBCTransactionContext _tx)
	{
		super(_tx);
		log = LogManager.getLogger(this.getClass());
	}

	// on Add page for Saving Data
	public void saveDutyRole(DutyRoleMstVO roleMstVO, UserVO userVO) {
	
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = DutyRosterConfig.QUERY_FILE_FOR_DUTY_ROSTER_MASTERSDAO;
		String queryKey = "INSERT.HDRT_DUTY_ROLE_MST";

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
			populateMAP.put(sq.next(), roleMstVO.getDutyRoleDesc());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), userVO.getSeatId());
			populateMAP.put(sq.next(), roleMstVO.getRoleShortName());

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
	public DutyRoleMstVO getDutyRole(DutyRoleMstVO roleMstVO, UserVO userVO) 
	{
		String query = "";
		ResultSet rs;
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence(); 
		
		String filename = DutyRosterConfig.QUERY_FILE_FOR_DUTY_ROSTER_MASTERSDAO;
		String queryKey = "SELECT.HDRT_DUTY_ROLE_MST";

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
			populateMAP.put(sq.next(), roleMstVO.getDutyRoleId());
			populateMAP.put(sq.next(), userVO.getHospitalCode()); 		
			populateMAP.put(sq.next(), roleMstVO.getSerialNo());
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("BloodDonorTypeMstDAO.populateMAP::" + e);
		}
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			
			if(rs.next()){
				 	roleMstVO.setDutyRoleDesc(rs.getString(1));
				 	roleMstVO.setIsActive(rs.getString(2));
				 	roleMstVO.setRoleShortName(rs.getString(3));
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
	 return roleMstVO;
		
	}

	// for Updating The old Record
	public void modifyDutyRole(DutyRoleMstVO roleMstVO, UserVO userVO) {
	
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = DutyRosterConfig.QUERY_FILE_FOR_DUTY_ROSTER_MASTERSDAO;
		String queryKey = "UPDATE.HDRT_DUTY_ROLE_MST";

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
			populateMAP.put(sq.next(), roleMstVO.getDutyRoleId());
			populateMAP.put(sq.next(), userVO.getHospitalCode()); 
			populateMAP.put(sq.next(), roleMstVO.getSerialNo());

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

	public void modifyInsertDutyRole(DutyRoleMstVO roleMstVO, UserVO userVO) { 

		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = DutyRosterConfig.QUERY_FILE_FOR_DUTY_ROSTER_MASTERSDAO;
		String queryKey = "MODIFY.INSERT.HDRT_DUTY_ROLE_MST";

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
			populateMAP.put(sq.next(), roleMstVO.getDutyRoleId());
			populateMAP.put(sq.next(), userVO.getHospitalCode()); 	
			populateMAP.put(sq.next(), roleMstVO.getDutyRoleId());
			populateMAP.put(sq.next(), userVO.getHospitalCode()); 			
			populateMAP.put(sq.next(), roleMstVO.getDutyRoleDesc());
			populateMAP.put(sq.next(), roleMstVO.getIsActive());
			populateMAP.put(sq.next(), userVO.getSeatId());
			populateMAP.put(sq.next(), roleMstVO.getRoleShortName());

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
	
	public void checkDuplicateBeforeInsert(String dutyRoleDesc,UserVO userVO) {
		
		String query  = "";
	    Map populateMAP =new HashMap();
	    String filename = DutyRosterConfig.QUERY_FILE_FOR_DUTY_ROSTER_MASTERSDAO;
	    String queryKey="DUPLICATE_CHECK.INSERT.HDRT_DUTY_ROLE_MST";
	    ResultSet rs=null;
	  
	    try{
	    	query =HelperMethodsDAO.getQuery(filename,queryKey);
	    }
	    catch(Exception e){
	    	throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);
	    }
	    
	    try{
	    	Sequence sq=new Sequence();
	    	populateMAP.put(sq.next(), dutyRoleDesc);
	    	populateMAP.put(sq.next(), userVO.getHospitalCode());
	    	populateMAP.put(sq.next(), Config.IS_VALID_DELETED);
	    }
	    catch(Exception e){
	    	throw new HisApplicationExecutionException("DeptMasterDAO:populateMap(_deptMasterVO,populateMAP)::"+e);
	    }
	    
	    try{
			  rs=HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(),query,populateMAP);
			 if(rs.next()){
				// record=rs.getString(1); 
				 throw new HisDuplicateRecordException("Role Name Already Exists");
			 }	  
			 
				 
		   }
	    catch(HisDuplicateRecordException e){
	    	e.printStackTrace();
	    	throw new HisDuplicateRecordException(e.getMessage());
	    }
	    catch(Exception e){
	    	e.printStackTrace();
	    	throw new HisDataAccessException("HelperMethodsDAO.getResultset"+e);
	    }
	
	}

public void checkDuplicateBeforeModify(DutyRoleMstVO roleMstVO,UserVO userVO) {
		
		String query  = "";
	    Map populateMAP =new HashMap();
	    String filename = DutyRosterConfig.QUERY_FILE_FOR_DUTY_ROSTER_MASTERSDAO;
	    String queryKey="DUPLICATE_CHECK.MODIFY.HDRT_DUTY_ROLE_MST";
	    
	    ResultSet rs=null;
	       
	    try{
	    	query =HelperMethodsDAO.getQuery(filename,queryKey);
	    }
	    catch(Exception e){
	    	throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);
	    }
	    
	    try{
	    	Sequence sq=new Sequence();
	    	populateMAP.put(sq.next(), roleMstVO.getDutyRoleId());
	    	populateMAP.put(sq.next(), roleMstVO.getDutyRoleDesc());
	    	populateMAP.put(sq.next(), userVO.getHospitalCode());
	    	populateMAP.put(sq.next(), Config.IS_VALID_DELETED);
	    }
	    catch(Exception e){
	    	throw new HisApplicationExecutionException("DeptMasterDAO:populateMap(_deptMasterVO,populateMAP)::"+e);
	    }
	    
	    try{
			 rs=HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(),query,populateMAP);
			 if(rs.next()){
				 throw new HisDuplicateRecordException("Role Name Already Exists"); 
			 }	   
				 
		   }
	    catch(HisDuplicateRecordException e){
	    	e.printStackTrace();
	    	throw new HisDuplicateRecordException(e.getMessage());
	    }
	    catch(Exception e){
	    	e.printStackTrace();
	    	throw new HisDataAccessException("HelperMethodsDAO.getResultset"+e);
	    }
		
	}
	

public void checkDuplicateShortNameBeforeInsert(String roleShortName, UserVO _userVO) {
	
	String query  = "";
    Map populateMAP =new HashMap();
    String filename = DutyRosterConfig.QUERY_FILE_FOR_DUTY_ROSTER_MASTERSDAO;
    String queryKey="DUPLICATE_SHORT_NAME_CHECK.INSERT.HDRT_DUTY_ROLE_MST";
    
    ResultSet rs=null;
  
    try{
    	query =HelperMethodsDAO.getQuery(filename,queryKey);
    }
    catch(Exception e){
    	throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);
    }
    
    try{
    	Sequence sq=new Sequence();
    	populateMAP.put(sq.next(), roleShortName);
    	populateMAP.put(sq.next(), _userVO.getHospitalCode());
    	populateMAP.put(sq.next(), Config.IS_VALID_DELETED);
    }
    catch(Exception e){
    	throw new HisApplicationExecutionException("DeptMasterDAO:populateMap(_deptMasterVO,populateMAP)::"+e);
    }
    
    try{
		  rs=HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(),query,populateMAP);
		 if(rs.next()){

			 throw new HisDuplicateRecordException("Role Short Name Already Exists");
		 }	   
			 
	   }
    catch(HisDuplicateRecordException e){
    	e.printStackTrace();
    	throw new HisDuplicateRecordException(e.getMessage());
    }
    catch(Exception e){
    	e.printStackTrace();
    	throw new HisDataAccessException("HelperMethodsDAO.getResultset"+e);
    }

}

public void checkDuplicateShortNameBeforeModify(DutyRoleMstVO _dutyRoleMstVO, UserVO _userVO) {
	
	String query  = "";
    Map populateMAP =new HashMap();
    String filename = DutyRosterConfig.QUERY_FILE_FOR_DUTY_ROSTER_MASTERSDAO;
    String queryKey="DUPLICATE_SHORT_NAME_CHECK.MODIFY.HDRT_DUTY_ROLE_MST";

    ResultSet rs=null;
       
    try{
    	query =HelperMethodsDAO.getQuery(filename,queryKey);
    }
    catch(Exception e){
    	throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);
    }
    
    try{
    	Sequence sq=new Sequence();
    	populateMAP.put(sq.next(), _dutyRoleMstVO.getDutyRoleId());
    	populateMAP.put(sq.next(), _dutyRoleMstVO.getRoleShortName());
    	populateMAP.put(sq.next(), _userVO.getHospitalCode());
    	populateMAP.put(sq.next(), Config.IS_VALID_DELETED);
    }
    catch(Exception e){
    	throw new HisApplicationExecutionException("DeptMasterDAO:populateMap(_deptMasterVO,populateMAP)::"+e);
    }
    
    try{
		 rs=HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(),query,populateMAP);
		 if(rs.next()){
			 throw new HisDuplicateRecordException("Role Short Name Already Exists");
		 }	   
			 
	   }
    catch(HisDuplicateRecordException e){
    	e.printStackTrace();
    	throw new HisDuplicateRecordException(e.getMessage());
    }
    catch(Exception e){
    	e.printStackTrace();
    	throw new HisDataAccessException("HelperMethodsDAO.getResultset"+e);
    }

    
}


}
