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
import hisglobal.vo.BloodDonorTypeMasterVO;
import hisglobal.vo.LocationMasterVO;
import hisglobal.vo.RosterCategoryMstVO;
import hisglobal.vo.UserVO;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import registration.RegistrationConfig;

import dutyroster.DutyRosterConfig;


import dutyroster.masters.dao.RosterCategoryMstDAOi;

public class RosterCategoryMstDAO extends DataAccessObject implements RosterCategoryMstDAOi {

	Logger log;

	public RosterCategoryMstDAO(JDBCTransactionContext _tx)
	{
		super(_tx);
		log = LogManager.getLogger(this.getClass());
	}

	// on Add page for Saving Data
	public void create(RosterCategoryMstVO _rosterCatMstVO, UserVO _UserVO) 
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = DutyRosterConfig.QUERY_FILE_FOR_DUTY_ROSTER_MASTERSDAO;
		String queryKey = "INSERT.HDRT_ROSTER_CAT_MST_NEW";

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
			populateMAP.put(sq.next(), _UserVO.getHospitalCode());
			populateMAP.put(sq.next(), _UserVO.getHospitalCode()); 			
			populateMAP.put(sq.next(), _rosterCatMstVO.getRosterCategoryName());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _UserVO.getSeatId());
			populateMAP.put(sq.next(), _rosterCatMstVO.getRosterMainCategoryCode());
			populateMAP.put(sq.next(), _rosterCatMstVO.getRosterMode());
			populateMAP.put(sq.next(), _rosterCatMstVO.getNoOfHours());
			populateMAP.put(sq.next(), _rosterCatMstVO.getMaxOff());
			populateMAP.put(sq.next(), _rosterCatMstVO.getMaxContinuousOff());
			populateMAP.put(sq.next(), _rosterCatMstVO.getDutyOffFlag());
			populateMAP.put(sq.next(), _rosterCatMstVO.getDutyOffAccount());
		
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
	public RosterCategoryMstVO fetch(RosterCategoryMstVO _rosterCatMstVO, UserVO _UserVO)
	{
		String query = "";
		ResultSet rs;
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence(); 
		RosterCategoryMstVO rostCatMstVO=new RosterCategoryMstVO(); 
		
		String filename = DutyRosterConfig.QUERY_FILE_FOR_DUTY_ROSTER_MASTERSDAO;
		String queryKey = "SELECT.HDRT_ROSTER_CAT_MST";

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
			populateMAP.put(sq.next(), _rosterCatMstVO.getRosterCategoryCode());
			populateMAP.put(sq.next(), _UserVO.getHospitalCode()); 		
			populateMAP.put(sq.next(), _rosterCatMstVO.getSerialNo());
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("BloodDonorTypeMstDAO.populateMAP::" + e);
		}
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			
			if(!rs.next()){
				 throw new HisRecordNotFoundException("Details Not Found");
			               }else
			               {
			            	   rs.beforeFirst();
			            	   while(rs.next())
			            	   {
			            		  HelperMethods.populateVOfrmRS(rostCatMstVO,rs);
			            	   }
			            		   
			               }	
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class) throw new HisRecordNotFoundException(e.getMessage());
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
	 return rostCatMstVO;
		
	}

	// for Updating The old Record
	public void update(String sRosterId,String sRosterSlno, UserVO _UserVO)
	{

		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = DutyRosterConfig.QUERY_FILE_FOR_DUTY_ROSTER_MASTERSDAO;
		String queryKey = "UPDATE.HDRT_ROSTER_CAT_MST";

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
			populateMAP.put(sq.next(), _UserVO.getSeatId());
			populateMAP.put(sq.next(), Config.IS_VALID_DELETED);
			populateMAP.put(sq.next(), sRosterId);
			populateMAP.put(sq.next(), _UserVO.getHospitalCode()); 
			populateMAP.put(sq.next(), sRosterSlno);

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

	public void createwhileUpdate(String sRosterSlno,RosterCategoryMstVO _rosterCatMstVO, UserVO _UserVO) 
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = DutyRosterConfig.QUERY_FILE_FOR_DUTY_ROSTER_MASTERSDAO;
		String queryKey = "INSERT.HDRT_ROSTER_CAT_MST_MODIFY";

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
			int sSlno=0;
		if(sRosterSlno!=null)
			  sSlno=Integer.parseInt(sRosterSlno);
			sSlno=sSlno+1;
			sRosterSlno=Integer.toString(sSlno);	
			
			populateMAP.put(sq.next(), _rosterCatMstVO.getRosterCategoryCode());
			populateMAP.put(sq.next(), _UserVO.getHospitalCode()); 			
			populateMAP.put(sq.next(), sRosterSlno);
			populateMAP.put(sq.next(), _rosterCatMstVO.getRosterCategoryName());
			populateMAP.put(sq.next(), _rosterCatMstVO.getIsValid());
			populateMAP.put(sq.next(), _UserVO.getSeatId());
			populateMAP.put(sq.next(), _rosterCatMstVO.getRosterMainCategoryCode());
			populateMAP.put(sq.next(), _rosterCatMstVO.getRosterMode());
			populateMAP.put(sq.next(), _rosterCatMstVO.getNoOfHours());
			populateMAP.put(sq.next(), _rosterCatMstVO.getMaxOff());
			populateMAP.put(sq.next(), _rosterCatMstVO.getMaxContinuousOff());
			populateMAP.put(sq.next(), _rosterCatMstVO.getDutyOffFlag());
			populateMAP.put(sq.next(), _rosterCatMstVO.getDutyOffAccount());

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
	
	public int duplicateCheckInsert(RosterCategoryMstVO _rosterCatMstVO, UserVO _userVO){
		
		String query  = "";
	    Map populateMAP =new HashMap();
	    String filename = DutyRosterConfig.QUERY_FILE_FOR_DUTY_ROSTER_MASTERSDAO;
	    String queryKey="DUPLICATE_CHECK.INSERT.HDRT_ROSTER_CAT_MST";
	    
	     
	    ResultSet rs=null;
	    int duplicateCheck=0;
	    
	    try{
	    	query =HelperMethodsDAO.getQuery(filename,queryKey);
	    	
	    	
	    }
	    catch(Exception e){
	    	throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);
	    }
	  
	    
	    
	    try{
	    	Sequence sq=new Sequence();
	    	populateMAP.put(sq.next(), _rosterCatMstVO.getRosterCategoryName());
	    	populateMAP.put(sq.next(), Config.IS_VALID_DELETED);
	    	populateMAP.put(sq.next(), _userVO.getHospitalCode());
	    }
	    catch(Exception e){
	    	throw new HisApplicationExecutionException("DeptMasterDAO:populateMap(_deptMasterVO,populateMAP)::"+e);
	    }
	    
	    try{
			  rs=HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(),query,populateMAP);
			 if(!rs.next()){
				 throw new HisRecordNotFoundException("Details Not Found");
			               }else
			               {
			             	   rs.beforeFirst();
			            	   while(rs.next())
			            	   {
			            		   duplicateCheck=rs.getInt(1);
			            	   }
			            		   
			               }	   
				 
		   }
	    catch(Exception e){
	    	e.printStackTrace();
	    	throw new HisDataAccessException("HelperMethodsDAO.getResultset"+e);
	    }
	  
		return duplicateCheck;
	    
	}

	public int duplicateCheckModify(RosterCategoryMstVO _rosterCatMstVO, UserVO _userVO)
	{
		String query  = "";
	    Map populateMAP =new HashMap();
	    String filename = DutyRosterConfig.QUERY_FILE_FOR_DUTY_ROSTER_MASTERSDAO;
	    String queryKey="DUPLICATE_CHECK.UPDATE.HDRT_ROSTER_CAT_MST";
	    int duplicateCheck=0;
	    ResultSet rs=null;
	    try{
	    	query =HelperMethodsDAO.getQuery(filename,queryKey);
	    }
	    catch(Exception e){
	    	throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);
	    }
	    try{
	    	Sequence sq=new Sequence();
	    	populateMAP.put(sq.next(), _rosterCatMstVO.getRosterCategoryName());
	    	populateMAP.put(sq.next(), Config.IS_VALID_DELETED);
	    	populateMAP.put(sq.next(), _userVO.getHospitalCode());
	    	populateMAP.put(sq.next(), _rosterCatMstVO.getRosterCategoryCode());
	   
			  rs=HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(),query,populateMAP);
			 if(!rs.next())
			 {
				 throw new HisRecordNotFoundException("Details Not Found");
			   }
			 else
			     {
			        	   rs.beforeFirst();
			            	  rs.next();
			            		   duplicateCheck=rs.getInt(1);
			      }	   
				 
		   }
	    catch(Exception e){
	    	e.printStackTrace();
	    	throw new HisDataAccessException("HelperMethodsDAO.getResultset"+e);
	    }
	  
		return duplicateCheck;
	    
	}
	
}
