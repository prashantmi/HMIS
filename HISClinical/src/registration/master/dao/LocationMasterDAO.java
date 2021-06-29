package registration.master.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import registration.RegistrationConfig;
import hisglobal.persistence.TransactionContext;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.exceptions.HisUpdateUnsuccesfullException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.Sequence;
import hisglobal.vo.DepartmentMasterVO;
import hisglobal.vo.DepartmentUnitRoomMasterVO;
import hisglobal.vo.LocationMasterVO;
import hisglobal.vo.UserVO;



public class LocationMasterDAO extends DataAccessObject {
	
	
	public LocationMasterDAO(TransactionContext _tx) {
		super(_tx);	
		
	}	
	
public void insertLocation(LocationMasterVO _locationMstVO, UserVO _userVO){
		
		String query  = "";
        Map populateMAP =new HashMap();
        String filename=RegistrationConfig.QUERY_FILE_FOR_MASTERSDAO;
        String queryKey="INSERT.HGBT_LOCATION_MST";
        
        
        try{
        	query =HelperMethodsDAO.getQuery(filename,queryKey);
        	
        	_locationMstVO.setSeatID(_userVO.getSeatId());
        	_locationMstVO.setHospitalCode(_userVO.getHospitalCode());        	
        
        	
        }
        catch(Exception e){
        	throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);
        }
        
        
        
        try{
        	populateMapAdd(_locationMstVO,populateMAP);
        }
        catch(Exception e){
        	throw new HisApplicationExecutionException("DeptMasterDAO:populateMap(_deptMasterVO,populateMAP)::"+e);
        }
        try{
        	HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(),query,populateMAP);
        }
        catch(Exception e){
        	e.printStackTrace();
        	throw new HisDataAccessException("HelperMethodsDAO.getResultset"+e);
        }
        
	}
public LocationMasterVO getModifyLocationEssential(String locationCode,String hospitalCode){
	
	String query  = "";
    Map populateMAP =new HashMap();
    String filename=RegistrationConfig.QUERY_FILE_FOR_MASTERSDAO;
    String queryKey="SELECT.HGBT_LOCATION_MST";
    
    LocationMasterVO locationMasterVO= new LocationMasterVO();
   
    ResultSet rs=null;
    
    try{
    	query =HelperMethodsDAO.getQuery(filename,queryKey);
    	
    	
    }
    catch(Exception e){
    	throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);
    }
  
    
    
    try{
    	Sequence sq=new Sequence();
    	populateMAP.put(sq.next(), locationCode);
    	populateMAP.put(sq.next(), hospitalCode);
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
		            		  HelperMethods.populateVOfrmRS(locationMasterVO,rs);
		            	   }
		            		   
		               }	   
			 
	   }
    catch(Exception e){
    	e.printStackTrace();
    	throw new HisDataAccessException("HelperMethodsDAO.getResultset"+e);
    }
  
	return locationMasterVO;
    
}
public void modifyLocation(LocationMasterVO _locationMstVO, UserVO _userVO){
	
	String query  = "";
    Map populateMAP =new HashMap();
    String filename=RegistrationConfig.QUERY_FILE_FOR_MASTERSDAO;
    String queryKey="UPDATE.HGBT_LOCATION_MST";
    
    
    try{
    	query =HelperMethodsDAO.getQuery(filename,queryKey);
    	
    	_locationMstVO.setSeatID(_userVO.getSeatId());
    	_locationMstVO.setHospitalCode(_userVO.getHospitalCode());        	
    	System.out.println("_locationMstVO inside dao "+_locationMstVO.getSeatID());
    	
    }
    catch(Exception e){
    	throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);
    }
    
    
    
    try{
    	populateMapModify(_locationMstVO,populateMAP);
    }
    catch(Exception e){
    	throw new HisApplicationExecutionException("DeptMasterDAO:populateMap(_deptMasterVO,populateMAP)::"+e);
    }
    try{
    	HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(),query,populateMAP);
    }
    catch(Exception e){
    	e.printStackTrace();
    	throw new HisDataAccessException("HelperMethodsDAO.getResultset"+e);
    }
    
}

public int duplicateCheckInsert(LocationMasterVO _locationMstVO, UserVO _userVO){
	
	String query  = "";
    Map populateMAP =new HashMap();
    String filename=RegistrationConfig.QUERY_FILE_FOR_MASTERSDAO;
    String queryKey="DUPLICATE_CHECK.INSERT.HGBT_LOCATION_MST";
    
    LocationMasterVO locationMasterVO= new LocationMasterVO();
   
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
    	populateMAP.put(sq.next(), _locationMstVO.getLocationDescription());
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
		            	   Sequence sq=new Sequence();
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

public int duplicateCheckModify(LocationMasterVO _locationMstVO, UserVO _userVO){
	
	String query  = "";
    Map populateMAP =new HashMap();
    String filename=RegistrationConfig.QUERY_FILE_FOR_MASTERSDAO;
    String queryKey="DUPLICATE_CHECK.UPDATE.HGBT_LOCATION_MST";
    
    LocationMasterVO locationMasterVO= new LocationMasterVO();
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
    	
    	populateMAP.put(sq.next(), _locationMstVO.getLocationDescription());
    	populateMAP.put(sq.next(), Config.IS_VALID_DELETED);
    	populateMAP.put(sq.next(), _userVO.getHospitalCode());
    	populateMAP.put(sq.next(), _locationMstVO.getLocationCode());
    	
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
		            	   Sequence sq=new Sequence();
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
public void populateMapAdd(LocationMasterVO _locationMstVO, Map _populateMAP){
	 Sequence sq=new Sequence();
	 
	 _populateMAP.put(sq.next(), _locationMstVO.getHospitalCode());
	 _populateMAP.put(sq.next(), _locationMstVO.getLocationDescription());
	 _populateMAP.put(sq.next(), _locationMstVO.getBuilding());
	 _populateMAP.put(sq.next(), _locationMstVO.getBlock());
	 _populateMAP.put(sq.next(), _locationMstVO.getFloor());
	 _populateMAP.put(sq.next(), _locationMstVO.getRoom());	 
	 _populateMAP.put(sq.next(), _locationMstVO.getLandMark());
	 _populateMAP.put(sq.next(), _locationMstVO.getLocationTypeCode());
	 //_populateMAP.put(sq.next(), _locationMstVO.getEffectiveDate());
	 _populateMAP.put(sq.next(), _locationMstVO.getIsValid());
	 _populateMAP.put(sq.next(), _locationMstVO.getSeatID());
	 _populateMAP.put(sq.next(), _locationMstVO.getHospitalCode());

}

public void populateMapModify(LocationMasterVO _locationMstVO, Map _populateMAP){
	 Sequence sq=new Sequence();
	 
	 _populateMAP.put(sq.next(), _locationMstVO.getLocationDescription());
	 _populateMAP.put(sq.next(), _locationMstVO.getBuilding());
	 _populateMAP.put(sq.next(), _locationMstVO.getBlock());
	 _populateMAP.put(sq.next(), _locationMstVO.getFloor());
	 _populateMAP.put(sq.next(), _locationMstVO.getRoom());	 
	 _populateMAP.put(sq.next(), _locationMstVO.getLocationTypeCode());	 
	 _populateMAP.put(sq.next(), _locationMstVO.getLandMark());
	 _populateMAP.put(sq.next(),"'"+ _locationMstVO.getEffectiveDate()+"'");
	 _populateMAP.put(sq.next(), _locationMstVO.getIsValid());	 
	 _populateMAP.put(sq.next(), _locationMstVO.getLocationCode());
	 _populateMAP.put(sq.next(), _locationMstVO.getHospitalCode());

}

}
