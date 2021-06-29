/**
 * 
 */
package registration.master.dao;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.exceptions.HisUpdateUnsuccesfullException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.TransactionContext;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.Sequence;
import hisglobal.vo.UnitMasterVO;
import hisglobal.vo.UserDeskMenuMasterVO;
import hisglobal.vo.UserDeskMenuTemplateMasterVO;
import hisglobal.vo.UserVO;
import hisglobal.vo.ValueObject;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import registration.RegistrationConfig;

/**
 * @author Administrator
 *
 */
public class UnitMasterDAO extends DataAccessObject {

	public UnitMasterDAO(TransactionContext _tx) {
		super(_tx);
		// TODO Auto-generated constructor stub
	}
	
	public void create(UnitMasterVO _unitMasterVO, UserVO _userVO){
		System.out.println("inside  create of UnitMasterDAO");
		String query  = "";
        Map populateMAP =new HashMap();
        System.out.println("inside cretae");
        String filename=RegistrationConfig.QUERY_FILE_FOR_MASTERSDAO;
        String queryKey="INSERT.HGBT_UNIT_MST";                       
        //Properties properties = new Properties();        
        //call the getQueryMethod with arguments filename,querykey from prop file
        _unitMasterVO.setSeatID(_userVO.getSeatId());
        _unitMasterVO.setHospitalCode(_userVO.getHospitalCode());
        try{
        	query =HelperMethodsDAO.getQuery(filename,queryKey);
        }
        catch(Exception e){
        	throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);
        }
        System.out.println("query"+query);
        
        try{
        	populateMap(_unitMasterVO,populateMAP);
        }
        catch(Exception e){
        	throw new HisApplicationExecutionException("UnknownChangeDAO:populateMap(_unknownChangeVO,populateMAP)::"+e);
        }
        try{
        	HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(),query,populateMAP);
        }
        catch(Exception e){
        	throw new HisDataAccessException("HelperMethodsDAO.getResultset"+e);
        }
        
	}


public String getMaxUnitCode(UnitMasterVO _unitMasterVO,UserVO _userVO){
	String query  = "";
	String unitCode  = "";
	
	ResultSet rs=null;
    Map populateMAP =new HashMap();
    String filename=RegistrationConfig.QUERY_FILE_FOR_MASTERSDAO;
    String queryKey="SELECT.MAX_UNIT_CODE.HGBT_UNIT_MST";                       
    //Properties properties = new Properties();        
    //call the getQueryMethod with arguments filename,querykey from prop file
    
    try{
    	query =HelperMethodsDAO.getQuery(filename,queryKey);
    }
    catch(Exception e){
    	throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);
    }
    System.out.println("query"+query);
    
    try{
    	Sequence sq=new Sequence();   	
    	populateMAP.put(sq.next(), _unitMasterVO.getDepartmentCode());  
    	populateMAP.put(sq.next(), _userVO.getHospitalCode());  
    }
    catch(Exception e){
    	throw new HisApplicationExecutionException("UnknownChangeDAO:populateMap(_unknownChangeVO,populateMAP)::"+e);
    }
    try{
    	 rs=HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(),query,populateMAP);
    	 rs.next();
    	 unitCode=rs.getString(1);    	
    }
    catch(Exception e){
    	throw new HisDataAccessException("HelperMethodsDAO.getResultset"+e);
    }
    return unitCode;    
	
}
	
	/**
	 * Populates the map with the unknown change details to be entered in the DB.
	 * @param	_unknownChangeVO	Provides unknown change details to be entered.
	 * @param	_populateMAP	Map containig values which will be used for insert query.
	 */
    public void populateMap(UnitMasterVO _unitMasterVO, Map _populateMAP){
    	String timeString=RegistrationConfig.TIME_STRING; 
    	//modified by Anshul for Postgres compatiblity
    	String strFileDepartmentUnitCode="";
     	String strDepartmentUnitCode="";
     	String strStartNo="";
    	if(_unitMasterVO.getStartNo()==null||_unitMasterVO.getStartNo().equalsIgnoreCase(""))
    	{
    		strStartNo=null;
    	}
    	else
    	{
    		strStartNo=_unitMasterVO.getStartNo();
    	}
    	if(_unitMasterVO.getFileDepartmentCode()==null||_unitMasterVO.getFileDepartmentCode().equalsIgnoreCase(""))
    	{
    		strDepartmentUnitCode=null;
    	}
    	else
    	{
    		strDepartmentUnitCode=_unitMasterVO.getFileDepartmentCode();
    	}
    	if(_unitMasterVO.getFileDepartmentUnitCode()==null||_unitMasterVO.getFileDepartmentUnitCode().equalsIgnoreCase(""))
    	{
    		strFileDepartmentUnitCode=null;
    	}
    	else
    	{
    		strFileDepartmentUnitCode=_unitMasterVO.getFileDepartmentUnitCode();
    	}
        System.out.println("strStartNo"+strStartNo);
        System.out.println("strDepartmentUnitCode"+strDepartmentUnitCode);
        System.out.println("strFileDepartmentUnitCode"+strFileDepartmentUnitCode);
    	Sequence sq=new Sequence();   	
    	_populateMAP.put(sq.next(), _unitMasterVO.getDepartmentUnitCode());    	
    	_populateMAP.put(sq.next(), _unitMasterVO.getDepartmentCode());    	
    	_populateMAP.put(sq.next(), _unitMasterVO.getUnitName());
    	_populateMAP.put(sq.next(), _unitMasterVO.getEmpNo());
    	_populateMAP.put(sq.next(), _unitMasterVO.getSeatID());    	
    	_populateMAP.put(sq.next(), _unitMasterVO.getIsGeneral());
    	_populateMAP.put(sq.next(), _unitMasterVO.getEffectiveFrom());
    	_populateMAP.put(sq.next(), _unitMasterVO.getEffectiveTo());
    	_populateMAP.put(sq.next(), _unitMasterVO.getUnitCode());
    	_populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE); 
    	_populateMAP.put(sq.next(),_unitMasterVO.getIsExpiry()); 
    	_populateMAP.put(sq.next(),_unitMasterVO.getExpiryDay()); 
    	_populateMAP.put(sq.next(),_unitMasterVO.getExpiryMonth()); 
    	_populateMAP.put(sq.next(),_unitMasterVO.getDiagnosisCodeType());
    	_populateMAP.put(sq.next(),_unitMasterVO.getDefaultCloseDays());
    	_populateMAP.put(sq.next(),_unitMasterVO.getLocationCode());
    	_populateMAP.put(sq.next(),_unitMasterVO.getHospitalCode());
    	_populateMAP.put(sq.next(),_unitMasterVO.getFileNoRequired());
    	_populateMAP.put(sq.next(),_unitMasterVO.getIsUnit());
    	_populateMAP.put(sq.next(),_unitMasterVO.getFilePrefix());
    	_populateMAP.put(sq.next(),strStartNo);
    	_populateMAP.put(sq.next(),strDepartmentUnitCode);
    	_populateMAP.put(sq.next(),strFileDepartmentUnitCode);
    	_populateMAP.put(sq.next(),_unitMasterVO.getCardPrintFlag());
    	_populateMAP.put(sq.next(),_unitMasterVO.getFilePrintFlag());
    }
    
public UnitMasterVO getUnitDetails (String _deptUnitCode,String _unitSerialNo, UserVO _userVO){    	
    	
	   UnitMasterVO unitMasterVO=new UnitMasterVO();
    	//ValueObject[] vo= {};
		String query =  "" ;
		Map populateMap =new HashMap();
		String filename=RegistrationConfig.QUERY_FILE_FOR_MASTERSDAO;
		String queryKey="SELECT.ALL.HGBT_UNIT_MST";
		Sequence sq=new Sequence();
			
		populateMap.put(sq.next(),RegistrationConfig.IS_HOU_DOCTOR);
		//populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
		populateMap.put(sq.next(),_deptUnitCode);
		populateMap.put(sq.next(),_unitSerialNo);
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
	 	    	throw new HisRecordNotFoundException();	 	    
	 	    }
	 	    else{
	 	    	rs.beforeFirst();
	 	    	HelperMethods.populateVOfrmRS(unitMasterVO,rs);	
	 	    	System.out.println("file no in dao"+unitMasterVO.getFileNoRequired());
	 	    }
	 	    System.out.println("after populating _episodeVO");
		}
		catch(Exception e){
			if(e.getClass()==HisRecordNotFoundException.class){
				throw new HisRecordNotFoundException();	
			}
			else			 		
			 throw new HisDataAccessException("EpisodeDAO:retrieveByCrNo::Episode Details:: "+e);			 
		 }			 	 	
    	return unitMasterVO;   	
    }   


public UnitMasterVO[] getAllUnitsToDept (String _deptUnitCode, UserVO _userVO){    	
	
	UnitMasterVO unitMasterVO[];
 	ValueObject[] vo= {};
		String query =  "" ;
		Map populateMap =new HashMap();
		String filename=RegistrationConfig.QUERY_FILE_FOR_MASTERSDAO;
		String queryKey="SELECT.ALLUNITS.HGBT_UNIT_MST";
		Sequence sq=new Sequence();
		
		populateMap.put(sq.next(),_deptUnitCode);
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
	 	    	throw new HisRecordNotFoundException();	 	    
	 	    }
	 	    else{
	 	    	System.out.println("rooms founddddddddddd");
	 	    	rs.beforeFirst();
	 	    	vo=HelperMethods.populateVOfrmRS(UnitMasterVO.class,rs);
	 	    	System.out.println("rooms founddddddddddd");
	 	    	unitMasterVO =new UnitMasterVO[vo.length];
	 	    	 for(int i=0;i<vo.length;i++){					
	 	    		unitMasterVO[i]=(UnitMasterVO)vo[i];					
				 }	
	 	    	System.out.println("rooms founddddddddddd");
	 	    }
	 	    System.out.println("after populating UnitMasterVO");
		}
		catch(Exception e){
			if(e.getClass()==HisRecordNotFoundException.class){
				throw new HisRecordNotFoundException();	
			}
			else	
			{
			  e.printStackTrace();	
			 throw new HisDataAccessException("EpisodeDAO:retrieveByCrNo::Episode Details:: "+e);
			}
		 }			 	 	
 	return unitMasterVO;   
 }    


//for label change of a unit 
public void updateAdminDetails(UnitMasterVO _unitMasterVO, UserVO _userVO){
	System.out.println("inside  create of UnitMasterDAO");
	String query  = "";
    Map populateMAP =new HashMap();
    System.out.println("inside cretae");
    String filename=RegistrationConfig.QUERY_FILE_FOR_MASTERSDAO;
    
    String queryKey="UPDATE.ADMIN.HGBT_UNIT_MST";                       
    //Properties properties = new Properties();        
    //call the getQueryMethod with arguments filename,querykey from prop file
    
    try{
    	query =HelperMethodsDAO.getQuery(filename,queryKey);
    }
    catch(Exception e){
    	throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);
    }
    System.out.println("query"+query);
    
    try{
    	Sequence sq=new Sequence();   	 
    	populateMAP.put(sq.next(), _unitMasterVO.getUnitName());
    	populateMAP.put(sq.next(), _unitMasterVO.getIsGeneral());    	
    	populateMAP.put(sq.next(), _unitMasterVO.getDepartmentUnitCode());    	
    	//populateMAP.put(sq.next(),RegistrationConfig.IS_VALID_ACTIVE);
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
public void updateTempClosureDetails(UnitMasterVO _unitMasterVO, UserVO _userVO){
	System.out.println("inside  create of UnitMasterDAO");
	String query  = "";
    Map populateMAP =new HashMap();
    System.out.println("inside cretae");
    String filename=RegistrationConfig.QUERY_FILE_FOR_MASTERSDAO;
    String queryKey="UPDATE.TEMPCLOSE.HGBT_UNIT_MST";                       
    //Properties properties = new Properties();        
    //call the getQueryMethod with arguments filename,querykey from prop file
    
    try{
    	query =HelperMethodsDAO.getQuery(filename,queryKey);
    }
    catch(Exception e){
    	throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);
    }
    System.out.println("query"+query);    
    try{
    	Sequence sq=new Sequence();   	 
    	populateMAP.put(sq.next(), _unitMasterVO.getEffectiveDate() +RegistrationConfig.TIME_STRING);
    	/*populateMAP.put(sq.next(), _unitMasterVO.getInactiveFromDate()+RegistrationConfig.TIME_STRING);
    	populateMAP.put(sq.next(), _unitMasterVO.getInactiveTillDate()+RegistrationConfig.TIME_STRING);*/
    	populateMAP.put(sq.next(), _unitMasterVO.getDepartmentUnitCode());    	
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
public void updatePermanentClosureDetails(UnitMasterVO _unitMasterVO, UserVO _userVO){
	System.out.println("inside  create of UnitMasterDAO");
	String query  = "";
    Map populateMAP =new HashMap();
    System.out.println("inside cretae");
    String filename=RegistrationConfig.QUERY_FILE_FOR_MASTERSDAO;
    String queryKey="UPDATE.PERMANENT.HGBT_UNIT_MST";                       
    //Properties properties = new Properties();        
    //call the getQueryMethod with arguments filename,querykey from prop file
    
    try{
    	query =HelperMethodsDAO.getQuery(filename,queryKey);
    	/*_unitMasterVO.setInactiveTillDate(RegistrationConfig.INACTIVE_TILL_DATE +RegistrationConfig.TIME_STRING );*/
    }
    catch(Exception e){
    	throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);
    }
    System.out.println("query"+query);
    
    try{
    	Sequence sq=new Sequence();   	 
    	/*populateMAP.put(sq.next(), _unitMasterVO.getInactiveFromDate());
    	populateMAP.put(sq.next(), _unitMasterVO.getInactiveTillDate());*/
    	populateMAP.put(sq.next(), _unitMasterVO.getDepartmentUnitCode());    
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

public void updateUnitDetails(UnitMasterVO _unitMasterVO, UserVO _userVO){
	System.out.println("inside  create of UnitMasterDAO");
	String query  = "";
    Map populateMAP =new HashMap();
    System.out.println("inside cretae");
    String filename=RegistrationConfig.QUERY_FILE_FOR_MASTERSDAO;
    String queryKey="UPDATE.HGBT_UNIT_MST";                       
    //Properties properties = new Properties();        
    //call the getQueryMethod with arguments filename,querykey from prop file
    
    try{
    	query =HelperMethodsDAO.getQuery(filename,queryKey);
    }
    catch(Exception e){
    	throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);
    }
    System.out.println("query"+query);
    
    try{
    	Sequence sq=new Sequence();   	 
    	populateMAP.put(sq.next(), _unitMasterVO.getEffectiveDate() +RegistrationConfig.TIME_STRING);
    	populateMAP.put(sq.next(), _unitMasterVO.getIsGeneral());
    	/*populateMAP.put(sq.next(), _unitMasterVO.getInactiveFromDate()+RegistrationConfig.TIME_STRING);
    	populateMAP.put(sq.next(), _unitMasterVO.getInactiveFromDate()+RegistrationConfig.TIME_STRING);*/
    	populateMAP.put(sq.next(), _unitMasterVO.getDepartmentUnitCode());    
    }
    catch(Exception e){
    	throw new HisApplicationExecutionException("UnknownChangeDAO:populateMap(_unknownChangeVO,populateMAP)::"+e);
    }
    try{
    	HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(),query,populateMAP);
    }
    catch(Exception e){
    	throw new HisDataAccessException("HelperMethodsDAO.getResultset"+e);
    }    
}

public void deleteUnit(UnitMasterVO _unitMasterVO, UserVO _userVO){
	System.out.println("inside  create of UnitMasterDAO");
	String query  = "";
    Map populateMAP =new HashMap();
    System.out.println("inside cretae");
    String filename=RegistrationConfig.QUERY_FILE_FOR_MASTERSDAO;
    String queryKey="DELETE.HGBT_UNIT_MST";                       
    //Properties properties = new Properties();        
    //call the getQueryMethod with arguments filename,querykey from prop file
    
    try{
    	query =HelperMethodsDAO.getQuery(filename,queryKey);
    	_unitMasterVO.setIsValid(Config.IS_VALID_DELETED);
    }
    catch(Exception e){
    	throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);
    }
    System.out.println("query"+query);
    
    try{
    	Sequence sq=new Sequence();
    	populateMAP.put(sq.next(), _unitMasterVO.getIsValid());
    	populateMAP.put(sq.next(), _unitMasterVO.getDepartmentUnitCode()); 
    	populateMAP.put(sq.next(), _unitMasterVO.getUnitSerialNo());
    	populateMAP.put(sq.next(), _userVO.getHospitalCode());
    }
    catch(Exception e){
    	throw new HisApplicationExecutionException("UnknownChangeDAO:populateMap(_unknownChangeVO,populateMAP)::"+e);
    }
    try{
    	HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(),query,populateMAP);
    }
    catch(Exception e){
    	throw new HisDataAccessException("HelperMethodsDAO.getResultset"+e);
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

public void updateUnitMst(UnitMasterVO _unitMasterVO, UserVO _userVO){
	System.out.println("inside  create of UnitMasterDAO");
	String query  = "";
    Map populateMAP =new HashMap();
    System.out.println("inside cretae");
    String filename=RegistrationConfig.QUERY_FILE_FOR_MASTERSDAO;
    String queryKey="UPDATE.UNITMST.HGBT_UNIT_MST";                       
    //Properties properties = new Properties();        
    //call the getQueryMethod with arguments filename,querykey from prop file
    
    _unitMasterVO.setLastModifiedSeatID(_userVO.getSeatId());
    
    try{
    	query =HelperMethodsDAO.getQuery(filename,queryKey);
    	//_unitMasterVO.setIsValid(RegistrationConfig.IS_VALIDIS_VALID_DELETED);
    }
    catch(Exception e){
    	throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);
    }
    System.out.println("query"+query);
    
    try{
    	Sequence sq=new Sequence();
    	populateMAP.put(sq.next(), _unitMasterVO.getUnitName());
    	populateMAP.put(sq.next(), _unitMasterVO.getIsGeneral());
    	populateMAP.put(sq.next(), _unitMasterVO.getEffectiveFrom());
    	populateMAP.put(sq.next(), _unitMasterVO.getEffectiveTo());
    	populateMAP.put(sq.next(), _unitMasterVO.getEntryDate());
    	populateMAP.put(sq.next(), _unitMasterVO.getLastModifiedSeatID());
    	populateMAP.put(sq.next(), _unitMasterVO.getRemarks());
    	populateMAP.put(sq.next(), _unitMasterVO.getIsExpiry());
    	populateMAP.put(sq.next(), _unitMasterVO.getExpiryDay());
    	populateMAP.put(sq.next(), _unitMasterVO.getExpiryMonth());
    	populateMAP.put(sq.next(), _unitMasterVO.getDiagnosisCodeType());
    	populateMAP.put(sq.next(), _unitMasterVO.getDefaultCloseDays());
    	populateMAP.put(sq.next(),_unitMasterVO.getLocationCode());
    	populateMAP.put(sq.next(),_unitMasterVO.getFileNoRequired());
    	populateMAP.put(sq.next(), _unitMasterVO.getDepartmentUnitCode());
    	populateMAP.put(sq.next(), _unitMasterVO.getUnitSerialNo());
    	populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);  
    	populateMAP.put(sq.next(), _userVO.getHospitalCode());  
    }
    catch(Exception e){
    	throw new HisApplicationExecutionException("UnknownChangeDAO:populateMap(_unknownChangeVO,populateMAP)::"+e);
    }
    try{
    	HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(),query,populateMAP);
    }
    catch(Exception e){
    	throw new HisDataAccessException("HelperMethodsDAO.getResultset"+e);
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

public  String getNextUnitDate(UnitMasterVO unitMasterVO,UserVO _userVO) {	
	
	String query =  "" ;
	Map populateMap =new HashMap();
	String filename=RegistrationConfig.QUERY_FILE_FOR_MASTERSDAO;
	String queryKey="SELECT.NEXT_DATE.HGBT_UNIT_MASTER"; 	  
	
	String isValid=Config.IS_VALID_ACTIVE;
	
	Sequence sq=new Sequence();
	
	populateMap.put(sq.next(),unitMasterVO.getDepartmentUnitCode());
	populateMap.put(sq.next(),unitMasterVO.getEffectiveFrom());
	populateMap.put(sq.next(),isValid);
	populateMap.put(sq.next(),_userVO.getHospitalCode());
	
	String nextDate = "";
	int rowCount;
	/*Connection conn =super.getTransactionContext().getConnection();*/
	
	try{
 	      query =HelperMethodsDAO.getQuery(filename,queryKey);
	}catch(Exception e){
		throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::"+e);
	}
	
	System.out.println("query"+query);		 	 
 	
	try{
		ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMap);	 
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


public  int getPreviousUnitDate(UnitMasterVO unitMasterVO,UserVO _userVO) {
	
	
	String query =  "" ;
	Map populateMap =new HashMap();
	String filename=RegistrationConfig.QUERY_FILE_FOR_MASTERSDAO;
	String queryKey="SELECT.PREV_DATE.HGBT_UNIT_MASTER"; 	  
	
	String isValid=Config.IS_VALID_ACTIVE;
	
	Sequence sq=new Sequence();
	populateMap.put(sq.next(),unitMasterVO.getEffectiveFrom());
	populateMap.put(sq.next(),unitMasterVO.getEffectiveFrom());
	populateMap.put(sq.next(),unitMasterVO.getDepartmentUnitCode());
	populateMap.put(sq.next(),_userVO.getHospitalCode());
	
	
	int count;	
	/*Connection conn =super.getTransactionContext().getConnection();*/
	
	try{
 	      query =HelperMethodsDAO.getQuery(filename,queryKey);
	}catch(Exception e){
		throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::"+e);
	}
	
	System.out.println("query"+query);		 	 
 	
	try{
		ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMap);	 
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


public  String[] getPreviousSerialNo(UnitMasterVO unitMasterVO,UserVO _userVO) {
	
	/*DepartmentMasterVO _deptMstVO=new DepartmentMasterVO();*/
	String query =  "" ;
	Map populateMap =new HashMap();
	String filename=RegistrationConfig.QUERY_FILE_FOR_MASTERSDAO;
	String queryKey="SELECT.PREVIOUS_SERIAL_NO.HGBT_UNIT_MASTER"; 	  
	
	String isValid=Config.IS_VALID_ACTIVE;
	
	String serialNo="";
	
	String[] arrayRS=new String[2]; 
	
	String frmDate=""; //of Null Record for update
	
	int rowCount;
	Sequence sq=new Sequence();
	
	populateMap.put(sq.next(),unitMasterVO.getDepartmentUnitCode());
	populateMap.put(sq.next(),_userVO.getHospitalCode());
	
	
	
	
	/*Connection conn =super.getTransactionContext().getConnection();*/
	
	try{
 	      query =HelperMethodsDAO.getQuery(filename,queryKey);
	}catch(Exception e){
		throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::"+e);
	}
	
	System.out.println("query"+query);		 	 
 	
	try{
		ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMap);	 
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


public void updatePrevRow(UnitMasterVO _unitMasterVO,String serialNo, UserVO _userVO){
	
	String query  = "";
	   Map populateMAP =new HashMap();
	   String filename=RegistrationConfig.QUERY_FILE_FOR_MASTERSDAO;
	   String queryKey="UPDATE.PREVIOUS_ROW.HGBT_UNIT_MASTER";
	   
	   try{
	       query =HelperMethodsDAO.getQuery(filename,queryKey);
	       _unitMasterVO.setLastModifiedSeatID(_userVO.getSeatId());
	       
	       
	    }     
	   catch(Exception e){
	   	throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);
	   }
	   System.out.println("query"+query);
	   
	   try{    	  
		      Sequence sq=new Sequence();
		      
		     
		      //populateMAP.put(sq.next(),  _unitMasterVO.getEffectiveFrom());//earlier used code
		      populateMAP.put(sq.next(), Config.IS_VALID_DELETED);
		      populateMAP.put(sq.next(),  _unitMasterVO.getEntryDate());
		      populateMAP.put(sq.next(),  _unitMasterVO.getEntryDate());
		      populateMAP.put(sq.next(),  _unitMasterVO.getLastModifiedSeatID());
		      populateMAP.put(sq.next(), _unitMasterVO.getDepartmentUnitCode());
		      populateMAP.put(sq.next(), serialNo);
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



public void insertRow(UnitMasterVO _unitMasterVO, UserVO _userVO){
	
	
	String query  = "";
   Map populateMAP =new HashMap();
   String filename=RegistrationConfig.QUERY_FILE_FOR_MASTERSDAO;
   String queryKey="CREATE.NEW.HGBT_UNIT_MASTER";
   
   try{
       query =HelperMethodsDAO.getQuery(filename,queryKey);
       _unitMasterVO.setSeatID(_userVO.getSeatId());
       //_unitMasterVO.setIsValid(Config.IS_VALID_ACTIVE);
       _unitMasterVO.setHospitalCode(_userVO.getHospitalCode());
    }     
   catch(Exception e){
   	throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);
   }
   System.out.println("query"+query);
   
   try{    	  
	      Sequence sq=new Sequence();
	      populateMAP.put(sq.next(), _unitMasterVO.getDepartmentUnitCode());
	      populateMAP.put(sq.next(), _unitMasterVO.getDepartmentCode());
	      populateMAP.put(sq.next(), _unitMasterVO.getUnitName());
	      populateMAP.put(sq.next(), _unitMasterVO.getEmpNo());
	      populateMAP.put(sq.next(), _unitMasterVO.getDepartmentUnitCode());
	      populateMAP.put(sq.next(), _unitMasterVO.getHospitalCode());
	      populateMAP.put(sq.next(), _unitMasterVO.getSeatID());
	      populateMAP.put(sq.next(), _unitMasterVO.getIsGeneral());
	     // populateMAP.put(sq.next(), _unitMasterVO.getEffectiveFrom());
	      populateMAP.put(sq.next(), _unitMasterVO.getEffectiveTo());
	      populateMAP.put(sq.next(), _unitMasterVO.getUnitCode());
	      populateMAP.put(sq.next(), _unitMasterVO.getIsValid());
	      populateMAP.put(sq.next(), _unitMasterVO.getRemarks());
	      populateMAP.put(sq.next(), _unitMasterVO.getIsExpiry());
	      populateMAP.put(sq.next(), _unitMasterVO.getExpiryDay());
	      populateMAP.put(sq.next(), _unitMasterVO.getExpiryMonth());
	      populateMAP.put(sq.next(), _unitMasterVO.getDiagnosisCodeType());
	      populateMAP.put(sq.next(), _unitMasterVO.getDefaultCloseDays());
	      populateMAP.put(sq.next(),_unitMasterVO.getLocationCode());
	      populateMAP.put(sq.next(), _unitMasterVO.getHospitalCode());
	      populateMAP.put(sq.next(),_unitMasterVO.getIsUnit());
	      populateMAP.put(sq.next(),_unitMasterVO.getFileNoRequired());
	      populateMAP.put(sq.next(), _unitMasterVO.getHospitalCode());
	      populateMAP.put(sq.next(), _unitMasterVO.getDepartmentUnitCode());
	      populateMAP.put(sq.next(), _unitMasterVO.getFilePrefix());
	      populateMAP.put(sq.next(), _unitMasterVO.getStartNo());
	      populateMAP.put(sq.next(), _unitMasterVO.getFileDepartmentCode());
	      populateMAP.put(sq.next(), _unitMasterVO.getFileDepartmentUnitCode());
	      populateMAP.put(sq.next(),_unitMasterVO.getCardPrintFlag());
	      populateMAP.put(sq.next(),_unitMasterVO.getFilePrintFlag());
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

    
	//* Getting Unit Name
	public UserDeskMenuTemplateMasterVO getUnitName(String _deptUnitCode, UserVO _userVO)
	{
		ResultSet rs=null;
		String query =  "" ;
		Map populateMap =new HashMap();
		
		String filename=RegistrationConfig.QUERY_FILE_FOR_MASTERSDAO;
		String queryKey="SELECT.GBLT_DEPARTMENT_MST";

		Sequence sq=new Sequence();
		
		try
		{
			populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
			populateMap.put(sq.next(),_deptUnitCode);
			System.out.println("deptunit code is..."+_deptUnitCode);
			
			populateMap.put(sq.next(),_userVO.getHospitalCode());
		}
		catch(Exception e)
		{
			throw new HisApplicationExecutionException("UnitMasterDAO:populateMap::"+e);
		}
		
		try
		{
			query =HelperMethodsDAO.getQuery(filename,queryKey);
		}
		catch(Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);
		}
		
		System.out.println("   -------> query :: "+query);
		
		try
		{
			rs=HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(),query,populateMap);
		}
		catch(Exception e)
		{
			if(e.getClass()==HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else
				throw new HisDataAccessException("HelperMethodsDAO.getResultset"+e);
		}
		UserDeskMenuTemplateMasterVO unitName = new UserDeskMenuTemplateMasterVO();
		try
		{
			if(rs.next())
			{
				unitName.setUnitName(rs.getString(1));
				unitName.setDeptUnitCode(rs.getString(2));
			}
			System.out.println("unit name="+unitName.getUnitName());

				//unitName=rs.getString(1);
		}
		catch(Exception e)
		{
			throw new HisDataAccessException("HisDataAccessException  :getAllDepartment"+e);
		}
		return unitName;
	}
	
	public String getUnit(String _deptUnitCode, UserVO _userVO)
	{
		ResultSet rs=null;
		String query =  "" ;
		Map populateMap =new HashMap();
		
		String filename=RegistrationConfig.QUERY_FILE_FOR_MASTERSDAO;
		String queryKey="SELECT.GBLT_DEPARTMENT_MST";

		Sequence sq=new Sequence();
		
		try
		{
			populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
			populateMap.put(sq.next(),_deptUnitCode);
			populateMap.put(sq.next(),_userVO.getHospitalCode());
		}
		catch(Exception e)
		{
			throw new HisApplicationExecutionException("UnitMasterDAO:populateMap::"+e);
		}
		
		try
		{
			query =HelperMethodsDAO.getQuery(filename,queryKey);
		}
		catch(Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);
		}
		
		System.out.println("   -------> query :: "+query);
		
		try
		{
			rs=HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(),query,populateMap);
		}
		catch(Exception e)
		{
			if(e.getClass()==HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else
				throw new HisDataAccessException("HelperMethodsDAO.getResultset"+e);
		}
		String unitName = new String();
		try
		{
			if(rs.next())
				unitName=rs.getString(1);
		}
		catch(Exception e)
		{
			throw new HisDataAccessException("HisDataAccessException  :getAllDepartment"+e);
		}
		return unitName;
	}

	
	public UserDeskMenuMasterVO gettingUnitName(String _deptUnitCode, UserVO _userVO)
	{
		ResultSet rs=null;
		String query =  "" ;
		Map populateMap =new HashMap();
		
		String filename=RegistrationConfig.QUERY_FILE_FOR_MASTERSDAO;
		String queryKey="SELECT.GBLT_DEPARTMENT_MST";

		Sequence sq=new Sequence();
		
		try
		{
			populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
			populateMap.put(sq.next(),_deptUnitCode);
			System.out.println("deptunit code is..."+_deptUnitCode);
			
			populateMap.put(sq.next(),_userVO.getHospitalCode());
		}
		catch(Exception e)
		{
			throw new HisApplicationExecutionException("UnitMasterDAO:populateMap::"+e);
		}
		
		try
		{
			query =HelperMethodsDAO.getQuery(filename,queryKey);
		}
		catch(Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);
		}
		
		System.out.println("   -------> query :: "+query);
		
		try
		{
			rs=HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(),query,populateMap);
		}
		catch(Exception e)
		{
			if(e.getClass()==HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else
				throw new HisDataAccessException("HelperMethodsDAO.getResultset"+e);
		}
		UserDeskMenuMasterVO unitName = new UserDeskMenuMasterVO();
		try
		{
			if(rs.next())
			{
				unitName.setUnitName(rs.getString(1));
				unitName.setDeptUnitCode(rs.getString(2));
			}
			System.out.println("unit name="+unitName.getUnitName());

				//unitName=rs.getString(1);
		}
		catch(Exception e)
		{
			throw new HisDataAccessException("HisDataAccessException  :getAllDepartment"+e);
		}
		return unitName;
	}
	
	
	public void updateForRoster(UnitMasterVO _unitMasterVO, UserVO _userVO){
		
		String query  = "";
        Map populateMAP =new HashMap();
        String filename=RegistrationConfig.QUERY_FILE_FOR_MASTERSDAO;
        String queryKey="UPDATE.HGSTR_UNIT_DAYS.HGBT_UNIT_MST";                       
        //Properties properties = new Properties();        
        Sequence sq=new Sequence();
        try{
        	query =HelperMethodsDAO.getQuery(filename,queryKey);
        }
        catch(Exception e){
        	throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);
        }
        System.out.println("query"+query);
        
        try{
        	populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
        	populateMAP.put(sq.next(),_userVO.getHospitalCode());
        	populateMAP.put(sq.next(),_unitMasterVO.getDepartmentUnitCode());
        }
        catch(Exception e){
        	throw new HisApplicationExecutionException("UnitMasterDAO:populateMap(unitMasterVO,populateMAP)::"+e);
        }
        try{
        	HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(),query,populateMAP);
        }
        catch(Exception e){
        	throw new HisDataAccessException("HelperMethodsDAO.getResultset"+e);
        }
        
	}
	
	
	public UnitMasterVO getDistinctRecordForFileGeneration(UnitMasterVO unitMasterVO, UserVO _userVO)
	{
		ResultSet rs=null;
		String query =  "" ;
		Map populateMap =new HashMap();
		
		String filename=RegistrationConfig.QUERY_FILE_FOR_MASTERSDAO;
		String queryKey="SELECT.FOR_FILE_GEN.HGBT_UNIT_MST";

		Sequence sq=new Sequence();
		
		try
		{
			populateMap.put(sq.next(),unitMasterVO.getDepartmentCode());
			populateMap.put(sq.next(),unitMasterVO.getDepartmentUnitCode());
			populateMap.put(sq.next(),unitMasterVO.getFileNoRequired());
			populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
			populateMap.put(sq.next(),_userVO.getHospitalCode());
			populateMap.put(sq.next(),unitMasterVO.getFileDepartmentCode());
			populateMap.put(sq.next(),unitMasterVO.getFileDepartmentUnitCode());
		}
		catch(Exception e)
		{
			throw new HisApplicationExecutionException("UnitMasterDAO:populateMap::"+e);
		}
		
		try
		{
			query =HelperMethodsDAO.getQuery(filename,queryKey);
		}
		catch(Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);
		}
		
		System.out.println("   -------> query :: "+query);
		
		try
		{
			rs=HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(),query,populateMap);
		}
		catch(Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.getResultset"+e);
		}
		UserDeskMenuMasterVO unitName = new UserDeskMenuMasterVO();
		try
		{
			if(rs.next())
			{
				rs.beforeFirst();
				unitMasterVO=new UnitMasterVO();
				HelperMethods.populateVOfrmRS(unitMasterVO, rs);
			}
		}
		catch(Exception e)
		{
			throw new HisDataAccessException("HisDataAccessException  :getAllDepartment"+e);
		}
		return unitMasterVO;
	}
	

}//end of class
