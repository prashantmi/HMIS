package registration.master.dao;

import hisglobal.persistence.TransactionContext;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.Sequence;

import hisglobal.vo.UnitConsultantMasterVO;

import hisglobal.vo.UserVO;
import hisglobal.vo.ValueObject;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import registration.RegistrationConfig;

public class UnitConultantDAO extends DataAccessObject{
	
public UnitConultantDAO(TransactionContext _tx) {
		super(_tx);
		// TODO Auto-generated constructor stub
	}

public void create(UnitConsultantMasterVO _unitConsultantMstVO, UserVO _userVO){
		
		String query  = "";
        Map populateMAP =new HashMap();
        String filename=RegistrationConfig.QUERY_FILE_FOR_MASTERSDAO;
        String queryKey="INSERT.HGBT_UNIT_CONSULTANT_MST";        
        
        try{
        	query =HelperMethodsDAO.getQuery(filename,queryKey);
        }
        catch(Exception e){
        	throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);
        }
        System.out.println("query"+query);
        
        try{
        	System.out.println("_userVO.getSeatId()"+_userVO.getSeatId());
        	
        	_unitConsultantMstVO.setSeatID(_userVO.getSeatId());
        	_unitConsultantMstVO.setHospitalCode(_userVO.getHospitalCode());
        	populateMap(_unitConsultantMstVO,populateMAP);
        }
        catch(Exception e){
        	throw new HisDataAccessException(":populateMap(_unknownChangeVO,populateMAP)::"+e);
        }
        try{
        	HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(),query,populateMAP);
        }
        catch(Exception e){
        	throw new HisDataAccessException("HelperMethodsDAO.getResultset"+e);
        }        
	}

public void delete(UnitConsultantMasterVO _unitConsultantMstVO, UserVO _userVO){
	
	String query  = "";
    Map populateMAP =new HashMap();
    String filename=RegistrationConfig.QUERY_FILE_FOR_MASTERSDAO;
    String queryKey="DELETE.HGBT_UNIT_CONSULTANT_MST";        
    
    try{
    	query =HelperMethodsDAO.getQuery(filename,queryKey);
    }
    catch(Exception e){
    	throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);
    }
    System.out.println("query"+query);
    
    try{
    	System.out.println("_userVO.getSeatId()"+_userVO.getSeatId());
    	
    	Sequence sq=new Sequence();   	
     	populateMAP.put(sq.next(), _unitConsultantMstVO.getDepartmentUnitCode());    	
     	populateMAP.put(sq.next(), _unitConsultantMstVO.getEmpNo()); 
     	populateMAP.put(sq.next(),_userVO.getHospitalCode());
    }
    catch(Exception e){
    	throw new HisApplicationExecutionException("UnknownChangeDAO:populateMap(_unknownChangeVO,populateMAP)::"+e);
    }
    try{
    	int i=HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(),query,populateMAP);
    	if(i==0)
    		throw new HisDataAccessException("HelperMethodsDAO.getResultset");   		
    }
    catch(Exception e){
    	throw new HisDataAccessException("HelperMethodsDAO.getResultset"+e);
    }   
}

public Collection getDeletedOrUpdatedRecords(UserVO _userVO){
	
	String query  = "";
    Map populateMAP =new HashMap();
    String filename=RegistrationConfig.QUERY_FILE_FOR_MASTERSDAO;
    String queryKey="SELECT.CONSULTANT.DELETED.HGBT_UNIT_CONSULTANT_MST"; 
    Collection col=new ArrayList();    
    
    try{
    	query =HelperMethodsDAO.getQuery(filename,queryKey);
    }
    catch(Exception e){
    	throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);
    }
    System.out.println("query"+query);
    
    try{  	
    	Sequence sq=new Sequence();
    	populateMAP.put(sq.next(),Config.IS_VALID_DELETED);
    	populateMAP.put(sq.next(),Config.IS_VALID_INACTIVE);  
    	populateMAP.put(sq.next(),_userVO.getHospitalCode());
    	
    }
    catch(Exception e){
    	throw new HisApplicationExecutionException("UnknownChangeDAO:populateMap(_unknownChangeVO,populateMAP)::"+e);
    }
    try{
    	ResultSet rs=HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(),query,populateMAP);
    	while(rs.next()){
      	  String str=rs.getString(1);
      	  String str1=rs.getString(2);
      	  col.add(new UnitConsultantMasterVO(str,str1));    		
    	}
    }
    catch(Exception e){
    	throw new HisDataAccessException("HelperMethodsDAO.getResultset"+e);
    }
	return col;    
}
	
	/**
	 * Populates the map with the unknown change details to be entered in the DB.
	 * @param	_unknownChangeVO	Provides unknown change details to be entered.
	 * @param	_populateMAP	Map containig values which will be used for insert query.
	 */

    public void populateMap(UnitConsultantMasterVO _unitConsultantMstVO, Map _populateMAP)
    {
    	 Sequence sq=new Sequence();   	
    	_populateMAP.put(sq.next(), _unitConsultantMstVO.getDepartmentUnitCode());    	
    	_populateMAP.put(sq.next(), _unitConsultantMstVO.getEmpNo());   	
    	
    	_populateMAP.put(sq.next(), _unitConsultantMstVO.getSeatID());    	
    	_populateMAP.put(sq.next(), _unitConsultantMstVO.getLocationCode());
    	_populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
    	_populateMAP.put(sq.next(), _unitConsultantMstVO.getIsHOU()); 
    	_populateMAP.put(sq.next(),_unitConsultantMstVO.getHospitalCode());
    	_populateMAP.put(sq.next(), _unitConsultantMstVO.getDepartmentUnitCode());    	
    	_populateMAP.put(sq.next(), _unitConsultantMstVO.getEmpNo());   
    	_populateMAP.put(sq.next(),_unitConsultantMstVO.getHospitalCode());
    	_populateMAP.put(sq.next(),_unitConsultantMstVO.getHierarchyLevel());
    }
    
    public UnitConsultantMasterVO[] getConsultantsForAUnit(String _deptUnitCode, UserVO _userVO){    	
    	
    	UnitConsultantMasterVO unitConsultantMasterVO[];
    	ValueObject[] vo= {};
		String query =  "" ;
		Map populateMap =new HashMap();
		String filename=RegistrationConfig.QUERY_FILE_FOR_MASTERSDAO;
		String queryKey="SELECT.CONSULTANT.UNIT.HGBT_UNIT_CONSULTANT_MST"; 	  
		
		Sequence sq=new Sequence();
		populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
		populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
		//populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
		populateMap.put(sq.next(),_deptUnitCode);	
		populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);	
		populateMap.put(sq.next(),_userVO.getHospitalCode());
		Connection conn =super.getTransactionContext().getConnection();
    	
    	try{
	 	      query =HelperMethodsDAO.getQuery(filename,queryKey);
    	}catch(Exception e){
    		throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::"+e);
    	}
    	
    	System.out.println("query"+query);		 	 
	 	
		try{
			ResultSet rs = HelperMethodsDAO.executeQuery(conn, query,populateMap);	 
	 	    if(!rs.next()){
	 	    	throw new HisRecordNotFoundException("No Consultant Found");	 	    
	 	    }
	 	    else{
	 	    	rs.beforeFirst();
	 	    	vo=HelperMethods.populateVOfrmRS(UnitConsultantMasterVO.class,rs);
	 	    	unitConsultantMasterVO =new UnitConsultantMasterVO[vo.length];
	 	    	 for(int i=0;i<vo.length;i++){					
	 	    		unitConsultantMasterVO[i]=(UnitConsultantMasterVO)vo[i];					
				 }		
	 	    }
	 	    System.out.println("after populating UnitConsultantMasterVO");
		}
		catch(Exception e){
			if(e.getClass()==HisRecordNotFoundException.class){
				throw new HisRecordNotFoundException(e.getMessage());	
			}
			else			 		
			 throw new HisDataAccessException("UnitConsulkatantDAO:getConsultantsForAUnit:: "+e);			 
		 }			 	 	
    	return unitConsultantMasterVO;   	
    }
    public List getEmployeeAsConsultant(String designationMappingProcessId,UserVO _userVO){
    	 ResultSet rs=null;    		   	
	   	  String query =  "" ;
	   	  Map populateMAP =new HashMap();    		 	  	
	 	  String filename=RegistrationConfig.QUERY_FILE_FOR_MASTERSDAO;
	 	  String queryKey="SELECT.ISCONSULTANT.PSRT_EMPLOYEE_MST";
	 	  
	 	  //first call the getQueryMethod with arguments filename,querykey from prop file    		 	  
	 	  try{
	 	      query =HelperMethodsDAO.getQuery(filename,queryKey);   		 	 
	 	     }
	 	  catch(Exception e)
	 	  {	
	 		 throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);	 		  
	 	  }    		 	  
	 	  System.out.println("query"+query);	
	 	  Sequence sq=new Sequence();	 	
	 	  	
	 	  populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
	 	 // populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
	 	 // populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
	 	  populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
	 	  populateMAP.put(sq.next(),designationMappingProcessId);
	 	  populateMAP.put(sq.next(),_userVO.getHospitalCode());
	 //	  populateMAP.put(sq.next(),unitCode);
	 	
	 	  
	 	  try{
	 	  rs=HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(),query,populateMAP);
	 	 if(!rs.next()){
			 //throw new HisRecordNotFoundException("No Consultant Record Found");
		 }
			 
	     }catch(Exception e){
		 if(e.getClass()==HisRecordNotFoundException.class){
				throw new HisRecordNotFoundException(e.getMessage());	
			}
			else
		 throw new HisDataAccessException("HelperMethodsDAO.getResultset"+e);
	    }	 	 
	 	    		 	      		 	  
	 	  List alRecord = new ArrayList();    		 	  
	 	  try{
	   	  alRecord= HelperMethodsDAO.getAlOfEntryObjects(rs);
	 	  }
	 	  catch(Exception e)
	 	  {
	 		throw new HisDataAccessException("CountryDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)"+e);    		 		 
	 	  }
	   	  return alRecord;  	
    }  
    public void updateHOU(UnitConsultantMasterVO _unitConsultantMstVO, UserVO _userVO) {
    	String query  = "";
        Map populateMAP =new HashMap();
        String filename=RegistrationConfig.QUERY_FILE_FOR_MASTERSDAO;
        String queryKey="UPDATE.HOU.HGBT_UNIT_CONSULTANT_MST";        
        
        try{
        	query =HelperMethodsDAO.getQuery(filename,queryKey);
        }
        catch(Exception e){
        	throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);
        }
        System.out.println("query"+query);
        
        try{
        	Sequence sq= new Sequence();        	
        	populateMAP.put(sq.next(), _unitConsultantMstVO.getIsHOU());
        	populateMAP.put(sq.next(), _unitConsultantMstVO.getHierarchyLevel());
        	populateMAP.put(sq.next(), _unitConsultantMstVO.getDepartmentUnitCode());    	
        	populateMAP.put(sq.next(), _unitConsultantMstVO.getEmpNo());  
        	populateMAP.put(sq.next(),_userVO.getHospitalCode());
        	populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
        }
        catch(Exception e){
        	e.printStackTrace();
        	throw new HisDataAccessException("populateMap(_unknownChangeVO,populateMAP)::"+e);
        }
        try{
        	int i=HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(),query,populateMAP);
        	if(i==0){
        		throw new HisDataAccessException("HelperMethodsDAO.getResultset");
        	}
        }
        catch(Exception e){
        	throw new HisDataAccessException("HelperMethodsDAO.getResultset"+e);
        }
    }
    
    
    public void removeConsultant(UnitConsultantMasterVO _unitConsultantMstVO, UserVO _userVO) {
    	String query  = "";
        Map populateMAP =new HashMap();
        String filename=RegistrationConfig.QUERY_FILE_FOR_MASTERSDAO;
        String queryKey="UPDATE.REMOVE.HGBT_UNIT_CONSULTANT_MST";        
        
        try{
        	query =HelperMethodsDAO.getQuery(filename,queryKey);
        }
        catch(Exception e){
        	throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);
        }
        System.out.println("query"+query);
        
        try{
        	Sequence sq= new Sequence();     
        	populateMAP.put(sq.next(), Config.IS_VALID_DELETED);
        	populateMAP.put(sq.next(), _unitConsultantMstVO.getDepartmentUnitCode());    	
        	populateMAP.put(sq.next(), _unitConsultantMstVO.getEmpNo());
        	populateMAP.put(sq.next(),_userVO.getHospitalCode());
        }
        catch(Exception e){
        	throw new HisDataAccessException("UnknownChangeDAO:populateMap(_unknownChangeVO,populateMAP)::"+e);
        }
        try{
        	HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(),query,populateMAP);
        }
        catch(Exception e){
        	throw new HisDataAccessException("HelperMethodsDAO.getResultset"+e);
        }
    }    
    
    public void updatePreviousRecord(UnitConsultantMasterVO _unitConsultantMstVO, UserVO _userVO)
    {
    	String query  = "";
        Map populateMAP =new HashMap();
        String filename=RegistrationConfig.QUERY_FILE_FOR_MASTERSDAO;
        String queryKey="UPDATE.PREVIOUS_ROW.HGBT_UNIT_CONSULTANT_MST";        
        
        try{
        	query =HelperMethodsDAO.getQuery(filename,queryKey);
        }
        catch(Exception e){
        	throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);
        }
        System.out.println("query"+query);
        
        try{
        	Sequence sq= new Sequence();        	
        	
        	populateMAP.put(sq.next(), Config.IS_VALID_DELETED);
        	populateMAP.put(sq.next(), _unitConsultantMstVO.getDepartmentUnitCode());    	
        	
        	populateMAP.put(sq.next(),_userVO.getHospitalCode());
        }
        catch(Exception e){
        	e.printStackTrace();
        	throw new HisDataAccessException("populateMap(_unknownChangeVO,populateMAP)::"+e);
        }
        try{
        	int i=HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(),query,populateMAP);
        	if(i==0){
        		throw new HisDataAccessException("HelperMethodsDAO.getResultset");
        	}
        }
        catch(Exception e){
        	throw new HisDataAccessException("HelperMethodsDAO.getResultset"+e);
        }
    }
    
}//end of class
