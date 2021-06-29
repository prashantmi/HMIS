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
import hisglobal.vo.EpisodeVO;
import hisglobal.vo.UserVO;
import hisglobal.vo.ValueObject;

public class DepartmentMasterDAO extends DataAccessObject {
	Logger log;
	public DepartmentMasterDAO(TransactionContext _tx) {
		super(_tx);	
		log=LogManager.getLogger(this.getClass());
	}	
	
public void create(DepartmentMasterVO _deptMasterVO, UserVO _userVO){
		
		String query  = "";
        Map populateMAP =new HashMap();
        String filename=RegistrationConfig.QUERY_FILE_FOR_MASTERSDAO;
        String queryKey="INSERT.GBLT_DEPARTMENT_MASTER";
        
        
        try{
        	query =HelperMethodsDAO.getQuery(filename,queryKey);
        	_deptMasterVO.setSeatID(_userVO.getSeatId());
        	_deptMasterVO.setHospitalCode(_userVO.getHospitalCode());
        	
        	_deptMasterVO.setIsValid(Config.IS_VALID_ACTIVE);
        	
        }
        catch(Exception e){
        	throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);
        }
        log.info(query);
        
        
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
	 String strHl7Code=null;
	 if(_deptMasterVO.getHl7Code()==null||_deptMasterVO.getHl7Code().equalsIgnoreCase(""))
	 {
		 strHl7Code=null;
	 }
	
	 
	 _populateMAP.put(sq.next(), _deptMasterVO.getDepartmentCode());
	 _populateMAP.put(sq.next(), _deptMasterVO.getDepartmentCode());
		_populateMAP.put(sq.next(), _deptMasterVO.getHospitalCode());
	_populateMAP.put(sq.next(), _deptMasterVO.getDepartment());
	_populateMAP.put(sq.next(), _deptMasterVO.getHodCode());
	_populateMAP.put(sq.next(), _deptMasterVO.getDeptShortName());	
	_populateMAP.put(sq.next(), _deptMasterVO.getLocationCode());	
	//_populateMAP.put(sq.next(), _deptMasterVO.getEffectiveFrom());
	//_populateMAP.put(sq.next(), _deptMasterVO.getEffectiveTo());
	_populateMAP.put(sq.next(), _deptMasterVO.getIsValid() );
	_populateMAP.put(sq.next(), _deptMasterVO.getSeatID());	
	//_populateMAP.put(sq.next(), _deptMasterVO.getEntryDate());
	_populateMAP.put(sq.next(), _deptMasterVO.getDepartmentType());	
	_populateMAP.put(sq.next(), strHl7Code);	
	_populateMAP.put(sq.next(), _deptMasterVO.getRemarks());
	_populateMAP.put(sq.next(), _deptMasterVO.getHospitalCode());
	
}



public void update(DepartmentMasterVO _deptMasterVO, UserVO _userVO){
	
	
	String query  = "";
   Map populateMAP =new HashMap();
   String filename=RegistrationConfig.QUERY_FILE_FOR_MASTERSDAO;
   String queryKey="UPDATE.GBLT_DEPARTMENT_MASTER";
   
   try{
       query =HelperMethodsDAO.getQuery(filename,queryKey);
       _deptMasterVO.setLastModifiedSeatID(_userVO.getSeatId());
       _deptMasterVO.setIsValid(Config.IS_VALID_ACTIVE);
       _deptMasterVO.setHospitalCode(_userVO.getHospitalCode());
       
       
       
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
	      populateMAP.put(sq.next(), _deptMasterVO.getLastModifiedSeatID());
	      populateMAP.put(sq.next(), _deptMasterVO.getDepartmentCode());
	      populateMAP.put(sq.next(), _deptMasterVO.getDepartmentSlNo());
	      populateMAP.put(sq.next(), _deptMasterVO.getHospitalCode());
	      
	           
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
	populateMap.put(sq.next(),_uservo.getHospitalCode());
	
	
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


public List getLocation(UserVO _userVO){
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
	 log.info(query);	
	  Sequence sq=new Sequence();	 	
	  
	  populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
	  populateMAP.put(sq.next(),_userVO.getHospitalCode());
	  
	  try{
	  rs=HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(),query,populateMAP);
	 if(!rs.next()){
		 throw new HisRecordNotFoundException("Location Not Found");
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


public String getNextDepartmentDate(DepartmentMasterVO deptMstVO,UserVO _userVO) {	
	DepartmentMasterVO _deptMstVO=new DepartmentMasterVO();
	String query =  "" ;
	Map populateMap =new HashMap();
	String filename=RegistrationConfig.QUERY_FILE_FOR_MASTERSDAO;
	String queryKey="SELECT.NEXT_DATE.GBLT_DEPARTMENT_MASTER"; 	  
	
	String isValid=Config.IS_VALID_ACTIVE;
	
	Sequence sq=new Sequence();
	
	populateMap.put(sq.next(),deptMstVO.getDepartmentCode());
	populateMap.put(sq.next(),deptMstVO.getEffectiveFrom());
	populateMap.put(sq.next(),isValid);
	populateMap.put(sq.next(),_userVO.getHospitalCode());
	
	String nextDate = "";
	int rowCount;
	Connection conn =super.getTransactionContext().getConnection();
	
	try{
 	      query =HelperMethodsDAO.getQuery(filename,queryKey);
	}catch(Exception e){
		throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::"+e);
	}
	
	log.info(query);		 	 
 	
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

public int getPreviousDepartmentDate(DepartmentMasterVO deptMstVO) {
	
	DepartmentMasterVO _deptMstVO=new DepartmentMasterVO();
	String query =  "" ;
	Map populateMap =new HashMap();
	String filename=RegistrationConfig.QUERY_FILE_FOR_MASTERSDAO;
	String queryKey="SELECT.PREV_DATE.GBLT_DEPARTMENT_MASTER"; 	  
	
	String isValid=Config.IS_VALID_ACTIVE;
	
	Sequence sq=new Sequence();
	populateMap.put(sq.next(),deptMstVO.getEffectiveFrom());
	populateMap.put(sq.next(),deptMstVO.getEffectiveFrom());
	populateMap.put(sq.next(),deptMstVO.getDepartmentCode());
	
	
	int count;	
	Connection conn =super.getTransactionContext().getConnection();
	
	try{
 	      query =HelperMethodsDAO.getQuery(filename,queryKey);
	}catch(Exception e){
		throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::"+e);
	}
	
	log.info(query);		 	 
 	
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



public String[] getPreviousSerialNo(DepartmentMasterVO deptMstVo,UserVO _userVO) {
	
	/*DepartmentMasterVO _deptMstVO=new DepartmentMasterVO();*/
	String query =  "" ;
	Map populateMap =new HashMap();
	String filename=RegistrationConfig.QUERY_FILE_FOR_MASTERSDAO;
	String queryKey="SELECT.PREVIOUS_SERIAL_NO.GBLT_DEPARTMENT_MASTER"; 	  
	
	String isValid=Config.IS_VALID_ACTIVE;
	
	String serialNo="";
	
	String[] arrayRS=new String[2]; 
	
	String frmDate=""; //of Null Record for update
	
	int rowCount;
	Sequence sq=new Sequence();
	
	populateMap.put(sq.next(),deptMstVo.getDepartmentCode());
	populateMap.put(sq.next(),_userVO.getHospitalCode());
	
	
	
	
	Connection conn =super.getTransactionContext().getConnection();
	
	try{
 	      query =HelperMethodsDAO.getQuery(filename,queryKey);
	}catch(Exception e){
		throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::"+e);
	}
	
	log.info(query);		 	 
 	
	try{
		ResultSet rs = HelperMethodsDAO.executeQuery(conn, query, populateMap);	 
		rs.last();
		rowCount=rs.getRow();
		rs.beforeFirst();
		
		if(rs.next())
		{
		serialNo=rs.getString(1);
		
		frmDate=rs.getString(2);
		}	
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


public void updatePrevRow(DepartmentMasterVO deptMstVO,String serialNo, UserVO _userVO){
	
	String query  = "";
	   Map populateMAP =new HashMap();
	   String filename=RegistrationConfig.QUERY_FILE_FOR_MASTERSDAO;
	   String queryKey="UPDATE.PREVIOUS_ROW.GBLT_DEPARTMENT_MASTER";
	   
	   try{
	       query =HelperMethodsDAO.getQuery(filename,queryKey);
	       deptMstVO.setLastModifiedSeatID(_userVO.getSeatId());
	       
	       
	    }     
	   catch(Exception e){
	   	throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);
	   }
	   log.info(query);
	   
	   try{    	  
		      Sequence sq=new Sequence();
		      
		       //EARLIER WE USED TO ENTER THE EFFECTIVE FROM DATE ,BUT NOW WE ARE TO ENTER THE 
		      //EFFECTIVE_TO_DATE=SYSDATE-1  
		      //populateMAP.put(sq.next(),  deptMstVO.getEffectiveFrom());
		      //NOW WE ARE TO UPDATE THE ISVALID ALSO
		      populateMAP.put(sq.next(), Config.IS_VALID_DELETED);
		      populateMAP.put(sq.next(),  deptMstVO.getEntryDate());
		      populateMAP.put(sq.next(),  deptMstVO.getEntryDate());
		      populateMAP.put(sq.next(),  deptMstVO.getLastModifiedSeatID());
		      populateMAP.put(sq.next(), deptMstVO.getDepartmentCode());
		      populateMAP.put(sq.next(), deptMstVO.getDepartmentSlNo());
		      populateMAP.put(sq.next(), _userVO.getHospitalCode());
		           
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
   String queryKey="CREATE.NEW.GBLT_DEPARTMENT_MASTER";
   
   try{
       query =HelperMethodsDAO.getQuery(filename,queryKey);
       _deptMasterVO.setSeatID(_userVO.getSeatId());
       //_deptMasterVO.setIsValid(Config.IS_VALID_ACTIVE);
       _deptMasterVO.setHospitalCode(_userVO.getHospitalCode());
    }     
   catch(Exception e){
   	throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);
   }
   log.info(query);
   
   try{    	  
	      Sequence sq=new Sequence();
	      populateMAP.put(sq.next(), _deptMasterVO.getDepartmentCode());
	      populateMAP.put(sq.next(), _deptMasterVO.getDepartmentCode());
	      populateMAP.put(sq.next(), _deptMasterVO.getHospitalCode());
	      populateMAP.put(sq.next(), _deptMasterVO.getDepartment());
	      populateMAP.put(sq.next(), _deptMasterVO.getHodCode());
	      populateMAP.put(sq.next(), _deptMasterVO.getLocationCode());
	      populateMAP.put(sq.next(), _deptMasterVO.getDeptShortName());
          //EARLIER WE USED TO ENTER THE DATE FROM EFFECTIVE FROM DATE ,BUT NOW WE WILL ENTER SYSDATE 
	      //populateMAP.put(sq.next(), _deptMasterVO.getEffectiveFrom());
	      populateMAP.put(sq.next(), _deptMasterVO.getIsValid());
	      populateMAP.put(sq.next(), _deptMasterVO.getSeatID());
	      populateMAP.put(sq.next(), _deptMasterVO.getEffectiveTo());
	      populateMAP.put(sq.next(), _deptMasterVO.getDepartmentType());
	      populateMAP.put(sq.next(), _deptMasterVO.getRemarks());
	      populateMAP.put(sq.next(), _deptMasterVO.getHospitalCode());
	           
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

public int checkDuplicateAddModify(DepartmentMasterVO deptMstVO,UserVO _userVO) {
	
	DepartmentMasterVO _deptMstVO=new DepartmentMasterVO();
	String query =  "" ;
	Map populateMap =new HashMap();
	String filename=RegistrationConfig.QUERY_FILE_FOR_MASTERSDAO;
	String queryKey="SELECT.PREV_DATE.GBLT_DEPARTMENT_MASTER"; 	  
	
	String isValid=Config.IS_VALID_ACTIVE;
	
	Sequence sq=new Sequence();
	populateMap.put(sq.next(),deptMstVO.getDepartment());
	populateMap.put(sq.next(),_userVO.getHospitalCode());
	populateMap.put(sq.next(),deptMstVO.getDepartmentCode());
	
	
	int count;	
	Connection conn =super.getTransactionContext().getConnection();
	
	try{
 	      query =HelperMethodsDAO.getQuery(filename,queryKey);
	}catch(Exception e){
		throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::"+e);
	}
	
	log.info(query);		 	 
 	
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

	public DepartmentMasterVO[] getGlobalDepartment(UserVO _uservo) {
	 ResultSet rs=null;    		   	
	  String query =  "" ;
	  ValueObject[] vo={};
	  DepartmentMasterVO[] deptVO=null;
	  Map populateMAP =new HashMap();    		 	  	
	  String filename=RegistrationConfig.QUERY_FILE_FOR_MASTERSDAO;
	  String queryKey="SELECT.GLOBAL_DEPARTMENT.GBLT_DEPARTMENT_MST";
	  
	    		 	  
	  try{
	      query =HelperMethodsDAO.getQuery(filename,queryKey);   		 	 
	     }
	  catch(Exception e)
	  {	
		 throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);	 		  
	  }    		 	  
	 log.info(query);	
	  Sequence sq=new Sequence();	 	
	  
	  populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
	  populateMAP.put(sq.next(),Config.SUPER_USER_HOSPITAL_CODE);
	  populateMAP.put(sq.next(),_uservo.getHospitalCode());
	  populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
	  populateMAP.put(sq.next(),Config.IS_VALID_INACTIVE);
	  try{
	  rs=HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(),query,populateMAP);
	 if(!rs.next()){
		 throw new HisRecordNotFoundException("Global Department Not Found");
	 }
		 
  }catch(Exception e){
	 if(e.getClass()==HisRecordNotFoundException.class){
			throw new HisRecordNotFoundException(e.getMessage());	
		}
		else
	 throw new HisDataAccessException("HelperMethodsDAO.getResultset"+e);
 }	 	 
	    		 	      		 	  
	 // List alRecord = new ArrayList();    		 	  
	  try{
	 // alRecord= HelperMethodsDAO.getAlOfEntryObjects(rs);
		  rs.beforeFirst();
		  vo = HelperMethods.populateVOfrmRS(DepartmentMasterVO.class, rs);
			
			deptVO = new DepartmentMasterVO[vo.length];
			for (int i = 0; i < vo.length; i++)
			{
				
				deptVO[i] = (DepartmentMasterVO) vo[i];
				
			}
	  }
	  catch(Exception e)
	  {
		throw new HisDataAccessException("CountryDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)"+e);    		 		 
	  }
	  return deptVO;  	

	
}

	public DepartmentMasterVO getGlobalDepartmentDetail(DepartmentMasterVO _deptVO,UserVO _uservo) {
		 ResultSet rs=null;    		   	
		  String query =  "" ;
		  DepartmentMasterVO deptVO=new DepartmentMasterVO();
		  Map populateMAP =new HashMap();    		 	  	
		  String filename=RegistrationConfig.QUERY_FILE_FOR_MASTERSDAO;
		  String queryKey="SELECT.GLOBAL_DEPARTMENT_DETAIL.GBLT_DEPARTMENT_MST";
		  
		    		 	  
		  try{
		      query =HelperMethodsDAO.getQuery(filename,queryKey);   		 	 
		     }
		  catch(Exception e)
		  {	
			 throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);	 		  
		  }    		 	  
		 log.info(query);	
		  Sequence sq=new Sequence();	 	
		  
		  populateMAP.put(sq.next(),_deptVO.getDepartmentCode());
		  populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
		  populateMAP.put(sq.next(),Config.SUPER_USER_HOSPITAL_CODE);
		  
		  try{
		  rs=HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(),query,populateMAP);
		 if(!rs.next()){
			 throw new HisRecordNotFoundException("Global Department Not Found");
		 }
			 
	  }catch(Exception e){
		 if(e.getClass()==HisRecordNotFoundException.class){
				throw new HisRecordNotFoundException(e.getMessage());	
			}
			else
		 throw new HisDataAccessException("HelperMethodsDAO.getResultset"+e);
	 }	 	 
		    		 	      		 	  
		 // List alRecord = new ArrayList();    		 	  
		  try{
		 // alRecord= HelperMethodsDAO.getAlOfEntryObjects(rs);
			  HelperMethods.populateVOfrmRS(deptVO, rs);
				
				
		  }
		  catch(Exception e)
		  {
			throw new HisDataAccessException("CountryDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)"+e);    		 		 
		  }
		  return deptVO;  	

		
}

}//end of class
