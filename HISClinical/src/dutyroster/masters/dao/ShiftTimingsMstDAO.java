
package dutyroster.masters.dao;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.JDBCTransactionContext;
import hisglobal.utility.HelperMethods; 
import hisglobal.utility.Sequence;
import hisglobal.vo.DutyRosterPrintPropertiesVO;
import hisglobal.vo.DutyRosterShiftTimingsMasterVO;
import hisglobal.vo.UserVO;
import hisglobal.vo.ValueObject;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import registration.RegistrationConfig;

import dutyroster.DutyRosterConfig;


import dutyroster.masters.dao.ShiftMstDAOi;

public class ShiftTimingsMstDAO extends DataAccessObject implements ShiftTimingsMstDAOi{
	
	Logger log;

	public ShiftTimingsMstDAO(JDBCTransactionContext _tx)
	{
		super(_tx);
		log = LogManager.getLogger(this.getClass());
	}

	
	
	
	
	// on Add page for Saving Data
	public void create(DutyRosterShiftTimingsMasterVO _shiftTimingsMstVO, UserVO _UserVO) 
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = DutyRosterConfig.QUERY_FILE_FOR_DUTY_ROSTER_MASTERSDAO;
		String queryKey = "INSERT.HDRT_SHIFTTIMING_MST_NEW";

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
			populateMAP.put(sq.next(), _shiftTimingsMstVO.getWeekDayCode());
			populateMAP.put(sq.next(), _UserVO.getHospitalCode()); 			
			populateMAP.put(sq.next(), _shiftTimingsMstVO.getWeekDayCode());
			populateMAP.put(sq.next(), _shiftTimingsMstVO.getShiftStartTime());
			populateMAP.put(sq.next(), _shiftTimingsMstVO.getShiftEndTime());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _UserVO.getSeatId());
			
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
	public DutyRosterShiftTimingsMasterVO[] fetch(String _shiftCode, UserVO _userVO)
	{
		ResultSet rs;
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = DutyRosterConfig.QUERY_FILE_FOR_DUTY_ROSTER_MASTERSDAO;
		String queryKey = "SELECT.HDRT_SHIFTTIMING_MST";
		DutyRosterShiftTimingsMasterVO[]  _shiftTimeVO=null;
		ValueObject[] _valueObjects=null;
		
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
			populateMAP.put(sq.next(), _shiftCode);
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("BlockAreaMstDAO.populateMAP::" + e);
		}
		try
		{
			rs=HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
		
		if(!rs.next()){
				throw new HisRecordNotFoundException("No Record Found");
			}
			else{
				rs.beforeFirst();
				_valueObjects=HelperMethods.populateVOfrmRS(DutyRosterShiftTimingsMasterVO.class,rs);
				
				_shiftTimeVO=new DutyRosterShiftTimingsMasterVO[_valueObjects.length];
				
				for(int i= 0 ;i < _valueObjects.length ; i++)
					_shiftTimeVO[i]=(DutyRosterShiftTimingsMasterVO)_valueObjects[i];
				
				
				
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
		return _shiftTimeVO;
		
	}

	// for Updating The old Record
	public void update(String shiftCode,String sSlno, UserVO _UserVO)
	{

		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = DutyRosterConfig.QUERY_FILE_FOR_DUTY_ROSTER_MASTERSDAO;
		String queryKey = "UPDATE.HDRT_SHIFTTIMING_MST";

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
			populateMAP.put(sq.next(), _UserVO.getSeatId());			
			populateMAP.put(sq.next(), shiftCode);
			populateMAP.put(sq.next(), _UserVO.getHospitalCode()); 
			populateMAP.put(sq.next(), sSlno);

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

	public void createwhileUpdate(String sSlno,DutyRosterShiftTimingsMasterVO _shiftTimingsMstVO, UserVO _UserVO) 
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = DutyRosterConfig.QUERY_FILE_FOR_DUTY_ROSTER_MASTERSDAO;
		String queryKey = "INSERT.HDRT_SHIFTTIMING_MST_MODIFY";

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
			//increasing the serial No. by 1 
			
			int intSlno=0;
		if(sSlno!=null)
			intSlno=Integer.parseInt(sSlno);
			intSlno=intSlno+1;
			sSlno=Integer.toString(intSlno);	
			
			 
			
			populateMAP.put(sq.next(), _shiftTimingsMstVO.getShiftCode());
			populateMAP.put(sq.next(), _UserVO.getHospitalCode()); 			
			populateMAP.put(sq.next(), sSlno);
			populateMAP.put(sq.next(), _shiftTimingsMstVO.getWeekDayCode());
			populateMAP.put(sq.next(), _shiftTimingsMstVO.getShiftStartTime());
			populateMAP.put(sq.next(), _shiftTimingsMstVO.getShiftEndTime());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _UserVO.getSeatId());
			
			

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
	
	public int duplicateCheckForShiftDescriptionWhileInsert(DutyRosterShiftTimingsMasterVO _shiftTimingsMstVO, UserVO _userVO){
		
		String query  = "";
	    Map populateMAP =new HashMap();
	    String filename = DutyRosterConfig.QUERY_FILE_FOR_DUTY_ROSTER_MASTERSDAO;
	    String queryKey="DUPLICATE_CHECK.HDTSTR_SHIFT_DESC.INSERT.HDRT_SHIFT_MST";
	    
	     
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
	   // 	populateMAP.put(sq.next(), _shiftTimingsMstVO.getShiftDescription());
	    	populateMAP.put(sq.next(), Config.IS_VALID_DELETED);
	    	populateMAP.put(sq.next(), _userVO.getHospitalCode());
	    }
	    catch(Exception e){
	    	throw new HisApplicationExecutionException("DeptMasterDAO:populateMap(_deptMasterVO,populateMAP)::"+e);
	    }
	    
	    try{
			  rs=HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(),query,populateMAP);
			 if(!rs.next()){
			//	 throw new HisRecordNotFoundException("Details Not Found");
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

	public int duplicateCheckForShiftDescriptionWhileModify(DutyRosterShiftTimingsMasterVO _shiftTimingsMstVO, UserVO _userVO)
	{
		String query  = "";
	    Map populateMAP =new HashMap();
	    String filename = DutyRosterConfig.QUERY_FILE_FOR_DUTY_ROSTER_MASTERSDAO;
	    String queryKey="DUPLICATE_CHECK.HDTSTR_SHIFT_DESC.UPDATE.HDRT_SHIFT_MST";
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
	    	
	   // 	populateMAP.put(sq.next(), _shiftTimingsMstVO.getShiftDescription());
	    	populateMAP.put(sq.next(), Config.IS_VALID_DELETED);
	    	populateMAP.put(sq.next(), _userVO.getHospitalCode());
	    	populateMAP.put(sq.next(), _shiftTimingsMstVO.getShiftCode());
	    	
			  rs=HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(),query,populateMAP);
			 if(!rs.next())
			 {
			//	 throw new HisRecordNotFoundException("Details Not Found");
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

public int duplicateCheckForShiftShortNameWhileInsert(DutyRosterShiftTimingsMasterVO _shiftTimingsMstVO, UserVO _userVO){
		
		String query  = "";
	    Map populateMAP =new HashMap();
	    String filename = DutyRosterConfig.QUERY_FILE_FOR_DUTY_ROSTER_MASTERSDAO;
	    String queryKey="DUPLICATE_CHECK.HDTSTR_SHIFT_SHORTNAME.INSERT.HDRT_SHIFT_MST";
	    
	     
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
	  //  	populateMAP.put(sq.next(), _shiftTimingsMstVO.getShiftShortName());
	    	populateMAP.put(sq.next(), Config.IS_VALID_DELETED);
	    	populateMAP.put(sq.next(), _userVO.getHospitalCode());
	    }
	    catch(Exception e){
	    	throw new HisApplicationExecutionException("DeptMasterDAO:populateMap(_deptMasterVO,populateMAP)::"+e);
	    }
	    
	    try{
			  rs=HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(),query,populateMAP);
			 if(!rs.next()){
			//	 throw new HisRecordNotFoundException("Details Not Found");
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

	public int duplicateCheckForShiftShortNameWhileModify(DutyRosterShiftTimingsMasterVO _shiftTimingsMstVO, UserVO _userVO)
	{
		String query  = "";
	    Map populateMAP =new HashMap();
	    String filename = DutyRosterConfig.QUERY_FILE_FOR_DUTY_ROSTER_MASTERSDAO;
	    String queryKey="DUPLICATE_CHECK.HDTSTR_SHIFT_SHORTNAME.UPDATE.HDRT_SHIFT_MST";
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
	    	
	  //  	populateMAP.put(sq.next(), _shiftTimingsMstVO.getShiftShortName());
	    	populateMAP.put(sq.next(), Config.IS_VALID_DELETED);
	    	populateMAP.put(sq.next(), _userVO.getHospitalCode());
	    	populateMAP.put(sq.next(), _shiftTimingsMstVO.getShiftCode());
	    	
			  rs=HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(),query,populateMAP);
			 if(!rs.next())
			 {
				// throw new HisRecordNotFoundException("Details Not Found");
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
