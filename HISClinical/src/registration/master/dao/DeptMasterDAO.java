package registration.master.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Types;
import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//import oracle.jdbc.driver.OracleTypes;

import registration.RegistrationConfig;
import registration.dao.RegistrationDaoConfig;
import hisglobal.persistence.TransactionContext;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.exceptions.HisUpdateUnsuccesfullException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.Procedure;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.Sequence;
import hisglobal.vo.DepartmentMasterVO;
import hisglobal.vo.UserVO;

public class DeptMasterDAO extends DataAccessObject{

	public DeptMasterDAO(TransactionContext _tx) {
		super(_tx);
		
	}
	
public void create(DepartmentMasterVO _deptMasterVO, UserVO _userVO){
		
		String query  = "";
        Map populateMAP =new HashMap();
        String filename=RegistrationConfig.QUERY_FILE_FOR_MASTERSDAO;
        String queryKey="INSERT.GBLT_DEPARTMENT_MASTER";
        
        
        try{
        	query =HelperMethodsDAO.getQuery(filename,queryKey);
        	_deptMasterVO.setSeatID(_userVO.getSeatId());
        	_deptMasterVO.setIsValid(Config.IS_VALID_ACTIVE);
        	
        }
        catch(Exception e){
        	throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);
        }
        System.out.println("query"+query);
        
        try{
        	populateMap(_deptMasterVO,populateMAP);
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


public void populateMap(DepartmentMasterVO _deptMasterVO, Map _populateMAP){
	 Sequence sq=new Sequence();
	_populateMAP.put(sq.next(), _deptMasterVO.getDepartment());
	_populateMAP.put(sq.next(), _deptMasterVO.getHodCode());
	_populateMAP.put(sq.next(), _deptMasterVO.getDeptShortName());	
	_populateMAP.put(sq.next(), _deptMasterVO.getLocationCode());	
	_populateMAP.put(sq.next(), _deptMasterVO.getEffectiveFrom());
	_populateMAP.put(sq.next(), _deptMasterVO.getEffectiveTo());
	_populateMAP.put(sq.next(), _deptMasterVO.getIsValid() );
	_populateMAP.put(sq.next(), _deptMasterVO.getSeatID());	
	_populateMAP.put(sq.next(), _deptMasterVO.getEntryDate());
	_populateMAP.put(sq.next(), _deptMasterVO.getHl7Code());	
	_populateMAP.put(sq.next(), _deptMasterVO.getDeptShortName());	
	_populateMAP.put(sq.next(), _deptMasterVO.getRemarks());
	
}

public void update(DepartmentMasterVO _deptMasterVO, UserVO _userVO){
	
	
	String query  = "";
   Map populateMAP =new HashMap();
   String filename=RegistrationConfig.QUERY_FILE_FOR_MASTERSDAO;
   String queryKey="UPDATE.GBLT_DEPT_MASTER";
   
   try{
       query =HelperMethodsDAO.getQuery(filename,queryKey);
       _deptMasterVO.setSeatID(_userVO.getSeatId());
       
       
       
    }     
   catch(Exception e){
   	throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);
   }
   System.out.println("query"+query);
   
   try{    	  
	      Sequence sq=new Sequence();
	      populateMAP.put(sq.next(), _deptMasterVO.getDepartment());
	      populateMAP.put(sq.next(), _deptMasterVO.getDepartmentType());
	      populateMAP.put(sq.next(), _deptMasterVO.getEffectiveFrom());
	      populateMAP.put(sq.next(), _deptMasterVO.getDeptShortName());
	      populateMAP.put(sq.next(), _deptMasterVO.getEffectiveTo());
	      populateMAP.put(sq.next(), _deptMasterVO.getIsValid());
	      populateMAP.put(sq.next(), _deptMasterVO.getRemarks());
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
	String queryKey="SELECT.DEPT.GBLT_DEPTHIST_MASTER"; 	  
	/*Integer serialNoInt=new Integer(serialNo);
	int serialNoValue=serialNoInt.intValue();*/
	
	
	Sequence sq=new Sequence();
	populateMap.put(sq.next(),code);	
	populateMap.put(sq.next(),serialNo);
	
	
	Connection conn =super.getTransactionContext().getConnection();
	
	try{
 	      query =HelperMethodsDAO.getQuery(filename,queryKey);
	}catch(Exception e){
		throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::"+e);
	}
	
	System.out.println("query"+query);		 	 
	
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
 	    System.out.println("after populating deptMstVO");
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

/*public DepartmentMasterVO getPreviousDepartmentDetails(String code,String serialNo, UserVO _uservo) {
	DepartmentMasterVO deptMstVO =new DepartmentMasterVO();
	
	String query =  "" ;
	Map populateMap =new HashMap();
	String filename=RegistrationConfig.QUERY_FILE_FOR_MASTERSDAO;
	String queryKey="SELECT.PREVIOUS_DEPT.GBLT_DEPTHIST_MASTER"; 	  
	String queryKey="SELECT.DEPT.GBLT_DEPTHIST_MASTER"; 
	
	Sequence sq=new Sequence();
	populateMap.put(sq.next(),code);
	populateMap.put(sq.next(),serialNo);
	
	
	Connection conn =super.getTransactionContext().getConnection();
	
	try{
 	      query =HelperMethodsDAO.getQuery(filename,queryKey);
	}catch(Exception e){
		throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::"+e);
	}
	
	System.out.println("query"+query);		 	 
 	
	try{
		ResultSet rs = HelperMethodsDAO.executeQuery(conn, query, populateMap);	 
 	    if(!rs.next()){
 	    	throw new HisRecordNotFoundException();	 	    
 	    }
 	    else{
 	    	rs.beforeFirst();
 	    	
 	    	HelperMethods.populateVOfrmRS(deptMstVO,rs); 	    	
 	    }
 	    System.out.println("after populating deptMstVO");
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

public void createHistory(DepartmentMasterVO _deptMasterVO, UserVO _userVO){
	
	String query  = "";
	   Map populateMAP =new HashMap();
	   String filename=RegistrationConfig.QUERY_FILE_FOR_MASTERSDAO;
	   String queryKey="UPDATE.HISTORY.GBLT_DEPT_MASTER";
	   
	   try{
	       query =HelperMethodsDAO.getQuery(filename,queryKey);
	       _deptMasterVO.setSeatID(_userVO.getSeatId());
	       _deptMasterVO.setIsValid(Config.IS_VALID_ACTIVE);
	       
	    }     
	   catch(Exception e){
	   	throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);
	   }
	   System.out.println("query"+query);
	   
	   try{    	  
		      Sequence sq=new Sequence();
		      
		      populateMAP.put(sq.next(), _deptMasterVO.getIsValid());
		      populateMAP.put(sq.next(), _deptMasterVO.getDepartmentCode());
		      populateMAP.put(sq.next(), _deptMasterVO.getDepartmentCode());
		           
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

public void insertRow(DepartmentMasterVO _deptMasterVO, UserVO _userVO){
	
	
	String query  = "";
   Map populateMAP =new HashMap();
   String filename=RegistrationConfig.QUERY_FILE_FOR_MASTERSDAO;
   String queryKey="CREATE.NEW.GBLT_DEPT_MASTER";
   
   try{
       query =HelperMethodsDAO.getQuery(filename,queryKey);
       _deptMasterVO.setSeatID(_userVO.getSeatId());
       _deptMasterVO.setIsValid(Config.IS_VALID_ACTIVE);
    }     
   catch(Exception e){
   	throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);
   }
   System.out.println("query"+query);
   
   try{    	  
	      Sequence sq=new Sequence();
	      populateMAP.put(sq.next(), _deptMasterVO.getDepartmentCode());
	      populateMAP.put(sq.next(), _deptMasterVO.getDepartmentCode());
	      populateMAP.put(sq.next(), _deptMasterVO.getDepartment());
	      populateMAP.put(sq.next(), _deptMasterVO.getDeptShortName());
	      populateMAP.put(sq.next(), _deptMasterVO.getEffectiveFrom());
	      populateMAP.put(sq.next(), _deptMasterVO.getIsValid());
	      populateMAP.put(sq.next(), _deptMasterVO.getSeatID());
	      populateMAP.put(sq.next(), _deptMasterVO.getEffectiveTo());
	      populateMAP.put(sq.next(), _deptMasterVO.getDepartmentType());
	      populateMAP.put(sq.next(), _deptMasterVO.getRemarks());
	           
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

public void updateDate(DepartmentMasterVO _deptMasterVO, UserVO _userVO){
	
	String query  = "";
	   Map populateMAP =new HashMap();
	   String filename=RegistrationConfig.QUERY_FILE_FOR_MASTERSDAO;
	   String queryKey="UPDATE.PREVIOUS_DATE.GBLT_DEPTHIST_MASTER";
	   
	   try{
	       query =HelperMethodsDAO.getQuery(filename,queryKey);
	       _deptMasterVO.setSeatID(_userVO.getSeatId());
	       _deptMasterVO.setIsValid(Config.IS_VALID_ACTIVE);
	       
	    }     
	   catch(Exception e){
	   	throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);
	   }
	   System.out.println("query"+query);
	   
	   try{    	  
		      Sequence sq=new Sequence();
		      
		      populateMAP.put(sq.next(), _deptMasterVO.getIsValid());
		      populateMAP.put(sq.next(), _deptMasterVO.getEffectiveFrom());
		      populateMAP.put(sq.next(), _deptMasterVO.getDepartmentCode());
		      populateMAP.put(sq.next(), _deptMasterVO.getDepartmentCode());
		           
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

public void updatePrevRow(DepartmentMasterVO deptMstVO,String serialNo, UserVO _userVO){
	
	String query  = "";
	   Map populateMAP =new HashMap();
	   String filename=RegistrationConfig.QUERY_FILE_FOR_MASTERSDAO;
	   String queryKey="UPDATE.PREVIOUS_ROW.GBLT_DEPTHIST_MASTER";
	   
	   try{
	       query =HelperMethodsDAO.getQuery(filename,queryKey);
	       deptMstVO.setSeatID(_userVO.getSeatId());
	       
	       
	    }     
	   catch(Exception e){
	   	throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);
	   }
	   System.out.println("query"+query);
	   
	   try{    	  
		      Sequence sq=new Sequence();
		      
		     
		      populateMAP.put(sq.next(),  deptMstVO.getEffectiveFrom());
		      populateMAP.put(sq.next(), deptMstVO.getDepartmentCode());
		      populateMAP.put(sq.next(), serialNo);
		           
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


public void updateNextRow(DepartmentMasterVO nextDeptVO,String currentEffToDate, UserVO _userVO){
	
	String query  = "";
	   Map populateMAP =new HashMap();
	   String filename=RegistrationConfig.QUERY_FILE_FOR_MASTERSDAO;
	   String queryKey="UPDATE.NEXT_ROW.GBLT_DEPTHIST_MASTER";
	   
	   try{
	       query =HelperMethodsDAO.getQuery(filename,queryKey);
	       nextDeptVO.setSeatID(_userVO.getSeatId());
	       
	       
	    }     
	   catch(Exception e){
	   	throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);
	   }
	   System.out.println("query"+query);
	   
	   try{    	  
		      Sequence sq=new Sequence();
		      
		      
		      populateMAP.put(sq.next(), currentEffToDate);
		      populateMAP.put(sq.next(), nextDeptVO.getDepartmentCode());
		      populateMAP.put(sq.next(), nextDeptVO.getDepartmentSlNo());
		           
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

public DepartmentMasterVO getPreviousRow(DepartmentMasterVO deptMstVO, UserVO _uservo) {
	
	DepartmentMasterVO _deptMstVO=new DepartmentMasterVO();
	String query =  "" ;
	Map populateMap =new HashMap();
	String filename=RegistrationConfig.QUERY_FILE_FOR_MASTERSDAO;
	String queryKey="SELECT.PREVIOUS_ROW.GBLT_DEPTHIST_MASTER"; 	  
	
	
	Sequence sq=new Sequence();
	populateMap.put(sq.next(),deptMstVO.getDepartmentCode());
	populateMap.put(sq.next(),deptMstVO.getIsValid());
	populateMap.put(sq.next(),deptMstVO.getDepartmentCode());
	populateMap.put(sq.next(),deptMstVO.getIsValid());
	populateMap.put(sq.next(),deptMstVO.getEffectiveFrom());
	
	
	Connection conn =super.getTransactionContext().getConnection();
	
	try{
 	      query =HelperMethodsDAO.getQuery(filename,queryKey);
	}catch(Exception e){
		throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::"+e);
	}
	
	System.out.println("query"+query);		 	 
 	
	try{
		ResultSet rs = HelperMethodsDAO.executeQuery(conn, query, populateMap);	 
 	    if(!rs.next())
 	    {
 	    	//throw new HisRecordNotFoundException();	 	    
 	    }
 	    else
 	    {
 	    	rs.beforeFirst();
 	    	while(rs.next())
 	    	{
 	    	HelperMethods.populateVOfrmRS(_deptMstVO,rs);  	    	
 	    	}
 	    System.out.println("after populating deptMstVO");
 	    }
	}
	catch(Exception e){
		if(e.getClass()==HisRecordNotFoundException.class){
			throw new HisRecordNotFoundException();	
		}
		else			 		
		 throw new HisDataAccessException("HisDataAccessException:: "+e);			 
	 }			 	 	
	return _deptMstVO;
}

public DepartmentMasterVO getNextRow(DepartmentMasterVO deptMstVO, UserVO _uservo) {
	
	DepartmentMasterVO _deptMstVO=new DepartmentMasterVO();
	String query =  "" ;
	Map populateMap =new HashMap();
	String filename=RegistrationConfig.QUERY_FILE_FOR_MASTERSDAO;
	String queryKey="SELECT.NEXT_ROW.GBLT_DEPTHIST_MASTER"; 	  
	
	
	Sequence sq=new Sequence();
	populateMap.put(sq.next(),deptMstVO.getDepartmentCode());
	populateMap.put(sq.next(),deptMstVO.getIsValid());
	populateMap.put(sq.next(),deptMstVO.getDepartmentCode());
	populateMap.put(sq.next(),deptMstVO.getIsValid());
	populateMap.put(sq.next(),deptMstVO.getEffectiveTo());
	
	
	Connection conn =super.getTransactionContext().getConnection();
	
	try{
 	      query =HelperMethodsDAO.getQuery(filename,queryKey);
	}catch(Exception e){
		throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::"+e);
	}
	
	System.out.println("query"+query);		 	 
 	
	try{
		ResultSet rs = HelperMethodsDAO.executeQuery(conn, query, populateMap);	 
 	    if(!rs.next())
 	    {
 	    	//throw new HisRecordNotFoundException();	 	    
 	    }
 	    else
 	    {
 	    	rs.beforeFirst();
 	    	while(rs.next())
 	    	{
 	    	HelperMethods.populateVOfrmRS(_deptMstVO,rs);  	    	
 	    	}
 	    System.out.println("after populating deptMstVO");
 	    }
	}
	catch(Exception e){
		if(e.getClass()==HisRecordNotFoundException.class){
			throw new HisRecordNotFoundException();	
		}
		else			 		
		 throw new HisDataAccessException("HisDataAccessException:: "+e);			 
	 }			 	 	
	return _deptMstVO;
}

public int getPreviousDepartmentDate(DepartmentMasterVO deptMstVO) {
	
	DepartmentMasterVO _deptMstVO=new DepartmentMasterVO();
	String query =  "" ;
	Map populateMap =new HashMap();
	String filename=RegistrationConfig.QUERY_FILE_FOR_MASTERSDAO;
	String queryKey="SELECT.PREV_DATE.GBLT_DEPTHIST_MASTER"; 	  
	
	String isValid=Config.IS_VALID_ACTIVE;
	
	Sequence sq=new Sequence();
	populateMap.put(sq.next(),deptMstVO.getEffectiveFrom());
	populateMap.put(sq.next(),deptMstVO.getEffectiveFrom());
	populateMap.put(sq.next(),deptMstVO.getEffectiveFrom());
	populateMap.put(sq.next(),deptMstVO.getDepartmentCode());
	populateMap.put(sq.next(),isValid);
	
	int count;	
	Connection conn =super.getTransactionContext().getConnection();
	
	try{
 	      query =HelperMethodsDAO.getQuery(filename,queryKey);
	}catch(Exception e){
		throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::"+e);
	}
	
	System.out.println("query"+query);		 	 
 	
	try{
		ResultSet rs = HelperMethodsDAO.executeQuery(conn, query, populateMap);	 
 	    rs.next();
 	   count=rs.getInt(1); 	        
 	    
 	   
 	    
	}
	catch(Exception e){
		e.printStackTrace();
		if(e.getClass()==HisRecordNotFoundException.class){
			throw new HisRecordNotFoundException();	
		}
		else			 		
		 throw new HisDataAccessException("HisDataAccessException:: "+e);			 
	 }			 	 	
	return count;
}

public String getNextDepartmentDate(DepartmentMasterVO deptMstVO) {	
	DepartmentMasterVO _deptMstVO=new DepartmentMasterVO();
	String query =  "" ;
	Map populateMap =new HashMap();
	String filename=RegistrationConfig.QUERY_FILE_FOR_MASTERSDAO;
	String queryKey="SELECT.NEXT_DATE.GBLT_DEPTHIST_MASTER"; 	  
	
	String isValid=Config.IS_VALID_ACTIVE;
	
	Sequence sq=new Sequence();
	
	populateMap.put(sq.next(),deptMstVO.getDepartmentCode());
	populateMap.put(sq.next(),deptMstVO.getEffectiveFrom());
	populateMap.put(sq.next(),isValid);
	
	String nextDate = "";
	int rowCount;
	Connection conn =super.getTransactionContext().getConnection();
	
	try{
 	      query =HelperMethodsDAO.getQuery(filename,queryKey);
	}catch(Exception e){
		throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::"+e);
	}
	
	System.out.println("query"+query);		 	 
 	
	try{
		ResultSet rs = HelperMethodsDAO.executeQuery(conn, query, populateMap);	 
 	    rs.last();
 	   rowCount=rs.getRow();
 	   if(rowCount>0)
 	   {
 		   nextDate=rs.getString(1);
 		  if(nextDate==null)
 		  {
 			  nextDate="";
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
	
	return nextDate;
}

public String[] getPreviousSerialNo(DepartmentMasterVO deptMstVo) {
	
	/*DepartmentMasterVO _deptMstVO=new DepartmentMasterVO();*/
	String query =  "" ;
	Map populateMap =new HashMap();
	String filename=RegistrationConfig.QUERY_FILE_FOR_MASTERSDAO;
	String queryKey="SELECT.PREVIOUS_SERIAL_NO.GBLT_DEPTHIST_MASTER"; 	  
	
	String isValid=Config.IS_VALID_ACTIVE;
	
	String serialNo="";
	
	String[] arrayRS=new String[2]; 
	
	String frmDate=""; //of Null Record for update
	
	int rowCount;
	Sequence sq=new Sequence();
	
	populateMap.put(sq.next(),deptMstVo.getDepartmentCode());
	
	
	
	
	Connection conn =super.getTransactionContext().getConnection();
	
	try{
 	      query =HelperMethodsDAO.getQuery(filename,queryKey);
	}catch(Exception e){
		throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::"+e);
	}
	
	System.out.println("query"+query);		 	 
 	
	try{
		ResultSet rs = HelperMethodsDAO.executeQuery(conn, query, populateMap);	 
		rs.last();
		rowCount=rs.getRow();
		rs.beforeFirst();
		rs.next();
		
		serialNo=rs.getString(1);
		
		frmDate=rs.getString(2);
			
			if(serialNo==null)
			{
				serialNo="";
			}
			
			if(frmDate==null)
			{
				frmDate="";
			} 
			arrayRS[0]=serialNo;
			arrayRS[1]=frmDate;
		
	}
	catch(Exception e){
		if(e.getClass()==HisRecordNotFoundException.class){
			throw new HisRecordNotFoundException();	
		}
		else			 		
		 throw new HisDataAccessException("HisDataAccessException:: "+e);			 
	 }			 	 	
	return arrayRS ;
}

public List getLocation(){
	 ResultSet rs=null;    		   	
  	  String query =  "" ;
  	  Map populateMAP =new HashMap();    		 	  	
	  String filename=RegistrationConfig.QUERY_FILE_FOR_MASTERSDAO;
	  String queryKey="SELECT.LOCATION.HGBT_LOCATION_MST";
	  
	    		 	  
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
	  
	  try{
	  rs=HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(),query,populateMAP);
	 if(!rs.next()){
		 throw new HisRecordNotFoundException("getLocation");
	 }
		 
    }catch(Exception e){
	 if(e.getClass()==HisRecordNotFoundException.class){
			throw new HisRecordNotFoundException();	
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

///////////////////testing gfor global alerts

public List getRecords(){
	 ResultSet rs=null;    		   	
 	  
 	  	  
	    		 	  
	  try{
		  Procedure proc = new Procedure("HisAlert");
			proc.addInParameter(1, Types.VARCHAR,"108");
			proc.addInParameter(2, Types.VARCHAR,"10008");
			proc.addInParameter(3, Types.VARCHAR,"10008");
			proc.addOutParameter(4, Types.VARCHAR);
			proc.addOutParameter(5, Types.REF);
			proc.execute(super.getTransactionContext().getConnection());	 
			String errorMsg = (String) proc.getParameterAt(4);
			rs = (ResultSet) proc.getParameterAt(5);
	     }
	  catch(Exception e)
	  {	
		  e.printStackTrace();
		 throw new HisDataAccessException(""+e);	 		  
	  }    		 	  
	  
	  
	  
	  try{
	  //rs=HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(),query,populateMAP);
	
		 
   }catch(Exception e){
	 if(e.getClass()==HisRecordNotFoundException.class){
			throw new HisRecordNotFoundException(e.getMessage());	
		}
		else
	 throw new HisDataAccessException("HelperMethodsDAO.getResultset"+e);
  }	 	 
	    		 	      		 	  
	  List alRecord = new ArrayList();    
	  List innerList=new ArrayList();
	  try{
		 // rs.beforeFirst();
		// ResultSetMetaData rsmd = rs.getMetaData();
		// int noOfColumns=rsmd.getColumnCount();
		 int i=0;
		 //  rs.beforeFirst();
		   while(rs.next())
		   {
			   System.out.println(i+"ist record"+rs.getString(1));
			   System.out.println(i+"2st record"+rs.getString(2));
			   innerList.add(0,rs.getString(1));
			   innerList.add(1,rs.getString(2));
			   alRecord.add(i, innerList);
			   i++;
		   }
	  }
	  catch(Exception e)
	  {
		throw new HisDataAccessException("CountryDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)"+e);    		 		 
	  }
 	  return alRecord;  	
}  


}
