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
import hisglobal.vo.ChamberRackMasterVO;
import hisglobal.vo.UserVO;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import mortuary.MortuaryConfig;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

//import disaster.DisasterConfig;
//import dutyroster.DutyRosterConfig; @anant patel


public class ChamberRackMasterDAO extends DataAccessObject implements ChamberRackMasterDAOi {

	Logger log;

	public ChamberRackMasterDAO(JDBCTransactionContext _tx)
	{
		super(_tx);
		log = LogManager.getLogger(this.getClass());
	}
	
	// on Add page for Saving Data
	public void saveChamberRack(ChamberRackMasterVO _chamberRackMstVO,UserVO _userVO){
	
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		
		
		String filename = MortuaryConfig.QUERY_FILE_FOR_MORTUARY_MASTER_DAO;
		String queryKey = "INSERT.HMRT_CHAMBER_RACK_MST";

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
			populateMAP.put(sq.next(), _chamberRackMstVO.getRackName());
			populateMAP.put(sq.next(), _chamberRackMstVO.getChamberId());
			populateMAP.put(sq.next(), _chamberRackMstVO.getRackStatus());
			populateMAP.put(sq.next(), _chamberRackMstVO.getMaxCapacity());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _userVO.getSeatId());
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			

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
	public ChamberRackMasterVO getChamberRackDetails(ChamberRackMasterVO _chamberRackMstVO,UserVO _userVO)
	{
		String query = "";
		ResultSet rs;
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence(); 
		
		String filename = MortuaryConfig.QUERY_FILE_FOR_MORTUARY_MASTER_DAO;
		String queryKey = "SELECT.HMRT_CHAMBER_RACK_MST";
		ChamberRackMasterVO chamberMstVO=new ChamberRackMasterVO();
		
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
			populateMAP.put(sq.next(), _chamberRackMstVO.getChamberRackId());
			populateMAP.put(sq.next(), _chamberRackMstVO.getIsActive());
			populateMAP.put(sq.next(), _userVO.getHospitalCode()); 		
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("BloodDonorTypeMstDAO.populateMAP::" + e);
		}
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			
			 
			if(rs.next()){
					HelperMethods.populateVOfrmRS(chamberMstVO,rs);
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
	 return chamberMstVO;
		
	}

	// for Updating The old Record
	public void modifyChamberRack(ChamberRackMasterVO _chamberRackMstVO,UserVO _userVO){
	
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MortuaryConfig.QUERY_FILE_FOR_MORTUARY_MASTER_DAO;
		String queryKey = "UPDATE.HMRT_CHAMBER_RACK_MST";

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
			populateMAP.put(sq.next(), _chamberRackMstVO.getChamberRackId());
			populateMAP.put(sq.next(), _userVO.getHospitalCode()); 
			populateMAP.put(sq.next(), _chamberRackMstVO.getSerialNo());

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

	public void modifyInsertChamberRack(ChamberRackMasterVO _chamberRackMstVO,UserVO _userVO){ 

		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MortuaryConfig.QUERY_FILE_FOR_MORTUARY_MASTER_DAO;
		String queryKey = "MODIFY.INSERT.HMRT_CHAMBER_RACK_MST";

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
				
			populateMAP.put(sq.next(), _chamberRackMstVO.getChamberRackId());
			populateMAP.put(sq.next(), _chamberRackMstVO.getChamberRackId());
			populateMAP.put(sq.next(), _userVO.getHospitalCode()); 					
			populateMAP.put(sq.next(), _chamberRackMstVO.getRackName());
			populateMAP.put(sq.next(), _chamberRackMstVO.getChamberId());
			populateMAP.put(sq.next(), _chamberRackMstVO.getRackStatus());
			populateMAP.put(sq.next(), _chamberRackMstVO.getMaxCapacity());
			populateMAP.put(sq.next(), _chamberRackMstVO.getIsActive());
			populateMAP.put(sq.next(), _userVO.getSeatId());
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			
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
	
	public void checkDuplicateBeforeInsert(String rackName,String chamberId, UserVO _userVO) {
		
		String query  = "";
	    Map populateMAP =new HashMap();
	    String filename = MortuaryConfig.QUERY_FILE_FOR_MORTUARY_MASTER_DAO;
	    String queryKey="DUPLICATE_CHECK.INSERT.HMRT_CHAMBER_RACK_MST";
	    ResultSet rs=null;
	  
	    try{
	    	query =HelperMethodsDAO.getQuery(filename,queryKey);
	    }
	    catch(Exception e){
	    	throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);
	    }
	    
	    try{
	    	Sequence sq=new Sequence();
	    	populateMAP.put(sq.next(), rackName);
	    	populateMAP.put(sq.next(), _userVO.getHospitalCode());
	    	populateMAP.put(sq.next(), Config.IS_VALID_DELETED);
	    	populateMAP.put(sq.next(), chamberId);
	    
	    }
	    catch(Exception e){
	    	throw new HisApplicationExecutionException("DeptMasterDAO:populateMap(_deptMasterVO,populateMAP)::"+e);
	    }
	    
	    try{
			  rs=HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(),query,populateMAP);
			 if(rs.next()){
				// record=rs.getString(1); 
				 throw new HisDuplicateRecordException("Rack Name Already Exists");
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

	public void checkDuplicateBeforeModify(ChamberRackMasterVO _chamberRackMstVO,UserVO _userVO) {
		
		String query  = "";
	    Map populateMAP =new HashMap();
	    String filename = MortuaryConfig.QUERY_FILE_FOR_MORTUARY_MASTER_DAO;
	    String queryKey="DUPLICATE_CHECK.MODIFY.HMRT_CHAMBER_RACK_MST";
	    
	    ResultSet rs=null;
	       
	    try{
	    	query =HelperMethodsDAO.getQuery(filename,queryKey);
	    }
	    catch(Exception e){
	    	throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);
	    }
	    
	    try{
	    	Sequence sq=new Sequence();
	    	populateMAP.put(sq.next(), _chamberRackMstVO.getChamberRackId());
	    	populateMAP.put(sq.next(), _chamberRackMstVO.getRackName());
	    	populateMAP.put(sq.next(), _userVO.getHospitalCode());
	    	populateMAP.put(sq.next(), Config.IS_VALID_DELETED);
	    	populateMAP.put(sq.next(), _chamberRackMstVO.getChamberId());
	 
	    }
	    catch(Exception e){
	    	throw new HisApplicationExecutionException("DeptMasterDAO:populateMap(_deptMasterVO,populateMAP)::"+e);
	    }
	    
	    try{
			 rs=HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(),query,populateMAP);
			 if(rs.next()){
				 throw new HisDuplicateRecordException("Rack Name Already Exists"); 
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
	

	
	
	
	public void checkChamberIdPresent(String _chamberId,UserVO _userVO) {
		
		String query  = "";
	    Map populateMAP =new HashMap();
	    String filename = MortuaryConfig.QUERY_FILE_FOR_MORTUARY_MASTER_DAO;
	    String queryKey="SELECT_CHAMBER_ID.HMRT_CHAMBER_RACK_MST";
	    ResultSet rs=null;
	  
	    try{
	    	query =HelperMethodsDAO.getQuery(filename,queryKey);
	    }
	    catch(Exception e){
	    	throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);
	    }
	    
	    try{
	    	Sequence sq=new Sequence();
	    	populateMAP.put(sq.next(), _chamberId);
	    	populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
	    	populateMAP.put(sq.next(), _userVO.getHospitalCode());

	    
	    }
	    catch(Exception e){
	    	throw new HisApplicationExecutionException("DeptMasterDAO:populateMap(_deptMasterVO,populateMAP)::"+e);
	    }
	    
	    try{
			  rs=HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(),query,populateMAP);
			 if(rs.next()){
				 throw new HisDuplicateRecordException("Chamber Racks Already Exists,Please use Modify");
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
