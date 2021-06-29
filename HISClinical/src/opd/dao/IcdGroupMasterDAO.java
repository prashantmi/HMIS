package opd.dao;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.TransactionContext;
import hisglobal.utility.Sequence;
import hisglobal.vo.IcdGroupMasterVO;
import hisglobal.vo.UserVO;

import java.util.HashMap;
import java.util.Map;

import opd.OpdConfig;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class IcdGroupMasterDAO extends DataAccessObject {
	Logger log;
	
	public IcdGroupMasterDAO(TransactionContext _tx) {
		super(_tx);	
		log=LogManager.getLogger(this.getClass());
	}
	
public void create(IcdGroupMasterVO _groupMasterVO, UserVO _userVO){
		
		String query  = "";
        Map populateMAP =new HashMap();
        Sequence sq=new Sequence();
        String filename=OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
        String queryKey="INSERT.HGBT_ICD_GROUP_MST";
        
        
        try{
        	query =HelperMethodsDAO.getQuery(filename,queryKey);
        	
        }
        catch(Exception e){
        	throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);
        }
        log.info(query);
        
        
        try{
        	populateMAP.put(sq.next(),_groupMasterVO.getIcdGroupCode());
        	populateMAP.put(sq.next(),_groupMasterVO.getIcdGroup());
        	populateMAP.put(sq.next(),_groupMasterVO.getIsValid());
        	populateMAP.put(sq.next(),_groupMasterVO.getSeatId());
        	populateMAP.put(sq.next(),_groupMasterVO.getEntryDate());
        }
        catch(Exception e){
        	throw new HisApplicationExecutionException("IcdGroupMasterDAO.populateMAP::"+e);
        }
        try{
        	HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(),query,populateMAP);
        }
        catch(Exception e){
        	e.printStackTrace();
        	throw new HisDataAccessException("HelperMethodsDAO.getResultset"+e);
        }
        
	}





/*public void update(DepartmentMasterVO _deptMasterVO, UserVO _userVO){
	
	
	String query  = "";
   Map populateMAP =new HashMap();
   String filename=RegistrationConfig.QUERY_FILE_FOR_MASTERSDAO;
   String queryKey="UPDATE.GBLT_DEPARTMENT_MASTER";
   
   try{
       query =HelperMethodsDAO.getQuery(filename,queryKey);
       _deptMasterVO.setLastModifiedSeatID(_userVO.getSeatId());
       _deptMasterVO.setIsValid(RegistrationConfig.IS_VALID_ACTIVE);
       
       
       
    }     
   catch(Exception e){
   	throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);
   }
   log.info(query);
   
   try{    	  
	      Sequence sq=new Sequence();
	      populateMAP.put(sq.next(), _deptMasterVO.getDepartment());
	      populateMAP.put(sq.next(), _deptMasterVO.getDepartmentType());
	      populateMAP.put(sq.next(), _deptMasterVO.getEffectiveFrom());
	      populateMAP.put(sq.next(), _deptMasterVO.getDeptShortName());
	      populateMAP.put(sq.next(), _deptMasterVO.getEffectiveTo());
	      populateMAP.put(sq.next(), _deptMasterVO.getIsValid());
	      populateMAP.put(sq.next(), _deptMasterVO.getRemarks());
	      populateMAP.put(sq.next(), _deptMasterVO.getLastModifyDate());
	      populateMAP.put(sq.next(), _deptMasterVO.getHodCode());
	      populateMAP.put(sq.next(), _deptMasterVO.getLocationCode());
	      populateMAP.put(sq.next(), _deptMasterVO.getDefaultCloseDays());
	      populateMAP.put(sq.next(), _deptMasterVO.getLastModifiedSeatID());
	      populateMAP.put(sq.next(), _deptMasterVO.getDepartmentCode());
	      populateMAP.put(sq.next(), _deptMasterVO.getDepartmentSlNo());
	      
	           
   }
   catch(Exception e){
   	throw new HisApplicationExecutionException("UnknownChangeDAO:populateMap(_unknownChangeVO,populateMAP)::"+e);
   }
   try{
   	int i=HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(),query,populateMAP);
   	if(i==0){
   		throw new HisUpdateUnsuccesfullException();
   	}
   }
   catch(Exception e){
   	e.printStackTrace();
   	throw new HisUpdateUnsuccesfullException("HelperMethodsDAO.getResultset"+e);
   }     
	}


public DepartmentMasterVO getDeparmenttDetails(String code, String serialNo, UserVO _uservo) {
	
	
	DepartmentMasterVO deptMstVO=new DepartmentMasterVO();
	
	String query =  "" ;
	Map populateMap =new HashMap();
	String filename=RegistrationConfig.QUERY_FILE_FOR_MASTERSDAO;
	String queryKey="SELECT.DEPT.GBLT_DEPARTMENT_MASTER"; 	  
	
	
	
	Sequence sq=new Sequence();
	populateMap.put(sq.next(),code);	
	populateMap.put(sq.next(),serialNo);
	
	
	Connection conn =super.getTransactionContext().getConnection();
	
	try{
 	      query =HelperMethodsDAO.getQuery(filename,queryKey);
	}catch(Exception e){
		throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::"+e);
	}
	
	log.info(query);	 	 
	
	try{
		
		ResultSet rs = HelperMethodsDAO.executeQuery(conn, query, populateMap);
				
 	    if(!rs.next()){
 	    	throw new HisRecordNotFoundException();	 	    
 	    }
 	    else{
 	    	rs.beforeFirst();
 	    	
 	    	while(rs.next()){
 	    		
 	 	    	HelperMethods.populateVOfrmRS(deptMstVO,rs); 	
 	 	    	
 	    	}
 	    	
 	    }
 	    
	}
	catch(Exception e){
		if(e.getClass()==HisRecordNotFoundException.class){
			throw new HisRecordNotFoundException();	
		}
		else			 		
		 throw new HisDataAccessException("HisDataAccessException:: "+e);			 
	 }			 	 	
	return deptMstVO;

}
*/

}