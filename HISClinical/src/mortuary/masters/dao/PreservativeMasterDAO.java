package mortuary.masters.dao;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisDuplicateRecordException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.JDBCTransactionContext;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.Sequence;
import hisglobal.vo.PreservativeMasterVO;
import hisglobal.vo.UserVO;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import mortuary.MortuaryConfig;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;



public class PreservativeMasterDAO extends DataAccessObject implements PreservativeMasterDAOi {

	Logger log;

	public PreservativeMasterDAO(JDBCTransactionContext _tx)
	{
		super(_tx);
		log = LogManager.getLogger(this.getClass());
	}
	
	// on Add page for Saving Data
	public void saveExternalLabTestDetails(PreservativeMasterVO _preservativeMasterVO, UserVO _userVO){
	
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		
		
		String filename = MortuaryConfig.QUERY_FILE_FOR_MORTUARY_MASTER_DAO;
		String queryKey = "INSERT.HMRT_PRESERVATIVE_MST";

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
						
								
			//populateMAP.put(sq.next(), _userVO.getHospitalCode()); @anantpatel
			populateMAP.put(sq.next(),Config.SUPER_USER_HOSPITAL_CODE);
			//populateMAP.put(sq.next(), _userVO.getHospitalCode()); @anantpatel
			populateMAP.put(sq.next(),Config.SUPER_USER_HOSPITAL_CODE);
			populateMAP.put(sq.next(), _preservativeMasterVO.getPreservativeName());			
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _userVO.getSeatId());
			

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
	public PreservativeMasterVO getExternalLabTestDetails(PreservativeMasterVO _preservativeMasterVO, UserVO _userVO)
	{
		String query = "";
		ResultSet rs;
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence(); 
		
		String filename = MortuaryConfig.QUERY_FILE_FOR_MORTUARY_MASTER_DAO;
		String queryKey = "SELECT.HMRT_PRESERVATIVE_MST";
		PreservativeMasterVO externalLabTestMstVO=new PreservativeMasterVO();
		
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
			
					
			populateMAP.put(sq.next(), _preservativeMasterVO.getPreservativeId());
			//populateMAP.put(sq.next(), _userVO.getHospitalCode()); @anantpatel
			populateMAP.put(sq.next(),Config.SUPER_USER_HOSPITAL_CODE);
			populateMAP.put(sq.next(), _preservativeMasterVO.getSerialNo());
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("BloodDonorTypeMstDAO.populateMAP::" + e);
		}
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			
			 
			if(rs.next()){
					HelperMethods.populateVOfrmRS(externalLabTestMstVO,rs);
			}else
			{
				throw new HisRecordNotFoundException("Details Not Found");
			}	
		}
		catch (Exception e)
		{	e.printStackTrace();
			if (e.getClass() == HisRecordNotFoundException.class) throw new HisRecordNotFoundException(e.getMessage());
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
	 return externalLabTestMstVO;
		
	}

	// for Updating The old Record
	public void modiftExternalLabTestDetails(PreservativeMasterVO _preservativeMasterVO, UserVO _userVO){
	
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MortuaryConfig.QUERY_FILE_FOR_MORTUARY_MASTER_DAO;
		String queryKey = "UPDATE.HMRT_PRESERVATIVE_MST";

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
			populateMAP.put(sq.next(), _userVO.getSeatId());
			populateMAP.put(sq.next(), _preservativeMasterVO.getPreservativeId());
			//populateMAP.put(sq.next(), _userVO.getHospitalCode()); @anantpatel
			populateMAP.put(sq.next(),Config.SUPER_USER_HOSPITAL_CODE);
			populateMAP.put(sq.next(), _preservativeMasterVO.getSerialNo());

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

	public void modifyInsertExternalLabTestDetails(PreservativeMasterVO _preservativeMasterVO, UserVO _userVO){ 

		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MortuaryConfig.QUERY_FILE_FOR_MORTUARY_MASTER_DAO;
		String queryKey = "MODIFY.INSERT.HMRT_PRESERVATIVE_MST";

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
			
			
			populateMAP.put(sq.next(), _preservativeMasterVO.getPreservativeId());
			//populateMAP.put(sq.next(), _userVO.getHospitalCode()); @anantpatel
			populateMAP.put(sq.next(),Config.SUPER_USER_HOSPITAL_CODE);
			populateMAP.put(sq.next(), _preservativeMasterVO.getPreservativeId());
			//populateMAP.put(sq.next(), _userVO.getHospitalCode()); @anantpatel
			populateMAP.put(sq.next(),Config.SUPER_USER_HOSPITAL_CODE);
			populateMAP.put(sq.next(), _preservativeMasterVO.getPreservativeName());			
			populateMAP.put(sq.next(), _preservativeMasterVO.getIsActive());
			populateMAP.put(sq.next(), _userVO.getSeatId());
			
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
	
	public void checkDuplicateBeforeInsert(String preservativeName, UserVO _userVO) {
		
		String query  = "";
	    Map populateMAP =new HashMap();
	    String filename = MortuaryConfig.QUERY_FILE_FOR_MORTUARY_MASTER_DAO;
	    String queryKey="DUPLICATE_CHECK.INSERT.HMRT_PRESERVATIVE_MST";
	    ResultSet rs=null;
	  
	    try{
	    	query =HelperMethodsDAO.getQuery(filename,queryKey);
	    }
	    catch(Exception e){
	    	throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);
	    }
	    
	    try{
	    	Sequence sq=new Sequence();
	    	populateMAP.put(sq.next(), preservativeName);
	    	//populateMAP.put(sq.next(), _userVO.getHospitalCode()); @anantpatel
	    	populateMAP.put(sq.next(),Config.SUPER_USER_HOSPITAL_CODE);
	    	populateMAP.put(sq.next(), Config.IS_VALID_DELETED);
	    }
	    catch(Exception e){
	    	throw new HisApplicationExecutionException("DeptMasterDAO:populateMap(_deptMasterVO,populateMAP)::"+e);
	    }
	    
	    try{
			  rs=HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(),query,populateMAP);
			 if(rs.next()){
				// record=rs.getString(1); 
				 throw new HisDuplicateRecordException("Preservative Name Already Exists");
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

	public void checkDuplicateBeforeModify(PreservativeMasterVO _preservativeMasterVO,UserVO _userVO) {
		
		String query  = "";
	    Map populateMAP =new HashMap();
	    String filename = MortuaryConfig.QUERY_FILE_FOR_MORTUARY_MASTER_DAO;
	    String queryKey="DUPLICATE_CHECK.MODIFY.HMRT_PRESERVATIVE_MST";
	    
	    ResultSet rs=null;
	       
	    try{
	    	query =HelperMethodsDAO.getQuery(filename,queryKey);
	    }
	    catch(Exception e){
	    	throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);
	    }
	    
	    try{
	    	Sequence sq=new Sequence();
	    	populateMAP.put(sq.next(), _preservativeMasterVO.getPreservativeId());
	    	populateMAP.put(sq.next(), _preservativeMasterVO.getPreservativeName());
	    	//populateMAP.put(sq.next(), _userVO.getHospitalCode()); @anantpatel
	    	populateMAP.put(sq.next(),Config.SUPER_USER_HOSPITAL_CODE);
	    	populateMAP.put(sq.next(), Config.IS_VALID_DELETED);
	    }
	    catch(Exception e){
	    	throw new HisApplicationExecutionException("DeptMasterDAO:populateMap(_deptMasterVO,populateMAP)::"+e);
	    }
	    
	    try{
			 rs=HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(),query,populateMAP);
			 if(rs.next()){
				 throw new HisDuplicateRecordException("Preservative Name Already Exists"); 
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
